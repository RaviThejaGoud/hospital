drop table IF EXISTS vw_staffHostelFeeTypes;
drop view IF EXISTS vw_staffHostelFeeTypes;
drop table IF EXISTS vw_staffHostelFeeDetails;
drop view IF EXISTS vw_staffHostelFeeDetails;
drop view IF EXISTS vw_staffHostelFees;
drop table IF EXISTS vw_staffHostelFees;
drop view IF EXISTS vw_hostelStaffFeePaymentDetails;
drop table IF EXISTS vw_hostelStaffFeePaymentDetails;
drop table IF EXISTS vw_staffHostelFeePaymentDetails;
drop view IF EXISTS vw_staffHostelFeePaymentDetails;
drop table IF EXISTS vw_govtschoolsreport;
drop view IF EXISTS vw_govtschoolsreport;
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
IFNULL((select SUM(paymentAmount) from hostelStaffFeePaidDetails AS hsf where hsf.staffHostelFeeId=sf.staffHostelFeeId and hsf.staffId=sf.staffId and hsf.deleteStatus='N'),0) as paymentTotalAmount,
IFNULL(sfp.paymentStatus,'N') as paymentStatus,IFNULL(sfp.discountAmount,0) as discountAmount,IFNULL(sfp.deleteStatus,'N') as deleteStatus,
IFNULL((select SUM(discountAmount) from hostelStaffFeePaidDetails AS hsf where hsf.staffHostelFeeId=sf.staffHostelFeeId and hsf.staffId=sf.staffId and hsf.deleteStatus='N'),0) as totalDiscountAmount,
sf.hostelTermId,sf.hostelFeeTypeId,sf.hostelCategoryId,sf.hostelTermName,sf.toDate,sf.toMonthName,sf.fromDate,sf.fromMonthName,
sf.dueDate,sf.hostelFeeType,sf.categoryName,IFNULL(sf.feeAmount,0) as feeAmount,sf.status,IFNULL(sf.bedId,0) as bedId,a.username,p.firstName,p.lastName, p.middleName, p.mobileNumber,
p.phoneNumber,p.parentEmail,sf.custId,sf.academicYearId,sf.bedCost
from vw_staffHostelFees sf left join hostelStaffFeePaidDetails sfp on (sfp.staffId = sf.staffId and sfp.staffHostelFeeId=sf.staffHostelFeeId and sfp.deleteStatus='N')
 left join Account a on (sf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);

create
or replace view vw_staffHostelFeePaymentDetails as
SELECT IFNULL(sp.id,0) hostelStaffPaymentId,IFNULL(sp.paidAmount,0) as paidAmount,IFNULL(sp.discountAmount,0) as discountAmount,
IFNULL(sp.bankId,0) as bankId,sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,IFNULL(sp.ddNumber,0) as ddNumber,
IFNULL(sp.chequeNumber,0) as chequeNumber,IFNULL(sp.invoiceNumber,0) as invoiceNumber,IFNULL(sp.deleteStatus,'N') as deleteStatus,IFNULL(sfp.paymentStatus,'N') as paymentStatus,
sp.paymentDate,IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.paymentAmount,0) as paymentAmount,sf.academicYearId,sf.custId,sf.staffHostelFeeId,sf.hostelTermId,
sf.hostelFeeTypeId,sf.hostelTermName,sf.toDate,sf.fromMonthName,sf.toMonthName,sf.dueDate,sf.hostelFeeType,sf.categoryName,IFNULL(sf.feeAmount,0) as feeAmount,
IFNULL(sf.staffId,0) as staffId,IFNULL(sf.bedId,0) as bedId,IFNULL(sf.bedCost,0) as bedCost,sf.hostelCategoryId,sf.fromdate,
sf.status,sf.description,a.username,p.firstName,p.lastName,p.middleName, p.mobileNumber,p.phoneNumber,p.parentEmail
from vw_staffHostelFees sf left join hostelStaffFeePaidDetails sfp on (sfp.staffId = sf.staffId and sfp.staffHostelFeeId=sf.staffHostelFeeId and sfp.deleteStatus='N')
left join hostelStaffPayment sp on (sp.id=sfp.hostelStaffPaymentId) left join Account a on (sf.accountId=a.id) LEFT JOIN Person p on (a.personId=p.id) LEFT JOIN  Address d on (a.paddressId=d.id);

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