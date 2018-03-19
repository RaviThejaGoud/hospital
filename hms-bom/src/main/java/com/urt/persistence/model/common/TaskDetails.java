package com.urt.persistence.model.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.TaskDetailsVO;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name="taskDetails")
public class TaskDetails extends PersistentObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8187552719914022819L;
	protected String taskName;
	protected String description;
	protected Long custId;
	protected Long academicYearId;
	protected String reminderOption;
	protected Date specificDate;
	protected boolean checkMobileService;
	protected boolean checkEmailService;
	protected List<TaskHistory> taskHistories;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="taskDetailsId")
	public List<TaskHistory> getTaskHistories() {
		if(ObjectFunctions.isNullOrEmpty(this.taskHistories)){
			this.taskHistories = new ArrayList<TaskHistory>();
		}
		return this.taskHistories;
	}

	public void setTaskHistories(List<TaskHistory> taskHistories) {
		this.taskHistories = taskHistories;
	}
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@Column(name="description",nullable=true ,length = 1024,columnDefinition="varchar(1024) default null")
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
	@Column(name = "reminderOption", nullable = true, length = 1,columnDefinition="char(1) default 'E'")
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
	@Column(name = "checkMobileService", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	public boolean isCheckMobileService() {
		return checkMobileService;
	}

	public void setCheckMobileService(boolean checkMobileService) {
		this.checkMobileService = checkMobileService;
	}
	@Column(name = "checkEmailService", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	public boolean isCheckEmailService() {
		return checkEmailService;
	}

	public void setCheckEmailService(boolean checkEmailService) {
		this.checkEmailService = checkEmailService;
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
	public void addTaskHistory(TaskHistory taskHistory) {
		if(ObjectFunctions.isNullOrEmpty(taskHistory)){
			taskHistory=new TaskHistory();
		}
		this.getTaskHistories().add(taskHistory);
	}
	
	@Transient
	public TaskDetailsVO copyFromEntityToVo()
	{
		
		TaskDetailsVO taskDetailsVO= new TaskDetailsVO();
		taskDetailsVO.setId(this.getId());
		taskDetailsVO.setAcademicYearId(this.getAcademicYearId());
		taskDetailsVO.setCustId(this.getCustId());
		taskDetailsVO.setTaskName(this.getTaskName());
		taskDetailsVO.setDescription(this.getDescription());
		taskDetailsVO.setReminderOption(this.getReminderOption());
		taskDetailsVO.setSpecificDate(this.getSpecificDate());
		taskDetailsVO.setCheckMobileService(this.isCheckMobileService());
		taskDetailsVO.setCheckEmailService(this.isCheckEmailService());
		
		return taskDetailsVO;
	}
	@Transient
	public TaskDetails copyFromVoToEntity(TaskDetailsVO taskDetailsVO)
	{
		this.setCustId(taskDetailsVO.getCustId());
		this.setTaskName(taskDetailsVO.getTaskName());
		this.setAcademicYearId(taskDetailsVO.getAcademicYearId());
		this.setDescription(taskDetailsVO.getDescription());
		this.setLastAccessDate(new Date());
		this.setLastUpdatedDate(new Date());
		this.setReminderOption(taskDetailsVO.getReminderOption());
		this.setCheckMobileService(taskDetailsVO.isCheckMobileService());
		this.setCheckEmailService(taskDetailsVO.isCheckEmailService());
		if("E".equalsIgnoreCase(taskDetailsVO.getReminderOption())){
			this.setSpecificDate(null);
		}else{
			this.setSpecificDate(taskDetailsVO.getSpecificDate());
		}
		return this;
	}
	@Transient
	public String getSpecificDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getSpecificDate());
	}
}
