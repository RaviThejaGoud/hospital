package com.hyniva.sms.ws.vo;


public class StudentMarksVO {
	
	protected long studentId;
	protected long scheduleId;
	protected double subjectMarks;
	protected String isPresent;
	protected double subjectHighestMarks;
	protected double obtainedMarks;
	protected String subjectName;
	protected String passOrFail;
	private String subTypeName;
	private String subjectResult;
	
	
	
	
	
	public String getSubjectResult() {
		return subjectResult;
	}
	public void setSubjectResult(String subjectResult) {
		this.subjectResult = subjectResult;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public String getPassOrFail() {
		return passOrFail;
	}
	public void setPassOrFail(String passOrFail) {
		this.passOrFail = passOrFail;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public double getObtainedMarks() {
		return obtainedMarks;
	}
	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	public double getSubjectMarks() {
		return subjectMarks;
	}
	public void setSubjectMarks(double subjectMarks) {
		this.subjectMarks = subjectMarks;
	}
	public double getSubjectHighestMarks() {
		return subjectHighestMarks;
	}
	public void setSubjectHighestMarks(double subjectHighestMarks) {
		this.subjectHighestMarks = subjectHighestMarks;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(String isPresent) {
		this.isPresent = isPresent;
	}

}
