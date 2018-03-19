package com.urt.persistence.model.customer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.Student;

/*
 * @create new table customer.
 */

@Entity
@Table(name = "fineFee")
public class FineFee  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private long  custId; 
    protected String status;
    protected String description;
    protected double fineFeeAmount;
    protected AcademicYear academicYear;
    protected long invoiceNumber;
    protected Date paymentDate;
   // private long  studId; 
    protected String ipAddress;
    protected Student student;
    protected long quantity;
    protected String deleteStatus;
    protected long academicYearId;
    
    /*This is dummy because we need to get acemic year Id in addSclar to display academic year id in delete invoice*/
    @Transient
    public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	@Column(name = "deleteStatus",nullable=true, length = 1,columnDefinition="char(1) default 'N'")
    public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	@Column(name = "quantity", nullable = true, columnDefinition="bigint(3) default 0",length=3)
    public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@OneToOne
    @JoinColumn(name="studentId")
    public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Column(name = "invoiceNumber", nullable = true, columnDefinition="bigint(11) default 0",length=12)
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
   
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
   
	@Column(name = "ipAddress",  columnDefinition="varchar(16)",length=16)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	/**
     * @return the academicYear
     */
    @OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    
    public FineFee() {
        
    }
	public FineFee(long id) {
        setId(id);
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
	 * @return the feeAmount
	 */
	@Column(name = "fineFeeAmount", nullable = true, columnDefinition="bigint(11) default 0",length=12)
	public double getFineFeeAmount() {
		return fineFeeAmount;
	}

	/**
	 * @param feeAmount the feeAmount to set
	 */
	public void setFineFeeAmount(double fineFeeAmount) {
		this.fineFeeAmount = fineFeeAmount;
	}
	@Column(name="description", nullable=true, length = 125)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getDateOfPaymentStr()
	{
		if(ObjectFunctions.isNullOrEmpty(getPaymentDate()))
		{
			return "";
		}
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getPaymentDate()); 
	}
	/*public long getStudId() {
		return studId;
	}
	public void setStudId(long studId) {
		this.studId = studId;
	}*/
	@Transient
	public String getPaymentDateStr()
	{
		if(ObjectFunctions.isNullOrEmpty(getPaymentDate()))
		{
			return "";
		}
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getPaymentDate()); 
	}
	
}

