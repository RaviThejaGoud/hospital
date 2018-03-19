package com.urt.persistence.model.fee;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.transport.StudentTransportDetails;

/*
 * @create new table customer.
 */

@Entity
@Table(name = "studentFeeConcession")
public class StudentFeeConcession  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private long custId; 
    protected AcademicYear academicYear;
    protected long studentId;
    protected Fee fee;
    protected double concessionAmount;
    private StudentTransportDetails studentTransportDetails; 
   
   
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
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
    
    public StudentFeeConcession() {
        
    }
	public StudentFeeConcession(long id) {
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
	@OneToOne
    @JoinColumn(name="feeId")
	public Fee getFee() {
		return fee;
	}
	public void setFee(Fee fee) {
		this.fee = fee;
	}
	public double getConcessionAmount() {
		return concessionAmount;
	}
	public void setConcessionAmount(double concessionAmount) {
		this.concessionAmount = concessionAmount;
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
	@OneToOne()
	@JoinColumn(name="studTransportDetailsId", insertable=true, updatable=true)
	public StudentTransportDetails getStudentTransportDetails() {
		return studentTransportDetails;
	}
	
	public void setStudentTransportDetails(StudentTransportDetails studentTransportDetails) {
		this.studentTransportDetails = studentTransportDetails;
	}
}

