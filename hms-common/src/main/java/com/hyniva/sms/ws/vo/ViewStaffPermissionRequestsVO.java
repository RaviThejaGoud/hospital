package com.hyniva.sms.ws.vo;

public class ViewStaffPermissionRequestsVO {
	
	private String staffName;
	private long PermissionRequestId;
	
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public long getPermissionRequestId() {
		return PermissionRequestId;
	}

	public void setPermissionRequestId(long permissionRequestId) {
		PermissionRequestId = permissionRequestId;
	}

	protected  StaffPermissionRequestsVO staffPermissionRequestsVO;

	public StaffPermissionRequestsVO getStaffPermissionRequestsVO() {
		return staffPermissionRequestsVO;
	}

	public void setStaffPermissionRequestsVO( StaffPermissionRequestsVO staffPermissionRequestsVO) {
		this.staffPermissionRequestsVO = staffPermissionRequestsVO;
	}
	

}
