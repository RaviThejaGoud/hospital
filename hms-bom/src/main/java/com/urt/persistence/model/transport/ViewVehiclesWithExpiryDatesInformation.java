package com.urt.persistence.model.transport;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * July 8, 2013      Rama		        this view was written for expirydates information of vehicles.
 * 
/********************************************************************/
@Entity
@Table(name = "vw_vehiclesWithExpiryDatesInformation")
public class ViewVehiclesWithExpiryDatesInformation implements Cloneable, Comparable,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2865553867886127694L;
	private long custId;
	protected long academicYearId;
	protected Date insuranceExpiredDate;
	protected String insuranceNumber;
	//protected String status;
	protected String vehicleNumber;
	protected Date fitnessExpiryDate;
	protected int fitnessDays;
	protected Date permitExpiryDate;
	protected int permitDays;
	protected Date pollutionExpiryDate;
	protected int pollutionDays;
	protected Date roadTaxNextPaymentDate;
	protected int insuranceDays;
    protected int roadTaxDays;
    protected String name;
    private long vehicleAcademicDetailId;
    
    
    
    
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

	@Column(name = "name", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoadTaxDays() {
		return roadTaxDays;
	}
	public void setRoadTaxDays(int roadTaxDays) {
		this.roadTaxDays = roadTaxDays;
	}

	public int getInsuranceDays() {
		return insuranceDays;
	}

	public void setInsuranceDays(int insuranceDays) {
		this.insuranceDays = insuranceDays;
	}

	@Column(name = "insuranceExpiredDate", nullable = true) 
	public Date getInsuranceExpiredDate() {
		if(!ObjectFunctions.isNullOrEmpty(insuranceExpiredDate)){
			return DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,String.valueOf(insuranceExpiredDate));
		}
		return insuranceExpiredDate;
	}

	public void setInsuranceExpiredDate(Date insuranceExpiredDate) {
		this.insuranceExpiredDate = insuranceExpiredDate;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	/*@Column(name = "status", nullable = false, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/
	@Id
	@Column(name = "vehicleNumber",  length = 120)
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	
	@Column(name = "custId", nullable = false, length = 20) 
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(name = "academicYearId", nullable = false, length = 20)
	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	@Column(name = "fitnessExpiryDate", nullable = true) 
	public Date getFitnessExpiryDate() {
		if(!ObjectFunctions.isNullOrEmpty(fitnessExpiryDate)){
			return DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,String.valueOf(fitnessExpiryDate));
		}
		return fitnessExpiryDate;
	}

	public void setFitnessExpiryDate(Date fitnessExpiryDate) {
		this.fitnessExpiryDate = fitnessExpiryDate;
	}
		
	@Column(name = "permitExpiryDate", nullable = true) 
	public Date getPermitExpiryDate() {
		if(!ObjectFunctions.isNullOrEmpty(permitExpiryDate)){
			return DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,String.valueOf(permitExpiryDate));
		}
		return permitExpiryDate;
	}	
	public void setPermitExpiryDate(Date permitExpiryDate) {
		this.permitExpiryDate = permitExpiryDate;
	}
	
	@Column(name = "pollutionExpiryDate", nullable = true) 
	public Date getPollutionExpiryDate() {
		if(!ObjectFunctions.isNullOrEmpty(pollutionExpiryDate)){
			return DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,String.valueOf(pollutionExpiryDate));
		}
		return pollutionExpiryDate;
	}

	public void setPollutionExpiryDate(Date pollutionExpiryDate) {
		this.pollutionExpiryDate = pollutionExpiryDate;
	}
	
	@Column(name = "roadTaxNextPaymentDate", nullable = true) 
	public Date getRoadTaxNextPaymentDate() {
		if(!ObjectFunctions.isNullOrEmpty(roadTaxNextPaymentDate)){
			return DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,String.valueOf(roadTaxNextPaymentDate));
		}
		return roadTaxNextPaymentDate;
	}

	public void setRoadTaxNextPaymentDate(Date roadTaxNextPaymentDate) {
		this.roadTaxNextPaymentDate = roadTaxNextPaymentDate;
	}

	public int getFitnessDays() {
		return fitnessDays;
	}

	public void setFitnessDays(int fitnessDays) {
		this.fitnessDays = fitnessDays;
	}

	public int getPermitDays() {
		return permitDays;
	}

	public void setPermitDays(int permitDays) {
		this.permitDays = permitDays;
	}

	public int getPollutionDays() {
		return pollutionDays;
	}

	public void setPollutionDays(int pollutionDays) {
		this.pollutionDays = pollutionDays;
	}

	@Override
	public int hashCode() {
		int result = 0;

		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewVehiclesWithExpiryDatesInformation))
			return false;
		return false;
	}

	@Override
	public String toString() {

		return "";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}	
	

}