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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.Reminder;
import com.urt.persistence.model.common.TaskDetails;
import com.urt.persistence.model.common.UserAutoReportsConfiguration;
import com.urt.persistence.model.common.ViewTaskDetailsAndTaskHistory;
import com.urt.persistence.model.common.WorkingDays;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.service.manager.interfaces.user.UserManager;
import com.urt.util.email.MailUtil;
import com.urt.webapp.action.base.BaseAction;



/**
 * <p>
 *  This job removes from the system all expired events and messages
 *  including churchwide messages/events
 * </p>cd 
 * 
 * @author  Seshu S
 */
public class MobileAndEmailRemaindersBatchJob extends BaseAction implements Job {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6137644058516625485L;
	private static Log _log = LogFactory.getLog(MobileAndEmailRemaindersBatchJob.class);
  //  private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
    /**
     * <p>
     * Empty constructor for job initilization
     * </p>
     * <p>
     * Quartz requires a public empty constructor so that the
     * scheduler can instantiate the class whenever it needs.
     * </p>
     */
    public MobileAndEmailRemaindersBatchJob() {
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     * 
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
    /********************************************************************
     * Date              Name               Description
     * ========          ============       ==================
     * Oct 2, 2013       Seshu		        Job for sending birth day wishes. 
    /********************************************************************/
	@Autowired
	private AdminManager adminManager;
	@Autowired
	private UserManager	userManager;
	@Autowired
	private StaffManager staffManager;
	@Autowired
	private CommunicationManager communicationManager;
	@Autowired
	private StudentManager studentManager;
	
    public void execute(JobExecutionContext context)
        throws JobExecutionException {
    	       
       
    	String jobName = context.getJobDetail().getDescription();
        _log.info("MobileAndEmailRemaindersBatchJob Batch Process Started : " + jobName + " executing at " + new Date());
		try {
			
			/*if(ObjectFunctions.isNullOrEmpty(adminManager)){
				adminManager=(AdminManager)SpringContextAware.getBean("adminManager");
            }
			if(ObjectFunctions.isNullOrEmpty(userManager)){
				userManager=(UserManager)SpringContextAware.getBean("userManager");
            }
			if(ObjectFunctions.isNullOrEmpty(staffManager)){
				staffManager=(StaffManager)SpringContextAware.getBean("staffManager");
            }
			if(ObjectFunctions.isNullOrEmpty(studentManager)){
				studentManager=(StudentManager)SpringContextAware.getBean("studentManager");
            }
			if(ObjectFunctions.isNullOrEmpty(communicationManager)){
				communicationManager=(CommunicationManager)SpringContextAware.getBean("communicationManager");
            }*/
			StringBuffer query = new StringBuffer(" status='Y' and (checkEmailService = 'Y' or checkMobileService='Y')");
			List<Customer> customers = adminManager.getAll(Customer.class, query.toString());
			AcademicYear academicYear = null;
			SMSServiceProviders smsServiceProvider=null;
			if(ObjectFunctions.isNullOrEmpty(customers)){
				_log.info("No customers enabled mobile or email services.");
			}else{
				Reminder reminder = null;
				for(Customer customer : customers){
					academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
					reminder = (Reminder) adminManager.get(Reminder.class, "custId="+customer.getId());
					smsServiceProvider=(SMSServiceProviders)  adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
					if(ObjectFunctions.isNullOrEmpty(academicYear)){
						_log.info("Academic year not created for customer Id - "+customer.getId());
					}else{
						log.debug("QA customer "+customer.getOrganization());
						//For sending Birth day wishes to students.
						List<UserAutoReportsConfiguration> configurationsList=adminManager.getAll(UserAutoReportsConfiguration.class, "custId="+customer.getId()+" and status='Y'");
							for(UserAutoReportsConfiguration reportsConfiguration : configurationsList){
								if("Birthday Wishes".equalsIgnoreCase(reportsConfiguration.getAutoReportsTypes().getReportName())){
									sendBirthdayWishesToStudents(academicYear,customer,smsServiceProvider);
									sendBirthdayWishesToStaff(academicYear,customer,smsServiceProvider);
								}else if ("Fee Reminder".equalsIgnoreCase(reportsConfiguration.getAutoReportsTypes().getReportName())) {
									remindersForAllFeeModules(academicYear,customer,smsServiceProvider);
								}else if ("Exam Schedules Reminder".equalsIgnoreCase(reportsConfiguration.getAutoReportsTypes().getReportName())) {
									sendExamAlertMailAndMobileToParents(academicYear,customer,smsServiceProvider);
								}
							}
						
						//sendHolidayAlertsToMobiles(academicYear,customer);
						//sendSmsStausEmailToSupportTeam(academicYear,customer);
						updateSuspendorBlacklistStudent(academicYear,customer);
						sendTaskReminderToUser(academicYear,customer,smsServiceProvider);
					}
					customer = null;
				}
				//sendSmsStausEmailToSupportTeam();
				academicYear = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	_log.info("MobileAndEmailRemaindersBatchJob Batch Completed successfully.....");
    }

	public void sendBirthdayWishesToStudents(AcademicYear academicYear,Customer customer,SMSServiceProviders smsServiceProvider)
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'sendBirthdayWishesToStudents' method");
			System.out
					.println("Entering 'sendBirthdayWishesToStudents' method");
		}
		try {
				List<Object[]> studentList = null;
				String fullStudentName = null;
				String mobileNumber = null;
				String mobileNumbers = null;
				String parentEmail = null;
				Messages message = null;
				Set<String> mobileNumberSet = null;
				String mobileType = customer.getMobileType();
				StringBuffer query = new StringBuffer("select parentEmail,fullName,");
				if("B".equalsIgnoreCase(mobileType))
					query.append("mobileNumber,secondaryMobileNumber,anotherMobileNumber,anotherSecondaryMobileNumber");
				else if("P".equalsIgnoreCase(mobileType))
					query.append("mobileNumber,anotherMobileNumber");
				else
					query.append("secondaryMobileNumber,anotherSecondaryMobileNumber");
				query.append(" ,addressType from vw_studentDetailsByDays where academicYearId=").append(academicYear.getId());
				if(!"S".equalsIgnoreCase(mobileType))
					query.append(" and (((mobileNumber !='' and mobileNumber is not null ) or (anotherMobileNumber !='' and anotherMobileNumber is not null )) or (parentEmail !='' and parentEmail is not null))");
				else
					query.append(" and (((secondaryMobileNumber !='' and secondaryMobileNumber is not null) or (anotherSecondaryMobileNumber !='' and anotherSecondaryMobileNumber is not null )) or (parentEmail !='' and parentEmail is not null))");
				studentList = adminManager.getAll(query.toString());
				if(ObjectFunctions.isNotNullOrEmpty(studentList)){
					for (Object[] obj1 : studentList) {
						if (!ObjectFunctions.isNullOrEmpty(obj1)) {
							if(!ObjectFunctions.isNullOrEmpty(obj1[1]))
								fullStudentName = obj1[1].toString();
							if(academicYear.isSendBirthdayAlerts()){
								mobileNumberSet = new HashSet<String>();
								if("B".equalsIgnoreCase(mobileType)){
									if("R".equalsIgnoreCase(obj1[6].toString())){
										if(!ObjectFunctions.isNullOrEmpty(obj1[3]))
											mobileNumber = obj1[3].toString();
										if(!ObjectFunctions.isNullOrEmpty(obj1[2]))
											mobileNumbers = obj1[2].toString();
									}else{
										if(!ObjectFunctions.isNullOrEmpty(obj1[5]))
											mobileNumber = obj1[5].toString();
										if(!ObjectFunctions.isNullOrEmpty(obj1[4]))
											mobileNumbers = obj1[4].toString();
									}
									mobileNumberSet.addAll(adminManager.addMobileNumberBasedOnSettings(mobileType,mobileNumbers,mobileNumber));
									mobileNumber = null;
									mobileNumbers = null;
								}else{
									if("R".equalsIgnoreCase(obj1[4].toString())){
										if(!ObjectFunctions.isNullOrEmpty(obj1[2]))
											mobileNumberSet.add(obj1[2].toString());
									}else{
										if(!ObjectFunctions.isNullOrEmpty(obj1[3]))
											mobileNumberSet.add(obj1[3].toString());
									} 
								}
								int sentSmsCount=staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
								int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
								if (customer.isCheckMobileService() && ObjectFunctions.isNotNullOrEmpty(mobileNumberSet) && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
									//mobileNumberSet = new HashSet<String>();
									//mobileNumberSet.add(mobileNumber);
									log.debug("mobile numbers :"+ mobileNumberSet);
									message = new Messages();
									StringBuffer msgContent = new StringBuffer();
									msgContent.append("Dear Parent, ");
									if(customer.getId()==50){
										msgContent.append(" This is to wish ");
										msgContent.append(fullStudentName);
										msgContent.append(" a happy birthday. From ");
									}else{
										msgContent.append(customer.getOrganization());
										msgContent.append(" Wishes a Very Happy Birthday to your child ");
										msgContent.append(fullStudentName);
										msgContent.append(",Thank You Principal from ");
									}
									message.setMessageDescription(msgContent.toString());
									log.debug("message content :"+ msgContent);
									log.debug("getMessages().setMessageDescription-  ---"+ message.getMessageDescription());
									message.setStatus("BA");   
									message.setCustomer(customer);
									message.setSmsSenderId(customer.getSender());
									message.setAcademicYear(academicYear);
									message.setPurposeType("Regd: Birthday Wishes");
									message.setSenderName("System");
									//setAnyTitle("throwFromCamBatch");
									communicationManager.deliverSms(message,mobileNumberSet,smsServiceProvider);
									mobileNumberSet = null;
									message = null;
									msgContent = null;
								}
							}else{
								_log.info("Mobile Birthday wishes alerts are not enabled for customer - ."+customer.getId());
							}
							if(academicYear.isSendBirthdayAlertsByEmail()){
								if(!ObjectFunctions.isNullOrEmpty(obj1[0]))
									parentEmail = obj1[0].toString();
								if (customer.isCheckEmailService() && StringFunctions.isNotNullOrEmpty(parentEmail)) {
									String[] emailAddresses = new String[1];
									emailAddresses[0] = parentEmail;
									MailUtil mailUtil = new MailUtil(emailAddresses,"Birthday wishes from "+ customer.getOrganization()+ ".",customer.getId(), customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
									mailUtil.setContactEmail(customer.getContactEmail());
									mailUtil.setContactPasword(customer.getContactPassword());
									mailUtil.sendMailForAllParentsForBirthdayDateAlert(parentEmail,fullStudentName,customer.getOrganization(),"Student",adminManager.getContactFromEmail(customer),customer);
											mailUtil = null;
										emailAddresses = null;
								}
								
							}else{
								_log.info("Email Birthday wishes alerts are not enabled for customer - ."+customer.getId());
							}
							obj1 = null;
							mobileNumber = null;
							parentEmail = null;
							fullStudentName = null;
						}
					}
				}else{
					_log.info("Email or mobile is not found for students.(OR) No students found for sending birthday wishes.");
				}
				studentList = null;
				mobileNumberSet = null;
				query = null;
				studentList = null;
				academicYear = null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void remindersForAllFeeModules(AcademicYear academicYear,Customer customer,SMSServiceProviders smsServiceProvider) {
		if (log.isDebugEnabled()) {
			log.debug("QA Entering 'remindersForAllFeeModules' method");
			log.debug(" QA Entering 'remindersForAllFeeModules' method");
		}
		try {		
			List<SchoolTerms> schoolTermsList = adminManager.getFeeTermsByRemainderDays(academicYear.getId());
			if(ObjectFunctions.isNullOrEmpty(schoolTermsList)){
				_log.info("Fee due terms are not available for the customer - ."+customer.getId());
			}else{
				if (!ObjectFunctions.isNullOrEmpty(schoolTermsList)) {
					MailUtil mailUtil=null;
					String[] emailAddresses=null;
					long boardingPointId=0;
					int remainderDays = 0;
					int daysCount = 0;
					List<ViewStudentClassDetails> studentFeeList = null;
					Messages message = null;
					Set<String> mobileNumber = null;
					Date todayDate = DateFormatter.getTodayDate(DateFormatter.YYYY_MM_DD_PATTERN);
					String dueFeeCont = "Dear Parent, You have exceeded the due date in paying your children's school fee for the <Term Name>. Please make the payment as soon as possible to avoid the late payment charges. ";
					for (SchoolTerms schoolTerms : schoolTermsList) {
						if(StringFunctions.isNotNullOrEmpty(schoolTerms.getNoOfDays())){
							remainderDays = Integer.valueOf(schoolTerms.getNoOfDays());
							String mobileType = customer.getMobileType();
							List<ClassName> classList=adminManager.getAll(ClassName.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" order by sortingOrder");
							if(ObjectFunctions.isNotNullOrEmpty(classList)){
								daysCount = DateFunctions.daysBetween(todayDate, schoolTerms.getDueDate());
								for(ClassName className : classList){
									List<Fee> fees = adminManager.getAll(Fee.class, "custId="+ customer.getId() + " and academicYearId="+ academicYear.getId() + " and ( classId="+ className.getId() + " || routeBoardingPointId >0 ) and feeAmount > 0");
									if (ObjectFunctions.isNotNullOrEmpty(fees)) {
										StringBuffer query = new StringBuffer(" custId="+ customer.getId()+ " and academicYearId="+ academicYear.getId()+ " and classId="+className.getId()+" and status='"+ Constants.YES_STRING+ "' and studDiscontinueDesc is null ");
										if(Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName())){
											query.append(" and transportMode='T' and vehicleAcademicDetailsId !=0 and boardingPointId !=0");
											if(!"S".equalsIgnoreCase(mobileType)){
												query.append(" and (((mobileNumber !='' and mobileNumber is not null ) or (anotherMobileNumber !='' and anotherMobileNumber is not null)) or (parentEmail !='' and parentEmail is not null))");
											}else
												query.append(" and (((secondaryMobileNumber !='' and secondaryMobileNumber is not null ) or (anotherSecondaryMobileNumber !='' and anotherSecondaryMobileNumber is not null)) or (parentEmail !='' and parentEmail is not null))");
											log.debug(query.toString());
											studentFeeList = adminManager.getAll(ViewStudentClassDetails.class, query.toString());
										}else{
											if(!"S".equalsIgnoreCase(mobileType)){
												query.append(" and (((mobileNumber !='' and mobileNumber is not null) or (anotherMobileNumber !='' and anotherMobileNumber is not null)) or (parentEmail !='' and parentEmail is not null))");
											}else
												query.append(" and (((secondaryMobileNumber !='' and secondaryMobileNumber is not null ) or (anotherSecondaryMobileNumber !='' and anotherSecondaryMobileNumber is not null)) or (parentEmail !='' and parentEmail is not null))");
											log.debug(query.toString());
											studentFeeList = adminManager.getAll(ViewStudentClassDetails.class, query.toString());
										}
										for (ViewStudentClassDetails studentDetails : studentFeeList) {
											if(Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName())){
												if("Y".equals(academicYear.isTransportFeeByBoardingPoint()))
													boardingPointId=studentDetails.getBoardingPointId();
											}
											long totalTermAmount = adminManager.getFeeTotalAmountByTerm("Fee", customer.getId(), studentDetails.getClassId(),academicYear.getId(), schoolTerms.getId(), studentDetails.getCategoryId(),boardingPointId);
											long totalPaidAmount = adminManager.getStudentPaidAmountByClassId("vw_studentFeePaymentDetails",studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), schoolTerms.getId(),boardingPointId);
											prepareFeeIds(schoolTerms.getId(), studentDetails.getClassId(), studentDetails.getCategoryId(),studentDetails.getBoardingPointId(),academicYear.isTransportFeeByBoardingPoint());
											long discAmount = adminManager.getStudentDiscountAmountByClassId("studentFeePaidDetails",studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), getAnyId(),0);
											long dueAmount = totalTermAmount- (totalPaidAmount + discAmount);
											int sentSmsCount=staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
											int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
											if (daysCount == remainderDays) {
												//log.debug("Before Due Date");
												if (totalTermAmount > (totalPaidAmount + discAmount)) {
													long totalUnpaidPreviewAmount=adminManager.getStudentPaidAmountByTermIdAndClassId(studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), schoolTerms.getId(),String.valueOf(schoolTerms.getDueDate()),schoolTerms.getFeeSettingId(),"");
													if (customer.isCheckEmailService() && StringFunctions.isNotNullOrEmpty(studentDetails.getParentEmail())) {
														emailAddresses = new String[1];
														emailAddresses[0] = studentDetails.getParentEmail();
														mailUtil=new MailUtil(emailAddresses,"Fee Payment Remainder",customer.getId(),customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
														mailUtil.setContactEmail(customer.getContactEmail());
														mailUtil.setContactPasword(customer.getContactPassword());
														mailUtil.sendMailForAllParentsForFeeAmount(studentDetails,schoolTerms,dueAmount,customer.getOrganization(),totalUnpaidPreviewAmount,adminManager.getContactFromEmail(customer));
														mailUtil=null;
														emailAddresses=null;
													}
													if (customer.isCheckMobileService() && (StringFunctions.isNotNullOrEmpty(studentDetails.getMobileNumber()) || StringFunctions.isNullOrEmpty(studentDetails.getAnotherMobileNumber())) && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
														log.debug("mobileNumber :"+studentDetails.getMobileNumber());
														long totalamount=totalUnpaidPreviewAmount+dueAmount;
														message = new Messages();
														String messageContent=null;
											        	StringBuffer dueFeeContent=new StringBuffer();
														if (totalUnpaidPreviewAmount>0) {
															messageContent=dueFeeContent.append(dueAmount).append("/-,").append(" Previous Due Amount="+totalUnpaidPreviewAmount+"").append(", total fee of Rs."+totalamount).toString();
														}else{
															messageContent=dueAmount+"" ;
														}
														message.setMessageDescription("Dear Parent, This is a friendly reminder that your child "+studentDetails.getFullName()+", "+studentDetails.getClassAndSection()+", "+schoolTerms.getTermName()+" fee of RS. "+messageContent+". Due on "+schoolTerms.getDueDateStr()+". Please ignore if paid. From ");
														message.setCustomer(customer);
														message.setSmsSenderId(customer.getSender());
														message.setStatus("A"); // i will change status "FR" to A here send message aumatic thats way i'm change the status FR to A RaviTeja
														message.setSenderName("System");
														setAnyTitle("throwFromCamBatch");
														message.setAcademicYear(academicYear);
														message.setPurposeType("Regd: Fee Reminder");
														mobileNumber = new HashSet<String>();
														mobileNumber.addAll(adminManager.addMobileNumbersBasedOnAddressType(mobileType,studentDetails.getMobileNumber(),studentDetails.getSecondaryMobileNumber(),studentDetails.getAnotherMobileNumber(),studentDetails.getAnotherSecondaryMobileNumber(),studentDetails.getAddressType()));
														/*if("B".equalsIgnoreCase(customer.getMobileType()))
															mobileNumber = adminManager.addMobileNumberBasedOnSettings(customer.getMobileType(),studentDetails.getMobileNumber(),studentDetails.getSecondaryMobileNumber());
														else if("P".equalsIgnoreCase(customer.getMobileType()))
															mobileNumber.add(studentDetails.getMobileNumber());
														else
															mobileNumber.add(studentDetails.getSecondaryMobileNumber());*/
														if(!ObjectFunctions.isNullOrEmpty(mobileNumber))
															communicationManager.deliverSms(message, mobileNumber,smsServiceProvider);
														message = null;
														mobileNumber = null;
													}
												}
											}
											if ((totalPaidAmount + discAmount) < totalTermAmount) {
												if(schoolTerms.getDueDate1() !=null || schoolTerms.getDueDate2() !=null ){
													if (todayDate.compareTo(schoolTerms.getDueDate1())==0 || todayDate.compareTo(schoolTerms.getDueDate2())==0) {
														String isDueDateVal="";
														long totalUnpaidPreviewAmount=0;
														if(todayDate.compareTo(schoolTerms.getDueDate1())==0 && schoolTerms.getDueDate1() !=null){
															isDueDateVal="D1";
															totalUnpaidPreviewAmount=adminManager.getStudentPaidAmountByTermIdAndClassId(studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), schoolTerms.getId(),String.valueOf(schoolTerms.getDueDate1()),schoolTerms.getFeeSettingId(),"D1");
														}else{
															isDueDateVal="D2";
															totalUnpaidPreviewAmount=adminManager.getStudentPaidAmountByTermIdAndClassId(studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), schoolTerms.getId(),String.valueOf(schoolTerms.getDueDate2()),schoolTerms.getFeeSettingId(),"D2");
														}/*else{
															totalUnpaidPreviewAmount=adminManager.getStudentPaidAmountByTermIdAndClassId(studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), schoolTerms.getId(),String.valueOf(schoolTerms.getDueDate()),schoolTerms.getFeeSettingId(),"");
														}*/
														if (customer.isCheckEmailService() && StringFunctions.isNotNullOrEmpty(studentDetails.getParentEmail())) {
															emailAddresses = new String[1];
															emailAddresses[0] = studentDetails.getParentEmail();
															mailUtil=new MailUtil(emailAddresses,"Fee Exceed Payment Remainder",customer.getId(),customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
															mailUtil.setContactEmail(customer.getContactEmail());
															mailUtil.setContactPasword(customer.getContactPassword());
															mailUtil.sendMailForAllParentsForFeeAmountCrossDueDate(studentDetails, schoolTerms,dueAmount,customer.getOrganization(),totalUnpaidPreviewAmount,isDueDateVal,adminManager.getContactFromEmail(customer));
															mailUtil=null;
															emailAddresses=null;
														}
														if (customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(studentDetails.getMobileNumber()) && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
															message = new Messages();
															message.setMessageDescription(dueFeeCont.replaceAll("<Term Name>", schoolTerms.getTermName()));
															message.setStatus("A");
															message.setCustomer(customer);
															message.setSmsSenderId(customer.getSender());
															message.setSenderName("System");
															setAnyTitle("throwFromCamBatch");
															message.setAcademicYear(academicYear);
															message.setPurposeType("Regd: Fee late payment");
															mobileNumber = new HashSet<String>();
															mobileNumber.addAll(adminManager.addMobileNumbersBasedOnAddressType(mobileType,studentDetails.getMobileNumber(),studentDetails.getSecondaryMobileNumber(),studentDetails.getAnotherMobileNumber(),studentDetails.getAnotherSecondaryMobileNumber(),studentDetails.getAddressType()));
															/*if("B".equalsIgnoreCase(mobileType))
																mobileNumber = adminManager.addMobileNumberBasedOnSettings(mobileType,studentDetails.getMobileNumber(),studentDetails.getSecondaryMobileNumber());
															else if("P".equalsIgnoreCase(mobileType))
																mobileNumber.add(studentDetails.getMobileNumber());
															else
																mobileNumber.add(studentDetails.getSecondaryMobileNumber());*/
															if(!ObjectFunctions.isNullOrEmpty(mobileNumber))
																communicationManager.deliverSms(message, mobileNumber,smsServiceProvider);
															message = null;
															mobileNumber = null;
														}
													}
												}
											}
											studentDetails = null;
											totalTermAmount = 0;
											totalPaidAmount = 0;
											dueAmount = 0;
											boardingPointId = 0;
										}
									}
								}
							}
						studentFeeList = null;
						schoolTerms = null;
					}
				}
					schoolTermsList = null;
					message = null;
					emailAddresses = null;
					mobileNumber = null;
					dueFeeCont = null;
					todayDate = null;
					mailUtil = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void sendExamAlertMailAndMobileToParents(AcademicYear academicYear,Customer customer,SMSServiceProviders smsServiceProvider){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'sendExamAlertMailAndMobileToParents' method");
		}
		try {
			List<ExamSchedules> examSchedules = adminManager.getExamSchedulesForSendingMobileAlerts(academicYear.getId());
			if(ObjectFunctions.isNotNullOrEmpty(examSchedules)){
				List<ExamSchedules> subjectWiseExamSchedule  = null;
				List<Object[]> studentsContactDetails =null;
				List studentsContactDetailsStr =  null;
				HashSet<String> mobileNumberSet = new HashSet<String>();
				String messageCont = null;
				Messages message = null;
				StudyClass classSection = null;
				StringBuffer query = null;
				MailUtil mailUtil= null;
				Object[] studentInfo = null;
				String[] emailAddress = null;
				String mobileType = customer.getMobileType();
				for(ExamSchedules examSchedule : examSchedules){
					classSection = examSchedule.getClassSection();
					log.debug("Class Name : "+classSection.getClassAndSection()+", Start Date : "+examSchedule.getStartDateStr());
					int sentSmsCount=staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
					int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
					if (customer.isCheckMobileService() && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
						query = new StringBuffer("select ");
						if("B".equalsIgnoreCase(mobileType))
							query.append("mobileNumber,secondaryMobileNumber,anotherMobileNumber,anotherSecondaryMobileNumber");
						else if("P".equalsIgnoreCase(mobileType))
							query.append("mobileNumber,anotherMobileNumber");
						else
							query.append("secondaryMobileNumber,anotherSecondaryMobileNumber");
						
						query.append(" ,addressType from vw_studentClassDetails where classSectionId=").append(classSection.getId())
						.append(" and academicYearId=").append(academicYear.getId()).append(" and custId=").append(customer.getId()).append(" and status='Y' ");
						if(!"S".equalsIgnoreCase(mobileType))
							query.append(" and ((mobileNumber !='' and mobileNumber is not null ) or (anotherMobileNumber !='' and anotherMobileNumber is not null))");	
						else
							query.append(" and ((secondaryMobileNumber !='' and secondaryMobileNumber is not null ) or (anotherSecondaryMobileNumber !='' and anotherSecondaryMobileNumber is not null ))");	
						
						//if("B".equalsIgnoreCase(mobileType))
							studentsContactDetails = adminManager.getAll(query.toString());
						/*else
							studentsContactDetailsStr = adminManager.getAll(query.toString());*/
						
						if(ObjectFunctions.isNotNullOrEmpty(studentsContactDetails)){
							String mobileNumber = null;
							String mobileNumbers = null;
							for(Object[] obj : studentsContactDetails){
								if("B".equalsIgnoreCase(mobileType)){
									if("R".equalsIgnoreCase(obj[4].toString())){
										if(!ObjectFunctions.isNullOrEmpty(obj[1]))
											mobileNumber = obj[1].toString();
										if(!ObjectFunctions.isNullOrEmpty(obj[0]))
											mobileNumbers = obj[0].toString();
									}else{
										if(!ObjectFunctions.isNullOrEmpty(obj[3]))
											mobileNumber = obj[3].toString();
										if(!ObjectFunctions.isNullOrEmpty(obj[2]))
											mobileNumbers = obj[2].toString();
									}
									mobileNumberSet.addAll(adminManager.addMobileNumberBasedOnSettings(mobileType,mobileNumbers,mobileNumber));
									mobileNumbers = null;
									mobileNumber = null;
								}else{
									if("R".equalsIgnoreCase(obj[2].toString())){
										if(!ObjectFunctions.isNullOrEmpty(obj[0]))
											mobileNumberSet.add(obj[0].toString());
									}else{
										if(!ObjectFunctions.isNullOrEmpty(obj[1]))
											mobileNumberSet.add(obj[1].toString());
									}
								}
						  }
						}/*else if(ObjectFunctions.isNotNullOrEmpty(studentsContactDetailsStr))
							mobileNumberSet.addAll(studentsContactDetailsStr);*/
						if(ObjectFunctions.isNotNullOrEmpty(mobileNumberSet)){
							message = new Messages();
							messageCont = examSchedule.getExamType().getMobileContentDesc();
							//Dear Parent Seccond Term 1 examination is going to start from  30/09/2012  for your children Nikhil. Thank you svms
							String msgContent = messageCont.replaceAll("<examination>",classSection.getClassAndSection()+","+examSchedule.getExamType().getExamType());
							msgContent = msgContent.replaceAll("<date>",examSchedule.getStartDateStr());
							//msgContent = msgContent.replaceAll("<Student Name>","");
							msgContent = msgContent.replaceAll("<school name>","");
							message.setMessageDescription(msgContent);
							log.debug(msgContent);
							message.setStatus("EX");
							message.setCustomer(customer);
							message.setSmsSenderId(customer.getSender());
							message.setAcademicYear(academicYear);
							message.setPurposeType("Regd: Exam schedule");
							message.setSenderName("System");
							setAnyTitle("throwFromCamBatch");
							communicationManager.deliverSms(message,mobileNumberSet,smsServiceProvider);
						}
						message = null;
						mobileNumberSet = null;
						studentsContactDetails = null;
						messageCont = null;
					}
					if(customer.isCheckEmailService()){
						subjectWiseExamSchedule = adminManager.getAllExamSchedulesForSendingEmailAlerts(academicYear.getId(), classSection.getId());
						if(ObjectFunctions.isNotNullOrEmpty(subjectWiseExamSchedule)){
							query = new StringBuffer("select fatherName,fullName,rollNumber,parentEmail from vw_studentClassDetails where academicYearId=").append(academicYear.getId()).append(" and status='Y' and (parentEmail!='' AND parentEmail is not null)")
							.append(" and classSectionId=").append(classSection.getId());
							studentsContactDetails=adminManager.getAll(query.toString());
							if(ObjectFunctions.isNotNullOrEmpty(studentsContactDetails)){
								for(Object stud : studentsContactDetails){
									studentInfo = (Object[])stud;
									if(!ObjectFunctions.isNullOrEmpty(studentInfo[0]) && !ObjectFunctions.isNullOrEmpty(studentInfo[1]) && !ObjectFunctions.isNullOrEmpty(studentInfo[2]) 
											&& !ObjectFunctions.isNullOrEmpty(studentInfo[3])){
										emailAddress = new String[1];
										emailAddress[0] = studentInfo[3].toString();
										log.debug("Email Id : "+emailAddress[0]);
										mailUtil=new MailUtil(emailAddress,"Welcome to URT Eazy School Management",customer.getId(),customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
										mailUtil.setContactEmail(customer.getContactEmail());
										mailUtil.setContactPasword(customer.getContactPassword());
										mailUtil.sendMailForAllParentsForExamDateAlert(studentInfo,subjectWiseExamSchedule,customer.getCustEmail(),customer.getOrganization(),adminManager.getContactFromEmail(customer));
										emailAddress = null;
									}
									mailUtil=null;
									stud = null;
								}
								studentsContactDetails = null;
								studentsContactDetailsStr  = null;
							}
						}
					}
					classSection = null;
					examSchedule = null;
					query = null;
				}
				mailUtil= null;
			}
			examSchedules = null;
			academicYear=null; 
			customer=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendHolidayAlertsToMobiles(AcademicYear academicYear,Customer customer){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'sendHolidayAlertsToMobiles' method");
			log.debug("Entering 'sendHolidayAlertsToMobiles' method");
		}
		try {
			//Date afterOneDay = DateFunctions.add(new Date(), 1);
			//SchoolHolidays holidays = (SchoolHolidays)adminManager.get(SchoolHolidays.class,"custId="+customer.getId()+" and academicyearId="+academicYear.getId()+" and startDate='"+new SimpleDateFormat("yyyy-MM-dd").format(afterOneDay)+"' and (status='H' or status='WH') group by description,startDate,endDate");
			SchoolHolidays holidays = (SchoolHolidays)adminManager.getHolidayByCustIdAndAcademicYearId(customer.getId(),academicYear.getId(),null,0,null,null,"AlertsToMobile");
			Calendar c1 = Calendar.getInstance();
			int sentSmsCount=staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
			int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
	        	if (!ObjectFunctions.isNullOrEmpty(holidays) && customer.isCheckMobileService() && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
	        		c1.setTime(holidays.getStartDate());
	    			int dayofDate = c1.get(Calendar.DAY_OF_WEEK);
	    			log.debug(dayofDate);
	    	        WorkingDays wrkdays = (WorkingDays)adminManager.get(WorkingDays.class," dayId="+dayofDate+" and academicYearId ="+academicYear.getId());
	    	        if(!ObjectFunctions.isNullOrEmpty(wrkdays)){
						Set<String> mobileNumberset = null;
						String mobileNumbers = null;
						StringBuffer smsContent = new StringBuffer();
						Messages message = null;
						List<Object[]> studentList = null;
						List staffList = null;
						String mobileNumber = null;
						String pmobileNumbers = null;
						String mobileType = customer.getMobileType();
						StringBuffer queryString = new StringBuffer("select mobileNumber from vw_staffDetails where (mobileNumber!=0 AND mobileNumber is not null) and custId=")
													.append(customer.getId()).append(" and academicyearId=").append(academicYear.getId()).append(" and status='Y'");
						staffList=adminManager.getAll(queryString.toString());
						/*StringBuffer query = new StringBuffer("select mobileNumber  from vw_studentDetails where (mobileNumber!=0 AND mobileNumber is not null) and custId=")
												.append(customer.getId()).append(" and academicyearId=").append(academicYear.getId()).append(" and status='Y'");*/
						StringBuffer query = new StringBuffer("select ");
						if("B".equalsIgnoreCase(mobileType))
							query.append("mobileNumber,secondaryMobileNumber,anotherMobileNumber,anotherSecondaryMobileNumber");
						else if("P".equalsIgnoreCase(mobileType))
							query.append("mobileNumber,anotherMobileNumber");
						else
							query.append("secondaryMobileNumber,anotherSecondaryMobileNumber");
						
						query.append(" ,addressType from vw_studentDetails where custId=").append(customer.getId()).append(" and academicYearId=").append(academicYear.getId()).append(" and status='Y' ");
						if(!"S".equalsIgnoreCase(mobileType))
							query.append(" and ((mobileNumber !='' and mobileNumber is not null ) or (anotherMobileNumber !='' and anotherMobileNumber is not null ))");	
						else
							query.append(" and ((secondaryMobileNumber !='' and secondaryMobileNumber is not null )  or (anotherSecondaryMobileNumber !='' and anotherSecondaryMobileNumber is not null ))");	
							studentList=adminManager.getAll(query.toString());
						if (!ObjectFunctions.isNullOrEmpty(studentList)) {
							smsContent.append("Dear Parent,");
							holidayContent(smsContent, holidays);
							mobileNumberset = new HashSet<String>();
							for (Object[] obj1 : studentList) {
								if("B".equalsIgnoreCase(mobileType)){
									if(!ObjectFunctions.isNullOrEmpty(obj1[1]))
										mobileNumber = obj1[1].toString();
									if(!ObjectFunctions.isNullOrEmpty(obj1[0]))
										pmobileNumbers = obj1[0].toString();
									mobileNumberset.addAll(adminManager.addMobileNumberBasedOnSettings(mobileType,pmobileNumbers,mobileNumber));
									pmobileNumbers = null;
									mobileNumber = null;
								}else{
									if(!ObjectFunctions.isNullOrEmpty(obj1[2]))
										mobileNumberset.add(obj1[2].toString());
								}
								//mobileNumber = obj1.toString();
								//mobileNumberset.add(mobileNumber);
							}
							if(!ObjectFunctions.isNullOrEmpty(mobileNumberset)){
								message = new Messages();
								message.setCustomer(customer);
								message.setSmsSenderId(customer.getSender());
								message.setAcademicYear(academicYear);
								message.setMessageDescription(smsContent.toString());
								communicationManager.deliverSms(message, mobileNumberset,smsServiceProvider);
							}
							studentList = null;
							message = null;
							smsContent = null;
							mobileNumberset = null;
						}
						if (!ObjectFunctions.isNullOrEmpty(staffList)) {
							mobileNumberset = new HashSet<String>();
							smsContent = new StringBuffer();
							smsContent.append("Dear Staff,");
							holidayContent(smsContent, holidays);
							for (Object obj : staffList) {
								mobileNumbers = obj.toString();
								mobileNumberset.add(mobileNumbers);
							}
							message = new Messages();
							message.setCustomer(customer);
							message.setSmsSenderId(customer.getSender());
							message.setAcademicYear(academicYear);
							message.setMessageDescription(smsContent.toString());
							communicationManager.deliverSms(message, mobileNumberset,smsServiceProvider);
						}
						staffList = null;
						message = null;
						smsContent = null;
						mobileNumberset = null;
				}
	    	        wrkdays = null;
	        }
			
			holidays = null;
			academicYear = null;
			customer = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public String holidayContent(StringBuffer smsContent,SchoolHolidays holidays){
		try{
			smsContent.append("Holiday(s) for ");
			smsContent.append(holidays.getDescription());
			smsContent.append(" from ");
			smsContent.append(holidays.getStartDateFormat());
			smsContent.append(" to ");
			smsContent.append(holidays.getEndDateFormat());
			smsContent.append(" Thank you from ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsContent.toString();
	 }
	 
		public void sendSmsStausEmailToSupportTeam(){
			if (log.isDebugEnabled()) {
				log.debug("Entering 'sendSmsStausEmailToSupportTeam' method");
				log.debug("Entering 'sendSmsStausEmailToSupportTeam' method");
			}
			try {
				List<Customer> customers = adminManager.getAll(Customer.class,"status='Y' and accountType='C'");
				if(!ObjectFunctions.isNullOrEmpty(customers)){
					List smsAlertsList = new ArrayList();
					for(Customer customer : customers){
						AcademicYear academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
						if(!ObjectFunctions.isNullOrEmpty(academicYear)){
							long usedSmsCount = staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
							long exceededSmsCount = ((academicYear.getAllotedsms()+academicYear.getPaidSms())- usedSmsCount);
							if(exceededSmsCount < 0){
								Customer custObj = new Customer();
								custObj.setId(customer.getId());
								custObj.setOrganization(customer.getOrganization());
								custObj.setCustEmail(customer.getCustEmail());
								custObj.setAllowedTotalSms(academicYear.getAllotedsms()+academicYear.getPaidSms());
								custObj.setOrgId(usedSmsCount);
								custObj.setOrgnizationTypeId(exceededSmsCount);
								smsAlertsList.add(custObj);
								setCustomerList(smsAlertsList);
							}
							customer = null;
							academicYear = null;
						}
					}
					if(ObjectFunctions.isNotNullOrEmpty(getCustomerList())){
				   		Set<String> emailIdsSet = new HashSet<String>();
						List<Object[]> emailsAndMobileNumbers = adminManager.getAll("select email,id from supportTeam where (email!='' AND email is not null)");
						if(!ObjectFunctions.isNullOrEmpty(emailsAndMobileNumbers)){
							String[] emailAdd = null;
							for(Object obj[] : emailsAndMobileNumbers){
								if(!ObjectFunctions.isNullOrEmpty(obj[0]))
									emailIdsSet.add(obj[0].toString());
								if(!ObjectFunctions.isNullOrEmpty(emailIdsSet)){
								    int i = 0;
								    emailAdd = new String[emailIdsSet.size()];
									for (Object emailId : emailIdsSet) {
									    if (!ObjectFunctions.isNullOrEmpty(emailId)) {
										emailAdd[i] = emailId.toString();
										emailId = null;
									    }
									    i++;
									}
								}
							}
							MailUtil mailUtil = new MailUtil(emailAdd,"Regd : SMS Credit Status For Respected Customers",getCustomerList());
							mailUtil.sendMailAllottedSmsCountForSupportTeam(getCustomerList(),"messages@eazyschool.com");
							mailUtil=null;
							emailAdd = null;
							emailsAndMobileNumbers = null;
							emailIdsSet = null;
						}
						else{
							_log.info("Email is not found for Supporting Team.(OR) No Supporting Team found for sending total alloted sms count.");
						}
				    }
				    else{
						_log.info("Currently there is no Exceeded Sms Count");
					}
					customers = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void sendBirthdayWishesToStaff(AcademicYear academicYear,Customer customer,SMSServiceProviders smsServiceProvider)
				throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'sendBirthdayWishesToStaff' method");
				System.out
						.println("Entering 'sendBirthdayWishesToStaff' method");
			}
			try {
				List<Object[]> staffList = null;
				String fullStaffName = null;
				String mobileNumber = null;
				String email = null;
				Messages message = null;
				HashSet<String> mobileNumberSet = null;
				StringBuffer query = new StringBuffer("select mobileNumber,email,fullName from vw_staffDetailsByDays where academicYearId=")
				.append(academicYear.getId()).append(" and ((mobileNumber !='' and mobileNumber is not null ) or (email !='' and email is not null))");
				staffList = adminManager.getAll(query.toString());
				if (ObjectFunctions.isNotNullOrEmpty(staffList)) {
					for (Object[] obj1 : staffList) {
						if (!ObjectFunctions.isNullOrEmpty(obj1)) {
							if(!ObjectFunctions.isNullOrEmpty(obj1[2]))
								fullStaffName = obj1[2].toString();
							if (academicYear.isSendStaffBirthdayAlerts()) {
								if(!ObjectFunctions.isNullOrEmpty(obj1[0]))
									mobileNumber = obj1[0].toString();
								
								int sentSmsCount=staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
								int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
								if (customer.isCheckMobileService() && StringFunctions.isNotNullOrEmpty(mobileNumber) && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
									mobileNumberSet = new HashSet<String>();
									mobileNumberSet.add(mobileNumber);
									log.debug("mobile numbers :"+ mobileNumberSet);
									message = new Messages();
									StringBuffer msgContent = new StringBuffer();
									msgContent.append("Dear "+fullStaffName);
									if(customer.getId()==50){
										msgContent.append(" this is to wish you a happy birthday from "); 
									}else{
										msgContent.append(",");
										msgContent.append(" Wishing you a very happy birthday from ");
									}
									message.setMessageDescription(msgContent.toString());
									log.debug("message content :"+ msgContent);
									log.debug("getMessages().setMessageDescription-  ---"+ message.getMessageDescription());
									message.setStatus("SBA");   
									message.setCustomer(customer);
									message.setSmsSenderId(customer.getSender());
									message.setAcademicYear(academicYear);
									message.setPurposeType("Regd: Birthday Wishes");
									message.setSenderName("System");
									communicationManager.deliverSms(message,mobileNumberSet,smsServiceProvider);
									mobileNumberSet = null;
									message = null;
									msgContent = null;
								}
							}else{
								_log.info("Mobile Staff Birthday wishes alerts are not enabled for customer - ."+customer.getId());
							}if(academicYear.isSendStaffBirthdayAlertsByEmail()){
								if(!ObjectFunctions.isNullOrEmpty(obj1[1]))
									email = obj1[1].toString();
								
								if (customer.isCheckEmailService() && StringFunctions.isNotNullOrEmpty(email)) {
									String[] emailAddresses = new String[1];
									emailAddresses[0] = email;
									MailUtil mailUtil = new MailUtil(emailAddresses,"Birthday wishes from "+ customer.getOrganization()+ ".",customer.getId(), customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
									mailUtil.setContactEmail(customer.getContactEmail());
									mailUtil.setContactPasword(customer.getContactPassword());
									mailUtil.sendMailForAllParentsForBirthdayDateAlert(email,fullStaffName,customer.getOrganization(),"Staff",adminManager.getContactFromEmail(customer),customer);
											mailUtil = null;
										emailAddresses = null;
								}
							}else{
								_log.info("Email Staff Birthday wishes alerts are not enabled for customer - ."+customer.getId());
							}
							obj1 = null;
							mobileNumber = null;
							email = null;
							fullStaffName = null;
						}
						
					}
				}else{
					_log.info("Email or mobile is not found for staff.(OR) No staff found for sending birthday wishes.");
				}
				staffList = null;
				mobileNumberSet = null;
				query = null;
				academicYear = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void updateSuspendorBlacklistStudent(AcademicYear academicYear,Customer customer)
				throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'updateSuspendorBlacklistStudent' method");
				System.out
						.println("Entering 'updateSuspendorBlacklistStudent' method");
			}
			try {
					//log.debug("updateSuspendorBlacklistStudent");
					Date toDayDate = new Date();
					List<Object[]> studentsList = null;
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String todayDate = formatter.format(DateFunctions.getDatePlusNdays(toDayDate,0));
					StringBuffer querys = new StringBuffer("select blackedOrSuspendToDate,studentId,status from suspendAndBlacklistStudents where academicYearId="+academicYear.getId()+" and custId="+customer.getId()+" and blackedOrSuspendFromDate ='"+todayDate.replace(" 00:00:00", "")+"' ");
					log.debug(querys.toString());
					studentsList = adminManager.getAll(querys.toString());
					if (ObjectFunctions.isNotNullOrEmpty(studentsList)) {
						for (Object[] obj1 : studentsList) {
							// Date scStartDates = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,obj1[0].toString().replace("00:00:00.0", ""));
							// Date toDayDates = new Date();
							// int totalDayss = DateFunctions.daysBetween(scStartDates,toDayDates);
							if (!ObjectFunctions.isNullOrEmpty(obj1)) {
								Student student = (Student)studentManager.get(Student.class,"id="+obj1[1]+" and custId="+customer.getId()+" and academicYearId="+academicYear.getId());
								if(!ObjectFunctions.isNullOrEmpty(student)){
									//student.setDescription(null);
									student.setStatus(obj1[2].toString());
									student.setCreatedDate(new Date());
									student.setLastUpdatedDate(new Date());
									studentManager.save(student);
									studentManager.sendNotificationForStudentUpdate(student);
									student = null;
								}
							}
						}
						studentsList = null;
					}
					String newDate = formatter.format(DateFunctions.getDatePlusNdays(toDayDate,-1));
					StringBuffer query = new StringBuffer("select blackedOrSuspendToDate,studentId,status from suspendAndBlacklistStudents where academicYearId="+academicYear.getId()+" and custId="+customer.getId()+" and blackedOrSuspendToDate <='"+newDate.replace(" 00:00:00", "")+"' ");
					log.debug(query.toString());
					studentsList = adminManager.getAll(query.toString());
					if (ObjectFunctions.isNotNullOrEmpty(studentsList)) {
						for (Object[] obj1 : studentsList) {
							 Date scStartDates = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,obj1[0].toString().replace("00:00:00.0", ""));
							 Date toDayDates = new Date();
							 int totalDayss = DateFunctions.daysBetween(scStartDates,toDayDates);
							if (!ObjectFunctions.isNullOrEmpty(obj1) && (totalDayss > 0)) {
								Student student = (Student)studentManager.get(Student.class,"id="+obj1[1]+" and status='"+obj1[2].toString()+"' and academicYearId="+academicYear.getId());
								if(!ObjectFunctions.isNullOrEmpty(student)){
									//student.setDescription(null);
									student.setStatus(Constants.YES_STRING);
									student.setCreatedDate(new Date());
									student.setLastUpdatedDate(new Date());
									studentManager.save(student);
									studentManager.sendNotificationForStudentUpdate(student);
									student = null;
								}
							}
						}
						studentsList = null;
					}/*else{
						_log.info("Email or mobile is not found for staff.(OR) No staff found for sending birthday wishes.");
					}*/
						//studentsList = null;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*Ravi Theja Panem 04-AUG-2017 Send Reminder to Staff*/
		public void sendTaskReminderToUser(AcademicYear academicYear,Customer customer,SMSServiceProviders smsServiceProvider) throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'sendReminderToUser' method");
				log.info("Entering 'sendReminderToUser' method");
			}
			try {
				String fullStaffName = null;
				String mobileNumber = null;
				String email = null;
				Messages message = null;
				Object[] staffDetails = null;
				StringBuffer query = null;
				HashSet<String> mobileNumberSet = null;
				Date todayDate = DateFormatter.getTodayDate(DateFormatter.MM_DD_YYYY_PATTERN1);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String today = formatter.format(DateFunctions.getDatePlusNdays(todayDate,0));
				List<Reminder> staffReminderList = staffManager.getAll(Reminder.class, "custId="+customer.getId()+" and expirationDate >='"+today.replace(" 00:00:00", "")+"' ");
				List<ViewTaskDetailsAndTaskHistory> staffTaskReminderList = staffManager.getAll(ViewTaskDetailsAndTaskHistory.class, "custId="+customer.getId()+ " and status in ('O','H') and taskDate >='"+today.replace(" 00:00:00", "")+"' ");
				for(ViewTaskDetailsAndTaskHistory taskReminder : staffTaskReminderList){
					Reminder reminder = new Reminder();
					reminder.setAccountId(taskReminder.getAccountId());
					reminder.setName(taskReminder.getTaskName());
					reminder.setExpirationDate(taskReminder.getTaskDate());
					reminder.setReminderOption(taskReminder.getReminderOption());
					reminder.setSpecificDate(taskReminder.getSpecificDate());
					reminder.setCheckMobileService(taskReminder.isCheckMobileService());
					reminder.setCheckEmailService(taskReminder.isCheckEmailService());
					staffReminderList.add(reminder);
					reminder = null;
				}
				for(Reminder reminder : staffReminderList ){
					query = new StringBuffer("select mobileNumber,email,CONCAT(IF(firstName IS NULL,'',firstName), IF(lastName IS NULL || lastName  = '','',CONCAT(' ',lastName))) as staffFullName from vw_staffDetails where academicYearId=")
					.append(academicYear.getId()).append(" and accountId =").append(reminder.getAccountId()).append(" and ((mobileNumber !='' and mobileNumber is not null ) or (email !='' and email is not null))");
					log.info("Query =" + query);
					staffDetails = adminManager.get(query.toString());
					if(!ObjectFunctions.isNullOrEmpty(staffDetails)){
						if(!ObjectFunctions.isNullOrEmpty(staffDetails[2]))
							fullStaffName = staffDetails[2].toString();
						if(!ObjectFunctions.isNullOrEmpty(staffDetails[0]))
							mobileNumber = staffDetails[0].toString();
						int sentSmsCount=staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
						int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
						if (customer.isCheckMobileService() && StringFunctions.isNotNullOrEmpty(mobileNumber) && reminder.isCheckMobileService() && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
							mobileNumberSet = new HashSet<String>();
							mobileNumberSet.add(mobileNumber);
							log.debug("mobile numbers :"+ mobileNumberSet);
							message = new Messages();
							message.setMessageDescription("Dear "+fullStaffName +", This message is a reminder for "+reminder.getName()+" on "+new SimpleDateFormat("dd-MMM-YYYY").format(reminder.getExpirationDate())+". Thanks from ");
							log.debug("getMessages().setMessageDescription-  ---"+ message.getMessageDescription());
							message.setStatus("STR"); // Here using STR means Staff Task Reminder                     
							message.setCustomer(customer);
							message.setSmsSenderId(customer.getSender());
							message.setAcademicYear(academicYear);
							message.setPurposeType("Regd: Reminder");
							message.setSenderName("System");
							if(reminder.getReminderOption().equalsIgnoreCase("E")){
								if(todayDate.before(reminder.getExpirationDate()) || todayDate.equals(reminder.getExpirationDate())){
									communicationManager.deliverSms(message,mobileNumberSet,smsServiceProvider);
								}
							}else if(reminder.getReminderOption().equalsIgnoreCase("S")){
								if(todayDate.equals(reminder.getSpecificDate())){
									communicationManager.deliverSms(message,mobileNumberSet,smsServiceProvider);
								}
							}
							mobileNumberSet = null;
							message = null;
					}else{
						_log.info("Mobile Staff reminder alerts are not enabled for customer - ."+customer.getId());
					}if(customer.isCheckEmailService() && reminder.isCheckEmailService()){
						if(!ObjectFunctions.isNullOrEmpty(staffDetails[1]))
							email = staffDetails[1].toString();
						
						if (customer.isCheckEmailService() && StringFunctions.isNotNullOrEmpty(email)) {
							String[] emailAddresses = new String[1];
							emailAddresses[0] = email;
							MailUtil mailUtil = new MailUtil(emailAddresses,"Reminder from "+ customer.getOrganization()+ ".",customer.getId(), customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
							mailUtil.setContactEmail(customer.getContactEmail());
							mailUtil.setContactPasword(customer.getContactPassword());
							if(reminder.getReminderOption().equalsIgnoreCase("E")){
								if(todayDate.before(reminder.getExpirationDate()) || todayDate.equals(reminder.getExpirationDate())){
									mailUtil.sendMailForRemider(email,fullStaffName,customer.getOrganization(),adminManager.getContactFromEmail(customer),customer,reminder);
								}
							}else if(reminder.getReminderOption().equalsIgnoreCase("S")){
								if(todayDate.equals(reminder.getSpecificDate())){
									mailUtil.sendMailForRemider(email,fullStaffName,customer.getOrganization(),adminManager.getContactFromEmail(customer),customer,reminder);
								}
							}
							mailUtil = null;
							emailAddresses = null;
						}
					}else{
						_log.info("Email Staff reminder alerts are not enabled for customer - ."+customer.getId());
					}
				}else{
					_log.info("Email or mobile is not found for staff.(OR) No staff found for sending birthday wishes.");
				}
			}
				mobileNumberSet = null;
				query = null;
				academicYear = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}