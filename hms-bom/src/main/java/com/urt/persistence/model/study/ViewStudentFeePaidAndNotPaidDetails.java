package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
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
@Table(name = "vw_studentFeePaidAndNotPaidDetails")
public class ViewStudentFeePaidAndNotPaidDetails implements Serializable,Cloneable,Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private long id;
	private long studId;
	private String firstName;
	private String lastName;
	private String admissionNumber;
	private long custId;
	private long academicYearId;
	private long classId;
	private String rollNumber;
	private long accountId;
	private boolean studentStatus;
	private String studDiscontinueDesc;
	private long feeId;
	private double feeAmount;
	private long feeTypeId;
	private long schoolTermId;
	private long categoryId;
	private String paymentStatus;
	private double paidAmount;
	private String categoryName;
	private String feeType;
	private String termName;
	private long paymentId;
	private String registerNumber;
	private String className;
	private String section;
	private boolean joinedThroughAdmissions;
	private Date dueDate;
	private long classSectionId;
	private double discountAmount;
	private Date fromDate;
	private Date toDate;
	private String feeSettingName;
	private double paymentAmount;
	private long feeSettingId;
	
	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the discountAmount
	 */
	public double getDiscountAmount() {
		return discountAmount;
	}
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
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
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	@Column(name = "joinedThroughAdmissions", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}
	public void setJoinedThroughAdmissions(boolean joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
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
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public long getStudId() {
		return studId;
	}
	public void setStudId(long studId) {
		this.studId = studId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	@Column(name = "studentStatus", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(boolean studentStatus) {
		this.studentStatus = studentStatus;
	}
	public String getStudDiscontinueDesc() {
		return studDiscontinueDesc;
	}
	public void setStudDiscontinueDesc(String studDiscontinueDesc) {
		this.studDiscontinueDesc = studDiscontinueDesc;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
    
	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the paidAmount
	 */
	public double getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the paymentId
	 */
    
	public long getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
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
	 * @return the paymentStatus
	 */
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getFeeSettingName() {
		return feeSettingName;
	}

	public void setFeeSettingName(String feeSettingName) {
		this.feeSettingName = feeSettingName;
	}
	@Transient
    public String getClassAndSection()
    {
    	if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getClassName()+" - "+getSection();
    }
	
	public ViewStudentFeePaidAndNotPaidDetails() {
		super();
	}

	public ViewStudentFeePaidAndNotPaidDetails(long custId,
			long academicYearId) {
		super();
		this.studId = studId;
		this.custId = custId;
		this.academicYearId = academicYearId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentFeePaidAndNotPaidDetails))
			return false;
		return false;

	}

	@Override
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

	@Override
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

	@Transient
	public String getPersonFullName() {
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
	
	@Override
	public int compareTo(Object o) {
		ViewStudentFeePaidAndNotPaidDetails myClass = (ViewStudentFeePaidAndNotPaidDetails) o;
		if(ObjectFunctions.isNullOrEmpty(myClass))
			return 0;
		else if(StringFunctions.isNotNullOrEmpty(myClass.getRegisterNumber()) && StringFunctions.isNotNullOrEmpty(this.getRegisterNumber())){
			if(myClass.getSection().equalsIgnoreCase(this.section)){
				return this.getRegisterNumber().compareToIgnoreCase(myClass.getRegisterNumber());
			}else
				return myClass.getSection().compareToIgnoreCase(this.section);
		}else
			return this.getPersonFullName().compareToIgnoreCase(myClass.getPersonFullName());
	}

	public long getFeeSettingId() {
		return feeSettingId;
	}

	public void setFeeSettingId(long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}
	
	
	
}
