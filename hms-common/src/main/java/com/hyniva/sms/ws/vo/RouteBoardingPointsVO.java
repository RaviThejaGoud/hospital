package com.hyniva.sms.ws.vo;



public class RouteBoardingPointsVO {
	
	public long id;
	public boolean status;
    public String boardingPointName;
    public String boardingPointStatTime;
    public String boardingPointEndTime;
    public String boardingPointReturnStatTime;
    public String boardingPointReturnEndTime;
    public long custId;
    //public TransportFee transportFee;
    public long academicYearId;
    public double boardingPointFeeAmount;
    public long boardingPointOrder;
    public RouteVo routeVo;
    
    
	public boolean isStatus() {
		return status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getBoardingPointName() {
		return boardingPointName;
	}
	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}
	public String getBoardingPointStatTime() {
		return boardingPointStatTime;
	}
	public void setBoardingPointStatTime(String boardingPointStatTime) {
		this.boardingPointStatTime = boardingPointStatTime;
	}
	public String getBoardingPointEndTime() {
		return boardingPointEndTime;
	}
	public void setBoardingPointEndTime(String boardingPointEndTime) {
		this.boardingPointEndTime = boardingPointEndTime;
	}
	public String getBoardingPointReturnStatTime() {
		return boardingPointReturnStatTime;
	}
	public void setBoardingPointReturnStatTime(String boardingPointReturnStatTime) {
		this.boardingPointReturnStatTime = boardingPointReturnStatTime;
	}
	public String getBoardingPointReturnEndTime() {
		return boardingPointReturnEndTime;
	}
	public void setBoardingPointReturnEndTime(String boardingPointReturnEndTime) {
		this.boardingPointReturnEndTime = boardingPointReturnEndTime;
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
	public double getBoardingPointFeeAmount() {
		return boardingPointFeeAmount;
	}
	public void setBoardingPointFeeAmount(double boardingPointFeeAmount) {
		this.boardingPointFeeAmount = boardingPointFeeAmount;
	}
	public long getBoardingPointOrder() {
		return boardingPointOrder;
	}
	public void setBoardingPointOrder(long boardingPointOrder) {
		this.boardingPointOrder = boardingPointOrder;
	}
	public RouteVo getRouteVo() {
		return routeVo;
	}
	public void setRouteVo(RouteVo routeVo) {
		this.routeVo = routeVo;
	}
    

    
}
