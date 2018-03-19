package com.hyniva.sms.ws.vo.account;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class FinWebAndClientIdsMainVO extends SMSBaseVO{

	List<FinWebAndClientIdsVO> finWebAndClientIdsVOs;

	public List<FinWebAndClientIdsVO> getFinWebAndClientIdsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.finWebAndClientIdsVOs))
		{
			this.finWebAndClientIdsVOs = new ArrayList<FinWebAndClientIdsVO>(); 
		}
		return finWebAndClientIdsVOs;
	}

	public void setFinWebAndClientIdsVOs(
			List<FinWebAndClientIdsVO> finWebAndClientIdsVOs) {
		this.finWebAndClientIdsVOs = finWebAndClientIdsVOs;
	}
	
	
}
	

