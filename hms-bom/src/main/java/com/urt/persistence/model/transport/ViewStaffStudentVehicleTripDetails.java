package com.urt.persistence.model.transport;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "vw_staffStudentVehicleTripDetails")
public class ViewStaffStudentVehicleTripDetails  implements Serializable,Cloneable {


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
     protected long routeId;
     protected String routePointName;
     protected String routeEndName;
     protected String routeName;   
     protected String routePointStartTime;  
     protected String routePointEndTime;
    
     private long boardingPointId;
     protected String boardingPointName;
     protected String boardingPointStatTime;
     protected String boardingPointEndTime;
     
     protected long vehicleId;
    protected String vehicleType;  
     protected String vehicleNumber;
     protected long driverId;
     private long driverStaffId;
     private String driverFirstName;
     private String driverLastName;
     private String driverMiddleName;
     protected String driverMobileNumber;
     private long driverRoleId;
     private long vehicleAcademicId;
     
     
    /* 
     protected long helperId;
     private long helperStaffId;
     private String helperFirstName;
     private String helperLastName;
     private String helperMiddleName;
     protected String helperMobileNumber;
     private long helperRoleId;*/
     
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
	private long availableNoOfSeats;
     private long enRolledNoOfSeat;
     private long totalNoOfSeat;
    
     private String firstName;
 	 private String lastName;
 	 private String middleName;
 	 protected String mobileNumber;
	 protected long rollNumber;
	 protected String className;
	 private String section;
	 private String transportMode;
	 private long studentId;
	 private String parentEmail;
	 protected long accountId;
	 private long vehicleSeatCount;
     private long availableSeatCount;
     private long sortingOrder;
     
	@Column(name = "sortingOrder", columnDefinition="bigint(20) default 0")
    public long getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	
	public long getVehicleSeatCount() {
		return vehicleSeatCount;
	}
	
	public void setVehicleSeatCount(long vehicleSeatCount) {
		this.vehicleSeatCount = vehicleSeatCount;
	}
	public long getAvailableSeatCount() {
		return availableSeatCount;
	}
	public void setAvailableSeatCount(long availableSeatCount) {
		this.availableSeatCount = availableSeatCount;
	}
	@Column(name = "accountId", nullable = true, length = 10) 
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	
	@Column(name = "driverStaffId", nullable = true, length =10)
	public long getDriverStaffId() {
		return driverStaffId;
	}
	public void setDriverStaffId(long driverStaffId) {
		this.driverStaffId = driverStaffId;
	}

	@Column(name = "driverLastName", nullable = true, length =128)
	public String getDriverLastName() {
		return driverLastName;
	}
	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}
	@Column(name = "driverMiddleName", nullable = true, length =128)
	public String getDriverMiddleName() {
		return driverMiddleName;
	}
	public void setDriverMiddleName(String driverMiddleName) {
		this.driverMiddleName = driverMiddleName;
	}
	@Column(name = "driverMobileNumber", nullable = true, length =128)
	public String getDriverMobileNumber() {
		return driverMobileNumber;
	}
	public void setDriverMobileNumber(String driverMobileNumber) {
		this.driverMobileNumber = driverMobileNumber;
	}
	

	@Column(name = "driverRoleId", nullable = true, length =10)
	public long getDriverRoleId() {
		return driverRoleId;
	}
	public void setDriverRoleId(long driverRoleId) {
		this.driverRoleId = driverRoleId;
	}
	
	/*
	@Column(name = "helperStaffId", nullable = true, length =10)
	public long getHelperStaffId() {
		return helperStaffId;
	}
	public void setHelperStaffId(long helperStaffId) {
		this.helperStaffId = helperStaffId;
	}
	
	@Column(name = "helperLastName", nullable = true, length =128)
	public String getHelperLastName() {
		return helperLastName;
	}
	public void setHelperLastName(String helperLastName) {
		this.helperLastName = helperLastName;
	}
	
	@Column(name = "helperMiddleName", nullable = true, length =128)
	public String getHelperMiddleName() {
		return helperMiddleName;
	}
	public void setHelperMiddleName(String helperMiddleName) {
		this.helperMiddleName = helperMiddleName;
	}

	
	@Column(name = "helperMobileNumber", nullable = true, length =13)
	public String getHelperMobileNumber() {
		return helperMobileNumber;
	}
	public void setHelperMobileNumber(String helperMobileNumber) {
		this.helperMobileNumber = helperMobileNumber;
	}
	
	@Column(name = "helperRoleId", nullable = true, length =10)
	public long getHelperRoleId() {
		return helperRoleId;
	}
	public void setHelperRoleId(long helperRoleId) {
		this.helperRoleId = helperRoleId;
	}*/
	
	public ViewStaffStudentVehicleTripDetails()
    {
        super();
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
            //.append(getDriverFirstName())
            .append(" Status : ")
            .append(getRoutePointName());
        return buffer.toString();
    }
    
    /*
    public int compareTo(Object object) {
    	ViewStaffStudentVehicleTripDetails myClass = (ViewStaffStudentVehicleTripDetails) object;
        return new CompareToBuilder().append(this.driverFirstName,
                        myClass.driverFirstName).toComparison();
    }*/
   
   
	/*@Transient
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
    }*//*
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
    }*/

    /**
     * Returns the student for display on UI
     * @return string with full formatted student
     */
   /*@Transient
    public String getDisplayName()
    {
      //  return getFullFormattedName(false, true, true);
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
   }*/
   /*
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
   }*/
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
	/*
	@Column(name = "helperId", nullable = true, length = 10)
	public long getHelperId() {
		return helperId;
	}

	public void setHelperId(long helperId) {
		this.helperId = helperId;
	}
	*/
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
	 *//*
	@Column(name = "vehicleType", nullable = true, length = 40)
	public String getVehicleType() {
		return vehicleType;
	}
	*//**
	 * @param vehicleType the vehicleType to set
	 *//*
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}*/
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

 @Transient
    public String getVehicleIdAndRouteId() {
	return getVehicleId()+"_"+getRouteId();
    }
  /*@Transient
    public String getVehicleTypeAndRouteName() {
	return getVehicleType()+" - "+getRouteName();
    }*/
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
/*	@Transient
    public String getVehicleTypeAndVehicleNumber() {
	return getVehicleType()+" - "+getVehicleNumber();
    }*/
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public long getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
		}
		return getClassName();
	}
	@Transient
	public String getPersonFullName() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getFirstName())) {
			ret.append(getFirstName());
		}
		if (!StringFunctions.isNullOrEmpty(getLastName())) {
			ret.append(" ");
			ret.append(getLastName());
		}

		return ret.toString().trim();
	}
	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	@Id
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getBoardingPointId() {
		return boardingPointId;
	}
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	public String getDriverFirstName() {
		return driverFirstName;
	}
	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}
	/*
	 * public String getHelperFirstName() {
		return helperFirstName;
	}
	public void setHelperFirstName(String helperFirstName) {
		this.helperFirstName = helperFirstName;
	}*/
	
}