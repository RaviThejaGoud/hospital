package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentMonthlyAttendanceMainVO extends SMSBaseVO{
	
	protected List<StudentMonthlyAttendanceVO> studentMonthlyAttendanceVOs;

	public List<StudentMonthlyAttendanceVO> getStudentMonthlyAttendanceVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.studentMonthlyAttendanceVOs))
		{
			this.studentMonthlyAttendanceVOs = new ArrayList<StudentMonthlyAttendanceVO>(); 
		}
		return studentMonthlyAttendanceVOs;
	}

	public void setStudentMonthlyAttendanceVOs(List<StudentMonthlyAttendanceVO> studentMonthlyAttendanceVOs) {
		this.studentMonthlyAttendanceVOs = studentMonthlyAttendanceVOs;
	}
	
	
}
