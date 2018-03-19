package com.hyniva.sms.ws.vo;

import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class AndroidMobileUsersVO extends SMSBaseVO{
	
	protected String registrationKey;
	
	private KeyIdentifierVO identifier;
	
	public KeyIdentifierVO getIdentifier() {
		return identifier;
	}
	public void setIdentifier(KeyIdentifierVO identifier) {
		this.identifier = identifier;
	}
	public String getRegistrationKey() {
		return registrationKey;
	}
	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}

}
