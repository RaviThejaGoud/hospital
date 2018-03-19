package com.urt.webapp.action.userloginmetatdata;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.UserLoginMetaData;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class UserLoginMetaDataThread implements Runnable {
	
	protected transient final Log log = LogFactory.getLog(getClass());
	@Autowired
	protected AdminManager adminManager;
	
	private long userId;
	private long custId;
	private String ipAddress;
	private int loginCount;
	private long academicYearId;
	

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	/**
	 * @return the loginCount
	 */
	public int getLoginCount() {
		return loginCount;
	}

	/**
	 * @param loginCount the loginCount to set
	 */
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public UserLoginMetaDataThread() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public void run(){
		try{
			synchronized(UserLoginMetaDataThread.class){
				String todayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
				UserLoginMetaData userLoginMetaData = (UserLoginMetaData) adminManager.get(UserLoginMetaData.class, "ipAddress='"+getIpAddress()+"' and custId="+getCustId()+" and userId="+getUserId()+" and academicYearId="+getAcademicYearId()+" and createdDate like '"+todayDate+"%'");
				if(ObjectFunctions.isNullOrEmpty(userLoginMetaData)){
					userLoginMetaData = new UserLoginMetaData();
					userLoginMetaData.setUserId(getUserId());
					userLoginMetaData.setAcademicYearId(getAcademicYearId());
					userLoginMetaData.setCustId(getCustId());
					userLoginMetaData.setIpAddress(getIpAddress());
					userLoginMetaData.setLoginCount(1);
					userLoginMetaData.setCreatedDate(new Date());
					userLoginMetaData.setLastAccessDate(new Date());
				} else{
					userLoginMetaData.setLoginCount(userLoginMetaData.getLoginCount()+1);
					userLoginMetaData.setLastAccessDate(new Date());
				}
				adminManager.save(userLoginMetaData);
				userLoginMetaData = null;
				log.info("UserLoginMetaDataThread Save Process Completed.");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}	      
    }
}