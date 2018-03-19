package com.hyniva.sms.ws.vo.account;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.hyniva.sms.ws.vo.cashbook.CashBookVO;


public class FinCashbookMainVO extends SMSBaseVO{

	protected List<CashBookVO> cashBookVOs;

	public List<CashBookVO> getCashBookVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.cashBookVOs))
		{
			this.cashBookVOs = new ArrayList<CashBookVO>(); 
		}
		return cashBookVOs;
	}

	public void setCashBookVOs(List<CashBookVO> cashBookVOs) {
		this.cashBookVOs = cashBookVOs;
	}

	
}
