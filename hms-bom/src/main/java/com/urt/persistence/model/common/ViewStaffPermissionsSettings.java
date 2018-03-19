package com.urt.persistence.model.common;

/********************************************************************
 * Copyright (C) 2005-06

 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
 / ********************************************************************/
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_staffPermissionsSettings")
public class ViewStaffPermissionsSettings implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;
	private static final int STRING_BUFFER_SIZE = 1024;

	
	    private long custId;
	    private long academicYearId;
	    private String status;
	    private String monthOrYear;
	    private String isRolebased;
	    private long roleId;
	    private int days;
	    private long staffPermissionsSettingsId;
	    private long staffPermissionsDayDetailsId;
	    private String roleDescription;
	    
	    
	    public String getRoleDescription() {
			return roleDescription;
		}

		public void setRoleDescription(String roleDescription) {
			this.roleDescription = roleDescription;
		}

		@Id
	    public long getStaffPermissionsDayDetailsId() {
			return staffPermissionsDayDetailsId;
		}

		public void setStaffPermissionsDayDetailsId(long staffPermissionsDayDetailsId) {
			this.staffPermissionsDayDetailsId = staffPermissionsDayDetailsId;
		}
		
	    public long getStaffPermissionsSettingsId() {
			return staffPermissionsSettingsId;
		}

		public void setStaffPermissionsSettingsId(long staffPermissionsSettingsId) {
			this.staffPermissionsSettingsId = staffPermissionsSettingsId;
		}

		public long getCustId() {
			return custId;
		}

		public void setCustId(long custId) {
			this.custId = custId;
		}

		public long getAcademicYearId() {
			return academicYearId;
		}

		public void setAcademicYearId(long academicYearId) {
			this.academicYearId = academicYearId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMonthOrYear() {
			return monthOrYear;
		}

		public void setMonthOrYear(String monthOrYear) {
			this.monthOrYear = monthOrYear;
		}
 
		public String getIsRolebased() {
			return isRolebased;
		}

		public void setIsRolebased(String isRolebased) {
			this.isRolebased = isRolebased;
		}

		public long getRoleId() {
			return roleId;
		}

		public void setRoleId(long roleId) {
			this.roleId = roleId;
		}

		public int getDays() {
			return days;
		}

		public void setDays(int days) {
			this.days = days;
		}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		 if (this == obj) 
		  return true;
		 else 
		  return false;
	}

	/**
	 * 
	 */
	public ViewStaffPermissionsSettings() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
		buffer.append(academicYearId);
		return buffer.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
