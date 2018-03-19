package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


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
@Table(name = "vw_classSectionDetails")
public class ViewClassSectionDetails  implements Serializable,Cloneable,Comparable {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     
     private long classId;
     private long classSectionId;
     private String className;
     private long academicYearId;
     private long custId;
     private long sortingOrder;
     private long noOfSections;
     private boolean higherStandard;
     private int filledSeats;
     private String section;
     private int sectionCapacity; 
     private String groupNumber;
     private long mediumId;
     private long syllabusTypeId;
     private String classMedium;
     private String syllabusTypeName;
     private String syllabusTypeDescription;
     private String classAndSection;
     protected String admissionsOpen;
     
    /** default constructor */
    public ViewClassSectionDetails()
    {
        super();
    }
    
    
    
	public String getAdmissionsOpen() {
		return admissionsOpen;
	}



	public void setAdmissionsOpen(String admissionsOpen) {
		this.admissionsOpen = admissionsOpen;
	}



	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
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
	 * @return the classSectionId
	 */
	@Id
	@Column(name="classSectionId")
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



	/**
	 * @return the sortingOrder
	 */
	public long getSortingOrder() {
		return sortingOrder;
	}



	/**
	 * @param sortingOrder the sortingOrder to set
	 */
	public void setSortingOrder(long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}



	/**
	 * @return the noOfSections
	 */
	public long getNoOfSections() {
		return noOfSections;
	}



	/**
	 * @param noOfSections the noOfSections to set
	 */
	public void setNoOfSections(long noOfSections) {
		this.noOfSections = noOfSections;
	}



	/**
	 * @return the higherStandard
	 */
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
	 * @return the filledSeats
	 */
	public int getFilledSeats() {
		return filledSeats;
	}



	/**
	 * @param filledSeats the filledSeats to set
	 */
	public void setFilledSeats(int filledSeats) {
		this.filledSeats = filledSeats;
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
	 * @return the sectionCapacity
	 */
	public int getSectionCapacity() {
		return sectionCapacity;
	}



	/**
	 * @param sectionCapacity the sectionCapacity to set
	 */
	public void setSectionCapacity(int sectionCapacity) {
		this.sectionCapacity = sectionCapacity;
	}



	/**
	 * @return the groupNumber
	 */
	public String getGroupNumber() {
		return groupNumber;
	}



	/**
	 * @param groupNumber the groupNumber to set
	 */
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}



	/**
	 * @return the mediumId
	 */
	public long getMediumId() {
		return mediumId;
	}



	/**
	 * @param mediumId the mediumId to set
	 */
	public void setMediumId(long mediumId) {
		this.mediumId = mediumId;
	}



	/**
	 * @return the syllabusTypeId
	 */
	public long getSyllabusTypeId() {
		return syllabusTypeId;
	}



	/**
	 * @param syllabusTypeId the syllabusTypeId to set
	 */
	public void setSyllabusTypeId(long syllabusTypeId) {
		this.syllabusTypeId = syllabusTypeId;
	}



	/**
	 * @return the classMedium
	 */
	public String getClassMedium() {
		return classMedium;
	}



	/**
	 * @param classMedium the classMedium to set
	 */
	public void setClassMedium(String classMedium) {
		this.classMedium = classMedium;
	}



	/**
	 * @return the syllabusTypeName
	 */
	public String getSyllabusTypeName() {
		return syllabusTypeName;
	}



	/**
	 * @param syllabusTypeName the syllabusTypeName to set
	 */
	public void setSyllabusTypeName(String syllabusTypeName) {
		this.syllabusTypeName = syllabusTypeName;
	}



	/**
	 * @return the syllabusTypeDescription
	 */
	public String getSyllabusTypeDescription() {
		return syllabusTypeDescription;
	}



	/**
	 * @param syllabusTypeDescription the syllabusTypeDescription to set
	 */
	public void setSyllabusTypeDescription(String syllabusTypeDescription) {
		this.syllabusTypeDescription = syllabusTypeDescription;
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
        buffer.append(" Class Name: ").append(this.className+" "+this.section);
        return buffer.toString();
    }
    public int compareTo(Object object) {
    	ViewClassSectionDetails target = (ViewClassSectionDetails) object;
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
    @Transient
    public int getAvailableSeats(){
 	   return this.getSectionCapacity() - this.getFilledSeats();
    }
}
      
