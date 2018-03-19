package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "admissionSettings")
public class AdmissionSettings  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected boolean status;
    protected Date startDate;
    protected Date endDate;
    protected long custId;
    protected Date admissionEndDate;
    protected boolean testConducted;
    protected long applicationFee;
    protected Date entranceDate;
    protected String admissionContent;
    protected String admissionOpenOrClose;
    protected AcademicYear academicYear;
    protected double entranceExamPassMarks;
    protected double entranceExamTotalMarks;
    protected long registrationFee;
    protected long prospectiveFee;
    private String admissionFormTemplatepath;
    private String admissionFormTemplateFileName;
    
    private String admissionFormEmptyTemplatepath;
    private String admissionFormEmptyTemplateFileName;
    protected boolean atuoGenerationAdmissionNumberStatus;


	@Column(name = "atuoGenerationAdmissionNumberStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isAtuoGenerationAdmissionNumberStatus() {
		return atuoGenerationAdmissionNumberStatus;
	}

	public void setAtuoGenerationAdmissionNumberStatus(
			boolean atuoGenerationAdmissionNumberStatus) {
		this.atuoGenerationAdmissionNumberStatus = atuoGenerationAdmissionNumberStatus;
	}

	public String getAdmissionFormTemplateFileName() {
		return admissionFormTemplateFileName;
	}

	public String getAdmissionFormEmptyTemplatepath() {
		return admissionFormEmptyTemplatepath;
	}

	public void setAdmissionFormEmptyTemplatepath(
			String admissionFormEmptyTemplatepath) {
		this.admissionFormEmptyTemplatepath = admissionFormEmptyTemplatepath;
	}

	public String getAdmissionFormEmptyTemplateFileName() {
		return admissionFormEmptyTemplateFileName;
	}

	public void setAdmissionFormEmptyTemplateFileName(
			String admissionFormEmptyTemplateFileName) {
		this.admissionFormEmptyTemplateFileName = admissionFormEmptyTemplateFileName;
	}

	public void setAdmissionFormTemplateFileName(
			String admissionFormTemplateFileName) {
		this.admissionFormTemplateFileName = admissionFormTemplateFileName;
	}

	public String getAdmissionFormTemplatepath() {
		return admissionFormTemplatepath;
	}

	public void setAdmissionFormTemplatepath(String admissionFormTemplatepath) {
		this.admissionFormTemplatepath = admissionFormTemplatepath;
	}

	/**
	 * @return the registrationFee
	 */
	@Column(name = "registrationFee", nullable = false, length = 12,unique=false,columnDefinition="char(12) default '0'")
	public long getRegistrationFee() {
		return registrationFee;
	}

	/**
	 * @param registrationFee the registrationFee to set
	 */
	public void setRegistrationFee(long registrationFee) {
		this.registrationFee = registrationFee;
	}

	/**
	 * @return the prospectiveFee
	 */
	@Column(name = "prospectiveFee", nullable = false, length = 12,unique=false,columnDefinition="char(12) default '0'")
	public long getProspectiveFee() {
		return prospectiveFee;
	}

	/**
	 * @param prospectiveFee the prospectiveFee to set
	 */
	public void setProspectiveFee(long prospectiveFee) {
		this.prospectiveFee = prospectiveFee;
	}

	public double getEntranceExamPassMarks() {
		return entranceExamPassMarks;
	}

	public void setEntranceExamPassMarks(double entranceExamPassMarks) {
		this.entranceExamPassMarks = entranceExamPassMarks;
	}

	public double getEntranceExamTotalMarks() {
		return entranceExamTotalMarks;
	}

	public void setEntranceExamTotalMarks(double entranceExamTotalMarks) {
		this.entranceExamTotalMarks = entranceExamTotalMarks;
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

    
	public AdmissionSettings() {
        
    }

	public AdmissionSettings(long id) {
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
	 * @return the status
	 */
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the startDate
	 */
	@Column(name = "startDate", nullable = true, length = 12,unique=false)
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	@Column(name = "endDate", nullable = true, length = 12,unique=false)
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	/**
	 * @return the className
	 */

	
	/**
	 * @return the admissionEndDate
	 */
	@Column(name = "admissionEndDate", nullable = true, length = 12,unique=false)
	public Date getAdmissionEndDate() {
		return admissionEndDate;
	}

	/**
	 * @param admissionEndDate the admissionEndDate to set
	 */
	public void setAdmissionEndDate(Date admissionEndDate) {
		this.admissionEndDate = admissionEndDate;
	}

	/**
	 * @return the testConducted
	 */
	@Column(name = "testConducted", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isTestConducted() {
		return testConducted;
	}

	/**
	 * @param testConducted the testConducted to set
	 */
	public void setTestConducted(boolean testConducted) {
		this.testConducted = testConducted;
	}

	/**
	 * @return the applicationFee
	 */
	@Column(name = "applicationFee", nullable = true, length = 12,unique=false)
	public long getApplicationFee() {
		return applicationFee;
	}

	/**
	 * @param applicationFee the applicationFee to set
	 */
	public void setApplicationFee(long applicationFee) {
		this.applicationFee = applicationFee;
	}

	/**
	 * @return the entranceDate
	 */
	@Column(name = "entranceDate", nullable = true, length = 12,unique=false)
	public Date getEntranceDate() {
		return entranceDate;
	}

	/**
	 * @param entranceDate the entranceDate to set
	 */
	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}
	@Transient
	public String getAdmissionOpenOrClose() {
		return admissionOpenOrClose;
	}

	public void setAdmissionOpenOrClose(String admissionOpenOrClose) {
		this.admissionOpenOrClose = admissionOpenOrClose;
	}

	@Transient
	public String getEntranceDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getEntranceDate());
	}
	@Transient
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
	}


	/**
	 * @return the admissionContent
	 */
	@Column(name="admissionContent",nullable=true,length=1024)
	public String getAdmissionContent() {
		return admissionContent;
	}

	/**
	 * @param admissionContent the admissionContent to set
	 */
	public void setAdmissionContent(String admissionContent) {
		this.admissionContent = admissionContent;
	}
	
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(getAcademicYear()))
			return 0;
		else
			return getAcademicYear().getId();
	}
	@Transient
	public long getAdmissionApplicationFee(){
		if(!ObjectFunctions.isNullOrEmpty(getApplicationFee()) && getApplicationFee() !=0){
			return getApplicationFee();
		}			
		else{
			long total = getRegistrationFee() + getProspectiveFee();
			return total;
		}
	}
	public void copyFrom(AdmissionSettings admissionSettings)
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

		
    }
}
    

  

