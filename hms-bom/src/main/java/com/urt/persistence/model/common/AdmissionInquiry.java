package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.ClassName;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "admissionInquiry")
public class AdmissionInquiry  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    private long custId;
    private double applicationFee;
    private String studentName;
    private String parentName;
    private AcademicYear academicYear;
    private ClassName classId; 
    private String parentMobileNumber;
    private String studentAddmitted;
    private int recepitNumber;
    private String previousSchoolName;
    private String studentType;
    private Address address;
   
    @Column(name = "recepitNumber",nullable = false, columnDefinition=" int default 0")
    public int getRecepitNumber() {
		return recepitNumber;
	}

	public void setRecepitNumber(int recepitNumber) {
		this.recepitNumber = recepitNumber;
	}

	@Column(name = "studentAddmitted",nullable=true, length = 1,columnDefinition="char(1) default 'N'")
	public String getStudentAddmitted() {
		return studentAddmitted;
	}

	public void setStudentAddmitted(String studentAddmitted) {
		this.studentAddmitted = studentAddmitted;
	}

	public String getParentMobileNumber() {
		return parentMobileNumber;
	}

	public void setParentMobileNumber(String parentMobileNumber) {
		this.parentMobileNumber = parentMobileNumber;
	}

	public double getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(double applicationFee) {
		this.applicationFee = applicationFee;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public String getPreviousSchoolName() {
			return previousSchoolName;
		}

	public void setPreviousSchoolName(String previousSchoolName) {
			this.previousSchoolName = previousSchoolName;
		}

	public String getStudentType() {
			return studentType;
		}
	
	public void setStudentType(String studentType) {
			this.studentType = studentType;
		}
    
	@OneToOne
	@JoinColumn(name="classId")
	public ClassName getClassId() {
		return classId;
	}

	public void setClassId(ClassName classId) {
		this.classId = classId;
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
	@OneToOne
	@JoinColumn(name="addressId")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
    
	public AdmissionInquiry() {
        
    }

	public AdmissionInquiry(long id) {
        setId(id);
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
	 * @return the custId
	 */
	@Column(name = "custId", nullable = true, length = 10)
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	
	public void setCustId(long custId) {
		this.custId = custId;
	}


	@Transient
	public String getCreatedDateStr() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getCreatedDate());
	}
	/*@Transient
	public String getApplicationStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getStartDate());
	}
	@Transient
	public String getApplicationClosedDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getEndDate());
	}
	@Transient
	public String getAdmissionsEndDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getAdmissionEndDate());
	}
	@Transient
	public String getApplicationsStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getStartDate());
	}
	@Transient
	public String getApplicationsClosedDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getEndDate());
	}*/

	
	
	
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(getAcademicYear()))
			return 0;
		else
			return getAcademicYear().getId();
	}
	
	@Transient
	public String getClassNameStr(){
		if(!ObjectFunctions.isNullOrEmpty(getClassId()))
			return getClassId().getClassName();
		else
			return "";
	}
	
	/*@Transient
	public long getAdmissionApplicationFee(){
		if(!ObjectFunctions.isNullOrEmpty(getApplicationFee()) && getApplicationFee() !=0){
			return getApplicationFee();
		}			
		else{
			long total = getRegistrationFee() + getProspectiveFee();
			return total;
		}
	}*/
	/*public void copyFrom(AdmissionSettings admissionSettings)
    {
		setAdmissionContent(admissionSettings.getAdmissionContent());
		setAdmissionEndDate(admissionSettings.getAdmissionEndDate());
		setApplicationFee(admissionSettings.getApplicationFee());
		setEndDate(admissionSettings.getEndDate());
		setEntranceDate(admissionSettings.getEntranceDate());
		setEntranceExamPassMarks(admissionSettings.getEntranceExamPassMarks());
		setEntranceExamTotalMarks(admissionSettings.getEntranceExamTotalMarks());
		setStartDate(admissionSettings.getStartDate());
		setStatus(admissionSettings.isStatus());
		setTestConducted(admissionSettings.isTestConducted());
		setRegistrationFee(admissionSettings.getRegistrationFee());
		setProspectiveFee(admissionSettings.getProspectiveFee());

		
    }*/
}
    

  

