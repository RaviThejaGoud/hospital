package com.urt.persistence.model.hostel;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table manageItems.
 */
@Entity
@Table(name = "issueProvisionItemsToMess")
public class IssueProvisionItemsToMess  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	public IssueProvisionItemsToMess() {
        
    }

    public IssueProvisionItemsToMess(long id) {
        setId(id);
    }

    
    private long provisionItemId;
    private long messId;
    private long messFoodTypeId;
    private double usedQuantity;
    private Date issueDate;
    private long custId;
    private String status;

     
    

	public long getProvisionItemId() {
		return provisionItemId;
	}

	public void setProvisionItemId(long provisionItemId) {
		this.provisionItemId = provisionItemId;
	}

	public long getMessId() {
		return messId;
	}

	public void setMessId(long messId) {
		this.messId = messId;
	}

	public long getMessFoodTypeId() {
		return messFoodTypeId;
	}

	public void setMessFoodTypeId(long messFoodTypeId) {
		this.messFoodTypeId = messFoodTypeId;
	}

	public double getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(double usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
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
	@Transient
	public String getIssueDateStr()
	{
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getIssueDate()); 
	}
}
