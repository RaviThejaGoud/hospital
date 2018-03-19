package com.hyniva.sms.ws.vo;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class VehicleRouteMainVO extends SMSBaseVO {
	protected List<VehicleRouteVo> vehicleRouteVO;

	public List<VehicleRouteVo> getVehicleRouteVo() {
		return vehicleRouteVO;
	}

	public void setVehicleRouteVo(List<VehicleRouteVo> vehicleRouteVO) {
		this.vehicleRouteVO = vehicleRouteVO;
	}
}
