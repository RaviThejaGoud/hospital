package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class FeeMainVO extends SMSBaseVO{

	protected List<FeeVO> feeVOs;

	public List<FeeVO> getFeeVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.feeVOs))
		{
			this.feeVOs = new ArrayList<FeeVO>(); 
		}
		return feeVOs;
	}

	public void setFeeVOs(List<FeeVO> feeVOs) {
		this.feeVOs = feeVOs;
	}
	
	
}
