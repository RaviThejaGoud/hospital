package com.urt.persistence.model.jrexception;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;


@Entity
@Table(name = "jrException")
public class JRException extends PersistentObject {
  
	private static final long serialVersionUID = 1567864261885107076L;
	
	private String exceptionName;
    private String methodName;
    private String className;
    private String fileName;
    private String exceptionLineNumber;
    private String cause;
    private String status; // P - Pending,  R - Resolved, I - Ignored, D - Permanently Ignored
    private long userId;
    private int custId;
    private int academicYearId;;
    private String ipAddress;
    private String computerName;
    private String computerUsername;
    
    
    /**
	 * @return the cause
	 */
    @Column(name = "cause", columnDefinition="varchar(2048)")
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
	 * @return the exceptionLineNumber
	 */
    @Column(name = "exceptionLineNumber", columnDefinition="varchar(2048)")
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
    @Column(name = "computerName", columnDefinition="varchar(64)")
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
	@Column(name = "computerUsername", columnDefinition="varchar(64)")
	public String getComputerUsername() {
		return computerUsername;
	}
	/**
	 * @param computerUsername the computerUsername to set
	 */
	public void setComputerUsername(String computerUsername) {
		this.computerUsername = computerUsername;
	}
	@Column(name = "academicYearId", columnDefinition="int default 0")
	public int getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
	}
	/**
	 * @return the exceptionName
	 */
	@Column(name = "exceptionName", columnDefinition="varchar(4096)")
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
	 * @return the userId
	 */
	@Column(name = "userId", columnDefinition=" bigint(20) default 0")
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
	@Column(name = "custId", columnDefinition=" int default 0")
	public int getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}
	/**
	 * @return the ipAddress
	 */
	@Column(name = "ipAddress", columnDefinition="varchar(20)")
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
	@Column(name = "methodName", columnDefinition="varchar(256)")
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
	@Column(name = "className", columnDefinition="varchar(256)")
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
	@Column(name = "fileName", columnDefinition="varchar(256)")
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the status
	 */
	@Column(name = "status", columnDefinition="char(2) default 'P'")
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	// Constructors
	/** default constructor */
    public JRException() {
    }
    public JRException(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
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
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
}