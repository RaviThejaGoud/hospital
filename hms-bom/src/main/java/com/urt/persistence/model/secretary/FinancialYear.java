package com.urt.persistence.model.secretary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "financialYear")
public class FinancialYear  extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	protected String description;
	protected Date startDate;
	protected Date endDate;
	protected long orgId;
	protected String status;
	protected String yearName;
	protected List<BudgetRequest> budgetRequest;
	
    
	
	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public void setBudgetRequest(List<BudgetRequest> budgetRequest) {
		this.budgetRequest = budgetRequest;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="financialYearId")
	public List<BudgetRequest> getBudgetRequest() {
		if(ObjectFunctions.isNullOrEmpty(this.budgetRequest))
		{
	    	 this.budgetRequest=new ArrayList<BudgetRequest>();
	    }
		return budgetRequest;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
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
	public String getGenerateAcacemicYearStr() {
		if(!ObjectFunctions.isNullOrEmpty(this.getStartDate()) && !ObjectFunctions.isNullOrEmpty(this.getEndDate()))
		{
			Calendar cal1 = Calendar.getInstance();
		    cal1.setTime(this.getStartDate());
		    int year1 = cal1.get(Calendar.YEAR);
		    
		    Calendar cal2 = Calendar.getInstance();
		    cal2.setTime(this.getEndDate());
		    int year2 = cal2.get(Calendar.YEAR)% 100;
		    
		   // int month = cal1.get(Calendar.MONTH);
		  //  int day = cal.get(Calendar.DAY_OF_MONTH);
		    
			return year1 + "-" + year2;
		}
			
		else return "";
	}
	@Transient
	public String getUserFormattedStartDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.startDate);
	}
	
	@Transient
	public String getUserFormattedEndDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.endDate);
	}
	@Transient
    public String getStartDateStr() {
        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,this.startDate);

    }
	@Transient
    public String getEndDateStr() {
        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,this.endDate);

    }
	
	@Transient
	public void addBudgetRequest(BudgetRequest budgetRequest) {
		if(ObjectFunctions.isNullOrEmpty(budgetRequest)){
			budgetRequest=new BudgetRequest();
		}
		getBudgetRequest().add(budgetRequest);
	}
}
