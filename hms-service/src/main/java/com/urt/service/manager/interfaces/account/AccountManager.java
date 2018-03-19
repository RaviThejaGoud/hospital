package com.urt.service.manager.interfaces.account;

import java.util.Date;
import java.util.List;

import com.hyniva.sms.ws.vo.account.CustomerBankAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.FinAccountMainVO;
import com.hyniva.sms.ws.vo.account.FinAccountWebAndClientIdsMainVO;
import com.hyniva.sms.ws.vo.account.FinCashbookMainVO;
import com.hyniva.sms.ws.vo.account.FinCategoryMainVO;
import com.hyniva.sms.ws.vo.account.FinWebAndClientIdsMainVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountCategoryVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.FinancialAccountTotalsVO;
import com.hyniva.sms.ws.vo.account.FinancialParticaularAssociationMainVO;
import com.hyniva.sms.ws.vo.account.FinancialYearMainVO;
import com.hyniva.sms.ws.vo.cashbook.CashBookVO;
import com.hyniva.sms.ws.vo.fee.DeleteStudentPaymentVo;
import com.urt.persistence.model.account.FinancialAccountCategory;
import com.urt.persistence.model.account.FinancialAccountDetails;
import com.urt.persistence.model.account.FinancialCashBook;
import com.urt.persistence.model.account.ViewFinAccountDetails;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.FineFee;
import com.urt.persistence.model.secretary.FinancialYear;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.service.manager.interfaces.base.UniversalManager;

public interface AccountManager extends UniversalManager {

	public FinancialAccountDetails addOrUpdateFinancialAccountDetails(Customer customer,FinancialAccountDetailsVO finAccountDetailsVO,FinancialAccountCategoryVO finAccountCategoryVO,FinancialAccountTotalsVO financialAccountTotalsVO,long userId);
	
	public int createOrUpdateFinancialAccountDetails(Customer customer,FinancialAccountDetailsVO finAccountDetailsVO,FinancialAccountCategoryVO finAccountCategoryVO,FinancialAccountTotalsVO financialAccountTotalsVO,long userId);
	
	public FinancialAccountDetailsVO getFinancialAccountDetails(long accountDetailsId);
	
	FinancialCashBook saveCashBook(CashBookVO cashBookVO,Customer customer,FinancialYear financialYear,FinancialAccountDetails accountDetails);
	
	public long addParticularAssociationToAccount(String concessionFormData,AcademicYear academicYear, long userId, long custId);
	
	FinCategoryMainVO getAccountCategoryDetails(long custId,String cretedDate);
	
	FinAccountMainVO getAccountDetails(long custId,String cretedDate);
	
	FinancialYearMainVO getFinancialYearDetails(String cretedDate);
	
	FinCashbookMainVO getCashbookDetails(long custId,long financialYearId,String cretedDate);
	
	FinancialAccountTotalsVO getFinancialAccountTotalsVO(long accountId,long finYearId);
	
	int saveVoucherDetailsInMontlyAccountTotal(double amount,Date transactionDate,String transactionType,Customer customer,FinancialYear financialYear,FinancialAccountDetails accountDetails,long userId);
	
	double getMonthTotalValue(String transactionType,double amount,double monthTotalAmount);
	
	List<ViewFinAccountDetails> getFinalReportDetails(long custId,long financialYearId,String fromDate,String endDate,String statementIds);
	
	List<ViewFinAccountDetails> getAllAccountOpeningBalance(long custId,long financialYearId,String statementIds);
	
	FinancialParticaularAssociationMainVO getFinancialParticaularAssociation(long custId,String cretedDate);
	
	FinWebAndClientIdsMainVO addOrUpdateFinAccountMainVO(FinancialYearMainVO financialYearMainVO);
	
	FinWebAndClientIdsMainVO addOrUpdateAccountCategoryDetails(FinCategoryMainVO finCategoryMainVO,long custId);
	
	int addOrUpdateAccountCategory(long custId,FinancialAccountCategoryVO financialAccountCategoryVO,long userId);
	
	FinancialAccountCategory addOrUpdateFinAccountCategory(long custId,FinancialAccountCategoryVO financialAccountCategoryVO,long userId);
	
	FinAccountWebAndClientIdsMainVO addOrUpdateAccountDetailsClient(FinAccountMainVO finAccountMainVO,long custId,long financialYearId);
	
	FinWebAndClientIdsMainVO addOrUpdateCashbook(FinCashbookMainVO finCashbookMainVO,long custId,long financialYearId);
	
	FinWebAndClientIdsMainVO particaularAssociationSubmit(FinancialParticaularAssociationMainVO particaularAssociationMainVO,long custId);
	
	public Date getLastCreatedDate(long webId,String tableName);
	
	List<ViewFinAccountDetails> getAllAccountOpeningBalanceCashInHand(long custId,long financialYearId,String statementIds);
	
	List<ViewFinAccountDetails> getSumOfFinalReportDetails(long custId,long financialYearId,String fromDate,String endDate,String statementIds);
	
	int getAddBankAccountDetails(CustomerBankAccountDetailsVO bankAccountDetailsVO,long custId,long userId);
	
	CustomerBankAccountDetailsVO getCustomerBankAccountDetailsVo(long custId);
	
	List<FineFee> getOtherFeePaymentDetails(long custId,long academicYearId,long studentId);
	
	int deleteInvoicePaymentByMasterAdmin(DeleteStudentPaymentVo deleteStudentPaymentVo,long userId,String deleteType,long academicYearId,long studentId);
	
	List<ViewStudentFeePaymentDetails> getPreviousYearFeeDefaultersList(long custId,long academicYearId,String toDayDate);
	
	Object[] getOverAllFeePaymentSummary(long custId,long academicYearId);
	
	List<ViewStudentFeePaymentDetails> getClassWiseConsolidatedStudentFeeDetails(long custId,long academicYearId,String classSectionIds,String status);
}
