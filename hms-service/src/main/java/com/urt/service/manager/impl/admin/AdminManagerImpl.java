package com.urt.service.manager.impl.admin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Sheet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hyniva.Amazon.S3Info;
import com.hyniva.Amazon.S3Wrapper;
import com.hyniva.Amazon.Types;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.AdmissionSettingsVO;
import com.hyniva.sms.ws.vo.AlbumAttachmentVO;
import com.hyniva.sms.ws.vo.AndroidMobileUsersVO;
import com.hyniva.sms.ws.vo.AttachmentVO;
import com.hyniva.sms.ws.vo.CasteSettingsMainVO;
import com.hyniva.sms.ws.vo.CasteSettingsVO;
import com.hyniva.sms.ws.vo.ClassNameMainVO;
import com.hyniva.sms.ws.vo.ClassNameVO;
import com.hyniva.sms.ws.vo.ClassSectionSubjectVO;
import com.hyniva.sms.ws.vo.ClassTeacherMainVO;
import com.hyniva.sms.ws.vo.ClassTeacherVO;
import com.hyniva.sms.ws.vo.CommonTypeMainVO;
import com.hyniva.sms.ws.vo.CommonTypeVO;
import com.hyniva.sms.ws.vo.DefaultScoreCardTemplatesVO;
import com.hyniva.sms.ws.vo.EventMainVO;
import com.hyniva.sms.ws.vo.EventsVO;
import com.hyniva.sms.ws.vo.ExamSchedulesMainVO;
import com.hyniva.sms.ws.vo.ExamSchedulesVO;
import com.hyniva.sms.ws.vo.ExamTypeVO;
import com.hyniva.sms.ws.vo.ExcessPaymentVO;
import com.hyniva.sms.ws.vo.FeeMainVO;
import com.hyniva.sms.ws.vo.FeeTypeMainVO;
import com.hyniva.sms.ws.vo.FeeTypeVO;
import com.hyniva.sms.ws.vo.FeeVO;
import com.hyniva.sms.ws.vo.LeaveVO;
import com.hyniva.sms.ws.vo.LeavesMainVO;
import com.hyniva.sms.ws.vo.LeavesVO;
import com.hyniva.sms.ws.vo.MeetingScheduleSlotsVO;
import com.hyniva.sms.ws.vo.MeetingSchedulesDetailsVO;
import com.hyniva.sms.ws.vo.MeetingSchedulesMainVO;
import com.hyniva.sms.ws.vo.MeetingSchedulesVO;
import com.hyniva.sms.ws.vo.MessagesMainVO;
import com.hyniva.sms.ws.vo.MessagesVO;
import com.hyniva.sms.ws.vo.PayslipBaseVO;
import com.hyniva.sms.ws.vo.PayslipVO;
import com.hyniva.sms.ws.vo.ReplyScrapMessageVO;
import com.hyniva.sms.ws.vo.RouteBoardingPointsVO;
import com.hyniva.sms.ws.vo.RouteMainVO;
import com.hyniva.sms.ws.vo.RouteNotifyVO;
import com.hyniva.sms.ws.vo.RouteTrackVO;
import com.hyniva.sms.ws.vo.RouteVo;
import com.hyniva.sms.ws.vo.RunCCReportsVO;
import com.hyniva.sms.ws.vo.SchoolCategoryMainVO;
import com.hyniva.sms.ws.vo.SchoolCategoryVO;
import com.hyniva.sms.ws.vo.SchoolHolidaysMainVO;
import com.hyniva.sms.ws.vo.SchoolHolidaysVO;
import com.hyniva.sms.ws.vo.SchoolTermsMainVO;
import com.hyniva.sms.ws.vo.SchoolTermsVO;
import com.hyniva.sms.ws.vo.ScrapMessageVO;
import com.hyniva.sms.ws.vo.ShareUserActivitiesVO;
import com.hyniva.sms.ws.vo.SocialDiscussionsVO;
import com.hyniva.sms.ws.vo.StaffElgibleSubjectsVO;
import com.hyniva.sms.ws.vo.StaffTimetablesVO;
import com.hyniva.sms.ws.vo.StudentFeeConcessionMainVO;
import com.hyniva.sms.ws.vo.StudentFeeConcessionVO;
import com.hyniva.sms.ws.vo.StudentFeeDetailsFeeTypeVO;
import com.hyniva.sms.ws.vo.StudentFeeDetailsVO;
import com.hyniva.sms.ws.vo.StudentFeePaidDetailsVO;
import com.hyniva.sms.ws.vo.StudentFeeTermsVO;
import com.hyniva.sms.ws.vo.StudentFeeVO;
import com.hyniva.sms.ws.vo.StudentIssuedBookDetailsVO;
import com.hyniva.sms.ws.vo.StudentLibraryBooksDetailsVO;
import com.hyniva.sms.ws.vo.StudentLibraryBooksVO;
import com.hyniva.sms.ws.vo.StudentMarksMainVO;
import com.hyniva.sms.ws.vo.StudentMarksVO;
import com.hyniva.sms.ws.vo.StudentMonthlyAttendanceMainVO;
import com.hyniva.sms.ws.vo.StudentMonthlyAttendanceVO;
import com.hyniva.sms.ws.vo.StudentPaymentMainVO;
import com.hyniva.sms.ws.vo.StudentPaymentVO;
import com.hyniva.sms.ws.vo.StudentScoreCardVO;
import com.hyniva.sms.ws.vo.StudentTimetableDayVO;
import com.hyniva.sms.ws.vo.StudentTimetableDetailsVO;
import com.hyniva.sms.ws.vo.StudentTimetablePeriodVO;
import com.hyniva.sms.ws.vo.StudentTimetableVO;
import com.hyniva.sms.ws.vo.StudyClassTimetablesVO;
import com.hyniva.sms.ws.vo.StudyClassVO;
import com.hyniva.sms.ws.vo.StudySubjectVO;
import com.hyniva.sms.ws.vo.SubCasteSettingsVO;
import com.hyniva.sms.ws.vo.SubTypeVO;
import com.hyniva.sms.ws.vo.UserAddressChangeVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.hyniva.sms.ws.vo.VehicleRouteMainVO;
import com.hyniva.sms.ws.vo.VehicleRouteVo;
import com.hyniva.sms.ws.vo.VehicleVO;
import com.hyniva.sms.ws.vo.ViewAllUsersMainVO;
import com.hyniva.sms.ws.vo.ViewAllUsersVO;
import com.hyniva.sms.ws.vo.ViewAssignedVehiclestoRoutesWithBoardingPointsVo;
import com.hyniva.sms.ws.vo.ViewCircularUsersVO;
import com.hyniva.sms.ws.vo.ViewClassAssignmentDetailsMainVO;
import com.hyniva.sms.ws.vo.ViewClassAssignmentDetailsVO;
import com.hyniva.sms.ws.vo.ViewRouteTrackDetailsVO;
import com.hyniva.sms.ws.vo.ViewRouteTrackMainVO;
import com.hyniva.sms.ws.vo.ViewRouteTrackVO;
import com.hyniva.sms.ws.vo.ViewRouteVehiclesMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPersonAccountDetailsMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPersonAccountDetailsVO;
import com.hyniva.sms.ws.vo.ViewStudentFeePaymentDetailsMainVO;
import com.hyniva.sms.ws.vo.ViewStudentFeePaymentDetailsVO;
import com.hyniva.sms.ws.vo.attendance.StudentAndStaffDailyAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StudentDailyAttendanceMainVO;
import com.hyniva.sms.ws.vo.attendance.StudentDailyAttendanceVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.KeyIdentifierVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.hyniva.sms.ws.vo.exam.QuestionPaperBankVO;
import com.hyniva.sms.ws.vo.fee.ChallanaPaymentVO;
import com.hyniva.sms.ws.vo.fee.ExcessPaymnetVO;
import com.hyniva.sms.ws.vo.fee.PaymentDetailsVO;
import com.hyniva.sms.ws.vo.fee.StudentFeePaymentVO;
import com.hyniva.sms.ws.vo.fee.StudentPaymentListVO;
import com.hyniva.sms.ws.vo.sports.AchievementVO;
import com.hyniva.sms.ws.vo.sports.PlayersVO;
import com.spring.context.SpringContextAware;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.interfaces.admin.AdminDao;
import com.urt.persistence.model.account.CustomerBankAccountDetails;
import com.urt.persistence.model.alumnee.ShareUserActivities;
import com.urt.persistence.model.alumnee.SocialDiscussions;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.AndroidMobileUsers;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.AutoReportsTypes;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.FeedbackGrades;
import com.urt.persistence.model.common.HallTicketSettings;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.PaySlip;
import com.urt.persistence.model.common.PersonDocuments;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.StaffMonthlyPaySlips;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.common.StudyAndBonafiedSettings;
import com.urt.persistence.model.common.StudyMaterialAttachments;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.UserAutoReportsConfiguration;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.common.VWFeedbackRatingDetails;
import com.urt.persistence.model.common.VWStaffAttendance;
import com.urt.persistence.model.common.VWStudentDailyAttendance;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.common.ViewHostelStudentDailyAttendance;
import com.urt.persistence.model.common.ViewUserRoles;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.LoginAccessbilityRoles;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.event.AlbumAttachment;
import com.urt.persistence.model.event.Events;
import com.urt.persistence.model.event.EventsAlbum;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.DefaultScoreCardTemplates;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.OverAllGrades;
import com.urt.persistence.model.exam.QuestionPaperBank;
import com.urt.persistence.model.exam.SchoolGrades;
import com.urt.persistence.model.exam.ScoreCardTemplates;
import com.urt.persistence.model.exam.SubType;
import com.urt.persistence.model.exam.Syllabus;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.exam.ViewExamSchedule;
import com.urt.persistence.model.exam.ViewExamScheduleDetails;
import com.urt.persistence.model.exam.ViewStaffSubjectsDetails;
import com.urt.persistence.model.exam.WeekDays;
import com.urt.persistence.model.fee.ExcessPayment;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.fee.StudentFeeConcession;
import com.urt.persistence.model.fee.ViewStudentDeleteFeeDetails;
import com.urt.persistence.model.fee.ViewStudentsExcessPaymentDetails;
import com.urt.persistence.model.hostel.Building;
import com.urt.persistence.model.library.ViewIssuedBookAndSettings;
import com.urt.persistence.model.manuatimetable.TimeTableDetails;
import com.urt.persistence.model.manuatimetable.TimeTablePeriod;
import com.urt.persistence.model.mobile.MeetingSchedules;
import com.urt.persistence.model.mobile.MeetingSlots;
import com.urt.persistence.model.mobile.ViewMeetingSchedules;
import com.urt.persistence.model.mobile.ViewSubstitutionTimeTable;
import com.urt.persistence.model.secretary.BudgetParticularHistory;
import com.urt.persistence.model.secretary.BudgetRequest;
import com.urt.persistence.model.secretary.FinalBudgetRequest;
import com.urt.persistence.model.secretary.FinancialYear;
import com.urt.persistence.model.secretary.ViewBudgetRequestDetails;
import com.urt.persistence.model.secretary.ViewMeetingRequestDetails;
import com.urt.persistence.model.sports.Achievement;
import com.urt.persistence.model.sports.Sport;
import com.urt.persistence.model.sports.Team;
import com.urt.persistence.model.sports.TeamPlayers;
import com.urt.persistence.model.study.ClassAssignment;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.PromoteClass;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Section;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StaffElgibleSubjects;
import com.urt.persistence.model.study.StaffSubstitutionTimeTable;
import com.urt.persistence.model.study.StaffSyllabusPlanner;
import com.urt.persistence.model.study.StaffTimetables;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentAcademicPerformance;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.StudentMarks;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.SubstitutionTimeTable;
import com.urt.persistence.model.study.TimetablePeriods;
import com.urt.persistence.model.study.TransferCertificate;
import com.urt.persistence.model.study.ViewClassFeeDetails;
import com.urt.persistence.model.study.ViewClassSectionTeacher;
import com.urt.persistence.model.study.ViewClassSubjectsSettings;
import com.urt.persistence.model.study.ViewOnlineApplicationStudentClassFees;
import com.urt.persistence.model.study.ViewStaffLeaveDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentFeePaidAndNotPaidDetails;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentTransportFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentsLatestExamMarksDetails;
import com.urt.persistence.model.study.ViewStudyClassSubjects;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.transport.RouteTrack;
import com.urt.persistence.model.transport.StudentTransportDetails;
import com.urt.persistence.model.transport.VehiclesAcademicDetails;
import com.urt.persistence.model.transport.ViewAssignedVehiclestoRoutesWithBoardingPoints;
import com.urt.persistence.model.transport.ViewRouteTrack;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.OnlineApplicationDetailsView;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.persistence.model.views.VWStudentPaymentDetails;
import com.urt.persistence.model.webservice.ViewFee;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.thread.EventsNotificationThread;
import com.urt.service.thread.StudentMarksNotificationThread;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.jrexception.JRExceptionClient;

@Component
public class AdminManagerImpl extends UniversalManagerImpl implements AdminManager {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffPersonAccountDetails> getAllTeacherListByStatus(long custId,String status) {
		return adminDao.getAllTeacherListByStatus(custId,status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<BigInteger> getStaffElgibleSubjectsListByStudySubjectId(long studySubjectId,String staffIds) {
		return adminDao.getStaffElgibleSubjectsListByStudySubjectId(studySubjectId,staffIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffPersonAccountDetails> getViewStaffDetailsListByStaffIds(String staffIds,long customerId,long academicYear) {
		return adminDao.getViewStaffDetailsListByStaffIds(staffIds, customerId, academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Section getSectionBySectionName(String sectionName,long custId){
		return adminDao.getSectionBySectionName(sectionName, custId);
	}
	public void updateClassesOrder(long classId,long sortingOrder){
		adminDao.updateClassesOrder(classId,sortingOrder);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllCommonTypesByCustIdandType(long custId,String type){
		return adminDao.getAllCommonTypesByCustIdandType(custId,type);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StudySubject> getStudySubjectByCustIdAndAcademicYear(long custId,long academicYear){
		return adminDao.getStudySubjectByCustIdAndAcademicYear(custId,academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ClassTeacher> getClassTeacherByStudyClass(long studyClassId,long academicYear) {
		return adminDao.getClassTeacherByStudyClass(studyClassId,academicYear);
	}
	
	public void updateClassNameByClassId(String className,long classId){
		adminDao.updateClassNameByClassId(className,classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getRemainingClassIdsByExamTypeIdAndClassIds(long examtypeId,String classIds){
		return adminDao.getRemainingClassIdsByExamTypeIdAndClassIds(examtypeId,classIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ClassName> getClassesByClassIdsAndAdmissionStatus(long custId,long academicYearId,String status,String classIds,boolean isClassIdIn){
		return adminDao.getClassesByClassIdsAndAdmissionStatus(custId,academicYearId,status,classIds,isClassIdIn);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudyClass getclassByClassAndsection(String className,String sectionName, long customerId,long academicYearId) {
		return adminDao.getclassByClassAndsection(className, sectionName,customerId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllFeesByCustId(long classId) {
		return adminDao.getAllFeesByCustId(classId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Fee getFeeByClassId(long classId) {
		return adminDao.getFeeByClassId(classId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Student> getAllStudentsByCustId(long custId,String status,long academicYearId) {
		return adminDao.getAllStudentsByCustId(custId, status, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Student> getAllStudentsByClassName(long classId, long custId,String status,long academicYearId){
		return adminDao.getAllStudentsByClassName(classId, custId,status,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Student> getAllStudentsByRollNumber(String rollNumber,long custId) {
		return adminDao.getAllStudentsByRollNumber(rollNumber, custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStaffLeaveDetails getViewStaffLeaveDetailsByLeaveId(long leaveid, long customerId) {
		return adminDao.getViewStaffLeaveDetailsByLeaveId(leaveid, customerId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffLeaveDetails> getAllLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus, String roleName, long supervisorId,long academicYearId) {
		return adminDao.getAllLeavesByStatusAndRoleNameAndSupervisorId(customerId, leaveStatus, roleName, supervisorId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Leave> getLeavesListByAccountId(long accountId, String leaveStatus,long customerId) {
		return adminDao.getLeavesListByAccountId(accountId, leaveStatus,customerId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Role getRoleDetailsByRoleName(String roleName) {
		return adminDao.getRoleDetailsByRoleName(roleName);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StudyClass> getSubjectsByClassName(String className,long custId,long academicYearId) {
		return adminDao.getSubjectsByClassName(className,custId,academicYearId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSectionByStudyClass(String sectionName) {
		return adminDao.getSectionByStudyClass(sectionName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Student getStudentByRollNumber(String rollNumber, long custId) {
		return adminDao.getStudentByRollNumber(rollNumber, custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Person> checkPersonId(String userId) {
		return adminDao.checkPersonId(userId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StudySubject> getSubjectsForHigherClass(String subjectId) {
		return adminDao.getSubjectsForHigherClass(subjectId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudentPayment getPaymentStatusByStudentId(long studentId) {
		return adminDao.getPaymentStatusByStudentId(studentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSubjectsByClass(long studyClassId) {
		return adminDao.getSubjectsByClass(studyClassId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public LeaveManagement getLeaveManagementByRoleName(long roleId,long custId,long academicYearId) {
		return adminDao.getLeaveManagementByRoleName(roleId,custId,academicYearId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffPersonAccountDetails> getViewStaffDetailsByRoleName(String rollName, long customerId,String status) {
		return adminDao.getViewStaffDetailsByRoleName(rollName, customerId,status);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentsPaymetDetailsByCustId(String paymentType, long custId) {
		return adminDao.getStudentsPaymetDetailsByCustId(paymentType, custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getLeavesByAccountId(long accountId) {
		return adminDao.getLeavesByAccountId(accountId);
	}

	public void updateAllStaffLeaveDetailsByCasualANDSickLivesAndStaffIds(String staffIds, long casualLeaves, long sickLeaves,long custId) {
		adminDao.updateAllStaffLeaveDetailsByCasualANDSickLivesAndStaffIds(staffIds, casualLeaves, sickLeaves, custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentClassDetails> getStudentsPayentByRollNumber(String rollNumber, long custId,long academicYearId) {
		return adminDao.getStudentsPayentByRollNumber(rollNumber, custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentPersonAccountDetails> getStudentTransportPayentByRollNumber(String rollNumber,long custId,String status,String transportStatus){
		return adminDao.getStudentTransportPayentByRollNumber(rollNumber, custId,status,transportStatus);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassSubjectByClassId(long classId) {
		return adminDao.getClassSubjectByClassId(classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentClassFeePaymentDetails> getStudentByClassIdAndFee(long termId,long custId) {
		return adminDao.getStudentByClassIdAndFee(termId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Fee getPaymentStatusByClassId(long classId) {
		return adminDao.getPaymentStatusByClassId(classId);
	}

	public void deleteStudentAttendanceByStudentId(long studentId) {
		adminDao.deleteStudentAttendanceByStudentId(studentId);

	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllAdminFeeStausList(String status) {
		return adminDao.getAllAdminFeeStausList(status);
	}
	 
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StudentPayment> getALlReceiptsByPaymentId(long spId, String createdDate) {
		return adminDao.getALlReceiptsByPaymentId(spId, createdDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getFeesPaymentListByStudentPaymentId(String table,long studentId,long classId,long custId,long academicYearId,String today,String paymentStatus,long invoiceNumber) {
		return adminDao.getFeesPaymentListByStudentPaymentId(table,studentId, classId, custId, academicYearId,today, paymentStatus, invoiceNumber);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllNoticeBoardMessagesList(String attendanceStatus,long customerId,String academicYearId) {
		return adminDao.getAllNoticeBoardMessagesList(attendanceStatus,customerId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ExamTypes> getAllClassExamTypes(long custId,String academicYear) {
		return adminDao.getAllClassExamTypes(custId,academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllHolidayBoardMessagesList(String attendanceStatus,long customerId) {
		return adminDao.getAllHolidayBoardMessagesList(attendanceStatus,customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassName getClassByClassName(String className,long custId,long academicYearId,boolean isAutoCheck) {
		return adminDao.getClassByClassName(className,custId,academicYearId,isAutoCheck);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStaffPersonAccountDetails getStaffLastRecordByRoleName(String roleName, long customerId) {
		return adminDao.getStaffLastRecordByRoleName(roleName, customerId);
	}

	public long getMaxExamTypeIdFromStudentMarks(long classId,long academicYear) {
		return adminDao.getMaxExamTypeIdFromStudentMarks(classId, academicYear);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassName saveClassName(ClassName className) {
		return adminDao.saveClassName(className);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSyllabusByClassIdAndCustId(long classId,long custId){
		return adminDao.getSyllabusByClassIdAndCustId(classId,custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentsByClassNameClassId(long classNameClassId,long customerId) {
		return adminDao.getStudentsByClassNameClassId(classNameClassId,customerId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllHolidaysListByAcademicYearId(String status,long customerId, long academicYearId,long classNameClassId) {
		return adminDao.getAllHolidaysListByAcademicYearId(status, customerId,academicYearId,classNameClassId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllUpcomingClassExamSchedulesGroupByClassIdandExamTypeId(long custId, long academicYearId,String date) {
		return adminDao.getAllUpcomingClassExamSchedulesGroupByClassIdandExamTypeId(custId, academicYearId, date);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassSyllabusList(long classId) {
		return adminDao.getClassSyllabusList(classId);
	}

	public String removeAllSyllabusWithClassId(long classId) {
		return adminDao.removeAllSyllabusWithClassId(classId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAcademicsByAcademicYear(String academicYear, long custId) {
		return adminDao.getAcademicsByAcademicYear(academicYear, custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllCompletedClassExamSchedulesGroupByClassIdandExamTypeId(long custId, long academicYearId,String date) {
		return adminDao.getAllCompletedClassExamSchedulesGroupByClassIdandExamTypeId(custId, academicYearId, date);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentPersonAccountDetails> getStudentsByFirstName(String firstName, long custId) {
		return adminDao.getStudentsByFirstName(firstName, custId);
	}

	/*public List<ClassName> getAllClasseNames(long custId, String status,long academicYearId) {
		return adminDao.getAllClasseNames(custId, status ,academicYearId);
	}*/

	public AdmissionSettings getAdmissionSettingsByCustIdAndClassId(long custId, long classId) {
		return adminDao.getAdmissionSettingsByCustIdAndClassId(custId, classId);
	}

	public AdmissionSettings getAdmissionSettingsByCustId(long custId) {
		return adminDao.getAdmissionSettingsByCustId(custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public OnlineApplicationDetailsView getDetailsByApplicationNumberAndView(String applicationNumber) {
		return adminDao.getDetailsByApplicationNumberAndView(applicationNumber);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getApplicationDetailsByClassName(long classId, long custId) {
		return adminDao.getApplicationDetailsByClassName(classId, custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getApplicationDetailsByMarks(long classId, long custId) {
		return adminDao.getApplicationDetailsByMarks(classId, custId);
	}

	public String getClassStudentStrengthByClassId(long classId, long custId) {
		return adminDao.getClassStudentStrengthByClassId(classId, custId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getApplicationsByStatus(String status, long classId, long custId,long academicYearId,String todayDate) {
		return adminDao.getApplicationsByStatus(status, classId, custId,academicYearId,todayDate);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public OnlineApplicationDetails getDetailsByApplicationNumber(
			String applicationNumber, long custId) {
		return adminDao
				.getDetailsByApplicationNumber(applicationNumber, custId);
	}

	public List getAdmissionSettingsForAcademicYears(long custId,long academicYearId) {
		return adminDao.getAdmissionSettingsForAcademicYears(custId,academicYearId);
	}

	public List<AcademicYear> getAcademicYears(long pastYear, String academicYear,long custId) {
		return adminDao.getAcademicYears(pastYear, academicYear,custId);
	}
	public AdmissionSettings getAdmissionSettingsByCustIdAndYear(long custId,long academicYearId) {
		return adminDao.getAdmissionSettingsByCustIdAndYear(custId,academicYearId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public AcademicYear saveAcademicYear(AcademicYear academicYear) {
		return adminDao.saveAcademicYear(academicYear);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<KBank> getKBankBySearchKewordsKBankTypeId(String searchKeywords, long kBankTypeId, long custId) {
		return adminDao.getKBankBySearchKewordsKBankTypeId(searchKeywords,kBankTypeId, custId);
	}

	public List geAllHolidaysByAcademicYearId(long custId, long academicYearId) {
		return adminDao.geAllHolidaysByAcademicYearId(custId, academicYearId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewClassFeeDetails> getClassFeeDetails(long classId,long custId, String academicYear) {
		return adminDao.getClassFeeDetails(classId, custId, academicYear);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Fee> getClassFeeTermDetails(long classId,long custId, String academicYear) {
		return adminDao.getClassFeeTermDetails(classId, custId, academicYear);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassFeeTermsByStudentId(String table,long studentId,long custId,long academicYearId,long classId,long categoryId,long feeSettingId){
		return adminDao.getClassFeeTermsByStudentId(table,studentId, custId, academicYearId, classId, categoryId, feeSettingId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassFeeTermsByTransportStudentId(String table,long studentId,long custId,long academicYearId,long classId,long categoryId){
		return adminDao.getClassFeeTermsByTransportStudentId(table,studentId, custId, academicYearId, classId, categoryId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllHolidaysByMonthId(int monthId, long orgId,String holidayYear) {
		return adminDao.getAllHolidaysByMonthId(monthId, orgId,holidayYear);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudentPayment getRemainingPaymentByStudentPaymentId(long studentId,long classId,long feeId,long custId,long academicYearId,String status) {
		return adminDao.getRemainingPaymentByStudentPaymentId(studentId, classId,feeId, custId,  academicYearId, status);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public FeeType getFeeTypeId(long feeTypeId,long custId){
		return adminDao.getFeeTypeId(feeTypeId,custId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentClassFeePaymentDetails getClassFeePendingTerms(long classId,long feeId,long custId,String studentNumber){
		return adminDao.getClassFeePendingTerms(classId,feeId,custId,studentNumber);
	}
	
	public AcademicYear getAcademicYearByAcademicYear(String academicYear,long custId){
		return adminDao.getAcademicYearByAcademicYear(academicYear,custId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStudentsDetailsByCustId(long custId){
		return adminDao.getAllStudentsDetailsByCustId(custId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List removeStaffSubjectList(long staffId,long academicYearId){
		return adminDao.removeStaffSubjectList(staffId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStaffLeavesByStatus(long custId,String leaveStatus){
		return adminDao.getAllStaffLeavesByStatus(custId,leaveStatus);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffPersonAccountDetails> getAllStaffList(long custId,long academicYearId){
		return adminDao.getAllStaffList(custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Fee getFeeAndFeeTypeByClassId(String table,long classId,long feeTypeId,long custId,long academicYearId,long schoolTermId,long categoryId){
		return adminDao.getFeeAndFeeTypeByClassId(table,classId,feeTypeId, custId, academicYearId, schoolTermId, categoryId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getQuizQuestionListWithStartDate(long quizId){
		return adminDao.getQuizQuestionListWithStartDate(quizId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public FeeType saveFeeTypeName(FeeType feeType){
		return adminDao.saveFeeTypeName(feeType);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getFeeTypeList(String table,String feeType, long custId,long acadmeicYearId){
		return adminDao.getFeeTypeList(table,feeType,custId,acadmeicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ParentFeedbackResult> getStaffFeedbackResultList(long staffId,long custId,String academicYearId){
		return adminDao.getStaffFeedbackResultList(staffId,custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List removeFeedbackParentsResults(long feedbackQuestionId,long custId){
		return adminDao.removeFeedbackParentsResults(feedbackQuestionId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ParentFeedbackResult getTeacherGradeByStudent(long staffId,long studentId,long custId){
		return adminDao.getTeacherGradeByStudent(staffId,studentId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewClassFeeDetails getClassFeeDetails(long classId,long custId, String academicYear,long feeTypeId) {
		return adminDao.getClassFeeDetails(classId, custId, academicYear,feeTypeId);
	}
	public ViewClassFeeDetails getAdminssionFeeDetails(String feeType,long custId,long classId){
		return adminDao.getAdminssionFeeDetails(feeType,custId,classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List updateOnlineApplicationDetails(long classId){
		return adminDao.updateOnlineApplicationDetails(classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewClassFeeDetails> getClassFeeDetailsList(long classId,String academicYear,long custId){
		return adminDao.getClassFeeDetailsList(classId,academicYear,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Staff getStaffByAcountId(long accountId,String status){
		 return adminDao.getStaffByAcountId(accountId,status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<PromoteClass> getPromoteClassDetailsByCustId(long custId,long academicYearId){
		return adminDao.getPromoteClassDetailsByCustId(custId,academicYearId);
	}
	public List getAllAdminsByUrlPath(String urlPath) {
		return adminDao.getAllAdminsByUrlPath(urlPath);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Student saveStudentDetails(Student student){
		return adminDao.saveStudentDetails(student);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StudySubject> getStudySubjectByCustId(long custId){
		return adminDao.getStudySubjectByCustId(custId);
	}
	public String getMaxExamTypeIdFromStudentMarksBydate(String date){
		return adminDao.getMaxExamTypeIdFromStudentMarksBydate(date);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<AcademicYear> getPastAcademicYears(long academicYearId,long custId){
		return adminDao.getPastAcademicYears(academicYearId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<FeeType> getFeeTypeListByAcademicYearId(String queryString,long custId,long academicYearId,long feeSettingId){
		return adminDao.getFeeTypeListByAcademicYearId(queryString,custId,academicYearId,feeSettingId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getClassTeacherByAcademicId(long staffId,long custId,long academicYearId){
		return adminDao.getClassTeacherByAcademicId(staffId,custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<LeaveManagement> getLeavesListByCustId(long custId,long academicYearId){
		return adminDao.getLeavesListByCustId(custId,academicYearId);
	}
	public List<AdmissionSettings> getAdmissionSettingsList(String status){
		return adminDao.getAdmissionSettingsList(status);
	}
	public Customer getCustomerByUrlPath(String urlPath){
		return adminDao.getCustomerByUrlPath(urlPath);
	}
	/*public List<ClassName> getAllClassesByCustIdandAcademicYear(long custId,String academicYearId){
		return adminDao.getAllClassesByCustIdandAcademicYear(custId,academicYearId);
	}*/
	public AcademicYear getAcademicYearByPastYearandCustId(String pastYear,long custId){
		return adminDao.getAcademicYearByPastYearandCustId(pastYear,custId);
	}
	/*public ClassName getClassNameByclassNameandCustIdandAcademicYear(String className,long custId,long academicYearId){
		return adminDao.getClassNameByclassNameandCustIdandAcademicYear(className,custId,academicYearId);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudySubject getStudySubjectBySubjectNameandAcademicYearandCustId(String subjectName,long academicYear,long custId){
		return adminDao.getStudySubjectBySubjectNameandAcademicYearandCustId(subjectName,academicYear,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StaffElgibleSubjects> getStaffElgibleSubjectsByAcademicYearId(long staffId,long academicYearId){
		return adminDao.getStaffElgibleSubjectsByAcademicYearId(staffId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudySubject saveStudySubject(StudySubject subject){
		return adminDao.saveStudySubject(subject);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassTeachersByAcademicIdAndStaffIdandCustId(long staffId,long custId,long academicYearId){
		return adminDao.getClassTeachersByAcademicIdAndStaffIdandCustId(staffId,custId,academicYearId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Customer> getCustomerListByStatus(String status){
		return adminDao.getCustomerListByStatus(status);
	}
	public void removeSyllabusByAcademicYearAndCustId(long custId,long academicYear){
		adminDao.removeSyllabusByAcademicYearAndCustId(custId,academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllTeachingStaffListByStatus(long custId,String status,long academicYearId){
		return adminDao.getAllTeachingStaffListByStatus(custId,status,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StudySubject getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(long custId,String academiYear,String subjectName){
		return adminDao.getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(custId,academiYear,subjectName);
	}
	public List getAllAcademicYearsByCustIdAndStartDateAndEndDateisNull(long custId){
		return adminDao.getAllAcademicYearsByCustIdAndStartDateAndEndDateisNull(custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public CommonType getSkillTypeNameByCustIdAndAcademicYear(long custId,String skillTypeName){
		return adminDao.getSkillTypeNameByCustIdAndAcademicYear(custId,skillTypeName);	
	}
	
	public long getStudentsCountBycustIdandStatus(long custId,String status){
		return adminDao.getStudentsCountBycustIdandStatus(custId,status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllFuturePackagesBycurrentPackage(long studentsRange){
		return adminDao.getAllFuturePackagesBycurrentPackage(studentsRange);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Student> getStudentsByClassIdAndCustIdAndYearAndStatus(long classId,long custId,long academiYearId, String status){
		return adminDao.getStudentsByClassIdAndCustIdAndYearAndStatus(classId,custId,academiYearId,status);
	}
	
	public List getAllFeeByClasIdAndCustId(String table,long custId,long classId,long academicYearId,long categoryId){
			return adminDao.getAllFeeByClasIdAndCustId(table,custId, classId, academicYearId, categoryId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public long getFeeTotalAmountByTerm(String queryString,long custId,long classId,long academicYearId,long schoolTermId,long categoryId,long boardingPointId){
			return adminDao.getFeeTotalAmountByTerm(queryString, custId, classId, academicYearId, schoolTermId, categoryId,boardingPointId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ExamTypes saveExamType(ExamTypes examType){
		return adminDao.saveExamType(examType);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewExamSchedule> getExamSchedulesByClassIdAndExamTypeId(long classId,long examTypeId){
		return adminDao.getExamSchedulesByClassIdAndExamTypeId(classId,examTypeId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getExamScheduleIdsByAcademicYearandClassNameClassIdandExamTypeIdAndSubjectIdAndSubTypeId(long academicYear,long classId ,long examTypeId,long subjectId,long subTypeId){
		return adminDao.getExamScheduleIdsByAcademicYearandClassNameClassIdandExamTypeIdAndSubjectIdAndSubTypeId(academicYear,classId ,examTypeId,subjectId,subTypeId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffSubjectsDetails> getStaffSubjectsByStaffIdAndAcademicYearIdAndCustId(long staffId,long academicYearId,long custId){
		return adminDao.getStaffSubjectsByStaffIdAndAcademicYearIdAndCustId(staffId,academicYearId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewClassExamDetails> getExamSchedulesByClassIdandSubjectIdandExamTypeIdandAcademicYearId(long classId,long subjectId,long examTypeId,long academicYear){
		return adminDao.getExamSchedulesByClassIdandSubjectIdandExamTypeIdandAcademicYearId(classId,subjectId,examTypeId,academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentMarks getStudentMarksByScheduleIdAndStudentId(long scheduleId, long studentId){
		return adminDao.getStudentMarksByScheduleIdAndStudentId(scheduleId, studentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Student getStudentByCustIdAndStudentIdAndStatus(long studentId,long custId,String status,long academicYearId){
		return adminDao.getStudentByCustIdAndStudentIdAndStatus(studentId,custId,status, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Student getStudentByCustIdAndStudentIdAndTransportStatus(long studentId,long custId,String status,String transportStatus,long academicYearId){
		return adminDao.getStudentByCustIdAndStudentIdAndTransportStatus(studentId,custId,status,transportStatus,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getFeetypesByClassId(String table,long classId,long custId,long academicYearId){
		return adminDao.getFeetypesByClassId(table,classId, custId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<SchoolTerms> getSchoolTermsByCustId(String clazz,long custId,String academicYear){
		return adminDao.getSchoolTermsByCustId(clazz, custId, academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public SchoolTerms saveSchoolTerms(SchoolTerms schoolTerms){
		return adminDao.saveSchoolTerms(schoolTerms);
	}
	 
	public List getAllFeeByClasIdAndCustIdAndTermId(String table,List<String> classIds,long custId,long academicYearId,long termId,String categoryIds){
		return adminDao.getAllFeeByClasIdAndCustIdAndTermId(table,classIds, custId, academicYearId, termId, categoryIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Fee getFeeByClasIdAndCustIdAndTermIdAndTypeId(long classId,long typeId,long termId,long custId,long academicYearId){
		return adminDao.getFeeByClasIdAndCustIdAndTermIdAndTypeId(classId, typeId, termId, custId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Syllabus> getSyllabusByClassIdAndSubjectIdAndCustId(long subjectId,long custId,long studyClassId){
		return adminDao.getSyllabusByClassIdAndSubjectIdAndCustId(subjectId,custId,studyClassId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Syllabus> getSyllabusBySyllabusIdsAndCustId(String syllabusIds,long custId){
		return adminDao.getSyllabusBySyllabusIdsAndCustId(syllabusIds,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public long getStudentPaidAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,long termId,long boardingPointId){
		return adminDao.getStudentPaidAmountByClassId(queryString, studentId, classId, custId, academicYearId, termId,boardingPointId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllTitlesByPlaylist(String subjectName){
		return adminDao.getAllTitlesByPlaylist(subjectName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getPlayListSubjects(String clazz){
		return adminDao.getPlayListSubjects(clazz);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getPlayListForSubTopics(String subName){
		return adminDao.getPlayListForSubTopics(subName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getVideoPlayList(long playListId){
		return adminDao.getVideoPlayList(playListId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStaffsListByRoleAndCustIdAndStatus(String roleName,long custId,String status,long academicYearId){
		return adminDao.getStaffsListByRoleAndCustIdAndStatus(roleName,custId,status,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStaffPersonAccountDetails getStaffDetailsByRoleNameAndStaffIdAndCustId(String roleName,long custId,long staffId,String status,long academicYearId){
		return adminDao.getStaffDetailsByRoleNameAndStaffIdAndCustId(roleName,custId,staffId,status,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public long getStudentDiscountAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber){//long termId,String status
		return adminDao.getStudentDiscountAmountByClassId( queryString, studentId, classId, custId, academicYearId, feeIds,invoiceNumber);//termId, status
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getClassTeacherByAcademicIdAndNotInCurrentClass(long staffId,long custId,long academicYearId,long classId){
		return adminDao.getClassTeacherByAcademicIdAndNotInCurrentClass(staffId,custId,academicYearId,classId);
	}
	public void removeClassTeacherByCustId(long classTeacherId,long custId){
		 adminDao.removeClassTeacherByCustId(classTeacherId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public State getStateCodeByStateName(String stateName){
		return adminDao.getStateCodeByStateName(stateName );
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffLeaveDetails> getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId){
		return adminDao.getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(customerId,roleName,leaveStatus,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentLeaveDetails> getAllStudentLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId) {
		return adminDao.getAllStudentLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(customerId,roleName,leaveStatus,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewUserRoles getViewUserRolesByUserIdAndCustId(long userId, long customerId,String status){
		return adminDao.getViewUserRolesByUserIdAndCustId(userId, customerId,status);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentClassFeePamentByTodayDateAndcustIdAndStatus(long userCustId, long reqYear,String todayDate,String status){
		return adminDao.getStudentClassFeePamentByTodayDateAndcustIdAndStatus(userCustId,reqYear,todayDate,status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentPaidTermsAmountByClassIdAndStatus(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber){//long termId,String status
		return adminDao.getStudentPaidTermsAmountByClassIdAndStatus(queryString,studentId,classId,custId,academicYearId, feeIds, invoiceNumber);//termId,status
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStaffPaidTermsAmountByStaffIdAndStatus(String queryString,long staffId,long custId,long academicYearId,String feeIds,long invoiceNumber){
		return adminDao.getStaffPaidTermsAmountByStaffIdAndStatus(queryString,staffId,custId,academicYearId, feeIds, invoiceNumber);//termId,status
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassFeeTermsByStudentIdAndStatusAndCurrentDate(String queryString,long studentId,long custId,long academicYearId,long classId,String todayDate,String feeIds,long invoiceNumber){
		return adminDao.getClassFeeTermsByStudentIdAndStatusAndCurrentDate(queryString,studentId,custId,academicYearId,classId,todayDate,feeIds, invoiceNumber);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getHostelFeeTermsByStaffIdAndStatusAndCurrentDate(String queryString,long studentId, long custId, long academicYearId,String todayDate, String feeIds, long invoiceNumber){
		return adminDao.getHostelFeeTermsByStaffIdAndStatusAndCurrentDate(queryString,studentId,custId,academicYearId,todayDate,feeIds, invoiceNumber);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllSmsEventsByAcademicYear(String academicYear){
		return adminDao.getAllSmsEventsByAcademicYear(academicYear);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<SubType> getAllSubTypesBySubTypeIds(String subTypeIds,long custId){
		return adminDao.getAllSubTypesBySubTypeIds(subTypeIds,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public SchoolGrades getSchoolGradeByName(String gradName, long academicYearId){
		return adminDao.getSchoolGradeByName(gradName, academicYearId);
	}
	public List<ExamTypes> checkExamTypeByNameAndCustId(String examType,long custId) {
		return adminDao.checkExamTypeByNameAndCustId(examType,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSubTypesByClassSectionIdAndExamTypeId(long classSectionId,long examTypeId){
		return adminDao.getSubTypesByClassSectionIdAndExamTypeId(classSectionId,examTypeId);
	}
	public void removeClassTeachersByStudyClassIdandSubjectIdsandCustId(long classSectionId,long custId,String subjectIds){
		adminDao.removeClassTeachersByStudyClassIdandSubjectIdsandCustId(classSectionId,custId,subjectIds);
	}
	public void removeSyllabusByStudyClassIdandSubjectIdsCustId(long classSectionId,long custId,String subjectIds){
		adminDao.removeSyllabusByStudyClassIdandSubjectIdsCustId(classSectionId,custId,subjectIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassTeachersByAcademicIdAndSubjectIdAndStaffIdandCustId(long staffId, long subjectId, long custId, long academicYearId) {
		return adminDao.getClassTeachersByAcademicIdAndSubjectIdAndStaffIdandCustId(staffId, subjectId, custId, academicYearId); 
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public void removeClassTeacherByClassTeacherIdAndSubjectIdAndCustId(long classTeacherId, long subjectId, long custId,long academicYearId) {
		adminDao.removeClassTeacherByClassTeacherIdAndSubjectIdAndCustId(classTeacherId, subjectId, custId, academicYearId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassSubjectBySubjectId(long subjectId) {
		return adminDao.getClassSubjectBySubjectId(subjectId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getClassTeachersByAcademicIdAndSubjectIdAndClassIdandCustId(long studyClassId, long subjectId, long custId, long academicYearId) {
		return adminDao.getClassTeachersByAcademicIdAndSubjectIdAndClassIdandCustId(studyClassId, subjectId, custId, academicYearId);
	}
	
	public List<SchoolTerms> checkSchoolTermsByNameAndCustId(String termName,long custId,long academicYearId) {
		return adminDao.checkSchoolTermsByNameAndCustId(termName,custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffSubjectsDetails> getClassTeacherClassesListByStaffIdCustIdAcademicYearIdGroupByClassTecherStatusAndStudyClassId(long academicYearId,long staffId,long custId){
		return adminDao.getClassTeacherClassesListByStaffIdCustIdAcademicYearIdGroupByClassTecherStatusAndStudyClassId(academicYearId,staffId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffSubjectsDetails> getTeacherClassesAndSubjectsListByStaffIdCustIdAcademicYearIdOrderbyStudyClassId(long academicYearId,long staffId,long custId){
		return adminDao.getTeacherClassesAndSubjectsListByStaffIdCustIdAcademicYearIdOrderbyStudyClassId(academicYearId,staffId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSchoolTermsByDuedate(String table,long custId,long academicYearId,String todayDate,long feeSettingId){
		return adminDao.getSchoolTermsByDuedate(table,custId, academicYearId, todayDate , feeSettingId);
	} 
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSchoolTermsOrderByDuedate(String table,long custId,long academicYearId,long feeSettingId){
		return adminDao.getSchoolTermsOrderByDuedate(table, custId, academicYearId, feeSettingId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getDescriptionByCustIdAndAcademicYear(long custId,long academicYearId){
		return adminDao.getDescriptionByCustIdAndAcademicYear(custId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StaffDailyAttendance> getStaffAttendance(long custId,long academicYearId, String date) {
		return adminDao.getStaffAttendance(custId, academicYearId, date);
	} 
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentClassFeePaymentDetails> getAllReceiptsStudentClassFeeByPaymentId(long spId,long invoiceNumber){
        return adminDao.getAllReceiptsStudentClassFeeByPaymentId(spId,invoiceNumber);

    }
	public long getFeeTotalAmountByCustId(long custId,long termId,long academicYearId,long classId){
		return adminDao.getFeeTotalAmountByCustId( custId, termId, academicYearId,classId);
	}
	public long getDiscountTotalByCustId(long custId,long termId,long academicYearId,String status,long classId){
		return adminDao.getDiscountTotalByCustId( custId, termId, academicYearId, status, classId);
	}
	public long getTotalStudentsPaidAmount(long custId,long termId,long academicYearId,String status,long classId){
		return adminDao.getTotalStudentsPaidAmount(custId, termId, academicYearId, status,classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassFeeTermsByStudentIdAndStatusAndCurrentAndEndDate(String queryString,long studentId,long custId,long academicYearId,long classId,long termId,String fromdate,String todayDate,String status,long invoiceNumber){
		return adminDao.getClassFeeTermsByStudentIdAndStatusAndCurrentAndEndDate(queryString,studentId,custId,academicYearId,classId,termId,fromdate,todayDate,status, invoiceNumber);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getFeeTermsByStaffIdAndStatusAndCurrentAndEndDate(String queryString,long staffId,long custId,long academicYearId,long termId,String fromdate,String todayDate,String status,long invoiceNumber){
		return adminDao.getFeeTermsByStaffIdAndStatusAndCurrentAndEndDate(queryString,staffId,custId,academicYearId,termId,fromdate,todayDate,status,invoiceNumber);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSchoolTermsByCompleteDuedate(String table,long custId,long academicYearId,String toDayDate){
		return adminDao.getSchoolTermsByCompleteDuedate(table,custId, academicYearId, toDayDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentPersonAccountDetails> getStudentbyClassNameClassIdAndCustIdAndAcademicYear(long classNameClassId,long custId,long academicYearId,String status){
		return adminDao.getStudentbyClassNameClassIdAndCustIdAndAcademicYear(classNameClassId, custId, academicYearId, status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStudentInvoiceFeeDetailsByCustId(String queryString,long customerId,long academicYearId,long termId,String today,String daysBetwwenenfromDate,String daysBetwwenendDate,String transportmode,long feesettingId,String selectedClassSectionId){//,String paymentStatus
		return adminDao.getAllStudentInvoiceFeeDetailsByCustId(queryString, customerId, academicYearId,termId,today,daysBetwwenenfromDate,daysBetwwenendDate,transportmode,feesettingId,selectedClassSectionId);
	}//,paymentStatus
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStaffInvoiceFeeDetailsByCustId(String queryString,long customerId, long academicYearId, long termId, String today,String status) {// ,String paymentStatus
		return adminDao.getAllStaffInvoiceFeeDetailsByCustId(queryString, customerId, academicYearId,termId,today,status);//,paymentStatus
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentMarksDetails getStudentMarksByStudIdAndExamTypeIdAndSubTypeIdAndSubjectId(long studId,long examTypeId,long subTypeId,long subjectId){
		return adminDao.getStudentMarksByStudIdAndExamTypeIdAndSubTypeIdAndSubjectId(studId,examTypeId,subTypeId,subjectId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public SchoolCategory getCategoryIdByCustId(String status,long custId){
		return adminDao.getCategoryIdByCustId(status, custId);
	}
	public void removeSchedulesByScheduleIds(String scheduleIds){
		 adminDao.removeSchedulesByScheduleIds(scheduleIds);
	}
	public void updateExamScheduleStartDateAndEndDateByClassSectionIdAndExamTypeId(long classSectionId,long examType,String startDate,String endDate){
		 adminDao.updateExamScheduleStartDateAndEndDateByClassSectionIdAndExamTypeId(classSectionId,examType,startDate,endDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getStudySubjectTeacherByStudyClassAndCustId(long custId,long academicYearId,long staffId,long studyClassId,long subjectId){
		return adminDao.getStudySubjectTeacherByStudyClassAndCustId(custId, academicYearId, staffId, studyClassId, subjectId);
	}
	public void removeClassTeachersByStudyClassIdandSubjectIds(long staffId, long academicYearId, long custId,String subjectId){
		 adminDao.removeClassTeachersByStudyClassIdandSubjectIds(staffId,custId,academicYearId,subjectId);
	}
	public ClassTeacher getAllClasseNamesByClassId(long subjectId, long staffId, String classId){
		return adminDao.getAllClasseNamesByClassId(subjectId,staffId,classId);
	}
	
	public long getAdmissionReceiptNumberByCustId(String table,long custId,long academicyearId){
		return adminDao.getAdmissionReceiptNumberByCustId(table,custId,academicyearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Staff getStaffByAcountIdAndCustId(long accountId,String status,long custId){
		return adminDao.getStaffByAcountIdAndCustId(accountId,status,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StaffElgibleSubjects getStudySubjectTeacherByStudyClassAndCustId(long academicYearId, long staffId, long subjectId){
		return adminDao.getStudySubjectTeacherByStudyClassAndCustId(academicYearId, staffId, subjectId);	
	}
	public void removeSelecteEndDateHolidays(long custId,long academicYearId,String endDate){
	 adminDao.removeSelecteEndDateHolidays(custId, academicYearId, endDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassWiseFeeDefaultersByCustId(String table,long customerId,long academicYearId,long classId,long termId,String transportmode,String hostelmode){
		return adminDao.getClassWiseFeeDefaultersByCustId(table,customerId, academicYearId, classId, termId, transportmode,hostelmode);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSchoolHolidaysByDescription(long custId, long academicYearId,String description){
		return adminDao.getSchoolHolidaysByDescription(custId,academicYearId,description);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSubTypesByClassSectionIdAndExamTypeIdAndSubjectId(long classSectionId,long examTypeId,long subjectId){
		return adminDao.getSubTypesByClassSectionIdAndExamTypeIdAndSubjectId(classSectionId,examTypeId,subjectId);
	}
	public List getAllByCustIdAndStatus(String clazz,long custId,String status,long academicYearId){
		return adminDao.getAllByCustIdAndStatus(clazz, custId,status,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getParentSearchResultsByStudentDetails(long custId,String fName,String lName,String pName,String adNumber,String status){
		return adminDao.getParentSearchResultsByStudentDetails(custId,fName,lName,pName,adNumber,status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StaffDailyAttendance getStaffAttendanceByAttendanceDateAndAccountIdAndCustId(long custId,long accountId,String attendanceDate){
		return adminDao.getStaffAttendanceByAttendanceDateAndAccountIdAndCustId(custId,accountId,attendanceDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewUserRoles> getViewUserRolesByRoleNameAndCustId(String roleName, long customerId,String status){
		return adminDao.getViewUserRolesByRoleNameAndCustId(roleName, customerId,status);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getClassTeacherByStudyClassIdAndClassTeacherStatus(long studyClassId, String classTeacherType,long academicYearId){
		return adminDao.getClassTeacherByStudyClassIdAndClassTeacherStatus(studyClassId, classTeacherType,academicYearId) ;
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentPersonAccountDetails> getParentChildrens(long custId,long parentId,long academicYearId){
		return adminDao.getParentChildrens(custId, parentId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffLeaveDetails> getAllFutureLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId){
		return adminDao.getAllFutureLeavesByStatusAndRoleNameAndSupervisorId(customerId,leaveStatus,roleName,supervisorId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public TransferCertificate saveTCSerialNumber(TransferCertificate transferCertificate){
		return adminDao.saveTCSerialNumber(transferCertificate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<SubCastSettings> getSubcastBySubCastNameAndCastId(long customerId,String subCastName,long castId){
		return adminDao.getSubcastBySubCastNameAndCastId(customerId,subCastName,castId);	
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSubcastSettingsByCastIdAndCustId(long castId, long customerId){
		return adminDao.getSubcastSettingsByCastIdAndCustId(castId, customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getEventsByDates(long custId,String startDate,String endDate){
		return adminDao.getEventsByDates(custId,startDate,endDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getWorkingDayHolidaysByDates(long custId,String status,String startDate,String endDate){
		return adminDao.getWorkingDayHolidaysByDates(custId,status,startDate,endDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<StaffElgibleSubjects> getAllStaffElgibleSubjectsByStudySubjectId(long studySubjectId){
		return adminDao.getAllStaffElgibleSubjectsByStudySubjectId(studySubjectId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Object[] getAllStudentsByClassNameAndCastName(long custId,long academicYearId,List<CastSettings> castList,long classId,String userName){
		return adminDao.getAllStudentsByClassNameAndCastName(custId,academicYearId,castList,classId,userName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Section saveSectionName(Section sectionName){
		return adminDao.saveSectionName(sectionName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public CastSettings saveComunityName(CastSettings castSettings){
		return adminDao.saveComunityName(castSettings);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Object[] getAllStudentsCountByCastWise(long custId,long academicYearId,List<CastSettings> castList,String username,String classNameClassIds){
		return adminDao.getAllStudentsCountByCastWise(custId,academicYearId,castList,username,classNameClassIds);
	}
	public void removeClassSubjectsByClassSectionId(long studyClassId){
		adminDao.removeClassSubjectsByClassSectionId(studyClassId);
	}
	public void removeClassEventsByClassSectionId(long studyClassId){
		adminDao.removeClassEventsByClassSectionId(studyClassId);
	}
	public void removeExamSchedulesByClassSectionId(long studyClassId){
		adminDao.removeExamSchedulesByClassSectionId(studyClassId);
	}
	public void removeKbanksByClassSectionId(long studyClassId){
		adminDao.removeKbanksByClassSectionId(studyClassId);
	}
	public void removeClassTeachersByStudyClassIdCustId(long classSectionId,long custId){
		adminDao.removeClassTeachersByStudyClassIdCustId(classSectionId,custId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Object[]> getAllStaffDetailsByDesignation(long custId,long academicYearId,List<CastSettings> castList,String username){
		return adminDao.getAllStaffDetailsByDesignation(custId,academicYearId,castList,username);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Object[] getAllStaffsCountByCastWise(long custId,long academicYearId,List<CastSettings> castList,String username){
		return adminDao.getAllStaffsCountByCastWise(custId,academicYearId,castList,username);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public AcademicYear getFutureAcademicYearByCustIdAndStatus(long custId, String status){
		return adminDao.getFutureAcademicYearByCustIdAndStatus(custId, status);
	}
	public int getMaxOfClassNameSortingOrder(long academicYearId, long customerId){
		return adminDao.getMaxOfClassNameSortingOrder(academicYearId,customerId);
	}
	public Date getMaxExamDateByExamTypeIdAndClassSectionId(long examtypeId,long classSectionId,String dateType){
		return adminDao.getMaxExamDateByExamTypeIdAndClassSectionId(examtypeId,classSectionId,dateType);
	}
	public List<Object[]> getAllClassAndSectionsByCustIdAndAcademicYearId(long custId,long academicYearId){
		return adminDao.getAllClassAndSectionsByCustIdAndAcademicYearId(custId,academicYearId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public Object[] getMediumWiseStudentDetailsForEachClass(long custId,long academicYearId,String classNameClassId, List schoolMediumsListList,String username){
		 return adminDao.getMediumWiseStudentDetailsForEachClass(custId,academicYearId,classNameClassId,schoolMediumsListList,username);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public Object[] getAllStaffCountByReligionWide(long custId,long academicYearId,List  religionsList, List motherToungsList,long organiZationTypeId,String religionIds,String username){
		 return adminDao.getAllStaffCountByReligionWide(custId,academicYearId,religionsList,motherToungsList,organiZationTypeId,religionIds,username);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public Object[] getAllStudentsCountByReligionWide(long custId,long academicYearId,List religionsList,List motherToungsList,long classId,String religionIds,String username){
		 return adminDao.getAllStudentsCountByReligionWide(custId,academicYearId,religionsList,motherToungsList,classId,religionIds,username);
	 }
	 public int getPaidAmountByClassId(String table,long custId,long classId,long academicYearId,long categoryId,SchoolFeeSetting schoolFeeSetting){
		 return adminDao.getPaidAmountByClassId(table,custId, classId, academicYearId, categoryId,schoolFeeSetting);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public Object[] getAllSyllabusTypesByCustId(long custId){
		 return adminDao.getAllSyllabusTypesByCustId(custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public StudentAcademicPerformance getStudentAcademicPerformanceByStudentIdAndstudentActivityTypeIdAndExamTypeId(long studId, long studentActivityTypeId, long examTypeId,long custId, long academicYearId) {
			return adminDao.getStudentAcademicPerformanceByStudentIdAndstudentActivityTypeIdAndExamTypeId(studId, studentActivityTypeId, examTypeId, custId, academicYearId);
	}
	public void removeSyllabusTypeInfoByCustId(long customerId) {
		adminDao.removeSyllabusTypeInfoByCustId(customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Address saveAddress(Address address) throws DataAccessException{
	    return adminDao.saveAddress(address);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<ViewStaffPersonAccountDetails> getStaffDetailsBySearchName(String rollNumber,long custId,String status){
		 return adminDao.getStaffDetailsBySearchName(rollNumber, custId, status);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<AbstractEntityPersister> getAllTableNames(){
		 return adminDao.getAllTableNames();
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<Object[]> getAllUsersByCretedBy(String table){
		 return adminDao.getAllUsersByCretedBy(table);
	 }
	 public void updateCreatedByRecords(String table,String accountId,String fullName){
		 adminDao.updateCreatedByRecords(table,accountId,fullName);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<Object[]> getAllUsersByUpdatedBy(String table){
		 return adminDao.getAllUsersByUpdatedBy(table);
	 }
	public void updateLastUpdatedByRecords(String table,String accountId,String fullName){
		adminDao.updateLastUpdatedByRecords(table,accountId,fullName);
	}
	public void removeLastUpdatedByAndCreatedByRecords(String table){
		adminDao.removeLastUpdatedByAndCreatedByRecords(table);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStudentFeeDefaultersByCustId(String queryString,long customerId,long academicYearId,long termId,String today,String transportmode){//,String paymentStatus
		return adminDao.getAllStudentFeeDefaultersByCustId(queryString, customerId, academicYearId,termId,today,transportmode);//,paymentStatus
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Staff getStaffByCustIdAndStaffIdAndStatus(long staffId,long custId,String status,long academicYearId){
		return adminDao.getStaffByCustIdAndStaffIdAndStatus(staffId,custId,status,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllVisitorsByBetweenDated(long custId,long academicYearId,String inDate,String outDate){
		return adminDao.getAllVisitorsByBetweenDated(custId, academicYearId,inDate,outDate);//,paymentStatus
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassNameIdsFromStudyClassIds(String studyClassIds){
		return adminDao.getClassNameIdsFromStudyClassIds(studyClassIds);
	}
	
	public void updateCourseCompletedStudents(long classId,String failurePromotStudIds,long userId){
		 adminDao.updateCourseCompletedStudents(classId,failurePromotStudIds,userId);
	}
	public void updateStudentRollNumberByClassSectionId(long classSectionId){
		 adminDao.updateStudentRollNumberByClassSectionId(classSectionId);
	}
	public void updateSubjectsOrder(long subjectId,long sortingOrder){
		adminDao.updateSubjectsOrder(subjectId,sortingOrder);	
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStudentInvoiceFeeDetailsByVehicleIdAndCustId(String queryString,long customerId,long academicYearId,long termId,String vehicleAcademicId,String status){//,String paymentStatus
		return adminDao.getAllStudentInvoiceFeeDetailsByVehicleIdAndCustId(queryString, customerId, academicYearId,termId,vehicleAcademicId,status);//,paymentStatus
	}
	public void removeExamScheduleMarks(long classScctionId,long examTypeId,long academicYearId,long custId){
		adminDao.removeExamScheduleMarks(classScctionId,examTypeId,academicYearId,custId);
	}
	public void removeStudentMarksByClasssSectionId(long classScctionId,long examTypeId,long academicYearId,long custId){
		adminDao.removeStudentMarksByClasssSectionId(classScctionId,examTypeId,academicYearId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public SMSServiceProviders saveSMSUrl(SMSServiceProviders smsProvider){
		return adminDao.saveSMSUrl(smsProvider);
	}
	public void updateRemainingSMSProviderstoInactiveState(long providerId){
		 adminDao.updateRemainingSMSProviderstoInactiveState(providerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getSchoolTermsByCustIdAndAcademicYearId(String table,long custId,long academicYearId){
		return adminDao.getSchoolTermsByCustIdAndAcademicYearId(table,custId,academicYearId);
	}
	public List<Building> checkBuildingId(String buildingName,long custId,long academicYearId){
		return adminDao.checkBuildingId(buildingName,custId,academicYearId);
	}
	
	public int getMaxPeriodsByClassSectionIdAndDaydId(String classSectionId, String dayId,String periodType){
		return  adminDao.getMaxPeriodsByClassSectionIdAndDaydId(classSectionId,dayId,periodType);
	}
	public List<ViewClassSubjectsSettings> getAllClassWiseSubjects(String clause){
		return  adminDao.getAllClassWiseSubjects(clause);
	}
	public void updateTimeTableTeacherIdandSubjectIdsByAcademicYearId(long academicYearId){
		adminDao.updateTimeTableTeacherIdandSubjectIdsByAcademicYearId(academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudyClassSubjects> getAllStudyClassSubjects(String clause){
		return  adminDao.getAllStudyClassSubjects(clause);
	}
	public void updateClassSubjectsPeriodsCount(long classSectionId){
		adminDao.updateClassSubjectsPeriodsCount(classSectionId);
	}
	public void updateClassTeacherHandlePeriodsCount(long classSectionId){
		adminDao.updateClassTeacherHandlePeriodsCount(classSectionId);
	}
	public void updateClassStatus(long custId,long academicYearId) {
		adminDao.updateClassStatus(custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentClassDetails> getAllStudentDetailsByGender(long custId, long academicYearId, long classSectionId)
	{
		  return adminDao.getAllStudentDetailsByGender(custId,academicYearId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public OverAllGrades getOverAllGradeByName(String gradName, long academicYearId){
		return adminDao.getOverAllGradeByName(gradName, academicYearId);
	}
	public void updateExamTypesOrder(long examTypeId,long sortingOrder){
		adminDao.updateExamTypesOrder(examTypeId,sortingOrder);
	}
	public void updateSubTypesOrder(long subTypeId,long sortingOrder){
		adminDao.updateSubTypesOrder(subTypeId,sortingOrder);
	}
	public void updateTimeTableDetailsByClassSectionIdAndSubjectIds(long classSectionId,String subjectIds){
		adminDao.updateTimeTableDetailsByClassSectionIdAndSubjectIds(classSectionId,subjectIds);
	}
	public void updateAdmissionSettingsStatus(long admissionSettingId,long custId){
		adminDao.updateAdmissionSettingsStatus(admissionSettingId,custId);
	}
	public void removeClassSubjectsSettingsByClassSectionId(long studyClassId){
		adminDao.removeClassSubjectsSettingsByClassSectionId(studyClassId);
	}
	public void removeTimeTablePeriodsByClassSectionId(long studyClassId){
		adminDao.removeTimeTablePeriodsByClassSectionId(studyClassId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public AdmissionSettings saveAdmissionSettings(AdmissionSettings admissionSettings){
		return adminDao.saveAdmissionSettings(admissionSettings);
	}
	public void updateClassAdmissionStatusToActive(String classIds,long academicYearId){
		 adminDao.updateClassAdmissionStatusToActive(classIds,academicYearId);
	}
	public void generateShortListApplications(long custId,long academicYearId,String applicationId){
		 adminDao.generateShortListApplications(custId,academicYearId,applicationId);
	}
	public void generateRejectedListApplications(long custId,long academicYearId,String applicationId){
		 adminDao.generateRejectedListApplications(custId,academicYearId,applicationId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public SchoolHolidays getSchoolHolidayByDate(long custId,String selectedDate)
	{
		return adminDao.getSchoolHolidayByDate(custId,selectedDate);
	}
	public void updateStudentDailyAttendance(long studentId,String leaveRequest,String attendanceDate)
	{
		adminDao.updateStudentDailyAttendance(studentId,leaveRequest,attendanceDate);
	}
	public void updateStudentTransport(long studentId,Long vehicleId,Long boardingPointId)
	{
		adminDao.updateStudentTransport(studentId,vehicleId,boardingPointId);
		
	}
	@Transactional
	public Integer fetchTotalWorkingDays(long academicYearId,String endDate){
		return adminDao.fetchTotalWorkingDays(academicYearId,endDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStudentObtainedActivitiesGradesByStudIdAndExamTypeIds(long studId,String examTypeIds,long activityId,long classId){
		return adminDao.getStudentObtainedActivitiesGradesByStudIdAndExamTypeIds(studId,examTypeIds,activityId,classId);
	}
	public void updateClassTeacherStatus(long custId,long academicYearId,String classTeacher,long studyClassId){
		 adminDao.updateClassTeacherStatus(custId,academicYearId,classTeacher,studyClassId);
	}
	
	public List geAllSchoolHolidaysListByAcademicYearId(long custId,long academicYearId) {
		return  adminDao.geAllSchoolHolidaysListByAcademicYearId(custId, academicYearId); 
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaidAndNotPaidDetails> getStudentsFeePaidAndNotPaidDetails(String clause){
		return  adminDao.getStudentsFeePaidAndNotPaidDetails(clause);
	}
	public String getBookSettingsCreatedClassIdsByAcademicYearId(long academicYearId,String type){
		return  adminDao.getBookSettingsCreatedClassIdsByAcademicYearId(academicYearId,type);
	}
	public List<Customer> findExistCustomer(String keyWord) {
		return  adminDao.findExistCustomer(keyWord);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeId(long studId,long examTypeId,long custId,long academicYearId){
		return adminDao.getStudentMarksByStudentIdAndExamtypeId(studId,examTypeId,custId,academicYearId);
	}
	
	public List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeIdAndScheduleId(long studId,long examTypeId,long custId,long academicYearId,long scheduleId){
		return adminDao.getStudentMarksByStudentIdAndExamtypeIdAndScheduleId(studId,examTypeId,custId,academicYearId,scheduleId);
	}
	
	public boolean isUserAsClassTeacher(long accountId,long classSectionId,long academicYearId){
		return adminDao.isUserAsClassTeacher(accountId,classSectionId,academicYearId);
	}
	@Transactional
	public int getAssignedTimeTableSubjectsSettingsCount(long academicYearId){
		return adminDao.getAssignedTimeTableSubjectsSettingsCount(academicYearId);
	}
	public int getPaidAmountByBoardingPointWise(String table,long custId,long academicYearId,long categoryId,long feeSettingId,String trnaportMode,String boardingPointIds){
		return adminDao.getPaidAmountByBoardingPointWise(table,custId,academicYearId,categoryId,feeSettingId,trnaportMode,boardingPointIds);
	}
	
	@Transactional
	public Integer fetchStudentAbsentiesCount(long studentId,String endDate){
		return adminDao.fetchStudentAbsentiesCount(studentId,endDate);
	}
	@Transactional
	public void calculateStudentsSubjectPosition(long classSectionId,long examTypeId){
		adminDao.calculateStudentsSubjectPosition(classSectionId,examTypeId);
	}
	public void updateStudentHostel(long studentId,long roomId)
	{
		adminDao.updateStudentHostel(studentId,roomId);
	}
	@Transactional
	public List<ViewClassSectionTeacher> getAllClassSectionTeachersDetails(String clause){
		return adminDao.getAllClassSectionTeachersDetails(clause);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<SchoolTerms> getFeeTermsByRemainderDays(long academicYearId){
		return adminDao.getFeeTermsByRemainderDays(academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ExamSchedules> getExamSchedulesForSendingMobileAlerts(long academicYearId){
		return adminDao.getExamSchedulesForSendingMobileAlerts(academicYearId);
	}
	@Transactional
	public List<ExamSchedules> getAllExamSchedulesForSendingEmailAlerts(long academicYearId,long classSectionId){
		return adminDao.getAllExamSchedulesForSendingEmailAlerts(academicYearId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<StudentMarks> getLatestUploadedStudentsMarks(long classSectionId,long academicYearId,int noOfExamTypes){
		return adminDao.getLatestUploadedStudentsMarks(classSectionId,academicYearId,noOfExamTypes);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<StudentMarks> getLatestUploadedExamTypesByStudentId(long studId,int noOfExamTypes){
		return adminDao.getLatestUploadedExamTypesByStudentId(studId,noOfExamTypes);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public long getStudentPaidAmountByTermIdAndClassId(long studentId,long classId,long custId,long academicYearId,long termId,String dueDate,long feeSettingId,String checkDueDate){
		return adminDao.getStudentPaidAmountByTermIdAndClassId(studentId, classId, custId, academicYearId, termId,dueDate,feeSettingId,checkDueDate);
	}
	public String removeAllAcademicYearTimingsByAcademicYearId(long academicYearId) {
		return adminDao.removeAllAcademicYearTimingsByAcademicYearId(academicYearId);
	}
	public String getStudyCertificateBookSettingsCreatedClassIdsByAcademicYearId(long academicYearId){
		return adminDao.getStudyCertificateBookSettingsCreatedClassIdsByAcademicYearId(academicYearId);
	}
	@Transactional
	public void addStudentRollNumbersForNonAssignedStudentsByClassSectionId(long classSectionId,int lastAssignedRollNumber){
		adminDao.addStudentRollNumbersForNonAssignedStudentsByClassSectionId(classSectionId,lastAssignedRollNumber);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewAssignedVehiclestoRoutesWithBoardingPoints> getAllViewAssignedVehiclestoRoutesWithBoardingPoints(String clause){
		 return adminDao.getAllViewAssignedVehiclestoRoutesWithBoardingPoints(clause);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getAllStudentNonPaidClassFeePaymentDetails(String tableName,long studentId,long academicYearId,long termId){
		return adminDao.getAllStudentNonPaidClassFeePaymentDetails(tableName,studentId,academicYearId,termId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseNonPaidClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId){
		return adminDao.getAllStudentTermsWiseNonPaidClassFeePaymentDetails(studentId,academicYearId,feeSettingId);
	}
	public void updateStudentPartialFeePaymentStatus(long studentId,long feeId,long transportFeeId){
		 adminDao.updateStudentPartialFeePaymentStatus(studentId,feeId,transportFeeId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public ViewStudentFeePaymentDetails getStudentNonPaidClassFeePaymentDetails(long studentId,long academicYearId,long termId,long feeTypeId){
		return adminDao.getStudentNonPaidClassFeePaymentDetails(studentId,academicYearId,termId,feeTypeId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewOnlineApplicationStudentClassFees> getTermsWiseNonPaidClassFeePaymentDetailsForAdmissions(long classId,long academicYearId,String feeSettingId,long categoryId,String feeIds,long partialFeeId,double partialFeeAmount){
		return adminDao.getTermsWiseNonPaidClassFeePaymentDetailsForAdmissions(classId,academicYearId,feeSettingId,categoryId,feeIds,partialFeeId,partialFeeAmount);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewOnlineApplicationStudentClassFees> getNonPaidClassFeePaymentDetailsForAdmissions(long classId,long academicYearId,long termId,long categoryId,String feeIds,long partialFeeId,double partialFeeAmount){
		return adminDao.getNonPaidClassFeePaymentDetailsForAdmissions(classId,academicYearId,termId,categoryId,feeIds,partialFeeId,partialFeeAmount);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId){
		return adminDao.getAllStudentTermsWiseClassFeePaymentDetails(studentId,academicYearId,feeSettingId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getAllStudenClassFeePaymentDetails(long studentId,long academicYearId,long termId){
		return adminDao.getAllStudenClassFeePaymentDetails(studentId,academicYearId,termId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentsExcessPaymentDetails> getAllStudentsExcessPayments(String classSectionId,long academicYearId,long custId){
		return adminDao.getAllStudentsExcessPayments(classSectionId,academicYearId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentClassFeeDetails(long studentId,long academicYearId,long termId){
		return adminDao.getStudentClassFeeDetails(studentId,academicYearId,termId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentFutureAcademicTermsWiseNonPaidClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId){
		return adminDao.getStudentFutureAcademicTermsWiseNonPaidClassFeePaymentDetails(studentId,academicYearId,feeSettingId,classId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentFutureClassFeeDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId){
		return adminDao.getStudentFutureClassFeeDetails(studentId,academicYearId,termId,classId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentNonPaidFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId){
		return adminDao.getStudentNonPaidFutureClassFeePaymentDetails(studentId,academicYearId,termId,classId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public ViewStudentFeePaymentDetails getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(long studentId,long academicYearId,long termId,long feeTypeId,long classId,long classSectionId){
		return adminDao.getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(studentId,academicYearId,termId,feeTypeId,classId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseFutureClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId){
		return adminDao.getAllStudentTermsWiseFutureClassFeePaymentDetails(studentId,academicYearId,feeSettingId,classId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getAllStudentFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId){
		return adminDao.getAllStudentFutureClassFeePaymentDetails(studentId,academicYearId,termId,classId,classSectionId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getFutureAcademicYearStudentFeeReceipts(long studentId,long academicYearId,String invoiceNumber){
		return adminDao.getFutureAcademicYearStudentFeeReceipts(studentId,academicYearId,invoiceNumber);
	}

public List<ViewExamScheduleDetails> getUsersStartAndEndDateExamSchedulesDetails(long accountId,long academicYearId,String schedulesType,boolean isStaff,long classSectionId,long studentId){
		try{
			StringBuffer query = null;
			Student student = null;
			if(studentId != 0){
				student = (Student)adminDao.get(Student.class, studentId);
			}
			else if(accountId != 0 && academicYearId != 0){
				student = (Student)adminDao.get(Student.class, "accountId="+accountId+" and academicYearId="+academicYearId);
			}
			if(!ObjectFunctions.isNullOrEmpty(student)){
				List<ViewExamScheduleDetails> upcmgSchedules = new ArrayList<ViewExamScheduleDetails>();
				List<Object[]> examSchedules = this.getAll("select subjectName,subTypeName,examDate,examTypeName,classSectionId,examTypeId,className,custId,startTime,endTime from vw_examScheduleDetails where classSectionId="+student.getClassSectionId()+" and endDate >='"+DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date())+"'");
				if(ObjectFunctions.isNotNullOrEmpty(examSchedules)){
					for(Object[] object : examSchedules){
					  if(!ObjectFunctions.isNullOrEmpty(object)){
						  ViewExamScheduleDetails viewExamSecDetails=new ViewExamScheduleDetails();
						  viewExamSecDetails.setSubjectName(object[0].toString());
						  viewExamSecDetails.setSubTypeName(object[1].toString());
						  viewExamSecDetails.setExamTypeName(object[3].toString());
						  viewExamSecDetails.setClassSectionId(Long.valueOf(object[4].toString()));
						  viewExamSecDetails.setExamTypeId(Long.valueOf(object[5].toString()));
						  viewExamSecDetails.setClassName(object[6].toString());
						  viewExamSecDetails.setCustId(Long.valueOf(object[7].toString()));
						  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
						  Date convertedDate = (Date) formatter.parse(object[2].toString());
						  viewExamSecDetails.setExamDate(DateFormatter.parseString(DateFormatter.MMDDCCYY_PATTERN, DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,convertedDate)));
						  viewExamSecDetails.setStartTime(object[8].toString());
						  viewExamSecDetails.setEndTime(object[9].toString());
						  upcmgSchedules.add(viewExamSecDetails);
						  viewExamSecDetails = null;
					  }
		    		}
				}
				return upcmgSchedules;
				
			}
			else{
				if(classSectionId == 0){
					if(isStaff){
						List<String> classSectionIds = this.getAll("select studyClassId from classTeacher where academicYearId="+academicYearId+" and teacherId in (select id from staff where accountId="+accountId+" and status='Y') group by studyClassId");
						String teacherStudyClassId=null;
						if(!ObjectFunctions.isNullOrEmpty(classSectionIds))
							teacherStudyClassId=StringFunctions.convertListToCommaDelimitedString(classSectionIds);
						else
							teacherStudyClassId="0";
						query = new StringBuffer("from ViewExamScheduleDetails schedule WHERE schedule.classSectionId in("+teacherStudyClassId+")");
					}else{
						query = new StringBuffer("from ViewExamScheduleDetails schedule WHERE schedule.academicYearId=").append(academicYearId);
					}
				}else{
					query = new StringBuffer("from ViewExamScheduleDetails schedule WHERE schedule.classSectionId=").append(classSectionId);
				}
				if(!StringFunctions.isNullOrEmpty(schedulesType)){
				if("completed".equalsIgnoreCase(schedulesType))
					query.append(" and schedule.endDate < '").append(DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date()));
				if("future".equalsIgnoreCase(schedulesType))
					query.append(" and schedule.endDate >= '").append(DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date()));
				}
				else
					query.append(" and schedule.endDate >= '").append(DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date()));
				if(classSectionId == 0)
					query.append("' group by schedule.classSectionId,schedule.examTypeId");
				else
					query.append("' group by schedule.examTypeId order by startDate ASC LIMIT 1");
				return adminDao.getAllHqlQuery(query.toString());
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional	
	public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds){
		return adminDao.getStudentsFeePaidDetailsBySettingAndClassId(tableName,custId,academicYearId,classSectionIds,fromDate,toDate,feeSettingIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public AdmissionSettings getOpenedAdmissions(long custId){
		StringBuffer query = new StringBuffer("custId=").append(custId).append(" and status='Y'");
		return (AdmissionSettings)adminDao.get(AdmissionSettings.class, query.toString());
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewFee> getViewFeeDetails(String clause){
		return adminDao.getViewFeeDetails(clause);
	}
	public long getFineFeeMaxInvoiceNumber(long custId,long academicYearId){
		long receiptNumber = 0;
		try{
			Customer customer = (Customer)adminDao.get(Customer.class, custId);
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				if(customer.isAcademicWiseFeeReceipt())
					receiptNumber = adminDao.getInvoiceNumberByCustId("fineFee", custId,academicYearId);
				else
					receiptNumber = adminDao.getInvoiceNumberByCustId("fineFee", custId,0);
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return ++receiptNumber;
	}
	
	public ClassName addClass(ClassName objClassName, long custId, long userId, String section, String subjectIds)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'addClass' method");
		}
		try {
				AcademicYear academicYear = this.getCurrentAcademicYear("Y", custId);
				objClassName.setDescription(objClassName.getClassName().toUpperCase());
				objClassName.setClassName(objClassName.getDescription());
				objClassName.setCreatedById(userId);
				objClassName.setLastAccessDate(new Date());
				objClassName.setCreatedDate(new Date());
				objClassName.setLastUpdatedDate(new Date());
				objClassName.setCustId(custId);
				objClassName.setAcademicYear(academicYear);
				int maxSortingOrder = this.getMaxOfClassNameSortingOrder(academicYear.getId(),custId);
				objClassName.setSortingOrder(maxSortingOrder + 1);
				objClassName= this.saveClassName(objClassName);
				StringBuffer query = new StringBuffer("id in(").append(subjectIds).append(")");
				List<StudySubject> studySubjects = this.getAll(StudySubject.class,query.toString());
				query = null;
				if(StringFunctions.isNullOrEmpty(section)){
					this.createStudyClass(objClassName,"",academicYear,studySubjects, custId, userId);
				}else{
					List<String> sections = Arrays.asList(section.split(","));
					Collections.sort(sections);
					Set<String> sectionsSet = new LinkedHashSet<String>(sections);
					Section objSection = null;
					StudyClass studyClass = null;
					for (String sectionName : sectionsSet) {
						if (StringFunctions.isNotNullOrEmpty(sectionName)) {
							query = new StringBuffer("section = '").append(sectionName.toUpperCase()).append("' ");
							query.append("and custId=").append(custId);
							objSection = (Section) this.get(Section.class, query.toString());
							if(ObjectFunctions.isNullOrEmpty(objSection)){
								objSection = new Section();
								objSection.setSection(sectionName.toUpperCase());
								objSection.setCustId(custId);
								objSection.setCreatedById(userId);
								objSection.setCreatedDate(new Date());
								objSection.setLastAccessDate(new Date());
								objSection.setLastUpdatedDate(new Date());
								objSection = (Section) this.save(objSection);
							}
							studyClass = this.getclassByClassAndsection(objClassName.getClassName(),objSection.getSection(),custId,academicYear.getId());
							if (ObjectFunctions.isNullOrEmpty(studyClass)) {
								this.createStudyClass(objClassName,objSection.getSection(),academicYear,studySubjects, custId, userId);
							}
						}
						sectionName = null;
						section = null;
						studyClass = null;
					}
					sections = null; 
					sectionsSet = null;
				}
				studySubjects = null;
				academicYear = null;
				
				return objClassName;
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	/**  For creating StudyClass Object. 
	 * @param ClassName object, 
	 * @param sectionName (if section is null pass empty string)
	 * @param AcademicYear object, 
	 * @param StudySubjects list for this class (optional)
	 */
	public StudyClass createStudyClass(ClassName className,String section,AcademicYear academicYear,List<StudySubject> studySubjects,long custId, long userId){
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'createStudyClass' method");
		}
		try {
			StudyClass studyClass = new StudyClass();
			studyClass.setCreatedById(userId);
			studyClass.setCreatedDate(new Date());
			studyClass.setLastAccessDate(new Date());
			studyClass.setLastUpdatedDate(new Date());
			studyClass.setClassName(className.getClassName());
			studyClass.setSectionCapacity(className.getNoOfStudents());
			studyClass.setClassNameClassId(className);
			studyClass.setCustId(custId);
			studyClass.setSection(section);
			studyClass.setAcademicYear(academicYear);
			if(ObjectFunctions.isNullOrEmpty(studySubjects))
				studyClass.setSubjects(null);
			else	
				studyClass.setSubjects(ConvertUtil.convertListToSet(studySubjects));
			studyClass.setLastUpdatedById(userId);
			studyClass.setLastUpdatedDate(new Date());
			studyClass.setLastAccessDate(new Date());
			return (StudyClass) this.saveOrUpdateObject(studyClass);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	/** Returns ClassName list by user academicYearId with sort by ClassName sorting order.
	 * Method  modified by Narahari 24/April/2013 for performance issue
	*/
	public List<ClassName> getAllClassNames(long custId, long academicYearId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering  AdminManager 'getAllClassNames' method");
		}
		
		try {
			StringBuffer query = new StringBuffer("academicYearId=").append(academicYearId).append(" and custId=").append(custId).append(" order by sortingOrder");
			List<ClassName> classNamesList = this.getAll(ClassName.class,query.toString());
			query=null;
			if(ObjectFunctions.isNullOrEmpty(classNamesList))
				classNamesList = null;			
			return classNamesList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
 	
	/**
	 * Chaged by seshu on 17th April 2013. This method calls from Academics -> Subjects -> Add Subject and submit the form. For Subject creation and edit process.*/
	public  Map<String,String>  addUpdateSubject(StudySubject oSubject, long custId, long userId) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'AddUpdateSubjects' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try {
			 	StudySubject subject = null;
				if(oSubject.getId() > 0){
					subject = (StudySubject)this.get(StudySubject.class,oSubject.getId());
				}
				else{
					subject = new StudySubject();
					subject.setCreatedById(userId);
					subject.setCreatedDate(new Date());
					AcademicYear academicYear = this.getCurrentAcademicYear("Y", custId);
					Object[] maxSort=this.get("select IFNULL(max(sortingOrder),0) sortingOrder,name from studySubject where academicYearId="+academicYear.getId());
					subject.setSortingOrder(Integer.valueOf(maxSort[0].toString()) + 1);
					subject.setAcademicYear(academicYear);
					subject.setCustId(custId);
					
				}
				subject.setLastUpdatedById(userId);
				subject.setLastUpdatedDate(new Date());
				subject.setLastAccessDate(new Date());
				subject.setSubjectNumber(oSubject.getSubjectNumber());
				subject.setName(oSubject.getName().trim());
				subject.setDescription(oSubject.getDescription());
				subject.setLanguage(oSubject.isLanguage());
				if(!StringFunctions.isNullOrEmpty(oSubject.getSubjectType()))
					subject.setSubjectType(oSubject.getSubjectType());
				else
					subject.setSubjectType("N");
				this.save(subject);
				subject=null;
				msg.put("0", "Subject added/updated successfully");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		return msg;
	}
	
	/**
	 * Chaged by seshu on 17th April 2013. This method calls from Academics -> Subjects -> Change Subjects Order -> change subject event is done. For storing subjects order.*/
	public Map<String, String> UpdateSubjectOrder(String subOrders) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering  AdminManager 'UpdateSubjectOrder' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try {
			if(StringFunctions.isNotNullOrEmpty(subOrders)){
				JSONArray classJSONArray=new JSONArray(subOrders);
				JSONObject classJson=null;
				long subId=0;
				long sortingOrder=0;
				for(int i=0;i<classJSONArray.length();i++)
				{
					classJson=classJSONArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(classJson))
					{
						subId = Long.valueOf((String)classJson.get("subId"));
						sortingOrder =Long.valueOf((Integer)classJson.get("sortingOrder"));
						 if(subId > 0 && sortingOrder > 0){
								this.updateSubjectsOrder(subId,sortingOrder);
						 }
				     }
					classJson=null;
			     }
				classJSONArray=null;
				msg.put("0", "Subject order updated successfully."); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		return msg;
	}
	@Transactional
	public void checkStudentFeePaidStatus(long academicYearId,long custId,Student student){
		StringBuffer queryString = new StringBuffer();
		if (!ObjectFunctions.isNullOrEmpty(student)) {
			queryString.append("status in ('" + Constants.SCHOOL_MODULE+ "'");
			if (Constants.TRANSPORT_STATUS.equalsIgnoreCase(student.getTransportMode())&& !ObjectFunctions.isNullOrEmpty(student.getBed())) {
				queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
				if(student.getBed().getId() !=0)
					queryString.append(",'" + Constants.HOSTEL_STATUS + "'");
			}if (Constants.TRANSPORT_STATUS.equalsIgnoreCase(student.getTransportMode())) {
				queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
			}if (StringFunctions.isNotNullOrEmpty(student.getHostelMode()) && "H".equalsIgnoreCase(student.getHostelMode().trim())) {
				queryString.append(",'" + Constants.HOSTEL_STATUS + "'");
			}
			queryString.append(")");
		}
		List<SchoolFeeSetting> feeSettingList=super.getAll(SchoolFeeSetting.class,queryString.toString());
		queryString=null;
		if (!ObjectFunctions.isNullOrEmpty(feeSettingList)) {
			queryString= new StringBuffer();
			queryString.append(" and feeSettingId in (0");
			for(SchoolFeeSetting feeSetting: feeSettingList){
				queryString.append(","+String.valueOf(feeSetting.getId()));
			}
			queryString.append(")");
		}
		List<Object[]> stdObject = null;
		if(student.getFeeConfigured().equalsIgnoreCase("Y")){
			if(!ObjectFunctions.isNullOrEmpty(queryString)){
				log.debug("select studentId,paymentStatus,invoiceNumber,id from vw_studentFeePaymentDetails where academicYearId="+ academicYearId + " and custId="+ custId + "  and studentId="+ student.getId() +" and paymentStatus='"+ Constants.NO_STRING+ "' and deleteStatus='"+ Constants.NO_STRING+ "'"+queryString+" group by studentId,paymentStatus");
				stdObject = adminDao.getAll("select studentId,paymentStatus,invoiceNumber,id from vw_studentFeePaymentDetails where academicYearId="+ academicYearId + " and custId="+ custId + "  and studentId="+ student.getId() +" and paymentStatus='"+ Constants.NO_STRING+ "' and deleteStatus='"+ Constants.NO_STRING+ "'"+queryString+" group by studentId,paymentStatus");
			}else{
				stdObject = adminDao.getAll("select studentId,paymentStatus,invoiceNumber,id from vw_studentFeePaymentDetails where academicYearId="+ academicYearId + " and custId="+ custId + "  and studentId="+ student.getId() +" and paymentStatus='"+ Constants.NO_STRING+ "' and deleteStatus='"+ Constants.NO_STRING+ "' group by studentId,paymentStatus");
			 }
			//ViewStudentTransportFeePaymentDetails
			List<Object[]> stdTransportObject=adminDao.getAll("select studentId,paymentStatus,invoiceNumber,id from vw_studentTransportFeePaymentDetails where academicYearId="+ academicYearId + " and custId="+ custId + "  and studentId="+ student.getId() +" and paymentStatus='"+ Constants.NO_STRING+ "' and deleteStatus='"+ Constants.NO_STRING+ "' group by studentId,paymentStatus");
			if(!ObjectFunctions.isNullOrEmpty(stdTransportObject))
				stdObject.addAll(stdTransportObject);
			queryString=null;
			/* This condition for if student fee paid fully. We will update feePaidStatus as "F" in student table. */
			if(ObjectFunctions.isNullOrEmpty(stdObject)){
				adminDao.updateStudentFeePaidStatus(student.getId(),"F");
				stdObject=null;
			}else {
				/* If the student paid at least one payment we will update peePaidStatus as "P" in student table. */
				StudentPayment studentPayment = (StudentPayment)adminDao.get(StudentPayment.class, "custId="+custId+" and academicYearId="+academicYearId+" and studentId="+student.getId()+" and deleteStatus='"+Constants.NO_STRING+"' and concessionStatus='N' ");// and invoiceNumber != 0
				if(!ObjectFunctions.isNullOrEmpty(studentPayment))
					adminDao.updateStudentFeePaidStatus(student.getId(),"P");
				else
					adminDao.updateStudentFeePaidStatus(student.getId(),"N");
			}
			/**/
		}
		else if(student.getFeeConfigured().equalsIgnoreCase("N")){
			adminDao.updateStudentFeePaidStatus(student.getId(),"N");
		}
	}
	public void updateStudentPaymentStatus(long classId,long categoryId){
		Fee fee =(Fee)adminDao.get(Fee.class, "classId="+classId+" and categoryId="+categoryId+" and feeAmount != 0");
		if(!ObjectFunctions.isNullOrEmpty(fee)){
			/* If we update any or create any fee particular we will update feeConfigured column as "Y" and we will update student feePaidStatus as "P". Who are have "F" status in feePaidStatus */
			adminDao.updateFeeConfiguredStatusInStudent(classId,categoryId,"Y");
			/* In class Fee edit if we create/edit any new fee to class we will update as "P" who are paid fully paid students. That means who are have "F" status */
			adminDao.updateStudentPaymentStatus(classId,categoryId,"P");
		}else {
			adminDao.updateFeeConfiguredStatusInStudent(classId,categoryId,"N");
			adminDao.updateStudentPaymentStatus(classId,categoryId,"N");
		}
	}
	public void updateCustomersFeeUpdatesInStudent(long customerId){
		
		List<AcademicYear> academicYearList=adminDao.getAll(AcademicYear.class, "custId="+customerId);
		if(!ObjectFunctions.isNullOrEmpty(academicYearList)){
			for(AcademicYear academicYear : academicYearList){
				log.debug("customer name :"+customerId+" :  academic Year :"+academicYear.getAcademicYear());
				List<SchoolCategory> categoryList=adminDao.getAll(SchoolCategory.class, "custId="+customerId);
				List<ClassName> classList=adminDao.getAll(ClassName.class, "custId="+customerId+" and academicYearId="+academicYear.getId());
				if(!ObjectFunctions.isNullOrEmpty(categoryList) && !ObjectFunctions.isNullOrEmpty(classList)){
					for(ClassName className : classList){
						if(!ObjectFunctions.isNullOrEmpty(className)){
							log.debug("checking "+className.getClassName()+" className .....");
							for(SchoolCategory category : categoryList){
								if(!ObjectFunctions.isNullOrEmpty(category)){
									List<Fee> feeList =adminDao.getAll(Fee.class, "custId="+customerId+" and classId="+className.getId()+" and academicYearId="+academicYear.getId()+" and categoryId="+category.getId()+" and feeAmount != 0");
									//log.debug("custId="+customerId+" and classId="+className.getId()+" and academicYearId="+academicYear.getId()+" and categoryId="+category.getId()+" and feeAmount != 0");
									if(!ObjectFunctions.isNullOrEmpty(feeList)){
										log.debug("checking "+category.getCategoryName()+" category data....."+feeList.size());
										adminDao.updateFeeConfiguredStatusInStudent(className.getId(),category.getId(),"Y");
										List<Student> studentList= adminDao.getAll(Student.class, "custId="+customerId+" and academicYearId="+academicYear.getId()+" and classNameClassId="+className.getId()+" and categoryId="+category.getId());
										if(!ObjectFunctions.isNullOrEmpty(studentList)){
											log.debug("student status updating......");
											for(Student student : studentList){
												this.checkStudentFeePaidStatus(academicYear.getId(), customerId, student);
												student=null;
											}
											studentList=null;
										}
									}
									category=null;
								}
							}
							className=null;
						}
					}
					categoryList=null;
					classList=null;
				}
				academicYear=null;
			}
			academicYearList=null;
		}
	}
	public List<VWFeedbackRatingDetails> teacherClassRatingDescription(long custId, String staffId, String feedBackQuesId) {

		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'teacherClassRatingDescription' method");
		}
		List<VWFeedbackRatingDetails> feedBackRatingDetails=null;
		try {
			VWFeedbackRatingDetails feedbackResultDes=null;
			double resultGradeValue=0.0;
			if(Long.valueOf(staffId)>0){
				feedBackRatingDetails = new ArrayList<VWFeedbackRatingDetails>();
				List<Object[]> feedbackQuestionsList=this.getAll("select count(studentId),sum(resultGradeValue),qusDescription,description,studentId from vw_FeedbackRatingDetails where custId="+custId+" and staffId ="+staffId+" group by qusDescription order by qusDescription");
				ViewStudentPersonAccountDetails vwsDetails=null;
				for (Object[] feedBackObj : feedbackQuestionsList) {
					vwsDetails=(ViewStudentPersonAccountDetails) this.get(ViewStudentPersonAccountDetails.class,"studentId="+Long.valueOf(feedBackObj[4].toString())+" group by studentId order by fullName");
					if(!ObjectFunctions.isNullOrEmpty(vwsDetails)){
						feedbackResultDes=new VWFeedbackRatingDetails();
						resultGradeValue=Double.valueOf(feedBackObj[1].toString());
						if(!ObjectFunctions.isNullOrEmpty(feedBackObj[2]))
						feedbackResultDes.setQusDescription(feedBackObj[2].toString());
						feedbackResultDes.setRoleDescription(vwsDetails.getPersonFullName());
						feedbackResultDes.setTotalRatingVal(Double.parseDouble(new DecimalFormat("##.##").format(resultGradeValue/Double.valueOf(feedBackObj[0].toString()))));
						String avgRatingDescription=this.getRatingDescription(custId,Double.parseDouble(new DecimalFormat("##.##").format(resultGradeValue/Double.valueOf(feedBackObj[0].toString()))));
						if(StringFunctions.isNotNullOrEmpty(avgRatingDescription)){
							feedbackResultDes.setDescription(avgRatingDescription);
						}
						feedBackRatingDetails.add(feedbackResultDes);
						feedbackResultDes=null;
					}
					vwsDetails=null; 
				}
				feedbackQuestionsList=null; 
			}else{
				List<ViewStudentPersonAccountDetails> vwsDetailList=null;
				if(Long.valueOf(feedBackQuesId)>0){
					feedBackRatingDetails = new ArrayList<VWFeedbackRatingDetails>();
					List<Object[]> feedbackQuestionsList=this.getAll("select count(studentId),sum(resultGradeValue),qusDescription,description,studentId,parentId from vw_FeedbackRatingDetails where custId="+custId+" and feedbackQuestionId ="+feedBackQuesId+" group by studentId");
					for (Object[] feedBackObj : feedbackQuestionsList) {
						vwsDetailList=(List<ViewStudentPersonAccountDetails>) this.getAll(ViewStudentPersonAccountDetails.class,"studentId="+Long.valueOf(feedBackObj[4].toString())+" group by studentId order by fullName");
						if(ObjectFunctions.isNotNullOrEmpty(vwsDetailList)){
							ViewStudentPersonAccountDetails vwsDetails=null;
							for(Object objStu:vwsDetailList){
								vwsDetails=(ViewStudentPersonAccountDetails) objStu;
								if(!ObjectFunctions.isNullOrEmpty(vwsDetails)){
									feedbackResultDes=new VWFeedbackRatingDetails();
									resultGradeValue=Double.valueOf(feedBackObj[1].toString());
									if(!ObjectFunctions.isNullOrEmpty(feedBackObj[2]))
									feedbackResultDes.setQusDescription(feedBackObj[2].toString());
									feedbackResultDes.setRoleDescription(vwsDetails.getPersonFullName());
									feedbackResultDes.setTotalRatingVal(Double.parseDouble(new DecimalFormat("##.##").format(resultGradeValue/Double.valueOf(feedBackObj[0].toString()))));
									String avgRatingDescription=this.getRatingDescription(custId,Double.parseDouble(new DecimalFormat("##.##").format(resultGradeValue/Double.valueOf(feedBackObj[0].toString()))));
									if(StringFunctions.isNotNullOrEmpty(avgRatingDescription)){
										feedbackResultDes.setDescription(avgRatingDescription);
									}
									feedBackRatingDetails.add(feedbackResultDes);
									feedbackResultDes=null;
								}
								vwsDetails=null;
							}
							vwsDetailList=null;
						}else{
							vwsDetailList=(List<ViewStudentPersonAccountDetails>) this.getAll(ViewStudentPersonAccountDetails.class,"parentId="+Long.valueOf(feedBackObj[5].toString())+" group by studentId order by fullName");
							if(!ObjectFunctions.isNullOrEmpty(vwsDetailList)){
								ViewStudentPersonAccountDetails vwsDetails=null;
								for(Object objStu:vwsDetailList){
									vwsDetails=(ViewStudentPersonAccountDetails) objStu;
									if(!ObjectFunctions.isNullOrEmpty(vwsDetails)){
										resultGradeValue=Double.valueOf(feedBackObj[1].toString());
										if(!ObjectFunctions.isNullOrEmpty(feedBackObj[2]))
										feedbackResultDes.setQusDescription(feedBackObj[2].toString());
										feedbackResultDes.setRoleDescription(vwsDetails.getPersonFullName());
										feedbackResultDes.setTotalRatingVal(Double.parseDouble(new DecimalFormat("##.##").format(resultGradeValue/Double.valueOf(feedBackObj[0].toString()))));
										String avgRatingDescription=this.getRatingDescription(custId,Double.parseDouble(new DecimalFormat("##.##").format(resultGradeValue/Double.valueOf(feedBackObj[0].toString()))));
										if(StringFunctions.isNotNullOrEmpty(avgRatingDescription)){
											feedbackResultDes.setDescription(avgRatingDescription);
										}
										feedBackRatingDetails.add(feedbackResultDes);
										feedbackResultDes=null;
									}
									vwsDetails=null;
								}
							}
							vwsDetailList=null;
						}
					}
					feedbackQuestionsList=null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return feedBackRatingDetails;
	}
	
	public String getRatingDescription(long custId, double ratingVal )
	 {
		 String ratingDescription="Average";
		 
		 try {
			 	FeedbackGrades grade=(FeedbackGrades)this.get(FeedbackGrades.class,"custId="+custId+" and title >="+ ratingVal);
			 	if(!ObjectFunctions.isNullOrEmpty(grade)){
			 		ratingDescription = grade.getDescription();
			 	}
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		 }
		 return ratingDescription;
	 }
	
	public void createSchoolHolidays(long custId, long userId, long academicYearId,Date holidayStartDate,Date endDate,String holidayDescription,String studyClassId, boolean isCreate, String deletedIds){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'createSchoolHolidays' method");
		}
		
		if(!ObjectFunctions.isNullOrEmpty(holidayStartDate) && !ObjectFunctions.isNullOrEmpty(endDate)){
			AcademicYear academicYear = (AcademicYear)this.get(AcademicYear.class,academicYearId);
			if(!StringFunctions.isNullOrEmpty(studyClassId))
			{
				if("all".equalsIgnoreCase(studyClassId))
				{
					List<ClassName> classNamesList = this.getAllClassNames(custId,academicYear.getId());
					if(!ObjectFunctions.isNullOrEmpty(classNamesList))
		    		{
						for(ClassName className : classNamesList)
						{
							this.createShcoolHolidayImpl(endDate, className.getId(), custId, userId, academicYearId, holidayStartDate,holidayDescription, academicYear, isCreate, deletedIds);
						}
		    		}
				}
				else
				{
					Object[] classNameClassIds= this.get("select classId,className from vw_classSectionDetails where custId="+custId+ " and academicYearId="+academicYear.getId()+" and classSectionId='"+studyClassId+"'");
					if(!ObjectFunctions.isNullOrEmpty(classNameClassIds))
		    		{
						if(!ObjectFunctions.isNullOrEmpty(classNameClassIds[0]))
			    		{
							this.createShcoolHolidayImpl(endDate, Long.valueOf(classNameClassIds[0].toString()), custId, userId, academicYearId, holidayStartDate,holidayDescription, academicYear, isCreate, deletedIds);
			    		}
						
		    		}
					classNameClassIds = null;
				}
			}
			else
				this.createShcoolHolidayImpl(endDate, 0, custId, userId, academicYearId, holidayStartDate,holidayDescription, academicYear, isCreate, deletedIds);
		
		 academicYear=null;
		}
	}
	public void createShcoolHolidayImpl(Date endDate,long classId,long custId, long userId, long academicYearId,Date holidayStartDate,String holidayDescription,AcademicYear academicYear, boolean isCreate, String deletedIds)
	{		List<Object[]> studentDailyAttendanceList=null;
		SchoolHolidays holiday=null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(holidayStartDate);
		for(Calendar startDate=cal;DateFunctions.compare2Dates(endDate,startDate.getTime());)
		{
    			holiday = new SchoolHolidays();
    			holiday.setCustId(custId);
    			holiday.setStartDate(holidayStartDate);
			    holiday.setHolidayDate(new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
			    holiday.setMonthId(Integer.parseInt(new SimpleDateFormat("MM").format(startDate.getTime())));
			    holiday.setYearId(new SimpleDateFormat("yyyy").format(startDate.getTime()));
			    holiday.setEndDate(endDate);
			    holiday.setDescription(holidayDescription);
			    holiday.setNoOfDays(1);
			    holiday.setStatus("H");
			    holiday.setAcademicYear(academicYear);
			    if(classId > 0)
				{
			    	holiday.setClassId(classId);
				}
			    else
			    	holiday.setClassId(0);
			    holiday.setCreatedById(userId);
			    holiday.setLastUpdatedById(userId);
			    holiday.setCreatedDate(new Date());
			    holiday.setLastAccessDate(new Date());
			    holiday.setLastUpdatedDate(new Date());
			    this.save(holiday);
			    studentDailyAttendanceList = this.getAll("select studentId,attendanceDate from vw_StudentDailyAttendance where attendanceDate ='"+ new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()) +"' and custId="+custId);
				if(ObjectFunctions.isNotNullOrEmpty(studentDailyAttendanceList))
			    {
			    	for(Object[] studentDetails:studentDailyAttendanceList){
			    		StudentDailyAttendance studentDailyAttnd = (StudentDailyAttendance) this.get(StudentDailyAttendance.class, "studentId="+ studentDetails[0].toString()+" and attendanceDate='"+studentDetails[1].toString().replace(".0", "")+"'");
						if(!ObjectFunctions.isNullOrEmpty(studentDailyAttnd)){
							
							 this.updateStudentDailyAttendance(studentDailyAttnd.getStudentId(),"H",new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
						}
						studentDailyAttnd = null;
					}
			    }
			    studentDailyAttendanceList=null;
			startDate.add(Calendar.DATE, 1);
    	}
		//Sending Notification for Holidays
		try
		{
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			JSONArray holidaysArray = new JSONArray();
			main.put("notificationFor", "Holidays");
			subVal.put("description", holidayDescription);
			subVal.put("title", isCreate ? "Holidays added":"Holidays updated");
			List<SchoolHolidays> schoolHolidaysList= this.getAll(SchoolHolidays.class, " academicYearId="+academicYearId+" and custId="+custId+" and startDate='" +  DateFunctions.convertDateToString(holidayStartDate) +"' and endDate='" +DateFunctions.convertDateToString(endDate)+"'");
			if(!ObjectFunctions.isNullOrEmpty(schoolHolidaysList))
				for(SchoolHolidays schoolHolidays : schoolHolidaysList)
				{
					JSONObject holidaysSub = new JSONObject();
					holidaysSub.put("id",schoolHolidays.getId());
					holidaysSub.put("startDate", ObjectFunctions.isNullOrEmpty(schoolHolidays.getStartDate())? "": DateFunctions.convertDateToString(schoolHolidays.getStartDate()));
					holidaysSub.put("endDate",ObjectFunctions.isNullOrEmpty(schoolHolidays.getEndDate())? "": DateFunctions.convertDateToString(schoolHolidays.getEndDate()));
					holidaysSub.put("holidayDate",schoolHolidays.getHolidayDate());
					holidaysSub.put("noOfDays",schoolHolidays.getNoOfDays());
					holidaysSub.put("holidayDescription",schoolHolidays.getDescription());
					holidaysSub.put("status",schoolHolidays.getStatus());
					holidaysSub.put("monthId",schoolHolidays.getMonthId());
					holidaysSub.put("yearId",schoolHolidays.getYearId());
					holidaysSub.put("classId",schoolHolidays.getClassId());
					holidaysArray.put(holidaysSub);
					holidaysSub = null;
				}
				subVal.put("schoolHolidaysVOList", holidaysArray);
				subVal.put("deletedIds", ObjectFunctions.isNullOrEmpty(deletedIds)? "": deletedIds);
				main.put("information", subVal);
				log.debug(main.toString()); 
				this.sendNotificationToAndroidMobileUsers(custId,main.toString());
			}
		catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
	}
	public String createHolidays(long custId, long userId, long academicYearId, org.apache.poi.ss.usermodel.Sheet sheet,String loadType,List<SchoolHolidays> schoolHolidaysDetailsList) {
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'createHolidays' method");
		}
		String holidayDescriptions = null;
		try {
			try {
				if (!ObjectFunctions.isNullOrEmpty(schoolHolidaysDetailsList))
				{
					Object[] classNameClassIds= this.get("select classId,className from vw_classSectionDetails where custId="+custId+ " and academicYearId="+academicYearId+" and className='"+sheet.getSheetName()+"'");
					AcademicYear academicYear = (AcademicYear)this.get(AcademicYear.class,academicYearId);
					for (SchoolHolidays schoolHolidays : schoolHolidaysDetailsList) {
						if ((!ObjectFunctions.isNullOrEmpty(schoolHolidays) && !ObjectFunctions.isNullOrEmpty(schoolHolidays.getStartDate()) && !ObjectFunctions.isNullOrEmpty(schoolHolidays.getEndDate())) && (schoolHolidays.getStartDate().compareTo(schoolHolidays.getEndDate())<=0) && ((academicYear.getStartDate().compareTo(schoolHolidays.getStartDate())<=0 && schoolHolidays.getEndDate().compareTo(academicYear.getEndDate()) <=0) && (academicYear.getStartDate().compareTo(schoolHolidays.getEndDate())<=0 && (schoolHolidays.getStartDate().compareTo(academicYear.getEndDate())<=0)))) 
						{ 
							if(loadType.equals("E") || loadType.equals(""))
							{
								if(loadType.equals("E"))
								{
									if (!StringFunctions.isNullOrEmpty(schoolHolidays.getSchoolHolidayIdStr())) 
									{
										long holidayId = Long.valueOf(schoolHolidays.getSchoolHolidayIdStr());
										/*if("No".equalsIgnoreCase(schoolHolidays.getSchoolWorkingDay()) || StringFunctions.isNullOrEmpty(schoolHolidays.getSchoolWorkingDay()))
										{*/
											SchoolHolidays schoolHolidaysObj = (SchoolHolidays) this.get(SchoolHolidays.class,"id="+Long.valueOf(holidayId));
								            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(schoolHolidays.getStartDateFormate().toString());
											Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(schoolHolidays.getEndDateFormate().toString());
											if(!StringFunctions.isNullOrEmpty(schoolHolidays.getDescription())){
												if(!ObjectFunctions.isNullOrEmpty(schoolHolidaysObj)){
													if(!schoolHolidaysObj.getDescription().equalsIgnoreCase(schoolHolidays.getDescription()) || (!schoolHolidaysObj.getStartDate().toString().replace(".0", "").equals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate))) || (!schoolHolidaysObj.getEndDate().toString().replace(".0", "").equals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate)))){
														this.removeAllSchoolHolidaysByDescriptionAndStartAndEndDate(custId,schoolHolidaysObj.getDescription(),schoolHolidaysObj.getStartDateFormate(),schoolHolidaysObj.getEndDateFormate());
														this.addSchoolHolidays(schoolHolidays,classNameClassIds,custId,userId,academicYear);
													}
												}
												else{
													this.addSchoolHolidays(schoolHolidays,classNameClassIds,custId,userId,academicYear);
												}
											}
											/*else if(!ObjectFunctions.isNullOrEmpty(schoolHolidaysObj))
											{
												schoolHolidaysObj.setDescription(schoolHolidays.getDescription());
						                    	if(!ObjectFunctions.isNullOrEmpty(schoolHolidays.getStartDate()))
						                    	{
						                    		 schoolHolidaysObj.setStartDate(schoolHolidays.getStartDate());
						 			            }
							                    if(!ObjectFunctions.isNullOrEmpty(schoolHolidays.getEndDate())){
							                    	schoolHolidaysObj.setEndDate(schoolHolidays.getEndDate());
									            }
							                    if (!ObjectFunctions.isNullOrEmpty(classNameClassIds))
							    				{
							                    	if (!ObjectFunctions.isNullOrEmpty(classNameClassIds[0]))
								    				{
							                    		schoolHolidaysObj.setClassId(Long.valueOf(classNameClassIds[0].toString()));
								    				}
							    				}
							                    else
							                    	schoolHolidaysObj.setClassId(0);
												this.save(schoolHolidaysObj);
											}*/
										/*}
										else
										{
											try {
												this.remove(SchoolHolidays.class,Long.valueOf(holidayId));
											} catch (Exception e) {
												e.printStackTrace();
											}
										}*/
									}
									else{
										this.addSchoolHolidays(schoolHolidays,classNameClassIds,custId,userId,academicYear);
									}
								}
								else{
									this.addSchoolHolidays(schoolHolidays,classNameClassIds,custId,userId,academicYear);
								}
							}
						}else if(!StringFunctions.isNullOrEmpty(schoolHolidays.getDescription())){
							String holidayDescription = schoolHolidays.getDescription();
							if(!StringFunctions.isNullOrEmpty(holidayDescriptions))
								holidayDescriptions = holidayDescriptions +","+ holidayDescription;
							else
								holidayDescriptions = holidayDescription;
						}
					}
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return holidayDescriptions;
	}
	
	public Events addEvent(EventsVO eventVo){
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager ' addEvent ' method");
		}
		Events levent=new Events();
		Address eventAddress = null;
		try {
			if(!ObjectFunctions.isNullOrEmpty(eventVo))
			{
				AcademicYear academicYear = (AcademicYear)this.get(AcademicYear.class,eventVo.getAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(academicYear)) 
				{
					if(eventVo.getId() > 0){
						levent =(Events)this.get(Events.class,eventVo.getId());
						if("H".equalsIgnoreCase(levent.getStatus())){
							this.updateAndDeleteSchoolHolidays(eventVo.getCustId(), eventVo.getAcademicYearId(), levent.getEventName());
						}
						levent.setLastUpdatedById(eventVo.getEventCreatedUserId());
						/*if(!ObjectFunctions.isNullOrEmpty(levent.getEventAddress()))
							eventAddress = levent.getEventAddress();
						else
							eventAddress = new Address();*/
					}
					else
					{
						levent = new Events();
						eventAddress = new Address();
						levent.setCreatedById(eventVo.getEventCreatedUserId());
						levent.setLastUpdatedById(eventVo.getEventCreatedUserId());
					}
					levent.copyFromVoToEntity(levent,eventVo);
					
					if("M".equalsIgnoreCase(eventVo.getRequestType()))
					{
						levent.setStartDate(DateFunctions.convertStringToDate(eventVo.getStartDateTime()));
						levent.setEndDate(DateFunctions.convertStringToDate(eventVo.getEndDateTime()));
						
					}
					else
					{
						String[] hhmm=eventVo.getStartTime().split(":");
						String hour=hhmm[0];
						String[] mmAmPm=hhmm[1].split(" ");
						String mm=mmAmPm[0];
						String amPm=mmAmPm[1];
						int hours=0;
						if(amPm.equalsIgnoreCase("AM"))
						    hours=Integer.valueOf(hour);
						else
						{
						    hours=Integer.valueOf(hour)+12;
						}
						//log.debug("hour:"+hour);
						Calendar cal =Calendar.getInstance();
						if(!ObjectFunctions.isNullOrEmpty(eventVo.getStartDateTime()))
							cal.setTime(DateFunctions.convertStringToDate(eventVo.getStartDateTime()));
						/*else{
							cal.setTime(startDate);
						}*/
						//log.debug("Before adding hours : "+cal.getTime());
						cal.add(Calendar.HOUR,hours);
						cal.add(Calendar.MINUTE,Integer.valueOf(mm));
						//log.debug("After adding hours : "+cal.getTime());
						
						levent.setStartDate(cal.getTime());
						
						String[] endTimehhmm=eventVo.getEndTime().split(":");
						String endTimehour=endTimehhmm[0];
						String[] endTimemmAmPm=endTimehhmm[1].split(" ");
						String endTimemm=endTimemmAmPm[0];
						String endTimeamPm=endTimemmAmPm[1];
						int endTimehours=0;
						if(endTimeamPm.equalsIgnoreCase("PM"))
							endTimehours=Integer.valueOf(endTimehour);
						else
						{
							endTimehours=Integer.valueOf(endTimehour)+12;
						}
						
						cal =Calendar.getInstance();
						if(!ObjectFunctions.isNullOrEmpty(eventVo.getEndDateTime()))
							cal.setTime(DateFunctions.convertStringToDate(eventVo.getEndDateTime()));
						
						cal.add(Calendar.HOUR,endTimehours);
						cal.add(Calendar.MINUTE,Integer.valueOf(endTimemm));
						
						levent.setEndDate(cal.getTime());
					}
					
					levent.setAcademicYear(academicYear);
					
					levent.setCustId(academicYear.getCustId());
					levent.setIncludeNonTeachingStaff(Constants.NO_STRING);
					
					//eventAddress.copyFromVoToEntity(eventAddress, eventVo.getEventAddressVO());
					//eventAddress = (Address)this.save(eventAddress);
					
					//levent.setEventAddress(eventAddress);
					if("true".equalsIgnoreCase(levent.getStatus()))
						levent.setStatus("H");
					else
						levent.setStatus("E");
					
					if("cs".equalsIgnoreCase(eventVo.getEventFor()))
					{
						if(!StringFunctions.isNullOrEmpty(eventVo.getStudyClassIds()))
						{
							List<StudyClass> studyClassList = this.getAll(StudyClass.class," id in "+eventVo.getStudyClassIds());
							levent.setStudyClass(ConvertUtil.convertListToSet(studyClassList));
							studyClassList = null;
						}
						if(!StringFunctions.isNullOrEmpty(eventVo.getNonTeachingRoleIds()))
						{
							List<Role> nonTeachingRoleList = this.getAll(Role.class," name in "+eventVo.getNonTeachingRoleIds());
							levent.setRole(ConvertUtil.convertListToSet(nonTeachingRoleList));
							nonTeachingRoleList = null;
							levent.setIncludeNonTeachingStaff(Constants.YES_STRING);
						}
					}
					else if("s".equalsIgnoreCase(eventVo.getEventFor()))
					{
						levent.setStaffEvent(eventVo.getStaffEvent());
					}
					
					levent = (Events)this.save(levent);
					//EventsAlbum album = ((EventsAlbum)this.get(EventsAlbum.class, "custId="+levent.getCustId()+" and academicYearId="+levent.getAcademicYear().getId()+" and albumName='"+events.getEventName()+"' and eventId="+events.getId() ));
					//this.updateQuery("update Account set password='"+accountuserVo.getPassword()+"', passwordStatus='Y' where id="+accountuserVo.getId());
					if("H".equalsIgnoreCase(levent.getStatus())){
						if(!ObjectFunctions.isNullOrEmpty(levent.getStartDate()))
							this.createSchoolHolidays(levent.getCustId(), eventVo.getEventCreatedUserId(), levent.getAcademicYear().getId(),levent.getStartDate(),levent.getEndDate(),levent.getEventName(),"",false,null);
						else{
							this.createSchoolHolidays(levent.getCustId(), eventVo.getEventCreatedUserId(),levent.getAcademicYear().getId(),new Date(),levent.getEndDate(),levent.getEventName(),"",false,null);
						}
					}
				    
					if (!ObjectFunctions.isNullOrEmpty(levent) && "W".equalsIgnoreCase(eventVo.getRequestType())) 
					{
						EventsNotificationThread R1 = new EventsNotificationThread(levent,eventVo.getId(),null);
					    R1.start();
					}
					
					
					//levent=null;
					//event=null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return levent;
	}
	
	//This method is used for SchoolHolidays delete process and edit process -- seshu
	public String updateAndDeleteSchoolHolidays(long custId, long academicYearId, String description){
			List<SchoolHolidays> holidaysList=null;
			List<Object[]> studentDailyAttendanceList=null;
		//	List<Object[]> staffAttendanceList = null;
			holidaysList=this.getSchoolHolidaysByDescription(custId, academicYearId, description);
			if(ObjectFunctions.isNotNullOrEmpty(holidaysList)){
				StringBuffer sbf = new StringBuffer();
				sbf.append("(");
				Calendar cal=Calendar.getInstance();
				for(SchoolHolidays holiday:holidaysList){
					if(!ObjectFunctions.isNullOrEmpty(holiday)){
						 studentDailyAttendanceList = this.getAll("select studentId from vw_StudentDailyAttendance where attendanceDate ='"+ holiday.getHolidayDate() +"' and custId="+custId);
						 if(ObjectFunctions.isNotNullOrEmpty(studentDailyAttendanceList))
						 {
						    	for(Object[] studentDetails:studentDailyAttendanceList){
						    		StudentDailyAttendance studentDailyAttnd = (StudentDailyAttendance) this.get(StudentDailyAttendance.class, "studentId="+ studentDetails[0].toString());
									if(!ObjectFunctions.isNullOrEmpty(studentDailyAttnd)){
										
										 this.updateStudentDailyAttendance(studentDailyAttnd.getStudentId(),"A",holiday.getHolidayDate());
									}
									studentDailyAttnd = null;
									studentDetails=null;
								}
						  }
						  if(holiday.getStatus().equalsIgnoreCase("H")){
							this.remove(SchoolHolidays.class, holiday.getId());
							sbf.append(holiday.getId());
							sbf.append(",");
						  }else{
							holiday.setStatus("W");
							cal.setTime(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,holiday.getHolidayDate()));
							holiday.setDescription(cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.getDefault()).toUpperCase());
							holiday.setStartDate(cal.getTime());
							holiday.setEndDate(cal.getTime());
							this.save(holiday);
						}
					}
				}
				sbf.append("0)");
				return sbf.toString();
			}
			holidaysList=null;
			studentDailyAttendanceList=null;
		//	staffAttendanceList = null;
			return null;
	}
	
	public void assignClassSubjects(long custId, long userId, long academicYearId, long staffId,String classSectionId, List<String> subjectIds){
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'assignClassSubjects' method");
		}
		try {
			int staffAssignedClassSubjCount = 0;
			if(ObjectFunctions.isNotNullOrEmpty(subjectIds) && StringFunctions.isNotNullOrEmpty(classSectionId) && staffId > 0){
				AcademicYear academicYear = (AcademicYear)this.get(AcademicYear.class,academicYearId);
				Staff staff=(Staff)this.get(Staff.class, staffId);
				//StudyClass studyClass=(StudyClass)this.get(StudyClass.class, "id="+ classSectionId);
				if(!ObjectFunctions.isNullOrEmpty(academicYear) && !ObjectFunctions.isNullOrEmpty(staff)){// && !ObjectFunctions.isNullOrEmpty(studyClass)
					StaffElgibleSubjects staffElgibleSubjects = null;
					StudySubject subject = null;
					ClassTeacher classTeacher = null;
					for(String subjectId : subjectIds){
						staffAssignedClassSubjCount = this.getCount("classTeacher","teacherId="+staffId+" and studyClassId="+classSectionId+" and studySubjectId="+subjectId);
						if(staffAssignedClassSubjCount <= 0){
							 subject =(StudySubject)this.get(StudySubject.class,"id="+subjectId);
							 staffElgibleSubjects = (StaffElgibleSubjects)this.get(StaffElgibleSubjects.class, " studySubjectId="+subjectId+" and staffId="+staffId+" and academicYearId="+academicYearId);
                             if(ObjectFunctions.isNullOrEmpty(staffElgibleSubjects)){
                                     staffElgibleSubjects = new StaffElgibleSubjects();
                                     staffElgibleSubjects.setAcademicYear(academicYear);
                                     staffElgibleSubjects.setStaffId(staff);
                                     staffElgibleSubjects.setStudySubjectId(subject);
                                     staffElgibleSubjects.setCreatedDate(new Date());
                                     staffElgibleSubjects.setLastAccessDate(new Date());
                                     staffElgibleSubjects.setCreatedById(userId);
                                     staffElgibleSubjects.setLastUpdatedDate(new Date());
                                     this.save(staffElgibleSubjects);
                                     staffElgibleSubjects = null;
                             }
                             classTeacher = new ClassTeacher();
                             this.saveClassTeacherDetails(classTeacher,custId,userId,staff,Long.valueOf(classSectionId),subject,academicYear);
                             classTeacher = null;
                             subject=null;
						}
					}
				}
				academicYear = null;
				staff = null;
			}
		} catch (Exception ex) {
			;RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	public void updateCommunityAndCaste(long custId, long userId,Sheet sheet,String loadType) {
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'assignClassSubjects' method");
		}
		try {
			 int j=0;
			 if(loadType.equals("E"))
			 {
				  j=10;
			 }
			 else
			 {
				 j=2;
			 }
			 if(StringFunctions.isNotNullOrEmpty(String.valueOf(sheet.getCell(0, 0).getContents().trim()))){
				CastSettings casteSettings = null;
				SubCastSettings subCastSettings = null;
				boolean isNotExist = true;
				boolean isCreatePro = false;
				int rowSize = sheet.getRows();
				for (int i = j; i < rowSize; i++) {
					casteSettings = null;
					subCastSettings=null;
					if (loadType.equals("E")) {
						if (StringFunctions.isNotNullOrEmpty(sheet.getCell(2, i).getContents().trim()) && StringFunctions.isNullOrEmpty(sheet.getCell(3, i).getContents().trim())) {
							long communityId = Long.valueOf(sheet.getCell(2, i).getContents().trim().toString());
							casteSettings = (CastSettings) this.get(CastSettings.class,Long.valueOf(communityId));
							if(!ObjectFunctions.isNullOrEmpty(casteSettings)){
								isNotExist = true;
				    			isCreatePro = true;
							}
						}else{
							long casteId = Long.valueOf(sheet.getCell(3, i).getContents().trim().toString());
							subCastSettings = (SubCastSettings) this.get(SubCastSettings.class,Long.valueOf(casteId));
							if(!ObjectFunctions.isNullOrEmpty(subCastSettings)){
								isNotExist = true;
							}
						}
				    }else{
				    		isNotExist = true;
				    }
					if(isNotExist) 
					{
						if(ObjectFunctions.isNullOrEmpty(casteSettings)){
							if(StringFunctions.isNotNullOrEmpty(sheet.getCell(0,i).getContents()) || StringFunctions.isNotNullOrEmpty(sheet.getCell(1,i).getContents())){
								if(StringFunctions.isNotNullOrEmpty(sheet.getCell(0, i).getContents().trim()) && StringFunctions.isNullOrEmpty(sheet.getCell(1, i).getContents().trim())){
									casteSettings = (CastSettings)this.get(CastSettings.class,"castName='"+sheet.getCell(0,i).getContents().trim()+"' and custId="+custId);
								}
								if(StringFunctions.isNullOrEmpty(sheet.getCell(0, i).getContents().trim()) && StringFunctions.isNotNullOrEmpty(sheet.getCell(1, i).getContents().trim())){
									subCastSettings = (SubCastSettings) this.get(SubCastSettings.class,"subCastName='"+sheet.getCell(1,i).getContents().trim()+"' and custId="+custId);
								}
							}else{
								continue;
							}
						}else if(ObjectFunctions.isNullOrEmpty(subCastSettings)){
		    					subCastSettings = (SubCastSettings) this.get(SubCastSettings.class,"subCastName='"+sheet.getCell(1,i).getContents().trim()+"' and custId="+custId);
				    			if(!ObjectFunctions.isNullOrEmpty(subCastSettings) &&  StringFunctions.isNotNullOrEmpty(sheet.getCell(3, i).getContents().trim())){
			    					continue;
				    			}
		    			}
		    			if(StringFunctions.isNotNullOrEmpty(sheet.getCell(0, i).getContents().trim()) && StringFunctions.isNullOrEmpty(sheet.getCell(1, i).getContents().trim()))
		    			{
							if(!isCreatePro){
								casteSettings = (CastSettings)this.get(CastSettings.class,"castName='"+sheet.getCell(0,i).getContents().trim()+"' and custId="+custId);
							}
							if(ObjectFunctions.isNullOrEmpty(casteSettings)){
								casteSettings = new CastSettings();
								casteSettings.setCastName(sheet.getCell(0, i).getContents().trim());
								casteSettings.setCreatedById(userId);
								casteSettings.setCreatedDate(new Date());
								casteSettings.setCustId(custId);
							}else { 
								if(isCreatePro && ObjectFunctions.isNullOrEmpty(sheet.getCell(3, i).getContents())){
									casteSettings.setCastName(sheet.getCell(0, i).getContents().trim());
									casteSettings.setLastUpdatedById(userId);
									casteSettings.setLastUpdatedDate(new Date());
									casteSettings.setCustId(custId);
								}else{
										subCastSettings.setSubCastName(sheet.getCell(1, i).getContents().trim());
										subCastSettings.setLastUpdatedById(userId);
										subCastSettings.setLastUpdatedDate(new Date());
										subCastSettings.setCustId(custId);
										casteSettings.addSubCastSettings(subCastSettings);
								}
								if(!isCreatePro)
									continue;
							}
							
						}else{
								if(!ObjectFunctions.isNullOrEmpty(subCastSettings)){
									if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(3, i).getContents())){
										subCastSettings.setSubCastName(sheet.getCell(1, i).getContents().trim());
										subCastSettings.setLastUpdatedById(userId);
										subCastSettings.setLastUpdatedDate(new Date());
										subCastSettings.setCustId(custId);
										casteSettings.addSubCastSettings(subCastSettings);
									}else{
										casteSettings.setCastName(sheet.getCell(0, i).getContents().trim());
										casteSettings.setLastUpdatedById(userId);
										casteSettings.setLastUpdatedDate(new Date());
										casteSettings.setCustId(custId);
										casteSettings.addSubCastSettings(subCastSettings);
									}
								}else{
									subCastSettings = new SubCastSettings();
									subCastSettings.setSubCastName(sheet.getCell(1, i).getContents().trim());
									subCastSettings.setCreatedById(userId);
									subCastSettings.setCreatedDate(new Date());
									subCastSettings.setCustId(custId);
									casteSettings.addSubCastSettings(subCastSettings);
								}
							}
							casteSettings = (CastSettings)this.saveOrUpdateObject(casteSettings);
						}
					}
				}
		} catch (Exception ex) {
			// TODO: handle exception
			;RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate){
		return adminDao.getStudentsAttendanceByClassSectionIdAndAttendanceDate(classSectionId,attendanceDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<VWStaffAttendance> getStaffAttendanceByAttendanceDate(String attendanceDate,long custId,long academicYearId,String staffBiometric){
		return adminDao.getStaffAttendanceByAttendanceDate(attendanceDate,custId,academicYearId,staffBiometric);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Fee> getClassFeeListByTermId(String schoolTermIds,String classIds,long academicYearId,long custId){
		return adminDao.getClassFeeListByTermId(schoolTermIds, classIds, academicYearId, custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getFeeCollectionList(String tableName,long custId,long academicYearId,String toDayDate,String daysBeetweenStartDate,String daysBeetweenEndDate,String receiptType){
		return adminDao.getFeeCollectionList(tableName,custId, academicYearId, toDayDate, daysBeetweenStartDate, daysBeetweenEndDate,receiptType);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getTermWiseFeeCollectionList(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String receiptType){
		return adminDao.getTermWiseFeeCollectionList(tableName,custId, academicYearId, termIds,selectedClassSectionIds,receiptType);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate){
		return adminDao.getFeeDefaultersList(custId, academicYearId, classIds,termsIs, toDayDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String studentStatus){
		return adminDao.getStudentFeePaidAndUnpaidDetails(tableName,custId, academicYearId, termIds,selectedClassSectionIds,studentStatus);
	}
	public void updateSelectedLoginRoles(String selectedId,Long custId){
		adminDao.updateSelectedLoginRoles(selectedId,custId);
	}
	public void updateUnselectedLoginRoles(String unselectedId,Long custId){
		adminDao.updateUnselectedLoginRoles(unselectedId,custId);
	}
	public boolean createStudentPaymentSyncFromDesktopToWeb(StudentPaymentMainVO studentPaymentMainVO){
		boolean paymentSuccess= true;
		StudentPayment studentPayment = null;
		StudentFeePaidDetails studentFeePaidDetails = null;
		ExcessPayment excessPayAmount = null;
		Student student = null;
		String createdDate = null;
		Fee fee = null;
		StudentTransportDetails studentTransportDetails=null;
		AcademicYear academicYear = null;
		long studentId = 0;
		long createdById = 0;
		long descTopId = 0;
		Date date = null;
		HashMap<Long, Long> studentPaymentMap = new HashMap<Long, Long>();
		long custId = studentPaymentMainVO.getIdentifier().getCustId();
		//long academicYearId = studentPaymentMainVO.getIdentifier().getAcademicYearId();
		try {
			List<Student> studentList = new ArrayList<Student>();
			if (!ObjectFunctions.isNullOrEmpty(studentPaymentMainVO.getStudentPaymentVOs())) {
				log.debug("getStudentPaymentVOs = "+ studentPaymentMainVO.getStudentPaymentVOs().size());
				for (StudentPaymentVO studentPaymentVO : studentPaymentMainVO.getStudentPaymentVOs()) {
					if (!ObjectFunctions.isNullOrEmpty(studentPaymentVO)) {
						descTopId = studentPaymentVO.getDpPaymentDetailsId();
						log.debug("descTopId :"+descTopId);
						createdById = studentPaymentVO.getCreatedById();
						studentId = studentPaymentVO.getStudentId();
						//log.debug("studentId=" + studentId);
						String receiptNumber = studentPaymentVO.getDesktopReceiptNumber();
						String paymentType =  studentPaymentVO.getPaymentType();
						studentPayment = (StudentPayment) this.get(StudentPayment.class, "custId=" + custId + " and academicYearId="+studentPaymentVO.getAcademicYearId()+" and desktopReceiptNumber='" + receiptNumber + "' and dpPaymentDetailsId=" + descTopId);
						if (ObjectFunctions.isNullOrEmpty(studentPayment)) {
							studentPayment = new StudentPayment();
							academicYear = (AcademicYear) this.get(AcademicYear.class,studentPaymentVO.getAcademicYearId());
							student = (Student) this.get(Student.class, studentId);
							studentPayment.setAcademicYear(academicYear);
							studentPayment.setStudent(student);
							studentPayment.setCreatedById(Long.valueOf(createdById));
							studentPayment.setDesktopReceiptNumber(receiptNumber);
						}else{
							academicYear = studentPayment.getAcademicYear();
							student = studentPayment.getStudent();
						}
						studentPayment.setCustId(custId);
						if (!ObjectFunctions.isNullOrEmpty(academicYear) && !ObjectFunctions.isNullOrEmpty(student)) {
								studentPayment.setLastUpdatedById(Long.valueOf(createdById));
								if (!StringFunctions.isNullOrEmpty(studentPaymentVO.getPaymentDate())) 
										studentPayment.setPaymentDate(DateFunctions.convertStringToDate(studentPaymentVO.getPaymentDate()));

								if ("DD".equalsIgnoreCase(paymentType)) {
									studentPayment.setDdNumber(studentPaymentVO.getDdNumber());
									if (!StringFunctions.isNullOrEmpty(studentPaymentVO.getDdDrawnDate())) {
										studentPayment.setDdDrawnDate(DateFunctions.convertStringToDate(studentPaymentVO.getDdDrawnDate()));
									}
									studentPayment.setPaymentType("D");
								}else if ("CS".equalsIgnoreCase(paymentType)){
									studentPayment.setPaymentType("CS");
									studentPayment.setTransactionNumber("0");
								}
								else if ("CQ".equalsIgnoreCase(paymentType)){
									studentPayment.setChequeNumber(studentPaymentVO.getChequeNumber());
									if (!StringFunctions.isNullOrEmpty(studentPaymentVO.getChequeIssuedDate())) {
										studentPayment.setChequeIssuedDate(DateFunctions.convertStringToDate(studentPaymentVO.getChequeIssuedDate()));
									}
									studentPayment.setPaymentType("CH");
								}else {
									studentPayment.setPaymentType("C");
								}
								if (!ObjectFunctions.isNullOrEmpty(studentPaymentVO.getPaidAmount())) {
									studentPayment.setPaidAmount(studentPaymentVO.getPaidAmount());
								}
								if (!ObjectFunctions.isNullOrEmpty(studentPaymentVO.getDiscountAmount())) {
									studentPayment.setDiscountAmount(studentPaymentVO.getDiscountAmount());
								}
								if (!ObjectFunctions.isNullOrEmpty(studentPaymentVO.getDeleteStatus())) {
									studentPayment.setDeleteStatus(studentPaymentVO.getDeleteStatus());
								}
								// Object[] invoiceNumberCount=adminManager.get("select id,invoiceNumber from studentPayment where custId="+custId+"  order by id DESC limit 1");
								long lastInvoiceNumber = this.getInvoiceNumberByCustId("studentPayment", custId, 0);
								studentPayment.setInvoiceNumber(lastInvoiceNumber + 1);
								if (!StringFunctions.isNullOrEmpty(studentPaymentVO.getBankName()) && !"null".equalsIgnoreCase(studentPaymentVO.getBankName())) {
									String bankName =  studentPaymentVO.getBankName();
									BankMaster bankMaster = (BankMaster) this.get(BankMaster.class, "bankName='" + bankName + "' ");
									if (!ObjectFunctions.isNullOrEmpty(bankMaster)) {
										studentPayment.setBankMaster(bankMaster);
										bankMaster = null;
									}
								}
								studentPayment.setDpPaymentDetailsId(descTopId);
								studentPayment.setConcessionStatus(Constants.NO_STRING);
								studentPayment = (StudentPayment) this.saveOrUpdateObject(studentPayment);
								studentList.add(student);
								studentPaymentMap.put(descTopId, studentPayment.getId());
								//Send Fee payment Notification to Mobile
								if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent()))
									this.sendNotificationForFeePayment(student.getCustId(),student.getId(),Long.valueOf(student.getAccount().getStudentParent().getId()),"P");
						}
					}
					studentPayment = null;
				}
			} else {
				log.debug("student payment not available.");
			}
			if (!ObjectFunctions.isNullOrEmpty(studentPaymentMainVO.getStudentFeePaidDetailsVOs())) {
				log.debug("getStudentFeePaidDetailsVOs = "+ studentPaymentMainVO.getStudentFeePaidDetailsVOs().size());
				long studentPaymentId=0;
				for (StudentFeePaidDetailsVO studentFeePaidDetailsVO : studentPaymentMainVO.getStudentFeePaidDetailsVOs()) {
					if (studentFeePaidDetailsVO.getStudentPaymentId() >0){
						long studentFeePaymentId = studentFeePaidDetailsVO.getStudentPaymentId();
						if(!ObjectFunctions.isNullOrEmpty(studentPaymentMap.get(studentFeePaymentId))){
							studentPaymentId = studentPaymentMap.get(studentFeePaymentId);
							log.debug("studentPaymentId :"+studentPaymentId);
							if (!ObjectFunctions.isNullOrEmpty(studentPaymentId)) {
								if(studentFeePaidDetailsVO.getFeeId()>0)
									fee = (Fee) this.get(Fee.class, "id=" + studentFeePaidDetailsVO.getFeeId());
								else if (studentFeePaidDetailsVO.getStudTransportDetailsId()>0)
									studentTransportDetails = (StudentTransportDetails)this.get(StudentTransportDetails.class, studentFeePaidDetailsVO.getStudTransportDetailsId());
								if (!ObjectFunctions.isNullOrEmpty(fee) || !ObjectFunctions.isNullOrEmpty(studentTransportDetails)) {
									double paymentAmount = studentFeePaidDetailsVO.getPaymentAmount();
									double discountAmount = studentFeePaidDetailsVO.getDiscountAmount();
									studentPayment = (StudentPayment) this.get(StudentPayment.class, "custId=" + custId + " and id=" + studentPaymentId);
									if (!ObjectFunctions.isNullOrEmpty(studentPayment)) {
										if(!ObjectFunctions.isNullOrEmpty(fee))
											studentFeePaidDetails = (StudentFeePaidDetails) this.get(StudentFeePaidDetails.class, "custId="+ custId + " and feeId=" + fee.getId() + " and studentPaymentId=" + studentPayment.getId() + " and studentId=" + studentPayment.getStudent().getId());
										else if (!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
											studentFeePaidDetails = (StudentFeePaidDetails) this.get(StudentFeePaidDetails.class, "custId="+ custId + " and studTransportDetailsId=" + studentTransportDetails.getId() + " and studentPaymentId=" + studentPayment.getId() + " and studentId=" + studentPayment.getStudent().getId());
										if (ObjectFunctions.isNullOrEmpty(studentFeePaidDetails)) {
											studentFeePaidDetails = new StudentFeePaidDetails();
										}
										if (!ObjectFunctions.isNullOrEmpty(studentFeePaidDetailsVO)) {
											studentFeePaidDetails.setStudentPaymentId(studentPayment.getId());
											if (!ObjectFunctions.isNullOrEmpty(studentFeePaidDetails)) {
												studentFeePaidDetails.setPaymentAmount(paymentAmount);
												if (!ObjectFunctions.isNullOrEmpty(studentFeePaidDetailsVO.getDiscountAmount()) && !"null".equalsIgnoreCase(String.valueOf(studentFeePaidDetailsVO.getDiscountAmount()))) {
													studentFeePaidDetails.setDiscountAmount(discountAmount);
												}
												if(!ObjectFunctions.isNullOrEmpty(fee))
													studentFeePaidDetails.setFee(fee);
												if(!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
													studentFeePaidDetails.setStudentTransportDetails(studentTransportDetails);
												studentFeePaidDetails.setCreatedById(Long.valueOf(studentPayment.getCreatedById()));
												studentFeePaidDetails.setLastUpdatedById(Long.valueOf(studentPayment.getCreatedById()));
												studentFeePaidDetails.setDeleteStatus(studentPayment.getDeleteStatus());
												if (!ObjectFunctions.isNullOrEmpty(studentPayment.getStudent())) {
													studentFeePaidDetails.setStudentId(studentPayment.getStudent().getId());
												}
												if(!ObjectFunctions.isNullOrEmpty(fee)){
													if (fee.getFeeAmount() == paymentAmount + discountAmount)
														studentFeePaidDetails.setPaymentStatus("P");
													else
														studentFeePaidDetails.setPaymentStatus("N");
												}else if (!ObjectFunctions.isNullOrEmpty(studentTransportDetails)) {
													if (studentTransportDetails.getPickupAndDropFeeAmount() == paymentAmount + discountAmount)
														studentFeePaidDetails.setPaymentStatus("P");
													else
														studentFeePaidDetails.setPaymentStatus("N");
												}
												
												
												studentFeePaidDetails.setFutureFeeStatus("N");
												studentFeePaidDetails.setCommittedFeeStatus("N");
												studentFeePaidDetails.setCustId(custId);
												this.save(studentFeePaidDetails);
												studentFeePaidDetails=null;
											}
										}
									}
									fee=null;
									studentTransportDetails=null;
								}
							}
						}
					} else
						log.debug("student payment object not available.");
				}
			} else
				log.debug("student fee payment details not available.");
			if (!ObjectFunctions.isNullOrEmpty(studentPaymentMainVO.getExcessPaymentVOs())) {
				log.debug("getExcessPaymentVOs = "+ studentPaymentMainVO.getExcessPaymentVOs().size());
				long PaymentId = 0;
				long usedPaymentId = 0;
				for (ExcessPaymentVO excessPaymentVO : studentPaymentMainVO.getExcessPaymentVOs()) {
				   if (!ObjectFunctions.isNullOrEmpty(excessPaymentVO.getPaymentId()))
							PaymentId = excessPaymentVO.getPaymentId();
					if (!ObjectFunctions.isNullOrEmpty(excessPaymentVO.getUsedPaymentId()) && !"null".equalsIgnoreCase(String.valueOf(excessPaymentVO.getUsedPaymentId())))
							usedPaymentId = excessPaymentVO.getUsedPaymentId();
						studentId =  excessPaymentVO.getStudentId();
					if (studentId>0){
						studentPayment = (StudentPayment) this.get(StudentPayment.class, "custId=" + custId+ " and dpPaymentDetailsId=" + PaymentId+ " and studentId=" + studentId);
						if (!ObjectFunctions.isNullOrEmpty(studentPayment)){
							StudentPayment usedStudentPayment = (StudentPayment) this.get(StudentPayment.class, "custId=" + custId+ " and dpPaymentDetailsId="+ usedPaymentId + " and studentId="+ studentId);
							excessPayAmount = (ExcessPayment) this.get(ExcessPayment.class, "paymentId="+ studentPayment.getId()+ " and accountId="+ studentPayment.getStudent().getAccount().getId());
							if (ObjectFunctions.isNullOrEmpty(excessPayAmount)) {
								excessPayAmount = new ExcessPayment();
							}
							excessPayAmount.setPaymentId(studentPayment.getId());
							if (!ObjectFunctions.isNullOrEmpty(usedStudentPayment)) {
								excessPayAmount.setUsedPaymentId(usedStudentPayment.getId());
							}
							double excessAmount =  excessPaymentVO.getExcessAmount();
							if (!ObjectFunctions.isNullOrEmpty(studentPayment.getStudent())) {
								excessPayAmount.setAccountId(studentPayment.getStudent().getAccount().getId());
							}
							excessPayAmount.setExcessAmount(excessAmount);
							if (excessPaymentVO.isStatus())
								excessPayAmount.setStatus(true);
							else
								excessPayAmount.setStatus(false);
							createdDate = excessPaymentVO.getCreatedDate();
							excessPayAmount.setCreatedDate(DateFunctions.convertStringToDate(createdDate));
							this.save(excessPayAmount);
							excessPayAmount = null;
							studentPayment =null;
							usedStudentPayment =null;
							usedPaymentId=0;
							PaymentId=0;
						}
					}
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(studentList)){
				for(Student studentObj: studentList){
					this.checkStudentFeePaidStatus(studentObj.getAcademicYearId(),studentObj.getCustId(),studentObj);
				}
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewHostelStudentDailyAttendance> getHostelStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate){
		return adminDao.getHostelStudentsAttendanceByClassSectionIdAndAttendanceDate(classSectionId,attendanceDate);
	}
	public void insertIntoLoginAcceessbilityRoles(List<Role> roles,long custId){
		adminDao.insertIntoLoginAcceessbilityRoles(roles,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermId(String tableName,long custId,long academicYearId,String termIds,String fromDate,String toDate,String feeSettingIds){
		return adminDao.getStudentsFeePaidDetailsBySchoolTermId(tableName,custId,academicYearId,termIds,fromDate,toDate,feeSettingIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermIdAndEndDateAndStatus(String tableName,long custId,long academicYearId,String termIds,String toDate,String feeSettingIds,String checkValue){
		return adminDao.getStudentsFeePaidDetailsBySchoolTermIdAndEndDateAndStatus(tableName,custId,academicYearId,termIds,toDate,feeSettingIds,checkValue);
	}
	public List<OnlineApplicationDetailsView> getAdminssionApplicationFeeDetails(long custId,long academicYearId){
		return adminDao.getAdminssionApplicationFeeDetails(custId,academicYearId);
	}
	@Transactional
	public List<ViewStudentMarksDetails> getStudentMarksDetailsByClassIdAndAcademicYearIdAndCustId(long custId,long academicYearId,long classSectionId){
		return adminDao.getStudentMarksDetailsByClassIdAndAcademicYearIdAndCustId(custId,academicYearId,classSectionId);
	}
	@SuppressWarnings("unchecked")
    @Override
    @Transactional
   public List<StudyClass> GetAllStudyClasses(long custId,long academicYearId,String studyClassIds){
		
		StringBuffer query = new StringBuffer(" custId=").append(custId).append(" and academicYearId=").append(academicYearId);
		if (!StringFunctions.isNullOrEmpty(studyClassIds)){
			if(studyClassIds.contains("("))
				query.append(" and id in "+studyClassIds+"");
			else
			query.append(" and id in ("+studyClassIds+")");
		}	
		//Session session = this.sessionFactory.openSession();
        List<StudyClass> studyClassesList = adminDao.getStudyClasses(query.toString()); 
        		//this.getAll(StudyClass.class,query.toString()); //session.createQuery("from StudyClass").list();
       // session.close();
        return studyClassesList;
		//return adminDao.GetAllStudyClasses(custId,academicYearId,studyClassIds);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<StudySubject> GetAllStudySubjects(long custId,long academicYearId){
		return adminDao.GetAllStudySubjects(custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getFeeCollecttionWithPercentageDetailsByClassWise(String tableName,long custId,long academicYearId,String classSectionIds,String perValue,String statusValue){
		return adminDao.getFeeCollecttionWithPercentageDetailsByClassWise(tableName,custId,academicYearId,classSectionIds,perValue,statusValue);
	}
	@Transactional
	public List<Object[]> getStaffLeavesSummary(long staffId, long academicYearId){
		return adminDao.getStaffLeavesSummary(staffId, academicYearId);
	}
	
	@Transactional
	public StudentPayment entryStudentCommittedPayment(Customer customer,Student student,double pendingAmount,long createdUserId,String ipAddress){
		StudentPayment payment = null;
		if(pendingAmount !=0){
			long receiptNumber=0;
			String paymentStatus="P";
			double paidAmount =0;
			boolean processContinue =true;
			double allowedCommittedFee=pendingAmount;
			List<ViewClassFeeDetails> commitedClassFee = this.getAll(ViewClassFeeDetails.class, "custId="+customer.getId()+" and classSectionId="+student.getClassSection().getId()+" and categoryId="+student.getCategoryId()+" and committedFeeStatus='Y' order by priorityPosition,dueDate");
			if(!ObjectFunctions.isNullOrEmpty(commitedClassFee)){

				if(customer.isAcademicWiseFeeReceipt())
					receiptNumber = this.getInvoiceNumberByCustId("studentPayment", customer.getId(),student.getAcademicYearId()); 
				else
					receiptNumber = this.getInvoiceNumberByCustId("studentPayment", customer.getId(),0);
				if (receiptNumber == 0 && customer.getStartInvoiceNumber() != 0) {
					receiptNumber = customer.getStartInvoiceNumber();
				}else{
					receiptNumber = receiptNumber+1;
				}
				payment =  new StudentPayment();
				payment.setAcademicYear(student.getAcademicYear());
				payment.setCreatedById(createdUserId);
				payment.setCustId(customer.getId());
				payment.setInvoiceNumber(receiptNumber);
				payment.setIpAddress(ipAddress);
				payment.setLastUpdatedById(createdUserId);
				payment.setPaymentDate(new Date());
				payment.setPaymentType("CH");
				//payment.setDiscountDesc("");
				payment.setStudent(student);
				payment.setPaidAmount(pendingAmount);
				for(ViewClassFeeDetails feeDetails : commitedClassFee){
					if(processContinue == false)
						continue;
					Fee fee = (Fee)this.get(Fee.class,feeDetails.getFeeId());
					StudentFeePaidDetails studentFee = new StudentFeePaidDetails();
					studentFee.setCreatedById(createdUserId);
					studentFee.setCustId(customer.getId());
					studentFee.setFee(fee);
					studentFee.setLastUpdatedById(createdUserId);
					studentFee.setStudentId(student.getId());
					studentFee.setDeleteStatus(Constants.YES_STRING);
					
					if(feeDetails.getFeeAmount() >= allowedCommittedFee && allowedCommittedFee != 0){
						paidAmount= allowedCommittedFee;
						if(feeDetails.getFeeAmount() == paidAmount)
							paymentStatus = "P";
						else
							paymentStatus = "N";
					processContinue=false;
                    allowedCommittedFee =0;
					}else{
						paidAmount= feeDetails.getFeeAmount();
					}
					studentFee.setPaymentStatus(paymentStatus);
					studentFee.setPaymentAmount(paidAmount);
					studentFee.setCommittedFeeStatus(Constants.YES_STRING);
					payment.addStudentFeeDetails(studentFee);
					allowedCommittedFee = (allowedCommittedFee-feeDetails.getFeeAmount());
					feeDetails = null;
					fee = null;
				}
				commitedClassFee=null;
			}
			receiptNumber=0;
			payment = (StudentPayment)this.saveOrUpdateObject(payment);
			adminDao.updateStudentFeePaidStatus(student.getId(),"C");
			//this.checkStudentFeePaidStatus(student.getAcademicYearId(),customer.getId(),student);
		}
		return payment;
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate){
		return adminDao.getStudentFeeDefaultersList(custId, academicYearId, classIds,termsIs, toDayDate);
	}
	/*@Transactional
	public List<Object[]> getStudentAttendanceSummaryDetails(long academicYearId,long accountId, String type,String standardType){
		return adminDao.getStudentAttendanceSummaryDetails(academicYearId, accountId, type,standardType);
	}*/
	public boolean committedFeeStatusEnableOrDisable(long custId,long academicYearId){
		boolean committedFeeSatus = true;
		ViewStudentFeePaymentDetails studentFeePaymentDetails = (ViewStudentFeePaymentDetails)this.get(ViewStudentFeePaymentDetails.class, "custId="+custId+" and academicYearId="+academicYearId+" and paymentCommitFeeStatus='"+Constants.YES_STRING+"' ");
		if(!ObjectFunctions.isNullOrEmpty(studentFeePaymentDetails))
			committedFeeSatus =false;
		studentFeePaymentDetails=null;
		return committedFeeSatus;
	}
	public List<Events> getAllEventsByCustIdAndAcademicYearId(long custId, long academicYearId){
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'CreateParentLoginAccount' method");
		}
		try {
			String queryString = "custId =" + custId +" and academicYearId = "+ academicYearId+" order By startDate DESC";
			return (List<Events>)this.getAll(Events.class,queryString.toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public void updateHolidayStatusForClassWiseHolidays(String table,long custId, long academicYearId){
		adminDao.updateHolidayStatusForClassWiseHolidays(table, custId, academicYearId);
	}
	
	@Transactional
	public List<Object[]> getStudentDailyAttendanceSP(long custId, long academicYearId,long classSectionId){
		return adminDao.getStudentDailyAttendanceSP(custId, academicYearId,classSectionId);
	}

	@Override
	public BudgetRequest saveBudgetRequest(long custId, long financialYearId,double totalAmount,String tempString,long userId,int monthId,String comments,String createdBy,long budgetRequestId,boolean isSecretary,String budgetType) {
		try {
			if(monthId > 0 && financialYearId > 0)
			{
				FinancialYear financialYear = (FinancialYear)this.get(FinancialYear.class,financialYearId);
				if(!ObjectFunctions.isNullOrEmpty(financialYear))
				{
					BudgetRequest budgetRequest = null;
					if(budgetRequestId > 0)
						budgetRequest = (BudgetRequest)this.get(BudgetRequest.class,budgetRequestId);
					else
						budgetRequest = new BudgetRequest();
					budgetRequest.setManagerId(userId);
					budgetRequest.setStatus(Constants.PENDING_STATUS);
					budgetRequest.setRequestedMonth(monthId);
					budgetRequest.setBudgetType(budgetType);
					budgetRequest.setTotalBudgetAmount(totalAmount);
					budgetRequest.setMonthName(DateFunctions.geMonthNameByMonthId(monthId));
					budgetRequest.setComments(comments);
					budgetRequest.setCustId(custId);
					budgetRequest.setCreatedDate(new Date());
					log.debug(tempString);
					JSONArray perticualrJSONArray=new JSONArray(tempString);
					JSONObject perticualrJson=null;
					long perticularId=0;
					double perticularAmount=0;
					for(int i=0;i<perticualrJSONArray.length();i++)
					{
						perticualrJson=perticualrJSONArray.getJSONObject(i);
						if(!ObjectFunctions.isNullOrEmpty(perticualrJson))
						{
							if(isSecretary)
							{
								FinalBudgetRequest finalBudgetRequest = new FinalBudgetRequest();
								perticularId = Long.valueOf((String)perticualrJson.get("perticularId"));
								perticularAmount =Double.valueOf((String)perticualrJson.get("perticularAmount"));
								 if(perticularId > 0){
									 finalBudgetRequest.setParticularId(perticularId);
									 finalBudgetRequest.setAmount(perticularAmount);
									 finalBudgetRequest.setCreatedBy(createdBy);
									 finalBudgetRequest.setCreatedById(userId);
									 finalBudgetRequest.setCreatedDate(new Date());
									 finalBudgetRequest.setLastUpdatedById(userId);
									 finalBudgetRequest.setLastUpdatedDate(new Date());
									 finalBudgetRequest.setLastAccessDate(new Date());
									 budgetRequest.addFinalBudgetRequest(finalBudgetRequest);
								 }
							}
							else
							{
								BudgetParticularHistory budgetParticularHistory = new BudgetParticularHistory();
								perticularId = Long.valueOf((String)perticualrJson.get("perticularId"));
								perticularAmount =Double.valueOf((String)perticualrJson.get("perticularAmount"));
								 if(perticularId > 0){
									 budgetParticularHistory.setParticularId(perticularId);
									 budgetParticularHistory.setAmount(perticularAmount);
									 budgetParticularHistory.setCreatedBy(createdBy);
									 budgetParticularHistory.setCreatedById(userId);
									 budgetParticularHistory.setCreatedDate(new Date());
									 budgetParticularHistory.setLastUpdatedById(userId);
									 budgetParticularHistory.setLastUpdatedDate(new Date());
									 budgetParticularHistory.setLastAccessDate(new Date());
									 budgetRequest.addBudgetParticularHistory(budgetParticularHistory);
								 }
							}
					     }
						perticualrJson=null;
				     }
					budgetRequest = (BudgetRequest)this.save(budgetRequest);
					financialYear.addBudgetRequest(budgetRequest);
					this.save(financialYear);
					return budgetRequest;
				}
			financialYear = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return null;
	}
	public void createLoginAccessbilityRolesForRole(long roleId,long custId,String status)
	{
		LoginAccessbilityRoles loginAccessbilityRoles = (LoginAccessbilityRoles)this.get(LoginAccessbilityRoles.class,"roleId="+roleId+" and customerId="+custId+"  and status='"+status+"' ");
		if (ObjectFunctions.isNullOrEmpty(loginAccessbilityRoles)) 
		{
			loginAccessbilityRoles = new LoginAccessbilityRoles();
			loginAccessbilityRoles.setRoleId(roleId);
			loginAccessbilityRoles.setCustomerId(custId);
			loginAccessbilityRoles.setStatus(status);
			loginAccessbilityRoles.setAndroidStatus(status);
			this.save(loginAccessbilityRoles);
		}
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewBudgetRequestDetails> getAllViewBudgetRequestDetailsByFinancialYearIdCustIdStatus(long financialYearId, long custId, String statuss) {
		return adminDao.getAllViewBudgetRequestDetailsByFinancialYearIdCustIdStatus(financialYearId, custId, statuss);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewMeetingRequestDetails> getAllViewMeetingRequestDetailsByOrgIdCustIdDate(long orgId, long custId, String status) {
		return adminDao.getAllViewMeetingRequestDetailsByOrgIdCustIdDate(orgId, custId,status);
	}
	@Transactional
	public List<Object[]> getMonthlyStudentAttendanceSP(int pMonth,long classSectionId, long custId, long academicYearId){
		return adminDao.getMonthlyStudentAttendanceSP( pMonth, classSectionId,custId,academicYearId);
	}
	
	public Student upadateStudentMarksFromSMSAppAndWeb(Student student,long examScheduleId,double obtainedMarks,long accountId, String[] anyIds, String isPresent,HashMap<Integer, String> showingResult,HashMap<Long, ExamSchedules> schedulesMap){
        try {
        	//ViewExamSchedule examSchedule=null;
        	//ViewStudentMarks viewStudentMarks=null;
        	StudentMarks studentMarks=null;        	
        	List<Object[]> schedules=null;
        	ExamSchedules examSchedules=null;
        	
        	 if(schedulesMap.containsKey(examScheduleId)){
				 examSchedules=schedulesMap.get(examScheduleId);
			 }
			 else{
			  examSchedules= (ExamSchedules) this.get(ExamSchedules.class,"id=" +examScheduleId+" and custId = "+ student.getCustId() +" and academicYearId="+student.getAcademicYearId());
			  schedulesMap.put(examScheduleId, examSchedules);
			 }
        	
        	
        	double scheduleMaxMarks=0D;
        	double examTypeMaxMarks=0D;
        	Long subjectId=(long) 0;
        	String subjectType=null;
        	Long examTypeId=(long) 0; 
        	long marksId=0;
        	
        	showingResult.put(0, Boolean.toString(true));
        	long classSectionId=0;
        	 Object[] totalSubMarksObtained=null;
				if(examScheduleId >0){
					if(anyIds[0].equalsIgnoreCase("0"))
						schedules=this.getAll("select scheduleId,examTypeId,scheduleMaxMarks,subjectId,examTypeMaxMarks,subjectType from vw_examSchedule where custId="+student.getCustId()+" and academicYearId="+student.getAcademicYearId()+" and classSectionId="+student.getClassSectionId()+" and scheduleId="+examScheduleId);
					else
						schedules=this.getAll("select scheduleId,examTypeId,scheduleMaxMarks,subjectId,examTypeMaxMarks,subjectType from vw_examSchedule where custId="+student.getCustId()+" and academicYearId="+student.getAcademicYearId()+" and classSectionId="+student.getClassSectionId()+" and scheduleId="+examScheduleId+" and examTypeId="+anyIds[0]);
	    				
	    				if(!ObjectFunctions.isNullOrEmpty(schedules)){
	    					for(Object[] obj1 : schedules) {
	    						if(!ObjectFunctions.isNullOrEmpty(obj1[2]))
	    						scheduleMaxMarks=Double.valueOf(obj1[2].toString());
	    						if(!ObjectFunctions.isNullOrEmpty(obj1[4]))
	    						examTypeMaxMarks=Double.valueOf(obj1[4].toString());
	    						if(!ObjectFunctions.isNullOrEmpty(obj1[3]))
	    						subjectId=Long.valueOf(obj1[3].toString());
	    						if(!ObjectFunctions.isNullOrEmpty(obj1[5]))
	    						subjectType=obj1[5].toString();
	    						
	    						if(!ObjectFunctions.isNullOrEmpty(obj1[1]))
	    							examTypeId=Long.valueOf(obj1[1].toString());
	    						
	    					  break;	
	    					}
	    			  
						showingResult.put(1, String.valueOf(examTypeId));						
						
						//marksId = adminDao.getMarksIdByStudentIdAndExamScheduleId(student.getId(),examScheduleId);
						//log.debug("This is calling for uploading marks sheet ....."+student.getId() +"---"+new Date());
						//if(!ObjectFunctions.isNullOrEmpty(marksId)){							 
							//studentMarks= (StudentMarks)this.get(StudentMarks.class,"studId = "+ student.getId() +" and examScheduleId="+examScheduleId);
						//}
						
						List<ViewStudentMarks> viewStudentMarks= getStudentMarksByStudentIdAndExamtypeIdAndScheduleId(student.getId(),examTypeId,student.getCustId(),student.getAcademicYearId(),examScheduleId);
						
						 
						if(!ObjectFunctions.isNullOrEmpty(viewStudentMarks)){
							for(ViewStudentMarks vwStudentMarks:viewStudentMarks){
								marksId=vwStudentMarks.getMarksId();
								break;
							}
						}
		    			if(marksId==0){
	   	    				studentMarks=new StudentMarks();
	   	    				studentMarks.setPresent(true);
	   	    				studentMarks.setCreatedDate(new Date());
	   	    				studentMarks.setCreatedById(accountId);
	   	    			}else{
	   	    				studentMarks= (StudentMarks)this.get(StudentMarks.class,"id=" +marksId+" and studId = "+ student.getId() +" and examScheduleId="+examScheduleId);
	   	    				studentMarks.setLastUpdatedById(accountId);
	   	    			}
	   	    				
		    			if(scheduleMaxMarks >= obtainedMarks){
							studentMarks.setObtainedMarks(obtainedMarks);
							studentMarks.setPresent(true);
							student.setErrorMsg(null);
						}else{
							studentMarks.setObtainedMarks(0);
							studentMarks.setPresent(false);
							showingResult.put(0, Boolean.toString(false));
							if(scheduleMaxMarks < obtainedMarks)
							{
								if(!ObjectFunctions.isNullOrEmpty(student.getAccount()))
									if(!StringFunctions.isNullOrEmpty(student.getAccount().getFullPersonName()))
										student.setErrorMsg(student.getAccount().getFullPersonName());
							}
						}
   	    			if(anyIds.length > 2){
						totalSubMarksObtained=this.get("select totalSubjectMarksObtained,maxMarks from vw_studentMarksDetails where studId="+student.getId()+" and scheduleId="+examScheduleId);
						if(!ObjectFunctions.isNullOrEmpty(totalSubMarksObtained)){
							if(Double.valueOf(examTypeMaxMarks) >= (Double.valueOf(totalSubMarksObtained[0].toString()) + obtainedMarks)){ //&& (Double.valueOf(cell.toString())> 0)  -- To not allow negative marks
								studentMarks.setModerationMarks(obtainedMarks);
							}else{
								studentMarks.setModerationMarks(0);
								showingResult.put(0, Boolean.toString(false));
							}
						}else if(Double.valueOf(examTypeMaxMarks) >= obtainedMarks){
							studentMarks.setModerationMarks(obtainedMarks);
						}
   	    			}else{
						if(obtainedMarks >0){ 
							if(scheduleMaxMarks >= obtainedMarks){
								studentMarks.setObtainedMarks(obtainedMarks);
								studentMarks.setPresent(true);
							}else{
								showingResult.put(0, Boolean.toString(false));
							}
						}else if(obtainedMarks==0){
							studentMarks.setObtainedMarks(0);
							studentMarks.setPresent(false);
						}else{
							showingResult.put(0, Boolean.toString(false));
						}
						if(StringFunctions.isNotNullOrEmpty(isPresent)){
							studentMarks.setPresent(isPresent.equalsIgnoreCase("P")?true:false);
						}
					}
   	    			if(Constants.YES_STRING.equalsIgnoreCase(subjectType) && student.getOptionalSubjectId()>0){
   	    					student.setOptionalSubjectId(subjectId);
   	    			}
   	    			studentMarks.setExamSchedule(examSchedules);
   					studentMarks.setLastUpdatedDate(new Date());
   					studentMarks.setLastAccessDate(new Date());
   					student.setRouteId(examTypeId);   					
   					student.addStudentMarks(studentMarks);
   					studentMarks=null;
   					//viewStudentMarks=null;
   				 }
	    		}
				
        } catch (Exception e) {
        	e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
        }
        return student;
    }
	
	
	public void insertStudyClassIdInsteadOfClassId(long studyClassId, long examTypeId){
		adminDao.insertStudyClassIdInsteadOfClassId(studyClassId, examTypeId);
	}
	public Map<String,String> disableCustomer(long custId,String reasonForDisable, long operatorId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering adminManager 'disableCustomer' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
			if(custId != 0 && StringFunctions.isNotNullOrEmpty(reasonForDisable)){
				Customer customer=(Customer) this.get(Customer.class, custId);
				/*List<User> userList = this.getAll(User.class,"custId="+custId);
				if(!ObjectFunctions.isNullOrEmpty(userList)){
					for(User user: userList){
						user.setAccountExpired(true);
						user.setEnabled(false);
						this.save(user);
					}
				}*/
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					customer.setLastUpdatedDate(new Date());
					customer.setLastUpdatedById(operatorId);
					customer.setLastAccessDate(new Date());
					customer.setStatus(false);
					customer.setLastUpdatedById(operatorId);
					//customer.setCustomerStatus("N");
					customer.setCreatedDate(new Date());
					customer.setCustomerInActiveDescription(reasonForDisable);
					this.save(customer);
					msg.put("0", "Customer Account deactivated successfully");
				}else{
					msg.put("1", "No Customer exists with customer identifier");
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		return msg;
	}
	public Map<Integer,String> addorUpdateEmailContactDetails(Customer customerDetails,Customer customer,long userId){
		if (log.isDebugEnabled()) {
			log.debug("Entering adminManager 'disableCustomer' method");
		}
		Map<Integer, String> msg = new HashMap<Integer,String>();
		try {
			String[] emailAddresses = new String[1];
			emailAddresses[0]="messages@eazyschool.com";
			MailUtil mailUtil = new MailUtil(emailAddresses,"Email Configuration Validation","Testing From Email varification", customerDetails.getContactEmail(),null);
			mailUtil.setContactEmail(customerDetails.getContactEmail());
			mailUtil.setContactPasword(customerDetails.getContactPassword());
			int returnCode = mailUtil.sendVarificationForValidEmail();
			if(returnCode == 0){
		    	customer.setContactEmail(customerDetails.getContactEmail());
		    	customer.setContactPassword(customerDetails.getContactPassword());
		    	customer.setLastUpdatedById(userId);
		    	customer.setLastAccessDate(new Date());
				customer.setLastUpdatedDate(new Date());
				this.save(customer);
				customer=null;
				msg.put(returnCode, "From email details updated successfully.");
			}else if (returnCode == 1) 
				msg.put(returnCode, "From email address or password wrong.");
			else if (returnCode == 2)
				msg.put(returnCode, "Connection timed out please try again.");
			else if (returnCode == 3) 
				msg.put(returnCode, "System error occurred. Please contact customer support.");
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			msg.put(3 , "System error occurred. Please contact customer support.");
		}
		return msg;
	}
	public ViewStaffPersonAccountDetailsMainVO getStaffDetails(long custId,long academicYearId){
		try {
			ViewStaffPersonAccountDetailsMainVO staffPersonAccountDetailsMainVO =null;
			ViewStaffPersonAccountDetailsVO staffPersonAccountDetailsVO =null;
			List<ViewStaffPersonAccountDetailsVO> staffPersonAccountDetailsVOs = new ArrayList<ViewStaffPersonAccountDetailsVO>();
			/* they need all active and inactive status staff so I remove status condition*/
			List<ViewStaffPersonAccountDetails> staffList = this.getAll(ViewStaffPersonAccountDetails.class, "custId = "+ custId);//status = 'Y' AND 
			if (!ObjectFunctions.isNullOrEmpty(staffList)) {
				for(ViewStaffPersonAccountDetails staff : staffList){
					staffPersonAccountDetailsVO = new ViewStaffPersonAccountDetailsVO();
					staffPersonAccountDetailsVO.setStaffId(staff.getStaffId());
					staffPersonAccountDetailsVO.setAccountId(staff.getAccountId());
					staffPersonAccountDetailsVO.setStaffType(ObjectFunctions.isNullOrEmpty(staff.getStaffType())? "": staff.getStaffType());
					staffPersonAccountDetailsVO.setQualification1(ObjectFunctions.isNullOrEmpty(staff.getQualification1())? "": staff.getQualification1());
					staffPersonAccountDetailsVO.setQualification2(ObjectFunctions.isNullOrEmpty(staff.getQualification2())? "": staff.getQualification2());
					staffPersonAccountDetailsVO.setDateofJoining(ObjectFunctions.isNullOrEmpty(staff.getDateofJoining())? "": DateFunctions.convertDateToString(staff.getDateofJoining()));
					staffPersonAccountDetailsVO.setUsername(ObjectFunctions.isNullOrEmpty(staff.getUsername())? "": staff.getUsername());
					staffPersonAccountDetailsVO.setRoleName(staff.getRoleName());
					staffPersonAccountDetailsVO.setFirstName(staff.getFirstName());
					staffPersonAccountDetailsVO.setLastName(ObjectFunctions.isNullOrEmpty(staff.getLastName())? "": staff.getLastName());
					staffPersonAccountDetailsVO.setGender(ObjectFunctions.isNullOrEmpty(staff.getGender())? "": staff.getGender());
					staffPersonAccountDetailsVO.setImageId(ObjectFunctions.isNullOrEmpty(staff.getImageId())? 0: staff.getImageId());
					staffPersonAccountDetailsVO.setMobileNumber(ObjectFunctions.isNullOrEmpty(staff.getMobileNumber())? "": staff.getMobileNumber());
					staffPersonAccountDetailsVO.setPhoneNumber(ObjectFunctions.isNullOrEmpty(staff.getPhoneNumber())? "": staff.getPhoneNumber());
					staffPersonAccountDetailsVO.setDateOfBirth(ObjectFunctions.isNullOrEmpty(staff.getDateOfBirth())? "": DateFunctions.convertDateToString(staff.getDateOfBirth()));
					staffPersonAccountDetailsVO.setBioMetricId(ObjectFunctions.isNullOrEmpty(staff.getBioMetricId())? 0: staff.getBioMetricId());
					staffPersonAccountDetailsVO.setEmail(ObjectFunctions.isNullOrEmpty(staff.getEmail())? "": staff.getEmail());
					staffPersonAccountDetailsVO.setStaffUniqueNumber(ObjectFunctions.isNullOrEmpty(staff.getStaffUniqueNumber())? "": staff.getStaffUniqueNumber());
					staffPersonAccountDetailsVO.setDesignation(ObjectFunctions.isNullOrEmpty(staff.getDesignation())? "": staff.getDesignation());
					staffPersonAccountDetailsVO.setBankName(ObjectFunctions.isNullOrEmpty(staff.getBankName())? "": staff.getBankName());
					staffPersonAccountDetailsVO.setBankAccountNumber(ObjectFunctions.isNullOrEmpty(staff.getBankAccountNumber())? "": staff.getBankAccountNumber());
					staffPersonAccountDetailsVO.setBankBranchName(ObjectFunctions.isNullOrEmpty(staff.getBankBranchName())? "": staff.getBankBranchName());
					staffPersonAccountDetailsVO.setSalary(ObjectFunctions.isNullOrEmpty(staff.getSalary())? 0.0: staff.getSalary());
					staffPersonAccountDetailsVO.setIfscCode(ObjectFunctions.isNullOrEmpty(staff.getIfscCode())? "": staff.getIfscCode());
					staffPersonAccountDetailsVO.setPanNumber(ObjectFunctions.isNullOrEmpty(staff.getPanNumber())? "": staff.getPanNumber());
					staffPersonAccountDetailsVO.setPfNo(ObjectFunctions.isNullOrEmpty(staff.getPfNo())? "": staff.getPfNo());
					staffPersonAccountDetailsVO.setEsiNo(ObjectFunctions.isNullOrEmpty(staff.getEsiNo())? "": staff.getEsiNo());
					staffPersonAccountDetailsVO.setSalaryPaymentMode(ObjectFunctions.isNullOrEmpty(staff.getSalaryPaymentMode())? "": staff.getSalaryPaymentMode());
					staffPersonAccountDetailsVO.setStaffGrade(ObjectFunctions.isNullOrEmpty(staff.getStaffGrade())? "": staff.getStaffGrade());
					staffPersonAccountDetailsVO.setStaffLocation(ObjectFunctions.isNullOrEmpty(staff.getStaffLocation())? "": staff.getStaffLocation());

					/*SchoolShiftInfoVO shiftInfoVO = new SchoolShiftInfoVO();
					shiftInfoVO.setStartTime(ObjectFunctions.isNullOrEmpty(staff.getStartTime())? "": staff.getStartTime());
					shiftInfoVO.setEndTime(ObjectFunctions.isNullOrEmpty(staff.getEndTime())? "": staff.getEndTime());
					shiftInfoVO.setShiftName(ObjectFunctions.isNullOrEmpty(staff.getShiftName())? "": staff.getShiftName());
					shiftInfoVO.setId(ObjectFunctions.isNullOrEmpty(staff.getShiftId())? 0: Long.valueOf(staff.getShiftId()));
					staffPersonAccountDetailsVO.setSchoolShiftInfoVO(shiftInfoVO);
					shiftInfoVO=null;*/
					// Address
					//AddressVO addressVO = new AddressVO();
					staffPersonAccountDetailsVO.setAddressLine1(ObjectFunctions.isNullOrEmpty(staff.getAddressLine1())? "": staff.getAddressLine1());
					staffPersonAccountDetailsVO.setAddressLine2(ObjectFunctions.isNullOrEmpty(staff.getAddressLine2())? "": staff.getAddressLine2());
					staffPersonAccountDetailsVO.setCity(ObjectFunctions.isNullOrEmpty(staff.getCity())? "": staff.getCity());
					staffPersonAccountDetailsVO.setState(ObjectFunctions.isNullOrEmpty(staff.getState())? "": staff.getState());
					staffPersonAccountDetailsVO.setPostalCode(ObjectFunctions.isNullOrEmpty(staff.getPostalCode())? "": staff.getPostalCode());
					//staffPersonAccountDetailsVO.setAddressVO(addressVO);
					//addressVO=null;
					// Staff Subjects
					JSONArray subjectsArray = new JSONArray();
					List<ClassTeacher> staffSubjectList = this.getAll(ClassTeacher.class, "teacherId="+ staff.getStaffId() +" and academicYearId="+ academicYearId);
					if (!ObjectFunctions.isNullOrEmpty(staffSubjectList)) {
						ClassTeacherVO classTeacherVO = null;
						for(ClassTeacher staffSubject : staffSubjectList){
							classTeacherVO = new ClassTeacherVO();
							classTeacherVO.setClassTeacher(ObjectFunctions.isNullOrEmpty(staffSubject.getIsClassTeacher())? "": staffSubject.getIsClassTeacher());
							classTeacherVO.setStudyClassId(staffSubject.getStudyClassId());
							classTeacherVO.setStudySubjectId(staffSubject.getStudySubjectId());
							classTeacherVO.setSubjectName(staffSubject.getStudySubject().getName());
							classTeacherVO.setPeriodsCount(staffSubject.getPeriodsCount());
							staffPersonAccountDetailsVO.getClassTeacherVOs().add(classTeacherVO);
							classTeacherVO=null;
						}
					}
					// Staff Leaves
					LeaveVO leaveVO = new LeaveVO();
					List<Object[]> staffLeaves = this.getStaffLeavesSummary(Long.valueOf(custId), Long.valueOf(academicYearId));
					if (!ObjectFunctions.isNullOrEmpty(staffLeaves)) {
						Object[] staffLeave = staffLeaves.get(0);
						if(!ObjectFunctions.isNullOrEmpty(staffLeaves)){
							leaveVO.setTotalCasualLeaves(Double.valueOf(staffLeave[1].toString()));
							leaveVO.setUsedCasualLeaves(Double.valueOf(staffLeave[2].toString()));
							leaveVO.setRemainingCasualLeaves(Double.valueOf(staffLeave[3].toString()));
							leaveVO.setTotalSickLeaves(Double.valueOf(staffLeave[4].toString()));
							leaveVO.setUsedSickLeaves(Double.valueOf(staffLeave[5].toString()));
							leaveVO.setRemainingSickLeaves(Double.valueOf(staffLeave[6].toString()));
							leaveVO.setTotalEarnedLeaves(Double.valueOf(staffLeave[7].toString()));
							leaveVO.setUsedEarnedLeaves(Double.valueOf(staffLeave[8].toString()));
							leaveVO.setRemainingEarnedLeaves(Double.valueOf(staffLeave[9].toString()));
						}
					}
					staffPersonAccountDetailsVO.setLeaveVO(leaveVO);
					staffPersonAccountDetailsVO.setStatus(staff.getStatus());
					
					staffPersonAccountDetailsVOs.add(staffPersonAccountDetailsVO);
				}
				staffPersonAccountDetailsMainVO = new ViewStaffPersonAccountDetailsMainVO();
				staffPersonAccountDetailsMainVO.setStaffPersonAccountDetailsVOS(staffPersonAccountDetailsVOs);
				return staffPersonAccountDetailsMainVO;
			}else{
				return null;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewStaffPersonAccountDetailsMainVO getTeachingStaffDetails(long accountId,String type){
		try {
			ViewStaffPersonAccountDetailsMainVO staffPersonAccountDetailsMainVO =null;
			ViewStaffPersonAccountDetailsVO staffPersonAccountDetailsVO =null;
			List<ViewStaffPersonAccountDetailsVO> staffPersonAccountDetailsVOs = new ArrayList<ViewStaffPersonAccountDetailsVO>();
			// Customers list
			String whereClause = "";
			List<String> academicYears = null;
			List<String> studyClassIds = null;
			if(type.equalsIgnoreCase("P")){
                List<String> custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+accountId);
                academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
                studyClassIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM vw_studentClassDetails s WHERE s.parentId="+accountId);
                List<String> staffIds = this.getAll("SELECT GROUP_CONCAT(ct.teacherId) FROM classTeacher ct WHERE ct.studyClassId IN("+studyClassIds.get(0)+")");
                whereClause = "staffId IN("+staffIds.get(0)+") OR (roleId IN(8, 12, 31)) AND custId IN ("+custIds.get(0)+")";
                }else if(type.equalsIgnoreCase("C")){
				List<Integer> custId = this.getAll("SELECT a.custId FROM Account a WHERE a.id = "+accountId);
				academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custId.get(0)+") AND status = 'Y';");
				studyClassIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM student s WHERE s.academicYearId IN("+academicYears.get(0)+") AND s.accountId  = "+accountId);
				List<String> staffIds = this.getAll("SELECT GROUP_CONCAT(ct.teacherId) FROM classTeacher ct WHERE ct.studyClassId IN("+studyClassIds.get(0)+")");
				whereClause = "staffId IN("+staffIds.get(0)+") OR (roleId IN(2, 8, 12, 31, 35) AND custId = "+custId.get(0)+")";
			}
			else{
				List<Integer> custId = this.getAll("SELECT a.custId FROM Account a WHERE a.id = "+accountId);
				academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custId.get(0)+") AND status = 'Y';");
				studyClassIds = this.getAll("SELECT GROUP_CONCAT(s.id) FROM studyClass s WHERE s.academicYearId ="+academicYears.get(0));
				//whereClause = "roleId IN(2, 8, 12, 31, 35) AND custId = "+custId.get(0);
				whereClause = "status = 'Y' AND custId = "+custId.get(0);
			}
			/* they need all active and inactive status staff so I remove status condition*/
			List<ViewStaffPersonAccountDetails> staffList = this.getAll(ViewStaffPersonAccountDetails.class, whereClause);//status = 'Y' AND 
			if (!ObjectFunctions.isNullOrEmpty(staffList)) {
				for(ViewStaffPersonAccountDetails staff : staffList){
					staffPersonAccountDetailsVO = new ViewStaffPersonAccountDetailsVO();
					staffPersonAccountDetailsVO.setStaffId(staff.getStaffId());
					staffPersonAccountDetailsVO.setAccountId(staff.getAccountId());
					staffPersonAccountDetailsVO.setStaffType(ObjectFunctions.isNullOrEmpty(staff.getStaffType())? "": staff.getStaffType());
					staffPersonAccountDetailsVO.setQualification1(ObjectFunctions.isNullOrEmpty(staff.getQualification1())? "": staff.getQualification1());
					staffPersonAccountDetailsVO.setQualification2(ObjectFunctions.isNullOrEmpty(staff.getQualification2())? "": staff.getQualification2());
					staffPersonAccountDetailsVO.setDateofJoining(ObjectFunctions.isNullOrEmpty(staff.getDateofJoining())? "": DateFunctions.convertDateToString(staff.getDateofJoining()));
					staffPersonAccountDetailsVO.setUsername(ObjectFunctions.isNullOrEmpty(staff.getUsername())? "": staff.getUsername());
					staffPersonAccountDetailsVO.setRoleName(staff.getRoleName());
					staffPersonAccountDetailsVO.setFirstName(staff.getFirstName());
					staffPersonAccountDetailsVO.setLastName(ObjectFunctions.isNullOrEmpty(staff.getLastName())? "": staff.getLastName());
					staffPersonAccountDetailsVO.setGender(ObjectFunctions.isNullOrEmpty(staff.getGender())? "": staff.getGender());
					staffPersonAccountDetailsVO.setImageId(ObjectFunctions.isNullOrEmpty(staff.getImageId())? 0: staff.getImageId());
					staffPersonAccountDetailsVO.setMobileNumber(ObjectFunctions.isNullOrEmpty(staff.getMobileNumber())? "": staff.getMobileNumber());
					staffPersonAccountDetailsVO.setPhoneNumber(ObjectFunctions.isNullOrEmpty(staff.getPhoneNumber())? "": staff.getPhoneNumber());
					staffPersonAccountDetailsVO.setDateOfBirth(ObjectFunctions.isNullOrEmpty(staff.getDateOfBirth())? "": DateFunctions.convertDateToString(staff.getDateOfBirth()));
					staffPersonAccountDetailsVO.setBioMetricId(ObjectFunctions.isNullOrEmpty(staff.getBioMetricId())? 0: staff.getBioMetricId());
					staffPersonAccountDetailsVO.setEmail(ObjectFunctions.isNullOrEmpty(staff.getEmail())? "": staff.getEmail());
					staffPersonAccountDetailsVO.setStaffUniqueNumber(ObjectFunctions.isNullOrEmpty(staff.getStaffUniqueNumber())? "": staff.getStaffUniqueNumber());
					staffPersonAccountDetailsVO.setDesignation(ObjectFunctions.isNullOrEmpty(staff.getDesignation())? "": staff.getDesignation());
					staffPersonAccountDetailsVO.setAcademicYearId(ObjectFunctions.isNullOrEmpty(staff.getAcademicYearId())? 0: Long.valueOf(staff.getAcademicYearId()));
					staffPersonAccountDetailsVO.setCustId(ObjectFunctions.isNullOrEmpty(staff.getCustId())? 0: staff.getCustId());
					staffPersonAccountDetailsVO.setImageUrl(ObjectFunctions.isNullOrEmpty(staff.getImagePath())? "": staff.getImagePath());
					staffPersonAccountDetailsVO.setPanNumber(ObjectFunctions.isNullOrEmpty(staff.getPanNumber())? "": staff.getPanNumber());
					staffPersonAccountDetailsVO.setAddressLine1(ObjectFunctions.isNullOrEmpty(staff.getAddressLine1())? "": staff.getAddressLine1());
					staffPersonAccountDetailsVO.setAddressLine2(ObjectFunctions.isNullOrEmpty(staff.getAddressLine2())? "": staff.getAddressLine2());
					staffPersonAccountDetailsVO.setCity(ObjectFunctions.isNullOrEmpty(staff.getCity())? "": staff.getCity());
					staffPersonAccountDetailsVO.setState(ObjectFunctions.isNullOrEmpty(staff.getState())? "": staff.getState());
					staffPersonAccountDetailsVO.setPostalCode(ObjectFunctions.isNullOrEmpty(staff.getPostalCode())? "": staff.getPostalCode());
					// Staff Subjects
					JSONArray subjectsArray = new JSONArray();
					List<Object[]> staffSubjectList = this.getAll("select IFNULL(studyClassId,0) as studyClassId,IFNULL(studySubjectId,0) as studySubjectId,IF(classTeacher = 'Y', 'Yes', 'No') from classTeacher where studyClassId IN("+studyClassIds.get(0)+") AND teacherId="+ staff.getStaffId() +" and academicYearId IN("+ academicYears.get(0) +")");
					if (!ObjectFunctions.isNullOrEmpty(staffSubjectList)) {
						ClassTeacherVO classTeacherVO = null;
						for(Object[] staffSubject : staffSubjectList){
							classTeacherVO = new ClassTeacherVO();
							classTeacherVO.setClassTeacher(ObjectFunctions.isNullOrEmpty(staffSubject[2].toString())?"":staffSubject[2].toString());
							classTeacherVO.setStudyClassId(ObjectFunctions.isNullOrEmpty(staffSubject[0].toString())?0:Long.valueOf(staffSubject[0].toString()));
							classTeacherVO.setStudySubjectId(ObjectFunctions.isNullOrEmpty(staffSubject[1].toString())?0:Long.valueOf(staffSubject[1].toString()));
							staffPersonAccountDetailsVO.getClassTeacherVOs().add(classTeacherVO);
							classTeacherVO=null;
						}
					}
					staffPersonAccountDetailsVO.setStatus(staff.getStatus());
					
					staffPersonAccountDetailsVOs.add(staffPersonAccountDetailsVO);
				}
				staffPersonAccountDetailsMainVO = new ViewStaffPersonAccountDetailsMainVO();
				staffPersonAccountDetailsMainVO.setStaffPersonAccountDetailsVOS(staffPersonAccountDetailsVOs);
				return staffPersonAccountDetailsMainVO;
			}else{
				return null;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewAllUsersMainVO getUserDetails(long custId,long webAccountId){
		ViewAllUsersMainVO viewAllUsersMainVO = new ViewAllUsersMainVO();
		List<ViewAllUsersVO> allUsersVOs = new ArrayList<ViewAllUsersVO>();
		ViewAllUsersVO viewAllUsersVO =null;
		try {
			
			StringBuilder query = new StringBuilder(" custId = "+ custId +" AND roleId IN(1,4,9,11,14,15,16)");
			
			if(webAccountId > 0)
			{
				User curUser= (User)this.get(User.class,webAccountId);
				query.append(" and (accountId >"+ webAccountId +" OR lastUpdatedDate>='"+curUser.getLastUpdatedDate()+"')");
				curUser = null;
			}
			List<ViewAllUsers> usersList =  this.getAll(ViewAllUsers.class, query.toString());
			if(!ObjectFunctions.isNullOrEmpty(usersList)) {
				JSONArray userArray = new JSONArray();
				for(ViewAllUsers user : usersList) {
					viewAllUsersVO = new ViewAllUsersVO();
					viewAllUsersVO.setAccountId(user.getAccountId());
					viewAllUsersVO.setUsername(user.getUsername());
					viewAllUsersVO.setRoleName(user.getRoleName());
					viewAllUsersVO.setFirstName(user.getFirstName());
					viewAllUsersVO.setLastName((ObjectFunctions.isNullOrEmpty(user.getLastName()))? "": user.getLastName());
					viewAllUsersVO.setPassword((ObjectFunctions.isNullOrEmpty(user.getPassword()))? "": user.getPassword());
					allUsersVOs.add(viewAllUsersVO);
					viewAllUsersVO = null;
				}
				viewAllUsersMainVO.setViewAllUsersVOs(allUsersVOs);
				allUsersVOs = null;
				return viewAllUsersMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ClassNameMainVO getClassDetails(long academicYearId){
		try {
			ClassNameVO classNameVO = null;
			List<ClassNameVO> classNameVOs = new ArrayList<ClassNameVO>();
			ClassNameMainVO classNameMainVO = new ClassNameMainVO();
			List<ClassName> classesList = this.getAll(ClassName.class, "academicYearId = "+ academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(classesList)){
				for(ClassName className : classesList){
					classNameVO = new ClassNameVO();
					classNameVO.setClassName(className.getClassName());
					classNameVO.setNoOfSections(className.getNoOfSections());
					classNameVO.setSortingOrder(className.getSortingOrder());
					classNameVO.setId(className.getId());
					// Sections
					List<StudyClass> studyClassList = this.getAll(StudyClass.class, " classNameClassId = " + className.getId());
					classNameVO = addStudyClassAndStudySubjectVo(studyClassList,classNameVO);
					classNameVOs.add(classNameVO);
					classNameVO = null;
				}
			}
			classNameMainVO.setClassNameVOs(classNameVOs);
			return classNameMainVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public ClassNameMainVO getStudyClassesDetailsRespectedPerson(long accountId, String type){
		try{
			StudyClassVO studyClassVO = null;
			StudySubjectVO studySubjectVO = null;
			ClassNameVO classNameVO = null;
			ClassNameMainVO classNameMainVO = null;
			List<StudyClass> studyClassList = null;
			List<ClassNameVO> classNameVOs = new ArrayList<ClassNameVO>();
			if(accountId > 0 && !StringFunctions.isNullOrEmpty(type)){
				User curUser= (User)this.get(User.class,accountId);
				if(!ObjectFunctions.isNullOrEmpty(curUser))
				{
					AcademicYear academicYear  = super.getCurrentAcademicYear("Y",curUser.getCustId());
					if (!ObjectFunctions.isNullOrEmpty(academicYear)) 
					{
						if(type.equalsIgnoreCase("A")){ //ADMIN
							studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.academicYear = "+ academicYear.getId()+" ORDER BY c.classNameClassId");
						}
						else if(type.equalsIgnoreCase("S")){// STAFF
							studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.id IN (SELECT ct.studyClass FROM ClassTeacher ct WHERE ct.academicYear = "+ academicYear.getId() +" AND ct.staff in (select s.id from Staff s where s.account= "+ accountId +"))  ORDER BY c.classNameClassId");
						}else if(type.equalsIgnoreCase("P")){ // PARENT
							//studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.id IN (SELECT s.classSection FROM Student s WHERE s.status = 'Y' AND s.account in (select u.id from User u where u.parentId= "+ accountId +")) ORDER BY c.classNameClassId");
							List<String> studyClassIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM vw_studentClassDetails s WHERE s.parentId="+accountId);
							if(!ObjectFunctions.isNullOrEmpty(studyClassIds)){
								studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.id IN ("+studyClassIds.get(0)+")");
							}
						}else if(type.equalsIgnoreCase("C")){ // STUDENT
							studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.id IN (SELECT s.classSection FROM Student s WHERE s.academicYear = "+ academicYear.getId() +" AND s.account = "+ accountId +") ORDER BY c.classNameClassId");
						}
						if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
							long classId = 0;
							int size = 0;
							for(StudyClass studyClassObj : studyClassList){
								if(classNameVO == null){
									classNameVO = new ClassNameVO();
									classId = studyClassObj.getClassId();
								}
								if(classId>0 && classId!= studyClassObj.getClassId()){
									classId = studyClassObj.getClassId();
									classNameVOs.add(classNameVO);
									classNameVO = new ClassNameVO();
								}
								classNameVO.setClassName(studyClassObj.getClassName());
								classNameVO.setNoOfSections(studyClassObj.getClassNameClassId().getNoOfSections());
								classNameVO.setSortingOrder(studyClassObj.getClassNameClassId().getSortingOrder());
								classNameVO.setId(studyClassObj.getClassId());
								studyClassVO = new StudyClassVO();
								studyClassVO.setId(studyClassObj.getId());
								studyClassVO.setSection(ObjectFunctions.isNullOrEmpty(studyClassObj.getSection())? "": studyClassObj.getSection());
								studyClassVO.setSectionCapacity(studyClassObj.getSectionCapacity());
								if(!ObjectFunctions.isNullOrEmpty(studyClassObj.getSubjects())){
									for(StudySubject subject : studyClassObj.getSubjects()){
										studySubjectVO = new StudySubjectVO();
										studySubjectVO.setId(subject.getId());
										studySubjectVO.setName(subject.getName());
										studyClassVO.getStudySubjectList().add(studySubjectVO);
										studySubjectVO = null;
									}
								}
								classNameVO.getStudyClassList().add(studyClassVO);
								if(size == studyClassList.size()-1){
									classId = studyClassObj.getClassId();
									classNameVOs.add(classNameVO);
									classNameVO = null;
								}
									
								size++;
								studyClassVO = null;
								//classNameVO = null;
							}
						}
					}
				}
			}
			classNameMainVO = new ClassNameMainVO();
			classNameMainVO.setClassNameVOs(classNameVOs);
			classNameVOs = null;
			return classNameMainVO;
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public ClassNameVO addStudyClassAndStudySubjectVo(List<StudyClass> studyClassList,ClassNameVO classNameVO){
		try{
			StudyClassVO studyClassVO = null;
			StudySubjectVO studySubjectVO = null;
			if (!ObjectFunctions.isNullOrEmpty(studyClassList)) {
				for(StudyClass studyClass : studyClassList){
					studyClassVO = new StudyClassVO();
					studyClassVO.setId(studyClass.getId());
					studyClassVO.setSection(ObjectFunctions.isNullOrEmpty(studyClass.getSection())? "": studyClass.getSection());
					studyClassVO.setSectionCapacity(studyClass.getSectionCapacity());
					if(!ObjectFunctions.isNullOrEmpty(studyClass.getSubjects())){
						for(StudySubject subject : studyClass.getSubjects()){
							studySubjectVO = new StudySubjectVO();
							studySubjectVO.setId(subject.getId());
							studySubjectVO.setName(subject.getName());
							studyClassVO.getStudySubjectList().add(studySubjectVO);
							studySubjectVO = null;
						}
					}
					classNameVO.getStudyClassList().add(studyClassVO);
					studyClassVO = null;
				}	
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return classNameVO;
	}
	public ViewClassAssignmentDetailsMainVO getAssignmentDetails(long id, boolean isStudyClassId){
		ViewClassAssignmentDetailsVO classAssignmentDetailsVO = null;
		StudySubjectVO studySubjectVO=null;
		StudyClassVO studyClassVO = null;
		AttachmentVO attachmentVO=null;
		ViewClassAssignmentDetailsMainVO viewClassAssignmentDetailsMainVO=null;
		List<ViewClassAssignmentDetailsVO> viewClassAssignmentDetailsVOList = new ArrayList<ViewClassAssignmentDetailsVO>();
		List<AttachmentVO> attachmentVOList = new ArrayList<AttachmentVO>();
		try { 
			if(id > 0){
				String query = "";
				if(isStudyClassId){
					query = "SELECT ca.assignmentId, ca.classId, ca.classSectionId, ca.className, ca.assignmentDescription, ca.classAndSection, ca.createdBy, ca.subjectName, ca.subjectId, at.filePath, at.name, at.id, ca.assignmentDate FROM vw_classAssignmentDetails ca LEFT JOIN attachment at ON(at.classAssignmentId = ca.assignmentId) WHERE ca.classSectionId = "+ id +" AND DATE(ca.assignmentDate) >= DATE(CURDATE())";
				}else{
					query = "SELECT ca.assignmentId, ca.classId, ca.classSectionId, ca.className, ca.assignmentDescription, ca.classAndSection, ca.createdBy, ca.subjectName, ca.subjectId, at.filePath, at.name, at.id, ca.assignmentDate FROM vw_classAssignmentDetails ca LEFT JOIN attachment at ON(at.classAssignmentId = ca.assignmentId) WHERE ca.academicYearId = "+ id +" AND DATE(ca.assignmentDate) >= DATE(CURDATE())";
				}
				List<Object[]> assignmentsList = this.getAll(query);
				if(!ObjectFunctions.isNullOrEmpty(assignmentsList)) {
					long assignmentId = 0;
					int size = 0;
					//JSONArray assignmentsArray = new JSONArray();
					for(Object[] assignment : assignmentsList) {
						if(classAssignmentDetailsVO == null){
							classAssignmentDetailsVO = new ViewClassAssignmentDetailsVO();
							assignmentId = ObjectFunctions.isNullOrEmpty(assignment[0])? 0: Long.valueOf(assignment[0].toString());
						}
						if(assignmentId>0 && assignmentId!= Long.valueOf(assignment[0].toString()))
						{
							assignmentId = ObjectFunctions.isNullOrEmpty(assignment[0])? 0: Long.valueOf(assignment[0].toString());
							viewClassAssignmentDetailsVOList.add(classAssignmentDetailsVO);
							attachmentVOList = new ArrayList<AttachmentVO>();
							classAssignmentDetailsVO = new ViewClassAssignmentDetailsVO();
						}
												
						classAssignmentDetailsVO.setId(Long.valueOf(assignment[0].toString()));
						classAssignmentDetailsVO.setDescription(ObjectFunctions.isNullOrEmpty(assignment[4])? "": assignment[4].toString());
						classAssignmentDetailsVO.setCreatedBy(ObjectFunctions.isNullOrEmpty(assignment[6])? "": assignment[6].toString());
						classAssignmentDetailsVO.setDate(ObjectFunctions.isNullOrEmpty(assignment[12])? null: assignment[12].toString());
						classAssignmentDetailsVO.setStudyClassId(ObjectFunctions.isNullOrEmpty(assignment[2])? 0: Long.valueOf(assignment[2].toString()));
						classAssignmentDetailsVO.setClassAndSection(ObjectFunctions.isNullOrEmpty(assignment[5])? "": assignment[5].toString());
						classAssignmentDetailsVO.setSubjectId(ObjectFunctions.isNullOrEmpty(assignment[8])? 0: Long.valueOf(assignment[8].toString()));
						classAssignmentDetailsVO.setSubjectName(ObjectFunctions.isNullOrEmpty(assignment[7])? "": assignment[7].toString());
						if((!ObjectFunctions.isNullOrEmpty(assignment[11]) )&& (!ObjectFunctions.isNullOrEmpty(assignment[9]) ) &&(!ObjectFunctions.isNullOrEmpty(assignment[10])) )
						{
							// Attachments
							attachmentVO = new AttachmentVO();
							attachmentVO.setId(ObjectFunctions.isNullOrEmpty(assignment[11])? 0: Long.valueOf(assignment[11].toString()));
							attachmentVO.setFilePath(ObjectFunctions.isNullOrEmpty(assignment[9])? "": assignment[9].toString());
							attachmentVO.setFileName(ObjectFunctions.isNullOrEmpty(assignment[10])? "": assignment[10].toString());
							attachmentVOList.add(attachmentVO);
							classAssignmentDetailsVO.setAttachments(attachmentVOList);
							attachmentVO = null;
						}
						if(size == assignmentsList.size()-1){
							assignmentId = ObjectFunctions.isNullOrEmpty(assignment[0])? 0: Long.valueOf(assignment[0].toString());
							viewClassAssignmentDetailsVOList.add(classAssignmentDetailsVO);
							classAssignmentDetailsVO = null;
							
						}
						size++;
					}
					viewClassAssignmentDetailsMainVO = new ViewClassAssignmentDetailsMainVO();
					viewClassAssignmentDetailsMainVO.setClassAssignmentVO(viewClassAssignmentDetailsVOList);
					return viewClassAssignmentDetailsMainVO;
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public EventMainVO getEventDetails(long accountId, String roletype)
	{
		try { 
			EventsVO eventsVO = null;
			EventMainVO eventMainVO=null;
			List<Events> eventList = null;
			List<EventsVO> eventsVOList = new ArrayList<EventsVO>();
			if(accountId > 0){
				User curUser= (User)this.get(User.class,accountId);
				if(!ObjectFunctions.isNullOrEmpty(curUser))
				{
					AcademicYear academicYear  = super.getCurrentAcademicYear("Y",curUser.getCustId());
					if (!ObjectFunctions.isNullOrEmpty(academicYear)) 
					{
						//List<Events> eventList = this.getAll(Events.class, "academicYearId = "+ accountId +" ORDER BY startDate DESC");
						
						if(roletype.equalsIgnoreCase("A")){ //ADMIN
							eventList = this.getAllHqlQuery("FROM Events c WHERE c.academicYear = "+ academicYear.getId()+" ORDER BY c.startDate");
						}
						else if(roletype.equalsIgnoreCase("S"))
						{// STAFF
							//studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.id IN (SELECT ct.studyClass FROM ClassTeacher ct WHERE ct.academicYear = "+ academicYear.getId() +" AND ct.staff in (select s.id from Staff s where s.account= "+ accountId +"))  ORDER BY c.classNameClassId");
							List<ViewStaffSubjectsDetails> staffClassList = null;
							String studyClassIds=null;
								List<BigInteger> staffStudyClassIds =  this.getAll("select studyClassId from vw_staffSubjectsDetails where custId ="+curUser.getCustId()+" and academicYearId="+academicYear.getId()+" and accountId="+accountId+" group by studyClassId");
								if (ObjectFunctions.isNotNullOrEmpty(staffStudyClassIds)) 
									studyClassIds = StringFunctions.convertListToCommaDelimitedString(staffStudyClassIds);
								else
									studyClassIds="0";
								eventList = this.eventsforStaffStudents(studyClassIds,curUser.getRoleId(),curUser.getCustId(),academicYear.getId(),null);
								ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = (ViewStaffPersonAccountDetails)this.get(ViewStaffPersonAccountDetails.class,"accountId="+curUser.getId());
								if(!ObjectFunctions.isNullOrEmpty(viewStaffPersonAccountDetails))
								{
									List<Events> eventsList = null;
									if(Constants.SCHOOL_TEACHER.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()) || Constants.SCHOOL_ASST_STAFF.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()) || Constants.SCHOOL_HOD.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()))
										eventsList = this.getAll(Events.class,"(staffEvent='T' OR staffEvent='A') and academicYearId = "+ academicYear.getId() +" ORDER BY startDate");
									else
										eventsList = this.getAll(Events.class," (staffEvent='N' OR staffEvent='A') and academicYearId = "+ academicYear.getId() +" ORDER BY startDate");
									
									if(!ObjectFunctions.isNullOrEmpty(eventsList))
									{
										if(ObjectFunctions.isNullOrEmpty(eventList))
											eventList = new ArrayList<Events>();
										
										eventList.addAll(eventsList);
									}
									eventsList = null;
								}
								viewStaffPersonAccountDetails = null;
								
						}else if(roletype.equalsIgnoreCase("P")){ // PARENT
							//List<StudyClass> studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.id IN (SELECT s.classSection FROM Student s WHERE s.status = 'Y' AND s.account in (select u.id from User u where u.studentParent.parentAccountId= "+ accountId +")) ORDER BY c.classNameClassId");
							List<String> studyClassIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM vw_studentClassDetails s WHERE s.parentId="+accountId);
							if(!ObjectFunctions.isNullOrEmpty(studyClassIds))
							{
								/*StringBuffer studyClassIds= new StringBuffer();
								for(StudyClass studyClass : studyClassList)
								{
									studyClassIds.append(studyClass.getId()+",");
								}
								studyClassIds.append("0");*/
								
								eventList = this.eventsforStaffStudents(studyClassIds.get(0).toString(),0,curUser.getCustId(),academicYear.getId(),null);
								
							}
						}else if(roletype.equalsIgnoreCase("C")){ // STUDENT
							List<StudyClass> studyClassList = this.getAllHqlQuery("FROM StudyClass c WHERE c.id IN (SELECT s.classSection FROM Student s WHERE s.academicYear = "+ academicYear.getId() +" AND s.account = "+ accountId +") ORDER BY c.classNameClassId");
							if(!ObjectFunctions.isNullOrEmpty(studyClassList))
							{
								StringBuffer studyClassIds= new StringBuffer();
								for(StudyClass studyClass : studyClassList)
								{
									studyClassIds.append(studyClass.getId()+",");
								}
								studyClassIds.append("0");
								
								eventList = this.eventsforStaffStudents(studyClassIds.toString(),0,curUser.getCustId(),academicYear.getId(),null);
								
							}
						}
						if(!ObjectFunctions.isNullOrEmpty(eventList)){
							//JSONArray eventsArray = new JSONArray();
							
							for(Events events : eventList) 
							{
								eventsVO = new EventsVO();
								eventsVO = events.copyFromEntityToVo(events);
								//eventsVO.setId(events.getId());
								//eventsVO.setStartDate(ObjectFunctions.isNullOrEmpty(events.getStartDate())? "": DateFunctions.convertDateToString(events.getStartDate()));
								//eventsVO.setEndDate(ObjectFunctions.isNullOrEmpty(events.getEndDate())? "": DateFunctions.convertDateToString(events.getEndDate()));
								/*eventsVO.setEventName(events.getEventName());
								eventsVO.setEventDescription(ObjectFunctions.isNullOrEmpty(events.getEventDescription())? "": events.getEventDescription());
								eventsVO.setStartTime(ObjectFunctions.isNullOrEmpty(events.getStartTime())? "": events.getStartTime());
								eventsVO.setEndTime(ObjectFunctions.isNullOrEmpty(events.getEndTime())? "": events.getEndTime());*/
								StringBuffer studyClassIds= new StringBuffer();
								if("cs".equalsIgnoreCase(events.getEventFor()))
								{
									if(!ObjectFunctions.isNullOrEmpty(events.getStudyClass()))
									{
										for(StudyClass studyClass : events.getStudyClass())
										{
											studyClassIds.append(studyClass.getId()+",");
										}
										studyClassIds.append("0");
									}
									
									eventsVO.setStudyClassIds(studyClassIds.toString());
								}
								
								List<EventsAlbum> eventsAlbumList = this.getAll(EventsAlbum.class, "custId="+events.getCustId()+" and academicYearId="+events.getAcademicYear().getId()+" and eventId="+events.getId());
								if(!ObjectFunctions.isNullOrEmpty(eventsAlbumList))
								{
									for(EventsAlbum eventsAlbum : eventsAlbumList)
									{
										Set<AlbumAttachment> albumAttachmentSet = eventsAlbum.getAlbumAttachment();
										if(!ObjectFunctions.isNullOrEmpty(eventsAlbumList))
										{
											for(AlbumAttachment albumAttachment : albumAttachmentSet)
											{
												AlbumAttachmentVO albumAttachmentVo = new AlbumAttachmentVO();
												albumAttachmentVo.setId(albumAttachment.getId());
												//albumAttachmentVo.setFileName(albumAttachment.getFileName());
												albumAttachmentVo.setFilePath(albumAttachment.getFilePath());
												eventsVO.getAlbumAttachmentVo().add(albumAttachmentVo);
											}
										}
									}
								}
								eventsVOList.add(eventsVO);
								eventsVO = null;
								eventsAlbumList = null;
							}
							eventMainVO = new EventMainVO();
							eventMainVO.setEventsVoList(eventsVOList);
							return eventMainVO;
						}else{
							return null;
						}
					}
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}

	public ViewStudentFeePaymentDetailsMainVO getFeeDetails(long academicYearId){
		try { 
			ViewStudentFeePaymentDetailsVO studentFeePaymentDetailsVO = null;
			ViewStudentFeePaymentDetailsMainVO studentFeePaymentDetailsMainVO=null;
			List<ViewStudentFeePaymentDetailsVO> studentFeePaymentDetailsVOList = new ArrayList<ViewStudentFeePaymentDetailsVO>();
			if(academicYearId > 0){
				//List<Object[]> feeDetails = this.getAll("SELECT t.studentId, SUM(t.feeAmount), SUM(t.paidAmount), SUM(t.discountAmount), SUM(t.concessionAmount) FROM (SELECT studentId, feeAmount, SUM(paymentAmount) as paidAmount, SUM(discountAmount) as discountAmount, SUM(paymentConcessionAmount) as concessionAmount FROM vw_studentFeePaymentDetails WHERE academicYearId= "+ academicYearId +" GROUP BY feeId, studentId)as t GROUP BY studentId");
				List<Object[]> feeDetails = this.getAll("select u.studentId, SUM(u.feeAmount), SUM(u.paidAmount), SUM(u.discountAmount), SUM(u.concessionAmount) FROM (SELECT f.studentId, SUM(f.feeAmount) as feeAmount, SUM(f.paidAmount) as paidAmount, SUM(f.discountAmount) discountAmount, SUM(f.concessionAmount) concessionAmount FROM (SELECT studentId, feeAmount, SUM(paymentAmount) as paidAmount, SUM(discountAmount) as discountAmount, SUM(paymentConcessionAmount) as concessionAmount FROM vw_studentFeePaymentDetails WHERE academicYearId= "+ academicYearId +" AND deleteStatus='N' GROUP BY feeId, studentId)as f GROUP BY f.studentId "
						+ "UNION "
						+ "SELECT t.studentId, SUM(t.feeAmount), SUM(t.paidAmount), SUM(t.discountAmount), SUM(t.concessionAmount) FROM (SELECT studentId, feeAmount, SUM(paymentAmount) as paidAmount, SUM(discountAmount) as discountAmount, SUM(paymentConcessionAmount) as concessionAmount FROM vw_studentTransportFeePaymentDetails WHERE academicYearId= "+ academicYearId +" AND deleteStatus='N' GROUP BY transportFeeId, studentId)as t GROUP BY t.studentId ) u GROUP BY u.studentId");
				
				if(!ObjectFunctions.isNullOrEmpty(feeDetails)) {
					JSONArray feeArray = new JSONArray();
					for(Object[] fee : feeDetails) {
						studentFeePaymentDetailsVO = new ViewStudentFeePaymentDetailsVO();
						studentFeePaymentDetailsVO.setStudentId(ObjectFunctions.isNullOrEmpty(fee[0])?0: Long.valueOf(fee[0].toString()));
						studentFeePaymentDetailsVO.setFeeAmount(Double.valueOf(fee[1].toString()));
						studentFeePaymentDetailsVO.setPaidAmount(Double.valueOf(fee[2].toString()));
						studentFeePaymentDetailsVO.setDiscountAmount(Double.valueOf(fee[3].toString()));
						studentFeePaymentDetailsVO.setConcessionAmount(Double.valueOf(fee[4].toString()));
						if(Double.valueOf(fee[1].toString()) > (Double.valueOf(fee[2].toString())+Double.valueOf(fee[3].toString())))
							studentFeePaymentDetailsVO.setBalanceAmount(Double.valueOf(fee[1].toString())-Double.valueOf(fee[2].toString())-Double.valueOf(fee[3].toString())-Double.valueOf(fee[4].toString()));
						else studentFeePaymentDetailsVO.setBalanceAmount(0.0);
						
						studentFeePaymentDetailsVOList.add(studentFeePaymentDetailsVO);											 
						studentFeePaymentDetailsVO = null;
					}
					studentFeePaymentDetailsMainVO = new ViewStudentFeePaymentDetailsMainVO();
					studentFeePaymentDetailsMainVO.setViewStudentFeePaymentDetailsVOList(studentFeePaymentDetailsVOList);
					return studentFeePaymentDetailsMainVO;
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public StudentMonthlyAttendanceMainVO getMonthlyAttendance(long academicYearId,long accountId,String type,String standardType){
		StudentMonthlyAttendanceMainVO studentMonthlyAttendanceMainVO = new StudentMonthlyAttendanceMainVO();
		List<StudentMonthlyAttendanceVO> studentMonthlyAttendanceVOs = new ArrayList<StudentMonthlyAttendanceVO>();
		StudentMonthlyAttendanceVO studentMonthlyAttendanceVO = null;
		try {
			List<Object[]> stuAttdList = null;
			if("P".equalsIgnoreCase(standardType)){
				stuAttdList = adminDao.getPreSchoolStudentAttendanceSummaryDetails(academicYearId, accountId, type);
			}else{
				stuAttdList = adminDao.getStudentAttendanceSummaryDetails(academicYearId, accountId, type);
			}
			if (!ObjectFunctions.isNullOrEmpty(stuAttdList)) {
				for(Object[] object : stuAttdList) {
					studentMonthlyAttendanceVO = new StudentMonthlyAttendanceVO();
					studentMonthlyAttendanceVO.setStudentId(Long.valueOf(object[1].toString()));
					studentMonthlyAttendanceVO.setMonthName(object[2].toString());
					studentMonthlyAttendanceVO.setTotalWorkingDays(object[3].toString());
					studentMonthlyAttendanceVO.setPresentiesCount(object[4].toString());
					studentMonthlyAttendanceVO.setAbsentiesCount(object[5].toString());
					studentMonthlyAttendanceVO.setPercentage(object[6].toString());
					studentMonthlyAttendanceVOs.add(studentMonthlyAttendanceVO);
					studentMonthlyAttendanceVO = null;
				}
				studentMonthlyAttendanceMainVO.setStudentMonthlyAttendanceVOs(studentMonthlyAttendanceVOs);
				studentMonthlyAttendanceVOs =null;
				return studentMonthlyAttendanceMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public StudentDailyAttendanceMainVO getDailyAttendanceByStudyClassId(long academicYearId,long studyClassId){
		StudentDailyAttendanceMainVO studentDailyAttendanceMainVO = new StudentDailyAttendanceMainVO();
		List<StudentDailyAttendanceVO> studentDailyAttendanceVOs = new ArrayList<StudentDailyAttendanceVO>();
		StudentDailyAttendanceVO dailyAttendanceVO = null;
		try {
			List<Object[]> studentAttList = this.getAll("SELECT st.present,st.afternoonSession,st.studentId FROM studentDailyAttendance st LEFT JOIN student s ON (st.studentId = s.id) WHERE st.academicYearId="+ academicYearId +" AND s.classSectionId="+ studyClassId +" and DATE(st.attendanceDate) = DATE(CURDATE())");
			if (!ObjectFunctions.isNullOrEmpty(studentAttList)) {
				for(Object[] object : studentAttList) {
					dailyAttendanceVO = new StudentDailyAttendanceVO();
					dailyAttendanceVO.setMorningSession(!ObjectFunctions.isNullOrEmpty(object[0])?("N".equalsIgnoreCase(object[0].toString())?"A":"P"):null);
					dailyAttendanceVO.setAfternoonSession(!ObjectFunctions.isNullOrEmpty(object[1])?("N".equalsIgnoreCase(object[1].toString())?"A":"P"):null);
					dailyAttendanceVO.setStudentId(Long.valueOf(object[2].toString()));
					studentDailyAttendanceVOs.add(dailyAttendanceVO);
					dailyAttendanceVO = null;
				}
				studentDailyAttendanceMainVO.setStudentDailyAttendance(studentDailyAttendanceVOs);
				studentDailyAttendanceVOs=null;
				return studentDailyAttendanceMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public StudentDailyAttendanceMainVO getAttendanceByParentAccountId(long parentAccountId){
		StudentDailyAttendanceMainVO studentDailyAttendanceMainVO = new StudentDailyAttendanceMainVO();
		List<StudentDailyAttendanceVO> studentDailyAttendanceVOs = new ArrayList<StudentDailyAttendanceVO>();
		StudentDailyAttendanceVO dailyAttendanceVO =  new StudentDailyAttendanceVO();;
		try {
			//List<Object[]> studentAttList = this.getAll("SELECT st.present,st.studentId FROM studentDailyAttendance st LEFT JOIN student s ON (st.studentId = s.id) LEFT JOIN Account a ON(a.id = s.accountId) WHERE a.parentId = "+ parentAccountId +" and DATE(st.attendanceDate) = DATE(CURDATE())");
			List<Object[]> studentAttList = this.getAll("SELECT st.present,st.afternoonSession,st.studentId FROM studentDailyAttendance st LEFT JOIN student s ON (st.studentId = s.id) LEFT JOIN Account a ON(a.id = s.accountId) LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+ parentAccountId +" and DATE(st.attendanceDate) = DATE(CURDATE())");
			if (!ObjectFunctions.isNullOrEmpty(studentAttList)) {
				for(Object[] object : studentAttList) {
					dailyAttendanceVO = new StudentDailyAttendanceVO();
					dailyAttendanceVO.setMorningSession(!ObjectFunctions.isNullOrEmpty(object[0])?("N".equalsIgnoreCase(object[0].toString())?"A":"P"):null);
					dailyAttendanceVO.setAfternoonSession(!ObjectFunctions.isNullOrEmpty(object[1])?("N".equalsIgnoreCase(object[1].toString())?"A":"P"):null);
					dailyAttendanceVO.setStudentId(Long.valueOf(object[2].toString()));
					studentDailyAttendanceVOs.add(dailyAttendanceVO);
					dailyAttendanceVO = null;
				}				
			}else{
				dailyAttendanceVO.setMorningSession("P");
				dailyAttendanceVO.setAfternoonSession("P");
				dailyAttendanceVO.setStudentId(0L);
				studentDailyAttendanceVOs.add(dailyAttendanceVO);
				dailyAttendanceVO = null;
			}
			
			
			studentDailyAttendanceMainVO.setStudentDailyAttendance(studentDailyAttendanceVOs);
			studentDailyAttendanceVOs=null;
			return studentDailyAttendanceMainVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public ExamSchedulesMainVO getExamSchedules(long academicYearId,long accountId, String type){
		try {
			StringBuilder query =null;
			ExamSchedulesMainVO examSchedulesMainVO =null;
			ViewStaffPersonAccountDetailsVO staffPersonAccountDetailsVO =null;
			List<ExamSchedulesVO> examSchedulesVOList = new ArrayList<ExamSchedulesVO>();
			ExamSchedulesVO examSchedulesVO = null;
			ExamTypeVO examTypeVO=null;
			StudyClassVO studyClassVO=null;
			ClassSectionSubjectVO classSectionSubjectVO=null;
			SubTypeVO subTypeVO=null;
			if(academicYearId > 0){
				List<Object[]> examSchedules = null;
				//adminManager.getAllHqlQuery("FROM ExamSchedules
				if(StringFunctions.isNullOrEmpty(type)){
					return null;
				}else{
					query =new StringBuilder("select scheduleId,IFNULL(startDate,'') as startDate,IFNULL(startTime,'') as startTime,IFNULL(endDate,'') as endDate,IFNULL(endTime,'') as endTime,IFNULL(examDate,'') as examDate,scheduleMaxMarks,scheduled,eid,maxMarks,minMarks,examType,examTypeSortingOrder,subTypeId,subTypeName,subTypeSortingOrder,classSectionId,classSubjectId from vw_classExamDetails where ");
					if(accountId > 0){
						if(type.equalsIgnoreCase("S")){ // Staff
							query.append("classSectionId in (SELECT studyClassId FROM classTeacher WHERE academicYearId = "+ academicYearId +" AND teacherId in (select id from staff where accountId = "+ accountId +"))");
							//examSchedules = this.getAllHqlQuery("FROM ExamSchedules es WHERE es.classSection IN (SELECT ct.studyClass FROM ClassTeacher ct WHERE ct.academicYear = "+ academicYearId +" AND ct.staff in (select s.id from Staff s where s.account = "+ accountId +"))");
						}else if(type.equalsIgnoreCase("P")){ // Parent
							query.append("classSectionId in (SELECT classSectionId FROM student WHERE academicYearId = "+ academicYearId +" AND accountId in (select id from Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+accountId +"))");
							//examSchedules = this.getAllHqlQuery("FROM ExamSchedules es WHERE es.classSection IN (SELECT s.classSection FROM Student s WHERE s.academicYear = "+ academicYearId +" AND s.account in (select u.id from User u where u.studentParent.parentAccountId= "+ accountId +"))");
						}else if(type.equalsIgnoreCase("C")){ // Student
							query.append("classSectionId IN (SELECT classSectionId FROM student WHERE academicYearId = "+ academicYearId +" AND accountId = "+ accountId +")");
							//examSchedules = this.getAllHqlQuery("FROM ExamSchedules es WHERE es.classSection IN (SELECT s.classSection FROM Student s WHERE s.academicYear = "+ academicYearId +" AND s.account = "+ accountId +")");
						}
						else{
							query.append(" academicYearId=").append(academicYearId);
							//examSchedules = this.getAllHqlQuery("FROM ExamSchedules es WHERE es.academicYear = "+ academicYearId);
						}
					}
					else{
						query.append(" academicYearId=").append(academicYearId);
						//examSchedules = this.getAllHqlQuery("FROM ExamSchedules es WHERE es.academicYear = "+ academicYearId);
					}
					examSchedules = this.getAll(query.toString());
					if (!ObjectFunctions.isNullOrEmpty(examSchedules)) {
						JSONArray examsArray = new JSONArray();
						for(Object[] exam : examSchedules) {
							examSchedulesVO = new ExamSchedulesVO();						
							examSchedulesVO.setId(Long.valueOf(exam[0].toString()));
							examSchedulesVO.setStartDate(exam[1].toString());
							examSchedulesVO.setStartTime(exam[2].toString());
							examSchedulesVO.setEndDate(exam[3].toString());
							examSchedulesVO.setEndTime(exam[4].toString());
							examSchedulesVO.setExamDate(exam[5].toString());
							examSchedulesVO.setMaxMarks(Double.valueOf(exam[6].toString()));
							examSchedulesVO.setScheduled(Boolean.valueOf(Boolean.valueOf(exam[7].toString())? "Y": "N"));
							// Exam-Type
							if(Long.valueOf(exam[8].toString()) >0){
								examTypeVO = new ExamTypeVO();							
								examTypeVO.setId(Long.valueOf(exam[8].toString()));
								examTypeVO.setMaxMarks(exam[9].toString());
								examTypeVO.setMinMarks(exam[10].toString());
								examTypeVO.setExamType(exam[11].toString());
								examTypeVO.setSortingOrder(Integer.valueOf(exam[12].toString()));
								examSchedulesVO.setExamTypeVO(examTypeVO);
								examTypeVO = null;
							}else
								examSchedulesVO.setExamTypeVO(examTypeVO);
							// Subject
							if(Long.valueOf(exam[17].toString()) >0){
								 classSectionSubjectVO = new ClassSectionSubjectVO();								
								 classSectionSubjectVO.setSubjectId(Long.valueOf(exam[17].toString()));
								 //classSectionSubjectVO.setSubjectName(exam.getClassSectionSubject().getName());							
								 examSchedulesVO.setClassSectionSubjectVO(classSectionSubjectVO);
								classSectionSubjectVO = null;
							}else
								examSchedulesVO.setClassSectionSubjectVO(classSectionSubjectVO);
							// Study-Class
							if(Long.valueOf(exam[16].toString()) >0){
								studyClassVO = new StudyClassVO();							
								studyClassVO.setId(Long.valueOf(exam[16].toString()));
								/*studyClassVO.setClassName(exam.getClassSection().getClassName());
								studyClassVO.setSection(ObjectFunctions.isNullOrEmpty(exam.getClassSection().getSection())? "": exam.getClassSection().getSection());*/
								examSchedulesVO.setStudyClassVO(studyClassVO);
								studyClassVO = null;
							}else
								examSchedulesVO.setStudyClassVO(studyClassVO);
							// Sub-Type
							if(Long.valueOf(exam[13].toString()) >0){
								subTypeVO = new SubTypeVO();							
								subTypeVO.setId(Long.valueOf(exam[13].toString()));
								subTypeVO.setSubTypeName(exam[14].toString());
								subTypeVO.setSortingOrder(Integer.valueOf(exam[15].toString()));
								examSchedulesVO.setSubTypeVO(subTypeVO);
								subTypeVO = null;
							}else
								examSchedulesVO.setSubTypeVO(subTypeVO);
							examSchedulesVOList.add(examSchedulesVO);
						}
						examSchedulesMainVO = new ExamSchedulesMainVO();
						examSchedulesMainVO.setExamSchedulesVOList(examSchedulesVOList);
						examSchedulesVOList=null;
						examSchedulesVO=null;
						return examSchedulesMainVO;
					}else{
						return null;
					}
				}
				
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public SchoolHolidaysMainVO getHolidays(long academicYearId,String classIds,String type){
		SchoolHolidaysMainVO schoolHolidaysMainVO = null;
		List<SchoolHolidaysVO> schoolHolidaysVOList = new ArrayList<SchoolHolidaysVO>();
		SchoolHolidaysVO schoolHolidaysVO = null;
		try { 
			if(academicYearId > 0){
				AcademicYear academicYear = (AcademicYear) this.get(AcademicYear.class, "id = "+ academicYearId +" AND status='"+ Constants.YES_STRING + "'");
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					String classIdWhere = "";
					if ("CH".equalsIgnoreCase(academicYear.getHolidayStatus())) {
						//String classIds = classId.equals("")? "": classId.split("/")[2];
						if(StringFunctions.isNotNullOrEmpty(classIds))
							classIdWhere = " AND classId IN ("+ classIds +") ";
					}
					String holidayStatus = "";
					//String weekHolidays = type.equals("")? "": type.split("/")[2];
					if(StringFunctions.isNotNullOrEmpty(type))
						holidayStatus = " AND status IN ('H','W')";
					else
						holidayStatus = " AND status = 'H'";
					
					List<SchoolHolidays> holidaysList = this.getAll(SchoolHolidays.class, " academicYearId = "+ academicYearId +holidayStatus+ classIdWhere);
					if(!ObjectFunctions.isNullOrEmpty(holidaysList)){
						JSONArray holidaysArray = new JSONArray();
						for(SchoolHolidays holiday : holidaysList) {
							schoolHolidaysVO = new SchoolHolidaysVO();
							schoolHolidaysVO.setId(holiday.getId());
							schoolHolidaysVO.setHolidayDate(ObjectFunctions.isNullOrEmpty(holiday.getHolidayDate())? "": holiday.getHolidayDate());
							schoolHolidaysVO.setStartDate(ObjectFunctions.isNullOrEmpty(holiday.getStartDate())? "": DateFunctions.convertDateToString(holiday.getStartDate()));
							schoolHolidaysVO.setEndDate(ObjectFunctions.isNullOrEmpty(holiday.getEndDate())? "": DateFunctions.convertDateToString(holiday.getEndDate()));
							schoolHolidaysVO.setHolidayDescription(ObjectFunctions.isNullOrEmpty(holiday.getDescription())? "": holiday.getDescription());
							schoolHolidaysVO.setNoOfDays(holiday.getNoOfDays());
							schoolHolidaysVO.setMonthId(holiday.getMonthId());
							schoolHolidaysVO.setYearId(ObjectFunctions.isNullOrEmpty(holiday.getYearId())? "": holiday.getYearId());
							schoolHolidaysVO.setClassId(holiday.getClassId());
							schoolHolidaysVO.setStatus(holiday.getStatus());
							schoolHolidaysVOList.add(schoolHolidaysVO);
							schoolHolidaysVO = null;
						}
						holidaysList=null;
						schoolHolidaysMainVO = new SchoolHolidaysMainVO();
						schoolHolidaysMainVO.setSchoolHolidaysVOList(schoolHolidaysVOList);
						schoolHolidaysVOList=null;
						return schoolHolidaysMainVO;
					}else{
						return null;
					}
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public boolean submitStudentMarks(StudentMarksMainVO studentMarksMainVO){
		StudentMarks studentMarks=null;
		try {
			if (!ObjectFunctions.isNullOrEmpty(studentMarksMainVO)) {
				Student student =null;
				HashMap<Integer, String> response = new HashMap<Integer, String>();
				long accountId = studentMarksMainVO.getIdentifier().getAccountId();
				List<String> classAndExamType = new ArrayList<String>();
				HashMap<Long, ExamSchedules> schedulesMap = new HashMap<Long, ExamSchedules>();
				long custId = 0;
				if (!ObjectFunctions.isNullOrEmpty(studentMarksMainVO.getStudentMarksVOList())) {
					for(StudentMarksVO studentMarksVO:studentMarksMainVO.getStudentMarksVOList()){
						StudentMarksVO studentMark=(StudentMarksVO)studentMarksVO;
						long studentId = studentMark.getStudentId();
						long examScheduleId =  studentMark.getScheduleId();
						double obtainedMarks = studentMark.getObtainedMarks();
						String isPresent = studentMark.getIsPresent();
						
						student =(Student) this.get(Student.class,"id = "+ studentId);
						if(!ObjectFunctions.isNullOrEmpty(student)){
							student = this.upadateStudentMarksFromSMSAppAndWeb(student, examScheduleId, obtainedMarks, accountId, new String[]{"0"}, isPresent, response,schedulesMap);
							this.merge(student);
							custId = student.getCustId();
							//Commented by Siva for not calculating subject position below
							/*if(response.get(0).equalsIgnoreCase("true"))
								if(!classAndExamType.contains(student.getClassSectionId()+"_"+response.get(1)))
									classAndExamType.add(student.getClassSectionId()+"_"+response.get(1));*/
						}
				     }
					if(custId > 0)
					{
						//Sending Notification to mobile for student marks addition
						//this.sendNotificationForStudentMarks(custId);
						StudentMarksNotificationThread R1 = new StudentMarksNotificationThread(custId);
					    R1.start();
					}
						
					student = null;
					return true;
					/*if(classAndExamType.size()>0){
						for(String item : classAndExamType)
							calculateStudentsSubjectPoistionByExamScheule(Long.valueOf(item.split("_")[0]), Long.valueOf(item.split("_")[1]));
						return true;
					}else{
						return false;
					}*/
				}else{
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return false;
	}
	/*public void calculateStudentsSubjectPoistionByExamScheule(long classSectionId,long examTypeId){
		try{
			 //ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) springContextAware.getBean("taskExecutor");
			 ProcessStudentsSubjectwisePosition processStudentMarks = new ProcessStudentsSubjectwisePosition();
			 processStudentMarks.setClassSectionId(classSectionId);
			 processStudentMarks.setExamTypeId(examTypeId);
			 taskExecutor.execute(processStudentMarks);
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}*/
	public LeavesMainVO getLeaves(String type, long accountId){
		try { 
			LeavesVO leavesVO =null;
			LeavesMainVO leavesMainVO=null;
			List<LeavesVO> leavesVOList = new ArrayList<LeavesVO>();
				if(accountId > 0 && !StringFunctions.isNullOrEmpty(type)){
					User curUser= (User)this.get(User.class,accountId);
					if(!ObjectFunctions.isNullOrEmpty(curUser))
					{
						AcademicYear academicYear  = null;
						List<Leave> leavesList = null;
							// A - Admin & S - Staff & C - Student & P - Parent
							if(!type.equalsIgnoreCase("P"))
								academicYear=super.getCurrentAcademicYear("Y",curUser.getCustId());
							if(type.equalsIgnoreCase("A"))
								leavesList = this.getAll(Leave.class,"academicYearId = "+ academicYear.getId()+" and (accountId="+accountId+" OR leaveStatus='P')");
							else if(type.equalsIgnoreCase("S"))
								leavesList = this.getAll(Leave.class,"academicYearId = "+ academicYear.getId()+ " AND (accountId = "+ accountId+" OR (supervisorId="+accountId+" AND leaveStatus='P'))");
							else if(type.equalsIgnoreCase("C"))
								  leavesList = this.getAll(Leave.class,"academicYearId = "+ academicYear.getId() + " AND accountId = "+ accountId);
							else if(type.equalsIgnoreCase("P")){
								List<String> custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+accountId);
								List<String> academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
								List<String> studentIds = this.getAll("SELECT GROUP_CONCAT(s.accountId) FROM student s WHERE s.academicYearId IN("+academicYears.get(0)+") AND s.accountId IN(SELECT a.id FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+accountId+")");
								leavesList = this.getAll(Leave.class,"academicYearId IN( "+ academicYears.get(0) + ") AND accountId in ("+ studentIds.get(0)+")");
							}
							else return null;
						
							//List<Leave> leavesList = this.getAll(Leave.class,"academicYearId = "+ academicYearId + " AND accountId = "+ accountId);
							if (!ObjectFunctions.isNullOrEmpty(leavesList)) {
								for(Leave leave : leavesList) {
									leavesVO = new LeavesVO();
									leavesVO.setId(leave.getId());
									leavesVO.setAccountId(ObjectFunctions.isNullOrEmpty(leave.getAccountId())? 0: leave.getAccountId());
									leavesVO.setAppliedBy(ObjectFunctions.isNullOrEmpty(leave.getUser().getFullPersonName())? "": leave.getUser().getFullPersonName());
									leavesVO.setApprovalsComment(ObjectFunctions.isNullOrEmpty(leave.getApprovalsComment())? "": leave.getApprovalsComment());
									leavesVO.setDescription(ObjectFunctions.isNullOrEmpty(leave.getDescription())? "": leave.getDescription());
									leavesVO.setEndDate(ObjectFunctions.isNullOrEmpty(leave.getEndDate())? "": DateFunctions.convertDateToString(leave.getEndDate()));
									leavesVO.setLeavesCount(ObjectFunctions.isNullOrEmpty(leave.getLeavesCount())? 0: leave.getLeavesCount());
									leavesVO.setLeaveStatus(ObjectFunctions.isNullOrEmpty(leave.getLeaveStatus())? "": leave.getLeaveStatus());
									leavesVO.setLeaveType(ObjectFunctions.isNullOrEmpty(leave.getLeaveType())? "": leave.getLeaveType());
									leavesVO.setStartDate(ObjectFunctions.isNullOrEmpty(leave.getStartDate())? "": DateFunctions.convertDateToString(leave.getStartDate()));
									leavesVO.setSupervisorId(ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())? 0: leave.getSupervisorId());
									User user = (User) this.get(User.class, ObjectFunctions.isNullOrEmpty(leave.getSupervisorId())? 0: leave.getSupervisorId());
									leavesVO.setSupervisor(ObjectFunctions.isNullOrEmpty(user)? "": user.getFullPersonName());
									leavesVO.setHalfDayLeave(leave.isHalfDay());
									leavesVO.setLeaveSessionType(ObjectFunctions.isNullOrEmpty(leave.getSessionType())? "": leave.getSessionType());
									user = null;
									leavesVOList.add(leavesVO);
									leavesVO = null;
								}
								leavesMainVO = new LeavesMainVO();
								leavesMainVO.setLeavesList(leavesVOList);
								leavesVOList=null;
								return leavesMainVO;
							}
					}
					else{
						return null;
					}
				}
			else{
				return null;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return null;
		}
		return null;
	}
	public boolean androidRegistration(AndroidMobileUsersVO androidMobileUsersVO){
		try {
			if(StringFunctions.isNotNullOrEmpty(androidMobileUsersVO.getRegistrationKey())){
				long custId = androidMobileUsersVO.getIdentifier().getCustId();
				long accountId = androidMobileUsersVO.getIdentifier().getAccountId();
				//AndroidMobileUsers androidMobileUsers = (AndroidMobileUsers) this.get(AndroidMobileUsers.class,"registrationKey='"+androidMobileUsersVO.getRegistrationKey()+"' and accountId="+accountId);
				AndroidMobileUsers androidMobileUsers = (AndroidMobileUsers) this.get(AndroidMobileUsers.class,"registrationKey='"+androidMobileUsersVO.getRegistrationKey()+"'");
				if(ObjectFunctions.isNullOrEmpty(androidMobileUsers)){
					androidMobileUsers= new AndroidMobileUsers();
				}
				androidMobileUsers.setAccountId(Long.valueOf(accountId));
				androidMobileUsers.setCustId(Long.valueOf(custId));
				androidMobileUsers.setRegistrationKey(androidMobileUsersVO.getRegistrationKey());
				this.save(androidMobileUsers);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return true;
	}
	public MessagesMainVO getMessages(long academicYearId,long accountId){
		ViewCircularUsersVO circularUsersVO = null;
		MessagesVO messageVO = null;
		ScrapMessageVO scrapMessageVO = null;
		List<ViewCircularUsersVO> circularUsersVOs = new ArrayList<ViewCircularUsersVO>();
		List<MessagesVO> messageVOs = new ArrayList<MessagesVO>();
		List<ScrapMessageVO> scrapMessageVOs = new ArrayList<ScrapMessageVO>();
		List<ReplyScrapMessageVO> replyScrapMessageVOs = new ArrayList<ReplyScrapMessageVO>();
		ReplyScrapMessageVO replyscrapMessageVO = null;
		try {
			MessagesMainVO  messagesMainVO  = new MessagesMainVO();
			// Circular Messages
			User account = (User) this.get(User.class, accountId);
			if(!ObjectFunctions.isNullOrEmpty(account)){
				List<Object[]> circularUsersList =null;
			    if(account.isSchoolAdmin()){
			    	log.debug("Query One"+ " SELECT circularDescription, circularDate,IFNULL(userId,0) AS userId FROM vw_circularusers WHERE academicYearId="+ academicYearId+" GROUP BY circularDescription, circularDate ORDER BY circularDate DESC");
			    	circularUsersList = this.getAll("SELECT circularDescription, circularDate,IFNULL(userId,0) AS userId FROM vw_circularusers WHERE academicYearId="+ academicYearId+" GROUP BY circularDescription, circularDate ORDER BY circularDate DESC ");
			    }else{
			    	StringBuilder circularInfo=new StringBuilder();
			    	if(account.isParent()){
						circularInfo.append("circularStatus='AP'");
					}else if (account.isSchoolStudent()) {
						circularInfo.append("circularStatus='AC'");
					}else if(account.getRoleId() > 0){
						//2,8,12,31,35 Teaching Staff 
						// If anyone change the roles please update whereever we are using the roleId or roleName like this
						if(account.getRoleId()==2 || account.getRoleId()==8  || account.getRoleId()==12 || account.getRoleId()==31 || account.getRoleId()==35 ){
							circularInfo.append("circularStatus='AS'");
						}else{
							circularInfo.append("circularStatus='AN'");
						}
					}
				    log.debug("Query Two"+ " SELECT circularDescription, circularDate,IFNULL(userId,0) AS userId FROM vw_circularusers WHERE academicYearId="+ academicYearId +" AND (userId="+ accountId + " or "+circularInfo.toString()+" or circularStatus='AA') GROUP BY circularDescription, circularDate ORDER BY circularDate DESC ");
				    circularUsersList = this.getAll("SELECT circularDescription, circularDate,IFNULL(userId,0) AS userId FROM vw_circularusers WHERE academicYearId="+ academicYearId +" AND (userId="+ accountId + " or "+circularInfo.toString()+" or circularStatus='AA') GROUP BY circularDescription, circularDate ORDER BY circularDate DESC ");
			    }
				if (!ObjectFunctions.isNullOrEmpty(circularUsersList)) {
					for(Object[] object : circularUsersList) {
						circularUsersVO = new ViewCircularUsersVO();
						circularUsersVO.setCircularDescription(object[0].toString());
						circularUsersVO.setCircularDate(String.valueOf(object[1].toString()));
						circularUsersVO.setUserId(Long.valueOf(object[2].toString()));
						circularUsersVOs.add(circularUsersVO);
						circularUsersVO = null;
					}
					messagesMainVO.setCircularMessagesVOs(circularUsersVOs);
					circularUsersVOs = null;
					circularUsersList = null;
				}
			}
			// Dash-board messages
			List<Messages>  dashboardMessageList = this.getAll(Messages.class, "academicYearId="+ academicYearId +" and receiverType='ST' and messageType='B' ");
			if (!ObjectFunctions.isNullOrEmpty(dashboardMessageList)) {
				for(Messages message : dashboardMessageList) {
					messageVO = new MessagesVO();
					messageVO.setId(message.getId());
					messageVO.setTitle(ObjectFunctions.isNullOrEmpty(message.getTitle())? "": message.getTitle());
					messageVO.setStatus(ObjectFunctions.isNullOrEmpty(message.getStatus())? "": message.getStatus());
					messageVO.setPurposeType(ObjectFunctions.isNullOrEmpty(message.getPurposeType())? "": message.getPurposeType());
					messageVO.setSenderName( ObjectFunctions.isNullOrEmpty(message.getSenderName())? "": message.getSenderName());
					messageVO.setMessageDescription(message.getMessageDescription());
					messageVO.setMessageDate(String.valueOf(message.getMessageDate()));
					messageVO.setChannel(message.getChannel());
					messageVOs.add(messageVO);
					messageVO = null;
				}
				messagesMainVO.setMessagesVOs(messageVOs);
				messageVOs = null;
				dashboardMessageList = null;
			}
			int monthNum;
			// General Messages
			List<Object[]> generalMessageList = this.getAll("SELECT sm.id, sm.scrapDescription, sm.title, sm.status, sm.messageType, sm.scrapDate, sm.isNewMessage, sm.receiverAccountId, sm.senderAccountId, p.firstName, p.lastName FROM scrapMessage sm LEFT JOIN Account a ON (a.id = sm.senderAccountId) LEFT JOIN Person p ON (p.id=a.personId) WHERE (sm.receiverAccountId="+ accountId +"  OR sm.senderAccountId ="+ accountId+")");
			if (!ObjectFunctions.isNullOrEmpty(generalMessageList)) {
				for(Object[] scrapMessage: generalMessageList) {
					scrapMessageVO = new ScrapMessageVO();
					scrapMessageVO.setId(Long.valueOf(scrapMessage[0].toString()));
					scrapMessageVO.setScrapDescription(scrapMessage[1].toString());
					scrapMessageVO.setTitle(ObjectFunctions.isNullOrEmpty(scrapMessage[2])? "": scrapMessage[2].toString());
					scrapMessageVO.setStatus(scrapMessage[3].toString());
					scrapMessageVO.setMessageType(ObjectFunctions.isNullOrEmpty(scrapMessage[4])? "": scrapMessage[4].toString());
					scrapMessageVO.setScrapDate(ObjectFunctions.isNullOrEmpty(scrapMessage[5])? "": String.valueOf(scrapMessage[5].toString()));
					scrapMessageVO.setIsNewMessage(scrapMessage[6].toString());
					scrapMessageVO.setReceiverAccountId(ObjectFunctions.isNullOrEmpty(scrapMessage[7])? 0: Long.valueOf(scrapMessage[7].toString()));
					scrapMessageVO.setSenderAccountId(ObjectFunctions.isNullOrEmpty(scrapMessage[8])? 0: Long.valueOf(scrapMessage[8].toString()));
					scrapMessageVO.setSenderName((!ObjectFunctions.isNullOrEmpty(scrapMessage[9]) && !ObjectFunctions.isNullOrEmpty(scrapMessage[10]))? scrapMessage[9].toString()+" "+scrapMessage[10].toString(): !ObjectFunctions.isNullOrEmpty(scrapMessage[9])? scrapMessage[9].toString(): "");
					scrapMessageVOs.add(scrapMessageVO);
					scrapMessageVO = null;
				}
				messagesMainVO.setScrapMessageVOs(scrapMessageVOs);
				scrapMessageVOs = null;
				generalMessageList = null;
			}
			// Reply Messages
			List<Object[]> replyMessageList = this.getAll("select rs.id, rs.scrapDescription, rs.title, sm.messageType, rs.scrapDate ,rs.scrapId, rs.receiverAccountId, rs.senderAccountId, p.firstName, p.lastName FROM scrapMessage sm LEFT JOIN replyScrapMessage rs on (rs.custId = sm.custId and rs.academicYearId = sm.academicYearId and rs.scrapId =sm.id) LEFT JOIN Account a ON(a.id = rs.senderAccountId) LEFT JOIN  Person p ON(p.id = a.personId) WHERE (rs.receiverAccountId="+ accountId +"  or rs.senderAccountId = "+ accountId +")");
			if (!ObjectFunctions.isNullOrEmpty(replyMessageList)) {
				for(Object[] replyMessage: replyMessageList) {
					replyscrapMessageVO = new ReplyScrapMessageVO();
					replyscrapMessageVO.setId(Long.valueOf(replyMessage[0].toString()));
					replyscrapMessageVO.setScrapDescription(ObjectFunctions.isNullOrEmpty(replyMessage[1])? "": replyMessage[1].toString());
					replyscrapMessageVO.setTitle(ObjectFunctions.isNullOrEmpty(replyMessage[2])? "": replyMessage[2].toString());
					replyscrapMessageVO.setMessageType(ObjectFunctions.isNullOrEmpty(replyMessage[3])? "": replyMessage[3].toString());
					replyscrapMessageVO.setScrapDate(String.valueOf(replyMessage[4].toString()));
					replyscrapMessageVO.setScrapMesssageId(Long.valueOf(replyMessage[5].toString()));
					replyscrapMessageVO.setReceiverAccount(ObjectFunctions.isNullOrEmpty(replyMessage[6])? 0: Long.valueOf(replyMessage[6].toString()));
					replyscrapMessageVO.setSenderAccount(ObjectFunctions.isNullOrEmpty(replyMessage[7])? 0: Long.valueOf(replyMessage[7].toString()));
					replyscrapMessageVO.setSenderName((!ObjectFunctions.isNullOrEmpty(replyMessage[8]) && !ObjectFunctions.isNullOrEmpty(replyMessage[9]))? replyMessage[8].toString()+" "+replyMessage[9].toString(): !ObjectFunctions.isNullOrEmpty(replyMessage[8])? replyMessage[8].toString(): "");
					replyScrapMessageVOs.add(replyscrapMessageVO);
					replyscrapMessageVO = null;
				}
				messagesMainVO.setReplyScrapMessageVOs(replyScrapMessageVOs);
				replyScrapMessageVOs = null;
				replyMessageList = null;
			}
			if(ObjectFunctions.isNullOrEmpty(messagesMainVO.getCircularMessagesVOs()) && ObjectFunctions.isNullOrEmpty(messagesMainVO.getMessagesVOs()) && ObjectFunctions.isNullOrEmpty(messagesMainVO.getReplyScrapMessageVOs()) && ObjectFunctions.isNullOrEmpty(messagesMainVO.getScrapMessageVOs())){
				return null;
			}else
				return messagesMainVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public FeeTypeMainVO getFeeParticulars(long custId,long academicYearId){
		try {
			FeeTypeMainVO feeTypeMainVO = null;
			List<FeeTypeVO> feeTypeVOs = new ArrayList<FeeTypeVO>();
			List<FeeType> feeTypeList =  this.getAll(FeeType.class, "custId="+custId+" and academicYearId="+academicYearId);
			if(ObjectFunctions.isNotNullOrEmpty(feeTypeList)){
				feeTypeMainVO = new FeeTypeMainVO();
				for(FeeType feeType : feeTypeList) {
					FeeTypeVO feeTypeVO = new FeeTypeVO();
					feeTypeVO.setId(feeType.getId());
					feeTypeVO.setFeeType(feeType.getFeeType());
					feeTypeVO.setFeeSettingId(feeType.getFeeSettingId());
					feeTypeVO.setCommittedFeeStatus(feeType.getCommittedFeeStatus());
					feeTypeVO.setPriorityPosition(feeType.getPriorityPosition());
					feeTypeVO.setStatus(feeType.getStatus());
					feeTypeVOs.add(feeTypeVO);
					feeTypeVO=null;
				}
				feeTypeMainVO.setFeeTypeVOs(feeTypeVOs);
				feeTypeVOs=null;
				return feeTypeMainVO;
			}
			feeTypeList=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public SchoolCategoryMainVO studentCategoryList(long custId){
		try {
			SchoolCategoryMainVO schoolCategoryMainVO = null;
			List<SchoolCategoryVO> schoolCategoryVOS = new ArrayList<SchoolCategoryVO>();
			List<SchoolCategory> schoolCategories =  this.getAll(SchoolCategory.class, "custId="+custId);
			if(ObjectFunctions.isNotNullOrEmpty(schoolCategories)){
				schoolCategoryMainVO = new SchoolCategoryMainVO();
				for(SchoolCategory schoolCategory : schoolCategories) {
					SchoolCategoryVO schoolCategoryVO = new SchoolCategoryVO();
					schoolCategoryVO.setId(schoolCategory.getId());
					schoolCategoryVO.setCategoryName(schoolCategory.getCategoryName());
					schoolCategoryVOS.add(schoolCategoryVO);
					schoolCategoryVO=null;
				}
				schoolCategoryMainVO.setSchoolCategoryVOs(schoolCategoryVOS);
				schoolCategoryVOS=null;
				return schoolCategoryMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public SchoolTermsMainVO schoolFeeTermsList(long custId,long academicYearId){
		try {
			Customer customer=(Customer)this.get(Customer.class, custId);
			StringBuffer queryString = new StringBuffer();
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
				queryString.append("status in ('"+Constants.SCHOOL_MODULE+"'");
				if (customer.isTransportModuleStatus() && customer.isHostelModuleStatus()) {
					queryString.append(",'"+Constants.TRANSPORT_STATUS+"','"+Constants.HOSTEL_STATUS+"'");
				} else if (customer.isTransportModuleStatus()) {
					queryString.append(",'"+Constants.TRANSPORT_STATUS+"'");
				} else if (customer.isHostelModuleStatus()){
					queryString.append(",'"+Constants.HOSTEL_STATUS+"'");
				}
				queryString.append(")");
			}
			customer=null;
			SchoolTermsMainVO schoolTermsMainVO = null;
			List<SchoolTermsVO> schoolTermsVOs= null;
			List<SchoolTerms> schoolTermsList =  this.getAll(SchoolTerms.class, " custId="+custId+" and academicYearId="+academicYearId+" and "+queryString.toString());
			if(ObjectFunctions.isNotNullOrEmpty(schoolTermsList)){
				schoolTermsMainVO = new SchoolTermsMainVO();
				schoolTermsVOs= new ArrayList<SchoolTermsVO>();
				for(SchoolTerms  schoolTerms : schoolTermsList) {
					SchoolTermsVO schoolTermsVO = new SchoolTermsVO();
					schoolTermsVO.setId(schoolTerms.getId());
					schoolTermsVO.setStatus(schoolTerms.getStatus());
					schoolTermsVO.setDescription(schoolTerms.getDescription());
					schoolTermsVO.setTermName(schoolTerms.getTermName());
					schoolTermsVO.setFromDate(ObjectFunctions.isNullOrEmpty(schoolTerms.getFromDate())? "": DateFunctions.convertDateToString(schoolTerms.getFromDate()));
					schoolTermsVO.setToDate(ObjectFunctions.isNullOrEmpty(schoolTerms.getToDate())? "": DateFunctions.convertDateToString(schoolTerms.getToDate()));
					schoolTermsVO.setDueDate(ObjectFunctions.isNullOrEmpty(schoolTerms.getDueDate())? "": DateFunctions.convertDateToString(schoolTerms.getDueDate()));
					schoolTermsVO.setFromMonthName(schoolTerms.getFromMonthName());
					schoolTermsVO.setToMonthName(schoolTerms.getToMonthName());
					schoolTermsVO.setNoOfDays(schoolTerms.getNoOfDays());
					//schoolTermsVO.setMailContentDesc(schoolTerms.getMailContentDesc());
					//schoolTermsVO.setMobileContentDesc(schoolTerms.getMobileContentDesc());
					schoolTermsVO.setFeeSettingId(schoolTerms.getFeeSettingId());
					schoolTermsVO.setApplToNewStuds(ObjectFunctions.isNullOrEmpty(schoolTerms.isApplToNewStuds())? null: schoolTerms.isApplToNewStuds());
					schoolTermsVO.setDueDate1(ObjectFunctions.isNullOrEmpty(schoolTerms.getDueDate1())? "": DateFunctions.convertDateToString(schoolTerms.getDueDate1()));
					schoolTermsVO.setDueDate2(ObjectFunctions.isNullOrEmpty(schoolTerms.getDueDate2())? "": DateFunctions.convertDateToString(schoolTerms.getDueDate2()));
					schoolTermsVOs.add(schoolTermsVO);
					schoolTermsVO=null;
				}
				schoolTermsMainVO.setSchoolTermsVOs(schoolTermsVOs);
				schoolTermsVOs=null;
				return schoolTermsMainVO;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public boolean runCCEReports(RunCCReportsVO runCCReportsVO){
		try {
			String status=null;
			String exception=null;
			long custId,academicYearId = 0;
			log.debug("runCCReportsVO :"+runCCReportsVO.toString());
			if(!ObjectFunctions.isNullOrEmpty(runCCReportsVO)){
				MailUtil mailUtil= null;
           		String[] emailAddresses =null;
				status = runCCReportsVO.getResult();
				if("success".equalsIgnoreCase(status)){
					Process p = null;
               		//if("prod".equalsIgnoreCase(applicationDetailsJson.getString("ServerType"))){
               			p = Runtime.getRuntime().exec("sh /etc/init.d/cce.sh");
	               /*	}else{
	               		p = Runtime. getRuntime().exec("sh /etc/init.d/cce.sh");
               		}*/
               		p.waitFor();
                	//Runtime.getRuntime().exec("rm -f /home/eazyschool/cce/" + fileName);
               		String fileName = runCCReportsVO.getFileName();
               		String filePath = "/home/eazyschool/cce/" + fileName;
               		log.debug("filePath :"+filePath);
               		File file = new File(filePath);
               		if(file.exists()){
               			FileUtils.deleteQuietly(file);
               			//Runtime.getRuntime().exec("rm -f " + file.getAbsolutePath());
               			log.debug("Run CCE file exist and deleted.");
               			
               		}else
               			log.debug("Run CCE file not exist (or) not found, this code not worked out....");
               		return true;
				}else{
					exception = runCCReportsVO.getException();
					custId = runCCReportsVO.getIdentifier().getCustId();
					academicYearId = runCCReportsVO.getIdentifier().getAcademicYearId();
	           		String server = runCCReportsVO.getServerType();
	           		if(StringFunctions.isNotNullOrEmpty(exception) && StringFunctions.isNotNullOrEmpty(server) && custId > 0 && academicYearId > 0 ){
	           			List<Object[]> emailsAndMobileNumbers = this.getAll("select email,id from supportTeam where (email!='' AND email is not null)");
	           			Set<String> emailIdsSet = new HashSet<String>();
	           			if(!ObjectFunctions.isNullOrEmpty(emailsAndMobileNumbers)){
	           				String[] emailAdd = null;
	           				for(Object obj[] : emailsAndMobileNumbers){
	           					if(!ObjectFunctions.isNullOrEmpty(obj[0]))
	           						emailIdsSet.add(obj[0].toString());
	           					if(!ObjectFunctions.isNullOrEmpty(emailIdsSet)){
	           					    int i = 0;
	           					    emailAdd = new String[emailIdsSet.size()];
	           						for (Object emailId : emailIdsSet) {
	           						    if (!ObjectFunctions.isNullOrEmpty(emailId)) {
	           						    	emailAdd[i] = emailId.toString();
	           						    	emailId = null;
	           						    }
	           						    i++;
	           						}
	           					}
	           				}
	           				mailUtil = new MailUtil(emailAdd,"Error Occurred in ("+server+") ScoreCard WCF Service for CustId : "+custId+", AcdId : "+academicYearId,0,null,null,"messages@eazyschool.com","ForSupportTeamEmails");
	           				mailUtil.sendMailForSupportUrt("(From DotNet Server) "+exception,null,null);
	           			}
	           		}	
	           		mailUtil=null;
				}
			}	
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return true;
	}
	public CommonTypeMainVO getCommonTypes(long custId,String type){
		try { 
			CommonTypeVO commonTypeVO =null;
			CommonTypeMainVO commonTypeMainVO=null;
			List<CommonTypeVO> commonTypeVOList = new ArrayList<CommonTypeVO>();
			StringBuffer query = new StringBuffer("custId=").append(custId);
			if(StringFunctions.isNotNullOrEmpty(type)){
				if(!(type.contains("'") || type.startsWith("\""))){
					type="'"+type+"'";	
				}
				query.append(" and type=").append(type);
			}
			List<CommonType> commonTypeList = this.getAll(CommonType.class,query.toString());
			if (!ObjectFunctions.isNullOrEmpty(commonTypeList)) {
				for(CommonType commonType : commonTypeList){
					if(!ObjectFunctions.isNullOrEmpty(commonType)) { 
						commonTypeVO = new CommonTypeVO();
						commonTypeVO.setId(commonType.getId());
						commonTypeVO.setType(commonType.getType());
						commonTypeVO.setSkillTypeName(commonType.getSkillTypeName());
						commonTypeVOList.add(commonTypeVO);
						commonTypeVO=null;
					}
				}
				commonTypeMainVO = new CommonTypeMainVO();
				commonTypeMainVO.setCommonTypeVOList(commonTypeVOList);
				return commonTypeMainVO;
			}query=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public CasteSettingsMainVO getCasteSettings(long custId){
		try { 
			CasteSettingsMainVO casteSettingsMainVO =null;
			CasteSettingsVO  casteSettingsVO=null;
			SubCasteSettingsVO  subCastSettingsVO=null;
			List<SubCastSettings> subCastSettingsList =null;
			List<CasteSettingsVO> casteSettingsVOList = new ArrayList<CasteSettingsVO>();
			List<SubCasteSettingsVO> subCastSettingsVOList = null;
			StringBuffer query = new StringBuffer("custId=").append(custId);
			List<CastSettings> casteSettingsList =this.getAll(CastSettings.class,query.toString());
			if (!ObjectFunctions.isNullOrEmpty(casteSettingsList)) {
				for(CastSettings casteSettings : casteSettingsList){
					if(!ObjectFunctions.isNullOrEmpty(casteSettings)) { 
						casteSettingsVO = new CasteSettingsVO();
						casteSettingsVO.setId(casteSettings.getId());
						casteSettingsVO.setCasteName(casteSettings.getCastName());
						casteSettingsVO.setSubCasteName(casteSettings.getSubCastName());	
						subCastSettingsList =this.getAll(SubCastSettings.class, "castId="+casteSettingsVO.getId()+" and custId="+custId);
						if (!ObjectFunctions.isNullOrEmpty(subCastSettingsList)) {
							subCastSettingsVOList = new ArrayList<SubCasteSettingsVO>();
							for(SubCastSettings subCastSettings : subCastSettingsList){
								if(!ObjectFunctions.isNullOrEmpty(subCastSettings)) { 
									subCastSettingsVO = new SubCasteSettingsVO();
									subCastSettingsVO.setId(subCastSettings.getId());
									subCastSettingsVO.setCasteId(subCastSettings.getCastSettings().getId());
									subCastSettingsVO.setSubCastName(subCastSettings.getSubCastName());
									subCastSettingsVOList.add(subCastSettingsVO);
									subCastSettings = null;
								}
							}
						casteSettingsVO.setSubCasteSettingsVOList(subCastSettingsVOList);
						casteSettingsVOList.add(casteSettingsVO);
						casteSettingsVO = null;
						subCastSettingsList=null;
					}
				  }
				}
				casteSettingsList=null;
				casteSettingsMainVO = new CasteSettingsMainVO();
				casteSettingsMainVO.setCasteSettingsVOList(casteSettingsVOList);
				return casteSettingsMainVO;
				
			}
			query=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return null;
		}
		return null;
	}
	public FeeMainVO classFeeList(long custId,long academicYearId){
		try {
			FeeMainVO feeMainVO = null;
			List<FeeVO> feeVOs= null;
			List<Fee> feeList =  this.getAll(Fee.class, " custId="+custId+" and academicYearId="+academicYearId);
			if(ObjectFunctions.isNotNullOrEmpty(feeList)){
				feeMainVO = new FeeMainVO();
				feeVOs= new ArrayList<FeeVO>();
				for(Fee  fee : feeList) {
					FeeVO feeVO = new FeeVO();
					feeVO.setId(fee.getId());
					feeVO.setStatus(fee.getStatus());
					if(!ObjectFunctions.isNullOrEmpty(fee.getClassName()))
						feeVO.setClassId(ObjectFunctions.isNullOrEmpty(fee.getClassName().getId())? 0: fee.getClassName().getId());
					else
						feeVO.setClassId(0);
					feeVO.setSchoolTermId(fee.getSchoolTerms().getId());
					feeVO.setFeeTypeId(fee.getFeeType().getId());
					feeVO.setFeeAmount(fee.getFeeAmount());
					feeVO.setRouteBoardingPointId(fee.getRouteBoardingPointId());
					feeVO.setCategoryId(fee.getCategoryId());
					feeVOs.add(feeVO);
					feeVO=null;
				}
				feeMainVO.setFeeVOs(feeVOs);
				feeVOs=null;
				return feeMainVO;
			}
		
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public AdmissionSettingsVO admissionsOpenedForAcademicYearList(long custId){
		AdmissionSettingsVO admissionSettingsVO = null;
		try {
			AdmissionSettings admissionSettings = (AdmissionSettings) this.get(AdmissionSettings.class,"custId="+custId);
			if(!ObjectFunctions.isNullOrEmpty(admissionSettings)){
				admissionSettingsVO = new AdmissionSettingsVO();
				admissionSettingsVO.getAcademicYear().setId(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getId())? 0: admissionSettings.getAcademicYear().getId());
				admissionSettingsVO.getAcademicYear().setAcademicYear(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getAcademicYear())? "": admissionSettings.getAcademicYear().getAcademicYear());
				admissionSettingsVO.getAcademicYear().setStatus(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getStatus())? "": admissionSettings.getAcademicYear().getStatus());
				admissionSettingsVO.getAcademicYear().setPastYear(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getPastYear())? 0: admissionSettings.getAcademicYear().getPastYear());
				admissionSettingsVO.getAcademicYear().setStartDate(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getStartDate())? "" : DateFunctions.convertDateToString(admissionSettings.getAcademicYear().getStartDate()));
				admissionSettingsVO.getAcademicYear().setEndDate(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getEndDate())? "" : DateFunctions.convertDateToString(admissionSettings.getAcademicYear().getEndDate()));
				admissionSettingsVO.getAcademicYear().setStartTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getStartTime())? "" : admissionSettings.getAcademicYear().getStartTime());
				admissionSettingsVO.getAcademicYear().setEndTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getEndTime())? "" : admissionSettings.getAcademicYear().getEndTime());
				admissionSettingsVO.getAcademicYear().setNoOfWorkingDays(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getNoOfWorkingDays())? 0 : admissionSettings.getAcademicYear().getNoOfWorkingDays());
				admissionSettingsVO.getAcademicYear().setNoOfHolidays(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getNoOfHolidays())? 0 : admissionSettings.getAcademicYear().getNoOfHolidays());
				admissionSettingsVO.getAcademicYear().setNextAcademicStartDate(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getNextAcademicStartDate())? "" :  DateFunctions.convertDateToString(admissionSettings.getAcademicYear().getNextAcademicStartDate()));
				admissionSettingsVO.getAcademicYear().setMorningBreakStartTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getMorningBreakStartTime())? "" : admissionSettings.getAcademicYear().getMorningBreakStartTime());
				admissionSettingsVO.getAcademicYear().setMorningBreakEndTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getMorningBreakEndTime())? "" : admissionSettings.getAcademicYear().getMorningBreakEndTime());
				admissionSettingsVO.getAcademicYear().setEveningBreakStartTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getEveningBreakStartTime())? "" : admissionSettings.getAcademicYear().getEveningBreakStartTime());
				admissionSettingsVO.getAcademicYear().setEveningBreakEndTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getEveningBreakEndTime())? "" : admissionSettings.getAcademicYear().getEveningBreakEndTime());
				admissionSettingsVO.getAcademicYear().setLunchStartTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getLunchStartTime())? "" : admissionSettings.getAcademicYear().getLunchStartTime());
				admissionSettingsVO.getAcademicYear().setLunchEndTime(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getLunchEndTime())? "" : admissionSettings.getAcademicYear().getLunchEndTime());
				admissionSettingsVO.getAcademicYear().setAttendancePercentage(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getAttendancePercentage())? 0 : admissionSettings.getAcademicYear().getAttendancePercentage());
				admissionSettingsVO.getAcademicYear().setFutureAcademicData(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().isFutureAcademicData())? false : admissionSettings.getAcademicYear().isFutureAcademicData());
				admissionSettingsVO.getAcademicYear().setClassTeacherHandleFirstPeriod(StringFunctions.isNullOrEmpty(String.valueOf(admissionSettings.getAcademicYear().isClassTeacherHandleFirstPeriod()))? "" : String.valueOf(admissionSettings.getAcademicYear().isClassTeacherHandleFirstPeriod()));
				admissionSettingsVO.getAcademicYear().setManageAttendanceBy(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getManageAttendanceBy())? "" : admissionSettings.getAcademicYear().getManageAttendanceBy());
				admissionSettingsVO.getAcademicYear().setManageStaffAttendanceBy(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getManageStaffAttendanceBy())? "" : admissionSettings.getAcademicYear().getManageStaffAttendanceBy());
				admissionSettingsVO.getAcademicYear().setUseBiometricForStaff(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getUseBiometricForStaff())? "" : admissionSettings.getAcademicYear().getUseBiometricForStaff());
				admissionSettingsVO.getAcademicYear().setTransportFeeByBoardingPoint(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().isTransportFeeByBoardingPoint())? false : admissionSettings.getAcademicYear().isTransportFeeByBoardingPoint());
				admissionSettingsVO.getAcademicYear().setSendBirthdayAlerts(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().isSendBirthdayAlerts())? false : admissionSettings.getAcademicYear().isSendBirthdayAlerts());
				admissionSettingsVO.getAcademicYear().setManageStudentsAdmissionsByFee(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getManageStudentsAdmissionsByFee())? "" : admissionSettings.getAcademicYear().getManageStudentsAdmissionsByFee());
				admissionSettingsVO.getAcademicYear().setDispActivityDescField(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().isDispActivityDescField())? false : admissionSettings.getAcademicYear().isDispActivityDescField());
				admissionSettingsVO.getAcademicYear().setHolidayStatus(ObjectFunctions.isNullOrEmpty(admissionSettings.getAcademicYear().getHolidayStatus())? "" : admissionSettings.getAcademicYear().getHolidayStatus());
				this.saveServiceRequesByCustId(custId,admissionSettings.getAcademicYear().getId(),"admissionsOpenedAcademicYear",null);
				admissionSettings = null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return admissionSettingsVO;
	}
	public StudentPaymentMainVO createStudentPaymentSyncFromWebToDesktop(long custId,long academicYearId){
		try {
			StudentPaymentMainVO studentPaymentMainVO =null;
			List<StudentPaymentVO> studentPaymentVOs = null;
			StudentPaymentVO studentPaymentVO = null;
			log.debug("studentPaymentList :"+new Date());
			List<StudentPayment> studentPaymentList =this.getAll(StudentPayment.class,"custId="+custId+" and academicYearId="+academicYearId);
			log.debug("studentPaymentList end :"+new Date());
			if(!ObjectFunctions.isNullOrEmpty(studentPaymentList)){
				studentPaymentMainVO = new StudentPaymentMainVO();
				studentPaymentVOs = new ArrayList<StudentPaymentVO>();
				StringBuffer paymentIds = new StringBuffer("(");
				log.debug("studentPaymentList for start :"+new Date());
				for(StudentPayment studentPayment : studentPaymentList){
					studentPaymentVO = new StudentPaymentVO();
					studentPaymentVO.setId(studentPayment.getId());
					studentPaymentVO.setLastAccessDate(ObjectFunctions.isNullOrEmpty(studentPayment.getLastAccessDate())? "": DateFunctions.convertDateToString(studentPayment.getLastAccessDate()));
					studentPaymentVO.setLastUpdatedDate(ObjectFunctions.isNullOrEmpty(studentPayment.getLastUpdatedDate())? "": DateFunctions.convertDateToString(studentPayment.getLastUpdatedDate()));
					studentPaymentVO.setCreatedDate(ObjectFunctions.isNullOrEmpty(studentPayment.getCreatedDate())? "": DateFunctions.convertDateToString(studentPayment.getCreatedDate()));
					studentPaymentVO.setCreatedById(studentPayment.getCreatedById());
					studentPaymentVO.setLastUpdatedById(studentPayment.getLastUpdatedById());
					studentPaymentVO.setPaidAmount(studentPayment.getPaidAmount());
					studentPaymentVO.setDiscountAmount(studentPayment.getDiscountAmount());
					studentPaymentVO.setStudentId(studentPayment.getStudent().getId());
					studentPaymentVO.setInvoiceNumber(studentPayment.getInvoiceNumber());
					studentPaymentVO.setBranchName(ObjectFunctions.isNullOrEmpty(studentPayment.getBranchName())? "": studentPayment.getBranchName());
					studentPaymentVO.setChequeNumber(ObjectFunctions.isNullOrEmpty(studentPayment.getChequeNumber())? "": studentPayment.getChequeNumber());
					studentPaymentVO.setChequeIssuedDate(ObjectFunctions.isNullOrEmpty(studentPayment.getChequeIssuedDate())? "": DateFunctions.convertDateToString(studentPayment.getChequeIssuedDate()));
					studentPaymentVO.setDdNumber(ObjectFunctions.isNullOrEmpty(studentPayment.getDdNumber())? "": studentPayment.getDdNumber());
					studentPaymentVO.setDdDrawnDate(ObjectFunctions.isNullOrEmpty(studentPayment.getDdDrawnDate())? "": DateFunctions.convertDateToString(studentPayment.getDdDrawnDate()));
					studentPaymentVO.setPaymentType(studentPayment.getPaymentType());
					studentPaymentVO.setDeleteStatus(studentPayment.getDeleteStatus());
					studentPaymentVO.setBankMasterId(ObjectFunctions.isNullOrEmpty(studentPayment.getBankMaster())? 0: studentPayment.getBankMaster().getId());
					studentPaymentVO.setBankName(ObjectFunctions.isNullOrEmpty(studentPayment.getBankMaster())? "": studentPayment.getBankMaster().getBankName());
					studentPaymentVO.setPaymentDate(ObjectFunctions.isNullOrEmpty(studentPayment.getPaymentDate())? "": DateFunctions.convertDateToString(studentPayment.getPaymentDate()));
					studentPaymentVO.setDpPaymentDetailsId(studentPayment.getDpPaymentDetailsId());
					studentPaymentVO.setIpAddress(ObjectFunctions.isNullOrEmpty(studentPayment.getIpAddress())? "": studentPayment.getIpAddress());
					studentPaymentVO.setExcessAmount(studentPayment.getExcessAmount());
					studentPaymentVO.setDiscountDesc(ObjectFunctions.isNullOrEmpty(studentPayment.getDiscountDesc())? "": studentPayment.getDiscountDesc());
					studentPaymentVO.setDesktopReceiptNumber(ObjectFunctions.isNullOrEmpty(studentPayment.getDesktopReceiptNumber())? "": studentPayment.getDesktopReceiptNumber());
					studentPaymentVO.setFineAmount(studentPayment.getFineAmount());
					studentPaymentVOs.add(studentPaymentVO);
					paymentIds.append(studentPayment.getId()).append(",");
					studentPaymentVO=null;
					studentPaymentMainVO.setStudentPaymentVOs(studentPaymentVOs);
				}
				log.debug("studentPaymentList for end :"+new Date());
				paymentIds.append("0)");
				List<StudentFeePaidDetails> studentFeeDetailsList = this.getAll(StudentFeePaidDetails.class, "custId="+custId+" and studentPaymentId in "+paymentIds.toString());
				if(!ObjectFunctions.isNullOrEmpty(studentFeeDetailsList)){
					List<StudentFeePaidDetailsVO> studentFeePaidDetailsVOs = this.getstudentFeePaidDetails(studentFeeDetailsList);
					studentPaymentMainVO.setStudentFeePaidDetailsVOs(studentFeePaidDetailsVOs);
				}
				List<ExcessPayment> excessPaymentList =this.getAll(ExcessPayment.class,"paymentId in "+paymentIds.toString());
				if(!ObjectFunctions.isNullOrEmpty(excessPaymentList)){
					List<ExcessPaymentVO> excessPaymentVOs = this.getExcessPaymentDetails(excessPaymentList);
					studentPaymentMainVO.setExcessPaymentVOs(excessPaymentVOs);
					excessPaymentVOs=null;
				}
				return studentPaymentMainVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public List<StudentFeePaidDetailsVO> getstudentFeePaidDetails(List<StudentFeePaidDetails> studentFeeDetailsList){
		try {
			StudentFeePaidDetailsVO studentFeePaidDetailsVO =null;
			List<StudentFeePaidDetailsVO> studentFeePaidDetailsVOs =new ArrayList<StudentFeePaidDetailsVO>();
			for(StudentFeePaidDetails studentFeePaidDetails : studentFeeDetailsList){
				studentFeePaidDetailsVO = new StudentFeePaidDetailsVO();
				studentFeePaidDetailsVO.setId(studentFeePaidDetails.getId());
				studentFeePaidDetailsVO.setLastAccessDate(ObjectFunctions.isNullOrEmpty(studentFeePaidDetails.getLastAccessDate())? "": DateFunctions.convertDateToString(studentFeePaidDetails.getLastAccessDate()));
				studentFeePaidDetailsVO.setLastUpdatedDate(ObjectFunctions.isNullOrEmpty(studentFeePaidDetails.getLastUpdatedDate())? "": DateFunctions.convertDateToString(studentFeePaidDetails.getLastUpdatedDate()));
				studentFeePaidDetailsVO.setCreatedDate(ObjectFunctions.isNullOrEmpty(studentFeePaidDetails.getCreatedDate())? "": DateFunctions.convertDateToString(studentFeePaidDetails.getCreatedDate()));
				studentFeePaidDetailsVO.setCreatedById(studentFeePaidDetails.getCreatedById());
				studentFeePaidDetailsVO.setLastUpdatedById(studentFeePaidDetails.getLastUpdatedById());
				studentFeePaidDetailsVO.setStudentPaymentId(studentFeePaidDetails.getStudentPaymentId());
				studentFeePaidDetailsVO.setPaymentAmount(studentFeePaidDetails.getPaymentAmount());
				studentFeePaidDetailsVO.setDiscountAmount(studentFeePaidDetails.getDiscountAmount());
				if(!ObjectFunctions.isNullOrEmpty(studentFeePaidDetails.getFee()))
					studentFeePaidDetailsVO.setFeeId(studentFeePaidDetails.getFee().getId());
				if(!ObjectFunctions.isNullOrEmpty(studentFeePaidDetails.getStudentTransportDetails()))
					studentFeePaidDetailsVO.setFeeId(studentFeePaidDetails.getStudentTransportDetails().getId());
				studentFeePaidDetailsVO.setPaymentStatus(studentFeePaidDetails.getPaymentStatus());
				studentFeePaidDetailsVO.setFutureFeeStatus(studentFeePaidDetails.getFutureFeeStatus());
				studentFeePaidDetailsVO.setDeleteStatus(studentFeePaidDetails.getDeleteStatus());
				studentFeePaidDetailsVO.setStudentId(studentFeePaidDetails.getStudentId());
				studentFeePaidDetailsVO.setFeePaidDetailsId(studentFeePaidDetails.getFeePaidDetailsId());
				studentFeePaidDetailsVO.setCommittedFeeStatus(studentFeePaidDetails.getCommittedFeeStatus());
				studentFeePaidDetailsVOs.add(studentFeePaidDetailsVO);
				studentFeePaidDetailsVO=null;
				studentFeePaidDetails=null;
			}
			return studentFeePaidDetailsVOs;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public List<ExcessPaymentVO> getExcessPaymentDetails(List<ExcessPayment> excessPaymentList){
		try {
			ExcessPaymentVO excessPaymentVO = null;
			List<ExcessPaymentVO> excessPaymentVOs = new ArrayList<ExcessPaymentVO>();
			for(ExcessPayment excessPayment : excessPaymentList){
				excessPaymentVO = new ExcessPaymentVO();
				excessPaymentVO.setId(excessPayment.getId());
				excessPaymentVO.setLastAccessDate(ObjectFunctions.isNullOrEmpty(excessPayment.getLastAccessDate())? "": DateFunctions.convertDateToString(excessPayment.getLastAccessDate()));
				excessPaymentVO.setLastUpdatedDate(ObjectFunctions.isNullOrEmpty(excessPayment.getLastUpdatedDate())? "": DateFunctions.convertDateToString(excessPayment.getLastUpdatedDate()));
				excessPaymentVO.setCreatedDate(ObjectFunctions.isNullOrEmpty(excessPayment.getCreatedDate())? "": DateFunctions.convertDateToString(excessPayment.getCreatedDate()));
				excessPaymentVO.setCreatedById(excessPayment.getCreatedById());
				excessPaymentVO.setLastUpdatedById(excessPayment.getLastUpdatedById());
				excessPaymentVO.setAccountId(excessPayment.getAccountId());
				excessPaymentVO.setPaymentId(excessPayment.getPaymentId());
				excessPaymentVO.setUsedPaymentId(excessPayment.getUsedPaymentId());
				excessPaymentVO.setExcessAmount(excessPayment.getExcessAmount());
				excessPaymentVO.setStatus(excessPayment.isStatus());
				excessPaymentVOs.add(excessPaymentVO);
				excessPaymentVO=null;
				excessPayment=null;
			}
			return excessPaymentVOs;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public StudentScoreCardVO scoreCardDetailWithStudentAndStudyClassIdWise(long custId,long academicYearId,long studentId,long studyClassId,long examTypeId){
		String pathValue="Exact path is not available.";
		StudentScoreCardVO studentScoreCardVO = new StudentScoreCardVO();
		try {
			ViewStudentPersonAccountDetails viewStudentPersonAccountDetails=null;
			Customer customer = (Customer) this.get(Customer.class,custId);
			AcademicYear academicYear = (AcademicYear)this.get(AcademicYear.class,"id="+academicYearId);
			ExamTypes examType = (ExamTypes)this.get(ExamTypes.class,"id="+examTypeId+" and custId="+custId+" and academicYearId="+academicYearId);
			StudyClass studyClass = null;
			studyClass = (StudyClass) this.get(StudyClass.class,studyClassId);
			StringBuffer pathName = null;
			if (studentId > 0 && studyClassId>0 && !ObjectFunctions.isNullOrEmpty(examType)) {
				viewStudentPersonAccountDetails= (ViewStudentPersonAccountDetails) this.get(ViewStudentPersonAccountDetails.class,"custId="+custId+" and academicYearId="+academicYearId+" and studentId="+studentId+" and classSectionId="+studyClassId+" and description is null");
				if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails)){
					pathName = new StringBuffer("userFiles/").append("ScoreCardTemplates/").append(customer.getCustomerShortName()).append("/").append(academicYear.getAcademicYear()).append("/").append(examType.getExamType()).append("/").append("ClassSection_").append(viewStudentPersonAccountDetails.getClassSectionId());
				    pathName.append("/");
				    //pathName.append(viewStudentPersonAccountDetails.getFullName()).append("_").append(viewStudentPersonAccountDetails.getAdmissionNumber().replaceAll("/",""));
				   // pathName.append("/");
				    if(!ObjectFunctions.isNullOrEmpty(studyClass)){
				    	pathName.append(studyClass.getClassName()+"-"+studyClass.getSection()+"_"+viewStudentPersonAccountDetails.getFullName().replaceAll("\\s","")).append("_").append(String.valueOf(viewStudentPersonAccountDetails.getAccountId()).replaceAll("/","")).append(".xlsx");
				    }
				 }
			}else{
				if(!ObjectFunctions.isNullOrEmpty(studyClass) && !ObjectFunctions.isNullOrEmpty(examType)){
					pathName = new StringBuffer("userFiles/ScoreCardTemplates/"+customer.getCustomerShortName()+"/"+academicYear.getAcademicYear()+"/"+examType.getExamType()+"/ClassSection_"+studyClassId+".zip");
				}
			} 
			if(!ObjectFunctions.isNullOrEmpty(pathName)){
				String fieLocation = SpringContextAware.getRealPath(pathName.toString());
				File directoryToZip = new File(fieLocation);
				if(!directoryToZip.exists()){
					directoryToZip = null;
					return null;
				}else{
					pathValue = SpringContextAware.getHostUrl()+"//"+pathName.toString();
					studentScoreCardVO.setPathValue(pathValue);
				}
			}
			this.saveServiceRequesByCustId(custId,academicYear.getId(),"scoreCardWithClassId",null);
			customer=null;
			examType = null;
			academicYear = null;
			studyClass=null;
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return studentScoreCardVO;
	}
	public void updateSelectedCustomersOrgId(String selectedId,Long orgId, String tempString){
		adminDao.updateSelectedCustomersOrgId(selectedId,orgId,tempString);
	}
	
	public List<SocialDiscussionsVO> getSocialDiscussionsList(long custId, long accountId){
		try {
			List<SocialDiscussions> socialDiscussionsList =null;
			List<SocialDiscussionsVO> socialDiscussionsVOs = new ArrayList<SocialDiscussionsVO>();
				if(accountId>0){
					socialDiscussionsList = this.getAll(SocialDiscussions.class, "accountId=" + accountId + " and custId=" + custId + " order by messageDate DESC");
				}else{
					 socialDiscussionsList = this.getAll(SocialDiscussions.class, "custId=" + custId+ " order by messageDate DESC");
				}
				if(!ObjectFunctions.isNullOrEmpty(socialDiscussionsList)){
					for(SocialDiscussions socialDiscussions : socialDiscussionsList){
						socialDiscussionsVOs.add(this.getsocialDiscussionsDetails(socialDiscussions));
					socialDiscussions=null;
				}
				socialDiscussionsList=null;
			}
			return socialDiscussionsVOs;
		} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public SocialDiscussionsVO getsocialDiscussionsById(long socialDiscussionsVOId){
		try {
		SocialDiscussions socialDiscussions =(SocialDiscussions)this.dao.get(SocialDiscussions.class, socialDiscussionsVOId);
		if(!ObjectFunctions.isNullOrEmpty(socialDiscussions)){
			SocialDiscussionsVO socialDiscussionsVO = new SocialDiscussionsVO();
			return this.getsocialDiscussionsDetails(socialDiscussions);
		}
		} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
		}
	
	public SocialDiscussionsVO getsocialDiscussionsDetails(SocialDiscussions socialDiscussions){
		try {
			UserVO userVo = new UserVO();
			SocialDiscussionsVO socialDiscussionsVO = new SocialDiscussionsVO();
			socialDiscussionsVO.setDescription(socialDiscussions.getDescription());
			socialDiscussionsVO.setStatus(socialDiscussions.getStatus());
			socialDiscussionsVO.setMessageDate(socialDiscussions.getMessageDate());
			socialDiscussionsVO.setMessageDateStr(socialDiscussions.getMessageDateStr());
			socialDiscussionsVO.setSubject(socialDiscussions.getSubject());
			socialDiscussionsVO.setAttachmentPath(socialDiscussions.getAttachmentPath());
			socialDiscussionsVO.setAttachmentName(socialDiscussions.getAttachmentName());
			socialDiscussionsVO.setAttachmentType(socialDiscussions.getAttachmentType());
			socialDiscussionsVO.setCategoryStr(socialDiscussions.getCategoryStr());			
			socialDiscussionsVO.setCategory(socialDiscussions.getCategory());
			socialDiscussionsVO.setAdjustedStudentImagePath(socialDiscussions.getAdjustedStudentImagePath());
			userVo.setFullName(socialDiscussions.getAccount().getPerson().getFullFormattedName());
			userVo.setUsername(socialDiscussions.getAccount().getUsername());
			userVo.setId(socialDiscussions.getAccount().getId());
			socialDiscussionsVO.setAccountVo(userVo);
			socialDiscussionsVO.setId(socialDiscussions.getId());
			return socialDiscussionsVO; 
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public List<ShareUserActivitiesVO> getShareUserActivitiesList(long custId, String type,long accountId,Date messageDate){
		try {
			List<ShareUserActivities> shareUserActivitiesList =null;
			List<ShareUserActivitiesVO> shareUserActivitiesVOs = new ArrayList<ShareUserActivitiesVO>();
			StringBuffer query = new StringBuffer();
			query.append("custId=").append(custId);
			if(!ObjectFunctions.isNullOrEmpty(messageDate)){
				query.append(" and messageDate >=").append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,messageDate));
				query.append(" and messageType='E'");
			}
			else if("otherPosts".equalsIgnoreCase(type)){
				query.append(" and visibleType='P' ");
				query.append(" and accountId not in(").append(accountId).append(")");
				query.append(" and imagePath is not null");
			}
			else if("AllPosts".equalsIgnoreCase(type)){
				query.append(" and visibleType='P'");
				query.append(" or visibleType='O' and accountId="+accountId);
			}else{
				if(accountId>0)
					query.append(" and accountId=").append(accountId);
				if("P".equalsIgnoreCase(type))
					query.append(" and visibleType='P'");
				if("E".equalsIgnoreCase(type))
					query.append(" and messageType='E'");
			}
			query.append(" order by messageDate DESC");
			if(!ObjectFunctions.isNullOrEmpty(messageDate)){
				query.append(" limit 3");
			}
			shareUserActivitiesList = this.getAll(ShareUserActivities.class, query.toString());
			query=null;
			if(!ObjectFunctions.isNullOrEmpty(shareUserActivitiesList)){
				for(ShareUserActivities shareUserActivities : shareUserActivitiesList){
					shareUserActivitiesVOs.add(this.getUserActivitiesDetails(shareUserActivities));
					shareUserActivities=null;
				}
			shareUserActivitiesList=null;
			}
			return shareUserActivitiesVOs;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public ShareUserActivitiesVO getShareUserActivitiesById(long shareUserActivitiesVoId){
		try {
			ShareUserActivities shareUserActivities =(ShareUserActivities)this.dao.get(ShareUserActivities.class, shareUserActivitiesVoId);
			if(!ObjectFunctions.isNullOrEmpty(shareUserActivities)){
				return this.getUserActivitiesDetails(shareUserActivities);
			}
			} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			}
		return null;
		}
	
	public ShareUserActivitiesVO getUserActivitiesDetails(ShareUserActivities shareUserActivities){
		try {
			UserVO userVo = new UserVO();
			ShareUserActivitiesVO svo=new ShareUserActivitiesVO();
			svo.setDescription(shareUserActivities.getDescription());
			svo.setId(shareUserActivities.getId());
			svo.setEventName(shareUserActivities.getEventName());
			svo.setEventLocation(shareUserActivities.getEventLocation());
			svo.setStartDateTime(shareUserActivities.getStartDateTime());
			svo.setMessageDate(shareUserActivities.getMessageDate());
			svo.setMessageDateStr(shareUserActivities.getFormatDateStr());
			svo.setStatus(shareUserActivities.getStatus());
			svo.setMessageType(shareUserActivities.getMessageType());
			svo.setEndDateTime(shareUserActivities.getEndDateTime());
			svo.setImagePath(shareUserActivities.getImagePath());
			svo.setImageName(shareUserActivities.getImageName());
			svo.setImageType(shareUserActivities.getImageType());
			userVo.setFullName(shareUserActivities.getAccount().getPerson().getFullFormattedName());
			userVo.setUsername(shareUserActivities.getAccount().getUsername());
			userVo.setId(shareUserActivities.getAccount().getId());
			svo.setCreatedDate(shareUserActivities.getCreatedDateStr());
			svo.setAdjustedStudentImagePath(shareUserActivities.getAdjustedStudentImagePath());
			svo.setAccountVO(userVo);
			return svo;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public boolean runTimeTableReports(RunCCReportsVO runCCReportsVO){
		
		long custId = runCCReportsVO.getIdentifier().getCustId();
		long academicYearId = runCCReportsVO.getIdentifier().getAcademicYearId();
		MailUtil mailUtil= null;
   		String[] emailAddresses =null;
   		
		try {
			String status=null;
			//String exception=null;
			
			status = runCCReportsVO.getResult();
			//if("success".equalsIgnoreCase(status)){
				/*log.debug("Before calling timetable_move.sh file");
           		Process p = Runtime.getRuntime().exec("sh /etc/init.d/timetable.sh");
       		    p.waitFor();
           		log.debug("Successfully executed timetable.sh file ");*/
           		
           		StringBuffer sqlQuery = null;
				if (custId > 0) {
					
					this.remove("substitutionTimeTable", "custId="+custId+" and academicYearId="+academicYearId);//we remove existing timetable data with the same academic year
					this.remove("staffSubstitutionTimeTable", "custId="+custId+" and academicYearId="+academicYearId);
					this.remove("timetablePeriods", "custId="+custId+" and academicYearId="+academicYearId);//we remove existing timetable periods with the same custId
					
					log.debug(" *************** Starting to save the time table data ************** ");
					timeTableSubstitution(custId, academicYearId,runCCReportsVO.getXmlFilePath());//this method parse the timetable xml file and save the database
					//Send notification for Timetable update
					this.sendNotificationForTimetableUpdate(custId);
					log.debug(" *************** successfully to saved the time table data ************** ");
					
					String mysqlTodayDate = DateFormatter.formatDate (DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date());
					if(!ObjectFunctions.isNullOrEmpty(runCCReportsVO.getStaffTimetables()))
					{
						
						for(StaffTimetablesVO staffTimetablesVO : runCCReportsVO.getStaffTimetables())
						{
							Staff staff=(Staff)  this.get(Staff.class, "accountId = " + staffTimetablesVO.getAccountId() + " and custId = " +custId+ " and status = '" + Constants.YES_STRING+"'");
							if(!ObjectFunctions.isNullOrEmpty(staff))
							{
								StaffTimetables staffTimetables=(StaffTimetables)  this.get(StaffTimetables.class, "staffId = " + staff.getId() + " and custId = " +custId+ " and academicYearId = " + academicYearId);
								if(!ObjectFunctions.isNullOrEmpty(staffTimetables))
								{
									this.updateQuery("update staffTimetables set filePath='"+staffTimetablesVO.getFilePath()+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+staffTimetables.getId());
								}
								else
								{
									staffTimetables = new StaffTimetables();
									staffTimetables.setAcademicYearId(academicYearId);
									staffTimetables.setCustId(custId);
									staffTimetables.setStaffId(staff.getId());
									staffTimetables.setFilePath(staffTimetablesVO.getFilePath());
									staffTimetables.setCreatedDate(new Date());
									staffTimetables = (StaffTimetables) this.save(staffTimetables); 
									
									//staff.setIsTimetableUploaded(Constants.YES_STRING);
									staff.addStaffTimetables(staffTimetables);
									log.debug("fileName timeTable file :"+staffTimetablesVO.getFilePath());
									this.merge(staff); 
									 staff=null;
								}
							}
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(runCCReportsVO.getStudyClassTimetables()))
					{
						for(StudyClassTimetablesVO studyClassTimetablesVO : runCCReportsVO.getStudyClassTimetables())
						{
							this.updateQuery("update studyClass set classTimetableUploadedFilePath='"+studyClassTimetablesVO.getFilePath()+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+studyClassTimetablesVO.getStudyClassId());
							
						}
					}
					
					/*sqlQuery = new StringBuffer("select  distinct(sd.staffId),sd.firstName,sd.lastName,sd.mobileNumber,sd.roleDescription,sd.roleName,sd.qualification1,sd.accountId,sd.isTimetableUploaded,sd.custId  from vw_staffDetails sd LEFT JOIN classTeacher ct on (sd.staffId=ct.teacherId)") 
					.append(" where sd.custId=").append(custId).append(" and sd.academicYearId<="+academicYearId+"").append(" and sd.status='"+Constants.YES_STRING+"'")
					//.append(" and roleId in(2,8,12)")
					.append(" and roleId in (1,2,4,5,6,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,30,31,32,33,35)")
					.append(" order by roleName");
					
					List<Object[]> staffList= this.getAll(sqlQuery.toString());
					Staff staff=null;
					String fileName="";
					if(!ObjectFunctions.isNullOrEmpty(staffList)){
						long accountId=0;
						for(Object[] staffObj : staffList){
							if(!ObjectFunctions.isNullOrEmpty(staffObj)){
							accountId=Long.valueOf(staffObj[7].toString());
							if(accountId > 0){
								staff=(Staff)  this.get(Staff.class, "accountId = " + accountId + " and custId = " +custId+ " and status = '" + Constants.YES_STRING+"'");
								if(!ObjectFunctions.isNullOrEmpty(staff)){
									//staff.setIsTimetableUploaded(Constants.YES_STRING);
									log.debug("fileName timeTable file :"+fileName);
									this.save(staff); 
									 staff=null;
								}
								accountId=0;
							}
						  }
						}
					 }
					List<StudyClass> studyClassList =this.GetAllStudyClasses(custId, academicYearId,null);
					if(!ObjectFunctions.isNullOrEmpty(studyClassList))
					{
						Map<String,StudyClass> studyClassMap = new HashMap<String, StudyClass>();
						
						//File file =null;
						for(StudyClass studyClassObj : studyClassList){
							if(!ObjectFunctions.isNullOrEmpty(studyClassObj)){
								
								studyClassMap.put(String.valueOf(studyClassObj.getId()), studyClassObj);
								studyClassObj.setIsClassTimetableUploaded(Constants.YES_STRING);
								this.save(studyClassObj);
								 studyClassObj=null;
							}
						}
						
						studyClassMap = null;
					}*/
				}
           		return true;
           	/*}else{
           		log.debug("Got the exception, sending the mail to support team");
           		exception = runCCReportsVO.getException();
           		if(StringFunctions.isNotNullOrEmpty(exception)){
           			List<Object[]> emailsAndMobileNumbers = this.getAll("select email,id from supportTeam where (email!='' AND email is not null)");
           			Set<String> emailIdsSet = new HashSet<String>();
           			if(!ObjectFunctions.isNullOrEmpty(emailsAndMobileNumbers)){
           				String[] emailAdd = null;
           				for(Object obj[] : emailsAndMobileNumbers){
           					if(!ObjectFunctions.isNullOrEmpty(obj[0]))
           						emailIdsSet.add(obj[0].toString());
           					if(!ObjectFunctions.isNullOrEmpty(emailIdsSet)){
           					    int i = 0;
           					    emailAdd = new String[emailIdsSet.size()];
           						for (Object emailId : emailIdsSet) {
           						    if (!ObjectFunctions.isNullOrEmpty(emailId)) {
           						    	emailAdd[i] = emailId.toString();
           						    	emailId = null;
           						    }
           						    i++;
           						}
           					}
           				}
           				//mailUtil =new MailUtil(emailAdd, "WCF Service PDF Generation Exception", exception.toString(), "messages@eazyschool.com",null);//  new MailUtil(emailAddresses,"WCF Service calling Error.",0,"Our Support Team","");
						//mailUtil.sendMailForSupportUrt(exception.toString(),null,null);
           			}
           		}	
           		mailUtil=null;
           	}*/
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			if(!ObjectFunctions.isNullOrEmpty(ex)){
       			List<Object[]> emailsAndMobileNumbers = this.getAll("select email,id from supportTeam where (email!='' AND email is not null)");
       			Set<String> emailIdsSet = new HashSet<String>();
       			if(!ObjectFunctions.isNullOrEmpty(emailsAndMobileNumbers)){
       				String[] emailAdd = null;
       				for(Object obj[] : emailsAndMobileNumbers){
       					if(!ObjectFunctions.isNullOrEmpty(obj[0]))
       						emailIdsSet.add(obj[0].toString());
       					if(!ObjectFunctions.isNullOrEmpty(emailIdsSet)){
       					    int i = 0;
       					    emailAdd = new String[emailIdsSet.size()];
       						for (Object emailId : emailIdsSet) {
       						    if (!ObjectFunctions.isNullOrEmpty(emailId)) {
       						    	emailAdd[i] = emailId.toString();
       						    	emailId = null;
       						    }
       						    i++;
       						}
       					}
       				}
       				//mailUtil =new MailUtil(emailAdd, "WCF Service PDF Generation Exception", exception.toString(), "messages@eazyschool.com",null);//  new MailUtil(emailAddresses,"WCF Service calling Error.",0,"Our Support Team","");
					//mailUtil.sendMailForSupportUrt(exception.toString(),null,null);
       				this.saveServiceRequesByCustId(custId,academicYearId,"runTimeTableEReports",null);
       			}
       		}
			return false;
		}
	}
	
	public List<DefaultScoreCardTemplatesVO> getDefaultTemplatesList(){
		try {
			List<DefaultScoreCardTemplatesVO> defaultScoreCardTemplatesVOs = new ArrayList<DefaultScoreCardTemplatesVO>();
			List<DefaultScoreCardTemplates> defaultScoreCardTemplatesList = this.getAll(DefaultScoreCardTemplates.class);
			Collections.sort(defaultScoreCardTemplatesList);
			if(!ObjectFunctions.isNullOrEmpty(defaultScoreCardTemplatesList)){
				for(DefaultScoreCardTemplates scoreCardTemplates : defaultScoreCardTemplatesList){
					defaultScoreCardTemplatesVOs.add(this.getDefaultScorecardTemplateDetails(scoreCardTemplates));
					scoreCardTemplates=null;
				}
				defaultScoreCardTemplatesList=null;
			}
			return defaultScoreCardTemplatesVOs;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public DefaultScoreCardTemplatesVO getDefaultScorecardTemplate(long scoreCardId){
		try {
			DefaultScoreCardTemplates scoreCardTemplates =(DefaultScoreCardTemplates)this.dao.get(DefaultScoreCardTemplates.class, scoreCardId);
			if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplates)){
				return this.getDefaultScorecardTemplateDetails(scoreCardTemplates);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public DefaultScoreCardTemplatesVO getDefaultScorecardTemplateDetails(DefaultScoreCardTemplates scoreCardTemplates){
		try {
			DefaultScoreCardTemplatesVO defaultScoreCardTemplatesVO = new DefaultScoreCardTemplatesVO();
			defaultScoreCardTemplatesVO.setId(scoreCardTemplates.getId());
			defaultScoreCardTemplatesVO.setCustId(scoreCardTemplates.getCreatedById());
			defaultScoreCardTemplatesVO.setScoreCardName(scoreCardTemplates.getScoreCardName());
			defaultScoreCardTemplatesVO.setNoOfExamTypes(scoreCardTemplates.getNoOfExamTypes());
			defaultScoreCardTemplatesVO.setNoOfSubTypes(scoreCardTemplates.getNoOfSubTypes());
			defaultScoreCardTemplatesVO.setExcelFileName(scoreCardTemplates.getExcelFileName());
			defaultScoreCardTemplatesVO.setExcelFilePath(scoreCardTemplates.getExcelFilePath());
			defaultScoreCardTemplatesVO.setImageFileName(scoreCardTemplates.getImageFileName());
			defaultScoreCardTemplatesVO.setImageFilePath(scoreCardTemplates.getImageFilePath());
			return defaultScoreCardTemplatesVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeIdAndSubTypeId(long studId,long examTypeId,long subtypeId){
		return adminDao.getStudentMarksByStudentIdAndExamtypeIdAndSubTypeId(studId, examTypeId, subtypeId);
	}
	public StudySubjectVO getStudySubjectVODetails(StudySubject studySubject){
		try {
			StudySubjectVO studySubjectVo = new StudySubjectVO();
			studySubjectVo.setId(studySubject.getId());
			studySubjectVo.setCustId(studySubject.getCreatedById());
			studySubjectVo.setName(studySubject.getName());
			studySubjectVo.setDescription(studySubject.getDescription());
			studySubjectVo.setStaffAccountId(studySubject.getStaffAccountId());
			studySubjectVo.setSubjectNumber(studySubject.getSubjectNumber());
			studySubjectVo.setSortingOrder(studySubject.getSortingOrder());
			studySubjectVo.setLanguage(studySubject.isLanguage());
			studySubjectVo.setSubjectStaffId(studySubject.getSubjectStaffId());
			studySubjectVo.setSubjectType(studySubject.getSubjectType());
			return studySubjectVo;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public List<StudySubjectVO> getStudySubjectVOsByCustIdAndAcademicYear(long custId,long academicYearId){
		try {
			List<StudySubjectVO> studySubjectVOs = new ArrayList<StudySubjectVO>();
			List<StudySubject> studySubjectList = this.getAll(StudySubject.class,"custId="+custId+" and academicYearId="+ academicYearId);
			Collections.sort(studySubjectList);
			if(!ObjectFunctions.isNullOrEmpty(studySubjectList)){
				for(StudySubject studySubject : studySubjectList){
					studySubjectVOs.add(this.getStudySubjectVODetails(studySubject));
					studySubject=null;
				}
				studySubjectList=null;
			}
			return studySubjectVOs;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public StaffElgibleSubjectsVO getStaffElgibleSubjectVODetails(StaffElgibleSubjects staffElgibleSubject){
		try {
			StaffElgibleSubjectsVO staffElgibleSubjectsVO = new StaffElgibleSubjectsVO();
			staffElgibleSubjectsVO.setStaffIdVo(staffElgibleSubject.getStaffId().copyFromEntityToVo(staffElgibleSubject.getStaffId()));
			staffElgibleSubjectsVO.setStudySubjectIdVo(staffElgibleSubject.getStudySubjectId().copyFromEntityToVo(staffElgibleSubject.getStudySubjectId()));
			staffElgibleSubjectsVO.setAcademicYearVo(staffElgibleSubject.getAcademicYear().copyFromEntityToVo(staffElgibleSubject.getAcademicYear()));
			return staffElgibleSubjectsVO;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public List<StaffElgibleSubjectsVO> getStaffElgibleSubjectVOsByAcademicYearId(long staffId,long academicYearId){
		try {
			List<StaffElgibleSubjectsVO> staffElgibleSubjectsVOs = new ArrayList<StaffElgibleSubjectsVO>();
			List<StaffElgibleSubjects> staffElgibleSubjectsList = this.getAll(StaffElgibleSubjects.class,"staffId="+staffId+" and academicYearId="+ academicYearId);
			Collections.sort(staffElgibleSubjectsList);
			if(!ObjectFunctions.isNullOrEmpty(staffElgibleSubjectsList)){
				for(StaffElgibleSubjects staffElgibleSubject : staffElgibleSubjectsList){
					staffElgibleSubjectsVOs.add(this.getStaffElgibleSubjectVODetails(staffElgibleSubject));
					staffElgibleSubject=null;
				}
				staffElgibleSubjectsList=null;
			}
			return staffElgibleSubjectsVOs;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassIdAndFinanceUserId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds,long financeUserId){
		return adminDao.getStudentsFeePaidDetailsBySettingAndClassIdAndFinanceUserId(tableName,custId,academicYearId,classSectionIds,fromDate,toDate,feeSettingIds,financeUserId);
	}
	
	public void removeAllSchoolHolidaysByDescriptionAndStartAndEndDate(long custId,String description,String startDate,String endDate) {
		//Sending notification for holidays delete
		this.sendNotificationForHolidaysDelete(custId, description, startDate, endDate);
		adminDao.removeAllSchoolHolidaysByDescriptionAndStartAndEndDate(custId,description,startDate,endDate);
	}
	public void addSchoolHolidays(SchoolHolidays schoolHolidays,Object[] classNameClassIds,long custId,long userId,AcademicYear academicYear){
		try {
			if(!ObjectFunctions.isNullOrEmpty(schoolHolidays.getDescription()) && !ObjectFunctions.isNullOrEmpty(schoolHolidays.getStartDate()) && !ObjectFunctions.isNullOrEmpty(schoolHolidays.getEndDate())){
				//05-10-2016 Commentes added by Sunanda because we have take out school working Day from excel sheet
				/*if("No".equalsIgnoreCase(schoolHolidays.getSchoolWorkingDay()) || StringFunctions.isNullOrEmpty(schoolHolidays.getSchoolWorkingDay()))
				{
				*/	long classId = 0;
					if (!ObjectFunctions.isNullOrEmpty(classNameClassIds))
    				{
                    	if (!ObjectFunctions.isNullOrEmpty(classNameClassIds[0]))
	    				{
                    		classId = Long.valueOf(classNameClassIds[0].toString());
	    				}
    				}
					this.createShcoolHolidayImpl(schoolHolidays.getEndDate(), classId, custId, userId, academicYear.getId(), schoolHolidays.getStartDate(),schoolHolidays.getDescription().trim(), academicYear, false, null);
				}
			//}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
   }
	
	/*public String removeAllHolidaysByDescription(long custId,String description,String startDate,String endDate) {
		return adminDao.removeAllHolidaysByDescription(custId,description,startDate,endDate);
	}*/
	
	
	//this method parse the timetable xml file and save the database
	public void  timeTableSubstitution(long custId,long academicYearId,String xmlFilePath){

		//http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		
		Map<String,Staff> staffsMap = new HashMap<String, Staff>();
		List<Staff> staffList = this.getAll(Staff.class,"custId="+custId+" and academicYearId<="+academicYearId+" and status='"+Constants.YES_STRING+"'");
		if(!ObjectFunctions.isNullOrEmpty(staffList))
		{
			for(Staff staff : staffList)
			{
				staffsMap.put(String.valueOf(staff.getId()), staff);
				staff = null;
			}
		}
		staffList = null;
		
		Map<String,StudySubject> studySubjectsMap = new HashMap<String, StudySubject>();
		List<StudySubject> studySubjectsList = this.GetAllStudySubjects(custId,academicYearId);
		if(!ObjectFunctions.isNullOrEmpty(studySubjectsList))
		{
			for(StudySubject studySubject : studySubjectsList)
			{
				studySubjectsMap.put(String.valueOf(studySubject.getId()), studySubject);
				studySubject = null;
			}
		}
		studySubjectsList = null;
		
		Map<String,StudyClass> studyClassMap = new HashMap<String, StudyClass>();
		List<StudyClass> classesList = this.GetAllStudyClasses(custId, academicYearId,null);
		if(!ObjectFunctions.isNullOrEmpty(classesList))
		{
			for(StudyClass studyClass : classesList)
			{
				studyClassMap.put(String.valueOf(studyClass.getId()), studyClass);
				studyClass = null;
			}
		}
		classesList = null;
		
		Map<String,Integer> weekDaysMap = new HashMap<String, Integer>();
		List<WeekDays> weekDaysList = this.getAll(WeekDays.class);
		
		if(!ObjectFunctions.isNullOrEmpty(weekDaysList))
		{
			for(WeekDays weekDays : weekDaysList)
			{
				weekDaysMap.put(weekDays.getDayName(),(int)weekDays.getId());
				weekDays = null;
			}
		}
		weekDaysList = null;
		
		Map<String,TimetablePeriods> timetablePeriodsMap = new HashMap<String, TimetablePeriods>();
		
		List<TimetablePeriods> timetablePeriodsList = this.getAll(TimetablePeriods.class,"custId="+custId+" and academicYearId="+academicYearId+ " and status='"+Constants.ACTIVE_STATUS+"'");
		if(!ObjectFunctions.isNullOrEmpty(timetablePeriodsList))
		{
			for(TimetablePeriods timetablePeriods : timetablePeriodsList)
			{
				timetablePeriodsMap.put(String.valueOf(timetablePeriods.getTimePeriod()), timetablePeriods);
				timetablePeriods = null;
			}
		}
		timetablePeriodsList = null;
		File fXmlFile = null;
		try {
			
				URL url = new URL(xmlFilePath);
				URLConnection conn = url.openConnection();
				InputStream is = conn.getInputStream();
			
				fXmlFile = File.createTempFile("StudyClassWise", ".xml");
				fXmlFile.deleteOnExit();
				FileUtils.copyInputStreamToFile(is, fXmlFile);
				
			
			   // String studyClassWisePath = SpringContextAware.getRealPath("userFiles/timetable/"+custId+"/"+academicYearId+"/xmlfiles/StudyClassWise.xml");
				//File fXmlFile = new File(studyClassWisePath);
				if(fXmlFile.exists())
				{
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
							
					//optional, but recommended
					//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
					doc.getDocumentElement().normalize();
			
					log.debug("Root element :" + doc.getDocumentElement().getNodeName());
							
					NodeList nList = doc.getElementsByTagName("Subgroup");
					
					log.debug("----------------------------");
					
					for (int temp = 0; temp < nList.getLength(); temp++) 
					{
						try {
							Node nNode = nList.item(temp);
							log.debug("\nCurrent Element :" + nNode.getNodeName());
									
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {

								org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

								log.debug("Subgroup name : " + eElement.getAttribute("name"));
								
								if(!ObjectFunctions.isNullOrEmpty(studyClassMap.get(eElement.getAttribute("name"))))
								{
									NodeList DayList =  eElement.getChildNodes();
									
									for (int tempDay = 0; tempDay < DayList.getLength(); tempDay++) {

										Node nNodeDay = DayList.item(tempDay);
												
										log.debug("\nCurrent Element :" + nNodeDay.getNodeName());
												
										if (nNodeDay.getNodeType() == Node.ELEMENT_NODE) {

											org.w3c.dom.Element eElementDay = (org.w3c.dom.Element) nNodeDay;

											log.debug("Day name : " + eElementDay.getAttribute("name"));
											
											NodeList HourList =  eElementDay.getChildNodes();
											
											if(!ObjectFunctions.isNullOrEmpty(HourList))
											{
												for (int tempHour = 0; tempHour < HourList.getLength(); tempHour++) {

													Node nNodeHour = HourList.item(tempHour);
															
													log.debug("\nCurrent Element :" + nNodeHour.getNodeName());
															
													if (nNodeHour.getNodeType() == Node.ELEMENT_NODE) {

														org.w3c.dom.Element eElementHour = (org.w3c.dom.Element) nNodeHour;

														log.debug("Hour name : " + eElementHour.getAttribute("name"));
														
														SubstitutionTimeTable timeTable = new SubstitutionTimeTable();
														timeTable.setAcademicYearId(academicYearId);
														timeTable.setCustId(custId);
														timeTable.setSubject(null);
														//timeTable.setTeachers(null);
														timeTable.setCreatedById(0);
														timeTable.setCreatedDate(new Date());
														timeTable.setLastAccessDate(new Date());
														timeTable.setLastUpdatedById(0);
														timeTable.setLastUpdatedDate(new Date());
														
														timeTable.setClassSection(studyClassMap.get(eElement.getAttribute("name")));
														timeTable.setDayId(weekDaysMap.get(eElementDay.getAttribute("name").toUpperCase()));
														timeTable.setPeriodName(eElementHour.getAttribute("name"));
														
														if(ObjectFunctions.isNullOrEmpty(timetablePeriodsMap.get(eElementHour.getAttribute("name"))))
														{
															TimetablePeriods timetablePeriods = new TimetablePeriods();
															timetablePeriods.setCustId(custId);
															timetablePeriods.setTimePeriod(eElementHour.getAttribute("name"));
															timetablePeriods.setStatus(Constants.ACTIVE_STATUS);
															timetablePeriods.setAcademicYearId(academicYearId);
															timetablePeriods.setCreatedById(0);
															timetablePeriods.setCreatedDate(new Date());
															timetablePeriods.setLastAccessDate(new Date());
															timetablePeriods.setLastUpdatedById(0);
															timetablePeriods.setLastUpdatedDate(new Date());
															timetablePeriods = (TimetablePeriods)this.save(timetablePeriods);
															
															timetablePeriodsMap.put(eElementHour.getAttribute("name"), timetablePeriods);
															timetablePeriods = null;
														}
														
														timeTable.setTimetablePeriods(timetablePeriodsMap.get(eElementHour.getAttribute("name")));
														
														NodeList teacherSubject =  eElementHour.getChildNodes();
														
														if(!ObjectFunctions.isNullOrEmpty(teacherSubject))
														{
															for (int tempTeachSub = 0; tempTeachSub < teacherSubject.getLength(); tempTeachSub++) {

																Node nNodeTeachSub = teacherSubject.item(tempTeachSub);
																
																if(!ObjectFunctions.isNullOrEmpty(nNodeTeachSub))
																{
																	log.debug("\nCurrent Element :" + nNodeTeachSub.getNodeName());
																	
																	if("Teacher".equalsIgnoreCase(nNodeTeachSub.getNodeName()))
																	{
																		if (nNodeTeachSub.getNodeType() == Node.ELEMENT_NODE) 
																		{

																			org.w3c.dom.Element eElementTeachSub = (org.w3c.dom.Element) nNodeTeachSub;

																			log.debug("Teach name : " + eElementTeachSub.getAttribute("name"));
																			
																			timeTable.setStaff(staffsMap.get(eElementTeachSub.getAttribute("name")));

																		}
																	}
																	else if("Subject".equalsIgnoreCase(nNodeTeachSub.getNodeName()))
																	{
																		if (nNodeTeachSub.getNodeType() == Node.ELEMENT_NODE) 
																		{
																			org.w3c.dom.Element eElementTeachSub = (org.w3c.dom.Element) nNodeTeachSub;

																			log.debug("Subject name : " + eElementTeachSub.getAttribute("name"));
																			timeTable.setSubject(studySubjectsMap.get(eElementTeachSub.getAttribute("name")));

																		}
																	}
																}
															}
														}
														
														this.saveSmsObject(timeTable);
														timeTable = null;
													}
												}
											}
											
										}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
						
					log.debug("----------------------------");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
	    }
		finally
		{
			fXmlFile.delete();
		}
	  }
	
	public List<StaffSubstitutionTimeTable> getStaffSubstitutionTimeTableListsByAcademicYearIdAndDate(long custId,long academicYearId,String substitutionDate,String staffId){
		try {
			StringBuffer query = new StringBuffer();
			query.append("custId="+custId);
			query.append(" and academicYearId="+academicYearId);
			query.append(" and substitutionDate='" +substitutionDate+ "'");
			if(!StringFunctions.isNullOrEmpty(staffId))
			{
				query.append(" and substituteStaffId="+staffId);
			}
			return this.getAll(StaffSubstitutionTimeTable.class,query.toString());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public JSONObject prepareJsonForExamSchedules(ExamSchedules exam){
		try {
				JSONObject examSchedulesJsonObj = new JSONObject();
				JSONObject examTypeJsonObj = new JSONObject();
				JSONObject subTypeJsonObj = new JSONObject();
				
				JSONObject classSectionSubjectJsonObj = new JSONObject();
				JSONObject studyClassJsonObj = new JSONObject();
				
				if (!ObjectFunctions.isNullOrEmpty(exam)) {
					
					examSchedulesJsonObj.put("id",exam.getId());
					examSchedulesJsonObj.put("startDate",ObjectFunctions.isNullOrEmpty(exam.getStartDate())? "": DateFunctions.convertDateToString(exam.getStartDate()));
					examSchedulesJsonObj.put("startTime",ObjectFunctions.isNullOrEmpty(exam.getStartTime())? "": exam.getStartTime());
					examSchedulesJsonObj.put("endDate",ObjectFunctions.isNullOrEmpty(exam.getEndDate())? "": DateFunctions.convertDateToString(exam.getEndDate()));
					examSchedulesJsonObj.put("endTime",ObjectFunctions.isNullOrEmpty(exam.getEndTime())? "": exam.getEndTime());
					examSchedulesJsonObj.put("maxMarks",exam.getMaxMarks());
					examSchedulesJsonObj.put("scheduled",Boolean.valueOf(exam.isScheduled()? "Y": "N"));
						
					// Exam-Type
					if(!ObjectFunctions.isNullOrEmpty(exam.getExamType())){
						examTypeJsonObj.put("id",exam.getExamType().getId());
						examTypeJsonObj.put("examType",exam.getExamType().getExamType());
						examTypeJsonObj.put("sortingOrder",exam.getExamType().getSortingOrder());
						examTypeJsonObj.put("minMarks",exam.getExamType().getMinMarks());
						examTypeJsonObj.put("maxMarks",exam.getExamType().getMaxMarks());
					}else
					{
						examTypeJsonObj.put("id","");
						examTypeJsonObj.put("examType","");
						examTypeJsonObj.put("sortingOrder","");
						examTypeJsonObj.put("minMarks","");
						examTypeJsonObj.put("maxMarks","");
					}
					examSchedulesJsonObj.put("examTypeVO", examTypeJsonObj);
					
					// Subject
					if(!ObjectFunctions.isNullOrEmpty(exam.getClassSectionSubject()) || !ObjectFunctions.isNullOrEmpty(exam.getClassSection())){
						classSectionSubjectJsonObj.put("subjectId",exam.getClassSectionSubject().getId());
						classSectionSubjectJsonObj.put("subjectName",exam.getClassSectionSubject().getName());
						
					}else
					{
						classSectionSubjectJsonObj.put("subjectId","");
						classSectionSubjectJsonObj.put("subjectName","");
					}
					examSchedulesJsonObj.put("classSectionSubjectVO", classSectionSubjectJsonObj);
					
					// Study-Class
					if(!ObjectFunctions.isNullOrEmpty(exam.getClassSection())){
						studyClassJsonObj.put("id",exam.getClassSection().getId());
						studyClassJsonObj.put("className",exam.getClassSection().getClassName());
						studyClassJsonObj.put("section",ObjectFunctions.isNullOrEmpty(exam.getClassSection().getSection())? "": exam.getClassSection().getSection());
					}else
					{
						studyClassJsonObj.put("id","");
						studyClassJsonObj.put("className","");
						studyClassJsonObj.put("section","");
					}
					examSchedulesJsonObj.put("studyClassVO", studyClassJsonObj);
					
					// Sub-Type
					if(!ObjectFunctions.isNullOrEmpty(exam.getSubType())){
						subTypeJsonObj.put("id",exam.getSubType().getId());
						subTypeJsonObj.put("subTypeName",exam.getSubType().getSubTypeName());
						subTypeJsonObj.put("sortingOrder",exam.getSubType().getSortingOrder());
					}else
					{
						subTypeJsonObj.put("id","");
						subTypeJsonObj.put("subTypeName","");
						subTypeJsonObj.put("sortingOrder","");
					}
					
					examSchedulesJsonObj.put("subTypeVO", subTypeJsonObj);
				}
			return examSchedulesJsonObj;
				
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public boolean changeUserPrimaryAddress(UserAddressChangeVO userAddressChangeVO)
	{
		if(userAddressChangeVO.getIdentifier().getAccountId() >0)
		{
			User account = (User) this.get(User.class, userAddressChangeVO.getIdentifier().getAccountId());
			if(!ObjectFunctions.isNullOrEmpty(account))
			{
				Address primaryAddress = new Address();
				if(!ObjectFunctions.isNullOrEmpty(account.getPrimaryAddress())){
					primaryAddress = (Address) this.get(Address.class, account.getPrimaryAddress().getId());
				}
				primaryAddress.setAddressLine1(userAddressChangeVO.getPrimaryAddressVo().getAddressLine1());
				primaryAddress.setAddressLine2(userAddressChangeVO.getPrimaryAddressVo().getAddressLine2());
				primaryAddress.setCity(userAddressChangeVO.getPrimaryAddressVo().getCity());
				primaryAddress.setState(userAddressChangeVO.getPrimaryAddressVo().getState());
				primaryAddress.setPostalCode(userAddressChangeVO.getPrimaryAddressVo().getPostalCode());
				account.setPrimaryAddress(primaryAddress);
				this.save(account);
				return true;
			}
		}
		return false;
		
	}
	
	public boolean changeUserMobileNumber(UserAddressChangeVO userAddressChangeVO)
	{
		if(userAddressChangeVO.getIdentifier().getAccountId() >0 && !StringFunctions.isNullOrEmpty(userAddressChangeVO.getMobileNumber()))
		{
			User account = (User) this.get(User.class, userAddressChangeVO.getIdentifier().getAccountId());
			if(!ObjectFunctions.isNullOrEmpty(account))
			{
				if(!ObjectFunctions.isNullOrEmpty(account.getPerson())){
					Person person = account.getPerson();
					person.setMobileNumber(userAddressChangeVO.getMobileNumber());
					account.setPerson(person);
					this.save(account);
					return true;
				}
			}
		}
		return false;
		
	}
	
	public ViewRouteVehiclesMainVO getRouteVehiclesByRouteBoardingPointId(long routeBoardingPointId)
	{
		ViewRouteVehiclesMainVO viewRouteVehiclesMainVO = new ViewRouteVehiclesMainVO();
		
		List<ViewAssignedVehiclestoRoutesWithBoardingPointsVo> vehiclesVOList = new ArrayList<ViewAssignedVehiclestoRoutesWithBoardingPointsVo>();
		StringBuffer query = new StringBuffer("boardingPointId=").append(routeBoardingPointId);
		List<ViewAssignedVehiclestoRoutesWithBoardingPoints> vehiclesList = this.getAllViewAssignedVehiclestoRoutesWithBoardingPoints(query.toString());
		if(!ObjectFunctions.isNullOrEmpty(vehiclesList))
		{
			for(ViewAssignedVehiclestoRoutesWithBoardingPoints viewAssignedVehiclestoRoutesWithBoardingPoints : vehiclesList)
			{
				ViewAssignedVehiclestoRoutesWithBoardingPointsVo vehicleVo = viewAssignedVehiclestoRoutesWithBoardingPoints.copyFromEntityToVo(viewAssignedVehiclestoRoutesWithBoardingPoints);
				vehiclesVOList.add(vehicleVo);
			}
			viewRouteVehiclesMainVO.setVehiclesList(vehiclesVOList);
		}
		return viewRouteVehiclesMainVO;
		
	}

	public long addStudentFeeConcessionDetails(String concessionFormData,long studentId, AcademicYear academicYear, long userId, long custId) {
		try {
			JSONObject formData = new JSONObject(concessionFormData);
			JSONArray studentConcessionFeeDetailsJsonArray = (JSONArray) formData.get("data");
			long concessionId = 0;
			long feeId = 0;
			long concessionAmount = 0;
			double totalConcessionAmount=0;
			double totalFeeAmount=0;
			long feeSettingId = 0;
			Fee newFee = null;
			StudentTransportDetails studentTransportDetails = null;
			JSONObject studentConcessionJson = null;
			Date paymentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,new Date()));
			if (!ObjectFunctions.isNullOrEmpty(studentConcessionFeeDetailsJsonArray)) {
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				StudentFeePaidDetails studentFeePaidDetails = null;
				StudentFeePaidDetails invoiceFeePaidDetails = null;
				StudentPayment studentPayment = (StudentPayment) this.get(StudentPayment.class, "custId=" + custId+ " and academicYearId=" + academicYear.getId()+ " and studentId=" + studentId+ " and concessionStatus='"+ Constants.YES_STRING + "' ");
				if (ObjectFunctions.isNullOrEmpty(studentPayment)) {
					studentPayment = new StudentPayment();
					studentPayment.setCreatedDate(new Date());
					studentPayment.setCreatedById(userId);
				}
				Student student = (Student) this.get(Student.class, studentId);
				studentPayment.setLastUpdatedDate(new Date());
				studentPayment.setLastUpdatedById(userId);
				studentPayment.setCustId(custId);
				studentPayment.setStudent(student);
				studentPayment.setAcademicYear(academicYear);
				studentPayment.setIpAddress(ipAddress);
				studentPayment.setLastUpdatedById(userId);
				studentPayment.setPaymentDate(paymentDate);
				studentPayment.setPaymentType("C");
				studentPayment.setDeleteStatus(Constants.NO_STRING);
				studentPayment.setConcessionStatus(Constants.YES_STRING);

				for (int i = 0; i < studentConcessionFeeDetailsJsonArray.length(); i++) {
					double totalPaidAmount=0;
					studentConcessionJson = studentConcessionFeeDetailsJsonArray.getJSONObject(i);
					if (!ObjectFunctions.isNullOrEmpty(studentConcessionJson)) {
						if (!ObjectFunctions.isNullOrEmpty(studentConcessionJson.get("feeId"))) {
							feeId = Long.valueOf((String) studentConcessionJson.get("feeId"));
						}
						if (!ObjectFunctions.isNullOrEmpty(studentConcessionJson.get("concessionId"))) {
							concessionId = Long.valueOf((String) studentConcessionJson.get("concessionId"));
						}
						if (!ObjectFunctions.isNullOrEmpty(studentConcessionJson.get("concessionAmount"))) {
							concessionAmount = Long.valueOf((String) studentConcessionJson.get("concessionAmount"));
						}
						if (!ObjectFunctions.isNullOrEmpty(studentConcessionJson.get("feeSettingId"))) {
							feeSettingId = Long.valueOf((String) studentConcessionJson.get("feeSettingId"));
						}
						
						if(feeSettingId == 3)
						{
							studentTransportDetails  = (StudentTransportDetails)this.get(StudentTransportDetails.class,"custId="+ custId+ " and academicYearId="+ academicYear.getId()+ " and id="+ feeId+ " and studentId="+ studentId);
							invoiceFeePaidDetails = (StudentFeePaidDetails) this.get(StudentFeePaidDetails.class,"custId="+ custId+ " and studentId="+student.getId()+" and studTransportDetailsId="+ studentTransportDetails.getId()+ " and committedFeeStatus='N' ");
						}else{
							newFee = (Fee) this.get(Fee.class, feeId);
							invoiceFeePaidDetails = (StudentFeePaidDetails) this.get(StudentFeePaidDetails.class,"custId="+ custId+ " and studentId="+student.getId()+" and feeId="+ newFee.getId()+ " and committedFeeStatus='N' ");
						}
						
						
						if(!ObjectFunctions.isNullOrEmpty(invoiceFeePaidDetails)){
							totalPaidAmount = (invoiceFeePaidDetails.getPaymentAmount()+invoiceFeePaidDetails.getDiscountAmount());
						}
						StudentFeeConcession studentFeeConcession = null;
						if (!ObjectFunctions.isNullOrEmpty(newFee) || !ObjectFunctions.isNullOrEmpty(studentTransportDetails)) {
							if (concessionAmount > 0) {
								if (concessionId > 0) {
									if(!ObjectFunctions.isNullOrEmpty(newFee))
										studentFeeConcession = (StudentFeeConcession) this.get(StudentFeeConcession.class,"custId="+ custId+ " and academicYearId="+ academicYear.getId()+ " and feeId="+ feeId+ " and id="+ concessionId+ " and studentId="+ studentId);
									else if (!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
										studentFeeConcession = (StudentFeeConcession) this.get(StudentFeeConcession.class,"custId="+ custId+ " and academicYearId="+ academicYear.getId()+ " and studTransportDetailsId="+ feeId+ " and id="+ concessionId+ " and studentId="+ studentId);
									if (ObjectFunctions.isNullOrEmpty(studentFeeConcession)) {
										studentFeeConcession = new StudentFeeConcession();
										studentFeeConcession.setCreatedDate(new Date());
										studentFeeConcession.setCreatedById(userId);
									}
								} else {
									studentFeeConcession = new StudentFeeConcession();
									studentFeeConcession.setCreatedDate(new Date());
									studentFeeConcession.setCreatedById(userId);
								}
								studentFeeConcession.setLastUpdatedDate(new Date());
								studentFeeConcession.setLastAccessDate(new Date());
								studentFeeConcession.setLastUpdatedById(userId);
								studentFeeConcession.setCustId(custId);
								studentFeeConcession.setAcademicYear(academicYear);
								studentFeeConcession.setStudentId(studentId);
								if(!ObjectFunctions.isNullOrEmpty(newFee))
									studentFeeConcession.setFee(newFee);
								studentFeeConcession.setConcessionAmount(concessionAmount);
								//Siva: This below code is to save student transport using feesetting id
								if(!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
									studentFeeConcession.setStudentTransportDetails(studentTransportDetails);
								
								// this.save(studentFeeConcession);
								StudentFeeConcession feeConcession = (StudentFeeConcession) this.saveOrUpdateObject(studentFeeConcession);
								if (!ObjectFunctions.isNullOrEmpty(feeConcession)) {
									if (!ObjectFunctions.isNullOrEmpty(studentPayment)) {
										if(!ObjectFunctions.isNullOrEmpty(newFee))
											studentFeePaidDetails = (StudentFeePaidDetails) this.get(StudentFeePaidDetails.class,"custId="+ custId+ " and feeId="+ newFee.getId()+ " and studentPaymentId="+ studentPayment.getId()+ " and committedFeeStatus='Y' ");
										else if (!ObjectFunctions.isNullOrEmpty(studentTransportDetails)) 
											studentFeePaidDetails = (StudentFeePaidDetails) this.get(StudentFeePaidDetails.class,"custId="+ custId+ " and studTransportDetailsId="+ studentTransportDetails.getId()+ " and studentPaymentId="+ studentPayment.getId()+ " and committedFeeStatus='Y' ");
									}
									if (ObjectFunctions.isNullOrEmpty(studentFeePaidDetails)) {
										studentFeePaidDetails = new StudentFeePaidDetails();
										studentFeeConcession.setCreatedDate(new Date());
										studentFeeConcession.setCreatedById(userId);
									}
									studentFeeConcession.setLastUpdatedDate(new Date());
									studentFeeConcession.setLastUpdatedById(userId);
									studentFeePaidDetails.setCustId(custId);
									studentFeePaidDetails.setStudentId(studentId);
									if(!ObjectFunctions.isNullOrEmpty(newFee))
										studentFeePaidDetails.setFee(newFee);
									studentFeePaidDetails.setPaymentAmount(0d);
									studentFeePaidDetails.setDiscountAmount(0d);
									studentFeePaidDetails.setCommittedFeeStatus(Constants.YES_STRING);
									studentFeePaidDetails.setConcessionAmount(concessionAmount);
									studentFeePaidDetails.setDeleteStatus(Constants.NO_STRING);
									//Siva: This below code is to save student transport using feesetting id
									if(!ObjectFunctions.isNullOrEmpty(studentTransportDetails)){
										studentFeePaidDetails.setStudentTransportDetails(studentTransportDetails);
									}
									
									if(!ObjectFunctions.isNullOrEmpty(newFee)){
										if (newFee.getFeeAmount() == (concessionAmount+totalPaidAmount)) {
											studentFeePaidDetails.setPaymentStatus("P");
										} else {
											studentFeePaidDetails.setPaymentStatus("N");
										}
									}else if (!ObjectFunctions.isNullOrEmpty(studentTransportDetails)){
										if (studentTransportDetails.getPickupAndDropFeeAmount() == (concessionAmount+totalPaidAmount)) {
											studentFeePaidDetails.setPaymentStatus("P");
										} else {
											studentFeePaidDetails.setPaymentStatus("N");
										}
									
									}
									if (studentPayment.getId() != 0) {
										studentFeePaidDetails.setStudentPaymentId(studentPayment.getId());
										this.save(studentFeePaidDetails);
									} else
										studentPayment.addStudentFeeDetails(studentFeePaidDetails);
								}
							} else {
								if (concessionId > 0) {
									studentFeePaidDetails = (StudentFeePaidDetails) this.get(StudentFeePaidDetails.class,"custId="+ custId+ " and feeId="+ newFee.getId()+ " and studentPaymentId="+ studentPayment.getId()+ " and committedFeeStatus='Y' ");
									if (!ObjectFunctions.isNullOrEmpty(studentFeePaidDetails)) {
										this.remove(StudentFeePaidDetails.class,studentFeePaidDetails.getId());
										this.remove(StudentFeeConcession.class,concessionId);
									}
								}
							}
						}
						totalConcessionAmount = (totalConcessionAmount+concessionAmount);
						if(!ObjectFunctions.isNullOrEmpty(newFee))
							totalFeeAmount = totalFeeAmount+newFee.getFeeAmount();
						else if (!ObjectFunctions.isNullOrEmpty(studentTransportDetails))
							totalFeeAmount = totalFeeAmount+studentTransportDetails.getPickupAndDropFeeAmount();
					}
					newFee=null;
					studentTransportDetails=null;
				}
				studentPayment.setPaidAmount(totalConcessionAmount);
				this.merge(studentPayment);
				Object[] concessionStdObject = adminDao.get("select ifNULL(sum(paymentConcessionAmount),0) as paymentConcessionAmount,id from vw_studentFeePaymentDetails where academicYearId="+ academicYear.getId() + " and custId="+ custId + "  and studentId="+ student.getId() +" and paymentStatus='"+ Constants.PAYMENT_STATUS+ "' and deleteStatus='"+ Constants.NO_STRING+ "' and paymentCommitFeeStatus='"+Constants.YES_STRING+"' ");
				Object[] transportConcessionStdObject = adminDao.get("select ifNULL(sum(paymentConcessionAmount),0) as paymentConcessionAmount,id from vw_studentTransportFeePaymentDetails where academicYearId="+ academicYear.getId() + " and custId="+ custId + "  and studentId="+ student.getId() +" and paymentStatus='"+ Constants.PAYMENT_STATUS+ "' and deleteStatus='"+ Constants.NO_STRING+ "' and paymentCommitFeeStatus='"+Constants.YES_STRING+"' ");
				if(!ObjectFunctions.isNullOrEmpty(concessionStdObject)){
					double paidConcessionAMount=Double.valueOf(concessionStdObject[0].toString());
					if(!ObjectFunctions.isNullOrEmpty(transportConcessionStdObject))
						paidConcessionAMount += Double.valueOf(transportConcessionStdObject[0].toString());
					
					log.debug("totalFeeAmount :"+totalFeeAmount+"paidConcessionAMount :"+paidConcessionAMount);
					if(totalFeeAmount == paidConcessionAMount){
						adminDao.updateStudentFeePaidStatus(student.getId(),"C");
					}else{
						this.checkStudentFeePaidStatus(academicYear.getId(), custId, student);
						/*StudentPayment payment = (StudentPayment)adminDao.get(StudentPayment.class, "custId="+custId+" and academicYearId="+academicYear.getId()+" and studentId="+student.getId()+" and deleteStatus='"+Constants.NO_STRING+"' and concessionStatus='N' ");
						if(!ObjectFunctions.isNullOrEmpty(payment)){
							adminDao.updateStudentFeePaidStatus(student.getId(),"P");
						}else
							adminDao.updateStudentFeePaidStatus(student.getId(),"N");*/
					}
				}
				
				
				return 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return 0;
		}
		return 0;
	}
	public StudentFeeConcessionMainVO getStudentFeeConcessionDetails(long custId,long academicYearId){
		try {
			StudentFeeConcessionMainVO studentFeeConcessionMainVO = null;
			List<StudentFeeConcessionVO> studentFeeConcessionVOS = new ArrayList<StudentFeeConcessionVO>();
			List<StudentFeeConcession> studentFeeConcessionsList =  this.getAll(StudentFeeConcession.class, "custId="+custId+" and academicYearId="+academicYearId);
			if(ObjectFunctions.isNotNullOrEmpty(studentFeeConcessionsList)){
				studentFeeConcessionMainVO = new StudentFeeConcessionMainVO();
				for(StudentFeeConcession studentFeeConcession : studentFeeConcessionsList) {
					StudentFeeConcessionVO studentFeeConcessionVO = new StudentFeeConcessionVO();
					studentFeeConcessionVO.setId(studentFeeConcession.getId());
					if(!ObjectFunctions.isNullOrEmpty(studentFeeConcession.getFee()))
						studentFeeConcessionVO.setFeeId(studentFeeConcession.getFee().getId());
					if(!ObjectFunctions.isNullOrEmpty(studentFeeConcession.getStudentTransportDetails()))
						studentFeeConcessionVO.setStudTransportDetailsId(studentFeeConcession.getStudentTransportDetails().getId());
					studentFeeConcessionVO.setStudentId(studentFeeConcession.getStudentId());
					studentFeeConcessionVO.setConcessionAmount(studentFeeConcession.getConcessionAmount());
					studentFeeConcessionVOS.add(studentFeeConcessionVO);
					studentFeeConcessionVO=null;
				}
				studentFeeConcessionMainVO.setStudentFeeConcessionVOs(studentFeeConcessionVOS);
				studentFeeConcessionVOS=null;
				return studentFeeConcessionMainVO;
			}
			studentFeeConcessionsList=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public List<Customer> checkCustomerShortName(String shortName) {
		return adminDao.checkCustomerShortName(shortName);
	}
	public StudentFeeVO getStudentFeeDetails(long parentAccountId) {
		try {
			List<StudentFeeDetailsVO> studentFeeDetailsVOS = new ArrayList<StudentFeeDetailsVO>();
			List<String> custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+parentAccountId);
			List<String> academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
			List<String> studentIds = this.getAll("SELECT GROUP_CONCAT(s.id) FROM student s WHERE s.academicYearId IN("+academicYears.get(0)+") AND s.accountId IN(SELECT a.id FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+parentAccountId+")");
			List<Object[]> studentFeeDetailsList = this.getAll("SELECT studentId, firstName, lastName, schoolTermId, termName, dueDate, fromDate, toDate, settingName, feeTypeId, feeType, feeAmount, paymentStatus FROM vw_studentFeePaymentDetails WHERE studentId IN("+studentIds.get(0)+") AND deleteStatus='N' UNION SELECT studentId, firstName, lastName, schoolTermId, termName, dueDate, fromDate, toDate, settingName, feeTypeId, feeType, feeAmount, paymentStatus FROM vw_studentTransportFeePaymentDetails WHERE studentId IN ("+studentIds.get(0)+") AND deleteStatus='N' GROUP BY schoolTermId, feeTypeId, studentId ORDER BY studentId, schoolTermId, feeTypeId");
			if(ObjectFunctions.isNotNullOrEmpty(studentFeeDetailsList)){
				StudentFeeVO studentFeeVO = new StudentFeeVO();
				int size=0;
				long feeTermId =0;
				long feeTypeId = 0;
				long studentId = 0;
				StudentFeeDetailsVO studentFeeDetailsVO = new StudentFeeDetailsVO();
				List<StudentFeeTermsVO> listStudentFeeTerms = new ArrayList<StudentFeeTermsVO>();
				StudentFeeTermsVO studentFeeTerm = null;
				List<StudentFeeDetailsFeeTypeVO> listStudentFeeTypes = new ArrayList<StudentFeeDetailsFeeTypeVO>();
				StudentFeeDetailsFeeTypeVO studentFeeType = null;
				for(Object[] studentFee : studentFeeDetailsList) {
					
					long feeObjTermId = 0;
					if (!ObjectFunctions.isNullOrEmpty(studentFee[3]))
		            {
						feeObjTermId = Long.valueOf(studentFee[3].toString()); 
		            }
					if(studentId != Long.valueOf(studentFee[0].toString())){
						if(studentFeeDetailsVO != null){
							if(studentFeeType != null){
								if(studentFeeType.getId() > 0) listStudentFeeTypes.add(studentFeeType);
								studentFeeType = null;
							}
							if(studentFeeTerm != null){
								studentFeeTerm.setFeeTypes(listStudentFeeTypes);
								listStudentFeeTerms.add(studentFeeTerm);
								studentFeeTerm = null;
							}
							studentFeeDetailsVO.setFeeTerms(listStudentFeeTerms);
							studentFeeDetailsVOS.add(studentFeeDetailsVO);
							studentFeeDetailsVO = null;
						}
						studentFeeDetailsVO = new StudentFeeDetailsVO();
						listStudentFeeTerms = new ArrayList<StudentFeeTermsVO>();
						listStudentFeeTypes = new ArrayList<StudentFeeDetailsFeeTypeVO>();

						studentId = Long.valueOf(studentFee[0].toString());
					}
					studentFeeDetailsVO.setStudentId(Long.valueOf(studentFee[0].toString()));
					
					StringBuffer ret = new StringBuffer(10);
			 	       
		            if (!ObjectFunctions.isNullOrEmpty(studentFee[1]))
		            {
		                ret.append(studentFee[1].toString());
		            }
		            if (!ObjectFunctions.isNullOrEmpty(studentFee[2]))
		            {
		                ret.append(" ");
		                ret.append(studentFee[2].toString());
		            }
					studentFeeDetailsVO.setStudentName(ret.toString());
					ret = null;
					
					if(feeTermId != Long.valueOf(feeObjTermId)){
						if(studentFeeType != null) if(studentFeeType.getId() > 0) listStudentFeeTypes.add(studentFeeType);
						studentFeeType = new StudentFeeDetailsFeeTypeVO();
						if(ObjectFunctions.isNotNullOrEmpty(listStudentFeeTypes)){
							studentFeeTerm.setFeeTypes(listStudentFeeTypes);
							listStudentFeeTypes = new ArrayList<StudentFeeDetailsFeeTypeVO>();
						}
						if(studentFeeTerm != null) listStudentFeeTerms.add(studentFeeTerm);
						studentFeeTerm = new StudentFeeTermsVO();
						feeTermId = Long.valueOf(feeObjTermId);
					}
					if(!ObjectFunctions.isNullOrEmpty(studentFeeTerm))
					studentFeeTerm.setId(feeObjTermId);
					
					if (!ObjectFunctions.isNullOrEmpty(studentFee[4]))
		            {
						studentFeeTerm.setFeeTerm(studentFee[4].toString());
		            }
					studentFeeTerm.setDueDate(ObjectFunctions.isNullOrEmpty(studentFee[5].toString())? "":studentFee[5].toString());
					studentFeeTerm.setFromDate(ObjectFunctions.isNullOrEmpty(studentFee[6].toString())? "":studentFee[6].toString());
					studentFeeTerm.setToDate(ObjectFunctions.isNullOrEmpty(studentFee[7].toString())? "":studentFee[7].toString());
					studentFeeTerm.setFeeSettingName(ObjectFunctions.isNullOrEmpty(studentFee[8].toString())? "":studentFee[8].toString());
					
					if(feeTypeId != Long.valueOf(studentFee[9].toString()) || feeTypeId == 0){
						if(studentFeeType != null){
							if(studentFeeType.getId() > 0) listStudentFeeTypes.add(studentFeeType);
							studentFeeType = null;
						}
						studentFeeType = new StudentFeeDetailsFeeTypeVO();
						feeTypeId = Long.valueOf(studentFee[9].toString());
					}
					List<Object[]> subQueryObj = null;
					if(Constants.TRANSPORT_FEES.equalsIgnoreCase(studentFee[8].toString()))
						subQueryObj = this.getAll("SELECT SUM(paymentAmount), SUM(paymentConcessionAmount), SUM(discountAmount) FROM (SELECT paymentAmount, paymentConcessionAmount, discountAmount FROM vw_studentTransportFeePaymentDetails WHERE studentId = "+ Long.valueOf(studentFee[0].toString()) +" AND schoolTermId = "+ Long.valueOf(studentFee[3].toString()) +" AND feeTypeId = "+ Long.valueOf(studentFee[9].toString()) +" GROUP BY paymentId)AS t;");
					else
						subQueryObj = this.getAll("SELECT SUM(paymentAmount), SUM(paymentConcessionAmount), SUM(discountAmount) FROM (SELECT paymentAmount, paymentConcessionAmount, discountAmount FROM vw_studentFeePaymentDetails WHERE studentId = "+ Long.valueOf(studentFee[0].toString()) +" AND schoolTermId = "+ Long.valueOf(studentFee[3].toString()) +" AND feeTypeId = "+ Long.valueOf(studentFee[9].toString()) +" GROUP BY paymentId)AS t;");
					
					studentFeeType.setId(Long.valueOf(studentFee[9].toString()));
					studentFeeType.setName(studentFee[10].toString());
					studentFeeType.setAmount(studentFee[11].toString());
					studentFeeType.setPaidAmount(ObjectFunctions.isNotNullOrEmpty(studentFeeDetailsList)?subQueryObj.get(0)[0].toString():"0");
					studentFeeType.setConcessionAmount(ObjectFunctions.isNotNullOrEmpty(studentFeeDetailsList)?subQueryObj.get(0)[1].toString():"0");
					studentFeeType.setDiscountAmount(ObjectFunctions.isNotNullOrEmpty(studentFeeDetailsList)?subQueryObj.get(0)[2].toString():"0");
					studentFeeType.setPaidStatus(studentFee[12].toString());
					subQueryObj = null;
					if(size == studentFeeDetailsList.size()-1){
						if(studentFeeType != null){
							if(studentFeeType.getId() > 0) listStudentFeeTypes.add(studentFeeType);
							studentFeeType = null;
						}
						studentFeeTerm.setFeeTypes(listStudentFeeTypes);
						if(studentFeeTerm != null){
							listStudentFeeTerms.add(studentFeeTerm);
							studentFeeTerm = null;
						}
						studentFeeDetailsVO.setFeeTerms(listStudentFeeTerms);
						studentFeeDetailsVOS.add(studentFeeDetailsVO);
						studentFeeDetailsVO = null;
					}
					size++;
				}
				studentFeeVO.setStudentFeeVOs(studentFeeDetailsVOS);
				return studentFeeVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public StudentTimetableVO getStudentTimetableDetails(long accountId, String type) {
		try {
			StudentTimetableVO studentTimetableVO = null;
			List<StudentTimetableDetailsVO> studentTimetableDetailsVOs = new ArrayList<StudentTimetableDetailsVO>();
			List<String> custIds = null;
			List<String> academicYears = null;
			List<String> classSectionIds = null;
			List<ViewSubstitutionTimeTable> studentTimetableList =null;
			//A- Admin, S- Staff, C- Student, P-Parent
			if(type.equalsIgnoreCase("P")){
				//custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account WHERE parentId = "+accountId);
				//academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
				//classSectionIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM student s WHERE s.academicYearId IN("+academicYears.get(0)+") AND s.accountId IN(SELECT a.id FROM Account a WHERE a.parentId = "+accountId+")");
				classSectionIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM vw_studentClassDetails s WHERE s.parentId="+accountId);
				studentTimetableList =  this.getAll(ViewSubstitutionTimeTable.class, "classSectionId IN("+classSectionIds.get(0)+")  order by classSectionId, dayId");
			}else if(type.equalsIgnoreCase("S")){
				custIds = this.getAll("SELECT GROUP_CONCAT(custId) FROM Account WHERE id = "+accountId);
				academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId = "+custIds.get(0)+" AND status = 'Y';");
				Staff staff = (Staff)this.get(Staff.class,"accountId = "+accountId);
				if(!ObjectFunctions.isNullOrEmpty(staff)){
					studentTimetableList =  this.getAll(ViewSubstitutionTimeTable.class, "teacherId ="+staff.getId()+" and academicYearId="+academicYears.get(0)+" order by classSectionId, dayId");
				}
			}
			else if(type.equalsIgnoreCase("A")){
				custIds = this.getAll("SELECT GROUP_CONCAT(custId) FROM Account WHERE id = "+accountId);
				academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId = "+custIds.get(0)+" AND status = 'Y';");
				studentTimetableList =  this.getAll(ViewSubstitutionTimeTable.class, "academicYearId="+academicYears.get(0)+" order by classSectionId, dayId");
			}
			else if(type.equalsIgnoreCase("C")){
				custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account WHERE id = "+accountId);
				academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
				classSectionIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM student s WHERE s.academicYearId IN("+academicYears.get(0)+") AND s.accountId ="+accountId);
				studentTimetableList =  this.getAll(ViewSubstitutionTimeTable.class, "classSectionId IN("+classSectionIds.get(0)+") order by classSectionId, dayId");
			}
			if(ObjectFunctions.isNotNullOrEmpty(studentTimetableList)){
				studentTimetableVO = new StudentTimetableVO();
				int size=0;
				long dayId =0;
				String periodName="";
				long classSectionId = 0;
				StudentTimetableDetailsVO studentTimetableDetailsVO = new StudentTimetableDetailsVO();
				List<StudentTimetableDayVO> listStudentTimetableDays = new ArrayList<StudentTimetableDayVO>();
				StudentTimetableDayVO timetableDay = null;
				List<StudentTimetablePeriodVO> listStudentTimetablePeriods = new ArrayList<StudentTimetablePeriodVO>();
				StudentTimetablePeriodVO timetablePeriod = null;
				for(ViewSubstitutionTimeTable timetable : studentTimetableList) {
					if(classSectionId != timetable.getClassSectionId()){
						if(studentTimetableDetailsVO != null){
							if(timetablePeriod != null){
								listStudentTimetablePeriods.add(timetablePeriod);
								timetablePeriod = null;
							}
							if(timetableDay != null){
								timetableDay.setTimetablePeriods(listStudentTimetablePeriods);
								listStudentTimetableDays.add(timetableDay);
								timetableDay = null;
								periodName="";
							}
							studentTimetableDetailsVO.setTimetableDays(listStudentTimetableDays);
							studentTimetableDetailsVOs.add(studentTimetableDetailsVO);
							studentTimetableDetailsVO = null; 
						}
						studentTimetableDetailsVO = new StudentTimetableDetailsVO();
						listStudentTimetableDays = new ArrayList<StudentTimetableDayVO>();
						listStudentTimetablePeriods = new ArrayList<StudentTimetablePeriodVO>();
						
						classSectionId = timetable.getClassSectionId();
					}
					studentTimetableDetailsVO.setStudyClassId(timetable.getClassSectionId());
					studentTimetableDetailsVO.setClassSectionName(timetable.getClassAndSection());
					if(dayId != timetable.getDayId()){
						if(timetablePeriod != null){
							listStudentTimetablePeriods.add(timetablePeriod);
							timetablePeriod = null;
						}
						if(ObjectFunctions.isNotNullOrEmpty(listStudentTimetablePeriods)){
							timetableDay.setTimetablePeriods(listStudentTimetablePeriods);
							listStudentTimetablePeriods = new ArrayList<StudentTimetablePeriodVO>();
						}
						if(timetableDay != null){
							listStudentTimetableDays.add(timetableDay);
							timetableDay = null;
							periodName="";
						}
						 timetableDay = new StudentTimetableDayVO();
						 dayId = timetable.getDayId();
					}
					timetableDay.setDay(timetable.getDayName());
					timetableDay.setId(dayId);
					
					if(!periodName.equalsIgnoreCase(timetable.getPeriodName())){
						if(timetablePeriod != null){
							listStudentTimetablePeriods.add(timetablePeriod);
							timetablePeriod = null;
						}
						timetablePeriod = new StudentTimetablePeriodVO();
						periodName = timetable.getPeriodName();
					}
					timetablePeriod.setPeriodName(timetable.getPeriodName());
					timetablePeriod.setSubjectId(timetable.getSubjectId());
					timetablePeriod.setSubjectName(timetable.getSubjectName());
					timetablePeriod.setTeacherId(timetable.getTeacherId());
					timetablePeriod.setTeacherName(timetable.getStaffFullName());
					
					if(size == studentTimetableList.size()-1){
						if(timetablePeriod != null){
							listStudentTimetablePeriods.add(timetablePeriod);
							timetablePeriod = null;
						}
						timetableDay.setTimetablePeriods(listStudentTimetablePeriods);
						if(timetableDay != null){
							listStudentTimetableDays.add(timetableDay);
							timetableDay = null;
						}
						studentTimetableDetailsVO.setTimetableDays(listStudentTimetableDays);
						studentTimetableDetailsVOs.add(studentTimetableDetailsVO);
						studentTimetableDetailsVO = null;
					}
					size++;
				}
				studentTimetableVO.setStudentTimetableVOs(studentTimetableDetailsVOs);
				return studentTimetableVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public StudentLibraryBooksVO getStudentLibraryBooksDetails(long accountId, String type){
		try {
			StudentLibraryBooksVO studentLibraryBooksVO = null;
			List<StudentLibraryBooksDetailsVO> studentLibraryBooksDetailsVOs = new ArrayList<StudentLibraryBooksDetailsVO>();
			List<String> custIds = null;
			List<String> academicYears = null;
			List<String> accountIds = null;
			if(type.equalsIgnoreCase("P")){
				//custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account WHERE parentId = "+accountId);
				custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+accountId);
				academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
				//accountIds = this.getAll("SELECT GROUP_CONCAT(a.id) FROM Account a WHERE a.parentId = "+accountId);
				accountIds = this.getAll("SELECT GROUP_CONCAT(a.id) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = "+accountId);
			}else{
				custIds = this.getAll("SELECT GROUP_CONCAT(custId) FROM Account WHERE id = "+accountId);
				academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId = "+custIds.get(0)+" AND status = 'Y';");
				accountIds = new ArrayList<String>();
				accountIds.add(String.valueOf(accountId));
			}
			List<ViewIssuedBookAndSettings> studentIssuedBooksList =  this.getAll(ViewIssuedBookAndSettings.class, "status = 'P' AND accountId IN("+accountIds.get(0)+") AND academicYearId IN("+academicYears.get(0)+")");
			if(ObjectFunctions.isNotNullOrEmpty(studentIssuedBooksList)){
				studentLibraryBooksVO = new StudentLibraryBooksVO();
				int size=0;
				long studentAccountId = 0;
				long bookId = 0;
				StudentLibraryBooksDetailsVO studentLibraryBooksDetailsVO = null;
				List<StudentIssuedBookDetailsVO> listStudentIssuedBooks = new ArrayList<StudentIssuedBookDetailsVO>();
				StudentIssuedBookDetailsVO issueBook = null;
				for(ViewIssuedBookAndSettings issuedBook : studentIssuedBooksList) {
					if(studentAccountId != issuedBook.getAccountId()){
						if(issueBook != null){
							listStudentIssuedBooks.add(issueBook);
							issueBook = null;
						}
						if(studentLibraryBooksDetailsVO != null){
							studentLibraryBooksDetailsVO.setIssuedBooks(listStudentIssuedBooks);
							studentLibraryBooksDetailsVOs.add(studentLibraryBooksDetailsVO);
							studentLibraryBooksDetailsVO = null;
						}
						studentLibraryBooksDetailsVO = new StudentLibraryBooksDetailsVO();
						listStudentIssuedBooks = new ArrayList<StudentIssuedBookDetailsVO>();
						
						studentAccountId = issuedBook.getAccountId();
					}
					studentLibraryBooksDetailsVO.setStudentAccountId(issuedBook.getAccountId());

					if(bookId != issuedBook.getBookTitleId()){
						if(issueBook != null){
							listStudentIssuedBooks.add(issueBook);
							issueBook = null;
						}
						issueBook = new StudentIssuedBookDetailsVO();
						bookId = issuedBook.getBookTitleId();
					}
					issueBook.setBookId(issuedBook.getBookTitleId());
					issueBook.setBookName(issuedBook.getBookName());
					issueBook.setSubjectId(issuedBook.getSubjectId());
					issueBook.setSubjectName(issuedBook.getName());
					issueBook.setIssuedDate(ObjectFunctions.isNullOrEmpty(issuedBook.getIssuedDate())? "":DateFunctions.convertDateToString(issuedBook.getIssuedDate()));
					issueBook.setDueDate(ObjectFunctions.isNullOrEmpty(issuedBook.getDueDate())? "":DateFunctions.convertDateToString(issuedBook.getDueDate()));
					
					if(size == studentIssuedBooksList.size()-1){
						if(issueBook != null){
							listStudentIssuedBooks.add(issueBook);
							issueBook = null;
						}
						studentLibraryBooksDetailsVO.setIssuedBooks(listStudentIssuedBooks);
						studentLibraryBooksDetailsVOs.add(studentLibraryBooksDetailsVO);
						studentLibraryBooksDetailsVO = null;
					}
					size++;
				}
				studentLibraryBooksVO.setStudentLibraryBooksVOs(studentLibraryBooksDetailsVOs);
				return studentLibraryBooksVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public MeetingSchedulesVO getMeetingSchedules(long accountId, String type){
		try {
			MeetingSchedulesVO meetingSchedulesVO = null;
			List<MeetingSchedulesDetailsVO> meetingSchedulesDetailsVOs = null;
			if("P".equalsIgnoreCase(type))
			{
				//List<String> custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account WHERE parentId = "+accountId);
				//List<String> academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
				//List<String> classSectionIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM student s WHERE s.academicYearId IN("+academicYears.get(0)+") AND s.accountId IN(SELECT a.id FROM Account a WHERE a.parentId = "+accountId+")");
				List<String> classSectionIds = this.getAll("SELECT GROUP_CONCAT(s.classSectionId) FROM vw_studentClassDetails s WHERE s.parentId="+accountId);
				
				meetingSchedulesDetailsVOs = getMeetingSchedulesVOByStudyClassId(classSectionIds.get(0), "P","");
			}
			else if("A".equalsIgnoreCase(type) || "S".equalsIgnoreCase(type))
			{
				meetingSchedulesDetailsVOs = getMeetingSchedulesVOByStudyClassId(String.valueOf(accountId), "S","");
			}
			
			if(ObjectFunctions.isNotNullOrEmpty(meetingSchedulesDetailsVOs)){
				meetingSchedulesVO = new MeetingSchedulesVO();
				meetingSchedulesVO.setMeetingSchedulesVOs(meetingSchedulesDetailsVOs);
			}
			
			return meetingSchedulesVO;			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	private List<MeetingSchedulesDetailsVO> getMeetingSchedulesVOByStudyClassId(String studyClassIds, String type,String meetingScheduleIds){
		List<MeetingSchedulesDetailsVO> meetingSchedulesDetailsVOs = new ArrayList<MeetingSchedulesDetailsVO>();
		List<ViewMeetingSchedules> meetingSchedulesList =null;
		if("P".equalsIgnoreCase(type))
		{
			meetingSchedulesList =  this.getAll(ViewMeetingSchedules.class, "studyClassId IN("+studyClassIds+") AND DATE(date) >= DATE(CURDATE())"+(StringFunctions.isNotNullOrEmpty(meetingScheduleIds)?" AND meetingScheduleId in "+ meetingScheduleIds:""));
		}
		else if("A".equalsIgnoreCase(type) || "S".equalsIgnoreCase(type))
		{
			Staff staff = (Staff)this.get(Staff.class,"accountId="+Long.valueOf(studyClassIds));
			if(!ObjectFunctions.isNullOrEmpty(staff)){
				if(StringFunctions.isNotNullOrEmpty(meetingScheduleIds)){
					meetingSchedulesList =  this.getAll(ViewMeetingSchedules.class, " (staffId = "+staff.getId()+" OR custId="+staff.getCustId()+") AND DATE(date) >= DATE(CURDATE()) AND meetingScheduleId in "+ meetingScheduleIds);
				}else{
					meetingSchedulesList =  this.getAll(ViewMeetingSchedules.class, " (staffId = "+staff.getId()+" OR custId="+staff.getCustId()+")  AND DATE(date) >= DATE(CURDATE())");
				}
			}
		}
		if(ObjectFunctions.isNotNullOrEmpty(meetingSchedulesList)){
			int size=0;
			long meetingId = 0;
			long slotId = 0;
			MeetingSchedulesDetailsVO meetingSchedulesDetailsVO = null;
			List<MeetingScheduleSlotsVO> listMeetingSlots = new ArrayList<MeetingScheduleSlotsVO>();
			MeetingScheduleSlotsVO slot = null;
			for(ViewMeetingSchedules meetingSlot : meetingSchedulesList) {
				if(meetingId != meetingSlot.getMeetingScheduleId()){
					if(slot != null){
						listMeetingSlots.add(slot);
						slot = null;
					}
					if(meetingSchedulesDetailsVO != null){
						meetingSchedulesDetailsVO.setMeetingSlots(listMeetingSlots);
						meetingSchedulesDetailsVOs.add(meetingSchedulesDetailsVO);
						meetingSchedulesDetailsVO = null;
					}
					meetingSchedulesDetailsVO = new MeetingSchedulesDetailsVO();
					listMeetingSlots = new ArrayList<MeetingScheduleSlotsVO>();
					
					meetingId = meetingSlot.getMeetingScheduleId();
				}
				meetingSchedulesDetailsVO.setId(meetingSlot.getMeetingScheduleId());
				meetingSchedulesDetailsVO.setDate(ObjectFunctions.isNullOrEmpty(meetingSlot.getDate())? "":DateFunctions.convertDateToString(meetingSlot.getDate()));
				meetingSchedulesDetailsVO.setDescription(meetingSlot.getDescription());
				meetingSchedulesDetailsVO.setStaffId(meetingSlot.getStaffId());
				meetingSchedulesDetailsVO.setStaffAccountId(meetingSlot.getStaffAccountId());
				meetingSchedulesDetailsVO.setStaffName(meetingSlot.getStaffName());
				meetingSchedulesDetailsVO.setStudyClassId(meetingSlot.getStudyClassId());
				meetingSchedulesDetailsVO.setClassName(meetingSlot.getClassName());
				meetingSchedulesDetailsVO.setSection(meetingSlot.getSection());
				
				if(slotId != meetingSlot.getMeetingSlotId()){
					if(slot != null){
						listMeetingSlots.add(slot);
						slot = null;
					}
					slot = new MeetingScheduleSlotsVO();
					slotId = meetingSlot.getMeetingSlotId();
				}
				slot.setId(meetingSlot.getMeetingSlotId());
				slot.setSlotType(meetingSlot.getSlotType());
				slot.setSlotTime(meetingSlot.getSlotTime());
				slot.setParentAccountId(meetingSlot.getParentAccountId());
				slot.setParentName(StringFunctions.isNotNullOrEmpty(meetingSlot.getParentName())?meetingSlot.getParentName():"");
				
				if(size == meetingSchedulesList.size()-1){
					if(slot != null){
						listMeetingSlots.add(slot);
						slot = null;
					}
					meetingSchedulesDetailsVO.setMeetingSlots(listMeetingSlots);
					meetingSchedulesDetailsVOs.add(meetingSchedulesDetailsVO);
					meetingSchedulesDetailsVO = null;
				}
				size++;
			}
			return meetingSchedulesDetailsVOs;
		}
		return null;
	}
	public boolean reserveMeetingSlot(MeetingScheduleSlotsVO meetingScheduleSlotsVO){
		try{
			if(meetingScheduleSlotsVO.getParentAccountId() >0 && meetingScheduleSlotsVO.getId() > 0){
				MeetingSlots meetingSlot = (MeetingSlots) this.get(MeetingSlots.class, meetingScheduleSlotsVO.getId());
				User account = (User) this.get(User.class, meetingScheduleSlotsVO.getParentAccountId());
				if(!ObjectFunctions.isNullOrEmpty(account) && !ObjectFunctions.isNullOrEmpty(meetingSlot)){
						MeetingSlots existingSlot = (MeetingSlots)this.get(MeetingSlots.class,"meetingScheduleId="+meetingSlot.getMeetingScheduleId()+" and accountId="+meetingScheduleSlotsVO.getParentAccountId());
						if(!ObjectFunctions.isNullOrEmpty(existingSlot)){
							existingSlot.setAccountId(null);
							this.save(existingSlot);
						}
						meetingSlot.setAccountId(account);
						this.save(meetingSlot);
						return true;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return false;
	}
	public boolean meetingScheduleSubmit(MeetingSchedulesMainVO meetingSchedulesMainVO){
		 try{
			 if(meetingSchedulesMainVO.getIdentifier().getAccountId() > 0 && meetingSchedulesMainVO.getIdentifier().getAcademicYearId() > 0){
				 AcademicYear academicYear = (AcademicYear) this.get(AcademicYear.class, meetingSchedulesMainVO.getIdentifier().getAcademicYearId());
				 if(ObjectFunctions.isNullOrEmpty(academicYear))
					 return false;
				 Staff staff = (Staff) this.get(Staff.class, meetingSchedulesMainVO.getStaffId());
				 if(ObjectFunctions.isNullOrEmpty(staff))
					 return false;
				 StudyClass studyClass = (StudyClass) this.get(StudyClass.class, meetingSchedulesMainVO.getStudyClassId());
				 Object[] parentAccountIds =null;
				 long custId=0;
				 if(meetingSchedulesMainVO.getIdentifier().getCustId() > 0){
					 custId= meetingSchedulesMainVO.getIdentifier().getCustId();
				 }else{
					 custId= staff.getCustId();
				 }
				 String passingVal;
				 if(ObjectFunctions.isNullOrEmpty(studyClass)){
					 passingVal=String.valueOf(meetingSchedulesMainVO.getIdentifier().getAccountId());
					 parentAccountIds = this.get( "SELECT GROUP_CONCAT(accountId),'' FROM vw_staffDetails WHERE roleId IN(2, 8, 12, 31, 35) AND custId = "+custId);
				 }else{
					 passingVal=String.valueOf(meetingSchedulesMainVO.getStudyClassId());
					 //parentAccountIds = this.get("SELECT GROUP_CONCAT(parentId),'' FROM Account WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+studyClass.getId()+")");
					 parentAccountIds = this.get("SELECT GROUP_CONCAT(sp.parentAccountId),'' FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+studyClass.getId()+")");
				 }
				 List<User> parentsList = this.getAll(User.class,"id IN("+parentAccountIds[0].toString()+")");
				 int mainParents = parentsList.size();
				 int varParents = mainParents;
				 Calendar fromDate = Calendar.getInstance();
				 fromDate.setTime(meetingSchedulesMainVO.getFromDate());
				 Calendar toDate = Calendar.getInstance();
				 toDate.setTime(meetingSchedulesMainVO.getToDate());
				 long slotDuration = 60/meetingSchedulesMainVO.getParentsPerHour();
				 StringBuffer meetingScheduleIds = new StringBuffer("(");
				 for(Date date = fromDate.getTime(); fromDate.before(toDate);fromDate.add(Calendar.DATE, 1),date=fromDate.getTime()){
					 Set<MeetingSlots> meetingSlotsList = new HashSet<MeetingSlots>();
					 MeetingSchedules meetingSchedules = new MeetingSchedules();
					 meetingSchedules.setDate(date);
					 meetingSchedules.setDescription(meetingSchedulesMainVO.getDescription());
					 meetingSchedules.setSlotDuration((int) slotDuration);
					 /*meetingSchedules.setMorningSlotCount(meetingSchedulesMainVO.getMorningSlotCount());
					 meetingSchedules.setAfternoonSlotCount(meetingSchedulesMainVO.getAfternoonSlotCount());*/
					 meetingSchedules.setStaff(staff);
					 meetingSchedules.setCustId(custId);
					 meetingSchedules.setStudyClass(studyClass);
					 meetingSchedules.setIsAuto(meetingSchedulesMainVO.getIsAuto());
					 //Get school start time to for slots.
					 DateFormat formatter = new SimpleDateFormat("hh:mm aa");
					 Date startTime = formatter.parse(StringFunctions.isNotNullOrEmpty(academicYear.getStartTime())?academicYear.getStartTime():"09:00 AM");
					 Date endTime = formatter.parse(StringFunctions.isNotNullOrEmpty(academicYear.getEndTime())?academicYear.getEndTime():"04:00 PM");
					 Calendar calStart = Calendar.getInstance();
					 calStart.setTime(startTime);
					 Calendar calEnd = Calendar.getInstance();
					 calEnd.setTime(endTime);
					 
					 for(int i=0; i < mainParents; i++){
						 if(varParents == 0 || calStart.getTime().after(calEnd.getTime()))
							 break;
						 
						 MeetingSlots meetingSlot = new MeetingSlots();
						 meetingSlot.setCustId(custId);
						 meetingSlot.setSlotTime(formatter.format(calStart.getTime()));
						 meetingSlot.setSlotType(formatter.format(calStart.getTime()).contains("AM")?"M":"A");
						 User user = (User) parentsList.get(varParents-1);
						 if(meetingSchedulesMainVO.getIsAuto().equalsIgnoreCase("Y"))
							 meetingSlot.setAccountId(user);
						 meetingSlotsList.add(meetingSlot);
						 user = null;
						 calStart.add(Calendar.MINUTE, (int) slotDuration);
						 varParents--;
					 }
					 meetingSchedules.setMeetingSlots(meetingSlotsList);
					 meetingSchedules= (MeetingSchedules) this.save(meetingSchedules);
					 meetingScheduleIds.append(meetingSchedules.getId());
					 meetingScheduleIds.append(",");
					 if(varParents <= 0)
						 break;
				 }
				 meetingScheduleIds.append("0)");
				 
				 StringBuffer accountIdbuffer = new StringBuffer("(");
				 accountIdbuffer.append(parentAccountIds[0].toString());
				 accountIdbuffer.append(")");
				 String message = "Staff parent meeting schedule added.";
				 List<MeetingSchedulesDetailsVO> meetingSchedulesDetailsList = getMeetingSchedulesVOByStudyClassId(String.valueOf(passingVal), ObjectFunctions.isNullOrEmpty(studyClass)? "S": "P", meetingScheduleIds.toString());
				 if (ObjectFunctions.isNotNullOrEmpty(meetingSchedulesDetailsList)){
						JSONObject main = new JSONObject();
						JSONObject subVal = new JSONObject();
						main.put("notificationFor", "StaffParentMeetingSchedule");
						subVal.put("description", message);
						subVal.put("title", message);
						subVal.put("studyClassId", meetingSchedulesMainVO.getStudyClassId());
						GsonBuilder gsonBuilder = new GsonBuilder(); gsonBuilder.serializeNulls(); 
						Gson gson = gsonBuilder.create();
						subVal.put("meetingSchedulesVOs",new JSONArray(gson.toJson(meetingSchedulesDetailsList)));
						//subVal.put("meetingSchedulesVOs", meetingSchedulesDetailsList);
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
					}
				 
				 return true;
			 } 
		 }catch(Exception ex){
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		 }
		 return false;
	 }
	public RouteMainVO getAllRoutesForDriver(long accountId){
		try{
			RouteMainVO routeMainVO = new RouteMainVO();
			
			List<String> custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account WHERE id = "+accountId);
			List<String> academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
			List<VehiclesAcademicDetails> vehicleAcademicsList = this.getAll(VehiclesAcademicDetails.class, "driverId = "+accountId+" AND academicYearId = "+academicYears.get(0));
			if(!ObjectFunctions.isNullOrEmpty(vehicleAcademicsList)){
				List<RouteVo> listRouteVo = new ArrayList<RouteVo>();
				for(VehiclesAcademicDetails vehicleAcademic : vehicleAcademicsList){
					Set<Route> routes = vehicleAcademic.getRoute();
					for(Route route : routes){
						RouteVo routeVo = new RouteVo();
						routeVo.setId(route.getId());
						routeVo.setRoutePointName(route.getRoutePointName());
						routeVo.setRouteEndName(route.getRouteEndName());
						routeVo.setRouteName(route.getRouteName());
						routeVo.setRoutePointStartTime(route.getRoutePointStartTime());
						routeVo.setRoutePointEndTime(route.getRoutePointEndTime());
						routeVo.setRoutePointReturnStartTime(route.getRoutePointReturnStartTime());
						routeVo.setRoutePointReturnEndTime(route.getRoutePointReturnEndTime());
						routeVo.setRoutePointFeeAmount(route.getRoutePointFeeAmount());
						routeVo.setRoutePoints(route.getRoutePoints());
						routeVo.setStatus(route.getStatus());
						routeVo.setDriverId(accountId);
						routeVo.setRouteBoardingPointsVoList(getRouteBoardingPointsListByRouteId(route.getId()));
						
						listRouteVo.add(routeVo);
						routeVo = null;
					}
				}
				routeMainVO.setRouteVo(listRouteVo);
				
				return routeMainVO;
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	private List<RouteBoardingPointsVO> getRouteBoardingPointsListByRouteId(long routeId)
	{
		List<RouteBoardingPointsVO> routeBoardingPointsVOList = new ArrayList<RouteBoardingPointsVO>();
		List<RouteBoardingPoints> routeBoardingPointsList = this.getAll(RouteBoardingPoints.class,"routeId ="+routeId);
		if(!ObjectFunctions.isNullOrEmpty(routeBoardingPointsList))
			for(RouteBoardingPoints routeBoardingPoints : routeBoardingPointsList){
				RouteBoardingPointsVO routeBoardingPointsVO = routeBoardingPoints.copyFromEntityToVo(routeBoardingPoints);
				routeBoardingPointsVOList.add(routeBoardingPointsVO);
			}
		return routeBoardingPointsVOList;
	}
	public boolean updateRoutePoints(RouteVo routeVo){
		try{
			if(routeVo.getId() >0){
				Route route = (Route) this.get(Route.class, routeVo.getId());
				if(!ObjectFunctions.isNullOrEmpty(route)){
					route.setRoutePoints(routeVo.getRoutePoints());
					this.save(route);
					return true;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return false;
	}
	public boolean saveRouteTrack(RouteTrackVO routeTrackVO){
		try{
			if(routeTrackVO.getRouteId() >0 && routeTrackVO.getDriverId() > 0){
				Route route = (Route) this.get(Route.class, routeTrackVO.getRouteId());
				User user = (User) this.get(User.class, routeTrackVO.getDriverId());
				if(!ObjectFunctions.isNullOrEmpty(route) && !ObjectFunctions.isNullOrEmpty(user)){
					RouteTrack routeTrack = new RouteTrack();
					
					routeTrack.setLatitude(routeTrackVO.getLatitude());
					routeTrack.setLongitude(routeTrackVO.getLongitude());
					routeTrack.setAddress(routeTrackVO.getAddress());
					routeTrack.setTime(StringFunctions.isNotNullOrEmpty(routeTrackVO.getTime())?DateFunctions.convertStringToDate(routeTrackVO.getTime()):new Date());
					routeTrack.setSpeed(routeTrackVO.getSpeed());
					routeTrack.setDriver(user);
					routeTrack.setRoute(route);
					
					this.save(routeTrack);
					return true;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return false;
	}
	public ViewRouteTrackVO getVehicleLastLocation(long driverId, long routeId){
		try{
			ViewRouteTrack routeTrack =(ViewRouteTrack) this.get(ViewRouteTrack.class, "routeId = "+routeId+" AND driverId = "+driverId+" ORDER BY id DESC LIMIT 1");
			if(!ObjectFunctions.isNullOrEmpty(routeTrack)){
				ViewRouteTrackVO routeTrackVO = new ViewRouteTrackVO();
				
				routeTrackVO.setId(routeTrack.getId());
				routeTrackVO.setLatitude(routeTrack.getLatitude());
				routeTrackVO.setLongitude(routeTrack.getLongitude());
				routeTrackVO.setAddress(routeTrack.getAddress());
				routeTrackVO.setTime(ObjectFunctions.isNullOrEmpty(routeTrack.getTime())? "":DateFunctions.convertDateToString(routeTrack.getTime()));
				routeTrackVO.setSpeed(routeTrack.getSpeed());
				routeTrackVO.setRouteId(routeTrack.getRouteId());
				routeTrackVO.setRouteName(routeTrack.getRouteName());
				routeTrackVO.setDriverId(routeTrack.getDriverId());
				routeTrackVO.setDriverName(routeTrack.getDriverName());
				routeTrackVO.setVehicleId(routeTrack.getVehicleId());
				
				return routeTrackVO;
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public ViewRouteTrackMainVO getAllRouteVehiclesLocations(long accountId){
		try{
			ViewRouteTrackMainVO routeTrackMainVO = new ViewRouteTrackMainVO();
			
			List<String> custIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(custId)) FROM Account WHERE id = "+accountId);
			List<String> academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId IN("+custIds.get(0)+") AND status = 'Y';");
			List<String> vehicleIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(vehicleId)) FROM vehiclesAcademicDetails WHERE academicYearId IN("+academicYears.get(0)+");");
			List<Object[]> routeTracksList = this.getAll("SELECT * FROM (SELECT * FROM vw_routeTrack WHERE vehicleId IN("+vehicleIds.get(0)+") ORDER BY id DESC)s GROUP BY vehicleId;");
			if(!ObjectFunctions.isNullOrEmpty(routeTracksList)){
				List<ViewRouteTrackDetailsVO> listRouteTrackVo = new ArrayList<ViewRouteTrackDetailsVO>();
				for(Object[] routeTrack : routeTracksList){
					ViewRouteTrackDetailsVO routeTrackVO = new ViewRouteTrackDetailsVO();
					
					routeTrackVO.setId(Long.valueOf(routeTrack[0].toString()));
					routeTrackVO.setLatitude(Double.valueOf(routeTrack[1].toString()));
					routeTrackVO.setLongitude(Double.valueOf(routeTrack[2].toString()));
					routeTrackVO.setAddress(routeTrack[3].toString());
					routeTrackVO.setTime(ObjectFunctions.isNullOrEmpty(routeTrack[4].toString())? "":routeTrack[4].toString());
					routeTrackVO.setSpeed(Double.valueOf(routeTrack[5].toString()));
					routeTrackVO.setRouteId(Long.valueOf(routeTrack[6].toString()));
					routeTrackVO.setRouteName(routeTrack[7].toString());
					routeTrackVO.setDriverId(Long.valueOf(routeTrack[8].toString()));
					routeTrackVO.setDriverName(routeTrack[9].toString());
					routeTrackVO.setVehicleId(Long.valueOf(routeTrack[10].toString()));
					
					listRouteTrackVo.add(routeTrackVO);
					routeTrackVO = null;
				}
				routeTrackMainVO.setViewRouteTrackDetailsVOs(listRouteTrackVo);
				
				return routeTrackMainVO;
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public boolean sendBusPickupDropNotification(long driverId, long routeId, String type){
		try{
			if(StringFunctions.isNotNullOrEmpty(type)){
				List<Long> notificationAccountIds = new ArrayList<Long>();
				JSONObject mainObj = new JSONObject();
				JSONObject infoObj = new JSONObject();
				JSONObject routeNotifyVOObj = new JSONObject();				
				User loggedUser = (User) this.get(User.class, driverId);
				if(ObjectFunctions.isNullOrEmpty(loggedUser))return false;
				Customer customer = this.getCustomerByCustId(loggedUser.getCustId());
				AcademicYear academicYear = (AcademicYear)this.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				//if(!(customer.isCheckMobileService() && customer.isCheckTransportService()))return false;
				Date date = new Date();
				Messages objMsg = new Messages();
				objMsg.setCreatedById(driverId);
				objMsg.setCreatedDate(date);
				objMsg.setLastAccessDate(date);
				objMsg.setLastUpdatedDate(date);
				objMsg.setStatus("A");
				objMsg.setSenderName(loggedUser.getUserRoleDescription());
				objMsg.setAcademicYear(academicYear);
				objMsg.setCustomer(customer);
				objMsg.setSmsSenderId(objMsg.getCustomer().getSender());
				
				loggedUser=null;
				if (!ObjectFunctions.isNullOrEmpty(customer)) 
				objMsg.setPurposeType("regd: Sending bus pickup/drop notifications to parent/admin");
				if(type.equalsIgnoreCase("P")){
					objMsg.setMessageDescription("Dear Parent, Bus has departed from school to "+ (new SimpleDateFormat("hh:mm aa").format(date).contains("AM")? "pick up": "drop") + " students from ");
				}else{
					if(new SimpleDateFormat("hh:mm aa").format(date).contains("AM"))
						objMsg.setMessageDescription("Dear Parent, Bus has reached the school and dropped students in school from ");
				}
				Set<String> mobileNumbersSet = new HashSet<String>();
				//Get parent mobile numbers..
				List<String> boardingPoints = this.getAll("SELECT GROUP_CONCAT(id) FROM routeBoardingPoints WHERE routeId = "+ routeId);
				List<Student> studentsList = this.getAll(Student.class, "boardingPointId IN("+ boardingPoints.get(0) +")");
				for(Student student :studentsList){
					if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent())){
						if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getStudentParent().getId())) notificationAccountIds.add(student.getAccount().getStudentParent().getId());
						mobileNumbersSet.addAll(this.addMobileNumbersBasedOnAddressType(customer.getMobileType(),student.getAccount().getPerson().getMobileNumber(),student.getAccount().getPerson().getSecondaryMobileNumber(),student.getAccount().getPerson().getAnotherMobileNumber(),student.getAccount().getPerson().getAnotherSecondaryMobileNumber(),student.getAccount().getPerson().getAddressType()));
						objMsg = this.saveMessageDetailsTracking(objMsg, student, null, null);
					}
				}
				if(notificationAccountIds.size() > 0){
					mainObj.put("notificationFor", "");
					
					infoObj.put("title", objMsg.getMessageDescription());
					infoObj.put("description", objMsg.getMessageDescription());
					mainObj.put("information", infoObj);
					
					sendNotificationToAndroidMobileUsersByUserIds(0, mainObj.toString(), "("+StringFunctions.convertListToCommaDelimitedString(notificationAccountIds)+")");
					notificationAccountIds.clear();
				}
				if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet) && customer.isCheckMobileService() && customer.isCheckTransportService()) {
					try {
						log.debug("mobileNumbersSet :"+mobileNumbersSet);
						this.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
					} catch (Exception e) { e.printStackTrace(); }
				}
				mobileNumbersSet = null;
				mobileNumbersSet = new HashSet<String>();
				VehiclesAcademicDetails vehicleAcademicDetails = (VehiclesAcademicDetails) this.get(VehiclesAcademicDetails.class, "driverId = " + driverId);
				String busNo = "1";
				if(!ObjectFunctions.isNullOrEmpty(vehicleAcademicDetails))
					if(!ObjectFunctions.isNullOrEmpty(vehicleAcademicDetails.getVehicle()))
							busNo = vehicleAcademicDetails.getVehicle().getVehicleNumber();
				
				if(type.equalsIgnoreCase("P")){
					if(new SimpleDateFormat("hh:mm aa").format(date).contains("AM"))
						objMsg.setMessageDescription("Dear Admin, Bus No: "+ busNo +" has departed from school to pick up students from ");
					else
						objMsg.setMessageDescription("Dear Admin, Bus No: "+ busNo +" has departed from school to drop students from ");
				}else{
					if(new SimpleDateFormat("hh:mm aa").format(date).contains("AM"))
						objMsg.setMessageDescription("Dear Admin, Bus no: "+ busNo +" has reached the school and dropped students in school from ");
					else
						objMsg.setMessageDescription("Dear Admin, Bus no: "+ busNo +" has reached back to school after dropping students from ");
				}
				//Add Admin/Transport Admin mobile numbers.
				List<String> accountIds = this.getAll("SELECT GROUP_CONCAT(accountId) FROM vw_allUsers WHERE custId = "+customer.getId()+" AND roleId in(1,10)");
				List<User> adminsList = this.getAll(User.class, "id IN("+ accountIds.get(0) +")");
				if(ObjectFunctions.isNotNullOrEmpty(adminsList))
					for(User user:adminsList)
						if(StringFunctions.isNotNullOrEmpty(user.getPerson().getMobileNumber())){
							notificationAccountIds.add(user.getId());
							mobileNumbersSet.add(user.getPerson().getMobileNumber());
							objMsg = this.saveMessageDetailsTracking(objMsg, null, null, user);
						}
				
				if(notificationAccountIds.size() > 0){
					mainObj.put("notificationFor", "");
					
					infoObj.put("title", "Bus no:"+ busNo + " transport notification");
					infoObj.put("description", objMsg.getMessageDescription());
					mainObj.put("information", infoObj);
					
					sendNotificationToAndroidMobileUsersByUserIds(0, mainObj.toString(), "("+StringFunctions.convertListToCommaDelimitedString(notificationAccountIds)+")");
					notificationAccountIds.clear();
				}
				
				if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet) && customer.isCheckMobileService() && customer.isCheckTransportService()) {
					try {
						log.debug("mobileNumbersSet :"+mobileNumbersSet);
						this.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
					} catch (Exception e) { e.printStackTrace(); }
				}
				mobileNumbersSet = null;
				
				return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return false;
	}
	public VehicleRouteMainVO getAllRoutes(long custId){
		try{
			VehicleRouteMainVO vehicleRouteMainVO = new VehicleRouteMainVO();
			
			List<String> academicYears = this.getAll("SELECT GROUP_CONCAT(id) FROM academicYear WHERE custId ="+ custId +" AND status = 'Y';");
			List<VehiclesAcademicDetails> vehicleAcademicsList = this.getAll(VehiclesAcademicDetails.class, "academicYearId = "+academicYears.get(0));
			if(!ObjectFunctions.isNullOrEmpty(vehicleAcademicsList)){
				List<VehicleRouteVo> listVehicleRouteVo = new ArrayList<VehicleRouteVo>();
				for(VehiclesAcademicDetails vehicleAcademic : vehicleAcademicsList){
					Set<Route> routes = vehicleAcademic.getRoute();
					List<RouteVo> listRouteVo = new ArrayList<RouteVo>();
					VehicleRouteVo vehicleRouteVo = new VehicleRouteVo();
					VehicleVO vehicleVo = new VehicleVO();
					
					for(Route route : routes){
						RouteVo routeVo = new RouteVo();
						routeVo.setId(route.getId());
						routeVo.setRoutePointName(route.getRoutePointName());
						routeVo.setRouteEndName(route.getRouteEndName());
						routeVo.setRouteName(route.getRouteName());
						routeVo.setRoutePointStartTime(route.getRoutePointStartTime());
						routeVo.setRoutePointEndTime(route.getRoutePointEndTime());
						routeVo.setRoutePointReturnStartTime(route.getRoutePointReturnStartTime());
						routeVo.setRoutePointReturnEndTime(route.getRoutePointReturnEndTime());
						routeVo.setRoutePointFeeAmount(route.getRoutePointFeeAmount());
						routeVo.setRoutePoints(route.getRoutePoints());
						routeVo.setStatus(route.getStatus());
						routeVo.setDriverId(vehicleAcademic.getDriverId());
						routeVo.setRouteBoardingPointsVoList(getRouteBoardingPointsListByRouteId(route.getId()));
						
						listRouteVo.add(routeVo);
						routeVo = null;
					}
					vehicleVo.setId(vehicleAcademic.getVehicle().getId());
					vehicleVo.setVehicleType(vehicleAcademic.getVehicle().getVehicleType());
					vehicleVo.setVehicleNumber(vehicleAcademic.getVehicle().getVehicleNumber());
					vehicleVo.setNoOfSeats(vehicleAcademic.getVehicle().getNoOfSeats());
					vehicleVo.setInsuranceNumber(vehicleAcademic.getVehicle().getInsuranceNumber());
					vehicleVo.setClassificationType(vehicleAcademic.getVehicle().getClassificationType());
					vehicleVo.setOwnerName(vehicleAcademic.getVehicle().getOwnerName());
					vehicleVo.setChasisNumber(vehicleAcademic.getVehicle().getChasisNumber());
					vehicleVo.setEngineNumber(vehicleAcademic.getVehicle().getEngineNumber());
					vehicleVo.setAvailableStudent(vehicleAcademic.getVehicle().getAvailableStudent());
					vehicleVo.setEnRolledStudent(vehicleAcademic.getVehicle().getEnRolledStudent());
					vehicleVo.setTotalStudent(vehicleAcademic.getVehicle().getTotalStudent());
					vehicleVo.setRegistrationAuthority(vehicleAcademic.getVehicle().getRegistrationAuthority());
					vehicleVo.setInsuranceDetails(vehicleAcademic.getVehicle().getInsuranceDetails());
					vehicleVo.setPermitNumber(vehicleAcademic.getVehicle().getPermitNumber());
					
					vehicleRouteVo.setVehicleVo(vehicleVo);
					vehicleRouteVo.setRouteVos(listRouteVo);
					vehicleRouteVo.setName(vehicleAcademic.getName());
					vehicleRouteVo.setId(vehicleAcademic.getId());
					
					listVehicleRouteVo.add(vehicleRouteVo);
					vehicleRouteVo = null;
					vehicleVo = null;
					listRouteVo = null;
				}
				vehicleRouteMainVO.setVehicleRouteVo(listVehicleRouteVo);
				
				return vehicleRouteMainVO;
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	@Transactional
	public void updateAndGetTopStudentsMarksDetails(long custId, long academicYearId){
		 adminDao.updateAndGetTopStudentsMarksDetails(custId,academicYearId);
	}
	@Transactional
	public void updateAndGetTopStaffPerformanceDetails(long custId, long academicYearId){
		 adminDao.updateAndGetTopStaffPerformanceDetails(custId,academicYearId);
	}
	@Transactional
	public void updateAndGetTopStudentsMonthlyAttendanceDetails(long custId,long academicYearId){
		 adminDao.updateAndGetTopStudentsMonthlyAttendanceDetails(custId,academicYearId);
	}
	@Transactional
	public void updateAndGetTopStudentsDailyAttendanceDetails(long custId,long academicYearId){
		 adminDao.updateAndGetTopStudentsDailyAttendanceDetails(custId,academicYearId);
	}

	public void updateStudentPaymentStatusForSchoolFee(long classId,long categoryId){
		Fee fee =(Fee)adminDao.get(Fee.class, "classId="+classId+" and categoryId="+categoryId+" and feeAmount != 0");
		if(!ObjectFunctions.isNullOrEmpty(fee)){
			/* If we update any or create any fee particular we will update feeConfigured column as "Y" and we will update student feePaidStatus as "P". Who are have "F" status in feePaidStatus */
			adminDao.updateFeeConfiguredStatusInStudent(classId,categoryId,"Y");
			/* In class Fee edit if we create/edit any new fee to class we will update as "P" who are paid fully paid students. That means who are have "F" status */
			adminDao.updateStudentPaymentStatus(classId,categoryId,"P");
		}else {
			adminDao.updateStudentPaymentAndConfifuredStatusForSchoolFee(classId,categoryId,"N");
		}
	}
	
	public boolean runPaySlipsReports(PayslipBaseVO payslipBaseVO){
		try {
			if(!ObjectFunctions.isNullOrEmpty(payslipBaseVO.getPaySlips()))
			{
				/*Process p = null;
           		p = Runtime.getRuntime().exec("sh /etc/init.d/payslips.sh");
           		p.waitFor();*/
           		PaySlip paySlips = null;
           		StaffMonthlyPaySlips staffMonthlyPaySlips = null;
           		paySlips = (PaySlip)this.get(PaySlip.class,"custId="+payslipBaseVO.getIdentifier().getCustId()+" and monthId="+payslipBaseVO.getMonth()+" and yearName="+payslipBaseVO.getYear());
           		if(ObjectFunctions.isNullOrEmpty(paySlips))
           			paySlips = new PaySlip();
           		paySlips.setYearName(payslipBaseVO.getYear());
           		paySlips.setCustId(payslipBaseVO.getIdentifier().getCustId());
           		paySlips.setMonthId(payslipBaseVO.getMonth());
           		paySlips.setMonthName(DateFunctions.getMonthFullName(payslipBaseVO.getMonth()-1));
           		paySlips.setCreatedById(0);
           		paySlips.setCreatedDate(new Date());
           		paySlips.setLastAccessDate(new Date());
           		paySlips.setLastUpdatedById(0);
           		paySlips.setLastUpdatedDate(new Date());
           		for(PayslipVO payslipVO : payslipBaseVO.getPaySlips())
           		{
               		staffMonthlyPaySlips = (StaffMonthlyPaySlips)this.get(StaffMonthlyPaySlips.class,"custId="+payslipBaseVO.getIdentifier().getCustId()+" and month="+payslipBaseVO.getMonth()+" and accountId="+Long.valueOf(payslipVO.getAccountId()));
               		if(ObjectFunctions.isNullOrEmpty(staffMonthlyPaySlips))
               			staffMonthlyPaySlips = new StaffMonthlyPaySlips();
               		staffMonthlyPaySlips.setAccountId(payslipVO.getAccountId());
               		staffMonthlyPaySlips.setCustId(payslipBaseVO.getIdentifier().getCustId());
               		staffMonthlyPaySlips.setMonth(payslipBaseVO.getMonth());
               		staffMonthlyPaySlips.setFilePath(payslipVO.getFilePath());
               		paySlips.addStaffMonthlyPySlips(staffMonthlyPaySlips);
                }
           		this.merge(paySlips);
           		/*String filePath = "/home/eazyschool/payslip/" + custId+".zip";
           		log.debug("filePath :"+filePath);
           		File file = new File(filePath);
           		if(file.exists()){
           			FileUtils.deleteQuietly(file);
           			log.debug("Run CCE file exist and deleted.");
           			
           		}else
           			log.debug("Run CCE file not exist (or) not found, this code not worked out....");*/
           		return true;
			}	
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return true;
	}
	@Transactional
	public List<Object[]> getCustomerFeePaidAndUnpaiddetailSummary(){
		return adminDao.getCustomerFeePaidAndUnpaiddetailSummary();
	}
	public boolean notifyNearBusStop(RouteNotifyVO routeNotifyVO){
		try{
			if(!ObjectFunctions.isNullOrEmpty(routeNotifyVO)){
				if(routeNotifyVO.getRouteBoardingPointId()>0){
					List<String> studentAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM student WHERE status = 'Y' AND boardingPointId = "+routeNotifyVO.getRouteBoardingPointId());
					//List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(parentId) FROM Account WHERE id IN("+studentAccountIds.get(0)+");");
					List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(parentAccountId) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN("+studentAccountIds.get(0)+");");
					
					JSONObject mainObj = new JSONObject();
					JSONObject infoObj = new JSONObject();
					JSONObject routeNotifyVOObj = new JSONObject();
					mainObj.put("notificationFor", "NotifyNearBusStop");
					
					infoObj.put("title", "School bus reaching your stop");
					infoObj.put("description", "School bus reaching your stop");
					
					routeNotifyVOObj.put("driverId", routeNotifyVO.getDriverId());
					routeNotifyVOObj.put("routeId", routeNotifyVO.getRouteId());
					routeNotifyVOObj.put("routeBoardingPointId", routeNotifyVO.getRouteBoardingPointId());
					routeNotifyVOObj.put("routeLatitude", routeNotifyVO.getRouteLatitude());
					routeNotifyVOObj.put("routeLongitude", routeNotifyVO.getRouteLongitude());
					routeNotifyVOObj.put("routeAddress", routeNotifyVO.getRouteAddress());
					routeNotifyVOObj.put("currentLatitude", routeNotifyVO.getCurrentLatitude());
					routeNotifyVOObj.put("currentLongitude", routeNotifyVO.getCurrentLongitude());
					routeNotifyVOObj.put("currentAddress", routeNotifyVO.getCurrentAddress());
					routeNotifyVOObj.put("currentSpeed", routeNotifyVO.getCurrentSpeed());
					
					infoObj.put("routeNotifyVO", routeNotifyVOObj);
					mainObj.put("information", infoObj);
					
					sendNotificationToAndroidMobileUsersByUserIds(0, mainObj.toString(), "("+parentAccountIds.get(0)+")");
					
					return true;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return false;
	}
	
	public VehicleRouteMainVO getAllRoutesByAccountIdAndType(long accountId, String type)
	{
		try{
			VehicleRouteMainVO vehicleRouteMainVO = new VehicleRouteMainVO();
			if(accountId > 0 && !StringFunctions.isNullOrEmpty(type)){
				User curUser= (User)this.get(User.class,accountId);
				if(!ObjectFunctions.isNullOrEmpty(curUser))
				{
					AcademicYear academicYear  = super.getCurrentAcademicYear("Y",curUser.getCustId());
					if (!ObjectFunctions.isNullOrEmpty(academicYear)) 
					{
						List<String> vehicleDetails = null;
						// A - Admin & S - Staff & C - Student & P - Parent
						if(type.equalsIgnoreCase("A"))
							vehicleDetails = this.getAll("SELECT GROUP_CONCAT(vehicleAcademicDetailsId) FROM vw_studentDetails WHERE academicYearId = "+ academicYear.getId());
							
						else if(type.equalsIgnoreCase("S"))
							vehicleDetails =  this.getAll("SELECT GROUP_CONCAT(vehicleAcademicDetailsId) FROM vw_studentDetails vs WHERE vs.academicYearId = "+ academicYear.getId() + " AND vs.classSectionId IN(SELECT ct.studyClassId FROM classTeacher ct WHERE ct.academicYearId = "+ academicYear.getId() +" AND ct.teacherId in (select s.id from staff s where s.accountId= "+ accountId +"))");
						
						else if(type.equalsIgnoreCase("C"))
							vehicleDetails =  this.getAll("SELECT GROUP_CONCAT(vehicleAcademicDetailsId) FROM vw_studentDetails vs WHERE vs.academicYearId = "+ academicYear.getId() + " AND vs.accountId = "+ accountId);
						
						else if(type.equalsIgnoreCase("P"))
							vehicleDetails =  this.getAll("SELECT GROUP_CONCAT(vehicleAcademicDetailsId) FROM vw_studentDetails vs WHERE vs.parentId = "+ accountId + " AND vs.status = 'Y'");
						
						else return null;
					
						if(!ObjectFunctions.isNullOrEmpty(vehicleDetails))
						{
							List<VehiclesAcademicDetails> vehicleAcademicsList = this.getAll(VehiclesAcademicDetails.class, "id in( "+vehicleDetails.get(0)+")");
							if(!ObjectFunctions.isNullOrEmpty(vehicleAcademicsList)){
								List<VehicleRouteVo> listVehicleRouteVo = new ArrayList<VehicleRouteVo>();
								for(VehiclesAcademicDetails vehicleAcademic : vehicleAcademicsList){
									Set<Route> routes = vehicleAcademic.getRoute();
									List<RouteVo> listRouteVo = new ArrayList<RouteVo>();
									VehicleRouteVo vehicleRouteVo = new VehicleRouteVo();
									VehicleVO vehicleVo = new VehicleVO();
									
									for(Route route : routes){
										RouteVo routeVo = new RouteVo();
										routeVo.setId(route.getId());
										routeVo.setRoutePointName(route.getRoutePointName());
										routeVo.setRouteEndName(route.getRouteEndName());
										routeVo.setRouteName(route.getRouteName());
										routeVo.setRoutePointStartTime(route.getRoutePointStartTime());
										routeVo.setRoutePointEndTime(route.getRoutePointEndTime());
										routeVo.setRoutePointReturnStartTime(route.getRoutePointReturnStartTime());
										routeVo.setRoutePointReturnEndTime(route.getRoutePointReturnEndTime());
										routeVo.setRoutePointFeeAmount(route.getRoutePointFeeAmount());
										routeVo.setRoutePoints(route.getRoutePoints());
										routeVo.setStatus(route.getStatus());
										routeVo.setDriverId(vehicleAcademic.getDriverId());
										routeVo.setRouteBoardingPointsVoList(getRouteBoardingPointsListByRouteId(route.getId()));
										
										listRouteVo.add(routeVo);
										routeVo = null;
									}
									vehicleVo.setId(vehicleAcademic.getVehicle().getId());
									vehicleVo.setVehicleType(vehicleAcademic.getVehicle().getVehicleType());
									vehicleVo.setVehicleNumber(vehicleAcademic.getVehicle().getVehicleNumber());
									vehicleVo.setNoOfSeats(vehicleAcademic.getVehicle().getNoOfSeats());
									vehicleVo.setInsuranceNumber(vehicleAcademic.getVehicle().getInsuranceNumber());
									vehicleVo.setClassificationType(vehicleAcademic.getVehicle().getClassificationType());
									vehicleVo.setOwnerName(vehicleAcademic.getVehicle().getOwnerName());
									vehicleVo.setChasisNumber(vehicleAcademic.getVehicle().getChasisNumber());
									vehicleVo.setEngineNumber(vehicleAcademic.getVehicle().getEngineNumber());
									vehicleVo.setAvailableStudent(vehicleAcademic.getVehicle().getAvailableStudent());
									vehicleVo.setEnRolledStudent(vehicleAcademic.getVehicle().getEnRolledStudent());
									vehicleVo.setTotalStudent(vehicleAcademic.getVehicle().getTotalStudent());
									vehicleVo.setRegistrationAuthority(vehicleAcademic.getVehicle().getRegistrationAuthority());
									vehicleVo.setInsuranceDetails(vehicleAcademic.getVehicle().getInsuranceDetails());
									vehicleVo.setPermitNumber(vehicleAcademic.getVehicle().getPermitNumber());
									
									vehicleRouteVo.setVehicleVo(vehicleVo);
									vehicleRouteVo.setRouteVos(listRouteVo);
									vehicleRouteVo.setName(vehicleAcademic.getName());
									vehicleRouteVo.setId(vehicleAcademic.getId());
									
									listVehicleRouteVo.add(vehicleRouteVo);
									vehicleRouteVo = null;
									vehicleVo = null;
									listRouteVo = null;
								}
								vehicleRouteMainVO.setVehicleRouteVo(listVehicleRouteVo);
								
								return vehicleRouteMainVO;
							}
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public ViewStaffPersonAccountDetailsMainVO getStaffDetailsForDesktop(long custId,long academicYearId){
		try {
			ViewStaffPersonAccountDetailsMainVO staffPersonAccountDetailsMainVO =null;
			ViewStaffPersonAccountDetailsVO staffPersonAccountDetailsVO =null;
			List<ViewStaffPersonAccountDetailsVO> staffPersonAccountDetailsVOs = new ArrayList<ViewStaffPersonAccountDetailsVO>();
			/* they need all active and inactive status staff so I remove status condition*/
			List<ViewStaffPersonAccountDetails> staffList = this.getAll(ViewStaffPersonAccountDetails.class, "custId = "+ custId);//status = 'Y' AND 
			if (!ObjectFunctions.isNullOrEmpty(staffList)) {
				for(ViewStaffPersonAccountDetails staff : staffList){
					staffPersonAccountDetailsVO = new ViewStaffPersonAccountDetailsVO();
					staffPersonAccountDetailsVO.setStaffId(staff.getStaffId());
					staffPersonAccountDetailsVO.setAccountId(staff.getAccountId());
					staffPersonAccountDetailsVO.setStaffType(ObjectFunctions.isNullOrEmpty(staff.getStaffType())? "": staff.getStaffType());
					staffPersonAccountDetailsVO.setQualification1(ObjectFunctions.isNullOrEmpty(staff.getQualification1())? "": staff.getQualification1());
					staffPersonAccountDetailsVO.setQualification2(ObjectFunctions.isNullOrEmpty(staff.getQualification2())? "": staff.getQualification2());
					staffPersonAccountDetailsVO.setDateofJoining(ObjectFunctions.isNullOrEmpty(staff.getDateofJoining())? "": DateFunctions.convertDateToString(staff.getDateofJoining()));
					staffPersonAccountDetailsVO.setUsername(ObjectFunctions.isNullOrEmpty(staff.getUsername())? "": staff.getUsername());
					staffPersonAccountDetailsVO.setRoleName(staff.getRoleName());
					staffPersonAccountDetailsVO.setFirstName(staff.getFirstName());
					staffPersonAccountDetailsVO.setLastName(ObjectFunctions.isNullOrEmpty(staff.getLastName())? "": staff.getLastName());
					staffPersonAccountDetailsVO.setGender(ObjectFunctions.isNullOrEmpty(staff.getGender())? "": staff.getGender());
					staffPersonAccountDetailsVO.setImageId(ObjectFunctions.isNullOrEmpty(staff.getImageId())? 0: staff.getImageId());
					staffPersonAccountDetailsVO.setMobileNumber(ObjectFunctions.isNullOrEmpty(staff.getMobileNumber())? "": staff.getMobileNumber());
					staffPersonAccountDetailsVO.setPhoneNumber(ObjectFunctions.isNullOrEmpty(staff.getPhoneNumber())? "": staff.getPhoneNumber());
					staffPersonAccountDetailsVO.setDateOfBirth(ObjectFunctions.isNullOrEmpty(staff.getDateOfBirth())? "": DateFunctions.convertDateToString(staff.getDateOfBirth()));
					staffPersonAccountDetailsVO.setBioMetricId(ObjectFunctions.isNullOrEmpty(staff.getBioMetricId())? 0: staff.getBioMetricId());
					staffPersonAccountDetailsVO.setEmail(ObjectFunctions.isNullOrEmpty(staff.getEmail())? "": staff.getEmail());
					staffPersonAccountDetailsVO.setStaffUniqueNumber(ObjectFunctions.isNullOrEmpty(staff.getStaffUniqueNumber())? "": staff.getStaffUniqueNumber());
					staffPersonAccountDetailsVO.setDesignation(ObjectFunctions.isNullOrEmpty(staff.getDesignation())? "": staff.getDesignation());
					staffPersonAccountDetailsVO.setBankName(ObjectFunctions.isNullOrEmpty(staff.getBankName())? "": staff.getBankName());
					staffPersonAccountDetailsVO.setBankAccountNumber(ObjectFunctions.isNullOrEmpty(staff.getBankAccountNumber())? "": staff.getBankAccountNumber());
					staffPersonAccountDetailsVO.setBankBranchName(ObjectFunctions.isNullOrEmpty(staff.getBankBranchName())? "": staff.getBankBranchName());
					staffPersonAccountDetailsVO.setSalary(ObjectFunctions.isNullOrEmpty(staff.getSalary())? 0.0: staff.getSalary());
					staffPersonAccountDetailsVO.setIfscCode(ObjectFunctions.isNullOrEmpty(staff.getIfscCode())? "": staff.getIfscCode());
					staffPersonAccountDetailsVO.setPanNumber(ObjectFunctions.isNullOrEmpty(staff.getPanNumber())? "": staff.getPanNumber());
					staffPersonAccountDetailsVO.setPfNo(ObjectFunctions.isNullOrEmpty(staff.getPfNo())? "": staff.getPfNo());
					staffPersonAccountDetailsVO.setEsiNo(ObjectFunctions.isNullOrEmpty(staff.getEsiNo())? "": staff.getEsiNo());
					staffPersonAccountDetailsVO.setSalaryPaymentMode(ObjectFunctions.isNullOrEmpty(staff.getSalaryPaymentMode())? "": staff.getSalaryPaymentMode());
					staffPersonAccountDetailsVO.setStaffGrade(ObjectFunctions.isNullOrEmpty(staff.getStaffGrade())? "": staff.getStaffGrade());
					staffPersonAccountDetailsVO.setStaffLocation(ObjectFunctions.isNullOrEmpty(staff.getStaffLocation())? "": staff.getStaffLocation());

					/*SchoolShiftInfoVO shiftInfoVO = new SchoolShiftInfoVO();
					shiftInfoVO.setStartTime(ObjectFunctions.isNullOrEmpty(staff.getStartTime())? "": staff.getStartTime());
					shiftInfoVO.setEndTime(ObjectFunctions.isNullOrEmpty(staff.getEndTime())? "": staff.getEndTime());
					shiftInfoVO.setShiftName(ObjectFunctions.isNullOrEmpty(staff.getShiftName())? "": staff.getShiftName());
					shiftInfoVO.setId(ObjectFunctions.isNullOrEmpty(staff.getShiftId())? 0: Long.valueOf(staff.getShiftId()));
					staffPersonAccountDetailsVO.setSchoolShiftInfoVO(shiftInfoVO);
					shiftInfoVO=null;*/
					// Address
					//AddressVO addressVO = new AddressVO();
					staffPersonAccountDetailsVO.setAddressLine1(ObjectFunctions.isNullOrEmpty(staff.getAddressLine1())? "": staff.getAddressLine1());
					staffPersonAccountDetailsVO.setAddressLine2(ObjectFunctions.isNullOrEmpty(staff.getAddressLine2())? "": staff.getAddressLine2());
					staffPersonAccountDetailsVO.setCity(ObjectFunctions.isNullOrEmpty(staff.getCity())? "": staff.getCity());
					staffPersonAccountDetailsVO.setState(ObjectFunctions.isNullOrEmpty(staff.getStateName())? "": staff.getStateName());
					staffPersonAccountDetailsVO.setPostalCode(ObjectFunctions.isNullOrEmpty(staff.getPostalCode())? "": staff.getPostalCode());
					/*staffPersonAccountDetailsVO.setAddressVO(addressVO);
					addressVO=null;*/
					
					staffPersonAccountDetailsVO.setStatus(staff.getStatus());
					
					staffPersonAccountDetailsVOs.add(staffPersonAccountDetailsVO);
				}
				staffPersonAccountDetailsMainVO = new ViewStaffPersonAccountDetailsMainVO();
				staffPersonAccountDetailsMainVO.setStaffPersonAccountDetailsVOS(staffPersonAccountDetailsVOs);
				return staffPersonAccountDetailsMainVO;
			}else{
				return null;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ClassTeacherMainVO getStaffClassTeacherDetailsForDesktop(long custId,long academicYearId){
		try {
			ClassTeacherMainVO classTeacherMainVO =null;
			List<ClassTeacherVO> classTeacherVOs = new ArrayList<ClassTeacherVO>();
			//List<ClassTeacher> staffSubjectList = this.getAll(ClassTeacher.class, "custId="+ custId +" and academicYearId="+ academicYearId);
			String query = "SELECT  if(ct.classTeacher='Y', 'Yes','No'),sc.id as studyClassId, ss.id AS studySubjectId, ss.name AS subjectName, ct.periodsCount, st.id AS staffId FROM classTeacher ct "
					+ "left join studyClass sc ON (ct.studyClassId = sc.id)"
					+" left join staff st ON(ct.teacherId = st.id)"
					+ "left join studySubject ss ON(ct.studySubjectId = ss.id)"
					+ "WHERE ct.custId="+ custId +" and ct.academicYearId="+ academicYearId;
			List<Object[]> staffSubjectList = this.getAll(query);
			if (!ObjectFunctions.isNullOrEmpty(staffSubjectList)) {
				ClassTeacherVO classTeacherVO = null;
				/*for(ClassTeacher staffSubject : staffSubjectList){
					classTeacherVO = new ClassTeacherVO();
					classTeacherVO.setClassTeacher(ObjectFunctions.isNullOrEmpty(staffSubject.getIsClassTeacher())? "": staffSubject.getIsClassTeacher());
					classTeacherVO.setStudyClassId(staffSubject.getStudyClassId());
					classTeacherVO.setStudySubjectId(staffSubject.getStudySubjectId());
					classTeacherVO.setSubjectName(staffSubject.getStudySubject().getName());
					classTeacherVO.setPeriodsCount(staffSubject.getPeriodsCount());
					classTeacherVO.setStaffId(staffSubject.getStaffId());
					classTeacherVOs.add(classTeacherVO);
					classTeacherVO=null;
				}*/
				for(Object[] staffSubject : staffSubjectList){
					classTeacherVO = new ClassTeacherVO();
					classTeacherVO.setClassTeacher(staffSubject[0].toString());
					classTeacherVO.setStudyClassId(Long.valueOf(staffSubject[1].toString()));
					classTeacherVO.setStudySubjectId(Long.valueOf(staffSubject[2].toString()));
					classTeacherVO.setSubjectName(staffSubject[3].toString());
					classTeacherVO.setPeriodsCount(Integer.valueOf(staffSubject[4].toString()));
					classTeacherVO.setStaffId(Long.valueOf(staffSubject[5].toString()));
					classTeacherVOs.add(classTeacherVO);
					classTeacherVO=null;
				}
				classTeacherMainVO = new ClassTeacherMainVO();
				classTeacherMainVO.setClassTeacherVOs(classTeacherVOs);
				//classTeacherMainVO.getClassTeacherVOs().addAll(classTeacherVOs);
				return classTeacherMainVO;
			}else{
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/**
	 * Sending Notification when assignment was created.
	 */
	public void sendNotificationForClassAssignments(List<String> classIds, ClassAssignment classAssignment,boolean isNewAssignment)
	{
		log.debug("Inside sendNotificationForClassAssignments method.");
		List<String> studentAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM student WHERE status = 'Y' AND classSectionId in ("+StringUtil.convertListToString(classIds)+")");
		//List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(parentId) FROM Account WHERE id IN("+studentAccountIds.get(0)+");");
		List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(parentAccountId) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN("+studentAccountIds.get(0)+");");
  		 String commaDelimitedString = StringUtil.convertListToString(parentAccountIds);
		 StringBuffer accountIdbuffer = new StringBuffer("(");
		 accountIdbuffer.append(commaDelimitedString);
		 accountIdbuffer.append(")");
		 String message = "Class Assignment Created.";
		 if(!isNewAssignment){
		  message = "Class Assignment Updated.";
		 }
	
		 try {

				ViewClassAssignmentDetailsVO classAssignmentDetailsVO = new ViewClassAssignmentDetailsVO();
				AttachmentVO attachmentVO=null;
				List<ViewClassAssignmentDetailsVO> viewClassAssignmentDetailsVOList = new ArrayList<ViewClassAssignmentDetailsVO>();
				List<AttachmentVO> attachmentVOList = new ArrayList<AttachmentVO>();
				
				classAssignmentDetailsVO.setId(classAssignment.getId());
				classAssignmentDetailsVO.setDescription(ObjectFunctions.isNullOrEmpty(classAssignment.getDescription())? "": classAssignment.getDescription());
				classAssignmentDetailsVO.setCreatedBy(ObjectFunctions.isNullOrEmpty(classAssignment.getCreatedById())? "": String.valueOf(classAssignment.getCreatedById()));
				classAssignmentDetailsVO.setDate(ObjectFunctions.isNullOrEmpty(classAssignment.getAssignmentDate())? null: DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, classAssignment.getAssignmentDate()));
				classAssignmentDetailsVO.setStudyClassId(ObjectFunctions.isNullOrEmpty(classAssignment.getClassSectionId())? 0: classAssignment.getClassSectionId());
				classAssignmentDetailsVO.setClassAndSection("");//TODO 
				classAssignmentDetailsVO.setSubjectId(ObjectFunctions.isNullOrEmpty(classAssignment.getSubjectId())? 0: classAssignment.getSubjectId());
				classAssignmentDetailsVO.setSubjectName(ObjectFunctions.isNullOrEmpty(classAssignment.getSubjectName())? "": classAssignment.getSubjectName());
				classAssignmentDetailsVO.setStatus("O");
				
				if(!ObjectFunctions.isNullOrEmpty(classAssignment.getAttachmentList()))
				{
					for(Attachment attachment:classAssignment.getAttachmentList()){
						attachmentVO = new AttachmentVO();
						attachmentVO.setId(ObjectFunctions.isNullOrEmpty(attachment.getId())? 0: attachment.getId());
						attachmentVO.setFilePath(ObjectFunctions.isNullOrEmpty(attachment.getFilePath())? "": attachment.getFilePath());
						attachmentVO.setFileName(ObjectFunctions.isNullOrEmpty(attachment.getFileName())? "": attachment.getFileName());
						attachmentVOList.add(attachmentVO);
					}
					classAssignmentDetailsVO.setAttachments(attachmentVOList);
				}
				viewClassAssignmentDetailsVOList.add(classAssignmentDetailsVO);
				JSONObject main = new JSONObject();
				JSONObject subVal = new JSONObject();
				main.put("notificationFor", "Class Assignment");
				subVal.put("description", classAssignment.getDescription());
				subVal.put("title", message);
				subVal.put("classAssignmentVO",new JSONArray(new Gson().toJson(viewClassAssignmentDetailsVOList)));
				main.put("information", subVal);
				
				this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
				
	  }
	  catch(Exception e){
		   e.printStackTrace();
	  }
}
	/**
	 * Sending Notification when assignment was deleted.
	 */
	public void sendNotificationForDeleteClassAssignment(List<String> classIds,List<String> assignmentIds)
	{
		log.debug("Inside sendNotificationForDeleteClassAssignment method.");
		try {
			List<String> studentAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM student WHERE status = 'Y' AND classSectionId in ("+StringUtil.convertListToString(classIds)+")");
			List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(parentAccountId) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN("+studentAccountIds.get(0)+");");
			String commaDelimitedString = StringUtil.convertListToString(parentAccountIds);
			StringBuffer accountIdbuffer = new StringBuffer("(");
			accountIdbuffer.append(commaDelimitedString);
			accountIdbuffer.append(")");
			String message = "Class Assignment Deleted.";
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			main.put("notificationFor", "Class Assignment");
			subVal.put("deletedIds", StringUtil.convertListToString(assignmentIds));
			subVal.put("title", message);
			main.put("information", subVal);

			this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.

		}
		catch(Exception e){
			e.printStackTrace();
		}
}
	/**
	 * Sending Notification when assignment was Completed.
	 */
	public void sendNotificationForCompletionOfClassAssignments(List<String> studentAccountIds, ClassAssignment classAssignment)
	{
		log.debug("Inside sendNotificationForCompletionOfClassAssignments method.");
		List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(parentAccountId) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN("+studentAccountIds.get(0)+");");
  		 String commaDelimitedString = StringUtil.convertListToString(parentAccountIds);
		 StringBuffer accountIdbuffer = new StringBuffer("(");
		 accountIdbuffer.append(commaDelimitedString);
		 accountIdbuffer.append(")");
		 String message = "Class Assignment Completed.";
	
		 try {

				ViewClassAssignmentDetailsVO classAssignmentDetailsVO = new ViewClassAssignmentDetailsVO();
				AttachmentVO attachmentVO=null;
				List<ViewClassAssignmentDetailsVO> viewClassAssignmentDetailsVOList = new ArrayList<ViewClassAssignmentDetailsVO>();
				List<AttachmentVO> attachmentVOList = new ArrayList<AttachmentVO>();
				
				classAssignmentDetailsVO.setId(classAssignment.getId());
				classAssignmentDetailsVO.setDescription(ObjectFunctions.isNullOrEmpty(classAssignment.getDescription())? "": classAssignment.getDescription());
				classAssignmentDetailsVO.setCreatedBy(ObjectFunctions.isNullOrEmpty(classAssignment.getCreatedById())? "": String.valueOf(classAssignment.getCreatedById()));
				classAssignmentDetailsVO.setDate(ObjectFunctions.isNullOrEmpty(classAssignment.getAssignmentDate())? null: DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, classAssignment.getAssignmentDate()));
				classAssignmentDetailsVO.setStudyClassId(ObjectFunctions.isNullOrEmpty(classAssignment.getClassSectionId())? 0: classAssignment.getClassSectionId());
				classAssignmentDetailsVO.setClassAndSection("");//TODO 
				classAssignmentDetailsVO.setSubjectId(ObjectFunctions.isNullOrEmpty(classAssignment.getSubjectId())? 0: classAssignment.getSubjectId());
				classAssignmentDetailsVO.setSubjectName(ObjectFunctions.isNullOrEmpty(classAssignment.getSubjectName())? "": classAssignment.getSubjectName());
				classAssignmentDetailsVO.setStatus("C");
				
				viewClassAssignmentDetailsVOList.add(classAssignmentDetailsVO);
				JSONObject main = new JSONObject();
				JSONObject subVal = new JSONObject();
				main.put("notificationFor", "Class Assignment Completion");
				subVal.put("description", classAssignment.getDescription());
				subVal.put("title", message);
				subVal.put("classAssignmentVO",new JSONArray(new Gson().toJson(viewClassAssignmentDetailsVOList)));
				main.put("information", subVal);
				
				this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
				
	  }
	  catch(Exception e){
		   e.printStackTrace();
	  }
}
	public void sendNotificationForScoreCard(long studentId, String parentId, long classSectionId,String fullName,long examTypeId,String examType,long userAcademicYearId,long userCustId,long accountId)
	{
		try
		{
			StudentMarksVO studentMarksVO=null;
			StudentMarksMainVO studentMarksMainVO = null;
			List<StudentMarksVO> studentMarksVOList = new ArrayList<StudentMarksVO>();
			List<Object[]> studentMarks = this.getAll("SELECT (sm.obtainedMarks+sm.moderationMarks) AS subjectMarks, sm.present, sm.examScheduleId, sm.studId, es.classSubjectId FROM studentMarks sm LEFT JOIN examSchedules es ON(es.id = sm.examScheduleId) Left join student s ON(sm.studId = s.id) WHERE s.id ="+studentId+" AND s.status = 'Y' AND es.examTypeId ="+examTypeId);
			if (!ObjectFunctions.isNullOrEmpty(studentMarks)) {
				Map<String, Double> subjHighest = new HashMap<String, Double>();
				List<Object[]> HighestMarks = this.getAll("SELECT MAX(sm.obtainedMarks+sm.moderationMarks) AS subjectHighestMarks, sm.examScheduleId, es.classSubjectId FROM studentMarks sm LEFT JOIN examSchedules es ON(es.id = sm.examScheduleId) Left join student s ON(sm.studId = s.id) WHERE s.id ="+studentId+" AND s.status='Y' AND es.examTypeId ="+examTypeId+" GROUP BY es.classSubjectId, sm.examScheduleId");
				for(Object[] marks : HighestMarks){
					if(!ObjectFunctions.isNullOrEmpty(marks)){
						subjHighest.put(marks[1].toString()+"_"+marks[2].toString(), Double.valueOf(marks[0].toString()));
					}
				}
				HighestMarks = null;					
				/*for(Object[] marks : studentMarks) {
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
				studentMarksMainVO.setStudentMarksVOList(studentMarksVOList);*/
				JSONArray studentMarksArray = new JSONArray();
				for(Object[] marks : studentMarks) {
					JSONObject studentMarksObj = new JSONObject();
					studentMarksObj.put("studentId",Long.valueOf(marks[3].toString()));
					studentMarksObj.put("scheduleId", Long.valueOf(marks[2].toString()));
					studentMarksObj.put("subjectMarks",Double.valueOf(marks[0].toString()));
					studentMarksObj.put("isPresent",marks[1].toString());
					if(!ObjectFunctions.isNullOrEmpty(subjHighest.get(marks[2].toString()+"_"+marks[4].toString())))
						studentMarksObj.put("subjectHighestMarks",subjHighest.get(marks[2].toString()+"_"+marks[4].toString()));
					else
						studentMarksObj.put("subjectHighestMarks",0);
					studentMarksArray.put(studentMarksObj);
					studentMarksObj=null;
				}
				
				StringBuffer accountIdbuffer = new StringBuffer("(");
				accountIdbuffer.append(parentId);
				accountIdbuffer.append(")");
				
				JSONObject main = new JSONObject();
				JSONObject subVal = new JSONObject();
				
				main.put("notificationFor", "ScoreCardDownload");
				subVal.put("title", "Score Card Generated");
				subVal.put("description", "Your children "+fullName+" score card for "+examType+" is generated, click to download.");
				//subVal.put("studentMarksVOList",new JSONArray(new Gson().toJson(studentMarksVOList)));
				subVal.put("studentMarksVOList",studentMarksArray);
				main.put("information", subVal);
				this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void sendNotificationForScoreCardByClassSectionId(long classSectionId)
	{
		try
		{
	  		 //List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(parentId)) FROM Account WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+classSectionId+")");
			 List<String> parentAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(parentAccountId)) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+classSectionId+")");
			 JSONObject main = new JSONObject();
			 JSONObject subVal = new JSONObject();
				
			main.put("notificationFor", "Scorecard");
			subVal.put("title", "Scorecard Generated");
			subVal.put("description", "Your children score card is generated.");
			//subVal.put("studentMarksVOList",studentMarksArray);
			main.put("information", subVal);
			this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+parentAccountIds.get(0)+")"); //To add notification for mobile app.
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sendNotificationForFeePayment(long parentId)
	{
		try{
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			main.put("notificationFor", "Class Fee");
			subVal.put("description", "Fee Paid for your child. Click to see more info");
			subVal.put("title", "Fee Paid for your child.");
			main.put("information", subVal);
			
			this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(parentId)+")");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean sendReminderForMeetingSchedule(long meetingScheduleId)
	{
		try{
			MeetingSchedules meetingSchedules = (MeetingSchedules)this.get(MeetingSchedules.class, meetingScheduleId);
			if(!ObjectFunctions.isNullOrEmpty(meetingSchedules)){
				long studyClassId =0;
				if(!ObjectFunctions.isNullOrEmpty(meetingSchedules.getStudyClass())){
					studyClassId = meetingSchedules.getStudyClass().getId();
				}
				Object[] parentAccountIds;
				List<String> parentIdMeetingIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM meetingSlots WHERE meetingScheduleId = "+meetingScheduleId+" AND accountId IS NOT NULL");
				if(studyClassId > 0){
					if(ObjectFunctions.isNullOrEmpty(parentIdMeetingIds.get(0)))
					{
						 //parentAccountIds = this.get("SELECT GROUP_CONCAT(parentId),'' FROM Account WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+studyClassId+")");
						parentAccountIds = this.get("SELECT GROUP_CONCAT(parentAccountId),'' FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+studyClassId+")");
					}
					else 
					{
						//parentAccountIds = this.get("SELECT GROUP_CONCAT(parentId),'' FROM Account WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+studyClassId+") and parentId NOT IN("+parentIdMeetingIds.get(0)+")");
						parentAccountIds = this.get("SELECT GROUP_CONCAT(parentAccountId),'' FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN(SELECT s.accountId FROM student s WHERE s.classSectionId = "+studyClassId+") and parentId NOT IN("+parentIdMeetingIds.get(0)+")");
					}
				}else{
					if(!ObjectFunctions.isNullOrEmpty(parentIdMeetingIds.get(0)))
					{
						 parentAccountIds = this.get("SELECT GROUP_CONCAT(accountId),'' FROM vw_staffDetails WHERE roleId IN(2, 8, 12, 31, 35) AND custId = "+meetingSchedules.getCustId()+"");
					}
					else 
					{
						parentAccountIds = this.get("SELECT GROUP_CONCAT(accountId),'' FROM vw_staffDetails WHERE roleId IN(2, 8, 12, 31, 35) AND custId = "+meetingSchedules.getCustId()+" and accountId NOT IN("+parentIdMeetingIds.get(0)+")");
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(parentAccountIds[0]))
				{
					JSONObject main = new JSONObject();
					JSONObject subVal = new JSONObject();
					main.put("notificationFor", "StaffParentMeetingSchedule");
					subVal.put("description", "This is reminder for staff parent meeting. Click to reserve your slot.");
					subVal.put("title", "Staff parent meeting reminder");
					main.put("information", subVal);
					
					this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+parentAccountIds[0].toString()+")");
					return true;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public void sendNotificationForTimetableUpdate(long custId)
	{
		try
		{
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			main.put("notificationFor", "Timetable");
			subVal.put("description", "Your school Timetable is updated.");
			subVal.put("title", "Timetable is updated");
			main.put("information", subVal);
			this.sendNotificationToAndroidMobileUsers(custId, main.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendNotificationForEventDelete(long eventId, long custId)
	{
		try
		{
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			main.put("notificationFor", "Events");
			subVal.put("description", "Your school Event is deleted.");
			subVal.put("title", "Your school Event is deleted");
			subVal.put("deletedIds", eventId);
			main.put("information", subVal);
			this.sendNotificationToAndroidMobileUsers(custId, main.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sendNotificationForHolidaysDelete(long custId,String description,String startDate,String endDate)
	{
		try
		{
			List<String> holidayIds = this.getAll("SELECT GROUP_CONCAT(id) FROM schoolHolidays where custId="+custId+" and description='"+description+"' and startDate='"+startDate+"' and endDate='"+endDate+"'");
			if(!ObjectFunctions.isNullOrEmpty(holidayIds))
			{
				if(!ObjectFunctions.isNullOrEmpty(holidayIds.get(0)))
				{
					JSONObject main = new JSONObject();
					JSONObject subVal = new JSONObject();
					main.put("notificationFor", "Holidays");
					subVal.put("description", "Your school holidays are deleted.");
					subVal.put("title", "Your school holidays are deleted");
					subVal.put("deletedIds", holidayIds.get(0));
					main.put("information", subVal);
					this.sendNotificationToAndroidMobileUsers(custId, main.toString());
				}
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendNotificationForStudentMarks(long custId)
	{
		try
		{
			List<String>  staffAccountIds = this.getAll("SELECT GROUP_CONCAT(accountId) FROM vw_staffDetails WHERE roleId IN(1,2, 8, 12, 31, 32, 35) AND custId = "+custId+" AND status='Y'");
			//List<ViewStaffPersonAccountDetails> staffList = this.getAll(ViewStaffPersonAccountDetails.class, "roleId IN(2, 8, 12, 31, 35) AND custId = "+custId);
			if(!ObjectFunctions.isNullOrEmpty(staffAccountIds))
			{
				if(!ObjectFunctions.isNullOrEmpty(staffAccountIds.get(0)))
				{
					JSONObject main = new JSONObject();
					JSONObject subVal = new JSONObject();
					main.put("notificationFor", "Scorecard");
					subVal.put("title", "Student Marks Added");
					subVal.put("description", "Student Marks Added.");
					//subVal.put("studentMarksVOList",studentMarksArray);
					main.put("information", subVal);
					this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+staffAccountIds.get(0)+")"); //To add notification for mobile app.
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Events> eventsforStaffStudents(String studyClassIds, long roleId,long custId,long academicYearId, String activeOrCompleted) {
		
		//A - active events, C - completed events
		String eventId = null;
		StringBuffer allEventIds = new StringBuffer();
		if (!ObjectFunctions.isNullOrEmpty(studyClassIds)){
			List<BigInteger> eventIds =  this.getAll("select eventId from EventStudyClass where studyClassId in ("+studyClassIds+")");
			if (ObjectFunctions.isNotNullOrEmpty(eventIds)) 
				eventId = StringFunctions.convertListToCommaDelimitedString(eventIds);
			else
				eventId="0";
			allEventIds.append(eventId);
			
			eventIds = null;
		 }
		
		if (roleId > 0){
			List<BigInteger> eventIds =  this.getAll("select eventId from EventRole where roleId="+roleId);
			if (ObjectFunctions.isNotNullOrEmpty(eventIds)) 
				eventId = StringFunctions.convertListToCommaDelimitedString(eventIds);
			else
				eventId="0";
			
			allEventIds.append(","+eventId);
			eventIds = null;
		 }
		
		//log.debug(allEventIds.toString());
		List<Events> allEventsList = new ArrayList<Events>();
		List<Events> eventsList = null;
		
		if(!StringFunctions.isNullOrEmpty(activeOrCompleted))
		{
			if(Constants.ACTIVE_STATUS.equalsIgnoreCase(activeOrCompleted))
				eventsList = this.getAll(Events.class," (id in ("+allEventIds.toString()+") OR eventFor='all') and academicYearId = "+ academicYearId +" and endDate >='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00' ORDER BY startDate");
			else
				eventsList = this.getAll(Events.class," (id in ("+allEventIds.toString()+") OR eventFor='all') and academicYearId = "+ academicYearId +" and endDate <'"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00' ORDER BY startDate");
		}
		else
			eventsList = this.getAll(Events.class," (id in ("+allEventIds.toString()+") OR eventFor='all') and academicYearId = "+ academicYearId +" ORDER BY startDate");
		
		if(!ObjectFunctions.isNullOrEmpty(eventsList)){
			for(Events events : eventsList)
			{
				List<EventsAlbum> eventsAlbumList=this.getAll(EventsAlbum.class, "custId="+custId+" and academicYearId="+academicYearId+" and eventId="+events.getId());
				if(!ObjectFunctions.isNullOrEmpty(eventsAlbumList)){
					events.setEventsAlbum(eventsAlbumList);
				}
				allEventsList.add(events);
			}
		}
		
		eventsList = null;
		
		return allEventsList;
	}
	
	@Override
	public List<Integer[]> getMarksIdByStudentIdAndExamScheduleId(long studentId, long examScheduleId) {
		return adminDao.getMarksIdByStudentIdAndExamScheduleId(studentId, examScheduleId);
	}
	
	public StudentAndStaffDailyAttendanceVO getStudentAndStaffDailyAttendance(long academicYearId)
	{
		try{
			StudentAndStaffDailyAttendanceVO studentAndStaffDailyAttendanceVO=new StudentAndStaffDailyAttendanceVO();
			AcademicYear academicYear = (AcademicYear)this.get(AcademicYear.class, academicYearId);
			if("D".equalsIgnoreCase(academicYear.getManageAttendanceBy()) && "N".equalsIgnoreCase(academicYear.getUseBiometricForStudent())){
				if("T".equalsIgnoreCase(academicYear.getCaptureAttendanceBy())){
					List<String> studentIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(studentId)) from vw_StudentDailyAttendance where academicYearId="+academicYearId+" and present ='N' and Date(attendanceDate) = Date(curdate())");
					if(!ObjectFunctions.isNullOrEmpty(studentIds)){
						studentAndStaffDailyAttendanceVO.setMorningSessionStudentIds(ObjectFunctions.isNullOrEmpty(studentIds.get(0))?"":studentIds.get(0));
					}
					studentIds = null;
					studentIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(studentId)) from vw_StudentDailyAttendance where academicYearId="+academicYearId+" and afternoonSession ='N' and Date(attendanceDate) = Date(curdate())");
					if(!ObjectFunctions.isNullOrEmpty(studentIds)){
						studentAndStaffDailyAttendanceVO.setAfternoonSessionStudentIds(ObjectFunctions.isNullOrEmpty(studentIds.get(0))?"":studentIds.get(0));
					}
				}else{
					List<String> studentIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(studentId)) from vw_StudentDailyAttendance where academicYearId="+academicYearId+" and Date(attendanceDate) = Date(curdate())");
					if(!ObjectFunctions.isNullOrEmpty(studentIds)){
						studentAndStaffDailyAttendanceVO.setMorningSessionStudentIds(ObjectFunctions.isNullOrEmpty(studentIds.get(0))?"":studentIds.get(0));
					}
				}
			}
			if("D".equalsIgnoreCase(academicYear.getManageStaffAttendanceBy()) && "N".equalsIgnoreCase(academicYear.getUseBiometricForStaff())){
				if("T".equalsIgnoreCase(academicYear.getCaptureAttendanceForStaff())){
					List<String> staffIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) from vw_StaffDailyAttendance where academicYearId="+academicYearId+" and present ='N' and Date(attendanceDate) =Date(curdate())");
					if(!ObjectFunctions.isNullOrEmpty(staffIds)){
						studentAndStaffDailyAttendanceVO.setMorningSessionStaffIds(ObjectFunctions.isNullOrEmpty(staffIds.get(0))?"":staffIds.get(0));
					}
					staffIds = null;
					staffIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) from vw_StaffDailyAttendance where academicYearId="+academicYearId+" and afternoonSession ='N' and Date(attendanceDate) =Date(curdate())");
					if(!ObjectFunctions.isNullOrEmpty(staffIds)){
						studentAndStaffDailyAttendanceVO.setAfternoonSessionStaffIds(ObjectFunctions.isNullOrEmpty(staffIds.get(0))?"":staffIds.get(0));
					}
				}else{
					List<String> staffIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) from vw_StaffDailyAttendance where academicYearId="+academicYearId+" and Date(attendanceDate) =Date(curdate())");
					if(!ObjectFunctions.isNullOrEmpty(staffIds)){
						studentAndStaffDailyAttendanceVO.setMorningSessionStaffIds(ObjectFunctions.isNullOrEmpty(staffIds.get(0))?"":staffIds.get(0));
					}
				}
			}
			
			return studentAndStaffDailyAttendanceVO;
		}
		catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	//below methos used to compress the 2 MB above size images can be compress the 100kb with original quality by cvs on 23-03-2014
	public void compressImage(String path, String ext) throws IOException{
		try {
			if(!StringFunctions.isNullOrEmpty(path) && !StringFunctions.isNullOrEmpty(ext)){
			 if(!"gif".equalsIgnoreCase(ext.trim())){
				 File input = new File(path);
				 if(input.length()>2*1024*1024){
					 BufferedImage image = ImageIO.read(input);
					  File compressedImageFile = new File(path);
					  FileOutputStream os =new FileOutputStream(compressedImageFile);
						  Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
						  ImageWriter writer = writers.next();
						  ImageOutputStream ios = ImageIO.createImageOutputStream(os);
						  writer.setOutput(ios);
						  ImageWriteParam param = writer.getDefaultWriteParam();
						  param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
						  param.setCompressionQuality(0.6f);
						  writer.write(null, new IIOImage(image, null, null), param);
					  os.close();
					  ios.close();
					  writer.dispose();
				 }
			 }
		  }
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	public int uploadEventPhotos(long eventId,InputStream uploadedInputStream,String fileName)
	{
		Events events =  (Events)this.get(Events.class, "id="+eventId);
		 if(!ObjectFunctions.isNullOrEmpty(events))
		 {
			AlbumAttachment photos = new AlbumAttachment();
		    String ext = FilenameUtils.getExtension(fileName);
   		 	try {
   		 		//saveToFile(uploadedInputStream,path);
   		 		
	   		 	File tempFile = File.createTempFile(fileName, ".jpg");
	            tempFile.deleteOnExit();
	            try(FileOutputStream out = new FileOutputStream(tempFile)){
	                IOUtils.copy(uploadedInputStream, out);
	            }
   		 	
   			 	photos.setFilePath(this.getUploadImageFilePath(tempFile, events.getAcademicYear().getAcademicYear(),fileName));
   			 	photos.setFileName(fileName);
   			 	photos.setCustId(events.getCustId());
   			 	//photos.setFileType(ext);
   				photos.setAcademicYearId(events.getAcademicYear().getId());
   				photos = (AlbumAttachment) this.saveOrUpdateObject(photos);
   			 
   				//compressImage(path,ext);
				} catch (Throwable e) {
					e.printStackTrace();
					return 2;
				}
			 
		 	List<AlbumAttachment> albumAttachmentList = null;
			EventsAlbum album = ((EventsAlbum)this.get(EventsAlbum.class, "custId="+events.getCustId()+" and academicYearId="+events.getAcademicYear().getId()+" and albumName='"+events.getEventName()+"' and eventId="+events.getId() ));
			if(!ObjectFunctions.isNullOrEmpty(album))
			 {
				album.setLastUpdatedById(0);
			 }
			 else{
					album = new EventsAlbum();
					album.setCreatedById(0);
					album.setCreatedDate(new Date());
					album.setCustId(events.getCustId());
					album.setAcademicYearId(events.getAcademicYear().getId());
				}
				album.setEventId(events.getId());
				album.setAlbumName(events.getEventName());
				//albumAttachmentList = this.getAll(AlbumAttachment.class, " id="+photos.getId());
				
				/*if(!ObjectFunctions.isNullOrEmpty(albumAttachmentList))
	 			{*/
					//album.getAlbumAttachment().addAll(ConvertUtil.convertListToSet(albumAttachmentList));
					album.getAlbumAttachment().add(photos);
					//albumAttachmentList = null;
	 			//}
				
				//album = (EventsAlbum) this.save(album);
				album = (EventsAlbum) this.merge(album);
				album = null;
		 }
		 return 0;
	}
       
	public int saveAutoReportsDetails(String reportDetails,long custId,long userId){
		try {
			List<AutoReportsTypes> reportTypesList =this.getAll(AutoReportsTypes.class);
			Map<Long, AutoReportsTypes> autoReporTypesMap =new HashMap<Long, AutoReportsTypes>();
			if(!ObjectFunctions.isNullOrEmpty(reportTypesList)){
				for(AutoReportsTypes reportsTypes : reportTypesList){
					autoReporTypesMap.put(reportsTypes.getId(), reportsTypes);
				}
			}
			List<UserAutoReportsConfiguration> autoConfigurationsList= this.getAll(UserAutoReportsConfiguration.class, "custId="+custId);
			Map<Long,UserAutoReportsConfiguration> userAutoMap = new HashMap<Long, UserAutoReportsConfiguration>();
			if(!ObjectFunctions.isNullOrEmpty(autoConfigurationsList)){
				for(UserAutoReportsConfiguration reportsConfiguration : autoConfigurationsList){
					userAutoMap.put(reportsConfiguration.getId(), reportsConfiguration);
				}
			}
			JSONArray reportJSONArray=new JSONArray(reportDetails);
			JSONObject userReportJson=null;
			long userReportId=0;
			long reportTypeId=0;
			String reportStatus=null;
			UserAutoReportsConfiguration userAutoReportsConfiguration=null;
			for(int i=0;i<reportJSONArray.length();i++)
			{
				userReportJson=reportJSONArray.getJSONObject(i);
				if(!ObjectFunctions.isNullOrEmpty(userReportJson))
				{
					userReportId = Long.valueOf((String)userReportJson.get("userReportId"));
					reportTypeId = Long.valueOf((String)userReportJson.get("reportTypeId"));
					reportStatus = (String)userReportJson.get("status");
					if(userReportId>0){
						if(!ObjectFunctions.isNullOrEmpty(userAutoMap.get(userReportId))){
							userAutoReportsConfiguration=userAutoMap.get(userReportId);
						}else{
							if("N".equalsIgnoreCase(reportStatus))
								continue;
							userAutoReportsConfiguration=new UserAutoReportsConfiguration();
							userAutoReportsConfiguration.setCreatedById(userId);
							userAutoReportsConfiguration.setCreatedDate(new Date());
						}
					}else{
						if("N".equalsIgnoreCase(reportStatus))
							continue;
						userAutoReportsConfiguration=new UserAutoReportsConfiguration();
						userAutoReportsConfiguration.setCreatedById(userId);
						userAutoReportsConfiguration.setCreatedDate(new Date());
					}
						
					userAutoReportsConfiguration.setLastUpdatedById(userId);
					userAutoReportsConfiguration.setLastUpdatedDate(new Date());
					userAutoReportsConfiguration.setCustId(custId);
					userAutoReportsConfiguration.setAutoReportsTypes(autoReporTypesMap.get(reportTypeId));
					userAutoReportsConfiguration.setStatus(reportStatus);
					this.save(userAutoReportsConfiguration);
				}
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return 0;
		}
	}
	
	public void sendNotificationForFeePayment(long custId, long studentId, long parentId,String feePayment)
	{
		
		//Here D fro delete, P for Paid
		try{
			ViewStudentFeePaymentDetailsVO studentFeePaymentDetailsVO = null;
			ViewStudentFeePaymentDetailsMainVO studentFeePaymentDetailsMainVO=null;
			List<ViewStudentFeePaymentDetailsVO> studentFeePaymentDetailsVOList = new ArrayList<ViewStudentFeePaymentDetailsVO>();
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			main.put("notificationFor", "Class Fee");
			if("D".equalsIgnoreCase(feePayment))
			{
				subVal.put("description", "Payment invoice has been deleted for your child");
				subVal.put("title", "Invoice deleted for your child.");
			}
			else
			{
				subVal.put("description", "Fee Paid for your child. Click to see more info");
				subVal.put("title", "Fee Paid for your child.");
			}
			
			if(parentId > 0){
				main.put("information", subVal);
				this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+String.valueOf(parentId)+")");
			}
			AcademicYear academicYear  = super.getCurrentAcademicYear("Y",custId);
			//List<Object[]> feeDetails = this.getAll("SELECT t.studentId, SUM(t.feeAmount), SUM(t.paidAmount), SUM(t.discountAmount), SUM(t.concessionAmount) FROM (SELECT studentId, feeAmount, SUM(paymentAmount) as paidAmount, SUM(discountAmount) as discountAmount, SUM(paymentConcessionAmount) as concessionAmount FROM vw_studentFeePaymentDetails WHERE studentId= "+ studentId +" GROUP BY feeId, studentId)as t");
			//List<Object[]> feeDetails = this.getAll("SELECT t.studentId, SUM(t.feeAmount), SUM(t.paidAmount), SUM(t.discountAmount), SUM(t.concessionAmount) FROM (SELECT studentId, feeAmount, SUM(paymentAmount) as paidAmount, SUM(discountAmount) as discountAmount, SUM(paymentConcessionAmount) as concessionAmount FROM vw_studentFeePaymentDetails WHERE academicYearId= "+ academicYearId +" GROUP BY feeId, studentId)as t GROUP BY studentId");
			List<Object[]> feeDetails = this.getAll("select u.studentId, SUM(u.feeAmount), SUM(u.paidAmount), SUM(u.discountAmount), SUM(u.concessionAmount) FROM (SELECT f.studentId, SUM(f.feeAmount) as feeAmount, SUM(f.paidAmount) as paidAmount, SUM(f.discountAmount) discountAmount, SUM(f.concessionAmount) concessionAmount FROM (SELECT studentId, feeAmount, SUM(paymentAmount) as paidAmount, SUM(discountAmount) as discountAmount, SUM(paymentConcessionAmount) as concessionAmount FROM vw_studentFeePaymentDetails WHERE studentId= "+ studentId +" AND deleteStatus='N' GROUP BY feeId, studentId)as f "
					+ "UNION "
					+ "SELECT t.studentId, SUM(t.feeAmount), SUM(t.paidAmount), SUM(t.discountAmount), SUM(t.concessionAmount) FROM (SELECT studentId, feeAmount, SUM(paymentAmount) as paidAmount, SUM(discountAmount) as discountAmount, SUM(paymentConcessionAmount) as concessionAmount FROM vw_studentTransportFeePaymentDetails WHERE studentId= "+ studentId +" AND deleteStatus='N' GROUP BY transportFeeId, studentId)as t GROUP BY t.studentId ) u ");
			if(!ObjectFunctions.isNullOrEmpty(feeDetails)) {
				JSONArray feeArray = new JSONArray();
				for(Object[] fee : feeDetails) {
					if(!ObjectFunctions.isNullOrEmpty(fee)) {
					studentFeePaymentDetailsVO = new ViewStudentFeePaymentDetailsVO();
					if(!ObjectFunctions.isNullOrEmpty(fee[0]))
					studentFeePaymentDetailsVO.setStudentId(ObjectFunctions.isNullOrEmpty(Long.valueOf(fee[0].toString()))? 0:Long.valueOf(fee[0].toString()));
					studentFeePaymentDetailsVO.setFeeAmount(Double.valueOf(fee[1].toString()));
					studentFeePaymentDetailsVO.setPaidAmount(Double.valueOf(fee[2].toString()));
					studentFeePaymentDetailsVO.setDiscountAmount(fee[3].toString().equals("0.0") ?0d:Double.valueOf(fee[3].toString()));
					studentFeePaymentDetailsVO.setConcessionAmount(fee[4].toString().equals("0.0") ?0d:Double.valueOf(fee[4].toString()));
					if(Double.valueOf(fee[1].toString()) > (Double.valueOf(fee[2].toString())+Double.valueOf(fee[3].toString())))
						studentFeePaymentDetailsVO.setBalanceAmount(Double.valueOf(fee[1].toString())-Double.valueOf(fee[2].toString())-Double.valueOf(fee[3].toString())-Double.valueOf(fee[4].toString()));
					else studentFeePaymentDetailsVO.setBalanceAmount(0d);
					
					studentFeePaymentDetailsVOList.add(studentFeePaymentDetailsVO);											 
					studentFeePaymentDetailsVO = null;
					}
				}
				GsonBuilder gsonBuilder = new GsonBuilder(); gsonBuilder.serializeNulls(); 
				Gson gson = gsonBuilder.create();
				JSONParser parser = new JSONParser();
				subVal.put("viewStudentFeePaymentDetailsVOList",(org.json.simple.JSONArray)parser.parse(gson.toJson(studentFeePaymentDetailsVOList)));
				main.put("information", subVal);
				Object[] toAccountIds = this.get( "SELECT GROUP_CONCAT(DISTINCT(accountId)),'' FROM vw_staffDetails WHERE roleId IN(1, 12, 32) AND custId = "+custId);
				if(!ObjectFunctions.isNullOrEmpty(toAccountIds))
					this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+toAccountIds[0].toString()+")");
			}
		} catch(Exception e){ e.printStackTrace(); }
	}
	public void  studentFeePaidStatusForTransport(long studentId,long custId,long academicYearId,long categoryId) {
		adminDao.studentFeePaidStatusForTransport(studentId,custId,academicYearId,categoryId);
	}
	public void updateFeeConfiguredStatusInStudentForTransport(long boardingPointId,long categoryId,String status,long studentId) {
		adminDao.updateFeeConfiguredStatusInStudentForTransport(boardingPointId,categoryId,status,studentId);
	}
	public void updateStudentPaymentStatusForTransportFee(long boardingPointId,long categoryId){
		adminDao.updateStudentPaymentStatusForTransportFee(boardingPointId,categoryId);
	}
	public  void studentFeePaidStatusWithTransport(long studentId,long custId,long academicYearId,long categoryId,long classId){
		adminDao.studentFeePaidStatusWithTransport(studentId,custId,academicYearId,categoryId,classId);
	}
	public int removeClassFee(Fee fee,ClassName className){
		try {
			this.removeFee(fee.getId());
			this.updateStudentsFeePaidStatusClassWise(fee.getCustomer().getId(),fee.getCategoryId(),className.getId());
		} catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex = null;
		}
		return 0;
	}
	public void removeFee(long feeId){
		adminDao.removeFee(feeId);
	}
	public void updateStudentsFeePaidStatusClassWise(long custId,long categoryId,long classId){
		adminDao.updateStudentsFeePaidStatusClassWise(custId,categoryId,classId);
	}
	public boolean removeStudyClassSubjectAssignedTecher(long custId,long academicYearId,long studyClassId,long subjectId,long teacherId){
		return adminDao.removeStudyClassSubjectAssignedTecher(custId,academicYearId,studyClassId,subjectId,teacherId);
	}
	public List<ViewStudentsLatestExamMarksDetails> getLatestExamsClasses(long custId,long academicYearId){
		return adminDao.getLatestExamsClasses(custId,academicYearId);
	}
	public List<ViewClassExamDetails> getAllSectionsExamSchedulesDetails(long custId,long academicYearId,String classSectionIds,long examId,String subtypeIds){
		return adminDao.getAllSectionsExamSchedulesDetails(custId,academicYearId,classSectionIds,examId,subtypeIds);
	}
	public List<ScoreCardTemplates> getAllScorecardTemplatesByAcademicYearId(long custId,long academicYearId){
		return adminDao.getAllScorecardTemplatesByAcademicYearId(custId,academicYearId);
	}
	
	@Override
	public boolean moveImageFiles() {
		try{
			List<AlbumAttachment> albumAttachments = this.getAll(AlbumAttachment.class);
			if(ObjectFunctions.isNotNullOrEmpty(albumAttachments)){
				for(AlbumAttachment attachment : albumAttachments){
					File file = new File(SpringContextAware.getRealPath(attachment.getFilePath()+attachment.getFileName()));
					//File file = new File(String.format("%s/%s%s", path, attachment.getFilePath(), attachment.getFileName()));
					if(file.exists()){
						AcademicYear academicYear = (AcademicYear) this.get(AcademicYear.class, attachment.getAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(academicYear)){
							/*S3Info s3Info = new S3Info();
							s3Info.setAcademicYear(academicYear.getAcademicYear());
							s3Info.setSource("S");
							s3Info.setType(Types.Images);
							s3Info.setFile(file);
							
							S3Wrapper s3Wrapper = new S3Wrapper();
							String fileURL = s3Wrapper.upload(s3Info);*/
							
							attachment.setFilePath(this.getUploadImageFilePath(file, academicYear.getAcademicYear(),attachment.getFileName()));
							this.saveOrUpdateObject(attachment);
							attachment = null;
						}
					}
				}
			}
		}catch(Exception e){ e.printStackTrace(); }
		
		return false;
	}
	
	public String getUploadImageFilePath(File file,String academicYear,String fileName)
	{
		try {
			 String ext = FilenameUtils.getExtension(fileName);
			 
			S3Info s3Info = new S3Info();
			s3Info.setAcademicYear(academicYear);
			s3Info.setSource("S");
			s3Info.setType(Types.Images);
			s3Info.setFile(file);
			s3Info.setExtension(ext);
			
			S3Wrapper s3Wrapper = new S3Wrapper();
			return s3Wrapper.upload(s3Info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean moveUserImageFiles() {
		try{
			String mysqlTodayDate = DateFormatter.formatDate (DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date());
			log.debug("mysqlTodayDate:" + mysqlTodayDate);
			
			List<Customer> allActiveCustomersList = this.getAll(Customer.class,"status='Y' and organizationSubTypeId = 0 order by id");
			 if(!ObjectFunctions.isNullOrEmpty(allActiveCustomersList))
			 {
				for(Customer customer : allActiveCustomersList)
				{
					try {
						AcademicYear academicYear  = this.getCurrentAcademicYear("Y",customer.getId());
						
						if(!ObjectFunctions.isNullOrEmpty(academicYear))
						{
							//sending customer Image to amazon cloud
							if(!ObjectFunctions.isNullOrEmpty(customer.getCustomerOrgImage()))
							{
								UserImage userImage = customer.getCustomerOrgImage();
								File file = new File(SpringContextAware.getRealPath(userImage.getPath()+userImage.getName()));
								//File file = new File(String.format("%s/%s%s", path, attachment.getFilePath(), attachment.getFileName()));
								if(file.exists()){
									String imagePath = this.getUploadImageFilePath(file, academicYear.getAcademicYear(),userImage.getName());
									this.updateQuery("update UserImage set path='"+imagePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+userImage.getId());
									//customer = null;
									//academicYear = null;
									userImage = null;
								}
							}
							
							
							String principalDigitalSignaturePath = SpringContextAware.getRealPath("userFiles/"+customer.getId()+"/"+customer.getId()+"PrincipalSign"+"/"+customer.getId()+".png");
							if(ObjectFunctions.isNullOrEmpty(customer.getPrincipalDigitalSignature()))
							{
								File file = new File(SpringContextAware.getRealPath(principalDigitalSignaturePath));
								//File file = new File(String.format("%s/%s%s", path, attachment.getFilePath(), attachment.getFileName()));
								if(file.exists())
								{
									UserImage principalDigitalSignature = new UserImage();
									String imagePath = this.getUploadImageFilePath(file, academicYear.getAcademicYear(),customer.getId()+".png");
									principalDigitalSignature.setPath(imagePath);
									principalDigitalSignature.setName(customer.getId()+".png");  
									principalDigitalSignature.setThumbNail(customer.getId()+".png");
									principalDigitalSignature.setLastUpdatedById(1);
									principalDigitalSignature.setLastUpdatedDate(new Date());
									principalDigitalSignature = (UserImage)this.saveOrUpdateObject(principalDigitalSignature);
									customer.setPrincipalDigitalSignature(principalDigitalSignature);
									this.merge(customer);
									//customer = null;
									//academicYear = null;
									
								}
							}
							
							//sending student Image to amazon cloud
							List<ViewStudentClassDetails> viewStudentClassDetailsList = this.getAll(ViewStudentClassDetails.class, "custId="+customer.getId()+" and imagePath is not null order by custId");
							if(ObjectFunctions.isNotNullOrEmpty(viewStudentClassDetailsList))
							{
								for(ViewStudentClassDetails viewStudentClassDetails : viewStudentClassDetailsList)
								{
									try {
										File file = new File(SpringContextAware.getRealPath(viewStudentClassDetails.getImagePath()+viewStudentClassDetails.getImageName()));
										//File file = new File(String.format("%s/%s%s", path, attachment.getFilePath(), attachment.getFileName()));
										if(file.exists()){
											String imagePath = this.getUploadImageFilePath(file, viewStudentClassDetails.getAcademicYear(),viewStudentClassDetails.getImageName());
											this.updateQuery("update UserImage set path='"+imagePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1 where id="+viewStudentClassDetails.getImageId());
											viewStudentClassDetails = null;
										}
									} catch (Exception e) {
										e.printStackTrace();
										continue;
									}
								}
							}
							viewStudentClassDetailsList = null;
							
							//sending student profile Image,staff Image and use barcode image to amazon cloud
							List<User> usersList = this.getAll(User.class, "custId="+customer.getId()+" and (imageId is not null OR barcode is not null)");
							if(ObjectFunctions.isNotNullOrEmpty(usersList))
							{
								for(User userAccount : usersList)
								{
									if(!ObjectFunctions.isNullOrEmpty(userAccount.getProfileImage()))
									{
										try {
											if(!StringFunctions.isNullOrEmpty(userAccount.getProfileImage().getPath()))
											{
												File file = new File(SpringContextAware.getRealPath(userAccount.getProfileImage().getPath()+userAccount.getProfileImage().getName()));
												//File file = new File(String.format("%s/%s%s", path, attachment.getFilePath(), attachment.getFileName()));
												if(file.exists()){
													String imagePath = this.getUploadImageFilePath(file, academicYear.getAcademicYear(),userAccount.getProfileImage().getName());
													this.updateQuery("update UserImage set path='"+imagePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+userAccount.getProfileImage().getId());
												}
											}
										} catch (Exception e) {
											e.printStackTrace();
											//continue;
										}
									}
									
									//Sending barcode image to Amazon cloud
									try {
										if(!StringFunctions.isNullOrEmpty(userAccount.getBarcode()))
										{
											moveBarCodeByUserObj(userAccount, academicYear);
										}
									} catch (Exception e) {
										e.printStackTrace();
										continue;
									}
									userAccount = null;
								}
							}
							usersList = null;
							
							//sending admission students image to amazon cloud
							List<OnlineApplicationDetails> onlineApplicationDetailsList = this.getAll(OnlineApplicationDetails.class, "custId="+customer.getId()+" and profileImage is not null");
							if(ObjectFunctions.isNotNullOrEmpty(onlineApplicationDetailsList)){
								for(OnlineApplicationDetails onlineApplicationDetails : onlineApplicationDetailsList)
								{
									try {
										if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getProfileImage()))
										{
											UserImage userImage = onlineApplicationDetails.getProfileImage();
											AcademicYear admissionAcademicYear  = onlineApplicationDetails.getAcademicYear();
											File file = new File(SpringContextAware.getRealPath(userImage.getPath()+userImage.getName()));
											//File file = new File(String.format("%s/%s%s", path, attachment.getFilePath(), attachment.getFileName()));
											if(file.exists()){
												String imagePath = this.getUploadImageFilePath(file, admissionAcademicYear.getAcademicYear(),userImage.getName());
												this.updateQuery("update UserImage set path='"+imagePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+userImage.getId());
												onlineApplicationDetails = null;
												admissionAcademicYear = null;
												userImage = null;
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
										continue;
									}
								}
							}
							onlineApplicationDetailsList = null;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			 }
			
		}catch(Exception e){ e.printStackTrace(); }
		
		return false;
	}
	
	public SMSBaseVO uploadEventAlbumPhotos(long eventId,String url)
	{
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		KeyIdentifierVO keyIdentifier = new KeyIdentifierVO();
		APIStatusVO apistatusVO = null;
		int responseCode=2;
		Events events =  (Events)this.get(Events.class, "id="+eventId);
		 if(!ObjectFunctions.isNullOrEmpty(events))
		 {
			AlbumAttachment photos = new AlbumAttachment();
   		 	try {
   			 	photos.setFilePath(url);
   			 	photos.setCustId(events.getCustId());
   			 	//photos.setFileType(ext);
   				photos.setAcademicYearId(events.getAcademicYear().getId());
   				photos = (AlbumAttachment) this.saveOrUpdateObject(photos);
   				responseCode = 0;
   				//compressImage(path,ext);
				} catch (Throwable e) {
					e.printStackTrace();
					responseCode = 2;
				}
		 	List<AlbumAttachment> albumAttachmentList = null;
			EventsAlbum album = ((EventsAlbum)this.get(EventsAlbum.class, "custId="+events.getCustId()+" and academicYearId="+events.getAcademicYear().getId()+" and albumName='"+events.getEventName()+"' and eventId="+events.getId() ));
			if(!ObjectFunctions.isNullOrEmpty(album))
			 {
				album.setLastUpdatedById(0);
			 }
			 else{
					album = new EventsAlbum();
					album.setCreatedById(0);
					album.setCreatedDate(new Date());
					album.setCustId(events.getCustId());
					album.setAcademicYearId(events.getAcademicYear().getId());
				}
				album.setEventId(events.getId());
				album.setAlbumName(events.getEventName());
				album.getAlbumAttachment().add(photos);
					
				album = (EventsAlbum) this.merge(album);
				
				 if(!ObjectFunctions.isNullOrEmpty(album.getAlbumAttachment()))
				 {
					 List<AlbumAttachment> albumAttachmentAllList = ConvertUtil.convertSetToList(album.getAlbumAttachment()); 
					 keyIdentifier.setChannel(String.valueOf(photos.getId()));
					 albumAttachmentList = new ArrayList<AlbumAttachment>();
					 //albumAttachmentList.add(albumAttachment);
					 EventsNotificationThread R1 = new EventsNotificationThread(events,events.getId(),Constants.YES_STRING,albumAttachmentList);
					 R1.start();
					 albumAttachmentAllList = null;
				 }
				 
				 if(responseCode == 0)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
					else if(responseCode == 1)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorCode(),ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorDesc());
					else if(responseCode == 2)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
					smsBaseVO.setApiStatus(apistatusVO);
					smsBaseVO.setIdentifier(keyIdentifier);
					
				album = null;
		 }
		 events=null;
		 
		 
		 return smsBaseVO;
	}
	
	public void generateBarcode(long userId)
	{
		try {
			User user = (User)this.get(User.class, userId);
			AcademicYear academicYear = this.getCurrentAcademicYear("Y",user.getCustId());
			generateBarCodeByUserObj(user,academicYear);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public void generateBarCodeByUserObj(User user,AcademicYear academicYear)
	{
		try {
			BitMatrix bitMatrix;
			Writer writer = new QRCodeWriter();
			String barcodeNumber = String.format("S"+"%07d", user.getId());
			bitMatrix = new Code128Writer().encode(barcodeNumber, BarcodeFormat.CODE_128, 150, 80, null);
			
			File tempFile = File.createTempFile(barcodeNumber, ".png");
			tempFile.deleteOnExit();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(tempFile));
			
  // MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(getSession().getServletContext().getRealPath(fileName.toString()+barcodeNumber+".png"))));
			user.setBarcode(this.getUploadImageFilePath(tempFile, academicYear.getAcademicYear(),barcodeNumber+".png"));
			this.saveOrUpdateObject(user);
			user=null;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void moveBarCodeByUserObj(User user,AcademicYear academicYear)
	{
		try {
			String mysqlTodayDate = DateFormatter.formatDate (DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date());
			StringBuffer fileName = new StringBuffer("userFiles/");
		 	fileName.append("barcode");
		 	fileName.append("/");
		 	fileName.append(user.getCustId());
		 	fileName.append("/");

			File file = new File(SpringContextAware.getRealPath(fileName.toString()+user.getBarcode()+".png"));
			//File file = new File(String.format("%s/%s%s", path, attachment.getFilePath(), attachment.getFileName()));
			if(file.exists()){
				String imagePath = this.getUploadImageFilePath(file, academicYear.getAcademicYear(),user.getBarcode());
				//log.debug("Image Path:"+imagePath);
				this.updateQuery("update Account set barcode='"+imagePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+user.getId());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public boolean movePayslips(){
		try {
			
			String mysqlTodayDate = DateFormatter.formatDate (DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date());
			List<Customer> allActiveCustomersList = this.getAll(Customer.class,"status='Y' and organizationSubTypeId = 0 order by id");
			 if(!ObjectFunctions.isNullOrEmpty(allActiveCustomersList))
			 {
				for(Customer customer : allActiveCustomersList)
				{
					List<PaySlip> paySlipList = this.getAll(PaySlip.class,"custId="+customer.getId());
					if(!ObjectFunctions.isNullOrEmpty(paySlipList))
					{
						for(PaySlip paySlip : paySlipList)
						{
							if(!ObjectFunctions.isNullOrEmpty(paySlip.getStaffMonthlyPaySlips()))
							{
								for(StaffMonthlyPaySlips staffMonthlyPaySlips : paySlip.getStaffMonthlyPaySlips())
								{
									StringBuffer pathName = new StringBuffer("userFiles/").append(staffMonthlyPaySlips.getCustId()).append("/").append("payslips").append("/").append(paySlip.getYearName()).append("/").append(staffMonthlyPaySlips.getMonth()).append("/").append(staffMonthlyPaySlips.getAccountId()).append(".pdf");
									File file = new File(SpringContextAware.getRealPath(pathName.toString()));
									
									if(file.exists()){
										String payslipFilePath = this.getUploadPayslipFilePath(file, "",staffMonthlyPaySlips.getAccountId()+".pdf");
										this.updateQuery("update staffMonthlyPaySlips set filePath='"+payslipFilePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+staffMonthlyPaySlips.getId());
										staffMonthlyPaySlips = null;
									}
								}
							}
						}
					 }
					paySlipList =null;
				}
				allActiveCustomersList = null;
			 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return true;
	}
	
	public String getUploadPayslipFilePath(File file,String academicYear,String fileName)
	{
		try {
			 String ext = FilenameUtils.getExtension(fileName);
			 
			S3Info s3Info = new S3Info();
			s3Info.setAcademicYear(academicYear);
			s3Info.setSource("S");
			s3Info.setType(Types.PaySlips);
			s3Info.setFile(file);
			s3Info.setExtension(ext);
			
			S3Wrapper s3Wrapper = new S3Wrapper();
			return s3Wrapper.upload(s3Info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean moveTimetables(){
		try {
			
			String mysqlTodayDate = DateFormatter.formatDate (DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date());
			List<Customer> allActiveCustomersList = this.getAll(Customer.class,"status='Y' and organizationSubTypeId = 0 order by id");
			 if(!ObjectFunctions.isNullOrEmpty(allActiveCustomersList))
			 {
				for(Customer customer : allActiveCustomersList)
				{
					AcademicYear academicYear  = this.getCurrentAcademicYear("Y",customer.getId());
					
					if(!ObjectFunctions.isNullOrEmpty(academicYear))
					 {
						//Study Class Wise time table
						log.debug("select id,className from studyClass where custId="+customer.getId()+" and academicYearId="+academicYear.getId()+ " and isClassTimetableUploaded='Y'");
				        List<Object[]> studyClassesList = this.getAll("select id,className from studyClass where custId="+customer.getId()+" and academicYearId="+academicYear.getId()+ " and isClassTimetableUploaded='Y'");
						if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
						{
							for(Object[] studyClassObj : studyClassesList)
							{
								try {
									StringBuffer pathName = new StringBuffer("userFiles/timetable/");
									pathName.append(customer.getId()+"/"+academicYear.getId()+"/classtimetables/"+studyClassObj[0].toString()+".html");
									
									File file = new File(SpringContextAware.getRealPath(pathName.toString()));
									if(file.exists()){
										String timeTableFilePath = this.getUploadTimetablesFilePath(file, academicYear.getAcademicYear(),studyClassObj[0].toString()+".html");
										this.updateQuery("update studyClass set classTimetableUploadedFilePath='"+timeTableFilePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+studyClassObj[0].toString());
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						 }
						studyClassesList = null;
						
						//Staff Wise time table
						StringBuffer sqlQuery = new StringBuffer("select  distinct(sd.staffId),sd.accountId  from vw_staffDetails sd LEFT JOIN classTeacher ct on (sd.staffId=ct.teacherId)") 
						.append(" where sd.custId=").append(customer.getId()).append(" and sd.academicYearId<="+academicYear.getId()+"").append(" and sd.status='"+Constants.YES_STRING+"' and sd.isTimetableUploaded='Y'")
						//.append(" and roleId in(2,8,12)")
						.append(" and roleId in (1,2,4,5,6,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,30,31,32,33,35)")
						.append(" order by roleName");
						log.debug("Staff URL:" + sqlQuery);
						List<Object[]> staffList = this.getAll(sqlQuery.toString());
						if(!ObjectFunctions.isNullOrEmpty(staffList))
						{
							for(Object[] staffObj : staffList)
							{
								try {
									StringBuffer pathName = new StringBuffer("userFiles/timetable/");
									pathName.append(customer.getId()+"/"+academicYear.getId()+"/stafftimetables/"+staffObj[1].toString()+".html");
									
									File file = new File(SpringContextAware.getRealPath(pathName.toString()));
									if(file.exists())
									{
										String timeTableFilePath = this.getUploadTimetablesFilePath(file, academicYear.getAcademicYear(),staffObj[1].toString()+".html");
										Staff staff  = (Staff) this.get(Staff.class,"id="+staffObj[0].toString());
										if(!ObjectFunctions.isNullOrEmpty(staff))
										{
											StaffTimetables staffTimetables = new StaffTimetables();
											staffTimetables.setAcademicYearId(academicYear.getId());
											staffTimetables.setCustId(customer.getId());
											staffTimetables.setStaffId(staff.getId());
											staffTimetables.setFilePath(timeTableFilePath);
											staffTimetables.setCreatedDate(new Date());
											staffTimetables = (StaffTimetables) this.save(staffTimetables); 
											
											staff.addStaffTimetables(staffTimetables);
											this.merge(staff);
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					 }
				}
				allActiveCustomersList = null;
			 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return true;
	}
	
	public String getUploadTimetablesFilePath(File file,String academicYear,String fileName)
	{
		try {
			 String ext = FilenameUtils.getExtension(fileName);
			 
			S3Info s3Info = new S3Info();
			s3Info.setAcademicYear(academicYear);
			s3Info.setSource("S");
			s3Info.setType(Types.Timetables);
			s3Info.setFile(file);
			s3Info.setExtension(ext);
			
			S3Wrapper s3Wrapper = new S3Wrapper();
			return s3Wrapper.upload(s3Info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Object[] getAllStudentsByClassNameAndCastAndSubCastName(long custId,long academicYearId,List<CastSettings> castList,String classSectionIds,String userName){
		return adminDao.getAllStudentsByClassNameAndCastAndSubCastName(custId,academicYearId,castList,classSectionIds,userName);
	}
	@Transactional
	public List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDateForPreSchool(long classSectionId, String attendanceDate){
		return adminDao.getStudentsAttendanceByClassSectionIdAndAttendanceDateForPreSchool(classSectionId,attendanceDate);
	}
	
	public boolean moveCertificatesAndDocuments(){
		try {
			
			String mysqlTodayDate = DateFormatter.formatDate (DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, new Date());
			List<Customer> allActiveCustomersList = this.getAll(Customer.class," id >257 and status='Y' and organizationSubTypeId = 0 order by id");
			 if(!ObjectFunctions.isNullOrEmpty(allActiveCustomersList))
			 {
				for(Customer customer : allActiveCustomersList)
				{
					System.out.println("customer......"+customer.getId());
					AcademicYear academicYear  = this.getCurrentAcademicYear("Y",customer.getId());
					
					if(!ObjectFunctions.isNullOrEmpty(academicYear))
					 {
						//Hall Ticket hallTicketSettings
						
						//Study Class Wise time table
				        List<Object[]> studyClassesList = this.getAll("select id,className from studyClass where custId="+customer.getId()+" and academicYearId="+academicYear.getId());
						if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
						{
							for(Object[] studyClassObj : studyClassesList)
							{
								try {
									String pathName="userFiles/StudyAndBonafiedTemplate/"+customer.getCustomerShortName()+"/";
									StudyAndBonafiedSettings studyAndBonafiedSettings = (StudyAndBonafiedSettings)this.get(StudyAndBonafiedSettings.class, "custId="+customer.getId()+" and studyClassId="+studyClassObj[0].toString()+" and filePath is null");
									if(!ObjectFunctions.isNullOrEmpty(studyAndBonafiedSettings))
									{
										if(!StringFunctions.isNullOrEmpty(studyAndBonafiedSettings.getStudyfileName()))
										{
											File file = new File(SpringContextAware.getRealPath(pathName+studyAndBonafiedSettings.getStudyfileName()));
											if(file.exists()){
												String uploadFilePath = this.getUploadTemplates(file, academicYear.getAcademicYear(),studyAndBonafiedSettings.getStudyfileName());
												this.updateQuery("update studyAndBonafiedSettings set fileName='"+studyAndBonafiedSettings.getStudyfileName()+"',filePath='"+uploadFilePath+"',certificateType='SC',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+studyAndBonafiedSettings.getId());
											}
										}
										
										if(!StringFunctions.isNullOrEmpty(studyAndBonafiedSettings.getBonafiedFileName()))
										{
											File file = new File(SpringContextAware.getRealPath(pathName+studyAndBonafiedSettings.getBonafiedFileName()));
											if(file.exists()){
												String uploadFilePath = this.getUploadTemplates(file, academicYear.getAcademicYear(),studyAndBonafiedSettings.getBonafiedFileName());
												
												StudyAndBonafiedSettings studyAndBonafiedSettingsObj = new StudyAndBonafiedSettings();
												
												studyAndBonafiedSettingsObj.setCertificateType("BC");
												studyAndBonafiedSettingsObj.setFileName(studyAndBonafiedSettings.getBonafiedFileName());
												studyAndBonafiedSettingsObj.setFilePath(uploadFilePath);
												studyAndBonafiedSettingsObj.setCustId(customer.getId());
												studyAndBonafiedSettingsObj.setCreatedById(1);
												studyAndBonafiedSettingsObj.setLastUpdatedById(1);
												studyAndBonafiedSettingsObj.setStudyClassId(Long.valueOf(studyClassObj[0].toString()));
												this.save(studyAndBonafiedSettingsObj);												
											}
											
										}
										if(!StringFunctions.isNullOrEmpty(studyAndBonafiedSettings.getDueCertificateFileName()))
										{
											File file = new File(SpringContextAware.getRealPath(pathName+studyAndBonafiedSettings.getDueCertificateFileName()));
											if(file.exists()){
												String uploadFilePath = this.getUploadTemplates(file, academicYear.getAcademicYear(),studyAndBonafiedSettings.getDueCertificateFileName());
												
												StudyAndBonafiedSettings studyAndBonafiedSettingsObj = new StudyAndBonafiedSettings();
												
												studyAndBonafiedSettingsObj.setCertificateType("ND");
												studyAndBonafiedSettingsObj.setFileName(studyAndBonafiedSettings.getDueCertificateFileName());
												studyAndBonafiedSettingsObj.setFilePath(uploadFilePath);
												studyAndBonafiedSettingsObj.setCustId(customer.getId());
												studyAndBonafiedSettingsObj.setCreatedById(1);
												studyAndBonafiedSettingsObj.setLastUpdatedById(1);
												studyAndBonafiedSettingsObj.setStudyClassId(Long.valueOf(studyClassObj[0].toString()));
												this.save(studyAndBonafiedSettingsObj);
												
											}
										}										
									}
									
									
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						 }
						studyClassesList = null;
						List<Object[]> classesList = this.getAll("select id,className from class where custId="+customer.getId()+" and academicYearId="+academicYear.getId());
						if(!ObjectFunctions.isNullOrEmpty(classesList))
						{
							for(Object[] className : classesList){
								String pathName1="userFiles/HTTemplate/"+customer.getCustomerShortName()+"/";
								String uploadFilePath = null;
								HallTicketSettings htSettings = (HallTicketSettings)this.get(HallTicketSettings.class, "custId="+customer.getId()+" and classNames='"+className[1].toString()+"'");
								if(!ObjectFunctions.isNullOrEmpty(htSettings)){
									File file = new File(SpringContextAware.getRealPath(pathName1+htSettings.getFileName()));
									if(file.exists()){
										uploadFilePath = this.getUploadTemplates(file, academicYear.getAcademicYear(),htSettings.getFileName());
									}
										
				 						if(ObjectFunctions.isNullOrEmpty(htSettings))
				 							htSettings = new HallTicketSettings();
				 						htSettings.setFileName(htSettings.getFileName());
				 						htSettings.setFilePath(uploadFilePath);
				 						htSettings.setCustId(customer.getId());
				 						htSettings.setCreatedById(1);
				 						htSettings.setLastUpdatedById(1);
				 						htSettings.setClassNames(className[1].toString());
				 						this.save(htSettings);
				 						className=null;
								}
							}
							classesList=null;
						}
						//Tc And Lc
						List<Object[]> tcSettingsList = this.getAll("select id,fileName,type from tcSettings where custId="+customer.getId() +" and filePath is null");
						if(!ObjectFunctions.isNullOrEmpty(tcSettingsList))
						{
							StringBuffer pathName=null;
							for(Object[] tcSettingsObj : tcSettingsList)
							{
								try 
								{
									if(!ObjectFunctions.isNullOrEmpty(tcSettingsObj[2]))
									{
										if("templateSettings".equalsIgnoreCase(tcSettingsObj[2].toString()))
											pathName = new StringBuffer("userFiles/TCTemplate/").append(customer.getCustomerShortName()).append("/");
										else
											 pathName = new StringBuffer("userFiles/LCTemplate/").append(customer.getCustomerShortName()).append("/");
										
										File file = new File(SpringContextAware.getRealPath(pathName+tcSettingsObj[1].toString()));
										if(file.exists()){
											String uploadFilePath = this.getUploadTemplates(file, academicYear.getAcademicYear(),tcSettingsObj[1].toString());
											this.updateQuery("update tcSettings set filePath='"+uploadFilePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1  where id="+tcSettingsObj[0].toString());
										}
									}
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						
						
						//Study Materials
						List<Object[]> materialList = this.getAll("select IFNULL(materialId,0) as materialId,IFNULL(classSectionId,0) as classSectionId,IFNULL(subjectId,0) as subjectId,IFNULL(academicYearId,0) as academicYearId from vw_studyClassMaterials where custId="+customer.getId());
						if(!ObjectFunctions.isNullOrEmpty(materialList))
						{
							for(Object[] studyMaterialsObj : materialList)
							{
							    
							    try {
							    	StringBuilder pathName = new StringBuilder("userFiles/").append("StudyMaterial/").append(customer.getCustomerShortName());
								    pathName.append("/").append(studyMaterialsObj[1].toString()).append("/").append(studyMaterialsObj[2].toString()).append("/").append(studyMaterialsObj[0].toString()).append("/");
							    	if(StringFunctions.isNotNullOrEmpty(pathName.toString())){
								    	File destFile = new File(SpringContextAware.getRealPath(pathName.toString()));
								    	String[] children = destFile.list();
										List FileNamesList = new ArrayList();
								        if (children == null) {
								        	FileUtils.deleteDirectory(destFile);
								        } else {
								            for (int i=0; i<children.length; i++) {
								            	String fileName = children[i];
								            	
								            	File file = new File(SpringContextAware.getRealPath(pathName+fileName));
								            	if(file.exists())
								            	{
								            		String filePath = this.getUploadDocuments(file, studyMaterialsObj[3].toString(), fileName);
									    			if(!StringFunctions.isNullOrEmpty(filePath))
									    			{
									    				StudyMaterialAttachments studyMaterialAttachments = new StudyMaterialAttachments();
									    				studyMaterialAttachments.setFileName(fileName);
									    				studyMaterialAttachments.setFilePath(filePath);
									    				studyMaterialAttachments.setLastUpdatedDate(new Date());
									    				studyMaterialAttachments.setLastUpdatedById(1);
									    				studyMaterialAttachments.setCreatedById(1);
									    				studyMaterialAttachments = (StudyMaterialAttachments)this.saveOrUpdateObject(studyMaterialAttachments);
									    				this.updateQuery("update studyMaterialAttachments set studyMaterialsId='"+studyMaterialsObj[0].toString()+"' where id="+studyMaterialAttachments.getId());
									    				studyMaterialAttachments = null;
									    			}
								            	}
								            }
								        }
								    }
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
						}
						
						//Score Card
						List<Object[]> scoreCardTemplatesList = this.getAll("select id,fileName,docFileName from scoreCardTemplates where custId="+customer.getId());
						if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplatesList))
						{
							for(Object[] scoreCardTemplatesObj : scoreCardTemplatesList)
							{
							    if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplatesObj[1]))
							    {
							    	File file = new File(SpringContextAware.getRealPath(scoreCardTemplatesObj[1].toString()));
									if(file.exists())
									{
										String fileName = scoreCardTemplatesObj[1].toString().substring(scoreCardTemplatesObj[1].toString().lastIndexOf("/")+1);
										
										String uploadFilePath = this.getUploadTemplates(file, academicYear.getAcademicYear(),fileName);
										this.updateQuery("update scoreCardTemplates set filePath='"+uploadFilePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1,fileName='"+fileName+"'  where id="+scoreCardTemplatesObj[0].toString());
									}
							    }
							    if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplatesObj[2]))
							    {
							    	File file = new File(SpringContextAware.getRealPath(scoreCardTemplatesObj[2].toString()));
									if(file.exists())
									{
										String fileName = scoreCardTemplatesObj[2].toString().substring(scoreCardTemplatesObj[2].toString().lastIndexOf("/")+1);
										String uploadFilePath = this.getUploadTemplates(file, academicYear.getAcademicYear(),fileName);
										this.updateQuery("update scoreCardTemplates set filePath='"+uploadFilePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1,docFileName='"+fileName+"'  where id="+scoreCardTemplatesObj[0].toString());
									}
							    }
							}
						}
					    
						//upload Student Download documents
						List<Object[]> vwStudentClassDetails = this.getAll("select studId,accountId,admissionNumber,academicYear,academicYearId from vw_studentClassDetails where custId="+customer.getId());
						if(!ObjectFunctions.isNullOrEmpty(scoreCardTemplatesList))
						{
							for(Object[] vwStudentClassDetailsObj : vwStudentClassDetails)
							{
							    try {
									if(!ObjectFunctions.isNullOrEmpty(vwStudentClassDetailsObj[1]))
									{
										StringBuilder pathName = new StringBuilder("userFiles/").append(customer.getId()).append("/");
									    pathName.append("/");
									    pathName.append(vwStudentClassDetailsObj[2].toString());
									    try {
									    	 uploadDocumentsFromBatch(pathName, vwStudentClassDetailsObj[1].toString(),vwStudentClassDetailsObj[4].toString(),vwStudentClassDetailsObj[3].toString());
												pathName = new StringBuilder("userFiles/").append("parentAndAdmin/").append(customer.getCustomerShortName());
											    pathName.append("/");
											    pathName.append(vwStudentClassDetailsObj[1].toString());
												try {
													 uploadDocumentsFromBatch(pathName, vwStudentClassDetailsObj[1].toString(),vwStudentClassDetailsObj[4].toString(),vwStudentClassDetailsObj[3].toString());
												} catch (Exception e) {
													e.printStackTrace();
												}
											   
											    pathName = null;
										} catch (Exception e) {
											e.printStackTrace();
										}
									   
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							    vwStudentClassDetailsObj = null;
							}
						}
						vwStudentClassDetails = null;
						
						
						//upload Staff Download documents
						List<Object[]> vwStaffDetailsList = this.getAll("select staffId,accountId,firstName,academicYear,academicYearId from vw_staffDetails where custId="+customer.getId()+" and isDocsUploaded='Y'");
						if(!ObjectFunctions.isNullOrEmpty(vwStaffDetailsList))
						{
							for(Object[] vwStaffDetails : vwStaffDetailsList)
							{
							    try {
									if(!ObjectFunctions.isNullOrEmpty(vwStaffDetails[1]))
									{
										String memberName = vwStaffDetails[2].toString().replaceAll(" ", "_");
										StringBuilder pathName = new StringBuilder("userFiles/Staff/");
										pathName.append(customer.getCustomerShortName()+customer.getId()+memberName+"_"+vwStaffDetails[1].toString()+vwStaffDetails[0].toString());
									    //pathName.append("/");
									    uploadDocumentsFromBatch(pathName, vwStaffDetails[1].toString(),vwStaffDetails[4].toString(),vwStaffDetails[3].toString());
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							    vwStaffDetails = null;
							}
						}
						vwStaffDetailsList = null;
						
						//Class Assignments
						List<ClassAssignment> classAssignmentList =	 this.getAll(ClassAssignment.class,"custId="+customer.getId()+" and isDocsUpload='Y'");
						if(!ObjectFunctions.isNullOrEmpty(classAssignmentList))
						{
							for(ClassAssignment classAssignment : classAssignmentList)
							{
							    if(!ObjectFunctions.isNullOrEmpty(classAssignment.getAttachmentList()))
								{
							    	for(Attachment attachment : classAssignment.getAttachmentList())
									{
							    		 try 
							    		 {
							    			 File file = new File(SpringContextAware.getRealPath(attachment.getFilePath()+attachment.getFileName()));
								    		 String fileName = attachment.getFileName();
								    		 String filePath = this.getUploadDocuments(file, academicYear.getAcademicYear(), fileName);
							    			 this.updateQuery("update attachment set filePath='"+filePath+"',lastUpdatedDate='"+mysqlTodayDate+"',lastUpdatedById=1 where id="+attachment.getId());
							    			 
										} catch (Throwable e) {
											e.printStackTrace();//RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
										}
									}
								}
							}
						}
						classAssignmentList = null;
						
						
					
					 }
				}
				allActiveCustomersList = null;
			 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return false;
		}
		return true;
	}
	
	public String getUploadTemplates(File file,String academicYear,String fileName)
	{
		try {
			 String ext = FilenameUtils.getExtension(fileName);
			 
			S3Info s3Info = new S3Info();
			s3Info.setAcademicYear(academicYear);
			s3Info.setSource("S");
			s3Info.setType(Types.Templates);
			s3Info.setFile(file);
			s3Info.setExtension(ext);
			
			S3Wrapper s3Wrapper = new S3Wrapper();
			return s3Wrapper.upload(s3Info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getUploadDocuments(File file,String academicYear,String fileName)
	{
		try {
			 String ext = FilenameUtils.getExtension(fileName);
			 
			S3Info s3Info = new S3Info();
			s3Info.setAcademicYear(academicYear);
			s3Info.setSource("S");
			s3Info.setType(Types.Documents);
			s3Info.setFile(file);
			s3Info.setExtension(ext);
			
			S3Wrapper s3Wrapper = new S3Wrapper();
			return s3Wrapper.upload(s3Info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void uploadDocumentsFromBatch(StringBuilder pathName, String accountId,String academicYearId,String academicYearName)
	{
		try {
			pathName.append("/");
			if(StringFunctions.isNotNullOrEmpty(pathName.toString()))
			{
		    	File destFile = new File(SpringContextAware.getRealPath(pathName.toString()));
		    	String[] children = destFile.list();
		        if (children == null) {
		        	FileUtils.deleteDirectory(destFile);
		        } else {
		            for (int i=0; i<children.length; i++) 
		            {
		                // Get filename of file or directory
		                String fileName = children[i];
		                File file = new File(SpringContextAware.getRealPath(pathName+fileName));
		            	if(file.exists())
		            	{
		            		try {
								String filePath = this.getUploadDocuments(file, academicYearName, fileName);
								if(!StringFunctions.isNullOrEmpty(filePath))
								{
									PersonDocuments personDocuments = new PersonDocuments();
									personDocuments.setFileName(fileName);
									personDocuments.setCreatedById(1);
									personDocuments.setFilePath(filePath);
									personDocuments.setAcademicYearId(Long.valueOf(academicYearId));
									personDocuments.setLastUpdatedDate(new Date());
									personDocuments.setLastUpdatedById(1);
									
									personDocuments = (PersonDocuments)this.saveOrUpdateObject(personDocuments);
									this.updateQuery("update personDocuments set accountId='"+accountId+"' where id="+personDocuments.getId());
									personDocuments = null;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
		            	}
		            }
		        }
		    	
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeClassTeacherByStudyClassId(long custId, long studyClassId){
		this.updateQuery("update classTeacher set classTeacher= 'N' where custId="+ custId + " and studyClassId=" + studyClassId);
	}	
	
	public List<ClassTeacher> getClassTeachersListByStaffId(String query){
		return adminDao.getClassTeachersListByStaffId(query);
	}
	@Override
	public List getAllStaffMobileNumbersByRole(long custId, String roleIds) {
		return adminDao.getAllStaffMobileNumbersByRole(custId, roleIds);
	}
	public List<ViewStudentDeleteFeeDetails> getStudentDeleteInvoiceDetailsById(long userCustId,	long userAcademicYearId, String feeType) {  
		return  adminDao.getStudentDeleteInvoiceDetailsById(userCustId,userAcademicYearId, feeType);
	}
	public List<ViewStudentDeleteFeeDetails> getStudentDeleteOtherFeeDetailsById(long userCustId, long userAcademicYearId, String feeType) {
		return  adminDao.getStudentDeleteOtherFeeDetailsById(userCustId,userAcademicYearId, feeType);
	}
	@Transactional
	public List<StudyClass> getStudyClasses(String queryString){
		return adminDao.getStudyClasses(queryString);
	}
	/**
	 * This Method is used to get the all student payment details based on the paymentId send by the desktop application.
	 */
	public StudentPaymentListVO getStudentPaymentDetails(long custId,long academicYearId,long paymnetId){
		try {
			
			KeyIdentifierVO identifier = new KeyIdentifierVO();
			identifier.setAcademicYearId(academicYearId);
			identifier.setCustId(custId);
			StudentPaymentListVO studentPaymentDetailsVO = new StudentPaymentListVO();
			studentPaymentDetailsVO.setIdentifier(identifier);
			CustomerBankAccountDetails customerBankAccountDetails = (CustomerBankAccountDetails)this.get(CustomerBankAccountDetails.class, "custId="+custId);
			List<VWStudentPaymentDetails> studentPaymentDetailsList = adminDao.getStudentPaymentDetails(custId,academicYearId,paymnetId);
			
			List<StudentFeePaymentVO> studentFeePaymentListVO = new ArrayList<StudentFeePaymentVO>();
			StudentFeePaymentVO studentFeePaymentVO = null;
			List<PaymentDetailsVO> paymentDetailsListVO = null;
			PaymentDetailsVO paymentDetailsVO = null;
			ChallanaPaymentVO challanaPaymentVO=null;
			 ExcessPaymnetVO excessPaymnetVO = null;
			long temPaymnetId = 0;
			for(VWStudentPaymentDetails paymentDetails:studentPaymentDetailsList){
				if(temPaymnetId != paymentDetails.getStudentPaymentId()){
					if(temPaymnetId > 0){
						studentFeePaymentVO.setPaymentDetails(paymentDetailsListVO);
						studentFeePaymentVO.setChallanaPayment(challanaPaymentVO);
						studentFeePaymentVO.setExcessPaymnet(excessPaymnetVO);
						studentFeePaymentListVO.add(studentFeePaymentVO);
						studentFeePaymentVO = null;
						paymentDetailsListVO = null;
						excessPaymnetVO = null;
						challanaPaymentVO=null;
					}
					paymentDetailsListVO = new ArrayList<PaymentDetailsVO>();
					temPaymnetId = paymentDetails.getStudentPaymentId();
					studentFeePaymentVO = new StudentFeePaymentVO();
					studentFeePaymentVO.setId(paymentDetails.getStudentPaymentId());
					studentFeePaymentVO.setStudentId(paymentDetails.getStudentId());
					studentFeePaymentVO.setInvoiceNumber(paymentDetails.getInvoiceNumber());
					studentFeePaymentVO.setPaidDate(ObjectFunctions.isNullOrEmpty(paymentDetails.getPaidDate())? "": DateFunctions.convertDateToYYYYMMDDString(paymentDetails.getPaidDate()));
					studentFeePaymentVO.setPaymentType(paymentDetails.getPaymentType());
					studentFeePaymentVO.setPaidAmount(paymentDetails.getPaidAmount());
					studentFeePaymentVO.setDiscountAmount(paymentDetails.getDiscountAmount());
					studentFeePaymentVO.setDueAmount(paymentDetails.getDueAmount());
					if(!ObjectFunctions.isNullOrEmpty(paymentDetails.getBankName()) && !(paymentDetails.getBankName().toUpperCase().contains("SELECT"))){
						studentFeePaymentVO.setBankName(paymentDetails.getBankName());
						studentFeePaymentVO.setBranchName(paymentDetails.getBranchName());
					}
					studentFeePaymentVO.setInstrumentNumber(paymentDetails.getInstrumentNumber());
					studentFeePaymentVO.setInstrumentDate(ObjectFunctions.isNullOrEmpty(paymentDetails.getInstrumentDate())? "": DateFunctions.convertDateToYYYYMMDDString(paymentDetails.getInstrumentDate()));
					studentFeePaymentVO.setDeleteStatus(paymentDetails.getDeleteStatus());
					studentFeePaymentVO.setDeleteCause(paymentDetails.getDeleteCause());
					studentFeePaymentVO.setDpPaymentDetailsId(paymentDetails.getDpPaymentDetailsId());
					
					if(paymentDetails.getCP_challanaId()>0){
						challanaPaymentVO = new ChallanaPaymentVO();
						challanaPaymentVO.setId(paymentDetails.getCP_challanaId());
						challanaPaymentVO.setChallanaNumber(paymentDetails.getCP_challanaNumber());
						challanaPaymentVO.setChallanaDate(paymentDetails.getCP_challanaDate());
						challanaPaymentVO.setReceivedDate(paymentDetails.getCP_receivedDate());
						challanaPaymentVO.setDeleteStatus(paymentDetails.getCP_deleteStatus());
						challanaPaymentVO.setBankId(ObjectFunctions.isNullOrEmpty(customerBankAccountDetails)? 0: customerBankAccountDetails.getId());
					}
					
				}
				paymentDetailsVO = new PaymentDetailsVO();
				paymentDetailsVO.setId(paymentDetails.getStudentFeePaidDetailsId());
				paymentDetailsVO.setAmount(Double.parseDouble(paymentDetails.getSpd_PaymentAmount().toString()));
				paymentDetailsVO.setDiscountAmount(paymentDetails.getSpd_DiscountAmount());
				paymentDetailsVO.setClassFeeId(paymentDetails.getSpd_ClassFeeId());
				paymentDetailsVO.setTransportFeeId(paymentDetails.getSpd_TransportFeeId());
				paymentDetailsListVO.add(paymentDetailsVO);
				
				if(!ObjectFunctions.isNullOrEmpty(paymentDetails.getExcessPaymentId()) && paymentDetails.getExcessPaymentId() > 0){
					excessPaymnetVO  = new ExcessPaymnetVO();
					excessPaymnetVO.setId(paymentDetails.getExcessPaymentId());
					excessPaymnetVO.setAmount(paymentDetails.getExcessAmount());
					excessPaymnetVO.setIsUsed(ObjectFunctions.isNullOrEmpty(paymentDetails.getIsUsed())? "N": (paymentDetails.getIsUsed()?"Y":"N"));
					excessPaymnetVO.setUsedPaymnetId(paymentDetails.getUsedPaymentId());
				}
			}
			//adding last record
			studentFeePaymentVO.setPaymentDetails(paymentDetailsListVO);
			studentFeePaymentVO.setChallanaPayment(challanaPaymentVO);
			studentFeePaymentVO.setExcessPaymnet(excessPaymnetVO);
			studentFeePaymentListVO.add(studentFeePaymentVO);
			studentFeePaymentVO = null;
			paymentDetailsListVO = null;
			excessPaymnetVO = null;
			studentPaymentDetailsVO.setStudentPayments(studentFeePaymentListVO);
					
			return studentPaymentDetailsVO;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<ExamTypes> getExamTypesByStudyClassId(long studyClassId){
		return adminDao.getExamTypesByStudyClassId(studyClassId);
	}
	
	public List<StudySubject> getSubjectsByStudyClassId(long studyClassId){
		return adminDao.getSubjectsByStudyClassId(studyClassId);
	}
	public List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeePaymentDetails(long studentId,long academicYearId, long feeSettingId){
		return adminDao.getAllStudentTermsWiseTransportFeePaymentDetails(studentId,academicYearId,feeSettingId);
	}
	public List<ViewStudentTransportFeePaymentDetails> getStudentTransportFeeDetails(long studentId,long academicYearId,long termId){
		return adminDao.getStudentTransportFeeDetails(studentId,academicYearId,termId);
	}
	public List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeeViewPaymentDetails(long studentId, long academicYearId, long feeSettingId) {
		return adminDao.getAllStudentTermsWiseTransportFeeViewPaymentDetails(studentId,academicYearId,feeSettingId);
	}
	public List<ViewStudentTransportFeePaymentDetails> getAllStudenTransportFeePaymentDetails(long studentId, long academicYearId, long schoolTermId) {
		return adminDao.getAllStudenTransportFeePaymentDetails(studentId,academicYearId,schoolTermId);
	}
	public ViewStudentTransportFeePaymentDetails getStudentNonPaidTransportFeePaymentDetails(long studentId,long academicYearId,long termId,long feeTypeId){
		return adminDao.getStudentNonPaidTransportFeePaymentDetails(studentId,academicYearId,termId,feeTypeId);
	}
	public List<ViewStudentFeePaymentDetails> getStudentFeeUnpaidDetails(String tableName,long custId,long academicYearId,String termId,String selectedClassSectionIds){
		return adminDao.getStudentFeeUnpaidDetails(tableName,custId, academicYearId, termId,selectedClassSectionIds);
	}
	public ViewStudentFeePaymentDetails getStudentWiseFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,long termId,long classSectionId,long studentId){
		return adminDao.getStudentWiseFeePaidAndUnpaidDetails(tableName,custId,academicYearId,termId,classSectionId,studentId);
	}

	public Student upadateStudentMarksFromSMSAppAndWebNew(Student student,long examScheduleId, double obtainedMarks, long accountId,long examTypeId, String isPresent,HashMap<Integer, String> showingResult,HashMap<Long, ExamSchedules> schedulesMap) {
		try {
			StudentMarks studentMarks = null;
			List<Object[]> schedules = null;
			ExamSchedules examSchedules = null;

			if (schedulesMap.containsKey(examScheduleId)) {
				examSchedules = schedulesMap.get(examScheduleId);
			}

			showingResult.put(0, Boolean.toString(true));
			Object[] totalSubMarksObtained = null;
			if (!ObjectFunctions.isNullOrEmpty(examSchedules)) {
				showingResult.put(1, String.valueOf(examTypeId));

				studentMarks = new StudentMarks();
				studentMarks.setPresent(true);
				studentMarks.setCreatedDate(new Date());
				studentMarks.setCreatedById(accountId);

				if (isPresent.equalsIgnoreCase("P")) {
					if (examSchedules.getMaxMarks() >= obtainedMarks) {
						studentMarks.setObtainedMarks(obtainedMarks);
						studentMarks.setPresent(true);
						student.setErrorMsg(null);
					} else {
						showingResult.put(0, Boolean.toString(false));
						if (!ObjectFunctions.isNullOrEmpty(examSchedules))
							if (examSchedules.getMaxMarks() < obtainedMarks) {
								if (!ObjectFunctions.isNullOrEmpty(student.getAccount()))
									if (!StringFunctions.isNullOrEmpty(student.getAccount().getFullPersonName()))
										student.setErrorMsg(student.getAccount().getFullPersonName());
							}
						return student;
					}
				} else {
					studentMarks.setObtainedMarks(0);
					studentMarks.setPresent(false);
				}
				studentMarks.setExamSchedule(examSchedules);
				studentMarks.setLastUpdatedDate(new Date());
				studentMarks.setLastAccessDate(new Date());
				studentMarks.setStudent(student);
				student.addStudentMarks(studentMarks);
				studentMarks = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex = null;
		}
		return student;
	}
	
	public ViewClassAssignmentDetailsMainVO studentAssignmentDetails(long studentId){
		ViewClassAssignmentDetailsVO classAssignmentDetailsVO = null;
		StudySubjectVO studySubjectVO=null;
		StudyClassVO studyClassVO = null;
		AttachmentVO attachmentVO=null;
		ViewClassAssignmentDetailsMainVO viewClassAssignmentDetailsMainVO=null;
		List<ViewClassAssignmentDetailsVO> viewClassAssignmentDetailsVOList = new ArrayList<ViewClassAssignmentDetailsVO>();
		List<AttachmentVO> attachmentVOList = new ArrayList<AttachmentVO>();
		try { 
			Student student = (Student)this.get(Student.class,"id="+studentId);
			
			 	String query = "";
					query = "SELECT ca.assignmentId, ca.classId, ca.classSectionId, ca.className, ca.assignmentDescription, ca.classAndSection, ca.createdBy, ca.subjectName, ca.subjectId, at.filePath, at.name, at.id, ca.assignmentDate, ca.status FROM vw_classAssignmentDetails ca LEFT JOIN attachment at ON(at.classAssignmentId = ca.assignmentId) WHERE ca.classSectionId = "+ student.getClassSection().getId() +" AND DATE(ca.assignmentDate) >= DATE(CURDATE())";
				
				List<Object[]> assignmentsList = this.getAll(query);
				query = null;
				query = "SELECT assignmentId,assignmentDone FROM studentClassAssignment WHERE studentId ="+studentId;
				List<Object[]> studentAssignmentsList = this.getAll(query);
				List<Long> pendingAssignmentIsList = null;
				if(!ObjectFunctions.isNullOrEmpty(studentAssignmentsList)) {
					pendingAssignmentIsList = new ArrayList<Long>();
					for(Object[] obj :studentAssignmentsList){
						pendingAssignmentIsList.add(Long.valueOf(obj[0].toString()));
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(assignmentsList)) {
					long assignmentId = 0;
					int size = 0;
					//JSONArray assignmentsArray = new JSONArray();
					for(Object[] assignment : assignmentsList) {
						if(classAssignmentDetailsVO == null){
							classAssignmentDetailsVO = new ViewClassAssignmentDetailsVO();
							assignmentId = ObjectFunctions.isNullOrEmpty(assignment[0])? 0: Long.valueOf(assignment[0].toString());
						}
						if(assignmentId>0 && assignmentId!= Long.valueOf(assignment[0].toString()))
						{
							assignmentId = ObjectFunctions.isNullOrEmpty(assignment[0])? 0: Long.valueOf(assignment[0].toString());
							viewClassAssignmentDetailsVOList.add(classAssignmentDetailsVO);
							attachmentVOList = new ArrayList<AttachmentVO>();
							classAssignmentDetailsVO = new ViewClassAssignmentDetailsVO();
						}
												
						classAssignmentDetailsVO.setId(Long.valueOf(assignment[0].toString()));
						classAssignmentDetailsVO.setDescription(ObjectFunctions.isNullOrEmpty(assignment[4])? "": assignment[4].toString());
						classAssignmentDetailsVO.setCreatedBy(ObjectFunctions.isNullOrEmpty(assignment[6])? "": assignment[6].toString());
						classAssignmentDetailsVO.setDate(ObjectFunctions.isNullOrEmpty(assignment[12])? null: assignment[12].toString());
						classAssignmentDetailsVO.setStudyClassId(ObjectFunctions.isNullOrEmpty(assignment[2])? 0: Long.valueOf(assignment[2].toString()));
						classAssignmentDetailsVO.setClassAndSection(ObjectFunctions.isNullOrEmpty(assignment[5])? "": assignment[5].toString());
						classAssignmentDetailsVO.setSubjectId(ObjectFunctions.isNullOrEmpty(assignment[8])? 0: Long.valueOf(assignment[8].toString()));
						classAssignmentDetailsVO.setSubjectName(ObjectFunctions.isNullOrEmpty(assignment[7])? "": assignment[7].toString());
						if(!ObjectFunctions.isNullOrEmpty(pendingAssignmentIsList)){
							if(pendingAssignmentIsList.contains(classAssignmentDetailsVO.getId())){
								classAssignmentDetailsVO.setStatus("O"); // Not yet finished assignment
							}else{
								classAssignmentDetailsVO.setStatus("C"); // completed assignment
							}
						}else{
							if(!ObjectFunctions.isNullOrEmpty(assignment[13])){
								if("P".equalsIgnoreCase(assignment[13].toString())){
									classAssignmentDetailsVO.setStatus("O"); // Not yet finished assignment
								}else{
									classAssignmentDetailsVO.setStatus("C"); // completed assignment
								}
							}
						}
						
						if((!ObjectFunctions.isNullOrEmpty(assignment[11]) )&& (!ObjectFunctions.isNullOrEmpty(assignment[9]) ) &&(!ObjectFunctions.isNullOrEmpty(assignment[10])) )
						{
							// Attachments
							attachmentVO = new AttachmentVO();
							attachmentVO.setId(ObjectFunctions.isNullOrEmpty(assignment[11])? 0: Long.valueOf(assignment[11].toString()));
							attachmentVO.setFilePath(ObjectFunctions.isNullOrEmpty(assignment[9])? "": assignment[9].toString());
							attachmentVO.setFileName(ObjectFunctions.isNullOrEmpty(assignment[10])? "": assignment[10].toString());
							attachmentVOList.add(attachmentVO);
							classAssignmentDetailsVO.setAttachments(attachmentVOList);
							attachmentVO = null;
						}
						if(size == assignmentsList.size()-1){
							assignmentId = ObjectFunctions.isNullOrEmpty(assignment[0])? 0: Long.valueOf(assignment[0].toString());
							viewClassAssignmentDetailsVOList.add(classAssignmentDetailsVO);
							classAssignmentDetailsVO = null;
							
						}
						size++;
					}
					viewClassAssignmentDetailsMainVO = new ViewClassAssignmentDetailsMainVO();
					viewClassAssignmentDetailsMainVO.setClassAssignmentVO(viewClassAssignmentDetailsVOList);
					return viewClassAssignmentDetailsMainVO;
				}else{
					return null;
				}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudentTermsWisePaidClassFeePaymentDetails(long studentId,long academicYearId){
		return adminDao.getStudentTermsWisePaidClassFeePaymentDetails(studentId,academicYearId);
	}
	@Transactional
	public List<ViewStudentFeePaymentDetails> getStudenClassFeePaymentDetails(long studentId,long academicYearId,long termId){
		return adminDao.getStudenClassFeePaymentDetails(studentId,academicYearId,termId);
	}
	
	public List<Object[]> getAttendanceNotSubmittedDates(long classSectionId ,long academicYearId, String monthIds){
		return adminDao.getAttendanceNotSubmittedDates(classSectionId ,academicYearId,monthIds);
	}
	
	public void saveClassTeacherDetails(ClassTeacher classTeacher,long custId,long userId,Staff staff,long studyClassId,StudySubject subject,AcademicYear academicYear){
		try {
			classTeacher.setAcademicYear(academicYear);
	        classTeacher.setClassTeacher(false);
	        classTeacher.setCreatedById(userId);
	        classTeacher.setCreatedDate(new Date());
	        classTeacher.setCustId(custId);
	        classTeacher.setLastAccessDate(new Date());
	        classTeacher.setLastUpdatedById(userId);
	        classTeacher.setLastUpdatedDate(new Date());
	        classTeacher.setPeriodsCount(0);
	        classTeacher.setStaff(staff);
	        //classTeacher.setStudyClass(studyClass);
	        classTeacher.setStudySubject(subject);
	        classTeacher = (ClassTeacher) this.saveOrUpdateObject(classTeacher);
			/* @Ganesh Here study class have performance issue when we fetch record it coming very late. So we removed study class object and once we save classTeacher object they we
			 * are updating study class id to classTecher object. */
			this.updateQuery("UPDATE classTeacher SET studyClassId="+ studyClassId+ " WHERE id="+ classTeacher.getId());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		
	}
	@Override
	public List<Object[]> getParentOccupationDetails(long custId,	long academicYearId, String classIds){
		return adminDao.getParentOccupationDetails(custId, academicYearId, classIds);
	}
	public Map<String, String> saveOrUpdateTimetableDetails(String tempString,long studyClassId, String changeInTimetable, User user, Date startDate) {
		Map<String, String> msg = new HashMap<String,String>();
		try{
			TimeTableDetails timeTableDetails=null;
			StudyClass studyClass = null;
			long subjectId = 0;
			TimeTablePeriod timeTablePeriod=null;
			JSONObject subjectJson=null;
			JSONArray subjectJsonArray=null;
			Date oneDayBefore= null;
			List<TimeTableDetails> timeTableDetailsList = null;
			StudySubject studySubject = null;
			subjectJsonArray=new JSONArray(tempString);
			long weekPeriodId=0;
			studyClass=(StudyClass)this.get(StudyClass.class,studyClassId);
			if(!StringFunctions.isNullOrEmpty(changeInTimetable) && Constants.YES_STRING.equalsIgnoreCase(changeInTimetable)){
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DAY_OF_YEAR,-1);
				oneDayBefore= cal.getTime();
				timeTableDetailsList = this.getAll(TimeTableDetails.class, "studyClassId="+studyClassId+" and status='"+Constants.YES_STRING+"' and toDate is null");
				if(!ObjectFunctions.isNullOrEmpty(timeTableDetailsList)){
					for(TimeTableDetails timeTableDetails2 : timeTableDetailsList){
						timeTableDetails = (TimeTableDetails) this.get(TimeTableDetails.class, "id="+timeTableDetails2.getId());
						if(!ObjectFunctions.isNullOrEmpty(timeTableDetails)){
							timeTableDetails.setToDate(oneDayBefore);
							timeTableDetails.setStatus(Constants.STATUS_FALSE);
							timeTableDetails.setLastUpdatedById(user.getId());
							timeTableDetails.setLastUpdatedDate(new Date());
							this.save(timeTableDetails);
						}
					}timeTableDetailsList=null;studySubject=null;
				}
			}
			for(int i=0;i<subjectJsonArray.length();i++){
				subjectJson=subjectJsonArray.getJSONObject(i);
				if(!ObjectFunctions.isNullOrEmpty(subjectJson)){
					if(StringFunctions.isNotNullOrEmpty(subjectJson.get("weekPeriodId").toString())){
						weekPeriodId = Long.valueOf(subjectJson.get("weekPeriodId").toString());
						if(weekPeriodId>0){
							if(StringFunctions.isNotNullOrEmpty(subjectJson.get("subjectId").toString())){
								subjectId = Long.valueOf(subjectJson.get("subjectId").toString());
								if(subjectId>0){
									timeTableDetails = new TimeTableDetails();
							    	timeTableDetails.setDayId(Long.valueOf(subjectJson.get("dayId").toString()));
							    	timeTableDetails.setStudyClassId(studyClassId);
							    	timeTableDetails.setStudySubjectId(subjectId);
							    	timeTableDetails.setTimeTablePeriodId(Long.valueOf(subjectJson.get("periodId").toString()));
							    	if(!StringFunctions.isNullOrEmpty(changeInTimetable) && changeInTimetable.equalsIgnoreCase(Constants.YES_STRING)){
										timeTableDetails.setFromDate(startDate);
							    	}else if(!ObjectFunctions.isNullOrEmpty(studyClass) && !ObjectFunctions.isNullOrEmpty(studyClass.getAcademicYear().getStartDate())){
							    		timeTableDetails.setFromDate(studyClass.getAcademicYear().getStartDate());
							    	}
							    	timeTableDetails.setStatus(Constants.STATUS_TRUE);
							    	this.save(timeTableDetails);
							    	timeTableDetails=null;
								}
						    	subjectId=0;
							}
						}weekPeriodId=0;
					}
					subjectJson=null;
				}
			}subjectJsonArray=null;studyClass=null;
			msg.put("0", "Assign subjects to periods successfully.");
		}catch(Exception ex){
			msg.put("1","Timetable details not added, please contact support team.");
			ex.printStackTrace();
		}
		return msg;
	}
	@Override
	public List<StudentMarks> getLatestUploadedMarksForStudent(
			long classSectionId, long academicYearId, int noOfExamTypes,
			long studentId) {
		return adminDao.getLatestUploadedMarksForStudent(classSectionId,academicYearId,noOfExamTypes,studentId);
	}
	public List<Object[]> getVehiclesWithDriverDetails(long academicYearId,boolean status){
		return adminDao.getVehiclesWithDriverDetails(academicYearId, status);

	}
	
	public int addOrUpdateQuestionPaperBank(QuestionPaperBankVO questionPaperBankVO,User user,AcademicYear academicYear){
		if(log.isInfoEnabled()){
			log.info("Entering AdminManager 'addOrUpdateQuestionPaperBank' method");
		}
		try {
			QuestionPaperBank questionPaperBank = null;
			Attachment attachment=null;
			List<Attachment> attachmentList=null;
			String academicYearStr=academicYear.getAcademicYear();
			if(!ObjectFunctions.isNullOrEmpty(questionPaperBankVO) && questionPaperBankVO.getId() > 0){
				questionPaperBank = (QuestionPaperBank) this.get(QuestionPaperBank.class, questionPaperBankVO.getId());
			} 
			else {
				questionPaperBank = new QuestionPaperBank();
				questionPaperBank.setCreatedById(user.getId());
				questionPaperBank.setCreatedDate(new Date());
			}
			questionPaperBankVO.setCustId(user.getCustId());
			questionPaperBankVO.setAcademicYearId(academicYear.getId());
			questionPaperBank.copyFromVoToEntity(questionPaperBankVO);
			if(!ObjectFunctions.isNullOrEmpty(questionPaperBankVO.getStudyClassVO())){
				if(questionPaperBankVO.getStudyClassVO().getId() > 0){
					StudyClass studyClass = (StudyClass) this.get(StudyClass.class,questionPaperBankVO.getStudyClassVO().getId());
					questionPaperBank.setStudyClass(studyClass);
					studyClass = null;
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(questionPaperBankVO.getStudySubjectVO())){
				if(questionPaperBankVO.getStudySubjectVO().getId() > 0){
					StudySubject studySubject = (StudySubject) this.get(StudySubject.class, questionPaperBankVO.getStudySubjectVO().getId());
					questionPaperBank.setStudySubject(studySubject);
					studySubject = null;
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(questionPaperBankVO.getStaffSyllabusPlannerVo())){
				if(questionPaperBankVO.getStaffSyllabusPlannerVo().getId() > 0){
					StaffSyllabusPlanner syllabusPlanner = (StaffSyllabusPlanner) this.get(StaffSyllabusPlanner.class,questionPaperBankVO.getStaffSyllabusPlannerVo().getId());
					questionPaperBank.setStaffSyllabusPlanner(syllabusPlanner);
					syllabusPlanner = null;
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(questionPaperBankVO.getFileUpload())){
				if(!ObjectFunctions.isNullOrEmpty(questionPaperBankVO.getFileUpload().get(0))){
					for(int i=0;i<questionPaperBankVO.getFileUpload().size();i++){
						attachment = addOrUploadAttachments(questionPaperBankVO.getFileUpload().get(i),academicYearStr,questionPaperBankVO.getFileUploadFileName().get(i));
						questionPaperBank.addAttachments(attachment);
					}
				}
			}
			this.saveOrUpdateObject(questionPaperBank);
			if(!ObjectFunctions.isNullOrEmpty(questionPaperBankVO) && questionPaperBankVO.getId() > 0){
				questionPaperBank = null;
				return 1;
			}
			else
				return 2;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			questionPaperBankVO = null;
		}
		return 0;
	}
	public Attachment addOrUploadAttachments(File fileUpload,String academicYearStr, String fileUploadFileName){
		if (log.isInfoEnabled()) {
			log.info("Entering AdminManager 'addOrUploadAttachments' method");
		}try {
			Attachment attachment=null;
			if(!ObjectFunctions.isNullOrEmpty(fileUpload)){
				if(!StringFunctions.isNullOrEmpty(fileUploadFileName)){
					String filePath =  this.getUploadDocuments(fileUpload, academicYearStr, fileUploadFileName);
					if(!StringFunctions.isNullOrEmpty(filePath))
					{	
						attachment=new Attachment();
						attachment.setFileName(fileUploadFileName);
						attachment.setFilePath(filePath);
					}
				}
			}
			return attachment;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public List<QuestionPaperBankVO> getQuestionBankList(long userCustId, long  academicYearId, String classSectionId, String subjectId){
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'getQuestionBankList' method");
		}
		try {
			List objectList = new ArrayList<QuestionPaperBankVO>();
			List<QuestionPaperBank> questionBankList=this.getAll(QuestionPaperBank.class, " custId = "+ userCustId + " and academicYearId ="+ academicYearId+" and studyClassId="+classSectionId+" and subjectId="+subjectId);
			QuestionPaperBankVO questionPaperBankVO=null;
			if(!ObjectFunctions.isNullOrEmpty(questionBankList)){
				for(QuestionPaperBank questionPaperBank:questionBankList){
					questionPaperBankVO= questionPaperBank.copyFromEntityToVO();
			        questionPaperBankVO.getStaffSyllabusPlannerVo().setId(questionPaperBank.getStaffSyllabusPlanner().getId());
					questionPaperBankVO.getStaffSyllabusPlannerVo().setChapterName(questionPaperBank.getStaffSyllabusPlanner().getChapterName());
					List<Attachment> attachmentList=this.getAll(Attachment.class,"questionPaperBankId="+ questionPaperBank.getId());
						if(!ObjectFunctions.isNullOrEmpty(attachmentList)){
							for(Attachment attachment:attachmentList){
								AttachmentVO attachmentVO=new AttachmentVO();
								if(!ObjectFunctions.isNullOrEmpty(attachment)){
									attachmentVO.setFileName(attachment.getFileName());
									attachmentVO.setFilePath(attachment.getFilePath());
									attachmentVO.setId(attachment.getId());
								}
								questionPaperBankVO.getAttachmentVOs().add(attachmentVO);
								attachmentVO=null;
							}
						}
					objectList.add(questionPaperBankVO);
					questionPaperBankVO=null;
				}
			}
			return objectList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int removeQuestionBank(long questionBankId){
		if (log.isInfoEnabled()) {
			log.info("Entering AdminManager 'removeQuestionBank' method");
		}try{
			if(questionBankId > 0){
				List<Attachment> attachmentList=super.getAll(Attachment.class,"questionPaperBankId="+questionBankId);
				if(!ObjectFunctions.isNullOrEmpty(attachmentList)){
					this.updateQuery("delete from attachment where questionPaperBankId="+questionBankId);
					this.updateQuery("delete from questionPaperBank where id="+questionBankId);
					attachmentList=null;
					return 1;
				}else{
					this.updateQuery("delete from questionPaperBank where id="+questionBankId);
					return 1;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public QuestionPaperBankVO getQuestionBankByLesson(long custId,long academicYearId,String classSectionId,String subjectId,String lessonId,String questionBankId){
		if (log.isInfoEnabled()) {
			log.info("Entering AdminManager 'getQuestionBankByLesson' method");
		}try{
			QuestionPaperBankVO questionPaperBankVO=null;
			if(!StringFunctions.isNullOrEmpty(lessonId)){
				QuestionPaperBank questionBank=(QuestionPaperBank) this.get(QuestionPaperBank.class, "id="+questionBankId+" and custId="+custId+" and academicYearId="+academicYearId+" and studyClassId="+classSectionId+" and subjectId="+subjectId+" and syllabusPlannerId="+lessonId);
				if(!ObjectFunctions.isNullOrEmpty(questionBank)){
					questionPaperBankVO= questionBank.copyFromEntityToVO();
					List<Attachment> attachmentList=this.getAll(Attachment.class,"questionPaperBankId="+ questionBank.getId());
					if(!ObjectFunctions.isNullOrEmpty(attachmentList)){
						for(Attachment attachment:attachmentList){
							AttachmentVO attachmentVO=new AttachmentVO();
							if(!ObjectFunctions.isNullOrEmpty(attachment)){
								attachmentVO.setFileName(attachment.getFileName());
								attachmentVO.setFilePath(attachment.getFilePath());
								attachmentVO.setId(attachment.getId());
							}
							questionPaperBankVO.getAttachmentVOs().add(attachmentVO);
							attachmentVO=null;
						}
					}
				}
				return questionPaperBankVO;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public int removeQuestionBankAttachment(long questionBankId,long attachmentId){
		if (log.isInfoEnabled()) {
			log.info("Entering AdminManager 'removeQuestionBankAttachment' method");
		}try{
			 Attachment attachment = (Attachment) super.get(Attachment.class,"questionPaperBankId="+questionBankId+" and id="+attachmentId);
			 if(!ObjectFunctions.isNullOrEmpty(attachment)){
				 this.updateQuery("delete from attachment where questionPaperBankId="+questionBankId+" and id="+attachmentId);
				 attachment = null;
				 return 1;
			 }
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
		
	}
	public List getParentIncomeWiseDetails(long custId,long academicYearId, String classIds,String incomeRangeIds ){
		return adminDao.getParentIncomeWiseDetails(custId,academicYearId,classIds,incomeRangeIds);
	}
}