package com.hyniva.sms.ws.vo;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class ViewRoutesMainVO extends SMSBaseVO{

	protected List<RouteVo> routeVoList;

	public List<RouteVo> getRouteVoList() {
		return routeVoList;
	}

	public void setRouteVoList(List<RouteVo> routeVoList) {
		this.routeVoList = routeVoList;
	}
	

}
