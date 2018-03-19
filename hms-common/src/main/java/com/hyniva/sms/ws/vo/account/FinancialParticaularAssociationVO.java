package com.hyniva.sms.ws.vo.account;

public class FinancialParticaularAssociationVO {	
	
	public long id;
	public long particularId;
	public long accountId;
	public long clientId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParticularId() {
		return particularId;
	}
	public void setParticularId(long particularId) {
		this.particularId = particularId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
    
	
}

