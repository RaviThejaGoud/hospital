package com.urt.persistence.model.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.annotations.Type;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;

@Entity
@Table(name = "vw_onlineApplicationDetails")
public class OnlineApplicationDetailsView  implements Serializable,Cloneable,Comparable {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String parentEmail;   // required
    private String lastName;
    private String firstName;
    private String fatherName;
    private String motherName;
    private String gender=Constants.YES_STRING;
    private Date dateOfBirth;
    protected String phoneNumber;
    protected String bloodGroup;   
	private String mobileNumber;
	protected String className;
    private String tAddrCity;
    private String tAddrState ;
    private String tAddrStateName ;
    private String tAddrPostalCode;
    private String applicationNumber;
    protected String applicationStatus=Constants.PENDING_STATUS;
    private String occupation;
    private long academicYearId;                  
    protected boolean admissionStatus;
    protected long custId;
    protected Date admissionEndDate;
    protected boolean testConducted;
    protected long applicationFee;
    protected Date entranceDate;
    protected String admissionContent;
    protected Double entranceMarks;
	private long classId;
    private String lastSchool;
    private String lastClassAttended;
    private String tAddrStreetName; 
    private String nationality;
    private long applicationId;
    private long castId;
    protected long subCastId;
    private String religion;
    private String academicYearStatus;
    protected String status;
    private String academicYearStr;
    protected boolean present; 
    protected String rejectApplicationDesc;
    protected double entranceExamTotalMarks;
   // protected String tempararyAddress;
    private String fatherQualification;
    private String motherQualification;
    private String designation;
    private long annualIncome;
    private String transferCertificateNo;
    private String caddrState;
	private String caddrPostalCode;
    private String caddrStreetName;
    private String caddrCity;
    private long religionId;
    private String transportMode;
    private String collectedDocuments;
    private String imageId;
    private String imagePath;
    private String imageName;
    private String castName;
    private String subCastName;
    private long categoryId;
    private Long boardingPointId;
    private Long vehicleAcademicDetailsId;
    private Long routeId;
	private String categoryName;
	private String placeOfBirth;
	private String studentEmail;
	private String studentMobile;
	private String hostelMode;
    private String aadharCardNumber; 
    private Date createdDate; 
    protected long registrationFee;
    protected long prospectiveFee;
    private String receiptNumber;
    protected double entranceExamPassMarks;
    private double committedFee;
    private String sslcNumber;
	private String tmrNumber;
    private String communityNumber; 
    private boolean phId;
    private String studentUniqueNumber;
    private String visionL;
    private String visionR;
    private double height;
	private double weight;
    private String goals;
    private String strengths;
    private String interestsAndHobbies;
    private String responsibilities;
    private String achievements;
    private String identification1;
    private String identification2;
	private long motherToungId;
	private String oralHygiene;
	private String rationCardNumber;
	private Date dateOfJoining;
	private String teeth;
	private String prefferedHospital;
	private String familyDoctor;
	private String scholarShipInfo; 
	protected String registerNumber; 
	private String rollNumber;
	private String motherToung;
	private String bplStatus;
	private String rteStatus;
	private String physicallyHandicappedDesc;
    private String isPHDocsUploaded;
    
    private String fatherAadharNumber;
   	private String motherAadharNumber;
   	
   	private String admissionNumberAutoGenerationStatus;
   	
   	
   	
   	
   

	public String getFatherAadharNumber() {
   		return fatherAadharNumber;
   	}

   	public void setFatherAadharNumber(String fatherAadharNumber) {
   		this.fatherAadharNumber = fatherAadharNumber;
   	}

   	public String getMotherAadharNumber() {
   		return motherAadharNumber;
   	}

   	public void setMotherAadharNumber(String motherAadharNumber) {
   		this.motherAadharNumber = motherAadharNumber;
   	}
	
	public String getPhysicallyHandicappedDesc() {
		return physicallyHandicappedDesc;
	}

	public void setPhysicallyHandicappedDesc(String physicallyHandicappedDesc) {
		this.physicallyHandicappedDesc = physicallyHandicappedDesc;
	}

	public String getIsPHDocsUploaded() {
		return isPHDocsUploaded;
	}

	public void setIsPHDocsUploaded(String isPHDocsUploaded) {
		this.isPHDocsUploaded = isPHDocsUploaded;
	}

	@Column(name = "bplStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getBplStatus() {
		return bplStatus;
	}

	public void setBplStatus(String bplStatus) {
		this.bplStatus = bplStatus;
	}
	@Column(name = "rteStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getRteStatus() {
		return rteStatus;
	}

	public void setRteStatus(String rteStatus) {
		this.rteStatus = rteStatus;
	}
	public String getMotherToung() {
		return motherToung;
	}

	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
	}
	
	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getScholarShipInfo() {
		return scholarShipInfo;
	}

	public void setScholarShipInfo(String scholarShipInfo) {
		this.scholarShipInfo = scholarShipInfo;
	}
	
	
	@Column(name = "familyDoctor", nullable = true, length = 200)
    public String getFamilyDoctor() {
		return familyDoctor;
	}

	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}
    
	@Column(name = "prefferedHospital", nullable = true, length = 200)
	public String getPrefferedHospital() {
		return prefferedHospital;
	}

	public void setPrefferedHospital(String prefferedHospital) {
		this.prefferedHospital = prefferedHospital;
	}
	
	public String getSslcNumber() {
		return sslcNumber;
	}

	public void setSslcNumber(String sslcNumber) {
		this.sslcNumber = sslcNumber;
	}

	public String getTmrNumber() {
		return tmrNumber;
	}

	public void setTmrNumber(String tmrNumber) {
		this.tmrNumber = tmrNumber;
	}

	public String getCommunityNumber() {
		return communityNumber;
	}

	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}

	@Column(name = "phId", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
    public boolean isPhId() {
		return phId;
	}
	public void setPhId(boolean phId) {
		this.phId = phId;
	}


	public String getStudentUniqueNumber() {
		return studentUniqueNumber;
	}

	public void setStudentUniqueNumber(String studentUniqueNumber) {
		this.studentUniqueNumber = studentUniqueNumber;
	}

	public String getVisionL() {
		return visionL;
	}

	public void setVisionL(String visionL) {
		this.visionL = visionL;
	}

	public String getVisionR() {
		return visionR;
	}

	public void setVisionR(String visionR) {
		this.visionR = visionR;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getStrengths() {
		return strengths;
	}

	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}

	public String getInterestsAndHobbies() {
		return interestsAndHobbies;
	}

	public void setInterestsAndHobbies(String interestsAndHobbies) {
		this.interestsAndHobbies = interestsAndHobbies;
	}

	public String getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
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

	public long getMotherToungId() {
		return motherToungId;
	}

	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}

	public String getTeeth() {
		return teeth;
	}

	public void setTeeth(String teeth) {
		this.teeth = teeth;
	}

	public String getOralHygiene() {
		return oralHygiene;
	}

	public void setOralHygiene(String oralHygiene) {
		this.oralHygiene = oralHygiene;
	}

	public String getRationCardNumber() {
		return rationCardNumber;
	}

	public void setRationCardNumber(String rationCardNumber) {
		this.rationCardNumber = rationCardNumber;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	
    /**
   	 * @return the tAddrStateName
   	 */
   	public String gettAddrStateName() {
   		return tAddrStateName;
   	}

   	/**
   	 * @param tAddrStateName the tAddrStateName to set
   	 */
   	public void settAddrStateName(String tAddrStateName) {
   		this.tAddrStateName = tAddrStateName;
   	}

    
	public double getCommittedFee() {
		return committedFee;
	}

	public void setCommittedFee(double committedFee) {
		this.committedFee = committedFee;
	}

	public double getEntranceExamPassMarks() {
    		return entranceExamPassMarks;
    	}

    	public void setEntranceExamPassMarks(double entranceExamPassMarks) {
    		this.entranceExamPassMarks = entranceExamPassMarks;
    	}
    
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	/**
	 * @return the registrationFee
	 */
	public long getRegistrationFee() {
		return registrationFee;
	}
	/**
	 * @param registrationFee the registrationFee to set
	 */
	public void setRegistrationFee(long registrationFee) {
		this.registrationFee = registrationFee;
	}
	/**
	 * @return the prospectiveFee
	 */
	public long getProspectiveFee() {
		return prospectiveFee;
	}
	/**
	 * @param prospectiveFee the prospectiveFee to set
	 */
	public void setProspectiveFee(long prospectiveFee) {
		this.prospectiveFee = prospectiveFee;
	}
	/**
	 * @return the hostelMode
	 */
	public String getHostelMode() {
		return hostelMode;
	}
	/**
	 * @param hostelMode the hostelMode to set
	 */
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}
	/**
	 * @return the studentMobile
	 */
	public String getStudentMobile() {
		return studentMobile;
	}
	/**
	 * @param studentMobile the studentMobile to set
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
	 * @param studentEmail the studentEmail to set
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
	 * @param placeOfBirth the placeOfBirth to set
	 */
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	/**
	 * @return the skillTypeName
	 */
	public String getSkillTypeName() {
		return skillTypeName;
	}
	/**
	 * @param skillTypeName the skillTypeName to set
	 */
	public void setSkillTypeName(String skillTypeName) {
		this.skillTypeName = skillTypeName;
	}

	private String skillTypeName;
	
	
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    
    
    /**
	 * @return the routeId
	 */
	public Long getRouteId() {
		return routeId;
	}
	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	/**
	 * @return the boardingPointId
	 */
	public Long getBoardingPointId() {
		return boardingPointId;
	}
	/**
	 * @param boardingPointId the boardingPointId to set
	 */
	public void setBoardingPointId(Long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	/**
	 * @return the vehicleAcademicDetailsId
	 */
	public Long getVehicleAcademicDetailsId() {
		return vehicleAcademicDetailsId;
	}
	/**
	 * @param vehicleAcademicDetailsId the vehicleAcademicDetailsId to set
	 */
	public void setVehicleAcademicDetailsId(Long vehicleAcademicDetailsId) {
		this.vehicleAcademicDetailsId = vehicleAcademicDetailsId;
	}
	@Column(name = "categoryId", nullable = false, updatable = true, columnDefinition = "int default 0")
	public long getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId;
	}
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return the collectedDocuments
	 */
	public String getCollectedDocuments() {
		return collectedDocuments;
	}
	/**
	 * @param collectedDocuments the collectedDocuments to set
	 */
	public void setCollectedDocuments(String collectedDocuments) {
		this.collectedDocuments = collectedDocuments;
	}
    
    @Column(name = "transportMode", nullable = true, length = 5,columnDefinition="char(1) default 'O'")
    public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}   
    @Column(name = "religionId", nullable = true, length = 10)
    public long getReligionId() {
		return religionId;
	}
	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}
	@Column(name = "caddrState", nullable = true, length = 20)
    public String getCaddrState() {
		return caddrState;
	}
	public void setCaddrState(String caddrState) {
		this.caddrState = caddrState;
	}
	@Column(name = "caddrPostalCode", nullable = true, length = 12)
	public String getCaddrPostalCode() {
		return caddrPostalCode;
	}
	public void setCaddrPostalCode(String caddrPostalCode) {
		this.caddrPostalCode = caddrPostalCode;
	}
	@Column(name = "caddrStreetName", nullable = true, length = 200)
	public String getCaddrStreetName() {
		return caddrStreetName;
	}
	public void setCaddrStreetName(String caddrStreetName) {
		this.caddrStreetName = caddrStreetName;
	}
	@Column(name = "caddrCity", nullable = true, length = 64)
	public String getCaddrCity() {
		return caddrCity;
	}
	public void setCaddrCity(String caddrCity) {
		this.caddrCity = caddrCity;
	}
    public long getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(long annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getTransferCertificateNo() {
		return transferCertificateNo;
	}
	public void setTransferCertificateNo(String transferCertificateNo) {
		this.transferCertificateNo = transferCertificateNo;
	}
   /* public String getTempararyAddress() {
		return tempararyAddress;
	}
	public void setTempararyAddress(String tempararyAddress) {
		this.tempararyAddress = tempararyAddress;
	}*/
	public String getFatherQualification() {
		return fatherQualification;
	}
	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}
	public String getMotherQualification() {
		return motherQualification;
	}
	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	 
	public double getEntranceExamTotalMarks() {
		return entranceExamTotalMarks;
	}
	public void setEntranceExamTotalMarks(double entranceExamTotalMarks) {
		this.entranceExamTotalMarks = entranceExamTotalMarks;
	}
	
	@Column(name="rejectApplicationDesc",nullable=true,length=1024)
    public String getRejectApplicationDesc() {
		return rejectApplicationDesc;
	}
	public void setRejectApplicationDesc(String rejectApplicationDesc) {
		this.rejectApplicationDesc = rejectApplicationDesc;
	}
    
    @Column(name = "present", nullable = false, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
    
	@Column(name = "status", nullable = true, length = 1) 
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
    /**
	 * @return the academicYearStatus
	 */
    public String getAcademicYearStatus() {
		return academicYearStatus;
	}
	public void setAcademicYearStatus(String academicYearStatus) {
		this.academicYearStatus = academicYearStatus;
	}
	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return the subCastId
	 */
    @Column(name = "subCastId", nullable = true, length =10)
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
	 * @return the castId
	 */
    @Column(name = "castId", nullable = true, length =10)
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
	 * @return the subcastId
	 */
	
    
    /**
	 * @return the applicationId
	 */
    @Id
	@Column( name="applicationId", unique=false, nullable=false, updatable=false )
	public long getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the nationality
	 */
    @Column(name = "nationality", nullable = true, length = 30)
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
	 * @return the tAddrStreetName
	 */
    @Column(name = "tAddrStreetName", nullable = true, length = 200)
	public String getTAddrStreetName() {
		return tAddrStreetName;
	}
	/**
	 * @param tAddrStreetName the tAddrStreetName to set
	 */
	public void setTAddrStreetName(String tAddrStreetName) {
		this.tAddrStreetName = tAddrStreetName;
	}
	
    public OnlineApplicationDetailsView() {
    	    
    }

   /* public OnlineApplicationDetailsView(long id) {
        setId(id);
    }*/
	/**
	 * @return the parentEmail
	 */
    
	public String getParentEmail() {
		return parentEmail;
	}

	/**
	 * @param parentEmail the parentEmail to set
	 */
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
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
	 * @return the motherName
	 */
	 
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
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
	 * @return the classId
	 */
	 
	public String getClassName() {
		return className;
	}

	/**
	 * @param classId the classId to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the custId
	 */
	 
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the tAddrCity
	 */
	
	public String getTAddrCity() {
		return tAddrCity;
	}

	/**
	 * @param city the tAddrCity to set
	 */
	public void setTAddrCity(String tAddrCity) {
		this.tAddrCity = tAddrCity;
	}

	/**
	 * @return the tAddrState
	 */
	
	public String getTAddrState() {
		return tAddrState;
	}

	/**
	 * @param state the tAddrState to set
	 */
	public void setTAddrState(String tAddrState) {
		this.tAddrState = tAddrState;
	}

	/**
	 * @return the tAddrPostalCode
	 */
	@Column(name = "tAddrPostalCode", nullable = true, length = 12)
	public String getTAddrPostalCode() {
		return tAddrPostalCode;
	}

	/**
	 * @param tAddrPostalCode the tAddrPostalCode to set
	 */
	public void setTAddrPostalCode(String tAddrPostalCode) {
		this.tAddrPostalCode = tAddrPostalCode;
	}

	  /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    @Override
	public int compareTo(Object object) {
        OnlineApplicationDetailsView myClass = (OnlineApplicationDetailsView) object;
        return new CompareToBuilder().append(this.className,
                myClass.className).toComparison();
    }
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * @return the applicationNumber
	 */
	
	public String getApplicationNumber() {
		return applicationNumber;
	}

	/**
	 * @param applicationNumber the applicationNumber to set
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	 @Transient
	    public String getChildrenFullPersonName() {
	    	StringBuffer sbf = new StringBuffer();
	    	if(!StringFunctions.isNullOrEmpty(this.getFirstName()))
	    	{
	    		sbf.append(this.getFirstName());
	    	}
	    	if(!StringFunctions.isNullOrEmpty(this.getLastName()))
	    	{
	    		sbf.append(" "+this.getLastName());
	    	}
	    	return sbf.toString();
	    }

	

	/**
	 * @return the occupation
	 */
	 
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the academicYear
	 */
	@Column(name = "academicYearId", nullable = true, length = 64)
	public long getAcademicYearId() {
		return academicYearId;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}
	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}
	/**
	 * @return the applicationStatus
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the status
	 */
	public boolean isAdmissionStatus() {
		return admissionStatus;
	}
	/**
	 * @return the AdmissionNumberAutoGenerationStatus
	 */
	public String getAdmissionNumberAutoGenerationStatus() {
		return admissionNumberAutoGenerationStatus;
	}
	
	public void setAdmissionNumberAutoGenerationStatus(String admissionNumberAutoGenerationStatus) {
		this.admissionNumberAutoGenerationStatus = admissionNumberAutoGenerationStatus;
	}

	/**
	 * @param status the status to set
	 */
	public void setAdmissionStatus(boolean admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	/**
	 * @return the admissionEndDate
	 */
	public Date getAdmissionEndDate() {
		return admissionEndDate;
	}

	/**
	 * @param admissionEndDate the admissionEndDate to set
	 */
	public void setAdmissionEndDate(Date admissionEndDate) {
		this.admissionEndDate = admissionEndDate;
	}

	/**
	 * @return the testConducted
	 */
	public boolean isTestConducted() {
		return testConducted;
	}

	/**
	 * @param testConducted the testConducted to set
	 */
	public void setTestConducted(boolean testConducted) {
		this.testConducted = testConducted;
	}

	/**
	 * @return the applicationFee
	 */
	public long getApplicationFee() {
		return applicationFee;
	}

	/**
	 * @param applicationFee the applicationFee to set
	 */
	public void setApplicationFee(long applicationFee) {
		this.applicationFee = applicationFee;
	}

	/**
	 * @return the entranceDate
	 */
	public Date getEntranceDate() {
		return entranceDate;
	}

	/**
	 * @param entranceDate the entranceDate to set
	 */
	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	/**
	 * @return the entranceMarks
	 */
	public Double getEntranceMarks() {
		return entranceMarks;
	}

	/**
	 * @param entranceMarks the entranceMarks to set
	 */
	public void setEntranceMarks(Double entranceMarks) {
		this.entranceMarks = entranceMarks;
	}

	/**
	 * @return the classId
	 */
	public long getClassId() {
		return classId;
	}

	/**
	 * @param classId the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}
	/**
	 * @return the lastSchool
	 */
	@Column(name="lastSchool",nullable=true,length=256)
	public String getLastSchool() {
		return lastSchool;
	}

	/**
	 * @param lastSchool the lastSchool to set
	 */
	public void setLastSchool(String lastSchool) {
		this.lastSchool = lastSchool;
	}

	/**
	 * @return the lastClassAttended
	 */
	@Column(name="lastClassAttended",nullable=true,length=128)
	public String getLastClassAttended() {
		return lastClassAttended;
	}

	/**
	 * @param lastClassAttended the lastClassAttended to set
	 */
	public void setLastClassAttended(String lastClassAttended) {
		this.lastClassAttended = lastClassAttended;
	}
	public String getAcademicYearStr() {
		return academicYearStr;
	}
	public void setAcademicYearStr(String academicYearStr) {
		this.academicYearStr = academicYearStr;
	}
	@Transient
    public String getApplicationStrId() {
            return String.valueOf(applicationId);
    }
	@Transient
	public String getEntranceMarksStr() {
		String total = "";
			if (!ObjectFunctions.isNullOrEmpty(getEntranceMarks())) {
				total = String.valueOf(getEntranceMarks());
			}
		return total;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Transient
    public String getDateOfBirthStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getDateOfBirth()); 
    }
	@Transient
	public String getDateOfJoiningStr()
	{
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getDateOfJoining()); 
	}
	
}	



  

