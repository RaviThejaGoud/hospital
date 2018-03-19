package com.hyniva.sms.ws.vo;

import java.util.List;

public class VehicleRouteVo {
	
	public long id;
	public List<RouteVo> routeVos;
	public String name;
	public VehicleVO vehicleVo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public VehicleVO getVehicleVo() {
		return vehicleVo;
	}
	public void setVehicleVo(VehicleVO vehicleVo) {
		this.vehicleVo = vehicleVo;
	}
	public List<RouteVo> getRouteVos() {
		return routeVos;
	}
	public void setRouteVos(List<RouteVo> routeVos) {
		this.routeVos = routeVos;
	}
	
}
