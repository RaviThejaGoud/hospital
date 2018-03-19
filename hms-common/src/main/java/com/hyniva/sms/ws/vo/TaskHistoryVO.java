package com.hyniva.sms.ws.vo;

import java.util.Date;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;

public class TaskHistoryVO {
	
	
	public Long id;
	public String comments;
	public String status;
	public Date taskDate;
	public UserVO accountVO;
	public Date taskHistoryDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public UserVO getAccountVO() {
		if(ObjectFunctions.isNullOrEmpty(this.accountVO))
			this.accountVO= new UserVO();
		return accountVO;
	}
	public void setAccountVO(UserVO accountVO) {
		this.accountVO = accountVO;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	
	public Date getTaskHistoryDate() {
		return taskHistoryDate;
	}
	public void setTaskHistoryDate(Date taskHistoryDate) {
		this.taskHistoryDate = taskHistoryDate;
	}

	public String getStatusStr(){
    	if("O".equalsIgnoreCase(getStatus())){
    		return "Open";
    	}else if ("I".equalsIgnoreCase(getStatus())){
    		return "In Progress";
    	}else if ("H".equalsIgnoreCase(getStatus())){
    		return "Hold";
    	}else if("C".equalsIgnoreCase(getStatus())){
    		return "Completed";
    	}else{
    		return "Open";
    	}
    }
	public String getTaskDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.taskDate);
	}
	public String getTaskHistoryDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getTaskHistoryDate());
	}
}
