package com.hyniva.sms.ws.vo;


public class MessagesVO {
	
	private String title;
	private String status;
	private String purposeType;
	protected String senderName;
	private String messageDescription;
	private String messageDate;
	protected long id;
	private String receiverType;
	private String hostlerStatus;
	private String otherType;
	private String staffType;
	private String messageType;
	private String studentAccountIds;
	private String classIds;
	private String emailContent;
	private String messageSalutation;
	private String emailIds;
	private String otherMobileNos;
    private String channel ; 
	
	
	public String getOtherMobileNos() {
		return otherMobileNos;
	}
	public void setOtherMobileNos(String otherMobileNos) {
		this.otherMobileNos = otherMobileNos;
	}
	public String getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(String emailIds) {
		this.emailIds = emailIds;
	}
	public String getMessageSalutation() {
		return messageSalutation;
	}
	public void setMessageSalutation(String messageSalutation) {
		this.messageSalutation = messageSalutation;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public String getReceiverType() {
		return receiverType;
	}
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}
	public String getHostlerStatus() {
		return hostlerStatus;
	}
	public void setHostlerStatus(String hostlerStatus) {
		this.hostlerStatus = hostlerStatus;
	}
	public String getOtherType() {
		return otherType;
	}
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getStudentAccountIds() {
		return studentAccountIds;
	}
	public void setStudentAccountIds(String studentAccountIds) {
		this.studentAccountIds = studentAccountIds;
	}
	public String getClassIds() {
		return classIds;
	}
	public void setClassIds(String classIds) {
		this.classIds = classIds;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurposeType() {
		return purposeType;
	}
	public void setPurposeType(String purposeType) {
		this.purposeType = purposeType;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
} 
