package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class MeetingSchedulesVO extends SMSBaseVO{

	protected List<MeetingSchedulesDetailsVO> meetingSchedulesDetailsVO;

	public List<MeetingSchedulesDetailsVO> getMeetingSchedulesVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.meetingSchedulesDetailsVO))
			this.meetingSchedulesDetailsVO = new ArrayList<MeetingSchedulesDetailsVO>(); 
		
		return meetingSchedulesDetailsVO;
	}

	public void setMeetingSchedulesVOs(List<MeetingSchedulesDetailsVO> meetingSchedulesDetailsVO) {
		this.meetingSchedulesDetailsVO = meetingSchedulesDetailsVO;
	}

	
}
