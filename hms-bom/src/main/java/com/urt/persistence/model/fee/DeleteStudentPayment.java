package com.urt.persistence.model.fee;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.FineFee;
import com.urt.persistence.model.study.StudentPayment;

/*
 * @create new table customer.
 */

@Entity
@Table(name = "deleteStudentPayment")
public class DeleteStudentPayment  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private long custId; 
    protected StudentPayment studentPayment;
    protected FineFee otherFee;
    protected String deleteRemarks;
    protected String reportedPerson;
    protected String supportPersonName;
    
    @OneToOne
    @JoinColumn(name="otherFeeId")
    public FineFee getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(FineFee otherFee) {
		this.otherFee = otherFee;
	}
	@OneToOne
    @JoinColumn(name="studentPaymentId")
    public StudentPayment getStudentPayment() {
		return studentPayment;
	}
	public void setStudentPayment(StudentPayment studentPayment) {
		this.studentPayment = studentPayment;
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
	public DeleteStudentPayment() {
        
    }
	public DeleteStudentPayment(long id) {
        setId(id);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).toString();
	}
	public long getCustId() {
		return custId;
	}
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
}

