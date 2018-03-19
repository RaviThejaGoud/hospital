package com.hyniva.sms.ws.vo;

public class MeetingScheduleSlotsVO {

	protected long id;
	protected String slotType;
	protected String slotTime;
	protected long parentAccountId;
	protected String parentName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSlotType() {
		return slotType;
	}
	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}
	public String getSlotTime() {
		return slotTime;
	}
	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}
	public long getParentAccountId() {
		return parentAccountId;
	}
	public void setParentAccountId(long parentAccountId) {
		this.parentAccountId = parentAccountId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}