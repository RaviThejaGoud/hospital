/*************************************************************************
* Copyright (C) 2017
* HYNIVA
* All Rights Reserved 
*
* File: SMSVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   Sep 18, 2017    KulaSekhar		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo.common;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class SMSVO extends SMSBaseVO {
	
	private String message;
	private String messageType;
	
	private String mobileNumber;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	

}
