package com.hyniva.sms.ws.vo;

import java.util.Date;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class PersonVO extends SMSBaseVO {
	
	public long id;
	@ExcelField(position = 4)
    public String firstName;
	@ExcelField(position = 5)
    public String lastName;
    public String middleName;
    public String personalTitle;
    @ExcelField(position = 11)
    public String fatherName;
    @ExcelField(position = 13)
    public String motherName;
    public String suffix;
    public String nickname; 
    @ExcelField(position = 6)
    public String gender="M";
    @ExcelField(position = 9)
    public Date dateOfBirth;
    public double height;
    public double weight;
    public String mothersMaidenName;
    public String maritalStatus;
    public String socialSecurityNumber;
    public String passportNumber;
    public Date passportExpireDate;
    public int totalYearsWorkExperence;
    @ExcelField(position = 10)
    public String classJoined;
    public String citizenship;
    public String  age;
    public String licenseNumber;
    public String licenseExpDate;
    public double experience;
    @ExcelField(position = 16)
    public String phoneNumber;
    @ExcelField(position = 34)
    public String bloodGroup;
    @ExcelField(position = 15)
	 public String parentEmail;
    @ExcelField(position = 17)
	 public String mobileNumber;
    @ExcelField(position = 8)
	 public Date dateOfJoining;	
	 public String roleName;
	 public String summary;
	 public long castId;
	 public long subCastId;
	 public String otherCastName;
	 @ExcelField(position = 36)
	 public String familyDoctor;
	 @ExcelField(position = 37)
	 public String prefferedHospital;
	 @ExcelField(position = 14)
	 public String motherOccupation;
	 @ExcelField(position = 25)
	 public String nationality;
	 @ExcelField(position = 12)
	 public String occupation;   
	 public long religionId;
	 public String designation;
	 public String panNumber;
	 public String gpfNumber;
	 public String officeNumber;
	 public double annualIncome;
	 @ExcelField(position = 30)
	 public String identification1;
	 @ExcelField(position = 31)
	 public String identification2;
	 public long motherToungId;

	 public String bankName;
	 public String bankAccountNumber;
	 public String bankBranchName;
	 @ExcelField(position = 39)
	 public String visionL;
	 @ExcelField(position = 41)
	 public String teeth;
	 @ExcelField(position = 42)
	 public String oralHygiene;
	 @ExcelField(position = 26)
	 public String rationCardNumber;
	 @ExcelField(position = 27)
	 public String communityNumber; 
	 public Date contractStartDate;	
	 public Date contractEndDate;	
	 @ExcelField(position = 28)
	 public String sslcNumber;
	 @ExcelField(position = 29)
	 public String tmrNumber;
	 
	 @ExcelField(position = 43)
	 public Date relievingDate;
	 @ExcelField(position = 45)
	 public String scholarShipInfo; 
	 public String fatherQualification;
    public String motherQualification;
    public String transferCertificateNo;
    public String isDocsUploaded;
	 
    @ExcelField(position = 32)
	public String annualIncomeStr;
    
    @ExcelField(position = 40)
    public String weightStr;
    
    @ExcelField(position = 38)
    public String heightStr;
    
    @ExcelField(position = 22)
    public String religion;
    
    @ExcelField(position = 23)
    public String community;//Cast Name
    
    @ExcelField(position = 24)
    public String castName;//Subcast Name
    
    @ExcelField(position = 33)
    public String motherToung;
    @ExcelField(position = 47)
    public Date tcAppliedDate;
    @ExcelField(position = 48)
    public Date tcIssuedDate;
    public boolean phId;
    
    @ExcelField(position = 46)
    public String phIdStr;
    @ExcelField(position = 57)
    public String aadharNumber;
    public String ifscCode;
    @ExcelField(position = 50)
    public String placeOfBirth;
    @ExcelField(position = 51)
    public String lastSchool;
    @ExcelField(position = 52)
    public String studentEmail; 
    @ExcelField(position = 53)
    public String studentMobile;
    
    public String staffUniqueNumber;
    @ExcelField(position = 56)
	public String studentUniqueNumber;
    @ExcelField(position = 60)
    public String visionR;
	
    public String isPHDocsUploaded;
    public double height2;
    public double weight2;
    @ExcelField(position = 67)
    public String height2Str;
    @ExcelField(position = 68)
    public String weight2Str;
    @ExcelField(position = 70)
    public String secondaryMobileNumber;
    @ExcelField(position = 71)
    public String addressType;
    @ExcelField(position = 72)
    public String anotherMobileNumber;
    @ExcelField(position = 73)
	public String anotherSecondaryMobileNumber;
    @ExcelField(position = 74)
	public String anotherParentEmail;
	public String dateOfBirthStr;
	public String dateOfJoiningStr;
	public String  salaryPaymentMode;
	public String  staffLocation;
	@ExcelField(position = 78)
	public String physicallyHandicappedDesc;
	@ExcelField(position = 79)
	public String stsNumber;
   
	public String fatherAadharNumber;
	public String motherAadharNumber;
	public String scholarShipAmountStr;
	public PersonOtherDetailsVO personOtherDetailsVO;
 	public ParentsEmploymentDetailsVO parentsEmploymentDetailsVO;
 	public PersonHealthDetailsVO personHealthDetailsVO;
 	public String sameAsResidentialAddress;
 	
 	private String fatherContactNumber;
	private String studentBankId; 
	
	
 	public String getScholarShipAmountStr() {
		return scholarShipAmountStr;
	}
	public void setScholarShipAmountStr(String scholarShipAmountStr) {
		this.scholarShipAmountStr = scholarShipAmountStr;
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
	
	
	
	public String getDateOfJoiningStr() {
		return dateOfJoiningStr;
	}
	public void setDateOfJoiningStr(String dateOfJoiningStr) {
		this.dateOfJoiningStr = dateOfJoiningStr;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDateOfBirthStr() {
		return dateOfBirthStr;
	}
	public void setDateOfBirthStr(String dateOfBirthStr) {
		this.dateOfBirthStr = dateOfBirthStr;
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPersonalTitle() {
		return personalTitle;
	}
	public void setPersonalTitle(String personalTitle) {
		this.personalTitle = personalTitle;
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
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public String getMothersMaidenName() {
		return mothersMaidenName;
	}
	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public Date getPassportExpireDate() {
		return passportExpireDate;
	}
	public void setPassportExpireDate(Date passportExpireDate) {
		this.passportExpireDate = passportExpireDate;
	}
	public int getTotalYearsWorkExperence() {
		return totalYearsWorkExperence;
	}
	public void setTotalYearsWorkExperence(int totalYearsWorkExperence) {
		this.totalYearsWorkExperence = totalYearsWorkExperence;
	}
	public String getClassJoined() {
		return classJoined;
	}
	public void setClassJoined(String classJoined) {
		this.classJoined = classJoined;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getLicenseExpDate() {
		return licenseExpDate;
	}
	public void setLicenseExpDate(String licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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
	public String getOtherCastName() {
		return otherCastName;
	}
	public void setOtherCastName(String otherCastName) {
		this.otherCastName = otherCastName;
	}
	public String getFamilyDoctor() {
		return familyDoctor;
	}
	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}
	public String getPrefferedHospital() {
		return prefferedHospital;
	}
	public void setPrefferedHospital(String prefferedHospital) {
		this.prefferedHospital = prefferedHospital;
	}
	public String getMotherOccupation() {
		return motherOccupation;
	}
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public long getReligionId() {
		return religionId;
	}
	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getGpfNumber() {
		return gpfNumber;
	}
	public void setGpfNumber(String gpfNumber) {
		this.gpfNumber = gpfNumber;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
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
	public String getVisionL() {
		return visionL;
	}
	public void setVisionL(String visionL) {
		this.visionL = visionL;
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
	public String getCommunityNumber() {
		return communityNumber;
	}
	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
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
	public String getIsDocsUploaded() {
		return isDocsUploaded;
	}
	public void setIsDocsUploaded(String isDocsUploaded) {
		this.isDocsUploaded = isDocsUploaded;
	}
	public String getAnnualIncomeStr() {
		return annualIncomeStr;
	}
	public void setAnnualIncomeStr(String annualIncomeStr) {
		this.annualIncomeStr = annualIncomeStr;
	}
	public String getWeightStr() {
		return weightStr;
	}
	public void setWeightStr(String weightStr) {
		this.weightStr = weightStr;
	}
	public String getHeightStr() {
		return heightStr;
	}
	public void setHeightStr(String heightStr) {
		this.heightStr = heightStr;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getCastName() {
		return castName;
	}
	public void setCastName(String castName) {
		this.castName = castName;
	}
	public String getMotherToung() {
		return motherToung;
	}
	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
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
	public boolean isPhId() {
		return phId;
	}
	public void setPhId(boolean phId) {
		this.phId = phId;
	}
	public String getPhIdStr() {
		return phIdStr;
	}
	public void setPhIdStr(String phIdStr) {
		this.phIdStr = phIdStr;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getLastSchool() {
		return lastSchool;
	}
	public void setLastSchool(String lastSchool) {
		this.lastSchool = lastSchool;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentMobile() {
		return studentMobile;
	}
	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}
	public String getStaffUniqueNumber() {
		return staffUniqueNumber;
	}
	public void setStaffUniqueNumber(String staffUniqueNumber) {
		this.staffUniqueNumber = staffUniqueNumber;
	}
	public String getStudentUniqueNumber() {
		return studentUniqueNumber;
	}
	public void setStudentUniqueNumber(String studentUniqueNumber) {
		this.studentUniqueNumber = studentUniqueNumber;
	}
	public String getVisionR() {
		return visionR;
	}
	public void setVisionR(String visionR) {
		this.visionR = visionR;
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
	public String getHeight2Str() {
		return height2Str;
	}
	public void setHeight2Str(String height2Str) {
		this.height2Str = height2Str;
	}
	public String getWeight2Str() {
		return weight2Str;
	}
	public void setWeight2Str(String weight2Str) {
		this.weight2Str = weight2Str;
	}
	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}
	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}
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
	
	public String getPersonName()
    {
        return getFullPersonName(false,true,true);
    }
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
	/**
	 * @return the fatherAadharNumber
	 */
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
	public String getMotherAadharNumber() {
		return motherAadharNumber;
	}
	/**
	 * @param motherAadharNumber the motherAadharNumber to set
	 */
	public void setMotherAadharNumber(String motherAadharNumber) {
		this.motherAadharNumber = motherAadharNumber;
	}
	public ParentsEmploymentDetailsVO getParentsEmploymentDetailsVO() {
		return parentsEmploymentDetailsVO;
	}
	public void setParentsEmploymentDetailsVO(
			ParentsEmploymentDetailsVO parentsEmploymentDetailsVO) {
		this.parentsEmploymentDetailsVO = parentsEmploymentDetailsVO;
	}
	public PersonHealthDetailsVO getPersonHealthDetailsVO() {
		return personHealthDetailsVO;
	}
	public void setPersonHealthDetailsVO(PersonHealthDetailsVO personHealthDetailsVO) {
		this.personHealthDetailsVO = personHealthDetailsVO;
	}
	public PersonOtherDetailsVO getPersonOtherDetailsVO() {
		return personOtherDetailsVO;
	}
	public void setPersonOtherDetailsVO(PersonOtherDetailsVO personOtherDetailsVO) {
		this.personOtherDetailsVO = personOtherDetailsVO;
	}
	public String getSameAsResidentialAddress() {
		return sameAsResidentialAddress;
	}
	public void setSameAsResidentialAddress(String sameAsResidentialAddress) {
		this.sameAsResidentialAddress = sameAsResidentialAddress;
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
	/**
	 * @return the studentBankId
	 */
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
