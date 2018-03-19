package com.urt.service.manager.interfaces.student;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.ClassAssignmentMainVO;
import com.hyniva.sms.ws.vo.OnlineApplicationDetailsMainVO;
import com.hyniva.sms.ws.vo.PersonVO;
import com.hyniva.sms.ws.vo.StaffPermissionRequestsMainVO;
import com.hyniva.sms.ws.vo.StaffPermissionRequestsVO;
import com.hyniva.sms.ws.vo.StudentDetailsListVO;
import com.hyniva.sms.ws.vo.StudentMarksMainVO;
import com.hyniva.sms.ws.vo.StudentVo;
import com.hyniva.sms.ws.vo.UserImageVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.hyniva.sms.ws.vo.ViewPermissionSettingsMainVO;
import com.hyniva.sms.ws.vo.ViewRouteBoardingPointsMainVO;
import com.hyniva.sms.ws.vo.ViewRoutesMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionRequestsMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionsSettingsMainVO;
import com.hyniva.sms.ws.vo.ViewStudentPersonAccountDetailsMainVO;
import com.hyniva.sms.ws.vo.attendance.ClassAttendanceMainVO;
import com.hyniva.sms.ws.vo.attendance.PreSchoolStudentAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StudentAttendanceListVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.PersonDocuments;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentQuestionAnswer;
import com.urt.persistence.model.study.StudentQuizResults;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.TimeTable;
import com.urt.persistence.model.study.TransferStudent;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.transport.ViewStaffVehicleTripdetails;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="UserManager.java.html"><i>View Source</i></a></p>
 */
public interface StudentManager extends UniversalManager {
	
	List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId,long academicYearId);
	Student getStudentById(long accountId);
	List getClassEventListByClassId(long classId);
	List getAllCalendarEventsByEventIds(String eventsIds,long customerId,long academicYearId);
	List getEventInvitedUserListByEventId(String eventId);
	List getEventAcceptedEventsByEventIdAndAccountId(String eventIds,long accountId,String eventAccepted);
	List getRemainingCalendarEventsByEventIds(String eventsIds,long customerId);
	
	void cancelRegistrationStudentEvent(long eventId,long accountId);
	
	List getEventsByDate(String eventDate);
	
	StudyClass getStudentSubjects(String className,String section);
	
	Student getStudentByAccountId(long accountId,long academicYearId,long custId);
	
	List<ViewStudentPersonAccountDetails> getMyChildren(long parentId, long custId, long academicYearId,String status);
	
	Fee getFeeByStudentId(long classId);
	
	ViewStaffVehicleTripdetails getStaffVehicleTripByTripId(long vehicleTripId);
	
	
	TimeTable getTimeTableByClassAndSec(String className, String sectionName, String dayName);
	
	List getViewStudentLeaveDetailsByAccountId(long accountId,String leaveStatus,long customerId);
	
	List<ViewStudentMarks> getAllMarksByStudId(long studentId, long academicYearId);
	
	List getAllStudentsBirthDays(long classId,long accountId);
	
	List getMyMessagesByReceiverAccountIdAndStatus(String receiverAccountId,long customerId,String status);
	
	Messages updateStudentsReadMessages(String msgId,String newStatus);
		
	List getMyUnreadMessagesAndStatus(String receiverAccountId,long customerId,String status);
	
	List<Messages> getAllNoticeBoardMessagesList(String status,String className,long customerId,long academicYearId);
	
	List getMyreplyMessagesAndStatus(String receiverAccountId,long customerId,String status);
	
	List getViewreplyMessagesAndStatus(String status,long customerId,String msgParentId);
	
	Messages getMessagesByAccountId(String msgAccountId);
	
	List getMyMessagesBySenderAccountIdAndStatus(String senderAccountId,long customerId,String status);

	List getStudentMarksUploadList(long classId,String academicYear);
	String getSubjectMaxMarksByClassIdandSubjectIdandExamtypId(long classId,String subjectName,long examTypeId,String academicYear);
	String getSubjectMinMarksByClassIdandSubjectIdandExamtypId(long classId,String subjectName,long examTypeId,String academicYear);
	double getSubjectAvgMarksByClassIdandSubjectIdandExamtypId(long classId,long subjectId,long examTypeId,long academicYear);
	String getSubjectObtainedMarksByClassIdandSubjectIdandExamtypIdandStudentId(long classId,String subjectName,long examTypeId,String academicYear,long studentId);
	
	List<ViewStudentClassFeePaymentDetails> getMyChildrenFeeDetails(long studentId);
	
	List getMyChildrenClassmatesList(long classId,String status);
	
	List<ViewStudentMarksDetails> getStudentMarksByStudIdandExamtypeId(long studId,long examTypeId);
	
	QuestionAnswer getQuestionAnswer(String studentAnswer,long questionId, long custId);
	
	List getStudentQuizQuestionAnswer(long quizId,long custId,String status);
	
	StudentQuizResults getQuizResultsWithId(long studentId,long quizId,long custId,String status);
	
	List getAllCalendarEventsByStudentIdAndAccountId(String studentId,long customerId);
	
	List getTermStudentAttendanceByClassId(long classId,int term,String year);
	
	List getStudentQuestionAnswers(long studentId,long quizId,long custId,String todayDate);
	
	List getStudentCorrectAnswersList(long questionId,long custId);
	
	List getStudentNotCorrectAnswersList(long questionId,long custId);
	
	StudentQuestionAnswer getStudentAnswerAttemptWithQuestionId(long studentId,long questionId,long custId);
	
	ParentFeedbackResult getStudentFeedbackResult(long questionId,long custId,long studentId);
	
	ParentFeedbackResult getParentFeedbackResult(long questionId,long custId,long parentId);
	
	ParentFeedbackResult getStudentFeedbackStaffResult(long staffId,long studentId,long custId);
	
	ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandStatus(long accountId,long academicYear,String status,long custId);
	
	List<AcademicYear> getAllAcademicYearsBycustId(long custId);
	int getAllStudentMarksCountByTypeAndClassandstudId(long studId,long examtype);
	
	List getAllStudentsByCustIdAndStatus(long custId,String status );
	
	String getSUMOfStudentMarksByStudIdandExamtypeId(long studId,long examTypeId);
	
	List getstAllStudentsByClassId(long classId,String status);
	long getUpcomingFeeTotalAmountByTerm(String queryString,long studentId,long custId,long classId,long academicYearId,long schoolTermId,String today,long boardingPointId);
	long getCommittedFeeAmountTermWise(String queryString,long studentId,long custId,long classId,long academicYearId,long schoolTermId,String today,long boardingPointId);
	
	ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandStatus(long accountId,String status);
	
	ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandAcademicYearId(long accountId,long academicYearId);
	
	List getAllClassesAndSetionWiseReportsByCustId(String className ,long custId,long academicYearId,String classIds);
	
	List getTermStudentAttendanceByAccountIdIdAndTerms(long accountId,int term,String year,Date date);
	/**
	 * 
	 * @param strFormData
	 * @param academicYearId
	 * @param custId
	 * @param userId
	 * @param attendanceType
	 * @return Map list of action messages / errors
	 */
	Map<String,String> addOrUpdateStudentAttendance(String strFormData, long academicYearId, long custId, long userId,String sms,String email,long classId);
	/**
	 * 
	 * @param studentId
	 * @param date
	 * @return
	 */
	
	StudentDailyAttendance getStudentDailyAttendance(long studentId,String date,long custId,long academicYearId);
	 Map<String,String> updateStudentAddress(String strFormData,long custId);
	/**
	 * 
	 * @param classId
	 * @param status
	 * @param custId
	 * @return
	 */
	int getClassStudentsCountByClassIdandStatus(long classId,String status,long custId);
	/**
	 * 
	 * @param studyClassId
	 * @param customerId
	 * @param academicYearId
	 * @return
	 */
	String generateStudnetRollNumber(long studyClassId, long customerId,long academicYearId);
	/**
	 * 
	 * @param custId
	 * @param userId
	 * @return parent account Id
	 */
	String createParentLoginAccount(Customer customer,AcademicYear academicYear, Student student,boolean isNewStudent, Customer masterCustomer,User loggedUser);
	/**
	 * 
	 * @param pobjStudent
	 * @param custId
	 * @param academicYearId
	 * @param userId
	 * @return
	 */
	Map<String, String> updateStudent(StudentVo pobjStudent,Customer customer,long academicYearId, User loggedUser,String currentClass,String admissionNumber,String feeSettingIds,SMSServiceProviders smsServiceProvider);
	/**
	 * 
	 * @param custId
	 * @param academicYearId
	 * @param params
	 * @return
	 */
	
	List<ViewStudentClassDetails> GetStudentClassDetailsList(long custId, long academicYearId, Map<String,String> params);
	/**
	 * 
	 * @param params
	 * @param studyTemplateFilePath
	 * @return
	 */
	
	Map<String, String> GenerateStudyAndBonafiedCertificate(Map<String,String> params, StringBuffer studyTemplateFilePath);
	
	List<StudyClass> getStudyClassesByClassNameClassId(long classNameClassId,long customerId,long academicYearId);
	
	String getApplicationNumber(Customer customer,AcademicYear academicYear);
	 void CreateStudentUserIsAvailable(TransferStudent transferStudent,long custId,AcademicYear academicYear, User user, StudyClass studyClass,Person person,String userName, long catId, String transportMode,String hostelMode,UserImage userImageId,long boardingPointId,long vehicleId,double committedFee,String goals,String strength,String interestAndHobbies,String responsibilities,String achievements,String remarks,String outSideDuty,String schoolMess);
	int getClassStudentsCountByClassId(long classId,long custId);
	/**
	 * 
	 * @param studyClassId
	 * @param customerId
	 * @param academicYearId
	 * @return
	 */
	
	Student addStudentFromApplicationAndUpdateCount(OnlineApplicationDetails onlineApplicationDetails,AcademicYear academicYear,String userName,String admissionNumber,Customer customer,SMSServiceProviders smsServiceProvider);
	int getApplicationMaxId(long custId,long academicYearId,int length);
	void removeCommittedStudentFeePaidDetails(StudentVo studentVo,long studentPaymentId);
	void removeCommittedStudentPayment(StudentVo studentVo,long studentPaymentId);
	double getCommittedConfiguredFeeAmountCategoryWise(long custId,long academicYearId,long classId,long categoryId,String feeSettingIds);
	Map<String, String> validateCommittedFeeConstrains(long custId,long academicYearId,double committedFee,long classId,long classSectionId,long categoryId,String feeSettingIds);
	void removeCommittedFeeEntries(StudentVo studentVo);
	void sendSmsForParentsAndStudent(String mobileNumber,String secondaryMobileNumber,String stuMobileNumber,User user,User parentUserObject,Student studObject,Customer customer,String updatedAdmissionStatus,String newPassword,SMSServiceProviders smsServiceProvider);
	/*Map<String,String> addOrUpdateStudentPermissions(String strPermissionData, long userId);*/
	int saveStudentPermissions(ViewPermissionSettingsMainVO viewPermissionSettingsMainVO);
	
	ViewStudentPersonAccountDetailsMainVO getStudentDetails(long academicYearId, long studyClassId, long accountId, String type);
	
	StudentMarksMainVO getStudentMarks(long academicYearId,long studyClassId);
	int addOrUpdateStudentAttendance(ClassAttendanceMainVO classAttendanceMainVO);
	boolean updateClassAssignment(ClassAssignmentMainVO classAssignmentMainVO);
	
	OnlineApplicationDetailsMainVO createAdmissionsAppliedStudents(OnlineApplicationDetailsMainVO onlineApplicationDetailsMainVO);
	
	Map<String,String> addOrUpdateStudentPermissions(String strPermissionData,long userId);
	
	ViewPermissionSettingsMainVO getAllPermissionsByAccountId(long custId,long academicYearId, long accountId);
	
	ViewStaffPermissionRequestsMainVO getStaffAllPermissionsByAccountId(long custId,long academicYearId, long accountId);
	
	ViewStaffPermissionsSettingsMainVO getStaffPermissionSettings(long custId,long academicYearId);	
	int saveStaffPermissionsRequests(StaffPermissionRequestsMainVO staffPermissionRequestsMainVO);
	
	Map<String, String> addStaffRequests(long custId,long academicYearId,long staffId,StaffPermissionRequestsVO requestVo);
	void removePermissionTimingsByPermissionId(long permissionId);
	User saveOrUpdateStudent(StudentVo studentStudentVo,UserVO studentAccountVo,PersonVO studentPersonVo,AddressVO studentAddressVo,Customer customer,SMSServiceProviders smsServiceProvider,List<PersonDocuments> studentDocList,User loggedUser);
	void sendEmailToParentsAndStudent(String email,String studName,Customer customer,String classAndSection,User user,String parentUserName,String sendEmail,String newPassword);
	
	ViewRoutesMainVO getRoutesListByAcademicYearIdAndCustId(long custId,long academicYearId);
	ViewRouteBoardingPointsMainVO getRouteBoardingPointsListByRouteId(long routeId);
	//void sendMobileRequest(MobileRequestsVO mobileRequestsVO);
	long addStudyClassWiseStudentOptionlaSubject(long custId,long academicYearId,String tempString,long classSectionId);
	public boolean sendSMSForParents(User parentUserObject,Customer customer,String password,User user,Set<String> mobileNumbersSet,Messages message,String oldUsername,SMSServiceProviders smsServiceProviders);
	StudentDetailsListVO getStudentDetailsVO(long accountId, String type);
	StudentMarksMainVO getStudentMarksByAccountIdAndType(long accountId, String type);
	void updateTcGenerateInActiveStudents(String wishTitle, long custId);
	void sendNotificationForStudentUpdate(Student student);
	void updateParentSecondaryUsername(long accountId);
	SMSBaseVO updateProfileFromApp(long userId, InputStream uploadedInputStream,String fileName);
	
	void updateStudentUserName(long accountId,String password);
	String checkingMobileNumberhasUpdated(String oldMobileNumber,String newMobileNuber);
	boolean sendStudentCredentiolsToParent(Customer customer,Person parentPersonObj,Messages message,User user,Set<String> mobileNumbersSet,SMSServiceProviders smsServiceProvider);
	SMSBaseVO updateUserImage(UserImageVO userImageVO);
	
	Map<String,String> addOrUpdateStudentAttendance(Long studentId,String attendanceDate, long academicYearId, long custId, long userId,String sms, long classId,String attendanceType);
	Map<String,String> addOrUpdateStudentAttendance(PreSchoolStudentAttendanceVO preSchoolStudentAttendanceVO);
	
	StudentAttendanceListVO getPreschoolStudentDailyAttendance(long academicYearId,long studyClassId);
	List<Object[]> getAllMotherTongueWiseStudentSummaryDetails(Long custId,Long academicYearId,String classIds,String motherTongueIds);
	List getAllClassesWiseTCTakenStudentCountByCustId(long custId,long academicYearId,String classIds);
	List getAllClassesWiseNewAdmittedStudentCountByCustId(long custId,long academicYearId,String classIds);
}