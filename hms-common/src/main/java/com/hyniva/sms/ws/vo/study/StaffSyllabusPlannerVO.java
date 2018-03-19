package com.hyniva.sms.ws.vo.study;

import java.util.Date;

public class StaffSyllabusPlannerVO {
	
	private long id;
	private long staffId;
    private long studySubjectId;
    protected long custId;
    private long academicYearId;
    private Date startDate;
    private Date completedDate;
    private long studyClassId;
    protected String chapterName;
    protected String unitName;
    protected String topicName;
    private long periodsRequired;
    private long periodsTaken;
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public long getStudySubjectId() {
		return studySubjectId;
	}
	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public long getPeriodsRequired() {
		return periodsRequired;
	}
	public void setPeriodsRequired(long periodsRequired) {
		this.periodsRequired = periodsRequired;
	}
	public long getPeriodsTaken() {
		return periodsTaken;
	}
	public void setPeriodsTaken(long periodsTaken) {
		this.periodsTaken = periodsTaken;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
    
}
