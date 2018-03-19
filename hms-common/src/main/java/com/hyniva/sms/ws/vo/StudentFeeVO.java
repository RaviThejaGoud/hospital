package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentFeeVO extends SMSBaseVO{

	protected List<StudentFeeDetailsVO> studentFeeDetailsVOs;

	public List<StudentFeeDetailsVO> getStudentFeeVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.studentFeeDetailsVOs))
		{
			this.studentFeeDetailsVOs = new ArrayList<StudentFeeDetailsVO>(); 
		}
		return studentFeeDetailsVOs;
	}

	public void setStudentFeeVOs(
			List<StudentFeeDetailsVO> studentFeeDetailsVOs) {
		this.studentFeeDetailsVOs = studentFeeDetailsVOs;
	}

	
}
