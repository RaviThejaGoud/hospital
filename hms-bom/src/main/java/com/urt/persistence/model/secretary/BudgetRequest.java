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
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "budgetRequest")
public class BudgetRequest extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	protected String status;
	protected int requestedMonth;
	protected String budgetType;
	protected long custId;
	protected long managerId;
	protected String comments;
	protected String monthName;
	protected double totalBudgetAmount;
	protected List<BudgetParticularHistory> budgetParticularHistory;
	protected List<FinalBudgetRequest> finalBudgetRequest;
	

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public double getTotalBudgetAmount() {
		return totalBudgetAmount;
	}

	public void setTotalBudgetAmount(double totalBudgetAmount) {
		this.totalBudgetAmount = totalBudgetAmount;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="budgetRequestId")
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
	@JoinColumn(name="budgetRequestId")
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

	public int getRequestedMonth() {
		return requestedMonth;
	}
	public void setRequestedMonth(int requestedMonth) {
		this.requestedMonth = requestedMonth;
	}
	@Column(name = "budgetType", nullable = true, length = 1,columnDefinition="char(1) default 'R'")
	public String getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	
	@Transient
	public void addBudgetParticularHistory(BudgetParticularHistory budgetParticularHistory) {
		if(ObjectFunctions.isNullOrEmpty(budgetParticularHistory)){
			budgetParticularHistory=new BudgetParticularHistory();
		}
		getBudgetParticularHistory().add(budgetParticularHistory);
	}
	
	@Transient
	public void addFinalBudgetRequest(FinalBudgetRequest finalBudgetRequest) {
		if(ObjectFunctions.isNullOrEmpty(finalBudgetRequest)){
			finalBudgetRequest=new FinalBudgetRequest();
		}
		getFinalBudgetRequest().add(finalBudgetRequest);
	}
	
	@Transient
	public String getUserFormattedCreatedDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.createdDate);
	}
	
	@Transient
    public String getCreatedDateStr() {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,this.createdDate);
    }
	@Transient
	public String getBudgetStatusDesc() 
	{
		if(!StringFunctions.isNullOrEmpty(getStatus()))
		{
			if("N".equalsIgnoreCase(getStatus()))
			{
				return "New";
			}
			else if("P".equalsIgnoreCase(getStatus()) || "F".equalsIgnoreCase(getStatus()))
			{
				return "Progress";
			}
			else if("A".equalsIgnoreCase(getStatus()))
			{
				return "Approved";
			}
			else if("R".equalsIgnoreCase(getStatus()))
			{
				return "Rejected";
			}
		}
		return "";
	}
}
