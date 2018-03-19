package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewStaffPermissionsSettingsMainVO extends SMSBaseVO{

	protected List<ViewStaffPermissionsSettingsVO> viewStaffPermissionsSettingsVOList;

	public List<ViewStaffPermissionsSettingsVO> getViewStaffPermissionsSettingsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.viewStaffPermissionsSettingsVOList))
		{
			this.viewStaffPermissionsSettingsVOList = new ArrayList<ViewStaffPermissionsSettingsVO>(); 
		}
		return viewStaffPermissionsSettingsVOList;
	}

	public void setViewStaffPermissionsSettingsVOList( List<ViewStaffPermissionsSettingsVO> viewStaffPermissionsSettingsVOList) {
		this.viewStaffPermissionsSettingsVOList = viewStaffPermissionsSettingsVOList;
	} 
	
}
