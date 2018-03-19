package com.hyniva.sms.ws.vo;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class RunPaySlipReportsVO extends SMSBaseVO{
	private String fileName;
	private  String result;
	private String exception;
	private String serverType;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	
	
}