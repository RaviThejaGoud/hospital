package com.hyniva.sms.ws.vo.account;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class FinancialYearMainVO extends SMSBaseVO{
	
	public List<FinancialYearVO> financialYearVOS;

	public List<FinancialYearVO> getFinancialYearVOS() {
		if(ObjectFunctions.isNullOrEmpty(this.financialYearVOS))
		{
			this.financialYearVOS = new ArrayList<FinancialYearVO>(); 
		}
		return financialYearVOS;
	}

	public void setFinancialYearVOS(List<FinancialYearVO> financialYearVOS) {
		this.financialYearVOS = financialYearVOS;
	}

	
	
}
	

