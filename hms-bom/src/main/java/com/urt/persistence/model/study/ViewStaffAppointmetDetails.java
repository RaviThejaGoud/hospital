package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.string.StringFunctions;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewStaffAppointmetDetails.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Nov 10, 2015     RaviTeja          	Created
/********************************************************************/

@Entity
@Table(name = "vw_staffAppointmentDetails")
public class ViewStaffAppointmetDetails  implements Comparable,Serializable,Cloneable {


	/**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long appointmentId;
     private long custId;
     private String email;
     private String subject;
     private String description;
     private String academicYearId;
     private String status;
     private long requestAccountId;
     private long receivedAccountId;
     private Date scheduleDate;
     private String scheduleTime;
     private String mobileNumber;
     private String firstName;
     private String lastName;
     private String requestRoleName;
     private String requestRoleDescription;
     private String requestFullName;
     private String receiveFullName;
     private String apporveDescription;
     private String receivedRoleDesc;
     private String receivedRoleName;
     private String reciveMobileNumber;
     private String receivStaffEmail;
     private String studentFullName;
     
     
    @Id
 	@Column( name="appointmentId", unique=false, nullable=false, updatable=false )
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getRequestRoleName() {
		return requestRoleName;
	}
	public void setRequestRoleName(String requestRoleName) {
		this.requestRoleName = requestRoleName;
	}
	public String getRequestRoleDescription() {
		return requestRoleDescription;
	}
	public void setRequestRoleDescription(String requestRoleDescription) {
		this.requestRoleDescription = requestRoleDescription;
	}
	public String getRequestFullName() {
		return requestFullName;
	}
	public void setRequestFullName(String requestFullName) {
		this.requestFullName = requestFullName;
	}
	public String getReceiveFullName() {
		return receiveFullName;
	}
	public void setReceiveFullName(String receiveFullName) {
		this.receiveFullName = receiveFullName;
	}
	
	public String getApporveDescription() {
		return apporveDescription;
	}
	public void setApporveDescription(String apporveDescription) {
		this.apporveDescription = apporveDescription;
	}
	public String getReceivedRoleDesc() {
		return receivedRoleDesc;
	}
	public void setReceivedRoleDesc(String receivedRoleDesc) {
		this.receivedRoleDesc = receivedRoleDesc;
	}
	public String getReceivedRoleName() {
		return receivedRoleName;
	}
	public void setReceivedRoleName(String receivedRoleName) {
		this.receivedRoleName = receivedRoleName;
	}
	
	public String getReciveMobileNumber() {
		return reciveMobileNumber;
	}
	public void setReciveMobileNumber(String reciveMobileNumber) {
		this.reciveMobileNumber = reciveMobileNumber;
	}
	
	public String getReceivStaffEmail() {
		return receivStaffEmail;
	}
	public void setReceivStaffEmail(String receivStaffEmail) {
		this.receivStaffEmail = receivStaffEmail;
	}
	
	public String getStudentFullName() {
		return studentFullName;
	}
	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}
	
	 
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	 @Transient
	   public String getPersonNameWithRoleDesc()
	   {
	       StringBuffer ret = new StringBuffer(10);
	       ret.append(getReceiveFullName());
	       if (!StringFunctions.isNullOrEmpty(getReceivedRoleDesc()))
	       {
	           ret.append(" (" +getReceivedRoleDesc()+ ")");
	       }
	      
	       return ret.toString().trim();
	   }
	 @Transient
	    public String getScheduleDateMonoth()
	    {
	        return DateFormatter.formatDate(DateFormatter.MMM_D_PATTERN, getScheduleDate()); 
	    }
		@Transient
	    public String getScheduleDateWithTimeStr()
	    {
	        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getScheduleDate())+" at "+getScheduleTime(); 
	    }
		@Transient
	    public String getScheduleDateStr()
	    {
	        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getScheduleDate()); 
	    }
		 @Transient
		   public String getRequestPersonNameWithRoleDesc()
		   {
		       StringBuffer ret = new StringBuffer(10);
		       ret.append(getRequestFullName());
		       if (!StringFunctions.isNullOrEmpty(getRequestRoleDescription()))
		       {
		           ret.append(" (" +getRequestRoleDescription()+ ")");
		       }
		      
		       return ret.toString().trim();
		   }
}