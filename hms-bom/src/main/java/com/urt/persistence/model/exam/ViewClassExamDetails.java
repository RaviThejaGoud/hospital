package com.urt.persistence.model.exam;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;


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
@Table(name = "vw_classExamDetails")
public class ViewClassExamDetails implements Serializable,Cloneable,Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long classId;
	private long classSectionId;
	private long  classSubjectId;
	private long custId;
	private String className;
	private String name;
	private Date examDate;
	private String startTime;
	private String endTime;
	protected Date lastUpdatedDate;
	private String section;
	private String examType;
	private double minMarks;
	private double maxMarks;
	private String academicYearId;
	private String syllabus;
	private long eid;
	private String description;
	private long  scheduleId;
	private long subTypeId;
	private String subTypeName;
	private boolean schedule;
	//private List<StudyClass> classSections;
	private double scheduleMaxMarks;
	private Date startDate;
	private Date endDate;
	private boolean predefinedSubType;
	private boolean higherStandard;
	private String subjectNumber;
	private int sortingOrder;
	private boolean language;
	private int subTypeSortingOrder;
	private int examTypeSortingOrder;
	
	
	
	/**
	 * @return the examTypeSortingOrder
	 */
	public int getExamTypeSortingOrder() {
		return examTypeSortingOrder;
	}
	/**
	 * @param examTypeSortingOrder the examTypeSortingOrder to set
	 */
	public void setExamTypeSortingOrder(int examTypeSortingOrder) {
		this.examTypeSortingOrder = examTypeSortingOrder;
	}
	@Column(name = "subTypeSortingOrder", columnDefinition="int(4) default 0")
	public int getSubTypeSortingOrder() {
		return subTypeSortingOrder;
	}
	public void setSubTypeSortingOrder(int subTypeSortingOrder) {
		this.subTypeSortingOrder = subTypeSortingOrder;
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
	 * @return the sortingOrder
	 */
    @Column(name = "sortingOrder", columnDefinition="int(4) default 0")
	public int getSortingOrder() {
		return sortingOrder;
	}
	/**
	 * @param sortingOrder the sortingOrder to set
	 */
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	/**
	 * @return the subjectNumber
	 */
	public String getSubjectNumber() {
		return subjectNumber;
	}
	/**
	 * @param subjectNumber the subjectNumber to set
	 */
	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	/**
	 * @return the higherStandard
	 */
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
	 * @return the predefinedSubType
	 */
    @Column(name = "predefinedSubType", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isPredefinedSubType() {
		return predefinedSubType;
	}
	/**
	 * @param predefinedSubType the predefinedSubType to set
	 */
	public void setPredefinedSubType(boolean predefinedSubType) {
		this.predefinedSubType = predefinedSubType;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the scheduleMaxMarks
	 */
	public double getScheduleMaxMarks() {
		return scheduleMaxMarks;
	}
	/**
	 * @param scheduleMaxMarks the scheduleMaxMarks to set
	 */
	public void setScheduleMaxMarks(double scheduleMaxMarks) {
		this.scheduleMaxMarks = scheduleMaxMarks;
	}
	/*@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="classNameClassId",referencedColumnName="classId")
	public List<StudyClass> getClassSections() {
		return classSections;
	}
	public void setClassSections(List<StudyClass> classSections) {
		this.classSections = classSections;
	}*/

	/**
	 * @return the subTypeId
	 */
	public long getSubTypeId() {
		return subTypeId;
	}
	/**
	 * @return the subTypeName
	 */
	public String getSubTypeName() {
		return subTypeName;
	}
	/**
	 * @return the schedule
	 */
	@Column(name = "schedule", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isSchedule() {
		return schedule;
	}
	/**
	 * @param subTypeId the subTypeId to set
	 */
	public void setSubTypeId(long subTypeId) {
		this.subTypeId = subTypeId;
	}
	/**
	 * @param subTypeName the subTypeName to set
	 */
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
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
	@Id
	@Column( name="scheduleId", unique=false, nullable=false, updatable=false )
	public long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}
	/*@Transient
	public String getExamTypeId(){
		return String.valueOf(this.eId);
	}*/
	/*@Column(name = "scid", nullable = true, length = 20)
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}*/
	@Column(name = "description", nullable = true, length = 255)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "eid", nullable = false, columnDefinition=" int default 0")
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	
	
	/**
	 * @return the syllabudIds
	 *//*
	public String getSyllabudIds() {
		return syllabudIds;
	}
	*//**
	 * @param syllabudIds the syllabudIds to set
	 *//*
	public void setSyllabudIds(String syllabudIds) {
		this.syllabudIds = syllabudIds;
	}*/
	
	
	public String getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@Override
	public int compareTo(Object object) {
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
	
	/**
	 * @return the minMarks
	 */
	public double getMinMarks() {
		return minMarks;
	}
	/**
	 * @return the maxMarks
	 */
	public double getMaxMarks() {
		return maxMarks;
	}
	/**
	 * @param minMarks the minMarks to set
	 */
	public void setMinMarks(double minMarks) {
		this.minMarks = minMarks;
	}
	/**
	 * @param maxMarks the maxMarks to set
	 */
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @return the classId
	 */
	public long getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
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
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the subjectName
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the examTypeId
	 */
	/*public String getExamTypeId() {
		return examTypeId;
	}
	*//**
	 * @param examTypeId the examTypeId to set
	 *//*
	public void setExamTypeId(String examTypeId) {
		this.examTypeId = examTypeId;
	}*/
	/**
	 * @return the examDate
	 */
	public Date getExamDate() {
		return examDate;
	}
	/**
	 * @param examDate the examDate to set
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	@Transient
    public String getExamDateStr() {
		if(ObjectFunctions.isNullOrEmpty(this.examDate))
			return "";
		else
            return DateFormatter.formatDate(DateFormatter.DDMMYYYY_PATTERN, this.getExamDate());
    }
	@Transient
    public String getStartDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getStartDate());
    }
	@Transient
    public String getStartExamDateStr() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getStartDate());
    }
	
	@Transient
    public String getExamEndDateStr() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getEndDate());
    }
	@Transient
    public String getEndDateStr() {
		    if(!ObjectFunctions.isNullOrEmpty(this.getEndDate()))
		    	return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getEndDate());
		    else
		    	return "";
    }
	@Transient
	public int getExamDuedays() {
	  try{
		  return DateFunctions.daysBetween( new Date(),getExamDate());			
	  }
	  catch(Exception ex){
		  return 0;
	  }
		
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	@Transient
	public String getClassSection() {
		if(StringFunctions.isNotNullOrEmpty(this.section))
			return this.className+" "+this.section;
		else
			return this.className;	
	}
    @Transient
    public String  getSubTypeUpperCase(){
    	if(StringFunctions.isNullOrEmpty(getSubTypeName()))
    		return "";
    	else
    		return getSubTypeName().toUpperCase();
    }
    @Transient
	public String getExamDateAndTime() {
		StringBuffer ret = new StringBuffer(10);

		if (!ObjectFunctions.isNullOrEmpty(getStartDate())) {
			ret.append(getStartDate());
		}
		if (!StringFunctions.isNullOrEmpty(getStartTime())) {
			ret.append(" ");
			ret.append(getStartTime());
		}

		return ret.toString().trim();
	}
    @Transient
	public String getExamDateAndTimeStr() {
		StringBuffer ret = new StringBuffer(10);

		if (!ObjectFunctions.isNullOrEmpty(getExamDateStr().replace("00:00:00.0", ""))) {
			ret.append(getExamDateStr().replace("00:00:00.0", ""));
		}
		if (!StringFunctions.isNullOrEmpty(getStartTime())) {
			ret.append(" ");
			ret.append(getStartTime());
		}

		return ret.toString().trim().replace("00:00:00.0", "");
	}
    
    @Transient
	public String getNameAndSubTypeName() {
		if (!StringFunctions.isNullOrEmpty(getSubTypeName())) {
			return getName() + " - " + getSubTypeName();
		}
		return getName();
	}
    @Transient
   	public String getExamStartTime() {
    	if (!StringFunctions.isNullOrEmpty(this.startTime)) {
   			return this.startTime;
   		}
   		return "";
    }
    
    @Transient
	public String getTodayDateString(){
    	String[] weekdays = new DateFormatSymbols().getWeekdays(); 
    	Calendar cal = Calendar.getInstance();
		if(!ObjectFunctions.isNullOrEmpty(getExamDateStr())){
			cal.setTime(getExamDate());
			String date = DateFormatter.formatDate(DateFormatter.DDMMYYYY_PATTERN, cal.getTime());
			int day = cal.get(Calendar.DAY_OF_WEEK);
		}
		return weekdays[cal.get(Calendar.DAY_OF_WEEK)];
	}
    @Transient
   	public String getExamStimeAndETime() {
    	
    	StringBuffer ret = new StringBuffer(10);

		if (!ObjectFunctions.isNullOrEmpty(getStartTime())) {
			ret.append(getStartTime());
		}
		if (!StringFunctions.isNullOrEmpty(getEndTime())) {
			ret.append(" - ");
			ret.append(getEndTime());
		}

		return ret.toString().trim();
    	
   	}
}
