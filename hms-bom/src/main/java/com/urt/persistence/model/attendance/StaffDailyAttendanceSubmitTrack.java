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
package com.urt.persistence.model.attendance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "staffDailyAttendanceSubmitTrack") 
public class StaffDailyAttendanceSubmitTrack extends PersistentObject {


	private static final long serialVersionUID = 1L;
	private Long staffAccountId;
	private Date attendanceDate;
	private Long academicYearId;
	private Long custId;
	private Long classSectionId;
	/**
	 * @return the staffAccountId
	 */
	public Long getStaffAccountId() {
		return staffAccountId;
	}
	/**
	 * @param staffAccountId the staffAccountId to set
	 */
	public void setStaffAccountId(Long staffAccountId) {
		this.staffAccountId = staffAccountId;
	}
	/**
	 * @return the attendanceDate
	 */
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	/**
	 * @param attendanceDate the attendanceDate to set
	 */
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	/**
	 * @return the academicYearId
	 */
	public Long getAcademicYearId() {
		return academicYearId;
	}
	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	/**
	 * @return the classSectionId
	 */
	public Long getClassSectionId() {
		return classSectionId;
	}
	/**
	 * @param classSectionId the classSectionId to set
	 */
	public void setClassSectionId(Long classSectionId) {
		this.classSectionId = classSectionId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
     
}