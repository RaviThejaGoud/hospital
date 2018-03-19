package com.hyniva.sms.ws.vo;

public class SchoolTermsVO {

	protected long id;
    protected String status="S";
    protected String description;
    protected String termName;
    protected String fromDate;
    protected String toDate;
    protected String dueDate;
    protected String fromMonthName;
    protected String toMonthName;
    protected String noOfDays;
    /*protected String mailContentDesc;
    protected String mobileContentDesc;*/
    protected long feeSettingId;
    protected boolean applToNewStuds;
    protected String dueDate1;
    protected String dueDate2;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getFromMonthName() {
		return fromMonthName;
	}
	public void setFromMonthName(String fromMonthName) {
		this.fromMonthName = fromMonthName;
	}
	public String getToMonthName() {
		return toMonthName;
	}
	public void setToMonthName(String toMonthName) {
		this.toMonthName = toMonthName;
	}
	public String getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}
	/*public String getMailContentDesc() {
		return mailContentDesc;
	}
	public void setMailContentDesc(String mailContentDesc) {
		this.mailContentDesc = mailContentDesc;
	}*/
/*	public String getMobileContentDesc() {
		return mobileContentDesc;
	}
	public void setMobileContentDesc(String mobileContentDesc) {
		this.mobileContentDesc = mobileContentDesc;
	}
*/	public long getFeeSettingId() {
		return feeSettingId;
	}
	public void setFeeSettingId(long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}
	public boolean isApplToNewStuds() {
		return applToNewStuds;
	}
	public void setApplToNewStuds(boolean applToNewStuds) {
		this.applToNewStuds = applToNewStuds;
	}
	public String getDueDate1() {
		return dueDate1;
	}
	public void setDueDate1(String dueDate1) {
		this.dueDate1 = dueDate1;
	}
	public String getDueDate2() {
		return dueDate2;
	}
	public void setDueDate2(String dueDate2) {
		this.dueDate2 = dueDate2;
	}
	
    
    
}
