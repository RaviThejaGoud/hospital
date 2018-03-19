package com.hyniva.sms.ws.vo;

import java.util.List;

public class RouteVo {
	
	public long id;
	public boolean status;
    public String routePointName;
    public String routeEndName;
    public String routeName;   
    public String routePointStartTime;  
    public String routePointEndTime;  
    public String routePointReturnStartTime;  
    public String routePointReturnEndTime;  
    public double routePointFeeAmount;
    public String routePoints;
    public long driverId;
	public long custId;
    public long academicYearId;
    public long routeStartTimeinMns;
    public long routeEndTimeinMns;
    protected List<RouteBoardingPointsVO> routeBoardingPointsVoList;

	public List<RouteBoardingPointsVO> getRouteBoardingPointsVoList() {
		return routeBoardingPointsVoList;
	}

	public void setRouteBoardingPointsVoList(
			List<RouteBoardingPointsVO> routeBoardingPointsVoList) {
		this.routeBoardingPointsVoList = routeBoardingPointsVoList;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
    public String getRoutePoints() {
		return routePoints;
	}
	public void setRoutePoints(String routePoints) {
		this.routePoints = routePoints;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getRoutePointName() {
		return routePointName;
	}
	public void setRoutePointName(String routePointName) {
		this.routePointName = routePointName;
	}
	public String getRouteEndName() {
		return routeEndName;
	}
	public void setRouteEndName(String routeEndName) {
		this.routeEndName = routeEndName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getRoutePointStartTime() {
		return routePointStartTime;
	}
	public void setRoutePointStartTime(String routePointStartTime) {
		this.routePointStartTime = routePointStartTime;
	}
	public String getRoutePointEndTime() {
		return routePointEndTime;
	}
	public void setRoutePointEndTime(String routePointEndTime) {
		this.routePointEndTime = routePointEndTime;
	}
	public String getRoutePointReturnStartTime() {
		return routePointReturnStartTime;
	}
	public void setRoutePointReturnStartTime(String routePointReturnStartTime) {
		this.routePointReturnStartTime = routePointReturnStartTime;
	}
	public String getRoutePointReturnEndTime() {
		return routePointReturnEndTime;
	}
	public void setRoutePointReturnEndTime(String routePointReturnEndTime) {
		this.routePointReturnEndTime = routePointReturnEndTime;
	}
	public double getRoutePointFeeAmount() {
		return routePointFeeAmount;
	}
	public void setRoutePointFeeAmount(double routePointFeeAmount) {
		this.routePointFeeAmount = routePointFeeAmount;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public long getRouteStartTimeinMns() {
		return routeStartTimeinMns;
	}
	public void setRouteStartTimeinMns(long routeStartTimeinMns) {
		this.routeStartTimeinMns = routeStartTimeinMns;
	}
	public long getRouteEndTimeinMns() {
		return routeEndTimeinMns;
	}
	public void setRouteEndTimeinMns(long routeEndTimeinMns) {
		this.routeEndTimeinMns = routeEndTimeinMns;
	}
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
}
