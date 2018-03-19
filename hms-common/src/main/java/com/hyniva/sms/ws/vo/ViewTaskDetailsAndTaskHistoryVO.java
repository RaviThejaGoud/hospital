package com.hyniva.sms.ws.vo;

import java.util.Date;

public class ViewTaskDetailsAndTaskHistoryVO {

	
	public long taskDetailsId;
	public long taskHistoryId;
	public String taskName;
	public String description;
	public String comments;
	public String staffName;
	public Date taskDate;
	public long accountId;
	public long custId;
	public long academicYearId;
	public String status;
	public Date taskHistoryDate;
	public long taskCreator;
	protected String reminderOption;
	protected Date specificDate;
	protected boolean checkMobileService;
	protected boolean checkEmailService;
	
	public long getTaskDetailsId() {
		return taskDetailsId;
	}
	public void setTaskDetailsId(long taskDetailsId) {
		this.taskDetailsId = taskDetailsId;
	}
	public long getTaskHistoryId() {
		return taskHistoryId;
	}
	public void setTaskHistoryId(long taskHistoryId) {
		this.taskHistoryId = taskHistoryId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
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
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTaskHistoryDate() {
		return taskHistoryDate;
	}
	public void setTaskHistoryDate(Date taskHistoryDate) {
		this.taskHistoryDate = taskHistoryDate;
	}
	public long getTaskCreator() {
		return taskCreator;
	}
	public void setTaskCreator(long taskCreator) {
		this.taskCreator = taskCreator;
	}
	public String getReminderOption() {
		return reminderOption;
	}
	public void setReminderOption(String reminderOption) {
		this.reminderOption = reminderOption;
	}
	public Date getSpecificDate() {
		return specificDate;
	}
	public void setSpecificDate(Date specificDate) {
		this.specificDate = specificDate;
	}
	public boolean isCheckMobileService() {
		return checkMobileService;
	}
	public void setCheckMobileService(boolean checkMobileService) {
		this.checkMobileService = checkMobileService;
	}
	public boolean isCheckEmailService() {
		return checkEmailService;
	}
	public void setCheckEmailService(boolean checkEmailService) {
		this.checkEmailService = checkEmailService;
	}
	
}
