package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "androidMobileUsers")
public class AndroidMobileUsers  extends PersistentObject {

    private static final long serialVersionUID = -9190968485277417762L;
    private long accountId;
    private long custId;
    private String registrationKey;
    
  
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(name = "registrationKey", length = 550)
    public String getRegistrationKey() {
		return registrationKey;
	}

	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}
	

	/** default constructor */
    public AndroidMobileUsers() {
    }
    
    /** full constructor */


	/**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-465592447, 1546771509).append(
                this.id).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
    	
    	if(null == object || this.getClass() != object.getClass())
			return false;
    	else{
	        AndroidMobileUsers rhs = (AndroidMobileUsers) object;
	        return new EqualsBuilder().append(this.id, rhs.id)
	                .append(this.registrationKey, rhs.registrationKey).isEquals();
    	}
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                //.append("registrationKey",
                .append("registrationKey",
                        this.registrationKey)
                .toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
		return 0;
    }

}