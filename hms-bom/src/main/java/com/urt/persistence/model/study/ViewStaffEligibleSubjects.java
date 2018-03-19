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
 *  1.0  July 14, 2010        Siva G           	Created
/********************************************************************/

@Entity
@Table(name = "vw_staffEligibleSubjects")
public class ViewStaffEligibleSubjects implements Serializable{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
    private long staffEligibleId;
    private long academicYearId;
    private long studySubjectId;
    private long staffId;
    private long accountId;
    private long custId;
    private boolean activeStatus;
    private String subjectName;
   

	public ViewStaffEligibleSubjects()
    {
        super();
    }
  
  
   	/**
	 * @return the custId
	 */
	@Column(name = "custId", nullable = true, length = 10)
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
	@Column(name = "academicYearId", nullable = true, length = 10)
	public long getAcademicYearId() {
		return academicYearId;
	}


	/**
	 * @return the studySubjectId
	 */
	@Column(name = "studySubjectId", nullable = true, length = 10)
	public long getStudySubjectId() {
		return studySubjectId;
	}


	/**
	 * @return the staffId
	 */
	@Column(name = "staffId", nullable = true, length = 10)
	public long getStaffId() {
		return staffId;
	}


	/**
	 * @return the accountId
	 */
	@Column(name = "accountId", nullable = true, length = 10)
	public long getAccountId() {
		return accountId;
	}


	/**
	 * @return the activeStatus
	 */
	@Column(name = "activeStatus", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isActiveStatus() {
		return activeStatus;
	}


	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}


	/**
	 * @param studySubjectId the studySubjectId to set
	 */
	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
	}


	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}


	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}


	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}


	/**
	 * @return the subjectName
	 */
	@Column(name = "subjectName", nullable = true, length = 250)
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

     @Id
	 @Column(name = "staffEligibleId", nullable = false, length = 10)
	
	public long getStaffEligibleId() {
		return staffEligibleId;
	}

	/**
	 * @param staffEligibleId the staffEligibleId to set
	 */
	public void setStaffEligibleId(long staffEligibleId) {
		this.staffEligibleId = staffEligibleId;
	}
	

}