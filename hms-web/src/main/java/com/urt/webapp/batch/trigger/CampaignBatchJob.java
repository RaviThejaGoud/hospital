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

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.SQLGrammarException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.exam.Quiz;
import com.urt.persistence.model.exam.QuizQuestion;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.service.manager.interfaces.user.UserManager;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.xml.SendSms;
import com.urt.webapp.action.base.BaseAction;



/**
 * <p>
 *  This job removes from the system all expired events and messages
 *  including churchwide messages/events
 * </p>
 * 
 * @author  Sreeramulu J
 */
public class CampaignBatchJob extends BaseAction implements Job {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6137644058516625483L;
	private static Log _log = LogFactory.getLog(CampaignBatchJob.class);
//    private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
    /**
     * <p>
     * Empty constructor for job initilization
     * </p>
     * <p>
     * Quartz requires a public empty constructor so that the
     * scheduler can instantiate the class whenever it needs.
     * </p>
     */
	
	
    public CampaignBatchJob() {
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
        _log.info("Batch Process Started : " + jobName + " executing at " + new Date());
		try {

			/**
			 * Delete all the expired blogs from the  blogs database
			 */
			/*if(ObjectFunctions.isNullOrEmpty(adminManager)){
				adminManager=(AdminManager)SpringContextAware.getBean("adminManager");
            }
			if(ObjectFunctions.isNullOrEmpty(staffManager)){
				staffManager=(StaffManager)SpringContextAware.getBean("staffManager");
            }
			if(ObjectFunctions.isNullOrEmpty(studentManager)){
				studentManager=(StudentManager)SpringContextAware.getBean("studentManager");
            }
			if(ObjectFunctions.isNullOrEmpty(userManager)){
				userManager=(UserManager)SpringContextAware.getBean("userManager");
            }*/
			//For updating sent SMSs delivered count and status
			//updateUserImagePathsFromExist();
			//updateSentSMSDeliverdCountAndStatus();
			
			//sendMonthlyAndDailyStudentAttendanceMailToParent();
			
			//updateStudentsFilledCountToSections();
			
			//sendMailToQuizAlert();
			//sendMonthlyUserLoginDetailsForAllAdmins();
			//sendEmailForCustomerByMonthlyOrWeekly();
			//sendMonthlyAttendanceAlertMailToParent();
			/*ViewUserRoles masterAdmin=(ViewUserRoles)adminManager.get(ViewUserRoles.class, "roleName='ROLE_MASTERADMIN'");
			if(!ObjectFunctions.isNullOrEmpty(masterAdmin)){
				batchAddStaffAttendance(masterAdmin.getAcountId());    //Auto staff attendance  close the school end of day.
				batchAddStudentAttendance(masterAdmin.getAcountId());    //Auto student attendance to close the school end of day.
			}*/
			//StringBuffer query = new StringBuffer(" status='Y' and customerStatus='A' ");// and (checkEmailService = 'Y' or checkMobileService='Y')
			StringBuffer query = new StringBuffer(" id >"+46);
			List<Customer> customers = adminManager.getAll(Customer.class, query.toString());
			AcademicYear academicYear = null;
			if(ObjectFunctions.isNullOrEmpty(customers)){
				_log.info("No customers enabled mobile or email services.");
			}else{
				for(Customer customer : customers){
					academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
					if(ObjectFunctions.isNullOrEmpty(academicYear)){
						_log.info("Academic year not created for customer Id - "+customer.getId());
					}else{
						//For sending Birth day wishes to students.
						//sendBirthdayWishesToStudents(academicYear,customer);
						//remindersForAllFeeModules(academicYear,customer);
						//sendExamAlertMailAndMobileToParents(academicYear,customer);
						//sendHolidayAlertsToMobiles(academicYear,customer);
						//sendSmsStausEmailToSupportTeam(academicYear,customer);
						//sendBirthdayWishesToStaff(academicYear,customer);
						createStudentsMultiParentCredentails(customer,academicYear);
					}
					customer = null;
				}
				//sendSmsStausEmailToSupportTeam();
				academicYear = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	_log.info(" Batch Completed successfully.....");
    }
    public void createStudentsMultiParentCredentails(Customer custObj,AcademicYear academicYearObj){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'createStudentsMultiParentCredentails' method");
		}
        try {
        		if(!ObjectFunctions.isNullOrEmpty(custObj)){
        			if(StringFunctions.isNotNullOrEmpty(custObj.getCustomerShortName())){
        				HashMap<Long, User> isParentmap = null;
        				if(!ObjectFunctions.isNullOrEmpty(academicYearObj)){
        					List<Student> studentDetailList=adminManager.getAll(Student.class,"custId="+custObj.getId()+" and academicYearId="+academicYearObj.getId());
            	        	log.debug("Customer :"+custObj.getOrganization()+": "+custObj.getId()+" #student Size:-"+studentDetailList.size());
            	        	isParentmap = new HashMap<Long, User>();
            	        	for(Student stuObj:studentDetailList){
            					if(!ObjectFunctions.isNullOrEmpty(stuObj)){
        							createParentLoginCredentials(custObj,stuObj.getAccount(),isParentmap);
            					}stuObj=null;
            	        	}academicYearObj=null;studentDetailList=null;
        				}
        			}else{
        				log.debug("Customer Short Name Not Available..." + custObj.getCustomerShortName());
        			}
        		}custObj=null;
        } catch (Exception e) {
        	e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
        }
    }
    
    public void createParentLoginCredentials(Customer customer, User user,HashMap<Long, User> isParentmap)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'CreateParentLoginAccount' method");
		}
		String parentId = null;
		try {
			if(!ObjectFunctions.isNullOrEmpty(user))
			{
				User userexists =null;
				log.debug("Student user id..." + user.getId());
				log.debug("Student Parent user id..." + user.getStudentParent().getId());
				if(StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName())){
					String username = user.getUsername();//.replaceAll("(?=[]\\[+&|!(){}^\"~*?:\\\\-])", "\\\\");
					if(StringFunctions.isNotNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(user.getPerson().getMobileNumber()))){
						String newMobile = "";
						if(StringFunctions.getMobileNumberLengthChecking(user.getPerson().getMobileNumber()).contains("+91-")){
							newMobile = StringFunctions.getMobileNumberLengthChecking(user.getPerson().getMobileNumber()).replace("+91-", "");
						}
						else{
							newMobile = user.getPerson().getMobileNumber();
						}
						username = newMobile;
					}
					else{
						username = username.trim()+"_p";
					}
					if(!ObjectFunctions.isNullOrEmpty(user.getStudentParent().getId())){
						if(!ObjectFunctions.isNullOrEmpty(isParentmap.get(Long.valueOf(user.getStudentParent().getId())))){
							//log.debug("dup parent :"+user.getParentId());
							createParentAccount(customer,user,username);
						}else{
							userexists =(User)adminManager.get(User.class, "custId="+customer.getId()+" and id="+user.getStudentParent().getId()+" and username='"+username+"' ");
							if(ObjectFunctions.isNullOrEmpty(userexists)){
								createParentAccount(customer,user,username);
							}else{
								//log.debug("userexists parent :"+user.getUsername()+" parent :"+username);
								userexists =(User)adminManager.get(User.class, "custId="+customer.getId()+" and id="+user.getStudentParent().getId());
								userexists.setUsername(username);
								userexists.setSecondaryUsername(String.valueOf(userexists.getId()));
								userexists.setPassword(PasswordUtils.passwordEncoder(customer.getCustomerShortName()+"2VsnHJG9", null));
								adminManager.save(userexists);
								isParentmap.put(userexists.getId(), userexists);
								userexists=null;
							
							}
								
						}
					}else{
						createParentAccount(customer,user,username);
					}
				}
				log.debug("Parent login credentails established now..." + parentId);
			}
			user=null;
			customer=null;
	    }
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	 }
    public void createParentAccount(Customer customer,User user,String username) throws SQLGrammarException
    {
    	User userexists =null;
    	User parentAccount = null;
    	Person person = user.getPerson();
    	try {
    		
    		if(StringFunctions.isNotNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(user.getPerson().getMobileNumber()))){
				String newMobile = "";
				if(StringFunctions.getMobileNumberLengthChecking(user.getPerson().getMobileNumber()).contains("+91-")){
					newMobile = StringFunctions.getMobileNumberLengthChecking(user.getPerson().getMobileNumber()).replace("+91-", "");
				}
				else{
					newMobile = user.getPerson().getMobileNumber();
				}
				userexists =adminManager.getUserEmailByUserName(newMobile);
			}
    		else{
    			userexists =adminManager.getUserEmailByUserName(user.getUsername()+"_p");
    		}
    		if (!ObjectFunctions.isNullOrEmpty(userexists)) {
    			if(!userexists.getUsername().toString().equalsIgnoreCase(username)){
    				userexists.setUsername(username);
    				userexists.setSecondaryUsername(String.valueOf(userexists.getId()));
    				userexists.setPassword(PasswordUtils.passwordEncoder(customer.getCustomerShortName()+"2VsnHJG9", null));
    			}
    			userexists.setCustId(customer.getId());
    			parentAccount = (User)adminManager.save(userexists);
    			/*user.getStudentParent().setParentAccountId(parentAccount.getId());
    			user.getStudentParent().setStudentAccountId(user.getId());*/
    			user.addParentChild(parentAccount);
				adminManager.save(user);
    			userexists=null;
    		}else{
    			try {
        			Person parentPerson =null;
        			Address parentAdress =null;
        				log.debug(user.getUsername()+"Method Calling for Creating New Object ........."+username);
        				parentAccount = new User();
        				parentAccount.setUsername(username);
        				parentAccount.setSecondaryUsername(username);
        				parentAccount.setPassword(PasswordUtils.passwordEncoder(customer.getCustomerShortName()+"2VsnHJG9", null));
        				parentAccount.addNewRole(adminManager.getRoleByName(Constants.SCHOOL_PARENT));
        				parentAccount.setCustId(customer.getId());
        				parentPerson = new Person();
        				if(!ObjectFunctions.isNullOrEmpty(person)){
        					parentPerson.setFirstName(person.getFatherName());
        					parentPerson.setMobileNumber(person.getMobileNumber());
        					parentPerson.setPhoneNumber(person.getPhoneNumber());
        					parentPerson.setParentEmail(person.getParentEmail());
        				}else{
        					parentAccount.setPerson(parentPerson);
        				}
        				parentAdress = new Address();
        				parentAccount.setCustId(customer.getId());
        				parentAccount.setPerson(parentPerson);
        				if(!ObjectFunctions.isNullOrEmpty(user.getPrimaryAddress())){
        					parentAccount.setPrimaryAddress(user.getPrimaryAddress());
        				}else{
        					parentAccount.setPrimaryAddress(parentAdress);
        				}
        				parentAccount = (User)adminManager.save(parentAccount);
        				/*user.getStudentParent().setParentAccountId(parentAccount.getId());
        				user.getStudentParent().setStudentAccountId(user.getId());*/
        				user.addParentChild(parentAccount);
        				adminManager.save(user);
        				parentPerson = null;
        				parentAccount =null;        		
				} catch (SQLGrammarException ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
    		}
    	    person=null;
    		userexists=null;
    		
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally{
			userexists =null;
        	parentAccount = null;
        	person=null;
		}
    }
    /********************************************************************
     * Date              Name               Description
     * ========          ============       ==================
     * Oct 8, 2013       Seshu		        For updating sent sms count and delivered status
    /********************************************************************/   
    public void updateSentSMSDeliverdCountAndStatus(){
    	_log.info("updateSentSMSDeliverdCountAndStatus");
    	try{
    		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//String today = formatter.format(new Date());
    		String tomorrowDate  = formatter.format(DateFunctions.getTodayPlusNdays(1));
			String yesterdayDate = formatter.format(DateFunctions.getTodayPlusNdays(-1));
    		//List<Messages> updatableSMSs= adminManager.getAll(Messages.class, "messageType='SMS' and deliveredSmsStatus='N'  and guid is not null and messageDate like '"+today+" %'");
    		List<Messages> updatableSMSs= adminManager.getAll(Messages.class, "messageType='SMS' and deliveredSmsStatus='N' and guid is not null and messageDate between '"+yesterdayDate+" %' and '"+tomorrowDate+" %'");
			//List<Messages> updatableSMSs= adminManager.getAll(Messages.class, "messageType='SMS' and deliveredSmsStatus='N' and guid is not null and messageDate messageDate BETWEEN DATE_SUB( CURDATE( ) ,INTERVAL 2 DAY ) AND CURDATE( )");
    		if(ObjectFunctions.isNotNullOrEmpty(updatableSMSs)){
    			int smscount=0;
    			for(Messages message : updatableSMSs){
    				if(!ObjectFunctions.isNullOrEmpty(message.getSmsProviders()) && StringFunctions.isNotNullOrEmpty(message.getSmsProviders().getUrl())){
    					smscount = SendSms.sendStatusRequest(message,message.getSmsProviders().getUrl());
        				if(smscount!=0){
        					message.setSmsCount(smscount);
        					message.setDeliveredSmsStatus(true);
        					userManager.save(message);
        				}
    				}else{
    					_log.info("SmsServiceProvider is not available for :"+message.getGuid());
    				}
    			}
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    /*public String sendMonthlyUserLoginDetailsForAllAdmins(){
    	if (log.isDebugEnabled()) {
			log.debug("Entering 'sendMonthlyUserLoginDetailsForAllAdmins' method");
			log.debug("Entering 'sendMonthlyUserLoginDetailsForAllAdmins' method");
		}
    	try {
    		List<Customer> customrList = adminManager.getCustomerListByStatus(Constants.YES_STRING);
    		if (!ObjectFunctions.isNullOrEmpty(customrList)) {
    			Date today = new Date();
    			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    			String currentDate = formatter.format(today);
			        Calendar calendar = Calendar.getInstance();  
			        calendar.setTime(today);  
			        calendar.set(Calendar.DAY_OF_MONTH, 1);  
			        Date firstDayOfMonth = calendar.getTime(); 
			        String convertedStartDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,firstDayOfMonth);
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, -1);
					cal.set(Calendar.DATE, 1);
					Date firstDateOfPreviousMonth = cal.getTime();
					cal.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
					Date lastDateOfPreviousMonth = cal.getTime();
					String lastMonthFirstDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,firstDateOfPreviousMonth);
					String lastMonthLastDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,lastDateOfPreviousMonth);
					List<Object[]> viewLoginUsersList = null;
				    for (Customer customer : customrList) {
					 if (customer.isCheckEmailService()) {
					  //if (convertedStartDate == currentDate) 
						 if(convertedStartDate.equalsIgnoreCase(currentDate))
					     {
							viewLoginUsersList = adminManager.getAll("select roleDescription,count(*),roleId as selectedROleId ,(select count(*) from vw_allUsers where lastAccessDate>='"+lastMonthFirstDate+" 00:00:00' and lastAccessDate<='"+lastMonthLastDate+" 00:00:00' and roleId= selectedROleId ) as logeedInCount  from vw_allUsers where custId="+customer.getId()+" group by roleId");
							//viewLoginUsersList = adminManager.getAll("select count(*) from vw_allUsers where custId="+ customer.getId()+ " and lastAccessDate>='"+lastMonthFirstDate+" 00:00:00' and lastAccessDate<='"+lastMonthLastDate+" 00:00:00' group by roleName");
							//viewAllUsersList = adminManager.getAll("select roleName,count(*) from vw_allUsers where custId="+ customer.getId()+ " group by roleName");
							String[] emailAddresses = new String[1];
							if(StringFunctions.isNotNullOrEmpty(customer.getCustEmail())){
								emailAddresses[0] = customer.getCustEmail();
								MailUtil mailUtil=new MailUtil(emailAddresses,"Users Login Reports",customer.getId(),customer.getSender(),getUser().getUserRoleDescription());
								mailUtil.sendMailForAllAdminAndMasterAdmins(viewLoginUsersList,customer.getFirstName(),lastMonthFirstDate,lastMonthLastDate);
								mailUtil=null;
							}
							emailAddresses=null;
							 EmailBean emailBean = sendMailForAllAdminAndMasterAdmins(viewLoginUsersList,customer,lastMonthFirstDate,lastMonthLastDate);
							 if(!ObjectFunctions.isNullOrEmpty(emailBean)){
								emailBean.setCustomerId(customer.getId());
							    saveActiveLogsInJob(emailBean);
							    emailBean = null;
							 }
					  }
					}
				}
    		}
    	}catch (Exception ex) {
    		ex.printStackTrace();
		}
    	return "SUCCESS";
    }*/

	/*public String remindersForAllFeeodules() {
		try {
			AcademicYear academicYear = null;
			Date currentDate = DateFormatter.getTodayDate(DateFormatter.YYYY_MM_DD_PATTERN);
			List<Customer> customerList=adminManager.getAll(Customer.class, "status='"+Constants.YES_STRING+"' and (checkEmailService='Y' || checkMobileService='Y') ");
			if (!ObjectFunctions.isNullOrEmpty(customerList)) {
				for (Customer customer : customerList) {
				//Customer customer=(Customer)adminManager.get(Customer.class, Long.valueOf(12));
					if (customer.isCheckEmailService() || customer.isCheckMobileService()) {
						academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
						sendMailORSmsReminderToSchool(customer,academicYear,currentDate);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}*/
	//Changed By Seshu on 27-03-2012 for code refactor on SMS module
	/*public String sendMailORSmsReminderToTransport(Customer customer,AcademicYear academicYear,Date currentDate){
		try{
			StringBuffer msgContent = null;
			Messages message = null;
			Set<String> mobileNumber = null;
			String dueDateMsg = "Dear Parent, You have exceeded the due date in paying your children's school fee for the <Term Name>. Please make the payment as soon as possible to avoid the late payment charges.";
			List<ViewStudentClassDetails> studentTransportList = null;
			if (!ObjectFunctions.isNullOrEmpty(academicYear)) {
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				List<SchoolTerms> transportTermsList = adminManager.getAllByCustId("TransportSchoolTerms",customer.getId(), academicYear.getId());
				if (!ObjectFunctions.isNullOrEmpty(transportTermsList)) {
					for (SchoolTerms transportTerms : transportTermsList) {
						int feeDueDays = DateFunctions.daysBetween(new Date(),transportTerms.getDueDate());
						studentTransportList = adminManager.getAll(ViewStudentClassDetails.class,"  custId="+ customer.getId()+ " and academicYearId="+ academicYear.getId()+ " and status='"+ Constants.YES_STRING+ "' and studDiscontinueDesc is null and boardingPointId != 0 ");
						if (!ObjectFunctions.isNullOrEmpty(studentTransportList)) {
							for (ViewStudentClassDetails studentDetails : studentTransportList) {
								int sentSmsCount=staffManager.getTotalSmsCount(customer.getId(),academicYear.getId());
								int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
								long totalTermAmount = adminManager.getFeeTotalAmountByTerm("TransportFee",customer.getId(),studentDetails.getClassId(),academicYear.getId(),transportTerms.getId(),studentDetails.getCategoryId(),studentDetails.getBoardingPointId());
								long totalPaidAmount = adminManager.getStudentPaidAmountByClassId("vw_studentTransportFeePaymentDetails",studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(),academicYear.getId(),transportTerms.getId(),studentDetails.getBoardingPointId());
								//prepareTransportFeeIds(transportTerms.getId(),studentDetails.getClassId(),studentDetails.getCategoryId());
								long discAmount = adminManager.getStudentDiscountAmountByClassId("transportFeePaidDetails",studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(),academicYear.getId(),getAnyId(),0);
								long dueAmount = totalTermAmount- totalPaidAmount + discAmount;
								if (Long.valueOf(transportTerms.getNoOfDays()) == feeDueDays + 1) {
									if (totalTermAmount > (totalPaidAmount+discAmount)) {
										if (customer.isCheckEmailService()) {
											 EmailBean emailBean = sendMailForAllParentsForFeeAmount(studentDetails,currentDate, getCurrentMonth(), transportTerms,dueAmount,customer);
											 if(!ObjectFunctions.isNullOrEmpty(emailBean)){
												emailBean.setCustomerId(customer.getId());
											    saveActiveLogsInJob(emailBean);
											    emailBean = null;
											 }
											log.debug("Enter into fee due reminder for email."+transportTerms.getTermName()+" days :"+feeDueDays+1);
										}
										if (customer.isCheckMobileService() && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
											message = new Messages();
											
											
											msgContent = new StringBuffer("Dear Parent, This is a friendly reminder that your child "+studentDetails.getFullName()+", "+studentDetails.getClassAndSection()+", "+transportTerms.getTermName()+" fee of RS."+dueAmount+".Due on "+schoolTerms.getDueDateStr()+".Please ignore if paid.From ");
											msgContent = new StringBuffer(transportTerms.getMobileContentDesc().replaceAll("<Term Name>",studentDetails.getClassAndSection()+","+transportTerms.getTermName()).
													replaceAll("<Amount>",dueAmount+ "").replaceAll("<Date>",transportTerms.getDueDateStr()).
													replaceAll("<School Name>",""));
											message.setCustomer(customer);
											message.setSmsSenderId(customer.getSender());
											message.setMessageDescription(msgContent.toString());
											message.setStatus("TFR");
											message.setSenderName("System");
											setAnyTitle("throwFromCamBatch");
											message.setAcademicYear(academicYear);
											message.setPurposeType("Regd: Fee Reminder");
											mobileNumber = new HashSet<String>();
											mobileNumber.add(studentDetails.getMobileNumber());
											if (!ObjectFunctions.isNullOrEmpty(mobileNumber)) {
												communicationManager.deliverSms(message,mobileNumber,smsServiceProvider);
											}
											mobileNumber = null;
											message = null;
											msgContent = null;
										}
									}
								}
								if (feeDueDays == -1 || feeDueDays == -5) {
									if ((totalPaidAmount+discAmount)<totalTermAmount) {
										
										if (feeDueDays == -1 || feeDueDays == -5) {
											if (customer.isCheckEmailService()) {
												EmailBean emailBean = sendMailForAllParentsForFeeAmountCrossDueDate(studentDetails,currentDate, getCurrentMonth(),transportTerms,dueAmount, customer);
												if(!ObjectFunctions.isNullOrEmpty(emailBean)){
												   emailBean.setCustomerId(customer.getId());
												   saveActiveLogsInJob(emailBean);
												   emailBean = null;
												}
												//log.debug("Enter into fee due exceed date for email."+schoolTerms.getTermName()+" days :"+(feeDueDays+1));
											}
											if (customer.isCheckMobileService() && StringFunctions.isNotNullOrEmpty(transportTerms.getMobileContentDesc()) && (allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)) {
												message = new Messages();
												message.setMessageDescription(dueDateMsg.replaceAll("<Term Name>",transportTerms.getTermName()));
												message.setStatus("TLP");
												message.setCustomer(customer);
												message.setSmsSenderId(customer.getSender());
												message.setSenderName("System");
												setAnyTitle("throwFromCamBatch");
												message.setMessageType("SMS");
												message.setAcademicYear(academicYear);
												message.setPurposeType("Regd: Fee late payment");
												mobileNumber = new HashSet<String>();
												mobileNumber.add(studentDetails.getMobileNumber());
												if (!ObjectFunctions.isNullOrEmpty(mobileNumber)) {
													communicationManager.deliverSms(message,mobileNumber,smsServiceProvider);
												}
												mobileNumber = null;
												message = null;
											}
											studentDetails = null;
											totalTermAmount = 0;
											totalPaidAmount = 0;
											dueAmount=0;
										}
									}
								}
							}
						}
					}
				}
			}
			msgContent = null;
			message = null;
			mobileNumber = null;
			dueDateMsg = null;
			studentTransportList = null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

	//Modified by seshu on 27-03-2013 for SMS code refactor
	/*public String sendMailORSmsReminderToSchool(Customer customer, AcademicYear academicYear, Date currentDate) {
		try {
			Set<String> mobileNumber = null;
			Messages message = null;
			long boardingPointId=0;
			log.debug("Fee reminder alert");
			String dueFeeCont = "Dear Parent, You have exceeded the due date in paying your children's school fee for the <Term Name>. Please make the payment as soon as possible to avoid the late payment charges. ";
			List<ViewStudentClassDetails> studentFeeList = null;
			if (!ObjectFunctions.isNullOrEmpty(academicYear)) {
				prepareCampSchoolFeeSettingList(customer);
				List<SchoolFeeSetting> feeSettingList=getObjectList();
				if(!ObjectFunctions.isNullOrEmpty(feeSettingList)){
					for(SchoolFeeSetting feeSetting : feeSettingList){
						List<SchoolTerms> schoolTermsList = adminManager.getAll(SchoolTerms.class, "custId="+academicYear.getCustId()+" and academicYearId="+academicYear.getId()+" and feeSettingid="+feeSetting.getId());
						if (!ObjectFunctions.isNullOrEmpty(schoolTermsList)) {
							MailUtil mailUtil=null;
							String[] emailAddresses=null;
							for (SchoolTerms schoolTerms : schoolTermsList) {
								if(StringFunctions.isNullOrEmpty(schoolTerms.getNoOfDays())){
									schoolTerms.setNoOfDays("1");
								}
								int feeDueDays = DateFunctions.daysBetween(new Date(),schoolTerms.getDueDate());
								log.debug("feeDueDays : "+feeDueDays);
								if (Long.valueOf(schoolTerms.getNoOfDays()) == (feeDueDays + 1) || feeDueDays == -1 || feeDueDays == -5) {
									log.debug(feeDueDays + 1);
									log.debug("reminder term days :"+schoolTerms.getNoOfDays());
									List<ClassName> classList=adminManager.getAll(ClassName.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" order by sortingOrder");
									if(ObjectFunctions.isNotNullOrEmpty(classList)){
										for(ClassName className : classList){
											List<Fee> fees = adminManager.getAll(Fee.class, "custId="+ customer.getId() + " and academicYearId="+ academicYear.getId() + " and ( classId="+ className.getId() + " || routeBoardingPointId >0 ) and feeAmount > 0");
											if (ObjectFunctions.isNotNullOrEmpty(fees)) {
												studentFeeList = adminManager.getAll(ViewStudentClassDetails.class, "  custId="+ customer.getId()+ " and academicYearId="+ academicYear.getId()+ " and classId="+className.getId()+" and status='"+ Constants.YES_STRING+ "' and studDiscontinueDesc is null and (parentEmail is not null || mobileNumber is not null) ");
												for (ViewStudentClassDetails studentDetails : studentFeeList) {
													if(StringFunctions.isNullOrEmpty(schoolTerms.getNoOfDays())){
														schoolTerms.setNoOfDays("1");
													}
													if(Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName())){
														boardingPointId=studentDetails.getBoardingPointId();
													}
													long totalTermAmount = adminManager.getFeeTotalAmountByTerm("Fee", customer.getId(), studentDetails.getClassId(),academicYear.getId(), schoolTerms.getId(), studentDetails.getCategoryId(),boardingPointId);
													long totalPaidAmount = adminManager.getStudentPaidAmountByClassId("vw_studentFeePaymentDetails",studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), schoolTerms.getId(),boardingPointId);
													prepareFeeIds(schoolTerms.getId(), studentDetails.getClassId(), studentDetails.getCategoryId(),studentDetails.getBoardingPointId(),academicYear.isTransportFeeByBoardingPoint());
													long discAmount = adminManager.getStudentDiscountAmountByClassId("studentFeePaidDetails",studentDetails.getStudId(),studentDetails.getClassId(),customer.getId(), academicYear.getId(), getAnyId(),0);
													long dueAmount = totalTermAmount- (totalPaidAmount + discAmount);
													if (Long.valueOf(schoolTerms.getNoOfDays()) == (feeDueDays + 1)) {
														if (totalTermAmount > (totalPaidAmount + discAmount)) {
															if (customer.isCheckEmailService()) {
																emailAddresses = new String[1];
																if(StringFunctions.isNotNullOrEmpty(studentDetails.getParentEmail())){
																	emailAddresses[0] = studentDetails.getParentEmail();
																	mailUtil=new MailUtil(emailAddresses,"Fee Payment Remainder",customer.getId(),customer.getSender(),"Administrator");
																	mailUtil.sendMailForAllParentsForFeeAmount(studentDetails, currentDate,getCurrentMonth(), schoolTerms,dueAmount,customer.getOrganization());
																	mailUtil=null;
																}
																emailAddresses=null;
															}
															if (customer.isCheckMobileService() && StringFunctions.isNotNullOrEmpty(schoolTerms.getMobileContentDesc())) {
																log.debug("mobileNumber :"+studentDetails.getMobileNumber());
																if (StringFunctions.isNotNullOrEmpty(studentDetails.getMobileNumber())) {
																message = new Messages();
																message.setMessageDescription(schoolTerms.getMobileContentDesc().replaceAll("<Term Name>", studentDetails.getClassAndSection()+ ","+ schoolTerms.getTermName()).
																		replaceAll("<Amount>", dueAmount + "").
																		replaceAll("<Date>", schoolTerms.getDueDateStr()).
																		replaceAll("<School Name>", ""));
																message.setCustomer(customer);
																message.setStatus("FR");
																message.setSenderName("System");
																message.setMessageType("SMS");
																setAnyTitle("throwFromCamBatch");
																message.setAcademicYear(academicYear);
																message.setPurposeType("Regd: Fee Reminder");
																mobileNumber = new HashSet<String>();
																mobileNumber.add(studentDetails.getMobileNumber());
																	log.debug("mobilr number"+mobileNumber);
																	deliverSms(message, mobileNumber);
																}
																message = null;
																mobileNumber = null;
															}
														}
													}
													if ((totalPaidAmount + discAmount) < totalTermAmount) {
														if (feeDueDays == -1 || feeDueDays == -5) {
															if (customer.isCheckEmailService()) {
																if(StringFunctions.isNotNullOrEmpty(studentDetails.getParentEmail())){
																	emailAddresses = new String[1];
																	emailAddresses[0] = studentDetails.getParentEmail();
																	mailUtil=new MailUtil(emailAddresses,"Fee Exceed Payment Remainder",customer.getId(),customer.getSender(),"Administrator");
																	mailUtil.sendMailForAllParentsForFeeAmountCrossDueDate(studentDetails, currentDate,getCurrentMonth(), schoolTerms,dueAmount,customer.getOrganization());
																	mailUtil=null;
																	emailAddresses=null;
																}
															}
															if (customer.isCheckMobileService()&& !StringFunctions.isNullOrEmpty(studentDetails.getMobileNumber())) {
																if (StringFunctions.isNotNullOrEmpty(studentDetails.getMobileNumber())) {
																message = new Messages();
																message.setMessageDescription(dueFeeCont.replaceAll("<Term Name>", schoolTerms.getTermName()));
																message.setStatus("LP");
																message.setCustomer(customer);
																message.setSenderName("System");
																message.setMessageType("SMS");
																setAnyTitle("throwFromCamBatch");
																message.setAcademicYear(academicYear);
																message.setPurposeType("Regd: Fee late payment");
																mobileNumber = new HashSet<String>();
																mobileNumber.add(studentDetails.getMobileNumber());
																
																	deliverSms(message, mobileNumber);
																}
																message = null;
																mobileNumber = null;
															}
															studentDetails = null;
															totalTermAmount = 0;
															totalPaidAmount = 0;
															dueAmount = 0;
														}
													}
													studentDetails = null;
												}
											}
										}
									}
								}
								studentFeeList = null;
								schoolTerms = null;
							}
							schoolTermsList = null;
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "updateBSFGData";
	}*/
	
	public String sendMailToQuizAlert() {
		try {
			User userParent = null;
			Person person = null;
			
		//	StudentPayment studentPayment = null;
		//	FeeType dummyFeeType = null;
			Date currentDate = DateFormatter.getTodayDate(DateFormatter.YYYY_MM_DD_PATTERN);
			List studentList = adminManager.getAll(Student.class);
			if (!ObjectFunctions.isNullOrEmpty(studentList)) {
				for (Object obj : studentList) {
					Student student = (Student) obj;
			//		long studentId = student.getId();
					long accountId = student.getAccount().getId();
					User user = (User) adminManager.get(User.class, accountId);
					Customer customer = (Customer) adminManager.get(Customer.class, user.getCustId());
					long personId = Long.valueOf(user.getPerson().getId());
					person = (Person) adminManager.get(Person.class, personId);
			//		String studentName = person.getPersonFullName();
					long parentId = Long.valueOf(user.getStudentParent().getId());
					userParent = (User) adminManager.get(User.class, parentId);
				//	String parentMail = userParent.getUsername();
					setQuizList(staffManager.getAll(Quiz.class));
					if(!ObjectFunctions.isNullOrEmpty(getQuizList())){
						for (Object qObj : getQuizList()) {
							Quiz quiz=(Quiz)qObj;
							String quizName=quiz.getTitle();
							if(!ObjectFunctions.isNullOrEmpty(quiz)){
								List quizQuestionList=adminManager.getQuizQuestionListWithStartDate(quiz.getId());
								if(!ObjectFunctions.isNullOrEmpty(quizQuestionList)){
									for (Object question : quizQuestionList) {
										QuizQuestion quizQuestion=(QuizQuestion)question;
										Date startDate = quizQuestion.getStartDate();
										Date endDate = quizQuestion.getEndDate();
										int days = DateFunctions.daysBetween(startDate, currentDate);
										log.debug("quizDays :"+days);
										if (days == 7 || days == 3 || days == 0) {
											/* Dummy Object */
											QuizQuestion quizQuestion2=new QuizQuestion();
											if(!ObjectFunctions.isNullOrEmpty(startDate)){
												quizQuestion2.setQuestionName(quizName);
												quizQuestion2.setStartDate(startDate);
												quizQuestion2.setEndDate(endDate);
												getQuestionAnswerList().add(quizQuestion2);
											}
										}
									}
								}
							}
						}
						String[] emailAddresses = new String[1];
						emailAddresses[0] = userParent.getUsername();
						MailUtil mailUtil = new MailUtil(emailAddresses,"Alert for Attend Quiz",customer.getId(),customer.getSender(),user.getUserRoleDescription(),adminManager.getContactFromEmail(customer));
						mailUtil.setContactEmail(customer.getContactEmail());
						mailUtil.setContactPasword(customer.getContactPassword());
						mailUtil.sendQuizAlertAndEmailToParentAnSdtudent(student, person,currentDate,getQuestionAnswerList(),adminManager.getContactFromEmail(customer));
						mailUtil=null;
						emailAddresses=null;
						/*if(!ObjectFunctions.isNullOrEmpty(getQuestionAnswerList())){
							EmailBean emailBean = sendQuizAlertAndEmailToParentAnSdtudent(student, person,currentDate, userParent.getUsername(),getQuestionAnswerList(),customer);
							if(!ObjectFunctions.isNullOrEmpty(emailBean)){
								emailBean.setCustomerId(customer.getId());
								saveActiveLogsInJob(emailBean);
								emailBean = null;
							}
							
						}*/
						setQuestionAnswerList(null);
					}
					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "updateBSFGData";
	}
    
	 public String sendEmailForCustomerByMonthlyOrWeekly()
	  {
			try{
//				Date newDate = new Date();
	//			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//	String today = formatter.format(newDate);
				Calendar cal = Calendar.getInstance();
				/*int dayOfTheWeek=cal.get(Calendar.DAY_OF_WEEK);
				String dayOfThedate=String.valueOf(cal.get(Calendar.DATE));
				String dayOfTheMonth=String.valueOf(cal.get(Calendar.MONTH) + 1);
				String.valueOf(Integer.valueOf(cal.get(Calendar.DATE)))   String.valueOf(Integer.valueOf(cal.get(Calendar.MONTH) + 1));
				String dayOfTheDateAndMonth= dayOfThedate "+ +" dayOfTheMonth;
				String[] monthName = { "01-01", "02-01", "03-01", "04-01","05-01", "06-01", "07-01", "08-01", "09-01", "10-01","11-01", "12-01" };
				setCurrentMonth(monthName[cal.get(Calendar.MONTH)]);*/
				//Date currentDate = DateFormatter.getTodayDate(DateFormatter.YYYY_MM_DD_PATTERN);
				List<Customer> customrList = adminManager.getCustomerListByStatus(Constants.YES_STRING);
				if (!ObjectFunctions.isNullOrEmpty(customrList)) {
					for (Customer customer : customrList) {
						//Messages messages=new Messages();
						if (customer.isCheckEmailService() || customer.isCheckMobileService()) {
							if (!ObjectFunctions.isNullOrEmpty(customer)) {
								MailUtil mailUtil=new MailUtil();
								if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
									mailUtil.sendEmailForCustomerWeeKly(customer);
									
								}
								if(cal.get(Calendar.DATE) == 1){
									mailUtil.sendEmailForCustomerMonthly(customer);
								}
							}
						}
						customer=null;
					}
				}
			}
			catch (Exception ex){
				ex.printStackTrace();
				_log.info("batch udpate failed in the sendVidosForUsers method::");
			}
			return"sendVideosForUser";
		}
    
    public String sendMonthlyAndDailyStudentAttendanceMailToParent() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'batchAddStaffAttendance' method");
		}

		try {
			AcademicYear academicYear = null;
			Date date=new Date();
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    long presentCount = 0;
       	    long absentCount= 0;
       	    int monthTotalDays=0;
		   // int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH)+1;
		    int day = cal.get(Calendar.DAY_OF_MONTH);
			List<Customer> customrList = adminManager.getCustomerListByStatus(Constants.YES_STRING);
			List<Object[]> studentAttendanceList=null;
			if (!ObjectFunctions.isNullOrEmpty(customrList)) {
				for (Customer customer : customrList) {
						academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
						if (!ObjectFunctions.isNullOrEmpty(academicYear)) {
							if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate()) && !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
									if (DateFunctions.isValidDay(month,day)) {
										monthTotalDays =getMonthOfTotalWorkingDays(month,academicYear,customer.getId());
										if(academicYear.getManageAttendanceBy().equalsIgnoreCase("D"))
										{
											studentAttendanceList=adminManager.getAll("select studentId,StudentName,monthName,'','',parentEmail,fatherName,classNameAndSection from vw_studentDailyAttendance where custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and parentEmail !='' and month="+month);
										}else{
											studentAttendanceList=adminManager.getAll("select studentId,StudentName,monthName,totalWorkingDays,noOfPresentDays,parentEmail,fatherName,classNameAndSection from vw_StudentMonthlyAttendance where custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and parentEmail !='' and month="+month);
										}
									if (ObjectFunctions.isNotNullOrEmpty(studentAttendanceList)){
										for (Object[] studAttObj : studentAttendanceList) {
										if (!ObjectFunctions.isNullOrEmpty(studAttObj)) {
											if(customer.isCheckEmailService()){
										    	String[] emailAddresses = new String[1];
									            if(!StringFunctions.isNullOrEmpty(studAttObj[5].toString())){
									            	emailAddresses[0]=studAttObj[5].toString();
											    	MailUtil mailUtil=new MailUtil(emailAddresses,"Information You Requested from Eazy School",customer.getId(),customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
											    	mailUtil.setContactEmail(customer.getContactEmail());
											    	mailUtil.setContactPasword(customer.getContactPassword());
									            	if(academicYear.getManageAttendanceBy().equalsIgnoreCase("D"))
													{
									            		absentCount = adminManager.getCount("vw_StudentDailyAttendance", "studentId="+Long.valueOf(studAttObj[0].toString())+" and month="+month+" and present='"+ "N'");
														presentCount=monthTotalDays - absentCount;
									            		mailUtil.sendMailMontlyStudentAttendanceForParent(studAttObj[1].toString(),studAttObj[2].toString(),monthTotalDays+"",presentCount+"",emailAddresses,studAttObj[6].toString(),studAttObj[7].toString(),adminManager.getContactFromEmail(customer));
													}else{
														mailUtil.sendMailMontlyStudentAttendanceForParent(studAttObj[1].toString(),studAttObj[2].toString(),studAttObj[3].toString(),studAttObj[4].toString(),emailAddresses,studAttObj[6].toString(),studAttObj[7].toString(),adminManager.getContactFromEmail(customer));
													}
											    	mailUtil=null;
									            }
										    	emailAddresses=null;
											}
										}studAttObj=null;
									}
									}
									studentAttendanceList=null;
								}
						   }
							academicYear=null;
						}
					customer=null;
				}
				customrList=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
    
    public int getMonthOfTotalWorkingDays(int monthId,AcademicYear academicYear,long custId)
	 {
		 int days =0; 
			int yearId;
			int monthTotalDays;
			Date closeDate = null;
			if(!ObjectFunctions.isNullOrEmpty(academicYear))
			{
					 closeDate = academicYear.getEndDate();
			}
			List<SchoolHolidays> holidays = null;
			Date todayDate=new Date();
			int currentMonth=DateFunctions.getMonthOfDate(todayDate);
			int currentYear = DateFunctions.getDayOfYear(new Date());
						Calendar calendar = Calendar.getInstance();
					 	 yearId = DateFunctions.getDayOfYear(new Date());
						if (monthId > 0) 
							{
							if (monthId == currentMonth) {
								monthTotalDays = DateFunctions.getDayOfMonth(new Date());
							} else {
								monthTotalDays =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
							}
							if (monthId == currentMonth && yearId == currentYear) {
								//holidays = adminManager.getAll(SchoolHolidays.class,"monthId="+ monthId+ " and academicYearId="+academicYear.getId() + " and custId="+ custId+ " and holidayDate <= '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date())+ "'");
								holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(custId,academicYear.getId(),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()),null,null,null,null,null,monthId,"holidayBetweenDates",null);
							} else {
								//holidays = adminManager.getAll(SchoolHolidays.class,"monthId="+ monthId+ " and academicYearId="+ academicYear.getId() + " and custId="+ custId+ " and holidayDate <= '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate) + "'");
								holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(custId,academicYear.getId(),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate),null,null,null,null,null,monthId,"holidayBetweenDates",null);
							}
							if (!ObjectFunctions.isNullOrEmpty(holidays)) {
							days=	monthTotalDays -= holidays.size();
							}
						
						} 
		 return days;
	 }
    
    public void updateStudentsFilledCountToSections(){
    	List<Customer> customersList = adminManager.getAll(Customer.class,"status='"+Constants.YES_STRING+"'");
    	if(!ObjectFunctions.isNullOrEmpty(customersList)){
    	int studentCount = 0;
    	List<AcademicYear> academicYearList = null;
    	List<StudyClass> studyClassList = null;
    	    for(Customer customer : customersList){
    	   	 if(!ObjectFunctions.isNullOrEmpty(customer)){ 
    	   		academicYearList =   adminManager.getAll(AcademicYear.class,"custId="+customer.getId());
    	   	 if(!ObjectFunctions.isNullOrEmpty(academicYearList)){
    	   		 for(AcademicYear academicYear : academicYearList){
    	   			 studyClassList = adminManager.getAll(StudyClass.class,"custId="+customer.getId()+" and academicYearId="+academicYear.getId());
    	    	   	 if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
    	    	   	 for(StudyClass studyClass : studyClassList){
    	    	   	 if(!ObjectFunctions.isNullOrEmpty(studyClass)){
    	    	   	 studentCount = adminManager.getCount("student","classSectionId="+studyClass.getId()+" and custId="+customer.getId()+" and academicYearId="+academicYear.getId());
    	    	   	 studyClass.setFilledSeats(studentCount);
    		    	 if(studyClass.getFilledSeats() > studyClass.getSectionCapacity()){
    		    		studyClass.setSectionCapacity(studentCount);
    		    	 }
    		    	 adminManager.save(studyClass);
    		    	}
    	    	   	studyClass = null;
    	    	   	studentCount = 0;
    		       }
    	    	  }
    	    	  academicYear=null; 
    	   		}
    	    }
    	   	academicYearList = null;
    	  }
    	  customer=null;  	 
    	}
      }
    customersList=null;
   }
    public void updateUserImagePathsFromExist() throws Throwable{
    	if (log.isDebugEnabled()) {
			log.debug("Entering 'updateUserImagePathsFromExist' method");
		}
  		 try{
  			 StringBuffer existedPath = null;
  			 StringBuffer imagePath = null;
  			 StringBuffer newImagePath = null;
  			 UserImage imageObject = null;
  			 File oldFile = null;
  			 boolean fileStatus = false;
  			 File newFile = null;
  			 File newFileDir = null;
  			 boolean status = false;
  			 User custUser = null;
			 WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			 List<Object[]> studentsList = adminManager.getAll(" select custId,academicYearId,imageName,imagePath,imageId,studentId,accountId,custImageId from vw_studentDetails  order by  custId ");
			 if(!ObjectFunctions.isNullOrEmpty(studentsList)){
				 User studUser = null;
				 for(Object[] student : studentsList){
					  existedPath = new StringBuffer();
	  	  			  imagePath = new StringBuffer();
	  	  			  newImagePath = new StringBuffer();
					 if(!ObjectFunctions.isNullOrEmpty(student[0]) && !ObjectFunctions.isNullOrEmpty(student[1]) && !ObjectFunctions.isNullOrEmpty(student[5]) && !ObjectFunctions.isNullOrEmpty(student[6]) ){
						 studUser = (User)adminManager.get(User.class,"id="+Long.valueOf(student[6].toString()));
						 if(!ObjectFunctions.isNullOrEmpty(studUser)){
							 if(!ObjectFunctions.isNullOrEmpty(studUser.getProfileImage())){
		  						 imageObject = (UserImage)adminManager.get(UserImage.class,studUser.getProfileImage().getId());
		  						 if(!ObjectFunctions.isNullOrEmpty(imageObject)){
	  		  						 imagePath.append(imageObject.getPath()).append(imageObject.getName());
	  	  						     existedPath.append(wac.getServletContext().getRealPath(imagePath.toString()));
	  			   				     log.debug(" studentoldimagepath for profile---->"+existedPath);
	    							 String imageName = imageObject.getId()+".jpg";
	    							 newFile = new File(existedPath.toString());
	  	   							 newImagePath.append("/userFiles/userimages/").append(student[0].toString()).append("/"+student[1].toString()+"/");
	  	   							 newFileDir = new File(wac.getServletContext().getRealPath(newImagePath.toString()));
	  	   						if(!new File(wac.getServletContext().getRealPath(newImagePath+imageName)).exists()){
	  		  						if(newFile.exists()){
		  		  						 if(!newFileDir.exists()){
			  	   								newFileDir.mkdirs();
			  	   					      }
	  		 							 oldFile = new File(wac.getServletContext().getRealPath(newImagePath+imageObject.getName()));
	  		  							 FileUtils.copyFileToDirectory(newFile, newFileDir);
		  	   							 log.debug(" studentnewimagepath for profile---->"+newImagePath+imageName);
	  		  							 status =  oldFile.renameTo(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
	  		  					         if(status){
	  		  					        	 imageObject.setName(imageName);   
	  		  	  							 imageObject.setThumbNail(imageName);
	  		  	  							 imageObject.setPath(newImagePath.toString());
	  		  	  					         imageObject.setType(getUploadContentType());
	  		  	  					         imageObject.setTypePath(Constants.FILE_TYPE_PATH + "img.gif");
	  		  	  					         imageObject.setFileUsed(Constants.FILE_TYPE_IMAGE);	
	  		  	  					         adminManager.saveOrUpdateObject(imageObject);
	  		  					         }else{
	  		  					        	FileUtils.deleteQuietly(oldFile);
	  										}
	  								  }
	  								}
	  	   						
		  						 }
	  		  					 existedPath = null;
	  			  				 newImagePath = null;
	  			  				 imageObject = null;
		  					     existedPath = new StringBuffer();
		   	  			         imagePath = new StringBuffer();
		   	  			         newImagePath = new StringBuffer();
							 }
							  custUser = (User) adminManager.get(User.class,"id="+Long.valueOf(student[0].toString()));
			  					if(!ObjectFunctions.isNullOrEmpty(custUser)){
			  						if(!ObjectFunctions.isNullOrEmpty(custUser.getProfileImage())){
			  							imageObject = (UserImage)adminManager.get(UserImage.class,custUser.getProfileImage().getId());
				  						 if(!ObjectFunctions.isNullOrEmpty(imageObject)){
			  		  						 imagePath.append(imageObject.getPath()).append(imageObject.getName());
			  	  						     existedPath.append(wac.getServletContext().getRealPath(imagePath.toString()));
			  			   				     log.debug(" customeroldimagepath for profile---->"+existedPath);
			    							 String imageName = imageObject.getId()+".jpg";
			    							 newFile = new File(existedPath.toString());
			  	   							 newImagePath.append("/userFiles/userimages/").append(student[0].toString()).append("/"+student[1].toString()+"/");
			  	   							 newFileDir = new File(wac.getServletContext().getRealPath(newImagePath.toString()));
			  	   						if(!new File(wac.getServletContext().getRealPath(newImagePath+imageName)).exists()){
			  		  						if(newFile.exists()){
				  		  						 if(!newFileDir.exists()){
					  	   								newFileDir.mkdirs();
					  	   					      }
			  		 							 oldFile = new File(wac.getServletContext().getRealPath(newImagePath+imageObject.getName()));
			  		  							 FileUtils.copyFileToDirectory(newFile, newFileDir);
				  	   							 log.debug(" customernewimagepath for profile---->"+newImagePath+imageName);
			  		  							 status =  oldFile.renameTo(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
			  		  					         if(status){
			  		  					        	 imageObject.setName(imageName);   
			  		  	  							 imageObject.setThumbNail(imageName);
			  		  	  							 imageObject.setPath(newImagePath.toString());
			  		  	  					         imageObject.setType(getUploadContentType());
			  		  	  					         imageObject.setTypePath(Constants.FILE_TYPE_PATH + "img.gif");
			  		  	  					         imageObject.setFileUsed(Constants.FILE_TYPE_IMAGE);	
			  		  	  					         adminManager.saveOrUpdateObject(imageObject);
			  		  					         }else{
			  		  					        	FileUtils.deleteQuietly(oldFile);
													}
												}
											}

										}
			  		  					 existedPath = null;
			  			  				 newImagePath = null;
			  			  				 imagePath = null;
			  			  				 imageObject = null;
				  					     existedPath = new StringBuffer();
				   	  			         imagePath = new StringBuffer();
				   	  			         newImagePath = new StringBuffer();
			  						}
			  					}
							 if(!ObjectFunctions.isNullOrEmpty(student[7].toString())){
					   				imageObject = (UserImage)adminManager.get(UserImage.class, Long.valueOf(student[7].toString()));
						   			 if(!ObjectFunctions.isNullOrEmpty(imageObject)){
						   				imagePath.append(imageObject.getPath()).append(imageObject.getName());
						   				 existedPath.append(wac.getServletContext().getRealPath(imagePath.toString()));
						   				 log.debug(" customeroldimagepath---->"+existedPath);
										 String imageName = student[7].toString()+".jpg";
										  newFile = new File(existedPath.toString());
										  newImagePath.append("/userFiles/userimages/").append(student[0].toString()).append("/"+student[1].toString()+"/");
										  newFileDir = new File(wac.getServletContext().getRealPath(newImagePath.toString()));
										if(!new File(wac.getServletContext().getRealPath(newImagePath+imageName)).exists()){
											if(newFile.exists()){
												 if(!newFileDir.exists()){
													 newFileDir.mkdir();
												 }
											   oldFile = new File(wac.getServletContext().getRealPath(newImagePath+"/"+imageObject.getName()));
											   fileStatus = oldFile.equals(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
											if(fileStatus == false){
												 FileUtils.copyFileToDirectory(newFile, newFileDir);
						   						 log.debug(" customernewimagepath---->"+newImagePath+imageName);
										         status =  oldFile.renameTo(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
										         if(status){
										        	 imageObject.setName(imageName);   
													 imageObject.setThumbNail(imageName);
													 imageObject.setPath(newImagePath.toString());
											         imageObject.setType(getUploadContentType());
											         imageObject.setTypePath(Constants.FILE_TYPE_PATH + "img.gif");
											         imageObject.setFileUsed(Constants.FILE_TYPE_IMAGE);	
											         adminManager.saveOrUpdateObject(imageObject);
										         }else{
										        	FileUtils.deleteQuietly(oldFile);
													}
												}
											}
										}
									}
						   			 existedPath = null;
		  			  				 newImagePath = null;
		  			  				 imageObject = null;
			  					     existedPath = new StringBuffer();
			   	  			         imagePath = new StringBuffer();
			   	  			         newImagePath = new StringBuffer();
								}
						 }
	  				 if(!ObjectFunctions.isNullOrEmpty(student[2]) && !ObjectFunctions.isNullOrEmpty(student[3]) && !ObjectFunctions.isNullOrEmpty(student[4]) && !ObjectFunctions.isNullOrEmpty(student[6]) ){
						 imagePath.append(student[3].toString()).append(student[2].toString());
						 existedPath.append(wac.getServletContext().getRealPath(imagePath.toString()));
		   				 log.debug(" studentoldimagepath for student in admin---->"+existedPath);
						 imageObject = (UserImage)adminManager.get(UserImage.class, Long.valueOf(student[4].toString()));
						 if(!ObjectFunctions.isNullOrEmpty(imageObject)){
							 String imageName = student[4].toString()+".jpg";
							 newFile = new File(existedPath.toString());
							 newImagePath.append("/userFiles/userimages/").append(student[0].toString()).append("/"+student[1].toString()+"/");
							 newFileDir = new File(wac.getServletContext().getRealPath(newImagePath.toString()));
						if(!new File(wac.getServletContext().getRealPath(newImagePath+"/"+imageName)).exists()){
	  						if(newFile.exists()){
	  							if(!newFileDir.exists()){
	  								newFileDir.mkdirs();
	  					        }
	 							 oldFile = new File(wac.getServletContext().getRealPath(newImagePath+"/"+student[2].toString()));
	  							 fileStatus = oldFile.equals(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
	  	  					if(fileStatus == false){
	  							 FileUtils.copyFileToDirectory(newFile, newFileDir);
		   						 log.debug(" studentnewimagepath for student in admin---->"+newImagePath+imageName);
	  							 status =  oldFile.renameTo(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
	  					         if(status){
	  					        	 imageObject.setName(imageName);   
	  	  							 imageObject.setThumbNail(imageName);
	  	  							 imageObject.setPath(newImagePath.toString());
	  	  					         imageObject.setType(getUploadContentType());
	  	  					         imageObject.setTypePath(Constants.FILE_TYPE_PATH + "img.gif");
	  	  					         imageObject.setFileUsed(Constants.FILE_TYPE_IMAGE);	
	  	  					         adminManager.saveOrUpdateObject(imageObject);
	  					         }else{
	  					        	FileUtils.deleteQuietly(oldFile);
									}
								}
							  }
							}
						}
					 }
					}
					 existedPath = null;
	  				 newImagePath = null;
	  				 imageObject = null;
				}
			}
			 imageObject = null;
			 studentsList = null;			 
			 List<Object[]> staffsList = adminManager.getAll(" select custId,academicYearId,imageName,imagePath,imageId,accountId from vw_staffDetails   order by  custId ");
			 if(!ObjectFunctions.isNullOrEmpty(staffsList)){
	  			  oldFile = null;
	  			  fileStatus = false;
	  			  newFile = null;
	  			  newFileDir = null;
	  			  status = false;
				 for(Object[] staff : staffsList){
					  existedPath = new StringBuffer();
	  	  			  imagePath = new StringBuffer();
	  	  			  newImagePath = new StringBuffer();
					 if(!ObjectFunctions.isNullOrEmpty(staff[0]) && !ObjectFunctions.isNullOrEmpty(staff[1]) && !ObjectFunctions.isNullOrEmpty(staff[2]) && !ObjectFunctions.isNullOrEmpty(staff[3]) && !ObjectFunctions.isNullOrEmpty(staff[4]) && !ObjectFunctions.isNullOrEmpty(staff[5]) ){
						 imagePath.append(staff[3].toString()).append(staff[2].toString());
						 existedPath.append(wac.getServletContext().getRealPath(imagePath.toString()));
		   				 log.debug(" staffoldimagepath---->"+existedPath);
						 imageObject = (UserImage)adminManager.get(UserImage.class, Long.valueOf(staff[4].toString()));
						 if(!ObjectFunctions.isNullOrEmpty(imageObject)){
							 String imageName = staff[4].toString()+".jpg";
							 newFile = new File(existedPath.toString());
							 newImagePath.append("/userFiles/userimages/").append(staff[0].toString()).append("/"+staff[1].toString()+"/");
							 newFileDir = new File(wac.getServletContext().getRealPath(newImagePath.toString()));
							if(!new File(wac.getServletContext().getRealPath(newImagePath+imageName)).exists()){
							if(newFile.exists()){
								 if(!newFileDir.exists()){
	  								 newFileDir.mkdir();
	  							 }
							   oldFile = new File(wac.getServletContext().getRealPath(newImagePath+"/"+staff[2].toString()));
							   fileStatus = oldFile.equals(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
	  	  					if(fileStatus == false){
	  							 FileUtils.copyFileToDirectory(newFile, newFileDir);
		   						 log.debug(" staffnewimagepath---->"+newImagePath+imageName);
	  					         status =  oldFile.renameTo(new File(wac.getServletContext().getRealPath(newImagePath+imageName)));
	  					         if(status){
	  					        	 imageObject.setName(imageName);   
	  	  							 imageObject.setThumbNail(imageName);
	  	  							 imageObject.setPath(newImagePath.toString());
	  	  					         imageObject.setType(getUploadContentType());
	  	  					         imageObject.setTypePath(Constants.FILE_TYPE_PATH + "img.gif");
	  	  					         imageObject.setFileUsed(Constants.FILE_TYPE_IMAGE);	
	  	  					         adminManager.saveOrUpdateObject(imageObject);
	  					         }else{
	  					        	FileUtils.deleteQuietly(oldFile);
									}
								}
							}
						}
					}
	
				}
				 existedPath = null;
  				 newImagePath = null;
			}
			 imageObject = null;
			 staffsList = null;
		   }
		     wac = null;
	      }
  		 catch(Exception ex){
		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}

}