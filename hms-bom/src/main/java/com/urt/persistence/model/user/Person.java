package com.urt.persistence.model.user;
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
/********************************************************************/

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.PersonVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.util.excel.PersonExcelRow;
import com.urt.persistence.util.excel.StudentExcelRow;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "Person")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class Person extends PersistentObject {

     private static final long serialVersionUID = 1L;
    
     @ExcelField(position = 4)
     private String firstName;
     @ExcelField(position = 5)
     private String lastName;
     private String middleName;
     private String personalTitle;
     @ExcelField(position = 11)
     private String fatherName;
     @ExcelField(position = 13)
     private String motherName;
     private String suffix;
     private String nickname; 
     @ExcelField(position = 6)
     private String gender="M";
     @ExcelField(position = 9)
     private Date dateOfBirth;
     private double height;
     private double weight;
     private String mothersMaidenName;
     private String maritalStatus;
     private String socialSecurityNumber;
     private String passportNumber;
     private Date passportExpireDate;
     private int totalYearsWorkExperence;
     @ExcelField(position = 10)
     private String classJoined;
     private String citizenship;
     private String  age;
     private String licenseNumber;
     private String licenseExpDate;
     private double experience;
     @ExcelField(position = 16)
     protected String phoneNumber;
     @ExcelField(position = 34)
     protected String bloodGroup;
     @ExcelField(position = 15)
 	 private String parentEmail;
     @ExcelField(position = 17)
	 private String mobileNumber;
	 @ExcelField(position = 8)
	 private Date dateOfJoining;	
	 private String roleName;
	 private String summary;
	 private long castId;
	 private long subCastId;
	 private String otherCastName;
	 @ExcelField(position = 36)
	 private String familyDoctor;
	 @ExcelField(position = 37)
	 private String prefferedHospital;
	 @ExcelField(position = 14)
	 private String motherOccupation;
	 @ExcelField(position = 25)
	 private String nationality;
	 @ExcelField(position = 12)
	 private String occupation;   
	 private long religionId;
	 private String designation;
	 private String panNumber;
	 private String gpfNumber;
	 private String officeNumber;
	 //@ExcelField(position = 32)
	 private double annualIncome;
	 @ExcelField(position = 30)
	 private String identification1;
	 @ExcelField(position = 31)
	 private String identification2;
	 private long motherToungId;

	 private String bankName;
	 private String bankAccountNumber;
	 private String bankBranchName;
	 @ExcelField(position = 39)
	 private String visionL;
	 @ExcelField(position = 41)
	 private String teeth;
	 @ExcelField(position = 42)
	 private String oralHygiene;
	 @ExcelField(position = 26)
	 private String rationCardNumber;
	 @ExcelField(position = 27)
	 private String communityNumber; 
	 private Date contractStartDate;	
	 private Date contractEndDate;	
	 @ExcelField(position = 28)
	 private String sslcNumber;
	 @ExcelField(position = 29)
	 private String tmrNumber;
	 
	 @ExcelField(position = 43)
	 private Date relievingDate;
	 @ExcelField(position = 45)
	 private String scholarShipInfo; 
	 private String fatherQualification;
     private String motherQualification;
     private String transferCertificateNo;
     private String isDocsUploaded;
	 
     @ExcelField(position = 32)
	 private String annualIncomeStr;
     
     @ExcelField(position = 40)
     private String weightStr;
     
     @ExcelField(position = 38)
     private String heightStr;
     
     @ExcelField(position = 22)
     private String religion;
     
     @ExcelField(position = 23)
     private String community;//Cast Name
     
     @ExcelField(position = 24)
     private String castName;//Subcast Name
     
     @ExcelField(position = 33)
     private String motherToung;
     @ExcelField(position = 47)
     private Date tcAppliedDate;
     @ExcelField(position = 48)
     private Date tcIssuedDate;
     //@ExcelField(position = 46)
     private boolean phId;
     
     @ExcelField(position = 46)
     private String phIdStr;
     @ExcelField(position = 57)
     private String aadharNumber;
     private String ifscCode;
     @ExcelField(position = 50)
     private String placeOfBirth;
     @ExcelField(position = 51)
     private String lastSchool;
     @ExcelField(position = 52)
     private String studentEmail;     
     @ExcelField(position = 53)
     private String studentMobile;
     
     private String staffUniqueNumber;
     @ExcelField(position = 56)
 	 private String studentUniqueNumber;
     @ExcelField(position = 60)
     private String visionR;
	
     private String physicallyHandicappedDesc;
     private String isPHDocsUploaded;
     private double height2;
     private double weight2;
     @ExcelField(position = 67)
     private String height2Str;
     @ExcelField(position = 68)
     private String weight2Str;
     @ExcelField(position = 70)
     private String secondaryMobileNumber;
     @ExcelField(position = 71)
     private String addressType;
     
     
    @ExcelField(position = 72)
    private String anotherMobileNumber;
 	@ExcelField(position = 73)
 	private String anotherSecondaryMobileNumber;
 	@ExcelField(position = 74)
 	private String anotherParentEmail;
 	@ExcelField(position = 75)
 	private String stsNumber;
    
 	private String salaryPaymentMode;
 	private String staffLocation;
 	private double scholarShipAmount;
    private String fatherAadharNumber;
 	private String motherAadharNumber;
 	 
 	private PersonOtherDetails personOtherDetails;
 	private ParentsEmploymentDetails parentsEmploymentDetails;
 	private PersonHealthDetails personHealthDetails;
 	private String sameAsResidentialAddress;
 	private String studentBankId; 
  	
 	
	public double getScholarShipAmount() {
		return scholarShipAmount;
	}
	public void setScholarShipAmount(double scholarShipAmount) {
		this.scholarShipAmount = scholarShipAmount;
	}
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

     /*private List<File> fileUpload = new ArrayList<File>();
     private List<String> fileUploadFileName = new ArrayList<String>();
 	

     @Transient
     public List<String> getFileUploadFileName() {
 		return fileUploadFileName;
 	}
  
 	public void setFileUploadFileName(List<String> fileUploadFileName) {
 		this.fileUploadFileName = fileUploadFileName;
 	}
 	@Transient
     public List<File> getFileUpload() {
 		return fileUpload;
 	}
  
 	public void setFileUpload(List<File> fileUpload) {
 		this.fileUpload = fileUpload;
 	}*/
 	
 	
 	@Column(name = "addressType", nullable = true, length = 1,columnDefinition="char(1) default 'R'")
	 public String getAddressType() {
		return addressType;
	}
 	public void setAddressType(String addressType) {
		this.addressType = addressType;
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


	@Column(name = "secondaryMobileNumber", nullable = true, length = 50)
     public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}

	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}

     /**
	 * @return the height2
	 */
     @Column(name = "height2", nullable = true, length = 10)
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
	 @Column(name = "weight2", nullable = true, length = 10)
	public double getWeight2() {
		return weight2;
	}

	/**
	 * @param weight2 the weight2 to set
	 */
	public void setWeight2(double weight2) {
		this.weight2 = weight2;
	}

	@Column(name = "isPHDocsUploaded", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
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

 	
 	 @Column(name = "staffUniqueNumber", nullable = true, length = 20)
 	 public String getStaffUniqueNumber() {
 		return staffUniqueNumber;
 	 }

 	 public void setStaffUniqueNumber(String staffUniqueNumber) {
 		this.staffUniqueNumber = staffUniqueNumber;
 	 }
 	 @Column(name = "studentUniqueNumber", nullable = true, length = 20)
 	 public String getStudentUniqueNumber() {
 		return studentUniqueNumber;
 	 }

 	 public void setStudentUniqueNumber(String studentUniqueNumber) {
 		this.studentUniqueNumber = studentUniqueNumber;
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
	/**
	 * @return the aadharNumber
	 */
    @Column(name = "aadharNumber", nullable = true, length = 14)
	public String getAadharNumber() {
		return this.aadharNumber;
	}
	/**
	 * @param aadharNumber the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	@Column(name = "phId", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
     @Type(type="yes_no")
     public boolean isPhId() {
 		return phId;
 	}
 	public void setPhId(boolean phId) {
 		this.phId = phId;
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
	@Column(name = "isDocsUploaded", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getIsDocsUploaded() {
		return isDocsUploaded;
	}
	public void setIsDocsUploaded(String isDocsUploaded) {
		this.isDocsUploaded = isDocsUploaded;
	}
	@Transient  
     public String getMotherToung() {
		return motherToung;
     }
     public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
     }
     
	@Transient  
     public String getCastName() {
		return castName;
     }
     public void setCastName(String castName) {
		this.castName = castName;
     }
     
     @Transient  
     public String getCommunity() {
		return community;
     }	
     public void setCommunity(String community) {
		this.community = community;
     }
	
	@Transient 
     public String getReligion() {
		return religion;
     }
     public void setReligion(String religion) {
		this.religion = religion;
     }

	@Transient
     public String getHeightStr() {
    	 //setHeight(Double.valueOf(heightStr));
		return heightStr;
	}

	public void setHeightStr(String heightStr) {
		this.heightStr = heightStr;
	}
	@Transient
    public String getHeight2Str() {
   	 //setHeight(Double.valueOf(heightStr));
		return height2Str;
	}

	public void setHeight2Str(String height2Str) {
		this.height2Str = height2Str;
	}
	@Transient
    public String getWeight2Str() {
   	 //setWeight(Double.valueOf(weightStr));
		return weight2Str;
	}

	public void setWeight2Str(String weight2Str) {
		this.weight2Str = weight2Str;
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
	public String getPhIdStr() {
		return phIdStr;
	}
	public void setPhIdStr(String phIdStr) {
		this.phIdStr = phIdStr;
	}
	@Transient
	public String getAnnualIncomeStr() 
    {
    	//setAnnualIncome(Long.valueOf(annualIncomeStr)); 
		return annualIncomeStr;
	}

	public void setAnnualIncomeStr(String annualIncomeStr) {
		this.annualIncomeStr = annualIncomeStr;
	}

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

	public String getTransferCertificateNo() {
		return transferCertificateNo;
	}

	public void setTransferCertificateNo(String transferCertificateNo) {
		this.transferCertificateNo = transferCertificateNo;
	}

	public String getScholarShipInfo() {
		return scholarShipInfo;
	}

	public void setScholarShipInfo(String scholarShipInfo) {
		this.scholarShipInfo = scholarShipInfo;
	}

	public Date getRelievingDate() {
		return relievingDate;
	}

	public void setRelievingDate(Date relievingDate) {
		this.relievingDate = relievingDate;
	}

    @Column(name = "sslcNumber", nullable = true, length = 15)
	public String getSslcNumber() {
		return sslcNumber;
	}

	public void setSslcNumber(String sslcNumber) {
		this.sslcNumber = sslcNumber;
	}
	@Column(name = "tmrNumber", nullable = true, length = 20)
	public String getTmrNumber() {
		return tmrNumber;
	}

	public void setTmrNumber(String tmrNumber) {
		this.tmrNumber = tmrNumber;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
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

	/**
	 * @return the motherToungId
	 */
    @Column(name = "motherToungId", nullable = false ,columnDefinition="bigint(20) default 0")
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
	@Column(name = "religionId", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getReligionId() {
		return religionId;
	}

	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}

	@Column(name = "nationality", nullable = true, length = 128)
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@Column(name = "FatherOccupation", nullable = true, length = 128)
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
    @Column(name = "motherOccupation", nullable = true, length = 200)
    public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
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

	@Column(name = "castId", nullable = false ,columnDefinition="bigint(20) default 0")
    public long getCastId() {
		return this.castId;
	}
	public void setCastId(long castId) {
		this.castId = castId;
	}
	
	@Column(name = "otherCastName", nullable = true, length = 200)
	public String getOtherCastName() {
		return otherCastName;
	}
	public void setOtherCastName(String otherCastName) {
		this.otherCastName = otherCastName;
	}    
	
    @Column(name = "summary", nullable = true, length = 1024)
    public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "roleName", nullable = true, length = 128)
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "fatherName", nullable = true, length = 128)
    public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	 @Column(name = "motherName", nullable = true, length = 128)
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "parentEmail", nullable = true, length = 128)
	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	@Column(name = "mobileNumber", nullable = true, length = 50)
    	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

    @Column(name = "bloodGroup", nullable = true, length = 20)
    public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Column(name = "phoneNumber", nullable = true, length = 50)
    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "experience",  nullable= true,length=4)
    public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	@Column(name = "licenseExpDate", nullable = true, length = 128)
    public String getLicenseExpDate() {
		return licenseExpDate;
	}

	public void setLicenseExpDate(String licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}

	@Column(name = "licenseNumber", nullable = true, length = 128)
    public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
    
	@Column(name = "firstName", nullable = true, length = 128)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	
    @Column(name = "lastName", nullable = true, length = 128)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "middleName", nullable = true, length = 64)
    public String getMiddleName() {
        return this.middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    @Column(name = "personalTitle", nullable = true, length = 10)
    public String getPersonalTitle() {
        return this.personalTitle;
    }
    
    public void setPersonalTitle(String personalTitle) {
        this.personalTitle = personalTitle;
    }

    @Column(name = "suffix", nullable = true, length = 20)
    public String getSuffix() {
        return this.suffix;
    }
    
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Column(name = "passwordHint", nullable = true, length = 255)
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "gender", nullable = true, length = 1)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
	@Transient
    public String getDateOfBirthMonthDay()
    {
        return DateFormatter.formatDate(DateFormatter.MMM_D_PATTERN, getDateOfBirth()); 
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
    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }

    public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
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

    @Column(name = "mothersMaidenName", nullable = true, length = 32)
    public String getMothersMaidenName() {
        return this.mothersMaidenName;
    }
    
    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    @Column(name = "maritalStatus", nullable = true, length = 32)
    public String getMaritalStatus() {
        return this.maritalStatus;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Column(name = "socialSecurityNumber", nullable = true, length = 14)
    public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }
    
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Column(name = "passportNumber", nullable = true, length = 16)
    public String getPassportNumber() {
        return this.passportNumber;
    }
    
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Column(name = "passportExpireDate", nullable = true, length = 12)
    public Date getPassportExpireDate() {
        return this.passportExpireDate;
    }
    
    public void setPassportExpireDate(Date passportExpireDate) {
        this.passportExpireDate = passportExpireDate;
    }
    @Transient
    public int getTotalYearsWorkExperence() {
        return this.totalYearsWorkExperence;
    }
    
    public void setTotalYearsWorkExperence(int totalYearsWorkExperence) {
        this.totalYearsWorkExperence = totalYearsWorkExperence;
    }

    @Column(name = "classJoined", nullable = true, length = 255)
    public String getClassJoined() {
        return this.classJoined;
    }
    
    public void setClassJoined(String classJoined) {
        this.classJoined = classJoined;
    }
    
  
    @Column(name = "citizenship", nullable = true, length = 32)
    public String getCitizenship() {
        return this.citizenship;
    }
    
    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }
   
    @Transient
    public String getPersonName()
    {
        return getFullPersonName(false,true,true);
    }
    
    @Transient
    public String getFullFormattedName()
    {
      return  getFullFormattedName(true,true,true);
    }
    /**
     * Returns the full formatted student of the person
     * @return string with full formatted student
     * @param title Set to true if title is desired
     * @param middle Set to true if middle initial is desired
     * @param suffix Set to true if suffix is desired
     */
   @Transient
    public String getFullFormattedName(
        boolean title,
        boolean middle,
        boolean suffix)
    {
        StringBuffer ret = new StringBuffer(10);
         /* if (title && !StringFunctions.isNullOrEmpty(strTitle))
            {
                ret.append(strTitle);
                if (!strTitle.endsWith("."))
                {
                    ret.append(".");
                }
                ret.append(" ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(getFirstName());
                
                
            }
           /* if (suffix && !StringFunctions.isNullOrEmpty(strSuffix) 
                    && !Constants.SELECT_ID.equalsIgnoreCase(strSuffix))
            {
                ret.append(" ");
                ret.append(strSuffix);
                ret.append(", ");
            }
            else
            {
                ret.append(", ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(" ");
                ret.append(getLastName());
              //  ret.append(" ");
            }
           /* if (middle && !StringFunctions.isNullOrEmpty(strMiddleInitial ))
            {
                ret.append(strMiddleInitial.charAt(0));
                ret.append(" ");
            }*/
        ret.append(" ");
        return ret.toString().trim();
    }
    
    /**
     * Returns the full formatted student of the person
     * @return string with full formatted student
     * @param title Set to true if title is desired
     * @param middle Set to true if middle initial is desired
     * @param suffix Set to true if suffix is desired
     */
   @Transient
    public String getFullPersonName(
        boolean title,
        boolean middle,
        boolean suffix)
    {
        StringBuffer ret = new StringBuffer(10);
            if (title && !StringFunctions.isNullOrEmpty(getPersonalTitle()))
            {
                ret.append(getPersonalTitle());
                if (!getPersonalTitle().endsWith("."))
                {
                    ret.append(".");
                }
                ret.append(" ");
            }
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            { ret.append(" ");
                ret.append(getFirstName());
                ret.append(" ");
            }
          
            if (suffix && !StringFunctions.isNullOrEmpty(getSuffix()) 
                    && !Constants.SELECT_ID.equalsIgnoreCase(getSuffix()))
            {
                ret.append(" ");
                ret.append(getSuffix());
                ret.append(",");
            }
            else
            {
                ret.append(", ");
            }
           
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(getLastName());
            }
            if (middle && !StringFunctions.isNullOrEmpty(getMiddleName()))
            {
                ret.append(getMiddleName().charAt(0));
                ret.append(" ");
            }
         return ret.toString().trim();
    }
    
    /**
     * Returns the full formatted student of the person
     * @return string with full formatted student
     * @param title Set to true if title is desired
     * @param middle Set to true if middle initial is desired
     * @param suffix Set to true if suffix is desired
     */
   @Transient
    public String getFullPersonName()
    {
         StringBuffer ret = new StringBuffer(10);
        //setName(new Student(this.getFirstName(),this.getMiddleName(),this.getLastName()));
       
           /* if (!StringFunctions.isNullOrEmpty(strTitle))
            {
                ret.append(strTitle);
                if (!strTitle.endsWith("."))
                {
                    ret.append(".");
                }
                ret.append(" ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(getFirstName());
                //ret.append(" ");
            }
           /* if (!StringFunctions.isNullOrEmpty(strMiddleInitial ))
            {
                ret.append(strMiddleInitial.charAt(0));
                ret.append(" ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(" ");
                ret.append(getLastName());
                //ret.append(" ");
            }
           /* if (!StringFunctions.isNullOrEmpty(strSuffix) 
                    && !Constants.SELECT_ID.equalsIgnoreCase(strSuffix))
            {
                ret.append(strSuffix);
            }*/
        return ret.toString().trim();
    }
    
   @Transient
    public String getPersonFirstLastNameOnly()
    {
        StringBuffer ret = new StringBuffer(10);
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(getFirstName());
            }
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(" ");
                ret.append(getLastName());
            }
       
        return ret.toString().trim();
    }

    /**
     * Returns the student for display on UI
     * @return string with full formatted student
     */
   @Transient
    public String getDisplayName()
    {
        return getFullFormattedName(false, true, true);
    }

    
    
    /**
     * @Brief Description: This method returns Month of Birth from dateofBirth field
     * @return day of Month - String 
     */
    public void setMonthOfBirth(String month)
    {
        //getDateOfBirth().setYear(1);
    }
    /**
     * @Brief Description: This method returns Day of Birth from dateofBirth field
     * @return day of Month - String 
     */
    public void setDayOfBirth(int day)
    {
        //getDateOfBirth().set(day);
    }
    /**
     * @return Returns the age.
     */
   @Transient
    public String getAge() {
        return age;
    }

    /**
     * @param age The age to set.
     */
    public void setAge(String age) {
        this.age = age;
    }
   
    /**
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId()).append("createdDate",
                        this.createdDate).append("displayName",
                        this.getDisplayName()).append("age", this.age).append(
                        "maritalStatus", this.maritalStatus).append("weight",
                        this.weight).append("totalYearsWorkExperence",
                        this.totalYearsWorkExperence).append("dateOfBirth",
                        this.dateOfBirth).append(
                        "citizenship", this.citizenship).append("height", this.height).append("suffix",
                        this.suffix).append("socialSecurityNumber",
                        this.socialSecurityNumber).append("passportNumber",
                        this.passportNumber).append("gender", this.gender)
                .append("firstName", this.firstName).append(
                        "passportExpireDate", this.passportExpireDate).append("personalTitle",
                        this.personalTitle).append("middleName",
                        this.middleName).append(
                        "mothersMaidenName", this.mothersMaidenName).append(
                        "lastName", this.lastName).append("Student Joined Class",
                        this.classJoined)
                .append("nickname",
                        this.nickname).toString();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
        return new HashCodeBuilder(-2081271763, -780066295).append(this.classJoined).append(this.createdDate)
                .append(
                        this.maritalStatus).append(this.firstName).append(
                        this.passportNumber).append(this.age).append(
                        this.socialSecurityNumber).append(this.height).append(
                        this.suffix).append(this.citizenship).append(
                        this.middleName).append(this.gender)
                .append(this.nickname).append(this.personalTitle)
                .append(this.lastName).append(this.totalYearsWorkExperence)
                .append(this.dateOfBirth).append(this.passportExpireDate)
                .append(this.mothersMaidenName).append(
                        this.weight)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
	public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }
        Person rhs = (Person) object;
        return new EqualsBuilder()
                .append(this.classJoined, rhs.classJoined)
                .append(this.createdDate, rhs.createdDate)
                .append(this.maritalStatus, rhs.maritalStatus)
                .append(this.firstName, rhs.firstName)
                .append(this.passportNumber, rhs.passportNumber)
                .append(this.age, rhs.age)
                .append(this.socialSecurityNumber, rhs.socialSecurityNumber)
                .append(this.height, rhs.height)
                .append(this.suffix, rhs.suffix)
                .append(this.citizenship, rhs.citizenship)
                .append(this.middleName, rhs.middleName)                
                .append(this.gender, rhs.gender)
                .append(this.nickname, rhs.nickname)
                .append(this.personalTitle, rhs.personalTitle)
                .append(this.lastName, rhs.lastName)
                .append(this.totalYearsWorkExperence,
                        rhs.totalYearsWorkExperence)
                .append(this.dateOfBirth, rhs.dateOfBirth)
                .append(this.passportExpireDate, rhs.passportExpireDate)
                .append(this.mothersMaidenName, rhs.mothersMaidenName)
                .append(this.weight, rhs.weight).isEquals();
    }
    
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    @Override
	public int compareTo(Object object) {
        
        Person target = (Person) object;
        if (target.getLastName() != null && this.getLastName() != null)
        {
            if(this.getLastName().equalsIgnoreCase(target.getLastName()))
                return 0;
            else 
               return this.getLastName().compareToIgnoreCase(target.getLastName());
        }
        return 0;
    }
    @Transient
    public String getPersonFullName()
    {
        StringBuffer ret = new StringBuffer(10);
 	       
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(getFirstName());
            }
            /*if (!StringFunctions.isNullOrEmpty(getFirstName()))
 	       {
         	   ret.append(" ");
 	           ret.append(getMiddleName());
 	       }*/
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(" ");
                ret.append(getLastName());
            }
       
        return ret.toString().trim();
    }
    @Transient
    public String getMaritalStatusDesc()
    {
    	if("UN".equalsIgnoreCase(getMaritalStatus()))
    	{
    		return "Un Married";
    	}
    	else
    	{
    		return "Married";
    	}
    }
    @Column(name = "subCastId", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getSubCastId() {
		return this.subCastId;
	}

	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
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
	 * @return the annualIncome
	 */
	@Column(name = "annualIncome", nullable = false ,columnDefinition="double default 0")
	public double getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome the annualIncome to set
	 */
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * @return the identification1
	 */
	public String getIdentification1() {
		return identification1;
	}

	/**
	 * @param identification1 the identification1 to set
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
	 * @param identification2 the identification2 to set
	 */
	public void setIdentification2(String identification2) {
		this.identification2 = identification2;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	@Column(name = "rationCardNumber", nullable = true, length = 128)
	public String getRationCardNumber() {
		return rationCardNumber;
	}

	public void setRationCardNumber(String rationCardNumber) {
		this.rationCardNumber = rationCardNumber;
	}
	@Column(name = "communityNumber", nullable = true, length = 128)
	public String getCommunityNumber() {
		return communityNumber;
	}

	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}/** default constructor */
    public Person() {
    }
    
    /** full constructor */
    public Person(String firstName, String lastName, String middleName, String personalTitle, String suffix, String nickname, String gender, Date dateOfBirth, double height, double weight, String mothersMaidenName, String maritalStatus, String socialSecurityNumber, String passportNumber, Date passportExpireDate, int totalYearsWorkExperence, String classJoined,  Date createdDate, String citizenship,String designation,String panNumber,String gpfNumber,String officeNumber,double annualIncome,long motherToungId,boolean phId,Date tcAppliedDate,Date tcIssuedDate, double height2, double weight2) {
    	this.firstName = firstName;
    	this.lastName = lastName;
        this.middleName = middleName;
        this.personalTitle = personalTitle;
        this.suffix = suffix;
        this.nickname = nickname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.mothersMaidenName = mothersMaidenName;
        this.maritalStatus = maritalStatus;
        this.socialSecurityNumber = socialSecurityNumber;
        this.passportNumber = passportNumber;
        this.passportExpireDate = passportExpireDate;
        this.totalYearsWorkExperence = totalYearsWorkExperence;
        this.classJoined = classJoined;
        this.createdDate = createdDate;
        this.citizenship = citizenship;
        this.castId=castId;
        this.designation=designation;
        this.panNumber=panNumber;
        this.gpfNumber=gpfNumber;
        this.officeNumber=officeNumber;
        this.annualIncome=annualIncome;
        this.motherToungId=motherToungId;
        this.phId=phId;
        this.tcAppliedDate=tcAppliedDate;
        this.tcIssuedDate=tcIssuedDate;
    }
    
    public void copyFrom(Person aPerson) {
        this.firstName = aPerson.getFirstName();
        this.lastName = aPerson.getLastName();
        this.middleName = aPerson.getMiddleName();
        this.personalTitle = aPerson.getPersonalTitle();
        this.suffix = aPerson.getSuffix();
        this.gender = aPerson.getGender();
        this.dateOfBirth = aPerson.getDateOfBirth();
        this.dateOfJoining = aPerson.getDateOfJoining();
        this.maritalStatus = aPerson.getMaritalStatus();
        this.socialSecurityNumber = aPerson.getSocialSecurityNumber();
        this.passportNumber = aPerson.getPassportNumber();
        this.passportExpireDate = aPerson.getPassportExpireDate();
        this.totalYearsWorkExperence = aPerson.getTotalYearsWorkExperence();
        this.citizenship = aPerson.getCitizenship();
        this.licenseNumber=aPerson.getLicenseNumber();
        this.licenseExpDate=aPerson.getLicenseExpDate();
        this.experience=aPerson.getExperience();
        this.phoneNumber=aPerson.getPhoneNumber();
        this.bloodGroup=aPerson.getBloodGroup();
        this.mobileNumber=aPerson.getMobileNumber();
        this.motherName=aPerson.getMotherName();
        this.fatherName=aPerson.getFatherName();
        this.parentEmail=aPerson.getParentEmail();
        this.castId=aPerson.getCastId();
        this.religionId=aPerson.getReligionId();
        this.designation=aPerson.getDesignation();
        this.panNumber=aPerson.getPanNumber();
        this.gpfNumber=aPerson.getGpfNumber();
        this.officeNumber=aPerson.getOfficeNumber();
        this.annualIncome=aPerson.getAnnualIncome();
        this.motherToungId=aPerson.getMotherToungId();
        this.fatherQualification=aPerson.getFatherQualification();
        this.motherOccupation=aPerson.getMotherOccupation();
        this.transferCertificateNo=aPerson.getTransferCertificateNo();
        this.motherQualification=aPerson.getMotherQualification();
        this.occupation=aPerson.getOccupation();
        this.placeOfBirth=aPerson.getPlaceOfBirth();
        this.lastSchool=aPerson.getLastSchool();
        this.classJoined=aPerson.getClassJoined();
        this.studentEmail=aPerson.getStudentEmail();
        this.studentMobile=aPerson.getStudentMobile();
        this.subCastId=aPerson.getSubCastId();
        if(aPerson.isPhId()){
        	this.phId=aPerson.isPhId();
        	this.isPHDocsUploaded = "Y";
        	this.physicallyHandicappedDesc = aPerson.getPhysicallyHandicappedDesc();
        } else{
        	this.phId=aPerson.isPhId();
        	this.isPHDocsUploaded = "N";
        	this.physicallyHandicappedDesc = null;
        }
        this.tcAppliedDate=tcAppliedDate;
        this.tcIssuedDate=tcIssuedDate;
        this.sslcNumber = aPerson.getSslcNumber();
        this.tmrNumber = aPerson.getTmrNumber();
        this.studentUniqueNumber = aPerson.getStudentUniqueNumber();
        this.aadharNumber = aPerson.getAadharNumber();
        this.teeth = aPerson.getTeeth();
        this.visionL = aPerson.getVisionL();
        this.visionR = aPerson.getVisionR();
        this.height = aPerson.getHeight();
        this.weight = aPerson.getWeight();
        this.oralHygiene = aPerson.getOralHygiene();
        this.familyDoctor = aPerson.getFamilyDoctor();
        this.prefferedHospital = aPerson.getPrefferedHospital();
        this.communityNumber = aPerson.getCommunityNumber();
        this.rationCardNumber = aPerson.getRationCardNumber();
        this.identification1 = aPerson.getIdentification1();
        this.identification2 = aPerson.getIdentification2();
        this.summary = aPerson.getSummary();
        this.nationality = aPerson.getNationality();
        this.fatherAadharNumber = aPerson.getFatherAadharNumber();
        this.motherAadharNumber = aPerson.getMotherAadharNumber();
        this.setStsNumber(aPerson.getStsNumber());
        		
    }
    
    public void copyBeans(Person newPerson, Person oldPerson)
    {
    	oldPerson.setFirstName(newPerson.getFirstName());
    	oldPerson.setLastName(newPerson.getLastName());
    	oldPerson.setFatherName(newPerson.getFatherName());
    	oldPerson.setMotherName(newPerson.getMotherName());
    	oldPerson.setGender(newPerson.getGender());
    	oldPerson.setDateOfBirth(newPerson.getDateOfBirth());
    	oldPerson.setClassJoined(newPerson.getClassJoined());
    	oldPerson.setPhoneNumber(newPerson.getPhoneNumber());
    	if(StringFunctions.isNotNullOrEmpty(newPerson.getBloodGroup())) {
    		oldPerson.setBloodGroup(newPerson.getBloodGroup().toLowerCase());
    	}
    	oldPerson.setParentEmail(newPerson.getParentEmail());
    	oldPerson.setLastSchool(newPerson.getLastSchool());
    	oldPerson.setPlaceOfBirth(newPerson.getPlaceOfBirth());
    	oldPerson.setStudentEmail(newPerson.getStudentEmail());
    	oldPerson.setAnotherParentEmail(newPerson.getAnotherParentEmail());
		if(StringFunctions.isNotNullOrEmpty(newPerson.getMobileNumber())){
			//oldPerson.setMobileNumber(StringFunctions.getMobileNumberLengthChecking(newPerson.getMobileNumber()));
			oldPerson.setMobileNumber(newPerson.getMobileNumber());
			if(StringFunctions.isNotNullOrEmpty(newPerson.getSecondaryMobileNumber())){
				//oldPerson.setSecondaryMobileNumber(StringFunctions.getMobileNumberLengthChecking(newPerson.getSecondaryMobileNumber()));
				oldPerson.setSecondaryMobileNumber(newPerson.getSecondaryMobileNumber());
			}else{
				oldPerson.setSecondaryMobileNumber(null);
			}
		}else{
			oldPerson.setMobileNumber(null);
			oldPerson.setSecondaryMobileNumber(null);
		}
		if(StringFunctions.isNotNullOrEmpty(newPerson.getStudentMobile())){
		//	oldPerson.setStudentMobile(StringFunctions.getMobileNumberLengthChecking(newPerson.getStudentMobile()));
			oldPerson.setStudentMobile(newPerson.getStudentMobile());
		}else{
			oldPerson.setStudentMobile(null);
		}
    	oldPerson.setDateOfJoining(newPerson.getDateOfJoining());
    	
    	oldPerson.setFamilyDoctor(newPerson.getFamilyDoctor());
    	oldPerson.setPrefferedHospital(newPerson.getPrefferedHospital());
    	oldPerson.setMotherOccupation(newPerson.getMotherOccupation());
    	oldPerson.setNationality(newPerson.getNationality());
    	oldPerson.setOccupation(newPerson.getOccupation());
    	
    	oldPerson.setCastId(newPerson.getCastId());
    	oldPerson.setSubCastId(newPerson.getSubCastId());
    	oldPerson.setMotherToungId(newPerson.getMotherToungId());
    	oldPerson.setReligionId(newPerson.getReligionId());
    	
    	oldPerson.setIdentification1(newPerson.getIdentification1());
    	oldPerson.setIdentification2(newPerson.getIdentification2());
    	if(!ObjectFunctions.isNullOrEmpty(newPerson.getVisionL())){
    		if(newPerson.getVisionL().length() > 10){
    			oldPerson.setVisionL(newPerson.getVisionL().substring(0, 10));
    		}else
    			oldPerson.setVisionL(newPerson.getVisionL());
    	}
    	if(!ObjectFunctions.isNullOrEmpty(newPerson.getVisionR())){
    		if(newPerson.getVisionR().length() > 10){
    			oldPerson.setVisionR(newPerson.getVisionR().substring(0, 10));
    		}else
    			oldPerson.setVisionR(newPerson.getVisionR());
    	}    	
    	oldPerson.setTeeth(newPerson.getTeeth());
    	oldPerson.setOralHygiene(newPerson.getOralHygiene());
    	oldPerson.setRationCardNumber(newPerson.getRationCardNumber());
    	oldPerson.setCommunityNumber(newPerson.getCommunityNumber()); 
    	oldPerson.setSslcNumber(newPerson.getSslcNumber());
    	oldPerson.setTmrNumber(newPerson.getTmrNumber());
    	oldPerson.setStudentUniqueNumber(newPerson.getStudentUniqueNumber());
    	if(StringFunctions.isNullOrEmpty(newPerson.getAnnualIncomeStr()))
    		oldPerson.setAnnualIncome(0);
    	else
    		oldPerson.setAnnualIncome(Long.valueOf(newPerson.getAnnualIncomeStr()));
    	if(StringFunctions.isNullOrEmpty(newPerson.getWeightStr())){
    		oldPerson.setWeight(0.0);
    	}
    	else{
    		String weightStr=newPerson.getWeightStr().replaceAll("[^0-9\\.]", "");
    		if(StringFunctions.isNotNullOrEmpty(weightStr)){
    			oldPerson.setWeight(Double.valueOf(weightStr));
    		}else{
    			oldPerson.setWeight(0.0);
    		}
    	//	oldPerson.setWeight(Double.valueOf(newPerson.getWeightStr()));
    	}
    	if(StringFunctions.isNullOrEmpty(newPerson.getHeightStr())){
    		oldPerson.setHeight(0.0);
    	}
    	else{
    		String heightStr=newPerson.getHeightStr().replaceAll("[^0-9\\.]", "");
    		if(StringFunctions.isNotNullOrEmpty(heightStr)){
    			oldPerson.setHeight(Double.valueOf(heightStr));
    		}else{
    			oldPerson.setHeight(0.0);
    		}
    		//oldPerson.setHeight(Double.valueOf(newPerson.getHeightStr()));
    	}
    	if(StringFunctions.isNullOrEmpty(newPerson.getWeight2Str())){
    		oldPerson.setWeight2(0.0);
    	}
    	else{
    		String weight2Str=newPerson.getWeight2Str().replaceAll("[^0-9\\.]", "");
    		if(StringFunctions.isNotNullOrEmpty(weight2Str)){
    			oldPerson.setWeight2(Double.valueOf(weight2Str));
    		}else{
    			oldPerson.setWeight2(0.0);
    		}
    	}
    	if(StringFunctions.isNullOrEmpty(newPerson.getHeight2Str())){
    		oldPerson.setHeight2(0.0);
    	}
    	else{
    		String height2Str=newPerson.getHeight2Str().replaceAll("[^0-9\\.]", "");
    		if(StringFunctions.isNotNullOrEmpty(height2Str)){
    			oldPerson.setHeight2(Double.valueOf(height2Str));
    		}else{
    			oldPerson.setHeight2(0.0);
    		}
    	}
    	oldPerson.setRelievingDate(newPerson.getRelievingDate());
    	oldPerson.setScholarShipInfo(newPerson.getScholarShipInfo());
    	if("Yes".equalsIgnoreCase(newPerson.getPhIdStr())){
    		oldPerson.setPhId(true);
    	}else {
    		oldPerson.setPhId(false);
		}
    	oldPerson.setTcAppliedDate(newPerson.getTcAppliedDate());
    	oldPerson.setTcIssuedDate(newPerson.getTcIssuedDate());
    	oldPerson.setAadharNumber(newPerson.getAadharNumber());
    	if(newPerson.getExperience()<0)
    		oldPerson.setExperience(0);
    	else
    		oldPerson.setExperience(newPerson.getExperience());
    	if(StringFunctions.isNotNullOrEmpty(newPerson.getAddressType())){
    		oldPerson.setAddressType(newPerson.getAddressType());
    	}else
    		oldPerson.setAddressType("R");
    	if(StringFunctions.isNotNullOrEmpty(newPerson.getAnotherMobileNumber())){
			//oldPerson.setAnotherMobileNumber(StringFunctions.getMobileNumberLengthChecking(newPerson.getAnotherMobileNumber()));
			oldPerson.setAnotherMobileNumber(newPerson.getAnotherMobileNumber());
			if(StringFunctions.isNotNullOrEmpty(newPerson.getAnotherSecondaryMobileNumber())){
				//oldPerson.setAnotherSecondaryMobileNumber(StringFunctions.getMobileNumberLengthChecking(newPerson.getAnotherSecondaryMobileNumber()));
				oldPerson.setAnotherSecondaryMobileNumber(newPerson.getAnotherSecondaryMobileNumber());
			}else{
				oldPerson.setAnotherSecondaryMobileNumber(null);
			}
		}else{
			oldPerson.setAnotherMobileNumber(null);
			oldPerson.setAnotherSecondaryMobileNumber(null);
		}
    	
      	oldPerson.setFatherAadharNumber(newPerson.getFatherAadharNumber());
    	oldPerson.setMotherAadharNumber(newPerson.getMotherAadharNumber());
    	oldPerson.setStudentBankId(newPerson.getStudentBankId());
      	
           }
    
	public void copyStaffExcelDate(PersonExcelRow person){
    	this.firstName = person.getFirstName();
    	this.lastName = person.getLastName();
    	this.dateOfBirth = person.getDateOfBirth();
    	this.gender = person.getGender();
    	this.maritalStatus = person.getMaritalStatus();
    	if(StringFunctions.isNotNullOrEmpty(person.getExperience())){
    		String experience=person.getExperience().toString().replaceAll("[^0-9\\.]", "");
    		if(StringFunctions.isNotNullOrEmpty(experience)){
    			this.experience = Double.valueOf(experience);
    		}else{
    			this.experience = 0.0;
    		}
    	}else{
    		this.experience = 0.0;
    	}
    	this.dateOfJoining = person.getDateOfJoining();
    	if(StringFunctions.isNotNullOrEmpty(person.getMobileNumber())){
			//this.mobileNumber= StringFunctions.getMobileNumberLengthChecking(person.getMobileNumber());
			this.mobileNumber= person.getMobileNumber();
		}
    	else
			this.mobileNumber = null;
    	this.mobileNumber = StringFunctions.getFormattedMobileNumber(person.getMobileNumber());
    	this.bloodGroup = person.getBloodGroup();
    	this.panNumber = person.getPanNumber();
    	if(StringFunctions.isNotNullOrEmpty(person.getGpfNumber())){
    		if(person.getGpfNumber().length() <=20){
    			this.gpfNumber = person.getGpfNumber();
    		}
    		else
    			this.gpfNumber = null;
    	}
    	this.officeNumber = person.getOfficeNumber();
    	this.phoneNumber = person.getPhoneNumber();
    	this.designation = person.getDesignation();
    	this.religionId = person.getReligionId();
    	this.motherToungId = person.getMotherToungId();
    	this.nationality = person.getNationality();
    	this.castId = person.getCastId();
    	this.subCastId = person.getSubCastId();
    	this.bankName = person.getBankName();
    	this.bankBranchName = person.getBankBranchName();
    	this.bankAccountNumber = person.getBankAccountNumber();
    	this.familyDoctor = person.getFamilyDoctor();
    	this.prefferedHospital = person.getPrefferedHospital();
    	this.aadharNumber = person.getAadharNumber();
    	this.passportNumber = person.getPassportNumber();
    	this.ifscCode = person.getIfscCode();
    	this.salaryPaymentMode = person.getSalaryPaymentMode();
    	this.staffLocation = person.getStaffLocation();
    	this.setFatherName(person.getFatherName());
    	this.setAnotherMobileNumber(StringFunctions.getFormattedMobileNumber(person.getFatherContactNumber()));
    	
	}
    public void copyApplicationsData(OnlineApplicationDetails onlineApplicationDetails){
    	this.firstName = onlineApplicationDetails.getFirstName();
		this.lastName  = onlineApplicationDetails.getLastName();
		this.fatherName = onlineApplicationDetails.getFatherName();
		this.motherName = onlineApplicationDetails.getMotherName();
		this.bloodGroup = onlineApplicationDetails.getBloodGroup();
		this.dateOfBirth = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,onlineApplicationDetails.getDateOfBirth()));
		this.dateOfJoining = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,onlineApplicationDetails.getDateOfJoining()));
		this.gender = onlineApplicationDetails.getGender();
		this.phoneNumber = onlineApplicationDetails.getPhoneNumber();
		this.mobileNumber = onlineApplicationDetails.getMobileNumber();
		this.parentEmail = onlineApplicationDetails.getParentEmail();
		this.nationality = onlineApplicationDetails.getNationality();
		this.transferCertificateNo = onlineApplicationDetails.getTransferCertificateNo();
		this.fatherQualification = onlineApplicationDetails.getFatherQualification();
		this.motherQualification=onlineApplicationDetails.getMotherQualification();
		this.designation = onlineApplicationDetails.getDesignation();
		this.annualIncome = onlineApplicationDetails.getAnnualIncome();
		this.occupation = onlineApplicationDetails.getOccupation();
		this.lastSchool = onlineApplicationDetails.getLastSchool();
		this.classJoined = onlineApplicationDetails.getLastClassAttended();
		this.placeOfBirth = onlineApplicationDetails.getPlaceOfBirth();
		this.studentMobile = onlineApplicationDetails.getStudentMobile();
		this.studentEmail = onlineApplicationDetails.getStudentEmail();
		this.teeth = onlineApplicationDetails.getTeeth();
		this.visionL = onlineApplicationDetails.getVisionL();
		this.visionR = onlineApplicationDetails.getVisionR();
		this.weight = onlineApplicationDetails.getWeight();
		this.height = onlineApplicationDetails.getHeight();
		this.identification1 = onlineApplicationDetails.getIdentification1();
		this.identification2 = onlineApplicationDetails.getIdentification2();
		this.motherToungId = onlineApplicationDetails.getMotherToungId();
		this.oralHygiene = onlineApplicationDetails.getOralHygiene();
		this.rationCardNumber = onlineApplicationDetails.getRationCardNumber();
		this.sslcNumber = onlineApplicationDetails.getSslcNumber();
		this.tmrNumber = onlineApplicationDetails.getTmrNumber();
		this.communityNumber = onlineApplicationDetails.getCommunityNumber();
		this.summary = onlineApplicationDetails.getCollectedDocuments();
		this.aadharNumber = onlineApplicationDetails.getAadharCardNumber();
		this.studentUniqueNumber = onlineApplicationDetails.getStudentUniqueNumber();
		this.prefferedHospital = onlineApplicationDetails.getPrefferedHospital();
		this.familyDoctor = onlineApplicationDetails.getFamilyDoctor();
		if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getReligionId()))
			this.religionId= 0;
		else
			this.religionId= onlineApplicationDetails.getReligionId().getId();
		if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getCastId()))
			this.castId = 0;
		else
			this.castId = onlineApplicationDetails.getCastId().getId();
		if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getSubCastId()))
			this.subCastId = 0;
		else
			this.subCastId = onlineApplicationDetails.getSubCastId().getId();
		if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getAddressType()))
			this.addressType = "R";
		else
			this.addressType = onlineApplicationDetails.getAddressType();
		if(onlineApplicationDetails.isPhId()){
			this.phId = true;
			this.physicallyHandicappedDesc = onlineApplicationDetails.getPhysicallyHandicappedDesc();
			this.isPHDocsUploaded = onlineApplicationDetails.getIsPHDocsUploaded();
		}else
			this.phId = true;
		
		
    }
    public void copyStudentExcelData(StudentExcelRow student){
    	
    	this.firstName = student.getFirstName();
    	this.lastName = student.getLastName();
    	this.fatherName =student.getFatherName();
    	this.motherName= student.getMotherName();
    	if(StringFunctions.isNotNullOrEmpty(student.getMobileNumber())){
		//	this.mobileNumber= StringFunctions.getMobileNumberLengthChecking(student.getMobileNumber());
			this.mobileNumber=student.getMobileNumber();
		}
    	else
			this.mobileNumber = null;
    	if(StringFunctions.isNotNullOrEmpty(student.getSecondaryMobileNumber())){
			//this.phoneNumber= StringFunctions.getMobileNumberLengthChecking(student.getSecondaryMobileNumber());
    		this.phoneNumber= student.getSecondaryMobileNumber();
		}
    	else
			this.phoneNumber = null;
    	if(StringFunctions.isNotNullOrEmpty(student.getStudentMobile())){
			//this.studentMobile= StringFunctions.getMobileNumberLengthChecking(student.getStudentMobile());
			this.studentMobile= student.getStudentMobile();
		}
    	else
			this.studentMobile = null;
    	//this.mobileNumber = StringFunctions.getFormattedMobileNumber(person.getMobileNumber());
    	this.parentEmail = student.getParentEmail();
    	this.studentEmail = student.getStudentEmail();
	}
    
    
	@Transient
	public Person copyFromVoToEntity(Person person, PersonVO personVo)
	{
		if(person.getId() == 0)
			person.id = personVo.id;
		person.firstName = personVo.firstName;
		person.lastName = personVo.lastName;
		person.middleName = personVo.middleName;
		person.personalTitle = personVo.personalTitle;
		person.fatherName = personVo.fatherName;
		person.motherName = personVo.motherName;
		person.suffix = personVo.suffix;
		person.nickname = personVo.nickname;
		person.gender = personVo.gender;
		person.dateOfBirth = personVo.dateOfBirth;
		
		if(!StringFunctions.isNullOrEmpty(personVo.heightStr))
			person.height = Double.valueOf(personVo.heightStr);
		else if(personVo.height > 0)
			person.height = personVo.height;
		else
			person.height = 0.0;
		
		if(!StringFunctions.isNullOrEmpty(personVo.weightStr))
			person.weight = Double.valueOf(personVo.weightStr);
		else if(personVo.weight > 0)
			person.weight = personVo.weight;
		else
			person.weight = 0.0;
		
		person.mothersMaidenName = personVo.mothersMaidenName;
		person.maritalStatus = personVo.maritalStatus;
		person.socialSecurityNumber = personVo.socialSecurityNumber;
		person.passportNumber = personVo.passportNumber;
		person.passportExpireDate = personVo.passportExpireDate;
		person.totalYearsWorkExperence = personVo.totalYearsWorkExperence;
		person.classJoined = personVo.classJoined;
		person.citizenship = personVo.citizenship;
		person.age = personVo.age;
		person.licenseNumber = personVo.licenseNumber;
		person.licenseExpDate = personVo.licenseExpDate;
		person.experience = personVo.experience;
		person.phoneNumber = personVo.phoneNumber;
		if(!StringFunctions.isNullOrEmpty(personVo.bloodGroup)){
			person.bloodGroup = personVo.bloodGroup.toUpperCase();
		}
		person.parentEmail = personVo.parentEmail;
		if(!StringFunctions.isNullOrEmpty(personVo.mobileNumber)){
			person.mobileNumber = personVo.mobileNumber;
		}
		person.dateOfJoining = personVo.dateOfJoining;
		person.roleName = personVo.roleName;
		person.summary = personVo.summary;
		person.castId = personVo.castId;
		person.subCastId = personVo.subCastId;
		person.otherCastName = personVo.otherCastName;
		person.familyDoctor = personVo.familyDoctor;
		person.prefferedHospital = personVo.prefferedHospital;
		person.motherOccupation = personVo.motherOccupation;
		person.nationality = personVo.nationality;
		person.occupation = personVo.occupation;
		person.religionId = personVo.religionId;
		person.designation = personVo.designation;
		person.panNumber = personVo.panNumber;
		person.gpfNumber = personVo.gpfNumber;
		person.officeNumber = personVo.officeNumber;
		
		if(personVo.annualIncome > 0)
			person.annualIncome = personVo.annualIncome;
		else
			if(!StringFunctions.isNullOrEmpty(personVo.annualIncomeStr))
				person.annualIncome = Double.valueOf(personVo.annualIncomeStr);
		
		person.identification1 = personVo.identification1;
		person.identification2 = personVo.identification2;
		person.motherToungId = personVo.motherToungId;
		person.bankName = personVo.bankName;
		person.bankAccountNumber = personVo.bankAccountNumber;
		person.bankBranchName = personVo.bankBranchName;
		person.visionL = personVo.visionL;
		person.teeth = personVo.teeth;
		person.oralHygiene = personVo.oralHygiene;
		person.rationCardNumber = personVo.rationCardNumber;
		person.communityNumber = personVo.communityNumber;
		person.contractStartDate = personVo.contractStartDate;
		person.contractEndDate = personVo.contractEndDate;
		person.sslcNumber = personVo.sslcNumber;
		person.tmrNumber = personVo.tmrNumber;
		person.relievingDate = personVo.relievingDate;
		person.scholarShipInfo = personVo.scholarShipInfo;
		person.fatherQualification = personVo.fatherQualification;
		person.motherQualification = personVo.motherQualification;
		person.transferCertificateNo = personVo.transferCertificateNo;
		person.isDocsUploaded = personVo.isDocsUploaded;
		person.weightStr = personVo.weightStr;
		person.heightStr = personVo.heightStr;
		person.religion = personVo.religion;
		person.community = personVo.community;
		person.castName = personVo.castName;
		person.motherToung = personVo.motherToung;
		person.tcAppliedDate = personVo.tcAppliedDate;
		person.tcIssuedDate = personVo.tcIssuedDate;
		
		/*if(ObjectFunctions.isNullOrEmpty(personVo.phId)){
			if(personVo.phId){
				person.physicallyHandicappedDesc = personVo.physicallyHandicappedDesc;
				person.isPHDocsUploaded = personVo.isPHDocsUploaded;			
			}
			person.phId = personVo.phId;
	    }else{*/
			if("Yes".equalsIgnoreCase(personVo.phIdStr) || personVo.phId){
				person.phId = true;
				person.physicallyHandicappedDesc = personVo.physicallyHandicappedDesc;
				person.isPHDocsUploaded = personVo.isPHDocsUploaded;
			}else {
				person.phId = false;
			person.physicallyHandicappedDesc = null;
			person.isPHDocsUploaded ="N";
			}
		//}
		
		person.aadharNumber = personVo.aadharNumber;
		person.ifscCode = personVo.ifscCode;
		person.placeOfBirth = personVo.placeOfBirth;
		person.lastSchool = personVo.lastSchool;
		person.studentEmail = personVo.studentEmail;
		person.studentMobile = personVo.studentMobile;
		person.staffUniqueNumber = personVo.staffUniqueNumber;
		person.studentUniqueNumber = personVo.studentUniqueNumber;
		person.visionR = personVo.visionR;
		if(!StringFunctions.isNullOrEmpty(personVo.height2Str))
			person.height2 = Double.valueOf(personVo.height2Str);
		else if(personVo.height2 > 0)
			person.height2 = personVo.height2;
		else
			person.height2 = 0.0;
		if(!StringFunctions.isNullOrEmpty(personVo.weight2Str))
			person.weight2 = Double.valueOf(personVo.weight2Str);
		else if(personVo.weight2 > 0)
			person.weight2 = personVo.height2;
		else
			person.weight2 = 0.0;
		
		person.height2Str = personVo.height2Str;
		person.weight2Str = personVo.weight2Str;
		if(!StringFunctions.isNullOrEmpty(personVo.secondaryMobileNumber)){
			person.secondaryMobileNumber = personVo.secondaryMobileNumber;
		}
		if(StringFunctions.isNullOrEmpty(personVo.addressType)){
			person.addressType ="R";
		}else
			person.addressType = personVo.addressType;
		person.anotherMobileNumber = personVo.anotherMobileNumber;
		person.anotherSecondaryMobileNumber = personVo.anotherSecondaryMobileNumber;
		person.anotherParentEmail = personVo.anotherParentEmail;
		person.staffLocation = personVo.staffLocation;
		person.salaryPaymentMode = personVo.salaryPaymentMode;
		
		if(!StringFunctions.isNullOrEmpty(personVo.scholarShipAmountStr))
			person.scholarShipAmount = Double.valueOf(personVo.scholarShipAmountStr);
		else
			person.scholarShipAmount = 0.0;
		
		person.fatherAadharNumber = personVo.fatherAadharNumber;
		person.motherAadharNumber = personVo.motherAadharNumber;
		person.sameAsResidentialAddress = personVo.sameAsResidentialAddress;
		person.setStudentBankId(personVo.getStudentBankId());
		person.setStsNumber(personVo.getStsNumber());
		//staffPersonAccountDetailsVO.setDateOfBirth(ObjectFunctions.isNullOrEmpty(staff.getDateOfBirth())? "": DateFunctions.convertDateToString(staff.getDateOfBirth()));
		return person;
		
	}
	
	@Transient
	public PersonVO copyFromEntityToVo(Person person)
	{
		PersonVO personVo = new PersonVO();
		
		personVo.id = person.id;
		personVo.firstName = person.firstName;
		personVo.lastName = person.lastName;
		personVo.middleName = person.middleName;
		personVo.personalTitle = person.personalTitle;
		personVo.fatherName = person.fatherName;
		personVo.motherName = person.motherName;
		personVo.suffix = person.suffix;
		personVo.nickname = person.nickname;
		personVo.gender = person.gender;
		personVo.dateOfBirth = person.dateOfBirth;
		personVo.height = person.height;
		personVo.weight = person.weight;
		personVo.mothersMaidenName = person.mothersMaidenName;
		personVo.maritalStatus = person.maritalStatus;
		personVo.socialSecurityNumber = person.socialSecurityNumber;
		personVo.passportNumber = person.passportNumber;
		personVo.passportExpireDate = person.passportExpireDate;
		personVo.totalYearsWorkExperence = person.totalYearsWorkExperence;
		personVo.classJoined = person.classJoined;
		personVo.citizenship = person.citizenship;
		personVo.age = person.age;
		personVo.licenseNumber = person.licenseNumber;
		personVo.licenseExpDate = person.licenseExpDate;
		personVo.experience = person.experience;
		personVo.phoneNumber = person.phoneNumber;
		if(!StringFunctions.isNullOrEmpty(person.bloodGroup)){
			personVo.bloodGroup = person.bloodGroup.toUpperCase();
		}
		personVo.parentEmail = person.parentEmail;
		personVo.mobileNumber = person.mobileNumber;
		personVo.dateOfJoining = person.dateOfJoining;
		personVo.roleName = person.roleName;
		personVo.summary = person.summary;
		personVo.castId = person.castId;
		personVo.subCastId = person.subCastId;
		personVo.otherCastName = person.otherCastName;
		personVo.familyDoctor = person.familyDoctor;
		personVo.prefferedHospital = person.prefferedHospital;
		personVo.motherOccupation = person.motherOccupation;
		personVo.nationality = person.nationality;
		personVo.occupation = person.occupation;
		personVo.religionId = person.religionId;
		personVo.designation = person.designation;
		personVo.panNumber = person.panNumber;
		personVo.gpfNumber = person.gpfNumber;
		personVo.officeNumber = person.officeNumber;
		personVo.annualIncome = person.annualIncome;
		personVo.identification1 = person.identification1;
		personVo.identification2 = person.identification2;
		personVo.motherToungId = person.motherToungId;
		personVo.bankName = person.bankName;
		personVo.bankAccountNumber = person.bankAccountNumber;
		personVo.bankBranchName = person.bankBranchName;
		personVo.visionL = person.visionL;
		personVo.teeth = person.teeth;
		personVo.oralHygiene = person.oralHygiene;
		personVo.rationCardNumber = person.rationCardNumber;
		personVo.communityNumber = person.communityNumber;
		personVo.contractStartDate = person.contractStartDate;
		personVo.contractEndDate = person.contractEndDate;
		personVo.sslcNumber = person.sslcNumber;
		personVo.tmrNumber = person.tmrNumber;
		personVo.relievingDate = person.relievingDate;
		personVo.scholarShipInfo = person.scholarShipInfo;
		personVo.fatherQualification = person.fatherQualification;
		personVo.motherQualification = person.motherQualification;
		personVo.transferCertificateNo = person.transferCertificateNo;
		personVo.isDocsUploaded = person.isDocsUploaded;
		personVo.annualIncomeStr = person.annualIncomeStr;
		personVo.weightStr = person.weightStr;
		personVo.heightStr = person.heightStr;
		personVo.religion = person.religion;
		personVo.community = person.community;
		personVo.castName = person.castName;
		personVo.motherToung = person.motherToung;
		personVo.tcAppliedDate = person.tcAppliedDate;
		personVo.tcIssuedDate = person.tcIssuedDate;
		personVo.phId = person.phId;
		personVo.phIdStr = person.phIdStr;
		personVo.aadharNumber = person.aadharNumber;
		personVo.ifscCode = person.ifscCode;
		personVo.placeOfBirth = person.placeOfBirth;
		personVo.lastSchool = person.lastSchool;
		personVo.studentEmail = person.studentEmail;
		personVo.studentMobile = person.studentMobile;
		personVo.staffUniqueNumber = person.staffUniqueNumber;
		personVo.studentUniqueNumber = person.studentUniqueNumber;
		personVo.visionR = person.visionR;
		personVo.physicallyHandicappedDesc = person.physicallyHandicappedDesc;
		personVo.isPHDocsUploaded = person.isPHDocsUploaded;
		personVo.height2 = person.height2;
		personVo.weight2 = person.weight2;
		personVo.height2Str = String.valueOf(person.height2);
		personVo.weight2Str = String.valueOf(person.weight2);
		personVo.secondaryMobileNumber = person.secondaryMobileNumber;
		if(StringFunctions.isNullOrEmpty(person.addressType)){
			personVo.addressType ="R";
		}else
			personVo.addressType = person.addressType;
		personVo.anotherMobileNumber = person.anotherMobileNumber;
		personVo.anotherSecondaryMobileNumber = person.anotherSecondaryMobileNumber;
		personVo.anotherParentEmail = person.anotherParentEmail;
		personVo.dateOfBirthStr = person.getDateOfBirthStr();
		personVo.dateOfJoiningStr = person.getDateOfJoiningStr();
		personVo.staffLocation = person.staffLocation;
		personVo.salaryPaymentMode = person.salaryPaymentMode;
		
		if(person.scholarShipAmount > 0) {
			personVo.scholarShipAmountStr = String.valueOf(person.scholarShipAmount);
		}
		//staffPersonAccountDetailsVO.setDateOfBirth(ObjectFunctions.isNullOrEmpty(staff.getDateOfBirth())? "": DateFunctions.convertDateToString(staff.getDateOfBirth()));
		personVo.fatherAadharNumber = person.fatherAadharNumber;
		personVo.motherAadharNumber = person.motherAadharNumber;
		personVo.setStudentBankId(person.getStudentBankId());
		personVo.setStsNumber(person.getStsNumber());
		return personVo;
		
	}
	/**
	 * @return the fatherAadharNumber
	 */
	@Column(name = "fatherAadharNumber", nullable = true, length = 14)
	public String getFatherAadharNumber() {
		return fatherAadharNumber;
	}
	/**
	 * @param fatherAadharNumber the fatherAadharNumber to set
	 */
	public void setFatherAadharNumber(String fatherAadharNumber) {
		this.fatherAadharNumber = fatherAadharNumber;
	}
	/**
	 * @return the motherAadharNumber
	 */
	@Column(name = "motherAadharNumber", nullable = true, length = 14)
	public String getMotherAadharNumber() {
		return motherAadharNumber;
	}
	/**
	 * @param motherAadharNumber the motherAadharNumber to set
	 */
	public void setMotherAadharNumber(String motherAadharNumber) {
		this.motherAadharNumber = motherAadharNumber;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="parentsEmploymentDetailsId", insertable=true, updatable=true) 
	public ParentsEmploymentDetails getParentsEmploymentDetails() {
		return parentsEmploymentDetails;
	}
	public void setParentsEmploymentDetails(
			ParentsEmploymentDetails parentsEmploymentDetails) {
		this.parentsEmploymentDetails = parentsEmploymentDetails;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="personHealthDetailsId", insertable=true, updatable=true) 
	public PersonHealthDetails getPersonHealthDetails() {
		return personHealthDetails;
	}
	public void setPersonHealthDetails(PersonHealthDetails personHealthDetails) {
		this.personHealthDetails = personHealthDetails;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="personOtherDetailsId", insertable=true, updatable=true) 
	public PersonOtherDetails getPersonOtherDetails() {
		return personOtherDetails;
	}
	public void setPersonOtherDetails(PersonOtherDetails personOtherDetails) {
		this.personOtherDetails = personOtherDetails;
	}
	@Column(name = "sameAsResidentialAddress", nullable = true, length = 3)
	public String getSameAsResidentialAddress() {
		return sameAsResidentialAddress;
	}
	public void setSameAsResidentialAddress(String sameAsResidentialAddress) {
		this.sameAsResidentialAddress = sameAsResidentialAddress;
	}
	/**
	 * @return the studentBankId
	 */
	@Column(name = "studentBankId", nullable = true, length = 25)
	public String getStudentBankId() {
		return studentBankId;
	}
	/**
	 * @param studentBankId the studentBankId to set
	 */
	public void setStudentBankId(String studentBankId) {
		this.studentBankId = studentBankId;
	}
	/**
	 * @return the stsNumber
	 */
	 @Column(name = "stsNumber", nullable = true, length = 50)
	public String getStsNumber() {
		return stsNumber;
	}
	/**
	 * @param stsNumber the stsNumber to set
	 */
	public void setStsNumber(String stsNumber) {
		this.stsNumber = stsNumber;
	}
	
	
    
  }