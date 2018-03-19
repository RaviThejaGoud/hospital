package com.hyniva.sms.ws.vo;

import java.beans.Transient;
import java.util.Date;

import com.churchgroup.util.date.DateFormatter;

public class ReminderVO {

	public Long id;
	public String name;
	public String description;
	public Date expirationDate;
	public String reminderOption;
	public Date specificDate;
	public boolean checkMobileService;
	public boolean checkEmailService;
	public Long custId;
	public Long academicYearId;
	public Long accountId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	@Transient
	public String getExpirationDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getExpirationDate());
	}
	@Transient
	public String getSpecificDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getSpecificDate());
	}
}
