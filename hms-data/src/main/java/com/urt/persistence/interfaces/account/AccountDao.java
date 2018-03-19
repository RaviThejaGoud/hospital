package com.urt.persistence.interfaces.account;

import java.util.Date;
import java.util.List;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.account.ViewFinAccountDetails;
import com.urt.persistence.model.customer.FineFee;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;

public interface AccountDao extends UniversalDao {

	public List<ViewFinAccountDetails> getFinalReportDetails(long custId,long financialYearId,String fromDate,String endDate,String statementIds);
	
	public List<ViewFinAccountDetails> getAllAccountOpeningBalance(long custId,long financialYearId,String statementIds);
	
	public Date getLastCreatedDate(long webId,String tableName);
	
	public List<ViewFinAccountDetails> getAllAccountOpeningBalanceCashInHand(long custId,long financialYearId,String statementIds);
	
	public List<ViewFinAccountDetails> getSumOfFinalReportDetails(long custId,long financialYearId,String fromDate,String endDate,String statementIds);
	
	public List<FineFee> getOtherFeePaymentDetails(long custId,long academicYearId,long studentId);
	
	public List<ViewStudentFeePaymentDetails> getPreviousYearFeeDefaultersList(long custId,long academicYearId,String toDayDate);
	
	public Object[] getOverAllFeePaymentSummary(long custId,long academicYearId);
	
	public List<ViewStudentFeePaymentDetails> getClassWiseConsolidatedStudentFeeDetails(long custId,long academicYearId,String classSectionIds,String status);
}
