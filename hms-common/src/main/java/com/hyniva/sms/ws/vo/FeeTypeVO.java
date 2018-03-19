package com.hyniva.sms.ws.vo;

public class FeeTypeVO {
	
	protected long id;
	protected String status;
    protected String feeType;
    protected long feeSettingId;
    protected long priorityPosition;
    protected String committedFeeStatus;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public long getFeeSettingId() {
		return feeSettingId;
	}
	public void setFeeSettingId(long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}
	public long getPriorityPosition() {
		return priorityPosition;
	}
	public void setPriorityPosition(long priorityPosition) {
		this.priorityPosition = priorityPosition;
	}
	public String getCommittedFeeStatus() {
		return committedFeeStatus;
	}
	public void setCommittedFeeStatus(String committedFeeStatus) {
		this.committedFeeStatus = committedFeeStatus;
	}
    
    
}
