package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentFeeConcessionMainVO extends SMSBaseVO{

	protected List<StudentFeeConcessionVO> studentFeeConcessionVOs;

	public List<StudentFeeConcessionVO> getStudentFeeConcessionVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.studentFeeConcessionVOs))
		{
			this.studentFeeConcessionVOs = new ArrayList<StudentFeeConcessionVO>(); 
		}
		return studentFeeConcessionVOs;
	}

	public void setStudentFeeConcessionVOs(
			List<StudentFeeConcessionVO> studentFeeConcessionVOs) {
		this.studentFeeConcessionVOs = studentFeeConcessionVOs;
	}

	
}
