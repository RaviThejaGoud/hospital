package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class FeeTypeMainVO extends SMSBaseVO{

	protected List<FeeTypeVO> feeTypeVOs;

	public List<FeeTypeVO> getFeeTypeVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.feeTypeVOs))
		{
			this.feeTypeVOs = new ArrayList<FeeTypeVO>(); 
		}
		return feeTypeVOs;
	}

	public void setFeeTypeVOs(List<FeeTypeVO> feeTypeVOs) {
		this.feeTypeVOs = feeTypeVOs;
	}
	
	
    
}
