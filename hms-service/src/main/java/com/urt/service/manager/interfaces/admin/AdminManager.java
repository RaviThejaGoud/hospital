package com.urt.service.manager.interfaces.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

import org.hibernate.persister.entity.AbstractEntityPersister;

import com.hyniva.sms.ws.vo.AdmissionSettingsVO;
import com.hyniva.sms.ws.vo.AndroidMobileUsersVO;
import com.hyniva.sms.ws.vo.CasteSettingsMainVO;
import com.hyniva.sms.ws.vo.ClassNameMainVO;
import com.hyniva.sms.ws.vo.ClassTeacherMainVO;
import com.hyniva.sms.ws.vo.CommonTypeMainVO;
import com.hyniva.sms.ws.vo.DefaultScoreCardTemplatesVO;
import com.hyniva.sms.ws.vo.EventMainVO;
import com.hyniva.sms.ws.vo.EventsVO;
import com.hyniva.sms.ws.vo.ExamSchedulesMainVO;
import com.hyniva.sms.ws.vo.ExcessPaymentVO;
import com.hyniva.sms.ws.vo.FeeMainVO;
import com.hyniva.sms.ws.vo.FeeTypeMainVO;
import com.hyniva.sms.ws.vo.LeavesMainVO;
import com.hyniva.sms.ws.vo.MeetingScheduleSlotsVO;
import com.hyniva.sms.ws.vo.MeetingSchedulesMainVO;
import com.hyniva.sms.ws.vo.MeetingSchedulesVO;
import com.hyniva.sms.ws.vo.MessagesMainVO;
import com.hyniva.sms.ws.vo.PayslipBaseVO;
import com.hyniva.sms.ws.vo.RouteMainVO;
import com.hyniva.sms.ws.vo.RouteNotifyVO;
import com.hyniva.sms.ws.vo.RouteTrackVO;
import com.hyniva.sms.ws.vo.RouteVo;
import com.hyniva.sms.ws.vo.RunCCReportsVO;
import com.hyniva.sms.ws.vo.SchoolCategoryMainVO;
import com.hyniva.sms.ws.vo.SchoolHolidaysMainVO;
import com.hyniva.sms.ws.vo.SchoolTermsMainVO;
import com.hyniva.sms.ws.vo.ShareUserActivitiesVO;
import com.hyniva.sms.ws.vo.SocialDiscussionsVO;
import com.hyniva.sms.ws.vo.StaffElgibleSubjectsVO;
import com.hyniva.sms.ws.vo.StudentFeeConcessionMainVO;
import com.hyniva.sms.ws.vo.StudentFeePaidDetailsVO;
import com.hyniva.sms.ws.vo.StudentFeeVO;
import com.hyniva.sms.ws.vo.StudentLibraryBooksVO;
import com.hyniva.sms.ws.vo.StudentMarksMainVO;
import com.hyniva.sms.ws.vo.StudentMonthlyAttendanceMainVO;
import com.hyniva.sms.ws.vo.StudentPaymentMainVO;
import com.hyniva.sms.ws.vo.StudentScoreCardVO;
import com.hyniva.sms.ws.vo.StudentTimetableVO;
import com.hyniva.sms.ws.vo.StudySubjectVO;
import com.hyniva.sms.ws.vo.UserAddressChangeVO;
import com.hyniva.sms.ws.vo.VehicleRouteMainVO;
import com.hyniva.sms.ws.vo.ViewAllUsersMainVO;
import com.hyniva.sms.ws.vo.ViewClassAssignmentDetailsMainVO;
import com.hyniva.sms.ws.vo.ViewRouteTrackMainVO;
import com.hyniva.sms.ws.vo.ViewRouteTrackVO;
import com.hyniva.sms.ws.vo.ViewRouteVehiclesMainVO;
import com.hyniva.sms.ws.vo.ViewStaffPersonAccountDetailsMainVO;
import com.hyniva.sms.ws.vo.ViewStudentFeePaymentDetailsMainVO;
import com.hyniva.sms.ws.vo.attendance.StudentAndStaffDailyAttendanceVO;
import com.hyniva.sms.ws.vo.attendance.StudentDailyAttendanceMainVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.hyniva.sms.ws.vo.exam.QuestionPaperBankVO;
import com.hyniva.sms.ws.vo.fee.StudentPaymentListVO;
import com.hyniva.sms.ws.vo.sports.AchievementVO;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.VWFeedbackRatingDetails;
import com.urt.persistence.model.common.VWStaffAttendance;
import com.urt.persistence.model.common.VWStudentDailyAttendance;
import com.urt.persistence.model.common.ViewHostelStudentDailyAttendance;
import com.urt.persistence.model.common.ViewUserRoles;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.event.Events;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.exam.OverAllGrades;
import com.urt.persistence.model.exam.SchoolGrades;
import com.urt.persistence.model.exam.ScoreCardTemplates;
import com.urt.persistence.model.exam.SubType;
import com.urt.persistence.model.exam.Syllabus;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.exam.ViewExamSchedule;
import com.urt.persistence.model.exam.ViewExamScheduleDetails;
import com.urt.persistence.model.exam.ViewStaffSubjectsDetails;
import com.urt.persistence.model.fee.ExcessPayment;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.fee.ViewStudentDeleteFeeDetails;
import com.urt.persistence.model.fee.ViewStudentsExcessPaymentDetails;
import com.urt.persistence.model.hostel.Building;
import com.urt.persistence.model.secretary.BudgetRequest;
import com.urt.persistence.model.secretary.ViewBudgetRequestDetails;
import com.urt.persistence.model.secretary.ViewMeetingRequestDetails;
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
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentAcademicPerformance;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.StudentMarks;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
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
import com.urt.persistence.model.transport.ViewAssignedVehiclestoRoutesWithBoardingPoints;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.OnlineApplicationDetailsView;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.persistence.model.webservice.ViewFee;
import com.urt.service.manager.interfaces.base.UniversalManager;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="AdminManager.java.html"><i>View Source</i></a></p>
 */
public interface AdminManager extends UniversalManager {
    
	/** For getting Teacher,Hod and Principal list. */
	
	List<ViewStaffPersonAccountDetails> getAllTeacherListByStatus(long custId,String status);
	
	List<BigInteger> getStaffElgibleSubjectsListByStudySubjectId(long studySubjectId,String staffIds);
	
	List<ViewStaffPersonAccountDetails> getViewStaffDetailsListByStaffIds(String staffIds,long customerId,long academicYear);
	
	Section getSectionBySectionName(String sectionName,long custId);
	void updateClassesOrder(long classId,long sortingOrder);
	
	List getAllCommonTypesByCustIdandType(long custId,String type);
	
	List<StudySubject> getStudySubjectByCustIdAndAcademicYear(long custId,long academicYear);
	
	List<ClassTeacher> getClassTeacherByStudyClass(long studyClassId,long academicYear);
	void updateClassNameByClassId(String className,long classId);
	
	List getRemainingClassIdsByExamTypeIdAndClassIds(long examtypeId,String classIds);
	/*List<StudyClass> getStudyClassesByCustId(long custId,long academicYearId);*/
	/*List<ClassName> getAllClassesById(long classId,String status);*/
	
	List<Fee> getAllFeesByCustId(long classId);
	
	Fee getFeeByClassId(long classId);
	
	List<Student> getAllStudentsByCustId(long custId,String status,long academicYearId);
	
	List<Student> getAllStudentsByClassName(long className,long custId,String status,long academicYearId);
	
	List<Student> getAllStudentsByRollNumber(String rollNumber,long custId);	
	
	ViewStaffLeaveDetails getViewStaffLeaveDetailsByLeaveId(long leaveid,long customerId);
	
	List<ViewStaffLeaveDetails> getAllLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId);
	
	List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId);
	
	Role getRoleDetailsByRoleName(String roleName);
	
	List<StudyClass> getSubjectsByClassName(String className,long custId,long academicYearId);
	
	List<Section> getSectionByStudyClass(String sectionName);
	
	Student getStudentByRollNumber(String rollNumber,long custId);	
	
	List<Person> checkPersonId(String userId);
	
	List<StudySubject> getSubjectsForHigherClass(String subjectId);
	
	StudentPayment getPaymentStatusByStudentId(long studentId);
	
	List getSubjectsByClass(long studyClassId);
	
	LeaveManagement getLeaveManagementByRoleName(long roleId,long custId,long academicYearId); 	 	
	
	List getStudentsPaymetDetailsByCustId(String paymentType,long custId);
	
	List<ViewStaffPersonAccountDetails> getViewStaffDetailsByRoleName(String rollName,long customerId,String status);
	
	List getLeavesByAccountId(long accountId);
	void updateAllStaffLeaveDetailsByCasualANDSickLivesAndStaffIds(String staffIds,long casualLeaves,long sickLeaves,long custId);
	
	List<ViewStudentClassDetails> getStudentsPayentByRollNumber(String rollNumber,long custId,long academicYearId);
	
	List<ViewStudentPersonAccountDetails> getStudentTransportPayentByRollNumber(String rollNumber,long custId,String status,String transportStatus);
	
	List getClassSubjectByClassId(long classId);  
	
	Fee getPaymentStatusByClassId(long classId);
	
	List<ViewStudentClassFeePaymentDetails> getStudentByClassIdAndFee(long termId,long custId);
	void deleteStudentAttendanceByStudentId(long studentId);
	
	List<Fee> getAllAdminFeeStausList(String status);
	
	List<StudentPayment> getALlReceiptsByPaymentId(long spId,String createdDate);
	//TODO JSR
	
	List getFeesPaymentListByStudentPaymentId(String table,long studentId,long classId,long custId,long academicYearId,String today,String paymentStatus,long invoiceNumber);
	
	List getAllNoticeBoardMessagesList(String attendanceStatus,long customerId,String academicYearId);
	
	List getAllClassExamTypes(long custId,String academicYear);
	
	List getAllHolidayBoardMessagesList(String attendanceStatus,long customerId);
	
	ViewStaffPersonAccountDetails getStaffLastRecordByRoleName(String roleName,long customerId);
	long getMaxExamTypeIdFromStudentMarks(long classId,long academicYear);
	
	ClassName saveClassName(ClassName className);
	
	AcademicYear getCurrentAcademicYear(String status,long custId);
	
	List getSyllabusByClassIdAndCustId(long classId,long custId);
	
	AdmissionSettings getAdmissionSettingsByCustIdAndClassId(long custId,long classId);
	/*List<ClassName> getClassesByCustId(long custId,long academicYearId);*/
 	/*List<ClassName> checkClassId(String className,long custId,long academicYearId);*/
	/*List<StudyClass> getStudyClassesByCustIdAndGroupByClassNameClassId(long customerId,String academicYearId);*/
	
	List getStudentsByClassNameClassId(long classNameClassId,long customerId);
	
	List getAllHolidaysListByAcademicYearId(String status,long customerId,long academicyearId,long classNameClassId);
	
	List getAllUpcomingClassExamSchedulesGroupByClassIdandExamTypeId(long custId,long academicYearId,String date);
	
	List getClassSyllabusList(long classId);
	String removeAllSyllabusWithClassId(long classId);
 	/*List getClassesByClassId(String classId,long custId,long academicYearId);*/
	
	List getAcademicsByAcademicYear(String academicYear,long custId);
	
	List getAllCompletedClassExamSchedulesGroupByClassIdandExamTypeId(long custId,long academicYearId,String date);
	
	List<ViewStudentPersonAccountDetails> getStudentsByFirstName(String firstName,long custId);
	/*List<ClassName> getAllClasseNames(long custId,String status,long academicYearId);*/
	
	OnlineApplicationDetails getDetailsByApplicationNumber(String applicationNumber,long custId);
	
	List getApplicationsByStatus(String status,long classId,long custId,long academicYearId,String todayDate);
	
	AdmissionSettings getAdmissionSettingsByCustId(long custId);
	/*List<ClassName> getClassesByAdmissionStatus(long custId,String status,long academicYearId);*/
	
	OnlineApplicationDetailsView getDetailsByApplicationNumberAndView(String applicationNumber);
	
	List getApplicationDetailsByClassName(long classId,long custId);
	
	List getApplicationDetailsByMarks(long classId,long custId);
	
	String getClassStudentStrengthByClassId(long classId,long custId);
	
	List getAdmissionSettingsForAcademicYears(long custId,long academicYearId);
	
	List<AcademicYear> getAcademicYears(long pastYear,String academicYear,long custId);
	
	AdmissionSettings getAdmissionSettingsByCustIdAndYear(long custId,long academicYearId);
	
	AcademicYear saveAcademicYear(AcademicYear academicYear);
	/*SchoolHolidays getHolidaysListForUpdate(String fromDate,long custId,long classId);*/
	
	List geAllHolidaysByAcademicYearId(long custId,long academicYearId);
	
	List<ViewClassFeeDetails> getClassFeeDetails(long classId,long custId,String academicYear);
	
	List<Fee> getClassFeeTermDetails(long classId,long custId,String academicYear);
	
	List getClassFeeTermsByStudentId(String table,long studentId,long custId,long academicYearId,long classId,long categoryId,long feeSettingId);
	
	List getClassFeeTermsByTransportStudentId(String table,long studentId,long custId,long academicYearId,long classId,long categoryId);
	
	List getAllHolidaysByMonthId(int monthId,long orgId,String holidayYear);
	
	StudentPayment getRemainingPaymentByStudentPaymentId(long studentId,long classId,long feeId,long custId,long academicYearId,String status);
	
	FeeType getFeeTypeId(long feeTypeId,long custId);
	
	ViewStudentClassFeePaymentDetails getClassFeePendingTerms(long classId,long feeId,long custId,String studentNumber);
	
	AcademicYear getAcademicYearByAcademicYear(String academicYear,long custId);
	
	List getAllStudentsDetailsByCustId(long custId);
	
	List removeStaffSubjectList(long staffId,long academicYearId);
	
	List getAllStaffLeavesByStatus(long custId,String leaveStatus);
	
	List<ViewStaffPersonAccountDetails> getAllStaffList(long custId,long academicYearId);
	
	Fee getFeeAndFeeTypeByClassId(String table,long classId,long feeTypeId,long custId,long academicYearId,long schoolTermId,long categoryId);
	List getQuizQuestionListWithStartDate(long quizId);
	
	FeeType saveFeeTypeName(FeeType feeType);
	
	List getFeeTypeList(String table,String feeType, long custId,long acadmeicYearId);
	
	List<ParentFeedbackResult> getStaffFeedbackResultList(long staffId,long custId,String academicYearId);
	
	List removeFeedbackParentsResults(long feedbackQuestionId,long custId);
	
	ParentFeedbackResult getTeacherGradeByStudent(long staffId,long studentId,long custId);
	
	ViewClassFeeDetails getClassFeeDetails(long classId,long custId,String academicYear,long feeTypeId);
	
	ViewClassFeeDetails getAdminssionFeeDetails(String feeType,long custId,long classId);
	
	List updateOnlineApplicationDetails(long classId);
	
	List<ViewClassFeeDetails> getClassFeeDetailsList(long classId,String academicYear,long custId);
	
	Staff getStaffByAcountId(long accountId,String status);
	
	List<PromoteClass> getPromoteClassDetailsByCustId(long custId,long academicYearId);
	
	List getAllAdminsByUrlPath(String urlPath);
	
	Student saveStudentDetails(Student student);
	
	List<StudySubject> getStudySubjectByCustId(long custId);
	
	String getMaxExamTypeIdFromStudentMarksBydate(String date);
	
	List<AcademicYear> getPastAcademicYears(long academicYearId,long custId);
	
	List<FeeType> getFeeTypeListByAcademicYearId(String queryString,long custId,long academicYearId,long feeSettingId);
	
	ClassTeacher getClassTeacherByAcademicId(long staffId,long custId,long academicYearId);
	
	List<LeaveManagement> getLeavesListByCustId(long custId,long academicYearId);
	
	List<AdmissionSettings> getAdmissionSettingsList(String status);
	
	Customer getCustomerByUrlPath(String urlPath);
	/*List<ClassName> getAllClassesByCustIdandAcademicYear(long custId,String academicYearId);*/
	
	AcademicYear getAcademicYearByPastYearandCustId(String pastYear,long custId);
	/*ClassName getClassNameByclassNameandCustIdandAcademicYear(String className,long custId,long academicYearId);*/
	
	StudySubject getStudySubjectBySubjectNameandAcademicYearandCustId(String subjectName,long academicYear,long custId);
	
	List<StaffElgibleSubjects> getStaffElgibleSubjectsByAcademicYearId(long staffId,long academicYearId);
	
	StudySubject saveStudySubject(StudySubject subject);
	
	List getClassTeachersByAcademicIdAndStaffIdandCustId(long staffId,long custId,long academicYearId);
	/*StudyClass getStudyClassByClassNameAndSectionandAcademicYear(String className,String section,long custId,long academicYear);*/
	
	List<Customer> getCustomerListByStatus(String status);
	void removeSyllabusByAcademicYearAndCustId(long custId,long academicYear);
	
	List getAllTeachingStaffListByStatus(long custId,String status,long academicYearId);
	
	StudySubject getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(long custId,String academiYear,String subjectName);
	
	List getAllAcademicYearsByCustIdAndStartDateAndEndDateisNull(long custId);
	
	CommonType getSkillTypeNameByCustIdAndAcademicYear(long custId,String skillTypeName);
	
	//ReminderExamDetails getReminderExamDetailsBycustIdandExamTypeIdandClassNameClassId(long custId,long classId,long examTypeId);
	long getStudentsCountBycustIdandStatus(long custId,String status);
	
	List getAllFuturePackagesBycurrentPackage(long studentsRange);
	
	List<Student> getStudentsByClassIdAndCustIdAndYearAndStatus(long classId,long custId,long academiYearId, String status);
	/*List getAllClasseNamesNotinClass(long custId,String status,long academicYearId,long classId);*/
	
	List getAllFeeByClasIdAndCustId(String table,long custId,long classId,long academicYearId,long categoryId);
	long getFeeTotalAmountByTerm(String queryString,long custId,long classId,long academicYearId,long schoolTermId,long categoryId,long boardingPointId);
	
	ExamTypes saveExamType(ExamTypes examType);
	
	//ReminderExamDetails getRemindarExamDetailsByExamTypeId(long examTypeId);
	
	List<ViewExamSchedule> getExamSchedulesByClassIdAndExamTypeId(long classId,long examTypeId);
	
	List getExamScheduleIdsByAcademicYearandClassNameClassIdandExamTypeIdAndSubjectIdAndSubTypeId(long academicYear,long classId ,long examTypeId,long subjectId,long subTypeId);
	
	List<ViewStaffSubjectsDetails> getStaffSubjectsByStaffIdAndAcademicYearIdAndCustId(long staffId,long academicYearId,long custId);
	
	List<ViewClassExamDetails> getExamSchedulesByClassIdandSubjectIdandExamTypeIdandAcademicYearId(long classId,long subjectId,long examTypeId,long academicYear);
	
	ViewStudentMarks getStudentMarksByScheduleIdAndStudentId(long scheduleId, long studentId);
	
	Student getStudentByCustIdAndStudentIdAndStatus(long studentId,long custId,String status,long academicYearId);
	
	Student getStudentByCustIdAndStudentIdAndTransportStatus(long studentId,long custId,String status,String transportStatus,long academicYearId);
	
	List getFeetypesByClassId(String table,long classId,long custId,long academicYearId);
	
	List<SchoolTerms> getSchoolTermsByCustId(String clazz,long custId,String academicYear);
	
	SchoolTerms saveSchoolTerms(SchoolTerms schoolTerms);
	
	List getAllFeeByClasIdAndCustIdAndTermId(String table,List<String> classIds,long custId,long academicYearId,long termId,String categoryIds);
	
	Fee getFeeByClasIdAndCustIdAndTermIdAndTypeId(long classId,long typeId,long termId,long custId,long academicYearId);
	
	List<Syllabus> getSyllabusByClassIdAndSubjectIdAndCustId(long subjectId,long custId,long studyClassId);
	
	List<Syllabus> getSyllabusBySyllabusIdsAndCustId(String syllabusIds,long custId);
	long getStudentPaidAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,long termId,long boardingPointId);
	
	List getAllTitlesByPlaylist(String subjectName);
	
	List getPlayListSubjects(String clazz);
	
	List getPlayListForSubTopics(String subName);
	
	List getVideoPlayList(long playListId);
	
	List getStaffsListByRoleAndCustIdAndStatus(String roleName,long custId,String status,long academicYearId);
	
	ViewStaffPersonAccountDetails getStaffDetailsByRoleNameAndStaffIdAndCustId(String roleName,long custId,long staffId,String status,long academicYearId);
	long getStudentDiscountAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber);//long termId,String status
	
	ClassTeacher getClassTeacherByAcademicIdAndNotInCurrentClass(long staffId,long custId,long academicYearId,long classId);
	void removeClassTeacherByCustId(long classTeacherId,long custId);
	
	State getStateCodeByStateName(String stateName);
	
	List<ViewStaffLeaveDetails> getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId);
	
	List<ViewStudentLeaveDetails> getAllStudentLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId) ;
	
	ViewUserRoles getViewUserRolesByUserIdAndCustId(long userId, long customerId,String status);
	
	List getStudentClassFeePamentByTodayDateAndcustIdAndStatus(long userCustId, long reqYear,String todayDate,String status);
	
	List getStudentPaidTermsAmountByClassIdAndStatus(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber);//long termId,String status
	
	List getStaffPaidTermsAmountByStaffIdAndStatus(String queryString,long staffId,long custId,long academicYearId,String feeIds,long invoiceNumber);
	
	List getClassFeeTermsByStudentIdAndStatusAndCurrentDate(String queryString,long studentId,long custId,long academicYearId,long classId,String todayDate,String feeIds,long invoiceNumber);//,long termId,String status
	
	List getHostelFeeTermsByStaffIdAndStatusAndCurrentDate(String queryString,long studentId, long custId, long academicYearId,String todayDate, String feeIds, long invoiceNumber);
	
	List getAllSmsEventsByAcademicYear(String academicYear);
	
	List<SubType> getAllSubTypesBySubTypeIds(String subTypeIds,long custId);
	
	SchoolGrades getSchoolGradeByName(String gradName, long academicYearId);
	
	List<ExamTypes> checkExamTypeByNameAndCustId(String examType,long custId);
	
	List getSubTypesByClassSectionIdAndExamTypeId(long classSectionId,long examTypeId);
	void removeClassTeachersByStudyClassIdandSubjectIdsandCustId(long classSectionId,long custId,String subjectIds);
	void removeSyllabusByStudyClassIdandSubjectIdsCustId(long classSectionId,long custId,String subjectIds);
	
	List getClassTeachersByAcademicIdAndSubjectIdAndStaffIdandCustId(long staffId,long subjectId,long custId,long academicYearId);
	void removeClassTeacherByClassTeacherIdAndSubjectIdAndCustId(long classTeacherId,long subjectId,long custId,long academicYearId);
	
	List getClassSubjectBySubjectId(long subjectId);
	
	ClassTeacher getClassTeachersByAcademicIdAndSubjectIdAndClassIdandCustId(long studyClassId,long subjectId,long custId,long academicYearId);
	
	List<SchoolTerms> checkSchoolTermsByNameAndCustId(String termName,long custId,long academicYearId);
	
	List<ViewStaffSubjectsDetails> getClassTeacherClassesListByStaffIdCustIdAcademicYearIdGroupByClassTecherStatusAndStudyClassId(long academicYearId,long staffId,long custId);
	
	List<ViewStaffSubjectsDetails> getTeacherClassesAndSubjectsListByStaffIdCustIdAcademicYearIdOrderbyStudyClassId(long academicYearId,long staffId,long custId);
	
	List getSchoolTermsOrderByDuedate(String table,long custId,long academicYearId,long feeSettingId);
	
	List getSchoolTermsByDuedate(String table,long custId,long academicYearId,String toDayDate,long feeSettingId);
	
	List getDescriptionByCustIdAndAcademicYear(long custId,long academicYearId);
	
	List<StaffDailyAttendance> getStaffAttendance(long custId, long academicYearId,String date);
	
	List<ViewStudentClassFeePaymentDetails> getAllReceiptsStudentClassFeeByPaymentId(long spId,long invoiceNumber);
	long getFeeTotalAmountByCustId(long custId,long termId,long academicYearId,long classId);
	long getDiscountTotalByCustId(long custId,long termId,long academicYearId,String status,long classId);
	long getTotalStudentsPaidAmount(long custId,long termId,long academicYearId,String status,long classId);
	
	List getClassFeeTermsByStudentIdAndStatusAndCurrentAndEndDate(String queryString,long studentId,long custId,long academicYearId,long classId,long termId,String fromdate,String todayDate,String status,long invoiceNumber);
	
	List getFeeTermsByStaffIdAndStatusAndCurrentAndEndDate(String queryString,long staffId,long custId,long academicYearId,long termId,String fromdate,String todayDate,String status,long invoiceNumber);
	
	List getSchoolTermsByCompleteDuedate(String table,long custId,long academicYearId,String toDayDate);
	
	List<ViewStudentPersonAccountDetails> getStudentbyClassNameClassIdAndCustIdAndAcademicYear(long classNameClassId,long custId,long academicYearId,String status);
	
	List getAllStaffInvoiceFeeDetailsByCustId(String queryString,long customerId, long academicYearId, long termId, String today,String status);// ,String paymentStatus
	
	ViewStudentMarksDetails getStudentMarksByStudIdAndExamTypeIdAndSubTypeIdAndSubjectId(long studId,long examTypeId,long subTypeId,long subjectId);
	
	SchoolCategory getCategoryIdByCustId(String status,long custId);
	void removeSchedulesByScheduleIds(String scheduleIds);
	void updateExamScheduleStartDateAndEndDateByClassSectionIdAndExamTypeId(long classSectionId,long examType,String startDate,String endDate);
	
	ClassTeacher getStudySubjectTeacherByStudyClassAndCustId(long custId,long academicYearId,long staffId,long studyClassId,long subjectId);
	void removeClassTeachersByStudyClassIdandSubjectIds(long staffId, long academicYearId, long custId,String subjectId);
	
	ClassTeacher getAllClasseNamesByClassId(long subjectId, long staffId, String classId);
	
	Staff getStaffByAcountIdAndCustId(long accountId,String status,long custId);
	
	StaffElgibleSubjects getStudySubjectTeacherByStudyClassAndCustId(long academicYearId, long staffId, long subjectId);
	void removeSelecteEndDateHolidays(long custId,long academicYearId,String endDate);
	
	List getClassWiseFeeDefaultersByCustId(String table,long customerId,long academicYearId,long classId,long termId,String transportmode,String hostelmode);
	
	List getSchoolHolidaysByDescription(long custId, long academicYearId,String description);
	
	List getSubTypesByClassSectionIdAndExamTypeIdAndSubjectId(long classSectionId,long examTypeId,long subjectId);
	
	List getAllByCustIdAndStatus(String clazz,long custId,String status,long academicYearId);
	
	List getParentSearchResultsByStudentDetails(long custId,String fName,String lName,String pName,String adNumber,String status);
	
	StaffDailyAttendance getStaffAttendanceByAttendanceDateAndAccountIdAndCustId(long custId,long accountId,String attendanceDate);
	
	List<ViewUserRoles> getViewUserRolesByRoleNameAndCustId(String roleName, long customerId,String status);
	/*List getHolidaysByDates(long custId,String startDate,String endDate);*/
	
	ClassTeacher getClassTeacherByStudyClassIdAndClassTeacherStatus(long studyClassId, String classTeacherType,long academicYearId);
	
	List<ViewStudentPersonAccountDetails> getParentChildrens(long custId,long parentId,long academicYearId);
	
	List<ViewStaffLeaveDetails> getAllFutureLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId);
	
	TransferCertificate saveTCSerialNumber(TransferCertificate transferCertificate);
	
	List<SubCastSettings> getSubcastBySubCastNameAndCastId(long customerId,String subCastName,long castId);
	
	List getSubcastSettingsByCastIdAndCustId(long castId, long customerId);
	
	List getEventsByDates(long custId,String startDate,String endDate);
	
	List getWorkingDayHolidaysByDates(long custId,String status,String startDate,String endDate);
	
	List<StaffElgibleSubjects> getAllStaffElgibleSubjectsByStudySubjectId(long studySubjectId);
	Object[] getAllStudentsByClassNameAndCastName(long custId,long academicYearId,List<CastSettings> castList,long classId,String userName);
	Object[] getAllStudentsCountByCastWise(long custId,long academicYearId,List<CastSettings> castList,String username,String classNameClassIds);
	
	Section saveSectionName(Section sectionName);
	
	CastSettings saveComunityName(CastSettings castSettings);
	void removeClassSubjectsByClassSectionId(long studyClassId);
	void removeClassEventsByClassSectionId(long studyClassId);
	void removeExamSchedulesByClassSectionId(long studyClassId);
	void removeKbanksByClassSectionId(long studyClassId);
	void removeClassTeachersByStudyClassIdCustId(long classSectionId,long custId);
	List<Object[]> getAllStaffDetailsByDesignation(long custId,long academicYearId,List<CastSettings> castList,String username);
	Object[] getAllStaffsCountByCastWise(long custId,long academicYearId,List<CastSettings> castList,String username);
	
	AcademicYear getFutureAcademicYearByCustIdAndStatus(long custId, String status);
	int getMaxOfClassNameSortingOrder(long academicYearId, long customerId);
	
	Date getMaxExamDateByExamTypeIdAndClassSectionId(long examtypeId,long classSectionId,String dateType); 
	
	List<Object[]> getAllClassAndSectionsByCustIdAndAcademicYearId(long custId,long academicYearId);
  
	Object[] getMediumWiseStudentDetailsForEachClass(long custId,long academicYearId,String classNameClassId, List schoolMediumsListList,String username);
	Object[] getAllStaffCountByReligionWide(long custId,long academicYearId,List  religionsList, List motherToungsList,long organiZationTypeId,String religionIds,String username);
	Object[] getAllStudentsCountByReligionWide(long custId,long academicYearId,List religionsList,List motherToungsList,long classId,String religionIds,String username);
	int getPaidAmountByClassId(String table,long custId,long classId,long academicYearId,long categoryId,SchoolFeeSetting schoolFeeSetting);
	Object[] getAllSyllabusTypesByCustId(long custId);
	
	StudentAcademicPerformance getStudentAcademicPerformanceByStudentIdAndstudentActivityTypeIdAndExamTypeId(long studId,long studentActivityTypeId,long examTypeId,long custId,long academicYearId);
	void removeSyllabusTypeInfoByCustId(long customerId);
	
	Address saveAddress(Address address);
	
	List<ViewStaffPersonAccountDetails> getStaffDetailsBySearchName(String rollNumber,long custId,String status);
	
	List<AbstractEntityPersister> getAllTableNames();
	List<Object[]> getAllUsersByCretedBy(String table);
	void updateCreatedByRecords(String table,String accountId,String fullName);
	List<Object[]> getAllUsersByUpdatedBy(String table);
	void updateLastUpdatedByRecords(String table,String accountId,String fullName);
	void removeLastUpdatedByAndCreatedByRecords(String table);
	
	List getAllStudentFeeDefaultersByCustId(String queryString,long customerId,long academicYearId,long termId,String today,String transportmode);//,String paymentStatus
	
	Staff getStaffByCustIdAndStaffIdAndStatus(long staffId,long custId,String status,long academicYearId);
	
	List getAllVisitorsByBetweenDated(long custId,long academicYearId,String inDate,String outDate);
	
	List getClassNameIdsFromStudyClassIds(String studyClassIds);
	
	//List getExamTypeIdsByClassIds(long classId);
	void updateCourseCompletedStudents(long classId,String failurePromotStudIds,long userId);
	void updateStudentRollNumberByClassSectionId(long classSectionId);
	void updateSubjectsOrder(long subjectId,long sortingOrder);
	
	List getAllStudentInvoiceFeeDetailsByVehicleIdAndCustId(String queryString,long customerId,long academicYearId,long termId,String vehicleAcademicId,String status);//,String paymentStatus
	void removeExamScheduleMarks(long classScctionId,long examTypeId,long academicYearId,long custId);
	void removeStudentMarksByClasssSectionId(long classScctionId,long examTypeId,long academicYearId,long custId);
	
	SMSServiceProviders saveSMSUrl(SMSServiceProviders smsProvider);
	void updateRemainingSMSProviderstoInactiveState(long providerId);
	
	List getSchoolTermsByCustIdAndAcademicYearId(String table,long custId,long academicYearId);
	
	List<Building> checkBuildingId(String buildingName,long custId,long academicYearId);
	int getMaxPeriodsByClassSectionIdAndDaydId(String classSectionId, String dayId,String periodType);
	
	List<ViewClassSubjectsSettings> getAllClassWiseSubjects(String clause);
	
	void updateTimeTableTeacherIdandSubjectIdsByAcademicYearId(long academicYearId);
	
	List<ViewStudyClassSubjects> getAllStudyClassSubjects(String clause);
	void updateClassSubjectsPeriodsCount(long classSectionId);
	void updateClassTeacherHandlePeriodsCount(long classSectionId);
	void updateClassStatus(long custId,long academicYearId);
	
	List<ViewStudentClassDetails> getAllStudentDetailsByGender(long custId,long academicYearId,long classSectionId);
	OverAllGrades getOverAllGradeByName(String gradName, long academicYearId);
	void updateExamTypesOrder(long examTypeId,long sortingOrder);
	void updateSubTypesOrder(long subTypeId,long sortingOrder);
	void updateTimeTableDetailsByClassSectionIdAndSubjectIds(long classSectionId,String subjectIds);
	void updateAdmissionSettingsStatus(long admissionSettingId,long custId);
	void removeClassSubjectsSettingsByClassSectionId(long studyClassId);
	void removeTimeTablePeriodsByClassSectionId(long studyClassId);
	
	AdmissionSettings saveAdmissionSettings(AdmissionSettings admissionSettings);
	void updateClassAdmissionStatusToActive(String classIds,long academicYearId);
	void generateShortListApplications(long custId,long academicYearId,String applicationId);
	void generateRejectedListApplications(long custId,long academicYearId,String applicationId);
	
	SchoolHolidays getSchoolHolidayByDate(long custId,String selectedDate);
	void updateStudentDailyAttendance(long studentId,String leaveRequest,String attendanceDate);
	void updateStudentTransport(long studentId,Long vehicleAcademicDetailId,Long boardingPointId);
	
	Integer fetchTotalWorkingDays(long academicYearId,String endDate);
	
	List getStudentObtainedActivitiesGradesByStudIdAndExamTypeIds(long studId,String examTypeIds,long activityId,long classId);
	void updateClassTeacherStatus(long custId,long academicYearId,String classTeacher,long studyClassId);
	
	List geAllSchoolHolidaysListByAcademicYearId(long custId,long academicYearId);
	
	List<ViewStudentFeePaidAndNotPaidDetails> getStudentsFeePaidAndNotPaidDetails(String clause);
	
	String getBookSettingsCreatedClassIdsByAcademicYearId(long academicYearId,String type);
	
	List<Customer> findExistCustomer(String keyWord);
	
	List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeId(long studId,long examTypeId,long custId,long academicYearId);
	boolean isUserAsClassTeacher(long accountId,long classSectionId,long academicYearId);
	int getAssignedTimeTableSubjectsSettingsCount(long academicYearId);
	int getPaidAmountByBoardingPointWise(String table,long custId,long academicYearId,long categoryId,long feeSettingId,String trnaportMode,String boardingPointIds);
	Integer fetchStudentAbsentiesCount(long studentId,String endDate);
	void calculateStudentsSubjectPosition(long classSectionId,long examTypeId);
	void updateStudentHostel(long studentId,long bedId);
	
	List<ViewClassSectionTeacher> getAllClassSectionTeachersDetails(String clause);
	
	List<SchoolTerms> getFeeTermsByRemainderDays(long academicYearId);
	
	List<ExamSchedules> getExamSchedulesForSendingMobileAlerts(long academicYearId);
	
	List<ExamSchedules> getAllExamSchedulesForSendingEmailAlerts(long academicYearId,long classSectionId);
	
	List<StudentMarks> getLatestUploadedStudentsMarks(long classSectionId,long academicYearId,int noOfExamTypes);
	
	List<StudentMarks> getLatestUploadedExamTypesByStudentId(long studId,int noOfExamTypes);
	long getStudentPaidAmountByTermIdAndClassId(long studentId,long classId,long custId,long academicYearId,long termId,String dueDate,long feeSettingId,String checkDueDate);
	String removeAllAcademicYearTimingsByAcademicYearId(long academicYearId);
	String getStudyCertificateBookSettingsCreatedClassIdsByAcademicYearId(long academicYearId);
	void addStudentRollNumbersForNonAssignedStudentsByClassSectionId(long classSectionId,int lastAssignedRollNumber);
	
	List<ViewAssignedVehiclestoRoutesWithBoardingPoints> getAllViewAssignedVehiclestoRoutesWithBoardingPoints(String clause);
	
	List<ViewStudentFeePaymentDetails> getAllStudentNonPaidClassFeePaymentDetails(String tableName,long studentId,long academicYearId,long termId);
	
	List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseNonPaidClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId);
	void updateStudentPartialFeePaymentStatus(long studentId,long feeId,long transportFeeId);
	
	ViewStudentFeePaymentDetails getStudentNonPaidClassFeePaymentDetails(long studentId,long academicYearId,long termId,long feeTypeId);
	
	List<ViewOnlineApplicationStudentClassFees> getTermsWiseNonPaidClassFeePaymentDetailsForAdmissions(long classId,long academicYearId,String feeSettingId,long categoryId,String feeIds,long partialFeeId,double partialFeeAmount);
	
	List<ViewOnlineApplicationStudentClassFees> getNonPaidClassFeePaymentDetailsForAdmissions(long classId,long academicYearId,long termId,long categoryId,String feeIds,long partialFeeId,double partialFeeAmount);
	
	List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId);
	
	List<ViewStudentFeePaymentDetails> getAllStudenClassFeePaymentDetails(long studentId,long academicYearId,long termId);
	
	List<ViewStudentFeePaymentDetails> getStudentClassFeeDetails(long studentId,long academicYearId,long termId);
	
	List<ViewStudentFeePaymentDetails> getStudentFutureAcademicTermsWiseNonPaidClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId);
	
	List<ViewStudentFeePaymentDetails> getStudentFutureClassFeeDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId);
	
	List<ViewStudentFeePaymentDetails> getStudentNonPaidFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId);
	
	ViewStudentFeePaymentDetails getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(long studentId,long academicYearId,long termId,long feeTypeId,long classId,long classSectionId);
	
	List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseFutureClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId);
	
	List<ViewStudentFeePaymentDetails> getAllStudentFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId);
	
	List<ViewStudentFeePaymentDetails> getFutureAcademicYearStudentFeeReceipts(long studentId,long academicYearId,String invoiceNumber);
	
	List<ViewExamScheduleDetails> getUsersStartAndEndDateExamSchedulesDetails(long accountId,long academicYearId,String schedulesType,boolean isStaff,long classSectionId,long studentId);
	
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds);
	
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassIdAndFinanceUserId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds,long financeUserId);
	
	AdmissionSettings getOpenedAdmissions(long custId);
	
	List<ViewFee> getViewFeeDetails(String clause);
	long getFineFeeMaxInvoiceNumber(long custId,long academicYearId);
	
	/**
	 * 
	 * @param objClassName
	 * @param custId
	 * @param userId
	 * @param section
	 * @param subjectIds
	 * @return ClassName object
	 */
	
	ClassName addClass(ClassName objClassName, long custId, long userId, String section, String subjectIds);
	/**
	 * 
	 * @param className
	 * @param section
	 * @param academicYear
	 * @param studySubjects
	 * @param custId
	 * @param userId
	 * @return StudyClass object
	 */
	
	StudyClass createStudyClass(ClassName className,String section,AcademicYear academicYear,List<StudySubject> studySubjects,long custId, long userId);
	
	/**
	 * @param custId
	 * @param academicYearId
	 * @return list of study subjects
	 */
	
	List<StudySubject> GetAllStudySubjects(long custId,long academicYearId);
	
	/**
	 * 
	 * @param custId
	 * @param academicYearId
	 * @return list of ClassName objects
	 */
	
	List<ClassName> getAllClassNames(long custId, long academicYearId);
	/**
	 * 
	 * @param custId
	 * @param academicYearId
	 * @return list of StudyClass objects
	 */
	
	List<StudyClass> GetAllStudyClasses(long custId,long academicYearId,String studyClassIds);
	/**
	 * 	
	 * @param custId
	 * @param academicYearId
	 * @param studyClassIds within which the list should be.
	 * @return Success or Failures messages that will be presented on the screen through actionMessages
	 */
	 Map<String,String>  addUpdateSubject(StudySubject oSubject, long custId, long userId);
	/**
	 * 
	 * @param subOrders
	 * @return
	 */
	Map<String, String> UpdateSubjectOrder(String subOrders);
	void checkStudentFeePaidStatus(long academicYearId,long custId,Student studentId);
	void updateStudentPaymentStatus(long classId,long categoryId);
	/**
	 * 
	 * @param custId
	 * @param staffId
	 * @param feedBackQuesId
	 * @return
	 */
	
	List<VWFeedbackRatingDetails> teacherClassRatingDescription(long custId, String staffId, String feedBackQuesId);
	/**
	 * 
	 * @param custId
	 * @param ratingVal
	 * @return
	 */
	String getRatingDescription(long custId, double ratingVal );
	/**
	 * 
	 * @param custId
	 * @param userId
	 * @param academicYear
	 * @param holidayStartDate
	 * @param endDate
	 * @param holidayDescription
	 */
	void createSchoolHolidays(long custId, long userId, long academicYearId,Date holidayStartDate,Date endDate,String holidayDescription,String studyClassId, boolean isCreate, String deletedIds);
	/**
	 * 
	 * @param custId
	 * @param userId
	 * @param academicYearId
	 * @param sheet
	 * @param loadType
	 */
	String createHolidays(long custId, long userId, long academicYearId, org.apache.poi.ss.usermodel.Sheet sheet,String loadType,List<SchoolHolidays> schoolHolidaysDetailsList);
	/**
	 * 
	 * @param custId
	 * @param userId
	 * @param academicYearId
	 * @param eventId
	 * @param event
	 * @param startDate
	 */
	Events addEvent(EventsVO eventsVO);
	/**
	 * 
	 * @param custId
	 * @param academicYearId
	 * @param description
	 */
	String updateAndDeleteSchoolHolidays(long custId, long academicYearId, String description);
	/**
	 * 
	 * @param custId
	 * @param userId
	 * @param academicYearId
	 * @param staffId
	 * @param classSectionId
	 * @param subjectIds
	 */
	void assignClassSubjects(long custId, long userId, long academicYearId, long staffId,String classSectionId, List<String> subjectIds);
	/**
	 * 
	 * @param custId
	 * @param userId
	 * @param sheet
	 * @param loadType
	 */
	
	ClassName getClassByClassName(String className,long custId,long academicYearId,boolean isAutoCheck);
	/**
	 * @param className- By using this to get the class object.
	 * @param custId
	 * @param academicYearId
	 * @param isAutoCheck - If true to check the existence of className, if false to get the object by className.
	 */
	
	List<ClassName> getClassesByClassIdsAndAdmissionStatus(long custId,long academicYearId,String status,String classIds,boolean isClassIdIn);
	/**
	 * @param custId
	 * @param academicYearId
	 * @param status - To pass the admission status of the class.
	 * @param classIds - the list to be or not to be in these classIds.
	 * @param isClassIdIn - If true to get the list within these classIds, if false to get the classes list not in these classIds .
	 */
	
	StudyClass getclassByClassAndsection(String className,String sectionName,long customerId,long academicYearId);
	/**
	 * @param className
	 * @param sectionName
	 * @param customerId
	 * @param academicYearId
	 * @return returns the class object with given classNaem and section.
	 */
	void updateCommunityAndCaste(long custId, long userId,Sheet sheet,String loadType) ;
	void updateCustomersFeeUpdatesInStudent(long customerId);
	
	List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate);
	
	List<VWStaffAttendance> getStaffAttendanceByAttendanceDate(String attendanceDate,long custId,long academicYearId,String staffBiometric);
	
	List<Fee> getClassFeeListByTermId(String schoolTermIds,String classIds,long academicYearId,long custId);
	
	List<ViewStudentFeePaymentDetails> getFeeCollectionList(String tableName,long custId,long academicYearId,String toDayDate,String daysBeetweenStartDate,String daysBeetweenEndDate,String receiptType);
	
	List<ViewStudentFeePaymentDetails> getFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate);
	void updateSelectedLoginRoles(String selectedId,Long custId);
	void updateUnselectedLoginRoles(String unselectedId,Long custId);
	
	List<ViewHostelStudentDailyAttendance> getHostelStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate);
	void insertIntoLoginAcceessbilityRoles(List<Role> roles,long custId);
	
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermId(String tableName,long custId,long academicYearId,String termIds,String fromDate,String toDate,String feeSettingIds);
	
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermIdAndEndDateAndStatus(String tableName,long custId,long academicYearId,String termIds,String toDate,String feeSettingIds,String checkValue);
	
	List<OnlineApplicationDetailsView> getAdminssionApplicationFeeDetails(long custId,long academicYearId);
	
	List<ViewStudentMarksDetails> getStudentMarksDetailsByClassIdAndAcademicYearIdAndCustId(long custId,long academicYearId,long classSectionId);
	long getAdmissionReceiptNumberByCustId(String table,long custId,long academicYearId);
	
	List<ViewStudentFeePaymentDetails> getTermWiseFeeCollectionList(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String receiptType);
	
	List<ViewStudentFeePaymentDetails> getStudentFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String studentStatus);
	
	List<ViewStudentsExcessPaymentDetails> getAllStudentsExcessPayments(String classSectionId,long academicYearId,long custId);
	
	List getAllStudentInvoiceFeeDetailsByCustId(String queryString,long customerId,long academicYearId,long termId,String today,String daysBetwwenenfromDate,String daysBetwwenendDate,String transportmode,long feesettingId,String selectedClassSectionId);//,String paymentStatus
	
	List<ViewStudentFeePaymentDetails> getFeeCollecttionWithPercentageDetailsByClassWise(String tableName,long custId,long academicYearId,String classSectionIds,String perValue,String statusValue);
	List<Object[]> getStaffLeavesSummary(long custId, long academicYearId);
	
	
	List<ViewStudentFeePaymentDetails> getStudentFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate);
	boolean committedFeeStatusEnableOrDisable(long custId,long academicYearId);
	
	List<Events> getAllEventsByCustIdAndAcademicYearId(long custId, long academicYearId);
	void updateHolidayStatusForClassWiseHolidays(String table,long custId, long academicYearId);
	List<Object[]> getStudentDailyAttendanceSP(long custId, long academicYearId,long classSectionId);
	
	BudgetRequest saveBudgetRequest(long custId, long financialYearId,double totalAmount,String tempString,long userId,int monthId,String comments,String createdBy,long budgetRequestId,boolean isSecretary,String budgetType);
	
	void createLoginAccessbilityRolesForRole(long roleId,long custId,String status);	
	
	List<ViewBudgetRequestDetails> getAllViewBudgetRequestDetailsByFinancialYearIdCustIdStatus(long financialYearId,long custId,String statuss);
	
	List<ViewMeetingRequestDetails> getAllViewMeetingRequestDetailsByOrgIdCustIdDate(long orgId,long custId,String status);
	List<Object[]> getMonthlyStudentAttendanceSP(int pMonth,long classSectionId, long custId, long academicYearId);
	Student upadateStudentMarksFromSMSAppAndWeb(Student student,long examScheduleId,double obtainedMarks, long accountId, String[] anyIds,String isPresent,HashMap<Integer, String> showingResult,HashMap<Long, ExamSchedules> schedulesMap);
	void insertStudyClassIdInsteadOfClassId(long studyClassId, long examTypeId);
	
	Map<String,String> disableCustomer(long custId,String reasonForDisable, long operatorId);
	
	Map<Integer,String> addorUpdateEmailContactDetails(Customer inputCustomerDetails,Customer customer,long userId);
	
	ViewStaffPersonAccountDetailsMainVO getStaffDetails(long custId,long academicYearId);
	
	ViewStaffPersonAccountDetailsMainVO getTeachingStaffDetails(long accountId, String type);
	
	ViewAllUsersMainVO getUserDetails(long custId,long webAccountId);
	
	ClassNameMainVO getClassDetails(long academicYearId);
	
	ViewClassAssignmentDetailsMainVO getAssignmentDetails(long id, boolean isStudyClass);
	
	EventMainVO getEventDetails(long accountId, String roletype);
	
	ClassNameMainVO getStudyClassesDetailsRespectedPerson(long accountId, String type);
	
	ViewStudentFeePaymentDetailsMainVO getFeeDetails(long academicYearId);
	
	StudentMonthlyAttendanceMainVO getMonthlyAttendance(long academicYearId,long accountId,String type,String standardType);
	
	StudentDailyAttendanceMainVO getDailyAttendanceByStudyClassId(long academicYearId,long studyClassId);
	
	StudentDailyAttendanceMainVO getAttendanceByParentAccountId(long parentAccountId);
	
	ExamSchedulesMainVO getExamSchedules(long academicYearId,long accountId,String type);
	
	SchoolHolidaysMainVO getHolidays(long academicYearId,String classIds,String type);
	boolean submitStudentMarks(StudentMarksMainVO studentMarksMainVO);
	
	LeavesMainVO getLeaves(String type, long accountId);
	boolean androidRegistration(AndroidMobileUsersVO androidMobileUsersVO);
	
	MessagesMainVO getMessages(long academicYearId,long accountId);
	
	FeeTypeMainVO getFeeParticulars(long custId,long academicYEarId);
	
	SchoolCategoryMainVO studentCategoryList(long custId);
	
	SchoolTermsMainVO schoolFeeTermsList(long custId,long academicYearId);
	boolean runCCEReports(RunCCReportsVO runCCReportsVO);
	
	CommonTypeMainVO getCommonTypes(long custId,String type);
	
	CasteSettingsMainVO getCasteSettings(long custId);
	
	FeeMainVO classFeeList(long custId,long academicYearId);
	
	AdmissionSettingsVO admissionsOpenedForAcademicYearList(long custId);
	
	StudentPaymentMainVO createStudentPaymentSyncFromWebToDesktop(long custId,long academicYearId);
	
	List<StudentFeePaidDetailsVO> getstudentFeePaidDetails(List<StudentFeePaidDetails> studentFeePaidList);
	List<ExcessPaymentVO> getExcessPaymentDetails(List<ExcessPayment> excessPaymentList);
	boolean createStudentPaymentSyncFromDesktopToWeb(StudentPaymentMainVO studentPaymentMainVO);
	
	StudentScoreCardVO scoreCardDetailWithStudentAndStudyClassIdWise(long custId,long academicYearId,long studentId,long studyClassId,long examTypeId);
	void updateSelectedCustomersOrgId(String selectedId,Long orgId, String tempString);
	
	List<SocialDiscussionsVO> getSocialDiscussionsList(long custId,long accountId);
	
	SocialDiscussionsVO getsocialDiscussionsById(long socialDiscussionsVOId);
	
	List<ShareUserActivitiesVO> getShareUserActivitiesList(long custId, String type,long accountId,Date messageDate);
	
	ShareUserActivitiesVO getShareUserActivitiesById(long shareUserActivitiesVoId);
	boolean runTimeTableReports(RunCCReportsVO runCCReportsVO);
	List<DefaultScoreCardTemplatesVO> getDefaultTemplatesList();
	DefaultScoreCardTemplatesVO getDefaultScorecardTemplate(long scoreCardId);
	List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeIdAndSubTypeId(long studId,long examTypeId,long subtypeId);
	List<StudySubjectVO> getStudySubjectVOsByCustIdAndAcademicYear(long custId,long academicYearId);
	List<StaffElgibleSubjectsVO> getStaffElgibleSubjectVOsByAcademicYearId(long staffId,long academicYearId);
	void removeAllSchoolHolidaysByDescriptionAndStartAndEndDate(long custId,String description,String startDate,String endDate);
	
	void  timeTableSubstitution(long custId,long academicYearId,String xmlFilePath);
	
	List<StaffSubstitutionTimeTable> getStaffSubstitutionTimeTableListsByAcademicYearIdAndDate(long custId,long academicYearId,String date,String staffId);
	
	JSONObject prepareJsonForExamSchedules(ExamSchedules exam);
	
	boolean changeUserPrimaryAddress(UserAddressChangeVO userAddressChangeVO);
	boolean changeUserMobileNumber(UserAddressChangeVO userAddressChangeVO);
	
	ViewRouteVehiclesMainVO getRouteVehiclesByRouteBoardingPointId(long routeBoardingPointId);

	long addStudentFeeConcessionDetails(String concessionFormData,long studentId,AcademicYear academicYear,long userId,long custId);
	StudentFeeConcessionMainVO getStudentFeeConcessionDetails(long custId,long academicYearId);
	StudentFeeVO getStudentFeeDetails(long parentAccountId);
	List<Customer> checkCustomerShortName(String shortName);
	StudentTimetableVO getStudentTimetableDetails(long accountId, String type);
	StudentLibraryBooksVO getStudentLibraryBooksDetails(long accountId, String type);
	MeetingSchedulesVO getMeetingSchedules(long accountId, String type);
	boolean reserveMeetingSlot(MeetingScheduleSlotsVO meetingScheduleSlotsVO);
	boolean meetingScheduleSubmit(MeetingSchedulesMainVO meetingSchedulesMainVO);
	RouteMainVO getAllRoutesForDriver(long accountId);
	boolean updateRoutePoints(RouteVo routeVo);
	boolean saveRouteTrack(RouteTrackVO routeTrackVO);
	ViewRouteTrackVO getVehicleLastLocation(long driverId, long routeId);
	boolean sendBusPickupDropNotification(long driverId, long routeId, String type);
	ViewRouteTrackMainVO getAllRouteVehiclesLocations(long accountId);
	VehicleRouteMainVO getAllRoutes(long custId);
	void updateAndGetTopStudentsMarksDetails(long custId, long academicYearId);
	void updateAndGetTopStaffPerformanceDetails(long custId, long academicYearId);
	void updateAndGetTopStudentsMonthlyAttendanceDetails(long custId,long academicYearId);
	void updateAndGetTopStudentsDailyAttendanceDetails(long custId,long academicYearId);
	void updateStudentPaymentStatusForSchoolFee(long classId,long categoryId);
	boolean runPaySlipsReports(PayslipBaseVO payslipBaseVO);
	List<Object[]> getCustomerFeePaidAndUnpaiddetailSummary();
	boolean notifyNearBusStop(RouteNotifyVO routeNotifyVO);
	VehicleRouteMainVO getAllRoutesByAccountIdAndType(long accountId, String type);
	ViewStaffPersonAccountDetailsMainVO getStaffDetailsForDesktop(long custId,long academicYearId);
	ClassTeacherMainVO getStaffClassTeacherDetailsForDesktop(long custId,long academicYearId);
	void sendNotificationForClassAssignments(List<String> classIds, ClassAssignment classAssignment,boolean isNewAssignment);
	void sendNotificationForScoreCard(long studentId, String parentId, long classSectionId,String fullName,long examTypeId,String examType,long userAcademicYearId,long userCustId,long accountId);
	void sendNotificationForScoreCardByClassSectionId(long classSectionId);
	void sendNotificationForFeePayment(long parentId);
	boolean sendReminderForMeetingSchedule(long meetingScheduleId);
	void sendNotificationForEventDelete(long eventId, long custId);
	void sendNotificationForStudentMarks(long custId);
	
	List<Integer[]> getMarksIdByStudentIdAndExamScheduleId(long studentId,long examScheduleId);
	
	List<Events> eventsforStaffStudents(String studyClassIds, long roleId,long custId,long academicYearId,String currentOrCompleted);
	
	StudentAndStaffDailyAttendanceVO getStudentAndStaffDailyAttendance(long academicYearId);
	void compressImage(String path, String ext) throws IOException;
	//void uploadPhotosFronAndroid(long eventId,InputStream uploadedInputStream,String fileName);
	int uploadEventPhotos(long eventId,InputStream uploadedInputStream,String fileName);
	int saveAutoReportsDetails(String reportDetails,long custId,long userId);
	int getAvailableSmsCount(long custId,long academicYearId);
	void sendNotificationForFeePayment(long custId, long studentId, long parentId,String feePayment);
	void updateStudentPaymentStatusForTransportFee(long boardingPointId,long categoryId);
	void studentFeePaidStatusWithTransport(long studentId,long custId,long academicYearId,long categoryId,long classId);
	void studentFeePaidStatusForTransport(long studentId,long custId,long academicYearId,long categoryId);
	void updateFeeConfiguredStatusInStudentForTransport(long boardingPointId,long categoryId,String status,long studentId);
	int removeClassFee(Fee fee,ClassName className);
	void removeFee(long feeId);
	void updateStudentsFeePaidStatusClassWise(long custId,long categoryId,long classId);
	boolean removeStudyClassSubjectAssignedTecher(long custId,long academicYearId,long studyClassId,long subjectId,long teacherId);
	List<ViewStudentsLatestExamMarksDetails> getLatestExamsClasses(long custId,long academicYearId);
	List<ViewClassExamDetails> getAllSectionsExamSchedulesDetails(long custId,long academicYearId,String classSectionIds,long examId,String subtypeIds);
	List<ScoreCardTemplates> getAllScorecardTemplatesByAcademicYearId(long custId,long academicYearId);
	boolean moveImageFiles();
	
	String getUploadImageFilePath(File file,String academicYear,String fileName);
	boolean moveUserImageFiles();
	
	SMSBaseVO uploadEventAlbumPhotos(long eventId,String url);
	void generateBarcode(long userId);
	void generateBarCodeByUserObj(User user,AcademicYear academicYear);
	boolean movePayslips();
	boolean moveTimetables();
	Object[] getAllStudentsByClassNameAndCastAndSubCastName(long custId,long academicYearId,List<CastSettings> castList,String classSectionIds,String userName);
	String getUploadTemplates(File file,String academicYear,String fileName);
	List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDateForPreSchool(long classSectionId, String attendanceDate);
	boolean moveCertificatesAndDocuments();
	String getUploadDocuments(File file,String academicYear,String fileName);
	void removeClassTeacherByStudyClassId(long custId, long studyClassId);
	List<ClassTeacher> getClassTeachersListByStaffId(String query);
	List getAllStaffMobileNumbersByRole(long custId,String roleIds);
	List<ViewStudentDeleteFeeDetails> getStudentDeleteInvoiceDetailsById(long userCustId,long userAcademicYearId, String feeType);
	List<ViewStudentDeleteFeeDetails> getStudentDeleteOtherFeeDetailsById(long userCustId,long userAcademicYearId, String feeType);
	StudentPaymentListVO getStudentPaymentDetails(long custId,long academicYearId,long paymnetId);
	
	List<ExamTypes> getExamTypesByStudyClassId(long studyClassId);
	List<StudySubject> getSubjectsByStudyClassId(long studyClassId);
	List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeePaymentDetails(long studentId,long academicYearId,long feeSettingId);
	List<ViewStudentTransportFeePaymentDetails> getStudentTransportFeeDetails(long studentId,long academicYearId,long termId);
	List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeeViewPaymentDetails(long studentId,long academicYearId,long feeSettingId);
	List<ViewStudentTransportFeePaymentDetails> getAllStudenTransportFeePaymentDetails(long id, long academicYearId,long schoolTermId);
	ViewStudentTransportFeePaymentDetails getStudentNonPaidTransportFeePaymentDetails(long studentId,long academicYearId,long termId,long feeTypeId);
	List<ViewStudentFeePaymentDetails> getStudentFeeUnpaidDetails(String tableName,long custId,long academicYearId,String termId,String selectedClassSectionIds);
	ViewStudentFeePaymentDetails getStudentWiseFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,long termId,long classSectionId,long studentId);
	public Student upadateStudentMarksFromSMSAppAndWebNew(Student student,long examSchedules,double obtainedMarks,long accountId, long examTypeId, String isPresent,HashMap<Integer, String> showingResult,HashMap<Long, ExamSchedules> schedulesMap);
	ViewClassAssignmentDetailsMainVO studentAssignmentDetails(long studentId); 
	void sendNotificationForDeleteClassAssignment(List<String> classIds,List<String> assignmentIds);
	void sendNotificationForCompletionOfClassAssignments(List<String> studentAccountIds, ClassAssignment classAssignment);

	List<ViewStudentFeePaymentDetails> getStudentTermsWisePaidClassFeePaymentDetails(long studentId,long academicYearId);
	List<ViewStudentFeePaymentDetails> getStudenClassFeePaymentDetails(long studentId,long academicYearId,long termId);
	List<Object[]> getAttendanceNotSubmittedDates(long classSectionId ,long academicYearId, String monthIds);
	void saveClassTeacherDetails(ClassTeacher classTeacher,long custId,long userId,Staff staff,long studyClassId,StudySubject subject,AcademicYear academicYear);
	List<Object[]> getParentOccupationDetails(long custId,	long academicYearId, String classIds);
	public Map<String, String> saveOrUpdateTimetableDetails(String tempString, long studyClassId, String changeInTimetable, User user, Date startDate);
	List<StudentMarks> getLatestUploadedMarksForStudent(long classSectionId,long academicYearId,int noOfExamTypes,long studentId);
	public List<Object[]> getVehiclesWithDriverDetails(long custId,boolean AcademicYearStatus);

	public int addOrUpdateQuestionPaperBank(QuestionPaperBankVO questionPaperBankVo, User user,AcademicYear academicYear);
	List<QuestionPaperBankVO> getQuestionBankList(long userCustId, long  academicYearId ,String classSectionId , String subjectId);
	public QuestionPaperBankVO getQuestionBankByLesson(long custId,long academicYearId,String classSectionId,String subjectId,String lessonId,String questionBankId);
	public int removeQuestionBank(long questionBankId);
	public int removeQuestionBankAttachment(long questionBankId,long attachmentId);
	public List<Object[]> getParentIncomeWiseDetails(long custId,long academicYearId, String classIds,String incomeRangeIds );
	}	