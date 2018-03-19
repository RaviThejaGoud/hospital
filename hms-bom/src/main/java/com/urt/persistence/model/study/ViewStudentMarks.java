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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.string.StringFunctions;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_studentMarks")
public class ViewStudentMarks implements Serializable,Cloneable,Comparable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private long studId;
	private long marksId;
	private long examTypeId;
	private long custId;
	private long scheduleId;
	private String rollNumber;
	private long  classSectionId;
	private long  subjectId;
	private long  classNameClassId;
	private String academicYearId;
	private double obtainedMarks;
	private double moderationMarks;
	private String className;
	private String section;
	private String subjectType;
	protected Date lastUpdatedDate;
	protected boolean present;
	private long  subTypeId;
	   
	/**
	 * @return the stringBufferSize
	 */
	public static int getStringBufferSize() {
		return STRING_BUFFER_SIZE;
	}
	 
	/**
	 * @return the classNameClassId
	 */
	public long getClassNameClassId() {
		return classNameClassId;
	}
	/**
	 * @param classNameClassId the classNameClassId to set
	 */
	public void setClassNameClassId(long classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
	 
	@Column(name = "present", nullable = false, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	@Column(name = "scheduleId", nullable = false,columnDefinition="varchar(5) default 0",length = 20)
	public long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
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
	
	@Column(name = "custId", nullable = true, length = 20)
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Column(name = "rollNumber", nullable = true, length = 255)
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	
	@Column(name = "academicYearId", nullable = true, length = 128)
	public String getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@Column(name = "classSectionId", nullable = true, length = 20)
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	 
	
	@Column(name = "obtainedMarks",nullable = false,columnDefinition="varchar(5) default 0", length = 128)
	public double getObtainedMarks() {
		return obtainedMarks;
	}
	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	
	@Column(name = "examTypeId", nullable = true, length = 255)
	public long getExamTypeId() {
		return examTypeId;
	}
	public void setExamTypeId(long examTypeId) {
		this.examTypeId = examTypeId;
	}
	
	 
	/**
	 * @return the subjectId
	 */
	@Column(name = "subjectId", nullable = true, length = 255)
	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the moderationMarks
	 */
	@Column(name = "moderationMarks",nullable = false,columnDefinition="varchar(5) default 0", length = 128)
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
	 * @return the subjectType
	 */
	@Column(name = "subjectType", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getSubjectType() {
		return subjectType;
	}

	/**
	 * @param subjectType the subjectType to set
	 */
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	/**
	 * @return the subTypeId
	 */
	@Column(name = "subTypeId", nullable = true, length = 255)
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
	 * Constructor for Student.
	 */
	public ViewStudentMarks() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		 if (this == obj) {
			 return true;
		 } else{
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
        return new HashCodeBuilder(375130981, -1650712895).append(this.rollNumber)
                .append(this.classSectionId).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
    	ViewStudentMarks myClass = (ViewStudentMarks) object;
        return new CompareToBuilder().append(this.classSectionId, myClass.classSectionId)
                .append(this.academicYearId, myClass.getAcademicYearId()).toComparison();
    }
    
    @JSON(serialize=false)
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	@Transient
	public int getLastUpdatedDateDueDays() {
	  try{
		  return DateFunctions.daysBetween(getLastUpdatedDate(), new Date());			
	  }
	  catch(Exception ex){
		  return 0;
	  }
		
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
	@Transient
    public String getClassAndSection()
    {
    	if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getClassName()+" - "+getSection();
    }
	
	@Transient
    public String getClassNameClassIdAndExamTypeId()
    {
    	return getClassNameClassId()+"-"+getExamTypeId();
    }
	 
}
