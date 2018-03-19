package com.hyniva.sms.ws.vo;

public class RouteNotifyVO {

	public long routeId;
	public long routeBoardingPointId;
	public long driverId;
	public double routeLatitude;
	public double routeLongitude;
	public String routeAddress;
	public double currentLatitude;
	public double currentLongitude;
	public String currentAddress;
	public double currentSpeed;
	
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public long getRouteBoardingPointId() {
		return routeBoardingPointId;
	}
	public void setRouteBoardingPointId(long routeBoardingPointId) {
		this.routeBoardingPointId = routeBoardingPointId;
	}
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	public double getRouteLatitude() {
		return routeLatitude;
	}
	public void setRouteLatitude(double routeLatitude) {
		this.routeLatitude = routeLatitude;
	}
	public double getRouteLongitude() {
		return routeLongitude;
	}
	public void setRouteLongitude(double routeLongitude) {
		this.routeLongitude = routeLongitude;
	}
	public String getRouteAddress() {
		return routeAddress;
	}
	public void setRouteAddress(String routeAddress) {
		this.routeAddress = routeAddress;
	}
	public double getCurrentLatitude() {
		return currentLatitude;
	}
	public void setCurrentLatitude(double currentLatitude) {
		this.currentLatitude = currentLatitude;
	}
	public double getCurrentLongitude() {
		return currentLongitude;
	}
	public void setCurrentLongitude(double currentLongitude) {
		this.currentLongitude = currentLongitude;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public double getCurrentSpeed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

}
