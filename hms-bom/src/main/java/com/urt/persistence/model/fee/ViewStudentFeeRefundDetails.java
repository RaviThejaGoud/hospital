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
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;



@Entity
@Table(name = "vw_studentFeeRefundDetails")
public class ViewStudentFeeRefundDetails implements Serializable,Cloneable, Comparable {

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
	private String username;
	private String admissionNumber;
	private long studentId;
	private long academicYearId;
	private String description;
	private String status;
	private long classNameClassId;
	private String rollNumber;
	private long categoryId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private long personId;
	private String className;
	private String section;
	private String classNameAndSection;
	private long classSectionId;
	private String studentMobile;
	private String fullName;
	private String categoryName;
	private String feeRefundStatus;
	private long refundId;
	private double refundAmount;
	private Date refundDate;
	private String branchName;
	private Date chequeIssuedDate;
	private String chequeNumber;
	private long invoiceNumber;
	private String invoiceString;
	private String paymentMode;
	private double totalFeeAmount;
	private long bankId;

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	public ViewStudentFeeRefundDetails() {
		super();
	}

	public ViewStudentFeeRefundDetails(long accountId, long custId,
			long academicYearId, String status,
			String firstName, String lastName) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.academicYearId = academicYearId;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentFeeRefundDetails))
			return false;
		return false;

	}

	@Override
	public int hashCode() {
		int result = 0;

		return result;
	}


	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		buffer.append(" Status : ").append(getStatus());
		return buffer.toString();
	}

	@Override
	public int compareTo(Object object) {
		ViewStudentFeeRefundDetails myClass = (ViewStudentFeeRefundDetails) object;
		if(ObjectFunctions.isNullOrEmpty(myClass))
			return 0;
		else
			return this.getPersonFullName().compareToIgnoreCase(myClass.getPersonFullName());	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getClassNameClassId() {
		return classNameClassId;
	}
	public void setClassNameClassId(long classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
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
	public String getClassNameAndSection() {
		return classNameAndSection;
	}
	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	public String getStudentMobile() {
		return studentMobile;
	}
	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getFeeRefundStatus() {
		return feeRefundStatus;
	}
	public void setFeeRefundStatus(String feeRefundStatus) {
		this.feeRefundStatus = feeRefundStatus;
	}
	@Id
	public long getRefundId() {
		return refundId;
	}
	public void setRefundId(long refundId) {
		this.refundId = refundId;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getChequeIssuedDate() {
		return chequeIssuedDate;
	}
	public void setChequeIssuedDate(Date chequeIssuedDate) {
		this.chequeIssuedDate = chequeIssuedDate;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceString() {
		return invoiceString;
	}
	public void setInvoiceString(String invoiceString) {
		this.invoiceString = invoiceString;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public double getTotalFeeAmount() {
		return totalFeeAmount;
	}
	public void setTotalFeeAmount(double totalFeeAmount) {
		this.totalFeeAmount = totalFeeAmount;
	}
	public long getBankId() {
		return bankId;
	}
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	@Transient
    public String getRefundDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getRefundDate());
    }
	@Transient
	public String getPaymentModeStr() {
		if ("C".equalsIgnoreCase(this.getPaymentMode())) {
			return "Cash";
		}  else if ("CH".equalsIgnoreCase(this.getPaymentMode())) {
			return "Cheque" + "(" + getChequeNumber() + ")";
		}
		return null;
	}
}
