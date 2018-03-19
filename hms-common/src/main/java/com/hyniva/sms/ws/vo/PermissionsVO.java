/*************************************************************************
* Copyright (C) 2015
* ACG
* All Rights Reserved 
*
* File: UserVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   June 18, 2015    Sreeram		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;


public class PermissionsVO{
	
	protected long id;
	private long studentId;
    private String startDate;
    private String endDate;
    private String status;
    private String comments;
    private long createdById;    
    private long studyClassId;   
    protected List<PermissionTimingsVO> permissionTimingsList;
    private long supervisorId; 
    private String approvalsComment;
	   
	 
	public String getApprovalsComment() {
		return approvalsComment;
	}
	public void setApprovalsComment(String approvalsComment) {
		this.approvalsComment = approvalsComment;
	}
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	public List<PermissionTimingsVO> getPermissionTimingsList() {
    	if(ObjectFunctions.isNullOrEmpty(this.permissionTimingsList))
		{
			this.permissionTimingsList = new ArrayList<PermissionTimingsVO>(); 
		}
		return permissionTimingsList;
    }
	public void setPermissionTimingsList( List<PermissionTimingsVO> permissionTimingsList) {
		this.permissionTimingsList = permissionTimingsList;
	}
	
	public long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(long createdById) {
		this.createdById = createdById;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}