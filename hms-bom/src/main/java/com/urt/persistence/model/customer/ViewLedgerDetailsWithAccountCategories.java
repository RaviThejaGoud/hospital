package com.urt.persistence.model.customer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewClassExamDetails.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Ganesh Created /
 ********************************************************************/

@Entity
@Table(name = "vw_ledgerDetailsWithAccountCategories")
public class ViewLedgerDetailsWithAccountCategories implements Serializable,Cloneable,Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;


	private long custId;
	private String groupName;
	private String ledgerName;
	private String status;
	private long ledgerId;
	private long accountCatageryId;
	
	
	public long getAccountCatageryId() {
		return accountCatageryId;
	}

	public void setAccountCatageryId(long accountCatageryId) {
		this.accountCatageryId = accountCatageryId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
   @Id
	public long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
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
	  
}
