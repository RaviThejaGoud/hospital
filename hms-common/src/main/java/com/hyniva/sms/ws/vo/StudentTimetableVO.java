package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentTimetableVO extends SMSBaseVO{

	protected List<StudentTimetableDetailsVO> studentTimetableDetailsVOs;

	public List<StudentTimetableDetailsVO> getStudentTimetableVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.studentTimetableDetailsVOs))
			this.studentTimetableDetailsVOs = new ArrayList<StudentTimetableDetailsVO>(); 
		
		return studentTimetableDetailsVOs;
	}

	public void setStudentTimetableVOs(List<StudentTimetableDetailsVO> studentTimetableDetailsVOs) {
		this.studentTimetableDetailsVOs = studentTimetableDetailsVOs;
	}

	
}
