package com.hyniva.sms.ws.vo;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class RouteMainVO extends SMSBaseVO {
	protected List<RouteVo> routeVO;

	public List<RouteVo> getRouteVo() {
		return routeVO;
	}

	public void setRouteVo(List<RouteVo> routeVo) {
		this.routeVO = routeVo;
	}
}
