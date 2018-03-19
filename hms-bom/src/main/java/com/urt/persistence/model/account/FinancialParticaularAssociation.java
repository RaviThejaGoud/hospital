package com.urt.persistence.model.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.customer.FeeType;

@Entity
@Table(name = "finFeeParticularAssociation")
public class FinancialParticaularAssociation extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public long custId;
	
	public FeeType feeType;
	public FinancialAccountDetails financialAccountDetails; 
	
	

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="paticularId", insertable=true, updatable=true)
	public FeeType getFeeType() {
		return feeType;
	}

	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="finAccountId", insertable=true, updatable=true)
	public FinancialAccountDetails getFinancialAccountDetails() {
		return financialAccountDetails;
	}

	public void setFinancialAccountDetails(FinancialAccountDetails financialAccountDetails) {
		this.financialAccountDetails = financialAccountDetails;
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

}
