package com.hyniva.sms.ws.vo.account;

import java.beans.Transient;
import java.util.Date;

import com.churchgroup.util.date.DateFormatter;
import com.hyniva.sms.ws.vo.CustomerVo;


public class FinancialAccountDetailsVO {	
	
	public long id;
	public CustomerVo customerVo;
	public FinancialYearVO financialYearVO;
	public FinancialAccountTypeVO financialAccountTypeVO;
	public FinancialCustomerDetailsVO financialCustomerDetailsVO;
	public FinancialAccountCategoryVO financialAccountCategoryVO;
	public FinancialAccountTotalsVO financialAccountTotalsVO;
	public String accountName;
	public String tinNumber;
	public Date tinIssueDate;
	public String gstNumber;
	public Date gstIssueDate;
	public String itPanNumber;
	public long clientId;
	public String tinIssueDateStr;
	public String gstIssueDateStr;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public FinancialAccountCategoryVO getFinancialAccountCategoryVO() {
		return financialAccountCategoryVO;
	}
	public void setFinancialAccountCategoryVO(FinancialAccountCategoryVO financialAccountCategoryVO) {
		this.financialAccountCategoryVO = financialAccountCategoryVO;
	}

	public FinancialCustomerDetailsVO getFinancialCustomerDetailsVO() {
		return financialCustomerDetailsVO;
	}
	public void setFinancialCustomerDetailsVO(FinancialCustomerDetailsVO financialCustomerDetailsVO) {
		this.financialCustomerDetailsVO = financialCustomerDetailsVO;
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
	public FinancialAccountTypeVO getFinancialAccountTypeVO() {
		return financialAccountTypeVO;
	}
	public void setFinancialAccountTypeVO(FinancialAccountTypeVO financialAccountTypeVO) {
		this.financialAccountTypeVO = financialAccountTypeVO;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getTinNumber() {
		return tinNumber;
	}
	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}
	 
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getItPanNumber() {
		return itPanNumber;
	}
	public void setItPanNumber(String itPanNumber) {
		this.itPanNumber = itPanNumber;
	}
	public Date getTinIssueDate() {
		return tinIssueDate;
	}
	public void setTinIssueDate(Date tinIssueDate) {
		this.tinIssueDate = tinIssueDate;
	}
	public Date getGstIssueDate() {
		return gstIssueDate;
	}
	public void setGstIssueDate(Date gstIssueDate) {
		this.gstIssueDate = gstIssueDate;
	}
	
	public FinancialAccountTotalsVO getFinancialAccountTotalsVO() {
		return financialAccountTotalsVO;
	}
	public void setFinancialAccountTotalsVO(
			FinancialAccountTotalsVO financialAccountTotalsVO) {
		this.financialAccountTotalsVO = financialAccountTotalsVO;
	}
	@Transient
    public String getGstIssueDateString() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getGstIssueDate());
    }
	@Transient
    public String getTinIssueDateString() {
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getTinIssueDate());
    }
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
	public void setTinIssueDateStr(String tinIssueDateStr) {
		this.tinIssueDateStr = tinIssueDateStr;
	}
	public void setGstIssueDateStr(String gstIssueDateStr) {
		this.gstIssueDateStr = gstIssueDateStr;
	}
	public String getTinIssueDateStr() {
		return tinIssueDateStr;
	}
	public String getGstIssueDateStr() {
		return gstIssueDateStr;
	}
    
	
}

