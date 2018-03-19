package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.hyniva.sms.ws.vo.SalaryVO;
import com.urt.persistence.model.base.PersistentObject;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: Salary.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Jul 12, 2012     Narahari          	Created
/********************************************************************/


@Entity
@Table(name = "salary")
public class Salary extends PersistentObject  {
    
    
    private double salary;
    private long custId;
    
    private int month;
    private int year;
    
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
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
    public String getSalaryCreatedDateStr() {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.createdDate);

    }
     
	
    
    @Transient
	public Salary copyFromVoToEntity(Salary salary, SalaryVO salaryVo)
	{
    	if(salary.getId() == 0)
    		salary.id = salaryVo.id;		 
		salary.salary = salaryVo.salary;
		salary.custId = salaryVo.custId;
		salary.month = salaryVo.month;
		salary.year = salaryVo.year;
		salary.lastAccessDate = salaryVo.lastAccessDate;
		salary.lastUpdatedDate = salaryVo.lastUpdatedDate;
		salary.createdDate = salaryVo.createdDate;
		//salary.createdById = salaryVo.createdById;
		//salary.lastUpdatedById = salaryVo.lastUpdatedById;
		return salary;
	}
	@Transient
	public SalaryVO copyFromEntityToVo(Salary salary)
	{
		SalaryVO salaryVo = new SalaryVO();
		salaryVo.id = salary.id;
		salaryVo.salary = salary.salary;
		salaryVo.custId = salary.custId;
		salaryVo.month = salary.month;
		salaryVo.year = salary.year;
		salaryVo.lastAccessDate = salary.lastAccessDate;
		salaryVo.lastUpdatedDate = salary.lastUpdatedDate;
		salaryVo.createdDate = salary.createdDate;
		return salaryVo;
	}

}
