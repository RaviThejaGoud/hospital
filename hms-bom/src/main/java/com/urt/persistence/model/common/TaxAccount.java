package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Staff;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: TaxAccount.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Aug 06, 2012     Narahari          	Created
/********************************************************************/


@Entity
@Table(name = "taxAccount")
public class TaxAccount extends PersistentObject  {
    
    
    private double taxAmountPerYear;
    private double netAmount;
    private double taxPercentage;
    private double taxAmountPerMonth;
    private long custId;
    
    private int month;
    private int year;
    
    protected Staff staff;
    
	
    @OneToOne
    @JoinColumn(name="staffId")
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public double getTaxAmountPerMonth() {
		return taxAmountPerMonth;
	}
	public void setTaxAmountPerMonth(double taxAmountPerMonth) {
		this.taxAmountPerMonth = taxAmountPerMonth;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	public double getTaxAmountPerYear() {
		return taxAmountPerYear;
	}
	public void setTaxAmountPerYear(double taxAmountPerYear) {
		this.taxAmountPerYear = taxAmountPerYear;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
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
    public String getTaxAccountCreatedDateStr() {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.createdDate);

    }

}
