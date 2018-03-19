package com.urt.persistence.interfaces.admin;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
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
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.OverAllGrades;
import com.urt.persistence.model.exam.SchoolGrades;
import com.urt.persistence.model.exam.ScoreCardTemplates;
import com.urt.persistence.model.exam.SubType;
import com.urt.persistence.model.exam.Syllabus;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.exam.ViewExamSchedule;
import com.urt.persistence.model.exam.ViewStaffSubjectsDetails;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.fee.ViewStudentDeleteFeeDetails;
import com.urt.persistence.model.fee.ViewStudentsExcessPaymentDetails;
import com.urt.persistence.model.hostel.Building;
import com.urt.persistence.model.secretary.ViewBudgetRequestDetails;
import com.urt.persistence.model.secretary.ViewMeetingRequestDetails;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.PromoteClass;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Section;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StaffElgibleSubjects;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentAcademicPerformance;
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
import com.urt.persistence.model.views.VWStudentPaymentDetails;
import com.urt.persistence.model.webservice.ViewFee;

public interface AdminDao extends UniversalDao {
	
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
	List<ClassName>getClassesByClassIdsAndAdmissionStatus(long custId,long academicYearId,String status,String classIds,boolean isClassIdIn);
	/*List<StudyClass> getStudyClassesByCustId(long custId,long academicYearId);*/
	StudyClass getclassByClassAndsection(String className,String sectionName,long customerId,long academicYearId);
	//List getAllClassesById(long classId,String status);
	List getAllFeesByCustId(long classId);
	Fee getFeeByClassId(long classId);
	List<Student> getAllStudentsByCustId(long custId,String status,long academicYearId);
	List<Student> getAllStudentsByClassName(long classId,long custId,String status,long academicYearId);
	List<Student> getAllStudentsByRollNumber(String rollNumber,long custId);
	ViewStaffLeaveDetails getViewStaffLeaveDetailsByLeaveId(long leaveid,long customerId);
	List<ViewStaffLeaveDetails> getAllLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId);
	List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId);
	Role getRoleDetailsByRoleName(String roleName);
	List<StudyClass> getSubjectsByClassName(String className,long custId,long academicYearId);
	List getSectionByStudyClass(String className);
	Student getStudentByRollNumber(String rollNumber,long custId);
	List<Person> checkPersonId(String userId);
	List<StudySubject> getSubjectsForHigherClass(String subjectId);
	StudentPayment getPaymentStatusByStudentId(long studentId);
	List getSubjectsByClass(long studyClassId);
	LeaveManagement getLeaveManagementByRoleName(long roleId,long custId,long academicYearId);
	List getStudentsPaymetDetailsByCustId(String paymentType,long custId);
	List<ViewStaffPersonAccountDetails> getViewStaffDetailsByRoleName(String rollName,long customerId,String status);
	List getLeavesByAccountId(long accountId);
	void updateAllStaffLeaveDetailsByCasualANDSickLivesAndStaffIds(String staffIds,long casualLeaves,long sickLeaves, long custId);
	List<ViewStudentClassDetails> getStudentsPayentByRollNumber(String rollNumber,long custId,long academicYearId);
	List<ViewStudentPersonAccountDetails> getStudentTransportPayentByRollNumber(String rollNumber,long custId,String status,String transportStatus);
	List getClassSubjectByClassId(long classId);
	Fee getPaymentStatusByClassId(long classId);
	/*ClassName getClassBystudyClassId(long classId);*/
	List<ViewStudentClassFeePaymentDetails> getStudentByClassIdAndFee(long termId,long custId);
	void deleteStudentAttendanceByStudentId(long studentId);
	List<Fee> getAllAdminFeeStausList(String status);
	List<StudentPayment> getALlReceiptsByPaymentId(long spId,String createdDate);
	List getFeesPaymentListByStudentPaymentId(String table,long studentId,long classId,long custId,long academicYearId,String today,String paymentStatus,long invoiceNumber);
	List getAllNoticeBoardMessagesList(String attendanceStatus,long customerId,String academicYearId);
	List<ExamTypes> getAllClassExamTypes(long custId,String academicYear);
	List getAllHolidayBoardMessagesList(String attendanceStatus,long customerId);
	ClassName getClassByClassName(String className,long custId,long academicYearId,boolean isAutoCheck);
	ViewStaffPersonAccountDetails getStaffLastRecordByRoleName(String roleName,long customerId);
	long getMaxExamTypeIdFromStudentMarks(long classId,long academicYear);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	ClassName saveClassName(ClassName className);
	
	List getSyllabusByClassIdAndCustId(long classId,long custId);
	AdmissionSettings getAdmissionSettingsByCustIdAndClassId(long custId,long classId);
	/*List<ClassName> getClassesByCustId(long custId,long academicYearId);*/
	OnlineApplicationDetails getDetailsByApplicationNumber(String applicationNumber);
	/*List<ClassName> checkClassId(String className,long custId,long academicYearId);*/
	/*List getStudyClassesByCustIdAndGroupByClassNameClassId(long customerId,String academicYearId);*/
	List getStudentsByClassNameClassId(long classNameClassId,long customerId);
	List getAllHolidaysListByAcademicYearId(String status,long customerId,long academicYearId,long classNameClassId);
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
	List<KBank> getKBankBySearchKewordsKBankTypeId(String searchKeywords,long kBankTypeId,long custId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
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
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
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
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
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
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
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
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	long getFeeTotalAmountByTerm(String queryString,long custId,long classId,long academicYearId,long schoolTermId,long categoryId,long boardingPointId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
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
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	SchoolTerms saveSchoolTerms(SchoolTerms schoolTerms);
	List getAllFeeByClasIdAndCustIdAndTermId(String table,List<String> classIds,long custId,long academicYearId,long termId,String categoryIds);
	Fee getFeeByClasIdAndCustIdAndTermIdAndTypeId(long classId,long typeId,long termId,long custId,long academicYearId);
	List<Syllabus> getSyllabusByClassIdAndSubjectIdAndCustId(long subjectId,long custId,long studyClassId);
	List<Syllabus> getSyllabusBySyllabusIdsAndCustId(String syllabusIds,long custId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	long getStudentPaidAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,long termId,long boardingPointId);
	
	List getAllTitlesByPlaylist(String subjectName);
	List getPlayListSubjects(String clazz);
	List getPlayListForSubTopics(String subName);
	List getVideoPlayList(long playListId);
	List getStaffsListByRoleAndCustIdAndStatus(String roleName,long custId,String status,long academicYearId);
	ViewStaffPersonAccountDetails getStaffDetailsByRoleNameAndStaffIdAndCustId(String roleName,long custId,long staffId,String status,long academicYearId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	long getStudentDiscountAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber);//long termId,String status
	
	ClassTeacher getClassTeacherByAcademicIdAndNotInCurrentClass(long staffId,long custId,long academicYearId,long classId);
	void removeClassTeacherByCustId(long classTeacherId,long custId);
	State getStateCodeByStateName(String stateName);
	List<ViewStaffLeaveDetails> getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId);
	List<ViewStudentLeaveDetails> getAllStudentLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId);
	ViewUserRoles getViewUserRolesByUserIdAndCustId(long userId, long customerId,String status);
	List getStudentClassFeePamentByTodayDateAndcustIdAndStatus(long userCustId, long reqYear,String todayDate,String status);
	List getStudentPaidTermsAmountByClassIdAndStatus(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber);//long termId,String status
	List getStaffPaidTermsAmountByStaffIdAndStatus(String queryString,long staffId,long custId,long academicYearId,String feeIds,long invoiceNumber);
	List getClassFeeTermsByStudentIdAndStatusAndCurrentDate(String queryString,long studentId,long custId,long academicYearId,long classId,String todayDate,String feeIds,long invoiceNumber);
	List getHostelFeeTermsByStaffIdAndStatusAndCurrentDate(String queryString,long studentId, long custId, long academicYearId,String todayDate, String feeIds, long invoiceNumber);
	List getAllSmsEventsByAcademicYear(String academicYear);
	List<SubType> getAllSubTypesBySubTypeIds(String subTypeIds,long custId);
	SchoolGrades getSchoolGradeByName(String gradName, long academicYearId);
	List<ExamTypes> checkExamTypeByNameAndCustId(String examType,long custId);
	List getClassTeachersByAcademicIdAndSubjectIdAndStaffIdandCustId(long staffId,long subjectId,long custId,long academicYearId);
	void removeClassTeacherByClassTeacherIdAndSubjectIdAndCustId(long classTeacherId,long subjectId,long custId,long academicYearId);
	List getClassSubjectBySubjectId(long subjectId);
	ClassTeacher getClassTeachersByAcademicIdAndSubjectIdAndClassIdandCustId(long studyClassId,long subjectId,long custId,long academicYearId);
	List getSubTypesByClassSectionIdAndExamTypeId(long classSectionId,long examTypeId);
	void removeClassTeachersByStudyClassIdandSubjectIdsandCustId(long classSectionId,long custId,String subjectsIds);
	void removeSyllabusByStudyClassIdandSubjectIdsCustId(long classSectionId,long custId,String subjectIds);
	List<SchoolTerms> checkSchoolTermsByNameAndCustId(String termName,long custId,long academicYearId);
	List<ViewStaffSubjectsDetails> getClassTeacherClassesListByStaffIdCustIdAcademicYearIdGroupByClassTecherStatusAndStudyClassId(long academicYearId,long staffId,long custId);
	List<ViewStaffSubjectsDetails> getTeacherClassesAndSubjectsListByStaffIdCustIdAcademicYearIdOrderbyStudyClassId(long academicYearId,long staffId,long custId);
	List getSchoolTermsByDuedate(String table,long custId,long academicYearId,String todayDate,long feeSettingId);
	List getSchoolTermsOrderByDuedate(String table,long custId,long academicYearId,long feeSettingId);
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
	List getAllStudentInvoiceFeeDetailsByCustId(String queryString,long customerId,long academicYearId,long termId,String today,String daysBetwwenenfromDate,String daysBetwwenendDate,String transportmode,long feesettingId,String selectedClassSectionId);//,String paymentStatus
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
	ClassTeacher getClassTeacherByStudyClassIdAndClassTeacherStatus(long studyClassId, String classTeacherType,long academicYearId) ;
	List<ViewStudentPersonAccountDetails> getParentChildrens(long custId,long parentId,long academicYearId);
	List<ViewStaffLeaveDetails> getAllFutureLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId) ;
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	TransferCertificate saveTCSerialNumber(TransferCertificate transferCertificate);
	List<SubCastSettings> getSubcastBySubCastNameAndCastId(long customerId,String subCastName,long castId);
	List getSubcastSettingsByCastIdAndCustId(long castId, long customerId);
	List getEventsByDates(long custId,String startDate,String endDate);
	List getWorkingDayHolidaysByDates(long custId,String status,String startDate,String endDate);
	List<StaffElgibleSubjects> getAllStaffElgibleSubjectsByStudySubjectId(long studySubjectId);
	Object[] getAllStudentsByClassNameAndCastName(long custId,long academicYearId,List<CastSettings> castList,long classId,String userName);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	Section saveSectionName(Section sectionName);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	CastSettings saveComunityName(CastSettings castSettings);
	Object[] getAllStudentsCountByCastWise(long custId,long academicYearId,List<CastSettings> castList,String username,String classNameClassIds);
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
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	Address saveAddress(Address address) throws DataAccessException;
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
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	SMSServiceProviders saveSMSUrl(SMSServiceProviders smsProvider);
	void updateRemainingSMSProviderstoInactiveState(long providerId);
	List getSchoolTermsByCustIdAndAcademicYearId(String table,long custId,long academicYearId);
	List<Building> checkBuildingId(String buildingName,long custId,long academicYearId);
	int getMaxPeriodsByClassSectionIdAndDaydId(String classSectionId, String dayId,String periodType) ;
	List<ViewClassSubjectsSettings> getAllClassWiseSubjects(String clause);
	void updateTimeTableTeacherIdandSubjectIdsByAcademicYearId(long academicYearId);
	List<ViewStudyClassSubjects> getAllStudyClassSubjects(String clause);
	void updateClassSubjectsPeriodsCount(long classSectionId);
	void updateClassTeacherHandlePeriodsCount(long classSectionId);
	void updateClassStatus(long custId,long academicYearId);
	List<ViewStudentClassDetails> getAllStudentDetailsByGender(long custId,long academicYearId,long classSectionId);
	OverAllGrades getOverAllGradeByName(String gradName, long academicYearId);
	void updateExamTypesOrder(long examTypeId,long sortingOrder);
	void updateSubTypesOrder(long subtypeId,long sortingOrder);
	void updateTimeTableDetailsByClassSectionIdAndSubjectIds(long classSectionId,String subjectIds);
	void updateAdmissionSettingsStatus(long admissionSettingId,long custId);
	void removeClassSubjectsSettingsByClassSectionId(long studyClassId);
	void removeTimeTablePeriodsByClassSectionId(long studyClassId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	AdmissionSettings saveAdmissionSettings(AdmissionSettings admissionSettings);
	void updateClassAdmissionStatusToActive(String classIds,long academicYearId);
	void generateShortListApplications(long custId,long academicYearId,String applicationId);
	void generateRejectedListApplications(long custId,long academicYearId,String applicationId);
	SchoolHolidays getSchoolHolidayByDate(long custId,String selectedDate);
	void updateStudentDailyAttendance(long studentId,String leaveRequest,String attendanceDate);
	void updateStudentTransport(long studentId,Long vehicleAcademicDetailId, Long boardingPointId);
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
	void updateStudentHostel(long studentId,long roomId);
	List<ViewClassSectionTeacher> getAllClassSectionTeachersDetails(String clause);
	List<SchoolTerms> getFeeTermsByRemainderDays(long academicYearId);
	List<ExamSchedules> getExamSchedulesForSendingMobileAlerts(long academicYearId);
	List<ExamSchedules> getAllExamSchedulesForSendingEmailAlerts(long academicYearId,long classSectionId);
	List<StudentMarks> getLatestUploadedStudentsMarks(long classSectionId,long academicYearId,int noOfExamTypes);
	List<StudentMarks> getLatestUploadedExamTypesByStudentId(long studId,int noOfExamTypes);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
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
	List<ViewStudentsExcessPaymentDetails> getAllStudentsExcessPayments(String classSectionId,long academicYearId,long custId);
	List<ViewStudentFeePaymentDetails> getStudentClassFeeDetails(long studentId,long academicYearId,long termId);
	List<ViewStudentFeePaymentDetails> getStudentFutureAcademicTermsWiseNonPaidClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId);
	List<ViewStudentFeePaymentDetails> getStudentFutureClassFeeDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId);
	List<ViewStudentFeePaymentDetails> getStudentNonPaidFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId);
	ViewStudentFeePaymentDetails getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(long studentId,long academicYearId,long termId,long feeTypeId,long classId,long classSectionId);
	List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseFutureClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId);
	List<ViewStudentFeePaymentDetails> getAllStudentFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId);
	List<ViewStudentFeePaymentDetails> getFutureAcademicYearStudentFeeReceipts(long studentId,long academicYearId,String invoiceNumber);
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds);
	List<ViewFee> getViewFeeDetails(String clause);
	void updateStudentPaymentStatus(long classId,long categoryId,String status);
	void updateFeeConfiguredStatusInStudent(long classId,long categoryId,String status);
	List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate);
	List<VWStaffAttendance> getStaffAttendanceByAttendanceDate(String attendanceDate,long custId,long academicYearId,String staffBiometric);
	List<Fee> getClassFeeListByTermId(String schoolTermIds,String classIds,long academicYearId,long custId);
	List<ViewStudentFeePaymentDetails> getFeeCollectionList(String tableName,long custId,long academicYearId,String toDayDate,String daysBeetweenStartDate,String daysBeetweenEndDate,String receiptType);
	List<ViewStudentFeePaymentDetails> getTermWiseFeeCollectionList(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String receiptType);
	List<ViewStudentFeePaymentDetails> getFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate);
	List<ViewStudentFeePaymentDetails> getStudentFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String studentStatus);
	void updateSelectedLoginRoles(String selectedId,Long custId);
	void updateUnselectedLoginRoles(String unselectedId,Long custId);
	List<ViewHostelStudentDailyAttendance> getHostelStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate);
	void insertIntoLoginAcceessbilityRoles(List<Role> roles,long custId);
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermId(String tableName,long custId,long academicYearId,String termIds,String fromDate,String toDate,String feeSettingIds);
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermIdAndEndDateAndStatus(String tableName,long custId,long academicYearId,String termIds,String toDate,String feeSettingIds,String checkValue);
	List<OnlineApplicationDetailsView> getAdminssionApplicationFeeDetails(long custId,long academicYearId);
	List<ViewStudentMarksDetails> getStudentMarksDetailsByClassIdAndAcademicYearIdAndCustId(long custId,long academicYearId,long classSectionId);
	/*List<StudyClass> GetAllStudyClasses(long custId,long academicYearId,String studyClassIds);*/
	List<StudySubject> GetAllStudySubjects(long custId,long academicYearId);
	long getAdmissionReceiptNumberByCustId(String table,long custId,long academicYearId);
	List<ViewStudentFeePaymentDetails> getFeeCollecttionWithPercentageDetailsByClassWise(String tableName,long custId,long academicYearId,String classSectionIds,String perValue,String statusValue);
	List<Object[]> getStaffLeavesSummary(long custId, long academicYearId);
	List<ViewStudentFeePaymentDetails> getStudentFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate);
	List<Object[]> getStudentAttendanceSummaryDetails(long academicYearId,long accountId, String type);
	void updateHolidayStatusForClassWiseHolidays(String table,long custId, long academicYearId);
	List<Object[]> getStudentDailyAttendanceSP(long custId, long academicYearId,long classSectionId);
	
	List<ViewBudgetRequestDetails> getAllViewBudgetRequestDetailsByFinancialYearIdCustIdStatus(long financialYearId,long custId,String statuss);
	List<ViewMeetingRequestDetails> getAllViewMeetingRequestDetailsByOrgIdCustIdDate(long orgId,long custId,String status);
	List<Object[]> getMonthlyStudentAttendanceSP(int pMonth,long classSectionId, long custId, long academicYearId);
	void insertStudyClassIdInsteadOfClassId(long studyClassId, long examTypeId);
	void updateSelectedCustomersOrgId(String selectedId,Long orgId,String tempString);
	List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeIdAndSubTypeId(long studId,long examTypeId,long subtypeId);
	List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassIdAndFinanceUserId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds,long financeUserId);
	void removeAllSchoolHolidaysByDescriptionAndStartAndEndDate(long custId,String description,String startDate,String endDate);
	List<Customer> checkCustomerShortName(String shortName);
	void updateAndGetTopStudentsMarksDetails(long custId, long academicYearId);
	void updateAndGetTopStaffPerformanceDetails(long custId, long academicYearId);
	void updateAndGetTopStudentsMonthlyAttendanceDetails(long custId,long academicYearId);
	void updateAndGetTopStudentsDailyAttendanceDetails(long custId,long academicYearId);
	void updateStudentPaymentAndConfifuredStatusForSchoolFee(long classId,long categoryId,String status);
	List<Object[]> getCustomerFeePaidAndUnpaiddetailSummary();
	
	List<Integer[]> getMarksIdByStudentIdAndExamScheduleId(long studentId,long examScheduleId);
	void updateFeeConfiguredStatusInStudentForTransport(long boardingPointId,long categoryId,String status,long studentId);
	void updateStudentPaymentStatusForTransport(long boardingPointId,long categoryId,String status,long studentId);
	void updateStudentPaymentAndConfifuredStatusForTransport(long boardingPointId,long categoryId,String status,long studentId);
	void studentFeePaidStatusForTransport(long studentId,long custId,long academicYearId,long categoryId);
	void updateStudentPaymentStatusForTransportFee(long boardingPointId,long categoryId);
	void studentFeePaidStatusWithTransport(long studentId,long custId,long academicYearId,long categoryId,long classId);
	void removeFee(long feeId);
	void updateStudentsFeePaidStatusClassWise(long custId,long categoryId,long classId);
	boolean removeStudyClassSubjectAssignedTecher(long custId,long academicYearId,long studyClassId,long subjectId,long teacherId);
	List<ViewStudentsLatestExamMarksDetails> getLatestExamsClasses(long custId,long academicYearId);
	public List<ViewClassExamDetails>  getAllSectionsExamSchedulesDetails(long custId,long academicYearId,String classSectionIds,long examId,String subtypeIds);
	List<ScoreCardTemplates> getAllScorecardTemplatesByAcademicYearId(long custId,long academicYearId);
	Object[] getAllStudentsByClassNameAndCastAndSubCastName(long custId,long academicYearId,List<CastSettings> castList,String classSectionIds,String userName);
	List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDateForPreSchool(long classSectionId, String attendanceDate);
	List<Object[]> getPreSchoolStudentAttendanceSummaryDetails(long academicYearId,long accountId, String type);
	List<ClassTeacher> getClassTeachersListByStaffId(String query);
	List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeIdAndScheduleId(long studId,long examTypeId,long custId,long academicYearId,long scheduleId);
	List getAllStaffMobileNumbersByRole(long custId,String roleIds);
	List<StudyClass> getStudyClasses(String queryString);
	List<VWStudentPaymentDetails> getStudentPaymentDetails(long custId,long academicYearId,long paymnetId);
	List<ExamTypes> getExamTypesByStudyClassId(long studyClassId);
	List<StudySubject> getSubjectsByStudyClassId(long studyClassId);
	List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeePaymentDetails(long studentId, long academicYearId,long feeSettingId);
	List<ViewStudentTransportFeePaymentDetails> getStudentTransportFeeDetails(long studentId,long academicYearId,long termId);
	List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeeViewPaymentDetails(long studentId, long academicYearId, long feeSettingId);
	List<ViewStudentTransportFeePaymentDetails> getAllStudenTransportFeePaymentDetails(long studentId, long academicYearId, long schoolTermId);
	ViewStudentTransportFeePaymentDetails getStudentNonPaidTransportFeePaymentDetails(long studentId,long academicYearId,long termId,long feeTypeId);
	List<ViewStudentFeePaymentDetails> getStudentFeeUnpaidDetails(String tableName,long custId,long academicYearId,String termId,String selectedClassSectionIds);
	ViewStudentFeePaymentDetails getStudentWiseFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,long termId,long classSectionId,long studentId);
	List<ViewStudentDeleteFeeDetails> getStudentDeleteInvoiceDetailsById(long userCustId,long userAcademicYearId, String feeType);
	List<ViewStudentDeleteFeeDetails> getStudentDeleteOtherFeeDetailsById(long userCustId, long userAcademicYearId, String feeType);
	List<ViewStudentFeePaymentDetails> getStudentTermsWisePaidClassFeePaymentDetails(long studentId, long academicYearId);
	List<ViewStudentFeePaymentDetails> getStudenClassFeePaymentDetails(long studentId,long academicYearId,long termId);
	List<Object[]> getAttendanceNotSubmittedDates(long classSectionId ,long academicYearId, String monthIds);
	List<Object[]> getParentOccupationDetails(long custId,	long academicYearId, String classIds);
	List<StudentMarks> getLatestUploadedMarksForStudent(long classSectionId,long academicYearId,int noOfExamTypes,long studentId);
	public List<Object[]> getVehiclesWithDriverDetails(long academicYearId,boolean status);
	public List getParentIncomeWiseDetails(long custId,long academicYearId,String classIds,String incomeRangeIds);
}
