package com.hyniva.sms.ws.vo;

import java.util.List;

public class MeetingSchedulesDetailsVO {

	protected long id;
	protected String date;
	protected String description;
	protected long staffId;
	protected long staffAccountId;
	protected String staffName;
	protected long studyClassId;
	protected String className;
	protected String section;
	protected List<MeetingScheduleSlotsVO> meetingSlots;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public long getStaffAccountId() {
		return staffAccountId;
	}
	public void setStaffAccountId(long staffAccountId) {
		this.staffAccountId = staffAccountId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public List<MeetingScheduleSlotsVO> getMeetingSlots() {
		return meetingSlots;
	}
	public void setMeetingSlots(List<MeetingScheduleSlotsVO> meetingSlots) {
		this.meetingSlots = meetingSlots;
	}
}