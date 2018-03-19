package com.hyniva.sms.ws.vo.account;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class FinAccountMainVO extends SMSBaseVO{

	protected List<FinancialAccountDetailsVO> financialAccountDetailsVOs;

	public List<FinancialAccountDetailsVO> getFinancialAccountDetailsVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.financialAccountDetailsVOs))
		{
			this.financialAccountDetailsVOs = new ArrayList<FinancialAccountDetailsVO>(); 
		}
		return financialAccountDetailsVOs;
	}

	public void setFinancialAccountDetailsVOs(
			List<FinancialAccountDetailsVO> financialAccountDetailsVOs) {
		this.financialAccountDetailsVOs = financialAccountDetailsVOs;
	}
	
	
}
