package com.urt.persistence.model.transport;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hyniva.sms.ws.vo.ViewAssignedVehiclestoRoutesWithBoardingPointsVo;


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
@Table(name = "vw_assignedVehiclestoRoutesWithBoardingPoints")
public class ViewAssignedVehiclestoRoutesWithBoardingPoints  implements Serializable,Cloneable,Comparable{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;

     private long id;
     private long vehicleAcademicId;
     private long routeId;
     private String name;
     private long academicYearId;
     private long custId;
     private long driverId;
     private long helperId;
     private boolean status;
     private long vehicleId;
     private String routeName;
     private boolean routeStatus;
     private String boardingPointName;
     private long boardingPointId;
     private int noOfSeats;
     
     

     @Id
     public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	/**
	 * @return the boardingPointName
	 */
	public String getBoardingPointName() {
		return boardingPointName;
	}
	/**
	 * @param boardingPointName the boardingPointName to set
	 */
	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}
	/**
	 * @return the boardingPointId
	 */
	public long getBoardingPointId() {
		return boardingPointId;
	}
	/**
	 * @param boardingPointId the boardingPointId to set
	 */
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
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
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
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
	public ViewAssignedVehiclestoRoutesWithBoardingPointsVo copyFromEntityToVo(ViewAssignedVehiclestoRoutesWithBoardingPoints viewAssignedVehiclestoRoutesWithBoardingPoints)
	{
		ViewAssignedVehiclestoRoutesWithBoardingPointsVo viewAssignedVehiclestoRoutesWithBoardingPointsVo = new ViewAssignedVehiclestoRoutesWithBoardingPointsVo();
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.vehicleAcademicId = viewAssignedVehiclestoRoutesWithBoardingPoints.vehicleAcademicId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.routeId = viewAssignedVehiclestoRoutesWithBoardingPoints.routeId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.name = viewAssignedVehiclestoRoutesWithBoardingPoints.name;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.academicYearId = viewAssignedVehiclestoRoutesWithBoardingPoints.academicYearId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.custId = viewAssignedVehiclestoRoutesWithBoardingPoints.custId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.driverId = viewAssignedVehiclestoRoutesWithBoardingPoints.driverId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.helperId = viewAssignedVehiclestoRoutesWithBoardingPoints.helperId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.status = viewAssignedVehiclestoRoutesWithBoardingPoints.status;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.vehicleId = viewAssignedVehiclestoRoutesWithBoardingPoints.vehicleId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.routeName = viewAssignedVehiclestoRoutesWithBoardingPoints.routeName;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.routeStatus = viewAssignedVehiclestoRoutesWithBoardingPoints.routeStatus;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.boardingPointName = viewAssignedVehiclestoRoutesWithBoardingPoints.boardingPointName;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.boardingPointId = viewAssignedVehiclestoRoutesWithBoardingPoints.boardingPointId;
		viewAssignedVehiclestoRoutesWithBoardingPointsVo.noOfSeats = viewAssignedVehiclestoRoutesWithBoardingPoints.noOfSeats;
		
		return viewAssignedVehiclestoRoutesWithBoardingPointsVo;
	}
	
	@Transient
	public String getRouteBoardingPointVechileName(){
		return this.getBoardingPointName()+"-"+this.getRouteName()+"-"+this.getName();
	}
	@Transient
	public String getRouteBoardingPointVechileIds(){
		return this.getBoardingPointId()+"-"+this.getRouteId()+"-"+this.getVehicleId();
	}
	
}