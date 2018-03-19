package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;

@Entity
@Table(name = "vw_StudentDailyAttendance") 
public class VWStudentDailyAttendance  implements java.io.Serializable {
	
	private String studentname;
	private long custId;
	private long classSectionId;
	private Date attendanceDate;
	private boolean present;
	private String rollNumber;
	private long studentId;
	private long accountId;
	private long month;
	private String monthName;
	private long academicYearId;
	private long studentAttendanceId;
	private String studDiscontinueDesc;
	private String status;
	private Character leaveRequest;
	private long classId;
	private String admissionNumber;
	private Date blackedOrSuspendFromDate;
    private Date blackedOrSuspendToDate;
    private String mobileNumber;
    private boolean  afternoonSession;
    private String leaveSessionType;
    private Date inTime;
    private Date outTime;
	
	public String getLeaveSessionType() {
		return leaveSessionType;
	}
	public void setLeaveSessionType(String leaveSessionType) {
		this.leaveSessionType = leaveSessionType;
	}
    
   /* private String year;
    
    
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}*/
    public boolean isAfternoonSession() {
		return afternoonSession;
	}

	public void setAfternoonSession(boolean afternoonSession) {
		this.afternoonSession = afternoonSession;
	}
    @Transient
    public String getBlackedOrSuspendFromDateStr() {
	return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, getBlackedOrSuspendFromDate());

    }

    @Transient
    public String getBlackedOrSuspendToDateStr() {
	return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, getBlackedOrSuspendToDate());

    }
	
    @Transient
	public Date getBlackedOrSuspendFromDate() {
		return blackedOrSuspendFromDate;
	}
	public void setBlackedOrSuspendFromDate(Date blackedOrSuspendFromDate) {
		this.blackedOrSuspendFromDate = blackedOrSuspendFromDate;
	}
	@Transient
	public Date getBlackedOrSuspendToDate() {
		return blackedOrSuspendToDate;
	}
	public void setBlackedOrSuspendToDate(Date blackedOrSuspendToDate) {
		this.blackedOrSuspendToDate = blackedOrSuspendToDate;
	}
	/**
	 * @return the classId
	 */
	@Transient
	public long getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}
	/**
	 * @return the leaveRequest
	 */
	@Transient
	public Character getLeaveRequest() {
		return leaveRequest;
	}
	/**
	 * @param leaveRequest the leaveRequest to set
	 */
	public void setLeaveRequest(Character leaveRequest) {
		this.leaveRequest = leaveRequest;
	}
	/**
	 * @return the status
	 */
	@Transient
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the studDiscontinueDesc
	 */
	@Transient
	public String getStudDiscontinueDesc() {
		return studDiscontinueDesc;
	}
	/**
	 * @param studDiscontinueDesc the studDiscontinueDesc to set
	 */
	public void setStudDiscontinueDesc(String studDiscontinueDesc) {
		this.studDiscontinueDesc = studDiscontinueDesc;
	}
	@Id
	@Column(name = "studentAttendanceId", nullable = false)
	public long getStudentAttendanceId() {
		return studentAttendanceId;
	}
	public void setStudentAttendanceId(long studentAttendanceId) {
		this.studentAttendanceId = studentAttendanceId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public long getMonth() {
		return month;
	}
	public void setMonth(long month) {
		this.month = month;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
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
	
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
  
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 4572;
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		result = prime * result
				+ ((attendanceDate == null) ? 0 : attendanceDate.hashCode());
		result = (int) (prime * result + classSectionId);
		result = prime * result + (present ? 1231 : 1237);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VWStudentDailyAttendance other = (VWStudentDailyAttendance) obj;
		if (studentId != other.studentId)
			return false;
		if (attendanceDate == null) {
			if (other.attendanceDate != null)
				return false;
		} else if (!attendanceDate.equals(other.attendanceDate))
			return false;
		
		if (present != other.present)
			return false;
		
		return true;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the inTime
	 */
	public Date getInTime() {
		return inTime;
	}
	/**
	 * @param inTime the inTime to set
	 */
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	/**
	 * @return the outTime
	 */
	public Date getOutTime() {
		return outTime;
	}
	/**
	 * @param outTime the outTime to set
	 */
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
}
