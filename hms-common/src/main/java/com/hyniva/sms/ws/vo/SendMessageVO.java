/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Scraps.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Feb 03, 2011       Narahari          Created
/ ********************************************************************/

package com.hyniva.sms.ws.vo;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;
public class SendMessageVO  extends SMSBaseVO{
   
     private String subject;
     private String description;
     private long senderAccountId;
     private String receiverAccountIds;
     
     
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
	public long getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	public String getReceiverAccountIds() {
		return receiverAccountIds;
	}
	public void setReceiverAccountIds(String receiverAccountIds) {
		this.receiverAccountIds = receiverAccountIds;
	}
	

	
}