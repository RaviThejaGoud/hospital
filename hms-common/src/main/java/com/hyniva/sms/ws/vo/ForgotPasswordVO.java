package com.hyniva.sms.ws.vo;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class ForgotPasswordVO extends SMSBaseVO{
	
	private String email;
	private String mobileNumber;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	

		
}
