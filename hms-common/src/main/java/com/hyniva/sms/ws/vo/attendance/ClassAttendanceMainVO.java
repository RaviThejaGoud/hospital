package com.hyniva.sms.ws.vo.attendance;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ClassAttendanceMainVO extends SMSBaseVO{
	
	protected String sms;
	protected List<ClassAttendanceVO> classAttendance;

	
	public String getSMS() {
		return sms;
	}

	public void setSMS(String sms) {
		this.sms = sms;
	}

	/**
	 * @return the classAttendance
	 */
	public List<ClassAttendanceVO> getClassAttendance() {
		if(ObjectFunctions.isNullOrEmpty(this.classAttendance))
		{
			this.classAttendance = new ArrayList<ClassAttendanceVO>(); 
		}
		return classAttendance;
	}

	/**
	 * @param classAttendance the classAttendance to set
	 */
	public void setClassAttendance(List<ClassAttendanceVO> classAttendance) {
		this.classAttendance = classAttendance;
	}
	
}
