package com.urt.persistence.model.common;

// default package

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * VWStaffAttendanceId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class VWStaffAttendanceId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private Long custId;
	private Long staffId;
	private Long academicYearId;
	private String username;
	private String status;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private String mobileNumber;
	private String phoneNumber;
	private Long attendanceId;
	private Date attendanceDate;
	private boolean category;
	private char leaveRequest;
	private String month;
	private int monthNum;
	private boolean present;
	private int year;
	 protected String anyString;
	 protected String leaveType;

	// Constructors

	/** default constructor */
	public VWStaffAttendanceId() {
	}

	/** minimal constructor */
	public VWStaffAttendanceId(Long accountId, Long custId, Long staffId,
			String username, Long attendanceId,
			boolean category, char leaveRequest, int monthNum,
			boolean present, int year) {
		this.accountId = accountId;
		this.custId = custId;
		this.staffId = staffId;
		this.username = username;
		this.attendanceId = attendanceId;
		this.category = category;
		this.leaveRequest = leaveRequest;
		this.monthNum = monthNum;
		this.present = present;
		this.year = year;
	}

	/** full constructor */
	public VWStaffAttendanceId(Long accountId, Long custId, Long staffId,
			Long academicYearId,
			String username,
			String status,  String firstName,
			String lastName, String middleName, 
			Date dateOfBirth, String mobileNumber, String phoneNumber,
			Long attendanceId, Date attendanceDate,
			boolean category, char leaveRequest, String month, int monthNum,
			boolean present, int year,String leaveType) {
		this.accountId = accountId;
		this.custId = custId;
		this.staffId = staffId;
		this.academicYearId = academicYearId;
		this.username = username;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.attendanceId = attendanceId;
		this.attendanceDate = attendanceDate;
		this.category = category;
		this.leaveRequest = leaveRequest;
		this.month = month;
		this.monthNum = monthNum;
		this.present = present;
		this.year = year;
		this.leaveType=leaveType;
	}

	// Property accessors
	@Column(name = "accountId", nullable = false)
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "custId", nullable = false)
	public Long getCustId() {
		return this.custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	@Column(name = "staffId", nullable = false)
	public Long getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	@Column(name = "academicYearId")
	public Long getAcademicYearId() {
		return this.academicYearId;
	}

	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Column(name = "username", nullable = false, length = 128)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "firstName", length = 128)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", length = 128)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "middleName", length = 64)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "dateOfBirth", length = 19)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "mobileNumber", length = 50)
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "phoneNumber", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Id
	@Column(name = "attendanceId", nullable = false)
	public Long getAttendanceId() {
		return this.attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	@Column(name = "attendanceDate", length = 19)
	public Date getAttendanceDate() {
		return this.attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	@Column(name = "month")
	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "monthNum", nullable = false)
	public int getMonthNum() {
		return this.monthNum;
	}

	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}


	@Column(name = "year", nullable = false)
	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the present
	 */
	@Column(name = "present", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isPresent() {
		return present;
	}

	/**
	 * @param present the present to set
	 */
	public void setPresent(boolean present) {
		this.present = present;
	}

	/**
	 * @return the category
	 */
	@Column(name = "category", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(boolean category) {
		this.category = category;
	}

	/**
	 * @return the leaveRequest
	 */
	public char getLeaveRequest() {
		return leaveRequest;
	}

	/**
	 * @param leaveRequest the leaveRequest to set
	 */
	public void setLeaveRequest(char leaveRequest) {
		this.leaveRequest = leaveRequest;
	}

	@Column(name = "leaveType", nullable = false, columnDefinition = "varchar(255) default ''")
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((academicYearId == null) ? 0 : academicYearId.hashCode());
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result
				+ ((attendanceDate == null) ? 0 : attendanceDate.hashCode());
		result = prime * result
				+ ((attendanceId == null) ? 0 : attendanceId.hashCode());
		result = prime * result + (category ? 1231 : 1237);
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + leaveRequest;
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + monthNum;
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + (present ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((staffId == null) ? 0 : staffId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + year;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VWStaffAttendanceId other = (VWStaffAttendanceId) obj;
		if (academicYearId == null) {
			if (other.academicYearId != null)
				return false;
		} else if (!academicYearId.equals(other.academicYearId))
			return false;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (attendanceDate == null) {
			if (other.attendanceDate != null)
				return false;
		} else if (!attendanceDate.equals(other.attendanceDate))
			return false;
		if (attendanceId == null) {
			if (other.attendanceId != null)
				return false;
		} else if (!attendanceId.equals(other.attendanceId))
			return false;
		if (category != other.category)
			return false;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (leaveRequest != other.leaveRequest)
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (monthNum != other.monthNum)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (present != other.present)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VWStudentAttendance [academicYearId=").append(
				academicYearId).append(", accountId=").append(accountId)
				.append(", attendanceDate=").append(attendanceDate).append(
						", attendanceId=").append(attendanceId).append(
						", category=").append(category).append(", classId=")
				.append(", className=")
				.append(", classSectionId=").append(
						", custId=").append(custId).append(", dateOfBirth=")
				.append(dateOfBirth).append(", fatherName=")
				.append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", leaveRequest=")
				.append(leaveRequest).append(", middleName=")
				.append(middleName).append(", mobileNumber=").append(
						mobileNumber).append(", month=").append(month).append(
						", monthNum=").append(monthNum)
				.append(", parentEmail=").append(
						", parentId=")
				.append(", phoneNumber=").append(phoneNumber).append(
						", present=").append(present).append(", rollNumber=")
				.append(", status=").append(status).append(", staffId=")
				.append(staffId).append(", username=").append(username)
				.append(", year=").append(year).append("]");
		return builder.toString();
	}
	
	@Transient
	public void setAnyString(String anyString) {
		this.anyString = anyString;
	}

}