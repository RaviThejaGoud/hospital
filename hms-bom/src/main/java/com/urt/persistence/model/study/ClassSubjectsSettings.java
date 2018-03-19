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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.urt.persistence.model.base.PersistentObject;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "classSubjectsSettings")
public class ClassSubjectsSettings extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	
	private StudyClass classSection;
	private long custId;
	private StudySubject studySubject;
	private int periodsPerWeek;
	private int subjectPriority;
	private int continuousPeriodsCount;
	
	@OneToOne
	@JoinColumn(name="classSectionId", insertable=true, updatable=true) 
	public StudyClass getClassSection() {
		return classSection;
	}
	
	public void setClassSection(StudyClass classSection) {
		this.classSection = classSection;
	}
	@OneToOne
    @JoinColumn(name="studySubjectId")
	public StudySubject getStudySubject() {
		return studySubject;
	}
	public void setStudySubject(StudySubject studySubject) {
		this.studySubject = studySubject;
	}
	
	public int getPeriodsPerWeek() {
		return periodsPerWeek;
	}
	public void setPeriodsPerWeek(int periodsPerWeek) {
		this.periodsPerWeek = periodsPerWeek;
	}
	public int getSubjectPriority() {
		return subjectPriority;
	}
	public void setSubjectPriority(int subjectPriority) {
		this.subjectPriority = subjectPriority;
	}
	
	public int getContinuousPeriodsCount() {
		return continuousPeriodsCount;
	}

	public void setContinuousPeriodsCount(int continuousPeriodsCount) {
		this.continuousPeriodsCount = continuousPeriodsCount;
	}

	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	/**
	 * Constructor for Student.
	 */
	public ClassSubjectsSettings() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof ClassSubjectsSettings)) {
			return false;
		} else {

			ClassSubjectsSettings student = (ClassSubjectsSettings) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(classSection, student.getClassSection());
			return builder.isEquals();
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
		buffer.append(getId()).append(" ");
		buffer.append(classSection).append(" ");
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895).append(this.classSection)
                .append(this.classSection).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    //For Sorting students  alphabetically -- seshu
    public int compareTo(Object object) {
        return 0;
    }
   	
	@Transient
    public String getStudentId()
    {
        return "S"+getStrId();
    }
}
