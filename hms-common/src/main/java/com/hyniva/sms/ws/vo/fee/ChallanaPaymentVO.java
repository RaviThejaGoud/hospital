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

import java.util.Date;

public class ChallanaPaymentVO {
	
	private Long id;
	private Long challanaNumber;
	private String deleteStatus;
	private Date challanaDate;
	private Date receivedDate;
	private Long bankId;
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
	public Long getChallanaNumber() {
		return challanaNumber;
	}
	public void setChallanaNumber(Long challanaNumber) {
		this.challanaNumber = challanaNumber;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public Date getChallanaDate() {
		return challanaDate;
	}
	public void setChallanaDate(Date challanaDate) {
		this.challanaDate = challanaDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Long getBankId() {
		return bankId;
	}
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	
	
}
