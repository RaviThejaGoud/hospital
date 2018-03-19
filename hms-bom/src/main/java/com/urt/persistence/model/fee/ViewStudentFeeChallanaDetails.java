package com.urt.persistence.model.fee;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.string.StringFunctions;


@Entity
@Table(name = "vw_studentFeeChallanaDetails")
public class ViewStudentFeeChallanaDetails implements Serializable,Cloneable,Comparable {

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
	private Date lastUpdatedDate;
	private long invoiceNumber;
	private boolean deleteStatus;
	private String paymentStatus;
	private long paymentId;
	private double paymentAmount;
	private String futureFeeStatus;
	private long feeId;
	private double feeAmount;
	private long custId;
	private long feeTypeId;
	private long schoolTermId;
	private long classId;
	private long academicYearId;
	private long sortingOrder;
	private String feeType;
	private Date fromdate;
	private Date toDate;
	private long feeSettingId;
	private String settingName;
	private String settingType;
	private boolean applToNewStuds;
	private String fromMonthName;
	private String toMonthName;
	private String termName;
	private Date dueDate;
	private Date dueDate2;
	private Date dueDate1;
	private long categoryId;
	private long studentId;
	private long rollNumber;
	private long accountId;
	private String status;
	private String description;
	private String joinedThroughAdmissions;
	private String hostelMode;
	private String className;
	private String section;
	private long classSectionId;
	private String paymentCommitFeeStatus;
	private long financeUserId;
	private long concessionId;
	private double concessionAmount;
	private double paymentConcessionAmount;
	private long challanaNumber;
	private Date challanaDate;
	private String admissionNumber;
	private String fullName;
	private long challanaId;
	
	public long getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public long getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getHostelMode() {
		return hostelMode;
	}
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}
	
	public String getPaymentCommitFeeStatus() {
		return paymentCommitFeeStatus;
	}
	public void setPaymentCommitFeeStatus(String paymentCommitFeeStatus) {
		this.paymentCommitFeeStatus = paymentCommitFeeStatus;
	}
	public long getFinanceUserId() {
		return financeUserId;
	}
	public void setFinanceUserId(long financeUserId) {
		this.financeUserId = financeUserId;
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
	public double getPaymentConcessionAmount() {
		return paymentConcessionAmount;
	}
	public void setPaymentConcessionAmount(double paymentConcessionAmount) {
		this.paymentConcessionAmount = paymentConcessionAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
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
	
	
	public String getJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}
	public void setJoinedThroughAdmissions(String joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
	}
	@Column(name = "applToNewStuds", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public boolean isApplToNewStuds() {
		return applToNewStuds;
	}
	public void setApplToNewStuds(boolean applToNewStuds) {
		this.applToNewStuds = applToNewStuds;
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
	
    
	@Column(name = "deleteStatus", nullable = false, length = 1, columnDefinition = "char(1) default 'Y'")
    @Type(type = "yes_no")
	public boolean isDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
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
	 * @return the feeId
	 */
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

	

	public ViewStudentFeeChallanaDetails() {
		super();
	}

	/*public ViewStudentFeePaymentDetails(long custId,long academicYearId,long id) {
		super();
	}*/

	public ViewStudentFeeChallanaDetails(long custId,
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
		if (!(o instanceof ViewStudentFeeChallanaDetails))
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

	

	

	@Override
	public int compareTo(Object obj) {
		ViewStudentFeeChallanaDetails paymentDetails =(ViewStudentFeeChallanaDetails)obj;
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
	
	public long getStudentPaymentId() {
		return studentPaymentId;
	}
	public void setStudentPaymentId(long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public long getChallanaNumber() {
		return challanaNumber;
	}
	public void setChallanaNumber(long challanaNumber) {
		this.challanaNumber = challanaNumber;
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
	
	public long getChallanaId() {
		return challanaId;
	}
	public void setChallanaId(long challanaId) {
		this.challanaId = challanaId;
	}
	public Date getChallanaDate() {
		return challanaDate;
	}
	public void setChallanaDate(Date challanaDate) {
		this.challanaDate = challanaDate;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Transient
	public String getChallanaDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getChallanaDate());
	}

	@Transient
	public String getTodayDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, new Date());
	}
}
