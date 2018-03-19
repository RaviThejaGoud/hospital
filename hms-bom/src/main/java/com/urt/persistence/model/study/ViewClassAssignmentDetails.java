package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.account.FinancialCashBook;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewGroupType.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Narahari Created /
 ********************************************************************/

@Entity
@Table(name = "vw_classAssignmentDetails")
public class ViewClassAssignmentDetails implements Serializable,Cloneable, Comparable {

     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     
     private long classId;
     private long classSectionId;
     private String className;
     private long academicYearId;
     private long custId;
     private String assignmentDescription;
     private String classAndSection;
     private String createdBy;
     private String subjectName;
     private long assignmentId;
     private long subjectId;
     private Date assignmentDate;
     private long createdById;
     private String isDocsUpload;
     private String status;
     private String assignmentStatus;
     
     public ViewClassAssignmentDetails(){
    	 
     }
     public ViewClassAssignmentDetails(long subjectId, String subjectName) {
 		super();
 		this.subjectName = subjectName;
 		this.subjectId = subjectId;
 	}
	public String getIsDocsUpload() {
		return isDocsUpload;
	}
	public void setIsDocsUpload(String isDocsUpload) {
		this.isDocsUpload = isDocsUpload;
	}
	public long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	/**
	 * @return the subjectId
	 */
	public long getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
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
	@Column(name="classSectionId",nullable=false,updatable=false,unique=true)
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

 
 


	public String getAssignmentDescription() {
		return assignmentDescription;
	}
	public void setAssignmentDescription(String assignmentDescription) {
		this.assignmentDescription = assignmentDescription;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        } else {
        	return false;
        }

        
    }

    @Override
	public int hashCode() {
        int result = 0;
        
        return result;
    }
	/*@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}*/
    public int compareTo(Object object) {
		ViewClassAssignmentDetails target = (ViewClassAssignmentDetails) object;
		if(!ObjectFunctions.isNullOrEmpty(target) && !ObjectFunctions.isNullOrEmpty(target.getAssignmentDate())){
			if(this.getAssignmentDate().compareTo(target.getAssignmentDate())>0){
				return 1;
			}else if(this.getAssignmentDate().compareTo(target.getAssignmentDate())<0){
				return 0;
			}else if(this.getAssignmentDate().compareTo(target.getAssignmentDate())==0){
				return -1;
			}
		}
		return 0;
	}
	@Id
	public long getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(long assignmentId) {
		this.assignmentId = assignmentId;
	}
  
	@Transient
    public String getAssignmentDateStr() {
	return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.assignmentDate);

    }
	
	@Transient
    public String getAssignmentDateYYMMStr() {
	return DateFormatter.formatDate(DateFormatter.YYMMDD_HHMM_PATTERN, this.assignmentDate);

    }
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the assignmentStatus
	 */
	public String getAssignmentStatus() {
		return assignmentStatus;
	}
	/**
	 * @param assignmentStatus the assignmentStatus to set
	 */
	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}
	
}
