package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Staff;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: Payroll.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Jul 11, 2012     Narahari          	Created
/********************************************************************/


@Entity
@Table(name = "payroll")
public class Payroll extends PersistentObject {
    
    private static final long serialVersionUID = 3690197650654049848L;
    
    private String payrollSettingsIds;
    private Date generatedDate;
    
    private double basicPay;
    private double netPay;
    private long custId;
    
    private int month;
    private int year;
    private long salaryId;
    private long staffStatutoryId;
    private String staffLoanEmiIds;
    
    protected Staff staff;
    protected TaxAccount taxAccount;
    
    
    
    @OneToOne
    @JoinColumn(name="taxAccountId")
    public TaxAccount getTaxAccount() {
		return taxAccount;
	}
	public void setTaxAccount(TaxAccount taxAccount) {
		this.taxAccount = taxAccount;
	}
	@OneToOne
    @JoinColumn(name="staffId")
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public String getPayrollSettingsIds() {
		return payrollSettingsIds;
	}
	public void setPayrollSettingsIds(String payrollSettingsIds) {
		this.payrollSettingsIds = payrollSettingsIds;
	}
	public long getStaffStatutoryId() {
		return staffStatutoryId;
	}
	public void setStaffStatutoryId(long staffStatutoryId) {
		this.staffStatutoryId = staffStatutoryId;
	}
	
	public String getStaffLoanEmiIds() {
		return staffLoanEmiIds;
	}
	public void setStaffLoanEmiIds(String staffLoanEmiIds) {
		this.staffLoanEmiIds = staffLoanEmiIds;
	}
	public long getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(long salaryId) {
		this.salaryId = salaryId;
	}
	public Date getGeneratedDate() {
		return generatedDate;
	}
	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	public double getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	
	@Override
    public boolean equals(Object o) 
	{
		return false;
        

    }
    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(" Id: ")
                .append(getId())
                .append(" Version: ")
                
                .toString();
    }

    @Override
    public int compareTo(Object object) 
    {
		return 0;
    	
    }

}
