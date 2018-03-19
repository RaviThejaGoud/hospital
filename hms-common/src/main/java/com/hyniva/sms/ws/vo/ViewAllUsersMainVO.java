package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewAllUsersMainVO extends SMSBaseVO{
	
	protected List<ViewAllUsersVO> viewAllUsersVOs;

	public List<ViewAllUsersVO> getViewAllUsersVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.viewAllUsersVOs))
		{
			this.viewAllUsersVOs = new ArrayList<ViewAllUsersVO>(); 
		}
		return viewAllUsersVOs;
	}

	public void setViewAllUsersVOs(List<ViewAllUsersVO> viewAllUsersVOs) {
		this.viewAllUsersVOs = viewAllUsersVOs;
	}
	
	
}
