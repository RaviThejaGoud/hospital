package com.urt.persistence.model.transport;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;


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
 *  1.0    Oct 05, 2010     Narahrai          	Created
/********************************************************************/

@Entity
@Table(name = "vw_vehicleAndDriverInfo")
public class ViewVehicleAndDriverInfo  implements Serializable,Cloneable,Comparable {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long custId;
     private long academicYearId;
     private long vehicleId;
     private long vehicleAcademicDetailId;
     private String vehicleName;
     private String vehicleNumber;
     private String driverName;
     private String mobileNumber;
     private long driverId;
     
	
	public ViewVehicleAndDriverInfo()
    {
        super();
    }
   
    /**
	 * @return the vehicleAcademicDetailId
	 */
    @Id
	public long getVehicleAcademicDetailId() {
		return vehicleAcademicDetailId;
	}
	/**
	 * @param vehicleAcademicDetailId the vehicleAcademicDetailId to set
	 */
	public void setVehicleAcademicDetailId(long vehicleAcademicDetailId) {
		this.vehicleAcademicDetailId = vehicleAcademicDetailId;
	}
	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}
	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}
	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        } else {
        	return false;
        }
        
	}
    @Override
	public int hashCode() {
        int result = 0;
        
        return result;
    }
   
    /**
     * @see java.lang.Object#toString()
     * 
     * Domestic Address Formatted as
     *  addressLine1; addressLine2; city, state   zipCode[-zipCodeSupplement]
     * 
     * Military Address Formatted as
     *  addressLine1; addressLine2; city postalCode;
     * @Override
     */
    
    @Override
	public String toString() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        buffer
            .append(" Driver Name: ")
            .append(getDriverName())
            .append(" Vehicle Name : ")
            .append(getVehicleName());
        return buffer.toString();
    }
    @Override
	public int compareTo(Object object) {
    	ViewVehicleAndDriverInfo myClass = (ViewVehicleAndDriverInfo) object;
        return new CompareToBuilder().append(this.driverName,
                        myClass.driverName).toComparison();
    }
   

   
    @Column(name = "custId", nullable = true, length = 10) 
    public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
   
	@Column(name = "driverId", nullable = true, length = 10)
	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the vehicleNumber
	 */
	@Column(name = "vehicleNumber", nullable = false, length = 20)
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	/**
	 * @return the vehicleId
	 */
	@Column(name = "vehicleId", nullable = true, length = 10) 
	public long getVehicleId() {
		return vehicleId;
	}
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@Column(name = "academicYearId", nullable = false, length = 10)
	public long getAcademicYearId() {
		return academicYearId;
	}


	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

}