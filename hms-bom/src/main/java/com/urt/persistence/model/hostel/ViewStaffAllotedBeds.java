package com.urt.persistence.model.hostel;

/********************************************************************
 * Copyright (C) 2005-06

 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
 / ********************************************************************/
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_staffAllotedBeds")
public class ViewStaffAllotedBeds implements Serializable, Cloneable,Comparable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;

	private long custId;
	private long academicYearId;
	private boolean status;
	private long accountId;
	private String username;
	private String firstName;
	private String lastName;
	private String city;
	private String postalCode;
	private String stateName;
	private long bedId;
	private String bedName;
	private int bedLevel;
	private double bedCost;
	private boolean bedStatus;
	private long roomId;
	private String roomName;
	private int roomLevel;
	private String roomType;
	private String floorName;
	private int floorLevel;
	private long buildingId;
	private String buildingName;
	//private String buildingAddress;
	private String buildingShortName;
	private String hostelName;
	private long staffId;
	private String streetName;
	private long floorId;
    private long hostelCategoryId;
    private String  gender;
	
	
	/**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
	/**
	 * @return the hostelCategoryId
	 */
	public long getHostelCategoryId() {
		return hostelCategoryId;
	}
	/**
	 * @param hostelCategoryId the hostelCategoryId to set
	 */
	public void setHostelCategoryId(long hostelCategoryId) {
		this.hostelCategoryId = hostelCategoryId;
	}
	
	

	/**
	 * @return the staffId
	 */
	public long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId
	 *            the staffId to set
	 */
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the floorId
	 */

	public long getFloorId() {
		return floorId;
	}

	/**
	 * @param floorId
	 *            the floorId to set
	 */
	public void setFloorId(long floorId) {
		this.floorId = floorId;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the academicYearId
	 */
	public long getAcademicYearId() {
		return academicYearId;
	}

	/**
	 * @param academicYearId
	 *            the academicYearId to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
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

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the castId
	 */

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the bedId
	 */
	@Id
	@Column(name = "bedId", nullable = false, updatable = false, unique = true)
	public long getBedId() {
		return bedId;
	}

	/**
	 * @param bedId
	 *            the bedId to set
	 */
	public void setBedId(long bedId) {
		this.bedId = bedId;
	}

	/**
	 * @return the bedName
	 */
	public String getBedName() {
		return bedName;
	}

	/**
	 * @param bedName
	 *            the bedName to set
	 */
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}

	/**
	 * @return the bedLevel
	 */
	public int getBedLevel() {
		return bedLevel;
	}

	/**
	 * @param bedLevel
	 *            the bedLevel to set
	 */
	public void setBedLevel(int bedLevel) {
		this.bedLevel = bedLevel;
	}

	/**
	 * @return the bedCost
	 */
	public double getBedCost() {
		return bedCost;
	}

	/**
	 * @param bedCost
	 *            the bedCost to set
	 */
	public void setBedCost(double bedCost) {
		this.bedCost = bedCost;
	}

	/**
	 * @return the bedStatus
	 */
	public boolean isBedStatus() {
		return bedStatus;
	}

	/**
	 * @param bedStatus
	 *            the bedStatus to set
	 */
	public void setBedStatus(boolean bedStatus) {
		this.bedStatus = bedStatus;
	}

	/**
	 * @return the roomId
	 */
	public long getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @param roomName
	 *            the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * @return the roomLevel
	 */
	public int getRoomLevel() {
		return roomLevel;
	}

	/**
	 * @param roomLevel
	 *            the roomLevel to set
	 */
	public void setRoomLevel(int roomLevel) {
		this.roomLevel = roomLevel;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the floorName
	 */
	public String getFloorName() {
		return floorName;
	}

	/**
	 * @param floorName
	 *            the floorName to set
	 */
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	/**
	 * @return the floorLevel
	 */
	public int getFloorLevel() {
		return floorLevel;
	}

	/**
	 * @param floorLevel
	 *            the floorLevel to set
	 */
	public void setFloorLevel(int floorLevel) {
		this.floorLevel = floorLevel;
	}

	/**
	 * @return the buildingId
	 */
	public long getBuildingId() {
		return buildingId;
	}

	/**
	 * @param buildingId
	 *            the buildingId to set
	 */
	public void setBuildingId(long buildingId) {
		this.buildingId = buildingId;
	}

	/**
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * @param buildingName
	 *            the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**
	 * @return the buildingShortName
	 */
	public String getBuildingShortName() {
		return buildingShortName;
	}

	/**
	 * @param buildingShortName
	 *            the buildingShortName to set
	 */
	public void setBuildingShortName(String buildingShortName) {
		this.buildingShortName = buildingShortName;
	}

	/**
	 * @return the hostelName
	 */
	public String getHostelName() {
		return hostelName;
	}

	/**
	 * @param hostelName
	 *            the hostelName to set
	 */
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {		
		if(this == obj){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 */
	public ViewStaffAllotedBeds() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Formats the name as follows: [title ]firstName[ middleName] lastName[
	 * suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
		buffer.append(academicYearId);
		return buffer.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public int compareTo(Object object) {
		ViewStaffAllotedBeds staffAllotedBeds = (ViewStaffAllotedBeds) object;
         if(ObjectFunctions.isNullOrEmpty(staffAllotedBeds)){
         	return 0;
         }else
        	 return (this.getBuildingName()+this.getStaffFloorName()+this.getStaffRoomName()+this.getStaffBedName()).compareToIgnoreCase(staffAllotedBeds.getBuildingName()+staffAllotedBeds.getStaffFloorName()+staffAllotedBeds.getStaffRoomName()+staffAllotedBeds.getStaffBedName());
    }
	
	 @Transient
	   public String getStaffRoomName(){
		   if(getRoomLevel() > 0)
			   return getRoomName()+getRoomLevel();
		   else
			   return getRoomName();
		   
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

	@Transient
	public String getStaffBedName() {
		if (getBedLevel() > 0)
			return getBedName() + getBedLevel();
		else
			return getBedName();

	}

	@Transient
	public String getStaffFloorName() {
		if (getFloorLevel() > 0)
			return getFloorName() + getFloorLevel();
		else
			return getFloorName();
	}


	@Transient
	public String getHostelAddress() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getStreetName())) {
			ret.append(getStreetName());
		}
		if (!StringFunctions.isNullOrEmpty(getCity())) {
			ret.append(", ");
			ret.append(getCity());
		}
		if (!StringFunctions.isNullOrEmpty(getPostalCode())) {
			ret.append(" ");
			ret.append(getPostalCode());
		}
		if (!StringFunctions.isNullOrEmpty(getStateName())) {
			ret.append(", ");
			ret.append(getStateName());
		}
		return ret.toString().trim();
	}
}
