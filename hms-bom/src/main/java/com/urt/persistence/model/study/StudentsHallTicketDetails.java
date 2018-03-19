package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;

/**
 * VwStudentstcdetailsId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "vw_studentsHallTicketDetails")
public class StudentsHallTicketDetails implements Serializable,Cloneable, Comparable {

	// Fields

	/**
	 * 
	 */
	private static final Log log = LogFactory.getLog(StudentsHallTicketDetails.class);
	private static final long serialVersionUID = 1L;
	private long studId;
	private long classSectionId;
	private String status;
	private long custId;
	private long academicYearId;
	private long accountId;
	private long classId;
	private String firstName;
	private String lastName;
	private String className;
	private String section;
	private String admissionNumber;
	private String organization;
	private String custAddressLine1;
	private String custCity;
	private String custPostalCode;
	private String custStreetName;
	private String rollNumber;
	private String startTime;
    private String endTime;
    private Date startDate;
    private Date endDate; 
    protected String name;   
    private String examType;
    private String subTypeName;
    private Date examDate;
    private String imagePath;
    private String imageName;
    private String thumbNail;
    private Date dateOfBirth;
    private String motherName;
    private String fatherName;
    private long subTypeId;
    private long examId;
    private String principalDigitalSignaturePath;
    private String registerNumber;
	// Constructors

	

    
    
	/** default constructor */
	public StudentsHallTicketDetails() {
	}
	
	
	public String getPrincipalDigitalSignaturePath() {
		return principalDigitalSignaturePath;
	}


	public void setPrincipalDigitalSignaturePath(
			String principalDigitalSignaturePath) {
		this.principalDigitalSignaturePath = principalDigitalSignaturePath;
	}


	public long getExamId() {
		return examId;
	}


	public void setExamId(long examId) {
		this.examId = examId;
	}


	public long getSubTypeId() {
		return subTypeId;
	}


	public void setSubTypeId(long subTypeId) {
		this.subTypeId = subTypeId;
	}


	/**
	 * @return the examDate
	 */
	public Date getExamDate() {
		return examDate;
	}
	/**
	 * @param examDate the examDate to set
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	
	
	
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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
	/**
	 * @return the examType
	 */
	@Column(name = "examType", nullable = false,updatable=false)
	public String getExamType() {
		return examType;
	}
	/**
	 * @param examType the examType to set
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}
	/**
	 * @return the subTypeName
	 */
	@Column(name = "subTypeName", nullable = false,updatable=false)
	public String getSubTypeName() {
		return subTypeName;
	}
	/**
	 * @param subTypeName the subTypeName to set
	 */
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	@Column(name = "name", length = 60,updatable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "startTime", nullable = false,updatable=false)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Column(name = "endTime", nullable = false,updatable=false)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name = "startDate", nullable = false,updatable=false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "endDate", nullable = false,updatable=false)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	/** minimal constructor */
	public StudentsHallTicketDetails(long studId, long custId, String rollNumber,
			String admissionNumber,String organization) {
		this.studId = studId;
		this.custId = custId;
		this.rollNumber = rollNumber;
		this.admissionNumber = admissionNumber;
		this.organization = organization;
	}

	/** full constructor */
	public StudentsHallTicketDetails(long studId, long classSectionId,
			String status, long custId, String rollNumber, long academicYearId,
			long accountId, long classId, String firstName,
			String lastName, 
			String className, String section, 
			 String admissionNumber,
			String organization,
			String custAddressLine1, String custCity, String custPostalCode) {
		this.studId = studId;
		this.rollNumber = rollNumber;
		this.classSectionId = classSectionId;
		this.status = status;
		this.custId = custId;
		this.academicYearId = academicYearId;
		this.accountId = accountId;
		this.classId = classId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.className = className;
		this.section = section;
		this.admissionNumber = admissionNumber;
		this.organization = organization;
		this.custAddressLine1 = custAddressLine1;
		this.custCity = custCity;
		this.custPostalCode = custPostalCode;
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
	public String getRollNumber() {
		return rollNumber;
	}
	/**
	 * @param rollNumber the rollNumber to set
	 */
	
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	@Column(name = "custStreetName", nullable = false,updatable=false)
	public String getCustStreetName() {
		return custStreetName;
	}

	public void setCustStreetName(String custStreetName) {
		this.custStreetName = custStreetName;
	}

	@Column(name = "classSectionId", nullable = false,updatable=false)
	public long getClassSectionId() {
		return this.classSectionId;
	}

	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}

	@Column(name = "status", length = 1,updatable=false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "custId", nullable = false,updatable=false)
	public long getCustId() {
		return this.custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Column(name = "academicYearId",updatable=false)
	public long getAcademicYearId() {
		return this.academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Column(name = "accountId",updatable=false)
	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "classId",updatable=false)
	public long getClassId() {
		return this.classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
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

	@Column(name = "custAddressLine1", length = 1024,updatable=false)
	public String getCustAddressLine1() {
		return this.custAddressLine1;
	}

	public void setCustAddressLine1(String custAddressLine1) {
		this.custAddressLine1 = custAddressLine1;
	}

	@Column(name = "custCity", length = 64,updatable=false)
	public String getCustCity() {
		return this.custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	@Column(name = "custPostalCode", length = 12,updatable=false)
	public String getCustPostalCode() {
		return this.custPostalCode;
	}

	public void setCustPostalCode(String custPostalCode) {
		this.custPostalCode = custPostalCode;
	}
	
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
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
	
	@Transient
	public String getOrganizationCustAddressLineCityAndPostalCode() {
		StringBuffer buffer = new StringBuffer();
		/*if (StringFunctions.isNotNullOrEmpty(this.organization)) {
			buffer.append(this.organization);
		}*/
		if (StringFunctions.isNotNullOrEmpty(this.custStreetName)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custStreetName);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custAddressLine1)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custAddressLine1);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custCity) && StringFunctions.isNotNullOrEmpty(this.custPostalCode)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity).append("-").append(this.custPostalCode);
		}else if(StringFunctions.isNotNullOrEmpty(this.custCity)){
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity);
		}else{
			if(buffer.length() > 0)
				if(StringFunctions.isNotNullOrEmpty(this.custPostalCode)){
					buffer.append(", ");
					buffer.append(this.custPostalCode);
					buffer.append(". ");
				}else{
					buffer.append(". ");
				}
		}
		return buffer.toString();
	}
	@Transient
	public String getOrganizationAddressLineCityAndPostalCode() {
		StringBuffer buffer = new StringBuffer();
		if (StringFunctions.isNotNullOrEmpty(this.custAddressLine1)) {
			buffer.append(this.custAddressLine1);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custStreetName)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custStreetName);
		}
		if (StringFunctions.isNotNullOrEmpty(this.custCity) && StringFunctions.isNotNullOrEmpty(this.custPostalCode)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity).append("-").append(this.custPostalCode);
		}else if(StringFunctions.isNotNullOrEmpty(this.custCity)){
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.custCity);
		}else{
			if(buffer.length() > 0)
				if(StringFunctions.isNotNullOrEmpty(this.custPostalCode)){
					buffer.append(", ");
					buffer.append(this.custPostalCode);
					buffer.append(". ");
				}else{
					buffer.append(". ");
				}
		}
		return buffer.toString();
	}
	@Transient
	public String getClassNameWithWords(){
		String classNameInWords= getNumbersInWords(getClassName());
		if(StringFunctions.isNullOrEmpty(classNameInWords))
			return "";
		else
			return classNameInWords+" Standard";
	}
	@Transient
	public String getNumbersInWords(String number){
		if(StringFunctions.isNullOrEmpty(number)){
			return "";
		}else{
			if("1".equalsIgnoreCase(number) || "I".equalsIgnoreCase(number))
				return "First";
			else if("2".equalsIgnoreCase(number) || "II".equalsIgnoreCase(number))
				return "Second";
			else if("3".equalsIgnoreCase(number) || "III".equalsIgnoreCase(number))
				return "Third";
			else if("4".equalsIgnoreCase(number) || "IV".equalsIgnoreCase(number))
				return "Fourth";
			else if("5".equalsIgnoreCase(number) || "V".equalsIgnoreCase(number))
				return "Fifth";
			else if("6".equalsIgnoreCase(number) || "VI".equalsIgnoreCase(number))
				return "Sixth";
			else if("7".equalsIgnoreCase(number) || "VII".equalsIgnoreCase(number))
				return "Seventh";
			else if("8".equalsIgnoreCase(number) || "VIII".equalsIgnoreCase(number))
				return "Eight";
			else if("9".equalsIgnoreCase(number) || "IX".equalsIgnoreCase(number))
				return "Ninth";
			else if("10".equalsIgnoreCase(number) || "X".equalsIgnoreCase(number))
				return "Tenth";
			else if("11".equalsIgnoreCase(number) || "XI".equalsIgnoreCase(number))
				return "Eleventh";
			else if("12".equalsIgnoreCase(number) || "XII".equalsIgnoreCase(number))
				return "Twelfth";
			else
				return "";
		}
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
		if (!(other instanceof StudentsHallTicketDetails))
			return false;
		StudentsHallTicketDetails castOther = (StudentsHallTicketDetails) other;

		return this.getStudId() == castOther.getStudId() || (this
				.getStudId() != 0
				&& castOther.getStudId() != 0 && this.getStudId()==
				castOther.getStudId()) 
				&& this.getRollNumber().equalsIgnoreCase(castOther.getRollNumber()) || (!StringFunctions.isNullOrEmpty(this.getRollNumber())
				&& castOther.getRollNumber() != null && this.getRollNumber().equalsIgnoreCase(castOther.getRollNumber()))
				&& this.getClassSectionId() == castOther.getClassSectionId() || (this
						.getClassSectionId() != 0
						&& castOther.getClassSectionId() != 0 && this
						.getClassSectionId() ==
								castOther.getClassSectionId())
				&& this.getStatus() == castOther.getStatus() || (this
						.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus()))
				&& this.getCustId() == castOther.getCustId() || (this
						.getCustId() != 0
						&& castOther.getCustId() != 0 && this.getCustId()
						== castOther.getCustId())
				&& this.getAcademicYearId() == castOther.getAcademicYearId() || (this
						.getAcademicYearId() != 0
						&& castOther.getAcademicYearId() != 0 && this
						.getAcademicYearId()==
								castOther.getAcademicYearId())
				&& this.getAccountId() == castOther.getAccountId() || (this
						.getAccountId() != 0
						&& castOther.getAccountId() != 0 && this
						.getAccountId() == castOther.getAccountId())
				&& this.getClassId() == castOther.getClassId() || (this
						.getClassId() != 0
						&& castOther.getClassId() != 0 && this.getClassId()
						== castOther.getClassId())
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
				&& this.getAdmissionNumber() == castOther
						.getAdmissionNumber() || (this.getAdmissionNumber() != null
						&& castOther.getAdmissionNumber() != null && this
						.getAdmissionNumber().equals(
								castOther.getAdmissionNumber()))
				&& this.getOrganization() == castOther
						.getOrganization() || (this.getOrganization() != null
						&& castOther.getOrganization() != null && this
						.getOrganization().equals(
								castOther.getOrganization()))
				&& this.getCustAddressLine1() == castOther
						.getCustAddressLine1() || (this.getCustAddressLine1() != null
						&& castOther.getCustAddressLine1() != null && this
						.getCustAddressLine1().equals(
								castOther.getCustAddressLine1()))
				&& this.getCustCity() == castOther.getCustCity() || (this
						.getCustCity() != null
						&& castOther.getCustCity() != null && this
						.getCustCity().equals(castOther.getCustCity()))
				&& this.getCustPostalCode() == castOther.getCustPostalCode() || this
						.getCustPostalCode() != null
						&& castOther.getCustPostalCode() != null && this
						.getCustPostalCode().equals(
								castOther.getCustPostalCode());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
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
		result = 37
				* result
				+ (getCustAddressLine1() == null ? 0 : this
						.getCustAddressLine1().hashCode());
		result = 37 * result
				+ (getCustCity() == null ? 0 : this.getCustCity().hashCode());
		result = 37
				* result
				+ (getCustPostalCode() == null ? 0 : this.getCustPostalCode()
						.hashCode());
		return result;
	}
	@Transient
	public String getExamDateAndTime() {
		StringBuffer ret = new StringBuffer(10);

		if (!ObjectFunctions.isNullOrEmpty(getExamDateStr().replace("00:00:00.0", ""))) {
			ret.append(getExamDateStr().replace("00:00:00.0", ""));
		}
		if (!StringFunctions.isNullOrEmpty(getStartTime())) {
			ret.append(" ");
			ret.append(getStartTime());
		}

		return ret.toString().trim().replace("00:00:00.0", "");
	}
	@Transient
    public String getExamDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getExamDate());
    }
	@Transient
    public String getStartDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getStartDate());
    }
	
    @Transient
	public String getSchoolIdCardAttachmentFilePath() {
	//String imagePath=ServletActionContext.getServletContext().getRealPath(getHrefOriginalAttachmentFilePath());
    //String hostUrl = ServletActionContext.getServletContext().getInitParameter("hostUrl");
    //hostUrl = hostUrl + "/images/header/headerPdfPng.png";
    return getHrefOriginalAttachmentFilePath();
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
			//return getImagePath().substring(1).concat(getImageName());
			return getImagePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Transient
    public String getDateOfBirthStr()
    {
		if(!ObjectFunctions.isNullOrEmpty(getDateOfBirth())){
			 return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getDateOfBirth()); 
		}else{
			 return "";
		}
       
    }
	@Transient
	public String getNameAndSubTypeName() {
		if (!StringFunctions.isNullOrEmpty(getSubTypeName())) {
			return getName() + " - " + getSubTypeName();
		}
		return getName();
	}
	
	@Transient
	public String getDigitalSignature() {
		//String imagePath = ServletActionContext.getServletContext().getRealPath("userFiles/"+getCustId()+"/"+getCustId()+"PrincipalSign"+"/"+getCustId()+".png");
		return getPrincipalDigitalSignaturePath();
	}
	
	@Transient
	public String getStaticPhoto() {
		String imagePath = ServletActionContext.getServletContext().getRealPath("/img/custLogo.jpg");
		return imagePath;
	}
	@Transient
	public String getExamTypeAndSubTypeName() {
		if (!StringFunctions.isNullOrEmpty(getSubTypeName())) {
			return getExamType() + " - " + getSubTypeName();
		}
		return getExamType();
	} 
}