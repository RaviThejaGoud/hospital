package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewStaffPersonAccountDetailsMainVO extends SMSBaseVO{
	protected List<ViewStaffPersonAccountDetailsVO> staffPersonAccountDetailsVOS;

	public List<ViewStaffPersonAccountDetailsVO> getStaffPersonAccountDetailsVOS() {
		if(ObjectFunctions.isNullOrEmpty(this.staffPersonAccountDetailsVOS))
		{
			this.staffPersonAccountDetailsVOS = new ArrayList<ViewStaffPersonAccountDetailsVO>(); 
		}
		return staffPersonAccountDetailsVOS;
	}

	public void setStaffPersonAccountDetailsVOS(List<ViewStaffPersonAccountDetailsVO> staffPersonAccountDetailsVOS) {
		this.staffPersonAccountDetailsVOS = staffPersonAccountDetailsVOS;
	}
	
	
	
}
