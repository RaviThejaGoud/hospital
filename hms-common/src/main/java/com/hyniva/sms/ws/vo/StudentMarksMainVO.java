package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentMarksMainVO extends SMSBaseVO{
	protected List<StudentMarksVO> studentMarksVOList;
	
		public List<StudentMarksVO> getStudentMarksVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.studentMarksVOList))
		{
			this.studentMarksVOList = new ArrayList<StudentMarksVO>(); 
		}
		return studentMarksVOList;
	}

	public void setStudentMarksVOList(List<StudentMarksVO> studentMarksVOList) {
		this.studentMarksVOList = studentMarksVOList;
	}
	
}
		
 