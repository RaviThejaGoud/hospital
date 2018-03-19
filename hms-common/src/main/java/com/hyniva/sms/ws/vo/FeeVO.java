package com.hyniva.sms.ws.vo;


public class FeeVO {

	protected long id;
	protected long classId;
    protected String status;
    protected double feeAmount;
    protected long schoolTermId;
    protected long feeTypeId;
    protected long  categoryId;
    protected long routeBoardingPointId;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public long getSchoolTermId() {
		return schoolTermId;
	}
	public void setSchoolTermId(long schoolTermId) {
		this.schoolTermId = schoolTermId;
	}
	public long getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getRouteBoardingPointId() {
		return routeBoardingPointId;
	}
	public void setRouteBoardingPointId(long routeBoardingPointId) {
		this.routeBoardingPointId = routeBoardingPointId;
	}
    
    
}
