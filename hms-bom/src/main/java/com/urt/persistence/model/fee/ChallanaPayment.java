package com.urt.persistence.model.fee;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentPayment;

@Entity
@Table(name = "challanaPayment")
public class ChallanaPayment  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private long  custId;
    private AcademicYear academicYear;
    private Student student;
    private Date challanaDate;
    private StudentPayment studentPayment;
    private Date receivedDate;
    private long challanaNumber;
    private String deleteStatus;    
    

    public ChallanaPayment(){
    	this.createdDate = new Date();
    	this.lastAccessDate = new Date();
    	this.lastUpdatedDate = new Date();
    }
    
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="studentId", insertable=true, updatable=true)
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="academicYearId", insertable=true, updatable=true)
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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
		if (!StringFunctions.isNullOrEmpty(getStudent().getRollNumber())) {
			return String.valueOf(getStudent().getRollNumber());
		}
		return null;
	}
	
	@Column(name = "challanaNumber", nullable = true, columnDefinition="bigint(11) default 0",length=12)
	public long getChallanaNumber() {
		return challanaNumber;
	}
	public void setChallanaNumber(long invoiceNumber) {
		this.challanaNumber = invoiceNumber;
	}
	
	@Column(name = "deleteStatus",nullable=true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	public Date getChallanaDate() {
		return challanaDate;
	}
	public void setChallanaDate(Date challanaDate) {
		this.challanaDate = challanaDate;
	}
	@Transient
	public String getChallanaDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getChallanaDate());
	}

	@Transient
	public String getTodayDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, new Date());
	}
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="studentPaymentId", insertable=true, updatable=true)
	public StudentPayment getStudentPayment() {
		return studentPayment;
	}

	public void setStudentPayment(StudentPayment studentPayment) {
		this.studentPayment = studentPayment;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	
	
}