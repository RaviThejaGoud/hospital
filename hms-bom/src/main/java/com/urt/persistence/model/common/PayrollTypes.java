package com.urt.persistence.model.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: PayrollTypes.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Jul 11, 2012     Narahari          	Created
/********************************************************************/


@Entity
@Table(name = "payrollTypes")
public class PayrollTypes extends PersistentObject {
    
    private static final long serialVersionUID = 3690197650654049848L;
    
    private String payrollName;
    private String payrollDescription;
    private String payrollType;
    private String defaultStatus;
    
    protected List<PayrollSettings> payrollSettings;
    private long custId;
    
	public String getPayrollName() {
		return payrollName;
	}
	
	@Column(name = "defaultStatus", nullable = false, length = 1)
	public String getDefaultStatus() {
		return defaultStatus;
	}
	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
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
	public String getPayrollType() {
		return payrollType;
	}
	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="payrollTypesId") 
	public List<PayrollSettings> getPayrollSettings() {
		return (List<PayrollSettings>) this.payrollSettings;
	}
	public void setPayrollSettings(List<PayrollSettings> payrollSettings) {
		this.payrollSettings = payrollSettings;
	}
	 public boolean equals(Object object) 
	 {
		 if (null == object || this.getClass() != object.getClass()) {
			 return false;
		 } else{  
			 PayrollTypes rhs = (PayrollTypes) object;
			 return new EqualsBuilder().append(this.payrollName, rhs.payrollName).append(
	                this.payrollDescription, rhs.payrollDescription).append(this.payrollType, rhs.payrollType)
	                .isEquals();
		 }
	   }

	    /**
	     * @see java.lang.Object#hashCode()
	     */
	    public int hashCode() {
	        return new HashCodeBuilder(-1923559909, -664973933).append(
	                this.payrollName).append(this.payrollName).toHashCode();
	    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(" Id: ")
                .append(getId())
                .append(" Version: ")
                
                .toString();
    }

    public int compareTo(Object object) 
	{
		PayrollTypes target = (PayrollTypes) object;
    	long timeDifference = 0;
    	if (target.getCreatedDate() != null && this.getCreatedDate() != null) {
    	timeDifference = this.getCreatedDate().getTime()
    	- target.getCreatedDate().getTime();
    	}
    	int difference;
    	if (timeDifference == 0) {
    	difference = 0;
    	} else if (timeDifference > 0) {
    	difference = -1;
    	} else {
    	difference = 1;
    	}
    	return difference;
    }
	
	public void addPayrollSettings(PayrollSettings payrollSettings) {
		if(ObjectFunctions.isNullOrEmpty(this.getPayrollSettings())){
			this.payrollSettings=new ArrayList<PayrollSettings>();
		}
		this.payrollSettings.add(payrollSettings);
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
