package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


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
@Table(name = "vw_timeTableDetails")
public class ViewTimeTableDetails  implements Serializable,Cloneable,Comparable {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long timeTableId;
     private long classSectionId;
     private long subjectId;
     private int dayId;
     private String periodName;
     private char periodType;
     private long custId;
     private long academicYearId; 
     private long teacherId;
     private String classAndSection;
     private String subjectName;
     private String dayName;
     private String firstName;
     private String lastName;
     private String className;
     private String section;
     private boolean combinedPeriod;
     private long classId;
     private String staffFullName;
     private boolean prioritiesBasedPeriod;
     
     
	 @Column(name = "prioritiesBasedPeriod", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	 @Type(type="yes_no")
    public boolean isPrioritiesBasedPeriod() {
		return prioritiesBasedPeriod;
	}

	public void setPrioritiesBasedPeriod(boolean prioritiesBasedPeriod) {
		this.prioritiesBasedPeriod = prioritiesBasedPeriod;
	}

	public String getStaffFullName() {
		return staffFullName;
	}

	public void setStaffFullName(String staffFullName) {
		this.staffFullName = staffFullName;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	@Column(name = "combinedPeriod", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
    public boolean isCombinedPeriod() {
 		return combinedPeriod;
 	}

 	public void setCombinedPeriod(boolean combinedPeriod) {
 		this.combinedPeriod = combinedPeriod;
 	}
    @Id
 	@Column(name="timeTableId",nullable=false,updatable=false,unique=true)
	public long getTimeTableId() {
		return timeTableId;
	}
	public void setTimeTableId(long timeTableId) {
		this.timeTableId = timeTableId;
	}
	public int getDayId() {
		return dayId;
	}
	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public char getPeriodType() {
		return periodType;
	}
	public void setPeriodType(char periodType) {
		this.periodType = periodType;
	}
	public long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	/** default constructor */
    public ViewTimeTableDetails()
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
    	/*ViewTimeTableDetails target=(ViewTimeTableDetails)object;
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
        }*/
		return 0;
    }
	
}
      
