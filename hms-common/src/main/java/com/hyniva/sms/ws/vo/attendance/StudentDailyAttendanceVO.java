package com.hyniva.sms.ws.vo.attendance;

public class StudentDailyAttendanceVO {
	
	private String morningSession;
	private String afternoonSession;
	private Long studentId;
	
	/**
	 * @return the morningSession
	 */
	public String getMorningSession() {
		return morningSession;
	}
	/**
	 * @param morningSession the morningSession to set
	 */
	public void setMorningSession(String morningSession) {
		this.morningSession = morningSession;
	}
	/**
	 * @return the studentId
	 */
	public Long getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the afternoonSession
	 */
	public String getAfternoonSession() {
		return afternoonSession;
	}
	/**
	 * @param afternoonSession the afternoonSession to set
	 */
	public void setAfternoonSession(String afternoonSession) {
		this.afternoonSession = afternoonSession;
	}
	
	
	
}
