package com.urt.util.jrexception;

import java.util.List;

import javax.persistence.Column;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.spring.context.SpringContextAware;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.jrexception.JRException;
import com.urt.persistence.model.jrexception.JRExceptionDetails;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.util.email.MailUtil;


public class JRExceptionThread implements Runnable {
	
	protected transient final Log log = LogFactory.getLog(getClass());
	AdminManager adminManager = (AdminManager) SpringContextAware.getBean("adminManager");
	
	private String exceptionName;
	private String methodName;
    private String className;
    private String fileName;
	private String exceptionLineNumber;
	private String cause;
    private String status;
	private long userId;
	private int custId;
	private int academicYearId;
    private String ipAddress;
    private String computerName;
    private String computerUsername;
    private String exceptionDescription;
    

    /**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}
	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * @return the exceptionDescription
	 */
    @Column(name = "exceptionDescription", columnDefinition="varchar(20480)")
	public String getExceptionDescription() {
		return exceptionDescription;
	}

	/**
	 * @param exceptionDescription the exceptionDescription to set
	 */
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}
	/**
	 * @return the exceptionLineNumber
	 */
	public String getExceptionLineNumber() {
		return exceptionLineNumber;
	}
	/**
	 * @param exceptionLineNumber the exceptionLineNumber to set
	 */
	public void setExceptionLineNumber(String exceptionLineNumber) {
		this.exceptionLineNumber = exceptionLineNumber;
	}
	/**
	 * @return the computerName
	 */
	public String getComputerName() {
		return computerName;
	}
	/**
	 * @param computerName the computerName to set
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	/**
	 * @return the computerUsername
	 */
	public String getComputerUsername() {
		return computerUsername;
	}
	/**
	 * @param computerUsername the computerUsername to set
	 */
	public void setComputerUsername(String computerUsername) {
		this.computerUsername = computerUsername;
	}
	
	/**
	 * @return the exceptionName
	 */
	public String getExceptionName() {
		return exceptionName;
	}

	/**
	 * @param exceptionName the exceptionName to set
	 */
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
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
	public int getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}

	public JRExceptionThread() {
		
	}
	
	public void run(){
		try{
			synchronized(JRExceptionThread.class){
				JRException jrException = new JRException();
				jrException.setUserId(getUserId());
				jrException.setAcademicYearId(getAcademicYearId());
				jrException.setCustId(getCustId());
				jrException.setExceptionName(getExceptionName());
				jrException.setFileName(getFileName());
				jrException.setClassName(getClassName());
				jrException.setMethodName(getMethodName());
				jrException.setCause(getCause());
				jrException.setExceptionLineNumber(getExceptionLineNumber());
				jrException.setIpAddress(getIpAddress());
				jrException.setComputerName(getComputerName());
				jrException.setComputerUsername(getComputerUsername());
				jrException.setStatus(getStatus());
				jrException = (JRException) adminManager.saveOrUpdateObject(jrException);
				if(!ObjectFunctions.isNullOrEmpty(jrException)) {
					JRExceptionDetails jrExceptionDetails = new JRExceptionDetails();
					jrExceptionDetails.setExceptionDescription(getExceptionDescription());
					jrExceptionDetails.setJrException(jrException);
					adminManager.persist(jrExceptionDetails);
					List<Object[]> supportTeamList = adminManager.getAll("select id,email from supportteam where status='"+Constants.YES_STRING+"' and type='"+Constants.DEVELOPER_TEAM+"'");
					String[] emailAddresses = null;
					if(!ObjectFunctions.isNullOrEmpty(supportTeamList)){
						int count = 0;
						emailAddresses = new String[supportTeamList.size()];
						for(Object[] supportTeam : supportTeamList){
							if(!ObjectFunctions.isNullOrEmpty(supportTeam) && !ObjectFunctions.isNullOrEmpty(supportTeam[1])){
								emailAddresses[count] = supportTeam[1].toString();
							}
						    count++;
						    supportTeam = null;
						}
					}else{
						emailAddresses = new String[] {"ganesh@hyniva.com","sekhar@hyniva.com","siva@hyniva.com","narahari@hyniva.com","raviteja@hyniva.com"};
					}
					supportTeamList = null;
					Customer customer = adminManager.getCustomerByCustId((long)getCustId());
					ViewAllUsers viewAllUsers = (ViewAllUsers) adminManager.get(ViewAllUsers.class, "accountId="+(long)getUserId());
					StringBuffer emailSubject = new StringBuffer();
					if("D".equals(customer.getAccountType())){
						emailSubject.append("Dev Error: EazySchool");
					}else{
						emailSubject.append("Production Error: EazySchool");
					}
					emailSubject.append(" User Id:").append(getUserId()).append(" & Cust Id:").append(getCustId());
						MailUtil mailUtil = new MailUtil(emailAddresses, emailSubject.toString());
					mailUtil.sendMailErrorLogToSupportTeam(jrException, getExceptionDescription(), customer, viewAllUsers);
					//toEmailAddresses = null;
					mailUtil = null;
					//hostUrl = null;
					viewAllUsers = null;
					customer = null;
					jrExceptionDetails = null;
				}
				jrException = null;
				log.debug("JRExceptionThread Save Process Completed.");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}	      
    }
	
}
