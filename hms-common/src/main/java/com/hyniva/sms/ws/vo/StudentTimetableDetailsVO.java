package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;

public class StudentTimetableDetailsVO {

	protected long studyClassId;
	protected String classSectionName;
	protected List<StudentTimetableDayVO> timetableDay;
	
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public String getStudentName() {
		return classSectionName;
	}
	public void setClassSectionName(String classSectionName) {
		this.classSectionName = classSectionName;
	}
	public List<StudentTimetableDayVO> getTimetableDays() {
		if(ObjectFunctions.isNullOrEmpty(this.timetableDay))
			this.timetableDay = new ArrayList<StudentTimetableDayVO>(); 

		return timetableDay;
	}
	public void setTimetableDays(List<StudentTimetableDayVO> timetableDay) {
		this.timetableDay = timetableDay;
	}
}