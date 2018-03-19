package com.urt.persistence.model.common;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VWStudentAttendanceId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class VWStudentAttendanceId implements java.io.Serializable {

	// Fields

	private Long accountId;
	private Long custId;
	private Long studentId;	
	private Long academicYearId;
	private Long classId;
	private Long classSectionId;
	private String username;
	private String className;
	private String section;
	private String parentId;
	private String status;
	private String rollNumber;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;
	private Timestamp dateOfBirth;
	private String mobileNumber;
	private String phoneNumber;
	private String parentEmail;
	private Long attendanceId;
	private Timestamp attendanceDate;
	private String category;
	private String leaveRequest;
	private String month;
	private Long monthNum;
	private String present;
	private Long year;

	// Constructors

	/** default constructor */
	public VWStudentAttendanceId() {
	}

	/** minimal constructor */
	public VWStudentAttendanceId(Long accountId, Long custId, Long studentId,
			Long classSectionId, String username, Long attendanceId,
			String category, String leaveRequest, Long monthNum,
			String present, Long year) {
		this.accountId = accountId;
		this.custId = custId;
		this.studentId = studentId;
		this.classSectionId = classSectionId;
		this.username = username;
		this.attendanceId = attendanceId;
		this.category = category;
		this.leaveRequest = leaveRequest;
		this.monthNum = monthNum;
		this.present = present;
		this.year = year;
	}

	/** full constructor */
	public VWStudentAttendanceId(Long accountId, Long custId, Long studentId,
			Long academicYearId, Long classId, Long classSectionId,
			String username, String className, String section, String parentId,
			String status, String rollNumber, String firstName,
			String lastName, String middleName, String fatherName,
			Timestamp dateOfBirth, String mobileNumber, String phoneNumber,
			String parentEmail, Long attendanceId, Timestamp attendanceDate,
			String category, String leaveRequest, String month, Long monthNum,
			String present, Long year) {
		this.accountId = accountId;
		this.custId = custId;
		this.studentId = studentId;
		this.academicYearId = academicYearId;
		this.classId = classId;
		this.classSectionId = classSectionId;
		this.username = username;
		this.className = className;
		this.section = section;
		this.parentId = parentId;
		this.status = status;
		this.rollNumber = rollNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.fatherName = fatherName;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.parentEmail = parentEmail;
		this.attendanceId = attendanceId;
		this.attendanceDate = attendanceDate;
		this.category = category;
		this.leaveRequest = leaveRequest;
		this.month = month;
		this.monthNum = monthNum;
		this.present = present;
		this.year = year;
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

	@Column(name = "studentId", nullable = false)
	public Long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Column(name = "academicYearId")
	public Long getAcademicYearId() {
		return this.academicYearId;
	}

	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Column(name = "classId")
	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	@Column(name = "classSectionId", nullable = false)
	public Long getClassSectionId() {
		return this.classSectionId;
	}

	public void setClassSectionId(Long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Column(name = "username", nullable = false, length = 128)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "className")
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "section")
	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Column(name = "parentId")
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "rollNumber")
	public String getRollNumber() {
		return this.rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
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

	@Column(name = "fatherName", length = 128)
	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	@Column(name = "dateOfBirth", length = 19)
	public Timestamp getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
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

	@Column(name = "parentEmail", length = 128)
	public String getParentEmail() {
		return this.parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	@Column(name = "attendanceId", nullable = false)
	public Long getAttendanceId() {
		return this.attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	@Column(name = "attendanceDate", length = 19)
	public Timestamp getAttendanceDate() {
		return this.attendanceDate;
	}

	public void setAttendanceDate(Timestamp attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	@Column(name = "category", nullable = false, length = 1)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "leaveRequest", nullable = false, length = 1)
	public String getLeaveRequest() {
		return this.leaveRequest;
	}

	public void setLeaveRequest(String leaveRequest) {
		this.leaveRequest = leaveRequest;
	}

	@Column(name = "month")
	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "monthNum", nullable = false)
	public Long getMonthNum() {
		return this.monthNum;
	}

	public void setMonthNum(Long monthNum) {
		this.monthNum = monthNum;
	}

	@Column(name = "present", nullable = false, length = 1)
	public String getPresent() {
		return this.present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	@Column(name = "year", nullable = false)
	public Long getYear() {
		return this.year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof VWStudentAttendanceId))
			return false;
		VWStudentAttendanceId castOther = (VWStudentAttendanceId) other;

		return this.getAccountId() == castOther.getAccountId() || (this
				.getAccountId() != null
				&& castOther.getAccountId() != null && this.getAccountId()
				.equals(castOther.getAccountId()))
				&& this.getCustId() == castOther.getCustId() || (this
						.getCustId() != null
						&& castOther.getCustId() != null && this.getCustId()
						.equals(castOther.getCustId()))
				&& this.getStudentId() == castOther.getStudentId() || (this
						.getStudentId() != null
						&& castOther.getStudentId() != null && this
						.getStudentId().equals(castOther.getStudentId()))
				&& this.getAcademicYearId() == castOther.getAcademicYearId() || (this
						.getAcademicYearId() != null
						&& castOther.getAcademicYearId() != null && this
						.getAcademicYearId().equals(
								castOther.getAcademicYearId()))
				&& this.getClassId() == castOther.getClassId() || (this
						.getClassId() != null
						&& castOther.getClassId() != null && this.getClassId()
						.equals(castOther.getClassId()))
				&& this.getClassSectionId() == castOther.getClassSectionId() || (this
						.getClassSectionId() != null
						&& castOther.getClassSectionId() != null && this
						.getClassSectionId().equals(
								castOther.getClassSectionId()))
				&& this.getUsername() == castOther.getUsername() || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername()))
				&& this.getClassName() == castOther.getClassName() || (this
						.getClassName() != null
						&& castOther.getClassName() != null && this
						.getClassName().equals(castOther.getClassName()))
				&& this.getSection() == castOther.getSection() || (this
						.getSection() != null
						&& castOther.getSection() != null && this.getSection()
						.equals(castOther.getSection()))
				&& this.getParentId() == castOther.getParentId() || (this
						.getParentId() != null
						&& castOther.getParentId() != null && this
						.getParentId().equals(castOther.getParentId()))
				&& this.getStatus() == castOther.getStatus() || (this
						.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus()))
				&& this.getRollNumber() == castOther.getRollNumber() || (this
						.getRollNumber() != null
						&& castOther.getRollNumber() != null && this
						.getRollNumber().equals(castOther.getRollNumber()))
				&& this.getFirstName() == castOther.getFirstName() || (this
						.getFirstName() != null
						&& castOther.getFirstName() != null && this
						.getFirstName().equals(castOther.getFirstName()))
				&& this.getLastName() == castOther.getLastName() || (this
						.getLastName() != null
						&& castOther.getLastName() != null && this
						.getLastName().equals(castOther.getLastName()))
				&& this.getMiddleName() == castOther.getMiddleName() || (this
						.getMiddleName() != null
						&& castOther.getMiddleName() != null && this
						.getMiddleName().equals(castOther.getMiddleName()))
				&& this.getFatherName() == castOther.getFatherName() || (this
						.getFatherName() != null
						&& castOther.getFatherName() != null && this
						.getFatherName().equals(castOther.getFatherName()))
				&& this.getDateOfBirth() == castOther.getDateOfBirth() || (this
						.getDateOfBirth() != null
						&& castOther.getDateOfBirth() != null && this
						.getDateOfBirth().equals(castOther.getDateOfBirth()))
				&& this.getMobileNumber() == castOther.getMobileNumber() || (this
						.getMobileNumber() != null
						&& castOther.getMobileNumber() != null && this
						.getMobileNumber().equals(castOther.getMobileNumber()))
				&& this.getPhoneNumber() == castOther.getPhoneNumber() || (this
						.getPhoneNumber() != null
						&& castOther.getPhoneNumber() != null && this
						.getPhoneNumber().equals(castOther.getPhoneNumber()))
				&& this.getParentEmail() == castOther.getParentEmail() || (this
						.getParentEmail() != null
						&& castOther.getParentEmail() != null && this
						.getParentEmail().equals(castOther.getParentEmail()))
				&& this.getAttendanceId() == castOther.getAttendanceId() || (this
						.getAttendanceId() != null
						&& castOther.getAttendanceId() != null && this
						.getAttendanceId().equals(castOther.getAttendanceId()))
				&& this.getAttendanceDate() == castOther.getAttendanceDate() || (this
						.getAttendanceDate() != null
						&& castOther.getAttendanceDate() != null && this
						.getAttendanceDate().equals(
								castOther.getAttendanceDate()))
				&& this.getCategory() == castOther.getCategory() || (this
						.getCategory() != null
						&& castOther.getCategory() != null && this
						.getCategory().equals(castOther.getCategory()))
				&& this.getLeaveRequest() == castOther.getLeaveRequest() || (this
						.getLeaveRequest() != null
						&& castOther.getLeaveRequest() != null && this
						.getLeaveRequest().equals(castOther.getLeaveRequest()))
				&& this.getMonth() == castOther.getMonth() || (this
						.getMonth() != null
						&& castOther.getMonth() != null && this.getMonth()
						.equals(castOther.getMonth()))
				&& this.getMonthNum() == castOther.getMonthNum() || (this
						.getMonthNum() != null
						&& castOther.getMonthNum() != null && this
						.getMonthNum().equals(castOther.getMonthNum()))
				&& this.getPresent() == castOther.getPresent() || (this
						.getPresent() != null
						&& castOther.getPresent() != null && this.getPresent()
						.equals(castOther.getPresent()))
				&& this.getYear() == castOther.getYear() || this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getCustId() == null ? 0 : this.getCustId().hashCode());
		result = 37 * result
				+ (getStudentId() == null ? 0 : this.getStudentId().hashCode());
		result = 37
				* result
				+ (getAcademicYearId() == null ? 0 : this.getAcademicYearId()
						.hashCode());
		result = 37 * result
				+ (getClassId() == null ? 0 : this.getClassId().hashCode());
		result = 37
				* result
				+ (getClassSectionId() == null ? 0 : this.getClassSectionId()
						.hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getClassName() == null ? 0 : this.getClassName().hashCode());
		result = 37 * result
				+ (getSection() == null ? 0 : this.getSection().hashCode());
		result = 37 * result
				+ (getParentId() == null ? 0 : this.getParentId().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getRollNumber() == null ? 0 : this.getRollNumber()
						.hashCode());
		result = 37 * result
				+ (getFirstName() == null ? 0 : this.getFirstName().hashCode());
		result = 37 * result
				+ (getLastName() == null ? 0 : this.getLastName().hashCode());
		result = 37
				* result
				+ (getMiddleName() == null ? 0 : this.getMiddleName()
						.hashCode());
		result = 37
				* result
				+ (getFatherName() == null ? 0 : this.getFatherName()
						.hashCode());
		result = 37
				* result
				+ (getDateOfBirth() == null ? 0 : this.getDateOfBirth()
						.hashCode());
		result = 37
				* result
				+ (getMobileNumber() == null ? 0 : this.getMobileNumber()
						.hashCode());
		result = 37
				* result
				+ (getPhoneNumber() == null ? 0 : this.getPhoneNumber()
						.hashCode());
		result = 37
				* result
				+ (getParentEmail() == null ? 0 : this.getParentEmail()
						.hashCode());
		result = 37
				* result
				+ (getAttendanceId() == null ? 0 : this.getAttendanceId()
						.hashCode());
		result = 37
				* result
				+ (getAttendanceDate() == null ? 0 : this.getAttendanceDate()
						.hashCode());
		result = 37 * result
				+ (getCategory() == null ? 0 : this.getCategory().hashCode());
		result = 37
				* result
				+ (getLeaveRequest() == null ? 0 : this.getLeaveRequest()
						.hashCode());
		result = 37 * result
				+ (getMonth() == null ? 0 : this.getMonth().hashCode());
		result = 37 * result
				+ (getMonthNum() == null ? 0 : this.getMonthNum().hashCode());
		result = 37 * result
				+ (getPresent() == null ? 0 : this.getPresent().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		return result;
	}

}