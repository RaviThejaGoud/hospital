package com.urt.persistence.interfaces.staff;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.common.ReplyScrapMessage;
import com.urt.persistence.model.common.ScrapMessage;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.VWStudentAttendance;
import com.urt.persistence.model.common.VWStudentDailyAttendance;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.KBankRating;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.exam.QuizQuestion;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.PromoteClass;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStaffLeaveDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.Role;

/**
 * Campaign Data Access Object (Dao) interface.
 * 
 */
public interface StaffDao extends UniversalDao {

	ClassTeacher getTeacherByClassandSubjectId(long staffId,long classId,long subjectId,String staffActiveStatus);
	
	/*Staff getStaffByAcountId(long accountId);*/
	
	ClassTeacher getStudyClassSubject(long classId, long staffId);
	
	List<Student> getStudentByClassId(long classId);
	
	//Period getPeriodByClassAndSubject(long classId, long subjectId);
	
	Student getStudentById(long studentId);
	
	List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId,long academicYearId);
	
	List getAllCalendarEventsByCustomerId(long customerId);
	
	List getEventInvitedUserListByEventId(String eventId);
	
	/*User getAccountByUserName(String userName);*/
	
	//EventInvitedUser getEventInvitedUserByEventIdAndAccountId(String eventId,long accountId,String eventAccepted);
	
	/*List getViewStudentPersonAccountDetailsByAccountIds(String accountIds);*/
	
	List getEventsByDate(String eventDate);
	
	List<ClassTeacher> getTeacherSubjectsByIdAndAcademicYear(long staffid,long academicYearId,long custId);
	
	List getTeacherSubjectsByStaffAndClassId(long accountId,long classId,long academicYearId);
	
	Student getStudentByAcountId(long accountId);
	
	List<ViewStudentPersonAccountDetails> getStudentByClassIdAndStaffId(long classId,long customerId,long academicYearId,String status);
	
	/*StudyClass getAllStudyClassesByClassId(long classId);*/
	
	/*ClassName getClassBystudyClassId(String className);*/
	
	Student getStudentsByAccountId(long stuAccountId);

	List<ViewStudentPersonAccountDetails> getViewStudentPersonAccountDetailsByStudyClassIdandStatus(long classId,String status,String academicYearId);
	
	List getClassEventListByEventId(String eventId);
	
	/*List getStudyClassListBystudyClassIds(String studyClassIds);*/
	
	/*List getALLViewStudentPersonAccountDetailsByStudentRole(String studentRole);*/
	
	List<ViewStudentLeaveDetails> getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId);
	
	ViewStudentLeaveDetails getViewStudentLeaveDetailsByLeaveId(long leaveid,long customerId);
	
	Role getRoleDetailsByRoleName(String roleName);
	
	List getAllStudentStatus(String studentId);
	
	List getStudyClassSubjectByClassid(long classId);
	
	List getStudySubjectsId(String subjectId,String classId);
	
	List getTeacherSubjects(String subjectId);
	
	void removeRegistrationStudentEvent(String accountId,String eventId,String eventAcceptedStatus);
	
	
	List getStudentsByIds(String studentIds);
	
	List getNotInvitedStudents(String accountIds);
	
	List<Leave> getAllLeavesListByAccountId(long accountId,long customerId);

	ClassTeacher getClassTeacherByStaffIdandAcademicYear(long staffId,long academicYear,long custId);
	
	ViewStaffPersonAccountDetails getFindStaffByIdOrName(String username, String firstName,long customerId);
	
	List getMyMessagesByReceiverAccountIdAndIsParent(String receiverAccountId, long customerId,String isParent);
	List<ViewClassExamDetails> getClassTeacherExamSchedules(long classId,long academicYear);	
	
	String getClassIdByNameAndSection(String className,String section,long custId,long academicYear);
	
	List getFindStudentsListByIdOrName(String rollNumber,String firstName,long customerId,long academicYearId,long studyClassId);
	
	List getAllMessagesByMessageStatus(String messageStatus,long customreId,String academicYearId,Date messageDate);
	 
	String getExamTypeIdByExamType(String examType,long custId,String academicYear);
	
	String getSubjectIdBySubjectName(String subjectName,long custId,long academicYear);
	
	ExamSchedules getExamSchedule(String studyClassId,String examTypeId,String subjectId,long academicYear,long custId);
	List getEventAcceptedEventsByEventIdAndAccountId(String eventIds,long accountId,String eventAccepted);
	List getClassEventListByClassId(long classId);
	List getAllCalendarEventsByEventIds(String eventsIds,long customerId);
	List getTeacherExamSchedulesByClassIdandSubjectId(long classId,long subjectId,long academicYear);
	List getFindStaffListByIdOrName(String username,String firstName,long customerId);
	
	ViewStaffPersonAccountDetails getStaffBirthDaysByAccountId(long staffId,long custId,long staffAccountId);
	List<ViewStaffPersonAccountDetails> getAllStaffBirthDaysList(String date1,long custId);
	List<ViewStudentPersonAccountDetails> getAllStudentBirthDaysList(String date1,long custId);
	List getMyMessagesBySenderAccountIdAndIsParent(String receiverAccountId, long customerId,String isParent);
	List getstudentMarksListByExamScheduleId(long scheduleId);
	List<ViewStudentPersonAccountDetails> getViewStudentPersonAccountDetailsByStudyClassIdandAccount(long classId,String status,long accountId,String academicYearId);
	double getPassStudentsCount(long scheduleId,double minMarks);
	double getFailStudentsCount(long scheduleId,double minMarks);
	Set getExamTypeIdsByClassIdandAcademicYear(long classId,long academicYear);
	List getExamTypesByExamTypeIds(String typeIds,long academicYear);
	List getClassExamSchedulesByExamTypeId(long examTypeId,long classId,long custId,long academicYear);
	SchoolHolidays checkSchoolHolidayBySelectedDate(String selectedDate,long customerId,long academicYearId);
	List<KBank> getKBankBySearchKewordsKBankTypeId(String searchKeywords,long kBankTypeId,long custId);
	ScrapMessage saveScrapMessage(ScrapMessage scrapMessage);
	List<ScrapMessage> getAllScrapMessagesListBySenderAccId(long senderAccountId,String status,String replyStatus,long customerId,String academicYearId);
	List<ScrapMessage> getAllScrapMessagesListByReceiverAccId(long receiverrAccountId,String status,String replyStatus,long customerId,String academicYearId,String messageType);
	List getAllUsersByFirstNameOrLastName(String name,long customerId);
	List getMyFavouritiesByUserId(long accountId);
	List getMyFavouritiesByUserIdAndKBankId(long accountId,long kBankId);
	KBankRating getKBankRateByKBankId(String kBankId);
	List getPlayListVideosByKeywords(String keywords);
	List getQuizListWithCustId(long custId);
	List getQuizQuestionListWithCategoryId(long quizId,long custId);
	QuizQuestion getQuizQuestionId(long questionId,long custId);
	List getQuestionAnswersWithQuestionId(long questionId,long custId);
	QuestionAnswer getCorrectAnswerWithQuestionId(long questionId,long custId);
	List getAllAlertsByAlertStatus(long customerId,String alertType,String receiverType,boolean isAdmin,String academicYearId);
	List deleteAbsentStudents(String attenDate,long classId,long customerId);
	int getUpdateQuizQuestionStatus(long quizId,long custId,String selectdDate);
	List getUpdateStudentQuizStatus(long custId);
	List getQuizQuestionListWithStudentList(long quizId,long custId);
	List getQuizQuestionListWithCategoryIdAndTeacherId(long quizId,long staffId,long custId);
	String studentFailInAnnuallyExam(long studentId,long examTypeId,String minMarks);
	PromoteClass getPromoteClassDetails(long classId,long custId);
	String getStudentCountByClassId(long classId,long custId);
	ViewStudentMarksDetails getStudentsMarksByClassIdandExamtypeId(long classId,long examTypeId,long custId,String academicYear,long studentId,long subjectId);
	List getMarksByClassIdandExamtypeId(long classId,long custId);
	String getStudentCountByClassNameClassId(long ClassNameClassId,long custId);
	List getExamScheduleIdsByAcademicYearandClassIdandExamTypeId(String academicYear,String classId ,String examTypeId);
	List getAllStudentMarksChangesByExamScheduleIds(String scheduleIds);
	int updateStudentMarksByStudentIdandScheduleId(String studentId,String scheduleId,String obtainedMarks);
	List getTeacherExamScheduleIdsByClassIdandSubjectId(long classId,long subjectId,long academicYear);
	void removeStudentMarksChangesByClassIdandSubjectIdandAcademicYear(String scheduleIds);
	ViewStudentMarksDetails getStudentsMarksByStudentIdandExamTypeIdandSubjectId(long examTypeId,long custId,long studentId,long subjectId);
	List<ReplyScrapMessage> getAllReplyMessagesListByScrapId(long scrapId,long customerId);
	List<VWStudentAttendance> getStudentsAttendance(long custId, long academicYearId,int month,long classId, long classSectionId, String date);
	long getStudentsAttendanceByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,String attendanceStatus);
	List getStaffAttendanceByPresentStatusAndLeaveRequestStaus(long custId,long academicYearId, long staffId, String present,String leaveRequest);
	List<ViewStudentMarksDetails> getLatestUpdatedStudentMarksExamTypeIdsByClassSectionIdAndCustId(long classSectionId,long custId);
	/*ViewStudentPersonAccountDetails getStudentDetailsByRollNumberAndAccountId(long userId,String rollNumber);*/
	List getTeacherSubjectsByStaffIdAndClassIdGroupByclassIdSubjectId(long staffId,long classId,long academicYearId);
	double getStudentsTotalMarksByClassIdTypeIdAndSubjId(long classSectionId,long examTypeId,long subjectId,long academicYearId);
	long getStudentsCountByClassIdTypeIdAndSubjId(long classSectionId,long examTypeId,long subjectId,long academicYearId);
	List getIsClassTeacherByClassSectionAndStaffIdAndAcademicYear(long classSectionId,long staffId,long academicYearId);
	long getPassStudentsCount(long classSectionId,long examTypeId,long subjectId,long academicYearId,double passMarks);
	long getFailStudentsCount(long classSectionId,long examTypeId,long subjectId,long academicYearId,double passMarks);
	ViewStaffPersonAccountDetails getStaffDetailsByAccountIdandStatus(long accountId,long academicYearId,String status,long custId);
	ScrapMessage getScrapMessageBySenderAndReceiverId(long senderAccountId,long receiverAccountId,long customerId,long academicYearId) ;
	List getLatestSmsCount(long custId,long academicYearId);
	List<ViewStudentLeaveDetails> getAllFutureStudentLeavesByStatusAndRoleNameAndSupervisorId(long customerId, String leaveStatus, String roleName,long supervisorId,long academicYearId);
	List<ViewStudentMarksDetails> getStudentsMarksListByStudentIdandExamTypeIdandSubjectId(long examTypeId,long custId,long studentId,long subjectId);
	
	//ViewStudentMarksDetails getStudentsMarksByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId,long custId,long studentId,long subjectId,long subTypeId);
	List<ViewStudentMarksDetails> getStudentsMarksListByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId, long custId, long studentId, long subjectId,long subTypeId);
	
	String getSUMStudentsMarksByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId, long custId, long studentId, long subTypeId);
	void removeStaffAttendanceByaccountIdAndDates(long accountId,String startDate,String endate,long custId,long academicYearId);
	int getTransportSmsCount(long custId,long academicYearId);
	
	List<VWStudentDailyAttendance> getStudentDailyAttendance(long custId, long classId, String date);
	long getStudentsMonthlyAttendanceByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,long studentId);
	
	int getTotalWorkingDaysByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,long studentId);
	
	void removeStudentDailyAttendance(long studentId,String startDate,String endDate);
	ClassTeacher getClassTeacherByAccountId(long accountId, long academicYearId);
	StaffDailyAttendance getStaffDailyAttendance(long accountId,String date);
	List getAllMessagesByAccountId(String messageStatus,long accountId,String academicYearId,Date messageDate);
	List<ViewStaffLeaveDetails> getAllFutureLeavesByStatusAndRoleName(long customerId,String leaveStatus,String roleName,long academicYearId);
	void updateRoleByUserId(long roleId,long userId);
	public List<BigInteger> getTeacherIdsByStudyClassSubject(long custId,long academicYearId,long studyClassId,long subjectId);
}