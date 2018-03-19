package com.urt.persistence.model.manuatimetable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "vw_timeTableStaffDetails")
public class ViewTimetableStaffDetails implements Serializable, Cloneable, Comparable {
	
	private long id;
	private long dayId;
	private long timeTablePeriodId;
	private long studyClassId;
	private long studySubjectId;
	private long timeTableSettingId;
	private String subjectName;
	private String subjectNumber;
	private String periodName;
	private Date startTime;
	private Date endTime;
	private long custId;
    private long academicYearId;
    private boolean language;
    private String subjectType;
    private long classTeacherId;
    private long staffId;
    private String staffName;
    private String timeTableDetailsStatus;
    private String classAndSection;
    private String periodSubjectFullClassName;
    private String periodIdSubjectIdStudyClassId;

    @Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDayId() {
		return dayId;
	}
	public void setDayId(long dayId) {
		this.dayId = dayId;
	}
	public long getTimeTablePeriodId() {
		return timeTablePeriodId;
	}
	public void setTimeTablePeriodId(long timeTablePeriodId) {
		this.timeTablePeriodId = timeTablePeriodId;
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
	public long getTimeTableSettingId() {
		return timeTableSettingId;
	}
	public void setTimeTableSettingId(long timeTableSettingId) {
		this.timeTableSettingId = timeTableSettingId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectNumber() {
		return subjectNumber;
	}
	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	@Column(name = "language", nullable = true, length = 1, columnDefinition = "char(1) default 'N'")
	@Type(type = "yes_no")
	public boolean isLanguage() {
		return language;
	}
	public void setLanguage(boolean language) {
		this.language = language;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public long getClassTeacherId() {
		return classTeacherId;
	}
	public void setClassTeacherId(long classTeacherId) {
		this.classTeacherId = classTeacherId;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getTimeTableDetailsStatus() {
		return timeTableDetailsStatus;
	}
	public void setTimeTableDetailsStatus(String timeTableDetailsStatus) {
		this.timeTableDetailsStatus = timeTableDetailsStatus;
	}
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	@Transient
	public String getPeriodSubjectFullClassName() {
		return periodSubjectFullClassName;
	}
	public void setPeriodSubjectFullClassName(String periodSubjectFullClassName) {
		this.periodSubjectFullClassName = periodSubjectFullClassName;
	}
	@Transient
	public String getPeriodIdSubjectIdStudyClassId() {
		return periodIdSubjectIdStudyClassId;
	}
	public void setPeriodIdSubjectIdStudyClassId(String periodIdSubjectIdStudyClassId) {
		this.periodIdSubjectIdStudyClassId = periodIdSubjectIdStudyClassId;
	}
}
