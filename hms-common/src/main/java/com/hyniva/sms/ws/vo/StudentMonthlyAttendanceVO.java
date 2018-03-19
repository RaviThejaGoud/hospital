package com.hyniva.sms.ws.vo;

public class StudentMonthlyAttendanceVO {
	
	protected long studentId;
	protected String monthName;
	protected String totalWorkingDays;
	protected String presentiesCount;
	protected String absentiesCount;
	protected String percentage;
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getTotalWorkingDays() {
		return totalWorkingDays;
	}
	public void setTotalWorkingDays(String totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	public String getPresentiesCount() {
		return presentiesCount;
	}
	public void setPresentiesCount(String presentiesCount) {
		this.presentiesCount = presentiesCount;
	}
	public String getAbsentiesCount() {
		return absentiesCount;
	}
	public void setAbsentiesCount(String absentiesCount) {
		this.absentiesCount = absentiesCount;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	
}
