/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: IssuedItemDetailsVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   Sep 22, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo.sotre;



public class IssuedItemDetailsVO {
	private Long quantity;
	private String recievedBy;
	private Long custId;
	private String issuedDate;
	private String issuerBy;
	private String itemType;
	private String itemCode;
	private String itemName;
	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the recievedBy
	 */
	public String getRecievedBy() {
		return recievedBy;
	}
	/**
	 * @param recievedBy the recievedBy to set
	 */
	public void setRecievedBy(String recievedBy) {
		this.recievedBy = recievedBy;
	}
	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	/**
	 * @return the issuedDate
	 */
	public String getIssuedDate() {
		return issuedDate;
	}
	/**
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	/**
	 * @return the issuerBy
	 */
	public String getIssuerBy() {
		return issuerBy;
	}
	/**
	 * @param issuerBy the issuerBy to set
	 */
	public void setIssuerBy(String issuerBy) {
		this.issuerBy = issuerBy;
	}
	
	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	

}
