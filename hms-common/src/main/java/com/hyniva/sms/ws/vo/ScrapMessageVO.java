package com.hyniva.sms.ws.vo;

public class ScrapMessageVO {
	
	 public String title;
	 public String scrapDescription;
	 public String status;
	 public String scrapDate;
	 public String isNewMessage;
	 public long senderAccountId;
	 public long receiverAccountId;
	 public String isNewReply;
	 public String messageType;
	 public long id;
	 public String senderName;
	 public long custId;
	 
	 
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	public long getReceiverAccountId() {
		return receiverAccountId;
	}
	public void setReceiverAccountId(long receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getIsNewMessage() {
		return isNewMessage;
	}
	public void setIsNewMessage(String isNewMessage) {
		this.isNewMessage = isNewMessage;
	}
	/*public String getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}*/
	public String getIsNewReply() {
		return isNewReply;
	}
	public void setIsNewReply(String isNewReply) {
		this.isNewReply = isNewReply;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getScrapDate() {
		return scrapDate;
	}
	public void setScrapDate(String scrapDate) {
		this.scrapDate = scrapDate;
	}
	
     
	

}
