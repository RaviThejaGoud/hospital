package com.urt.persistence.model.transport;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;


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
@Table(name = "vw_studentsTransportDetails")
public class ViewStudentsTransportDetails  implements Serializable,Cloneable,Comparable{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;

    //private long vehicleAcademicId;
     private long routeId;
     private String name;
     private long academicYearId;
     private long custId;
     private long driverId;
     private long helperId;
     private boolean activeStatus;
     private long vehicleId;
     private String routeName;
     private boolean routeStatus;
     private String boardingPointName;
   //private long boardingPointId;
     private String fullName;
     private long accountId;
     private long studId;
     private long classSectionId;
     private String status;
     private String rollNumber;
     private String admissionNumber;
     private String registerNumber;
     private String studDiscontinueDesc;
     private String mobileNumber;
     private String parentEmail;
     private int noOfSeats;
     private String className;
     private String section;
     private int classSortingOrder;
     private long pickupVehicleId;
     private long dropVehicleId;
     private long pickupBoardingPointId;
     private long dropBoardingPointId;

     
     
     /**
	 * @return the className
	 */
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
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the classSortingOrder
	 */
	public int getClassSortingOrder() {
		return classSortingOrder;
	}
	/**
	 * @param classSortingOrder the classSortingOrder to set
	 */
	public void setClassSortingOrder(int classSortingOrder) {
		this.classSortingOrder = classSortingOrder;
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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the studId
	 */
	@Id
	public long getStudId() {
		return studId;
	}
	/**
	 * @param studId the studId to set
	 */
	public void setStudId(long studId) {
		this.studId = studId;
	}
	/**
	 * @return the classSectionId
	 */
	public long getClassSectionId() {
		return classSectionId;
	}
	/**
	 * @param classSectionId the classSectionId to set
	 */
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
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
	/**
	 * @return the rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}
	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	/**
	 * @return the admissionNumber
	 */
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	/**
	 * @param admissionNumber the admissionNumber to set
	 */
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	/**
	 * @return the registerNumber
	 */
	public String getRegisterNumber() {
		return registerNumber;
	}
	/**
	 * @param registerNumber the registerNumber to set
	 */
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	/**
	 * @return the studDiscontinueDesc
	 */
	public String getStudDiscontinueDesc() {
		return studDiscontinueDesc;
	}
	/**
	 * @param studDiscontinueDesc the studDiscontinueDesc to set
	 */
	public void setStudDiscontinueDesc(String studDiscontinueDesc) {
		this.studDiscontinueDesc = studDiscontinueDesc;
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
	/**
	 * @return the parentEmail
	 */
	public String getParentEmail() {
		return parentEmail;
	}
	/**
	 * @param parentEmail the parentEmail to set
	 */
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
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
	/*public long getBoardingPointId() {
		return boardingPointId;
	}
	*//**
	 * @param boardingPointId the boardingPointId to set
	 *//*
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}*/
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
	/*public long getVehicleAcademicId() {
		return vehicleAcademicId;
	}
	*//**
	 * @param vehicleAcademicId the vehicleAcademicId to set
	 *//*
	public void setVehicleAcademicId(long vehicleAcademicId) {
		this.vehicleAcademicId = vehicleAcademicId;
	}
*/
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
	 * @return the activeStatus
	 */
	public boolean isActiveStatus() {
		return activeStatus;
	}
	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
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
	@Transient
	public String getClassAndSection(){
		StringBuffer classSection = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(this.className) && StringFunctions.isNotNullOrEmpty(this.section))
			classSection.append(this.className).append(" - ").append(this.section);
		else if(StringFunctions.isNotNullOrEmpty(this.className))
			classSection.append(this.className);
		else if(StringFunctions.isNotNullOrEmpty(this.section))
			classSection.append(this.section);
		return classSection.toString();
	}
	public long getPickupVehicleId() {
		return pickupVehicleId;
	}
	public void setPickupVehicleId(long pickupVehicleId) {
		this.pickupVehicleId = pickupVehicleId;
	}
	public long getDropVehicleId() {
		return dropVehicleId;
	}
	public void setDropVehicleId(long dropVehicleId) {
		this.dropVehicleId = dropVehicleId;
	}
	public long getPickupBoardingPointId() {
		return pickupBoardingPointId;
	}
	public void setPickupBoardingPointId(long pickupBoardingPointId) {
		this.pickupBoardingPointId = pickupBoardingPointId;
	}
	public long getDropBoardingPointId() {
		return dropBoardingPointId;
	}
	public void setDropBoardingPointId(long dropBoardingPointId) {
		this.dropBoardingPointId = dropBoardingPointId;
	}
      
	
}