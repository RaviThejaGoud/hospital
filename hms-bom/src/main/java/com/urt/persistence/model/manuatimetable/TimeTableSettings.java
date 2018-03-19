package com.urt.persistence.model.manuatimetable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author JR
 *
 */
@Entity
@Table(name = "timeTableSettings")
public class TimeTableSettings extends PersistentObject {

	private long custId;
    private long academicYearId;
	private String collegeStartTime;
	private String collegeEndTime;
	private int noOfPeriodsPerDay;
	private String durationOfEachPeriod;
	private int shortBreakAfterNoOfPeriodsMorningSession;
	private int shortBreakAfterNoOfPeriodsAfternoonSession;
	private int shortBreakMorningSessionDuration;
	private int shortBreakAfternoonSessionDuration;
	private int durationOfLunchBreak;
	private int lunchBreakAfterNoOfPeriods;
	protected List<TimeTablePeriod> timeTablePeriodList;
	
	
	public void addTimeTablePeriod(TimeTablePeriod timeTablePeriodsObj) {
        if(ObjectFunctions.isNullOrEmpty(getTimeTablePeriodList())){
          this.timeTablePeriodList=new ArrayList<TimeTablePeriod>();
        }
        getTimeTablePeriodList().add(timeTablePeriodsObj);
    }
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="timeTableSettingId")
	public List<TimeTablePeriod> getTimeTablePeriodList() {
		return timeTablePeriodList;
	}
	public void setTimeTablePeriodList(List<TimeTablePeriod> timeTablePeriodList) {
		this.timeTablePeriodList = timeTablePeriodList;
	}
	@Column(name = "custId",nullable = false, columnDefinition=" int default 0")
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(name = "academicYearId",nullable = false, columnDefinition=" int default 0")
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getCollegeStartTime() {
		return collegeStartTime;
	}
	public void setCollegeStartTime(String collegeStartTime) {
		this.collegeStartTime = collegeStartTime;
	}
	@Column(name = "shortBreakAfterNoOfPeriodsMorningSession",nullable = false, columnDefinition=" int default 0")
	public int getShortBreakAfterNoOfPeriodsMorningSession() {
		return shortBreakAfterNoOfPeriodsMorningSession;
	}
	public void setShortBreakAfterNoOfPeriodsMorningSession(
			int shortBreakAfterNoOfPeriodsMorningSession) {
		this.shortBreakAfterNoOfPeriodsMorningSession = shortBreakAfterNoOfPeriodsMorningSession;
	}
	@Column(name = "shortBreakAfterNoOfPeriodsAfternoonSession",nullable = false, columnDefinition=" int default 0")
	public int getShortBreakAfterNoOfPeriodsAfternoonSession() {
		return shortBreakAfterNoOfPeriodsAfternoonSession;
	}
	public void setShortBreakAfterNoOfPeriodsAfternoonSession(
			int shortBreakAfterNoOfPeriodsAfternoonSession) {
		this.shortBreakAfterNoOfPeriodsAfternoonSession = shortBreakAfterNoOfPeriodsAfternoonSession;
	}
	@Column(name = "shortBreakMorningSessionDuration",nullable = false, columnDefinition=" int default 0")
	public int getShortBreakMorningSessionDuration() {
		return shortBreakMorningSessionDuration;
	}

	public void setShortBreakMorningSessionDuration(
			int shortBreakMorningSessionDuration) {
		this.shortBreakMorningSessionDuration = shortBreakMorningSessionDuration;
	}
	@Column(name = "shortBreakAfternoonSessionDuration",nullable = false, columnDefinition=" int default 0")
	public int getShortBreakAfternoonSessionDuration() {
		return shortBreakAfternoonSessionDuration;
	}

	public void setShortBreakAfternoonSessionDuration(
			int shortBreakAfternoonSessionDuration) {
		this.shortBreakAfternoonSessionDuration = shortBreakAfternoonSessionDuration;
	}
	@Column(name = "noOfPeriodsPerDay",nullable = false, columnDefinition=" int default 0")
	public int getNoOfPeriodsPerDay() {
		return noOfPeriodsPerDay;
	}

	public void setNoOfPeriodsPerDay(int noOfPeriodsPerDay) {
		this.noOfPeriodsPerDay = noOfPeriodsPerDay;
	}
	public String getDurationOfEachPeriod() {
		return durationOfEachPeriod;
	}
	public void setDurationOfEachPeriod(String durationOfEachPeriod) {
		this.durationOfEachPeriod = durationOfEachPeriod;
	}
	@Column(name = "durationOfLunchBreak",nullable = false, columnDefinition=" int default 0")
	public int getDurationOfLunchBreak() {
		return durationOfLunchBreak;
	}
	public void setDurationOfLunchBreak(int durationOfLunchBreak) {
		this.durationOfLunchBreak = durationOfLunchBreak;
	}
	@Column(name = "lunchBreakAfterNoOfPeriods",nullable = false, columnDefinition=" int default 0")
	public int getLunchBreakAfterNoOfPeriods() {
		return lunchBreakAfterNoOfPeriods;
	}
	public void setLunchBreakAfterNoOfPeriods(int lunchBreakAfterNoOfPeriods) {
		this.lunchBreakAfterNoOfPeriods = lunchBreakAfterNoOfPeriods;
	}
	public String getCollegeEndTime() {
		return collegeEndTime;
	}
	public void setCollegeEndTime(String collegeEndTime) {
		this.collegeEndTime = collegeEndTime;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
}