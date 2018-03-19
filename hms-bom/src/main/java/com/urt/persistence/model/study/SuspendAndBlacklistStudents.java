package com.urt.persistence.model.study;
/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
/********************************************************************/

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "suspendAndBlacklistStudents")
public class SuspendAndBlacklistStudents extends PersistentObject {

    private long custId;
    private long academicYearId;
 	private Date blackedOrSuspendFromDate;
    private Date blackedOrSuspendToDate;
    private int suspendDays;
    private String status;
    private long studentId;
    protected String description; 
    
    
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public Date getBlackedOrSuspendFromDate() {
		return blackedOrSuspendFromDate;
	}

	public void setBlackedOrSuspendFromDate(Date blackedOrSuspendFromDate) {
		this.blackedOrSuspendFromDate = blackedOrSuspendFromDate;
	}

	public Date getBlackedOrSuspendToDate() {
		return blackedOrSuspendToDate;
	}

	public void setBlackedOrSuspendToDate(Date blackedOrSuspendToDate) {
		this.blackedOrSuspendToDate = blackedOrSuspendToDate;
	}

	@Column(name = "suspendDays", nullable = false, length = 2,columnDefinition="int default 0")
	public int getSuspendDays() {
		return suspendDays;
	}

	public void setSuspendDays(int suspendDays) {
		this.suspendDays = suspendDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	@Transient
	public String getBlackedOrSuspendFromDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.blackedOrSuspendFromDate);
	}
	
	@Transient
	public String getBlackedOrSuspendToDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.blackedOrSuspendToDate);
	}
	@Transient
	public String getBlackedOrSuspendFromDateStrs() {
		return DateFormatter.formatDate(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN, this.blackedOrSuspendFromDate);
	}
	@Transient
	public String getBlackedOrSuspendToDateStrs() {
		return DateFormatter.formatDate(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN, this.blackedOrSuspendToDate);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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