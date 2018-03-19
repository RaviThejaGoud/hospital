package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewGroupType.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0  July 14, 2010        Siva G           	Created
/********************************************************************/

@Entity
@Table(name = "vw_classFeeTypes")
public class ViewClassFeeType implements Serializable,Cloneable,Comparable{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long custId;
     private long classId;
     private long feeTypeId;
     private long academicYearId;
     private String className;
     private String feeType;
     private long feeSettingId;
     
     
   

	public ViewClassFeeType()
    {
        super();
    }
  
	
   	public long getFeeSettingId() {
		return feeSettingId;
	}


	public void setFeeSettingId(long feeSettingId) {
		this.feeSettingId = feeSettingId;
	}


	/**
	 * @return the custId
	 */
	 @Column(name = "custId", nullable = true, length = 10)
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the className
	 */
	 @Column(name = "className", nullable = true, length = 250)
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	
	/**
	 * @return the classId
	 */
	public long getClassId() {
		return classId;
	}



	/**
	 * @param classId the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}
	
	
	/**
	 * @return the feeTypeId
	 */
	@Id
	@Column( name="feeTypeId", unique=false, nullable=false, updatable=false )
	public long getFeeTypeId() {
		return feeTypeId;
	}


	/**
	 * @param feeTypeId the feeTypeId to set
	 */
	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}


	/**
	 * @return the academicYearId
	 */
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
	 * @return the feeType
	 */
	public String getFeeType() {
		return feeType;
	}


	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}


	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	 
}