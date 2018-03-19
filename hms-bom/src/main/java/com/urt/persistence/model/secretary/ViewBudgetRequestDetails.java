package com.urt.persistence.model.secretary;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.string.StringFunctions;

/*
 * @create new view ViewBudgetRequestDetails.
 */
@Entity
@Table(name = "vw_BudgetRequestDetails")
public class ViewBudgetRequestDetails implements Serializable,Cloneable,Comparable  {

    private static final long serialVersionUID = 1L;
    
    protected long budgetRequestId;
    protected long orgId;
    protected long custId;
    protected int requestedMonth;
    protected long financialYearId;
    protected Date createdDate;
    protected String organization;
    
    protected String budgetType;
    protected long managerId;
    
    protected String monthName;
    protected String fullName;
    protected String comments;
    protected double totalBudgetAmount;
    protected String status;
    protected String mobileNumber;
    
    
    public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(long financialYearId) {
		this.financialYearId = financialYearId;
	}

	@Id
	public long getBudgetRequestId() {
		return budgetRequestId;
	}

	public void setBudgetRequestId(long budgetRequestId) {
		this.budgetRequestId = budgetRequestId;
	}

	public int getRequestedMonth() {
		return requestedMonth;
	}

	public void setRequestedMonth(int requestedMonth) {
		this.requestedMonth = requestedMonth;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getTotalBudgetAmount() {
		return totalBudgetAmount;
	}

	public void setTotalBudgetAmount(double totalBudgetAmount) {
		this.totalBudgetAmount = totalBudgetAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ViewBudgetRequestDetails() {

    }

    @Override
    public String toString() {
	return "";
    }

    @Override
	public int compareTo(Object object) {
	//ViewMeetingRequestDetails target = (ViewMeetingRequestDetails) object;
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
   
    @Transient
	public String getUserFormattedCreatedDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.createdDate);
	}
	
	@Transient
    public String getCreatedDatetr() {
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
