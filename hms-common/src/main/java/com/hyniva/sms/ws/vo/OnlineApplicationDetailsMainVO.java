package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class OnlineApplicationDetailsMainVO extends SMSBaseVO{
	
	protected List<OnlineApplicationDetailsVO> onlineApplicationDetailsVOList;
	protected List<StudentVo> studentVoList;
	 
	public List<StudentVo> getStudentVoList() {
		if(ObjectFunctions.isNullOrEmpty(this.studentVoList))
		{
			this.studentVoList = new ArrayList<StudentVo>(); 
		}
		return studentVoList;
	}
	public void setStudentVoList(List<StudentVo> studentVoList) {
		this.studentVoList = studentVoList;
	}

	public List<OnlineApplicationDetailsVO> getOnlineApplicationDetailsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.onlineApplicationDetailsVOList))
		{
			this.onlineApplicationDetailsVOList = new ArrayList<OnlineApplicationDetailsVO>(); 
		}
		return onlineApplicationDetailsVOList;
	}

	public void setOnlineApplicationDetailsVOList(
			List<OnlineApplicationDetailsVO> onlineApplicationDetailsVOList) {
		this.onlineApplicationDetailsVOList = onlineApplicationDetailsVOList;
	}
	
}
