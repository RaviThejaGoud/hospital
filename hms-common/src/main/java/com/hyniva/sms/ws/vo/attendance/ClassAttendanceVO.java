package com.hyniva.sms.ws.vo.attendance;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;

public class ClassAttendanceVO {
	
	protected String attendanceDate;
	protected long classId;
	protected List<StudentDailyAttendanceVO> studentDailyAttendance;
	
	
	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

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
