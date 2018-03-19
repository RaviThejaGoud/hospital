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
/ ********************************************************************/
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.hyniva.sms.ws.vo.StaffHistoryVO;
import com.urt.persistence.model.base.PersistentObject;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "staffHistory")
public class StaffHistory extends PersistentObject {
	    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	protected String schoolName;
	protected double salary;
	protected Date startDate;
	protected Date endDate;
	protected double experience;
	protected String otherExperience;
	protected long custId;
	
	
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	public String getOtherExperience() {
		return otherExperience;
	}
	public void setOtherExperience(String otherExperience) {
		this.otherExperience = otherExperience;
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
	public String getStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getStartDate());
	}
	@Transient
	public String getEndDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getEndDate());
	}
	
	 
	@Transient
	public StaffHistory copyFromVoToEntity(StaffHistory staffHistory, StaffHistoryVO staffHistoryVo)
	{
		if(staffHistory.getId() == 0)
			staffHistory.id = staffHistoryVo.id;
		staffHistory.schoolName = staffHistoryVo.schoolName;
		staffHistory.salary = staffHistoryVo.salary;
		staffHistory.startDate = staffHistoryVo.startDate;
		staffHistory.endDate = staffHistoryVo.endDate;
		staffHistory.experience = staffHistoryVo.experience;
		staffHistory.otherExperience = staffHistoryVo.otherExperience;
		staffHistory.custId = staffHistoryVo.custId; 
		staffHistory.lastAccessDate = staffHistoryVo.lastAccessDate; 
		staffHistory.lastUpdatedDate = staffHistoryVo.lastUpdatedDate; 
		staffHistory.createdDate = staffHistoryVo.createdDate; 
		return staffHistory;
	}
	@Transient
	public StaffHistoryVO copyFromEntityToVo(StaffHistory staffHistory)
	{
		StaffHistoryVO staffHistoryVO = new StaffHistoryVO();
		
		staffHistoryVO.id = staffHistory.id;
		staffHistoryVO.schoolName = staffHistory.schoolName;
		staffHistoryVO.salary = staffHistory.salary;
		staffHistoryVO.startDate = staffHistory.startDate;
		staffHistoryVO.endDate = staffHistory.endDate;
		staffHistoryVO.experience = staffHistory.experience;
		staffHistoryVO.otherExperience = staffHistory.otherExperience;
		staffHistoryVO.custId = staffHistory.custId;
		staffHistoryVO.lastAccessDate = staffHistory.lastAccessDate; 
		staffHistoryVO.lastUpdatedDate = staffHistory.lastUpdatedDate; 
		staffHistoryVO.createdDate = staffHistory.createdDate; 
		return staffHistoryVO;
	}
		
}