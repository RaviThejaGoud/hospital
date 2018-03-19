package com.urt.service.manager.impl.staff;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.google.gson.Gson;
import com.hyniva.sms.ws.vo.LeavesVO;
import com.hyniva.sms.ws.vo.ReminderVO;
import com.hyniva.sms.ws.vo.ReplyScrapMessageMainVO;
import com.hyniva.sms.ws.vo.ReplyScrapMessageVO;
import com.hyniva.sms.ws.vo.ScrapMessageVO;
import com.hyniva.sms.ws.vo.SendMessageVO;
import com.hyniva.sms.ws.vo.TaskDetailsVO;
import com.hyniva.sms.ws.vo.TaskHistoryVO;
import com.hyniva.sms.ws.vo.attendance.StaffAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StaffDailyAttendanceMainVO;
import com.hyniva.sms.ws.vo.attendance.StaffDailyAttendanceVO;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.interfaces.staff.StaffDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.HostelStudentDailyAttendance;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.Reminder;
import com.urt.persistence.model.common.ReplyScrapMessage;
import com.urt.persistence.model.common.Salary;
import com.urt.persistence.model.common.ScrapMessage;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.TaskDetails;
import com.urt.persistence.model.common.TaskHistory;
import com.urt.persistence.model.common.VWStudentAttendance;
import com.urt.persistence.model.common.VWStudentDailyAttendance;
import com.urt.persistence.model.common.ViewTaskDetailsAndTaskHistory;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.HostelLeave;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.KBankRating;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.exam.QuizQuestion;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.PromoteClass;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStaffLeaveDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.thread.SendSMSThread;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.jrexception.JRExceptionClient;


/**
 * Implementation of UserManager interface.</p>
 * 
 * <p>
 * <a href="UserManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class StaffManagerImpl extends UniversalManagerImpl implements StaffManager {
	
	@Autowired
    private StaffDao staffDao;
	
	protected Set<String> mobileNumbersSet;

	public ClassTeacher getTeacherByClassandSubjectId(long staffId,long classId,long subjectId,String staffActiveStatus){
		 return staffDao.getTeacherByClassandSubjectId(staffId,classId,subjectId,staffActiveStatus);
	}

	public Set<String> getMobileNumbersSet() {
        if(ObjectFunctions.isNullOrEmpty(this.mobileNumbersSet)){
                this.mobileNumbersSet=new HashSet<String>();
        }
        return this.mobileNumbersSet;
	}

	/**
	 * @param mobileNumbersSet the mobileNumbersSet to set
	 */
	public void setMobileNumbersSet(Set<String> mobileNumbersSet) {
	        this.mobileNumbersSet = mobileNumbersSet;
	}
	

	public ClassTeacher getStudyClassSubject(long classId, long staffId){
		 return staffDao.getStudyClassSubject(classId, staffId);
	}
	public List<Student> getStudentByClassId(long classId){
		 return staffDao.getStudentByClassId(classId);
	}
	public Student getStudentById(long studentId){
		 return staffDao.getStudentById(studentId);
	}
	public List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId,long academicYearId) {
		 return staffDao.getLeavesListByAccountId(accountId,leaveStatus,customerId,academicYearId);
	}
	public List getAllCalendarEventsByCustomerId(long customerId) {
		 return staffDao.getAllCalendarEventsByCustomerId(customerId);
	}
	public List getEventInvitedUserListByEventId(String eventId) {
		 return staffDao.getEventInvitedUserListByEventId(eventId);
	}

	public List getEventsByDate(String eventDate){
		 return staffDao.getEventsByDate(eventDate);
	}
	public List<ClassTeacher> getTeacherSubjectsByIdAndAcademicYear(long staffId,long academicYearId,long custId){
		 return staffDao.getTeacherSubjectsByIdAndAcademicYear(staffId,academicYearId,custId);
	}
	public List getTeacherSubjectsByStaffAndClassId(long accountId,long classId,long academicYearId){
		 return staffDao.getTeacherSubjectsByStaffAndClassId(accountId,classId,academicYearId);
	}
	public Student getStudentByAcountId(long accountId){
		 return staffDao.getStudentByAcountId(accountId);
	}
	public List<ViewStudentPersonAccountDetails> getStudentByClassIdAndStaffId(long classId,long customerId,long academicYearId,String status){
		 return staffDao.getStudentByClassIdAndStaffId(classId,customerId,academicYearId, status);
	}
	public Student getStudentsByAccountId(long stuAccountId){
		 return staffDao.getStudentsByAccountId(stuAccountId);
	}
	public List<ViewStudentPersonAccountDetails> getViewStudentPersonAccountDetailsByStudyClassIdandStatus(long classId,String status,String academicYearId) {
		 return staffDao.getViewStudentPersonAccountDetailsByStudyClassIdandStatus(classId,status,academicYearId);
	}
	public List getClassEventListByEventId(String eventId) {
		 return staffDao.getClassEventListByEventId(eventId);
	}

	public List<ViewStudentLeaveDetails> getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId) {
		 return staffDao.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(customerId,leaveStatus,roleName,supervisorId,academicYearId);
	}
	public ViewStudentLeaveDetails getViewStudentLeaveDetailsByLeaveId(long leaveid, long customerId) {
		 return staffDao.getViewStudentLeaveDetailsByLeaveId(leaveid,customerId);
	}
	public Role getRoleDetailsByRoleName(String roleName) {
		 return staffDao.getRoleDetailsByRoleName(roleName);
	}
	public List getAllStudentStatus(String studentId){
	     return staffDao.getAllStudentStatus(studentId);
	}
	 
	public List getStudyClassSubjectByClassid(long classId)
	{
	     return staffDao.getStudyClassSubjectByClassid(classId);
	}
	public List getStudySubjectsId(String subjectId,String classId)
	{
	     return staffDao.getStudySubjectsId(subjectId,classId);
	}
	public List getTeacherSubjects(String subjectId)
	{
	     return staffDao.getTeacherSubjects(subjectId);  
	}
	public void removeRegistrationStudentEvent(String accountId, String eventId,String eventAcceptedStatus) {
		 staffDao.removeRegistrationStudentEvent(accountId, eventId,eventAcceptedStatus);  
	}
	public List getStudentsByIds(String studentIds){
		 return staffDao.getStudentsByIds(studentIds);  
	}
	public List getNotInvitedStudents(String accountIds){
		 return staffDao.getNotInvitedStudents(accountIds);  
	}
	public List getAllLeavesListByAccountId(long accountId, long customerId) {
		return staffDao.getAllLeavesListByAccountId(accountId, customerId);
	}
	
	public ClassTeacher getClassTeacherByStaffIdandAcademicYear(long staffId,long academicYear,long custId){
		 return staffDao.getClassTeacherByStaffIdandAcademicYear(staffId,academicYear,custId);
	}
	public ViewStaffPersonAccountDetails getFindStaffByIdOrName(String username,String firstName, long customerId) {
		return staffDao.getFindStaffByIdOrName(username, firstName, customerId);
	}
   public List getMyMessagesByReceiverAccountIdAndIsParent(String receiverAccountId, long customerId,String isParent) {
		return staffDao.getMyMessagesByReceiverAccountIdAndIsParent(receiverAccountId, customerId,isParent);
	}
	public List<ViewClassExamDetails> getClassTeacherExamSchedules(long classId,long academicYear){
		return staffDao.getClassTeacherExamSchedules(classId,academicYear);
	}
	public String getClassIdByNameAndSection(String className,String section,long custId,long academicYear){
		return staffDao.getClassIdByNameAndSection(className,section,custId,academicYear);
	}
	public List getFindStudentsListByIdOrName(String rollNumber,String firstName, long customerId,long academicYearId,long studyClassId) {
		return staffDao.getFindStudentsListByIdOrName(rollNumber,firstName, customerId,academicYearId,studyClassId);
	}
	public List getAllMessagesByMessageStatus(String messageStatus,long customreId,String academicYearId,Date messageDate) {
		return staffDao.getAllMessagesByMessageStatus(messageStatus, customreId,academicYearId,messageDate);
	}
	public String getExamTypeIdByExamType(String examType,long custId,String academicYear){
		return staffDao.getExamTypeIdByExamType(examType,custId,academicYear);
	}
	
	public String getSubjectIdBySubjectName(String subjectName,long custId,long academicYear){
		return staffDao.getSubjectIdBySubjectName(subjectName,custId,academicYear);
	}
	public ExamSchedules getExamSchedule(String studyClassId,String examTypeId,String subjectId,long academicYear,long custId){
		return staffDao.getExamSchedule(studyClassId,examTypeId,subjectId,academicYear,custId);
	}

	public String getClassIdByName(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getViewStudentPersonAccountDetailsByClassNameAndSectionId(
			long classId) {
		// TODO Auto-generated method stub
		return null;
	}
	public List getEventAcceptedEventsByEventIdAndAccountId(String eventIds,long accountId, String eventAccepted) {
		return staffDao.getEventAcceptedEventsByEventIdAndAccountId(eventIds,accountId,eventAccepted);
	}
	public List getAllCalendarEventsByEventIds(String eventsIds,long customerId) {
		return staffDao.getAllCalendarEventsByEventIds(eventsIds,customerId);
	}
	public List getClassEventListByClassId(long classId) {
		return staffDao.getClassEventListByClassId(classId);
	}
	public List getTeacherExamSchedulesByClassIdandSubjectId(long classId,long subjectId,long academicYear){
		return staffDao.getTeacherExamSchedulesByClassIdandSubjectId(classId,subjectId,academicYear);
	}
	@Transactional
	public List getFindStaffListByIdOrName(String username, String firstName,long customerId) {
		return staffDao.getFindStaffListByIdOrName(username, firstName, customerId);
	}
	public ViewStaffPersonAccountDetails getStaffBirthDaysByAccountId(long staffId, long customerId,long staffAccountId) {
		return staffDao.getStaffBirthDaysByAccountId(staffId, customerId,staffAccountId);
	}
	public List<ViewStaffPersonAccountDetails> getAllStaffBirthDaysList(String date1,long custId) {
		return staffDao.getAllStaffBirthDaysList(date1,custId);
	}
	public List<ViewStudentPersonAccountDetails> getAllStudentBirthDaysList(String date1,long custId) {
		return staffDao.getAllStudentBirthDaysList(date1,custId);
	}
	public List getMyMessagesBySenderAccountIdAndIsParent(String receiverAccountId, long customerId, String isParent) {
		return staffDao.getMyMessagesBySenderAccountIdAndIsParent(receiverAccountId, customerId, isParent);
	}
	public List getstudentMarksListByExamScheduleId(long scheduleId){
		return staffDao.getstudentMarksListByExamScheduleId(scheduleId);
	}
	public List<ViewStudentPersonAccountDetails> getViewStudentPersonAccountDetailsByStudyClassIdandAccount(long classId,String status,long accountId,String academicYearId) {
		 return staffDao.getViewStudentPersonAccountDetailsByStudyClassIdandAccount(classId,status,accountId,academicYearId);
	}
	public double getPassStudentsCount(long scheduleId,double minMarks){
		 return staffDao.getPassStudentsCount(scheduleId,minMarks);
	}
	public double getFailStudentsCount(long scheduleId,double minMarks){
		 return staffDao.getFailStudentsCount(scheduleId,minMarks);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Set getExamTypeIdsByClassIdandAcademicYear(long classId,long academicYear){
		 return staffDao.getExamTypeIdsByClassIdandAcademicYear(classId,academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getExamTypesByExamTypeIds(String typeIds,long academicYear){
		 return staffDao.getExamTypesByExamTypeIds(typeIds,academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassExamSchedulesByExamTypeId(long examTypeId,long classId,long custId,long academicYear){
		return staffDao.getClassExamSchedulesByExamTypeId(examTypeId,classId,custId,academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public SchoolHolidays checkSchoolHolidayBySelectedDate(String selectedDate,long customerId,long academicYearId) {
		return staffDao.checkSchoolHolidayBySelectedDate(selectedDate, customerId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<KBank> getKBankBySearchKewordsKBankTypeId(String searchKeywords,long kBankTypeId,long custId){
		return staffDao.getKBankBySearchKewordsKBankTypeId(searchKeywords,kBankTypeId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ScrapMessage saveScrapMessage(ScrapMessage scrapMessage) {
		return  staffDao.saveScrapMessage(scrapMessage);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ScrapMessage> getAllScrapMessagesListBySenderAccId(long senderAccountId,String status,String replyStatus,long customerId,String academicYearId) {
		return staffDao.getAllScrapMessagesListBySenderAccId(senderAccountId, status, replyStatus, customerId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ScrapMessage> getAllScrapMessagesListByReceiverAccId(long receiverrAccountId,String status, String replyStatus, long customerId,String academicYearId,String messageType) {
		return staffDao.getAllScrapMessagesListByReceiverAccId(receiverrAccountId,status, replyStatus, customerId,academicYearId,messageType);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllUsersByFirstNameOrLastName(String name, long customerId) {
		return staffDao.getAllUsersByFirstNameOrLastName(name,customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMyFavouritiesByUserId(long accountId){
		return staffDao.getMyFavouritiesByUserId(accountId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMyFavouritiesByUserIdAndKBankId(long accountId,long kBankId){
		return staffDao.getMyFavouritiesByUserIdAndKBankId(accountId,kBankId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public KBankRating getKBankRateByKBankId(String kBankId){
		return staffDao.getKBankRateByKBankId(kBankId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getPlayListVideosByKeywords(String keywords) {
		return staffDao.getPlayListVideosByKeywords(keywords);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getQuizListWithCustId(long custId){
		return staffDao.getQuizListWithCustId(custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getQuizQuestionListWithCategoryId(long quizId,long custId){
		return staffDao.getQuizQuestionListWithCategoryId(quizId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public QuizQuestion getQuizQuestionId(long questionId,long custId){
		return staffDao.getQuizQuestionId(questionId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getQuestionAnswersWithQuestionId(long questionId,long custId){
		return staffDao.getQuestionAnswersWithQuestionId(questionId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public QuestionAnswer getCorrectAnswerWithQuestionId(long questionId,long custId){
		return staffDao.getCorrectAnswerWithQuestionId(questionId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllAlertsByAlertStatus(long customerId,String alertType,String receiverType,boolean isAdmin,String academicYearId) {
		return staffDao.getAllAlertsByAlertStatus(customerId,alertType, receiverType,isAdmin,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List deleteAbsentStudents(String attenDate,long classId,long customerId) {
		return staffDao.deleteAbsentStudents(attenDate,classId,customerId);
	}
	public int getUpdateQuizQuestionStatus(long quizId,long custId,String selectdDate){
		return staffDao.getUpdateQuizQuestionStatus(quizId,custId,selectdDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getUpdateStudentQuizStatus(long custId){
		return staffDao.getUpdateStudentQuizStatus(custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getQuizQuestionListWithStudentList(long quizId,long custId){
		return staffDao.getQuizQuestionListWithStudentList(quizId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getQuizQuestionListWithCategoryIdAndTeacherId(long quizId,long staffId,long custId){
		return staffDao.getQuizQuestionListWithCategoryIdAndTeacherId(quizId,staffId,custId);
	}
	public String studentFailInAnnuallyExam(long studentId,long examTypeId,String minMarks){
		return staffDao.studentFailInAnnuallyExam(studentId,examTypeId,minMarks);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public PromoteClass getPromoteClassDetails(long classId,long custId){
		return staffDao.getPromoteClassDetails(classId,custId);
	}
	public String getStudentCountByClassId(long classId,long custId){
		return staffDao.getStudentCountByClassId(classId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentMarksDetails getStudentsMarksByClassIdandExamtypeId(long classId,long examTypeId,long custId,String academicYear,long studentId,long subjectId){
		return staffDao.getStudentsMarksByClassIdandExamtypeId(classId,examTypeId,custId,academicYear,studentId,subjectId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMarksByClassIdandExamtypeId(long classId,long custId){
		return staffDao.getMarksByClassIdandExamtypeId(classId,custId);
	}
	public String getStudentCountByClassNameClassId(long ClassNameClassId,long custId){
		return staffDao.getStudentCountByClassNameClassId(ClassNameClassId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getExamScheduleIdsByAcademicYearandClassIdandExamTypeId(String academicYear,String classId ,String examTypeId){
		return staffDao.getExamScheduleIdsByAcademicYearandClassIdandExamTypeId(academicYear,classId ,examTypeId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStudentMarksChangesByExamScheduleIds(String scheduleIds){
		return staffDao.getAllStudentMarksChangesByExamScheduleIds(scheduleIds);
	}
	public int updateStudentMarksByStudentIdandScheduleId(String studentId,String scheduleId,String obtainedMarks){
		return staffDao.updateStudentMarksByStudentIdandScheduleId(studentId,scheduleId,obtainedMarks);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getTeacherExamScheduleIdsByClassIdandSubjectId(long classId,long subjectId,long academicYear){
		return staffDao.getTeacherExamScheduleIdsByClassIdandSubjectId(classId,subjectId,academicYear);
	}
	public void  removeStudentMarksChangesByClassIdandSubjectIdandAcademicYear(String scheduleIds){
		staffDao.removeStudentMarksChangesByClassIdandSubjectIdandAcademicYear(scheduleIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentMarksDetails getStudentsMarksByStudentIdandExamTypeIdandSubjectId(long examTypeId,long custId,long studentId,long subjectId){
		return staffDao.getStudentsMarksByStudentIdandExamTypeIdandSubjectId(examTypeId,custId,studentId,subjectId);
	}
	 
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ReplyScrapMessage> getAllReplyMessagesListByScrapId(long scrapid, long custId)
	{
		return staffDao.getAllReplyMessagesListByScrapId(scrapid,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<VWStudentAttendance> getStudentsAttendance(long custId, long academicYearId,int month,long classId, long classSectionId, String date) {
		return staffDao.getStudentsAttendance(custId, academicYearId, month, classId,classSectionId, date);
	}
	public long getStudentsAttendanceByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,String attendanceStatus){
		return staffDao.getStudentsAttendanceByClassSectionIdAndMonthId(classSectionId,monthId,academicYearId,custId,attendanceStatus);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStaffAttendanceByPresentStatusAndLeaveRequestStaus(long custId,long academicYearId, long staffId, String present,String leaveRequest){
            return staffDao.getStaffAttendanceByPresentStatusAndLeaveRequestStaus(custId,academicYearId,staffId,present,leaveRequest);	
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentMarksDetails> getLatestUpdatedStudentMarksExamTypeIdsByClassSectionIdAndCustId(long classSectionId,long custId){
	       return staffDao.getLatestUpdatedStudentMarksExamTypeIdsByClassSectionIdAndCustId(classSectionId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getTeacherSubjectsByStaffIdAndClassIdGroupByclassIdSubjectId(long staffId,long classId,long academicYearId){
		return staffDao.getTeacherSubjectsByStaffIdAndClassIdGroupByclassIdSubjectId(staffId,classId,academicYearId);
	}
	public double getStudentsTotalMarksByClassIdTypeIdAndSubjId(long classSectionId,long examTypeId,long subjectId,long academicYearId){
		return staffDao.getStudentsTotalMarksByClassIdTypeIdAndSubjId(classSectionId,examTypeId,subjectId,academicYearId);
	}
	public long getStudentsCountByClassIdTypeIdAndSubjId(long classSectionId,long examTypeId,long subjectId,long academicYearId){
		return staffDao.getStudentsCountByClassIdTypeIdAndSubjId(classSectionId,examTypeId,subjectId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getIsClassTeacherByClassSectionAndStaffIdAndAcademicYear(long classSectionId,long staffId,long academicYearId){
		return staffDao.getIsClassTeacherByClassSectionAndStaffIdAndAcademicYear(classSectionId,staffId,academicYearId);
	}
	public long getPassStudentsCount(long classSectionId,long examTypeId,long subjectId,long academicYearId,double passMarks){
		return staffDao.getPassStudentsCount(classSectionId,examTypeId,subjectId,academicYearId,passMarks);
	}
	public long getFailStudentsCount(long classSectionId,long examTypeId,long subjectId,long academicYearId,double passMarks){
		return staffDao.getFailStudentsCount(classSectionId,examTypeId,subjectId,academicYearId,passMarks);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStaffPersonAccountDetails getStaffDetailsByAccountIdandStatus(long accountId,long academicYearId,String status,long custId) {
		return  staffDao.getStaffDetailsByAccountIdandStatus(accountId, academicYearId, status, custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ScrapMessage getScrapMessageBySenderAndReceiverId(long senderAccountId,long receiverAccountId,long customerId,long academicYearId) {
		return staffDao.getScrapMessageBySenderAndReceiverId(senderAccountId,receiverAccountId,customerId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getLatestSmsCount(long custId,long academicYearId){
		return staffDao.getLatestSmsCount(custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentLeaveDetails> getAllFutureStudentLeavesByStatusAndRoleNameAndSupervisorId(long customerId, String leaveStatus, String roleName,long supervisorId,long academicYearId){
		return staffDao.getAllFutureStudentLeavesByStatusAndRoleNameAndSupervisorId(customerId, leaveStatus, roleName,supervisorId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentMarksDetails> getStudentsMarksListByStudentIdandExamTypeIdandSubjectId(long examTypeId, long custId, long studentId, long subjectId) {
		return staffDao.getStudentsMarksListByStudentIdandExamTypeIdandSubjectId(examTypeId, custId, studentId, subjectId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentMarksDetails> getStudentsMarksListByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId, long custId, long studentId, long subjectId,long subTypeId){
		return staffDao.getStudentsMarksListByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(examTypeId, custId, studentId, subjectId, subTypeId);
	}

	public String getSUMStudentsMarksByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId, long custId, long studentId, long subTypeId) {
		return staffDao.getSUMStudentsMarksByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(examTypeId, custId, studentId, subTypeId);
	}
	public void removeStaffAttendanceByaccountIdAndDates(long accountId,String startDate,String endate,long custId,long academicYearId){
		staffDao.removeStaffAttendanceByaccountIdAndDates(accountId,startDate,endate,custId,academicYearId);
	}
	public int getTransportSmsCount(long custId,long academicYearId){
		return staffDao.getTransportSmsCount(custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<VWStudentDailyAttendance> getStudentDailyAttendance(long custId, long classId,String date) {
		return staffDao.getStudentDailyAttendance(custId, classId, date);
	}
	public long getStudentsMonthlyAttendanceByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,long studentId)
	{
		return staffDao.getStudentsMonthlyAttendanceByClassSectionIdAndMonthId(classSectionId,monthId,academicYearId,custId,studentId);
		
	}
	public int getTotalWorkingDaysByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,long studentId)
	{
		return staffDao.getTotalWorkingDaysByClassSectionIdAndMonthId(classSectionId,monthId,academicYearId,custId,studentId);
	}
	
	public void removeStudentDailyAttendance(long studentId,String startDate,String endDate)
	{
		staffDao.removeStudentDailyAttendance(studentId,startDate,endDate);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getClassTeacherByAccountId(long accountId, long academicYearId){
		return staffDao.getClassTeacherByAccountId(accountId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StaffDailyAttendance getStaffDailyAttendance(long accountId,String date)
	{
		return staffDao.getStaffDailyAttendance(accountId,date);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllMessagesByAccountId(String messageStatus,long customreId,String academicYearId,Date messageDate) {
		return staffDao.getAllMessagesByAccountId(messageStatus, customreId,academicYearId,messageDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffLeaveDetails> getAllFutureLeavesByStatusAndRoleName(long customerId,String leaveStatus,String roleName,long academicYearId){
		return staffDao.getAllFutureLeavesByStatusAndRoleName(customerId, leaveStatus,roleName,academicYearId);
	}
 
	public Map<String,String> disableStaff(long staffId,String reasonForDisable, long operatorId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'disableStaff' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
			if(staffId != 0 && StringFunctions.isNotNullOrEmpty(reasonForDisable)){
				Staff staff=(Staff) this.get(Staff.class, staffId);
				if(!ObjectFunctions.isNullOrEmpty(staff))
				{
					if(!ObjectFunctions.isNullOrEmpty(reasonForDisable)){
						staff.setDescription(reasonForDisable);
					}
				}
				else
				{
					msg.put("1", "No staff exists with staff identifier");
				}
				User user = (User) staff.getAccount();
				if(!ObjectFunctions.isNullOrEmpty(user)){
					user.setAccountExpired(true);
					user.setLastUpdatedDate(new Date());
					user.setLastUpdatedById(operatorId);
					user.setLastAccessDate(new Date());
					user.setEnabled(false);
					user.setUsername(String.valueOf(user.getId()));
					user.setSecondaryUsername(String.valueOf(user.getId()));
					staff.setStatus("N");
					staff.setAccount(user);
					staff.setLastUpdatedById(operatorId);
					staff.setCreatedDate(new Date());
					this.save(staff);
					this.sendStaffNotificationWhenEditOrAdd(staff.getId()," Admin made "+user.getPerson().getFullPersonName()+" has inactive.","Admin made staff has inactive.");
					msg.put("0", "Staff Inactivated successfully");
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		return msg;
	}
	
	public void loadTeacher(Sheet sheet,String loadType, long custId,long loggedUserId) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'loadTeacher' method");
		}
		
		try {
			int rowSize = sheet.getRows();
			Cell cell;
			String[] acountRules = new String[3];
			cell = sheet.getCell(0, 0);
			acountRules[0] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(1, 0); 	
			acountRules[1] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(2, 0);
			acountRules[2] = StringFunctions.trim(cell.getContents());
			User staffAccount;
			Person staffPerson;
			Address staffAddress;
			Staff staffObj;
			User createduser=null;
			Staff staff=null;
			Salary salary =null;
			boolean isNotExist = true;
//	/		boolean isCreatePro = false;
			String cellContent;
			Role role=null;
			User staffNumberExist = null;
			//prepareAcademicYearId();
			Map<String,State> statelist = this.getCountryStates(0);
			int j=0;
			 if(loadType.equals("E"))
			 {
				  j=10;
			 }
			 else
			 {
				 j=2;
			 }
			StringBuffer newStaffCountMessage=new StringBuffer();
			newStaffCountMessage.append("(");
			StringBuffer failureMsg=new StringBuffer(); 
			StringBuffer query = null;
			String staffNumber = null;
			HashMap<String, Role> rolesMap = null;
			AcademicYear academicYear = this.getCurrentAcademicYear("Y", custId);
			List<Role> roles = this.getAll(Role.class);
			if(ObjectFunctions.isNotNullOrEmpty(roles)){
				rolesMap = new HashMap<String, Role>();
				for(Role rolesobj : roles){
					rolesMap.put(rolesobj.getDescription().toLowerCase(), rolesobj);
				}
				roles = null;
			}
			String userName =null;
			for (int i = j; i < rowSize; i++) {
				cell = sheet.getCell(0, i);
				cellContent = cell.getContents().trim();
				if(loadType.equals("E") || loadType.equals(""))
				{
				    boolean isFailedRecords = false;
					if (loadType.equals("E")) {
						if (!ObjectFunctions.isNullOrEmpty(sheet.getCell(34, i).getContents())) {
							long staffId = Long.valueOf(sheet.getCell(34, i).getContents());
							staff = (Staff) this.get(Staff.class,Long.valueOf(staffId));
							if(!ObjectFunctions.isNullOrEmpty(staff)){
					    			isFailedRecords=true;
					    			//failureMsg.append(sheet.getCell(0,i).getContents()+" ");
					    			isNotExist = false;
					    			//log.debug(failureMsg.append(sheet.getCell(0,i).getContents()+" "));
							}
						}else{
							continue;
						}
			    	}else{
			    		isNotExist = true;
			    	}
					if(isNotExist || !ObjectFunctions.isNullOrEmpty(staff)) 
					{
						if(ObjectFunctions.isNullOrEmpty(staff)){
			    			if(StringFunctions.isNotNullOrEmpty(sheet.getCell(Integer.valueOf(acountRules[1]), i).getContents()) && (StringFunctions.isNotNullOrEmpty(sheet.getCell(1,i).getContents().toLowerCase()) || StringFunctions.isNotNullOrEmpty(sheet.getCell(2,i).getContents().toLowerCase())) ){
			    				userName = StringFunctions.prepareUserName(acountRules[0].toLowerCase(), sheet.getCell(Integer.valueOf(acountRules[1]), i).getContents().toLowerCase(),sheet.getCell(Integer.parseInt(acountRules[2]), i).getContents().toLowerCase());
			    				User objUser= this.getUserByUserName(userName);	
			    				if (!ObjectFunctions.isNullOrEmpty(objUser)) 
			    				{
			    					continue;
			    				}
			    				isFailedRecords=true;
			    				objUser=null;
							}else{
								continue;
							}
			    			staffAccount = new User();
							staffPerson = new Person();
							staffAddress = new Address();
							staffObj = new Staff();
							//salary = new Salary();
						//	isCreatePro=true;
		    			}else{
		    				staffObj =(Staff) this.get(Staff.class,Long.valueOf(staff.getId()));
		    				staffAccount = (User) this.get(User.class,"custId="+custId+" and id="+staff.getAccount().getId());
		    				staffPerson = (Person) this.get(Person.class,Long.valueOf(staffAccount.getPerson().getId()));
		    				staffAddress = (Address) this.get(Address.class, Long.valueOf(staffAccount.getPrimaryAddress().getId()));
		    				salary=(Salary) this.get(Salary.class,"staffId="+staff.getId());
		    			}
						staffPerson.setFirstName(sheet.getCell(1, i).getContents());
						staffPerson.setLastName(sheet.getCell(2, i).getContents());
						if (loadType.equals("E")) {
							if (StringFunctions.isNullOrEmpty(sheet.getCell(34, i).getContents())){
								staffNumber = null;
							}else{ 
								staffNumber = sheet.getCell(34, i).getContents().trim();
								query = new StringBuffer("custId=").append(custId).append(" and staffNumber='").append(staffNumber).append("' and id!=")
								.append(staff.getAccount().getId());
								staffNumberExist = (User)this.get(User.class,query.toString());
								if(!ObjectFunctions.isNullOrEmpty(staffNumberExist)){
									failureMsg.append(staffPerson.getFirstName()).append(" ").append(staffPerson.getLastName()).append(",");
									staffNumber = null;
								}						
							}
						}else{
							if (StringFunctions.isNullOrEmpty(sheet.getCell(34, i).getContents())){
								staffNumber = null;
							}else{ 
								staffNumber = sheet.getCell(34, i).getContents().trim();
								query = new StringBuffer("custId=").append(custId).append(" and staffNumber='").append(staffNumber).append("'");
								staffNumberExist = (User)this.get(User.class,query.toString());
								if(!ObjectFunctions.isNullOrEmpty(staffNumberExist)){
									failureMsg.append(staffPerson.getFirstName()).append(" ").append(staffPerson.getLastName()).append(",");
									staffNumber = null;
								}
							}
						}
						SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
			            if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(4, i).getContents())){
			            	if(!ObjectFunctions.isNullOrEmpty(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(4, i).getContents()))){
			            	 if(DateFunctions.compareDateOfBirths(SDF.parse(SDF.format(new Date())),DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(4, i).getContents()))) 
			            	 staffPerson.setDateOfBirth(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(4, i).getContents()));
			            	}
			            }
						staffPerson.setGender(sheet.getCell(5, i).getContents().trim());
						staffPerson.setMaritalStatus(sheet.getCell(6, i).getContents().trim());
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(7, i).getContents()))
						staffPerson.setExperience(Double.valueOf(sheet.getCell(7, i).getContents()));
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(8, i).getContents())){
						 if(!ObjectFunctions.isNullOrEmpty(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(8, i).getContents()))){
							boolean equalDates = DateFunctions.compareDatesByCompareTo(SDF.parse(SDF.format(new Date())),DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(8, i).getContents()));
			            	if(equalDates)
			            	staffPerson.setDateOfJoining(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(8, i).getContents()));
							}
			            }
						staffAddress.setEmail(String.valueOf(sheet.getCell(11, i).getContents()));
						staffPerson.setMobileNumber(String.valueOf(sheet.getCell(12, i).getContents()));
						staffAddress.setAddressLine1(String.valueOf(sheet.getCell(13, i).getContents()));
						/*if(StringFunctions.isNotNullOrEmpty(sheet.getCell(14, i).getContents())){
							staffAddress.setAddressLine2(String.valueOf(sheet.getCell(14, i).getContents()));
						}
						*/staffAddress.setCity(String.valueOf(sheet.getCell(14, i).getContents()));
						if(!StringFunctions.isNullOrEmpty(sheet.getCell(15, i).getContents())){
						staffAddress.setStateId(statelist.get(sheet.getCell(15, i).getContents().trim()).getId());
						staffAddress.setState(statelist.get(sheet.getCell(15, i).getContents().trim()).getStateCode());
						} 
						if (!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(16, i).getContents().trim()))) {
						staffAddress.setPostalCode(String.valueOf(sheet.getCell(16, i).getContents().trim()));
						}
						if(!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(17, i).getContents()))){
							staffPerson.setBloodGroup(String.valueOf(sheet.getCell(17, i).getContents()).toLowerCase());
						}
						if(!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(18, i).getContents()))){
							staffPerson.setPanNumber(String.valueOf(sheet.getCell(18, i).getContents()));
						}
						if(!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(19, i).getContents()))){
							staffPerson.setGpfNumber(String.valueOf(sheet.getCell(19, i).getContents()));
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(20, i))){
							staffPerson.setOfficeNumber(sheet.getCell(20, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(21, i).getContents()))){
							staffPerson.setPhoneNumber(String.valueOf(sheet.getCell(21, i).getContents()));
						}
						if(!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(22, i).getContents()))){
							staffPerson.setDesignation(String.valueOf(sheet.getCell(22, i).getContents()));
						}
						CommonType religion  =null;
						if(!StringFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(23, i).getContents().toUpperCase()))){
							String type ="RELIGION";
							  religion =(CommonType)this.getCommonType(custId,type,String.valueOf(sheet.getCell(23, i).getContents().toUpperCase()));
								if(ObjectFunctions.isNullOrEmpty(religion)){
									religion = new CommonType();
									religion.setCustId(custId);
									religion.setSkillTypeName(String.valueOf(sheet.getCell(23, i).getContents().toUpperCase()));
									religion.setType(type);
									//religion.setVersion(0);
									this.save(religion);	
								}
								if (!ObjectFunctions.isNullOrEmpty(religion)) {
									staffPerson.setReligionId(religion.getId());
								}
								religion = null;
					   	}
						MotherTongue motherTongue  =null;
						if (!StringFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(24, i).getContents().toUpperCase()))) {
							motherTongue = (MotherTongue) this.get(MotherTongue.class,"name= '"+sheet.getCell(24, i).getContents().trim()+"'");
							/*if (ObjectFunctions.isNullOrEmpty(motherTongue)) {
								motherTongue = new MotherTongue();
								motherTongue.setName(String.valueOf(sheet.getCell(25, i).getContents().trim()));
								motherTongue.setVersion(0);
								adminManager.save(motherTongue);
							}*/
							if (!ObjectFunctions.isNullOrEmpty(motherTongue)) {
								staffPerson.setMotherToungId(motherTongue.getId());
							}
							motherTongue = null;
						}
						staffPerson.setNationality(sheet.getCell(25, i).getContents());
						CastSettings castSettingsObj=null;
	                    SubCastSettings subCastSettingsObj=null;
						if (!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(26, i).getContents()))) {
							castSettingsObj = (CastSettings) this.getCastNames(String.valueOf(sheet.getCell(26, i).getContents().toUpperCase().trim()),custId);
							if (ObjectFunctions.isNullOrEmpty(castSettingsObj)) {
								castSettingsObj=new CastSettings();
								castSettingsObj.setCustId(custId);
								castSettingsObj.setCastName(sheet.getCell(26, i).getContents().toUpperCase().trim());
								castSettingsObj.setCreatedById(loggedUserId);
								castSettingsObj.setCreatedDate(new Date());
							}else
								castSettingsObj.setLastUpdatedById(loggedUserId);
							if (!ObjectFunctions.isNullOrEmpty(castSettingsObj)) {
								if (!ObjectFunctions.isNullOrEmpty(String.valueOf(sheet.getCell(27, i).getContents()))) {
									subCastSettingsObj = (SubCastSettings) this.getSubCast(custId,String.valueOf(sheet.getCell(27, i).getContents().toUpperCase().trim()),castSettingsObj.getId());
									if (ObjectFunctions.isNullOrEmpty(subCastSettingsObj)) {
										subCastSettingsObj=new SubCastSettings();
										subCastSettingsObj.setSubCastName(sheet.getCell(27, i).getContents().toUpperCase().trim());
										subCastSettingsObj.setCustId(custId);
										subCastSettingsObj.setCreatedById(loggedUserId);
										subCastSettingsObj.setCreatedDate(new Date());
									}else
										subCastSettingsObj.setLastUpdatedById(loggedUserId);
									if(!ObjectFunctions.isNullOrEmpty(subCastSettingsObj)){
										castSettingsObj.addSubCast(subCastSettingsObj);
									}
									castSettingsObj.setLastAccessDate(new Date());
									castSettingsObj.setLastUpdatedDate(new Date());
									if(castSettingsObj.getId() == 0 || subCastSettingsObj.getId() == 0 )
											castSettingsObj=(CastSettings)this.save(castSettingsObj);
									if (!ObjectFunctions.isNullOrEmpty(subCastSettingsObj)) {
										subCastSettingsObj.setLastAccessDate(new Date());
										subCastSettingsObj.setLastUpdatedDate(new Date());
										staffPerson.setCastId(castSettingsObj.getId());
										staffPerson.setSubCastId(subCastSettingsObj.getId());
									}
								}
							}
						}
						staffPerson.setBankName(sheet.getCell(28, i).getContents());
						staffPerson.setBankAccountNumber(sheet.getCell(29, i).getContents());
						staffPerson.setBankBranchName(sheet.getCell(30, i).getContents());
						staffPerson.setFamilyDoctor(sheet.getCell(31, i).getContents());
						staffPerson.setPrefferedHospital(sheet.getCell(32, i).getContents());
						staffAccount.setCustId(custId);
						if(ObjectFunctions.isNullOrEmpty(staff)){
							staffAccount.setUsername(userName.toLowerCase());
							staffAccount.setSecondaryUsername(userName.toLowerCase());
							staffAccount.setPassword(PasswordUtils.passwordEncoder(userName.toLowerCase(),null));
							if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(33, i).getContents())){
								staffAccount.setBioMetricId(Integer.parseInt(sheet.getCell(33, i).getContents()));
							}
						}else{
							staffAccount.setPassword(PasswordUtils.passwordEncoder(staffAccount.getUsername().toLowerCase(),null));
							if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(33, i).getContents())){
								staffAccount.setBioMetricId(Integer.parseInt(sheet.getCell(33, i).getContents()));
							}
						}
						//column35
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(35, i).getContents())){
							staffPerson.setAadharNumber(sheet.getCell(35, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(36, i).getContents())){
							staffPerson.setPassportNumber(sheet.getCell(36, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(37, i).getContents())){
							staffPerson.setIfscCode(sheet.getCell(37, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(38, i).getContents()) && "Temporary".equalsIgnoreCase(sheet.getCell(38, i).getContents().trim())){
							staffObj.setStaffType("C");
						}else{
							staffObj.setStaffType("P");
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(39, i).getContents())){
							staffPerson.setStaffUniqueNumber(sheet.getCell(39, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(40, i).getContents())){
							staffObj.setOutSideSchoolDuty(sheet.getCell(40, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(41, i).getContents())){
							staffObj.setSchoolMess(sheet.getCell(41, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(42, i).getContents())){
							staffPerson.setSalaryPaymentMode(sheet.getCell(42, i).getContents());
						}
						
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(43, i).getContents())){
							staffObj.setStaffGrade(sheet.getCell(43, i).getContents());
						}
						
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(44, i).getContents())){
							staffPerson.setStaffLocation(sheet.getCell(44, i).getContents());
						}
						
						
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(45, i).getContents())){
							staffPerson.setFatherName(sheet.getCell(45, i).getContents());
						}
						if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(46, i).getContents())){
							staffPerson.setAnotherMobileNumber(sheet.getCell(46, i).getContents());
						}
						staffAccount.setStaffNumber(staffNumber);
						staffAccount.setPerson(staffPerson);
						staffAccount.setPrimaryAddress(staffAddress);
						//role=this.getRoleByName(cellContent); 
						
						if(!ObjectFunctions.isNullOrEmpty(cellContent)){
							role=rolesMap.get(cellContent.toLowerCase()); 
						}
							
						if(ObjectFunctions.isNullOrEmpty(role))
						{
							newStaffCountMessage.append(cellContent);
							newStaffCountMessage.append(",");
							continue;
						}
						staffAccount.addRole(role);
						Customer customer = this.getCustomerByCustId(custId);
						createduser = (User) this.save(staffAccount);
						if (!ObjectFunctions.isNullOrEmpty(createduser)) {
							isFailedRecords=true;
							staffObj.setAccount(createduser);
							staffObj.setCustId(custId);
							staffObj.setOrganizationTypeId(customer.getOrgnizationTypeId());
							staffObj.setAcademicYear(academicYear);
							staffObj.setStatus(Constants.YES_STRING);
							staffObj.setQualification1(String.valueOf(sheet.getCell(9, i).getContents()));
							if(StringFunctions.isNotNullOrEmpty(sheet.getCell(10, i).getContents()))
							{
								if(!ObjectFunctions.isNullOrEmpty(salary)){
									salary.setCustId(custId);
									salary.setLastUpdatedById(custId);
									salary.setLastAccessDate(new Date());
									if(sheet.getCell(10, i).getContents().length() <=8)
									salary.setSalary(Double.valueOf(sheet.getCell(10, i).getContents()));
								}else{
									if(sheet.getCell(10, i).getContents().length() <=8){
										salary = new Salary();
										salary.setSalary(Double.valueOf(sheet.getCell(10, i).getContents()));
										salary.setCustId(custId);
									    salary.setCreatedById(custId);
										salary.setLastUpdatedById(custId);
										salary.setLastAccessDate(new Date());
									}
								}
								int monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
								SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
								int year = Integer.valueOf(simpleDateformat.format(new Date()));
								salary.setMonth(monthNum);
								salary.setYear(year);
								staffObj.addSalaryDetailsSettings(salary);
								salary=null;
							}
							this.save(staffObj);
						}else{
							isFailedRecords=true;
							continue;
						}
						log.debug("Teacher Name:"+staffAccount.getUsername());
						staffAccount = null;
						staffPerson = null;
						staffAddress = null;
						if(isFailedRecords){
							continue;
		    			}else{
		    				break;
		    			}
					}else{
						log.debug("***********Student Not Loaded - Username already available : " +acountRules[0] + sheet.getCell(Integer.valueOf(acountRules[1]), i).getContents() + sheet.getCell(Integer.valueOf(acountRules[2]), i).getContents() + " Name : " + sheet.getCell(1,i).getContents() + " Row : " + i);
					}
				}
				cell = null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public Map<String,String> addOrUpdateStaffAttendance(String strFormData, long academicYearId, long custId, long userId,String sms){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addOrUpdateStaffAttendance' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
		//	String mobileNumbers;
			JSONObject formData = new JSONObject(strFormData);
			if (ObjectFunctions.isNullOrEmpty(formData)) {
				msg.put("1", "Attendance is not registered due to system error. Please contact System Administrator");
				return msg;
			}
			boolean checkSMS = false;
			JSONObject json;
			//String month;
			int monthNum;
			StaffDailyAttendance attendance = null;
		//	String msgContent = null;
			String attendanceDate=(String) formData.get("attendanceDate");
			JSONArray attendanceData = (JSONArray) formData.get("data");
			Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, attendanceDate);
			if (ObjectFunctions.isNullOrEmpty(aDate)) {
				aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,attendanceDate));
			}
		//	month = new SimpleDateFormat("MMM").format(aDate);
			monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(aDate));
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
			String year = simpleDateformat.format(aDate);
			 if(!ObjectFunctions.isNullOrEmpty(aDate)) 
			 { 
		 	 // List  holidaysList = dao.getHolidaysListByMonthIdForAttendance(monthNum, academicYearId, year, aDate,custId,0,"S");
		 	  List<SchoolHolidays> holidaysList = this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate),null,null,null,null,null,monthNum,"holidayDateEqual",year);
			 //List holidaysList = dao.getHolidaysListByMonthIdForAttendance(monthNum,academicYearId, year, aDate,custId); //here ST is student ,s staff ,ch classWiseHolidays
			 if(!ObjectFunctions.isNullOrEmpty(holidaysList)) 
			 {
				 msg.put("1", "Today Is Holiday");
				 return msg;
			  } 
			 }
			Customer customer = this.getCustomerByCustId(custId);
			String attendanceStatus=null;
			User loggedUser = (User) this.get(User.class, userId);
			HashSet<String> mobileNumbersSet = null;
			User staffAcc = null;
			long accountId= 0 ;
			Staff staff = null;
			log.debug("aDate ::" + aDate);
			log.debug("attendanceData.length() : "+ attendanceData.length());
			Messages objMsg = null;
			 Date todayDate =  new Date();
			 Map<Messages, Set<String>> messageMobileMap = new HashMap<Messages, Set<String>>();
			 AcademicYear academicYear = this.getCurrentAcademicYear("Y", custId);
			 SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			int availableSmsCount=this.getAvailableSmsCount(custId,academicYearId);
			String captureAttendance =academicYear.getCaptureAttendanceForStaff();
			for (int i = 0; i < attendanceData.length(); i++) {
				json = attendanceData.getJSONObject(i);
				if (!ObjectFunctions.isNullOrEmpty(json)) {
					log.debug("i : " + i);
					attendanceStatus = (String) json.get("status");
					String astatus = null;
					if(!json.isNull("accountId"))
						accountId = json.getLong("accountId");
					if(accountId != 0)
					{
						//int sentSmsCount=this.getTotalSmsCount(custId,academicYearId);
						//int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
						if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(sms)){
							objMsg = new Messages();
							objMsg.setCreatedById(userId);
							objMsg.setCreatedDate(new Date());
							objMsg.setLastAccessDate(new Date());
							objMsg.setLastUpdatedDate(new Date());
							objMsg.setStatus("A");
							objMsg.setSenderName(loggedUser.getUserRoleDescription());
							if (!ObjectFunctions.isNullOrEmpty(customer)) {
								objMsg.setCustomer(customer);
							}
							objMsg.setSmsSenderId(customer.getSender());
							objMsg.setAcademicYear(academicYear);
						}
						staffAcc = (User) dao.get(User.class,accountId);
						
						if("T".equalsIgnoreCase(captureAttendance)){//Here 'T' Means CaptureAttendnance by two times and 'O' means one time
							 astatus = json.getString("astatus");
						}
						//log.debug(astatus);
						attendance = staffDao.getStaffDailyAttendance(accountId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate));
						String afternoonAttStatus = null;
						String morningAttStatus = null;
						if(!ObjectFunctions.isNullOrEmpty(attendance)){
							if(attendance.getId() > 0){
								if(attendance.isPresent()){
									morningAttStatus = "Y";
								}else if(!attendance.isPresent()){
									morningAttStatus = "N";
								}
								if(attendance.isAfternoonSession()){
									afternoonAttStatus = "Y";
								}else if(!attendance.isAfternoonSession()){
									afternoonAttStatus = "N";
								}
							}
						}
						staff = (Staff) this.get(Staff.class,"custId="+custId+" and academicYearId="+academicYearId+" and accountId="+accountId);
						mobileNumbersSet=new HashSet<String>();
						if (!StringFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(staffAcc.getPerson().getMobileNumber()))) {
							mobileNumbersSet.add(StringFunctions.getMobileNumberLengthChecking(staffAcc.getPerson().getMobileNumber()));
						}
						if(("A".equalsIgnoreCase(attendanceStatus) && ObjectFunctions.isNullOrEmpty(attendance)) || ("T".equalsIgnoreCase(captureAttendance) && ("A".equalsIgnoreCase(attendanceStatus) || "A".equalsIgnoreCase(astatus)))){//Here 'T' Means CaptureAttendnance by two times and 'O' means one time
							if(ObjectFunctions.isNullOrEmpty(attendance))
								attendance = new StaffDailyAttendance();
							attendance.setCustId(custId);
							attendance.setAcademicYearId(academicYearId);
							attendance.setCreatedById(userId);
							attendance.setCreatedDate(new Date());
							attendance.setAttendanceDate(aDate);
							attendance.setLastUpdatedDate(new Date());
							attendance.setLastAccessDate(new Date());
							attendance.setAccountId(accountId);
							if(!ObjectFunctions.isNullOrEmpty(academicYear)){
								if("T".equalsIgnoreCase(captureAttendance)){
									if("A".equalsIgnoreCase(attendanceStatus)){
										attendance.setPresent(Boolean.FALSE);
									}else{
										attendance.setPresent(Boolean.TRUE);
									}
									if("A".equalsIgnoreCase(astatus)){
										attendance.setAfternoonSession(Boolean.FALSE);
									}else{
										attendance.setAfternoonSession(Boolean.TRUE);
									}
								}else{
									attendance.setPresent(Boolean.FALSE);
									attendance.setAfternoonSession(Boolean.FALSE);
								}
							}
							attendance = (StaffDailyAttendance) this.merge(attendance);
						//	this.saveOrUpdateObject(attendance);
						   if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(sms) && !ObjectFunctions.isNullOrEmpty(mobileNumbersSet) && mobileNumbersSet.size()!=0){
							   //if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg) && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount))
							   if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg) && availableSmsCount > 0 )  
								{
									objMsg.setPurposeType("regd: absent of the staff"+staffAcc.getFullPersonName());
									if("T".equalsIgnoreCase(captureAttendance)){
										//staff at first time present from to absent to morning session
										 if(("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)) && ((!"A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus))))
											    objMsg.setMessageDescription("Dear Staff, You are absent for today Morning session. Please contact admin if you are present. Thank you principal from ");
										
										//staff at first time present from to absent to afternoon session
										 if(("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ((!"A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus))))
											    objMsg.setMessageDescription("Dear Staff, You are absent for today Afternoon session. Please contact admin if you are present. Thank you principal from  ");
										
										 if (("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)))
										    objMsg.setMessageDescription("Dear Staff, you are absent today. Please contact admin if you are present. Thank you principal from ");
										 
										 if(!StringFunctions.isNullOrEmpty(afternoonAttStatus) && !StringFunctions.isNullOrEmpty(morningAttStatus)){
											 if("A".equalsIgnoreCase(attendanceStatus) && "Y".equalsIgnoreCase(morningAttStatus))
												    objMsg.setMessageDescription("Dear Staff, You are absent for today Morning session. Please contact admin if you are present. Thank you principal from ");
												
											  if("A".equalsIgnoreCase(astatus) && "Y".equalsIgnoreCase(afternoonAttStatus))
												    objMsg.setMessageDescription("Dear Staff, You are absent for today Afternoon session. Please contact admin if you are present. Thank you principal from  ");
												
											  if(!ObjectFunctions.isNullOrEmpty(objMsg)){
												  checkSMS = this.sendStaffAttendanceSMS(mobileNumbersSet,objMsg,staff,smsServiceProvider);
												  objMsg.setMessageDescription(null);	
											  }
											 // if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
												  if(!ObjectFunctions.isNullOrEmpty(objMsg)){
													 if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
														 checkSMS = this.sendStaffAttendanceSMS(mobileNumbersSet,objMsg,staff,smsServiceProvider);
														 objMsg.setMessageDescription(null);	
														// checkSMS=true;
													 }
												  }
											 // }
											  
											 // staff at first time is absent and present for second time in morning session
											 if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus))
												 objMsg.setMessageDescription("Dear Staff, your presence for today Morning session is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from");
											 
											 // staff at first time is absent and present for second time in afternoon session
											 if("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus))
												 objMsg.setMessageDescription("Dear Staff, your presence for today Afternoon session is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from");
											 
											// staff at first time and second time also absent for both session
											 if (("A".equalsIgnoreCase(astatus) && "A".equalsIgnoreCase(attendanceStatus)) && (!"N".equalsIgnoreCase(morningAttStatus) && !"N".equalsIgnoreCase(afternoonAttStatus)))
												    objMsg.setMessageDescription("Dear Staff, you are absent today.Please contact admin if you are present. Thank you principal from ");
											 
											//if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
												 if(!ObjectFunctions.isNullOrEmpty(objMsg)){
													 if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
														// checkSMS = this.sendStaffAttendanceSMS(mobileNumbersSet,objMsg,staff);
														 objMsg = this.saveMessageDetailsTracking(objMsg,null,staff,null);
														 messageMobileMap.put(objMsg, mobileNumbersSet);
														 mobileNumbersSet = null;
														 objMsg = null;
														 //checkSMS=true;
													 }
												  }
											 //}
										}
									}else
										objMsg.setMessageDescription("Dear Staff, you are absent today. Please contact admin if you are present. Thank you principal from ");
									
									if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
										//checkSMS = this.sendStaffAttendanceSMS(mobileNumbersSet,objMsg,staff);
										objMsg = this.saveMessageDetailsTracking(objMsg,null,staff,null);
										messageMobileMap.put(objMsg, mobileNumbersSet);
										mobileNumbersSet = null;
										
								      // checkSMS=true;
									}
								}else{
									msg.put("4", "Attendance is recorded but sms is not delivered due to insufficient sms balance.");
								}
						   }
						}
						if(("P".equalsIgnoreCase(attendanceStatus) && !ObjectFunctions.isNullOrEmpty(attendance) && "O".equalsIgnoreCase(captureAttendance)) || ("T".equalsIgnoreCase(captureAttendance) && !ObjectFunctions.isNullOrEmpty(attendance) && "P".equalsIgnoreCase(attendanceStatus) && "P".equalsIgnoreCase(astatus))){
							this.remove(StaffDailyAttendance.class,attendance.getId());
							if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(sms)){
								//if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg) && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount))
								if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg) && availableSmsCount > 0)
								{
									if("T".equalsIgnoreCase(captureAttendance)){
										if(("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus)) && (!"N".equalsIgnoreCase(afternoonAttStatus) && !"N".equalsIgnoreCase(morningAttStatus)))
											 objMsg.setMessageDescription("Dear Staff,your presence for today Afternoon Session is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from ");
										else if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus) && (!"N".equalsIgnoreCase(afternoonAttStatus) && !"N".equalsIgnoreCase(morningAttStatus)))
											 objMsg.setMessageDescription("Dear Staff,your presence for today Morning Session is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from ");
										else
											if("N".equalsIgnoreCase(morningAttStatus))
												objMsg.setMessageDescription("Dear Staff,your presence for today Morning Session is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from ");
											else if("N".equalsIgnoreCase(afternoonAttStatus))
												objMsg.setMessageDescription("Dear Staff,your presence for today Afternoon Session is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from ");
											else
												objMsg.setMessageDescription("Dear Staff, your presence is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from ");
									}else
										objMsg.setMessageDescription("Dear Staff, your presence is confirmed by admin. Please ignore the absent message which you received previously. Thank you principal from ");
									
									objMsg.setPurposeType("regd: update from absent to present of the "+ staffAcc.getPerson().getFullPersonName());
									if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
										//checkSMS = this.sendStaffAttendanceSMS(mobileNumbersSet,objMsg,staff);
										objMsg = this.saveMessageDetailsTracking(objMsg,null,staff,null);
										messageMobileMap.put(objMsg, mobileNumbersSet);
										mobileNumbersSet = null;
									 // checkSMS=true;
									}
								}else{
									msg.put("4", "Attendance is recorded but sms is not delivered due to insufficient sms balance.");
								}
							}
						}
						attendance = null;
						mobileNumbersSet=null;
						objMsg=null;
					}					 
				}
			}
			
			if(!ObjectFunctions.isNullOrEmpty(messageMobileMap))
			{
				SendSMSThread R1 = new SendSMSThread(messageMobileMap,custId,smsServiceProvider);
			    R1.start();
			}
		      if(!StringFunctions.isNullOrEmpty(sms))
		      {
		    	  if("SMS".equalsIgnoreCase(sms))
		    		  msg.put("0", "Attendance recorded successfully and SMS sent successfully.");
		    	  else
		    		  msg.put("0", "Attendance recorded successfully.");
		      }
		      else
	    		  msg.put("0", "Attendance recorded successfully.");
		      //Sending Notification for Admin and Principal
		      this.sendNotificationForStaffAttendance(academicYearId, custId);
			/*if(checkSMS)
				msg.put("0", "Attendance recorded successfully and SMS sent successfully.");
			else
			 msg.put("0", "Attendance recorded successfully.");*/
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			 msg.put("2", "Oops..");
		}
		return msg;
	}
	public boolean updateMessage(long messageId){
		try { 
			 if(messageId > 0){
				 ScrapMessage message = (ScrapMessage) this.get(ScrapMessage.class, messageId);
    			 if(!ObjectFunctions.isNullOrEmpty(message)){
    				if("UR".equalsIgnoreCase(message.getIsNewMessage())){
    					message.setIsNewMessage("R");
    					this.saveScrapMessage(message);
    					message = null;
    				}
				}else
					return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return true;
	}
	public int submitReplyMessage(ReplyScrapMessageMainVO replyScrapMessageMainVO){
		try { 
			long custId = replyScrapMessageMainVO.getIdentifier().getCustId();
			long accountId = replyScrapMessageMainVO.getIdentifier().getAccountId();
			long academicYearId = replyScrapMessageMainVO.getIdentifier().getAcademicYearId();
			Map<String, String> response = new HashMap<String, String>();
			//int failedCount = 0;
			AcademicYear academicYear = (AcademicYear) this.get(AcademicYear.class, academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
				if(!ObjectFunctions.isNullOrEmpty(replyScrapMessageMainVO)){
					response = this.UpdateReplyNewScrapMessage(custId,academicYear,replyScrapMessageMainVO.getSenderAccount(),replyScrapMessageMainVO.getReceiverAccount(),replyScrapMessageMainVO.getScrapDescription(),replyScrapMessageMainVO.getScrapMesssageId(),String.valueOf(replyScrapMessageMainVO.getScrapDate()),accountId);
				}
				//String msg = response.get("1");
				if (!StringFunctions.isNullOrEmpty(response.get("1"))){
					return 1;
				}else if(!StringFunctions.isNullOrEmpty(response.get("4"))){
					return 4;
				}else if(!StringFunctions.isNullOrEmpty(response.get("5"))){
					return 5;
				}else if(!StringFunctions.isNullOrEmpty(response.get("0"))){
					return 0;
				}
				//failedCount++;
				replyScrapMessageMainVO = null;
				/*if(failedCount > 0){
					return 1;
				}else{
					return 0;					
				}*/
			}else{
				return 2;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return 0;
	}
	public void updateRoleByUserId(long roleId,long userId){
		staffDao.updateRoleByUserId(roleId,userId);  
	}
	public int submitParentMessage(SendMessageVO sendMessageVO)
	{
		try {
			long senderAccoutId=sendMessageVO.getSenderAccountId();
			ScrapMessage scrapMessage=null;
			User curUser= (User)this.get(User.class,senderAccoutId);
			if(!ObjectFunctions.isNullOrEmpty(curUser))
			{
				AcademicYear academicYear  = super.getCurrentAcademicYear("Y",curUser.getCustId());
				StringBuffer res = new StringBuffer(sendMessageVO.getReceiverAccountIds());	
				if (!ObjectFunctions.isNullOrEmpty(res)) {
					ScrapMessage scrapMessageObj = null;
					String[] accountIds = res.toString().split(",");
					if (accountIds.length > 0) {
						for (String receiverAccountId : accountIds) {
							if (StringFunctions.isNotNullOrEmpty(receiverAccountId)) {	
										ReplyScrapMessage replyScrapMessage = new ReplyScrapMessage();
										replyScrapMessage.setTitle(sendMessageVO.getSubject());
										replyScrapMessage.setScrapDescription(sendMessageVO.getDescription());
										replyScrapMessage.setSenderAccount(curUser);
										replyScrapMessage.setReceiverAccount((User)this.get(User.class, Long.valueOf(receiverAccountId)));
										replyScrapMessage.setStatus(Constants.ACTIVE_STATUS);
										replyScrapMessage.setMessageType("G");
										//User luser1 =(User)staffManager.get(User.class, getUser().getId());
										replyScrapMessage.setCreatedDate(new Date());
										replyScrapMessage.setLastUpdatedDate(new Date());
										replyScrapMessage.setLastAccessDate(new Date());
										replyScrapMessage.setCustId(curUser.getCustId());
										replyScrapMessage.setAcademicYear(academicYear);
										scrapMessageObj=new  ScrapMessage();
										scrapMessageObj.setSenderAccount(curUser);
										scrapMessageObj.setReceiverAccount((User)this.get(User.class, Long.valueOf(receiverAccountId)));
										scrapMessageObj.setStatus(Constants.ACTIVE_STATUS);
										scrapMessageObj.setMessageType("G");
										scrapMessageObj.setCustId(curUser.getCustId());
										scrapMessageObj.setLastUpdatedById(curUser.getId());
										scrapMessageObj.setAcademicYear(academicYear);
										scrapMessageObj.setTitle(sendMessageVO.getSubject());
										scrapMessageObj.setScrapDescription(sendMessageVO.getDescription());
										scrapMessageObj.addReplayScrapMessage(replyScrapMessage);
										scrapMessageObj = this.saveScrapMessage(scrapMessageObj);
										
										List<ScrapMessageVO> scrapMessageVOs = new ArrayList<ScrapMessageVO>();
										scrapMessageVOs.add(scrapMessageObj.copyFromEntityToVo(scrapMessageObj));
										
										List<ReplyScrapMessageVO> replyScrapMessageVOs = new ArrayList<ReplyScrapMessageVO>();
										
										replyScrapMessage = scrapMessageObj.getReplayScrap().get(scrapMessageObj.getReplayScrap().size() - 1);
										
										ReplyScrapMessageVO  replyScrapMessageVo = replyScrapMessage.copyFromEntityToVo(replyScrapMessage);
										replyScrapMessageVo.setScrapMesssageId(scrapMessageObj.getId());
										replyScrapMessageVOs.add(replyScrapMessageVo);
										
										//replyScrapMessageVOs.add(replyScrapMessage.copyFromEntityToVo(replyScrapMessage));
										
										JSONObject main = new JSONObject();
										JSONObject subVal = new JSONObject();
										main.put("notificationFor", "Messages");
										subVal.put("title", curUser.getFullPersonName()+" sent a message");
										subVal.put("description", curUser.getFullPersonName()+" sent a message");
										//subVal.put("scrapMessageVOs",scrapMessageVOs);
										subVal.put("scrapMessageVOs",new JSONArray(new Gson().toJson(scrapMessageVOs)));
										//subVal.put("replyScrapMessageVOs",scrapMessageVOs);
										subVal.put("replyScrapMessageVOs",new JSONArray(new Gson().toJson(replyScrapMessageVOs)));
										main.put("information", subVal);
										this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+receiverAccountId+",0)"); //To add notification for mobile app.
										
										replyScrapMessageVo = null;
										replyScrapMessage=null;
							}
						}
						curUser=null;
						return 0;
					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 1;
		}
		return 0;
	}
	
	public boolean sendStaffAttendanceSMS(Set<String> mobileNumbersSet,Messages objMsg,Staff staff,SMSServiceProviders smsServiceProvider){
		boolean smsStatus = false;
		try {
			if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)) {
				objMsg = this.saveMessageDetailsTracking(objMsg,null,staff,null);
				log.info("mobileNumbersSet :"+mobileNumbersSet);
				smsStatus = this.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsStatus;
	}
	
	
	public void sendNotificationForLeaveApply(Leave leave, String channel,long leaveId)
	{
		try{
			if(!ObjectFunctions.isNullOrEmpty(leave)){
				JSONObject main = new JSONObject();
				JSONObject subVal = new JSONObject();
				main.put("notificationFor", "Leave");
				JSONArray leavesArray = new JSONArray();
				JSONObject leavesObj = new JSONObject();
				leavesObj.put("accountId",ObjectFunctions.isNullOrEmpty(leave.getAccountId())? 0: leave.getAccountId());
				leavesObj.put("appliedBy", ObjectFunctions.isNullOrEmpty(leave.getUser().getFullPersonName())? "": leave.getUser().getFullPersonName());
				//leavesObj.put("approvalsComment",ObjectFunctions.isNullOrEmpty(leave.getApprovalsComment())? "": leave.getApprovalsComment());
				leavesObj.put("description",ObjectFunctions.isNullOrEmpty(leave.getDescription())? "": leave.getDescription());
				leavesObj.put("endDate",ObjectFunctions.isNullOrEmpty(leave.getEndDate())? "": DateFunctions.convertDateToString(leave.getEndDate()));
				leavesObj.put("id",leave.getId());
				leavesObj.put("leavesCount",ObjectFunctions.isNullOrEmpty(leave.getLeavesCount())? 0: leave.getLeavesCount());
				leavesObj.put("leaveStatus",ObjectFunctions.isNullOrEmpty(leave.getLeaveStatus())? "": leave.getLeaveStatus());
				leavesObj.put("leaveType",ObjectFunctions.isNullOrEmpty(leave.getLeaveType())? "": leave.getLeaveType());
				leavesObj.put("startDate",ObjectFunctions.isNullOrEmpty(leave.getStartDate())? "": DateFunctions.convertDateToString(leave.getStartDate()));
				leavesObj.put("supervisorId",ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())? 0: leave.getSupervisorId());
				leavesObj.put("halfDayLeave",leave.isHalfDay());
				leavesObj.put("leaveSessionType",ObjectFunctions.isNullOrEmpty(leave.getSessionType())? "": leave.getSessionType());
				//leavesVO.setSupervisor(leave.getSupervisorId());
				leavesArray.put(leavesObj);
				leavesObj=null;
				subVal.put("leavesList",leavesArray);
				if("WEB".equalsIgnoreCase(channel))
				{
					if(!ObjectFunctions.isNullOrEmpty(leave.getUser().getStudentParent())&&(leave.getUser().getStudentParent().getId())>0){
						if(leaveId>0){
							subVal.put("description", "Your Child  "+leave.getUser().getFullPersonName()+" updated the leave. Click to see more info");
							subVal.put("title", "Your Child  "+leave.getUser().getFullPersonName()+" updated the leave");
						}else{
							subVal.put("description", "Your Child  "+leave.getUser().getFullPersonName()+" applied for leave. Click to see more info");
							subVal.put("title", "Your Child  "+leave.getUser().getFullPersonName()+" applied for leave");
						}
						main.put("information", subVal);
						long parentId = leave.getUser().getStudentParent().getId();
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(parentId)+")");
					}
					else if(!ObjectFunctions.isNullOrEmpty(leave.getUser().getId())){
						if(leaveId>0){
							subVal.put("description", "You are updated the leave. Click to see more info");
							subVal.put("title", "You are updated the leave");
						}else{
							subVal.put("description", "You applied for leave. Click to see more info");
							subVal.put("title", "You applied for leave");
						}
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(leave.getUser().getId())+")");
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())){
					if(leaveId>0){
						subVal.put("description", leave.getUser().getFullPersonName()+" updated the leave. Click to see more info");
						subVal.put("title", leave.getUser().getFullPersonName()+" updated the leave");
					}else{
						subVal.put("description", leave.getUser().getFullPersonName()+" applied for leave. Click to see more info");
						subVal.put("title", leave.getUser().getFullPersonName()+" applied for leave");
					}
					main.put("information", subVal);
					List<String> toAccountIds = new ArrayList<String>();
					toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_allUsers WHERE custId = "+ leave.getCustId() + " AND roleName IN('ROLE_PRINCIPAL', 'ROLE_ADMIN', 'ROLE_ADMINOFFICER') ORDER BY roleName DESC");
					toAccountIds.add(String.valueOf(leave.getSupervisorId()));
					if(StringFunctions.isNotNullOrEmpty(toAccountIds.toString().replace("[", "").replace("]", "")) ){
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+toAccountIds.toString().replace("[", "").replace("]", "")+")");
					}
					toAccountIds=null;
				}
				leave=null;
			}
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	public void sendNotificationForLeaveApproval(Leave leave, String channel)
	{
		try{
			if(!ObjectFunctions.isNullOrEmpty(leave)){
				JSONObject main = new JSONObject();
				JSONObject subVal = new JSONObject();
				main.put("notificationFor", "Leave");
				JSONArray leavesArray = new JSONArray();
				JSONObject leavesObj = new JSONObject();
				leavesObj.put("accountId",ObjectFunctions.isNullOrEmpty(leave.getAccountId())? 0: leave.getAccountId());
				leavesObj.put("appliedBy", ObjectFunctions.isNullOrEmpty(leave.getUser().getFullPersonName())? "": leave.getUser().getFullPersonName());
				leavesObj.put("approvalsComment",ObjectFunctions.isNullOrEmpty(leave.getApprovalsComment())? "": leave.getApprovalsComment());
				leavesObj.put("description",ObjectFunctions.isNullOrEmpty(leave.getDescription())? "": leave.getDescription());
				leavesObj.put("endDate",ObjectFunctions.isNullOrEmpty(leave.getEndDate())? "": DateFunctions.convertDateToString(leave.getEndDate()));
				leavesObj.put("id",leave.getId());
				leavesObj.put("leavesCount",ObjectFunctions.isNullOrEmpty(leave.getLeavesCount())? 0: leave.getLeavesCount());
				leavesObj.put("leaveStatus",ObjectFunctions.isNullOrEmpty(leave.getLeaveStatus())? "": leave.getLeaveStatus());
				leavesObj.put("leaveType",ObjectFunctions.isNullOrEmpty(leave.getLeaveType())? "": leave.getLeaveType());
				leavesObj.put("startDate",ObjectFunctions.isNullOrEmpty(leave.getStartDate())? "": DateFunctions.convertDateToString(leave.getStartDate()));
				leavesObj.put("supervisorId",ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())? 0: leave.getSupervisorId());
				//leavesVO.setSupervisor(leave.getSupervisorId());
				leavesArray.put(leavesObj);
				leavesObj=null;
				subVal.put("leavesList",leavesArray);
				if("WEB".equalsIgnoreCase(channel))
				{
					if(!ObjectFunctions.isNullOrEmpty(leave.getUser()) && !ObjectFunctions.isNullOrEmpty(leave.getUser().getStudentParent())){
						long parentId = leave.getUser().getStudentParent().getId();
						if("A".equalsIgnoreCase(leave.getLeaveStatus())){
							subVal.put("description", "Your child "+leave.getUser().getFullPersonName()+" Leave is Approved. Click to see more info");
							subVal.put("title", "Your child "+leave.getUser().getFullPersonName()+" Leave is Approved");
						}
						else{
							subVal.put("description", "Your child "+leave.getUser().getFullPersonName()+" Leave is Rejected. Click to see more info");
							subVal.put("title", "Your child "+leave.getUser().getFullPersonName()+" Leave is Rejected");
						}
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(parentId)+")");
					}
					else if(!ObjectFunctions.isNullOrEmpty(leave.getUser().getId())){
						if("A".equalsIgnoreCase(leave.getLeaveStatus())){
							subVal.put("description", "Your Leave is Approved. Click to see more info");
							subVal.put("title", "Your Leave is Approved");
						}
						else{
							subVal.put("description", "Your Leave is Rejected. Click to see more info");
							subVal.put("title", "Your Leave is Rejected");
						}
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(leave.getUser().getId())+")");
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())){
					if("A".equalsIgnoreCase(leave.getLeaveStatus())){
						subVal.put("description", leave.getUser().getFullPersonName()+" Leave is Approved. Click to see more info");
						subVal.put("title", leave.getUser().getFullPersonName()+" Leave is Approved");
					}
					else{
						subVal.put("description", leave.getUser().getFullPersonName()+" Leave is Rejected. Click to see more info");
						subVal.put("title", leave.getUser().getFullPersonName()+" Leave is Rejected");
					}
					main.put("information", subVal);
					List<String> toAccountIds = new ArrayList<String>();
					toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_allUsers WHERE custId = "+ leave.getCustId() + " AND roleName IN('ROLE_PRINCIPAL', 'ROLE_ADMIN', 'ROLE_ADMINOFFICER') ORDER BY roleName DESC");
					toAccountIds.add(String.valueOf(leave.getSupervisorId()));
					if(StringFunctions.isNotNullOrEmpty(toAccountIds.toString().replace("[", "").replace("]", "")) ){
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+toAccountIds.toString().replace("[", "").replace("]", "")+")");
					}
					toAccountIds=null;
				}
				//leave=null;
			}
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	public void sendNotificationForLeaveApprovalForHostelLeave(HostelLeave leave, String channel)
	{
		try{
			if(!ObjectFunctions.isNullOrEmpty(leave)){
				JSONObject main = new JSONObject();
				JSONObject subVal = new JSONObject();
				main.put("notificationFor", "Leave");
				JSONArray leavesArray = new JSONArray();
				JSONObject leavesObj = new JSONObject();
				leavesObj.put("accountId",ObjectFunctions.isNullOrEmpty(leave.getAccountId())? 0: leave.getAccountId());
				leavesObj.put("appliedBy", ObjectFunctions.isNullOrEmpty(leave.getUser().getFullPersonName())? "": leave.getUser().getFullPersonName());
				leavesObj.put("approvalsComment",ObjectFunctions.isNullOrEmpty(leave.getApprovalsComment())? "": leave.getApprovalsComment());
				leavesObj.put("description",ObjectFunctions.isNullOrEmpty(leave.getDescription())? "": leave.getDescription());
				leavesObj.put("endDate",ObjectFunctions.isNullOrEmpty(leave.getEndDate())? "": DateFunctions.convertDateToString(leave.getEndDate()));
				leavesObj.put("id",leave.getId());
				leavesObj.put("leavesCount",ObjectFunctions.isNullOrEmpty(leave.getLeavesCount())? 0: leave.getLeavesCount());
				leavesObj.put("leaveStatus",ObjectFunctions.isNullOrEmpty(leave.getLeaveStatus())? "": leave.getLeaveStatus());
				leavesObj.put("leaveType",ObjectFunctions.isNullOrEmpty(leave.getLeaveType())? "": leave.getLeaveType());
				leavesObj.put("startDate",ObjectFunctions.isNullOrEmpty(leave.getStartDate())? "": DateFunctions.convertDateToString(leave.getStartDate()));
				leavesObj.put("supervisorId",ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())? 0: leave.getSupervisorId());
				//leavesVO.setSupervisor(leave.getSupervisorId());
				leavesArray.put(leavesObj);
				leavesObj=null;
				subVal.put("leavesVO",leavesArray);
				if("WEB".equalsIgnoreCase(channel))
				{
					if(!ObjectFunctions.isNullOrEmpty(leave.getUser().getStudentParent().getId())){
						long parentId = leave.getUser().getStudentParent().getId();
						if("A".equalsIgnoreCase(leave.getLeaveStatus())){
							subVal.put("description", "Your child "+leave.getUser().getFullPersonName()+" Leave is Approved. Click to see more info");
							subVal.put("title", "Your child "+leave.getUser().getFullPersonName()+" Leave is Approved");
						}
						else{
							subVal.put("description", "Your child "+leave.getUser().getFullPersonName()+" Leave is Rejected. Click to see more info");
							subVal.put("title", "Your child "+leave.getUser().getFullPersonName()+" Leave is Rejected");
						}
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(parentId)+")");
					}
					else if(!ObjectFunctions.isNullOrEmpty(leave.getUser().getId())){
						if("A".equalsIgnoreCase(leave.getLeaveStatus())){
							subVal.put("description", "Your Leave is Approved. Click to see more info");
							subVal.put("title", "Your Leave is Approved");
						}
						else{
							subVal.put("description", "Your Leave is Rejected. Click to see more info");
							subVal.put("title", "Your Leave is Rejected");
						}
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(leave.getUser().getId())+")");
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())){
					if("A".equalsIgnoreCase(leave.getLeaveStatus())){
						subVal.put("description", leave.getUser().getFullPersonName()+" Leave is Approved. Click to see more info");
						subVal.put("title", leave.getUser().getFullPersonName()+" Leave is Approved");
					}
					else{
						subVal.put("description", leave.getUser().getFullPersonName()+" Leave is Rejected. Click to see more info");
						subVal.put("title", leave.getUser().getFullPersonName()+" Leave is Rejected");
					}
					main.put("information", subVal);
					List<String> toAccountIds = new ArrayList<String>();
					toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_allUsers WHERE custId = "+ leave.getCustId() + " AND roleName IN('ROLE_PRINCIPAL', 'ROLE_ADMIN', 'ROLE_ADMINOFFICER') ORDER BY roleName DESC");
					toAccountIds.add(String.valueOf(leave.getSupervisorId()));
					if(StringFunctions.isNotNullOrEmpty(toAccountIds.toString().replace("[", "").replace("]", "")) ){
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+toAccountIds.toString().replace("[", "").replace("]", "")+")");
					}
					toAccountIds=null;
					//this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(leave.getSupervisorId())+")");
				}
				//leave=null;
			}
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	 public StudentDailyAttendance getStudentDailyAttendance(long studentId, String date,long custId,long academicYearId) {
			try {
				StringBuffer sb = new StringBuffer();
				sb.append(" from StudentDailyAttendance where custId="+custId+" and academicYearId="+academicYearId+" and studentId = " + studentId);
				// include class and sectionId
				sb.append(" and attendanceDate = '" + date+ "'");
				List tempList = this.getAllHqlQuery(sb.toString());
				if (ObjectFunctions.isNullOrEmpty(tempList)) {
					return null;
				} else {
					return (StudentDailyAttendance) tempList.get(0);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return null;
	}
	 
	 public int addOrUpdateStaffAttendanceFromApp(StaffDailyAttendanceMainVO staffDailyAttendanceMainVO)
	 {
		 try{
			 int monthNum;
			 StaffDailyAttendance attendance = null;
			 String attendanceDate= null;
			 long accountId= 0 ;
			 String afternoonAttStatus = null;
			 String morningAttStatus = null;
			 long timeDifInMilliSec;
			 Date date1;
			 String adate;
			 Date aDate;
			 long custId = staffDailyAttendanceMainVO.getIdentifier().getCustId();
			 long userId = staffDailyAttendanceMainVO.getIdentifier().getAccountId();
			 long academicYearId = staffDailyAttendanceMainVO.getIdentifier().getAcademicYearId();
			 AcademicYear academicYear = this.getCurrentAcademicYear("Y", custId);
			 String captureAttendance =academicYear.getCaptureAttendanceForStaff();
			 List<SchoolHolidays> holidaysList = null; 
			 List<StaffDailyAttendanceVO> listStaffDailyAttendance  =staffDailyAttendanceMainVO.getStaffDailyAttendance();
			 for(StaffDailyAttendanceVO staffDailyAttendance : listStaffDailyAttendance){
				 attendanceDate = staffDailyAttendance.getAttendanceDate();
				 aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, attendanceDate);
				 adate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,aDate); 
				 if (ObjectFunctions.isNullOrEmpty(aDate)) {
					 aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,attendanceDate));
				 }
				 monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(aDate));
				 SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
				 String year = simpleDateformat.format(aDate);
				 if(!ObjectFunctions.isNullOrEmpty(aDate)) 
				 { 
					 holidaysList = this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate),null,null,null,null,null,monthNum,"holidayDateEqual",year);
					 if(!ObjectFunctions.isNullOrEmpty(holidaysList)) 
					 {
						if(((!ObjectFunctions.isNullOrEmpty(academicYear.getUseBiometricForStaff())) &&("Y".equalsIgnoreCase(academicYear.getUseBiometricForStaff().trim())))){
							// for biometric we need to continue other dates attendance.
							continue;
						}else{
						 return 1;
						}
					 } 
				 } 
				 if(((!ObjectFunctions.isNullOrEmpty(academicYear.getUseBiometricForStaff())) &&("Y".equalsIgnoreCase(academicYear.getUseBiometricForStaff().trim())))){ // if Biometic
					 for(StaffAttendanceVO staffAttendance :staffDailyAttendance.getAttendance() ){
						 morningAttStatus = staffAttendance.getMorningSession();
						 accountId = staffAttendance.getAccountId();
						 attendance = staffDao.getStaffDailyAttendance(accountId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate));
						 if(ObjectFunctions.isNullOrEmpty(attendance)){
							 attendance = new StaffDailyAttendance();
							 attendance.setCustId(custId);
							 attendance.setAcademicYearId(academicYearId);
							 attendance.setCreatedById(userId);
							 attendance.setCreatedDate(new Date());
							 attendance.setAttendanceDate(aDate);
							 attendance.setLastUpdatedDate(new Date());
							 attendance.setLastAccessDate(new Date());
							 attendance.setAccountId(accountId);
						 }
						 if("A".equalsIgnoreCase(morningAttStatus)){
							 attendance.setPresent(Boolean.FALSE);
							 attendance.setAfternoonSession(Boolean.FALSE);
						 }else{
							 attendance.setPresent(Boolean.TRUE);
							 attendance.setAfternoonSession(Boolean.TRUE);
						 }
						 if(!ObjectFunctions.isNullOrEmpty(staffAttendance.getInTime()))
						 {
							 attendance.setInTime(staffAttendance.getInTime().trim());		
							 date1 = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,adate+" "+staffAttendance.getInTime());
							 if (!ObjectFunctions.isNullOrEmpty(date1) && !ObjectFunctions.isNullOrEmpty(aDate)) {
								 long milliSec1 = date1.getTime();
								 long milliSec2 = aDate.getTime();
								 if(milliSec1 >= milliSec2)
								 {
									 timeDifInMilliSec = milliSec1 - milliSec2;
									 attendance.setStaffLateTime(String.valueOf((timeDifInMilliSec / (60 * 60 * 1000) % 24)+" : "+(timeDifInMilliSec / (60 * 1000) % 60)+" : "+ (timeDifInMilliSec / 1000 % 60)));
								 }
							 }
							 if(!ObjectFunctions.isNullOrEmpty(staffAttendance.getOutTime())){
								 attendance.setOutTime(staffAttendance.getOutTime().trim());
							 }
							 if(!ObjectFunctions.isNullOrEmpty(staffAttendance.getWorkingHours())){
								 attendance.setWorkingHours(staffAttendance.getWorkingHours().trim());
							 }
						 }
						 attendance = (StaffDailyAttendance) this.merge(attendance);
						 attendance = null;
					 }
				 }else{
					 if("O".equalsIgnoreCase(captureAttendance)){ //one time attendance
						 for(StaffAttendanceVO staffAttendance :staffDailyAttendance.getAttendance() ){
							 morningAttStatus = staffAttendance.getMorningSession();
							 accountId = staffAttendance.getAccountId();
							 attendance = staffDao.getStaffDailyAttendance(accountId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate));
							 if("A".equalsIgnoreCase(morningAttStatus)|| ("P".equalsIgnoreCase(morningAttStatus) && !ObjectFunctions.isNullOrEmpty(attendance))){
								 if(ObjectFunctions.isNullOrEmpty(attendance)){
									 attendance = new StaffDailyAttendance();
									 attendance.setCustId(custId);
									 attendance.setAcademicYearId(academicYearId);
									 attendance.setCreatedById(userId);
									 attendance.setCreatedDate(new Date());
									 attendance.setAttendanceDate(aDate);
									 attendance.setLastUpdatedDate(new Date());
									 attendance.setLastAccessDate(new Date());
									 attendance.setAccountId(accountId);
								 }
								 if("A".equalsIgnoreCase(morningAttStatus)){
									 attendance.setPresent(Boolean.FALSE);
									 attendance.setAfternoonSession(Boolean.FALSE);
								 }else{
									 attendance.setPresent(Boolean.TRUE);
									 attendance.setAfternoonSession(Boolean.TRUE);
								 }
								 attendance = (StaffDailyAttendance) this.merge(attendance);
								 attendance = null;
							 } 
						 }
					 }else{ // Two Time attendance

						 for(StaffAttendanceVO staffAttendance :staffDailyAttendance.getAttendance() ){
							 morningAttStatus = staffAttendance.getMorningSession();
							 afternoonAttStatus =staffAttendance.getAfternoonSession();
							 accountId = staffAttendance.getAccountId();
							 attendance = staffDao.getStaffDailyAttendance(accountId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate));
							 if(ObjectFunctions.isNullOrEmpty(attendance)){
								 attendance = new StaffDailyAttendance();
								 attendance.setCustId(custId);
								 attendance.setAcademicYearId(academicYearId);
								 attendance.setCreatedById(userId);
								 attendance.setCreatedDate(new Date());
								 attendance.setAttendanceDate(aDate);
								 attendance.setLastUpdatedDate(new Date());
								 attendance.setLastAccessDate(new Date());
								 attendance.setAccountId(accountId);
							 }
							 if("A".equalsIgnoreCase(morningAttStatus)){
								 attendance.setPresent(Boolean.FALSE);
							 }else{
								 attendance.setPresent(Boolean.TRUE);
							 }
							 if("A".equalsIgnoreCase(afternoonAttStatus)){
								 attendance.setAfternoonSession(Boolean.FALSE);
							 }else{
								 attendance.setAfternoonSession(Boolean.TRUE);
							 }
							 attendance = (StaffDailyAttendance) this.merge(attendance);
							 attendance = null;
						 }
					 }
					 sendNotificationForStaffAttendance(academicYearId,custId);// If not bio metric
				 }
			 }
			 return 0; //"Attendance recorded successfully."
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		 }
		 return 1;
	 }
	 
	 public void sendNotificationForStaffAttendance(long academicYearId, long custId)
	 {
		 try
			{
			 //Sending notification to Admin, HOD, Principal, Vice Principal, Admin Officer
				List<String>  staffAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_staffDetails WHERE roleId IN(1, 8, 12, 31, 32) AND custId = "+custId);
				if(!ObjectFunctions.isNullOrEmpty(staffAccountIds))
				{
					if(!ObjectFunctions.isNullOrEmpty(staffAccountIds.get(0)))
					{
						JSONObject main = new JSONObject();
						JSONObject subVal = new JSONObject();
						main.put("notificationFor", "Staff Attendance");
						subVal.put("title", "Staff Attendance Taken");
						subVal.put("description", "Staff Attendance Taken.");
						//subVal.put("studentMarksVOList",studentMarksArray);
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+staffAccountIds.get(0)+")"); //To add notification for mobile app.
					}
				}
			
			}
		 catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 public int deleteLeaveInfo(long leaveId){
			if (log.isDebugEnabled()) {
				log.debug("Entering StudentManager 'deleteLeaveInfo' method");
			}
			try{
				long supervisorId=0;
				long accountId=0;
				long custId=0;
				Leave leave = (Leave)this.get(Leave.class,leaveId);
				if(!ObjectFunctions.isNullOrEmpty(leave)){
					supervisorId=leave.getSupervisorId();
					custId=leave.getCustId();
					accountId=leave.getAccountId();
					this.remove(Leave.class, leave.getId());	
					this.sendNotificationForLeaveDelete(supervisorId,leaveId,custId,accountId);				
				}else{
					return 1;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				return 2;
			}
			return 0;
		}
	 public void sendNotificationForLeaveDelete(long supervisorId,long leaveId,long custId,long accountId)
		{
			try
			{
				List<String> toAccountIds = new ArrayList<String>();
				 toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_allUsers WHERE custId = "+ Long.valueOf(custId) + " AND roleName IN('ROLE_PRINCIPAL', 'ROLE_ADMIN', 'ROLE_ADMINOFFICER') ORDER BY roleName DESC");
				 toAccountIds.add(String.valueOf(supervisorId));
				 if(StringFunctions.isNotNullOrEmpty(toAccountIds.toString().replace("[", "").replace("]", "")) ){
					 	String personName="";
						User userObj = (User)this.get(User.class,accountId);
						if(!ObjectFunctions.isNullOrEmpty(userObj))
							personName =userObj.getPerson().getFullPersonName();
						JSONObject main = new JSONObject();
						JSONObject subVal = new JSONObject();
						main.put("notificationFor", "Leave");
						subVal.put("description", ""+personName+" deleted his leave.");
						subVal.put("title", ""+personName+" deleted his leave.");
						subVal.put("deletedIds", leaveId);
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(custId,main.toString(),"("+toAccountIds.toString().replace("[", "").replace("]", "")+")");
				 }
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	 
	 public int leaveVoFromApp(LeavesVO leavesVO){
			if (log.isDebugEnabled()) {
				log.debug("Entering StudentManager 'leaveVoFromApp' method");
				//submitOrEditLeaves(Leave leaveObj, AcademicYear academicYearObj, User userObj,long custId,long leaveId,String channel,String stuHostelLeave)
			}
			try{
				long leaveId = leavesVO.getId();
				long custId = leavesVO.getIdentifier().getCustId();
				long userId = leavesVO.getAccountId();
				long academicYearId = leavesVO.getIdentifier().getAcademicYearId();
				User userObj = (User) this.get(User.class, userId);
				AcademicYear academicYearObj = (AcademicYear) this.get(AcademicYear.class, academicYearId);
				if(ObjectFunctions.isNullOrEmpty(academicYearObj))
					return 4;
				if(ObjectFunctions.isNullOrEmpty(userObj))
					return 5;
				Leave leave=null;
				Leave leaveObj= new Leave();
				leaveObj.copyFromVoToEntity(leaveObj,leavesVO);
				if(leaveId > 0){
					leave = (Leave)this.get(Leave.class,"custId="+custId+" and id="+leaveId+" and leaveType='"+leavesVO.getLeaveType()+"' and accountId="+userObj.getId()+" and (endDate BETWEEN '"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, leaveObj.getStartDate())+"' AND  '"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, leaveObj.getStartDate())+"')");
				}//Commented this for unncessary call
				/*else{
					leave = (Leave)this.get(Leave.class,"custId="+custId+" and description='"+leavesVO.getDescription().trim()+"' and leaveType='"+leavesVO.getLeaveType()+"'  and accountId="+userObj.getId()+" and (endDate BETWEEN '"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, leaveObj.getStartDate())+"' AND  '"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, leaveObj.getStartDate())+"')");
				}*/
				int responseCode =0;
				if(ObjectFunctions.isNullOrEmpty(leave)){
					Object[] supervisor = null;
					long supervisorId = 0;
					if(leavesVO.getAppliedBy().equalsIgnoreCase("A")){
						supervisor = this.get("SELECT vu.accountId, vu.roleName FROM vw_allUsers vu WHERE vu.custId = "+ Long.valueOf(custId) + " AND vu.roleName ='ROLE_PRINCIPAL' ORDER BY vu.roleName DESC");
					}else if(leavesVO.getAppliedBy().equalsIgnoreCase("S")){ // S for all staff members
						supervisor = this.get("SELECT vu.accountId, vu.roleName FROM vw_allUsers vu WHERE vu.custId = "+ Long.valueOf(custId) + " AND vu.roleName IN('ROLE_PRINCIPAL', 'ROLE_ADMIN') ORDER BY vu.roleName DESC");
					}
					else{
						if(leavesVO.getAppliedBy().contains("P")){// P for Parents
							 Object[] student= this.get("SELECT id,classSectionId,academicYearId from student where status = 'Y' and accountId="+ leavesVO.getAccountId());
							 if(!ObjectFunctions.isNullOrEmpty(student) && !ObjectFunctions.isNullOrEmpty(student[1])) {
								long studyClassId = Long.valueOf(student[1].toString());
								ClassTeacher teacher= (ClassTeacher)this.get(ClassTeacher.class," studyClassId="+ studyClassId+" and classTeacher='"+ Constants.YES_STRING+ "' ");
								 if(!ObjectFunctions.isNullOrEmpty(teacher)) {
									 supervisorId = teacher.getStaff().getAccount().getId();
								 }else{
									 supervisor = this.get("SELECT vu.accountId, vu.roleName FROM vw_allUsers vu WHERE vu.custId = "+ Long.valueOf(custId) + " AND vu.roleName = 'ROLE_PRINCIPAL'");
								 }
						   }
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(supervisor))
						leaveObj.setSupervisorId(Long.parseLong(supervisor[0].toString()));
					else
						leaveObj.setSupervisorId(supervisorId);
					supervisor = null;
					responseCode =this.submitOrEditLeaves(leaveObj,academicYearObj,userObj,custId,leaveId,"APP","");
				}else{
					return 1;
				}
				return responseCode;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				return 2;
			}
		}
	 
	 public int submitOrEditLeaves(Leave leaveObj, AcademicYear academicYearObj, User userObj,long custId,long leaveId,String channel,String stuHostelLeave){
			if (log.isDebugEnabled()) {
				log.debug("Entering StudentManager 'submitOrEditLeaves' method");
			}
			try {
				if(ObjectFunctions.isNullOrEmpty(academicYearObj))
					return 4;
				if(ObjectFunctions.isNullOrEmpty(userObj))
					return 5;
				Calendar startDate=Calendar.getInstance();
				startDate.setTime(leaveObj.getStartDate());
				Calendar endDate=Calendar.getInstance();
				endDate.setTime(leaveObj.getEndDate());
				SchoolHolidays holiday=null;
				boolean isStartDateSet=false;
				boolean isEndDateSet=false;
				Leave leave=null;
				for(;DateFunctions.compare2Dates(startDate.getTime(),leaveObj.getStartDate()) && DateFunctions.compare2Dates(leaveObj.getEndDate(),startDate.getTime());startDate.add(Calendar.DATE, 1)){
					holiday=this.getHolidayByCustIdAndAcademicYearId(custId,0,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()),0,null,null,"holidayDateLike");
					if(!ObjectFunctions.isNullOrEmpty(holiday)){
						continue;
					}else{
						isStartDateSet=true;
						break;
					}
				}
				if(isStartDateSet){
					for(;DateFunctions.compare2Dates(endDate.getTime(),leaveObj.getStartDate()) && DateFunctions.compare2Dates(leaveObj.getEndDate(),endDate.getTime());endDate.add(Calendar.DATE, -1)){
						holiday=this.getHolidayByCustIdAndAcademicYearId(custId,0,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, endDate.getTime()),0,null,null,"holidayDateLike");
						if(!ObjectFunctions.isNullOrEmpty(holiday)){
							continue;
						}else{
							isEndDateSet=true;
							break;
						}
					}
				}
				if(isStartDateSet && isEndDateSet){
					HostelLeave hostelLeave = null;
					if(leaveId >0 ){
						leave=(Leave)this.get(Leave.class, leaveId);
						if(userObj.isSchoolStudent())
							leave.setLastUpdatedById(Long.valueOf(userObj.getStudentParent().getId()));
						else
							leave.setLastUpdatedById(userObj.getId());
					}else{
						leave=new Leave();
						if(userObj.isSchoolStudent())
							leave.setCreatedById(Long.valueOf(userObj.getStudentParent().getId()));
						else
							leave.setCreatedById(userObj.getId());
						leave.setCreatedDate(new Date());
						if(!StringFunctions.isNullOrEmpty(stuHostelLeave))
						{
							hostelLeave=new HostelLeave();
							hostelLeave.setCreatedById(userObj.getId());
							hostelLeave.setCreatedDate(new Date());
						}
					}
					leave.copyFrom(leaveObj);
					leave.setLeaveStatus("P");
					leave.setCustId(custId);
					leave.setLastUpdatedDate(new Date());
					leave.setLastAccessDate(new Date());
					leave.setStartTime(academicYearObj.getStartTime());
					leave.setEndTime(academicYearObj.getEndTime());
					leave.setUser(userObj);
					leave.setAcademicYear(academicYearObj);
					if(leaveObj.isHalfDay()){
						if(!ObjectFunctions.isNullOrEmpty(leaveObj.getSessionType())){
							leave.setSessionType(leaveObj.getSessionType());
						}else
							leave.setSessionType("M");
						leave.setHalfDay(Boolean.TRUE);
					}else{
						leave.setHalfDay(leaveObj.isHalfDay());
						leave.setSessionType(null);
					}
					leave=(Leave) this.save(leave);
					if(!StringFunctions.isNullOrEmpty(stuHostelLeave))
					{
						//log.debug(stuHostelLeave);
						if("true".equalsIgnoreCase(stuHostelLeave))
							this.save(hostelLeave.copyFromLeaveObj(leave, hostelLeave));
					}
					this.sendNotificationForLeaveApply(leave, channel,leaveId);
					if(!ObjectFunctions.isNullOrEmpty(leave.getStartDate()) && !ObjectFunctions.isNullOrEmpty(leave.getEndDate()) && leave.getId() >0 ){
						if(!ObjectFunctions.isNullOrEmpty(leave)){
							Customer usrCust = (Customer)this.get(Customer.class,"id="+custId); 
							int availableSmsCount=this.getAvailableSmsCount(custId, leave.getAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(usrCust) && availableSmsCount >0){
								User usr = (User)this.get(User.class,"userName='"+usrCust.getCustEmail()+"'");
								if (!ObjectFunctions.isNullOrEmpty(usr)) {
									if(!StringFunctions.isNullOrEmpty(usr.getPerson().getMobileNumber())){
										getMobileNumbersSet().add(usr.getPerson().getMobileNumber());
										log.debug("adminMobl:"+getMobileNumbersSet());
										List PrincesMobileNos = this.getAll("select mobileNumber from vw_staffDetails where (mobileNumber!=0 AND mobileNumber is not null) and roleName='ROLE_PRINCIPAL'  and status='Y' and academicYearStatus='Y' and custId="+custId+" and academicYearId="+academicYearObj.getId()+"  ");
										if(!ObjectFunctions.isNullOrEmpty(PrincesMobileNos))
											getMobileNumbersSet().addAll(PrincesMobileNos);
										log.debug("mblnolist:"+getMobileNumbersSet());
										if (!ObjectFunctions.isNullOrEmpty(getMobileNumbersSet())) {
											this.SendSmsToAdminAndStaff(null,getMobileNumbersSet(), usrCust.getId(),userObj,academicYearObj,leave);
										}
									}
								}
							}else{
								if(leaveId>0)
								return 7;
								else
								return 8;
							}
						}
					}
					if(leaveId >0 )
						return 6; //super.addActionMessage("Leave updated successfully.");
				}else{
					return 3;
					//super.addActionError("The selected days are part of holidays.Please change selections.");
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				return 2;
			}
			return 0;
		}
	 
	 public int approveOrRejectLeaves(User userObj,String leaveStatus, String approvalsComment, long leaveId, String channel, String forStaffOrStudent){
			if (log.isDebugEnabled()) {
				log.debug("Entering StudentManager 'approveOrRejectLeaves' method");
			}
			try {
				if(userObj.isSchoolHostel() && "student".equalsIgnoreCase(forStaffOrStudent)){
					int responseCode= this.ajaxApproveOrRejectHostelStudentLeave(userObj,leaveStatus,approvalsComment,leaveId,forStaffOrStudent);
					return responseCode;
				}else{
					Leave leave=(Leave)this.get(Leave.class, leaveId);
					if(!ObjectFunctions.isNullOrEmpty(leave)){
						if(leaveStatus.equalsIgnoreCase("R")){
							leave.setLeaveStatus(Constants.REJECTED_STATUS);
							//super.addActionMessage("Leave rejected successfully");
						}else if(leaveStatus.equalsIgnoreCase("A")){
							leave.setLeaveStatus(Constants.ACTIVE_STATUS);
						}
						leave.setApprovalsComment(approvalsComment);
						leave.setLastUpdatedById(userObj.getId());
						leave.setLastUpdatedDate(new Date());
						leave=(Leave) this.save(leave);
						//getSmsCount();
						int smsCnt=0;
						int smsAlloted =0;
						long academicYearId=0;
						if(!ObjectFunctions.isNullOrEmpty(leave.getAcademicYear())){
							academicYearId =leave.getAcademicYear().getId();
							smsCnt=this.getTotalSmsCount(leave.getCustId(),academicYearId);
							smsAlloted = (int) leave.getAcademicYear().getAllotedsms()+(int) leave.getAcademicYear().getPaidSms();
						}
						if(leaveStatus.equalsIgnoreCase("A")){
							Student student =null;
							if(!"staff".equalsIgnoreCase(forStaffOrStudent)){
								//student = adminManager.getStudentByCustIdAndStudentIdAndStatus(leave.getAccountId(), leave.getCustId(),"Y", leave.getAcademicYearId());
								student = (Student) this.get(Student.class,"custId="+leave.getCustId()+" and academicYearId="+academicYearId+" and accountId="+leave.getAccountId()+" and status='"+Constants.YES_STRING+"'");
							}
							Calendar cal=Calendar.getInstance();
							cal.setTime(leave.getStartDate());
							SchoolHolidays holiday=null;
							long i = 0;
							for(Calendar startDate=cal;DateFunctions.compare2Dates(leave.getEndDate(), startDate.getTime());startDate.add(Calendar.DATE, 1)){
								//holiday=adminManager.getHolidaysListForUpdate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()), getUserCustId(),0);
								holiday=this.getHolidayByCustIdAndAcademicYearId(leave.getCustId(),0,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()),0,null,null,"holidayDateLike");
								if(ObjectFunctions.isNullOrEmpty(holiday)){
									if(!ObjectFunctions.isNullOrEmpty(student)){
										StudentDailyAttendance attendance=null;
										attendance = this.getStudentDailyAttendance(leaveId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()),leave.getCustId(),leave.getAcademicYearId());
										//attendance=staffManager.getAttendance(getUserCustId(), leave.getAcademicYearId(),leave.getAccountId() , Integer.valueOf(new SimpleDateFormat("MM").format(startDate.getTime())),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()));
										if(ObjectFunctions.isNullOrEmpty(attendance)){
											attendance=new StudentDailyAttendance();
											attendance.setCreatedById(userObj.getId());
											attendance.setAcademicYearId(leave.getCustId());
										}else
										attendance.setLastUpdatedById(userObj.getId());
										attendance.setStudentId(student.getId());
										attendance.setAttendanceDate(startDate.getTime());
										attendance.setCreatedDate(new Date());
										attendance.setLastAccessDate(new Date());
										attendance.setCustId(leave.getCustId());
										attendance.setAcademicYearId(academicYearId);
										attendance.setLastUpdatedDate(new Date());
										attendance.setLeaveRequest('A');
										attendance.setLeaveType(leave.getLeaveType());
										if(leave.isHalfDay()){
											if(leave.getLeavesCount()==0.5 || i!=0){
												if("M".equalsIgnoreCase(leave.getSessionType())){
													attendance.setPresent(Boolean.FALSE);
												    attendance.setAfternoonSession(Boolean.TRUE);
												    attendance.setLeaveSessionType("M");
												}else{
												    attendance.setAfternoonSession(Boolean.FALSE);
													attendance.setPresent(Boolean.TRUE);
													attendance.setLeaveSessionType("A");
												}
											}else{
												if(i==0){
													attendance.setPresent(Boolean.FALSE);
												    attendance.setAfternoonSession(Boolean.FALSE);
												    attendance.setLeaveSessionType(null);
												}
											}
										}else{
											attendance.setPresent(Boolean.FALSE);
										    attendance.setAfternoonSession(Boolean.FALSE);
										    attendance.setLeaveSessionType(attendance.getLeaveSessionType());
										}
										//attendance.setVersion(0);
										this.save(attendance);
										attendance=null;
								}else{
										StaffDailyAttendance attendance=null;
										attendance=(StaffDailyAttendance) this.get(StaffDailyAttendance.class, "accountId = "+leave.getAccountId()+" and attendanceDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime())+"'");
										//attendance=staffManager.getAttendance(getUserCustId(), academicYearId,leave.getAccountId() , Integer.valueOf(new SimpleDateFormat("MM").format(startDate.getTime())),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()));
										if(ObjectFunctions.isNullOrEmpty(attendance)){
											attendance=new StaffDailyAttendance();
											attendance.setCreatedById(userObj.getId());
											attendance.setAcademicYearId(academicYearId);
										}else
										attendance.setLastUpdatedById(userObj.getId());
										attendance.setAccountId(leave.getAccountId());
										attendance.setAttendanceDate(startDate.getTime());
										attendance.setCreatedDate(new Date());
										attendance.setLastAccessDate(new Date());
										attendance.setLastUpdatedDate(new Date());
										attendance.setCustId(leave.getCustId());
										attendance.setLeaveRequest('A');
										if(leave.isHalfDay()){
											if(leave.getLeavesCount()==0.5 || i!=0){
												if("M".equalsIgnoreCase(leave.getSessionType())){
													attendance.setPresent(Boolean.FALSE);
												    attendance.setAfternoonSession(Boolean.TRUE);
												    attendance.setLeaveSessionType("M");
												}else{
												    attendance.setAfternoonSession(Boolean.FALSE);
													attendance.setPresent(Boolean.TRUE);
													attendance.setLeaveSessionType("A");
												}
											}else{
												if(i==0){
													attendance.setPresent(Boolean.FALSE);
												    attendance.setAfternoonSession(Boolean.FALSE);
												    attendance.setLeaveSessionType(null);
												}
											}
										}else{
											attendance.setPresent(Boolean.FALSE);
										    attendance.setAfternoonSession(Boolean.FALSE);
											attendance.setLeaveSessionType(attendance.getLeaveSessionType());

										}
										//attendance.setPresent(Boolean.FALSE);
										attendance.setLeaveType(leave.getLeaveType());
										attendance.setAcademicYearId(academicYearId);
										this.save(attendance);
										attendance=null;
								}
									i=leave.getId();
							}
						 }
						//super.addActionMessage("Leave approved successfully");
						}
						//Sending Leave Approva/Reject Notification to mobile
						this.sendNotificationForLeaveApproval(leave, "WEB");
						if((smsAlloted!=0 && smsAlloted > smsCnt)){
							Customer customer = this.getCustomerByCustId(leave.getCustId());
							getMobileNumbersSet().addAll(this.addMobileNumbersBasedOnAddressType(customer.getMobileType(),leave.getUser().getPerson().getMobileNumber(),leave.getUser().getPerson().getSecondaryMobileNumber(),leave.getUser().getPerson().getAnotherMobileNumber(),leave.getUser().getPerson().getAnotherSecondaryMobileNumber(),leave.getUser().getPerson().getAddressType()));
							if("B".equalsIgnoreCase(customer.getMobileType())){
								getMobileNumbersSet().addAll(this.addMobileNumberBasedOnSettings(customer.getMobileType(),leave.getUser().getPerson().getMobileNumber(),leave.getUser().getPerson().getSecondaryMobileNumber()));
							}else if("P".equalsIgnoreCase(customer.getMobileType()))
								getMobileNumbersSet().add(leave.getUser().getPerson().getMobileNumber());
							else{
								if(!ObjectFunctions.isNullOrEmpty(leave.getUser().getPerson().getSecondaryMobileNumber()))
								getMobileNumbersSet().add(leave.getUser().getPerson().getSecondaryMobileNumber());
							}
							customer = null;
							if(!ObjectFunctions.isNullOrEmpty(getMobileNumbersSet())){
								if(!ObjectFunctions.isNullOrEmpty(getMobileNumbersSet()))
								this.SendSmsToAdminAndStaff(leaveStatus,getMobileNumbersSet(),leave.getCustId(),userObj,leave.getAcademicYear(),leave);
							}
						}else{
							return 4;
						}						
						if("R".equalsIgnoreCase(leave.getLeaveStatus())){
							return 0;
						}else{
							return 1;
						}
					}else{
						return 2;
					}
				}
			}		
			catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				return 3;
			}
		}
	 public int ajaxApproveOrRejectHostelStudentLeave(User userObj,String leaveStatus, String approvalsComment, long leaveId,String forStaffOrStudent)
		{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxApproveOrRejectHostelStudentLeave' method");
		}
		try {
			HostelLeave hostelLeave=(HostelLeave)this.get(HostelLeave.class, leaveId);
			if(!ObjectFunctions.isNullOrEmpty(hostelLeave)){
				if(leaveStatus.equalsIgnoreCase("R")){
					hostelLeave.setLeaveStatus(Constants.REJECTED_STATUS);
					//super.addActionMessage("Leave rejected successfully");
				}else if(leaveStatus.equalsIgnoreCase("A")){
					hostelLeave.setLeaveStatus(Constants.ACTIVE_STATUS);
				}
				hostelLeave.setSupervisorId(userObj.getId());
				hostelLeave.setApprovalsComment(approvalsComment);
				hostelLeave.setLastUpdatedById(userObj.getId());
				hostelLeave.setLastUpdatedDate(new Date());
				hostelLeave=(HostelLeave) this.save(hostelLeave);
				if(leaveStatus.equalsIgnoreCase("A")){
					Student student =null;
					if(!"staff".equalsIgnoreCase(forStaffOrStudent)){
						//student = adminManager.getStudentByCustIdAndStudentIdAndStatus(leave.getAccountId(), leave.getCustId(),"Y", leave.getAcademicYearId());
						student = (Student) this.get(Student.class,"custId="+hostelLeave.getCustId()+" and academicYearId="+hostelLeave.getAcademicYearId()+" and accountId="+hostelLeave.getAccountId()+" and status='"+Constants.YES_STRING+"'");
					}
					Calendar cal=Calendar.getInstance();
					cal.setTime(hostelLeave.getStartDate());
					SchoolHolidays holiday=null;
					for(Calendar startDate=cal;DateFunctions.compare2Dates(hostelLeave.getEndDate(), startDate.getTime());startDate.add(Calendar.DATE, 1)){
						//holiday=adminManager.getHolidaysListForUpdate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()), getUserCustId(),0);
						holiday=this.getHolidayByCustIdAndAcademicYearId(userObj.getCustId(),0,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime()),0,null,null,"holidayDateLike");
						if(ObjectFunctions.isNullOrEmpty(holiday))
						{
							if(!ObjectFunctions.isNullOrEmpty(student))
							{
								HostelStudentDailyAttendance attendance = (HostelStudentDailyAttendance)this.get(HostelStudentDailyAttendance.class, "studentId = " + student.getId() + " and attendanceDate = '" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime())+ "'");
								if(ObjectFunctions.isNullOrEmpty(attendance)){
									attendance=new HostelStudentDailyAttendance();
									attendance.setAcademicYearId(hostelLeave.getAcademicYearId());
									attendance.setLeaveType(hostelLeave.getLeaveType());
									attendance.setCustId(userObj.getCustId());
									attendance.setCreatedById(userObj.getId());
									attendance.setCreatedDate(new Date());
									attendance.setLeaveCount(1);
									//attendance.setVersion(0);
								}else
								{
									attendance.setLastUpdatedById(userObj.getId());
									attendance.setLastUpdatedDate(new Date());
									attendance.setLastAccessDate(new Date());
								}
								attendance.setStudentId(student.getId());
								attendance.setAttendanceDate(startDate.getTime());
								attendance.setLeaveRequest('A');
								attendance.setPresent(Boolean.FALSE);
								this.save(attendance);
								attendance=null;
							}
						}
					}
					//super.addActionMessage("Hostel leave approved successfully");
					//Sending Leave Approva/Reject Notification to mobile
					this.sendNotificationForLeaveApprovalForHostelLeave(hostelLeave, "WEB");
				}
				if("R".equalsIgnoreCase(hostelLeave.getLeaveStatus())){
					return 0;
				}else{
					return 1;
				}
			}else{
				return 2;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 3;
		}
	}
	public List<BigInteger> getTeacherIdsByStudyClassSubject(long custId,long academicYearId,long studyClassId,long subjectId){
		 return staffDao.getTeacherIdsByStudyClassSubject(custId,academicYearId,studyClassId,subjectId);
	 }

	public int addOrUpdateTaskDetails(TaskDetailsVO taskDetailsVO, User user, long academicYearId,String isCheckMobileService,String isCheckEmailService) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'addOrUpdateTaskDetails' method");
		}
		try {
			TaskDetails taskDetails = null;
			TaskHistory taskHistory = null;
			TaskHistoryVO taskHistoryVO = null;
			if(!ObjectFunctions.isNullOrEmpty(taskDetailsVO) && taskDetailsVO.getId() > 0) {
				taskDetails = (TaskDetails) this.get(TaskDetails.class, taskDetailsVO.getId());
				taskDetails.setLastUpdatedById(user.getId());
			}else{
				taskDetails =  new TaskDetails();
				taskDetails.setCreatedById(user.getId());
			}
			taskDetailsVO.setCustId(user.getCustId());
			taskDetailsVO.setAcademicYearId(academicYearId);
			taskDetails.copyFromVoToEntity(taskDetailsVO);
			
				taskHistoryVO = new TaskHistoryVO();
				taskHistory =  new TaskHistory();
				User account = (User) this.get(User.class, taskDetailsVO.getLatestTaskHistoryVO().getAccountVO().getId());
				taskHistory.setAccount(account);
				taskHistoryVO.setStatus("O");
				taskHistoryVO.setComments(user.getPerson().getFullPersonName()+" task assined to you.");
				taskHistoryVO.setTaskDate(taskDetailsVO.getLatestTaskHistoryVO().getTaskDate());
				taskHistory.copyFromVoToEntity(taskHistoryVO);
				if(StringFunctions.isNotNullOrEmpty(isCheckMobileService)){
					if("on".equalsIgnoreCase(isCheckMobileService)){
						taskDetails.setCheckMobileService(true);
				}}
				else{
					taskDetails.setCheckMobileService(false);
				}
				if(StringFunctions.isNotNullOrEmpty(isCheckEmailService)){
					if("on".equalsIgnoreCase(isCheckEmailService)){
						taskDetails.setCheckEmailService(true);
					}}
					else{
						taskDetails.setCheckEmailService(false);
					}
				taskDetails.addTaskHistory(taskHistory);
			this.save(taskDetails);
			if((!ObjectFunctions.isNullOrEmpty(taskDetailsVO.getId())) && taskDetailsVO.getId()>0)
				return 1;
			else
				return 2;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	
	}

	public TaskDetailsVO getTaskDetailsById(Long taskId) {
		try {
			TaskDetailsVO taskDetailsVO =null;
			TaskDetails taskDetails = (TaskDetails) this.get(TaskDetails.class,taskId);
			if(!ObjectFunctions.isNullOrEmpty(taskDetails)){
				taskDetailsVO=taskDetails.copyFromEntityToVo();
				TaskHistory taskHistory = (TaskHistory) this.get(TaskHistory.class," taskDetailsId="+taskId+" GROUP BY taskDetailsId,id ORDER BY taskHistoryDate DESC LIMIT 1");
				if(!ObjectFunctions.isNullOrEmpty(taskHistory)){
				taskDetailsVO.getLatestTaskHistoryVO().getAccountVO().setId(taskHistory.getAccount().getId());
				taskDetailsVO.getLatestTaskHistoryVO().setTaskDate(taskHistory.getTaskDate());
				}
			}
			return taskDetailsVO;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public int addOrUpdateReminder(ReminderVO reminderVO, User user, long academicYearId, String isCheckMobileService, String isCheckEmailService) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'addReminder' method");
		}
		try {
			Reminder reminder = null;
			if(!ObjectFunctions.isNullOrEmpty(reminderVO) && reminderVO.getId() > 0) {
				reminder = (Reminder) this.get(Reminder.class, reminderVO.getId());
				reminder.setLastUpdatedById(user.getId());
			}else{
				reminder =  new Reminder();
				reminder.setCreatedById(user.getId());
				reminder.setCreatedDate(new Date());
			}
			reminder.setAccountId(user.getId());
			reminder.setAcademicYearId(academicYearId);
			reminder.setCustId(user.getCustId());
			reminder.copyFromVoToEntity(reminderVO);
			if (StringFunctions.isNotNullOrEmpty(isCheckMobileService)) {
				if ("on".equalsIgnoreCase(isCheckMobileService)) {
					reminder.setCheckMobileService(true);
				}}
			else {
				reminder.setCheckMobileService(false);
			}
			if (StringFunctions.isNotNullOrEmpty(isCheckEmailService)) {
				if ("on".equalsIgnoreCase(isCheckEmailService)) {
					reminder.setCheckEmailService(true);
				}}
			else {
				reminder.setCheckEmailService(false);
			}
			this.save(reminder);
			if(!ObjectFunctions.isNullOrEmpty(reminderVO) && reminderVO.getId() > 0)
				return 1;
			else{
				return 2;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public int deleteTaskDetails(long taskId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'deleteTaskDetails' method");
		}
		try{
			TaskHistory viewTask = (TaskHistory) this.get(TaskHistory.class, "status !='O' and taskDetailsId="+taskId);
			if(!ObjectFunctions.isNullOrEmpty(viewTask)){
				return 1;
			}else{
				this.updateQuery("delete from taskHistory where taskDetailsId="+taskId+" and status= 'O'");
				this.updateQuery("delete from taskDetails where id="+taskId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 2;
		}
		return 0;
	}

	public TaskDetailsVO showTaskDetailsComments(long taskId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'showTaskDetailsComments' method");
		}
		try{
			TaskDetailsVO taskDetailsVO = null;
			TaskDetails taskDetails = (TaskDetails)this.get(TaskDetails.class,"id="+taskId);
			taskDetailsVO = new TaskDetailsVO();
			taskDetailsVO = taskDetails.copyFromEntityToVo();
			List<TaskHistory> taskHistoryList = this.getAll(TaskHistory.class,"taskDetailsId="+taskId+ " order by id DESC");
			for(TaskHistory taskHistories : taskHistoryList){
				TaskHistoryVO taskHistoryVO = new TaskHistoryVO();
				taskHistoryVO.setTaskDate(taskHistories.getTaskDate());
				taskHistoryVO.setStatus(taskHistories.getStatus());
				taskHistoryVO.getAccountVO().setId(taskHistories.getAccount().getId());
				log.info("Account Id =" +taskHistories.getAccount().getId()+ " Name ="+taskHistories.getAccount().getPerson().getPersonFullName());
				taskHistoryVO.getAccountVO().setFullName(taskHistories.getAccount().getPerson().getPersonFullName());
				taskHistoryVO.setComments(taskHistories.getComments());
				taskHistoryVO.setTaskHistoryDate(taskHistories.getTaskHistoryDate());
				taskDetailsVO.getTaskHistoriesVO().add(taskHistoryVO);
				taskHistoryVO = null;
			}
			return taskDetailsVO;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public TaskDetailsVO showTaskInfromationInTaskProcess(long taskId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'showTaskInfromationInTaskProcess' method");
		}
		try{
			TaskDetailsVO taskDetailsVO = null;
			TaskHistoryVO  taskHistoryVO = null;
			TaskHistory taskHistory = (TaskHistory) this.get(TaskHistory.class,"taskDetailsId="+taskId+" group by id order by id DESC LIMIT 1");
			
			TaskDetails taskDetails = (TaskDetails) this.get(TaskDetails.class,taskId);
			if(!ObjectFunctions.isNullOrEmpty(taskHistory)){
				taskDetailsVO =  taskDetails.copyFromEntityToVo();
				taskHistoryVO = taskHistory.copyFromEntityToVo();
				taskHistoryVO.getAccountVO().setId(taskHistory.getAccount().getId());
				taskDetailsVO.setLatestTaskHistoryVO(taskHistoryVO);
			}
			return taskDetailsVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}

	public int addOrUpdateTaskInprogressOrComplete(long taskId, TaskHistoryVO taskHistoryVO, long userAcademicYearId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'addOrUpdateTaskInprogressOrComplete' method");
		}
		try {
			TaskHistory taskHistory = null;
			if(!ObjectFunctions.isNullOrEmpty(taskHistoryVO)){
				taskHistory =  new TaskHistory();
				User account = (User) this.get(User.class, taskHistoryVO.getAccountVO().getId());
				TaskDetails taskDetails = (TaskDetails) this.get(TaskDetails.class,taskId);
				taskHistory.setAccount(account);
				taskHistory.copyFromVoToEntity(taskHistoryVO);
				taskDetails.addTaskHistory(taskHistory);
				this.save(taskDetails);
			}
			
			if(!ObjectFunctions.isNullOrEmpty(taskHistory))
				return 1;
			else
				return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public List getAllTaskdetails(String anyTitle, User user, boolean isClassTeacher) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'addOrUpdateTaskInprogressOrComplete' method");
		}
		try {
			List objectList = null;
			if(!ObjectFunctions.isNullOrEmpty(anyTitle)){
				if(anyTitle.equalsIgnoreCase("CT")){
					if(user.isSchoolAdmin() ||  user.isOnlySchoolHod() || user.isSchoolPrincipal() || user.isSchoolDirector() || isClassTeacher || Constants.SCHOOL_VICEPRINCIPAL.equalsIgnoreCase(user.getRoleName()) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(user.getRoleName()))
						objectList = this.getAll(ViewTaskDetailsAndTaskHistory.class,"custId="+user.getCustId()+" and (taskCreator="+user.getId()+" or accountId="+user.getId()+") and status ='C' order by taskHistoryDate DESC");
					else
						objectList = this.getAll(ViewTaskDetailsAndTaskHistory.class,"custId="+user.getCustId()+" and accountId="+user.getId()+" and status ='C' order by taskHistoryDate DESC");
				}
			}else{
				if(user.isSchoolAdmin() || user.isOnlySchoolHod() || user.isSchoolPrincipal() || user.isSchoolDirector() || isClassTeacher || Constants.SCHOOL_VICEPRINCIPAL.equalsIgnoreCase(user.getRoleName()) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(user.getRoleName()))
					objectList = this.getAll(ViewTaskDetailsAndTaskHistory.class,"custId="+user.getCustId()+" and (taskCreator="+user.getId()+" or accountId="+user.getId()+") and status !='C' order by taskHistoryDate DESC");
				else
					objectList = this.getAll(ViewTaskDetailsAndTaskHistory.class,"custId="+user.getCustId()+" and accountId="+user.getId()+" and status !='C' order by taskHistoryDate DESC");	
			}
		return objectList;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List getRemindersList(long custId, long userId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StaffManager 'getRemindersList' method");
		}try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date= new Date();
			String todayDate =dateformat.format(date);
			List reminderList=null;
			reminderList=this.getAll(Reminder.class, " custId= " + custId+" and accountId="+userId+" and expirationDate>='"+todayDate+" 00:00:00' ");
			return reminderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ReminderVO getReminderDetailsById(Long id) {
		try{
			ReminderVO reminderVo =null;
			Reminder reminder = (Reminder) this.get(Reminder.class, id);
			if(!ObjectFunctions.isNullOrEmpty(reminder)){
				reminderVo=reminder.copyFromEntityToVo();
				reminderVo.setCheckEmailService(reminder.isCheckEmailService());
				reminderVo.setCheckMobileService(reminder.isCheckMobileService());
			}
			return reminderVo;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

}
