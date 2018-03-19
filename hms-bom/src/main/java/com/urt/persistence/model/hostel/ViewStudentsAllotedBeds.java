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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "vw_studentsAllotedBeds")
public class ViewStudentsAllotedBeds implements Serializable,Cloneable,Comparable {
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
	private String admissionNumber;
	private long classId;
	private long  rollNumber;
	private long  categoryId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;
	private String motherName;
	private Date dateOfBirth;
	private String bloodGroup;
	private String mobileNumber;
	private long castId;
	private long subCastId;
	private String religionId;
	private String nationality;
	private String  gender;
	private long motherToungId;
	private String city;
	private String postalCode;
	private String className;
	private String section;
	private long mediumId;
	private String higherStandard;
	private long classSectionId;
	private String castName;
	private String subCastName;
	private String stateName;
	private String religion;
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
	private long studentId;
	private String streetName;
	private long floorId;
	private String hostelFullAddress;
	private String fullName;
	private String studentFloorName;
	private String classSection;
	
	
	public String getHostelFullAddress() {
		return hostelFullAddress;
	}

	public void setHostelFullAddress(String hostelFullAddress) {
		this.hostelFullAddress = hostelFullAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setStudentFloorName(String studentFloorName) {
		this.studentFloorName = studentFloorName;
	}

	public void setClassSection(String classSection) {
		this.classSection = classSection;
	}

	/**
     * @return the floorId
     */
   
    public long getFloorId() {
        return floorId;
    }

    /**
     * @param floorId the floorId to set
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
	 * @param streetName the streetName to set
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
	 * @param custId the custId to set
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
	 * @param academicYearId the academicYearId to set
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
	 * @param status the status to set
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
	 * @param accountId the accountId to set
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
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the classId
	 */
	public long getClassId() {
		return classId;
	}

	/**
	 * @param classId the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}

	/**
	 * @return the rollNumber
	 */
	public long getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * @return the categoryId
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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
	 * @return the castId
	 */
	public long getCastId() {
		return castId;
	}

	/**
	 * @param castId the castId to set
	 */
	public void setCastId(long castId) {
		this.castId = castId;
	}

	/**
	 * @return the subCastId
	 */
	public long getSubCastId() {
		return subCastId;
	}

	/**
	 * @param subCastId the subCastId to set
	 */
	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}

	/**
	 * @return the religionId
	 */
	public String getReligionId() {
		return religionId;
	}

	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

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
	 * @return the motherToungId
	 */
	public long getMotherToungId() {
		return motherToungId;
	}

	/**
	 * @param motherToungId the motherToungId to set
	 */
	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
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
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

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
	 * @return the mediumId
	 */
	public long getMediumId() {
		return mediumId;
	}

	/**
	 * @param mediumId the mediumId to set
	 */
	public void setMediumId(long mediumId) {
		this.mediumId = mediumId;
	}

	/**
	 * @return the higherStandard
	 */
	public String getHigherStandard() {
		return higherStandard;
	}

	/**
	 * @param higherStandard the higherStandard to set
	 */
	public void setHigherStandard(String higherStandard) {
		this.higherStandard = higherStandard;
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
	 * @return the castName
	 */
	public String getCastName() {
		return castName;
	}

	/**
	 * @param castName the castName to set
	 */
	public void setCastName(String castName) {
		this.castName = castName;
	}

	/**
	 * @return the subCastName
	 */
	public String getSubCastName() {
		return subCastName;
	}

	/**
	 * @param subCastName the subCastName to set
	 */
	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the bedId
	 */
	@Id
	@Column(name="bedId",nullable=false,updatable=false,unique=true)
	public long getBedId() {
		return bedId;
	}

	/**
	 * @param bedId the bedId to set
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
	 * @param bedName the bedName to set
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
	 * @param bedLevel the bedLevel to set
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
	 * @param bedCost the bedCost to set
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
	 * @param bedStatus the bedStatus to set
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
	 * @param roomId the roomId to set
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
	 * @param roomName the roomName to set
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
	 * @param roomLevel the roomLevel to set
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
	 * @param roomType the roomType to set
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
	 * @param floorName the floorName to set
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
	 * @param floorLevel the floorLevel to set
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
	 * @param buildingId the buildingId to set
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
	 * @param buildingName the buildingName to set
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
	 * @param buildingShortName the buildingShortName to set
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
	 * @param hostelName the hostelName to set
	 */
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	/**
	 * @return the studentId
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		 if (this == obj) {
			 return true;
		 } else {
			 return false;	        
		 }
	}

	/**
	 * 
	 */
	public ViewStudentsAllotedBeds() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);	
		buffer.append(classSectionId).append(getClassSectionId());
		buffer.append(academicYearId);		
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895).append(this.rollNumber)
                .append(this.classSectionId).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    @Override
	public int compareTo(Object object) {
    	ViewStudentsAllotedBeds studAllotedBeds = (ViewStudentsAllotedBeds) object;
         if(ObjectFunctions.isNullOrEmpty(studAllotedBeds)){
         	return 0;
         }else
        	 return (this.getBuildingName()+this.getClassSection()+this.getStudentFloorName()+this.getStudentRoomName()+this.getStudentBedName()).compareToIgnoreCase(studAllotedBeds.getBuildingName()+studAllotedBeds.getClassSection()+studAllotedBeds.getStudentFloorName()+studAllotedBeds.getStudentRoomName()+studAllotedBeds.getStudentBedName());
    }
    @Transient
    public String getClassSection(){
    	if(StringFunctions.isNullOrEmpty(getSection()))
    		return getClassName();
    	else
    		return getClassName()+" "+getSection();
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
   public String getStudentBedName(){
	   if(getBedLevel() > 0)
		   return getBedName()+getBedLevel();
	   else
		   return getBedName();
	   
   }
   @Transient
   public String getStudentFloorName(){
	   if(getFloorLevel() > 0)
		   return getFloorName()+getFloorLevel();
	   else
		   return getFloorName();
	   
   }
   @Transient
   public String getStudentRoomName(){
	   if(getRoomLevel() > 0)
		   return getRoomName()+getRoomLevel();
	   else
		   return getRoomName();
   }
   @Transient
   public String getHostelAddress(){
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
