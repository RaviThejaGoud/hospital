package com.urt.persistence.model.customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;


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
@Table(name = "vw_voucherDetails")
public class ViewVoucherDetails implements Serializable,Cloneable,Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;


	private long voucherId;
	private long voucherDetailsId;
	private long custId;
	private long financialYearId;
	private long toAccountId;
	private long fromAccountId;
	private String voucherNo;
	private String narration;
	private String groupName;
	private String ledgerName;
	private String name;
	protected Date voucherDate;
	protected double amount;
	protected double totalAmount;
	private String status;
	private long ledgerId;
	
	
	
	
	public long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
 
	public long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(long voucherId) {
		this.voucherId = voucherId;
	}

	@Id
	@Column( name="voucherDetailsId", unique=false, nullable=false, updatable=false )
	public long getVoucherDetailsId() {
		return voucherDetailsId;
	}

	public void setVoucherDetailsId(long voucherDetailsId) {
		this.voucherDetailsId = voucherDetailsId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}
 

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
     
	 @Override
	public int compareTo(Object object) {
       return 0;       
    }
	 @Transient
		public String getVoucherDateStr() {
			return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getVoucherDate());
		}
		
	 @Transient
		public String getVoucherDateFormet() {
			return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getVoucherDate());
		}
		
	  
}
