package com.urt.persistence.model.transport;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 05, 2010 Narahrai Created /
 ********************************************************************/

@Entity
@Table(name = "vw_vehicleWithDriverDetails")
public class ViewVehicleWithDriverDetails implements Serializable, Cloneable,
		Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long custId;
	private long routeId;
	private long vehicleId;
	private String vehicleType;
	private String vehicleNumber;
	private long noOfSeats;
	protected boolean status;
	//private long available;
	private long availablePickUp;
	private long availableDrop;
	protected String insuranceNumber;
	protected Date insuranceExpiredDate;
	// protected Set<VehicleTrip> vehicleTrip;
	protected String classificationType;
	protected String ownerName;
	// protected String registrationNumber;
	protected String chasisNumber;
	protected String engineNumber;
	protected long driverId;
	protected long helperId;
	protected String driverName;
	protected String mobileNumber;
    
	private long filledPickUp;
	private long filledDrop;
	//private long filled;
	private long totalStudent;
	protected long academicYearId;
	protected String name;

	protected String roadTaxAmount;
	protected Date roadTaxPaidDate;
	protected Date roadTaxNextPaymentDate;

	protected Date pollutionCheckedDate;
	protected Date pollutionExpiryDate;

	protected Date fitnessCheckDate;
	protected Date fitnessExpiryDate;

	protected Date permitCheckedDate;
	protected Date permitExpiryDate;

	protected String routeName;
	//protected String vehicleTiming;

	private long vehicleAcademicDetailId;
	private long accountId;
	private Date insurancePaidDate;
	private String vehicleMaker;
	private String registrationAuthority;
	private String insuranceDetails;
	private String routePointStartTime;  
	private String routePointEndTime;  
	private long routeStartTimeinMns;
	private long routeEndTimeinMns;
	private String academicYearStatus;
	private long vehicleAcademicId;
	
	
	
	public long getVehicleAcademicId() {
		return vehicleAcademicId;
	}

	public void setVehicleAcademicId(long vehicleAcademicId) {
		this.vehicleAcademicId = vehicleAcademicId;
	}

	/**
	 * @return the routePointStartTime
	 */
	public String getRoutePointStartTime() {
		return routePointStartTime;
	}

	/**
	 * @param routePointStartTime the routePointStartTime to set
	 */
	public void setRoutePointStartTime(String routePointStartTime) {
		this.routePointStartTime = routePointStartTime;
	}

	/**
	 * @return the routePointEndTime
	 */
	public String getRoutePointEndTime() {
		return routePointEndTime;
	}

	/**
	 * @param routePointEndTime the routePointEndTime to set
	 */
	public void setRoutePointEndTime(String routePointEndTime) {
		this.routePointEndTime = routePointEndTime;
	}

	/**
	 * @return the routeStartTimeinMns
	 */
	public long getRouteStartTimeinMns() {
		return routeStartTimeinMns;
	}

	/**
	 * @param routeStartTimeinMns the routeStartTimeinMns to set
	 */
	public void setRouteStartTimeinMns(long routeStartTimeinMns) {
		this.routeStartTimeinMns = routeStartTimeinMns;
	}

	/**
	 * @return the routeEndTimeinMns
	 */
	public long getRouteEndTimeinMns() {
		return routeEndTimeinMns;
	}

	/**
	 * @param routeEndTimeinMns the routeEndTimeinMns to set
	 */
	public void setRouteEndTimeinMns(long routeEndTimeinMns) {
		this.routeEndTimeinMns = routeEndTimeinMns;
	}

	/**
	 * @return the academicYearStatus
	 */
	public String getAcademicYearStatus() {
		return academicYearStatus;
	}

	/**
	 * @param academicYearStatus the academicYearStatus to set
	 */
	public void setAcademicYearStatus(String academicYearStatus) {
		this.academicYearStatus = academicYearStatus;
	}

	/**
	 * @return the insuranceDetails
	 */
	public String getInsuranceDetails() {
		return insuranceDetails;
	}

	/**
	 * @param insuranceDetails the insuranceDetails to set
	 */
	public void setInsuranceDetails(String insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

	/**
	 * @return the registrationAuthority
	 */
	public String getRegistrationAuthority() {
		return registrationAuthority;
	}

	/**
	 * @param registrationAuthority the registrationAuthority to set
	 */
	public void setRegistrationAuthority(String registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
	}

	/**
	 * @return the vehicleMaker
	 */
	public String getVehicleMaker() {
		return vehicleMaker;
	}

	/**
	 * @param vehicleMaker the vehicleMaker to set
	 */
	public void setVehicleMaker(String vehicleMaker) {
		this.vehicleMaker = vehicleMaker;
	}

	/**
	 * @return the insurancePaidDate
	 */
	public Date getInsurancePaidDate() {
		return insurancePaidDate;
	}

	/**
	 * @param insurancePaidDate the insurancePaidDate to set
	 */
	public void setInsurancePaidDate(Date insurancePaidDate) {
		this.insurancePaidDate = insurancePaidDate;
	}

	/**
	 * @return the vehicleAcademicDetailId
	 */
	@Id
	public long getVehicleAcademicDetailId() {
		return vehicleAcademicDetailId;
	}

	/**
	 * @param vehicleAcademicDetailId
	 *            the vehicleAcademicDetailId to set
	 */
	public void setVehicleAcademicDetailId(long vehicleAcademicDetailId) {
		this.vehicleAcademicDetailId = vehicleAcademicDetailId;
	}

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public long getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(long noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/*public long getAvailable() {
		return available;
	}

	public void setAvailable(long available) {
		this.available = available;
	}*/

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public Date getInsuranceExpiredDate() {
		return insuranceExpiredDate;
	}

	public void setInsuranceExpiredDate(Date insuranceExpiredDate) {
		this.insuranceExpiredDate = insuranceExpiredDate;
	}

	public String getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(String classificationType) {
		this.classificationType = classificationType;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getChasisNumber() {
		return chasisNumber;
	}

	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
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
	
	public long getAvailablePickUp() {
		return availablePickUp;
	}

	public void setAvailablePickUp(long availablePickUp) {
		this.availablePickUp = availablePickUp;
	}

	public long getAvailableDrop() {
		return availableDrop;
	}

	public void setAvailableDrop(long availableDrop) {
		this.availableDrop = availableDrop;
	}

	public long getFilledPickUp() {
		return filledPickUp;
	}

	public void setFilledPickUp(long filledPickUp) {
		this.filledPickUp = filledPickUp;
	}

	public long getFilledDrop() {
		return filledDrop;
	}

	public void setFilledDrop(long filledDrop) {
		this.filledDrop = filledDrop;
	}

	/*public long getFilled() {
		return filled;
	}

	public void setFilled(long filled) {
		this.filled = filled;
	}*/

	@Transient
	public long getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(long totalStudent) {
		this.totalStudent = totalStudent;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoadTaxAmount() {
		return roadTaxAmount;
	}

	public void setRoadTaxAmount(String roadTaxAmount) {
		this.roadTaxAmount = roadTaxAmount;
	}

	public Date getRoadTaxPaidDate() {
		return roadTaxPaidDate;
	}

	public void setRoadTaxPaidDate(Date roadTaxPaidDate) {
		this.roadTaxPaidDate = roadTaxPaidDate;
	}

	public Date getRoadTaxNextPaymentDate() {
		return roadTaxNextPaymentDate;
	}

	public void setRoadTaxNextPaymentDate(Date roadTaxNextPaymentDate) {
		this.roadTaxNextPaymentDate = roadTaxNextPaymentDate;
	}

	public Date getPollutionCheckedDate() {
		return pollutionCheckedDate;
	}

	public void setPollutionCheckedDate(Date pollutionCheckedDate) {
		this.pollutionCheckedDate = pollutionCheckedDate;
	}

	public Date getPollutionExpiryDate() {
		return pollutionExpiryDate;
	}

	public void setPollutionExpiryDate(Date pollutionExpiryDate) {
		this.pollutionExpiryDate = pollutionExpiryDate;
	}

	public Date getFitnessCheckDate() {
		return fitnessCheckDate;
	}

	public void setFitnessCheckDate(Date fitnessCheckDate) {
		this.fitnessCheckDate = fitnessCheckDate;
	}

	public Date getFitnessExpiryDate() {
		return fitnessExpiryDate;
	}

	public void setFitnessExpiryDate(Date fitnessExpiryDate) {
		this.fitnessExpiryDate = fitnessExpiryDate;
	}

	public Date getPermitCheckedDate() {
		return permitCheckedDate;
	}

	public void setPermitCheckedDate(Date permitCheckedDate) {
		this.permitCheckedDate = permitCheckedDate;
	}

	public Date getPermitExpiryDate() {
		return permitExpiryDate;
	}

	public void setPermitExpiryDate(Date permitExpiryDate) {
		this.permitExpiryDate = permitExpiryDate;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transient
	public String getVehicleTiming() {
		StringBuffer routeTiming = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(this.routePointStartTime) && StringFunctions.isNotNullOrEmpty(this.routePointEndTime))
			routeTiming.append(this.routePointStartTime).append(" - ").append(this.routePointEndTime);	
		else if(StringFunctions.isNotNullOrEmpty(this.routePointStartTime))
			routeTiming.append(this.routePointStartTime);
		else if(StringFunctions.isNotNullOrEmpty(this.routePointEndTime))
			routeTiming.append(this.routePointEndTime);
		return routeTiming.toString();
	}
	/*public void setVehicleTiming(String vehicleTiming) {
		this.vehicleTiming = vehicleTiming;
	}*/

}