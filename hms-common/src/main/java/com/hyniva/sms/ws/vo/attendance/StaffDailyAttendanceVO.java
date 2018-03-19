package com.hyniva.sms.ws.vo.attendance;

import java.util.List;


public class StaffDailyAttendanceVO {
	
	private String attendanceDate;
	
	List<StaffAttendanceVO>  attendance;

	/**
	 * @return the attendanceDate
	 */
	public String getAttendanceDate() {
		return attendanceDate;
	}

	/**
	 * @param attendanceDate the attendanceDate to set
	 */
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	/**
	 * @return the attendance
	 */
	public List<StaffAttendanceVO> getAttendance() {
		return attendance;
	}

	/**
	 * @param attendance the attendance to set
	 */
	public void setAttendance(List<StaffAttendanceVO> attendance) {
		this.attendance = attendance;
	}
	
	
	
	
}
