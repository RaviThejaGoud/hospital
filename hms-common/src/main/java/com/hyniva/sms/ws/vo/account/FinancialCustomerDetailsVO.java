package com.hyniva.sms.ws.vo.account;

import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.CustomerVo;



public class FinancialCustomerDetailsVO {	
	
	public long id;
	public CustomerVo customerVo;
	public FinancialYearVO financialYearVO;
	public FinancialAccountCategoryVO financialAccountCategoryVO;
	public FinancialAccountTypeVO financialAccountTypeVO; 
	public AddressVO addressVO;
	
	public String customerName;
	public String contactName;
	public String mobileNumber;
	public long clientId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	public FinancialYearVO getFinancialYearVO() {
		return financialYearVO;
	}
	public void setFinancialYearVO(FinancialYearVO financialYearVO) {
		this.financialYearVO = financialYearVO;
	}
	public FinancialAccountCategoryVO getFinancialAccountCategoryVO() {
		return financialAccountCategoryVO;
	}
	public void setFinancialAccountCategoryVO(
			FinancialAccountCategoryVO financialAccountCategoryVO) {
		this.financialAccountCategoryVO = financialAccountCategoryVO;
	}
	public FinancialAccountTypeVO getFinancialAccountTypeVO() {
		return financialAccountTypeVO;
	}
	public void setFinancialAccountTypeVO(
			FinancialAccountTypeVO financialAccountTypeVO) {
		this.financialAccountTypeVO = financialAccountTypeVO;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
	
}

