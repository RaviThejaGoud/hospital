package com.urt.persistence.model.study;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.string.StringFunctions;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewGroupType.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Narahrai Created /
 ********************************************************************/

@Entity
@Table(name = "vw_onlineApplicationStudentClassFees")
public class ViewOnlineApplicationStudentClassFees implements Serializable,Cloneable, Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	
	private static final long serialVersionUID = 1L;
	public DecimalFormat priceFormatter = new DecimalFormat("##,##,###.##");
	
	private long feeId;
	private long custId;
	private long schoolTermId;
	private long academicYearId;
	private long classId;
	private long feeTypeId;
	private long categoryId;
	private String termName;
	private Date toDate;
	private String toMonthName;
	private Date fromDate;
	private String fromMonthName;
	private Date dueDate;
	private String feeType;
	private double feeAmount;
	private String categoryName;
	private long feeSettingId;
	private String settingName;
	private boolean settingType;
	private String status;
	private double paymentAmount;
	private double discountAmount;
	private List pendingStudentList;
	private Date paymentDate;
	private double payableAmount;
	protected String paymentStatus;
	
	
	
	/**
	 * @return the paymentStatus
	 */
	@Transient
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the payableAmount
	 */
	@Transient
	public double getPayableAmount() {
		return payableAmount;
	}

	/**
	 * @param payableAmount the payableAmount to set
	 */
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * @return the paymentDate
	 */
	@Transient
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the pendingStudentList
	 */
	@Transient
	public List getPendingStudentList() {
		return pendingStudentList;
	}

	/**
	 * @param pendingStudentList the pendingStudentList to set
	 */
	public void setPendingStudentList(List pendingStudentList) {
		this.pendingStudentList = pendingStudentList;
	}

	@Transient
	public double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Transient
	public double getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public long getFeeSettingId() {
		return feeSettingId;
	}

	public void setFeeSettingId(long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	@Column(name = "settingType", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	public boolean isSettingType() {
		return settingType;
	}

	public void setSettingType(boolean settingType) {
		this.settingType = settingType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the categoryId
	 */
	@Column(name = "categoryId", nullable = true, updatable = false)
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	@Column(name = "categoryName", nullable = true, updatable = false)
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the dueDate
	 */
	@Column(name = "dueDate", nullable = true, updatable = false)
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the classId
	 */
	@Column(name = "classId", nullable = true, updatable = false)
	public long getClassId() {
		return classId;
	}

	/**
	 * @param classId
	 *            the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}

	/**
	 * @return the academicYearId
	 */
	@Column(name = "academicYearId", nullable = true, updatable = false)
	public long getAcademicYearId() {
		return academicYearId;
	}

	/**
	 * @param academicYearId
	 *            the academicYearId to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	/**
	 * @return the fromMonthName
	 */
	@Column(name = "fromMonthName", nullable = true, updatable = false)
	public String getFromMonthName() {
		return fromMonthName;
	}

	/**
	 * @param fromMonthName
	 *            the fromMonthName to set
	 */
	public void setFromMonthName(String fromMonthName) {
		this.fromMonthName = fromMonthName;
	}

	/**
	 * @return the toMonthName
	 */
	@Column(name = "toMonthName", nullable = true, updatable = false)
	public String getToMonthName() {
		return toMonthName;
	}

	/**
	 * @param toMonthName
	 *            the toMonthName to set
	 */
	public void setToMonthName(String toMonthName) {
		this.toMonthName = toMonthName;
	}

	/**
	 * @return the termName
	 */
	@Column(name = "termName", nullable = true, updatable = false)
	public String getTermName() {
		return termName;
	}

	/**
	 * @param termName
	 *            the termName to set
	 */
	public void setTermName(String termName) {
		this.termName = termName;
	}

	/**
	 * @return the fromDate
	 */
	@Column(name = "fromDate", nullable = true, updatable = false)
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	@Column(name = "toDate", nullable = false, updatable = false)
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the feeTypeId
	 */
	@Column(name = "feeTypeId", nullable = false, updatable = false, columnDefinition = "int default 0")
	public long getFeeTypeId() {
		return feeTypeId;
	}

	/**
	 * @param feeTypeId
	 *            the feeTypeId to set
	 */
	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

	/**
	 * @return the schoolTermId
	 */
	@Column(name = "schoolTermId", nullable = false, updatable = false, columnDefinition = "int default 0")
	public long getSchoolTermId() {
		return schoolTermId;
	}

	/**
	 * @param schoolTermId
	 *            the schoolTermId to set
	 */
	public void setSchoolTermId(long schoolTermId) {
		this.schoolTermId = schoolTermId;
	}

	/**
	 * @return the feeId
	 */
	@Id
	@Column(name = "feeId", unique = false, nullable = false, updatable = false)
	public long getFeeId() {
		return feeId;
	}

	/**
	 * @param feeId
	 *            the feeId to set
	 */
	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}

	/**
	 * @return the feeType
	 */
	@Column(name = "feeType", nullable = false, updatable = false)
	public String getFeeType() {
		return feeType;
	}

	/**
	 * @param feeType
	 *            the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	/**
	 * @return the feeAmount
	 */
	@Column(name = "feeAmount", nullable = false, updatable = false, columnDefinition = "int default 0")
	public double getFeeAmount() {
		return feeAmount;
	}

	@Transient
	public String getFeeAmountFormatted() {
		return priceFormatter.format(getFeeAmount());
	}

	/**
	 * @param feeAmount
	 *            the feeAmount to set
	 */
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public ViewOnlineApplicationStudentClassFees() {
		super();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewOnlineApplicationStudentClassFees))
			return false;
		return false;

	}

	@Override
	public int hashCode() {
		int result = 0;

		return result;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Transient
	public String getDueDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getDueDate());
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transient
	public String getMonthOfTermName() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getTermName())) {
			ret.append(getTermName());
			ret.append("(");
		}
		if (!StringFunctions.isNullOrEmpty(getFromMonthName())) {
			ret.append(getFromMonthName().substring(0, 3));
		}
		if (!StringFunctions.isNullOrEmpty(getToMonthName())) {
			ret.append(" - ");
			ret.append(getToMonthName().substring(0, 3));
			ret.append(" ) ");
		}

		return ret.toString().trim();
	}
}
