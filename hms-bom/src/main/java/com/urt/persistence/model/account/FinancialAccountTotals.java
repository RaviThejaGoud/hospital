package com.urt.persistence.model.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.secretary.FinancialYear;

@Entity
@Table(name = "finAccountTotal")
public class FinancialAccountTotals extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Customer customer;
	public FinancialYear financialYear;
	public FinancialAccountDetails financialAccountDetails;

	public String transactionType;
	public double balanceAmount;
	public double month1;
	public double month2;
	public double month3;
	public double month4;
	public double month5;
	public double month6;
	public double month7;
	public double month8;
	public double month9;
	public double month10;
	public double month11;
	public double month12;
	

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

	@Column(name = "transactionType", nullable = true, length = 1)
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
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finAccountId", insertable = true, updatable = true)
	public FinancialAccountDetails getFinancialAccountDetails() {
		return financialAccountDetails;
	}

	public void setFinancialAccountDetails(FinancialAccountDetails financialAccountDetails) {
		this.financialAccountDetails = financialAccountDetails;
	}

	public double getMonth1() {
		return month1;
	}

	public void setMonth1(double month1) {
		this.month1 = month1;
	}

	public double getMonth2() {
		return month2;
	}

	public void setMonth2(double month2) {
		this.month2 = month2;
	}

	public double getMonth3() {
		return month3;
	}

	public void setMonth3(double month3) {
		this.month3 = month3;
	}

	public double getMonth4() {
		return month4;
	}

	public void setMonth4(double month4) {
		this.month4 = month4;
	}

	public double getMonth5() {
		return month5;
	}

	public void setMonth5(double month5) {
		this.month5 = month5;
	}

	public double getMonth6() {
		return month6;
	}

	public void setMonth6(double month6) {
		this.month6 = month6;
	}

	public double getMonth7() {
		return month7;
	}

	public void setMonth7(double month7) {
		this.month7 = month7;
	}

	public double getMonth8() {
		return month8;
	}

	public void setMonth8(double month8) {
		this.month8 = month8;
	}

	public double getMonth9() {
		return month9;
	}

	public void setMonth9(double month9) {
		this.month9 = month9;
	}

	public double getMonth10() {
		return month10;
	}

	public void setMonth10(double month10) {
		this.month10 = month10;
	}

	public double getMonth11() {
		return month11;
	}

	public void setMonth11(double month11) {
		this.month11 = month11;
	}

	public double getMonth12() {
		return month12;
	}

	public void setMonth12(double month12) {
		this.month12 = month12;
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
	public String getTransactionTypeName() {
		if ("D".equalsIgnoreCase(getTransactionType())) {
			return "Dr";
		} else {
			return "Cr";
		}
	}
	
}
