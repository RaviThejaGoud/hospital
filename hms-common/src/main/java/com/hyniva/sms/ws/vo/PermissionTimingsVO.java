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



public class PermissionTimingsVO{
	
	protected long id;
	private String startTime;
	private String endTime;
	private String days;
	protected long permissionsId;
	
	 
	public long getPermissionsId() {
		return permissionsId;
	}
	public void setPermissionsId(long permissionsId) {
		this.permissionsId = permissionsId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
}