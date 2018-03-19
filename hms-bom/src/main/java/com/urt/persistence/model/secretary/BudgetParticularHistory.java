package com.urt.persistence.model.secretary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "budgetParticularHistory")
public class BudgetParticularHistory  extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	protected long particularId;
	protected double amount;
	protected String createdBy;
	

	@Column(name = "createdBy", nullable = true, length = 1,columnDefinition="char(2) default 'M'")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public long getParticularId() {
		return particularId;
	}
	public void setParticularId(long particularId) {
		this.particularId = particularId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public int compareTo(Object object) {
		return 0;
	}
	@Override
	public boolean equals(Object o) {
		return false;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	@Override
	public String toString() {
		return "";
	}
	
}
