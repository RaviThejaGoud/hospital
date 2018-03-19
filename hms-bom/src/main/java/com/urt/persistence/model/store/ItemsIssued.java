package com.urt.persistence.model.store;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;


@Entity
@Table(name = "issuedItems")

public class ItemsIssued extends PersistentObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long quantity;
	private String recieverName;
	private long itemId;
	private long custId;
	private Date issuedDate;
	private Long issuedBy;
	private String issuerName;

	@Column(nullable = false, length = 10)
	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@Column(nullable = false, length = 40)
	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}
	@Column(nullable = false)
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	@Column(nullable = false)
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the issuedDate
	 */
	public Date getIssuedDate() {
		return issuedDate;
	}

	/**
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	/**
	 * @return the issuedBy
	 */
	public Long getIssuedBy() {
		return issuedBy;
	}

	/**
	 * @param issuedBy the issuedBy to set
	 */
	public void setIssuedBy(Long issuedBy) {
		this.issuedBy = issuedBy;
	}

	/**
	 * @return the issuerName
	 */
	public String getIssuerName() {
		return issuerName;
	}

	/**
	 * @param issuerName the issuerName to set
	 */
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

}
