package com.hyniva.sms.ws.vo.account;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class FinCategoryMainVO extends SMSBaseVO{

	protected List<FinancialAccountCategoryVO> financialAccountCategoryVOs;

	public List<FinancialAccountCategoryVO> getFinancialAccountCategoryVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.financialAccountCategoryVOs))
		{
			this.financialAccountCategoryVOs = new ArrayList<FinancialAccountCategoryVO>(); 
		}
		return financialAccountCategoryVOs;
	}

	public void setFinancialAccountCategoryVOs(
			List<FinancialAccountCategoryVO> financialAccountCategoryVOs) {
		this.financialAccountCategoryVOs = financialAccountCategoryVOs;
	}
	
	
}
