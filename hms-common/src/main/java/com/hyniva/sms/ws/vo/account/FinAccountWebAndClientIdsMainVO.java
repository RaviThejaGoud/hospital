package com.hyniva.sms.ws.vo.account;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class FinAccountWebAndClientIdsMainVO extends SMSBaseVO{
	
	List<FinAccountWebAndClientIdsVO> accountWebAndClientIdsVOs;

	public List<FinAccountWebAndClientIdsVO> getAccountWebAndClientIdsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.accountWebAndClientIdsVOs))
		{
			this.accountWebAndClientIdsVOs = new ArrayList<FinAccountWebAndClientIdsVO>(); 
		}
		return accountWebAndClientIdsVOs;
	}

	public void setAccountWebAndClientIdsVOs(List<FinAccountWebAndClientIdsVO> accountWebAndClientIdsVOs) {
		this.accountWebAndClientIdsVOs = accountWebAndClientIdsVOs;
	}

	
}
	

