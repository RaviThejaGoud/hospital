package com.hyniva.sms.ws.vo;

public class ExcessPaymentVO {

	protected long  id;
	protected String lastAccessDate;
	protected String lastUpdatedDate;
    protected String createdDate;
    protected long createdById;
    protected long lastUpdatedById;
	protected long accountId;
	protected long paymentId;
	protected long usedPaymentId;
	protected double excessAmount;
	protected boolean status;
	protected long studentId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(String lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}
	public long getLastUpdatedById() {
		return lastUpdatedById;
	}
	public void setLastUpdatedById(long lastUpdatedById) {
		this.lastUpdatedById = lastUpdatedById;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public long getUsedPaymentId() {
		return usedPaymentId;
	}
	public void setUsedPaymentId(long usedPaymentId) {
		this.usedPaymentId = usedPaymentId;
	}
	public double getExcessAmount() {
		return excessAmount;
	}
	public void setExcessAmount(double excessAmount) {
		this.excessAmount = excessAmount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	
}
