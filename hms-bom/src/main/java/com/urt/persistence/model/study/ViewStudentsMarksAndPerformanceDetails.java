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
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_studentsMarksAndPerformanceDetails")
public class ViewStudentsMarksAndPerformanceDetails implements Serializable,Cloneable,Comparable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

       
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private long studId;
	private double obtainedMarks;
	private double moderationMarks;
	private double subjectTopperMarks;
	private long classSectionId;
	private long examScheduleId;
	private long marksId;
	private long custId;
	private long academicYearId;
	private int subjectPosition;
	private boolean present;
	private int rankPosition;
	
	
	
	/**
	 * @return the present
	 */
	@Column(name = "present", nullable = false, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isPresent() {
		return present;
	}
	/**
	 * @param present the present to set
	 */
	public void setPresent(boolean present) {
		this.present = present;
	}
	
	/**
	 * @return the rankPosition
	 */
	@Column(name = "rankPosition", columnDefinition="int(4) default 0")
	public int getRankPosition() {
		return rankPosition;
	}
	public void setRankPosition(int rankPosition) {
		this.rankPosition = rankPosition;
	}
	/**
	 * @return the subjectPosition
	 */
	@Column(name = "subjectPosition", columnDefinition="int(4) default 0")
	public int getSubjectPosition() {
		return subjectPosition;
	}
	/**
	 * @param subjectPosition the subjectPosition to set
	 */
	public void setSubjectPosition(int subjectPosition) {
		this.subjectPosition = subjectPosition;
	}
	/**
	 * @return the subjectTopperMarks
	 */
	@Column(name = "subjectTopperMarks",nullable = false,columnDefinition="double")
	public double getSubjectTopperMarks() {
		return subjectTopperMarks;
	}
	/**
	 * @param subjectTopperMarks the subjectTopperMarks to set
	 */
	public void setSubjectTopperMarks(double subjectTopperMarks) {
		this.subjectTopperMarks = subjectTopperMarks;
	}
	/**
	 * @return the examScheduleId
	 */
	public long getExamScheduleId() {
		return examScheduleId;
	}
	/**
	 * @param examScheduleId the examScheduleId to set
	 */
	public void setExamScheduleId(long examScheduleId) {
		this.examScheduleId = examScheduleId;
	}
	
	@Column(name = "studId", nullable = false,columnDefinition="varchar(5) default 0",length = 20)
	public long getStudId() {
		return studId;
	}
	public void setStudId(long studId) {
		this.studId = studId;
	}
	
	@Id
	@Column( name="marksId", unique=false, nullable=false, updatable=false )
	public long getMarksId() {
		return marksId;
	}
	public void setMarksId(long marksId) {
		this.marksId = marksId;
	}
	
	@Column(name = "custId")
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Column(name = "academicYearId")
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@Column(name = "classSectionId")
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Column(name = "obtainedMarks",nullable = false,columnDefinition="double")
	public double getObtainedMarks() {
		return obtainedMarks;
	}
	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	
	/**
	 * Constructor for Student.
	 */
	public ViewStudentsMarksAndPerformanceDetails() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		 if (this == obj) {
			 return true;
		 } else {
			 return false;
		 }
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);	
		buffer.append(classSectionId).append(getClassSectionId());
		buffer.append(academicYearId);		
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895).append(this.examScheduleId)
                .append(this.classSectionId).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
    	ViewStudentsMarksAndPerformanceDetails myClass = (ViewStudentsMarksAndPerformanceDetails) object;
        return new CompareToBuilder().append(this.classSectionId, myClass.classSectionId)
                .append(this.academicYearId, myClass.getAcademicYearId()).toComparison();
    }
    
	/**
	 * @return the moderationMarks
	 */
    @Column(name = "moderationMarks",nullable = false,columnDefinition = "double default 0" )
	public double getModerationMarks() {
		return moderationMarks;
	}
	/**
	 * @param moderationMarks the moderationMarks to set
	 */
	public void setModerationMarks(double moderationMarks) {
		this.moderationMarks = moderationMarks;
	}
	
	@Transient
	public double getObtainedAndModerationMarks(){
		return this.obtainedMarks+this.moderationMarks;
	}
}
