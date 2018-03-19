package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hyniva.sms.ws.vo.TaskHistoryVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

@Entity
@Table(name="taskHistory")
public class TaskHistory extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3327342381145335870L;
	protected String comments;
	protected String status;
	protected Date taskDate;
	protected User account;
	protected Date taskHistoryDate;
	
	@Column(name="comments",nullable=true ,length = 1024,columnDefinition="varchar(1024) default null")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'O'")
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
	
	@OneToOne
	@JoinColumn(name="staffAccountId", insertable=true, updatable=true) 
	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Transient
	public TaskHistoryVO copyFromEntityToVo()
	{
		TaskHistoryVO taskHistoryVO= new TaskHistoryVO();
		taskHistoryVO.setId(this.getId());
		taskHistoryVO.setComments(this.getComments());
		taskHistoryVO.setStatus(this.getStatus());
		taskHistoryVO.setTaskDate(this.getTaskDate());
		taskHistoryVO.setTaskHistoryDate(new Date());
		return taskHistoryVO;
	}
	
	@Transient
	public TaskHistory copyFromVoToEntity(TaskHistoryVO taskHistoryVO)
	{
		this.setComments(taskHistoryVO.getComments());
		this.setStatus(taskHistoryVO.getStatus());
		this.setTaskDate(taskHistoryVO.getTaskDate());
		this.setTaskHistoryDate(new Date());
		this.setLastAccessDate(new Date());
		this.setLastUpdatedDate(new Date());
		return this;
	}

	public Date getTaskHistoryDate() {
		return taskHistoryDate;
	}

	public void setTaskHistoryDate(Date taskHistoryDate) {
		this.taskHistoryDate = taskHistoryDate;
	}
	/*@Transient
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
    }*/
	
	/*@Transient
	public String getTaskHistoryDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getTaskHistoryDate());
	}*/
}
