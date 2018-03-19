package com.urt.persistence.model.subscription;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.churchgroup.util.date.DateFormatter;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewGroupType.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0  July 14, 2010        Siva G           	Created
/********************************************************************/

@Entity
@Table(name = "vw_ProductDetailsByCustomer")
public class ViewProductDetailsByCustomer implements Serializable{


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long productId;
     private String productName;
     private String status;
     private String subscriptionType;     
     private String urlName;
     private long subscriptionId;

	 private double amount;
 	 private Date nextPaymentDate; 
 	 private long custId; 
 	 private String productLink; 

   

	public ViewProductDetailsByCustomer()
    {
        super();
    }
  
    public ViewProductDetailsByCustomer(String productName) {
        this.productName = productName;
    }
    
    @Column(name = "subscriptionId", nullable = true, length = 10, unique=false)
	public long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewProductDetailsByCustomer)) return false;

        final ViewProductDetailsByCustomer product = (ViewProductDetailsByCustomer) o;

        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (productLink != null ? !productLink.equals(product.productLink) : product.productLink != null) return false;
        if (nextPaymentDate != null ? !nextPaymentDate.equals(product.nextPaymentDate) : product.nextPaymentDate != null) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = (productName != null ? productName.hashCode() : 0);
        result = 29 * result + (productLink != null ? productLink.hashCode() : 0);
        result = 29 * result + (nextPaymentDate != null ? nextPaymentDate.hashCode() : 0);
        return result;
    }
    
    @Column(name = "status", nullable = false, length = 1,columnDefinition="varchar(5) default 'Y'")
	 public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
   
    /**
     * @see java.lang.Object#toString()
     * 
     * Domestic Address Formatted as
     *  addressLine1; addressLine2; city, state   zipCode[-zipCodeSupplement]
     * 
     * Military Address Formatted as
     *  addressLine1; addressLine2; city postalCode;
     * @Override
     */
    
    public String toString() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        buffer
            .append(" Product Student: ")
            .append(getProductName())            
            .append(" Product Link : ")
            .append(getProductLink())
            .append(" Next PaymentDate : ")
            .append(getNextPaymentDate());
        return buffer.toString();
    }
    public int compareTo(Object object) {
    	ViewProductDetailsByCustomer myClass = (ViewProductDetailsByCustomer) object;
        return new CompareToBuilder().append(this.productName,myClass.productName).append(this.productLink,myClass.productLink).append(this.nextPaymentDate,myClass.nextPaymentDate).toComparison();
    }
  @Id
	 @Column(name = "productId", nullable = true, length = 10, unique=false)
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	 @Column(name = "productName", nullable = true, length = 250)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	 @Column(name = "subscriptionType", nullable = true, length = 250)
	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}
	
	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	@Transient
	public String getActiveStatus() {
		if("Y".equalsIgnoreCase(getStatus()))
		{
			return "Inactive";
		}
		return "Active";
	}
	@Transient
	public String getSubscriptionTypes() {
		if( subscriptionType.equalsIgnoreCase("Y"))
		{
			return "Year";
		}
		return "Monthly";
	}
	@Transient
	public String getNextPaymentDateStr() {
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getNextPaymentDate());
	}

}