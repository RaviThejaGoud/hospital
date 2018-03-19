package com.urt.persistence.model.study;
import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.ServletActionContext;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;

/**
 * VwStudentstcdetailsId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "vw_studentsScoreCardProfileDetails")
public class ViewStudentsScoreCardProfileDetails implements Serializable,Cloneable, Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 457522654454L;
	private int rollNumber;
	private String fullName;
	private String className;
	private String section;
	private Date dateOfBirth;
	private String admissionNumber;
	private String fatherName;
	private String motherName;
	private String streetName;
	private String city;
	private String postalCode;
	private String studentEmail;
	private String mobileNumber;
	private String phoneNumber;
	private String academicYear;
	private long custId;
	private long academicYearId;
	private long studId;
	private long classSectionId;
	private double totalWorkingDaysCount;
	private double presentDaysCount;
	private double height;
	private double weight;
	private String visionL;
	private String visionR;
	private String teeth;
	private String oralHygiene;
	private String bloodGroup;
	private String schoolCode;
	private String organizationName;
	private String custAddressLine1;
	private String custStreetName;
	private String custCity;
	private String custPostalCode;
	private String imageName;
	private String imagePath;
	private String thumbNail;
	private String studDiscontinueDesc;
	private String birthOfStudentYear;
	private String birthOfStudentMonth;
	private String registerNumber;
	private double term1WorkingDaysCount;
	private double term2WorkingDaysCount;
	private double term1Percentage;
	private double term2Percentage;	
	private Date schoolReOpenDate;
	private String parentEmail;
	private long accountId;
	private String goals;
	private String strengths;
	private String interestsAndHobbies;
	private String responsibilities;
	private String achievements;
	private String promoteToClass;
	
	
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
	/**
	 * @return the schoolReOpenDate
	 */
	public Date getSchoolReOpenDate() {
		return schoolReOpenDate;
	}
	/**
	 * @param schoolReOpenDate the schoolReOpenDate to set
	 */
	public void setSchoolReOpenDate(Date schoolReOpenDate) {
		this.schoolReOpenDate = schoolReOpenDate;
	}
	/**
	 * @return the term1WorkingDaysCount
	 */
	@Transient
	public double getTerm1WorkingDaysCount() {
		return term1WorkingDaysCount;
	}
	/**
	 * @param term1WorkingDaysCount the term1WorkingDaysCount to set
	 */
	public void setTerm1WorkingDaysCount(double term1WorkingDaysCount) {
		this.term1WorkingDaysCount = term1WorkingDaysCount;
	}
	/**
	 * @return the term2WorkingDaysCount
	 */
	@Transient
	public double getTerm2WorkingDaysCount() {
		return term2WorkingDaysCount;
	}
	/**
	 * @param term2WorkingDaysCount the term2WorkingDaysCount to set
	 */
	public void setTerm2WorkingDaysCount(double term2WorkingDaysCount) {
		this.term2WorkingDaysCount = term2WorkingDaysCount;
	}
	/**
	 * @return the term1Percentage
	 */
	@Transient
	public double getTerm1Percentage() {
		return term1Percentage;
	}
	/**
	 * @param term1Percentage the term1Percentage to set
	 */
	public void setTerm1Percentage(double term1Percentage) {
		this.term1Percentage = term1Percentage;
	}
	/**
	 * @return the term2Percentage
	 */
	@Transient
	public double getTerm2Percentage() {
		return term2Percentage;
	}
	/**
	 * @param term2Percentage the term2Percentage to set
	 */
	public void setTerm2Percentage(double term2Percentage) {
		this.term2Percentage = term2Percentage;
	}
	
	
	/**
	 * @return the registerNumber
	 */
	public String getRegisterNumber() {
		return registerNumber;
	}

	/**
	 * @param registerNumber the registerNumber to set
	 */
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
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
	 * @return the schoolCode
	 */
	public String getSchoolCode() {
		return schoolCode;
	}

	/**
	 * @param schoolCode the schoolCode to set
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * @return the custAddressLine1
	 */
	public String getCustAddressLine1() {
		return custAddressLine1;
	}

	/**
	 * @param custAddressLine1 the custAddressLine1 to set
	 */
	public void setCustAddressLine1(String custAddressLine1) {
		this.custAddressLine1 = custAddressLine1;
	}

	/**
	 * @return the custStreetName
	 */
	public String getCustStreetName() {
		return custStreetName;
	}

	/**
	 * @param custStreetName the custStreetName to set
	 */
	public void setCustStreetName(String custStreetName) {
		this.custStreetName = custStreetName;
	}

	/**
	 * @return the custCity
	 */
	public String getCustCity() {
		return custCity;
	}

	/**
	 * @param custCity the custCity to set
	 */
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	/**
	 * @return the custPostalCode
	 */
	public String getCustPostalCode() {
		return custPostalCode;
	}

	/**
	 * @param custPostalCode the custPostalCode to set
	 */
	public void setCustPostalCode(String custPostalCode) {
		this.custPostalCode = custPostalCode;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the visionL
	 */
	public String getVisionL() {
		return visionL;
	}

	/**
	 * @param visionR the visionR to set
	 */
	public void setVisionR(String visionR) {
		this.visionL = visionR;
	}
	/**
	 * @return the visionR
	 */
	public String getVisionR() {
		return visionR;
	}

	/**
	 * @param visionL the visionL to set
	 */
	public void setVisionL(String visionL) {
		this.visionL = visionL;
	}

	/**
	 * @return the teeth
	 */
	public String getTeeth() {
		return teeth;
	}

	/**
	 * @param teeth the teeth to set
	 */
	public void setTeeth(String teeth) {
		this.teeth = teeth;
	}

	/**
	 * @return the oralHygiene
	 */
	public String getOralHygiene() {
		return oralHygiene;
	}

	/**
	 * @param oralHygiene the oralHygiene to set
	 */
	public void setOralHygiene(String oralHygiene) {
		this.oralHygiene = oralHygiene;
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
	 * @return the academicYear
	 */
	public String getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * @return the studId
	 */
	@Id
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
	 * @return the rollNumber
	 */
	public int getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
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
	 * @return the admissionNumber
	 */
	@Column(name = "admissionNumber", nullable = true,updatable=true)
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
	 * @return the studentEmail
	 */
	public String getStudentEmail() {
		return studentEmail;
	}

	/**
	 * @param studentEmail the studentEmail to set
	 */
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "goals", nullable = true,updatable=true, length = 250)
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getStrengths() {
		return strengths;
	}
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}
	public String getInterestsAndHobbies() {
		return interestsAndHobbies;
	}
	public void setInterestsAndHobbies(String interestsAndHobbies) {
		this.interestsAndHobbies = interestsAndHobbies;
	}
	public String getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}
	public String getAchievements() {
		return achievements;
	}
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	public String getPromoteToClass() {
		return promoteToClass;
	}
	public void setPromoteToClass(String promoteToClass) {
		this.promoteToClass = promoteToClass;
	}
	@Transient
	public String getClassAndSection(){
		if (!StringFunctions.isNullOrEmpty(getSection())) {
			return getClassName() + " - " + getSection();
		}
		return getClassName();
	}
	
	@Transient
	public String getDateOfBirthStr(){
		if(ObjectFunctions.isNullOrEmpty(this.dateOfBirth))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.dateOfBirth);
	}
	
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return the totalWorkingDaysCount
	 */
	@Transient
	public double getTotalWorkingDaysCount() {
		return totalWorkingDaysCount;
	}

	/**
	 * @param totalWorkingDaysCount the totalWorkingDaysCount to set
	 */
	public void setTotalWorkingDaysCount(double totalWorkingDaysCount) {
		this.totalWorkingDaysCount = totalWorkingDaysCount;
	}

	/**
	 * @return the presentDaysCount
	 */
	@Transient
	public double getPresentDaysCount() {
		return presentDaysCount;
	}

	/**
	 * @param presentDaysCount the presentDaysCount to set
	 */
	public void setPresentDaysCount(double presentDaysCount) {
		this.presentDaysCount = presentDaysCount;
	}
	@Transient
	public String getStudentAddress() {
		StringBuffer buffer = new StringBuffer();
		if (StringFunctions.isNotNullOrEmpty(this.streetName)) {
			buffer.append(this.streetName);
		}
		if (StringFunctions.isNotNullOrEmpty(this.city) && StringFunctions.isNotNullOrEmpty(this.postalCode)) {
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.city).append("-").append(this.postalCode);
		}else if(StringFunctions.isNotNullOrEmpty(this.city)){
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.city);
		}else{
			if(buffer.length() > 0)
				buffer.append(", ");
			buffer.append(this.postalCode);
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
				buffer.append(", ");
			buffer.append(this.custPostalCode);
		}
		return buffer.toString();
	}
	@Transient
	public String getHrefOriginalAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			return getImagePath().substring(1).concat(getImageName());
		}
		return UserImage.getStudyImageNotFoundFile();
	}
	
	@Transient
	public String getSchoolIdCardAttachmentFilePath() {
		String imagePath=ServletActionContext.getServletContext().getRealPath(getHrefOriginalAttachmentFilePath());
		File file=new File(imagePath);
		if(!file.exists()){
			imagePath=ServletActionContext.getServletContext().getRealPath("/images/common/photo_notAvailable.jpg"); 
		}
	    return imagePath;
	}
	 public int[] calculateDurationBetweenTwoDatesInYrsMntsdays(Date date1, Date date2) {
		 int[] monthDay = { 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		 int[] ageDiffArr = new int[3];
		 Calendar fromDate;
		 Calendar toDate;
		 int increment = 0;
		 int year;
		 int month;
		 int day;
		 Calendar d1 = new GregorianCalendar().getInstance();
		 d1.setTime(date1);
		 Calendar d2 = new GregorianCalendar().getInstance();
		 d2.setTime(date2);
		 //From the two dates, we identify which date is bigger. The bigger one is set as toDate and the smaller one is fromDate so that we always get a positive duration. 
		 if (d1.getTime().getTime() > d2.getTime().getTime()) {
			 fromDate = d2;
			 toDate = d1;
		 } else {
			 fromDate = d1;
			 toDate = d2;
		 }
		 //Day Calculation 
		 
		 if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
			 increment = monthDay[fromDate.get(Calendar.MONTH)];
		 }

		 GregorianCalendar cal = new GregorianCalendar();
		 boolean isLeapYear = cal.isLeapYear(fromDate.get(Calendar.YEAR));

		 if (increment == -1) {
			 if (isLeapYear) {
				 increment = 29;
			 } else {
				 increment = 28;
			 }
		 }

		 // DAY CALCULATION
		 if (increment != 0) {
		 day = (toDate.get(Calendar.DAY_OF_MONTH) + increment) - fromDate.get(Calendar.DAY_OF_MONTH);
		 increment = 1;
		 } else {
		 day = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH);
		 }

		 // MONTH CALCULATION
		 if ((fromDate.get(Calendar.MONTH) + increment) > toDate.get(Calendar.MONTH)) {
		 month = (toDate.get(Calendar.MONTH) + 12) - (fromDate.get(Calendar.MONTH) + increment);
		 increment = 1;
		 } else {
		 month = (toDate.get(Calendar.MONTH)) - (fromDate.get(Calendar.MONTH) + increment);
		 increment = 0;
		 }

		 // YEAR CALCULATION
		 year = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment);

		 ageDiffArr[0] = day;
		 ageDiffArr[1] = month;
		 ageDiffArr[2] = year;
		 setBirthOfStudentYear(String.valueOf(year));
		 setBirthOfStudentMonth(String.valueOf(month));
		 return ageDiffArr; 
	 }
	@Transient
	public String getBirthOfStudentYear() {
		return birthOfStudentYear;
	}
	@Transient
	public void setBirthOfStudentYear(String birthOfStudentYear) {
		this.birthOfStudentYear = birthOfStudentYear;
	}
	@Transient
	public String getBirthOfStudentMonth() {
		return birthOfStudentMonth;
	}
	@Transient
	public void setBirthOfStudentMonth(String birthOfStudentMonth) {
		this.birthOfStudentMonth = birthOfStudentMonth;
	}
	@Transient
	public void calculateAgeInYearsAndMonths(){
		if(!ObjectFunctions.isNullOrEmpty(this.dateOfBirth)){
			int[] ageDuration= calculateDurationBetweenTwoDatesInYrsMntsdays(new Date(), this.dateOfBirth);
			setBirthOfStudentYear(String.valueOf(ageDuration[2]));
			setBirthOfStudentMonth(String.valueOf(ageDuration[1]));
		}
	}
	@Transient
	public String getNextAcademicStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getSchoolReOpenDate());
	}
	
	@Transient
	public String getStudentAgeInMonthYear(){
		 
		if(ObjectFunctions.isNullOrEmpty(this.dateOfBirth)){
			return "";
		}
		else{
			int[] ageDuration= calculateDurationBetweenTwoDatesInYrsMntsdays(new Date(), this.dateOfBirth);
			setBirthOfStudentYear(String.valueOf(ageDuration[2]));
			setBirthOfStudentMonth(String.valueOf(ageDuration[1]));
			//log.debug(ageDuration[2]+"-----"+ageDuration[1]);
			return ageDuration[2]+" Years "+ ageDuration[1] +" Months ";  // DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.dateOfBirth);
		}
	}
	
	@Transient
	public String appendImageNameforOriginalAttachmentFilePath(String newImage) {
		if (!StringFunctions.isNullOrEmpty(getImagePath())) {
			return getImagePath().substring(1).concat(newImage);
		}
		return UserImage.getStudyImageNotFoundFile();
	}
	
	@Transient
	public String appendImageSchoolIdCardAttachmentFilePath(String newImage) {
		String imagePath=ServletActionContext.getServletContext().getRealPath(appendImageNameforOriginalAttachmentFilePath(newImage));
		File file=new File(imagePath);
		if(!file.exists()){
			imagePath=ServletActionContext.getServletContext().getRealPath("/images/common/photo_notAvailable.jpg"); 
		}
		//log.debug(imagePath);
	    return imagePath;
	}
}