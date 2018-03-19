package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class MessagesMainVO extends SMSBaseVO{
	protected List<MessagesVO> messagesVOs;
	protected List<ScrapMessageVO> scrapMessageVOs;
	protected List<ViewCircularUsersVO> circularMessagesVOs;
	protected List<ReplyScrapMessageVO> replyScrapMessageVOs;
	
	public List<MessagesVO> getMessagesVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.messagesVOs))
		{
			this.messagesVOs = new ArrayList<MessagesVO>(); 
		}
		return messagesVOs;
	}
	public void setMessagesVOs(List<MessagesVO> messagesVOs) {
		this.messagesVOs = messagesVOs;
	}
	public List<ScrapMessageVO> getScrapMessageVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.scrapMessageVOs))
		{
			this.scrapMessageVOs = new ArrayList<ScrapMessageVO>(); 
		}
		return scrapMessageVOs;
	}
	public void setScrapMessageVOs(List<ScrapMessageVO> scrapMessageVOs) {
		this.scrapMessageVOs = scrapMessageVOs;
	}
	public List<ViewCircularUsersVO> getCircularMessagesVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.circularMessagesVOs))
		{
			this.circularMessagesVOs = new ArrayList<ViewCircularUsersVO>(); 
		}
		return circularMessagesVOs;
	}
	public void setCircularMessagesVOs(List<ViewCircularUsersVO> circularMessagesVOs) {
		this.circularMessagesVOs = circularMessagesVOs;
	}
	public List<ReplyScrapMessageVO> getReplyScrapMessageVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.replyScrapMessageVOs))
		{
			this.replyScrapMessageVOs = new ArrayList<ReplyScrapMessageVO>(); 
		}
		return replyScrapMessageVOs;
	}
	public void setReplyScrapMessageVOs(
			List<ReplyScrapMessageVO> replyScrapMessageVOs) {
		this.replyScrapMessageVOs = replyScrapMessageVOs;
	}
	
	

	

}
