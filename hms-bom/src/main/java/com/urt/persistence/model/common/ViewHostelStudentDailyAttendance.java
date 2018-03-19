package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;

@Entity
@Table(name = "vw_hostelStudentDailyAttendance") 
public class ViewHostelStudentDailyAttendance  implements java.io.Serializable {
	
	private String studentname;
	private long custId;
	private long classSectionId;
	private Date attendanceDate;
	private boolean present;
	private long rollNumber;
	private long studentId;
	private long accountId;
	private long month;
	private String monthName;
	private long academicYearId;
	private long studentAttendanceId;
	private String leaveRequest;
	private long classId;
	protected String className;
	protected String section;
	protected String admissionNumber;
	protected String mobileNumber;
	private String status;
	private String hostelMode;
	
	
	
	
	public String getLeaveRequest() {
		return leaveRequest;
	}
	public void setLeaveRequest(String leaveRequest) {
		this.leaveRequest = leaveRequest;
	}
	public String getHostelMode() {
		return hostelMode;
	}
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
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
	public long getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(long rollNumber) {
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
		if(this == obj){
			return true;
		} else if (null == obj || getClass() != obj.getClass()) {
			return false;
		} else {
			ViewHostelStudentDailyAttendance other = (ViewHostelStudentDailyAttendance) obj;
			if (studentId != other.studentId)
				return false;
			if (attendanceDate == null) {
				if (other.attendanceDate != null)
					return false;
			} else if (!attendanceDate.equals(other.attendanceDate))
				return false;
			
			if (present != other.present)
				return false;
		}
		return true;
	}
	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName().trim()+"-"+getSection();
		}
		return getClassName();
	}
	@Transient
	public String getPresentStr() {
		if (isPresent()) {
			return "Y";
		}
		return "N";
	}

}
