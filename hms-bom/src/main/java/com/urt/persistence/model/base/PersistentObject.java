package com.urt.persistence.model.base;
/********************************************************************
 * Copyright (C) 2010-11

 * UpperRoom Technologies
 * All Rights Reserved 
 *
 * File: PersistentObject.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0  July 1, 2010           Sreeram J           Created
/ ********************************************************************/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

import com.churchgroup.util.date.DateFunctions;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
public abstract class PersistentObject implements Serializable,Cloneable,Comparable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4102953039713167523L;
	
	protected final Log log = LogFactory.getLog(getClass());
	protected long id;	
	//private int version;
	//private String createdBy;
	//private String lastUpdatedBy;
	protected Date lastAccessDate;
	protected Date lastUpdatedDate;
    protected Date createdDate;
    private long createdById;
	private long lastUpdatedById;
	
	/**
	 * @see java.lang.Object#Object()
	 */
	public PersistentObject() {
		 this.createdDate=DateFunctions.getTodayPlusNdays(0);
	     this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
	}	
	
	/**
	 * @return the createdById
	 */
	@Column(name = "createdById",nullable = false, columnDefinition=" int default 0")
	@JSON(serialize=false)
	public long getCreatedById() {
		return createdById;
	}

	/**
	 * @param createdById the createdById to set
	 */
	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}

	/**
	 * @return the lastUpdatedById
	 */
	@Column(name = "lastUpdatedById",nullable = false, columnDefinition=" int default 0")
	@JSON(serialize=false)
	public long getLastUpdatedById() {
		return lastUpdatedById;
	}

	/**
	 * @param lastUpdatedById the lastUpdatedById to set
	 */
	public void setLastUpdatedById(long lastUpdatedById) {
		this.lastUpdatedById = lastUpdatedById;
	}

	/**
	 * Constructor PersistentObject.
	 * @param id
	 */
	public PersistentObject(Long id) {
		this.id = id;
	}
	
    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract int hashCode();
    public abstract int compareTo(Object object) ;
	
	/**
	 * Returns the version.
	 * @return int
	 */
    //@Transient
    /*@Version
    @Column(name = "version", nullable = true)
    @JSON(serialize=false)
	public int getVersion() {
		return version;
	}*/

	/**
	 * Sets the version.
	 * @param version The version to set
	 */
	/*public void setVersion(int version) {
		this.version = version;
	}*/

	/**
	 * Returns the id.
	 * @return Long
	 */
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	
	@Transient
	@JSON(serialize=false)
	public String getStrId() {
	    return String.valueOf(this.getId());
    }
    
	/**
	 * Sets the id.
	 * @param id The id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the createdBy
	 */
	/*@JSON(serialize=false)
	public String getCreatedBy() {
		return createdBy;
	}*/

	/**
	 * @param createdBy the createdBy to set
	 */
/*	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
*/
	/**
	 * @return the lastUpdatedBy
	 */
	/*@JSON(serialize=false)
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}*/

	/**
	 * @param lastUpdatedBy the lastUpdatedBy to set
	 */
/*	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}*/

	/**
	 * @return the lastAccessDate
	 */
	@JSON(serialize=false)
	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	/**
	 * @param lastAccessDate the lastAccessDate to set
	 */
	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	/**
	 * @return the createdDate
	 */
	@JSON(serialize=false)
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	@JSON(serialize=false)
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
}
