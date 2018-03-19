/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: StudentDailyAttendanceTimeTrack.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   July 07, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "studentDailyAttendanceTimeTrack") 
public class StudentDailyAttendanceTimeTrack extends PersistentObject {


	private static final long serialVersionUID = 1L;
	private Long studentId;
	private Date attendanceDate;
	private Long academicYearId;
	private Long custId;
	private Date inTime;
	private Date outTime;
	private Long studyClassId;

	/** default constructor */
	public StudentDailyAttendanceTimeTrack() {
	}

	/** minimal constructor */
	public StudentDailyAttendanceTimeTrack(Long id, Long studentId) {
		super.setId(id);
		this.studentId = studentId;
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
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (null == obj || getClass() != obj.getClass()) {
			return false;
		} else {
			StudentDailyAttendanceTimeTrack other = (StudentDailyAttendanceTimeTrack) obj;
			if (studentId != other.studentId)
				return false;
			if (attendanceDate == null) {
				if (other.attendanceDate != null)
					return false;
			} else if (!attendanceDate.equals(other.attendanceDate))
				return false;
		}
		
		return true;
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

	/**
	 * @return the inTIme
	 */
	public Date getInTime() {
		return inTime;
	}

	/**
	 * @param inTIme the inTIme to set
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

	/**
	 * @return the studyClassId
	 */
	public Long getStudyClassId() {
		return studyClassId;
	}

	/**
	 * @param studyClassId the studyClassId to set
	 */
	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}

	/**
	 * @return the studentId
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * @return the academicYearId
	 */
	public Long getAcademicYearId() {
		return academicYearId;
	}

	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}
     
}