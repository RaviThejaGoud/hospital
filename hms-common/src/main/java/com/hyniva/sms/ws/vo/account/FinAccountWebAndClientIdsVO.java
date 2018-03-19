package com.hyniva.sms.ws.vo.account;


public class FinAccountWebAndClientIdsVO {

	public long webId;
	public long clientId;
	public FinAccountTotalWebAndClientIdsVO accountTotalWebAndClientIdsVO;
	public FinCustomerWebAndClientIdsVO customerWebAndClientIdsVO;
	
	public long getWebId() {
		return webId;
	}
	public void setWebId(long webId) {
		this.webId = webId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public FinAccountTotalWebAndClientIdsVO getAccountTotalWebAndClientIdsVO() {
		return accountTotalWebAndClientIdsVO;
	}
	public void setAccountTotalWebAndClientIdsVO(
			FinAccountTotalWebAndClientIdsVO accountTotalWebAndClientIdsVO) {
		this.accountTotalWebAndClientIdsVO = accountTotalWebAndClientIdsVO;
	}
	public FinCustomerWebAndClientIdsVO getCustomerWebAndClientIdsVO() {
		return customerWebAndClientIdsVO;
	}
	public void setCustomerWebAndClientIdsVO(
			FinCustomerWebAndClientIdsVO customerWebAndClientIdsVO) {
		this.customerWebAndClientIdsVO = customerWebAndClientIdsVO;
	}
	
	
}
	

