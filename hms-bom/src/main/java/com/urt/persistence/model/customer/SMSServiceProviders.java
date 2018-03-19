package com.urt.persistence.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "smsServiceProviders")
public class SMSServiceProviders extends PersistentObject {

    private static final long serialVersionUID = 3832626162173359411L;
    protected String url;
    protected String serviceProvider;
    private boolean activeUrl;
    
    protected String userName;
    protected String password;
    protected String providerKey;
    protected String name;
    protected String isCustomerSpecific;
    
    
    
    
    
    
    @Column(name = "isCustomerSpecific", nullable = true, length = 1, columnDefinition = "char(1) default 'N'")
    public String getIsCustomerSpecific() {
		return isCustomerSpecific;
	}

	public void setIsCustomerSpecific(String isCustomerSpecific) {
		this.isCustomerSpecific = isCustomerSpecific;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public SMSServiceProviders(long id) {
	setId(id);
    }

    public SMSServiceProviders() {
	this.createdDate = DateFunctions.getTodayPlusNdays(0);
	this.lastUpdatedDate = DateFunctions.getTodayPlusNdays(0);
	this.lastAccessDate = DateFunctions.getTodayPlusNdays(0);
    }

    /** @return the url */
    @Column(name = "url", nullable = true, length = 1024)
    public String getUrl() {
	return url;
    }

    /** @param url
     *            the url to set */
    public void setUrl(String url) {
	this.url = url;
    }

    /** @return the serviceProvider */
    @Column(name = "serviceProvider", nullable = true, length = 125)
    public String getServiceProvider() {
	return serviceProvider;
    }

    /** @param serviceProvider
     *            the serviceProvider to set */
    public void setServiceProvider(String serviceProvider) {
	this.serviceProvider = serviceProvider;
    }

    /** @return the activeUrl */
    @Column(name = "activeUrl", nullable = true, length = 1, columnDefinition = "char(1) default 'Y'")
    @Type(type = "yes_no")
    public boolean isActiveUrl() {
	return activeUrl;
    }

    /** @param activeUrl
     *            the activeUrl to set */
    public void setActiveUrl(boolean activeUrl) {
	this.activeUrl = activeUrl;
    }

    @Override
    public int compareTo(Object object) {
	return 0;
    }

    public boolean equals(Object o) {
	return false;
    }

    public int hashCode() {
	return (getStrId() != null ? this.getStrId().hashCode() : 0);
    }

    @Override
    public String toString() {
	return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.getId())
		.append("Provider", this.serviceProvider).toString();
    }

}
