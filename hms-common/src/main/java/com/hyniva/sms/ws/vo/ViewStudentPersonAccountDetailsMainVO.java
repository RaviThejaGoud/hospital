package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewStudentPersonAccountDetailsMainVO extends SMSBaseVO{

	protected List<ViewStudentPersonAccountDetailsVO> viewStudentPersonAccountDetailsVOs;

	public List<ViewStudentPersonAccountDetailsVO> getViewStudentPersonAccountDetailsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.viewStudentPersonAccountDetailsVOs))
		{
			this.viewStudentPersonAccountDetailsVOs = new ArrayList<ViewStudentPersonAccountDetailsVO>(); 
		}
		return viewStudentPersonAccountDetailsVOs;
	}

	public void setViewStudentPersonAccountDetailsVOs(List<ViewStudentPersonAccountDetailsVO> viewStudentPersonAccountDetailsVOs) {
		this.viewStudentPersonAccountDetailsVOs = viewStudentPersonAccountDetailsVOs;
	}
	

}
