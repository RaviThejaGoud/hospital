package com.hyniva.sms.ws.enums;

/********************************************************************
* HYNIVA
* All Rights Reserved 
*
* File: ERROR_CODE_ENUM.java
********************************************************************
*
*  Ver   Date            Author                Description
*  ====  ========        ============          ==================
*  0.1   June 18, 2015   Sreeram		       Created
/********************************************************************/

public enum ERROR_CODE_ENUM {

	// 10XX for authentication
	SUCCESS(1000,"Data save successfully"),
	USER_NOT_FOUND(1001,"UserName doesn't exists"),
	INVALID_PASSWORD(1002,"Wrong Password"),
	INVALID_SECURITY_TOKEN(1003,"Invalid Security Token"),
	INVALID_ID(1004,"Id should be greater than zero"),
	INVALID_DATA(1005,"Data does not exists"),
	SYSTEM_ERROR(1006,"Attendance is not registered due to system error. Please contact System Administrator"),
	HOLIDAY_ERROR(1007,"Today Is Holiday"),
	MESSAGE_ERROR(1008,"messages are failed to update in server."),
	ACADEMICYEAR_ERROR(1009,"Invalid academic year details."),
	SMS_SUCCESS_MESSAGE(1010,"SMS(s) has been delivered successfully."),
	DATA_ERROR(1011,"Data does not saved."),
	SCORE_CORD(1012,"Exact path is not available."),
	STUDENT_PERMISSION_CORD(1013,"Applied Permission range is overlapped with exiting permission, save not performed."),
	LEAVE_STATUS(1014,"Leave already applied on selected dates."),
	LEAVE_STATUS_HOLIDAY(1015,"This date have holiday. Please change the date."),
	ATTENDANCE_TYPE_ERROR(1016,"Sorry, we are un able to submit your request. Please check your attendance type in web."),
	Exception(6011,"Unknown Exception occured"),
	INCORRECT_OLD_PASSWORD(1017,"Incorrect old password!"),
	LEAVE_NOT_FOUND(1018,"Leave Not Found"),
	USER_ERROR(1019,"Invalid user details."),
	SCRAPMESSAGE_ERROR(1020,"Invalid scrap message details."),
	DELETE_ERROR(1021,"Data deleted successfully."),
	CAN_NOT_SAVE_FILE(1022,"Can not save file into directory."),
	ALLOTTED_SMS_COUNT_ATTENDANCE(1023,"Attendance is recorded but sms is not delivered due to insufficient sms balance/sms service is disabled."),
	ALLOTTED_SMS_COUNT_CIRCULAR(1024,"Circular is saved but sms is not delivered due to insufficient sms balance/sms service is disabled."),
	ALLOTTED_SMS_COUNT_EVENTS(1025,"Event is saved but sms is not delivered due to insufficient sms balance/sms service is disabled."),
	ALLOTTED_SMS_COUNT_SENDSMS(1026,"SMS is not delivered due to insufficient sms balance/sms service is disabled."),
	ALLOTTED_SMS_COUNT_LEAVE(1027,"Leave submitted successfully but sms is not delivered due to insufficient sms balance/sms service is disabled."),
	EMAIL_NOT_DELIVERED(1028,"Email not delivered."),
	WRONG_CREDENTIALS(1029,"From Email Address or Password are wrong."),
	SMS_ERROR_MESSAGE(1030,"SMS(s) has not delivered.");


	private Integer errorCode;
	private String errorDesc;
	
	/**
	 * @param errorCode
	 * @param errorDesc
	 */
	private ERROR_CODE_ENUM(int errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		
		
	}
	
	public static ERROR_CODE_ENUM getErrorCodeEnum(String desc){
		ERROR_CODE_ENUM enu = null;
		for(ERROR_CODE_ENUM num: ERROR_CODE_ENUM.values()){
			if(num.getErrorDesc().equalsIgnoreCase(desc)){
				enu = num;
				break;
			}
		}
		return enu;
	}
	
	/**
	 * @param errorCode
	 * @param errorDesc
	 */
	private <T extends Throwable> ERROR_CODE_ENUM(int errorCode, String errorDesc,Class<T> ex) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	   
	}
	
	/**
	 * @return the errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	private ERROR_CODE_ENUM(Integer errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
}
