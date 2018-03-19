package com.urt.persistence.model.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "vw_finAccountDetails")
public class ViewFinAccountDetails implements Serializable,Cloneable, Comparable<Object> {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long accountId;
	private long custId;
	private String accountName;
	private String tinNumber;
	private Date tinIssueDate;
	private String gstNumber;
	private Date gstIssueDate;
	private String itPanNumber;
	private long categoryId;
	private String cartegoryName;
	private long statementId;
	private String statementName;
	private String statmentCode;
	private long customerDetailsId;
	private String customerName;
	private String contactName;
	private String mobileNumber;
	private long addressId;
	private String city;
	private String addressLine1;
	private String streetName;
	private String postalCode;
	private String country;
	private String state;
	private String email;
	private long accountTotalId;
	private String transactionType;
	private double balanceAmount;
	private long accountTypeId;
	private String accountType;
	private String accountCode;
	private long financialYearId;
	private String yearName;
	private Date startDate;
	private Date endDate;
	private String status;

	
	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
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

	public Date getTinIssueDate() {
		return tinIssueDate;
	}

	public void setTinIssueDate(Date tinIssueDate) {
		this.tinIssueDate = tinIssueDate;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public Date getGstIssueDate() {
		return gstIssueDate;
	}

	public void setGstIssueDate(Date gstIssueDate) {
		this.gstIssueDate = gstIssueDate;
	}

	public String getItPanNumber() {
		return itPanNumber;
	}

	public void setItPanNumber(String itPanNumber) {
		this.itPanNumber = itPanNumber;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCartegoryName() {
		return cartegoryName;
	}

	public void setCartegoryName(String cartegoryName) {
		this.cartegoryName = cartegoryName;
	}

	public long getStatementId() {
		return statementId;
	}

	public void setStatementId(long statementId) {
		this.statementId = statementId;
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

	public long getCustomerDetailsId() {
		return customerDetailsId;
	}

	public void setCustomerDetailsId(long customerDetailsId) {
		this.customerDetailsId = customerDetailsId;
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

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getAccountTotalId() {
		return accountTotalId;
	}

	public void setAccountTotalId(long accountTotalId) {
		this.accountTotalId = accountTotalId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public long getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Transient
	public String getTransactionTypeName(){
		if("C".equalsIgnoreCase(this.getTransactionType()))
			return "Cr";
		else if ("D".equalsIgnoreCase(this.getTransactionType())) {
			return "Dr";
		}else
			return "";
	}
}
