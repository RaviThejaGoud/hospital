package com.hyniva.sms.ws.vo.fee;

public class DeleteStudentPaymentVo {
	
	private long id;
	private long custId;
	private long studentPaymentId;
	private String deleteRemarks;
	private String reportedPerson;
	private String supportPersonName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getStudentPaymentId() {
		return studentPaymentId;
	}
	public void setStudentPaymentId(long studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}
	public String getDeleteRemarks() {
		return deleteRemarks;
	}
	public void setDeleteRemarks(String deleteRemarks) {
		this.deleteRemarks = deleteRemarks;
	}
	public String getReportedPerson() {
		return reportedPerson;
	}
	public void setReportedPerson(String reportedPerson) {
		this.reportedPerson = reportedPerson;
	}
	public String getSupportPersonName() {
		return supportPersonName;
	}
	public void setSupportPersonName(String supportPersonName) {
		this.supportPersonName = supportPersonName;
	}

	
}
