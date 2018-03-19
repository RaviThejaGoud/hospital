/********************************************************************
 * HYNIVA (C) 2017
 * All Rights Reserved 
 *
 * File: PaymentDetailsVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   August 22, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/

package com.hyniva.sms.ws.vo.fee;

public class PaymentDetailsVO {

	private Long id;
	private Double amount;
	private Double discountAmount;
	private Long classFeeId;
	private Long transportFeeId;
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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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
	 * @return the classFeeId
	 */
	public Long getClassFeeId() {
		return classFeeId;
	}
	/**
	 * @param classFeeId the classFeeId to set
	 */
	public void setClassFeeId(Long classFeeId) {
		this.classFeeId = classFeeId;
	}
	public Long getTransportFeeId() {
		return transportFeeId;
	}
	public void setTransportFeeId(Long transportFeeId) {
		this.transportFeeId = transportFeeId;
	}
	
}
