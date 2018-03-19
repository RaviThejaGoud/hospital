package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewPermissionSettingsMainVO extends SMSBaseVO{
 
	protected List<PermissionsVO> permissionsVOList;

	public List<PermissionsVO> getPermissionsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.permissionsVOList))
		{
			this.permissionsVOList = new ArrayList<PermissionsVO>(); 
		}
		return permissionsVOList;
	}

	public void setPermissionsVOList(List<PermissionsVO> permissionsVOList) {
		this.permissionsVOList = permissionsVOList;
	}
	
	
}
