-- CALL SP_SetDemoDataByOrgName(' HYNIVA Auto Created Test Account','test@hyniva.com','9492527489');

DROP procedure IF EXISTS SP_SetDemoDataByOrgName;
DELIMITER $$
CREATE PROCEDURE SP_SetDemoDataByOrgName ($OrgName nvarchar(250), $Email nvarchar(150),$MobileNo nvarchar(15))
BEGIN
# Checking whether the Parsing Email is already Enrolled or Not
IF (SELECT COUNT(*) FROM customer WHERE BINARY LOWER(custEmail) = LOWER($Email)) > 0  THEN
    SELECT 'Customer Email Already Taken. Please try with different Email!!' AS ERROR;
ELSE
BEGIN
Declare orgId INT;
Declare $custId INT;
Declare acdYearId INT;
Declare castId INT;
Declare commonTypeId INT;
Declare personId INT;
Declare addressId1 INT;
Declare addressId2 INT;
Declare feedBackGradeId INT;
Declare feeTypeId INT;
Declare loginaccessbilityrolesId INT;
Declare categoryId INT;
Declare workingDaysId INT;
Declare sectionId INT;
Declare studySubjectId INT;
Declare classId INT;
Declare studyClassId INT;
Declare accountId INT;
Declare staffId INT;
Declare studentId INT;
Declare termId INT;
Declare feeId INT;
Declare examSubTypeId INT;
Declare examTypeId INT;
Declare examScheduleId INT;
Declare classteacherId INT;
Declare staffElgibleSubjectsId INT;


SET orgId = (SELECT MAX(Id)+1 FROM organization);
SET $custId = (SELECT MAX(Id)+1 FROM customer);
SET acdYearId = (SELECT MAX(Id)+1 FROM academicYear);
SET castId = (SELECT MAX(Id)+1 FROM castSettings);
SET commonTypeId = (SELECT MAX(Id)+1 FROM commonType);
SET personId = (SELECT MAX(Id)+1 FROM Person);
SET addressId1 = (SELECT Id FROM Address WHERE (Id % 1000) = FLOOR(RAND() * 1000) ORDER BY RAND() LIMIT 1);
SET addressId2 = (SELECT Id FROM Address WHERE (Id % 1000) = FLOOR(RAND() * 1000) ORDER BY RAND() LIMIT 1);
SET feedBackGradeId = (SELECT MAX(Id)+1 FROM feedbackGrades);
SET feeTypeId = (SELECT MAX(Id)+1 FROM feeType);
SET loginaccessbilityrolesId = (SELECT MAX(Id)+1 FROM loginAccessbilityRoles);
SET categoryId = (select MAX(Id)+1 From schoolCategory);
SET workingDaysId = (SELECT MAX(Id)+1 FROM workingDays);
SET sectionId = (SELECT MAX(Id)+1 FROM section);
SET studySubjectId = (SELECT MAX(Id)+1 FROM studySubject);
SET classId = (SELECT MAX(Id)+1 FROM class);
SET studyClassId = (SELECT MAX(Id)+1 FROM studyClass);
SET accountId = (SELECT MAX(Id)+1 FROM Account);
SET staffId = (SELECT MAX(Id)+1 FROM staff);
SET studentId = (SELECT MAX(Id)+1 FROM student);
SET termId = (SELECT MAX(Id)+1 FROM schoolTerms);
SET feeId = (SELECT MAX(Id)+1 FROM Fee);
SET examSubTypeId = (SELECT MAX(Id)+1 FROM subType);
SET examTypeId = (SELECT MAX(Id)+1 FROM examTypes);
SET examScheduleId = (SELECT MAX(Id)+1 FROM examSchedules);
SET classteacherId = (SELECT MAX(Id)+1 FROM classTeacher);
SET staffElgibleSubjectsId = (SELECT MAX(Id)+1 FROM staffElgibleSubjects);

# Organization Table    : Creating new Organization with the Name parsed
INSERT INTO organization
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,contactNumber,mobileNumber,orgEmail,
organizationName,status,subscriptionType,webSiteUrl,addressId)
VALUES(orgId,1,NOW(),NOW(),0,NULL,NULL,$MobileNo,$MobileNo,$Email,$OrgName,'Y',NULL,NULL,addressId2);

# Customer Table        : Creating new record in Customer Table
INSERT INTO customer
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,accountType,affiliationNumber,allowedTotalSms,
boardOfEducation,checkEmailService,checkMobileService,contactNumber,custEmail,customerName,customerShortName,customerStatus,deleteInvoicePassword,
educationalDistrict,firstName,hostelModuleStatus,lastName,mobileNumber,modifyInvoicePassword,organization,organizationSubTypeId,orgnizationTypeId,
recognisedBy,reportColorCode,revenueDistrict,schoolCode,sender,startInvoiceNumber,status,subscriptionType,transportModuleStatus,webSiteUrl,
addressId,custImageId,masterCustId,packageDetailsId,academicWiseFeeReceipt,paymentType,customerMission,customerVision,feeReceiptNoWithCustName,
organizationLevel,washRoomId,committedFeeStatus,orgnizationId,orgId,checkMobilePaymentService,bankBranchName,collegeBankAccountNo,nameOfTheAllotedBank,
nameOfTheMandal,natureOfTheCollege,sendSmsUpdatedMobile,senderIdDesc,customerInActiveDescription,mobileType,contactEmail,contactPassword,
parentPermissionStatus,staffPermissionStatus,barcodeStatus,mailChimpAPIKey,mailChimpPassword,mailChimpUserName,smsServiceProviderId,smsCost,
checkParentSmsService,feeReceiptModel,standardType,showAddreesInFeeReceipt,showBalanceAmountInFeeReceipt,checkTransportService,accountModuleUsing,
chalanaGenerationStatus,allowSameAdmissionNumber,diceCode,addStudentsSameAdmissionNumber)
VALUES
($custId,1,NOW(),NOW(),0,NULL,NULL,'D',NULL,0,NULL,'Y','Y',$MobileNo,$Email,NULL,CONCAT('CSN',$custId),'A',NULL,NULL,'HariBabu','Y',
'P',$MobileNo,NULL,$OrgName,0,7,NULL,NULL,NULL,NULL,NULL,0,'Y',NULL,'Y',NULL,addressId1,NULL,NULL,2,0,'P','------ Mission ------','---- Vision ----',NULL,
'S',NULL,NULL,0,orgId,'N',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'P',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,0.14,'N','General','B',NULL,NULL,
'Y',NULL,NULL,'N',NULL,'N');

# AcademicYear Table   : Creating New Academic Year with Start Date as 'Today' and End Date as 'Same Day of Next Year'
INSERT INTO academicYear
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,academicYear,attendancePercentage,
classTeacherHandleFirstPeriod,custId,endDate,endTime,eveningBreakEndTime,eveningBreakStartTime,futureAcademicData,
lunchEndTime,lunchStartTime,manageAttendanceBy,manageStaffAttendanceBy,morningBreakEndTime,morningBreakStartTime,
nextAcademicStartDate,noOfHolidays,noOfWorkingDays,pastYear,startDate,startTime,status,useBiometricForStaff,
useBiometricForStudent,isTransportFeeByBoardingPoint,transportFeeByBoardingPoint,sendBirthdayAlerts,manageStudentsAdmissionsByFee,
dispActivityDescField,timingStatus,holidayStatus,allotedsms,academicDetailsId,branchId,streamId,isDefaultExamTypeStatus,
enableSchoolShift,paidSms,sendStaffBirthdayAlerts,receiptGenerationType,sendBirthdayAlertsByEmail,sendStaffBirthdayAlertsByEmail,
captureAttendanceBy,sendEventAlertSMS,sendEventAlertsByEmail,assignmentSMSLoginUser)
VALUES
(acdYearId,1,NOW(),NOW(),1,null,null,CONCAT(YEAR(NOW()),'-',DATE_FORMAT(DATE_ADD(NOW(),interval 1 year),'%y')),65,'N',
$custId,DATE_ADD(NOW(), INTERVAL 1 YEAR),null,null,null,'N',null,null,'D','D',null,null,null,0,0,YEAR(NOW()),NOW(),null,'Y','N','N',
'N','Y','Y','Y','Y',null,'SH',300,null,null,null,'N',null,0,'Y','A','Y','Y','T','N','N','N');

# CastSettings Table    : Creating Default Record 'Other' in table 
INSERT INTO castSettings(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,
castName,custId,subCastName,communityTypesId) VALUES (castId,1,NOW(),NOW(),0,NULL,NULL,'OTHER',$custId,NULL,0);

# CommonType Table    : Creating Default Records of CommonType
INSERT INTO commonType(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,skillTypeName,type)
VALUES
(commonTypeId,1,NOW(),NOW(),0,NULL,NULL,$custId,'Other','RELIGION'),
(commonTypeId+1,1,NOW(),NOW(),0,NULL,NULL,$custId,'HINDU','RELIGION'),
(commonTypeId+2,1,NOW(),NOW(),0,NULL,NULL,$custId,'JAIN','RELIGION'),
(commonTypeId+3,1,NOW(),NOW(),0,NULL,NULL,$custId,'MUSLIM','RELIGION'),
(commonTypeId+4,1,NOW(),NOW(),0,NULL,NULL,$custId,'CHRISTIAN ','RELIGION'),
(commonTypeId+5,1,NOW(),NOW(),0,NULL,NULL,$custId,'DEAF','HANDICAP'),
(commonTypeId+6,1,NOW(),NOW(),0,NULL,NULL,$custId,'ORTHOPATICALLY HANDICAP','HANDICAP'),
(commonTypeId+7,1,NOW(),NOW(),0,NULL,NULL,$custId,'BLIND','HANDICAP'),
(commonTypeId+8,1,NOW(),NOW(),0,NULL,NULL,$custId,'EVENING SNACKS','MENUITEMS'),
(commonTypeId+9,1,NOW(),NOW(),0,NULL,NULL,$custId,'MORNING SNACKS','MENUITEMS'),
(commonTypeId+10,1,NOW(),NOW(),0,NULL,NULL,$custId,'DINNER','MENUITEMS'),
(commonTypeId+11,1,NOW(),NOW(),0,NULL,NULL,$custId,'LUNCH','MENUITEMS'),
(commonTypeId+12,1,NOW(),NOW(),0,NULL,NULL,$custId,'BREAKFAST','MENUITEMS');


# Address Table
# INSERT INTO address
# (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,accuracy,addressLine1,
# addressLine2,addressLine3,addressType,addressUsage,apartmentNumber,boxNumber,city,country,distance,
# districtId,duration,effectiveDate,email,endDate,latitude,longitude,mandalId,postalCode,ruralRoute,
# state,stateId,streetName,streetNumber,streetPostDirection,streetType,suiteNumber,timeDuration,url,
# villageId,zipCodeSupplement,countryId,districtName,taluka)
# VALUES(addressId,1,NOW(),NOW(),0,NULL,NULL,'','addressLine 1','','','','','','','city','','',0,NULL,NULL,'',NULL,
# '','',0,560043,'','KA',12,'','','','','','','',0,'',0,NULL,NULL),
# (addressId+1,1,NOW(),NOW(),0,NULL,NULL,'','addressLine 1','','','','','','','city','','',0,NULL,NULL,'',NULL,
# '','',0,560043,'','KA',12,'','','','','','','',0,'',0,NULL,NULL);
# -- select * from address Order By Id desc;
# ----------------------------------------------------------------------------------- Address Table Insertion Ended

# FeedBackGrades Table  : Creating Default Records of Grades to new Account
INSERT INTO feedbackGrades
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,
description,fgradeDate,status,title,fOptionsId)
VALUES
(feedBackGradeId,1,NOW(),NOW(),0,NULL,NULL,$custId,'Excellent',NULL,'Y',5,NULL),
(feedBackGradeId+1,1,NOW(),NOW(),0,NULL,NULL,$custId,'Good',NULL,'Y',4,NULL),
(feedBackGradeId+2,1,NOW(),NOW(),0,NULL,NULL,$custId,'Above Average',NULL,'Y',3,NULL),
(feedBackGradeId+3,1,NOW(),NOW(),0,NULL,NULL,$custId,'Average',NULL,'Y',2,NULL),
(feedBackGradeId+4,1,NOW(),NOW(),0,NULL,NULL,$custId,'Bad',NULL,'Y',1,NULL);

# FeeType Table         : Creating Default Records of FeeType/FeeParticulars with 1 additional particular 'Exam Fee' to 'Term Fee' Fee Setting
INSERT INTO feeType
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,
feeSettingId,feeType,status,academicYearId,committedFeeStatus,priorityPosition,academicDetailsId)
VALUES
(feeTypeId,1,NOW(),NULL,0,NULL,NULL,$custId,4,'Hostel Fee','S',acdYearId,NULL,0,NULL),
(feeTypeId+1,1,NOW(),NULL,0,NULL,NULL,$custId,3,'Transport Fee','S',acdYearId,NULL,0,NULL),
(feeTypeId+2,1,NOW(),NULL,0,NULL,NULL,$custId,2,'Tuition Fee','S',acdYearId,NULL,0,NULL),
(feeTypeId+3,1,NOW(),NULL,0,NULL,NULL,$custId,1,'Admission Fee','S',acdYearId,NULL,0,NULL),
(feeTypeId+4,1,NOW(),NULL,0,NULL,NULL,$custId,2,'Exam Fee','S',acdYearId,NULL,0,NULL),
(feeTypeId+5,1,NOW(),NULL,0,NULL,NULL,$custId,1,'Infrastructure Fee','S',acdYearId,NULL,0,NULL);

# Login AccessabilityRoles Table    : Default Mappings for Customer-Accessable Roles for new Account
BEGIN
DECLARE varLoop INT DEFAULT 1;
WHILE varLoop <= 52 DO
    INSERT INTO loginAccessbilityRoles
    (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,customerId,roleId,status)
    VALUES(loginaccessbilityrolesId,1,NOW(),NOW(),0,NULL,0,$custId,varLoop,'Y');

    SET varLoop = varLoop + 1;
    SET loginaccessbilityrolesId = loginaccessbilityrolesId + 1;
END WHILE;
END;


# SchoolCategory Table          : Creating Default Category (General) to new Account
INSERT INTO schoolCategory
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,categoryName,custId)
VALUES(categoryId,1,NOW(),NOW(),0,NULL,NULL,'General',$custId);

# SyllabusTypeInfo Table        : Mapping already exist Syllabus infos (CBSE, SBSE, etc.....) to new Account
 INSERT INTO syllabusTypeInfo(syllabusTypeId,customerId)
 VALUES(1,$custId),(2,$custId),(3,$custId),(4,$custId);

# AcademicYearTimings Table     : Creating Common Academic Year Timings for new Account
INSERT INTO academicYearTimings
(createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,academicYearId,classId,
endTime,eveningBreakEndTime,eveningBreakStartTime,lunchEndTime,lunchStartTime,morningBreakEndTime,morningBreakStartTime,
startTime,status,weekDay)
VALUES
(1,NOW(),NOW(),0,NULL,NULL,acdyearId,0,'04:15PM','03:30PM','03:15PM','01:45PM','12:45PM','10:30AM','10:15AM','09:15AM','ST',NULL);

# SchoolHolidays Table  : Setting 6 days (MON-SAT) in a week as working days for New account
INSERT INTO schoolHolidays
(createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,description,endDate,endTime,
holidayDate,monthId,noOfDays,startDate,startTime,status,yearId,academicYearId,classId,schoolHolidayIdStr,
classHolidayDescription,academicDetailsId)
VALUES(1,NOW(),NULL,0,NULL,NULL,$custId,'SUNDAY','2018-03-25 00:00:00',NULL,'2018-03-25',3,1,'2018-03-25 00:00:00',
NULL,'W',2018,acdYearId,NULL,0,NULL,NULL);

# WorkingDays Table     : Setting 6 days (MON-SAT) in a week as working days for New account
INSERT INTO workingDays
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,academicYearId,dayId,halfDay,academicDetailsId)
VALUES
(workingDaysId,1,NOW(),NOW(),0,NULL,NULL,acdYearId,7,'N',0),
(workingDaysId+1,1,NOW(),NOW(),0,NULL,NULL,acdYearId,6,'N',0),
(workingDaysId+2,1,NOW(),NOW(),0,NULL,NULL,acdYearId,5,'N',0),
(workingDaysId+3,1,NOW(),NOW(),0,NULL,NULL,acdYearId,4,'N',0),
(workingDaysId+4,1,NOW(),NOW(),0,NULL,NULL,acdYearId,3,'N',0),
(workingDaysId+5,1,NOW(),NOW(),0,NULL,NULL,acdYearId,2,'N',0);

# Section Table         : Creating Default 3 (A, B & C) sections to New Account  
INSERT INTO section
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,section,sectionName)
VALUES
(sectionId,1,NOW(),NOW(),0,NULL,NULL,$custId,'C',NULL),
(sectionId+1,1,NOW(),NOW(),0,NULL,NULL,$custId,'B',NULL),
(sectionId+2,1,NOW(),NOW(),0,NULL,NULL,$custId,'A',NULL);

# StudySubject Table    : Creating Subjects of 4 (Telugu, Hindi, English & Maths) to new Account
INSERT INTO studySubject
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,description,language,name,
sortingOrder,subjectNumber,academicYearId,credits,academicDetailsId,examinationHours,groupSub,internalMarks,internalMinMarks,
periodsForPracticle,periodsForTheory,practical,semesterMarks,semesterMinMarks,shortName,total,branchId,courseYearId,semesterId,
theorySubjId,subjectType)
VALUES
(studySubjectId,1,NOW(),NOW(),0,NULL,NULL,$custId,'MAT','N','Mathematics',4,'MAT',acdYearId,0,0,0,NULL,0,0,0,0,'N',0,0,NULL,0,NULL,NULL,NULL,NULL,'N'),
(studySubjectId+1,1,NOW(),NOW(),0,NULL,NULL,$custId,'ENG','Y','English',3,'ENG',acdYearId,0,0,0,NULL,0,0,0,0,'N',0,0,NULL,0,NULL,NULL,NULL,NULL,'N'),
(studySubjectId+2,1,NOW(),NOW(),0,NULL,NULL,$custId,'DRW','N','Drawing',2,'DRW',acdYearId,0,0,0,NULL,0,0,0,0,'N',0,0,NULL,0,NULL,NULL,NULL,NULL,'Y'),
(studySubjectId+3,1,NOW(),NOW(),0,NULL,NULL,$custId,'CS','N','Computer Science',1,'CS',acdYearId,0,0,0,NULL,0,0,0,0,'N',0,0,NULL,0,NULL,NULL,NULL,NULL,'N');

# Class Table           : Creating 14 Common Classes to new Account
INSERT INTO class
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,admissionsOpen,className,
custId,description,entranceMarksUploaded,extendableClassCapacity,higherStandard,noOfSections,sortingOrder,academicYearId,
academicBatchId,endDate,projectWork,startDate,status,courseYearId,semesterId)
VALUES
(classId,1,NOW(),NOW(),0,NULL,NULL,'N','PRE KG',$custId,'PRE KG','N',0,'N',2,1,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+1,1,NOW(),NOW(),0,NULL,NULL,'N','LKG',$custId,'LKG','N',0,'N',2,2,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+2,1,NOW(),NOW(),0,NULL,NULL,'N','UKG',$custId,'UKG','N',0,'N',2,3,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+3,1,NOW(),NOW(),0,NULL,NULL,'N','NURSARY',$custId,'NURSARY','N',0,'N',1,4,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+4,1,NOW(),NOW(),0,NULL,NULL,'N','I',$custId,'I','N',0,'N',3,5,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+5,1,NOW(),NOW(),0,NULL,NULL,'N','II',$custId,'II','N',0,'N',2,6,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+6,1,NOW(),NOW(),0,NULL,NULL,'N','III',$custId,'III','N',0,'N',2,7,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+7,1,NOW(),NOW(),0,NULL,NULL,'N','IV',$custId,'IV','N',0,'N',2,8,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+8,1,NOW(),NOW(),0,NULL,NULL,'N','V',$custId,'V','N',0,'N',2,9,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+9,1,NOW(),NOW(),0,NULL,NULL,'N','VI',$custId,'VI','N',0,'N',2,10,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+10,1,NOW(),NOW(),0,NULL,NULL,'N','VII',$custId,'VII','N',0,'N',2,11,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+11,1,NOW(),NOW(),0,NULL,NULL,'N','VIII',$custId,'VIII','N',0,'N',2,12,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+12,1,NOW(),NOW(),0,NULL,NULL,'N','IX',$custId,'IX','N',0,'N',2,13,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL),
(classId+13,1,NOW(),NOW(),0,NULL,NULL,'N','X',$custId,'X','N',0,'N',2,14,acdYearId,0,NULL,'N',NULL,'N',NULL,NULL);

# StudyClass Table      : Creating Sections for Each class created above 
# Total Classes : 14, 
# With 1 Section : 1, 
# With 2 Sections :11, 
# With 3 Sections :1
INSERT INTO studyClass
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,className,custId,educationType,
filledSeats,groupNumber,section,sectionCapacity,academicYearId,classNameClassId,mediumId,syllabusTypeId,isClassTimetableUploaded,
classInChargeId,status,sectionId,classSectionSubject,syllabusTypeSchoolCodeId)
VALUES
(studyClassId,1,NOW(),NOW(),0,NULL,NULL,'PRE KG',$custId,NULL,0,'','A',25,acdYearId,classId,7,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+1,1,NOW(),NOW(),0,NULL,NULL,'PRE KG',$custId,NULL,0,'','B',25,acdYearId,classId,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+2,1,NOW(),NOW(),0,NULL,NULL,'LKG',$custId,NULL,0,'','A',25,acdYearId,classId+1,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+3,1,NOW(),NOW(),0,NULL,NULL,'LKG',$custId,NULL,0,'','B',25,acdYearId,classId+1,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+4,1,NOW(),NOW(),0,NULL,NULL,'UKG',$custId,NULL,0,'','A',25,acdYearId,classId+2,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+5,1,NOW(),NOW(),0,NULL,NULL,'UKG',$custId,NULL,0,'','B',25,acdYearId,classId+2,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+6,1,NOW(),NOW(),0,NULL,NULL,'NURSARY',$custId,NULL,0,'','A',50,acdYearId,classId+3,7,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+7,1,NOW(),NOW(),0,NULL,NULL,'I',$custId,NULL,0,'','A',25,acdYearId,classId+4,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+8,1,NOW(),NOW(),0,NULL,NULL,'I',$custId,NULL,0,'','B',25,acdYearId,classId+4,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+9,1,NOW(),NOW(),0,NULL,NULL,'I',$custId,NULL,0,'','C',25,acdYearId,classId+4,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+10,1,NOW(),NOW(),0,NULL,NULL,'II',$custId,NULL,0,'','A',25,acdYearId,classId+5,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+11,1,NOW(),NOW(),0,NULL,NULL,'II',$custId,NULL,0,'','B',25,acdYearId,classId+5,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+12,1,NOW(),NOW(),0,NULL,NULL,'III',$custId,NULL,0,'','A',25,acdYearId,classId+6,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+13,1,NOW(),NOW(),0,NULL,NULL,'III',$custId,NULL,0,'','B',25,acdYearId,classId+6,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+14,1,NOW(),NOW(),0,NULL,NULL,'IV',$custId,NULL,0,'','A',25,acdYearId,classId+7,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+15,1,NOW(),NOW(),0,NULL,NULL,'IV',$custId,NULL,0,'','B',25,acdYearId,classId+7,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+16,1,NOW(),NOW(),0,NULL,NULL,'V',$custId,NULL,0,'','A',25,acdYearId,classId+8,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+17,1,NOW(),NOW(),0,NULL,NULL,'V',$custId,NULL,0,'','B',25,acdYearId,classId+8,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+18,1,NOW(),NOW(),0,NULL,NULL,'VI',$custId,NULL,0,'','A',25,acdYearId,classId+9,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+19,1,NOW(),NOW(),0,NULL,NULL,'VI',$custId,NULL,0,'','B',25,acdYearId,classId+9,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+20,1,NOW(),NOW(),0,NULL,NULL,'VII',$custId,NULL,0,'','A',25,acdYearId,classId+10,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+21,1,NOW(),NOW(),0,NULL,NULL,'VII',$custId,NULL,0,'','B',25,acdYearId,classId+10,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+22,1,NOW(),NOW(),0,NULL,NULL,'VIII',$custId,NULL,0,'','A',25,acdYearId,classId+11,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+23,1,NOW(),NOW(),0,NULL,NULL,'VIII',$custId,NULL,0,'','B',25,acdYearId,classId+11,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+24,1,NOW(),NOW(),0,NULL,NULL,'IX',$custId,NULL,0,'','A',25,acdYearId,classId+12,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+25,1,NOW(),NOW(),0,NULL,NULL,'IX',$custId,NULL,0,'','B',25,acdYearId,classId+12,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+26,1,NOW(),NOW(),0,NULL,NULL,'X',$custId,NULL,0,'','A',25,acdYearId,classId+13,1,2,NULL,0,'N',NULL,NULL,NULL),
(studyClassId+27,1,NOW(),NOW(),0,NULL,NULL,'X',$custId,NULL,0,'','B',25,acdYearId,classId+13,1,2,NULL,0,'N',NULL,NULL,NULL);

# ClassSubject Table    : Mapping Subject Details to Class Sections(Study Classes)
# INSERT INTO classsubject(studyClassId,subjectId)VALUES(studyClassId,studySubjectId),(studyClassId+24,studySubjectId);

BEGIN
Declare isFinished INT DEFAULT 0;
Declare _studyClassId INT DEFAULT 0;
Declare studyClasses CURSOR FOR SELECT Id FROM studyClass WHERE custId = $custId;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET isFinished = 1;

OPEN studyClasses;
get_StudyClassId: LOOP
 FETCH studyClasses INTO _studyClassId;
    IF isFinished = 1 THEN 
        LEAVE get_StudyClassId;
    END IF;
    
    INSERT INTO ClassSubject(studyClassId,subjectId)
    VALUES(_studyClassId,studySubjectId),(_studyClassId,studySubjectId+1),(_studyClassId,studySubjectId+2),(_studyClassId,studySubjectId+3);
    
END LOOP get_StudyClassId;
CLOSE studyClasses;
END;

# Person Table      : Creating 3 person entries as follows Admin, Student & Staff
INSERT INTO Person
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,annualIncome,bankAccountNumber,bankBranchName,bankName,bloodGroup,castId,citizenship,classJoined,communityNumber,
contractEndDate,contractStartDate,dateOfBirth,dateOfJoining,designation,experience,familyDoctor,fatherName,fatherQualification,firstName,gender,gpfNumber,height,identification1,identification2,
lastName,licenseExpDate,licenseNumber,maritalStatus,middleName,mobileNumber,motherName,motherOccupation,motherQualification,motherToungId,mothersMaidenName,nationality,passwordHint,FatherOccupation,officeNumber,
oralHygiene,otherCastName,panNumber,parentEmail,passportExpireDate,passportNumber,personalTitle,phoneNumber,prefferedHospital,rationCardNumber,relievingDate,religionId,roleName,scholarShipInfo,socialSecurityNumber,sslcNumber,
subCastId,suffix,summary,tcAppliedDate,tcIssuedDate,teeth,tmrNumber,transferCertificateNo,weight,phId,aadharNumber,ifscCode,isDocsUploaded,lastSchool,placeOfBirth,studentEmail,studentMobile,staffUniqueNumber,
studentUniqueNumber,isPHDocsUploaded,physicallyHandicappedDesc,visionL,visionR,height2,weight2,expAfterPg,expAfterUg,stPhaCouncilNo,stuMobileNumber,vision,secondaryMobileNumber,addressType,anotherMobileNumber,anotherParentEmail,
anotherSecondaryMobileNumber,salaryPaymentMode,staffLocation,scholarShipAmount,Personcol,fatherAadharNumber,motherAadharNumber)
VALUES 
-- ADMIN
(personId,1,NOW(),NOW(),0,NULL,NULL,15000,'035001533648','KALYANAGAR','ICICI','a+',castId,NULL,NULL,NULL,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),NULL,0,NULL,NULL,NULL,'HariBabu','M',NULL,0,NULL,NULL,'P',NULL,NULL,NULL,NULL,IF(CHAR_LENGTH(CONCAT(personId,personId))>10,RIGHT(CONCAT(personId,personId),10),LPAD(CONCAT(personId,personId),10,'0')),NULL,NULL,NULL,0,NULL,'INDIAN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2345678901','ZION',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,'Nothing',NULL,NULL,NULL,NULL,NULL,0,'N','111122223333',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,'R',NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL),
-- Student 
(personId+1,1,NOW(),NOW(),0,NULL,NULL,12000,'035001533649','KALYANAGAR','ICICI','a-',castId,NULL,'Nursary',123,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),NULL,0,'','JogaRao',NULL,'Subramanyam','M',NULL,0,'A Mole on Back','A Mole on Right Hand','P',NULL,NULL,NULL,NULL,IF(CHAR_LENGTH(CONCAT(personId+1,personId+1))>10,RIGHT(CONCAT(personId+1,personId+1),10),LPAD(CONCAT(personId+1,personId+1),10,'0')),'motherName','motherOccupation',NULL,21,NULL,'INDIAN',NULL,'FatherOccupation',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2345678901','ZION','rationCardNumber',NULL,commonTypeId+1,NULL,NULL,NULL,'',0,NULL,'Nothing',NULL,NULL,32,'',NULL,0,'N','111122223333',NULL,NULL,'n/a','Kakinada','haribabu@hyniva.com','9591997451',NULL,'NAS1','N',NULL,'','',0,0,0,0,NULL,NULL,NULL,'','R','','','',NULL,NULL,0,0,'100000000001',''),
-- Staff
(personId+2,1,NOW(),NOW(),0,NULL,NULL,11000,'035001533650','KALYANAGAR','ICICI','a1+',castId,NULL,NULL,NULL,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),'Teacher',5,'Nikesh',NULL,NULL,'Devi','F',NULL,0,NULL,NULL,'P',NULL,NULL,'M',NULL,IF(CHAR_LENGTH(CONCAT(personId+2,personId+2))>10,RIGHT(CONCAT(personId+2,personId+2),10),LPAD(CONCAT(personId+2,personId+2),10,'0')),NULL,NULL,NULL,21,NULL,'INDIAN',NULL,NULL,NULL,NULL,NULL,'PAN12345',NULL,NULL,NULL,NULL,'2345678901','ZION',NULL,NULL,commonTypeId+1,NULL,NULL,NULL,NULL,0,NULL,'Nothing',NULL,NULL,NULL,NULL,NULL,0,'N','111122223333','IFSC123456','N',NULL,NULL,NULL,NULL,'T1',NULL,'N',NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,'R',NULL,NULL,NULL,'NEFT/RTGS','Building 1',0,0,NULL,NULL),
-- Teacher 2
(personId+3,1,NOW(),NOW(),0,NULL,NULL,10000,'035001533651','KALYANAGAR','ICICI','a1-',castId,NULL,NULL,NULL,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),'Teacher',5,'Nikesh',NULL,NULL,'Sai Nagendra','M',NULL,0,NULL,NULL,'P',NULL,NULL,'UN',NULL,IF(CHAR_LENGTH(CONCAT(personId+3,personId+3))>10,RIGHT(CONCAT(personId+3,personId+3),10),LPAD(CONCAT(personId+3,personId+3),10,'0')),NULL,NULL,NULL,21,NULL,'INDIAN',NULL,NULL,NULL,NULL,NULL,'PAN12345',NULL,NULL,NULL,NULL,'2345678901','ZION',NULL,NULL,commonTypeId+1,NULL,NULL,NULL,NULL,0,NULL,'Nothing',NULL,NULL,NULL,NULL,NULL,0,'N','111122223333','IFSC123456','N',NULL,NULL,NULL,NULL,'T2',NULL,'N',NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,'R',NULL,NULL,NULL,'NEFT/RTGS','Building 2',0,0,NULL,NULL),
-- Class Teacher
(personId+4,1,NOW(),NOW(),0,NULL,NULL,9000,'035001533652','KALYANAGAR','ICICI','a2+',castId,NULL,NULL,NULL,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),'Class Teacher',5,'Nikesh',NULL,NULL,'Kasi Annapoorna','F',NULL,0,NULL,NULL,'P',NULL,NULL,'M',NULL,IF(CHAR_LENGTH(CONCAT(personId+4,personId+4))>10,RIGHT(CONCAT(personId+4,personId+4),10),LPAD(CONCAT(personId+4,personId+4),10,'0')),NULL,NULL,NULL,21,NULL,'INDIAN',NULL,NULL,NULL,NULL,NULL,'PAN12345',NULL,NULL,NULL,NULL,'2345678901','ZION',NULL,NULL,commonTypeId+1,NULL,NULL,NULL,NULL,0,NULL,'Nothing',NULL,NULL,NULL,NULL,NULL,0,'N','111122223333','IFSC123456','N',NULL,NULL,NULL,NULL,'T3',NULL,'N',NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,'R',NULL,NULL,NULL,'NEFT/RTGS','Building 3',0,0,NULL,NULL),
-- Librarian
(personId+5,1,NOW(),NOW(),0,NULL,NULL,8000,'035001533648','KALYANAGAR','ICICI','b+',castId,NULL,NULL,NULL,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),'Librarian',5,'Nikesh',NULL,NULL,'VenkateswaRao','M',NULL,0,NULL,NULL,'P',NULL,NULL,'M',NULL,IF(CHAR_LENGTH(CONCAT(personId+5,personId+5))>10,RIGHT(CONCAT(personId+5,personId+5),10),LPAD(CONCAT(personId+5,personId+5),10,'0')),NULL,NULL,NULL,21,NULL,'INDIAN',NULL,NULL,NULL,NULL,NULL,'PAN12345',NULL,NULL,NULL,NULL,'2345678901','ZION',NULL,NULL,commonTypeId+1,NULL,NULL,NULL,NULL,0,NULL,'Nothing',NULL,NULL,NULL,NULL,NULL,0,'N','111122223333','IFSC123456','N',NULL,NULL,NULL,NULL,'T4',NULL,'N',NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,'R',NULL,NULL,NULL,'NEFT/RTGS','Building 4',0,0,NULL,NULL),
-- Accountant
(personId+6,1,NOW(),NOW(),0,NULL,NULL,7000,'035001533648','KALYANAGAR','ICICI','o+',castId,NULL,NULL,NULL,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),'Accountant',5,'Nikesh',NULL,NULL,'Hema','F',NULL,0,NULL,NULL,'P',NULL,NULL,'UN',NULL,IF(CHAR_LENGTH(CONCAT(personId+6,personId+6))>10,RIGHT(CONCAT(personId+6,personId+6),10),LPAD(CONCAT(personId+6,personId+6),10,'0')),NULL,NULL,NULL,21,NULL,'INDIAN',NULL,NULL,NULL,NULL,NULL,'PAN12345',NULL,NULL,NULL,NULL,'2345678901','ZION',NULL,NULL,commonTypeId+1,NULL,NULL,NULL,NULL,0,NULL,'Nothing',NULL,NULL,NULL,NULL,NULL,0,'N','111122223333','IFSC123456','N',NULL,NULL,NULL,NULL,'T5',NULL,'N',NULL,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,'R',NULL,NULL,NULL,'NEFT/RTGS','Building 5',0,0,NULL,NULL);

SELECT 'Account Table Start '+ accountId;
# Account Table         : Creating 3 Accounts for Admin, Staff & Student for login
INSERT INTO Account
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,accountExpired,accountLocked,
admissionNumber,bioMetricId,credentialsExpired,custId,accountEnabled,parentId,password,passwordHint,sharePrivacy,
userOnlineNow,username,caddressId,personId,paddressId,imageId,taddressId,staffNumber,studentImageId,registerNo,
academicBatchId,barcode,enrollmentCode,passwordStatus,OTP,otpStatus,secondaryUsername)
VALUES
(accountId,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(accountId,'_HYN'),0,'N',$custId,'Y',NULL,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
$Email,NULL,personId,addressId1,NULL,addressId2,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,NULL,CONCAT(accountId,'_HYN2')),
(accountId+1,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(accountId,'_HYN'),0,'N',$custId,'Y',NULL,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
CONCAT(accountId+1,'_HYN_STF'),NULL,personId+2,addressId2,NULL,addressId1,'STF001',NULL,NULL,NULL,CONCAT('BC',accountId+1),CONCAT('EC',accountId+1),
'N',NULL,NULL,CONCAT(accountId+1,'_HYN2_STF')),
(accountId+2,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(accountId,'_HYN'),0,'N',$custId,'Y',NULL,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
CONCAT(accountId+2,'_HYN_STF'),NULL,personId+3,addressId2,NULL,addressId1,'STF002',NULL,NULL,NULL,CONCAT('BC',accountId+2),CONCAT('EC',accountId+2),
'N',NULL,NULL,CONCAT(accountId+3,'_HYN2_STF')),
(accountId+3,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(accountId,'_HYN'),0,'N',$custId,'Y',NULL,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
CONCAT(accountId+3,'_HYN_STF'),NULL,personId+4,addressId1,NULL,addressId2,'STF_CT_001',NULL,NULL,NULL,CONCAT('BC',accountId+3),CONCAT('EC',accountId+3),
'N',NULL,NULL,CONCAT(accountId+4,'_HYN2_STF')),
(accountId+4,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(accountId,'_HYN'),0,'N',$custId,'Y',NULL,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
CONCAT(accountId+4,'_HYN_STF'),NULL,personId+5,addressId2,NULL,addressId1,'STF_LB_001',NULL,NULL,NULL,CONCAT('BC',accountId+4),CONCAT('EC',accountId+4),
'N',NULL,NULL,CONCAT(accountId+5,'_HYN2_STF')),
(accountId+5,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(accountId,'_HYN'),0,'N',$custId,'Y',NULL,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
CONCAT(accountId+5,'_HYN_STF'),NULL,personId+6,addressId1,NULL,addressId2,'STF_AC_001',NULL,NULL,NULL,CONCAT('BC',accountId+5),CONCAT('EC',accountId+5),
'N',NULL,NULL,CONCAT(accountId+6,'_HYN2_STF')),
(accountId+6,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(accountId,'_HYN'),0,'N',$custId,'Y',47925,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
CONCAT(accountId+6,'_HYN_STU'),NULL,personId+1,addressId1,NULL,addressId2,NULL,NULL,NULL,NULL,CONCAT('BC',accountId+6),CONCAT('EC',accountId+6),
'N',NULL,NULL,CONCAT(accountId+6,'_HYN2_STU'));
SELECT 'Account Table END';
# UserRoles Table       : User * Role Mappings for the 3 accounts Created (Admin, Teacher & Student)
INSERT INTO UserRoles (userId,roleId) values (accountId,1),(accountId+1,2),(accountId+2,2),(accountId+3,2),(accountId+4,9),(accountId+5,11),(accountId+6,3);

# Staff Table           : Creating 2 Staff Records (Admin & Teacher) to the new Account 
INSERT INTO staff
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,classTeacher,custId,
description,hostelCategoryId,organizationTypeId,qualification1,qualification2,salary,staffPayrollMonth,
staffPayrollYear,staffType,status,supervisorId,academicYearId,accountId,bedId,isTimetableUploaded,outSideSchoolDuty,
sortingOrder,storeAccess,academicDetailsId,departmentId,shiftId,schoolMess,staffGrade)
VALUES
(staffId,1,NOW(),NOW(),0,NULL,NULL,'N',$custId,NULL,0,0,'M.Sc',NULL,NULL,NULL,NULL,NULL,'Y',NULL,acdYearId,accountId,NULL,NULL,'N',0,'N',NULL,NULL,NULL,NULL,NULL),
(staffId+1,1,NOW(),NOW(),0,NULL,NULL,'N',$custId,NULL,0,0,NULL,NULL,NULL,NULL,NULL,'P','Y',NULL,acdYearId,accountId+1,NULL,NULL,'N',0,'N',NULL,NULL,NULL,'Y','H'),
(staffId+2,1,NOW(),NOW(),0,NULL,NULL,'N',$custId,NULL,0,0,NULL,NULL,NULL,NULL,NULL,'P','Y',NULL,acdYearId,accountId+2,NULL,NULL,'N',0,'N',NULL,NULL,NULL,'Y','H'),
(staffId+3,1,NOW(),NOW(),0,NULL,NULL,'Y',$custId,NULL,0,0,NULL,NULL,NULL,NULL,NULL,'P','Y',NULL,acdYearId,accountId+3,NULL,NULL,'N',0,'N',NULL,NULL,NULL,'Y','H'),
(staffId+4,1,NOW(),NOW(),0,NULL,NULL,'N',$custId,NULL,0,0,NULL,NULL,NULL,NULL,NULL,'P','Y',NULL,acdYearId,accountId+4,NULL,NULL,'N',0,'N',NULL,NULL,NULL,'Y','H'),
(staffId+5,1,NOW(),NOW(),0,NULL,NULL,'N',$custId,NULL,0,0,NULL,NULL,NULL,NULL,NULL,'P','Y',NULL,acdYearId,accountId+5,NULL,NULL,'N',0,'N',NULL,NULL,NULL,'Y','H');

# Student Table             : Creating 1 Student record with 'General' Category to 'PRE KG-A'
BEGIN
Declare loopVar INT DEFAULT 0;
Declare isFinished INT DEFAULT 0;
Declare _classId INT DEFAULT 0;
Declare _studyClassId INT DEFAULT 0;
Declare _stuId INT DEFAULT studentId;
Declare _stuAccountId INT DEFAULT accountId+7;
Declare _stuPersonId INT DEFAULT personId+7;
Declare studyClasses CURSOR FOR SELECT Id,classnameclassId FROM studyClass WHERE custId = $custId;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET isFinished = 1;

OPEN studyClasses;
get_StudyClassId: LOOP
 FETCH studyClasses INTO _studyClassId,_classId;
    IF isFinished = 1 THEN 
        LEAVE get_StudyClassId;
    END IF;
    SET loopVar = 1;
    WHILE loopVar< 6 DO
    BEGIN
    Declare _firstName nvarchar(150);
    Declare _gender nvarchar(150);
    Declare _fatherName nvarchar(150);
    Declare _randmPersonId INT;
    SET _randmPersonId = (SELECT Id FROM (SELECT Id,firstName,fatherName,Gender FROM Person WHERE LENGTH(TRIM(firstName)) > 5 AND LENGTH(fatherName)>5 AND gender=IF(loopvar%2=0,'M','F')) A WHERE (Id % 250) = FLOOR(RAND() * 250) ORDER BY RAND() LIMIT 1);
    SET _firstName = (SELECT firstName FROM Person WHERE Id=_randmPersonId);
    SET _gender = (SELECT gender FROM Person WHERE Id=_randmPersonId);
    SET _fatherName = (SELECT fatherName FROM Person WHERE Id=_randmPersonId);
        # Person Insert
        INSERT INTO Person
        (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,annualIncome,bankAccountNumber,bankBranchName,bankName,bloodGroup,castId,citizenship,classJoined,communityNumber,
        contractEndDate,contractStartDate,dateOfBirth,dateOfJoining,designation,experience,familyDoctor,fatherName,fatherQualification,firstName,gender,gpfNumber,height,identification1,identification2,
        lastName,licenseExpDate,licenseNumber,maritalStatus,middleName,mobileNumber,motherName,motherOccupation,motherQualification,motherToungId,mothersMaidenName,nationality,passwordHint,FatherOccupation,officeNumber,
        oralHygiene,otherCastName,panNumber,parentEmail,passportExpireDate,passportNumber,personalTitle,phoneNumber,prefferedHospital,rationCardNumber,relievingDate,religionId,roleName,scholarShipInfo,socialSecurityNumber,sslcNumber,
        subCastId,suffix,summary,tcAppliedDate,tcIssuedDate,teeth,tmrNumber,transferCertificateNo,weight,phId,aadharNumber,ifscCode,isDocsUploaded,lastSchool,placeOfBirth,studentEmail,studentMobile,staffUniqueNumber,
        studentUniqueNumber,isPHDocsUploaded,physicallyHandicappedDesc,visionL,visionR,height2,weight2,expAfterPg,expAfterUg,stPhaCouncilNo,stuMobileNumber,vision,secondaryMobileNumber,addressType,anotherMobileNumber,anotherParentEmail,
        anotherSecondaryMobileNumber,salaryPaymentMode,staffLocation,scholarShipAmount,Personcol,fatherAadharNumber,motherAadharNumber)
        VALUES 
        (_stuPersonId,1,NOW(),NOW(),0,NULL,NULL,0,NULL,NULL,NULL,'a-',castId,NULL,'Nursary',123,NULL,NULL,DATE(DATE_ADD(NOW(), INTERVAL -10 YEAR)),DATE(DATE_ADD(NOW(), INTERVAL -1 YEAR)),NULL,0,'',_fatherName,NULL,_firstName,_gender,NULL,0,'A Mole on Back','A Mole on Right Hand','',NULL,NULL,NULL,NULL,IF(CHAR_LENGTH(CONCAT(_stuAccountId,_stuId))>10,RIGHT(CONCAT(_stuAccountId,_stuId),10),LPAD(CONCAT(_stuAccountId,_stuId),10,'0')),'motherName','motherOccupation',NULL,21,NULL,'INDIAN',NULL,'FatherOccupation',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2345678901','ZION','rationCardNumber',NULL,commonTypeId+1,NULL,NULL,NULL,'',0,NULL,'Nothing',NULL,NULL,32,'',NULL,0,'N','111122223333',NULL,NULL,'n/a','Kakinada','haribabu@hyniva.com','9591997451',NULL,'NAS1','N',NULL,'','',0,0,0,0,NULL,NULL,NULL,'','R','','','',NULL,NULL,0,0,'100000000001','');
        # Account Table 
        INSERT INTO Account
        (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,accountExpired,accountLocked,
        admissionNumber,bioMetricId,credentialsExpired,custId,accountEnabled,parentId,password,passwordHint,sharePrivacy,
        userOnlineNow,username,caddressId,personId,paddressId,imageId,taddressId,staffNumber,studentImageId,registerNo,
        academicBatchId,barcode,enrollmentCode,passwordStatus,OTP,otpStatus,secondaryUsername)
        VALUES
        (_stuAccountId,1,NOW(),NOW(),0,NULL,NULL,'N','N',CONCAT(_stuAccountId,'_HYN'),0,'N',$custId,'Y',47925,'7628a11993549d0a42f17fd4839976e751435278',NULL,'N','N',
        CONCAT(_stuAccountId,'_HYN_STU'),NULL,_stuPersonId,addressId1,NULL,addressId2,NULL,NULL,NULL,NULL,CONCAT('BC',_stuAccountId),CONCAT('EC',_stuAccountId),
        'N',NULL,NULL,CONCAT(_stuAccountId,'_HYN2_STU'));
        # Student Insert
        INSERT INTO student
        (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,status,categoryId,custId,description,joinedThroughAdmissions,
        registerNumber,rollNumber,transportMode,academicYearId,accountId,bedId,classNameClassId,classSectionId,boardingPointId,hostelMode,roomId,
        rollNumberStr,vehicleAcademicDetailsId,onlineApplicationDetailsId,futureAcademicClassSecId,feeConfigured,feePaidStatus,imageId,shippedSection,
        outSideSchoolDuty,committedFee,achievements,goals,interestsAndHobbies,responsibilities,strengths,remarks,promoteToClass,vehicleId,academicDetailsId,
        batchId,eligibleForScholarShip,schoolMess,failurePromotableResons,optionalSubjectId,popUpDisplay,ptaStatus,bplStatus,rteStatus,bplNumber)
        VALUES
        (_stuId,1,NOW(),NOW(),0,NULL,NULL,'Y',categoryId,$custId,NULL,'N',NULL,loopVar,'O',acdYearId,_stuAccountId,NULL,_classId,_studyClassId,NULL,'D',0,NULL,NULL,NULL,NULL,
        'Y','N',NULL,NULL,'N',0,'My Achievements','My Goals','My Hobbies','My Responsibities','My Strength','Remarks',NULL,NULL,0,0,'N','N',NULL,0,NULL,'Y','Y','N','BPL123');
        SET loopVar=loopVar+1;
        SET _stuAccountId=_stuAccountId+1;
        SET _stuPersonId=_stuPersonId+1;
        SET _stuId=_stuId+1;
        END;
    END WHILE;
END LOOP get_StudyClassId;
CLOSE studyClasses;
END;

# StaffElgibleSubjects Table
#INSERT INTO `staffelgiblesubjects`
#(`id`,`createdById`,`createdDate`,`lastAccessDate`,`lastUpdatedById`,`lastUpdatedDate`,`version`,`academicYearId`,`staffId`,`studySubjectId`,`status`)
#VALUES(staffElgibleSubjectsId,1,NOW(),NOW(),0,NULL,NULL,acdYearId,staffId,studySubjectId,'Y');
BEGIN
Declare isFinished INT DEFAULT 0;
Declare _staffId INT DEFAULT 0;
Declare staffIds CURSOR FOR SELECT Id FROM staff WHERE custId = $custId;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET isFinished = 1;

OPEN staffIds;
get_StaffId: LOOP
 FETCH staffIds INTO _staffId;
    IF isFinished = 1 THEN 
        LEAVE get_StaffId;
    END IF;
    
    INSERT INTO `staffElgibleSubjects`
    (`id`,`createdById`,`createdDate`,`lastAccessDate`,`lastUpdatedById`,`lastUpdatedDate`,`version`,`academicYearId`,`staffId`,`studySubjectId`,`status`)
    VALUES(staffElgibleSubjectsId,1,NOW(),NOW(),0,NULL,NULL,acdYearId,_staffId,studySubjectId,'Y'),
            (staffElgibleSubjectsId+1,1,NOW(),NOW(),0,NULL,NULL,acdYearId,_staffId,studySubjectId+1,'Y'),
            (staffElgibleSubjectsId+2,1,NOW(),NOW(),0,NULL,NULL,acdYearId,_staffId,studySubjectId+2,'Y'),
            (staffElgibleSubjectsId+3,1,NOW(),NOW(),0,NULL,NULL,acdYearId,_staffId,studySubjectId+3,'Y');
    SET staffElgibleSubjectsId = staffElgibleSubjectsId+4;
END LOOP get_StaffId;
CLOSE staffIds;
END;

# ClassTeacher Table
INSERT INTO `classTeacher`
(`id`,`createdById`,`createdDate`,`lastAccessDate`,`lastUpdatedById`,`lastUpdatedDate`,`version`,`classTeacher`,`custId`,`periodsCount`,
`academicYearId`,`teacherId`,`studyClassId`,`studySubjectId`,`status`)
VALUES(classteacherId,1,NOW(),NOW(),0,NULL,NULL,'Y',$custId,0,acdYearId,staffId+3,studyClassId,studySubjectId,'Y');


# SchoolTerm Table          : Creating new Term named as 'Term I' for newly created Account
# with From Date as 'Today', Due Date as POST 3 months to Today Date and End Date as POST 4 months to Today Date
INSERT INTO schoolTerms
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,applToNewStuds,custId,description,dueDate,
feeSettingId,fromDate,fromMonthName,mailContentDesc,mobileContentDesc,noOfDays,status,termName,toDate,toMonthName,academicYearId,
dueDate1,dueDate2,academicDetailsId)
VALUES
(termId,1,NOW(),NOW(),0,NULL,NULL,'N',$custId,NULL,DATE(DATE_ADD(NOW(),INTERVAL 3 MONTH)),2,DATE(NOW()),DATE_FORMAT(NOW(),'%M'),
'This is just a friendly reminder that your children <Children Name>  <Term Name > Fee Due date is <Date>.',
'Dear Parents <Term Name> fee of Rs.<Amount>/- due on <Date>, please ignore if you already paid. <School Name>',15,'S','Term I',
DATE(DATE_ADD(NOW(),INTERVAL 4 MONTH)),DATE_FORMAT(DATE_ADD(NOW(),INTERVAL 4 MONTH),'%M'),acdYearId,NULL,NULL,NULL),
(termId+1,1,NOW(),NOW(),0,NULL,NULL,'N',$custId,NULL,DATE(DATE_ADD(NOW(),INTERVAL 3 MONTH)),1,DATE(NOW()),DATE_FORMAT(NOW(),'%M'),
'This is just a friendly reminder that your children <Children Name>  <Term Name > Fee Due date is <Date>.',
'Dear Parents <Term Name> fee of Rs.<Amount>/- due on <Date>, please ignore if you already paid. <School Name>',15,'S','One Time',
DATE(DATE_ADD(NOW(),INTERVAL 4 MONTH)),DATE_FORMAT(DATE_ADD(NOW(),INTERVAL 4 MONTH),'%M'),acdYearId,NULL,NULL,NULL);

# Fee Table                     : Adding Fee Amount '1116' to 'General' Category of Class 'PRE KG' Students For Term 'Term I'
BEGIN
Declare isFinished INT DEFAULT 0;
Declare _classId INT DEFAULT 0;
DECLARE classIds CURSOR FOR SELECT Id FROM class WHERE custId = $custId;
Declare CONTINUE HANDLER FOR NOT FOUND SET isFinished =1;

OPEN classIds;
get_ClassID : LOOP
FETCH classIds INTO _classId;
    IF isFinished =1 THEN
        LEAVE get_ClassID;
    END IF;
    INSERT INTO Fee(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,categoryId,custId,feeAmount,
    status,academicYearId,classId,feeTypeId,schoolTermId,routeBoardingPointId,academicDetailsId,courseYearId)
    VALUES
    (feeId,1,NOW(),NOW(),0,NULL,NULL,categoryId,$custId,7000,NULL,acdYearId,_classId,feeTypeId+2,termId,0,NULL,NULL),
    (feeId+1,1,NOW(),NOW(),0,NULL,NULL,categoryId,$custId,2500,NULL,acdYearId,_classId,feeTypeId+3,termId+1,0,NULL,NULL),
    (feeId+2,1,NOW(),NOW(),0,NULL,NULL,categoryId,$custId,275,NULL,acdYearId,_classId,feeTypeId+4,termId,0,NULL,NULL),
    (feeId+3,1,NOW(),NOW(),0,NULL,NULL,categoryId,$custId,250,NULL,acdYearId,_classId,feeTypeId+5,termId+1,0,NULL,NULL);
    
    SET feeId = feeId + 4;
END LOOP get_ClassId;
CLOSE classIds;
END;
-- select * from fee Order By Id desc;
# ----------------------------------------------------------------------------------- Fee Table Insertion Ended

# ExamSubType Table             : Creating Default Exam Type (Theory) to the created Account
INSERT INTO subType
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,predefinedSubType,schedule,sortingOrder,
subTypeName,academicYearId)
VALUES(examSubTypeId,1,NOW(),NOW(),0,NULL,NULL,$custId,'N','Y',1,'Theory',acdyEarId);
-- select * from subtype Order By Id desc;
# ----------------------------------------------------------------------------------- ExamSubType Table Insertion Ended

# ExamType Table                : Creating 1 Exam Type named as 'Unit Test I'
INSERT INTO examTypes
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,examType,mailContentDesc,maxMarks,minMarks,
mobileContentDesc,noOfDays,sortingOrder,academicYearId,academicDetailsId,examTypeStateId)
VALUES
(examTypeId,1,NOW(),NOW(),0,NULL,NULL,$custId,'Unit Test I',
'Dear Parents,The <examination Type> exams are starting from <start date> get the timetable from website,Please help your son/daughter to score better marks.',
25,9,'Dear Parents <examination> examination is going to start from  <date>  for your children. Thank You From <school name>',15,1,acdYearId,NULL,0);
-- select * from examtypes Order By Id desc;
# ----------------------------------------------------------------------------------- ExamType Table Insertion Ended

# ExamTypesAndSubTypes Table    : Mapping Subtype (Theory) to newly created Exam Type (Unit Test I) 
INSERT INTO examTypesAndSubTypes(examTypeId,subTypeId)VALUES(examTypeId,examSubTypeId);
-- select * from examtypesandsubtypes Order By Id desc;
# ----------------------------------------------------------------------------------- ExamTypesAndSubTypes Table Insertion Ended

# ClassSectionExamTypes Table   : Assigning Exam Type to 2 Sections of Same Class (IX)
INSERT INTO classSectionExamTypes(examTypeId,classSectionId)VALUES(examTypeId,studyClassId+24),(examTypeId,studyClassId+25);
-- select * from classsectionexamtypes Order By Id desc;
# ----------------------------------------------------------------------------------- ClassSectionExamTypes Table Insertion Ended

# ExamSchedules Table
-- Creating Exam Schedule for 1 class (IX-2 Sections) for 3 Subject 
-- Start Date of Exam, POST 15 days from Today to 3 days
INSERT INTO examSchedules
(id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,custId,endDate,endTime,examDate,maxMarks,
scheduled,startDate,startTime,academicYearId,classSectionId,classSubjectId,examTypeId,subTypeId,syllabus,internalMarks,semesterMarks)
VALUES
(examScheduleId,1,NOW(),NOW(),0,NULL,NULL,$custId,DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),'10:00 AM',DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),25,'Y',
DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),'08:00 AM',acdyearId,studyClassId+24,studySubjectId,examTypeId,examSubTypeId,NULL,NULL,NULL),
(examScheduleId+1,1,NOW(),NOW(),0,NULL,NULL,$custId,DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),'10:00 AM',DATE(DATE_ADD(NOW(),INTERVAL 14 DAY)),25,'Y',
DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),'08:00 AM',acdyearId,studyClassId+24,studySubjectId+1,examTypeId,examSubTypeId,NULL,NULL,NULL),
(examScheduleId+2,1,NOW(),NOW(),0,NULL,NULL,$custId,DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),'10:00 AM',DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),25,'Y',
DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),'08:00 AM',acdyearId,studyClassId+24,studySubjectId+2,examTypeId,examSubTypeId,NULL,NULL,NULL),
(examScheduleId+3,1,NOW(),NOW(),0,NULL,NULL,$custId,DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),'10:00 AM',DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),25,'Y',
DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),'08:00 AM',acdyearId,studyClassId+25,studySubjectId,examTypeId,examSubTypeId,NULL,NULL,NULL),
(examScheduleId+4,1,NOW(),NOW(),0,NULL,NULL,$custId,DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),'10:00 AM',DATE(DATE_ADD(NOW(),INTERVAL 14 DAY)),25,'Y',
DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),'08:00 AM',acdyearId,studyClassId+25,studySubjectId+1,examTypeId,examSubTypeId,NULL,NULL,NULL),
(examScheduleId+5,1,NOW(),NOW(),0,NULL,NULL,$custId,DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),'10:00 AM',DATE(DATE_ADD(NOW(),INTERVAL 15 DAY)),25,'Y',
DATE(DATE_ADD(NOW(),INTERVAL 13 DAY)),'08:00 AM',acdyearId,studyClassId+25,studySubjectId+2,examTypeId,examSubTypeId,NULL,NULL,NULL);
-- select * from examschedules Order By Id desc;
# ----------------------------------------------------------------------------------- ExamSchedules Table Insertion Ended

SELECT 'ADMIN' AS ROLE, username AS UserName,'hyniva123' AS Password,secondaryUsername AS SecondaryUserName FROM Account WHERE Id=accountId
UNION ALL SELECT 'STAFF' AS ROLE, userName AS UserName,'hyniva123' AS Password,secondaryUsername AS SecondaryUserName FROM Account WHERE Id=accountId+1
UNION ALL SELECT 'STUDENT' AS ROLE, userName AS UserName,'hyniva123' AS Password,secondaryUsername AS SecondaryUserName FROM Account WHERE Id=accountId+2
UNION ALL SELECT 'STAFF' AS ROLE, userName AS UserName,'hyniva123' AS Password,secondaryUsername AS SecondaryUserName FROM Account WHERE Id=accountId+3
UNION ALL SELECT 'CLASS TEACHER' AS ROLE, userName AS UserName,'hyniva123' AS Password,secondaryUsername AS SecondaryUserName FROM Account WHERE Id=accountId+4
UNION ALL SELECT 'LIBRARIAN' AS ROLE, userName AS UserName,'hyniva123' AS Password,secondaryUsername AS SecondaryUserName FROM Account WHERE Id=accountId+5
UNION ALL SELECT 'ACCOUNTENT' AS ROLE, userName AS UserName,'hyniva123' AS Password,secondaryUsername AS SecondaryUserName FROM Account WHERE Id=accountId+6;
END;
END IF;
END
$$ DELIMITER ; 
-- CALL SP_SetDemoDataByOrgName(' HYNIVA Auto Created Test Account','test@hyniva.com','9492527489');