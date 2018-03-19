package com.hyniva.sms.ws.vo.account;

public class FinancialAccountStatementVO {	
	
	public long id;
	public String statementName;
	public String statmentCode;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatementName() {
		return statementName;
	}
	public void setStatementName(String statementName) {
		this.statementName = statementName;
	}
	public String getStatmentCode() {
		return statmentCode;
	}
	public void setStatmentCode(String statmentCode) {
		this.statmentCode = statmentCode;
	}
	 
}

