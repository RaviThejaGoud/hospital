package com.urt.persistence.model.study;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.transport.StudentTransportDetails;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "studentFeePaidDetails")
public class StudentFeePaidDetails  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    protected long custId;
    protected Long studentPaymentId;
    protected double paymentAmount;
    protected double discountAmount;
    protected Fee fee;
    protected String paymentStatus;
    protected String futureFeeStatus;
    protected String deleteStatus;
    protected long studentId;
    protected long feePaidDetailsId;
    protected String committedFeeStatus;
    protected double concessionAmount;
    private StudentTransportDetails studentTransportDetails; 
    
    @Column(name = "concessionAmount", nullable = true, columnDefinition=" double default 0")
    public double getConcessionAmount() {
		return concessionAmount;
	}
	public void setConcessionAmount(double concessionAmount) {
		this.concessionAmount = concessionAmount;
	}
	@Column(name = "committedFeeStatus", nullable = true, length = 1, columnDefinition = "char(1) default 'N'")
    public String getCommittedFeeStatus() {
		return committedFeeStatus;
	}
	public void setCommittedFeeStatus(String committedFeeStatus) {
		this.committedFeeStatus = committedFeeStatus;
	}
	@Column(name = "feePaidDetailsId", nullable = true, columnDefinition=" double default 0")
    public long getFeePaidDetailsId() {
		return feePaidDetailsId;
	}
	public void setFeePaidDetailsId(long feePaidDetailsId) {
		this.feePaidDetailsId = feePaidDetailsId;
	}
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
	/**
     * @return the academicYear
     */
	public Long getStudentPaymentId() {
		return studentPaymentId;
	}
	public void setStudentPaymentId(Long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}
	@Column(name = "deleteStatus",nullable=true, length = 1,columnDefinition="char(1) default 'N'")
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	
	public StudentFeePaidDetails() {
		this.createdDate = new Date();
    	this.lastAccessDate = new Date();
    	this.lastUpdatedDate = new Date();
    }
	public StudentFeePaidDetails(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		StudentFeePaidDetails myClass = (StudentFeePaidDetails) object;
		return new CompareToBuilder().append(this.getCreatedDate(),
				myClass.getCreatedDate()).append(this.getCreatedById(),
				myClass.getCreatedById()).toComparison();
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        } else {
        	return false;
        }
    }

    @Override
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
	@Column(name = "futureFeeStatus",nullable=true, length = 1,columnDefinition="char(1) default 'N'")
	public String getFutureFeeStatus() {
		return futureFeeStatus;
	}
	public void setFutureFeeStatus(String futureFeeStatus) {
		this.futureFeeStatus = futureFeeStatus;
	}
	
	@OneToOne()
	@JoinColumn(name="studTransportDetailsId", insertable=true, updatable=true)
	public StudentTransportDetails getStudentTransportDetails() {
		return studentTransportDetails;
	}
	
	public void setStudentTransportDetails(StudentTransportDetails studentTransportDetails) {
		this.studentTransportDetails = studentTransportDetails;
	}
	
	
}
    

  

