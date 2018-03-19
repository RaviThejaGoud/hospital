package com.urt.persistence.model.transport;

import java.io.Serializable;

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
 *  1.0    Oct 05, 2010     Narahrai          	Created
/********************************************************************/

@Entity
@Table(name = "vw_assignedVehiclestoRoutes")
public class ViewAssignedVehiclestoRoutes  implements Serializable,Cloneable,Comparable{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;

     private long vehicleAcademicId;
     /**
	 * @return the noOfSeats
	 */
	public int getNoOfSeats() {
		return noOfSeats;
	}
	/**
	 * @param noOfSeats the noOfSeats to set
	 */
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	private long routeId;
     private String name;
     private long academicYearId;
     private long custId;
     private long driverId;
     private long helperId;
     private String status;
     private long vehicleId;
     private String routeName;
     private boolean routeStatus;
     private int noOfSeats;
     
     

     public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Id
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	@Id
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	@Id
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	public long getHelperId() {
		return helperId;
	}
	public void setHelperId(long helperId) {
		this.helperId = helperId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return the vehicleAcademicId
	 */
	public long getVehicleAcademicId() {
		return vehicleAcademicId;
	}
	/**
	 * @param vehicleAcademicId the vehicleAcademicId to set
	 */
	public void setVehicleAcademicId(long vehicleAcademicId) {
		this.vehicleAcademicId = vehicleAcademicId;
	}
	/**
	 * @return the routeStatus
	 */
	public boolean isRouteStatus() {
		return routeStatus;
	}
	/**
	 * @param routeStatus the routeStatus to set
	 */
	public void setRouteStatus(boolean routeStatus) {
		this.routeStatus = routeStatus;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
		
      
}