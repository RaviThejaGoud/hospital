/*************************************************************************
* Copyright (C) 2015
* ACG
* All Rights Reserved 
*
* File: APIStatusVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   June 18, 2015    Sreeram		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo.base;


public class APIStatusVO {

	private Integer code;
	private String errorMsg;
	
	/**
	 * 
	 */
	public APIStatusVO() {
		this.code = 200;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @param code
	 */
	public APIStatusVO(Integer code) {
		this.code = code;
	}
	/**
	 * @param code
	 */
	public APIStatusVO(Integer code,String errorMsg) {
		this.code = code;
		this.errorMsg= errorMsg;
	}
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

}
