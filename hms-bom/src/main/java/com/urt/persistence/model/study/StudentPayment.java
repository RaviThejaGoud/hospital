package com.urt.persistence.model.study;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.BankMaster;

@Entity
@Table(name = "studentPayment")
public class StudentPayment  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    //protected long studentId; 
    private long  custId;
    protected double paidAmount;
    protected double discountAmount;
    protected Student student;
    protected AcademicYear academicYear;
    protected long invoiceNumber;
    protected String branchName;
    protected String chequeNumber;
    protected Date chequeIssuedDate;
    protected String ddNumber;
    protected Date ddDrawnDate;
    /*C - Cash, D - DD , CH - Cheque, CS - Card Swipe, NEFT - Third Party Transfer*/
    protected String paymentType;
    protected String deleteStatus;
    protected BankMaster bankMaster;
    protected Date paymentDate;
    protected long dpPaymentDetailsId;
    protected String ipAddress;
    protected double excessAmount;
    protected String discountDesc;
    protected String desktopReceiptNumber;
    protected double fineAmount;
    protected String deleteDescription;
    protected String invoiceString;
    protected String concessionStatus;
    protected String transactionNumber;
    protected Double totalBalanceAmount;
    protected Double termwiseTotalBalanceAmount;
    protected double totalDueAmount;
    
    private double usedExcessAmount;
    
	
	@Transient
    public double getUsedExcessAmount() {
		return usedExcessAmount;
	}

	public void setUsedExcessAmount(double usedExcessAmount) {
		this.usedExcessAmount = usedExcessAmount;
	}

	@Column(name = "transactionNumber", nullable = true, columnDefinition="bigint(20) default 0")
    public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Column(name = "concessionStatus",nullable=true, length = 1,columnDefinition="char(1) default 'N'")
	public String getConcessionStatus() {
		return concessionStatus;
	}

	public void setConcessionStatus(String concessionStatus) {
		this.concessionStatus = concessionStatus;
	}

	@Column(name = "deleteDescription", nullable = true, length = 1024)
	public String getDeleteDescription() {
		return deleteDescription;
	}

	public void setDeleteDescription(String deleteDescription) {
		this.deleteDescription = deleteDescription;
	}

	//protected Fee fee;
    public StudentPayment(){
    	this.createdDate = new Date();
    	this.lastAccessDate = new Date();
    	this.lastUpdatedDate = new Date();
    }
    
    @Column(name = "fineAmount", nullable = false, columnDefinition=" double default 0")
    public double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
    public String getDesktopReceiptNumber() {
		return desktopReceiptNumber;
	}

	public void setDesktopReceiptNumber(String desktopReceiptNumber) {
		this.desktopReceiptNumber = desktopReceiptNumber;
	}
	
    @Column(name = "discountDesc", nullable = true, length = 1024)
	public String getDiscountDesc() {
		return discountDesc;
	}
	/**
	 * @param discountDesc the discountDesc to set
	 */
	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}
	
	/**
	 * @return the excessAmount
	 */
    @Transient
	public double getExcessAmount() {
		return excessAmount;
	}
	/**
	 * @param excessAmount the excessAmount to set
	 */
	public void setExcessAmount(double excessAmount) {
		this.excessAmount = excessAmount;
	}
	
	public long getDpPaymentDetailsId() {
		return dpPaymentDetailsId;
	}

	public void setDpPaymentDetailsId(long dpPaymentDetailsId) {
		this.dpPaymentDetailsId = dpPaymentDetailsId;
	}

	protected Set<StudentFeePaidDetails> studentFeePaidDetails;
    
    
	public void addStudentFeeDetails(StudentFeePaidDetails studentFeePaidDetails) {
		if (ObjectFunctions.isNullOrEmpty(getStudentFeePaidDetails())) {
			this.studentFeePaidDetails = new HashSet<StudentFeePaidDetails>();
		}
		getStudentFeePaidDetails().add(studentFeePaidDetails);
	}
	/**
	 * @return the studentFeePaidDetails
	 */
	@OneToMany(cascade=CascadeType.ALL)
 	@JoinColumn(name="studentPaymentId")
	public Set<StudentFeePaidDetails> getStudentFeePaidDetails() {
		return studentFeePaidDetails;
	}
	/**
	 * @param studentFeePaidDetails the studentFeePaidDetails to set
	 */
	public void setStudentFeePaidDetails(Set<StudentFeePaidDetails> studentFeePaidDetails) {
		this.studentFeePaidDetails = studentFeePaidDetails;
	}

	/**
	 * @return the student
	 */
    @OneToOne()
    @JoinColumn(name="studentId", insertable=true, updatable=true)
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * @return the fee
	 *//*
    @OneToOne()
    @JoinColumn(name="feeId", insertable=true, updatable=true)
	public Fee getFee() {
		return fee;
	}
	*//**
	 * @param fee the fee to set
	 *//*
	public void setFee(Fee fee) {
		this.fee = fee;
	}*/
	/**
     * @return the academicYear
     */
    @OneToOne()
    @JoinColumn(name="academicYearId", insertable=true, updatable=true)
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    
	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}
	

	/**
	 * @return the paidAmount
	 */
	@Column(name = "paidAmount", nullable = true, columnDefinition=" double default 0")
	public double getPaidAmount() {
		return paidAmount;
	}


	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}


	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
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
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	
	/**
	 * @return the discountPaidAmount
	 */
	@Column(name = "discountAmount",  columnDefinition=" double default 0")
	public double getDiscountAmount() {
		return discountAmount;
	}


	/**
	 * @param discountPaidAmount the discountPaidAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}


	@Transient
	public String getCreatedDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getCreatedDate());
	}
	
	@Transient
	public String getCtrDateStr() {
		return DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, this.getCreatedDate());
	}
	

	@Transient
	public String getFullPersonName() {
		if (!StringFunctions.isNullOrEmpty(getStudent().getAccount().getPerson().getFullPersonName())) {
			return getStudent().getAccount().getPerson().getFullPersonName();
		}
		return null;
	}
	@Transient
	public String getClassAndSection() {
		if (!ObjectFunctions.isNullOrEmpty(getStudent().getClassSection())) {
			return getStudent().getClassSection().getClassAndSection();
		}
		return null;
	}
	@Transient
	public String getRollNumber() {
		if (StringFunctions.isNullOrEmpty(getStudent().getRollNumber())) {
			return String.valueOf(getStudent().getRollNumber());
		}
		return null;
	}
	/*@Transient
	public String getTermName() {
		if (!StringFunctions.isNullOrEmpty(getFee().getSchoolTerms().getTermName())) {
			return getFee().getSchoolTerms().getTermName();
		}
		return null;
	}
	@Transient
	public String getParticulars() {
		if (!StringFunctions.isNullOrEmpty(getFee().getFeeType().getFeeType())) {
			return getFee().getFeeType().getFeeType();
		}
		return null;
	}
	@Transient
	public String getCustomerName() {
		if (!StringFunctions.isNullOrEmpty(getCustomerName().ge)) {
			return getCustomerName();
		}
		return null;
	}
	@Transient
	public String getTermDetails() {
		if (!ObjectFunctions.isNullOrEmpty(getFee().getSchoolTerms())) {
			return getFee().getSchoolTerms().getTermName()+"("+getFee().getSchoolTerms().getFromMonthName()+"-"+getFee().getSchoolTerms().getToMonthName()+")";
		}
		return null;
	}
	@Transient
	public double getStudentParticularTotal() {
		double total = 0;
			if (getPaidAmount() != 0) {
				total = (getFee().getFeeAmount()-getPaidAmount());
			}
		return total;
	}*/
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
	@Column(name = "ddNumber",  columnDefinition="varchar(15)",length=15)
	public String getDdNumber() {
		return ddNumber;
	}
	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}
	public Date getDdDrawnDate() {
		return ddDrawnDate;
	}
	public void setDdDrawnDate(Date ddDrawnDate) {
		this.ddDrawnDate = ddDrawnDate;
	}
	@Column(name = "paymentType",   columnDefinition="varchar(5)",length=5)
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	@Column(name = "deleteStatus",nullable=true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	@Column(name = "ipAddress",  columnDefinition="varchar(16)",length=16)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@OneToOne()
    @JoinColumn(name="bankId", insertable=true, updatable=true)
	public BankMaster getBankMaster() {
		return bankMaster;
	}
	public void setBankMaster(BankMaster bankMaster) {
		this.bankMaster = bankMaster;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Transient
	public String getPaymentDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getPaymentDate());
	}
	@Transient
	public String getDdNumberStr() {
		if ("C".equalsIgnoreCase(getPaymentType())) {
			return "Cash";
		} else if ("D".equalsIgnoreCase(getPaymentType())) {
			return "DD" + "(" + getDdNumber() + ")";
		} else if ("CH".equalsIgnoreCase(getPaymentType())) {
			return "Cheque" + "(" + getChequeNumber() + ")";
		}
		return null;
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
	
	@Transient
	public String getCtrPaymentDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getPaymentDate());
	}
	
	@Transient
	public String getAdmissionNumber() {
		if (!StringFunctions.isNullOrEmpty(getStudent().getAccount().getAdmissionNumber())) {
			return getStudent().getAccount().getAdmissionNumber();
		}
		return null;
	}
	
	@Column(name = "totalBalanceAmount", nullable = true) 
	public Double getTotalBalanceAmount() {
		return totalBalanceAmount;
	}

	public void setTotalBalanceAmount(Double totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}
	
	@Column(name = "termwiseTotalBalanceAmount", nullable = true)
	public Double getTermwiseTotalBalanceAmount() {
		return termwiseTotalBalanceAmount;
	}
 
	public void setTermwiseTotalBalanceAmount(Double termwiseTotalBalanceAmount) {
		this.termwiseTotalBalanceAmount = termwiseTotalBalanceAmount;
	}
	
	@Transient
	public double getTotalDueAmount() {
		return totalDueAmount;
	}

	public void setTotalDueAmount(double totalDueAmount) {
		this.totalDueAmount = totalDueAmount;
	}
	
}
    

  

