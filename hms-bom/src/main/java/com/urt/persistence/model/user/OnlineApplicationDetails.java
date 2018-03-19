package com.urt.persistence.model.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.OnlineApplicationDetailsVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.AdmissionInquiry;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.Student;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "onlineApplicationDetails")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class OnlineApplicationDetails  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    @ExcelField(position = 3)
    private String lastName;
    @ExcelField(position = 2)
    private String firstName;
    @ExcelField(position = 4)
    private String gender;
    @ExcelField(position = 5)
    protected String className;
    @ExcelField(position = 7)
    private Date dateOfBirth;
    @ExcelField(position = 44)
    protected String bloodGroup; 
    @ExcelField(position = 8)
    private String lastClassAttended;
    @ExcelField(position = 40 )
    private String lastSchool;
    @ExcelField(position = 9)
    private String fatherName;
    @ExcelField(position = 10)
    private String occupation;
    @ExcelField(position = 11)
    private String fatherQualification;
    @ExcelField(position = 12)
    private String designation;
    @ExcelField(position = 13)
    private String motherName;
    @ExcelField(position = 14)
    private String motherQualification;
    @ExcelField(position = 15)
    protected String parentEmail;                    // required
    @ExcelField(position = 16)
    protected String phoneNumber;
    @ExcelField(position = 17)
	private String mobileNumber;
    @ExcelField(position = 32)
    private String religion;
    protected CastSettings castId;
    protected SubCastSettings subCastId;
    private double annualIncome;
    @ExcelField(position = 61)
    private String transferCertificateNo;
    @ExcelField(position = 28)
    private String transportMode;
    private long categoryId;
    
    private String middleName;
    protected long custId;
    private String applicationNumber;
    protected String status=Constants.PENDING_STATUS;
    private Double entranceMarks;
    private ClassName classId; 
    protected AcademicYear academicYear;
    @ExcelField(position = 35)
    private String nationality;
    private CommonType religionId;
    protected boolean present; 
    protected String rejectApplicationDesc;
    @MappedExcelObject
    protected Address tempararyAddress;
    @MappedExcelObject
    private Address primaryAddress;
    @ExcelField(position = 29)
    private String hostelMode;
    private String collectedDocuments;
    protected UserImage profileImage;
    private boolean appliedThroughOnline;
    private Long vehicleAcademicDetailsId;
    private Long boardingPointId;
    private Long applicationId; // Will get this value from desktop application. 
    @ExcelField(position = 26)
	private String categoryName;
    @ExcelField(position = 31)
    private String placeOfBirth;
    @ExcelField(position = 41)
    private String studentEmail;
    @ExcelField(position = 42)
    private String studentMobile;
    @ExcelField(position = 25)
    private String aadharCardNumber; 
    private long receiptNumber;
    private double committedFee;
    @ExcelField(position = 38)
    private String sslcNumber;
    @ExcelField(position = 39)
	private String tmrNumber;
    @ExcelField(position = 37)
    private String communityNumber; 
    @ExcelField(position = 30)
    private String motherToung;
    private boolean phId;
    @ExcelField(position = 24)
    private String studentUniqueNumber;
    @ExcelField(position = 46)
    private String visionL;
    @ExcelField(position = 47)
    private String visionR;
    private double height;
	private double weight;
	@ExcelField(position = 56)
    private String goals;
    @ExcelField(position = 57)
    private String strengths;
    @ExcelField(position = 58)
    private String interestsAndHobbies;
    @ExcelField(position = 59)
    private String responsibilities;
    @ExcelField(position = 60)
    private String achievements;
    //private String remarks;
    @ExcelField(position = 54)
    private String identification1;
    @ExcelField(position = 55)
    private String identification2;
	private long motherToungId;
    @ExcelField(position = 45)
	private String teeth;
	@ExcelField(position = 50)
	private String oralHygiene;
	@ExcelField(position = 36)
	private String rationCardNumber;
	@ExcelField(position = 6)
	private Date dateOfJoining;	
	@ExcelField(position = 52)
	private String prefferedHospital;
	@ExcelField(position = 51)
	private String familyDoctor;
	@ExcelField(position = 22)
	protected String registerNumber; 
	private String rollNumber;
	@ExcelField(position = 27)
	private String scholarShipInfo;
	@ExcelField(position = 33)
	protected String subCastName;
	@ExcelField(position = 34)
	protected String castName;
	@ExcelField(position = 53)
    private String phIdStr;
	@ExcelField(position = 23)
	private String rollNumberStr;
	@ExcelField(position = 43)
	private String annualIncomeStr;
	@ExcelField(position = 49)
    private String weightStr;
	@ExcelField(position = 48)
    private String heightStr;
	@ExcelField(position = 62)
	private String committedFeeStr;
	@ExcelField(position = 1)
	private String applicationIdStr; 
	private String addressType;
	@ExcelField(position = 64)
	private String bplStatus;//Below Poverty Line
	@ExcelField(position = 65)
	private String rteStatus;
	@ExcelField(position = 63)
	protected String physicallyHandicappedDesc;
	protected String isPHDocsUploaded;
	public String secondaryMobileNumber;
	private double height2;
    private double weight2;
    
    private String bplNumber;//Below Poverty Line Number
	
    private String fatherAadharNumber;
	private String motherAadharNumber;
	
	private int age;
	private String ageAppropriation;
	private String urbanOrRural;
	private String studentCasteCertificate;
	private String fatherCasteCertificate;
	private String motherCasteCertificate;
	private String fatherCaste;
	private String motherCaste;
	private String socialCategory;
	private String special;
	private String bhagyalakshmiBondNo;
	private String stream;
	private String mediumOfInstruction;
	private String previousSchoolAffiliation;
	
	private Date transferCertificateDate;
	private String previousSchoolType;
	private Address previousSchoolAddress;
	private String studentOrParentBankAccountName;
	private String studentOrParentBankAccountNumber;
	private String bankIFSCCode;
	private String motherOccupation;
	 
	private String monthlyIncome;
	private String anyOtherLanguageSpoken;
	private String specialInterest;
	private String anyHealthDiseases;
	private String specificPointNoted;
	private String motherMobileNumber;
	private String motherEmailAddress; 
	
	
	private int noOfElder;
	private int noOfYounger;
	
	private int noOfElderSisters;
	private int noOfYoungerSisters;
	
	private AdmissionInquiry admissionInquiry;
	
	
	
	
	
	
	
	public int getNoOfElderSisters() {
		return noOfElderSisters;
	}

	public void setNoOfElderSisters(int noOfElderSisters) {
		this.noOfElderSisters = noOfElderSisters;
	}

	public int getNoOfYoungerSisters() {
		return noOfYoungerSisters;
	}

	public void setNoOfYoungerSisters(int noOfYoungerSisters) {
		this.noOfYoungerSisters = noOfYoungerSisters;
	}

	@OneToOne
    @JoinColumn(name="admissionInquiryId")
	public AdmissionInquiry getAdmissionInquiry() {
		return admissionInquiry;
	}

	public void setAdmissionInquiry(AdmissionInquiry admissionInquiry) {
		this.admissionInquiry = admissionInquiry;
	}

	@Column(name = "noOfElder",nullable = false, columnDefinition=" int default 0")
	public int getNoOfElder() {
		return noOfElder;
	}

	public void setNoOfElder(int noOfElder) {
		this.noOfElder = noOfElder;
	}

	@Column(name = "noOfYounger",nullable = false, columnDefinition=" int default 0")
	public int getNoOfYounger() {
		return noOfYounger;
	}

	public void setNoOfYounger(int noOfYounger) {
		this.noOfYounger = noOfYounger;
	}

	public String getMotherEmailAddress() {
		return motherEmailAddress;
	}

	public void setMotherEmailAddress(String motherEmailAddress) {
		this.motherEmailAddress = motherEmailAddress;
	}

	public String getMotherMobileNumber() {
		return motherMobileNumber;
	}

	public void setMotherMobileNumber(String motherMobileNumber) {
		this.motherMobileNumber = motherMobileNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAgeAppropriation() {
		return ageAppropriation;
	}

	public void setAgeAppropriation(String ageAppropriation) {
		this.ageAppropriation = ageAppropriation;
	}

	public String getUrbanOrRural() {
		return urbanOrRural;
	}

	public void setUrbanOrRural(String urbanOrRural) {
		this.urbanOrRural = urbanOrRural;
	}

	public String getStudentCasteCertificate() {
		return studentCasteCertificate;
	}

	public void setStudentCasteCertificate(String studentCasteCertificate) {
		this.studentCasteCertificate = studentCasteCertificate;
	}

	public String getFatherCasteCertificate() {
		return fatherCasteCertificate;
	}

	public void setFatherCasteCertificate(String fatherCasteCertificate) {
		this.fatherCasteCertificate = fatherCasteCertificate;
	}

	public String getMotherCasteCertificate() {
		return motherCasteCertificate;
	}

	public void setMotherCasteCertificate(String motherCasteCertificate) {
		this.motherCasteCertificate = motherCasteCertificate;
	}

	public String getFatherCaste() {
		return fatherCaste;
	}

	public void setFatherCaste(String fatherCaste) {
		this.fatherCaste = fatherCaste;
	}

	public String getMotherCaste() {
		return motherCaste;
	}

	public void setMotherCaste(String motherCaste) {
		this.motherCaste = motherCaste;
	}

	public String getSocialCategory() {
		return socialCategory;
	}

	public void setSocialCategory(String socialCategory) {
		this.socialCategory = socialCategory;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getBhagyalakshmiBondNo() {
		return bhagyalakshmiBondNo;
	}

	public void setBhagyalakshmiBondNo(String bhagyalakshmiBondNo) {
		this.bhagyalakshmiBondNo = bhagyalakshmiBondNo;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getMediumOfInstruction() {
		return mediumOfInstruction;
	}

	public void setMediumOfInstruction(String mediumOfInstruction) {
		this.mediumOfInstruction = mediumOfInstruction;
	}

	public String getPreviousSchoolAffiliation() {
		return previousSchoolAffiliation;
	}

	public void setPreviousSchoolAffiliation(String previousSchoolAffiliation) {
		this.previousSchoolAffiliation = previousSchoolAffiliation;
	}

	public Date getTransferCertificateDate() {
		return transferCertificateDate;
	}

	public void setTransferCertificateDate(Date transferCertificateDate) {
		this.transferCertificateDate = transferCertificateDate;
	}

	public String getPreviousSchoolType() {
		return previousSchoolType;
	}

	public void setPreviousSchoolType(String previousSchoolType) {
		this.previousSchoolType = previousSchoolType;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="previousSchooladdressId", insertable=true, updatable=true) 
	public Address getPreviousSchoolAddress() {
		return previousSchoolAddress;
	}

	public void setPreviousSchoolAddress(Address previousSchoolAddress) {
		this.previousSchoolAddress = previousSchoolAddress;
	}

	public String getStudentOrParentBankAccountName() {
		return studentOrParentBankAccountName;
	}

	public void setStudentOrParentBankAccountName(
			String studentOrParentBankAccountName) {
		this.studentOrParentBankAccountName = studentOrParentBankAccountName;
	}

	public String getStudentOrParentBankAccountNumber() {
		return studentOrParentBankAccountNumber;
	}

	public void setStudentOrParentBankAccountNumber(
			String studentOrParentBankAccountNumber) {
		this.studentOrParentBankAccountNumber = studentOrParentBankAccountNumber;
	}

	public String getBankIFSCCode() {
		return bankIFSCCode;
	}

	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	

	

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getAnyOtherLanguageSpoken() {
		return anyOtherLanguageSpoken;
	}

	public void setAnyOtherLanguageSpoken(String anyOtherLanguageSpoken) {
		this.anyOtherLanguageSpoken = anyOtherLanguageSpoken;
	}

	public String getSpecialInterest() {
		return specialInterest;
	}

	public void setSpecialInterest(String specialInterest) {
		this.specialInterest = specialInterest;
	}


	public String getAnyHealthDiseases() {
		return anyHealthDiseases;
	}

	public void setAnyHealthDiseases(String anyHealthDiseases) {
		this.anyHealthDiseases = anyHealthDiseases;
	}

	public String getSpecificPointNoted() {
		return specificPointNoted;
	}

	public void setSpecificPointNoted(String specificPointNoted) {
		this.specificPointNoted = specificPointNoted;
	}

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

	public String getBplNumber() {
		return bplNumber;
	}

	public void setBplNumber(String bplNumber) {
		this.bplNumber = bplNumber;
	}
    
    
	public double getHeight2() {
		return height2;
	}
	public void setHeight2(double height2) {
		this.height2 = height2;
	}
	public double getWeight2() {
		return weight2;
	}
	public void setWeight2(double weight2) {
		this.weight2 = weight2;
	}
	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}
	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}
	public String getIsPHDocsUploaded() {
		return isPHDocsUploaded;
	}
	public void setIsPHDocsUploaded(String isPHDocsUploaded) {
		this.isPHDocsUploaded = isPHDocsUploaded;
	}
	public String getPhysicallyHandicappedDesc() {
		return physicallyHandicappedDesc;
	}
	public void setPhysicallyHandicappedDesc(String physicallyHandicappedDesc) {
		this.physicallyHandicappedDesc = physicallyHandicappedDesc;
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
	@Column(name = "addressType", nullable = true, length = 1,columnDefinition="char(1) default 'R'")
	 public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	@Transient
	public String getApplicationIdStr() {
		return applicationIdStr;
	}

	public void setApplicationIdStr(String applicationIdStr) {
		this.applicationIdStr = applicationIdStr;
	}

	@Transient
    public String getHeightStr() {
		return heightStr;
	}

	public void setHeightStr(String heightStr) {
		this.heightStr = heightStr;
	}
	@Transient
    public String getWeightStr() {
   	 //setWeight(Double.valueOf(weightStr));
		return weightStr;
	}

	public void setWeightStr(String weightStr) {
		this.weightStr = weightStr;
	}
	@Transient
	public String getAnnualIncomeStr() 
    {
		return annualIncomeStr;
	}

	public void setAnnualIncomeStr(String annualIncomeStr) {
		this.annualIncomeStr = annualIncomeStr;
	}
	 
	 /**
	 * @return the rollNumberStr
	 */
	@Transient
	public String getRollNumberStr() {
		return rollNumberStr;
	}
	/**
	 * @param rollNumberStr the rollNumberStr to set
	 */
	public void setRollNumberStr(String rollNumberStr) {
		this.rollNumberStr = rollNumberStr;
	}
	@Transient
	public String getPhIdStr() {
		return phIdStr;
	}
	public void setPhIdStr(String phIdStr) {
		this.phIdStr = phIdStr;
	}
	@Transient 
	public String getSubCastName() {
		return subCastName;
	}

	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}
	@Transient 
	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}
	
	public String getScholarShipInfo() {
		return scholarShipInfo;
	}

	public void setScholarShipInfo(String scholarShipInfo) {
		this.scholarShipInfo = scholarShipInfo;
	}
	
	@Column(name = "registerNumber", nullable = true, length = 20)
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	/**
	 * @return the rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}
	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	/**
	 * @return the primaryAddress
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="paddressId", insertable=true, updatable=true) 
	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	/**
	 * @param primaryAddress the primaryAddress to set
	 */
	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	/**
	 * @return the tempararyAddress
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="taddressId", insertable=true, updatable=true) 
	public Address getTempararyAddress() {
		return tempararyAddress;
	}

	/**
	 * @param tempararyAddress the tempararyAddress to set
	 */
	public void setTempararyAddress(Address tempararyAddress) {
		this.tempararyAddress = tempararyAddress;
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
	public String getCommunityNumber() {
		return communityNumber;
	}

	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	  @Column(name = "dateOfJoining", nullable = true, length = 12)
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
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
	@Transient 
	public String getMotherToung() {
		return motherToung;
	}

	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
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
	
	@Column(name = "height", nullable = true, length = 10)
    public double getHeight() {
        return this.height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }

	@Column(name = "weight", nullable = true, length = 10)
    public double getWeight() {
        return this.weight;
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
    
    @Transient
	public String getCommittedFeeStr() {
		return committedFeeStr;
	}

	public void setCommittedFeeStr(String committedFeeStr) {
		this.committedFeeStr = committedFeeStr;
	}
    
    @Column(name = "committedFee", nullable = false, columnDefinition="double default 0",length=12)
    public double getCommittedFee() {
		return committedFee;
	}

	public void setCommittedFee(double committedFee) {
		this.committedFee = committedFee;
	}
    /**
	 * @return the receiptNumber
	 */
    @Column(name = "receiptNumber", nullable = false, columnDefinition="bigint(11) default 0",length=12)
	public long getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(long receiptNumber) {
		this.receiptNumber = receiptNumber;
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
	 * @return the categoryName
	 */
    @Transient
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public OnlineApplicationDetails( String firstName,String lastName,
			String gender, Date dateOfBirth, String bloodGroup,
			String lastClassAttended, String lastSchool, String fatherName,
			String occupation, String fatherQualification, String designation,
			String motherName, String motherQualification, String parentEmail,
			String phoneNumber, String mobileNumber,CastSettings castId, SubCastSettings subCastId, double annualIncome,
			String transferCertificateNo, String transportMode,
			long categoryId, long custId,
			String applicationNumber, String status, ClassName classId,
			String nationality, CommonType religionId,
			Address primaryAddress,Address tempararyAddress, String collectedDocuments,Long applicationId,Date createdDate,AcademicYear academicYear,String bplStatus,String rteStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.bloodGroup = bloodGroup;
		this.lastClassAttended = lastClassAttended;
		this.lastSchool = lastSchool;
		this.fatherName = fatherName;
		this.occupation = occupation;
		this.fatherQualification = fatherQualification;
		this.designation = designation;
		this.motherName = motherName;
		this.motherQualification = motherQualification;
		this.parentEmail = parentEmail;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.castId = castId;
		this.subCastId = subCastId;
		this.annualIncome = annualIncome;
		this.transferCertificateNo = transferCertificateNo;
		this.transportMode = transportMode;
		this.categoryId = categoryId;
		this.custId = custId;
		this.applicationNumber = applicationNumber;
		this.status = status;
		this.classId = classId;
		this.nationality = nationality;
		this.religionId = religionId;
		this.primaryAddress = primaryAddress;
		this.tempararyAddress = tempararyAddress;
		this.collectedDocuments = collectedDocuments;
		this.applicationId = applicationId;
		this.createdDate = createdDate;
		this.lastAccessDate = new Date();
		this.lastUpdatedDate = new Date();
		this.academicYear = academicYear;
		this.bplStatus = bplStatus;
		this.rteStatus = rteStatus;
	}

	/**
	 * @return the applicationId
	 */
	//@Transient  we are saving applicationId for desktop modify by CVS 07-08-2015
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
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
	 * @return the appliedThroughOnline
	 */
    @Column(name = "appliedThroughOnline", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isAppliedThroughOnline() {
		return appliedThroughOnline;
	}

	/**
	 * @param appliedThroughOnline the appliedThroughOnline to set
	 */
	public void setAppliedThroughOnline(boolean appliedThroughOnline) {
		this.appliedThroughOnline = appliedThroughOnline;
	}

	/**
	 * @return the profileImage
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
	 */
    @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="profileImage", insertable=true, updatable=true) 
	public UserImage getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(UserImage profileImage) {
		this.profileImage = profileImage;
	}

    /**
	 * @return the collectedDocuments
	 */
	 @Column(name = "collectedDocuments", nullable = true)
	public String getCollectedDocuments() {
		return collectedDocuments;
	}
	/**
	 * @param collectedDocuments the collectedDocuments to set
	 */
	public void setCollectedDocuments(String collectedDocuments) {
		this.collectedDocuments = collectedDocuments;
	}
    
    @Column(name = "hostelMode", nullable = true, length = 5,columnDefinition="char(1) default 'D'")
    public String getHostelMode() {
		return hostelMode;
	}
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}
	@Column(name = "transportMode", nullable = true, length = 5,columnDefinition="char(1) default 'O'")
    public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	} 
    
   
   /**
	 * @return the religionId
	 */
	 @OneToOne
	 @JoinColumn(name="religionId")
	public CommonType getReligionId() {
		return religionId;
	}

	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(CommonType religionId) {
		this.religionId = religionId;
	}
	
	@Column(name = "annualIncome", nullable = false ,columnDefinition="double default 0")
    public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}
	@Column(name = "transferCertificateNo", nullable = true)
	public String getTransferCertificateNo() {
		return transferCertificateNo;
	}
	public void setTransferCertificateNo(String transferCertificateNo) {
		this.transferCertificateNo = transferCertificateNo;
	}
	
	
	@Column(name = "fatherQualification", nullable = true)
	public String getFatherQualification() {
		return fatherQualification;
	}
	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}
	@Column(name = "motherQualification", nullable = true)
	public String getMotherQualification() {
		return motherQualification;
	}
	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}
	@Column(name = "designation", nullable = true)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
    /**
	 * @return the religion
	 */
    @Transient
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
	@OneToOne
    @JoinColumn(name="subCastId")
	public SubCastSettings getSubCastId() {
		return subCastId;
	}

	/**
	 * @param subCastId the subCastId to set
	 */
	public void setSubCastId(SubCastSettings subCastId) {
		this.subCastId = subCastId;
	}
    
	/**
	 * @return the castId
	 */
    @OneToOne
    @JoinColumn(name="castId")
	public CastSettings getCastId() {
		return castId;
	}

	/**
	 * @param castId the castId to set
	 */
	public void setCastId(CastSettings castId) {
		this.castId = castId;
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
     * @return the academicYear
     */
    @OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    
    public OnlineApplicationDetails() {
    	    
    }

	public OnlineApplicationDetails(long id) {
        setId(id);
    }
	public OnlineApplicationDetails(String status) {
        this.status = status;
    }
	/**
	 * @return the parentEmail
	 */
    @Column(name = "parentEmail", nullable = true, length = 256)
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
	 @Column(name = "lastName", nullable = true, length = 256)
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
	 @Column(name = "firstName", nullable = true, length = 256)
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
	 * @return the middleName
	 */
	 @Column(name = "middleName", nullable = true, length = 256)
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the fatherName
	 */
	 @Column(name = "fatherName", nullable = true, length = 256)
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
	 @Column(name = "motherName", nullable = true, length = 256)
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
	@Column(name = "gender", nullable = true, length = 1)
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
	  @Column(name = "dateOfBirth", nullable = true, length = 12)
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
	 @Column(name = "phoneNumber", nullable = true, length = 50)
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
	 @Column(name = "bloodGroup", nullable = true, length =10)
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
	 @Column(name = "mobileNumber", nullable = true, length = 20)
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Transient
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the custId
	 */
	 @Column(name = "custId", nullable = true, length = 10)
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
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        OnlineApplicationDetails myClass = (OnlineApplicationDetails) object;
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
	}

	/**
	 * @return the applicationNumber
	 */
	@Column(name = "applicationNumber", nullable = true, length = 50)
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
	 @Column(name = "occupation", nullable = true, length = 40)
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
	 * @return the status
	 */
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
	 * @return the entranceMarks
	 */
	@Column(name = "entranceMarks", nullable = true) 
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
	 @OneToOne
	 @JoinColumn(name="classId")
	public ClassName getClassId() {
		return classId;
	}

	public void setClassId(ClassName classId) {
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
	
	@Column(name = "aadharCardNumber", nullable = true)
	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	@Transient
    public String getAcademicYearStr() {
            return academicYear.getAcademicYear();
    }
	
	public void copyFrom(OnlineApplicationDetails obj)
    {
		this.setFirstName(obj.getFirstName());
		this.setLastName(obj.getLastName());
		this.setGender(obj.getGender());
		this.setClassName(obj.getClassName());
		this.setDateOfBirth(obj.getDateOfBirth());
		this.setDateOfJoining(obj.getDateOfJoining());
		this.setLastSchool(obj.getLastSchool());
		this.setFatherName(obj.getFatherName());
		this.setOccupation(obj.getOccupation());
		this.setFatherQualification(obj.getFatherQualification());
		this.setDesignation(obj.getDesignation());
		this.setMotherName(obj.getMotherName());
		this.setMotherQualification(obj.getMotherQualification());
		this.setParentEmail(obj.getParentEmail());
		this.setPhoneNumber(obj.getPhoneNumber());
		this.setReceiptNumber(obj.getReceiptNumber());
		if(StringFunctions.isNotNullOrEmpty(obj.getMobileNumber())){
			this.setMobileNumber(StringFunctions.getMobileNumberLengthChecking(obj.getMobileNumber()));
			
		}else{
			this.setMobileNumber(null);
		}
		if(StringFunctions.isNotNullOrEmpty(obj.getStudentMobile())){
			this.setStudentMobile(StringFunctions.getMobileNumberLengthChecking(obj.getStudentMobile()));
			
		}else{
			this.setStudentMobile(null);
		}
		this.setRegisterNumber(obj.getRegisterNumber());
		this.setStudentUniqueNumber(obj.getStudentUniqueNumber());
		this.setAadharCardNumber(obj.getAadharCardNumber());
		this.setScholarShipInfo(obj.getScholarShipInfo());
		if(!StringFunctions.isNullOrEmpty(obj.getTransportMode()))
			this.setTransportMode(obj.getTransportMode().trim());
		else
			this.setTransportMode("O");
		
		if(!StringFunctions.isNullOrEmpty(obj.getHostelMode()))
			this.setHostelMode(obj.getHostelMode().trim());
			else
			this.setHostelMode("D");
		
		this.setPlaceOfBirth(obj.getPlaceOfBirth());
		this.setNationality(obj.getNationality());
		this.setRationCardNumber(obj.getRationCardNumber());
		this.setCommunityNumber(obj.getCommunityNumber());
		this.setSslcNumber(obj.getSslcNumber());
		this.setTmrNumber(obj.getTmrNumber());
		this.setLastSchool(obj.getLastSchool());
		this.setStudentEmail(obj.getStudentEmail());
		if(!StringFunctions.isNullOrEmpty(obj.getAnnualIncomeStr())){
			this.setAnnualIncome(Double.valueOf(obj.getAnnualIncomeStr()));
		}
		this.setBloodGroup(obj.getBloodGroup());
		this.setTeeth(obj.getTeeth());
		this.setVisionL(obj.getVisionL());
		this.setVisionR(obj.getVisionR());
		if(!StringFunctions.isNullOrEmpty(obj.getHeightStr())){
			this.setHeight(Double.valueOf(obj.getHeightStr()));
		}
		if(!StringFunctions.isNullOrEmpty(obj.getWeightStr())){
			this.setWeight(Double.valueOf(obj.getWeightStr()));
		}
		this.setLastClassAttended(obj.getLastClassAttended());
		
		this.setOralHygiene(obj.getOralHygiene());
		this.setFamilyDoctor(obj.getFamilyDoctor());
		this.setPrefferedHospital(obj.getPrefferedHospital());
		if("Yes".equalsIgnoreCase(obj.getPhIdStr())){
			this.setPhId(true);
			this.setPhysicallyHandicappedDesc(obj.getPhysicallyHandicappedDesc());
			this.setIsPHDocsUploaded(obj.getIsPHDocsUploaded());

    	}else {
    		this.setPhId(false);
    		this.setPhysicallyHandicappedDesc(null);
			this.setIsPHDocsUploaded("N");
		}
		this.setIdentification1(obj.getIdentification1());
		this.setIdentification2(obj.getIdentification2());
		
		if(!StringFunctions.isNullOrEmpty(obj.getGoals()))
			if(obj.getGoals().length() > 200)
				this.setGoals(obj.getGoals().substring(0,200));
			else
				this.setGoals(obj.getGoals());
		if(!StringFunctions.isNullOrEmpty(obj.getStrengths()))
			if(obj.getStrengths().length() > 200)
				this.setStrengths(obj.getStrengths().substring(0,200));
			else
				this.setStrengths(obj.getStrengths());
		if(!StringFunctions.isNullOrEmpty(obj.getInterestsAndHobbies()))
			if(obj.getInterestsAndHobbies().length() > 200)
				this.setInterestsAndHobbies(obj.getInterestsAndHobbies().substring(0,200));
			else
				this.setInterestsAndHobbies(obj.getInterestsAndHobbies());
		if(!StringFunctions.isNullOrEmpty(obj.getResponsibilities()))
			if(obj.getResponsibilities().length() > 200)
				this.setResponsibilities(obj.getResponsibilities().substring(0,200));
			else
				this.setResponsibilities(obj.getResponsibilities());
		if(!StringFunctions.isNullOrEmpty(obj.getAchievements()))
			if(obj.getAchievements().length() > 200)
				this.setAchievements(obj.getAchievements().substring(0,200));
			else
				this.setAchievements(obj.getAchievements());
		this.setTransferCertificateNo(obj.getTransferCertificateNo());
		this.setLastUpdatedDate(new Date());
		if("Yes".equalsIgnoreCase(obj.getBplStatus())){
			this.setBplStatus("Y");
    	}else {
    		this.setBplStatus("N");
		}

		if("Yes".equalsIgnoreCase(obj.getRteStatus())){
			this.setRteStatus("Y");
    	}else {
    		this.setRteStatus("N");
		}
    }
	@Transient
	public String getStudentClassName(){
		if(ObjectFunctions.isNullOrEmpty(this.classId))
			return "";
		else
			return this.classId.getClassName();
	}
	@Transient
	public long getStudentClassId(){
		if(ObjectFunctions.isNullOrEmpty(this.classId))
			return 0;
		else
			return this.classId.getId();
	}
	public void copyStudentInfo(Student student){
		this.classId = student.getClassNameClassId();
		this.firstName = student.getAccount().getPerson().getFirstName();
		this.lastName = student.getAccount().getPerson().getLastName();
		this.gender = student.getAccount().getPerson().getGender();
		this.dateOfBirth = student.getAccount().getPerson().getDateOfBirth();
		this.bloodGroup = student.getAccount().getPerson().getBloodGroup();
		this.lastClassAttended = student.getAccount().getPerson().getClassJoined();
		this.fatherName = student.getAccount().getPerson().getFatherName();
		this.occupation = student.getAccount().getPerson().getOccupation();
		this.fatherQualification = student.getAccount().getPerson().getFatherQualification();
		this.designation = student.getAccount().getPerson().getDesignation();
		this.motherName = student.getAccount().getPerson().getMotherName();
		this.motherQualification = student.getAccount().getPerson().getMotherQualification();
		this.parentEmail = student.getAccount().getPerson().getParentEmail();
		this.phoneNumber = student.getAccount().getPerson().getPhoneNumber();
		this.mobileNumber = student.getAccount().getPerson().getMobileNumber();
		this.annualIncome = student.getAccount().getPerson().getAnnualIncome();
		this.transferCertificateNo = student.getAccount().getPerson().getTransferCertificateNo();
		this.transportMode  = student.getTransportMode();
		this.sslcNumber = student.getAccount().getPerson().getSslcNumber();
		this.tmrNumber = student.getAccount().getPerson().getTmrNumber();
		this.communityNumber = student.getAccount().getPerson().getCommunityNumber();
		this.motherToung = student.getAccount().getPerson().getMotherToung();
		this.phId = student.getAccount().getPerson().isPhId();
		this.studentUniqueNumber = student.getAccount().getPerson().getStudentUniqueNumber();
		this.visionL = student.getAccount().getPerson().getVisionL();
		this.visionR = student.getAccount().getPerson().getVisionR();
		this.height = student.getAccount().getPerson().getHeight();
		this.weight = student.getAccount().getPerson().getWeight();
		this.goals = student.getGoals();
		this.strengths = student.getStrengths();
		this.interestsAndHobbies = student.getInterestsAndHobbies();
		this.responsibilities = student.getResponsibilities();
		this.achievements = student.getAchievements();
		this.identification1 = student.getAccount().getPerson().getIdentification1();
		this.identification2 = student.getAccount().getPerson().getIdentification2();
		this.motherToungId = student.getAccount().getPerson().getMotherToungId();
		this.teeth = student.getAccount().getPerson().getTeeth();
		this.oralHygiene = student.getAccount().getPerson().getOralHygiene();
		this.rationCardNumber = student.getAccount().getPerson().getRationCardNumber();
		this.dateOfJoining = student.getAccount().getPerson().getDateOfJoining();
		this.custId = student.getCustId();
		this.bplStatus = student.getBplStatus();
		this.rteStatus = student.getRteStatus();
	}
	/*@Transient
    public String getAddressForStudent() {
        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        if(!StringFunctions.isNullOrEmpty(this.streetName))
        {
            buffer.append(getStreetName());
        }
        if(!StringFunctions.isNullOrEmpty(this.city))
        {
            buffer.append(", ").append(getCity());
        }
        if(!StringFunctions.isNullOrEmpty(this.postalCode))
        {
            buffer.append(" - ");
			buffer.append(getPostalCode());
        }
        if(!StringFunctions.isNullOrEmpty(this.state))
        {
            buffer.append(", ").append(getState());
        }
        return buffer.toString();
    }*/
	
	
	public OnlineApplicationDetailsVO copyFromEntityToVo(OnlineApplicationDetails obj)
   {
		OnlineApplicationDetailsVO onlineApplicationDetailsVO = new OnlineApplicationDetailsVO();
		
		onlineApplicationDetailsVO.setFirstName(obj.getFirstName());
		onlineApplicationDetailsVO.setLastName(obj.getLastName());
		onlineApplicationDetailsVO.setGender(obj.getGender());
		onlineApplicationDetailsVO.setClassName(obj.getClassId().getClassName());
		onlineApplicationDetailsVO.setDateOfBirthDate(obj.getDateOfBirth());
		onlineApplicationDetailsVO.setDateOfJoiningDate(obj.getDateOfJoining());
		
		onlineApplicationDetailsVO.setLastSchool(obj.getLastSchool());
		onlineApplicationDetailsVO.setFatherName(obj.getFatherName());
		onlineApplicationDetailsVO.setOccupation(obj.getOccupation());
		onlineApplicationDetailsVO.setFatherQualification(obj.getFatherQualification());
		onlineApplicationDetailsVO.setDesignation(obj.getDesignation());
		onlineApplicationDetailsVO.setMotherName(obj.getMotherName());
		onlineApplicationDetailsVO.setMotherQualification(obj.getMotherQualification());
		onlineApplicationDetailsVO.setParentEmail(obj.getParentEmail());
		onlineApplicationDetailsVO.setPhoneNumber(obj.getPhoneNumber());
		onlineApplicationDetailsVO.setReceiptNumber(obj.getReceiptNumber());
		if(StringFunctions.isNotNullOrEmpty(obj.getMobileNumber())){
			onlineApplicationDetailsVO.setMobileNumber(StringFunctions.getMobileNumberLengthChecking(obj.getMobileNumber()));
			
		}else{
			onlineApplicationDetailsVO.setMobileNumber("");
		}
		if(StringFunctions.isNotNullOrEmpty(obj.getStudentMobile())){
			onlineApplicationDetailsVO.setStudentMobile(StringFunctions.getMobileNumberLengthChecking(obj.getStudentMobile()));
			
		}else{
			onlineApplicationDetailsVO.setStudentMobile("");
		}
		onlineApplicationDetailsVO.setRegisterNumber(obj.getRegisterNumber());
		onlineApplicationDetailsVO.setStudentUniqueNumber(obj.getStudentUniqueNumber());
		onlineApplicationDetailsVO.setAadharCardNumber(obj.getAadharCardNumber());
		onlineApplicationDetailsVO.setScholarShipInfo(obj.getScholarShipInfo());
		if(!StringFunctions.isNullOrEmpty(obj.getTransportMode()))
			onlineApplicationDetailsVO.setTransportMode(obj.getTransportMode().trim());
		else
			onlineApplicationDetailsVO.setTransportMode("O");
		
		if(!StringFunctions.isNullOrEmpty(obj.getHostelMode()))
			onlineApplicationDetailsVO.setHostelMode(obj.getHostelMode().trim());
			else
			onlineApplicationDetailsVO.setHostelMode("D");
		
		onlineApplicationDetailsVO.setPlaceOfBirth(obj.getPlaceOfBirth());
		onlineApplicationDetailsVO.setNationality(obj.getNationality());
		onlineApplicationDetailsVO.setRationCardNumber(obj.getRationCardNumber());
		onlineApplicationDetailsVO.setCommunityNumber(obj.getCommunityNumber());
		onlineApplicationDetailsVO.setSslcNumber(obj.getSslcNumber());
		onlineApplicationDetailsVO.setTmrNumber(obj.getTmrNumber());
		onlineApplicationDetailsVO.setLastSchool(obj.getLastSchool());
		onlineApplicationDetailsVO.setStudentEmail(obj.getStudentEmail());
		if(!StringFunctions.isNullOrEmpty(obj.getAnnualIncomeStr())){
			onlineApplicationDetailsVO.setAnnualIncome(Double.valueOf(obj.getAnnualIncomeStr()));
		}
		onlineApplicationDetailsVO.setBloodGroup(obj.getBloodGroup());
		onlineApplicationDetailsVO.setTeeth(obj.getTeeth());
		onlineApplicationDetailsVO.setVisionL(obj.getVisionL());
		onlineApplicationDetailsVO.setVisionR(obj.getVisionR());
		if(!StringFunctions.isNullOrEmpty(obj.getHeightStr())){
			onlineApplicationDetailsVO.setHeightStr(obj.getHeightStr());
		}
		if(!StringFunctions.isNullOrEmpty(obj.getWeightStr())){
			onlineApplicationDetailsVO.setWeightStr(obj.getWeightStr());
		}
		onlineApplicationDetailsVO.setLastClassAttended(obj.getLastClassAttended());
		
		onlineApplicationDetailsVO.setOralHygiene(obj.getOralHygiene());
		onlineApplicationDetailsVO.setFamilyDoctor(obj.getFamilyDoctor());
		onlineApplicationDetailsVO.setPrefferedHospital(obj.getPrefferedHospital());
		if("Yes".equalsIgnoreCase(obj.getPhIdStr())){
			onlineApplicationDetailsVO.setPhId(true);
			onlineApplicationDetailsVO.setPhysicallyHandicappedDesc(obj.getPhysicallyHandicappedDesc());
			onlineApplicationDetailsVO.setIsPHDocsUploaded(obj.getIsPHDocsUploaded());

    	}else {
    		onlineApplicationDetailsVO.setPhId(false);
    		onlineApplicationDetailsVO.setPhysicallyHandicappedDesc(null);
			onlineApplicationDetailsVO.setIsPHDocsUploaded("N");
		}
		onlineApplicationDetailsVO.setIdentification1(obj.getIdentification1());
		onlineApplicationDetailsVO.setIdentification2(obj.getIdentification2());
		
		if(!StringFunctions.isNullOrEmpty(obj.getGoals()))
			if(obj.getGoals().length() > 200)
				onlineApplicationDetailsVO.setGoals(obj.getGoals().substring(0,200));
			else
				onlineApplicationDetailsVO.setGoals(obj.getGoals());
		if(!StringFunctions.isNullOrEmpty(obj.getStrengths()))
			if(obj.getStrengths().length() > 200)
				onlineApplicationDetailsVO.setStrengths(obj.getStrengths().substring(0,200));
			else
				onlineApplicationDetailsVO.setStrengths(obj.getStrengths());
		
		if(!StringFunctions.isNullOrEmpty(obj.getInterestsAndHobbies()))
		{
			if(obj.getInterestsAndHobbies().length() > 200)
				onlineApplicationDetailsVO.setInterestsAndHobbies(obj.getInterestsAndHobbies().substring(0,200));
			else
				onlineApplicationDetailsVO.setInterestsAndHobbies(obj.getInterestsAndHobbies());
		}
		else
			onlineApplicationDetailsVO.setInterestsAndHobbies("");
		
		if(!StringFunctions.isNullOrEmpty(obj.getResponsibilities()))
			if(obj.getResponsibilities().length() > 200)
				onlineApplicationDetailsVO.setResponsibilities(obj.getResponsibilities().substring(0,200));
			else
				onlineApplicationDetailsVO.setResponsibilities(obj.getResponsibilities());
		if(!StringFunctions.isNullOrEmpty(obj.getAchievements()))
			if(obj.getAchievements().length() > 200)
				onlineApplicationDetailsVO.setAchievements(obj.getAchievements().substring(0,200));
			else
				onlineApplicationDetailsVO.setAchievements(obj.getAchievements());
		onlineApplicationDetailsVO.setTransferCertificateNo(obj.getTransferCertificateNo());
		//onlineApplicationDetailsVO.setLastUpdatedDate(new Date());
		if("Y".equalsIgnoreCase(obj.getBplStatus())){
			onlineApplicationDetailsVO.setBplStatus("Yes");
			onlineApplicationDetailsVO.setBplNumber(obj.getBplNumber());
    	}else {
    		onlineApplicationDetailsVO.setBplStatus("No");
    		onlineApplicationDetailsVO.setBplNumber("");
		}

		if("Y".equalsIgnoreCase(obj.getRteStatus())){
			onlineApplicationDetailsVO.setRteStatus("Yes");
    	}else {
    		onlineApplicationDetailsVO.setRteStatus("NO");
		}
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getAcademicYear())){
			onlineApplicationDetailsVO.setAcademicYear(obj.getAcademicYear().getAcademicYear());
		}
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getFatherAadharNumber())){
			onlineApplicationDetailsVO.setFatherAadharNumber(obj.getFatherAadharNumber());
		}
		else
			onlineApplicationDetailsVO.setFatherAadharNumber("");
		
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getMotherAadharNumber())){
			onlineApplicationDetailsVO.setMotherAadharNumber(obj.getMotherAadharNumber());
		}
		else
			onlineApplicationDetailsVO.setMotherAadharNumber("");
		
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getReligionId())){
			onlineApplicationDetailsVO.setReligion(obj.getReligionId().getSkillTypeName());
		}
		else
			onlineApplicationDetailsVO.setReligion("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getCastId())){
			onlineApplicationDetailsVO.setCastName(obj.getCastId().getCastName());
		}
		else
			onlineApplicationDetailsVO.setCastName("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getSubCastId())){
			onlineApplicationDetailsVO.setSubCastName(obj.getSubCastId().getSubCastName());
		}
		else
			onlineApplicationDetailsVO.setSubCastName("");
		
		onlineApplicationDetailsVO.setCreatedDate(obj.getCreatedDate());
		
		onlineApplicationDetailsVO.setMotherToung(obj.getMotherToung());
		onlineApplicationDetailsVO.setFatherOccupation(obj.getOccupation());
		if(!StringFunctions.isNullOrEmpty(obj.getMotherOccupation()))
			onlineApplicationDetailsVO.setMotherOccupation(obj.getMotherOccupation());
		else
			onlineApplicationDetailsVO.setMotherOccupation("");
		
		onlineApplicationDetailsVO.setNoOfBrother(obj.getNoOfElder() + getNoOfYounger());
		
		onlineApplicationDetailsVO.setNoOfElder(obj.getNoOfElder());
		onlineApplicationDetailsVO.setNoOfSisters(obj.getNoOfElderSisters() + obj.getNoOfYoungerSisters());
		onlineApplicationDetailsVO.setNoOfYounger(obj.getNoOfYounger());
		if(!StringFunctions.isNullOrEmpty(obj.getMonthlyIncome()))
			onlineApplicationDetailsVO.setMonthlyIncome(obj.getMonthlyIncome());
		else
			onlineApplicationDetailsVO.setMonthlyIncome("");
		
		if(!StringFunctions.isNullOrEmpty(obj.getSpecial()))
			onlineApplicationDetailsVO.setSpecial(obj.getSpecial());
		else
			onlineApplicationDetailsVO.setSpecial("");
		
		if(!StringFunctions.isNullOrEmpty(obj.getSpecialInterest()))
			onlineApplicationDetailsVO.setSpecialInterest(obj.getSpecialInterest());
		else
			onlineApplicationDetailsVO.setSpecialInterest("");
		
		if(!StringFunctions.isNullOrEmpty(obj.getSpecificPointNoted()))
			onlineApplicationDetailsVO.setSpecificPointNoted(obj.getSpecificPointNoted());
		else
			onlineApplicationDetailsVO.setSpecificPointNoted("");
		
		if(!StringFunctions.isNullOrEmpty(obj.getAnyHealthDiseases()))
			onlineApplicationDetailsVO.setAnyHealthDiseases(obj.getAnyHealthDiseases());
		else
			onlineApplicationDetailsVO.setAnyHealthDiseases("");
		
		if(!StringFunctions.isNullOrEmpty(obj.getAnyOtherLanguageSpoken()))
			onlineApplicationDetailsVO.setAnyOtherLanguageSpoken(obj.getAnyOtherLanguageSpoken());
		else
			onlineApplicationDetailsVO.setAnyOtherLanguageSpoken("");
			
		if(!ObjectFunctions.isNullOrEmpty(obj.getPrimaryAddress())){
			onlineApplicationDetailsVO.setResidentialAddress(obj.getPrimaryAddress().getFormatAddress());
		}
		else
			onlineApplicationDetailsVO.setResidentialAddress("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getUrbanOrRural())){
			onlineApplicationDetailsVO.setUrbanOrRural(obj.getUrbanOrRural());
		}
		else
			onlineApplicationDetailsVO.setUrbanOrRural("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getStudentCasteCertificate())){
			onlineApplicationDetailsVO.setStudentCasteCertificate(obj.getStudentCasteCertificate());
		}
		else
			onlineApplicationDetailsVO.setStudentCasteCertificate("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getFatherCasteCertificate())){
			onlineApplicationDetailsVO.setFatherCasteCertificate(obj.getFatherCasteCertificate());
		}
		else
			onlineApplicationDetailsVO.setFatherCasteCertificate("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getMotherCasteCertificate())){
			onlineApplicationDetailsVO.setMotherCasteCertificate(obj.getMotherCasteCertificate());
		}
		else
			onlineApplicationDetailsVO.setMotherCasteCertificate("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getFatherCaste())){
			onlineApplicationDetailsVO.setFatherCaste(obj.getFatherCaste());
		}
		else
			onlineApplicationDetailsVO.setFatherCaste("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getMotherCaste())){
			onlineApplicationDetailsVO.setMotherCaste(obj.getMotherCaste());
		}
		else
			onlineApplicationDetailsVO.setMotherCaste("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getSocialCategory())){
			onlineApplicationDetailsVO.setSocialCategory(obj.getSocialCategory());
		}
		else
			onlineApplicationDetailsVO.setSocialCategory("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolAffiliation())){
			onlineApplicationDetailsVO.setPreviousSchoolAffiliation(obj.getPreviousSchoolAffiliation());
		}
		else
			onlineApplicationDetailsVO.setPreviousSchoolAffiliation("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolType())){
			onlineApplicationDetailsVO.setPreviousSchoolType(obj.getPreviousSchoolType());
		}
		else
			onlineApplicationDetailsVO.setPreviousSchoolType("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolAddress()))
		{
			if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolAddress().getPostalCode())){
				onlineApplicationDetailsVO.setPreviousSchoolPincode(obj.getPreviousSchoolAddress().getPostalCode());
			}
			else
				onlineApplicationDetailsVO.setPreviousSchoolPincode("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolAddress().getDistrictName())){
				onlineApplicationDetailsVO.setPreviousSchoolDistictName(obj.getPreviousSchoolAddress().getDistrictName());
			}
			else
				onlineApplicationDetailsVO.setPreviousSchoolDistictName("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolAddress().getTaluka())){
				onlineApplicationDetailsVO.setPreviousSchoolTaluka(obj.getPreviousSchoolAddress().getTaluka());
			}
			else
				onlineApplicationDetailsVO.setPreviousSchoolTaluka("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolAddress().getCity())){
				onlineApplicationDetailsVO.setPreviousSchoolCity(obj.getPreviousSchoolAddress().getCity());
			}
			else
				onlineApplicationDetailsVO.setPreviousSchoolCity("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPreviousSchoolAddress().getStreetName())){
				onlineApplicationDetailsVO.setPreviousSchoolStreetName(obj.getPreviousSchoolAddress().getStreetName());
			}
			else
				onlineApplicationDetailsVO.setPreviousSchoolStreetName("");
		}
		else
		{
			onlineApplicationDetailsVO.setPreviousSchoolPincode("");
			onlineApplicationDetailsVO.setPreviousSchoolDistictName("");
			onlineApplicationDetailsVO.setPreviousSchoolTaluka("");
			onlineApplicationDetailsVO.setPreviousSchoolCity("");
			onlineApplicationDetailsVO.setPreviousSchoolStreetName("");
			
		}
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getBhagyalakshmiBondNo())){
			onlineApplicationDetailsVO.setBhagyalakshmiBondNo(obj.getBhagyalakshmiBondNo());
		}
		else
			onlineApplicationDetailsVO.setBhagyalakshmiBondNo("");
		
		
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getPrimaryAddress()))
		{
			if(!ObjectFunctions.isNullOrEmpty(obj.getPrimaryAddress().getPostalCode())){
				onlineApplicationDetailsVO.setStudentResidencePincode(obj.getPrimaryAddress().getPostalCode());
			}
			else
				onlineApplicationDetailsVO.setStudentResidencePincode("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPrimaryAddress().getDistrictName())){
				onlineApplicationDetailsVO.setStudentResidenceDistictName(obj.getPrimaryAddress().getDistrictName());
			}
			else
				onlineApplicationDetailsVO.setStudentResidenceDistictName("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPrimaryAddress().getTaluka())){
				onlineApplicationDetailsVO.setStudentResidenceTaluka(obj.getPrimaryAddress().getTaluka());
			}
			else
				onlineApplicationDetailsVO.setStudentResidenceTaluka("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPrimaryAddress().getCity())){
				onlineApplicationDetailsVO.setStudentResidenceCity(obj.getPrimaryAddress().getCity());
			}
			else
				onlineApplicationDetailsVO.setStudentResidenceCity("");
			
			if(!ObjectFunctions.isNullOrEmpty(obj.getPrimaryAddress().getStreetName())){
				onlineApplicationDetailsVO.setStudentResidenceStreetName(obj.getPrimaryAddress().getStreetName());
			}
			else
				onlineApplicationDetailsVO.setStudentResidenceStreetName("");
		}
		else
		{
			onlineApplicationDetailsVO.setStudentResidencePincode("");
			onlineApplicationDetailsVO.setStudentResidenceDistictName("");
			onlineApplicationDetailsVO.setStudentResidenceTaluka("");
			onlineApplicationDetailsVO.setStudentResidenceCity("");
			onlineApplicationDetailsVO.setStudentResidenceStreetName("");
			
		}
		
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getMotherMobileNumber())){
			onlineApplicationDetailsVO.setMotherMobileNumber(obj.getMotherMobileNumber());
		}
		else
			onlineApplicationDetailsVO.setMotherMobileNumber("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getMotherEmailAddress())){
			onlineApplicationDetailsVO.setMotherEmailAddress(obj.getMotherEmailAddress());
		}
		else
			onlineApplicationDetailsVO.setMotherEmailAddress("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getStudentOrParentBankAccountName())){
			onlineApplicationDetailsVO.setStudentOrParentBankAccountName(obj.getStudentOrParentBankAccountName());
		}
		else
			onlineApplicationDetailsVO.setStudentOrParentBankAccountName("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getStudentOrParentBankAccountNumber())){
			onlineApplicationDetailsVO.setStudentOrParentBankAccountNumber(obj.getStudentOrParentBankAccountNumber());
		}
		else
			onlineApplicationDetailsVO.setStudentOrParentBankAccountNumber("");
		
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getBankIFSCCode())){
			onlineApplicationDetailsVO.setBankIFSCCode(obj.getBankIFSCCode());
		}
		else
			onlineApplicationDetailsVO.setBankIFSCCode("");
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getAgeAppropriation())){
			onlineApplicationDetailsVO.setAgeAppropriation(obj.getAgeAppropriation());
		}
		else
			onlineApplicationDetailsVO.setAgeAppropriation("");
		
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getAadharCardNumber())){
			onlineApplicationDetailsVO.setAadharCardNumber(obj.getAadharCardNumber());
		}
		else
			onlineApplicationDetailsVO.setAadharCardNumber("");
		
		onlineApplicationDetailsVO.setNoOfElderSisters(obj.getNoOfElderSisters());
		onlineApplicationDetailsVO.setNoOfYoungerSisters(obj.getNoOfYoungerSisters());
		
		
		
		if(!ObjectFunctions.isNullOrEmpty(obj.getMediumOfInstruction())){
			onlineApplicationDetailsVO.setMediumOfInstruction(obj.getMediumOfInstruction());
		}
		else
			onlineApplicationDetailsVO.setMediumOfInstruction("");
		
		onlineApplicationDetailsVO.setAge(obj.getAge());
		
		
		if(obj.isPhId())
			onlineApplicationDetailsVO.setPhIdStr("Yes");
		else
			onlineApplicationDetailsVO.setPhIdStr("No");
		
		
		onlineApplicationDetailsVO.setTransportModeStr(obj.getTransportModeStr());
		
		onlineApplicationDetailsVO.setTransferCertificateDate(obj.getTransferCertificateDate());
		
		
		return onlineApplicationDetailsVO;
    }
	
	@Transient
	public String getOriginalAttachmentFilePath() {
		if(!ObjectFunctions.isNullOrEmpty(getProfileImage()))
		if (!StringFunctions.isNullOrEmpty(getProfileImage().getPath())) {
			//return getProfileImage().getPath().concat(getProfileImage().getThumbNail());
			return getProfileImage().getPath();
		}
		return UserImage.getStudyImageNotFoundFile();
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
	
	@Transient
    public String getTransferCertificateDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getTransferCertificateDate()); 
    }
	
	@Transient
	public String getTransportModeStr()
	{
		if(!StringFunctions.isNullOrEmpty(getTransportMode()))
		{
			if("O".equalsIgnoreCase(getTransportMode()))
				return "Own";
			else if("P".equalsIgnoreCase(getTransportMode()))
				return "Private";
			else
				return "School Transport";
				
		}
		else
			return "";
	}
	
	
}	


  

