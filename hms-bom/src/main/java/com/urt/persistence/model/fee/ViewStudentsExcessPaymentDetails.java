package com.urt.persistence.model.fee;

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
 * File: ViewGroupType.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Narahrai Created /
 ********************************************************************/

@Entity
@Table(name = "vw_studentsExcessPaymentDetails")
public class ViewStudentsExcessPaymentDetails implements Serializable,Cloneable,Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long excessPaymentId;
	private double excessAmount;
	private boolean excessPaymentStatus;
	private long usedPaymentId;
	//private double usedExcessAmount;
	private long accountId;
	private long studId;
	private long academicYearId;
	private long custId;
	private long classSectionId;
	private String className;
	private String section;
	private String admissionNumber;
	private String rollNumber;
	private String mobileNumber;
	private long invoiceNumber;
	private String fullName;
	private boolean activeStatus;
	private String studDiscontinueDesc;
	private double remainingExcessAmount;
	
	
	/**
	 * @return the activeStatus
	 */
	@Column(name = "activeStatus", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isActiveStatus() {
		return activeStatus;
	}

	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * @return the studDiscontinueDesc
	 */
	public String getStudDiscontinueDesc() {
		return studDiscontinueDesc;
	}

	/**
	 * @param studDiscontinueDesc the studDiscontinueDesc to set
	 */
	public void setStudDiscontinueDesc(String studDiscontinueDesc) {
		this.studDiscontinueDesc = studDiscontinueDesc;
	}

	/**
	 * @return the excessPaymentId
	 */
	@Id
	public long getExcessPaymentId() {
		return excessPaymentId;
	}

	/**
	 * @param excessPaymentId the excessPaymentId to set
	 */
	public void setExcessPaymentId(long excessPaymentId) {
		this.excessPaymentId = excessPaymentId;
	}

	/**
	 * @return the excessAmount
	 */
	public double getExcessAmount() {
		return excessAmount;
	}

	/**
	 * @param excessAmount the excessAmount to set
	 */
	public void setExcessAmount(double excessAmount) {
		this.excessAmount = excessAmount;
	}

	/**
	 * @return the status
	 */
	@Column(name = "excessPaymentStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	public boolean isExcessPaymentStatus() {
		return excessPaymentStatus;
	}

	/**
	 * @param excessPaymentStatus the excessPaymentStatus to set
	 */
	public void setExcessPaymentStatus(boolean excessPaymentStatus) {
		this.excessPaymentStatus = excessPaymentStatus;
	}

	
	/**
	 * @return the usedPaymentId
	 */
	public long getUsedPaymentId() {
		return usedPaymentId;
	}
	
	/**
	 * @param usedPaymentId the usedPaymentId to set
	 */
	public void setUsedPaymentId(long usedPaymentId) {
		this.usedPaymentId = usedPaymentId;
	}

	/**
	 * @return the usedExcessAmount
	 */
	/*public double getUsedExcessAmount() {
		return usedExcessAmount;
	}

	*//**
	 * @param usedExcessAmount the usedExcessAmount to set
	 *//*
	public void setUsedExcessAmount(double usedExcessAmount) {
		this.usedExcessAmount = usedExcessAmount;
	}*/

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the studId
	 */
	public long getStudId() {
		return studId;
	}

	/**
	 * @param studId the studId to set
	 */
	public void setStudId(long studId) {
		this.studId = studId;
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
	 * @return the classSectionId
	 */
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
	 * @return the admissionNumber
	 */
	public String getAdmissionNumber() {
		return admissionNumber;
	}

	/**
	 * @param admissionNumber the admissionNumber to set
	 */
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	/**
	 * @return the rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the invoiceNumber
	 */
	public long getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	/**
	 * @see java.lang.Object#toString()
	 * 
	 *      Domestic Address Formatted as addressLine1; addressLine2; city,
	 *      state zipCode[-zipCodeSupplement]
	 * 
	 *      Military Address Formatted as addressLine1; addressLine2; city
	 *      postalCode;
	 * @Override
	 */

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
	@Transient
	public String getClassSection(){
		if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getClassName().trim()+"-"+getSection().trim();
	}

	/**
	 * @return the remainingExcessAmount
	 */
	@Transient
	public double getRemainingExcessAmount() {
		return remainingExcessAmount;
	}

	/**
	 * @param remainingExcessAmount the remainingExcessAmount to set
	 */
	public void setRemainingExcessAmount(double remainingExcessAmount) {
		this.remainingExcessAmount = remainingExcessAmount;
	}
	
}
