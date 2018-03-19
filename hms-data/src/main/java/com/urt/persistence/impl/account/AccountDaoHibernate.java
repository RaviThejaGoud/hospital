package com.urt.persistence.impl.account;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.account.AccountDao;
import com.urt.persistence.model.account.ViewFinAccountDetails;
import com.urt.persistence.model.customer.FineFee;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.util.common.RayGunException;

public class AccountDaoHibernate extends UniversalHibernateDao implements AccountDao {

	private static final Log log = LogFactory.getLog(AccountDaoHibernate.class);

	public List<ViewFinAccountDetails> getFinalReportDetails(long custId,long financialYearId, String fromDate, String endDate,String statementIds) {
		try {
			StringBuilder sqlQuery = new StringBuilder("SELECT t1.accountId as accountId,accountName,t1.TrxAmount as balanceAmount,vw.cartegoryName as cartegoryName,vw.statmentCode as statmentCode,vw.statementId as statementId FROM  ");
			sqlQuery.append("(SELECT financialYearId,accountId AS AccountId,Sum(TrxAmount) as TrxAmount FROM  ");
			sqlQuery.append("(SELECT financialYearId,accountId,TrxAmount FROM vw_trialBalance WHERE custId="+custId+" and financialYearId ="+financialYearId+" AND accountId <> 1 ");
			sqlQuery.append("UNION ALL ");
			sqlQuery.append("SELECT cb.financialYearId, 1 AS AccountId,SUM((CASE WHEN transactionType = 'D' THEN (amount * -1) ELSE amount END)) + ");
			sqlQuery.append("(SELECT SUM(TrxAmount) FROM vw_trialBalance vwT WHERE vwT.custId="+custId+" and vwT.financialYearId ="+financialYearId+") AS TrxAmount FROM finCashBook cb WHERE cb.custId="+custId+" and cb.financialYearId ="+financialYearId);
			if (!StringFunctions.isNullOrEmpty(fromDate) && !StringFunctions.isNullOrEmpty(endDate))
				sqlQuery.append(" and transactionDate >= '" + fromDate + " 00:00:0' and transactionDate <= '" + endDate + " 00:00:0' ");
			sqlQuery.append(" GROUP BY cb.financialYearId ");
			sqlQuery.append("UNION ALL ");
			sqlQuery.append("SELECT financialYearId, finAccountId AS AccountId, (CASE WHEN transactionType = 'D' THEN (amount * -1) ELSE amount END) AS TrxAmount FROM finCashBook  WHERE custId="+custId+" and financialYearId = "+financialYearId);  
			if (!StringFunctions.isNullOrEmpty(fromDate) && !StringFunctions.isNullOrEmpty(endDate))
				sqlQuery.append(" and transactionDate >= '" + fromDate + " 00:00:0' and transactionDate <= '" + endDate + " 00:00:0' ");
			sqlQuery.append(" ) AS temp ");
			sqlQuery.append("GROUP BY financialYearId, accountId) AS t1 ");
			sqlQuery.append("JOIN vw_finAccountDetails AS vw ON vw.accountId = t1.accountId  AND vw.financialYearId IN (0,t1.financialYearId) WHERE t1.financialYearId in (t1.financialYearId) AND TrxAmount <> 0 AND vw.statementId in "+statementIds+" ORDER BY vw.accountName");
			log.debug(sqlQuery.toString());
			return (List<ViewFinAccountDetails>) getSession().createSQLQuery(sqlQuery.toString()).addScalar("accountId",StandardBasicTypes.LONG).addScalar("accountName").addScalar("balanceAmount", StandardBasicTypes.DOUBLE)
			.addScalar("cartegoryName").addScalar("statmentCode").addScalar("statementId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewFinAccountDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public List<ViewFinAccountDetails> getAllAccountOpeningBalance(long custId,long financialYearId,String statementIds){
		try {
			StringBuilder sqlQuery = new StringBuilder("select accountId,accountName,transactionType,(CASE WHEN transactionType = 'D' THEN (balanceAmount * -1) ELSE balanceAmount END) AS balanceAmount,statmentCode,statementId from vw_finAccountDetails where ");
			sqlQuery.append("custId=").append(custId).append(" and financialYearId=").append(financialYearId).append(" and balanceAmount !=0 and statementId in ").append(statementIds);
			log.debug(sqlQuery.toString());
			return (List<ViewFinAccountDetails>)getSession().createSQLQuery(sqlQuery.toString()).addScalar("accountId",StandardBasicTypes.LONG).addScalar("accountName").addScalar("transactionType").addScalar("balanceAmount", StandardBasicTypes.DOUBLE)
					.addScalar("statmentCode").addScalar("statementId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewFinAccountDetails.class)).list(); 
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public Date getLastCreatedDate(long webId,String tableName){
		try {

			StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select createdDate from "+tableName+" where id="+webId);
			List resultList=this.getAll(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(resultList))
				return (Date)resultList.get(0);
		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public List<ViewFinAccountDetails> getAllAccountOpeningBalanceCashInHand(long custId,long financialYearId,String statementIds){
		try {
			StringBuilder sqlQuery = new StringBuilder("select accountId,accountName,transactionType,sum(balanceAmount) as balanceAmount,statmentCode,statementId from (select accountId,accountName,transactionType,((CASE WHEN transactionType = 'D' THEN (balanceAmount * -1) ELSE balanceAmount END)) AS balanceAmount,statmentCode,statementId from vw_finAccountDetails where ");
			sqlQuery.append("custId in (1,").append(custId).append(") and financialYearId in (0,").append(financialYearId).append(") and statementId in ").append(statementIds).append(" order by accountId ) a");
			log.debug(sqlQuery.toString());
			return (List<ViewFinAccountDetails>)getSession().createSQLQuery(sqlQuery.toString()).addScalar("accountId",StandardBasicTypes.LONG).addScalar("accountName").addScalar("transactionType").addScalar("balanceAmount", StandardBasicTypes.DOUBLE)
					.addScalar("statmentCode").addScalar("statementId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewFinAccountDetails.class)).list(); 
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public List<ViewFinAccountDetails> getSumOfFinalReportDetails(long custId,long financialYearId, String fromDate, String endDate,String statementIds) {
		try {
			StringBuilder sqlQuery = new StringBuilder("SELECT t1.accountId as accountId,accountName,sum(t1.TrxAmount) as balanceAmount,vw.cartegoryName as cartegoryName,vw.statmentCode as statmentCode,vw.statementId as statementId FROM  ");
			sqlQuery.append("(SELECT financialYearId,accountId AS AccountId,Sum(TrxAmount) as TrxAmount FROM  ");
			sqlQuery.append("(SELECT financialYearId,accountId,TrxAmount FROM vw_trialBalance WHERE custId="+custId+" and financialYearId ="+financialYearId+" AND accountId <> 1 ");
			sqlQuery.append("UNION ALL ");
			sqlQuery.append("SELECT cb.financialYearId, 1 AS AccountId,SUM((CASE WHEN transactionType = 'D' THEN (amount * -1) ELSE amount END)) + ");
			sqlQuery.append("(SELECT SUM(TrxAmount) FROM vw_trialBalance vwT WHERE vwT.custId="+custId+" and vwT.financialYearId ="+financialYearId+") AS TrxAmount FROM finCashBook cb WHERE cb.custId="+custId+" and cb.financialYearId ="+financialYearId);
			if (!StringFunctions.isNullOrEmpty(fromDate) && !StringFunctions.isNullOrEmpty(endDate))
				sqlQuery.append(" and transactionDate >= '" + fromDate + " 00:00:0' and transactionDate <= '" + endDate + " 00:00:0' ");
			sqlQuery.append(" GROUP BY cb.financialYearId ");
			sqlQuery.append("UNION ALL ");
			sqlQuery.append("SELECT financialYearId, finAccountId AS AccountId, (CASE WHEN transactionType = 'D' THEN (amount * -1) ELSE amount END) AS TrxAmount FROM finCashBook  WHERE custId="+custId+" and financialYearId = "+financialYearId);  
			if (!StringFunctions.isNullOrEmpty(fromDate) && !StringFunctions.isNullOrEmpty(endDate))
				sqlQuery.append(" and transactionDate >= '" + fromDate + " 00:00:0' and transactionDate <= '" + endDate + " 00:00:0' ");
			sqlQuery.append(" ) AS temp ");
			sqlQuery.append("GROUP BY financialYearId, accountId) AS t1 ");
			sqlQuery.append("JOIN vw_finAccountDetails AS vw ON vw.accountId = t1.accountId  AND vw.financialYearId IN (0,t1.financialYearId) WHERE t1.financialYearId in (t1.financialYearId) AND TrxAmount <> 0 AND vw.statementId in "+statementIds+" ORDER BY vw.accountName");
			log.debug(sqlQuery.toString());
			return (List<ViewFinAccountDetails>) getSession().createSQLQuery(sqlQuery.toString()).addScalar("accountId",StandardBasicTypes.LONG).addScalar("accountName").addScalar("balanceAmount", StandardBasicTypes.DOUBLE)
			.addScalar("cartegoryName").addScalar("statmentCode").addScalar("statementId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewFinAccountDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public List<FineFee> getOtherFeePaymentDetails(long custId,long academicYearId,long studentId){
		try {
			StringBuilder sqlQuery = new StringBuilder("SELECT description,sum(fineFeeAmount) as fineFeeAmount,invoiceNumber,paymentDate,quantity,custId,academicYearId from fineFee where custId=");
			sqlQuery.append(custId);
			sqlQuery.append(" and academicYearId=").append(academicYearId);
			sqlQuery.append(" and studentId=").append(studentId).append(" and deleteStatus='N' ");
			sqlQuery.append(" group by invoiceNumber,paymentDate");
			log.debug(sqlQuery.toString());
			return (List<FineFee>) getSession().createSQLQuery(sqlQuery.toString()).addScalar("description").addScalar("fineFeeAmount", StandardBasicTypes.DOUBLE).addScalar("invoiceNumber",StandardBasicTypes.LONG)
					.addScalar("paymentDate",StandardBasicTypes.DATE).addScalar("quantity",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG).addScalar("academicYearId",StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(FineFee.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public List<ViewStudentFeePaymentDetails> getPreviousYearFeeDefaultersList(long custId,long academicYearId,String toDayDate){
		try { 
			StringBuffer queryBuff = new StringBuffer().append("select sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,(sum(feeAmount)-(sum(paymentAmount)+sum(discountAmount)+sum(concessionAmount))) as paidAmount,sum(discountAmount) as discountAmount,fullName,termName,admissionNumber,className,section,schoolTermId,dueDate,mobileNumber,id,settingName,committedFee,feeAmount as fineAmount,classId,studentId,sum(concessionAmount) as concessionAmount from vw_studentFeePaymentDetails where custId=");
			queryBuff.append(custId).append(" and academicYearId=").append(academicYearId).append(" and feePaidStatus in ('N','P') ").append(" and feeConfigured='Y' ").append(" and description is null");
			queryBuff.append(" and deleteStatus='N'").append(" and paymentStatus='"+Constants.NO_STRING+"'").append(" and paymentCommitFeeStatus='N' ").append(" and dueDate <='"+toDayDate+" 00:00:00' ").append(" group by studentId");
			log.info(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE).addScalar("paidAmount").addScalar("discountAmount").addScalar("committedFee", StandardBasicTypes.DOUBLE).addScalar("fineAmount", StandardBasicTypes.DOUBLE)
			.addScalar("fullName").addScalar("termName").addScalar("admissionNumber").addScalar("className").addScalar("section").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("dueDate").addScalar("mobileNumber").addScalar("id", StandardBasicTypes.LONG).addScalar("settingName").addScalar("classId", StandardBasicTypes.LONG).addScalar("studentId", StandardBasicTypes.LONG).addScalar("concessionAmount", StandardBasicTypes.DOUBLE).setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public Object[] getOverAllFeePaymentSummary(long custId,long academicYearId){
		try {
			Query qry = getSession().createSQLQuery("CALL sp_OverAllFeePaymentSummary(:custId, :academicYearId)")
			.setParameter("custId", custId)
			.setParameter("academicYearId", academicYearId);
			return (Object[]) qry.list().get(0);
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	
	public List<ViewStudentFeePaymentDetails> getClassWiseConsolidatedStudentFeeDetails(long custId, long academicYearId,String classSectionIds, String status) {

		try {
			StringBuilder queryBuff = new StringBuilder();
			/*queryBuff.append("select a.admissionNumber ,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  = '','',CONCAT(' ',p.lastName))) as fullName,");
			queryBuff.append("CONCAT(IF(c.className IS NULL,'',c.className), IF(c.section IS NULL || c.section  = '','',CONCAT(' - ',c.section))) as className,c.id classSectionId,");
			queryBuff.append("IFNULL(scft.TermFee,0) feeAmount,IFNULL(scft.concessionAmount,0) concessionAmount,IFNULL(sum(sp.paidAmount),0) paymentAmount,IFNULL(sum(sp.discountAmount),0) discountAmount,");
			queryBuff.append("IF((IFNULL(sum(sp.paidAmount),0)+IFNULL(scft.concessionAmount,0)+IFNULL(sum(sp.discountAmount),0))<=IFNULL(scft.TermFee,0),0,(IFNULL(sum(sp.paidAmount),0)+IFNULL(scft.concessionAmount,0)+IFNULL(sum(sp.discountAmount),0))-IFNULL(scft.TermFee,0)) excessAmount,");
			queryBuff.append("IF((IFNULL(sum(sp.paidAmount),0)+IFNULL(scft.concessionAmount,0)+IFNULL(sum(sp.discountAmount),0))<=IFNULL(scft.TermFee,0),IFNULL(scft.TermFee,0)-(IFNULL(sum(sp.paidAmount),0)+IFNULL(scft.concessionAmount,0)+IFNULL(sum(sp.discountAmount),0)),0) paidAmount,");
			queryBuff.append("s.id studentId  from student s LEFT JOIN studentPayment sp ON (s.id=sp.studentId and sp.deleteStatus='N' and sp.concessionStatus='N') ");
			queryBuff.append("LEFT JOIN Account a ON (a.id=s.accountId) ");
			queryBuff.append("LEFT JOIN Person p ON (p.id=a.personId) ");
			queryBuff.append("LEFT JOIN studyClass c ON (c.id=s.classSectionId) ");
			queryBuff.append("LEFT JOIN class cl ON (cl.id=c.classNameClassId) ");
			queryBuff.append("LEFT JOIN (select IFNULL(sum(scf.feeAmount),0)+IFNULL(sum(stf.feeAmount),0) TermFee,scf.studentId,IFNULL(sum(scf.concessionAmount),0)+IFNULL(sum(stf.concessionAmount),0) as concessionAmount from  vw_studentClassFees  scf ");
			queryBuff.append("LEFT JOIN vw_studentTransportFees  stf on (scf.studentId=stf.studentId) ");
			queryBuff.append("where scf.custId=").append(custId).append(" and scf.academicYearId=").append(academicYearId);
			if("N".equalsIgnoreCase(status))
				queryBuff.append(" and scf.status='Y' and scf.description is null ");
			queryBuff.append(" group by scf.studentId,stf.studentId) scft ON (scft.studentId=s.id) ");
			queryBuff.append("where s.custId=").append(custId).append(" and s.academicYearId=").append(academicYearId).append(" and c.id in ").append(classSectionIds);
			if("N".equalsIgnoreCase(status))
				queryBuff.append(" and s.status='Y' and s.description is null ");
			queryBuff.append("group by s.id order by cl.sortingOrder,c.id");
			log.info(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("admissionNumber").addScalar("fullName").addScalar("className")
					.addScalar("classSectionId", StandardBasicTypes.LONG).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("concessionAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
					.addScalar("discountAmount", StandardBasicTypes.DOUBLE).addScalar("excessAmount", StandardBasicTypes.DOUBLE).addScalar("paidAmount", StandardBasicTypes.DOUBLE).addScalar("studentId", StandardBasicTypes.LONG)
					.setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
*/
			queryBuff.append("SELECT S.id StudentId,CONCAT(p.firstName,' ',IFNULL(p.lastName,'')) fullName,"
					+ "		a.admissionNumber,"
					+ "		CONCAT(IFNULL(sc.className,''),' - ',IFNULL(sc.section,'')) className,sc.id classSectionId,"
					+ "        IFNULL(schRegularFee.SchoolFee,0) SchoolFee,"
					+ "		IFNULL(transportFee.TransportFee,0) TransportFee,"
					+ "		IFNULL(concessionFee.ConcessionAmount,0) concessionAmount,"
					+ "		(IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0)) feeAmount,"
					+ "		IFNULL(SP.totalPaid,0.00) paidAmount,"
					+ "        IFNULL(SP.totalDiscount,0.00) discountAmount,"
					+ "        IF((IFNULL(concessionFee.ConcessionAmount,0)+IFNULL(SP.totalPaid,0.00)+IFNULL(SP.totalDiscount,0.00))<="
					+ "			(IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0)),0,"
					+ "				(IFNULL(concessionFee.ConcessionAmount,0)+IFNULL(SP.totalPaid,0.00)+IFNULL(SP.totalDiscount,0.00))-"
					+ "				(IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0))) excessAmount,"
					+ "        (IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0)-"
					+ "        (IFNULL(concessionFee.ConcessionAmount,0)+IFNULL(SP.totalPaid,0.00)+IFNULL(SP.totalDiscount,0.00))) totalBalanceAmount"
					+ "	FROM student S"
					+ "    LEFT JOIN Account a ON (a.id = S.accountId)"
					+ "	LEFT JOIN Person p ON (p.id = a.personId)"
					+ "	LEFT JOIN studyClass sc ON (sc.id = S.classSectionId)"
					+ "	LEFT JOIN class cl ON (cl.id = sc.classNameClassId)"
					+ "    JOIN customer C ON S.custId=C.id AND C.status='Y'"
					+ "	JOIN academicYear AC ON S.academicYearId=AC.id AND AC.status='Y'"
					+ "	LEFT JOIN ("
					+ "		SELECT classId,categoryId,SUM(IFNULL(feeAmount,0)) SchoolFee FROM Fee"
					+ "		WHERE IFNULL(routeBoardingPointId,0)=0 AND IFNULL(classId,0)>0"
					+ "		GROUP BY categoryId,classId"
					+ "	) schRegularFee ON schRegularFee.classId=S.classNameClassId AND schRegularFee.categoryId=S.categoryId"
					+ "	LEFT JOIN ("
					+ "		select s.Id AS studentId,"
					+ "			sum(((ifnull(pbp.boardingPointFeeAmount, 0) / 2) + (ifnull(dbp.boardingPointFeeAmount, 0) / 2))) AS TransportFee"
					+ "		from"
					+ "			(((student s"
					+ "			join studentTransportDetails std ON ((s.Id = std.StudentId)))"
					+ "			left join routeBoardingPoints pbp ON ((std.PickupBoardingPointId = pbp.Id)))"
					+ "			left join routeBoardingPoints dbp ON ((std.DropBoardingPointId = dbp.Id)))"
					+ "		group by s.Id) transportFee ON transportFee.studentId=S.id"
					+ "	LEFT JOIN  ("
					+ "		SELECT SUM(IFNULL(concessionAmount,0)) ConcessionAmount,studentId FROM studentFeeConcession GROUP BY studentId"
					+ "	)concessionFee ON concessionFee.studentId=S.id"
					+ "    LEFT JOIN ("
					+ "		select SUM(paidAmount) totalPaid,SUM(discountAmount) totalDiscount,studentId from studentPayment WHERE deleteStatus = 'N' AND concessionStatus = 'N' GROUP BY studentId"
					+ "	) SP ON SP.studentId=S.id"
					+ "	WHERE IF('Y'='"+status+"',TRUE, S.status='Y'and S.description is null) AND S.custId="+custId+" AND S.academicYearId="+academicYearId+" AND S.classSectionId IN "+classSectionIds
					+ "    ORDER BY sc.id,cl.sortingOrder,p.firstName;");
			log.info(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("admissionNumber").addScalar("fullName").addScalar("className")
					.addScalar("classSectionId", StandardBasicTypes.LONG).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("concessionAmount", StandardBasicTypes.DOUBLE)
					.addScalar("discountAmount", StandardBasicTypes.DOUBLE).addScalar("excessAmount", StandardBasicTypes.DOUBLE).addScalar("paidAmount", StandardBasicTypes.DOUBLE)
					.addScalar("totalBalanceAmount", StandardBasicTypes.DOUBLE).addScalar("studentId", StandardBasicTypes.LONG)
					.setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;

	}

}

