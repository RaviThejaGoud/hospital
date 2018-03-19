package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;

/**
 * VwStudentstcdetailsId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "vw_transportRequestFormDetails")

public class ViewTransportRequestFormDetails implements Serializable,Cloneable, Comparable {
	// Fields

	/**
	 * 
	 */
	private static final Log log = LogFactory.getLog(ViewTransportRequestFormDetails.class);
	private static final long serialVersionUID = 1L;
	private long studId;
	private long classSectionId;
	private long custId;
	private long academicYearId;
	private String firstName;
	private String lastName;
	private String className;
	private String section;
	private String mobileNumber;
	private String admissionNumber;
	private String organization;
	private String city;
	private String postalCode;
	private String streetName;
	private long rollNumber;
    private String imagePath;
    private String imageName;
    private String thumbNail;
    private String vehicleName;
    private String boardingPointName;
	private String vehicleNumber;
	private double boardingPointFeeAmount;
	private String addressLine1;
	private String routeName;
	
	
	
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getBoardingPointFeeAmount() {
		return boardingPointFeeAmount;
	}
	public void setBoardingPointFeeAmount(double boardingPointFeeAmount) {
		this.boardingPointFeeAmount = boardingPointFeeAmount;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	/** default constructor */
	public ViewTransportRequestFormDetails() {
	}
	public String getBoardingPointName() {
		return boardingPointName;
	}
	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return the thumbNail
	 */
	public String getThumbNail() {
		return thumbNail;
	}
	/**
	 * @param thumbNail the thumbNail to set
	 */
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/** minimal constructor */
	public ViewTransportRequestFormDetails(long studId, long custId, long rollNumber,
			String admissionNumber,String organization) {
		this.studId = studId;
		this.custId = custId;
		this.rollNumber = rollNumber;
		this.admissionNumber = admissionNumber;
		this.organization = organization;
	}

	/** full constructor */
	public ViewTransportRequestFormDetails(long studId, long classSectionId,
			String status, long custId, long rollNumber, long academicYearId,
			long accountId, long classId, String firstName,
			String lastName, 
			String className, String section, 
			 String admissionNumber,
			String organization,
			String boardingPointName,
			String vehicleNumber,
			String vehicleName,
			String addressLine1, String city, String postalCode) {
		this.studId = studId;
		this.rollNumber = rollNumber;
		this.classSectionId = classSectionId;
		this.boardingPointName = boardingPointName;
		this.vehicleNumber = vehicleNumber;
		this.vehicleName = vehicleName;
		this.academicYearId = academicYearId;
		this.custId = custId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.className = className;
		this.section = section;
		this.admissionNumber = admissionNumber;
		this.organization = organization;
	}

	// Property accessors
	@Id
	@Column(name = "studId", nullable = false,updatable=false)
	public long getStudId() {
		return this.studId;
	}

	public void setStudId(long studId) {
		this.studId = studId;
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
	 

	@Column(name = "classSectionId", nullable = false,updatable=false)
	public long getClassSectionId() {
		return this.classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Column(name = "custId", nullable = false,updatable=false)
	public long getCustId() {
		return this.custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	  

	@Column(name = "firstName", length = 128,updatable=false)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", length = 128,updatable=false)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "className",updatable=false)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "section",updatable=false)
	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Column(name = "admissionNumber", nullable = false, length = 128,updatable=false)
	public String getAdmissionNumber() {
		return this.admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
		
	}

	@Column(name = "organization", nullable = false, length = 128,updatable=false)
	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
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
	public String getClassNameAndSection() {
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
		}
		return getClassName();
	}
	@Transient
	public String getPersonFirstLastNameOnly() {
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
	

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof ViewTransportRequestFormDetails))
			return false;
		ViewTransportRequestFormDetails castOther = (ViewTransportRequestFormDetails) other;

		return this.getStudId() == castOther.getStudId() || (this.getStudId() != 0
				&& castOther.getStudId() != 0 && this.getStudId()==
				castOther.getStudId())
				
				&& this.getRollNumber() == castOther.getRollNumber() || (this
						.getRollNumber() != 0
						&& castOther.getRollNumber() != 0 && this
						.getRollNumber() == castOther.getRollNumber())
				&& this.getAcademicYearId() == castOther.getAcademicYearId() || (this
						.getAcademicYearId() != 0
						&& castOther.getAcademicYearId() != 0 && this
						.getAcademicYearId() ==
								castOther.getAcademicYearId())
				&& this.getClassSectionId() == castOther.getClassSectionId() || (this
						.getClassSectionId() != 0
						&& castOther.getClassSectionId() != 0 && this
						.getClassSectionId() ==
								castOther.getClassSectionId())  
				&& this.getCustId() == castOther.getCustId() || (this
						.getCustId() != 0
						&& castOther.getCustId() != 0 && this.getCustId()
						== castOther.getCustId())
				&& this.getFirstName() == castOther.getFirstName() || (this
						.getFirstName() != null
						&& castOther.getFirstName() != null && this
						.getFirstName().equals(castOther.getFirstName()))
				&& this.getLastName() == castOther.getLastName() || (this
						.getLastName() != null
						&& castOther.getLastName() != null && this
						.getLastName().equals(castOther.getLastName()))
				&& this.getClassName() == castOther.getClassName() || (this
						.getClassName() != null
						&& castOther.getClassName() != null && this
						.getClassName().equals(castOther.getClassName()))
				&& this.getSection() == castOther.getSection() || (this
						.getSection() != null
						&& castOther.getSection() != null && this.getSection()
						.equals(castOther.getSection()))
				&& this.getOrganization() == castOther
						.getOrganization() || (this.getOrganization() != null
						&& castOther.getOrganization() != null && this
						.getOrganization().equals(
								castOther.getOrganization()));
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result
				+ (getFirstName() == null ? 0 : this.getFirstName().hashCode());
		result = 37 * result
				+ (getLastName() == null ? 0 : this.getLastName().hashCode());
		result = 37 * result
				+ (getClassName() == null ? 0 : this.getClassName().hashCode());
		result = 37 * result
				+ (getSection() == null ? 0 : this.getSection().hashCode());
		result = 37
				* result
				+ (getAdmissionNumber() == null ? 0 : this.getAdmissionNumber()
						.hashCode());
		result = 37
				* result
				+ (getOrganization() == null ? 0 : this
						.getOrganization().hashCode());
		return result;
	}
	  
	@Transient
	public String getSchoolIdCardAttachmentFilePath() {
	String imagePath=ServletActionContext.getServletContext().getRealPath(getHrefOriginalAttachmentFilePath());
    //String hostUrl = ServletActionContext.getServletContext().getInitParameter("hostUrl");
    //hostUrl = hostUrl + "/images/header/headerPdfPng.png";
    return imagePath;
	}

	@Transient
	public String getAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			log.debug(getImagePath().concat(getThumbNail()));
			return getImagePath().concat(getThumbNail());
		}
		return UserImage.getStudyImageNotFoundFile();
	}

	@Transient
	public String getHrefOriginalAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			return getImagePath().substring(1).concat(getImageName());
		}
		return UserImage.getStudyImageNotFoundFile();
	}
}