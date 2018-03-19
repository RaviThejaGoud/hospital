drop table IF EXISTS vw_AcademicYearTimings;
drop table IF EXISTS vw_BudgetRequestDetails;
drop table IF EXISTS vw_Fee;
drop table IF EXISTS vw_FeedbackRatingDetails;
drop table IF EXISTS vw_MessagesDetails;
drop table IF EXISTS vw_ProductDetailsByCustomer;
drop table IF EXISTS vw_SmsApp_StudentDailyAttendance;
drop table IF EXISTS vw_StaffDailyAttendance;
drop table IF EXISTS vw_StaffDailyAttendanceDetails;
drop table IF EXISTS vw_StudentClassAssignment;
drop table IF EXISTS vw_StudentDailyAttendance;
drop table IF EXISTS vw_StudentMonthlyAttendance;
drop table IF EXISTS vw_accountGroupCategory;
drop table IF EXISTS vw_allUsers;
drop table IF EXISTS vw_appointmentDetails;
drop table IF EXISTS vw_assignedVehiclestoRoutes;
drop table IF EXISTS vw_assignedVehiclestoRoutesWithBoardingPoints;
drop table IF EXISTS vw_bookAndLabelDetails;
drop table IF EXISTS vw_bookTitleAndBlockDetails;
drop table IF EXISTS vw_bookrackassignmentdetails;
drop table IF EXISTS vw_buildingFloorDetails;
drop table IF EXISTS vw_buildingMenuItemsDetails;
drop table IF EXISTS vw_calculatePassAndFailCountStudents;
drop table IF EXISTS vw_calculatePassAndFailCountStudentsBySubjects;
drop table IF EXISTS vw_circularusers;
drop table IF EXISTS vw_classAssignmentDetails;
drop table IF EXISTS vw_classExamDetails;
drop table IF EXISTS vw_classExamSubTypes;
drop table IF EXISTS vw_classFeeDetails;
drop table IF EXISTS vw_classFeeTypes;
drop table IF EXISTS vw_classSectionDetails;
drop table IF EXISTS vw_classSectionTeacher;
drop table IF EXISTS vw_classSubjectsSettings;
drop table IF EXISTS vw_classWisePeriodsCountDetails;
drop table IF EXISTS vw_combinedClassSubjectDetails;
drop table IF EXISTS vw_customeraddress;
drop table IF EXISTS vw_examSchedule;
drop table IF EXISTS vw_examScheduleDetailsWithTopperMarks;
drop table IF EXISTS vw_examScheduleWiseStudentMarks;
drop table IF EXISTS vw_examTypesSchedules;
drop table IF EXISTS vw_examWiseTopperDetails;
drop table IF EXISTS vw_hostelAllDetails;
drop table IF EXISTS vw_hostelBuildingDetails;
drop table IF EXISTS vw_hostelRoomBedDetails;
drop table IF EXISTS vw_hostelStudentDailyAttendance;
drop table IF EXISTS vw_hostelStudentDetails;
drop table IF EXISTS vw_hostelStudentLeaveDetails;
drop table IF EXISTS vw_issueProvisionItemsToMess;
drop table IF EXISTS vw_issuedBookAndSettings;
drop table IF EXISTS vw_labEquipmentDetails;
drop table IF EXISTS vw_labInchargersDetails;
drop table IF EXISTS vw_labRequestToStoreDetails;
drop table IF EXISTS vw_leaveManagementTypesRoles;
drop table IF EXISTS vw_marksUpdatedDetails;
drop table IF EXISTS vw_meetingRequestDetails;
drop table IF EXISTS vw_onlineApplicationDetails;
drop table IF EXISTS vw_onlineApplicationStudentClassFees;
drop table IF EXISTS vw_parentAppointmentDetails;
drop table IF EXISTS vw_parentFeedBackResponse;
drop table IF EXISTS vw_permissionSettings;
drop table IF EXISTS vw_practicalBreakageDetails;
drop table IF EXISTS vw_practicalDetails;
drop table IF EXISTS vw_practicalEquipments;
drop table IF EXISTS vw_promotionClassesDetails;
drop table IF EXISTS vw_racksubjectdetails;
drop table IF EXISTS vw_roomDetails;
drop table IF EXISTS vw_routeWiseStudents;
drop table IF EXISTS vw_scholarShipDetails;
drop table IF EXISTS vw_semesterSubjectMarks;
drop table IF EXISTS vw_societyMembersDetails;
drop table IF EXISTS vw_staffAllotedBeds;
drop table IF EXISTS vw_staffAppointmentDetails;
drop table IF EXISTS vw_staffAttendance;
drop table IF EXISTS vw_staffDetails;
drop table IF EXISTS vw_staffDetailsByDays;
drop table IF EXISTS vw_staffDriverDetails;
drop table IF EXISTS vw_staffEligibleSubjects;
drop table IF EXISTS vw_staffLeaveDetails;
drop table IF EXISTS vw_staffMonthlyAttendance;
drop table IF EXISTS vw_staffPayrollDetails;
drop table IF EXISTS vw_staffPermissionRequests;
drop table IF EXISTS vw_staffPermissionsSettings;
drop table IF EXISTS vw_staffStudentVehicleTripDetails;
drop table IF EXISTS vw_staffSubjectsDetails;
drop table IF EXISTS vw_staffSyllabusPlanner;
drop table IF EXISTS vw_staffVehicleTripDetails;
drop table IF EXISTS vw_staffmonthlyattendanceDetails;
drop table IF EXISTS vw_storeEquipmentDetails;
drop table IF EXISTS vw_streamBranch;
drop table IF EXISTS vw_streamCourseYear;
drop table IF EXISTS vw_studentAttendance;
drop table IF EXISTS vw_studentCertificateDetails;
drop table IF EXISTS vw_studentClassAndAttendanceDetails;
drop table IF EXISTS vw_studentClassDetails;
drop table IF EXISTS vw_studentClassFeePaymentDetails;
drop table IF EXISTS vw_studentClassFees;
drop table IF EXISTS vw_studentClasswiseAndPersonalInfo;
drop table IF EXISTS vw_studentCountByBoardingPointId;
drop table IF EXISTS vw_studentCountByRoom;
drop table IF EXISTS vw_studentDeletedInvoiceDetails;
drop table IF EXISTS vw_studentDetails;
drop table IF EXISTS vw_importStudentDetails;
drop table IF EXISTS vw_studentDetailsByDays;
drop table IF EXISTS vw_studentExamMarks;
drop table IF EXISTS vw_studentFeePaidAndNotPaidDetails;
drop table IF EXISTS vw_studentFeePaidDetails;
drop table IF EXISTS vw_studentFeeParticularsPayment;
drop table IF EXISTS vw_studentFeePaymentDetails;
drop table IF EXISTS vw_studentFineFeeDetails;
drop table IF EXISTS vw_studentFutureFeePaymentDetails;
drop table IF EXISTS vw_studentLeaveDetails;
drop table IF EXISTS vw_studentMarks;
drop table IF EXISTS vw_studentMarksDetails;
drop table IF EXISTS vw_studentMessAccessedDetails;
drop table IF EXISTS vw_studentOutHostelDetails;
drop table IF EXISTS vw_studentParticularwiseFeePayments;
drop table IF EXISTS vw_studentPersonInfo;
drop table IF EXISTS vw_studentProjectDetails;
drop table IF EXISTS vw_studentQuestionAnswers;
drop table IF EXISTS vw_studentQuizQuestionAnswers;
drop table IF EXISTS vw_studentScoreCardMarksDetails;
drop table IF EXISTS vw_studentSiblings;
drop table IF EXISTS vw_studentSubjectWiseMarksDetails;
drop table IF EXISTS vw_studentTransportDetails;
drop table IF EXISTS vw_studentissuebookandreservations;
drop table IF EXISTS vw_studentsAbsentiesCount;
drop table IF EXISTS vw_studentsAllotedBeds;
drop table IF EXISTS vw_studentsClassSectionDetails;
drop table IF EXISTS vw_studentsExcessPaymentDetails;
drop table IF EXISTS vw_studentsFutureAcademicFeeDetails;
drop table IF EXISTS vw_studentsFutureAcademicTransportDetails;
drop table IF EXISTS vw_studentsHallTicketDetails;
drop table IF EXISTS vw_studentsMarksAndPerformanceDetails;
drop table IF EXISTS vw_studentsScoreCardProfileDetails;
drop table IF EXISTS vw_studentsTCDetails;
drop table IF EXISTS vw_studentsTransportDetails;
drop table IF EXISTS vw_studyAndBonafiedCertificates;
drop table IF EXISTS vw_studyAndBonafiedDetails;
drop table IF EXISTS vw_studyClassMaterials;
drop table IF EXISTS vw_studyClassStudentsDailyAttendance;
drop table IF EXISTS vw_studyClassStudentsMonthlyAttendance;
drop table IF EXISTS vw_studyClassSubjectDetails;
drop table IF EXISTS vw_subjectWiseAssignedPeriodsCount;
drop table IF EXISTS vw_subjectWisePeriodsDetails;
drop table IF EXISTS vw_suspendAndBlacklistStudents;
drop table IF EXISTS vw_syllabusPlannerComments;
drop table IF EXISTS vw_timeTableDetails;
drop table IF EXISTS vw_transportRequestFormDetails;
drop table IF EXISTS vw_userLoginMetaData;
drop table IF EXISTS vw_userJRExceptionDetails;
drop table IF EXISTS vw_userRoles;
drop table IF EXISTS vw_userStaffLeaveDetails;
drop table IF EXISTS vw_vehicleAndDriverInfo;
drop table IF EXISTS vw_vehicleMaintenance;
drop table IF EXISTS vw_vehicleMaintenanceByMonth;
drop table IF EXISTS vw_vehicleWithBoardingPoint;
drop table IF EXISTS vw_vehicleWithDriverDetails;
drop table IF EXISTS vw_vehiclesWithExpiryDates;
drop table IF EXISTS vw_vehiclesWithExpiryDatesInformation;
drop table IF EXISTS vw_voucherDetails;
drop table IF EXISTS vw_studentConcessionClassFees;
drop table IF EXISTS vw_meetingSchedules;
drop table IF EXISTS vw_ledgerDetailsWithAccountCategories;
drop table IF EXISTS vw_studyCertificateForStudentFeePaidAndNotPaidDetails;
drop table IF EXISTS vw_staffPaySlipDetails;
drop table IF EXISTS vw_messagesDetails;
drop table IF EXISTS vw_classWiseFeeStructure;
drop table IF EXISTS vw_partucularToAccount;
drop table IF EXISTS vw_userJRExceptionDetails;
drop table IF EXISTS vw_studentsLatestExamMarksDetails;
drop table IF EXISTS vw_studentFeeChallanaDetails;
drop table IF EXISTS vw_finAccountDetails;
drop table IF EXISTS vw_studentAdmissionNumberWithSectionWise;
drop table IF EXISTS vw_studentTransportFeePaymentDetails;
drop table IF EXISTS vw_studentTransportParticularwiseFeePayments;
drop table IF EXISTS vw_studentTransportFeeParticularsPayment;
drop table IF EXISTS vw_studentConcessionTransportFees;
drop table IF EXISTS vw_storeDetails;
drop table IF EXISTS vw_issuedItemDetails;
drop table IF EXISTS vw_supplierDetails;
drop table IF EXISTS vw_studentFeeRefundDetails;
drop table IF EXISTS vw_storeBasicDetails;
drop table IF EXISTS vw_studentPerformance;
drop table if exists vw_timeTable;
drop table if exists vw_timeTableStaffDetails;
drop table if exists vw_questionBankDetails;

drop view IF EXISTS vw_studentAdmissionNumberWithSectionWise;
drop view IF EXISTS vw_partucularToAccount;
drop view IF EXISTS vw_staffPaySlipDetails;
drop view IF EXISTS vw_ledgerDetailsWithAccountCategories;
drop view IF EXISTS vw_meetingSchedules;
drop view IF EXISTS vw_AcademicYearTimings;
drop view IF EXISTS vw_BudgetRequestDetails;drop view IF EXISTS vw_Fee;
drop view IF EXISTS vw_FeedbackRatingDetails;
drop view IF EXISTS vw_MessagesDetails;
drop view IF EXISTS vw_ProductDetailsByCustomer;
drop view IF EXISTS vw_SmsApp_StudentDailyAttendance;
drop view IF EXISTS vw_StaffDailyAttendance;
drop view IF EXISTS vw_StaffDailyAttendanceDetails;
drop view IF EXISTS vw_StudentClassAssignment;
drop view IF EXISTS vw_StudentDailyAttendance;
drop view IF EXISTS vw_StudentMonthlyAttendance;
drop view IF EXISTS vw_accountGroupCategory;
drop view IF EXISTS vw_allUsers;
drop view IF EXISTS vw_appointmentDetails;
drop view IF EXISTS vw_assignedVehiclestoRoutes;
drop view IF EXISTS vw_assignedVehiclestoRoutesWithBoardingPoints;
drop view IF EXISTS vw_bookAndLabelDetails;
drop view IF EXISTS vw_bookTitleAndBlockDetails;
drop view IF EXISTS vw_bookrackassignmentdetails;
drop view IF EXISTS vw_buildingFloorDetails;
drop view IF EXISTS vw_buildingMenuItemsDetails;
drop view IF EXISTS vw_calculatePassAndFailCountStudents;
drop view IF EXISTS vw_calculatePassAndFailCountStudentsBySubjects;
drop view IF EXISTS vw_circularusers;
drop view IF EXISTS vw_classAssignmentDetails;
drop view IF EXISTS vw_classExamDetails;
drop view IF EXISTS vw_classExamSubTypes;
drop view IF EXISTS vw_classFeeDetails;
drop view IF EXISTS vw_classFeeTypes;
drop view IF EXISTS vw_classSectionDetails;
drop view IF EXISTS vw_classSectionTeacher;
drop view IF EXISTS vw_classSubjectsSettings;
drop view IF EXISTS vw_classWisePeriodsCountDetails;
drop view IF EXISTS vw_combinedClassSubjectDetails;
drop view IF EXISTS vw_customeraddress;
drop view IF EXISTS vw_examSchedule;
drop view IF EXISTS vw_examScheduleDetailsWithTopperMarks;
drop view IF EXISTS vw_examScheduleWiseStudentMarks;
drop view IF EXISTS vw_examTypesSchedules;
drop view IF EXISTS vw_examWiseTopperDetails;
drop view IF EXISTS vw_hostelAllDetails;
drop view IF EXISTS vw_hostelBuildingDetails;
drop view IF EXISTS vw_hostelRoomBedDetails;
drop view IF EXISTS vw_hostelStudentDailyAttendance;
drop view IF EXISTS vw_hostelStudentDetails;
drop view IF EXISTS vw_hostelStudentLeaveDetails;
drop view IF EXISTS vw_issueProvisionItemsToMess;
drop view IF EXISTS vw_issuedBookAndSettings;
drop view IF EXISTS vw_labEquipmentDetails;
drop view IF EXISTS vw_labInchargersDetails;
drop view IF EXISTS vw_labRequestToStoreDetails;
drop view IF EXISTS vw_leaveManagementTypesRoles;
drop view IF EXISTS vw_marksUpdatedDetails;
drop view IF EXISTS vw_meetingRequestDetails;
drop view IF EXISTS vw_onlineApplicationDetails;
drop view IF EXISTS vw_onlineApplicationStudentClassFees;
drop view IF EXISTS vw_parentAppointmentDetails;
drop view IF EXISTS vw_parentFeedBackResponse;
drop view IF EXISTS vw_permissionSettings;
drop view IF EXISTS vw_practicalBreakageDetails;
drop view IF EXISTS vw_practicalDetails;
drop view IF EXISTS vw_practicalEquipments;
drop view IF EXISTS vw_promotionClassesDetails;
drop view IF EXISTS vw_racksubjectdetails;
drop view IF EXISTS vw_roomDetails;
drop view IF EXISTS vw_routeWiseStudents;
drop view IF EXISTS vw_scholarShipDetails;
drop view IF EXISTS vw_semesterSubjectMarks;
drop view IF EXISTS vw_societyMembersDetails;
drop view IF EXISTS vw_staffAllotedBeds;
drop view IF EXISTS vw_staffAppointmentDetails;
drop view IF EXISTS vw_staffAttendance;
drop view IF EXISTS vw_staffDetails;
drop view IF EXISTS vw_staffDetailsByDays;
drop view IF EXISTS vw_staffDriverDetails;
drop view IF EXISTS vw_staffEligibleSubjects;
drop view IF EXISTS vw_staffLeaveDetails;
drop view IF EXISTS vw_staffMonthlyAttendance;
drop view IF EXISTS vw_staffPayrollDetails;
drop view IF EXISTS vw_staffPermissionRequests;
drop view IF EXISTS vw_staffPermissionsSettings;
drop view IF EXISTS vw_staffStudentVehicleTripDetails;
drop view IF EXISTS vw_staffSubjectsDetails;
drop view IF EXISTS vw_staffSyllabusPlanner;
drop view IF EXISTS vw_staffVehicleTripDetails;
drop view IF EXISTS vw_staffmonthlyattendanceDetails;
drop view IF EXISTS vw_storeEquipmentDetails;
drop view IF EXISTS vw_streamBranch;
drop view IF EXISTS vw_streamCourseYear;
drop view IF EXISTS vw_studentAttendance;
drop view IF EXISTS vw_studentCertificateDetails;
drop view IF EXISTS vw_studentClassAndAttendanceDetails;
drop view IF EXISTS vw_studentClassDetails;
drop view IF EXISTS vw_studentClassFeePaymentDetails;
drop view IF EXISTS vw_studentClassFees;
drop view IF EXISTS vw_studentClasswiseAndPersonalInfo;
drop view IF EXISTS vw_studentCountByBoardingPointId;
drop view IF EXISTS vw_studentCountByRoom;
drop view IF EXISTS vw_studentDeletedInvoiceDetails;
drop view IF EXISTS vw_studentDetails;
drop view IF EXISTS vw_importStudentDetails;
drop view IF EXISTS vw_studentDetailsByDays;
drop view IF EXISTS vw_studentExamMarks;
drop view IF EXISTS vw_studentFeePaidAndNotPaidDetails;
drop view IF EXISTS vw_studentFeePaidDetails;
drop view IF EXISTS vw_studentFeeParticularsPayment;
drop view IF EXISTS vw_studentFeePaymentDetails;
drop view IF EXISTS vw_studentFineFeeDetails;
drop view IF EXISTS vw_studentFutureFeePaymentDetails;
drop view IF EXISTS vw_studentLeaveDetails;
drop view IF EXISTS vw_studentMarks;
drop view IF EXISTS vw_studentMarksDetails;
drop view IF EXISTS vw_studentMessAccessedDetails;
drop view IF EXISTS vw_studentOutHostelDetails;
drop view IF EXISTS vw_studentParticularwiseFeePayments;
drop view IF EXISTS vw_studentPersonInfo;
drop view IF EXISTS vw_studentProjectDetails;
drop view IF EXISTS vw_studentQuestionAnswers;
drop view IF EXISTS vw_studentQuizQuestionAnswers;
drop view IF EXISTS vw_studentScoreCardMarksDetails;
drop view IF EXISTS vw_studentSiblings;
drop view IF EXISTS vw_studentSubjectWiseMarksDetails;
drop view IF EXISTS vw_studentTransportDetails;
drop view IF EXISTS vw_studentissuebookandreservations;
drop view IF EXISTS vw_studentsAbsentiesCount;
drop view IF EXISTS vw_studentsAllotedBeds;
drop view IF EXISTS vw_studentsClassSectionDetails;
drop view IF EXISTS vw_studentsExcessPaymentDetails;
drop view IF EXISTS vw_studentsFutureAcademicFeeDetails;
drop view IF EXISTS vw_studentsFutureAcademicTransportDetails;
drop view IF EXISTS vw_studentsHallTicketDetails;
drop view IF EXISTS vw_studentsMarksAndPerformanceDetails;
drop view IF EXISTS vw_studentsScoreCardProfileDetails;
drop view IF EXISTS vw_studentsTCDetails;
drop view IF EXISTS vw_studentsTransportDetails;
drop view IF EXISTS vw_studyAndBonafiedCertificates;
drop view IF EXISTS vw_studyAndBonafiedDetails;
drop view IF EXISTS vw_studyClassMaterials;
drop view IF EXISTS vw_studyClassStudentsDailyAttendance;
drop view IF EXISTS vw_studyClassStudentsMonthlyAttendance;
drop view IF EXISTS vw_studyClassSubjectDetails;
drop view IF EXISTS vw_subjectWiseAssignedPeriodsCount;
drop view IF EXISTS vw_subjectWisePeriodsDetails;
drop view IF EXISTS vw_suspendAndBlacklistStudents;
drop view IF EXISTS vw_syllabusPlannerComments;
drop view IF EXISTS vw_timeTableDetails;
drop view IF EXISTS vw_transportRequestFormDetails;
drop view IF EXISTS vw_userLoginMetaData;
drop view IF EXISTS vw_userJRExceptionDetails;
drop view IF EXISTS vw_userRoles;
drop view IF EXISTS vw_userStaffLeaveDetails;
drop view IF EXISTS vw_vehicleAndDriverInfo;
drop view IF EXISTS vw_vehicleMaintenance;
drop view IF EXISTS vw_vehicleMaintenanceByMonth;
drop view IF EXISTS vw_vehicleWithBoardingPoint;
drop view IF EXISTS vw_vehicleWithDriverDetails;
drop view IF EXISTS vw_vehiclesWithExpiryDates;
drop view IF EXISTS vw_vehiclesWithExpiryDatesInformation;
drop view IF EXISTS vw_voucherDetails;
drop view IF EXISTS vw_studentConcessionClassFees;
drop view IF EXISTS vw_studyCertificateForStudentFeePaidAndNotPaidDetails;
drop view IF EXISTS vw_messagesDetails;
drop view IF EXISTS vw_classWiseFeeStructure;
drop view IF EXISTS vw_userjrexceptiondetails;
drop view IF EXISTS vw_studentsLatestExamMarksDetails;
drop view IF EXISTS vw_studentFeeChallanaDetails;
drop view IF EXISTS vw_finAccountDetails;
drop view IF EXISTS vw_storeDetails;
drop view IF EXISTS vw_issuedItemDetails;
drop view IF EXISTS vw_supplierDetails;
drop view IF EXISTS vw_studentPerformance;

drop table IF EXISTS vw_substitutionTimeTable;
drop view IF EXISTS vw_substitutionTimeTable;
drop table IF EXISTS vw_taskDetailsAndTaskHistory;
drop view IF EXISTS vw_taskDetailsAndTaskHistory;
drop table IF EXISTS vw_studentDeleteFeeDetails;
drop view IF EXISTS vw_studentDeleteFeeDetails;
drop view IF EXISTS vw_studentTransportFeePaymentDetails;
drop view IF EXISTS vw_studentTransportParticularwiseFeePayments;
drop view IF EXISTS vw_studentTransportFeeParticularsPayment;
drop view IF EXISTS vw_studentConcessionTransportFees;
drop view IF EXISTS vw_studentFeeRefundDetails;
drop view IF EXISTS vw_storeBasicDetails;
drop view if exists vw_timeTable;
drop view if exists vw_timeTableStaffDetails;
drop view if exists vw_questionBankDetails;


CREATE OR REPLACE VIEW vw_userRoles 
AS select a.id accountId,a.custId,a.userName,ur.roleId,r.name AS roleName,r.description 
AS roleDescription,p.firstName,IFNULL(p.lastName,'') as lastName,a.accountExpired, CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as formatedFullName
from UserRoles ur  LEFT JOIN Account a  on (ur.userId=a.id) LEFT JOIN Person p  on (a.personId=p.id) LEFT JOIN Role r on (ur.roleId=r.id);

create or replace view vw_staffDetails as
select a.id AS accountId,a.accountExpired AS accountExpired,a.custId AS custId,IFNULL(a.bioMetricId,0) AS bioMetricId,a.username AS username,a.secondaryUsername as secondaryUsername,a.version AS version,a.barcode,
a.sharePrivacy AS sharePrivacy,a.userOnlineNow AS userOnlineNow,s.id AS staffId,s.description AS description,ay.id AS academicYearId,ay.academicYear AS academicYear,ay.status AS academicYearStatus,
s.qualification1 AS qualification1,s.qualification2 AS qualification2,s.createdDate AS createdDate,s.lastAccessDate AS lastAccessDate,s.status AS status,
s.supervisorId AS supervisorId,ifnull(s.bedId, 0) AS bedId,s.hostelCategoryId AS hostelCategoryId,s.staffType As staffType,
(case when (isnull(s.staffType) or (s.staffType = 'P')) then 'Permanent' when (s.staffType = 'C') then 'Temporary' end) AS 'staffTypeStatus',
ifnull(c.orgnizationTypeId, 0) AS organizationTypeId,s.staffPayrollMonth AS staffPayrollMonth,s.staffPayrollYear AS staffPayrollYear,s.isTimetableUploaded,
sa.salary AS salary,ss.pfNo AS pfNo,ss.esiNo AS esiNo,ifnull(ss.pfPercentage, 0) AS pfPercentage,ss.pfDateofJoin AS pfDateofJoin,ifnull(ss.esiPercentage, 0) AS esiPercentage,
ss.esiDateofJoin AS esiDateofJoin,p.contractStartDate AS contractStartDate,p.contractEndDate AS contractEndDate,p.licenseNumber AS licenseNumber,
p.licenseExpDate AS licenseExpDate,p.experience AS experience,p.dateOfJoining AS dateofJoining,p.firstName AS firstName,concat(p.firstName,' ',IFNULL(p.lastName,'')) as fullName,
p.lastName AS lastName,p.middleName AS middleName,p.dateOfBirth AS dateOfBirth,p.bloodGroup AS bloodGroup,ifnull(p.mobileNumber,'N/A') AS mobileNumber,
p.phoneNumber AS phoneNumber,p.maritalStatus AS maritalStatus,p.summary AS summary,p.gender AS gender,p.familyDoctor AS familyDoctor,p.prefferedHospital AS prefferedHospital,
ifnull(p.religionId, 0) AS religionId,ifnull(ct.skillTypeName,NULL) as religion,ifnull(p.castId, 0) AS castId,ifnull(p.subCastId, 0) AS subCastId,
ifnull(p.annualIncome, 0) AS annualIncome, (case when (isnull(p.designation) or (p.designation = '')) then 'No Designation' else p.designation end) AS designation,
p.gpfNumber AS gpfNumber,p.officeNumber AS officeNumber,p.nationality AS nationality,p.staffUniqueNumber AS staffUniqueNumber,p.panNumber AS panNumber,
ifnull(p.motherToungId, 0) AS motherToungId,ifnull(mot.name, NULL) AS motherTounge,d.addressLine1 AS addressLine1,d.addressLine2 AS addressLine2,
d.city AS city,d.state AS state,st.stateName as stateName,d.postalCode AS postalCode,d.email AS email,a.lastUpdatedDate AS lastUpdatedDate,ifnull(ur.roleId, 0) AS roleId,
ifnull(ur.roleName,'') AS roleName,ur.roleDescription AS roleDescription,IFNULL(ui.id,0) AS imageId,ui.name AS imageName,ui.path AS imagePath,ui.thumbNail AS thumbNail,
p.bankName AS bankName,p.bankAccountNumber AS bankAccountNumber,p.bankBranchName AS bankBranchName,cs.castName AS castName,c.custImageId,cstUi.name custImageName,
cstUi.path custImagePath,cstUi.thumbNail as custThumbNail,ifnull(scs.subCastName, '') AS subCastName,a.staffNumber,p.aadharNumber,p.passportNumber,p.ifscCode,p.isDocsUploaded,
td.addressLine1 AS taddressLine1,td.city AS tcity,td.state AS tstate,td.postalCode AS tpostalCode,s.outSideSchoolDuty,sif.startTime,sif.shiftName,sif.endTime,ifnull(sif.id, 0) AS shiftId,s.schoolMess,p.salaryPaymentMode,p.staffLocation,s.staffGrade,
a.personId AS personId,csUi.path AS principalDigitalSignaturePath,p.fatherName as fatherName,p.anotherMobileNumber as fatherContactNumber
from staff s left join Account a ON s.accountId = a.id
left join Person p ON a.personId = p.id
left join Address d ON a.paddressId = d.id
left join Address td ON a.taddressId = td.id
left join vw_userRoles ur ON a.id = ur.accountId
left join UserImage ui ON ui.id = a.imageId
left join customer c ON c.id = s.custId
left join castSettings cs ON cs.id = p.castId
left join subCastSettings scs ON scs.id = p.subCastId
left join staffStatutory ss ON s.id = ss.staffId
left join salary sa ON s.id = sa.staffId
left join academicYear ay ON s.academicYearId = ay.id
left join commonType ct on p.religionId=ct.id
left join motherTongue mot on p.motherToungId=mot.id
left join UserImage cstUi ON (cstUi.id = c.custImageId)
left join State st on st.stateCode=d.state
left join schoolShiftInfo sif on sif.id=s.shiftId
left join UserImage csUi ON (csUi.id = c.principalDigitalSignatureId); 

CREATE OR REPLACE VIEW vw_staffLeaveDetails AS
select
	a.Id as accountId,
	a.custId,
	a.username,
	s.id as staffId,
	s.supervisorId,
	l.academicYearId,
	s.qualification1,
	s.qualification2,
	s.status,
	p.firstName,
	p.lastName,            
	p.summary,
	p.dateOfJoining,
	l.id as leavesId,
	l.description,
	l.startDate,
	l.endDate,
	l.leaveStatus,
	IFNULL(l.leavesCount, 0) as leavesCount,
	l.leaveType,
	l.startTime,
	l.endTime,
	r.Name as roleName,
	l.supervisorId leaveSupervisorId,
	r.description as roleDescription,
	r.id as roleId,
	ifnull(lm.id, 0) AS leaveManagementId,
	lm.permanentOrContract AS permanentOrContract,
	ifnull(lm.earnedLeaves, 0) AS userEarnedLeaves,
	ifnull(lm.sickLeaves, 0) AS userSickLeaves,
	ifnull(lm.casualLeaves, 0) AS userCasualLeaves
   
FROM
	leaves l
	left join Account a ON (a.id = l.accountId)
	left join staff s on (s.accountId=a.Id)    
	LEFT JOIN Person p on (a.personId=p.id)
	LEFT JOIN UserRoles ur on (ur.userId=a.id)
	left join Role r ON (r.id = ur.roleId)
	left join leaveManagement lm ON (((lm.roleId = ur.roleId) and (l.academicYearId = lm.academicYearId) and (s.staffType = lm.permanentOrContract)))
where leaveType is not null and s.id is not null;	
	
/*change the vw_studentDetails by prasad 28-09-2012 */
create or replace view vw_studentDetails as
SELECT a.id as accountId,a.custId,a.username,sp.parentAccountId AS parentId,a.version,a.sharePrivacy,a.userOnlineNow,a.accountExpired,a.admissionNumber,a.barcode,s.id as studentId,s.academicYearId,
s.description,s.createdDate,s.lastAccessDate,ifnull(s.hostelMode,'D') as hostelMode,s.status,ifnull(s.transportMode, 'O') as transportMode,s.classNameClassId,s.rollNumber,s.joinedThroughAdmissions,
s.categoryId,IFNULL(p.height,0) as height,IFNULL(p.weight,0) as weight,p.teeth,p.visionL,p.visionR,p.oralHygiene,trim(p.firstName) AS firstName,trim(p.lastName) AS lastName,p.middleName,p.fatherName,p.motherName,p.mothersMaidenName,p.dateOfBirth,
UCASE(IFNULL(p.bloodGroup,'')) as bloodGroup,p.mobileNumber,p.phoneNumber,p.parentEmail,p.summary,p.dateOfJoining,IFNULL(p.castId, 0) as castId,IFNULL(p.subCastId, 0) as subCastId,
IFNULL(p.religionId, 0) as religionId,IFNULL(p.nationality,'') as nationality,p.identification1,p.identification2,p.gender,IFNULL(p.motherToungId, 0) as motherToungId,p.rationCardNumber,IFNULL(p.id, 0) as personId,IFNULL(d.id, 0) as paddressId,
p.communityNumber,IF(p.phId ='Y','Yes','No') AS phId,d.addressLine1,d.addressLine2,d.city,d.districtName,d.state,IFNULL(d.postalCode,'') as postalCode,s.lastUpdatedDate,c.className,c.section,
CAST(CONCAT(IF(c.className IS NULL, '', c.className),IF(c.section IS NULL || c.section = '','',CONCAT(' - ', c.section))) AS CHAR(60)) as classNameAndSection,
IFNULL(c.mediumId, 0) as mediumId,c.filledSeats,c.sectionCapacity,cl.higherStandard,ur.roleName,ur.roleDescription,ifNULL(ur.roleId,3) as roleId,IFNULL(ui.id,0) as imageId,
ui.name as imageName,ui.path as imagePath,ui.thumbNail,IFNULL(c.id, 0) as classSectionId,cs.castName,IFNULL(scs.subCastName, '') as subCastName,p.FatherOccupation as fatherOccupation,
p.motherOccupation,IFNULL(p.annualIncome,0) as annualIncome,p.familyDoctor,p.sslcNumber,p.tmrNumber,p.classJoined,p.prefferedHospital,p.placeOfBirth,p.lastSchool,p.studentEmail,p.studentMobile,
CONCAT(d.addressLine1,' ',d.addressLine2) AS streetName,IFNULL(st.stateName, '') as stateName,cst.revenueDistrict,cst.custEmail,cst.contactNumber,cst.educationalDistrict,cst.organization,cst.customerShortName,
cst.custImageId,cst.schoolCode,cst.affiliationNumber,acy.academicYear,ct.skillTypeName as religion,cst.boardOfEducation,cst.recognisedBy,if(cst.transportModuleStatus ='Y','TRUE','FALSE') AS transportModuleStatus,
if((s.boardingPointId > 0 && s.TransportMode='T'),'TRUE','FALSE') AS studentTransportStatus,IFNULL(s.bedId, 0) as bedId,ct.skillTypeName as medium,
IFNULL(s.boardingPointId, 0) as boardingPointId,IFNULL(v.id, 0) as vehicleId,IFNULL(s.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,cstUi.name as custImageName,
cstUi.path custImagePath,cstUi.thumbNail as custThumbNail,va.name as vehicleName,
CONCAT(IFNULL(concat(custAddr.addressLine1, ', '), ''),IFNULL(concat(custAddr.streetName, ', '), ''),CHAR(10),IFNULL(concat(custAddr.city, ' - '), ''),IFNULL(concat(custAddr.postalCode, ', '), ''),IFNULL(concat('Ph - ', cst.contactNumber, '.'),'')) as customerFullAddress,
CONCAT(IFNULL(concat(d.streetName, ', '), ''),IFNULL(concat(d.city, ' - '), ''),CHAR(10),IFNULL(concat(d.postalCode, '. '), ''),IFNULL(concat(' Ph - ', p.mobileNumber, '.'), '')) as parentFullAddress,
IFNULL(s.registerNumber, '') as registerNumber,IFNULL(ifnull(concat(va.name, '-', br.boardingPointName),concat(v.vehicleNumber,'-',br.boardingPointName)),'') AS VehicleBoardingPointname,v.vehicleNumber,IFNULL(br.boardingPointFeeAmount,0) boardingPointFeeAmount,
IFNULL(r.routeName, '') as routeName,IFNULL(r.id, 0) as routeId,cl.sortingOrder,
(case when (s.TransportMode = 'O') then 'Own' when (s.TransportMode = 'P') then 'Private' when (s.TransportMode = 'T') then 'School Transport' else '' end) AS 'TransportName',
ctype.name as mediumOfStudy,mtype.name as motherToung,p.relievingDate,p.scholarShipInfo,p.scholarShipAmount,p.tcAppliedDate,p.tcIssuedDate,p.studentUniqueNumber,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,br.boardingPointName,
sc.categoryName,p.aadharNumber,s.outSideSchoolDuty,s.committedFee,s.goals,s.strengths,s.interestsAndHobbies,s.responsibilities,s.achievements,IFNULL(p.height2,0) as height2,IFNULL(p.weight2,0) as weight2,
acy.nextAcademicStartDate as schoolReOpenDate,s.remarks,s.promoteToClass,p.secondaryMobileNumber,p.addressType,p.anotherMobileNumber,p.anotherSecondaryMobileNumber,p.anotherParentEmail,s.schoolMess,a.enrollmentCode,s.ptaStatus,IF(s.rteStatus ='Y','Yes','No') AS rteStatus,IF(s.bplStatus ='Y','Yes','No') AS bplStatus,
s.bplNumber,p.physicallyHandicappedDesc,cst.barcodeStatus AS barcodeStatus,p.fatherAadharNumber,p.motherAadharNumber,upi.path AS principalDigitalSignaturePath,
syt.syllabusTypeName AS syllabusTypeName,s.feeRefundStatus,IFNULL(rht.type, '') AS houseType,p.studentBankId as studentBankId,p.stsNumber

FROM student s JOIN Account a ON (s.accountId = a.id)

LEFT JOIN Person p ON (a.personId = p.id)
LEFT JOIN Address d ON (a.paddressId = d.id)
LEFT JOIN State st ON (st.stateCode = d.state)
LEFT JOIN studyClass c ON (c.id = s.classSectionId)
LEFT JOIN syllabusType syt on (c.syllabusTypeId=syt.id)
LEFT JOIN academicYear acy ON (acy.id = s.academicYearId)
LEFT JOIN schoolCategory sc ON (sc.id = s.categoryId)
LEFT JOIN vw_userRoles ur ON (ur.accountId = s.accountId)
LEFT JOIN UserImage ui ON (ui.id = s.imageId)
LEFT JOIN castSettings cs ON (cs.id = p.castId)
LEFT JOIN subCastSettings scs ON (scs.id = p.subCastId)
LEFT JOIN class cl ON (cl.id = s.classNameClassId)
JOIN customer cst ON (a.custId = cst.id)
LEFT JOIN Address custAddr ON (custAddr.id = cst.addressId)
LEFT JOIN UserImage cstUi ON (cstUi.id = cst.custImageId)
LEFT JOIN commonType ct ON (ct.id = p.religionId and ct.type = 'RELIGION')
LEFT JOIN medium ctype ON (ctype.id = c.mediumId)
LEFT JOIN motherTongue mtype ON (mtype.id = p.motherToungId)
LEFT JOIN vehiclesAcademicDetails va on (va.id=s.vehicleAcademicDetailsId)
LEFT JOIN vehicles v ON (v.id = va.vehicleId)
LEFT JOIN routeBoardingPoints br ON (br.id = s.boardingPointId)
left join route r ON (br.routeId = r.id)
left join UserImage upi ON (upi.id = cst.principalDigitalSignatureId)
LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id)
LEFT JOIN Ref_HouseType rht on (rht.id =d.houseTypeId);

 create
 or replace view vw_studentClassDetails as
 SELECT  s.id as studId,s.classSectionId,s.status,s.custId,s.rollNumber,s.academicYearId,ayc.academicYear,s.accountId,IFNULL(s.bedId,0) as bedId,IFNULL(s.categoryId,0) as categoryId,s.transportMode,
 s.classNameClassId as classId,IFNULL(s.boardingPointId,0) as boardingPointId,s.joinedThroughAdmissions,TRIM(p.firstName) as firstName,TRIM(p.lastName) as lastName,IFNULL(p.castId,0) as castId,
 IFNULL(p.subCastId,0) as subCastId,IFNULL(p.religionId,0) as religionId ,p.gender,p.rationCardNumber,p.communityNumber,p.FatherOccupation,c.className,c.section,cl.higherStandard,p.dateOfBirth,
 CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,IFNULL(cs.castName,'') as castName,IFNULL(a.admissionNumber,'') as admissionNumber,
 s.registerNumber,a.accountExpired,s.description as studDiscontinueDesc,p.dateOfJoining,p.fatherName,p.motherName,IFNULL(p.height,0) as height,IFNULL(p.weight,0) as weight,p.teeth,p.visionL,p.visionR,
 p.oralHygiene,p.motherOccupation,p.parentEmail,p.annualIncome,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.nationality,d.streetName,d.postalCode,IFNULL(p.motherToungId,0) as motherToungId,p.identification1,
 p.identification2,d.city,cl.sortingOrder,d.id as addressId,d.addressLine1,s.roomId,IFNULL(s.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,s.hostelMode,s.futureAcademicClassSecId,s.feePaidStatus,
 s.feeConfigured,s.description,s.committedFee,p.secondaryMobileNumber,p.anotherMobileNumber,p.anotherSecondaryMobileNumber,p.anotherParentEmail,p.addressType,IFNULL(ui.id,0) as imageId,s.classSectionId  as studyClassId,
 ui.name imageName,ui.path imagePath,ui.thumbNail,s.failurePromotableResons,CONCAT(IF(c.className IS NULL,'',c.className), IF(c.section IS NULL || c.section  = '','',CONCAT(' - ',c.section))) as classAndSection,
 sp.parentAccountId AS parentId,s.optionalSubjectId,ayc.startDate,ayc.endDate,s.createdDate,d.addressline2 AS addressLine2
FROM student s  
JOIN Account a on (s.accountId=a.id) 
LEFT JOIN Person p on (a.personId=p.id) 
LEFT JOIN Address d on (a.paddressId=d.id) 
LEFT JOIN  studyClass c on (c.id=s.classSectionId)  
LEFT JOIN class cl on (cl.id=s.classNameClassId) 
LEFT JOIN castSettings cs on (cs.id=p.castId) 
LEFT JOIN academicYear ayc on (s.academicYearId=ayc.id)
LEFT JOIN UserImage ui ON (ui.id = s.imageId)
LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id);


create or Replace view vw_studentLeaveDetails as
SELECT
	s.accountId,
	s.custId,
	a.username,
	sp.parentAccountId AS parentId,
	a.sharePrivacy,
	a.userOnlineNow,
	s.id as studentId,
	s.academicYearId,
	s.status,
	s.classSectionId,
	s.rollNumber,
	p.firstName,
	p.lastName,
	p.middleName,
	p.fatherName,
	p.motherName,
	p.mothersMaidenName,
	p.dateOfBirth,
	p.bloodGroup,
	p.mobileNumber,
	p.phoneNumber,
	ad.addressLine1,
	ad.addressLine2,
	ad.city,
	ad.state,
	ad.postalCode,
	p.parentEmail,
	p.summary,
	p.dateOfJoining,
	sc.section,
	sc.className,
	s.hostelMode,
	r.Name as roleName,
	r.description as roleDescription,
	r.id as roleId,
	l.id leavesId,
	l.leaveStatus,
	l.leaveType,
	l.startTime,
	l.endTime,
	l.description,
	l.startDate,
	l.endDate,
	l.supervisorId leaveSupervisorId,
	IFNULL(l.leavesCount, 0) as leavesCount,
	ui.id imageId,
	ui.name imageName,
	ui.path imagePath,
	ui.thumbNail,
	s.classNameClassId as classId,
	l.halfDay,l.sessionType
FROM
	leaves l left join Account a on l.accountId=a.id
	left join student s on s.accountId=l.accountId and s.academicYearId=l.academicYearId   
	left join Person p on p.id=a.personId
	left join Address ad on ad.id=a.taddressId
	LEFT JOIN UserRoles ur on (ur.userId=a.id)
	left join Role r ON (r.id = ur.roleId)
	LEFT JOIN  UserImage ui ON (ui.id = s.imageId)
	left join studyClass sc on (sc.id = s.classNameClassId)
	LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id)
	where s.id is not null and l.leaveType is not null;



CREATE OR REPLACE VIEW vw_classExamDetails AS 
select ss.name AS name,ss.lastUpdatedDate AS lastUpdatedDate,ss.description AS description,ifnull(ss.subjectNumber, '') AS subjectNumber,es.id AS scheduleId,es.custId AS custId,es.examDate AS examDate,
es.startTime AS startTime,es.endTime AS endTime,es.classSubjectId AS classSubjectId,es.academicYearId AS academicYearId,sc.className AS className,sc.id AS classSectionId,sc.section AS section,es.version AS version,
et.id AS eid,et.examType AS examType,ifnull(et.minMarks, 0) AS minMarks,ifnull(et.maxMarks, 0) AS maxMarks,cs.id AS classId,st.id AS subTypeId,st.subTypeName AS subTypeName,st.schedule AS schedule,
ifnull(es.maxMarks, 0) AS scheduleMaxMarks,es.startDate AS startDate,es.endDate AS endDate,st.predefinedSubType AS predefinedSubType,cs.higherStandard AS higherStandard,ifnull(ss.sortingOrder, 0) AS sortingOrder,
ss.language AS language,ifnull(st.sortingOrder, 0) AS subTypeSortingOrder,ifnull(et.sortingOrder, 0) AS examTypeSortingOrder,es.syllabus AS Syllabus,ifnull(es.scheduled, 'N') AS scheduled 
from examSchedules es 
left join studyClass sc ON (sc.id = es.classSectionId)
left join class cs ON (sc.classNameClassId = cs.id)
left join studySubject ss ON (ss.id = es.classSubjectId)
left join examTypes et ON (et.id = es.examTypeId)
left join subType st ON (st.id = es.subTypeId);

create or replace view vw_studentSubjectWiseMarksDetails as
	SELECT IFNULL(s.id,0) as studId,IFNULL(sm.id,0) as marksId,IFNULL(ss.id,0) as subjectId,IFNULL(et.id,0) as examTypeId,ss.name as subjectName,et.examType,sm.present,sm.obtainedMarks,sm.moderationMarks,sum(sm.obtainedMarks+sm.moderationMarks) as totalSubjectMarksObtained,SUM(es.maxMarks) as subjectTotalMarks,s.custId,s.academicYearId,IFNULL(s.classSectionId,0) as classSectionId
	FROM student s  LEFT JOIN  studentMarks sm on (s.id=sm.studId) LEFT JOIN  examSchedules es on (es.id=sm.examScheduleId) LEFT JOIN examTypes et on (et.id=es.examTypeId) LEFT JOIN studySubject ss on (ss.id=es.classSubjectId) LEFT JOIN studyClass sc on (sc.id=s.classSectionId) group by et.id,ss.id,s.id;
	
create or replace view vw_studentMarksDetails as
	select
	ifnull(s.id, 0) AS studId,
	ifnull(sm.id, 0) AS marksId,
	ifnull(et.id, 0) AS examTypeId,
	s.custId AS custId,
	ss.id AS subjectId,
	ifnull(es.id, 0) AS scheduleId,
	s.rollNumber AS rollNumber,
	ifnull(s.classSectionId, 0) AS classSectionId,
	ifnull(st.id, 0) AS subTypeId,
	et.examType AS examType,
	ifnull(et.maxMarks, 0) AS maxMarks,
	ifnull(et.minMarks, 0) AS minMarks,
	s.academicYearId AS academicYearId,
	sm.obtainedMarks AS obtainedMarks,
	ss.name AS subjectName,
	es.startDate AS examScStartDate,
	es.examDate AS examDate,
	es.startTime AS startTime,
	es.endTime AS endTime,
	sm.lastUpdatedDate AS lastUpdatedDate,
	sm.present AS present,
	sc.className AS className,
	sc.classNameClassId AS classNameClassId,
	sc.section AS section,
	st.subTypeName AS subTypeName,
	ifnull(st.schedule, 'N') AS subTypeSchedule,
	ifnull(es.maxMarks, 0) AS subTypeMaxMarks,
	sm.moderationMarks AS moderationMarks,
	sum((sm.obtainedMarks + sm.moderationMarks)) AS totalSubjectMarksObtained,
	ifnull(ss.language, 'N') AS language,
	s.status AS status,
	ifnull(et.sortingOrder, 0) AS examTypeSortingOrder,
	(sm.obtainedMarks + sm.moderationMarks) AS obtainedSubTypeMarks,
	ifnull(ss.sortingOrder, 0) AS subjectSortingOrder,
	ifnull(st.sortingOrder, 0) AS subTypeSortingOrder,
	s.description AS studDiscontinueDesc,
	sum(es.maxMarks) AS subjectTotalMarks,
	ss.description AS subjectShortName,
	sm.examScheduleId AS examScheduleId,
	sm.rankPosition AS rankPosition
from
	 studentMarks sm
	join student s ON (s.id = sm.studId)
	join examSchedules es ON (es.id = sm.examScheduleId)
	join examTypes et ON (et.id = es.examTypeId)
	join studySubject ss ON (ss.id = es.classSubjectId)
	join subType st ON (st.id = es.subTypeId)
	join studyClass sc ON (sc.id = s.classSectionId) 
	group by sm.id,et.id , ss.id , s.id;

	
	
	
create or replace view vw_studentScoreCardMarksDetails as
	SELECT IFNULL(sm.studentId,0) as studentId,ss.id as subjectId,ss.name as subjectName,IFNULL(ss.sortingOrder,0) as subjectSortingOrder,
	sm.term1St1,sm.term1St2,sm.term1St3,sm.term1St4,sm.term1St5,sm.term1St6,sm.term1St7,sm.term1St8,sm.term1St9,sm.term1St10,
	sm.term1Total,sg1.gradeName as term1Grade,sg1.gradePoints as term1GradePoint,
	sm.term2St1,sm.term2St2,sm.term2St3,sm.term2St4,sm.term2St5,sm.term2St6,sm.term2St7,sm.term2St8,sm.term2St9,sm.term2St10,
	sm.term2Total,sg2.gradeName as term2Grade,sg2.gradePoints as term2GradePoint,
	sm.term3St1,sm.term3St2,sm.term3St3,sm.term3St4,sm.term3St5,sm.term3St6,sm.term3St7,sm.term3St8,sm.term3St9,sm.term3St10,
	sm.term3Total,sg3.gradeName as term3Grade,sg3.gradePoints as term3GradePoint,
	sm.term4St1,sm.term4St2,sm.term4St3,sm.term4St4,sm.term4St5,sm.term4St6,sm.term4St7,sm.term4St8,sm.term4St9,sm.term4St10,
	sm.term4Total,sg4.gradeName as term4Grade,sg4.gradePoints as term4GradePoint,
	sm.term5St1,sm.term5St2,sm.term5St3,sm.term5St4,sm.term5St5,sm.term5St6,sm.term5St7,sm.term5St8,sm.term5St9,sm.term5St10,
	sm.term5Total,sg5.gradeName as term5Grade,sg5.gradePoints as term5GradePoint,
	sm.term6St1,sm.term6St2,sm.term6St3,sm.term6St4,sm.term6St5,sm.term6St6,sm.term6St7,sm.term6St8,sm.term6St9,sm.term6St10,
	sm.term6Total,sg6.gradeName as term6Grade,sg6.gradePoints as term6GradePoint,
	sm.term7St1,sm.term7St2,sm.term7St3,sm.term7St4,sm.term7St5,sm.term7St6,sm.term7St7,sm.term7St8,sm.term7St9,sm.term7St10,
	sm.term7Total,sg7.gradeName as term7Grade,sg7.gradePoints as term7GradePoint,
	sm.term8St1,sm.term8St2,sm.term8St3,sm.term8St4,sm.term8St5,sm.term8St6,sm.term8St7,sm.term8St8,sm.term8St9,sm.term8St10,
	sm.term8Total,sg8.gradeName as term8Grade,sg8.gradePoints as term8GradePoint,
	sm.term9St1,sm.term9St2,sm.term9St3,sm.term9St4,sm.term9St5,sm.term9St6,sm.term9St7,sm.term9St8,sm.term9St9,sm.term9St10,
	sm.term9Total,sg9.gradeName as term9Grade,sg9.gradePoints as term9GradePoint,
	sm.term10St1,sm.term10St2,sm.term10St3,sm.term10St4,sm.term10St5,sm.term10St6,sm.term10St7,sm.term10St8,sm.term10St9,sm.term10St10,
	sm.term10Total,sg10.gradeName as term10Grade,sg10.gradePoints as term10GradePoint,	
	sm.term11St1,sm.term11St2,sm.term11St3,sm.term11St4,sm.term11St5,sm.term11St6,sm.term11St7,sm.term11St8,sm.term11St9,sm.term11St10,
	sm.term11Total,sg11.gradeName as term11Grade,sg11.gradePoints as term11GradePoint,	
	sm.term12St1,sm.term12St2,sm.term12St3,sm.term12St4,sm.term12St5,sm.term12St6,sm.term12St7,sm.term12St8,sm.term12St9,sm.term12St10,
	sm.term12Total,sg12.gradeName as term12Grade,sg12.gradePoints as term12GradePoint,	
	sm.term13St1,sm.term13St2,sm.term13St3,sm.term13St4,sm.term13St5,sm.term13St6,sm.term13St7,sm.term13St8,sm.term13St9,sm.term13St10,
	sm.term13Total,sg13.gradeName as term13Grade,sg13.gradePoints as term13GradePoint,	
	sm.term14St1,sm.term14St2,sm.term14St3,sm.term14St4,sm.term14St5,sm.term14St6,sm.term14St7,sm.term14St8,sm.term14St9,sm.term14St10,
	sm.term14Total,sg10.gradeName as term14Grade,sg14.gradePoints as term14GradePoint,	
	sm.term15St1,sm.term15St2,sm.term15St3,sm.term15St4,sm.term15St5,sm.term15St6,sm.term15St7,sm.term15St8,sm.term15St9,sm.term15St10,
	sm.term15Total,sg15.gradeName as term15Grade,sg15.gradePoints as term15GradePoint,	
	ss.academicYearId
	FROM studentsScoreCardMarks sm JOIN  studySubject ss on (ss.id=sm.subjectId) LEFT JOIN schoolGrades sg1 on (sm.term1Total >= sg1.minMarks and sm.term1Total <= sg1.maxMarks and sg1.academicYearId = ss.academicYearId) 
	LEFT JOIN schoolGrades sg2 on (sm.term2Total >= sg2.minMarks and sm.term2Total <= sg2.maxMarks and sg2.academicYearId = ss.academicYearId) 
	LEFT JOIN schoolGrades sg3 on (sm.term3Total >= sg3.minMarks and sm.term3Total <= sg3.maxMarks and sg3.academicYearId = ss.academicYearId) 
	LEFT JOIN schoolGrades sg4 on (sm.term4Total >= sg4.minMarks and sm.term4Total <= sg4.maxMarks and sg4.academicYearId = ss.academicYearId) 
	LEFT JOIN schoolGrades sg5 on (sm.term5Total >= sg5.minMarks and sm.term5Total <= sg5.maxMarks and sg5.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg6 on (sm.term6Total >= sg6.minMarks and sm.term6Total <= sg6.maxMarks and sg6.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg7 on (sm.term7Total >= sg7.minMarks and sm.term7Total <= sg7.maxMarks and sg7.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg8 on (sm.term8Total >= sg8.minMarks and sm.term8Total <= sg8.maxMarks and sg8.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg9 on (sm.term9Total >= sg9.minMarks and sm.term9Total <= sg9.maxMarks and sg9.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg10 on (sm.term10Total >= sg10.minMarks and sm.term10Total <= sg10.maxMarks and sg10.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg11 on (sm.term11Total >= sg11.minMarks and sm.term11Total <= sg11.maxMarks and sg11.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg12 on (sm.term12Total >= sg12.minMarks and sm.term12Total <= sg10.maxMarks and sg12.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg13 on (sm.term13Total >= sg13.minMarks and sm.term13Total <= sg13.maxMarks and sg13.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg14 on (sm.term14Total >= sg14.minMarks and sm.term10Total <= sg14.maxMarks and sg14.academicYearId = ss.academicYearId)
	LEFT JOIN schoolGrades sg15 on (sm.term15Total >= sg15.minMarks and sm.term15Total <= sg15.maxMarks and sg15.academicYearId = ss.academicYearId);
	
create or replace view vw_studentExamMarks as
    SELECT sm.studId,IFNULL(et.id,0) examTypeId,IFNULL(es.id,0) as scheduleId,sm.present,sm.obtainedMarks,sm.moderationMarks,IFNULL(et.minMarks,0) as minMarks,IFNULL(ss.language,'N') as language,ss.id as subjectId,
     ss.name as subjectName,et.examType as examType,es.subTypeId,es.classSectionId,sm.rankPosition,IFNULL(es.maxMarks,0) as maxMarks,sbt.subTypeName,es.custId,es.academicYearId,sm.subjectPosition,es.examDate
    FROM studentMarks sm LEFT JOIN  examSchedules es on (es.id=sm.examScheduleId) LEFT JOIN examTypes et on (et.id=es.examTypeId) LEFT JOIN studySubject ss on (ss.id=es.classSubjectId)
    LEFT JOIN subType sbt on(sbt.id=es.subTypeId);
    
    
CREATE OR REPLACE VIEW vw_examWiseTopperDetails as 
SELECT es.id as scheduleId,es.classSectionId,MAX(sm.obtainedMarks+sm.moderationMarks) as subjectTopperMarks,es.custId,es.academicYearId
FROM studentMarks sm JOIN examSchedules es on (sm.examScheduleId=es.id) group by es.examTypeId,es.classSectionId,es.classSubjectId,es.subTypeId ;


CREATE OR REPLACE VIEW vw_examScheduleDetailsWithTopperMarks as
	SELECT 
	ss.name,
	et.examType,
	st.subTypeName,
	es.id as scheduleId,
	IFNULL(es.maxMarks,0) as scheduleMaxMarks,
	es.classSectionId,
	es.academicYearId,
	IFNULL(ss.sortingOrder,0) as sortingOrder,
	IFNULL(et.sortingOrder,0) as examTypeSortingOrder,
	IFNULL(st.sortingOrder,0) as subTypeSortingOrder,
	MAX(IFNULL(sm.obtainedMarks,0)+IFNULL(sm.moderationMarks,0)) as subjectTopperMarks,
	sm.rankPosition,subjectPosition,sm.studId as studentId,sc.custId
FROM studentMarks sm 
LEFT JOIN examSchedules es on (sm.examScheduleId=es.id) 
LEFT JOIN studyClass sc on (sc.id=es.classSectionId and sc.custId=es.custId and sc.academicYearId=es.academicYearId) 
LEFT JOIN class cs on (sc.classNameClassId=cs.id) 
LEFT JOIN studySubject ss on (ss.id=es.classSubjectId and cs.custId=ss.custId and cs.academicYearId=ss.academicYearId) 
LEFT JOIN examTypes et on(et.id=es.examTypeId and ss.custId=et.custId and ss.academicYearId=et.academicYearId) 
LEFT JOIN subType st on(st.id=es.subTypeId and ss.custId=et.custId and ss.academicYearId=et.academicYearId) group by es.examTypeId,es.classSectionId,es.classSubjectId,es.subTypeId,sm.studId; 

 
CREATE OR REPLACE VIEW vw_studentsMarksAndPerformanceDetails as 
SELECT sm.studId,
sm.obtainedMarks,
sm.moderationMarks,
MAX(IFNULL(sm.obtainedMarks+sm.moderationMarks ,0)) as subjectTopperMarks,
IFNULL(es.classSectionId,0) as classSectionId,
IFNULL(sm.examScheduleId,0) as examScheduleId,
sm.id as marksId,
IFNULL(es.custId,0) as custId,
IFNULL(es.academicYearId,0) as academicYearId,
IFNULL(sm.subjectPosition,0) as subjectPosition,
sm.present,sm.rankPosition
FROM studentMarks sm JOIN examSchedules es on (sm.examScheduleId=es.id) group by es.examTypeId,es.classSectionId,es.classSubjectId,es.subTypeId,sm.studId;

 
create or replace view vw_studentAttendance as
SELECT
	at.accountId,
	at.custId,
	s.id as studentId,
	at.academicYearId,
	s.classNameClassId as classId,
	s.classSectionId,
	a.username,
	cl.className,
	cl.section,
	sp.parentAccountId AS parentId,
	s.status,
	s.rollNumber,
	p.firstName,
	p.lastName,
	p.middleName,
	p.fatherName,
	p.dateOfBirth,
	p.mobileNumber,
	p.phoneNumber,
	p.parentEmail,
	IFNULL(at.id, 0) as attendanceId,
	at.attendanceDate,
	IFNULL(at.category, 'N') as category,
	IFNULL(at.leaveRequest, 'N') as leaveRequest,
	at.month,
	IFNULL(at.monthNum, 0) as monthNum,
	IFNULL(at.present, 'Y') as present,
	IFNULL(at.year, 0) as year
FROM
attendance at left join student s ON (at.accountId = s.accountId and at.academicYearId=s.academicYearId)
	left join Account a on at.accountId=a.id
	left join Person p on p.id=a.personId
	left join studyClass cl on cl.id=s.classSectionId
	LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id);

create 
or replace view vw_onlineApplicationDetails as
SELECT o.id as applicationId,o.custId, o.parentEmail,o.fatherName,o.studentEmail,o.studentMobile,o.hostelMode,ui.id as imageId,ui.path as imagePath,ui.name as imageName,o.collectedDocuments,o.bloodGroup,o.version, o.motherName,o.occupation,o.firstName,o.lastName,o.phoneNumber,o.transferCertificateNo,IFNULL(o.annualIncome,0)as annualIncome,o.designation,o.motherQualification,o.fatherQualification,o.transportMode,o.placeOfBirth,
o.mobileNumber,o.status,adr.streetName as tAddrstreetName,IFNULL(o.subCastId,0) as subCastId,IFNULL(o.castId,0) as castId,adr.city as tAddrCity,adr.state as tAddrState,o.academicYearId, o.createdDate, o.lastAccessDate, o.status as applicationStatus,c.className,o.applicationNumber,o.dateOfBirth,o.gender,adr.postalCode as tAddrPostalCode, o.lastUpdatedDate,o.lastClassAttended,o.lastSchool,o.entranceMarks,IFNULL(o.classId,0) as classId,o.nationality,ctReli.skillTypeName as religion, 
a.admissionEndDate,a.testConducted,a.applicationFee,a.entranceDate,a.status as admissionStatus,ac.status as academicYearStatus,ac.academicYear as academicYearStr,o.present,o.rejectApplicationDesc,a.entranceExamTotalMarks,
ad.streetName as caddrStreetName,ad.city as caddrCity,st.stateCode as caddrState,ad.postalCode as caddrPostalCode,IFNULL(o.religionId,0) as religionId,cs.castName,scs.subCastName,o.boardingPointId,o.vehicleAcademicDetailsId,
r.id as routeId,IFNULL(o.categoryId,0) as categoryId,sc.categoryName,ctReli.skillTypeName,o.aadharCardNumber,a.registrationFee,a.prospectiveFee,o.receiptNumber,a.entranceExamPassMarks,IFNULL(o.committedFee,0) as committedFee,st.stateName as tAddrStateName,
o.sslcNumber,o.tmrNumber,o.communityNumber,o.phId,o.studentUniqueNumber,IFNULL(o.visionL,'') as visionL,IFNULL(o.visionR,'') as visionR,o.scholarShipInfo,
o.height,o.weight,o.goals,o.strengths,o.interestsAndHobbies,o.responsibilities,o.achievements,o.identification1,o.identification2,o.motherToungId,o.dateOfJoining,o.oralHygiene,o.rationCardNumber,o.teeth,o.prefferedHospital,o.familyDoctor,o.registerNumber,o.rollNumber as rollNumber,mtype.name as motherToung,o.physicallyHandicappedDesc,o.isPHDocsUploaded,o.bplStatus,o.rteStatus,
o.fatherAadharNumber,o.motherAadharNumber,a.atuoGenerationAdmissionNumberStatus as admissionNumberAutoGenerationStatus
FROM onlineApplicationDetails o LEFT JOIN admissionSettings a on (o.custId=a.custId and o.academicYearId=a.academicYearId)
LEFT JOIN academicYear ac on(a.academicYearId=ac.id)
LEFT JOIN class c on (o.classId = c.id and o.academicYearId = c.academicYearId)
LEFT JOIN Address ad on (ad.id=o.paddressId)
LEFT JOIN commonType ctReli on (ctReli.type='RELIGION' and ctReli.id=o.religionId)
LEFT JOIN UserImage ui on (ui.id=o.profileImage)
LEFT JOIN castSettings cs on(cs.id=o.castId) 
LEFT JOIN subCastSettings scs on (scs.id=o.subCastId) 
LEFT JOIN schoolCategory sc on(sc.id=o.categoryId)
LEFT JOIN vehiclesAcademicDetails va on (va.id=o.vehicleAcademicDetailsId) 
LEFT JOIN vehicles v ON (v.id = va.vehicleId)
LEFT JOIN routeBoardingPoints br ON (br.id = o.boardingPointId) 
left join route r ON (br.routeId = r.id)  
LEFT JOIN Address adr on (adr.id=o.taddressId)
LEFT JOIN State st on (st.id = ad.stateId)
LEFT JOIN motherTongue mtype ON (mtype.id = o.motherToungId);



-- School Fee---
create
or replace view vw_classFeeTypes as
select c.id as classId, c.custId, c.academicYearId, c.className, f.id as feeTypeId, f.feeType,f.feeSettingId from  class c , feeType f where c.custId=f.custId and c.academicYearId=f.academicYearId ;

create
or replace view vw_classFeeDetails as
select f.id AS feeId,f.custId AS custId,ifnull(t.id,0) AS schoolTermId,f.academicYearId AS academicYearId,f.classId AS classId,f.feeTypeId AS feeTypeId,c.id AS categoryId,
t.termName AS termName,t.toDate AS toDate,t.toMonthName AS toMonthName,t.fromDate AS fromDate,t.applToNewStuds AS applToNewStuds,t.fromMonthName AS fromMonthName,
t.dueDate AS dueDate,t.dueDate1 AS dueDate1,t.dueDate2 AS dueDate2,ft.feeType AS feeType,f.feeAmount AS feeAmount,c.categoryName AS categoryName,s.id AS feeSettingId,s.settingName AS settingName,
s.settingType AS settingType,s.status AS status,a.transportFeeByBoardingPoint AS transportFeeByBoardingPoint,f.routeBoardingPointId AS routeBoardingPointId,sc.className AS className,
sc.section AS section,sc.id AS classSectionId,ft.committedFeeStatus,ft.priorityPosition 
from schoolTerms t left join Fee f on (f.schoolTermId = t.id and t.custId = f.custId and t.academicYearId = f.academicYearId and f.feeAmount <> '0') 
left join feeType ft on (f.feeTypeId = ft.id and ft.custId = f.custId) 
left join schoolCategory c on (f.categoryId = c.id and f.custId = c.custId) 
left join schoolFeeSetting s on (s.id = t.feeSettingId) 
left join academicYear a on (a.id = f.academicYearId and a.custId = f.custId) 
left join studyClass sc on (sc.custId = f.custId and sc.academicYearId = f.academicYearId and f.classId = sc.classNameClassId);


CREATE OR REPLACE VIEW vw_studentClassFees AS 
select cf.feeId AS feeId,ifnull(cf.feeAmount, 0) AS feeAmount,cf.custId AS custId,cf.feeTypeId AS feeTypeId,ifnull(cf.schoolTermId, 0) AS schoolTermId,cf.classId AS classId,
cf.academicYearId AS academicYearId,if((((s.transportMode = 'O') and (cf.settingName = 'Transport Fee')) or ((s.transportMode = 'p')
and (cf.settingName = 'Transport Fee')) or ((s.hostelMode = 'D') and (cf.settingName = 'Hostel Fee'))), 'N',cf.feeType) AS feeType,cf.fromDate AS fromdate,
cf.toDate AS toDate,cf.feeSettingId AS feeSettingId,cf.settingName AS settingName,cf.settingType AS settingType,cf.applToNewStuds AS applToNewStuds,
cf.fromMonthName AS fromMonthName,cf.toMonthName AS toMonthName,cf.termName AS termName,cf.dueDate AS dueDate,cf.categoryId AS categoryId,cf.categoryName AS categoryName,
cf.routeBoardingPointId AS routeBoardingPointId,s.id AS studentId,s.rollNumber AS rollNumber,s.accountId AS accountId,s.status AS status,s.description AS description,
s.transportMode AS transportMode,s.joinedThroughAdmissions AS joinedThroughAdmissions,s.hostelMode AS hostelMode,s.boardingPointId AS boardingPointId,
cf.className AS className,cf.section AS section,cf.classSectionId AS classSectionId,s.vehicleAcademicDetailsId AS vehicleAcademicDetailsId,s.feeConfigured,s.feePaidStatus,
sfc.concessionAmount
FROM vw_classFeeDetails cf JOIN student s ON 
(cf.custId = s.custId and cf.academicYearId = s.academicYearId and cf.categoryId = s.categoryId and 
IF(s.joinedThroughAdmissions = 'N',cf.applToNewStuds = 'N',true) AND 
    IF(s.joinedThroughAdmissions = 'N',cf.applToNewStuds = 'N',true) AND
        (IF(s.TransportMode='T',cf.RouteBoardingPointId=s.BoardingpointId OR s.classSectionId=cf.classSectionId,
                cf.feeSettingId<>3 AND s.classSectionId=cf.classSectionId )) AND
        (IF(s.HostelMode='H',s.classSectionId=cf.classSectionId,cf.feeSettingId<>4 AND IF(s.TransportMode<>'T',s.classSectionId=cf.classSectionId,TRUE))
        ))
       LEFT JOIN studentFeeConcession sfc ON (sfc.studentId=s.id and sfc.feeId=cf.feeId) where feeSettingId !=3;



        
/*        
CREATE OR REPLACE VIEW vw_studentClassFees AS 
select f.id AS feeId,ifnull(f.feeAmount, 0) AS feeAmount,f.custId AS custId,f.feeTypeId AS feeTypeId,ifnull(t.id, 0) AS schoolTermId,f.classId AS classId,f.academicYearId AS academicYearId,
if((((s.transportMode = 'O') and (sf.settingName = 'Transport Fee')) or ((s.transportMode = 'p') and (sf.settingName = 'Transport Fee')) or ((s.hostelMode = 'D') and (sf.settingName = 'Hostel Fee'))), 'N',ft.feeType) AS feeType,
t.fromDate AS fromdate,t.toDate AS toDate,sf.id AS feeSettingId,sf.settingName AS settingName,sf.settingType AS settingType,t.applToNewStuds AS applToNewStuds,t.fromMonthName AS fromMonthName,
t.toMonthName AS toMonthName,t.termName AS termName,t.dueDate AS dueDate,c.id AS categoryId,c.categoryName AS categoryName,f.routeBoardingPointId AS routeBoardingPointId,s.id AS studentId,s.rollNumber AS rollNumber,
s.accountId AS accountId,s.status AS status,s.description AS description,s.transportMode AS transportMode,s.joinedThroughAdmissions AS joinedThroughAdmissions,s.hostelMode AS hostelMode,s.boardingPointId AS boardingPointId,
sc.className AS className,sc.section AS section,sc.id AS classSectionId,s.vehicleAcademicDetailsId AS vehicleAcademicDetailsId
from schoolTerms t 
left join Fee f ON (f.schoolTermId = t.id and t.custId = f.custId and t.academicYearId = f.academicYearId and f.feeAmount <> '0')
left join feeType ft ON (f.feeTypeId = ft.id and ft.custId = f.custId)
left join schoolCategory c ON (f.categoryId = c.id and f.custId = c.custId)
left join schoolFeeSetting sf ON (sf.id = t.feeSettingId)
left join academicYear a ON (a.id = f.academicYearId and a.custId = f.custId)
left join studyClass sc ON (sc.custId = f.custId and sc.academicYearId = f.academicYearId and f.classId = sc.classNameClassId)
join student s ON (if((sf.settingName = 'Transport Fee'), if((a.transportFeeByBoardingPoint = 'Y'), ((s.boardingPointId = f.routeBoardingPointId) and (f.custId = s.custId) and (f.academicYearId = s.academicYearId) 
and (s.transportMode = 'T') and (c.categoryName = 'General')), ((f.classId = s.classNameClassId) and (f.custId = s.custId) and (f.academicYearId = s.academicYearId) and (s.boardingPointId <> 0) 
and (c.id = s.categoryId))), ((f.classId = s.classNameClassId) and (f.custId = s.custId) and (f.academicYearId = s.academicYearId) and if((s.joinedThroughAdmissions = 'N'), ((c.id = s.categoryId) 
and (f.custId = s.custId) and (f.academicYearId = s.academicYearId) and (t.applToNewStuds = 'N')), (c.id = s.categoryId)))) and (s.classSectionId = sc.id) and (s.academicYearId = f.academicYearId));

*/
        
        
create
or replace view vw_onlineApplicationStudentClassFees as
select f.id as feeId, f.custId, t.id as schoolTermId, f.academicYearId, f.classId, f.feeTypeId,IFNULL(c.id,0) as categoryId,t.termName, t.toDate, t.toMonthName,t.fromDate,t.applToNewStuds, 
t.fromMonthName, t.dueDate,ft.feeType, f.feeAmount,c.categoryName,s.id as feeSettingId,s.settingName,s.settingType,s.status from Fee f left join schoolTerms t on (f.schoolTermId = t.id and f.feeAmount <> '0')left join feeType ft on f.feeTypeId=ft.id 
left join schoolCategory c on (f.categoryId = c.id and f.custId = c.custId) left join schoolFeeSetting s on s.id=t.feeSettingId;

 
-- CVS 30-Dec-2011 
CREATE OR REPLACE VIEW vw_allUsers AS
select a.id as accountId,IFNULL(sp.parentAccountId,0) as parentId,a.username,a.password,a.accountEnabled,ifnull(a.bioMetricId,0) as bioMetricId,p.gender,a.barcode,
	ifnull(a.personId,0) as personId,ifnull(a.paddressId,0) as addressId,d.addressLine1,d.streetName,d.state as stateCode,
	IFNULL(d.stateId,0) as stateId,d.postalCode,ifnull(a.custId,0) as custId,a.lastAccessDate,a.lastUpdatedDate, ifnull(ur.roleId,0) as roleId,
	r.name AS roleName,r.description AS roleDescription,p.firstName,p.lastName,p.parentEmail,p.studentEmail,p.mobileNumber, p.fatherName,p.dateOfBirth, 
	p.studentMobile, p.bloodGroup, p.designation as staffDesignation, d.email as staffEmail, d.city, ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail,a.accountExpired,
	IFNULL(a.admissionNumber,0) as admissionNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName
from UserRoles ur 
	LEFT JOIN Account a  on (ur.userId=a.id)
	LEFT JOIN Person p  on (a.personId=p.id)
	LEFT JOIN  Address d on (a.paddressId=d.id)
	LEFT JOIN Role r on (ur.roleId=r.id)
	LEFT JOIN UserImage ui on (ui.id=a.imageId)
	LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id);

create
or replace view vw_studentQuizQuestionAnswers as
SELECT  qa.id as questionAnswerId,q.questionName,q.custId,q.id questionId,q.quizId,q.status, q.version,q.createdDate,q.lastAccessDate,q.lastUpdatedDate, qa.questionAnswer,qa.anserOptions,qa.correctAnswer
FROM quizQuestion q  RIGHT JOIN questionAnswer qa on (q.id=qa.questionId);

create
or replace view vw_studentQuestionAnswers as
SELECT  q.questionName,q.startDate,q.endDate,q.custId,q.id as questionId,q.quizId,q.status, qa.studentAnswer,qa.studentCorrectAnswer,qa.correctAnswer,qa.studentId
FROM quizQuestion q  RIGHT JOIN studentQuestionAnswer qa on (q.id=qa.questionId);

create 
or replace view vw_studyClassSubjectDetails as
SELECT sc.classNameClassId classId, sc.id as studyClassId,sc.className,sc.section,sc.custId,sc.academicYearId,ss.id as subjectId,ss.name as subjectName,IFNULL(ss.subjectNumber,'') as subjectNumber,
IFNULL(ss.sortingOrder,0) as sortingOrder,IFNULL(c.sortingOrder,0) as classSortingOrder,c.noOfSections,ss.language,sc.educationType,ss.subjectType
FROM ClassSubject cs LEFT JOIN  studyClass sc on (cs.studyClassId=sc.id) LEFT JOIN studySubject ss on (cs.subjectId=ss.id) LEFT JOIN class c on(c.id = sc.classNameClassId);

create
or replace view vw_classExamSubTypes as
SELECT ce.classNameId classId, et.id examTypeId,et.examType,et.minMarks,et.maxMarks,st.id as subTypeId,st.subTypeName,st.schedule,et.custId,st.predefinedSubType,IFNULL(et.sortingOrder,0) as examTypeSortingOrder,IFNULL(st.sortingOrder,0) as subTypeSortingOrder
FROM classExamTypes ce LEFT JOIN examTypes et on (ce.examTypeId=et.id) LEFT JOIN subType st  on (st.custId=et.custId and st.academicYearId=et.academicYearId);

 CREATE OR REPLACE VIEW vw_examSchedule AS
select sc.id AS classSectionId,sc.academicYearId AS academicYearId,sc.className AS className,sc.section AS section,ss.name AS subjectName,et.id AS examTypeId,et.examType AS examType,et.minMarks AS minMarks,
ifnull(es.maxMarks, 0.0) AS scheduleMaxMarks,es.examDate AS examDate,es.startTime AS startTime,es.endTime AS endTime,sc.custId AS custId,ifnull(es.id, 0)  AS scheduleId,ss.id AS subjectId,
ss.subjectType AS subjectType,st.id AS subTypeId,
st.subTypeName AS subTypeName,st.schedule AS subTypeSchedule,ifnull(es.scheduled, 'N') AS scheduled,c.id AS classId,et.maxMarks AS examTypeMaxMarks,st.predefinedSubType AS predefinedSubType,ifnull(ss.subjectNumber, '') AS subjectNumber,
ifnull(ss.sortingOrder, 0) AS subjectSortingOrder,ifnull(c.sortingOrder, 0) AS classSortingOrder,ifnull(et.sortingOrder, 0) AS examTypeSortingOrder,ifnull(st.sortingOrder, 0) AS subTypeSortingOrder,c.higherStandard as higherStandard
from examTypesAndSubTypes exs left join classSectionExamTypes ce ON(ce.examTypeId = exs.examTypeId)
left join examTypes et ON(et.id = ce.examTypeId)
left join subType st ON(st.id = exs.subTypeId)
left join studyClass sc ON(ce.classSectionId = sc.id)
left join class c ON(c.id = sc.classNameClassId)
join ClassSubject cs ON(cs.studyClassId = sc.id)
left join studySubject ss ON (ss.id = cs.subjectId)
left join examSchedules es ON (es.classSectionId = sc.id and es.classSubjectId = ss.id and es.examTypeId = et.id and es.subTypeId = st.id);


create or replace view vw_staffSubjectsDetails as
SELECT s.accountId,s.id as staffId,cteacher.id as classTeacherId,sc.classNameClassId as classId,IFNULL(ss.id,0) as classSubjectId,cteacher.classTeacher as classTeacher,
cteacher.studyClassId,IFNULL(cteacher.studySubjectId,0) as studySubjectId,ss.name as subjectName,sc.className,sc.section,sc.custId,cteacher.academicYearId,
CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  = '','',CONCAT(' - ',sc.section))) as classAndSection,IFNULL(css.periodsPerWeek,0) as periodsPerWeek,
IFNULL(cteacher.periodsCount,0) as periodsHandleCount,c.sortingOrder as classSortingOrder,s.status,IFNULL(css.subjectPriority,0) as subjectPriority,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL,'',CONCAT(' ',p.lastName))) as fullName
FROM classTeacher cteacher LEFT JOIN staff s on (s.id=cteacher.teacherId) 
LEFT JOIN studySubject ss on (cteacher.studySubjectId=ss.id) 
LEFT JOIN studyClass sc on (cteacher.studyClassId=sc.id) 
LEFT JOIN class c on (c.id=sc.classNameClassId) 
LEFT JOIN classSubjectsSettings css on (css.classSectionId = cteacher.studyClassId and css.studySubjectId = cteacher.studySubjectId)
LEFT JOIN staff st ON (st.id=cteacher.teacherId)
LEFT JOIN Account a ON (a.id = st.accountId)
LEFT JOIN Person p ON (p.id = a.personId);

 
CREATE OR REPLACE VIEW vw_classSectionTeacher AS 
select c.id AS classId,sc.id AS classSectionId,sc.filledSeats AS filledSeats,sc.sectionCapacity AS sectionCapacity,sc.academicYearId AS academicYearId,
sc.className AS className,sc.section AS section,ifnull(ct.teacherId, 0) AS teacherId,ifnull(ct.classTeacher, 'N') AS classTeacher,ifnull(ct.studySubjectId, 0) AS subjectId,
ifnull(p.firstName, '') AS firstName,ifnull(p.lastName, '') AS lastName,ifnull(p.mobileNumber, 'N/A') AS mobileNumber,a.username AS username,a.id AS accountId,
c.sortingOrder AS classSortingOrder,c.custId AS custId,s.status AS status,vsd.email AS staffEmail
from class c 
left join studyClass sc ON (sc.classNameClassId = c.id)
left join classTeacher ct ON (ct.studyClassId = sc.id)
left join staff s ON(s.id = ct.teacherId)
left join Account a ON(a.id = s.accountId)
left join Person p ON(p.id = a.personId)
left join vw_staffDetails vsd ON (ct.teacherId = vsd.staffId and ct.academicYearId = vsd.academicYearId);

create 
or replace view vw_staffAttendance as
SELECT sd.accountId,sd.custId,sd.staffId, sd.academicYearId,sd.username, 
sd.status,sd.firstName,sd.lastName, sd.middleName, sd.dateOfBirth,sd.mobileNumber,sd.roleName,
sd.phoneNumber,IFNULL(a.id,0) as attendanceId, a.attendanceDate,IFNULL(a.noOfPresentDays,0) as noOfPresentDays,IFNULL(a.category,'N') as category, IFNULL(a.leaveRequest,'N') as leaveRequest,a.month,IFNULL(a.monthNum,0) as monthNum,IFNULL(a.present,'Y') as present,IFNULL(a.year,0) as year,IFNULL(leaveType,'') as leaveType
FROM vw_staffDetails sd LEFT JOIN attendance a on (sd.accountId=a.accountId);

create 
or replace view vw_staffEligibleSubjects as
SELECT ses.id as staffEligibleId,ses.academicYearId,ss.id as studySubjectId,ss.name as subjectName,s.id as staffId,s.status as activeStatus,s.accountId,s.custId
FROM staffElgibleSubjects ses LEFT JOIN studySubject ss on (ses.studySubjectId=ss.id) LEFT JOIN staff s on (ses.staffId=s.id);

-- For Hostel req:Madhu-22/11/2011
create or replace view vw_buildingFloorDetails as
SELECT buil.id as buildingId,buil.buildingName,buil.custId,buil.gender,buil.academicYearId,buil.status as buildingStatus,f.id as floorId,f.floorName,f.floorLevel,f.gender as fgender,f.status as floorStatus
FROM floor f LEFT JOIN building buil on (buil.id = f.buildingId ); 
   
create
or replace view vw_hostelBuildingDetails as
SELECT buil.id as buildingId,buil.buildingName,buil.custId,buil.academicYearId,h.id as hostelId,h.hostelName,buil.contactNumber,buil.status as buildingStatus,a.streetName,a.city,a.state,a.postalCode,IFNULL(concat(h.hostelName,'-',buil.buildingName),'') as hostelWithBuildingName
FROM building buil LEFT JOIN hostel h on (buil.hostelId = h.id ) LEFT JOIN Address a on (buil.addressId=a.id); 
 

create
or replace view vw_hostelAllDetails as
SELECT buil.id as buildingId,buil.buildingName,buil.custId,buil.academicYearId,h.id as hostelId,
h.hostelName,buil.contactNumber,buil.status as buildingStatus,f.id as floorId,f.floorName,f.floorLevel,f.gender as fgender,f.status as floorStatus,
IFNULL(concat(h.hostelName,'-',buil.buildingName),'') as hostelWithBuildingName,r.roomName,
r.id as roomId,b.id as bedId,
IFNULL(concat(r.roomName,'-',b.bedName),'') as roomAndBedName,
IFNULL(concat(h.hostelName,'-',buil.buildingName,'-',f.floorName,'-',r.roomName,'-',b.bedName),'') as hostelAllDetailsName,
IFNULL(concat(h.hostelName,'-',buil.buildingName,'-',f.floorName),'') as hostelBuildingFloorName

FROM bed b LEFT JOIN room r on (r.id = b.roomId)
LEFT JOIN floor f on (f.id = r.floorId)
LEFT JOIN building buil on (buil.id = f.buildingId)
LEFT JOIN hostel h on (buil.hostelId = h.id );

create
or replace view vw_hostelRoomBedDetails as
SELECT buil.id as buildingId,buil.buildingName,buil.custId,buil.academicYearId,h.id as hostelId,
h.hostelName,buil.contactNumber,buil.status as buildingStatus,f.id as floorId,f.floorName,f.floorLevel,f.gender as fgender,f.status as floorStatus,
IFNULL(concat(h.hostelName,'-',buil.buildingName),'') as hostelWithBuildingName,r.roomName,
r.id as roomId,b.id as bedId,
IFNULL(concat(r.roomName,'-',b.bedName),'') as roomAndBedName,
IFNULL(concat(h.hostelName,'-',buil.buildingName,'-',f.floorName,'-',r.roomName,'-',b.bedName),'') as hostelAllDetailsName,
IFNULL(concat(h.hostelName,'-',buil.buildingName,'-',f.floorName),'') as hostelBuildingFloorName

FROM hostel h LEFT JOIN building buil on (buil.hostelId = h.id)
LEFT JOIN floor f on (f.buildingId = buil.id)
LEFT JOIN room r on (r.floorId = f.id)
LEFT JOIN bed b on (b.roomId = r.id);


 create or replace view vw_hostelStudentDetails as SELECT 
a.id as accountId,a.custId,a.username,sp.parentAccountId AS parentId,a.accountExpired,a.admissionNumber,s.id as studentId,s.academicYearId,s.description,s.status,s.classNameClassId,
s.rollNumber,s.joinedThroughAdmissions,s.categoryId,s.hostelMode,p.firstName,p.lastName,p.middleName,p.fatherName,p.motherName,p.mothersMaidenName,p.dateOfBirth,p.mobileNumber,
p.phoneNumber,p.parentEmail,p.gender,p.identification1,p.identification2,p.bloodGroup,d.city,d.state,d.postalCode,c.className,c.section,
CONCAT(IF(c.className IS NULL, '', c.className),IF(c.section IS NULL || c.section = '','',CONCAT(' - ', c.section))) as classNameAndSection,
ur.roleName,ur.roleDescription,ifNULL(ur.roleId,3) as roleId,IFNULL(c.id, 0) as classSectionId,p.FatherOccupation as fatherOccupation,p.motherOccupation,p.annualIncome,p.sslcNumber,
p.tmrNumber,p.classJoined,p.prefferedHospital,d.streetName,IFNULL(st.stateName, '') as stateName,acy.academicYear,CONCAT(IFNULL(concat(custAddr.addressLine1, ', '), ''),
IFNULL(concat(custAddr.streetName, ', '), ''),CHAR(10),IFNULL(concat(custAddr.city, ' - '), ''),IFNULL(concat(custAddr.postalCode, ', '), ''),IFNULL(concat('PH - ', cst.mobileNumber, '.'),'')) as customerFullAddress,CONCAT(IFNULL(concat(d.streetName, ', '), ''),
IFNULL(concat(d.city, ' - '), ''),CHAR(10),IFNULL(concat(d.postalCode, '. '), ''),IFNULL(concat('PH - ', p.mobileNumber, '.'), '')) as parentFullAddress,IFNULL(s.registerNumber, '') as registerNumber,
ifnull(r.roomName,'') as roomName,ifnull(f.floorName,'') as floorName,ifnull(r.id,0) as roomId,ifnull(f.id,0) as floorId,ifnull(concat(f.floorName, ' - ', r.roomName),'') as roomWithFloorName,
ifnull(hb.hostelName,'') as hostelName,ifnull(hb.buildingName,'') as buildingName,
IFNULL(concat(r.roomName,'-',b.bedName),'') as roomAndBedName,
IFNULL(concat(hb.hostelName,'-',hb.buildingName,'-',f.floorName),'') as hostelBuildingFloorName
FROM
    student s LEFT JOIN Account a ON (s.accountId = a.id) LEFT JOIN Person p ON (a.personId = p.id) LEFT JOIN Address d ON (a.paddressId = d.id) LEFT JOIN State st ON (st.stateCode = d.state)
    LEFT JOIN studyClass c ON (c.id = s.classSectionId) LEFT JOIN academicYear acy ON (acy.id = s.academicYearId) LEFT JOIN schoolCategory sc ON (sc.id = s.categoryId)
    LEFT JOIN vw_userRoles ur ON (ur.accountId = s.accountId) LEFT JOIN class cl ON (cl.id = s.classNameClassId) JOIN customer cst ON (a.custId = cst.id) LEFT JOIN Address custAddr ON (custAddr.id = cst.addressId)
    LEFT JOIN hostelStudents hs on (s.id=hs.studentId and a.id = hs.accountId)
    LEFT JOIN room r on (hs.roomId = r.id)
    LEFT JOIN bed b on (hs.bedId = b.id and hs.roomId = b.roomId)
    LEFT JOIN floor f on (f.id=r.floorId) 
    LEFT JOIN vw_hostelBuildingDetails hb on hb.buildingId=f.buildingId
    LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id);
    
    
  
CREATE or replace VIEW vw_studentCountByRoom AS select 
    count(0) AS count,
    vhs.roomId AS roomId,
    vhs.custId AS custId,
    vhs.floorId AS floorId,
    vhs.academicYearId AS academicYearId
from
    vw_hostelStudentDetails as vhs
where
    (vhs.roomId > 0)
group by vhs.roomId , vhs.floorId , vhs.academicYearId;


create
or replace view vw_roomDetails as  
SELECT 
    r.id as roomId,
    r.roomNumber,
    r.custId,
    ifnull(r.capacity,0) as capacity,
    ifnull(r.capacity,0)-ifnull(vws.count,0) as remainingBeds,
    r.academicYearId,
    IFNULL(f.id, 0) as floorId,
    f.floorName,
    IFNULL(f.floorLevel, 0) as floorLevel,
    f.buildingId,
    r.roomLevel,
    r.roomName,
    concat(b.buildingName, ' - ', f.floorName) as buildingFloorName,
    concat(f.floorName, ' - ', r.roomName) as roomWithFloorName,
    b.allocatedFor,
    b.buildingName,
    IFNULL(be.id, 0) as bedId,
    IFNULL(concat(r.roomName,'-',be.bedName),'') as roomAndBedName
FROM
    room r
        LEFT JOIN
    floor f ON (r.floorId = f.id)
        LEFT JOIN
    building b ON (f.buildingId = b.id)
    LEFT JOIN bed be on(r.id=be.roomId)
   LEFT JOIN
   vw_studentCountByRoom vws on (vws.roomId = r.id);
   

CREATE OR REPLACE VIEW vw_studentsAllotedBeds AS 
select hs.id as hostelStudentId, s.id AS studentId,s.custId AS custId,s.academicYearId AS academicYearId,s.status AS status,s.accountId AS accountId,a.username AS username,a.admissionNumber AS admissionNumber,s.classNameClassId AS classId,
s.rollNumber AS rollNumber,s.categoryId AS categoryId,p.firstName AS firstName,p.lastName AS lastName,p.middleName AS middleName,p.fatherName AS fatherName,p.motherName AS motherName,p.dateOfBirth AS dateOfBirth,
p.bloodGroup AS bloodGroup,p.mobileNumber AS mobileNumber,p.castId AS castId,p.subCastId AS subCastId,p.religionId AS religionId,p.nationality AS nationality,p.gender AS gender,p.motherToungId AS motherToungId,
sc.className AS className,sc.section AS section,ifnull(sc.mediumId, 0) AS mediumId,cl.higherStandard AS higherStandard,sc.id AS classSectionId,cs.castName AS castName,ifnull(scs.subCastName, '') AS subCastName,
ct.skillTypeName AS religion,ifnull(hs.bedId, 0) AS bedId,addr.streetName AS streetName,addr.city AS city,st.stateName AS stateName,addr.postalCode AS postalCode,b.bedName AS bedName,b.bedLevel AS bedLevel,b.bedCost AS bedCost,
b.status AS bedStatus,r.id AS roomId,r.roomName AS roomName,r.roomLevel AS roomLevel,r.roomType AS roomType,r.floorId AS floorId,f.floorName AS floorName,f.floorLevel AS floorLevel,buil.id AS buildingId,
buil.buildingName AS buildingName,buil.buildingShortName AS buildingShortName,h.hostelName AS hostelName,
CONCAT(IFNULL(concat(addr.addressLine1, ', '), ''),IFNULL(concat(addr.streetName, ', '), ''),CHAR(10),IFNULL(concat(addr.city, ' - '), ''),IFNULL(concat(addr.postalCode, ', '), ''),IFNULL(concat('Ph - ', buil.contactNumber, '.'),'')) as hostelFullAddress,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,
 CONCAT(IF(f.floorName IS NULL,'',f.floorName), IF(f.FloorLevel IS NULL || f.FloorLevel  = '','',CONCAT(' ',f.FloorLevel))) as studentFloorName,
 CAST(CONCAT(IF(sc.className IS NULL, '', sc.className),IF(sc.section IS NULL || sc.section = '','',CONCAT(' - ', sc.section))) AS CHAR(60)) as classSection
from hostelStudents hs 
left join student s ON(s.id = hs.studentId)
join Account a ON(a.id = s.accountId)
left join Person p ON(p.id = a.personId)
left join studyClass sc ON(sc.id = s.classSectionId)
left join class cl ON (cl.id = s.classNameClassId)
left join castSettings cs ON (cs.id = p.castId)
left join subCastSettings scs ON (scs.id = p.subCastId)
left join commonType ct ON (ct.id = p.religionId and ct.type = 'RELIGION')
left join bed b ON (hs.bedId = b.id)
left join room r ON (b.roomId = r.id)
left join floor f ON (r.floorId = f.id)
left join building buil ON (f.buildingId = buil.id)
left join hostel h ON (buil.hostelId = h.id)
left join Address addr ON (h.addressId = addr.id)
left join State st ON (st.stateCode = addr.state);

create or replace view vw_staffAllotedBeds as
SELECT s.staffId,s.custId,s.academicYearId,s.status,s.accountId,s.username,s.hostelCategoryId,s.firstName,s.lastName,s.gender,
s.bedId,addr.streetName,addr.city,st.stateName,addr.postalCode,b.bedName,b.bedLevel,b.bedCost,
b.status as bedStatus,r.id as roomId,r.roomName,r.roomLevel,r.roomType,r.floorId,f.floorName,f.floorLevel,buil.id as buildingId,buil.buildingName,buil.buildingShortName,h.hostelName
FROM vw_staffDetails s  JOIN bed b on(s.bedId=b.id) LEFT JOIN room r on (b.roomId=r.id) LEFT JOIN floor f on (r.floorId=f.id) LEFT JOIN building buil on (f.buildingId=buil.id) LEFT JOIN hostel h on (buil.hostelId=h.id) LEFT JOIN Address addr on (h.addressId=addr.id) LEFT JOIN State st on (st.stateCode=addr.state); 

CREATE OR REPLACE VIEW vw_studentOutHostelDetails AS 
select s.id AS studentOutId,s.visitorName AS visitorName,s.custId AS custId,s.academicYearId AS academicYearId,s.visitorRelation AS visitorRelation,s.outDate AS outDate,s.outTime AS outTime,s.exceptedInTime AS exceptedInTime,
s.reasonForOuting AS reasonForOuting,s.informParentsOnOut AS informParentsOnOut,s.expectedInDate AS expectedInDate,s.actualInDate AS actualInDate,s.studentInOutStatus AS studentInOutStatus,s.accountId AS accountId,
p.firstName AS firstName,p.lastName AS lastName,c.className AS className,ss.rollNumber AS rollNumber,c.section AS section,b.bedName AS bedName,b.bedLevel AS bedLevel,b.bedCost AS bedCost,b.status AS bedStatus,
r.id AS roomId,r.roomName AS roomName,r.roomLevel AS roomLevel,r.roomType AS roomType,r.floorId AS floorId,f.floorName AS floorName,f.floorLevel AS floorLevel,buil.id AS buildingId,buil.buildingName AS buildingName,
buil.buildingShortName AS buildingShortName,h.hostelName AS hostelName,addr.streetName AS streetName,addr.city AS city,st.stateName AS stateName,addr.postalCode AS postalCode
from studentOut s join Account a ON(a.id = s.accountId)
left join Person p ON(p.id = a.personId)
left join student ss ON(ss.accountId = s.accountId and ss.custId = s.custId and ss.academicYearId = s.academicYearId)
left join studyClass c ON (c.id = ss.classSectionId)
join bed b ON (ss.bedId = b.id)
left join room r ON (b.roomId = r.id)
left join floor f ON (r.floorId = f.id)
left join building buil ON (f.buildingId = buil.id)
left join hostel h ON (buil.hostelId = h.id)
left join Address addr ON (h.addressId = addr.id)
left join State st ON (st.stateCode = addr.state);

create
or replace view vw_buildingMenuItemsDetails as
SELECT IFNULL(fmi.id,0) as foodMenuItemsId,fmi.dayId,fmi.custId,fmi.menuItemNames,ft.id as foodTypeId,ft.academicYearId,mmt.id as messMenuTimeId,mmt.startTime,mmt.endTime,mmt.messFoodTypeName,mmt.buildingId,wd.dayName,b.buildingName
FROM foodMenuItems fmi LEFT JOIN foodTypes ft on (fmi.foodTypeId = ft.id) LEFT JOIN messMenuTime mmt on (fmi.messMenuTimeId = mmt.id) LEFT JOIN weekDays wd on (fmi.dayId = wd.id) LEFT JOIN building b on (mmt.buildingId = b.id);

create or replace view vw_classSectionDetails as
select c.id as classId,sc.id as classSectionId,c.className,c.academicYearId,c.custId,c.sortingOrder,c.noOfSections,c.higherStandard,c.extendableClassCapacity,c.admissionsOpen,IFNULL(sc.filledSeats,0) as filledSeats,IFNULL(sc.section,'') as section,sc.sectionCapacity,sc.groupNumber,IFNULL(sc.mediumId,0) as mediumId,IFNULL(sc.syllabusTypeId,0) as syllabusTypeId,
IFNULL(ct.name,'') as classMedium,st.syllabusTypeName,st.syllabusTypeDescription,IFNULL(a.academicYear,'') as academicYear,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  = '','',CONCAT(' - ',sc.section))) as classAndSection 
from  studyClass sc LEFT JOIN class c on (sc.classNameClassId = c.id) LEFT JOIN medium ct on (sc.mediumId=ct.id) LEFT JOIN syllabusType st on (sc.syllabusTypeId=st.id) LEFT JOIN academicYear a on (sc.academicYearId = a.id);

 
create or replace view vw_examScheduleWiseStudentMarks as SELECT
   es.id as scheduleId,
   IFNULL(s.id, 0) as studId,
   es.custId,
   es.classSectionId,
   es.classSubjectId,
   es.academicYearId,
   es.examTypeId,
   IFNULL(sm.id, 0) as marksId,
   es.subTypeId,
   IFNULL(es.maxMarks, 0) as scheduleMaxMarks,
   IFNULL(sm.obtainedMarks, 0) as obtainedMarks,
   IFNULL(sm.moderationMarks, 0) as moderationMarks,
   sm.present,
   et.examType,
   et.maxMarks,
   et.id as eid,
   et.minMarks,
   st.subTypeName,
   es.maxMarks as subTypeMaxMarks,
   ss.name as subjectName,
   IFNULL(ss.subjectNumber, '') as subjectNumber,
   ss.sortingOrder,
   sum(sm.obtainedMarks + sm.moderationMarks) as totalSubjectMarksObtained,
   cs.higherStandard,
   ss.id as subjectId,
   (IFNULL(sm.obtainedMarks, 0) + IFNULL(sm.moderationMarks, 0)) as obtainedSubTypeTotalMarks
FROM
   student s
       LEFT JOIN
   studentMarks sm ON (s.id = sm.studId)
       LEFT JOIN
   examSchedules es ON (es.id = sm.examScheduleId)
       LEFT JOIN
   examTypes et ON (et.id = es.examTypeId)
       LEFT JOIN
       subType st ON (st.id = es.subTypeId)
       LEFT JOIN
   class cs ON (cs.id = s.classNameClassId)
       LEFT JOIN
   studySubject ss ON (ss.id = es.classSubjectId)
       LEFT JOIN
   studyClass sc ON (sc.id = s.classSectionId) group by sm.id,et.id , ss.id , s.id;

create or replace view vw_promotionClassesDetails as 
SELECT IFNULL(sc.id,0) as classSectionId,sc.className,sc.custId,sc.academicYearId,sc.section,IFNULL(pc.promoteClassName,'') as promoteClassName,IFNULL(pc.promoteSectionName,'') as promoteSectionName,IFNULL(pc.id,0) as promotionId,c.sortingOrder,IFNULL(ct.name,'') as medium,IFNULL(pc.mediumId,0) as promoteClassMediumId,IFNULL(sc.mediumId,0) as mediumId,IFNULL(cts.name,'') as promoteClassMedium,c.id as classId,IFNULL(pc.promoteProcessCompleted,'N') as promoteProcessCompleted 
FROM studyClass sc JOIN class c on (sc.classNameClassId = c.id) LEFT JOIN promoteClass pc on (sc.id = pc.currentClassSectionId) LEFT JOIN studyClass cs on (pc.promoteClassName=cs.className and pc.promoteSectionName = cs.section and cs.academicYearId = pc.academicYearId) LEFT JOIN medium ct on (sc.mediumId = ct.id)  LEFT JOIN medium cts on (pc.mediumId = cts.id);

create or replace view vw_studentsAbsentiesCount as
	SELECT accountId,academicYearId,IFNULL(count(*),0) as absentiesCount FROM attendance where category='Y' and present='N' group by accountId,academicYearId,custId;

CREATE OR REPLACE VIEW vw_studentClassAndAttendanceDetails AS 
select s.id AS studId,s.classSectionId AS classSectionId,s.status AS status,s.custId AS custId,s.rollNumber AS rollNumber,s.academicYearId AS academicYearId,s.accountId AS accountId,ifnull(s.bedId, 0) AS bedId,
s.classNameClassId AS classId,trim(p.firstName) AS firstName,trim(p.lastName) AS lastName,c.className AS className,c.section AS section,cl.higherStandard AS higherStandard,
concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')), '', concat(' ', p.lastName))) AS fullName,ifnull(cs.castName, '') AS castName,ifnull(a.admissionNumber, '') AS admissionNumber,
ifnull(vsa.absentiesCount, 0) AS absentiesCount,s.registerNumber AS registerNumber
from student s 
left join Account a ON (s.accountId = a.id)
left join Person p ON (a.personId = p.id)
left join studyClass c ON (c.id = s.classSectionId)
left join class cl ON (cl.id = s.classNameClassId)
left join castSettings cs ON (cs.id = p.castId)
left join vw_studentsAbsentiesCount vsa ON (vsa.accountId = s.accountId and vsa.academicYearId = s.academicYearId);

/* This view contains student account,person and address details */
create 
or replace view vw_studentPersonInfo as
SELECT s.id as studId,s.classSectionId,s.status,s.custId,s.rollNumber,s.academicYearId,s.accountId,s.bedId,s.classNameClassId as classId,
TRIM(p.firstName) as firstName,TRIM(p.lastName) as lastName,p.castId,p.subCastId,p.religionId,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,
IFNULL(a.admissionNumber,'') as admissionNumber,s.registerNumber,a.accountExpired,s.description as studDiscontinueDesc,s.joinedThroughAdmissions,p.dateOfJoining,
p.fatherName,p.motherName,p.motherOccupation,p.parentEmail,p.annualIncome,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.nationality,p.motherToungId,
d.streetName,d.city,d.postalCode,p.identification1,p.identification2,cs.castName,scs.subCastName,IFNULL(rel.skillTypeName,' ') as religion,
IFNULL(mtg.skillTypeName,'') as motherToung,p.dateOfBirth,p.communityNumber,p.sslcNumber,p.tmrNumber,p.classJoined,p.relievingDate,p.scholarShipInfo,
p.gender,s.categoryId,p.tcAppliedDate,p.tcIssuedDate,d.email as studentEmail,s.transportMode,p.height,p.weight,p.visionL,p.visionR,p.teeth,p.oralHygiene,a.imageId,p.placeOfBirth,p.lastSchool,
IFNULL(s.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,IFNULL(s.boardingPointId,0) as boardingPointId,s.hostelMode
FROM student s  JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN Address d on (a.paddressId=d.id) LEFT JOIN castSettings cs on (cs.id = p.castId) 
LEFT JOIN subCastSettings scs on (scs.id = p.subCastId) LEFT JOIN commonType rel on (rel.id = p.religionId and rel.type = 'RELIGION') 
LEFT  JOIN commonType mtg on (mtg.id = p.motherToungId and mtg.type = 'MOTHERTOUNG');

/* This view contains student Classes details. */
create 
or replace view vw_studentsClassSectionDetails as
SELECT s.id as studId,s.classSectionId,s.status,s.custId,s.rollNumber,s.academicYearId,s.accountId,s.bedId,s.classNameClassId as classId,c.className,c.section,cl.higherStandard,s.registerNumber,s.description as studDiscontinueDesc,ac.academicYear,c.mediumId,ct.name as medium,
c.educationType,IFNULL(cl.sortingOrder,0) as classSortingOrder,IFNULL(c.syllabusTypeId,0) as syllabusTypeId,ac.nextAcademicStartDate
FROM student s JOIN  studyClass c on (c.id=s.classSectionId) JOIN class cl on (cl.id=s.classNameClassId) LEFT JOIN academicYear ac on (ac.id=s.academicYearId) LEFT JOIN medium ct on (ct.id = c.mediumId);

/* This view contains student account,person,address and class details */
CREATE OR REPLACE VIEW vw_studentClasswiseAndPersonalInfo AS 
select s.id AS studId,s.classSectionId AS classSectionId,s.status AS status,s.custId AS custId,s.rollNumber AS rollNumber,s.academicYearId AS academicYearId,s.accountId AS accountId,ifnull(sp.parentAccountId, 0) as parentId,ifnull(s.bedId, 0) AS bedId,
s.classNameClassId AS classId,p.firstName AS firstName,p.lastName AS lastName,p.castId AS castId,p.subCastId AS subCastId,p.religionId AS religionId,c.className AS className,c.section AS section,
cl.higherStandard AS higherStandard,concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')), '',concat(' ', p.lastName))) AS fullName,cs.castName AS castName,
ifnull(a.admissionNumber, '') AS admissionNumber,s.registerNumber AS registerNumber,a.accountExpired AS accountExpired,s.description AS studDiscontinueDesc,if(isnull(p.dateOfJoining), '', date_format(p.dateOfJoining, '%e/%c/%Y')) AS dateOfJoining,
if(isnull(p.dateOfBirth), '', date_format(p.dateOfBirth, '%e/%c/%Y')) AS dateOfBirth,p.fatherName AS fatherName,p.motherName AS motherName,p.motherOccupation AS motherOccupation,p.parentEmail AS parentEmail,
p.annualIncome AS annualIncome,p.bloodGroup AS bloodGroup,p.mobileNumber AS mobileNumber,p.phoneNumber AS phoneNumber,p.nationality AS nationality,p.motherToungId AS motherToungId,d.streetName AS streetName,
d.city AS city,d.postalCode AS postalCode,p.identification1 AS identification1,p.identification2 AS identification2,scs.subCastName AS subCastName,ifnull(rel.skillTypeName, ' ') AS religion,
ifnull(mtg.skillTypeName, '') AS motherToung,s.categoryId AS categoryId,s.joinedThroughAdmissions AS joinedThroughAdmissions,s.transportMode AS transportMode,ifnull(s.vehicleAcademicDetailsId, 0) AS vehicleAcademicDetailsId,
ifnull(s.boardingPointId, 0) AS boardingPointId,ifnull(cl.sortingOrder, 0) AS classSortingOrder,s.hostelMode AS hostelMode
from student s join Account a ON (s.accountId = a.id) 
left join Person p ON (a.personId = p.id)
left join Address d ON (a.paddressId = d.id)
left join castSettings cs ON (cs.id = p.castId)
left join subCastSettings scs ON (scs.id = p.subCastId)
left join commonType rel ON (rel.id = p.religionId and rel.type = 'RELIGION')
left join commonType mtg ON (mtg.id = p.motherToungId and mtg.type = 'MOTHERTOUNG')
left join studyClass c ON (c.id = s.classSectionId)
join class cl ON (cl.id = s.classNameClassId)
LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id);

create 
or replace view vw_staffPayrollDetails as
SELECT a.id accountId,a.accountExpired,a.custId, a.username,  a.version, a.sharePrivacy, a.userOnlineNow, s.id staffId, s.description,s.academicYearId,s.qualification1,s.qualification2, s.createdDate, s.lastAccessDate, s.status, s.supervisorId,IFNULL(s.bedId,0) as bedId,s.hostelCategoryId,s.staffType,IFNULL(s.organizationTypeId,0) as organizationTypeId, s.staffPayrollMonth ,s.staffPayrollYear, p.licenseNumber,p.licenseExpDate,p.experience,p.dateofJoining,p.firstName, p.lastName,p.middleName,p.dateOfBirth,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.maritalStatus,p.summary,p.gender,p.familyDoctor,p.prefferedHospital,IFNULL(p.religionId,0) as religionId,IFNUll(p.castId,0) as castId,IFNULL(p.subCastId,0) as subCastId,IFNULL(p.annualIncome,0) as annualIncome,p.designation,p.gpfNumber,p.officeNumber,p.nationality,p.panNumber,IFNULL(p.motherToungId,0) as motherToungId,d.addressLine1,d.addressLine2,d.city,d.state,d.postalCode,d.email, a.lastUpdatedDate,
IFNULL(ur.roleId,0) as roleId,ur.roleName,ur.roleDescription,ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail ,ps.id as payrollSettingsId,ps.payrollName,ps.payrollDescription,ps.payrollTypesId, ps.month as payrollMonth,ps.year as payrollYear,ps.percentage,ps.lastUpdatedDate as pslastUpdatedDate,ps.payrollType
FROM staff s LEFT JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id) LEFT JOIN  vw_userRoles ur on (a.id=ur.accountId) LEFT JOIN UserImage ui on (ui.id=a.imageId) LEFT JOIN payrollSettings ps on (ps.staffId=s.id);

 
create or replace view vw_classWisePeriodsCountDetails as
SELECT  tt.id as timeTableId,tt.dayId,wd.
dayName,tt.periodName,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  = '','',CONCAT(' - ',sc.section))) as classAndSection,count(*) as periodsCount,tt.classSectionId,tt.custId,tt.academicYearId,c.className,c.sortingOrder,sc.section,IFNULL(sc.classNameClassId,0) as classId
FROM class c left join studyClass sc ON (sc.classNameClassId = c.id) right join timeTable tt on (tt.classSectionId = sc.id)
left JOIN weekDays wd ON (tt.dayId = wd.id) group by tt.classSectionId , tt.dayId;

CREATE OR REPLACE VIEW vw_classSubjectsSettings AS 
select sc.id AS classSectionId,sc.academicYearId AS academicYearId,sc.className AS className,sc.section AS section,ss.name AS subjectName,sc.custId AS custId,
ss.id AS subjectId,sc.classNameClassId AS classId,ifnull(ss.subjectNumber, '') AS subjectNumber,ifnull(ss.sortingOrder, 0) AS sortingOrder,ifnull(css.periodsPerWeek, 0) AS periodsPerWeek,
ifnull(css.subjectPriority, 0) AS subjectPriority,ifnull(css.continuousPeriodsCount, 0) AS continuousPeriodsCount,ifnull(css.id, 0) AS classSubjectSettingId,ifnull(c.sortingOrder, 0) AS classSortingOrder,c.noOfSections AS noOfSections
from ClassSubject cs left join studyClass sc ON (sc.id = cs.studyClassId)
left join studySubject ss ON (ss.id = cs.subjectId)
left join class c ON (c.id = sc.classNameClassId)
left join classSubjectsSettings css ON (css.classSectionId = sc.id and css.studySubjectId = ss.id);

create
or replace view vw_timeTableDetails as
SELECT tt.id as timeTableId,tt.classSectionId as classSectionId,IFNULL(tt.subjectId,0) as subjectId,tt.dayId,tt.periodName,tt.periodType,tt.custId,tt.academicYearId,IFNULL(stp.teacherId,0) as teacherId,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  = '','',CONCAT(' - ',sc.section))) as classAndSection,ss.name as subjectName,wd.dayName,vsd.firstName, vsd.lastName,sc.className,sc.section,tt.combinedPeriod,sc.classNameClassId as classId,CONCAT(IF(vsd.firstName IS NULL,'',vsd.firstName), IF(vsd.lastName IS NULL || vsd.lastName  = '','',CONCAT(', ',vsd.lastName))) as staffFullName,tt.prioritiesBasedPeriod  
FROM timeTable tt LEFT JOIN staffTimeTablePeriods stp on (tt.id = stp.timeTableId) LEFT JOIN studyClass sc on (tt.classSectionId = sc.id) LEFT JOIN studySubject ss on (tt.subjectId = ss.id) LEFT JOIN weekDays wd on (tt.dayId = wd.id) LEFT JOIN vw_staffDetails vsd on (vsd.staffId = stp.teacherId);
 

create or replace view vw_subjectWiseAssignedPeriodsCount as
SELECT tt.classSectionId,tt.subjectId,tt.academicYearId,tt.custId,count(*) as assignedPeriodsCount,tt.id as timeTableId
FROM timeTable tt where tt.subjectId is not null group by tt.classSectionId,tt.subjectId;

create or replace view vw_subjectWisePeriodsDetails as
SELECT IFNULL(sap.timeTableId,0) as timeTableId,css.classSectionId,css.subjectId,css.custId,css.academicYearId,css.className,css.section,css.subjectName,css.periodsPerWeek,css.continuousPeriodsCount,css.subjectPriority,css.classSortingOrder,css. sortingOrder as subjectSortingOrder,IFNULL(sap.assignedPeriodsCount,0) as assignedPeriodsCount,CONCAT(IF(css.className IS NULL,'',css.className), IF(css.section IS NULL || css.section  = '','',CONCAT(' - ',css.section))) as classAndSection,css.classId
FROM vw_classSubjectsSettings css LEFT JOIN vw_subjectWiseAssignedPeriodsCount sap on (css.classSectionId = sap.classSectionId and css.subjectId = sap.subjectId);


CREATE OR REPLACE VIEW vw_studentsTCDetails AS 
select s.id AS studId,s.classSectionId AS classSectionId,s.status AS status,s.custId AS custId,s.rollNumber AS rollNumber,s.academicYearId AS academicYearId,s.accountId AS accountId,ifnull(s.bedId, 0) AS bedId,
s.classNameClassId AS classId,p.firstName AS firstName,p.lastName AS lastName,p.castId AS castId,p.subCastId AS subCastId,p.religionId AS religionId,c.className AS className,c.section AS section,
cl.higherStandard AS higherStandard,concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')),'',concat(' ', p.lastName))) AS fullName,ifnull(cas.castName, '') AS castName,
a.admissionNumber AS admissionNumber,ifnull(s.registerNumber, '') AS registerNumber,a.accountExpired AS accountExpired,s.description AS studDiscontinueDesc,p.dateOfJoining AS dateOfJoining,p.dateOfBirth AS dateOfBirth,
ifnull(p.fatherName, '') AS fatherName,ifnull(p.motherName, '') AS motherName,p.motherOccupation AS motherOccupation,p.parentEmail AS parentEmail,p.annualIncome AS annualIncome,p.bloodGroup AS bloodGroup,
        p.mobileNumber AS mobileNumber,
        p.phoneNumber AS phoneNumber,
        ifnull(p.nationality, '') AS nationality,
        p.motherToungId AS motherToungId,
        d.streetName AS streetName,
        d.addressLine1 AS addressLine1,
        d.addressLine2 AS addressLine2,
        d.city AS city,
        d.postalCode AS postalCode,
        ifnull(p.identification1, '') AS identification1,
        ifnull(p.identification2, '') AS identification2,
        ifnull(scs.subCastName ,'') AS subCastName,
        ifnull(rel.skillTypeName, ' ') AS religion,
        ifnull(mtg.name, '') AS motherToung,
        ifnull(cs.organization, '') AS organizationName,
        custaddr.addressLine1 AS custAddressLine1,
        custaddr.streetName AS custStreetName,
        custaddr.city AS custCity,
        custaddr.postalCode AS custPostalCode,
        cs.educationalDistrict AS educationalDistrict,
        cs.revenueDistrict AS revenueDistrict,
        ifnull(cs.schoolCode, '') AS schoolCode,
        cs.recognisedBy AS recognisedBy,
        cs.boardOfEducation AS boardOfEducation,
        ifnull(cs.affiliationNumber, '') AS affiliationNumber,
        ifnull(p.communityNumber, '') AS communityNumber,
        ifnull(p.sslcNumber, '') AS sslcNumber,
        ifnull(p.tmrNumber, '') AS tmrNumber,
        p.classJoined AS classJoined,
        p.relievingDate AS relievingDate,
        ifnull(p.scholarShipInfo, '') AS scholarShipInfo,
        ifnull(trnc.serialNumber, 0) AS serialNumber,
        ifnull(acy.academicYear, '') AS academicYear,
        ifnull(c.educationType, '') AS educationType,
        p.gender AS gender,
        ifnull(ct.name, '') AS mediumOfStudy,
        p.tcAppliedDate AS tcAppliedDate,
        p.tcIssuedDate AS tcIssuedDate,
        ifnull(bs.tcBookNumber, 0) AS tcBookNumber,
        ifnull(p.height, 0) AS height,
        ifnull(p.weight, 0) AS weight,
        ifnull(p.visionL, '') AS visionL,
        ifnull(p.visionR, '') AS visionR,
        ifnull(p.oralHygiene, '') AS oralHygiene,
        ifnull(c.syllabusTypeId, 0) AS syllabusTypeId,
        ifnull(stype.syllabusTypeName, '') AS syllabusTypeName,
        acy.startDate AS startDate,
        acy.endDate AS endDate,
        bs.type AS type,
        p.placeOfBirth AS placeOfBirth,
        p.lastSchool AS lastSchool,
        ui.name AS imageName,
        ui.path AS imagePath,
        ui.thumbNail AS thumbNail,
        st.stateName As stateName,
        p.aadharNumber,
        a.enrollmentCode AS enrollmentCode,ifnull(c.id, 0) AS studyClassId,trnc.createdDate as tcIssueDate
    from
        student s
        join Account a ON (s.accountId = a.id)
        left join Person p ON (a.personId = p.id)
        left join Address d ON (a.paddressId = d.id)
        left join castSettings cas ON (cas.id = p.castId)
        left join subCastSettings scs ON (scs.id = p.subCastId)
        left join commonType rel ON (rel.id = p.religionId and rel.type = 'RELIGION')
        left join motherTongue mtg ON (mtg.id = p.motherToungId)
        left join studyClass c ON (c.id = s.classSectionId)
        left join class cl ON (cl.id = s.classNameClassId)
        left join academicYear ac ON (ac.id = s.academicYearId)
        left join medium ct ON (ct.id = c.mediumId)
        left join customer cs ON (cs.id = s.custId)
        left join syllabusType stype ON (stype.id = c.syllabusTypeId)
        left join Address custaddr ON (custaddr.id = cs.addressId)
        left join transferCertificate trnc ON (s.accountId = trnc.accountId)
        left join tcBookSettings bs ON (bs.id = trnc.bookSettingId)
        left join academicYear acy ON (acy.id = s.academicYearId)
        left join UserImage ui ON (ui.id = s.imageId)
        left join State st ON (st.stateCode = d.state);
 
 
CREATE OR REPLACE VIEW vw_studentsScoreCardProfileDetails AS
    select 
        s.id AS studId,
        ifnull(s.rollNumber, 0) AS rollNumber,
        concat(if(isnull(p.firstName),
                    '',
                    p.firstName),
                if((isnull(p.lastName)
                        or (p.lastName = '')),
                    '',
                    concat(' ', p.lastName))) AS fullName,
        ifnull(c.className, '') AS className,
        ifnull(c.section, '') AS section,
        p.dateOfBirth AS dateOfBirth,
        ifnull(a.admissionNumber, '') AS admissionNumber,
        ifnull(s.registerNumber, '') AS registerNumber,
        ifnull(p.fatherName, '') AS fatherName,
        ifnull(p.motherName, '') AS motherName,
        ifnull(d.streetName, '') AS streetName,
        ifnull(d.city, '') AS city,
        ifnull(d.postalCode, '') AS postalCode,
        ifnull(p.studentEmail, '') AS studentEmail,
        ifnull(p.mobileNumber, '') AS mobileNumber,
        ifnull(p.phoneNumber, '') AS phoneNumber,
        s.custId AS custId,
        s.academicYearId AS academicYearId,
        ifnull(ac.academicYear, '') AS academicYear,
        s.classSectionId AS classSectionId,
        p.height AS height,
        p.weight AS weight,
        ifnull(p.visionL, '') AS visionL,
        ifnull(p.visionR, '') AS visionR,
        ifnull(p.teeth, '') AS teeth,
        ifnull(p.oralHygiene, '') AS oralHygiene,
        ifnull(ucase(p.bloodGroup), '') AS bloodGroup,
        ifnull(cus.schoolCode, '') AS schoolCode,
        ifnull(cus.organization, '') AS organizationName,
        d.addressLine1 AS custAddressLine1,
        d.streetName AS custStreetName,
        d.city AS custCity,
        d.postalCode AS custPostalCode,
        ui.name AS imageName,
        ui.path AS imagePath,
        ui.thumbNail AS thumbNail,
        s.description AS studDiscontinueDesc,
        ac.nextAcademicStartDate AS schoolReOpenDate,
        ifnull(p.parentEmail, '') AS parentEmail,
        a.id AS accountId,
        s.goals AS goals,
        s.strengths AS strengths,
        s.interestsAndHobbies AS interestsAndHobbies,
        s.responsibilities AS responsibilities,
        s.achievements AS achievements
    from
        ((((((((student s
        left join studyClass c ON ((c.id = s.classSectionId)))
        left join class cl ON ((cl.id = c.classNameClassId)))
        left join Account a ON ((a.id = s.accountId)))
        left join Person p ON ((p.id = a.personId)))
        left join Address d ON ((d.id = a.paddressId)))
        left join UserImage ui ON ((ui.id = s.imageId)))
        left join academicYear ac ON ((ac.id = s.academicYearId)))
        left join customer cus ON ((cus.id = s.custId)));

-- For validating student fee payment details. If fee not configured for students categories this view will return nothing  

CREATE OR REPLACE VIEW vw_studentFeePaidAndNotPaidDetails AS 
select payment_SequanceId() AS `id`,s.id AS studId,p.firstName AS firstName,p.lastName AS lastName,a.admissionNumber AS admissionNumber,s.custId AS custId,s.academicYearId AS academicYearId,sc.classNameClassId AS classId,s.rollNumber AS rollNumber,
s.accountId AS accountId,s.status AS studentStatus,s.description AS studDiscontinueDesc,s.joinedThroughAdmissions AS joinedThroughAdmissions,ifnull(f.id, 0) AS feeId,ifnull(f.feeAmount, 0) AS feeAmount,
f.feeTypeId AS feeTypeId,f.schoolTermId AS schoolTermId,c.id AS categoryId,ifnull(sfp.paymentStatus, 'N') AS paymentStatus,ifnull(sp.paidAmount, 0) AS paidAmount,ifnull(sp.discountAmount, 0) AS discountAmount,
c.categoryName AS categoryName,if((((s.transportMode = 'O')and (ft.feeType = 'Transport Fee')) or ((s.transportMode = 'p') and (ft.feeType = 'Transport Fee'))),'N', ft.feeType) AS feeType,t.termName AS termName,
t.applToNewStuds AS applToNewStuds,IFNULL(sfp.id, 0) AS paymentId,s.registerNumber AS registerNumber,t.dueDate AS dueDate,sc.className AS className,sc.section AS section,sc.id AS classSectionId, t.fromDate, t.toDate, sft.settingName AS feeSettingName, IFNULL(sfp.paymentAmount, 0) AS paymentAmount, sft.id AS feeSettingId
from
        student s
        join Account a ON (a.id = s.accountId)
        left join Person p ON (p.id = a.personId)
        left join studyClass sc ON (sc.id = s.classSectionId)
        left join Fee f ON (f.classId = sc.classNameClassId
            and f.academicYearId = s.academicYearId
            and f.feeAmount <> '0')
        left join feeType ft ON (f.feeTypeId = ft.id
            and ft.custId = f.custId)
        left join schoolFeeSetting sft ON (sft.id = ft.feeSettingId)
        join schoolTerms t ON (t.id = f.schoolTermId AND 
    IF(s.joinedThroughAdmissions = 'N',t.applToNewStuds = 'N',true) AND 
(IF(s.TransportMode='T',f.RouteBoardingPointId=s.BoardingpointId OR sc.classNameClassId=f.classId,
                sft.id<>3 AND sc.classNameClassId=f.classId)) AND
 (IF(s.HostelMode='H',sc.classNameClassId=f.classId,sft.id<>4 AND IF(s.TransportMode<>'T',sc.classNameClassId=f.classId,TRUE)))
)
        left join schoolCategory c ON (f.categoryId = c.id
            and f.custId = c.custId and f.categoryId = s.categoryId)
        left join studentFeePaidDetails sfp ON (s.id = sfp.studentId
            and f.id = sfp.feeId
            and sfp.deleteStatus = 'N')
        left join studentPayment sp ON (sp.id = sfp.studentPaymentId);

CREATE OR REPLACE VIEW vw_studyCertificateForStudentFeePaidAndNotPaidDetails AS 
select payment_SequanceId() AS `id`,s.id AS studId,p.firstName AS firstName,p.lastName AS lastName,a.admissionNumber AS admissionNumber,s.custId AS custId,s.academicYearId AS academicYearId,sc.classNameClassId AS classId,s.rollNumber AS rollNumber,
s.accountId AS accountId,s.status AS studentStatus,s.description AS studDiscontinueDesc,s.joinedThroughAdmissions AS joinedThroughAdmissions,ifnull(f.id, 0) AS feeId,ifnull(f.feeAmount, 0) AS feeAmount,
f.feeTypeId AS feeTypeId,f.schoolTermId AS schoolTermId,f.categoryId AS categoryId,ifnull(sfp.paymentStatus, 'N') AS paymentStatus,ifnull(sp.paidAmount, 0) AS paidAmount,ifnull(sp.discountAmount, 0) AS discountAmount,
c.categoryName AS categoryName,if((((s.transportMode = 'O')and (ft.feeType = 'Transport Fee')) or ((s.transportMode = 'p') and (ft.feeType = 'Transport Fee'))),'N', ft.feeType) AS feeType,t.termName AS termName,
t.applToNewStuds AS applToNewStuds,IFNULL(sfp.id, 0) AS paymentId,s.registerNumber AS registerNumber,t.dueDate AS dueDate,sc.className AS className,sc.section AS section,sc.id AS classSectionId
from student s 
JOIN Account a ON(a.id = s.accountId)
LEFT JOIN Person p ON(p.Id = a.personId)
LEFT JOIN studyClass sc ON(sc.id = s.classSectionId)
LEFT JOIN Fee f ON(f.classId = sc.classNameClassId and f.academicYearId = s.academicYearId and f.feeAmount <> '0')
LEFT JOIN schoolTerms t ON(t.id = f.schoolTermId and if(s.joinedThroughAdmissions = 'N', f.categoryId = s.categoryId and t.applToNewStuds = 'N', f.categoryId = s.categoryId))
left join feeType ft ON (f.feeTypeId = ft.id and ft.custId = f.custId)
left join schoolCategory c ON (f.categoryId = c.id and f.custId = c.custId)
left join studentFeePaidDetails sfp ON (s.id = sfp.studentId and f.id = sfp.feeId and sfp.deleteStatus = 'N')
left join studentPayment sp ON (sp.id = sfp.studentPaymentId);


CREATE OR REPLACE VIEW vw_combinedClassSubjectDetails AS 
select ccs.id AS combinedClassDetailId,csec.classSectionId AS classSectionId,ccs.studySubjectId AS studySubjectId,ifnull(ccteacher.teacherId, 0) AS teacherId,ccs.custId AS custId,
sc.className AS className,sc.section AS section,ccs.academicYearId AS academicYearId,sc.classNameClassId AS classId,ss.name AS subjectName,p.firstName AS firstName,p.lastName AS lastName,s.status AS status,
concat(if(isnull(sc.className),'',sc.className),if((isnull(sc.section) or (sc.section <> '')),'',concat(' - ', sc.section))) AS classAndSection
from combinedClassSubjects ccs left join combinedClassSections csec ON (ccs.id = csec.combinedClassSubjectId)
left join combinedClassSubjectTeachers ccteacher ON (ccteacher.combinedClassSubjectId = ccs.id)
left join studyClass sc ON (csec.classSectionId = sc.id)
left join studySubject ss ON (ccs.studySubjectId = ss.id)
left join staff s ON(s.id = ccteacher.teacherId)
left join Account a ON(a.id = s.accountId)
left join Person p ON(p.id = a.personId);


CREATE OR REPLACE VIEW vw_StudentDailyAttendance AS
SELECT
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as StudentName,s.rollNumber,s.classSectionId,
s.id as studentId,s.accountId,s.custId,p.parentEmail,p.fatherName,cl.ClassName,c.section,
trim(trailing ' - ' from concat(ifnull(c.ClassName, ''), ' - ', ifnull(c.Section, ''))) AS classNameAndSection,
s.academicYearId,s.classNameClassId,
sda.present AS present,
	sda.attendanceDate AS attendanceDate,
	month(sda.attendanceDate) AS month,
    monthname(sda.attendanceDate) AS monthName,
	sda.id AS studentAttendanceId,sda.afternoonSession AS afternoonSession,sda.leaveSessionType,
a.admissionNumber AS admissionNumber,
p.mobileNumber AS mobileNumber,year(sda.attendanceDate) AS year
FROM
	student s
   	JOIN
	Account a ON (s.accountId = a.id)
   	LEFT JOIN
	Person p ON (a.personId = p.id)
   	LEFT JOIN
	studyClass c ON (c.id = s.classSectionId)
   	LEFT JOIN
	class cl ON (cl.id = s.classNameClassId)
	RIGHT JOIN
	studentDailyAttendance sda on s.Id= sda.studentid;

CREATE OR REPLACE VIEW vw_StudentMonthlyAttendance AS 
select  sma.id AS attendanceId,concat(p.firstName, ' ', ifnull(p.lastName, '')) AS StudentName,s.classSectionId AS classSectionId,s.id AS studentId,s.accountId AS accountId,s.custId AS custId,s.academicYearId AS academicYearId,
p.parentEmail AS parentEmail,p.fatherName AS fatherName,concat(if(isnull(c.className), '', c.className), if((isnull(c.section) or (c.section = '')), '',concat(' - ', c.section))) AS classNameAndSection,sma.month AS month,
sma.monthName AS monthName,sma.totalWorkingDays AS totalWorkingDays,sma.noOfPresentDays AS noOfPresentDays,a.admissionNumber,s.description
from student s join Account a ON(a.id = s.accountId)
left join Person p ON(p.id = a.personId)
left join studyClass c ON (c.id = s.classSectionId)
join studentMonthlyAttendance sma ON (s.id = sma.studentId);

        
   CREATE or replace view vw_staffMonthlyAttendance AS	
   	select
	ifnull(sma.id, 0) AS attendanceId,
	concat(p.firstName, ' ', ifnull(p.lastName, '')) AS staffName,
	a.id AS accountId,
	a.custId AS custId,
	ay.id AS academicYearId,
	sma.month AS month,
	sma.monthName AS monthName,
	ifnull(sma.totalWorkingDays, 0) AS totalWorkingDays,
	ifnull(sma.noOfPresentDays, 0) AS noOfPresentDays,
	ur.roleName AS rolename,
	s.status AS status,
	ur.roleDescription as roleDescription,
	ay.status AS academicYearStatus
   
	from
	staff s
	join Account a ON s.accountId = a.id
	join Person p ON a.personId = p.id
	left join Address d ON a.paddressId = d.id
	left join Address td ON a.taddressId = td.id
	left join vw_userRoles ur ON a.id = ur.accountId
	left join academicYear ay ON s.academicYearId = ay.id
	left join staffMonthlyAttendance sma ON (a.id = sma.accountId) and (ay.id = sma.academicYearId);   
           
	
CREATE or REPLACE view vw_StaffDailyAttendance AS select
	concat(p.firstName, ' ', ifnull(p.lastName,'')) AS staffName,
	a.id AS accountId,
	a.custId AS custId,
	r.description AS roleName,
 	r.id AS roleId,
	s.academicYearId AS academicYearId,
	sda.present AS present,
	sda.afternoonSession AS afternoonSession,
	sda.leaveRequest AS leaveRequest,
	sda.leaveType AS leaveType,
	sda.attendanceDate AS attendanceDate,
	ifnull(sda.inTime,0) AS inTime,
	ifnull(sda.outTime,0) AS outTime,
	ifnull(sda.workingHours,0) AS workingHours,
	month(sda.attendanceDate) AS month,
	monthname(sda.attendanceDate) AS monthName,
	s.id as staffId
from staff s
   	join Account a ON a.id = s.accountId
   	left join Person p ON p.id = a.personId
    left join UserRoles ur ON ur.userId = a.id
   	left join Role r ON r.id = ur.roleId
   	left join staffDailyAttendance sda ON (sda.accountId = a.id);
   	
   	
   	
   	
   		
CREATE or REPLACE view vw_StaffDailyAttendanceDetails AS select
	concat(p.firstName, ' ', ifnull(p.lastName,'')) AS staffName,
	a.id AS accountId,
	a.custId AS custId,
	r.description AS roleName,
 	r.id AS roleId,
	s.academicYearId AS academicYearId,
	sda.present AS present,
	sda.afternoonSession AS afternoonSession,
	sda.leaveSessionType,
	sda.leaveRequest AS leaveRequest,
	sda.leaveType AS leaveType,
	sda.attendanceDate AS attendanceDate,
	ifnull(sda.inTime,0) AS inTime,
	ifnull(sda.outTime,0) AS outTime,
	ifnull(sda.workingHours,0) AS workingHours,
	month(sda.attendanceDate) AS month,
	monthname(sda.attendanceDate) AS monthName
from staffDailyAttendance sda 
    left join Account a ON a.id = sda.accountId
    left join Person p ON p.id = a.personId
    left join staff s ON s.accountId = a.id
	left join Address d ON a.paddressId = d.id
	left join Address td ON a.taddressId = td.id
    left join UserRoles ur on ur.userId = a.id
	left join Role r on r.id = ur.roleId;	 
	
 
	
CREATE or replace view vw_staffmonthlyattendanceDetails AS	
   	select
	ifnull(sma.id, 0) AS attendanceId,
	concat(p.firstName, ' ', ifnull(p.lastName, '')) AS staffName,
	a.id AS accountId,
	a.custId AS custId,
	sma.academicYearId,
	sma.month AS month,
	sma.monthName AS monthName,
	ifnull(sma.totalWorkingDays, 0) AS totalWorkingDays,
	ifnull(sma.noOfPresentDays, 0) AS noOfPresentDays,
	r.name AS rolename,
	s.status AS status,
	r.description as roleDescription 
   
	from staffMonthlyAttendance sma 
    left join Account a ON a.id = sma.accountId
    left join Person p ON p.id = a.personId
    left join staff s ON s.accountId = a.id
	left join Address d ON a.paddressId = d.id
	left join Address td ON a.taddressId = td.id
    left join UserRoles ur on ur.userId = a.id
	left join Role r on r.id = ur.roleId;	 
    
	
    
CREATE OR REPLACE SQL SECURITY DEFINER VIEW vw_customeraddress AS 
select c.id AS custId,c.custEmail AS custEmail,c.organization AS organization,c.orgnizationTypeId AS orgnizationTypeId,c.organizationSubTypeId AS organizationSubTypeId,
ad.districtId AS districtId,ad.mandalId AS mandalId,ad.stateId AS stateId,ad.villageId AS villageId,s.stateCode AS stateCode,s.stateName AS stateName 
from customer c left join Account u on(u.custId = c.id) left join UserRoles ur on(ur.userId = u.id) 
left join Role r on(r.id = ur.roleId) left join Address ad on(u.paddressId = ad.id) 
left join State s on(s.id = ad.stateId)  group by c.Id;



CREATE OR REPLACE VIEW vw_vehiclesWithExpiryDates AS select 
    v.id AS id,
    a.id AS academicYearId,
    v.custId AS custId,
    v.insuranceExpiredDate AS insuranceExpiredDate,
    v.insuranceNumber AS insuranceNumber,
    v.vehicleNumber AS vehicleNumber,
    v.fitnessExpiryDate AS fitnessExpiryDate,
    DATEDIFF(v.fitnessExpiryDate,curdate()) as fitnessDays,
    v.permitExpiryDate AS permitExpiryDate,
    DATEDIFF( v.permitExpiryDate,curdate()) as permitDays,
    v.pollutionExpiryDate AS pollutionExpiryDate,
    DATEDIFF(v.pollutionExpiryDate,curdate()) as pollutionDays,
    v.roadTaxNextPaymentDate AS roadTaxNextPaymentDate,
    DATEDIFF(v.roadTaxNextPaymentDate,curdate()) as roadTaxDays   
from
    (vehicles v
    join academicYear a ON ((a.custId = v.custId)));
    
CREATE OR REPLACE  VIEW vw_vehiclesWithExpiryDatesInformation AS select 
    v.id AS id,
    va.academicYearId,    
    v.custId AS custId,        
    v.insuranceNumber AS insuranceNumber,
    va.status AS status,
    v.vehicleNumber AS vehicleNumber, 
    va.name AS name,   
    v.fitnessExpiryDate AS fitnessExpiryDate,
    DATEDIFF(IFNULL(v.fitnessExpiryDate,curdate()),curdate()) as fitnessDays,
    v.permitExpiryDate AS permitExpiryDate,
    DATEDIFF(IFNULL(v.permitExpiryDate,curdate()),curdate()) as permitDays,
    v.pollutionExpiryDate AS pollutionExpiryDate,
    DATEDIFF(IFNULL(v.pollutionExpiryDate,curdate()),curdate()) as pollutionDays,
    v.roadTaxNextPaymentDate AS roadTaxNextPaymentDate,
    DATEDIFF(IFNULL(v.roadTaxNextPaymentDate,curdate()),curdate()) as roadTaxDays,
    v.insuranceExpiredDate AS insuranceExpiredDate,
    DATEDIFF(IFNULL(v.insuranceExpiredDate,curdate()),curdate()) as insuranceDays,
    va.id as vehicleAcademicDetailId  
from
     vehicles v LEFT JOIN vehiclesAcademicDetails va on (va.vehicleId= v.id) 
     where v.fitnessExpiryDate is not null or v.permitExpiryDate is not null or v.pollutionExpiryDate is not null or v.roadTaxNextPaymentDate is not null
     or v.insuranceExpiredDate is not null;



CREATE OR REPLACE VIEW vw_studentDetailsByDays AS 
SELECT DISTINCT s.accountId AS accountId, p.mobileNumber AS mobileNumber,p.parentEmail AS parentEmail,concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')),'',concat(' ', p.lastName))) AS fullName,
s.custId AS custId,s.academicYearId AS academicYearId,p.secondaryMobileNumber as secondaryMobileNumber,p.anotherMobileNumber as anotherMobileNumber,p.anotherSecondaryMobileNumber as anotherSecondaryMobileNumber,p.addressType as addressType
FROM student s 
LEFT JOIN Account a ON(a.id = s.accountId) 
LEFT JOIN Person p ON(p.id = a.personId) where s.status = 'Y' and DATE_FORMAT(curdate(),'%c-%d') = DATE_FORMAT(p.dateOfBirth, '%c-%d');

    
CREATE OR REPLACE VIEW vw_classAssignmentDetails AS 
select c.id AS assignmentId,cast(c.assignmentDate as date) AS assignmentDate,(case when(isnull(c.description)or (c.description = '')) then 'N/A' else c.description end) AS assignmentDescription,c.createdById,
c.classSectionId AS classSectionId,ifnull(c.subjectId, 0) AS subjectId,c.status AS status ,IFNULL(ss.Name, 'Assignment') AS subjectName,concat(sc.className, ' - ', sc.section) AS classAndSection,c.isDocsUpload,
sc.academicYearId AS academicYearId,sc.custId AS custId,sc.className AS className,sc.classNameClassId AS classId,concat(p.firstName, ' ', p.lastName) AS createdBy,
CASE  c.status
WHEN 'P' THEN 'I'
WHEN 'A' THEN(if((select count(*) from studentClassAssignment sca where (c.id = sca.assignmentId)) >0,'I',  'C') ) 
ELSE 'I' END as assignmentStatus
from classAssignment c left join studySubject ss ON(ss.id = c.subjectId)
left join studyClass sc ON(sc.id = c.classSectionId)
left join Account a ON (a.id = c.createdById)
left join Person p ON (a.personId = p.id);
    
CREATE OR REPLACE VIEW vw_StudentClassAssignment AS 
select concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')), '', concat(' ', p.lastName))) AS StudentName,s.rollNumber AS rollNumber,s.classSectionId AS classSectionId,
s.id AS studentId,s.accountId AS accountId,s.custId AS custId,s.academicYearId AS academicYearId,sca.assignmentDone AS assignmentDone,sca.assignmentDate AS assignmentDate,ss.name AS subjectName,ca.isDocsUpload,
sca.assignmentId AS assignmentId,sca.id AS studentAssignmentId,if(isnull(sc.section), sc.className, concat(sc.className, ' ', sc.section)) AS classAndSection,p.mobileNumber AS mobileNumber,s.status AS status
from student s 
join Account a ON (s.accountId = a.id)
left join Person p ON (a.personId = p.id)
join studentClassAssignment sca ON(sca.studentId = s.id)
left join classAssignment ca ON(ca.id = sca.assignmentId)
left join studySubject ss ON(ss.id = ca.subjectId)
left join ClassSubject cs ON(cs.subjectId = ss.id)
left join studyClass sc ON(sc.id = cs.studyClassId) group by s.id;


CREATE OR REPLACE VIEW vw_studentFineFeeDetails AS 
select distinct s.accountId AS accountId,s.id AS studentId,s.classNameClassId AS classNameClassId,p.mobileNumber AS mobileNumber,a.admissionNumber AS admissionNumber,acy.academicYear AS academicYear,
d.streetName AS streetName,d.city AS city,d.postalCode AS postalCode,concat(if(isnull(c.className), '', c.className), if((isnull(c.section) or (c.section = '')), '',concat(' - ', c.section))) AS classAndSection,
s.rollNumber AS rollNumber,concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')), '',concat(' ', p.lastName))) AS studentFullName,ff.paymentDate AS paymentDate,
ff.description AS description,ff.invoiceNumber AS invoiceNumber,ff.fineFeeAmount AS fineFeeAmount,ff.custId AS custId,ff.academicYearId AS academicYearId,p.parentEmail AS parentEmail,p.fatherName AS fatherName,
s.status AS status,ff.id As fineFeeId,CONCAT(IF(pa.gender='M','Mr. ',''),IF(pa.gender='F','Ms. ',''),IF(pa.firstName IS NULL,'',pa.firstName), IF(pa.lastName IS NULL || pa.lastName  = '','',CONCAT(' ',pa.lastName))) as personFullName,
ff.quantity,ff.deleteStatus,s.classSectionId
from student s join fineFee ff ON (s.id = ff.studentId)
left join Account a ON(a.id = s.accountId)
left join Person p ON(p.id = a.personId)
left join Address d ON (a.paddressId = d.id)
left join studyClass c ON (c.id = s.classSectionId)
left join Account ac ON (ff.lastUpdatedById = ac.id)
LEFT JOIN Person pa ON (ac.personId = pa.id)
left join academicYear acy ON (acy.id = s.academicYearId);

   
   /*Hall Ticket*/ 
CREATE OR REPLACE VIEW vw_studentsHallTicketDetails AS 
select 
        s.id AS studId,
        s.status AS status,
        s.accountId AS accountId,
        s.rollNumber AS rollNumber,
        IFNULL(s.registerNumber, '') as registerNumber,
        es.academicYearId AS academicYearId,
        es.custId AS custId,
        cs.id AS classId,
        sc.id AS classSectionId,
        ss.name AS name,
        es.classSubjectId AS classSubjectId,
        sc.className AS className,
        c.organization AS organization,
        sc.section AS section,
        es.examDate AS examDate,
        es.startDate AS startDate,
        es.endDate AS endDate,
        es.startTime AS startTime,
        es.endTime AS endTime,
        et.examType AS examType,
        st.subTypeName AS subTypeName,
        acc.admissionNumber AS admissionNumber,
        pe.firstName AS firstName,
        pe.fatherName,
        pe.motherName,
        pe.dateOfBirth AS dateOfBirth,
        pe.lastName AS lastName,
        ad.addressLine1 AS custAddressLine1,
        ad.city AS custCity,
        ad.postalCode AS custPostalCode,
        ad.streetName AS custStreetName,
        ui.name AS imageName,
        ui.path AS imagePath,
        ui.thumbNail AS thumbNail,
        es.subTypeId as subTypeId,
        es.id as examId,
        upi.path AS principalDigitalSignaturePath
    from
        examSchedules es
        left join studyClass sc ON (sc.id = es.classSectionId)
        left join class cs ON (sc.classNameClassId = cs.id)
        left join studySubject ss ON (ss.id = es.classSubjectId)
        left join examTypes et ON (et.id = es.examTypeId)
        left join subType st ON (st.id = es.subTypeId)
        join student s ON (es.classSectionId = s.classSectionId)
        left join customer c ON (c.id = s.custId)
        left join Account acc ON (acc.id = s.accountId)
        left join Person pe ON (pe.id = acc.personId)
        left join Address ad ON (ad.id = c.addressId)
        left join UserImage ui ON (ui.id = s.imageId)
        left join UserImage upi ON (upi.id = c.principalDigitalSignatureId);



create
or replace view vw_studentClassFeePaymentDetails as
SELECT 
	payment_SequanceId() AS id,
    IFNULL(cf.studentId, 0) as studentId,cf.feeId,IFNULL(cf.feeSettingId, 0) as feeSettingId,cf.settingName,cf.settingType,cf.schoolTermId,cf.feeTypeId,
    IFNULL(cf.classId, 0) as classId,cf.academicYearId,cf.custId,cf.categoryId,IFNULL(cf.feeAmount, 0) as feeAmount,cf.fromdate,cf.toDate,cf.fromMonthName,
    cf.toMonthName,cf.termName,cf.dueDate,cf.categoryName,cf.rollNumber,cf.status,cf.description,a.username,p.firstName,p.lastName,p.middleName,p.mobileNumber,
    p.phoneNumber,p.parentEmail,cf.feeType,cf.classSectionId,cf.routeBoardingPointId,
    IFNULL(sfp.id, 0) paymentId,IFNULL(sfp.studentPaymentId, 0) studentPaymentId,sfp.lastUpdatedDate,IFNULL(sfp.paymentAmount, 0) as paymentAmount,
    IFNULL(sfp.paymentStatus, 'N') as paymentStatus,IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sfp.deleteStatus, 'N') as deleteStatus
FROM vw_studentClassFees cf LEFT JOIN studentFeePaidDetails sfp ON (cf.feeId = sfp.feeId and cf.studentid = sfp.studentId and cf.custId=sfp.custId and sfp.deleteStatus = 'N')
        left join Account a ON (cf.accountId = a.id) LEFT JOIN Person p ON (a.personId = p.id) LEFT JOIN Address d ON (a.paddressId = d.id);

create or replace view vw_studentFeePaymentDetails as SELECT 
    payment_SequanceId() AS id,IFNULL(sp.id, 0) as studentPaymentId,IFNULL(sp.paidAmount, 0) as paidAmount,IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sp.transactionNumber,0) as transactionNumber,IFNULL(sp.bankId, 0) as bankId,
    sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,sp.ddNumber,bm.bankName,sp.chequeNumber,IFNULL(sp.invoiceNumber, 0) as invoiceNumber,IFNULL(sp.fineAmount,0) as fineAmount,
    IFNULL(sp.deleteStatus, 'N') as deleteStatus,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,sp.paymentDate,IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,
    cf.feeId,IFNULL(cf.feeAmount, 0) as feeAmount,cf.custId,cf.feeTypeId,cf.schoolTermId,IFNULL(c.id,0) as classId,cf.academicYearId,c.sortingOrder,
    if(((s.transportMode = 'O' and cf.settingName = 'Transport Fee') or (s.transportMode = 'p' and cf.settingName = 'Transport Fee') or (s.hostelMode='D' and cf.settingName = 'Hostel Fee')),'N',cf.feeType) as feeType,
    cf.fromdate,cf.toDate,cf.feeSettingId,cf.settingName,cf.settingType,cf.applToNewStuds,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.dueDate2,cf.dueDate1,cf.categoryId,cf.categoryName,
    cf.routeBoardingPointId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,IFNULL(s.vehicleAcademicDetailsId,0)as vehicleAcademicDetailsId,s.hostelMode,s.committedFee,
    s.boardingPointId,sc.className,sc.section,sc.id as classSectionId,rb.routeId,a.username,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
    p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,cf.committedFeeStatus,cf.priorityPosition,
    IFNULL(sfp.committedFeeStatus,'N') as paymentCommitFeeStatus,sp.createdById as financeUserId,IFNULL(sfc.id,0) as concessionId,IFNULL(sfc.concessionAmount,0) as concessionAmount,IFNULL(sfp.concessionAmount,0) as paymentConcessionAmount,
    sp.invoiceString AS invoiceString,sp.desktopReceiptNumber,s.feePaidStatus,s.feeConfigured,IFNULL(sp.totalBalanceAmount,0) as totalBalanceAmount,IFNULL(sp.termwiseTotalBalanceAmount,0) as termwiseTotalBalanceAmount,
CONCAT(IF(pa.gender='M','Mr.',IF(pa.gender='F',if(pa.maritalStatus='M','Mrs.','Ms.') ,'')),IF(pa.firstName IS NULL,'',pa.firstName),IF(pa.lastName IS NULL || pa.lastName  = '','',CONCAT(' ',pa.lastName))) as personFullName
    
FROM vw_classFeeDetails cf JOIN student s ON 
(cf.custId = s.custId and cf.academicYearId = s.academicYearId and cf.categoryId = s.categoryId and 
IF(s.joinedThroughAdmissions = 'N',cf.applToNewStuds = 'N',true) AND 
    IF(s.joinedThroughAdmissions = 'N',cf.applToNewStuds = 'N',true) AND
        (IF(s.TransportMode='T',cf.RouteBoardingPointId=s.BoardingpointId OR s.classSectionId=cf.classSectionId,
                cf.feeSettingId<>3 AND s.classSectionId=cf.classSectionId )) AND
        (IF(s.HostelMode='H',s.classSectionId=cf.classSectionId,cf.feeSettingId<>4 AND IF(s.TransportMode<>'T',s.classSectionId=cf.classSectionId,TRUE))
        ))
    left join class c ON (c.id = cf.classId)
    left join studentFeePaidDetails sfp ON (s.id = sfp.studentId and cf.feeId = sfp.feeId and s.custId = sfp.custId and sfp.deleteStatus = 'N') -- OR sfp.deleteStatus = 'C'
    left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId = sfp.studentId  and sp.deleteStatus = 'N') -- OR sp.deleteStatus = 'C'
    left join studentFeeConcession sfc ON (s.id=sfc.studentId and cf.feeId=sfc.feeId)
    left join Account a ON (s.accountId = a.id)
    left join bankMaster bm ON (sp.bankId = bm.id)
    LEFT JOIN Person p ON (a.personId = p.id)
    left join Account ac ON (sp.lastUpdatedById = ac.id)
    LEFT JOIN Person pa ON (ac.personId = pa.id)
    LEFT JOIN studyClass sc ON (s.classSectionId=sc.id)
    left join routeBoardingPoints rb ON (rb.id = s.boardingPointId and rb.custId = s.custId and rb.academicYearId = s.academicYearId)
where p.firstName <> '' and feeSettingId !=3;

-- Used for displaying student fee payment  
CREATE OR REPLACE VIEW vw_studentFeePaidDetails as
SELECT sfp.id feePaidDetailsId,sfp.custId,sfp.deleteStatus,sfp.discountAmount,sfp.paymentAmount,
sfp.paymentStatus,sfp.studentId,sfp.studentPaymentId,sfp.feeId,sfp.futureFeeStatus,f.routeBoardingPointId
FROM studentFeePaidDetails sfp left join Fee f ON (f.id = sfp.feeId)

where sfp.deleteStatus='N' order by sfp.studentId,sfp.paymentStatus;

-- Used for displaying student fee payment 

CREATE OR REPLACE VIEW vw_studentParticularwiseFeePayments AS 
select sfp.feePaidDetailsId AS feePaidDetailsId,sfp.custId AS custId,sfp.deleteStatus AS deleteStatus,sum(sfp.discountAmount) AS discountAmount,sum(sfp.paymentAmount) AS paymentAmount,sfp.paymentStatus AS paymentStatus,
sfp.studentId AS studentId,sfp.studentPaymentId AS studentPaymentId,sfp.feeId AS feeId,sfp.futureFeeStatus AS futureFeeStatus
from studentFeePaidDetails sfp where sfp.deleteStatus='N' group by sfp.studentId , sfp.feeId;

-- Used for displaying student fee payment   
create or replace view vw_studentFeeParticularsPayment as
SELECT payment_SequanceId() AS id,IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sfp.deleteStatus, 'N') as deleteStatus,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,
IFNULL(sfp.feePaidDetailsId,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,cf.feeId,IFNULL(cf.feeAmount, 0) as feeAmount,cf.custId,
cf.feeTypeId,cf.schoolTermId,IFNULL(cf.classId,0) as classId,cf.academicYearId,c.sortingOrder,
if(((s.transportMode = 'O' and cf.settingName = 'Transport Fee') or (s.transportMode = 'p' and cf.settingName = 'Transport Fee') or (s.hostelMode='D' and cf.settingName = 'Hostel Fee')),'N',cf.feeType) as feeType,
cf.fromdate,cf.toDate,cf.feeSettingId,cf.settingName,cf.settingType,cf.applToNewStuds,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.dueDate2,cf.dueDate1,cf.categoryId,cf.categoryName,
cf.routeBoardingPointId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,IFNULL(s.vehicleAcademicDetailsId,0)as vehicleAcademicDetailsId,s.hostelMode,
s.boardingPointId,cf.className,cf.section,cf.classSectionId,rb.routeId,a.username,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,p.phoneNumber,p.parentEmail,s.registerNumber,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,IFNULL(sfc.concessionAmount, 0) as concessionAmount
FROM vw_classFeeDetails cf JOIN student s ON (cf.custId = s.custId and cf.academicYearId = s.academicYearId and cf.categoryId = s.categoryId and 
IF(s.joinedThroughAdmissions = 'N',cf.applToNewStuds = 'N',true) AND  IF(s.joinedThroughAdmissions = 'N',cf.applToNewStuds = 'N',true) AND
        (IF(s.TransportMode='T',cf.RouteBoardingPointId=s.BoardingpointId OR s.classSectionId=cf.classSectionId,
                cf.feeSettingId<>3 AND s.classSectionId=cf.classSectionId )) AND
        (IF(s.HostelMode='H',s.classSectionId=cf.classSectionId,cf.feeSettingId<>4 AND IF(s.TransportMode<>'T',s.classSectionId=cf.classSectionId,TRUE)))
)
left join class c on (c.id=cf.classId) 
left join vw_studentParticularwiseFeePayments sfp ON (s.id = sfp.studentId and cf.feeId = sfp.feeId and s.custId=sfp.custId and sfp.deleteStatus = 'N')
left join Account a ON (s.accountId = a.id)
LEFT JOIN Person p ON (a.personId = p.id)
left join studentFeeConcession sfc ON (s.id=sfc.studentId and cf.feeId=sfc.feeId)
left join routeBoardingPoints rb ON (rb.id = s.boardingPointId and rb.custId=s.custId and rb.academicYearId=s.academicYearId);
 



  CREATE 
   OR REPLACE VIEW vw_AcademicYearTimings AS
    select 
        ats.id AS academicYearTimingId,
        ats.academicYearId AS academicYearId,
        ats.startTime AS startTime,
        ats.endTime AS endTime,
        ats.morningBreakStartTime AS morningBreakStartTime,
        ats.morningBreakEndTime AS morningBreakEndTime,
        ats.lunchStartTime AS lunchStartTime,
        ats.lunchEndTime AS lunchEndTime,
        ats.eveningBreakStartTime AS eveningBreakStartTime,
        ats.eveningBreakEndTime AS eveningBreakEndTime,
        ats.classId AS classId,
        ats.weekDay AS weekDay,
        c.className AS className,
        d.dayName AS weekName,
        ats.status 
    from
        (((academicYearTimings ats
        left join academicYear a ON ((a.id = ats.academicYearId)))
        left join class c ON ((c.id = ats.classId)))
        left join weekDays d ON ((d.id = ats.weekDay)));
        
CREATE  or replace VIEW vw_studentCountByBoardingPointId AS 
select count(0) AS count,
	v.id AS vehicleId,
	va.id AS vehicleAcademicDetailsId,
	s.custId AS custId,
	r.id AS routeId,
	s.boardingPointId AS boardingPointId,
	s.academicYearId AS academicYearId,
	br.boardingPointName AS boardingPointName
from
	student s left join vehiclesAcademicDetails va ON ((va.id = s.vehicleAcademicDetailsId))
	left join vehicles v ON ((v.id = va.vehicleId))
	left join routeBoardingPoints br ON ((br.id = s.boardingPointId))
	left join route r ON ((br.routeId = r.id))
	where ((v.id > 0) and (br.Id > 0) and (s.transportMode = 'T') and isnull(s.description))
group by br.id,s.academicYearId;
        
/*CREATE OR REPLACE VIEW vw_routeWiseStudents AS 
select r.id AS routeId,r.routeName AS routeName,r.routePointName AS routePointName,r.status AS status,s.academicYearId AS academicYearId,ifnull(s.pickUpboardingpointId,st.dropboardingpointId) AS boardingPointId,
count(distinct(s.studentId)) AS boardingPointWiseStudsCount,br.boardingPointName AS boardingPointName
from route r left join routeBoardingPoints br ON (r.id=br.routeId) 
left join studentTransportDetails s on(br.id = s.pickupBoardingPointId)
left join studentTransportDetails st on(br.id = st.dropBoardingPointId)
left join schoolTerms gg on(gg.id=s.termId) where curDate() between fromDate and toDate and feeSettingId=3
group by r.id,s.academicYearId;*/


CREATE OR REPLACE VIEW vw_routeWiseStudents AS 
select r.id routeId,r.routeName,COUNT(distinct(STD.Id)) boardingPointWiseStudsCount ,
r.routePointName AS routePointName,r.status AS status,STD.academicYearId AS academicYearId,IFNULL(prbp.boardingPointName,drbp.boardingPointName) AS boardingPointName,
IFNULL(prbp.id,drbp.id) AS boardingPointId
from studentTransportDetails STD
left join routeBoardingPoints prbp on(prbp.id = STD.pickupBoardingPointId)
left join routeBoardingPoints drbp on(drbp.id = STD.dropBoardingPointId)
left join schoolTerms st on(st.id=STD.termId) and st.feeSettingId=3 
Left Join route r on r.id=IFNULL(prbp.routeId,drbp.routeId)
Where curDate() between st.fromDate and st.toDate and r.status='Y'
Group BY r.id;

/*Transport module views */

CREATE OR REPLACE VIEW vw_assignedVehiclestoRoutes AS  
SELECT rv.vehicleAcademicId,rv.routeId,va.name,va.academicYearId,r.custId,IFNULL(va.driverId,0) as driverId,IFNULL(va.helperId,0) as helperId,
va.status,IFNULL(va.vehicleId,0) as vehicleId,r.routeName,r.status as routeStatus,IFNULL(v.noOfSeats,0) as noOfSeats
FROM RouteWithVehicles rv LEFT JOIN vehiclesAcademicDetails va on (va.id = rv.vehicleAcademicId) JOIN route r on (r.id=rv.routeId) JOIN vehicles v on (v.id = va.vehicleId);


CREATE OR REPLACE VIEW vw_assignedVehiclestoRoutesWithBoardingPoints AS
SELECT payment_SequanceId() AS id,rv.vehicleAcademicId AS vehicleAcademicId,rv.routeId AS routeId,va.name AS name,va.academicYearId AS academicYearId,r.custId AS custId,ifnull(va.driverId, 0) AS driverId,ifnull(va.helperId, 0) AS helperId,
va.status AS status,ifnull(va.vehicleId, 0) AS vehicleId,r.routeName AS routeName,r.status AS routeStatus,rb.boardingPointName AS boardingPointName,rb.id AS boardingPointId,
	ifnull(v.noOfSeats, 0) AS noOfSeats
FROM RouteWithVehicles rv left join vehiclesAcademicDetails va ON (va.id = rv.vehicleAcademicId)
	LEFT join route r ON (r.id = rv.routeId) 
	left join routeBoardingPoints rb ON (rb.routeId = rv.routeId and rb.academicYearId = va.academicYearId)
	LEFT join vehicles v ON (v.id = va.vehicleId);

	


CREATE OR REPLACE VIEW vw_studentsTransportDetails AS 
select r.id AS routeId,ifnull(st.pickupvehicleId,0) AS pickupvehicleId,ifnull(st.dropvehicleId,0) AS dropvehicleId,ifnull(st.pickupBoardingPointId,0) As pickupBoardingPointId,ifnull(st.dropBoardingPointId,0) As dropBoardingPointId,rb.boardingPointName AS boardingPointName,va.name AS name,va.academicYearId AS academicYearId,r.custId AS custId,ifnull(va.driverId, 0) AS driverId,ifnull(va.helperId, 0) AS helperId,
va.status AS status,ifnull(va.vehicleId, 0) AS vehicleId,r.routeName AS routeName,r.status AS routeStatus,
concat(if(isnull(p.firstName),'',p.firstName),if((isnull(p.lastName)or (p.lastName = '')),'',concat(' ', p.lastName))) AS fullName,s.accountId AS accountId,s.id AS studId,s.classSectionId AS classSectionId,
s.status AS activeStatus,s.rollNumber AS rollNumber,ifnull(a.admissionNumber, '') AS admissionNumber,s.registerNumber AS registerNumber,s.description AS studDiscontinueDesc,p.mobileNumber AS mobileNumber,
p.parentEmail AS parentEmail,c.className AS className,c.section AS section,ifnull(v.noOfSeats, 0) AS noOfSeats,ifnull(cl.sortingOrder, 0) AS classSortingOrder
from studentTransportDetails st 
left join vehiclesAcademicDetails va ON(ifnull(va.id=st.pickupVehicleId,va.id=st.dropvehicleId))
left join student s on(s.id=st.studentId)
left join Account a ON (s.accountId = a.id)
left join Person p ON (a.personId = p.id)
left join studyClass c ON (c.id = s.classSectionId)
left join class cl ON(cl.id = c.classNameClassId)  
join routeBoardingPoints rb ON (ifnull(st.pickupBoardingPointId=rb.id,st.dropBoardingPointId=rb.id))
LEFT join vehicles v ON (v.id = va.vehicleId)
left join route r on(r.id=rb.routeId)
left join schoolTerms t on(t.id=st.termId) where curDate() between t.fromDate and t.toDate;


CREATE OR REPLACE VIEW vw_staffDriverDetails as 
SELECT a.id AS accountId,a.accountExpired AS accountExpired,a.custId AS custId,s.id AS staffId,s.description AS description,ay.id AS academicYearId,
ay.status AS academicYearStatus,s.status AS status,s.staffType AS staffType,ifnull(c.orgnizationTypeId, 0) AS organizationTypeId,p.contractStartDate AS contractStartDate,
p.contractEndDate AS contractEndDate,p.licenseNumber AS licenseNumber,p.licenseExpDate AS licenseExpDate,p.experience AS experience,p.dateOfJoining AS dateofJoining,
p.firstName AS firstName,p.lastName AS lastName,p.middleName AS middleName,ifnull(p.mobileNumber,'N/A') AS mobileNumber,p.phoneNumber AS phoneNumber,p.maritalStatus AS maritalStatus,
p.summary AS summary,p.gender AS gender,
    (CASE
        WHEN (ISNULL(p.designation) or (p.designation = '')) THEN 'No Designation'
        ELSE p.designation
    END) AS designation,IFNULL(ur.roleId, 0) AS roleId,ur.roleName AS roleName,ur.roleDescription AS roleDescription
from staff s JOIN Account a ON ((s.accountId = a.id)) LEFT JOIN Person p ON ((a.personId = p.id))
    JOIN vw_userRoles ur ON (a.id = ur.accountId and ur.roleName='ROLE_DRIVER') JOIN customer c ON ((c.id = s.custId)) LEFT JOIN academicYear ay ON ((ay.custId = c.id)); 
    
 -- If you add any extra fields for the below view please add them as last elements and also add those new fields in TransportDaoHibernate.java method name getAllViewVehicleWithDriverDetails().  
    

CREATE OR REPLACE VIEW vw_vehicleWithDriverDetails AS 
select va.id AS id,va.id AS vehicleAcademicId,va.academicYearId AS academicYearId,v.chasisNumber AS chasisNumber,v.classificationType AS classificationType,v.custId AS custId,va.driverId AS driverId,v.engineNumber AS engineNumber,
va.helperId AS helperId,v.insuranceExpiredDate AS insuranceExpiredDate,v.insuranceNumber AS insuranceNumber,v.noOfSeats AS noOfSeats,v.ownerName AS ownerName,v.insurancePaidDate AS insurancePaidDate,v.vehicleMaker AS vehicleMaker,
v.registrationAuthority AS registrationAuthority,v.insuranceDetails AS insuranceDetails,ifnull(rv.routeId, 0) AS routeId,va.status AS status,v.vehicleNumber AS vehicleNumber,v.vehicleType AS vehicleType,
v.roadTaxAmount AS roadTaxAmount,v.fitnessCheckDate AS fitnessCheckDate,v.fitnessExpiryDate AS fitnessExpiryDate,v.permitCheckedDate AS permitCheckedDate,v.permitExpiryDate AS permitExpiryDate,
v.pollutionCheckedDate AS pollutionCheckedDate,v.pollutionExpiryDate AS pollutionExpiryDate,v.roadTaxNextPaymentDate AS roadTaxNextPaymentDate,v.roadTaxPaidDate AS roadTaxPaidDate,ifnull(va.name, 'None') AS name,
ifnull(v.noOfSeats, 0)- ifnull((SELECT COUNT(instd.pickupVehicleId) _pickupCount FROM studentTransportDetails instd WHERE instd.pickupBoardingPointId in(select id from routeBoardingPoints where routeId=r.id) and instd.pickupvehicleId = va.id and instd.termId = (select sst.id from schoolTerms sst where curDate() between sst.fromDate and sst.toDate and sst.custId=a.custId and sst.feeSettingId=3 order by sst.fromdate limit 1) GROUP BY va.vehicleId),0) as availablePickup,
ifnull(v.noOfSeats, 0)-ifnull((SELECT COUNT(pstd.dropVehicleId) _dropCount FROM studentTransportDetails pstd WHERE pstd.dropBoardingPointId in(select id from routeBoardingPoints where routeId=r.id) and pstd.dropVehicleId = va.id and pstd.termId = (select sst.id from schoolTerms sst where curDate() between sst.fromDate and sst.toDate and sst.custId = a.custId and sst.feeSettingId=3 order by sst.fromdate limit 1) GROUP BY va.vehicleId),0) as availableDrop,
ifnull((SELECT COUNT(instd.pickupVehicleId) _pickupCount FROM studentTransportDetails instd WHERE instd.pickupBoardingPointId in(select id from routeBoardingPoints where routeId=r.id) and instd.pickupvehicleId = va.id and instd.termId = (select sst.id from schoolTerms sst where curDate() between sst.fromDate and sst.toDate and sst.custId=a.custId and sst.feeSettingId=3 order by sst.fromdate limit 1) GROUP BY va.vehicleId),0) as filledPickUp,
ifnull((SELECT COUNT(pstd.dropVehicleId) _dropCount FROM studentTransportDetails pstd WHERE pstd.dropBoardingPointId in(select id from routeBoardingPoints where routeId=r.id) and pstd.dropVehicleId = va.id and pstd.termId = (select sst.id from schoolTerms sst where curDate() between sst.fromDate and sst.toDate and sst.custId = a.custId and sst.feeSettingId=3 order by sst.fromdate limit 1) GROUP BY va.vehicleId),0) as filledDrop,
ifnull(concat(p.firstName, ' ', p.lastName), 'NA') AS driverName,ifnull(p.mobileNumber, '0') AS mobileNumber,
ifnull(r.routeName, 'NA') AS routeName,r.routePointStartTime AS routePointStartTime,r.routePointEndTime AS routePointEndTime,r.routeEndTimeInMns AS routeEndTimeInMns,r.routeStartTimeInMns AS routeStartTimeInMns,
ay.status AS academicYearStatus,v.id AS vehicleId,va.id AS vehicleAcademicDetailId,ifnull(s.accountId, 0) AS accountId
from
	RouteWithVehicles rv
	join vehiclesAcademicDetails va ON (rv.vehicleAcademicId = va.id)
	left join vehicles v ON (v.id = va.vehicleId)
	left join route r ON (r.id = rv.routeId)
	left join routeBoardingPoints rb on(r.id = rb.routeId)
	left join staff s ON(s.accountId = va.driverId and s.academicYearId = va.academicYearId)
	left join Account a ON(a.id = s.accountId)
	left join Person p ON(p.id = a.personId)
	left join academicYear ay ON (ay.custId = s.custId and s.academicYearId = ay.id)
	left join customer c ON(a.custId = c.id ) where c.status = 'Y'
group by v.id, r.id
order by va.name , r.routeName;

CREATE or REPLACE VIEW vw_vehicleMaintenanceByMonth AS select 
    tm.academicYearId,v.chasisNumber,v.classificationType,v.engineNumber,v.insuranceExpiredDate,IFNULL(v.insuranceNumber,0) AS insuranceNumber,
    vvd.status,IFNULL(v.vehicleNumber,'') AS vehicleNumber,IFNULL(v.vehicleType,'') AS vehicleType,v.insurancePaidDate,IFNULL(v.vehicleMaker,'N/A') as vehicleMaker,
	IFNULL(v.registrationAuthority,'N/A') as registrationAuthority,IFNULL(v.insuranceDetails,'N/A') as insuranceDetails,
    v.roadTaxAmount,v.fitnessExpiryDate,v.permitExpiryDate,v.pollutionExpiryDate,v.roadTaxNextPaymentDate,vvd.name,tm.id AS maintenanceId,
    IFNULL(tm.closingReading,0) AS closingReading,tm.custId,IFNULL(tm.openingReading,0) AS openingReading,IFNULL(tm.repairsCost,0) AS repairsCost,
    tm.vehicleId AS vehicleId,IFNULL(tm.monthId,0) AS monthId,tm.monthName,IFNULL(tm.oilBalance,0) AS oilBalance,IFNULL(tm.oilConsumed,0) AS oilConsumed,
    IFNULL(tm.oilPurchased,0) AS oilPurchased,tm.repairDescription,IFNULL(tm.totalKms,0) AS totalKms,IFNULL((tm.totalKms / tm.oilPurchased),0) AS perLtrKms,tm.vehicleAcademicDetailId
    FROM transportMaintenance tm JOIN vehiclesAcademicDetails vvd on (vvd.id = tm.vehicleAcademicDetailId and tm.academicYearId = vvd.academicYearId) 
   JOIN vehicles v on (v.id = vvd.vehicleId);  
     
-- For displaying route with vehicle names in excel sheet

CREATE or REPLACE VIEW vw_vehicleWithBoardingPoint AS 
select r.routeName AS routename,r.id AS routeId,br.id AS boardingpointId,br.boardingPointName AS boardingPointName,
v.id AS vehicleId,ifnull(concat(va.name,'-',br.boardingPointName)
,concat(v.vehicleNumber,'-',br.boardingPointName)) AS VehicleBoardingPointname,ifnull(va.name,v.vehicleNumber) AS VehicleName,
v.custId AS custId ,va.academicYearId,va.id as vehicleAcademicDetailId
from (((RouteWithVehicles rv join routeBoardingPoints br on((rv.routeId = br.routeId)))
 JOIN vehiclesAcademicDetails va on (va.id = rv.vehicleAcademicId) join vehicles v on((v.id = va.vehicleId))) join route r on((r.id = rv.routeId))) 
 where (va.status = 'Y') AND v.noOfSeats >0;
  


CREATE OR REPLACE VIEW vw_vehicleAndDriverInfo AS 
select 
	v.id AS vehicleId,
	va.id AS vehicleAcademicDetailId,
	ifnull(va.name, 'None') AS vehicleName,
	v.vehicleNumber AS vehicleNumber,
	ifnull(concat(p.firstName, ' ', p.lastName), 'NA') AS driverName,
	ifnull(p.mobileNumber, '0') AS mobileNumber,
	v.custId AS custId,
	va.academicYearId AS academicYearId,
	ifnull(va.driverId, 0) AS driverId
from
	vehicles v
	join vehiclesAcademicDetails va ON (v.id = va.vehicleId)
	left join staff s ON(s.accountId = va.driverId)
	left join Account a ON (s.accountId = a.id)
	left join Person p ON (a.personId = p.id);


    /*For transport request form*/       
CREATE OR REPLACE VIEW vw_transportRequestFormDetails AS 
select 
        s.id AS studId,
        s.academicYearId,
        s.transportMode,
        s.status AS status,
        s.rollNumber AS rollNumber,
        s.custId AS custId,
        sc.id AS classSectionId,
        sc.className AS className,
        c.organization AS organization,
        sc.section AS section,
        pe.mobileNumber,
        IFNULL(br.boardingPointFeeAmount,0) boardingPointFeeAmount,
        acc.admissionNumber AS admissionNumber,
        pe.firstName AS firstName,
        pe.lastName AS lastName,
        br.boardingPointName,v.vehicleNumber,va.name as vehicleName,r.routeName,
        ad.addressLine1,
        ad.city,
        ad.postalCode,
        ad.streetName,
        ui.name AS imageName,
        ui.path AS imagePath,
        ui.thumbNail AS thumbNail
    from
       student s
        left join RouteWithVehicles rv ON (s.vehicleAcademicDetailsId = rv.vehicleAcademicId)
        left join routeBoardingPoints br ON (br.id = s.boardingPointId)
        left join vehiclesAcademicDetails va ON (rv.vehicleAcademicId = va.id)
        left join vehicles v ON (v.id = va.vehicleId)
        LEFT JOIN route r on (r.id = br.routeId)
        left join studyClass sc ON (sc.id = s.classSectionId)
        left join customer c ON (c.id = s.custId and s.transportMode='T')
        left join Account acc ON (acc.id = s.accountId)
        left join Person pe ON (pe.id = acc.personId)
        left join Address ad ON (ad.id = acc.paddressId)
        left join UserImage ui ON (ui.id = s.imageId);
        
  
  
/* Library related views */


CREATE OR REPLACE VIEW vw_bookrackassignmentdetails AS 
select ifnull(ss.name, 'No Subject') AS subjectName,ifnull(ba.rackDetailsId, 0) AS rackId,b.custId AS custId,b.academicYearId AS academicYearId,ifnull(sum(b.noOfCopies), 0) AS noOfCount,
ifnull(sum(ba.noOfCopies), 0) AS existedCount,ifnull(b.subjectId, 0) AS subjectId,b.status AS status
from ((bookTitle b left join booksAssignToRack ba ON (((ba.subjectId = ifnull(b.subjectId, 0)) and (ba.academicYearId = b.academicYearId) and (ba.bookTitleId = b.id) and (ba.custId = b.custId))))
	left join studySubject ss ON (((ss.id = b.subjectId) and (ss.academicYearId = b.academicYearId) and (ss.custId = b.custId)))) where (b.status <> 'N') group by b.subjectId,b.custId, b.academicYearId;
     
    CREATE 
    OR REPLACE 
    VIEW vw_racksubjectdetails AS
    select 
        ra.id AS Id,
        r.rackName AS rackName,
        ifnull(ss.name,'No Subject') AS subjectName,
        r.id AS rackId,
        ra.custId AS custId,
        ra.academicYearId AS academicYearId,
        ra.subjectId AS subjectId,
        ra.noOfCopies AS noOfCopies,
        ra.bookTitleId As bookTitleId
    from
        booksAssignToRack ra
        left join  rackDetails r ON (ra.rackDetailsId = r.id)
        left join studySubject ss ON (ra.subjectId = ss.id)
    where ra.subjectId is not null;

        
        
 CREATE 
    OR REPLACE
	VIEW vw_studentissuebookandreservations AS
    select 
        bl.lableCode AS lableCode,
        ifnull(ss.name, 'No Subject') AS subjectName,
        bt.id AS bookTitleId,
        bt.bookName AS bookName,
        bt.noOfCopies AS noOfCopies, 
        bt.issueBookCount AS issuedBooksCount,
        bt.author AS author,
        bt.bookKeyWord AS bookKeyword,
        bt.publisher AS publisher,
        bt.otherSubjects AS otherSubjects,
        bt.status AS status,
        ifnull(rs.status, 'P') AS reservationStatus,
        ifnull(ib.status, 'S') AS issueBookStatus,        
        ifnull(bt.classId, 0) AS classId,
        ifnull(bt.subjectId, 0) AS subjectId,
        bt.custId AS custId,
        bt.academicYearId AS academicYearId,
        bl.bookLabelStatus AS booklabelStatus,
        bl.deleteStatus AS deleteStatus,
        bl.type AS type,
        bl.id AS bookLableId,
        ib.dueDate AS dueDate,
        ib.issuedDate AS issuedDate,
        ifnull(ib.accountId, 0) AS ibAccountId,
        ifnull(rs.bookReservationNo, 0) AS bookReservationNo,
        ifnull(rs.accountId, 0) AS rsAccountId,
        rs.createdDate AS reservationCreatedDate,
        rs.expiryDate AS reservationExpiryDate,
        IFNULL(b.blockName,'') AS blockName,
        IFNULL(rd.rackName,'') AS rackName,
        IFNULL(bt.acquisitionNumber,'') AS acquisitionNumber,
        IFNULL(bt.bookEdition,'') AS bookEdition
    from
        ((((bookTitle bt
        left join bookLables bl ON ((bl.bookTitleId = bt.id)))
        left join reservations rs ON (((rs.bookId = bt.id)
            and (rs.bookLableId = bl.id))))
        left join issuedBook ib ON (((ib.bookId = bt.id)
            and (ib.bookLableId = bl.id))))
        left join studySubject ss ON (((ss.id = bt.subjectId)
            and (ss.custId = bt.custId))))
           left join booksAssignToRack bar ON (bt.id = bar.bookTitleId)
           left join rackDetails rd ON (rd.id = bar.rackDetailsId)
          left join block b ON (b.id =rd.blockId);


CREATE 
   OR REPLACE 
VIEW vw_bookTitleAndBlockDetails AS
    select 
        bb.id AS id,
        bt.bookName AS bookName,
        ss.name AS name,
        bt.otherSubjects AS otherSubjects,
        bt.publisher AS publisher,
        bt.readingBookCount AS readingBookCount,
        bt.issueBookCount AS issueBookCount,
        bt.issueDays AS issueDays,
        bt.totalCost AS totalCost,
        bt.cost AS cost,
        bt.noOfCopies AS noOfCopies,
        bt.bookRequestExpareDays AS bookRequestExpareDays,
        bt.bookKeyWord AS bookKeyWord,
        bt.author AS author,
        bb.custId AS custId,
        b.blockName AS blockName,
        rd.rackName AS rackName,
        bt.id AS bookTitleId
    from
        ((((booksAssignToRack bb
        left join bookTitle bt ON ((bb.bookTitleId = bt.id)))
        left join rackDetails rd ON ((bb.rackDetailsId = rd.id)))
        left join block b ON ((rd.blockId = b.id)))
        left join studySubject ss ON ((bt.subjectId = ss.id)));

CREATE 
    OR REPLACE
   VIEW vw_bookAndLabelDetails AS
    select 
        bl.id AS bookLableId,
        bt.id AS bookTitleId,
        bt.bookName AS bookName,
        bt.otherSubjects AS otherSubjects,
        bt.publisher AS publisher,
        bt.bookKeyWord as bookKeyWord,
        bt.bookPublishedDate as bookPublishedDate,
        bt.yearOfPublication as yearOfPublication,
        bt.callNo as callNo,
        bt.classNo as classNo,
        bt.billNo as billNo,
        bt.noOfPages as noOfPages,
        bt.bookEdition as bookEdition,
        bt.place as place,
        bt.bookRemarks as bookRemarks,
        bt.language as language,
        bt.acquisitionNumber as acquisitionNumber,
        bt.sections as sections,
        bt.status as bookTitleStatus,
        ifnull(bt.issueDays, 0) AS issueDays,
        ifnull(bt.issueBookCount, 0) AS issueBookCount,
        bt.readingBookCount AS readingBookCount,
        bt.cost AS cost,
        bt.noOfCopies AS noOfCopies,
        bt.author AS author,
        bl.custId AS custId,
        bl.lableCode AS lableCode,
        bl.type AS type,
        bl.deleteStatus AS deleteStatus,
        bl.bookLabelStatus AS bookLabelStatus,
        ls.id AS librarySettingsId,
        ifnull(ls.fineAmountPerDay, 0) AS fineAmountPerDay,
        ifnull(ls.limitDays, 0) AS limitDays,
        ifnull(ls.noOfStaffIssuBooks, 0) AS noOfStaffIssuBooks,
        ifnull(ls.noOfStudentIssuBooks, 0) AS noOfStudentIssuBooks,
        ss.name AS name,cl.className as className
    from
        (((bookTitle bt
        join bookLables bl ON ((bl.bookTitleId = bt.id)))
        left join librarySettings ls ON (((bt.custId = ls.custId)
            and (bt.academicYearId = ls.academicYearId))))
        LEFT JOIN class cl ON (cl.id = bt.classId)
        left join studySubject ss ON ((bt.subjectId = ss.id)));
        
 

CREATE  OR REPLACE VIEW vw_issuedBookAndSettings AS select ib.id AS issuedBookId,bt.id AS bookTitleId,bt.bookName AS bookName,bt.otherSubjects AS otherSubjects,IFNULL(bt.subjectId, '0') as subjectId,
bt.cost AS cost,bt.totalCost AS totalCost,bt.readingBookCount AS readingBookCount,bt.issueBookCount AS issueBookCount,bt.issueDays AS issueDays,bt.bookRequestExpareDays AS bookRequestExpareDays,
bt.publisher AS publisher,bt.author AS author,ib.accountId AS accountId,ib.custId AS custId,ib.issuedDate AS issuedDate,ib.dueDate AS dueDate,ib.status AS status,ib.submitedDate AS submitedDate,
ib.fineAmount AS fineAmount,bl.lableCode AS lableCode,bl.id AS lableCodeId,bl.type AS type,bl.deleteStatus AS deleteStatus,bl.bookLabelStatus AS bookLabelStatus,ls.id AS librarySettingsId,ls.fineAmountPerDay AS fineAmountPerDay,
ls.limitDays AS limitDays,ls.noOfStaffIssuBooks AS noOfStaffIssuBooks,ls.noOfStudentIssuBooks AS noOfStudentIssuBooks,a.username AS username,ss.name AS name,IFNULL(ib.classId,0) as classId,ib.userStatus,
ls.academicYearId,ib.paidFineAmount,ib.fineExemption,a.admissionNumber admissionNumber from (((((bookTitle bt left join bookLables bl ON ((bl.bookTitleId = bt.id)))
left join issuedBook ib ON (((ib.bookLableId = bl.id) and (ib.bookId = bt.id))))
left join librarySettings ls ON ((ib.custId = ls.custId)))
left join Account a ON ((ib.accountId = a.id))) 
left join studySubject ss ON ((bt.subjectId = ss.id)))
where ib.Id is not null group by issuedBookId,accountId;

create or replace view vw_studentFutureFeePaymentDetails as
SELECT 
     payment_SequanceId() AS id,IFNULL(cf.feeId,0) AS feeId,cf.custId,IFNULL(cf.feeSettingId,0) AS feeSettingId,cf.settingName,cf.academicYearId,
     cf.schoolTermId,cf.termName,cf.toMonthName,cf.fromMonthName,IFNULL(cf.feeTypeId,0) AS feeTypeId,cf.feeType,cf.feeAmount,cf.classId,cf.className,cf.section,cf.classSectionId,
     IFNULL(sfp.studentId,0) AS studentId, IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sfp.deleteStatus, 'N') as deleteStatus,
     IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sp.paidAmount,0) AS paidAmount,sp.paymentDate,sp.invoiceNumber,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,IFNULL(sfp.studentPaymentId, 0) as studentPaymentId
FROM 
vw_classFeeDetails cf 
right join studentFeePaidDetails sfp ON (cf.feeId = sfp.feeId and cf.custId=sfp.custId and deleteStatus='N' and sfp.futureFeeStatus = 'Y')
right join studentPayment sp ON (sp.id = sfp.studentPaymentId and cf.custId=sfp.custId and cf.academicYearId=sp.academicYearId and sp.studentId=sfp.studentId) left join bankMaster bm ON (sp.bankId = bm.id) group by sp.id,sp.studentId,sfp.feeId;


 CREATE 
    OR REPLACE
   VIEW vw_FeedbackRatingDetails AS
    select 
        pfrd.id AS parentFeedbackId,
        pfrd.studentId AS studentId,
        pfrd.staffId AS staffId,
        pfrd.parentId AS parentId,
        pfrd.custId AS custId,
        pfrd.academicYearId AS academicYearId,
        fbg.id AS feedbackGradeId,
        fbg.description AS description,
        fbg.title AS resultGradeValue,
        fbq.id AS feedbackQuestionId,
        fbq.roleDescription AS roleDescription,
        fbq.description AS qusDescription
    from
  	 parentFeedbackResult pfrd right join feedbackGrades fbg ON (pfrd.feedbackGradeId=fbg.id)
 	   right join feedbackQuestions fbq ON (pfrd.feedbackQuestionId =fbq.id);
 	   
CREATE OR REPLACE VIEW vw_studentsExcessPaymentDetails AS 
select ep.id AS excessPaymentId,ep.excessAmount AS excessAmount,ep.status AS excessPaymentStatus,ep.accountId AS accountId,c.className AS className,c.section AS section,a.admissionNumber AS admissionNumber,
s.rollNumber AS rollNumber,s.academicYearId AS academicYearId,s.custId AS custId,p.mobileNumber AS mobileNumber,concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')), '',concat(' ', p.lastName))) AS fullName,
s.description AS studDiscontinueDesc
from excessPayment ep join studentPayment sp ON (ep.paymentId = sp.id)
left join student s ON(s.accountId = ep.accountId)
join Account a ON(a.id = s.accountId)
left join Person p ON(p.id = a.personId)
left join studyClass c ON(c.id = s.classSectionId);

 
CREATE OR REPLACE VIEW vw_studentTransportDetails as 
SELECT s.id as studentId,s.custId,s.classSectionId,ac.id as academicYearId,ac.academicYear,ac.status as academicYearStatus,IFNULL(rb.id,0) as boardingPointId,
rb.boardingPointName,IFNULL(va.id,0) as vehicleAcademicDetailsId,va.name as vehicleName,va.vehicleId,s.transportMode,r.routeName,
IFNULL(r.id,0) as routeId,s.categoryId,s.classNameClassId,IFNULL(s.futureAcademicClassSecId,0) as futureAcademicClassSecId,s.accountId,
s.status,s.description,s.registerNumber,s.hostelMode,s.rollNumber,s.feeConfigured,s.feePaidStatus 
FROM student s JOIN academicYear ac on (s.custId = ac.custId and ac.id = s.academicYearId) 
LEFT JOIN routeBoardingPoints rb on (s.transportMode='T' and s.boardingPointId = rb.id and s.academicYearId= rb.academicYearId) 
LEFT JOIN route r on (r.id = rb.routeId)
LEFT JOIN vehiclesAcademicDetails va on (s.transportMode='T' and s.vehicleAcademicDetailsId = va.id and va.academicYearId = s.academicYearId);

 
CREATE OR REPLACE VIEW vw_studentsFutureAcademicTransportDetails as 
SELECT s.studentId,s.academicYearId,IFNULL(futureYear.id,0) as futureAcademicId,futureYear.academicYear,futureYear.custId,r.id as routeId,r.routeName,rb.id as boardingPointId,
rb.boardingPointName,IFNULL(va.id,0) as vehicleAcademicId,va.name as vehicleName,s.transportMode,s.categoryId,s.classNameClassId,s.classSectionId,
IFNULL(s.futureAcademicClassSecId,0) as futureAcademicClassSecId,IFNULL(c.id,0) as futureAcademicClassId,s.accountId,s.status,s.description,
s.registerNumber,s.hostelMode,s.rollNumber,s.feeConfigured,s.feePaidStatus 
FROM vw_studentTransportDetails s JOIN academicYear futureYear on (futureYear.custId = s.custId and futureYear.academicYear = CONCAT((SUBSTRING_INDEX(s.academicYear,'-',1)+1),'-',(SUBSTRING_INDEX(s.academicYear,'-',-1)+1)))
LEFT JOIN studyClass sc on (s.futureAcademicClassSecId = sc.id) LEFT JOIN class c on (c.id = sc.classNameClassId)
LEFT JOIN route r on (s.transportMode = 'T' and r.academicYearId = futureYear.id and r.routeName = s.routeName and r.custId=s.custId)
LEFT JOIN routeBoardingPoints rb on (s.transportMode = 'T' and rb.academicYearId = futureYear.id and rb.routeId = r.id and rb.boardingPointName = s.boardingPointName)
LEFT JOIN vehiclesAcademicDetails va on (s.transportMode = 'T' and va.academicYearId = futureYear.id and va.name = s.vehicleName and va.vehicleId = s.vehicleId);  

CREATE OR REPLACE VIEW vw_futureYearStudentClassFees AS 
select cf.feeId AS feeId,ifnull(cf.feeAmount, 0) AS feeAmount,cf.custId AS custId,cf.feeTypeId AS feeTypeId,ifnull(cf.schoolTermId, 0) AS schoolTermId,cf.classId AS classId,
cf.academicYearId AS academicYearId,if((((s.transportMode = 'O') and (cf.settingName = 'Transport Fee')) or ((s.transportMode = 'p')
and (cf.settingName = 'Transport Fee')) or ((s.hostelMode = 'D') and (cf.settingName = 'Hostel Fee'))), 'N',cf.feeType) AS feeType,cf.fromDate AS fromdate,
cf.toDate AS toDate,cf.feeSettingId AS feeSettingId,cf.settingName AS settingName,cf.settingType AS settingType,cf.applToNewStuds AS applToNewStuds,
cf.fromMonthName AS fromMonthName,cf.toMonthName AS toMonthName,cf.termName AS termName,cf.dueDate AS dueDate,cf.categoryId AS categoryId,cf.categoryName AS categoryName,
cf.routeBoardingPointId AS routeBoardingPointId,s.studentId AS studentId,s.rollNumber AS rollNumber,s.accountId AS accountId,s.status AS status,s.description AS description,
s.transportMode AS transportMode,s.hostelMode AS hostelMode,s.boardingPointId AS boardingPointId,
cf.className AS className,cf.section AS section,cf.classSectionId AS classSectionId,s.feeConfigured,s.feePaidStatus,sfc.concessionAmount
FROM vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s ON (s.futureAcademicClassSecId != 0 and IF(cf.settingName = 'Transport Fee',
IF(cf.transportFeeByBoardingPoint = 'Y',
(s.boardingPointId = cf.routeBoardingPointId and cf.custId = s.custId and cf.academicYearId =s.futureAcademicId and s.transportMode = 'T' and cf.categoryId = s.categoryId),
(cf.classId = s.futureAcademicClassId and cf.custId = s.custId and cf.academicYearId = s.futureAcademicId and s.boardingPointId != 0 and cf.categoryId = s.categoryId)
),(cf.classId =s.futureAcademicClassId and cf.custId = s.custId and cf.academicYearId = s.futureAcademicId and cf.categoryId = s.categoryId)
) and cf.classSectionId = s.futureAcademicClassSecId) 
       LEFT JOIN studentFeeConcession sfc ON (sfc.studentId=s.studentId and sfc.feeId=cf.feeId);

CREATE OR REPLACE VIEW vw_studentsFutureAcademicFeeDetails as 
SELECT payment_SequanceId() AS id,cf.feeSettingId,cf.settingName,cf.schoolTermId,cf.termName,cf.feeTypeId,cf.feeType,cf.feeId,cf.feeAmount,IFNULL(sfp.paymentAmount,0) as paymentAmount,
IFNULL(sfp.discountAmount,0)  as discountAmount,sp.paymentDate,s.studentId,s.custId,s.academicYearId,IFNULL(s.futureAcademicId,0) as futureAcademicId,
IFNULL(s.futureAcademicClassSecId,0) as futureAcademicClassSecId,IFNULL(s.futureAcademicClassId,0) as futureAcademicClassId,cf.fromMonthName,cf.toMonthName,
cf.categoryId,sp.invoiceNumber,sp.paidAmount,s.accountId,s.status,s.description,cf.className,cf.section,cf.classSectionId,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,
IFNULL(sfc.id,0) as concessionId,IFNULL(sfc.concessionAmount,0) as concessionAmount,IFNULL(sp.transactionNumber,0) as transactionNumber,IFNULL(sp.bankId, 0) as bankId,sp.chequeIssuedDate,sp.ddDrawnDate,
sp.branchName,sp.lastUpdatedDate,sp.paymentType,sp.ddNumber,bm.bankName,sp.chequeNumber,sp.invoiceString,IFNULL(sp.totalBalanceAmount,0) as totalBalanceAmount,IFNULL(sp.termwiseTotalBalanceAmount,0) as termwiseTotalBalanceAmount
FROM vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s ON (s.futureAcademicClassSecId != 0 and IF(cf.settingName = 'Transport Fee',
IF(cf.transportFeeByBoardingPoint = 'Y',
(s.boardingPointId = cf.routeBoardingPointId and cf.custId = s.custId and cf.academicYearId =s.futureAcademicId and s.transportMode = 'T' and cf.categoryId = s.categoryId),
(cf.classId = s.futureAcademicClassId and cf.custId = s.custId and cf.academicYearId = s.futureAcademicId and s.boardingPointId != 0 and cf.categoryId = s.categoryId)
),(cf.classId =s.futureAcademicClassId and cf.custId = s.custId and cf.academicYearId = s.futureAcademicId and cf.categoryId = s.categoryId)
) and cf.classSectionId = s.futureAcademicClassSecId) 
left join class c ON (c.id = cf.classId) 
left join studentFeePaidDetails sfp ON (sfp.studentId = s.studentId and sfp.feeId = cf.feeId and sfp.custId = s.custId and sfp.deleteStatus = 'N' and sfp.futureFeeStatus = 'Y') 
left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId = s.studentId  and sp.academicYearId =s.futureAcademicId )
left join bankMaster bm ON (sp.bankId = bm.id)
left join studentFeeConcession sfc ON (s.studentId=sfc.studentId and sfp.feeId=sfc.feeId)
left join Account a ON (s.accountId = a.id) LEFT JOIN Person p ON (a.personId = p.id) ;


 /* For adding comments in syllabus subject planner view by cvs on 3-7-2014*/
create 
or replace view vw_syllabusPlannerComments as
SELECT spc.id as syllabusPlannerCommentsId,spc.commentsDate,IFNULL(spc.receiverAccountId,0) as receiverAccountId,spc.messageContent,spc.academicYearId,spc.custId,IFNULL(spc.staffSyllabusPlannerId,0) as staffSyllabusPlannerId,spc.status,ui.id AS imageId,ui.name AS imageName,ui.path AS imagePath,ui.thumbNail AS thumbNail,p.firstName,p.lastName
FROM syllabusPlannerComments spc LEFT JOIN Account a on (a.id=spc.receiverAccountId) LEFT JOIN Person p  on (a.personId=p.id) LEFT JOIN UserImage ui on (ui.id=a.imageId);


CREATE OR REPLACE VIEW vw_circularusers AS SELECT c.id,c.circularDate,	c.custId,c.circularDescription,c.academicYearId,c.senderAccountId,IFNULL(cur.userId,0) AS userId,c.circularStatus from circular c LEFT JOIN CircularUsers cur on (c.id = cur.circularId);

/*CREATE OR REPLACE VIEW vw_circularusers AS SELECT c.id,c.circularDate,	c.custId,c.circularDescription,c.academicYearId,c.senderAccountId,cur.userId from CircularUsers cur  LEFT JOIN circular c on (cur.circularId=c.id);*/
 
create 
or replace view vw_hostelStudentLeaveDetails as
SELECT s.accountId,s.custId, s.username,sp.parentAccountId AS parentId,s.version, s.sharePrivacy, s.userOnlineNow, s.studentId, s.academicYearId, s.status,s.classSectionId,s.rollNumber,s.firstName,s.lastName, s.middleName, s.fatherName, s.motherName, s.mothersMaidenName,s.dateOfBirth,s.bloodGroup,s.mobileNumber,s.phoneNumber,s.addressLine1,s.addressLine2,s.city,s.state,s.postalCode, s.parentEmail,s.summary,s.dateOfJoining,s.section,
s.className,s.hostelMode,ur.roleName,ur.roleDescription,ur.roleId,l.id leavesId,l.leaveStatus,l.leaveType,l.startTime,l.endTime,l.description,l.startDate,l.endDate,l.supervisorId leaveSupervisorId,IFNULL(l.leavesCount,0) as leavesCount, ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail,s.classNameClassId as classId
FROM vw_studentDetails s  JOIN hostelLeaves l on (s.accountId=l.accountId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN UserImage ui on (ui.id=s.imageId) LEFT JOIN academicYear a on (l.academicYearId = a.id) LEFT JOIN studentparent sp ON(sp.studentAccountId = ur.accountId);
 
CREATE OR REPLACE VIEW vw_hostelStudentDailyAttendance AS 
select 
    vws.fullName AS StudentName,
    vws.rollNumber AS rollNumber,
    vws.classSectionId AS classSectionId,
    vws.studId AS studentId,
    vws.accountId AS accountId,
    vws.custId AS custId,
    vws.parentEmail AS parentEmail,
    vws.fatherName AS fatherName,
    vws.className,
    vws.section,
    vws.status,
    vws.admissionNumber,
    vws.mobileNumber,
    trim(trailing ' - ' from concat(ifnull(vws.ClassName, ''), ' - ', ifnull(vws.Section, ''))) AS classNameAndSection,
    vws.academicYearId AS academicYearId,
    sda.present AS present,
    sda.attendanceDate AS attendanceDate,
    sda.leaveRequest,
    extract(month from sda.attendanceDate) AS month,
    (select monthname(sda.attendanceDate) AS monthName) AS monthName,
    sda.id as studentAttendanceId,
    vws.hostelMode
from
    (vw_studentClasswiseAndPersonalInfo vws
    LEFT JOIN hostelStudentDailyAttendance sda ON ((vws.studId = sda.studentId)));
    
-- Ramarao  26/09/2014  SMS App Webservice --
CREATE OR REPLACE VIEW vw_SmsApp_StudentDailyAttendance AS  
select  sda.studentId AS studentId,sda.custId AS custId,sda.academicYearId AS academicYearId,sda.present AS present,sda.attendanceDate AS attendanceDate,monthname(sda.attendanceDate) AS monthName
from studentDailyAttendance sda;

CREATE OR REPLACE VIEW vw_studyClassStudentsDailyAttendance AS SELECT s.id as studentId,s.custId,s.academicYearId,
sda.attendanceDate,sda.id as studentDailyAttendanceId,sc.id as studyClassId,sc.className,sda.present
from student s  LEFT JOIN studentDailyAttendance sda on (sda.studentId = s.id) LEFT JOIN studyClass sc on (s.classSectionId=sc.id);


CREATE OR REPLACE VIEW vw_studyClassStudentsMonthlyAttendance AS SELECT s.id as studentId,s.custId,s.academicYearId,
sma.totalWorkingDays,sma.id as studentMonthlyAttendanceId,sma.noOfPresentDays,sma.month,sma.monthName, sc.id as studyClassId,sc.className
from student s  LEFT JOIN studentMonthlyAttendance sma on (sma.studentId = s.id) LEFT JOIN studyClass sc on (s.classSectionId=sc.id);

CREATE OR REPLACE VIEW vw_studentSiblings AS
select ss.id AS studentSiblingsId,ss.custId AS custId,sc.id AS studyClassId,sc.className AS className,p.firstName AS firstName,ifnull(p.lastName, '') AS lastName,a.accountExpired AS accountExpired,
concat(if(isnull(p.firstName),'',p.firstName),if((isnull(p.lastName) or (p.lastName = '')),'',concat(' ', p.lastName))) AS fullName,concat(if(isnull(p.firstName),'',p.firstName),if((isnull(p.lastName)or (p.lastName = '')),'',concat(' ', p.lastName))) AS formatedFullName
from (((studentSiblings ss 
left join Account a ON ((ss.accountId = a.id)))
left join Person p ON ((a.personId = p.id)))
left join studyClass sc ON ((ss.studyClassId = sc.id)));

CREATE OR REPLACE VIEW vw_issueProvisionItemsToMess AS SELECT ipim.id as issueProvisionItemsToMessId,ipim.custId,ipim.status,ipim.usedQuantity,
ipim.issueDate,pi.id as provisionItemsId,pi.itemName,pi.measurement,mft.id as messFoodTypeId,mft.foodTypeName, m.id as messId,m.messName
from issueProvisionItemsToMess ipim  LEFT JOIN provisionItems pi on (ipim.provisionItemId = pi.id) LEFT JOIN messFoodType mft on (ipim.messFoodTypeId=mft.id) LEFT JOIN mess m on (ipim.messId=m.id);


create or replace view vw_examTypesSchedules as
SELECT IFNULL(et.id, 0) as examTypeId,et.examType,es.custId,es.academicYearId,IFNULL(es.classSectionId, 0) as classSectionId
FROM examTypes et LEFT JOIN examSchedules es ON (et.custId = es.custId and et.academicYearId = es.academicYearId and et.id = es.examTypeId)
LEFT JOIN studentMarks sm ON (es.id = sm.examScheduleId) where sm.id > 0 group by et.id,es.classSectionId;


CREATE OR REPLACE VIEW vw_meetingRequestDetails AS SELECT md.id as meetingDetailsId,mrc.custId,md.orgId,c.organization,mrc.id as meetingRequestToCustomersId,md.meetingDate,md.place,md.startTime,
md.meetingAgenda,md.comments
from meetingDetails md  LEFT JOIN meetingRequestToCustomers mrc on (mrc.meetingDetailsId = md.id) LEFT JOIN customer c on (mrc.custId=c.id);

CREATE OR REPLACE VIEW vw_BudgetRequestDetails AS SELECT br.id as budgetRequestId,br.requestedMonth,br.custId,c.orgId,c.organization,br.createdDate,br.budgetType,br.managerId,br.financialYearId,p.mobileNumber,
br.comments,br.monthName,br.totalBudgetAmount,br.status,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName
from budgetRequest br  LEFT JOIN customer c on (br.custId=c.id) left join Account a ON br.managerId = a.id left join Person p ON a.personId = p.id;
-- Below views are used to show the graphs for pass and fail persentage  in admin dashboard and not have pojo class ---
CREATE OR REPLACE VIEW vw_calculatePassAndFailCountStudents AS
SELECT IFNULL(c.id, 0) AS classNameClassId,es.custId,es.academicYearId,sc.id AS classSectionId,
CAST(CONCAT(IF(sc.className IS NULL, '', sc.className),IF(sc.section IS NULL || sc.section = '','',CONCAT(' - ', sc.section))) AS CHAR(60)) as classAndSection,et.id AS examTypeId,
SUM(IF((sm.obtainedMarks + sm.moderationMarks) < et.minMarks, 1, 0)) AS failCount,SUM(IF((sm.obtainedMarks + sm.moderationMarks) >= et.minMarks, 1, 0)) passCount
FROM studentMarks sm
LEFT JOIN examSchedules es ON (es.id = sm.examScheduleId)
LEFT JOIN examTypes et ON (et.id = es.examTypeId)
LEFT JOIN studyClass sc ON (sc.id = es.classSectionId)
LEFT JOIN class c ON (c.id = sc.classNameClassId)
GROUP BY sc.id,et.id;
    
CREATE OR REPLACE VIEW vw_calculatePassAndFailCountStudentsBySubjects AS
SELECT IFNULL(c.id, 0) AS classNameClassId,es.custId,ss.id as subjectId,ss.name as subjectName,es.academicYearId,sc.id AS classSectionId,et.id AS examTypeId,
SUM(IF((sm.obtainedMarks + sm.moderationMarks) < et.minMarks, 1, 0)) AS failCount,SUM(IF((sm.obtainedMarks + sm.moderationMarks) >= et.minMarks, 1, 0)) passCount
FROM studentMarks sm
LEFT JOIN examSchedules es ON (es.id = sm.examScheduleId)
LEFT JOIN examTypes et ON (et.id = es.examTypeId)
LEFT JOIN studySubject ss ON (ss.id = es.classSubjectId)
LEFT JOIN studyClass sc ON (sc.id = es.classSectionId)
LEFT JOIN class c ON (c.id = sc.classNameClassId)
GROUP BY sc.id,ss.id,et.id;

-- Venkatesh  24/07/2015 Found the updated marks staff details --
CREATE OR REPLACE VIEW vw_marksUpdatedDetails AS 
select 
    sm.id AS studMarkId,
    IFNULL(sm.lastUpdatedDate, sm.createdDate) AS date,  
    sm.obtainedMarks AS obtainedMarks,
    sm.moderationMarks AS moderationMarks,
    es.id as examScheduleId,
    CAST(CONCAT(IF(sc.className IS NULL, '', sc.className),IF(sc.section IS NULL || sc.section = '','',CONCAT(' - ', sc.section))) AS CHAR(60)) as classAndSection,
    et.id AS examTypeId,
    et.examType AS examType,
    ss.id AS classSubjectId,
    ss.name AS subjectName,
    ss.custId AS custId,
    ss.academicYearId AS academicYearId,
    ifnull(a.personId, 0) as personId, 
    p.firstName, 
    p.lastName, 
    concat(p.firstName,' ',p.lastName) as fullName,
    a.id AS accountId
from studentMarks sm 
    LEFT JOIN examSchedules es ON  (es.id = sm.examScheduleId) 
    LEFT JOIN studySubject ss ON (ss.id = es.classSubjectId)
    LEFT JOIN studyClass sc ON (sc.id = es.classSectionId)  
    LEFT JOIN examTypes et ON (et.id = es.examTypeId)
    LEFT JOIN Account a on (IF(IFNULL(sm.lastUpdatedById, 0) = 0, sm.createdById, sm.lastUpdatedById) = a.id and a.custId=et.custId)
    LEFT JOIN Person p on (a.personId = p.id);

CREATE OR REPLACE VIEW vw_studyClassMaterials AS
SELECT  payment_SequanceId() AS id,sm.id as materialId,sm.materialName,sm.description,sm.subjectId,sm.custId as custId,sm.academicyearId as academicYearId,ms.studyClassId as classSectionId,
s.name as subjectName,st.className as className,IFNULL(st.section,'') as sectionName
FROM studyMaterials sm
LEFT JOIN materialsStudyClasses ms ON (ms.MaterialId=sm.id)
LEFT JOIN studySubject s ON (s.id = sm.subjectId)
LEFT JOIN studyClass st ON (st.id=ms.studyClassId);



CREATE OR REPLACE VIEW vw_staffDetailsByDays AS 
SELECT DISTINCT s.accountId AS accountId, p.mobileNumber AS mobileNumber,d.email AS email,
concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')),'',concat(' ', p.lastName))) AS fullName,
s.custId AS custId,s.academicYearId AS academicYearId
FROM staff s 
LEFT JOIN Account a ON(a.id = s.accountId) 
LEFT JOIN Address d ON a.paddressId = d.id
LEFT JOIN Person p ON(p.id = a.personId) where s.status = 'Y' and DATE_FORMAT(curdate(),'%c-%d') = DATE_FORMAT(p.dateOfBirth, '%c-%d');

CREATE OR REPLACE VIEW vw_staffPermissionsSettings AS 
SELECT sp.id as staffPermissionsSettingsId, spd.id as staffPermissionsDayDetailsId,IF(r.description IS NULL || r.description  = '','All Roles',CONCAT(' ',r.description)) as roleDescription, IF(sp.status ='L','Late Settings','Permissions Settings') AS status,IF(sp.monthOrYear ='M','Monthly Wise','Yearly Wise') AS monthOrYear,
IF(sp.isRolebased ='R','Role Wise','All are smae days') AS isRolebased,spd.days,r.name,spd.roleId, sp.custId AS custId,sp.academicYearId AS academicYearId
FROM staffPermissionsDayDetails spd  
LEFT JOIN staffPermissionsSettings sp ON(spd.staffPermissionsSettingsId = sp.id)
LEFT JOIN Role r ON(r.id = spd.roleId);
 
CREATE OR REPLACE VIEW vw_permissionSettings AS 
SELECT pt.id as permissionTimingsId, p.id as permissionsId, p.studentId,p.createdById,p.lastUpdatedById,p.approvalsComment,ifnull(p.supervisorId, 0) AS supervisorId,p.startDate,p.endDate,p.status,p.comments,
pt.startTime,pt.endTime,(case when (pt.days = 'SU') then 'SUNDAY' when (pt.days = 'MO') then 'MONDAY' when (pt.days = 'TU') then 'TUESDAY' when (pt.days = 'WD') then 'WEDNESDAY' when (pt.days = 'TH') then 'THURSDAY'when (pt.days = 'FR') then 'FRIDAY' when (pt.days = 'SA') then 'SATURDAY' else '' end) AS 'days',
CONCAT(IF(pe.firstName IS NULL,'',pe.firstName), IF(pe.lastName IS NULL || pe.lastName  = '','',CONCAT(' ',pe.lastName))) as fullName,
CAST(CONCAT(IF(c.className IS NULL, '', c.className),IF(c.section IS NULL || c.section = '','',CONCAT(' - ', c.section))) AS CHAR(60)) as classNameAndSection, s.custId AS custId,s.academicYearId AS academicYearId
FROM  permissionTimings pt  
LEFT JOIN permissions p ON (p.id = pt.permissionsId)
left join student s ON (s.id = p.studentId)
left join Account a ON (a.id = s.accountId)
left join Person pe ON (a.personId = pe.id)
left join studyClass c ON (c.id = s.classSectionId)
left join class cl ON(cl.id = c.classNameClassId);

 
CREATE OR REPLACE VIEW vw_staffPermissionRequests AS 
SELECT sp.id as permissionRequestId,sp.status,sp.staffId,sp.comments,sp.approvalsComment,sp.startTime,sp.endTime,sp.permissionDate,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,sp.createdById,
r.name as roleName,r.id as roleId,s.custId AS custId,s.academicYearId AS academicYearId
FROM  staffPermissionRequests sp   
left join staff s ON (s.id = sp.staffId)
left join Account a ON (a.id = s.accountId)
left join Person p ON (a.personId = p.id)
LEFT JOIN UserRoles ur ON(ur.userId = a.id)
LEFT JOIN Role r ON(r.id = ur.roleId);

create 
or replace view vw_staffSyllabusPlanner as SELECT ss.id as staffSyllabusPlannerId,ss.staffId,ss.studySubjectId,ss.academicYearId,ss.custId,s.name as subjectName,ss.studyClassId as studyClassId
FROM staffSyllabusPlanner ss LEFT JOIN studySubject s on (s.id=ss.studySubjectId);

 
CREATE OR REPLACE VIEW vw_parentAppointmentDetails AS 
select ap.id AS appointmentId,
ap.email AS email,
ap.subject AS subject,
ap.custId AS custId,
ap.description AS description,
ap.academicYearId AS academicYearId,
ap.requestAccountId AS requestAccountId,
ap.receivedAccountId AS receivedAccountId,
ap.scheduleDate AS scheduleDate,
ap.scheduleTime AS scheduleTime,
ap.apporveDescription AS apporveDescription,
ap.status AS status,
ap.mobileNumber AS mobileNumber,
ap.studentAccountId,
trim(pe.firstName) AS firstName,
trim(pe.lastName) AS lastName,
r.description AS requestRoleDescription,
r.name AS requestRoleName,
concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')), '', concat(' ', p.lastName))) AS requestFullName,
rl.description AS receivedRoleDesc,
rl.name AS receivedRoleName,
concat(if(isnull(pe.firstName), '', pe.firstName), if((isnull(pe.lastName) or (pe.lastName = '')), '', concat(' ', pe.lastName))) AS receiveFullName,
pe.mobileNumber AS reciveMobileNUmber,
d.email AS receivStaffEmail, 
acy.status as academicYearStatus,
acy.id,sc.id as studyClassid,
concat(if(isnull(pe.firstName), '', pe.firstName), if((isnull(pe.lastName) or (pe.lastName = '')), '', concat(' ', pe.lastName))) AS studentFullName,
CAST(CONCAT(IF(c.className IS NULL, '', c.className),IF(sc.section IS NULL || sc.section = '','',CONCAT(' - ', sc.section))) AS CHAR(60)) as staffClassAndSection,
CAST(CONCAT(IF(c.className IS NULL, '', c.className),IF(sc.section IS NULL || sc.section = '','',CONCAT(' - ', sc.section))) AS CHAR(60)) as studClassAndSection
 
from Appointment ap 
LEFT JOIN studentparent sp ON(sp.parentAccountId = ap.requestAccountId)
left join Account a ON (a.id = sp.studentAccountId) 
left join Account ac ON (ap.receivedAccountId = ac.id)
left join Account sac ON (ap.studentAccountId = sac.id)
left join student st ON (st.accountId = sac.id)
left join Person p ON (a.personId = p.id)
left join Person pe ON (ac.personId = pe.id)
LEFT JOIN academicYear acy ON (acy.id = ap.academicYearId)
LEFT JOIN UserRoles ur ON(ur.userId = a.id)
LEFT JOIN UserRoles urs ON(urs.userId = ac.id)
LEFT JOIN Role r ON(r.id = ur.roleId)
LEFT JOIN Role rl ON(rl.id = urs.roleId)
LEFT JOIN Address d ON (ac.paddressId = d.id) 
LEFT JOIN studyClass sc on (sc.id=st.classSectionId and sc.academicYearId=acy.id)
LEFT JOIN class c on (c.id=sc.classNameClassId) 
where acy.status='Y' group by ap.id;

CREATE OR REPLACE VIEW vw_staffAppointmentDetails AS 
select ap.id AS appointmentId,
ap.email AS email,
ap.subject AS subject,
ap.custId AS custId,
ap.description AS description,
ap.academicYearId AS academicYearId,
ap.requestAccountId AS requestAccountId,
ap.receivedAccountId AS receivedAccountId,
ap.scheduleDate AS scheduleDate,
ap.scheduleTime AS scheduleTime,
ap.apporveDescription AS apporveDescription,
ap.status AS status,
ap.mobileNumber AS mobileNumber,
trim(pe.firstName) AS firstName,
trim(pe.lastName) AS lastName,
r.description AS requestRoleDescription,
r.name AS requestRoleName,
concat(if(isnull(p.firstName), '', p.firstName), if((isnull(p.lastName) or (p.lastName = '')), '', concat(' ', p.lastName))) AS requestFullName,
rl.description AS receivedRoleDesc,
rl.name AS receivedRoleName,
concat(if(isnull(pe.firstName), '', pe.firstName), if((isnull(pe.lastName) or (pe.lastName = '')), '', concat(' ', pe.lastName))) AS receiveFullName,
pe.mobileNumber AS reciveMobileNUmber,
d.email AS receivStaffEmail, 
acy.status as academicYearStatus,
acy.id,
concat(if(isnull(pe.firstName), '', pe.firstName), if((isnull(pe.lastName) or (pe.lastName = '')), '', concat(' ', pe.lastName))) AS studentFullName
 
from Appointment ap 
left join Account a ON (a.id = ap.requestAccountId) 
left join Account ac ON (ac.id = ap.receivedAccountId)
left join staff s ON (s.accountId = a.id)
left join Person p ON (a.personId = p.id)
left join Person pe ON (ac.personId = pe.id)
LEFT JOIN academicYear acy ON (acy.id = ap.academicYearId)
LEFT JOIN UserRoles ur ON(ur.userId = a.id)
LEFT JOIN UserRoles urs ON(urs.userId = ac.id)
LEFT JOIN Role r ON(r.id = ur.roleId)
LEFT JOIN Role rl ON(rl.id = urs.roleId)
LEFT JOIN Address d ON (ac.paddressId = d.id) ;

 create or replace view vw_studentMarks as 
select ifnull(s.id, 0) AS studId,ifnull(sm.id, 0) AS marksId,ifnull(es.examTypeId, 0) AS examTypeId,s.custId AS custId,ifnull(es.id,0) AS scheduleId,s.rollNumber AS rollNumber,ifnull(s.classSectionId, 0) AS classSectionId,
s.academicYearId AS academicYearId,sm.obtainedMarks AS obtainedMarks,
sm.moderationMarks AS moderationMarks,
es.classSubjectId as subjectId,
es.subTypeId as subTypeId,
ss.subjectType as subjectType,
sm.lastUpdatedDate AS lastUpdatedDate,sm.present AS present,sc.className AS className,sc.classNameClassId AS classNameClassId,sc.section AS section	
from studentMarks sm
join student s ON (s.id = sm.studId)
join examSchedules es ON (es.id = sm.examScheduleId)
join studySubject ss ON (ss.id = es.classSubjectId)
join studyClass sc ON (sc.id = s.classSectionId);

	
CREATE OR REPLACE VIEW vw_studyAndBonafiedCertificates AS
SELECT stc.id as studyAndBonafiedId,stc.custId,stc.studyClassId,stc.fileName,stc.certificateType,stc.filePath,st.academicYearId,
CONCAT(IF(st.className IS NULL,'',st.className), IF(st.section IS NULL || st.section  = '','',CONCAT(' - ',st.section))) as classAndSection
from studyAndBonafiedSettings stc  LEFT JOIN studyClass st on (stc.studyClassId=st.id and stc.custId=st.custId);


create or replace view vw_voucherDetails as 
select ifnull(v.id, 0) AS voucherId,ifnull(vd.id, 0) AS voucherDetailsId,v.custId,v.financialYearId,ifnull(v.fromAccountId, 0) AS fromAccountId ,v.voucherDate,v.voucherNo,vd.status,IFNULL(vd.amount,0) amount,vd.narration,IFNULL(vd.toAccountId,0) toAccountId ,a.groupName,l.ledgerName,ac.name,IFNULL(v.totalAmount,0) totalAmount,ifnull(l.id, 0) AS ledgerId 
from voucher v
left join voucherDetails vd ON (vd.voucherId = v.id)
left join ledgerDetails l ON (l.id = vd.toAccountId)
left join accountGroup a ON (a.id = l.accountGroupId)
left join accountCategorys ac ON (ac.id = a.accountCategorysId); 


CREATE OR REPLACE VIEW vw_suspendAndBlacklistStudents AS
SELECT stc.id as suspendStudentId,stc.custId,stc.blackedOrSuspendToDate,stc.blackedOrSuspendFromDate,stc.academicYearId,stc.suspendDays,stc.status,stc.description,stc.studentId,st.admissionNumber,st.fullName,st.classAndSection,st.studyClassId
from suspendAndBlacklistStudents stc  LEFT JOIN vw_studentClassDetails st  on (stc.studentId=st.studId and stc.custId=st.custId);


CREATE OR REPLACE VIEW vw_accountGroupCategory AS
SELECT ag.id as accountGroupId,ag.groupName,ag.custId,ag.status,agc.name,ag.accountCategorysId as categoryId from accountGroup ag  LEFT JOIN accountCategorys agc  on (ag.accountCategorysId=agc.id);

CREATE OR REPLACE VIEW vw_studentConcessionClassFees AS 
select vsc.feeId,ifnull(vsc.feeAmount, 0) AS feeAmount,vsc.custId,vsc.feeTypeId,vsc.schoolTermId,IFNULL(vsc.classId,0) as classId,vsc.academicYearId,vsc.categoryId,vsc.studentId,vsc.rollNumber,
vsc.accountId,vsc.status,vsc.description,IFNULL(vsc.classSectionId,0) as classSectionId,IFNULL(sfc.concessionAmount,0) as concessionAmount,IFNULL(sfc.id,0) as concessionId,vsc.feeSettingId as feeSettingId 
from vw_studentClassFees vsc 
left join studentFeeConcession sfc ON (vsc.studentId=sfc.studentId and vsc.feeId = sfc.feeId and vsc.custId = sfc.custId and vsc.academicYearId = sfc.academicYearId and vsc.feeAmount <> '0');


CREATE OR REPLACE VIEW vw_meetingSchedules AS
SELECT 
	payment_SequanceId() AS `id`,
	ms.id AS meetingSlotId,
	m.id AS meetingScheduleId,
	m.date,
	m.description,
	m.staffId,
	s.accountId AS staffAccountId,
	CONCAT(p.firstName,' ', IFNULL(p.lastName, '')) AS staffName,
	IFNULL(sc.id,0) AS studyClassId,
	sc.className,
	sc.section,
	ms.slotType,
	ms.slotTime,
	IFNULL(ms.accountId, 0) AS parentAccountId,
	CONCAT(pp.firstName,' ', IFNULL(pp.lastName, '')) AS parentName,
	m.isAuto,
	m.custId
FROM meetingSlots ms
LEFT JOIN meetingSchedules m ON(m.id = ms.meetingScheduleId)
LEFT JOIN staff s ON(s.id = m.staffId)
LEFT JOIN studyClass sc ON(sc.id = m.studyClassId)
LEFT JOIN Account a ON(a.id = s.accountId)
LEFT JOIN Person p ON(p.id = a.personId)
LEFT JOIN Account pa ON(pa.id = ms.accountId)
LEFT JOIN Person pp ON(pp.id = pa.personId);

CREATE OR REPLACE VIEW vw_substitutionTimeTable AS
SELECT 
    payment_SequanceId() AS `id`,
    tt.id AS timeTableId,
    tt.classSectionId AS classSectionId,
    IFNULL(tt.subjectId, 0) AS subjectId,
    tt.dayId AS `dayId`,
    `tt`.`periodName` AS `periodName`,
    `tt`.`custId` AS `custId`,
    `tt`.`academicYearId` AS `academicYearId`,
    IFNULL(`tt`.`staffId`, 0) AS `teacherId`,
    CONCAT(IF(ISNULL(`sc`.`className`), '', `sc`.`className`), IF((ISNULL(`sc`.`section`) OR (`sc`.`section` = '')), '', CONCAT(' - ', `sc`.`section`))) AS `classAndSection`,
    `ss`.`name` AS `subjectName`,
    `wd`.`dayName` AS `dayName`,
    `vsd`.`firstName` AS `firstName`,
    `vsd`.`lastName` AS `lastName`,
    `sc`.`className` AS `className`,
    `sc`.`section` AS `section`,
    `sc`.`classNameClassId` AS `classId`,
    CONCAT(IF(ISNULL(`vsd`.`firstName`), '', `vsd`.`firstName`), IF((ISNULL(`vsd`.`lastName`) OR (`vsd`.`lastName` = '')), '', CONCAT(', ', `vsd`.`lastName`))) AS `staffFullName`
FROM
    `substitutionTimeTable` `tt`
    LEFT JOIN `studyClass` `sc` ON (`tt`.`classSectionId` = `sc`.`id`)
    LEFT JOIN `studySubject` `ss` ON (`tt`.`subjectId` = `ss`.`id`)
    LEFT JOIN `weekDays` `wd` ON (`tt`.`dayId` = `wd`.`id`)
    LEFT JOIN `vw_staffDetails` `vsd` ON (`vsd`.`staffId` = `tt`.`staffId`);

CREATE OR REPLACE VIEW vw_routeTrack AS
SELECT 
	rt.id,
	rt.latitude,
	rt.longitude,
	rt.address,
	rt.time,
	rt.speed,
	r.id AS routeId,
	r.routeName,
	vad.driverId,
	CONCAT(p.firstName,' ', IFNULL(p.lastName, '')) AS driverName,
	vad.vehicleId
FROM routeTrack rt
LEFT JOIN route r ON(r.id = rt.routeId)
LEFT JOIN RouteWithVehicles rv ON(rv.routeId = r.id)
LEFT JOIN vehiclesAcademicDetails vad ON(vad.id = rv.vehicleAcademicId)
LEFT JOIN Account a ON(a.id = vad.driverId)
LEFT JOIN Person p ON(p.id = a.personId);


CREATE OR REPLACE VIEW vw_ledgerDetailsWithAccountCategories AS
SELECT ifnull(a.id, 0) AS accountGroupId,ifnull(l.id, 0) AS ledgerId,a.groupName,l.ledgerName,a.status,l.custId,ac.id as accountCatageryId
from  ledgerDetails l left join accountGroup a ON (l.accountGroupId = a.id) left join accountCategorys ac ON (a.accountCategorysId = ac.id);

    
CREATE OR REPLACE VIEW vw_staffPaySlipDetails AS
select sm.id AS staffPaySlipIds, ps.id as paySlipId,ps.custId,ps.monthId,ps.yearName,ps.monthName,sm.accountId,ifnull(ur.roleId, 0) AS roleId,ifnull(ur.roleName,'') AS roleName,ur.roleDescription AS roleDescription,
p.firstName AS firstName,concat(p.firstName,' ',IFNULL(p.lastName,'')) as fullName,ifnull(p.mobileNumber,'') AS mobileNumber,d.email AS email,sm.lastUpdatedDate
from staffMonthlyPaySlips sm 
left join  paySlips ps ON (ps.id = sm.paySlipId) 
left join Account a ON (a.id = sm.accountId) 
left join Person p ON(p.id=a.personId) 
left join Address d ON (a.paddressId = d.id)
left join vw_userRoles ur ON(sm.accountId=ur.accountId) ;


CREATE OR REPLACE VIEW vw_messagesDetails AS
SELECT ifnull(a.id, 0) AS accountId,
ifnull(ms.id, 0) AS messageId,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as senderName,
ms.invalidMobileNos AS invalidMobileNos,
ms.failedMobileNos AS failedMobileNos,
ms.messageDate AS messageDate,
ms.sentSms AS sentSms,
ms.smsCount AS smsCount,
ms.status AS status,
ms.messageDescription AS messageDescription,
ms.custId AS custId,
ms.academicYearId AS academicYearId,
ms.createdDate AS createdDate,
r.description as roleName,
ms.createdById
from  messages ms
left join Account a ON (a.id = ms.createdById)
left join Person p ON (a.personId = p.id)
left join UserRoles ur on (ur.userId=a.id)
left join Role r on (r.id=ur.roleId);


CREATE OR REPLACE VIEW vw_classWiseFeeStructure AS
select t.termName AS termName,cl.className AS className,f.feeAmount AS feeAmount,
f.classId AS classId,c.id AS categoryId,ifnull(t.id,0) AS schoolTermId,
f.custId AS custId,f.academicYearId AS academicYearId
from schoolTerms t 
left join Fee f on (f.schoolTermId = t.id and t.custId = f.custId and t.academicYearId = f.academicYearId and f.feeAmount <> '0' )
left join schoolCategory c on (f.categoryId = c.id and f.custId = c.custId)
left join academicYear a on (a.id = f.academicYearId and a.custId = f.custId)
left join class cl on (cl.id=f.classId);

CREATE OR REPLACE VIEW vw_partucularToAccount 
AS select payment_SequanceId() AS id,ft.id as feeTypeId,ft.feeType,ft.academicYearId,ft.custId as custId,IFNULL(fpa.id,0) as fpaId,IFNULL(fad.id,0) as finAccountId,IFNULL(fad.financialYearId,0) as financialYearId
from feeType ft  LEFT JOIN finFeeParticularAssociation fpa  on (ft.id=fpa.paticularId) LEFT JOIN finAccountDetails fad  on (fad.id=fpa.finAccountId) ;

CREATE OR REPLACE VIEW vw_finAccountDetails 
AS select payment_SequanceId() AS id,a.id as accountId,a.custId,a.accountName,a.tinNumber,a.tinIssueDate,a.gstNumber,a.gstIssueDate,a.itPanNumber,
ac.id as categoryId,ac.cartegoryName,fas.id as statementId,fas.statementName,fas.statmentCode,
IFNULL(fc.id,0) as customerDetailsId,fc.customerName,fc.contactName,fc.mobileNumber,IFNULL(ad.id,0) as addressId,ad.city,ad.addressLine1,ad.streetName,ad.postalCode,ad.country,ad.state,ad.email,
IFNULL(at.id,0) as accountTotalId,at.transactionType,IFNULL(at.balanceAmount,0) as balanceAmount,fat.id accountTypeId,fat.accountType,fat.accountCode,
IFNULL(fy.id,0) AS financialYearId,fy.yearName,fy.startDate,fy.endDate,fy.status
from finAccountDetails a  
LEFT JOIN finAccountCategory ac  on (ac.id=a.finAccountCategoryId) 
LEFT JOIN finAccountStatement fas  on (fas.id=ac.statementId) 
LEFT JOIN finCustomerDetails fc on (fc.id=a.finCustomerDetailsId)
LEFT JOIN finAccountTotal at on (at.finAccountId=a.id)
LEFT JOIN Address ad on (ad.id=fc.addressId)
LEFT JOIN financialYear fy on (fy.id=at.financialYearId)
LEFT JOIN finAccountType fat on (fat.id=a.accountTypeId);

CREATE OR REPLACE VIEW  vw_trialBalance  AS
select a.custId as custId,a.id  AS  accountId,a.AccountName  AS  AccountName,
(case when ( at.transactionType  = 'C') then  at.balanceAmount when ( at.transactionType  = 'D') then ( at.balanceAmount  * -(1)) end) AS  TrxAmount ,
c.cartegoryName  AS  cartegoryName,s.statmentCode  AS  statementCode,s.statementName  AS  statementName,at.financialYearId as financialYearId
from finAccountDetails   a join finAccountTotal at ON (at.finAccountId=a.id)
join finAccountCategory c ON ( a.finAccountCategoryId  =  c.id)
join finAccountStatement s ON ( c.statementId  =  s.id ) order by  a.AccountName;

CREATE OR REPLACE VIEW vw_userJRExceptionDetails AS
select jre.id as jrExceptionId, jre.userId, jre.custId, ifnull(jre.academicYearId, 0) as academicYearId, jre.exceptionName,
	jre.methodName, jre.className, jre.fileName, jre.exceptionLineNumber, jre.cause, jre.createdDate as exceptionDate, date(jre.createdDate) as exceptionDateWithoutTime,
	jre.status, jre.ipAddress, jre.computerName, jre.computerUsername,
	c.firstName as custFirstName, c.lastName as custLastName, concat(c.firstName,' ',c.lastName) as custFullName, c.custEmail, c.organization, ifnull(c.accountType, 'D') as accountType,
	a.username, ifnull(a.personId,0) as personId, p.firstName, p.lastName, concat(p.firstName,' ',p.lastName) as fullName,
	ifnull(c.addressId,0) as addressId, d.addressLine1, d.streetName, d.city, d.state as stateCode,
	CONCAT(IFNULL(concat(d.addressLine1, ', '), ''),IFNULL(concat(d.streetName, ', '), ''),CHAR(10),IFNULL(concat(d.city, ', '), ''),IFNULL(concat(d.state, '. '), ''),IFNULL(concat('Ph ', c.mobileNumber, '.'),'')) as custAddress,
	r.id as roleId, r.name AS roleName, r.description AS roleDescription,
	jred.exceptionDescription
from jrException jre 
	LEFT JOIN jrExceptionDetails jred on (jre.id = jred.jrExceptionId)
	LEFT JOIN Account a on (jre.userId = a.id)
	LEFT JOIN customer c on (jre.custId = c.id)
	LEFT JOIN Person p on (a.personId = p.id)
	LEFT JOIN Address d on (c.addressId = d.id)
	LEFT JOIN UserRoles ur on (a.id = ur.userId)
	LEFT JOIN Role r on (ur.roleId = r.id);

create or replace view vw_studentFeeChallanaDetails as
SELECT payment_SequanceId() AS id,IFNULL(sp.id, 0) as studentPaymentId,IFNULL(sp.paidAmount, 0) as paidAmount,IFNULL(sfp.discountAmount, 0) as discountAmount,
sp.lastUpdatedDate,IFNULL(sp.invoiceNumber, 0) as invoiceNumber,IFNULL(sp.deleteStatus, 'N') as deleteStatus,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,
IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,
IFNULL(f.id,0) as feeId,(IF(f.id<>0,IFNULL(f.feeAmount, 0),((IFNULL(prb.boardingPointFeeAmount,0)+IFNULL(drb.boardingPointFeeAmount,0))/2))) as feeAmount,sp.custId,
IFNULL(s.classNameClassId,0) as classId,sp.academicYearId,IFNULL(c.sortingOrder,0) as sortingOrder,
IFNULL(ft.id,0) as feeTypeId,ft.feeType as feeType,
IFNULL(st.id,0) as schoolTermId,st.fromdate,st.toDate,IFNULL(st.feeSettingId,0) as feeSettingId,IFNULL(st.applToNewStuds,'N') as applToNewStuds,st.fromMonthName,st.toMonthName,st.termName,st.dueDate,st.dueDate2,st.dueDate1,
sfs.settingName,sfs.settingType,
s.categoryId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.joinedThroughAdmissions,s.hostelMode,
sc.className,sc.section,sc.id as classSectionId,IFNULL(sfp.committedFeeStatus,'N') as paymentCommitFeeStatus,IFNULL(sp.createdById,0) as financeUserId,IFNULL(sfc.id,0) as concessionId,
IFNULL(sfc.concessionAmount,0) as concessionAmount,IFNULL(sfp.concessionAmount,0) as paymentConcessionAmount,IFNULL(cp.challanaNumber,0) as challanaNumber,IFNULL(std.id,0) as transportFeeId,cp.challanaDate,IFNULL(cp.id,0) as challanaId,
a.admissionNumber as admissionNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,
CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  = '','',CONCAT(' - ',sc.section))) as classAndSection
FROM studentPayment sp JOIN student s ON (sp.custId = s.custId and sp.academicYearId = s.academicYearId and sp.studentId=s.id and sp.deleteStatus = 'C')
LEFT JOIN challanaPayment cp ON (cp.studentPaymentId=sp.id)
LEFT JOIN class c ON (c.id = s.classNameClassId)
LEFT JOIN studyClass sc ON (sc.id = s.classSectionId)
LEFT JOIN studentFeePaidDetails sfp ON (s.id = sfp.studentId and sfp.studentPaymentId=sp.id and s.custId = sfp.custId and sfp.deleteStatus = 'C')
LEFT JOIN studentTransportDetails std ON (std.id=sfp.studTransportDetailsId)
LEFT JOIN Fee f ON (f.id=sfp.feeId and f.custId=sfp.custId)
LEFT JOIN studentFeeConcession sfc ON (s.id=sfc.studentId and (f.id=sfc.feeId OR std.id=sfc.studTransportDetailsId))
LEFT JOIN schoolTerms st ON ((st.id=f.schoolTermId OR st.id=std.termId))
LEFT JOIN feeType ft ON ( (ft.id=f.feeTypeId OR ft.id=std.feeTypeId) )
LEFT JOIN schoolFeeSetting sfs ON ((sfs.id=st.feeSettingId))
LEFT JOIN routeBoardingPoints prb ON (prb.id = std.pickupBoardingPointId and prb.custId = std.custId and prb.academicYearId = std.academicYearId)
LEFT JOIN routeBoardingPoints drb ON (drb.id = std.dropBoardingPointId and drb.custId = std.custId and drb.academicYearId = std.academicYearId)
LEFT JOIN Account a ON(a.id=s.accountId)
LEFT JOIN Person p ON(p.id=a.personId);			
	


/* This view used only for getting latest marks updated class with exam types*/
CREATE OR REPLACE VIEW  vw_studentsLatestExamMarksDetails AS
select sc.className,sc.id AS classSectionId,sc.classNameClassId,sc.section,et.id AS examTypeId,et.examType as examTypeName,ifnull(sm.id,0) AS marksId,
es.custId,es.academicYearId,es.startDate AS examStartDate,sm.lastUpdatedDate AS lastUpdatedDate,et.minMarks,sm.studId,sm.obtainedMarks,es.classSubjectId as subjectId,sm.present,s.status AS status
from studentMarks sm
JOIN examSchedules es ON (es.id=sm.examScheduleId)
JOIN examTypes et ON (et.id=es.examTypeId and et.custId=es.custId and et.academicYearId=es.academicYearId)
JOIN studyClass sc  ON (sc.id=es.classSectionId)
JOIN student s ON (s.id=sm.studId);
/*group by sm.id,et.id,sc.classNameClassId;*/

/* This view used for to find out the student Admission Numbers with with classSectionWise */
create or replace view vw_studentAdmissionNumberWithSectionWise as
select a.admissionNumber,st.id as studyClassId,st.custId,st.academicYearId,a.id as accountId,s.status,s.description,
CAST(CONCAT(IF(st.className IS NULL, '', st.className),IF(st.section IS NULL || st.section = '','',CONCAT('-', st.section))) AS CHAR(60)) as classNameAndSection
from studyClass st join student s on (st.id = s.classSectionId and st.custId=s.custId and st.academicYearId=s.academicYearId and s.status='Y' and s.description is  null )
join Account a on(a.id=s.accountId and a.admissionNumber is not null and a.accountEnabled='Y') ;

/* This view used for to import the student details from Excel sheet */
create or replace view vw_importStudentDetails as
SELECT a.id as accountId,a.custId,a.username,sp.parentAccountId AS parentId,a.version,a.sharePrivacy,a.userOnlineNow,a.accountExpired,a.admissionNumber,a.barcode,s.id as studentId,s.academicYearId,
s.description,s.createdDate,s.lastAccessDate,ifnull(s.hostelMode,'D') as hostelMode,s.status,ifnull(s.transportMode, 'O') as transportMode,s.classNameClassId,s.rollNumber,s.joinedThroughAdmissions,
s.categoryId,IFNULL(p.height,0) as height,IFNULL(p.weight,0) as weight,p.teeth,p.visionL,p.visionR,p.oralHygiene,p.firstName,p.lastName,p.middleName,p.fatherName,p.motherName,p.mothersMaidenName,p.dateOfBirth,
UCASE(IFNULL(p.bloodGroup,'')) as bloodGroup,p.mobileNumber,p.phoneNumber,p.parentEmail,p.summary,p.dateOfJoining,IFNULL(p.castId, 0) as castId,IFNULL(p.subCastId, 0) as subCastId,
IFNULL(p.religionId, 0) as religionId,IFNULL(p.nationality,'') as nationality,p.identification1,p.identification2,p.gender,IFNULL(p.motherToungId, 0) as motherToungId,p.rationCardNumber,IFNULL(p.id, 0) as personId,IFNULL(d.id, 0) as paddressId,
p.communityNumber,IF(p.phId ='Y','Yes','No') AS phId,d.addressLine1,d.addressLine2,d.city,d.districtName,d.state,IFNULL(d.postalCode,'') as postalCode,s.lastUpdatedDate,c.className,c.section,
CAST(CONCAT(IF(c.className IS NULL, '', c.className),IF(c.section IS NULL || c.section = '','',CONCAT(' - ', c.section))) AS CHAR(60)) as classNameAndSection,
IFNULL(c.mediumId, 0) as mediumId,c.filledSeats,c.sectionCapacity,cl.higherStandard,ur.roleName,ur.roleDescription,ifNULL(ur.roleId,3) as roleId,IFNULL(ui.id,0) as imageId,
ui.name as imageName,ui.path as imagePath,ui.thumbNail,IFNULL(c.id, 0) as classSectionId,cs.castName,IFNULL(scs.subCastName, '') as subCastName,p.FatherOccupation as fatherOccupation,
p.motherOccupation,IFNULL(p.annualIncome,0) as annualIncome,p.sslcNumber,p.tmrNumber,p.classJoined,p.prefferedHospital,p.placeOfBirth,p.lastSchool,p.studentEmail,p.studentMobile,
d.streetName,IFNULL(st.stateName, '') as stateName,cst.revenueDistrict,cst.custEmail,cst.contactNumber,cst.educationalDistrict,cst.organization,cst.customerShortName,
cst.custImageId,cst.schoolCode,cst.affiliationNumber,acy.academicYear,ct.skillTypeName as religion,cst.boardOfEducation,cst.recognisedBy,if(cst.transportModuleStatus ='Y','TRUE','FALSE') AS transportModuleStatus,
if((s.boardingPointId > 0 && s.TransportMode='T'),'TRUE','FALSE') AS studentTransportStatus,IFNULL(s.bedId, 0) as bedId,ct.skillTypeName as medium,
IFNULL(s.boardingPointId, 0) as boardingPointId,IFNULL(v.id, 0) as vehicleId,IFNULL(s.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,cstUi.name as custImageName,
cstUi.path custImagePath,cstUi.thumbNail as custThumbNail,va.name as vehicleName,
CONCAT(IFNULL(concat(custAddr.addressLine1, ', '), ''),IFNULL(concat(custAddr.streetName, ', '), ''),CHAR(10),IFNULL(concat(custAddr.city, ' - '), ''),IFNULL(concat(custAddr.postalCode, ', '), ''),IFNULL(concat('Ph - ', cst.contactNumber, '.'),'')) as customerFullAddress,
CONCAT(IFNULL(concat(d.streetName, ', '), ''),IFNULL(concat(d.city, ' - '), ''),CHAR(10),IFNULL(concat(d.postalCode, '. '), ''),IFNULL(concat(' Ph - ', p.mobileNumber, '.'), '')) as parentFullAddress,
IFNULL(s.registerNumber, '') as registerNumber,IFNULL(ifnull(concat(va.name, '-', br.boardingPointName),concat(v.vehicleNumber,'-',br.boardingPointName)),'') AS VehicleBoardingPointname,v.vehicleNumber,IFNULL(br.boardingPointFeeAmount,0) boardingPointFeeAmount,
IFNULL(r.routeName, '') as routeName,IFNULL(r.id, 0) as routeId,cl.sortingOrder,
(case when (s.TransportMode = 'O') then 'Own' when (s.TransportMode = 'P') then 'Private' when (s.TransportMode = 'T') then 'School Transport' else '' end) AS 'TransportName',
ctype.name as mediumOfStudy,mtype.name as motherToung,p.relievingDate,p.scholarShipInfo,p.scholarShipAmount,p.tcAppliedDate,p.tcIssuedDate,p.studentUniqueNumber,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,br.boardingPointName,
sc.categoryName,p.aadharNumber,s.outSideSchoolDuty,s.committedFee,s.goals,s.strengths,s.interestsAndHobbies,s.responsibilities,s.achievements,IFNULL(p.height2,0) as height2,IFNULL(p.weight2,0) as weight2,
acy.nextAcademicStartDate as schoolReOpenDate,s.remarks,s.promoteToClass,p.secondaryMobileNumber,p.addressType,p.anotherMobileNumber,p.anotherSecondaryMobileNumber,p.anotherParentEmail,s.schoolMess,a.enrollmentCode,s.ptaStatus,IF(s.rteStatus ='Y','Yes','No') AS rteStatus,IF(s.bplStatus ='Y','Yes','No') AS bplStatus,
s.bplNumber,p.physicallyHandicappedDesc,cst.barcodeStatus AS barcodeStatus,p.fatherAadharNumber,p.motherAadharNumber,upi.path AS principalDigitalSignaturePath,p.fatherQualification, p.motherQualification, 
pod.noOfDependents,pod.scstCommunity,pod.siblingName1,pod.siblingName2,pod.siblingName3,pod.motherEmail,
if(p.sameAsResidentialAddress ='No',ta.addressLine1,d.addressLine1) AS corAddressLine1, if(p.sameAsResidentialAddress ='No',ta.addressLine2,d.addressLine2) AS corAddressLine2, if(p.sameAsResidentialAddress ='No',ta.city,d.city) AS corCity,if(p.sameAsResidentialAddress ='No',ta.districtName,d.districtName) AS corDistrictName, 
if(p.sameAsResidentialAddress ='No',corst.stateName,st.stateName) AS corStateName,if(p.sameAsResidentialAddress ='No',ta.postalCode,d.postalCode) AS corPostalCode,
ped.fatherOrganizationName,ped.fatherDesignation,ped.fatherSelfEmployed,ped.fatherNatureofBusiness,ped.motherOrganizationName,ped.motherDesignation,ped.motherSelfEmployed,ped.motherNatureofBusiness,ped.motherAnnualIncome,
phd.allergies,phd.heartProblem,phd.diabetes,phd.asthma,phd.otherMedicalCondition,p.familyDoctor,phd.doctorsContactNo,phd.emergencyNo1,phd.emergencyNo2,p.sameAsResidentialAddress,IFNULL(rht.type,"") AS houseType,
p.studentBankId as studentBankId, p.stsNumber as stsNumber

FROM student s JOIN Account a ON (s.accountId = a.id)

LEFT JOIN Person p ON (a.personId = p.id)
LEFT JOIN Address d ON (a.paddressId = d.id)
LEFT JOIN State st ON (st.stateCode = d.state)
LEFT JOIN studyClass c ON (c.id = s.classSectionId)
LEFT JOIN academicYear acy ON (acy.id = s.academicYearId)
LEFT JOIN schoolCategory sc ON (sc.id = s.categoryId)
LEFT JOIN vw_userRoles ur ON (ur.accountId = s.accountId)
LEFT JOIN UserImage ui ON (ui.id = s.imageId)
LEFT JOIN castSettings cs ON (cs.id = p.castId)
LEFT JOIN subCastSettings scs ON (scs.id = p.subCastId)
LEFT JOIN class cl ON (cl.id = s.classNameClassId)
JOIN customer cst ON (a.custId = cst.id)
LEFT JOIN Address custAddr ON (custAddr.id = cst.addressId)
LEFT JOIN UserImage cstUi ON (cstUi.id = cst.custImageId)
LEFT JOIN commonType ct ON (ct.id = p.religionId and ct.type = 'RELIGION')
LEFT JOIN medium ctype ON (ctype.id = c.mediumId)
LEFT JOIN motherTongue mtype ON (mtype.id = p.motherToungId)
LEFT JOIN vehiclesAcademicDetails va on (va.id=s.vehicleAcademicDetailsId)
LEFT JOIN vehicles v ON (v.id = va.vehicleId)
LEFT JOIN routeBoardingPoints br ON (br.id = s.boardingPointId)
left join route r ON (br.routeId = r.id)
left join UserImage upi ON (upi.id = cst.principalDigitalSignatureId)
LEFT JOIN Address ta ON (a.taddressId = ta.id)
LEFT JOIN State corst ON (corst.stateCode = ta.state)
left join PersonOtherDetails pod ON (p.personOtherDetailsId = pod.id)
left join ParentsEmploymentDetails ped ON (p.parentsEmploymentDetailsId = ped.id)
left join PersonHealthDetails phd ON (p.personHealthDetailsId = phd.id)
LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id)
LEFT JOIN Ref_HouseType rht ON(rht.id=d.houseTypeId);


CREATE OR REPLACE VIEW vw_examScheduleDetails AS
select sc.id AS classSectionId,sc.academicYearId AS academicYearId,sc.className AS className,sc.section AS section,et.id AS examTypeId,et.examType AS examTypeName,
es.examDate AS examDate,sc.custId AS custId,ifnull(es.id, 0)  AS scheduleId,st.id AS subTypeId,es.endDate endDate,st.subTypeName AS subTypeName,
st.schedule AS subTypeSchedule,ifnull(es.scheduled, 'N') AS scheduled,c.id AS classId,ifnull(c.sortingOrder, 0) AS classSortingOrder,
ifnull(et.sortingOrder, 0) AS examTypeSortingOrder,ifnull(st.sortingOrder, 0) AS subTypeSortingOrder,es.startDate as startDate,et.maxMarks as maxMarks,
CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  = '','',CONCAT(' - ',sc.section))) as classAndSection,ss.name as subjectName,
es.startTime,es.endTime 
from examTypesAndSubTypes exs left join classSectionExamTypes ce ON(ce.examTypeId = exs.examTypeId)
left join examTypes et ON(et.id = ce.examTypeId)
left join subType st ON(st.id = exs.subTypeId)
left join studyClass sc ON(ce.classSectionId = sc.id)
left join class c ON(c.id = sc.classNameClassId)
left join examSchedules es ON (es.classSectionId = sc.id and es.examTypeId = et.id and es.subTypeId = st.id)
left join studySubject ss ON (ss.id=es.classSubjectId);

/* Babu- This view used for show the all deleted Regular/Fine fee invoices */
create or replace view vw_studentDeleteFeeDetails as
select a.admissionNumber AS admissionNumber,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) AS studentFullName,
CAST(CONCAT(IF(sc.className IS NULL, '', sc.className),IF(sc.section IS NULL || sc.section = '','',CONCAT(' - ', sc.section))) AS CHAR(60)) as classNameAndSection,
'R'feeType,
sp.id AS studentPaymentId, 
sp.invoiceNumber AS invoiceNumber,  
sp.academicYearId AS academicYearId,  
DATE(sp.paymentDate) AS paymentDate, 
sp.deleteStatus AS deleteStatus,
sp.paidAmount,
sp.discountAmount,
dsp.createdDate AS deleteDate,
dsp.deleteRemarks AS deleteRemarks,
dsp.reportedPerson AS reportedPerson,
dsp.supportPersonName AS supportPersonName,
s.custId AS custId
from studentPayment sp 
join deleteStudentPayment dsp ON (dsp.studentPaymentId = sp.id)
join student s ON (s.id = sp.studentId)
join studyClass sc ON (sc.id=s.classSectionId)
join Account a ON(a.id=s.accountId) 
join Person p  ON (a.personId = p.id) Where sp.deleteStatus='Y' and dsp.deleteRemarks is not null
UNION ALL
select a.admissionNumber AS admissionNumber,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) AS studentFullName,
CAST(CONCAT(IF(sc.className IS NULL, '', sc.className),IF(sc.section IS NULL || sc.section = '','',CONCAT(' - ', sc.section))) AS CHAR(60)) as classNameAndSection,
'F'feeType,
FF.id AS studentPaymentId, 
FF.invoiceNumber AS invoiceNumber,  
FF.academicYearId AS academicYearId,  
DATE(FF.paymentDate) AS paymentDate,
FF.deleteStatus AS deleteStatus,
FF.fineFeeAmount,
'0.00'discountAmount,
dsp.createdDate AS deleteDate,
dsp.deleteRemarks AS deleteRemarks,
dsp.reportedPerson AS reportedPerson,
dsp.supportPersonName AS supportPersonName,
s.custId AS custId
from fineFee FF 
join deleteStudentPayment dsp ON (dsp.otherFeeId = FF.id)
join student s ON (s.id = FF.studentId)
join studyClass sc ON (sc.id=s.classSectionId)
join Account a ON(a.id=s.accountId) 
join Person p  ON (a.personId = p.id) Where FF.deleteStatus='Y' and dsp.deleteRemarks is not null;


/*Ravi Theja Panem  This view used for show Task Details*/
create or replace view vw_taskDetailsAndTaskHistory as
select td.id AS taskDetailsId,
th.id as taskHistoryId,
th.comments AS comments,
th.taskDate AS taskDate,
td.taskName AS taskName,
td.description AS description,
td.custId AS custId,
td.academicYearId AS academicYearId,
th.staffAccountId as accountId,
th.status AS status, 
th.taskHistoryDate AS taskHistoryDate,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) AS staffName,
td.createdById AS taskCreator,
td.specificDate as specificDate,
td.reminderOption as reminderOption,
td.checkMobileService as checkMobileService,
td.checkEmailService as checkEmailService
from taskHistory th 
left join  taskDetails td on (td.id = th.taskDetailsId)
left join Account a on(a.id=th.staffAccountId ) 
left join Person p  on (a.personId = p.id)
where taskHistoryDate = (select max(taskHistoryDate) from taskHistory th1 where th1.taskDetailsId = td.id);

/* Siva: 14/09/2017 - This view is for getting student transport fee details based on term wrt route boarding points*/
create or replace view vw_studentTransportFeePaymentDetails as SELECT 
    payment_SequanceId() AS id,IFNULL(sp.id, 0) as studentPaymentId,IFNULL(sp.paidAmount, 0) as paidAmount,IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sp.transactionNumber,0) as transactionNumber,IFNULL(sp.bankId, 0) as bankId,
    sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,sp.ddNumber,bm.bankName,sp.chequeNumber,IFNULL(sp.invoiceNumber, 0) as invoiceNumber,IFNULL(sp.fineAmount,0) as fineAmount,
    IFNULL(sp.deleteStatus, 'N') as deleteStatus,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,sp.paymentDate,IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,
    stf.id AS transportFeeId,((IFNULL(prb.boardingPointFeeAmount,0)+IFNULL(drb.boardingPointFeeAmount,0))/2) AS feeAmount,
    stf.custId,stf.feeTypeId,stf.termId as schoolTermId,IFNULL(c.id,0) as classId,stf.academicYearId,c.sortingOrder,'Transport Fee'as feeType,
    st.fromdate,st.toDate,st.feeSettingId,sfs.settingName,sfs.settingType,st.applToNewStuds,st.fromMonthName,st.toMonthName,st.termName,st.dueDate,st.dueDate2,st.dueDate1,
    IFNULL(stf.pickupBoardingPointId,0) AS pickupBoardingPointId,IFNULL(stf.dropBoardingPointId,0) AS dropBoardingPointId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,IFNULL(s.vehicleAcademicDetailsId,0)as vehicleAcademicDetailsId,s.hostelMode,s.committedFee,
    s.boardingPointId,sc.className,sc.section,sc.id as classSectionId,a.username,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
    p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,
    IFNULL(sfp.committedFeeStatus,'N') as paymentCommitFeeStatus,sp.createdById as financeUserId,IFNULL(sfc.id,0) as concessionId,IFNULL(sfc.concessionAmount,0) as concessionAmount,IFNULL(sfp.concessionAmount,0) as paymentConcessionAmount,
    sp.invoiceString AS invoiceString,sp.desktopReceiptNumber,s.feePaidStatus,s.feeConfigured,IFNULL(sp.totalBalanceAmount,0) as totalBalanceAmount,IFNULL(sp.termwiseTotalBalanceAmount,0) as termwiseTotalBalanceAmount,
CONCAT(IF(pa.gender='M','Mr.',IF(pa.gender='F',if(pa.maritalStatus='M','Mrs.','Ms.') ,'')),IF(pa.firstName IS NULL,'',pa.firstName),IF(pa.lastName IS NULL || pa.lastName  = '','',CONCAT(' ',pa.lastName))) as personFullName,sp.concessionStatus concessionStatus
FROM studentTransportDetails stf LEFT JOIN student s ON 
(stf.studentId = s.id and stf.custId = s.custId and stf.academicYearId = s.academicYearId)
    left join class c ON (c.id = s.classNameClassId)
    left join studentFeePaidDetails sfp ON (s.id = sfp.studentId and stf.id = sfp.studTransportDetailsId and s.custId = sfp.custId and sfp.deleteStatus = 'N')
    left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId = sfp.studentId  and sp.deleteStatus = 'N')
    left join studentFeeConcession sfc ON (s.id=sfc.studentId and stf.id=sfc.studTransportDetailsId)
    left join Account a ON (s.accountId = a.id)
    left join bankMaster bm ON (sp.bankId = bm.id)
    LEFT JOIN Person p ON (a.personId = p.id)
    left join Account ac ON (sp.lastUpdatedById = ac.id)
    LEFT JOIN Person pa ON (ac.personId = pa.id)
    LEFT JOIN studyClass sc ON (s.classSectionId=sc.id)
    LEFT JOIN schoolTerms st ON (st.id = stf.termId)
    LEFT JOIN schoolFeeSetting sfs ON (sfs.id = st.feeSettingId)
    left join routeBoardingPoints prb ON (prb.id = stf.pickupBoardingPointId and prb.custId = stf.custId and prb.academicYearId = stf.academicYearId)
    left join routeBoardingPoints drb ON (drb.id = stf.dropBoardingPointId and drb.custId = stf.custId and drb.academicYearId = stf.academicYearId)
where p.firstName <> '';

-- Siva: Used for displaying student transport fee particular payment  
CREATE OR REPLACE VIEW vw_studentTransportParticularwiseFeePayments AS 
select sfp.feePaidDetailsId AS feePaidDetailsId,sfp.custId AS custId,sfp.deleteStatus AS deleteStatus,sum(sfp.discountAmount) AS discountAmount,sum(sfp.paymentAmount) AS paymentAmount,sum(sfp.concessionAmount) AS concessionAmount, sfp.paymentStatus AS paymentStatus,
sfp.studentId AS studentId,sfp.studentPaymentId AS studentPaymentId,sfp.studTransportDetailsId AS studTransportDetailsId,sfp.futureFeeStatus AS futureFeeStatus
from studentFeePaidDetails sfp where sfp.deleteStatus='N' group by sfp.studentId , sfp.studTransportDetailsId;

-- Siva: Used for displaying student transport fee payment   
create or replace view vw_studentTransportFeeParticularsPayment as
SELECT 
    payment_SequanceId() AS id,
    IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sfp.deleteStatus, 'N') as deleteStatus,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,
    IFNULL(sfp.feePaidDetailsId,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,
    stf.id AS transportFeeId,((IFNULL(prb.boardingPointFeeAmount,0)+IFNULL(drb.boardingPointFeeAmount,0))/2) AS feeAmount,stf.custId,stf.feeTypeId,stf.termId as schoolTermId,stf.academicYearId,
    'Transport Fee'as feeType,s.classSectionId as classSectionId,s.classNameClassId classId,sc.className as className,sc.section as section,
    st.fromdate,st.toDate,st.feeSettingId,sfs.settingName,sfs.settingType,st.fromMonthName,st.toMonthName,st.termName,st.dueDate,st.dueDate2,st.dueDate1,s.categoryId,
    IFNULL(stf.pickupBoardingPointId,0) AS pickupBoardingPointId,IFNULL(stf.dropBoardingPointId,0) AS dropBoardingPointId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,IFNULL(s.vehicleAcademicDetailsId,0)as vehicleAcademicDetailsId,
    s.boardingPointId,a.username,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
    p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,IFNULL(sfc.concessionAmount, 0) as concessionAmount,IFNULL(sfp.concessionAmount, 0) as paymentConcessionAmount

FROM studentTransportDetails stf LEFT JOIN student s ON (stf.studentId = s.id and stf.custId = s.custId and stf.academicYearId = s.academicYearId)
left join vw_studentTransportParticularwiseFeePayments sfp ON (stf.id = sfp.studTransportDetailsId and sfp.deleteStatus = 'N')
	left join Account a ON (s.accountId = a.id)
	LEFT JOIN Person p ON (a.personId = p.id)
	left join studentFeeConcession sfc ON (stf.id=sfc.studTransportDetailsId)
	LEFT JOIN schoolTerms st ON (st.id = stf.termId)
    LEFT JOIN schoolFeeSetting sfs ON (sfs.id = st.feeSettingId)
    LEFT JOIN studyClass sc on (sc.id=s.classSectionId)
    left join routeBoardingPoints prb ON (prb.id = stf.pickupBoardingPointId and prb.custId = stf.custId and prb.academicYearId = stf.academicYearId)
    left join routeBoardingPoints drb ON (drb.id = stf.dropBoardingPointId and drb.custId = stf.custId and drb.academicYearId = stf.academicYearId);

-- Ganesh 
CREATE OR REPLACE VIEW vw_studentTransportFees AS 
select stf.id AS transportFeeId,((IFNULL(prb.boardingPointFeeAmount,0)+IFNULL(drb.boardingPointFeeAmount,0))/2) AS feeAmount,stf.custId AS custId,stf.feeTypeId AS feeTypeId,ifnull(stf.termId, 0) AS schoolTermId,
stf.academicYearId AS academicYearId,'Transport Fee'as feeType,s.id AS studentId,s.rollNumber AS rollNumber,s.accountId AS accountId,s.status AS status,s.description AS description,
s.transportMode AS transportMode,s.boardingPointId AS boardingPointId,s.classNameClassId as classId,s.classSectionId AS classSectionId,s.vehicleAcademicDetailsId AS vehicleAcademicDetailsId,
s.feeConfigured,s.feePaidStatus,sfc.concessionAmount,(IFNULL(prb.boardingPointFeeAmount,0)/2) as pickupBoardingAmount,(IFNULL(drb.boardingPointFeeAmount,0)/2) dropBoardingAmount,
IFNULL(stf.pickupBoardingPointId,0) as pickupBoardingPointId,IFNULL(stf.dropBoardingPointId,0) as dropBoardingPointId
FROM studentTransportDetails stf LEFT JOIN student s ON 
(stf.custId = s.custId and stf.academicYearId = s.academicYearId and stf.studentId=s.id)
LEFT JOIN studentFeeConcession sfc ON (sfc.studentId=s.id and sfc.studTransportDetailsId=stf.id)
left join routeBoardingPoints prb ON (prb.id = stf.pickupBoardingPointId and prb.custId = stf.custId and prb.academicYearId = stf.academicYearId)
left join routeBoardingPoints drb ON (drb.id = stf.dropBoardingPointId and drb.custId = stf.custId and drb.academicYearId = stf.academicYearId);

CREATE OR REPLACE VIEW vw_studentConcessionTransportFees AS 
select vsc.transportFeeId,ifnull(vsc.feeAmount, 0) AS feeAmount,vsc.custId,vsc.feeTypeId,vsc.schoolTermId,vsc.academicYearId,vsc.studentId,vsc.rollNumber,vsc.accountId,vsc.status,vsc.description,
IFNULL(vsc.classSectionId,0) as classSectionId,IFNULL(sfc.concessionAmount,0) as concessionAmount,IFNULL(sfc.id,0) as concessionId 
from vw_studentTransportFees vsc 
left join studentFeeConcession sfc ON (vsc.studentId=sfc.studentId and vsc.transportFeeId = sfc.studTransportDetailsId 
and vsc.custId = sfc.custId and vsc.academicYearId = sfc.academicYearId and vsc.feeAmount <> '0');

-- JR  This view used for show User Login Meta Data Details
CREATE OR REPLACE VIEW vw_userLoginMetaData AS
select ipa.id as userLoginMetaDataId, ipa.userId, ipa.custId, ifnull(ipa.academicYearId, 0) as academicYearId, ipa.ipAddress, ipa.loginCount,
	ipa.createdDate as loginDate, time(ipa.createdDate) as loginTime, time(ipa.lastAccessDate) as lastAccessTime, ipa.lastAccessDate,
	c.firstName as custFirstName, c.lastName as custLastName, concat(c.firstName,' ',c.lastName) as custFullName, c.custEmail, c.organization, 
	a.username, ifnull(a.personId,0) as personId, p.firstName, p.lastName, concat(p.firstName,' ',p.lastName) as fullName,
	ifnull(c.addressId,0) as addressId, d.addressLine1, d.streetName, d.city, d.state as stateCode,
	CONCAT(IFNULL(concat(d.addressLine1, ', '), ''),IFNULL(concat(d.streetName, ', '), ''),CHAR(10),IFNULL(concat(d.city, ', '), ''),IFNULL(concat(d.state, '. '), ''),IFNULL(concat('Ph ', c.mobileNumber, '.'),'')) as custAddress,
	r.id as roleId, r.name AS roleName, r.description AS roleDescription,
	ifnull(s.id,0) as studentId, ifnull(sc.id,0) as studyClassId, sc.className, sc.section, ay.academicYear
from userLoginMetaData ipa 
	LEFT JOIN Account a on (ipa.userId = a.id)
	LEFT JOIN customer c on (ipa.custId = c.id)
	LEFT JOIN Person p on (a.personId = p.id)
	LEFT JOIN Address d on (c.addressId = d.id)
	LEFT JOIN UserRoles ur on (a.id = ur.userId)
	LEFT JOIN Role r on (ur.roleId = r.id)
	LEFT JOIN academicYear ay on (ipa.academicYearId = ay.id)
	LEFT JOIN student s on (a.id = s.accountId and ipa.academicYearId = s.academicYearId)
	LEFT JOIN studyClass sc on (s.classSectionId = sc.id);
	
-- Get items detils for all stores	
CREATE OR REPLACE VIEW vw_storeDetails AS
SELECT payment_SequanceId() AS id ,sd.id as storeId, sd.storeName,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) AS storeKeeperName,
(select sum(it.quantity) from item it where it.itemCode= i.itemCode)  as totalQuantity ,sum(ii.quantity) as issuedQuantity,i.itemCode,i.ItemName,it.typeName as itemType,sd.custId
FROM item i  
LEFT JOIN storeData sd  on (sd.id= i.storeId )
LEFT JOIN Account a  ON (sd.storeKeeperAccountId=a.id)
LEFT JOIN Person p  ON (a.personId=p.id)
LEFT JOIN itemType it on(it.id = i.itemTypeId) 
LEFT JOIN issuedItems ii on(i.id = ii.itemId)
group by i.itemCode,sd.id order by sd.id;




-- Get issued items details with respective to store
CREATE OR REPLACE VIEW vw_issuedItemDetails AS
SELECT payment_SequanceId() AS id, sd.id as storeId, sd.storeName,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) AS storeKeeperName,
sd.custId,
i.itemCode,i.ItemName,
it.typeName AS itemType,
ii.quantity AS issuedQuantity,
ii.recieverName AS issuedTo,
ii.issuedDate AS issuedDate,
ii.issuerName AS issuedBy
FROM issuedItems ii  
LEFT JOIN item i ON (i.id = ii.itemId)
LEFT JOIN storeData sd  ON  (sd.id= i.storeId )
LEFT JOIN Account a  ON (sd.storeKeeperAccountId=a.id)
LEFT JOIN Person p  ON (a.personId=p.id)
LEFT JOIN itemType it ON (it.id = i.itemTypeId) 
ORDER BY sd.id,ii.issuedDate;

-- Get supplier details
CREATE OR REPLACE VIEW vw_supplierDetails AS
SELECT payment_SequanceId() AS id,s.id AS supplierId, 
s.supplierName as supplierName,
s.mobileNumber AS mobileNumber,
s.phoneNumber AS phoneNumber,
sd.id AS storeId,
sd.storeName,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) AS storeKeeperName,
sd.custId,
i.itemCode,i.itemName,
it.typeName AS itemType,
i.quantity AS quantity,
i.createdDate AS receivedDate,
i.totalPrice AS price
FROM item i
LEFT JOIN supplier s ON(i.supplierId = s.id)
LEFT JOIN storeData sd  ON (sd.id= i.storeId )
LEFT JOIN Account a  ON (sd.storeKeeperAccountId=a.id)
LEFT JOIN Person p  ON (a.personId=p.id)
LEFT JOIN itemType it On(it.id = i.itemTypeId) 
ORDER BY s.id,sd.id;
create or replace view vw_studentFeeRefundDetails as
SELECT a.id as accountId,a.custId,a.username,a.admissionNumber,s.id as studentId,s.academicYearId,s.description,s.status,s.classNameClassId,s.rollNumber,s.categoryId,trim(p.firstName) AS firstName,
trim(p.lastName) AS lastName,p.mobileNumber,IFNULL(p.id, 0) as personId,c.className,c.section, 
CAST(CONCAT(IF(c.className IS NULL, '', c.className),IF(c.section IS NULL || c.section = '','',CONCAT(' - ', c.section))) AS CHAR(60)) as classNameAndSection,
IFNULL(c.id, 0) as classSectionId,p.studentMobile, CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,
sc.categoryName,s.feeRefundStatus,sfr.id refundId,sfr.refundAmount,sfr.refundDate,sfr.branchName,sfr.chequeIssuedDate,sfr.chequeNumber,sfr.invoiceNumber,sfr.invoiceString,sfr.paymentMode,sfr.totalFeeAmount,IFNULL(sfr.bankId,0) as bankId
FROM studentFeeRefund sfr  JOIN student s ON (sfr.studentId=s.id and sfr.academicYearId=s.academicYearId and sfr.custId=s.custId)
LEFT JOIN Account a ON (s.accountId = a.id)
LEFT JOIN Person p ON (a.personId = p.id)
LEFT JOIN studyClass c ON (c.id = s.classSectionId)
LEFT JOIN academicYear acy ON (acy.id = s.academicYearId)
LEFT JOIN schoolCategory sc ON (sc.id = s.categoryId)
LEFT JOIN class cl ON (cl.id = s.classNameClassId);

-- Getting Store BasicDetails
CREATE OR REPLACE VIEW vw_storeBasicDetails AS
SELECT sd.id AS storeId, sd.storeName AS storeName,
CONCAT(IF(ad.streetName IS NULL,'',ad.streetName), IF(ad.city IS NULL , '',CONCAT(' , ',ad.city)),
IF(ad.state IS NULL,'',CONCAT(' , ',ad.state)),IF(ad.country IS NULL,'',CONCAT(' , ',ad.country)),IF(ad.postalCode IS NULL,'',CONCAT(' , ',ad.postalCode))) AS storeAddress,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) AS storeKeeperName,
sd.storeKeeperAccountId AS accountId,sd.custId,
IF(i.id IS NULL ,'O',  'I') AS itemsAdded
FROM storeData sd 
LEFT JOIN item i ON (i.storeId = sd.id)
LEFT JOIN Account a  ON (sd.storeKeeperAccountId=a.id)
LEFT JOIN Person p  ON (a.personId=p.id)
LEFT JOIN Address ad  ON (ad.id=sd.addressId) GROUP BY sd.id;


CREATE OR REPLACE VIEW vw_studentPerformance AS
	select
	ifnull(s.id, 0) AS studId,
	ifnull(sm.id, 0) AS marksId,
	ifnull(et.id, 0) AS examTypeId,
    s.custId AS custId,
	ss.id AS subjectId,
	s.rollNumber AS rollNumber,
	ifnull(s.classSectionId, 0) AS classSectionId,
	ifnull(st.id, 0) AS subTypeId,
	et.examType AS examType,
	ifnull(et.maxMarks, 0) AS examMaxMarks,
	ifnull(et.minMarks, 0) AS examMinMarks,
	s.academicYearId AS academicYearId,
	sm.obtainedMarks AS obtainedMarks,
    es.maxMarks as subjectMaxMarks,
	ss.name AS subjectName,
	sm.present AS present,
	sc.className AS className,
	sc.classNameClassId AS classNameClassId,
	sc.section AS section,
	st.subTypeName AS subTypeName,
	ifnull(st.schedule, 'N') AS subTypeSchedule,
	ifnull(es.maxMarks, 0) AS subTypeMaxMarks,
	sm.moderationMarks AS moderationMarks,
	sum((sm.obtainedMarks + sm.moderationMarks)) AS totalSubjectMarksObtained,
	(sm.obtainedMarks + sm.moderationMarks) AS obtainedSubTypeMarks,
	sum(es.maxMarks) AS subjectTotalMarks,
	ss.description AS subjectShortName
	from
	 studentMarks sm
	join student s ON (s.id = sm.studId)
	join examSchedules es ON (es.id = sm.examScheduleId)
	join examTypes et ON (et.id = es.examTypeId)
	join studySubject ss ON (ss.id = es.classSubjectId)
	join subType st ON (st.id = es.subTypeId)
	join studyClass sc ON (sc.id = s.classSectionId) 
   group by sm.id,et.id , ss.id , s.id;

CREATE OR REPLACE VIEW vw_timeTable as
SELECT payment_SequanceId() as id,IFNULL(ttd.dayId,0) AS dayId,IFNULL(ttd.timeTablePeriodId,0) AS timeTablePeriodId,IFNULL(ttd.studyClassId,0) AS studyClassId,IFNULL(tts.custId,0) AS custId,IFNULL(tts.academicYearId,0) AS academicYearId,
	IFNULL(ttd.studySubjectId,0) AS studySubjectId,ttd.status AS timeTableDetailsStatus,
	IFNULL(ttp.timeTableSettingId,0) AS timeTableSettingId,ttp.periodName,ttp.startTime,ttp.endTime,
	ss.name AS subjectName,ss.subjectNumber,
	ss.language,ss.subjectType
FROM timeTableDetails ttd 
LEFT JOIN timeTablePeriod ttp on (ttp.id=ttd.timeTablePeriodId)
LEFT JOIN studySubject ss on (ss.id=ttd.studySubjectId) 
LEFT JOIN timeTableSettings tts on (tts.id=ttp.timeTableSettingId);

CREATE OR REPLACE VIEW vw_timeTableStaffDetails as
SELECT payment_SequanceId() as id,IFNULL(ttd.dayId,0) AS dayId,IFNULL(ttd.timeTablePeriodId,0) AS timeTablePeriodId,
IFNULL(ttd.studyClassId,0) AS studyClassId,IFNULL(tts.custId,0) AS custId,IFNULL(tts.academicYearId,0) AS academicYearId,
IFNULL(ttd.studySubjectId,0) AS studySubjectId,ttd.status AS timeTableDetailsStatus,
IFNULL(ttp.timeTableSettingId,0) AS timeTableSettingId,ttp.periodName,ttp.startTime,ttp.endTime,
ss.name AS subjectName,ss.subjectNumber,ss.language,ss.subjectType,
ifnull(ct.id,0) As classTeacherId,ifnull(ct.teacherId,'0') as staffId,
ifnull(st.accountId,'0') as accountId,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL,'',CONCAT(' ',p.lastName))) AS staffName,
CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL,'',CONCAT(' - ',sc.section))) AS classAndSection
FROM timeTableDetails ttd
LEFT JOIN timeTablePeriod ttp on (ttp.id=ttd.timeTablePeriodId) 
LEFT JOIN studySubject ss on (ss.id=ttd.studySubjectId)
LEFT JOIN timeTableSettings tts on (tts.id=ttp.timeTableSettingId)
LEFT JOIN classTeacher ct ON (ct.studySubjectId = ttd.studySubjectId and ct.studyClassId = ttd.studyClassId)
LEFT JOIN staff st ON (st.id=ct.teacherId)
LEFT JOIN Account a ON (a.id = st.accountId)
LEFT JOIN Person p ON (p.id = a.personId)
LEFT JOIN studyClass sc ON (sc.id = ttd.studyClassId);

CREATE OR REPLACE VIEW vw_questionBankDetails as
SELECT payment_SequanceId() as id,
IFNULL(qpb.id,0) AS questionBankId,
qpb.custId AS custId,
qpb.academicYearId AS academicYearId,
IFNULL(qpb.subjectId,0) AS subjectId,
ss.name AS subjectName,
IFNULL(qpb.syllabusPlannerId,0) AS lessonId,
ssp.chapterName AS lessonName,
IFNULL(qpb.studyClassId,0) AS classSectionId,
CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL,'',CONCAT(' - ',sc.section))) AS classNameAndSection,
qpb.materialName AS materialName,
qpb.description AS description
FROM questionPaperBank qpb
LEFT JOIN studyClass sc ON (qpb.studyClassId=sc.id)
LEFT JOIN studySubject ss ON(qpb.subjectId=ss.id)
LEFT JOIN staffSyllabusPlanner ssp ON(qpb.syllabusPlannerId=ssp.id);