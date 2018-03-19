package com.urt.persistence.model.hostel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "studentOut")
public class StudentOut extends PersistentObject {

    private static final long serialVersionUID = 1L;

    public StudentOut() {
    }

    public StudentOut(long id) {
	setId(id);
    }

    private String visitorName;
    private String reasonForOuting;
    private Long permissionStaffId;
    private String visitorRelation;
    private Long custId;
    private Long accountId;
    private Date expectedInDate;
    private Date outDate;
    protected String exceptedInTime;
    protected String outTime;
    private AcademicYear academicYear;
    private Date actualInDate;
    protected String actualInTime;
    public boolean studentInOutStatus;
    private String informParentsOnOut;

    /**
     * @return the informParentsOnOut
     */
    public String getInformParentsOnOut() {
        return informParentsOnOut;
    }

    /**
     * @param informParentsOnOut the informParentsOnOut to set
     */
    public void setInformParentsOnOut(String informParentsOnOut) {
        this.informParentsOnOut = informParentsOnOut;
    }

    /**
     * @return the studentInOutStatus
     */
    @Column(name = "studentInOutStatus", nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
    @Type(type = "yes_no")
    public boolean isStudentInOutStatus() {
        return studentInOutStatus;
    }

    /**
     * @param studentInOutStatus the studentInOutStatus to set
     */
    public void setStudentInOutStatus(boolean studentInOutStatus) {
        this.studentInOutStatus = studentInOutStatus;
    }

    /**
     * @return the actualInDate
     */
    public Date getActualInDate() {
        return actualInDate;
    }

    /**
     * @param actualInDate the actualInDate to set
     */
    public void setActualInDate(Date actualInDate) {
        this.actualInDate = actualInDate;
    }

    /**
     * @return the actualInTime
     */
    public String getActualInTime() {
        return actualInTime;
    }

    /**
     * @param actualInTime the actualInTime to set
     */
    public void setActualInTime(String actualInTime) {
        this.actualInTime = actualInTime;
    }

    /**
     * @return the reasonForOuting
     */
    public String getReasonForOuting() {
        return reasonForOuting;
    }

    /**
     * @param reasonForOuting the reasonForOuting to set
     */
    public void setReasonForOuting(String reasonForOuting) {
        this.reasonForOuting = reasonForOuting;
    }

    /**
     * @return the permissionStaffId
     */
    public Long getPermissionStaffId() {
        return permissionStaffId;
    }

    /**
     * @param permissionStaffId the permissionStaffId to set
     */
    public void setPermissionStaffId(Long permissionStaffId) {
        this.permissionStaffId = permissionStaffId;
    }

    /**
     * @return the visitorName
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * @param visitorName the visitorName to set
     */
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    /**
     * @return the visitorRelation
     */
    public String getVisitorRelation() {
        return visitorRelation;
    }

    /**
     * @param visitorRelation the visitorRelation to set
     */
    public void setVisitorRelation(String visitorRelation) {
        this.visitorRelation = visitorRelation;
    }

    /**
     * @return the expectedInDate
     */
    public Date getExpectedInDate() {
        return expectedInDate;
    }

    /**
     * @param expectedInDate the expectedInDate to set
     */
    public void setExpectedInDate(Date expectedInDate) {
        this.expectedInDate = expectedInDate;
    }

    /**
     * @return the outDate
     */
    public Date getOutDate() {
        return outDate;
    }

    /**
     * @param outDate the outDate to set
     */
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    /**
     * @return the exceptedInTime
     */
    public String getExceptedInTime() {
        return exceptedInTime;
    }

    /**
     * @param exceptedInTime the exceptedInTime to set
     */
    public void setExceptedInTime(String exceptedInTime) {
        this.exceptedInTime = exceptedInTime;
    }

    /**
     * @return the outTime
     */
    public String getOutTime() {
        return outTime;
    }

    /**
     * @param outTime the outTime to set
     */
    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    /**
     * @return the accountId
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academicYearId", insertable = true, updatable = true)
    public AcademicYear getAcademicYear() {
	return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
	this.academicYear = academicYear;
    }

    /**
     * @return the custId
     */
    public Long getCustId() {
	return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(Long custId) {
	this.custId = custId;
    }
    
    @Override
    public String toString() {
	return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
		.append("id", this.getId()).toString();
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
    
    @Transient
    public String getStudentOutDateStr() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getOutDate());
    }
	@Transient
    public String getStudentInDateStr() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getExpectedInDate());
    }
   
}
