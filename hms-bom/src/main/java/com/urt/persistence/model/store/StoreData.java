package com.urt.persistence.model.store;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;

@Entity
@Table(name = "storeData")
public class StoreData extends PersistentObject {
	
	private static final long serialVersionUID = 1L;	
	private String storeName;	
	//private String storeKeeperName;
	private Address storeAddress;
	private long custId;
	private Long storeKeeperAccountId;
	private List<Item> items;
		
	@Column(nullable = false, length = 40)
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}	
	
	/*@Column(nullable = false, length = 40)
	public String getStoreKeeperName() {
		return storeKeeperName;
	}
	public void setStoreKeeperName(String storeKeeperName) {
		this.storeKeeperName = storeKeeperName;
	}	*/
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="addressId", insertable=true, updatable=true) 	
	public Address getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(Address storeAddress) {
		this.storeAddress = storeAddress;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@OneToMany	
	@JoinColumn(name="storeId")
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}	
	
	@Override
	public String toString() {		
		return "";
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
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the storeKeeperAccountId
	 */
	@Column(name = "storeKeeperAccountId", nullable = false ,columnDefinition="bigint(20) default 0")
	public Long getStoreKeeperAccountId() {
		return storeKeeperAccountId;  
	}

	/**
	 * @param storeKeeperAccountId the storeKeeperAccountId to set
	 */
	public void setStoreKeeperAccountId(Long storeKeeperAccountId) {
		this.storeKeeperAccountId = storeKeeperAccountId;
	}

	
}
