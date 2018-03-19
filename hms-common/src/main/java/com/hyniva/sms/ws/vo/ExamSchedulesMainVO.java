package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ExamSchedulesMainVO extends SMSBaseVO {
	
	protected List<ExamSchedulesVO> examSchedulesVOList;

	public List<ExamSchedulesVO> getExamSchedulesVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.examSchedulesVOList))
		{
			this.examSchedulesVOList = new ArrayList<ExamSchedulesVO>(); 
		}
		return examSchedulesVOList;
	}

	public void setExamSchedulesVOList(List<ExamSchedulesVO> examSchedulesVOList) {
		this.examSchedulesVOList = examSchedulesVOList;
	}
	
}
