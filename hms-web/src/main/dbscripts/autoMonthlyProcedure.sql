DROP PROCEDURE IF EXISTS topStudentMarksDetails;
DELIMITER $$
CREATE PROCEDURE topStudentMarksDetails(in $custId BIGINT(20),in $academicYearId BIGINT(20))
BEGIN
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE studList INT DEFAULT 0;
   DECLARE studsList INT DEFAULT 0;
   DECLARE pstudId INT DEFAULT 0;
   DECLARE fstudId INT DEFAULT 0;
   DECLARE curDate VARCHAR(20) DEFAULT NULL;
   DECLARE my_cursor CURSOR FOR select s.id,failedStudents.studId failedStudentId from student s left Join (select studId, examTypeId,subjectId,SUM(totalSubjectMarksObtained) as Obtained, minMarks,maxMarks from vw_studentMarksDetails where custId=$custId  and academicYearId=$academicYearId and studDiscontinueDesc is null group By studId,examTypeId,subjectId 
  								 having minMarks>Obtained) failedStudents ON s.id=failedStudents.studId  where failedStudents.studId IS NULL and s.description is null group By s.id;
  	DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1; 
   SET studList =  (select count(*) from studentMarksPercentageCalc where custId=$custId and academicYearId=$academicYearId);
    IF studList > 0 then
  	 	delete from studentMarksPercentageCalc where custId=$custId and academicYearId=$academicYearId; 
  	END If;
  	SET studsList =  (select count(*) from topStudentMarksPercentageCal where custId=$custId and academicYearId=$academicYearId);
	 IF studsList > 0 then
  	 	delete from topStudentMarksPercentageCal where custId=$custId and academicYearId=$academicYearId; 
  	END If;
  	 set curDate = (select CURDATE());
     OPEN my_cursor;  
       allpassedStud: LOOP 
               FETCH my_cursor INTO pstudId,fstudId;
               IF record_not_found THEN 
               LEAVE allpassedStud; 
               END IF;
               BEGIN
                DECLARE _totalObtainedMarks DOUBLE DEFAULT 0;
                DECLARE _totalExamMarks DOUBLE DEFAULT 0; 
                DECLARE _percentage DOUBLE DEFAULT 0;  
                DECLARE _studentName  VARCHAR(50) DEFAULT NULL;
                DECLARE _admisNo  VARCHAR(50) DEFAULT NULL;
                DECLARE _classAndSection  VARCHAR(50) DEFAULT NULL;
                set _totalObtainedMarks =(select sum(sm.totalSubjectMarksObtained) as totalObtainedMarks from vw_studentMarksDetails sm where sm.custId=$custId and sm.academicYearId=$academicYearId and sm.studId=pstudId and sm.studDiscontinueDesc is null group By sm.studId);
                set _totalExamMarks =(select sum(sm.subTypeMaxMarks) as totalExamMarks from vw_studentMarksDetails sm where sm.custId=$custId and sm.academicYearId=$academicYearId and sm.studId=pstudId and sm.studDiscontinueDesc is null group By sm.studId);
                 if _totalExamMarks > 0 then
                    set _percentage = (_totalObtainedMarks / _totalExamMarks)*100; 
                    set _studentName = (select fullName from vw_studentDetails where custId=$custId and academicYearId=$academicYearId and studentId=pstudId and description is null);
                    set _admisNo =(select admissionNumber from vw_studentDetails where custId=$custId and academicYearId=$academicYearId and studentId=pstudId and description is null);
                    set _classAndSection = (select classNameAndSection from vw_studentDetails where custId=$custId and academicYearId=$academicYearId and studentId=pstudId and description is null);
                   insert into studentMarksPercentageCalc (studId,createdDate,examTypeId,totalStudentMarks,custId,academicYearId,studName,admisNo,classAndSection) values (pstudId,curDate,examTypeId,round(_percentage),$custId,$academicYearId,_studentName,_admisNo,_classAndSection);
                End if;
              END;
       END LOOP allpassedStud;    
   CLOSE my_cursor;
     INSERT INTO topStudentMarksPercentageCal (studId,createdDate,topPercentage,custId,academicYearId,studNames,admisNos,classAndSections) SELECT smpc.studId,curDate,round(AVG(smpc.totalStudentMarks)) as stuTotalMarks,smpc.custId,smpc.academicYearId,smpc.studName,smpc.admisNo,smpc.classAndSection FROM studentMarksPercentageCalc smpc group by smpc.studId order by stuTotalMarks desc,studName asc;
 END$$
 DELIMITER ;

DROP PROCEDURE IF EXISTS topTeacherPerformanceDetails;
DELIMITER $$
CREATE PROCEDURE topTeacherPerformanceDetails(in $custId BIGINT(20),in $academicYearId BIGINT(20))
BEGIN
   DECLARE record_not_found INT DEFAULT 0; 
   DECLARE _examTypeId INT DEFAULT 0;
   DECLARE staffList INT DEFAULT 0;
   DECLARE staffsList INT DEFAULT 0;
   DECLARE curDate VARCHAR(20) DEFAULT NULL;
   DECLARE my_cursor CURSOR FOR SELECT et.examTypeId FROM vw_studentMarksDetails et where et.custId=$custId and et.academicYearId=$academicYearId group by et.examTypeId;   
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;  
   SET staffList = (select count(*) from staffPefromancePercentageCalc where custId=$custId and academicYearId=$academicYearId);
    IF staffList > 0 then
  	 	delete from staffPefromancePercentageCalc where custId=$custId and academicYearId=$academicYearId; 
  	END If;
  	SET staffsList =  (select count(*) from topStaffPeromancePercentageCal where custId=$custId and academicYearId=$academicYearId);
	 IF staffsList > 0 then
  	 	delete from topStaffPeromancePercentageCal where custId=$custId and academicYearId=$academicYearId; 
  	END If;
  	set curDate = (select CURDATE());
   OPEN my_cursor;  
       allExamTypes: LOOP 
           FETCH my_cursor INTO _examTypeId;
           IF record_not_found THEN 
           LEAVE allExamTypes; 
           END IF;
            IF _examTypeId > 0 then
                BEGIN
                     DECLARE record_not_found INTEGER DEFAULT 0;  
				     DECLARE _studyClassId INTEGER DEFAULT 0;
				     DECLARE _studySubjectId INTEGER DEFAULT 0; 
				     DECLARE _staffId INTEGER DEFAULT 0;
				     DECLARE _staffName  VARCHAR(50) DEFAULT NULL;
				     DECLARE my_staffSubjectsAndClasses CURSOR FOR SELECT ct.studyClassId,ct.studySubjectId,ct.teacherId FROM classTeacher ct where ct.custId=$custId and ct.teacherId in(select id from staff where custId=$custId and description is null);
                     DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;    
                      OPEN my_staffSubjectsAndClasses;
                        allStudyClasses : LOOP                                           
                        FETCH my_staffSubjectsAndClasses into _studyClassId,_studySubjectId,_staffId;
                            IF record_not_found THEN 
                                LEAVE allStudyClasses; 
                            END IF;
                            BEGIN
					               DECLARE _percentage DOUBLE DEFAULT 0; 
					               DECLARE _passedCount INT DEFAULT 0; 
					               DECLARE _totalStudentsCount INT DEFAULT 0; 
					               IF _studySubjectId > 0 OR _studyClassId > 0  then 
					              		 set _passedCount = (select sum(v.totalSubjectMarksObtained) as totalSubObtained
											from vw_studentMarksDetails  v where v.examTypeId = _examTypeId and v.classSectionId = _studyClassId and v.subjectId = _studySubjectId
											  order by v.subjectId);
										set _totalStudentsCount =(select sum(v.subjectTotalMarks) as totalSubjectMarks
											from vw_studentMarksDetails  v where v.examTypeId = _examTypeId and v.classSectionId = _studyClassId and v.subjectId = _studySubjectId
											  order by v.subjectId);
										if _passedCount > 0 AND  _totalStudentsCount > 0 then
											set _percentage = (_passedCount /_totalStudentsCount)*100;
										else
										   set _percentage = 0;
										end if; 
										set _staffName = (select fullName from vw_staffDetails where custId=$custId and staffId=_staffId and  description is null group by staffId);
										insert into staffPefromancePercentageCalc (staffId,createdDate,examTypeId,totalStaffPerformance,custId,academicYearId,staffName) values (_staffId,curDate,_examTypeId,round(_percentage),$custId,$academicYearId,_staffName);
								end IF;
					         END;  
                        END LOOP allStudyClasses;
                    CLOSE my_staffSubjectsAndClasses;
                END;
            END IF;
        END LOOP allExamTypes;    
   CLOSE my_cursor;
     -- SELECT smpc.id,smpc.staffId,round(AVG(smpc.totalStaffPerformance)) as staffTotalMarks FROM staffPefromancePercentageCalc smpc group by smpc.staffId order by staffTotalMarks desc; 
    INSERT INTO topStaffPeromancePercentageCal (staffId,createdDate,topStaffPercentage,custId,academicYearId,staffNames) SELECT smpc.staffId,curDate,round(AVG(smpc.totalStaffPerformance)) as staffTotalMarks,smpc.custId,smpc.academicYearId,smpc.staffName FROM staffPefromancePercentageCalc smpc group by smpc.staffId order by staffTotalMarks desc,smpc.staffName asc;
    -- SELECT COUNT(*) FROM topstudentMarksPercentageCal group by topPercentage order by topPercentage DESC;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS topStudentsMonthlyAttendance;
DELIMITER $$
CREATE PROCEDURE topStudentsMonthlyAttendance(in $custId BIGINT(20),in $academicYearId BIGINT(20))
BEGIN
   DECLARE record_not_found INTEGER DEFAULT 0; 
   DECLARE _totalWorkingDayCount  Double DEFAULT 0.0;
   DECLARE _presentgDayCount  Double DEFAULT 0.0;
   DECLARE _percentage Double DEFAULT 0.0;
   DECLARE _studId INTEGER DEFAULT 0;
   DECLARE _studentName  VARCHAR(50) DEFAULT NULL;
   DECLARE _admNo  VARCHAR(50) DEFAULT NULL;
   DECLARE _classAndSection  VARCHAR(50) DEFAULT NULL;
   DECLARE studList INT DEFAULT 0;
   DECLARE studsList INT DEFAULT 0;
   DECLARE curDate VARCHAR(20) DEFAULT NULL;
   DECLARE my_cursor CURSOR FOR SELECT sum(st.totalWorkingDays) as totalDaysCount,sum(st.noOfPresentDays) as presentDays,st.studentId as studentId,st.StudentName as studentName,st.admissionNumber as admNo,st.classNameAndSection as classAndSection FROM vw_StudentMonthlyAttendance st where st.custId=$custId and st.academicYearId=$academicYearId and st.description is null group by studentId;   
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;  
   SET studList =  (select count(*) from studentMonthlyAttendancePercentageCal where custId=$custId and academicYearId=$academicYearId);
    IF studList > 0 then
  	 	delete from studentMonthlyAttendancePercentageCal where custId=$custId and academicYearId=$academicYearId;  
  	END If;
  	SET studsList =  (select count(*) from topStudentMonthlyAttendancePercentageCal where custId=$custId and academicYearId=$academicYearId);
	 IF studsList > 0 then
  	 	delete from topStudentMonthlyAttendancePercentageCal where custId=$custId and academicYearId=$academicYearId; 
  	END If;
   set curDate = (select CURDATE());
   OPEN my_cursor;  
       allstudentAtt: LOOP 
               FETCH my_cursor INTO _totalWorkingDayCount,_presentgDayCount,_studId,_studentName,_admNo,_classAndSection;
               IF record_not_found THEN 
               LEAVE allstudentAtt; 
               END IF;
               BEGIN
	               if _presentgDayCount > 0.0 AND  _totalWorkingDayCount > 0.0 then
	              	set _percentage = (_presentgDayCount/_totalWorkingDayCount)*100; 
	              insert into studentMonthlyAttendancePercentageCal (studId,createdDate,totalPercentage,studName,custId,academicYearId,admNo,classAndSection) values (_studId,curDate,round(_percentage,2),_studentName,$custId,$academicYearId,_admNo,_classAndSection);
	              end if;
               END; 
       END LOOP allstudentAtt;    
   CLOSE my_cursor;
    -- SELECT smpc.id,smpc.studId,round(AVG(smpc.totalPercentage)) as stuTotalMonthlyAtt,smpc.studName as stduentName FROM studentMonthlyAttendancePercentageCal smpc group by smpc.studId order by stuTotalMonthlyAtt desc; 
     INSERT INTO topStudentMonthlyAttendancePercentageCal (studId,createdDate,topStudentMonthlyAttendancePercentage,studsName,custId,academicYearId,admNos,classAndSections) SELECT smpc.studId,curDate,round(AVG(smpc.totalPercentage),2) as stuTotalMonthlyAtt,smpc.studName as StudentName,smpc.custId,smpc.academicYearId,smpc.admNo,smpc.classAndSection FROM studentMonthlyAttendancePercentageCal smpc group by smpc.studId order by stuTotalMonthlyAtt desc,StudentName ASC;
    -- SELECT COUNT(*) FROM topStudentMonthlyAttendancePercentageCal group by topStudentMonthlyAttendancePercentage order by topStudentMonthlyAttendancePercentage ,studsName DESC;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS topStudentsDailyAttendance;
DELIMITER $$
CREATE PROCEDURE topStudentsDailyAttendance(in $custId BIGINT(20),in $academicYearId BIGINT(20))
BEGIN
    DECLARE monthId INT DEFAULT 0;
    DECLARE studList INT DEFAULT 0;
    DECLARE studsList INT DEFAULT 0;
    DECLARE schoolWorkingDays Double DEFAULT 0.0;
    DECLARE academicStartDate varchar(20);
    DECLARE academicEndDate varchar(20);
    DECLARE startDateMonthId INT DEFAULT 0;
    DECLARE endDateMonthId INT DEFAULT 0;
    DECLARE startYear INT DEFAULT 0;
    DECLARE endYear INT DEFAULT 0;
    DECLARE x INT DEFAULT 0;
    DECLARE y INT DEFAULT 0;
    DECLARE attendanceYear INT DEFAULT 0;
    DECLARE curDate VARCHAR(20) DEFAULT NULL;
    set curDate = (select CURDATE());
    set academicStartDate = (select date(startDate) from academicYear where custId=$custId and status ='Y');
    set academicEndDate = (select date(endDate) from academicYear where custId=$custId and status ='Y');
    set startDateMonthId = (select month(academicStartDate));
    set endDateMonthId = (select month(academicEndDate));
    set startYear = (select year(academicStartDate));
    set endYear = (select year(academicEndDate));
    set monthId = startDateMonthId;
    set x = startDateMonthId;
    set y = month(now());
    IF startYear < endYear then
       set y = endDateMonthId+12;
    end if;
     set attendanceYear = startYear;
    SET studList =  (select count(*) from studentDailyAttendancePercentageCal where custId=$custId and academicYearId=$academicYearId);
    IF studList > 0 then
        delete from studentDailyAttendancePercentageCal where custId=$custId and academicYearId=$academicYearId; 
  	END If;
  	SET studsList = (select count(*) from topStudentDailyAttendancePercentageCal where custIds=$custId and academicYearIds=$academicYearId);
    IF studsList > 0 then
        delete from topStudentDailyAttendancePercentageCal where custIds=$custId and academicYearIds=$academicYearId; 
    END If;
    WHILE x <= y DO  
        BEGIN
            if monthId = startDateMonthId then
                SET schoolWorkingDays = (((select dayofmonth(last_day(academicStartDate))) - (select dayofmonth(academicStartDate)) + 1) - (select count(*) From schoolHolidays sh where sh.custId=$custId and sh.academicYearId=$academicYearId and sh.monthId=monthId and sh.yearId=startYear));
            elseif monthId = endDateMonthId then
                SET schoolWorkingDays = ((select dayofmonth(academicEndDate)) - (select count(*) From schoolHolidays sh where sh.custId=$custId and sh.academicYearId=$academicYearId and sh.monthId=monthId and sh.yearId=endYear));
            else 
                SET schoolWorkingDays = ((select dayofmonth(last_day(academicStartDate))) - (select count(*) From schoolHolidays sh where sh.custId=$custId and sh.academicYearId=$academicYearId and sh.monthId=monthId));
            end if;
        END;       
        BEGIN
            DECLARE record_not_found INTEGER DEFAULT 0; 
            DECLARE _studId INTEGER DEFAULT 0;
            DECLARE _percentage Double DEFAULT 0.0;
            DECLARE studentAbsentCount Double DEFAULT 0.0;
            DECLARE studentPresentCount Double DEFAULT 0.0;
            DECLARE studentClassId INT DEFAULT 0;
            DECLARE _studentName  VARCHAR(50) DEFAULT NULL;
   	        DECLARE _admNo  VARCHAR(50) DEFAULT NULL;
            DECLARE _classAndSection  VARCHAR(50) DEFAULT NULL;
            DECLARE afternoonAbsentCount INT DEFAULT 0;
            DECLARE morningAbsentCount INT DEFAULT 0;
            DECLARE my_cursor CURSOR FOR SELECT st.id as studentId,st.classSectionId  FROM student st where st.custId=$custId and st.academicYearId=$academicYearId and st.description is null group by st.id ;
            DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;  
            OPEN my_cursor;  
			      allStudents: LOOP 
                FETCH my_cursor INTO _studId,studentClassId;
                IF record_not_found THEN 
                    LEAVE allStudents; 
                END IF;
                	-- select count(*) from vw_StudentDailyAttendance vsda where vsda.custId=2 and vsda.academicYearId=1 and vsda.month=8 and vsda.present='N' and vsda.studentId=3275 and vsda.classSectionId=37 and vsda.year=2016 group by vsda.studentId;
                    set morningAbsentCount = (select count(*) from vw_StudentDailyAttendance vsda where vsda.custId=$custId and vsda.academicYearId=$academicYearId and vsda.month=monthId and vsda.present='N' and vsda.studentId=_studId and vsda.classSectionId=studentClassId and vsda.year=attendanceYear group by vsda.studentId);
                    set afternoonAbsentCount = (select count(*) from vw_StudentDailyAttendance vsda where vsda.custId=$custId and vsda.academicYearId=$academicYearId and vsda.month=monthId and vsda.afternoonSession='N' and vsda.studentId=_studId and vsda.classSectionId=studentClassId and vsda.year=attendanceYear group by vsda.studentId);
                    if afternoonAbsentCount >0 then
                        set afternoonAbsentCount = afternoonAbsentCount;
                     else
                        set afternoonAbsentCount = 0;
                    end if;
                    if morningAbsentCount > 0 then
                        set morningAbsentCount = morningAbsentCount;
                     else
                        set morningAbsentCount = 0;
                    end if;
                    set studentAbsentCount = (morningAbsentCount+afternoonAbsentCount)/2;
                    -- select morningAbsentCount,afternoonAbsentCount,monthId,studentAbsentCount,_studId,schoolWorkingDays;
                    
                    IF studentAbsentCount > 0.0 then
                        set studentPresentCount = schoolWorkingDays - studentAbsentCount;
                    ELSE 
                        set studentPresentCount = schoolWorkingDays;
                    END IF;
                    IF studentPresentCount > 0.0 AND  schoolWorkingDays > 0.0 then
	                    set _percentage = (studentPresentCount/schoolWorkingDays)*100;
	                    set _studentName = (select fullName from vw_studentDetails where custId=$custId and academicYearId=$academicYearId and studentId=_studId and description is null);
	                    set _admNo =(select admissionNumber from vw_studentDetails where custId=$custId and academicYearId=$academicYearId and studentId=_studId and description is null);
	                    set _classAndSection = (select classNameAndSection from vw_studentDetails where custId=$custId and academicYearId= $academicYearId and studentId=_studId and description is null);
	                    insert into studentDailyAttendancePercentageCal(studId,createdDate,studyClassId,academicYearId,custId,stuAbsentCount,stuPresentCount,totalWorkingDays,monthId,totalPercentage,studName,admisNo,classAndSection)
	                            values(_studId,curDate,studentClassId,$academicYearId,$custId,studentAbsentCount,studentPresentCount,schoolWorkingDays,monthId,ROUND(_percentage, 2),_studentName,_admNo,_classAndSection);
                    END IF;        
                END LOOP allStudents;
		       CLOSE my_cursor;
        END;
        set x = x+1;
        BEGIN
            IF x = 13 then
                set monthId = 1;
                set attendanceYear = endYear;
            ELSE
                set monthId = monthId+1;
            END IF;
        END;
        set academicStartDate = (select date_add(academicStartDate, interval 1 month));
    END WHILE;
    -- SELECT smpc.id,smpc.studId,round(AVG(smpc.totalPercentage)) as stuTotalMonthlyAtt,smpc.studyClassId as stuTotalDailyAtt,smpc.academicYearId,smpc.custId,smpc.stuAbsentCount,smpc.stuPresentCount,smpc.totalWorkingDays,smpc.monthId,smpc.totalPercentage FROM studentDailyAttendancePercentageCal smpc group by smpc.studId order by stuTotalDailyAtt desc; 
    INSERT INTO topStudentDailyAttendancePercentageCal (studId,createdDate,studyClassIds,academicYearIds,custIds,stuAbsentCounts,stuPresentCounts,totalWorkingDayss,monthIds,topTotalPercentage,studNames,admisNos,classAndSections)
    SELECT smpc.studId,curDate,smpc.studyClassId,smpc.academicYearId,smpc.custId,smpc.stuAbsentCount,smpc.stuPresentCount,smpc.totalWorkingDays,smpc.monthId,round(AVG(smpc.totalPercentage),2) as stuTotalDailyAtt,smpc.studName,smpc.admisNo,smpc.classAndSection FROM studentDailyAttendancePercentageCal smpc group by smpc.studId order by stuTotalDailyAtt desc,studName asc;
    -- SELECT COUNT(*) FROM topStudentDailyAttendancePercentageCal group by topTotalPercentage order by topTotalPercentage DESC;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS customerWiseCollectionAndPendingAmountDetailsSummaryMonthWise;
DELIMITER $$
CREATE PROCEDURE customerWiseCollectionAndPendingAmountDetailsSummaryMonthWise()
BEGIN
   DECLARE record_not_found INT DEFAULT 0; 
   DECLARE $customerId INT DEFAULT 0;
   DECLARE customerList CURSOR FOR SELECT id FROM customer where id not in (1) and id not in (select custId from userAutoReportsConfiguration where status='Y' and id=2); 
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1; 
   drop TEMPORARY table IF EXISTS feedetailssummary;
   CREATE TEMPORARY TABLE feedetailssummary (id INT NOT NULL AUTO_INCREMENT,custId bigint(20),academicYearId bigint(20),feeAmount DECIMAL(15,2) DEFAULT 0,paymentAmount DECIMAL(15,2) DEFAULT 0,pendingAmount DECIMAL(15,2) DEFAULT 0,configureStatus varchar(1) default 'N',primary key (id));
   -- delete from feeDetailsSummary;
   OPEN customerList;  
       allCustomerDetails: LOOP 
           FETCH customerList INTO $customerId;
           IF record_not_found THEN 
           LEAVE allCustomerDetails; 
           END IF;
           BEGIN
                DECLARE $academicYearId INT DEFAULT 0;
                DECLARE $totalFeeAmount DOUBLE DEFAULT 0;
                DECLARE $totalPaymentAmount DOUBLE DEFAULT 0;
                DECLARE $configureFeeAmount DOUBLE DEFAULT 0;
                DECLARE $configureStatus varchar(1) DEFAULT 'N';
                set $academicYearId =(select id from academicYear where custId=$customerId and status='Y');
                if($academicYearId>0)then
                    BEGIN
                        set $configureFeeAmount =(select sum(feeAmount) from vw_studentFeePaymentDetails where custId=$customerId and academicYearId=$academicYearId and description is null);
                        set $totalFeeAmount =(select sum(feeAmount) from vw_studentFeePaymentDetails where custId=$customerId and academicYearId=$academicYearId and fromDate <= CURDATE() and description is null);
                        set $totalPaymentAmount=(select sum(paymentAmount) from vw_studentFeePaymentDetails where custId=$customerId and academicYearId=$academicYearId );
                        -- select $customerId,$academicYearId,$totalFeeAmount,$totalPaymentAmount,($totalFeeAmount-$totalPaymentAmount);
                        
                         BEGIN
                            if($configureFeeAmount > 0)then
                                set $configureStatus='Y';
                            END IF;
                            insert into feedetailssummary (custId,academicYearId,feeAmount,paymentAmount,pendingAmount,configureStatus) 
                            values ($customerId,$academicYearId,IFNULL($totalFeeAmount,0),IFNULL($totalPaymentAmount,0),IFNULL($totalFeeAmount-IFNULL($totalPaymentAmount,0),0),$configureStatus);
                            set $totalFeeAmount=0;
                            set $totalPaymentAmount=0;
                         END;
                        
                        
                    END;
                END IF;
           END;
        END LOOP allCustomerDetails;    
   CLOSE customerList;
   select * from feedetailssummary;
END$$
DELIMITER ;
