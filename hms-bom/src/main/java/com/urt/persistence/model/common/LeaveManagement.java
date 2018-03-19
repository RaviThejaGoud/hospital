package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.Role;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: LeaveManagement.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Nov 08, 2010     Narahari          	Created
/********************************************************************/


@Entity
@Table(name = "leaveManagement")
public class LeaveManagement extends PersistentObject implements GrantedAuthority {
    
    private static final long serialVersionUID = 3690197650654049848L;
    
    /*private String roleName;
    private String roleDescription;*/
    private long casualLeaves;
    private long sickLeaves;
    private long earnedLeaves;
    private Role role;
    protected long custId;
    protected AcademicYear academicYear;
    private String permanentOrContract;
    
    
    
    /**
	 * @return the role
	 */
    @OneToOne
    @JoinColumn(name="roleId")
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
     * @return the academicYear
     */
    @OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

    
    public LeaveManagement(long id) {
    	setId(id);
    }

	public LeaveManagement() {
    }
    /**
     * @see org.acegisecurity.GrantedAuthority#getAuthority()
     */
    @Transient
    public String getAuthority() {
    	if(ObjectFunctions.isNullOrEmpty(this.role))
    		return "";
    	else
    		return this.role.getName();
    }
    

	@Column(name = "casualLeaves", nullable = false,columnDefinition = "bigint(20) default 0" )
	public long getCasualLeaves() {
		return casualLeaves;
	}

	public void setCasualLeaves(long casualLeaves) {
		this.casualLeaves = casualLeaves;
	}

	@Column(name = "sickLeaves", nullable = false,columnDefinition = "bigint(20) default 0" )
	public long getSickLeaves() {
		return sickLeaves;
	}

	public void setSickLeaves(long sickLeaves) {
		this.sickLeaves = sickLeaves;
	}

    public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeaveManagement)) return false;

        final LeaveManagement leaveManagement = (LeaveManagement) o;
        if(!ObjectFunctions.isNullOrEmpty(this.role))
        	return !(this.role.getName() != null ? !this.role.getName().equals(leaveManagement.getRole().getName()) : leaveManagement.getRole().getName() != null);
        else
        	return false;

    }
    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(" Id: ")
                .append(getId())
                .append(" Version: ")
                
                .toString();
    }

    @Override
    public int compareTo(Object object) {
        LeaveManagement myClass = (LeaveManagement) object;
        if(!ObjectFunctions.isNullOrEmpty(this.role) && !ObjectFunctions.isNullOrEmpty(myClass.getRole()))
        return new CompareToBuilder().append(this.role.getDescription(), myClass.getRole().getDescription()).append(this.role.getName(), myClass.role.getName())
                .toComparison();
        else 
        	return 0;
    }
    public void copyFrom(LeaveManagement obj)
    {
		setCustId(obj.getCustId());
		setCasualLeaves(obj.getCasualLeaves());
		setRole(obj.getRole());
		setSickLeaves(obj.getSickLeaves());
		//setVersion(0);
		setCreatedDate(new Date());
		setLastAccessDate(new Date());
    }

	@Transient
	public String getRoleDescription(){
		if(ObjectFunctions.isNullOrEmpty(this.role))
			return "";
		else
			return this.role.getDescription();
	}
	@Column(name = "permanentOrContract", nullable = true, length = 1, columnDefinition = "char(1) default 'P'")
	public String getPermanentOrContract() {
		return permanentOrContract;
	}

	public void setPermanentOrContract(String permanentOrContract) {
		this.permanentOrContract = permanentOrContract;
	}
	@Column(name = "earnedLeaves", nullable = false,columnDefinition = "bigint(20) default 0" )
	public long getEarnedLeaves() {
		return earnedLeaves;
	}

	public void setEarnedLeaves(long earnedLeaves) {
		this.earnedLeaves = earnedLeaves;
	}
	
}
