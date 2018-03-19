package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewGroupType.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Narahari Created /
 ********************************************************************/

@Entity
@Table(name = "vw_studentFineFeeDetails")
public class ViewStudentFineFeeDetails implements Serializable,Cloneable, Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long accountId;
	private long custId;
	private long academicYearId;
	private long studentId;
	private String studentFullName;
	protected String mobileNumber;
	protected String classAndSection;
	protected String description;
    protected double fineFeeAmount;
    protected long invoiceNumber;
    protected Date paymentDate;
    protected String admissionNumber;
    protected long classNameClassId;
    protected String parentEmail;
    private String fatherName;
    private String academicYear;
    private String streetName;
	private String postalCode; 
    private String city;
    private long fineFeeId;
    private String personFullName; 
    protected long quantity;
    protected long classSectionId;
    @Id
	public long getFineFeeId() {
		return fineFeeId;
	}
	public void setFineFeeId(long fineFeeId) {
		this.fineFeeId = fineFeeId;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public long getClassNameClassId() {
		return classNameClassId;
	}
	public void setClassNameClassId(long classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
    
    
	@Column(name = "admissionNumber", nullable = true, length = 128)
    public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
    
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getStudentFullName() {
		return studentFullName;
	}
	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getFineFeeAmount() {
		return fineFeeAmount;
	}
	public void setFineFeeAmount(double fineFeeAmount) {
		this.fineFeeAmount = fineFeeAmount;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column( name="invoiceNumber", unique=true, nullable=false, updatable=false )
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@Transient
	public String getPaymentDateStr()
	{
		if(ObjectFunctions.isNullOrEmpty(getPaymentDate()))
		{
			return "";
		}
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getPaymentDate()); 
	}
	@Transient
	public String getPayDateStr()
	{
		if(ObjectFunctions.isNullOrEmpty(getPaymentDate()))
		{
			return "";
		}
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getPaymentDate()); 
	}
	 @Transient
	    public String getAddressForStudent1() {
	        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
	        if(!StringFunctions.isNullOrEmpty(this.streetName))
	        {
	            buffer.append(getStreetName());
	        }
	        if(!StringFunctions.isNullOrEmpty(this.city))
	        {
	            buffer.append(", ").append(getCity());
	        }
	        if(!StringFunctions.isNullOrEmpty(this.postalCode))
	        {
	            buffer.append(", ").append(getPostalCode());
	        }
	        return buffer.toString();
	    }
	public String getPersonFullName() {
		return personFullName;
	}
	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
}
