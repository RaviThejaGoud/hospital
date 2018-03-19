package com.urt.service.manager.interfaces.schoolfee;

import java.util.Date;
import java.util.List;

import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.fee.StudentFeeRefund;
import com.urt.persistence.model.fee.ViewStudentFeeRefundDetails;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.service.manager.interfaces.base.UniversalManager;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="SchoolFeeManager.java.html"><i>View Source</i></a></p>
 */

public interface SchoolFeeManager extends UniversalManager {

	StudentPayment makeStudentFeePayment(String jsonData,long createdUserId,String paymentType,String useExcessPayment,String futureFeePaymentStatus,long classId,long classSectionId,StudentPayment studentPayment);
	void createExcessPayment(long accountId,double excessAmount,long paymentId,long usedPaymentId);
	StudentPayment generateStudentInvoicetermWise(List<ViewStudentFeePaymentDetails> feeList,long userId,double payableAmount,double discountAmount,StudentPayment studentPayment);
	void sendNotificationForFee(long classId, long categotyId, long feeSettingId);
	//void generateChallanaStudentFeePayment(String jsonData,long createdUserId,String paymentType,String useExcessPayment,String futureFeePaymentStatus,long classId,long classSectionId,StudentPayment studentPayment);
	void chashBookPaymentEntry(StudentPayment studentPayment);
	List<ViewStudentFeeRefundDetails> getFeeRefundStudentsList(long userCustId, long userAcademicYearId);
	void saveStudentFeerefund(StudentFeeRefund studentFeeRefund, long studentId, long userCustId,AcademicYear academicYear, long createdUserId);
	int validatePaymentInvoiceNumber(Customer customer,AcademicYear academicYear,String invoiceNumber);
}
