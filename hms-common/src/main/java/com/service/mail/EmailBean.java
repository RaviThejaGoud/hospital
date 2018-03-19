package com.service.mail;

public class EmailBean {

	 private String body;
	 private String fromAddress;
	 private String[] toAddress;
	 private String subject;
	 private boolean blind;
	 private String[] ccAddress;
	 private String[] bccAddress;
	 private long customerId;
	 private String fromWhom;
	 private String type;
	 private String sender;
	 private String[] attachmentFiles;
	
	 
	 
	public String[] getAttachmentFiles() {
		return attachmentFiles;
	}
	public void setAttachmentFiles(String[] attachmentFiles) {
		this.attachmentFiles = attachmentFiles;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	public String[] getToAddress() {
		return toAddress;
	}
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}
	public String[] getBccAddress() {
		return bccAddress;
	}
	public void setBccAddress(String[] bccAddress) {
		this.bccAddress = bccAddress;
	}
	public String[] getCcAddress() {
		return ccAddress;
	}
	public void setCcAddress(String[] ccAddress) {
		this.ccAddress = ccAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public boolean getBlind() {
		return blind;
	}
	public void setBlind(boolean blind) {
		this.blind = blind;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getFromWhom() {
		return fromWhom;
	}
	public void setFromWhom(String fromWhom) {
		this.fromWhom = fromWhom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public EmailBean(String body, String fromAddress, String[] toAddress,
			String subject, boolean blind ,String[] ccaddress, String[] bccAddress) {
		super();
		this.body = body;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.subject = subject;
		this.blind = blind;
		this.ccAddress=ccaddress;
		this.bccAddress=bccAddress;
	}
	public EmailBean() {
		super();
		// TODO Auto-generated constructor stub
	}	 
	 
}
