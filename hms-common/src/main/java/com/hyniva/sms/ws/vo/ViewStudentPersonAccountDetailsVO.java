package com.hyniva.sms.ws.vo;

public class ViewStudentPersonAccountDetailsVO{
	
	private long studentId;
	private long accountId;
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	protected String gender;
	protected long classNameClassId;
	protected long classSectionId;
	protected String admissionNumber;
	protected String rollNumber;
	protected long categoryId;
	protected String mobileNumber;
	protected String phoneNumber;
	private String parentEmail;
	protected String bloodGroup;
	protected String dateOfBirth;
	private double committedFee;
	private String classSectionName;
	private String status;
	private String description;
	private long boardingPointId;
	private String hostelMode;
	private String transportMode;
	private String aadharNumber;
	private long religionId;
	private long subCastId;
	private String nationality;
	private String registerNumber;
	private String motherToung;
	private String classJoined;
	private String relievingDate;
	private boolean phId;
	private long studentImageId;	
	private String studentMobile;
	private AddressVO addressVO;
	private String ImageUrl;
	private String stsNumber;
	
	
	
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getClassNameClassId() {
		return classNameClassId;
	}
	public void setClassNameClassId(long classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public double getCommittedFee() {
		return committedFee;
	}
	public void setCommittedFee(double committedFee) {
		this.committedFee = committedFee;
	}
	public String getClassSectionName() {
		return classSectionName;
	}
	public void setClassSectionName(String classSectionName) {
		this.classSectionName = classSectionName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getBoardingPointId() {
		return boardingPointId;
	}
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	public String getHostelMode() {
		return hostelMode;
	}
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}
	 
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public long getReligionId() {
		return religionId;
	}
	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}
	public long getSubCastId() {
		return subCastId;
	}
	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}
	 
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
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
	public boolean isPhId() {
		return phId;
	}
	public void setPhId(boolean phId) {
		this.phId = phId;
	}
	public long getStudentImageId() {
		return studentImageId;
	}
	public void setStudentImageId(long studentImageId) {
		this.studentImageId = studentImageId;
	}
	public String getStudentMobile() {
		return studentMobile;
	}
	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRelievingDate() {
		return relievingDate;
	}
	public void setRelievingDate(String relievingDate) {
		this.relievingDate = relievingDate;
	}
	public String getStsNumber() {
		return stsNumber;
	}
	public void setStsNumber(String stsNumber) {
		this.stsNumber = stsNumber;
	}
	
}
