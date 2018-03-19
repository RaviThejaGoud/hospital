package com.urt.persistence.model.webservice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @create new table customer.
 */

@Entity
@Table(name = "vw_Fee")
public class ViewFee   implements Serializable, Cloneable,Comparable {
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

    
    protected long id;	
    private int version;
	protected Date lastAccessDate;
	protected Date lastUpdatedDate;
    protected Date createdDate;
    private long createdById;
	private long lastUpdatedById;
	
    private long  custId; 
    protected long classId;
    protected String status;
    protected double feeAmount;
    protected long academicYearId;
    protected long schoolTermId;
    protected long feeTypeId;
    private long  categoryId;
    private long routeBoardingPointId;
    
    
    
     /**
	 * @return the routeBoardingPointId
	 */
	public long getRouteBoardingPointId() {
		return routeBoardingPointId;
	}

	/**
	 * @param routeBoardingPointId the routeBoardingPointId to set
	 */
	public void setRouteBoardingPointId(long routeBoardingPointId) {
		this.routeBoardingPointId = routeBoardingPointId;
	}

	@Id
     public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}

	public long getLastUpdatedById() {
		return lastUpdatedById;
	}

	public void setLastUpdatedById(long lastUpdatedById) {
		this.lastUpdatedById = lastUpdatedById;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public long getSchoolTermId() {
		return schoolTermId;
	}

	public void setSchoolTermId(long schoolTermId) {
		this.schoolTermId = schoolTermId;
	}

	public long getFeeTypeId() {
		return feeTypeId;
	}

	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

	/**
	 * @return the categoryId
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'S'")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public ViewFee() {
        
    }
	public ViewFee(long id) {
        setId(id);
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
	 * @return the feeAmount
	 */
	@Column(name = "feeAmount", nullable = true, length = 10,columnDefinition="double default '0'")
	public double getFeeAmount() {
		return feeAmount;
	}

	/**
	 * @param feeAmount the feeAmount to set
	 */
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}

