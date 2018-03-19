package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/********************************************************************
 * Copyright (C) 2017-18 Hyniva All Rights Reserved
 * 
 * File: ViewStudentTransportFeePaymentDetails.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Sep 14, 2017 Siva Created /
 ********************************************************************/

@Entity
@Table(name = "vw_studentTransportFeePaymentDetails")
public class ViewStudentTransportFeePaymentDetails implements Serializable,Cloneable,Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long studentPaymentId;
	private double paidAmount;
	private double discountAmount;
	private long transactionNumber;
	private long bankId;
	private Date chequeIssuedDate;
	private Date ddDrawnDate;
	private String branchName;
	private Date lastUpdatedDate;
	private String paymentType;
	private long ddNumber;
	private String bankName;
	private String chequeNumber;
	private String invoiceNumber;
	private double fineAmount;
	private String deleteStatus;
	private String paymentStatus;
	private Date paymentDate;
	private long paymentId;
	private double paymentAmount;
	private String futureFeeStatus;
	private long transportFeeId;
	private double feeAmount;
	private long custId;
	private long feeTypeId;
	private long schoolTermId;
	private long classId;
	private long academicYearId;
	private long sortingOrder;
	private String feeType;
	private Date fromDate;
	private Date toDate;
	private long feeSettingId;
	private String settingName;
	private String settingType;
	private String applToNewStuds;
	private String fromMonthName;
	private String toMonthName;
	private String termName;
	private Date dueDate;
	private Date dueDate2;
	private Date dueDate1;
	private long pickupBoardingPointId;
	private long dropBoardingPointId;
	private long studentId;
	private long rollNumber;
	private long accountId;
	private String status;
	private String description;
	private String transportMode;
	private String joinedThroughAdmissions;
	private long vehicleAcademicDetailsId;
	private String hostelMode;
	private String committedFee;
	private long boardingPointId;
	private String className;
	private String section;
	private long classSectionId;
	private String username;
	private String admissionNumber;
	private String firstName;
	private String lastName;
	private String middleName;
	private String mobileNumber;
	private String phoneNumber;
	private String parentEmail;
	private String registerNumber;
	private String fullName;
	private String paymentCommitFeeStatus;
	private long financeUserId;
	private long concessionId;
	private double concessionAmount;
	private double paymentConcessionAmount;
	private String invoiceString;
	private String desktopReceiptNumber;
	private String feePaidStatus;
	private String feeConfigured;
	private String personFullName;
	private List pendingStudentList;
	private List studentTransportFeeList;
    
	
    public String getFeePaidStatus() {
		return feePaidStatus;
	}
	public void setFeePaidStatus(String feePaidStatus) {
		this.feePaidStatus = feePaidStatus;
	}
	public String getFeeConfigured() {
		return feeConfigured;
	}
	public void setFeeConfigured(String feeConfigured) {
		this.feeConfigured = feeConfigured;
	}
	
	public double getPaymentConcessionAmount() {
		return paymentConcessionAmount;
	}
	public void setPaymentConcessionAmount(double paymentConcessionAmount) {
		this.paymentConcessionAmount = paymentConcessionAmount;
	}
	public long getConcessionId() {
		return concessionId;
	}
	public void setConcessionId(long concessionId) {
		this.concessionId = concessionId;
	}
	public double getConcessionAmount() {
		return concessionAmount;
	}
	public void setConcessionAmount(double concessionAmount) {
		this.concessionAmount = concessionAmount;
	}
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	/**
	 * @return the classSectionId
	 */
    @Transient
	public Long getClassSectionId() {
		return classSectionId;
	}
	/**
	 * @param classSectionId the classSectionId to set
	 */
	public void setClassSectionId(Long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
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
	
	
	/**
	 * @return the vehicleAcademicDetailsId
	 */
	public long getVehicleAcademicDetailsId() {
		return vehicleAcademicDetailsId;
	}
	/**
	 * @param vehicleAcademicDetailsId the vehicleAcademicDetailsId to set
	 */
	public void setVehicleAcademicDetailsId(long vehicleAcademicDetailsId) {
		this.vehicleAcademicDetailsId = vehicleAcademicDetailsId;
	}
	public String getJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}
	public void setJoinedThroughAdmissions(String joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
	}
	
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
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
	/*public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}*/
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
    
    
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
    public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	/**
	 * @return the feeAmount
	 */
	public double getFeeAmount() {
		return feeAmount;
	}

	/**
	 * @return the discountAmount
	 */
	public double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @param feeAmount the feeAmount to set
	 */
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	/**
	 * @return the chequeNumber
	 */
	public String getChequeNumber() {
		return chequeNumber;
	}

	/**
	 * @param chequeNumber the chequeNumber to set
	 */
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	/**
	 * @return the studentPaymentId
	 */
	public long getStudentPaymentId() {
		return studentPaymentId;
	}

	/**
	 * @param studentPaymentId the studentPaymentId to set
	 */
	public void setStudentPaymentId(long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
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
	 * @return the paymentAmount
	 */
	public double getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @return the feeType
	 */
	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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
	public long getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the parentEmail
	 */
	public String getParentEmail() {
		return parentEmail;
	}

	/**
	 * @param parentEmail the parentEmail to set
	 */
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	@Transient
    public List getPendingStudentList() {
    	if (ObjectFunctions.isNullOrEmpty(this.pendingStudentList)) {
			this.pendingStudentList = new ArrayList();
		}
		return pendingStudentList;
	}

	public void setPendingStudentList(List pendingStudentList) {
		this.pendingStudentList = pendingStudentList;
	}
	
	public ViewStudentTransportFeePaymentDetails() {
		super();
	}

	/*public ViewStudentFeePaymentDetails(long custId,long academicYearId,long id) {
		super();
	}*/

	public ViewStudentTransportFeePaymentDetails(long custId,
			long academicYearId,
			long id) {
		super();
		//this.id = id;
		this.custId = custId;
		this.academicYearId = academicYearId;
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentTransportFeePaymentDetails))
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
	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Transient
	public String getFullFormattedName(boolean title, boolean middle,
			boolean suffix) {
		StringBuffer ret = new StringBuffer(10);
			
		ret.append(" ");
		return ret.toString().trim();
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Transient
	public String getIsBetweenFeeDueDays() {
		Date date=new Date();
		if("P".equalsIgnoreCase(getPaymentStatus())){
			return "P";
		}
		int feeDueDays=DateFunctions.daysBetween(getPaymentDate(),date);
		
		if(feeDueDays>=0 && feeDueDays <=14){
			return "A";
		}
		if(feeDueDays>14 && feeDueDays <=30){
			return "B";
		}
		if(feeDueDays>30 && feeDueDays<=60){
			return "C";
		}
		if(feeDueDays>60){
			return "D";
		}
		if(feeDueDays<=0 && feeDueDays>= -15){
			return "E";
		}
		
		return null;
	}

	@Override
	public int compareTo(Object obj) {
		ViewStudentTransportFeePaymentDetails paymentDetails =(ViewStudentTransportFeePaymentDetails)obj;
		if(paymentDetails.getId() > 0 && this.getId() > 0){
			if(this.getId() > paymentDetails.getId())
				return 1;
			else if(this.getId() == paymentDetails.getId())
				return 0;
			else
				return -1;
		}
		return 0;
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
	}*/
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
	@Transient
	public String getCtrDateStr() {
		return DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, this.getPaymentDate());
	}

	@Transient
	public String getCtrPaymentDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getPaymentDate());
	}
	
	@Transient
	public String getPaymentDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getPaymentDate());
	}
	@Transient
    public String getDueDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getDueDate());
    }
	@Transient
    public String getCreatedDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getDueDate());
    }
	@Transient
    public String getLastUpdatedDateStr() {
            return DateFormatter.formatDate(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN, this.getLastUpdatedDate());
    }
	
	public long getBankId() {
		return bankId;
	}
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	public Date getChequeIssuedDate() {
		return chequeIssuedDate;
	}
	public void setChequeIssuedDate(Date chequeIssuedDate) {
		this.chequeIssuedDate = chequeIssuedDate;
	}
	public Date getDdDrawnDate() {
		return ddDrawnDate;
	}
	public void setDdDrawnDate(Date ddDrawnDate) {
		this.ddDrawnDate = ddDrawnDate;
	}
	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
		}
		return getClassName();
	}
	public String getFutureFeeStatus() {
		return futureFeeStatus;
	}
	public void setFutureFeeStatus(String futureFeeStatus) {
		this.futureFeeStatus = futureFeeStatus;
	}
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
	@Transient
	public double getPayableAmount(){
		if(this.paymentConcessionAmount !=0){
			return (this.feeAmount) - (this.paymentAmount+this.discountAmount+this.paymentConcessionAmount);
		}else{
			if(this.concessionAmount !=0 ){
				return (this.feeAmount-this.concessionAmount) - (this.paymentAmount+this.discountAmount+this.paymentConcessionAmount);
			}else
				return this.feeAmount - (this.paymentAmount+this.discountAmount);
		}
		
		
	}
	 public double getFineAmount() {
			return fineAmount;
		}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	
	@Transient
	public int getDaysPending(){
		int feeDueDays=DateFunctions.daysBetween(this.getDueDate(),new Date());
		
		if(feeDueDays > 0){
			return feeDueDays;
		}else if (feeDueDays < 0 ) {
			return 0;
		}else {
			return feeDueDays;
		}
	}
	public String getInvoiceString() {
		return invoiceString;
	}
	public void setInvoiceString(String invoiceString) {
		this.invoiceString = invoiceString;
	}
	
	@Transient
	public String getInvoiceNumberStr(){
		if(!StringFunctions.isNullOrEmpty(this.getInvoiceString()))
			return this.getInvoiceString();
		else if (Long.valueOf(this.getInvoiceNumber()) >0 ){
			return String.valueOf(this.getInvoiceNumber());
		}else
			return null;
			
	}
	public String getPersonFullName() {
		return personFullName;
	}
	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	public long getTransportFeeId() {
		return transportFeeId;
	}
	public void setTransportFeeId(long transportFeeId) {
		this.transportFeeId = transportFeeId;
	}
	public long getPickupBoardingPointId() {
		return pickupBoardingPointId;
	}
	public void setPickupBoardingPointId(long pickupBoardingPointId) {
		this.pickupBoardingPointId = pickupBoardingPointId;
	}
	public long getDropBoardingPointId() {
		return dropBoardingPointId;
	}
	public void setDropBoardingPointId(long dropBoardingPointId) {
		this.dropBoardingPointId = dropBoardingPointId;
	}
	@Transient
	public List getStudentTransportFeeList() {
		if (ObjectFunctions.isNullOrEmpty(this.studentTransportFeeList)) {
			this.studentTransportFeeList = new ArrayList();
		}
		return studentTransportFeeList;
	}
	public void setStudentTransportFeeList(List studentTransportFeeList) {
		this.studentTransportFeeList = studentTransportFeeList;
	}
	
}
