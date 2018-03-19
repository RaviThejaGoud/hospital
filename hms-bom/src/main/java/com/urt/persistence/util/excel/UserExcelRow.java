package com.urt.persistence.util.excel;

import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;

/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: UserExcelRow.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Oct 31, 2013      Seshu S            Created
 /********************************************************************/
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class UserExcelRow {

	@MappedExcelObject
	private PersonExcelRow person;
	@MappedExcelObject
	private AddressExcelRow primaryAddress;
	@ExcelField(position = 35)
	private String bioMetricId;
	@ExcelField(position = 36)
	private String staffNumber;
	protected String username;
    protected String password;
    protected String secondaryUsername;
	
    
	public String getSecondaryUsername() {
		return secondaryUsername;
	}
	public void setSecondaryUsername(String secondaryUsername) {
		this.secondaryUsername = secondaryUsername;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the person
	 */
	public PersonExcelRow getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(PersonExcelRow person) {
		this.person = person;
	}
	/**
	 * @return the primaryAddress
	 */
	public AddressExcelRow getPrimaryAddress() {
		return primaryAddress;
	}
	/**
	 * @param primaryAddress the primaryAddress to set
	 */
	public void setPrimaryAddress(AddressExcelRow primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	/**
	 * @return the bioMetricId
	 */
	public String getBioMetricId() {
		return bioMetricId;
	}
	/**
	 * @param bioMetricId the bioMetricId to set
	 */
	public void setBioMetricId(String bioMetricId) {
		this.bioMetricId = bioMetricId;
	}
	/**
	 * @return the staffNumber
	 */
	public String getStaffNumber() {
		return staffNumber;
	}
	/**
	 * @param staffNumber the staffNumber to set
	 */
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	
	
}
