package com.hyniva.sms.ws.vo;


public class StaffPermissionRequestsVO {
	
	private long staffId;
    private long academicYearId;
	private String status;
    private String permissionDate;
    private String startTime;
    private String endTime;
    protected long supervisorId;
    private String comments;
    private String approvalsComment;
    
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public long getAcademicYearId() {
		return academicYearId;
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
	 
	public String getPermissionDate() {
		return permissionDate;
	}
	public void setPermissionDate(String permissionDate) {
		this.permissionDate = permissionDate;
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
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getApprovalsComment() {
		return approvalsComment;
	}
	public void setApprovalsComment(String approvalsComment) {
		this.approvalsComment = approvalsComment;
	}
    
}
