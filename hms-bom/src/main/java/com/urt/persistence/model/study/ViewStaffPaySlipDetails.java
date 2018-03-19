package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewStaffPayrollDetails.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    JUl 17, 2012     Narahari          	Created
/********************************************************************/

@Entity
@Table(name = "vw_staffPaySlipDetails")
public class ViewStaffPaySlipDetails  implements Comparable,Serializable,Cloneable {

     private static final long serialVersionUID = 1L;
     
     private long accountId;
     private long custId;
     private int monthId;
     private String roleName;
     private int yearName;
     private long roleId;
     private String fullName;
     private String mobileNumber;
     private String email;
     private String roleDescription;
     private long paySlipId;
 	 protected Date lastUpdatedDate;
 	 private long staffPaySlipIds;
 	 private String monthName;
 	

 	 
 	 
 	@Id
	public long getStaffPaySlipIds() {
		return staffPaySlipIds;
	}
	public void setStaffPaySlipIds(long staffPaySlipIds) {
		this.staffPaySlipIds = staffPaySlipIds;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public int getMonthId() {
		return monthId;
	}
	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getYearName() {
		return yearName;
	}
	public void setYearName(int yearName) {
		this.yearName = yearName;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public long getPaySlipId() {
		return paySlipId;
	}
	public void setPaySlipId(long paySlipId) {
		this.paySlipId = paySlipId;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	 @Transient
		public String getAccountIdAndClassSectionId() {
			return getMonthId()+"_"+getYearName();
		}
	    
	
}