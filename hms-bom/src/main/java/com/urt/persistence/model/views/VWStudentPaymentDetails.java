/********************************************************************
 * HYNIVA (C) 2017
 * All Rights Reserved 
 *
 * File: VWStudentPaymentDetails.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   August 22, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.urt.persistence.model.views;

import java.io.Serializable;
import java.util.Date;



public class VWStudentPaymentDetails implements  Serializable,Cloneable,Comparable<Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long studentPaymentId;
	private Long invoiceNumber;
	private Date paidDate;
	private Double paidAmount;
	private Double discountAmount;
	private Double dueAmount;
	private String paymentType;
	private Long studentId;
	private String bankName;
	private String branchName;
	private String instrumentNumber;
	private Date instrumentDate;
	private String deleteStatus;
	private String deleteCause;
	private Long studentFeePaidDetailsId;
	private Long spd_PaymentAmount;
	private Double spd_DiscountAmount;
	private Long spd_ClassFeeId;
	private Long excessPaymentId;
	private Double excessAmount;
	private Boolean isUsed;
	private Long usedPaymentId;
	private Long spd_TransportFeeId;
	private Long dpPaymentDetailsId;
	private Long CP_challanaId;
	private Long CP_challanaNumber;
	private String CP_deleteStatus;
	private Date CP_challanaDate;
	private Date CP_receivedDate;
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the studentPaymentId
	 */
	public Long getStudentPaymentId() {
		return studentPaymentId;
	}

	/**
	 * @param studentPaymentId the studentPaymentId to set
	 */
	public void setStudentPaymentId(Long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}

	/**
	 * @return the invoiceNumber
	 */
	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the paidDate
	 */
	public Date getPaidDate() {
		return paidDate;
	}

	/**
	 * @param paidDate the paidDate to set
	 */
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	/**
	 * @return the paidAmount
	 */
	public Double getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the discountAmount
	 */
	public Double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the dueAmount
	 */
	public Double getDueAmount() {
		return dueAmount;
	}

	/**
	 * @param dueAmount the dueAmount to set
	 */
	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the studentId
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the instrumentNumber
	 */
	public String getInstrumentNumber() {
		return instrumentNumber;
	}

	/**
	 * @param instrumentNumber the instrumentNumber to set
	 */
	public void setInstrumentNumber(String instrumentNumber) {
		this.instrumentNumber = instrumentNumber;
	}

	/**
	 * @return the instrumentDate
	 */
	public Date getInstrumentDate() {
		return instrumentDate;
	}

	/**
	 * @param instrumentDate the instrumentDate to set
	 */
	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}

	/**
	 * @return the deleteStatus
	 */
	public String getDeleteStatus() {
		return deleteStatus;
	}

	/**
	 * @param deleteStatus the deleteStatus to set
	 */
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	/**
	 * @return the deleteCause
	 */
	public String getDeleteCause() {
		return deleteCause;
	}

	/**
	 * @param deleteCause the deleteCause to set
	 */
	public void setDeleteCause(String deleteCause) {
		this.deleteCause = deleteCause;
	}

	/**
	 * @return the studentFeePaidDetailsId
	 */
	public Long getStudentFeePaidDetailsId() {
		return studentFeePaidDetailsId;
	}

	/**
	 * @param studentFeePaidDetailsId the studentFeePaidDetailsId to set
	 */
	public void setStudentFeePaidDetailsId(Long studentFeePaidDetailsId) {
		this.studentFeePaidDetailsId = studentFeePaidDetailsId;
	}

	/**
	 * @return the spd_PaymentAmount
	 */
	public Long getSpd_PaymentAmount() {
		return spd_PaymentAmount;
	}

	/**
	 * @param spd_PaymentAmount the spd_PaymentAmount to set
	 */
	public void setSpd_PaymentAmount(Long spd_PaymentAmount) {
		this.spd_PaymentAmount = spd_PaymentAmount;
	}

	/**
	 * @return the spd_DiscountAmount
	 */
	public Double getSpd_DiscountAmount() {
		return spd_DiscountAmount;
	}

	/**
	 * @param spd_DiscountAmount the spd_DiscountAmount to set
	 */
	public void setSpd_DiscountAmount(Double spd_DiscountAmount) {
		this.spd_DiscountAmount = spd_DiscountAmount;
	}

	/**
	 * @return the spd_ClassFeeId
	 */
	public Long getSpd_ClassFeeId() {
		return spd_ClassFeeId;
	}

	/**
	 * @param spd_ClassFeeId the spd_ClassFeeId to set
	 */
	public void setSpd_ClassFeeId(Long spd_ClassFeeId) {
		this.spd_ClassFeeId = spd_ClassFeeId;
	}

	/**
	 * @return the excessPaymentId
	 */
	public Long getExcessPaymentId() {
		return excessPaymentId;
	}

	/**
	 * @param excessPaymentId the excessPaymentId to set
	 */
	public void setExcessPaymentId(Long excessPaymentId) {
		this.excessPaymentId = excessPaymentId;
	}

	/**
	 * @return the excessAmount
	 */
	public Double getExcessAmount() {
		return excessAmount;
	}

	/**
	 * @param excessAmount the excessAmount to set
	 */
	public void setExcessAmount(Double excessAmount) {
		this.excessAmount = excessAmount;
	}

	/**
	 * @return the isUsed
	 */
	public Boolean getIsUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed the isUsed to set
	 */
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * @return the usedPaymentId
	 */
	public Long getUsedPaymentId() {
		return usedPaymentId;
	}

	/**
	 * @param usedPaymentId the usedPaymentId to set
	 */
	public void setUsedPaymentId(Long usedPaymentId) {
		this.usedPaymentId = usedPaymentId;
	}

	public Long getSpd_TransportFeeId() {
		return spd_TransportFeeId;
	}

	public void setSpd_TransportFeeId(Long spd_TransportFeeId) {
		this.spd_TransportFeeId = spd_TransportFeeId;
	}

	/**
	 * @return the dpPaymentDetailsId
	 */
	public Long getDpPaymentDetailsId() {
		return dpPaymentDetailsId;
	}

	/**
	 * @param dpPaymentDetailsId the dpPaymentDetailsId to set
	 */
	public void setDpPaymentDetailsId(Long dpPaymentDetailsId) {
		this.dpPaymentDetailsId = dpPaymentDetailsId;
	}

	public Long getCP_challanaId() {
		return CP_challanaId;
	}

	public void setCP_challanaId(Long cP_challanaId) {
		CP_challanaId = cP_challanaId;
	}

	public Long getCP_challanaNumber() {
		return CP_challanaNumber;
	}

	public void setCP_challanaNumber(Long cP_challanaNumber) {
		CP_challanaNumber = cP_challanaNumber;
	}

	public String getCP_deleteStatus() {
		return CP_deleteStatus;
	}

	public void setCP_deleteStatus(String cP_deleteStatus) {
		CP_deleteStatus = cP_deleteStatus;
	}

	public Date getCP_challanaDate() {
		return CP_challanaDate;
	}

	public void setCP_challanaDate(Date cP_challanaDate) {
		CP_challanaDate = cP_challanaDate;
	}

	public Date getCP_receivedDate() {
		return CP_receivedDate;
	}

	public void setCP_receivedDate(Date cP_receivedDate) {
		CP_receivedDate = cP_receivedDate;
	}

	
	
	
	
}
