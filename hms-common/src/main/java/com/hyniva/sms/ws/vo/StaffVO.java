package com.hyniva.sms.ws.vo;

import com.churchgroup.util.object.ObjectFunctions;


public class StaffVO {
	
	public long id;
	public String qualification1;
	public String qualification2;
	public String status="Y";
	public long custId;
	public UserVO staffAccountVo;
	public String supervisorId;
	public boolean classTeacher;
	public Double salary;
	public String staffType;
	public long organizationTypeId;
	public String classNameAndSection;
	//private Bed bedId;
	public long hostelCategoryId;
	public String description;
	public String staffPayrollMonth ;
	public String staffPayrollYear;
	//public String isTimetableUploaded;
	public String outSideSchoolDuty;
	public String schoolMess;
	public AcademicYearVo academicYearVo;
	public UserImageVO profileImageVo;
	public String staffGrade;
	public String timetableUploadedFilePath;
	
	
	

	
	public String getTimetableUploadedFilePath() {
		return timetableUploadedFilePath;
	}

	public void setTimetableUploadedFilePath(String timetableUploadedFilePath) {
		this.timetableUploadedFilePath = timetableUploadedFilePath;
	}

	public String getStaffGrade() {
		return staffGrade;
	}

	public void setStaffGrade(String staffGrade) {
		this.staffGrade = staffGrade;
	}
	public UserImageVO getProfileImageVo() {
		return profileImageVo;
	}
	public void setProfileImageVo(UserImageVO profileImageVo) {
		this.profileImageVo = profileImageVo;
	}
	public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}
	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQualification1() {
		return qualification1;
	}
	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}
	public String getQualification2() {
		return qualification2;
	}
	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}
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
	public UserVO getStaffAccountVo() {
		if(ObjectFunctions.isNullOrEmpty(staffAccountVo)){
			this.staffAccountVo=new UserVO();
		}
		return staffAccountVo;
	}
	public void setStaffAccountVo(UserVO staffAccountVo) {
		this.staffAccountVo = staffAccountVo;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public boolean isClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(boolean classTeacher) {
		this.classTeacher = classTeacher;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public long getOrganizationTypeId() {
		return organizationTypeId;
	}
	public void setOrganizationTypeId(long organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}
	public String getClassNameAndSection() {
		return classNameAndSection;
	}
	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}
	public long getHostelCategoryId() {
		return hostelCategoryId;
	}
	public void setHostelCategoryId(long hostelCategoryId) {
		this.hostelCategoryId = hostelCategoryId;
	}
	 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStaffPayrollMonth() {
		return staffPayrollMonth;
	}
	public void setStaffPayrollMonth(String staffPayrollMonth) {
		this.staffPayrollMonth = staffPayrollMonth;
	}
	public String getStaffPayrollYear() {
		return staffPayrollYear;
	}
	public void setStaffPayrollYear(String staffPayrollYear) {
		this.staffPayrollYear = staffPayrollYear;
	}
	/*public String getIsTimetableUploaded() {
		return isTimetableUploaded;
	}
	public void setIsTimetableUploaded(String isTimetableUploaded) {
		this.isTimetableUploaded = isTimetableUploaded;
	}*/
	public String getOutSideSchoolDuty() {
		return outSideSchoolDuty;
	}
	public void setOutSideSchoolDuty(String outSideSchoolDuty) {
		this.outSideSchoolDuty = outSideSchoolDuty;
	}
	public String getSchoolMess() {
		return schoolMess;
	}
	public void setSchoolMess(String schoolMess) {
		this.schoolMess = schoolMess;
	}
	
}
