package com.urt.persistence.model.hostel;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "manageItems")
public class ManageItems  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	public ManageItems() {
        
    }

    public ManageItems(long id) {
        setId(id);
    }

    
    private ProvisionItems provisionItems;
    private Merchant merchant;
    private double quantity;
    private double price;
    private Date purchaseDate;
    private long custId;
    private String status;
    private long messId;

     
    
    @Column(name = "messId",columnDefinition = "int default 0")
    public long getMessId() {
		return messId;
	}

	public void setMessId(long messId) {
		this.messId = messId;
	}

	@OneToOne
    @JoinColumn(name="provisionItemsId",nullable=true)
	public ProvisionItems getProvisionItems() {
		return provisionItems;
	}

	public void setProvisionItems(ProvisionItems provisionItems) {
		this.provisionItems = provisionItems;
	}

	@OneToOne
    @JoinColumn(name="merchantId",nullable=true)
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
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
	public String getPurchaseDateStr()
	{
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getPurchaseDate()); 
	}
	@Transient
	public String getQuantityMeasurementStr()
	{
		if(getQuantity() > 0)
		{
			return getQuantity() + " " + getProvisionItems().getMeasurement();
		}
		return "0";
	}
}
