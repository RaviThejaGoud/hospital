package com.urt.persistence.model.secretary;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "particular")
public class Particular extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	protected String particularName;
	protected String status;
	protected List<BudgetParticularHistory> budgetParticularHistory;
	protected List<FinalBudgetRequest> finalBudgetRequest;
	

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="particularId")
	public List<BudgetParticularHistory> getBudgetParticularHistory() {
		if(ObjectFunctions.isNullOrEmpty(this.budgetParticularHistory))
		{
	    	 this.budgetParticularHistory=new ArrayList<BudgetParticularHistory>();
	    }
		return budgetParticularHistory;
	}

	public void setBudgetParticularHistory(List<BudgetParticularHistory> budgetParticularHistory) {
		this.budgetParticularHistory = budgetParticularHistory;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="particularId")
	public List<FinalBudgetRequest> getFinalBudgetRequest() {
		if(ObjectFunctions.isNullOrEmpty(this.finalBudgetRequest))
		{
	    	 this.finalBudgetRequest=new ArrayList<FinalBudgetRequest>();
	    }
		return finalBudgetRequest;
	}

	public void setFinalBudgetRequest(List<FinalBudgetRequest> finalBudgetRequest) {
		this.finalBudgetRequest = finalBudgetRequest;
	}
	
	public String getParticularName() {
		return particularName;
	}

	public void setParticularName(String particularName) {
		this.particularName = particularName;
	}

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
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
