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
import com.churchgroup.util.date.DateFunctions;
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
@Table(name = "vw_studentClassFeePaymentDetails")
public class ViewStudentClassFeePaymentDetails implements Serializable,Cloneable, Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	
	private static final long serialVersionUID = 1L;
	public DecimalFormat priceFormatter = new DecimalFormat("##,##,###.##");
	private long id;
	private long studentId;
	private long studentPaymentId;
	private long feeId;
	private long schoolTermId;
	private long feeTypeId;
	private long classId;
	private long academicYearId;
	private long custId;
	private long categoryId;
	private double feeAmount;
	private String feeType;
	private Date fromDate;
	private Date toDate;
	private String fromMonthName;
	private String toMonthName;
	private Date dueDate;
	private String categoryName;
	protected String paymentStatus;
	private String rollNumber;
	private String status;
	private String username;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneNumber;
	private String parentEmail;
	private String termName;
	private double paymentAmount;
	private double discountAmount;
	private long paymentId;
	/*private double paymentTotalAmount;
	private double totalDiscountAmount;*/
	private Date lastUpdatedDate;
	private long feeSettingId;
	private String settingName;
	private String settingType;
	private String description;
	protected List<StudentFeePaidDetails> studentFeePaidDetails;
	
	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the studentPaymentId
	 */
	@Column(name = "studentPaymentId", nullable = false, updatable = false, columnDefinition = "int default 0")
	public long getStudentPaymentId() {
		return studentPaymentId;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
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

	public String getSettingType() {
		return settingType;
	}

	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}

	/**
	 * @return the discountAmount
	 */
	public double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountPaidAmount the discountPaidAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the paidAmount
	 */
	public double getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @param studentPaymentId
	 *            the studentPaymentId to set
	 */
	public void setStudentPaymentId(long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
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
	 * @return the status
	 */
	@Column(name = "status", nullable = true, updatable = false)
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the paymentStatus
	 */
	@Column(name = "paymentStatus", nullable = true, updatable = false, length = 1, columnDefinition = "N")
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus
	 *            the paymentStatus to set
	 */
	@Column(name = "paymentStatus", nullable = false, updatable = false)
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public ViewStudentClassFeePaymentDetails() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentClassFeePaymentDetails))
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

	@Column(name = "studentId", nullable = true, updatable = false)
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@Transient
	public String getDueDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getDueDate());
	}

	@Transient
	public int getFeeDueDates() {
		try {
			if ("P".equalsIgnoreCase(getPaymentStatus())) {
				return -1;
			}
			return DateFunctions.daysBetween(getDueDate(), new Date());
		} catch (Exception ex) {
			return 0;
		}

	}

	@Transient
	public String getIsBetweenFeeDueDays() {
		Date date = new Date();
		if ("P".equalsIgnoreCase(getPaymentStatus())) {
			return "P";
		}
		int feeDueDays = DateFunctions.daysBetween(getDueDate(), date);

		if (feeDueDays >= 0 && feeDueDays <= 14) {
			return "A";
		}
		if (feeDueDays > 14 && feeDueDays <= 30) {
			return "B";
		}
		if (feeDueDays > 30 && feeDueDays <= 60) {
			return "C";
		}
		if (feeDueDays > 60) {
			return "D";
		}
		if (feeDueDays <= 0 && feeDueDays >= -15) {
			return "E";
		}

		return null;
	}

	/*
	 * @Override public int compareTo(Object object) { // TODO Auto-generated
	 * method stub return 0; }
	 */

	/**
	 * @return the rollNumber
	 */
	@Column(name = "rollNumber", nullable = false, updatable = false)
	public String getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber
	 *            the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * @return the username
	 */
	@Column(name = "username", nullable = false, updatable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "firstName", nullable = false, updatable = false)
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "lastName", nullable = false, updatable = false)
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	@Column(name = "middleName", nullable = false, updatable = false)
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the phoneNumber
	 */
	@Column(name = "phoneNumber", nullable = false, updatable = false)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the parentEmail
	 */
	@Column(name = "parentEmail", nullable = false, updatable = false)
	public String getParentEmail() {
		return parentEmail;
	}

	/**
	 * @param parentEmail
	 *            the parentEmail to set
	 */
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
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

	@Transient
	public String getFullPersonName() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getFirstName())) {
			ret.append(getFirstName());
		}
		if (!StringFunctions.isNullOrEmpty(getLastName())) {
			ret.append(" ");
			ret.append(getLastName());
		}

		return ret.toString().trim();
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

	/*@Transient
	public String getPaymentTypeStr() {
		if ("C".equalsIgnoreCase(getPaymentType())) {
			return "Cash";
		} else if ("D".equalsIgnoreCase(getPaymentType())) {
			return "DD" + "(" + getDdNumber() + ")";
		} else if ("CH".equalsIgnoreCase(getPaymentType())) {
			return "Cheque" + "(" + getChequeNumber() + ")";
		}
		return null;
	}

	@Transient
	public String getCtrDateStr() {
		return DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, this
				.getPaymentDate());
	}

	@Transient
	public String getCreatedDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this
				.getPaymentDate());
	}*/
	/*@Transient
	public int getStudentParticularTotal() {
		int total = 0;
		for (StudentFeePaidDetails sfpd : getStudentFeePaidDetails()) {
			if (sfpd.getPaymentAmount() != 0) {
				total += sfpd.getPaymentAmount();
			}
		}
		return total;
	}*/
	@Transient
	public double getStudentParticularTotal() {
		double total = 0;
			/*if (getPaymentTotalAmount() != 0 || getTotalDiscountAmount() !=0) {
				total = (getFeeAmount()-(getPaymentTotalAmount()+getTotalDiscountAmount()));
			}*/
		return total;
	}
	@Transient
	public long getPaymentAmountStr() {
		double total = 0;
			if (getPaymentAmount() != 0) {
				total = getPaymentAmount();
			}
			
		return Math.round(total);
	}
	@Transient
	public String getLastPaymentDateStr() {
		return DateFormatter.formatDate(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN, this.getLastUpdatedDate());
	}
	@Transient
    public String getLastUpdatedDateStr() {
            return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, this.getLastUpdatedDate());
    }

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	/*public double getPaymentTotalAmount() {
		return paymentTotalAmount;
	}

	public void setPaymentTotalAmount(double paymentTotalAmount) {
		this.paymentTotalAmount = paymentTotalAmount;
	}

	public double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}*/
	
}
