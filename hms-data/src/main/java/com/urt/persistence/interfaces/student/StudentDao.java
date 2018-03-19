package com.urt.persistence.interfaces.student;

import java.util.Date;
import java.util.List;

import com.hyniva.sms.ws.vo.StudentVo;
import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentQuestionAnswer;
import com.urt.persistence.model.study.StudentQuizResults;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.TimeTable;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.transport.ViewStaffVehicleTripdetails;



public interface StudentDao extends UniversalDao {
	
	List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId,long academicYearId);
	
	Student getStudentById(long accountId);
	
	List getClassEventListByClassId(long classId);
	
	List getAllCalendarEventsByEventIds(String eventsIds,long customerId,long academicYearId);
	
	List getEventInvitedUserListByEventId(String eventId);
	
	//EventInvitedUser getEventInvitedUserByEventIdAndAccountId(String eventId,long accountId,String eventAccepted);
	
	List getEventAcceptedEventsByEventIdAndAccountId(String eventIds,long accountId,String eventAccepted);
	
	List getRemainingCalendarEventsByEventIds(String eventsIds,long customerId);
	
	void cancelRegistrationStudentEvent(long eventId,long accountId);
	
	List getEventsByDate(String eventDate);
	
	StudyClass getStudentSubjects(String className,String section);
	
	Student getStudentByAccountId(long accountId,long academicYearId,long custId);
	
	List<ViewStudentPersonAccountDetails> getMyChildren(long parentId, long custId, long academicYearId,String status);
	
	Fee getFeeByStudentId(long classId);
	
	ViewStaffVehicleTripdetails getStaffVehicleTripByTripId(long vehicleTripId);
	
	/*StudyClass getStudyClassByClassName(String className);*/
	
	/*StudentPayment getPaymentStatusByStudentId(long studentId);*/
	
	List getStudentAttendanceByStudentId(long studentId);
	
	TimeTable getTimeTableByClassAndSec(String className, String sectionName, String dayName);
	
	List getViewStudentLeaveDetailsByAccountId(long accountId,String leaveStatus,long customerId);
	
	List<ViewStudentMarks> getAllMarksByStudId(long studentId, long academicYearId);
	//List getAllVehivleTripByStudentId(long studentId);
	List getAllStudentsBirthDays(long classId,long accountId);
	List getMyMessagesByReceiverAccountIdAndStatus(String receiverAccountId,long customerId,String status);
	//List<CalendarEvent> getAllEvents(long custId);
	Messages updateStudentsReadMessages(String msgId,String newStatus);
	List getMyUnreadMessagesAndStatus(String receiverAccountId,long customerId,String status);
	List<Messages> getAllNoticeBoardMessagesList(String status,String className,long customerId,long academicYearId);
	
	List getMyreplyMessagesAndStatus(String receiverAccountId,long customerId,String status);
	List getViewreplyMessagesAndStatus(String status,long customerId,String msgParentId);
	Messages getMessagesByAccountId(String msgAccountId);
	List getMyMessagesBySenderAccountIdAndStatus(String senderAccountId,long customerId,String status);
	/*List getAllHolidayBoardMessagesList(String attendanceStatus,long customerId);*/
	
	
	//List getAllStudentMarksByScheduleIds(String sheduleIds,long studId);
	
	/*List getAllStudentMarksByTypeAndClassandstudId(long studId,String examtype);*/
	List getStudentMarksUploadList(long classId,String academicYear);
	/*List getMyFeeDetailsByStudentId(long studentId);*/
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
	//List<CalendarEvent> getAllCalendarEventsByTypeAndEventId(String eventId,long customerId,long academicYearId);
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
	/*List<ViewUserRoles> getViewUserRolesByRoleNameAndCustId(String rollName, long customerId);*/
	ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandStatus(long accountId,String status);
	ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandAcademicYearId(long accountId,long academicYearId);
	List getAllClassesAndSetionWiseReportsByCustId(String className ,long custId,long academicYearId ,String classIds);
	List getTermStudentAttendanceByAccountIdIdAndTerms(long accountId,int term,String year,Date date);
	
	StudentDailyAttendance getStudentDailyAttendance(long studentId,String date,long custId,long academicYearId);
	
	int getClassStudentsCountByClassIdandStatus(long classId,String status,long custId);
	
	List<StudyClass> getStudyClassesByClassNameClassId(long classNameClassId,long customerId,long academicYearId);
	int getClassStudentsCountByClassId(long classId,long custId);
	int getApplicationMaxId(long custId,long academicYearId,int length);
	void removeCommittedStudentFeePaidDetails(StudentVo studentVo,long studentPaymentId);
	void removeCommittedStudentPayment(StudentVo studentVo,long studentPaymentId);
	public double getCommittedConfiguredFeeAmountCategoryWise(long custId,long academicYearId,long classId,long categoryId,String feeSettingIds);
	void removePermissionTimingsByPermissionId(long permissionId);
	void updateTcGenerateInActiveStudents(String wishTitle,long custId);
	void updateParentSecondaryUsername(long accountId);
	void updateStudentUserName(long accountId,String password);
	List<Object[]> getAllMotherTongueWiseStudentSummaryDetails(Long custId,Long academicYearId,String classIds,String motherTongueIds);
	List getAllClassesWiseTCTakenStudentCountByCustId(long custId,long academicYearId,String classIds);
	List getAllClassesWiseNewAdmittedStudentCountByCustId(long custId,long academicYearId,String classIds);
	List<Object[]> getSubjectsMarksForStudent(long studentId, String classIds);
}