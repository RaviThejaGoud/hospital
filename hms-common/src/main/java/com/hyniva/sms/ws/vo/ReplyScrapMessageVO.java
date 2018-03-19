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

public class ReplyScrapMessageVO {
   
     public String title;
     public String scrapDescription;
     public String scrapDate;
     public long senderAccount;
     public long receiverAccount;
     public String messageType;
     public long id;
     public long scrapMesssageId;
     public String senderName;
     public String status;
     public long custId;
     
     
     
     
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getScrapDescription() {
		return scrapDescription;
	}
	public void setScrapDescription(String scrapDescription) {
		this.scrapDescription = scrapDescription;
	}
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getScrapMesssageId() {
		return scrapMesssageId;
	}
	public void setScrapMesssageId(long scrapMesssageId) {
		this.scrapMesssageId = scrapMesssageId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public long getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(long senderAccount) {
		this.senderAccount = senderAccount;
	}
	public long getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(long receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	public String getScrapDate() {
		return scrapDate;
	}
	public void setScrapDate(String scrapDate) {
		this.scrapDate = scrapDate;
	}
}