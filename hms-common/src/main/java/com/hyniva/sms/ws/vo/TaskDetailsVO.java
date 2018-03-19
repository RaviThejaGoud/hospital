package com.hyniva.sms.ws.vo;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;

public class TaskDetailsVO {
	
	public Long id;
	public String taskName;
	public String description;
	public Long custId;
	public Long academicYearId;
	public String reminderOption;
	public Date specificDate;
	public boolean checkMobileService;
	public boolean checkEmailService;
	protected List<TaskHistoryVO> taskHistoriesVO;
	protected TaskHistoryVO latestTaskHistoryVO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<TaskHistoryVO> getTaskHistoriesVO() {
		if(ObjectFunctions.isNullOrEmpty(this.taskHistoriesVO)){
			taskHistoriesVO =  new ArrayList<TaskHistoryVO>();
		}
		return taskHistoriesVO;
	}
	public void setTaskHistoriesVO(List<TaskHistoryVO> taskHistoriesVO) {
		this.taskHistoriesVO = taskHistoriesVO;
	}
	public TaskHistoryVO getLatestTaskHistoryVO() {
		if(ObjectFunctions.isNullOrEmpty(this.latestTaskHistoryVO))
			latestTaskHistoryVO = new TaskHistoryVO();
		return latestTaskHistoryVO;
	}
	public void setLatestTaskHistoryVO(TaskHistoryVO latestTaskHistoryVO) {
		this.latestTaskHistoryVO = latestTaskHistoryVO;
	}

	@Transient
	public String getSpecificDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getSpecificDate());
	}
	
}
