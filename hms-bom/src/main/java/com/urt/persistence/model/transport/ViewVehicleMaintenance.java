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
  * File: ViewVehicleMaintenance.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    July 01, 2013     Ram Prasad J        	Created
/********************************************************************/

@Entity
@Table(name = "vw_vehicleMaintenance")
public class ViewVehicleMaintenance  implements Serializable,Cloneable,Comparable{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     
     private String insuranceExpiredDate;
     private String insuranceNumber;
     private long routeId;
     private String status;
     private String vehicleNumber;
     private String vehicleType;
     private String roadTaxAmount;
     private String fitnessExpiryDate;
     private String permitExpiryDate;
     private String pollutionExpiryDate;
     private String roadTaxNextPaymentDate;
     private String name;
     private String routeName;
     private long repairsCost;
     private String  repairDescription;
     private long vehicleId;
     private long monthId;
     private String monthName;
     private long academicYearId;
     private long custId;
     private long maintenanceId;
     private long closingReading; 
     private long openingReading;
     private long oilBalance;
     private long oilConsumed;
     private long oilPurchased;
     private long totalKms ;
     private double  perLtrKms;
     private long prevClosingReading; 
     private long prevOpeningReading;
     private long prevOilBalance;
     private long prevOilConsumed;
     private long prevOilPurchased;
     private long prevTotalKms ;
     private double  prevPerLtrKms;
     private String insuranceDetails;
     private String vehicleMaker;
     private String registrationAuthority;
     private String customerName;
     private String monthDate; 
        
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
	  
		public String getInsuranceNumber() {
			return insuranceNumber;
		}
		public void setInsuranceNumber(String insuranceNumber) {
			this.insuranceNumber = insuranceNumber;
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
	 
		  
		public String getInsuranceExpiredDate() {
			return insuranceExpiredDate;
		}
		public void setInsuranceExpiredDate(String insuranceExpiredDate) {
			this.insuranceExpiredDate = insuranceExpiredDate;
		}
		public String getFitnessExpiryDate() {
			return fitnessExpiryDate;
		}
		public void setFitnessExpiryDate(String fitnessExpiryDate) {
			this.fitnessExpiryDate = fitnessExpiryDate;
		}
		public String getPermitExpiryDate() {
			return permitExpiryDate;
		}
		public void setPermitExpiryDate(String permitExpiryDate) {
			this.permitExpiryDate = permitExpiryDate;
		}
		public String getPollutionExpiryDate() {
			return pollutionExpiryDate;
		}
		public void setPollutionExpiryDate(String pollutionExpiryDate) {
			this.pollutionExpiryDate = pollutionExpiryDate;
		}
		public String getRoadTaxNextPaymentDate() {
			return roadTaxNextPaymentDate;
		}
		public void setRoadTaxNextPaymentDate(String roadTaxNextPaymentDate) {
			this.roadTaxNextPaymentDate = roadTaxNextPaymentDate;
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public long getClosingReading() {
			return closingReading;
		}
		public void setClosingReading(long closingReading) {
			this.closingReading = closingReading;
		}
		 
		public long getOpeningReading() {
			return openingReading;
		}
		public void setOpeningReading(long openingReading) {
			this.openingReading = openingReading;
		}
		public long getRepairsCost() {
			return repairsCost;
		}
		public void setRepairsCost(long repairsCost) {
			this.repairsCost = repairsCost;
		}
	 
		public long getMonthId() {
			return monthId;
		}
		public void setMonthId(long monthId) {
			this.monthId = monthId;
		}
		public String getMonthName() {
			return monthName;
		}
		public void setMonthName(String monthName) {
			this.monthName = monthName;
		}
		public long getOilBalance() {
			return oilBalance;
		}
		public void setOilBalance(long oilBalance) {
			this.oilBalance = oilBalance;
		}
		public long getOilConsumed() {
			return oilConsumed;
		}
		public void setOilConsumed(long oilConsumed) {
			this.oilConsumed = oilConsumed;
		}
		public long getOilPurchased() {
			return oilPurchased;
		}
		public void setOilPurchased(long oilPurchased) {
			this.oilPurchased = oilPurchased;
		}
		public String getRepairDescription() {
			return repairDescription;
		}
		public void setRepairDescription(String repairDescription) {
			this.repairDescription = repairDescription;
		}
		public long getTotalKms() {
			return totalKms;
		}
		public void setTotalKms(long totalKms) {
			this.totalKms = totalKms;
		}
		public double getPerLtrKms() {
			return perLtrKms;
		}
		public void setPerLtrKms(double perLtrKms) {
			this.perLtrKms = perLtrKms;
		}
		public long getCustId() {
			return custId;
		}
		public void setCustId(long custId) {
			this.custId = custId;
		}
		@Id
		public long getMaintenanceId() {
			return maintenanceId;
		}
		public void setMaintenanceId(long maintenanceId) {
			this.maintenanceId = maintenanceId;
		}
		public long getPrevClosingReading() {
			return prevClosingReading;
		}
		public void setPrevClosingReading(long prevClosingReading) {
			this.prevClosingReading = prevClosingReading;
		}
		public long getPrevOpeningReading() {
			return prevOpeningReading;
		}
		public void setPrevOpeningReading(long prevOpeningReading) {
			this.prevOpeningReading = prevOpeningReading;
		}
		public long getPrevOilBalance() {
			return prevOilBalance;
		}
		public void setPrevOilBalance(long prevOilBalance) {
			this.prevOilBalance = prevOilBalance;
		}
		public long getPrevOilConsumed() {
			return prevOilConsumed;
		}
		public void setPrevOilConsumed(long prevOilConsumed) {
			this.prevOilConsumed = prevOilConsumed;
		}
		public long getPrevOilPurchased() {
			return prevOilPurchased;
		}
		public void setPrevOilPurchased(long prevOilPurchased) {
			this.prevOilPurchased = prevOilPurchased;
		}
		public long getPrevTotalKms() {
			return prevTotalKms;
		}
		public void setPrevTotalKms(long prevTotalKms) {
			this.prevTotalKms = prevTotalKms;
		}
		public double getPrevPerLtrKms() {
			return prevPerLtrKms;
		}
		public void setPrevPerLtrKms(double prevPerLtrKms) {
			this.prevPerLtrKms = prevPerLtrKms;
		}
		public String getInsuranceDetails() {
			return insuranceDetails;
		}
		public void setInsuranceDetails(String insuranceDetails) {
			this.insuranceDetails = insuranceDetails;
		}
		public String getVehicleMaker() {
			return vehicleMaker;
		}
		public void setVehicleMaker(String vehicleMaker) {
			this.vehicleMaker = vehicleMaker;
		}
		public String getRegistrationAuthority() {
			return registrationAuthority;
		}
		public void setRegistrationAuthority(String registrationAuthority) {
			this.registrationAuthority = registrationAuthority;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getMonthDate() {
			return monthDate;
		}
		public void setMonthDate(String monthDate) {
			this.monthDate = monthDate;
		}
	
		
      
}