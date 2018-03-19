package com.urt.persistence.model.fee;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewStudentsExcessPaymentDetails.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * April 28, 2016 Ganesh Created /
 ********************************************************************/

@Entity
@Table(name = "vw_studentConcessionClassFees")
public class ViewStudentsConcessionClassFeeDetails implements Serializable,Cloneable, Comparable<Object> {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long concessionId;
	private long feeId;
	private double feeAmount;
	private long custId;
	private long feeTypeId;
	private long schoolTermId;
	private long classId;
	private long academicYearId;
	private long categoryId;
	private long studentId;
	private int rollNumber;
	private long accountId;
	private String status;
	private String description;
	private long classSectionId;
	private double concessionAmount;
	private long feeSettingId;
	
	
	public long getFeeSettingId() {
		return feeSettingId;
	}

	public void setFeeSettingId(long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}

	public long getConcessionId() {
		return concessionId;
	}

	public void setConcessionId(long concessionId) {
		this.concessionId = concessionId;
	}

	@Id
	public long getFeeId() {
		return feeId;
	}

	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}

	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public long getFeeTypeId() {
		return feeTypeId;
	}

	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

	public long getSchoolTermId() {
		return schoolTermId;
	}

	public void setSchoolTermId(long schoolTermId) {
		this.schoolTermId = schoolTermId;
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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getClassSectionId() {
		return classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	public double getConcessionAmount() {
		return concessionAmount;
	}

	public void setConcessionAmount(double concessionAmount) {
		this.concessionAmount = concessionAmount;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentsExcessPaymentDetails))
			return false;
		return false;

	}

	public int hashCode() {
		int result = 0;

		return result;
	}

	public String toString() {

		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);

		return buffer.toString();
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
