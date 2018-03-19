package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "staffMonthlyPaySlips")
public class StaffMonthlyPaySlips extends PersistentObject  {
	private static final long serialVersionUID = 3690197650654049848L;
	 
	private long accountId;
    private int month;
    private long custId;
    private String filePath;
    
	
    
    public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
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
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

}
