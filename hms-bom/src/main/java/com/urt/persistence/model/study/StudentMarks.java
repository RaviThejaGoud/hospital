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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.exam.ExamSchedules;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "studentMarks")
public class StudentMarks extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	private double obtainedMarks;
	private ExamSchedules examSchedule;
	protected boolean present;
	private double moderationMarks;
	private Student student;
	private int subjectPosition;
	private int rankPosition;
	
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
	 * @return the subjectPosition
	 */
	@Column(name = "rankPosition", columnDefinition="int(4) default 0")
	public int getRankPosition() {
		return rankPosition;
	}
	public void setRankPosition(int rankPosition) {
		this.rankPosition = rankPosition;
	}
	
	@ManyToOne
	@JoinColumn(name="studId", insertable=false, updatable=false)
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Column(name = "present", nullable = false, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
    
	@Column(name = "obtainedMarks",nullable = false,columnDefinition="double")
	public double getObtainedMarks() {
		return obtainedMarks;
	}
	
	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "examScheduleId",insertable=true, updatable=true)
	public ExamSchedules getExamSchedule() {
		return examSchedule;
	}
	public void setExamSchedule(ExamSchedules examSchedule) {
		this.examSchedule = examSchedule;
	}
	
	/**
	 * Constructor for Student.
	 */
	public StudentMarks() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		
		 if (this == o) {
			 return true;
		 }
		 else if (null == o || !(o instanceof StudentMarks)) {
			 return false;
		 } else { 
			 final StudentMarks marks = (StudentMarks) o;
			 if (obtainedMarks != 0 ? obtainedMarks!=marks.getObtainedMarks() : marks.getObtainedMarks() != 0)       	 
        	 return false;
		 } 
		return true;
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);	
		buffer.append(getId()).append(" ");
		buffer.append(obtainedMarks).append(" ");
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
    	 return  0;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    @Override
	public int compareTo(Object object) {
    	/*StudentMarks target = (StudentMarks) object;
        if (target.getExamType() != null && this.getExamType() != null)
        {
            if(this.getExamType().equalsIgnoreCase(target.getExamType()))
                return 0;
            else 
               return this.getExamType().compareToIgnoreCase(target.getExamType());
                 
        }*/
        return 0;
    }
	/**
	 * @return the moderationMarks
	 */
    @Column(name = "moderationMarks",nullable = false,updatable=true,columnDefinition = "double default 0" )
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
	public long getClassSectionId(){
		if(ObjectFunctions.isNullOrEmpty(this.examSchedule))
			return 0;
		else 
			return this.examSchedule.getClassSectionId();
	}
    @Transient
    public long getExamTypeId(){
    	if(!ObjectFunctions.isNullOrEmpty(this.examSchedule) && !ObjectFunctions.isNullOrEmpty(this.examSchedule.getExamType())){
    		return this.examSchedule.getExamType().getId();
    	}
    	return 0;
    }
    @Transient
    public long getScheduleId(){
    	if(ObjectFunctions.isNullOrEmpty(this.examSchedule))
    		return 0;
    	else
    		return this.examSchedule.getId();
    		
    }
    //Using in examschedule import and upload marks sheets
    @Transient
    public String getExamTypeIdAndScheduleId(){
    	StringBuffer ids = new StringBuffer();
    	return ids.append(getExamTypeId()).append("_").append(getScheduleId()).toString();
    }
    @Transient 
    public double getObtainedAndModerationMarks(){
    	return this.obtainedMarks+this.moderationMarks;
    }
    @Transient
    public String getSubjectName(){
    	if(!ObjectFunctions.isNullOrEmpty(this.examSchedule) && !ObjectFunctions.isNullOrEmpty(this.examSchedule.getClassSectionSubject()))
    		return this.examSchedule.getClassSectionSubject().getName();
    	else
    		return "";
    }
    @Transient
    public String getExamTypeName(){
    	if(!ObjectFunctions.isNullOrEmpty(this.examSchedule) && !ObjectFunctions.isNullOrEmpty(this.examSchedule.getExamType()))
    		return this.examSchedule.getExamType().getExamType();
    	else
    		return "";
    }
    @Transient
    public double getSubTypeMaxMarks(){
    	if(!ObjectFunctions.isNullOrEmpty(this.examSchedule))
    		return this.examSchedule.getMaxMarks();
    	else
    		return 0;
    }
    @Transient
    public String getSubTypeName(){
    	if(!ObjectFunctions.isNullOrEmpty(this.examSchedule))
    		return this.examSchedule.getSubTypeName();
    	else
    		return "";
    }
    @Transient
    public long getSubTypeId(){
    	if(!ObjectFunctions.isNullOrEmpty(this.examSchedule) && !ObjectFunctions.isNullOrEmpty(this.examSchedule.getSubType()))
    		return this.examSchedule.getSubType().getId();
    	else
    		return 0;
    }
    
    @Transient
    public long getSubjectId(){
    	if(!ObjectFunctions.isNullOrEmpty(this.examSchedule) && !ObjectFunctions.isNullOrEmpty(this.examSchedule.getClassSectionSubject()))
    		return this.examSchedule.getClassSectionSubject().getId();
    	else
    		return 0;
    }
  }
