package com.urt.persistence.model.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.secretary.FinancialYear;

@Entity
@Table(name = "finAccountDetails")
public class FinancialAccountDetails extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Customer customer;
	public FinancialYear financialYear;
	public FinancialAccountType financialAccountType;
	public FinancialCustomerDetails finCustomerDetails;
	public FinancialAccountCategory financialAccountCategory;
	public FinancialAccountTotals financialAccountTotals;
	
	public String accountName;
	public String tinNumber;
	public Date tinIssueDate;
	public String gstNumber;
	public Date gstIssueDate;
	public String itPanNumber;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finAccountCategoryId", insertable = true, updatable = true)
	public FinancialAccountCategory getFinancialAccountCategory() {
		return financialAccountCategory;
	}

	public void setFinancialAccountCategory(
			FinancialAccountCategory financialAccountCategory) {
		this.financialAccountCategory = financialAccountCategory;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finCustomerDetailsId", insertable = true, updatable = true)
	public FinancialCustomerDetails getFinCustomerDetails() {
		return finCustomerDetails;
	}

	public void setFinCustomerDetails(
			FinancialCustomerDetails finCustomerDetails) {
		this.finCustomerDetails = finCustomerDetails;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "custId", insertable = true, updatable = true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "financialYearId", insertable = true, updatable = true)
	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountTypeId", insertable = true, updatable = true)
	public FinancialAccountType getFinancialAccountType() {
		return financialAccountType;
	}

	public void setFinancialAccountType(
			FinancialAccountType financialAccountType) {
		this.financialAccountType = financialAccountType;
	}

	@Column(name = "accountName", nullable = true, length = 250)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "tinNumber", nullable = true, length = 20)
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

	@Column(name = "gstNumber", nullable = true, length = 20)
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

	@Column(name = "itPanNumber", nullable = true, length = 10)
	public String getItPanNumber() {
		return itPanNumber;
	}

	public void setItPanNumber(String itPanNumber) {
		this.itPanNumber = itPanNumber;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transient
	public String getAccountNameAndNum(){
		return this.getAccountName();
	}
	@Transient
	public String getGstIssueDateStr(){
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,this.getGstIssueDate()); 	
	}
	@Transient
	public String getTinIssueDateStr(){
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,this.getTinIssueDate());
	}
	@Transient
	public FinancialAccountTotals getFinancialAccountTotals() {
		return financialAccountTotals;
	}

	public void setFinancialAccountTotals(FinancialAccountTotals financialAccountTotals) {
		this.financialAccountTotals = financialAccountTotals;
	}
	
}
