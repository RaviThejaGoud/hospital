package com.urt.persistence.model.webservice;

import java.io.Serializable;
import java.util.Date;

/*
 * @create new table Fee Type.
 */

public class ViewFeeTerms   implements Serializable, Cloneable,Comparable {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    protected long id;	
    protected String status;
    protected String termName;
    protected Date fromDate;
    protected Date toDate;
    protected Date dueDate;
    protected String fromMonthName;
    protected String toMonthName;
    protected long academicYearId;
    protected Date lastUpdatedDate;
	private long custId;
	protected Long feeSettingId;
	private boolean applyToNewStudents;
	    
	    
	public Long getFeeSettingId() {
		return feeSettingId;
	}
	public void setFeeSettingId(Long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}
    
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}


	public long getCustId() {
		return custId;
	}


	public void setCustId(long custId) {
		this.custId = custId;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getAcademicYearId() {
		return academicYearId;
	}


	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}


	public ViewFeeTerms() {
        
    }
     
    
    public ViewFeeTerms(long id) {
        setId(id);
    }
    
    
	/**
	 * @return the terms
	 */
	
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
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
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
	
	public boolean isApplyToNewStudents() {
		return applyToNewStudents;
	}
	public void setApplyToNewStudents(boolean applyToNewStudents) {
		this.applyToNewStudents = applyToNewStudents;
	}
	/**
	 * @param custId the custId to set
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return the className
	 */
	/**
	 * @return the attendanceDetails
	 */
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}


	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

