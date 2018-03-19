package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_AcademicYearTimings") 
public class VWAcademicYearTimings implements java.io.Serializable{
	
	private long academicYearTimingId;
	private long academicYearId;
	private String startTime;
	private String endTime;
	private String morningBreakStartTime;
	private String morningBreakEndTime;
	private String lunchStartTime;
	private String lunchEndTime;
	private String eveningBreakStartTime;
	private String eveningBreakEndTime;
	private String className;
	private String weekName;
	private long classId;
	private String weekDay;
	private String status;
	
	@Id
	@Column(name = "academicYearTimingId", nullable = false)
    public long getAcademicYearTimingId() {
		return academicYearTimingId;
	}
	public void setAcademicYearTimingId(long academicYearTimingId) {
		this.academicYearTimingId = academicYearTimingId;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMorningBreakStartTime() {
		return morningBreakStartTime;
	}
	public void setMorningBreakStartTime(String morningBreakStartTime) {
		this.morningBreakStartTime = morningBreakStartTime;
	}
	public String getMorningBreakEndTime() {
		return morningBreakEndTime;
	}
	public void setMorningBreakEndTime(String morningBreakEndTime) {
		this.morningBreakEndTime = morningBreakEndTime;
	}
	public String getLunchStartTime() {
		return lunchStartTime;
	}
	public void setLunchStartTime(String lunchBreakStartTime) {
		this.lunchStartTime = lunchBreakStartTime;
	}
	public String getLunchEndTime() {
		return lunchEndTime;
	}
	public void setLunchEndTime(String lunchBreakEndTime) {
		this.lunchEndTime = lunchBreakEndTime;
	}
	public String getEveningBreakStartTime() {
		return eveningBreakStartTime;
	}
	public void setEveningBreakStartTime(String eveningBreakStartTime) {
		this.eveningBreakStartTime = eveningBreakStartTime;
	}
	public String getEveningBreakEndTime() {
		return eveningBreakEndTime;
	}
	public void setEveningBreakEndTime(String eveningBreakEndTime) {
		this.eveningBreakEndTime = eveningBreakEndTime;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getWeekName() {
		return weekName;
	}
	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
