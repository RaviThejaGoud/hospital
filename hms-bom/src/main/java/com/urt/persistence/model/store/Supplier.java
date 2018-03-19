package com.urt.persistence.model.store;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.urt.persistence.model.account.CustomerBankAccountDetails;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;

@Entity
@Table(name = "supplier")
public class Supplier extends PersistentObject{
	
	private String supplierName;
	private Address address;
	private CustomerBankAccountDetails supplierBankAccntDetails;
	private long custId;
	private String supplierStatus = "Y";
	private String email;
	private String mobileNumber;
	private String phoneNumber;
	
	@Column(nullable = false, length = 40)
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="addressId", insertable=true, updatable=true)
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bankId", insertable=true, updatable=true)
	public CustomerBankAccountDetails getSupplierBankAccntDetails() {
		return supplierBankAccntDetails;
	}
	public void setSupplierBankAccntDetails(
			CustomerBankAccountDetails supplierBankAccntDetails) {
		this.supplierBankAccntDetails = supplierBankAccntDetails;
	}
	@Column(nullable = false)
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(nullable = false, length = 1)
	public String getSupplierStatus() {
		return supplierStatus;
	}
	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
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
		return 0;
	}
	/**
	 * @return the email
	 */
	@Column(name="email", nullable = true, length = 40)
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobileNumber
	 */
	@Column(name ="mobileNumber",nullable = false, length = 20)
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the phoneNumber
	 */
	@Column(name = "phoneNumber" ,nullable = true, length = 20)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
