package com.urt.persistence.util.excel;
/********************************************************************
 * Copyright (C) 2011-14

 * IFS
 * All Rights Reserved 
 *
 * File: StaffExcelRow.java
 ********************************************************************
 *
 *  Ver   Date              Author	              Description
 *  ====  ===========       ============     ==================
 *  1.0   Oct 31, 2013      Seshu S          Created
/ ********************************************************************/
import org.apache.commons.lang.builder.EqualsBuilder;

import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;

@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class StaffExcelRow {
	
	@ExcelField(position = 2)
	private String roleDescription;
	@ExcelField(position = 11)
	private String qualification1;
	@ExcelField(position = 1)
	private String staffIdStr;
	@ExcelField(position = 12)
	private String salary;
	@MappedExcelObject
	private UserExcelRow account;
	@ExcelField(position = 40)
	private String staffTypeStatus;
	@ExcelField(position = 42)
	private String outSideSchoolDuty;
	@ExcelField(position = 43)
	private String schoolMess;
	@ExcelField(position = 45)
	private String staffGrade;
	
	
	
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getStaffGrade() {
		return staffGrade;
	}

	public void setStaffGrade(String staffGrade) {
		this.staffGrade = staffGrade;
	}

	public String getSchoolMess() {
		return schoolMess;
	}

	public void setSchoolMess(String schoolMess) {
		this.schoolMess = schoolMess;
	}

	public String getOutSideSchoolDuty() {
		return outSideSchoolDuty;
	}

	public void setOutSideSchoolDuty(String outSideSchoolDuty) {
		this.outSideSchoolDuty = outSideSchoolDuty;
	}

	public String getStaffTypeStatus() {
		return staffTypeStatus;
	}

	public void setStaffTypeStatus(String staffTypeStatus) {
		this.staffTypeStatus = staffTypeStatus;
	}


	/**
	 * @return the staffIdStr
	 */
	public String getStaffIdStr() {
		return staffIdStr;
	}

	/**
	 * @param staffIdStr the staffIdStr to set
	 */
	public void setStaffIdStr(String staffIdStr) {
		this.staffIdStr = staffIdStr;
	}


	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}



	public UserExcelRow getAccount() {
		return account;
	}

	public void setAccount(UserExcelRow account) {
		this.account = account;
	}


    
	
	public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}



	/**
	 * Constructor for Student.
	 */
	public StaffExcelRow() {
		super();
	}
	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (null == obj || !(obj instanceof StaffExcelRow)) {
			return false;
		} else {

			StaffExcelRow staff = (StaffExcelRow) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder
				.append(
					account,
					staff.getAccount())
				.append(qualification1, staff.getQualification1());
				

			return builder.isEquals();
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}