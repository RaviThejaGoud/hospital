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
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "combinedClassSubjects")
public class CombinedClassSubjects extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	private long custId;
	private long academicYearId;
	private List<Staff> teachers;
	private List<StudyClass> classSections;
	private StudySubject studySubject;
	
	
	
	@OneToOne
    @JoinColumn(name="studySubjectId")
	public StudySubject getStudySubject() {
		return studySubject;
	}

	public void setStudySubject(StudySubject studySubject) {
		this.studySubject = studySubject;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@ManyToMany
    @JoinTable(name = "combinedClassSubjectTeachers",
       joinColumns = { @JoinColumn(name = "combinedClassSubjectId") },
       inverseJoinColumns = { @JoinColumn(name = "teacherId") })
	public List<Staff> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Staff> teachers) {
		this.teachers = teachers;
	}

	@ManyToMany
    @JoinTable(name = "combinedClassSections",
       joinColumns = { @JoinColumn(name = "combinedClassSubjectId") },
       inverseJoinColumns = { @JoinColumn(name = "classSectionId") })
	public List<StudyClass> getClassSections() {
		return classSections;
	}

	public void setClassSections(List<StudyClass> classSections) {
		this.classSections = classSections;
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
	public CombinedClassSubjects() {
		super();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof CombinedClassSubjects)) {
			return false;
		} else {

			CombinedClassSubjects com = (CombinedClassSubjects) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(getStudySubject(), com.getStudySubject());
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
		buffer.append(getStudySubject()).append(" ");
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895).append(this.id)
                .append(this.studySubject).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
    	CombinedClassSubjects combSub=(CombinedClassSubjects)object;
        if(!ObjectFunctions.isNullOrEmpty(combSub) && !ObjectFunctions.isNullOrEmpty(combSub.getStudySubject()) && 
        		!ObjectFunctions.isNullOrEmpty(this) && !ObjectFunctions.isNullOrEmpty(this.getStudySubject())){
        	if(combSub.getStudySubject().getSortingOrder() > 0 && this.getStudySubject().getSortingOrder() > 0){
        		if(this.getStudySubject().getSortingOrder() >= combSub.getStudySubject().getSortingOrder())
    				return 1;
    			else 
    				return 0;
        	}else if(StringFunctions.isNotNullOrEmpty(combSub.getStudySubject().getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(this.getStudySubject().getSubjectNumber())){
    			return this.getStudySubject().getSubjectNumber().compareToIgnoreCase(combSub.getStudySubject().getSubjectNumber());
    		}else{
    			return this.getStudySubject().getName().compareToIgnoreCase(combSub.getStudySubject().getName());			
    		}
        }
        return 0;
    }
    
    @Transient
    public String getCombinedClassNames(){
    	if(ObjectFunctions.isNotNullOrEmpty(getClassSections())){
    		StringBuffer classNames= new StringBuffer();
    		for(StudyClass studyClass: getClassSections()){
    			if(classNames.length() > 0)
    				classNames.append(", "+studyClass.getClassAndSection());
    			else
    				classNames.append(studyClass.getClassAndSection());
    		}
    		return classNames.toString();
    	}
    	return "";
    }
    @Transient
    public String getStaffFullNames(){
    	if(ObjectFunctions.isNotNullOrEmpty(getTeachers())){
    		StringBuffer staffNames= new StringBuffer();
    		for(Staff staff: getTeachers()){
    			if(staffNames.length() > 0)
    				staffNames.append(", "+staff.getFullPersonName());
    			else
    				staffNames.append(staff.getFullPersonName());
    		}
    		return staffNames.toString();
    	}
    	return "";
    }
    @Transient
    public String getCombinedClassSectionIds(){
    	if(ObjectFunctions.isNotNullOrEmpty(getClassSections())){
    		StringBuffer classNames= new StringBuffer();
    		for(StudyClass studyClass: getClassSections()){
    			if(classNames.length() > 0)
    				classNames.append(","+studyClass.getId());
    			else
    				classNames.append(studyClass.getId());
    		}
    		return classNames.toString();
    	}
    	return "0";
    }
    @Transient
    public String getCombinedClassStaffIds(){
    	if(ObjectFunctions.isNotNullOrEmpty(getTeachers())){
    		StringBuffer staffNames= new StringBuffer();
    		for(Staff staff: getTeachers()){
    			if(staffNames.length() > 0)
    				staffNames.append(","+staff.getId());
    			else
    				staffNames.append(staff.getId());
    		}
    		return staffNames.toString();
    	}
    	return "0";
    }
}
