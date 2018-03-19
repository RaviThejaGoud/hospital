package com.hyniva.sms.ws.vo;

import java.util.Date;

public class StaffStatutoryVO {

	public long id;
	public String esiNo;
	public Date esiDateofJoin;
	public double esiPercentage;
	public String pfNo;
	public Date pfDateofJoin;
	public double pfPercentage;
    public long custId;    
    public double esiPercentageValue;
    public double pfPercentageValue;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEsiNo() {
		return esiNo;
	}
	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}
	public Date getEsiDateofJoin() {
		return esiDateofJoin;
	}
	public void setEsiDateofJoin(Date esiDateofJoin) {
		this.esiDateofJoin = esiDateofJoin;
	}
	public double getEsiPercentage() {
		return esiPercentage;
	}
	public void setEsiPercentage(double esiPercentage) {
		this.esiPercentage = esiPercentage;
	}
	public String getPfNo() {
		return pfNo;
	}
	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}
	public Date getPfDateofJoin() {
		return pfDateofJoin;
	}
	public void setPfDateofJoin(Date pfDateofJoin) {
		this.pfDateofJoin = pfDateofJoin;
	}
	public double getPfPercentage() {
		return pfPercentage;
	}
	public void setPfPercentage(double pfPercentage) {
		this.pfPercentage = pfPercentage;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public double getEsiPercentageValue() {
		return esiPercentageValue;
	}
	public void setEsiPercentageValue(double esiPercentageValue) {
		this.esiPercentageValue = esiPercentageValue;
	}
	public double getPfPercentageValue() {
		return pfPercentageValue;
	}
	public void setPfPercentageValue(double pfPercentageValue) {
		this.pfPercentageValue = pfPercentageValue;
	}
    
    
}
