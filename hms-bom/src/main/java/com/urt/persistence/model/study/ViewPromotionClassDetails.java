package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

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
@Table(name = "vw_promotionClassesDetails")
public class ViewPromotionClassDetails implements Serializable,Cloneable,Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private String className;
	private String section;
	private long custId;
	private long academicYearId;
	private String promoteClassName;
	private String promoteSectionName;
	private long promotionId;
	private long classSectionId;
	private long sortingOrder;
	private String medium;
	private String promoteClassMedium;
	private long promoteClassMediumId;
	private long mediumId;
	private long classId;
	private boolean promoteProcessCompleted;
	
	
	
	/**
	 * @return the promoteProcessCompleted
	 */
	 @Column(name = "promoteProcessCompleted", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	 @Type(type="yes_no")
	public boolean isPromoteProcessCompleted() {
		return promoteProcessCompleted;
	}

	/**
	 * @param promoteProcessCompleted the promoteProcessCompleted to set
	 */
	public void setPromoteProcessCompleted(boolean promoteProcessCompleted) {
		this.promoteProcessCompleted = promoteProcessCompleted;
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
	 * @return the promoteClassMediumId
	 */
	public long getPromoteClassMediumId() {
		return promoteClassMediumId;
	}

	/**
	 * @param promoteClassMediumId the promoteClassMediumId to set
	 */
	public void setPromoteClassMediumId(long promoteClassMediumId) {
		this.promoteClassMediumId = promoteClassMediumId;
	}

	/**
	 * @return the medium
	 */
	public String getMedium() {
		return medium;
	}

	/**
	 * @param medium the medium to set
	 */
	public void setMedium(String medium) {
		this.medium = medium;
	}

	/**
	 * @return the promoteClassMedium
	 */
	public String getPromoteClassMedium() {
		return promoteClassMedium;
	}

	/**
	 * @param promoteClassMedium the promoteClassMedium to set
	 */
	public void setPromoteClassMedium(String promoteClassMedium) {
		this.promoteClassMedium = promoteClassMedium;
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
	 * @return the promoteClassName
	 */
	public String getPromoteClassName() {
		return promoteClassName;
	}

	/**
	 * @param promoteClassName the promoteClassName to set
	 */
	public void setPromoteClassName(String promoteClassName) {
		this.promoteClassName = promoteClassName;
	}

	/**
	 * @return the promoteSectionName
	 */
	public String getPromoteSectionName() {
		return promoteSectionName;
	}

	/**
	 * @param promoteSectionName the promoteSectionName to set
	 */
	public void setPromoteSectionName(String promoteSectionName) {
		this.promoteSectionName = promoteSectionName;
	}

	/**
	 * @return the promotionId
	 */
	public long getPromotionId() {
		return promotionId;
	}

	/**
	 * @param promotionId the promotionId to set
	 */
	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}

	/**
	 * @return the classSectionId
	 */
	@Id
	@Column( name="classSectionId", unique=false, nullable=false, updatable=false )
	public long getClassSectionId() {
		return classSectionId;
	}

	/**
	 * @param classSectionId the classSectionId to set
	 */
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Override
	public int compareTo(Object object) {
    	ViewPromotionClassDetails target = (ViewPromotionClassDetails) object;
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
	@Override
	public boolean equals(Object o) {
		if (this == o) {
        	return true;
        } else {
        	return false;
        }
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
    public String getPromotedClassAndSection()
    {
		if(StringFunctions.isNotNullOrEmpty(getPromoteClassName()) && StringFunctions.isNotNullOrEmpty(getPromoteSectionName()))
			return getPromoteClassName()+" - "+getPromoteSectionName();
		else if(StringFunctions.isNotNullOrEmpty(getPromoteClassName()) && StringFunctions.isNullOrEmpty(getPromoteSectionName()))
			return getPromoteClassName();
		else
			return "- select class -";
	}
	/*@Transient
    public String getClassSectionAndMediumId()
    {
		if(StringFunctions.isNullOrEmpty(getClassAndSection()) && getMediumId() == 0){
			return "";
		}else if(StringFunctions.isNullOrEmpty(getClassAndSection())){
			return ":"+getMediumId();
		}else if(getMediumId() == 0)
			return getClassAndSection();
		else
			return getClassAndSection()+":"+ getMediumId();
    }*/
	@Transient
    public String getPromotableClassSectionAndMedium()
    {
		if(StringFunctions.isNullOrEmpty(getPromotedClassAndSection()) && StringFunctions.isNullOrEmpty(getPromoteClassMedium()) ){
			return "";
		}else if(StringFunctions.isNullOrEmpty(getPromotedClassAndSection())){
			return "";
		}else if(StringFunctions.isNullOrEmpty(getPromoteClassMedium()))
			return getPromotedClassAndSection();
		else if("COURSE COMPLETED".equalsIgnoreCase(getPromotedClassAndSection()))
				return getPromotedClassAndSection();
			else
				return getPromotedClassAndSection()+" ("+getPromoteClassMedium()+")";
    }
}
