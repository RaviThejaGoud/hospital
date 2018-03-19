package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentDetailsListVO extends SMSBaseVO{

	protected List<StudentDetailsVO> viewStudentPersonAccountDetailsVOs;

	public List<StudentDetailsVO> getViewStudentPersonAccountDetailsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.viewStudentPersonAccountDetailsVOs))
		{
			this.viewStudentPersonAccountDetailsVOs = new ArrayList<StudentDetailsVO>(); 
		}
		return viewStudentPersonAccountDetailsVOs;
	}

	public void setViewStudentPersonAccountDetailsVOs(List<StudentDetailsVO> viewStudentPersonAccountDetailsVOs) {
		this.viewStudentPersonAccountDetailsVOs = viewStudentPersonAccountDetailsVOs;
	}
	

}
