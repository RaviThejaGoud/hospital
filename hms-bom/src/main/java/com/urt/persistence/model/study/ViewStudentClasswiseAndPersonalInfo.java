package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewGroupType.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Narahari Created /
 ********************************************************************/

@Entity
@Table(name = "vw_studentClasswiseAndPersonalInfo")
public class ViewStudentClasswiseAndPersonalInfo implements Serializable,
		Cloneable, Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	private static final long serialVersionUID = 1L;
	protected long studId;
	protected long classSectionId;
	protected String status;
	protected long custId;
	protected int rollNumber;
	protected long academicYearId;
	protected long accountId;
	protected long bedId;
	protected long classId;
	protected String firstName;
	protected String lastName;
	protected long castId;
	protected long subCastId;
	protected long religionId;
	protected String className;
	protected String section;
	protected boolean higherStandard;
	protected String fullName;
	protected String castName;
	protected String admissionNumber;
	protected String registerNumber;
	protected boolean accountExpired;
	private String studDiscontinueDesc;
	protected String dateOfJoining;
	protected String dateOfBirth;
	private String fatherName;
	private String motherName;
	private String motherOccupation;
	private String parentEmail;
	protected Double annualIncome;
	protected String bloodGroup;
	private String mobileNumber;
	protected String phoneNumber;
	private String nationality;
	private long motherToungId;
	private String streetName;
	private String city;
	private String postalCode;
	private String identification1;
	private String identification2;
	private String subCastName; 
	private String religion;
	private String motherToung;
	protected String totalWorkingDaysCount;
	protected String presentDaysCount;
	private boolean joinedThroughAdmissions;
	private long boardingPointId;
	private String transportMode;
	private long vehicleAcademicDetailsId;
	private int classSortingOrder;
	private long parentId;
	
	
	
	
	
	
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the classSortingOrder
	 */
	public int getClassSortingOrder() {
		return classSortingOrder;
	}
	/**
	 * @param classSortingOrder the classSortingOrder to set
	 */
	public void setClassSortingOrder(int classSortingOrder) {
		this.classSortingOrder = classSortingOrder;
	}
	/**
	 * @return the boardingPointId
	 */
	public long getBoardingPointId() {
		return boardingPointId;
	}
	/**
	 * @param boardingPointId the boardingPointId to set
	 */
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	/**
	 * @return the transportMode
	 */
	public String getTransportMode() {
		return transportMode;
	}
	/**
	 * @param transportMode the transportMode to set
	 */
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	/**
	 * @return the vehicleAcademicDetailsId
	 */
	public long getVehicleAcademicDetailsId() {
		return vehicleAcademicDetailsId;
	}
	/**
	 * @param vehicleAcademicDetailsId the vehicleAcademicDetailsId to set
	 */
	public void setVehicleAcademicDetailsId(long vehicleAcademicDetailsId) {
		this.vehicleAcademicDetailsId = vehicleAcademicDetailsId;
	}
	@Column(name = "joinedThroughAdmissions", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}
	public void setJoinedThroughAdmissions(boolean joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
	}

	public ViewStudentClasswiseAndPersonalInfo() {
		super();
	}

	public ViewStudentClasswiseAndPersonalInfo(long accountId, long custId,
			long academicYearId, String status,
			String firstName, String lastName) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.academicYearId = academicYearId;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Transient
	public String getTotalWorkingDaysCount() {
		return totalWorkingDaysCount;
	}

	public void setTotalWorkingDaysCount(String totalWorkingDaysCount) {
		this.totalWorkingDaysCount = totalWorkingDaysCount;
	}

	@Transient
	public String getPresentDaysCount() {
		return presentDaysCount;
	}

	public void setPresentDaysCount(String presentDaysCount) {
		this.presentDaysCount = presentDaysCount;
	}

	@Id
	@Column(name="studId",nullable=false,updatable=false,unique=true)
	public long getStudId() {
		return studId;
	}

	public void setStudId(long studId) {
		this.studId = studId;
	}

	public long getClassSectionId() {
		return classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getBedId() {
		return bedId;
	}

	public void setBedId(long bedId) {
		this.bedId = bedId;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
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

	public long getCastId() {
		return castId;
	}

	public void setCastId(long castId) {
		this.castId = castId;
	}

	public long getSubCastId() {
		return subCastId;
	}

	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}

	public long getReligionId() {
		return religionId;
	}

	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public boolean isHigherStandard() {
		return higherStandard;
	}

	public void setHigherStandard(boolean higherStandard) {
		this.higherStandard = higherStandard;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public String getStudDiscontinueDesc() {
		return studDiscontinueDesc;
	}

	public void setStudDiscontinueDesc(String studDiscontinueDesc) {
		this.studDiscontinueDesc = studDiscontinueDesc;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public Double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public long getMotherToungId() {
		return motherToungId;
	}

	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getIdentification1() {
		return identification1;
	}

	public void setIdentification1(String identification1) {
		this.identification1 = identification1;
	}

	public String getIdentification2() {
		return identification2;
	}

	public void setIdentification2(String identification2) {
		this.identification2 = identification2;
	}

	public String getSubCastName() {
		return subCastName;
	}

	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getMotherToung() {
		return motherToung;
	}

	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentClasswiseAndPersonalInfo))
			return false;
		return false;

	}

	@Override
	public int hashCode() {
		int result = 0;

		return result;
	}


	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		buffer.append(" Status : ").append(getStatus());
		return buffer.toString();
	}

	@Override
	public int compareTo(Object object) {
		ViewStudentClasswiseAndPersonalInfo myClass = (ViewStudentClasswiseAndPersonalInfo) object;
		if(ObjectFunctions.isNullOrEmpty(myClass))
			return 0;
		else if(StringFunctions.isNotNullOrEmpty(myClass.getRegisterNumber()) && StringFunctions.isNotNullOrEmpty(this.getRegisterNumber())){
			return this.getRegisterNumber().compareToIgnoreCase(myClass.getRegisterNumber());
		}else
			return this.getPersonFullName().compareToIgnoreCase(myClass.getPersonFullName());	
	}
	
	@Transient
	public String getPersonFullName() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getFirstName())) {
			ret.append(getFirstName());
		}
		/*
		 * if (!StringFunctions.isNullOrEmpty(getFirstName())) {
		 * ret.append(" "); ret.append(getMiddleName()); }
		 */
		if (!StringFunctions.isNullOrEmpty(getLastName())) {
			ret.append(" ");
			ret.append(getLastName());
		}

		return ret.toString().trim();
	}
	
	@Transient
	public String getStudentFullAddress(){
		StringBuffer fullAddress = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(getStreetName())){
			fullAddress.append(getStreetName());
		}
		if(StringFunctions.isNotNullOrEmpty(getCity()) || StringFunctions.isNotNullOrEmpty(getPostalCode())){
			if(StringFunctions.isNotNullOrEmpty(getCity()) && StringFunctions.isNotNullOrEmpty(getPostalCode())){
				if(fullAddress.length() > 0)
					fullAddress.append(", ");
				fullAddress.append(getCity()+" - "+getPostalCode()+".");
			}else if(StringFunctions.isNotNullOrEmpty(getCity())){
				if(fullAddress.length() > 0)
					fullAddress.append(", ");
				fullAddress.append(getCity()+".");
			}else if(StringFunctions.isNotNullOrEmpty(getPostalCode())){
				if(fullAddress.length() > 0)
					fullAddress.append(", ");
				fullAddress.append(getPostalCode()+".");
			}
		}
		return fullAddress.toString();
	}
	
	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
		}
		return getClassName();
	}
	
}
