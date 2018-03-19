package com.hyniva.sms.ws.vo;

public class StudentFeeConcessionVO {

	protected long id;
	protected long studentId;
	protected long feeId;
	protected double concessionAmount;
	private long studTransportDetailsId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getFeeId() {
		return feeId;
	}
	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}
	public double getConcessionAmount() {
		return concessionAmount;
	}
	public void setConcessionAmount(double concessionAmount) {
		this.concessionAmount = concessionAmount;
	}
	public long getStudTransportDetailsId() {
		return studTransportDetailsId;
	}
	public void setStudTransportDetailsId(long studTransportDetailsId) {
		this.studTransportDetailsId = studTransportDetailsId;
	}

	
}
