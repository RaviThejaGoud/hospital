package com.urt.webapp.action.account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.xls.ExcelView;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.hyniva.sms.ws.vo.account.FinancialAccountCategoryVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountStatementVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountTotalsVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountTypeVO;
import com.hyniva.sms.ws.vo.account.FinancialCustomerDetailsVO;
import com.hyniva.sms.ws.vo.account.FinancialYearVO;
import com.hyniva.sms.ws.vo.cashbook.CashBookVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.account.FinancialAccountCategory;
import com.urt.persistence.model.account.FinancialAccountDetails;
import com.urt.persistence.model.account.FinancialAccountStatement;
import com.urt.persistence.model.account.FinancialAccountTotals;
import com.urt.persistence.model.account.FinancialAccountType;
import com.urt.persistence.model.account.FinancialCashBook;
import com.urt.persistence.model.account.FinancialCustomerDetails;
import com.urt.persistence.model.account.ViewFinAccountDetails;
import com.urt.persistence.model.account.ViewPartucularToAccount;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.secretary.FinancialYear;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;

@Namespace("/account")
public class AccountAction extends BaseAction {

	protected FinancialAccountTypeVO financialAccountTypeVO;
	protected FinancialAccountCategoryVO financialAccountCategoryVO;
	protected FinancialAccountStatementVO financialAccountStatementVO;
	protected FinancialAccountDetailsVO financialAccountDetailsVO;
	protected FinancialCustomerDetailsVO financialCustomerDetailsVO;
	protected FinancialAccountTotalsVO financialAccountTotalsVO;
	protected FinancialAccountType financialAccountType;
	protected FinancialAccountCategory financialAccountCategory;
	protected FinancialAccountStatement financialAccountStatement;
	protected FinancialAccountDetails financialAccountDetails;
	protected FinancialCustomerDetails financialCustomerDetails;
	protected List<FinancialAccountDetails> financialAccountDetailsList;
	protected List<FinancialCashBook> financialCashBookList;
	protected CashBookVO cashBookVo;

	/**
	 * @return the financialCashBookList
	 */
	public List<FinancialCashBook> getFinancialCashBookList() {
		if (ObjectFunctions.isNullOrEmpty(this.financialCashBookList)) {
			this.financialCashBookList = new ArrayList<FinancialCashBook>();
		}
		return financialCashBookList;
	}

	/**
	 * @param financialCashBookList the financialCashBookList to set
	 */
	public void setFinancialCashBookList(List<FinancialCashBook> financialCashBookList) {
		this.financialCashBookList = financialCashBookList;
	}

	/**
	 * @return the cashBookVo
	 */
	public CashBookVO getCashBookVo() {
		return cashBookVo;
	}

	/**
	 * @param cashBookVo the cashBookVo to set
	 */
	public void setCashBookVo(CashBookVO cashBookVo) {
		this.cashBookVo = cashBookVo;
	}

	public List<FinancialAccountDetails> getFinancialAccountDetailsList() {
		return financialAccountDetailsList;
	}

	public void setFinancialAccountDetailsList(List<FinancialAccountDetails> financialAccountDetailsList) {
		this.financialAccountDetailsList = financialAccountDetailsList;
	}

	public FinancialCustomerDetailsVO getFinancialCustomerDetailsVO() {
		return financialCustomerDetailsVO;
	}

	public void setFinancialCustomerDetailsVO(FinancialCustomerDetailsVO financialCustomerDetailsVO) {
		this.financialCustomerDetailsVO = financialCustomerDetailsVO;
	}

	public FinancialCustomerDetails getFinancialCustomerDetails() {
		return financialCustomerDetails;
	}

	public void setFinancialCustomerDetails(FinancialCustomerDetails financialCustomerDetails) {
		this.financialCustomerDetails = financialCustomerDetails;
	}

	public FinancialAccountDetailsVO getFinancialAccountDetailsVO() {
		return financialAccountDetailsVO;
	}

	public void setFinancialAccountDetailsVO(
			FinancialAccountDetailsVO financialAccountDetailsVO) {
		this.financialAccountDetailsVO = financialAccountDetailsVO;
	}

	public FinancialAccountDetails getFinancialAccountDetails() {
		return financialAccountDetails;
	}

	public void setFinancialAccountDetails(
			FinancialAccountDetails financialAccountDetails) {
		this.financialAccountDetails = financialAccountDetails;
	}

	public FinancialAccountType getFinancialAccountType() {
		return financialAccountType;
	}

	public void setFinancialAccountType(FinancialAccountType financialAccountType) {
		this.financialAccountType = financialAccountType;
	}

	public FinancialAccountCategory getFinancialAccountCategory() {
		return financialAccountCategory;
	}

	public void setFinancialAccountCategory(
			FinancialAccountCategory financialAccountCategory) {
		this.financialAccountCategory = financialAccountCategory;
	}

	public FinancialAccountStatement getFinancialAccountStatement() {
		return financialAccountStatement;
	}

	public void setFinancialAccountStatement(FinancialAccountStatement financialAccountStatement) {
		this.financialAccountStatement = financialAccountStatement;
	}

	public FinancialAccountCategoryVO getFinancialAccountCategoryVO() {
		return financialAccountCategoryVO;
	}

	public void setFinancialAccountCategoryVO(
			FinancialAccountCategoryVO financialAccountCategoryVO) {
		this.financialAccountCategoryVO = financialAccountCategoryVO;
	}

	public FinancialAccountTypeVO getFinancialAccountTypeVO() {
		return financialAccountTypeVO;
	}

	public void setFinancialAccountTypeVO( FinancialAccountTypeVO financialAccountTypeVO) {
		this.financialAccountTypeVO = financialAccountTypeVO;
	}

	public FinancialAccountStatementVO getFinancialAccountStatementVO() {
		return financialAccountStatementVO;
	}

	public void setFinancialAccountStatementVO( FinancialAccountStatementVO financialAccountStatementVO) {
		this.financialAccountStatementVO = financialAccountStatementVO;
	}

	public FinancialAccountTotalsVO getFinancialAccountTotalsVO() {
		return financialAccountTotalsVO;
	}

	public void setFinancialAccountTotalsVO(FinancialAccountTotalsVO financialAccountTotalsVO) {
		this.financialAccountTotalsVO = financialAccountTotalsVO;
	}

	@Override
	public String getAutoCheck() {
		return super.autoCheck;
		}
	
	@Actions({ @Action(value = "ajaxAccountAction", results = {}) })
	public void ajaxAccountAction() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAccountAction' method");
		}
		try {

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Actions( {
		@Action(value = "ajaxDoAddCreateAccountMaster", results = { @Result(location = "ajaxAddNewAccountMaster.jsp", name = "success") }),
		@Action(value = "ajaxDoEditFinAccountDetails", results = { @Result(location = "ajaxAddNewAccountMaster.jsp", name = "success") }) })
		public String ajaxDoAddCreateAccountMaster() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddCreateAccountMaster' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getFinancialAccountDetailsVO())){
					if(getFinancialAccountDetailsVO().getId()>0){
						setTempList2(accountManager.getAllHqlQuery("SELECT cashBook FROM FinancialCashBook cashBook WHERE cashBook.customer="+getUserCustId()+" and cashBook.financialAccountDetails="+getFinancialAccountDetailsVO().getId()));
						setFinancialAccountDetailsVO(accountManager.getFinancialAccountDetails(getFinancialAccountDetailsVO().getId()));
						setFinancialAccountTotalsVO(accountManager.getFinancialAccountTotalsVO(getFinancialAccountDetailsVO().getId(),getFinancialAccountDetailsVO().getFinancialYearVO().getId()));
					}
				}
				setObjectList(accountManager.getAll(FinancialAccountType.class));
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				setCountryList((List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST));
				setTempList1(accountManager.getAll(FinancialAccountCategory.class,"custId="+getUserCustId()));
				setFinancialYearList(accountManager.getAll(FinancialYear.class, "status='Y'"));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	
	@Actions( {
		@Action(value = "ajaxDoAddCategories", results = { @Result(location = "ajaxAddAccountCategory.jsp", name = "success") }) })
		public String ajaxDoAddCategories() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddCategories' method");
			}
			try {
				setObjectList(null);
				setObjectList(accountManager.getAll(FinancialAccountType.class));
				setTempList(accountManager.getAll(FinancialAccountStatement.class));
				setTempList1(accountManager.getAll(FinancialAccountCategory.class,"custId="+getUserCustId()));
				if(!ObjectFunctions.isNullOrEmpty(getFinancialAccountCategoryVO())){
					/*Ganesh we are given option to edit category account types up to when they did not enter any entry to voucher. When then given antry to any voucher we are disabling edit option.*/
					setTempList2(accountManager.getAllHqlQuery("SELECT cashBook FROM FinancialCashBook cashBook WHERE cashBook.customer="+getUserCustId()+" and cashBook.financialAccountDetails.financialAccountCategory="+getFinancialAccountCategoryVO().getId()));
					FinancialAccountCategory fac = (FinancialAccountCategory)accountManager.get(FinancialAccountCategory.class, "id="+getFinancialAccountCategoryVO().getId());
					if(!ObjectFunctions.isNullOrEmpty(fac)){
						setFinancialAccountCategoryVO(fac.copyFromEntityToVo(fac));
					}
				}/*else
					setTempList(null);*/
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	@Actions( {
		@Action(value = "ajaxViewCashBookDetails", results = { @Result(location = "ajaxViewCashBook.jsp", name = "success") }) })
		public String ajaxViewCashBookDetails() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewCashBookDetails' method");
			}
			try {
				setFinancialCashBookList(adminManager.getAll(FinancialCashBook.class, "custId="+ getUserCustId()+" and bookType='C' order by transactionDate DESC"));
				setObjectList(adminManager.getAll(FinancialCashBook.class, "custId="+ getUserCustId()+" and bookType='B' order by transactionDate DESC"));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	
	@Actions( {
		@Action(value = "ajaxDoAddCashBook", results = { @Result(location = "ajaxDoCashBook.jsp", name = "success") }) })
		public String ajaxDoAddCashBook() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddCashBook' method");
			}
			try {
				loadAcademicYearStartDateAndDates(getUserAcademicYearId());
				setObjectList((List<BankMaster>) (SMSLookUpDataCache.lookUpDataMap.get(Constants.BANK_LIST)));
				setFinancialAccountDetailsList(accountManager.getAll(FinancialAccountDetails.class, "custId="+getUserCustId()));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	
	@Actions( {
		@Action(value = "ajaxAddNewCategory", results = { @Result(location = "ajaxAddAccountCategory.jsp", name = "success") }) })
		public String ajaxAddNewCategory() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddNewCategory' method");
			}
			try {
				accountManager.addOrUpdateAccountCategory(getUserCustId(),getFinancialAccountCategoryVO(),getUser().getId());
				
				if(getFinancialAccountCategoryVO().getId() > 0)
					super.addActionMessage("Account category updated sucessfully.");
				else
					super.addActionMessage("Account category added sucessfully.");
				/*if(returnCode==1)
					super.addActionMessage("Account category updated sucessfully.");
				else if (returnCode==2) {
					super.addActionMessage("Account category added sucessfully.");
				}*/
				setFinancialAccountCategoryVO(null);
				 ajaxDoAddCategories();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	@Actions( {
		@Action(value = "ajaxRemoveCategory", results = { @Result(location = "ajaxAddAccountCategory.jsp", name = "success") }) })
		public String ajaxRemoveCategory() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveCategory' method");
			}
			try {
				//Need to implement code
				ajaxDoAddCategories();
				super.addActionMessage("Account category removed sucessfully.");
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	@Actions( {
		@Action(value = "ajaxGetAccountTypeByaccountId", results = { @Result(location = "ajaxAccountCategoriesByAccountList.jsp", name = "success") }) })
		public String ajaxGetAccountTypeByaccountId() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetAccountTypeByaccountId' method");
			}
			try {
				StringBuilder query = new StringBuilder("custId="+getUserCustId());
				if(getBankId()==1)
					query.append(" and accountTypeId=1");
				else
					query.append(" and accountTypeId=2");
				setTempList(accountManager.getAll(FinancialAccountCategory.class,query.toString()));
				if(StringFunctions.isNotNullOrEmpty(getAnyId())){
					if(Long.valueOf(getAnyId())>0){
						FinancialAccountCategoryVO accountCategoryVO=new FinancialAccountCategoryVO();
						accountCategoryVO.setId(Long.valueOf(getAnyId()));
						setFinancialAccountCategoryVO(accountCategoryVO);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}

	@Actions({ @Action(value = "ajaxDoFeeParticularToAccount", results = { @Result(location = "ajaxDoFeeParticularToAccount.jsp", name = "success") }) })
	public String ajaxDoFeeParticular() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoFeeParticularToAccount' method");
		}
		try {
			//setFinancialYearList(accountManager.getAll(FinancialYear.class,"status='Y'"));
			setFinancialAccountDetailsList(accountManager.getAll(FinancialAccountDetails.class, "custId="+getUserCustId()+" and accountTypeId="+1));
			//setFeeTypeList(accountManager.getAll(FeeType.class, "custId="+getUserCustId()+" and academicYEarId="+getUserAcademicYearId()));
			setObjectList(accountManager.getAll(ViewPartucularToAccount.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
			//prepareSchoolFeeSettingList();

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions( {
		@Action(value = "ajaxViewCreateAccountMaster", results = { @Result(location = "ajaxViewMasterAccounts.jsp", name = "success") }) })
		public String ajaxViewCreateAccountMaster() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewCreateAccountMaster' method");
			}
			try {
					setFinancialYearList(accountManager.getAll(FinancialYear.class));
					if(!ObjectFunctions.isNullOrEmpty(getFinancialYearList()))
					{
						FinancialYearVO financialYearVO = new FinancialYearVO();
						for( FinancialYear financialYear :getFinancialYearList()){
							if(!ObjectFunctions.isNullOrEmpty(financialYear)){
								if("Y".equalsIgnoreCase(financialYear.getStatus())){
									financialYearVO.setId(financialYear.getId());
									setFinancialYearVO(financialYearVO);
								}
							}
						}
					}
				setFinancialAccountDetailsList(accountManager.getAll(FinancialAccountDetails.class,"custId="+getUserCustId()));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	
	@Actions( {
		@Action(value = "ajaxGetFinYearAccountDetails", results = { @Result(location = "ajaxViewFinYearWiseAccountsDetails.jsp", name = "success") }) })
		public String ajaxGetFinYearAccountDetails() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetFinYearAccountDetails' method");
			}
			try {
				setObjectList(accountManager.getAll(ViewFinAccountDetails.class, "custId="+getUserCustId()+" and financialYearId <="+getFinancialYearVO().getId()));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
	@Actions({ @Action(value = "ajaxAddFinancialAccountDetails", results = { @Result(location = "ajaxViewMasterAccountsDetails.jsp", name = "success") }) })
	public String ajaxAddAccountMaster() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddAccountMaster' method");
		}
		try {
			//getFinancialYearVO().setId(getFinancialAccountDetailsVO().getFinancialYearVO().getId());
			int returnCode = accountManager.createOrUpdateFinancialAccountDetails(getCustomerByCustId(),getFinancialAccountDetailsVO(),getFinancialAccountCategoryVO(),getFinancialAccountTotalsVO(),getUser().getId());
			if (returnCode == 1)
				super.addActionMessage("Account master updated sucessfully.");
			else if (returnCode == 2) {
				super.addActionMessage("Account master added sucessfully.");
			} else if (returnCode == 0) {
				super.addActionError("There was a problem while saving data. Please contact EazySchool support team.");
			}
			ajaxViewCreateAccountMaster();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxAddFeeParticularToAccount", results = { @Result(location = "ajaxDoFeeParticularToAccount.jsp", name = "success") }) })
	public String ajaxAddFeeParticularToAccount() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddFeeParticularToAccount' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getTempString())) {
				long returnCode=accountManager.addParticularAssociationToAccount(getTempString(),getCurrentAcademicYear(), getUser().getId(),getUserCustId());
			if (returnCode == 1)
				super.addActionMessage("Particular association to account created sucessfully.");
			else if(returnCode == 2)
				super.addActionError("Particular OR Account details are not available. Please check once.");
			else if(returnCode == 0)
				super.addActionError("Particular association to account not created properlly.");
			}
			ajaxDoFeeParticular();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxSaveCashBook", results = { @Result(location = "ajaxViewCashBook.jsp", name = "success") }) })
	public String ajaxSaveCashBook() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveCashBook' method");
		}
		try {
			Customer customer = getCustomerByCustId();
			FinancialYear financialYear = (FinancialYear) accountManager.get(FinancialYear.class, "status='Y'");
			FinancialAccountDetails accountDetails = (FinancialAccountDetails) accountManager.get(FinancialAccountDetails.class, getCashBookVo().getAccountId());
			FinancialCashBook financialCashBook =null;
			if (!ObjectFunctions.isNullOrEmpty(customer) && !ObjectFunctions.isNullOrEmpty(financialYear) && !ObjectFunctions.isNullOrEmpty(accountDetails)) {
				financialCashBook = accountManager.saveCashBook(getCashBookVo(), customer,financialYear, accountDetails);
				if(!ObjectFunctions.isNullOrEmpty(financialCashBook)){
					int returnCode = accountManager.saveVoucherDetailsInMontlyAccountTotal(getCashBookVo().getAmount(),getCashBookVo().getTransactionDate(),getCashBookVo().getTransactionType(),customer,financialYear,accountDetails,getUser().getId());
					if(returnCode ==0)
						super.addActionError("There was a problem while saving data. Please contact EazySchool support team.");
				}
			}
			super.addActionMessage("Voucher generated successfully.");
			ajaxViewCashBookDetails();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxPrintDayBooksReport", results = { @Result(location = "jasper/daybook/cashBookVocher.jasper", type = "jasper", name = "success", params = {"dataSource", "objectList", "format", "PDF" }),
																	   @Result(location = "jasper/daybook/dayBookVocherBank.jasper", type = "jasper", name = "SUCCESSDD", params = {"dataSource", "objectList", "format", "PDF" }) }) }) 
	public String ajaxPrintDayBooksReport() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxPrintDayBooksReport' method");
	}
	try {
		String pdf = "pdf";
		setObjectList(adminManager.getAll(FinancialCashBook.class,"custId="+getUserCustId()+" and id="+getTempId()));
		String amountWords=null;
		if(!StringFunctions.isNullOrEmpty(getTempString())){
			amountWords=getTempString();
		}
		if(!StringFunctions.isNullOrEmpty(getPlTitle())){
			  amountWords=getPlTitle();
		}
		FinancialCashBook  cashBook = (FinancialCashBook)getObjectList().get(0);
		String chequeOrDDOrTransactionNumber =null;
		String labelName=null;
		String branchName = null;
		String bankName = null;
		/*C - Cash, D - DD , CH - Cheque, BD - Bank Deposit*/
		if("DD".equalsIgnoreCase(cashBook.getPaymentType())){
			chequeOrDDOrTransactionNumber=cashBook.getDdNumber();
			labelName="DD Number";
			branchName=cashBook.getBranchName();
			bankName=cashBook.getBankMaster().getBankName();
		}else if ("CH".equalsIgnoreCase(cashBook.getPaymentType())) {
			chequeOrDDOrTransactionNumber=cashBook.getChequeNumber();
			labelName="Cheque Number";		
			branchName=cashBook.getBranchName();
			bankName=cashBook.getBankMaster().getBankName();
		}else if ("BD".equalsIgnoreCase(cashBook.getPaymentType())) {
			chequeOrDDOrTransactionNumber=cashBook.getAccountNumber();
			labelName="Account Number";
			branchName=cashBook.getBranchName();
			bankName=cashBook.getBankMaster().getBankName();
		}else if ("CS".equalsIgnoreCase(cashBook.getPaymentType()) || "NEFT".equalsIgnoreCase(cashBook.getPaymentType())){
			chequeOrDDOrTransactionNumber=cashBook.getTransactionNumber();
			labelName="Transaction NUmber";
		}
		setTempString1(chequeOrDDOrTransactionNumber);
		setTempString2(labelName);
		setTempString3(bankName);
		setAnyTitle(branchName);
		setPlTitle(amountWords);
		setCustomer((Customer) adminManager.get(Customer.class, getUserCustId()));
		if (!ObjectFunctions.isNullOrEmpty(getCustomer())) {
			setCustomerName(getCustomer().getOrganization());
			setAnyId(getCustomer().getOrganizationFullAddress());
			setWebSiteUrl(getSession().getServletContext().getRealPath(getCustomer().getCustomerLogoPath()));
			log.debug(getWebSiteUrl());
		}
		if ("pdf".equalsIgnoreCase(pdf)) {
			getResponse().setHeader("Content-Disposition", "attachment; filename=feesReceipt" +StringFunctions.getReplaceAll(DateFormatter.getTodayDateStr(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN)," ", "-") + ".pdf");
		}
		if(!"C".equalsIgnoreCase(cashBook.getPaymentType()))
			return "SUCCESSDD";
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
	}
	
	 /* Reports */
	
	
	@Actions({ @Action(value = "ajaxDownloadAllAccountCategory", results = { @Result(location = "reports/ajaxViewMasterAccountReports.jsp", name = "success") }) })
	public String ajaxDownloadAllAccountCategory() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadAllAccountCategory' method");
		}
		try {

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxDownloadAccountReportCategory", results = { }) })
	public String ajaxDownloadAccountReportCategory() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadAccountReportCategory' method");
		}
		try {
			log.debug("account category type :"+getTempString());
			if("A".equalsIgnoreCase(getTempString())){
					prepareAllAccountCategoryDetails();
			}else if ("AC".equalsIgnoreCase(getTempString())) {
				prepareAllAccountCategoryWise();
			}else if ("AS".equalsIgnoreCase(getTempString())) {
				prepareAllAccountStatementWise();
			}else if("AV".equalsIgnoreCase(getTempString())){
				prepareAllVendorAccounts();
			}else if("VC".equalsIgnoreCase(getTempString())){
				prepareAllVendorsAccountCategoryWise();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public void prepareAllAccountCategoryDetails() {
		try {
			List<FinancialAccountDetails> accountDetailsList = null;
			StringBuilder query =new StringBuilder("custId="+getUserCustId());
			ExcelView excelView = prepareAccountsReportHeader("AllAccountCategory");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			if("A".equalsIgnoreCase(getTempString())){
				query.append( " and accountTypeId=1");
			}
			accountDetailsList = adminManager.getAll(FinancialAccountDetails.class, query.toString());
			int rowCount = 8;
			int studentCount = 1;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				prepareSheetSettings(excelView,"Account Details",cellFormat,getTempString());
				for (FinancialAccountDetails accountDetails : accountDetailsList) {
					prepareAccountDetails(excelView,accountDetails,getTempString(),studentCount,rowCount,cellFormat);
					if(!ObjectFunctions.isNullOrEmpty(accountDetails.getFinancialAccountCategory())){
						excelView.getWs().addCell(new Label(2, rowCount, accountDetails.getFinancialAccountCategory().getCartegoryName(), excelView.getDefaultFormat()));
						if(!ObjectFunctions.isNullOrEmpty(accountDetails.getFinancialAccountCategory().getFinancialAccountStatement()))
							excelView.getWs().addCell(new Label(3, rowCount, accountDetails.getFinancialAccountCategory().getFinancialAccountStatement().getStatementName(), excelView.getDefaultFormat()));
						else
							excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getDefaultFormat()));
					}else{
						excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getDefaultFormat()));
					}
					studentCount += 1;
					rowCount += 1;
				}
				rowCount += 1;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
			} else {
				prepareSheetSettings(excelView,"Account Details",cellFormat,getTempString());
				excelView.getWs().mergeCells(0, rowCount, 18, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount, "No account details available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	public void prepareAllAccountCategoryWise() {
		try {
			List<FinancialAccountDetails> accountDetailsList = null;
			ExcelView excelView = prepareAccountsReportHeader("AllAccountCategoryWise");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			accountDetailsList = adminManager.getAll(FinancialAccountDetails.class, "custId="+getUserCustId()+" and accountTypeId=1 order by finAccountCategoryId");
			int rowCount = 8;
			int studentCount = 1;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				prepareSheetSettings(excelView,"Category Wise Account Details",cellFormat,getTempString());
				long finAccountCategoryId=0;
				for (FinancialAccountDetails accountDetails : accountDetailsList) {
					if(finAccountCategoryId != accountDetails.getFinancialAccountCategory().getId()){
						excelView.getWs().mergeCells(0, rowCount,2,rowCount);
						excelView.getWs().addCell(new Label(0, rowCount, accountDetails.getFinancialAccountCategory().getCartegoryName(), cellFormat));
						rowCount+=1;
					}
					prepareAccountDetails(excelView,accountDetails,getTempString(),studentCount,rowCount,cellFormat);
					if(!ObjectFunctions.isNullOrEmpty(accountDetails.getFinancialAccountCategory().getFinancialAccountStatement()))
						excelView.getWs().addCell(new Label(2, rowCount, accountDetails.getFinancialAccountCategory().getFinancialAccountStatement().getStatementName(), excelView.getDefaultFormat()));
					else
						excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormat()));
					
					studentCount += 1;
					rowCount += 1;
					finAccountCategoryId=accountDetails.getFinancialAccountCategory().getId();
				}
				rowCount += 1;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 2);
				accountDetailsList=null;
			} else {
				prepareSheetSettings(excelView,"Category Wise Account Details",cellFormat,getTempString());
				excelView.getWs().mergeCells(0, rowCount, 18, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount, "No account details available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareAllAccountStatementWise() {
		try {
			List<FinancialAccountDetails> accountDetailsList = null;
			ExcelView excelView = prepareAccountsReportHeader("AllAccountStatementWise");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			accountDetailsList = accountManager.getAllHqlQuery("SELECT account FROM FinancialAccountDetails account WHERE account.customer="+getUserCustId()+" and account.financialAccountType=1 ORDER BY account.financialAccountCategory.financialAccountStatement.id");
			int rowCount = 8;
			int studentCount = 1;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				prepareSheetSettings(excelView,"Statement Wise Account Details",cellFormat,getTempString());
				long finAccountstatementId=0;
				for (FinancialAccountDetails accountDetails : accountDetailsList) {
					if(finAccountstatementId != accountDetails.getFinancialAccountCategory().getFinancialAccountStatement().getId()){
						excelView.getWs().mergeCells(0, rowCount,2,rowCount);
						excelView.getWs().addCell(new Label(0, rowCount, accountDetails.getFinancialAccountCategory().getFinancialAccountStatement().getStatementName(), cellFormat));
						rowCount+=1;
					}
					prepareAccountDetails(excelView,accountDetails,getTempString(),studentCount,rowCount,cellFormat);
					if(!ObjectFunctions.isNullOrEmpty(accountDetails.getFinancialAccountCategory()))
						excelView.getWs().addCell(new Label(2, rowCount, accountDetails.getFinancialAccountCategory().getCartegoryName(), excelView.getDefaultFormat()));
					else
						excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormat()));
					
					studentCount += 1;
					rowCount += 1;
					finAccountstatementId=accountDetails.getFinancialAccountCategory().getFinancialAccountStatement().getId();
				}
				rowCount += 1;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 2);
				accountDetailsList=null;
			} else {
				prepareSheetSettings(excelView,"Statement Wise Account Details",cellFormat,getTempString());
				excelView.getWs().mergeCells(0, rowCount, 18, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount, "No account details available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareAllVendorAccounts() {
		try {
			List<FinancialAccountDetails> accountDetailsList = null;
			ExcelView excelView = prepareAccountsReportHeader("AllVendorAccounts");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			accountDetailsList = adminManager.getAll(FinancialAccountDetails.class, "custId="+getUserCustId()+" and accountTypeId=2");
			int rowCount = 8;
			int studentCount = 1;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				prepareSheetSettings(excelView,"Vendor Account Details",cellFormat,getTempString());
				for (FinancialAccountDetails accountDetails : accountDetailsList) {
					prepareAccountDetails(excelView,accountDetails,getTempString(),studentCount,rowCount,cellFormat);
					excelView.getWs().addCell(new Label(2, rowCount, accountDetails.getFinancialAccountCategory().getCartegoryName(), excelView.getDefaultFormat()));
					studentCount += 1;
					rowCount += 1;
				}
				rowCount += 1;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 2);
			} else {
				prepareSheetSettings(excelView,"Vendor Account Details",cellFormat,getTempString());
				excelView.getWs().mergeCells(0, rowCount, 2, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount, "No account details available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareAllVendorsAccountCategoryWise() {
		try {
			List<FinancialAccountDetails> accountDetailsList = null;
			ExcelView excelView = prepareAccountsReportHeader("AllVendorsAccountCategoryWise");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			accountDetailsList = adminManager.getAll(FinancialAccountDetails.class, "custId="+getUserCustId()+" and accountTypeId=2 order by finAccountCategoryId");
			int rowCount = 8;
			int studentCount = 1;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				prepareSheetSettings(excelView,"Category Wise Vendor Account Details",cellFormat,getTempString());
				long finAccountCategoryId=0;
				for (FinancialAccountDetails accountDetails : accountDetailsList) {
					if(finAccountCategoryId != accountDetails.getFinancialAccountCategory().getId()){
						excelView.getWs().mergeCells(0, rowCount,1,rowCount);
						excelView.getWs().addCell(new Label(0, rowCount, accountDetails.getFinancialAccountCategory().getCartegoryName(), cellFormat));
						rowCount+=1;
					}
					prepareAccountDetails(excelView,accountDetails,getTempString(),studentCount,rowCount,cellFormat);
					studentCount += 1;
					rowCount += 1;
					finAccountCategoryId=accountDetails.getFinancialAccountCategory().getId();
				}
				rowCount += 1;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 1);
				accountDetailsList=null;
			} else {
				prepareSheetSettings(excelView,"Category Wise Vendor Account Details",cellFormat,getTempString());
				excelView.getWs().mergeCells(0, rowCount, 1, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount, "No account details available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public ExcelView prepareAccountsReportHeader(String fileName){
		ExcelView excelView = new ExcelView();
		try {
			fileName = fileName+"_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
			getResponse().setContentType(excelView.getMimeType());
			getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
			excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return excelView;
	}
	public WritableCellFormat prepareAccountsReportHeaderCellFormate(){
		WritableCellFormat cellFormat = null;
		try {
			WritableFont font = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
			font1.setColour(Colour.WHITE);
			cellFormat = ExcelView.getUserFormattedCell(font1, Colour.GREEN,false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL, BorderLineStyle.THIN);
			cellFormat = ExcelView.getUserFormattedCell(font, Colour.AQUA,false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL, BorderLineStyle.THIN);
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return cellFormat;
	}
	public void prepareSheetSettings(ExcelView excelView,String sheetName,WritableCellFormat cellFormat,String reportType){
		try {
			excelView.setWorkSheetName("Category Wise Account Details");
			excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			excelView.getWs().getSettings().setProtected(true);
			excelView.getWs().removeRow(0);
			excelView.getWs().setColumnView(0, 5);
			excelView.getWs().setColumnView(1, 50);
			excelView.getWs().setColumnView(2, 30);
			excelView.getWs().setColumnView(3, 30);
			if("A".equalsIgnoreCase(reportType)){
				excelView.getWs().setColumnView(3, 15);
				schoolAddresDetailsOnlyForExcel(excelView, 3);
				excelView.getWs().mergeCells(0, 6, 3, 6);
			}else if ("VC".equalsIgnoreCase(reportType)) {
				schoolAddresDetailsOnlyForExcel(excelView, 1);
				excelView.getWs().mergeCells(0, 6, 1, 6);
			}
			else{
				schoolAddresDetailsOnlyForExcel(excelView, 2);
				excelView.getWs().mergeCells(0, 6, 2, 6);
			}
			excelView.getWs().addCell(new Label(0, 6, sheetName,cellFormat));
			excelView.getWs().addCell(new Label(0, 7, "#No", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1, 7, "Account Name", excelView.getUsermore10BoldformatGreenBgClr()));
			//excelView.getWs().addCell(new Label(2, 7, "Place", excelView.getUsermore10BoldformatGreenBgClr()));
			if("A".equalsIgnoreCase(reportType) || "AV".equalsIgnoreCase(reportType)){
				excelView.getWs().addCell(new Label(2, 7, "Category Name", excelView.getUsermore10BoldformatGreenBgClr()));
				if(!"AV".equalsIgnoreCase(reportType))
				excelView.getWs().addCell(new Label(3, 7, "Statement Type", excelView.getUsermore10BoldformatGreenBgClr()));
			}else if("AC".equalsIgnoreCase(reportType))
				excelView.getWs().addCell(new Label(2, 7, "Statement Type", excelView.getUsermore10BoldformatGreenBgClr()));
			else if("AS".equalsIgnoreCase(reportType))
				excelView.getWs().addCell(new Label(2, 7, "Category Name", excelView.getUsermore10BoldformatGreenBgClr()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public ExcelView prepareAccountDetails(ExcelView excelView,FinancialAccountDetails accountDetails,String reportType,int studentCount,int rowCount,WritableCellFormat cellFormat){
		try {
			excelView.getWs().addCell(new Label(0, rowCount,String.valueOf(studentCount), excelView.getDefaultFormat()));
			excelView.getWs().addCell(new Label(1, rowCount, accountDetails.getAccountName(), excelView.getDefaultFormat()));
			//excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormat()));
			return excelView;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	@Actions({ @Action(value = "ajaxDownloadTransactions", results = { @Result(location = "reports/ajaxViewTransactionReports.jsp", name = "success") }) })
	public String ajaxDownloadTransactions() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadTransactions' method");
		}
		try {
			loadAcademicYearStartDateAndDates(getUserAcademicYearId());
			setFinancialYearList(accountManager.getAll(FinancialYear.class));
			setFinancialAccountDetailsList(accountManager.getAll(FinancialAccountDetails.class, "custId="+getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}

	@Actions({ @Action(value = "ajaxDownloadAccountTransactions", results = {}) })
	public void ajaxDownloadAccountTransactions() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadAccountTransactions' method");
		}
		try {
			List<FinancialCashBook> financialCashBooksList = null;
			double drAmount = 0, crAmount = 0;
			String transType = "";
			ExcelView excelView = prepareAccountsReportHeader("AllCashBookTransactionDetails");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.YYYY_MM_DD_PATTERN);
			SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			String fromDate =lastUpdatedStr.format(fromUser.parse(getParamValue("startDate")));
			String endDate =lastUpdatedStr.format(fromUser.parse(getParamValue("endDate")));
			//String fromDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("startDate"));
			//String endDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, getParamValue("endDate"));
			//FinancialYear financialYear =(FinancialYear)accountManager.get(FinancialYear.class, "status='Y'");
			financialCashBooksList = accountManager.getAll(FinancialCashBook.class, "custId=" + getUserCustId()+ " and financialYearId=" + getFinancialYearVO().getId()+ " and  Date(transactionDate) >=Date('"+ fromDate+ "') and Date(transactionDate) <=Date('" + endDate+ "') order by transactionDate");
			List<Object[]> cashBookObjDetails = accountManager.getAll("select id,IFNULL(sum(amount),0) as amount,transactionType from finCashBook where custId="+ getUserCustId()+ " and financialYearId="+ getFinancialYearVO().getId()+ " and Date(transactionDate) <Date('"+ fromDate+ "') group by transactionType order by transactionDate");
			if (!ObjectFunctions.isNullOrEmpty(cashBookObjDetails)) {
				for (Object[] object : cashBookObjDetails) {
					if ("C".equalsIgnoreCase(object[2].toString())) {
						crAmount = Double.valueOf(object[1].toString());
						transType = object[2].toString();
					} else if ("D".equalsIgnoreCase(object[2].toString())) {
						drAmount = Double.valueOf(object[1].toString());
						transType = object[2].toString();
					}
					object=null;
				}
				cashBookObjDetails=null;
			}
			//log.debug("drAMount :" + drAmount + "crAmount :"+ crAmount);
			int rowCount = 8;
			int studentCount = 1;
			if (!ObjectFunctions.isNullOrEmpty(financialCashBooksList)) {
				prepareDefaultHeader(excelView,cellFormat);
				String transactionDate = "";
				int count = 1;
				for (FinancialCashBook cashBook : financialCashBooksList) {
					if (!transactionDate.equalsIgnoreCase(cashBook.getTransactionDateStr())) {
						if (studentCount != 1) {
							prepareTotalBalanceView(excelView,rowCount,crAmount,drAmount);
							rowCount++;
							excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getArial10FontBoldWhiteRight()));
							excelView.getWs().addCell(new Label(1,rowCount," Closing Balance C/F ",excelView.getArial10FontBoldWhiteRight()));
							if (crAmount > drAmount) {
								crAmount = crAmount - drAmount;
								excelView.getWs().addCell(new Label(2,rowCount,String.valueOf(crAmount),excelView.getArial10FontBoldWhiteRight()));
								excelView.getWs().addCell(new Label(3,rowCount,"",excelView.getArial10FontBoldWhiteRight()));
								drAmount = 0;
							} else {
								drAmount = drAmount - crAmount;
								excelView.getWs().addCell(new Label(2,rowCount,"",excelView.getArial10FontBoldWhiteRight()));
								excelView.getWs().addCell(new Label(3,rowCount,String.valueOf(drAmount),excelView.getArial10FontBoldWhiteRight()));
								crAmount = 0;
							}
							rowCount += 1;
							prepareEmptyRow(excelView,rowCount);
							rowCount += 1;
						}
						prepareOpeningBalanceView(excelView, rowCount,cashBook,crAmount,drAmount);
						rowCount += 1;
					}
					excelView.getWs().addCell(new Label(0, rowCount,String.valueOf(studentCount), excelView.getDefaultFormat()));
					excelView.getWs().addCell(new Label(1, rowCount, cashBook.getFinancialAccountDetails().getAccountNameAndNum(), excelView.getDefaultFormat()));
					if ("C".equalsIgnoreCase(cashBook.getTransactionType())) {
						excelView.getWs().addCell(new Label(2, rowCount, String.valueOf(cashBook.getAmount()), excelView.getDefaultFormatRight()));
						excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getDefaultFormatRight()));
						crAmount = crAmount + cashBook.getAmount();
					} else if ("D".equalsIgnoreCase(cashBook.getTransactionType())) {
						excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormatRight()));
						excelView.getWs().addCell(new Label(3, rowCount, String.valueOf(cashBook.getAmount()), excelView.getDefaultFormatRight()));
						drAmount = drAmount + cashBook.getAmount();
					}
				
					studentCount += 1;
					rowCount += 1;
					transactionDate = cashBook.getTransactionDateStr();
					//log.debug(financialCashBooksList.size()+ "studentCount :" + count);
					if (financialCashBooksList.size() == count) {
						prepareTotalBalanceView(excelView,rowCount,crAmount,drAmount);
						rowCount += 1;
						excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getArial10FontBoldWhiteRight()));
						excelView.getWs().addCell(new Label(1,rowCount," Closing Balance C/F ",excelView.getArial10FontBoldWhiteRight()));
						if (crAmount > drAmount) {
							crAmount = crAmount - drAmount;
							excelView.getWs().addCell(new Label(2,rowCount,String.valueOf(crAmount),excelView.getArial10FontBoldWhiteRight()));
							excelView.getWs().addCell(new Label(3,rowCount,"",excelView.getArial10FontBoldWhiteRight()));
							drAmount = 0;
						} else {
							drAmount = drAmount - crAmount;
							excelView.getWs().addCell(new Label(2,rowCount,"",excelView.getArial10FontBoldWhiteRight()));
							excelView.getWs().addCell(new Label(3,rowCount,String.valueOf(drAmount),excelView.getArial10FontBoldWhiteRight()));
							crAmount = 0;
						}
						crAmount = 0;
						drAmount = 0;
					}
					count++;
				}
				rowCount += 2;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
			} else {
				prepareDefaultHeader(excelView,cellFormat);
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount,"No transaction details available.", excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareDefaultHeader(ExcelView excelView,WritableCellFormat cellFormat){
		try {
			excelView.setWorkSheetName("Cash Book");
			excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			excelView.getWs().getSettings().setProtected(true);
			excelView.getWs().removeRow(0);
			excelView.getWs().setColumnView(0, 10);
			excelView.getWs().setColumnView(1, 75);
			excelView.getWs().setColumnView(2, 15);
			excelView.getWs().setColumnView(3, 15);
			schoolAddresDetailsOnlyForExcel(excelView, 3);
			excelView.getWs().mergeCells(0, 6, 3, 6);
			excelView.getWs().addCell(new Label(0, 6, "Cash Book Report", cellFormat));
			excelView.getWs().addCell(new Label(0, 7, "#No", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1, 7, "Account Name", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(2, 7, "Credit Amount", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(3, 7, "Debit Amount", excelView.getUsermore10BoldformatGreenBgClr()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	public void prepareTotalBalanceView(ExcelView excelView,int rowCount,double crAmount,double drAmount){
		try {
			excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getArial10FontBoldWhiteRight()));
			excelView.getWs().addCell(new Label(1, rowCount, " Total ", excelView.getArial10FontBoldWhiteRight()));
			if (crAmount > 0) {
				excelView.getWs().addCell(new Label(2,rowCount,String.valueOf(crAmount),excelView.getArial10FontBoldWhiteRight()));
			} else
				excelView.getWs().addCell(new Label(2,rowCount,String.valueOf(crAmount),excelView.getArial10FontBoldWhiteRight()));
			if (drAmount > 0) {
				excelView.getWs().addCell(new Label(3,rowCount,String.valueOf(drAmount),excelView.getArial10FontBoldWhiteRight()));
			} else
				excelView.getWs().addCell(new Label(3,rowCount,String.valueOf(drAmount),excelView.getArial10FontBoldWhiteRight()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareOpeningBalanceView(ExcelView excelView,int rowCount,FinancialCashBook cashBook,double crAmount,double drAmount){
		try {
			excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getDefaultFormat()));
			excelView.getWs().addCell(new Label(1, rowCount, cashBook.getTransactionDateStr()+" Open Balance B/F ", excelView.getDefaultFormatRight()));
			if (crAmount !=0) {
				excelView.getWs().addCell(new Label(2, rowCount, String.valueOf(crAmount), excelView.getDefaultFormatRight()));
				excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getDefaultFormatRight()));
			} else{
				excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormatRight()));
				excelView.getWs().addCell(new Label(3, rowCount, String.valueOf(drAmount), excelView.getDefaultFormatRight()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareEmptyRow(ExcelView excelView,int rowCount){
		try {
			excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getDefaultFormat()));
			excelView.getWs().addCell(new Label(1,rowCount,"",excelView.getDefaultFormat()));
			excelView.getWs().addCell(new Label(2,rowCount,"",excelView.getDefaultFormat()));
			excelView.getWs().addCell(new Label(3,rowCount,"",excelView.getDefaultFormat()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	@Actions({ @Action(value = "ajaxDownloadLedgerAccountWiseDetails", results = {}) })
	public void ajaxDownloadLedgerAccountWiseDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadLedgerAccountWiseDetails' method");
		}
		try {
			List<FinancialCashBook> financialCashBooksList = null;
			List<FinancialCashBook> accountWiseCashBookList = null;
			double drAmount = 0, crAmount = 0;
			ExcelView excelView = prepareAccountsReportHeader("LedgerAccountWiseDetails");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.YYYY_MM_DD_PATTERN);
			SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			String fromDate =lastUpdatedStr.format(fromUser.parse(getParamValue("startDate")));
			String endDate =lastUpdatedStr.format(fromUser.parse(getParamValue("endDate")));
			/*String fromDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("startDate"));
			String endDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, getParamValue("endDate"));*/
			//FinancialYear financialYear =(FinancialYear)accountManager.get(FinancialYear.class, "status='Y'");
			financialCashBooksList = accountManager.getAll(FinancialCashBook.class, "custId=" + getUserCustId()+ " and financialYearId=" + getFinancialYearVO().getId()+ " and finAccountId in "+getTempString()+" and  transactionDate >='" + fromDate+ " 00:00:00' and transactionDate <='" + endDate+ " 00:00:00' order by finAccountId,transactionDate");
			Map<Long, List<FinancialCashBook>> accountWiseTransactionMap=new HashMap<Long, List<FinancialCashBook>>();
			List<FinancialAccountDetails>  accountList=accountManager.getAll(FinancialAccountDetails.class,"custId="+getUserCustId()+" and id in "+getTempString());
			int rowCount = 8;
			if (!ObjectFunctions.isNullOrEmpty(accountList)) {
				if(!ObjectFunctions.isNullOrEmpty(financialCashBooksList)){
					for(FinancialCashBook financialCashBook : financialCashBooksList){
						if(!ObjectFunctions.isNullOrEmpty(accountWiseTransactionMap.get(financialCashBook.getFinancialAccountDetails().getId()))){
							accountWiseCashBookList = accountWiseTransactionMap.get(financialCashBook.getFinancialAccountDetails().getId());
							accountWiseCashBookList.add(financialCashBook);
							accountWiseTransactionMap.put(financialCashBook.getFinancialAccountDetails().getId(), accountWiseCashBookList);
						}else{
							accountWiseCashBookList =new ArrayList<FinancialCashBook>();
							accountWiseCashBookList.add(financialCashBook);
							accountWiseTransactionMap.put(financialCashBook.getFinancialAccountDetails().getId(), accountWiseCashBookList);
						}
					}
					financialCashBooksList=null;
					accountWiseCashBookList=null;
				}
				prepareDefaultLedgerHeader(excelView,cellFormat);
				for(FinancialAccountDetails accountDetails : accountList){
					FinancialAccountTotals accountTotals = (FinancialAccountTotals)accountManager.get(FinancialAccountTotals.class, "custId="+getUserCustId()+" and financialYearId="+getFinancialYearVO().getId()+" and finAccountId="+accountDetails.getId());
					excelView.getWs().mergeCells(0, rowCount,3,rowCount);
					excelView.getWs().addCell(new Label(0, rowCount, accountDetails.getAccountNameAndNum(), cellFormat));
					rowCount+=1;
					excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getDefaultFormat()));
					excelView.getWs().addCell(new Label(1, rowCount, " Open Balance ", excelView.getDefaultFormatRight()));
					if ("C".equalsIgnoreCase(accountTotals.getTransactionType())) {
						crAmount=accountTotals.getBalanceAmount();
						excelView.getWs().addCell(new Label(2, rowCount, String.valueOf(accountTotals.getBalanceAmount()), excelView.getDefaultFormatRight()));
						excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getDefaultFormatRight()));
					} else{
						drAmount=accountTotals.getBalanceAmount();
						excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormatRight()));
						excelView.getWs().addCell(new Label(3, rowCount, String.valueOf(accountTotals.getBalanceAmount()), excelView.getDefaultFormatRight()));
					}
					rowCount+=1;
					accountWiseCashBookList=accountWiseTransactionMap.get(accountDetails.getId());
					if(!ObjectFunctions.isNullOrEmpty(accountWiseCashBookList)){
						for(FinancialCashBook cashBook : accountWiseCashBookList){
							
							excelView.getWs().addCell(new Label(0, rowCount, cashBook.getTransactionDateStr(), excelView.getDefaultFormat()));
							excelView.getWs().addCell(new Label(1, rowCount, cashBook.getNarration(), excelView.getDefaultFormat()));
							if ("C".equalsIgnoreCase(cashBook.getTransactionType())) {
								crAmount=crAmount+cashBook.getAmount();
								excelView.getWs().addCell(new Label(2, rowCount, String.valueOf(cashBook.getAmount()), excelView.getDefaultFormatRight()));
								excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getDefaultFormatRight()));
							} else{
								drAmount=drAmount+cashBook.getAmount();
								excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormatRight()));
								excelView.getWs().addCell(new Label(3, rowCount, String.valueOf(cashBook.getAmount()), excelView.getDefaultFormatRight()));
							}
							rowCount+=1;
						}
						accountWiseCashBookList=null;
					}
					prepareTotalBalanceView(excelView,rowCount,crAmount,drAmount);
					rowCount+=1;
					excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getArial10FontBoldWhiteRight()));
					excelView.getWs().addCell(new Label(1,rowCount," Closing Balance ",excelView.getArial10FontBoldWhiteRight()));
					if (crAmount > drAmount) {
						crAmount = crAmount - drAmount;
						excelView.getWs().addCell(new Label(2,rowCount,String.valueOf(crAmount),excelView.getArial10FontBoldWhiteRight()));
						excelView.getWs().addCell(new Label(3,rowCount,"",excelView.getArial10FontBoldWhiteRight()));
						drAmount = 0;
					} else {
						drAmount = drAmount - crAmount;
						excelView.getWs().addCell(new Label(2,rowCount,"",excelView.getArial10FontBoldWhiteRight()));
						excelView.getWs().addCell(new Label(3,rowCount,String.valueOf(drAmount),excelView.getArial10FontBoldWhiteRight()));
						crAmount = 0;
					}
					crAmount = 0;
					drAmount = 0;
					rowCount+=2;
				}
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
				
				accountList=null;
			} else {
				prepareDefaultLedgerHeader(excelView,cellFormat);
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount,"No transaction details available.", excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	public void prepareDefaultLedgerHeader(ExcelView excelView,WritableCellFormat cellFormat){
		try {
			excelView.setWorkSheetName("Ledger Book");
			excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			excelView.getWs().getSettings().setProtected(true);
			excelView.getWs().removeRow(0);
			excelView.getWs().setColumnView(0, 17);
			excelView.getWs().setColumnView(1, 75);
			excelView.getWs().setColumnView(2, 15);
			excelView.getWs().setColumnView(3, 15);
			schoolAddresDetailsOnlyForExcel(excelView, 3);
			excelView.getWs().mergeCells(0, 6, 3, 6);
			excelView.getWs().addCell(new Label(0, 6, "Ledger Book Report", cellFormat));
			excelView.getWs().addCell(new Label(0, 7, "Transaction Date", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1, 7, "Particular", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(2, 7, "Credit Amount", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(3, 7, "Debit Amount", excelView.getUsermore10BoldformatGreenBgClr()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	@Actions({ @Action(value = "ajaxDownloadFinalStatements", results = { @Result(location = "reports/ajaxViewFinalStatementReports.jsp", name = "success") }) })
	public String ajaxDownloadFinalStatements() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadFinalStatements' method");
		}
		try {
			setFinancialYearList(accountManager.getAll(FinancialYear.class));
			loadAcademicYearStartDateAndDates(getUserAcademicYearId());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxDownloadAllAccountTrailBalance", results = { }) })
	public String ajaxDownloadAllAccountTrailBalance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadAllAccountTrailBalance' method");
		}
		try {
			String reportType = getParamValue("trailBalanceType");
			log.debug("account category type :"+reportType);
			if("OTB".equalsIgnoreCase(reportType)){
				downloadAllAccountOpeningTrailBalance();
			}else if ("RDTB".equalsIgnoreCase(reportType)) {
				downloadAllAccountRangeOfDatesTrailBalance(reportType);
			}else if ("CBS".equalsIgnoreCase(reportType)) {
				downloadAllAccountRangeOfDatesTrailBalance(reportType);
				//downloadClosingTrailBalance();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public void prepareTrailBalancerHeader(ExcelView excelView,WritableCellFormat cellFormat,String sheetName){
		try {
			excelView.setWorkSheetName(sheetName);
			excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			excelView.getWs().getSettings().setProtected(true);
			excelView.getWs().removeRow(0);
			excelView.getWs().setColumnView(0, 5);
			excelView.getWs().setColumnView(1, 75);
			excelView.getWs().setColumnView(2, 15);
			excelView.getWs().setColumnView(3, 15);
			schoolAddresDetailsOnlyForExcel(excelView, 3);
			excelView.getWs().mergeCells(0, 6, 3, 6);
			schoolAddresDetailsOnlyForExcel(excelView, 3);
			excelView.getWs().mergeCells(0, 6, 3, 6);
			excelView.getWs().addCell(new Label(0, 6, sheetName, cellFormat));
			excelView.getWs().addCell(new Label(0, 7, "#No", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1, 7, "Account Name", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(2, 7, "Credit Amount", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(3, 7, "Debit Amount", excelView.getUsermore10BoldformatGreenBgClr()));
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}

	public void downloadAllAccountRangeOfDatesTrailBalance(String reportType) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadAllAccountRangeOfDatesTrailBalance' method");
		}
		try {
			double drAmount = 0, crAmount = 0;
			String fromDate = null;
			String endDate = null;
			String sheetName=null;
			if("RDTB".equalsIgnoreCase(reportType)){
				sheetName = "Dates Range Of Trail Balance";
				SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.YYYY_MM_DD_PATTERN);
				SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
				fromDate =lastUpdatedStr.format(fromUser.parse(getParamValue("startDate")));
				endDate =lastUpdatedStr.format(fromUser.parse(getParamValue("endDate")));
				/*fromDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("startDate"));
				endDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, getParamValue("endDate"));*/
			}else
				sheetName = "Closing Trail Balance";
			ExcelView excelView = prepareAccountsReportHeader(sheetName);
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			
			
			List<ViewFinAccountDetails> accountDetailsList = accountManager.getFinalReportDetails(getUserCustId(),getFinancialYearVO().getId(), fromDate, endDate,"(1,2,3,4)");
			int rowCount = 8;
			int count = 1;
			prepareTrailBalancerHeader(excelView, cellFormat,sheetName);
			ViewFinAccountDetails finAccountDetails=null;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				for (ViewFinAccountDetails accountDetails : accountDetailsList) {
					prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(count),accountDetails.getAccountName());
					/* Here account Id 1 have default account in in that account if we get positive value we will show DR and if we get negative value we will show CR*/
					if (accountDetails.getAccountId() == 1) {
						finAccountDetails=accountDetails;
					} else {
						if (accountDetails.getBalanceAmount() > 0) {
							crAmount = crAmount+ accountDetails.getBalanceAmount();
							prepareCreditAmount(excelView, rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())), "Y");
						} else {
							drAmount = drAmount+ (Math.abs(accountDetails.getBalanceAmount()));
							prepareDebitAmount(excelView, rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())), "Y");
						}
					}
					rowCount++;

					if (accountDetailsList.size() == count) {
						prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(count),finAccountDetails.getAccountName());
						if (finAccountDetails.getBalanceAmount() > 0) {
							drAmount = drAmount + accountDetails.getBalanceAmount();
							prepareDebitAmount(excelView, rowCount,String.valueOf( Math.abs(finAccountDetails.getBalanceAmount())), "Y");
						} else {
							crAmount = crAmount+ Math.abs(finAccountDetails.getBalanceAmount());
							prepareCreditAmount(excelView, rowCount,String.valueOf(Math.abs(finAccountDetails.getBalanceAmount())), "Y");
						}
						rowCount++;
						count++;
						prepareTotalBalanceView(excelView, rowCount, crAmount,drAmount);
						if (crAmount > drAmount) {
							crAmount = crAmount - drAmount;
							if (crAmount > 0) {
								rowCount += 1;
								prepareTrailBalanceDeferance(excelView,rowCount);
								prepareDebitAmount(excelView, rowCount,String.valueOf(crAmount), "N");
							}
							drAmount = 0;
						} else {
							drAmount = drAmount - crAmount;
							if (drAmount > 0) {
								rowCount += 1;
								prepareTrailBalanceDeferance(excelView,rowCount);
								prepareCreditAmount(excelView, rowCount,String.valueOf(drAmount), "N");
							}
							crAmount = 0;
						}
						rowCount++;
					}
					count++;
				}
				rowCount++;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
			} else {
				prepareTrailBalancerHeader(excelView, cellFormat,sheetName);
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount, sheetName+" balance accounts not available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void downloadAllAccountOpeningTrailBalance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadAllAccountOpeningTrailBalance' method");
		}
		try {
			double drAmount = 0, crAmount = 0,totalDrAmount=0,totalCrAmount=0;
			List<ViewFinAccountDetails> accountList =null;
			ExcelView excelView = prepareAccountsReportHeader("Opening Trail Balance");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			//accountList = accountManager.getAll(ViewFinAccountDetails.class, "custId=" + getUserCustId()+" and financialYearId="+getFinancialYearVO().getId()+" and balanceAmount !=0");
			accountList = accountManager.getAllAccountOpeningBalance(getUserCustId(),getFinancialYearVO().getId(), "(1,2,3,4)");
			accountList.addAll(accountManager.getAllAccountOpeningBalanceCashInHand(getUserCustId(),getFinancialYearVO().getId(), "(1,2,3,4)"));
			int rowCount = 8;
			int count = 1;
			if (!ObjectFunctions.isNullOrEmpty(accountList)) {
				prepareTrailBalancerHeader(excelView, cellFormat,"Opening Trail Balance");
				for (ViewFinAccountDetails accountDetails : accountList) {
					prepareSerialNumberAndAccountName(excelView,rowCount,String.valueOf(count),accountDetails.getAccountName());
					if (accountDetails.getAccountId() == 1) {
						if (accountDetails.getBalanceAmount() > 0) {
							drAmount = drAmount + accountDetails.getBalanceAmount();
							prepareDebitAmount(excelView, rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())), "Y");
						} else {
							crAmount = crAmount+ Math.abs(accountDetails.getBalanceAmount());
							prepareCreditAmount(excelView, rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())), "Y");
						}
					}else{
						if ("C".equalsIgnoreCase(accountDetails.getTransactionType())) {
							crAmount = crAmount + accountDetails.getBalanceAmount();
							prepareCreditAmount(excelView,rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())),"Y");
						} else {
							drAmount = drAmount + accountDetails.getBalanceAmount();
							prepareDebitAmount(excelView,rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())),"Y");
						}
					}
					
					if (accountList.size() == count) {
						totalCrAmount=crAmount;
						totalDrAmount=drAmount;
						//rowCount += 1;
						//count ++;
						/*excelView.getWs().addCell(new Label(0, rowCount, String.valueOf(count), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(1,rowCount,"CASH IN HAND",excelView.getDefaultFormat()));*/
						if (crAmount > drAmount) {
							crAmount = crAmount - drAmount;
							totalDrAmount=totalDrAmount+crAmount;
							//prepareDebitAmount(excelView,rowCount,String.valueOf(crAmount),"Y");
							//prepareDebitAmount(excelView,rowCount,String.valueOf(crAmount),"N");
							drAmount = 0;
						} else {
							drAmount = drAmount - crAmount;
							totalCrAmount = totalCrAmount + drAmount;
							//prepareCreditAmount(excelView,rowCount,String.valueOf(drAmount),"Y");
							//prepareCreditAmount(excelView,rowCount,String.valueOf(drAmount),"N");
							crAmount = 0;
						}
						rowCount += 1;
						prepareTotalBalanceView(excelView, rowCount, totalCrAmount,totalDrAmount);
					}
					rowCount += 1;
					count++;
				}
				crAmount = 0;
				drAmount = 0;
				rowCount += 1;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
				accountList = null;
			} else {
				prepareTrailBalancerHeader(excelView, cellFormat,"Opening Trail Balance");
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount,"Opening balance accounts not available.", excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	/*public void downloadClosingTrailBalance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadClosingTrailBalance' method");
		}
		try {
			double drAmount = 0, crAmount = 0;
			List<FinancialAccountDetails> accountList =null;
			ExcelView excelView = prepareAccountsReportHeader("Closing Balance Sheet");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			
			FinancialYear financialYear =(FinancialYear)accountManager.get(FinancialYear.class, "status='Y'");
			accountList = accountManager.getAll(FinancialAccountDetails.class, "custId=" + getUserCustId());
			Map<Long, CashBookVO> accountTotalMap = new HashMap<Long, CashBookVO>();
			List<Object[]> cashBookObjDetails = accountManager.getAll("SELECT A.id,A.transactionType,IFNULL(A.balanceAMount,0) as balanceAMount,ifNULL((SELECT SUM(IFNULL(amount,0)) FROM finCashBook C WHERE C.custId="+getUserCustId()+" and C.financialYearId="+ financialYear.getId()+ " and C.transactionType='C' AND C.finAccountId=A.id),0) CrAmt,ifNULL((SELECT SUM(IFNULL(amount,0)) FROM finCashBook C WHERE C.custId="+getUserCustId()+" and C.financialYearId="+ financialYear.getId()+ " and C.transactionType='D' AND C.finAccountId=A.id),0) DrAmt FROM finAccountDetails A where A.custId="+getUserCustId()+" ORDER BY id");
			if (!ObjectFunctions.isNullOrEmpty(cashBookObjDetails)) {
				for (Object[] object : cashBookObjDetails) {
					CashBookVO cashBookVO = new CashBookVO();
					crAmount = Double.valueOf(object[3].toString());
					drAmount = Double.valueOf(object[4].toString());
					if(!ObjectFunctions.isNullOrEmpty(object[1])){
						if("C".equalsIgnoreCase(object[1].toString()))
							crAmount+=Double.valueOf(object[2].toString());
						else if ("D".equalsIgnoreCase(object[1].toString())) {
							drAmount+=Double.valueOf(object[2].toString());
						}
					}
					if (crAmount > drAmount) {
						crAmount = crAmount - drAmount;
						cashBookVO.setTransactionType("C");
						cashBookVO.setAmount(crAmount);
						drAmount = 0;
					} else {
						drAmount = drAmount - crAmount;
						cashBookVO.setTransactionType("D");
						cashBookVO.setAmount(drAmount);
						crAmount = 0;
					}
					accountTotalMap.put(Long.valueOf(object[0].toString()), cashBookVO);
					object=null;
				}
				cashBookObjDetails=null;
			}
			
			CashBookVO cashBookVO=null;
			int rowCount = 8;
			int count = 1;
			prepareTrailBalancerHeader(excelView, cellFormat,"Closing Balance Sheet");
			if(!ObjectFunctions.isNullOrEmpty(accountList)){
				for (FinancialAccountDetails accountDetails : accountList) {
					cashBookVO=accountTotalMap.get(accountDetails.getId());
					if(!ObjectFunctions.isNullOrEmpty(cashBookVO)){
						if(cashBookVO.getAmount()>0){
							prepareSerialNumberAndAccountName(excelView,rowCount,String.valueOf(count),accountDetails.getAccountNameAndNum());
							if ("C".equalsIgnoreCase(cashBookVO.getTransactionType())) {
								crAmount = crAmount + cashBookVO.getAmount();
								prepareCreditAmount(excelView,rowCount,String.valueOf(cashBookVO.getAmount()),"Y");
							} else {
								drAmount = drAmount + cashBookVO.getAmount();
								prepareDebitAmount(excelView,rowCount,String.valueOf(cashBookVO.getAmount()),"Y");
							}
						}
					}
					if (accountList.size() == count) {
						rowCount += 1;
						prepareTotalBalanceView(excelView, rowCount, crAmount,drAmount);
						rowCount += 1;
						prepareTrailBalanceDeferance(excelView,rowCount);
						if (crAmount > drAmount) {
							crAmount = crAmount - drAmount;
							prepareDebitAmount(excelView,rowCount,String.valueOf(crAmount),"N");
							drAmount = 0;
						} else {
							drAmount = drAmount - crAmount;
							prepareCreditAmount(excelView,rowCount,String.valueOf(drAmount),"N");
							crAmount = 0;
						}
					}
					rowCount++;
					count++;
				}
				rowCount++;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
			}else {
				prepareTrailBalancerHeader(excelView, cellFormat,"Opening Trail Balance");
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount,"Opening balance accounts not available.", excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}*/
	public void prepareSerialNumberAndAccountName(ExcelView excelView,int rowCount,String count,String accountName){
		try {
			excelView.getWs().addCell(new Label(0, rowCount, String.valueOf(count),excelView.getDefaultFormat()));
			excelView.getWs().addCell(new Label(1, rowCount, accountName, excelView.getDefaultFormat()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareCreditAmount(ExcelView excelView,int rowCount,String amount,String formateType){
		try {
			if("Y".equalsIgnoreCase(formateType)){
				excelView.getWs().addCell(new Label(2, rowCount, amount, excelView.getDefaultFormatRight()));
				excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getDefaultFormatRight()));
			}else{
				excelView.getWs().addCell(new Label(2, rowCount, amount, excelView.getArial10FontBoldWhiteRight()));
				excelView.getWs().addCell(new Label(3, rowCount, "", excelView.getArial10FontBoldWhiteRight()));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareDebitAmount(ExcelView excelView,int rowCount,String amount,String formateType){
		try {
			if("Y".equalsIgnoreCase(formateType)){
				excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getDefaultFormatRight()));
				excelView.getWs().addCell(new Label(3, rowCount, amount, excelView.getDefaultFormatRight()));
			}else{
				excelView.getWs().addCell(new Label(2, rowCount, "", excelView.getArial10FontBoldWhiteRight()));
				excelView.getWs().addCell(new Label(3, rowCount, amount, excelView.getArial10FontBoldWhiteRight()));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareTrailBalanceDeferance(ExcelView excelView,int rowCount){
		try {
			excelView.getWs().addCell(new Label(0, rowCount, "", excelView.getArial10FontBoldWhiteRight()));
			excelView.getWs().addCell(new Label(1,rowCount,"Trail Balance Deferance",excelView.getArial10FontBoldWhiteRight()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	@Actions({ @Action(value = "ajaxDownloadIncomeAndExpAccountWiseDetails", results = { }) })
	public String ajaxDownloadIncomeAndExpAccountWiseDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadIncomeAndExpAccountWiseDetails' method");
		}
		try {
			String reportType = getParamValue("incomeAndExpType");
			log.debug("account category type :"+reportType);
			if("OS".equalsIgnoreCase(reportType)){
				downloadAllAccountIncomeAndExpanceBalance();
			}else if ("SBRD".equalsIgnoreCase(reportType)) {
				downloadAllAccountRangeOfDatesIncomeAndExp(reportType);
			}else if ("YES".equalsIgnoreCase(reportType)) {
				downloadAllAccountRangeOfDatesIncomeAndExp(reportType);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public void downloadAllAccountIncomeAndExpanceBalance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadAllAccountIncomeAndExpanceBalance' method");
		}
		try {
			double inTotalAmount = 0, exTotalAmount = 0;
			ExcelView excelView = prepareAccountsReportHeader("Income & Expenduture");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			List<ViewFinAccountDetails> accountDetailsList = accountManager.getAllAccountOpeningBalance(getUserCustId(),getFinancialYearVO().getId(), "(2,3)");
			int rowCount = 8;
			int count = 1;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				prepareIncomeAndExpHeader(excelView, cellFormat,"Income & Expenduture", "Y");
				for (ViewFinAccountDetails accountDetails : accountDetailsList) {
					prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(count),accountDetails.getAccountName());
					if ("IN".equalsIgnoreCase(accountDetails.getStatmentCode())) {
						inTotalAmount = inTotalAmount+ accountDetails.getBalanceAmount();
						prepareDrAndCrAmount(excelView, rowCount,String.valueOf(accountDetails.getBalanceAmount()), "Y", "IN");
					} else if ("EX".equalsIgnoreCase(accountDetails.getStatmentCode())) {
						exTotalAmount = exTotalAmount+ accountDetails.getBalanceAmount();
						prepareDrAndCrAmount(excelView, rowCount,String.valueOf(accountDetails.getBalanceAmount()), "Y","EX");
					}
					if (accountDetailsList.size() == count) {
						rowCount += 1;
						count++;
						ViewFinAccountDetails totalInAndExp =(ViewFinAccountDetails) accountManager.getAllAccountOpeningBalanceCashInHand(getUserCustId(), getFinancialYearVO().getId(), "(2,3)").get(0);
						excelView.getWs().addCell(new Label(0, rowCount, String.valueOf(count), excelView.getArial10FontBoldWhiteRight()));
						if(totalInAndExp.getBalanceAmount() >0)
							prepareFinalIncomeAndExp(excelView,rowCount,totalInAndExp.getBalanceAmount(),"N", "Income A/C");
						else
							prepareFinalIncomeAndExp(excelView,rowCount,totalInAndExp.getBalanceAmount(),"N", "Loss A/C");
						/*if (inTotalAmount > exTotalAmount) {
							inTotalAmount = inTotalAmount - Math.abs(exTotalAmount);
							prepareFinalIncomeAndExp(excelView,rowCount,inTotalAmount,"N", "Income A/C");
						} else {
							exTotalAmount = exTotalAmount - Math.abs(inTotalAmount);
							prepareFinalIncomeAndExp(excelView,rowCount,exTotalAmount,"N", "Loss A/C");
						}*/
					}
					rowCount += 1;
					count++;
				}
				rowCount += 1;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
				accountDetailsList = null;
			} else {
				prepareTrailBalancerHeader(excelView, cellFormat,"Income & Expenduture");
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0,rowCount,"Opening Income & Expenduture balance accounts not available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareIncomeAndExpHeader(ExcelView excelView,WritableCellFormat cellFormat,String sheetName,String type){
		try {
			excelView.setWorkSheetName(sheetName);
			excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			excelView.getWs().getSettings().setProtected(true);
			excelView.getWs().removeRow(0);
			excelView.getWs().setColumnView(0, 5);
			excelView.getWs().setColumnView(1, 75);
			excelView.getWs().setColumnView(2, 20);
			excelView.getWs().setColumnView(3, 15);
			excelView.getWs().setColumnView(4, 15);
			schoolAddresDetailsOnlyForExcel(excelView, 3);
			excelView.getWs().mergeCells(0, 6, 3, 6);
			schoolAddresDetailsOnlyForExcel(excelView, 4);
			excelView.getWs().mergeCells(0, 6, 3, 6);
			excelView.getWs().addCell(new Label(0, 6, sheetName, cellFormat));
			excelView.getWs().addCell(new Label(0, 7, "#No", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1, 7, "Account Name", excelView.getUsermore10BoldformatGreenBgClr()));
			//excelView.getWs().addCell(new Label(2, 7, "Transaction Type", excelView.getUsermore10BoldformatGreenBgClr()));
			if("Y".equalsIgnoreCase(type)){
				excelView.getWs().addCell(new Label(2, 7, "Income", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(3, 7, "Expenditure", excelView.getUsermore10BoldformatGreenBgClr()));
			}else{
				excelView.getWs().addCell(new Label(2, 7, "Liabilities", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(3, 7, "Assets", excelView.getUsermore10BoldformatGreenBgClr()));
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareDrAndCrAmount(ExcelView excelView,int rowCount,String amount,String formateType,String type){
		try {
			WritableCellFormat defaultFotmate=prepareDefaultFormate(excelView,formateType);
			if("IN".equalsIgnoreCase(type)){
				excelView.getWs().addCell(new Label(2, rowCount, String.valueOf(Math.abs(Double.valueOf(amount))), defaultFotmate));
				excelView.getWs().addCell(new Label(3, rowCount, "", defaultFotmate));
			}else if ("EX".equalsIgnoreCase(type)) {
				excelView.getWs().addCell(new Label(2, rowCount, "", defaultFotmate));
				excelView.getWs().addCell(new Label(3, rowCount, String.valueOf(Math.abs(Double.valueOf(amount))), defaultFotmate));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareFinalIncomeAndExp(ExcelView excelView,int rowCount,double amount,String formateType,String incomeOrExp){
		try {
			WritableCellFormat defaultFotmate=prepareDefaultFormate(excelView,formateType);
			excelView.getWs().addCell(new Label(1,rowCount,incomeOrExp,defaultFotmate));
			if(amount >0){
				excelView.getWs().addCell(new Label(2, rowCount, "", defaultFotmate));
				excelView.getWs().addCell(new Label(3, rowCount, String.valueOf(Math.abs(amount)), defaultFotmate));
			}else {
				excelView.getWs().addCell(new Label(2, rowCount, String.valueOf(Math.abs(amount)), defaultFotmate));
				excelView.getWs().addCell(new Label(3, rowCount, "", defaultFotmate));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public void prepareFinalIncomeAndExpReportTotal(ExcelView excelView,int rowCount,String totCrAmount,String totDrAmount){
		try {
			excelView.getWs().addCell(new Label(0,rowCount,"",excelView.getArial10FontBoldWhiteRight()));
			excelView.getWs().addCell(new Label(1,rowCount,"Report Total",excelView.getArial10FontBoldWhiteRight()));
			excelView.getWs().addCell(new Label(2, rowCount, String.valueOf(Math.abs(Double.valueOf(totCrAmount))), excelView.getArial10FontBoldWhiteRight()));
			excelView.getWs().addCell(new Label(3, rowCount, String.valueOf(Math.abs(Double.valueOf(totDrAmount))), excelView.getArial10FontBoldWhiteRight()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public WritableCellFormat prepareDefaultFormate(ExcelView excelView,String formateType){
		try {
			WritableCellFormat defaultFotmate=null;
			if("Y".equalsIgnoreCase(formateType))
				return defaultFotmate=excelView.getDefaultFormatRight();
			else if ("N".equalsIgnoreCase(formateType)) {
				return defaultFotmate=excelView.getArial10FontBoldWhiteRight();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public void downloadAllAccountRangeOfDatesIncomeAndExp(String reportType) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadAllAccountRangeOfDatesIncomeAndExp' method");
		}
		try {
			double inTotalAmount = 0, exTotalAmount = 0;
			String fromDate = null;
			String endDate = null;
			String sheetName = null;
			if ("SBRD".equalsIgnoreCase(reportType)) {
				sheetName = "Dates Range Of Income & Exp";
				SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.YYYY_MM_DD_PATTERN);
				SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
				fromDate =lastUpdatedStr.format(fromUser.parse(getParamValue("startDate")));
				endDate =lastUpdatedStr.format(fromUser.parse(getParamValue("endDate")));
				/*fromDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("startDate"));
				endDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("endDate"));*/
			} else
				sheetName = "Year End Income & Exp";
			ExcelView excelView = prepareAccountsReportHeader(sheetName);
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();

			List<ViewFinAccountDetails> accountDetailsList = accountManager.getFinalReportDetails(getUserCustId(),getFinancialYearVO().getId(), fromDate, endDate,"(2,3)");
			int rowCount = 8;
			int count = 1;
			prepareIncomeAndExpHeader(excelView, cellFormat, sheetName, "Y");
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				for (ViewFinAccountDetails accountDetails : accountDetailsList) {
					prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(count),accountDetails.getAccountName());
					if ("IN".equalsIgnoreCase(accountDetails.getStatmentCode())) {
						inTotalAmount = inTotalAmount + accountDetails.getBalanceAmount();
						prepareDrAndCrAmount(excelView, rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())), "Y", "IN");
					} else if ("EX".equalsIgnoreCase(accountDetails.getStatmentCode())) {
						exTotalAmount = exTotalAmount + accountDetails.getBalanceAmount();
						prepareDrAndCrAmount(excelView, rowCount,String.valueOf(Math.abs(accountDetails.getBalanceAmount())), "Y", "EX");
					}

					if (accountDetailsList.size() == count) {
						rowCount += 1;
						count++;
						ViewFinAccountDetails inAndExpTotal=(ViewFinAccountDetails)accountManager.getSumOfFinalReportDetails(getUserCustId(),getFinancialYearVO().getId(), fromDate, endDate,"(2,3)").get(0);
						excelView.getWs().addCell(new Label(0, rowCount, String.valueOf(count), excelView.getArial10FontBoldWhiteRight()));
						if(inAndExpTotal.getBalanceAmount() >0)
							prepareFinalIncomeAndExp(excelView,rowCount,inAndExpTotal.getBalanceAmount(),"N", "Income A/C");
						else
							prepareFinalIncomeAndExp(excelView,rowCount,inAndExpTotal.getBalanceAmount(),"N", "Loss A/C");
						/*if (inTotalAmount > exTotalAmount) {
							inTotalAmount = inTotalAmount - Math.abs(exTotalAmount);
							prepareFinalIncomeAndExp(excelView, rowCount,inTotalAmount, "N", "Income A/C");
						} else {
							exTotalAmount = exTotalAmount - Math.abs(inTotalAmount);
							prepareFinalIncomeAndExp(excelView, rowCount,exTotalAmount, "N", "Loss A/C");
						}*/
					}
					rowCount++;
					count++;
				}
				rowCount++;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
			} else {
				prepareTrailBalancerHeader(excelView, cellFormat, sheetName);
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount,"Income & Exp balance accounts not available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			RayGunException raygex = new RayGunException();
			raygex.sendRayGunException(ex);
			raygex = null;
		}
	}
	@Actions({ @Action(value = "ajaxDownloadBalanceSheetAccountWiseDetails", results = { }) })
	public String ajaxDownloadBalanceSheetAccountWiseDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadBalanceSheetAccountWiseDetails' method");
		}
		try {
			String reportType = getParamValue("balanceType");
			log.debug("account category type :"+reportType);
			if("BOS".equalsIgnoreCase(reportType)){
				downloadAllAccountOpeningBalanceSheetAmount();
			}else if ("BSBR".equalsIgnoreCase(reportType)) {
				downloadAllAccountRangeOfDatesLiAndAsBalanceSheet(reportType);
			}else if ("BYS".equalsIgnoreCase(reportType)) {
				downloadAllAccountRangeOfDatesLiAndAsBalanceSheet(reportType);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public void downloadAllAccountOpeningBalanceSheetAmount() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadAllAccountOpeningBalanceSheetAmount' method");
		}
		try {
			double liTotalAmount = 0, asTotalAmount = 0, inTotalAmount = 0, exTotalAmount = 0, totalLiAmount = 0, totalAsAmount = 0;
			ExcelView excelView = prepareAccountsReportHeader("Opening Balance Details");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			List<ViewFinAccountDetails> accountDetailsList = accountManager.getAllAccountOpeningBalance(getUserCustId(),getFinancialYearVO().getId(), "(1,2,3,4)");
			accountDetailsList.addAll(accountManager.getAllAccountOpeningBalanceCashInHand(getUserCustId(),getFinancialYearVO().getId(), "(1,2,3,4)"));
			int rowCount = 8;
			int count = 1;
			int number = 1;
			ViewFinAccountDetails finAccountDetails=null;
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				prepareIncomeAndExpHeader(excelView, cellFormat,"Opening Balance Details", "N");
				for (ViewFinAccountDetails accountDetails : accountDetailsList) {

					if(accountDetails.getAccountId()!=1){
						if ("LI".equalsIgnoreCase(accountDetails.getStatmentCode()) || "AS".equalsIgnoreCase(accountDetails.getStatmentCode()))
							prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(number),accountDetails.getAccountName());
							
						if ("LI".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								liTotalAmount = liTotalAmount + accountDetails.getBalanceAmount();
								prepareDrAndCrAmount(excelView, rowCount,String.valueOf(accountDetails.getBalanceAmount()), "Y", "IN");
							} else if ("AS".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								asTotalAmount = asTotalAmount+ accountDetails.getBalanceAmount();
								prepareDrAndCrAmount(excelView, rowCount,String.valueOf(accountDetails.getBalanceAmount()), "Y", "EX");
							} else if ("IN".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								inTotalAmount = inTotalAmount + accountDetails.getBalanceAmount();
							} else if ("EX".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								exTotalAmount = exTotalAmount + accountDetails.getBalanceAmount();
							}
					}else{
						finAccountDetails = accountDetails;
					}
					
					if (accountDetailsList.size() == count) {
						//Below code only for CASH IN HAND to display end of loop .
						prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(number),finAccountDetails.getAccountName());
						asTotalAmount = asTotalAmount+ finAccountDetails.getBalanceAmount();
						prepareDrAndCrAmount(excelView, rowCount,String.valueOf(finAccountDetails.getBalanceAmount()), "Y", "EX");
						
						rowCount++;
						number++;
						prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(number), "Income / Loss Account");
						if (inTotalAmount > exTotalAmount) {
							inTotalAmount = inTotalAmount - Math.abs(exTotalAmount);
							liTotalAmount = liTotalAmount + inTotalAmount;
							prepareDrAndCrAmount(excelView, rowCount,String.valueOf(inTotalAmount), "Y", "IN");
							rowCount += 1;
							prepareFinalIncomeAndExpReportTotal(excelView,rowCount, String.valueOf(asTotalAmount),String.valueOf(liTotalAmount));
						} else {
							exTotalAmount = exTotalAmount - Math.abs(inTotalAmount);
							asTotalAmount = asTotalAmount + exTotalAmount;
							prepareDrAndCrAmount(excelView, rowCount,String.valueOf(inTotalAmount), "Y", "IN");
							rowCount += 1;
							prepareFinalIncomeAndExpReportTotal(excelView,rowCount, String.valueOf(liTotalAmount),String.valueOf(asTotalAmount));
						}
					}
					if ("LI".equalsIgnoreCase(accountDetails.getStatmentCode()) || "AS".equalsIgnoreCase(accountDetails.getStatmentCode())) {
						if(accountDetails.getAccountId()!=1){
						rowCount += 1;
						number++;
						}
					}
					count++;
				}
				rowCount += 2;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
				accountDetailsList = null;
			} else {
				prepareTrailBalancerHeader(excelView, cellFormat,"Opening Balance Details");
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0,rowCount,"Opening Balance accounts details not available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}

	public void downloadAllAccountRangeOfDatesLiAndAsBalanceSheet(String reportType) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadAllAccountRangeOfDatesLiAndAsBalanceSheet' method");
		}
		try {
			double liTotalAmount = 0, asTotalAmount = 0, inTotalAmount = 0, exTotalAmount = 0;
			String fromDate = null;
			String endDate = null;
			ExcelView excelView = prepareAccountsReportHeader("Liabilities & Assets Balance Sheet");
			WritableCellFormat cellFormat = prepareAccountsReportHeaderCellFormate();
			if ("BSBR".equalsIgnoreCase(reportType)) {
				SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.YYYY_MM_DD_PATTERN);
				SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
				fromDate =lastUpdatedStr.format(fromUser.parse(getParamValue("startDate")));
				endDate =lastUpdatedStr.format(fromUser.parse(getParamValue("endDate")));
				/*fromDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("startDate"));
				endDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getParamValue("endDate"));*/
			}
			List<ViewFinAccountDetails> accountDetailsList = accountManager.getFinalReportDetails(getUserCustId(),getFinancialYearVO().getId(), fromDate, endDate,"(1,2,3,4)");

			int rowCount = 8;
			int count = 1;
			int number = 1;
			ViewFinAccountDetails finAccountDetails =null;
			prepareIncomeAndExpHeader(excelView, cellFormat,"Liabilities & Assets Balance Sheet", "N");
			if (!ObjectFunctions.isNullOrEmpty(accountDetailsList)) {
				for (ViewFinAccountDetails accountDetails : accountDetailsList) {
					if(accountDetails.getAccountId()!=1){
						if ("LI".equalsIgnoreCase(accountDetails.getStatmentCode()) || "AS".equalsIgnoreCase(accountDetails.getStatmentCode()))
							prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(number),accountDetails.getAccountName());
							
						if ("LI".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								liTotalAmount = liTotalAmount + accountDetails.getBalanceAmount();
								prepareDrAndCrAmount(excelView, rowCount,String.valueOf(accountDetails.getBalanceAmount()), "Y", "IN");
							} else if ("AS".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								asTotalAmount = asTotalAmount+ accountDetails.getBalanceAmount();
								prepareDrAndCrAmount(excelView, rowCount,String.valueOf(accountDetails.getBalanceAmount()), "Y", "EX");
							} else if ("IN".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								inTotalAmount = inTotalAmount + accountDetails.getBalanceAmount();
							} else if ("EX".equalsIgnoreCase(accountDetails.getStatmentCode())) {
								exTotalAmount = exTotalAmount + accountDetails.getBalanceAmount();
							}
					}else{
						finAccountDetails = accountDetails;
					}
					

					if (accountDetailsList.size() == count) {
						/*Below code only for CASH IN HAND to display end of loop .*/
						prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(number),finAccountDetails.getAccountName());
						asTotalAmount = asTotalAmount+ finAccountDetails.getBalanceAmount();
						prepareDrAndCrAmount(excelView, rowCount,String.valueOf(finAccountDetails.getBalanceAmount()), "Y", "EX");
						rowCount++;
						number++;
						
						ViewFinAccountDetails inAndExpTotal=(ViewFinAccountDetails)accountManager.getSumOfFinalReportDetails(getUserCustId(),getFinancialYearVO().getId(), fromDate, endDate,"(2,3)").get(0);
						excelView.getWs().addCell(new Label(0, rowCount, String.valueOf(count), excelView.getArial10FontBoldWhiteRight()));
						prepareSerialNumberAndAccountName(excelView, rowCount,String.valueOf(number), "Income / Loss Account");
						prepareDrAndCrAmount(excelView, rowCount,String.valueOf(inAndExpTotal.getBalanceAmount()), "Y", "IN");
						liTotalAmount = liTotalAmount + inAndExpTotal.getBalanceAmount();
						
						/*if (inTotalAmount > exTotalAmount) {
							inTotalAmount = inTotalAmount - Math.abs(exTotalAmount);
							liTotalAmount = liTotalAmount + inTotalAmount;
							prepareDrAndCrAmount(excelView, rowCount,String.valueOf(inTotalAmount), "Y", "IN");
							rowCount += 1;
							prepareFinalIncomeAndExpReportTotal(excelView,rowCount, String.valueOf(asTotalAmount),String.valueOf(liTotalAmount));
						} else {
							exTotalAmount = exTotalAmount - Math.abs(inTotalAmount);
							asTotalAmount = asTotalAmount + exTotalAmount;
							prepareDrAndCrAmount(excelView, rowCount,String.valueOf(inTotalAmount), "Y", "IN");
							rowCount += 1;
							prepareFinalIncomeAndExpReportTotal(excelView,rowCount, String.valueOf(liTotalAmount),String.valueOf(asTotalAmount));
						}*/
						rowCount += 1;
						prepareFinalIncomeAndExpReportTotal(excelView,rowCount, String.valueOf(asTotalAmount),String.valueOf(liTotalAmount));
					}
					if ("LI".equalsIgnoreCase(accountDetails.getStatmentCode()) || "AS".equalsIgnoreCase(accountDetails.getStatmentCode())) {
						if(accountDetails.getAccountId()!=1){
						rowCount += 1;
						number++;
						}
					}
					count++;
				}
				rowCount += 2;
				showSchoolUrlInExcelSheetFooter(rowCount - 2, excelView, 3);
			} else {
				prepareTrailBalancerHeader(excelView, cellFormat,"Income & Exp");
				excelView.getWs().mergeCells(0, rowCount, 3, rowCount);
				excelView.getWs().addCell(new Label(0, rowCount,"Income & Exp balance accounts not available.",excelView.getDefaultFormat()));
			}
			excelView.getWb().write();
			excelView.getWb().close();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	@Actions({
		@Action(value = "ajaxDoSchoolAccountInformation", results = { @Result(location = "ajaxAddCustomerAccountDetails.jsp", name = "success") }) })
	public String ajaxDoSchoolAccountInformation() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSchoolAccountInformation' method");
		}
		try {
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			setCountryList((List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST));
			setBankAccountDetailsVO(accountManager.getCustomerBankAccountDetailsVo(getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "ajaxAddBankAccountDetails", results = { @Result(location = "ajaxAddCustomerAccountDetails.jsp", name = "success") }) })
	public String ajaxAddBankAccountDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddBankAccountDetails' method");
		}
		try {
			 int returnCode =accountManager.getAddBankAccountDetails(getBankAccountDetailsVO(),getUserCustId(),getUser().getId());
			 if(returnCode ==1)
				 super.addActionMessage("Bank account details save sucessfully");
			 else
				 super.addActionError("Bank account details not saved. Please contact support team.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		ajaxDoSchoolAccountInformation();
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxVouchernumberAvailableOrNot", results = { @Result(type = "json", name = "success", params = { "includeProperties", "autoCheck" }) }) })
	public String ajaxVouchernumberAvailableOrNot()throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxVouchernumberAvailableOrNot' method");
		}
		try {
			List<FinancialCashBook> cashBookVoucherList = null;
			String voucherNumber = getParamValue("keyWord");
			if (StringFunctions.isNotNullOrEmpty(voucherNumber)) {
				cashBookVoucherList = adminManager.getAll(FinancialCashBook.class,"custId="+getUserCustId()+" and vocherNumber like '" +voucherNumber+ "' ");
				if (cashBookVoucherList.size() > 0) {
						setAutoCheck("1");
				}else{
					setAutoCheck("0");
				}
				}
			cashBookVoucherList = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}	
}
