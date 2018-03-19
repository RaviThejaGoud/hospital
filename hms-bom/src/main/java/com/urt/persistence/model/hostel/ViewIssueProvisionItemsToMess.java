package com.urt.persistence.model.hostel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "vw_issueProvisionItemsToMess")
public class ViewIssueProvisionItemsToMess implements Serializable,Cloneable,Comparable  {

    private static final long serialVersionUID = 1L;

    
    
    private long issueProvisionItemsToMessId;
    private String status;
    private Double usedQuantity;
    private long custId;
    private Date issueDate;
    private long provisionItemsId;
    private String itemName;
	private String measurement;
	private long messFoodTypeId;
	private String foodTypeName;
	private long messId;
	private String messName;
	
	
	@Id
	public long getIssueProvisionItemsToMessId() {
		return issueProvisionItemsToMessId;
	}

	public void setIssueProvisionItemsToMessId(long issueProvisionItemsToMessId) {
		this.issueProvisionItemsToMessId = issueProvisionItemsToMessId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(Double usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public long getProvisionItemsId() {
		return provisionItemsId;
	}

	public void setProvisionItemsId(long provisionItemsId) {
		this.provisionItemsId = provisionItemsId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public long getMessFoodTypeId() {
		return messFoodTypeId;
	}

	public void setMessFoodTypeId(long messFoodTypeId) {
		this.messFoodTypeId = messFoodTypeId;
	}

	public String getFoodTypeName() {
		return foodTypeName;
	}

	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}

	public long getMessId() {
		return messId;
	}

	public void setMessId(long messId) {
		this.messId = messId;
	}

	public String getMessName() {
		return messName;
	}

	public void setMessName(String messName) {
		this.messName = messName;
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
	
	@Transient
	public String getIssueDateStr()
	{
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getIssueDate()); 
	}
}
