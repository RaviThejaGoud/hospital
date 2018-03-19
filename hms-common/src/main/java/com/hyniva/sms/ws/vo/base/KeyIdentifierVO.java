/*************************************************************************
* Copyright (C) 2015
* ACG
* All Rights Reserved 
*
* File: ErrorDetailVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   June 18, 2015    Sreeram		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo.base;


public class KeyIdentifierVO {
	
	private long accountId;
	private long custId;
	private long academicYearId;
	private String channel;
	private String status;
	
	
	public KeyIdentifierVO(long accountId, long custId) {
		this.accountId = accountId;
		this.custId = custId;
	}
	public KeyIdentifierVO(long accountId, long custId, long academicYearId) {
		this.accountId = accountId;
		this.custId = custId;
		this.academicYearId = academicYearId;
	}
	public KeyIdentifierVO(long accountId, long custId, long academicYearId, String channel) {
		this.accountId = accountId;
		this.custId = custId;
		this.academicYearId = academicYearId;
		this.channel = channel;
	}
	public KeyIdentifierVO(String channel){
		this.channel = channel;
	}
	public KeyIdentifierVO() {
		
	}
	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}
	/**
	 * @return the academicYearId
	 */
	public long getAcademicYearId() {
		return academicYearId;
	}
	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
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
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
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
	
}
