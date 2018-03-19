package com.hyniva.sms.ws.vo.attendance;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentDailyAttendanceMainVO extends SMSBaseVO{
	
	private List<StudentDailyAttendanceVO> studentDailyAttendance;

	/**
	 * @return the studentDailyAttendance
	 */
	public List<StudentDailyAttendanceVO> getStudentDailyAttendance() {
		if(ObjectFunctions.isNullOrEmpty(this.studentDailyAttendance))
		{
			this.studentDailyAttendance = new ArrayList<StudentDailyAttendanceVO>(); 
		}
		return studentDailyAttendance;
	}

	/**
	 * @param studentDailyAttendance the studentDailyAttendance to set
	 */
	public void setStudentDailyAttendance(
			List<StudentDailyAttendanceVO> studentDailyAttendance) {
		this.studentDailyAttendance = studentDailyAttendance;
	}


	
	
}
