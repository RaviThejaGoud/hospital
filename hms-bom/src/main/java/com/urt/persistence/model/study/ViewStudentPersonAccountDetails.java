package com.urt.persistence.model.study;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;

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
@Table(name = "vw_studentDetails")
public class ViewStudentPersonAccountDetails implements Serializable,
		Cloneable, Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long accountId;
	private long personId;
	private long pAddressId;
	private long custId;
	private String username;
	private String sharePrivacy;
	private String userOnlineNow;
	private long academicYearId;
	private String status;
	private String transportMode;
	private long studentId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;
	private String motherName;
	// private String lastUpdatedBy;
	protected Date lastUpdatedDate;
	protected Date createdDate;
	protected String addressLine1;
	protected String addressLine2;
	protected String city;
	protected String state;
	protected String postalCode;
	protected String mobileNumber;
	protected String phoneNumber;
	protected String mothersMaidenName;
	protected String bloodGroup;
	protected Date dateOfBirth;
	/* protected long classId; */
	protected String rollNumber;
	protected String className;
	protected String parentId;
	private long roleId;
	private String roleName;
	private String registerNumber;
	private String custEmail;
	private String contactNumber;
	private String rationCardNumber;
	private String communityNumber;
	/*
	 * private long presentDays; private long workingDays;
	 */
	private String organizationName;
	private String joinedThroughAdmissions;

	protected String subjectName;
	protected String anyString;
	protected String typeOfExam;
	protected String maxMarks;
	protected String minMarks;
	protected String obtainedMarks;

	private String leaveCount;
	private String parentEmail;
	private String imageName;
	private String thumbNail;
	private String imagePath;
	private long imageId;
	private Date dateOfJoining;
	private String summary;
	private String section;
	private String todayDateDOB;
	private String roleDescription;;
	protected long classNameClassId;

	protected char leaveRequest;
	protected boolean present;
	protected long classSectionId;
	protected long categoryId;
	protected boolean accountExpired;
	protected String description;
	protected String admissionNumber;
	protected String gender;

	protected String castName;
	protected String subCastName;
	protected String fatherOccupation;
	protected String motherOccupation;
	protected long annualIncome;
	protected String religion;
	protected String streetName;
	protected long castId;
	protected long subCastId;
	protected String stateName;

	protected String organization;
	protected String educationalDistrict;
	protected String revenueDistrict;
	protected String customerShortName;
	/* protected long serialNumber = 0; */
	protected String academicYear;
	protected String nationality;
	protected String identification1;
	protected String identification2;
	protected long motherToungId;
	protected int filledSeats;
	private long mediumId;
	protected int sectionCapacity;
	protected long religionId;
	protected boolean higherStandard;

	protected String recognisedBy;
	protected String boardOfEducation;
	protected long bedId;
	protected String medium;
	protected long boardingPointId;
	protected String custImageName;
	protected String custImagePath;
	protected String custThumbNail;
	protected String customerFullAddress;
	protected String parentFullAddress;
	private String visionL;
	private String visionR;
	private String teeth;
	private String oralHygiene;
	private double height;
	private double weight;
	private String familyDoctor;
	private String prefferedHospital;
	private long sortingOrder;
	private String sslcNumber;
	private String tmrNumber;
	private String classNameAndSection;
	private String schoolCode;
	private String affiliationNumber;
	private String mediumOfStudy;
	private String classJoined;
	private String motherToung;
	private String scholarShipInfo;
	private Date relievingDate;
	private boolean phId;
	private boolean transportModuleStatus;
	private boolean studentTransportStatus;
	private String vehicleName;
	private String fullName;
	private long vehicleAcademicDetailsId;
	private long routeId;
	private String categoryName;
	private String hostelMode;
	private Date tcAppliedDate;
	private Date tcIssuedDate;
	private String placeOfBirth;
	private String lastSchool;
	private String studentEmail;
	private String studentMobile;
	private String studentUniqueNumber;
	private String aadharNumber;
	private String outSideSchoolDuty;
	private double committedFee;
	private String goals;
	private String strengths;
	private String interestsAndHobbies;
	private String responsibilities;
	private String achievements;
	private String remarks;
	private String birthOfStudentYear;
	private String birthOfStudentMonth;
	private double height2;
	private double weight2;
	private Date schoolReOpenDate;
	private String promoteToClass;
	private String boardingPointName;
	private String vehicleNumber;
	private double boardingPointFeeAmount;
	private String routeName;
	protected String secondaryMobileNumber;
	private String addressType;
	protected String anotherMobileNumber;
	protected String anotherSecondaryMobileNumber;
	protected String anotherParentEmail;
	protected String schoolMess;
	protected String barcode;
	protected String ptaStatus;
	protected String rteStatus;
	protected String bplStatus;
	protected String physicallyHandicappedDesc;
	protected String barcodeStatus;
	private String principalDigitalSignaturePath;
	private String syllabusTypeName;
	private String feeRefundStatus;
	private String houseType;
	protected String studentBankId;
	private String stsNumber;
		
	
	public String getSyllabusTypeName() {
		return syllabusTypeName;
	}
	public void setSyllabusTypeName(String syllabusTypeName) {
		this.syllabusTypeName = syllabusTypeName;
	}
	public String getPrincipalDigitalSignaturePath() {
		return principalDigitalSignaturePath;
	}
	public void setPrincipalDigitalSignaturePath(
			String principalDigitalSignaturePath) {
		this.principalDigitalSignaturePath = principalDigitalSignaturePath;
	}
	public String getBarcodeStatus() {
		return barcodeStatus;
	}
	public void setBarcodeStatus(String barcodeStatus) {
		this.barcodeStatus = barcodeStatus;
	}
	public String getPhysicallyHandicappedDesc() {
		return physicallyHandicappedDesc;
	}
	public void setPhysicallyHandicappedDesc(String physicallyHandicappedDesc) {
		this.physicallyHandicappedDesc = physicallyHandicappedDesc;
	}
	public String getRteStatus() {
		return rteStatus;
	}
	public void setRteStatus(String rteStatus) {
		this.rteStatus = rteStatus;
	}
	public String getBplStatus() {
		return bplStatus;
	}
	public void setBplStatus(String bplStatus) {
		this.bplStatus = bplStatus;
	}
	public String getPtaStatus() {
		return ptaStatus;
	}
	public void setPtaStatus(String ptaStatus) {
		this.ptaStatus = ptaStatus;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	@Column(name = "schoolMess", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getSchoolMess() {
		return schoolMess;
	}
	public void setSchoolMess(String schoolMess) {
		this.schoolMess = schoolMess;
	}
	public String getAnotherMobileNumber() {
		return anotherMobileNumber;
	}
	public void setAnotherMobileNumber(String anotherMobileNumber) {
		this.anotherMobileNumber = anotherMobileNumber;
	}
	public String getAnotherSecondaryMobileNumber() {
		return anotherSecondaryMobileNumber;
	}
	public void setAnotherSecondaryMobileNumber(String anotherSecondaryMobileNumber) {
		this.anotherSecondaryMobileNumber = anotherSecondaryMobileNumber;
	}
	public String getAnotherParentEmail() {
		return anotherParentEmail;
	}
	public void setAnotherParentEmail(String anotherParentEmail) {
		this.anotherParentEmail = anotherParentEmail;
	}
 	@Column(name = "addressType", nullable = false, length = 1,columnDefinition="char(1) default 'R'")
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;	}
	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public double getBoardingPointFeeAmount() {
		return boardingPointFeeAmount;
	}
	public void setBoardingPointFeeAmount(double boardingPointFeeAmount) {
		this.boardingPointFeeAmount = boardingPointFeeAmount;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getBoardingPointName() {
		return boardingPointName;
	}
	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}
	public long getpAddressId() {
		return pAddressId;
	}
	public void setpAddressId(long pAddressId) {
		this.pAddressId = pAddressId;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	/**
	 * @return the schoolReOpenDate
	 */
	public Date getSchoolReOpenDate() {
		return schoolReOpenDate;
	}
	/**
	 * @param schoolReOpenDate the schoolReOpenDate to set
	 */
	public void setSchoolReOpenDate(Date schoolReOpenDate) {
		this.schoolReOpenDate = schoolReOpenDate;
	}
	
	@Transient
	public String getNextAcademicStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getSchoolReOpenDate());
	}
	@Transient
	public String getNextAcademicStartDateDMY() {		
		 return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, getSchoolReOpenDate());
	}
	/**
	 * @return the goals
	 */
	public String getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}

	/**
	 * @return the strengths
	 */
	public String getStrengths() {
		return strengths;
	}

	/**
	 * @param strengths the strengths to set
	 */
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}

	/**
	 * @return the interestsAndHobbies
	 */
	public String getInterestsAndHobbies() {
		return interestsAndHobbies;
	}

	/**
	 * @param interestsAndHobbies the interestsAndHobbies to set
	 */
	public void setInterestsAndHobbies(String interestsAndHobbies) {
		this.interestsAndHobbies = interestsAndHobbies;
	}

	/**
	 * @return the responsibilities
	 */
	public String getResponsibilities() {
		return responsibilities;
	}

	/**
	 * @param responsibilities the responsibilities to set
	 */
	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	/**
	 * @return the achievements
	 */
	public String getAchievements() {
		return achievements;
	}

	/**
	 * @param achievements the achievements to set
	 */
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getCommittedFee() {
		return committedFee;
	}

	public void setCommittedFee(double committedFee) {
		this.committedFee = committedFee;
	}

	public String getOutSideSchoolDuty() {
		return outSideSchoolDuty;
	}

	public void setOutSideSchoolDuty(String outSideSchoolDuty) {
		this.outSideSchoolDuty = outSideSchoolDuty;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getStudentUniqueNumber() {
		return studentUniqueNumber;
	}

	public void setStudentUniqueNumber(String studentUniqueNumber) {
		this.studentUniqueNumber = studentUniqueNumber;
	}

	public String getStudentMobile() {
		return studentMobile;
	}

	/**
	 * @param studentMobile
	 *            the studentMobile to set
	 */
	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}

	/**
	 * @return the studentEmail
	 */
	public String getStudentEmail() {
		return studentEmail;
	}

	/**
	 * @param studentEmail
	 *            the studentEmail to set
	 */
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	/**
	 * @return the placeOfBirth
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * @param placeOfBirth
	 *            the placeOfBirth to set
	 */
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	/**
	 * @return the lastSchool
	 */
	public String getLastSchool() {
		return lastSchool;
	}

	/**
	 * @param lastSchool
	 *            the lastSchool to set
	 */
	public void setLastSchool(String lastSchool) {
		this.lastSchool = lastSchool;
	}

	public Date getTcAppliedDate() {
		return tcAppliedDate;
	}

	public void setTcAppliedDate(Date tcAppliedDate) {
		this.tcAppliedDate = tcAppliedDate;
	}

	public Date getTcIssuedDate() {
		return tcIssuedDate;
	}

	public void setTcIssuedDate(Date tcIssuedDate) {
		this.tcIssuedDate = tcIssuedDate;
	}

	public String getHostelMode() {
		return hostelMode;
	}

	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the routeId
	 */
	public long getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the vehicleAcademicDetailsId
	 */
	public long getVehicleAcademicDetailsId() {
		return vehicleAcademicDetailsId;
	}

	/**
	 * @param vehicleAcademicDetailsId
	 *            the vehicleAcademicDetailsId to set
	 */
	public void setVehicleAcademicDetailsId(long vehicleAcademicDetailsId) {
		this.vehicleAcademicDetailsId = vehicleAcademicDetailsId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public boolean isTransportModuleStatus() {
		return transportModuleStatus;
	}

	public void setTransportModuleStatus(boolean transportModuleStatus) {
		this.transportModuleStatus = transportModuleStatus;
	}

	public boolean isStudentTransportStatus() {
		return studentTransportStatus;
	}

	public void setStudentTransportStatus(boolean studentTransportStatus) {
		this.studentTransportStatus = studentTransportStatus;
	}

	@Column(name = "phId", nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
	@Type(type = "yes_no")
	public boolean isPhId() {
		return phId;
	}

	public void setPhId(boolean phId) {
		this.phId = phId;
	}

	/* private long tcSerialNumber; */

	public Date getRelievingDate() {
		return relievingDate;
	}

	public void setRelievingDate(Date relievingDate) {
		this.relievingDate = relievingDate;
	}

	public String getScholarShipInfo() {
		return scholarShipInfo;
	}

	public void setScholarShipInfo(String scholarShipInfo) {
		this.scholarShipInfo = scholarShipInfo;
	}

	public String getMotherToung() {
		return motherToung;
	}

	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
	}

	public String getClassJoined() {
		return classJoined;
	}

	public void setClassJoined(String classJoined) {
		this.classJoined = classJoined;
	}

	public String getMediumOfStudy() {
		return mediumOfStudy;
	}

	public void setMediumOfStudy(String mediumOfStudy) {
		this.mediumOfStudy = mediumOfStudy;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getAffiliationNumber() {
		return affiliationNumber;
	}

	public void setAffiliationNumber(String affiliationNumber) {
		this.affiliationNumber = affiliationNumber;
	}

	public String getJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}

	public void setJoinedThroughAdmissions(String joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
	}

	/**
	 * @return the custEmail
	 */
	public String getCustEmail() {
		return custEmail;
	}

	/**
	 * @param custEmail
	 *            the custEmail to set
	 */
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getClassNameAndSection() {
		return classNameAndSection;
	}

	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}

	public String getSslcNumber() {
		return this.sslcNumber;
	}

	public void setSslcNumber(String sslcNumber) {
		this.sslcNumber = sslcNumber;
	}

	public String getTmrNumber() {
		return this.tmrNumber;
	}

	public void setTmrNumber(String tmrNumber) {
		this.tmrNumber = tmrNumber;
	}

	public long getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	/**
	 * @return the familyDoctor
	 */
	public String getFamilyDoctor() {
		return familyDoctor;
	}

	/**
	 * @param familyDoctor
	 *            the familyDoctor to set
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
	 * @param prefferedHospital
	 *            the prefferedHospital to set
	 */
	public void setPrefferedHospital(String prefferedHospital) {
		this.prefferedHospital = prefferedHospital;
	}

	/**
	 * @return the visionL
	 */
	public String getVisionL() {
		return visionL;
	}

	/**
	 * @param visionL
	 *            the visionL to set
	 */
	public void setVisionL(String visionL) {
		this.visionL = visionL;
	}
	
	/**
	 * @return the visionR
	 */
	public String getVisionR() {
		return visionR;
	}

	/**
	 * @param visionR
	 *            the visionR to set
	 */
	public void setVisionR(String visionR) {
		this.visionR = visionR;
	}

	/**
	 * @return the teeth
	 */
	public String getTeeth() {
		return teeth;
	}

	/**
	 * @param teeth
	 *            the teeth to set
	 */
	public void setTeeth(String teeth) {
		this.teeth = teeth;
	}

	/**
	 * @return the oralHygiene
	 */
	public String getOralHygiene() {
		return oralHygiene;
	}

	/**
	 * @param oralHygiene
	 *            the oralHygiene to set
	 */
	public void setOralHygiene(String oralHygiene) {
		this.oralHygiene = oralHygiene;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the height2
	 */
	public double getHeight2() {
		return height2;
	}

	/**
	 * @param height2 the height2 to set
	 */
	public void setHeight2(double height2) {
		this.height2 = height2;
	}

	/**
	 * @return the weight2
	 */
	public double getWeight2() {
		return weight2;
	}

	/**
	 * @param weight2 the weight2 to set
	 */
	public void setWeight2(double weight2) {
		this.weight2 = weight2;
	}

	public String getParentFullAddress() {
		return parentFullAddress;
	}

	public void setParentFullAddress(String parentFullAddress) {
		this.parentFullAddress = parentFullAddress;
	}

	public String getCustomerFullAddress() {
		return customerFullAddress;
	}

	public void setCustomerFullAddress(String customerFullAddress) {
		this.customerFullAddress = customerFullAddress;
	}

	/**
	 * @return the boardingPointId
	 */
	public long getBoardingPointId() {
		return boardingPointId;
	}

	public String getCustImageName() {
		return custImageName;
	}

	public void setCustImageName(String custImageName) {
		this.custImageName = custImageName;
	}

	public String getCustImagePath() {
		return custImagePath;
	}

	public void setCustImagePath(String custImagePath) {
		this.custImagePath = custImagePath;
	}

	public String getCustThumbNail() {
		return custThumbNail;
	}

	public void setCustThumbNail(String custThumbNail) {
		this.custThumbNail = custThumbNail;
	}

	/**
	 * @param boardingPointId
	 *            the boardingPointId to set
	 */
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}

	/**
	 * @return the medium
	 */
	public String getMedium() {
		return medium;
	}

	/**
	 * @param medium
	 *            the medium to set
	 */
	public void setMedium(String medium) {
		this.medium = medium;
	}

	/**
	 * @return the bedId
	 */
	public long getBedId() {
		return bedId;
	}

	/**
	 * @param bedId
	 *            the bedId to set
	 */
	public void setBedId(long bedId) {
		this.bedId = bedId;
	}

	public String getRecognisedBy() {
		return recognisedBy;
	}

	public void setRecognisedBy(String recognisedBy) {
		this.recognisedBy = recognisedBy;
	}

	public String getBoardOfEducation() {
		return boardOfEducation;
	}

	public void setBoardOfEducation(String boardOfEducation) {
		this.boardOfEducation = boardOfEducation;
	}

	/**
	 * @return the higherStandard
	 */
	@Column(name = "higherStandard", nullable = true, length = 1, columnDefinition = "char(1) default 'N'")
	public boolean isHigherStandard() {
		return higherStandard;
	}

	/**
	 * @param higherStandard
	 *            the higherStandard to set
	 */
	public void setHigherStandard(boolean higherStandard) {
		this.higherStandard = higherStandard;
	}

	/**
	 * @return the religionId
	 */
	public long getReligionId() {
		return religionId;
	}

	/**
	 * @param religionId
	 *            the religionId to set
	 */
	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}

	/**
	 * @return the filledSeats
	 */
	public int getFilledSeats() {
		return filledSeats;
	}

	/**
	 * @param filledSeats
	 *            the filledSeats to set
	 */
	public void setFilledSeats(int filledSeats) {
		this.filledSeats = filledSeats;
	}

	/**
	 * @return the mediumId
	 */
	public long getMediumId() {
		return mediumId;
	}

	/**
	 * @param mediumId
	 *            the mediumId to set
	 */
	public void setMediumId(long mediumId) {
		this.mediumId = mediumId;
	}

	/**
	 * @return the sectionCapacity
	 */
	public int getSectionCapacity() {
		return sectionCapacity;
	}

	/**
	 * @param sectionCapacity
	 *            the sectionCapacity to set
	 */
	public void setSectionCapacity(int sectionCapacity) {
		this.sectionCapacity = sectionCapacity;
	}

	/**
	 * @return the motherToung
	 */
	public long getMotherToungId() {
		return motherToungId;
	}

	/**
	 * @param motherToung
	 *            the motherToung to set
	 */
	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the castId
	 */
	public long getCastId() {
		return castId;
	}

	/**
	 * @return the subCastId
	 */
	public long getSubCastId() {
		return subCastId;
	}

	/**
	 * @param castId
	 *            the castId to set
	 */
	public void setCastId(long castId) {
		this.castId = castId;
	}

	/**
	 * @param subCastId
	 *            the subCastId to set
	 */
	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the annualIncome
	 */
	public long getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion
	 *            the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @param annualIncome
	 *            the annualIncome to set
	 */
	public void setAnnualIncome(long annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * @return the motherOccupation
	 */
	public String getMotherOccupation() {
		return motherOccupation;
	}

	/**
	 * @param motherOccupation
	 *            the motherOccupation to set
	 */
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	/**
	 * @return the fatherOccupation
	 */
	public String getFatherOccupation() {
		return fatherOccupation;
	}

	/**
	 * @param fatherOccupation
	 *            the fatherOccupation to set
	 */
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}

	/**
	 * @return the gender
	 */
	@Column(name = "gender", nullable = true, length = 1)
	public String getGender() {
		return gender;
	}

	public String getCastName() {
		return this.castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	public String getSubCastName() {
		return subCastName;
	}

	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "admissionNumber", nullable = true, length = 128)
	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "accountExpired", nullable = true, length = 1, columnDefinition = "char(1) default 'N'")
	@Type(type = "yes_no")
	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @return the categoryId
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getClassSectionId() {
		return classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Transient
	public boolean getPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	@Transient
	public char getLeaveRequest() {
		return leaveRequest;
	}

	public void setLeaveRequest(char leaveRequest) {
		this.leaveRequest = leaveRequest;
	}

	/**
	 * @return the classNameClassId
	 */
	public long getClassNameClassId() {
		return classNameClassId;
	}

	/**
	 * @param classNameClassId
	 *            the classNameClassId to set
	 */
	public void setClassNameClassId(long classNameClassId) {
		this.classNameClassId = classNameClassId;
	}

	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}

	/**
	 * @param roleDescription
	 *            the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Transient
	public String getTodayDateDOB() {
		return todayDateDOB;
	}

	public void setTodayDateDOB(String todayDateDOB) {
		this.todayDateDOB = todayDateDOB;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name = "imageId", nullable = false, length = 7,columnDefinition="BIGINT(20) default 0")
	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return the promoteToClass
	 */
	public String getPromoteToClass() {
		return promoteToClass;
	}

	/**
	 * @param promoteToClass the promoteToClass to set
	 */
	public void setPromoteToClass(String promoteToClass) {
		this.promoteToClass = promoteToClass;
	}

	@Transient
	public String getLeaveCount() {
		return leaveCount;
	}

	@Transient
	public void setLeaveCount(String leaveCount) {
		this.leaveCount = leaveCount;
	}

	@Transient
	public String getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(String obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	@Transient
	public String getMaxMarks() {
		return maxMarks;
	}

	@Transient
	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	@Transient
	public String getMinMarks() {
		return minMarks;
	}

	@Transient
	public void setMinMarks(String minMarks) {
		this.minMarks = minMarks;
	}

	@Transient
	public String getTypeOfExam() {
		return typeOfExam;
	}

	@Transient
	public void setTypeOfExam(String typeOfExam) {
		this.typeOfExam = typeOfExam;
	}

	@Transient
	public String getAnyString() {
		return anyString;
	}

	@Transient
	public void setAnyString(String anyString) {
		this.anyString = anyString;
	}

	@Transient
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/*
	 * @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="vehicleTripId") public VehicleTrip getVehicleTrip() {
	 * return vehicleTrip; } public void setVehicleTrip(VehicleTrip vehicleTrip)
	 * { this.vehicleTrip = vehicleTrip; }
	 */
	/*
	 * @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="vehicleTripId", insertable=true, updatable=true) public
	 * VehicleTrip getVehicleTrip() { return vehicleTrip; }
	 * 
	 * public void setVehicleTrip(VehicleTrip vehicleTrip) { this.vehicleTrip =
	 * vehicleTrip; }
	 */
	/*
	 * private String vehicleTripId;
	 * 
	 * 
	 * @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="vehicleTripId", insertable=true, updatable=true) public
	 * String getVehicleTripId() { return vehicleTripId; } public void
	 * setVehicleTripId(String vehicleTripId) { this.vehicleTripId =
	 * vehicleTripId; }
	 */
	/*
	 * public String getBoardingPoint() { return boardingPoint; }
	 * 
	 * public void setBoardingPoint(String boardingPoint) { this.boardingPoint =
	 * boardingPoint; }
	 */

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public ViewStudentPersonAccountDetails() {
		super();
	}

	public ViewStudentPersonAccountDetails(String firstName, String lastName,
			long classSectionId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.classSectionId = classSectionId;
	}

	public ViewStudentPersonAccountDetails(long accountId, long custId,
			String username, String sharePrivacy, String userOnlineNow,
			long academicYearId, String status, long studentId,
			String firstName, String lastName, String middleName) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.username = username;
		this.sharePrivacy = sharePrivacy;
		this.userOnlineNow = userOnlineNow;
		this.academicYearId = academicYearId;
		this.status = status;
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.fatherName = fatherName;
		this.motherName = motherName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentPersonAccountDetails))
			return false;
		return false;

	}

	@Override
	public int hashCode() {
		int result = 0;

		return result;
	}

	/**
	 * @see java.lang.Object#toString()
	 * 
	 *      Domestic Address Formatted as addressLine1; addressLine2; city,
	 *      state zipCode[-zipCodeSupplement]
	 * 
	 *      Military Address Formatted as addressLine1; addressLine2; city
	 *      postalCode;
	 * @Override
	 */

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		buffer.append(" User Name: ").append(getUsername())
				.append(" Status : ").append(getStatus());
		return buffer.toString();
	}

	@Override
	public int compareTo(Object object) {
		ViewStudentPersonAccountDetails myClass = (ViewStudentPersonAccountDetails) object;
		if (ObjectFunctions.isNullOrEmpty(myClass))
			return 0;
		else if (StringFunctions.isNotNullOrEmpty(myClass.getRegisterNumber())
				&& StringFunctions.isNotNullOrEmpty(this.getRegisterNumber())) {
			return this.getRegisterNumber().compareToIgnoreCase(
					myClass.getRegisterNumber());
		} else
			return this.getPersonFullName().compareToIgnoreCase(
					myClass.getPersonFullName());
	}

	/*
	 * public long getClassId() { return classId; }
	 * 
	 * public void setClassId(long classId) { this.classId = classId; }
	 */

	public long getAccountId() {
		return accountId;
	}

	public long getCustId() {
		return custId;
	}

	public String getUsername() {
		return username;
	}

	public String getSharePrivacy() {
		return sharePrivacy;
	}

	public String getUserOnlineNow() {
		return userOnlineNow;
	}

	public String getStatus() {
		return status;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSharePrivacy(String sharePrivacy) {
		this.sharePrivacy = sharePrivacy;
	}

	public void setUserOnlineNow(String userOnlineNow) {
		this.userOnlineNow = userOnlineNow;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/*
	 * public long getCasualLeaves() { return casualLeaves; } public long
	 * getSickLeaves() { return sickLeaves; } public void setCasualLeaves(long
	 * casualLeaves) { this.casualLeaves = casualLeaves; } public void
	 * setSickLeaves(long sickLeaves) { this.sickLeaves = sickLeaves; }
	 */

	@Id
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@Transient
	public String getFullFormattedName(boolean title, boolean middle,
			boolean suffix) {
		StringBuffer ret = new StringBuffer(10);
		/*
		 * if (title && !StringFunctions.isNullOrEmpty(strTitle)) {
		 * ret.append(strTitle); if (!strTitle.endsWith(".")) { ret.append(".");
		 * } ret.append(" "); }
		 */
		if (!StringFunctions.isNullOrEmpty(getFirstName())) {
			ret.append(getFirstName());

		}
		/*
		 * if (suffix && !StringFunctions.isNullOrEmpty(strSuffix) &&
		 * !Constants.SELECT_ID.equalsIgnoreCase(strSuffix)) { ret.append(" ");
		 * ret.append(strSuffix); ret.append(", "); } else { ret.append(", "); }
		 */
		if (!StringFunctions.isNullOrEmpty(getLastName())) {
			ret.append(", ");
			ret.append(getLastName());
			// ret.append(" ");
		}
		/*
		 * if (middle && !StringFunctions.isNullOrEmpty(strMiddleInitial )) {
		 * ret.append(strMiddleInitial.charAt(0)); ret.append(" "); }
		 */
		ret.append(" ");
		return ret.toString().trim();
	}

	@Transient
	public String getPersonFirstLastNameOnly() {
		StringBuffer ret = new StringBuffer(10);
		if (!StringFunctions.isNullOrEmpty(getFirstName())) {
			ret.append(getFirstName());
		}
		if (!StringFunctions.isNullOrEmpty(getLastName())) {
			ret.append(" ");
			ret.append(getLastName());
		}

		return ret.toString().trim();
	}

	/**
	 * Returns the student for display on UI
	 * 
	 * @return string with full formatted student
	 */
	@Transient
	public String getDisplayName() {
		return getFullFormattedName(false, true, true);
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

	/*
	 * @Transient public long getDisplayTotalLeaves() { return getCasualLeaves()
	 * + getSickLeaves();
	 * 
	 * }
	 * 
	 * @Transient public long getDisplayUsedLeaves() { return getCasualLeaves()
	 * + getSickLeaves();
	 * 
	 * }
	 */
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
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
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the mothersMaidenName
	 */
	public String getMothersMaidenName() {
		return mothersMaidenName;
	}

	/**
	 * @param mothersMaidenName
	 *            the mothersMaidenName to set
	 */
	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}

	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup
	 *            the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the fatherName
	 */
	@Transient
	public String getIdAndName() {
		return getPersonFirstLastNameOnly() + " (" + getUsername() + ")";
	}

	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName
	 *            the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @param motherName
	 *            the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Transient
	public String getAddressDesc() {

		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);

		if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
			buffer.append(getAddressLine1());
		}
		if (!StringFunctions.isNullOrEmpty(this.addressLine2)) {
			if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
				buffer.append(", ");
			}
			buffer.append(getAddressLine2());
		}
		if (!StringFunctions.isNullOrEmpty(this.addressLine2)) {
			if (!StringFunctions.isNullOrEmpty(this.addressLine2)) {
				buffer.append(", ");
			}
			buffer.append(getAddressLine2());
		}
		if (!StringFunctions.isNullOrEmpty(this.city)) {
			if (!StringFunctions.isNullOrEmpty(this.addressLine2) || !StringFunctions.isNullOrEmpty(this.addressLine1)) {
				buffer.append(", ");
			}
			buffer.append(getCity());
		}
		if (!StringFunctions.isNullOrEmpty(this.state)) {
			if (!StringFunctions.isNullOrEmpty(this.city)) {
				buffer.append(", ");
			}
			buffer.append(getState());
		}
		if (!StringFunctions.isNullOrEmpty(this.postalCode)) {
			if (!StringFunctions.isNullOrEmpty(this.state)) {
				buffer.append(", ");
			}
			buffer.append(getPostalCode());
		}
		return buffer.toString();
	
	}

	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName().trim() + "-" + getSection();
		}
		return getClassName();
	}

	@Transient
	public String getDateOfBirthMonthName() {
		Date stBirthDate = DateFormatter.parseString(
				DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(
						DateFormatter.YYYY_MM_DD_PATTERN, getDateOfBirth()));

		if (stBirthDate == new Date()) {
			return "Today";
		}
		return DateFormatter.formatDate(DateFormatter.MMM_D_PATTERN,
				getDateOfBirth());
	}
	

	// UserImage methods

	@Transient
	public static String getImageNotFoundFile() {
		return "/images/common/photo_notAvailable.jpg";
	}

	@Transient
	public String getStaticPhoto() {
		String imagePath = ServletActionContext.getServletContext()
				.getRealPath("/img/maha.png");
		return imagePath;
	}
	
	@Transient
	public String getDigitalSignature() {
		//String imagePath = ServletActionContext.getServletContext().getRealPath("userFiles/"+getCustId()+"/"+getCustId()+"PrincipalSign"+"/"+getCustId()+".png");
		return getPrincipalDigitalSignaturePath();
	}	
	
	@Transient
	public String getLibranSignature() {
		String imagePath = ServletActionContext.getServletContext().getRealPath("userFiles/"+getCustId()+"/"+getCustId()+"libranSign"+"/"+getCustId()+".png");
		return imagePath;
	}	
	@Transient
	public String getSpecimanSignature() {
		String imagePath = ServletActionContext.getServletContext().getRealPath("userFiles/"+getCustId()+"/"+getCustId()+"specimanSign"+"/"+getCustId()+".png");
		return imagePath;
	}
	@Transient
	public String getBarcodeImage() {
		if("Y".equalsIgnoreCase(getBarcodeStatus())){
			return getBarcode();
		}
		return "";
	}
	
	@Transient
	public String getPrincipalSignature() {
		String imagePath = ServletActionContext.getServletContext()
				.getRealPath("/img/Sign.png");
		return imagePath;
	}
	
	@Transient
	public static String getStudyImageNotFoundFile() {
		// return "/images/common/studyImageNotAvailable.jpg";
		return "/images/common/photo_notAvailable.jpg";
	}

	@Transient
	public String getOriginalAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			//return getImagePath().concat(getImageName());
			return getImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getSchoolIdCardAttachmentFilePath() {
		//String imagePath = ServletActionContext.getServletContext().getRealPath(getHrefOriginalAttachmentFilePath());
		// String hostUrl =
		// ServletActionContext.getServletContext().getInitParameter("hostUrl");
		// hostUrl = hostUrl + "/images/header/headerPdfPng.png";
		//return imagePath;
		return getAdjustedAttachmentFilePath();
	}

	@Transient
	public String getAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			//log.debug(getImagePath().concat(getThumbNail()));
			//return getImagePath().concat(getThumbNail());
			return getImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getHrefOriginalAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			//return getImagePath().substring(1).concat(getImageName());
			return getImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getHrefAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			//return getImagePath().substring(1).concat(getThumbNail());
			return getImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getDateOfBirthStr() {
		if (!ObjectFunctions.isNullOrEmpty(getDateOfBirth())) {
			return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,getDateOfBirth());
		} else {
			return "";
		}
	
	}

	@Transient
	public String getDateOfBirthDMY() {
		if (!ObjectFunctions.isNullOrEmpty(getDateOfBirth())) {
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN,getDateOfBirth());
		} else {
			return "";
		}
	}

	@Transient
	public String getStudentDateOfJoing() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN,
				this.getDateOfJoining());
	}

	@Transient
	public String getStudentDateOfDiscontune() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN,
				this.getLastUpdatedDate());
	}

	@Transient
	public String getAccountIdAndClassSectionId() {
		return getAccountId() + "_" + getClassSectionId();
	}

	@Transient
	public String getAccountIdClassSectionIdAcademicYearIdAndCustId() {
		return getAccountId() + "_" + getClassSectionId() + "_"
				+ getAcademicYearId() + "_" + getCustId();
	}
	
	@Transient
	public String getStudentIdClassSectionIdAcademicYearIdAndCustId() {
		return getStudentId() + "_" + getClassSectionId() + "_"
				+ getAcademicYearId() + "_" + getCustId();
	}
	
	@Transient
	public String getTodayDate() {
		// return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, new
		// Date());
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN,
				new Date());
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getEducationalDistrict() {
		return educationalDistrict;
	}

	public void setEducationalDistrict(String educationalDistrict) {
		this.educationalDistrict = educationalDistrict;
	}

	public String getRevenueDistrict() {
		return revenueDistrict;
	}

	public void setRevenueDistrict(String revenueDistrict) {
		this.revenueDistrict = revenueDistrict;
	}

	public String getCustomerShortName() {
		return customerShortName;
	}

	public void setCustomerShortName(String customerShortName) {
		this.customerShortName = customerShortName;
	}

	/*
	 * public long getSerialNumber() { return serialNumber; }
	 * 
	 * public void setSerialNumber(long serialNumber) { this.serialNumber =
	 * serialNumber; }
	 */

	/**
	 * @return the academicYear
	 */
	public String getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear
	 *            the academicYear to set
	 */
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the identification1
	 */
	public String getIdentification1() {
		return identification1;
	}

	/**
	 * @param identification1
	 *            the identification1 to set
	 */
	public void setIdentification1(String identification1) {
		this.identification1 = identification1;
	}

	/**
	 * @return the identification2
	 */
	public String getIdentification2() {
		return identification2;
	}

	/**
	 * @param identification2
	 *            the identification2 to set
	 */
	public void setIdentification2(String identification2) {
		this.identification2 = identification2;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	@Transient
	public String getCustomerAttachmentFilePath() {
		//String imagePath = ServletActionContext.getServletContext().getRealPath(getHrefCustomerAdjustedAttachmentFilePath());
		return getHrefCustomerAdjustedAttachmentFilePath();
	}

	@Transient
	public String getCustomerReportAttachments() {
		String imagePath = ServletActionContext.getServletContext()
				.getRealPath("userFilessvms/svmsColorReport.png");
		return imagePath;
	}

	@Transient
	public String getHrefCustomerAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getCustImagePath())) {
			//return getCustImagePath().substring(1).concat(getCustImageName());
			return getCustImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getCustomerAddress() {
		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
			buffer.append(getAddressLine1());
		}
		if (!StringFunctions.isNullOrEmpty(this.streetName)) {
			buffer.append(", ").append(getStreetName());
		}
		if (!StringFunctions.isNullOrEmpty(this.city)) {
			buffer.append(", ").append(getCity());
		}
		if (!StringFunctions.isNullOrEmpty(this.state)) {
			buffer.append(", ").append(getState());
		}
		if (!StringFunctions.isNullOrEmpty(this.postalCode)) {
			buffer.append(", ").append(getPostalCode());
		}
		return buffer.toString();
	}

	@Transient
	public String getParentAddress() {
		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		if (!StringFunctions.isNullOrEmpty(this.streetName)) {
			buffer.append(getStreetName().trim());
		}
		if (!StringFunctions.isNullOrEmpty(this.city)) {
			if (!StringFunctions.isNullOrEmpty(getStreetName().trim())) {
				buffer.append(", ");
			}
			buffer.append(getCity().trim());
		}
		if (!StringFunctions.isNullOrEmpty(this.postalCode)) {
			if (!StringFunctions.isNullOrEmpty(getCity())) {
				buffer.append(" - ");
			}
			buffer.append(getPostalCode());
		}
		if (!StringFunctions.isNullOrEmpty(this.mobileNumber)) {
			if (!StringFunctions.isNullOrEmpty(getPostalCode()) || !StringFunctions.isNullOrEmpty(getCity())) {
				buffer.append(", ");
			}
			buffer.append("Ph - ");
			buffer.append(getMobileNumber());
		}
		return buffer.toString();
	}

	public String getRationCardNumber() {
		return rationCardNumber;
	}

	public void setRationCardNumber(String rationCardNumber) {
		this.rationCardNumber = rationCardNumber;
	}

	public String getCommunityNumber() {
		return communityNumber;
	}

	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}

	@Transient
	public String getOrganizationName() {
		return organizationName;
	}

	@Transient
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Transient
	public String getTransportModeStatus() {
		if (getTransportMode().equalsIgnoreCase("T")) {
			return "School Transport";
		} else if (getTransportMode().equalsIgnoreCase("O")) {
			return "Own";
		} else {
			return "Private";
		}
	}

	@Transient
	public String getStudentNameAndAdmissionNumber() {
		if (!StringFunctions.isNullOrEmpty(getFullName())
				&& !StringFunctions.isNullOrEmpty(getAdmissionNumber())) {
			StringBuffer name = new StringBuffer();
			name.append(getFullName()).append("(").append(getAdmissionNumber())
					.append(")");
			return name.toString();
		}

		return "";
	}
	@Transient
	public void calculateAgeInYearsAndMonths(){
		if(!ObjectFunctions.isNullOrEmpty(this.dateOfBirth)){
			int[] ageDuration= calculateDurationBetweenTwoDatesInYrsMntsdays(new Date(), this.dateOfBirth);
			setBirthOfStudentYear(String.valueOf(ageDuration[2]));
			setBirthOfStudentMonth(String.valueOf(ageDuration[1]));
		}
	}
	@Transient
	public String getStudentAgeInMonthYear(){
		 
		if(ObjectFunctions.isNullOrEmpty(this.dateOfBirth)){
			return "";
		}
		else{
			int[] ageDuration= calculateDurationBetweenTwoDatesInYrsMntsdays(new Date(), this.dateOfBirth);
			setBirthOfStudentYear(String.valueOf(ageDuration[2]));
			setBirthOfStudentMonth(String.valueOf(ageDuration[1]));
			//log.debug(ageDuration[2]+"-----"+ageDuration[1]);
			return ageDuration[2]+" Years "+ ageDuration[1] +" Months ";  // DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.dateOfBirth);
		}
	}
	 public int[] calculateDurationBetweenTwoDatesInYrsMntsdays(Date date1, Date date2) {
		 int[] monthDay = { 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		 int[] ageDiffArr = new int[3];
		 Calendar fromDate;
		 Calendar toDate;
		 int increment = 0;
		 int year;
		 int month;
		 int day;
		 Calendar d1 = new GregorianCalendar().getInstance();
		 d1.setTime(date1);
		 Calendar d2 = new GregorianCalendar().getInstance();
		 d2.setTime(date2);
		 //From the two dates, we identify which date is bigger. The bigger one is set as toDate and the smaller one is fromDate so that we always get a positive duration. 
		 if (d1.getTime().getTime() > d2.getTime().getTime()) {
			 fromDate = d2;
			 toDate = d1;
		 } else {
			 fromDate = d1;
			 toDate = d2;
		 }
		 //Day Calculation 
		 
		 if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
			 increment = monthDay[fromDate.get(Calendar.MONTH)];
		 }

		 GregorianCalendar cal = new GregorianCalendar();
		 boolean isLeapYear = cal.isLeapYear(fromDate.get(Calendar.YEAR));

		 if (increment == -1) {
			 if (isLeapYear) {
				 increment = 29;
			 } else {
				 increment = 28;
			 }
		 }

		 // DAY CALCULATION
		 if (increment != 0) {
		 day = (toDate.get(Calendar.DAY_OF_MONTH) + increment) - fromDate.get(Calendar.DAY_OF_MONTH);
		 increment = 1;
		 } else {
		 day = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH);
		 }

		 // MONTH CALCULATION
		 if ((fromDate.get(Calendar.MONTH) + increment) > toDate.get(Calendar.MONTH)) {
		 month = (toDate.get(Calendar.MONTH) + 12) - (fromDate.get(Calendar.MONTH) + increment);
		 increment = 1;
		 } else {
		 month = (toDate.get(Calendar.MONTH)) - (fromDate.get(Calendar.MONTH) + increment);
		 increment = 0;
		 }

		 // YEAR CALCULATION
		 year = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment);

		 ageDiffArr[0] = day;
		 ageDiffArr[1] = month;
		 ageDiffArr[2] = year;
		 setBirthOfStudentYear(String.valueOf(year));
		 setBirthOfStudentMonth(String.valueOf(month));
		 return ageDiffArr; 
	 }
	 @Transient
		public String getBirthOfStudentYear() {
			return birthOfStudentYear;
		}
		@Transient
		public void setBirthOfStudentYear(String birthOfStudentYear) {
			this.birthOfStudentYear = birthOfStudentYear;
		}
		@Transient
		public String getBirthOfStudentMonth() {
			return birthOfStudentMonth;
		}
		@Transient
		public void setBirthOfStudentMonth(String birthOfStudentMonth) {
			this.birthOfStudentMonth = birthOfStudentMonth;
		}
		
		@Transient
		public String appendImageNameforOriginalAttachmentFilePath(String newImage) {
			if (!StringFunctions.isNullOrEmpty(getImagePath())) {
				return getImagePath().substring(1).concat(newImage);
			}
			return UserImage.getStudyImageNotFoundFile();
		}
		
		@Transient
		public String appendImageSchoolIdCardAttachmentFilePath(String newImage) {
			String imagePath=ServletActionContext.getServletContext().getRealPath(appendImageNameforOriginalAttachmentFilePath(newImage));
			File file=new File(imagePath);
			if(!file.exists()){
				return UserImage.getStudyImageNotFoundFile(); 
			}
		    return imagePath;
		}

		@Transient
		public String getBloodGroupImage() {
		if (StringFunctions.isNotNullOrEmpty(this.bloodGroup)) {
			return ServletActionContext.getServletContext().getRealPath(
					UserImage.getBloodGroupImageFile());
		}
		return "";
		}
		@Transient
		public String getFName(){
			if(StringFunctions.isNotNullOrEmpty(getFatherName()))
				return getFatherName();
			else
				return "";
		}@Transient
		public String getMName(){
			if(StringFunctions.isNotNullOrEmpty(getMotherName()))
				return getMotherName();
			else
				return "";
		}
		@Transient
		public String getStudentAddressDesc() {

			StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);

			if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
				buffer.append(getAddressLine1());
			}
			if (!StringFunctions.isNullOrEmpty(this.addressLine2)) {
				if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
					buffer.append(", ");
				}
				buffer.append(getAddressLine2());
			}
			if (!StringFunctions.isNullOrEmpty(this.city)) {
				if (!StringFunctions.isNullOrEmpty(this.addressLine2) || !StringFunctions.isNullOrEmpty(this.addressLine1)) {
					buffer.append(", ");
				}
				buffer.append(getCity());
			}
			if (!StringFunctions.isNullOrEmpty(this.state)) {
				if (!StringFunctions.isNullOrEmpty(this.city)) {
					buffer.append(", ");
				}
				buffer.append(getState());
			}
			if (!StringFunctions.isNullOrEmpty(this.postalCode)) {
				if (!StringFunctions.isNullOrEmpty(this.state)) {
					buffer.append(", ");
				}
				buffer.append(getPostalCode());
			}
			return buffer.toString();
		}
		
		@Transient
		public String getSyllabusLogo() {
			if(this.getSyllabusTypeName().equalsIgnoreCase("CBSE"))
				return ServletActionContext.getServletContext().getRealPath("/images/sreeRamaSchool/CBSELogo.jpg");
			else
				return ServletActionContext.getServletContext().getRealPath("/images/sreeRamaSchool/SBSElogo.jpg");
		}
		
		@Transient
		public String getSyllabusBySign() {
			if(this.getSyllabusTypeName().equalsIgnoreCase("CBSE"))
				return ServletActionContext.getServletContext().getRealPath("/images/sreeRamaSchool/CBSEsign.jpg");
			else
				return ServletActionContext.getServletContext().getRealPath("/images/sreeRamaSchool/SBSEsign.jpg");
		}
		public String getFeeRefundStatus() {
			return feeRefundStatus;
		}
		public void setFeeRefundStatus(String feeRefundStatus) {
			this.feeRefundStatus = feeRefundStatus;
		}
		/**
		 * @return the houseType
		 */
		public String getHouseType() {
			return houseType;
		}
		/**
		 * @param houseType the houseType to set
		 */
		public void setHouseType(String houseType) {
			this.houseType = houseType;
		}
		
		public String getStudentBankId() {
			return studentBankId;
		}
		public void setStudentBankId(String studentBankId) {
			this.studentBankId = studentBankId;
		}
		public String getStsNumber() {
			return stsNumber;
		}
		public void setStsNumber(String stsNumber) {
			this.stsNumber = stsNumber;
		}
		
}
