package com.hyniva.sms.ws.vo;

import java.util.List;

import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;


@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class StudentVo{
	
	public long id;
	 
	/*below ids used in createAdmissionsAppliedStudents */
	public long applicationId;
	public String admissionNumber;
	public String rollNumber;
	public long accountId;
	public long studyClassId;
	
	@MappedExcelObject
	public UserVO account;
	
	
	//public AcademicYear academicYear;
	//public boolean activeStudent;
	public String status;
	
	public long custId;
	//public ClassName classNameClassId;
	//public List<StudentMarks> studentMarks;
	public long categoryId;
	@ExcelField(position = 35)
	public String transportMode;
	public String description; 
	//public RouteBoardingPoints routeBoardingPoints;
	//public Bed bed;
	//public Long vehicleId;
	public Long vehicleAcademicDetailsId;
	public String tempString; 
	public List feeDetailsList;
	@ExcelField(position = 44)
	public String registerNumber; 
	
	public boolean joinedThroughAdmissions;
	public long routeId;
	//public List<StudentAcademicPerformance> studentActivityGrades;
	//public Set<FineFee> fineFee;
	
	@ExcelField(position = 54)
	public String hostelMode;
	public long roomId;
	@ExcelField(position = 3)
	public String rollNumberStr;
	public Long onlineApplicationDetailsId;
	public Long futureAcademicClassSecId;
	public String feePaidStatus="N";
	public String feeConfigured="N";
	@ExcelField(position = 49)
	public String categoryName;
	public UserImageVO profileImage;
	public String shippedSection;
	@ExcelField(position = 58)
	public String outSideSchoolDuty;
	public double committedFee;
	@ExcelField(position = 59)
	public String committedFeeStr;
	@ExcelField(position = 61)
	public String goals;
	@ExcelField(position = 62)
	public String strengths;
	@ExcelField(position = 63)
	public String interestsAndHobbies;
	@ExcelField(position = 64)
	public String responsibilities;
	@ExcelField(position = 65)
	public String achievements;
	@ExcelField(position = 66)
	public String remarks;
	@ExcelField(position = 69)
	public String promoteToClass;
	@ExcelField(position = 75)
	public String schoolMess;
	public String errorMsg;
	
	public ClassNameVO classNameVo;
	public AcademicYearVo academicYearVo;
	@MappedExcelObject
	public StudyClassVO studyClassVo;
	public RouteBoardingPointsVO routeBoardingPointsVO;
	public String studentAacademicImage;
	public String studentName;
	public String classAndSection;
	public String dateOfJoiningStu;
	public BedVo bedVo;
	public String studAdmissionNumber;
	
	
	@ExcelField(position = 55)
	private String isJoinedThroughAdmissionStr;
	
	@ExcelField(position = 1)
	public String stuId;
	
	public long profileImageId;
	
	@ExcelField(position = 77)
	public String ptaStatus;
	@ExcelField(position = 79)
	public String bplStatus;//Below Poverty Line
	@ExcelField(position = 80)
	public String rteStatus;
	
	public String bplNumber;//Below Poverty Line Number
	
	public String tempString3;
	

	
	
	
	public String getTempString3() {
		return tempString3;
	}

	public void setTempString3(String tempString3) {
		this.tempString3 = tempString3;
	}

	public String getBplNumber() {
		return bplNumber;
	}

	public void setBplNumber(String bplNumber) {
		this.bplNumber = bplNumber;
	}

	public String getBplStatus() {
		return bplStatus;
	}

	public void setBplStatus(String bplStatus) {
		this.bplStatus = bplStatus;
	}

	public String getRteStatus() {
		return rteStatus;
	}

	public void setRteStatus(String rteStatus) {
		this.rteStatus = rteStatus;
	}

	public String getPtaStatus() {
		return ptaStatus;
	}

	public void setPtaStatus(String ptaStatus) {
		this.ptaStatus = ptaStatus;
	}
	
	public long getProfileImageId() {
		return profileImageId;
	}
	public void setProfileImageId(long profileImageId) {
		this.profileImageId = profileImageId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	public String getStudAdmissionNumber() {
		return studAdmissionNumber;
	}
	public void setStudAdmissionNumber(String studAdmissionNumber) {
		this.studAdmissionNumber = studAdmissionNumber;
	}
	public BedVo getBedVo() {
		return bedVo;
	}
	public void setBedVo(BedVo bedVo) {
		this.bedVo = bedVo;
	}
	public String getDateOfJoiningStu() {
		return dateOfJoiningStu;
	}
	public void setDateOfJoiningStu(String dateOfJoiningStu) {
		this.dateOfJoiningStu = dateOfJoiningStu;
	}
	public String getStudentAacademicImage() {
		return studentAacademicImage;
	}
	public void setStudentAacademicImage(String studentAacademicImage) {
		this.studentAacademicImage = studentAacademicImage;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	public RouteBoardingPointsVO getRouteBoardingPointsVO() {
		return routeBoardingPointsVO;
	}
	public void setRouteBoardingPointsVO(RouteBoardingPointsVO routeBoardingPointsVO) {
		this.routeBoardingPointsVO = routeBoardingPointsVO;
	}
	public ClassNameVO getClassNameVo() {
		return classNameVo;
	}
	public void setClassNameVo(ClassNameVO classNameVo) {
		this.classNameVo = classNameVo;
	}
	public StudyClassVO getStudyClassVo() {
		return studyClassVo;
	}
	public void setStudyClassVo(StudyClassVO studyClassVo) {
		this.studyClassVo = studyClassVo;
	}
	public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}
	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}
	public UserVO getAccount() {
		return account;
	}
	public void setAccount(UserVO account) {
		this.account = account;
	}
	/*public boolean isActiveStudent() {
		return activeStudent;
	}
	public void setActiveStudent(boolean activeStudent) {
		this.activeStudent = activeStudent;
	}*/
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getVehicleAcademicDetailsId() {
		return vehicleAcademicDetailsId;
	}
	public void setVehicleAcademicDetailsId(Long vehicleAcademicDetailsId) {
		this.vehicleAcademicDetailsId = vehicleAcademicDetailsId;
	}
	public String getTempString() {
		return tempString;
	}
	public void setTempString(String tempString) {
		this.tempString = tempString;
	}
	public List getFeeDetailsList() {
		return feeDetailsList;
	}
	public void setFeeDetailsList(List feeDetailsList) {
		this.feeDetailsList = feeDetailsList;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public boolean isJoinedThroughAdmissions() {
		return joinedThroughAdmissions;
	}
	public void setJoinedThroughAdmissions(boolean joinedThroughAdmissions) {
		this.joinedThroughAdmissions = joinedThroughAdmissions;
	}
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public String getHostelMode() {
		return hostelMode;
	}
	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public String getRollNumberStr() {
		return rollNumberStr;
	}
	public void setRollNumberStr(String rollNumberStr) {
		this.rollNumberStr = rollNumberStr;
	}
	public Long getOnlineApplicationDetailsId() {
		return onlineApplicationDetailsId;
	}
	public void setOnlineApplicationDetailsId(Long onlineApplicationDetailsId) {
		this.onlineApplicationDetailsId = onlineApplicationDetailsId;
	}
	public Long getFutureAcademicClassSecId() {
		return futureAcademicClassSecId;
	}
	public void setFutureAcademicClassSecId(Long futureAcademicClassSecId) {
		this.futureAcademicClassSecId = futureAcademicClassSecId;
	}
	public String getFeePaidStatus() {
		return feePaidStatus;
	}
	public void setFeePaidStatus(String feePaidStatus) {
		this.feePaidStatus = feePaidStatus;
	}
	public String getFeeConfigured() {
		return feeConfigured;
	}
	public void setFeeConfigured(String feeConfigured) {
		this.feeConfigured = feeConfigured;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public UserImageVO getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(UserImageVO profileImage) {
		this.profileImage = profileImage;
	}
	public String getShippedSection() {
		return shippedSection;
	}
	public void setShippedSection(String shippedSection) {
		this.shippedSection = shippedSection;
	}
	public String getOutSideSchoolDuty() {
		return outSideSchoolDuty;
	}
	public void setOutSideSchoolDuty(String outSideSchoolDuty) {
		this.outSideSchoolDuty = outSideSchoolDuty;
	}
	public double getCommittedFee() {
		return committedFee;
	}
	public void setCommittedFee(double committedFee) {
		this.committedFee = committedFee;
	}
	public String getCommittedFeeStr() {
		return committedFeeStr;
	}
	public void setCommittedFeeStr(String committedFeeStr) {
		this.committedFeeStr = committedFeeStr;
	}
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPromoteToClass() {
		return promoteToClass;
	}
	public void setPromoteToClass(String promoteToClass) {
		this.promoteToClass = promoteToClass;
	}
	public String getSchoolMess() {
		return schoolMess;
	}
	public void setSchoolMess(String schoolMess) {
		this.schoolMess = schoolMess;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIsJoinedThroughAdmissionStr() {
		return isJoinedThroughAdmissionStr;
	}
	public void setIsJoinedThroughAdmissionStr(String isJoinedThroughAdmissionStr) {
		this.isJoinedThroughAdmissionStr = isJoinedThroughAdmissionStr;
	}
	
}
