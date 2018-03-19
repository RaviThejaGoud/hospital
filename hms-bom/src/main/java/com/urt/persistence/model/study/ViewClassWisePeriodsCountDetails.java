package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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
@Table(name = "vw_classWisePeriodsCountDetails")
public class ViewClassWisePeriodsCountDetails  implements Serializable,Cloneable,Comparable {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     
     private long classSectionId;
     private long timeTableId;
     private long dayId;
     private String dayName;
     private String periodName;
     private String classAndSection;
     private int periodsCount;
     private long academicYearId;
     private long custId;
     private String className;
     private String section;
     private int sortingOrder;
     private long classId;
     
     
    public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
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
    public ViewClassWisePeriodsCountDetails()
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


	@Id
	@Column(name="timeTableId",nullable=false,updatable=false,unique=true)
	public long getTimeTableId() {
		return timeTableId;
	}




	public void setTimeTableId(long timeTableId) {
		this.timeTableId = timeTableId;
	}




	public long getDayId() {
		return dayId;
	}




	public void setDayId(long dayId) {
		this.dayId = dayId;
	}




	public String getDayName() {
		return dayName;
	}




	public void setDayName(String dayName) {
		this.dayName = dayName;
	}




	public String getPeriodName() {
		return periodName;
	}




	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}




	public String getClassAndSection() {
		return classAndSection;
	}




	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}




	public int getPeriodsCount() {
		return periodsCount;
	}




	public void setPeriodsCount(int periodsCount) {
		this.periodsCount = periodsCount;
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
        buffer.append(" Class Name: ").append(this.classAndSection);
        return buffer.toString();
    }
    public int compareTo(Object object) {
    	ViewClassWisePeriodsCountDetails target = (ViewClassWisePeriodsCountDetails) object;
    	if (target != null && this != null)
        {
    		if(this.getSortingOrder() > target.getSortingOrder())
    				return 1;
    		else if(this.getSortingOrder() == target.getSortingOrder()){
    			if(this.getSection().compareTo(target.getSection()) > 0)
    				return 1;
    		}
    		else
    			return 0;
        }
		return 0;
	
    }
	
}
      
