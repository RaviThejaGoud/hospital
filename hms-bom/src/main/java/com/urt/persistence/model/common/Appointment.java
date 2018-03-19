package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;


@Entity
@Table(name = "Appointment")
public class Appointment  extends PersistentObject {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
   
     private Date scheduleDate;
     private String scheduleTime;
     private String email;
     private String mobileNumber;
     private String description;
     private String subject;
     protected long requestAccountId;    
     private long appointmentRoleId;
     protected long custId;
     protected long receivedAccountId;
     protected long academicYearId;
     protected String status=Constants.PENDING_STATUS;
     private String apporveDescription;
     protected long studentAccountId;
     
     
     
    @Column(name = "studentAccountId",nullable = false, columnDefinition=" int default 0")
	public long getStudentAccountId() {
		return studentAccountId;
	}
	public void setStudentAccountId(long studentAccountId) {
		this.studentAccountId = studentAccountId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAppointmentRoleId() {
		return appointmentRoleId;
	}
	public void setAppointmentRoleId(long appointmentRoleId) {
		this.appointmentRoleId = appointmentRoleId;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name = "description", nullable = true, length = 1048)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getRequestAccountId() {
		return requestAccountId;
	}
	public void setRequestAccountId(long requestAccountId) {
		this.requestAccountId = requestAccountId;
	}
	public long getReceivedAccountId() {
		return receivedAccountId;
	}
	public void setReceivedAccountId(long receivedAccountId) {
		this.receivedAccountId = receivedAccountId;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "apporveDescription", nullable = true, length = 1048)
	public String getApporveDescription() {
		return apporveDescription;
	}

	public void setApporveDescription(String apporveDescription) {
		this.apporveDescription = apporveDescription;
	}
	@Transient
    public String getScheduleDateMonth()
    {
        return DateFormatter.formatDate(DateFormatter.MMM_D_PATTERN, getScheduleDate()); 
    }
	@Transient
    public String getScheduleDtaeStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getScheduleDate()); 
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

    
}