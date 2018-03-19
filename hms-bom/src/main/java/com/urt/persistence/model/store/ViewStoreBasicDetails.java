/********************************************************************
 * Copyright (C) 2018
 * HYNIVA
 * All Rights Reserved 
 *
 * File: ViewSupplierDetails.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   Jan 09, 2018     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.urt.persistence.model.store;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_storeBasicDetails")
public class ViewStoreBasicDetails implements Serializable,Cloneable, Comparable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long storeId;
	private String storeName;
	private String storeKeeperName;
	private Long custId;
	private String storeAddress;
	private Long accountId;
	private String itemsAdded;
	/**
	 * @return the storeId
	 */
	@Id
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
	 * @return the storeAddress
	 */
	public String getStoreAddress() {
		return storeAddress;
	}
	/**
	 * @param storeAddress the storeAddress to set
	 */
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the itemsAdded
	 */
	public String getItemsAdded() {
		return itemsAdded;
	}
	/**
	 * @param itemsAdded the itemsAdded to set
	 */
	public void setItemsAdded(String itemsAdded) {
		this.itemsAdded = itemsAdded;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
}
