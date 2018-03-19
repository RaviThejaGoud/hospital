package com.urt.persistence.model.fee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Fee;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "studentTransportMonthFeeDetails")
public class StudentTransportMonthFeeDetails  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    protected long custId;
    protected long studentId;
    protected double paymentAmount;
    protected double discountAmount;
    protected Fee fee;
    protected String paymentStatus;
    protected long monthId;
    protected String monthName;
    protected AcademicYear academicYear;
    
    
	/**
	 * @return the fee
	 */
    @OneToOne()
    @JoinColumn(name="feeId", insertable=true, updatable=true)
	public Fee getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(Fee fee) {
		this.fee = fee;
	}
	
	public StudentTransportMonthFeeDetails() {
    }
	public StudentTransportMonthFeeDetails(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		StudentTransportMonthFeeDetails myClass = (StudentTransportMonthFeeDetails) object;
		return new CompareToBuilder().append(this.getCreatedDate(),
				myClass.getCreatedDate()).append(this.getCreatedById(),
				myClass.getCreatedById()).toComparison();
	}



    public int hashCode() {
        return (getStrId() != null ? this.getStrId().hashCode() : 0);
    }
    
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
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
	 * @return the paymentAmount
	 */
	@Column(name = "paymentAmount", nullable = true, columnDefinition=" double default 0")
	public double getPaymentAmount() {
		return paymentAmount;
	}
	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	@Column(name = "discountAmount", nullable = true, columnDefinition=" double default 0")
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	@Column(name = "paymentStatus", nullable = true, length = 1, columnDefinition = "char(1) default 'N'")
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getMonthId() {
		return monthId;
	}
	public void setMonthId(long monthId) {
		this.monthId = monthId;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	@OneToOne()
    @JoinColumn(name="academicYearId", insertable=true, updatable=true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
    

  

