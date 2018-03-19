package com.urt.persistence.model.fee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.BankMaster;

	@Entity
	@Table(name = "studentFeeRefund")
	public class StudentFeeRefund  extends PersistentObject {
		
	    private static final long serialVersionUID = 3832626162173359411L;
	    
	    private long custId; 
	    protected AcademicYear academicYear;
	    protected long studentId;
	    protected double refundAmount;
	    protected Date refundDate;
	    protected double totalFeeAmount;
	    protected long invoiceNumber;
	    protected String invoiceString;
	    protected String paymentMode;
	    protected BankMaster bankMaster;
	    protected String branchName;
	    protected String chequeNumber;
	    protected Date chequeIssuedDate;
	   
	    
	    
	    public long getCustId() {
			return custId;
		}
		public void setCustId(long custId) {
			this.custId = custId;
		}
		
		@OneToOne
		@JoinColumn(name="academicYearId")
		public AcademicYear getAcademicYear() {
			return academicYear;
		}
		public void setAcademicYear(AcademicYear academicYear) {
			this.academicYear = academicYear;
		}
		public long getStudentId() {
			return studentId;
		}
		public void setStudentId(long studentId) {
			this.studentId = studentId;
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
		public double getTotalFeeAmount() {
			return totalFeeAmount;
		}
		public void setTotalFeeAmount(double totalFeeAmount) {
			this.totalFeeAmount = totalFeeAmount;
		}
	    
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean equals(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int compareTo(Object object) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Transient
		public String getRefundDateStr() {
			return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getRefundDate());
		}
		@Column(name = "invoiceNumber", nullable = true, columnDefinition="bigint(11) default 0",length=12)
		public long getInvoiceNumber() {
			return invoiceNumber;
		}
		public void setInvoiceNumber(long invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}
		public String getBranchName() {
			return branchName;
		}
		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}
		@Column(name = "chequeNumber",  columnDefinition="varchar(15)",length=15)
		public String getChequeNumber() {
			return chequeNumber;
		}
		public void setChequeNumber(String chequeNumber) {
			this.chequeNumber = chequeNumber;
		}
		public Date getChequeIssuedDate() {
			return chequeIssuedDate;
		}
		public void setChequeIssuedDate(Date chequeIssuedDate) {
			this.chequeIssuedDate = chequeIssuedDate;
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
		public String getPaymentMode() {
			return paymentMode;
		}
		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}
		@OneToOne()
	    @JoinColumn(name="bankId", insertable=true, updatable=true)
		public BankMaster getBankMaster() {
			return bankMaster;
		}
		public void setBankMaster(BankMaster bankMaster) {
			this.bankMaster = bankMaster;
		}
	    
}
