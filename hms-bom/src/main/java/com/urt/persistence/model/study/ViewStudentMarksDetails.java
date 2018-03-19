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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_studentMarksDetails")
public class ViewStudentMarksDetails implements Serializable,Cloneable,Comparable {
	
	private static final Log log = LogFactory.getLog(ViewStudentMarksDetails.class);
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
	private long  classNameClassId;
	private String examType;
	private double maxMarks;
	private double minMarks;
	private String academicYearId;
	private double obtainedMarks;
	private String subjectName;
	private Date examDate;
	private String startTime;
	private String endTime;
	private String className;
	private String section;
	protected boolean present;
	private String subjectId;
	protected Date lastUpdatedDate;
	private long  subTypeId;
	private String subTypeName;
	protected boolean subTypeSchedule;
	protected long subTypeMaxMarks;
	private double moderationMarks;
	private double totalSubjectMarksObtained;
	private boolean language;
	private boolean status;
	private int examTypeSortingOrder;
	private int subTypeSortingOrder;
	private int subjectSortingOrder;
	private double obtainedSubTypeMarks;
	private String studDiscontinueDesc;
	private int subjectTotalMarks;
	private String subjectShortName;
	private Date examScStartDate;
	

	private String studentName;
	private String admissionNumber;
	protected double marksPercentage;
	
	
	@Transient
	public double getMarksPercentage() {
		return marksPercentage;
	}
	public void setMarksPercentage(double marksPercentage) {
		this.marksPercentage = marksPercentage;
	}
	@Transient
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	@Transient
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Date getExamScStartDate() {
		return examScStartDate;
	}
	public void setExamScStartDate(Date examScStartDate) {
		this.examScStartDate = examScStartDate;
	}
	/**
	 * @return the subjectShortName
	 */
	public String getSubjectShortName() {
		return subjectShortName;
	}
	/**
	 * @param subjectShortName the subjectShortName to set
	 */
	public void setSubjectShortName(String subjectShortName) {
		this.subjectShortName = subjectShortName;
	}
	/**
	 * @return the subjectTotalMarks
	 */
	public int getSubjectTotalMarks() {
		return subjectTotalMarks;
	}
	/**
	 * @param subjectTotalMarks the subjectTotalMarks to set
	 */
	public void setSubjectTotalMarks(int subjectTotalMarks) {
		this.subjectTotalMarks = subjectTotalMarks;
	}
	/**
	 * @return the studDiscontinueDesc
	 */
	public String getStudDiscontinueDesc() {
		return studDiscontinueDesc;
	}
	/**
	 * @param studDiscontinueDesc the studDiscontinueDesc to set
	 */
	public void setStudDiscontinueDesc(String studDiscontinueDesc) {
		this.studDiscontinueDesc = studDiscontinueDesc;
	}
	/**
	 * @return the subTypeSortingOrder
	 */
	@Column(name = "subTypeSortingOrder", columnDefinition="int(4) default 0")
	public int getSubTypeSortingOrder() {
		return subTypeSortingOrder;
	}
	/**
	 * @param subTypeSortingOrder the subTypeSortingOrder to set
	 */
	public void setSubTypeSortingOrder(int subTypeSortingOrder) {
		this.subTypeSortingOrder = subTypeSortingOrder;
	}
	/**
	 * @return the subjectSortingOrder
	 */
	@Column(name = "subjectSortingOrder", columnDefinition="int(4) default 0")
	public int getSubjectSortingOrder() {
		return subjectSortingOrder;
	}
	/**
	 * @param subjectSortingOrder the subjectSortingOrder to set
	 */
	public void setSubjectSortingOrder(int subjectSortingOrder) {
		this.subjectSortingOrder = subjectSortingOrder;
	}
	/**
	 * @return the obtainedSubTypeMarks
	 */
	@Column(name = "obtainedSubTypeMarks",nullable = false,columnDefinition="varchar(5) default 0", length = 128)
	public double getObtainedSubTypeMarks() {
		return obtainedSubTypeMarks;
	}
	/**
	 * @param obtainedSubTypeMarks the obtainedSubTypeMarks to set
	 */
	public void setObtainedSubTypeMarks(double obtainedSubTypeMarks) {
		this.obtainedSubTypeMarks = obtainedSubTypeMarks;
	}
	/**
	 * @return the stringBufferSize
	 */
	public static int getStringBufferSize() {
		return STRING_BUFFER_SIZE;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	/**
	 * @return the language
	 */
    @Column(name = "language", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(boolean language) {
		this.language = language;
	}
	
	/**
	 * @return the subTypeMaxMarks
	 */
	public long getSubTypeMaxMarks() {
		return subTypeMaxMarks;
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
	 * @param subTypeMaxMarks the subTypeMaxMarks to set
	 */
	public void setSubTypeMaxMarks(long subTypeMaxMarks) {
		this.subTypeMaxMarks = subTypeMaxMarks;
	}
	public long getSubTypeId() {
		return subTypeId;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public boolean isSubTypeSchedule() {
		return subTypeSchedule;
	}
	public void setSubTypeId(long subTypeId) {
		this.subTypeId = subTypeId;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public void setSubTypeSchedule(boolean subTypeSchedule) {
		this.subTypeSchedule = subTypeSchedule;
	}
	@Column(name = "subjectId", nullable = true, length = 10)
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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

	@Column(name = "examType", nullable = true, length = 128)
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	@Column(name = "subjectName", nullable = true, length = 255)
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	@Column(name = "maxMarks", nullable = false,columnDefinition="varchar(5) default 0",length = 128)
	public double getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	
	@Column(name = "minMarks", nullable = false,columnDefinition="varchar(5) default 0",length = 128)
	public double getMinMarks() {
		return minMarks;
	}
	public void setMinMarks(double minMarks) {
		this.minMarks = minMarks;
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
	
	@Column(name = "examDate", nullable = true, length = 255)
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	
	@Column(name = "startTime", nullable = true, length = 255)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "endTime", nullable = true, length = 255)
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * Constructor for Student.
	 */
	public ViewStudentMarksDetails() {
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
        return new HashCodeBuilder(375130981, -1650712895).append(this.rollNumber)
                .append(this.classSectionId).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
    	ViewStudentMarksDetails myClass = (ViewStudentMarksDetails) object;
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
    public String getExamDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getExamDate());
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
    public String getCBSCObtainedMarksToGrade()
    {
		String grade=" ";
		double marks =0;
    	if(!ObjectFunctions.isNullOrEmpty(getObtainedMarks()))
    	{
    		marks = (getObtainedMarks()/getSubTypeMaxMarks())*100;
    		
    		log.debug("getObtainedMarks() ::" +getObtainedMarks()); 
    		log.debug("getSubTypeMaxMarks() ::" +getSubTypeMaxMarks());
    		log.debug("marks ::" +marks); 
    		if(marks <= 100 && marks > 91)
    		{
    			grade="A1";
    		}
    		else if(marks <= 90 && marks > 81)
    		{
    			grade="A2";
    		}
    		else if(marks <= 80 && marks > 71)
    		{
    			grade="B1";
    		}
    		else if(marks <= 70 && marks > 61)
    		{
    			grade="B2";
    		}
    		else if(marks <= 60 && marks > 51)
    		{
    			grade="C1";
    		}
    		else if(marks <= 50 && marks > 41)
    		{
    			grade="C2";
    		}
    		else if(marks <= 40 && marks > 31)
    		{
    			grade="D";
    		}
    		else if(marks <= 30 && marks > 21)
    		{
    			grade="E1";
    		}
    		else if(marks <= 20 && marks > 0)
    		{
    			grade="E2";
    		}
    	}
    	return grade;
    }
	@Transient
    public String getCBSCTotalMarksToGrade(double toatlMarks)
    {
		String grade=" ";
		double marks =0;
    	if(!ObjectFunctions.isNullOrEmpty(getObtainedMarks()))
    	{
    		marks = (toatlMarks/50)*100;
    		
    		log.debug("getObtainedMarks() ::" +getObtainedMarks()); 
    		log.debug("getSubTypeMaxMarks() ::" +getSubTypeMaxMarks());
    		log.debug("marks ::" +marks); 
    		if(marks <= 100 && marks > 91)
    		{
    			grade="A1";
    		}
    		else if(marks <= 90 && marks > 81)
    		{
    			grade="A2";
    		}
    		else if(marks <= 80 && marks > 71)
    		{
    			grade="B1";
    		}
    		else if(marks <= 70 && marks > 61)
    		{
    			grade="B2";
    		}
    		else if(marks <= 60 && marks > 51)
    		{
    			grade="C1";
    		}
    		else if(marks <= 50 && marks > 41)
    		{
    			grade="C2";
    		}
    		else if(marks <= 40 && marks > 31)
    		{
    			grade="D";
    		}
    		else if(marks <= 30 && marks > 21)
    		{
    			grade="E1";
    		}
    		else if(marks <= 20 && marks > 0)
    		{
    			grade="E2";
    		}
    	}
    	return grade;
    }
	
	@Transient
    public String getClassNameClassIdAndExamTypeId()
    {
    	return getClassNameClassId()+"-"+getExamTypeId();
    }
	@Column(name = "examTypeSortingOrder", columnDefinition="int(4) default 0")
	public int getExamTypeSortingOrder() {
		return examTypeSortingOrder;
	}
	public void setExamTypeSortingOrder(int examTypeSortingOrder) {
		this.examTypeSortingOrder = examTypeSortingOrder;
	}
	@Transient
	 public String getFmtedTotalSubjectMarksObtained()
	{
	    if(this.totalSubjectMarksObtained == (int) this.totalSubjectMarksObtained)
	        return String.format("%d",(int)this.totalSubjectMarksObtained);
	    else
	        return String.format("%s",this.totalSubjectMarksObtained);
	}
}
