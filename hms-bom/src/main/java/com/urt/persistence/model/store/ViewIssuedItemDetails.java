/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: ViewIssuedItemDetails.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   Dec 02, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.urt.persistence.model.store;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_issuedItemDetails")
public class ViewIssuedItemDetails implements Serializable,Cloneable, Comparable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long storeId;
	private String storeName;
	private String storeKeeperName;
	private Long custId;
	private String itemCode;
	private String itemName;
	private String itemType;
	private Long issuedQuantity;
	private String issuedTo;
	private Date issuedDate;
	private String issuedBy;
	
	/**
	 * @return the id
	 */
	@Id
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
	 * @return the storeId
	 */
	public Long getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}
	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * @return the storeKeeperName
	 */
	public String getStoreKeeperName() {
		return storeKeeperName;
	}
	/**
	 * @param storeKeeperName the storeKeeperName to set
	 */
	public void setStoreKeeperName(String storeKeeperName) {
		this.storeKeeperName = storeKeeperName;
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
	/**
	 * @return the issuedQuantity
	 */
	public Long getIssuedQuantity() {
		return issuedQuantity;
	}
	/**
	 * @param issuedQuantity the issuedQuantity to set
	 */
	public void setIssuedQuantity(Long issuedQuantity) {
		this.issuedQuantity = issuedQuantity;
	}
	/**
	 * @return the issuedTo
	 */
	public String getIssuedTo() {
		return issuedTo;
	}
	/**
	 * @param issuedTo the issuedTo to set
	 */
	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}
	/**
	 * @return the issuedDate
	 */
	public Date getIssuedDate() {
		return issuedDate;
	}
	/**
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	/**
	 * @return the issuedBy
	 */
	public String getIssuedBy() {
		return issuedBy;
	}
	/**
	 * @param issuedBy the issuedBy to set
	 */
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
