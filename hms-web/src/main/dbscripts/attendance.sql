DROP procedure IF EXISTS `SP_EazySchoolSummary`;
DELIMITER $$
CREATE PROCEDURE SP_EazySchoolSummary ()
BEGIN
DROP TEMPORARY TABLE IF EXISTS EazySchoolSummary;
	CREATE TEMPORARY TABLE EazySchoolSummary(
						Id INT,
						EnrolledCustomers INT,
						UsingCustomers INT,
						StuCount INT,
						EmailWithParents INT,
						MobileWithParents INT,
						ModuleUsage DECIMAL);
	INSERT INTO EazySchoolSummary (Id,EnrolledCustomers,UsingCustomers,StuCount,EmailWithParents,MobileWithParents,ModuleUsage) 
							VALUES (1,0,0,0,0,0,0);

	UPDATE EazySchoolSummary SET EnrolledCustomers = (
		SELECT COUNT(*) FROM customer 
		WHERE accountType='C'
	) WHERE Id=1;
	
	UPDATE EazySchoolSummary SET UsingCustomers = (
		SELECT COUNT(*) FROM customer 
		WHERE accountType='C' And status ='Y'
	) WHERE Id=1;
	
	UPDATE EazySchoolSummary SET StuCount = (
		select count(s.Id) 
		FROM customer c 
			LEFt JOIN academicYear ay ON (c.id = ay.custId and ay.status = 'Y')
			LEFT JOIN student s ON (s.custId = c.id and academicYearId = ay.id and s.status = 'Y')
		WHERE c.accountType='C' And c.status ='Y'
	) WHERE Id=1;
	
	UPDATE EazySchoolSummary SET EmailWithParents = (
		select count(s.Id) 
		from customer c 
			LEFt JOIN academicYear ay ON (c.id = ay.custId and ay.status = 'Y')
			LEFT JOIN student s ON (s.custId = c.id and academicYearId = ay.id and s.status = 'Y')
			LEFT JOIN Account a on (s.custId=a.custId and s.accountId=a.id)
			LEFT JOIN Person p on (p.id=a.personId)
		WHERE c.accountType='C' And c.status ='Y' and p.parentEmail is not null
	) WHERE Id=1;
	
	UPDATE EazySchoolSummary SET MobileWithParents = (
		select count(s.Id) 
		from customer c 
			LEFt JOIN academicYear ay ON (c.id = ay.custId and ay.status = 'Y')
			LEFT JOIN student s ON (s.custId = c.id and academicYearId = ay.id and s.status = 'Y')
			LEFT JOIN Account a on (s.custId=a.custId and s.accountId=a.id)
			LEFT JOIN Person p on (p.id=a.personId)
		WHERE c.accountType='C' And c.status ='Y' and p.mobileNumber is not null
	) WHERE Id=1;


SELECT * FROM EazySchoolSummary;
END$$

DELIMITER ;

DROP procedure IF EXISTS SP_CustomerOverAllModuleUsage;
DELIMITER $$
CREATE PROCEDURE SP_CustomerOverAllModuleUsage ()
BEGIN
SELECT C.Id as CustId, 
	C.organization,
	C.custEmail,
	C.mobileNumber,
	custAddress.city,
	custAddress.state,
	stuTotal.stuCount StudentCount,
	stuTotal.stuCount*30 AllottedMsgCount,
	subUsage.usageCount Subject,
	stuUsage.usageCount Student,
	stfUsage.usageCount Staff,
	stfAssignUsage.usageCount StaffAssign,
	stfLeaveUsage.usageCount StaffLeave,
	stuAttUsage.usageCount StudentAttendance,
	stfAttUsage.usageCount StaffAttendance,
	feeUsage.usageCount Fee,
	stuInvUsage.usageCount StudentInvoice,
	examShedUsage.usageCount ExamShedule,
	stuMarksUsage.usageCount StudentMarks,
	admSettingsUsage.usageCount AdmissionSettings,
	admnsUsage.usageCount Admissions,
	vehcleUsage.usageCount Vehicle,
	librUsage.usageCount Library,
	msgUsage.usageCount Messages,
	kBankUsage.usageCount KnowlwdgeBank,
	(subUsage.usageCount+stuUsage.usageCount+stfUsage.usageCount+stfAssignUsage.usageCount+stfLeaveUsage.usageCount+stuAttUsage.usageCount+
	stfAttUsage.usageCount+feeUsage.usageCount+stuInvUsage.usageCount+examShedUsage.usageCount+stuMarksUsage.usageCount+admSettingsUsage.usageCount+
	admnsUsage.usageCount+vehcleUsage.usageCount+librUsage.usageCount+msgUsage.usageCount+kBankUsage.usageCount) ModuleCoverage,
	17 AS TotalModules,
	ROUND((subUsage.usageCount+stuUsage.usageCount+stfUsage.usageCount+stfAssignUsage.usageCount+stfLeaveUsage.usageCount+stuAttUsage.usageCount+
	stfAttUsage.usageCount+feeUsage.usageCount+stuInvUsage.usageCount+examShedUsage.usageCount+stuMarksUsage.usageCount+admSettingsUsage.usageCount+
	admnsUsage.usageCount+vehcleUsage.usageCount+librUsage.usageCount+msgUsage.usageCount+kBankUsage.usageCount)*100/17) ModuleUsagePercentage 
FROM customer C
	LEFT JOIN (#customer address
		select c.id as CustId,c.organization,c.custEmail,c.mobileNumber,
		IFNULL(ad.city,'') city,
		IFNULL(st.stateName,'') state
		from customer c 
		left join Address ad on (c.addressId=ad.id) 
		left Join State st on (ad.StateId = st.Id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)custAddress on custAddress.CustId = C.Id
	LEFT JOIN (# Total students customer wise
		select count(s.Id) stuCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay ON (c.id = ay.custId and ay.status = 'Y')
		LEFT JOIN student s ON (s.custId = c.id and academicYearId = ay.id and s.status = 'Y')
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stuTotal on stuTotal.CustId = C.Id
	LEFT JOIN (# subject wise usage
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId) 
		LEFT JOIN studySubject s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)subUsage on subUsage.CustId = C.Id
	LEFT JOIN (# Student Usage
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN student s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stuUsage on stuUsage.CustId=C.Id
	LEFT JOIN (# Staff Usage
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN staff s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stfUsage on stfUsage.CustId=C.Id
	LEFT JOIN (# Staff Assign
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN classTeacher s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stfAssignUsage on stfAssignUsage.CustId=C.Id
	LEFT JOIN (# Staff Leave Management
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN leaves s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stfLeaveUsage on stfLeaveUsage.CustId=C.Id
	LEFT JOIN (# Student Daily Attendance
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN staffDailyAttendance s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stuAttUsage on stuAttUsage.CustId=C.Id
	LEFT JOIN (# Staff Daily Attendance
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN staffDailyAttendance s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stfAttUsage on stfAttUsage.CustId=C.Id
	LEFT JOIN (# Fee
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN Fee s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)feeUsage on feeUsage.CustId=C.Id
	LEFT JOIN (# Student Invoice
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN studentPayment s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stuInvUsage on stuInvUsage.CustId=C.Id
	LEFT JOIN (# Exam Schedule
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN examSchedules s on (s.custId=c.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)examShedUsage on examShedUsage.CustId=C.Id
	LEFT JOIN (# Student Marks
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN examSchedules s on (s.custId=c.id)
		LEFT JOIN studentMarks sm on (s.id=sm.examScheduleId)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)stuMarksUsage on stuMarksUsage.CustId=C.Id
	LEFT JOIN (# Admission Settings
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN admissionSettings s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)admSettingsUsage on admSettingsUsage.CustId=C.Id
	LEFT JOIN (# Admissions
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN onlineApplicationDetails s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)admnsUsage on admnsUsage.CustId=C.Id
	LEFT JOIN (# vehicles
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN route s on (s.custId=c.id and s.academicYearId=ay.id)
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)vehcleUsage on vehcleUsage.CustId=C.Id
	LEFT JOIN (# Library
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN bookLables s on (s.custId=c.id )
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)librUsage on librUsage.CustId=C.Id
	LEFT JOIN (# Messages
		SELECT if(DateDiff(NOW(),max(s.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN messages s on (s.custId=c.id )
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)msgUsage on msgUsage.CustId = C.Id
	LEFT JOIN (# K-Bank
		select if(DateDiff(NOW(),max(kb.lastUpdatedDate))<365,1,0) as usageCount, c.id as CustId
		from customer c LEFt JOIN academicYear ay on (c.id=ay.custId and ay.status='Y') 
		LEFT JOIN kBank kb on (kb.custId=c.id )
		WHERE c.accountType='C' And c.status ='Y'
		group by c.id
	)kBankUsage on msgUsage.CustId = C.Id
WHERE C.accountType='C' And C.status ='Y'
Group By C.Id;

END$$

DELIMITER ;

