package com.urt.persistence.model.study;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.WordUtils;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;

/**
 * VwStudentstcdetailsId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "vw_studentsTCDetails")
public class ViewStudentsTCDetails implements Serializable,Cloneable, Comparable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long studId;
	private long classSectionId;
	private String status;
	private long custId;
	private long rollNumber;
	private long academicYearId;
	private long accountId;
	private long bedId;
	private long classId;
	private String firstName;
	private String lastName;
	private long castId;
	private long subCastId;
	private long religionId;
	private String className;
	private String section;
	private String higherStandard;
	private String fullName;
	private String castName;
	private String admissionNumber;
	private String registerNumber;
	private String accountExpired;
	private String studDiscontinueDesc;
	private Date dateOfJoining;
	private Date dateOfBirth;
	private String fatherName;
	private String motherName;
	private String motherOccupation;
	private String parentEmail;
	private long annualIncome;
	private String bloodGroup;
	private String mobileNumber;
	private String phoneNumber;
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
	private String organizationName;
	private String custAddressLine1;
	private String custCity;
	private String custPostalCode;
	private String educationalDistrict;
	private String revenueDistrict;
	private String schoolCode;
	private String recognisedBy;
	private String boardOfEducation;
	private String affiliationNumber;
	private String communityNumber;
	private String sslcNumber;
	private String tmrNumber;
	private String classJoined;
	private Date relievingDate;
	private String scholarShipInfo;
	private int serialNumber;
	private int tcSerialNumber;
	private String academicYear;
	private String educationType;
	private String generalEducationSubjects;
	private String vocationalEducationSubjects;
	private String gender;
	private String mediumOfStudy;
	private String custStreetName;
	private Date tcAppliedDate;
	private Date tcIssuedDate;
	private int tcBookNumber;
	private int totalWorkingDaysCount;
	private int presentDaysCount;
	private double height;
    private double weight;
	private String visionL;
	private String visionR;
	private String oralHygiene;
	private long syllabusTypeId;
	private String syllabusTypeName;
	private Date startDate;
	private Date endDate;
	private String feeDueMonthAndYear;
	private String type;
	private String placeOfBirth;
	private String lastSchool;
	private String imagePath;
	private String imageName;
	private String thumbNail;
	private String stateName;
	private String aadharNumber;
	private String enrollmentCode;
	private long studyClassId;
	private Date tcIssueDate;
	private String addressLine1;
	private String addressLine2;
	private double paidAmount;
	
	
	
     public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public Date getTcIssueDate() {
		return tcIssueDate;
	}
	public void setTcIssueDate(Date tcIssueDate) {
		this.tcIssueDate = tcIssueDate;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
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
	/**
 	 * @return the placeOfBirth
 	 */
 	public String getPlaceOfBirth() {
 		return placeOfBirth;
 	}
 	/**
 	 * @param placeOfBirth the placeOfBirth to set
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
 	 * @param lastSchool the lastSchool to set
 	 */
 	public void setLastSchool(String lastSchool) {
 		this.lastSchool = lastSchool;
 	}
	
	// Constructors

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the tcBookNumber
	 */
	public int getTcBookNumber() {
		return tcBookNumber;
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the syllabusTypeId
	 */
	public long getSyllabusTypeId() {
		return syllabusTypeId;
	}
	/**
	 * @param syllabusTypeId the syllabusTypeId to set
	 */
	public void setSyllabusTypeId(long syllabusTypeId) {
		this.syllabusTypeId = syllabusTypeId;
	}
	/**
	 * @return the syllabusTypeName
	 */
	public String getSyllabusTypeName() {
		return syllabusTypeName;
	}
	/**
	 * @param syllabusTypeName the syllabusTypeName to set
	 */
	public void setSyllabusTypeName(String syllabusTypeName) {
		this.syllabusTypeName = syllabusTypeName;
	}
	/**
	 * @return the oralHygiene
	 */
	public String getOralHygiene() {
		return oralHygiene;
	}
	/**
	 * @param oralHygiene the oralHygiene to set
	 */
	public void setOralHygiene(String oralHygiene) {
		this.oralHygiene = oralHygiene;
	}
	/**
	 * @param height the height to set
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
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the vision
	 */
	public String getVisionL() {
		return visionL;
	}

	/**
	 * @param visionL the visionL to set
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
	 * @param visionR the visionR to set
	 */
	public void setVisionR(String visionR) {
		this.visionR = visionR;
	}
	/**
	 * @param tcBookNumber the tcBookNumber to set
	 */
	public void setTcBookNumber(int tcBookNumber) {
		this.tcBookNumber = tcBookNumber;
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

	/** default constructor */
	public ViewStudentsTCDetails() {
	}

	/** minimal constructor */
	public ViewStudentsTCDetails(long studId, long custId, long bedId,
			String admissionNumber, String religion, String motherToung,
			String organizationName) {
		this.studId = studId;
		this.custId = custId;
		this.bedId = bedId;
		this.admissionNumber = admissionNumber;
		this.religion = religion;
		this.motherToung = motherToung;
		this.organizationName = organizationName;
	}

	/** full constructor */
	public ViewStudentsTCDetails(long studId, long classSectionId,
			String status, long custId, long rollNumber, long academicYearId,
			long accountId, long bedId, long classId, String firstName,
			String lastName, long castId, long subCastId, long religionId,
			String className, String section, String higherStandard,
			String fullName, String castName, String admissionNumber,
			String registerNumber, String accountExpired,
			String studDiscontinueDesc, Date dateOfJoining,
			Date dateOfBirth, String fatherName, String motherName,
			String motherOccupation, String parentEmail, long annualIncome,
			String bloodGroup, String mobileNumber, String phoneNumber,
			String nationality, long motherToungId, String streetName,
			String city, String postalCode, String identification1,
			String identification2, String subCastName, String religion,
			String motherToung, String organizationName,
			String custAddressLine1, String custCity, String custPostalCode,
			String educationalDistrict, String revenueDistrict,
			String schoolCode, String recognisedBy, String boardOfEducation,
			String affiliationNumber, String communityNumber,
			String sslcNumber, String tmrNumber, String classJoined,
			Date relievingDate, String scholarShipInfo) {
		this.studId = studId;
		this.classSectionId = classSectionId;
		this.status = status;
		this.custId = custId;
		this.rollNumber = rollNumber;
		this.academicYearId = academicYearId;
		this.accountId = accountId;
		this.bedId = bedId;
		this.classId = classId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.castId = castId;
		this.subCastId = subCastId;
		this.religionId = religionId;
		this.className = className;
		this.section = section;
		this.higherStandard = higherStandard;
		this.fullName = fullName;
		this.castName = castName;
		this.admissionNumber = admissionNumber;
		this.registerNumber = registerNumber;
		this.accountExpired = accountExpired;
		this.studDiscontinueDesc = studDiscontinueDesc;
		this.dateOfJoining = dateOfJoining;
		this.dateOfBirth = dateOfBirth;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.motherOccupation = motherOccupation;
		this.parentEmail = parentEmail;
		this.annualIncome = annualIncome;
		this.bloodGroup = bloodGroup;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.nationality = nationality;
		this.motherToungId = motherToungId;
		this.streetName = streetName;
		this.city = city;
		this.postalCode = postalCode;
		this.identification1 = identification1;
		this.identification2 = identification2;
		this.subCastName = subCastName;
		this.religion = religion;
		this.motherToung = motherToung;
		this.organizationName = organizationName;
		this.custAddressLine1 = custAddressLine1;
		this.custCity = custCity;
		this.custPostalCode = custPostalCode;
		this.educationalDistrict = educationalDistrict;
		this.revenueDistrict = revenueDistrict;
		this.schoolCode = schoolCode;
		this.recognisedBy = recognisedBy;
		this.boardOfEducation = boardOfEducation;
		this.affiliationNumber = affiliationNumber;
		this.communityNumber = communityNumber;
		this.sslcNumber = sslcNumber;
		this.tmrNumber = tmrNumber;
		this.classJoined = classJoined;
		this.relievingDate = relievingDate;
		this.scholarShipInfo = scholarShipInfo;
	}

	// Property accessors
	@Id
	@Column(name = "studId", nullable = false)
	public long getStudId() {
		return this.studId;
	}

	public void setStudId(long studId) {
		this.studId = studId;
	}
	
	public String getCustStreetName() {
		return custStreetName;
	}

	public void setCustStreetName(String custStreetName) {
		this.custStreetName = custStreetName;
	}

	public String getMediumOfStudy() {
		return mediumOfStudy;
	}

	public void setMediumOfStudy(String mediumOfStudy) {
		this.mediumOfStudy = mediumOfStudy;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	@Column(name = "classSectionId")
	public long getClassSectionId() {
		return this.classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "custId", nullable = false)
	public long getCustId() {
		return this.custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Column(name = "rollNumber")
	public long getRollNumber() {
		return this.rollNumber;
	}

	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}

	@Column(name = "academicYearId")
	public long getAcademicYearId() {
		return this.academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Column(name = "accountId")
	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "bedId", nullable = false)
	public long getBedId() {
		return this.bedId;
	}

	public void setBedId(long bedId) {
		this.bedId = bedId;
	}

	@Column(name = "classId")
	public long getClassId() {
		return this.classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	@Column(name = "firstName", length = 128)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", length = 128)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "castId")
	public long getCastId() {
		return this.castId;
	}

	public void setCastId(long castId) {
		this.castId = castId;
	}

	@Column(name = "subCastId")
	public long getSubCastId() {
		return this.subCastId;
	}

	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}

	@Column(name = "religionId")
	public long getReligionId() {
		return this.religionId;
	}

	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}

	@Column(name = "className")
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "section")
	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Column(name = "higherStandard", length = 1)
	public String getHigherStandard() {
		return this.higherStandard;
	}

	public void setHigherStandard(String higherStandard) {
		this.higherStandard = higherStandard;
	}

	@Column(name = "fullName", length = 257)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "castName", length = 200)
	public String getCastName() {
		return this.castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	@Column(name = "admissionNumber", nullable = false, length = 128)
	public String getAdmissionNumber() {
		return this.admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	@Column(name = "registerNumber", length = 20)
	public String getRegisterNumber() {
		return this.registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	@Column(name = "accountExpired", length = 1)
	public String getAccountExpired() {
		return this.accountExpired;
	}

	public void setAccountExpired(String accountExpired) {
		this.accountExpired = accountExpired;
	}

	@Column(name = "studDiscontinueDesc")
	public String getStudDiscontinueDesc() {
		return this.studDiscontinueDesc;
	}

	public void setStudDiscontinueDesc(String studDiscontinueDesc) {
		this.studDiscontinueDesc = studDiscontinueDesc;
	}

	@Column(name = "dateOfJoining")
	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Column(name = "dateOfBirth")
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "fatherName", length = 128)
	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	@Column(name = "motherName", length = 128)
	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "motherOccupation", length = 200)
	public String getMotherOccupation() {
		return this.motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	@Column(name = "parentEmail", length = 128)
	public String getParentEmail() {
		return this.parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	@Column(name = "annualIncome")
	public long getAnnualIncome() {
		return this.annualIncome;
	}

	public void setAnnualIncome(long annualIncome) {
		this.annualIncome = annualIncome;
	}

	@Column(name = "bloodGroup", length = 20)
	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Column(name = "mobileNumber", length = 50)
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "phoneNumber", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "nationality", length = 128)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "motherToungId")
	public long getMotherToungId() {
		return this.motherToungId;
	}

	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}

	@Column(name = "streetName", length = 200)
	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "city", length = 64)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "postalCode", length = 12)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "identification1")
	public String getIdentification1() {
		return this.identification1;
	}

	public void setIdentification1(String identification1) {
		this.identification1 = identification1;
	}

	@Column(name = "identification2")
	public String getIdentification2() {
		return this.identification2;
	}

	public void setIdentification2(String identification2) {
		this.identification2 = identification2;
	}

	@Column(name = "subCastName", length = 200)
	public String getSubCastName() {
		return this.subCastName;
	}

	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}

	@Column(name = "religion", nullable = false, length = 65)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "motherToung", nullable = false, length = 65)
	public String getMotherToung() {
		return this.motherToung;
	}

	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
	}

	@Column(name = "organizationName", nullable = false, length = 128)
	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Column(name = "custAddressLine1", length = 1024)
	public String getCustAddressLine1() {
		return this.custAddressLine1;
	}

	public void setCustAddressLine1(String custAddressLine1) {
		this.custAddressLine1 = custAddressLine1;
	}

	@Column(name = "custCity", length = 64)
	public String getCustCity() {
		return this.custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	@Column(name = "custPostalCode", length = 12)
	public String getCustPostalCode() {
		return this.custPostalCode;
	}

	public void setCustPostalCode(String custPostalCode) {
		this.custPostalCode = custPostalCode;
	}

	@Column(name = "educationalDistrict")
	public String getEducationalDistrict() {
		return this.educationalDistrict;
	}

	public void setEducationalDistrict(String educationalDistrict) {
		this.educationalDistrict = educationalDistrict;
	}

	@Column(name = "revenueDistrict")
	public String getRevenueDistrict() {
		return this.revenueDistrict;
	}

	public void setRevenueDistrict(String revenueDistrict) {
		this.revenueDistrict = revenueDistrict;
	}

	@Column(name = "schoolCode", length = 10)
	public String getSchoolCode() {
		return this.schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	@Column(name = "recognisedBy")
	public String getRecognisedBy() {
		return this.recognisedBy;
	}

	public void setRecognisedBy(String recognisedBy) {
		this.recognisedBy = recognisedBy;
	}

	@Column(name = "boardOfEducation")
	public String getBoardOfEducation() {
		return this.boardOfEducation;
	}

	public void setBoardOfEducation(String boardOfEducation) {
		this.boardOfEducation = boardOfEducation;
	}

	@Column(name = "affiliationNumber", length = 20)
	public String getAffiliationNumber() {
		return this.affiliationNumber;
	}

	public void setAffiliationNumber(String affiliationNumber) {
		this.affiliationNumber = affiliationNumber;
	}

	@Column(name = "communityNumber", length = 128)
	public String getCommunityNumber() {
		return this.communityNumber;
	}

	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}

	@Column(name = "sslcNumber", length = 15)
	public String getSslcNumber() {
		return this.sslcNumber;
	}

	public void setSslcNumber(String sslcNumber) {
		this.sslcNumber = sslcNumber;
	}

	@Column(name = "tmrNumber", length = 20)
	public String getTmrNumber() {
		return this.tmrNumber;
	}

	public void setTmrNumber(String tmrNumber) {
		this.tmrNumber = tmrNumber;
	}

	@Column(name = "classJoined")
	public String getClassJoined() {
		return this.classJoined;
	}

	public void setClassJoined(String classJoined) {
		this.classJoined = classJoined;
	}

	@Column(name = "relievingDate", length = 19)
	public Date getRelievingDate() {
		return this.relievingDate;
	}

	public void setRelievingDate(Date relievingDate) {
		this.relievingDate = relievingDate;
	}

	@Column(name = "scholarShipInfo")
	public String getScholarShipInfo() {
		return this.scholarShipInfo;
	}

	public void setScholarShipInfo(String scholarShipInfo) {
		this.scholarShipInfo = scholarShipInfo;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Transient
	public String getTCPersonFullName() {
		
		return getPersonFullName().toUpperCase();
		
	}
	
	@Transient
	public String getStudentHeOrShe() 
	{
		if(getGender().equalsIgnoreCase("M"))
		{
			return "he ";
		}
		return  "she ";
		
	}
	
	@Transient
	public String getGenderDesc() {
		if("M".equalsIgnoreCase(this.gender))
			return "Male";
		else
			return "Female";
	}
	@Transient
	public String getCastAndSubCastName() {
		
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(this.castName)) {
			ret.append(this.castName);
		}
		if (!StringFunctions.isNullOrEmpty(this.subCastName)) {
			ret.append(" - ");
			ret.append(this.subCastName);
		}

		return ret.toString().trim();
		
		/*String castName = null;
		if(!StringFunctions.isNullOrEmpty(this.castName))
		{
			castName = this.castName;
			if (!StringFunctions.isNullOrEmpty(this.subCastName)) {
				
				castName = "- "+ this.subCastName;
			}
		}
		return castName;*/
	}
	
	@Transient
	public String getStudentSubCastName(){
		if(StringFunctions.isNullOrEmpty(this.subCastName))
			return "";
		else
			return this.subCastName;
	}
	@Transient
	public int getTcSerialNumber() {
		if(getSerialNumber() > 0){
			return getSerialNumber();
		}
		return tcSerialNumber;
	}

	public void setTcSerialNumber(int tcSerialNumber) {
		this.tcSerialNumber = tcSerialNumber;
	}
	
	@Transient
	public String getSerialNumberWithAcademicYear(){
		if(StringFunctions.isNullOrEmpty(getAcademicYear()))
			return String.valueOf(getTcSerialNumber());
		else
			return getTcSerialNumber()+"/"+(getAcademicYear().split("-")[0].substring(0, 2).concat(getAcademicYear().split("-")[1]));
	}
	
	@Transient
	public String getGeneralEducationSubjects() {
		if(StringFunctions.isNullOrEmpty(this.generalEducationSubjects))
			return "";
		return generalEducationSubjects;
	}

	public void setGeneralEducationSubjects(String generalEducationSubjects) {
		this.generalEducationSubjects = generalEducationSubjects;
	}
	@Transient
	public String getVocationalEducationSubjects() {
		if(StringFunctions.isNullOrEmpty(this.vocationalEducationSubjects))
			return "";
		return this.vocationalEducationSubjects;
	}

	public void setVocationalEducationSubjects(String vocationalEducationSubjects) {
		this.vocationalEducationSubjects = vocationalEducationSubjects;
	}
	
	@Transient
	public String getPersonFullName() {
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
	
	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
		}
		return getClassName();
	}
	@Transient
	public String getNationalityAndReligion() {
		StringBuffer buff = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(getNationality())){
			buff.append(WordUtils.capitalize(getNationality().toLowerCase()));
		}
		if(StringFunctions.isNotNullOrEmpty(getReligion())){
			if(buff.length() > 0)
				buff.append(", ");
			buff.append(WordUtils.capitalize(getReligion().toLowerCase()));
		}
		return buff.toString();
	}
	@Transient
	public String getNationalityAndReligionAndCast() {
		StringBuffer buff = new StringBuffer(); 
		if(StringFunctions.isNotNullOrEmpty(getNationality())){
			buff.append(WordUtils.capitalize(getNationality().toLowerCase()));
		}
		if(StringFunctions.isNotNullOrEmpty(getReligion())){
			if(buff.length() > 0)
				buff.append(", ");
			buff.append(WordUtils.capitalize(getReligion().toLowerCase()));
		}
		if(StringFunctions.isNotNullOrEmpty(getSubCastName())){
			if(buff.length() > 0)
				buff.append(", ");
			buff.append(WordUtils.capitalize(getSubCastName().toLowerCase()));
		}
		return buff.toString();
	}
	@Transient
	public String getDateOfBirthInWords() 
	{
		if(!ObjectFunctions.isNullOrEmpty(this.getDateOfBirth())) {
			StringBuffer buffer = new StringBuffer(); 
			buffer.append(DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getDateOfBirth()));
			buffer.append(" ( ");
			SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
			buffer.append(DateFunctions.DateInWords(sdf.format(this.getDateOfBirth())));
			buffer.append(")");
			return buffer.toString();
		}else
			return "";
		
	}
	@Transient
	public String getDateOfJoiningInWords() 
	{
		if(!ObjectFunctions.isNullOrEmpty(this.getDateOfJoining()))
		{
			StringBuffer buffer = new StringBuffer(); 
			buffer.append(DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getDateOfJoining()));
			buffer.append(" ( ");
			SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
			buffer.append(DateFunctions.DateInWords(sdf.format(this.getDateOfJoining())));
			buffer.append(")");
			return buffer.toString();
		}
		return null;
		
	}
	
	@Transient
	public String getStudentDateOfJoingInWords() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
		if(!ObjectFunctions.isNullOrEmpty(this.getDateOfJoining()))
			return DateFunctions.DateInWords(sdf.format(this.getDateOfJoining()));
		return null;
	}
	@Transient
	public String getOrganizationCustAddressLineCityAndPostalCode() {
		StringBuffer buffer = new StringBuffer();
		if (StringFunctions.isNotNullOrEmpty(this.organizationName)) {
			buffer.append(this.organizationName);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custAddressLine1)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custAddressLine1);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custCity) && StringFunctions.isNotNullOrEmpty(this.custPostalCode)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity).append("-").append(this.custPostalCode);
		}else if(StringFunctions.isNotNullOrEmpty(this.custCity)){
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity);
		}else{
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custPostalCode);
		}
		return buffer.toString();
	}
	@Transient
	public String getOrganizationAddressLineCityAndPostalCode() {
		StringBuffer buffer = new StringBuffer();
		if (StringFunctions.isNotNullOrEmpty(this.custAddressLine1)) {
			buffer.append(this.custAddressLine1);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custStreetName)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custStreetName);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custCity) && StringFunctions.isNotNullOrEmpty(this.custPostalCode)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity).append("-").append(this.custPostalCode);
		}else if(StringFunctions.isNotNullOrEmpty(this.custCity)){
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity);
		}else{
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custPostalCode);
		}
		return buffer.toString();
	}
	@Transient
	public String getFatherOrMotherName(){
		if(StringFunctions.isNotNullOrEmpty(getFatherName()))
			return getFatherName();
		else if(StringFunctions.isNotNullOrEmpty(getMotherName()))
			return getMotherName();
		else
			return "";
	}
	//For sarojini school
	@Transient
	public String getAdiDravidarOrSCorST(){
		if("ADIDRAVIDAR".equalsIgnoreCase(getCastName())){
			return "Adidravidar";
		}else if("SC".equalsIgnoreCase(getCastName())){
			return "SC";
		}else if("ST".equalsIgnoreCase(getCastName())){
			return "ST (Converted to Christianity Adi Dravidar)";
		}
		return "";
	}
	// For Maharishi school Raipur
	@Transient
	public String getSCorSTorOBC(){
		StringBuffer cast = new StringBuffer();
		if("SC".equalsIgnoreCase(getCastName())){
			cast.append("SC");
		}else if("ST".equalsIgnoreCase(getCastName())){
			cast.append("ST");
		}else if("OBC".equalsIgnoreCase(getCastName())){
			cast.append("OBC");
		}
		return cast.toString();
	}
	@Transient
	public String getBackwardClass(){
		if(StringFunctions.isNotNullOrEmpty(getCastName()) && getCastName().startsWith("BC"))
			return getCastName();
		else
			return "";
	}
	@Transient
	public String getMostBackwardClass(){
		if(StringFunctions.isNotNullOrEmpty(getCastName()) && getCastName().startsWith("MBC"))
			return getCastName();
		else
			return "";
	}
	@Transient
	public String getCommunityName(){
		if(StringFunctions.isNotNullOrEmpty(getAdiDravidarOrSCorST()) ||
				StringFunctions.isNotNullOrEmpty(getBackwardClass()) || StringFunctions.isNotNullOrEmpty(getMostBackwardClass())){
			return "";
		}else if(StringFunctions.isNotNullOrEmpty(getCastName()))
			return getCastName();
		return "";
	}
	@Transient
	public String getDateOfAdmissionAndStandard(){
		StringBuffer buffer = new StringBuffer();
		if(!ObjectFunctions.isNullOrEmpty(getDateOfJoining())){
			buffer.append(DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, getDateOfJoining()));
			buffer.append(" ( ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			buffer.append(DateFunctions.DateInWords(sdf.format(this.getDateOfJoining())));
			buffer.append(") ");
		}
		if(StringFunctions.isNotNullOrEmpty(getClassJoined())){
			buffer.append(getClassJoined()+" standard");
		}else{
			buffer.append("");
		}
		return buffer.toString();
	}
	@Transient
	public String getClassNameWithWords(){
		String classNameInWords= getNumbersInWords(getClassName());
		if(StringFunctions.isNullOrEmpty(classNameInWords))
			return "";
		else
			return getClassName()+" ( " +classNameInWords+" Standard )";
	}
	@Transient
	public String getNumbersInWords(String number){
		if(StringFunctions.isNullOrEmpty(number)){
			return "";
		}else{
			if("1".equalsIgnoreCase(number) || "I".equalsIgnoreCase(number))
				return "First";
			else if("2".equalsIgnoreCase(number) || "II".equalsIgnoreCase(number))
				return "Second";
			else if("3".equalsIgnoreCase(number) || "III".equalsIgnoreCase(number))
				return "Third";
			else if("4".equalsIgnoreCase(number) || "IV".equalsIgnoreCase(number))
				return "Fourth";
			else if("5".equalsIgnoreCase(number) || "V".equalsIgnoreCase(number))
				return "Fifth";
			else if("6".equalsIgnoreCase(number) || "VI".equalsIgnoreCase(number))
				return "Sixth";
			else if("7".equalsIgnoreCase(number) || "VII".equalsIgnoreCase(number))
				return "Seventh";
			else if("8".equalsIgnoreCase(number) || "VIII".equalsIgnoreCase(number))
				return "Eight";
			else if("9".equalsIgnoreCase(number) || "IX".equalsIgnoreCase(number))
				return "Ninth";
			else if("10".equalsIgnoreCase(number) || "X".equalsIgnoreCase(number))
				return "Tenth";
			else if("11".equalsIgnoreCase(number) || "XI".equalsIgnoreCase(number))
				return "Eleventh";
			else if("12".equalsIgnoreCase(number) || "XII".equalsIgnoreCase(number))
				return "Twelfth";
			else
				return "";
		}
	}
	@Transient
	public String getTcAppliedDateStr(){
		if(ObjectFunctions.isNullOrEmpty(getTcAppliedDate()))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, getTcAppliedDate());
	}
	@Transient
	public String getTcIssuedDateStr(){
		if(ObjectFunctions.isNullOrEmpty(getTcIssuedDate()))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, getTcIssuedDate());
	}
	@Transient
	public String getRelievingDateStr(){
		if(ObjectFunctions.isNullOrEmpty(getRelievingDate()))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, getRelievingDate());
	}
	
	@Transient
	public String getTcEducationalDistrict(){
		if(StringFunctions.isNullOrEmpty(this.educationalDistrict))
			return "";
		else
			return  WordUtils.capitalize(this.educationalDistrict.toLowerCase());
	}
	
	@Transient
	public String getTcRevenueDistrict(){
		if(StringFunctions.isNullOrEmpty(this.revenueDistrict))
			return "";
		else
			return  WordUtils.capitalize(this.revenueDistrict.toLowerCase());
	}
	
	@Transient
	public String getTcMediumOfStudy(){
		if(StringFunctions.isNullOrEmpty(this.mediumOfStudy))
			return "";
		else
			return WordUtils.capitalize(this.mediumOfStudy.toLowerCase());
	}
	
	@Transient
	public String getStudDateOfBirthInFigures(){
		if(ObjectFunctions.isNullOrEmpty(this.dateOfBirth))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.dateOfBirth);
	}
	
	@Transient
	public String getDateOfBirthInWordsWithOutDate(){
		if(ObjectFunctions.isNullOrEmpty(this.dateOfBirth))
			return "";
		else{
			return DateFunctions.DateInWords(new SimpleDateFormat("d MMMM yyyy").format(this.dateOfBirth));
		}
	}
	@Override
	public int compareTo(Object object) {
		ViewStudentsTCDetails myClass = (ViewStudentsTCDetails) object;
		if(ObjectFunctions.isNullOrEmpty(myClass))
			return 0;
		else if(StringFunctions.isNotNullOrEmpty(myClass.getRegisterNumber()) && StringFunctions.isNotNullOrEmpty(this.getRegisterNumber())){
			if(this.section.equalsIgnoreCase(myClass.getSection())){
				return this.getRegisterNumber().compareToIgnoreCase(myClass.getRegisterNumber());
			}else
				return this.section.compareToIgnoreCase(myClass.getSection());
		}else
			return this.getPersonFullName().compareToIgnoreCase(myClass.getPersonFullName());	
	}
	
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof ViewStudentsTCDetails))
			return false;
		ViewStudentsTCDetails castOther = (ViewStudentsTCDetails) other;

		return this.getStudId() == castOther.getStudId() || (this
				.getStudId() != 0
				&& castOther.getStudId() != 0 && this.getStudId()==
				castOther.getStudId())
				&& this.getClassSectionId() == castOther.getClassSectionId() || (this
						.getClassSectionId() != 0
						&& castOther.getClassSectionId() != 0 && this
						.getClassSectionId() ==
								castOther.getClassSectionId())
				&& this.getStatus() == castOther.getStatus() || (this
						.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus()))
				&& this.getCustId() == castOther.getCustId() || (this
						.getCustId() != 0
						&& castOther.getCustId() != 0 && this.getCustId()
						== castOther.getCustId())
				&& this.getRollNumber() == castOther.getRollNumber() || (this
						.getRollNumber() != 0
						&& castOther.getRollNumber() != 0 && this
						.getRollNumber() == castOther.getRollNumber())
				&& this.getAcademicYearId() == castOther.getAcademicYearId() || (this
						.getAcademicYearId() != 0
						&& castOther.getAcademicYearId() != 0 && this
						.getAcademicYearId()==
								castOther.getAcademicYearId())
				&& this.getAccountId() == castOther.getAccountId() || (this
						.getAccountId() != 0
						&& castOther.getAccountId() != 0 && this
						.getAccountId() == castOther.getAccountId())
				&& this.getBedId() == castOther.getBedId() || (this
						.getBedId() != 0
						&& castOther.getBedId() != 0 && this.getBedId()
						 == castOther.getBedId())
				&& this.getClassId() == castOther.getClassId() || (this
						.getClassId() != 0
						&& castOther.getClassId() != 0 && this.getClassId()
						== castOther.getClassId())
				&& this.getFirstName() == castOther.getFirstName() || (this
						.getFirstName() != null
						&& castOther.getFirstName() != null && this
						.getFirstName().equals(castOther.getFirstName()))
				&& this.getLastName() == castOther.getLastName() || (this
						.getLastName() != null
						&& castOther.getLastName() != null && this
						.getLastName().equals(castOther.getLastName()))
				&& this.getCastId() == castOther.getCastId() || (this
						.getCastId() != 0
						&& castOther.getCastId() != 0 && this.getCastId()
						== castOther.getCastId())
				&& this.getSubCastId() == castOther.getSubCastId() || (this
						.getSubCastId() != 0
						&& castOther.getSubCastId() != 0 && this
						.getSubCastId() == castOther.getSubCastId())
				&& this.getReligionId() == castOther.getReligionId() || (this
						.getReligionId() != 0
						&& castOther.getReligionId() != 0 && this
						.getReligionId()==castOther.getReligionId())
				&& this.getClassName() == castOther.getClassName() || (this
						.getClassName() != null
						&& castOther.getClassName() != null && this
						.getClassName().equals(castOther.getClassName()))
				&& this.getSection() == castOther.getSection() || (this
						.getSection() != null
						&& castOther.getSection() != null && this.getSection()
						.equals(castOther.getSection()))
				&& this.getHigherStandard() == castOther.getHigherStandard() || (this
						.getHigherStandard() != null
						&& castOther.getHigherStandard() != null && this
						.getHigherStandard().equals(
								castOther.getHigherStandard()))
				&& this.getFullName() == castOther.getFullName() || (this
						.getFullName() != null
						&& castOther.getFullName() != null && this
						.getFullName().equals(castOther.getFullName()))
				&& this.getCastName() == castOther.getCastName() || (this
						.getCastName() != null
						&& castOther.getCastName() != null && this
						.getCastName().equals(castOther.getCastName()))
				&& this.getAdmissionNumber() == castOther
						.getAdmissionNumber() || (this.getAdmissionNumber() != null
						&& castOther.getAdmissionNumber() != null && this
						.getAdmissionNumber().equals(
								castOther.getAdmissionNumber()))
				&& this.getRegisterNumber() == castOther.getRegisterNumber() || (this
						.getRegisterNumber() != null
						&& castOther.getRegisterNumber() != null && this
						.getRegisterNumber().equals(
								castOther.getRegisterNumber()))
				&& this.getAccountExpired() == castOther.getAccountExpired() || (this
						.getAccountExpired() != null
						&& castOther.getAccountExpired() != null && this
						.getAccountExpired().equals(
								castOther.getAccountExpired()))
				&& this.getStudDiscontinueDesc() == castOther
						.getStudDiscontinueDesc() || (this
						.getStudDiscontinueDesc() != null
						&& castOther.getStudDiscontinueDesc() != null && this
						.getStudDiscontinueDesc().equals(
								castOther.getStudDiscontinueDesc()))
				&& this.getDateOfJoining() == castOther.getDateOfJoining() || (this
						.getDateOfJoining() != null
						&& castOther.getDateOfJoining() != null && this
						.getDateOfJoining()
						.equals(castOther.getDateOfJoining()))
				&& this.getDateOfBirth() == castOther.getDateOfBirth() || (this
						.getDateOfBirth() != null
						&& castOther.getDateOfBirth() != null && this
						.getDateOfBirth().equals(castOther.getDateOfBirth()))
				&& this.getFatherName() == castOther.getFatherName() || (this
						.getFatherName() != null
						&& castOther.getFatherName() != null && this
						.getFatherName().equals(castOther.getFatherName()))
				&& this.getMotherName() == castOther.getMotherName() || (this
						.getMotherName() != null
						&& castOther.getMotherName() != null && this
						.getMotherName().equals(castOther.getMotherName()))
				&& this.getMotherOccupation() == castOther
						.getMotherOccupation() || (this.getMotherOccupation() != null
						&& castOther.getMotherOccupation() != null && this
						.getMotherOccupation().equals(
								castOther.getMotherOccupation()))
				&& this.getParentEmail() == castOther.getParentEmail() || (this
						.getParentEmail() != null
						&& castOther.getParentEmail() != null && this
						.getParentEmail().equals(castOther.getParentEmail()))
				&& this.getAnnualIncome() == castOther.getAnnualIncome() || (this
						.getAnnualIncome() != 0
						&& castOther.getAnnualIncome() != 0 && this
						.getAnnualIncome() == castOther.getAnnualIncome())
				&& this.getBloodGroup() == castOther.getBloodGroup() || (this
						.getBloodGroup() != null
						&& castOther.getBloodGroup() != null && this
						.getBloodGroup().equals(castOther.getBloodGroup()))
				&& this.getMobileNumber() == castOther.getMobileNumber() || (this
						.getMobileNumber() != null
						&& castOther.getMobileNumber() != null && this
						.getMobileNumber().equals(castOther.getMobileNumber()))
				&& this.getPhoneNumber() == castOther.getPhoneNumber() || (this
						.getPhoneNumber() != null
						&& castOther.getPhoneNumber() != null && this
						.getPhoneNumber().equals(castOther.getPhoneNumber()))
				&& this.getNationality() == castOther.getNationality() || (this
						.getNationality() != null
						&& castOther.getNationality() != null && this
						.getNationality().equals(castOther.getNationality()))
				&& this.getMotherToungId() == castOther.getMotherToungId() || (this
						.getMotherToungId() != 0
						&& castOther.getMotherToungId() != 0 && this
						.getMotherToungId()
						== castOther.getMotherToungId())
				&& this.getStreetName() == castOther.getStreetName() || (this
						.getStreetName() != null
						&& castOther.getStreetName() != null && this
						.getStreetName().equals(castOther.getStreetName()))
				&& this.getCity() == castOther.getCity() || (this.getCity() != null
						&& castOther.getCity() != null && this.getCity()
						.equals(castOther.getCity()))
				&& this.getPostalCode() == castOther.getPostalCode() || (this
						.getPostalCode() != null
						&& castOther.getPostalCode() != null && this
						.getPostalCode().equals(castOther.getPostalCode()))
				&& this.getIdentification1() == castOther
						.getIdentification1() || (this.getIdentification1() != null
						&& castOther.getIdentification1() != null && this
						.getIdentification1().equals(
								castOther.getIdentification1()))
				&& this.getIdentification2() == castOther
						.getIdentification2() || (this.getIdentification2() != null
						&& castOther.getIdentification2() != null && this
						.getIdentification2().equals(
								castOther.getIdentification2()))
				&& this.getSubCastName() == castOther.getSubCastName() || (this
						.getSubCastName() != null
						&& castOther.getSubCastName() != null && this
						.getSubCastName().equals(castOther.getSubCastName()))
				&& this.getReligion() == castOther.getReligion() || (this
						.getReligion() != null
						&& castOther.getReligion() != null && this
						.getReligion().equals(castOther.getReligion()))
				&& this.getMotherToung() == castOther.getMotherToung() || (this
						.getMotherToung() != null
						&& castOther.getMotherToung() != null && this
						.getMotherToung().equals(castOther.getMotherToung()))
				&& this.getOrganizationName() == castOther
						.getOrganizationName() || (this.getOrganizationName() != null
						&& castOther.getOrganizationName() != null && this
						.getOrganizationName().equals(
								castOther.getOrganizationName()))
				&& this.getCustAddressLine1() == castOther
						.getCustAddressLine1() || (this.getCustAddressLine1() != null
						&& castOther.getCustAddressLine1() != null && this
						.getCustAddressLine1().equals(
								castOther.getCustAddressLine1()))
				&& this.getCustCity() == castOther.getCustCity() || (this
						.getCustCity() != null
						&& castOther.getCustCity() != null && this
						.getCustCity().equals(castOther.getCustCity()))
				&& this.getCustPostalCode() == castOther.getCustPostalCode() || (this
						.getCustPostalCode() != null
						&& castOther.getCustPostalCode() != null && this
						.getCustPostalCode().equals(
								castOther.getCustPostalCode()))
				&& this.getEducationalDistrict() == castOther
						.getEducationalDistrict() || (this
						.getEducationalDistrict() != null
						&& castOther.getEducationalDistrict() != null && this
						.getEducationalDistrict().equals(
								castOther.getEducationalDistrict()))
				&& this.getRevenueDistrict() == castOther
						.getRevenueDistrict() || (this.getRevenueDistrict() != null
						&& castOther.getRevenueDistrict() != null && this
						.getRevenueDistrict().equals(
								castOther.getRevenueDistrict()))
				&& this.getSchoolCode() == castOther.getSchoolCode() || (this
						.getSchoolCode() != null
						&& castOther.getSchoolCode() != null && this
						.getSchoolCode().equals(castOther.getSchoolCode()))
				&& this.getRecognisedBy() == castOther.getRecognisedBy() || (this
						.getRecognisedBy() != null
						&& castOther.getRecognisedBy() != null && this
						.getRecognisedBy().equals(castOther.getRecognisedBy()))
				&& this.getBoardOfEducation() == castOther
						.getBoardOfEducation() || (this.getBoardOfEducation() != null
						&& castOther.getBoardOfEducation() != null && this
						.getBoardOfEducation().equals(
								castOther.getBoardOfEducation()))
				&& this.getAffiliationNumber() == castOther
						.getAffiliationNumber() || (this
						.getAffiliationNumber() != null
						&& castOther.getAffiliationNumber() != null && this
						.getAffiliationNumber().equals(
								castOther.getAffiliationNumber()))
				&& this.getCommunityNumber() == castOther
						.getCommunityNumber() || (this.getCommunityNumber() != null
						&& castOther.getCommunityNumber() != null && this
						.getCommunityNumber().equals(
								castOther.getCommunityNumber()))
				&& this.getSslcNumber() == castOther.getSslcNumber() || (this
						.getSslcNumber() != null
						&& castOther.getSslcNumber() != null && this
						.getSslcNumber().equals(castOther.getSslcNumber()))
				&& this.getTmrNumber() == castOther.getTmrNumber() || (this
						.getTmrNumber() != null
						&& castOther.getTmrNumber() != null && this
						.getTmrNumber().equals(castOther.getTmrNumber()))
				&& this.getClassJoined() == castOther.getClassJoined() || (this
						.getClassJoined() != null
						&& castOther.getClassJoined() != null && this
						.getClassJoined().equals(castOther.getClassJoined()))
				&& this.getRelievingDate() == castOther.getRelievingDate() || (this
						.getRelievingDate() != null
						&& castOther.getRelievingDate() != null && this
						.getRelievingDate()
						.equals(castOther.getRelievingDate()))
				&& this.getScholarShipInfo() == castOther
						.getScholarShipInfo() || this.getScholarShipInfo() != null
						&& castOther.getScholarShipInfo() != null && this
						.getScholarShipInfo().equals(
								castOther.getScholarShipInfo());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getFirstName() == null ? 0 : this.getFirstName().hashCode());
		result = 37 * result
				+ (getLastName() == null ? 0 : this.getLastName().hashCode());
		result = 37 * result
				+ (getClassName() == null ? 0 : this.getClassName().hashCode());
		result = 37 * result
				+ (getSection() == null ? 0 : this.getSection().hashCode());
		result = 37
				* result
				+ (getHigherStandard() == null ? 0 : this.getHigherStandard()
						.hashCode());
		result = 37 * result
				+ (getFullName() == null ? 0 : this.getFullName().hashCode());
		result = 37 * result
				+ (getCastName() == null ? 0 : this.getCastName().hashCode());
		result = 37
				* result
				+ (getAdmissionNumber() == null ? 0 : this.getAdmissionNumber()
						.hashCode());
		result = 37
				* result
				+ (getRegisterNumber() == null ? 0 : this.getRegisterNumber()
						.hashCode());
		result = 37
				* result
				+ (getAccountExpired() == null ? 0 : this.getAccountExpired()
						.hashCode());
		result = 37
				* result
				+ (getStudDiscontinueDesc() == null ? 0 : this
						.getStudDiscontinueDesc().hashCode());
		result = 37
				* result
				+ (getDateOfJoining() == null ? 0 : this.getDateOfJoining()
						.hashCode());
		result = 37
				* result
				+ (getDateOfBirth() == null ? 0 : this.getDateOfBirth()
						.hashCode());
		result = 37
				* result
				+ (getFatherName() == null ? 0 : this.getFatherName()
						.hashCode());
		result = 37
				* result
				+ (getMotherName() == null ? 0 : this.getMotherName()
						.hashCode());
		result = 37
				* result
				+ (getMotherOccupation() == null ? 0 : this
						.getMotherOccupation().hashCode());
		result = 37
				* result
				+ (getParentEmail() == null ? 0 : this.getParentEmail()
						.hashCode());
		result = 37
				* result
				+ (getBloodGroup() == null ? 0 : this.getBloodGroup()
						.hashCode());
		result = 37
				* result
				+ (getMobileNumber() == null ? 0 : this.getMobileNumber()
						.hashCode());
		result = 37
				* result
				+ (getPhoneNumber() == null ? 0 : this.getPhoneNumber()
						.hashCode());
		result = 37
				* result
				+ (getNationality() == null ? 0 : this.getNationality()
						.hashCode());
		result = 37
				* result
				+ (getStreetName() == null ? 0 : this.getStreetName()
						.hashCode());
		result = 37 * result
				+ (getCity() == null ? 0 : this.getCity().hashCode());
		result = 37
				* result
				+ (getPostalCode() == null ? 0 : this.getPostalCode()
						.hashCode());
		result = 37
				* result
				+ (getIdentification1() == null ? 0 : this.getIdentification1()
						.hashCode());
		result = 37
				* result
				+ (getIdentification2() == null ? 0 : this.getIdentification2()
						.hashCode());
		result = 37
				* result
				+ (getSubCastName() == null ? 0 : this.getSubCastName()
						.hashCode());
		result = 37 * result
				+ (getReligion() == null ? 0 : this.getReligion().hashCode());
		result = 37
				* result
				+ (getMotherToung() == null ? 0 : this.getMotherToung()
						.hashCode());
		result = 37
				* result
				+ (getOrganizationName() == null ? 0 : this
						.getOrganizationName().hashCode());
		result = 37
				* result
				+ (getCustAddressLine1() == null ? 0 : this
						.getCustAddressLine1().hashCode());
		result = 37 * result
				+ (getCustCity() == null ? 0 : this.getCustCity().hashCode());
		result = 37
				* result
				+ (getCustPostalCode() == null ? 0 : this.getCustPostalCode()
						.hashCode());
		result = 37
				* result
				+ (getEducationalDistrict() == null ? 0 : this
						.getEducationalDistrict().hashCode());
		result = 37
				* result
				+ (getRevenueDistrict() == null ? 0 : this.getRevenueDistrict()
						.hashCode());
		result = 37
				* result
				+ (getSchoolCode() == null ? 0 : this.getSchoolCode()
						.hashCode());
		result = 37
				* result
				+ (getRecognisedBy() == null ? 0 : this.getRecognisedBy()
						.hashCode());
		result = 37
				* result
				+ (getBoardOfEducation() == null ? 0 : this
						.getBoardOfEducation().hashCode());
		result = 37
				* result
				+ (getAffiliationNumber() == null ? 0 : this
						.getAffiliationNumber().hashCode());
		result = 37
				* result
				+ (getCommunityNumber() == null ? 0 : this.getCommunityNumber()
						.hashCode());
		result = 37
				* result
				+ (getSslcNumber() == null ? 0 : this.getSslcNumber()
						.hashCode());
		result = 37 * result
				+ (getTmrNumber() == null ? 0 : this.getTmrNumber().hashCode());
		result = 37
				* result
				+ (getClassJoined() == null ? 0 : this.getClassJoined()
						.hashCode());
		result = 37
				* result
				+ (getRelievingDate() == null ? 0 : this.getRelievingDate()
						.hashCode());
		result = 37
				* result
				+ (getScholarShipInfo() == null ? 0 : this.getScholarShipInfo()
						.hashCode());
		return result;
	}
	@Transient
	public String getCourseAcademicYears(){
		StringBuffer courseAcademicYears = new StringBuffer();
		if(!ObjectFunctions.isNullOrEmpty(this.dateOfJoining) && !ObjectFunctions.isNullOrEmpty(this.academicYear)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			courseAcademicYears.append(sdf.format(this.getDateOfJoining()));
			courseAcademicYears.append("-").append(Long.valueOf(this.academicYear.split("-")[0])+1);
		}
		return courseAcademicYears.toString();
	}
	@Transient
	public String getStudiedClasses(){
		StringBuffer studiedClasses = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(this.classJoined)){ 
			String classJoined = this.classJoined;
			String[] splitClassJoined = classJoined.split(" -");
			if(!splitClassJoined[0].equalsIgnoreCase(this.className)){
				studiedClasses.append(this.classJoined);
			}
		}
		if(!ObjectFunctions.isNullOrEmpty(this.className)){
			if(studiedClasses.length() > 0)
				studiedClasses.append("-");
			studiedClasses.append(this.className);
		}
		return studiedClasses.toString();
	}
	@Transient
	public String getClassJoinedStr(){
		if(StringFunctions.isNullOrEmpty(this.classJoined))
			return "";
		else
			return this.classJoined;
	}
	/**
	 * @return the totalWorkingDaysCount
	 */
	@Transient
	public int getTotalWorkingDaysCount() {
		return totalWorkingDaysCount;
	}

	/**
	 * @param totalWorkingDaysCount the totalWorkingDaysCount to set
	 */
	public void setTotalWorkingDaysCount(int totalWorkingDaysCount) {
		this.totalWorkingDaysCount = totalWorkingDaysCount;
	}

	/**
	 * @return the presentDaysCount
	 */
	@Transient
	public int getPresentDaysCount() {
		return presentDaysCount;
	}

	/**
	 * @param presentDaysCount the presentDaysCount to set
	 */
	public void setPresentDaysCount(int presentDaysCount) {
		this.presentDaysCount = presentDaysCount;
	}
	@Transient
	public String getDateOfBirthStr() {
		if(ObjectFunctions.isNullOrEmpty(this.dateOfBirth))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.dateOfBirth);
	}
	@Transient
	public String getStudentAddress() {
		StringBuffer buffer = new StringBuffer();
		if (StringFunctions.isNotNullOrEmpty(this.addressLine1)) {
			buffer.append(this.addressLine1);
		}
		if(StringFunctions.isNotNullOrEmpty(this.addressLine2)) {
			if(StringFunctions.isNotNullOrEmpty(this.addressLine1))
				buffer.append(", ");
			buffer.append(this.addressLine2);
		}
		if (StringFunctions.isNotNullOrEmpty(this.city) && StringFunctions.isNotNullOrEmpty(this.postalCode)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.city).append("-").append(this.postalCode);
		}else if(StringFunctions.isNotNullOrEmpty(this.city)){
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.city);
		}else{
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.postalCode);
		}
		return buffer.toString();
	}
	@Transient
	public String getDateOfJoiningStr(){
		if(ObjectFunctions.isNullOrEmpty(this.dateOfJoining))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.dateOfJoining);
	}

	@Transient
	public String getDateOfJoiningYear(){
		StringBuffer years = new StringBuffer();
		if(!ObjectFunctions.isNullOrEmpty(this.dateOfJoining)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			DecimalFormat df = new DecimalFormat("#00");
			String year = sdf.format(this.dateOfJoining);
			years.append(year).append("-").append(df.format(Long.valueOf(year.substring(1))+1));
		}
		return years.toString();
	}
	@Transient
	public String getToDayDate(){
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, new Date());
	}
	@Transient
	public String getStudentJoiningYear() {
		if(ObjectFunctions.isNullOrEmpty(this.dateOfJoining))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, this.dateOfJoining);
	}
	@Transient
	public String getStudentCompletionYear() {
		if(ObjectFunctions.isNullOrEmpty(this.academicYear))
			return "";
		else
			return String.valueOf(Long.valueOf(this.academicYear.split("-")[0])+1);
	}
	@Transient
	public String getReligionAndCastName(){
		StringBuffer religionAndCast = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(this.religion) && StringFunctions.isNotNullOrEmpty(this.castName)){
			religionAndCast.append(this.religion).append(" and ").append(this.castName);
		}else if(StringFunctions.isNotNullOrEmpty(this.religion))
			religionAndCast.append(this.religion);
		else if(StringFunctions.isNotNullOrEmpty(this.castName))
			religionAndCast.append(this.castName);
		return religionAndCast.toString();
	}
	@Transient
	public String getStudJoingMonthAndYear(){
		if(!ObjectFunctions.isNullOrEmpty(this.dateOfJoining))
			return new SimpleDateFormat("MMM - yyyy").format(this.dateOfJoining);
		else
			return "";
	}
	@Transient
	public String getStudCompletionMonthAndYear(){
		StringBuffer monthAndDate = new StringBuffer();
		if(!ObjectFunctions.isNullOrEmpty(this.endDate) && StringFunctions.isNotNullOrEmpty(this.academicYear)){
			monthAndDate.append(new SimpleDateFormat("MMM").format(this.endDate)).append(" - ").append(Long.valueOf(this.academicYear.split("-")[0])+1);
			return monthAndDate.toString();
		}else
			return monthAndDate.toString();
	}
	@Transient
	public String getFeeDueMonthAndYear() {
		return feeDueMonthAndYear;
	}
	public void setFeeDueMonthAndYear(String feeDueMonthAndYear) {
		this.feeDueMonthAndYear = feeDueMonthAndYear;
	}
	
  @Transient
	public String getSchoolIdCardAttachmentFilePath() {
	//String imagePath=ServletActionContext.getServletContext().getRealPath(getHrefOriginalAttachmentFilePath());
    return getHrefOriginalAttachmentFilePath();
	}
	  
  @Transient
	public String getHrefOriginalAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			//return getImagePath().substring(1).concat(getImageName());
			return getImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}
  public String getStateName() {
	return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
   @Transient
	public String getStudentAddressWithoutPostalCode() {
		StringBuffer buffer = new StringBuffer();
		if (StringFunctions.isNotNullOrEmpty(this.streetName)) {
			buffer.append(this.streetName);
		}
		if (StringFunctions.isNotNullOrEmpty(this.city)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.city);
		}if(StringFunctions.isNotNullOrEmpty(this.stateName)){
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.stateName);
		}
		return buffer.toString();
	}
	@Transient
	public String getToDayYear() {
			return DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, new Date());
	}
	@Transient
	public String getStudentDateOfBirthInWords() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
		if(!ObjectFunctions.isNullOrEmpty(this.getDateOfBirth()))
			return DateFunctions.DateInWords(sdf.format(this.getDateOfBirth()));
		else
			return "";
	}
	
	@Transient
	public String getEndDateStr() {
		if(ObjectFunctions.isNullOrEmpty(this.endDate))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.endDate);
	}
	// this year as considaer as today year this changes for trillium school study  
	@Transient
	public String getClName() {
		return getClassAndSection();
	}
	@Transient
	public String getDobStr() {
		if(ObjectFunctions.isNullOrEmpty(this.dateOfBirth))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.dateOfBirth);
	}
	@Transient
	public String getDjStr() {
		if(ObjectFunctions.isNullOrEmpty(this.dateOfJoining))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, this.dateOfJoining);
	}
	@Transient
	public String getYear() { 
			return DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, new Date());
	}
	@Transient
	public String getStName() {
		if(ObjectFunctions.isNullOrEmpty(this.streetName))
			return "";
		else
			return getStreetName()+ ", ";
	}
	@Transient
	public String getCityName() {
		if(ObjectFunctions.isNullOrEmpty(this.city))
			return "";
		else
			return getCity()+ " - ";
	}
	@Transient
	public String getPsCode() {
		if(ObjectFunctions.isNullOrEmpty(this.postalCode))
			return "";
		else
			return getPostalCode()+ ", ";
	}
	
	@Transient
	public String getState() {
		if(ObjectFunctions.isNullOrEmpty(this.stateName))
			return "";
		else
			return getStateName()+ ". ";
	}
	
	@Transient
	public String getCityStr() {
		if(ObjectFunctions.isNullOrEmpty(this.city))
			return "";
		else
			return getCity();
	}
	
	@Transient
	public String getStNameStr() {
		if(ObjectFunctions.isNullOrEmpty(this.streetName))
			return "";
		else
			return getStreetName();
	}
	@Transient
	public String getStuAdmiNoAndDj() {
		StringBuffer buffer = new StringBuffer();
		if (StringFunctions.isNotNullOrEmpty(this.admissionNumber)) {
			buffer.append(this.admissionNumber);
		}
		if (!ObjectFunctions.isNullOrEmpty(this.dateOfJoining)) {
			if (StringFunctions.isNotNullOrEmpty(this.admissionNumber))
				buffer.append(" & ");
			buffer.append(getDateOfJoiningStr());
		}
		return buffer.toString();
	}
	@Transient
	public String getStudentCastName(){
		if(StringFunctions.isNullOrEmpty(this.castName))
			return "";
		else
			return this.castName;
	}
	@Transient
	public int getSlNo(){
		return this.serialNumber;
	}
	@Transient					
	public String getAdNo(){
		if(StringFunctions.isNullOrEmpty(this.admissionNumber))
			return "";
		else
			return this.admissionNumber;
	}
	@Transient
	public String getGenderStr() {
		if(StringFunctions.isNotNullOrEmpty(this.gender)){
			if(this.gender.equalsIgnoreCase("M")){
				return "Male";
			}else if(this.gender.equalsIgnoreCase("F")){
				return "FeMale";
			}
		}
		return "";
	}@Transient
	public String getClJoStr(){
		if(StringFunctions.isNullOrEmpty(this.classJoined))
			return "";
		else
			return this.classJoined;
	}
	// RaviTeja For Boston school 
	@Transient
	public String getScorSt(){
		if("SC".equalsIgnoreCase(getCastName()) || "S.C".equalsIgnoreCase(getCastName())){
			return "SC";
		}else if("ST".equalsIgnoreCase(getCastName()) || "S.T".equalsIgnoreCase(getCastName())){
			return "ST";
		}
		return "";
	}
	@Transient
	public String getPOBStr(){ // POB means Place of birth
		if(StringFunctions.isNullOrEmpty(this.placeOfBirth))
			return "";
		else
			return this.placeOfBirth;
	}
	public String getEnrollmentCode() {
		return enrollmentCode;
	}
	public void setEnrollmentCode(String enrollmentCode) {
		this.enrollmentCode = enrollmentCode;
	}
	@Transient
	public String getEnrollmentStr(){
		if(!ObjectFunctions.isNullOrEmpty(this.enrollmentCode))
			return getEnrollmentCode();
		else
			return "";
	}
	@Transient
	public String getTcIssueDateStr() {
		if(ObjectFunctions.isNullOrEmpty(this.tcIssueDate))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.tcIssueDate);
	}
	@Transient
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	@Transient
	public String getShortAcademicYear() {
		StringBuffer sb = new StringBuffer();
		if (!StringFunctions.isNullOrEmpty(getAcademicYear())) {
			sb.append(getAcademicYear().replaceFirst(getAcademicYear().substring(0,2)," "));
		}
		return sb.toString();
	}
	
}