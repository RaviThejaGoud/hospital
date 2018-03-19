package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;

public class StaffPermissionsSettingsVO {
	
	private long id;
	private String status;
    private String monthOrYear;
    private String isRolebased;
    private long createdById;
    protected List<StaffPermissionsDayDetailsVO> staffPermissionsDayDetailsVOList;
    
    
	public List<StaffPermissionsDayDetailsVO> getStaffPermissionsDayDetailsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.staffPermissionsDayDetailsVOList))
		{
			this.staffPermissionsDayDetailsVOList = new ArrayList<StaffPermissionsDayDetailsVO>(); 
		}
		return staffPermissionsDayDetailsVOList;
	}
	public void setStaffPermissionsDayDetailsVOList( List<StaffPermissionsDayDetailsVO> staffPermissionsDayDetailsVOList) {
		this.staffPermissionsDayDetailsVOList = staffPermissionsDayDetailsVOList;
	}
	public long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}
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
	public String getMonthOrYear() {
		return monthOrYear;
	}
	public void setMonthOrYear(String monthOrYear) {
		this.monthOrYear = monthOrYear;
	}
	public String getIsRolebased() {
		return isRolebased;
	}
	public void setIsRolebased(String isRolebased) {
		this.isRolebased = isRolebased;
	}

}
