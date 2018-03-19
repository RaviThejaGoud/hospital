package com.urt.persistence.model.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.annotations.JSON;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.string.StringFunctions;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewUserRoles.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Feb 9, 2011 Narahari Created /
 ********************************************************************/

@Entity
@Table(name = "vw_allUsers")
public class ViewAllUsers implements Serializable, Cloneable, Comparable {
	
	private static final Log log = LogFactory.getLog(ViewAllUsers.class);

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private long accountId;
	private String username;
	private long roleId;
	private String roleName;
	private String roleDescription;
	private String firstName;
	private String lastName;
	private String imageName;
	private String thumbNail;
	private String imagePath;
	private String imageId;
	private long custId;
	private String parentEmail;
	private String staffEmail;
	//protected Date createdDate;
	//private String createdBy;
	private String fatherName;
	private String mobileNumber;
	private long parentId;
	protected boolean accountExpired;
    protected Date lastAccessDate;
    protected Date lastUpdatedDate;
    private String admissionNumber;	
    protected String password;   
    protected boolean accountEnabled;
    protected int bioMetricId;
    private String gender="M";
    protected int personId;
    protected int addressId;
    protected String addressLine1;
    protected String streetName;
    protected String stateCode;
    protected int stateId;
    private String postalCode;
    private String city;
    private String studentMobile;
    private Date dateOfBirth; 
    private String studentEmail;
    private String bloodGroup;
    private String staffDesignation;
    private String classAndSection;
    protected List feesList;
    protected List attendanceList;
    
   // private boolean activeStudent;
	private String status=Constants.YES_STRING;

    protected List studentMarksList;
    protected String grade;
    protected String description;
    protected String profileImage;
	private String stateName;
	protected List classAssignmentsList;

	/**
	 * @return the staffDesignation
	 */
	public String getStaffDesignation() {
		return staffDesignation;
	}
	/**
	 * @param staffDesignation the staffDesignation to set
	 */
	public void setStaffDesignation(String staffDesignation) {
		this.staffDesignation = staffDesignation;
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
	 * @return the city
	 */
	@Column(name = "city", nullable = true, length = 64)
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the addressId
	 */
	public int getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
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
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	@Column(name = "gender", nullable = true, length = 1)
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
	 * @return the bioMetricId
	 */
	
	public int getBioMetricId() {
		return bioMetricId;
	}
	/**
	 * @param bioMetricId the bioMetricId to set
	 */
	public void setBioMetricId(int bioMetricId) {
		this.bioMetricId = bioMetricId;
	}
    public boolean isAccountEnabled() {
        return accountEnabled;
    }
    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }
    @Column(name = "password", nullable = false, length = 128)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return the lastAccessDate
	 */
	@JSON(serialize=false)
	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	/**
	 * @param lastAccessDate the lastAccessDate to set
	 */
	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	
	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
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

	@Id
	@Column(name = "accountId", unique = false, nullable = false, updatable = false)
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Column(name = "roleId", nullable = true, length = 10, unique = false)
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "username", nullable = true, length = 250)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "firstName", nullable = true, length = 250)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", nullable = true, length = 250)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ViewAllUsers() {
		super();
	}

	public ViewAllUsers(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewAllUsers))
			return false;

		final ViewAllUsers viewRoles = (ViewAllUsers) o;

		if (username != null ? !username.equals(viewRoles.username)
				: viewRoles.username != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = (username != null ? username.hashCode() : 0);

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
		buffer.append(" User Name: ").append(getUsername());
		return buffer.toString();
	}

	@Override
	public int compareTo(Object object) {
		ViewAllUsers myClass = (ViewAllUsers) object;
		return new CompareToBuilder().append(this.username, myClass.username)
				.toComparison();
	}

	// UserImage methods

	@Transient
	public static String getImageNotFoundFile() {
		return "/images/common/photo_notAvailable.jpg";
	}

	@Transient
	public static String getStudyImageNotFoundFile() {
		return "/images/common/studyImageNotAvailable.jpg";
	}

	@Transient
	public String getOriginalAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			return getImagePath().concat(getImageName());
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			log.debug(getImagePath().concat(getThumbNail()));
			return getImagePath().concat(getThumbNail());
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getHrefOriginalAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			return getImagePath().substring(1).concat(getImageName());
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getHrefAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			return getImagePath().substring(1).concat(getThumbNail());
		}
		return UserImage.getStudyImageNotFoundFile();
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
		if (getRoleName().equals(Constants.SCHOOL_TEACHER)  ||
			 getRoleName().equals(Constants.SCHOOL_PRINCIPAL) ||
			 getRoleName().equals(Constants.SCHOOL_STUDENT) ||
			 getRoleName().equals(Constants.SCHOOL_HOD)){	
				ret.append(" (");
	            ret.append(getRoleDescription());       
	            ret.append(")");
	    }	
	
		return ret.toString().trim();
	}

	@Transient
	public String getUserRoleInfo() {
		if (Constants.SCHOOL_STUDENT.equalsIgnoreCase(getRoleName())) {
			return "(Stu)";
		} else if (Constants.SCHOOL_PARENT.equalsIgnoreCase(getRoleName())) {
			return "(Par)";
		} else {
			return "(Stf)";
		}
	}

	/**
	 * @return the parentId
	 */
	public long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	} 
	
	@Transient
    public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	@Transient
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Transient
    public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Transient
    public List getStudentMarksList() {
		return studentMarksList;
	}
	
	public void setStudentMarksList(List studentMarksList) {
		this.studentMarksList = studentMarksList;
	}
	
	@Transient
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
	
	/*@Transient
    @Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isActiveStudent() {
		return activeStudent;
	}
	public void setActiveStudent(boolean activeStudent) {
		this.activeStudent = activeStudent;
	}*/
	
    @Transient
    public List getFeesList() {
		return feesList;
	}
	public void setFeesList(List feesList) {
		this.feesList = feesList;
	}
	@Transient
	public List getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(List attendanceList) {
		this.attendanceList = attendanceList;
	}
    
	@Transient
    public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	@Transient
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Transient
	public List getClassAssignmentsList() {
		return classAssignmentsList;
	}
	public void setClassAssignmentsList(List classAssignmentsList) {
		this.classAssignmentsList = classAssignmentsList;
	}
	@Transient
	public String getFullPersonName() {
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
}