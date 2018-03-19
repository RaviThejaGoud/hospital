package com.hyniva.sms.ws.vo;


import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class LeavesVO extends SMSBaseVO{
	private long id;
	private String leaveStatus;
	private String approvalsComment;
	private double leavesCount;
	private String leaveType;
	private String startDate;
	private String endDate;
	private String description;
	private String supervisor;
	private long accountId; //here multiple accontIds will come in json
	private String appliedBy; //this filed used to who apply the leave
	private long  supervisorId;
	private Boolean halfDayLeave;
	private String leaveSessionType;
	
	public String getAppliedBy() {
		return appliedBy;
	}
	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLeaveStatus() {
		return leaveStatus;
	}
	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	public String getApprovalsComment() {
		return approvalsComment;
	}
	public void setApprovalsComment(String approvalsComment) {
		this.approvalsComment = approvalsComment;
	}
	  
	public double getLeavesCount() {
		return leavesCount;
	}
	public void setLeavesCount(double leavesCount) {
		this.leavesCount = leavesCount;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	/**
	 * @return the halfDayLeave
	 */
	public Boolean getHalfDayLeave() {
		return halfDayLeave;
	}
	/**
	 * @param halfDayLeave the halfDayLeave to set
	 */
	public void setHalfDayLeave(Boolean halfDayLeave) {
		this.halfDayLeave = halfDayLeave;
	}
	/**
	 * @return the leaveSessionType
	 */
	public String getLeaveSessionType() {
		return leaveSessionType;
	}
	/**
	 * @param leaveSessionType the leaveSessionType to set
	 */
	public void setLeaveSessionType(String leaveSessionType) {
		this.leaveSessionType = leaveSessionType;
	}
	
}
