package com.urt.persistence.util.excel;

/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: PersonExcelRow.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Oct 31, 2013      Seshu S            Created
 /********************************************************************/

import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class StudentExcelRow {

	@ExcelField(position = 1)
	private String studentId;
	@ExcelField(position = 2)
	private String admissionNumber;
	@ExcelField(position = 3)
	private String firstName;
	@ExcelField(position = 4)
	private String lastName;
	@ExcelField(position = 5)
	private String fatherName;
	@ExcelField(position = 6)
	private String motherName;
	@ExcelField(position = 7)
	private String mobileNumber;
	@ExcelField(position = 8)
	private String secondaryMobileNumber;
	@ExcelField(position = 9)
	private String studentMobile;
	@ExcelField(position = 10)
	private String parentEmail;
	@ExcelField(position = 11)
	private String studentEmail;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFatherName() {
		return fatherName;
	}



	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}



	public String getMotherName() {
		return motherName;
	}



	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStudentMobile() {
		return studentMobile;
	}


	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}



	public String getParentEmail() {
		return parentEmail;
	}



	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}



	public String getStudentEmail() {
		return studentEmail;
	}



	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	
	@Transient 
	public String getStudentFullName(){
		if(StringFunctions.isNullOrEmpty(this.firstName) && StringFunctions.isNullOrEmpty(this.lastName)){
			return "";
		}else if(StringFunctions.isNotNullOrEmpty(this.firstName) && StringFunctions.isNotNullOrEmpty(this.lastName)){
			return new StringBuffer(this.firstName).append(this.lastName).toString();
		}else if(StringFunctions.isNullOrEmpty(this.firstName)){
			return this.lastName;
		}else
			return this.firstName;
	}

	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}

	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}

}