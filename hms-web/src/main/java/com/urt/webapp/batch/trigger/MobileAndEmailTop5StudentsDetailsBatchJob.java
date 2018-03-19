/********************************************************************
 * Copyright (C) 2007-08
 * IFS
 * All Rights Reserved 
 *
 * File: CampaignBatchJob.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Apr 28, 2008       Sreeram J          Created
/********************************************************************/

package com.urt.webapp.batch.trigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.common.StudentMonthlyAttendance;
import com.urt.persistence.model.common.UserAutoReportsConfiguration;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.user.UserManager;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.webapp.action.base.BaseAction;

public class MobileAndEmailTop5StudentsDetailsBatchJob extends BaseAction implements Job {
	private static final long serialVersionUID = 6137644058516625485L;
	private static Log _log = LogFactory.getLog(MobileAndEmailTop5StudentsDetailsBatchJob.class);
  
    public MobileAndEmailTop5StudentsDetailsBatchJob() {
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

	@Autowired
	private AdminManager adminManager;
	@Autowired
	private UserManager	userManager;
    public void execute(JobExecutionContext context)
        throws JobExecutionException {
   	String jobName = context.getJobDetail().getDescription();
        _log.info("MobileAndEmailTop5StudentsDetailsBatchJob Batch Process Started : " + jobName + " executing at " + new Date());
		try {
			Date todayDate = new Date();
			int currentDate = DateFunctions.getDayOfMonth(todayDate);
			StringBuffer query = new StringBuffer(" status='Y' ");
			List<Customer> customers = adminManager.getAll(Customer.class, query.toString());
			AcademicYear academicYear = null;
			if(ObjectFunctions.isNullOrEmpty(customers)){
				_log.info("No customers enabled mobile or email services.");
			}else{
				log.info("batch start Time:"+new Date());
				for(Customer customer : customers){
					academicYear = userManager.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
					if(ObjectFunctions.isNullOrEmpty(academicYear)){
						_log.info("Academic year not created for customer Id - "+customer.getId());
					}else{
						/*if(ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
							academicYear.setEndDate(todayDate);
						}*/		
						UserAutoReportsConfiguration reportsConfiguration=(UserAutoReportsConfiguration)adminManager.get(UserAutoReportsConfiguration.class, "custId="+customer.getId()+" and status='Y' and reportId=1");
						if(!ObjectFunctions.isNullOrEmpty(reportsConfiguration)){
							if (!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate()) && !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())) {
								if(currentDate == 1 || (DateFunctions.getDatePlusNdays(academicYear.getEndDate(),1).compareTo(todayDate)<=0)){
									sendSMSAndEmailsForTop5StudentsDetails(academicYear,customer);
								}
							}
						}
					}
					customer = null;
				}
				academicYear = null;
				log.info("batch End Time:"+new Date());
			}
			if(currentDate == 1){
				sendEmailFeePaidAndUnPaidDetailsSummary();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	_log.info("MobileAndEmailRemaindersBatchJob Batch Completed successfully.....");
    }
	public void sendSMSAndEmailsForTop5StudentsDetails(AcademicYear academicYear,Customer customer)	throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'sendSMSAndEmailsForTop5StudentsDetails' method");
		}
		try {
			setViewStudentMarksDetailsList(null);
			setStudentMonthlyAttendanceList(null);
			setStudentDailyAttendanceList(null);
			setViewStaffPersonAccountDetailsList(null);
			long academicYearId = academicYear.getId();
			long custId = customer.getId();
			Date openDate = academicYear.getStartDate();
			Date closeDate = academicYear.getEndDate();
			closeDate = DateFunctions.getDatePlusNdays(closeDate,1);
			Date todayDate = new Date();
				log.info("custId:"+custId);
				if(DateFunctions.compare2Dates(todayDate,openDate) && DateFunctions.compare2Dates(closeDate,todayDate)){
					log.info("after  Compare startDate and End Date:"+custId);
					boolean topStudentsCount = false;
					List<Object[]> top5StudentMonthlyAttendanceList = null;
					List<Object[]> studentMonthlyOrDailyAttendanceList = null;
					if ("M".equalsIgnoreCase(academicYear.getManageAttendanceBy())) {
						adminManager.updateAndGetTopStudentsMonthlyAttendanceDetails(custId,academicYearId);
						List<Object[]> fifthAttenMonthlypercen = adminManager.getAll("select topStudentMonthlyAttendancePercentage from topStudentMonthlyAttendancePercentageCal where custId="+custId+" and academicYearId="+academicYearId+" and topStudentMonthlyAttendancePercentage!=0.0 group by studId order by topStudentMonthlyAttendancePercentage desc limit 5");
						log.info(fifthAttenMonthlypercen);
						if(!ObjectFunctions.isNullOrEmpty(fifthAttenMonthlypercen) && fifthAttenMonthlypercen.size()>=5){
							if(!ObjectFunctions.isNullOrEmpty(fifthAttenMonthlypercen.get(4))){
								studentMonthlyOrDailyAttendanceList = adminManager.getAll("select * from topStudentMonthlyAttendancePercentageCal where custId="+custId+" and academicYearId="+academicYearId+" and topStudentMonthlyAttendancePercentage>="+fifthAttenMonthlypercen.get(4)+" group by studId order by topStudentMonthlyAttendancePercentage desc");
								if(studentMonthlyOrDailyAttendanceList.size() > 5){
									if((studentMonthlyOrDailyAttendanceList.size()-1) >= 1){
										topStudentsCount = true;
									}
								}
							}
						}
						top5StudentMonthlyAttendanceList = userManager.getAll("select topStudentMonthlyAttendancePercentage,admNos,studsName,classAndSections from topStudentMonthlyAttendancePercentageCal where custId="+custId+" and academicYearId="+academicYearId+" and topStudentMonthlyAttendancePercentage!=0.0 group by studId order by topStudentMonthlyAttendancePercentage desc,studsName asc limit 5");
						 if(!ObjectFunctions.isNullOrEmpty(top5StudentMonthlyAttendanceList)){
							 for(Object[] obj : top5StudentMonthlyAttendanceList){
								if (!ObjectFunctions.isNullOrEmpty(obj)) {
									StudentMonthlyAttendance studenMonthlyAttendance = new StudentMonthlyAttendance();
									 studenMonthlyAttendance.setAttendancePercentage(Double.valueOf(obj[0].toString()));
									studenMonthlyAttendance.setAdmissionNumber(String.valueOf(obj[1].toString()));
									studenMonthlyAttendance.setStudentName(String.valueOf(obj[2].toString()));
									studenMonthlyAttendance.setClassNameAndSection(String.valueOf(obj[3].toString()));
									getStudentMonthlyAttendanceList().add(studenMonthlyAttendance);
								}
							 }
					     }
						 studentMonthlyOrDailyAttendanceList = null;
					  }else{
						  adminManager.updateAndGetTopStudentsDailyAttendanceDetails(custId,academicYearId);
						  List<Object[]> fifthAttenpercen = adminManager.getAll("select topTotalPercentage from topStudentDailyAttendancePercentageCal where custIds="+custId+" and academicYearIds="+academicYearId+" and topTotalPercentage!=0.0 group by studId order by topTotalPercentage desc limit 5");
						  log.info(fifthAttenpercen);
						  if(!ObjectFunctions.isNullOrEmpty(fifthAttenpercen) && fifthAttenpercen.size()>=5){
							  if(!ObjectFunctions.isNullOrEmpty(fifthAttenpercen.get(4))){
									studentMonthlyOrDailyAttendanceList = adminManager.getAll("select * from topStudentDailyAttendancePercentageCal where custIds="+custId+" and academicYearIds="+academicYearId+" and topTotalPercentage>="+fifthAttenpercen.get(4)+" group by studId order by topTotalPercentage desc");
									if(studentMonthlyOrDailyAttendanceList.size() > 5){
										if((studentMonthlyOrDailyAttendanceList.size()-1) >= 1){
											topStudentsCount = true;
										}
									}
								}
						   }
						  top5StudentMonthlyAttendanceList = userManager.getAll("select studId,topTotalPercentage,admisNos,studNames,classAndSections from topStudentDailyAttendancePercentageCal where custIds="+custId+" and academicYearIds="+academicYearId+" and topTotalPercentage!=0.0 group by studId order by topTotalPercentage desc,studNames asc limit 5");
						  if(!ObjectFunctions.isNullOrEmpty(top5StudentMonthlyAttendanceList)){
							 for(Object[] obj : top5StudentMonthlyAttendanceList){
								 if(!ObjectFunctions.isNullOrEmpty(obj)){
									 if((Double.valueOf(obj[0].toString()) != 0.0)){
										 StudentDailyAttendance studentDailyAttendance = new StudentDailyAttendance();
										 studentDailyAttendance.setAttendancePercentage(Double.valueOf(obj[1].toString()));
										 studentDailyAttendance.setAdmissionNumber(obj[2].toString());
										 studentDailyAttendance.setStudentName(obj[3].toString());
										 studentDailyAttendance.setClassNameAndSection(obj[4].toString());
										 getStudentDailyAttendanceList().add(studentDailyAttendance);
									 }
								 }
							}
					    }
					 studentMonthlyOrDailyAttendanceList = null;
					}
					boolean topStudentsMarksDetails = false;
					List<Object[]> topStudentsMarks = null;
					adminManager.updateAndGetTopStudentsMarksDetails(custId,academicYearId);
					List<Object[]> fifthpercen = adminManager.getAll("select topPercentage from topStudentMarksPercentageCal where custId="+custId+" and academicYearId="+academicYearId+"  and topPercentage!=0.0  group by studId order by topPercentage desc limit 5");
					log.info(fifthpercen);
					if(!ObjectFunctions.isNullOrEmpty(fifthpercen) && fifthpercen.size()>=5){
						 if(!ObjectFunctions.isNullOrEmpty(fifthpercen.get(4))){
							topStudentsMarks = adminManager.getAll("select * from topStudentMarksPercentageCal where custId="+custId+" and academicYearId="+academicYearId+" and topPercentage>="+fifthpercen.get(4)+" group by studId order by topPercentage desc");
							if(topStudentsMarks.size() > 5){
								if((topStudentsMarks.size()-1) >= 1){
									topStudentsMarksDetails = true;
								}
							}	
						 }
					}
					List<Object[]> stduentsMarks = userManager.getAll("select studId,topPercentage,admisNos,studNames,classAndSections from topStudentMarksPercentageCal where custId="+custId+" and academicYearId="+academicYearId+" and topPercentage!=0.0  group by studId order by topPercentage desc,studNames asc limit 5");
					 if(!ObjectFunctions.isNullOrEmpty(stduentsMarks)){
						 for(Object[] obj : stduentsMarks){
							 if(!ObjectFunctions.isNullOrEmpty(obj)){
								 ViewStudentMarksDetails viewStudentMarksDetails = new ViewStudentMarksDetails();
								 if((Double.valueOf(obj[0].toString()) != 0.0)){
									 viewStudentMarksDetails.setMarksPercentage(Double.valueOf(obj[1].toString()));
									 viewStudentMarksDetails.setAdmissionNumber(obj[2].toString());
									 viewStudentMarksDetails.setStudentName(obj[3].toString());
									 viewStudentMarksDetails.setClassName(obj[4].toString());
								 }
								 getViewStudentMarksDetailsList().add(viewStudentMarksDetails);
							 }
						  }
				     }
					 stduentsMarks = null;
					 boolean topteachersSubjectsDetails = false;
					List<Object[]> topteachersSubjects = null;
					adminManager.updateAndGetTopStaffPerformanceDetails(custId,academicYearId);
					List<Object[]> topFifthpercen = adminManager.getAll("select topStaffPercentage from topStaffPeromancePercentageCal where custId="+custId+" and academicYearId="+academicYearId+" group by staffId  order by topStaffPercentage desc limit 5");
					log.info(topFifthpercen);
					if(!ObjectFunctions.isNullOrEmpty(topFifthpercen) && topFifthpercen.size()>=5){
						if(!ObjectFunctions.isNullOrEmpty(topFifthpercen.get(4))){
							topteachersSubjects = adminManager.getAll("select * from topStaffPeromancePercentageCal where custId="+custId+" and academicYearId="+academicYearId+" and topStaffPercentage>="+topFifthpercen.get(4)+" group by staffId order by topStaffPercentage desc");
							if(topteachersSubjects.size() > 5){
								if((topteachersSubjects.size()-5) >= 1){
									topteachersSubjectsDetails = true;
								}
							}	
						}
					}
					StringBuffer querys = new StringBuffer("select staffId,topStaffPercentage,staffNames from topStaffPeromancePercentageCal  where custId="+custId+" and academicYearId="+academicYearId+" group by staffId order by topStaffPercentage desc,staffNames asc limit 5");
					log.info(querys.toString());
					List<Object[]> staffPerformance = userManager.getAll(querys.toString());
					 if(!ObjectFunctions.isNullOrEmpty(staffPerformance	)){
						 for(Object[] obj : staffPerformance){
							 if(!ObjectFunctions.isNullOrEmpty(obj)){
								 if(Double.valueOf(obj[0].toString()) != 0) {
									 ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = new ViewStaffPersonAccountDetails();
									 viewStaffPersonAccountDetails.setSubjectsMarksPercentage(Double.valueOf(obj[1].toString()));
									 viewStaffPersonAccountDetails.setFirstName(obj[2].toString());
									 getViewStaffPersonAccountDetailsList().add(viewStaffPersonAccountDetails);
								 }
							 }
						  }
				     }
					 staffPerformance = null;
					StringBuffer query = new StringBuffer("select email from vw_staffDetails where  custId="+customer.getId()+" and academicYearId<=").append(academicYearId).append(" and status='Y' and (email!='' AND email is not null) and description is null");
					List<Object> staffEmails = adminManager.getAll(query.toString());
					String[] emailAddresses = new String[ConvertUtil.convertListToSet(staffEmails).size()];
					List<Object> emailsIdsList = new ArrayList(new HashSet(staffEmails));
					if(!ObjectFunctions.isNullOrEmpty(emailsIdsList)){
						int i = 0;
						for (Object staffEmailIds : emailsIdsList) {
							emailAddresses[i] = staffEmailIds.toString();
							i++;
						}
						//String[] emailAddresses = new String[2];
						//emailAddresses[0] = "sunanda@hyniva.com";
						//emailAddresses[1] = "sunanda.mca2@gmail.com";
						MailUtil mailUtil = new MailUtil(emailAddresses,"Auto Monthly Report from EazySchool",custId, customer.getSender(),"Administrator",this.getContactFromEmail(customer));
						mailUtil.sendEmailToAllCustomersTheirTopStduentsDetails(getStudentMonthlyAttendanceList(),getStudentDailyAttendanceList(),getViewStudentMarksDetailsList(),getViewStaffPersonAccountDetailsList(),customer.getCustEmail(),customer.getOrganization(),this.getContactFromEmail(customer),topStudentsCount,topStudentsMarksDetails,topteachersSubjectsDetails);
						mailUtil = null;
						emailAddresses = null;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendEmailFeePaidAndUnPaidDetailsSummary(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'sendEmailFeePaidAndUnPaidDetailsSummary' method");
			log.debug("Entering 'sendEmailFeePaidAndUnPaidDetailsSummary' method");
		}
		try {
			String[] emailAddresses =null;
			String subject=null;
			List<Object[]> paidAndUnpaidDetails =  adminManager.getCustomerFeePaidAndUnpaiddetailSummary();
			if(!ObjectFunctions.isNullOrEmpty(paidAndUnpaidDetails)){
				for(Object[] object : paidAndUnpaidDetails){
					List<Object[]> allUsers = adminManager.getAll("select staffEmail,fullName,username,roleId from vw_allUsers where custId="+object[1].toString()+" and roleId in (1,11,12,15,16,32) and staffEmail is not null");
					if(!ObjectFunctions.isNullOrEmpty(allUsers)){
						for(Object[] obj : allUsers){
							if(!ObjectFunctions.isNullOrEmpty(obj[0])){
								if(Long.valueOf(obj[3].toString())==1){
									emailAddresses = new String[2];
									emailAddresses[1]=obj[2].toString();
								}else
									emailAddresses = new String[1];
								emailAddresses[0]=obj[0].toString();
								StringBuffer br = new StringBuffer();
								br = prepareStudentFeePaidAndUnpaidDetailSummary(br,obj[1].toString(),object[3].toString(),object[4].toString(),object[5].toString(),object[6].toString());
								if(Double.valueOf(object[3].toString())>0)
									subject= "Monthly Auto Fee Report as on ("+DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, new Date())+")";
								else
									subject="Monthly Auto Fee Report";
								MailUtil mailUtil = new MailUtil(emailAddresses,subject,br.toString(), getUser(), null,"messages@eazyschool.com");
								mailUtil.sendFeePaidAndUnpaidSummary("messages@eazyschool.com");
							}
							subject=null;
							emailAddresses=null;
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public StringBuffer prepareStudentFeePaidAndUnpaidDetailSummary(StringBuffer sb,String fullName,String feeAmount,String paidAmount,String balanceAmount,String configureStatus)throws URTUniversalException {
		try {
			sb.append("Dear "+fullName+",");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			if("Y".equalsIgnoreCase(configureStatus)){
				sb.append("The monthly fee collected report as on "+DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, new Date())+" is as follows :  ");
				sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
				sb.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead style='background:#076dbf;color:#FFF;'><tr><th class='head'>Description</th><th class='head'>Amount</th></tr></thead>");
				sb.append("<tr><td>Total Amount </td>");
				sb.append("<td align='right'>"+feeAmount+"</td></tr>");
				sb.append("<tr><td>Collected Amount </td>");
				sb.append("<td align='right'>"+paidAmount+"</td></tr>");
				sb.append("<tr><td>Due Amount </td>");
				sb.append("<td align='right'>"+balanceAmount+"</td></tr>");
			    sb.append("</table>");
			}else{
				sb.append("<b>Kindly start using the fee module to start getting auto fee report</b>");
			}
			
			sb.append( "<br/><br/>" );
			sb.append("**NOTE: This is an automated email from EazySchool Software. Please DO NOT REPLY to this email.");
			return sb;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
}