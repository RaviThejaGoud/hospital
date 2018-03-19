/*************************************************************************
* Copyright (C) 2015
* ACG
* All Rights Reserved 
*
* File: UserVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   June 18, 2015    Sreeram		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class UserAddressChangeVO extends SMSBaseVO {
	
	public String mobileNumber;
    public AddressVO primaryAddressVo;

    
    
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public AddressVO getPrimaryAddressVo() {
		return primaryAddressVo;
	}

	public void setPrimaryAddressVo(AddressVO primaryAddressVo) {
		this.primaryAddressVo = primaryAddressVo;
	}
	
	
}