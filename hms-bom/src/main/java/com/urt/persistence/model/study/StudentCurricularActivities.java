package com.urt.persistence.model.study;
/********************************************************************
 * Copyright (C) 2014-15

 * IFS
 * All Rights Reserved 
 *
 * File: StudentCurricularActivities.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0  OCT 10, 2006           Narahari           Created
/ ********************************************************************/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "studentCurricularActivities")
public class StudentCurricularActivities extends PersistentObject {
    private static final long serialVersionUID = 1L;

	protected String activityName;
	protected String description;
	protected long custId;
	protected long activityTypeId;
	private String isDocsUploaded;
	
	private String activityTypeName;
	private long academicYearId;
	
	
	
	
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	@Transient
	public String getActivityTypeName() {
		return activityTypeName;
	}
	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}
	@Column(name = "isDocsUploaded", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getIsDocsUploaded() {
		return isDocsUploaded;
	}
	public void setIsDocsUploaded(String isDocsUploaded) {
		this.isDocsUploaded = isDocsUploaded;
	}
	public long getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(long activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
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
	
	@Override
	public int compareTo(Object object) {
		return 0;
	}
	@Override
	public boolean equals(Object o) {
		return false;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	@Override
	public String toString() {
		return "";
	}
	
	@Transient
	public String getCreatedDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getCreatedDate());
	}
	
	
		
}