package com.urt.persistence.model.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.user.User;
/*
 * @create new table customer.
 */
@Entity
@Table(name = "organization")
public class Organization  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String organizationName;                  
    protected String status;
    protected String subscriptionType;
    protected String orgEmail;
    protected String contactNumber;
    protected String webSiteUrl;
    protected String mobileNumber;
    //protected String accountType;
    protected Address address;
    protected List<OrganizationMembers> organizationMembers;
	
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="orgId")
	public List<OrganizationMembers> getOrganizationMembers() {
    	if(ObjectFunctions.isNullOrEmpty(this.organizationMembers))
		{
	    	 this.organizationMembers=new ArrayList<OrganizationMembers>();
	    }
		return organizationMembers;
	}

	public void setOrganizationMembers(List<OrganizationMembers> organizationMembers) {
		this.organizationMembers = organizationMembers;
	}
	
    
    public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="addressId" ,insertable=true, updatable=true) 
    @javax.xml.bind.annotation.XmlTransient()
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    

	public Organization() {
        
    }

    public Organization(long id) {
        setId(id);
    }
    
    

    @Column(name = "status", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
        
		Organization target = (Organization) object;
        if (target.getOrganizationName() != null && this.getOrganizationName() != null)
        {
            if(this.getOrganizationName().equalsIgnoreCase(target.getOrganizationName()))
                return 0;
            else 
               return this.getOrganizationName().compareToIgnoreCase(target.getOrganizationName());
                 
        }
        return 0;
    }
	public boolean equals(Object o) {		
        if (this == o) {
        	return true;
        } else if(null == o || this.getClass() != o.getClass()) {
        	return false;
        } else {
        	final Organization organization = (Organization) o;
        	if (!this.getOrganizationName().equals(organization.getOrganizationName())) 
        	return false;
        } 
        return true;
    }

    public int hashCode() {
        return (getOrganizationName() != null ? getOrganizationName().hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("organizationName", this.organizationName)
        .append("status", this.status)
        .toString();
	}

	
	@Transient
	public String getCustomerFormattedAddress() {
		if(ObjectFunctions.isNullOrEmpty(getAddress()))
			return "";
		else
			return getAddress().getStreetName()+", "+getAddress().getAddressLine1()+", "+getAddress().getCity()+" - "+getAddress().getPostalCode();
    }
	@Transient
    public String getOrganizationFullAddress() {
		if(ObjectFunctions.isNullOrEmpty(getAddress()))
			return "";
		else
		{
			 StringBuffer ret = new StringBuffer(10);
			if(StringFunctions.isNotNullOrEmpty(getAddress().getAddressLine1()))
			{
				ret.append(getAddress().getAddressLine1());
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getStreetName()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getStreetName());
					
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getCity()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getCity());
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getState()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getState());
			}
			if(StringFunctions.isNotNullOrEmpty(getAddress().getPostalCode()))
			{
				if(ret.length() > 0)
					ret.append(", ");
				ret.append(getAddress().getPostalCode());
			}
			return ret.toString();
		}
    }
	@Transient
	public void addOrganizationMembers(OrganizationMembers organizationMembers) {
		if(ObjectFunctions.isNullOrEmpty(organizationMembers)){
			organizationMembers=new OrganizationMembers();
		}
		getOrganizationMembers().add(organizationMembers);
	}
	
}
