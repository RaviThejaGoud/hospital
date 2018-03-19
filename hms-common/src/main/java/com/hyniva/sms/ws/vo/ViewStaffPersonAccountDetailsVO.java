package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;

public class ViewStaffPersonAccountDetailsVO {

	protected long staffId;
	protected long accountId;
	protected String staffType;
	private String qualification1;
	private String qualification2;
	private String dateofJoining;
	private String username;
	private String roleName;
	private String firstName;
	private String lastName;
	private String gender;
	private long imageId;
	private String imageUrl;
	protected String mobileNumber;
	protected String phoneNumber;
	protected String dateOfBirth;
	private int bioMetricId;
	private String email;
	private String status;
	//protected SchoolShiftInfoVO schoolShiftInfoVO;
	protected List<ClassTeacherVO> classTeacherVOs;
	//protected AddressVO addressVO;
	protected LeaveVO leaveVO;
	private String staffUniqueNumber;
	private String designation;
	private String bankName;
	private String bankAccountNumber;
	private String bankBranchName;
	protected Double salary;
	private String ifscCode;
	private String pfNo;
	private String esiNo;
	private String panNumber;
	private String staffGrade;
	private String  salaryPaymentMode;
	private String  staffLocation;
	private long academicYearId;
	private long custId;
	public String addressLine1="";
	public String addressLine2="";
	public String city="";
	public String state="";
	public String postalCode="";
	private String fatherName;
	private String fatherContactNumber;


	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStaffGrade() {
		return staffGrade;
	}

	public void setStaffGrade(String staffGrade) {
		this.staffGrade = staffGrade;
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

	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public String getEsiNo() {
		return esiNo;
	}

	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getStaffUniqueNumber() {
		return staffUniqueNumber;
	}

	public void setStaffUniqueNumber(String staffUniqueNumber) {
		this.staffUniqueNumber = staffUniqueNumber;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	public String getQualification2() {
		return qualification2;
	}

	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	public String getDateofJoining() {
		return dateofJoining;
	}

	public void setDateofJoining(String dateofJoining) {
		this.dateofJoining = dateofJoining;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getBioMetricId() {
		return bioMetricId;
	}

	public void setBioMetricId(int bioMetricId) {
		this.bioMetricId = bioMetricId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public SchoolShiftInfoVO getSchoolShiftInfoVO() {
		return schoolShiftInfoVO;
	}

	public void setSchoolShiftInfoVO(SchoolShiftInfoVO schoolShiftInfoVO) {
		this.schoolShiftInfoVO = schoolShiftInfoVO;
	}*/

	public List<ClassTeacherVO> getClassTeacherVOs() {
		if (ObjectFunctions.isNullOrEmpty(this.classTeacherVOs)) {
			this.classTeacherVOs = new ArrayList<ClassTeacherVO>();
		}
		return classTeacherVOs;
	}

	public void setClassTeacherVOs(List<ClassTeacherVO> classTeacherVOs) {
		this.classTeacherVOs = classTeacherVOs;
	}

	/*public AddressVO getAddressVO() {
		return addressVO;
	}

	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}*/

	public LeaveVO getLeaveVO() {
		return leaveVO;
	}

	public void setLeaveVO(LeaveVO leaveVO) {
		this.leaveVO = leaveVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

}
