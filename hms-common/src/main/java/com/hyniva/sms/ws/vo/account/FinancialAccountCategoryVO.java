package com.hyniva.sms.ws.vo.account;


public class FinancialAccountCategoryVO {	
	
	public long id;
	public long custId;
	public String cartegoryName;
	public FinancialAccountStatementVO financialAccountStatementVO;
	public FinancialAccountTypeVO financialAccountTypeVO;
	public long clientId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getCartegoryName() {
		return cartegoryName;
	}
	public void setCartegoryName(String cartegoryName) {
		this.cartegoryName = cartegoryName;
	}
	public FinancialAccountStatementVO getFinancialAccountStatementVO() {
		return financialAccountStatementVO;
	}
	public void setFinancialAccountStatementVO(
			FinancialAccountStatementVO financialAccountStatementVO) {
		this.financialAccountStatementVO = financialAccountStatementVO;
	}
	public FinancialAccountTypeVO getFinancialAccountTypeVO() {
		return financialAccountTypeVO;
	}
	public void setFinancialAccountTypeVO(
			FinancialAccountTypeVO financialAccountTypeVO) {
		this.financialAccountTypeVO = financialAccountTypeVO;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
	
	
	 
}

