package com.hyniva.sms.ws.vo.attendance;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentAndStaffDailyAttendanceVO extends SMSBaseVO{
	
	private String morningSessionStudentIds;
	private String morningSessionStaffIds;
	private String afternoonSessionStudentIds;
	private String afternoonSessionStaffIds;
	/**
	 * @return the morningSessionStudentIds
	 */
	public String getMorningSessionStudentIds() {
		return morningSessionStudentIds;
	}
	/**
	 * @param morningSessionStudentIds the morningSessionStudentIds to set
	 */
	public void setMorningSessionStudentIds(String morningSessionStudentIds) {
		this.morningSessionStudentIds = morningSessionStudentIds;
	}
	/**
	 * @return the morningSessionStaffIds
	 */
	public String getMorningSessionStaffIds() {
		return morningSessionStaffIds;
	}
	/**
	 * @param morningSessionStaffIds the morningSessionStaffIds to set
	 */
	public void setMorningSessionStaffIds(String morningSessionStaffIds) {
		this.morningSessionStaffIds = morningSessionStaffIds;
	}
	/**
	 * @return the afternoonSessionStudentIds
	 */
	public String getAfternoonSessionStudentIds() {
		return afternoonSessionStudentIds;
	}
	/**
	 * @param afternoonSessionStudentIds the afternoonSessionStudentIds to set
	 */
	public void setAfternoonSessionStudentIds(String afternoonSessionStudentIds) {
		this.afternoonSessionStudentIds = afternoonSessionStudentIds;
	}
	/**
	 * @return the afternoonSessionStaffIds
	 */
	public String getAfternoonSessionStaffIds() {
		return afternoonSessionStaffIds;
	}
	/**
	 * @param afternoonSessionStaffIds the afternoonSessionStaffIds to set
	 */
	public void setAfternoonSessionStaffIds(String afternoonSessionStaffIds) {
		this.afternoonSessionStaffIds = afternoonSessionStaffIds;
	}
	
	
	
	
	
}
