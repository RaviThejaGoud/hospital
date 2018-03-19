package com.urt.persistence.model.exam;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewClassExamDetails.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Ganesh Created /
 ********************************************************************/

@Entity
@Table(name = "vw_examScheduleWiseStudentMarks")
public class ViewExamScheduleWiseStudentMarks implements Serializable,Cloneable,Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	protected long scheduleId;
	protected long studId;
	protected long custId;
	protected long classSectionId;
	protected long classSubjectId;
	protected long academicYearId;
	protected long examTypeId;
	protected long marksId;
	protected long subTypeId;
	protected double obtainedMarks;
	protected double moderationMarks;
	protected String present;
	protected String examType;
	protected long maxMarks;
	protected long minMarks;
	protected double totalSubjectMarksObtained;
	protected String subjectName;
	private boolean higherStandard;
	private String subTypeName;
	private double scheduleMaxMarks;
	private long eid;
	private long sortingOrder;
	private String subjectNumber;
	private long subjectId;
	private double obtainedSubTypeTotalMarks;
	
	
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectNumber() {
		return subjectNumber;
	}
	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	public long getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public double getScheduleMaxMarks() {
		return scheduleMaxMarks;
	}
	public void setScheduleMaxMarks(double scheduleMaxMarks) {
		this.scheduleMaxMarks = scheduleMaxMarks;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	/**
	 * @param subTypeName the subTypeName to set
	 */
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	
	@Column(name = "higherStandard", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isHigherStandard() {
		return higherStandard;
	}
	/**
	 * @param higherStandard the higherStandard to set
	 */
	public void setHigherStandard(boolean higherStandard) {
		this.higherStandard = higherStandard;
	}
	 
	/**
	 * @return the studId
	 */
	public long getStudId() {
		return studId;
	}
	/**
	 * @param studId the studId to set
	 */
	public void setStudId(long studId) {
		this.studId = studId;
	}
	/**
	 * @return the academicYearId
	 */
	public long getAcademicYearId() {
		return academicYearId;
	}
	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	/**
	 * @return the examTypeId
	 */
	public long getExamTypeId() {
		return examTypeId;
	}
	/**
	 * @param examTypeId the examTypeId to set
	 */
	public void setExamTypeId(long examTypeId) {
		this.examTypeId = examTypeId;
	}
	/**
	 * @return the marksId
	 */
	@Id
	@Column( name="marksId", unique=true, nullable=false, updatable=false )
	public long getMarksId() {
		return marksId;
	}
	/**
	 * @param marksId the marksId to set
	 */
	public void setMarksId(long marksId) {
		this.marksId = marksId;
	}
	/**
	 * @return the subTypeId
	 */
	public long getSubTypeId() {
		return subTypeId;
	}
	/**
	 * @param subTypeId the subTypeId to set
	 */
	public void setSubTypeId(long subTypeId) {
		this.subTypeId = subTypeId;
	}
	/**
	 * @return the obtainedMarks
	 */
	public double getObtainedMarks() {
		return obtainedMarks;
	}
	/**
	 * @param obtainedMarks the obtainedMarks to set
	 */
	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	/**
	 * @return the moderationMarks
	 */
	public double getModerationMarks() {
		return moderationMarks;
	}
	/**
	 * @param moderationMarks the moderationMarks to set
	 */
	public void setModerationMarks(double moderationMarks) {
		this.moderationMarks = moderationMarks;
	}
	/**
	 * @return the present
	 */
	public String getPresent() {
		return present;
	}
	/**
	 * @param present the present to set
	 */
	public void setPresent(String present) {
		this.present = present;
	}
	/**
	 * @return the maxMarks
	 */
	public long getMaxMarks() {
		return maxMarks;
	}
	/**
	 * @param maxMarks the maxMarks to set
	 */
	public void setMaxMarks(long maxMarks) {
		this.maxMarks = maxMarks;
	}
	/**
	 * @return the minMarks
	 */
	public long getMinMarks() {
		return minMarks;
	}
	/**
	 * @param minMarks the minMarks to set
	 */
	public void setMinMarks(long minMarks) {
		this.minMarks = minMarks;
	}
	/**
	 * @return the totalSubjectMarksObtained
	 */
	public double getTotalSubjectMarksObtained() {
		return totalSubjectMarksObtained;
	}
	/**
	 * @param totalSubjectMarksObtained the totalSubjectMarksObtained to set
	 */
	public void setTotalSubjectMarksObtained(double totalSubjectMarksObtained) {
		this.totalSubjectMarksObtained = totalSubjectMarksObtained;
	}
	/**
	 * @return the classSubjectId
	 */
	public long getClassSubjectId() {
		return classSubjectId;
	}
	/**
	 * @param classSubjectId the classSubjectId to set
	 */
	public void setClassSubjectId(long classSubjectId) {
		this.classSubjectId = classSubjectId;
	}
	/**
	 * @return the classSectionId
	 */
	public long getClassSectionId() {
		return classSectionId;
	}
	/**
	 * @param classSectionId the classSectionId to set
	 */
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	//private String scid;
	
	public long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	
	@Override
	public int compareTo(Object object) {/*
		ViewExamScheduleWiseStudentMarks target = (ViewExamScheduleWiseStudentMarks) object;
        long timeDifference = 0;
        boolean isBefore;
        if (target.getLastUpdatedDate()!= null && this.getLastUpdatedDate()!= null)
        {
        	isBefore = (target.getLastUpdatedDate()).before(this.getLastUpdatedDate());
        }
        int difference;
        if(timeDifference == 0)
        {
            difference = 0;
        }
        else if(timeDifference < 0)
        {
            difference = -1;
        }
        else
        {
            difference = 1;
        }
        return difference;       
    */
		return 0;
	}
	/**
	 * @return the examType
	 */
	public String getExamType() {
		return examType;
	}
	/**
	 * @param examType the examType to set
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewExamScheduleWiseStudentMarks))
			return false;
		return false;
	}
	
	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	public double getObtainedSubTypeTotalMarks() {
		return obtainedSubTypeTotalMarks;
	}
	public void setObtainedSubTypeTotalMarks(double obtainedSubTypeTotalMarks) {
		this.obtainedSubTypeTotalMarks = obtainedSubTypeTotalMarks;
	}
	
}
