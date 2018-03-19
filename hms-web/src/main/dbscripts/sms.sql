grant all on sms.* to eschool@localhost identified by 'eschool';

drop table vw_classSection;
drop table vw_ProductDetailsByCustomer;
drop table vw_staffDetails;
drop table vw_staffLeaveDetails;
drop table vw_staffVehicleTripDetails;
drop table vw_studentDetails;
drop table vw_studentLeaveDetails;
drop table vw_studentAttendanceDetails;
drop table vw_staffSubjectsDetails;
drop table vw_buildingfloordetails;
drop table vw_buildingmenuitemsdetails;
drop table vw_hostelbuildingdetails;
drop table vw_issuedbookandsettings;
drop table vw_roomdetails;
drop table vw_staffattendance;
drop table vw_studentattendance;



insert into State (id,StateCode,stateName,version) values(1,'AP','Andhra Pradesh',0);
insert into State (id,StateCode,stateName,version) values(2,'AR','Arunachal Pradesh',0);
insert into State (id,StateCode,stateName,version) values(3,'AS','Assam',0);
insert into State (id,StateCode,stateName,version) values(4,'BR','Bihar',0);
insert into State (id,StateCode,stateName,version) values(5,'CG','Chattisgarh',0);
insert into State (id,StateCode,stateName,version) values(6,'GA','Goa',0);
insert into State (id,StateCode,stateName,version) values(7,'GJ','Gujarat',0);
insert into State (id,StateCode,stateName,version) values(8,'HR','Haryana',0);
insert into State (id,StateCode,stateName,version) values(9,'HP','Himachal Pradesh',0);
insert into State (id,StateCode,stateName,version) values(10,'JK','Jammu & Kashmir',0);
insert into State (id,StateCode,stateName,version) values(11,'JH','Jharkhand',0);
insert into State (id,StateCode,stateName,version) values(12,'KA','Karnataka',0);
insert into State (id,StateCode,stateName,version) values(13,'KL','Kerala',0);
insert into State (id,StateCode,stateName,version) values(14,'MP','Madhya Pradesh',0);
insert into State (id,StateCode,stateName,version) values(15,'MH','Maharashtra',0);
insert into State (id,StateCode,stateName,version) values(16,'MN','Manipur',0);
insert into State (id,StateCode,stateName,version) values(17,'ML','Meghalaya',0);
insert into State (id,StateCode,stateName,version) values(18,'MZ','Mizoram',0);
insert into State (id,StateCode,stateName,version) values(19,'NL','Nagaland',0);
insert into State (id,StateCode,stateName,version) values(20,'OR','Orissa',0);
insert into State (id,StateCode,stateName,version) values(21,'PB','Punjab',0);
insert into State (id,StateCode,stateName,version) values(22,'RJ','Rajasthan',0);
insert into State (id,StateCode,stateName,version) values(23,'SK','Sikkim',0);
insert into State (id,StateCode,stateName,version) values(24,'TN','Tamil Nadu',0);
insert into State (id,StateCode,stateName,version) values(25,'TR','Tripura',0);
insert into State (id,StateCode,stateName,version) values(26,'UK','Uttaranchal',0);
insert into State (id,StateCode,stateName,version) values(27,'UP','Uttar Pradesh',0);
insert into State (id,StateCode,stateName,version) values(28,'WB','West Bengal',0);
insert into State (id,StateCode,stateName,version) values(29,'AN','Andaman & Nicobar',0);
insert into State (id,StateCode,stateName,version) values(30,'CH','Chandigarh',0);
insert into State (id,StateCode,stateName,version) values(31,'DN','Dadra and Nagar Haveli',0);
insert into State (id,StateCode,stateName,version) values(32,'DD','Daman & Diu',0);
insert into State (id,StateCode,stateName,version) values(33,'DL','Delhi',0);
insert into State (id,StateCode,stateName,version) values(34,'LD','Lakshadweep',0);
insert into State (id,StateCode,stateName,version) values(35,'PY','Puducherry',0);
insert into State (id,StateCode,stateName,version) values(36,'NA','---------------------',0);

 
 
insert into Developers (id,Status,emailId,firstName,version) values(1,'A','ganesh@uroomtech.com','Ganesh',0);
insert into Developers (id,Status,emailId,firstName,version) values(2,'A','seshu@uroomtech.com','Seshu',0);
insert into Developers (id,Status,emailId,firstName,version) values(3,'A','venkatesh@uroomtech.com','Venkatesh',0);
insert into Developers (id,Status,emailId,firstName,version) values(4,'A','balu@uroomtech.com','Balu',0);
insert into Developers (id,Status,emailId,firstName,version) values(5,'A','venkat@uroomtech.com','Venkat',0);
insert into Developers (id,Status,emailId,firstName,version) values(6,'A','madhu@uroomtech.com','Madhu',0); 
===============================sample Account =========================================================================


INSERT INTO Role(id,version,name,description) VALUES
(1,1,'ROLE_ADMIN','Administrator'),
(2,1,'ROLE_TEACHER','Teacher'),
(3,1,'ROLE_STUDENT','Student'),
(4,1,'ROLE_CLERK','Clerk'),
(5,1,'ROLE_DRIVER','Driver'),
(6,1,'ROLE_HELPER','Helper'),
(7,1,'ROLE_PARENT','Parent'),
(8,1,'ROLE_HOD','Hod'),
(9,1,'ROLE_LIBRARIAN','Librarian'),
(10,1,'ROLE_TRANSPORT','Transport'),
(11,1,'ROLE_FINANCE','Finance'),
(12,1,'ROLE_PRINCIPAL','Principal'),
(13,1,'ROLE_MASTERADMIN','MASTERADMIN');
(14,1,'ROLE_HOSTEL','HOSTEL');
(15,1,'ROLE_HOSTELFINANCE','HOSTELFINANCE');

===================================Seshu 17/01/2011     Not Required ===================================================
insert into academicYear(id,custId,academicYear,version,status,pastYear) values
(1,1,'2010-11',1,'Y','2010'),
(2,1,'2011-12',1,'N','2011'),
(3,1,'2012-13',1,'N','2012'),
(4,1,'2013-14',1,'N','2013'),
(5,1,'2014-15',1,'N','2014'),
(6,1,'2015-16',1,'N','2015'),
(7,1,'2016-17',1,'N','2016'),
(8,1,'2017-18',1,'N','2017'),
(9,1,'2018-19',1,'N','2018'),
(10,1,'2019-20',1,'N','2019'),
(11,1,'2020-21',1,'N','2020'),
(12,1,'2021-22',1,'N','2021'),
(13,1,'2022-23',1,'N','2022'),
(14,1,'2023-24',1,'N','2023'),
(15,1,'2024-25',1,'N','2024');

INSERT INTO studySubject(id,version,custId,description,name,academicYearId) VALUES
(1,1,1,'English','ENGLISH',1),
(2,1,1,'Hindi','HINDI',1),
(3,1,1,'Telugu','TELUGU',1),
(4,1,1,'Maths','MATHEMATICS',1),
(5,1,1,'Sanskrit','SANSKRIT',1),
(6,1,1,'Science','SCIENCE',1),
(7,1,1,'Social','SOCIAL',1),
(8,1,1,'Physical Science','PHYSICAL SCIENCE',1),
(9,1,1,'Natural Science','NATURAL SCIENCE',1),
(10,1,1,'Economics','ECONOMICS',1),
(11,1,1,'Geography','GEOGRAPHY',1),
(12,1,1,'Civics','CIVICS',1),
(13,1,1,'History','HISTORY',1);

---------------------Not required----------------

INSERT INTO class(id,version,className,custId,description,academicYearId) VALUES
(1,1,'Pre-Kg',1,'Pre-Kg',1),
(2,1,'LKG',1,'LKG',1),
(3,1,'UKG',1,'UKG',1),
(4,1,'I',1,'First Class',1),
(5,1,'II',1,'Second Class',1),
(6,1,'III',1,'Third Class',1),
(7,1,'IV',1,'Fourth Class',1),
(8,1,'V',1,'Fifth Class',1),
(9,1,'VI',1,'Sixth Class',1),
(10,1,'VII',1,'Seventh Class',1),
(11,1,'VIII',1,'Eighth Class',1),
(12,1,'IX',1,'Nineth Class',1),
(13,1,'X',1,'Tenth Class',1),
(14,1,'XI',1,'Eleventh Class',1),
(15,1,'XII',1,'Twelveth Class',1);

--------------------------   Sections ------------

INSERT INTO section(id,version,section,custId) VALUES
(1,1,'A',1),
(2,1,'B',1),
(3,1,'C',1),
(4,1,'D',1);

--------------------------For Master Admin Login ------------------
insert into customer(id,version,custEmail,customerName, organization) values(1,0,'support@uroomtech.com','Support','Upper Room Technologies');

INSERT INTO `Person` (`id`,`createdBy`,`createdDate`,`lastAccessDate`,`lastUpdatedBy`,`lastUpdatedDate`,`version`,`citizenship`,`studyClass`,`dateOfBirth`,`firstName`,`gender`,`height`,`lastName`,`maritalStatus`,`middleName`,`mothersMaidenName`,`passwordHint`,`passportExpireDate`,`passportNumber`,`personalTitle`,`socialSecurityNumber`,`suffix`,`weight`,`bloodGroup`,`experience`,`licenseExpDate`,`licenseNumber`,`mobileNumber`,`parentEmail`,`phoneNumber`,`fatherName`,`motherName`,`dateOfJoining`,`roleName`) VALUES 
 (1,NULL,'2010-10-08 19:31:46',NULL,NULL,'2010-10-08 19:31:46',0,NULL,NULL,'10/15/1985','Admin','M',0,'Admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'a+',0,NULL,NULL,NULL,'support@uroomtech.com','9962030607',NULL,NULL,NULL,NULL);

insert into Account(id,version,password,username,personId,custId) values(1,0,'eee417ade1aaabe13200a63d02c841130be82051','support@uroomtech.com',1,1);

insert into UserRoles(roleId,userId) values (13,1);

---------------------------For School Admin ------------------------ 

insert into customer(id,version,custEmail,customerName, organization) values(2,1,'admin@urt.com','SRI Saraswathi Vidya Mandir','SRI SRI SRI MOOKAMBIKA EDUCATIONAL SOCIETY');

INSERT INTO `Person` (`id`,`createdBy`,`createdDate`,`lastAccessDate`,`lastUpdatedBy`,`lastUpdatedDate`,`version`,`citizenship`,`studyClass`,`dateOfBirth`,`firstName`,`gender`,`height`,`lastName`,`maritalStatus`,`middleName`,`mothersMaidenName`,`passwordHint`,`passportExpireDate`,`passportNumber`,`personalTitle`,`socialSecurityNumber`,`suffix`,`weight`,`bloodGroup`,`experience`,`licenseExpDate`,`licenseNumber`,`mobileNumber`,`parentEmail`,`phoneNumber`,`fatherName`,`motherName`,`dateOfJoining`,`roleName`) VALUES 
 (2,NULL,'2010-10-08 19:31:46',NULL,NULL,'2010-10-08 19:31:46',0,NULL,NULL,'10/15/1985','Admin','M',0,'Admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'a+',0,NULL,NULL,NULL,'admin@gmail.com','9962030607',NULL,NULL,NULL,NULL);

insert into Account(id,version,password,username,personId,custId) values(2,0,'eee417ade1aaabe13200a63d02c841130be82051','oakadmin@urt.com',2,2);

insert into UserRoles(roleId,userId) values (1,2);
insert into staff (id,createdDate,version,casualLeaves,sickLeaves,status,accountId,custId,totalLeaves,academicYearId) values(07,'2010-11-30 17:00:50','1',10,20,'A',2,2,30,1);


========================================================================================================
drop view vw_userRoles;          
          
CREATE OR REPLACE VIEW vw_userRoles 
AS select a.id id,a.id accountId,a.custId custId,a.username,a.createdBy, a.createdDate,a.lastAccessDate,a.lastUpdatedBy,a.lastUpdatedDate,a.version, ur.roleId,r.name AS roleName,r.description 
AS roleDescription,p.firstName,p.lastName 
from UserRoles ur  LEFT JOIN Account a  on (ur.userId=a.id) LEFT JOIN Person p  on (a.personId=p.id) LEFT JOIN Role r on (ur.roleId=r.id);


========================================Narahari 03/11/2010 ==============================================
create 
or replace view vw_studentDetails as
SELECT a.id id,a.id accountId,a.custId, a.username,a.parentId, a.version, a.sharePrivacy, a.userOnlineNow, s.id studentId, s.academicYearId, s.createdDate, s.lastAccessDate, s.status,s.classId,s.classNameClassId,s.rollNumber,p.firstName,s.boardingPoint, p.lastName, p.middleName, p.fatherName, p.motherName, p.mothersMaidenName,p.dateOfBirth,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.parentEmail,p.summary,p.dateOfJoining,d.addressLine1,d.addressLine2,d.city,d.state,d.postalCode,a.lastUpdatedBy, a.lastUpdatedDate, a.createdBy,
c.className,c.section,ur.roleName,ur.roleDescription,ur.roleId,ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail
FROM student s  JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id) LEFT JOIN  studyClass c on (c.id=s.classId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN UserImage ui on (ui.id=a.imageId);

create 
or replace view vw_staffDetails as
SELECT a.id id,a.id accountId,a.custId, a.username,  a.version, a.sharePrivacy, a.userOnlineNow, s.id staffId, s.academicYearId,s.qualification1,s.qualification2, s.totalLeaves, s.casualLeaves, s.sickLeaves, s.createdDate, s.lastAccessDate, s.status, s.supervisorId,s.staffType, p.licenseNumber,p.licenseExpDate,p.experience,p.dateofJoining,p.firstName, p.lastName,p.middleName,p.dateOfBirth,p.bloodGroup,p.mobileNumber,p.phoneNumber,p.maritalStatus,p.summary,p.gender,p.familyDoctor,p.prefferedHospital,d.addressLine1,d.addressLine2,d.city,d.state,d.postalCode,d.email,a.lastUpdatedBy, a.lastUpdatedDate, a.createdBy ,
ur.roleId,ur.roleName,ur.roleDescription,ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail
FROM staff s LEFT JOIN Account a on (s.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id) LEFT JOIN  vw_userRoles ur on (a.id=ur.accountId) LEFT JOIN UserImage ui on (ui.id=a.imageId);

create 
or replace view vw_staffLeaveDetails
as select l.id id,s.id accountId,s.custId, s.username, s.version, s.id staffId, s.supervisorId, s.academicYearId, s.qualification1,s.qualification2, s.casualLeaves, s.sickLeaves, s.createdDate, s.lastAccessDate, s.status,s.firstName, s.lastName,s.middleName,s.lastUpdatedBy, s.lastUpdatedDate, s.createdBy, s.totalLeaves,s.summary,s.dateOfJoining,
l.id leavesId,l.description,l.startDate,l.endDate,l.leaveStatus,l.leaveType,l.days,l.startTime,l.endTime,ur.roleName,l.supervisorId leaveSupervisorId,ur.roleDescription,ui.id imageId,ur.roleId,ui.name imageName,ui.path imagePath,ui.thumbNail
FROM vw_staffDetails s  JOIN leaves l on (s.accountId=l.accountId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN UserImage ui on (ui.id=s.imageId);


========================================Narahari 04/11/2010 ==============================================
create 
or replace view vw_studentLeaveDetails as
SELECT l.id id,s.id accountId,s.custId, s.username,s.parentId,s.version, s.sharePrivacy, s.userOnlineNow, s.id studentId, s.academicYearId, s.createdDate, s.lastAccessDate, s.status,s.classId,s.rollNumber,s.firstName,s.boardingPoint,s.lastName, s.middleName, s.fatherName, s.motherName, s.mothersMaidenName,s.dateOfBirth,s.bloodGroup,s.mobileNumber,s.phoneNumber,s.addressLine1,s.addressLine2,s.city,s.state,s.postalCode,s.lastUpdatedBy, s.lastUpdatedDate, s.createdBy,s.parentEmail,s.summary,s.dateOfJoining,s.section,
s.className,ur.roleName,ur.roleId,l.id leavesId,l.leaveStatus,l.leaveType,l.startTime,l.endTime,l.description,l.days,l.startDate,l.endDate,l.supervisorId leaveSupervisorId,ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail
FROM vw_studentDetails s  JOIN leaves l on (s.accountId=l.accountId) LEFT JOIN vw_userRoles ur on (ur.accountId=s.accountId) LEFT JOIN UserImage ui on (ui.id=s.imageId);

========================================Venkatesh 28/10/2010==============================================
create or replace view vw_staffVehicleTripDetails as
	SELECT vt.id ,sd.accountId,sd.custId, sd.username, sd.version, sd.sharePrivacy, sd.userOnlineNow, sd.staffId, sd.academicYearId, sd.qualification1, sd.totalLeaves, sd.casualLeaves, sd.sickLeaves, sd.createdDate, sd.lastAccessDate, sd.status, sd.licenseNumber, sd.licenseExpDate, sd.firstName, sd.lastName, sd.middleName,sd.dateOfJoining, sd.dateOfBirth, sd.bloodGroup, sd.mobileNumber, sd.phoneNumber, sd.addressLine1, sd.addressLine2, sd.city, sd.state, sd.postalCode, sd.lastUpdatedBy, sd.lastUpdatedDate, sd.createdBy, sd.roleId, sd.roleName,
	 vt.driverAccountId,vt.helperAccountId,vt.vehicleId,vt.status vehicleStatus ,vt.startPoint,vt.endPoint,vt.tripNum,vt.routeNumber,vt.shh1,vt.ehh2,vt.emm2,vt.amPm7,vt.amPm8,vt.smm1,vb.amPm1,vb.amPm2,vb.amPm3,vb.amPm4,vb.amPm5,vb.amPm6,vb.hh1,vb.hh2,vb.hh3,vb.hh4,vb.hh5,vb.hh6 ,vb.mm1,vb.mm2,vb.mm3,vb.mm4,vb.mm5,vb.mm6 ,vb.stop1,vb.stop2,vb.stop3,vb.stop4,vb.stop5,vb.stop6,vb.vehicleTripId
	FROM vehicleTrip vt LEFT JOIN vw_staffDetails sd on (vt.driverAccountId=sd.accountId or vt.helperAccountId=sd.accountId) LEFT JOIN boardingPoints vb on (vt.id=vb.vehicleTripId);
========================================Venkatesh---New--- 03/11/2010==============================================  
create 
or replace view vw_staffVehicleTripDetails as
	SELECT vt.id ,d.custId,d.userName as driverUsername, d.staffId as driverStaffId,d.qualification1 as driverQualification1 ,d.mobileNumber as driverMobileNumber,d.phoneNumber as driverPhoneNumber,d.licenseNumber as driverLicenseNumber,d.licenseExpDate as diverLicenseExpDate,d.firstName as driverFristName,d.lastName as driverLastName,d.middleName as driverMiddleName,d.dateOfJoining as driverDateOfJoining,d.dateOfBirth as driverDateOfBirth,d.bloodGroup  as driverBloodGroup,d.addressLine1 as driverAddressLine1, d.addressLine2 as driverAddressLine2,d.city as driverCity,d.state as driverState,d.postalCode as driverPostalCode,d.roleName as driverRoleName,d.roleId as driverRoleId,
	 h.userName as helperUsername, h.staffId as helperStaffId,h.qualification1 as helperQualification1 ,h.mobileNumber as helperMobileNumber,h.phoneNumber as helperPhoneNumber,h.licenseNumber as helperLicenseNumber,h.licenseExpDate as helperLicenseExpDate,h.firstName as helperFristName,h.lastName as helperLastName,h.middleName as helperMiddleName,h.dateOfJoining as helperDateOfJoining,h.dateOfBirth as helperDateOfBirth,h.bloodGroup as helperBloodGroup,h.addressLine1 as helperAddressLine1,h.addressLine2 as helperAddressLine2,h.city as helperCity,h.state as helperState,h.postalCode as helperPostalCode,h.roleName as helperRoleName,d.roleId as helperRoleId,
	 vt.driverAccountId,vt.helperAccountId,vt.vehicleId,vt.status vehicleStatus,vt.createdBy,vt.createdDate,vt.lastAccessDate,vt.lastUpdatedDate,vt.lastUpdatedBy,vt.version,vt.startPoint,vt.endPoint,vt.tripNum,vt.routeNumber,vt.shh1,vt.ehh2,vt.emm2,vt.amPm7,vt.amPm8,vt.smm1,vb.amPm1,vb.amPm2,vb.amPm3,vb.amPm4,vb.amPm5,vb.amPm6,vb.hh1,vb.hh2,vb.hh3,vb.hh4,vb.hh5,vb.hh6 ,vb.mm1,vb.mm2,vb.mm3,vb.mm4,vb.mm5,vb.mm6 ,vb.stop1,vb.stop2,vb.stop3,vb.stop4,vb.stop5,vb.stop6,vb.vehicleTripId
	FROM vehicleTrip vt LEFT JOIN vw_staffDetails d on (vt.driverAccountId=d.accountId) LEFT JOIN vw_staffDetails h on  (vt.helperAccountId=h.accountId) LEFT JOIN boardingPoints vb on (vt.id=vb.vehicleTripId);

========================================09/11/2010 ==============================================
drop table vw_classFeeDetails;

create
or replace view vw_classFeeDetails as
SELECT c.id classId,c.custId, c.className, c.version, f.id feeId
FROM studyClass c  RIGHT JOIN Fee f on (c.id=f.classId);

create
or replace view vw_classSection as
SELECT c.id classId,c.custId, c.className, c.version, s.section
FROM class c  JOIN section s;
===================================07/12/2010===================================================
drop table vw_classExamDetails;

create
or replace view vw_classExamDetails as
SELECT ss.id subId,ss.name,ss.lastUpdatedDate,ss.description,es.id as scheduleId,es.custId,es.classId,es.examDate,es.startTime,es.endTime,es.subjectId,es.academicYearId,sc.className,sc.section,es.version,et.id as eid,et.examType,et.minMarks,et.maxMarks,c.id classNameClassId
FROM studyClass sc  RIGHT JOIN examSchedules es on (sc.id=es.classId) LEFT JOIN studySubject ss on (ss.id=es.subjectId)LEFT JOIN examTypes et on(et.id=es.examTypeId) LEFT JOIN class c on(c.id=sc.classNameClassId);
 
===================================Seshu 09/12/2010===================================================
drop table vw_studentMarksDetails;

create or replace view vw_studentMarksDetails as
	SELECT s.id as studId,sm.id as marksId,et.id examTypeId,s.custId,ss.id as subjectId,es.id scheduleId,s.rollNumber,s.classId,et.examType,et.maxMarks,et.minMarks,s.academicYearId,sm.obtainedMarks,ss.name as subjectName,
	es.examDate,es.startTime,es.endTime,sm.lastUpdatedDate,sm.present,sc.className,sc.section
	FROM student s RIGHT JOIN studentMarks sm on (s.id=sm.studId) LEFT JOIN  examSchedules es on (es.id=sm.examScheduleId) LEFT JOIN examTypes et on (et.id=es.examTypeId)
	 LEFT JOIN studySubject ss on (ss.id=es.subjectId) LEFT JOIN studyClass sc on (sc.id=s.classId);
	
=============================Ganesh 24/12/2010   Not Required=====================
insert into examTypes(id,version,custId,maxMarks,minMarks,examType) values
(1,0,1,25,9,'Term-1'),
(2,0,1,25,9,'Term-2'),
(3,0,1,25,9,'Term-3'),
(4,0,1,25,9,'Term-4'),
(5,0,1,100,35,'Quarterly'),
(6,0,1,100,35,'Half Yearly'),
(7,0,1,100,35,'Annually');

update customer set organization='SRI SRI SRI MOOKAMBIKA EDUCATIONAL SOCIETY';
update customer set customerName='SRI Saraswathi Vidya Mandir';

===================================Narahari 03/01/2011===================================================
create 
or replace view vw_studentAttendanceDetails as
SELECT sd.id id,sd.id accountId,sd.custId, sd.username,sd.parentId,  sd.version, sd.sharePrivacy, sd.userOnlineNow, sd.id studentId, sd.academicYearId, sd.createdDate, sd.lastAccessDate, sd.status,sd.classId,sd.rollNumber,sd.firstName,sd.lastName, sd.middleName, sd.fatherName, sd.motherName, sd.mothersMaidenName,sd.dateOfBirth,sd.mobileNumber,sd.phoneNumber,sd.parentEmail,sd.summary,sd.dateOfJoining,sd.addressLine1,sd.addressLine2,sd.city,sd.state,sd.postalCode,sd.lastUpdatedBy, sd.lastUpdatedDate, sd.createdBy,
sd.className,sd.section,sd.roleName,sd.roleId,sd.imageId,sd.imageName,sd.imagePath,sd.thumbNail,
a.attendanceDate, a.status attendanceStatus,a.monthId,a.monthName,a.classId attendanceClassId
FROM vw_studentDetails sd LEFT JOIN attendance a on (sd.studentId=a.studentId);


============================cherivi 18/01/2011===========================================================
insert into feeType(id,custId,description,status) values 
(1,1,'Admission Fee','L'),
(2,1,'TotalTerm Fee','L'),
(3,1,'Term1 Fee','L'),
4,1,'Term2 Fee','L'),
(5,1,'Term3 Fee','L'),
(6,1,'Examination Fee','L'),
(7,1,'Tranport Fee','L');

============================Narahari 26/01/2011       Not Required  ===========================================================

create table staffElgibleSubjects (staffId bigint(20) not null, studySubjectId bigint(20) not null, primary key (staffId, studySubjectId));
=====================================================================================================================
drop table vw_onlineApplicationDetails;

create 
or replace view vw_onlineApplicationDetails as
SELECT o.id id,o.custId, o.parentEmail,o.fatherName,o.bloodGroup,  o.version, o.motherName,o.occupation,o.firstName,o.lastName,o.phoneNumber,o.mobileNumber,o.addressLine1,o.addressLine2,o.otherCastName,o.castName,o.city,o.state,o.academicYearId, o.createdDate, o.lastAccessDate, o.status as applicationStatus,o.className,o.applicationNumber,o.dateOfBirth,o.gender,o.postalCode,o.lastUpdatedBy, o.lastUpdatedDate,o.lastClassAttended,o.lastSchool, o.createdBy,o.entranceMarks,o.classId, 
a.admissionEndDate,a.testConducted,a.applicationFee,a.entranceDate,a.status as admissionStatus
FROM onlineApplicationDetails o LEFT JOIN admissionSettings a on (o.custId=a.custId);


============================Venkatesh 02/02/2011  Not Required ===========================================================
insert into kBankType(id,version,custId,typeName,status) values 
(1,1,1,'My Contribution','Y'),
(2,1,1,'My Projects','Y'),
(3,1,1,'My Class Material','Y'),
(4,1,1,'Competitive Training','Y'),
(5,1,1,'Case Studies','Y'),
(6,1,1,'White Papers','Y');

=========================Ganesh 06/02/2011============================
drop table vw_classFeeDetails;

create
or replace view vw_classFeeTypes as
select c.id as classId, c.custId, c.academicYearId, c.className, f.id as feeTypeId, f.feeType from  class c , feeType f where c.custId=f.custId;


create
or replace view vw_classFeeDetails as
select f.id as feeId, f.custId, t.id as schoolTermId, f.academicYearId, f.classId, f.feeTypeId,c.id as categoryId,t.termName, t.toDate, t.toMonthName,t.fromDate, 
t.fromMonthName, t.dueDate,ft.feeType, f.feeAmount,c.categoryName from Fee f left join schoolTerms t on f.schoolTermId = t.id left join feeType ft on f.feeTypeId=ft.id 
left join schoolCategory c on f.categoryId=c.id and f.custId=ft.custId;

create or replace view vw_StudentPayments as 
select s.id as studentId, s.classId as sectionId, s.classNameClassId as classId,sp.paymentDate,sp.feeId, sp.id as  studentPaymentId, IFNULL(sp.paymentAmount,0) as paymentAmount,sp.paymentType,sp.ddNumber,sp.paymentStatus
from student s left join studentPayment sp on s.id = sp.studentId;

create
or replace view vw_studentClassFees as
SELECT cf.feeId,IFNULL(cf.feeAmount,0) as feeAmount,cf.custId,cf.feeTypeId,cf.schoolTermId,cf.classId,cf.academicYearId,cf.feeType,cf.fromdate,
	cf.toDate,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.categoryId,cf.categoryName,
	s.id as studentId,s.rollNumber,s.accountId,s.status
FROM   vw_classFeeDetails cf left join student s on (cf.classId=s.classNameClassId) and (cf.categoryId=s.categoryId); 


select * from vw_studentClassFees where studentId=30
select * from studentPayment where studentId=30

-----query---------------
select feeId,classId,custId,feeTypeId,feeType,termName,schoolTermId,feeAmount,paymentAmount,studentId,paymentStatus,academicYearId from vw_studentClassFeePaymentDetails where custId =2 and academicYearId=2 and classId=11 and studentId=30 order by schoolTermId;

select firstName,lastName,rollNumber,feeId,classId,custId,termName,schoolTermId,studentId,academicYearId, sum(feeAmount)-sum(paymentAmount) from vw_studentClassFeePaymentDetails where custId =2 and academicYearId=2  group by firstName,lastName,rollNumber,feeId,classId,custId,termName,schoolTermId,studentId,academicYearId;


select firstName,lastName,rollNumber,feeId,classId,termName,studentId,academicYearId,sum(feeAmount), sum(feeAmount)-sum(paymentAmount) from vw_studentClassFeePaymentDetails where custId =2 and academicYearId=2  and studentId=30 group by schoolTermId;

-----------------------------
create
or replace view vw_studentClassFeePaymentDetails as
SELECT IFNULL(cf.studentId,0) as studentId,sp.id as studentPaymentId,cf.feeId,cf.schoolTermId,cf.feeTypeId,cf.classId,cf.academicYearId,cf.custId,cf.categoryId,IFNULL(cf.feeAmount,0) as feeAmount,cf.feeType,cf.fromdate,
	cf.toDate,cf.fromMonthName,cf.toMonthName,cf.termName,cf.dueDate,cf.categoryName,
	 sp.paymentDate,IFNULL(sp.paymentAmount,0) as paymentAmount,sp.paymentType,sp.ddNumber,IFNULL(sp.paymentStatus,'N') as paymentStatus,IFNULL(sp.discountAmount,0) as discountAmount,
	 cf.rollNumber,cf.status,a.username,p.firstName,p.lastName, p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail
FROM   vw_studentClassFees cf LEFT JOIN studentPayment sp on cf.studentId=sp.studentId and (cf.feeId = sp.feeId ) left join 
	Account a on (cf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);

=========================Narahari 09/02/2011============================

drop table vw_allUsers;          
          
CREATE OR REPLACE VIEW vw_allUsers 
AS select a.id id,a.id accountId,a.username,a.createdBy,a.custId, a.createdDate,a.lastAccessDate,a.lastUpdatedBy,a.lastUpdatedDate,a.version, ur.roleId,r.name AS roleName,r.description 
AS roleDescription,p.firstName,p.lastName,p.parentEmail,p.mobileNumber, p.fatherName,d.email staffEmail,ui.id imageId,ui.name imageName,ui.path imagePath,ui.thumbNail
from UserRoles ur  LEFT JOIN Account a  on (ur.userId=a.id) LEFT JOIN Person p  on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id) LEFT JOIN Role r on (ur.roleId=r.id) LEFT JOIN UserImage ui on (ui.id=a.imageId);


===================================Narahari 11/02/2011===================================

Feed back Req
--------------

insert into feedbackQuestions (id,activityType,description) values(1,'HS','How you rate our School');
insert into feedbackQuestions (id,activityType,description) values(2,'RS','Rate the Transport System');
insert into feedbackQuestions (id,activityType,description) values(3,'TS','Rate the Time Schedule of the School'); 
insert into feedbackQuestions (id,activityType,description) values(4,'HF','How you rate the Hostel Facilities ');

	insert into feedbackGrades (id,version,title,description) values(1,0,'1','Bad');
	insert into feedbackGrades (id,version,title,description) values(2,0,'2','Below Average');
	insert into feedbackGrades (id,version,title,description) values(3,0,'3','Average');
	insert into feedbackGrades (id,version,title,description) values(4,0,'4','Good');
	insert into feedbackGrades (id,version,title,description) values(5,0,'5','Excellent');


/*
insert into feedbackQuestions (id,activityType,description) values(1,'ACD','Academics');
insert into feedbackQuestions (id,activityType,description) values(2,'COA','Co-curriculam activities');
insert into feedbackQuestions (id,activityType,description) values(3,'TF','Transport Facilities');
insert into feedbackQuestions (id,activityType,description) values(4,'BF','Basic Facilities');
insert into feedbackQuestions (id,activityType,description) values(5,'ATO','Attitude Of');
insert into feedbackQuestions (id,activityType,description) values(6,'TSE','The School Environment');


insert into feedbackOptions (id,fquestionsId,description) values(1,2,'Games');
insert into feedbackOptions (id,fquestionsId,description) values(2,2,'Music & Dance');
insert into feedbackOptions (id,fquestionsId,description) values(3,2,'Arts & Crafts');
insert into feedbackOptions (id,fquestionsId,description) values(4,2,'General Assembly Programmes');
insert into feedbackOptions (id,fquestionsId,description) values(5,2,'School Annual Day');
insert into feedbackOptions (id,fquestionsId,description) values(6,2,'Sports Day');
insert into feedbackOptions (id,fquestionsId,description) values(7,2,'Project');

insert into feedbackOptions (id,fquestionsId,description) values(8,4,'Cafeteria');
insert into feedbackOptions (id,fquestionsId,description) values(9,4,'Reception');
insert into feedbackOptions (id,fquestionsId,description) values(10,4,'School Website');
insert into feedbackOptions (id,fquestionsId,description) values(11,4,'First Aid');
insert into feedbackOptions (id,fquestionsId,description) values(12,4,'School Safety Provisions');

insert into feedbackOptions (id,fquestionsId,description) values(13,5,'Teachers');
insert into feedbackOptions (id,fquestionsId,description) values(14,5,'Front Office Staff');
insert into feedbackOptions (id,fquestionsId,description) values(15,5,'Helpers');
insert into feedbackOptions (id,fquestionsId,description) values(16,5,'Transport Operators');


insert into feedbackGrades (id,foptionsId,title,description) values(1,1,'A','Excellent');
insert into feedbackGrades (id,foptionsId,title,description) values(2,1,'B','Very Good');
insert into feedbackGrades (id,foptionsId,title,description) values(3,1,'C','Good');
insert into feedbackGrades (id,foptionsId,title,description) values(4,1,'D','Need to Improvement');

insert into feedbackGrades (id,foptionsId,title,description) values(5,2,'A','Excellent');
insert into feedbackGrades (id,foptionsId,title,description) values(6,2,'B','Very Good');
insert into feedbackGrades (id,foptionsId,title,description) values(7,2,'C','Good');
insert into feedbackGrades (id,foptionsId,title,description) values(8,2,'D','Need to Improvement');

insert into feedbackGrades (id,foptionsId,title,description) values(9,3,'A','Excellent');
insert into feedbackGrades (id,foptionsId,title,description) values(10,3,'B','Very Good');
insert into feedbackGrades (id,foptionsId,title,description) values(11,3,'C','Good');
insert into feedbackGrades (id,foptionsId,title,description) values(12,3,'D','Need to Improvement');

insert into feedbackGrades (id,foptionsId,title,description) values(13,4,'A','Excellent');
insert into feedbackGrades (id,foptionsId,title,description) values(14,4,'B','Very Good');
insert into feedbackGrades (id,foptionsId,title,description) values(15,4,'C','Good');
insert into feedbackGrades (id,foptionsId,title,description) values(16,4,'D','Need to Improvement');

insert into feedbackGrades (id,foptionsId,title,description) values(17,5,'A','Excellent');
insert into feedbackGrades (id,foptionsId,title,description) values(18,5,'B','Very Good');
insert into feedbackGrades (id,foptionsId,title,description) values(19,5,'C','Good');
insert into feedbackGrades (id,foptionsId,title,description) values(20,5,'D','Need to Improvement');

insert into feedbackGrades (id,foptionsId,title,description) values(21,6,'A','Excellent');
insert into feedbackGrades (id,foptionsId,title,description) values(22,6,'B','Very Good');
insert into feedbackGrades (id,foptionsId,title,description) values(23,6,'C','Good');
insert into feedbackGrades (id,foptionsId,title,description) values(24,6,'D','Need to Improvement');

insert into feedbackGrades (id,foptionsId,title,description) values(25,7,'A','Excellent');
insert into feedbackGrades (id,foptionsId,title,description) values(26,7,'B','Very Good');
insert into feedbackGrades (id,foptionsId,title,description) values(27,7,'C','Good');
insert into feedbackGrades (id,foptionsId,title,description) values(28,7,'D','Need to Improvement');*/




//not required

insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(23,'VAW',null,'Would you like to Volunteer for any work in the school');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(24,'SAD',23,'School Annual Day');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(25,'SEP',23,'School Educational Project');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(26,'SSD',23,'School Sports Day');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(27,'TFT',23,'Term Field Trips');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(28,'TRME',23,'Traffic Regulation during morning and evening');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(29,'LV',23,'Leave Vacancy/substitution');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(30,'CWS',23,'Coaching Weak Students(please mention the subject');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(31,'CBSL',23,'Controlling Books for the school librart');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(32,'PT',23,'Parent Tree');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(33,'AO',23,'Any Other');
insert into feedbackQuestions (id,activityType,feedbackQuestionsId,description) values(36,'NA',null,'---------------------');
------------------Quiz Ganesh 5/03/2011------------------

drop table vw_studentQuizQuestionAnswers;

create
or replace view vw_studentQuizQuestionAnswers as
SELECT  qa.id,q.questionName,q.custId,q.id questionId,q.quizId,q.status, q.version,q.createdDate,q.createdBy,q.lastAccessDate,q.lastUpdatedBy,q.lastUpdatedDate, qa.questionAnswer,qa.anserOptions,qa.correctAnswer
FROM quizQuestion q  RIGHT JOIN questionAnswer qa on (q.id=qa.questionId);

drop table vw_studentQuestionAnswers;

create
or replace view vw_studentQuestionAnswers as
SELECT  qa.id,q.questionName,q.startDate,q.endDate,q.custId,q.id questionId,q.quizId,q.status, q.version,q.createdDate,q.createdBy,q.lastAccessDate,q.lastUpdatedBy,q.lastUpdatedDate, qa.studentAnswer,qa.studentCorrectAnswer,qa.correctAnswer,qa.studentId
FROM quizQuestion q  RIGHT JOIN studentQuestionAnswer qa on (q.id=qa.questionId);

drop table vw_parentFeedBackResponse;

create
or replace view vw_parentFeedBackResponse as
SELECT  pfr.id,pfr.parentId,fq.custId,fq.version,fq.createdDate,fq.createdBy,fq.lastAccessDate,fq.lastUpdatedBy,fq.lastUpdatedDate,fq.id feedbackQuestionId,fq.description,fq.activityType,fg.id feedbackGradeId,fg.description gradeDescription,fg.title
FROM feedbackQuestions fq RIGHT JOIN parentFeedbackResult pfr on(fq.id=pfr.feedbackQuestionId)LEFT JOIN feedbackGrades fg on(fg.id=pfr.feedbackGradeId);
------------------library cvs 15/05/2011------------------

create
or replace view vw_bookTitleAndLablesDetails as
SELECT  bl.id,b.bookName,b.author,b.publisher,b.noOfCopies,b.cost,b.status,b.readingBookCount,b.issueBookCount,b.issueDays,b.custId,b.subject, b.version,b.createdDate,b.createdBy,b.lastAccessDate,b.lastUpdatedBy,b.lastUpdatedDate, bl.lableCode,bl.bookTitleId 
FROM bookTitle b RIGHT JOIN bookLables bl on (b.id=bl.bookTitleId);


------------For examSchedules

create
or replace view vw_examSchedules as
SELECT sc.id classId,sc.academicYearId,sc.className,sc.section,ss.name subjectName,et.id examTypeId,et.examType,et.minMarks,et.maxMarks,es.examDate,es.startTime,es.endTime,sc.custId,IFNULL(es.id,0) as scheduleId,cs.subjectId
FROM studyClass sc  LEFT JOIN ClassSubject cs on (sc.id=cs.studyClassId) LEFT JOIN studySubject ss on (cs.subjectId=ss.id) LEFT JOIN classExamTypes ce on(ce.classNameId = sc.classNameClassId) LEFT JOIN examTypes et on (ce.examTypeId=et.id) LEFT JOIN examSchedules es on (es.classId=sc.id and es.subjectId=cs.subjectId and es.examTypeId = ce.examTypeId);
 

---------------------For staff subjects------------

create 
or replace view vw_staffSubjectsDetails as
SELECT s.accountId,s.id as staffId,cteacher.ct as classTeacher,cteacher.studyClassId,cteacher.studySubjectId,ss.name as subjectName,sc.className,sc.section,sc.custId,sc.academicYearId
FROM staff s LEFT JOIN classTeacher cteacher on (s.id=cteacher.teacherId) LEFT JOIN studySubject ss on (cteacher.studySubjectId=ss.id) LEFT JOIN studyClass sc on (cteacher.studyClassId=sc.id);


---------------------For StudyClass Subjects

create 
or replace view vw_studyClassSubjectDetails as
SELECT sc.id as studyClassId,sc.className,sc.section,sc.custId,sc.academicYearId,ss.id as subjectId,ss.name as subjectName
FROM ClassSubject cs LEFT JOIN  studyClass sc on (cs.studyClassId=sc.id) LEFT JOIN studySubject ss on (cs.subjectId=ss.id);

---------------------For Active Logs

CREATE TABLE activeLogs (id BIGINT NOT NULL AUTO_INCREMENT,customerId BIGINT,toAddress tinyblob,fromAddress varchar(128),sender varchar(128),sendDate datetime,type varchar(128),purpose varchar(255), PRIMARY KEY (id));


-------------------- Syllabus Type info table

create table syllabusTypeInfo (customerId bigint(20) not null, syllabusTypeId bigint(20) not null, primary key (customerId, syllabusTypeId));

insert into syllabusType (id,syllabusTypeName,syllabusTypeDescription,version,status) values(1,'CBSE','Central Board School Education',0,"Y");
insert into syllabusType (id,syllabusTypeName,syllabusTypeDescription,version,status) values(2,'SBSE','State Board School Education',0,"Y");
insert into syllabusType (id,syllabusTypeName,syllabusTypeDescription,version,status) values(3,'ICSE','Indian Certificate of Secondary Education',0,"Y");
insert into syllabusType (id,syllabusTypeName,syllabusTypeDescription,version,status) values(4,'IB','International Baccalaureate',0,"Y");

insert into syllabusTypeInfo (customerId,syllabusTypeId) values(2,1);
insert into syllabusTypeInfo (customerId,syllabusTypeId) values(2,2);


-------------------------  income tax tates

INSERT INTO incomeTaxRates(id,version,slabs,netAmount1,netAmount2,taxPercentage,financialYear,status,month,year,Gender) VALUES
(1,1,1,0,200000,0,'2012-2013','Y',8,2012,'M'),
(2,1,2,200001,500000,10,'2012-2013','Y',8,2012,'M'),
(3,1,3,500001,1000000,20,'2012-2013','Y',8,2012,'M'),
(4,1,4,1000000,0,30,'2012-2013','Y',8,2012,'M');


INSERT INTO incomeTaxRates(id,version,slabs,netAmount1,netAmount2,taxPercentage,financialYear,status,month,year,Gender) VALUES
(5,1,1,0,200000,0,'2012-2013','Y',8,2012,'F'),
(6,1,2,200001,500000,10,'2012-2013','Y',8,2012,'F'),
(7,1,3,500001,1000000,20,'2012-2013','Y',8,2012,'F'),
(8,1,4,1000000,0,30,'2012-2013','Y',8,2012,'F');



