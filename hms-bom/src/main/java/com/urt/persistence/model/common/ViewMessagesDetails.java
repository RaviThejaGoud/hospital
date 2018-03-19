package com.urt.persistence.model.common;

/********************************************************************
 * Copyright (C) 2016-17

 * IFS
 * All Rights Reserved 
 *
 * File: ViewMessagesDetails.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0  Jul 18, 2016        RaviTeja Panem           Created
 / ********************************************************************/
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;

/**
 * Represents a person's full name
 */@Entity
@Table(name = "vw_messagesDetails")
public class ViewMessagesDetails implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;
	private static final int STRING_BUFFER_SIZE = 1024;

	    private long accountId;
	    private long messageId;
	    private long custId;
	    private long academicYearId;
	    private String status;
	    private String failedMobileNos;
	    private String invalidMobileNos;
	    private String senderName;
	    private Date messageDate;
	    protected int smsCount;
	    private String messageDescription;
	    private Date createdDate;
	    private String roleName;
	    private long createdById;
	    private long sentSms;
	    
		public long getAccountId() {
			return accountId;
		}
		public void setAccountId(long accountId) {
			this.accountId = accountId;
		}
		@Id
		public long getMessageId() {
			return messageId;
		}
		public void setMessageId(long messageId) {
			this.messageId = messageId;
		}
		public long getCustId() {
			return custId;
		}
		public void setCustId(long custId) {
			this.custId = custId;
		}
		public long getAcademicYearId() {
			return academicYearId;
		}
		public void setAcademicYearId(long academicYearId) {
			this.academicYearId = academicYearId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getFailedMobileNos() {
			return failedMobileNos;
		}
		public void setFailedMobileNos(String failedMobileNos) {
			this.failedMobileNos = failedMobileNos;
		}
		public String getInvalidMobileNos() {
			return invalidMobileNos;
		}
		public void setInvalidMobileNos(String invalidMobileNos) {
			this.invalidMobileNos = invalidMobileNos;
		}
		public String getSenderName() {
			return senderName;
		}
		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}
		public Date getMessageDate() {
			return messageDate;
		}
		public void setMessageDate(Date messageDate) {
			this.messageDate = messageDate;
		}
		public int getSmsCount() {
			return smsCount;
		}
		public void setSmsCount(int smsCount) {
			this.smsCount = smsCount;
		}
		@Column(length = 10000)
		public String getMessageDescription() {
			return messageDescription;
		}
		public void setMessageDescription(String messageDescription) {
			this.messageDescription = messageDescription;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		
		public long getCreatedById() {
			return createdById;
		}
		public void setCreatedById(long createdById) {
			this.createdById = createdById;
		}
		
		public long getSentSms() {
			return sentSms;
		}
		public void setSentSms(long sentSms) {
			this.sentSms = sentSms;
		}
		@Transient
	    public String getSenderNameStr() {
			if(this.getCreatedById()>0){
				return this.getSenderName()+"<br/>"+"("+this.getRoleName()+")";
			}else
				return "System Generated";

	    }
		@Transient
	    public String getMessageDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.messageDate);

	    }
		
		 @Transient
		 public String getSortingMessageDate() {
			return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.messageDate);
		 }
	   
}
