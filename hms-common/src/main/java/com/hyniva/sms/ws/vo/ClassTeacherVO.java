package com.hyniva.sms.ws.vo;


public class ClassTeacherVO {
	protected String classTeacher; // is class teacher
	protected long studyClassId;
	protected long studySubjectId;
	protected String subjectName;
	protected int periodsCount;
	protected long staffId;
	
	public String getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public long getStudySubjectId() {
		return studySubjectId;
	}
	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getPeriodsCount() {
		return periodsCount;
	}
	public void setPeriodsCount(int periodsCount) {
		this.periodsCount = periodsCount;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	
	
}
