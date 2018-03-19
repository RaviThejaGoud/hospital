package com.urt.persistence.model.customer;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;

@Entity
@Table(name = "organizationMembers")
public class OrganizationMembers  extends PersistentObject {

    private static final long serialVersionUID = -9190968485277417762L;
    
    private User organizationUsers;
    private Staff organizationStaff;
    private Role organizationRole;
    private Date periodStartDate;
    private Date periodEndDate;
    private String status;
	

    public Date getPeriodStartDate() {
		return periodStartDate;
	}

	public void setPeriodStartDate(Date periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	public Date getPeriodEndDate() {
		return periodEndDate;
	}

	public void setPeriodEndDate(Date periodEndDate) {
		this.periodEndDate = periodEndDate;
	}


	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="roleId", insertable=true, updatable=true) 
    public Role getOrganizationRole() {
		return organizationRole;
	}

	public void setOrganizationRole(Role organizationRole) {
		this.organizationRole = organizationRole;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="staffId", insertable=true, updatable=true) 
    public Staff getOrganizationStaff() {
		return organizationStaff;
	}

	public void setOrganizationStaff(Staff organizationStaff) {
		this.organizationStaff = organizationStaff;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="accountId", insertable=true, updatable=true) 
    public User getOrganizationUsers() {
		return organizationUsers;
	}

	public void setOrganizationUsers(User organizationUsers) {
		this.organizationUsers = organizationUsers;
	}

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	/** default constructor */
    public OrganizationMembers() {
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
       if(null == object || this.getClass() != object.getClass()) {
    		return false;
       } else {    		
	        OrganizationMembers rhs = (OrganizationMembers) object;
	        return new EqualsBuilder().append(this.id, rhs.id)
	                .append(this.organizationUsers.getId(), rhs.organizationUsers.getId()).isEquals();
       }
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                
                .toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
		return 0;
    }

}