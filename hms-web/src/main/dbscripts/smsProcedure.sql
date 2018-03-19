/*For updating students roll numbers for a class. */
DROP PROCEDURE IF EXISTS UpdateStudentRollNOs;
DELIMITER $$
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
   END $$    
DELIMITER ;

/*For updating student position based on subject marks in studentMarks table. */


DROP PROCEDURE IF EXISTS calculateStudentsSubjectPosition;    

DELIMITER $$
CREATE PROCEDURE calculateStudentsSubjectPosition(IN $classSectionId bigint(20),IN $examTypeId bigint(20))

BEGIN    
    DECLARE record_not_found INTEGER DEFAULT 0; 
    DECLARE _studId,_examScheduleId,_blnChange,_position,_blnChange1,_rankPosition INTEGER DEFAULT 0;
    DECLARE _obtainedMarks DOUBLE DEFAULT 0;  
    BEGIN
     DECLARE done INT DEFAULT FALSE;
     DECLARE my_cursor CURSOR FOR SELECT marks.studId,marks.examScheduleId,marks.studObtainedMarks,if(@schId = marks.examScheduleId,@bln:=0,@bln:=1) blnChange,
     IF((@marks=(@marks:=marks.studObtainedMarks) XOR @schId = (@schId:=marks.examScheduleId)),@auto:=@auto+1,IF(@bln = 1,@auto:=1,@auto)) position
	 FROM (SELECT studId,examScheduleId, (obtainedMarks+moderationMarks) as studObtainedMarks FROM studentMarks sm  JOIN examSchedules es on(es.id=sm.examScheduleId) WHERE es.classSectionId=$classSectionId and es.examTypeId=$examTypeId ORDER BY sm.examScheduleId,sm.obtainedMarks DESC) AS marks, (SELECT @auto:=1, @marks:=0,@schId:=0) AS fields;   

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
	    DECLARE my_cursor CURSOR FOR SELECT marks1.studId,marks1.examScheduleId,marks1.totalMarks FROM (SELECT studId,examScheduleId, SUM(obtainedMarks+moderationMarks) as totalMarks FROM studentMarks sms  JOIN  examSchedules ess on(ess.id=sms.examScheduleId) WHERE ess.classSectionId=$classSectionId and ess.examTypeId=$examTypeId GROUP BY sms.studId ORDER BY SUM(sms.obtainedMarks+sms.moderationMarks) DESC ) AS marks1;  
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
 END $$
DELIMITER ;


/* For adding roll numbers for students who are not having rollnumber. */

DROP PROCEDURE IF EXISTS CreateStudentRollNOs;
DELIMITER $$
CREATE  PROCEDURE CreateStudentRollNOs(IN sectionId bigint(20),IN lastAssignedRollNum bigint(20))

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
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_staffLeavesSummary;  
DELIMITER $$
CREATE  PROCEDURE sp_staffLeavesSummary(in $staffId bigint(20), in $academicYearId bigint(20))

BEGIN
	select s.id, IFNULL(lev.casualLeaves, 0) as casualLeaves,IFNULL(u_casual.usedCasualLeaves, 0) as usedCasualLeaves,
	IF(IFNULL(lev.casualLeaves, 0) >= IFNULL(u_casual.usedCasualLeaves, 0),IFNULL(lev.casualLeaves, 0)-IFNULL(u_casual.usedCasualLeaves, 0), 0) as remainingCasualLeaves,
	IFNULL(lev.sickLeaves, 0) as sickLeaves,IFNULL(u_sick.usedSickLeaves,0) usedSickLeaves,IF(IFNULL(lev.sickLeaves, 0) >= IFNULL(u_sick.usedSickLeaves, 0),IFNULL(lev.sickLeaves,0)-IFNULL(u_sick.usedSickLeaves, 0), 0) as remainingSickLeaves,
	IFNULL(lev.earnedLeaves, 0) as earnedLeaves,IFNULL(u_earned.usedEarnedLeaves,0) usedEarnedLeaves,IF(IFNULL(lev.earnedLeaves, 0) >= IFNULL(u_earned.usedEarnedLeaves, 0),IFNULL(lev.earnedLeaves,0)-IFNULL(u_earned.usedEarnedLeaves, 0), 0) as remainingEarnedLeaves
	from staff s 
	left Join ( Select s1.id, lmgt.casualLeaves, lmgt.sickLeaves, lmgt.earnedLeaves, ur.roleId 
	from staff s1 Join Account a on a.id = s1.accountId
	Join UserRoles ur on ur.userId = a.id
	Join leaveManagement lmgt on lmgt.roleId = ur.roleId
	Where lmgt.academicYearId = $academicYearId )lev on lev.id = s.id
	left Join(Select s2.id, SUM(l.leavesCount) as usedCasualLeaves, leaveType from staff s2 Join Account a on a.id = s2.accountId Join leaves l on l.accountId = a.id Where l.academicYearId = $academicYearId And leaveType = 'CL' AND leaveStatus = 'A' Group By s2.id ) u_casual on u_casual.id = s.id
	left Join(Select s3.id, SUM(l.leavesCount) usedSickLeaves, leaveType from staff s3 Join Account a on a.id = s3.accountId Join leaves l on l.accountId = a.id Where l.academicYearId = $academicYearId And leaveType = 'SL' AND leaveStatus = 'A' Group By s3.id ) u_sick on u_sick.id = s.id
	left Join(select s4.id, SUM(l.leavesCount) as usedEarnedLeaves, leaveType from staff s4 Join Account a on a.id = s4.accountId Join leaves l on l.accountId = a.id Where l.academicYearId = $academicYearId And leaveType = 'EL' AND leaveStatus = 'A' Group By s4.id ) u_earned on u_earned.id = s.id
	Where s.id = $staffId and s.status='Y';
END $$
DELIMITER ;

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
END $$
DELIMITER ;
 
DROP procedure IF EXISTS GetStudentAttendanceSummary;
DELIMITER $$
CREATE PROCEDURE `GetStudentAttendanceSummary`(IN $acdYearId INT, IN $accountId INT, IN $type CHAR(1))
BEGIN
    DECLARE academicStartDate DATE DEFAULT CURDATE();
    DECLARE totalDays INT DEFAULT 0;
    DECLARE totalWorkingDays INT DEFAULT 0;
	
	SET academicStartDate = (SELECT startDate FROM academicYear where id = $acdYearId);
    IF (SELECT manageAttendanceBy FROM academicYear WHERE id=$acdYearId) = 'M' THEN
    	-- For Monthly Student Attendance
        SELECT 
			s.id,s.id studentId,
			'' monthName,
			IFNULL(SUM(sAbs.totalWorkingDays),0) workingDays,
			IFNULL(SUM(sAbs.noOfPresentDays),0) present,
			IFNULL(SUM(sAbs.totalWorkingDays),0) - IFNULL(SUM(sAbs.noOfPresentDays),0) absent,
			0 presentPercentage
		FROM student s
		LEFT JOIN ( SELECT s.id sId,sma.* FROM student s
				JOIN studentMonthlyAttendance sma ON sma.studentId = s.id WHERE academicYearId=$acdYearId)sAbs ON sAbs.sId = s.id
		WHERE s.academicYearId=$acdYearId AND IF($type = 'A' , TRUE, IF($type = 'C', s.accountId = $accountId, IF($type = 'P', (s.accountId IN (SELECT a.id FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = $accountId)), (s.classSectionId IN (SELECT ct.studyClassId FROM classTeacher ct WHERE ct.teacherId IN(SELECT stt.accountId FROM staff stt WHERE stt.accountId = $accountId))))))
			AND s.status='Y' AND sAbs.monthName IS NOT NULL
		GROUP BY s.id;
        -- For Daily Student Attendance ;
    ELSE
		BEGIN
			DECLARE totalHolidaysDays INT DEFAULT 0;
			SET totalHolidaysDays = (SELECT COUNT(*) from schoolHolidays where academicYearId = $acdYearId AND DATE(holidayDate)<= CURDATE());
			SET totalDays = DATEDIFF(CURDATE(), academicStartDate);

			SELECT 
				s.id,s.id studentId,
				'' monthName,
				(totalDays - totalHolidaysDays) workingDays,
				(totalDays - totalHolidaysDays - IFNULL(COUNT(stuAtt.id), 0)) present,
				IFNULL(COUNT(stuAtt.id), 0) absent,
				0 presentPercentage
			FROM student s
			LEFT JOIN studentDailyAttendance stuAtt ON stuAtt.studentId= s.id
			WHERE s.academicYearId = $acdYearId AND s.status='Y' AND IF($type = 'A' , TRUE, IF($type = 'C', s.accountId = $accountId, IF($type = 'P', (s.accountId IN (SELECT a.id FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId = $accountId)), (s.classSectionId IN (SELECT ct.studyClassId FROM classTeacher ct WHERE ct.teacherId IN(SELECT stt.id FROM staff stt WHERE stt.accountId = $accountId))))))
			GROUP BY s.id;
		END;
    END IF;
END $$
DELIMITER ;



DROP PROCEDURE IF EXISTS sp_studentDailyAttendance;
DELIMITER $$
CREATE PROCEDURE `sp_studentDailyAttendance`(in $custId bigint(20), in $academicYearId bigint(20))
BEGIN
    DECLARE studentId1 INT DEFAULT 0;
    DECLARE monthName1 VARCHAR(16) DEFAULT NULL;
    DECLARE monthIdVal INT DEFAULT 0;
    DECLARE studentAbsentCount INT DEFAULT 0;
    DECLARE studentPresentCount INT DEFAULT 0;
    DECLARE schoolWorkingDays INT DEFAULT 0;
    DECLARE schoolHolidaysCount INT DEFAULT 0;
    DECLARE studentStudyClassId INT DEFAULT 0;
    DECLARE query VARCHAR(512) DEFAULT NULL;
    Declare totalDays INT DEFAULT 0;
     Declare holiDaysCount INT DEFAULT 0;
    DECLARE x  INT DEFAULT 1;
     DROP TEMPORARY TABLE IF EXISTS studentDailyAttendanceList;
     CREATE TEMPORARY TABLE studentDailyAttendanceList(studentId bigint(20),studyClassId bigint(20),academicYearId bigint(20),custId bigint(20),stuAbsentCount INT,stuPresentCount INT,totalWorkingDays INT,monthName varchar(10),monthId INT);
     BEGIN
        WHILE x <= 12 DO
            BEGIN
	          	DECLARE record_not_found INT DEFAULT 0;         
                DECLARE my_cursor CURSOR FOR (select count(*),studentId,month,monthName,classSectionId from vw_StudentDailyAttendance where custId=$custId and academicYearId=$academicYearId and month=x and present='N' group by studentId);
                DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;
                
                SET schoolWorkingDays = (select IF(Month(LAST_DAY(SH1.holidayDate))=Month(NOW()),DAY(NOW()),DAY(LAST_DAY(SH1.holidayDate)))-COUNT(SH1.Description) workingDays From schoolHolidays SH1 where SH1.custId=$custId and academicYearId=$academicYearId AND SH1.holidayDate <= NOW() and monthId=x Group By Month(SH1.holidayDate));
                
                OPEN my_cursor;
                    allStudentsAttendance: LOOP
                        FETCH my_cursor INTO studentAbsentCount,studentId1, monthIdVal, monthName1,studentStudyClassId;
                         IF record_not_found = 1 THEN
                            LEAVE allStudentsAttendance;
                        END IF;
                        IF studentId1 > 0 then  
                            set studentAbsentCount = 0;
                            set studentAbsentCount = (select count(*) from studentDailyAttendance sda where custId=$custId and studentId=studentId1 and academicYearId=$academicYearId and Month(sda.attendanceDate)=x);
                            set studentPresentCount = schoolWorkingDays - studentAbsentCount;
                          
                            insert into studentDailyAttendanceList(studentId,studyClassId,academicYearId,custId,stuAbsentCount,stuPresentCount,totalWorkingDays,monthName,monthId)
                                values(studentId1,studentStudyClassId,$academicYearId,$custId,studentAbsentCount,studentPresentCount,schoolWorkingDays,monthName1,monthIdVal);
                        end IF;
                    END LOOP allStudentsAttendance;
                CLOSE my_cursor;
            END;
            set x= x +1;
        END WHILE;
    END;
   select * from studentDailyAttendanceList;
END $$
DELIMITER ;



DROP PROCEDURE IF EXISTS sp_studentDailyAttendanceByClassId;
DELIMITER $$
CREATE PROCEDURE `sp_studentDailyAttendanceByClassId`(in $custId bigint(20), in $academicYearId bigint(20), in $studyClassID bigint(20))
BEGIN
    DECLARE studentId1 INT DEFAULT 0;
    DECLARE monthName1 VARCHAR(16) DEFAULT NULL;
    DECLARE monthIdVal INT DEFAULT 0;
    DECLARE studentAbsentCount INT DEFAULT 0;
    DECLARE studentPresentCount INT DEFAULT 0;
    DECLARE schoolWorkingDays INT DEFAULT 0;
    DECLARE schoolHolidaysCount INT DEFAULT 0;
    DECLARE studentClassId INT DEFAULT 0;
    DECLARE studentStudyClassId INT DEFAULT 0;
    DECLARE schoolHolidayStatus VARCHAR(16) DEFAULT NULL;
    DECLARE query VARCHAR(512) DEFAULT NULL;
    DECLARE totalDays INT DEFAULT 0;
    DECLARE holiDaysCount INT DEFAULT 0;
    DECLARE x  INT DEFAULT 1;
    DROP TEMPORARY TABLE IF EXISTS studentDailyAttendanceList;
     CREATE TEMPORARY TABLE studentDailyAttendanceList(studentId bigint(20),studyClassId bigint(20),academicYearId bigint(20),custId bigint(20),stuAbsentCount INT,stuPresentCount INT,totalWorkingDays INT,monthName varchar(10),monthId INT);
     BEGIN
        WHILE x <= 12 DO
            BEGIN
	          	DECLARE record_not_found INT DEFAULT 0;         
                DECLARE my_cursor CURSOR FOR (select count(*),studentId,month,monthName,classSectionId from vw_StudentDailyAttendance where custId=$custId and academicYearId=$academicYearId and month=x and present='N' and classSectionId=$studyClassID group by studentId);
                DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;
                
                SET schoolHolidayStatus = (select holidayStatus From academicYear ay where custId=$custId and id=$academicYearId);
                
                 SET studentClassId = (select classId from vw_classSectionDetails where custId=$custId and academicYearId=$academicYearId and classSectionId=$studyClassID);
               
                 if (schoolHolidayStatus = 'SH') then
                	SET schoolWorkingDays = (select IF(Month(LAST_DAY(SH1.holidayDate))=Month(NOW()),DAY(NOW()),DAY(LAST_DAY(SH1.holidayDate)))-COUNT(SH1.Description) workingDays From schoolHolidays SH1 where SH1.custId=$custId and academicYearId=$academicYearId AND SH1.holidayDate <= NOW() and monthId=x Group By Month(SH1.holidayDate));
                ELSEif (schoolHolidayStatus = 'CH') then
                	 IF studentClassId > 0 then  
                		SET schoolWorkingDays = (select IF(Month(LAST_DAY(SH1.holidayDate))=Month(NOW()),DAY(NOW()),DAY(LAST_DAY(SH1.holidayDate)))-COUNT(SH1.Description) workingDays From schoolHolidays SH1 where SH1.custId=$custId and academicYearId=$academicYearId AND SH1.holidayDate <= NOW() and monthId=x and classId=studentClassId Group By Month(SH1.holidayDate));
              		 end IF;
                END if;
                
                OPEN my_cursor;
                    allStudentsAttendance: LOOP
                        FETCH my_cursor INTO studentAbsentCount,studentId1, monthIdVal, monthName1,studentStudyClassId;
                         IF record_not_found = 1 THEN
                            LEAVE allStudentsAttendance;
                        END IF;
                        IF studentId1 > 0 then  
                            set studentAbsentCount = 0;
                            set studentAbsentCount = (select count(*) from studentDailyAttendance sda where custId=$custId and studentId=studentId1 and academicYearId=$academicYearId and Month(sda.attendanceDate)=x);
                            
                            set studentPresentCount = schoolWorkingDays - studentAbsentCount;
                          
                            insert into studentDailyAttendanceList(studentId,studyClassId,academicYearId,custId,stuAbsentCount,stuPresentCount,totalWorkingDays,monthName,monthId)
                                values(studentId1,$studyClassID,$academicYearId,$custId,studentAbsentCount,studentPresentCount,schoolWorkingDays,monthName1,monthIdVal);
                        end IF;
                    END LOOP allStudentsAttendance;
                CLOSE my_cursor;
            END;
            set x= x +1;
        END WHILE;
    END;
   select * from studentDailyAttendanceList s;
END $$
DELIMITER ; 

DROP procedure IF EXISTS sp_MonthlyStudentAttendance;
DELIMITER $$
CREATE PROCEDURE sp_MonthlyStudentAttendance (
IN $monthNo INT, 
IN $studyClassId INT, 
IN $custId INT, 
IN $acdYearId INT
)
BEGIN
-- CALL emsprod.sp_MonthlyStudentAttendance(10,2504,61,92);
DROP TEMPORARY TABLE IF EXISTS presentStudentsList;
CREATE TEMPORARY TABLE presentStudentsList(
studentId INT,
studentName VARCHAR(400),
attState CHAR(2),
attendanceDate DATE);

INSERT INTO presentStudentsList(studentId,studentName,attState,attendanceDate)

SELECT s.id AS studentId,CONCAT(p.firstName, ' ', p.lastName) AS studentName,a.present,a.attendanceDate AS attendanceDate
FROM  student s  JOIN Account ac on s.accountId = ac.id Join Person p on ac.personId = p.id LEFT JOIN studentDailyAttendance a ON (s.id = a.studentId)
WHERE s.classSectionId = $studyClassId AND MONTH(attendanceDate) = $monthNo AND s.custId = $custId AND s.academicYearId = $acdYearId AND s.description is null;
INSERT INTO presentStudentsList(studentId,studentName,attState,attendanceDate)

SELECT 
s.Id,
CONCAT(IFNULL(p.firstName,''),' ',IFNULL(p.lastName,'')) studentName,
'P',
MAKEDATE(1900,1)
FROM
student s
JOIN Account ac on s.accountId = ac.Id
Join Person p on ac.personId = p.id
WHERE s.id NOT IN(
SELECT studentId FROM studentDailyAttendance WHERE MONTH(attendanceDate) = $monthNo AND academicYearId = $acdYearId GROUP BY studentId)
AND s.classSectionId = $studyClassId AND 
s.custId = $custId AND
s.academicYearId = $acdYearId AND s.description is null;

SELECT * FROM presentStudentsList ORDER BY studentId;
END$$

DELIMITER ; 

DROP procedure IF EXISTS updateCustomerSMSLimit;
DELIMITER $$
CREATE  PROCEDURE updateCustomerSMSLimit()
BEGIN
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE customerId INTEGER DEFAULT 0;
   DECLARE studentCount INTEGER DEFAULT 0;
   DECLARE academicYearId INTEGER DEFAULT 0;
   DECLARE my_cursor CURSOR FOR SELECT id FROM customer;   
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor;  
               allCustomer: LOOP 
                       FETCH my_cursor INTO customerId;
                       IF record_not_found THEN 
                       LEAVE allCustomer; 
                       END IF;
                       SET academicYearId = (select id from academicYear where custId=customerId and status='Y');
                       if(academicYearId !=0)THEN
                        BEGIN
                            DECLARE studentCount INTEGER DEFAULT 0;
                            set studentCount = (select count(*) from student s where s.custId=customerId and s.academicYearId=academicYearId and s.description is null);
                            if(studentCount !=0)THEN
                                update academicYear ay set ay.allotedsms=(studentCount*30) where ay.id=academicYearId;
                            ELSE
                                update academicYear ay set ay.allotedsms=1 where ay.id=academicYearId;
                            END IF;
                        END; 
                    END IF;
               END LOOP allCustomer;    
   CLOSE my_cursor;
END $$
DELIMITER ; 

DROP procedure IF EXISTS updateMessageCharacterCount;
DELIMITER $$
CREATE PROCEDURE updateMessageCharacterCount()
BEGIN
    declare messages_not_found integer default 0;
    declare _messageId bigint default 0;
    DECLARE _messageDesc VARCHAR(1020) DEFAULT NULL;
    declare messagesCount bigint default 0;
    declare _messagessentSms bigint default 0;
    DECLARE my_cursorMessageDetails CURSOR FOR  select id,messageDescription,sentSms from messages where messageType='SMS' group by id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET messages_not_found = 1;
    OPEN my_cursorMessageDetails;  
                myMessageDetails: LOOP
                IF messages_not_found = 1 THEN 
                    LEAVE myMessageDetails; 
                END IF;
                FETCH my_cursorMessageDetails INTO _messageId,_messageDesc,_messagessentSms;
                BEGIN
              --  select _messageDesc;
                    set messagesCount = LENGTH(_messageDesc); 
                    update messages m set m.messagesCount=(CEIL((messagesCount)/160)*(_messagessentSms)) where m.id=_messageId;
                END;
        END LOOP myMessageDetails;    
    CLOSE my_cursorMessageDetails;
END $$
DELIMITER ;

DROP procedure IF EXISTS updateExistCustPaidSMSCount;
DELIMITER $$
CREATE  PROCEDURE updateExistCustPaidSMSCount()
BEGIN
    DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE customerId INTEGER DEFAULT 0;
   DECLARE my_cursor CURSOR FOR SELECT id FROM customer where accountType='C' and status='Y' and id not in(60,61,62,63,65,67,68,73,16,26,50);    
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1; 
   OPEN my_cursor;
        allCustomer: LOOP 
         FETCH my_cursor INTO customerId;
           IF record_not_found THEN 
           LEAVE allCustomer; 
        END IF;
        BEGIN
                DECLARE ac_record_not_found INTEGER DEFAULT 0; 
               DECLARE academicYearId INTEGER DEFAULT 0;
               DECLARE allotedsms INTEGER DEFAULT 0;
               DECLARE usedSmscount INTEGER DEFAULT 0;
               DECLARE paidSms INTEGER DEFAULT 0;
               DECLARE totalSmsAlloted INTEGER DEFAULT 0;
               DECLARE forwordPaidSmsCount INTEGER DEFAULT 0;
               DECLARE carryForwardSmsCount INTEGER DEFAULT 0;
               DECLARE actualAllotedCount INTEGER DEFAULT 0;
               DECLARE stuCount INTEGER DEFAULT 0;
               DECLARE customerAcademicYear CURSOR FOR select a.id,a.allotedsms,a.paidSms from academicYear a where a.custId=customerId and a.academicYear='2014-15';
               DECLARE CONTINUE HANDLER FOR NOT FOUND SET ac_record_not_found = 1; 
               -- select customerId;
               OPEN customerAcademicYear;
                    academicYear : LOOP
                        FETCH customerAcademicYear INTO academicYearId,allotedsms,paidSms;
                        IF ac_record_not_found THEN 
                           LEAVE academicYear; 
                        END IF;
                        if(academicYearId !=0)THEN
                            BEGIN
                                #select academicYearId,allotedsms,paidSms,customerId;
                                set stuCount =(select count(*) from student s where s.custId=customerId and s.academicYearId=academicYearId and s.description is null);
                                #update academicYear ay set ay.allotedsms=(stuCount*30) where ay.custId=customerId and ay.id=academicYearId;
                                set totalSmsAlloted = (allotedsms+paidSms);
                                #select "Ganesh",customerId,academicYearId;
                                set usedSmscount = (select sum(m.messagesCount) from messages m where m.custId=customerId and m.academicYearId=academicYearId and m.messageType='SMS');
                                #select customerId,usedSmscount,allotedsms;
                                if(usedSmscount < allotedsms)THEN
                                    update academicYear ayc set ayc.allotedsms=(stuCount*30),ayc.paidSms=0 where ayc.id=academicYearId;
                                    
                                ELSE
                                    #set forwordPaidSmsCount =  (usedSmscount - allotedsms);
                                   -- set carryForwardSmsCount = (paidSms-forwordPaidSmsCount);
                                   #select customerId,stuCount,(stuCount*30) as stuCOunt,usedSmscount,(usedSmscount-(stuCount*30)) as paidCount;
                                    update academicYear ayd set ayd.allotedsms=(stuCount*30),ayd.paidSms=(usedSmscount-(stuCount*30)) where ayd.id=academicYearId;
                                END IF;
                            END;
                        END IF;
                       set allotedsms=0;
                       set paidSms=0;
                       set totalSmsAlloted=0;
                        END LOOP academicYear;
                CLOSE customerAcademicYear;
            END;
        END LOOP allCustomer;
    CLOSE my_cursor;
END $$
DELIMITER ;

DROP procedure IF EXISTS sp_insertMessage;
DELIMITER $$
CREATE  PROCEDURE sp_insertMessage(
IN $createdById bigint(20),
IN $custId bigint(20),
IN $messageStatus varchar(100),
IN $purposeType varchar(100),
IN $messageDescription varchar(10000),
IN $senderName varchar(100),
IN $sentSms int,
IN $messageType varchar(100),
IN $invalidMobileNos mediumtext,
IN $msgCount int,
IN $academicYearId long,
IN $smsProviders varchar(100),
IN $guid varchar(100))
BEGIN
     insert into messages 
     (createdById,createdDate,lastAccessDate,lastUpdatedDate,version,custId,status,purposeType,messageDescription,
     senderName,sentSms,messageType,invalidMobileNos,messagesCount,academicYearId,smsProvidersId,guid,messageDate,senderAccountId,receiverAccountId,smsCount) 
     values ($createdById,NOW(),NOW(),NOW(),0,$custId,$messageStatus,$purposeType,$messageDescription,$senderName,
     $sentSms,$messageType,$invalidMobileNos,$msgCount,$academicYearId,$smsProviders,$guid,NOW(),0,0,0);  
     
     SELECT LAST_INSERT_ID();
END $$
DELIMITER ;


/* This procedure adding the staff record for admin role account, this is one time task No need to run every time
 * 
 * call addStaffRecordForAdmin(); */

DROP PROCEDURE IF EXISTS addStaffRecordForAdmin;
DELIMITER $$
CREATE procedure addStaffRecordForAdmin() 
BEGIN    
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE inc INTEGER DEFAULT 0;
   DECLARE academicYearId INTEGER DEFAULT 0;
   DECLARE maxStaffId INTEGER DEFAULT 0;
   DECLARE accountIdVal INTEGER DEFAULT 0;
   DECLARE roleIdVal INTEGER DEFAULT 0;
   DECLARE custIdVal INTEGER DEFAULT 0;
   DECLARE my_cursor CURSOR FOR select accountId,roleId,custId from vw_allUsers where roleId = 1;   
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
    SET maxStaffId = (SELECT MAX(id) FROM staff);
   
   OPEN my_cursor;  
               allStaffAdmins: LOOP 
                       FETCH my_cursor INTO accountIdVal,roleIdVal,custIdVal;
                       IF record_not_found THEN 
                       LEAVE allStaffAdmins; 
                       END IF;
                       SET maxStaffId = maxStaffId+1;
                      
                      SET academicYearId = (select id from academicYear where custId=custIdVal and status='Y');
                        select academicYearId;
                      INSERT INTO staff(id,createdDate,version,status,accountId,custId,academicYearId,staffType) VALUES (maxStaffId,CURDATE(),1,'Y',accountIdVal,custIdVal,academicYearId,'P');
			
               END LOOP allStaffAdmins;    
   CLOSE my_cursor;
   END $$    
DELIMITER ;

DROP PROCEDURE IF EXISTS updateStudentParentId;
DELIMITER $$
CREATE PROCEDURE updateStudentParentId()
BEGIN
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE customerId INTEGER DEFAULT 0;
   DECLARE studentCount INTEGER DEFAULT 0;
   DECLARE academicYearId INTEGER DEFAULT 0;
   DECLARE my_cursor CURSOR FOR SELECT id FROM customer;   
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor;  
       allCustomer: LOOP 
               FETCH my_cursor INTO customerId;
               IF record_not_found THEN 
               LEAVE allCustomer; 
               END IF;
               BEGIN
                    DECLARE record_not_found INTEGER DEFAULT 0; 
                    DECLARE $username VARCHAR(1020) DEFAULT null;
                    DECLARE $accountId INTEGER DEFAULT 0;
                    DECLARE my_CustomerStudentDetails CURSOR FOR SELECT id,username FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) where custId=customerId and sp.studentAccountId is null and id in (select userId from UserRoles where roleId=3);   
                    DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
                    #select customerId;
                    OPEN my_CustomerStudentDetails;
                        custStudentDetails : LOOP
                            FETCH my_CustomerStudentDetails into $accountId,$username;
                            IF record_not_found THEN 
                                LEAVE custStudentDetails; 
                            END IF;
                            BEGIN
                                DECLARE $parentAccountId INTEGER DEFAULT 0;
                                DECLARE $parentUsername varchar(1020) DEFAULT NULL;
                                #SELECT $username;
                                set $parentUsername = CONCAT($username,'_p');
                                #SELECT $parentUsername;
                                set $parentAccountId =(select a.id from Account a where a.custId=customerId and a.username=$parentUsername);
                                if($parentAccountId !=0)then
                                    BEGIN
                                        update Account sa set sa.parentId=$parentAccountId where id=$accountId;
                                        set $parentAccountId=0;
                                    END;
                                end if;
                            END;
                        END LOOP custStudentDetails;
                    CLOSE my_CustomerStudentDetails;
               END;                       
       END LOOP allCustomer;    
   CLOSE my_cursor;
END $$    
DELIMITER ;

/* This procedure for updating the parent mobile number if the student has mobile number
call updateParentMobileNumbers(); 
 */
DROP procedure IF EXISTS `updateParentMobileNumbers`;

DELIMITER $$

CREATE PROCEDURE `updateParentMobileNumbers`()
BEGIN
    DECLARE record_not_found INTEGER DEFAULT 0;
    DECLARE personParentId, personId INTEGER DEFAULT 0;
    DECLARE parentMobile VARCHAR(255) DEFAULT '';
    DECLARE my_cursor CURSOR FOR SELECT a.id, p.id, p.mobileNumber FROM Person p LEFT JOIN Account a ON (p.id = a.personId) LEFT JOIN UserRoles ur ON(ur.userId = a.id) WHERE ur.roleId =7;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;
    OPEN my_cursor; 
        allCustomer: LOOP 
            FETCH my_cursor INTO personParentId, personId, parentMobile;
            IF record_not_found THEN 
                LEAVE allCustomer; 
           END IF;
            BEGIN
            DECLARE studentMobile VARCHAR(255) DEFAULT '';
            SET studentMobile = (SELECT v.mobileNumber FROM vw_allUsers v WHERE v.roleId = 3 AND v.mobileNumber IS NOT NULL AND v.parentId = personParentId LIMIT 1);

            IF studentMobile IS NOT NULL THEN

                IF RIGHT(studentMobile,10) <> RIGHT(parentMobile, 10) THEN
                    UPDATE Person SET mobileNumber = studentMobile WHERE id = personId;
                END IF;
            END IF;
            END;
       END LOOP allCustomer;   
   CLOSE my_cursor;
END

$$
DELIMITER ;

/* This procedure for updating the student ParentID if there are multiple students are having same parent mobile number. Making single parent to all students who are having same mobile number
 * 
 * call updateStudentDuplicateParentID(); */
DROP PROCEDURE IF EXISTS updateStudentDuplicateParentID;
DELIMITER $$

CREATE PROCEDURE `updateStudentDuplicateParentID`() 
BEGIN
    DECLARE record_not_found INTEGER DEFAULT 0; 
    DECLARE lstudentId INT DEFAULT 0;
    DECLARE lmobileNumber VARCHAR(200) DEFAULT '';
    DECLARE amobileNumber VARCHAR(200) DEFAULT '';
    DECLARE lparentId INT DEFAULT 0;
    DECLARE aparentId INT DEFAULT 0;
    DECLARE accountIdsList LONGTEXT DEFAULT '';
    DECLARE my_cursor CURSOR FOR SELECT accountId, RIGHT(mobileNumber, 10) AS mobileNumber ,parentId from vw_allUsers va where va.roleId = 3 and parentId != 0 and va.mobileNumber=(select mobileNumber from vw_allUsers v  where v.mobileNumber=va.mobileNumber limit 1) ORDER BY mobileNumber,accountId;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor; 
        allCustomer: LOOP 
            FETCH my_cursor INTO lstudentId, lmobileNumber, lparentId;
            IF record_not_found THEN 
                LEAVE allCustomer; 
           END IF;
           BEGIN
	            IF amobileNumber <> RIGHT(lmobileNumber, 10) THEN
                    SET amobileNumber = RIGHT(lmobileNumber, 10);
                    SET aparentId = lparentId;
                END IF;
               -- UPDATE Account a SET a.parentId = aparentId WHERE a.id = lstudentId;
               	UPDATE studentparent s SET s.parentAccountId = aparentId WHERE s.studentAccountId = lstudentId;
                SET accountIdsList = '';
                
           END;
        END LOOP allCustomer;   
   CLOSE my_cursor;
END$$
DELIMITER ;

/* This procedure for deleting the duplicate parent accounts from DB if there are multiple acccounts with same student parentIds
 * 
 * call deleteDuplicateStudentAccounts(); */
DROP PROCEDURE IF EXISTS deleteDuplicateStudentAccounts;
DELIMITER $$

CREATE PROCEDURE `deleteDuplicateStudentAccounts`()
BEGIN
    DECLARE record_not_found INTEGER DEFAULT 0; 
    DECLARE lparentId INT DEFAULT 0;
    DECLARE lmobileNumber VARCHAR(200) DEFAULT '';
    DECLARE lpersonId INT DEFAULT 0;
    DECLARE laddressId INT DEFAULT 0;
    DECLARE isStaff INT DEFAULT 0;
    DECLARE isExist INT DEFAULT 0;
    DECLARE my_cursor CURSOR FOR SELECT a.id, a.personId,a.paddressId from Account a LEFT JOIN UserRoles U ON(U.userId = a.id)
    where U.roleId = 7 ORDER BY a.id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor; 
        allCustomer: LOOP 
            FETCH my_cursor INTO lparentId, lpersonId, laddressId;
            IF record_not_found THEN 
                LEAVE allCustomer; 
           END IF;
           BEGIN
                SET isExist = ( select COUNT(*) from vw_allUsers v where v.roleId=3 and v.parentId = lparentId);
                SET isStaff = (SELECT COUNT(*) FROM staff s WHERE s.accountId = lparentId);
                
                #SELECT isExist, isStaff, lparentId, lpersonId, laddressId;
                
                IF isExist = 0 AND isStaff = 0 THEN
               # SELECT 1;
                    DELETE from UserRoles where userId=lparentId;
                    DELETE from replyScrapMessage where receiverAccountId=lparentId;
                    DELETE from replyScrapMessage where senderAccountId=lparentId;
                    DELETE from scrapMessage where receiverAccountId = lparentId;
                    DELETE from scrapMessage where senderAccountId = lparentId;
                    DELETE from circular where id=lparentId;
                    DELETE from circular where senderAccountId=lparentId;
                    DELETE from CircularUsers where userId=lparentId;
                    DELETE from messageDetailsTracking where accountId=lparentId;
                    DELETE from meetingSlots where accountId=lparentId;
                    DELETE from Account where id = lparentId;
                    DELETE from Person where id=lpersonId;
                    
                END IF;
                SET isExist = 0;
                SET isStaff = 0;
           END;
        END LOOP allCustomer;   
   CLOSE my_cursor;
END$$
DELIMITER ;

/* This procedure for updating parent accounts with mobile numbers
 * 
 * call update_usernames_for_parent(); */

DROP procedure IF EXISTS `update_usernames_for_parent`;

DELIMITER $$

CREATE PROCEDURE `update_usernames_for_parent`()
BEGIN
    DECLARE record_not_found INTEGER DEFAULT 0; 
    DECLARE userId, personId INT DEFAULT 0;
    DECLARE mobileNumber VARCHAR(100) DEFAULT '';
    DECLARE isStaff INT DEFAULT 0;
    DECLARE my_cursor CURSOR FOR SELECT a.id, p.mobileNumber, p.id FROM Account a left join Person p on a.personId=p.id left join UserRoles ur on ur.userId= a.id left join customer c on a.custId=c.id 
        where  p.mobileNumber is not null and p.mobileNumber<>'+91-0000000000' and c.id <> 47 and c.id <> 49  and c.id <> 50 and ur.roleId=7;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor; 
        allCustomer: LOOP 
            FETCH my_cursor INTO userId, mobileNumber, personId;
            IF record_not_found THEN 
                LEAVE allCustomer; 
           END IF;
           SET isStaff = (SELECT COUNT(*) FROM staff s WHERE s.accountId = userId);
           select userId, mobileNumber, personId;
           IF isStaff = 0 THEN
                UPDATE Account SET username = RIGHT(mobileNumber,10) WHERE id= userId;
                SET mobileNumber = '';
            END IF;   
        END LOOP allCustomer;   
   CLOSE my_cursor;
END
$$

CREATE  PROCEDURE staffHistoryCusotmerIdUpdate()
BEGIN
     DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE inc INTEGER DEFAULT 0;
   DECLARE $accountId INTEGER DEFAULT 0;
   DECLARE $custId INTEGER DEFAULT 0;
   DECLARE $staffHistoryId INTEGER DEFAULT 0;
       DECLARE my_cursor CURSOR FOR SELECT accountId,id FROM staffHistory WHERE custId=0;   
       DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor;  
               allStudents: LOOP 
                       FETCH my_cursor INTO $accountId,$staffHistoryId;
                       IF record_not_found THEN 
                       LEAVE allStudents; 
                       END IF;
                       select $accountId,$staffHistoryId;
                       if($accountId>0)then
                        
                        set $custId=(select custId from Account where id=$accountId);
                       UPDATE staffHistory SET custId=$custId WHERE id =$staffHistoryId;
                       
                       end if;
               END LOOP allStudents;    
   CLOSE my_cursor;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS getMarksIdByStudentIdAndExamScheduleId;    
DELIMITER $$
CREATE PROCEDURE getMarksIdByStudentIdAndExamScheduleId(IN $studentId bigint(20),IN $examScheduleId bigint(20))

BEGIN    

     DECLARE record_not_found INTEGER DEFAULT 0;     
     
     SET record_not_found=(SELECT  id FROM studentMarks WHERE studId = $studentId and examScheduleId=$examScheduleId  limit 1); 
     
     IF record_not_found > 0 THEN
       select record_not_found;
     ELSE
     select 0;
     END IF;
END $$    
DELIMITER ;


-- This procedure for when the userrole missing while creatingt the student, that time will run this procedure, then automatically will insert the userroles data
DELIMITER $$
CREATE  PROCEDURE studentUseRoleMissing()
BEGIN
     DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE inc INTEGER DEFAULT 0;
   DECLARE $accountId INTEGER DEFAULT 0;
   DECLARE $staffId INTEGER DEFAULT 0;
       DECLARE my_cursor CURSOR FOR SELECT accountId,id FROM student where accountId not in (select userId from UserRoles );   
       DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor;  
               allStudents: LOOP 
                       FETCH my_cursor INTO $accountId,$staffId;
                       IF record_not_found THEN 
                       LEAVE allStudents; 
                       END IF;
                       if($accountId>0) then 
                       		insert into UserRoles(userId,roleId) values  ($accountId,'3');
                       end if;
               END LOOP allStudents;    
   CLOSE my_cursor;
END $$
DELIMITER ;



DROP procedure IF EXISTS updateStduentFeePaidStatus;
DELIMITER $$
CREATE  PROCEDURE updateStduentFeePaidStatus()
BEGIN
   DECLARE customer_record_not_found INTEGER DEFAULT 0; 
    DECLARE customerId INTEGER DEFAULT 0;
    DECLARE $academicYearId INTEGER DEFAULT 0;
    DECLARE my_cursor CURSOR FOR SELECT id FROM customer where transportModuleStatus='Y' and id!=1;   
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET customer_record_not_found = 1;  
    OPEN my_cursor;  
        allCustomer: LOOP
            FETCH my_cursor INTO customerId;
            IF customer_record_not_found THEN 
                LEAVE allCustomer; 
            END IF;
            BEGIN
                SET $academicYearId = (select id from academicYear where custId=customerId and status='Y' and transportFeeByBoardingPoint='Y');
                -- select $academicYearId;
                IF $academicYearId > 0 then
                    BEGIN
                        DECLARE $boardingPointId INTEGER DEFAULT 0;
                        DECLARE $categoryId INTEGER DEFAULT 0;
                        DECLARE $studentId INTEGER DEFAULT 0;
                        DECLARE record_not_found INTEGER DEFAULT 0; 
                        DECLARE my_studentsList CURSOR FOR SELECT id,categoryId,boardingPointId  FROM student where custId=customerId and academicYearId=$academicYearId and boardingPointId!=0 and feePaidStatus='F' and description is null;
                        DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;  
                        OPEN my_studentsList;  
                            allStudents: LOOP
                                FETCH my_studentsList INTO $studentId,$categoryId,$boardingPointId;
                                IF record_not_found THEN 
                                    LEAVE allStudents; 
                                END IF;
                                BEGIN
                                    -- select $studentId,$categoryId,$boardingPointId;
                                    IF ($boardingPointId > 0 AND $studentId > 0 AND $categoryId > 0) then
                                    BEGIN
                                        DECLARE feeListCount INTEGER DEFAULT 0;
                                        set feeListCount = (SELECT count(*) FROM Fee where custId=customerId and academicYearId=$academicYearId and routeBoardingPointId=$boardingPointId and categoryId=$categoryId);
                                        if (feeListCount > 0) then 
                                            UPDATE student SET feePaidStatus='P' WHERE id=$studentId AND academicYearId = $academicYearId and custId=customerId; 
                                        END If;
                                    END;
                                 END IF;
                               END;
                            END loop allStudents;
                        CLOSE my_studentsList;
                    END;
                END IF;
            END;
        END loop allCustomer;
    CLOSE my_cursor;
END $$
DELIMITER ; 



DROP PROCEDURE IF EXISTS UpdateStudentRollNOs;
DELIMITER $$
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
   END $$    
DELIMITER ;

DROP PROCEDURE IF EXISTS ShiftStreetNameToAddressLine1;
DELIMITER $$
CREATE procedure ShiftStreetNameToAddressLine1() 
BEGIN    
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE $addressID INTEGER DEFAULT 0;
   DECLARE $addressLine1 VARCHAR(1024) DEFAULT '';
   DECLARE $streetName VARCHAR(1024) DEFAULT '';
       DECLARE my_cursor CURSOR FOR SELECT addressId,addressLine1,streetName FROM vw_studentClassDetails WHERE addressLine1 is null OR addressLine1 ='';   
       DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
   OPEN my_cursor;  
               allStudents: LOOP 
                       FETCH my_cursor INTO $addressID,$addressLine1,$streetName;
                       IF record_not_found THEN 
                       LEAVE allStudents; 
                       END IF;
                       IF ($streetName IS NOT NULL) THEN
                       		UPDATE Address SET addressLine1=$streetName WHERE id=$addressID;
                       	END IF;
               END LOOP allStudents;    
   CLOSE my_cursor;
   END $$    
DELIMITER ;

DROP procedure IF EXISTS GetPreSchoolStudentAttendanceSummary;
DELIMITER $$
CREATE PROCEDURE `GetPreSchoolStudentAttendanceSummary`(IN $acdYearId INT, IN $accountId INT, IN $type CHAR(1))
BEGIN
    DECLARE academicStartDate DATE DEFAULT CURDATE();
    DECLARE totalDays INT DEFAULT 0;
    DECLARE totalWorkingDays INT DEFAULT 0;
	
	SET academicStartDate = (SELECT startDate FROM academicYear where id = $acdYearId);
   		BEGIN
			DECLARE totalHolidaysDays INT DEFAULT 0;
			SET totalHolidaysDays = (SELECT COUNT(*) from schoolHolidays where academicYearId = $acdYearId AND DATE(holidayDate)<= CURDATE());
			SET totalDays = DATEDIFF(CURDATE(), academicStartDate);

			SELECT 
				s.id,s.id studentId,
				'' monthName,
				(totalDays - totalHolidaysDays) workingDays,
				IFNULL(COUNT(stuAtt.id), 0) present,
				(totalDays - totalHolidaysDays - IFNULL(COUNT(stuAtt.id), 0))  absent,
				0 presentPercentage
			FROM student s
			LEFT JOIN studentDailyAttendanceTimeTrack stuAtt ON stuAtt.studentId= s.id
			WHERE s.academicYearId = $acdYearId AND s.status='Y' AND IF($type = 'A' , TRUE, IF($type = 'C', s.accountId = $accountId, IF($type = 'P', (s.accountId IN (SELECT a.id FROM Account a WHERE a.parentId = $accountId)), (s.classSectionId IN (SELECT ct.studyClassId FROM classTeacher ct WHERE ct.teacherId IN(SELECT stt.id FROM staff stt WHERE stt.accountId = $accountId))))))
			GROUP BY s.id;
		END;
   END $$
DELIMITER ;


DROP procedure IF EXISTS sp_OverAllFeePaymentSummary;
DELIMITER $$
CREATE PROCEDURE sp_OverAllFeePaymentSummary(IN $customerId BIGINT(20),IN $academicYearId BIGINT(20))
BEGIN
	IF($academicYearId >0)then
		BEGIN
			declare feeAmount bigint(20) default 0;
            declare paidAmount bigint(20) default 0;
            declare discountAmount bigint(20) default 0;
            declare fineFeeAmount bigint(20) default 0;
            declare concessionAmount bigint(20) default 0;
            declare todayPaidAmount bigint(20) default 0;
            declare todayOtherFeeAmount bigint(20) default 0;
            declare transportFeeAmount bigint(20) default 0;
            declare refundFeeAmount bigint(20) default 0;
            declare paidFineAmount bigint(20) default 0;
            set feeAmount = (select sum(vscf.feeAmount)as feeAmount from vw_studentClassFees vscf where vscf.custId=$customerId and vscf.academicYearId=$academicYearId);
            set transportFeeAmount =(select sum(vscf.feeAmount)as feeAmount from vw_studentTransportFees vscf where vscf.custId=$customerId and vscf.academicYearId=$academicYearId);
            if(transportFeeAmount <> 0)then
				set feeAmount =(feeAmount+transportFeeAmount);
            end if;
			set paidAmount = (select sum(sp.paidAmount) as paidAmount from studentPayment sp where sp.academicYearId=$academicYearId and sp.deleteStatus='N' and concessionStatus='N');
            set todayPaidAmount = (select IFNULL(sum(sp.paidAmount),0) as paidAmount from studentPayment sp where sp.academicYearId=$academicYearId and Date(paymentDate)=current_date and sp.deleteStatus='N' and concessionStatus='N');
            set discountAmount =(select sum(sp.discountAmount) as discountAmount from studentPayment sp where sp.academicYearId=$academicYearId and sp.deleteStatus='N' and concessionStatus='N');
			set fineFeeAmount = (select sum(ff.fineFeeAmount) as fineFeeAmount from fineFee ff where ff.academicYearId=$academicYearId and ff.deleteStatus='N');
            set todayOtherFeeAmount = (select IFNULL(sum(ff.fineFeeAmount),0) as fineFeeAmount from fineFee ff where ff.academicYearId=$academicYearId and Date(paymentDate)=current_date and ff.deleteStatus='N');
			set concessionAmount = (select sum(sfc.concessionAmount) as concessionAmount from studentFeeConcession sfc where sfc.academicYearId=$academicYearId);
			set refundFeeAmount = (select sum(sfr.refundAmount) as refundAmount from studentFeeRefund sfr where sfr.academicYearId=$academicYearId);
			set paidFineAmount = (select IFNULL(sum(ib.paidFineAmount),0) as paidFineAmount from issuedBook ib where ib.custId=$customerId);
            select IFNULL(FORMAT(feeAmount,2,'en_IN'),0) as feeAmout,IFNULL(FORMAT(paidAmount,2,'en_IN'),0) as paidAmount,IFNULL(FORMAT(discountAmount,2,'en_IN'),0) as discountAmount,IFNULL(FORMAT(fineFeeAmount,2,'en_IN'),0) as fineFeeAmount,IFNULL(FORMAT(concessionAmount,2,'en_IN'),0) as concessionAmount,IFNULL(FORMAT(todayPaidAmount,2,'en_IN'),0) as todayPaidAmount,(FORMAT(feeAmount-(IFNULL(paidAmount,0)+IFNULL(discountAmount,0)+IFNULL(concessionAmount,0)),2,'en_IN')) as balanceAmount,IFNULL(FORMAT(todayOtherFeeAmount,2,'en_IN'),0) as todayOtherFeeAmount,IFNULL(FORMAT(refundFeeAmount,2,'en_IN'),0) as refundFeeAmount,IFNULL(FORMAT(paidFineAmount,2,'en_IN'),0) as paidFineAmount;
        END;
    END IF;
END $$
DELIMITER ;

DROP procedure IF EXISTS addSubTypeForCustomer;
DELIMITER $$
CREATE PROCEDURE addSubTypeForCustomer
BEGIN
   DECLARE _record_not_found INTEGER DEFAULT 0; 
   DECLARE _academicYearId INTEGER DEFAULT 0;
   DECLARE _customerId INTEGER DEFAULT 0;
   DECLARE my_cursor CURSOR FOR SELECT id FROM customer where status='Y';   
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET _record_not_found = 1;    
   OPEN my_cursor;  
      allCustomer: LOOP 
              FETCH my_cursor INTO _customerId;
              IF _record_not_found THEN 
              LEAVE allCustomer; 
              END IF;
              SET _academicYearId = (SELECT id FROM academicYear WHERE custId=_customerId and status='Y' limit 1);
              if(_academicYearId <> 0)THEN
               BEGIN
                   DECLARE subtypecount INTEGER DEFAULT 0;
                   DECLARE _Id int;
                   set _Id=(select ifnull(max(id),0) from subtype)+1;
                   SET subtypecount = (SELECT count(*) FROM subType s WHERE s.custId=_customerId and s.academicYearId=_academicYearId and s.subTypeName='Theory');
                   if(subtypecount =0 OR subtypecount='')THEN
                       INSERT INTO subType (id,createdbyId,createdDate,lastUpdatedDate,custId,schedule,subTypeName,academicYearId,predefinedSubType,sortingOrder) 
                       VALUES(_Id,1,now(),now(),_customerId,'Y','Theory',_academicYearId,'Y',1);
                  END IF;
               END; 
           END IF;
      END LOOP allCustomer;    
   CLOSE my_cursor;
END $$
DELIMITER 

-- This Procedure is used to get vehicle with driver Details 
CREATE PROCEDURE SP_VehicleWithDriverDetails($custId INT, $pickCurrentTerm boolean)
BEGIN
	select vad.id AS id,vad.id AS vehicleAcademicId,vad.academicYearId AS academicYearId, ifnull(v.noOfSeats, 0) noOfSeats, 
		IFNULL(pickup.pickupCount,0) filledPickUp, IFNULL(dropV.dropCount,0) filledDrop,
		ifnull(v.noOfSeats, 0)-IFNULL(pickup.pickupCount,0) AS availablePickup,
	    ifnull(v.noOfSeats, 0)-IFNULL(dropV.dropCount,0) AS availableDrop,
	    ay.id acdYearId,v.chasisNumber AS chasisNumber,
	    v.classificationType AS classificationType,v.custId AS custId,vad.driverId AS driverId,v.engineNumber AS engineNumber,
	    vad.helperId AS helperId,v.insuranceExpiredDate AS insuranceExpiredDate,v.insuranceNumber AS insuranceNumber,
	    v.ownerName AS ownerName,v.insurancePaidDate AS insurancePaidDate,v.vehicleMaker AS vehicleMaker,v.registrationAuthority AS registrationAuthority,
	    v.insuranceDetails AS insuranceDetails,ifnull(rv.routeId, 0) AS routeId,vad.status AS status,v.vehicleNumber AS vehicleNumber,v.vehicleType AS vehicleType,
	    v.roadTaxAmount AS roadTaxAmount,v.fitnessCheckDate AS fitnessCheckDate,v.fitnessExpiryDate AS fitnessExpiryDate,
	    v.permitCheckedDate AS permitCheckedDate,v.permitExpiryDate AS permitExpiryDate,
	    v.pollutionCheckedDate AS pollutionCheckedDate,v.pollutionExpiryDate AS pollutionExpiryDate,v.roadTaxNextPaymentDate AS roadTaxNextPaymentDate,
	    v.roadTaxPaidDate AS roadTaxPaidDate,ifnull(vad.name, 'None') AS name,
	    ifnull(concat(p.firstName, ' ', p.lastName), 'NA') AS driverName,ifnull(p.mobileNumber, '0') AS mobileNumber,
	    ifnull(r.routeName, 'NA') AS routeName,r.routePointStartTime AS routePointStartTime,r.routePointEndTime AS routePointEndTime,
	    r.routeEndTimeInMns AS routeEndTimeInMns,r.routeStartTimeInMns AS routeStartTimeInMns,
	    ay.status AS academicYearStatus,v.id AS vehicleId,ifnull(a.id, 0) AS accountId
	from vehicles v
	Left Join vehiclesAcademicDetails vad ON vad.vehicleId=v.id
	Left Join academicYear ay ON vad.academicYearId=ay.id
	left join Account a ON a.id=vad.driverId
	left join Person p ON(p.id = a.personId)
	left join RouteWithVehicles rv on rv.vehicleAcademicId=vad.id
	left Join route r ON r.id=rv.routeId
	left Join (
		select pickupVehicleId, COUNT(std.id) pickupCount,rbp.routeId from studentTransportDetails std 
	    left Join vehiclesAcademicDetails vd on std.pickupVehicleId=vd.id
	    left join routeBoardingPoints rbp on std.pickupBoardingPointId=rbp.id
	    WHERE IF(NOT $pickCurrentTerm,TRUE,termId in (select sst.id from schoolTerms sst where curDate() between sst.fromDate and sst.toDate) and 
			IF($custId=0, TRUE,std.custId=$custId))
	    Group By pickupVehicleId,rbp.routeId
	)pickup ON pickup.pickupVehicleId=vad.id and pickup.routeId=r.id
	left Join (
		select dropVehicleId, COUNT(std.id) dropCount,rbp.routeId from studentTransportDetails std 
	    left Join vehiclesAcademicDetails vd on std.dropVehicleId=vd.id
	    left join routeBoardingPoints rbp on std.dropBoardingPointId=rbp.id
	    WHERE  IF(NOT $pickCurrentTerm,TRUE,termId in (select sst.id from schoolTerms sst where curDate() between sst.fromDate and sst.toDate) and 
			IF($custId=0, TRUE,std.custId=$custId))
	    Group By dropVehicleId,rbp.routeId
	)dropV ON dropV.dropVehicleId=vad.id and dropV.routeId=r.id
	Where ay.status='Y' AND IF($custId=0, TRUE,v.custId=$custId)
	Group by v.id,r.id;
END

-- This Procedure used to get attendance not submitted dates
DROP procedure IF EXISTS `sp_AttendanceNotSubmittedDatesByMonths`;
DELIMITER $$
CREATE PROCEDURE `sp_AttendanceNotSubmittedDatesByMonths`($StrMonths VARCHAR(27), $AcdYearId INT, $StudyClassId INT)
BEGIN
    DECLARE _strMonths VARCHAR(27) DEFAULT $strMonths;
    DECLARE _acdYearId INT DEFAULT $AcdYearId;
    DECLARE _studyClassId INT DEFAULT $StudyClassId;
    
    DECLARE _next INT DEFAULT NULL;
    DECLARE _nextlen INT DEFAULT NULL;
    DECLARE _monthNo TEXT DEFAULT NULL;
    
    DECLARE _acdYearStartDate DATE;
    DECLARE _acdYearEndDate DATE;
    
    SET _acdYearStartDate= (SELECT DATE(startDate) FROM academicYear WHERE id=_acdYearId LIMIT 1);
    SET _acdYearEndDate= (SELECT DATE(endDate) FROM academicYear WHERE id=_acdYearId LIMIT 1);
    
    DROP TABLE IF EXISTS TEMP_AttendanceNotSubmittedDates;
    CREATE TEMPORARY TABLE TEMP_AttendanceNotSubmittedDates(MonthName VARCHAR(15),AttendanceDate VARCHAR(20)); 
    
    iterator:
    LOOP
        IF LENGTH(TRIM(_strMonths)) = 0 OR _strMonths IS NULL THEN
            LEAVE iterator;
        END IF;
        SET _next = SUBSTRING_INDEX(_strMonths,',',1);
        SET _nextlen = LENGTH(_next);
        SET _monthNo = TRIM(_next);
        BEGIN
            
            DECLARE _startDate DATE;
            DECLARE _endDate DATE;            
            DECLARE _selectedYear INT;
            
            SET _selectedYear = IF(_monthNo>=MONTH(_acdYearStartDate),YEAR(_acdYearStartDate),YEAR(_acdYearEndDate));
            SET _startDate = DATE(DATE_FORMAT(CONCAT(_selectedYear,'/',_monthNo,'/01'),'%y/%m/%d'));
            SET _endDate =  IF(LAST_DAY(_startDate) >CURDATE(),CURDATE(),IF(LAST_DAY(_startDate) >_acdYearEndDate,_acdYearEndDate,LAST_DAY(_startDate)));
            
            SET _startDate = IF(_startDate < _acdYearStartDate,_acdYearStartDate,_startDate);
            
            INSERT INTO TEMP_AttendanceNotSubmittedDates 
                SELECT DATE_FORMAT(tmp.dte,'%M'),DATE_FORMAT(tmp.dte,'%d-%b-%Y (%a)') FROM (SELECT _startDate + INTERVAL a + b DAY dte, DAYOFWEEK(_startDate + INTERVAL a + b DAY) week 
                    FROM (SELECT 0 a UNION SELECT 1 a UNION SELECT 2 UNION SELECT 3
                            UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7
                            UNION SELECT 8 UNION SELECT 9 ) d,
                         (SELECT 0 b UNION SELECT 10 UNION SELECT 20 
                            UNION SELECT 30 UNION SELECT 40) m
                    WHERE _startDate + INTERVAL a + b DAY  <=  _endDate AND FIND_IN_SET(DAYOFWEEK(_startDate + INTERVAL a + b DAY),(SELECT GROUP_CONCAT(DISTINCT(dayId)) FROM workingDays WHERE academicYearId =_acdYearId))
                    ORDER BY a + b) tmp
                    WHERE tmp.dte NOT IN(SELECT DATE(sda.attendanceDate) FROM staffDailyAttendanceSubmitTrack sda WHERE sda.academicYearId=_acdYearId  AND sda.classSectionId = _studyClassId)
                        AND tmp.dte NOT IN(SELECT DATE(sh.holidayDate) FROM schoolHolidays sh WHERE sh.academicYearId=_acdYearId)
                        GROUP BY tmp.dte;
        
        END;
        SET _strMonths = INSERT(_strMonths,1,_nextlen + 1,'');        
    END LOOP;
    
    SELECT * FROM TEMP_AttendanceNotSubmittedDates;
END$$

DELIMITER ;
 # CALL sp_AttendanceNotSubmittedDatesByMonths('4',310,6087);
 
 
 

