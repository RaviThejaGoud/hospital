package com.hyniva.sms.ws.vo;

import java.util.Date;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;


public class OnlineApplicationDetailsVO {
	
	
	public long id;
	public String lastClassStudied;
    public String bloodGroup;
    public String lastSchoolName;
    public String fatherName;
    public String fatherOccupation;
    public String fatherDesignation;
    public String motherName;
    public String motherQualification;
    public String parentEmailId;
    public String phoneNumber;
    public String mobileNumber;
    public String tCNumber;
    public String transportType;
    public String nationality;
    public String collectedDoc;
    public StudentVo studentVo;
    public String firstName;
    public String lastName;
    public String gender;
    public long classId;
    public long feeCategoryId;
    public String status;
    public long religionId;
    public long casteId;
    public double annualIncome;
    public String dateOfBirth;
    public String applicationDate;
    public String fatherQualification;
    public long academicYearId;
	public AddressVO addressVO;
	public String bplStatus;//Below Poverty Line
	public String rteStatus;
    
	protected String className;
	public String lastSchool;
	public String occupation;
	public String designation;
	public String parentEmail; 
	public long receiptNumber;
	public String transportMode;
	public String studentMobile;
	public String registerNumber; 
	public String studentUniqueNumber;
	public String aadharCardNumber; 
	public String scholarShipInfo;
	public String hostelMode;
	public String placeOfBirth;
	public String rationCardNumber;
	public String communityNumber; 
	public String sslcNumber;
	public String tmrNumber;
	public String studentEmail;
	public String teeth;
	public String visionL;
	public String visionR;
	public String heightStr;
	public String weightStr;
	public String lastClassAttended;
	public String oralHygiene;
	public String familyDoctor;
	public String prefferedHospital;
	public String identification1;
	public String identification2;
	public String goals;
	public String strengths;
	public String interestsAndHobbies;
	public String achievements;
	public String responsibilities;
	public boolean phId;
	public String physicallyHandicappedDesc;
	public String isPHDocsUploaded;
	public String transferCertificateNo;
	
	public String organizationName;
	public String academicYear;
	
	public String fatherAadharNumber;
	public String motherAadharNumber;
	public Date dateOfBirthDate;
	public Date dateOfJoiningDate;	
	
	
	public int age;
	public String ageAppropriation;
	public String urbanOrRural;
	public String studentCasteCertificate;
	public String fatherCasteCertificate;
	public String motherCasteCertificate;
	public String fatherCaste;
	public String motherCaste;
	public String socialCategory;
	public String special;
	public String bhagyalakshmiBondNo;
	public String stream;
	public String mediumOfInstruction;
	public String previousSchoolAffiliation;
	
	public Date transferCertificateDate;
	public String previousSchoolType;
	public String previousSchoolAddress;
	public String studentOrParentBankAccountName;
	public String studentOrParentBankAccountNumber;
	public String bankIFSCCode;
	public String motherOccupation;
	 
	public String monthlyIncome;
	public String anyOtherLanguageSpoken;
	public String specialInterest;
	public String anyHealthDiseases;
	public String specificPointNoted;
	public String motherMobileNumber;
	public String motherEmailAddress; 
	
	public int noOfBrother;
	public int noOfSisters;
	public int noOfElder;
	public int noOfYounger;
	public Date createdDate;
	public String studentCaste;
	public String motherToung;
	public String residentialAddress;
	public String religion;
	public String castName;
	public String subCastName;
	public String bplNumber;
	
	public String previousSchoolPincode;
	public String previousSchoolDistictName;
	public String previousSchoolTaluka;
	public String previousSchoolCity;
	public String previousSchoolStreetName;
	
	
	public String studentResidencePincode;
	public String studentResidenceDistictName;
	public String studentResidenceTaluka;
	public String studentResidenceCity;
	public String studentResidenceStreetName;
	
	public int noOfElderSisters;
	public int noOfYoungerSisters;
	public String phIdStr;
	public String transportModeStr;
	
	
	
	
	public String getTransportModeStr() {
		return transportModeStr;
	}

	public void setTransportModeStr(String transportModeStr) {
		this.transportModeStr = transportModeStr;
	}

	public String getPhIdStr() {
		return phIdStr;
	}

	public void setPhIdStr(String phIdStr) {
		this.phIdStr = phIdStr;
	}

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

	public String getStudentResidencePincode() {
		return studentResidencePincode;
	}

	public void setStudentResidencePincode(String studentResidencePincode) {
		this.studentResidencePincode = studentResidencePincode;
	}

	public String getStudentResidenceDistictName() {
		return studentResidenceDistictName;
	}

	public void setStudentResidenceDistictName(String studentResidenceDistictName) {
		this.studentResidenceDistictName = studentResidenceDistictName;
	}

	public String getStudentResidenceTaluka() {
		return studentResidenceTaluka;
	}

	public void setStudentResidenceTaluka(String studentResidenceTaluka) {
		this.studentResidenceTaluka = studentResidenceTaluka;
	}

	public String getStudentResidenceCity() {
		return studentResidenceCity;
	}

	public void setStudentResidenceCity(String studentResidenceCity) {
		this.studentResidenceCity = studentResidenceCity;
	}

	public String getStudentResidenceStreetName() {
		return studentResidenceStreetName;
	}

	public void setStudentResidenceStreetName(String studentResidenceStreetName) {
		this.studentResidenceStreetName = studentResidenceStreetName;
	}

	public String getPreviousSchoolStreetName() {
		return previousSchoolStreetName;
	}

	public void setPreviousSchoolStreetName(String previousSchoolStreetName) {
		this.previousSchoolStreetName = previousSchoolStreetName;
	}

	public String getPreviousSchoolPincode() {
		return previousSchoolPincode;
	}

	public void setPreviousSchoolPincode(String previousSchoolPincode) {
		this.previousSchoolPincode = previousSchoolPincode;
	}

	public String getPreviousSchoolDistictName() {
		return previousSchoolDistictName;
	}

	public void setPreviousSchoolDistictName(String previousSchoolDistictName) {
		this.previousSchoolDistictName = previousSchoolDistictName;
	}

	public String getPreviousSchoolTaluka() {
		return previousSchoolTaluka;
	}

	public void setPreviousSchoolTaluka(String previousSchoolTaluka) {
		this.previousSchoolTaluka = previousSchoolTaluka;
	}

	public String getPreviousSchoolCity() {
		return previousSchoolCity;
	}

	public void setPreviousSchoolCity(String previousSchoolCity) {
		this.previousSchoolCity = previousSchoolCity;
	}

	public String getBplNumber() {
		return bplNumber;
	}

	public void setBplNumber(String bplNumber) {
		this.bplNumber = bplNumber;
	}

	public String getCastName() {
		return castName;
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

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPreviousSchoolAddress() {
		return previousSchoolAddress;
	}

	public void setPreviousSchoolAddress(String previousSchoolAddress) {
		this.previousSchoolAddress = previousSchoolAddress;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getMotherToung() {
		return motherToung;
	}

	public void setMotherToung(String motherToung) {
		this.motherToung = motherToung;
	}

	public String getStudentCaste() {
		return studentCaste;
	}

	public void setStudentCaste(String studentCaste) {
		this.studentCaste = studentCaste;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getMotherMobileNumber() {
		return motherMobileNumber;
	}

	public void setMotherMobileNumber(String motherMobileNumber) {
		this.motherMobileNumber = motherMobileNumber;
	}

	public String getMotherEmailAddress() {
		return motherEmailAddress;
	}

	public void setMotherEmailAddress(String motherEmailAddress) {
		this.motherEmailAddress = motherEmailAddress;
	}

	public int getNoOfBrother() {
		return noOfBrother;
	}

	public void setNoOfBrother(int noOfBrother) {
		this.noOfBrother = noOfBrother;
	}

	public int getNoOfSisters() {
		return noOfSisters;
	}

	public void setNoOfSisters(int noOfSisters) {
		this.noOfSisters = noOfSisters;
	}

	public int getNoOfElder() {
		return noOfElder;
	}

	public void setNoOfElder(int noOfElder) {
		this.noOfElder = noOfElder;
	}

	public int getNoOfYounger() {
		return noOfYounger;
	}

	public void setNoOfYounger(int noOfYounger) {
		this.noOfYounger = noOfYounger;
	}

	public Date getDateOfJoiningDate() {
		return dateOfJoiningDate;
	}

	public void setDateOfJoiningDate(Date dateOfJoiningDate) {
		this.dateOfJoiningDate = dateOfJoiningDate;
	}

	public Date getDateOfBirthDate() {
		return dateOfBirthDate;
	}

	public void setDateOfBirthDate(Date dateOfBirthDate) {
		this.dateOfBirthDate = dateOfBirthDate;
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
	
	
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getTransferCertificateNo() {
		return transferCertificateNo;
	}
	public void setTransferCertificateNo(String transferCertificateNo) {
		this.transferCertificateNo = transferCertificateNo;
	}
	public String getIsPHDocsUploaded() {
		return isPHDocsUploaded;
	}
	public void setIsPHDocsUploaded(String isPHDocsUploaded) {
		this.isPHDocsUploaded = isPHDocsUploaded;
	}
	public boolean isPhId() {
		return phId;
	}
	public void setPhId(boolean phId) {
		this.phId = phId;
	}
	public String getPhysicallyHandicappedDesc() {
		return physicallyHandicappedDesc;
	}
	public void setPhysicallyHandicappedDesc(String physicallyHandicappedDesc) {
		this.physicallyHandicappedDesc = physicallyHandicappedDesc;
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
	public String getInterestsAndHobbies() {
		return interestsAndHobbies;
	}
	public void setInterestsAndHobbies(String interestsAndHobbies) {
		this.interestsAndHobbies = interestsAndHobbies;
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
	public String getPrefferedHospital() {
		return prefferedHospital;
	}
	public void setPrefferedHospital(String prefferedHospital) {
		this.prefferedHospital = prefferedHospital;
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
	public String getFamilyDoctor() {
		return familyDoctor;
	}
	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}
	public String getOralHygiene() {
		return oralHygiene;
	}
	public void setOralHygiene(String oralHygiene) {
		this.oralHygiene = oralHygiene;
	}
	public String getLastClassAttended() {
		return lastClassAttended;
	}
	public void setLastClassAttended(String lastClassAttended) {
		this.lastClassAttended = lastClassAttended;
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
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getTmrNumber() {
		return tmrNumber;
	}
	public void setTmrNumber(String tmrNumber) {
		this.tmrNumber = tmrNumber;
	}
	public String getSslcNumber() {
		return sslcNumber;
	}
	public void setSslcNumber(String sslcNumber) {
		this.sslcNumber = sslcNumber;
	}
	public String getCommunityNumber() {
		return communityNumber;
	}
	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}
	public String getRationCardNumber() {
		return rationCardNumber;
	}
	public void setRationCardNumber(String rationCardNumber) {
		this.rationCardNumber = rationCardNumber;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getHostelMode() {
		return hostelMode;
	}
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}
	public String getScholarShipInfo() {
		return scholarShipInfo;
	}
	public void setScholarShipInfo(String scholarShipInfo) {
		this.scholarShipInfo = scholarShipInfo;
	}
	public String getAadharCardNumber() {
		return aadharCardNumber;
	}
	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}
	public String getStudentUniqueNumber() {
		return studentUniqueNumber;
	}
	public void setStudentUniqueNumber(String studentUniqueNumber) {
		this.studentUniqueNumber = studentUniqueNumber;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public String getStudentMobile() {
		return studentMobile;
	}
	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public long getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(long receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getLastSchool() {
		return lastSchool;
	}
	public void setLastSchool(String lastSchool) {
		this.lastSchool = lastSchool;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getBplStatus() {
		return bplStatus;
	}
	public void setBplStatus(String bplStatus) {
		this.bplStatus = bplStatus;
	}
	public String getRteStatus() {
		return rteStatus;
	}
	public void setRteStatus(String rteStatus) {
		this.rteStatus = rteStatus;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	
	
	
     
	public String getFatherQualification() {
		return fatherQualification;
	}
	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public long getCasteId() {
		return casteId;
	}
	public void setCasteId(long casteId) {
		this.casteId = casteId;
	}
	public long getReligionId() {
		return religionId;
	}
	public void setReligionId(long religionId) {
		this.religionId = religionId;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public long getFeeCategoryId() {
		return feeCategoryId;
	}
	public void setFeeCategoryId(long feeCategoryId) {
		this.feeCategoryId = feeCategoryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public StudentVo getStudentVo() {
		return studentVo;
	}
	public void setStudentVo(StudentVo studentVo) {
		this.studentVo = studentVo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	 
	public String getLastClassStudied() {
		return lastClassStudied;
	}
	public void setLastClassStudied(String lastClassStudied) {
		this.lastClassStudied = lastClassStudied;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getLastSchoolName() {
		return lastSchoolName;
	}
	public void setLastSchoolName(String lastSchoolName) {
		this.lastSchoolName = lastSchoolName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherOccupation() {
		return fatherOccupation;
	}
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	public String getFatherDesignation() {
		return fatherDesignation;
	}
	public void setFatherDesignation(String fatherDesignation) {
		this.fatherDesignation = fatherDesignation;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherQualification() {
		return motherQualification;
	}
	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}
	public String getParentEmailId() {
		return parentEmailId;
	}
	public void setParentEmailId(String parentEmailId) {
		this.parentEmailId = parentEmailId;
	}
	 
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String gettCNumber() {
		return tCNumber;
	}
	public void settCNumber(String tCNumber) {
		this.tCNumber = tCNumber;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCollectedDoc() {
		return collectedDoc;
	}
	public void setCollectedDoc(String collectedDoc) {
		this.collectedDoc = collectedDoc;
	}
	
	
	public String getPersonFullName()
    {
        StringBuffer ret = new StringBuffer();
 	       
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
	
	
	public String getDateOfJoiningDateStr()
    {
    	if(!ObjectFunctions.isNullOrEmpty(getDateOfJoiningDate()))
    		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getDateOfJoiningDate()); 
    	return "";
    }
	
	
    public String getDateOfBirthStr()
    {
    	if(!ObjectFunctions.isNullOrEmpty(getDateOfBirthDate()))
    		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getDateOfBirthDate()); 
    	return "";
    }
    
    public int age()
    {
    	if(!ObjectFunctions.isNullOrEmpty(getDateOfBirthDate()))
    		return DateFunctions.calculateAge(getDateOfBirthDate());
    	return 0;
    }
    
    public String getTransferCertificateDateStr() {
    	if(!ObjectFunctions.isNullOrEmpty(getTransferCertificateDate()))
    		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getTransferCertificateDate()); 
    	return "";
	}
    
    public String getHostelModeStr() {
    	if(!ObjectFunctions.isNullOrEmpty(getHostelMode()))
    	{
    		if("D".equalsIgnoreCase(getHostelMode()))
    			return "Day Scholar";
    		else
    			return "Hostler";
    	}
    	return "";
	}
    public String getDateofAdmissionStr()
    {
    	if(!ObjectFunctions.isNullOrEmpty(getCreatedDate()))
    		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getCreatedDate()); 
    	return "";
    }
    
	public String getCastAndSubCastName() {
		
		StringBuffer ret = new StringBuffer();

		if (!StringFunctions.isNullOrEmpty(getCastName())) {
			ret.append(getCastName());
		}
		if (!StringFunctions.isNullOrEmpty(getSubCastName())) {
			ret.append(" - ");
			ret.append(getSubCastName());
		}

		return ret.toString().trim();
		
	}
	
	
	public String getStudentBankAccountDetails() {
		
		StringBuffer ret = new StringBuffer();

		if (!StringFunctions.isNullOrEmpty(getStudentOrParentBankAccountName())) {
			ret.append(getStudentOrParentBankAccountName());
		}
		if (!StringFunctions.isNullOrEmpty(getStudentOrParentBankAccountNumber())) {
			ret.append(" , ");
			ret.append(getStudentOrParentBankAccountNumber());
		}

		return ret.toString().trim();
		
	}
	
    
}
