package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

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
 *  1.0  July 14, 2010        Siva G           	Created
/********************************************************************/

@Entity
@Table(name = "vw_classFeeDetails")
public class ViewClassFeeDetails implements Serializable,Cloneable,Comparable {

     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long feeId;
     private long custId;
     private long schoolTermId;
     private long academicYearId;
     private long classId;
     private long feeTypeId;
     private String termName;
     private Date toDate;
     private String toMonthName;
     private Date fromDate;
     private String fromMonthName;
     private Date dueDate;
     private String feeType;
     private double feeAmount;
     private boolean applToNewStuds;
     private long routeBoardingPointId;
     private boolean isTransportFeeByBoardingPoint;
     private String className;
     private String section;
     protected Date dueDate1;
     protected Date dueDate2;
     private long priorityPosition;
     private Character committedFeeStatus;
     private long classSectionId;
     private long categoryId;
     
     /*
      private String description;
      private String className;
      private String ddNumber;
     private Date paymentDate;
     private String paymentStatus;
     private String paymentType;*/
     
     public long getCategoryId() {
 		return categoryId;
 	}

 	public void setCategoryId(long categoryId) {
 		this.categoryId = categoryId;
 	}
    
	public long getPriorityPosition() {
		return priorityPosition;
	}

	public void setPriorityPosition(long priorityPosition) {
		this.priorityPosition = priorityPosition;
	}
	public long getClassSectionId() {
		return classSectionId;
	}


	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Column(name = "committedFeeStatus", length = 1,columnDefinition="char(1) default 'N'")
	public Character getCommittedFeeStatus() {
		return committedFeeStatus;
	}


	public void setCommittedFeeStatus(Character committedFeeStatus) {
		this.committedFeeStatus = committedFeeStatus;
	}


	public ViewClassFeeDetails()
    {
        super();
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


	public boolean isTransportFeeByBoardingPoint() {
		return isTransportFeeByBoardingPoint;
	}


	public void setTransportFeeByBoardingPoint(boolean isTransportFeeByBoardingPoint) {
		this.isTransportFeeByBoardingPoint = isTransportFeeByBoardingPoint;
	}


	public long getRouteBoardingPointId() {
		return routeBoardingPointId;
	}


	public void setRouteBoardingPointId(long routeBoardingPointId) {
		this.routeBoardingPointId = routeBoardingPointId;
	}


	@Column(name = "applToNewStuds", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public boolean isApplToNewStuds() {
		return applToNewStuds;
	}


	public void setApplToNewStuds(boolean applToNewStuds) {
		this.applToNewStuds = applToNewStuds;
	}


	/**
	 * @return the feeId
	 */
	@Id
    @Column( name="feeId", unique=false, nullable=false, updatable=false )
	public long getFeeId() {
		return feeId;
	}


	/**
	 * @param feeId the feeId to set
	 */
	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}

	
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}


	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	/**
	 * @return the schoolTermId
	 */
	public long getSchoolTermId() {
		return schoolTermId;
	}


	/**
	 * @param schoolTermId the schoolTermId to set
	 */
	public void setSchoolTermId(long schoolTermId) {
		this.schoolTermId = schoolTermId;
	}


	/**
	 * @return the feeTypeId
	 */
	public long getFeeTypeId() {
		return feeTypeId;
	}


	/**
	 * @param feeTypeId the feeTypeId to set
	 */
	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}


	/**
	 * @return the fromMonthName
	 */
	public String getFromMonthName() {
		return fromMonthName;
	}


	/**
	 * @param fromMonthName the fromMonthName to set
	 */
	public void setFromMonthName(String fromMonthName) {
		this.fromMonthName = fromMonthName;
	}


	/**
	 * @return the toMonthName
	 */
	public String getToMonthName() {
		return toMonthName;
	}


	/**
	 * @param toMonthName the toMonthName to set
	 */
	public void setToMonthName(String toMonthName) {
		this.toMonthName = toMonthName;
	}


	/**
	 * @return the termName
	 */
	public String getTermName() {
		return termName;
	}


	/**
	 * @param termName the termName to set
	 */
	public void setTermName(String termName) {
		this.termName = termName;
	}


	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}


	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}


	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	/**
	 * @return the feeAmount
	 */
	public double getFeeAmount() {
		return feeAmount;
	}

	
	/**
	 * @return the feeDueDate
	 */
	/*public Date getFeeDueDate() {
		return feeDueDate;
	}


	*//**
	 * @param feeDueDate the feeDueDate to set
	 *//*
	public void setFeeDueDate(Date feeDueDate) {
		this.feeDueDate = feeDueDate;
	}*/


	/**
	 * @param feeAmount the feeAmount to set
	 */
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}


	/**
	 * @return the academicYear
	 */
	public long getAcademicYearId() {
		return academicYearId;
	}


	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}


	/**
	 * @return the feeType
	 */
	public String getFeeType() {
		return feeType;
	}


	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
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
	 * @return the classId
	 */
	 @Column(name = "classId", nullable = true, length = 10)
	public long getClassId() {
		return classId;
	}



	/**
	 * @param classId the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}


	//@Override
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}


	//@Override
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	//@Override
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	//@Override
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	/*@Transient
    public String getFeeDueDateStr() {
            return DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, this.getFeeDueDate());
    }*/
	/*@Transient
    public String getPaymentDateStr() {
            return DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, this.getPaymentDate());
    }*/


	public Date getDueDate1() {
		return dueDate1;
	}
	public void setDueDate1(Date dueDate1) {
		this.dueDate1 = dueDate1;
	}
	public Date getDueDate2() {
		return dueDate2;
	}
	public void setDueDate2(Date dueDate2) {
		this.dueDate2 = dueDate2;
	}
	
}