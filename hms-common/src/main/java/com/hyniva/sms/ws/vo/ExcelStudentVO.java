package com.hyniva.sms.ws.vo;

import java.util.Date;

import com.churchgroup.util.string.StringFunctions;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;


@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class ExcelStudentVO{
	
	public long id;
	 
	public long applicationId;
	
	@ExcelField(position = 1)
	public String stuId;
	
	@ExcelField(position = 2)
	public String admissionNumber;
	
	@ExcelField(position = 3)
	public String registerNumber; 
	
	@ExcelField(position = 4)
    public String rollNumberStr;
	
    @ExcelField(position = 5)
	public String studentUniqueNumber;
    
	@ExcelField(position = 6)
    public String firstName;
	
	@ExcelField(position = 7)
    public String lastName;
	
	@ExcelField(position = 8)
    public Date dateOfBirth;
	
	@ExcelField(position = 9)
    public String gender="M";
	
	@ExcelField(position = 10)
	public String classAndSection;
    
	@ExcelField(position = 11)
	public Date dateOfJoining;	
	
	@ExcelField(position = 12)
	public String rteStatus;
	
	@ExcelField(position = 13)
    public String classJoined;
	
	@ExcelField(position = 14)
	public String transportMode;
	
	@ExcelField(position = 15)
    public String fatherName;
	
	@ExcelField(position = 16)
	public String occupation;   
	
    @ExcelField(position = 17)
    public String motherName;
    
    @ExcelField(position = 18)
	public String motherOccupation;
	
    @ExcelField(position = 19)
	public String annualIncomeStr;
	
    @ExcelField(position = 20)
	public String bplStatus;//Below Poverty Line
    
    @ExcelField(position = 21)
	public String bplNumber;
    
    @ExcelField(position = 22)
	public String mobileNumber;
    
    @ExcelField(position = 23)
    public String secondaryMobileNumber;
	
    @ExcelField(position = 24)
    public String phoneNumber;
    
    @ExcelField(position = 25)
	public String parentEmail;
    
    @ExcelField(position = 26)
    public String addressLine1;
    
    @ExcelField(position = 27)
    public String addressLine2;
    
    @ExcelField(position = 28)
	public String city="";
    
	@ExcelField(position = 29)
	public String districtName="";
	
	@ExcelField(position = 30)
	public String state="";
	
	@ExcelField(position = 31)
	public String postalCode="";
    
	@ExcelField(position = 32)
    public String studentEmail;
	
    @ExcelField(position = 33)
    public String studentMobile;
    
    @ExcelField(position = 34)
    public String enrollmentCode;
    
    @ExcelField(position = 35)
	public String identification1;
    
	@ExcelField(position = 36)
	public String identification2;
	 
	@ExcelField(position = 37)
    public String motherToung;
	@ExcelField(position = 38)
    public String placeOfBirth;
	@ExcelField(position = 39)
	public String nationality;
	
	@ExcelField(position = 40)
    public String religion;
    
    @ExcelField(position = 41)
    public String community;//Cast Name
    
    @ExcelField(position = 42)
    public String castName;//Subcast Name
    @ExcelField(position = 43)
	public String categoryName;
    @ExcelField(position = 44)
	public String scholarShipInfo; 
    
    @ExcelField(position = 45)
    public String scholarShipAmount;
	
    @ExcelField(position = 46)
	public String rationCardNumber;
    
    @ExcelField(position = 47)
    public String aadharNumber;
    
    @ExcelField(position = 48)
	private String isJoinedThroughAdmissionStr;
    
    @ExcelField(position = 49)
	public String hostelMode;
    @ExcelField(position = 50)
    public String bloodGroup;
    
    @ExcelField(position = 51)
    public String heightStr;
    
    @ExcelField(position = 52)
    public String height2Str;
    
    @ExcelField(position = 53)
    public String weightStr;
    
    @ExcelField(position = 54)
    public String weight2Str;
    
    @ExcelField(position = 55)
    public String visionR;
    
    @ExcelField(position = 56)
	public String visionL;
    
    @ExcelField(position = 57)
	public String teeth;
    
    @ExcelField(position = 58)
    public String phIdStr;
    
    @ExcelField(position = 59)
	public String physicallyHandicappedDesc;
    
    @ExcelField(position = 60)
    public String fatherAadharNumber;
    
    @ExcelField(position = 61)
    private String noOfDependents;
    
    @ExcelField(position = 62)
	public String motherAadharNumber;
    
    @ExcelField(position = 63)
   	public String fatherQualification;
    
    @ExcelField(position = 64)
   	public String fatherOrganizationName;
    
    @ExcelField(position = 65)
   	public String fatherDesignation;
    
    @ExcelField(position = 66)
   	public String fatherSelfEmployed;
    
    @ExcelField(position = 67)
   	public String fatherNatureofBusiness;
    
    @ExcelField(position = 68)
   	public String motherQualification;
    
    @ExcelField(position = 69)
    private String motherOrganizationName;
    
    @ExcelField(position = 70)
    private String motherDesignation;
    
    @ExcelField(position = 71)
    private String motherSelfEmployed;
    
    @ExcelField(position = 72)
    private String motherNatureofBusiness;
    
    @ExcelField(position = 73)
    private String motherAnnualIncome;
    
    @ExcelField(position = 74)
    private String motherEmail;
    
    //New fields
    @ExcelField(position = 75)
    public String sameAsResidentialAddress;
    
    @ExcelField(position = 76)
    public String corAddressLine1;
    
    @ExcelField(position = 77)
    public String corAddressLine2;
    
    @ExcelField(position = 78)
	public String corCity="";
    
	@ExcelField(position = 79)
	public String corDistrictName="";
	
	@ExcelField(position = 80)
	public String corstate="";
	
	@ExcelField(position = 81)
	public String corPostalCode="";
	
	@ExcelField(position = 82)
	private String previousSchoolName;
    
    @ExcelField(position = 83)
    private String scstCommunity;
     
    @ExcelField(position = 84)
    private String siblingName1;
    
    @ExcelField(position = 85)
    private String siblingName2;
    
    @ExcelField(position = 86)
    private String siblingName3;
    
    @ExcelField(position = 87)
    private String allergies;
        
    @ExcelField(position = 88)
   	public String heartProblem;
    
    @ExcelField(position = 89)
   	public String diabetes;
    
    @ExcelField(position = 90)
   	public String asthma;
    
    @ExcelField(position = 91)
   	public String OtherMedicalCondition;
    
    @ExcelField(position = 92)
   	public String familyDoctor;
    
    @ExcelField(position = 93)
   	public String doctorsContactNo;
    
    @ExcelField(position = 94)
   	public String emergencyNo1;
    
    @ExcelField(position = 95)
   	public String emergencyNo2;
    
    @ExcelField(position = 96)
    public String houseType;
    
    @ExcelField(position = 97)
    public String studentBankId;
    
    @ExcelField(position = 98)
    public String stsNumber;
      
	
	//public UserVO account;
    public long categoryId;
    public String feePaidStatus="N";

    
    
    public String getRollNumberStr() {
		return rollNumberStr;
	}

	public void setRollNumberStr(String rollNumberStr) {
		this.rollNumberStr = rollNumberStr;
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
	
	
	public String getFeePaidStatus() {
		return feePaidStatus;
	}

	public void setFeePaidStatus(String feePaidStatus) {
		this.feePaidStatus = feePaidStatus;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/*public UserVO getAccount() {
		return account;
	}

	public void setAccount(UserVO account) {
		this.account = account;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getClassAndSection() {
		return classAndSection;
	}

	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getRteStatus() {
		return rteStatus;
	}

	public void setRteStatus(String rteStatus) {
		this.rteStatus = rteStatus;
	}

	public String getClassJoined() {
		return classJoined;
	}

	public void setClassJoined(String classJoined) {
		this.classJoined = classJoined;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	public String getAnnualIncomeStr() {
		return annualIncomeStr;
	}

	public void setAnnualIncomeStr(String annualIncomeStr) {
		this.annualIncomeStr = annualIncomeStr;
	}

	public String getBplStatus() {
		return bplStatus;
	}

	public void setBplStatus(String bplStatus) {
		this.bplStatus = bplStatus;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}

	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

	public String getStudentUniqueNumber() {
		return studentUniqueNumber;
	}

	public void setStudentUniqueNumber(String studentUniqueNumber) {
		this.studentUniqueNumber = studentUniqueNumber;
	}

	public String getEnrollmentCode() {
		return enrollmentCode;
	}

	public void setEnrollmentCode(String enrollmentCode) {
		this.enrollmentCode = enrollmentCode;
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

	public String getMotherToung() {
		return motherToung;
	}

	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getScholarShipInfo() {
		return scholarShipInfo;
	}

	public void setScholarShipInfo(String scholarShipInfo) {
		this.scholarShipInfo = scholarShipInfo;
	}

	public String getScholarShipAmount() {
		return scholarShipAmount;
	}

	public void setScholarShipAmount(String scholarShipAmount) {
		this.scholarShipAmount = scholarShipAmount;
	}

	public String getRationCardNumber() {
		return rationCardNumber;
	}

	public void setRationCardNumber(String rationCardNumber) {
		this.rationCardNumber = rationCardNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getIsJoinedThroughAdmissionStr() {
		return isJoinedThroughAdmissionStr;
	}

	public void setIsJoinedThroughAdmissionStr(String isJoinedThroughAdmissionStr) {
		this.isJoinedThroughAdmissionStr = isJoinedThroughAdmissionStr;
	}

	public String getHostelMode() {
		return hostelMode;
	}

	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getHeightStr() {
		return heightStr;
	}

	public void setHeightStr(String heightStr) {
		this.heightStr = heightStr;
	}

	public String getHeight2Str() {
		return height2Str;
	}

	public void setHeight2Str(String height2Str) {
		this.height2Str = height2Str;
	}

	public String getWeightStr() {
		return weightStr;
	}

	public void setWeightStr(String weightStr) {
		this.weightStr = weightStr;
	}

	public String getWeight2Str() {
		return weight2Str;
	}

	public void setWeight2Str(String weight2Str) {
		this.weight2Str = weight2Str;
	}

	public String getVisionR() {
		return visionR;
	}

	public void setVisionR(String visionR) {
		this.visionR = visionR;
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

	public String getPhIdStr() {
		return phIdStr;
	}

	public void setPhIdStr(String phIdStr) {
		this.phIdStr = phIdStr;
	}

	public String getPhysicallyHandicappedDesc() {
		return physicallyHandicappedDesc;
	}

	public void setPhysicallyHandicappedDesc(String physicallyHandicappedDesc) {
		this.physicallyHandicappedDesc = physicallyHandicappedDesc;
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

	public String getNoOfDependents() {
		return noOfDependents;
	}

	public void setNoOfDependents(String noOfDependents) {
		this.noOfDependents = noOfDependents;
	}

	public String getFatherQualification() {
		return fatherQualification;
	}

	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}

	public String getFatherOrganizationName() {
		return fatherOrganizationName;
	}

	public void setFatherOrganizationName(String fatherOrganizationName) {
		this.fatherOrganizationName = fatherOrganizationName;
	}

	public String getFatherDesignation() {
		return fatherDesignation;
	}

	public void setFatherDesignation(String fatherDesignation) {
		this.fatherDesignation = fatherDesignation;
	}

	public String getFatherSelfEmployed() {
		return fatherSelfEmployed;
	}

	public void setFatherSelfEmployed(String fatherSelfEmployed) {
		this.fatherSelfEmployed = fatherSelfEmployed;
	}

	public String getFatherNatureofBusiness() {
		return fatherNatureofBusiness;
	}

	public void setFatherNatureofBusiness(String fatherNatureofBusiness) {
		this.fatherNatureofBusiness = fatherNatureofBusiness;
	}

	public String getMotherQualification() {
		return motherQualification;
	}

	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}

	public String getMotherOrganizationName() {
		return motherOrganizationName;
	}

	public void setMotherOrganizationName(String motherOrganizationName) {
		this.motherOrganizationName = motherOrganizationName;
	}

	public String getMotherDesignation() {
		return motherDesignation;
	}

	public void setMotherDesignation(String motherDesignation) {
		this.motherDesignation = motherDesignation;
	}

	public String getMotherSelfEmployed() {
		return motherSelfEmployed;
	}

	public void setMotherSelfEmployed(String motherSelfEmployed) {
		this.motherSelfEmployed = motherSelfEmployed;
	}

	public String getMotherNatureofBusiness() {
		return motherNatureofBusiness;
	}

	public void setMotherNatureofBusiness(String motherNatureofBusiness) {
		this.motherNatureofBusiness = motherNatureofBusiness;
	}

	public String getMotherAnnualIncome() {
		return motherAnnualIncome;
	}

	public void setMotherAnnualIncome(String motherAnnualIncome) {
		this.motherAnnualIncome = motherAnnualIncome;
	}

	public String getMotherEmail() {
		return motherEmail;
	}

	public void setMotherEmail(String motherEmail) {
		this.motherEmail = motherEmail;
	}

	public String getScstCommunity() {
		return scstCommunity;
	}

	public void setScstCommunity(String scstCommunity) {
		this.scstCommunity = scstCommunity;
	}

	

	public String getPreviousSchoolName() {
		return previousSchoolName;
	}

	public void setPreviousSchoolName(String previousSchoolName) {
		this.previousSchoolName = previousSchoolName;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getHeartProblem() {
		return heartProblem;
	}

	public void setHeartProblem(String heartProblem) {
		this.heartProblem = heartProblem;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	public String getAsthma() {
		return asthma;
	}

	public void setAsthma(String asthma) {
		this.asthma = asthma;
	}

	public String getOtherMedicalCondition() {
		return OtherMedicalCondition;
	}

	public void setOtherMedicalCondition(String otherMedicalCondition) {
		OtherMedicalCondition = otherMedicalCondition;
	}

	public String getFamilyDoctor() {
		return familyDoctor;
	}

	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}

	public String getDoctorsContactNo() {
		return doctorsContactNo;
	}

	public void setDoctorsContactNo(String doctorsContactNo) {
		this.doctorsContactNo = doctorsContactNo;
	}

	public String getEmergencyNo1() {
		return emergencyNo1;
	}

	public void setEmergencyNo1(String emergencyNo1) {
		this.emergencyNo1 = emergencyNo1;
	}

	public String getEmergencyNo2() {
		return emergencyNo2;
	}

	public void setEmergencyNo2(String emergencyNo2) {
		this.emergencyNo2 = emergencyNo2;
	}

	public String getCorAddressLine1() {
		return corAddressLine1;
	}

	public void setCorAddressLine1(String corAddressLine1) {
		this.corAddressLine1 = corAddressLine1;
	}

	public String getCorAddressLine2() {
		return corAddressLine2;
	}

	public void setCorAddressLine2(String corAddressLine2) {
		this.corAddressLine2 = corAddressLine2;
	}

	public String getCorCity() {
		return corCity;
	}

	public void setCorCity(String corCity) {
		this.corCity = corCity;
	}

	public String getCorDistrictName() {
		return corDistrictName;
	}

	public void setCorDistrictName(String corDistrictName) {
		this.corDistrictName = corDistrictName;
	}

	public String getCorstate() {
		return corstate;
	}

	public void setCorstate(String corstate) {
		this.corstate = corstate;
	}

	public String getCorPostalCode() {
		return corPostalCode;
	}

	public void setCorPostalCode(String corPostalCode) {
		this.corPostalCode = corPostalCode;
	}

	public String getSiblingName1() {
		return siblingName1;
	}

	public void setSiblingName1(String siblingName1) {
		this.siblingName1 = siblingName1;
	}

	public String getSiblingName2() {
		return siblingName2;
	}

	public void setSiblingName2(String siblingName2) {
		this.siblingName2 = siblingName2;
	}

	public String getSiblingName3() {
		return siblingName3;
	}

	public void setSiblingName3(String siblingName3) {
		this.siblingName3 = siblingName3;
	}

	public String getSameAsResidentialAddress() {
		return sameAsResidentialAddress;
	}

	public void setSameAsResidentialAddress(String sameAsResidentialAddress) {
		this.sameAsResidentialAddress = sameAsResidentialAddress;
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
