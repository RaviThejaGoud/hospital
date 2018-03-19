package com.hyniva.sms.ws.vo;


public class StudentFeePaidDetailsVO {

	protected long  id;
	protected String lastAccessDate;
	protected String lastUpdatedDate;
    protected String createdDate;
    protected long createdById;
    protected long lastUpdatedById;
    protected Long studentPaymentId;
    protected double paymentAmount;
    protected double discountAmount;
    protected long feeId;
    protected String paymentStatus;
    protected String futureFeeStatus;
    protected String deleteStatus;
    protected long studentId;
    protected long feePaidDetailsId;
    protected String committedFeeStatus;
    protected long studTransportDetailsId;
    
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
	public Long getStudentPaymentId() {
		return studentPaymentId;
	}
	public void setStudentPaymentId(Long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public long getFeeId() {
		return feeId;
	}
	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getFutureFeeStatus() {
		return futureFeeStatus;
	}
	public void setFutureFeeStatus(String futureFeeStatus) {
		this.futureFeeStatus = futureFeeStatus;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getFeePaidDetailsId() {
		return feePaidDetailsId;
	}
	public void setFeePaidDetailsId(long feePaidDetailsId) {
		this.feePaidDetailsId = feePaidDetailsId;
	}
	public String getCommittedFeeStatus() {
		return committedFeeStatus;
	}
	public void setCommittedFeeStatus(String committedFeeStatus) {
		this.committedFeeStatus = committedFeeStatus;
	}
	public long getStudTransportDetailsId() {
		return studTransportDetailsId;
	}
	public void setStudTransportDetailsId(long studTransportDetailsId) {
		this.studTransportDetailsId = studTransportDetailsId;
	}
    
    
}
