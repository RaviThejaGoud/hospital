package com.urt.persistence.model.transport;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;


@Entity
@Table(name = "transportMaintenance")
public class TransportMaintenance extends PersistentObject  {
	
	 private static final long serialVersionUID = 3832626162173359411L;
	 private static final int STRING_BUFFER_SIZE = 1024;
	 
	 private long  custId;
	 protected AcademicYear academicYear;
	 private double openingReading;
	 private double closingReading;
	 private double totalKms;
	 private String monthName;
	 private int monthId;
	 private double oilBalance;
	 private double oilPurchased;
	 private double oilConsumed;
	 private double oilCost;

	 private double repairsCost;
	 private String repairDescription;

	 private long  vehicleId;
	 private long vehicleAcademicDetailId;
	 
	 

	public TransportMaintenance() {
		this.createdDate = new Date();
		this.lastAccessDate = new Date();
		this.lastUpdatedDate = new Date();
	}

	/**
	 * @return the vehicleAcademicDetailId
	 */
	public long getVehicleAcademicDetailId() {
		return vehicleAcademicDetailId;
	}

	/**
	 * @param vehicleAcademicDetailId the vehicleAcademicDetailId to set
	 */
	public void setVehicleAcademicDetailId(long vehicleAcademicDetailId) {
		this.vehicleAcademicDetailId = vehicleAcademicDetailId;
	}

	public long getVehicleId() {
			return vehicleId;
		}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	 /**
		 * @return the custId
		 */
	@Column(name = "custId", nullable = true, length = 10) 
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	/**
     * @return the academicYear
     */
    @OneToOne
    @JoinColumn(name="academicYearId")
	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	 /**
	 * @return the openingReading
	 */
	@Column(name = "openingReading", nullable = true, columnDefinition=" int default 0") 
	public double getOpeningReading() {
		return openingReading;
	}

	public void setOpeningReading(double openingReading) {
		this.openingReading = openingReading;
	}

	 /**
	 * @return the openingReading
	 */
	@Column(name = "closingReading", nullable = true,columnDefinition=" int default 0") 
	public double getClosingReading() {
		return closingReading;
	}

	public void setClosingReading(double closingReading) {
		this.closingReading = closingReading;
	}
   

	/**
	 * @return the openingReading
	 */
	@Column(name = "repairsCost", nullable = true,columnDefinition=" int default 0") 
	public double getRepairsCost() {
		return repairsCost;
	}

	public void setRepairsCost(double repairsCost) {
		this.repairsCost = repairsCost;
	}
  
	@Column(name = "oilCost", nullable = true,columnDefinition=" int default 0") 
	public double getOilCost() {
		return oilCost;
	}

	public void setOilCost(double oilCost) {
		this.oilCost = oilCost;
	}
  
	
	/**
	 * @return the oilCost
	 *//*
	public double getOilCost() {
		return oilCost;
	}

	*//**
	 * @param oilCost the oilCost to set
	 *//*
	public void setOilCost(double oilCost) {
		this.oilCost = oilCost;
	}*/

	@Column(name = "totalKms", nullable = true,columnDefinition=" int default 0") 
	public double getTotalKms() {
		return totalKms;
	}

	public void setTotalKms(double totalKms) {
		this.totalKms = totalKms;
	}
	@Column(name = "monthName", nullable = true, length = 10,unique=false) 
	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	@Column(name = "monthId", nullable = true, columnDefinition=" int default 0") 
	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	@Column(name = "oilBalance", nullable = true, columnDefinition=" int default 0") 
	public double getOilBalance() {
		return oilBalance;
	}

	public void setOilBalance(double oilBalance) {
		this.oilBalance = oilBalance;
	}
	
	@Column(name = "oilPurchased", nullable = true, columnDefinition=" int default 0")
	public double getOilPurchased() {
		return oilPurchased;
	}

	public void setOilPurchased(double oilPurchased) {
		this.oilPurchased = oilPurchased;
	}

	@Column(name = "oilConsumed", nullable = true, columnDefinition=" int default 0")
	public double getOilConsumed() {
		return oilConsumed;
	}

	public void setOilConsumed(double oilConsumed) {
		this.oilConsumed = oilConsumed;
	}

	@Column(name = "repairDescription", nullable = true,  length = 1024)
	public String getRepairDescription() {
		return repairDescription;
	}

	public void setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
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
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
		buffer.append("Id : ").append(getId()).append(", vehicleAcademicDetailId : ");
		buffer.append(vehicleAcademicDetailId).append(", monthName: ");
		buffer.append(monthName);
		return buffer.toString();
	} 
}
