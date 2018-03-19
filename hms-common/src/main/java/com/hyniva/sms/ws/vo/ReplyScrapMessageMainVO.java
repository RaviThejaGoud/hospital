package com.hyniva.sms.ws.vo;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ReplyScrapMessageMainVO extends SMSBaseVO{
	
	private String title;
    private String scrapDescription;
    private String scrapDate;
    private long senderAccount;
    private long receiverAccount;
    private String messageType;
    private long id;
    private long scrapMesssageId;
    private String senderName;
    
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
	
	/*protected List<ReplyScrapMessageVO> replyScrapMessageVOs;

	public List<ReplyScrapMessageVO> getReplyScrapMessageVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.replyScrapMessageVOs))
		{
			this.replyScrapMessageVOs = new ArrayList<ReplyScrapMessageVO>(); 
		}
		return replyScrapMessageVOs;
	}

	public void setReplyScrapMessageVO(List<ReplyScrapMessageVO> replyScrapMessageVOs) {
		this.replyScrapMessageVOs = replyScrapMessageVOs;
	}*/
	

}
