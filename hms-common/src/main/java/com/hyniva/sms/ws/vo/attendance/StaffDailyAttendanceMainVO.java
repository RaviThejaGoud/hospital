package com.hyniva.sms.ws.vo.attendance;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StaffDailyAttendanceMainVO extends SMSBaseVO{
	
	
	private List<StaffDailyAttendanceVO> staffDailyAttendance;

	/**
	 * @return the staffDailyAttendance
	 */
	public List<StaffDailyAttendanceVO> getStaffDailyAttendance() {
		if(ObjectFunctions.isNullOrEmpty(this.staffDailyAttendance))
		{
			this.staffDailyAttendance = new ArrayList<StaffDailyAttendanceVO>(); 
		}
		return staffDailyAttendance;
	}

	/**
	 * @param staffDailyAttendance the staffDailyAttendance to set
	 */
	public void setStaffDailyAttendance(
			List<StaffDailyAttendanceVO> staffDailyAttendance) {
		this.staffDailyAttendance = staffDailyAttendance;
	}

	
}
