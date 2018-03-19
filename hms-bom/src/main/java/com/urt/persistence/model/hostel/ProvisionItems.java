package com.urt.persistence.model.hostel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table provisionItems.
 */
@Entity
@Table(name = "provisionItems")
public class ProvisionItems  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	public ProvisionItems() {
        
    }

    public ProvisionItems(long id) {
        setId(id);
    }

    
    private String itemName;
    private String measurement;
    private long custId;
    private String status;

     
    

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
	public String getItemMeasurement()
	{
		if(!StringFunctions.isNullOrEmpty(getItemName()) && !StringFunctions.isNullOrEmpty(getMeasurement()))
		{
			return getItemName() + " " + getMeasurement();
		}
		return null;
	}
}
