package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.urt.persistence.model.base.PersistentObject;

/**
* Attendance entity. @author MyEclipse Persistence Tools
*/
@Entity
@Table(name = "hostelStudentDailyAttendance") 
public class HostelStudentDailyAttendance extends PersistentObject {


	private static final long serialVersionUID = 1L;
	private long studentId;
	private boolean present;
	private char leaveRequest;
	private String leaveType;
	private Date attendanceDate;
	private long academicYearId;
	private long custId;
	private long leaveCount;
 
	 
	@Column(name = "leavesCount", nullable = false ,columnDefinition="double default 0")
	public long getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(long leaveCount) {
		this.leaveCount = leaveCount;
	}

	/** default constructor */
	public HostelStudentDailyAttendance() {
	}

	/** minimal constructor */
	public HostelStudentDailyAttendance(long id, long studentId) {
		super.setId(id);
		this.studentId = studentId;
	}
 	/**
	 * @return the present
	 */
	@Column(name = "present", nullable = true, length = 1, columnDefinition = "char(1) default 'Y'")
	@Type(type = "yes_no")
	public boolean isPresent() {
		return present;
	}

	/**
	 * @param present
	 *            the present to set
	 */
	public void setPresent(boolean present) {
		this.present = present;
	}

	/**
	 * @return the leaveRequest
	 */
	public char getLeaveRequest() {
		return leaveRequest;
	}

	/**
	 * @param leaveRequest
	 *            the leaveRequest to set
	 */
	public void setLeaveRequest(char leaveRequest) {
		this.leaveRequest = leaveRequest;
	} 
	/**
	 * @return the attendanceDate
	 */
	public Date getAttendanceDate() {
		return attendanceDate;
	}

	/**
	 * @param attendanceDate
	 *            the attendanceDate to set
	 */
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
	@Column(name = "leaveType", columnDefinition = "varchar(255) default ' '")
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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
		result = prime * result + leaveRequest;
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
		
		if (null == obj || this.getClass() != obj.getClass()){
			return false;
		} else {
			HostelStudentDailyAttendance other = (HostelStudentDailyAttendance) obj;		
			if (studentId != other.studentId)
				return false;
			if (attendanceDate == null) {
				if (other.attendanceDate != null)
					return false;
			} else if (!attendanceDate.equals(other.attendanceDate))
				return false;
			if (leaveRequest != other.leaveRequest)
				return false;
			if (present != other.present)
				return false;
		}
			
		return true;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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
     
}