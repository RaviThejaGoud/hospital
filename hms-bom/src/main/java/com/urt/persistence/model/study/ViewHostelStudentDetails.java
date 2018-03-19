package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "vw_hostelStudentDetails")
public class ViewHostelStudentDetails implements Serializable,
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
	private long custId;
	private String username;
	private long academicYearId;
	private String status;
	private long studentId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;
	private String motherName;  
	protected String city;
	protected String state;
	protected String postalCode;
	protected String mobileNumber;
	protected String phoneNumber;
	protected String mothersMaidenName;
	protected String bloodGroup;
	protected Date dateOfBirth; 
	protected long rollNumber;
	protected String className;
	protected String parentId;
	private long roleId;
	private String roleName;
	private String registerNumber; 
	private String joinedThroughAdmissions;
	private long categoryId;	
	private String parentEmail; 
	private String section;
	private String roleDescription;;
	protected long classNameClassId;
	protected long classSectionId;
	protected boolean accountExpired;
	protected String description;
	protected String admissionNumber;
    protected String gender;    
    protected String fatherOccupation;
    protected String motherOccupation;
    protected long annualIncome;
    protected String streetName;
    protected String stateName;
	protected String academicYear; 
    protected String identification1;
    protected String identification2;
    protected String customerFullAddress;
    protected String parentFullAddress;
     private String sslcNumber;
	 private String tmrNumber;
	 private String classNameAndSection;
	 private String classJoined; 
	 private long roomId;
	 private String roomName;
	 private long floorId;
	 private String floorName;
	 private String roomWithFloorName;
	 private String hostelName;
	 private String buildingName;
	 private String hostelBuildingFloorName;
	 private String roomAndBedName;
	 private String hostelMode;
	 

	 
	public String getHostelMode() {
		return hostelMode;
	}

	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}

	public String getRoomAndBedName() {
		return roomAndBedName;
	}

	public void setRoomAndBedName(String roomAndBedName) {
		this.roomAndBedName = roomAndBedName;
	}

	public String getHostelBuildingFloorName() {
		return hostelBuildingFloorName;
	}

	public void setHostelBuildingFloorName(String hostelBuildingFloorName) {
		this.hostelBuildingFloorName = hostelBuildingFloorName;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public long getFloorId() {
		return floorId;
	}

	public void setFloorId(long floorId) {
		this.floorId = floorId;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getRoomWithFloorName() {
		return roomWithFloorName;
	}

	public void setRoomWithFloorName(String roomWithFloorName) {
		this.roomWithFloorName = roomWithFloorName;
	}

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
 
	public String getClassJoined() {
		return classJoined;
	}

	public void setClassJoined(String classJoined) {
		this.classJoined = classJoined;
	}
	 
	public String getJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}

	public void setJoinedThroughAdmissions(String joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
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
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
 

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
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
	 * @param annualIncome the annualIncome to set
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
	 * @param motherOccupation the motherOccupation to set
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
	 * @param fatherOccupation the fatherOccupation to set
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
 
	/**
	 * @param gender the gender to set
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
	 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name = "accountExpired", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
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
 
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
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

	public long getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}

	public ViewHostelStudentDetails() {
		super();
	}

	public ViewHostelStudentDetails(String firstName,String lastName,long classSectionId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.classSectionId = classSectionId;
	}
	
	public ViewHostelStudentDetails(long accountId, long custId,
			String username, long academicYearId, String status,
			long studentId, String firstName, String lastName, String middleName) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.username = username;
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
		if (!(o instanceof ViewHostelStudentDetails))
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
		ViewHostelStudentDetails myClass = (ViewHostelStudentDetails) object;
		if(ObjectFunctions.isNullOrEmpty(myClass))
			return 0;
		else if(StringFunctions.isNotNullOrEmpty(myClass.getRegisterNumber()) && StringFunctions.isNotNullOrEmpty(this.getRegisterNumber())){
			return this.getRegisterNumber().compareToIgnoreCase(myClass.getRegisterNumber());
		}else
			return this.getPersonFullName().compareToIgnoreCase(myClass.getPersonFullName());	
	}
 

	public long getAccountId() {
		return accountId;
	}

	public long getCustId() {
		return custId;
	}

	public String getUsername() {
		return username;
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
		return  getPersonFirstLastNameOnly()+" ("+getUsername()+")";
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
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
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
	public static String getStudyImageNotFoundFile() {
		// return "/images/common/studyImageNotAvailable.jpg";
		return "/images/common/photo_notAvailable.jpg";
	}
  
 
	@Transient
	public String getDateOfBirthStr() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getDateOfBirth());
	} 
	 @Transient
	public String getAccountIdAndClassSectionId() {
		return  getAccountId()+"_"+getClassSectionId();
	}
	@Transient
	public String getAccountIdClassSectionIdAcademicYearIdAndCustId() {
		return  getAccountId()+"_"+getClassSectionId()+"_"+getAcademicYearId()+"_"+getCustId();
	}
	
	@Transient
	public String getTodayDate() {
		//return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, new Date());
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, new Date());
	}
	
	  
	/**
	 * @return the academicYear
	 */
	public String getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
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
	 
	@Transient
    public String getCustomerReportAttachments() {
        String imagePath=ServletActionContext.getServletContext().getRealPath("userFilessvms/svmsColorReport.png");
        return imagePath;
    }
	 
	
	 
 
}
