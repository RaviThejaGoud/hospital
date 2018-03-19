/*************************************************************************
* Copyright (C) 2015
* ACG
* All Rights Reserved 
*
* File: SMSBaseVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   June 18, 2015    Sreeram		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo.base;


public class SMSBaseVO {
	
	private KeyIdentifierVO identifier;
	private APIStatusVO apiStatus;

	public SMSBaseVO() {
		apiStatus = new APIStatusVO();
		identifier = new KeyIdentifierVO();
	}
	
	public SMSBaseVO(long accountId,long custId ) {
		this.identifier= new KeyIdentifierVO();
		this.identifier.setAccountId(accountId);
		this.identifier.setCustId(custId);
		this.apiStatus = new APIStatusVO();
	}
	public SMSBaseVO(long custId ) {
		this.identifier= new KeyIdentifierVO();
		this.identifier.setCustId(custId);
		this.apiStatus = new APIStatusVO();
	}

	/**
	 * @return the identifier
	 */
	public KeyIdentifierVO getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(KeyIdentifierVO identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the apiStatus
	 */
	public APIStatusVO getApiStatus() {
		return apiStatus;
	}

	/**
	 * @param apiStatus the apiStatus to set
	 */
	public void setApiStatus(APIStatusVO apiStatus) {
		this.apiStatus = apiStatus;
	}
	
	

}