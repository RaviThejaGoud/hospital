/********************************************************************
* HYNIVA
* All Rights Reserved 
*
* File: SMSURIConstants.java
********************************************************************
*
*  Ver   Date            Author                Description
*  ====  ========        ============          ==================
*  0.1   June 18, 2015    Sreeram		       Created
/********************************************************************/
package com.hyniva.sms.api.constants;


public class SMSURIConstants {	
	
	/**
	 * Get API's
	 */
	public static final String GET_CUSTOMER_DETAILS="/customer/{ui}";
	public static final String GET_CLASS_DETAILS="/class/{academicYearId}";
	public static final String GET_STAFF_DETAILS="/staff/{custId}/{academicYearId}";
	public static final String GET_TEACHING_STAFF_DETAILS="/staffDetails/{accountId}/{type}";
	public static final String GET_DESKTOP_USERS="/users/{custId}/{webAccountId}";
	public static final String GET_STUDENT_LIST_BY_ACADEMICYEARID="/studentsDetails/{academicYearId}";
	//public static final String GET_STUDENT_LIST_BY_STUDYCLASSId="/studentsDetails/{academicYearId}/{studyClassId}";
	public static final String GET_STUDENT_LIST_BY_ACCOUNTID_TYPE="/studentsDetails/{accountId}/{type}";
	public static final String GET_ASSIGNMENT_DETAILS="/assignment/{academicYearId}";
	public static final String GET_CLASS_ASSIGNMENT_DETAILS="/class/assignment/{studyClassId}";
	public static final String GET_STUDENT_ASSIGNMENT_DETAILS="/student/assignment/{studentId}";
	public static final String GET_EVENTS="/event/{accountId}/{type}";
	public static final String GET_STUDY_CLASS_DETAILS="/class/{accountId}/{type}";
	public static final String GET_STUDENT_MONTH_ATTENDANCE="/attendance/student/{academicYearId}/{accountId}/{type}/{standardType}";
	public static final String GET_STUDENT_DAILY_ATTENDANCE="/attendance/student/{academicYearId}/{studyClassId}";
	public static final String GET_STUDENT_ATTENDANCE="/attendance/student/{parentAccountId}";
	public static final String GET_FEE="/fee/{academicYearId}";
	public static final String GET_EXAMSCHEDULES="/examSchedule/{academicYearId}";
	public static final String GET_EXAMSCHEDULES_BY_ACCOUNTID="/examSchedule/{academicYearId}/{accountId}/{type}";
	public static final String GET_STUDENT_MARKS="/marks/{academicYearId}/{studyClassId}";
	public static final String GET_HOLIDAYS="/holidays/{academicYearId}";
	public static final String GET_HOLIDAYS_BY_CLASSIDS="/holidays/{academicYearId}/{classIds}/{type}";
	public static final String GET_LEAVES="/leaves/{accountId}/{type}";
	public static final String GET_MESSAGES="/messages/{academicYearId}/{accountId}";
	public static final String GET_PARENT_MOBILE_SEARCH="/user/parentMobileSearch";
	public static final String GET_FEE_PARTICULARS = "/fee/feeParticulars/{custId}/{academicYearId}";
	public static final String GET_STUDENT_CATEGORIES = "/fee/studentCategories/{custId}";
	public static final String GET_FEE_TERMS = "/fee/schoolFeeTerms/{custId}/{academicYearId}";
	public static final String GET_COMMONTYPES="/commonType/{custId}/{type}";
	public static final String GET_CASTE_SETTINGS="/casteSettings/{custId}";
	public static final String GET_STUDENT_CLASS_FEES = "/fee/classFee/{custId}/{academicYearId}";
	public static final String GET_ADMISSIONS_OPENED_FOR_ACADEMICYEAR="/academicYear/admissionsOpenedAcademicYear/{custId}";
	public static final String GET_WEB_TO_DESKTOP_PAYMENT_DETAILS = "/fee/paymentSyncFromWebToDesktop/{custId}/{academicYearId}";
	public static final String GET_SCORECARDWITH_CLASSID="/scoreCard/scoreCardWithClassId/{custId}/{academicYearId}/{studentId}/{studyClassId}/{examTypeId}";
	public static final String GET_STUDENT_OR_STAFF_PERMISSIONS_DETAILS="/permissionsDetails/{custId}/{academicYearId}/{accountId}/{type}";
	public static final String GET_STAFF_PERMISSIONS_SETTINGS="/staffPermissionsSettings/{custId}/{academicYearId}";
	public static final String GET_ROUTES="/routes/{custId}/{academicYearId}";
	public static final String GET_ROUTE_BOARDING_POINTS_BY_ROUTEID="/routeBoardingPoints/{routeId}";
	public static final String GET_VEHICLES_BY_ROUTE_BOARDING_POINTID="/vehicles/{routeBoardingPointId}";
	public static final String GET_STUDNET_FEE_CONCESSION="/fee/studentFeeConcession/{custId}/{academicYearId}";
	public static final String GET_STUDNET_FEE_DETAILS="/fee/studentFee/{parentAccountId}";
	public static final String GET_STUDNET_TIMETABLE="/student/timetable/{accountId}/{type}";
	public static final String GET_STUDNET_LIBRARY_BOOKS="/student/books/{accountId}/{type}";
	public static final String GET_MEETING_SCHEDULES = "/meetingSchedules/{accountId}/{type}";
	public static final String GET_ROUTE = "/route/{accountId}";
	public static final String GET_ROUTE_VEHICLE_LOCATION = "/route/{driverId}/{routeId}";
	public static final String GET_SEND_BUS_PICKUP_DROP_NOTIFICATION = "/route/{driverId}/{routeId}/{type}";
	public static final String GET_ALL_ROUTE_VEHICLE_LOCATION = "/route/vehicles/{accountId}";
	public static final String GET_SCHOOL_ROUTES = "/routes/{custId}";
	
	public static final String GET_SCHOOL_ROUTES_BY_ACCOUNT_ID_AND_TYPE = "/routes/account/{accountId}/{type}"; 
	public static final String GET_STUDENT_MARKS_BY_ACCOUNTID_TYPE="/studentMarks/{accountId}/{type}";
	public static final String GET_STAFF_DETAILS_DESKTOP="/staffDetailsForDesktop/{custId}/{academicYearId}";
	public static final String GET_STAFF_CLASS_TEACHER_DESKTOP="/staffClassTeaherDetails/{custId}/{academicYearId}";
	public static final String SEND_PARENT_MEETING_REMINDER="/meetingSchedules/reminder/{meetingScheduleId}";
	public static final String GET_STUDENT_STAFF_DAILY_ATTENDANCE="/attendance/admin/{academicYearId}";
	public static final String GET_ACCOUNT_CATEGORY_DETAILS="/account/{custId}/{webId}";
	public static final String GET_ACCOUNT_DETAILS="/account/accountDetails/{custId}/{webId}";
	public static final String GET_FINANCIAL_YEAR_DETAILS="/account/financialYear/{webId}";
	public static final String GET_CASHBOOK_DETAILS="/account/cashBook/{custId}/{financialYearId}/{webId}";
	public static final String GET_PARTICAULAR_ASSOCIATION="/account/particaularAssociation/{custId}/{webId}";
	public static final String GET_EVENTS_ALBUM_ATTACHMENTS="/event/attachment";
	public static final String USER_IMAGE_SUBMIT = "/common/userImage/submit";
	
	// Temporary 
	public static final String MOVE_ATTACHMENTS = "/common/run";
	public static final String MOVE_USER_IMAGES = "/common/moveUserImages";
	public static final String MOVE_PAYSLIPS = "/common/movePayslips";
	
	public static final String MOVE_TIMETABLES = "/common/moveTimetables";
	public static final String MOVE_CERTIFICATES_DOCUMENTS = "/common/moveCertificatesAndDocuments";
	
	public static final String GET_PRE_SCHOOLSTUDENT_DAILY_ATTENDANCE="/attendance/preSchool/student/{academicYearId}/{studyClassId}";
	
	public static final String GET_STUDENT_PAYMENT_DETAILS = "/fee/feePayments/{custId}/{academicYearId}/{paymentId}";
	public static final String GET_STUDENT_TRANSPORT_ASSIGN_DETAILS = "/fee/studTransportAssign/{custId}/{academicYearId}/{webId}";
	
	/**
	 *  POST API's
	 */
	public static final String FORGOT_PASSWORD="/forgotPassword";
	public static final String USER_AUTHENTICATE="/authenticate";
	public static final String CUSTOMER_SCHOOL_AREA_DETAILS="/schoolarea";
	public static final String STUDENT_DAILY_ATTENDANCE_SUBMIT="/attendance/submit";
	public static final String UPDATE_STAFF_BIOMETRIC_ATTENDANCE="/attendance/updateStaffAttence";	
	public static final String GET_STUDENT_PERMISSIONS="/studentPermissions/submit";		
	public static final String UPDATE_STUDENT_MARKS="/studentMarks";	
	public static final String UPDATE_MESSAGE_DETAILS="/update";
	public static final String ANDROID_REGISTRATION="/androidRegistration";
	public static final String UPDATE_CLASS_ASSIGNMENT="/assignments/submit";
	public static final String SUBMIT_REPLY_MESSAGES="/messages/submit";
	public static final String UPDATE_LEAVES="/leaves/submit";
	public static final String RUN_CCE_REPORTS="/messages/runCCEReports";
	public static final String SEND_SCHOOLWIDE_MESSAGES="/sendSMS/submit";
	public static final String UPDATE_PAYMENTS_FROM_DESKTOP_WEB = "/fee/updatePayDetailsFromDeskTop";
	public static final String CREATE_ADMISSIONS_FOR_STUDENTS="/addAdmissions/submit";
	public static final String GET_STAFF_PERMISSION_REQUESTS="/staffRequests/submit";
	public static final String RUN_TIME_TABLE_REPORTS="/runTimeTableEReports";
	public static final String CHANGE_USER_PRIMARY_ADDRESS="/changeUserPrimaryAddress";
	
	public static final String GET_RUN_PAYSLIPS_REPORTS="/runPaySlipsReports";
	
	public static final String CHANGE_USER_MOBILE_NUMBER="/changeUserMobileNumber";
	public static final String SEND_MOBILE_REQUEST="/sendMobileRequest";
	public static final String SUBMIT_MEETING_SCHEDULES="/meetingSchedules/submit";
	public static final String RESERVE_MEETING_SLOT = "/meetingSchedules/reserve";
	public static final String UPDATE_ROUTE_POINTS = "/route/updateRoutePoints";
	public static final String ROUTE_TRACK_SUBMIT = "/routeTrack/submit";
	public static final String ROUTE_NOTIFY_NEAR_BUS_STOP = "/route/notifyNearBusStop";
	public static final String CHANGE_PASSWORD="/changePassword";
	public static final String SUBMIT_MESSAGE="/messages/submitMessage";
	public static final String SIGN_UP="/signUp";
	public static final String APPROVE_REJECT_LEAVES="/leaves/approveOrReject";
	public static final String SUBMIT_CIRCULAR_MESSAGE="/messages/circular/submit";
	public static final String ADD_EVENTS="/events/submit";
	public static final String STAFF_DAILY_ATTENDANCE_SUBMIT="/attendance/staff/submit";
	public static final String FINANCIAL_YEAR_DETAILS_SUBMIT="/account/financialYear/submit";
	public static final String ACCOUNT_CATEGORY_DETAILS_SUBMIT="/account/accountCategory/submit/{custId}";
	public static final String ACCOUNT_DETAILS_SUBMIT="/account/accountDetails/submit/{custId}/{financialYearId}";
	public static final String CASHBOOK_DETAILS_SUBMIT="/account/cashBookDetails/submit/{custId}/{financialYearId}";
	public static final String PARTICULAR_ASSOCIATION_SUBMIT="/account/particaularAssociation/submit/{custId}";
	public static final String CUSTOMER_BANK_ACCOUNT_DETAILS="/account/bankAccountDetails/{custId}";
	
	public static final String PRE_SCHOOL_STUDENT_DAILY_ATTENDANCE_SUBMIT="/attendance/preSchool/submit";
	
	public static final String SEND_SMS="/sms";
	
	/**
	 *  DELETE API's
	 */
	public static final String DELETE_LEAVE_INFO="/leaveDelete/{leaveId}";
}
