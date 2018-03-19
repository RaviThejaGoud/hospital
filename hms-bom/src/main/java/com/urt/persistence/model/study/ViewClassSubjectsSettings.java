package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewGroupType.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Nov 04, 2010     Narahari          	Created
/********************************************************************/

@Entity
@Table(name = "vw_classSubjectsSettings")
public class ViewClassSubjectsSettings  implements Serializable,Cloneable,Comparable {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long classSectionId;
     private long academicYearId;
     private String className;
     private String section;
     private String subjectName;
     private long custId;
     private long subjectId;
     private long classId;
     private String subjectNumber;
     private int sortingOrder;
     private int periodsPerWeek;
     private int subjectPriority;
     private int continuousPeriodsCount;
     private long classSubjectSettingId;
     private int classSortingOrder;
     private int totalAssignedSubjectPeriodsCount;
     protected int noOfSections;
     private String staffHandlePeriodsDetails;
     
     
    public ViewClassSubjectsSettings(long subjectId, String subjectName) {
		super();
		this.subjectName = subjectName;
		this.subjectId = subjectId;
	}
	@Transient
    public String getStaffHandlePeriodsDetails() {
		return staffHandlePeriodsDetails;
	}
	public void setStaffHandlePeriodsDetails(String staffHandlePeriodsDetails) {
		this.staffHandlePeriodsDetails = staffHandlePeriodsDetails;
	}
	public int getNoOfSections() {
		return noOfSections;
	}
	public void setNoOfSections(int noOfSections) {
		this.noOfSections = noOfSections;
	}
	public int getClassSortingOrder() {
		return classSortingOrder;
	}
	public void setClassSortingOrder(int classSortingOrder) {
		this.classSortingOrder = classSortingOrder;
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
	public int getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	/** default constructor */
    public ViewClassSubjectsSettings()
    {
        super();
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

	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public String getSubjectNumber() {
		return subjectNumber;
	}
	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
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
	@Id
	@Column(name="classSubjectSettingId",nullable=false,updatable=false,unique=true)
	public long getClassSubjectSettingId() {
		return classSubjectSettingId;
	}
	public void setClassSubjectSettingId(long classSubjectSettingId) {
		this.classSubjectSettingId = classSubjectSettingId;
	}
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        } else {
        	return false;
        }
                
    }

    public int hashCode() {
        int result = 0;
        
        return result;
    }
   
    
    public String toString() {
        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        buffer.append(" Class Name: ").append(this.className);
        return buffer.toString();
    }
    //Changed by seshu. Used in time table generation
    public int compareTo(Object object) {
    	ViewClassSubjectsSettings target=(ViewClassSubjectsSettings)object;
		if(!ObjectFunctions.isNullOrEmpty(target) && !ObjectFunctions.isNullOrEmpty(this)){
			if(this.getClassSortingOrder() > target.getClassSortingOrder())
				return 1;
			else if(this.getClassSortingOrder() == target.getClassSortingOrder()){
				if(this.getSortingOrder() >= target.getSortingOrder())
					return 1;
				else
					return 0;
			}else
				return 0;
        }
		return 0;
    }
    @Transient
	public int getTotalAssignedSubjectPeriodsCount() {
		return totalAssignedSubjectPeriodsCount;
	}
	public void setTotalAssignedSubjectPeriodsCount(
			int totalAssignedSubjectPeriodsCount) {
		this.totalAssignedSubjectPeriodsCount = totalAssignedSubjectPeriodsCount;
	}
	
	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName().trim()+"-"+getSection();
		}
		return getClassName();
	}
}
      
