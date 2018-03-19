package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: PayrollSettings.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Jul 11, 2012     Narahari          	Created
/********************************************************************/


@Entity
@Table(name = "payrollSettings")
public class PayrollSettings extends PersistentObject {
    
    private static final long serialVersionUID = 3690197650654049848L;
    
    private String payrollName;
    private String payrollDescription;
    private String payrollSettingStatus;
    private String payrollType;
    
    private long staffId;
    private double percentage;
    private long custId;
    
    private int month;
    private int year;
    private double basicSalary;
    /* private char taxable;
    private double taxRate;*/
    
    
    
    /*public char getTaxable() {
		return taxable;
	}
	public void setTaxable(char taxable) {
		this.taxable = taxable;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}*/
	public double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public String getPayrollType() {
		return payrollType;
	}
	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}
	public String getPayrollSettingStatus() {
		return payrollSettingStatus;
	}
	public void setPayrollSettingStatus(String payrollSettingStatus) {
		this.payrollSettingStatus = payrollSettingStatus;
	}
	public PayrollSettings() {
		super();
	}
	public PayrollSettings(Long id) {
		super(id);
	}
	public PayrollSettings(Object[] object) {
		super();
		
		this.id = Long.valueOf(object[0].toString());
		this.basicSalary = Double.valueOf(object[7].toString());
		if(!StringFunctions.isNullOrEmpty(object[8].toString()))
		{
			this.custId = Long.valueOf(object[8].toString());
		}
		this.month = Integer.valueOf(object[9].toString());
		this.payrollDescription = object[10].toString();
		this.payrollName = object[11].toString();
		this.payrollSettingStatus = object[12].toString();
		this.payrollType = object[13].toString();
		this.percentage = Double.valueOf(object[14].toString());
		this.staffId = Long.valueOf(object[15].toString());
		this.year = Integer.valueOf(object[16].toString());
		
		
		
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
	
	public String getPayrollName() {
		return payrollName;
	}
	public void setPayrollName(String payrollName) {
		this.payrollName = payrollName;
	}
	public String getPayrollDescription() {
		return payrollDescription;
	}
	public void setPayrollDescription(String payrollDescription) {
		this.payrollDescription = payrollDescription;
	}
	
	
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
    @Transient
	public String getPayrollTypeDesc() 
	{
		if("AW".equalsIgnoreCase(getPayrollType()))
		{
			return "Allowance";
		}
		else if("BS".equalsIgnoreCase(getPayrollType()))
		{
			return "Basic Salary";
		}
		else
			return "Deduction";
	}

}
