package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewGroupType.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Narahari Created /
 ********************************************************************/

@Entity
@Table(name = "vw_studentClassDetails")
public class ViewStudentClassDetails implements Serializable,
		Cloneable, Comparable {

	/**
	 * Default buffer size to be allocated for StringBuffer.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long studId;
	protected long classSectionId;
	private String status;
	private long accountId;
	private long custId;
	protected String rollNumber;
	private long academicYearId;
	protected long bedId;
	protected long classId;
	private String firstName;
	private String lastName;
	protected String className;
	private String section;
	protected boolean higherStandard;
	private String fullName;
	protected String castName;
	protected String admissionNumber;
	private String registerNumber;
	protected boolean accountExpired;
	private String studDiscontinueDesc;
	private long religionId;
	private long castId;
	private long subCastId;
	protected Date dateOfBirth;
	protected Date dateOfJoining;
	private String fatherName;
	private String motherName;
	private String motherOccupation;
	private String parentEmail;
	protected Double annualIncome;
	protected String bloodGroup;
	private String mobileNumber;
	protected String phoneNumber;
	private String nationality;
	private String streetName;
	private String postalCode; 
	private long motherToungId;
	private String identification1;
	private String identification2;
	private String city;
	private String visionL;
	private String visionR;
	private String teeth;
	private String oralHygiene;
	private double height;
    private double weight;
    private String gender;
    private String rationCardNumber;
	private String communityNumber; 
	private String occupation;  
	private long categoryId;
	private long boardingPointId;
	private String joinedThroughAdmissions;
	private long sortingOrder;
	private String transportMode;  
	//private long vehicleId;
	private long roomId;
	private long imageId;
	private long vehicleAcademicDetailsId;
	private Long futureAcademicClassSecId;
	private String feePaidStatus;
	private String feeConfigured;
	private String description;
	private String academicYear;
	private double committedFee;
	protected String secondaryMobileNumber;
	
	protected String addressType;
	protected String anotherMobileNumber;
	protected String anotherSecondaryMobileNumber;
	protected String anotherParentEmail;
	private String imageName;
	private String thumbNail;
	private String imagePath;
	private String parentId;
	private long optionalSubjectId;
	private Date startDate;
	private Date endDate;
	protected Date createdDate;

	private String addressLine1;
	private String addressLine2;
	
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getOptionalSubjectId() {
		return optionalSubjectId;
	}

	public void setOptionalSubjectId(long optionalSubjectId) {
		this.optionalSubjectId = optionalSubjectId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "addressType", nullable = false, length = 1,columnDefinition="char(1) default 'R'")
	 public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	public String getAnotherMobileNumber() {
		return anotherMobileNumber;
	}

	public void setAnotherMobileNumber(String anotherMobileNumber) {
		this.anotherMobileNumber = anotherMobileNumber;
	}

	public String getAnotherSecondaryMobileNumber() {
		return anotherSecondaryMobileNumber;
	}

	public void setAnotherSecondaryMobileNumber(String anotherSecondaryMobileNumber) {
		this.anotherSecondaryMobileNumber = anotherSecondaryMobileNumber;
	}

	public String getAnotherParentEmail() {
		return anotherParentEmail;
	}

	public void setAnotherParentEmail(String anotherParentEmail) {
		this.anotherParentEmail = anotherParentEmail;
	}

	 
	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}
	public void setSecondaryMobileNumber(String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}
	
	@Column(name = "feeConfigured", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getFeeConfigured() {
		return feeConfigured;
	}
	public void setFeeConfigured(String feeConfigured) {
		this.feeConfigured = feeConfigured;
	}
	
	@Column(name = "feePaidStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getFeePaidStatus() {
		return feePaidStatus;
	}
	public void setFeePaidStatus(String feePaidStatus) {
		this.feePaidStatus = feePaidStatus;
	}
	/**
	 * @return the futureAcademicClassSecId
	 */
	public Long getFutureAcademicClassSecId() {
		return futureAcademicClassSecId;
	}
	/**
	 * @param futureAcademicClassSecId the futureAcademicClassSecId to set
	 */
	public void setFutureAcademicClassSecId(Long futureAcademicClassSecId) {
		this.futureAcademicClassSecId = futureAcademicClassSecId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the vehicleAcademicDetailsId
	 */
	public long getVehicleAcademicDetailsId() {
		return vehicleAcademicDetailsId;
	}

	/**
	 * @param vehicleAcademicDetailsId the vehicleAcademicDetailsId to set
	 */
	public void setVehicleAcademicDetailsId(long vehicleAcademicDetailsId) {
		this.vehicleAcademicDetailsId = vehicleAcademicDetailsId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	/*public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
*/
	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public long getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public String getJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}

	public void setJoinedThroughAdmissions(String joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
	}

	@Column(name = "FatherOccupation", nullable = true, length = 128)
    public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
    public String getRationCardNumber() {
		return rationCardNumber;
	}

	public void setRationCardNumber(String rationCardNumber) {
		this.rationCardNumber = rationCardNumber;
	}

	public String getCommunityNumber() {
		return communityNumber;
	}

	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
    
    public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getVisionL() {
		return visionL;
	}

	public void setVisionL(String visionL) {
		this.visionL = visionL;
	}
	public String getVisionR() {
		return visionR;
	}

	public void setVisionR(String visionR) {
		this.visionR = visionR;
	}

	public String getTeeth() {
		return teeth;
	}

	public void setTeeth(String teeth) {
		this.teeth = teeth;
	}

	public String getOralHygiene() {
		return oralHygiene;
	}

	public void setOralHygiene(String oralHygiene) {
		this.oralHygiene = oralHygiene;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public Double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public long getMotherToungId() {
		return motherToungId;
	}

	public void setMotherToungId(long motherToungId) {
		this.motherToungId = motherToungId;
	}

	public String getIdentification1() {
		return identification1;
	}

	public void setIdentification1(String identification1) {
		this.identification1 = identification1;
	}

	public String getIdentification2() {
		return identification2;
	}

	public void setIdentification2(String identification2) {
		this.identification2 = identification2;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	@Column(name = "subCastId", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getSubCastId() {
		return this.subCastId;
	}

	public void setSubCastId(long subCastId) {
		this.subCastId = subCastId;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	@Column(name = "castId", nullable = false ,columnDefinition="bigint(20) default 0")
	    public long getCastId() {
			return this.castId;
		}
		public void setCastId(long castId) {
			this.castId = castId;
		}
	
	

	/**
	 * @return the religionId
	 */
	@Column(name = "religionId", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getReligionId() {
		return religionId;
	}

	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}

	/**
	 * @return the studId
	 */
	@Id
	@Column(name="studId",nullable=false,updatable=false,unique=true)
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

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}


	/**
	 * @return the bedId
	 */
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
	 * @return the higherStandard
	 */
    @Column(name = "higherStandard", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public boolean isHigherStandard() {
		return higherStandard;
	}

	/**
	 * @param higherStandard the higherStandard to set
	 */
	public void setHigherStandard(boolean higherStandard) {
		this.higherStandard = higherStandard;
	}


	public String getCastName() {
		return this.castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	
	@Column(name = "admissionNumber", nullable = true, length = 128)
    public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	
	@Column(name = "accountExpired", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public long getClassSectionId() {
		return classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}


	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}


	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public ViewStudentClassDetails() {
		super();
	}

	public ViewStudentClassDetails(long accountId, long custId,
			long academicYearId, String status,
			String firstName, String lastName) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.academicYearId = academicYearId;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ViewStudentClassDetails))
			return false;
		return false;

	}

	@Override
	public int hashCode() {
		int result = 0;

		return result;
	}


	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
		buffer.append(" Status : ").append(getStatus());
		return buffer.toString();
	}

	@Override
	public int compareTo(Object object) {
		ViewStudentClassDetails myClass = (ViewStudentClassDetails) object;
		if(ObjectFunctions.isNullOrEmpty(myClass))
			return 0;
		else if(StringFunctions.isNotNullOrEmpty(myClass.getRegisterNumber()) && StringFunctions.isNotNullOrEmpty(this.getRegisterNumber())){
			return this.getRegisterNumber().compareToIgnoreCase(myClass.getRegisterNumber());
		}else
			return this.getPersonFullName().compareToIgnoreCase(myClass.getPersonFullName());	
	}
	
	@Transient
	public String getPersonFullName() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getFirstName())) {
			ret.append(getFirstName());
		}
		/*
		 * if (!StringFunctions.isNullOrEmpty(getFirstName())) {
		 * ret.append(" "); ret.append(getMiddleName()); }
		 */
		if (!StringFunctions.isNullOrEmpty(getLastName())) {
			ret.append(" ");
			ret.append(getLastName());
		}

		return ret.toString().trim();
	}
	
	public long getAccountId() {
		return accountId;
	}

	public long getCustId() {
		return custId;
	}

	public String getStatus() {
		return status;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}


	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	
	@Transient
	public String getClassAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
		}
		return getClassName();
	}

	public long getBoardingPointId() {
		return boardingPointId;
	}

	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	
    @Transient
    public String getAddressForStudent() {
        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        
        if(!StringFunctions.isNullOrEmpty(this.addressLine1)){
        	buffer.append(getAddressLine1());
        }if(!StringFunctions.isNullOrEmpty(this.addressLine2)){
        	buffer.append(", ").append(getAddressLine2());
        }
        if(!StringFunctions.isNullOrEmpty(this.city))
        {
            buffer.append(", ").append(getCity());
        }
        if(!StringFunctions.isNullOrEmpty(this.postalCode))
        {
            buffer.append(", ").append(getPostalCode());
        }
        /*
        if(!StringFunctions.isNullOrEmpty(this.streetName))
        {
            buffer.append(getStreetName());
        }
        if(!StringFunctions.isNullOrEmpty(this.city))
        {
            buffer.append(", ").append(getCity());
        }
        if(!StringFunctions.isNullOrEmpty(this.postalCode))
        {
            buffer.append(", ").append(getPostalCode());
        }*/
        return buffer.toString();
    }
    @Column(name = "committedFee", nullable = false, length = 7,columnDefinition="double default 0")
	public double getCommittedFee() {
		return committedFee;
	}

	public void setCommittedFee(double committedFee) {
		this.committedFee = committedFee;
	}
	
	@Transient
	public String getFullNameAndClassName() {
		StringBuffer ret = new StringBuffer(10);

		if (!StringFunctions.isNullOrEmpty(getPersonFullName())) {
			ret.append(getPersonFullName());
		}
		if (!StringFunctions.isNullOrEmpty(getClassAndSection())) {
			ret.append(" ( ");
			ret.append(getClassAndSection());
			ret.append( " )"); 
		}
		return ret.toString().trim();
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
}
