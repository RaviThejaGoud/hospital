drop table IF EXISTS vw_StudentDailyAttendance;            
drop table IF EXISTS vw_allUsers;                          
drop table IF EXISTS vw_bookAndLabelDetails;               
drop table IF EXISTS vw_bookTitleAndBlockDetails;          
drop table IF EXISTS vw_buildingFloorDetails;              
drop table IF EXISTS vw_buildingMenuItemsDetails;          
drop table IF EXISTS vw_classExamDetails;                  
drop table IF EXISTS vw_classFeeDetails;                   
drop table IF EXISTS vw_classFeeTypes;                     
drop table IF EXISTS vw_classSectionDetails;               
drop table IF EXISTS vw_classSectionTeacher;               
drop table IF EXISTS vw_classSubjectsSettings;             
drop table IF EXISTS vw_classWisePeriodsCountDetails;      
drop table IF EXISTS vw_examSchedule;                      
drop table IF EXISTS vw_examScheduleWiseStudentMarks;      
drop table IF EXISTS vw_govtschoolsreport;                 
drop table IF EXISTS vw_hostelBuildingDetails;             
drop table IF EXISTS vw_hostelStaffFeePaymentDetails;      
drop table IF EXISTS vw_issuedBookAndSettings;             
drop table IF EXISTS vw_onlineApplicationDetails;          
drop table IF EXISTS vw_onlineApplicationStudentClassFees; 
drop table IF EXISTS vw_promotionClassesDetails;           
drop table IF EXISTS vw_roomDetails;                       
drop table IF EXISTS vw_staffAllotedBeds;                  
drop table IF EXISTS vw_staffAttendance;                   
drop table IF EXISTS vw_staffDetails;                      
drop table IF EXISTS vw_staffEligibleSubjects;             
drop table IF EXISTS vw_staffHostelFeeDetails;             
drop table IF EXISTS vw_staffHostelFeeTypes;               
drop table IF EXISTS vw_staffLeaveDetails;                 
drop table IF EXISTS vw_staffPayrollDetails;               
/*drop table IF EXISTS vw_staffStudentVehicleTripDetails;*/    
drop table IF EXISTS vw_staffSubjectsDetails;              
drop table IF EXISTS vw_staffVehicleTripDetails;           
drop table IF EXISTS vw_studentAttendance;                 
drop table IF EXISTS vw_studentClassDetails;               
drop table IF EXISTS vw_studentClasswiseAndPersonalInfo;   
drop table IF EXISTS vw_studentDetails;                    
drop table IF EXISTS vw_studentFeePaidAndNotPaidDetails;   
drop table IF EXISTS vw_studentFeePaymentDetails;          
drop table IF EXISTS vw_studentLeaveDetails;               
drop table IF EXISTS vw_studentMarksDetails;               
drop table IF EXISTS vw_studentOutHostelDetails;           
drop table IF EXISTS vw_studentQuestionAnswers;            
drop table IF EXISTS vw_studentQuizQuestionAnswers;        
drop table IF EXISTS vw_studentsAllotedBeds;              
drop table IF EXISTS vw_studentsScoreCardProfileDetails;   
drop table IF EXISTS vw_studentsTCDetails;                 
drop table IF EXISTS vw_studyClassSubjectDetails;          
drop table IF EXISTS vw_timeTableDetails;                  
drop table IF EXISTS vw_userRoles;
drop table IF EXISTS vw_studentsMarksAndPerformanceDetails;
drop table IF EXISTS vw_examWiseTopperDetails;
drop table IF EXISTS vw_vehicleWithDriverDetails;
drop table IF EXISTS vw_classAssignmentDetails;
drop table IF EXISTS vw_hostelStudentDetails;
drop table IF EXISTS vw_StudentClassAssignment;
drop table IF EXISTS vw_studentFineFeeDetails;
drop table IF EXISTS vw_studentsHallTicketDetails;
drop table IF EXISTS vw_vehiclemaintenance;
drop table IF EXISTS vw_vehicleMaintenanceByMonth;
drop table IF EXISTS vw_vehiclesWithExpiryDatesInformation;
drop table IF EXISTS vw_AcademicYearTimings;
drop table IF EXISTS vw_routeWiseStudents;
drop table IF EXISTS vw_assignedVehiclestoRoutes;
drop table IF EXISTS vw_studentsTransportDetails;
drop table IF EXISTS vw_studentPersonInfo;
drop table IF EXISTS vw_assignedVehiclestoRoutesWithBoardingPoints;
drop table IF EXISTS vw_vehicleAndDriverInfo;
drop table IF EXISTS vw_studentissuebookandreservations;
drop table IF EXISTS vw_staffHostelFeePaymentDetails;
drop table IF EXISTS vw_staffHostelFees;
drop table IF EXISTS vw_staffMonthlyAttendance;
drop table IF EXISTS vw_studentBoardingPointDetails;
drop table IF EXISTS vw_studentClassAndAttendanceDetails;
drop table IF EXISTS vw_studentClassFeePaymentDetails;
drop table IF EXISTS vw_studentClassFees;
drop table IF EXISTS vw_studentcountbyvehicle;
drop table IF EXISTS vw_studentDetailsByDays;
drop table IF EXISTS vw_studentExamMarks;
drop table IF EXISTS vw_studentMessAccessedDetails;
drop table IF EXISTS vw_StudentMonthlyAttendance;
drop table IF EXISTS vw_studentsAbsentiesCount;
drop table IF EXISTS vw_studentsClassSectionDetails;
drop table IF EXISTS vw_studentScoreCardMarksDetails;
drop table IF EXISTS vw_studentSubjectWiseMarksDetails;
drop table IF EXISTS vw_subjectWiseAssignedPeriodsCount;
drop table IF EXISTS vw_subjectWisePeriodsDetails;
drop table IF EXISTS vw_vehicleWithBoardingPoint;
drop table IF EXISTS vw_studentFutureFeePaymentDetails;
drop table IF EXISTS vw_studentsExcessPaymentDetails;
drop table IF EXISTS vw_studentTransportDetails;
drop table IF EXISTS vw_studentsFutureAcademicTransportDetails;
drop table IF EXISTS vw_studentFeePaidDetails;
drop table IF EXISTS vw_studentParticularwiseFeePayments;
drop table IF EXISTS vw_FeedbackRatingDetails;
drop table IF EXISTS vw_syllabusPlannerComments;
drop table IF EXISTS vw_circularusers;
drop table IF EXISTS vw_hostelAllDetails;
drop table IF EXISTS vw_hostelStudentLeaveDetails;
drop table IF EXISTS vw_studyClassStudentsDailyAttendance;
drop table IF EXISTS vw_studyClassStudentsMonthlyAttendance;
drop table IF EXISTS vw_studentSiblings;
drop table IF EXISTS vw_userIpAddressLocation;
drop table IF EXISTS vw_issueProvisionItemsToMess;
drop table IF EXISTS vw_examTypesSchedules;

drop view IF EXISTS vw_examWiseTopperDetails;
drop view IF EXISTS vw_studentsMarksAndPerformanceDetails;
drop view IF EXISTS vw_StudentDailyAttendance;            
drop view IF EXISTS vw_allUsers;                          
drop view IF EXISTS vw_bookAndLabelDetails;               
drop view IF EXISTS vw_bookTitleAndBlockDetails;          
drop view IF EXISTS vw_buildingFloorDetails;              
drop view IF EXISTS vw_buildingMenuItemsDetails;          
drop view IF EXISTS vw_classExamDetails;                  
drop view IF EXISTS vw_classFeeDetails;                   
drop view IF EXISTS vw_classFeeTypes;                     
drop view IF EXISTS vw_classSectionDetails;               
drop view IF EXISTS vw_classSectionTeacher;               
drop view IF EXISTS vw_classSubjectsSettings;             
drop view IF EXISTS vw_classWisePeriodsCountDetails;      
drop view IF EXISTS vw_examSchedule;                      
drop view IF EXISTS vw_examScheduleWiseStudentMarks;      
drop view IF EXISTS vw_govtschoolsreport;                 
drop view IF EXISTS vw_hostelBuildingDetails;             
drop view IF EXISTS vw_hostelStaffFeePaymentDetails;      
drop view IF EXISTS vw_issuedBookAndSettings;             
drop view IF EXISTS vw_onlineApplicationDetails;          
drop view IF EXISTS vw_onlineApplicationStudentClassFees; 
drop view IF EXISTS vw_promotionClassesDetails;           
drop view IF EXISTS vw_roomDetails;                       
drop view IF EXISTS vw_staffAllotedBeds;                  
drop view IF EXISTS vw_staffAttendance;                   
drop view IF EXISTS vw_staffDetails;                      
drop view IF EXISTS vw_staffEligibleSubjects;             
drop view IF EXISTS vw_staffHostelFeeDetails;             
drop view IF EXISTS vw_staffHostelFeeTypes;               
drop view IF EXISTS vw_staffLeaveDetails;                 
drop view IF EXISTS vw_staffPayrollDetails;               
/*drop view IF EXISTS vw_staffStudentVehicleTripDetails;*/    
drop view IF EXISTS vw_staffSubjectsDetails;              
drop view IF EXISTS vw_staffVehicleTripDetails;           
drop view IF EXISTS vw_studentAttendance;                 
drop view IF EXISTS vw_studentClassDetails;               
drop view IF EXISTS vw_studentClasswiseAndPersonalInfo;   
drop view IF EXISTS vw_studentDetails;                    
drop view IF EXISTS vw_studentFeePaidAndNotPaidDetails;   
drop view IF EXISTS vw_studentFeePaymentDetails;          
drop view IF EXISTS vw_studentLeaveDetails;               
drop view IF EXISTS vw_studentMarksDetails;               
drop view IF EXISTS vw_studentOutHostelDetails;           
drop view IF EXISTS vw_studentQuestionAnswers;            
drop view IF EXISTS vw_studentQuizQuestionAnswers;        
drop view IF EXISTS vw_studentsAllotedBeds;              
drop view IF EXISTS vw_studentsScoreCardProfileDetails;   
drop view IF EXISTS vw_studentsTCDetails;                 
drop view IF EXISTS vw_studyClassSubjectDetails;          
drop view IF EXISTS vw_timeTableDetails;                  
drop view IF EXISTS vw_userRoles;
drop view IF EXISTS vw_classAssignmentDetails;
drop view IF EXISTS vw_classexamsubtypes;
/*drop view IF EXISTS vw_classsectionsubject;*/
drop view IF EXISTS vw_combinedclasssubjectdetails;
drop view IF EXISTS vw_customeraddress;
drop view IF EXISTS vw_examscheduledetailswithtoppermarks;
/*drop view IF EXISTS vw_newstudentdetails;
drop view IF EXISTS vw_parentfeedbackresponse;*/
drop view IF EXISTS vw_staffdailyattendance;
drop view IF EXISTS vw_staffdriverdetails;
drop view IF EXISTS vw_staffHostelFeePaymentDetails;
drop view IF EXISTS vw_staffHostelFees;
drop view IF EXISTS vw_staffMonthlyAttendance;
drop view IF EXISTS vw_studentBoardingPointDetails;
drop view IF EXISTS vw_studentClassAndAttendanceDetails;
drop view IF EXISTS vw_StudentClassAssignment;
drop view IF EXISTS vw_studentClassFeePaymentDetails;
drop view IF EXISTS vw_studentClassFees;
drop view IF EXISTS vw_studentcountbyvehicle;
drop view IF EXISTS vw_studentDetailsByDays;
drop view IF EXISTS vw_studentExamMarks;
drop view IF EXISTS vw_studentFineFeeDetails;
drop view IF EXISTS vw_studentMessAccessedDetails;
drop view IF EXISTS vw_StudentMonthlyAttendance;
drop view IF EXISTS vw_studentPersonInfo;
drop view IF EXISTS vw_studentsAbsentiesCount;
drop view IF EXISTS vw_studentsClassSectionDetails;
drop view IF EXISTS vw_studentScoreCardMarksDetails;
drop view IF EXISTS vw_studentSubjectWiseMarksDetails;
drop view IF EXISTS vw_subjectWiseAssignedPeriodsCount;
drop view IF EXISTS vw_subjectWisePeriodsDetails;
drop view IF EXISTS vw_vehicledetails;
drop view IF EXISTS vw_vehiclemaintenance;
drop view IF EXISTS vw_vehicleMaintenanceByMonth;
drop view IF EXISTS vw_vehicleswithexpirydates;
drop view IF EXISTS vw_vehiclesWithExpiryDatesInformation;
drop view IF EXISTS vw_vehicleWithBoardingPoint;
drop view IF EXISTS vw_vehicleWithDriverDetails;
drop view IF EXISTS vw_AcademicYearTimings;
/*drop view IF EXISTS vw_studentCountByBoardingPointId;*/
drop view IF EXISTS vw_hostelStudentDetails;
drop view IF EXISTS vw_studentsHallTicketDetails;
drop view IF EXISTS vw_routeWiseStudents;
drop view IF EXISTS vw_assignedVehiclestoRoutes;
drop view IF EXISTS vw_studentsTransportDetails;
drop view IF EXISTS vw_assignedVehiclestoRoutesWithBoardingPoints;
drop view IF EXISTS vw_vehicleAndDriverInfo;
drop view IF EXISTS vw_studentissuebookandreservations;
drop view IF EXISTS vw_studentFutureFeePaymentDetails;
drop view IF EXISTS vw_studentsExcessPaymentDetails;
drop view IF EXISTS vw_studentTransportDetails;
drop view IF EXISTS vw_studentsFutureAcademicTransportDetails;
drop view IF EXISTS vw_studentFeePaidDetails;
drop view IF EXISTS vw_studentParticularwiseFeePayments;
drop view IF EXISTS vw_FeedbackRatingDetails;
drop view IF EXISTS vw_syllabusPlannerComments;
drop view IF EXISTS vw_circularusers;
drop view IF EXISTS vw_hostelAllDetails;
drop view IF EXISTS vw_hostelStudentLeaveDetails;
drop view IF EXISTS vw_SmsApp_StudentDailyAttendance;
drop view IF EXISTS vw_studyClassStudentsDailyAttendance;
drop view IF EXISTS vw_studyClassStudentsMonthlyAttendance;
drop view IF EXISTS vw_studentSiblings;
drop view IF EXISTS vw_userIpAddressLocation;
drop view IF EXISTS vw_issueProvisionItemsToMess;
drop view IF EXISTS vw_examTypesSchedules;



CREATE OR REPLACE VIEW vw_userRoles 
AS select a.id accountId,a.custId,a.userName,ur.roleId,r.name AS roleName,r.description 
AS roleDescription,p.firstName,IFNULL(p.lastName,'') as lastName,a.accountExpired, CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as formatedFullName
from UserRoles ur  LEFT JOIN Account a  on (ur.userId=a.id) LEFT JOIN Person p  on (a.personId=p.id) LEFT JOIN Role r on (ur.roleId=r.id);

/* vw_studentDetails  changed bellow 
create or replace view vw_studentDetails as SELECT a.id as accountId,a.custId, a.username,a.parentId, a.version, a.sharePrivacy, a.userOnlineNow,a.accountExpired,a.admissionNumber, s.id as studentId,s.academicYearId,s.description, s.createdDate, s.lastAccessDate, s.status,s.transportMode,s.classNameClassId,s.rollNumber,s.categoryId,s.hostelCategoryId,p.height,p.weight,p.teeth,p.vision,p.oralHygiene,p.firstName,p.lastName, p.middleName, p.fatherName, p.motherName, p.mothersMaidenName,p.dateOfBirth,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.parentEmail,p.summary,p.dateOfJoining, IFNULL(p.castId,0) as castId,IFNULL(p.subCastId,0) as subCastId,IFNULL(p.religionId,0) as religionId,p.nationality,p.identification1,p.identification2,p.gender,IFNULL(p.motherToungId,0) as motherToungId,d.addressLine1,d.addressLine2,d.city,d.state,d.postalCode,a.lastUpdatedDate,
c.className,c.section,IFNULL(c.mediumId,0) as mediumId,c.filledSeats,c.sectionCapacity,cl.higherStandard,ur.roleName,ur.roleDescription,ur.roleId,ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail,IFNULL(c.id,0) as classSectionId,cs.castName,IFNULL(scs.subCastName,'') as subCastName,p.FatherOccupation as fatherOccupation,p.motherOccupation,p.annualIncome,p.familyDoctor,p.prefferedHospital,d.streetName,IFNULL(st.stateName,'') as stateName,cst.revenueDistrict,cst.custEmail,cst.contactNumber,cst.educationalDistrict,cst.organization,cst.customerShortName,cst.custImageId,IFNULL(trnc.serialNumber,0) as serialNumber,acy.academicYear,ct.skillTypeName as religion,cst.boardOfEducation,cst.recognisedBy,IFNULL(s.bedId,0) as bedId,ct.skillTypeName as medium,IFNULL(s.boardingPointId,0) as boardingPointId,cstUi.name custImageName,cstUi.path custImagePath,cstUi.thumbNail as custThumbNail,CONCAT(IFNULL(concat(custAddr.addressLine1,', '),''),IFNULL(concat(custAddr.streetName,', '),''),CHAR(10),IFNULL(concat(custAddr.city,' - '),''),IFNULL(concat(custAddr.postalCode,', '),''),IFNULL(concat('PH - ',cst.mobileNumber,'.'),'')) as customerFullAddress,
CONCAT(IFNULL(concat(d.addressLine1,', '),''),IFNULL(concat(d.city,' - '),''),CHAR(10),IFNULL(concat(d.postalCode,', '),'')) as parentFullAddress,IFNULL(s.registerNumber,'') as registerNumber 
FROM student s  JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN Address d on (a.paddressId=d.id) LEFT JOIN State st on (st.stateCode=d.state) LEFT JOIN  studyClass c on (c.id=s.classSectionId) LEFT JOIN academicYear acy on(acy.id=s.academicYearId) LEFT JOIN  schoolCategory sc on (sc.id=s.categoryId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN UserImage ui on (ui.id=a.imageId) LEFT JOIN castSettings cs on (cs.id=p.castId) LEFT JOIN subCastSettings scs on (scs.id=p.subCastId) LEFT JOIN class cl on (cl.id=s.classNameClassId) JOIN customer cst on(a.custId=cst.id) LEFT JOIN Address custAddr on (custAddr.id=cst.addressId) LEFT JOIN transferCertificate trnc on(a.id=trnc.accountId) LEFT JOIN UserImage cstUi on (cstUi.id = cst.custImageId) LEFT JOIN  commonType ct on (ct.id =p.religionId and ct.type='RELIGION')  LEFT JOIN  commonType ctype on (ctype.id =c.mediumId and ctype.type='MEDIUM');
 */


/*
 changed the view for adding the salary,esiNo and pfNo in staff profile 
*/
create 
or replace view vw_staffDetails as 
select 
    a.id AS accountId,
    a.accountExpired AS accountExpired,
    a.custId AS custId,
    a.bioMetricId AS bioMetricId,
    a.username AS username,
    a.version AS version,
    a.sharePrivacy AS sharePrivacy,
    a.userOnlineNow AS userOnlineNow,
    s.id AS staffId,
    s.description AS description,
    ay.id AS academicYearId,
    ay.status AS academicYearStatus,
    s.qualification1 AS qualification1,
    s.qualification2 AS qualification2,
    s.createdDate AS createdDate,
    s.lastAccessDate AS lastAccessDate,
    s.status AS status,
    s.supervisorId AS supervisorId,
    ifnull(s.bedId, 0) AS bedId,
    s.hostelCategoryId AS hostelCategoryId,
    s.staffType As staffType,
    (case
        when (isnull(s.staffType) or (s.staffType = 'P')) then 'Permanent'
        when (s.staffType = 'C') then 'Temporary'
    end) AS 'staffTypeStatus',
    ifnull(c.orgnizationTypeId, 0) AS organizationTypeId,
    s.staffPayrollMonth AS staffPayrollMonth,
    s.staffPayrollYear AS staffPayrollYear,
    s.isTimetableUploaded,
    sa.salary AS salary,
    ss.pfNo AS pfNo,
    ss.esiNo AS esiNo,
    ifnull(ss.pfPercentage, 0) AS pfPercentage,
    ss.pfDateofJoin AS pfDateofJoin,
    ifnull(ss.esiPercentage, 0) AS esiPercentage,
    ss.esiDateofJoin AS esiDateofJoin,
    p.contractStartDate AS contractStartDate,
    p.contractEndDate AS contractEndDate,
    p.licenseNumber AS licenseNumber,
    p.licenseExpDate AS licenseExpDate,
    p.experience AS experience,
    p.dateOfJoining AS dateofJoining,
    p.firstName AS firstName,
    concat(p.firstName,' ',p.lastName) as fullName,
    p.lastName AS lastName,
    p.middleName AS middleName,
    p.dateOfBirth AS dateOfBirth,
    p.bloodGroup AS bloodGroup,
    ifnull(p.mobileNumber,'N/A') AS mobileNumber,
    p.phoneNumber AS phoneNumber,
    p.maritalStatus AS maritalStatus,
    p.summary AS summary,
    p.gender AS gender,
    p.familyDoctor AS familyDoctor,
    p.prefferedHospital AS prefferedHospital,
    ifnull(p.religionId, 0) AS religionId,
    ifnull(ct.skillTypeName,NULL) as religion,
    ifnull(p.castId, 0) AS castId,
    ifnull(p.subCastId, 0) AS subCastId,
    ifnull(p.annualIncome, 0) AS annualIncome,
    (case
        when (isnull(p.designation) or (p.designation = '')) then 'No Designation'
        else p.designation
    end) AS designation,
    p.gpfNumber AS gpfNumber,
    p.officeNumber AS officeNumber,
    p.nationality AS nationality,
    p.staffUniqueNumber AS staffUniqueNumber,
    p.panNumber AS panNumber,
    ifnull(p.motherToungId, 0) AS motherToungId,
    ifnull(mot.name, NULL) AS motherTounge,
    d.addressLine1 AS addressLine1,
    d.addressLine2 AS addressLine2,
    d.city AS city,
    d.state AS state,
    st.stateName as stateName,
    d.postalCode AS postalCode,
    d.email AS email,
    a.lastUpdatedDate AS lastUpdatedDate,
    ifnull(ur.roleId, 0) AS roleId,
    ifnull(ur.roleName,'') AS roleName,
    ur.roleDescription AS roleDescription,
    ui.id AS imageId,
    ui.name AS imageName,
    ui.path AS imagePath,
    ui.thumbNail AS thumbNail,
    p.bankName AS bankName,
    p.bankAccountNumber AS bankAccountNumber,
    p.bankBranchName AS bankBranchName,
    cs.castName AS castName,
    c.custImageId,
    cstUi.name custImageName,
    cstUi.path custImagePath,
    cstUi.thumbNail as custThumbNail,
    ifnull(scs.subCastName, '') AS subCastName,
    a.staffNumber,p.aadharNumber,p.passportNumber,p.ifscCode,p.isDocsUploaded,
    td.addressLine1 AS taddressLine1,
    td.city AS tcity,
    td.state AS tstate,
    td.postalCode AS tpostalCode,s.outSideSchoolDuty
from
    staff s
    left join Account a ON s.accountId = a.id
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
    left join State st on st.id=d.stateId;
     


create 
or replace view vw_staffLeaveDetails
as select s.accountId accountId,s.custId, s.username, s.version, s.staffId, s.supervisorId, s.academicYearId, s.qualification1,s.qualification2,s.status,s.firstName, s.lastName,s.middleName,s.summary,s.dateOfJoining,
l.id leavesId,l.description,l.startDate,l.endDate,l.leaveStatus,IFNULL(l.leavesCount,0) as leavesCount,l.leaveType,l.startTime,l.endTime,ur.roleName,l.supervisorId leaveSupervisorId,ur.roleDescription,ui.id imageId,ur.roleId,ui.name imageName,ui.path imagePath,ui.thumbNail,IFNULL(lm.id,0) as leaveManagementId,lm.permanentOrContract as permanentOrContract,IFNULL(lm.earnedLeaves,0) as userEarnedLeaves,IFNULL(lm.sickLeaves,0) as userSickLeaves,IFNULL(lm.casualLeaves,0) as userCasualLeaves
FROM vw_staffDetails s JOIN leaves l on (s.accountId=l.accountId and s.academicYearId = l.academicYearId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN leaveManagement lm on (lm.roleId=ur.roleId && l.academicYearId=lm.academicYearId && s.staffType=lm.permanentOrContract) LEFT JOIN UserImage ui on (ui.id=s.imageId);

/*change the vw_studentDetails by prasad 28-09-2012 */
create
or replace view vw_studentDetails as
SELECT 
    a.id as accountId,
    a.custId,
    a.username,
    a.parentId,
    a.version,
    a.sharePrivacy,
    a.userOnlineNow,
    a.accountExpired,
    a.admissionNumber,
    s.id as studentId,
    s.academicYearId,
    s.description,
    s.createdDate,
    s.lastAccessDate,
    s.hostelMode,
    s.status,
    ifnull(s.transportMode, 'O') as transportMode,
    s.classNameClassId,
    s.rollNumber,
    s.joinedThroughAdmissions,
    s.categoryId,
    p.height,
    p.weight,
    p.teeth,
    p.vision,
    p.oralHygiene,
    p.firstName,
    p.lastName,
    p.middleName,
    p.fatherName,
    p.motherName,
    p.mothersMaidenName,
    p.dateOfBirth,
    UCASE(p.bloodGroup) as bloodGroup,
    p.mobileNumber,
    p.phoneNumber,
    p.parentEmail,
    p.summary,
    p.dateOfJoining,
    IFNULL(p.castId, 0) as castId,
    IFNULL(p.subCastId, 0) as subCastId,
    IFNULL(p.religionId, 0) as religionId,
    p.nationality,
    p.identification1,
    p.identification2,
    p.gender,
    IFNULL(p.motherToungId, 0) as motherToungId,
    p.rationCardNumber,
    p.communityNumber,
    IF(p.phId ='Y','Yes','No') AS phId,
    d.addressLine1,
    d.addressLine2,
    d.city,
    d.state,
    d.postalCode,
    a.lastUpdatedDate,
    c.className,
    c.section,
    CAST(CONCAT(IF(c.className IS NULL, '', c.className),
            IF(c.section IS NULL || c.section <=> '',
                '',
                CONCAT(' - ', c.section))) AS CHAR(60)) as classNameAndSection,
    IFNULL(c.mediumId, 0) as mediumId,
    c.filledSeats,
    c.sectionCapacity,
    cl.higherStandard,
    ur.roleName,
    ur.roleDescription,
    ifNULL(ur.roleId,3) as roleId,
    ui.id imageId,
    ui.name imageName,
    ui.path imagePath,
    ui.thumbNail,
    IFNULL(c.id, 0) as classSectionId,
    cs.castName,
    IFNULL(scs.subCastName, '') as subCastName,
    p.FatherOccupation as fatherOccupation,
    p.motherOccupation,
    p.annualIncome,
    p.familyDoctor,
    p.sslcNumber,
    p.tmrNumber,
    p.classJoined,
    p.prefferedHospital,
    p.placeOfBirth,
    p.lastSchool,
    p.studentEmail,
    p.studentMobile,
    d.streetName,
    IFNULL(st.stateName, '') as stateName,
    cst.revenueDistrict,
    cst.custEmail,
    cst.contactNumber,
    cst.educationalDistrict,
    cst.organization,
    cst.customerShortName,
    cst.custImageId,
    cst.schoolCode,
    cst.affiliationNumber,
    acy.academicYear,
    ct.skillTypeName as religion,
    cst.boardOfEducation,
    cst.recognisedBy,
    if(cst.transportModuleStatus ='Y','TRUE','FALSE') AS transportModuleStatus,
    if((s.boardingPointId > 0 && s.TransportMode='T'),'TRUE','FALSE') AS studentTransportStatus,
    IFNULL(s.bedId, 0) as bedId,
    ct.skillTypeName as medium,
    IFNULL(s.boardingPointId, 0) as boardingPointId,
    IFNULL(v.id, 0) as vehicleId,
    IFNULL(s.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,
    cstUi.name custImageName,
    cstUi.path custImagePath,
    cstUi.thumbNail as custThumbNail,
    va.name as vehicleName,
    CONCAT(IFNULL(concat(custAddr.addressLine1, ', '), ''),
            IFNULL(concat(custAddr.streetName, ', '), ''),
            CHAR(10),
            IFNULL(concat(custAddr.city, ' - '), ''),
            IFNULL(concat(custAddr.postalCode, ', '), ''),
            IFNULL(concat('PH - ', cst.mobileNumber, '.'),
                    '')) as customerFullAddress,
    CONCAT(IFNULL(concat(d.streetName, ', '), ''),
            IFNULL(concat(d.city, ' - '), ''),
            CHAR(10),
            IFNULL(concat(d.postalCode, '. '), ''),
            IFNULL(concat('PH - ', p.mobileNumber, '.'), '')) as parentFullAddress,
    IFNULL(s.registerNumber, '') as registerNumber,
    IFNULL(ifnull(concat(va.name, '-', br.boardingPointName),
                    concat(v.vehicleNumber,
                            '-',
                            br.boardingPointName)),
            '') AS VehicleBoardingPointname,
    IFNULL(r.routeName, '') as routeName,
    IFNULL(r.id, 0) as routeId,
    cl.sortingOrder,
    (case
        when (s.TransportMode = 'O') then 'Own'
        when (s.TransportMode = 'P') then 'Private'
        when (s.TransportMode = 'T') then 'School Transport'
        else ''
    end) AS 'TransportName',
    ctype.name as mediumOfStudy,
    mtype.name as motherToung,
    p.relievingDate,
    p.scholarShipInfo,
    p.tcAppliedDate,
    p.tcIssuedDate,
    p.studentUniqueNumber,
    CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName,br.boardingPointName,
    sc.categoryName,p.aadharNumber,s.outSideSchoolDuty,s.committedFee
FROM
    student s
        JOIN
    Account a ON (s.accountId = a.id)
        LEFT JOIN
    Person p ON (a.personId = p.id)
        LEFT JOIN
    Address d ON (a.paddressId = d.id)
        LEFT JOIN
    State st ON (st.id = d.stateId)
        LEFT JOIN
    studyClass c ON (c.id = s.classSectionId)
        LEFT JOIN
    academicYear acy ON (acy.id = s.academicYearId)
        LEFT JOIN
    schoolCategory sc ON (sc.id = s.categoryId)
        LEFT JOIN
    vw_userRoles ur ON (ur.accountId = s.accountId)
        LEFT JOIN
    UserImage ui ON (ui.id = s.imageId)
        LEFT JOIN
    castSettings cs ON (cs.id = p.castId)
        LEFT JOIN
    subCastSettings scs ON (scs.id = p.subCastId)
        LEFT JOIN
    class cl ON (cl.id = s.classNameClassId)
        JOIN
    customer cst ON (a.custId = cst.id)
        LEFT JOIN
    Address custAddr ON (custAddr.id = cst.addressId)
        LEFT JOIN
    UserImage cstUi ON (cstUi.id = cst.custImageId)
        LEFT JOIN
    commonType ct ON (ct.id = p.religionId and ct.type = 'RELIGION')
        LEFT JOIN
    medium ctype ON (ctype.id = c.mediumId)
        LEFT JOIN
    motherTongue mtype ON (mtype.id = p.motherToungId)
        LEFT JOIN vehiclesAcademicDetails va on (va.id=s.vehicleAcademicDetailsId)
    LEFT JOIN vehicles v ON (v.id = va.vehicleId)
        LEFT JOIN
    routeBoardingPoints br ON (br.id = s.boardingPointId)
        left join
    route r ON (br.routeId = r.id);


create or replace view vw_studentClassDetails as
SELECT s.id as studId,s.classSectionId,s.status,s.custId,s.rollNumber,s.academicYearId,ayc.academicYear,s.accountId,IFNULL(s.bedId,0) as bedId,IFNULL(s.categoryId,0) as categoryId,s.transportMode,
s.classNameClassId as classId,IFNULL(s.boardingPointId,0) as boardingPointId,s.joinedThroughAdmissions,TRIM(p.firstName) as firstName,TRIM(p.lastName) as lastName,IFNULL(p.castId,0) as castId,IFNULL(p.subCastId,0) as subCastId,IFNULL(p.religionId,0) as religionId ,
p.gender,p.rationCardNumber,p.communityNumber,p.FatherOccupation,c.className,c.section,cl.higherStandard,p.dateOfBirth,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName,IFNULL(cs.castName,'') as castName,IFNULL(a.admissionNumber,'') as admissionNumber,
s.registerNumber,a.accountExpired,s.description as studDiscontinueDesc,p.dateOfJoining,p.fatherName,p.motherName,IFNULL(p.height,0) as height,IFNULL(p.weight,0) as weight,
p.teeth,p.vision,p.oralHygiene,p.motherOccupation,p.parentEmail,p.annualIncome,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.nationality,
d.streetName,d.postalCode,IFNULL(p.motherToungId,0) as motherToungId,p.identification1,p.identification2,d.city,cl.sortingOrder,s.roomId,IFNULL(s.imageId,0) as imageId,IFNULL(s.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,s.hostelMode,
s.futureAcademicClassSecId,s.feePaidStatus,s.feeConfigured,s.description
FROM student s  JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN Address d on (a.paddressId=d.id) LEFT JOIN  studyClass c on (c.id=s.classSectionId)  LEFT JOIN class cl on (cl.id=s.classNameClassId) LEFT JOIN castSettings cs on (cs.id=p.castId) LEFT JOIN academicYear ayc on (s.academicYearId=ayc.id);



create 
or replace view vw_studentLeaveDetails as
SELECT s.accountId,s.custId, s.username,s.parentId,s.version, s.sharePrivacy, s.userOnlineNow, s.studentId, s.academicYearId, s.status,s.classSectionId,s.rollNumber,s.firstName,s.lastName, s.middleName, s.fatherName, s.motherName, s.mothersMaidenName,s.dateOfBirth,s.bloodGroup,s.mobileNumber,s.phoneNumber,s.addressLine1,s.addressLine2,s.city,s.state,s.postalCode, s.parentEmail,s.summary,s.dateOfJoining,s.section,
s.className,s.hostelMode,ur.roleName,ur.roleDescription,ur.roleId,l.id leavesId,l.leaveStatus,l.leaveType,l.startTime,l.endTime,l.description,l.startDate,l.endDate,l.supervisorId leaveSupervisorId,IFNULL(l.leavesCount,0) as leavesCount, ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail,s.classNameClassId as classId
FROM vw_studentDetails s  JOIN leaves l on (s.accountId=l.accountId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN UserImage ui on (ui.id=s.imageId) LEFT JOIN academicYear a on (l.academicYearId = a.id);

create
or replace view vw_classExamDetails as
SELECT ss.name,ss.lastUpdatedDate,ss.description,IFNULL(ss.subjectNumber,'') as subjectNumber,es.id as scheduleId,es.custId,es.examDate,es.startTime,es.endTime,es.classSubjectId,es.academicYearId,sc.className,sc.id classSectionId,sc.section,es.version,et.id as eid,et.examType,IFNULL(et.minMarks,0)as minMarks,IFNULL(et.maxMarks,0) as maxMarks,cs.id classId,st.id subTypeId,st.subTypeName,st.schedule,IFNULL(es.maxMarks,0) as scheduleMaxMarks,es.startDate,es.endDate,st.predefinedSubType,cs.higherStandard,IFNULL(ss.sortingOrder,0) as sortingOrder,ss.language,IFNULL(st.sortingOrder,0) as subTypeSortingOrder,IFNULL(et.sortingOrder,0) as examTypeSortingOrder,es.Syllabus
FROM examSchedules es JOIN studyClass sc on (sc.id=es.classSectionId) JOIN class cs on (sc.classNameClassId=cs.id) JOIN studySubject ss on (ss.id=es.classSubjectId) JOIN examTypes et on(et.id=es.examTypeId) JOIN subType st on(st.id=es.subTypeId);
 
create or replace view vw_studentSubjectWiseMarksDetails as
	SELECT IFNULL(s.id,0) as studId,IFNULL(sm.id,0) as marksId,IFNULL(ss.id,0) as subjectId,IFNULL(et.id,0) as examTypeId,ss.name as subjectName,et.examType,sm.present,sm.obtainedMarks,sm.moderationMarks,sum(sm.obtainedMarks+sm.moderationMarks) as totalSubjectMarksObtained,SUM(es.maxMarks) as subjectTotalMarks,s.custId,s.academicYearId,IFNULL(s.classSectionId,0) as classSectionId
	FROM student s  LEFT JOIN  studentMarks sm on (s.id=sm.studId) LEFT JOIN  examSchedules es on (es.id=sm.examScheduleId) LEFT JOIN examTypes et on (et.id=es.examTypeId) LEFT JOIN studySubject ss on (ss.id=es.classSubjectId) LEFT JOIN studyClass sc on (sc.id=s.classSectionId) group by et.id,ss.id,s.id;
	
create or replace view vw_studentMarksDetails as
	SELECT IFNULL(s.id,0) as studId,IFNULL(sm.id,0) as marksId,IFNULL(et.id,0) examTypeId,s.custId,ss.id as subjectId,IFNULL(es.id,0) as scheduleId,s.rollNumber,IFNULL(s.classSectionId,0) as classSectionId,IFNULL(st.id,0) as subTypeId,et.examType,IFNULL(et.maxMarks,0) as maxMarks,IFNULL(et.minMarks,0) as minMarks,s.academicYearId,sm.obtainedMarks,ss.name as subjectName,
	es.startDate as examScStartDate,es.examDate,es.startTime,es.endTime,sm.lastUpdatedDate,sm.present,sc.className,sc.classNameClassId,sc.section,st.subTypeName,IFNULL(st.schedule,'N') as subTypeSchedule,IFNULL(es.maxMarks,0) as subTypeMaxMarks,
	sm.moderationMarks,IFNULL(vsm.totalSubjectMarksObtained,0) as totalSubjectMarksObtained,IFNULL(ss.language,'N') as language,s.status,IFNULL(et.sortingOrder,0) as examTypeSortingOrder,
	(sm.obtainedMarks+sm.moderationMarks) as obtainedSubTypeMarks,IFNULL(ss.sortingOrder,0) as subjectSortingOrder,IFNULL(st.sortingOrder,0) as subTypeSortingOrder,s.description studDiscontinueDesc,
	IFNULL(vsm.subjectTotalMarks,0) as subjectTotalMarks,ss.description as subjectShortName,sm.examScheduleId,sm.rankPosition
	FROM studentMarks sm  JOIN  student s on (s.id=sm.studId) JOIN  examSchedules es on (es.id=sm.examScheduleId) JOIN examTypes et on (et.id=es.examTypeId)
	 JOIN studySubject ss on (ss.id=es.classSubjectId) JOIN subType st on (st.id=es.subTypeId) JOIN studyClass sc on (sc.id=s.classSectionId) JOIN  vw_studentSubjectWiseMarksDetails vsm on (s.id=vsm.studId and ss.id=vsm.subjectId and et.id=vsm.examtypeId);
	
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


/*CREATE OR REPLACE VIEW vw_examScheduleDetailsWithTopperMarks as SELECT 
es.name,es.examType,es.subTypeName,es.scheduleId,es.scheduleMaxMarks,es.classSectionId,es.academicYearId,IFNULL(es.sortingOrder,0) as sortingOrder,IFNULL(es.examTypeSortingOrder,0) as examTypeSortingOrder,
IFNULL(es.subTypeSortingOrder,0) as subTypeSortingOrder,IFNULL(esm.subjectTopperMarks,0) as subjectTopperMarks 
FROM vw_classExamDetails es JOIN vw_examWiseTopperDetails esm on (es.scheduleId = esm.scheduleId); */

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


/*CREATE OR REPLACE VIEW vw_studentsMarksAndPerformanceDetails as 	
SELECT sm.studId,sm.obtainedMarks,sm.moderationMarks,IFNULL(td.subjectTopperMarks,0) as subjectTopperMarks,IFNULL(td.classSectionId,0) as classSectionId,IFNULL(sm.examScheduleId,0) as examScheduleId,sm.id as marksId,
IFNULL(td.custId,0) as custId,IFNULL(td.academicYearId,0) as academicYearId,IFNULL(sm.subjectPosition,0) as subjectPosition,sm.present
FROM studentMarks sm LEFT JOIN vw_examWiseTopperDetails td on (sm.examScheduleId=td.scheduleId); */
	
create 
or replace view vw_studentAttendance as
SELECT sd.accountId,sd.custId,sd.studentId, sd.academicYearId,sd.classNameClassId as classId,sd.classSectionId, sd.username, sd.className,sd.section,
sd.parentId,sd.status,sd.rollNumber,sd.firstName,sd.lastName, sd.middleName, sd.fatherName,sd.dateOfBirth,sd.mobileNumber,
sd.phoneNumber,sd.parentEmail,IFNULL(a.id,0) as attendanceId, a.attendanceDate,  IFNULL(a.category,'N') as category, IFNULL(a.leaveRequest,'N') as leaveRequest,a.month,IFNULL(a.monthNum,0) as monthNum,IFNULL(a.present,'Y') as present,IFNULL(a.year,0) as year
FROM vw_studentDetails sd LEFT JOIN attendance a on (sd.accountId=a.accountId);

create 
or replace view vw_onlineApplicationDetails as
SELECT o.id as applicationId,o.custId, o.parentEmail,o.fatherName,o.studentEmail,o.studentMobile,o.hostelMode,ui.id as imageId,ui.path as imagePath,ui.name as imageName,o.collectedDocuments,o.bloodGroup,o.version, o.motherName,o.occupation,o.firstName,o.lastName,o.phoneNumber,o.transferCertificateNo,IFNULL(o.annualIncome,0)as annualIncome,o.designation,o.motherQualification,o.fatherQualification,o.tempararyAddress,o.transportMode,o.placeOfBirth,
o.mobileNumber,o.status,o.streetName,IFNULL(o.subCastId,0) as subCastId,IFNULL(o.castId,0) as castId,o.city,o.state,o.academicYearId, o.createdDate, o.lastAccessDate, o.status as applicationStatus,c.className,o.applicationNumber,o.dateOfBirth,o.gender,o.postalCode, o.lastUpdatedDate,o.lastClassAttended,o.lastSchool,o.entranceMarks,IFNULL(o.classId,0) as classId,o.nationality,ctReli.skillTypeName as religion, 
a.admissionEndDate,a.testConducted,a.applicationFee,a.entranceDate,a.status as admissionStatus,ac.status as academicYearStatus,ac.academicYear as academicYearStr,o.present,o.rejectApplicationDesc,a.entranceExamTotalMarks,
ad.streetName as caddrStreetName,ad.city as caddrCity,ad.state as caddrState,ad.postalCode as caddrPostalCode,IFNULL(o.religionId,0) as religionId,cs.castName,scs.subCastName,o.boardingPointId,o.vehicleAcademicDetailsId,
r.id as routeId,IFNULL(o.categoryId,0) as categoryId,sc.categoryName,ctReli.skillTypeName,o.aadharCardNumber,a.registrationFee,a.prospectiveFee,0.receiptNumber,a.entranceExamPassMarks,IFNULL(o.committedFee,0) as committedFee 
FROM onlineApplicationDetails o LEFT JOIN admissionSettings a on (o.custId=a.custId and o.academicYearId=a.academicYearId) LEFT JOIN academicYear ac on(a.academicYearId=ac.id)
LEFT JOIN class c on (o.classId = c.id and o.academicYearId = c.academicYearId) LEFT JOIN Address ad on (ad.id=correAddrId) LEFT JOIN commonType ctReli on (ctReli.type='RELIGION' and ctReli.id=o.religionId)
LEFT JOIN UserImage ui on (ui.id=o.profileImage) LEFT JOIN castSettings cs on(cs.id=o.castId) LEFT JOIN subCastSettings scs on (scs.id=o.subCastId) LEFT JOIN schoolCategory sc on(sc.id=o.categoryId)
LEFT JOIN vehiclesAcademicDetails va on (va.id=o.vehicleAcademicDetailsId) LEFT JOIN vehicles v ON (v.id = va.vehicleId)
LEFT JOIN routeBoardingPoints br ON (br.id = o.boardingPointId) left join route r ON (br.routeId = r.id);
  
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
           

/*create
or replace view vw_studentClassFees as
SELECT cf.feeId,IFNULL(cf.feeAmount,0) as feeAmount,cf.custId,cf.feeTypeId,cf.schoolTermId,cf.classId,cf.academicYearId,cf.feeType,cf.fromdate,cf.toDate,cf.feeSettingId,cf.settingName,cf.settingType,cf.applToNewStuds,
cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.categoryId,cf.categoryName,s.id as studentId,s.rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions
FROM   vw_classFeeDetails cf left join student s on (cf.classId=s.classNameClassId) and (cf.categoryId=s.categoryId) ;
*/


create
or replace view vw_studentClassFees as
SELECT 
    cf.feeId,IFNULL(cf.feeAmount, 0) as feeAmount,cf.custId,cf.feeTypeId,cf.schoolTermId,cf.classId,cf.academicYearId,
    if(((s.transportMode = 'O' and cf.settingName = 'Transport Fee') or (s.transportMode = 'p' and cf.settingName = 'Transport Fee') or(s.hostelMode='D' and cf.settingName='Hostel Fee')),'N',cf.feeType) as feeType,
    cf.fromdate,cf.toDate,cf.feeSettingId,cf.settingName,cf.settingType,cf.applToNewStuds,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.categoryId,
    cf.categoryName,cf.routeBoardingPointId,s.id as studentId,s.rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,s.hostelMode,
    s.boardingPointId,cf.className,cf.section,cf.classSectionId,s.vehicleAcademicDetailsId
FROM vw_classFeeDetails cf JOIN student s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',(s.boardingPointId = cf.routeBoardingPointId and cf.custId=s.custId and cf.academicYearId=s.academicYearId  and s.transportMode = 'T' and cf.categoryName='General'),(cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and s.boardingPointId!=0 and cf.categoryId = s.categoryId)),
    cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId  and IF(s.joinedThroughAdmissions = 'N',(cf.categoryId = s.categoryId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and cf.applToNewStuds = 'N'),cf.categoryId = s.categoryId) ))  and s.classSectionId = cf.ClassSectionId and s.academicYearId=cf.academicYearId;
      
/*
IFNULL((select SUM(paymentAmount) from studentFeePaidDetails AS sf where sf.feeId = cf.feeId and cf.studentId = sf.studentId and cf.custId=sf.custId and sf.deleteStatus = 'Y'),0) as paymentTotalAmount,
IFNULL((select SUM(discountAmount) from studentFeePaidDetails AS sf where sf.feeId = cf.feeId and cf.studentId = sf.studentId and cf.custId=sf.custId and sf.deleteStatus = 'Y'), 0) as totalDiscountAmount
create or replace view vw_studentFeePaymentDetails as 
 select IFNULL(sp.id,0) as studentPaymentId,IFNULL(sp.paidAmount,0) as paidAmount,IFNULL(sfp.discountAmount,0) as discountAmount,IFNULL(sp.bankId,0) as bankId,sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,
 sp.lastUpdatedDate,sp.paymentType,sp.ddNumber,sp.chequeNumber,IFNULL(sp.invoiceNumber,0) as invoiceNumber,IFNULL(sfp.deleteStatus,'Y') as deleteStatus,
 IFNULL(sfp.paymentStatus,'N') as paymentStatus,sp.paymentDate,sfp.id as paymentId,IFNULL(sfp.paymentAmount,0) as paymentAmount,cf.academicYearId,cf.custId,cf.feeId,cf.applToNewStuds,
 cf.schoolTermId,IFNULL(cf.classId,0) as classId,cf.feeTypeId,cf.categoryId,cf.termName,cf.toDate,cf.toMonthName,cf.fromDate,cf.fromMonthName,cf.dueDate,
 cf.feeType,cf.categoryName,IFNULL(cf.feeAmount,0) as feeAmount,IFNULL(cf.studentId,0)as studentId,cf.rollNumber,cf.status,cf.description,cf.transportMode,cf.joinedThroughAdmissions,IFNULL(cf.vehicleId,0) as vehicleId,
 a.username,a.admissionNumber,p.firstName,p.lastName, p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail,p.fatherName,IFNULL(cf.feeSettingId,0) as feeSettingId,if(((cf.transportMode='O' and cf.settingName='Transport Fee') or (cf.transportMode='p' and cf.settingName='Transport Fee')),'N',cf.settingName) as settingName,cf.settingType,cf.routeBoardingPointId,
 cf.className,cf.section
from vw_studentClassFees cf  left join studentFeePaidDetails sfp on (cf.studentId=sfp.studentId and cf.feeId=sfp.feeId and sfp.deleteStatus='Y')
left join studentPayment sp on (sp.id = sfp.studentPaymentId)left join Account a on (cf.accountId=a.id)
LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);
*/

create
or replace view vw_onlineApplicationStudentClassFees as
select f.id as feeId, f.custId, t.id as schoolTermId, f.academicYearId, f.classId, f.feeTypeId,IFNULL(c.id,0) as categoryId,t.termName, t.toDate, t.toMonthName,t.fromDate,t.applToNewStuds, 
t.fromMonthName, t.dueDate,ft.feeType, f.feeAmount,c.categoryName,s.id as feeSettingId,s.settingName,s.settingType,s.status from Fee f left join schoolTerms t on (f.schoolTermId = t.id and f.feeAmount <> '0')left join feeType ft on f.feeTypeId=ft.id 
left join schoolCategory c on (f.categoryId = c.id and f.custId = c.custId) left join schoolFeeSetting s on s.id=t.feeSettingId;

/*
--- Transport Fee----
create
or replace view vw_classTransportFeeTypes as
select c.id as classId, c.custId, c.academicYearId, c.className, f.id as feeTypeId, f.feeType from  class c , transportFeeType f where c.custId=f.custId;


create
or replace view vw_classTransportFeeDetails as
select f.id as feeId, f.custId, t.id as schoolTermId, f.academicYearId, f.classId, f.feeTypeId,c.id as categoryId,t.termName, t.toDate, t.toMonthName,t.fromDate, 
t.fromMonthName, t.dueDate,ft.feeType, f.feeAmount,c.categoryName,f.categoryId as transportCategoryId
from transportFee f left join transportSchoolTerms t on f.schoolTermId = t.id left join transportFeeType ft on f.feeTypeId=ft.id 
left join transportCategory c on f.categoryId=c.id and f.custId=ft.custId;

create
or replace view vw_studentClassTransportFees as
SELECT cf.feeId,IFNULL(cf.feeAmount,0) as feeAmount,cf.custId,cf.feeTypeId,cf.schoolTermId,cf.classId,cf.academicYearId,cf.feeType,cf.fromdate,cf.toDate,cf.fromMonthName,
cf.toMonthName,cf.termName,cf.dueDate,cf.categoryId,cf.transportCategoryId,cf.categoryName,s.id as studentId,s.rollNumber,s.accountId,s.status,s.description,
IFNULL(s.vehicleId,0) as vehicleId,IFNULL(s.boardingPointId,0) as boardingPointId,IFNULL(rb.boardingPointFeeAmount,0) as boardingPointFeeAmount
FROM   vw_classTransportFeeDetails cf left join student s on (cf.classId=s.classNameClassId) and (cf.categoryId=s.transportCategoryId) 
left join routeBoardingPoints rb on (rb.id=s.boardingPointId);
	
create
or replace view vw_studentClassTransportFeePaymentDetails as
SELECT IFNULL(cf.studentId,0) as studentId,IFNULL(sfp.id,0) paymentId,IFNULL(sfp.studentPaymentId,0) studentPaymentId,sfp.lastUpdatedDate,cf.feeId,
cf.schoolTermId,cf.feeTypeId,cf.classId,cf.academicYearId,cf.custId,cf.categoryId,cf.transportCategoryId,IFNULL(cf.feeAmount,0) as feeAmount,cf.feeType,
cf.fromdate,cf.toDate,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.categoryName,IFNULL(sfp.paymentAmount,0) as paymentAmount,
IFNULL((select SUM(paymentAmount) from transportStudentFeePaidDetails AS sf where sf.feeId=cf.feeId and cf.studentId=sf.studentId and sf.deleteStatus='Y'),0) as paymentTotalAmount,
IFNULL(sfp.paymentStatus,'N') as paymentStatus,IFNULL(sfp.discountAmount,0) as discountAmount,IFNULL(sfp.deleteStatus,'Y') as deleteStatus,
IFNULL((select SUM(discountAmount) from transportStudentFeePaidDetails AS sf where sf.feeId=cf.feeId and cf.studentId=sf.studentId and sf.deleteStatus='Y'),0) as totalDiscountAmount,
cf.rollNumber,cf.status,IFNULL(cf.vehicleId,0) as vehicleId,IFNULL(cf.boardingPointId,0) as boardingPointId,IFNULL(cf.boardingPointFeeAmount,0) as boardingPointFeeAmount,
a.username,p.firstName,p.lastName, p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail
FROM   vw_studentClassTransportFees cf LEFT JOIN transportStudentFeePaidDetails sfp on (cf.studentId=sfp.studentId and cf.feeId = sfp.feeId and sfp.deleteStatus='Y')
 left join Account a on (cf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);

create or replace view vw_studentTransportFeePaymentDetails as 
select IFNULL(sp.id,0) as studentPaymentId,IFNULL(sp.paidAmount,0) as paidAmount,IFNULL(sp.discountAmount,0) as discountAmount,
IFNULL(sp.bankId,0) as bankId,sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,sp.ddNumber,
sp.chequeNumber,IFNULL(sp.invoiceNumber,0) as invoiceNumber,IFNULL(sp.deleteStatus,'Y') as deleteStatus,IFNULL(sp.paymentStatus,'N') as paymentStatus,
sp.paymentDate,sfp.id as paymentId,IFNULL(sfp.paymentAmount,0) as paymentAmount,cf.academicYearId,cf.custId,cf.feeId,cf.schoolTermId,
cf.classId,cf.feeTypeId,cf.categoryId,cf.termName,cf.toDate,cf.toMonthName,cf.fromDate,cf.fromMonthName,cf.dueDate,cf.feeType,cf.categoryName,
IFNULL(cf.feeAmount,0) as feeAmount,IFNULL(cf.studentId,0)as studentId,IFNULL(cf.vehicleId,0) as vehicleId,cf.rollNumber,cf.status,cf.description,
a.username,p.firstName,p.lastName,p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail
from vw_studentClassTransportFees cf left join transportStudentFeePaidDetails sfp on (cf.studentId = sfp.studentId and cf.feeId=sfp.feeId and sfp.deleteStatus='Y')
 left join transportStudentPayment sp on (sp.id=sfp.studentPaymentId) left join Account a on (cf.accountId=a.id)
  LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);

---- Hestel Fee---
create
or replace view vw_classHostelFeeTypes as
select c.id as classId, c.custId, c.academicYearId, c.className, f.id as feeTypeId, f.hostelFeeType as feeType from  class c , hostelFeeType f where c.custId=f.custId;

create
or replace view vw_classHostelFeeDetails as
select f.id as feeId, f.custId, t.id as hostelTermId, f.academicYearId, f.classId, f.hostelFeeTypeId as feeTypeId,c.id as hostelCategoryId,t.hostelTermName as termName, t.toDate, t.toMonthName,t.fromDate, 
t.fromMonthName, t.dueDate,ft.hostelFeeType as feeType, f.feeAmount,c.categoryName 
from hostelFee f left join hostelTerms t on f.hostelTermId = t.id left join hostelFeeType ft on f.hostelFeeTypeId=ft.id 
left join hostelCategory c on f.hostelCategoryId=c.id and f.custId=ft.custId;

create
or replace view vw_studentClassHostelFees as
SELECT cf.feeId,IFNULL(cf.feeAmount,0) as feeAmount,cf.custId,cf.feeTypeId,cf.hostelTermId,cf.classId,cf.academicYearId,cf.feeType,cf.fromdate,cf.toDate,cf.fromMonthName,
cf.toMonthName,cf.termName,cf.dueDate,cf.hostelCategoryId,cf.categoryName,s.id as studentId,s.rollNumber,s.accountId,s.status,IFNULL(s.bedId,0) as bedId,s.description,
IFNULL(b.bedCost,0) as bedCost FROM  vw_classHostelFeeDetails cf left join student s on (cf.classId=s.classNameClassId) and (cf.hostelCategoryId=s.hostelCategoryId)
 left join bed b on (b.id=s.bedId);

create or replace view vw_studentClassHostelFeePaymentDetails as 
select IFNULL(cf.studentId,0) as studentId,IFNULL(sfp.id,0) paymentId,IFNULL(sfp.studentPaymentId,0) studentPaymentId,sfp.lastUpdatedDate,cf.feeId,
cf.hostelTermId,cf.feeTypeId,cf.classId,cf.academicYearId,cf.custId,cf.hostelCategoryId,IFNULL(cf.feeAmount,0) as feeAmount,cf.feeType,
cf.fromdate,cf.toDate,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.categoryName,IFNULL(sfp.paymentAmount,0) as paymentAmount,
IFNULL((select SUM(paymentAmount) from hostelStudentFeePaidDetails AS sf where sf.feeId=cf.feeId and cf.studentId=sf.studentId and sf.deleteStatus='Y'),0) as paymentTotalAmount,
IFNULL(sfp.paymentStatus,'N') as paymentStatus,IFNULL(sfp.discountAmount,0) as discountAmount,IFNULL(sfp.deleteStatus,'Y') as deleteStatus,
IFNULL((select SUM(discountAmount) from hostelStudentFeePaidDetails AS sf where sf.feeId=cf.feeId and cf.studentId=sf.studentId and sf.deleteStatus='Y'),0) as totalDiscountAmount,
cf.rollNumber,cf.status,IFNULL(cf.bedId,0) as bedId,IFNULL(cf.bedCost,0) as bedCost,a.username,p.firstName,p.lastName, p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail
from vw_studentClassHostelFees cf left join hostelStudentFeePaidDetails sfp on (cf.studentId = sfp.studentId and cf.feeId = sfp.feeId and sfp.deleteStatus='Y')
 left join Account a on (cf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);

create
or replace view  vw_studentHostelFeePaymentDetails as
SELECT IFNULL(sp.id,0) as studentPaymentId,IFNULL(sp.paidAmount,0) as paidAmount,IFNULL(sp.discountAmount,0) as discountAmount,
IFNULL(sp.bankId,0) as bankId,sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,IFNULL(sp.ddNumber,0) as ddNumber,
IFNULL(sp.chequeNumber,0) as chequeNumber,IFNULL(sp.invoiceNumber,0) as invoiceNumber,IFNULL(sp.deleteStatus,'Y') as deleteStatus,IFNULL(sp.paymentStatus,'N') as paymentStatus,
sp.paymentDate,sfp.id as paymentId,IFNULL(sfp.paymentAmount,0) as paymentAmount,cf.academicYearId,cf.custId,cf.feeId,cf.hostelTermId,
cf.classId,cf.feeTypeId,cf.termName,cf.toDate,cf.toMonthName,cf.fromDate,cf.fromMonthName,cf.dueDate,cf.feeType,cf.categoryName,
IFNULL(cf.feeAmount,0) as feeAmount,IFNULL(cf.studentId,0)as studentId,IFNULL(cf.bedId,0) as bedId,IFNULL(cf.bedCost,0) as bedCost,
cf.rollNumber,cf.status,cf.description,a.username,p.firstName,p.lastName,p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail,cf.hostelCategoryId
FROM   vw_studentClassHostelFees cf LEFT JOIN hostelStudentFeePaidDetails sfp on (cf.studentId=sfp.studentId and cf.feeId = sfp.feeId and sfp.deleteStatus='Y') 
left join hostelStudentPayment sp on (sp.id=sfp.studentPaymentId) left join Account a on (cf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);
 */
-- Staff Hostel Fee CVS 8-mar-2012 staff hostel fee views
create
or replace view vw_staffHostelFeeTypes as
select s.id as staffId, s.custId, s.academicYearId, f.id as hostelFeeTypeId, f.hostelFeeType from  staff s , hostelFeeType f where s.custId=f.custId;

create
or replace view vw_staffHostelFeeDetails as
select sf.id as staffHostelFeeId, sf.custId, t.id as hostelTermId, sf.academicYearId,sf.hostelFeeTypeId,c.id as hostelCategoryId,t.hostelTermName, t.toDate, t.toMonthName,t.fromDate, 
t.fromMonthName, t.dueDate,ft.hostelFeeType, sf.feeAmount,c.categoryName from staffHostelFee sf left join hostelTerms t on sf.hostelTermId = t.id left join hostelFeeType ft on sf.hostelFeeTypeId=ft.id 
left join hostelCategory c on sf.hostelCategoryId=c.id and sf.custId=ft.custId;

create
or replace view vw_staffHostelFees as
SELECT sf.staffHostelFeeId,IFNULL(sf.feeAmount,0) as feeAmount,sf.custId,sf.hostelFeeTypeId,sf.hostelTermId,sf.academicYearId,sf.hostelFeeType,sf.fromdate,
	sf.toDate,sf.fromMonthName,sf.toMonthName,sf.hostelTermName,sf.dueDate,sf.hostelCategoryId,sf.categoryName,s.id as staffId,s.accountId,s.status,s.description,
	IFNULL(s.bedId,0) as bedId,IFNULL(b.bedCost,0) as bedCost FROM  vw_staffHostelFeeDetails sf left join staff s on (sf.hostelCategoryId=s.hostelCategoryId) 
	and sf.custId=s.custId LEFT JOIN bed b on (b.id=s.bedId);

create or replace view vw_hostelStaffFeePaymentDetails as 
select IFNULL(sf.staffId,0) as staffId,IFNULL(sfp.id,0) paymentId,IFNULL(sfp.hostelStaffPaymentId,0) hostelStaffPaymentId,sfp.lastUpdatedDate,IFNULL(sf.staffHostelFeeId,0) as staffHostelFeeId,IFNULL(sfp.paymentAmount,0) as paymentAmount,
IFNULL((select SUM(paymentAmount) from hostelStaffFeePaidDetails AS hsf where hsf.staffHostelFeeId=sf.staffHostelFeeId and hsf.staffId=sf.staffId and hsf.deleteStatus='Y'),0) as paymentTotalAmount,
IFNULL(sfp.paymentStatus,'N') as paymentStatus,IFNULL(sfp.discountAmount,0) as discountAmount,IFNULL(sfp.deleteStatus,'Y') as deleteStatus,
IFNULL((select SUM(discountAmount) from hostelStaffFeePaidDetails AS hsf where hsf.staffHostelFeeId=sf.staffHostelFeeId and hsf.staffId=sf.staffId and hsf.deleteStatus='Y'),0) as totalDiscountAmount,
sf.hostelTermId,sf.hostelFeeTypeId,sf.hostelCategoryId,sf.hostelTermName,sf.toDate,sf.toMonthName,sf.fromDate,sf.fromMonthName,
sf.dueDate,sf.hostelFeeType,sf.categoryName,IFNULL(sf.feeAmount,0) as feeAmount,sf.status,IFNULL(sf.bedId,0) as bedId,a.username,p.firstName,p.lastName, p.middleName, p.mobileNumber,
p.phoneNumber,p.parentEmail,sf.custId,sf.academicYearId,sf.bedCost
from vw_staffHostelFees sf left join hostelStaffFeePaidDetails sfp on (sfp.staffId = sf.staffId and sfp.staffHostelFeeId=sf.staffHostelFeeId and sfp.deleteStatus='Y')
 left join Account a on (sf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);

create
or replace view vw_staffHostelFeePaymentDetails as
SELECT IFNULL(sp.id,0) hostelStaffPaymentId,IFNULL(sp.paidAmount,0) as paidAmount,IFNULL(sp.discountAmount,0) as discountAmount,
IFNULL(sp.bankId,0) as bankId,sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,IFNULL(sp.ddNumber,0) as ddNumber,
IFNULL(sp.chequeNumber,0) as chequeNumber,IFNULL(sp.invoiceNumber,0) as invoiceNumber,IFNULL(sp.deleteStatus,'Y') as deleteStatus,IFNULL(sfp.paymentStatus,'N') as paymentStatus,
sp.paymentDate,IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount,0) as paymentAmount,sf.academicYearId,sf.custId,sf.staffHostelFeeId,sf.hostelTermId,
sf.hostelFeeTypeId,sf.hostelTermName,sf.toDate,sf.fromMonthName,sf.toMonthName,sf.dueDate,sf.hostelFeeType,sf.categoryName,IFNULL(sf.feeAmount,0) as feeAmount,
IFNULL(sf.staffId,0) as staffId,IFNULL(sf.bedId,0) as bedId,IFNULL(sf.bedCost,0) as bedCost,sf.hostelCategoryId,sf.fromdate,
sf.status,sf.description,a.username,p.firstName,p.lastName,p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail
from vw_staffHostelFees sf left join hostelStaffFeePaidDetails sfp on (sfp.staffId = sf.staffId and sfp.staffHostelFeeId=sf.staffHostelFeeId and sfp.deleteStatus='Y')
left join hostelStaffPayment sp on (sp.id=sfp.hostelStaffPaymentId) left join Account a on (sf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);
		
-- CVS 30-Dec-2011 
CREATE OR REPLACE VIEW vw_allUsers AS
select a.id as accountId,IFNULL(a.parentId,0) as parentId,a.username,a.password,a.accountEnabled,ifnull(a.bioMetricId,0) as bioMetricId,p.gender,
	ifnull(a.personId,0) as personId,ifnull(a.paddressId,0) as addressId,d.addressLine1,d.streetName,d.state as stateCode,
	IFNULL(d.stateId,0) as stateId,d.postalCode,ifnull(a.custId,0) as custId,a.lastAccessDate,a.lastUpdatedDate, ifnull(ur.roleId,0) as roleId,
	r.name AS roleName,r.description AS roleDescription,p.firstName,p.lastName,p.parentEmail,p.studentEmail,p.mobileNumber, p.fatherName,p.dateOfBirth, 
	p.studentMobile, p.bloodGroup, p.designation as staffDesignation, d.email as staffEmail, d.city, ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail,a.accountExpired,
	IFNULL(a.admissionNumber,0) as admissionNumber
from UserRoles ur 
	LEFT JOIN Account a  on (ur.userId=a.id)
	LEFT JOIN Person p  on (a.personId=p.id)
	LEFT JOIN  Address d on (a.paddressId=d.id)
	LEFT JOIN Role r on (ur.roleId=r.id)
	LEFT JOIN UserImage ui on (ui.id=a.imageId);

create
or replace view vw_studentQuizQuestionAnswers as
SELECT  qa.id as questionAnswerId,q.questionName,q.custId,q.id questionId,q.quizId,q.status, q.version,q.createdDate,q.lastAccessDate,q.lastUpdatedDate, qa.questionAnswer,qa.anserOptions,qa.correctAnswer
FROM quizQuestion q  RIGHT JOIN questionAnswer qa on (q.id=qa.questionId);

create
or replace view vw_studentQuestionAnswers as
SELECT  q.questionName,q.startDate,q.endDate,q.custId,q.id as questionId,q.quizId,q.status, qa.studentAnswer,qa.studentCorrectAnswer,qa.correctAnswer,qa.studentId
FROM quizQuestion q  RIGHT JOIN studentQuestionAnswer qa on (q.id=qa.questionId);

/*create
or replace view vw_parentFeedBackResponse as
SELECT  pfr.id as parentFeedbackResultId,pfr.parentId,fq.custId,fq.id as feedbackQuestionId,fq.description,fq.activityType,fg.id feedbackGradeId,fg.description gradeDescription,fg.title
FROM feedbackQuestions fq RIGHT JOIN parentFeedbackResult pfr on(fq.id=pfr.feedbackQuestionId)LEFT JOIN feedbackGrades fg on(fg.id=pfr.feedbackGradeId);
*/
create 
or replace view vw_studyClassSubjectDetails as
SELECT sc.classNameClassId classId, sc.id as studyClassId,sc.className,sc.section,sc.custId,sc.academicYearId,ss.id as subjectId,ss.name as subjectName,IFNULL(ss.subjectNumber,'') as subjectNumber,IFNULL(ss.sortingOrder,0) as sortingOrder,IFNULL(c.sortingOrder,0) as classSortingOrder,c.noOfSections,ss.language,sc.educationType 
FROM ClassSubject cs LEFT JOIN  studyClass sc on (cs.studyClassId=sc.id) LEFT JOIN studySubject ss on (cs.subjectId=ss.id) LEFT JOIN class c on(c.id = sc.classNameClassId);

create
or replace view vw_classExamSubTypes as
SELECT ce.classNameId classId, et.id examTypeId,et.examType,et.minMarks,et.maxMarks,st.id as subTypeId,st.subTypeName,st.schedule,et.custId,st.predefinedSubType,IFNULL(et.sortingOrder,0) as examTypeSortingOrder,IFNULL(st.sortingOrder,0) as subTypeSortingOrder
FROM classExamTypes ce LEFT JOIN examTypes et on (ce.examTypeId=et.id) LEFT JOIN subType st  on (st.custId=et.custId and st.academicYearId=et.academicYearId);

create
or replace view vw_examSchedule as
SELECT scs.studyClassId as classSectionId,scs.academicYearId,scs.className,scs.section,scs.subjectName,
ces.examTypeId,ces.examType,ces.minMarks,
IFNULL(es.maxMarks,0.0) scheduleMaxMarks,es.examDate,es.startTime,es.endTime,
scs.custId,IFNULL(es.id,0) as scheduleId,scs.subjectId,ces.subTypeId,ces.subTypeName,ces.schedule as subTypeSchedule,IFNULL(es.scheduled,'N') as scheduled,scs.classId,ces.maxMarks as examTypeMaxMarks,ces.predefinedSubType,scs.subjectNumber,IFNULL(scs.sortingOrder,0) as subjectSortingOrder,IFNULL(scs.classSortingOrder,0) as classSortingOrder,IFNULL(ces.examTypeSortingOrder,0) as examTypeSortingOrder,IFNULL(ces.subTypeSortingOrder,0) as subTypeSortingOrder  
FROM vw_studyClassSubjectDetails scs LEFT JOIN vw_classExamSubTypes ces on (scs.classId = ces.classId) 
LEFT JOIN  examSchedules es on (es.classSectionId=scs.studyClassId and es.classSubjectId=scs.subjectId and es.examTypeId = ces.examTypeId and es.subTypeId=ces.subTypeId);
 
create 
or replace view vw_staffSubjectsDetails as
SELECT s.accountId,s.id as staffId,cteacher.id as classTeacherId,sc.classNameClassId as classId,IFNULL(ss.id,0) as classSubjectId,cteacher.classTeacher as classTeacher,cteacher.studyClassId,IFNULL(cteacher.studySubjectId,0) as studySubjectId,ss.name as subjectName,sc.className,sc.section,sc.custId,cteacher.academicYearId,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  <=> '','',CONCAT(' - ',sc.section))) as classAndSection,IFNULL(css.periodsPerWeek,0) as periodsPerWeek,IFNULL(cteacher.periodsCount,0) as periodsHandleCount,c.sortingOrder as classSortingOrder,s.status,IFNULL(css.subjectPriority,0) as subjectPriority
FROM classTeacher cteacher LEFT JOIN staff s on (s.id=cteacher.teacherId) LEFT JOIN studySubject ss on (cteacher.studySubjectId=ss.id) LEFT JOIN studyClass sc on (cteacher.studyClassId=sc.id) LEFT JOIN class c on (c.id=sc.classNameClassId) LEFT JOIN classSubjectsSettings css on (css.classSectionId = cteacher.studyClassId and css.studySubjectId = cteacher.studySubjectId);

/*CREATE OR REPLACE VIEW  vw_classSectionSubject as
SELECT c.id as classId,sc.id classSectionId,ss.id as subjectId, sc.academicYearId,sc.className,sc.section,ss.name subjectName,sc.custId,sc.filledSeats
FROM class c  LEFT JOIN studyClass sc on (c.id=sc.classNameClassId) LEFT JOIN ClassSubject cs on (sc.id=cs.studyClassId) LEFT JOIN studySubject ss on (cs.subjectId=ss.id);
*/ 
CREATE OR REPLACE VIEW  vw_classSectionTeacher as
SELECT c.id as classId,sc.id classSectionId,sc.filledSeats,sc.sectionCapacity, sc.academicYearId,sc.className,sc.section,IFNULL(ct.teacherId,0) as teacherId,IFNULL(ct.classTeacher,'N') as classTeacher,IFNULL(ct.studySubjectId,0) as subjectId,IFNULL(vsd.firstName,'') as firstName,IFNULL(vsd.lastName,'')as lastName,IFNULL(vsd.mobileNumber,'') as mobileNumber,IFNULL(vsd.username,'') as username,IFNULL(vsd.accountId,0) as accountId,c.sortingOrder as classSortingOrder,vsd.custId,vsd.status
FROM class c  LEFT JOIN studyClass sc on (c.id=sc.classNameClassId and c.className is not NULL) LEFT JOIN classTeacher ct on (ct.studyClassId=sc.id) LEFT JOIN vw_staffDetails vsd on (ct.teacherId=vsd.staffId and ct.academicYearId=vsd.academicYearId);

/* @Description 30th Apr cvs: Modularization  below add new views for  LibraryAction*/
/* create 
or replace view vw_bookAndLabelDetails as
SELECT bl.id as bookLableId, bt.id as bookTitleId,bt.bookName,bt.otherSubjects,bt.publisher,IFNULL(bt.issueDays,0) as issueDays,IFNULL(bt.issueBookCount,0) as issueBookCount,bt.readingBookCount,bt.cost,bt.noOfCopies,bt.author,bl.custId,bl.lableCode,bl.type,bl.deleteStatus,bl.bookLabelStatus,ls.id as librarySettingsId,IFNULL(ls.fineAmountPerDay,0) as fineAmountPerDay,IFNULL(ls.limitDays,0) as limitDays,IFNULL(ls.noOfStaffIssuBooks,0) as noOfStaffIssuBooks,IFNULL(ls.noOfStudentIssuBooks,0) as noOfStudentIssuBooks,ss.name
FROM bookTitle bt  JOIN bookLables bl on ((bl.bookTitleId = bt.id))  LEFT JOIN librarySettings ls on (bt.custId=ls.custId) LEFT JOIN studySubject ss on (bt.subjectId=ss.id) ;


create 
or replace view vw_issuedBookAndSettings as
SELECT ib.id as issuedBookId,bt.id as bookTitleId,bt.bookName,bt.otherSubjects,bt.cost,bt.totalCost,bt.readingBookCount,bt.issueBookCount,bt.issueDays,bt.bookRequestExpareDays,bt.publisher,bt.author,ib.accountId,ib.custId,ib.issuedDate,ib.dueDate,ib.status,ib.submitedDate,ib.fineAmount,bl.lableCode,bl.id  as lableCodeId,bl.type,bl.deleteStatus,bl.bookLabelStatus, ls.id as librarySettingsId,ls.fineAmountPerDay,ls.limitDays,ls.noOfStaffIssuBooks,ls.noOfStudentIssuBooks,a.username,ss.name
FROM ((bookTitle bt  JOIN bookLables bl on ((bl.bookTitleId = bt.id))) JOIN issuedBook ib on (((ib.bookLableId = bl.id) and (ib.bookId = bt.id)))) LEFT JOIN librarySettings ls on (ib.custId=ls.custId) LEFT JOIN Account a on (ib.accountId=a.id) LEFT JOIN studySubject ss on (bt.subjectId=ss.id);
*/
/* create or replace view vw_bookTitleAndBlockDetails as
SELECT bb.id, bt.bookName,ss.name,bt.otherSubjects,bt.publisher,bt.readingBookCount,bt.issueBookCount,bt.issueDays,bt.totalCost,bt.cost,bt.noOfCopies,bt.bookRequestExpareDays,bt.bookKeyWord, bt.author,bb.custId,b.blockName,rd.rackName,bt.id as bookTitleId
FROM bookAndBlockDetails bb LEFT JOIN bookTitle bt on (bb.bookId = bt.id)  LEFT JOIN block b on (bb.blockId=b.id)  LEFT JOIN rackDetails rd on (bb.rackId=rd.id) LEFT JOIN studySubject ss on (bt.subjectId=ss.id) ;
*/
/*------------------------------------------------------------*/	 
 
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

/*CREATE OR REPLACE VIEW vw_attendance as

SELECT att.id attId, att.custId, att.academicYearId, att.accountId,  vst.studentId
s.id staffId
FROM attendance att LEFT JOIN leaves l on (att.accountId = l.accountId and ) LEFT JOIN  vw_staffDetails vt on (att.accountId = vt.accountId);
*/


-- Venkatesh 01-Dec-2011
/*create or replace view vw_studentBoardingPointDetails as
	SELECT IFNULL(s.vehicleId,0) as studVehicleId,IFNULL(s.boardingPointId,0) as studBoardingId, IFNULL(count(boardingPointId),0) as studentsCount
	FROM student s where s.transportMode='T' group by s.vehicleId,s.boardingPointId;

create 
or replace view vw_staffVehicleTripDetails as
	SELECT v.id as vehicleId,d.custId,d.userName as driverUsername, d.staffId as driverStaffId,d.qualification1 as driverQualification1 ,d.mobileNumber as driverMobileNumber,d.phoneNumber as driverPhoneNumber,d.licenseNumber as driverLicenseNumber,d.licenseExpDate as diverLicenseExpDate,d.firstName as driverFirstName,d.lastName as driverLastName,d.middleName as driverMiddleName,d.dateOfJoining as driverDateOfJoining,d.dateOfBirth as driverDateOfBirth,d.bloodGroup  as driverBloodGroup,d.addressLine1 as driverAddressLine1, d.addressLine2 as driverAddressLine2,d.city as driverCity,d.state as driverState,d.postalCode as driverPostalCode,d.roleName as driverRoleName,d.roleId as driverRoleId,
	 h.userName as helperUsername, h.staffId as helperStaffId,h.qualification1 as helperQualification1 ,h.mobileNumber as helperMobileNumber,h.phoneNumber as helperPhoneNumber,h.licenseNumber as helperLicenseNumber,h.licenseExpDate as helperLicenseExpDate,h.firstName as helperFirstName,h.lastName as helperLastName,h.middleName as helperMiddleName,h.dateOfJoining as helperDateOfJoining,h.dateOfBirth as helperDateOfBirth,h.bloodGroup as helperBloodGroup,h.addressLine1 as helperAddressLine1,h.addressLine2 as helperAddressLine2,h.city as helperCity,h.state as helperState,h.postalCode as helperPostalCode,h.roleName as helperRoleName,d.roleId as helperRoleId,
	 v.driverId,v.helperId,v.status vehicleStatus,v.createdDate,v.lastAccessDate,v.lastUpdatedDate,v.version,v.chasisNumber,v.classificationType,v.engineNumber,v.insuranceExpiredDate,v.insuranceNumber,v.noOfSeats,v.ownerName,v.vehicleNumber,v.vehicleType,r.id as routeId,r.routeName,r.routePointName,r.academicYearId,r.routePointStartTime,r.routePointEndTime,r.routeEndName,rb.boardingPointName,rb.boardingPointStatTime,rb.boardingPointEndTime,rb.id as boardingPointId,IFNULL(stbp.studentsCount,0) as studentsCount 
	 FROM vehicles v LEFT JOIN vw_staffDetails d on (v.driverId=d.accountId) LEFT JOIN vw_staffDetails h on  (v.helperId=h.accountId) LEFT JOIN route r on (r.id=v.routeId) JOIN routeBoardingPoints rb on (r.id=rb.routeId)  LEFT JOIN vw_studentBoardingPointDetails stbp on  (stbp.studVehicleId = v.id && stbp.studBoardingId = rb.id);
*/
/*create 
or replace view vw_staffVehicleTripDetails as
	SELECT v.id as vehicleId,d.custId,d.userName as driverUsername, d.staffId as driverStaffId,d.qualification1 as driverQualification1 ,d.mobileNumber as driverMobileNumber,d.phoneNumber as driverPhoneNumber,d.licenseNumber as driverLicenseNumber,d.licenseExpDate as diverLicenseExpDate,d.firstName as driverFristName,d.lastName as driverLastName,d.middleName as driverMiddleName,d.dateOfJoining as driverDateOfJoining,d.dateOfBirth as driverDateOfBirth,d.bloodGroup  as driverBloodGroup,d.addressLine1 as driverAddressLine1, d.addressLine2 as driverAddressLine2,d.city as driverCity,d.state as driverState,d.postalCode as driverPostalCode,d.roleName as driverRoleName,d.roleId as driverRoleId,
	 h.userName as helperUsername, h.staffId as helperStaffId,h.qualification1 as helperQualification1 ,h.mobileNumber as helperMobileNumber,h.phoneNumber as helperPhoneNumber,h.licenseNumber as helperLicenseNumber,h.licenseExpDate as helperLicenseExpDate,h.firstName as helperFristName,h.lastName as helperLastName,h.middleName as helperMiddleName,h.dateOfJoining as helperDateOfJoining,h.dateOfBirth as helperDateOfBirth,h.bloodGroup as helperBloodGroup,h.addressLine1 as helperAddressLine1,h.addressLine2 as helperAddressLine2,h.city as helperCity,h.state as helperState,h.postalCode as helperPostalCode,h.roleName as helperRoleName,d.roleId as helperRoleId,
	 v.driverId,v.helperId,v.status vehicleStatus,v.createdDate,v.lastAccessDate,v.lastUpdatedDate,v.version,v.chasisNumber,v.classificationType,v.engineNumber,v.insuranceExpiredDate,v.insuranceNumber,v.noOfSeats,v.ownerName,v.registrationNumber,v.vehicleNumber,v.vehicleType,r.id as routeId,r.routeName,r.routePointName,r.academicYearId,r.routePointStartTime,r.routePointEndTime,r.routeEndName,rb.boardingPointName,rb.boardingPointStatTime,rb.boardingPointEndTime
	FROM vehicles v LEFT JOIN vw_staffDetails d on (v.driverId=d.accountId) LEFT JOIN vw_staffDetails h on  (v.helperId=h.accountId) LEFT JOIN routewithvehicles rv  on((v.id = rv.vehicleId))) left join route r on((r.id = rv.routeId)))  left JOIN routeBoardingPoints rb on (r.id=rb.routeId);

create 
or replace view vw_routeVehicleInfo as
	SELECT r.id as routeId,r.routeName,r.routePointName,r.routePointStartTime,r.routePointEndTime,r.routeNumber,v.id as vehicleId,v.driverId,v.helperId,v.custId,v.status vehicleStatus,v.chasisNumber,v.classificationType,v.engineNumber,v.insuranceExpiredDate,v.insuranceNumber,v.noOfSeats,v.ownerName,v.registrationNumber,v.vehicleNumber,v.vehicleType
	FROM vehicles v LEFT JOIN route r on (r.id=v.routeId);
*/


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

 create or replace view vw_hostelStudentDetails as SELECT 
a.id as accountId,a.custId,a.username,a.parentId,a.accountExpired,a.admissionNumber,s.id as studentId,s.academicYearId,s.description,s.status,s.classNameClassId,
s.rollNumber,s.joinedThroughAdmissions,s.categoryId,s.hostelMode,p.firstName,p.lastName,p.middleName,p.fatherName,p.motherName,p.mothersMaidenName,p.dateOfBirth,p.mobileNumber,
p.phoneNumber,p.parentEmail,p.gender,p.identification1,p.identification2,p.bloodGroup,d.city,d.state,d.postalCode,c.className,c.section,
CONCAT(IF(c.className IS NULL, '', c.className),IF(c.section IS NULL || c.section <=> '','',CONCAT(' - ', c.section))) as classNameAndSection,
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
    LEFT JOIN vw_hostelBuildingDetails hb on hb.buildingId=f.buildingId;
    
    
  
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
   
create or replace view vw_studentsAllotedBeds as
SELECT s.studentId,s.custId,s.academicYearId,s.status,s.accountId,s.username,s.admissionNumber,s.classNameClassId as classId,s.rollNumber,s.categoryId,s.firstName,s.lastName,
s.middleName,s.fatherName,s.motherName,s.dateOfBirth,s.bloodGroup,s.mobileNumber,s.castId,s.subCastId,s.religionId,s.nationality,s.gender,s.motherToungId,
s.className,s.section,s.mediumId,s.higherStandard,s.classSectionId,s.castName,s.subCastName,s.religion,s.bedId,addr.streetName,addr.city,st.stateName,addr.postalCode,b.bedName,b.bedLevel,b.bedCost,
b.status as bedStatus,r.id as roomId,r.roomName,r.roomLevel,r.roomType,r.floorId,f.floorName,f.floorLevel,buil.id as buildingId,buil.buildingName,buil.buildingShortName,h.hostelName
FROM vw_studentDetails s  JOIN bed b on(s.bedId=b.id) LEFT JOIN room r on (b.roomId=r.id) LEFT JOIN floor f on (r.floorId=f.id) LEFT JOIN building buil on (f.buildingId=buil.id) LEFT JOIN hostel h on (buil.hostelId=h.id) LEFT JOIN Address addr on (h.addressId=addr.id) LEFT JOIN State st on (st.stateCode=addr.state); 

create or replace view vw_staffAllotedBeds as
SELECT s.staffId,s.custId,s.academicYearId,s.status,s.accountId,s.username,s.hostelCategoryId,s.firstName,s.lastName,s.gender,
s.bedId,addr.streetName,addr.city,st.stateName,addr.postalCode,b.bedName,b.bedLevel,b.bedCost,
b.status as bedStatus,r.id as roomId,r.roomName,r.roomLevel,r.roomType,r.floorId,f.floorName,f.floorLevel,buil.id as buildingId,buil.buildingName,buil.buildingShortName,h.hostelName
FROM vw_staffDetails s  JOIN bed b on(s.bedId=b.id) LEFT JOIN room r on (b.roomId=r.id) LEFT JOIN floor f on (r.floorId=f.id) LEFT JOIN building buil on (f.buildingId=buil.id) LEFT JOIN hostel h on (buil.hostelId=h.id) LEFT JOIN Address addr on (h.addressId=addr.id) LEFT JOIN State st on (st.stateCode=addr.state); 

create
or replace view vw_studentOutHostelDetails as
SELECT s.id as studentOutId,s.visitorName,
s.custId,s.academicYearId,s.visitorRelation,s.outDate,s.outTime,s.exceptedInTime,s.reasonForOuting,s.informParentsOnOut,s.expectedInDate,s.actualInDate,s.studentInOutStatus,p.accountId,p.firstName,p.lastName,p.className,p.rollNumber,p.section,b.bedName,b.bedLevel,b.bedCost,
b.status as bedStatus,r.id as roomId,r.roomName,r.roomLevel,r.roomType,r.floorId,f.floorName,f.floorLevel,buil.id as buildingId,buil.buildingName,buil.buildingShortName,h.hostelName,addr.streetName,addr.city,st.stateName,addr.postalCode
FROM  studentOut s LEFT JOIN vw_studentClassDetails p on (p.accountId = s.accountId) and (p.custId = s.custId) JOIN bed b on(p.bedId=b.id) LEFT JOIN room r on (b.roomId=r.id) LEFT JOIN floor f on (r.floorId=f.id) LEFT JOIN building buil on (f.buildingId=buil.id) LEFT JOIN hostel h on (buil.hostelId=h.id) LEFT JOIN Address addr on (h.addressId=addr.id) LEFT JOIN State st on (st.stateCode=addr.state); 

create
or replace view vw_buildingMenuItemsDetails as
SELECT IFNULL(fmi.id,0) as foodMenuItemsId,fmi.dayId,fmi.custId,fmi.menuItemNames,ft.id as foodTypeId,ft.academicYearId,mmt.id as messMenuTimeId,mmt.startTime,mmt.endTime,mmt.messFoodTypeName,mmt.buildingId,wd.dayName,b.buildingName
FROM foodMenuItems fmi LEFT JOIN foodTypes ft on (fmi.foodTypeId = ft.id) LEFT JOIN messMenuTime mmt on (fmi.messMenuTimeId = mmt.id) LEFT JOIN weekDays wd on (fmi.dayId = wd.id) LEFT JOIN building b on (mmt.buildingId = b.id);

create or replace view vw_classSectionDetails as
select c.id as classId,sc.id as classSectionId,c.className,c.academicYearId,c.custId,c.sortingOrder,c.noOfSections,c.higherStandard,c.extendableClassCapacity,c.admissionsOpen,IFNULL(sc.filledSeats,0) as filledSeats,IFNULL(sc.section,'') as section,sc.sectionCapacity,sc.groupNumber,IFNULL(sc.mediumId,0) as mediumId,IFNULL(sc.syllabusTypeId,0) as syllabusTypeId,
IFNULL(ct.name,'') as classMedium,st.syllabusTypeName,st.syllabusTypeDescription,IFNULL(a.academicYear,'') as academicYear,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  <=> '','',CONCAT(' - ',sc.section))) as classAndSection 
from  studyClass sc LEFT JOIN class c on (sc.classNameClassId = c.id) LEFT JOIN medium ct on (sc.mediumId=ct.id) LEFT JOIN syllabusType st on (sc.syllabusTypeId=st.id) LEFT JOIN academicYear a on (sc.academicYearId = a.id);

-- For Transport VehicleRouteView req:Venkatesh-16/03/2012

/*create 
or replace view vw_newStudentDetails as
SELECT a.id as accountId,a.custId, a.username, a.version,a.admissionNumber, s.status,s.transportMode,s.classNameClassId,IFNULL(s.rollNumber,0) as rollNumber,p.parentEmail,p.firstName,p.lastName, p.middleName,p.mobileNumber,IFNULL(s.id,0) as studentId, 
c.className,c.section,IFNULL(c.id,0) as classSectionId,IFNULL(s.boardingPointId,0) as boardingPointId,IFNULL(s.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,cl.sortingOrder,s.academicYearId
FROM student s  JOIN Account a on (s.accountId=a.id and s.transportMode='T') LEFT JOIN Person p on (a.personId=p.id)  LEFT JOIN  studyClass c on (c.id=s.classSectionId)  LEFT JOIN class cl on (cl.id=s.classNameClassId) ;

create 
or replace view vw_staffStudentVehicleTripDetails as
	SELECT 
    v.id as vehicleId,
    v.custId,
    va.academicYearId,
    d.staffId as driverStaffId,
    d.mobileNumber as driverMobileNumber,
    d.firstName as driverFirstName,
    d.lastName as driverLastName,
    d.middleName as driverMiddleName,
    d.roleId as driverRoleId,
    d.academicYearStatus,
    va.driverId,
    va.helperId,
    v.createdDate,
    v.lastAccessDate,
    v.lastUpdatedDate,
    v.version,
    v.vehicleNumber,
    IFNULL(v.noOfSeats, 0) as vehicleSeatCount,
    v.noOfSeats - (select count(*) vw_newStudentDetails) as availableSeatCount,
    v.vehicleType,
    r.id as routeId,
    r.routeName,
    r.routePointName,
    r.routePointStartTime,
    r.routePointEndTime,
    r.routeEndName,
    rb.id as boardingPointId,
    rb.boardingPointName,
    rb.boardingPointStatTime,
    rb.boardingPointEndTime,
    vst.sortingOrder,
    vst.className,
    vst.section,
    IFNULL(vst.rollNumber, 0) as rollNumber,
    vst.mobileNumber,
    vst.firstName,
    vst.lastName,
    vst.middleName,
    IFNULL(vst.accountId, 0) as accountId,
    vst.transportMode,
    IFNULL(vst.studentId, 0) as studentId,
    vst.parentEmail,va.id as vehicleAcademicId
FROM
    vehicles v
    JOIN vehiclesAcademicDetails va on (v.id=va.vehicleId)
        LEFT JOIN
    RouteWithVehicles rv ON (va.id = rv.vehicleAcademicId)
     LEFT JOIN
     route r ON (r.id = rv.routeId)
     LEFT JOIN
     routeBoardingPoints rb ON (r.id = rb.routeId)
     LEFT JOIN
    vw_staffDetails d ON (va.driverId = d.accountId)
        LEFT JOIN
    vw_newStudentDetails vst ON (v.id = vst.vehicleAcademicDetailsId and rb.id = vst.boardingPointId)
where
    vst.studentId != 0 and vst.status = 'Y';
 */
-- For getting subject wise total marks(with out sub type)
create or replace view vw_examScheduleWiseStudentMarks as 
SELECT es.id as scheduleId,IFNULL(s.id,0) as studId,es.custId,es.classSectionId,es.classSubjectId,es.academicYearId,es.examTypeId,IFNULL(sm.id,0) as marksId,es.subTypeId,IFNULL(es.maxMarks,0) as scheduleMaxMarks,IFNULL(sm.obtainedMarks,0) as obtainedMarks,IFNULL(sm.moderationMarks,0) as moderationMarks,sm.present,et.examType,et.maxMarks,et.id as eid,et.minMarks,st.subTypeName,es.maxMarks as subTypeMaxMarks,ss.name as subjectName,IFNULL(ss.subjectNumber,'') as subjectNumber,ss.sortingOrder,IFNULL(vsm.totalSubjectMarksObtained,0) as totalSubjectMarksObtained,cs.higherStandard,ss.id as subjectId,(IFNULL(sm.obtainedMarks,0)+IFNULL(sm.moderationMarks,0)) as obtainedSubTypeTotalMarks,es.examDate
FROM examSchedules es JOIN student s on (es.classSectionId = s.classSectionId and s.description is null) LEFT JOIN studentMarks sm on (es.id = sm.examScheduleId and s.id = sm.studId) LEFT JOIN examTypes et on (es.examTypeId=et.id)  LEFT JOIN subType st on (st.id = es.subTypeId)  LEFT JOIN class cs on (cs.id=s.classNameClassId) LEFT JOIN studySubject ss on (ss.id = es.classSubjectId) LEFT JOIN  vw_studentSubjectWiseMarksDetails vsm on (s.id=vsm.studId and ss.id=vsm.subjectId and et.id=vsm.examtypeId); 

create or replace view vw_promotionClassesDetails as 
SELECT IFNULL(sc.id,0) as classSectionId,sc.className,sc.custId,sc.academicYearId,sc.section,IFNULL(pc.promoteClassName,'') as promoteClassName,IFNULL(pc.promoteSectionName,'') as promoteSectionName,IFNULL(pc.id,0) as promotionId,c.sortingOrder,IFNULL(ct.name,'') as medium,IFNULL(pc.mediumId,0) as promoteClassMediumId,IFNULL(sc.mediumId,0) as mediumId,IFNULL(cts.name,'') as promoteClassMedium,c.id as classId,IFNULL(pc.promoteProcessCompleted,'N') as promoteProcessCompleted 
FROM studyClass sc JOIN class c on (sc.classNameClassId = c.id) LEFT JOIN promoteClass pc on (sc.id = pc.currentClassSectionId) LEFT JOIN studyClass cs on (pc.promoteClassName=cs.className and pc.promoteSectionName = cs.section and cs.academicYearId = pc.academicYearId) LEFT JOIN medium ct on (sc.mediumId = ct.id)  LEFT JOIN medium cts on (pc.mediumId = cts.id);


/*create 
or replace view vw_studentClassDetails as
SELECT s.id as studId,s.classSectionId,s.status,s.custId,s.rollNumber,s.academicYearId,s.accountId,IFNULL(s.bedId,0) as bedId,s.classNameClassId as classId,TRIM(p.firstName) as firstName,TRIM(p.lastName) as lastName,p.castId,p.subCastId,p.religionId,c.className,c.section,cl.higherStandard,p.dateOfBirth,concat(p.firstName,' ',p.lastName) as fullName,IFNULL(cs.castName,'') as castName,IFNULL(a.admissionNumber,'') as admissionNumber,s.registerNumber,a.accountExpired,s.description as studDiscontinueDesc,p.dateOfJoining,p.fatherName,p.motherName,p.height,p.weight,p.teeth,p.vision,p.oralHygiene,p.motherOccupation,p.parentEmail,p.annualIncome,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.nationality,d.streetName,d.postalCode,p.motherToungId,p.identification1,p.identification2,d.city
FROM student s  JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN Address d on (a.paddressId=d.id) LEFT JOIN  studyClass c on (c.id=s.classSectionId)  LEFT JOIN class cl on (cl.id=s.classNameClassId) LEFT JOIN castSettings cs on (cs.id=p.castId);
*/
create or replace view vw_studentsAbsentiesCount as
	SELECT accountId,academicYearId,IFNULL(count(*),0) as absentiesCount FROM attendance where category='Y' and present='N' group by accountId,academicYearId,custId;

create 
or replace view vw_studentClassAndAttendanceDetails as
SELECT sc.studId,sc.classSectionId,sc.status,sc.custId,sc.rollNumber,sc.academicYearId,sc.accountId,sc.bedId,sc.classId,sc.firstName,sc.lastName,sc.className,sc.section,sc.higherStandard,sc.fullName,sc.castName,sc.admissionNumber,IFNULL(vsa.absentiesCount,0) as absentiesCount,sc.registerNumber,UPPER(sc.fullName) as fullNameUpperCase
FROM vw_studentClassDetails sc LEFT JOIN vw_studentsAbsentiesCount vsa on (vsa.accountId = sc.accountId and vsa.academicYearId = sc.academicYearId);

/* This view contains student account,person and address details */
create 
or replace view vw_studentPersonInfo as
SELECT s.id as studId,s.classSectionId,s.status,s.custId,s.rollNumber,s.academicYearId,s.accountId,s.bedId,s.classNameClassId as classId,
TRIM(p.firstName) as firstName,TRIM(p.lastName) as lastName,p.castId,p.subCastId,p.religionId,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName,
IFNULL(a.admissionNumber,'') as admissionNumber,s.registerNumber,a.accountExpired,s.description as studDiscontinueDesc,s.joinedThroughAdmissions,p.dateOfJoining,
p.fatherName,p.motherName,p.motherOccupation,p.parentEmail,p.annualIncome,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.nationality,p.motherToungId,
d.streetName,d.city,d.postalCode,p.identification1,p.identification2,cs.castName,scs.subCastName,IFNULL(rel.skillTypeName,' ') as religion,
IFNULL(mtg.skillTypeName,'') as motherToung,p.dateOfBirth,p.communityNumber,p.sslcNumber,p.tmrNumber,p.classJoined,p.relievingDate,p.scholarShipInfo,
p.gender,s.categoryId,p.tcAppliedDate,p.tcIssuedDate,d.email as studentEmail,s.transportMode,p.height,p.weight,p.vision,p.teeth,p.oralHygiene,a.imageId,p.placeOfBirth,p.lastSchool,
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
create 
or replace view vw_studentClasswiseAndPersonalInfo as
SELECT vsp.studId,vsc.classSectionId,vsc.status,vsc.custId,vsc.rollNumber,vsc.academicYearId,vsp.accountId,IFNULL(vsp.bedId,0) as bedId,vsc.classId,
vsp.firstName,vsp.lastName,vsp.castId,vsp.subCastId,vsp.religionId,vsc.className,vsc.section,vsc.higherStandard,vsp.fullName,vsp.castName,vsp.admissionNumber,
vsp.registerNumber,vsp.accountExpired,vsp.studDiscontinueDesc,IF(vsp.dateOfJoining IS NULL,'',DATE_FORMAT(vsp.dateOfJoining,'%e/%c/%Y')) as dateOfJoining,
IF(vsp.dateOfBirth IS NULL,'',DATE_FORMAT(vsp.dateOfBirth,'%e/%c/%Y')) as dateOfBirth,vsp.fatherName,vsp.motherName,vsp.motherOccupation,vsp.parentEmail,
vsp.annualIncome,vsp.bloodGroup,vsp.mobileNumber,vsp.phoneNumber,vsp.nationality,vsp.motherToungId,vsp.streetName,vsp.city,vsp.postalCode,vsp.identification1,
vsp.identification2,vsp.subCastName,vsp.religion,vsp.motherToung, vsp.categoryId,vsp.joinedThroughAdmissions ,vsp.transportMode,IFNULL(vsp.vehicleAcademicDetailsId,0) as vehicleAcademicDetailsId,
IFNULL(vsp.boardingPointId,0) as boardingPointId,IFNULL(vsc.classSortingOrder,0) as classSortingOrder,vsp.hostelMode
FROM vw_studentPersonInfo vsp JOIN  vw_studentsClassSectionDetails vsc on (vsp.studId=vsc.studId);

/*
create 
or replace view vw_studentClassDetails as
SELECT s.id as studId,s.classSectionId,s.status,s.custId,s.rollNumber,s.academicYearId,s.accountId,IFNULL(s.bedId,0) as bedId,s.classNameClassId as classId,TRIM(p.firstName) as firstName,TRIM(p.lastName) as lastName,p.castId,p.subCastId,p.religionId,c.className,c.section,cl.higherStandard,concat(p.firstName,' ',p.lastName) as fullName,IFNULL(cs.castName,'') as castName,IFNULL(a.admissionNumber,'') as admissionNumber,s.registerNumber,a.accountExpired,s.description as studDiscontinueDesc
FROM student s  JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN Address d on (a.paddressId=d.id) LEFT JOIN  studyClass c on (c.id=s.classSectionId)  LEFT JOIN class cl on (cl.id=s.classNameClassId) LEFT JOIN castSettings cs on (cs.id=p.castId);
*/


create 
or replace view vw_staffPayrollDetails as
SELECT a.id accountId,a.accountExpired,a.custId, a.username,  a.version, a.sharePrivacy, a.userOnlineNow, s.id staffId, s.description,s.academicYearId,s.qualification1,s.qualification2, s.createdDate, s.lastAccessDate, s.status, s.supervisorId,IFNULL(s.bedId,0) as bedId,s.hostelCategoryId,s.staffType,IFNULL(s.organizationTypeId,0) as organizationTypeId, s.staffPayrollMonth ,s.staffPayrollYear, p.licenseNumber,p.licenseExpDate,p.experience,p.dateofJoining,p.firstName, p.lastName,p.middleName,p.dateOfBirth,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.maritalStatus,p.summary,p.gender,p.familyDoctor,p.prefferedHospital,IFNULL(p.religionId,0) as religionId,IFNUll(p.castId,0) as castId,IFNULL(p.subCastId,0) as subCastId,IFNULL(p.annualIncome,0) as annualIncome,p.designation,p.gpfNumber,p.officeNumber,p.nationality,p.panNumber,IFNULL(p.motherToungId,0) as motherToungId,d.addressLine1,d.addressLine2,d.city,d.state,d.postalCode,d.email, a.lastUpdatedDate,
IFNULL(ur.roleId,0) as roleId,ur.roleName,ur.roleDescription,ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail ,ps.id as payrollSettingsId,ps.payrollName,ps.payrollDescription,ps.payrollTypesId, ps.month as payrollMonth,ps.year as payrollYear,ps.percentage,ps.lastUpdatedDate as pslastUpdatedDate,ps.payrollType
FROM staff s LEFT JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id) LEFT JOIN  vw_userRoles ur on (a.id=ur.accountId) LEFT JOIN UserImage ui on (ui.id=a.imageId) LEFT JOIN payrollSettings ps on (ps.staffId=s.id);

/*Time Table*/
create 
or replace view vw_classWisePeriodsCountDetails as
SELECT tt.id as timeTableId,tt.dayId,wd.dayName,tt.periodName,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  <=> '','',CONCAT(' - ',sc.section))) as classAndSection,count(*) as periodsCount,tt.classSectionId,tt.custId,tt.academicYearId,c.className,c.sortingOrder,sc.section,IFNULL(sc.classNameClassId,0) as classId
FROM timeTable tt LEFT JOIN  weekDays wd on (tt.dayId=wd.id) LEFT JOIN studyClass sc on (tt.classSectionId = sc.id) LEFT JOIN class c on (sc.classNameClassId = c.id) group by tt.classSectionId,tt.dayId;

create
or replace view vw_classSubjectsSettings as
SELECT scs.studyClassId as classSectionId,scs.academicYearId,scs.className,scs.section,scs.subjectName,scs.custId,scs.subjectId,scs.classId,scs.subjectNumber,IFNULL(scs.sortingOrder,0) as sortingOrder, IFNULL(css.periodsPerWeek,0) as periodsPerWeek,IFNULL(css.subjectPriority,0) as subjectPriority,IFNULL(css.continuousPeriodsCount,0) as continuousPeriodsCount,IFNULL(css.id,0) as classSubjectSettingId,scs.classSortingOrder,scs.noOfSections 
FROM vw_studyClassSubjectDetails scs LEFT JOIN classSubjectsSettings css on (scs.studyClassId = css.classSectionId and scs.subjectId = css.studySubjectId);

create
or replace view vw_timeTableDetails as
SELECT tt.id as timeTableId,tt.classSectionId as classSectionId,IFNULL(tt.subjectId,0) as subjectId,tt.dayId,tt.periodName,tt.periodType,tt.custId,tt.academicYearId,IFNULL(stp.teacherId,0) as teacherId,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  <=> '','',CONCAT(' - ',sc.section))) as classAndSection,ss.name as subjectName,wd.dayName,vsd.firstName, vsd.lastName,sc.className,sc.section,tt.combinedPeriod,sc.classNameClassId as classId,CONCAT(IF(vsd.firstName IS NULL,'',vsd.firstName), IF(vsd.lastName IS NULL || vsd.lastName  <=> '','',CONCAT(', ',vsd.lastName))) as staffFullName,tt.prioritiesBasedPeriod  
FROM timeTable tt LEFT JOIN staffTimeTablePeriods stp on (tt.id = stp.timeTableId) LEFT JOIN studyClass sc on (tt.classSectionId = sc.id) LEFT JOIN studySubject ss on (tt.subjectId = ss.id) LEFT JOIN weekDays wd on (tt.dayId = wd.id) LEFT JOIN vw_staffDetails vsd on (vsd.staffId = stp.teacherId);

/*
create
or replace view vw_combinedClassSubjectDetails as
SELECT  ccs.id as combinedClassDetailId,ccs.classSectionId,ccs.studySubjectId,ccs.staffId,ccs.custId,sc.className,sc.section,sc.academicYearId,sc.classNameClassId as classId,ss.name as subjectName,vsd.firstName,vsd.lastName,vsd.status,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  <=> '','',CONCAT(' - ',sc.section))) as classAndSection
FROM combinedClassSubjects ccs LEFT JOIN studyClass sc on (ccs.classSectionId = sc.id) LEFT JOIN studySubject ss on (ccs.studySubjectId = ss.id) LEFT JOIN vw_staffDetails vsd on (ccs.staffId = vsd.staffId);

create
or replace view vw_combinedClassSubjectsGroupByClassIdSubIdStaffId as
SELECT  ccs.combinedClassDetailId,ccs.classSectionId,ccs.studySubjectId,ccs.staffId,ccs.custId,ccs.className,ccs.section,ccs.academicYearId,ccs.classId,ccs.subjectName,ccs.firstName,ccs.lastName,ccs.status,ccs.classAndSection,group_concat(ccs.classAndSection ORDER BY ccs.section) as combinedClassNames,group_concat(ccs.classSectionId) as combinedClassSectionIds,group_concat(ccs.combinedClassDetailId) as combinedClassDetailsIds
FROM vw_combinedClassSubjectDetails ccs group by classId,studySubjectId,staffId;


create
or replace view vw_combinedClassSubjectDetails as
SELECT  ccs.id as combinedClassDetailsId,group_concat(DISTINCT ccsec.classSectionId) as combinedClassSectionIds,ccs.studySubjectId,group_concat(DISTINCT cst.teacherId) as combinedClassTeacherIds,ccs.custId,ccs.academicYearId,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  <=> '','',CONCAT(' - ',sc.section))) as classAndSection
FROM combinedClassSubjects ccs LEFT JOIN combinedClassSubjectTeachers cst on (cst.combinedClassSubjectId = ccs.id) LEFT JOIN combinedClassSections ccsec on (ccsec.combinedClassSubjectId = ccs.id) LEFT JOIN studyClass sc on (sc.id=ccsec=classSectionId) LEFT JOIN vw_staffDetails vsd on (cst.teacherId = vsd.staffId) group by ccs.id; 
*/

create or replace view vw_subjectWiseAssignedPeriodsCount as
SELECT tt.classSectionId,tt.subjectId,tt.academicYearId,tt.custId,count(*) as assignedPeriodsCount,tt.id as timeTableId
FROM timeTable tt where tt.subjectId is not null group by tt.classSectionId,tt.subjectId;

create or replace view vw_subjectWisePeriodsDetails as
SELECT IFNULL(sap.timeTableId,0) as timeTableId,css.classSectionId,css.subjectId,css.custId,css.academicYearId,css.className,css.section,css.subjectName,css.periodsPerWeek,css.continuousPeriodsCount,css.subjectPriority,css.classSortingOrder,css. sortingOrder as subjectSortingOrder,IFNULL(sap.assignedPeriodsCount,0) as assignedPeriodsCount,CONCAT(IF(css.className IS NULL,'',css.className), IF(css.section IS NULL || css.section  <=> '','',CONCAT(' - ',css.section))) as classAndSection,css.classId
FROM vw_classSubjectsSettings css LEFT JOIN vw_subjectWiseAssignedPeriodsCount sap on (css.classSectionId = sap.classSectionId and css.subjectId = sap.subjectId);


create
or replace view vw_studentsTCDetails as
SELECT vsp.studId,vsc.classSectionId,vsc.status,vsp.custId,vsc.rollNumber,vsp.academicYearId,vsp.accountId,
IFNULL(vsp.bedId,0) as bedId,vsp.classId,vsp.firstName,vsp.lastName,vsp.castId,vsp.subCastId,vsp.religionId,
vsc.className,vsc.section,vsc.higherStandard,vsp.fullName,IFNULL(vsp.castName,'') as castName,vsp.admissionNumber,IFNULL(vsp.registerNumber,'') as registerNumber,
vsp.accountExpired,vsp.studDiscontinueDesc,vsp.dateOfJoining,vsp.dateOfBirth,IFNULL(vsp.fatherName,'') as fatherName,IFNULL(vsp.motherName,'') as motherName,vsp.motherOccupation,
vsp.parentEmail,vsp.annualIncome,vsp.bloodGroup,vsp.mobileNumber,vsp.phoneNumber,IFNULL(vsp.nationality,'') as nationality,vsp.motherToungId,vsp.streetName,vsp.city,
vsp.postalCode,IFNULL(vsp.identification1,'') as identification1,IFNULL(vsp.identification2,'') as identification2,vsp.subCastName,IFNULL(vsp.religion,'') as religion,vsp.motherToung,IFNULL(cs.organization,'') as organizationName,
custAddr.addressLine1 as custAddressLine1,custAddr.streetName as custStreetName,custAddr.city as custCity,custAddr.postalCode as custPostalCode,cs.educationalDistrict,cs.revenueDistrict,
IFNULL(cs.schoolCode,'') as schoolCode,cs.recognisedBy,cs.boardOfEducation,IFNULL(cs.affiliationNumber,'') as affiliationNumber,IFNULL(vsp.communityNumber,'') as communityNumber,IFNULL(vsp.sslcNumber,'') as sslcNumber,IFNULL(vsp.tmrNumber,'') as tmrNumber,
vsp.classJoined,vsp.relievingDate,IFNULL(vsp.scholarShipInfo,'') as scholarShipInfo,IFNULL(trnc.serialNumber,0) as serialNumber,IFNULL(acy.academicYear,'') as academicYear,
IFNULL(vsc.educationType,'') as educationType,vsp.gender,IFNULL(vsc.medium,'') as mediumOfStudy,vsp.tcAppliedDate,vsp.tcIssuedDate,IFNULL(bs.tcBookNumber,0) as tcBookNumber,
IFNULL(vsp.height,0) as height,IFNULL(vsp.weight,0) as weight,IfNULL(vsp.vision,'') as vision,IFNULL(vsp.oralHygiene,'') as oralHygiene,IFNULL(vsc.syllabusTypeId,0) as syllabusTypeId,IFNULL(stype.syllabusTypeName,'') as syllabusTypeName,
acy.startDate,acy.endDate,bs.type,vsp.placeOfBirth,vsp.lastSchool
FROM vw_studentPersonInfo vsp JOIN  vw_studentsClassSectionDetails vsc on (vsp.studId=vsc.studId) JOIN customer cs on (cs.id = vsc.custId) LEFT JOIN syllabusType stype on (stype.id=vsc.syllabusTypeId)  
LEFT JOIN Address custAddr on (custAddr.id=cs.addressId) LEFT JOIN transferCertificate trnc on(vsp.accountId=trnc.accountId)
LEFT JOIN tcBookSettings bs on (bs.id=trnc.bookSettingId) LEFT JOIN academicYear acy on(acy.id=vsc.academicYearId);
 
 
 create or replace view vw_studentsScoreCardProfileDetails as 
	SELECT s.id as studId,
		IFNULL(s.rollNumber,0) as rollNumber,
		CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName,
		IFNULL(c.className,'') as className,
		IFNULL(c.section,'') as section,
		p.dateOfBirth,
		IFNULL(a.admissionNumber,'') as admissionNumber,
		IFNULL(s.registerNumber,'') as registerNumber,
		IFNULL(p.fatherName,'') as fatherName,
		IFNULL(p.motherName,'') as motherName,
		IFNULL(d.streetName,'') as streetName,
		IFNULL(d.city,'') as city,
		IFNULL(d.postalCode,'') as postalCode,
		IFNULL(p.studentEmail,'') as studentEmail,
		IFNULL(p.mobileNumber,'') as mobileNumber,
		IFNULL(p.phoneNumber,'') as phoneNumber,
		s.custId,
		s.academicYearId,
		IFNULL(ac.academicYear,'') as academicYear,
		s.classSectionId,
		p.height,
		p.weight,
		IFNULL(p.vision,'') as vision,
		IFNULL(p.teeth,'') as teeth,
		IFNULL(p.oralHygiene,'') as oralHygiene,
		IFNULL(UPPER(p.bloodGroup),'') as bloodGroup,
		IFNULL(cus.schoolCode,'') as schoolCode,
		IFNULL(cus.organization,'') as organizationName,
		d.addressLine1 as custAddressLine1,
		d.streetName as custStreetName,
		d.city as custCity,
		d.postalCode as custPostalCode,
		ui.name imageName,
		ui.path imagePath,
		ui.thumbNail,
		s.description as studDiscontinueDesc,
		ac.nextAcademicStartDate as schoolReOpenDate,
		IFNULL(p.parentEmail,'') as parentEmail,
		a.id as accountId

FROM class cl 
LEFT JOIN studyClass c on (cl.id=c.classNameClassId and cl.custId=c.custId and cl.academicYearId=c.academicYearId)
LEFT JOIN student s on (c.id=s.classSectionId and c.classNameClassId=s.classNameClassId and s.custId=c.custId and s.academicYearId=c.academicYearId) 
LEFT JOIN Account a on (a.custId=s.custId and s.accountId=a.id)
LEFT JOIN Person p on (a.personId=p.id) 
LEFT JOIN Address d on (a.paddressId=d.id) 
LEFT JOIN UserImage ui on (ui.id=s.imageId) 
LEFT JOIN academicYear ac on (ac.custId=a.custId and ac.id=s.academicYearId) 
LEFT JOIN customer cus on (cus.addressId = d.id and cus.id = s.custId); 
 
 
/*create or replace view vw_studentsScoreCardProfileDetails as 
SELECT IFNULL(vsp.rollNumber,0) as rollNumber,IFNULL(vsp.fullName,'') as fullName,IFNULL(vsc.className,'') as className,IFNULL(vsc.section,'') as section,vsp.dateOfBirth,IFNULL(vsp.admissionNumber,'') as admissionNumber,IFNULL(vsp.registerNumber,'') as registerNumber,IFNULL(vsp.fatherName,'') as fatherName,IFNULL(vsp.motherName,'') as motherName,IFNULL(vsp.streetName,'') as streetName,IFNULL(vsp.city,'') as city,
IFNULL(vsp.postalCode,'') as postalCode,IFNULL(vsp.studentEmail,'') as studentEmail,IFNULL(vsp.mobileNumber,'') as mobileNumber,IFNULL(vsp.phoneNumber,'') as phoneNumber,vsc.custId,vsc.academicYearId,vsp.studId,IFNULL(vsc.academicYear,'') as academicYear,
vsc.classSectionId,vsp.height,vsp.weight,IFNULL(vsp.vision,'') as vision,IFNULL(vsp.teeth,'') as teeth,IFNULL(vsp.oralHygiene,'') as oralHygiene,IFNULL(UPPER(vsp.bloodGroup),'') as bloodGroup,
IFNULL(cs.schoolCode,'') as schoolCode,IFNULL(cs.organization,'') as organizationName,custAddr.addressLine1 as custAddressLine1,custAddr.streetName as custStreetName,custAddr.city as custCity,custAddr.postalCode as custPostalCode,
ui.name imageName,ui.path imagePath,ui.thumbNail,vsp.studDiscontinueDesc,vsc.nextAcademicStartDate as schoolReOpenDate,IFNULL(vsp.parentEmail,'') as parentEmail
FROM vw_studentPersonInfo vsp JOIN  vw_studentsClassSectionDetails vsc on (vsp.studId=vsc.studId) JOIN customer cs on (cs.id = vsc.custId)
LEFT JOIN Address custAddr on (custAddr.id=cs.addressId) LEFT JOIN UserImage ui on (ui.id=vsp.imageId); */



-- For validating student fee payment details. If fee not configured for students categories this view will return nothing  
create
or replace view vw_studentFeePaidAndNotPaidDetails as
SELECT s.studId,s.firstName,s.lastName,s.admissionNumber,s.custId,s.academicYearId,s.classId,s.rollNumber,s.accountId,s.status as studentStatus,s.studDiscontinueDesc,s.joinedThroughAdmissions,
ifnull(f.feeId,0) as feeId,IFNULL(f.feeAmount,0) as feeAmount,f.feeTypeId,f.schoolTermId,f.categoryId,IFNULL(sfp.paymentStatus,'N') as paymentStatus,
IFNULL(sp.paidAmount,0) as paidAmount,IFNULL(sp.discountAmount,0) as discountAmount,f.categoryName,if(((s.transportMode='O' and f.feeType='Transport Fee') or (s.transportMode='p' and f.feeType='Transport Fee')),'N',f.feeType) as feeType,f.termName,
f.applToNewStuds,sfp.id as paymentId,s.registerNumber,f.dueDate,s.className,s.section,s.classSectionId
FROM vw_studentClasswiseAndPersonalInfo s left join vw_classFeeDetails f on (s.classId=f.classId and s.academicYearId = f.academicYearId) and IF(s.joinedThroughAdmissions='N',(f.categoryId=s.categoryId and f.applToNewStuds = 'N'),f.categoryId=s.categoryId) 
LEFT JOIN studentFeePaidDetails sfp on (s.studId=sfp.studentId and f.feeid=sfp.feeId and sfp.deleteStatus='Y')
LEFT JOIN studentPayment sp on (sp.id = sfp.studentPaymentId); 

create
or replace view vw_combinedClassSubjectDetails as
SELECT  ccs.id as combinedClassDetailId,csec.classSectionId,ccs.studySubjectId,IFNULL(ccteacher.teacherId,0) as teacherId,ccs.custId,sc.className,sc.section,ccs.academicYearId,sc.classNameClassId as classId,ss.name as subjectName,vsd.firstName,vsd.lastName,vsd.status,CONCAT(IF(sc.className IS NULL,'',sc.className), IF(sc.section IS NULL || sc.section  <=> '','',CONCAT(' - ',sc.section))) as classAndSection
FROM combinedClassSubjects ccs LEFT JOIN combinedClassSections csec on (ccs.id = csec.combinedClassSubjectId) LEFT JOIN combinedClassSubjectTeachers ccteacher on (ccteacher.combinedClassSubjectId = ccs.id) LEFT JOIN studyClass sc on (csec.classSectionId = sc.id) LEFT JOIN studySubject ss on (ccs.studySubjectId = ss.id) LEFT JOIN vw_staffDetails vsd on (ccteacher.teacherId = vsd.staffId);

CREATE OR REPLACE VIEW vw_StudentDailyAttendance AS 
	select 
	TRIM(p.firstName) as firstName,TRIM(p.lastName) as lastName,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as StudentName,
	s.rollNumber AS rollNumber,
	s.classSectionId AS classSectionId,
	s.id as studentId,
    a.id AS accountId,
    s.custId AS custId,
    p.parentEmail AS parentEmail,
    p.fatherName AS fatherName,
    c.className,c.section,
    trim(trailing ' - ' from concat(ifnull(c.ClassName, ''), ' - ', ifnull(c.Section, ''))) AS classNameAndSection,
    ac.id AS academicYearId,
    sda.present AS present,
    sda.attendanceDate AS attendanceDate,
    extract(month from sda.attendanceDate) AS month,
    (select monthname(sda.attendanceDate) AS monthName) AS monthName,    
    sda.id as studentAttendanceId, a.admissionNumber
    FROM student s  JOIN Account a on (s.accountId=a.id) JOIN Person p on (a.personId=p.id) LEFT JOIN Address d on (a.paddressId=d.id) 
    JOIN  studyClass c on (c.id=s.classSectionId) JOIN class cl on (cl.id=s.classNameClassId) LEFT JOIN academicYear ac on (ac.id=s.academicYearId) LEFT JOIN medium ct on (ct.id = c.mediumId)
    join studentDailyAttendance sda ON (s.id = sda.studentId);

CREATE or REPLACE VIEW vw_StudentMonthlyAttendance AS select sma.id AS attendanceId,concat(vws.firstName,' ',ifnull(vws.lastName,'')) AS StudentName,vws.rollNumber AS rollNumber,vws.classSectionId AS classSectionId,vws.studentId AS studentId,vws.accountId AS accountId,vws.custId AS custId,vws.academicYearId AS academicYearId,vws.className AS className,vws.section AS Section,vws.parentEmail AS parentEmail,vws.fatherName AS fatherName,vws.classNameAndSection AS classNameAndSection,sma.month AS month,sma.monthName AS monthName,sma.totalWorkingDays AS totalWorkingDays,sma.noOfPresentDays AS noOfPresentDays from (vw_studentDetails vws join studentMonthlyAttendance sma on((vws.studentId = sma.studentId)));

CREATE OR REPLACE 
VIEW vw_govtschoolsreport AS
    select 
        u.custId AS custId,
        u.username AS username,
        u.password AS password,
        ur.roleId AS roleId,
        r.name AS roleName,
        c.custEmail AS custEmail,
        c.organization AS organization,
        c.orgnizationTypeId AS orgnizationTypeId,
        c.organizationSubTypeId AS organizationSubTypeId,
        a.id AS addressId,
        a.stateId AS stateId,
        a.districtId AS districtId,
        a.mandalId AS mandalId,
        a.villageId AS villageId,
        s.stateCode AS stateCode,
        s.stateName AS stateName,
        d.districtCode AS districtCode,
        d.districtName AS districtName,
        m.mandalCode AS mandalCode,
        m.mandalName AS mandalName,
        v.villageCode AS villageCode,
        v.villageName AS VillageName,
        p.firstName AS firstName,
        p.lastName AS LastName
    from
        (((((((((customer c
        left join Account u ON ((u.custId = c.id)))
        left join UserRoles ur ON ((ur.userId = u.id)))
        left join Role r ON ((r.id = ur.roleId)))
        left join Address a ON ((u.paddressId = a.id)))
        left join Person p ON ((u.personId = p.id)))
        left join State s ON ((s.id = a.stateId)))
        left join district d ON ((d.id = a.districtId)))
        left join mandal m ON ((m.id = a.mandalId)))
        left join village v ON ((v.id = a.villageId)))
    where
        (a.stateId > 0);
        
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
    from staff s
	    join Account a ON s.accountId = a.id
	    join Person p ON a.personId = p.id
	    left join Address d ON a.paddressId = d.id
	    left join Address td ON a.taddressId = td.id
	    left join vw_userRoles ur ON a.id = ur.accountId
	    left join academicYear ay ON s.academicYearId = ay.id
	    left join staffMonthlyAttendance sma ON (a.id = sma.accountId) and (ay.id = sma.academicYearId);
    
CREATE or REPLACE view vw_StaffDailyAttendance AS select 
    concat(vws.firstName, ' ', ifnull(vws.lastName,'')) AS staffName,
    vws.accountId AS accountId,
    vws.custId AS custId,
     vws.roleDescription AS roleName, 
      vws.roleId AS roleId,
    vws.academicYearId AS academicYearId,
    sda.present AS present,
    sda.leaveRequest AS leaveRequest,
    sda.leaveType AS leaveType,
    sda.attendanceDate AS attendanceDate,
    ifnull(sda.inTime,0) AS inTime,
    ifnull(sda.outTime,0) AS outTime,
    ifnull(sda.workingHours,0) AS workingHours,
    extract(month from sda.attendanceDate) AS month,
    (select monthname(sda.attendanceDate) AS monthName) AS monthName
from
    (vw_staffDetails vws
    join staffDailyAttendance sda ON ((vws.accountId = sda.accountId)));
    
    
CREATE OR REPLACE SQL SECURITY DEFINER VIEW vw_customeraddress AS select c.id AS custId,c.custEmail AS custEmail,c.organization AS organization,c.orgnizationTypeId AS orgnizationTypeId,c.organizationSubTypeId AS organizationSubTypeId,ad.districtId AS districtId,ad.mandalId AS mandalId,ad.stateId AS stateId,ad.villageId AS villageId,s.stateCode AS stateCode,s.stateName AS stateName,d.districtCode AS districtCode,d.districtName AS districtName,m.mandalCode AS mandalCode,m.mandalName AS mandalName,v.villageCode AS villageCode,v.villageName AS VillageName from ((((((((customer c left join Account u on((u.custId = c.id))) left join UserRoles ur on((ur.userId = u.id))) left join Role r on((r.id = ur.roleId))) left join Address ad on((u.paddressId = ad.id))) left join State s on((s.id = ad.stateId))) left join district d on((d.id = ad.districtId))) left join mandal m on((m.id = ad.mandalId))) left join village v on((v.id = ad.villageId))) group by c.Id;


CREATE OR REPLACE SQL SECURITY DEFINER VIEW vw_studentMessAccessedDetails AS select 
    sma.id AS id,
    sma.accessedDate AS accessedDate,
    sma.mealType AS mealType,
    sma.present AS present,
    sma.studentId AS studentId,
    s.custId AS custId,
    s.academicYearId AS academicYearId,
    vca.districtId AS districtId,
    vca.districtName AS districtName,
    vca.districtCode AS districtCode,
    vca.mandalId AS MandalId,
    vca.mandalName AS MandalName,
    vca.mandalCode AS MandalCode,
    vca.stateId AS StateId,
    vca.stateName AS stateName,
    vca.stateCode AS StateCode,
    vca.villageId AS VillageId,
    vca.VillageName AS villageName,
    vca.villageCode AS VillageCode
from
    ((studentMessAccess sma
    left join student s ON ((sma.studentId = s.id)))
    left join vw_customeraddress vca ON ((vca.custId = s.custId)))
where
    (vca.stateId > 0);

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
    IFNULL(DATEDIFF(v.fitnessExpiryDate,curdate()),31) as fitnessDays,
    v.permitExpiryDate AS permitExpiryDate,
    IFNULL(DATEDIFF( v.permitExpiryDate,curdate()),31) as permitDays,
    v.pollutionExpiryDate AS pollutionExpiryDate,
    IFNULL(DATEDIFF(v.pollutionExpiryDate,curdate()),31) as pollutionDays,
    v.roadTaxNextPaymentDate AS roadTaxNextPaymentDate,
    IFNULL(DATEDIFF(v.roadTaxNextPaymentDate,curdate()),31) as roadTaxDays,
    v.insuranceExpiredDate AS insuranceExpiredDate,
    IFNULL(DATEDIFF(v.insuranceExpiredDate,curdate()),31) as insuranceDays,
    va.id as vehicleAcademicDetailId  
from
    vehicles v LEFT JOIN vehiclesAcademicDetails va on (va.vehicleId= v.id);


 CREATE or REPLACE VIEW vw_studentDetailsByDays AS 
 select distinct
   vs.accountId AS accountId,mobileNumber,parentEmail,CONCAT(IF(vs.firstName IS NULL,'',vs.firstName), IF(vs.lastName IS NULL || vs.lastName  <=> '','',CONCAT(', ',vs.lastName)))  as fullName,custId,academicYearId  
from
    vw_studentDetails vs  where  vs.status='Y' and (month(curDate())-month(vs.dateOfBirth))=0 and (day(curDate())-day(vs.dateOfBirth))=0; 
    
    
CREATE OR REPLACE VIEW vw_classAssignmentDetails as select 
    c.id as assignmentId,
    date(c.assignmentDate) as assignmentDate,
      (case
        when (isnull(c.description) or (c.description = '')) then 'N/A'
        else c.description
    end) AS assignmentDescription, 
    c.classSectionId,
    IFNULL(c.subjectId,0) as subjectId,
    s.subjectName,
    concat(s.className,' ',s.section) as classAndSection,
    s.academicYearId,
    s.custId,
    s.className,
    s.classId,
    concat(p.firstName,' ',p.lastName) as createdBy
from
    classAssignment c
        left join
    vw_classSubjectsSettings s ON s.subjectId = c.subjectId and s.classSectionId = c.classSectionId
    left join Account a on a.id=c.createdById left join Person p on a.personId=p.id;
    
  CREATE OR REPLACE VIEW vw_StudentClassAssignment as  select 
    vws.fullName AS StudentName,
    vws.rollNumber AS rollNumber,
    vws.classSectionId AS classSectionId,
    vws.studId AS studentId,
    vws.accountId AS accountId,
    vws.custId AS custId,
    vws.academicYearId AS academicYearId,
    sda.assignmentDone AS assignmentDone,
    sda.assignmentDate AS assignmentDate,
    vs.subjectName as subjectName,
    sda.assignmentId as assignmentId,
    sda.id as studentAssignmentId,
    vs.classAndSection,
    vws.mobileNumber,
    vws.status
from
    (vw_studentPersonInfo vws
    join studentClassAssignment sda ON (vws.studId = sda.studentId)
    left join vw_classAssignmentDetails vs on vs.assignmentId=sda.assignmentId);

  CREATE or REPLACE VIEW vw_studentFineFeeDetails AS 
 select distinct 
   vs.accountId AS accountId,studentId,classNameClassId,mobileNumber,admissionNumber,academicYear,streetName,city,postalCode,CONCAT(IF(vs.className IS NULL,'',vs.className), IF(vs.section IS NULL || vs.section  <=> '','',CONCAT(' - ',vs.section)))  as classAndSection,rollNumber,CONCAT(IF(vs.firstName IS NULL,'',vs.firstName), IF(vs.lastName IS NULL || vs.lastName  <=> '','',CONCAT(' ',vs.lastName)))  as studentFullName, ff.paymentDate,ff.description,ff.invoiceNumber,ff.fineFeeAmount,ff.custId,ff.academicYearId,vs.parentEmail,vs.fatherName,vs.status
from
    vw_studentDetails vs JOIN fineFee ff on (studentId=ff.studId);   

   
   /*Hall Ticket*/ 
create
or replace view vw_studentsHallTicketDetails as
SELECT s.id as studId,s.status,s.accountId,s.rollNumber,vcexm.academicYearId,vcexm.custId,vcexm.classId,vcexm.classSectionId,vcexm.name,vcexm.classSubjectId,vcexm.className,c.organization,
vcexm.section,vcexm.examDate,vcexm.startDate,vcexm.endDate,vcexm.startTime,vcexm.endTime,vcexm.examType,vcexm.subTypeName,acc.admissionNumber,pe.firstName,pe.dateOfBirth,pe.lastName,ad.addressLine1 as custAddressLine1,
ad.city as custCity,ad.postalCode as custPostalCode,ad.streetName as custStreetName,ui.name imageName,ui.path imagePath,ui.thumbNail
from vw_classExamDetails vcexm JOIN student s  on (vcexm.classSectionId=s.classSectionId) LEFT JOIN customer c on (c.id=s.custId) LEFT JOIN Account acc on (acc.id =s.accountId) 
LEFT JOIN Person pe on (pe.id =acc.personId) LEFT JOIN Address ad on (ad.id =acc.paddressId) LEFT JOIN UserImage ui on (ui.id=acc.imageId);

/*For updating students roll numbers for a class. */
DELIMITER //
DROP PROCEDURE IF EXISTS UpdateStudentRollNOs;
CREATE procedure UpdateStudentRollNOs(IN sectionId bigint(20)) 
BEGIN    
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE inc INTEGER DEFAULT 0;
   DECLARE localID INTEGER DEFAULT 0;
       DECLARE my_cursor CURSOR FOR SELECT studId FROM vw_studentClassDetails WHERE classSectionId = sectionId and status='Y' order by firstName;   
       DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor;  
               allStudents: LOOP 
                       FETCH my_cursor INTO localID;
                       IF record_not_found THEN 
                       LEAVE allStudents; 
                       END IF;
                       SET inc = inc+1;
                       UPDATE student SET rollNumber=inc WHERE id =localID;
               END LOOP allStudents;    
   CLOSE my_cursor;
   END //    
DELIMITER ;

/*For updating student position based on subject marks in studentMarks table. */

DELIMITER //
DROP PROCEDURE IF EXISTS calculateStudentsSubjectPosition;    
CREATE  PROCEDURE calculateStudentsSubjectPosition(IN $classSectionId bigint(20),IN $examTypeId bigint(20))
BEGIN    
    DECLARE record_not_found INTEGER DEFAULT 0; 
    DECLARE _studId,_examScheduleId,_blnChange,_position,_blnChange1,_rankPosition INTEGER DEFAULT 0;
    DECLARE _obtainedMarks DOUBLE DEFAULT 0;  
    
    BEGIN
     DECLARE done INT DEFAULT FALSE;
    DECLARE my_cursor CURSOR FOR SELECT marks.studId,marks.examScheduleId,marks.studObtainedMarks,if(@schId = marks.examScheduleId,@bln:=0,@bln:=1) blnChange,
        IF((@marks=(@marks:=marks.studObtainedMarks) XOR @schId = (@schId:=marks.examScheduleId)),@auto:=@auto+1,IF(@bln = 1,@auto:=1,@auto)) position
        FROM (SELECT studId,examScheduleId, (obtainedMarks+moderationMarks) as studObtainedMarks
        FROM studentMarks sm  JOIN examSchedules es on(es.id=sm.examScheduleId) WHERE es.classSectionId=$classSectionId and es.examTypeId=$examTypeId ORDER BY sm.examScheduleId,sm.obtainedMarks DESC

       ) AS marks, (SELECT @auto:=1, @marks:=0,@schId:=0) AS fields;   
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN my_cursor;  
        allStudents: LOOP 
            FETCH my_cursor INTO _studId,_examScheduleId,_obtainedMarks,_blnChange,_position;
            IF done THEN 
                LEAVE allStudents;
            END IF;
            UPDATE studentMarks SET subjectPosition=_position WHERE examScheduleId =_examScheduleId AND studId = _studId;
        END LOOP allStudents;    
    CLOSE my_cursor;
    END;
    BEGIN
     DECLARE done INT DEFAULT FALSE;
     DECLARE previous_rank INTEGER DEFAULT 0;
     DECLARE previous_Position INTEGER DEFAULT 0;
     DECLARE _studId,_examScheduleId,_rankPosition INTEGER DEFAULT 0; 
    DECLARE my_cursor CURSOR FOR SELECT marks1.studId,marks1.examScheduleId,marks1.totalMarks
        FROM (SELECT studId,examScheduleId, SUM(obtainedMarks+moderationMarks) as totalMarks
        FROM studentMarks sms  JOIN  examSchedules ess on(ess.id=sms.examScheduleId) WHERE ess.classSectionId=$classSectionId and ess.examTypeId=$examTypeId GROUP BY sms.studId ORDER BY SUM(sms.obtainedMarks+sms.moderationMarks) DESC
       ) AS marks1;  
      
     DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
     OPEN my_cursor;  
        allStudents: LOOP 
            FETCH my_cursor INTO _studId,_examScheduleId,_rankPosition;
            IF done THEN 
                LEAVE allStudents;
            END IF;
                        
            IF(previous_rank =_rankPosition) THEN
             set previous_Position := previous_Position;
            ELSE
            set previous_Position := previous_Position + 1;
            END IF;
            set previous_rank := _rankPosition;
            UPDATE studentMarks SET rankPosition=previous_Position WHERE examScheduleId in(select ess.id from examSchedules as ess where ess.examTypeId=$examTypeId) AND studId = _studId;
        END LOOP allStudents;
    CLOSE my_cursor;
   END;
 END   //  
DELIMITER ;
  
create
or replace view vw_studentClassFeePaymentDetails as
SELECT 
	payment_SequanceId() AS id,
    IFNULL(cf.studentId, 0) as studentId,cf.feeId,IFNULL(cf.feeSettingId, 0) as feeSettingId,cf.settingName,cf.settingType,cf.schoolTermId,cf.feeTypeId,
    IFNULL(cf.classId, 0) as classId,cf.academicYearId,cf.custId,cf.categoryId,IFNULL(cf.feeAmount, 0) as feeAmount,cf.fromdate,cf.toDate,cf.fromMonthName,
    cf.toMonthName,cf.termName,cf.dueDate,cf.categoryName,cf.rollNumber,cf.status,cf.description,a.username,p.firstName,p.lastName,p.middleName,p.mobileNumber,
    p.phoneNumber,p.parentEmail,cf.feeType,cf.classSectionId,cf.routeBoardingPointId,
    IFNULL(sfp.id, 0) paymentId,IFNULL(sfp.studentPaymentId, 0) studentPaymentId,sfp.lastUpdatedDate,IFNULL(sfp.paymentAmount, 0) as paymentAmount,
    IFNULL(sfp.paymentStatus, 'N') as paymentStatus,IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sfp.deleteStatus, 'Y') as deleteStatus
FROM vw_studentClassFees cf LEFT JOIN studentFeePaidDetails sfp ON (cf.feeId = sfp.feeId and cf.studentid = sfp.studentId and cf.custId=sfp.custId and sfp.deleteStatus = 'Y')
        left join Account a ON (cf.accountId = a.id) LEFT JOIN Person p ON (a.personId = p.id) LEFT JOIN Address d ON (a.paddressId = d.id);

create or replace view vw_studentFeePaymentDetails as
SELECT 
    payment_SequanceId() AS id,
    IFNULL(sp.id, 0) as studentPaymentId,IFNULL(sp.paidAmount, 0) as paidAmount,IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sp.bankId, 0) as bankId,
    sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,sp.ddNumber,bm.bankName,sp.chequeNumber,IFNULL(sp.invoiceNumber, 0) as invoiceNumber,IFNULL(sp.fineAmount,0) as fineAmount,
    IFNULL(sfp.deleteStatus, 'Y') as deleteStatus,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,sp.paymentDate,IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,
    cf.feeId,IFNULL(cf.feeAmount, 0) as feeAmount,cf.custId,cf.feeTypeId,cf.schoolTermId,IFNULL(cf.classId,0) as classId,cf.academicYearId,c.sortingOrder,
    if(((s.transportMode = 'O' and cf.settingName = 'Transport Fee') or (s.transportMode = 'p' and cf.settingName = 'Transport Fee') or (s.hostelMode='D' and cf.settingName = 'Hostel Fee')),'N',cf.feeType) as feeType,
    cf.fromdate,cf.toDate,cf.feeSettingId,cf.settingName,cf.settingType,cf.applToNewStuds,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.dueDate2,cf.dueDate1,cf.categoryId,cf.categoryName,
    cf.routeBoardingPointId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,IFNULL(s.vehicleAcademicDetailsId,0)as vehicleAcademicDetailsId,s.hostelMode,
    s.boardingPointId,cf.className,cf.section,cf.classSectionId,rb.routeId,a.username,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
    p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName,cf.committedFeeStatus,cf.priorityPosition,IFNULL(sfp.committedFeeStatus,'N') as paymentCommitFeeStatus
FROM
    vw_classFeeDetails cf JOIN student s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',(s.boardingPointId = cf.routeBoardingPointId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and s.transportMode = 'T' and cf.categoryId = s.categoryId),(cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and s.boardingPointId!=0 and cf.categoryId = s.categoryId)),
    cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId  and IF(s.joinedThroughAdmissions = 'N',(cf.categoryId = s.categoryId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and cf.applToNewStuds = 'N'),cf.categoryId = s.categoryId) ))  and s.classSectionId = cf.ClassSectionId
    left join class c on (c.id=cf.classId) 
    left join studentFeePaidDetails sfp ON (s.id = sfp.studentId and cf.feeId = sfp.feeId and s.custId=sfp.custId and sfp.deleteStatus = 'Y')
    left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId=sfp.custId and sp.studentId=sfp.studentId)
    left join Account a ON (s.accountId = a.id)
    left join bankMaster bm ON (sp.bankId = bm.id)
    LEFT JOIN Person p ON (a.personId = p.id) 
    left join routeBoardingPoints rb ON (rb.id = s.boardingPointId and rb.custId=s.custId and rb.academicYearId=s.academicYearId) where p.firstName <> '';

-- Used for displaying student fee payment  
CREATE OR REPLACE VIEW vw_studentFeePaidDetails as
SELECT sfp.id feePaidDetailsId,sfp.custId,sfp.deleteStatus,sfp.discountAmount,sfp.paymentAmount,
sfp.paymentStatus,sfp.studentId,sfp.studentPaymentId,sfp.feeId,sfp.futureFeeStatus 
FROM studentFeePaidDetails sfp order by sfp.studentId,sfp.paymentStatus;

-- Used for displaying student fee payment 
CREATE OR REPLACE VIEW vw_studentParticularwiseFeePayments as
SELECT sfp.feePaidDetailsId,sfp.custId,sfp.deleteStatus,SUM(sfp.discountAmount) as discountAmount,SUM(sfp.paymentAmount) as paymentAmount,sfp.paymentStatus,
sfp.studentId,sfp.studentPaymentId,sfp.feeId,sfp.futureFeeStatus from 
vw_studentFeePaidDetails sfp group by studentId,feeId;
  
-- Used for displaying student fee payment   
create or replace view vw_studentFeeParticularsPayment as
SELECT 
    payment_SequanceId() AS id,
    IFNULL(sfp.discountAmount, 0) as discountAmount,
    IFNULL(sfp.deleteStatus, 'Y') as deleteStatus,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,IFNULL(sfp.feePaidDetailsId,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,
    cf.feeId,IFNULL(cf.feeAmount, 0) as feeAmount,cf.custId,cf.feeTypeId,cf.schoolTermId,IFNULL(cf.classId,0) as classId,cf.academicYearId,c.sortingOrder,
    if(((s.transportMode = 'O' and cf.settingName = 'Transport Fee') or (s.transportMode = 'p' and cf.settingName = 'Transport Fee') or (s.hostelMode='D' and cf.settingName = 'Hostel Fee')),'N',cf.feeType) as feeType,
    cf.fromdate,cf.toDate,cf.feeSettingId,cf.settingName,cf.settingType,cf.applToNewStuds,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.dueDate2,cf.dueDate1,cf.categoryId,cf.categoryName,
    cf.routeBoardingPointId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,IFNULL(s.vehicleAcademicDetailsId,0)as vehicleAcademicDetailsId,s.hostelMode,
    s.boardingPointId,cf.className,cf.section,cf.classSectionId,rb.routeId,a.username,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
    p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName
FROM
    vw_classFeeDetails cf JOIN student s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',(s.boardingPointId = cf.routeBoardingPointId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and s.transportMode = 'T' and cf.categoryId = s.categoryId),(cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and s.boardingPointId!=0 and cf.categoryId = s.categoryId)),
    cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId  and IF(s.joinedThroughAdmissions = 'N',(cf.categoryId = s.categoryId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and cf.applToNewStuds = 'N'),cf.categoryId = s.categoryId) ))  and s.classSectionId = cf.ClassSectionId
    left join class c on (c.id=cf.classId) 
    left join vw_studentParticularwiseFeePayments sfp ON (s.id = sfp.studentId and cf.feeId = sfp.feeId and s.custId=sfp.custId and sfp.deleteStatus = 'Y')
    left join Account a ON (s.accountId = a.id)
    LEFT JOIN Person p ON (a.personId = p.id) 
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
        
CREATE or replace VIEW vw_studentCountByBoardingPointId AS 
select count(0) AS count,vw_studentDetails.vehicleId AS vehicleId,vw_studentDetails.vehicleAcademicDetailsId,vw_studentDetails.custId AS custId,
vw_studentDetails.routeId AS routeId,vw_studentDetails.boardingPointId AS boardingPointId,vw_studentDetails.academicYearId,
vw_studentDetails.boardingPointName from vw_studentDetails 
where (vw_studentDetails.vehicleAcademicDetailsId > 0 and vw_studentDetails.boardingPointId > 0 and vw_studentDetails.transportMode = 'T') and vw_studentDetails.description is null  group by vw_studentDetails.boardingPointId,vw_studentDetails.academicYearId;

CREATE OR REPLACE VIEW  vw_routeWiseStudents as
SELECT  rt.id as routeId,rt.routeName,rt.routePointName,rt.status,rtStuds.academicYearId,rtStuds.boardingPointId,rtStuds.count as boardingPointWiseStudsCount,rtStuds.boardingPointName 
FROM route rt  LEFT JOIN vw_studentCountByBoardingPointId rtStuds on (rt.id=rtStuds.routeId);

/*Transport module views */

CREATE OR REPLACE VIEW vw_assignedVehiclestoRoutes AS  
SELECT rv.vehicleAcademicId,rv.routeId,va.name,va.academicYearId,r.custId,IFNULL(va.driverId,0) as driverId,IFNULL(va.helperId,0) as helperId,
va.status,IFNULL(va.vehicleId,0) as vehicleId,r.routeName,r.status as routeStatus,IFNULL(v.noOfSeats,0) as noOfSeats
FROM RouteWithVehicles rv LEFT JOIN vehiclesAcademicDetails va on (va.id = rv.vehicleAcademicId) JOIN route r on (r.id=rv.routeId) JOIN vehicles v on (v.id = va.vehicleId);

CREATE OR REPLACE VIEW vw_assignedVehiclestoRoutesWithBoardingPoints AS  
SELECT rv.vehicleAcademicId,rv.routeId,rv.name,rv.academicYearId,rv.custId,IFNULL(rv.driverId,0) as driverId,IFNULL(rv.helperId,0) as helperId,
rv.status,IFNULL(rv.vehicleId,0) as vehicleId,rv.routeName,rv.routeStatus,rb.boardingPointName,rb.id as boardingPointId,IFNULL(rv.noOfSeats,0) as noOfSeats
FROM vw_assignedVehiclestoRoutes rv LEFT JOIN routeBoardingPoints rb on (rv.routeId = rb.routeId and rv.academicYearId=rb.academicYearId);
 

CREATE OR REPLACE VIEW vw_studentsTransportDetails as 
SELECT vb.vehicleAcademicId,vb.routeId,vb.boardingPointId,vb.boardingPointName,vb.name,vb.academicYearId,vb.custId,vb.driverId,vb.helperId,
vb.status,vb.vehicleId,vb.routeName,vb.routeStatus,stud.fullName,stud.accountId,stud.studId,stud.classSectionId,stud.status as activeStatus,stud.rollNumber,stud.admissionNumber,
stud.registerNumber,stud.studDiscontinueDesc,stud.mobileNumber,stud.parentEmail,stud.className,stud.section,IFNULL(vb.noOfSeats,0) as noOfSeats,IFNULL(stud.classSortingOrder,0) as classSortingOrder
FROM vw_studentClasswiseAndPersonalInfo stud JOIN vw_assignedVehiclestoRoutesWithBoardingPoints vb on (stud.vehicleAcademicDetailsId = vb.vehicleAcademicId and stud.boardingPointId = vb.boardingPointId and stud.academicYearId = vb.academicYearId and stud.transportMode='T'); 


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
    
CREATE OR REPLACE VIEW vw_studentcountbyvehicle AS 
SELECT count(0) AS count,v.id,vw_studentDetails.vehicleId AS vehicleId,vw_studentDetails.custId AS custId,vw_studentDetails.routeId AS routeId,
vw_studentDetails.academicYearId,vw_studentDetails.vehicleAcademicDetailsId
from vw_studentDetails LEFT JOIN vehiclesAcademicDetails v on (v.id=vw_studentDetails.vehicleAcademicDetailsId and v.academicYearId = vw_studentDetails.academicYearId)
 where vw_studentDetails.description is null and vw_studentDetails.transportMode='T'  group by vw_studentDetails.vehicleAcademicDetailsId,vw_studentDetails.routeId;
 
 -- If you add any extra fields for the below view please add them as last elements and also add those new fields in TransportDaoHibernate.java method name getAllViewVehicleWithDriverDetails().  
    
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=root@localhost SQL SECURITY DEFINER VIEW vw_vehicleWithDriverDetails AS select
    va.id AS id,
    va.academicYearId,
    v.chasisNumber AS chasisNumber,
    v.classificationType AS classificationType,
    v.custId AS custId,
    va.driverId AS driverId,
    v.engineNumber AS engineNumber,
    va.helperId AS helperId,
    v.insuranceExpiredDate AS insuranceExpiredDate,
    v.insuranceNumber AS insuranceNumber,
    v.noOfSeats AS noOfSeats,
    v.ownerName AS ownerName, 
    v.insurancePaidDate as insurancePaidDate,
    v.vehicleMaker,
	v.registrationAuthority,
	v.insuranceDetails,
    ifnull(rv.routeId, 0) AS routeId,
    va.status AS status,
    v.vehicleNumber AS vehicleNumber,
    v.vehicleType AS vehicleType,
    v.roadTaxAmount AS roadTaxAmount,
    v.fitnessCheckDate AS fitnessCheckDate,
    v.fitnessExpiryDate AS fitnessExpiryDate,
    v.permitCheckedDate AS permitCheckedDate,
    v.permitExpiryDate AS permitExpiryDate,
    v.pollutionCheckedDate AS pollutionCheckedDate,
    v.pollutionExpiryDate AS pollutionExpiryDate,
    v.roadTaxNextPaymentDate AS roadTaxNextPaymentDate,
    v.roadTaxPaidDate AS roadTaxPaidDate,
    ifnull(va.name, 'None') AS name,
    ifnull((ifnull(v.noOfSeats, 0) - ifnull(vws.count, 0)),0) AS available,
    ifnull(vws.count, 0) AS filled,
    ifnull(concat(vwstaff.firstName, ' ', vwstaff.lastName),'NA') AS driverName,
    ifnull(vwstaff.mobileNumber, '0') AS mobileNumber,
    ifnull(r.routeName, 'NA') AS routeName,
    r.routePointStartTime AS routePointStartTime,
    r.routePointEndTime AS routePointEndTime,
    r.routeEndTimeInMns AS routeEndTimeInMns,
    r.routeStartTimeInMns AS routeStartTimeInMns,
    vwstaff.academicYearStatus as academicYearStatus,
    v.id as vehicleId,va.id as vehicleAcademicDetailId,IFNULL(vwstaff.accountId,0) as accountId 
from
	RouteWithVehicles rv JOIN vehiclesAcademicDetails va on (rv.vehicleAcademicId = va.id) 
	LEFT JOIN vehicles v on (v.id = va.vehicleId)
	LEFT JOIN route r on (r.id=rv.routeId)
	LEFT JOIN vw_studentcountbyvehicle vws on (va.id = vws.vehicleAcademicDetailsId and r.id = vws.routeId)
	LEFT join vw_staffDriverDetails vwstaff on (va.driverId=vwstaff.accountId and vwstaff.academicYearId = va.academicYearId) order by va.name,r.routeName;
	
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
  

CREATE OR REPLACE VIEW vw_vehicleAndDriverInfo AS SELECT 
v.id as vehicleId,va.id as vehicleAcademicDetailId,ifnull(va.name, 'None') AS vehicleName,v.vehicleNumber,IFNULL(concat(staff.firstName, ' ', staff.lastName),'NA') AS driverName,
ifnull(staff.mobileNumber, '0') AS mobileNumber,v.custId,va.academicYearId,IFNULL(va.driverId,0) as driverId
FROM vehicles v JOIN vehiclesAcademicDetails va on (v.id=va.vehicleId) LEFT JOIN vw_staffDriverDetails staff on (va.driverId = staff.accountId and va.academicYearId = staff.academicYearId); 
    

/* For adding roll numbers for students who are not having rollnumber. */
DELIMITER //
DROP PROCEDURE IF EXISTS CreateStudentRollNOs;
CREATE procedure CreateStudentRollNOs(IN sectionId bigint(20),IN lastAssignedRollNum bigint(20)) 
BEGIN    
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE inc INTEGER DEFAULT lastAssignedRollNum;
   DECLARE localID INTEGER DEFAULT 0;
       DECLARE my_cursor CURSOR FOR SELECT studId FROM vw_studentClassDetails WHERE classSectionId = sectionId and status='Y' and rollNumber = 0 order by firstName;   
       DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor;  
               allStudents: LOOP 
                       FETCH my_cursor INTO localID;
                       IF record_not_found THEN 
                       LEAVE allStudents; 
                       END IF;
                       SET inc = inc+1;
                       UPDATE student SET rollNumber=inc WHERE id =localID;
               END LOOP allStudents;    
   CLOSE my_cursor;
   END //    
DELIMITER ;
  
  
/* Library related views */


CREATE 
    OR REPLACE
VIEW vw_bookrackassignmentdetails AS
    select 
        ifnull(ss.name, 'No Subject') AS subjectName,
        ifnull(ba.rackDetailsId, 0) AS rackId,
        b.custId AS custId,
        b.academicYearId AS academicYearId, 
       case when (SELECT sum(bt.noOfCopies) from bookTitle bt where ifnull(bt.subjectId, 0)=ifnull(b.subjectId, 0)
         and (bt.academicYearId = b.academicYearId and bt.custId=b.custId and bt.status <>'N')) then
       (SELECT sum(bt.noOfCopies) from bookTitle bt where ifnull(bt.subjectId, 0)=ifnull(b.subjectId, 0)
         and (bt.academicYearId = b.academicYearId and bt.custId=b.custId and bt.status <>'N')) Else ifnull(sum(b.noOfCopies), 0) 
        end AS noOfCount,                	
         ifnull(sum(ba.noOfCopies), 0) AS existedCount,
         ifnull(b.subjectId, 0) AS subjectId,
         b.status AS status
       from
        ((bookTitle b
         left join booksAssignToRack ba ON (((ba.subjectId = ifnull(b.subjectId, 0))
            and (ba.academicYearId = b.academicYearId)
            and (ba.bookTitleId = b.id)
            and (ba.custId = b.custId))))
         left join studySubject ss ON (((ss.id = b.subjectId)
            and (ss.academicYearId = b.academicYearId)
            and (ss.custId = b.custId))))
     where
        ( b.status <> 'N')
     group by b.subjectId;
     
     
    CREATE 
    OR REPLACE 
    VIEW vw_racksubjectdetails AS
    select 
        ra.id AS Id,
        r.rackName AS rackName,
        ifnull(ss.name,'No Subject') AS subjectName,
        r.id AS rackId,
        r.custId AS custId,
        ra.academicYearId AS academicYearId,
        ra.subjectId AS subjectId,
        ra.noOfCopies AS noOfCopies,
        ra.bookTitleId As bookTitleId
    from
        ((rackDetails r
        left join booksAssignToRack ra ON (((ra.rackDetailsId = r.id)
            and (ra.academicYearId = ra.academicYearId)
            and (r.custId = ra.custId))))
        left join studySubject ss ON (((ra.subjectId = ss.id)
            and (ss.academicYearId = ra.academicYearId)
            and (ss.custId = r.custId))))
    where
        (ra.subjectId is not null);

        
        
 CREATE 
    OR REPLACE
	VIEW vw_studentissuebookandreservations AS
    select 
        bl.lableCode AS lableCode,
        ifnull(ss.name, 'No Subject') AS subjectName,
        bt.id AS bookTitleId,
        bt.bookName AS bookName,
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
            and (ss.academicYearId = bt.academicYearId)
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
        
 

 CREATE 
    OR REPLACE
   VIEW vw_issuedBookAndSettings AS
    select 
        ib.id AS issuedBookId,
        bt.id AS bookTitleId,
        bt.bookName AS bookName,
        bt.otherSubjects AS otherSubjects,
        bt.cost AS cost,
        bt.totalCost AS totalCost,
        bt.readingBookCount AS readingBookCount,
        bt.issueBookCount AS issueBookCount,
        bt.issueDays AS issueDays,
        bt.bookRequestExpareDays AS bookRequestExpareDays,
        bt.publisher AS publisher,
        bt.author AS author,
        ib.accountId AS accountId,
        ib.custId AS custId,
        ib.issuedDate AS issuedDate,
        ib.dueDate AS dueDate,
        ib.status AS status,
        ib.submitedDate AS submitedDate,
        ib.fineAmount AS fineAmount,
        bl.lableCode AS lableCode,
        bl.id AS lableCodeId,
        bl.type AS type,
        bl.deleteStatus AS deleteStatus,
        bl.bookLabelStatus AS bookLabelStatus,
        ls.id AS librarySettingsId,
        ls.fineAmountPerDay AS fineAmountPerDay,
        ls.limitDays AS limitDays,
        ls.noOfStaffIssuBooks AS noOfStaffIssuBooks,
        ls.noOfStudentIssuBooks AS noOfStudentIssuBooks,
        a.username AS username,
        ss.name AS name,
        IFNULL(ib.classId,0) as classId,
        ib.userStatus        
    from
        (((((bookTitle bt
        left join bookLables bl ON ((bl.bookTitleId = bt.id)))
        left join issuedBook ib ON (((ib.bookLableId = bl.id)
            and (ib.bookId = bt.id))))
        left join librarySettings ls ON ((ib.custId = ls.custId)))
        left join Account a ON ((ib.accountId = a.id)))
        left join studySubject ss ON ((bt.subjectId = ss.id)))
where ib.Id is not null group by issuedBookId,accountId;


create or replace view vw_studentFutureFeePaymentDetails as
SELECT 
     payment_SequanceId() AS id,IFNULL(cf.feeId,0) AS feeId,cf.custId,IFNULL(cf.feeSettingId,0) AS feeSettingId,cf.settingName,cf.academicYearId,
     cf.schoolTermId,cf.termName,cf.toMonthName,cf.fromMonthName,IFNULL(cf.feeTypeId,0) AS feeTypeId,cf.feeType,cf.feeAmount,cf.classId,cf.className,cf.section,cf.classSectionId,
     IFNULL(sfp.studentId,0) AS studentId, IFNULL(sfp.discountAmount, 0) as discountAmount,IFNULL(sfp.deleteStatus, 'Y') as deleteStatus,
     IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sp.paidAmount,0) AS paidAmount,sp.paymentDate,sp.invoiceNumber,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,IFNULL(sfp.studentPaymentId, 0) as studentPaymentId
FROM 
vw_classFeeDetails cf 
right join studentFeePaidDetails sfp ON (cf.feeId = sfp.feeId and cf.custId=sfp.custId and deleteStatus='Y' and sfp.futureFeeStatus = 'Y')
right join studentPayment sp ON (sp.id = sfp.studentPaymentId and cf.custId=sfp.custId and cf.academicYearId=sp.academicYearId and sp.studentId=sfp.studentId) group by sp.id,sp.studentId,sfp.feeId;


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
 	   
 	   
create or replace view vw_studentsExcessPaymentDetails as
SELECT ep.id as excessPaymentId,ep.excessAmount,ep.status as excessPaymentStatus,ep.usedPaymentId,ep.accountId,vsd.studId,vsd.academicYearId,
vsd.custId,vsd.classSectionId,vsd.className,vsd.section,vsd.admissionNumber,vsd.rollNumber,vsd.mobileNumber,sp.invoiceNumber,vsd.fullName,vsd.status as activeStatus,
vsd.studDiscontinueDesc 
FROM excessPayment ep JOIN studentPayment sp on (ep.paymentId = sp.id) LEFT JOIN  vw_studentClassDetails vsd on (vsd.accountId = ep.accountId);
 
CREATE OR REPLACE VIEW vw_studentTransportDetails as 
SELECT s.id as studentId,s.custId,s.classSectionId,ac.id as academicYearId,ac.academicYear,ac.status as academicYearStatus,IFNULL(rb.id,0) as boardingPointId,
rb.boardingPointName,IFNULL(va.id,0) as vehicleAcademicDetailsId,va.name as vehicleName,va.vehicleId,s.transportMode,r.routeName,
IFNULL(r.id,0) as routeId,s.categoryId,s.classNameClassId,IFNULL(s.futureAcademicClassSecId,0) as futureAcademicClassSecId,s.accountId,
s.status,s.description,s.registerNumber 
FROM student s JOIN academicYear ac on (s.custId = ac.custId and ac.id = s.academicYearId) 
LEFT JOIN routeBoardingPoints rb on (s.transportMode='T' and s.boardingPointId = rb.id and s.academicYearId= rb.academicYearId) 
LEFT JOIN route r on (r.id = rb.routeId)
LEFT JOIN vehiclesAcademicDetails va on (s.transportMode='T' and s.vehicleAcademicDetailsId = va.id and va.academicYearId = s.academicYearId);

 
CREATE OR REPLACE VIEW vw_studentsFutureAcademicTransportDetails as 
SELECT s.studentId,s.academicYearId,IFNULL(futureYear.id,0) as futureAcademicId,futureYear.academicYear,futureYear.custId,r.id as routeId,r.routeName,rb.id as boardingPointId,
rb.boardingPointName,IFNULL(va.id,0) as vehicleAcademicId,va.name as vehicleName,s.transportMode,s.categoryId,s.classNameClassId,s.classSectionId,
IFNULL(s.futureAcademicClassSecId,0) as futureAcademicClassSecId,IFNULL(c.id,0) as futureAcademicClassId,s.accountId,s.status,s.description,
s.registerNumber 
FROM vw_studentTransportDetails s JOIN academicYear futureYear on (futureYear.custId = s.custId and futureYear.academicYear = CONCAT((SUBSTRING_INDEX(s.academicYear,'-',1)+1),'-',(SUBSTRING_INDEX(s.academicYear,'-',-1)+1)))
LEFT JOIN studyClass sc on (s.futureAcademicClassSecId = sc.id) LEFT JOIN class c on (c.id = sc.classNameClassId)
LEFT JOIN route r on (s.transportMode = 'T' and r.academicYearId = futureYear.id and r.routeName = s.routeName and r.custId=s.custId)
LEFT JOIN routeBoardingPoints rb on (s.transportMode = 'T' and rb.academicYearId = futureYear.id and rb.routeId = r.id and rb.boardingPointName = s.boardingPointName)
LEFT JOIN vehiclesAcademicDetails va on (s.transportMode = 'T' and va.academicYearId = futureYear.id and va.name = s.vehicleName and va.vehicleId = s.vehicleId); 

CREATE OR REPLACE VIEW vw_studentsFutureAcademicFeeDetails as 
SELECT cf.feeSettingId,cf.settingName,cf.schoolTermId,cf.termName,cf.feeTypeId,cf.feeType,cf.feeId,cf.feeAmount,IFNULL(sfp.paymentAmount,0) as paymentAmount,
IFNULL(sfp.discountAmount,0)  as discountAmount,sp.paymentDate,s.studentId,s.custId,s.academicYearId,IFNULL(s.futureAcademicId,0) as futureAcademicId,
IFNULL(s.futureAcademicClassSecId,0) as futureAcademicClassSecId,IFNULL(s.futureAcademicClassId,0) as futureAcademicClassId,cf.fromMonthName,cf.toMonthName,
cf.categoryId,sp.invoiceNumber,sp.paidAmount,s.accountId,s.status,s.description,cf.className,cf.section,cf.classSectionId,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName
FROM vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s ON (s.futureAcademicClassSecId != 0 and IF(cf.settingName = 'Transport Fee',
IF(cf.transportFeeByBoardingPoint = 'Y',
(s.boardingPointId = cf.routeBoardingPointId and cf.custId = s.custId and cf.academicYearId =s.futureAcademicId and s.transportMode = 'T' and cf.categoryId = s.categoryId),
(cf.classId = s.futureAcademicClassId and cf.custId = s.custId and cf.academicYearId = s.futureAcademicId and s.boardingPointId != 0 and cf.categoryId = s.categoryId)
),(cf.classId =s.futureAcademicClassId and cf.custId = s.custId and cf.academicYearId = s.futureAcademicId and cf.categoryId = s.categoryId)
) and cf.classSectionId = s.futureAcademicClassSecId) 
left join class c ON (c.id = cf.classId) 
left join studentFeePaidDetails sfp ON (sfp.studentId = s.studentId and sfp.feeId = cf.feeId and sfp.custId = s.custId and sfp.deleteStatus = 'Y' and sfp.futureFeeStatus = 'Y') 
left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId = s.studentId  and sp.academicYearId =s.futureAcademicId )
left join Account a ON (s.accountId = a.id) LEFT JOIN Person p ON (a.personId = p.id) ;

 /* For adding comments in syllabus subject planner view by cvs on 3-7-2014*/
create 
or replace view vw_syllabusPlannerComments as
SELECT spc.id as syllabusPlannerCommentsId,spc.commentsDate,IFNULL(spc.receiverAccountId,0) as receiverAccountId,spc.messageContent,spc.academicYearId,spc.custId,IFNULL(spc.staffSyllabusPlannerId,0) as staffSyllabusPlannerId,spc.status,ui.id AS imageId,ui.name AS imageName,ui.path AS imagePath,ui.thumbNail AS thumbNail,p.firstName,p.lastName
FROM syllabusPlannerComments spc LEFT JOIN Account a on (a.id=spc.receiverAccountId) LEFT JOIN Person p  on (a.personId=p.id) LEFT JOIN UserImage ui on (ui.id=a.imageId);

CREATE OR REPLACE VIEW vw_circularusers AS SELECT c.id,c.circularDate,	c.custId,c.circularDescription,c.academicYearId,c.senderAccountId,cur.userId from CircularUsers cur  LEFT JOIN circular c on (cur.circularId=c.id);
 
create 
or replace view vw_hostelStudentLeaveDetails as
SELECT s.accountId,s.custId, s.username,s.parentId,s.version, s.sharePrivacy, s.userOnlineNow, s.studentId, s.academicYearId, s.status,s.classSectionId,s.rollNumber,s.firstName,s.lastName, s.middleName, s.fatherName, s.motherName, s.mothersMaidenName,s.dateOfBirth,s.bloodGroup,s.mobileNumber,s.phoneNumber,s.addressLine1,s.addressLine2,s.city,s.state,s.postalCode, s.parentEmail,s.summary,s.dateOfJoining,s.section,
s.className,s.hostelMode,ur.roleName,ur.roleDescription,ur.roleId,l.id leavesId,l.leaveStatus,l.leaveType,l.startTime,l.endTime,l.description,l.startDate,l.endDate,l.supervisorId leaveSupervisorId,IFNULL(l.leavesCount,0) as leavesCount, ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail,s.classNameClassId as classId
FROM vw_studentDetails s  JOIN hostelLeaves l on (s.accountId=l.accountId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN UserImage ui on (ui.id=s.imageId) LEFT JOIN academicYear a on (l.academicYearId = a.id);
 
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
select 
    sda.studentId AS studentId,
    sda.custId AS custId,
    sda.academicYearId AS academicYearId,
    sda.present AS present,
    sda.attendanceDate AS attendanceDate,
    extract(month from sda.attendanceDate) AS month,
    (select monthname(sda.attendanceDate) AS monthName) AS monthName    
from studentDailyAttendance sda;

CREATE OR REPLACE VIEW vw_studyClassStudentsDailyAttendance AS SELECT s.id as studentId,s.custId,s.academicYearId,
sda.attendanceDate,sda.id as studentDailyAttendanceId,sc.id as studyClassId,sc.className,sda.present
from student s  LEFT JOIN studentDailyAttendance sda on (sda.studentId = s.id) LEFT JOIN studyClass sc on (s.classSectionId=sc.id);


CREATE OR REPLACE VIEW vw_studyClassStudentsMonthlyAttendance AS SELECT s.id as studentId,s.custId,s.academicYearId,
sma.totalWorkingDays,sma.id as studentMonthlyAttendanceId,sma.noOfPresentDays,sma.month,sma.monthName, sc.id as studyClassId,sc.className
from student s  LEFT JOIN studentMonthlyAttendance sma on (sma.studentId = s.id) LEFT JOIN studyClass sc on (s.classSectionId=sc.id);

CREATE OR REPLACE VIEW vw_studentSiblings AS SELECT ss.id as studentSiblingsId,ss.custId,
sc.id as studyClassId,sc.className,p.firstName,IFNULL(p.lastName,'') as lastName,a.accountExpired, CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName,
CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as formatedFullName
from studentSiblings ss LEFT JOIN Account a on (ss.accountId = a.id) LEFT JOIN Person p  on (a.personId=p.id) LEFT JOIN studyClass sc on (ss.studyClassId=sc.id);

CREATE OR REPLACE VIEW vw_userIpAddressLocation AS
select ipa.id as ipAddressLocationId, ipa.userId, ipa.custId, ifnull(ipa.academicYearId, 0) as academicYearId, ipa.ipAddress, ipa.loginCount,
	ipa.createdDate as loginDate, time(ipa.createdDate) as loginTime, time(ipa.lastAccessDate) as lastAccessTime, ipa.lastAccessDate,
	c.firstName as custFirstName, c.lastName as custLastName, concat(c.firstName,' ',c.lastName) as custFullName, c.custEmail, c.organization, 
	a.username, ifnull(a.personId,0) as personId, p.firstName, p.lastName, concat(p.firstName,' ',p.lastName) as fullName,
	ifnull(c.addressId,0) as addressId, d.addressLine1, d.streetName, d.city, d.state as stateCode,
	CONCAT(IFNULL(concat(d.addressLine1, ', '), ''),IFNULL(concat(d.streetName, ', '), ''),CHAR(10),IFNULL(concat(d.city, ', '), ''),IFNULL(concat(d.state, '. '), ''),IFNULL(concat('Ph ', c.mobileNumber, '.'),'')) as custAddress,
	r.id as roleId, r.name AS roleName, r.description AS roleDescription,
	ifnull(s.id,0) as studentId, ifnull(sc.id,0) as studyClassId, sc.className, sc.section, ay.academicYear
from ipAddressLocation ipa 
	LEFT JOIN Account a on (ipa.userId = a.id)
	LEFT JOIN customer c on (ipa.custId = c.id)
	LEFT JOIN Person p on (a.personId = p.id)
	LEFT JOIN Address d on (c.addressId = d.id)
	LEFT JOIN UserRoles ur on (a.id = ur.userId)
	LEFT JOIN Role r on (ur.roleId = r.id)
	LEFT JOIN academicYear ay on (ipa.academicYearId = ay.id)
	LEFT JOIN student s on (a.id = s.accountId and ipa.academicYearId = s.academicYearId)
	LEFT JOIN studyClass sc on (s.classSectionId = sc.id);

		
CREATE OR REPLACE VIEW vw_issueProvisionItemsToMess AS SELECT ipim.id as issueProvisionItemsToMessId,ipim.custId,ipim.status,ipim.usedQuantity,
ipim.issueDate,pi.id as provisionItemsId,pi.itemName,pi.measurement,mft.id as messFoodTypeId,mft.foodTypeName, m.id as messId,m.messName
from issueProvisionItemsToMess ipim  LEFT JOIN provisionItems pi on (ipim.provisionItemId = pi.id) LEFT JOIN messFoodType mft on (ipim.messFoodTypeId=mft.id) LEFT JOIN mess m on (ipim.messId=m.id);

DROP PROCEDURE IF EXISTS sp_staffLeavesSummary;  
DELIMITER $$
CREATE PROCEDURE sp_staffLeavesSummary(in $custId bigint(20), in $academicYearId bigint(20))
	BEGIN
		select s.id, s.accountId,
		  ur.roleId,
		  IFNULL(lev.casualLeaves, 0) as casualLeaves,
		  IFNULL(u_casual.usedCasualLeaves, 0) as usedCasualLeaves,
		  IF(IFNULL(lev.casualLeaves, 0) >= IFNULL(u_casual.usedCasualLeaves, 0),IFNULL(lev.casualLeaves, 0)-IFNULL(u_casual.usedCasualLeaves, 0), 0) as remainingCasualLeaves,
		  IFNULL(lev.sickLeaves, 0) as sickLeaves,
		  IFNULL(u_sick.usedSickLeaves,0) usedSickLeaves,
		  IF(IFNULL(lev.sickLeaves, 0) >= IFNULL(u_sick.usedSickLeaves, 0),IFNULL(lev.sickLeaves,0)-IFNULL(u_sick.usedSickLeaves, 0), 0) as remainingSickLeaves,
		  IFNULL(lev.earnedLeaves, 0) as earnedLeaves,
		  IFNULL(u_earned.usedEarnedLeaves,0) usedEarnedLeaves,
		  IF(IFNULL(lev.earnedLeaves, 0) >= IFNULL(u_earned.usedEarnedLeaves, 0),IFNULL(lev.earnedLeaves,0)-IFNULL(u_earned.usedEarnedLeaves, 0), 0) as remainingEarnedLeaves
		from staff s
			Join Account a on a.id = s.accountId
			Join UserRoles ur on ur.userId = a.id
		left Join (
		 Select s1.id, lmgt.casualLeaves, lmgt.sickLeaves, lmgt.earnedLeaves, ur.roleId from staff s1
		 Join Account a on a.id = s1.accountId
		 Join UserRoles ur on ur.userId = a.id
		 Join leaveManagement lmgt on lmgt.roleId = ur.roleId
		 Where lmgt.custId = $custId And lmgt.academicYearId = $academicYearId Group By s1.id
		)lev on lev.id = s.id
		left Join(
		 Select s2.id, a.id as accountId, SUM(l.leavesCount) as usedCasualLeaves, leaveType from staff s2
		 Join Account a on a.id = s2.accountId
		 Join leaves l on l.accountId = a.id
		Where l.academicYearId = $academicYearId And leaveType = 'CL' AND leaveStatus = 'A' And a.custId = $custId
		Group By s2.id
		)u_casual on u_casual.id = s.id
		left Join(
		 Select s3.id, a.id as accountId, SUM(l.leavesCount) usedSickLeaves, leaveType from staff s3
		 Join Account a on a.id = s3.accountId
		 Join leaves l on l.accountId = a.id
		Where l.academicYearId = $academicYearId And leaveType = 'SL' AND leaveStatus = 'A' And a.custId = $custId
		Group By s3.id
		)u_sick on u_sick.id = s.id
		left Join(
		 select s4.id, a.id as accountId, SUM(l.leavesCount) as usedEarnedLeaves, leaveType from staff s4
		 Join Account a on a.id = s4.accountId
		 Join leaves l on l.accountId = a.id
		Where l.academicYearId = $academicYearId And leaveType = 'EL' AND leaveStatus = 'A' And a.custId = $custId
		Group By s4.id
		)u_earned on u_earned.id = s.id
		Where s.custId = $custId and s.status='Y';
	END$$
DELIMITER ;

create or replace view vw_examTypesSchedules as
SELECT IFNULL(et.id, 0) as examTypeId,et.examType,es.custId,es.academicYearId,IFNULL(es.classSectionId, 0) as classSectionId
FROM examTypes et LEFT JOIN examSchedules es ON (et.custId = es.custId and et.academicYearId = es.academicYearId and et.id = es.examTypeId)
LEFT JOIN studentMarks sm ON (es.id = sm.examScheduleId) where sm.id > 0 group by et.id,es.classSectionId;

DROP PROCEDURE IF EXISTS entryStudentCommittedFee;  
DELIMITER $$
CREATE PROCEDURE entryStudentCommittedFee(IN $studentId BIGINT(20),IN $customerId BIGINT(20),IN $classId BIGINT(20),IN $committedFee DOUBLE,IN $categoryId BIGINT,IN $academicYearId BIGINT(20))
BEGIN
    IF($committedFee <> 0) THEN
        BEGIN
            declare classFee_not_found integer default 0;
            declare _feeId bigint default 0;
            declare _feeAmount bigint default 0;
            declare allowedCommittedFee double default 0;
            declare generateInvoiceNumber BIGINT default 0;
            declare studPaymentId BIGINT default 0;
            declare paymentStatus char default 'P';
            declare paidAmount BIGINT default 0;
            DECLARE my_cursorCommittedClassFee CURSOR FOR  select feeId,feeAmount from vw_classFeeDetails where custId=$customerId and classId=$classId and categoryId=$categoryId and committedFeeStatus='Y' order by priorityPosition;
            DECLARE CONTINUE HANDLER FOR NOT FOUND SET classFee_not_found = 1;
            set allowedCommittedFee =$committedFee;
            set generateInvoiceNumber =(select max(invoiceNumber) from studentPayment where custId=$customerId and academicYearId=$academicYearId limit 1)+1;
            insert into studentPayment(createdById,createdDate,lastUpdatedById,version,custId,invoiceNumber,paidAmount,paymentDate,paymentType,academicYearId,studentId) values  (1,CURDATE(),1,0,$customerId,generateInvoiceNumber,$committedFee,CURDATE(),'CH',$academicYearId,$studentId);
            set studPaymentId=(select id from studentPayment where custId=$customerId and invoiceNumber=generateInvoiceNumber limit 1);
            OPEN my_cursorCommittedClassFee;  
                committedClassFee: LOOP
                IF classFee_not_found = 1 THEN 
                    LEAVE committedClassFee; 
                END IF;
                FETCH my_cursorCommittedClassFee INTO _feeId,_feeAmount;
                IF(_feeAmount >= allowedCommittedFee and allowedCommittedFee <> 0)THEN
                    BEGIN
                        set paidAmount= allowedCommittedFee;
                        if(_feeAmount = paidAmount)then
                            set paymentStatus = 'P';
                             -- select paymentStatus,_feeAmount,paidAmount;
                        ELSE
                            set paymentStatus = 'N';
                            -- select paymentStatus,_feeAmount,paidAmount;
                        END IF;
                        set allowedCommittedFee =0;
                        
                        insert into studentFeePaidDetails (createdDate,lastAccessDate,lastUpdatedDate,version,custId,paymentAmount,paymentStatus,studentId,studentPaymentId,feeId,committedFeeStatus) values (CURDATE(),CURDATE(),CURDATE(),0,$customerId,paidAmount,paymentStatus,$studentId,studPaymentId,_feeId,'Y');
                        LEAVE committedClassFee; 
                    END;
                ELSE 
                    BEGIN
                        IF(allowedCommittedFee <> 0)then
                             set paidAmount= _feeAmount;
                             insert into studentFeePaidDetails (createdDate,lastAccessDate,lastUpdatedDate,version,custId,paymentAmount,paymentStatus,studentId,studentPaymentId,feeId,committedFeeStatus) values (CURDATE(),CURDATE(),CURDATE(),0,$customerId,paidAmount,paymentStatus,$studentId,studPaymentId,_feeId,'Y');
                         END IF;
                    END;
               END IF;
                IF(allowedCommittedFee <> 0)then
                    set allowedCommittedFee = allowedCommittedFee-_feeAmount;
                END IF;
            END LOOP committedClassFee;    
            CLOSE my_cursorCommittedClassFee;
            select studPaymentId,generateInvoiceNumber;
            set generateInvoiceNumber=0;
        END;
    END IF;
END$$
DELIMITER ;
 
DROP procedure IF EXISTS GetStudentAttendanceSummary;
DELIMITER $$
CREATE PROCEDURE GetStudentAttendanceSummary (in $custId int,in $acdYearId int, in $studentId int)
BEGIN
-- For Month wise Student Attendance ; 
if  (Select manageAttendanceBy from academicYear where id=$acdYearId) = 'M' then
	Select s.id,s.id studentId,
		IFNULL(sAbs.monthName,'') monthName,
		IFNULL(sAbs.totalWorkingDays,0) workingDays,
		IFNULL(sAbs.noOfPresentDays,0) present,
		IFNULL(sAbs.totalWorkingDays,0) - IFNULL(sAbs.noOfPresentDays,0) absent,
		CAST(TRIM(TRAILING '.00' FROM ROUND(IFNULL(sAbs.noOfPresentDays,0) * 100/IFNULL(sAbs.totalWorkingDays,1),2)) as decimal(10,2)) presentPercentage
	From student s 
	Left JOIN 
		( Select s.id sId,sma.* from student s 
			JOIN studentMonthlyAttendance sma ON sma.studentId = s.id  where custId=$custId
		)sAbs on sAbs.sId = s.id 
	where s.custId=$custId AND 
			IF($studentId = 0 , TRUE, s.id = $studentId) AND
			s.status='Y' And 
			sAbs.monthName is Not NULL 
	Group By s.id,sAbs.monthName
	order By s.id,sAbs.monthName;
-- For Daily Student Attendance ;
ELSE
	Select s.id,s.id studentId,
		monthNames.monthName,
		-- monthNames.YearName,
		-- schoolDaysInfo.totalDays,
		-- schoolDaysInfo.holidaysCount,
		schoolDaysInfo.workingDays workingDays,
		IFNULL(stuAttInfo.PresentDays, schoolDaysInfo.workingDays) present,
		IFNULL(stuAttInfo.AbscentDays, 0) absent,
		IFNULL(stuAttInfo.PresentPercentage, 100) presentPercentage
	From student s 
	-- Mapping Every Month untill today to Each Student Record ; 
	Left Join
		(Select MONTHNAME(holidayDate) monthName,YEAR(holidayDate) YearName,0 as MonthId
			From schoolHolidays 
			where custId=$custId AND DATE(holidayDate) <= DATE(NOW())
			Group By Month(holidayDate)
		)monthNames on monthNames.MonthId=0 
	-- Know and Map the Month Name and its Holidays, totalDays and Working Days info from School Holidays Table ; 
	Left JOIN
		( Select MONTHNAME(SH1.holidayDate) monthName,
				IF(Month(LAST_DAY(SH1.holidayDate))=Month(NOW()),DAY(NOW()),DAY(LAST_DAY(SH1.holidayDate))) totalDays,
				COUNT(SH1.Description) holiDaysCount,
				IF(Month(LAST_DAY(SH1.holidayDate))=Month(NOW()),DAY(NOW()),DAY(LAST_DAY(SH1.holidayDate)))-COUNT(SH1.Description) workingDays
			From schoolHolidays SH1 
			where SH1.custId=$custId AND SH1.holidayDate <= NOW()
			Group By Month(SH1.holidayDate)
		)schoolDaysInfo on schoolDaysInfo.monthName = monthNames.monthName
	-- Returns Each Student Abscent count with respect to the Month ; 
	Left Join
		( Select sda.studentId,
				MONTHNAME(sda.attendanceDate) monthName,
				schoolWorkingDays.totalDays,
				schoolWorkingDays.holidaysCount,
				schoolWorkingDays.workingDays,
				Count(sda.attendanceDate) AbscentDays,
				schoolWorkingDays.workingDays-Count(sda.attendanceDate) PresentDays,
				CAST(TRIM(TRAILING '.00' FROM ROUND(100-(Count(sda.attendanceDate)*100/schoolWorkingDays.workingDays),2)) as decimal(10,2)) PresentPercentage
			From studentDailyAttendance sda
			Left JOIN
				( Select MONTHNAME(SH1.holidayDate) monthName,
						IF(Month(LAST_DAY(SH1.holidayDate))=Month(NOW()),DAY(NOW()),DAY(LAST_DAY(SH1.holidayDate))) totalDays,
						COUNT(SH1.Description) HoliDaysCount,
						IF(Month(LAST_DAY(SH1.holidayDate))=Month(NOW()),DAY(NOW()),DAY(LAST_DAY(SH1.holidayDate)))-COUNT(SH1.Description) workingDays
					From schoolHolidays SH1 
					where SH1.custId=$custId AND SH1.holidayDate <= NOW()
					Group By Month(SH1.holidayDate)
				)schoolWorkingDays on schoolWorkingDays.monthName = MONTHNAME(sda.attendanceDate)
			where sda.custId=$custId
			Group By MONTHNAME(sda.attendanceDate),sda.studentId
			Order By sda.studentId
		)stuAttInfo on stuAttInfo.studentId= s.id AND stuAttInfo.monthName = monthNames.monthName
	WHERE monthNames.monthName is not null AND 
			s.custId=$custId AND 
			s.academicYearId = $acdYearId AND 
			IF($studentId = 0, TRUE, s.id = $studentId)
	Group By s.id, monthNames.monthName;

end if;
END$$

DELIMITER ;