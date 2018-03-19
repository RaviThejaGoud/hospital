package com.urt.persistence.model.account;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.customer.Customer;

@Entity
@Table(name = "finCustomerDetails")
public class FinancialCustomerDetails extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Customer customer;
	public Address address;
	
	public String customerName;
	public String contactName;
	public String mobileNumber;
	 
	@OneToOne
	@JoinColumn(name="custId", insertable=true, updatable=true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@OneToOne
	@JoinColumn(name="addressId", insertable=true, updatable=true)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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

}
