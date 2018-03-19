package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewStaffPermissionRequestsMainVO extends SMSBaseVO{
	protected List<ViewStaffPermissionRequestsVO> viewStaffPermissionRequestsVOList;

	public List<ViewStaffPermissionRequestsVO> getViewStaffPermissionRequestsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.viewStaffPermissionRequestsVOList))
		{
			this.viewStaffPermissionRequestsVOList = new ArrayList<ViewStaffPermissionRequestsVO>(); 
		}
		return viewStaffPermissionRequestsVOList;
	}

	public void setViewStaffPermissionRequestsVOList(List<ViewStaffPermissionRequestsVO> viewStaffPermissionRequestsVOList) {
		this.viewStaffPermissionRequestsVOList = viewStaffPermissionRequestsVOList;
	}

}
