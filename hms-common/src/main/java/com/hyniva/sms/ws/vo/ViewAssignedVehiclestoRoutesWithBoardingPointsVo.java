package com.hyniva.sms.ws.vo;

public class ViewAssignedVehiclestoRoutesWithBoardingPointsVo {

	 public long vehicleAcademicId;
     public long routeId;
     public String name;
     public long academicYearId;
     public long custId;
     public long driverId;
     public long helperId;
     public boolean status;
     public long vehicleId;
     public String routeName;
     public boolean routeStatus;
     public String boardingPointName;
     public long boardingPointId;
     public int noOfSeats;
     
     
	public long getVehicleAcademicId() {
		return vehicleAcademicId;
	}
	public void setVehicleAcademicId(long vehicleAcademicId) {
		this.vehicleAcademicId = vehicleAcademicId;
	}
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	public long getHelperId() {
		return helperId;
	}
	public void setHelperId(long helperId) {
		this.helperId = helperId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public boolean isRouteStatus() {
		return routeStatus;
	}
	public void setRouteStatus(boolean routeStatus) {
		this.routeStatus = routeStatus;
	}
	public String getBoardingPointName() {
		return boardingPointName;
	}
	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}
	public long getBoardingPointId() {
		return boardingPointId;
	}
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
    
}
