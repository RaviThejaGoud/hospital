package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.ReminderVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Student;
@Entity
@Table(name="reminder")
public class Reminder extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2169126514327396525L;
	
	private String name;
	private String description;
	private Date expirationDate;
	private String reminderOption;
	private Date specificDate;
	private boolean checkMobileService;
	private boolean checkEmailService;
	private Long custId;
	private Long academicYearId;
	private Long accountId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description",nullable=true ,length = 1024,columnDefinition="varchar(1024) default null")
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
        Reminder reminder = (Reminder) object;
        if(ObjectFunctions.isNullOrEmpty(reminder)){
        	return 0;
        }else
        	return this.getExpirationDate().compareTo(reminder.getExpirationDate());
    }

	@Transient
	public ReminderVO copyFromEntityToVo()
	{
		
		ReminderVO reminderVO= new ReminderVO();
		reminderVO.setId(this.getId());
		reminderVO.setAcademicYearId(this.getAcademicYearId());
		reminderVO.setCustId(this.getCustId());
		reminderVO.setName(this.getName());
		reminderVO.setDescription(this.getDescription());
		reminderVO.setExpirationDate(this.getExpirationDate());
		reminderVO.setReminderOption(this.getReminderOption());
		reminderVO.setSpecificDate(this.getSpecificDate());
		return reminderVO;
	}
	@Transient
	public Reminder copyFromVoToEntity(ReminderVO reminderVO)
	{
		this.setName(reminderVO.getName());
		this.setDescription(reminderVO.getDescription());
		this.setExpirationDate(reminderVO.getExpirationDate());
		this.setReminderOption(reminderVO.getReminderOption());
		if("E".equalsIgnoreCase(reminderVO.getReminderOption())){
			this.setSpecificDate(null);
		}else{
			this.setSpecificDate(reminderVO.getSpecificDate());
		}
		this.setLastAccessDate(new Date());
		this.setLastUpdatedDate(new Date());
		return this;
	}
	@Transient
	public String getExpirationDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getExpirationDate());
	}
	@Transient
	public String getSpecificDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, getSpecificDate());
	}

}
