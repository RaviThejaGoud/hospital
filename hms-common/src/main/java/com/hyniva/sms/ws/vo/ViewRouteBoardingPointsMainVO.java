package com.hyniva.sms.ws.vo;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewRouteBoardingPointsMainVO extends SMSBaseVO{

	protected List<RouteBoardingPointsVO> routeBoardingPointsVoList;

	public List<RouteBoardingPointsVO> getRouteBoardingPointsVoList() {
		return routeBoardingPointsVoList;
	}

	public void setRouteBoardingPointsVoList(
			List<RouteBoardingPointsVO> routeBoardingPointsVoList) {
		this.routeBoardingPointsVoList = routeBoardingPointsVoList;
	}

	
	

}
