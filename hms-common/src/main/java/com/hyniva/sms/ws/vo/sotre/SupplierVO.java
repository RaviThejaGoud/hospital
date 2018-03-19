/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: SupplierVO.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   Dec 06, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.ws.vo.sotre;


public class SupplierVO {

	private Long supplierId;
	private String supplierName;
	private Long custId;
	private String email;
	private String mobileNumber;
	private String itemsSupplied;
	private String streetName;
	private String city;
	private String state;
	private String country;
	private String postalcode;
	private String supplierAddress;
	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
	 * @return the itemsSupplied
	 */
	public String getItemsSupplied() {
		return itemsSupplied;
	}
	/**
	 * @param itemsSupplied the itemsSupplied to set
	 */
	public void setItemsSupplied(String itemsSupplied) {
		this.itemsSupplied = itemsSupplied;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}
	/**
	 * @param postalcode the postalcode to set
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	/**
	 * @return the supplierAddress
	 */
	public String getSupplierAddress() {
		
		String address="";
		
		if(null!=this.getStreetName()){
			address =this.getStreetName();
		}
		if(null!=this.getCity()){
			address =address.concat(",").concat(this.getCity());
		}
		if(null!=this.getState()){
			address =address.concat(",").concat(this.getState());
		}
		if(null!=this.getCountry()){
			address =address.concat(",").concat(this.getCountry());
		}
		if(null!=this.getPostalcode()){
			address =address.concat(" - ").concat(this.getPostalcode());
		}
		
		return address;
	}
	/**
	 * @param supplierAddress the supplierAddress to set
	 */
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	/**
	 * @return the supplierId
	 */
	public Long getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}
	
	
	
}
