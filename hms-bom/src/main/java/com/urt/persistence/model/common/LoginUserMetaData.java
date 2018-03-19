/********************************************************************
* HYNIVA
* All Rights Reserved 
*
* File: LoginUserMetaData.java
********************************************************************
*
*  Ver   Date            Author                Description
*  ====  ========        ============          ==================
*  0.1   Sep 10, 2015    Sreeram			   Created
/********************************************************************/

package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "loginUserMetaData")
public class LoginUserMetaData extends PersistentObject  {
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private long custId;
	private long academicYearId;
	private int loginCount;
	private String ipAddress;
	
	
	/**
	 * @return the ipAddress
	 */
	@Column(name = "ipAddress", columnDefinition="varchar(20)")
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the loginCount
	 */
	@Column(name = "loginCount", columnDefinition=" int default 0")
	public int getLoginCount() {
		return loginCount;
	}

	/**
	 * @param loginCount the loginCount to set
	 */
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	/**
	 * @return the academicYearId
	 */
	@Column(name = "academicYearId", columnDefinition="bigint(20) default 0")
	public long getAcademicYearId() {
		return academicYearId;
	}

	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	/**
	 * @return the userId
	 */
	@Column(name = "userId", columnDefinition="bigint(20) default 0")
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the custId
	 */
	@Column(name = "custId", columnDefinition="bigint(20) default 0")
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
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
