package com.urt.persistence.model.study;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AcademicYearVo;
import com.hyniva.sms.ws.vo.ClassNameVO;
import com.hyniva.sms.ws.vo.PersonVO;
import com.hyniva.sms.ws.vo.StudentVo;
import com.hyniva.sms.ws.vo.UserVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.hostel.Bed;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.User;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "student")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)

public class Student extends PersistentObject  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	@MappedExcelObject
	private User account;
	@MappedExcelObject
	private StudyClass classSection;
	private String rollNumber;
	private AcademicYear academicYear;
	//private boolean activeStudent;
	private String status=Constants.YES_STRING;
	private long custId;
	private ClassName classNameClassId;
	protected List<StudentMarks> studentMarks;
	private long categoryId;
	//private long hostelCategoryId;
	//private long transportCategoryId;
	@ExcelField(position = 35)
	protected String transportMode;
	protected String description; 
	private RouteBoardingPoints routeBoardingPoints;
	private Bed bed;
	//protected Long vehicleId;
	protected Long vehicleAcademicDetailsId;
	protected String tempString; 
	protected List feeDetailsList;
	@ExcelField(position = 44)
	protected String registerNumber; 
	
	private boolean joinedThroughAdmissions;
	protected long routeId;
	protected List<StudentAcademicPerformance> studentActivityGrades;
	//private Set<FineFee> fineFee;
	@ExcelField(position = 54)
	private String hostelMode;
	private long roomId;
	@ExcelField(position = 3)
	private String rollNumberStr;
	private Long onlineApplicationDetailsId;
	private Long futureAcademicClassSecId;
	private String feePaidStatus="N";
	private String feeConfigured="N";
	@ExcelField(position = 49)
	private String categoryName;
	protected UserImage profileImage;
	private String shippedSection;
	@ExcelField(position = 58)
	private String outSideSchoolDuty;
	private double committedFee;
	@ExcelField(position = 59)
	private String committedFeeStr;
	@ExcelField(position = 61)
	private String goals;
	@ExcelField(position = 62)
	private String strengths;
	@ExcelField(position = 63)
	private String interestsAndHobbies;
	@ExcelField(position = 64)
	private String responsibilities;
	@ExcelField(position = 65)
	private String achievements;
	@ExcelField(position = 66)
	private String remarks;
	@ExcelField(position = 69)
	private String promoteToClass;
	@ExcelField(position = 75)
	private String schoolMess;
	protected String failurePromotableResons;
	private String errorMsg;
	private long optionalSubjectId;
	private String popUpDisplay;
	private String ptaStatus;
	private String bplStatus;//Below Poverty Line
	private String rteStatus;//Free Education
	private String bplNumber;//Below Poverty Line Number
	private String feeRefundStatus="N";

	
	
	
	
	public String getBplNumber() {
		return bplNumber;
	}

	public void setBplNumber(String bplNumber) {
		this.bplNumber = bplNumber;
	}

	@Column(name = "bplStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getBplStatus() {
		return bplStatus;
	}

	public void setBplStatus(String bplStatus) {
		this.bplStatus = bplStatus;
	}
	@Column(name = "rteStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getRteStatus() {
		return rteStatus;
	}

	public void setRteStatus(String rteStatus) {
		this.rteStatus = rteStatus;
	}

	@Column(name = "ptaStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getPtaStatus() {
		return ptaStatus;
	}

	public void setPtaStatus(String ptaStatus) {
		this.ptaStatus = ptaStatus;
	}
	@Column(name = "popUpDisplay", nullable = true, length = 1,columnDefinition="char(1) default 'P'")
	public String getPopUpDisplay() {
		return popUpDisplay;
	}

	public void setPopUpDisplay(String popUpDisplay) {
		this.popUpDisplay = popUpDisplay;
	}

	public long getOptionalSubjectId() {
		return optionalSubjectId;
	}

	public void setOptionalSubjectId(long optionalSubjectId) {
		this.optionalSubjectId = optionalSubjectId;
	}

	public String getFailurePromotableResons() {
		return failurePromotableResons;
	}

	public void setFailurePromotableResons(String failurePromotableResons) {
		this.failurePromotableResons = failurePromotableResons;
	}

	@Column(name = "schoolMess", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getSchoolMess() {
		return schoolMess;
	}

	public void setSchoolMess(String schoolMess) {
		this.schoolMess = schoolMess;
	}

	/**
	 * @return the goals
	 */
	public String getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}

	/**
	 * @return the strengths
	 */
	public String getStrengths() {
		return strengths;
	}

	/**
	 * @param strengths the strengths to set
	 */
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}

	/**
	 * @return the interestsAndHobbies
	 */
	public String getInterestsAndHobbies() {
		return interestsAndHobbies;
	}

	/**
	 * @param interestsAndHobbies the interestsAndHobbies to set
	 */
	public void setInterestsAndHobbies(String interestsAndHobbies) {
		this.interestsAndHobbies = interestsAndHobbies;
	}

	/**
	 * @return the responsibilities
	 */
	public String getResponsibilities() {
		return responsibilities;
	}

	/**
	 * @param responsibilities the responsibilities to set
	 */
	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	/**
	 * @return the achievements
	 */
	public String getAchievements() {
		return achievements;
	}

	/**
	 * @param achievements the achievements to set
	 */
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;	
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Transient
	public String getCommittedFeeStr() {
		return committedFeeStr;
	}

	public void setCommittedFeeStr(String committedFeeStr) {
		this.committedFeeStr = committedFeeStr;
	}

	@Column(name = "committedFee", nullable = false, length = 7,columnDefinition="double default 0")
	public double getCommittedFee() {
		return committedFee;
	}

	public void setCommittedFee(double committedFee) {
		this.committedFee = committedFee;
	}

	
	@Column(name = "outSideSchoolDuty", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getOutSideSchoolDuty() {
		return outSideSchoolDuty;
	}

	public void setOutSideSchoolDuty(String outSideSchoolDuty) {
		this.outSideSchoolDuty = outSideSchoolDuty;
	}
	public String getShippedSection() {
		return shippedSection;
	}
	public void setShippedSection(String shippedSection) {
		this.shippedSection = shippedSection;
	}
	@ExcelField(position = 55)
	private String isJoinedThroughAdmissionStr;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="imageId", insertable=true, updatable=true) 
	public UserImage getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(UserImage profileImage) {
		this.profileImage = profileImage;
	}
	
	
	/**
	 * @return the categoryName
	 */
	@Transient
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/* "N"- Fee not configured to based on classId and categoryId. "Y" - fee configured to based on classId and categoryId */
	@Column(name = "feeConfigured",  nullable=true, columnDefinition="varchar(10) default 'N'")
	public String getFeeConfigured() {
		return feeConfigured;
	}
	public void setFeeConfigured(String feeConfigured) {
		this.feeConfigured = feeConfigured;
	}
	/* N - "Not Paid" , P - "Partial Paid" , F - "Full Paid" , C - "Committed Fee " */
	@Column(name = "feePaidStatus",  nullable=true, columnDefinition="varchar(10) default 'N'")
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
	/**
	 * @return the onlineApplicationDetailsId
	 */
	public Long getOnlineApplicationDetailsId() {
		return onlineApplicationDetailsId;
	}
	/**
	 * @param onlineApplicationDetailsId the onlineApplicationDetailsId to set
	 */
	public void setOnlineApplicationDetailsId(Long onlineApplicationDetailsId) {
		this.onlineApplicationDetailsId = onlineApplicationDetailsId;
	}
	/**
	 * @return the vehicleAcademicDetailsId
	 */
	public Long getVehicleAcademicDetailsId() {
		return vehicleAcademicDetailsId;
	}
	/**
	 * @param vehicleAcademicDetailsId the vehicleAcademicDetailsId to set
	 */
	public void setVehicleAcademicDetailsId(Long vehicleAcademicDetailsId) {
		this.vehicleAcademicDetailsId = vehicleAcademicDetailsId;
	}
	/**
	 * @return the rollNumberStr
	 */
	@Transient
	public String getRollNumberStr() {
		return rollNumberStr;
	}
	/**
	 * @param rollNumberStr the rollNumberStr to set
	 */
	public void setRollNumberStr(String rollNumberStr) {
		this.rollNumberStr = rollNumberStr;
	}
	@Column(name = "hostelMode", nullable = false, length = 5,columnDefinition="char(1) default 'D'")
	public String getHostelMode() {
		return hostelMode;
	}
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}

	@ExcelField(position = 1)
	private String stuId;
	
	@Transient
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	@Transient
	public String getIsJoinedThroughAdmissionStr() {
		return isJoinedThroughAdmissionStr;
	}

	public void setIsJoinedThroughAdmissionStr(String isJoinedThroughAdmissionStr) {
		this.isJoinedThroughAdmissionStr = isJoinedThroughAdmissionStr;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="studId")
	public List<StudentAcademicPerformance> getStudentActivityGrades() {
		return studentActivityGrades;
	}
	public void setStudentActivityGrades(
			List<StudentAcademicPerformance> studentActivityGrades) {
		this.studentActivityGrades = studentActivityGrades;
	}
	
	@Column(name = "joinedThroughAdmissions", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	@Type(type="yes_no")
	public boolean isJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}
	public void setJoinedThroughAdmissions(boolean joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
	}
	/**
	 * @return the bed
	 */
	@OneToOne(cascade=CascadeType.ALL)
	    @JoinColumn(name="bedId", insertable=true, updatable=true)
	public Bed getBed() {
	    return bed;
	}
	/**
	 * @param bed the bed to set
	 */
	public void setBed(Bed bed) {
	    this.bed = bed;
	}
   @Column(name = "roomId", columnDefinition = "int default 0")
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Transient
	public String getStudentName()
	{
		if(ObjectFunctions.isNullOrEmpty(getAccount()))
		{
			return "";
		}
		return getAccount().getFullPersonName();
	}
	/*@Column(name = "transportCategoryId", nullable = false, updatable = true, columnDefinition = "int default 0")
	public long getTransportCategoryId() {
		return transportCategoryId;
	}
	public void setTransportCategoryId(long transportCategoryId) {
		this.transportCategoryId = transportCategoryId;
	}*/
	/**
	 * @return the hostelCategoryId
	 *//*
	@Column(name = "hostelCategoryId", columnDefinition = "int default 0")
	public long getHostelCategoryId() {
		return hostelCategoryId;
	}
	*//**
	 * @param hostelCategoryId the hostelCategoryId to set
	 *//*
	public void setHostelCategoryId(long hostelCategoryId) {
		this.hostelCategoryId = hostelCategoryId;
	}*/
	/**
	 * @return the categoryId
	 */
	@Column(name = "categoryId", nullable = false, updatable = true, columnDefinition = "int default 0")
	public long getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	@Transient
	public String getClassAndSection() {
		if(ObjectFunctions.isNullOrEmpty(this.getClassSection()))
		{
			return "";
		}
		return this.getClassSection().getClassAndSection();
	}
	@Column(name = "transportMode", nullable = true, length = 5,columnDefinition="char(1) default 'O'")
	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	/**
	 * @return the accountId
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="accountId", insertable=true, updatable=true)
	@PrimaryKeyJoinColumn(name = "accountId", referencedColumnName = "id")
	public User getAccount() {
		return account;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccount(User account) {
		this.account = account;
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
	
	/**
	 * Constructor for Student.
	 */
	public Student() {
		super();
		setStatus(Constants.YES_STRING);
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Student)) {
			return false;
		} else {

			Student student = (Student) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder
				.append(
					account,
					student.getAccount())
				.append(classSection, student.getClassSection())
				;

			return builder.isEquals();
		}
	}

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	/* Changed by seshu on 4 Apr for import student sheet end row purpose 
	  */
	public String toString() {
		try {
			if (StringFunctions
					.isNullOrEmpty(this.account.getAdmissionNumber()))
				return "";
			if(StringFunctions.isNullOrEmpty(this.account.getPerson().getFirstName()))
				return "";
			
			StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
			buffer.append(getId()).append(" ");
			buffer.append(classSection).append(" ");
			buffer.append(account).append(" ");
			buffer.append(academicYear);
			return buffer.toString();
		} catch (Exception ex) {
			return "";
		}
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895).append(this.account)
                .append(this.classSection).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    //For Sorting students  alphabetically -- seshu
    public int compareTo(Object object) {
        Student stud = (Student) object;
        if(ObjectFunctions.isNullOrEmpty(stud)){
        	return 0;
        }else
        	return this.getStudentName().compareToIgnoreCase(stud.getStudentName());
        /*return new CompareToBuilder().append(this.classSection, myClass.classSection)
                .append(this.account, myClass.getAccount()).append(this.academicYear, myClass.getAcademicYear()).toComparison();*/
    }
   	
	@Transient
    public String getStudentId()
    {
        return "S"+getStrId();
    }
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="studId") 
	public List<StudentMarks> getStudentMarks() {
		return studentMarks;
	}
	public void setStudentMarks(List<StudentMarks> studentMarks) {
		this.studentMarks = studentMarks;
	}
	
	
	@OneToOne()
	@JoinColumn(name="classNameClassId", insertable=true, updatable=true) 
	public ClassName getClassNameClassId() {
		return classNameClassId;
	}
	public void setClassNameClassId(ClassName classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
	
	@OneToOne()
	@JoinColumn(name="classSectionId", insertable=true, updatable=true) 
	public StudyClass getClassSection() {
		return classSection;
	}
	
	public void setClassSection(StudyClass classSection) {
		this.classSection = classSection;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true) 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	public void addStudentActivitiesGrades(StudentAcademicPerformance academicPerformance) {
		if(ObjectFunctions.isNullOrEmpty(this.studentActivityGrades)){
			this.studentActivityGrades=new ArrayList<StudentAcademicPerformance>();
		}
		this.studentActivityGrades.add(academicPerformance);
	}
	
	public void addStudentMarks(StudentMarks studentMarks) {
		if(ObjectFunctions.isNullOrEmpty(this.studentMarks)){
			this.studentMarks=new ArrayList<StudentMarks>();
		}
		this.studentMarks.add(studentMarks);
	}
	
	public void removeStudentMarks(List studentMarks) {       
		if(ObjectFunctions.isNullOrEmpty(this.studentMarks)){
			this.studentMarks=new ArrayList<StudentMarks>();
		}
		this.studentMarks.removeAll(studentMarks);
	}
	
	@Transient
	public String getTransportModeDecription()
	{
		if(!StringFunctions.isNullOrEmpty(getTransportMode()))
		{
			if("O".equalsIgnoreCase(getTransportMode()))
			{
				return "Own";
			}
			else if("P".equalsIgnoreCase(getTransportMode()))
			{
				return "Private";
			}
			else if("T".equalsIgnoreCase(getTransportMode()))
			{
				return "School Transport";
			}
		}
		return null;
	}
	
	/**
	 * @return the activeStudent
	 *//*
    @Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isActiveStudent() {
		return activeStudent;
	}
	*//**
	 * @param activeStudent the activeStudent to set
	 *//*
	public void setActiveStudent(boolean activeStudent) {
		this.activeStudent = activeStudent;
	}*/
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'") 
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getStudentImage()
	{
		if(ObjectFunctions.isNullOrEmpty(getAccount().getProfileImage()))
		{
			return "/images/common/photo_notAvailable.jpg"; 
		}
		return getAccount().getProfileImage().getAdjustedAttachmentFilePath();
	}
	
	@Transient
	public String getStudentAacademicImage()
	{
		if(ObjectFunctions.isNullOrEmpty(getProfileImage()))
		{
			return "/images/common/photo_notAvailable.jpg"; 
		}
		//return getProfileImage().getAdjustedAttachmentFilePath();
		return getProfileImage().getPath();
	}
	// For SMSAPP *** Ramarao
	@Transient
	public String getStudentAacademicImagePath() {
		if(ObjectFunctions.isNullOrEmpty(getProfileImage())) {
			return "/images/common/photo_notAvailable.jpg"; 
		}
		return getProfileImage().getCropAttachmentFilePath();
	}
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(this.academicYear))
			return 0;
		else
			return this.academicYear.getId();
	}
	@Transient
	public long getClassSectionId(){
		if(ObjectFunctions.isNullOrEmpty(this.classSection))
			return 0;
		else
			return this.classSection.getId();
	}
	@Transient
	public long getClassNameId(){
		if(ObjectFunctions.isNullOrEmpty(this.classNameClassId))
			return 0;
		else
			return this.classNameClassId.getId();
	}
	
	 @OneToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name="boardingPointId", insertable=true, updatable=true)
	public RouteBoardingPoints getRouteBoardingPoints() {
		return routeBoardingPoints;
	}
	public void setRouteBoardingPoints(RouteBoardingPoints routeBoardingPoints) {
		this.routeBoardingPoints = routeBoardingPoints;
	}
	@Transient
	public String getBoardingPoint() {
		if(ObjectFunctions.isNullOrEmpty(this.routeBoardingPoints))
		{
			return "";
		}
		return this.routeBoardingPoints.getBoardingPointName();
	}
	
	/*public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}*/
	/**
	 * @return the tempString
	 */
	@Transient
	public String getTempString() {
		return tempString;
	}
	/**
	 * @param tempString the tempString to set
	 */
	public void setTempString(String tempString) {
		this.tempString = tempString;
	}
	@Transient
	public String getMedium(){
		if(ObjectFunctions.isNullOrEmpty(getClassSection())){
			return "";
		}else if(ObjectFunctions.isNullOrEmpty(getClassSection().getMediumId())){
			return "";
		}else
			return getClassSection().getMediumId().getName();
	}
	public void copyFrom(Student obj)
    {
		setAccount(obj.getAccount());
		setCategoryId(obj.getCategoryId());
		setCreatedDate(new Date());
		setCustId(obj.getCustId());
		//setHostelCategoryId(obj.getHostelCategoryId());
		setLastAccessDate(new Date());
		setLastUpdatedDate(new Date());
		//setTransportCategoryId(obj.getTransportCategoryId());
		setTransportMode(obj.getTransportMode());
		setHostelMode(obj.getHostelMode());
		setPtaStatus(obj.getPtaStatus());
		setBplStatus(obj.getBplStatus());
		setRteStatus(obj.getRteStatus());
    }
	
	
	/**
	 * @return the feeDetailsList
	 */
	@Transient
	public List getFeeDetailsList() {
		if(ObjectFunctions.isNullOrEmpty(this.feeDetailsList)){
			this.feeDetailsList=new ArrayList();
		}
		return feeDetailsList;
	}
	/**
	 * @param feeDetailsList the feeDetailsList to set
	 */
	public void setFeeDetailsList(List feeDetailsList) {
		this.feeDetailsList = feeDetailsList;
	}
	
	/*public List getTempList() {
		if(ObjectFunctions.isNullOrEmpty(this.tempList)){
			this.tempList=new ArrayList();
		}
		return this.tempList;
	}
	public void setTempList(List tempList) {
		this.tempList = tempList;
	}*/
	@Column(name = "registerNumber", nullable = true, length = 20)
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	@Column(name = "feeRefundStatus", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
	public String getFeeRefundStatus() {
		return feeRefundStatus;
	}

	public void setFeeRefundStatus(String feeRefundStatus) {
		this.feeRefundStatus = feeRefundStatus;
	}
	@Transient
	public String getDateOfJoiningStu()
	{
		if(ObjectFunctions.isNullOrEmpty(getAccount().getPerson().getDateOfJoiningStr()))
		{
			return "";
		}
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getAccount().getPerson().getDateOfJoiningStr()); 
	}
	@Transient
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	@Transient
	public String getStudentNameAndUserName() {
		StringBuffer name = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(getStudentName()))
			name.append(getStudentName());
		if(!ObjectFunctions.isNullOrEmpty(this.account) && StringFunctions.isNotNullOrEmpty(this.account.getUsername()))
			name.append(" (").append(this.account.getUsername()).append(")").toString();
		return name.toString();
	}
	@Transient
	public String getStudAdmissionNumber(){
		if(ObjectFunctions.isNullOrEmpty(this.account))
			return "";
		return this.account.getAdmissionNumber();
			
	}
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="studId")
	public Set<FineFee> getFineFee() {
		return fineFee;
	}
	public void setFineFee(Set<FineFee> fineFee) {
		this.fineFee = fineFee;
	}
	
	 public void addFineFee(FineFee fineFee) {
	        if(ObjectFunctions.isNullOrEmpty(getFineFee())){
	          this.fineFee=new HashSet<FineFee>();
	        }
	        getFineFee().add(fineFee);
	}*/
	 @Transient
	 public long getStudentBoardingPointId(){
		 if(ObjectFunctions.isNullOrEmpty(this.routeBoardingPoints))
			 return 0;
		 else
			 return this.routeBoardingPoints.getId();
	 }
	 @Transient
		public String getAccountIdClassSectionIdAcademicYearIdAndCustId() {
			return  getAccount().getId()+"_"+getClassSectionId()+"_"+getAcademicYearId()+"_"+getCustId();
		}
	 /**
		 * @return the promoteToClass
		 */
		public String getPromoteToClass() {
			return promoteToClass;	
		}

		/**
		 * @param promoteToClass the promoteToClass to set
		 */
		public void setPromoteToClass(String promoteToClass) {
			this.promoteToClass = promoteToClass;
		}

		@Transient
		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}
		
		@Transient
		public Student copyFromVoToEntity(Student student, StudentVo studentVo)
		{
			student.id = studentVo.id;
			//student.account = studentVo.account;
			//student.classSection = studentVo.classSection;
			student.rollNumber = studentVo.rollNumber;
			//student.academicYear = studentVo.academicYear;
			//student.activeStudent = studentVo.activeStudent;
			student.custId = studentVo.custId;
			//student.classNameClassId = studentVo.classNameClassId;
			//student.studentMarks = studentVo.studentMarks;
			student.categoryId = studentVo.categoryId;
			student.transportMode = studentVo.transportMode;
			student.description = studentVo.description;
			//student.routeBoardingPoints = studentVo.routeBoardingPoints;
			//student.bed = studentVo.bed;
			student.vehicleAcademicDetailsId = studentVo.vehicleAcademicDetailsId;
			student.tempString = studentVo.tempString;
			student.feeDetailsList = studentVo.feeDetailsList;
			student.registerNumber = studentVo.registerNumber;
			student.joinedThroughAdmissions = studentVo.joinedThroughAdmissions;
			student.routeId = studentVo.routeId;
			//student.studentActivityGrades = studentVo.studentActivityGrades;
			//student.fineFee = studentVo.fineFee;
			student.hostelMode = studentVo.hostelMode;
			student.roomId = studentVo.roomId;
			student.rollNumberStr = studentVo.rollNumberStr;
			student.onlineApplicationDetailsId = studentVo.onlineApplicationDetailsId;
			student.futureAcademicClassSecId = studentVo.futureAcademicClassSecId;
			student.feePaidStatus = studentVo.feePaidStatus;
			student.feeConfigured = studentVo.feeConfigured;
			student.categoryName = studentVo.categoryName;
			//student.profileImage = studentVo.profileImage;
			student.shippedSection = studentVo.shippedSection;
			student.outSideSchoolDuty = studentVo.outSideSchoolDuty;
			student.committedFee = studentVo.committedFee;
			student.committedFeeStr = studentVo.committedFeeStr;
			student.goals = studentVo.goals;
			student.strengths = studentVo.strengths;
			student.interestsAndHobbies = studentVo.interestsAndHobbies;
			student.responsibilities = studentVo.responsibilities;
			student.achievements = studentVo.achievements;
			student.remarks = studentVo.remarks;
			student.promoteToClass = studentVo.promoteToClass;
			student.schoolMess = studentVo.schoolMess;
			student.errorMsg = studentVo.errorMsg;
			student.ptaStatus = studentVo.ptaStatus;
			student.bplStatus = studentVo.bplStatus;
			student.rteStatus = studentVo.rteStatus;
			student.bplNumber = studentVo.bplNumber;
			
			//student.isJoinedThroughAdmissionStr = studentVo.isJoinedThroughAdmissionStr;
			
			return student;
			
		}
		@Transient
		public StudentVo copyFromEntityToVo(Student student)
		{
			StudentVo studentVo = new StudentVo();
			
			studentVo.id = student.id;
			//studentVo.account = student.account;
			//studentVo.classSection = student.classSection;
			studentVo.rollNumber = student.rollNumber;
			//studentVo.academicYear = student.academicYear;
			studentVo.status = student.status;
			studentVo.custId = student.custId;
			//studentVo.classNameClassId = student.classNameClassId;
			//studentVo.studentMarks = student.studentMarks;
			studentVo.categoryId = student.categoryId;
			studentVo.transportMode = student.transportMode;
			studentVo.description = student.description;
			//studentVo.routeBoardingPoints = student.routeBoardingPoints;
			//studentVo.bed = student.bed;
			studentVo.vehicleAcademicDetailsId = student.vehicleAcademicDetailsId;
			studentVo.tempString = student.tempString;
			studentVo.feeDetailsList = student.feeDetailsList;
			studentVo.registerNumber = student.registerNumber;
			studentVo.joinedThroughAdmissions = student.joinedThroughAdmissions;
			studentVo.routeId = student.routeId;
			//studentVo.studentActivityGrades = student.studentActivityGrades;
			//studentVo.fineFee = student.fineFee;
			studentVo.hostelMode = student.hostelMode;
			studentVo.roomId = student.roomId;
			studentVo.rollNumberStr = student.rollNumberStr;
			studentVo.onlineApplicationDetailsId = student.onlineApplicationDetailsId;
			studentVo.futureAcademicClassSecId = student.futureAcademicClassSecId;
			studentVo.feePaidStatus = student.feePaidStatus;
			studentVo.feeConfigured = student.feeConfigured;
			studentVo.categoryName = student.categoryName;
			//studentVo.profileImage = student.profileImage;
			studentVo.shippedSection = student.shippedSection;
			studentVo.outSideSchoolDuty = student.outSideSchoolDuty;
			studentVo.committedFee = student.committedFee;
			studentVo.committedFeeStr = student.committedFeeStr;
			studentVo.goals = student.goals;
			studentVo.strengths = student.strengths;
			studentVo.interestsAndHobbies = student.interestsAndHobbies;
			studentVo.responsibilities = student.responsibilities;
			studentVo.achievements = student.achievements;
			studentVo.remarks = student.remarks;
			studentVo.promoteToClass = student.promoteToClass;
			studentVo.schoolMess = student.schoolMess;
			studentVo.errorMsg = student.errorMsg;
			
			studentVo.studentAacademicImage = student.getStudentAacademicImage();
			studentVo.studentName = student.getStudentName();
			studentVo.classAndSection = student.getClassAndSection();
			studentVo.dateOfJoiningStu = student.getDateOfJoiningStu();
			studentVo.studAdmissionNumber = student.getStudAdmissionNumber();
			studentVo.ptaStatus = student.ptaStatus;
			studentVo.bplStatus = student.bplStatus;
			studentVo.rteStatus = student.rteStatus;
			studentVo.bplNumber = student.bplNumber;
			
			
			//studentVo.isJoinedThroughAdmissionStr = student.isJoinedThroughAdmissionStr;
			
			return studentVo;
			
		}
		
	@Transient
	public String getFullFormattedName() {
		StringBuffer ret = new StringBuffer(10);
		if (!StringFunctions.isNullOrEmpty(this.getAccount().getPerson().getFirstName())) {
			ret.append(this.getAccount().getPerson().getFirstName());
		}
		if (!StringFunctions.isNullOrEmpty(this.getAccount().getPerson().getLastName())) {
			ret.append(" ");
			ret.append(this.getAccount().getPerson().getLastName());
		}
		return ret.toString().trim();
	}
	
	@Transient
    public String getStudProfileOrAdminImageFilePath()
    {
		 if(!ObjectFunctions.isNullOrEmpty(getAccount().getProfileImage()))
		 {
			if(!StringFunctions.isNullOrEmpty(getAccount().getProfileImage().getPath()))
	        {
	        	return getAccount().getProfileImage().getPath();
	        }
		 }
		 else
		 {
			 if(!ObjectFunctions.isNullOrEmpty(getProfileImage()))
			 {
				if(!StringFunctions.isNullOrEmpty(getProfileImage().getPath()))
		        {
		        	return getProfileImage().getPath();
		        }
			 }
		 }
        return UserImage.getStudyImageNotFoundFile();
    }
	
	public StudentVo deepCopyEntityToVO(Student student)
	{
		StudentVo studentVo = new StudentVo();
		if(!ObjectFunctions.isNullOrEmpty(student))
		{
			AcademicYearVo academicYearVo = null;
			ClassNameVO classNameVO = null;
			UserVO userVo = null;
			if(!ObjectFunctions.isNullOrEmpty(student.getAccount())){
				User account = student.getAccount();
				userVo = account.copyFromEntityToVo(account);
				
				if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getPerson()))
				{
					Person person = student.getAccount().getPerson();
					PersonVO personVo = person.copyFromEntityToVo(person);
					userVo.setPersonVo(personVo);
					person=null;
					personVo=null;
				}
				if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getPrimaryAddress()))
				{
					Address primaryAddress = student.getAccount().getPrimaryAddress();
					userVo.setPrimaryAddressVo(primaryAddress.copyFromEntityToVo(primaryAddress));
					primaryAddress=null;
				}
				if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getCommunicationAddress()))
				{
					 Address communicationAddress = student.getAccount().getCommunicationAddress();
					 userVo.setCommunicationAddressVo(communicationAddress.copyFromEntityToVo(communicationAddress));
					 communicationAddress=null;
				}
				
				if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getTempararyAddress()))
				{
					Address tempararyAddress = student.getAccount().getTempararyAddress();
				    userVo.setTempararyAddressVo(tempararyAddress.copyFromEntityToVo(tempararyAddress));
				    tempararyAddress=null;
				}
			}
			studentVo = student.copyFromEntityToVo(student);
			if(!ObjectFunctions.isNullOrEmpty(student.getAcademicYear()))
			{
				AcademicYear academicYear = student.getAcademicYear();
				academicYearVo = academicYear.copyFromEntityToVo(academicYear);
				studentVo.setAcademicYearVo(academicYearVo);
				academicYear = null;
			}
			if(!ObjectFunctions.isNullOrEmpty(student.getClassNameClassId()))
			{
				ClassName className = student.getClassNameClassId();
				classNameVO = className.copyFromEntityToVo(className, academicYearVo);
				studentVo.setClassNameVo(classNameVO);
				className = null;
			}
			if(!ObjectFunctions.isNullOrEmpty(student.getClassSection()))
			{
				StudyClass studyClass = student.getClassSection();
				studentVo.setStudyClassVo(studyClass.copyFromEntityToVo(studyClass, academicYearVo, classNameVO));
				studyClass = null;
			}
			
			if (!ObjectFunctions.isNullOrEmpty(student.getProfileImage())) 
			{
				UserImage profileImage = new UserImage();
				studentVo.setProfileImage(profileImage.copyFromEntityToVo(student.getProfileImage()));
			}
			if (!ObjectFunctions.isNullOrEmpty(student.getBed())) 
			{
				Bed bed = new Bed();
				studentVo.setBedVo(bed.copyFromEntityToVo(student.getBed()));
			}
			if (!ObjectFunctions.isNullOrEmpty(student.getRouteBoardingPoints())) 
			{
				RouteBoardingPoints routeBoardingPoints = new RouteBoardingPoints();
				studentVo.setRouteBoardingPointsVO(routeBoardingPoints.copyFromEntityToVo(student.getRouteBoardingPoints()));
			}
			
			studentVo.setAccount(userVo);
			userVo=null;
			academicYearVo=null;
		}
		return studentVo;
	}
}
