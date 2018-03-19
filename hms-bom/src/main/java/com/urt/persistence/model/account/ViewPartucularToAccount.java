package com.urt.persistence.model.account;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_partucularToAccount")
public class ViewPartucularToAccount implements Serializable,Cloneable, Comparable<Object> {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long feeTypeId;
	private String feeType;
	private long academicYearId;
	private long fpaId;
	private long finAccountId;
	private long financialYearId;
	private long custId;
	
	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getFeeTypeId() {
		return feeTypeId;
	}

	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public long getFpaId() {
		return fpaId;
	}

	public void setFpaId(long fpaId) {
		this.fpaId = fpaId;
	}

	public long getFinAccountId() {
		return finAccountId;
	}

	public void setFinAccountId(long finAccountId) {
		this.finAccountId = finAccountId;
	}

	public long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(long financialYearId) {
		this.financialYearId = financialYearId;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
