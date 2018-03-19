package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class EventMainVO extends SMSBaseVO{
	protected List<EventsVO> eventsVoList;

	public List<EventsVO> getEventsVoList() {
		if(ObjectFunctions.isNullOrEmpty(this.eventsVoList))
		{
			this.eventsVoList = new ArrayList<EventsVO>(); 
		}
		return eventsVoList;
	}

	public void setEventsVoList(List<EventsVO> eventsVoList) {
		this.eventsVoList = eventsVoList;
	}
	

}
