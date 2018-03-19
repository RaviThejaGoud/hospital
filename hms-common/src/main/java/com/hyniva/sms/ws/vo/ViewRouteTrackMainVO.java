package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewRouteTrackMainVO extends SMSBaseVO{

	protected List<ViewRouteTrackDetailsVO> viewRouteTrackDetailsVO;

	public List<ViewRouteTrackDetailsVO> getViewRouteTrackDetailsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.viewRouteTrackDetailsVO))
			this.viewRouteTrackDetailsVO = new ArrayList<ViewRouteTrackDetailsVO>(); 
		
		return viewRouteTrackDetailsVO;
	}

	public void setViewRouteTrackDetailsVOs(List<ViewRouteTrackDetailsVO> viewRouteTrackDetailsVO) {
		this.viewRouteTrackDetailsVO = viewRouteTrackDetailsVO;
	}

	
}
