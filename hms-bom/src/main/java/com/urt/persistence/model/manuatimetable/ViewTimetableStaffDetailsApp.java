package com.urt.persistence.model.manuatimetable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "vw_timeTableStaffDetailsApp")
public class ViewTimetableStaffDetailsApp implements Serializable, Cloneable, Comparable {
	
	private long id;
	private long dayId;
	private long timeTablePeriodId;
	private long studyClassId;
	private long batchId;
	private long studySubjectId;
	private long timeTableSettingId;
	private String subjectName;
	private String subjectShortName;
	private String periodName;
	private Date startTime;
	private Date endTime;
	private long custId;
    private long academicDetailsId;
    private boolean practical;
    private int noOfPeriods;
    private String subjectType;
    private long classTeacherId;
    private long staffId;
    private String staffName;
    private String timeTableDetailsStatus;

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
	public long getBatchId() {
		return batchId;
	}
	public void setBatchId(long batchId) {
		this.batchId = batchId;
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
	public long getAcademicDetailsId() {
		return academicDetailsId;
	}
	public void setAcademicDetailsId(long academicDetailsId) {
		this.academicDetailsId = academicDetailsId;
	}
	@Column(name = "practical", nullable = true, length = 1, columnDefinition = "char(1) default 'N'")
	@Type(type = "yes_no")
	public boolean isPractical() {
		return practical;
	}
	public void setPractical(boolean practical) {
		this.practical = practical;
	}
	public int getNoOfPeriods() {
		return noOfPeriods;
	}
	public void setNoOfPeriods(int noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
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
	public String getSubjectShortName() {
		return subjectShortName;
	}
	public void setSubjectShortName(String subjectShortName) {
		this.subjectShortName = subjectShortName;
	}
}
