package com.urt.persistence.model.hostel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;

@Entity
@Table(name = "vw_studentOutHostelDetails")
public class ViewStudentOutHostelDetails implements Serializable,Cloneable,Comparable  {
   
    private static final long serialVersionUID = 1L;

    
    private long studentOutId;
    protected String visitorName;
    private long custId;
    private long academicYearId;
    protected String visitorRelation;
    protected Date outDate;
    protected String outTime;
    protected String exceptedInTime;
    private long accountId;
    private String firstName;
    private String lastName;
    private String className;
    private String section;
    private String reasonForOuting;
    private boolean studentInOutStatus;
    protected long rollNumber;
    private String informParentsOnOut;
    private Date expectedInDate;
    private Date actualInDate;
    private String bedName;
    private String bedLevel;
    private String roomName;
    
    
    
    
    
    
    /**
     * @return the bedName
     */
    public String getBedName() {
        return bedName;
    }

    /**
     * @param bedName the bedName to set
     */
    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    /**
     * @return the bedLevel
     */
    public String getBedLevel() {
        return bedLevel;
    }

    /**
     * @param bedLevel the bedLevel to set
     */
    public void setBedLevel(String bedLevel) {
        this.bedLevel = bedLevel;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
     * @return the rollNumber
     */
    public long getRollNumber() {
        return rollNumber;
    }

    /**
     * @param rollNumber the rollNumber to set
     */
    public void setRollNumber(long rollNumber) {
        this.rollNumber = rollNumber;
    }

    /**
     * @return the studentInOutStatus
     */
    @Column(name = "studentInOutStatus", nullable = false, length = 1, columnDefinition = "char(1) default 'Y'")
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
    @Override
	public int compareTo(Object o) {
	// TODO Auto-generated method stub
	return 0;
    }


    /**
     * @return the studentOutId
     */
    @Id
    public long getStudentOutId() {
        return studentOutId;
    }


    /**
     * @param studentOutId the studentOutId to set
     */
    public void setStudentOutId(long studentOutId) {
        this.studentOutId = studentOutId;
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
     * @return the academicYearId
     */
    public long getAcademicYearId() {
        return academicYearId;
    }


    /**
     * @param academicYearId the academicYearId to set
     */
    public void setAcademicYearId(long academicYearId) {
        this.academicYearId = academicYearId;
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
     * @return the accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
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
    @Transient
	public String getOutDateStr() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getOutDate());
	}
    @Transient
	public String getExpectedInDateStr() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getExpectedInDate());
	}
}
