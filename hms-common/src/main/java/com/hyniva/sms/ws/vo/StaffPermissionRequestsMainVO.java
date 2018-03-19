package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StaffPermissionRequestsMainVO extends SMSBaseVO{
	protected List<StaffPermissionRequestsVO> staffPermissionRequestsVOlList;

	public List<StaffPermissionRequestsVO> getStaffPermissionRequestsVOlList() {
		if(ObjectFunctions.isNullOrEmpty(this.staffPermissionRequestsVOlList))
		{
			this.staffPermissionRequestsVOlList = new ArrayList<StaffPermissionRequestsVO>(); 
		}
		return staffPermissionRequestsVOlList;
	}

	public void setStaffPermissionRequestsVOlList( List<StaffPermissionRequestsVO> staffPermissionRequestsVOlList) {
		this.staffPermissionRequestsVOlList = staffPermissionRequestsVOlList;
	}

}
