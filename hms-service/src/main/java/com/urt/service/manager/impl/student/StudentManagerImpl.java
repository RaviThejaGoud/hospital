package com.urt.service.manager.impl.student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.google.gson.Gson;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.ClassAssignmentMainVO;
import com.hyniva.sms.ws.vo.ClassAssignmentVO;
import com.hyniva.sms.ws.vo.OnlineApplicationDetailsMainVO;
import com.hyniva.sms.ws.vo.OnlineApplicationDetailsVO;
import com.hyniva.sms.ws.vo.PermissionTimingsVO;
import com.hyniva.sms.ws.vo.PermissionsVO;
import com.hyniva.sms.ws.vo.PersonVO;
import com.hyniva.sms.ws.vo.RouteBoardingPointsVO;
import com.hyniva.sms.ws.vo.RouteVo;
import com.hyniva.sms.ws.vo.StaffPermissionRequestsMainVO;
import com.hyniva.sms.ws.vo.StaffPermissionRequestsVO;
import com.hyniva.sms.ws.vo.StaffPermissionsDayDetailsVO;
import com.hyniva.sms.ws.vo.StaffPermissionsSettingsVO;
import com.hyniva.sms.ws.vo.StudentDetailsListVO;
import com.hyniva.sms.ws.vo.StudentDetailsVO;
import com.hyniva.sms.ws.vo.StudentMarksMainVO;
import com.hyniva.sms.ws.vo.StudentMarksVO;
import com.hyniva.sms.ws.vo.StudentVo;
import com.hyniva.sms.ws.vo.StudyClassVO;
import com.hyniva.sms.ws.vo.UserImageVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.hyniva.sms.ws.vo.ViewPermissionSettingsMainVO;
import com.hyniva.sms.ws.vo.ViewRouteBoardingPointsMainVO;
import com.hyniva.sms.ws.vo.ViewRoutesMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionRequestsMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionRequestsVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionsSettingsMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPermissionsSettingsVO;
import com.hyniva.sms.ws.vo.ViewStudentPersonAccountDetailsMainVO;
import com.hyniva.sms.ws.vo.ViewStudentPersonAccountDetailsVO;
import com.hyniva.sms.ws.vo.attendance.ClassAttendanceMainVO;
import com.hyniva.sms.ws.vo.attendance.ClassAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.PreSchoolStudentAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StudentAttendanceListVO;
import com.hyniva.sms.ws.vo.attendance.StudentAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StudentDailyAttendanceVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.spring.context.SpringContextAware;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.interfaces.student.StudentDao;
import com.urt.persistence.model.attendance.StaffDailyAttendanceSubmitTrack;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.HostelStudentDailyAttendance;
import com.urt.persistence.model.common.HouseType;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.PermissionTimings;
import com.urt.persistence.model.common.Permissions;
import com.urt.persistence.model.common.PersonDocuments;
import com.urt.persistence.model.common.StaffPermissionRequests;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.common.StudentDailyAttendanceTimeTrack;
import com.urt.persistence.model.common.StudyAndBonafiedSettings;
import com.urt.persistence.model.common.StudyCertificateBookSettings;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.common.ViewStaffPermissionRequests;
import com.urt.persistence.model.common.ViewStaffPermissionsSettings;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.hostel.Bed;
import com.urt.persistence.model.study.ClassAssignment;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentQuestionAnswer;
import com.urt.persistence.model.study.StudentQuizResults;
import com.urt.persistence.model.study.StudentRollNumberComparator;
import com.urt.persistence.model.study.StudyCertificate;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.TimeTable;
import com.urt.persistence.model.study.TransferStudent;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentsTCDetails;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.transport.ViewStaffVehicleTripdetails;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.service.thread.SendSmsTrackingThread;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.jrexception.JRExceptionClient;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;

/**
 * Implementation of StudentManagerImpl interface.</p>
 * 
 * <p>
 * <a href="StudentManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class StudentManagerImpl extends UniversalManagerImpl implements StudentManager {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	protected SessionFactory sessionFactory;

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Leave> getLeavesListByAccountId(long accountId,
			String leaveStatus, long customerId, long academicYearId) {
		return studentDao.getLeavesListByAccountId(accountId, leaveStatus,
				customerId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Student getStudentById(long accountId) {
		return studentDao.getStudentById(accountId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassEventListByClassId(long classId) {
		return studentDao.getClassEventListByClassId(classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllCalendarEventsByEventIds(String eventsIds,
			long customerId, long academicYearId) {
		return studentDao.getAllCalendarEventsByEventIds(eventsIds, customerId,
				academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getEventInvitedUserListByEventId(String eventId) {
		return studentDao.getEventInvitedUserListByEventId(eventId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getEventAcceptedEventsByEventIdAndAccountId(String eventIds,
			long accountId, String eventAccepted) {
		return studentDao.getEventAcceptedEventsByEventIdAndAccountId(eventIds,
				accountId, eventAccepted);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getRemainingCalendarEventsByEventIds(String eventsIds,
			long customerId) {
		return studentDao.getRemainingCalendarEventsByEventIds(eventsIds,
				customerId);
	}

	public void cancelRegistrationStudentEvent(long eventId, long accountId) {
		studentDao.cancelRegistrationStudentEvent(eventId, accountId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getEventsByDate(String eventDate) {
		return studentDao.getEventsByDate(eventDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudyClass getStudentSubjects(String className, String section) {
		return studentDao.getStudentSubjects(className, section);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Student getStudentByAccountId(long accountId, long academicYearId,
			long custId) {
		return studentDao.getStudentByAccountId(accountId, academicYearId,
				custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentPersonAccountDetails> getMyChildren(long parentId,
			long custId, long academicYearId, String status) {
		return studentDao.getMyChildren(parentId, custId, academicYearId,
				status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Fee getFeeByStudentId(long classId) {
		return studentDao.getFeeByStudentId(classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStaffVehicleTripdetails getStaffVehicleTripByTripId(
			long vehicleTripId) {
		return studentDao.getStaffVehicleTripByTripId(vehicleTripId);
	}

	/*public StudyClass getStudyClassByClassName(String className) {
		return studentDao.getStudyClassByClassName(className);
	}*/

	/*
	 * public StudentPayment getPaymentStatusByStudentId(long studentId){ return
	 * studentDao.getPaymentStatusByStudentId(studentId); }
	 */
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public TimeTable getTimeTableByClassAndSec(String className,
			String sectionName, String dayName) {
		return studentDao.getTimeTableByClassAndSec(className, sectionName,
				dayName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getViewStudentLeaveDetailsByAccountId(long accountId,
			String leaveStatus, long customerId) {
		return studentDao.getViewStudentLeaveDetailsByAccountId(accountId,
				leaveStatus, customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentMarks> getAllMarksByStudId(long studentId, long academicYearId) {
		return studentDao.getAllMarksByStudId(studentId,academicYearId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List getAllStudentsBirthDays(long classId, long accountId) {
		return studentDao.getAllStudentsBirthDays(classId, accountId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMyMessagesByReceiverAccountIdAndStatus(
			String receiverAccountId, long customerId, String status) {
		return studentDao.getMyMessagesByReceiverAccountIdAndStatus(
				receiverAccountId, customerId, status);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Messages updateStudentsReadMessages(String msgId, String newStatus) {
		return studentDao.updateStudentsReadMessages(msgId, newStatus);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMyUnreadMessagesAndStatus(String receiverAccountId,
			long customerId, String status) {
		return studentDao.getMyUnreadMessagesAndStatus(receiverAccountId,
				customerId, status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getTermStudentAttendanceByAccountIdIdAndTerms(long studentId,
			int term, String year, Date date) {
		return studentDao.getTermStudentAttendanceByAccountIdIdAndTerms(
				studentId, term, year, date);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Messages> getAllNoticeBoardMessagesList(String status,
			String className, long customerId, long academicYearId) {
		return studentDao.getAllNoticeBoardMessagesList(status, className,
				customerId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMyreplyMessagesAndStatus(String receiverAccountId,
			long customerId, String status) {
		return studentDao.getMyreplyMessagesAndStatus(receiverAccountId,
				customerId, status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getViewreplyMessagesAndStatus(String status, long customerId,
			String msgParentId) {
		return studentDao.getViewreplyMessagesAndStatus(status, customerId,
				msgParentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Messages getMessagesByAccountId(String msgAccountId) {
		return studentDao.getMessagesByAccountId(msgAccountId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMyMessagesBySenderAccountIdAndStatus(String senderAccountId,
			long customerId, String status) {
		return studentDao.getMyMessagesBySenderAccountIdAndStatus(
				senderAccountId, customerId, status);
	}

	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentMarksUploadList(long classId, String academicYear) {
		return studentDao.getStudentMarksUploadList(classId, academicYear);
	}

	
	public String getSubjectMaxMarksByClassIdandSubjectIdandExamtypId(
			long classId, String subjectName, long examTypeId,
			String academicYear) {
		return studentDao.getSubjectMaxMarksByClassIdandSubjectIdandExamtypId(
				classId, subjectName, examTypeId, academicYear);
	}

	public String getSubjectMinMarksByClassIdandSubjectIdandExamtypId(
			long classId, String subjectName, long examTypeId,
			String academicYear) {
		return studentDao.getSubjectMinMarksByClassIdandSubjectIdandExamtypId(
				classId, subjectName, examTypeId, academicYear);
	}

	public double getSubjectAvgMarksByClassIdandSubjectIdandExamtypId(
			long classId, long subjectId, long examTypeId, long academicYear) {
		return studentDao.getSubjectAvgMarksByClassIdandSubjectIdandExamtypId(
				classId, subjectId, examTypeId, academicYear);
	}

	public String getSubjectObtainedMarksByClassIdandSubjectIdandExamtypIdandStudentId(
			long classId, String subjectName, long examTypeId,
			String academicYear, long studentId) {
		return studentDao
				.getSubjectObtainedMarksByClassIdandSubjectIdandExamtypIdandStudentId(
						classId, subjectName, examTypeId, academicYear,
						studentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentClassFeePaymentDetails> getMyChildrenFeeDetails(
			long studentId) {
		return studentDao.getMyChildrenFeeDetails(studentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getMyChildrenClassmatesList(long classId, String status) {
		return studentDao.getMyChildrenClassmatesList(classId, status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentMarksDetails> getStudentMarksByStudIdandExamtypeId(
			long studId, long examTypeId) {
		return studentDao.getStudentMarksByStudIdandExamtypeId(studId,
				examTypeId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public QuestionAnswer getQuestionAnswer(String studentAnswer,
			long questionId, long custId) {
		return studentDao.getQuestionAnswer(studentAnswer, questionId, custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentQuizQuestionAnswer(long quizId, long custId,
			String status) {
		return studentDao.getStudentQuizQuestionAnswer(quizId, custId, status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudentQuizResults getQuizResultsWithId(long studentId, long quizId,
			long custId, String status) {
		return studentDao.getQuizResultsWithId(studentId, quizId, custId,
				status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllCalendarEventsByStudentIdAndAccountId(String studentId,
			long customerId) {
		return studentDao.getAllCalendarEventsByStudentIdAndAccountId(
				studentId, customerId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getTermStudentAttendanceByClassId(long classId, int term,
			String year) {
		return studentDao
				.getTermStudentAttendanceByClassId(classId, term, year);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentQuestionAnswers(long studentId, long quizId,
			long custId, String todayDate) {
		return studentDao.getStudentQuestionAnswers(studentId, quizId, custId,
				todayDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentCorrectAnswersList(long questionId, long custId) {
		return studentDao.getStudentCorrectAnswersList(questionId, custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentNotCorrectAnswersList(long questionId, long custId) {
		return studentDao.getStudentNotCorrectAnswersList(questionId, custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudentQuestionAnswer getStudentAnswerAttemptWithQuestionId(
			long studentId, long questionId, long custId) {
		return studentDao.getStudentAnswerAttemptWithQuestionId(studentId,
				questionId, custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ParentFeedbackResult getStudentFeedbackResult(long questionId,
			long custId, long studentId) {
		return studentDao.getStudentFeedbackResult(questionId, custId,
				studentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ParentFeedbackResult getParentFeedbackResult(long questionId,
			long custId, long parentId) {
		return studentDao.getParentFeedbackResult(questionId, custId, parentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ParentFeedbackResult getStudentFeedbackStaffResult(long staffId,
			long studentId, long custId) {
		return studentDao.getStudentFeedbackStaffResult(staffId, studentId,
				custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandStatus(
			long accountId, long academicYear, String status, long custId) {
		return studentDao.getStudentDetailsByAccountIdandStatus(accountId,
				academicYear, status, custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<AcademicYear> getAllAcademicYearsBycustId(long custId) {
		return studentDao.getAllAcademicYearsBycustId(custId);
	}

	public int getAllStudentMarksCountByTypeAndClassandstudId(long studId,
			long examtype) {
		return studentDao.getAllStudentMarksCountByTypeAndClassandstudId(
				studId, examtype);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStudentsByCustIdAndStatus(long custId, String status) {
		return studentDao.getAllStudentsByCustIdAndStatus(custId, status);
	}

	public String getSUMOfStudentMarksByStudIdandExamtypeId(long studId,
			long examTypeId) {
		return studentDao.getSUMOfStudentMarksByStudIdandExamtypeId(studId,
				examTypeId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getstAllStudentsByClassId(long classId, String status) {
		return studentDao.getstAllStudentsByClassId(classId, status);
	}

	public long getUpcomingFeeTotalAmountByTerm(String queryString,long studentId, long custId, long classId, long academicYearId,long schoolTermId, String today, long boardingPointId) {
		return studentDao.getUpcomingFeeTotalAmountByTerm(queryString,studentId, custId, classId, academicYearId, schoolTermId,today, boardingPointId);
	}
	public long getCommittedFeeAmountTermWise(String queryString,long studentId,long custId,long classId,long academicYearId,long schoolTermId,String today,long boardingPointId){
		return studentDao.getCommittedFeeAmountTermWise(queryString,studentId, custId, classId, academicYearId, schoolTermId,today, boardingPointId);
	}

	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandStatus(
			long accountId, String status) {
		return studentDao.getStudentDetailsByAccountIdandStatus(accountId,
				status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandAcademicYearId(
			long accountId, long academicYearId) {
		return studentDao.getStudentDetailsByAccountIdandAcademicYearId(
				accountId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllClassesAndSetionWiseReportsByCustId(String className,
			long custId, long academicYearId, String classIds) {
		return studentDao.getAllClassesAndSetionWiseReportsByCustId(className,
				custId, academicYearId, classIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudentDailyAttendance getStudentDailyAttendance(long studentId,String date,long custId,long academicYearId)
	{
		return studentDao.getStudentDailyAttendance(studentId,date,custId,academicYearId);
	}
	
	public int getClassStudentsCountByClassIdandStatus(long classId,String status,long custId) {
		return studentDao.getClassStudentsCountByClassIdandStatus(classId, status , custId);
	}
	public Map<String,String> addOrUpdateStudentAttendance(String strFormData, long academicYearId, long custId, long userId,String sms,String email, long classId) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addOrUpdateStudentAttendance' method");
		}
		Map<String, String> msg = new HashMap<String, String>();
		try {
			//JSONObject json;
			int monthNum;
			StudentDailyAttendance attendance = null;
			HostelStudentDailyAttendance hostelAttendance = null;
			StaffDailyAttendanceSubmitTrack staffDailyAttendanceSubmitTrack = null;
			JSONObject formData = new JSONObject(strFormData);
			if (ObjectFunctions.isNullOrEmpty(formData)) {
				msg.put("1","Attendance is not registered due to system error. Please contact System Administrator");
				return msg;
			}
			JSONArray studentAttendanceData = (JSONArray) formData.getJSONArray("classAttendanceData");
			JSONObject jsonObj = null;
			boolean checkSMS = false;
			Customer customer = this.getCustomerByCustId(custId);
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			AcademicYear academicYear=(AcademicYear)dao.get(AcademicYear.class, academicYearId);
			User loggedUser = (User) this.get(User.class, userId);
			
			JSONObject studentAttendanceArr1 = (JSONObject) studentAttendanceData.get(0);
			String attendanceDate = (String) studentAttendanceArr1.get("attendanceDate");
			Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, attendanceDate);
			if (ObjectFunctions.isNullOrEmpty(aDate)) {
				aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,attendanceDate));
			}
			monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(aDate));
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
			String year = simpleDateformat.format(aDate);
			if (!ObjectFunctions.isNullOrEmpty(aDate)) {
				List<SchoolHolidays> holidaysList=null;  //below classId used to get the holidays for class wise. if school have class wise holidays in school settings tab
				if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()) && classId > 0){
					Object[] classNameClassIds= dao.get("select classId,className from vw_classSectionDetails where custId="+custId+ " and academicYearId="+academicYearId+" and classSectionId="+classId);
		 			if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
		 				holidaysList = this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate),null,null,classNameClassIds[0].toString(),null,null,monthNum,"holidayDateEqual",year);
		 			}
		 		  }else{
		 			 	holidaysList = this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate),null,null,null,null,null,monthNum,"holidayDateEqual",year);
		 		}
				if (!ObjectFunctions.isNullOrEmpty(holidaysList)) {
					msg.put("1", "Today Is Holiday");
					return msg;
				}
			}
			String captureAttendance = academicYear.getCaptureAttendanceBy();
			log.info("student start :"+new Date());
			List<Student> studentList=this.getAll(Student.class, "custId="+custId+" and academicYearId="+academicYearId+" and classSectionId="+classId+" and description is null");
			log.info("student End :"+new Date());
			Map<Long, Student>  studentMap=new HashMap<Long, Student>();
			if(!ObjectFunctions.isNullOrEmpty(studentList)){
				for(Student student:studentList){
					studentMap.put(student.getId(), student);
					student=null;
				}
				studentList=null;
			}
			for (int i = 0; i < studentAttendanceData.length(); i++) {
				JSONObject studentAttendanceArr = (JSONObject) studentAttendanceData.get(i);
				Student student = null;
				Set<String> mobileNumbersSet = null;
				Messages objMsg = null;
				boolean sendEMail = false;
				Date todayDate = new Date();
				JSONArray studentData = (JSONArray) studentAttendanceArr.getJSONArray("students");
				Map<Messages, List> messageMobileMap = new HashMap<Messages, List>();
				if (!ObjectFunctions.isNullOrEmpty(studentData)) {
					for (int j = 0; j < studentData.length(); j++) {
						jsonObj = studentData.getJSONObject(j);
						if (!ObjectFunctions.isNullOrEmpty(jsonObj)) {
							
							String afternoonAttStatus = null;
							String morningAttStatus = null;
							String astatus = null;
							String parentEmail = null;
							 sendEMail = false;
							long studentId = jsonObj.getLong("studentId"); //Long.valueOf((String) jsonObj.get("studentId"));
							String attendanceStatus = jsonObj.getString("status");  //(String) jsonObj.get("status");
							log.debug(new Date()+"studentId :" + studentId+ "status :" + attendanceStatus);
							//student = (Student) this.get(Student.class,"custId="+custId+" and academicYearId="+academicYearId+" and id="+studentId+" and description is null");
							student = studentMap.get(studentId);
							log.debug("Student and :"+new Date());
							
							User studentUser = null;
							if (!ObjectFunctions.isNullOrEmpty(student)) {
								studentUser = student.getAccount();
							}
							
							if("T".equalsIgnoreCase(captureAttendance)){//Here 'T' Means CaptureAttendnance by two times and 'O' means one time
								 astatus = jsonObj.getString("astatus");
							}
							//log.debug(astatus);
							String organzationLeavel = customer.getOrganizationLevel();//organzationLeavel is using more one time i have declared object every time no need to call object just one time calling
							if(!ObjectFunctions.isNullOrEmpty(studentUser)) {
								parentEmail = studentUser.getPerson().getParentEmail();
								boolean sendAttendanceEmail = customer.isCheckAttendanceEmailService();
								if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(sms)){
									mobileNumbersSet = new HashSet<String>();
									String  mobileType = customer.getMobileType();
									mobileNumbersSet.addAll(this.addMobileNumbersBasedOnAddressType(mobileType,StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getSecondaryMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherSecondaryMobileNumber()),studentUser.getPerson().getAddressType()));
									
									objMsg = new Messages();
									objMsg.setCreatedById(userId);
									objMsg.setCreatedDate(new Date());
									objMsg.setLastAccessDate(new Date());
									objMsg.setLastUpdatedDate(new Date());
									objMsg.setStatus("A");
									objMsg.setAcademicYear(academicYear);
									objMsg.setSenderName(loggedUser.getUserRoleDescription());
									if (!ObjectFunctions.isNullOrEmpty(customer)) {
										objMsg.setCustomer(customer);
									}
									objMsg.setSmsSenderId(customer.getSender());
								}
								if(loggedUser.isSchoolHostel())
								{
									String clause = " custId=" + custId+ " and studentId = " + studentId + " and attendanceDate = '" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate)+ "'";
									hostelAttendance = (HostelStudentDailyAttendance) this.get(HostelStudentDailyAttendance.class,clause);
									if("A".equalsIgnoreCase(attendanceStatus) && ObjectFunctions.isNullOrEmpty(hostelAttendance)){
										hostelAttendance = new HostelStudentDailyAttendance();
										hostelAttendance.setCustId(custId);
										hostelAttendance.setAcademicYearId(academicYearId);
										hostelAttendance.setCreatedById(userId);
										hostelAttendance.setCreatedDate(new Date());
										hostelAttendance.setAttendanceDate(aDate);
										hostelAttendance.setLastUpdatedDate(new Date());
										hostelAttendance.setLastAccessDate(new Date());
										hostelAttendance.setStudentId(studentId);
										hostelAttendance.setPresent(Boolean.FALSE);
										this.save(hostelAttendance);
											if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg))
											{
												if("S".equalsIgnoreCase(customer.getOrganizationLevel()))
												    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");
												else
												    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent. Please contact college if your child has already started to college. ");	
													
												objMsg.setPurposeType("regd: absent of the "+ studentUser.getFullPersonName());
												checkSMS=true;
											}
									}
									if("P".equalsIgnoreCase(attendanceStatus) && !ObjectFunctions.isNullOrEmpty(hostelAttendance)){
										this.remove(HostelStudentDailyAttendance.class,hostelAttendance.getId());
										if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg))
										{
											objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+". Please ignore the absent message which you received previously. ");
											objMsg.setPurposeType("regd: update from absent to present of the "+ studentUser.getFullPersonName());
											checkSMS=true;
										}
									}
									hostelAttendance = null; 
								}
								else
								{
									
									attendance = this.getStudentDailyAttendance(studentId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate),customer.getId(),academicYearId);
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
									if(("A".equalsIgnoreCase(attendanceStatus) && ObjectFunctions.isNullOrEmpty(attendance)) || ("T".equalsIgnoreCase(captureAttendance) && 
											("A".equalsIgnoreCase(attendanceStatus) || "A".equalsIgnoreCase(astatus)))){//Here 'T' Means CaptureAttendnance by two times and 'O' means one time
										if(ObjectFunctions.isNullOrEmpty(attendance)){
											attendance = new StudentDailyAttendance();
										}
										attendance.setCustId(custId);
										attendance.setAcademicYearId(academicYearId);
										attendance.setCreatedById(userId);
										attendance.setCreatedDate(new Date());
										attendance.setAttendanceDate(aDate);
										attendance.setLastUpdatedDate(new Date());
										attendance.setLastAccessDate(new Date());
										attendance.setStudentId(studentId);
										if(!ObjectFunctions.isNullOrEmpty(student.getAcademicYear())){
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
										attendance = (StudentDailyAttendance) this.merge(attendance);
										sendEMail = true;
										if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate))
												&&  !ObjectFunctions.isNullOrEmpty(objMsg))
										{
											if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet) && mobileNumbersSet.size()!=0){
												if("T".equalsIgnoreCase(captureAttendance)){
													objMsg.setPurposeType("regd: absent of the "+ studentUser.getFullPersonName());
													if("S".equalsIgnoreCase(organzationLeavel)){
														//student at first time present from to absent to morning session
														 if(("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)) && ((!"A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus))))
														    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Morning session.");
														
														//student at first time present from to absent to afternoon session
														 if(("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ((!"A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus))))
														    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Afternoon session.");
														
														 if (("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)))
														    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");

														 if(!StringFunctions.isNullOrEmpty(afternoonAttStatus) && !StringFunctions.isNullOrEmpty(morningAttStatus)){
															 if("A".equalsIgnoreCase(attendanceStatus) && "Y".equalsIgnoreCase(morningAttStatus))
																    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Morning session.");
																
															  if("A".equalsIgnoreCase(astatus) && "Y".equalsIgnoreCase(afternoonAttStatus))
																    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Afternoon session.");
																
															  if(!ObjectFunctions.isNullOrEmpty(objMsg)){
																  if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
																	  //checkSMS = this.sendStudentAttendanceSMS(mobileNumbersSet,objMsg,student);
																	  //objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);
																	  
																	  List objectsList = new ArrayList();
																		 
																		 objectsList.add(student);
																		 objectsList.add(null);
																		 objectsList.add(null);
																		 objectsList.add(mobileNumbersSet);
																		 
																	  messageMobileMap.put(objMsg, objectsList);
																	  //objMsg.setMessageDescription(null);
																  }
															  }
															  
															 // student at first time is absent and present for second time in morning session
															 if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus))
																 objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to school for Morning session. Please ignore the absent message which you received previously.");
															 
															 // student at first time is absent and present for second time in afternoon session
															 if("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus))
																 objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to school for Afternoon session. Please ignore the absent message which you received previously.");
															 
															// student at first time and second time also absent for both session
															 if (("A".equalsIgnoreCase(astatus) && "A".equalsIgnoreCase(attendanceStatus)) && (!"N".equalsIgnoreCase(morningAttStatus) && !"N".equalsIgnoreCase(afternoonAttStatus)))
																    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");
															  if(!ObjectFunctions.isNullOrEmpty(objMsg)){
																 if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
																	 //checkSMS = this.sendStudentAttendanceSMS(mobileNumbersSet,objMsg,student);
																	 //objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);
																	 
																	 List objectsList = new ArrayList();
																	 
																	 objectsList.add(student);
																	 objectsList.add(null);
																	 objectsList.add(null);
																	 objectsList.add(mobileNumbersSet);
																	  messageMobileMap.put(objMsg, objectsList);
																	 mobileNumbersSet = null;
																	 objMsg = null;
																 }
															  } 
														}
													}else{
														//student at first time present from to absent to morning session
														 if(("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)) && ((!"A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus))))
														    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Morning session. Please contact college if your child has already started to college.");
														 
														//student at first time present from to absent to afternoon session
														 if(("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ((!"A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus))))
														    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Afternoon session. Please contact college if your child has already started to college.");
														
														 if (("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)))
															objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent. Please contact college if your child has already started to college. ");
														
														 if(!StringFunctions.isNullOrEmpty(afternoonAttStatus) && !StringFunctions.isNullOrEmpty(morningAttStatus)){
															 
															 if("A".equalsIgnoreCase(attendanceStatus) && "Y".equalsIgnoreCase(morningAttStatus))
																    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Morning session. Please contact college if your child has already started to college.");
																
															  if("A".equalsIgnoreCase(astatus) && "Y".equalsIgnoreCase(afternoonAttStatus))
																    objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Afternoon session. Please contact college if your child has already started to college.");
															  
															  if(!ObjectFunctions.isNullOrEmpty(objMsg)){
																  if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
																	// checkSMS = this.sendStudentAttendanceSMS(mobileNumbersSet,objMsg,student);
																	  //objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);
																	  //messageMobileMap.put(objMsg, mobileNumbersSet);
																	  List objectsList = new ArrayList();
																		 objectsList.add(student);
																		 objectsList.add(null);
																		 objectsList.add(null);
																		 objectsList.add(mobileNumbersSet);
																		  messageMobileMap.put(objMsg, objectsList);
																	  //objMsg.setMessageDescription(null);
																  }
															  }
															  
															 // student at first time is absent and present for second time in morning session
															 if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus))
																 objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to college for Morning Session. Please ignore the absent message which you received previously.");
															 
															// student at first time is absent and present for second time in afternoon session
															 if("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus))
																 objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to college for Afternoon Session. Please ignore the absent message which you received previously.");
															 
															// student at first time and second time also absent for both session
															 if (("A".equalsIgnoreCase(astatus) && "A".equalsIgnoreCase(attendanceStatus)) && (!"N".equalsIgnoreCase(morningAttStatus) && !"N".equalsIgnoreCase(afternoonAttStatus)))
																	objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent. Please contact college if your child has already started to college. ");
															 if(!ObjectFunctions.isNullOrEmpty(objMsg)){
																 if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
																	 //checkSMS = this.sendStudentAttendanceSMS(mobileNumbersSet,objMsg,student);
																	// objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);
																	 
																	 List objectsList = new ArrayList();
																	 objectsList.add(student);
																	 objectsList.add(null);
																	 objectsList.add(null);
																	 objectsList.add(mobileNumbersSet);
																	 messageMobileMap.put(objMsg, objectsList);
																	  
																	 mobileNumbersSet = null;
																	// objMsg = null;
																 }
															 } 
														}
													}
												}else{
													if("S".equalsIgnoreCase(organzationLeavel))
														objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");
													else
													     objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent. Please contact college if your child has already started to college. ");
												}
												if(!ObjectFunctions.isNullOrEmpty(objMsg)){
													objMsg.setPurposeType("regd: absent of the "+ studentUser.getFullPersonName());
												}
											}
										}
									}
									//Sending Mobile Notification for Student Attendance
									
									if("T".equalsIgnoreCase(captureAttendance)){
										if(("A".equalsIgnoreCase(attendanceStatus) || "P".equalsIgnoreCase(attendanceStatus) ||"A".equalsIgnoreCase(astatus) || "P".equalsIgnoreCase(attendanceStatus))
												&& !ObjectFunctions.isNullOrEmpty(attendance) 
												&& DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate))){
										    this.sendNotificationForAttendance(studentUser, attendanceStatus,astatus, studentId, classId);
										}
									}else{
										if(("A".equalsIgnoreCase(attendanceStatus) || "P".equalsIgnoreCase(attendanceStatus))
												&& !ObjectFunctions.isNullOrEmpty(attendance) 
												&& DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate))){
										    this.sendNotificationForAttendance(studentUser, attendanceStatus, studentId, classId);
										}
									}
									if(("P".equalsIgnoreCase(attendanceStatus) && !ObjectFunctions.isNullOrEmpty(attendance) && "O".equalsIgnoreCase(captureAttendance)) || ("T".equalsIgnoreCase(captureAttendance) && !ObjectFunctions.isNullOrEmpty(attendance) && "P".equalsIgnoreCase(attendanceStatus) && "P".equalsIgnoreCase(astatus))){
										this.remove(StudentDailyAttendance.class,attendance.getId());
										sendEMail = true;
										if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg))
										{
											if("T".equalsIgnoreCase(captureAttendance)){
												if(("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus)) && (!"N".equalsIgnoreCase(afternoonAttStatus) && !"N".equalsIgnoreCase(morningAttStatus)))
													 objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+" for Afternoon Session. Please ignore the absent message which you received previously.");
												else if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus) && (!"N".equalsIgnoreCase(afternoonAttStatus) && !"N".equalsIgnoreCase(morningAttStatus)))
													 objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+" for Morning Session. Please ignore the absent message which you received previously.");
												else
												{
													if("N".equalsIgnoreCase(morningAttStatus))
													{
														objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+" for Morning session. Please ignore the absent message which you received previously.");
													}
													else if("N".equalsIgnoreCase(afternoonAttStatus))
													{
														objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+" for Afternoon session. Please ignore the absent message which you received previously.");
													}
													else
														objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+". Please ignore the absent message which you received previously. ");
												}
											}else
												objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+". Please ignore the absent message which you received previously. ");
											
											if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
												objMsg.setPurposeType("regd: absent of the "+ studentUser.getFullPersonName());
											}
										}
									}
									//attendance = null; 
								}
								if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet) && mobileNumbersSet.size()!=0 && !ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
									//checkSMS = this.sendStudentAttendanceSMS(mobileNumbersSet,objMsg,student);
									// objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);
									 // messageMobileMap.put(objMsg, mobileNumbersSet);
									 List objectsList = new ArrayList();
									 objectsList.add(student);
									 objectsList.add(null);
									 objectsList.add(null);
									 objectsList.add(mobileNumbersSet);
									  messageMobileMap.put(objMsg, objectsList);
									  
									mobileNumbersSet = null;
								}
								
									//}
								mobileNumbersSet = null;
								//sending attendance email
								if(sendEMail && !StringFunctions.isNullOrEmpty(email) && sendAttendanceEmail && !ObjectFunctions.isNullOrEmpty(parentEmail) &&
										DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate))){
									sendAttendanceEmail(loggedUser,customer,aDate ,studentUser.getFullPersonName() ,parentEmail ,organzationLeavel,captureAttendance,attendanceStatus, 
											morningAttStatus,astatus,afternoonAttStatus,objMsg,attendance);
										
								}
							//if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(sms)){								
							objMsg = null;
							parentEmail = null;
							attendance = null; 
							studentUser = null;
						}
					}
					}
					SendSmsTrackingThread R1 = new SendSmsTrackingThread(messageMobileMap,smsServiceProvider);
				    R1.start();
				}
			}
			
			  if(!StringFunctions.isNullOrEmpty(sms))
		      {
		    	  if("SMS".equalsIgnoreCase(sms) && (!StringFunctions.isNullOrEmpty(email))){
		    		  msg.put("0", "Attendance recorded successfully and SMS,Email sent successfully.");
		    	  }else  if(!StringFunctions.isNullOrEmpty(email)){
		    		  msg.put("0", "Attendance recorded successfully and Email sent successfully.");
		    	  }else{
		    		  msg.put("0", "Attendance recorded successfully and SMS sent successfully.");
		    	  }
		      } else if(!StringFunctions.isNullOrEmpty(email)){
		    	  msg.put("0", "Attendance recorded successfully and Email sent successfully.");
		      }
		      else 
	    		  msg.put("0", "Attendance recorded successfully.");
			  
			 String clause = " custId=" + custId+ " and classSectionId = " + classId + " and attendanceDate = '" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate)+ "'";
				
			   staffDailyAttendanceSubmitTrack = (StaffDailyAttendanceSubmitTrack) this.get(StaffDailyAttendanceSubmitTrack.class,clause);
				if(ObjectFunctions.isNullOrEmpty(staffDailyAttendanceSubmitTrack)){
					staffDailyAttendanceSubmitTrack = new StaffDailyAttendanceSubmitTrack();
					staffDailyAttendanceSubmitTrack.setCustId(custId);
					staffDailyAttendanceSubmitTrack.setAcademicYearId(academicYearId);
					staffDailyAttendanceSubmitTrack.setCreatedById(userId);
					staffDailyAttendanceSubmitTrack.setCreatedDate(new Date());
					staffDailyAttendanceSubmitTrack.setAttendanceDate(aDate);
					staffDailyAttendanceSubmitTrack.setLastUpdatedDate(new Date());
					staffDailyAttendanceSubmitTrack.setLastAccessDate(new Date());
					staffDailyAttendanceSubmitTrack.setStaffAccountId(userId);
					staffDailyAttendanceSubmitTrack.setClassSectionId(classId);
				}else{
					staffDailyAttendanceSubmitTrack.setLastUpdatedDate(new Date());
					staffDailyAttendanceSubmitTrack.setLastAccessDate(new Date());
					staffDailyAttendanceSubmitTrack.setStaffAccountId(userId);
				}
					this.save(staffDailyAttendanceSubmitTrack);
				
			
			/*if(checkSMS)
				msg.put("0", "Attendance recorded successfully and SMS sent successfully.");
			else
				msg.put("0", "Attendance recorded successfully.");*/
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return msg;
	}

	public  Map<String,String> updateStudentAddress(String formData, long custId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'updateStudentAddress' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		
		try {
			
			if (StringFunctions.isNullOrEmpty(formData)) {
				msg.put("1", "Please resubmit with Student address");
				return msg;
			}
			
			StudyClass studyClass = null;
			Student student = null;
			State state = null;
			ClassName classId = null;
			AcademicYear academicYear = null;
			
			JSONArray studentDataJA = new JSONArray(formData);
			JSONObject studentDetailsJson = null;
			for (int i = 0; i < studentDataJA.length(); i++) {
				studentDetailsJson = studentDataJA.getJSONObject(i);
				if (!ObjectFunctions.isNullOrEmpty(studentDetailsJson)) {
					long studentId = (Integer) studentDetailsJson.get("StudentId");
					student = (Student) this.get(Student.class,"id=" + studentId);
					if (!ObjectFunctions.isNullOrEmpty(student)) {
						
						// JSR TO-DO : Why do we need to re-attach the class and class section
						classId = (ClassName) this.get(ClassName.class, "id="+ (Integer) studentDetailsJson.get("ClassId"));
						if (!ObjectFunctions.isNullOrEmpty(classId)) {
							student.setClassNameClassId(classId);
						}
						studyClass = (StudyClass) this.get(StudyClass.class, "id="+ (Integer) studentDetailsJson.get("StudyClassId"));
						if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
							student.setClassSection(studyClass);
						}
						academicYear = (AcademicYear) this.get(AcademicYear.class, "id="+ (Integer) studentDetailsJson.get("AcademicYearId"));
						if (!ObjectFunctions.isNullOrEmpty(academicYear)) {
							student.setAcademicYear(academicYear);
						}
						student.setLastUpdatedDate(new Date());
						String admissionNumber = (String) studentDetailsJson.get("AdmissionNumber");
						String clause = " custId=" + custId+ " and admissionNumber='"+ admissionNumber + "' and id!="+ studentId;
						User user = (User) this.get(User.class,clause);
						if (ObjectFunctions.isNullOrEmpty(user)) {
							student.getAccount().setAdmissionNumber(admissionNumber);
							student.setCategoryId((Integer) studentDetailsJson.get("FeeCategoryId"));
							student.getAccount().getPerson().setFirstName((String) studentDetailsJson.get("FirstName"));
							student.getAccount().getPerson().setLastName((String) studentDetailsJson.get("LastName"));
							student.getAccount().getPerson().setFatherName((String) studentDetailsJson.get("FatherName"));
							student.getAccount().getPerson().setMotherName((String) studentDetailsJson.get("MotherName"));
							student.getAccount().getPrimaryAddress().setStreetName((String) studentDetailsJson.get("Street"));
							student.getAccount().getPrimaryAddress().setCity((String) studentDetailsJson.get("City"));
							state = (State) this.get(State.class,"stateName='"+ (String) studentDetailsJson.get("State") + "'");
							if (!ObjectFunctions.isNullOrEmpty(state)) {
								if (StringFunctions.isNotNullOrEmpty(state.getStateCode())) {
									student.getAccount().getPrimaryAddress().setState(state.getStateCode());
								} else
									student.getAccount().getPrimaryAddress().setState("null");
							}
							student.getAccount().getPrimaryAddress().setPostalCode((String) studentDetailsJson.get("ZipCode"));
							student.getAccount().getPerson().setPhoneNumber((String) studentDetailsJson.get("PhoneNumber"));
							student.getAccount().getPerson().setMobileNumber((String) studentDetailsJson.get("MobileNumber"));
							student.getAccount().getPerson().setParentEmail((String) studentDetailsJson.get("ParentEmail"));
							this.save(student);
						}
						student = null;
						state = null;
						classId = null;
						academicYear = null;
						studentDetailsJson = null;
					}
					else
					{
						msg.put("1", "Student record doesn't exist for student Id : " +studentId);
					}
				}
			}
			
		} catch (Exception ex) {
			msg.put("1", "System error occurred, please contact System Administrator");
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return msg;
	}
	
	 /*
	* Removed PrepareAcademicYearId and used getUserAcademicYearId() is done by venkatesh - 04-19-2013
	*/
	public Map<String, String> updateStudent(StudentVo pobjStudent,Customer customer,long academicYearId, User loggedUser,String currentClass,String admissionNumber,String feeSettingIds,SMSServiceProviders smsServiceProvider) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'updateStudent' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		String newMobileNo = null;
		String oldMobileNo = null;
		try {
			if (pobjStudent.getId() != 0) {
				//Customer customer = (Customer) this.get(Customer.class, custId);
				Student lobjStudent = (Student) this.get(Student.class,pobjStudent.getId());
				//User sendUser =  (User) this.get(User.class,Long.valueOf(userId));
				//String oldParentEmail =	null;
				if(!ObjectFunctions.isNullOrEmpty(lobjStudent)){
					log.debug("updateStudent lobjStudent");
					StudyClassVO csection = pobjStudent.getStudyClassVo();
					if(!ObjectFunctions.isNullOrEmpty(csection) && csection.getId() > 0)
					{
						log.debug("updateStudent csection");
						if(csection.getId() != lobjStudent.getClassSectionId()){// Change student Class section
							StudyClass presentStudyClass=(StudyClass)this.get(StudyClass.class, csection.getId());
							StudyClass oldStudyClass=(StudyClass)this.get(StudyClass.class, lobjStudent.getClassSectionId());
							if(!ObjectFunctions.isNullOrEmpty(presentStudyClass)){
								log.debug("updateStudent presentStudyClass");
								int classStrength= this.getClassStudentsCountByClassIdandStatus(presentStudyClass.getId(),Constants.YES_STRING,customer.getId());
								int remainingSeats=presentStudyClass.getSectionCapacity()-classStrength;
								if(remainingSeats == 0)
									presentStudyClass.setSectionCapacity(presentStudyClass.getSectionCapacity()+1); 
								presentStudyClass.setFilledSeats(classStrength+1);
								this.save(presentStudyClass);
								log.debug("updateStudent generateStudnetRollNumber");
								
								
								//lobjStudent.setRollNumber(generateStudnetRollNumber(presentStudyClass.getId(),lobjStudent.getCustId(),lobjStudent.getAcademicYearId()));
								lobjStudent.setClassSection(presentStudyClass);
								lobjStudent.setClassNameClassId(presentStudyClass.getClassNameClassId());
								lobjStudent.setShippedSection("Section Changed from " +currentClass + " to " +presentStudyClass.getClassAndSection());
								log.debug("Section Changed from " +currentClass + " to " +presentStudyClass.getClassAndSection());
							}
							if(!ObjectFunctions.isNullOrEmpty(oldStudyClass)){
								int classStrength= this.getClassStudentsCountByClassIdandStatus(oldStudyClass.getId(),Constants.YES_STRING,customer.getId());
								oldStudyClass.setFilledSeats(classStrength - 1);
								this.save(oldStudyClass);
							}
							presentStudyClass = null;
							oldStudyClass=null;
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getAccount().getPersonVo())){
						log.debug("updateStudent pobjStudent.getAccount().getPersonVo()");
						PersonVO pobjPerson = pobjStudent.getAccount().getPersonVo();
						Person lobjPerson = lobjStudent.getAccount().getPerson();
						oldMobileNo = lobjPerson.getMobileNumber();
						if((!ObjectFunctions.isNullOrEmpty(pobjPerson.getFirstName()) || !ObjectFunctions.isNullOrEmpty(pobjPerson.getLastName())) 
								  && !ObjectFunctions.isNullOrEmpty(pobjPerson.getGender())){
							lobjPerson.setFirstName(pobjPerson.getFirstName());
							lobjPerson.setLastName(pobjPerson.getLastName());
							lobjPerson.setGender(pobjPerson.getGender());
							lobjPerson.setClassJoined(pobjPerson.getClassJoined());
							lobjPerson.setDateOfBirth(pobjPerson.getDateOfBirth());
							lobjPerson.setDateOfJoining(pobjPerson.getDateOfJoining());
							lobjPerson.setPhoneNumber(pobjPerson.getPhoneNumber());
							lobjPerson.setPlaceOfBirth(pobjPerson.getPlaceOfBirth());
							lobjPerson.setLastSchool(pobjPerson.getLastSchool());
							lobjStudent.setRollNumber(pobjStudent.getRollNumber());
							lobjStudent.setTransportMode(pobjStudent.getTransportMode());
							lobjStudent.setRegisterNumber(pobjStudent.getRegisterNumber());
							if("T".equalsIgnoreCase(pobjStudent.getTransportMode())){
								log.debug("updateStudent pobjStudent.getTransportMode()");
								/*RouteBoardingPointsVO routeBoardingPointsVO = pobjStudent.getRouteBoardingPointsVO();
								RouteBoardingPoints routeBoardingPoints = new RouteBoardingPoints();
								routeBoardingPoints.copyFromVoToEntity(routeBoardingPoints, routeBoardingPointsVO);*/
								if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getRouteBoardingPointsVO()))
								if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getRouteBoardingPointsVO().getId()))
								{
									RouteBoardingPoints routeBoardingPoints = (RouteBoardingPoints) this.get(RouteBoardingPoints.class,"id="+pobjStudent.getRouteBoardingPointsVO().getId());
									if(!ObjectFunctions.isNullOrEmpty(routeBoardingPoints))
									{
										lobjStudent.setRouteBoardingPoints(routeBoardingPoints);
										lobjStudent.setVehicleAcademicDetailsId(pobjStudent.getVehicleAcademicDetailsId());
									}
									routeBoardingPoints = null;
								}
							}else{
								lobjStudent.setRouteBoardingPoints(null);
								lobjStudent.setVehicleAcademicDetailsId(null);
							}
							log.debug("updateStudent pobjStudent.getHostelMode()");
							if(!StringFunctions.isNullOrEmpty(pobjStudent.getHostelMode()))
							lobjStudent.setHostelMode(pobjStudent.getHostelMode());
							lobjPerson.setStudentEmail(pobjStudent.getAccount().getPersonVo().getStudentEmail());
							//lobjPerson.setStudentMobile(StringFunctions.getMobileNumberLengthChecking(pobjStudent.getAccount().getPersonVo().getStudentMobile()));
							lobjPerson.setStudentMobile(pobjStudent.getAccount().getPersonVo().getStudentMobile());
							lobjPerson.setAadharNumber(pobjStudent.getAccount().getPersonVo().getAadharNumber());
							lobjStudent.getAccount().getPerson().setStudentUniqueNumber(pobjStudent.getAccount().getPersonVo().getStudentUniqueNumber());
							lobjStudent.setSchoolMess(pobjStudent.getSchoolMess());
							lobjStudent.getAccount().setEnrollmentCode(pobjStudent.getAccount().getEnrollmentCode());
							lobjStudent.setRteStatus(pobjStudent.getRteStatus());
							lobjStudent.setBplStatus(pobjStudent.getBplStatus());
							lobjStudent.setBplNumber(pobjStudent.getBplNumber());
							lobjPerson.setStudentBankId(pobjStudent.getAccount().getPersonVo().getStudentBankId());
							if(StringFunctions.isNotNullOrEmpty(pobjStudent.getAccount().getPersonVo().getStsNumber()) && pobjStudent.getAccount().getPersonVo().getStsNumber().length() <= 15){
								lobjPerson.setStsNumber(pobjStudent.getAccount().getPersonVo().getStsNumber());
							}else{
								lobjPerson.setStsNumber("");
							}
							
								}
						if(StringFunctions.isNotNullOrEmpty(pobjStudent.getTempString3())){
							log.debug("updateStudent pobjStudent.getAccount().getAdmissionNumber()");
							lobjPerson.setFatherName(pobjPerson.getFatherName());
							lobjPerson.setOccupation(pobjPerson.getOccupation());
							lobjPerson.setMotherName(pobjPerson.getMotherName());
							lobjPerson.setBloodGroup(pobjPerson.getBloodGroup());
							lobjPerson.setPrefferedHospital(pobjPerson.getPrefferedHospital());
							lobjPerson.setAnnualIncome(pobjPerson.getAnnualIncome());
							lobjPerson.setFamilyDoctor(pobjPerson.getFamilyDoctor());
							lobjPerson.setMotherOccupation(pobjPerson.getMotherOccupation());
							lobjPerson.setNationality(pobjPerson.getNationality());
							lobjPerson.setParentEmail(pobjPerson.getParentEmail());
							//lobjStudent.setOutSideSchoolDuty(pobjStudent.getOutSideSchoolDuty());
							
							if (StringFunctions.isNullOrEmpty(pobjStudent.getOutSideSchoolDuty())) 
								lobjStudent.setOutSideSchoolDuty(Constants.NO_STRING);
							else
								lobjStudent.setOutSideSchoolDuty(pobjStudent.getOutSideSchoolDuty());
								lobjStudent.setGoals(pobjStudent.getGoals());
								lobjStudent.setStrengths(pobjStudent.getStrengths());
								lobjStudent.setInterestsAndHobbies(pobjStudent.getInterestsAndHobbies());
								lobjStudent.setResponsibilities(pobjStudent.getResponsibilities());
								lobjStudent.setAchievements(pobjStudent.getAchievements());
								lobjStudent.setRemarks(pobjStudent.getRemarks());
								
								//if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getStrengths()))
								//if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getInterestsAndHobbies()))
								//if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getResponsibilities()))
								//if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getAchievements()))
								//if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getRemarks()))
							if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getPromoteToClass()))
								lobjStudent.setStrengths(pobjStudent.getPromoteToClass());
							
							if(!StringFunctions.isNullOrEmpty(pobjStudent.getPtaStatus()))
								lobjStudent.setPtaStatus(pobjStudent.getPtaStatus());
							else
								lobjStudent.setPtaStatus("N");
							if(pobjPerson.getReligionId() !=0){
								lobjPerson.setReligionId(pobjPerson.getReligionId());
							}
							else{
								CommonType commonType =(CommonType)this.get(CommonType.class,"custId="+customer.getId()+" and skillTypeName like '%"+"Other"+"%' and type like '%"+"RELIGION"+"%' ");
								if(!ObjectFunctions.isNullOrEmpty(commonType)){
									lobjPerson.setReligionId(commonType.getId());
								}
							}
							if(pobjPerson.getCastId() !=0){
								lobjPerson.setCastId(pobjPerson.getCastId());
							}
							else{
								CastSettings castSettings = (CastSettings)this.get(CastSettings.class,"custId ="+customer.getId()+" and castName like '%"+"Other"+"%' ");
								if(!ObjectFunctions.isNullOrEmpty(castSettings)){
									lobjPerson.setCastId(castSettings.getId());
								}		
							}
							log.debug("updateStudent castSettings ending");
							lobjPerson.setSubCastId(pobjStudent.getAccount().getPersonVo().getSubCastId());
							lobjPerson.setMotherToungId(pobjStudent.getAccount().getPersonVo().getMotherToungId());
							/*lobjPerson.setPhoneNumber(pobjStudent.getAccount().getPerson().getPhoneNumber());
							lobjPerson.setMobileNumber(pobjStudent.getAccount().getPerson().getMobileNumber());*/
							lobjPerson.setIdentification1(pobjStudent.getAccount().getPersonVo().getIdentification1());
							lobjPerson.setIdentification2(pobjStudent.getAccount().getPersonVo().getIdentification2());
							lobjPerson.setHeight(pobjStudent.getAccount().getPersonVo().getHeight());
							lobjPerson.setWeight(pobjStudent.getAccount().getPersonVo().getWeight());
							lobjPerson.setHeight2(pobjStudent.getAccount().getPersonVo().getHeight2());
							lobjPerson.setWeight2(pobjStudent.getAccount().getPersonVo().getWeight2());
							lobjPerson.setTeeth(pobjStudent.getAccount().getPersonVo().getTeeth());
							lobjPerson.setVisionL(pobjStudent.getAccount().getPersonVo().getVisionL());
							lobjPerson.setVisionR(pobjStudent.getAccount().getPersonVo().getVisionR());
							lobjPerson.setOralHygiene(pobjStudent.getAccount().getPersonVo().getOralHygiene());
							lobjPerson.setRationCardNumber(pobjStudent.getAccount().getPersonVo().getRationCardNumber());
							lobjPerson.setCommunityNumber(pobjStudent.getAccount().getPersonVo().getCommunityNumber());
							lobjPerson.setSslcNumber(pobjStudent.getAccount().getPersonVo().getSslcNumber());
							lobjPerson.setTmrNumber(pobjStudent.getAccount().getPersonVo().getTmrNumber());
							lobjPerson.setPhId(pobjStudent.getAccount().getPersonVo().isPhId());
							lobjPerson.setPhysicallyHandicappedDesc(pobjStudent.getAccount().getPersonVo().getPhysicallyHandicappedDesc());
							lobjPerson.setIsPHDocsUploaded(pobjStudent.getAccount().getPersonVo().getIsPHDocsUploaded());
							lobjPerson.setAddressType(pobjStudent.getAccount().getPersonVo().getAddressType());
							
							lobjPerson.setFatherAadharNumber(pobjStudent.getAccount().getPersonVo().getFatherAadharNumber());
							lobjPerson.setMotherAadharNumber(pobjStudent.getAccount().getPersonVo().getMotherAadharNumber());
							
							
							lobjPerson.setAnotherParentEmail(pobjPerson.getAnotherParentEmail());
							/*lobjPerson.setSecondaryMobileNumber(StringFunctions.getMobileNumberLengthChecking(pobjPerson.getSecondaryMobileNumber()));
							lobjPerson.setMobileNumber(StringFunctions.getMobileNumberLengthChecking(pobjPerson.getMobileNumber()));
							lobjPerson.setAnotherSecondaryMobileNumber(StringFunctions.getMobileNumberLengthChecking(pobjPerson.getAnotherSecondaryMobileNumber()));
							lobjPerson.setAnotherMobileNumber(StringFunctions.getMobileNumberLengthChecking(pobjPerson.getAnotherMobileNumber()));*/
							lobjPerson.setSecondaryMobileNumber(pobjPerson.getSecondaryMobileNumber());
							lobjPerson.setMobileNumber(pobjPerson.getMobileNumber());
							lobjPerson.setAnotherSecondaryMobileNumber(pobjPerson.getAnotherSecondaryMobileNumber());
							lobjPerson.setAnotherMobileNumber(pobjPerson.getAnotherMobileNumber());
							lobjPerson.setNationality(pobjStudent.getAccount().getPersonVo().getNationality());
							//oldParentEmail = lobjStudent.getAccount().getPerson().getParentEmail();
							
							newMobileNo = pobjPerson.getMobileNumber();
							
							
						}
						lobjStudent.getAccount().setPerson(lobjPerson);
						//Added by Siva on 20 Apr 2016 for Stale State exception Saving account object separately
						//User sUser = lobjStudent.getAccount();
						//sUser =(User) this.saveOrUpdateObject(sUser);
						//this.saveOrUpdateObject(sUser);
					}
					Fee fee=null;
					log.debug("updateStudent getFeePaidStatus entering");
					if ("N".equalsIgnoreCase(pobjStudent.getFeePaidStatus()) || "C".equalsIgnoreCase(lobjStudent.getFeePaidStatus())) {
						if (pobjStudent.getCategoryId() != 0) {
							
							if(!StringFunctions.isNullOrEmpty(feeSettingIds))
							{
								StringBuffer feeQuery = new StringBuffer().append("classId=");
								if (!ObjectFunctions.isNullOrEmpty(pobjStudent.getClassNameVo()))
									feeQuery.append(pobjStudent.getClassNameVo().getId());
								else
									feeQuery.append(lobjStudent.getClassNameClassId().getId());
								List<String> schoolTermIds = this.getAll("select id from schoolTerms where custId="+customer.getId()+" and academicYearId="+academicYearId+" and feeSettingId in "+feeSettingIds);
								if(ObjectFunctions.isNullOrEmpty(schoolTermIds))
									schoolTermIds.add("0");
								feeQuery.append(" and categoryId="+ pobjStudent.getCategoryId()+ " and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
							//	feeQuery.append(" and categoryId="+ pobjStudent.getCategoryId()+ " and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+") and categoryId="+pobjStudent.getCategoryId());
								fee = (Fee) studentDao.get(Fee.class, feeQuery.toString());
								/* Below condition for if any student have committed fee and they click on pay option. We are generating default records and they did not pay any payment. In this scenario when they came and change the category id we need to remove the default record in payment table. */
								/*if(Constants.YES_STRING.equalsIgnoreCase(customer.getCommittedFeeStatus())){
									if(lobjStudent.getCategoryId() != pobjStudent.getCategoryId()){
										if("C".equalsIgnoreCase(lobjStudent.getFeePaidStatus())){
											removeCommittedFeeEntries(pobjStudent);
										}
									}
								}*/
								lobjStudent.setCategoryId(pobjStudent.getCategoryId());
								if (!ObjectFunctions.isNullOrEmpty(fee))
									lobjStudent.setFeeConfigured(Constants.YES_STRING);
								else
									lobjStudent.setFeeConfigured(Constants.NO_STRING);
							}
						}
					}
					log.debug("updateStudent caegories changes");
					/*if(pobjStudent.getCategoryId()!=0){
						lobjStudent.setCategoryId(pobjStudent.getCategoryId());
					}*/		
					if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getProfileImage())){
						UserImage profileImage = null;
						if(!ObjectFunctions.isNullOrEmpty(lobjStudent.getProfileImage())){
							profileImage = new UserImage();
							/*UserImageVO userImageVO = pobjStudent.getProfileImage();*/
							UserImageVO userImageVO = pobjStudent.getProfileImage();
							profileImage = profileImage.copyFromVoToEntity(profileImage, userImageVO);
							//lobjStudent.setProfileImage(profileImage.copyFromVoToEntity(profileImage, userImageVO));
						}else{
							//profileImage =new UserImage();
							profileImage = (UserImage)this.get(UserImage.class, pobjStudent.getProfileImage().getId());
						}
						/*@Ganesh - If i user below line I am getting stale state exception that why I change the code.*/
						//lobjStudent.setProfileImage(profileImage.copyFromVoToEntity(profileImage, userImageVO));
						lobjStudent.setProfileImage(profileImage);
						log.debug("updateStudent profileImage ending");
					}
					if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getDescription())){
						lobjStudent.setDescription(pobjStudent.getDescription().trim());
					}
					if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo())){
						if(!StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo().getStreetName()) || !StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo().getAddressLine1()) || !StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo().getAddressLine2()) ||
								!StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo().getCity()) || !StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo().getState()) ||
								!StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo().getPostalCode()))
						{
							Object[] stateIdObj = null;
							if(!StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getPrimaryAddressVo().getState())){
								stateIdObj = this.get("select id,stateCode from State where stateCode='"+pobjStudent.getAccount().getPrimaryAddressVo().getState()+"'");
							}
							log.debug("updateStudent getPrimaryAddressVo starting");
							if(!ObjectFunctions.isNullOrEmpty(lobjStudent.getAccount().getPrimaryAddress()))
							{
								//lobjStudent.getAccount().getPrimaryAddress().setStreetName(pobjStudent.getAccount().getPrimaryAddressVo().getStreetName());
								lobjStudent.getAccount().getPrimaryAddress().setAddressLine1(pobjStudent.getAccount().getPrimaryAddressVo().getAddressLine1());
								lobjStudent.getAccount().getPrimaryAddress().setAddressLine2(pobjStudent.getAccount().getPrimaryAddressVo().getAddressLine2());
								lobjStudent.getAccount().getPrimaryAddress().setCity(pobjStudent.getAccount().getPrimaryAddressVo().getCity());
								lobjStudent.getAccount().getPrimaryAddress().setState(pobjStudent.getAccount().getPrimaryAddressVo().getState());
								if(!ObjectFunctions.isNullOrEmpty(stateIdObj))
									lobjStudent.getAccount().getPrimaryAddress().setStateId(Long.valueOf(stateIdObj[0].toString()));
								lobjStudent.getAccount().getPrimaryAddress().setPostalCode(pobjStudent.getAccount().getPrimaryAddressVo().getPostalCode());
								HouseType houseType =null;
								houseType = (HouseType) this.get(HouseType.class, "id ="+pobjStudent.getAccount().getPrimaryAddressVo().getHouseTypeId())  ;
								lobjStudent.getAccount().getPrimaryAddress().setHouseType(houseType);
							}
							else
							{
								Address primaryAddress = new Address();
								primaryAddress.setStreetName(pobjStudent.getAccount().getPrimaryAddressVo().getStreetName());
								primaryAddress.setAddressLine1(pobjStudent.getAccount().getPrimaryAddressVo().getAddressLine1());
								primaryAddress.setAddressLine2(pobjStudent.getAccount().getPrimaryAddressVo().getAddressLine2());
								primaryAddress.setCity(pobjStudent.getAccount().getPrimaryAddressVo().getCity());
								primaryAddress.setState(pobjStudent.getAccount().getPrimaryAddressVo().getState());
								if(!ObjectFunctions.isNullOrEmpty(stateIdObj))
									primaryAddress.setStateId(Long.valueOf(stateIdObj[0].toString()));
								primaryAddress.setPostalCode(pobjStudent.getAccount().getPrimaryAddressVo().getPostalCode());
								HouseType houseType =null;
								houseType = (HouseType) this.get(HouseType.class, "id ="+pobjStudent.getAccount().getPrimaryAddressVo().getHouseTypeId())  ;
								primaryAddress.setHouseType(houseType);
								lobjStudent.getAccount().setPrimaryAddress(primaryAddress);
								primaryAddress = null;
							}
							
							lobjStudent.getAccount().getPrimaryAddress().setLastUpdatedDate(new Date());
						}
					}
					Address tempararyaddress = null;
					if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getAccount().getTempararyAddressVo())){
						if(!StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getTempararyAddressVo().getStreetName()) || !StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getTempararyAddressVo().getAddressLine1()) || !StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getTempararyAddressVo().getAddressLine2()) ||
								!StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getTempararyAddressVo().getCity()) || !StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getTempararyAddressVo().getState()) ||
								!StringFunctions.isNullOrEmpty(pobjStudent.getAccount().getTempararyAddressVo().getPostalCode()))
						{
							if(!ObjectFunctions.isNullOrEmpty(lobjStudent.getAccount().getTempararyAddress())){
							//lobjStudent.getAccount().getTempararyAddress().setStreetName(pobjStudent.getAccount().getTempararyAddressVo().getStreetName());
								lobjStudent.getAccount().getTempararyAddress().setAddressLine1(pobjStudent.getAccount().getTempararyAddressVo().getAddressLine1());
								lobjStudent.getAccount().getTempararyAddress().setAddressLine2(pobjStudent.getAccount().getTempararyAddressVo().getAddressLine2());
							lobjStudent.getAccount().getTempararyAddress().setCity(pobjStudent.getAccount().getTempararyAddressVo().getCity());
							lobjStudent.getAccount().getTempararyAddress().setState(pobjStudent.getAccount().getTempararyAddressVo().getState());
							lobjStudent.getAccount().getTempararyAddress().setPostalCode(pobjStudent.getAccount().getTempararyAddressVo().getPostalCode());
							lobjStudent.getAccount().getTempararyAddress().setLastUpdatedDate(new Date());
	
							}
							else{
								tempararyaddress = new Address();
							    //tempararyaddress.setStreetName(pobjStudent.getAccount().getTempararyAddressVo().getStreetName());
								tempararyaddress.setAddressLine1(pobjStudent.getAccount().getTempararyAddressVo().getAddressLine1());
								tempararyaddress.setAddressLine2(pobjStudent.getAccount().getTempararyAddressVo().getAddressLine2());
							    tempararyaddress.setCity(pobjStudent.getAccount().getTempararyAddressVo().getCity());
							    tempararyaddress.setState(pobjStudent.getAccount().getTempararyAddressVo().getState());
							    tempararyaddress.setPostalCode(pobjStudent.getAccount().getTempararyAddressVo().getPostalCode());
							    tempararyaddress.setLastUpdatedDate(new Date());
							    lobjStudent.getAccount().setTempararyAddress(tempararyaddress);
							}
						}
					}else{
						//tempararyaddress = new Address();
					}
					lobjStudent.setLastUpdatedDate(new Date());
					lobjStudent.setLastAccessDate(new Date());
					lobjStudent.setLastUpdatedById(loggedUser.getId());
					/*if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getAccount().getPerson().getParentEmail()))
						lobjStudent.getAccount().getPerson().setParentEmail(pobjStudent.getAccount().getPerson().getParentEmail());*/
					//if(!ObjectFunctions.isNullOrEmpty(pobjStudent.getAccount().getProfileImage())) //comment by cvs
						//lobjStudent.getAccount().setProfileImage(pobjStudent.getAccount().getProfileImage());//Student profileimage set with student ----rama
					//Added by Siva on 20 Apr 2016 for Stale State exception Saving account object separately
					this.saveOrUpdateObject(lobjStudent.getAccount());
					lobjStudent = (Student)this.saveOrUpdateObject(lobjStudent);
					log.debug("updateStudent lobjStudent saveOrUpdateObject");
					//if(StringFunctions.isNotNullOrEmpty(pobjStudent.getAccount().getUsername()) &&
							//!pobjStudent.getAccount().getUsername().equalsIgnoreCase(lobjStudent.getAccount().getUsername())){
					
					long studId = lobjStudent.getId();
					
					oldMobileNo = StringFunctions.getMobileNumberLengthChecking(oldMobileNo);
					newMobileNo = StringFunctions.getMobileNumberLengthChecking(newMobileNo);
					
					if(!StringFunctions.isNullOrEmpty(newMobileNo))
					{
						if(!newMobileNo.equalsIgnoreCase(oldMobileNo))
						{
							studId = 0;
						}
					}
					
					Customer masterCustomer = null;
					if(customer.getId() == 173)
						masterCustomer = customer;
					else
						masterCustomer = this.getMasterCustomerById();
					
					String response = createParentLoginAccount(customer,lobjStudent.getAcademicYear(),lobjStudent,false,masterCustomer,loggedUser);
					
					masterCustomer = null;
					log.debug("updateStudent lobjStudent saveOrUpdateObject");
					lobjStudent = (Student)this.merge(lobjStudent);
					log.debug("updateStudent :"+lobjStudent.getAccount().getUsername());
					User user = (User) this.get(User.class,Long.valueOf(lobjStudent.getAccount().getId()));
					log.debug(user.getUsername());
					msg.put("0","Student details updated successfully.");
					msg.put("99", lobjStudent.getStrId());
					//Sending notification for student profile update
					this.sendNotificationForStudentUpdate(lobjStudent);
				}
			}
		} catch (Exception ex) {
			msg.put("1", "System error occurred, please contact System Administrator");
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return msg;
	}
	
	/** @param studyClassId
     * @param customerId
     * @return
     * @Description 28th March Narahari: Doing Modularization of editStudent for roll numbers */
	public String generateStudnetRollNumber(long studyClassId, long customerId,long academicYearId) {
		List<Student> studentDetails=null;
		try {
			// long studentLastRecord = adminManager.getStudentLastRecordByStudyClassId(studyClassId, customerId);
			studentDetails=this.getAll(Student.class,"custId="+ customerId+ " and academicYearId="+ academicYearId+ " and classSectionId="+ studyClassId+" and status='"+ Constants.YES_STRING+ "' and description is null");
			if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
				Collections.sort(studentDetails,new StudentRollNumberComparator());
				Student student=studentDetails.get(studentDetails.size()-1);
				return Long.toString(Long.valueOf(student.getRollNumber()) + 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		studentDetails=null;
		return "1";
	}
	

	public String createParentLoginAccount(Customer customer,AcademicYear academicYear, Student student,boolean isNewStudent, Customer masterCustomer,User loggedUser)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManagerImpl 'createParentLoginAccount' method");
		}
		String userName = null;
		String parentId = null;
		try {
				User user = student.getAccount();
				User parentAccount = null;
				User availParentAccount = null;
				if(!ObjectFunctions.isNullOrEmpty(user) && StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName()))
				{
					//log.debug("Student user id..." + user.getId());
					//log.debug("Student parent user id..." + user.getStudentParent().getParentAccountId());
					Person stuPerson = user.getPerson();
					boolean isParentSMS = false;
						if(!ObjectFunctions.isNullOrEmpty(user.getStudentParent()))
							parentAccount = (User) this.get(User.class, Long.valueOf(user.getStudentParent().getId()));
						if(!ObjectFunctions.isNullOrEmpty(parentAccount) && parentAccount.getUsername().contains("_p") && StringFunctions.isNotNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(stuPerson.getMobileNumber())))
						{
								isParentSMS = true;
						}
						//Checking parent mobile number is given or not
						if(StringFunctions.isNotNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(stuPerson.getMobileNumber()))){
							if(StringFunctions.getMobileNumberLengthChecking(stuPerson.getMobileNumber()).contains("+91-")){
								userName = StringFunctions.getMobileNumberLengthChecking(stuPerson.getMobileNumber()).replace("+91-", "");
							}
							else{
								userName = stuPerson.getMobileNumber();
							}
							//Checking if parent is exists with given mobile number or not And checking username is changed from current username
							if(ObjectFunctions.isNullOrEmpty(parentAccount) || (!ObjectFunctions.isNullOrEmpty(parentAccount) && !parentAccount.getUsername().equalsIgnoreCase(userName))){
								availParentAccount = this.usernameAvailabulity(userName,customer.getId());
								isParentSMS = true;
							}
							//Checking if parent is exists with given mobile number or not, if parent exists then not creating parent, if yes then changing username by adding 'P' for the same 
							if(!ObjectFunctions.isNullOrEmpty(availParentAccount) && !availParentAccount.isParent() && !availParentAccount.isAdmin() &&  !availParentAccount.isSchoolStudent()){
									userName ="P"+userName;
									availParentAccount = this.usernameAvailabulity(userName,customer.getId());
							}
						}
						else{
							userName = user.getUsername()+"_p";
							availParentAccount = this.usernameAvailabulity(userName,customer.getId());
						}
						if(!ObjectFunctions.isNullOrEmpty(availParentAccount)){
							parentAccount = availParentAccount; 
						}
						parentAccount = addOrUpdateParentDetails(customer, academicYear, student, isNewStudent, masterCustomer, parentAccount, user, userName, isParentSMS,loggedUser);
					if(!ObjectFunctions.isNullOrEmpty(parentAccount)){
						parentId = parentAccount.getStrId();
					}
					log.debug("Parent login credentails established now..." + parentId);
				}
	    }
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	    return parentId;
	 }
	 
	private User addOrUpdateParentDetails(Customer customer, AcademicYear academicYear, Student student, boolean isNewStudent, Customer masterCustomer, User parentAccount, User user, String userName, boolean isParentSMS,User loggedUser) {
		
		try{
		Person stuPerson =user.getPerson();
		Person parentPerson =null;
		Address parentAdress =null;
		boolean isNewParent = false;
		boolean isOldParent = false;
		int sentSmsCount=this.getTotalSmsCount(academicYear.getCustId(),academicYear.getId());
		int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
		SMSServiceProviders smsServiceProviders=(SMSServiceProviders)  this.getSMSServiceProviderByCustId(masterCustomer.getSmsServiceProviderId());
		String newPassword = StringUtil.generateRandomString();
			if(ObjectFunctions.isNullOrEmpty(parentAccount))
			{
				parentAccount = new User();
				parentAccount.setUsername(userName);
				parentAccount.setSecondaryUsername(parentAccount.getUsername());
				parentAccount.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
				parentAccount.addNewRole(this.getRoleByName(Constants.SCHOOL_PARENT));
				parentPerson = new Person();
				if(StringFunctions.isNotNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(stuPerson.getMobileNumber())))
				isNewParent = true;
			}
			else{ 
				parentAccount.setUsername(userName);
				parentPerson = parentAccount.getPerson();
				isOldParent = true;
				parentAccount.setAccountExpired(false);
				parentAccount.setEnabled(true);	
			}
			if(!ObjectFunctions.isNullOrEmpty(stuPerson)){
				parentPerson.setFirstName(stuPerson.getFatherName());
				parentPerson.setMobileNumber(StringFunctions.getMobileNumberLengthChecking(stuPerson.getMobileNumber()));
				parentPerson.setSecondaryMobileNumber(StringFunctions.getMobileNumberLengthChecking(stuPerson.getSecondaryMobileNumber()));
				parentPerson.setPhoneNumber(stuPerson.getPhoneNumber());
				parentPerson.setParentEmail(stuPerson.getParentEmail());
				parentPerson.setAddressType(stuPerson.getAddressType());
				parentPerson.setAnotherMobileNumber(StringFunctions.getMobileNumberLengthChecking(stuPerson.getAnotherMobileNumber()));
				parentPerson.setAnotherSecondaryMobileNumber(StringFunctions.getMobileNumberLengthChecking(stuPerson.getAnotherSecondaryMobileNumber()));
				parentPerson.setAnotherParentEmail(stuPerson.getAnotherParentEmail());
			}
			parentAccount.setPerson(parentPerson);
			parentAccount.setCustId(customer.getId());
			if(!ObjectFunctions.isNullOrEmpty(user.getPrimaryAddress())){
				if(!ObjectFunctions.isNullOrEmpty(parentAccount.getPrimaryAddress())){
					parentAdress=parentAccount.getPrimaryAddress();
					if(user.getPrimaryAddress().getId()==parentAccount.getPrimaryAddress().getId()){
						parentAdress=new Address();
					}
				}
				if(ObjectFunctions.isNullOrEmpty(parentAdress))
					parentAdress = new Address();
				parentAdress.setStreetName(user.getPrimaryAddress().getStreetName());
				parentAdress.setAddressLine1(user.getPrimaryAddress().getAddressLine1());
				parentAdress.setCity(user.getPrimaryAddress().getCity());
				parentAdress.setState(user.getPrimaryAddress().getState());
				parentAdress.setPostalCode(user.getPrimaryAddress().getPostalCode());
				parentAccount.setPrimaryAddress(parentAdress);
			}
			try {
				parentAccount = (User)this.saveOrUpdateObject(parentAccount);
				this.updateParentSecondaryUsername(parentAccount.getId());
				//Setting Parent accountId to student
				/*user.getStudentParent().setParentAccountId(parentAccount.getId());
				user.getStudentParent().setStudentAccountId(user.getId());*/
				if(ObjectFunctions.isNullOrEmpty(user.getStudentParent())){
					user.addParentChild(parentAccount);
					user = (User)this.saveOrUpdateObject(user);
				}
				//Checking weather existing parent having same username/mobilenumber
				if(parentAccount.getId() != user.getStudentParent().getId()){
					try{
					user.removeParentChild(user.getStudentParent());
					user = (User)this.saveOrUpdateObject(user);
					user.addParentChild(parentAccount);
					user = (User)this.saveOrUpdateObject(user);
					}
					catch(Exception e){e.printStackTrace();}
				}
				this.updateStudentUserName(user.getId(),PasswordUtils.passwordEncoder(String.valueOf(user.getId()),null));
			} catch (Exception e) {
				e.printStackTrace();
			}
				//Sending email to parent
				if((customer.isCheckEmailService())){
					try {
					String parentsEmail = null;
					if("R".equalsIgnoreCase(stuPerson.getAddressType()))
						 parentsEmail = parentPerson.getParentEmail();
					else
						 parentsEmail = parentPerson.getAnotherParentEmail();
					if(StringFunctions.isNotNullOrEmpty(parentsEmail)){
						if(isNewParent)
						this.sendEmailToParentsAndStudent(parentsEmail,stuPerson.getFullPersonName(),customer,student.getClassAndSection(),user,parentAccount.getUsername(),"Parents",newPassword);
					}
					if(StringFunctions.isNotNullOrEmpty(user.getPerson().getStudentEmail())){
						if(isNewStudent) 
						this.sendEmailToParentsAndStudent(user.getPerson().getStudentEmail(),stuPerson.getFullPersonName(),customer,student.getClassAndSection(),user,null,"Students",null);
					}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				//Sending SMS to parent
				if(allottedSmsCount==0){
					//This is for sending sms to future academic year parents and students. Because sms count will not be updated for future academic year. So checking current academic year sms count
					AcademicYear currentAcademicYear = this.getCurrentAcademicYear("Y", customer.getId());
					if(currentAcademicYear.getAllotedsms() > 0){
						sentSmsCount=this.getTotalSmsCount(currentAcademicYear.getCustId(),currentAcademicYear.getId());
						allottedSmsCount = (int) currentAcademicYear.getAllotedsms()+(int) currentAcademicYear.getPaidSms(); 
					}
				}
				if((customer.isCheckMobileService() && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)  && customer.isCheckParentSmsService()))
				{
					try{
					Set<String> mobileNumbersSet = null;
					boolean smsStatus = false;
					Messages message = new Messages();
					message.setStatus("M");
					if(!ObjectFunctions.isNullOrEmpty(loggedUser)){
						message.setCreatedById(loggedUser.getId());
						message.setSenderName(loggedUser.getUserRoleDescription());
					}
					message.setSmsSenderId(customer.getSender());
					if(isNewStudent)
					{
						if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(user.getPerson().getStudentMobile())) && !customer.getStandardType().equalsIgnoreCase("P")){
							mobileNumbersSet = new HashSet<String>(); 
							mobileNumbersSet.add(user.getPerson().getStudentMobile());
							if(student.getId() == 0){
								message.setCustomer(masterCustomer);
								smsStatus = this.sendSMSForStudent(user,mobileNumbersSet,message,customer.getOrganization(),smsServiceProviders);
							}else{
								message.setCustomer(customer);
								message.setAcademicYear(academicYear);
								smsStatus = this.sendSMSForStudent(user,mobileNumbersSet,message,customer.getOrganization(),smsServiceProviders);
							}
							
							if(smsStatus)
								message = this.saveMessageDetailsTracking(message,student,null,null);
						}
					}
					if((isNewParent || isParentSMS) && !isOldParent)
					{
						if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(parentPerson.getMobileNumber())) || !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(parentPerson.getAnotherMobileNumber()))){
							mobileNumbersSet=new HashSet<String>(); 
							mobileNumbersSet = this.addMobileNumbersBasedOnAddressType(customer.getMobileType(),StringFunctions.getMobileNumberLengthChecking(parentPerson.getMobileNumber()),parentPerson.getSecondaryMobileNumber(),StringFunctions.getMobileNumberLengthChecking(parentPerson.getAnotherMobileNumber()),parentPerson.getAnotherSecondaryMobileNumber(),stuPerson.getAddressType());
							if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
								if(student.getId() == 0){
									message.setCustomer(masterCustomer);
								}else{
									message.setCustomer(customer);
									message.setAcademicYear(academicYear);
								}
								smsStatus =  sendSMSForParents(parentAccount,customer,newPassword,user,mobileNumbersSet,message,student.getAccount().getUsername(),smsServiceProviders);
								
								if(smsStatus)
									message = this.saveMessageDetailsTracking(message,null,null,parentAccount);
							}
						}
					}
					message = null;
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			parentPerson = null;
			parentAdress= null;
			return parentAccount;
		}
		catch(Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	public List<ViewStudentClassDetails> GetStudentClassDetailsList(long custId, long academicYearId, Map<String,String> params) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'GetStudentClassDetailsList' method");
		}
		try {
			
			StringBuffer queryString=new StringBuffer();
			queryString.append("custId="+ custId+" and academicYearId="+ academicYearId);
			
			String param = params.get("classSectionId");
			
			if(!StringFunctions.isNullOrEmpty(params.get("classSectionId")) && !StringFunctions.isNullOrEmpty(params.get("Upload"))){
				
				// Ganesh : We need above condition because in one of the functionality we are searching who are not have images to student's. So through the form we are passing 'upload' string to anyId for identification. 
				queryString.append(" and classSectionId in(").append(params.get("classSectionId")+") and imageId =0 ");
			}
			else if (!StringFunctions.isNullOrEmpty(params.get("classSectionId"))) {
				queryString.append(" and classSectionId in(").append(params.get("classSectionId")+")");
				if (!StringFunctions.isNullOrEmpty(params.get("staffClassStudents"))) {
					if (!StringFunctions.isNullOrEmpty(params.get("name"))) {
						queryString.append(" and (firstName like '%"+ params.get("name") + "%' or lastName like '%"+ params.get("name")+ "%') ");
					} if (!StringFunctions.isNullOrEmpty(params.get("admissionNumber"))) {
						queryString.append(" and (admissionNumber like '%"+ params.get("admissionNumber")+ "%') ");
					}
				}
			}
			
			else if (!StringFunctions.isNullOrEmpty(params.get("name"))) {
				queryString.append(" and (firstName like '%"+ params.get("name") + "%' or lastName like '%"+ params.get("name")+ "%') ");
			} else if (!StringFunctions.isNullOrEmpty(params.get("admissionNumber"))) {
				queryString.append(" and (admissionNumber like '%"+ params.get("admissionNumber")+ "%') ");
			}
			
			if(params.get("schoolTransport") == "Y" || params.get("transportFinance") == "Y"){
				queryString.append(" and transportMode='T' and boardingPointId!=0 and vehicleAcademicDetailsId!=0 ");
			}if(params.get("schoolHostler") == "Y"){
				queryString.append(" and hostelMode='H' ");
			}
			
			queryString.append(" and studDiscontinueDesc is null");
			queryString.append(" order by classId,classSectionId ");
			return (List<ViewStudentClassDetails>)this.getAll(ViewStudentClassDetails.class,queryString.toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> GenerateStudyAndBonafiedCertificate(Map<String,String> params, StringBuffer studyTemplateFilePath) {
		if (log.isInfoEnabled()) {
			log.info("Entering 'ajaxGenerateStudyAndBonafied' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		
		try {
			String certificateType= null;
			String custId = params.get("custId");
			String studyClassId = params.get("classId");
			String className = params.get("className");
			String studentNumber = params.get("studentNumber");
			String type = params.get("type");
			String userId = params.get("userId");
			String academicYearId = params.get("academicYearId");
			StringBuffer generatedTCsFilePath = new StringBuffer(studyTemplateFilePath).append("temp/");
			File outFile = new File(generatedTCsFilePath.toString()); 
			FileUtils.deleteDirectory(outFile);// Removes existing files
			outFile.mkdirs(); // If directories are not available it creates directories
			StringBuffer studentsListQuery = null;
			StringBuffer bookSettingsQuery = null;
			StringBuffer query = null;
			
			if("S".equalsIgnoreCase(type)){
				certificateType="SC";
			}
			else if("B".equalsIgnoreCase(type)){
				certificateType="BC";
			}
			else if("N".equalsIgnoreCase(type)){
				certificateType="ND";
			}
			else
			{
				certificateType=Constants.FEE_CERTIFICATE;
			}
			
			//For generating Study Certificate for classwise
			if(StringFunctions.isNotNullOrEmpty(studyClassId) && StringFunctions.isNotNullOrEmpty(className))
			{
				
				//For getting classId using studyClassId because we have setting certificates with classIds
				Object[] studyClassIds = this.get("select id,classNameClassId from studyClass where id="+studyClassId);
				//log.debug(studyClassIds[1]);
				query = new StringBuffer("custId=").append(custId).append(" and studyClassId=").append(studyClassId).append(" and certificateType='"+certificateType+"'");
				studentsListQuery = new StringBuffer("custId=").append(custId).append(" and academicYearId=").append(academicYearId).
				append(" and classSectionId=").append(studyClassId).append(" and studId in").append(studentNumber);
				if("S".equalsIgnoreCase(type)){
					Object[] bookSettingId = this.get("select studyBookId,classId from studyBookSettingsClasses where classId="+studyClassIds[1]);
					if(!ObjectFunctions.isNullOrEmpty(bookSettingId) && !ObjectFunctions.isNullOrEmpty(bookSettingId[0])){
						bookSettingsQuery = new StringBuffer("id="+bookSettingId[0].toString());
					}
					bookSettingId=null;
				}
			}
			//For generating Study Certificate by search form 
			else if(StringFunctions.isNotNullOrEmpty(studentNumber) && StringFunctions.isNotNullOrEmpty(studyClassId)){
				 query = new StringBuffer("studyClassId in").append(studyClassId).append(" and custId=").append(custId).append(" and certificateType='"+certificateType+"'");;
				 studentsListQuery = new StringBuffer("custId=").append(custId).append(" and academicYearId=").append(academicYearId).append(" and studId in").append(studentNumber);
				 if("S".equalsIgnoreCase(type))
					 bookSettingsQuery = new StringBuffer("academicYearId="+academicYearId);
			}
			IXDocReport report = null;
	 		IContext context = null;
	 		HashMap<Long, IXDocReport> studySettingsMap = new HashMap<Long, IXDocReport>();
	        HashMap<Long, StudyCertificateBookSettings> studyBookSettingsMap = new HashMap<Long, StudyCertificateBookSettings>();
	        if(!ObjectFunctions.isNullOrEmpty(query)){
				 List<StudyAndBonafiedSettings> studyAndBonafiedList  = this.getAll(StudyAndBonafiedSettings.class,query.toString());
				if(!ObjectFunctions.isNullOrEmpty(studyAndBonafiedList)){
					//File certFileLocation = null;
					InputStream templateFile= null;
			 		for(StudyAndBonafiedSettings sbSetting:studyAndBonafiedList)
			 		{
			 			URL url = new URL(sbSetting.getFilePath());
						URLConnection conn = url.openConnection();
						InputStream is = conn.getInputStream();
						
						File certFileLocation = File.createTempFile(sbSetting.getFileName(), null);
						certFileLocation.deleteOnExit();
						FileUtils.copyInputStreamToFile(is, certFileLocation);
						
						
						if(certFileLocation.exists()){
							templateFile= new FileInputStream(certFileLocation);
							report = XDocReportRegistry.getRegistry().loadReport(templateFile, TemplateEngineKind.Velocity );
							if (!ObjectFunctions.isNullOrEmpty(report)) {
								FieldsMetadata metadata = new FieldsMetadata();
								metadata.addFieldAsImage("logo");
								metadata.setUseImageSize(true);
								metadata.addFieldAsImage("fileImageNotExistsAndRemoveImageTemplate","logo",NullImageBehaviour.RemoveImageTemplate);
								report.setFieldsMetadata(metadata);
							}
							studySettingsMap.put(sbSetting.getStudyClassId(), report);
							//report=null;
						}
					}
				}
			}
	        List<ViewStudentsTCDetails> vwStudentList = null;
	        Object[] paidAmount = null;
			if(!ObjectFunctions.isNullOrEmpty(studentsListQuery))
				vwStudentList = this.getAll(ViewStudentsTCDetails.class, studentsListQuery.toString());
			if(Constants.FEE_CERTIFICATE.equalsIgnoreCase(certificateType)){
				vwStudentList = new ArrayList<ViewStudentsTCDetails>();
				List<ViewStudentsTCDetails> vwFeeStudentList = this.getAll(ViewStudentsTCDetails.class, studentsListQuery.toString());
				if(!ObjectFunctions.isNullOrEmpty(vwFeeStudentList)){
					for(ViewStudentsTCDetails viewStudentsTCDetails : vwFeeStudentList){
						paidAmount = this.get("select sp.studentId,IFNULL(sum(IFNULL(sp.paidAmount,0)) - IFNULL(sfp.refundAmount,0),0) AS paidAmount from studentPayment sp LEFT JOIN studentFeeRefund sfp on (sfp.studentId = sp.studentId)"
					       		+ "where (sp.studentId="+viewStudentsTCDetails.getStudId()+ " and concessionStatus='"+ Constants.NO_STRING+ "'"+" and deleteStatus='"+ Constants.NO_STRING+ "')");
						 if (!ObjectFunctions.isNullOrEmpty(paidAmount)) {
							// viewStudentsTCDetails.setPaidAmount((double) paidAmount[1]);
						 }
						 vwStudentList.add(viewStudentsTCDetails);
						 paidAmount = null;
						 viewStudentsTCDetails = null;
					}
					vwFeeStudentList = null;
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(bookSettingsQuery)){
				List<StudyCertificateBookSettings> studyBookSettingsList = this.getAll(StudyCertificateBookSettings.class, bookSettingsQuery.toString());
				if(ObjectFunctions.isNotNullOrEmpty(studyBookSettingsList)){
					for(StudyCertificateBookSettings studyBookSetting: studyBookSettingsList){
						if(ObjectFunctions.isNotNullOrEmpty(studyBookSetting.getClasses())){
							for(ClassName clas : studyBookSetting.getClasses()){
								//Object[] classSecionId = this.get("select id,classNameClassId from studyClass where classNameClassId="+clas.getId());
								studyBookSettingsMap.put(clas.getId(), studyBookSetting);
							}
						}
					}
					studyBookSettingsList=null;
				}
			}
			bookSettingsQuery=null;
			studentsListQuery=null;
			if (ObjectFunctions.isNullOrEmpty(vwStudentList) || ObjectFunctions.isNullOrEmpty(studySettingsMap)) {
				if("S".equalsIgnoreCase(type)){
					if(ObjectFunctions.isNullOrEmpty(studySettingsMap))
						this.writeToFile("Study certificate settings are not available. Please add Study certificate settings.",generatedTCsFilePath.toString()+"readMe.doc");
					if(ObjectFunctions.isNullOrEmpty(vwStudentList))
						this.writeToFile("Students are not avilable for generating Study Certificate.",generatedTCsFilePath.toString()+"readMe.doc");
				}else if("B".equalsIgnoreCase(type)){
					if(ObjectFunctions.isNullOrEmpty(studySettingsMap))
						this.writeToFile("Bonafied certificate settings are not available. Please add Bonafied certificate settings.",generatedTCsFilePath.toString()+"readMe.doc");
					if(ObjectFunctions.isNullOrEmpty(vwStudentList))
						this.writeToFile("Students are not avilable for generating Bonafied Certificate.",generatedTCsFilePath.toString()+"readMe.doc");
				}else if("N".equalsIgnoreCase(type)){
					if(ObjectFunctions.isNullOrEmpty(studySettingsMap))
						this.writeToFile("No Due certificate settings are not available. Please add No Due certificate settings.",generatedTCsFilePath.toString()+"readMe.doc");
					if(ObjectFunctions.isNullOrEmpty(vwStudentList))
						this.writeToFile("Students are not avilable for generating No Due Certificate.",generatedTCsFilePath.toString()+"readMe.doc");
				}else{
					if(ObjectFunctions.isNullOrEmpty(studySettingsMap))
						this.writeToFile("Fee certificate settings are not available. Please add Fee certificate settings.",generatedTCsFilePath.toString()+"readMe.doc");
					if(ObjectFunctions.isNullOrEmpty(vwStudentList))
						this.writeToFile("Students are not avilable for generating Fee Certificate.",generatedTCsFilePath.toString()+"readMe.doc");
				}
				msg.put("status", "P");
				return msg;
			}else if("S".equalsIgnoreCase(type) && ObjectFunctions.isNullOrEmpty(studyBookSettingsMap)){
				log.info("generatedTCsFilePath : " +generatedTCsFilePath.toString());
				this.writeToFile("Please create book settings.",generatedTCsFilePath.toString()+"readMe.doc");
				msg.put("status", "P");
				return msg;
			}
			try{
		 		StringBuffer studentDOCXFilePath = null;
		 		Collections.sort(vwStudentList);
			 	for(ViewStudentsTCDetails viewStudentPersonAccountDetails:vwStudentList){
						report = studySettingsMap.get(viewStudentPersonAccountDetails.getClassSectionId());
						if(ObjectFunctions.isNullOrEmpty(report)){
							studentDOCXFilePath = new StringBuffer(generatedTCsFilePath).append("readMe").append(viewStudentPersonAccountDetails.getClassAndSection()).
							append("_").append(viewStudentPersonAccountDetails.getPersonFullName()).append("_").append(viewStudentPersonAccountDetails.getAdmissionNumber().replaceAll("/", "")).append(".doc");
							if("S".equalsIgnoreCase(type))
								this.writeToFile("Please create Study Certificate settings.",studentDOCXFilePath.toString());
							else if("B".equalsIgnoreCase(type))
								this.writeToFile("Please create Bonafied Certificate settings.",studentDOCXFilePath.toString());
							else if("N".equalsIgnoreCase(type))
								this.writeToFile("Please create No Due Certificate settings.",studentDOCXFilePath.toString());
							else
								this.writeToFile("Please create Fee Certificate settings.",studentDOCXFilePath.toString());
							continue;
						}
						studentDOCXFilePath = new StringBuffer(generatedTCsFilePath).append(viewStudentPersonAccountDetails.getClassAndSection()).
								append("_").append(viewStudentPersonAccountDetails.getPersonFullName()).append("_").append(viewStudentPersonAccountDetails.getAdmissionNumber().replaceAll("/", "")).append(".doc");
						StudyCertificateBookSettings studyBookSettings = studyBookSettingsMap.get(viewStudentPersonAccountDetails.getClassId());
						// CVS on 02-06-14 :  when generate the No Due Certificate we dont have book settings option .so skip the bellow lines
						if("S".equalsIgnoreCase(type)){
							if(ObjectFunctions.isNullOrEmpty(studyBookSettings)){
									if("S".equalsIgnoreCase(type))
										this.writeToFile("Please create Study Certificate settings.",studentDOCXFilePath.toString());
									
									studyBookSettings = null;
									continue;
							}else if(viewStudentPersonAccountDetails.getSerialNumber() == 0){
									int startingSerialNo = studyBookSettings.getStudyStartingNumber();
									StudyCertificate studyCertificate=new StudyCertificate(); 
									studyCertificate.setBookSetting(studyBookSettings);
									studyCertificate.setCustId(Long.parseLong(custId));
									studyCertificate.setAccountId(viewStudentPersonAccountDetails.getAccountId());
									studyCertificate.setCreatedById(Long.parseLong(userId));
									studyCertificate.setCreatedDate(new Date());
									studyCertificate.setSerialNumber(startingSerialNo++);
									studyCertificate.setLastAccessDate(new Date());
									studyCertificate.setLastUpdatedDate(new Date());
									studyCertificate = (StudyCertificate)this.save(studyCertificate);
					                viewStudentPersonAccountDetails.setTcSerialNumber(studyCertificate.getSerialNumber());
					                studyBookSettings.setStudyStartingNumber(startingSerialNo);
					                studyBookSettings = (StudyCertificateBookSettings)this.save(studyBookSettings);
					                studyBookSettingsMap.put(viewStudentPersonAccountDetails.getClassId(), studyBookSettings);
					                studyCertificate=null;
					                studyBookSettings=null;
							}
						}
						try{
							context = report.createContext();
							if (StringFunctions.isNotNullOrEmpty(viewStudentPersonAccountDetails.getImagePath())) {
								//File imageFile = new File(viewStudentPersonAccountDetails.getSchoolIdCardAttachmentFilePath());
								
								File imageFile = this.loadImageFromURL(viewStudentPersonAccountDetails.getSchoolIdCardAttachmentFilePath());
								if (!ObjectFunctions.isNullOrEmpty(imageFile)&& imageFile.exists()) {
									IImageProvider logo = new FileImageProvider(imageFile);
									if (!ObjectFunctions.isNullOrEmpty(logo))
										context.put("logo", logo);
								}
							}
						   	context.put("student", viewStudentPersonAccountDetails);
						   	context.put("stu", viewStudentPersonAccountDetails);
						    OutputStream out = new FileOutputStream(new File(studentDOCXFilePath.toString()));
			                report.process(context, out);
			                report=null;
			                context=null;
			                out.close();
		            	}catch (Exception ex) {
		            		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		            		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
						}/*finally{
							
						}*/
						viewStudentPersonAccountDetails = null;
						studentDOCXFilePath = null;
					}
					vwStudentList=null;
					msg.put("status", "C");
 				}catch (Exception ex) {
 					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 					JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				}finally{
					studySettingsMap=null;
					studyBookSettingsMap=null;
				}
			
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}/*finally{
			
		}*/
		return msg;
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StudyClass> getStudyClassesByClassNameClassId(long classNameClassId,long customerId,long academicYearId) {
		return studentDao.getStudyClassesByClassNameClassId(classNameClassId,customerId,academicYearId);
	}
	
	public void CreateStudentUserIsAvailable(TransferStudent transferStudent,long custId,AcademicYear academicYear, User user, StudyClass studyClass,Person person,String userName, long catId, String transportMode,String hostelMode,UserImage userImage,long boardingPointId,long vehicleId,double committedFee,String goals,String strengths,String interestsAndHobbies,String responsibilities,String achievements,String remarks,String outsideDuty,String schoolMess) 
    { 
			if (log.isDebugEnabled()) {
				log.debug("Entering 'CreateStudentUserIsAvailable' method");
			}
			try {
				Customer masterCustomer = null;
				Customer customer = (Customer)this.get(Customer.class,custId);
				if(custId == 173)
					masterCustomer = customer;
				else
					masterCustomer = this.getMasterCustomerById();
				@SuppressWarnings("unused")
				SMSServiceProviders smsServiceProvider=(SMSServiceProviders)  this.getSMSServiceProviderByCustId(masterCustomer.getSmsServiceProviderId());
				Student student = new Student();
				student.setAccount(user);
				student.setCustId(custId);
				//User parentAccount = null;
				//String parentId = null;
				
				
				student.setRollNumber(this.generateStudnetRollNumber(studyClass.getId(),studyClass.getCustId(),studyClass.getAcademicYearId()));
				student.setAcademicYear(academicYear);
				
				Student oldStudentObj  = (Student) this.get(Student.class,"accountId="+transferStudent.getAccount().getId());
				
				UserImage studentImage =new UserImage();//imageId 
				if(!ObjectFunctions.isNullOrEmpty(oldStudentObj.getProfileImage()))
				{
					studentImage.copyFrom(oldStudentObj.getProfileImage());
					studentImage = (UserImage) this.save(studentImage);
					student.setProfileImage(studentImage);
				}
				studentImage = null;
				oldStudentObj = null;
				
				if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
					student.setClassSection(studyClass);
				}
				if (!ObjectFunctions.isNullOrEmpty(studyClass.getClassNameClassId())) {
					student.setClassNameClassId(studyClass.getClassNameClassId());
				}
				student.setCategoryId(catId);
				StringBuffer feeSettingIds = new StringBuffer("1,2");
				
				if(customer.isTransportModuleStatus()){
					student.setTransportMode(transportMode);
					if("T".equals(transportMode)){
						feeSettingIds.append(",3");
						RouteBoardingPoints routeBoardingPoints =(RouteBoardingPoints)this.get(RouteBoardingPoints.class, "id="+boardingPointId);
						student.setRouteBoardingPoints(routeBoardingPoints);
						student.setVehicleAcademicDetailsId(vehicleId);
						routeBoardingPoints=null;
					}
				}else{
					student.setTransportMode("O");
				}
				if(customer.isHostelModuleStatus()){
					student.setHostelMode(hostelMode);
					feeSettingIds.append(",4");
				}else{
					student.setHostelMode("D");
				}
				student.setOutSideSchoolDuty(outsideDuty);
				List<String> schoolTermIds = this.getAll("select id from schoolTerms where custId="+custId+" and academicYearId="+academicYear.getId()+" and feeSettingId in ("+feeSettingIds.toString()+")");
				if(ObjectFunctions.isNullOrEmpty(schoolTermIds))
					schoolTermIds.add("0");
				Fee fee =(Fee)this.get(Fee.class, "classId="+studyClass.getClassNameClassId().getId()+" and categoryId="+catId+" and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
				if(!ObjectFunctions.isNullOrEmpty(fee)){
					student.setFeeConfigured(Constants.YES_STRING);
				}
				else{
					student.setFeeConfigured(Constants.NO_STRING);
				}
				student.setFeePaidStatus(Constants.NO_STRING);
				student.setCommittedFee(committedFee);
				if(!StringFunctions.isNullOrEmpty(goals))
					student.setGoals(goals);
				if(!StringFunctions.isNullOrEmpty(strengths))
					student.setStrengths(strengths);
				if(!StringFunctions.isNullOrEmpty(interestsAndHobbies))
					student.setInterestsAndHobbies(interestsAndHobbies);
				if(!StringFunctions.isNullOrEmpty(responsibilities))
					student.setResponsibilities(responsibilities);
				if(!StringFunctions.isNullOrEmpty(achievements))
					student.setAchievements(achievements);
				if(!StringFunctions.isNullOrEmpty(remarks))
					student.setRemarks(remarks);
				student.setSchoolMess(schoolMess);
				student.setPtaStatus(Constants.NO_STRING);
				student.setBplStatus(Constants.NO_STRING);
				student.setRteStatus(Constants.NO_STRING);
				
				
				student = (Student)this.save(student);
				String response = createParentLoginAccount(customer,academicYear,student,false,masterCustomer,null);
				this.updateClassAndSectionCapacity(custId, academicYear.getId(),0, studyClass.getId());
				 
		} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	public int getClassStudentsCountByClassId(long classId,long custId) {
		return studentDao.getClassStudentsCountByClassId(classId,custId);
	}
	
	
	public OnlineApplicationDetailsMainVO createAdmissionsAppliedStudents(OnlineApplicationDetailsMainVO onlineApplicationDetailsMainVO){
		try{
			long applicationId,academicYearId,classId,categoryId,religionId,subCastId = 0;
	         String firstName,lastName,gender,streetName,city,postalCode,state= null;
	         OnlineApplicationDetails applicationDetails = null;
	         long custId = onlineApplicationDetailsMainVO.getIdentifier().getCustId();
	         CommonType religion =null;
	         Double annualIncome = null;
	         SubCastSettings subCast = null;
	         CastSettings cast = null;
	         AcademicYear academicYear = null;
	         String applicationNo=null;
	         Customer customer = (Customer)this.get(Customer.class, custId);
	         SMSServiceProviders smsServiceProvider=(SMSServiceProviders)  this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
	         ClassName className = null;
	         String status = null;
	         Address primaryAddress = null;
	         Address tempAddress = null;
	         Student student =null;
	         Date date = null;
	         Date dateOfBirth = null;
	         StudentVo studentVo = null;
	         AddressVO addressVO = null;
	      	List<StudentVo> studentVoList = new ArrayList<StudentVo>();
	         OnlineApplicationDetails onlineAppl = null;
		    if (custId>0) {
		    	if (!ObjectFunctions.isNullOrEmpty(onlineApplicationDetailsMainVO.getOnlineApplicationDetailsVOList())) {
					for(OnlineApplicationDetailsVO onlineApplicationDetailsVo:onlineApplicationDetailsMainVO.getOnlineApplicationDetailsVOList()){ 
						if (!ObjectFunctions.isNullOrEmpty(onlineApplicationDetailsVo)) {
				           	   	applicationId = onlineApplicationDetailsVo.getId();
				           	   	firstName = onlineApplicationDetailsVo.getFirstName();
				           	    if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetailsVo.getLastName()))
				           	    	lastName = onlineApplicationDetailsVo.getLastName();
				           	    else
				           	    	lastName = "";
				           	   	gender = onlineApplicationDetailsVo.getGender();
				           	    academicYearId = onlineApplicationDetailsVo.getAcademicYearId();
				           	   	classId = onlineApplicationDetailsVo.getClassId();
				           	   	categoryId = onlineApplicationDetailsVo.getFeeCategoryId();
				           	   	status = onlineApplicationDetailsVo.getStatus();
				           	   	if(applicationId > 0 && academicYearId > 0 && (StringFunctions.isNotNullOrEmpty(firstName) || StringFunctions.isNotNullOrEmpty(lastName))
				           	   			&& StringFunctions.isNotNullOrEmpty(gender) && classId > 0 && categoryId > 0){
				           	   			if("A".equals(status)){
				           	   				onlineAppl = (OnlineApplicationDetails)this.get(OnlineApplicationDetails.class,"custId="+custId+" and applicationId="+applicationId);
				           	   				log.debug("applicationId : "+applicationId);
				                   	   		if(ObjectFunctions.isNullOrEmpty(onlineAppl)){
					                	   			academicYear =(AcademicYear)this.get(AcademicYear.class,academicYearId);
					                    	   		applicationNo = getApplicationNumber(customer, academicYear);
					                   	   			User objUser= this.getUserByUserName(applicationNo);	
					    		    				if (ObjectFunctions.isNullOrEmpty(objUser)) 
					    		    				{
					    		    					 if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetailsVo.getReligionId()))
					    	            	   				religion = null;
					    	            	   			else{
					    	            	   				religionId = onlineApplicationDetailsVo.getReligionId();
					    	            	   				if(religionId == 0)
					    	            	   					religion = null;
					    	                    	   		else
					    	                    	   			religion = (CommonType)this.get(CommonType.class,religionId);
					    	            	   			}
					    		    					 if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetailsVo.getCasteId()))
					    	            	   				subCast = null;
					    	            	   			else{
					    	            	   				subCastId= onlineApplicationDetailsVo.getCasteId();
					    	                    	   		if(subCastId == 0)
					    	                    	   			subCast = null;
					    	                    	   		else
					    	                    	   			subCast = (SubCastSettings)this.get(SubCastSettings.class,subCastId);
					    	            	   			}
					    	                	   		if(!ObjectFunctions.isNullOrEmpty(subCast))
					    	                	   			cast = subCast.getCastSettings();
					    	                	   		else
					    	                	   			cast = null;
					    	                	   		className = (ClassName)this.get(ClassName.class,classId);
					    	                	   		/*In onlineApplicationDetails object we are not using these columns because we have already address object there changes done by Sunanda */
					    	                	   		addressVO = onlineApplicationDetailsVo.getAddressVO();
					    	                	   		streetName = StringFunctions.isNull(addressVO.getR_StreetName())? "":addressVO.getR_StreetName();
					    	                	   		city = StringFunctions.isNull(addressVO.getR_City())? "":addressVO.getR_City();
					    	                	   		postalCode = StringFunctions.isNull(addressVO.getR_Pincode())? "":addressVO.getR_Pincode();
					    	                	   		state = StringFunctions.isNull(addressVO.getR_StateShortName())? "":addressVO.getR_StateShortName();
					    	                	   		if(StringFunctions.isNotNullOrEmpty(streetName) || StringFunctions.isNotNullOrEmpty(city) || StringFunctions.isNotNullOrEmpty(state) || StringFunctions.isNotNullOrEmpty(postalCode))
					    	                	   			primaryAddress = new Address(streetName,city,state,postalCode);
					    	                	   		else
					    	                	   			primaryAddress = null;
					    	                	   		streetName= null;
					    	                	   		city = null;
					    	                	   		state = null;
					    	                	   		postalCode= null;
					    	                	   		streetName = StringFunctions.isNull(addressVO.getC_StreetName())? "":addressVO.getC_StreetName();
					    	                	   		city = StringFunctions.isNull(addressVO.getC_City())? "":addressVO.getC_City();
					    	                	   		state = StringFunctions.isNull(addressVO.getC_StateShortName())? "":addressVO.getC_StateShortName();
					    	                	   		postalCode = StringFunctions.isNull(addressVO.getC_Pincode())? "":addressVO.getC_Pincode();
					    	                	   		if(StringFunctions.isNotNullOrEmpty(streetName) || StringFunctions.isNotNullOrEmpty(city)
					    	                	   				|| StringFunctions.isNotNullOrEmpty(state) || StringFunctions.isNotNullOrEmpty(postalCode))
					    	                	   			tempAddress = new Address(streetName,city,state,postalCode);
					    	                	   		else
					    	                	   			tempAddress = null;
					    	                	   		addressVO=null;
					    	                	   		if(StringFunctions.isNull("AnnualIncome"))
					    	                	   			annualIncome = 0d;
					    	                	   		else{
					    	                	   			annualIncome = onlineApplicationDetailsVo.getAnnualIncome();
					    	                    	   		if(ObjectFunctions.isNullOrEmpty(annualIncome))
					    	                    	   			annualIncome = 0d;
					    	                    	   		else
					    	                    	   			annualIncome = 0d;
					    	                	   		}
					    	                	   		if(StringFunctions.isNullOrEmpty(onlineApplicationDetailsVo.getDateOfBirth()))
					    	                	   			dateOfBirth = null; 
					    	                	   		else{
					    	                	   			dateOfBirth = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(onlineApplicationDetailsVo.getDateOfBirth().replace("T", " ").replace("+05:30", ""));
					    	                	   		}
					    	                	   		date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(onlineApplicationDetailsVo.getApplicationDate().replace("T", " ").replace("+05:30", ""));
					    	                	   		applicationDetails = new OnlineApplicationDetails(firstName,lastName,gender,dateOfBirth,
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getBloodGroup())? "":onlineApplicationDetailsVo.getBloodGroup(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getLastClassStudied())? "":onlineApplicationDetailsVo.getLastClassStudied(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getLastSchoolName())? "":onlineApplicationDetailsVo.getLastSchoolName(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getFatherName())? "":onlineApplicationDetailsVo.getFatherName(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getFatherOccupation())? "":onlineApplicationDetailsVo.getFatherOccupation(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getFatherQualification())? "":onlineApplicationDetailsVo.getFatherQualification(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getFatherDesignation())? "":onlineApplicationDetailsVo.getFatherDesignation(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getMotherName())? "":onlineApplicationDetailsVo.getMotherName(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getMotherQualification())? "":onlineApplicationDetailsVo.getMotherQualification(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getParentEmailId())? "":onlineApplicationDetailsVo.getParentEmailId(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getPhoneNumber())? "":onlineApplicationDetailsVo.getPhoneNumber(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getMobileNumber())? "":onlineApplicationDetailsVo.getMobileNumber(),cast,subCast,annualIncome,
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.gettCNumber())? "":onlineApplicationDetailsVo.gettCNumber(),
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getTransportType())? "":onlineApplicationDetailsVo.getTransportType(),categoryId,custId,applicationNo,"S",className,
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getNationality())? "":onlineApplicationDetailsVo.getNationality(),religion,primaryAddress,tempAddress,
							    		    					StringFunctions.isNull(onlineApplicationDetailsVo.getCollectedDoc())? "":onlineApplicationDetailsVo.getCollectedDoc(),
							                	   				applicationId,date,academicYear,
							                	   				StringFunctions.isNull(onlineApplicationDetailsVo.getBplStatus())? "":onlineApplicationDetailsVo.getBplStatus(),
							                	   				StringFunctions.isNull(onlineApplicationDetailsVo.getRteStatus())? "":onlineApplicationDetailsVo.getRteStatus()
					    	                	   				);
					    	                	   		
					    		    					applicationDetails = (OnlineApplicationDetails)this.saveOrUpdateObject(applicationDetails);
					    		    					student = addStudentFromApplicationAndUpdateCount(applicationDetails,academicYear,applicationNo,applicationNo,customer,smsServiceProvider);
				   			    				}else{
				   			    					log.debug("Username already exist."+applicationNo);
				   			    				}
				           	   			}else{
				               	   			log.debug("Application already created : "+applicationId);
				               	   			log.debug("Application already created : "+applicationId);
				               	   			student = (Student)this.get(Student.class,"onlineApplicationDetailsId="+onlineAppl.getId()+" and custId="+custId);
				               	   		}
				               	   		if(!ObjectFunctions.isNullOrEmpty(student)){
				               	   			studentVo = new StudentVo();
				               	   			studentVo.setApplicationId(applicationId);
				               	   			studentVo.setAdmissionNumber(student.getAccount().getAdmissionNumber());
				               	   			studentVo.setRollNumber(student.getRollNumber() );
				               	   			studentVo.setId(student.getId() );
				               	   			studentVo.setAccountId(student.getAccount().getId());
				               	   			studentVo.setStudyClassId(student.getClassSectionId());
				               	   		    studentVoList.add(studentVo);
				               	   		   studentVo = null;
				       					}
				           	   		}else{
				           	   			log.debug("Application status mismatched.");
				           	   		}
				           	   	}else{
				           	   		log.debug("Mandatory field values are missed for "+applicationId);
				           	   	}
				           	 onlineApplicationDetailsVo = null;
						}
					}
					onlineApplicationDetailsMainVO = new OnlineApplicationDetailsMainVO();
					onlineApplicationDetailsMainVO.setStudentVoList(studentVoList);
					studentVoList=null;
					return onlineApplicationDetailsMainVO;
		    	}
		    }
	}catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		return null;
	}
	return onlineApplicationDetailsMainVO;
}
	
	public String getApplicationNumber(Customer customer,AcademicYear academicYear){
		try{
			if(StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName())){
				String applicationNumber = customer.getCustomerShortName().concat(String.valueOf(academicYear.getPastYear()));
				int applicationMaxId = getApplicationMaxId(customer.getId(), academicYear.getId(),applicationNumber.length() + 1);
				long applicationNo = applicationMaxId + 1;
				return applicationNumber.concat(String.valueOf(applicationNo));
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public Student addStudentFromApplicationAndUpdateCount(OnlineApplicationDetails onlineApplicationDetails,AcademicYear academicYear,String userName,String admissionNumber,Customer customer,SMSServiceProviders smsServiceProvider){
		Student student = addStudentFromApplication(onlineApplicationDetails,academicYear,userName,admissionNumber,customer,smsServiceProvider);
		if(!ObjectFunctions.isNullOrEmpty(student)){
			this.updateClassAndSectionCapacity(student.getCustId(),student.getAcademicYearId(),0, student.getClassSectionId());
		}
		return student;
	}
	
	public Student addStudentFromApplication(OnlineApplicationDetails onlineApplicationDetails,AcademicYear academicYear,String userName,String admissionNumber,Customer customer,SMSServiceProviders smsServiceProvider){
		try{
			StudyClass studyClass = (StudyClass)this.get(StudyClass.class, "academicYearId="+academicYear.getId()+" and custId="+academicYear.getCustId()+" and classNameClassId="+onlineApplicationDetails.getClassId().getId()+" and sectionCapacity > filledSeats");
			if(ObjectFunctions.isNullOrEmpty(studyClass)){
				studyClass = (StudyClass)this.get(StudyClass.class, "academicYearId="+academicYear.getId()+" and custId="+academicYear.getCustId()+" and classNameClassId="+onlineApplicationDetails.getClassId().getId());
			    if(!ObjectFunctions.isNullOrEmpty(studyClass)){
				 studyClass.setSectionCapacity(studyClass.getSectionCapacity()+1);
				 studyClass = (StudyClass) this.saveOrUpdateObject(studyClass);
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(studyClass)){
				SchoolCategory category=null;
				Person person = new Person();
				User account = new User();
				Student student = new Student();
				person.copyApplicationsData(onlineApplicationDetails);
				person.setDateOfJoining(new Date());
				if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getPrimaryAddress())){
					Address corrAddress = new Address();
					corrAddress.copyFrom(onlineApplicationDetails.getPrimaryAddress());
					account.setPrimaryAddress(corrAddress);
					corrAddress = null;
				}
				if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getTempararyAddress())){
					Address tAddress = new Address();
					tAddress.copyFrom(onlineApplicationDetails.getTempararyAddress());
					account.setTempararyAddress(tAddress);
					tAddress = null;
				}
				if("N".equalsIgnoreCase(customer.getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(customer.getAlphaNumericRollNumber()))
					student.setRollNumber(this.generateStudnetRollNumber(studyClass.getId(), academicYear.getCustId(), academicYear.getId()));
				else
					student.setRollNumber(onlineApplicationDetails.getRollNumber());
				account.setProfileImage(onlineApplicationDetails.getProfileImage());
				//account.getStudentParent().setParentAccountId(0);
				account.setCustId(academicYear.getCustId());
				account.setPerson(person);
				account.setUsername(userName.toLowerCase());
				account.setSecondaryUsername(userName.toLowerCase());
				account.setPassword(PasswordUtils.passwordEncoder(userName.toLowerCase(), null));
				account.setAdmissionNumber(admissionNumber);
				account.addNewRole(this.getRoleByName(Constants.SCHOOL_STUDENT));
				account = (User)this.saveOrUpdateObject(account);
				this.updateStudentUserName(account.getId(),PasswordUtils.passwordEncoder(String.valueOf(account.getId()),null));
				//academicYear.setAllotedsms(this.getAllottedSmsCount(academicYear.getCustId(),academicYear.getId()));
				student.setAcademicYear(academicYear);
				StringBuffer categoryQuery= new StringBuffer().append("custId=").append(academicYear.getCustId());
				if(onlineApplicationDetails.getCategoryId() == 0)
					categoryQuery.append(" and categoryName='General' ");
				else
					categoryQuery.append(" and id=").append(onlineApplicationDetails.getCategoryId());
				category=(SchoolCategory)this.get(SchoolCategory.class, categoryQuery.toString());
				categoryQuery=null;
				if(!ObjectFunctions.isNullOrEmpty(category))
					student.setCategoryId(category.getId());
				student.setAccount(account);
				student.setProfileImage(onlineApplicationDetails.getProfileImage());
				student.setCustId(academicYear.getCustId());
				student.setJoinedThroughAdmissions(true);
				
				student.setStatus(Constants.YES_STRING);
				//student.setActiveStudent(true);
				
				StringBuffer feeSettingIds = new StringBuffer("1,2");
				student.setCommittedFee(onlineApplicationDetails.getCommittedFee());
				student.setTransportMode(onlineApplicationDetails.getTransportMode());
				if("T".equalsIgnoreCase(onlineApplicationDetails.getTransportMode()))
					feeSettingIds.append(",3");
				if(!StringFunctions.isNullOrEmpty(onlineApplicationDetails.getHostelMode())){
					student.setHostelMode(onlineApplicationDetails.getHostelMode());
					feeSettingIds.append(",4");
				}else
				student.setHostelMode("D");
				
				student.setClassSection(studyClass);
				student.setClassNameClassId(studyClass.getClassNameClassId());
				student.setOnlineApplicationDetailsId(onlineApplicationDetails.getId());
				if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getBoardingPointId()) && !ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getVehicleAcademicDetailsId())){
					RouteBoardingPoints boardingPoint =(RouteBoardingPoints)this.get(RouteBoardingPoints.class, onlineApplicationDetails.getBoardingPointId());
					student.setRouteBoardingPoints(boardingPoint);
					student.setVehicleAcademicDetailsId(onlineApplicationDetails.getVehicleAcademicDetailsId());
					boardingPoint = null;
				}
				List<String> schoolTermIds = this.getAll("select id from schoolTerms where custId="+academicYear.getCustId()+" and academicYearId="+academicYear.getId()+" and feeSettingId in ("+feeSettingIds.toString()+")");
				if(ObjectFunctions.isNullOrEmpty(schoolTermIds))
					schoolTermIds.add("0");
				Fee fee =(Fee)this.get(Fee.class, "classId="+studyClass.getClassNameClassId().getId()+" and categoryId="+category.getId()+" and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
				if(!ObjectFunctions.isNullOrEmpty(fee))
					student.setFeeConfigured(Constants.YES_STRING);
				else
					student.setFeeConfigured(Constants.NO_STRING);
				student.setCommittedFee(onlineApplicationDetails.getCommittedFee());
				student.setGoals(onlineApplicationDetails.getGoals());
				student.setStrengths(onlineApplicationDetails.getStrengths());
				student.setInterestsAndHobbies(onlineApplicationDetails.getInterestsAndHobbies());
				student.setResponsibilities(onlineApplicationDetails.getResponsibilities());
				student.setAchievements(onlineApplicationDetails.getAchievements());
				student.setPtaStatus(Constants.NO_STRING);
				student.setBplStatus(onlineApplicationDetails.getBplStatus());
				student.setRteStatus(onlineApplicationDetails.getRteStatus());
				student = (Student)this.saveOrUpdateObject(student);
				AcademicYear academicYearsms=this.getCurrentAcademicYear("Y",academicYear.getCustId());
				Customer masterCustomer = null;
				if(customer.getId() == 173)
					masterCustomer = customer;
				else
					masterCustomer = this.getMasterCustomerById();
				String response = createParentLoginAccount(customer,academicYear,student,false,masterCustomer,null);
				//student.getAccount().getStudentParent().setId(Long.valueOf(response));
				//Sending notification for student addition
				this.sendNotificationForStudentUpdate(student);
				onlineApplicationDetails.setStatus("C");
				onlineApplicationDetails.setLastUpdatedDate(new Date());
				this.save(onlineApplicationDetails);
				person = null;
				account = null;
				studyClass = null;
				fee = null;
				academicYearsms = null;
				masterCustomer = null;
				return student;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public int getApplicationMaxId(long custId,long academicYearId,int length) {
		return studentDao.getApplicationMaxId(custId,academicYearId,length);
	}
	public void removeCommittedFeeEntries(StudentVo studentVo){
		ViewStudentFeePaymentDetails studentFeePaymentDetails = (ViewStudentFeePaymentDetails)this.get(ViewStudentFeePaymentDetails.class, "custId="+studentVo.getCustId()+" and academicYearId="+studentVo.getAcademicYearVo().getId()+" and studentId="+studentVo.getId()+" and paymentCommitFeeStatus='Y' ");
		if(!ObjectFunctions.isNullOrEmpty(studentFeePaymentDetails)){
			this.removeCommittedStudentFeePaidDetails(studentVo,studentFeePaymentDetails.getStudentPaymentId());
			this.removeCommittedStudentPayment(studentVo,studentFeePaymentDetails.getStudentPaymentId());
		}
	}
	public void removeCommittedStudentFeePaidDetails(StudentVo studentVo,long studentPaymentId){
		studentDao.removeCommittedStudentFeePaidDetails(studentVo, studentPaymentId);
	}
	public void removeCommittedStudentPayment(StudentVo studentVo,long studentPaymentId){
		studentDao.removeCommittedStudentPayment(studentVo, studentPaymentId);
	}
	public double getCommittedConfiguredFeeAmountCategoryWise(long custId,long academicYearId,long classId,long categoryId,String feeSettingIds){
		return studentDao.getCommittedConfiguredFeeAmountCategoryWise(custId,academicYearId,classId,categoryId,feeSettingIds);
	}
	public Map<String, String> validateCommittedFeeConstrains(long custId,long academicYearId,double committedFee,long classId,long classSectionId,long categoryId,String feeSettingIds){
		Map<String, String> msg = new HashMap<String,String>();
		Object[] congifuredFee = this.get("select id,IFNULL(SUM(feeAmount),0) as feeAmount from Fee where custId="+ custId + " and classId="+ classId + " and academicYearId="+ academicYearId + " and categoryId=" + categoryId);
		log.debug("congifuredFee");
		if (!ObjectFunctions.isNullOrEmpty(congifuredFee)) {
			if(committedFee !=0){
				List<FeeType> committedFeePriorityList = this.getAll(FeeType.class, "custId=" + custId + " and academicYearId=" + academicYearId+ " and committedFeeStatus='Y' ");
				log.debug("committedFeePriorityList");
				if (!ObjectFunctions.isNullOrEmpty(committedFeePriorityList)) {
					if (committedFee > Double.valueOf(congifuredFee[1].toString())) {
						log.debug("committedFeePriorityList msg 10");
						msg.put("10", "This selected student has more committed fee than class fee. Please check the fee settings.");
					}
				}else{
					log.debug("committedFeePriorityList msg 11");
					msg.put("11", "Committed fee settings are not configured. Please configure committed fee settings");
				}
				double committedConfiguredFee = this.getCommittedConfiguredFeeAmountCategoryWise(custId,academicYearId,classSectionId,categoryId,feeSettingIds);
				log.debug("committedConfiguredFee ");
				if(!ObjectFunctions.isNullOrEmpty(committedConfiguredFee)){
					log.debug("(Double.valueOf(congifuredFee[1].toString()) - committedFee) :"+(Double.valueOf(congifuredFee[1].toString()) - committedFee));
					double committedDiscount = (Double.valueOf(congifuredFee[1].toString()) - committedFee);
					if(committedConfiguredFee <  committedDiscount ){
						log.debug("committedConfiguredFee : msg 12");
						msg.put("12", "Max allowed commited fee should not be greater than "+committedConfiguredFee);
					}
				}
			}
		}
		return msg;
	}
	public void sendSmsForParentsAndStudent(String mobileNumber,String secondaryMobileNumber,String studMobileNumber,User user,User parentUserObject,Student studentObject,Customer customer,String updatedAdmissionStatus,String newPassword,SMSServiceProviders smsServiceProvider) throws URTUniversalException {
		try
		{
			Set<String> mobileNumbersSet = null;
			String response = null;
			boolean smsStatus = false;
			Messages message = new Messages();
			message.setStatus("M");
			message.setCreatedById(user.getId());
			message.setSenderName(user.getUserRoleDescription());
			message.setCustomer(customer);
			message.setAcademicYear(studentObject.getAcademicYear());
			message.setSmsSenderId(customer.getSender());
			String mobileNum = null;
			String secondMobileNum = null;
			Person pobjPerson = studentObject.getAccount().getPerson();
			if("R".equalsIgnoreCase(pobjPerson.getAddressType())){
				mobileNum = pobjPerson.getMobileNumber();
				secondMobileNum = pobjPerson.getSecondaryMobileNumber();
			}else{
				mobileNum = pobjPerson.getAnotherMobileNumber();
				secondMobileNum = pobjPerson.getAnotherSecondaryMobileNumber();
			}
			if(ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(studMobileNumber)) || !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(pobjPerson.getStudentMobile())) || "Yes".equalsIgnoreCase(updatedAdmissionStatus)){  
				mobileNumbersSet=new HashSet<String>();
				if((ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(studMobileNumber)) && !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(pobjPerson.getStudentMobile()))) || "Yes".equalsIgnoreCase(updatedAdmissionStatus) && customer.isCheckParentSmsService() && !customer.getStandardType().equalsIgnoreCase("P")){ // RaviTeja 26 JULY 2016 here pass standsrdType P means Pre School if customer select The standard Type Pre School not send to any student Related messages
					if("Yes".equalsIgnoreCase(updatedAdmissionStatus)){	
						if(!ObjectFunctions.isNullOrEmpty(studMobileNumber))
							mobileNumbersSet.add(studMobileNumber);
					}else{
						if(!ObjectFunctions.isNullOrEmpty(pobjPerson.getStudentMobile()))
						mobileNumbersSet.add(pobjPerson.getStudentMobile());
					}
					if(!user.getUsername().equalsIgnoreCase(studentObject.getAccount().getUsername())){
						if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
							smsStatus = this.sendSMSForStudent(studentObject.getAccount(),mobileNumbersSet,message,customer.getOrganization(),smsServiceProvider);
							if(smsStatus)
								message = this.saveMessageDetailsTracking(message,studentObject,null,null);
						}
					}
						
				}else if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(studMobileNumber)) && !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(pobjPerson.getStudentMobile()))){
					response = checkingMobileNumberhasUpdated(studMobileNumber,pobjPerson.getStudentMobile());
					smsStatus = this.sendSmsForUpdatedMobiles(response,customer,message,pobjPerson.getStudentMobile(),"Student",smsServiceProvider);
					if(smsStatus)
						message = this.saveMessageDetailsTracking(message,studentObject,null,null);
				}
			}
			if(ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNumber)) || !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNum)) || "Yes".equalsIgnoreCase(updatedAdmissionStatus)){
				mobileNumbersSet=new HashSet<String>();
				if((ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNumber)) && !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNum))) || "Yes".equalsIgnoreCase(updatedAdmissionStatus) && customer.isCheckParentSmsService()){
					if(!ObjectFunctions.isNullOrEmpty(mobileNum))
						mobileNumbersSet.add(mobileNum);
					if(!ObjectFunctions.isNullOrEmpty(secondMobileNum))
						mobileNumbersSet.add(secondMobileNum);
					if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
						smsStatus = sendSMSForParents(parentUserObject,customer,newPassword,studentObject.getAccount(),mobileNumbersSet,message,user.getUsername(),smsServiceProvider);
						if(smsStatus)
							message = this.saveMessageDetailsTracking(message,null,null,parentUserObject);
					}	
				}else if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNumber)) && !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNum))){ // If update Mobile number2
					if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNumber)) && !ObjectFunctions.isNullOrEmpty(mobileNum)){
						response = checkingMobileNumberhasUpdated(mobileNumber,mobileNum);
						smsStatus = this.sendSmsForUpdatedMobiles(response,customer,message,mobileNum,"Parent",smsServiceProvider);
						if(smsStatus)
							message = this.saveMessageDetailsTracking(message,null,null,parentUserObject);
					}
					if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(secondaryMobileNumber)) && !ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(secondMobileNum))){ // If update Mobile number2
						response = checkingMobileNumberhasUpdated(secondaryMobileNumber,secondMobileNum);
						smsStatus = this.sendSmsForUpdatedMobiles(response,customer,message,secondMobileNum,"Parent",smsServiceProvider);
						if(smsStatus)
							message = this.saveMessageDetailsTracking(message,null,null,parentUserObject);
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	//@Transactional
	public boolean sendSMSForParents(User parentUserObject,Customer customer,String password,User user,Set<String> mobileNumbersSet,Messages message,String oldUsername,SMSServiceProviders smsServiceProviders) throws URTUniversalException {
		boolean smsStatus = false;
		try
		{
			//Customer customer =(Customer) this.get(Customer.class,"id="+custId);
			boolean customerStatus = this.getResouceBundleURLConfiguraionPropertiesFileDetails(new File(SpringContextAware.getRealPath("/WEB-INF/classes/eschoolURL_en.properties")),customer.getId());
			StringBuffer smsContent = null;
			if(!ObjectFunctions.isNullOrEmpty(parentUserObject)){
				smsContent = new StringBuffer();
				if(customerStatus){
					smsContent.append("Dear "+customer.getCustomerShortName()+" Parent,");
					smsContent.append(" Download the EazySchool Android App from play store or https://goo.gl/Q5Odw7 Username:"+parentUserObject.getUsername()+" Password:"+password+", For any help call 9466227032 from Dharamveer ");//here password is random number
				}else{
					if(!ObjectFunctions.isNullOrEmpty(parentUserObject.getPerson().getFirstName()))
						smsContent.append("Dear "+parentUserObject.getPerson().getFirstName()+ ",");
					else
						smsContent.append("Dear Parent,");
					smsContent.append(" Your "+customer.getOrganization()+" account login details are below URL:www.eazyschool.in Username:"+parentUserObject.getUsername()+" Password:"+password+" from ");//here password is random number
				}
				message.setMessageDescription(smsContent.toString());
				smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProviders);
				smsContent = null;
			}
			if(!oldUsername.equalsIgnoreCase(user.getUsername())){
				smsStatus = sendStudentCredentiolsToParent(customer,parentUserObject.getPerson(),message,user,mobileNumbersSet,smsServiceProviders);
			}
			if(!customerStatus){
				smsContent = new StringBuffer("Dear Parent, Download the EazySchool Android App from play store or https://goo.gl/Q5Odw7  UserName :"+parentUserObject.getUsername()+" Password :"+password+" from ");//here password is random number
				if(!ObjectFunctions.isNullOrEmpty(smsContent)){
					message.setMessageDescription(smsContent.toString());
					smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProviders);
					smsContent = null;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return smsStatus;
	}
	public boolean sendStudentCredentiolsToParent(Customer customer,Person parentPersonObj,Messages message,User user,Set<String> mobileNumbersSet,SMSServiceProviders smsServiceProviders){
		boolean smsStatus = false;
		try {
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				if(!ObjectFunctions.isNullOrEmpty(user) && !customer.getStandardType().equalsIgnoreCase("P")){ // RaviTeja 26 JULY 2016 here pass standsrdType P means Pre School if customer select The standard Type P not send to any student Related messages 
					String ParentName = null;
					if(!ObjectFunctions.isNullOrEmpty(parentPersonObj.getFirstName()))
						ParentName = parentPersonObj.getFirstName();
					else
						ParentName = "Parent";
					StringBuffer smsContent = new StringBuffer("Dear "+ParentName+", Your child "+customer.getOrganization()+" account login details are below URL:www.eazyschool.in Username:" +user.getUsername().toLowerCase()+" Password:"+user.getUsername().toLowerCase()+" from ");
					message.setMessageDescription(smsContent.toString());
					smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProviders);
					smsContent = null;
				}
			}
			return smsStatus;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return false;
	}
	public String checkingMobileNumberhasUpdated(String oldMobileNumber,String newMobileNuber) throws URTUniversalException {
		String remainingDigits = null;
		String newRemainingDigits = null;
		try
		{
			String newFirstFourDigits = null;
			String firstFourDigits = null;
			if(oldMobileNumber.length()==14){
				firstFourDigits = oldMobileNumber.substring(0, 4);
				if("+91-".equalsIgnoreCase(firstFourDigits)){
					remainingDigits = oldMobileNumber.substring(4, 14);
				}
			}
			else{
				remainingDigits = oldMobileNumber;
			}
			if(newMobileNuber.length() == 14){
				newFirstFourDigits = newMobileNuber.substring(0, 4);
				if("+91-".equalsIgnoreCase(firstFourDigits)){
					newRemainingDigits = newMobileNuber.substring(4, 14);
				}
			}
			else{
				newRemainingDigits = newMobileNuber;
			}
	}catch(Exception ex)
	{
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
		return remainingDigits+"&"+newRemainingDigits;
 }
	public boolean sendSmsForUpdatedMobiles(String response,Customer customer,Messages message,String mobileNumber,String salutation,SMSServiceProviders smsServiceProvider) throws URTUniversalException {
		boolean smsStatus = false;
		try
		{
			String[] mobNumbers= null;
			StringBuffer smsContent = null;
			Set<String> mobileNumbersSet = new HashSet<String>();
			mobNumbers = response.split("&");
			//log.debug(mobNumbers[0].toString());
			//log.debug(mobNumbers[1].toString());
			if(!mobNumbers[0].toString().equalsIgnoreCase(mobNumbers[1].toString())){
				if("Y".equalsIgnoreCase(customer.getSendSmsUpdatedMobile())){
					smsContent = new StringBuffer();
					mobileNumbersSet.add(mobileNumber);
					if("Student".equalsIgnoreCase(salutation))
						smsContent.append("Dear "+salutation+", Your mobile number has updated in your school records. Going forward communication messages will send to "+mobileNumber+" number. From ");
					else
						smsContent.append("Dear "+salutation+", Your mobile number has updated in your child school records. Going forward communication messages will send to "+mobileNumber+" number. From ");
					message.setMessageDescription(smsContent.toString());
					smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProvider);
					smsContent = null;
					mobNumbers = null;
					response = null;
					mobileNumbersSet = null;
				}
			}		
		}catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return smsStatus;
 }
	
	public ViewStudentPersonAccountDetailsMainVO getStudentDetails(long academicYearId, long studyClassId, long accountId, String type) {
		try {
			ViewStudentPersonAccountDetailsMainVO viewStudentPersonAccountDetailsMainVO=null;
			ViewStudentPersonAccountDetailsVO studentDetailsVo=null;
			List<ViewStudentPersonAccountDetailsVO> viewStudentPersonAccountDetailsVOs = new ArrayList<ViewStudentPersonAccountDetailsVO>();
			AddressVO addressVo=null;
			if(academicYearId > 0){
				List<ViewStudentPersonAccountDetails> studentsList =null;
				if(StringFunctions.isNullOrEmpty(type)){
					studentsList =  this.getAll(ViewStudentPersonAccountDetails.class, " academicYearId = "+ academicYearId + ((studyClassId > 0)? " AND classSectionId = "+ studyClassId: ""));
				}else{
					if(accountId > 0 && !type.equalsIgnoreCase("S"))
						// P - Parent & C - Student
						studentsList =  this.getAllHqlQuery("FROM ViewStudentPersonAccountDetails vs WHERE vs.academicYearId = "+ academicYearId + (type.equalsIgnoreCase("P")? " AND vs.parentId = "+ accountId: " AND vs.accountId = "+ accountId));
					else if(accountId > 0 && type.equalsIgnoreCase("S"))
						studentsList =  this.getAllHqlQuery("FROM ViewStudentPersonAccountDetails vs WHERE vs.academicYearId = "+ academicYearId + " AND vs.classSectionId IN(SELECT ct.studyClass FROM ClassTeacher ct WHERE ct.academicYear = "+ academicYearId +" AND ct.staff in (select s.id from Staff s where s.account= "+ accountId +"))");
					else{
						return null;
					}
				}
				if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
					for(ViewStudentPersonAccountDetails student : studentsList) {
						studentDetailsVo=new ViewStudentPersonAccountDetailsVO(); 
						studentDetailsVo.setStudentId(student.getStudentId());
						studentDetailsVo.setAccountId(student.getAccountId()); 
						studentDetailsVo.setFirstName(student.getFirstName());
						studentDetailsVo.setLastName(ObjectFunctions.isNullOrEmpty(student.getLastName())? "": student.getLastName());
						studentDetailsVo.setFatherName(ObjectFunctions.isNullOrEmpty(student.getFatherName())? "": student.getFatherName());
						studentDetailsVo.setMotherName(ObjectFunctions.isNullOrEmpty(student.getMotherName())? "": student.getMotherName());
						studentDetailsVo.setGender(ObjectFunctions.isNullOrEmpty(student.getGender())? "": student.getGender());
						studentDetailsVo.setClassNameClassId(student.getClassNameClassId());
						studentDetailsVo.setClassSectionId(student.getClassSectionId());
						studentDetailsVo.setAdmissionNumber(ObjectFunctions.isNullOrEmpty(student.getAdmissionNumber())? "": student.getAdmissionNumber());
						studentDetailsVo.setRollNumber(ObjectFunctions.isNullOrEmpty(student.getRollNumber())? "": student.getRollNumber());
						studentDetailsVo.setCategoryId(student.getCategoryId());
						// Address
						addressVo = new AddressVO();	
						addressVo.setAddressLine1(ObjectFunctions.isNullOrEmpty(student.getAddressLine1())? "": student.getAddressLine1());
						addressVo.setAddressLine2(ObjectFunctions.isNullOrEmpty(student.getAddressLine2())? "": student.getAddressLine2());
						addressVo.setStreetName(ObjectFunctions.isNullOrEmpty(student.getStreetName())? "": student.getStreetName());
						addressVo.setCity(ObjectFunctions.isNullOrEmpty(student.getCity())? "": student.getCity());
						addressVo.setStateName(ObjectFunctions.isNullOrEmpty(student.getStateName())? "": student.getStateName());
						addressVo.setPostalCode(ObjectFunctions.isNullOrEmpty(student.getPostalCode())? "": student.getPostalCode());
						studentDetailsVo.setAddressVO(addressVo);
						addressVo = null;						
						studentDetailsVo.setPhoneNumber(ObjectFunctions.isNullOrEmpty(student.getPhoneNumber())? "": student.getPhoneNumber());
						studentDetailsVo.setMobileNumber(ObjectFunctions.isNullOrEmpty(student.getMobileNumber())? "": student.getMobileNumber());
						studentDetailsVo.setParentEmail(ObjectFunctions.isNullOrEmpty(student.getParentEmail())? "": student.getParentEmail());
						studentDetailsVo.setClassSectionName(ObjectFunctions.isNullOrEmpty(student.getClassAndSection())? "": student.getClassAndSection());
						studentDetailsVo.setBloodGroup(ObjectFunctions.isNullOrEmpty(student.getBloodGroup())? "": student.getBloodGroup());
						studentDetailsVo.setDateOfBirth(ObjectFunctions.isNullOrEmpty(student.getDateOfBirth())? "": DateFunctions.convertDateToString(student.getDateOfBirth()));
						studentDetailsVo.setCommittedFee(student.getCommittedFee());
						studentDetailsVo.setStatus(student.getStatus());
						studentDetailsVo.setDescription(ObjectFunctions.isNullOrEmpty(student.getDescription())? "": student.getDescription());
						studentDetailsVo.setBoardingPointId(student.getBoardingPointId());
						studentDetailsVo.setHostelMode(ObjectFunctions.isNullOrEmpty(student.getHostelMode())? "": student.getHostelMode());
						studentDetailsVo.setTransportMode(ObjectFunctions.isNullOrEmpty(student.getTransportMode())? "": student.getTransportMode());
						studentDetailsVo.setAadharNumber(ObjectFunctions.isNullOrEmpty(student.getAadharNumber())? "": student.getAadharNumber());
						studentDetailsVo.setReligionId(student.getReligionId());
						studentDetailsVo.setSubCastId(student.getSubCastId());
						studentDetailsVo.setNationality(ObjectFunctions.isNullOrEmpty(student.getNationality())? "": student.getNationality());
						studentDetailsVo.setRegisterNumber(ObjectFunctions.isNullOrEmpty(student.getRegisterNumber())? "": student.getRegisterNumber());
						studentDetailsVo.setMotherToung(ObjectFunctions.isNullOrEmpty(student.getMotherToung())? "": student.getMotherToung());
						studentDetailsVo.setClassJoined(ObjectFunctions.isNullOrEmpty(student.getClassJoined())? "": student.getClassJoined());
						studentDetailsVo.setRelievingDate(ObjectFunctions.isNullOrEmpty(student.getRelievingDate())? "": DateFunctions.convertDateToString(student.getRelievingDate()));
						studentDetailsVo.setPhId(ObjectFunctions.isNullOrEmpty(student.isPhId())? false: student.isPhId());
						//studentDetailsVo.setStudentImageId(ObjectFunctions.isNullOrEmpty(student.getImageId())? null: student.getImageId());
						studentDetailsVo.setImageUrl(ObjectFunctions.isNullOrEmpty(student.getImagePath())? null: student.getImagePath());
						studentDetailsVo.setStudentMobile(ObjectFunctions.isNullOrEmpty(student.getStudentMobile())? "": student.getStudentMobile());
						studentDetailsVo.setStsNumber(ObjectFunctions.isNullOrEmpty(student.getStsNumber())? "": student.getStsNumber());
						viewStudentPersonAccountDetailsVOs.add(studentDetailsVo);
					}
					viewStudentPersonAccountDetailsMainVO = new ViewStudentPersonAccountDetailsMainVO();
					viewStudentPersonAccountDetailsMainVO.setViewStudentPersonAccountDetailsVOs(viewStudentPersonAccountDetailsVOs);
					return viewStudentPersonAccountDetailsMainVO;
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public StudentMarksMainVO getStudentMarks(long academicYearId,long studyClassId){
		StudentMarksVO studentMarksVO=null;
		StudentMarksMainVO studentMarksMainVO = null;
		List<StudentMarksVO> studentMarksVOList = new ArrayList<StudentMarksVO>();
		try { 
			if(academicYearId > 0 && studyClassId > 0){
				List<Object[]> studentMarks = this.getAll("SELECT (sm.obtainedMarks+sm.moderationMarks) AS subjectMarks, sm.present, sm.examScheduleId, sm.studId, es.classSubjectId FROM studentMarks sm LEFT JOIN examSchedules es ON(es.id = sm.examScheduleId) Left join student s ON(sm.studId = s.id) WHERE s.classSectionId = "+ studyClassId +" AND s.academicYearId = "+ academicYearId +" AND s.status = 'Y'");
				if (!ObjectFunctions.isNullOrEmpty(studentMarks)) {
					Map<String, Double> subjHighest = new HashMap<String, Double>();
					List<Object[]> HighestMarks = this.getAll("SELECT MAX(sm.obtainedMarks+sm.moderationMarks) AS subjectHighestMarks, sm.examScheduleId, es.classSubjectId FROM studentMarks sm LEFT JOIN examSchedules es ON(es.id = sm.examScheduleId) Left join student s ON(sm.studId = s.id) WHERE s.classSectionId="+ studyClassId +" AND s.academicYearId="+ academicYearId +" AND s.status='Y' GROUP BY es.classSubjectId, sm.examScheduleId");
					for(Object[] marks : HighestMarks){
						if(!ObjectFunctions.isNullOrEmpty(marks)){
							subjHighest.put(marks[1].toString()+"_"+marks[2].toString(), Double.valueOf(marks[0].toString()));
						}
					}
					HighestMarks = null;					
					for(Object[] marks : studentMarks) {
						  studentMarksVO = new StudentMarksVO();
						  studentMarksVO.setStudentId(Long.valueOf(marks[3].toString()));
						  studentMarksVO.setScheduleId(Long.valueOf(marks[2].toString()));
						  studentMarksVO.setSubjectMarks(Double.valueOf(marks[0].toString()));
						  studentMarksVO.setIsPresent(marks[1].toString());
						if(!ObjectFunctions.isNullOrEmpty(subjHighest.get(marks[2].toString()+"_"+marks[4].toString())))
							studentMarksVO.setSubjectHighestMarks(subjHighest.get(marks[2].toString()+"_"+marks[4].toString()));
						else
							studentMarksVO.setSubjectHighestMarks(0);
						studentMarksVOList.add(studentMarksVO);
						studentMarksVO = null;
					}
					studentMarksMainVO = new StudentMarksMainVO();
					studentMarksMainVO.setStudentMarksVOList(studentMarksVOList);
					return studentMarksMainVO;
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return null;
		}
	}
	public int addOrUpdateStudentAttendance(ClassAttendanceMainVO classAttendanceMainVO){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addOrUpdateStudentAttendance VO' method");
		}
		try {
			if (ObjectFunctions.isNullOrEmpty(classAttendanceMainVO)) return 1;
			long custId = classAttendanceMainVO.getIdentifier().getCustId();
			int availableSmsCount=this.getAvailableSmsCount(custId,classAttendanceMainVO.getIdentifier().getAcademicYearId());
			Customer customer = this.getCustomerByCustId(custId);
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			User loggedUser = (User) this.get(User.class, classAttendanceMainVO.getIdentifier().getAccountId());
			AcademicYear academicYear = (AcademicYear)dao.get(AcademicYear.class, classAttendanceMainVO.getIdentifier().getAcademicYearId());
			
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
				if(!academicYear.getManageAttendanceBy().equalsIgnoreCase("D")) return 3;
				if("O".equalsIgnoreCase(academicYear.getCaptureAttendanceBy())) return oneSessionAttendance(classAttendanceMainVO, academicYear, customer, loggedUser, smsServiceProvider, availableSmsCount);
				else return twoSessionAttendance(classAttendanceMainVO, academicYear, customer, loggedUser, smsServiceProvider, availableSmsCount);
			}
			if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(classAttendanceMainVO.getSMS()) && availableSmsCount <= 0) return 4; 
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 1;
		}
	}
	
	private int oneSessionAttendance(ClassAttendanceMainVO classAttendanceMainVO, AcademicYear academicYear, Customer customer, User loggedUser, SMSServiceProviders smsServiceProvider, int availableSmsCount){
		try{
			for(ClassAttendanceVO classAttendanceVO : classAttendanceMainVO.getClassAttendance()){
				String attendanceDate = classAttendanceVO.getAttendanceDate();
				StaffDailyAttendanceSubmitTrack staffDailyAttendanceSubmitTrack = null;
				long classId = classAttendanceVO.getClassId();
				Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, attendanceDate);
				if (ObjectFunctions.isNullOrEmpty(aDate)) aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,attendanceDate));
				// HERE checking given date is holiday or not
				if(isAttendanceDateIsHoliday(aDate, classAttendanceVO, academicYear, customer)) return 2;
				
				StudentDailyAttendance attendance = null;
				Date todayDate = new Date();
				Messages objMsg = null;
				Set<String> mobileNumbersSet = null;
				if (!ObjectFunctions.isNullOrEmpty(classAttendanceVO.getStudentDailyAttendance())) 
					for (StudentDailyAttendanceVO dailyAttendanceVO : classAttendanceVO.getStudentDailyAttendance()) 
						if (!ObjectFunctions.isNullOrEmpty(dailyAttendanceVO)) {
							boolean sendEmail = false;
							String dbAttendance = null;
							long studentId = dailyAttendanceVO.getStudentId();
							String attendanceStatus = dailyAttendanceVO.getMorningSession();
							Student student = (Student) this.get(Student.class," custId = " +customer.getId()+" AND academicYearId = "+academicYear.getId()+" AND id="+studentId);
							if (!ObjectFunctions.isNullOrEmpty(student)) {
								User studentUser = student.getAccount();
								//Preparing SMS object here
								if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(classAttendanceMainVO.getSMS()) && availableSmsCount > 0){
									mobileNumbersSet = new HashSet<String>();
									mobileNumbersSet.addAll(this.addMobileNumbersBasedOnAddressType(customer.getMobileType(), StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getSecondaryMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherSecondaryMobileNumber()),studentUser.getPerson().getAddressType()));
									objMsg = new Messages();
									objMsg.setCreatedById(classAttendanceMainVO.getIdentifier().getAccountId());
									objMsg.setCreatedDate(new Date());
									objMsg.setLastAccessDate(new Date());
									objMsg.setLastUpdatedDate(new Date());
									objMsg.setStatus("A");
									objMsg.setSenderName(loggedUser.getUserRoleDescription());
									objMsg.setAcademicYear(student.getAcademicYear());
									objMsg.setSmsSenderId(customer.getSender());
									if (!ObjectFunctions.isNullOrEmpty(customer)) objMsg.setCustomer(customer);
								}
								
								attendance = this.getStudentDailyAttendance(studentId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate), customer.getId(), academicYear.getId());
								if("A".equalsIgnoreCase(attendanceStatus) && ObjectFunctions.isNullOrEmpty(attendance)){
									attendance = new StudentDailyAttendance();
									attendance.setCustId(customer.getId());
									attendance.setAcademicYearId(academicYear.getId());
									attendance.setCreatedById(classAttendanceMainVO.getIdentifier().getAccountId());
									attendance.setCreatedDate(new Date());
									attendance.setAttendanceDate(aDate);
									attendance.setLastUpdatedDate(new Date());
									attendance.setLastAccessDate(new Date());
									attendance.setStudentId(studentId);
									attendance.setPresent(Boolean.FALSE);
									this.save(attendance);
									if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg)){
										sendEmail = true;
										if("S".equalsIgnoreCase(customer.getOrganizationLevel())) objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");
										else objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent. Please contact college if your child has already started to college. ");	
										objMsg.setPurposeType("regd: absent of the "+ studentUser.getFullPersonName());
									}
								}else if(!ObjectFunctions.isNullOrEmpty(attendance)){
									dbAttendance = attendance.isPresent()? "Y": "N";
								}
								//Sending Mobile Notification for Student Attendance
								if(("A".equalsIgnoreCase(attendanceStatus) || "P".equalsIgnoreCase(attendanceStatus)) && !ObjectFunctions.isNullOrEmpty(attendance) && DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)))
								    this.sendNotificationForAttendance(studentUser, attendanceStatus, studentId, classId);
								
								if("P".equalsIgnoreCase(attendanceStatus) && !ObjectFunctions.isNullOrEmpty(attendance)){
									this.remove(StudentDailyAttendance.class, attendance.getId());
									if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg)) {
										sendEmail = true;
										objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+". Please ignore the absent message which you received previously. ");
										objMsg.setPurposeType("regd: update from absent to present of the "+ studentUser.getFullPersonName());
									}
								}
								
								if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)) {
									objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);
									this.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
								}
								mobileNumbersSet = null;
								//objMsg = null;
								//attendance = null;
								// Sending Email here..
								if(sendEmail && !StringFunctions.isNullOrEmpty(classAttendanceMainVO.getSMS()) && customer.isCheckAttendanceEmailService() && !ObjectFunctions.isNullOrEmpty(studentUser.getPerson().getParentEmail()) &&
										DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)))
									sendAttendanceEmail(loggedUser,customer,aDate ,studentUser.getFullPersonName() ,studentUser.getPerson().getParentEmail() , customer.getOrganizationLevel(), academicYear.getCaptureAttendanceBy(), attendanceStatus, 
											dbAttendance, null, null, objMsg, attendance);
								
							}
						}
				String clause = " custId=" + customer.getId()+ " and classSectionId = " +  classAttendanceVO.getClassId() + " and attendanceDate = '" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate)+ "'";
				
				   staffDailyAttendanceSubmitTrack = (StaffDailyAttendanceSubmitTrack) this.get(StaffDailyAttendanceSubmitTrack.class,clause);
					if(ObjectFunctions.isNullOrEmpty(staffDailyAttendanceSubmitTrack)){
						staffDailyAttendanceSubmitTrack = new StaffDailyAttendanceSubmitTrack();
						staffDailyAttendanceSubmitTrack.setCustId(customer.getId());
						staffDailyAttendanceSubmitTrack.setAcademicYearId(academicYear.getId());
						staffDailyAttendanceSubmitTrack.setCreatedById(loggedUser.getId());
						staffDailyAttendanceSubmitTrack.setCreatedDate(new Date());
						staffDailyAttendanceSubmitTrack.setAttendanceDate(aDate);
						staffDailyAttendanceSubmitTrack.setLastUpdatedDate(new Date());
						staffDailyAttendanceSubmitTrack.setLastAccessDate(new Date());
						staffDailyAttendanceSubmitTrack.setStaffAccountId(loggedUser.getId());
						staffDailyAttendanceSubmitTrack.setClassSectionId(classAttendanceVO.getClassId());
					}else{
						staffDailyAttendanceSubmitTrack.setLastUpdatedDate(new Date());
						staffDailyAttendanceSubmitTrack.setLastAccessDate(new Date());
						staffDailyAttendanceSubmitTrack.setStaffAccountId(loggedUser.getId());
					}
						this.save(staffDailyAttendanceSubmitTrack);
			}
		}catch(Exception e) { e.printStackTrace(); return 1; }
		return 0;
	}
	
	private boolean isAttendanceDateIsHoliday(Date aDate, ClassAttendanceVO classAttendanceVO, AcademicYear academicYear, Customer customer){
		try{
			int monthNum;
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
			String year = simpleDateformat.format(aDate);
			long classId = classAttendanceVO.getClassId();
			monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(aDate));
			if (!ObjectFunctions.isNullOrEmpty(aDate)) {
				List<SchoolHolidays> holidaysList=null;  //below classId used to get the holidays for class wise. if school have class wise holidays in school settings tab
				if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()) && classId > 0){
					Object[] classNameClassIds= dao.get("SELECT classId, className FROM vw_classSectionDetails WHERE custId="+customer.getId()+ " AND academicYearId="+academicYear.getId()+" AND classSectionId="+classId);
		 			if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ) 
		 				holidaysList = this.getSchoolHolidaysListByDatesAndCustId(customer.getId(), academicYear.getId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate), null, null,classNameClassIds[0].toString(), null, null, monthNum, "holidayDateEqual", year);
		 		  }else holidaysList = this.getSchoolHolidaysListByDatesAndCustId(customer.getId(), academicYear.getId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate), null, null, null, null, null, monthNum, "holidayDateEqual", year);
				if (!ObjectFunctions.isNullOrEmpty(holidaysList)) return true;//msg.put("1", "Today Is Holiday");
			}
		}catch(Exception e){ e.printStackTrace(); }
		return false;
	}
	
	private int twoSessionAttendance(ClassAttendanceMainVO classAttendanceMainVO, AcademicYear academicYear, Customer customer, User loggedUser, SMSServiceProviders smsServiceProvider, int availableSmsCount){
		try{
			for(ClassAttendanceVO classAttendanceVO : classAttendanceMainVO.getClassAttendance()){
				String attendanceDate = classAttendanceVO.getAttendanceDate();
				StaffDailyAttendanceSubmitTrack staffDailyAttendanceSubmitTrack = null;
				Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, attendanceDate);
				if (ObjectFunctions.isNullOrEmpty(aDate)) aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,attendanceDate));
				// HERE checking given date is holiday or not
				if(isAttendanceDateIsHoliday(aDate, classAttendanceVO, academicYear, customer)) return 2;
				
				StudentDailyAttendance attendance = null;
				Date todayDate = new Date();
				Messages objMsg = null;
				Set<String> mobileNumbersSet = null;
				if (!ObjectFunctions.isNullOrEmpty(classAttendanceVO.getStudentDailyAttendance())) 
					for (StudentDailyAttendanceVO dailyAttendanceVO : classAttendanceVO.getStudentDailyAttendance()) 
						if (!ObjectFunctions.isNullOrEmpty(dailyAttendanceVO)) {
							boolean sendEmail = false;
							long studentId = dailyAttendanceVO.getStudentId();
							String morningStatus = dailyAttendanceVO.getMorningSession();
							String noonStatus = dailyAttendanceVO.getAfternoonSession();
							String dbMorningStatus = null;
							String dbNoonStatus = null;
							Student student = (Student) this.get(Student.class," custId = " +customer.getId()+" AND academicYearId = "+academicYear.getId()+" AND Id="+studentId);
							if (!ObjectFunctions.isNullOrEmpty(student)) {
								User studentUser = student.getAccount();
								//Preparing SMS object here
								if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(classAttendanceMainVO.getSMS()) && availableSmsCount > 0){
									mobileNumbersSet = new HashSet<String>();
									mobileNumbersSet.addAll(this.addMobileNumbersBasedOnAddressType(customer.getMobileType(), StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getSecondaryMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherSecondaryMobileNumber()),studentUser.getPerson().getAddressType()));
									objMsg = new Messages();
									objMsg.setCreatedById(classAttendanceMainVO.getIdentifier().getAccountId());
									objMsg.setCreatedDate(new Date());
									objMsg.setLastAccessDate(new Date());
									objMsg.setLastUpdatedDate(new Date());
									objMsg.setStatus("A");
									objMsg.setSenderName(loggedUser.getUserRoleDescription());
									objMsg.setAcademicYear(student.getAcademicYear());
									objMsg.setSmsSenderId(customer.getSender());
									if (!ObjectFunctions.isNullOrEmpty(customer)) objMsg.setCustomer(customer);
								}
								attendance = this.getStudentDailyAttendance(studentId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate),customer.getId(),academicYear.getId());
								if(morningStatus.equalsIgnoreCase("A") || noonStatus.equalsIgnoreCase("A")){
									if(ObjectFunctions.isNullOrEmpty(attendance)){
										attendance = new StudentDailyAttendance();
									}else{
										dbMorningStatus = attendance.isPresent()? "Y": "N";
										dbNoonStatus = attendance.isAfternoonSession()? "Y": "N";
									}
									attendance.setCustId(customer.getId());
									attendance.setAcademicYearId(academicYear.getId());
									attendance.setCreatedById(classAttendanceMainVO.getIdentifier().getAccountId());
									attendance.setCreatedDate(new Date());
									attendance.setAttendanceDate(aDate);
									attendance.setLastUpdatedDate(new Date());
									attendance.setLastAccessDate(new Date());
									attendance.setStudentId(studentId);
									attendance.setPresent(!morningStatus.equalsIgnoreCase("A"));
									attendance.setAfternoonSession(!noonStatus.equalsIgnoreCase("A"));
									attendance = (StudentDailyAttendance) this.merge(attendance);
									if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg))
										if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet) && mobileNumbersSet.size()!=0){
											objMsg.setPurposeType("regd: absent of the "+ studentUser.getFullPersonName());
											if(StringFunctions.isNullOrEmpty(dbMorningStatus) && StringFunctions.isNullOrEmpty(dbNoonStatus)){
												if(morningStatus.equalsIgnoreCase("A") && !noonStatus.equalsIgnoreCase("A"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Morning session.");
												else if(!morningStatus.equalsIgnoreCase("A") && noonStatus.equalsIgnoreCase("A"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Afternoon session.");
												else if(morningStatus.equalsIgnoreCase("A") && noonStatus.equalsIgnoreCase("A"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");
												if(!"S".equalsIgnoreCase(customer.getOrganizationLevel())){
													String msg = objMsg.getMessageDescription();
													msg = msg+"Please contact college if your child has already started to college.";
													objMsg.setMessageDescription(msg);
												}
											}else if(StringFunctions.isNotNullOrEmpty(dbMorningStatus) && StringFunctions.isNotNullOrEmpty(dbNoonStatus)){
												if(morningStatus.equalsIgnoreCase("A") && dbMorningStatus.equalsIgnoreCase("Y") && noonStatus.equalsIgnoreCase("P") && dbNoonStatus.equalsIgnoreCase("Y"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Morning session.");
												else if(morningStatus.equalsIgnoreCase("P") && dbMorningStatus.equalsIgnoreCase("Y") && noonStatus.equalsIgnoreCase("A") && dbNoonStatus.equalsIgnoreCase("Y"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent for Afternoon session.");
												else if(morningStatus.equalsIgnoreCase("P") && dbMorningStatus.equalsIgnoreCase("N") && noonStatus.equalsIgnoreCase("P") && dbNoonStatus.equalsIgnoreCase("Y"))
													objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to school for Morning session. Please ignore the absent message which you received previously.");
												else if(morningStatus.equalsIgnoreCase("P") && dbMorningStatus.equalsIgnoreCase("N") && noonStatus.equalsIgnoreCase("A") && dbNoonStatus.equalsIgnoreCase("N"))
													objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to school for Morning session. Please ignore the absent message which you received previously.");
												else if(morningStatus.equalsIgnoreCase("P") && dbMorningStatus.equalsIgnoreCase("Y") && noonStatus.equalsIgnoreCase("P") && dbNoonStatus.equalsIgnoreCase("N"))
													objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to school for Afternoon session. Please ignore the absent message which you received previously.");
												else if(morningStatus.equalsIgnoreCase("A") && dbMorningStatus.equalsIgnoreCase("N") && noonStatus.equalsIgnoreCase("P") && dbNoonStatus.equalsIgnoreCase("N"))
													objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to school for Afternoon session. Please ignore the absent message which you received previously.");
												else if(morningStatus.equalsIgnoreCase("A") && dbMorningStatus.equalsIgnoreCase("Y") && noonStatus.equalsIgnoreCase("A") && dbNoonStatus.equalsIgnoreCase("Y"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");
												else if(morningStatus.equalsIgnoreCase("A") && dbMorningStatus.equalsIgnoreCase("N") && noonStatus.equalsIgnoreCase("A") && dbNoonStatus.equalsIgnoreCase("Y"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");	
												else if(morningStatus.equalsIgnoreCase("A") && dbMorningStatus.equalsIgnoreCase("Y") && noonStatus.equalsIgnoreCase("A") && dbNoonStatus.equalsIgnoreCase("N"))
													objMsg.setMessageDescription("Dear Parent, Today your child "+studentUser.getFullPersonName()+" is absent.");
											}
											
											if(!ObjectFunctions.isNullOrEmpty(objMsg))if(!ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
												if(!"S".equalsIgnoreCase(customer.getOrganizationLevel())){
													String msg = objMsg.getMessageDescription().replace("school", "college");
													objMsg.setMessageDescription(msg);
												}
											}
											sendEmail = true;
										}
								}
								if(morningStatus.equalsIgnoreCase("P") && noonStatus.equalsIgnoreCase("P") && !ObjectFunctions.isNullOrEmpty(attendance)){
									this.remove(StudentDailyAttendance.class,attendance.getId());
									if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)) && !ObjectFunctions.isNullOrEmpty(objMsg)){
										objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" has just reached to "+customer.getSchoolOrCollege()+". Please ignore the absent message which you received previously. ");
										objMsg.setPurposeType("regd: update from absent to present of the "+ studentUser.getFullPersonName());
									}
									sendEmail = true;
								}
								if( !ObjectFunctions.isNullOrEmpty(attendance) && DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate))){
									this.sendNotificationForAttendance(studentUser, morningStatus, noonStatus, studentId, classAttendanceVO.getClassId());
								}
								
								if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)) {
									objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);
									this.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
								}
																// Sending Email here..
								if(sendEmail && !StringFunctions.isNullOrEmpty(classAttendanceMainVO.getSMS()) && customer.isCheckAttendanceEmailService() && !ObjectFunctions.isNullOrEmpty(studentUser.getPerson().getParentEmail()) &&
										DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)))
									sendAttendanceEmail(loggedUser,customer,aDate ,studentUser.getFullPersonName() ,studentUser.getPerson().getParentEmail() , customer.getOrganizationLevel(), academicYear.getCaptureAttendanceBy(), morningStatus, 
											dbMorningStatus, noonStatus, dbNoonStatus, objMsg, attendance);
								
								mobileNumbersSet = null;
								objMsg = null;
								attendance = null;
							}
						}
				
				 String clause = " custId=" + customer.getId()+ " and classSectionId = " +  classAttendanceVO.getClassId() + " and attendanceDate = '" + DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate)+ "'";
					
				   staffDailyAttendanceSubmitTrack = (StaffDailyAttendanceSubmitTrack) this.get(StaffDailyAttendanceSubmitTrack.class,clause);
					if(ObjectFunctions.isNullOrEmpty(staffDailyAttendanceSubmitTrack)){
						staffDailyAttendanceSubmitTrack = new StaffDailyAttendanceSubmitTrack();
						staffDailyAttendanceSubmitTrack.setCustId(customer.getId());
						staffDailyAttendanceSubmitTrack.setAcademicYearId(academicYear.getId());
						staffDailyAttendanceSubmitTrack.setCreatedById(loggedUser.getId());
						staffDailyAttendanceSubmitTrack.setCreatedDate(new Date());
						staffDailyAttendanceSubmitTrack.setAttendanceDate(aDate);
						staffDailyAttendanceSubmitTrack.setLastUpdatedDate(new Date());
						staffDailyAttendanceSubmitTrack.setLastAccessDate(new Date());
						staffDailyAttendanceSubmitTrack.setStaffAccountId(loggedUser.getId());
						staffDailyAttendanceSubmitTrack.setClassSectionId(classAttendanceVO.getClassId());
					}else{
						staffDailyAttendanceSubmitTrack.setLastUpdatedDate(new Date());
						staffDailyAttendanceSubmitTrack.setLastAccessDate(new Date());
						staffDailyAttendanceSubmitTrack.setStaffAccountId(loggedUser.getId());
					}
						this.save(staffDailyAttendanceSubmitTrack);
					
			}
		}catch(Exception e){ e.printStackTrace(); return 1; }
		return 0;
	}
	
	public boolean updateClassAssignment(ClassAssignmentMainVO classAssignmentMainVO){
		boolean tempBoolean=false; 
		try { 
			long custId = classAssignmentMainVO.getIdentifier().getCustId();
			long academicYearId = classAssignmentMainVO.getIdentifier().getAcademicYearId();
		    if (custId>0  && academicYearId>0 && !ObjectFunctions.isNullOrEmpty(classAssignmentMainVO)) {
		    	if (!ObjectFunctions.isNullOrEmpty(classAssignmentMainVO.getClassAssignmentVOList())) {
					for(ClassAssignmentVO classAssignmentVO:classAssignmentMainVO.getClassAssignmentVOList()){
						ClassAssignmentVO  assignment=(ClassAssignmentVO)classAssignmentVO;
						long assignmentId = assignment.getId();
						long subjectId =  assignment.getSubjectId();
						long studyClassId = assignment.getStudyClassId();
						String assignmentDate = assignment.getAssignmentDate();
						String attachmentName =  assignment.getAttachmentName();
						String description =  assignment.getDescription();
						long createdById =  assignment.getCreatedById();
						String status =  assignment.getStatus();
						Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, assignmentDate);
						if(!StringFunctions.isNullOrEmpty(status))
						{
							if("D".equalsIgnoreCase(status))
							{
								this.remove(ClassAssignment.class,assignmentId);
								tempBoolean = true;
							}
							else
							{
								tempBoolean = assignments(studyClassId,Long.valueOf(custId),Long.valueOf(academicYearId),subjectId,assignmentId,status,aDate,description,attachmentName,createdById);
							}
						}
						 
				     }
				} 
		    }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			tempBoolean = false;
		}
		return tempBoolean;
	}
	
	public boolean assignments(long studyClassId,long custId,long academicYearId,long subjectId,long assignmentId,String status,Date assignmentDate,String description,String attachmentName,long createdById)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'assignments' method");
		}
		boolean returnStatement = false;
		ClassAssignment classAssignment=null;
		File destDir = null;
		try 
		{
			if("U".equalsIgnoreCase(status) &&  assignmentId > 0)
			{
				classAssignment =	(ClassAssignment) this.get(ClassAssignment.class,assignmentId);
				if(!ObjectFunctions.isNullOrEmpty(classAssignment))
				{
					classAssignment.setLastUpdatedDate(new Date());
					classAssignment.setLastAccessDate(new Date());
					classAssignment.setLastUpdatedById(createdById);
				}
			}
			else
			{
				classAssignment = new ClassAssignment();
				classAssignment.setCreatedDate(new Date());
				classAssignment.setCustId(custId);
				
				classAssignment.setCreatedById(createdById);
			}
			if(!ObjectFunctions.isNullOrEmpty(classAssignment)){
			classAssignment.setAssignmentDate(assignmentDate);
			classAssignment.setDescription(description);
			classAssignment.setSubjectId(subjectId);
			classAssignment.setAssignmentDate(assignmentDate);
			classAssignment.setClassSectionId(studyClassId);
			classAssignment.setDescription(description);
			
			log.debug("Before running query");
			Object[] studySubjectobj = (Object[])this.get("select name,id from studySubject where custId ="+custId+" and academicYearId="+academicYearId+" and id="+subjectId);
			log.debug("After running query");
			
			if(!ObjectFunctions.isNullOrEmpty(studySubjectobj))
				 classAssignment.setSubjectName(studySubjectobj[0].toString());
				StringBuffer pathName = new StringBuffer("userFiles/").append("teacherOrAdmin");
			    pathName.append("/");
			    pathName.append(studyClassId);
			    pathName.append("/");
			    if(!ObjectFunctions.isNullOrEmpty(pathName))
			    {
			    	 Attachment attachment = null;
			    	 destDir = new File(SpringContextAware.getRealPath(pathName+attachmentName));
					 try {
						 	attachment = new Attachment();
							attachment.setFilePath(pathName.toString());
							attachment.setFileName(attachmentName);
							if(!ObjectFunctions.isNullOrEmpty(attachment)) {
								classAssignment.addAttachmentFiles(attachment);
				            }
							attachment = null;
						} catch (Exception e) {
							e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
						}
				}
			    this.save(classAssignment);
			    Process p = null;
           		
			    //if("prod".equalsIgnoreCase(serverType)){
           			p = Runtime.getRuntime().exec("sh /etc/init.d/classAssignments.sh");
               	/*}else{
               		p = Runtime.getRuntime().exec("sh /usr/share/tomcat7/urtapps/eschooldev/ROOT/classAssignments_move.sh");
           		}*/
			    
           		p.waitFor();
           		StringBuffer ftpPathName = new StringBuffer("userFiles/").append("classAssignments");
           		ftpPathName.append("/");
           		File ftpDir = new File(SpringContextAware.getRealPath(ftpPathName+attachmentName));
           		if(ftpDir.exists())
           		{
           			FileUtils.copyFile(ftpDir, destDir);
           		}
           		returnStatement = true;
			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		return returnStatement;
	}
	
	
	public int saveStudentPermissions(ViewPermissionSettingsMainVO viewPermissionSettingsMainVO){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'saveStudentPermissions' method");
		}
		Permissions permissions=null;
		Permissions permissionsObj=null;
		PermissionTimings pTimings = null;
		//boolean tempBoolean=false; 
		try {
			if (!ObjectFunctions.isNullOrEmpty(viewPermissionSettingsMainVO)) {
				long supervisorId = 0;
				Object[] supervisor = null;
				Date stDate =null;
				Date eDate =null;
				long custId = viewPermissionSettingsMainVO.getIdentifier().getCustId();
				long academicYearId = viewPermissionSettingsMainVO.getIdentifier().getAcademicYearId();
				long accountId = viewPermissionSettingsMainVO.getIdentifier().getAccountId();
				for(PermissionsVO permissionsVo : viewPermissionSettingsMainVO.getPermissionsVOList()){
					if(!ObjectFunctions.isNullOrEmpty(permissionsVo)){
						permissionsObj=(Permissions)this.get(Permissions.class,"studentId="+permissionsVo.getStudentId()+" and startDate between '"+permissionsVo.getStartDate()+"' and '"+permissionsVo.getEndDate()+"'  and  endDate between '"+permissionsVo.getStartDate()+"' and '"+permissionsVo.getEndDate()+"'");
						if(ObjectFunctions.isNullOrEmpty(permissionsObj)){
							permissions= new Permissions();
							permissions.setId(permissionsVo.getId());
							stDate = DateFormatter.parseString(DateFormatter.CCYY_MM_DD_PATTERN, permissionsVo.getStartDate());
							eDate = DateFormatter.parseString(DateFormatter.CCYY_MM_DD_PATTERN, permissionsVo.getEndDate());
							permissions.setStartDate(stDate);
							permissions.setEndDate(eDate);
							permissions.setStudentId(permissionsVo.getStudentId());
							permissions.setComments(permissionsVo.getComments());
							permissions.setCreatedById(permissionsVo.getCreatedById());
							User loggedUser = (User) this.get(User.class, accountId);
							permissions.setCreatedById(accountId);
							 if(!ObjectFunctions.isNullOrEmpty(loggedUser)){
								if(loggedUser.isParent()){// P for Parent
									permissions.setComments(permissionsVo.getComments());
									 long studyClassId = permissionsVo.getStudyClassId();
									 if(studyClassId>0) {
										ClassTeacher teacher= (ClassTeacher)this.get(ClassTeacher.class,"custId="+custId+" and academicYearId<="+ academicYearId +" and studyClassId="+ studyClassId+" and classTeacher='"+ Constants.YES_STRING+ "' ");
										 if(!ObjectFunctions.isNullOrEmpty(teacher)) {
											 supervisorId = teacher.getStaff().getAccount().getId();
										 }else{
											 supervisor = this.get("SELECT vu.accountId, vu.roleName FROM vw_allUsers vu WHERE vu.custId = "+ Long.valueOf(custId) + " AND vu.roleName = 'ROLE_HOD'");
										 } 
								   }
								  if(!ObjectFunctions.isNullOrEmpty(supervisor))
										permissions.setSupervisorId(Long.parseLong(supervisor[0].toString()));
									else
										permissions.setSupervisorId(supervisorId);
									supervisor = null;
									permissions.setStatus(Constants.PENDING_STATUS);
								}else{
									permissions.setSupervisorId(loggedUser.getId());
									permissions.setStatus(Constants.ACTIVE_STATUS);
								}
								if(!ObjectFunctions.isNullOrEmpty(permissionsVo.getPermissionTimingsList())){
									for(PermissionTimingsVO ptimeObj:permissionsVo.getPermissionTimingsList()){
										pTimings = new PermissionTimings();
										pTimings.setId(ptimeObj.getId());
										//pTimings.setPermissions(permissions);
										pTimings.setStartTime(ObjectFunctions.isNullOrEmpty(ptimeObj.getStartTime())? "": ptimeObj.getStartTime());
										pTimings.setEndTime(ObjectFunctions.isNullOrEmpty(ptimeObj.getEndTime())? "": ptimeObj.getEndTime());
										pTimings.setDays(ObjectFunctions.isNullOrEmpty(ptimeObj.getDays())? "": ptimeObj.getDays());
										permissions.addPermissionTimingDetails(pTimings);
									}
								}
								this.save(permissions);
								return 0;
							}
						}else{
							return 1;
						}
					}
				}
		 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 2;
		}
		return 0;
	}
	public  Map<String,String> addOrUpdateStudentPermissions(String strPermissionData,long userId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'updateStudentAddress' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try {
			Permissions permissions=null;
			PermissionTimings timings=null;
			JSONObject timejsonObj = null;
			Permissions permissionsObj=null;
			JSONObject formData = new JSONObject(strPermissionData);
			if (ObjectFunctions.isNullOrEmpty(formData)) {
				msg.put("1","Student Permission is not registered due to system error. Please contact System Administrator");
				return msg;
			}
			JSONArray studentData = (JSONArray) formData.getJSONArray("permissionData");
			User loggedUser = (User) this.get(User.class, userId);
			Date stDate =null;
			Date eDate =null;
			Date sqlStatDate =null;
			 Date sqlEndDate =null;
			 String sdate=null;
			 String edate=null;
			long supervisorId = 0;			
			Object[] supervisor = null;
			for (int i = 0; i < studentData.length(); i++) {
				JSONObject studentPermissionObj = (JSONObject) studentData.get(i);
				if (!ObjectFunctions.isNullOrEmpty(studentPermissionObj)) {
					long studentId = studentPermissionObj.getLong("studentId");
					String startDate = studentPermissionObj.getString("startDate");
					String endDate = studentPermissionObj.getString("endDate");
					 stDate = DateFormatter.parseString(DateFormatter.MMDDCCYY_PATTERN, startDate);
					 eDate = DateFormatter.parseString(DateFormatter.MMDDCCYY_PATTERN, endDate);
					 /*below date format used to check student permission already applied above dates or not*/ 
					 sqlStatDate = DateFormatter.parseString(DateFormatter.MMDDCCYY_PATTERN, DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,startDate));
					 sqlEndDate = DateFormatter.parseString(DateFormatter.MMDDCCYY_PATTERN, DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,endDate));
					 sdate= DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, sqlStatDate);
					 edate= DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, sqlEndDate);
					String comments =  studentPermissionObj.getString("comments");
					if (!ObjectFunctions.isNullOrEmpty(studentId)) {
						permissionsObj = (Permissions)this.get(Permissions.class,"studentId="+studentId+" and startDate between '"+sdate+"' and '"+edate+"'  and  endDate between '"+sdate+"' and '"+edate+"'");
						if(ObjectFunctions.isNullOrEmpty(permissionsObj)){
							permissions=new Permissions();
							permissions.setStudentId(studentId);
							permissions.setStartDate(stDate);
							permissions.setComments(comments);	
							permissions.setCreatedById(loggedUser.getId());						
							permissions.setEndDate(eDate); 
							if(loggedUser.isParent()){// P for Parent
								 long studyClassId = studentPermissionObj.getLong("studyClassId");
								 long custId = studentPermissionObj.getLong("custId");
								 long academicYearId = studentPermissionObj.getLong("academicYearId");
								 if(studyClassId>0) {
									ClassTeacher teacher= (ClassTeacher)this.get(ClassTeacher.class,"custId="+custId+" and academicYearId<="+ academicYearId +" and studyClassId="+ studyClassId+" and classTeacher='"+ Constants.YES_STRING+ "' ");
									 if(!ObjectFunctions.isNullOrEmpty(teacher)) {
										 supervisorId = teacher.getStaff().getAccount().getId();
									 }else{
										 supervisor = this.get("SELECT vu.accountId, vu.roleName FROM vw_allUsers vu WHERE vu.custId = "+ Long.valueOf(custId) + " AND vu.roleName = 'ROLE_HOD'");
									 } 
							   }
							  if(!ObjectFunctions.isNullOrEmpty(supervisor))
									permissions.setSupervisorId(Long.parseLong(supervisor[0].toString()));
								else
									permissions.setSupervisorId(supervisorId);
								supervisor = null;
								permissions.setStatus(Constants.PENDING_STATUS);
							}else{
								permissions.setSupervisorId(loggedUser.getId());
								permissions.setStatus(Constants.ACTIVE_STATUS);
							}
							//permissions = (Permissions)this.saveOrUpdateObject(permissions);
							JSONArray timingsData = (JSONArray) studentPermissionObj.getJSONArray("dayTimes");
							if (!ObjectFunctions.isNullOrEmpty(timingsData)) {
								for (int j = 0; j < timingsData.length(); j++) {
									timejsonObj = timingsData.getJSONObject(j);
									if (!ObjectFunctions.isNullOrEmpty(timejsonObj)) {
										String statTime = timejsonObj.getString("startTime");									
										String endTime = timejsonObj.getString("endTime");
										String day = timejsonObj.getString("days");
										timings=new PermissionTimings();
										timings.setEndTime(endTime);
										timings.setDays(day);									
										timings.setStartTime(statTime);
										//timings.setPermissions(permissions);
										permissions.addPermissionTimingDetails(timings);
									}
								}
							}
							this.save(permissions);
						}else{
							msg.put("1","Applied Permission range is overlapped with exiting permission, save not performed.");
							return msg;
						}
					}
				}
			}
			msg.put("0", "Successfully added student permissions.");
		} catch (Exception ex) {
			msg.put("1", "System error occurred, please contact System Administrator");
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return msg;
	}
	public int saveStaffPermissionsRequests(StaffPermissionRequestsMainVO staffPermissionRequestsMainVO){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'saveStaffPermissionsRequests' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(staffPermissionRequestsMainVO)) {
				Map<String,String> msgs =null;
				long academicYearId = staffPermissionRequestsMainVO.getIdentifier().getAcademicYearId();
				long custId = staffPermissionRequestsMainVO.getIdentifier().getCustId();
				for(StaffPermissionRequestsVO requestVo : staffPermissionRequestsMainVO.getStaffPermissionRequestsVOlList()){
					msgs = this.addStaffRequests(custId,academicYearId,0,requestVo);
					if (!StringFunctions.isNullOrEmpty(msgs.get("0")))
						return 0;
					if (!StringFunctions.isNullOrEmpty(msgs.get("1")))
						return 1;
					if (!StringFunctions.isNullOrEmpty(msgs.get("2")))
						return 2;
				}
		 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 1;
		}
		return 0;
	}
	public Map<String,String> addStaffRequests(long custId, long academicYearId, long staffId, StaffPermissionRequestsVO requestVo){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'getAllPermissionsByAccountId' method");
		}
		Map<String, String> msgs = new HashMap<String,String>();
		try {
			long sId=0;
			if(staffId>0)
			  sId=staffId; //this value coming from web page
			else
			  sId=requestVo.getStaffId();//this value coming from mobile app
			Date edate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(requestVo.getPermissionDate()+" 00:00:00");
			StaffPermissionRequests staffPermissionRequests =(StaffPermissionRequests)this.get(StaffPermissionRequests.class,"staffId="+sId+" and academicYearId<="+academicYearId+" and permissionDate='"+requestVo.getPermissionDate()+"' ");
			 if(ObjectFunctions.isNullOrEmpty(staffPermissionRequests)){
				StaffPermissionRequests  stp = new StaffPermissionRequests();
				stp.setStaffId(sId);
				stp.setStartTime(requestVo.getStartTime());
				stp.setEndTime(requestVo.getEndTime());
				stp.setAcademicYearId(academicYearId);
				stp.setPermissionDate(edate);
				stp.setStatus(requestVo.getStatus());
				stp.setComments(requestVo.getComments());
				stp.setApprovalsComment(requestVo.getApprovalsComment());
				Object[] supervisor = this.get("SELECT vu.accountId, vu.roleName FROM vw_allUsers vu WHERE vu.custId = "+ custId + " AND vu.roleName IN('ROLE_PRINCIPAL', 'ROLE_ADMIN') ORDER BY vu.roleName DESC");
				if(!ObjectFunctions.isNullOrEmpty(supervisor))
					stp.setSupervisorId(Long.parseLong(supervisor[0].toString()));
				supervisor = null;
				this.save(stp);
				msgs.put("0","success");
			 }else{
				 msgs.put("2","Applied Permission range is overlapped with exiting permission, save not performed.");
			 }
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			msgs.put("1","fail");
		}
		return msgs;
	}
	public ViewPermissionSettingsMainVO getAllPermissionsByAccountId(long custId,long academicYearId, long accountId){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'getAllPermissionsByAccountId' method");
		}
		try {
			PermissionsVO permissionsVO=null;
			PermissionTimingsVO permissionTimingsVO=null;
			ViewPermissionSettingsMainVO viewPermissionSettingsMainVO = new ViewPermissionSettingsMainVO();
			List<Permissions> permissionsList =null;
				if(accountId > 0){
					List<String> studentIds = this.getAll("select studentId from vw_studentDetails where custId="+custId+" and academicYearId="+academicYearId+" and parentId="+accountId+" and status='"+Constants.YES_STRING+"'");
					if(ObjectFunctions.isNullOrEmpty(studentIds))
						studentIds.add("0");
					permissionsList= this.getAll(Permissions.class, "studentId in ("+StringUtil.convertListToString(studentIds)+")"+" order by startDate");
				} 
				if (!ObjectFunctions.isNullOrEmpty(permissionsList)) {
					for(Permissions  permissions: permissionsList) {
						permissionsVO = new PermissionsVO();
						permissionsVO.setStudentId(permissions.getStudentId());
						permissionsVO.setId(permissions.getId());
						permissionsVO.setComments(permissions.getComments());
						permissionsVO.setStartDate(ObjectFunctions.isNullOrEmpty(permissions.getStartDate())? "": DateFunctions.convertDateToString(permissions.getStartDate()));
						permissionsVO.setEndDate(ObjectFunctions.isNullOrEmpty(permissions.getEndDate())? "": DateFunctions.convertDateToString(permissions.getEndDate()));
						permissionsVO.setApprovalsComment(permissions.getApprovalsComment());
						permissionsVO.setCreatedById(permissions.getCreatedById());
						//permissionsVO.setPermissionTimingsId(permissions.getPermissionTimings());
						permissionsVO.setStatus(permissions.getStatus());
						for(PermissionTimings  permissionTimings: permissions.getPermissionTimings()) {
							permissionTimingsVO = new PermissionTimingsVO();
							permissionTimingsVO.setId(permissionTimings.getId());
							permissionTimingsVO.setPermissionsId(permissions.getId());
							permissionTimingsVO.setStartTime(ObjectFunctions.isNullOrEmpty(permissionTimings.getStartTime())? "": permissionTimings.getStartTime());
							permissionTimingsVO.setEndTime(ObjectFunctions.isNullOrEmpty(permissionTimings.getEndTime())? "": permissionTimings.getEndTime());
							permissionTimingsVO.setDays(ObjectFunctions.isNullOrEmpty(permissionTimings.getDays())? "": permissionTimings.getDays());
							permissionsVO.getPermissionTimingsList().add(permissionTimingsVO);
						}
						viewPermissionSettingsMainVO.getPermissionsVOList().add(permissionsVO);
						permissionsVO=null;
					}
					return viewPermissionSettingsMainVO;
				}else{
					return null;
				}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public ViewStaffPermissionsSettingsMainVO getStaffPermissionSettings(long custId,long academicYearId){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'getStaffPermissionSettings' method");
		}
		try {
			StaffPermissionsSettingsVO staffPermissionsSettingsVO=null;
			StaffPermissionsDayDetailsVO permissionsDayDetailsVO=null;
			ViewStaffPermissionsSettingsMainVO viewStaffPermissionsSettingsMainVO = null;
			ViewStaffPermissionsSettingsVO viewStaffPermissionsSettingsVO = null;
			List<ViewStaffPermissionsSettingsVO> viewStaffPermissionsSettingsVOList = new ArrayList<ViewStaffPermissionsSettingsVO>();
			List<ViewStaffPermissionsSettings>  permissionsList= this.getAll(ViewStaffPermissionsSettings.class, "custId="+custId+" and academicYearId="+academicYearId);
				if (!ObjectFunctions.isNullOrEmpty(permissionsList)) {
					for(ViewStaffPermissionsSettings  viewStaffPermissionSettings: permissionsList) {
						viewStaffPermissionsSettingsVO = new ViewStaffPermissionsSettingsVO();
						staffPermissionsSettingsVO = new StaffPermissionsSettingsVO();
						staffPermissionsSettingsVO.setId(viewStaffPermissionSettings.getStaffPermissionsSettingsId());
						staffPermissionsSettingsVO.setStatus(viewStaffPermissionSettings.getStatus());
						staffPermissionsSettingsVO.setMonthOrYear(viewStaffPermissionSettings.getMonthOrYear());
						staffPermissionsSettingsVO.setIsRolebased(viewStaffPermissionSettings.getIsRolebased());
						viewStaffPermissionsSettingsVO.setStaffPermissionsSettingsVO(staffPermissionsSettingsVO);
						permissionsDayDetailsVO = new StaffPermissionsDayDetailsVO();
						permissionsDayDetailsVO.setId(viewStaffPermissionSettings.getStaffPermissionsDayDetailsId());
						permissionsDayDetailsVO.setStaffPermissionsSettingsId(viewStaffPermissionSettings.getStaffPermissionsSettingsId());
						permissionsDayDetailsVO.setRoleId(viewStaffPermissionSettings.getRoleId());
						permissionsDayDetailsVO.setDays(viewStaffPermissionSettings.getDays());
						viewStaffPermissionsSettingsVO.setStaffPermissionsDayDetailsVO(permissionsDayDetailsVO);
						viewStaffPermissionsSettingsVOList.add(viewStaffPermissionsSettingsVO);
						viewStaffPermissionsSettingsVO = null;
					}
					viewStaffPermissionsSettingsMainVO = new ViewStaffPermissionsSettingsMainVO();
					viewStaffPermissionsSettingsMainVO.setViewStaffPermissionsSettingsVOList(viewStaffPermissionsSettingsVOList);
					return viewStaffPermissionsSettingsMainVO;
				}else{
					return null;
				}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public ViewStaffPermissionRequestsMainVO getStaffAllPermissionsByAccountId(long custId,long academicYearId, long accountId){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'getAllPermissionsByAccountId' method");
		}
		try {
			StaffPermissionRequestsVO staffPermissionRequestsVO=null;
			ViewStaffPermissionRequestsMainVO viewStaffPermissionRequestsMainVO = null;
			ViewStaffPermissionRequestsVO requestsVO = null;
			List<ViewStaffPermissionRequestsVO> viewStaffPermissionRequestsVOList = new ArrayList<ViewStaffPermissionRequestsVO>();
			Staff staff = (Staff) this.get(Staff.class, " accountId = " +accountId+ " and status = '" + Constants.YES_STRING+"'");
			if (!ObjectFunctions.isNullOrEmpty(staff)) {
			 List<ViewStaffPermissionRequests>  requestsList= this.getAll(ViewStaffPermissionRequests.class, "custId="+custId+" and staffId="+staff.getId());
				if (!ObjectFunctions.isNullOrEmpty(requestsList)) {
					for(ViewStaffPermissionRequests  permissionRequests: requestsList) {
						requestsVO = new ViewStaffPermissionRequestsVO();
						staffPermissionRequestsVO = new StaffPermissionRequestsVO();
						staffPermissionRequestsVO.setStaffId(permissionRequests.getStaffId());
						staffPermissionRequestsVO.setComments(permissionRequests.getComments());
						staffPermissionRequestsVO.setStartTime(permissionRequests.getStartTime());
						staffPermissionRequestsVO.setEndTime(permissionRequests.getEndTime());
						staffPermissionRequestsVO.setPermissionDate(ObjectFunctions.isNullOrEmpty(permissionRequests.getPermissionDate())? "": DateFunctions.convertDateToString(permissionRequests.getPermissionDate()));
						staffPermissionRequestsVO.setStatus(permissionRequests.getStatus());
						staffPermissionRequestsVO.setApprovalsComment(permissionRequests.getApprovalsComment());
						requestsVO.setStaffName(permissionRequests.getFullName());
						requestsVO.setPermissionRequestId(permissionRequests.getPermissionRequestId());
						requestsVO.setStaffPermissionRequestsVO(staffPermissionRequestsVO);
						viewStaffPermissionRequestsVOList.add(requestsVO);
						requestsVO = null;
					}
					viewStaffPermissionRequestsMainVO = new ViewStaffPermissionRequestsMainVO();
					viewStaffPermissionRequestsMainVO.setViewStaffPermissionRequestsVOList(viewStaffPermissionRequestsVOList);
					return viewStaffPermissionRequestsMainVO;
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public void removePermissionTimingsByPermissionId(long permissionId) {
		studentDao.removePermissionTimingsByPermissionId(permissionId);
	}

	public User saveOrUpdateStudent(StudentVo studentStudentVo, UserVO studentAccountVo, PersonVO studentPersonVo, AddressVO studentAddressVo,Customer customer,SMSServiceProviders smsServiceProvider,List<PersonDocuments> studentDocList,User loggedUser) {
		
		boolean isNewStudent = false;
		User createduser = new User();
		createduser = createduser.copyFromVoToEntity(createduser, studentAccountVo);
		 Role role = this.getRoleByName(Constants.SCHOOL_STUDENT);
		 createduser.addNewRole(role);
		 role = null;
		Person person = new Person();
		person = person.copyFromVoToEntity(person, studentAccountVo.getPersonVo());
		createduser.setPerson(person);
		if (!ObjectFunctions.isNullOrEmpty(studentAccountVo.getPrimaryAddressVo())) 
		{
			if(!StringFunctions.isNullOrEmpty(studentAccountVo.getPrimaryAddressVo().getState())){
				Object[] stateIdObj = this.get("select id,stateCode from State where stateCode='"+studentAccountVo.getPrimaryAddressVo().getState()+"'");
				if(!ObjectFunctions.isNullOrEmpty(stateIdObj))
					studentAccountVo.getPrimaryAddressVo().setStateId(Long.valueOf(stateIdObj[0].toString()));
			}
			Address primaryAddress = new Address();
			primaryAddress = primaryAddress.copyFromVoToEntity(primaryAddress, studentAccountVo.getPrimaryAddressVo());
			HouseType houseType =null;
			houseType = (HouseType) this.get(HouseType.class, "id ="+studentAccountVo.getPrimaryAddressVo().getHouseTypeId())  ;
			primaryAddress.setHouseType(houseType);
			createduser.setPrimaryAddress(primaryAddress);
		}
		if (!ObjectFunctions.isNullOrEmpty(studentAccountVo.getTempararyAddressVo())) 
		{
			Address tempararyAddress = new Address();
			tempararyAddress = tempararyAddress.copyFromVoToEntity(tempararyAddress, studentAccountVo.getTempararyAddressVo());
			createduser.setTempararyAddress(tempararyAddress);
		}
		
		createduser.setAccountExpired(false);
		createduser.setEnabled(true);	
		if(!ObjectFunctions.isNullOrEmpty(studentDocList)){
			createduser.getPersonDocuments().addAll(studentDocList);
		}
		createduser =(User) this.saveOrUpdateObject(createduser);
		if (!ObjectFunctions.isNullOrEmpty(createduser)) 
		{
			this.updateStudentUserName(createduser.getId(),PasswordUtils.passwordEncoder(String.valueOf(createduser.getId()),null));
			/* username password we are updating throug sql qery. So we are not getting updated username and password that's why I am setting accountId to username because in this method we are seding student credentiols when student have mobile number in this case we credentiols are going old details. */
			createduser.setUsername(String.valueOf(createduser.getId()));
			isNewStudent = true;
			Student student = new Student();
			student = student.copyFromVoToEntity(student, studentStudentVo);
			student.setCustId(customer.getId());
			
			student.setStatus(Constants.YES_STRING);
			
			student.setAccount(createduser);
			
			AcademicYear academicYear = new AcademicYear();
			academicYear = academicYear.copyFromVoToEntity(academicYear, studentStudentVo.getAcademicYearVo());
			student.setAcademicYear(academicYear);
			
			ClassName className = new ClassName();
			className = className.copyFromVoToEntity(className, studentStudentVo.getClassNameVo());
			className.setAcademicYear(academicYear);
			student.setClassNameClassId(className);
			
			StudyClass studyClass = new StudyClass();
			studyClass = studyClass.copyFromVoToEntity(studyClass, studentStudentVo.getStudyClassVo());
			studyClass.setAcademicYear(academicYear);
			studyClass.setClassNameClassId(className);
			student.setClassSection(studyClass);
			
			if (!ObjectFunctions.isNullOrEmpty(studentStudentVo.getProfileImage())) 
			{
				UserImage profileImage =(UserImage) this.get(UserImage.class, studentStudentVo.getProfileImage().getId());
				student.setProfileImage(profileImage);
			}
			
			if (!ObjectFunctions.isNullOrEmpty(studentStudentVo.getBedVo())) 
			{
				Bed bed = new Bed();
				student.setBed(bed.copyFromVoToEntity(bed, studentStudentVo.getBedVo()));
			}
			student = (Student)this.saveOrUpdateObject(student);
			
			if(!ObjectFunctions.isNullOrEmpty(student))
			{
				Customer masterCustomer = null;
				if(customer.getId() == 173)
					masterCustomer = customer;
				else
					masterCustomer = this.getMasterCustomerById();
				//here passing User Object to dispaly sms sender name in the View SMS page 
				String response = createParentLoginAccount(customer,academicYear,student,isNewStudent,masterCustomer,loggedUser);
				this.updateClassAndSectionCapacity(customer.getId(), academicYear.getId(),0, studyClass.getId());
				
				//Sending notification for student addition for admin, staff and parents
				 this.sendNotificationForStudentUpdate(student);
				 masterCustomer = null;
			}
		}
		return createduser;
	}

	public void sendEmailToParentsAndStudent(String email,String studName,Customer customer,String classAndSection,User user,String parentUserName,String sendEmail,String newPassword){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'sendEmailToParentsAndStudent' method");
		}
		try {
			String[] emailAddresses = new String[1];
			MailUtil mailUtil = null;
			if(StringFunctions.isNotNullOrEmpty(email)){
				emailAddresses[0]=email;
				if("Students".equalsIgnoreCase(sendEmail))
					 mailUtil=new MailUtil(emailAddresses,"Regd : Student Registration Email",customer.getId(),customer.getSender(),"Administrator",this.getContactFromEmail(customer));
				else
					 mailUtil=new MailUtil(emailAddresses,"Regd : Parent Registration Email",customer.getId(),customer.getSender(),"Administrator",this.getContactFromEmail(customer));
				mailUtil.setContactEmail(customer.getContactEmail());
				mailUtil.setContactPasword(customer.getContactPassword());
				if("Students".equalsIgnoreCase(sendEmail))
					mailUtil.sendMailForParentWithStudentCredentials(customer,studName,classAndSection,null,null, user.getUsername().toLowerCase(),user.getUsername().toLowerCase(),this.getContactFromEmail(customer));
				else
					mailUtil.sendMailForParentWithStudentCredentials(customer,studName,classAndSection,parentUserName,newPassword,user.getUsername(),user.getUsername(),this.getContactFromEmail(customer));
				mailUtil=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	public ViewRoutesMainVO getRoutesListByAcademicYearIdAndCustId(long custId,long academicYearId)
	{
		ViewRoutesMainVO viewRoutesMainVO = new ViewRoutesMainVO();
		List<RouteVo> routeVoList = new ArrayList<RouteVo>();
		List<Route> routesList = this.getAll(Route.class,"custId="+custId+" and academicYearId="+academicYearId);
		if(!ObjectFunctions.isNullOrEmpty(routesList))
		{
			for(Route route : routesList)
			{
				RouteVo routeVo = route.copyFromEntityToVo(route);
				routeVoList.add(routeVo);
			}
			viewRoutesMainVO.setRouteVoList(routeVoList);
		}
		return viewRoutesMainVO;
		
	}
	
	public ViewRouteBoardingPointsMainVO getRouteBoardingPointsListByRouteId(long routeId)
	{
		ViewRouteBoardingPointsMainVO viewRouteBoardingPointsMainVO = new ViewRouteBoardingPointsMainVO();
		List<RouteBoardingPointsVO> routeBoardingPointsVOList = new ArrayList<RouteBoardingPointsVO>();
		List<RouteBoardingPoints> routeBoardingPointsList = this.getAll(RouteBoardingPoints.class,"routeId ="+routeId);
		if(!ObjectFunctions.isNullOrEmpty(routeBoardingPointsList))
		{
			for(RouteBoardingPoints routeBoardingPoints : routeBoardingPointsList)
			{
				RouteBoardingPointsVO routeBoardingPointsVO = routeBoardingPoints.copyFromEntityToVo(routeBoardingPoints);
				routeBoardingPointsVOList.add(routeBoardingPointsVO);
			}
			viewRouteBoardingPointsMainVO.setRouteBoardingPointsVoList(routeBoardingPointsVOList);
		}
		return viewRouteBoardingPointsMainVO;
		
	}
	
	public long addStudyClassWiseStudentOptionlaSubject(long custId, long academicYearId, String tempString, long classSectionId) {
		try {
			if (!StringFunctions.isNullOrEmpty(tempString)) {
				JSONObject formData = new JSONObject(tempString);
				JSONArray studentOptionalSubjJsonArray = (JSONArray) formData.get("data");
				long studentId = 0;
				long optionalSubjId = 0;
				JSONObject studentOptionalSubjJson = null;

				if (!ObjectFunctions.isNullOrEmpty(studentOptionalSubjJsonArray)) {
					for (int i = 0; i < studentOptionalSubjJsonArray.length(); i++) {
						studentOptionalSubjJson = studentOptionalSubjJsonArray.getJSONObject(i);
						if (!ObjectFunctions.isNullOrEmpty(studentOptionalSubjJson)) {
							if (!ObjectFunctions.isNullOrEmpty(studentOptionalSubjJson.get("studentId"))) {
								studentId = Long.valueOf((String) studentOptionalSubjJson.get("studentId"));
							}
							if (!ObjectFunctions.isNullOrEmpty(studentOptionalSubjJson.get("optionalSubjId"))) {
								optionalSubjId = Long.valueOf((String) studentOptionalSubjJson.get("optionalSubjId"));
							}
							if(studentId >0){
								Student student = (Student) this.get(Student.class,"custId=" + custId + " and academicYearId="+ academicYearId + " and id="+ studentId+ " and description is null");
								if (!ObjectFunctions.isNullOrEmpty(student)) {
									student.setLastUpdatedDate(new Date());
									student.setOptionalSubjectId(optionalSubjId);
									this.saveOrUpdateObject(student);
									student = null;
									optionalSubjId=0;
									studentId=0;
								}
							}
						}
					}
				}
				return 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 0;
		}
		return 1;
	}
	public StudentDetailsListVO getStudentDetailsVO(long accountId, String type) {
		try {
			StudentDetailsListVO studentDetailsListVO=null;
			StudentDetailsVO studentDetailsVo=null;
			List<StudentDetailsVO> viewStudentPersonAccountDetailsVOs = new ArrayList<StudentDetailsVO>();
			if(accountId > 0 && !StringFunctions.isNullOrEmpty(type)){
				User curUser= (User)this.get(User.class,accountId);
				if(!ObjectFunctions.isNullOrEmpty(curUser))
				{
					AcademicYear academicYear  = super.getCurrentAcademicYear("Y",curUser.getCustId());
					if (!ObjectFunctions.isNullOrEmpty(academicYear)) 
					{
						List<Object[]> studentsList =null;
						// A - Admin & S - Staff & C - Student & P - Parent
						if(type.equalsIgnoreCase("A"))
							studentsList =  this.getAll("SELECT vs.studentId, vs.accountId, vs.parentId, vs.fatherName, vs.motherName, vs.custId, vs.academicYearId, vs.firstName, vs.lastName, vs.gender, vs.classSectionId, vs.admissionNumber, vs.rollNumber,"
									+ " vs.streetName, vs.city, vs.stateName, vs.postalCode, vs.mobileNumber, vs.parentEmail, vs.dateOfBirth, vs.status, vs.description, vs.imageId,"
									+ "vs.studentMobile, vs.transportMode, vs.boardingPointId,vs.imagePath,vs.registerNumber,vs.aadharNumber,vs.stsNumber FROM vw_studentDetails vs WHERE vs.academicYearId = "+ academicYear.getId());
						
						else if(type.equalsIgnoreCase("S"))
							studentsList =  this.getAll("SELECT vs.studentId, vs.accountId, vs.parentId, vs.fatherName, vs.motherName, vs.custId, vs.academicYearId, vs.firstName, vs.lastName, vs.gender, vs.classSectionId, vs.admissionNumber, vs.rollNumber,"
									+ " vs.streetName, vs.city, vs.stateName, vs.postalCode, vs.mobileNumber, vs.parentEmail, vs.dateOfBirth, vs.status, vs.description, vs.imageId,"
									+ "vs.studentMobile, vs.transportMode, vs.boardingPointId,vs.imagePath,vs.registerNumber,vs.aadharNumber,vs.stsNumber FROM vw_studentDetails  vs ,(SELECT ct.studyClassId FROM classTeacher ct, staff s WHERE s.accountId= "+ accountId +" and ct.academicYearId = "+academicYear.getId()+" AND ct.teacherId = s.id) as cts WHERE vs.academicYearId = "+ academicYear.getId() +" AND vs.classSectionId=cts.studyClassId");
						
						else if(type.equalsIgnoreCase("C"))
							studentsList =  this.getAll("SELECT vs.studentId, vs.accountId, vs.parentId, vs.fatherName, vs.motherName, vs.custId, vs.academicYearId, vs.firstName, vs.lastName, vs.gender, vs.classSectionId, vs.admissionNumber, vs.rollNumber,"
									+ " vs.streetName, vs.city, vs.stateName, vs.postalCode, vs.mobileNumber, vs.parentEmail, vs.dateOfBirth, vs.status, vs.description, vs.imageId,"
									+ "vs.studentMobile, vs.transportMode, vs.boardingPointId,vs.imagePath,vs.registerNumber,vs.aadharNumber,vs.stsNumber FROM vw_studentDetails vs WHERE vs.academicYearId = "+ academicYear.getId() + " AND vs.accountId = "+ accountId);
						
						else if(type.equalsIgnoreCase("P"))
							studentsList =  this.getAll("SELECT vs.studentId, vs.accountId, vs.parentId, vs.fatherName, vs.motherName, vs.custId, vs.academicYearId, vs.firstName, vs.lastName, vs.gender, vs.classSectionId, vs.admissionNumber, vs.rollNumber,"
									+ " vs.streetName, vs.city, vs.stateName, vs.postalCode, vs.mobileNumber, vs.parentEmail, vs.dateOfBirth, vs.status, vs.description, vs.imageId,"
									+ "vs.studentMobile, vs.transportMode, vs.boardingPointId,vs.imagePath,vs.registerNumber,vs.aadharNumber,vs.stsNumber FROM vw_studentDetails vs WHERE vs.parentId = "+ accountId + " AND vs.status = 'Y'");
						
						else return null;
					if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
						for(Object[] student : studentsList) {
							studentDetailsVo=new StudentDetailsVO();
							studentDetailsVo.setStudentId(Long.valueOf(student[0].toString()));
							studentDetailsVo.setAccountId(Long.valueOf(student[1].toString()));
							studentDetailsVo.setParentId(ObjectFunctions.isNullOrEmpty(student[2])? 0: Long.valueOf(student[2].toString()));
							studentDetailsVo.setFatherName(ObjectFunctions.isNullOrEmpty(student[3])?"": student[3].toString());
							studentDetailsVo.setMotherName(ObjectFunctions.isNullOrEmpty(student[4])?"": student[4].toString());
							studentDetailsVo.setCustId(Long.valueOf(student[5].toString()));
							studentDetailsVo.setAcademicYearId(Long.valueOf(student[6].toString()));
							studentDetailsVo.setFirstName(ObjectFunctions.isNullOrEmpty(student[7])? "": student[7].toString());
							studentDetailsVo.setLastName(ObjectFunctions.isNullOrEmpty(student[8])? "": student[8].toString());
							studentDetailsVo.setGender(ObjectFunctions.isNullOrEmpty(student[9])? "": student[9].toString());
							studentDetailsVo.setClassSectionId(Long.valueOf(student[10].toString()));
							studentDetailsVo.setAdmissionNumber(ObjectFunctions.isNullOrEmpty(student[11])? "": student[11].toString());
							studentDetailsVo.setRollNumber(ObjectFunctions.isNullOrEmpty(student[12])? "": student[12].toString());
							studentDetailsVo.setStreet(ObjectFunctions.isNullOrEmpty(student[13])? "": student[13].toString());
							studentDetailsVo.setCity(ObjectFunctions.isNullOrEmpty(student[14])? "": student[14].toString());
							studentDetailsVo.setStateName(ObjectFunctions.isNullOrEmpty(student[15])? "": student[15].toString());
							studentDetailsVo.setPostalCode(ObjectFunctions.isNullOrEmpty(student[16])? "": student[16].toString());
							studentDetailsVo.setMobileNumber(ObjectFunctions.isNullOrEmpty(student[17])? "": student[17].toString());
							studentDetailsVo.setParentEmail(ObjectFunctions.isNullOrEmpty(student[18])? "": student[18].toString());
							studentDetailsVo.setDateOfBirth(ObjectFunctions.isNullOrEmpty(student[19])? DateFunctions.convertDateToString(DateUtils.addDays(new Date(), -1)): DateFunctions.convertDateToString((Date)student[19]));
							studentDetailsVo.setStatus(ObjectFunctions.isNullOrEmpty(student[20])? "": student[20].toString());
							studentDetailsVo.setDescription(ObjectFunctions.isNullOrEmpty(student[21])? "": student[21].toString());
							studentDetailsVo.setImageId(ObjectFunctions.isNullOrEmpty(student[22])? 0: Long.valueOf(student[22].toString()));
							studentDetailsVo.setStudentMobile(ObjectFunctions.isNullOrEmpty(student[23])? "": student[23].toString());
							studentDetailsVo.setTransportMode(ObjectFunctions.isNullOrEmpty(student[24])? "": student[24].toString());
							studentDetailsVo.setBoardingPointId(ObjectFunctions.isNullOrEmpty(student[25])? 0: Long.valueOf(student[25].toString()));
							studentDetailsVo.setImageUrl(ObjectFunctions.isNullOrEmpty(student[26])? "": student[26].toString());
						    //studentDetailsVo.setRegisterNumber(ObjectFunctions.isNullOrEmpty(student[27])? "":student[27].toString());
							studentDetailsVo.setAadharNumber(ObjectFunctions.isNullOrEmpty(student[28])? "":student[28].toString());
							studentDetailsVo.setStsNumber(ObjectFunctions.isNullOrEmpty(student[29])? "":student[29].toString());
							viewStudentPersonAccountDetailsVOs.add(studentDetailsVo);
						}
						studentDetailsListVO = new StudentDetailsListVO();
						studentDetailsListVO.setViewStudentPersonAccountDetailsVOs(viewStudentPersonAccountDetailsVOs);
						return studentDetailsListVO;
					}
					else return null;
				}else{
					return null;
				}
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public StudentMarksMainVO getStudentMarksByAccountIdAndType(long accountId, String type){
		StudentMarksVO studentMarksVO=null;
		StudentMarksMainVO studentMarksMainVO = null;
		List<StudentMarksVO> studentMarksVOList = new ArrayList<StudentMarksVO>();
		try { 
			if(accountId > 0 && accountId > 0){
				User curUser= (User)this.get(User.class,accountId);
				if(!ObjectFunctions.isNullOrEmpty(curUser))
				{
					AcademicYear academicYear  = super.getCurrentAcademicYear("Y",curUser.getCustId());
					if (!ObjectFunctions.isNullOrEmpty(academicYear)) 
					{ 
						List<String>  studentsList = null;
						// A - Admin & S - Staff & C - Student & P - Parent
						if(type.equalsIgnoreCase("A"))
							studentsList = this.getAll("SELECT GROUP_CONCAT(s.id) FROM student s WHERE s.academicYearId = "+ academicYear.getId()+" AND s.status='Y'");
							
						else if(type.equalsIgnoreCase("S"))
							studentsList =  this.getAll("SELECT GROUP_CONCAT(s.id) FROM student s WHERE s.academicYearId = "+ academicYear.getId() + " AND s.status='Y' AND s.classSectionId IN(SELECT ct.studyClassId FROM classTeacher ct WHERE ct.academicYearId = "+ academicYear.getId() +" AND ct.teacherId in (select st.id from staff st where st.accountId= "+ accountId +"))");
						
						else if(type.equalsIgnoreCase("C"))
							studentsList =  this.getAll("SELECT GROUP_CONCAT(s.id) FROM student s WHERE s.academicYearId = "+ academicYear.getId() + " AND s.status='Y' AND s.accountId = "+ accountId);
						
						else if(type.equalsIgnoreCase("P"))
							//studentsList =  this.getAll("SELECT GROUP_CONCAT(s.id) from student s LEFT JOIN Account a ON(a.id=s.accountId) WHERE a.parentId = "+ accountId + " AND s.status = 'Y'");
							  studentsList =  this.getAll("SELECT GROUP_CONCAT(s.id) from student s LEFT JOIN Account a ON(a.id=s.accountId) LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+accountId+" AND s.status = 'Y'");
						else return null;
				
				if(!ObjectFunctions.isNullOrEmpty(studentsList))
				{
					List<Object[]> studentMarks = this.getAll("SELECT (sm.obtainedMarks+sm.moderationMarks) AS subjectMarks, sm.present, sm.examScheduleId, sm.studId, es.classSubjectId FROM studentMarks sm LEFT JOIN examSchedules es ON(es.id = sm.examScheduleId) WHERE FIND_IN_SET(sm.studId, '"+studentsList.get(0)+"')");
					if (!ObjectFunctions.isNullOrEmpty(studentMarks)) {
						Map<String, Double> subjHighest = new HashMap<String, Double>();
						List<Object[]> HighestMarks = this.getAll("SELECT MAX(sm.obtainedMarks+sm.moderationMarks) AS subjectHighestMarks, sm.examScheduleId, es.classSubjectId FROM studentMarks sm LEFT JOIN examSchedules es ON(es.id = sm.examScheduleId) WHERE FIND_IN_SET(sm.studId, '"+studentsList.get(0)+"') GROUP BY es.classSubjectId, sm.examScheduleId");
						for(Object[] marks : HighestMarks){
							if(!ObjectFunctions.isNullOrEmpty(marks)){
								subjHighest.put(marks[1].toString()+"_"+marks[2].toString(), Double.valueOf(marks[0].toString()));
							}
						}
						HighestMarks = null;					
						for(Object[] marks : studentMarks) {
							  studentMarksVO = new StudentMarksVO();
							  studentMarksVO.setStudentId(Long.valueOf(marks[3].toString()));
							  studentMarksVO.setScheduleId(Long.valueOf(marks[2].toString()));
							  studentMarksVO.setSubjectMarks(Double.valueOf(marks[0].toString()));
							  studentMarksVO.setIsPresent(marks[1].toString());
							if(!ObjectFunctions.isNullOrEmpty(subjHighest.get(marks[2].toString()+"_"+marks[4].toString())))
								studentMarksVO.setSubjectHighestMarks(subjHighest.get(marks[2].toString()+"_"+marks[4].toString()));
							else
								studentMarksVO.setSubjectHighestMarks(0);
							studentMarksVOList.add(studentMarksVO);
							studentMarksVO = null;
						}
						studentMarksMainVO = new StudentMarksMainVO();
						studentMarksMainVO.setStudentMarksVOList(studentMarksVOList);
						return studentMarksMainVO;
					}else{
						return null;
					}
				}
				else{
					return null;
				}
			}
			else{
				return null;
			}
		}
				else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return null;
		}
	}
	public void updateTcGenerateInActiveStudents(String wishTitle, long custId) {
		studentDao.updateTcGenerateInActiveStudents(wishTitle, custId);
	}
	public void sendNotificationForAttendance(User studentUser, String status, long studentId, long classId)
	{
		try
		{
			if(!ObjectFunctions.isNullOrEmpty(studentUser.getStudentParent()) && !ObjectFunctions.isNullOrEmpty(studentUser.getStudentParent().getId())){
			StringBuffer accountIdbuffer = new StringBuffer("(");
			accountIdbuffer.append(studentUser.getStudentParent().getId());
			accountIdbuffer.append(",");
			
			//For class staff and Admin, HOD, Principal, Vice Principal, Admin Officer
			List<String> staffIds = this.getAll("SELECT GROUP_CONCAT(ct.teacherId) FROM classTeacher ct WHERE ct.studyClassId ="+classId);
			String whereClause = "staffId IN("+staffIds.get(0)+") OR (roleId IN(1, 8, 12, 31, 32) AND custId = "+studentUser.getCustId()+")";
			List<String> staffAccIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_staffDetails vs WHERE "+whereClause);
			accountIdbuffer.append(staffAccIds.get(0));
			accountIdbuffer.append(")");
			
			List<StudentDailyAttendanceVO> studentDailyAttendanceVOs = new ArrayList<StudentDailyAttendanceVO>();
			StudentDailyAttendanceVO dailyAttendanceVO = null;
			try {
				dailyAttendanceVO = new StudentDailyAttendanceVO();
				if("A".equalsIgnoreCase(status))
					dailyAttendanceVO.setMorningSession("A");
				else if("P".equalsIgnoreCase(status))
					dailyAttendanceVO.setMorningSession("P");
				dailyAttendanceVO.setStudentId(studentId);
				studentDailyAttendanceVOs.add(dailyAttendanceVO);
				dailyAttendanceVO = null;
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			main.put("notificationFor", "Attendance");
			if("A".equalsIgnoreCase(status))
			{
				subVal.put("description", "Today your child "+studentUser.getFullPersonName()+" is absent.");
				subVal.put("title", "Today your child "+studentUser.getFullPersonName()+" is absent.");
			}
			if("P".equalsIgnoreCase(status))
			{
				subVal.put("description", "Your child "+studentUser.getFullPersonName()+"  has just reached to school. Please ignore the absent message which you received earlier.");
				subVal.put("title", "Your child "+studentUser.getFullPersonName()+"  has just reached to school. ");
			}
			subVal.put("studentDailyAttendance",new JSONArray(new Gson().toJson(studentDailyAttendanceVOs)));
			main.put("information", subVal);
			this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
		}
		}
		catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
	}
	public void sendNotificationForAttendance(User studentUser, String morningAttendanceStatus, String afterNoonAttendanceStatus,long studentId, long classId)
	{
		try
		{
			if(!ObjectFunctions.isNullOrEmpty(studentUser.getStudentParent()) && !ObjectFunctions.isNullOrEmpty(studentUser.getStudentParent().getId())){
			StringBuffer accountIdbuffer = new StringBuffer("(");
			accountIdbuffer.append(studentUser.getStudentParent().getId());
			accountIdbuffer.append(",");
			
			//For class staff and Admin, HOD, Principal, Vice Principal, Admin Officer
			List<String> staffIds = this.getAll("SELECT GROUP_CONCAT(ct.teacherId) FROM classTeacher ct WHERE ct.studyClassId ="+classId);
			String whereClause = "staffId IN("+staffIds.get(0)+") OR (roleId IN(1, 8, 12, 31, 32) AND custId = "+studentUser.getCustId()+")";
			List<String> staffAccIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_staffDetails vs WHERE "+whereClause);
			accountIdbuffer.append(staffAccIds.get(0));
			accountIdbuffer.append(")");
			
			List<StudentDailyAttendanceVO> studentDailyAttendanceVOs = new ArrayList<StudentDailyAttendanceVO>();
			StudentDailyAttendanceVO dailyAttendanceVO = null;
			try {
				dailyAttendanceVO = new StudentDailyAttendanceVO();
				if("A".equalsIgnoreCase(morningAttendanceStatus))
					dailyAttendanceVO.setMorningSession("A");
				else if("P".equalsIgnoreCase(morningAttendanceStatus))
					dailyAttendanceVO.setMorningSession("P");
				if("A".equalsIgnoreCase(afterNoonAttendanceStatus))
					dailyAttendanceVO.setAfternoonSession("A");
				else if("P".equalsIgnoreCase(afterNoonAttendanceStatus))
					dailyAttendanceVO.setAfternoonSession("P");
				
				dailyAttendanceVO.setStudentId(studentId);
				studentDailyAttendanceVOs.add(dailyAttendanceVO);
				dailyAttendanceVO = null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			main.put("notificationFor", "Attendance");
			
			if("A".equalsIgnoreCase(morningAttendanceStatus) && "A".equalsIgnoreCase(afterNoonAttendanceStatus)){
				subVal.put("description", "Today your child "+studentUser.getFullPersonName()+" is absent.");
				subVal.put("title", "Today your child "+studentUser.getFullPersonName()+" is absent.");
			}else if("P".equalsIgnoreCase(morningAttendanceStatus)&& "P".equalsIgnoreCase(afterNoonAttendanceStatus))
			{
				subVal.put("description", "Your child "+studentUser.getFullPersonName()+"  has just reached to school. Please ignore the absent message which you received earlier.");
				subVal.put("title", "Your child "+studentUser.getFullPersonName()+"  has just reached to school. ");
			} else 	if("A".equalsIgnoreCase(morningAttendanceStatus) && "P".equalsIgnoreCase(afterNoonAttendanceStatus))
			{
				subVal.put("description", "Today your child "+studentUser.getFullPersonName()+"is absent for Morning session.");
				subVal.put("title", "Today your child "+studentUser.getFullPersonName()+"is absent for Morning session.");
			}else if("P".equalsIgnoreCase(morningAttendanceStatus)&& "A".equalsIgnoreCase(afterNoonAttendanceStatus))
			{
				subVal.put("description", "Today your child "+studentUser.getFullPersonName()+"is absent for for Afternoon session.");
				subVal.put("title", "Today your child "+studentUser.getFullPersonName()+"is absent for Afternoon session.");
			}
			
			subVal.put("studentDailyAttendance",new JSONArray(new Gson().toJson(studentDailyAttendanceVOs)));
			main.put("information", subVal);
			this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
		}
		}
		catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
	}
	public void sendNotificationForStudentUpdate(Student curstudent)
	{
		try
		{
			StudentDetailsListVO studentDetailsListVO=null;
			StudentDetailsVO studentDetailsVo=null;
			List<StudentDetailsVO> viewStudentPersonAccountDetailsVOs = new ArrayList<StudentDetailsVO>();
			AddressVO addressVo=null;
			List<ViewStudentPersonAccountDetails> studentsList =null;
			//studentsList =  this.getAllHqlQuery("FROM ViewStudentPersonAccountDetails vs WHERE vs.studentId = "+ curstudent.getId() + " AND vs.status = 'Y'");
			studentsList =  this.getAllHqlQuery("FROM ViewStudentPersonAccountDetails vs WHERE vs.studentId = "+ curstudent.getId());
			if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
				for(ViewStudentPersonAccountDetails student : studentsList) {
					studentDetailsVo=new StudentDetailsVO(); 
					studentDetailsVo.setStudentId(student.getStudentId());
					studentDetailsVo.setAccountId(student.getAccountId());
					studentDetailsVo.setCustId(student.getCustId());
					studentDetailsVo.setAcademicYearId(student.getAcademicYearId());
					studentDetailsVo.setFirstName(student.getFirstName());
					studentDetailsVo.setLastName(ObjectFunctions.isNullOrEmpty(student.getLastName())? "": student.getLastName());
					studentDetailsVo.setGender(ObjectFunctions.isNullOrEmpty(student.getGender())? "": student.getGender());
					studentDetailsVo.setClassSectionId(student.getClassSectionId());
					studentDetailsVo.setAdmissionNumber(ObjectFunctions.isNullOrEmpty(student.getAdmissionNumber())? "": student.getAdmissionNumber());
					studentDetailsVo.setRollNumber(String.valueOf(student.getRollNumber()));
					studentDetailsVo.setStreet(ObjectFunctions.isNullOrEmpty(student.getStreetName())? "": student.getStreetName());
					studentDetailsVo.setCity(ObjectFunctions.isNullOrEmpty(student.getCity())? "": student.getCity());
					studentDetailsVo.setStateName(ObjectFunctions.isNullOrEmpty(student.getStateName())? "": student.getStateName());
					studentDetailsVo.setPostalCode(ObjectFunctions.isNullOrEmpty(student.getPostalCode())? "": student.getPostalCode());
					studentDetailsVo.setMobileNumber(ObjectFunctions.isNullOrEmpty(student.getMobileNumber())? "": student.getMobileNumber());
					studentDetailsVo.setParentEmail(ObjectFunctions.isNullOrEmpty(student.getParentEmail())? "": student.getParentEmail());
					studentDetailsVo.setDateOfBirth(ObjectFunctions.isNullOrEmpty(student.getDateOfBirth())? DateFunctions.convertDateToString(DateUtils.addDays(new Date(), -1)): DateFunctions.convertDateToString(student.getDateOfBirth()));
					studentDetailsVo.setStatus(student.getStatus());
					studentDetailsVo.setDescription(ObjectFunctions.isNullOrEmpty(student.getDescription())? "": student.getDescription());
					studentDetailsVo.setImageId(ObjectFunctions.isNullOrEmpty(student.getImageId())? 0: student.getImageId());
					studentDetailsVo.setStudentMobile(ObjectFunctions.isNullOrEmpty(student.getStudentMobile())? "": student.getStudentMobile());
					studentDetailsVo.setTransportMode(ObjectFunctions.isNullOrEmpty(student.getTransportMode())? "": student.getTransportMode());
					studentDetailsVo.setBoardingPointId(ObjectFunctions.isNullOrEmpty(student.getBoardingPointId())? 0: student.getBoardingPointId());
					studentDetailsVo.setImageUrl(ObjectFunctions.isNullOrEmpty(student.getImagePath())? "": student.getImagePath());
					studentDetailsVo.setStsNumber(ObjectFunctions.isNullOrEmpty(student.getStsNumber())? "": student.getStsNumber());
					studentDetailsVo.setAadharNumber(ObjectFunctions.isNullOrEmpty(student.getAadharNumber())? "": student.getAadharNumber());
					studentDetailsVo.setParentId(ObjectFunctions.isNullOrEmpty(student.getParentId())? 0: Long.valueOf(student.getParentId()));
					viewStudentPersonAccountDetailsVOs.add(studentDetailsVo);
				}
				studentDetailsListVO = new StudentDetailsListVO();
				studentDetailsListVO.setViewStudentPersonAccountDetailsVOs(viewStudentPersonAccountDetailsVOs);
			
				//Sending Notiifcation to Parent
				if(!ObjectFunctions.isNullOrEmpty(curstudent.getAccount().getStudentParent()) && !ObjectFunctions.isNullOrEmpty(curstudent.getAccount().getStudentParent().getId()))
				{
					StringBuffer accountIdbuffer = new StringBuffer("(");
					if(!ObjectFunctions.isNullOrEmpty(curstudent.getAccount().getStudentParent())){
						accountIdbuffer.append(curstudent.getAccount().getStudentParent().getId());
						accountIdbuffer.append(")");
					}
					JSONObject main = new JSONObject();
					JSONObject subVal = new JSONObject();
					main.put("notificationFor", "Student Profile Update");
					subVal.put("description", "Your child "+curstudent.getAccount().getFullPersonName()+" details are updated.");
					subVal.put("title", "Your child profile updated");
					subVal.put("viewStudentPersonAccountDetailsVOs",new JSONArray(new Gson().toJson(viewStudentPersonAccountDetailsVOs)));
					main.put("information", subVal);
					this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
				}
				//Sending Notiifcation to Staff
				if(!ObjectFunctions.isNullOrEmpty(curstudent.getClassSectionId()))
				{
					List<String> staffIds = this.getAll("SELECT GROUP_CONCAT(ct.teacherId) FROM classTeacher ct WHERE ct.studyClassId IN("+curstudent.getClassSectionId()+")");	
					if(!ObjectFunctions.isNullOrEmpty(staffIds))
					{
						List<String> staffAccountIds = this.getAll("SELECT GROUP_CONCAT(s.accountId) FROM staff s WHERE s.id IN ("+staffIds.get(0)+")");
						List<String>  allStaffAccountIds = this.getAll("SELECT GROUP_CONCAT(accountId) FROM vw_staffDetails WHERE roleId IN(1, 8, 12, 31) AND custId = "+curstudent.getCustId());
						if(!ObjectFunctions.isNullOrEmpty(staffAccountIds))
						{
							if(!ObjectFunctions.isNullOrEmpty(staffAccountIds.get(0)))
							{
								try{
								StringBuffer accountIdbuffer = new StringBuffer("(");
								accountIdbuffer.append(staffAccountIds.get(0).toString());
								if(!ObjectFunctions.isNullOrEmpty(allStaffAccountIds.get(0).toString())){
									accountIdbuffer.append(",");
									accountIdbuffer.append(allStaffAccountIds.get(0).toString());
								}
								accountIdbuffer.append(")");
								JSONObject main = new JSONObject();
								JSONObject subVal = new JSONObject();
								main.put("notificationFor", "Student Profile Update");
								subVal.put("description", "Your student "+curstudent.getAccount().getFullPersonName()+" details are updated.");
								subVal.put("title", "Your student profile updated");
								subVal.put("viewStudentPersonAccountDetailsVOs",new JSONArray(new Gson().toJson(viewStudentPersonAccountDetailsVOs)));
								main.put("information", subVal);
								this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
								}
								catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void updateParentSecondaryUsername(long accountId){
		studentDao.updateParentSecondaryUsername(accountId);
	}
	
	public SMSBaseVO updateProfileFromApp(long userId, InputStream uploadedInputStream,String fileName)
	{
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null; 
		int responseCode=0;
		try{
			User luser=((User)this.get(User.class,userId));
			Customer customer=(Customer)this.get(Customer.class, luser.getCustId());
			AcademicYear academicYear = this.getCurrentAcademicYear("Y", customer.getId());
			long userImageId = 0;
			StringBuffer pathName = new StringBuffer();
			UserImage profileImage=null;
			List<String> studyClassIds = null;
			List<String> staffIds =null;
			List<Student> students=null;
			
			if(fileName!= null){
				if(luser.getRoleName().equalsIgnoreCase(Constants.SCHOOL_STUDENT)){
					students = this.getAll(Student.class,"accountId="+userId+" AND status='Y'");
		   			if(!ObjectFunctions.isNullOrEmpty(students)){
		   				if(!ObjectFunctions.isNullOrEmpty(students.get(0).getProfileImage())){
		   					userImageId = students.get(0).getProfileImage().getId();
		   				}
		   			}
				}
				else{
					if(!ObjectFunctions.isNullOrEmpty(luser.getProfileImage()))
					userImageId = luser.getProfileImage().getId();
				}
				if(!ObjectFunctions.isNullOrEmpty(customer))
				{
					pathName.append("/userFiles").append("/userimages/").append(customer.getId()).append("/").append(academicYear.getId());
					pathName.append("/");
					if(userImageId > 0) 
					{
			        	profileImage = (UserImage)this.get(UserImage.class, "id="+userImageId);
			        }
					else{
						profileImage = new UserImage();
						profileImage = (UserImage)this.saveOrUpdateObject(profileImage);
					}
				    File dir = new File(SpringContextAware.getRealPath(pathName.toString()));
				    if (!dir.exists()) 
				    dir.mkdirs();
				    fileName=String.valueOf(profileImage.getId())+".jpg";
				    String path =SpringContextAware.getRealPath(pathName+fileName);
				    String ext = FilenameUtils.getExtension(fileName);
		   		 	 profileImage.setName(fileName);   
			         profileImage.setThumbNail(fileName);
			         profileImage.setPath(pathName.toString());
			         profileImage.setType(ext);
			         profileImage = (UserImage)this.saveOrUpdateObject(profileImage);
		   			 try {
		   				saveToFile(uploadedInputStream,path);
		   			 }
		   			catch (IOException e) {
						responseCode = 1;
					}
			   		profileImage = (UserImage) this.saveOrUpdateObject(profileImage);
			   		smsBaseVO.setIdentifier(new KeyIdentifierVO(String.valueOf(profileImage.getId())));
			   		if(luser.getRoleName().equalsIgnoreCase(Constants.SCHOOL_STUDENT)){
			   			if(!ObjectFunctions.isNullOrEmpty(students)){
			   				students.get(0).setProfileImage(profileImage);
			   				this.save(students.get(0));
			   			    this.sendNotificationForStudentUpdate(students.get(0));
				         }
					 }
					 else 
					 {
						 luser.setProfileImage(profileImage);
						 this.save(luser);// Other than student saving to account obj
						 staffIds = this.getAll("SELECT GROUP_CONCAT(st.id) FROM staff st WHERE st.accountId IN("+userId+")");
						 if(!ObjectFunctions.isNullOrEmpty(staffIds)){
							 super.sendStaffNotificationWhenEditOrAdd(Long.valueOf(staffIds.get(0)), "Staff Profile Update", "Staff Profile Update");
						 }
					 }
				}
			
			}
		}
		catch(Exception e){
			e.printStackTrace();
			responseCode=2;
		}
		if(responseCode == 0)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else if(responseCode == 1)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorCode(),ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorDesc());
		else if(responseCode == 2)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
		    
		smsBaseVO.setApiStatus(apistatusVO);
		 return smsBaseVO;
	}
	public void updateStudentUserName(long accountId,String password){
		studentDao.updateStudentUserName(accountId,password);
	}
	
	
	public SMSBaseVO updateUserImage(UserImageVO userImageVO)
	{
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		KeyIdentifierVO keyIdentifier = new KeyIdentifierVO();
		APIStatusVO apistatusVO = null;
		int responseCode=2;
		
		try {
			if(userImageVO.getId() >0)
			{
				this.updateQuery("update UserImage set path='"+userImageVO.getPath()+"' where id="+userImageVO.getId());
				keyIdentifier.setChannel(String.valueOf(userImageVO.getId()));
				responseCode = 0;
			}
			else
			{
				UserImage profileImage = new UserImage();
				
				Object[] userRoles = this.get("select accountId,roleName from vw_userRoles where accountId="+userImageVO.getIdentifier().getAccountId());
				if(!ObjectFunctions.isNullOrEmpty(userRoles))
				{
					if("ROLE_STUDENT".equalsIgnoreCase(userRoles[1].toString()))
					{
						Object[] studnetObj = this.get("select id,IFNULL(imageId,0) as imageId from student where accountId="+userImageVO.getIdentifier().getAccountId()+" and academicYearId="+userImageVO.getIdentifier().getAcademicYearId());
						log.info("inamge start :"+new Date());
						Student student = (Student)this.get(Student.class, Long.valueOf(studnetObj[0].toString()));
						log.info("inamge End :"+new Date());
						if(!ObjectFunctions.isNullOrEmpty(student))
						{
							if(!ObjectFunctions.isNullOrEmpty(student.getProfileImage()))
							{
								profileImage=student.getProfileImage();
								profileImage.setPath(userImageVO.getPath());
								this.save(profileImage);
							}else{
								profileImage.setPath(userImageVO.getPath());
								profileImage = (UserImage)this.saveOrUpdateObject(profileImage);
								student.setProfileImage(profileImage);
								this.save(student);
							}
							keyIdentifier.setChannel(String.valueOf(profileImage.getId()));
							responseCode = 0;
			   			    this.sendNotificationForStudentUpdate(student);
						}
						else
							log.debug("Student Not Found..");
					}
					else
					{
						List<String> staffIds =null;
						User luser=((User)this.get(User.class,userImageVO.getIdentifier().getAccountId()));
						if(!ObjectFunctions.isNullOrEmpty(luser))
						{
							if(!ObjectFunctions.isNullOrEmpty(luser.getProfileImage()))
							{
								profileImage=luser.getProfileImage();
							}
							profileImage.setPath(userImageVO.getPath());
							profileImage = (UserImage)this.saveOrUpdateObject(profileImage);
							keyIdentifier.setChannel(String.valueOf(profileImage.getId()));
							luser.setProfileImage(profileImage);
							this.save(luser);
							responseCode = 0;
							staffIds = this.getAll("SELECT GROUP_CONCAT(st.id) FROM staff st WHERE st.accountId IN("+userImageVO.getIdentifier().getAccountId()+")");
							if(!ObjectFunctions.isNullOrEmpty(staffIds))
							{
								super.sendStaffNotificationWhenEditOrAdd(Long.valueOf(staffIds.get(0)), "Staff Profile Update", "Staff Profile Update");
							}
						}
						else
							log.debug("Account Not Found..");
					}
				}
				
			}
		} catch (Exception e) {
			responseCode=2;
			e.printStackTrace();
		}
		
		if(responseCode == 0)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
		else if(responseCode == 1)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorCode(),ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorDesc());
		else if(responseCode == 2)
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
		smsBaseVO.setApiStatus(apistatusVO);
		smsBaseVO.setIdentifier(keyIdentifier);
		 
		 return smsBaseVO;
	}
	
	public Map<String,String> addOrUpdateStudentAttendance(Long studentId,String attendanceDate, long academicYearId, long custId, long userId,String sms, long classId,String attendanceType) 
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addOrUpdateStudentAttendance' method");
		}
		Map<String, String> msg = new HashMap<String, String>();
		try {

			int monthNum;
			StudentDailyAttendance attendance = null;
			Customer customer = this.getCustomerByCustId(custId);
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			AcademicYear academicYear=(AcademicYear)dao.get(AcademicYear.class, academicYearId);
			User loggedUser = (User) this.get(User.class, userId);

			Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, attendanceDate);
			if (ObjectFunctions.isNullOrEmpty(aDate)) {
				aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,attendanceDate));
			}
			monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(aDate));
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
			String year = simpleDateformat.format(aDate);
			if (!ObjectFunctions.isNullOrEmpty(aDate)) {
				List<SchoolHolidays> holidaysList=null;  //below classId used to get the holidays for class wise. if school have class wise holidays in school settings tab
				if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()) && classId > 0){
					Object[] classNameClassIds= dao.get("select classId,className from vw_classSectionDetails where custId="+custId+ " and academicYearId="+academicYearId+" and classSectionId="+classId);
					if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
						holidaysList = this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate),null,null,classNameClassIds[0].toString(),null,null,monthNum,"holidayDateEqual",year);
					}
				}else{
					holidaysList = this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,aDate),null,null,null,null,null,monthNum,"holidayDateEqual",year);
				}
				if (!ObjectFunctions.isNullOrEmpty(holidaysList)) {
					msg.put("1", "Today Is Holiday");
					return msg;
				}
			}
			String captureAttendance = academicYear.getCaptureAttendanceBy();
			Student student = null;
			Set<String> mobileNumbersSet = null;
			Messages objMsg = null;
			Date todayDate = new Date();
			Map<Messages, List> messageMobileMap = new HashMap<Messages, List>();

			student = (Student) this.get(Student.class,"custId="+custId+" and academicYearId="+academicYearId+" and id="+studentId+" and description is null");
			User studentUser = null;
			if (!ObjectFunctions.isNullOrEmpty(student)) {
				studentUser = student.getAccount();
			}

			if(!ObjectFunctions.isNullOrEmpty(studentUser)) {
			
				StudentDailyAttendanceTimeTrack studentTimeTrack = (StudentDailyAttendanceTimeTrack) this.get(StudentDailyAttendanceTimeTrack.class,"custId="+custId+
						" and attendanceDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,aDate)+"' and academicYearId="+academicYearId+" and studentId="+studentId);

				if(ObjectFunctions.isNullOrEmpty(studentTimeTrack)){
					studentTimeTrack = new StudentDailyAttendanceTimeTrack();
					studentTimeTrack.setAcademicYearId(academicYearId);
					studentTimeTrack.setCustId(custId);
					studentTimeTrack.setStudyClassId(classId);
					studentTimeTrack.setCreatedById(userId);
					studentTimeTrack.setCreatedDate(new Date());
					studentTimeTrack.setAttendanceDate(aDate);
					studentTimeTrack.setLastUpdatedDate(new Date());
					studentTimeTrack.setLastAccessDate(new Date());
					studentTimeTrack.setStudentId(studentId);
					studentTimeTrack.setInTime(new Date());
				}else{
					studentTimeTrack.setLastUpdatedDate(new Date());
					studentTimeTrack.setLastAccessDate(new Date());
					studentTimeTrack.setOutTime(new Date());
				}
				studentTimeTrack = (StudentDailyAttendanceTimeTrack) this.merge(studentTimeTrack);

				if(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate).equals(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate))){
					if(customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(sms)){
						mobileNumbersSet = new HashSet<String>();
						String  mobileType = customer.getMobileType();
						mobileNumbersSet.addAll(this.addMobileNumbersBasedOnAddressType(mobileType,StringFunctions.getMobileNumberLengthChecking(
								studentUser.getPerson().getMobileNumber()),StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getSecondaryMobileNumber()),
								StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherMobileNumber()),
								StringFunctions.getMobileNumberLengthChecking(studentUser.getPerson().getAnotherSecondaryMobileNumber()),studentUser.getPerson().getAddressType()));
						objMsg = new Messages();
						objMsg.setCreatedById(userId);
						objMsg.setCreatedDate(new Date());
						objMsg.setLastAccessDate(new Date());
						objMsg.setLastUpdatedDate(new Date());
						objMsg.setStatus("P");
						objMsg.setAcademicYear(academicYear);
						objMsg.setSenderName(loggedUser.getUserRoleDescription());
						if (!ObjectFunctions.isNullOrEmpty(customer)) {
							objMsg.setCustomer(customer);
						}
						objMsg.setSmsSenderId(customer.getSender());

						if("IN".equalsIgnoreCase(attendanceType))	{		
							objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" is just reached to "+customer.getSchoolOrCollege()+".");
						}else{
							objMsg.setMessageDescription("Dear Parent, your child "+studentUser.getFullPersonName()+" is just left from the "+customer.getSchoolOrCollege()+".");
						}
						if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet) && mobileNumbersSet.size()!=0 && !ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){

							objMsg = this.saveMessageDetailsTracking(objMsg,student,null,null);

							boolean	smsStatus = this.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
						}	 
					}					

					if(!StringFunctions.isNullOrEmpty(sms))
					{
						if("SMS".equalsIgnoreCase(sms))
							msg.put("0", "Attendance recorded successfully and SMS sent successfully.");
						else
							msg.put("0", "Attendance recorded successfully.");
					}
					else{
						msg.put("0", "Attendance recorded successfully.");
					}

				}else{
					msg.put("0", "Attendance recorded successfully.");	
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return msg;
	}
	
	/**
	 * This Method used to submit preschool students attendace .
	 */
	public Map<String, String> addOrUpdateStudentAttendance(
			PreSchoolStudentAttendanceVO preSchoolStudentAttendanceVO) {
		
		Long studentId = preSchoolStudentAttendanceVO.getStudentAttendance().getStudentId();
		String attendanceDate =preSchoolStudentAttendanceVO.getStudentAttendance().getDate();
		Long custId = preSchoolStudentAttendanceVO.getIdentifier().getCustId();
		Long userId = preSchoolStudentAttendanceVO.getIdentifier().getAccountId();
		Long academicYearId =  preSchoolStudentAttendanceVO.getIdentifier().getAcademicYearId();
		Long studyClassId = preSchoolStudentAttendanceVO.getStudyClassId();
		String attendanceType = "IN";
		
		if(!StringFunctions.isNullOrEmpty(preSchoolStudentAttendanceVO.getStudentAttendance().getOutTime())){
			attendanceType = "OUT";
		}
        int smsCount = this.getTotalSmsCount(custId,academicYearId);
        int smsAlloted = 0;
        AcademicYear academicYear = getCurrentAcademicYear("Y",custId);
		if(!ObjectFunctions.isNullOrEmpty(academicYear)){
			 smsAlloted = ((int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms());
		}
		String smsType = null;
		if(!(smsAlloted == 0 || smsAlloted <=smsCount )){
			smsType = "SMS";
		}
		return addOrUpdateStudentAttendance(studentId,attendanceDate,academicYearId, custId, userId,smsType, studyClassId,attendanceType);
		
	}
	/**
	 * This Method used to get the todays preschool students attendace details.
	 */
	@Override
	public StudentAttendanceListVO getPreschoolStudentDailyAttendance(
			long academicYearId, long studyClassId) {
		 
		StudentAttendanceListVO studentAttendanceListVO = new StudentAttendanceListVO();
		SMSBaseVO baseVO = new SMSBaseVO();
		Long custId = null ;
		List<StudentDailyAttendanceTimeTrack> studentTimeTrackList = (List<StudentDailyAttendanceTimeTrack>) this.getAll(StudentDailyAttendanceTimeTrack.class,"studyClassId="+studyClassId+
				" and attendanceDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,new Date())+"' and academicYearId="+academicYearId);
		studentAttendanceListVO.setStudyClassId(studyClassId);
		studentAttendanceListVO.getIdentifier().setAcademicYearId(academicYearId);
		List<StudentAttendanceVO> studentAttendanceList  = new ArrayList<StudentAttendanceVO>();
		if(!ObjectFunctions.isNullOrEmpty(studentTimeTrackList)) {
			StudentAttendanceVO studentAttendanceVO = null;
			for(StudentDailyAttendanceTimeTrack attendanceTimeTrack:studentTimeTrackList){
				studentAttendanceVO = new StudentAttendanceVO();
				studentAttendanceVO.setDate(DateFunctions.convertDateToString(attendanceTimeTrack.getAttendanceDate()));
				studentAttendanceVO.setInTime(DateFunctions.convertDateToString(attendanceTimeTrack.getInTime()));
				studentAttendanceVO.setOutTime(DateFunctions.convertDateToString(attendanceTimeTrack.getOutTime()));
				studentAttendanceVO.setStudentId(attendanceTimeTrack.getStudentId());
				studentAttendanceList.add(studentAttendanceVO);
				custId =attendanceTimeTrack.getCustId();
				studentAttendanceVO = null;
			}
			studentAttendanceListVO.getIdentifier().setCustId(custId);
			studentAttendanceListVO.setStudentAttendance(studentAttendanceList);
			return studentAttendanceListVO;
		}else{
			return studentAttendanceListVO;
		}

		
	}
	
	/**
	 * This Method used to send the attendance email to parent email
	 * @param loggedUser
	 * @param customer
	 * @param aDate
	 * @param studentName
	 * @param parentEmail
	 * @param organzationLeavel
	 * @param captureAttendance
	 * @param attendanceStatus
	 * @param morningAttStatus
	 * @param astatus
	 * @param afternoonAttStatus
	 * @param objMsg
	 * @param attendance
	 */
	public void sendAttendanceEmail(User loggedUser,Customer customer,Date aDate ,String studentName ,String parentEmail ,String organzationLeavel,String captureAttendance,String attendanceStatus,
			String morningAttStatus,String astatus,String afternoonAttStatus,Messages objMsg,StudentDailyAttendance attendance ){

		String message = "";
		if(!ObjectFunctions.isNullOrEmpty(objMsg) && !ObjectFunctions.isNullOrEmpty(objMsg.getMessageDescription())){
			message = objMsg.getMessageDescription();
		}else{
			if(("P".equalsIgnoreCase(attendanceStatus) && !ObjectFunctions.isNullOrEmpty(attendance) && "O".equalsIgnoreCase(captureAttendance)) || 
					("T".equalsIgnoreCase(captureAttendance) && !ObjectFunctions.isNullOrEmpty(attendance) && "P".equalsIgnoreCase(attendanceStatus) && "P".equalsIgnoreCase(astatus))){
				if("T".equalsIgnoreCase(captureAttendance)){
					if(("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus)) && (!"N".equalsIgnoreCase(afternoonAttStatus) && !"N".equalsIgnoreCase(morningAttStatus)))
						message ="Dear Parent, your child "+studentName+" has just reached to "+customer.getSchoolOrCollege()+" for Afternoon Session. Please ignore the absent message which you received previously.";
					else if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus) && (!"N".equalsIgnoreCase(afternoonAttStatus) && !"N".equalsIgnoreCase(morningAttStatus)))
						message = "Dear Parent, your child "+studentName+" has just reached to "+customer.getSchoolOrCollege()+" for Morning Session. Please ignore the absent message which you received previously.";
					else
					{
						if("N".equalsIgnoreCase(morningAttStatus))
						{
							message ="Dear Parent, your child "+studentName+" has just reached to "+customer.getSchoolOrCollege()+" for Morning session. Please ignore the absent message which you received previously.";
						}
						else if("N".equalsIgnoreCase(afternoonAttStatus))
						{
							message = "Dear Parent, your child "+studentName+" has just reached to "+customer.getSchoolOrCollege()+" for Afternoon session. Please ignore the absent message which you received previously.";
						}
						else{
							message ="Dear Parent, your child "+studentName+" has just reached to "+customer.getSchoolOrCollege()+". Please ignore the absent message which you received previously. ";
						}
					}
				}else{
					message = "Dear Parent, your child "+studentName+" has just reached to "+customer.getSchoolOrCollege()+". Please ignore the absent message which you received previously. ";
				}
			}
			else{
				if("T".equalsIgnoreCase(captureAttendance)){
					if("S".equalsIgnoreCase(organzationLeavel)){
						//student at first time present from to absent to morning session
						if(("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)) && ((!"A".equalsIgnoreCase(astatus) 
								&& StringFunctions.isNullOrEmpty(afternoonAttStatus)))){
							message = "Dear Parent, Today your child "+studentName+" is absent for Morning session."; 
						}else	if(("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ((!"A".equalsIgnoreCase(attendanceStatus) 
								&& StringFunctions.isNullOrEmpty(morningAttStatus)))){//student at first time present from to absent to afternoon session
							message = "Dear Parent, Today your child "+studentName+" is absent for Afternoon session.";
						}else  if (("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ("A".equalsIgnoreCase(attendanceStatus) 
								&& StringFunctions.isNullOrEmpty(morningAttStatus))){
							message = "Dear Parent, Today your child "+studentName+" is absent.";
						}else 	 if(!StringFunctions.isNullOrEmpty(afternoonAttStatus) && !StringFunctions.isNullOrEmpty(morningAttStatus)){
							if("A".equalsIgnoreCase(attendanceStatus) && "Y".equalsIgnoreCase(morningAttStatus)){
								message = "Dear Parent, Today your child "+studentName+" is absent for Morning session.";
							}else  if("A".equalsIgnoreCase(astatus) && "Y".equalsIgnoreCase(afternoonAttStatus)){
								message ="Dear Parent, Today your child "+studentName+" is absent for Afternoon session.";
							}else if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus)){ // student at first time is absent and present for second time in morning session
								message = "Dear Parent, your child "+studentName+" has just reached to school for Morning session. Please ignore the absent message which you received previously.";
							}else  if("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus)) {// // student at first time is absent and present for second time in afternoon session
								message =  "Dear Parent, your child "+studentName+" has just reached to school for Afternoon session. Please ignore the absent message which you received previously.";
							}else   if (("A".equalsIgnoreCase(astatus) && "A".equalsIgnoreCase(attendanceStatus)) && (!"N".equalsIgnoreCase(morningAttStatus) 
									&& !"N".equalsIgnoreCase(afternoonAttStatus))){//// student at first time and second time also absent for both session
								message = "Dear Parent, Today your child "+studentName+" is absent.";
							}
						}
					}else{
						//student at first time present from to absent to morning session
						if(("A".equalsIgnoreCase(attendanceStatus) && StringFunctions.isNullOrEmpty(morningAttStatus)) && ((!"A".equalsIgnoreCase(astatus) 
								&& StringFunctions.isNullOrEmpty(afternoonAttStatus)))){
							message = "Dear Parent, Today your child "+studentName+" is absent for Morning session. Please contact college if your child has already started to college.";
						}else if(("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ((!"A".equalsIgnoreCase(attendanceStatus)
								&& StringFunctions.isNullOrEmpty(morningAttStatus)))){////student at first time present from to absent to afternoon session
							message = "Dear Parent, Today your child "+studentName+" is absent for Afternoon session. Please contact college if your child has already started to college.";
						}else  if (("A".equalsIgnoreCase(astatus) && StringFunctions.isNullOrEmpty(afternoonAttStatus)) && ("A".equalsIgnoreCase(attendanceStatus) 
								&& StringFunctions.isNullOrEmpty(morningAttStatus))) {
							message = "Dear Parent, Today your child "+studentName+" is absent. Please contact college if your child has already started to college. ";
						}else  if(!StringFunctions.isNullOrEmpty(afternoonAttStatus) && !StringFunctions.isNullOrEmpty(morningAttStatus)){

							if("A".equalsIgnoreCase(attendanceStatus) && "Y".equalsIgnoreCase(morningAttStatus)){
								message = "Dear Parent, Today your child "+studentName+" is absent for Morning session. Please contact college if your child has already started to college.";
							}else  if("A".equalsIgnoreCase(astatus) && "Y".equalsIgnoreCase(afternoonAttStatus)){
								message = "Dear Parent, Today your child "+studentName+" is absent for Afternoon session. Please contact college if your child has already started to college.";
							}else  if("P".equalsIgnoreCase(attendanceStatus) && "N".equalsIgnoreCase(morningAttStatus)){// // student at first time is absent and present for second time in morning session
								message = "Dear Parent, your child "+studentName+" has just reached to college for Morning Session. Please ignore the absent message which you received previously.";
							}else if("P".equalsIgnoreCase(astatus) && "N".equalsIgnoreCase(afternoonAttStatus)){// student at first time is absent and present for second time in afternoon session
								message = "Dear Parent, your child "+studentName+" has just reached to college for Afternoon Session. Please ignore the absent message which you received previously.";
							}else if (("A".equalsIgnoreCase(astatus) && "A".equalsIgnoreCase(attendanceStatus)) && (!"N".equalsIgnoreCase(morningAttStatus) &&
									!"N".equalsIgnoreCase(afternoonAttStatus))){// student at first time and second time also absent for both session
								message = "Dear Parent, Today your child "+studentName+" is absent. Please contact college if your child has already started to college. ";
							}
						}
					}
				}else{
					if("S".equalsIgnoreCase(organzationLeavel))
						message = "Dear Parent, Today your child "+studentName+" is absent.";
					else
						message = "Dear Parent, Today your child "+studentName+" is absent. Please contact college if your child has already started to college. ";
				}
			}


		}

		Set<String> emailIdsSet = new HashSet<String>();
		String[] emails =new String[1];
		emails[0] = parentEmail;
		emailIdsSet.add(parentEmail); 
		String subject = studentName +" attendance on " +new SimpleDateFormat("dd-MMM-YYYY").format(aDate);
		String body  = null;
		String content = message;

		if("T".equalsIgnoreCase(captureAttendance)){
			if(content.contains("Morning session")){
				if(content.contains("is absent")){
					body = "Your child "
							+ studentName
							+ " is absent for Morning session to school on "
							+ new SimpleDateFormat("dd-MMM-YYYY").format(aDate)
							+ ". Please make sure your child attend the school regularly.";
				}else{
					content = content.replace("Dear Parent,","").trim();
					content = content.replace("your child", "Your child");
					body = content;
				}
			}else{
				if(content.contains("is absent")){
					body = "Your child "
							+ studentName
							+ " is absent for Afternoon session to school on "
							+ new SimpleDateFormat("dd-MMM-YYYY").format(aDate)
							+ ". Please make sure your child attend the school regularly.";
				}else{
					content = content.replace("Dear Parent,","").trim();
					content = content.replace("your child", "Your child");
					body = content;
				}
			}
		}else{
			if(content.contains("is absent")){
				body = "Your child "
						+ studentName
						+ " is absent to school on "
						+ new SimpleDateFormat("dd-MMM-YYYY").format(aDate)
						+ ". Please make sure your child attend the school regularly.";
			}else{
				content = content.replace("Dear Parent,","").trim();
				content = content.replace("your child", "Your child");
				body = content;
			}
		}

		if(!ObjectFunctions.isNullOrEmpty(emails)){
			MailUtil mailUtil = new MailUtil(emails, subject, body, loggedUser,null, getContactFromEmail(customer));

			mailUtil.setContactEmail(customer.getContactEmail());
			mailUtil.setContactPasword(customer.getContactPassword());
			mailUtil.sendEmailForAttendance(getContactFromEmail(customer));
		}


	}
	@Override
	public List<Object[]> getAllMotherTongueWiseStudentSummaryDetails(Long custId,
			Long academicYearId, String classIds, String motherTongueIds) {
		
		return studentDao.getAllMotherTongueWiseStudentSummaryDetails(custId,academicYearId,classIds,motherTongueIds);
	}
	@Override
	public List getAllClassesWiseTCTakenStudentCountByCustId(long custId,
			long academicYearId, String classIds) {
		return studentDao.getAllClassesWiseTCTakenStudentCountByCustId(custId, academicYearId, classIds);
	}
	@Override
	public List getAllClassesWiseNewAdmittedStudentCountByCustId(long custId,
			long academicYearId, String classIds) {
		return studentDao.getAllClassesWiseNewAdmittedStudentCountByCustId(custId, academicYearId, classIds);
	}
}