package com.hyniva.sms.ws.vo.fee;

import java.util.Date;

import com.churchgroup.util.date.DateFormatter;

public class ViewStudentDeleteFeeDetailsVO {
	
	public Long studentPaymentId;
	public String studentFullName;
	public String classNameAndSection;
	public String admissionNumber;
	public String invoiceNumber;
	public Date deleteDate;
	public String deleteRemarks;
	public String reportedPerson;
	public String supportPersonName;
	public Long academicYearId;
	public String custId;
	public double paidAmount;
	public double discountAmount;
	public String deleteStatus;
	public Date paymentDate;
	public String feeType;
	
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Long getStudentPaymentId() {
		return studentPaymentId;
	}
	public void setStudentPaymentId(Long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}
	public String getStudentFullName() {
		return studentFullName;
	}
	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}
	public String getClassNameAndSection() {
		return classNameAndSection;
	}
	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getDeleteRemarks() {
		return deleteRemarks;
	}
	public void setDeleteRemarks(String deleteRemarks) {
		this.deleteRemarks = deleteRemarks;
	}
	public String getReportedPerson() {
		return reportedPerson;
	}
	public void setReportedPerson(String reportedPerson) {
		this.reportedPerson = reportedPerson;
	}
	public String getSupportPersonName() {
		return supportPersonName;
	}
	public void setSupportPersonName(String supportPersonName) {
		this.supportPersonName = supportPersonName;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getDeleteDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getDeleteDate()); 
    }
    public String getPaymentDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getPaymentDate()); 
    }
    
}
