/********************************************************************
 * HYNIVA (C) 2017
 * All Rights Reserved 
 *
 * File: StudentFeePaymentVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   August 22, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo.fee;

import java.util.List;

import org.bouncycastle.asn1.cmp.Challenge;

public class StudentFeePaymentVO {
	
	private Long id;
	private Long studentId;
	private Long invoiceNumber;
	private String paidDate;
	private String paymentType;
	private Double paidAmount;
	private Double discountAmount;
	private Double dueAmount;
	private String bankName;
	private String branchName;
	private String instrumentNumber;
	private String instrumentDate;
	private String deleteStatus;
	private String deleteCause;
	private Long dpPaymentDetailsId;
	private List<PaymentDetailsVO> paymentDetails;
	private ExcessPaymnetVO excessPaymnet;
	private ChallanaPaymentVO challanaPayment;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the paidDate
	 */
	public String getPaidDate() {
		return paidDate;
	}
	/**
	 * @param paidDate the paidDate to set
	 */
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
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
	public String getInstrumentDate() {
		return instrumentDate;
	}
	/**
	 * @param instrumentDate the instrumentDate to set
	 */
	public void setInstrumentDate(String instrumentDate) {
		this.instrumentDate = instrumentDate;
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
	 * @return the paymentDetails
	 */
	public List<PaymentDetailsVO> getPaymentDetails() {
		return paymentDetails;
	}
	/**
	 * @param paymentDetails the paymentDetails to set
	 */
	public void setPaymentDetails(List<PaymentDetailsVO> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	/**
	 * @return the excessPaymnet
	 */
	public ExcessPaymnetVO getExcessPaymnet() {
		return excessPaymnet;
	}
	/**
	 * @param excessPaymnet the excessPaymnet to set
	 */
	public void setExcessPaymnet(ExcessPaymnetVO excessPaymnet) {
		this.excessPaymnet = excessPaymnet;
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
	
	
	public ChallanaPaymentVO getChallanaPayment() {
		return challanaPayment;
	}
	public void setChallanaPayment(ChallanaPaymentVO challanaPayment) {
		this.challanaPayment = challanaPayment;
	}
	
	
	

}
