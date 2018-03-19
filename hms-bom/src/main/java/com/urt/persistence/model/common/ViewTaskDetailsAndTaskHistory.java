package com.urt.persistence.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;

@Entity
@Table(name="vw_taskDetailsAndTaskHistory")
public class ViewTaskDetailsAndTaskHistory implements Serializable,
Cloneable,Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4093718521696979005L;
	private long taskDetailsId;
	private long taskHistoryId;
	private String taskName;
	private String description;
	private String comments;
	private String staffName;
	private Date taskDate;
	private long accountId;
	private long custId;
    private long academicYearId;
    private String status;
    private Date taskHistoryDate;
    private long taskCreator;
    protected String reminderOption;
	protected Date specificDate;
	protected boolean checkMobileService;
	protected boolean checkEmailService;
	private Date taskCreatedDate;
    
    @Id
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
	@Transient
	public String getTaskDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getTaskDate());
		
	}
	@Transient
    public String getStatusDesc(){
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
	
	@Override
	public int compareTo(Object object) {
		ViewTaskDetailsAndTaskHistory viewTaskDetailsAndTaskHistory = (ViewTaskDetailsAndTaskHistory) object;
        if(ObjectFunctions.isNullOrEmpty(viewTaskDetailsAndTaskHistory)){
        	return 0;
        }else
        	return this.getTaskDate().compareTo(viewTaskDetailsAndTaskHistory.getTaskDate());
	}
	public Date getTaskCreatedDate() {
		return taskCreatedDate;
	}
	public void setTaskCreatedDate(Date taskCreatedDate) {
		this.taskCreatedDate = taskCreatedDate;
	}
}
