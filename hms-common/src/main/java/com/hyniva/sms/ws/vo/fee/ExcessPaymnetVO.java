/********************************************************************
 * HYNIVA (C) 2017
 * All Rights Reserved 
 *
 * File: ExcessPaymnetVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   August 22, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo.fee;

public class ExcessPaymnetVO {
	
	private Long id;
	private Double amount;
	private String isUsed;
	private Long usedPaymnetId;
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
	 * @return the isUsed
	 */
	public String getIsUsed() {
		return isUsed;
	}
	/**
	 * @param isUsed the isUsed to set
	 */
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	/**
	 * @return the usedPaymnetId
	 */
	public Long getUsedPaymnetId() {
		return usedPaymnetId;
	}
	/**
	 * @param usedPaymnetId the usedPaymnetId to set
	 */
	public void setUsedPaymnetId(Long usedPaymnetId) {
		this.usedPaymnetId = usedPaymnetId;
	}
	
	

}
