package com.urt.persistence.model.transport;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;


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
@Table(name = "vw_vehicleMaintenanceByMonth")
public class ViewVehicleMaintenanceByMonth  implements Serializable,Cloneable,Comparable{

     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private Date insurancePaidDate;
     private Date insuranceExpiredDate;
     private String insuranceNumber;
     private String status;
     private String vehicleNumber;
     private String vehicleType;
     private String roadTaxAmount;
     private Date fitnessExpiryDate;
     private Date permitExpiryDate;
     private Date pollutionExpiryDate;
     private Date roadTaxNextPaymentDate;
     private String name;
     private int closingReading; 
     private int openingReading;
     private int repairsCost;
     private long vehicleId;
     private int monthId;
     private String monthName;
     private int oilBalance;
     private int oilConsumed;
     private int oilPurchased;
     private String  repairDescription;
     private int totalKms ;
     private double  perLtrKms;
     private long academicYearId;
     private long custId;
     private long maintenanceId;
     private String insuranceDetails;
     private String vehicleMaker;
     private String registrationAuthority;
     private String customerName;
     private String monthDate;
     private int prevClosingReading;
	 private int prevOpeningReading;
	 private int prevTotalKms;
	 private int prevOilBalance;
	 private int prevOilConsumed;
	 private int prevOilPurchased;
	 private double prevPerLtrKms;
	 private String prevMonthName;
	 
     /**
	 * @return the prevClosingReading
	 */
	 @Transient
	public int getPrevClosingReading() {
		return prevClosingReading;
	}
	/**
	 * @param prevClosingReading the prevClosingReading to set
	 */
	public void setPrevClosingReading(int prevClosingReading) {
		this.prevClosingReading = prevClosingReading;
	}
	/**
	 * @return the prevOpeningReading
	 */
	@Transient
	public int getPrevOpeningReading() {
		return prevOpeningReading;
	}
	/**
	 * @param prevOpeningReading the prevOpeningReading to set
	 */
	public void setPrevOpeningReading(int prevOpeningReading) {
		this.prevOpeningReading = prevOpeningReading;
	}
	/**
	 * @return the prevTotalKms
	 */
	@Transient
	public int getPrevTotalKms() {
		return prevTotalKms;
	}
	/**
	 * @param prevTotalKms the prevTotalKms to set
	 */
	public void setPrevTotalKms(int prevTotalKms) {
		this.prevTotalKms = prevTotalKms;
	}
	/**
	 * @return the prevOilBalance
	 */
	@Transient
	public int getPrevOilBalance() {
		return prevOilBalance;
	}
	/**
	 * @param prevOilBalance the prevOilBalance to set
	 */
	public void setPrevOilBalance(int prevOilBalance) {
		this.prevOilBalance = prevOilBalance;
	}
	/**
	 * @return the prevOilConsumed
	 */
	@Transient
	public int getPrevOilConsumed() {
		return prevOilConsumed;
	}
	/**
	 * @param prevOilConsumed the prevOilConsumed to set
	 */
	public void setPrevOilConsumed(int prevOilConsumed) {
		this.prevOilConsumed = prevOilConsumed;
	}
	/**
	 * @return the prevOilPurchased
	 */
	@Transient
	public int getPrevOilPurchased() {
		return prevOilPurchased;
	}
	/**
	 * @param prevOilPurchased the prevOilPurchased to set
	 */
	public void setPrevOilPurchased(int prevOilPurchased) {
		this.prevOilPurchased = prevOilPurchased;
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
	 * @return the prevClosingReading
	 */
	/**
	 * @return the prevPerLtrKms
	 */
	@Transient
	public double getPrevPerLtrKms() {
		return prevPerLtrKms;
	}
	/**
	 * @param prevPerLtrKms the prevPerLtrKms to set
	 */
	public void setPrevPerLtrKms(double prevPerLtrKms) {
		this.prevPerLtrKms = prevPerLtrKms;
	}
	/**
	 * @return the monthDate
	 */
    @Transient
	public String getMonthDate() {
		return monthDate;
	}
	/**
	 * @param monthDate the monthDate to set
	 */
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}
	/**
	 * @return the customerName
	 */
     @Transient
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
		public Date getInsuranceExpiredDate() {
			return insuranceExpiredDate;
		}
		public void setInsuranceExpiredDate(Date insuranceExpiredDate) {
			this.insuranceExpiredDate = insuranceExpiredDate;
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
	 
		public Date getRoadTaxNextPaymentDate() {
			return roadTaxNextPaymentDate;
		}
		public void setRoadTaxNextPaymentDate(Date roadTaxNextPaymentDate) {
			this.roadTaxNextPaymentDate = roadTaxNextPaymentDate;
		}
		 
		public Date getPollutionExpiryDate() {
			return pollutionExpiryDate;
		}
		public void setPollutionExpiryDate(Date pollutionExpiryDate) {
			this.pollutionExpiryDate = pollutionExpiryDate;
		}
	 
		public Date getFitnessExpiryDate() {
			return fitnessExpiryDate;
		}
		public void setFitnessExpiryDate(Date fitnessExpiryDate) {
			this.fitnessExpiryDate = fitnessExpiryDate;
		}
		 
		public Date getPermitExpiryDate() {
			return permitExpiryDate;
		}
		public void setPermitExpiryDate(Date permitExpiryDate) {
			this.permitExpiryDate = permitExpiryDate;
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
		public String getMonthName() {
			return monthName;
		}
		public void setMonthName(String monthName) {
			this.monthName = monthName;
		}
		public int getOilBalance() {
			return oilBalance;
		}
		public String getRepairDescription() {
			return repairDescription;
		}
		public void setRepairDescription(String repairDescription) {
			this.repairDescription = repairDescription;
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
		/**
		 * @return the closingReading
		 */
		public int getClosingReading() {
			return closingReading;
		}
		/**
		 * @param closingReading the closingReading to set
		 */
		public void setClosingReading(int closingReading) {
			this.closingReading = closingReading;
		}
		/**
		 * @return the openingReading
		 */
		public int getOpeningReading() {
			return openingReading;
		}
		/**
		 * @param openingReading the openingReading to set
		 */
		public void setOpeningReading(int openingReading) {
			this.openingReading = openingReading;
		}
		/**
		 * @return the repairsCost
		 */
		public int getRepairsCost() {
			return repairsCost;
		}
		/**
		 * @param repairsCost the repairsCost to set
		 */
		public void setRepairsCost(int repairsCost) {
			this.repairsCost = repairsCost;
		}
		/**
		 * @return the monthId
		 */
		public int getMonthId() {
			return monthId;
		}
		/**
		 * @param monthId the monthId to set
		 */
		public void setMonthId(int monthId) {
			this.monthId = monthId;
		}
		/**
		 * @return the oilConsumed
		 */
		public int getOilConsumed() {
			return oilConsumed;
		}
		/**
		 * @param oilConsumed the oilConsumed to set
		 */
		public void setOilConsumed(int oilConsumed) {
			this.oilConsumed = oilConsumed;
		}
		/**
		 * @return the oilPurchased
		 */
		public int getOilPurchased() {
			return oilPurchased;
		}
		/**
		 * @param oilPurchased the oilPurchased to set
		 */
		public void setOilPurchased(int oilPurchased) {
			this.oilPurchased = oilPurchased;
		}
		/**
		 * @return the totalKms
		 */
		public int getTotalKms() {
			return totalKms;
		}
		/**
		 * @param totalKms the totalKms to set
		 */
		public void setTotalKms(int totalKms) {
			this.totalKms = totalKms;
		}
		/**
		 * @param oilBalance the oilBalance to set
		 */
		public void setOilBalance(int oilBalance) {
			this.oilBalance = oilBalance;
		}
		@Transient
		public String getRoadTaxNextPaymentDateStr(){
			if(ObjectFunctions.isNullOrEmpty(this.roadTaxNextPaymentDate))
				return "";
			else
				return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.roadTaxNextPaymentDate);
		}
		@Transient
		public String getInsuranceExpiredDateStr(){
			if(ObjectFunctions.isNullOrEmpty(this.insuranceExpiredDate))
				return "";
			else
				return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.insuranceExpiredDate);
		}
		
		@Transient
		public int getTotalBalanceInTank(){
			return (this.oilBalance + this.prevOilBalance);
		}
		@Transient
		public int getTotalKmsRunning(){
			return (this.totalKms + this.prevTotalKms);
		}
		@Transient
		public String getPrevMonthName() {
			return prevMonthName;
		}
		public void setPrevMonthName(String prevMonthName) {
			this.prevMonthName = prevMonthName;
		}
		@Transient
		public String getPriviousMonth(){
			if(StringFunctions.isNullOrEmpty(this.prevMonthName))
				return "";
			else
				return " ( " +this.prevMonthName + ")";
		}
		
		
}