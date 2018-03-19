package com.hyniva.sms.ws.vo;


public class StaffTimetablesVO{
	
	private String filePath;
	private  long accountId;
	private String staffName;
	private String staffMobileNumber;
	private String roleName;
	private String qualification;
	
	
	
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffMobileNumber() {
		return staffMobileNumber;
	}
	public void setStaffMobileNumber(String staffMobileNumber) {
		this.staffMobileNumber = staffMobileNumber;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
}