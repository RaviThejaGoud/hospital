package com.urt.persistence.model.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.user.User;
/*
 * @create new table customer.
 */
@Entity
@Table(name = "masterCustomer")
public class MasterCustomer  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String organization;                  
    protected boolean status;
    protected String custEmail;
    protected String customerStatus;
    protected String firstName;
    protected String lastName;
    protected String contactNumber;
    protected String webSiteUrl;
    protected String mobileNumber;
    protected Set<Customer> customers;
    protected Address address;
    
    
    /**
     * @return the address
     */
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="addressId" ,insertable=true, updatable=true) 
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void addUserMultiBranchDetails(Customer customers) {
		if (ObjectFunctions.isNullOrEmpty(getCustomers())) {
			this.customers = new HashSet<Customer>();
		}
		getCustomers().add(customers);
	}
	
    @OneToMany(cascade=CascadeType.ALL)
 	@JoinColumn(name="masterCustId")
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public MasterCustomer() {
        
    }

    public MasterCustomer(long id) {
        setId(id);
    }
    
    @Column(name = "mobileNumber", nullable = true, length = 128)
    public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	

	/**
     * @return Returns boolean value.
     */
	@Column(name = "status", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type="yes_no")
	public boolean isStatus() {
		return status;
	}

	/**
	* @param status the status to set
	*/
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the custEmail.
	 */
	@Column(name = "custEmail", nullable = false, unique=true, length = 128)
	public String getCustEmail() {
		return custEmail;
	}
	
	/**
	* @param custEmail the custEmail to set
	*/
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	/**
	 * @return the firstName.
	 */
	@Column(name = "firstName", nullable = true, length = 128)
	public String getFirstName() {
		return firstName;
	}

	/**
	* @param firstName the firstName to set
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName.
	 */
	@Column(name = "lastName", nullable = true, length = 128)
	public String getLastName() {
		return lastName;
	}

	/**
	* @param lastName the lastName to set
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the websites.
	 */
	@Column(name = "contactNumber", nullable = true, length = 128)
		public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Column(name = "webSiteUrl", nullable = true, length = 128)
	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}
	
	@Override
	public int compareTo(Object object) {
        
		MasterCustomer target = (MasterCustomer) object;
        if (target.getCustEmail() != null && this.getCustEmail() != null)
        {
            if(this.getCustEmail().equalsIgnoreCase(target.getCustEmail()))
                return 0;
            else 
               return this.getCustEmail().compareToIgnoreCase(target.getCustEmail());
                 
        }
        return 0;
    }
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final MasterCustomer customer = (MasterCustomer) o;

        if (custEmail != null ? !custEmail.equals(customer.getCustEmail()) : customer.getCustEmail() != null) return false;

        return true;
    }

    public int hashCode() {
        return (custEmail != null ? custEmail.hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("organization", this.organization)
        .append("status", this.status)
        .append("custEmail",this.custEmail)
        .append("firstName",this.firstName)
        .append("lastName",this.lastName)
        .toString();
	}

	/**
	 * @return the organization
	 */
	@Column(name = "organization", nullable = true, length = 128)
	public String getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

   	/**
	 * @return the customerStatus
	 */
	@Column(name = "customerStatus", nullable = true, length = 5,columnDefinition="varchar(5) default 'A'")
	public String getCustomerStatus() {
		return customerStatus;
	}

	/**
	 * @param customerStatus the customerStatus to set
	 */
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

    @Transient
    public String getCustomerFullPersonName() {
    	StringBuffer sbf = new StringBuffer();
    	if(!StringFunctions.isNullOrEmpty(this.getFirstName()))
    	{
    		sbf.append(this.getFirstName());
    	}
    	if(!StringFunctions.isNullOrEmpty(this.getLastName()))
    	{
    		sbf.append(" "+this.getLastName());
    	}
    	return sbf.toString();
    }
}
