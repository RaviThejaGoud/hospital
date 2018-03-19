package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

@Entity
@Table(name = "studentAcademicPendingFee")
public class StudentAcademicPendingFee  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected long studentId;
    private long  custId;
    protected double partialFeeAmount;
    protected String paymentSattus;
    protected String paymentType;
    protected AcademicYear academicYear;
        
	
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}


	public String getPaymentSattus() {
		return paymentSattus;
	}

	public void setPaymentSattus(String paymentSattus) {
		this.paymentSattus = paymentSattus;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the academicYear
	 */
	@OneToOne
    @JoinColumn(name="academicYearId")
	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
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

	public double getPartialFeeAmount() {
		return partialFeeAmount;
	}

	public void setPartialFeeAmount(double partialFeeAmount) {
		this.partialFeeAmount = partialFeeAmount;
	}
	
	
}
    

  
