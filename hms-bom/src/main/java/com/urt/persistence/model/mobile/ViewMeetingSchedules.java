package com.urt.persistence.model.mobile;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewMeetingSchedules.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * May 05, 2016 SUBRAMANYAM Created /
 ********************************************************************/

@Entity
@Table(name = "vw_meetingSchedules")
public class ViewMeetingSchedules implements Serializable,Cloneable,Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private long id;
	private long meetingSlotId;
	private long meetingScheduleId;
	private Date date;
	private String description;
	private long staffId;
	private long staffAccountId;
	private String staffName;
	private long studyClassId;
	private String className;
	private String section;
	private String slotType;
	private String slotTime;
	private long parentAccountId;
	private String parentName;
	private String isAuto;
	private long custId;
	
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	public String getSlotType() {
		return slotType;
	}
	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public ViewMeetingSchedules() {
		super();
	}
	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMeetingSlotId() {
		return meetingSlotId;
	}

	public void setMeetingSlotId(long meetingSlotId) {
		this.meetingSlotId = meetingSlotId;
	}

	public long getMeetingScheduleId() {
		return meetingScheduleId;
	}

	public void setMeetingScheduleId(long meetingScheduleId) {
		this.meetingScheduleId = meetingScheduleId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public long getStaffAccountId() {
		return staffAccountId;
	}

	public void setStaffAccountId(long staffAccountId) {
		this.staffAccountId = staffAccountId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public long getStudyClassId() {
		return studyClassId;
	}

	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
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

	public String getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}

	public long getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(long parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewMeetingSchedules))
			return false;
		return false;

	}

	@Override
	public int hashCode() {
		int result = 0;

		return result;
	}
	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		
		return buffer.toString();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
