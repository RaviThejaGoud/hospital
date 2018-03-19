package com.urt.persistence.model.common;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;



/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewUserRoles.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0  July 14, 2010        Siva G           	Created
/********************************************************************/

@Entity
@Table(name = "vw_userRoles")
public class ViewUserRoles implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private ViewUserRolesPK id;

	@EmbeddedId
	    @AttributeOverrides( {
	        @AttributeOverride(name="username", column=@Column(name="username", nullable=false) ), 
	        @AttributeOverride(name="roleName", column=@Column(name="roleName") ), 
	        @AttributeOverride(name="roleDescription", column=@Column(name="roleDescription") ), 
	        @AttributeOverride(name="firstName", column=@Column(name="firstName") ), 
	        @AttributeOverride(name="lastName", column=@Column(name="lastName") ), 
	        @AttributeOverride(name="custId", column=@Column(name="custId") ), 
	        @AttributeOverride(name="accountId", column=@Column(name="accountId") ), 
	        @AttributeOverride(name="roleId", column=@Column(name="roleId") )} )

	  /**
	 * @return the id
	 */
	@Id
	public ViewUserRolesPK getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(ViewUserRolesPK id) {
		this.id = id;
	}

	@Transient
	public String getRoleDescription()
	{
		return id.getRoleDescription();
	}
	@Transient
	public String getRoleName()
	{
		return id.getRoleName();
	}
	
	@Transient
	public String getUsername()
	{
		return id.getUsername();
	}
	@Transient
	public Long getAcountId()
	{
		return id.getAccountId();
	}
	@Transient
	public Long getRoleId()
	{
		return id.getRoleId();
	}
	@Transient
	public String getFirstName()
	{
		return id.getFirstName();
	}
	@Transient
   public String getPersonFullName()
   {
       StringBuffer ret = new StringBuffer(10);
	       
           if (!StringFunctions.isNullOrEmpty(id.getFirstName()))
           {
               ret.append(id.getFirstName());
           }
           /*if (!StringFunctions.isNullOrEmpty(getFirstName()))
	       {
        	   ret.append(" ");
	           ret.append(getMiddleName());
	       }*/
           if (!StringFunctions.isNullOrEmpty(id.getLastName()))
           {
               ret.append(" ");
               ret.append(id.getLastName());
           }
      
       return ret.toString().trim();
   }
   @Transient
   public String getPersonNameWithRoleDesc()
   {
       StringBuffer ret = new StringBuffer(10);
       ret.append(getPersonFullName());
       if (!StringFunctions.isNullOrEmpty(id.getRoleDescription()))
       {
           ret.append(" (" + id.getRoleDescription() + ")");
       }
      
       return ret.toString().trim();
   }
   @Transient
	public String getRoleIdAndAccountId() {
		if(ObjectFunctions.isNullOrEmpty(id.getRoleId()))
			return "";
		else
			return id.getRoleId()+","+id.getAccountId();
   }
}