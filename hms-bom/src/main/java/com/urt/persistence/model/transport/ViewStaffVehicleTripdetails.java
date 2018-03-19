package com.urt.persistence.model.transport;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;

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
@Table(name = "vw_staffVehicleTripDetails")
public class ViewStaffVehicleTripdetails  implements Serializable,Cloneable,Comparable {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long custId;
     protected long academicYearId;
     protected String routePointName;
     protected String routeEndName;
     protected String routeName;   
     protected String routePointStartTime;  
     protected String routePointEndTime;
    
     
     protected String boardingPointName;
     protected String boardingPointStatTime;
     protected String boardingPointEndTime;
     
     protected String vehicleType;  
     protected String vehicleNumber;
     protected long noOfSeats;
     protected String insuranceNumber;
     protected Date insuranceExpiredDate;     
     
 
     protected long vehicleId;
     protected long routeId;
     protected long driverId;
     protected long helperId;
     
     private String driverUsername;
     private String helperUsername;
     private String driverQualification1;
     private String helperQualification1;
     private long driverStaffId;
     private long helperStaffId;
     private String driverFirstName;
     private String helperFirstName;
     private String driverLastName;
     private String helperLastName;
     private String driverMiddleName;
     private String helperMiddleName;
     protected String driverAddressLine1;
     protected String helperAddressLine1;
     protected String driverAddressLine2;
     protected String helperAddressLine2;
     protected String driverCity;
     protected String helperCity;
     protected String driverState;
     protected String helperState;
     protected String driverPostalCode;
     protected String helperPostalCode;
     protected String driverMobileNumber;
     protected String helperMobileNumber;
     protected String driverPhoneNumber;
     protected String helperPhoneNumber;
     protected String driverBloodGroup;
     protected String helperBloodGroup;
     protected String driverDateOfBirth;
     protected String helperDateOfBirth;
     protected String driverDateOfJoining;
     protected String helperDateOfJoining;
     private String driverRoleName;
     private String helperRoleName;
     private long driverRoleId;
     private long helperRoleId;
     private String driverLicenseNumber;
     private String helperLicenseNumber;
     private String diverLicenseExpDate; 
     private String helperLicenseExpDate;
     private long availableNoOfSeats;
     private long enRolledNoOfSeat;
     private long totalNoOfSeat;
     private long boardingPointId;
     private long studentsCount;
	
 	@Column(name = "driverUsername", nullable = true, length = 128)
	public String getDriverUsername() {
		return driverUsername;
	}
 	@Id
	public long getBoardingPointId() {
		return boardingPointId;
	}
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	public void setDriverUsername(String driverUsername) {
		this.driverUsername = driverUsername;
	}
	@Column(name = "helperUsername", nullable = true, length = 128)
	public String getHelperUsername() {
		return helperUsername;
	}
	public void setHelperUsername(String helperUsername) {
		this.helperUsername = helperUsername;
	}
	@Column(name = "driverQualification1", nullable = true, length =128)
	public String getDriverQualification1() {
		return driverQualification1;
	}
	public void setDriverQualification1(String driverQualification1) {
		this.driverQualification1 = driverQualification1;
	}
	@Column(name = "helperQualification1", nullable = true, length =128)
	public String getHelperQualification1() {
		return helperQualification1;
	}
	public void setHelperQualification1(String helperQualification1) {
		this.helperQualification1 = helperQualification1;
	}
	@Column(name = "driverStaffId", nullable = true, length =10)
	public long getDriverStaffId() {
		return driverStaffId;
	}
	public void setDriverStaffId(long driverStaffId) {
		this.driverStaffId = driverStaffId;
	}
	@Column(name = "helperStaffId", nullable = true, length =10)
	public long getHelperStaffId() {
		return helperStaffId;
	}
	public void setHelperStaffId(long helperStaffId) {
		this.helperStaffId = helperStaffId;
	}
	@Column(name = "driverFirstName", nullable = true, length =128)
	public String getDriverFirstName() {
		return driverFirstName;
	}
	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}
	@Column(name = "helperFirstName", nullable = true, length =128)
	public String getHelperFirstName() {
		return helperFirstName;
	}
	public void setHelperFirstName(String helperFirstName) {
		this.helperFirstName = helperFirstName;
	}
	@Column(name = "driverLastName", nullable = true, length =128)
	public String getDriverLastName() {
		return driverLastName;
	}
	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}
	@Column(name = "helperLastName", nullable = true, length =128)
	public String getHelperLastName() {
		return helperLastName;
	}
	public void setHelperLastName(String helperLastName) {
		this.helperLastName = helperLastName;
	}
	@Column(name = "driverMiddleName", nullable = true, length =128)
	public String getDriverMiddleName() {
		return driverMiddleName;
	}
	public void setDriverMiddleName(String driverMiddleName) {
		this.driverMiddleName = driverMiddleName;
	}
	@Column(name = "helperMiddleName", nullable = true, length =128)
	public String getHelperMiddleName() {
		return helperMiddleName;
	}
	public void setHelperMiddleName(String helperMiddleName) {
		this.helperMiddleName = helperMiddleName;
	}
	@Column(name = "driverAddressLine1", nullable = true, length =128)
	public String getDriverAddressLine1() {
		return driverAddressLine1;
	}
	public void setDriverAddressLine1(String driverAddressLine1) {
		this.driverAddressLine1 = driverAddressLine1;
	}
	@Column(name = "helperAddressLine1", nullable = true, length =128)
	public String getHelperAddressLine1() {
		return helperAddressLine1;
	}
	public void setHelperAddressLine1(String helperAddressLine1) {
		this.helperAddressLine1 = helperAddressLine1;
	}
	@Column(name = "driverAddressLine2", nullable = true, length =128)
	public String getDriverAddressLine2() {
		return driverAddressLine2;
	}
	public void setDriverAddressLine2(String driverAddressLine2) {
		this.driverAddressLine2 = driverAddressLine2;
	}
	@Column(name = "helperAddressLine2", nullable = true, length =128)
	public String getHelperAddressLine2() {
		return helperAddressLine2;
	}
	
	public void setHelperAddressLine2(String helperAddressLine2) {
		this.helperAddressLine2 = helperAddressLine2;
	}
	@Column(name = "driverCity", nullable = true, length =128)
	public String getDriverCity() {
		return driverCity;
	}
	public void setDriverCity(String driverCity) {
		this.driverCity = driverCity;
	}
	@Column(name = "helperCity", nullable = true, length =128)
	public String getHelperCity() {
		return helperCity;
	}
	public void setHelperCity(String helperCity) {
		this.helperCity = helperCity;
	}
	@Column(name = "driverState", nullable = true, length =128)
	public String getDriverState() {
		return driverState;
	}
	public void setDriverState(String driverState) {
		this.driverState = driverState;
	}
	@Column(name = "helperState", nullable = true, length =128)
	public String getHelperState() {
		return helperState;
	}
	public void setHelperState(String helperState) {
		this.helperState = helperState;
	}
	@Column(name = "driverPostalCode", nullable = true, length =10)
	public String getDriverPostalCode() {
		return driverPostalCode;
	}
	public void setDriverPostalCode(String driverPostalCode) {
		this.driverPostalCode = driverPostalCode;
	}
	@Column(name = "helperPostalCode", nullable = true, length =10)
	public String getHelperPostalCode() {
		return helperPostalCode;
	}
	public void setHelperPostalCode(String helperPostalCode) {
		this.helperPostalCode = helperPostalCode;
	}
	@Column(name = "driverMobileNumber", nullable = true, length =128)
	public String getDriverMobileNumber() {
		return driverMobileNumber;
	}
	public void setDriverMobileNumber(String driverMobileNumber) {
		this.driverMobileNumber = driverMobileNumber;
	}
	@Column(name = "helperMobileNumber", nullable = true, length =13)
	public String getHelperMobileNumber() {
		return helperMobileNumber;
	}
	public void setHelperMobileNumber(String helperMobileNumber) {
		this.helperMobileNumber = helperMobileNumber;
	}
	@Column(name = "driverPhoneNumber", nullable = true, length =13)
	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}
	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}
	@Column(name = "helperPhoneNumber", nullable = true, length =13)
	public String getHelperPhoneNumber() {
		return helperPhoneNumber;
	}
	public void setHelperPhoneNumber(String helperPhoneNumber) {
		this.helperPhoneNumber = helperPhoneNumber;
	}
	@Column(name = "driverBloodGroup", nullable = true, length =10)
	public String getDriverBloodGroup() {
		return driverBloodGroup;
	}
	public void setDriverBloodGroup(String driverBloodGroup) {
		this.driverBloodGroup = driverBloodGroup;
	}
	@Column(name = "helperBloodGroup", nullable = true, length =10)
	public String getHelperBloodGroup() {
		return helperBloodGroup;
	}
	public void setHelperBloodGroup(String helperBloodGroup) {
		this.helperBloodGroup = helperBloodGroup;
	}
	@Column(name = "driverDateOfBirth", nullable = true, length =12)
	public String getDriverDateOfBirth() {
		return driverDateOfBirth;
	}
	public void setDriverDateOfBirth(String driverDateOfBirth) {
		this.driverDateOfBirth = driverDateOfBirth;
	}
	@Column(name = "helperDateOfBirth", nullable = true, length =12)
	public String getHelperDateOfBirth() {
		return helperDateOfBirth;
	}
	public void setHelperDateOfBirth(String helperDateOfBirth) {
		this.helperDateOfBirth = helperDateOfBirth;
	}
	@Column(name = "driverDateOfJoining", nullable = true, length =12)
	public String getDriverDateOfJoining() {
		return driverDateOfJoining;
	}
	public void setDriverDateOfJoining(String driverDateOfJoining) {
		this.driverDateOfJoining = driverDateOfJoining;
	}
	@Column(name = "helperDateOfJoining", nullable = true, length =12)
	public String getHelperDateOfJoining() {
		return helperDateOfJoining;
	}
	public void setHelperDateOfJoining(String helperDateOfJoining) {
		this.helperDateOfJoining = helperDateOfJoining;
	}
	@Column(name = "driverRoleName", nullable = true, length =128)
	public String getDriverRoleName() {
		return driverRoleName;
	}
	public void setDriverRoleName(String driverRoleName) {
		this.driverRoleName = driverRoleName;
	}
	@Column(name = "helperRoleName", nullable = true, length =128)
	public String getHelperRoleName() {
		return helperRoleName;
	}
	public void setHelperRoleName(String helperRoleName) {
		this.helperRoleName = helperRoleName;
	}
	@Column(name = "driverRoleId", nullable = true, length =10)
	public long getDriverRoleId() {
		return driverRoleId;
	}
	public void setDriverRoleId(long driverRoleId) {
		this.driverRoleId = driverRoleId;
	}
	@Column(name = "helperRoleId", nullable = true, length =10)
	public long getHelperRoleId() {
		return helperRoleId;
	}
	public void setHelperRoleId(long helperRoleId) {
		this.helperRoleId = helperRoleId;
	}
	@Column(name = "driverLicenseNumber", nullable = true, length =10)
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}
	@Column(name = "helperLicenseNumber", nullable = true, length =10)
	public String getHelperLicenseNumber() {
		return helperLicenseNumber;
	}
	public void setHelperLicenseNumber(String helperLicenseNumber) {
		this.helperLicenseNumber = helperLicenseNumber;
	}
	@Column(name = "diverLicenseExpDate", nullable = true, length =10)
	public String getDiverLicenseExpDate() {
		return diverLicenseExpDate;
	}
	public void setDiverLicenseExpDate(String diverLicenseExpDate) {
		this.diverLicenseExpDate = diverLicenseExpDate;
	}
	@Column(name = "helperLicenseExpDate", nullable = true, length =10)
	public String getHelperLicenseExpDate() {
		return helperLicenseExpDate;
	}
	public void setHelperLicenseExpDate(String helperLicenseExpDate) {
		this.helperLicenseExpDate = helperLicenseExpDate;
	}
	
	
	public ViewStaffVehicleTripdetails()
    {
        super();
    }
    public ViewStaffVehicleTripdetails(String driverUsername, String driverQualification1, long driverStaffId,
			String helperUsername, String driverLastName) {
		super();
		this.driverUsername = driverUsername;
		this.driverQualification1 = driverQualification1;
		this.driverStaffId = driverStaffId;
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.helperUsername = helperUsername;
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
            .append(" User Name: ")
            .append(getDriverUsername())
            .append(" Status : ")
            .append(getRoutePointName());
        return buffer.toString();
    }
    @Override
	public int compareTo(Object object) {
    	ViewStaffVehicleTripdetails myClass = (ViewStaffVehicleTripdetails) object;
        return new CompareToBuilder().append(this.driverUsername,
                        myClass.driverUsername).toComparison();
    }
   
   
	@Transient
    public String getFullFormattedName(
        boolean title,
        boolean middle,
        boolean suffix)
    {
        StringBuffer ret = new StringBuffer(10);
            if (!StringFunctions.isNullOrEmpty(getDriverLastName()))
            {
                ret.append(getDriverLastName());
                
                
            }
            if (!StringFunctions.isNullOrEmpty(getDriverFirstName()))
            {
                ret.append(", ");
                ret.append(getDriverFirstName());
              //  ret.append(" ");
            }
        ret.append(" ");
        return ret.toString().trim();
    }
	@Transient
    public String getPersonFirstLastNameOnly()
    {
        StringBuffer ret = new StringBuffer(10);
            if (!StringFunctions.isNullOrEmpty(getDriverFirstName()))
            {
                ret.append(getDriverFirstName());
            }
            if (!StringFunctions.isNullOrEmpty(getDriverLastName()))
            {
                ret.append(" ");
                ret.append(getDriverLastName());
            }
       
        return ret.toString().trim();
    }

    /**
     * Returns the student for display on UI
     * @return string with full formatted student
     */
   @Transient
    public String getDisplayName()
    {
        return getFullFormattedName(false, true, true);
    }
   @Transient
   public String getDiverPersonFullName()
   {
       StringBuffer ret = new StringBuffer(10);
	       
           if (!StringFunctions.isNullOrEmpty(getDriverFirstName()))
           {
               ret.append(getDriverFirstName());
           }
           if (!StringFunctions.isNullOrEmpty(getDriverMiddleName()))
	       {
        	   ret.append(" ");
	           ret.append(getDriverMiddleName());
	       }
           if (!StringFunctions.isNullOrEmpty(getDriverLastName()))
           {
               ret.append(" ");
               ret.append(getDriverLastName());
           }
      
       return ret.toString().trim();
   }
   
   @Transient
   public String getHelperPersonFullName()
   {
       StringBuffer ret = new StringBuffer(10);
	       
           if (!StringFunctions.isNullOrEmpty(getHelperFirstName()))
           {
               ret.append(getHelperFirstName());
           }
           if (!StringFunctions.isNullOrEmpty(getHelperMiddleName()))
	       {
        	   ret.append(" ");
	           ret.append(getHelperMiddleName());
	       }
           if (!StringFunctions.isNullOrEmpty(getHelperLastName()))
           {
               ret.append(" ");
               ret.append(getHelperLastName());
           }
      
       return ret.toString().trim();
   }
	/**
	 * @return the addressLine1
	 */
  
   
    @Column(name = "custId", nullable = true, length = 10) 
    public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
   
	@Column(name = "routeEndName", nullable = false, length = 10)
	public String getRouteEndName() {
		return routeEndName;
	}
	
	
	public void setRouteEndName(String routeEndName) {
		this.routeEndName = routeEndName;
	}
	
	
	
	
	/*@Transient
	public String getStartTime() {
		StringBuffer sb = new StringBuffer();
		if (!ObjectFunctions.isNullOrEmpty(getShh1())) {
			sb.append(getShh1());
			sb.append(":");
		}
		if (!ObjectFunctions.isNullOrEmpty(getSmm1())) {
			sb.append(getSmm1());
			sb.append(" ");
		}
		if (!ObjectFunctions.isNullOrEmpty(getAmPm7())) {
			sb.append(getAmPm7());
		}
		return sb.toString();
	}
	@Transient
	public String getEndTime() {
		StringBuffer sb = new StringBuffer();
		if (!ObjectFunctions.isNullOrEmpty(getEhh2())) {
			sb.append(getEhh2());
			sb.append(":");
		}
		if (!ObjectFunctions.isNullOrEmpty(getEmm2())) {
			sb.append(getEmm2());
			sb.append(" ");
		}
		if (!ObjectFunctions.isNullOrEmpty(getAmPm8())) {
			sb.append(getAmPm8());
		}
		return sb.toString();
	}*/
	
	@Column(name = "routeId", nullable = true, length = 10) 
	public long getRouteId() {
		return routeId;
	}


	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	@Column(name = "driverId", nullable = true, length = 10)
	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	@Column(name = "helperId", nullable = true, length = 10)
	public long getHelperId() {
		return helperId;
	}

	public void setHelperId(long helperId) {
		this.helperId = helperId;
	}
	
	/**
	 * @return the routePointName
	 */
	@Column(name = "routePointName", nullable = true, length = 40)
	public String getRoutePointName() {
		return routePointName;
	}
	/**
	 * @param routePointName the routePointName to set
	 */
	public void setRoutePointName(String routePointName) {
		this.routePointName = routePointName;
	}
	/**
	 * @return the routeName
	 */
	@Column(name = "routeName", nullable = true, length = 20)
	public String getRouteName() {
		return routeName;
	}
	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
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
	 * @return the boardingPointStatTime
	 */
	public String getBoardingPointStatTime() {
		return boardingPointStatTime;
	}
	/**
	 * @param boardingPointStatTime the boardingPointStatTime to set
	 */
	public void setBoardingPointStatTime(String boardingPointStatTime) {
		this.boardingPointStatTime = boardingPointStatTime;
	}
	/**
	 * @return the boardingPointEndTime
	 */
	public String getBoardingPointEndTime() {
		return boardingPointEndTime;
	}
	/**
	 * @param boardingPointEndTime the boardingPointEndTime to set
	 */
	public void setBoardingPointEndTime(String boardingPointEndTime) {
		this.boardingPointEndTime = boardingPointEndTime;
	}
	/**
	 * @return the vehicleType
	 */
	@Column(name = "vehicleType", nullable = true, length = 40)
	public String getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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
	 * @return the noOfSeats
	 */
	@Column(name = "noOfSeats", nullable = false, length = 10)
	public long getNoOfSeats() {
		return noOfSeats;
	}
	/**
	 * @param noOfSeats the noOfSeats to set
	 */
	public void setNoOfSeats(long noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	/**
	 * @return the insuranceNumber
	 */
	@Column(name = "insuranceNumber", nullable = true, length = 10)
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	/**
	 * @param insuranceNumber the insuranceNumber to set
	 */
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	/**
	 * @return the insuranceExpiredDate
	 */
	public Date getInsuranceExpiredDate() {
		return insuranceExpiredDate;
	}
	/**
	 * @param insuranceExpiredDate the insuranceExpiredDate to set
	 */
	public void setInsuranceExpiredDate(Date insuranceExpiredDate) {
		this.insuranceExpiredDate = insuranceExpiredDate;
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

 @Transient
    public String getVehicleIdAndRouteId() {
	return getVehicleId()+"_"+getRouteId();
    }
  @Transient
    public String getVehicleTypeAndRouteName() {
	return getVehicleType()+" - "+getRouteName();
    }
	@Transient
	public long getAvailableNoOfSeats() {
		return availableNoOfSeats;
	}
	public void setAvailableNoOfSeats(long availableNoOfSeats) {
		this.availableNoOfSeats = availableNoOfSeats;
	}
	@Transient
	public long getEnRolledNoOfSeat() {
		return enRolledNoOfSeat;
	}
	public void setEnRolledNoOfSeat(long enRolledNoOfSeat) {
		this.enRolledNoOfSeat = enRolledNoOfSeat;
	}
	@Transient
	public long getTotalNoOfSeat() {
		return totalNoOfSeat;
	}
	public void setTotalNoOfSeat(long totalNoOfSeat) {
		this.totalNoOfSeat = totalNoOfSeat;
	}
	@Transient
    public String getVehicleTypeAndVehicleNumber() {
	return getVehicleType()+" - "+getVehicleNumber();
    }
	public long getStudentsCount() {
		return studentsCount;
	}
	public void setStudentsCount(long studentsCount) {
		this.studentsCount = studentsCount;
	}
}