package com.urt.persistence.model.fee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.urt.persistence.model.base.PersistentObject;
@Entity
@Table(name = "excessPayment")
public class ExcessPayment extends PersistentObject{
	
	public ExcessPayment() {
		this.createdDate = new Date();
		this.lastAccessDate = new Date();
		this.lastUpdatedDate = new Date();
	}

	private long accountId;
	private long paymentId;
	private long usedPaymentId;
	private double excessAmount;
	private boolean status;
	//private double usedExcessAmount;
	
	/**
	 * @return the usedExcessAmount
	 *//*
	@Column(name = "usedExcessAmount", nullable = false, columnDefinition=" double default 0")
	public double getUsedExcessAmount() {
		return usedExcessAmount;
	}

	*//**
	 * @param usedExcessAmount the usedExcessAmount to set
	 *//*
	public void setUsedExcessAmount(double usedExcessAmount) {
		this.usedExcessAmount = usedExcessAmount;
	}*/

	/**
	 * @return the accountId
	 */
	
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the paymentId
	 */
	public long getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the usedPaymentId
	 */
	public long getUsedPaymentId() {
		return usedPaymentId;
	}

	/**
	 * @param usedPaymentId the usedPaymentId to set
	 */
	public void setUsedPaymentId(long usedPaymentId) {
		this.usedPaymentId = usedPaymentId;
	}

	/**
	 * @return the excessAmount
	 */
	@Column(name = "excessAmount", nullable = false, columnDefinition=" double default 0")
	public double getExcessAmount() {
		return excessAmount;
	}

	/**
	 * @param excessAmount the excessAmount to set
	 */
	public void setExcessAmount(double excessAmount) {
		this.excessAmount = excessAmount;
	}

	/**
	 * @return the status
	 */
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
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
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	
}
