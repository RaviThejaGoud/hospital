package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;

public class StudentTimetableDayVO {

	protected long id;
	protected String day;
	protected List<StudentTimetablePeriodVO> periods;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public List<StudentTimetablePeriodVO> getTimetablePeriods() {
		if(ObjectFunctions.isNullOrEmpty(this.periods))
			this.periods = new ArrayList<StudentTimetablePeriodVO>(); 

		return periods;
	}
	public void setTimetablePeriods(List<StudentTimetablePeriodVO> periods) {
		this.periods = periods;
	}
}