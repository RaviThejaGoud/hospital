package com.urt.persistence.model.study;

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

@Entity
@Table(name = "invoiceLogs")
public class InvoiceLogs  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String status;
    private long  custId;
    protected AcademicYear academicYear;
    protected double paymentAmount;
    protected Student student;
    protected Staff staff;
    protected String description;
    protected long invoiceNumber;
    protected String roleDescription;
    protected Date paymentDate;
    protected double lastModifyAmount;
    protected double discountAmount;
    protected String moduleType;
    
    @OneToOne()
    @JoinColumn(name="staffId", insertable=true, updatable=true)
    public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public double getLastModifyAmount() {
		return lastModifyAmount;
	}
	public void setLastModifyAmount(double lastModifyAmount) {
		this.lastModifyAmount = lastModifyAmount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
    public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	@Column(name = "paymentAmount", nullable = false, columnDefinition="int default 0")
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	@Column(name = "moduleType",nullable=true, length = 1,columnDefinition="char(1)")
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
}
    

  

