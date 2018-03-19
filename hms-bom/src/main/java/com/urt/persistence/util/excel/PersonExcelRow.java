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

import java.util.Date;

import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class PersonExcelRow {

	@ExcelField(position = 3)
	private String firstName;
	@ExcelField(position = 4)
	private String lastName;
	@ExcelField(position = 5)
	private String initial;
	@ExcelField(position = 6)
	private Date dateOfBirth;
	@ExcelField(position = 7)
	private String gender ;
	@ExcelField(position = 8)
	private String maritalStatus;
	@ExcelField(position = 9)
	private String experience;
	@ExcelField(position = 10)
	private Date dateOfJoining;
	@ExcelField(position = 14)
	private String mobileNumber;
	@ExcelField(position = 19)
	private String bloodGroup;
	@ExcelField(position = 20)
	private String panNumber;
	@ExcelField(position = 21)
	private String gpfNumber;
	@ExcelField(position = 22)
	private String officeNumber;
	@ExcelField(position = 23)
	private String phoneNumber;
	@ExcelField(position = 24)
	private String designation;
	@ExcelField(position = 25)
	private String religionName;
	@ExcelField(position = 26)
	private String motherToungName;
	@ExcelField(position = 27)
	private String nationality;
	@ExcelField(position = 28)
	private String castName;
	@ExcelField(position = 29)
	private String subCastName;
	@ExcelField(position = 30)
	private String bankName;
	@ExcelField(position = 31)
	private String bankAccountNumber;
	@ExcelField(position = 32)
	private String bankBranchName;
	@ExcelField(position = 33)
	private String familyDoctor;
	@ExcelField(position = 34)
	private String prefferedHospital;
	@ExcelField(position = 37)
	private String aadharNumber;
	@ExcelField(position = 38)
	private String passportNumber;
	@ExcelField(position = 39)
	private String ifscCode;
	private long religionId;
	private long motherToungId;
	private long castId;
	private long subCastId;
	@ExcelField(position = 41)
	private String staffUniqueNumber;
	
	@ExcelField(position = 44)
	private String salaryPaymentMode;
	@ExcelField(position = 46)
	private String staffLocation;
	
	@ExcelField(position = 47)
	private String fatherName;
	
	@ExcelField(position = 48)
	private String fatherContactNumber;
	

	
	public String getSalaryPaymentMode() {
		return salaryPaymentMode;
	}

	public void setSalaryPaymentMode(String salaryPaymentMode) {
		this.salaryPaymentMode = salaryPaymentMode;
	}

	public String getStaffLocation() {
		return staffLocation;
	}

	public void setStaffLocation(String staffLocation) {
		this.staffLocation = staffLocation;
	}

	public String getStaffUniqueNumber() {
		return staffUniqueNumber;
	}

	public void setStaffUniqueNumber(String staffUniqueNumber) {
		this.staffUniqueNumber = staffUniqueNumber;
	}
	
	
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	/**
	 * @return the castId
	 */
	public long getCastId() {
		return castId;
	}
	/**
	 * @param castId the castId to set
	 */
	public void setCastId(long castId) {
		this.castId = castId;
	}
	/**
	 * @return the subCastId
	 */
	public long getSubCastId() {
		return subCastId;
	}
	/**
	 * @param subCastId the subCastId to set
	 */
	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}
	/**
	 * @return the motherToungId
	 */
	public long getMotherToungId() {
		return motherToungId;
	}
	/**
	 * @param motherToungId the motherToungId to set
	 */
	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}
	/**
	 * @return the religionId
	 */
	public long getReligionId() {
		return religionId;
	}
	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the initial
	 */
	public String getInitial() {
		return initial;
	}
	/**
	 * @param initial the initial to set
	 */
	public void setInitial(String initial) {
		this.initial = initial;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * @return the dateOfJoining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
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
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}
	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	/**
	 * @return the panNumber
	 */
	public String getPanNumber() {
		return panNumber;
	}
	/**
	 * @param panNumber the panNumber to set
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	/**
	 * @return the gpfNumber
	 */
	public String getGpfNumber() {
		return gpfNumber;
	}
	/**
	 * @param gpfNumber the gpfNumber to set
	 */
	public void setGpfNumber(String gpfNumber) {
		this.gpfNumber = gpfNumber;
	}
	/**
	 * @return the officeNumber
	 */
	public String getOfficeNumber() {
		return officeNumber;
	}
	/**
	 * @param officeNumber the officeNumber to set
	 */
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the religionName
	 */
	public String getReligionName() {
		return religionName;
	}
	/**
	 * @param religionName the religionName to set
	 */
	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}
	/**
	 * @return the motherToungName
	 */
	public String getMotherToungName() {
		return motherToungName;
	}
	/**
	 * @param motherToungName the motherToungName to set
	 */
	public void setMotherToungName(String motherToungName) {
		this.motherToungName = motherToungName;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the castName
	 */
	public String getCastName() {
		return castName;
	}
	/**
	 * @param castName the castName to set
	 */
	public void setCastName(String castName) {
		this.castName = castName;
	}
	/**
	 * @return the subCastName
	 */
	public String getSubCastName() {
		return subCastName;
	}
	/**
	 * @param subCastName the subCastName to set
	 */
	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	/**
	 * @return the bankBranchName
	 */
	public String getBankBranchName() {
		return bankBranchName;
	}
	/**
	 * @param bankBranchName the bankBranchName to set
	 */
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	/**
	 * @return the familyDoctor
	 */
	public String getFamilyDoctor() {
		return familyDoctor;
	}
	/**
	 * @param familyDoctor the familyDoctor to set
	 */
	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}
	/**
	 * @return the prefferedHospital
	 */
	public String getPrefferedHospital() {
		return prefferedHospital;
	}
	/**
	 * @param prefferedHospital the prefferedHospital to set
	 */
	public void setPrefferedHospital(String prefferedHospital) {
		this.prefferedHospital = prefferedHospital;
	}
	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}
	/**
	 * @param aadharNumber the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	/**
	 * @return the passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}
	/**
	 * @param passportNumber the passportNumber to set
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	//This is used for staff username creation
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

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the fatherContactNumber
	 */
	public String getFatherContactNumber() {
		return fatherContactNumber;
	}

	/**
	 * @param fatherContactNumber the fatherContactNumber to set
	 */
	public void setFatherContactNumber(String fatherContactNumber) {
		this.fatherContactNumber = fatherContactNumber;
	}
}