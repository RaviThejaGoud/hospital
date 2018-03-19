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
@Table(name = "staffDailyAttendance") 
public class StaffDailyAttendance extends PersistentObject {


	private static final long serialVersionUID = 1L;
	private long accountId;
	private boolean present;
	private char leaveRequest;
	private String leaveType;
	private Date attendanceDate;
	private String inTime;
	private String outTime;
	private String workingHours;
	private long academicYearId;
	private long custId;
	private String staffLateTime;
	private boolean afternoonSession;
	private String leaveSessionType;

	
	public String getLeaveSessionType() {
		return leaveSessionType;
	}
	public void setLeaveSessionType(String leaveSessionType) {
		this.leaveSessionType = leaveSessionType;
	}

	@Column(name = "afternoonSession", nullable = true, length = 1, columnDefinition = "char(1) default 'Y'")
	@Type(type = "yes_no")
	public boolean isAfternoonSession() {
		return afternoonSession;
	}
	public void setAfternoonSession(boolean afternoonSession) {
		this.afternoonSession = afternoonSession;
	}
	// Constructors

	/** default constructor */
	public StaffDailyAttendance() {
	}

	/** minimal constructor */
	public StaffDailyAttendance(long id, long accountId) {
		super.setId(id);
		this.accountId = accountId;
	}
	
	
 	/**
	 * @return the staffLateTime
	 */
	public String getStaffLateTime() {
		return staffLateTime;
	}

	/**
	 * @param staffLateTime the staffLateTime to set
	 */
	public void setStaffLateTime(String staffLateTime) {
		this.staffLateTime = staffLateTime;
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
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
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
		
		if (null == obj || this.getClass() != obj.getClass()) {
			return false;
		} else {		
			StaffDailyAttendance other = (StaffDailyAttendance) obj;
			if (accountId != other.accountId)
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
 
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
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
	@Column(name = "inTime", nullable = true, length = 10)
	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	@Column(name = "outTime", nullable = true, length = 10)
	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	
	@Column(name = "workingHours", nullable = true, length = 10)
	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
     
	
}