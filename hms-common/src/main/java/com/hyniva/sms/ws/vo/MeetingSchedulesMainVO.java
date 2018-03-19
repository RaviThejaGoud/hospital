package com.hyniva.sms.ws.vo;

import java.util.Date;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class MeetingSchedulesMainVO extends SMSBaseVO{

	protected long id;
	protected Date fromDate;
	protected Date toDate;
	protected String description;
	protected int parentsPerHour;
	protected long studyClassId;
	protected long staffId;
	protected String isAuto;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParentsPerHour() {
		return parentsPerHour;
	}
	public void setParentsPerHour(int parentsPerHour) {
		this.parentsPerHour = parentsPerHour;
	}
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public String getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}
}