package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "contactUs")
public class ContactUs  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String customerName;                  
    protected String customerEmail;
    protected String phoneNumber;
    protected String comments;
    
    @Column(name = "phoneNumber", length = 20)
    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ContactUs() {
        
    }

    public ContactUs(long id) {
        setId(id);
    }

    /**
	 * @return the customer name.
	 */
    @Column(name = "customerName", length = 128)
    public String getCustomerName() {
		return customerName;
	}

    /**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerEmail
	 */
	 @Column(name = "customerEmail", length = 128)
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the comments
	 */
	@Column(name = "comments", nullable = true, length = 10254)
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * @return the websites.
	 */
	

	@Override
	public int compareTo(Object object) {
        
		ContactUs target = (ContactUs) object;
        if (target.getCustomerEmail() != null && this.getCustomerEmail() != null)
        {
            if(this.getCustomerEmail().equalsIgnoreCase(target.getCustomerEmail()))
                return 0;
            else 
               return this.getCustomerEmail().compareToIgnoreCase(target.getCustomerEmail());
                 
        }
        return 0;
    }

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final ContactUs customer = (ContactUs) o;

        if (customerEmail != null ? !customerEmail.equals(customer.getCustomerEmail()) : customer.getCustomerEmail() != null) return false;

        return true;
    }

    public int hashCode() {
        return (customerEmail != null ? customerEmail.hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("customerName", this.customerName)        
        .toString();
	}

   	 

	
}
    

  

