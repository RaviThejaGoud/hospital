package com.hyniva.sms.ws.vo;


public class StaffPermissionsDayDetailsVO {
	
	 private long id;
	 private long staffPermissionsSettingsId;
	 private long roleId;
	 private int days;
	 
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStaffPermissionsSettingsId() {
		return staffPermissionsSettingsId;
	}
	public void setStaffPermissionsSettingsId(long staffPermissionsSettingsId) {
		this.staffPermissionsSettingsId = staffPermissionsSettingsId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	 
}
