package com.hyniva.sms.ws.vo;

import java.util.Date;

import com.churchgroup.util.date.DateFormatter;


public class AcademicYearVo {
	
	public long id;
	public String academicYear;                  
    public String startDate;  
    public String endDate;
    public String classTeacherHandleFirstPeriod;
    public long pastYear;
    public String status;
    public long allowedTotalSms;
    public long paidSms;
    public String startTime;
    public String endTime;
    public int noOfWorkingDays;
    public int noOfHolidays;
    public String nextAcademicStartDate;
    public String morningBreakStartTime;
    public String morningBreakEndTime;
    public String eveningBreakStartTime;
    public String eveningBreakEndTime;
    public String lunchStartTime;
    public String lunchEndTime;
    public int attendancePercentage;
    public boolean futureAcademicData;
    public String manageAttendanceBy; 
    public String manageStaffAttendanceBy;
    public String useBiometricForStaff;
    public boolean transportFeeByBoardingPoint;
    public boolean sendBirthdayAlerts;
    public String manageStudentsAdmissionsByFee;
    public boolean dispActivityDescField;
    public String holidayStatus;
    public String useBiometricForStudent;
    public long custId;
    public long allotedsms;
    public String isDefaultExamTypeStatus;
    public String enableSchoolShift;
    public boolean sendStaffBirthdayAlerts;
    public boolean sendBirthdayAlertsByEmail;
    public boolean sendStaffBirthdayAlertsByEmail;
    
    
    public Date academicStartDate;
    public Date academicEndDate;
    public String captureAttendanceBy;
    public String receiptGenerationType;
    public String captureAttendanceForStaff;
    private String feeModuleUsegeBy;
    
	public String getCaptureAttendanceForStaff() {
		return captureAttendanceForStaff;
	}
	public void setCaptureAttendanceForStaff(String captureAttendanceForStaff) {
		this.captureAttendanceForStaff = captureAttendanceForStaff;
	}
	public String getReceiptGenerationType() {
		return receiptGenerationType;
	}
	public void setReceiptGenerationType(String receiptGenerationType) {
		this.receiptGenerationType = receiptGenerationType;
	}
	public Date getAcademicStartDate() {
		return academicStartDate;
	}
	public void setAcademicStartDate(Date academicStartDate) {
		this.academicStartDate = academicStartDate;
	}
	public Date getAcademicEndDate() {
		return academicEndDate;
	}
	public void setAcademicEndDate(Date academicEndDate) {
		this.academicEndDate = academicEndDate;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAllotedsms() {
		return allotedsms;
	}
	public void setAllotedsms(long allotedsms) {
		this.allotedsms = allotedsms;
	}
	public String getIsDefaultExamTypeStatus() {
		return isDefaultExamTypeStatus;
	}
	public void setIsDefaultExamTypeStatus(String isDefaultExamTypeStatus) {
		this.isDefaultExamTypeStatus = isDefaultExamTypeStatus;
	}
	public String getEnableSchoolShift() {
		return enableSchoolShift;
	}
	public void setEnableSchoolShift(String enableSchoolShift) {
		this.enableSchoolShift = enableSchoolShift;
	}
	public boolean isSendStaffBirthdayAlerts() {
		return sendStaffBirthdayAlerts;
	}
	public void setSendStaffBirthdayAlerts(boolean sendStaffBirthdayAlerts) {
		this.sendStaffBirthdayAlerts = sendStaffBirthdayAlerts;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getNoOfWorkingDays() {
		return noOfWorkingDays;
	}
	public void setNoOfWorkingDays(int noOfWorkingDays) {
		this.noOfWorkingDays = noOfWorkingDays;
	}
	public int getNoOfHolidays() {
		return noOfHolidays;
	}
	public void setNoOfHolidays(int noOfHolidays) {
		this.noOfHolidays = noOfHolidays;
	}
	public String getMorningBreakStartTime() {
		return morningBreakStartTime;
	}
	public void setMorningBreakStartTime(String morningBreakStartTime) {
		this.morningBreakStartTime = morningBreakStartTime;
	}
	public String getMorningBreakEndTime() {
		return morningBreakEndTime;
	}
	public void setMorningBreakEndTime(String morningBreakEndTime) {
		this.morningBreakEndTime = morningBreakEndTime;
	}
	public String getEveningBreakStartTime() {
		return eveningBreakStartTime;
	}
	public void setEveningBreakStartTime(String eveningBreakStartTime) {
		this.eveningBreakStartTime = eveningBreakStartTime;
	}
	public String getEveningBreakEndTime() {
		return eveningBreakEndTime;
	}
	public void setEveningBreakEndTime(String eveningBreakEndTime) {
		this.eveningBreakEndTime = eveningBreakEndTime;
	}
	public String getLunchStartTime() {
		return lunchStartTime;
	}
	public void setLunchStartTime(String lunchStartTime) {
		this.lunchStartTime = lunchStartTime;
	}
	public String getLunchEndTime() {
		return lunchEndTime;
	}
	public void setLunchEndTime(String lunchEndTime) {
		this.lunchEndTime = lunchEndTime;
	}
	public int getAttendancePercentage() {
		return attendancePercentage;
	}
	public void setAttendancePercentage(int attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	public boolean isFutureAcademicData() {
		return futureAcademicData;
	}
	public void setFutureAcademicData(boolean futureAcademicData) {
		this.futureAcademicData = futureAcademicData;
	}
	public String getManageAttendanceBy() {
		return manageAttendanceBy;
	}
	public void setManageAttendanceBy(String manageAttendanceBy) {
		this.manageAttendanceBy = manageAttendanceBy;
	}
	public String getManageStaffAttendanceBy() {
		return manageStaffAttendanceBy;
	}
	public void setManageStaffAttendanceBy(String manageStaffAttendanceBy) {
		this.manageStaffAttendanceBy = manageStaffAttendanceBy;
	}
	public String getUseBiometricForStaff() {
		return useBiometricForStaff;
	}
	public void setUseBiometricForStaff(String useBiometricForStaff) {
		this.useBiometricForStaff = useBiometricForStaff;
	}
	public boolean isTransportFeeByBoardingPoint() {
		return transportFeeByBoardingPoint;
	}
	public void setTransportFeeByBoardingPoint(boolean transportFeeByBoardingPoint) {
		this.transportFeeByBoardingPoint = transportFeeByBoardingPoint;
	}
	public boolean isSendBirthdayAlerts() {
		return sendBirthdayAlerts;
	}
	public void setSendBirthdayAlerts(boolean sendBirthdayAlerts) {
		this.sendBirthdayAlerts = sendBirthdayAlerts;
	}
	public String getManageStudentsAdmissionsByFee() {
		return manageStudentsAdmissionsByFee;
	}
	public void setManageStudentsAdmissionsByFee(
			String manageStudentsAdmissionsByFee) {
		this.manageStudentsAdmissionsByFee = manageStudentsAdmissionsByFee;
	}
	public boolean isDispActivityDescField() {
		return dispActivityDescField;
	}
	public void setDispActivityDescField(boolean dispActivityDescField) {
		this.dispActivityDescField = dispActivityDescField;
	}
	public String getHolidayStatus() {
		return holidayStatus;
	}
	public void setHolidayStatus(String holidayStatus) {
		this.holidayStatus = holidayStatus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getClassTeacherHandleFirstPeriod() {
		return classTeacherHandleFirstPeriod;
	}
	public void setClassTeacherHandleFirstPeriod(String classTeacherHandleFirstPeriod) {
		this.classTeacherHandleFirstPeriod = classTeacherHandleFirstPeriod;
	}
	public long getPastYear() {
		return pastYear;
	}
	public void setPastYear(long pastYear) {
		this.pastYear = pastYear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAllowedTotalSms() {
		return allowedTotalSms;
	}
	public void setAllowedTotalSms(long allowedTotalSms) {
		this.allowedTotalSms = allowedTotalSms;
	}
	public long getPaidSms() {
		return paidSms;
	}
	public void setPaidSms(long paidSms) {
		this.paidSms = paidSms;
	}
	public String getNextAcademicStartDate() {
		return nextAcademicStartDate;
	}
	public void setNextAcademicStartDate(String nextAcademicStartDate) {
		this.nextAcademicStartDate = nextAcademicStartDate;
	}
	public String getUseBiometricForStudent() {
		return useBiometricForStudent;
	}
	public void setUseBiometricForStudent(String useBiometricForStudent) {
		this.useBiometricForStudent = useBiometricForStudent;
	}
	
    public String getAcademicStartDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getAcademicStartDate()); 
    }
	public String getAcademicEndDateStr()
	{
		return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getAcademicEndDate()); 
	}
	public boolean isSendBirthdayAlertsByEmail() {
		return sendBirthdayAlertsByEmail;
	}
	public void setSendBirthdayAlertsByEmail(boolean sendBirthdayAlertsByEmail) {
		this.sendBirthdayAlertsByEmail = sendBirthdayAlertsByEmail;
	}
	public boolean isSendStaffBirthdayAlertsByEmail() {
		return sendStaffBirthdayAlertsByEmail;
	}
	public void setSendStaffBirthdayAlertsByEmail(
			boolean sendStaffBirthdayAlertsByEmail) {
		this.sendStaffBirthdayAlertsByEmail = sendStaffBirthdayAlertsByEmail;
	}
	public String getCaptureAttendanceBy() {
		return captureAttendanceBy;
	}
	public void setCaptureAttendanceBy(String captureAttendanceBy) {
		this.captureAttendanceBy = captureAttendanceBy;
	}
	/**
	 * @return the feeModuleUsegeBy
	 */
	public String getFeeModuleUsegeBy() {
		return feeModuleUsegeBy;
	}
	/**
	 * @param feeModuleUsegeBy the feeModuleUsegeBy to set
	 */
	public void setFeeModuleUsegeBy(String feeModuleUsegeBy) {
		this.feeModuleUsegeBy = feeModuleUsegeBy;
	}
    
}
