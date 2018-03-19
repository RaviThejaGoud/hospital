package com.urt.persistence.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table loginAccessbilityRoles.
 */
@Entity
@Table(name = "loginAccessbilityRoles")
public class LoginAccessbilityRoles extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long  customerId;
	protected Long roleId;
	protected String status;
	protected String androidStatus;
	
	
	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the status
	 */
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "androidStatus", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getAndroidStatus() {
		return androidStatus;
	}
	public void setAndroidStatus(String androidStatus) {
		this.androidStatus = androidStatus;
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
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
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

}
