package com.urt.service.manager.impl.account;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.account.CustomerBankAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.FinAccountMainVO;
import com.hyniva.sms.ws.vo.account.FinAccountTotalWebAndClientIdsVO;
import com.hyniva.sms.ws.vo.account.FinAccountWebAndClientIdsMainVO;
import com.hyniva.sms.ws.vo.account.FinAccountWebAndClientIdsVO;
import com.hyniva.sms.ws.vo.account.FinCashbookMainVO;
import com.hyniva.sms.ws.vo.account.FinCategoryMainVO;
import com.hyniva.sms.ws.vo.account.FinCustomerWebAndClientIdsVO;
import com.hyniva.sms.ws.vo.account.FinWebAndClientIdsMainVO;
import com.hyniva.sms.ws.vo.account.FinWebAndClientIdsVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountCategoryVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountStatementVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountTotalsVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountTypeVO;
import com.hyniva.sms.ws.vo.account.FinancialCustomerDetailsVO;
import com.hyniva.sms.ws.vo.account.FinancialParticaularAssociationMainVO;
import com.hyniva.sms.ws.vo.account.FinancialParticaularAssociationVO;
import com.hyniva.sms.ws.vo.account.FinancialYearMainVO;
import com.hyniva.sms.ws.vo.account.FinancialYearVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.cashbook.CashBookVO;
import com.hyniva.sms.ws.vo.fee.DeleteStudentPaymentVo;
import com.urt.persistence.interfaces.account.AccountDao;
import com.urt.persistence.model.account.CustomerBankAccountDetails;
import com.urt.persistence.model.account.FinancialAccountCategory;
import com.urt.persistence.model.account.FinancialAccountDetails;
import com.urt.persistence.model.account.FinancialAccountStatement;
import com.urt.persistence.model.account.FinancialAccountTotals;
import com.urt.persistence.model.account.FinancialAccountType;
import com.urt.persistence.model.account.FinancialCashBook;
import com.urt.persistence.model.account.FinancialCustomerDetails;
import com.urt.persistence.model.account.FinancialParticaularAssociation;
import com.urt.persistence.model.account.ViewFinAccountDetails;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.FineFee;
import com.urt.persistence.model.fee.DeleteStudentPayment;
import com.urt.persistence.model.secretary.FinancialYear;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.account.AccountManager;
import com.urt.util.common.RayGunException;
import com.urt.util.jrexception.JRExceptionClient;

import fr.opensagres.xdocreport.document.json.JSONArray;
import fr.opensagres.xdocreport.document.json.JSONObject;

@Component
public class AccountManagerImpl extends UniversalManagerImpl implements AccountManager {

	@Autowired
	private AccountDao accountDao;
	
	public FinancialAccountDetails addOrUpdateFinancialAccountDetails(Customer customer,FinancialAccountDetailsVO finAccountDetailsVO,FinancialAccountCategoryVO finAccountCategoryVO,FinancialAccountTotalsVO financialAccountTotalsVO,long userId){
		FinancialAccountDetails financialAccountDetails = null;
		FinancialCustomerDetails financialCustomerDetails = null;
		Address address = null;
		FinancialYear financialYear = null;
		FinancialAccountType accountType = null;
		FinancialAccountCategory financialAccountCategory = null;
		FinancialAccountTotals financialAccountTotals =null;
		try {
			//FinancialAccountStatement financialAccountStatement = null;
			if(!ObjectFunctions.isNullOrEmpty(finAccountDetailsVO)){
				if (finAccountDetailsVO.getFinancialAccountTypeVO().getId() > 0) {
					accountType = (FinancialAccountType) this.get(FinancialAccountType.class, "id=" + finAccountDetailsVO.getFinancialAccountTypeVO().getId());
				}
				financialYear = (FinancialYear) this.get(FinancialYear.class, "id=" + finAccountDetailsVO.getFinancialYearVO().getId()+ " and status='Y'");
				if (finAccountCategoryVO.getId() > 0) {
					financialAccountCategory = (FinancialAccountCategory) this.get(FinancialAccountCategory.class, "id="+ finAccountCategoryVO.getId());
				}
				
				if(!ObjectFunctions.isNullOrEmpty(accountType) && !ObjectFunctions.isNullOrEmpty(financialYear) && !ObjectFunctions.isNullOrEmpty(financialAccountCategory)){
					if (finAccountDetailsVO.getId() > 0) {
						financialAccountDetails = (FinancialAccountDetails) this.get(FinancialAccountDetails.class, finAccountDetailsVO.getId());
						if(financialAccountTotalsVO.getId()>0)
							financialAccountTotals = (FinancialAccountTotals)this.get(FinancialAccountTotals.class, financialAccountTotalsVO.getId());
						if(accountType.getId() == 2){
							if(!ObjectFunctions.isNullOrEmpty(financialAccountDetails.getFinCustomerDetails())){
								financialCustomerDetails= financialAccountDetails.getFinCustomerDetails();
								if(!ObjectFunctions.isNullOrEmpty(financialAccountDetails.getFinCustomerDetails().getAddress()))
									address=financialAccountDetails.getFinCustomerDetails().getAddress();
								else
									address = new Address();
							}else{
								financialCustomerDetails= new FinancialCustomerDetails();
								if(!ObjectFunctions.isNullOrEmpty(financialAccountDetails.getFinCustomerDetails().getAddress()))
									address=financialAccountDetails.getFinCustomerDetails().getAddress();
								else
									address = new Address();
							}
							
						}
					} else {
						financialAccountDetails = new FinancialAccountDetails();
						financialCustomerDetails = new FinancialCustomerDetails();
						financialAccountTotals = new FinancialAccountTotals();
						address = new Address();
						financialAccountDetails.setCreatedById(userId);
						financialAccountDetails.setCreatedDate(new Date());
						financialCustomerDetails.setCreatedById(userId);
						financialCustomerDetails.setCreatedDate(new Date());
						address.setCreatedById(userId);
						address.setCreatedDate(new Date());
						financialAccountTotals.setCreatedById(userId);
						financialAccountTotals.setCreatedDate(new Date());
					}
					financialAccountTotals.setLastUpdatedById(userId);
					financialAccountTotals.setLastUpdatedDate(new Date());
					financialAccountDetails.setLastUpdatedById(userId);
					financialAccountDetails.setLastUpdatedDate(new Date());
					
					financialAccountDetails.setCustomer(customer);
					financialAccountDetails.setAccountName(finAccountDetailsVO.getAccountName());
					if (!ObjectFunctions.isNullOrEmpty(financialYear)) {
						financialAccountDetails.setFinancialYear(financialYear);
					}
					if (!ObjectFunctions.isNullOrEmpty(accountType)) {
						financialAccountDetails.setFinancialAccountType(accountType);
					}
					if (!ObjectFunctions.isNullOrEmpty(financialAccountCategory)) {
						financialAccountDetails.setFinancialAccountCategory(financialAccountCategory);
					}
					if (accountType.getId() == 2) {
						financialCustomerDetails.setLastUpdatedById(userId);
						financialCustomerDetails.setLastUpdatedDate(new Date());
						address.setLastUpdatedById(userId);
						address.setLastUpdatedDate(new Date());
						
						financialCustomerDetails.setContactName(finAccountDetailsVO.getFinancialCustomerDetailsVO().getContactName());
						financialCustomerDetails.setCustomer(customer);
						financialCustomerDetails.setCustomerName(finAccountDetailsVO.getFinancialCustomerDetailsVO().getCustomerName());
						
						financialCustomerDetails.setMobileNumber(finAccountDetailsVO.getFinancialCustomerDetailsVO().getMobileNumber());
						if (!ObjectFunctions.isNullOrEmpty(address)) {
							address.setCity(finAccountDetailsVO.getFinancialCustomerDetailsVO().getAddressVO().getCity());
							address.setAddressLine1(finAccountDetailsVO.getFinancialCustomerDetailsVO().getAddressVO().getAddressLine1());
							address.setStreetName(finAccountDetailsVO.getFinancialCustomerDetailsVO().getAddressVO().getStreetName());
							address.setPostalCode(finAccountDetailsVO.getFinancialCustomerDetailsVO().getAddressVO().getPostalCode());
							address.setCountry(finAccountDetailsVO.getFinancialCustomerDetailsVO().getAddressVO().getCountry());
							address.setState(finAccountDetailsVO.getFinancialCustomerDetailsVO().getAddressVO().getState());
							address.setEmail(finAccountDetailsVO.getFinancialCustomerDetailsVO().getAddressVO().getEmail());
							address.setLastAccessDate(new Date());
							Address vendorAddress = (Address) this.saveOrUpdateObject(address);
							financialCustomerDetails.setAddress(vendorAddress);
							address = null;
							vendorAddress=null;
						}
						financialAccountDetails.setItPanNumber(finAccountDetailsVO.getItPanNumber());
						financialAccountDetails.setTinNumber(finAccountDetailsVO.getTinNumber());
						financialAccountDetails.setGstNumber(finAccountDetailsVO.getGstNumber());
						if(userId==1){
							financialAccountDetails.setGstIssueDate(ObjectFunctions.isNullOrEmpty(finAccountDetailsVO.getGstIssueDateStr())? null : DateFunctions.convertStringToDate(finAccountDetailsVO.getGstIssueDateStr()));
							financialAccountDetails.setTinIssueDate(ObjectFunctions.isNullOrEmpty(finAccountDetailsVO.getTinIssueDateStr())? null : DateFunctions.convertStringToDate(finAccountDetailsVO.getTinIssueDateStr()));
						}else{
							financialAccountDetails.setGstIssueDate(finAccountDetailsVO.getGstIssueDate());
							financialAccountDetails.setTinIssueDate(finAccountDetailsVO.getTinIssueDate());
							
						}
						financialCustomerDetails = (FinancialCustomerDetails) this.merge(financialCustomerDetails);
						financialAccountDetails.setFinCustomerDetails(financialCustomerDetails);
					}
					financialAccountDetails = (FinancialAccountDetails) this.merge(financialAccountDetails);
					financialAccountTotals.setBalanceAmount(financialAccountTotalsVO.getBalanceAmount());
					financialAccountTotals.setTransactionType(financialAccountTotalsVO.getTransactionType());
					financialAccountTotals.setFinancialAccountDetails(financialAccountDetails);
					financialAccountTotals.setFinancialYear(financialYear);
					financialAccountTotals.setCustomer(customer);
					financialAccountTotals = (FinancialAccountTotals)this.saveOrUpdateObject(financialAccountTotals);
					financialAccountDetails.setFinancialAccountTotals(financialAccountTotals);
				}else
					return null;
			}
			return financialAccountDetails;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}finally{
			financialAccountDetails = null;
			financialCustomerDetails = null;
			address = null;
			financialYear = null;
			accountType = null;
			financialAccountCategory = null;
			customer=null;
			finAccountDetailsVO=null;
			finAccountCategoryVO=null;
		}
		return null;
	}
	public int createOrUpdateFinancialAccountDetails(Customer customer,FinancialAccountDetailsVO finAccountDetailsVO,FinancialAccountCategoryVO finAccountCategoryVO,FinancialAccountTotalsVO financialAccountTotalsVO,long userId){
		FinancialAccountDetails financialAccountDetails = null;
		try {
			if(!ObjectFunctions.isNullOrEmpty(finAccountDetailsVO)){
				financialAccountDetails = addOrUpdateFinancialAccountDetails(customer,finAccountDetailsVO,finAccountCategoryVO,financialAccountTotalsVO, userId);
				if(!ObjectFunctions.isNullOrEmpty(financialAccountDetails)){
					if(finAccountDetailsVO.getId() > 0)
						return 1;
					else {
						return 2;
					}
				}else
					return 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 0;
		}finally{
			financialAccountDetails = null;
			customer=null;
			finAccountDetailsVO=null;
			finAccountCategoryVO=null;
		}
		return 0;
	}

	public FinancialAccountDetailsVO getFinancialAccountDetails(long accountDetailsId){
		try {
			FinancialAccountDetails financialAccountDetails =(FinancialAccountDetails)this.get(FinancialAccountDetails.class, accountDetailsId);
			if(!ObjectFunctions.isNullOrEmpty(financialAccountDetails)){
				FinancialAccountDetailsVO accountDetailsVO = getFinancialAccountDetailsVO(financialAccountDetails);
				return accountDetailsVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public FinancialCashBook saveCashBook(CashBookVO cashBookVO, Customer customer,FinancialYear financialYear, FinancialAccountDetails accountDetails) {
		try {
			FinancialCashBook financialCashBook=null;
			BankMaster bankMaster = null;
			if (!ObjectFunctions.isNullOrEmpty(customer) && !ObjectFunctions.isNullOrEmpty(financialYear) && !ObjectFunctions.isNullOrEmpty(accountDetails)) {
				if(cashBookVO.getBankId()>0){
					bankMaster = (BankMaster) this.get(BankMaster.class, cashBookVO.getBankId());
				}
				if(cashBookVO.getId()>0)
					financialCashBook =(FinancialCashBook)this.get(FinancialCashBook.class, cashBookVO.getId());
				else
					financialCashBook = new FinancialCashBook();
				
				financialCashBook=financialCashBook.deepCopyVoToCashBookObj(cashBookVO, customer,financialYear, accountDetails,financialCashBook,bankMaster);
				return financialCashBook =(FinancialCashBook)this.saveOrUpdateObject(financialCashBook);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public long addParticularAssociationToAccount(String concessionFormData,AcademicYear academicYear, long userId, long custId) {
		try {
			FinancialParticaularAssociation finParticaularAssociation =  null;
			JSONObject formData = new JSONObject(concessionFormData);
			JSONArray particularAssociationToAccountJsonArray = (JSONArray) formData.get("data");
			long feeTypeAccountId = 0;
			long feeTypeId =0;
			long accountId=0;
			JSONObject particularToAccountJson = null;
			boolean status=true;
			if (!ObjectFunctions.isNullOrEmpty(particularAssociationToAccountJsonArray)) {
				for (int i = 0; i < particularAssociationToAccountJsonArray.length(); i++) {
					particularToAccountJson = particularAssociationToAccountJsonArray.getJSONObject(i);
					if (!ObjectFunctions.isNullOrEmpty(particularToAccountJson)) {
						if (!ObjectFunctions.isNullOrEmpty(particularToAccountJson.get("feeTypeAccountId"))) {
							feeTypeAccountId = Long.valueOf((String) particularToAccountJson.get("feeTypeAccountId"));
						}
						if (!ObjectFunctions.isNullOrEmpty(particularToAccountJson.get("feeTypeId"))) {
							feeTypeId = Long.valueOf((String) particularToAccountJson.get("feeTypeId"));
						}
						if (!ObjectFunctions.isNullOrEmpty(particularToAccountJson.get("accountId"))) {
							accountId = Long.valueOf((String) particularToAccountJson.get("accountId"));
						}
						if(feeTypeId >0 && accountId>0){
							finParticaularAssociation =addOrUpdateFinancialParticaularAssociation(custId,feeTypeAccountId,feeTypeId,accountId,userId);
							if(ObjectFunctions.isNullOrEmpty(finParticaularAssociation)){
								status=false;
							}
						}
						
					}
				}
				if(status)
					return 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 0;
		}
		return 0;
	}
	public FinancialParticaularAssociation addOrUpdateFinancialParticaularAssociation(long custId,long feeTypeAccountId,long feeTypeId,long accountId,long userId){
		try {
			FinancialParticaularAssociation finParticaularAssociation =  null;
			FeeType feeType = (FeeType)this.get(FeeType.class, feeTypeId);
			FinancialAccountDetails financialAccountDetails = (FinancialAccountDetails)this.get(FinancialAccountDetails.class, accountId);
			if(!ObjectFunctions.isNullOrEmpty(feeType) && !ObjectFunctions.isNullOrEmpty(financialAccountDetails)){
				finParticaularAssociation = (FinancialParticaularAssociation)this.get(FinancialParticaularAssociation.class, feeTypeAccountId);
				if(!ObjectFunctions.isNullOrEmpty(finParticaularAssociation)){
					finParticaularAssociation.setLastUpdatedById(userId);
					finParticaularAssociation.setLastUpdatedDate(new Date());
				}else{
					finParticaularAssociation = new FinancialParticaularAssociation();
					finParticaularAssociation.setCreatedDate(new Date());
					finParticaularAssociation.setCreatedById(userId);
				}
				finParticaularAssociation.setCustId(custId);
				finParticaularAssociation.setFeeType(feeType);
				finParticaularAssociation.setFinancialAccountDetails(financialAccountDetails);
				finParticaularAssociation = (FinancialParticaularAssociation)this.save(finParticaularAssociation);
			}else{
				finParticaularAssociation = (FinancialParticaularAssociation)this.get(FinancialParticaularAssociation.class, "custId="+custId+" and id="+feeTypeAccountId+" and paticularId="+feeTypeId);
				if(!ObjectFunctions.isNullOrEmpty(finParticaularAssociation)){
					this.remove(FinancialParticaularAssociation.class, feeTypeAccountId);
				}
			}
			return finParticaularAssociation;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinCategoryMainVO getAccountCategoryDetails(long custId,String createdDate){
		try {
			StringBuilder query = new StringBuilder("custId=").append(custId);
			if(!StringFunctions.isNullOrEmpty(createdDate))
				query.append(" and createdDate >='"+createdDate+"' ");
			List<FinancialAccountCategory> accountCategoriesList = this.getAll(FinancialAccountCategory.class, query.toString());
			FinCategoryMainVO finCategoryMainVO = new FinCategoryMainVO();
			if(!ObjectFunctions.isNullOrEmpty(accountCategoriesList)){
				for(FinancialAccountCategory accountCategory : accountCategoriesList){
					FinancialAccountCategoryVO accountCategoryVO = this.getFinancialAccountCategoryVO(accountCategory);
					accountCategoryVO.setFinancialAccountStatementVO(this.getFinancialAccountStatementVO(accountCategory.getFinancialAccountStatement()));
					accountCategoryVO.setFinancialAccountTypeVO(this.getFinancialAccountTypeVO(accountCategory.getFinancialAccountType()));
					finCategoryMainVO.getFinancialAccountCategoryVOs().add(accountCategoryVO);
				}
				return finCategoryMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinancialAccountCategoryVO getFinancialAccountCategoryVO(FinancialAccountCategory accountCategory){
		try {
			FinancialAccountCategoryVO accountCategoryVO = new FinancialAccountCategoryVO();
			accountCategoryVO.setId(accountCategory.getId());
			accountCategoryVO.setCustId(accountCategory.getCustId());
			accountCategoryVO.setCartegoryName(accountCategory.getCartegoryName());
			return accountCategoryVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinancialAccountStatementVO getFinancialAccountStatementVO(FinancialAccountStatement finAccountStatement){
		try {
			FinancialAccountStatementVO accountStatementVO = new FinancialAccountStatementVO();
			accountStatementVO.setId(finAccountStatement.getId());
			accountStatementVO.setStatementName(finAccountStatement.getStatementName());
			accountStatementVO.setStatmentCode(finAccountStatement.getStatmentCode());
			return accountStatementVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinancialAccountTypeVO getFinancialAccountTypeVO(FinancialAccountType accountType){
		try {
			FinancialAccountTypeVO accountTypeVO = new FinancialAccountTypeVO();
			accountTypeVO.setId(accountType.getId());
			accountTypeVO.setAccountType(accountType.getAccountType());
			accountTypeVO.setAccountCode(accountType.getAccountCode());
			return accountTypeVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinancialYearVO getFinancialYearVO(FinancialYear financialYear){
		try {
			FinancialYearVO financialYearVO = new FinancialYearVO();
			financialYearVO.setId(financialYear.getId());
			financialYearVO.setDescription(financialYear.getDescription());
			financialYearVO.setStartDate(financialYear.getStartDateStr());
			financialYearVO.setEndDate(financialYear.getEndDateStr());
			financialYearVO.setStatus(financialYear.getStatus());
			financialYearVO.setYearName(financialYear.getYearName());
			return financialYearVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinancialCustomerDetailsVO getFinancialCustomerDetailsVO(FinancialCustomerDetails customerDetails){
		try {
			FinancialCustomerDetailsVO customerDetailsVO = new FinancialCustomerDetailsVO();
			customerDetailsVO.setId(customerDetails.getId());
			customerDetailsVO.setCustomerName(customerDetails.getCustomerName());
			customerDetailsVO.setContactName(customerDetails.getContactName());
			customerDetailsVO.setMobileNumber(customerDetails.getMobileNumber());
			if(!ObjectFunctions.isNullOrEmpty(customerDetails.getAddress())){
				AddressVO addressVO = new AddressVO();
				addressVO.setCity(customerDetails.getAddress().getCity());
				addressVO.setAddressLine1(customerDetails.getAddress().getAddressLine1());
				addressVO.setStreetName(customerDetails.getAddress().getStreetName());
				addressVO.setPostalCode(customerDetails.getAddress().getPostalCode());
				addressVO.setCountry(customerDetails.getAddress().getCountry());
				addressVO.setState(customerDetails.getAddress().getState());
				addressVO.setEmail(customerDetails.getAddress().getEmail());
				customerDetailsVO.setAddressVO(addressVO);
			}
			return customerDetailsVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinAccountMainVO getAccountDetails(long custId,String createdDate){
		try {
			StringBuilder query = new StringBuilder("custId=").append(custId);
			if(!StringFunctions.isNullOrEmpty(createdDate))
				query.append(" and lastUpdatedDate >='"+createdDate+"' ");
			FinAccountMainVO accountMainVO = new FinAccountMainVO();
			List<FinancialAccountDetails> accountDetailsList = this.getAll(FinancialAccountDetails.class, query.toString());
			if(!ObjectFunctions.isNullOrEmpty(accountDetailsList)){
				for(FinancialAccountDetails accountDetails : accountDetailsList){
					FinancialAccountDetailsVO accountDetailsVO = getFinancialAccountDetailsVO(accountDetails);
					accountMainVO.getFinancialAccountDetailsVOs().add(accountDetailsVO);
					accountDetails=null;
					accountDetailsVO=null;
				}
				return accountMainVO;
			}
			accountMainVO=null;
			accountDetailsList=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public FinancialAccountDetailsVO getFinancialAccountDetailsVO(FinancialAccountDetails accountDetails) {
		try {
			FinancialAccountDetailsVO accountDetailsVO = new FinancialAccountDetailsVO();
			accountDetailsVO.setId(accountDetails.getId());
			accountDetailsVO.setAccountName(accountDetails.getAccountName());
			accountDetailsVO.setTinNumber(accountDetails.getTinNumber());
			accountDetailsVO.setTinIssueDateStr(accountDetails.getTinIssueDateStr());
			accountDetailsVO.setGstNumber(accountDetails.getGstNumber());
			accountDetailsVO.setGstIssueDateStr(accountDetails.getGstIssueDateStr());
			accountDetailsVO.setItPanNumber(accountDetails.getItPanNumber());
			accountDetailsVO.setGstIssueDate(accountDetails.getGstIssueDate());
			accountDetailsVO.setTinIssueDate(accountDetails.getTinIssueDate());
			accountDetailsVO.setFinancialAccountCategoryVO(this.getFinancialAccountCategoryVO(accountDetails.getFinancialAccountCategory()));
			accountDetailsVO.setFinancialAccountTypeVO(this.getFinancialAccountTypeVO(accountDetails.getFinancialAccountType()));
			accountDetailsVO.setFinancialYearVO(this.getFinancialYearVO(accountDetails.getFinancialYear()));
			accountDetailsVO.setFinancialAccountTotalsVO(this.getFinancialAccountTotalsVO(accountDetails.getId(),accountDetails.getFinancialYear().getId()));
			if (accountDetails.getFinancialAccountType().getId() == 2) {
				accountDetailsVO.setFinancialCustomerDetailsVO(this.getFinancialCustomerDetailsVO(accountDetails.getFinCustomerDetails()));
			}
			return accountDetailsVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinancialYearMainVO getFinancialYearDetails(String cretedDate){
		try {
			List<FinancialYear> financialYearList = this.getAll(FinancialYear.class);
			if(!ObjectFunctions.isNullOrEmpty(financialYearList)){
				FinancialYearMainVO financialYearMainVO = new FinancialYearMainVO();
				for(FinancialYear financialYear : financialYearList){
					financialYearMainVO.getFinancialYearVOS().add(this.getFinancialYearVO(financialYear));
				}
				return financialYearMainVO;
			}
			financialYearList=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinCashbookMainVO getCashbookDetails(long custId,long financialYearId,String createdDate){
		try {
			StringBuilder query = new StringBuilder("custId=").append(custId).append(" and financialYearId=").append(financialYearId);
			if(!StringFunctions.isNullOrEmpty(createdDate))
				query.append(" and createdDate >='"+createdDate+"' ");
			FinCashbookMainVO cashbookMainVO = new FinCashbookMainVO();
			List<FinancialCashBook> cashBooksList = this.getAll(FinancialCashBook.class, query.toString());
			if(!ObjectFunctions.isNullOrEmpty(cashBooksList)){
				for(FinancialCashBook cashBook : cashBooksList){
					CashBookVO cashBookVO = new CashBookVO();
					cashBookVO.setId(cashBook.getId());
					cashBookVO.setTransactionDateStr(cashBook.getTransactionDateSt());
					cashBookVO.setNarration(cashBook.getNarration());
					cashBookVO.setVocherNumber(cashBook.getVocherNumber());
					cashBookVO.setTransactionType(cashBook.getTransactionType());
					cashBookVO.setAmount(cashBook.getAmount());
					cashBookVO.setAccountId(cashBook.getFinancialAccountDetails().getId());
					cashbookMainVO.getCashBookVOs().add(cashBookVO);
				}
				return cashbookMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinancialAccountTotalsVO getFinancialAccountTotalsVO(long accountId,long finYearId){
		try {
			FinancialAccountTotals accountTotals = (FinancialAccountTotals)this.get(FinancialAccountTotals.class, "finAccountId="+accountId+" and financialYearId="+finYearId);
			if(!ObjectFunctions.isNullOrEmpty(accountTotals)){
				FinancialAccountTotalsVO accountTotalsVO = new FinancialAccountTotalsVO();
				accountTotalsVO.setId(accountTotals.getId());
				accountTotalsVO.setTransactionType(accountTotals.getTransactionType());
				accountTotalsVO.setBalanceAmount(accountTotals.getBalanceAmount());
				accountTotalsVO.setFinAccountId(accountId);
				accountTotalsVO.setFinancialYearId(finYearId);
				return accountTotalsVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public int saveVoucherDetailsInMontlyAccountTotal(double amount,Date transactionDate, String transactionType, Customer customer,FinancialYear financialYear,FinancialAccountDetails accountDetails, long userId) {
		try {
			FinancialAccountTotals accountTotals = (FinancialAccountTotals) this.get(FinancialAccountTotals.class,"custId=" + customer.getId()+ " and financialYearId="+ financialYear.getId()+ " and finAccountId="+ accountDetails.getId());
			if (ObjectFunctions.isNullOrEmpty(accountTotals)) {
				accountTotals = new FinancialAccountTotals();
				accountTotals.setCreatedById(userId);
				accountTotals.setCreatedDate(new Date());
			}
			int monthNumber = DateFunctions.getMonthOfDate(transactionDate);
			accountTotals.setLastUpdatedById(userId);
			accountTotals.setLastUpdatedDate(new Date());
			if (monthNumber == 1) {
				accountTotals.setMonth1(getMonthTotalValue(transactionType,amount, accountTotals.getMonth1()));
			} else if (monthNumber == 2) {
				accountTotals.setMonth2(getMonthTotalValue(transactionType,amount, accountTotals.getMonth2()));
			} else if (monthNumber == 3) {
				accountTotals.setMonth3(getMonthTotalValue(transactionType,amount, accountTotals.getMonth3()));
			} else if (monthNumber == 4) {
				accountTotals.setMonth4(getMonthTotalValue(transactionType,amount, accountTotals.getMonth4()));
			} else if (monthNumber == 5) {
				accountTotals.setMonth5(getMonthTotalValue(transactionType,amount, accountTotals.getMonth5()));
			} else if (monthNumber == 6) {
				accountTotals.setMonth6(getMonthTotalValue(transactionType,amount, accountTotals.getMonth6()));
			} else if (monthNumber == 7) {
				accountTotals.setMonth7(getMonthTotalValue(transactionType,amount, accountTotals.getMonth7()));
			} else if (monthNumber == 8) {
				accountTotals.setMonth8(getMonthTotalValue(transactionType,amount, accountTotals.getMonth8()));
			} else if (monthNumber == 9) {
				accountTotals.setMonth9(getMonthTotalValue(transactionType,amount, accountTotals.getMonth9()));
			} else if (monthNumber == 10) {
				accountTotals.setMonth10(getMonthTotalValue(transactionType,amount, accountTotals.getMonth10()));
			} else if (monthNumber == 11) {
				accountTotals.setMonth11(getMonthTotalValue(transactionType,amount, accountTotals.getMonth11()));
			} else if (monthNumber == 12) {
				accountTotals.setMonth12(getMonthTotalValue(transactionType,amount, accountTotals.getMonth12()));
			}
			this.saveOrUpdateObject(accountTotals);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 0;
		}
	}

	public double getMonthTotalValue(String transactionType, double amount,double monthTotalAmount) {
		double amountTotal = 0;
		if ("C".equalsIgnoreCase(transactionType))
			amountTotal = monthTotalAmount + amount;
		else if ("D".equalsIgnoreCase(transactionType))
			amountTotal = monthTotalAmount - amount;
		return amountTotal;
	}
	
	public List<ViewFinAccountDetails> getFinalReportDetails(long custId,long financialYearId,String fromDate,String endDate,String statementIds){
		return accountDao.getFinalReportDetails(custId,financialYearId,fromDate,endDate,statementIds);
	}
	public List<ViewFinAccountDetails> getAllAccountOpeningBalance(long custId,long financialYearId,String statementIds){
		return accountDao.getAllAccountOpeningBalance(custId,financialYearId,statementIds);
	}
	public List<ViewFinAccountDetails> getAllAccountOpeningBalanceCashInHand(long custId,long financialYearId,String statementIds){
		return accountDao.getAllAccountOpeningBalanceCashInHand(custId,financialYearId,statementIds);
	}
	public FinancialParticaularAssociationMainVO getFinancialParticaularAssociation(long custId,String createdDate){
		try {
			StringBuilder query = new StringBuilder("custId=").append(custId);
			if(!StringFunctions.isNullOrEmpty(createdDate))
				query.append(" and createdDate >='"+createdDate+"' ");
			List<FinancialParticaularAssociation> particaularAssociationList = this.getAll(FinancialParticaularAssociation.class, query.toString());
			if(!ObjectFunctions.isNullOrEmpty(particaularAssociationList)){
				FinancialParticaularAssociationMainVO associationMainVO = new FinancialParticaularAssociationMainVO();
				for(FinancialParticaularAssociation association : particaularAssociationList){
					FinancialParticaularAssociationVO particaularAssociationVO = new FinancialParticaularAssociationVO();
					particaularAssociationVO.setId(association.getId());
					particaularAssociationVO.setParticularId(association.getFeeType().getId());
					particaularAssociationVO.setAccountId(association.getFinancialAccountDetails().getId());
					associationMainVO.getFinancialParticaularAssociationVOs().add(particaularAssociationVO);
					association=null;
					particaularAssociationVO=null;
				}
				particaularAssociationList=null;
				return associationMainVO; 
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinWebAndClientIdsMainVO addOrUpdateFinAccountMainVO(FinancialYearMainVO financialYearMainVO){
		boolean status =true;
		try {
			FinancialYear financialYear=null;
			FinWebAndClientIdsMainVO webAndClientIdsMainVO = null;
			APIStatusVO apistatusVO =null;
			if(!ObjectFunctions.isNullOrEmpty(financialYearMainVO)){
				webAndClientIdsMainVO = new FinWebAndClientIdsMainVO();
				for(FinancialYearVO financialYearVO : financialYearMainVO.getFinancialYearVOS()){
					if(financialYearVO.getId()>0){
						financialYear = this.addOrUpdateFinancialYear(financialYearVO);
						if(ObjectFunctions.isNullOrEmpty(financialYear)){
							status =false;
						}
					}else{
						financialYear = this.addOrUpdateFinancialYear(financialYearVO);
						if(!ObjectFunctions.isNullOrEmpty(financialYear)){
							webAndClientIdsMainVO.getFinWebAndClientIdsVOs().add(this.getFinWebAndClientIdsVO(financialYear.getId(), financialYearVO.getClientId()));
						}else{
							status =false;
						}
					}
				}
			}
			if(status){
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
			}else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
			webAndClientIdsMainVO.setApiStatus(apistatusVO);
			return webAndClientIdsMainVO;
		} catch (Exception ex) {
			status =false;
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public FinancialYear addOrUpdateFinancialYear(FinancialYearVO financialYearVO){
		try {
			FinancialYear financialYear=null;
			if(financialYearVO.getId()>0){
				financialYear = (FinancialYear)this.get(FinancialYear.class, financialYearVO.getId());
			}else{
				financialYear = new FinancialYear();
				financialYear.setCreatedById(1);
				financialYear.setCreatedDate(new Date());
			}
			financialYear.setLastUpdatedById(1);
			financialYear.setLastUpdatedDate(new Date());
			financialYear.setYearName(financialYearVO.getYearName());
			financialYear.setDescription(financialYearVO.getDescription());
			financialYear.setStartDate(ObjectFunctions.isNullOrEmpty(financialYearVO.getStartDate())? null : DateFunctions.convertStringToDate(financialYearVO.getStartDate()));
			financialYear.setEndDate(ObjectFunctions.isNullOrEmpty(financialYearVO.getEndDate())? null : DateFunctions.convertStringToDate(financialYearVO.getEndDate()));
			financialYear.setStatus(financialYearVO.getStatus());
			financialYear = (FinancialYear) this.saveOrUpdateObject(financialYear);
			return financialYear;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinWebAndClientIdsVO getFinWebAndClientIdsVO(long webId,long clientId){
		try {
			if(webId >0 && clientId>0){
				FinWebAndClientIdsVO webAndClientIdsVO = new FinWebAndClientIdsVO();
				webAndClientIdsVO.setWebId(webId);
				webAndClientIdsVO.setClientId(clientId);
				return webAndClientIdsVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public FinWebAndClientIdsMainVO addOrUpdateAccountCategoryDetails(FinCategoryMainVO finCategoryMainVO, long custId) {
		try {
			FinWebAndClientIdsMainVO webAndClientIdsMainVO = null;
			APIStatusVO apistatusVO = null;
			boolean status = true;
			FinancialAccountCategory accountCategory = null;
			if (!ObjectFunctions.isNullOrEmpty(finCategoryMainVO)) {
				webAndClientIdsMainVO = new FinWebAndClientIdsMainVO();
				for (FinancialAccountCategoryVO accountCategoryVO : finCategoryMainVO.getFinancialAccountCategoryVOs()) {
					if (accountCategoryVO.getId() > 0) {
						accountCategory = addOrUpdateFinAccountCategory(custId,accountCategoryVO, 1);
						if (ObjectFunctions.isNullOrEmpty(accountCategory)) {
							status = false;
						}
					} else {
						accountCategory = addOrUpdateFinAccountCategory(custId,accountCategoryVO, 1);
						if (!ObjectFunctions.isNullOrEmpty(accountCategory)) {
							webAndClientIdsMainVO.getFinWebAndClientIdsVOs().add(this.getFinWebAndClientIdsVO(accountCategory.getId(),accountCategoryVO.getClientId()));
						} else {
							status = false;
						}
					}
				}
				if (status) {
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				} else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
				webAndClientIdsMainVO.setApiStatus(apistatusVO);
				return webAndClientIdsMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public int addOrUpdateAccountCategory(long custId,FinancialAccountCategoryVO financialAccountCategoryVO, long userId) {
		try {
			int returnCode;
			FinancialAccountCategory fAccountCategory = null;
			if (financialAccountCategoryVO.getId() > 0) {
				fAccountCategory = addOrUpdateFinAccountCategory(custId,financialAccountCategoryVO, userId);
				if (!ObjectFunctions.isNullOrEmpty(fAccountCategory))
					returnCode = 1;
				else
					returnCode = 0;
			} else {
				fAccountCategory = addOrUpdateFinAccountCategory(custId,financialAccountCategoryVO, userId);
				if (!ObjectFunctions.isNullOrEmpty(fAccountCategory))
					returnCode = 2;
				else
					returnCode = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 0;
		}
		return 0;
	}

	public FinancialAccountCategory addOrUpdateFinAccountCategory(long custId,FinancialAccountCategoryVO financialAccountCategoryVO, long userId) {
		try {
			FinancialAccountCategory fAccountCategory = null;
			if (financialAccountCategoryVO.getId() > 0) {
				fAccountCategory = (FinancialAccountCategory) this.get(FinancialAccountCategory.class,financialAccountCategoryVO.getId());
			} else {
				fAccountCategory = new FinancialAccountCategory();
				fAccountCategory.setCreatedById(userId);
				fAccountCategory.setCreatedDate(new Date());
			}
			fAccountCategory.setLastUpdatedById(userId);
			fAccountCategory.setLastUpdatedDate(new Date());
			fAccountCategory.setCustId(custId);
			fAccountCategory.setCartegoryName(financialAccountCategoryVO.getCartegoryName());
			if (financialAccountCategoryVO.getFinancialAccountTypeVO().getId() > 0) {
				FinancialAccountType accountType = (FinancialAccountType) this.get(FinancialAccountType.class, "id="+ financialAccountCategoryVO.getFinancialAccountTypeVO().getId());
				fAccountCategory.setFinancialAccountType(accountType);
			}
			if (!ObjectFunctions.isNullOrEmpty(financialAccountCategoryVO.getFinancialAccountStatementVO()) && financialAccountCategoryVO.getFinancialAccountStatementVO().getId() > 0) {
				FinancialAccountStatement accountStatement = (FinancialAccountStatement) this.get(FinancialAccountStatement.class, "id="+ financialAccountCategoryVO.getFinancialAccountStatementVO().getId());
				fAccountCategory.setFinancialAccountStatement(accountStatement);
			} else {
				FinancialAccountStatement accountStatement = (FinancialAccountStatement) this.get(FinancialAccountStatement.class, "id=" + 4);
				fAccountCategory.setFinancialAccountStatement(accountStatement);
			}
			return fAccountCategory = (FinancialAccountCategory) this.saveOrUpdateObject(fAccountCategory);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public FinAccountWebAndClientIdsMainVO addOrUpdateAccountDetailsClient(FinAccountMainVO finAccountMainVO, long custId, long financialYearId) {
		try {
			FinAccountWebAndClientIdsMainVO webAndClientIdsMainVO = null;
			APIStatusVO apistatusVO = null;
			boolean status = true;
			FinancialAccountDetails financialAccountDetails = null;
			if (!ObjectFunctions.isNullOrEmpty(finAccountMainVO)) {
				webAndClientIdsMainVO = new FinAccountWebAndClientIdsMainVO();
				Customer customer = (Customer) this.get(Customer.class, custId);
				for (FinancialAccountDetailsVO accountDetailsVO : finAccountMainVO.getFinancialAccountDetailsVOs()) {
					if (accountDetailsVO.getId() > 0) {
						financialAccountDetails = addOrUpdateFinancialAccountDetails(customer, accountDetailsVO,accountDetailsVO.getFinancialAccountCategoryVO(),accountDetailsVO.getFinancialAccountTotalsVO(),1);
						if (ObjectFunctions.isNullOrEmpty(financialAccountDetails)) {
							status = false;
						}
					} else {
						financialAccountDetails = addOrUpdateFinancialAccountDetails(customer, accountDetailsVO,accountDetailsVO.getFinancialAccountCategoryVO(),accountDetailsVO.getFinancialAccountTotalsVO(),1);
						if (!ObjectFunctions.isNullOrEmpty(financialAccountDetails)) {
							webAndClientIdsMainVO.getAccountWebAndClientIdsVOs().add(this.getFinAccountWebAndClientIdsVO(financialAccountDetails,accountDetailsVO));
						} else {
							status = false;
						}
					}
				}
				if (status) {
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				} else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
				webAndClientIdsMainVO.setApiStatus(apistatusVO);
				return webAndClientIdsMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinAccountWebAndClientIdsVO getFinAccountWebAndClientIdsVO(FinancialAccountDetails financialAccountDetails,FinancialAccountDetailsVO accountDetailsVO){
		try {
			FinAccountWebAndClientIdsVO accountWebAndClientIdsVO = new FinAccountWebAndClientIdsVO();
			accountWebAndClientIdsVO.setWebId(financialAccountDetails.getId());
			accountWebAndClientIdsVO.setClientId(accountDetailsVO.getClientId());
			if(!ObjectFunctions.isNullOrEmpty(financialAccountDetails.getFinCustomerDetails())){
				if(financialAccountDetails.getFinCustomerDetails().getId()>0){
					FinCustomerWebAndClientIdsVO finCustomerWebAndClientIdsVO =new FinCustomerWebAndClientIdsVO();
					finCustomerWebAndClientIdsVO.setWebId(financialAccountDetails.getFinCustomerDetails().getId());
					finCustomerWebAndClientIdsVO.setClientId(accountDetailsVO.getFinancialCustomerDetailsVO().getClientId());
					accountWebAndClientIdsVO.setCustomerWebAndClientIdsVO(finCustomerWebAndClientIdsVO);
				}
			}
			FinAccountTotalWebAndClientIdsVO totalWebAndClientIdsVO = new FinAccountTotalWebAndClientIdsVO();
			totalWebAndClientIdsVO.setWebId(financialAccountDetails.getFinancialAccountTotals().getId());
			totalWebAndClientIdsVO.setClientId(accountDetailsVO.getFinancialAccountTotalsVO().getClientId());
			accountWebAndClientIdsVO.setAccountTotalWebAndClientIdsVO(totalWebAndClientIdsVO);
			return accountWebAndClientIdsVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public FinWebAndClientIdsMainVO addOrUpdateCashbook(FinCashbookMainVO finCashbookMainVO, long custId,long financialYearId) {
		try {
			FinWebAndClientIdsMainVO webAndClientIdsMainVO = null;
			APIStatusVO apistatusVO = null;
			boolean status = true;
			FinancialCashBook financialCashBook = null;
			if (!ObjectFunctions.isNullOrEmpty(finCashbookMainVO)) {
				webAndClientIdsMainVO = new FinWebAndClientIdsMainVO();
				Customer customer = (Customer) this.get(Customer.class, custId);
				FinancialYear financialYear = (FinancialYear) this.get(FinancialYear.class, financialYearId);
				for (CashBookVO cashBookVO : finCashbookMainVO.getCashBookVOs()) {
					cashBookVO.setTransactionDate(DateFunctions.convertStringToDate(cashBookVO.getTransactionDateStr()));
					if (cashBookVO.getId() > 0) {
						FinancialAccountDetails accountDetails = (FinancialAccountDetails) this.get(FinancialAccountDetails.class,cashBookVO.getAccountId());
						financialCashBook = saveCashBook(cashBookVO, customer,financialYear, accountDetails);
						if (!ObjectFunctions.isNullOrEmpty(financialCashBook)) {
							int returnCode = this.saveVoucherDetailsInMontlyAccountTotal(financialCashBook.getAmount(),financialCashBook.getTransactionDate(),financialCashBook.getTransactionType(),customer, financialYear,accountDetails, 1);
							if (returnCode == 0)
								status = false;
						}
					} else {
						FinancialAccountDetails accountDetails = (FinancialAccountDetails) this.get(FinancialAccountDetails.class,cashBookVO.getAccountId());
						financialCashBook = saveCashBook(cashBookVO, customer,financialYear, accountDetails);
						if (!ObjectFunctions.isNullOrEmpty(financialCashBook)) {
							int returnCode = this.saveVoucherDetailsInMontlyAccountTotal(financialCashBook.getAmount(),financialCashBook.getTransactionDate(),financialCashBook.getTransactionType(),customer, financialYear,accountDetails, 1);
							if (returnCode == 0)
								status = false;
							else {
								webAndClientIdsMainVO.getFinWebAndClientIdsVOs().add(this.getFinWebAndClientIdsVO(financialCashBook.getId(),cashBookVO.getClientId()));
							}
						}
					}
				}
				if (status) {
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				} else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
				webAndClientIdsMainVO.setApiStatus(apistatusVO);
				return webAndClientIdsMainVO;
			}

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public FinWebAndClientIdsMainVO particaularAssociationSubmit(FinancialParticaularAssociationMainVO particaularAssociationMainVO,long custId) {
		try {
			FinWebAndClientIdsMainVO webAndClientIdsMainVO = null;
			APIStatusVO apistatusVO = null;
			boolean status = true;
			FinancialParticaularAssociation finParticaularAssociation = null;
			if (!ObjectFunctions.isNullOrEmpty(particaularAssociationMainVO)) {
				webAndClientIdsMainVO = new FinWebAndClientIdsMainVO();
				for (FinancialParticaularAssociationVO particaularAssociationVO : particaularAssociationMainVO.getFinancialParticaularAssociationVOs()) {
					if (particaularAssociationVO.getId() > 0) {
						finParticaularAssociation = addOrUpdateFinancialParticaularAssociation(custId, particaularAssociationVO.getId(),particaularAssociationVO.getParticularId(),particaularAssociationVO.getAccountId(), 1);
						if (ObjectFunctions.isNullOrEmpty(finParticaularAssociation)) {
							status = false;
						}
					} else {
						finParticaularAssociation = addOrUpdateFinancialParticaularAssociation(custId, particaularAssociationVO.getId(),particaularAssociationVO.getParticularId(),particaularAssociationVO.getAccountId(), 1);
						if (ObjectFunctions.isNullOrEmpty(finParticaularAssociation)) {
							status = false;
						} else {
							webAndClientIdsMainVO.getFinWebAndClientIdsVOs().add(this.getFinWebAndClientIdsVO(finParticaularAssociation.getId(),particaularAssociationVO.getClientId()));
						}
					}
				}
				if (status) {
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				} else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
				webAndClientIdsMainVO.setApiStatus(apistatusVO);
				return webAndClientIdsMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public Date getLastCreatedDate(long webId,String tableName){
		return accountDao.getLastCreatedDate(webId, tableName);
	}
	
	public List<ViewFinAccountDetails> getSumOfFinalReportDetails(long custId,long financialYearId,String fromDate,String endDate,String statementIds){
		return accountDao.getSumOfFinalReportDetails(custId,financialYearId,fromDate,endDate,statementIds);
	}
	
	public int getAddBankAccountDetails(CustomerBankAccountDetailsVO bankAccountDetailsVO,long custId,long userId){
		try {
			CustomerBankAccountDetails customerBankAccountDetails=null;
			Address address=null;
			if(bankAccountDetailsVO.getId()>0){
				customerBankAccountDetails=(CustomerBankAccountDetails)this.get(CustomerBankAccountDetails.class, bankAccountDetailsVO.getId());
				address = (Address)this.get(Address.class, bankAccountDetailsVO.getAddressVO().getId());
			}else{
				customerBankAccountDetails = new CustomerBankAccountDetails();
				customerBankAccountDetails.setCreatedById(userId);
				customerBankAccountDetails.setCreatedDate(new Date());
				address = new Address();
				address.setCreatedById(userId);
				address.setCreatedDate(new Date());
			}
			customerBankAccountDetails.setLastUpdatedById(userId);
			customerBankAccountDetails.setLastUpdatedDate(new Date());
			address.setLastUpdatedById(userId);
			address.setLastUpdatedDate(new Date());
			customerBankAccountDetails.setCustId(custId);
			
			customerBankAccountDetails.setBankName(bankAccountDetailsVO.getBankName());
			customerBankAccountDetails.setAccountName(bankAccountDetailsVO.getAccountName());
			customerBankAccountDetails.setAccountNumber(bankAccountDetailsVO.getAccountNumber());
			customerBankAccountDetails.setMobileNumber(bankAccountDetailsVO.getMobileNumber());
			customerBankAccountDetails.setIfscCode(bankAccountDetailsVO.getIfscCode());
			
			address.setAddressLine1(bankAccountDetailsVO.getAddressVO().getAddressLine1());
			address.setCity(bankAccountDetailsVO.getAddressVO().getCity());
			address.setCountry(bankAccountDetailsVO.getAddressVO().getCountry());
			address.setState(bankAccountDetailsVO.getAddressVO().getState());
			address.setPostalCode(bankAccountDetailsVO.getAddressVO().getPostalCode());
			address.setEmail(bankAccountDetailsVO.getAddressVO().getEmail());
			customerBankAccountDetails.setAddress(address);
			this.save(customerBankAccountDetails);
			return 1;
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 0;
		}
	}
	
	public CustomerBankAccountDetailsVO getCustomerBankAccountDetailsVo(long custId){
		try {
			CustomerBankAccountDetailsVO bankAccountDetailsVO=null;
			AddressVO addressVO=null;
			CustomerBankAccountDetails customerBankAccountDetails = (CustomerBankAccountDetails)this.get(CustomerBankAccountDetails.class, "custId="+custId);
			if(!ObjectFunctions.isNullOrEmpty(customerBankAccountDetails)){
				bankAccountDetailsVO = new CustomerBankAccountDetailsVO();
				bankAccountDetailsVO.setId(customerBankAccountDetails.getId());
				bankAccountDetailsVO.setBankName(customerBankAccountDetails.getBankName());
				bankAccountDetailsVO.setAccountName(customerBankAccountDetails.getAccountName());
				bankAccountDetailsVO.setAccountNumber(customerBankAccountDetails.getAccountNumber());
				bankAccountDetailsVO.setIfscCode(customerBankAccountDetails.getIfscCode());
				bankAccountDetailsVO.setMobileNumber(customerBankAccountDetails.getMobileNumber());
				addressVO =new AddressVO();
				addressVO.setId(customerBankAccountDetails.getAddress().getId());
				addressVO.setAddressLine1(customerBankAccountDetails.getAddress().getAddressLine1());
				addressVO.setCity(customerBankAccountDetails.getAddress().getCity());
				addressVO.setPostalCode(customerBankAccountDetails.getAddress().getPostalCode());
				addressVO.setCountry(customerBankAccountDetails.getAddress().getCountry());
				addressVO.setState(customerBankAccountDetails.getAddress().getState());
				addressVO.setEmail(customerBankAccountDetails.getAddress().getEmail());
				bankAccountDetailsVO.setAddressVO(addressVO);
				return bankAccountDetailsVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public List<FineFee> getOtherFeePaymentDetails(long custId,long academicYearId,long studentId){
		return accountDao.getOtherFeePaymentDetails(custId,academicYearId,studentId);
	}
	
	public int deleteInvoicePaymentByMasterAdmin(DeleteStudentPaymentVo deleteStudentPaymentVo,long userId,String deleteType,long academicYearId,long studentId){
		try {
			if(deleteStudentPaymentVo.getCustId()>0 && deleteStudentPaymentVo.getStudentPaymentId()>0){
				if("S".equalsIgnoreCase(deleteType)){
					StudentPayment studentPayment = (StudentPayment)this.get(StudentPayment.class, deleteStudentPaymentVo.getStudentPaymentId());
					if(!ObjectFunctions.isNullOrEmpty(studentPayment)){
						studentPayment.setLastUpdatedDate(new Date());
						studentPayment.setLastAccessDate(new Date());
						studentPayment.setLastUpdatedById(userId);
						studentPayment.setLastUpdatedById(userId);
						studentPayment.setDeleteStatus(Constants.YES_STRING);
						this.updateQuery("UPDATE studentFeePaidDetails AS s, (select IFNULL(feeId,studTransportDetailsId) as feeId from studentFeePaidDetails where studentPaymentId="+studentPayment.getId()+") AS p SET s.paymentStatus='N' WHERE (s.feeId = p.feeId OR s.studTransportDetailsId=p.feeId) AND s.custId="+studentPayment.getCustId()+" and s.studentId="+studentPayment.getStudent().getId()+" and s.studentPaymentId<>"+studentPayment.getId());
						this.updateQuery("update studentFeePaidDetails set deleteStatus='Y' where studentPaymentId ="+deleteStudentPaymentVo.getStudentPaymentId());
						//List<StudentPayment> paymentList = this.getAll(StudentPayment.class, "custId="+deleteStudentPaymentVo.getCustId()+" and academicYearId="+studentPayment.getAcademicYear().getId()+" and studentId="+studentPayment.getStudent().getId()+" and id not in ("+deleteStudentPaymentVo.getStudentPaymentId()+") and deleteStatus='N'");
						int  paymentCount = this.getCount("studentPayment", "custId="+deleteStudentPaymentVo.getCustId()+" and academicYearId="+studentPayment.getAcademicYear().getId()+" and studentId="+studentPayment.getStudent().getId()+" and id not in ("+deleteStudentPaymentVo.getStudentPaymentId()+") and deleteStatus='N' and concessionStatus='N' ");
						if(paymentCount>0)
							accountDao.updateStudentFeePaidStatus(studentPayment.getStudent().getId(),"P");
						else{
							int fineFeeCount = this.getCount("fineFee", "custId="+deleteStudentPaymentVo.getCustId()+" and academicYearId="+academicYearId+" and studentId="+studentId+" and deleteStatus='N'");
							if(fineFeeCount==0)
								accountDao.updateStudentFeePaidStatus(studentPayment.getStudent().getId(),"N");
						}
						//paymentList=null;
						deleteStudentPayment(deleteStudentPaymentVo, studentPayment, userId, deleteType, null);
						this.save(studentPayment);
						return 1;
					}
				}else{
					List<FineFee> otherFeeList=this.getAll(FineFee.class, "custId="+deleteStudentPaymentVo.getCustId()+" and academicYearId="+academicYearId+" and studentId="+studentId+" and invoiceNumber='"+deleteStudentPaymentVo.getStudentPaymentId()+"' ");
					if(!ObjectFunctions.isNullOrEmpty(otherFeeList)){
						for(FineFee otherFee : otherFeeList){
							otherFee.setDeleteStatus("Y");
							deleteStudentPayment(deleteStudentPaymentVo, null, userId, deleteType, otherFee);
							this.save(otherFee);
						}
					}
					/*@Ganesh In scenario student did not pay any student payment but they did in Other Fee and removed from admin login in this scenario we are not changing fee paid status that't first we need to check
					 *  first student payment count if available we will not remove if count is coming 0 we will check in Other Fee. In other fee also if don't have any payment details we will change feePaidStatus as "N" */
					int  paymentCount = this.getCount("studentPayment", "custId="+deleteStudentPaymentVo.getCustId()+" and academicYearId="+academicYearId+" and studentId="+studentId+" and deleteStatus='N' and concessionStatus='N' ");
					if(paymentCount==0){
						int fineFeeCount = this.getCount("fineFee", "custId="+deleteStudentPaymentVo.getCustId()+" and academicYearId="+academicYearId+" and studentId="+studentId+" and deleteStatus='N'");
						if(fineFeeCount==0)
							accountDao.updateStudentFeePaidStatus(studentId,"N");
					}
					return 1;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 0;
		}
		return 0;
	}
	public void deleteStudentPayment(DeleteStudentPaymentVo deleteStudentPaymentVo,StudentPayment studentPayment,long userId,String deleteType,FineFee fineFee){
		try {
			DeleteStudentPayment deleteStudentPayment=new DeleteStudentPayment();
			deleteStudentPayment.setCreatedById(userId);
			deleteStudentPayment.setCreatedDate(new Date());
			deleteStudentPayment.setCustId(deleteStudentPaymentVo.getCustId());
			if("S".equalsIgnoreCase(deleteType))
				deleteStudentPayment.setStudentPayment(studentPayment);
			else
				deleteStudentPayment.setOtherFee(fineFee);
			deleteStudentPayment.setDeleteRemarks(deleteStudentPaymentVo.getDeleteRemarks());
			deleteStudentPayment.setReportedPerson(deleteStudentPaymentVo.getReportedPerson());
			deleteStudentPayment.setSupportPersonName(deleteStudentPaymentVo.getSupportPersonName());
			this.save(deleteStudentPayment);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	public List<ViewStudentFeePaymentDetails> getPreviousYearFeeDefaultersList(long custId,long academicYearId,String toDayDate){
		return accountDao.getPreviousYearFeeDefaultersList(custId,academicYearId,toDayDate);
	}
	
	public Object[] getOverAllFeePaymentSummary(long custId,long academicYearId){
		return accountDao.getOverAllFeePaymentSummary(custId,academicYearId);
	}
	
	public List<ViewStudentFeePaymentDetails> getClassWiseConsolidatedStudentFeeDetails(long custId,long academicYearId,String classSectionIds,String status){
		return accountDao.getClassWiseConsolidatedStudentFeeDetails(custId,academicYearId,classSectionIds,status);
	}
}

