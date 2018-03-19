package com.hyniva.sms.ws.vo;


public class BedVo {

	public long id;
	public long accountId;
	public long custId;
	public long roomId;
	public AcademicYearVo academicYearVo;
	public long bedLevel;
   // protected String roomName;
	public String bedName;
	public double bedCost;
   // protected String floorName;
	public boolean status;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}
	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}
	public long getBedLevel() {
		return bedLevel;
	}
	public void setBedLevel(long bedLevel) {
		this.bedLevel = bedLevel;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public double getBedCost() {
		return bedCost;
	}
	public void setBedCost(double bedCost) {
		this.bedCost = bedCost;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
