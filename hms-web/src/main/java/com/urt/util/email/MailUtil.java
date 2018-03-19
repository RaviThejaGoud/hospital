package com.urt.util.email;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.service.mail.EmailBean;
import com.spring.context.SpringContextAware;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.ContactUs;
import com.urt.persistence.model.common.MessageActivity;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.Reminder;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.common.StudentMonthlyAttendance;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.common.ViewPermissionSettings;
import com.urt.persistence.model.common.ViewStaffPermissionRequests;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.CustomerEnrollmentRequest;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.exam.Developers;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamSchedulesDateWiseComparator;
import com.urt.persistence.model.exam.QuizQuestion;
import com.urt.persistence.model.jrexception.JRException;
import com.urt.persistence.model.study.ClassAssignment;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.util.http.URTHttpClient;
import com.urt.webapp.action.jrexception.JRExceptionClient;

/**
 * Class for sending e-mail messages based on Velocity templates
 * or with attachments.
 * 
 * <p><a href="MailsSender.java.html"><i>View Source</i></a></p>
 * 
 * @author Sreeramulu J
 */
public class MailUtil {

    protected static final Log log = LogFactory.getLog(MailUtil.class);
    @Autowired
    protected AdminManager adminManager;
    protected EmailBean emailBean;
    protected MailSenderManager mailSender;
    protected String contactEmail;
    protected String contactPasword;
    
    public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPasword() {
		return contactPasword;
	}

	public void setContactPasword(String contactPasword) {
		this.contactPasword = contactPasword;
	}
	
		
    public MailUtil(String contactEmail, String contactPasword) {
		super();
		this.contactEmail = contactEmail;
		this.contactPasword = contactPasword;
	}

    public int sendEmailToSchoolWideMessages(String fromEmail) {
    	int responseCode=0;
	try {
		    String[] bodyPara = new String[4];
		    StringBuffer msg = new StringBuffer();
		    msg.append("Dear" + "&nbsp; Sir/Madam");
		    msg.append(",");
		    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		    msg.append(getEmailBean().getBody());
		    msg.append("<br />");
		    bodyPara[0] = msg.toString();
		    getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
		    getEmailBean().setFromAddress(fromEmail);
		    getEmailBean().setAttachmentFiles(getEmailBean().getAttachmentFiles());
		    getMailSender().setContactEmail(this.getContactEmail());
		    getMailSender().setContactPassword(this.getContactPasword());
		    responseCode = getMailSender().sendHtmlMail(getEmailBean());
		    log.debug("Hurray success....+ email sent successfully");
		    // saveActiveLogs(getEmailBean());
			setEmailBean(null);
			if(responseCode == 0){
				log.debug("Hurray success....+ email sent successfully");
				return responseCode;
			}else 
				return responseCode;
		} catch (Exception ex) {
		    ex.printStackTrace();
		    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		    log.debug(ex.getMessage());
		    responseCode = 3;
		}
		return responseCode;
    }
    
    
	/**
	 * 
	 */
	public MailUtil() {
		// TODO Auto-generated constructor stub
	}

	/** @param adminManager
     * @param emailBean */
    public MailUtil(String[] emailAddresses, String subject, String description, User user) {
		// this.adminManager = adminManager;
		this.emailBean = new EmailBean();
		this.emailBean.setFromAddress(user.getUsername());
		this.emailBean.setBccAddress(emailAddresses);
		//this.emailBean.setToAddress(emailAddresses);
		this.emailBean.setSubject(subject);
		this.emailBean.setFromWhom(user.getUserRoleDescription());
		this.emailBean.setBody(description);
    }
    public MailUtil(String[] emailAddresses, String subject, long custId, String sender,String userRoleName,String fromEmail) {
    	// this.adminManager = adminManager;
    	this.emailBean = new EmailBean();
    	//this.emailBean.setFromAddress("admin@easyschool.in");
		this.emailBean.setFromAddress(fromEmail);
    	//this.emailBean.setToAddress(emailAddresses);
    	this.emailBean.setBccAddress(emailAddresses);
    	this.emailBean.setSubject(subject);
    	this.emailBean.setCustomerId(custId);
    	this.emailBean.setSender(sender);
    	this.emailBean.setFromWhom(userRoleName);
    }
    public MailUtil(String[] emailAddresses, String subject, String description, User user,String[] attachmentFiles,String fromCustomerEmail) {
		// this.adminManager = adminManager;
		this.emailBean = new EmailBean();
		this.emailBean.setFromAddress(fromCustomerEmail);
		//this.emailBean.setToAddress(emailAddresses);
		this.emailBean.setBccAddress(emailAddresses);
		this.emailBean.setSubject(subject);
		//this.emailBean.setFromWhom(user.getUserRoleDescription());
		this.emailBean.setBody(description);
		this.emailBean.setAttachmentFiles(attachmentFiles);
    }
    public MailUtil(String[] emailAddresses, String subject, List<Customer> customersList) {
  		// this.adminManager = adminManager;
    	for(Customer customer : customersList){
	  		this.emailBean = new EmailBean();
	  		this.emailBean.setFromAddress("admin@easyschool.in");
	  		//this.emailBean.setToAddress(emailAddresses);
	  		this.emailBean.setBccAddress(emailAddresses);
	  		this.emailBean.setSubject(subject);
	  		this.emailBean.setFromWhom(customer.getSender());
    	}
    }
    
    public MailUtil(String[] emailAddresses, String subject, long custId, String sender,String userRoleName,String fromEmail,String supportTeamEmails) {
    	this.emailBean = new EmailBean();
    	this.emailBean.setFromAddress(fromEmail);
    	this.emailBean.setToAddress(emailAddresses);
    	this.emailBean.setSubject(subject);
    	this.emailBean.setCustomerId(custId);
    	this.emailBean.setSender(sender);
    	this.emailBean.setFromWhom(userRoleName);
    }
    
    public MailUtil(String[] emailAddresses, String subject, String description, String schoolName) {
		// this.adminManager = adminManager;
		this.emailBean = new EmailBean();
		this.emailBean.setFromAddress(schoolName);
		this.emailBean.setBccAddress(emailAddresses);
		//this.emailBean.setToAddress(emailAddresses);
		this.emailBean.setSubject(subject);
		this.emailBean.setFromWhom(schoolName);
		this.emailBean.setBody(description);
    }
    
    /** @return the adminManager */
    public AdminManager getAdminManager() {
		if (ObjectFunctions.isNullOrEmpty(this.adminManager)) {
		    adminManager = (AdminManager) SpringContextAware.getBean("adminManager");
		}
		return this.adminManager;
    }

    /** @param adminManager
     *            the adminManager to set */
    public void setAdminManager(AdminManager adminManager) {
    	this.adminManager = adminManager;
    }

    /** @return the emailBean */
    public EmailBean getEmailBean() {
    	return this.emailBean;
    }


    /** @param emailBean
     *            the emailBean to set */
    public void setEmailBean(EmailBean emailBean) {
    	this.emailBean = emailBean;
    }


    /** @return the mailSender */
    public MailSenderManager getMailSender() {
		if (ObjectFunctions.isNullOrEmpty(this.mailSender)) {
		    mailSender = new MailSenderManager();
		}
		return this.mailSender;
    }

    /** @param mailSender
     *            the mailSender to set */
    public void setMailSender(MailSenderManager mailSender) {
    	this.mailSender = mailSender;
    }
    
	public String getHostUrl(){
		return ServletActionContext.getServletContext().getInitParameter("hostUrl");
	}
	
    public boolean sendEmailToParentOrStaff(ViewAllUsers viewAllUsers) {
    	try {
	    	    StringBuffer msg = new StringBuffer();
	    	    if(Constants.SCHOOL_STUDENT.equalsIgnoreCase(viewAllUsers.getRoleName()) || Constants.SCHOOL_PARENT.equalsIgnoreCase(viewAllUsers.getRoleName()))
				{
					msg.append("Dear" + "&nbsp;" + viewAllUsers.getFatherName());
				}
				else
				{
					msg.append("Dear" + "&nbsp;" + viewAllUsers.getPersonFullName());
				}
	    	    msg.append(",");
	    	    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
	    	    msg.append(getEmailBean().getBody());
	    	    msg.append("<br />");
	    	    getEmailBean().setBody(msg.toString());
	    	    msg=null;
	    	    if(!StringFunctions.isNullOrEmpty(viewAllUsers.getParentEmail()))
				{
	    	    	getEmailBean().setFromAddress(viewAllUsers.getParentEmail());
				}
	    	    getMailSender().sendHtmlMail(getEmailBean());
	    	    log.debug("Hurray success....+ email sent successfully");
	    	    // saveActiveLogs(getEmailBean());
	    		setEmailBean(null);
	    		viewAllUsers=null;
    		} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	    log.debug(ex.getMessage());

    		}
    	return false;
    }
    public void sendMailForPwdChange(String password,String username,String fromEmail) {
		try {
			String[] bodyPara = new String[4];
			String[][] tableData = new String[2][2];
			//log.debug("User Student : " + user.getUsername());
			StringBuffer msg = new StringBuffer();
			msg.append("This e-mail is to confirm that your password has been updated. To access your account online, sign in at  <a href='"+ getHostUrl()+ "' target='_new'>HYNIVA Eazyschool Application Management Software</a> with this e-mail address and password.");
			bodyPara[0] = msg.toString();
			msg = new StringBuffer();
			bodyPara[1] = msg.toString();
			tableData[0][0] = "User name &nbsp;:&nbsp;";
			tableData[0][1] = username;
			tableData[1][0] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password&nbsp;:&nbsp;";
			tableData[1][1] = password;
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "115");
			msg = new StringBuffer();
			msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
			bodyPara[3] = msg.toString();
			getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			getEmailBean().setFromAddress(fromEmail);
			getMailSender().setContactEmail(this.getContactEmail());
			getMailSender().setContactPassword(this.getContactPasword());
			getMailSender().sendHtmlMail(getEmailBean());
			setEmailBean(null);
			tableData=null;
			msg=null;
			bodyPara=null;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug(ex.getMessage());
		}
	}
    public void sendMailForCustomerRegistration(Customer customer){
		try{
	        String[] bodyPara = new String[4];
	        String[][] tableData = new String[2][2];
	        log.debug("Customer Student : " + customer.getCustEmail());
	        StringBuffer msg = new StringBuffer();
	        msg.append("Dear" +"&nbsp;"+ customer.getCustomerFullPersonName());
	        msg.append(",");
	        msg.append("<br>");
	        msg.append("<br>");
	        msg.append("Your School Account for "+customer.getOrganization()+" has been created on "+getHostUrl());
	        bodyPara[0]=msg.toString();	       
	        tableData[0][0]="Your username is&nbsp;:&nbsp;";
	        tableData[0][1]=customer.getCustEmail();
	        tableData[1][0]="Your password is &nbsp;:&nbsp;";
	        tableData[1][1]=customer.getPassword();
            bodyPara[1] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
	        msg = new StringBuffer();
	        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
	        bodyPara[2]=msg.toString();	    	        
	        msg = new StringBuffer();
	        msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
	        msg.append("<br /><br />");
	        msg.append("If you have any questions or need any help with getting your account set up, please email support@eazyschool.com or call 080-46620999");
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			getMailSender().sendHtmlMail(getEmailBean());
            log.debug("Hurray success....+ email sent successfully");
            //saveActiveLogs(getEmailBean());
            setEmailBean(null);
			tableData=null;
			msg=null;
			bodyPara=null;
            customer=null;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
	}
    public void sendQuizAlertAndEmailToParentAnSdtudent(Student student,Person person,Date currentDate,List<QuizQuestion> questionAnswerList,String fromEmail) {
	    try {
			String[] bodyPara = new String[4];
			StringBuffer msg = new StringBuffer();
			msg.append("Dear" + "&nbsp;" +person.getFatherName());
			msg.append(",");
			msg.append("<br>");
			msg.append("<br>");
			bodyPara[0] = msg.toString();
			msg = new StringBuffer(); 
			msg.append("For "+person.getPersonFirstLastNameOnly()+"  RollNumber="+student.getRollNumber());
			msg.append("<br>");
			msg.append("We are cunducting Quiz Qhiz Details are given below");
			msg.append("<br>");
			msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Quiz Name</th><th class='head'>Start Date</th><th class='head'>End Date</th></tr></thead>");
			if (!ObjectFunctions.isNullOrEmpty(questionAnswerList)) {
				for(QuizQuestion quizDetails:questionAnswerList)
				{
					//QuizQuestion quizDetails = (QuizQuestion)obj;
					if (!ObjectFunctions.isNullOrEmpty(quizDetails)) {
						msg.append("<tr><td>"+	quizDetails.getQuestionName()+"</td>");
						msg.append("<td>"+	quizDetails.getStartDate()+"</td>");
						msg.append("<td>"+	quizDetails.getEndDate()+"</td></tr>");
					}
					quizDetails=null;
				}
			}
			msg.append("</table>");
			msg.append("By school Exatra Curricular Activities department.");
			bodyPara[1] = msg.toString();
			msg = new StringBuffer();
			msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
			msg.append("<br />");
			bodyPara[3] = msg.toString();
			getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			getEmailBean().setFromAddress(fromEmail);
			getMailSender().setContactEmail(this.getContactEmail());
			getMailSender().setContactPassword(this.getContactPasword());
			getMailSender().sendHtmlMail(getEmailBean());
			
			log.debug("Hurray success....+ email sent successfully");
			student=null;
			person=null;
			questionAnswerList=null;
			setEmailBean(null);
			msg=null;
			bodyPara=null;
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
		//return null;
	}
    public void sendMailForOnlineRegistration(OnlineApplicationDetails onlineApplicationDetails,AdmissionSettings admissionSettings){
		try{
	        String[] bodyPara = new String[4];
	        String[][] tableData = new String[4][3];
	        log.debug("Parent Email : " + onlineApplicationDetails.getParentEmail());
	        StringBuffer msg = new StringBuffer();
	        msg.append("Dear" +"&nbsp;"+ onlineApplicationDetails.getFatherName());
	        msg.append(",");
	        msg.append("<br>");
	        msg.append("<br>");
	        if(admissionSettings.isTestConducted())
	        {
	        	msg.append("You have successfully submitted Online Application Form for your childern.Your children needs to take entrance test on <b>" + admissionSettings.getEntranceDateStr() + "</b> (tentative date). Here are the details of your children with application number and entrance date used for further purpose");
	        }
	        else
	        {
	        	msg.append("You have successfully submitted Online Application Form for your childern.Here are the details of your children with application number used for further purpose");
	        }
	        bodyPara[0]=msg.toString();	       
	        tableData[0][0]="Student Name&nbsp;:&nbsp;";
	        tableData[0][1]=onlineApplicationDetails.getChildrenFullPersonName();
	        tableData[1][0]="Class &nbsp;:&nbsp;";
	        tableData[1][1]=onlineApplicationDetails.getClassName();
	        tableData[2][0]="Application Number &nbsp;:&nbsp;";
	        tableData[2][1]=onlineApplicationDetails.getApplicationNumber();
	        if(admissionSettings.isTestConducted())
	        {
	        	tableData[3][0]="Entrance Date &nbsp;:&nbsp;";
		        tableData[3][1]=admissionSettings.getEntranceDateStr();
	        }else{
	        	tableData[3][0]="";
		        tableData[3][1]="";
	        }
            bodyPara[1] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
	        msg = new StringBuffer();
	        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
	        bodyPara[2]=msg.toString();	    	        
	        msg = new StringBuffer();
	        msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
	        msg.append("<br /><br />");
	        msg.append("If you have any questions or need any help on application status, please email support@eazyschool.com");
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
	        getMailSender().sendHtmlMail(getEmailBean());
            log.debug("Hurray success....+ email sent successfully");
            setEmailBean(null);
			tableData=null;
			msg=null;
			bodyPara=null;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
    	onlineApplicationDetails= null;
    	admissionSettings=null;
	}
    public void sendMailForContactUs(ContactUs contactUs) {
    	try{
    	    String[] bodyPara = new String[4];
	        String[][] tableData = new String[3][3];
	        log.debug("User Name : " + contactUs.getCustomerEmail());
	        StringBuffer msg = new StringBuffer();
	        msg.append("This e-mail is to confirm that you have contacted eazySchool admin.EazySchool Support Team will contact you soon through  e-mail or phone number you have provided while filling contact form.");
	        bodyPara[0]=msg.toString();
	        msg = new StringBuffer();
	        bodyPara[1]=msg.toString();
	        tableData[0][0]="E-mail Address&nbsp;:&nbsp;";
	        tableData[0][1]=contactUs.getCustomerEmail();
	        tableData[1][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Phone Number&nbsp;:&nbsp;";
	        tableData[1][1]=contactUs.getPhoneNumber();
	        tableData[2][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Comments&nbsp;:&nbsp;";
	        tableData[2][1]=contactUs.getComments();
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "180");
	        msg = new StringBuffer();
	        msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
	        getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
                //saveActiveLogs(getEmailBean());
            setEmailBean(null);
			tableData=null;
			msg=null;
			bodyPara=null;
            contactUs=null;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
    }
    public void sendMailForAllAdminAndMasterAdmins(List<Object[]> viewLoginUsersList,String customerFirstName,String monthStartDate,String monthEndDate) {
	    try {
	    	//boolean mailSent=true;
			String[] bodyPara = new String[4];
				StringBuffer msg = new StringBuffer();
				msg.append("Dear" + "&nbsp;" +customerFirstName);
				msg.append(",");
				msg.append("<br>");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer(); 
				msg.append(" Warm greetings from Eazyschool ");
				msg.append("<br>");
				bodyPara[1] = msg.toString();
				msg = new StringBuffer();
				msg.append("We hope that your experience with Eazyschool system has been enjoyable. Please find your Account usage form the period of "+ monthStartDate+" to "+monthEndDate);
				msg.append("<br />");
				msg.append("<br />");
				msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Role Name</th><th class='head'>Total Users Count</th><th class='head'>Logged In User Count</th></tr></thead>");
		        if(!ObjectFunctions.isNullOrEmpty(viewLoginUsersList)){
		        	for(Object[] allUsers:viewLoginUsersList){
						if (!ObjectFunctions.isNullOrEmpty(allUsers)) {
							msg.append("<tr><td>"+allUsers[0].toString()+"</td>");
							msg.append("<td>"+allUsers[1].toString()+"</td>");
							msg.append("<td>"+allUsers[3].toString()+"</td></tr>");
						}
					}
				}
				msg.append("</table>");
				msg.append("<br />");
				bodyPara[2] = msg.toString();
				msg = new StringBuffer();
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
				msg.append("<br />");
				bodyPara[3] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getMailSender().sendHtmlMail(getEmailBean());
					log.debug("Hurray success....+ email sent successfully");
					//saveActiveLogs(getEmailBean());
					//return getEmailBean();
					setEmailBean(null);
					msg=null;
					bodyPara=null;
				viewLoginUsersList=null;
		} catch (Exception ex) {
			//mailSent=false;
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
		//return null;
	}
    public void sendMailForAllParentsForFeeAmount(ViewStudentClassDetails student,SchoolTerms feeType,long dueAmount,String custOrganizationName,long totalUnpaidPreviewAmount,String fromEmail) {
	    try {
	    	//mailSent=true;
			String[] bodyPara = new String[4];
			if(!StringFunctions.isNullOrEmpty(student.getParentEmail()) && !StringFunctions.isNullOrEmpty(student.getFatherName())){
				long totalamount=totalUnpaidPreviewAmount+dueAmount;
				StringBuffer msg = new StringBuffer();
				
				msg.append("Dear" + "&nbsp;" +student.getFatherName());
				msg.append(",");
				msg.append("<br>");
				
				bodyPara[0] = msg.toString();
				msg = new StringBuffer(); 
				msg.append("This is just a friendly reminder that your children "+student.getFullName()+", "+feeType.getTermName()+" Fee Due date is "+feeType.getDueDateStr()+".");
				msg.append("<br>");
				bodyPara[1] = msg.toString();
				msg = new StringBuffer(); 
				msg.append("Please make the payment before of the due date to avoid the late payment charges.");
				msg.append("<br />");
				msg.append("<br />");
				msg.append("<b> Fee Details:- </b>");
				msg.append("<br>");
				msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' '><tr><td>Class Name</td>");
				msg.append("<td>"+	student.getClassAndSection()+"</td></tr>");				
				msg.append("<tr><td>Term Name</td>");
				msg.append("<td>"+	feeType.getTermName()+"</td></tr>");
				msg.append("<tr><td>Due Amount</td>");
				msg.append("<td>"+ dueAmount +"</td></tr>");
				if(totalUnpaidPreviewAmount>0){
					msg.append("<tr><td>Previous Due Amount</td>");
					msg.append("<td>"+ totalUnpaidPreviewAmount +"</td></tr>");
				}
				msg.append("<tr><td>Total Amount</td>");
				msg.append("<td>"+ totalamount +"</td></tr>");				
				msg.append("<tr><td>Due Date</td>");
				msg.append("<td>"+	feeType.getDueDateStr()+"</td></tr>");				
				msg.append("</table>");
				msg.append("<br />");
				msg.append("Please ignore this communication if payment is already remitted.");
				msg.append("<br />");
				msg.append("<br>");
				msg.append("Thanks & Regards");
				msg.append("<br>");
				msg.append(custOrganizationName+" Finance department.");
				bodyPara[2] = msg.toString();
				msg = new StringBuffer();
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
				msg.append("<br />");
				bodyPara[3] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getEmailBean().setFromAddress(fromEmail);
				getMailSender().setContactEmail(this.getContactEmail());
				getMailSender().setContactPassword(this.getContactPasword());
				getMailSender().sendHtmlMail(getEmailBean());
					log.debug("Hurray success...."+student.getParentEmail()+" email sent successfully");
					setEmailBean(null);
					msg=null;
					bodyPara=null;
					//saveActiveLogs(getEmailBean());
					//return getEmailBean();
			}
			setEmailBean(null);
			student=null;
			feeType=null;
		} catch (Exception ex) {
			//mailSent=false;
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
		//return null;
	}
    public void sendMailForAllParentsForExamDateAlert(Object[] viewStudentPersonAccountDetails,List<ExamSchedules> schedulesList,String custEmail,String custOrganization,String fromEmail) {
		try{
			ExamSchedules schedule= schedulesList.get(0);
	        String[] bodyPara = new String[4];
	        String[][] tableData = new String[3][3];
	        StringBuffer msg = new StringBuffer();
	        String mailContent = schedule.getExamType().getMailContentDesc();
	    	mailContent=mailContent.replace("<br>", "\n");
	    	mailContent=mailContent.replaceAll("<parent name>",viewStudentPersonAccountDetails[0].toString());
	    	mailContent=mailContent.replaceAll("<examination Type>",schedule.getExamType().getExamType());
	    	mailContent=mailContent.replaceAll("<start date>",schedule.getStartDateStr());
	    	mailContent=mailContent.replaceAll("<customer specified name>",custEmail);
	    	bodyPara[1]=mailContent;
	        tableData[0][0]="Student Name&nbsp;:&nbsp;";
	        tableData[0][1]=viewStudentPersonAccountDetails[1].toString();
	        tableData[1][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Student RollNumber&nbsp;:&nbsp;";
	        tableData[1][1]=viewStudentPersonAccountDetails[2].toString();
	        tableData[2][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Class&nbsp;:&nbsp;";
	        tableData[2][1]=schedule.getClassSection().getClassAndSection();
	        msg = new StringBuffer(); 
			msg.append("<br>");
			msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Subject Name</th><th class='head'>Subtype</th><th class='head'>Exam Date</th><th class='head'>Start Time</th><th class='head'>End Time</th></tr></thead>");
	         if (!ObjectFunctions.isNullOrEmpty(schedulesList)) {
	        	 Collections.sort(schedulesList,new ExamSchedulesDateWiseComparator());
	        	 for(ExamSchedules examDetails:schedulesList){
	        		 if (!ObjectFunctions.isNullOrEmpty(examDetails)) {
							msg.append("<tr><td>"+	examDetails.getSubjectName()+"</td>");
							msg.append("<td>"+	examDetails.getSubTypeName()+"</td>");
							msg.append("<td>"+	examDetails.getExamDateStr()+"</td>");
							msg.append("<td>"+	examDetails.getStartTime()+"</td>");
							msg.append("<td>"+	examDetails.getEndTime()+"</td></tr>");
					 }
					 examDetails=null;
	        	 }
	         }
	        msg.append("</table>");
			msg.append("<br>");
			bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "180");
	        msg.append("<br /><strong>**NOTE:</strong> This is an automated email from "+ custOrganization +" Please DO NOT REPLY to this email.");
	        bodyPara[3]=msg.toString();
	        getEmailBean().setSubject(schedule.getClassSection().getClassAndSection()+" : "+schedule.getExamType().getExamType()+" Exam Schedules");
	        getEmailBean().setFromAddress(fromEmail);
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
	        getMailSender().setContactEmail(this.getContactEmail());
	        getMailSender().setContactPassword(this.getContactPasword());
	        getMailSender().sendHtmlMail(getEmailBean());
	        log.debug("Hurray success....+ email sent successfully");
	        viewStudentPersonAccountDetails=null;
		}
		catch(Exception ex){
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
	}
    public void sendMailForAllParentsForChildrenAttendance(ViewStudentPersonAccountDetails student1,ExamSchedules examSchedules1,String totalabsentDays,String totalWorkingDays,String fromEmail) {
    	try{
            String[] bodyPara = new String[4];
            String[][] tableData = new String[4][4];
            StringBuffer msg = new StringBuffer();
            msg.append("Dear "+student1.getFatherName());
            msg.append("<br />");
            msg.append( " Please find the attendance details for the month of "+examSchedules1.getCurrentYear());
            bodyPara[0]=msg.toString();
            msg = new StringBuffer();
            msg.append("<br />");
            bodyPara[1]=msg.toString();
            tableData[0][0]="Student Name&nbsp;:&nbsp;";
            tableData[0][1]=student1.getFirstName();
            tableData[1][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Student RollNumber&nbsp;:&nbsp;";
            tableData[1][1]=String.valueOf(student1.getRollNumber());
            tableData[2][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TotalAbsentDays&nbsp;:&nbsp;";
            tableData[2][1]= totalabsentDays;
            tableData[3][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TotalWorkingDays&nbsp;:&nbsp;";
            tableData[3][1]=totalWorkingDays;
            msg.append("<br /><strong>**NOTE:</strong>Please contact to school if you have any question");
    	    bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "180");
            msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
            bodyPara[3]=msg.toString();
            getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
            getEmailBean().setFromAddress(fromEmail);
            getMailSender().setContactEmail(this.getContactEmail());
            getMailSender().setContactPassword(this.getContactPasword());
            getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
           // return getEmailBean();
            setEmailBean(null);
            student1=null;
            examSchedules1=null;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
    	//return null;
    } 
    public void sendMailForDevelopers(Developers developer,String fromEmail) {
    	try{
    	    String[] bodyPara = new String[4];
	        String[][] tableData = new String[3][3];
	        log.debug("User Name : " + developer.getEmailId());
	        String[] emailId = new String[2];
	        emailId[0] =developer.getEmailId();
	        StringBuffer msg = new StringBuffer();
	        msg.append("This e-mail is to confirm that you have contacted eazySchool admin.Eazy School Support Team will contact you soon through  e-mail you have provided while working on the assigned issue.");
	        bodyPara[0]=msg.toString();
	        msg = new StringBuffer();
	        bodyPara[1]=msg.toString();
	        tableData[0][0]="E-mail Address&nbsp;:&nbsp;";
	        tableData[0][1]=developer.getEmailId();
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "180");
	        msg = new StringBuffer();
	        msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
	        bodyPara[3]=msg.toString();
	        getEmailBean().setFromAddress(fromEmail);
	        getEmailBean().setToAddress(emailId);
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
	        getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
              //  saveActiveLogs(getEmailBean());
            setEmailBean(null);
            developer=null;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
    }
 // This method is for already registered parent is for another student at add process : on 07/07/2014 ----- rama
    public void sendMailForParent(Customer customer,String studentName,String sClassAndSection,String username,String pwd,String msgContent){
		try{
	        String[] bodyPara = new String[5];
	        String[][] tableData = new String[5][5];
	       // log.debug("Customer Student : "+rollNumber);
	        StringBuffer msg = new StringBuffer();
	       /* String path = (SpringContextAware.getHostUrl()+customer.getCustomerLogo().replace("..", "").trim()).toString();
	        msg.append("<img style='height: 64px; width: 120px;' src='"+path+"'>"+"&nbsp;"+ customer.getOrganization().toUpperCase());
	        log.debug("<img src='"+path+"'>");
	        msg.append("<br>");*/
	        msg.append("Dear" +"&nbsp;"+ "Parent");
	        msg.append(",");
	        msg.append("<br>");
	        msg.append("<br>");
	        msg.append(msgContent);
	        msg.append("<br>");
	        msg.append("<br>");
	       /* msg.append("Your children has been successfully joined");
	        bodyPara[0]=msg.toString();	 
	        msg.append("<br>");
	        msg.append("<br>");*/
	        msg.append("Following  are the login details to access EazySchool.");
	        bodyPara[0]=msg.toString();	 
	        tableData[0][0]="Website URL &nbsp;:&nbsp;";
	        tableData[0][1]="http://eazyschool.in"; 
	        tableData[1][0]="Student Name &nbsp;:&nbsp;";
	        tableData[1][1]=studentName;
	        tableData[2][0]="Class / Section &nbsp;:&nbsp;";
	        tableData[2][1]=sClassAndSection;
	        tableData[3][0]="Student Login ID &nbsp;:&nbsp;";
	        tableData[3][1]=username;
	        tableData[4][0]="Student Password &nbsp;:&nbsp;";
	        tableData[4][1]=pwd;
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
	        msg = new StringBuffer();
	        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
            getEmailBean().setFromAddress("admin@eazyschool.com");
	        getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
                //saveActiveLogs(getEmailBean());
                setEmailBean(null);
                msgContent = null;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
	}
 // This method is for already existed user as a parent for another student at edit process : on 07/07/2014 ---------rama.
    public void sendMailForExistedParent(Customer customer,String studentName,String sClassAndSection,String username,String pwd,String msgContent){
		try{
	        String[] bodyPara = new String[5];
	        String[][] tableData = new String[5][5];
	       // log.debug("Customer Student : "+rollNumber);
	        StringBuffer msg = new StringBuffer();
	      /*  String path = (SpringContextAware.getHostUrl()+customer.getCustomerLogo().replace("..", "").trim()).toString();
	        msg.append("<img style='height: 64px; width: 120px;' src='"+path+"'>"+"&nbsp;"+ customer.getOrganization().toUpperCase());
	        log.debug("<img src='"+path+"'>");
	        msg.append("<br>");*/
	        msg.append("Dear" +"&nbsp;"+ "Parent");
	        msg.append(",");
	        msg.append("<br>");
	        msg.append("<br>");
	        msg.append(msgContent);
	        msg.append("<br>");
	        msg.append("<br>");
	        msg.append("Following  are the login details to access EazySchool.");
	        bodyPara[0]=msg.toString();	 
	        tableData[0][0]="Website URL &nbsp;:&nbsp;";
	        tableData[0][1]="http://eazyschool.in";   
	        tableData[1][0]="Student Name &nbsp;:&nbsp;";
	        tableData[1][1]=studentName;
	        tableData[2][0]="Class / Section &nbsp;:&nbsp;";
	        tableData[2][1]=sClassAndSection;
	        tableData[3][0]="Student Login ID &nbsp;:&nbsp;";
	        tableData[3][1]=username;
	        tableData[4][0]="Student Password &nbsp;:&nbsp;";
	        tableData[4][1]=pwd;
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
	        msg = new StringBuffer();
	        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
            getEmailBean().setFromAddress("admin@eazyschool.com");
	        getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
                //saveActiveLogs(getEmailBean());
                setEmailBean(null);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
	}
 // This method is for already existed user with another role as a parent for another student at add && edit process : on 07/07/2014 ----------rama.
    public void sendMailToParentIfHeHasAnotherRole(Customer customer,String studentName,String sClassAndSection,String pUserName,String pPwd,String username,String pwd,String fromEmail){
		try{
	        String[] bodyPara = new String[7];
	        String[][] tableData = new String[7][7];
	       // log.debug("Customer Student : "+rollNumber);
	        StringBuffer msg = new StringBuffer();
	       /* String path = (SpringContextAware.getHostUrl()+customer.getCustomerLogo().replace("..", "").trim()).toString();
	        msg.append("<img style='height: 64px; width: 120px;' src='"+path+"'>"+"&nbsp;"+ customer.getOrganization().toUpperCase());
	        log.debug("<img src='"+path+"'>");
	        msg.append("<br>");*/
	        msg.append("Dear" +"&nbsp;"+ "Parent");
	        msg.append(",");
	        msg.append("<br>");
	        msg.append("<br>");
	      //  msg.append(msgContent);
	        msg.append("<br>");
	        msg.append("<br>");
	       /* msg.append("Your credentilas are updated.");
	        msg.append("<br>");
	        msg.append("<br>");*/
	        msg.append("Following  are the login details to access EazySchool.");
	        bodyPara[0]=msg.toString();	 
	        tableData[0][0]="Website URL &nbsp;:&nbsp;";
	        tableData[0][1]="http://eazyschool.in";   
	        tableData[1][0]="Student Name &nbsp;:&nbsp;";
	        tableData[1][1]=studentName;
	        tableData[2][0]="Class / Section &nbsp;:&nbsp;";
	        tableData[2][1]=sClassAndSection;
	        tableData[3][0]="Parent Login ID &nbsp;:&nbsp;";
	        tableData[3][1]=pUserName;
	        tableData[4][0]="Parent Password &nbsp;:&nbsp;";
	        tableData[4][1]=pPwd;
	        tableData[5][0]="Student Login ID &nbsp;:&nbsp;";
	        tableData[5][1]=username;
	        tableData[6][0]="Student Password &nbsp;:&nbsp;";
	        tableData[6][1]=pwd;
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
	        msg = new StringBuffer();
	        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
            getEmailBean().setFromAddress(fromEmail);
            getMailSender().setContactEmail(this.getContactEmail());
            getMailSender().setContactPassword(this.getContactPasword());
	        getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
                //saveActiveLogs(getEmailBean());
                setEmailBean(null);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
	}
    // This method is for parent registration for the first time (add  process of student): on 07/07/2014 ---------rama.
    public void sendMailToParentWithStudentCredentials(Customer customer,String studentName,String sClassAndSection,String Pusername,String Ppwd,String SuserName,String Spwd,String msgContent){
		try{
	        String[] bodyPara = new String[7];
	        String[][] tableData = new String[7][7];
	       // log.debug("Customer Student : "+rollNumber);
	        StringBuffer msg = new StringBuffer();
	       /* String path = (SpringContextAware.getHostUrl()+customer.getCustomerLogo().replace("..", "").trim()).toString();
	        msg.append("<img style='height: 64px; width: 120px;' src='"+path+"'>"+"&nbsp;"+ customer.getOrganization().toUpperCase());
	        log.debug("<img src='"+path+"'>");
	        msg.append("<br>");*/
	        msg.append("Dear" +"&nbsp;"+ "Parent");
	        msg.append(",");
	        msg.append("<br>");
	        msg.append("<br>");
	        msg.append(msgContent);
	        msg.append("<br>");
	        msg.append("<br>");
	       /* msg.append("Thank you for registering with EAZYSCHOOL - A simple, convenient and efficient Service from EAZYSCHOOL to know about your children performance.");
	        msg.append("<br>");
	        msg.append("<br>");
	        msg.append("Your children has been successfully joined");
	        msg.append("<br>");
	        msg.append("<br>");*/
	        msg.append("Following  are the login details to access EazySchool.");
	        bodyPara[0]=msg.toString();	 
	        tableData[0][0]="Website URL &nbsp;:&nbsp;";
	        tableData[0][1]="http://eazyschool.in";
	        tableData[1][0]="Student Name &nbsp;:&nbsp;";
	        tableData[1][1]=studentName;
	        tableData[2][0]="Class / Section &nbsp;:&nbsp;";
	        tableData[2][1]=sClassAndSection;
	        if(!ObjectFunctions.isNullOrEmpty(Pusername) && !ObjectFunctions.isNullOrEmpty(Ppwd)){
	        	tableData[3][0]="Parent Login ID &nbsp;:&nbsp;";
	  	        tableData[3][1]=Pusername;
	  	        tableData[4][0]="Parent Password &nbsp;:&nbsp;";
	  	        tableData[4][1]=Ppwd;
	        }
	        tableData[5][0]="Student Login ID &nbsp;:&nbsp;";
	        tableData[5][1]=SuserName;
	        tableData[6][0]="Student Password &nbsp;:&nbsp;";
	        tableData[6][1]=Spwd;
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
	        msg = new StringBuffer();
	        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
            getEmailBean().setFromAddress("admin@eazyschool.com");
	        getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
                //saveActiveLogs(getEmailBean());
                setEmailBean(null);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
	}
    // This method is for parent registration for the first time (Edit process of student): on 07/07/2014 ---------rama.
    public void sendMailForParentWithStudentCredentials(Customer customer,String studentName,String sClassAndSection,String Pusername,String Ppwd,String SuserName,String Spwd,String formEmail){
		try{
			
			String[] bodyPara =null;
			String[][] tableData = null;
			 if(!ObjectFunctions.isNullOrEmpty(Pusername) && !ObjectFunctions.isNullOrEmpty(Ppwd)){
				 bodyPara = new String[7];
				 tableData = new String[7][7];
			 }else{
				 bodyPara = new String[5];
				 tableData = new String[5][5];
			 }
	       // log.debug("Customer Student : "+rollNumber);
	        StringBuffer msg = new StringBuffer();
	       /* String path = (SpringContextAware.getHostUrl()+customer.getCustomerLogo().replace("..", "").trim()).toString();
	        msg.append("<img style='height: 64px; width: 120px;' src='"+path+"'>"+"&nbsp;"+ customer.getOrganization().toUpperCase());
	        log.debug("<img src='"+path+"'>");
	        msg.append("<br>");*/
	        if(!ObjectFunctions.isNullOrEmpty(Pusername) && !ObjectFunctions.isNullOrEmpty(Ppwd))
	        	msg.append("Dear" +"&nbsp;"+ "Parent");
	        else
	        	msg.append("Dear" +"&nbsp;"+ "Students");
	        msg.append(",");
	        msg.append("<br>");
	        msg.append("<br>");
	        msg.append("At "+customer.getOrganization().toUpperCase()+" we are incessantly exploring the innovative ideas and latest technologies to enhance educational outcomes. We understand how effective innovation can be to help co-create a culture that promotes a healthy educational experience for both, the parent as well as the student.");
	        msg.append("<br>");
	        msg.append("<br>");
	        if(!ObjectFunctions.isNullOrEmpty(Pusername) && !ObjectFunctions.isNullOrEmpty(Ppwd))
	        	msg.append("We have introduced new School Information Management Software EazySchool which will help us partner with you in the overall success of your child's educational journey with us.");
	        else
	        	msg.append("We have introduced new School Information Management Software EazySchool which will help us partner with you in the overall success of your educational journey with us.");
	        msg.append("<br>"); 
	        msg.append("<br>");
	        if(!ObjectFunctions.isNullOrEmpty(Pusername) && !ObjectFunctions.isNullOrEmpty(Ppwd)){
		        msg.append("EazySchool allow you to keep up-to-date with hygiene records like attendance, fee reports and dues, exam time tables, exam results but also gives you detailed access to the syllabus, assessment reports, and keeps you clued in all school related activities with the newsletter, along with timely alerts on everything you need to know when it comes to school.");
		        msg.append("<br>"); 
		        msg.append("<br>");
		        msg.append("We want you to be an active participant in your child's daily progress in school by checking the 'EazySchool Parent Portal' for daily updates. In order to access EazySchool, every parent will be provided with a User ID and Password. All the important information from the school will be conveyed to you through EazySchool. Given below is the web link to access the Parent Portal:");
	        }else
		        msg.append("In order to access EazySchool, every student will be provided with a User ID and Password. All the important information from the school will be conveyed to you through EazySchool. Given below is the web link to access the Student Portal:");
	        msg.append("<br>");
	        msg.append("<br>");
	       /* msg.append("Thank you for registering with EAZYSCHOOL - A simple, convenient and efficient Service from EAZYSCHOOL to know about your children performance.");
	        msg.append("<br>");
	        msg.append("<br>");*/
	        msg.append("Following  are the login details to access EazySchool.");
	        bodyPara[0]=msg.toString();	 
	        tableData[0][0]="Website URL &nbsp;:&nbsp;";
	        tableData[0][1]="http://eazyschool.in";
	        tableData[1][0]="Student Name &nbsp;:&nbsp;";
	        tableData[1][1]=studentName;
	        tableData[2][0]="Class / Section &nbsp;:&nbsp;";
	        tableData[2][1]=sClassAndSection;
	        if(!ObjectFunctions.isNullOrEmpty(Pusername) && !ObjectFunctions.isNullOrEmpty(Ppwd)){
	        	tableData[3][0]="Parent Login ID &nbsp;:&nbsp;";
	  	        tableData[3][1]=Pusername;
	  	        tableData[4][0]="Parent Password &nbsp;:&nbsp;";
	  	        tableData[4][1]=Ppwd;
	  	        tableData[5][0]="Student Login ID &nbsp;:&nbsp;";
		        tableData[5][1]=SuserName;
		        tableData[6][0]="Student Password &nbsp;:&nbsp;";
		        tableData[6][1]=Spwd;
	        }else{
	        	tableData[3][0]="Student Login ID &nbsp;:&nbsp;";
		        tableData[3][1]=SuserName;
		        tableData[4][0]="Student Password &nbsp;:&nbsp;";
		        tableData[4][1]=Spwd;
	        }
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
	        msg = new StringBuffer();
	        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
	        bodyPara[4]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
            getEmailBean().setFromAddress(formEmail);
			getMailSender().setContactEmail(this.getContactEmail());
			getMailSender().setContactPassword(this.getContactPasword());
	        getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
                //saveActiveLogs(getEmailBean());
                setEmailBean(null);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
	}
    public void sendMailForAllParentsForFeeAmountCrossDueDate(ViewStudentClassDetails student, SchoolTerms feeType,long dueAmount,String custOrganizationName,long totalUnpaidPreviewAmount,String checkDueVal,String fromEmail) {
		try {
		//	mailSent = true;
			String[] bodyPara = new String[4];
			if (!StringFunctions.isNullOrEmpty(student.getParentEmail())) {
				long totalamount=totalUnpaidPreviewAmount+dueAmount;
				StringBuffer msg = new StringBuffer();
				msg.append("Dear" + "&nbsp;" + student.getFatherName()+",");
				msg.append("<br>");
				msg.append(feeType.getTermName());
				msg.append(" fee is due for ");
				msg.append(feeType.getDueDateStr());
				bodyPara[0] = msg.toString();
				msg = new StringBuffer();
				String msgContent;
				msgContent = "This is to inform that you are exceed the Due date of "+feeType.getTermName()+" payment for your children "+student.getPersonFullName()+"("+student.getClassAndSection()+"). We request you to please make the payment due as soon as possible.";
				msg.append(msgContent);
				msg.append("<br>");
				bodyPara[1] = msg.toString();
				msg = new StringBuffer();
				msg.append("<b> Fee Details:- </b>");
				msg.append("<br>");
				msg.append("<table border='1' style='margin-bottom:0; '><thead><tr><td>Due Amount</td>");
				msg.append("<td>" + dueAmount + "</td></tr>");
				if(totalUnpaidPreviewAmount>0){
					msg.append("<tr><td>Previous Due Amount</td>");
					msg.append("<td>" + totalUnpaidPreviewAmount + "</td></tr>");
				}
				msg.append("<tr><td>Total Amount</td>");
				msg.append("<td>" + totalamount + "</td></tr>");
				msg.append("<tr><td>Due Date</td>");
				if("D1".equalsIgnoreCase(checkDueVal)){
					msg.append("<td>" + feeType.getDueDate1Str() + "</td></tr>");
				}else{
					msg.append("<td>" + feeType.getDueDate2Str() + "</td></tr>");
				}
				msg.append("</table>");
				msg.append("<br />");
				msg.append("Please ignore this communication if payment is already remitted.");
				msg.append("<br />");
				msg.append("<br />");
				
				msg.append("Thanks & Regrads");
				msg.append("<br />");
				msg.append(custOrganizationName+" Finance department.");
				bodyPara[2] = msg.toString();
				msg = new StringBuffer();
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
				msg.append("<br />");
				bodyPara[3] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getEmailBean().setFromAddress(fromEmail);
				getMailSender().setContactEmail(this.getContactEmail());
				getMailSender().setContactPassword(this.getContactPasword());
				 getMailSender().sendHtmlMail(getEmailBean());
					log.debug("Hurray success....+ email sent successfully");
					// saveActiveLogs(getEmailBean());
					//return getEmailBean();
			}
			setEmailBean(null);
		} catch (Exception ex) {
			//mailSent = false;
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
		student = null;
		feeType=null;
		//return null;
	}
    public void sendMailForAllParentsForBirthdayDateAlert(String parentEmail,String fullStudentName,String custOrganizationName,String Salutation,String fromEmail,Customer customer) {
		try {
			String[] bodyPara = new String[4];
			if (!StringFunctions.isNullOrEmpty(parentEmail)) {
				StringBuffer msg = new StringBuffer();
				if("Staff".equalsIgnoreCase(Salutation))
					msg.append("Dear Sir/Madam, ");
				else
					msg.append("Dear Parent, ");
				if(customer.getId()==2){
					if("Staff".equalsIgnoreCase(Salutation)){
						msg.append(" Wishes a Very Happy Birthday to you ");
					}else{
						msg.append(" this is to wish ");
						msg.append(fullStudentName);
						msg.append(" a happy birthday ");
					}
				}else{
					msg.append(custOrganizationName);
					if("Staff".equalsIgnoreCase(Salutation))
						msg.append(" Wishes a Very Happy Birthday to you ");
					else
						msg.append(" Wishes a Very Happy Birthday to your child ");
					msg.append(fullStudentName);
				}
				msg.append(",Thank You Principal.");
				msg.append("<br />");
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
				msg.append("<br />");
				bodyPara[3] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getEmailBean().setFromAddress(fromEmail);
				getMailSender().setContactEmail(this.getContactEmail());
				getMailSender().setContactPassword(this.getContactPasword());
				getMailSender().sendHtmlMail(getEmailBean());
				log.debug("Hurray success....+ email sent successfully");
			}
			setEmailBean(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
	}
    public void sendMailMontlyStudentAttendanceForParent(String studentName,String monthName,String totalWorkingDays,String totalPresentDays,String[] parentEmail,String fatherName,String classNameAndSection,String fromEmail) {
    	try{
    		long absentDays= Long.valueOf(totalWorkingDays) - Long.valueOf(totalPresentDays);
            String[] bodyPara = new String[4];
            String[][] tableData = new String[5][5];
            StringBuffer msg = new StringBuffer();
            msg.append("Dear "+fatherName).append(",");
            msg.append("<br />");
            msg.append( " The following is your child "+monthName+" attendance Report");
            msg.append("<br />");
            bodyPara[1]=msg.toString();
            tableData[0][0]="Student Name&nbsp;:&nbsp;";
            tableData[0][1]=studentName;
            tableData[1][0]="Class&Section&nbsp;:&nbsp;";
            tableData[1][1]=classNameAndSection;
            tableData[2][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Working Days&nbsp;:&nbsp;";
            tableData[2][1]=totalWorkingDays;
            tableData[3][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Presented Days&nbsp;:&nbsp;";
            tableData[3][1]= totalPresentDays;
            tableData[4][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Absent Days&nbsp;:&nbsp;";
            tableData[4][1]= absentDays+"";
            msg.append("<br /><strong>**NOTE:</strong>Please contact to school Administration for more information.");
    	    bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "180");
            msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
            bodyPara[3]=msg.toString();
            getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
            getEmailBean().setFromAddress(fromEmail);
            getMailSender().setContactEmail(this.getContactEmail());
            getMailSender().setContactPassword(this.getContactPasword());
            getMailSender().sendHtmlMail(getEmailBean());
                log.debug("Hurray success....+ email sent successfully");
           // return getEmailBean();
            setEmailBean(null);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    		log.debug(ex.getMessage());
    	}
    	//return null;
    } 
    
    public int sendMailForAllAttachementFilesToParentAndStudent(Object[] viewStudentPersonAccountDetails,String custEmail,String custOrganization,ClassAssignment classAssignment,String[] fileUploadStr,Customer customer) {
    	int responseCode;
		try{
			
	      String[] bodyPara = new String[4];
	        String[][] tableData = new String[6][6];
	        StringBuffer msg = new StringBuffer();
	        String mainContent = "School assingments of your children(s)."; 
	        msg.append("Dear Parent,");
            msg.append("<br />");
	        String mailContent = "The following assignment has been given to your children. Below are the details of the assignment.";
	    	mailContent=mailContent.replace("<br>", "\n");
	    	
	    	
	    	mailContent=mailContent.replaceAll("<parent name>",ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails[0])? "Parent": viewStudentPersonAccountDetails[0].toString());
	    	mailContent=mailContent.replaceAll("<customer specified name>",custEmail);
	    	bodyPara[1]=mailContent;
	        tableData[0][0]="Student Name&nbsp;:&nbsp;";
	        tableData[0][1]=viewStudentPersonAccountDetails[1].toString();
	        tableData[1][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Student RollNumber&nbsp;:&nbsp;";
	        tableData[1][1]=viewStudentPersonAccountDetails[2].toString();
	        tableData[2][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Class&nbsp;:&nbsp;";
	       if(StringFunctions.isNotNullOrEmpty(viewStudentPersonAccountDetails[5].toString())){
	    	   tableData[2][1]=viewStudentPersonAccountDetails[4].toString()+"-"+viewStudentPersonAccountDetails[5].toString() ;
	       }else{
	    	   tableData[2][1]=viewStudentPersonAccountDetails[4].toString();
	       }
	       if(!ObjectFunctions.isNullOrEmpty(classAssignment)){
		       tableData[3][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Subject Name&nbsp;:&nbsp;";
		       if(StringFunctions.isNullOrEmpty(classAssignment.getSubjectName()))
		    	   classAssignment.setSubjectName("Assignment");
	           tableData[3][1]= classAssignment.getSubjectName();
	           tableData[4][0]="Assignment Submission Date&nbsp;:&nbsp;";
	           tableData[4][1]= classAssignment.getAssignmentDateFormat();
	           if(!ObjectFunctions.isNullOrEmpty(classAssignment.getDescription())){
	        	   tableData[5][0]="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Description&nbsp;:&nbsp;";
	        	   tableData[5][1]= classAssignment.getDescription();
	           }else{
	        	   tableData[5][0]= "";
	        	   tableData[5][1]= "";
		       }
	       }
	        msg = new StringBuffer(); 
			msg.append("<br>");
			bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "200");
	        msg.append("<br /><strong>**NOTE:</strong> Please make sure your children has to complete the assignment on time. This is an automated email from "+ custOrganization +" Please DO NOT REPLY to this email.");
	        bodyPara[3]=msg.toString();
	        getEmailBean().setSubject(mainContent);
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
	        getEmailBean().setFromAddress(custEmail);
	        getEmailBean().setAttachmentFiles(fileUploadStr);
	        getMailSender().setContactEmail(customer.getContactEmail());
			getMailSender().setContactPassword(customer.getContactPassword());
	        //getMailSender().sendHtmlMail(getEmailBean());
	        responseCode = getMailSender().sendHtmlMail(getEmailBean());
			setEmailBean(null);
			 viewStudentPersonAccountDetails=null;
			if(responseCode == 0 && !ObjectFunctions.isNullOrEmpty(fileUploadStr))
			{
				/*for(String filePath : fileUploadStr)
				{
					try {
						File file = new File(filePath);
						file.delete();
						file = null;
					} catch (Exception e) {
						log.debug("Temp File not removed");
					}
				}*/
				log.debug("Hurray success....+ email sent successfully");
				return responseCode;
			}else 
				return responseCode;
		}
		catch(Exception ex){
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
			responseCode = 2;
		}
		return responseCode;
	}
    
    public boolean sendFeeReceiptToParents(Student student,Person person, String parentEmail,List feesPaymentList, long custId, long userId)
	 {
		    try {
		    	String[] emailAddresses = new String[1];
				String[] bodyPara = new String[5];
				emailAddresses[0] = parentEmail;
				StringBuffer msg = new StringBuffer();
				msg.append("<b>Payment Fee Receipt</b>");
				msg.append("<br>");
				msg.append("<br>");
				msg.append("Dear" + "&nbsp;" +person.getFatherName()+",");
				msg = new StringBuffer(); 
				bodyPara[1] = msg.toString();
				msg.append("Thank You for Paying Fee On ");
				msg.append("<br>");
				msg.append("<br>");
				msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Fee Type</th><th class='head'>Amount</th><th class='head'>Paid Date</th></tr></thead>");
				
				// JSR TO-DO: Why do we need this loop
				/*if (!ObjectFunctions.isNullOrEmpty(feesPaymentList)) {
					for (Iterator classFeeIterator = feesPaymentList.iterator(); classFeeIterator.hasNext();) {
						StudentFeePaidDetails feeDetails = (StudentFeePaidDetails) classFeeIterator.next();
						if (!ObjectFunctions.isNullOrEmpty(feeDetails)) {
							//msg.append("<tr><td>"+	feeDetails.getFeeTypeStr()+"</td>");
							//msg.append("<td>"+	feeDetails.getFeeAmount()+"</td>");
							//msg.append("<td>"+	feeDetails.getCreatedDateStr()+"</td></tr>");
						}
					}
				}*/
				msg.append("</table>");
				msg.append("");
				msg.append("<br>");
				msg.append("<br>");
				bodyPara[2] = msg.toString();
				msg = new StringBuffer();
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
				msg.append("<br />");
				bodyPara[3] = msg.toString();
		    	
				
				EmailBean emailBean = new EmailBean();
				
				emailBean.setBody(StringFunctions.formatEmailBody(bodyPara));
				bodyPara=null;
				msg=null;
				emailBean.setToAddress(emailAddresses);
				emailBean.setSubject("Payment Fee Receipt");
				getMailSender().sendHtmlMail(emailBean);
				//return this.sendEmail(emailBean, custId, userId);
				return true;
				
			} catch (Exception ex) {
				ex.printStackTrace();	
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
				return false;
			}
	 }

	 public boolean sendEmail(EmailBean emailBean, long custId, long userId)
	 {
		 if (log.isDebugEnabled()) {
				log.debug("Entering UniversalManager 'sendEmail' method");
		 }
		 try
		 {
			Customer customer = (Customer)getAdminManager().get(Customer.class, custId);
			emailBean.setFromAddress("easySchool@urt.com");
			emailBean.setCustomerId(customer.getId());
	        emailBean.setSender(customer.getSender());
	        if(userId == 0)
	        	emailBean.setFromWhom("Admin");
	        else
	        {
	        	User user = (User)getAdminManager().get(User.class, userId);
				emailBean.setFromWhom(user.getUserRoleDescription());
	        	user=null;
	        }
			customer=null;
			
			URTHttpClient urtHttpClient = new URTHttpClient();
			if (urtHttpClient.postNameValuePair(emailBean)) {
				log.debug("Yup success....+ email sent successfully");
				this.saveActiveLogs(emailBean, custId, userId);
			}
			emailBean=null;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
		 }
		 return true;
	 }
	 public void saveActiveLogs(EmailBean emailBean, long custId, long userId){
			if (log.isDebugEnabled()) {
				log.debug("Entering UniversalManager 'saveActiveLogs' method");
			}
		 try{
			 
			 if (!ObjectFunctions.isNullOrEmpty(emailBean)){
			    Messages message = new Messages();
			    MessageActivity msg = new MessageActivity();
			    User user = (User) getAdminManager().get(User.class, userId);
				if(!StringFunctions.isNullOrEmpty(user.getPerson().getPersonName())){
				    message.setCreatedById(user.getId());
				    message.setLastUpdatedById(user.getId());
				    message.setLastAccessDate(new Date());
				}
				Customer customer = (Customer)getAdminManager().get(Customer.class, custId);
				if(!ObjectFunctions.isNullOrEmpty(customer)){
				     message.setCustomer(customer);
				     msg.setCustomerId(customer.getId());
				     
				}
				//message.setCustomer(emailBean.getCustomerId());
				AcademicYear academicYear = getAdminManager().getCurrentAcademicYear("Y", custId);
				message.setAcademicYear(academicYear);
	            message.setMessageType("e-mail");
	            message.setStatus("E");
			    message.setPurposeType(emailBean.getSubject());
			    message.setTitle(emailBean.getSubject());
			    message.setSenderName(user.getUserRoleDescription());
			    message = (Messages) getAdminManager().save(message);
				if(!StringFunctions.isNullOrEmpty(emailBean.getFromWhom())){
					msg.setFromAddress(emailBean.getFromWhom());
				}else{
					msg.setFromAddress("System");
				}
				msg.setType("EMAIL");
				msg.setPurpose(emailBean.getSubject());
				msg.setMessageId(message.getId());
			   
				if(!ObjectFunctions.isNullOrEmpty(emailBean.getToAddress())){
		      	   String toAddressArray[]=emailBean.getToAddress();
		      	   List<String> toAddressList = Arrays.asList(toAddressArray); 
		      	   for (String toAddr : toAddressList)  
			           {  
			      		 if(!StringFunctions.isNullOrEmpty(toAddr)){
			      			 log.debug("---toAddr----"+toAddr);
			      			 msg.setDelivered(true);
			      			 msg.setToAddress(toAddr);
			      			getAdminManager().save(msg);
			      		 }
			       }
			    }
				if(!ObjectFunctions.isNullOrEmpty(emailBean.getCcAddress())){
			      	   String toAddressArray[]=emailBean.getCcAddress();
			      	   List<String> ccAddressList = Arrays.asList(toAddressArray); 
			      	   for (String ccAddr : ccAddressList)  
				           {  
				      		 if(!StringFunctions.isNullOrEmpty(ccAddr)){
				      			 log.debug("---ccAddress----"+ccAddr);
				      			 msg.setToAddress(ccAddr);
				      			 msg.setDelivered(true);
				      			getAdminManager().save(msg);
				      		 }
				       }
				}
				if(!ObjectFunctions.isNullOrEmpty(emailBean.getBccAddress())){
			      	   String toAddressArray[]=emailBean.getBccAddress();
			      	   List<String> bccAddressList = Arrays.asList(toAddressArray); 
			      	   for (String bccAddr : bccAddressList)  
				           {  
				      		 if(!StringFunctions.isNullOrEmpty(bccAddr)){
				      			 log.debug("---bccAddress----"+bccAddr);
				      			 msg.setToAddress(bccAddr);
				      			 msg.setDelivered(true);
				      			getAdminManager().save(msg);
				      		 }
				       }
				}
			emailBean = null;
			msg = null;
			academicYear= null;
			customer=null;
			user=null;
			message=null;
		  }
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 
	 }
	 
	 public void sendEmailForCustomerWeeKly(Customer customer)
	 {
			try{
				
				String[] emailAddresses = new String[4];
		        String[] bodyPara = new String[4];
		        String[][] tableData = new String[7][2];
		        log.debug("Customer Name : " + customer.getCustEmail());
		        emailAddresses[0]=customer.getCustEmail(); 
		      //  resourcesArgs[0]="EazySchool Customer Details";
		        StringBuffer msg = new StringBuffer(); 
		        msg = new StringBuffer();
		        msg.append("<b><u>Dear privileged</u> "+customer.getFirstName()+" </b>");  
		        msg.append(",");
		        msg.append("<br>");
		        msg.append("<br>");
		        msg.append("It is our great pleasure to reach you.");
		        msg.append("<br>");
		        msg.append("<br>");
		        msg.append("We hope you have been using EazySchool system from past "+DateFunctions.daysBetween(customer.getCreatedDate(), new Date())+1 +" and we hope our application is fulfilling all your needs.");
		        msg.append("<br>");
		        msg.append("<br>");
		        msg.append("Please let us know if you have experienced any issue while using our system and how quickly our technical team resolved the issue.");
		        msg.append("<br>");
		        msg.append("<br>");
		        msg.append("Please send your feedback to admin@eazyschool.com.  This would help us to provide better service.");
		        msg.append("<br>");
		        msg.append("<br>");

		        bodyPara[0]=msg.toString();
		        tableData[0][0]="Customer Name&nbsp;:&nbsp;";
		        tableData[0][1]=customer.getFirstName();
		        tableData[1][0]="Preferred URL &nbsp;:&nbsp;";
		        tableData[1][1]=customer.getWebSiteUrl();
		        tableData[2][0]="Customer Email&nbsp;:&nbsp;";
		        tableData[2][1]=customer.getCustEmail(); 
		        tableData[3][0]="SubscriptionType&nbsp;:&nbsp;";
		        if("Y".equalsIgnoreCase(customer.getSubscriptionType())){
		        	tableData[3][1]="Yearly";
		        }
		        else{
		        	tableData[3][1]="Monthly";
		        }
		        tableData[4][0]="Address&nbsp;:&nbsp;";
		        tableData[4][1]=customer.getCustomerFormattedAddress();
		        tableData[5][0]="Phone Number&nbsp;:&nbsp;";
		        tableData[5][1]=customer.getMobileNumber();
		       
		        
		        bodyPara[1]="~"+StringFunctions.formatDataWithTable(tableData,"170"); 
		        EmailBean emailBean = new EmailBean();
		        emailBean.setBody(StringFunctions.formatEmailBody(bodyPara));
		        emailBean.setToAddress(emailAddresses);
		        emailAddresses=null;
		        tableData=null;
		        msg=null;
		        bodyPara=null;
		        emailBean.setFromAddress("admin@eazyschool.com");
		        emailBean.setSubject("EazySchool Customer Details"); 
		        getMailSender().sendHtmlMail(emailBean);
		        emailBean=null;
		            
		  	}
		  	catch(Exception ex){
		  		ex.printStackTrace();
		  		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		  		log.debug(ex.getMessage());
		  	}
		}
	 
	 public void sendEmailForCustomerMonthly(Customer customer) {
			try {

			    String[] emailAddresses = new String[4];
			    String[] bodyPara = new String[4];
			    String[] resourcesArgs = new String[1];
			    String[][] tableData = new String[7][2];
			    log.debug("Customer Name : " + customer.getCustEmail());
			    emailAddresses[0] = customer.getCustEmail();
			    // resourcesArgs[0]="EazySchool Customer Details";
			    StringBuffer msg = new StringBuffer();
			    msg = new StringBuffer();
			    msg.append("<b><u>Dear privileged</u> " + customer.getFirstName() + " </b>");
			    msg.append(",");
			    msg.append("<br>");
			    msg.append("<br>");
			    msg.append("It is our great pleasure to reach you.");
			    msg.append("<br>");
			    msg.append("<br>");
			    msg.append("We hope you have been using EazySchool system from past "
				    + DateFunctions.daysBetween(customer.getCreatedDate(), new Date()) + 1
				    + " and we hope our application is fulfilling all your needs.");
			    msg.append("<br>");
			    msg.append("<br>");
			    msg.append("Please let us know if you have experienced any issue while using our system and how quickly our technical team resolved the issue.");
			    msg.append("<br>");
			    msg.append("<br>");
			    msg.append("Please send your feedback to admin@eazyschool.com.  This would help us to provide better service.");
			    msg.append("<br>");
			    msg.append("<br>");

			    bodyPara[0] = msg.toString();
			    tableData[0][0] = "Customer Name&nbsp;:&nbsp;";
			    tableData[0][1] = customer.getFirstName();
			    tableData[1][0] = "Preferred URL &nbsp;:&nbsp;";
			    tableData[1][1] = customer.getWebSiteUrl();
			    tableData[2][0] = "Customer Email&nbsp;:&nbsp;";
			    tableData[2][1] = customer.getCustEmail();
			    tableData[3][0] = "SubscriptionType&nbsp;:&nbsp;";
			    if ("Y".equalsIgnoreCase(customer.getSubscriptionType())) {
				tableData[3][1] = "Yearly";
			    } else {
				tableData[3][1] = "Monthly";
			    }
			    tableData[4][0] = "Address&nbsp;:&nbsp;";
			    tableData[4][1] = customer.getCustomerFormattedAddress();
			    tableData[5][0] = "Phone Number&nbsp;:&nbsp;";
			    tableData[5][1] = customer.getMobileNumber();

			    bodyPara[1] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
			    EmailBean emailBean = new EmailBean();
			    emailBean.setBody(StringFunctions.formatEmailBody(bodyPara));
			    emailBean.setToAddress(emailAddresses);
			    emailAddresses=null;
		        tableData=null;
		        msg=null;
		        bodyPara=null;
			    emailBean.setFromAddress("admin@eazyschool.com");
			    emailBean.setSubject("EazySchool Customer Details");
			    getMailSender().sendHtmlMail(emailBean);
			    emailBean=null;
			   
			} catch (Exception ex) {
			    ex.printStackTrace();
			    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			    log.debug(ex.getMessage());
			}
	 }
	 
	 public boolean sendMailToParentForRegistration(String username,String pwd,String firstname,String schoolname,String fromEmail){
			try{
				 String[] bodyPara = new String[4];
			        String[][] tableData = new String[2][2];
			       // log.debug("Customer Student : "+rollNumber);
			        StringBuffer msg = new StringBuffer();
			        msg.append("Dear" +"&nbsp;"+ firstname);
			        msg.append(",");
			        msg.append("<br>");
			        msg.append("<br>");
			        msg.append("Welcome to Eazy School,");
			        msg.append("<br>");
			        msg.append("<br>");
			        msg.append("Your Login information to access the eazyschool.");
			        tableData[0][0]="User Name: ";
		        	tableData[0][1]=username;
		        	tableData[1][0]="Password: ";
				    tableData[1][1]=pwd;
			        bodyPara[0]=msg.toString();	
			        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
			        msg = new StringBuffer();
				    msg.append("Please keep this information for your records .");
				    msg.append("<br>");
				    msg.append("<br>");
				    msg.append("Thank You,");
				    msg.append("<br>");
				    msg.append("<br>");
				    msg.append(schoolname);
				    msg.append("<br>");
				    msg.append("<br>");
				    msg.append("Note: This is a System generated Mail therefore please don't reply to this mail.");
			        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
			        bodyPara[3]=msg.toString();
			        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
		            getEmailBean().setFromAddress(fromEmail);
		            getMailSender().setContactEmail(this.getContactEmail());
		            getMailSender().setContactPassword(this.getContactPasword());
			        getMailSender().sendHtmlMail(getEmailBean());
		               log.debug("Hurray success....+ email sent successfully");
		                //saveActiveLogs(getEmailBean());
		              setEmailBean(null);
		              return true;
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    		log.debug(ex.getMessage());
	    	}
	    	return false;
	}
	 
	 public static int SendAttachmentFilesToParentAndStudent(List<Object> studentsContactDetails, Customer customer, ClassAssignment classAssignment,String[] fileUploadStr,String custEmail)
	 {
		 int responseCode=0;
		 try {
			 	Object[] studentInfo = null;
				String[] emailAddress = null;
				MailUtil mailUtil= null;
				if(ObjectFunctions.isNotNullOrEmpty(studentsContactDetails)){
					for(Object stud : studentsContactDetails){
						studentInfo = (Object[])stud;
						if(!ObjectFunctions.isNullOrEmpty(studentInfo[1]) && !ObjectFunctions.isNullOrEmpty(studentInfo[2]) 
								&& !ObjectFunctions.isNullOrEmpty(studentInfo[3]) && !ObjectFunctions.isNullOrEmpty(studentInfo[4]) && !ObjectFunctions.isNullOrEmpty(studentInfo[5])){
							emailAddress = new String[1];
							emailAddress[0] = studentInfo[3].toString();
							log.debug("Email Id : "+emailAddress[0]);
							mailUtil=new MailUtil(emailAddress,"Welcome to EazySchool Management",customer.getId(),customer.getSender(),"Administrator",custEmail);
							responseCode=mailUtil.sendMailForAllAttachementFilesToParentAndStudent(studentInfo,custEmail,customer.getOrganization(),classAssignment,fileUploadStr,customer);
							
							emailAddress = null;
							//classAssignment =null;
						}
						mailUtil=null;
						stud = null;
					}
					if(!ObjectFunctions.isNullOrEmpty(fileUploadStr)){
						for(String filePath : fileUploadStr)
						{
							try {
								File file = new File(filePath);
								file.delete();
								file = null;
							} catch (Exception e) {
								log.debug("Temp File not removed");
							}
						}
					}
					
					studentsContactDetails = null;
				}
		 }catch (Exception ex) {
			 ex.printStackTrace();
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		 }
		 return responseCode;
	}
	 
	 public boolean sendEmailForIssuedBooks(User user) {
			try {
			    String[] bodyPara = new String[4];
			    StringBuffer msg = new StringBuffer();
			    if(user.isSchoolStudent()){
			    	msg.append("Dear" + "&nbsp; Student");
			    }else{
			    	msg.append("Dear" + "&nbsp; Sir/Madam");
			    }			    
			    msg.append(",");
			    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
			    msg.append(getEmailBean().getBody());
			    msg.append("<br />");
			    bodyPara[0] = msg.toString();
			    getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			    getEmailBean().setBlind(true);
			    getMailSender().sendHtmlMail(getEmailBean());
			    log.debug("Hurray success....+ email sent successfully");
			    // saveActiveLogs(getEmailBean());
				setEmailBean(null);
			} catch (Exception ex) {
			    ex.printStackTrace();
			    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			    log.debug(ex.getMessage());

			}
			return false;
	}
	 public boolean sendEmailToFeeReceiptToParent(String fromEmail) 
	 {
		 boolean isSendEmail=false;
		try {
    	    StringBuffer msg = new StringBuffer();
    	    msg.append("Dear Parent, ");
    	    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
    	    msg.append(getEmailBean().getBody());
    	    msg.append("<br />");
    	    getEmailBean().setBody(msg.toString());
    	    getEmailBean().setFromAddress(fromEmail);
    	    msg=null;
    	    getMailSender().setContactEmail(this.getContactEmail());
    	    getMailSender().setContactPassword(this.getContactPasword());
    	    int value = getMailSender().sendHtmlMail(getEmailBean());
    	    log.debug(value);
    	    if(value == 0)
    	    	isSendEmail = true;
    	    else
    	    	isSendEmail = false;
    	    log.debug("Hurray success....+ email sent successfully");
	    	    // saveActiveLogs(getEmailBean());
	    		setEmailBean(null);
    		} catch (Exception ex) {
    	    ex.printStackTrace();
    	    log.debug(ex.getMessage());
    		}
    	return isSendEmail;
    }
	 
	 public boolean sendEmailToSupportTeam(String fromEmail) 
	 {
		 boolean isSendEmail=false;
		try {
    	    StringBuffer msg = new StringBuffer();
    	    msg.append("Hi, ");
    	    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
    	    msg.append(getEmailBean().getBody());
    	    msg.append("<br />");
    	    getEmailBean().setBody(msg.toString());
    	    getEmailBean().setFromAddress(fromEmail);
    	    msg=null;
    	    getMailSender().setContactEmail(this.getContactEmail());
    	    getMailSender().setContactPassword(this.getContactPasword());
    	    int value = getMailSender().sendHtmlMail(getEmailBean());
    	    log.debug(value);
    	    if(value == 0)
    	    	isSendEmail = true;
    	    else
    	    	isSendEmail = false;
    	    log.debug("Hurray success....+ email sent successfully");
	    	    // saveActiveLogs(getEmailBean());
	    		setEmailBean(null);
    		} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	    log.debug(ex.getMessage());
    		}
    	return isSendEmail;
    }
	 
	 public void sendMailForStudPwdChange(String password,User username,String fromEmail) {
			try {
				String[] bodyPara = new String[4];
				String[][] tableData = new String[2][2];
				//log.debug("User Student : " + user.getUsername());
				StringBuffer msg = new StringBuffer();
				msg.append("Dear parent this e-mail is to confirm that your child "+username.getPerson().getFullPersonName()+" password has been updated. To access Eazyschool portal login, sign in at  <a href='"+ getHostUrl()+ "' target='_new'>HYNIVA Eazyschool Application Management Software</a> with this e-mail address and password.");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer();
				bodyPara[1] = msg.toString();
				tableData[0][0] = "User name &nbsp;:&nbsp;";
				tableData[0][1] = username.getUsername();
				tableData[1][0] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password&nbsp;:&nbsp;";
				tableData[1][1] = password;
		        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "115");
				msg = new StringBuffer();
				msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
				bodyPara[3] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getEmailBean().setFromAddress(fromEmail);
				getMailSender().setContactEmail(this.getContactEmail());
				getMailSender().setContactPassword(this.getContactPasword());
				getMailSender().sendHtmlMail(getEmailBean());
				setEmailBean(null);
				tableData=null;
				msg=null;
				bodyPara=null;
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}
	 public void sendMailForSupportUrt(String errorInfo,String custEmail,String ogrName) {
			try {
				String[] bodyPara = new String[4];
				String[][] tableData = null;
				if(StringFunctions.isNotNullOrEmpty(custEmail) && StringFunctions.isNotNullOrEmpty(ogrName)){
				 tableData = new String[3][3];
				}else{
					 tableData = new String[1][2];
				}
				//log.debug("User Student : " + user.getUsername());
				StringBuffer msg = new StringBuffer();
				msg.append("Dear Support Team,");
				msg.append("<br>");
				msg.append("<br>");
				msg.append("&nbsp;&nbsp;&nbsp;&nbsp;Please check and fix the below error for the school support.");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer();
				bodyPara[1] = msg.toString();
				tableData[0][0] = "Error Info &nbsp;:&nbsp;";
				tableData[0][1] = errorInfo;
				if(StringFunctions.isNotNullOrEmpty(custEmail) && StringFunctions.isNotNullOrEmpty(ogrName)){
					tableData[1][0] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Customer&nbsp;:&nbsp;";
					tableData[1][1] = ogrName;
					tableData[2][0] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contact Email &nbsp;:&nbsp;";
					tableData[2][1] = custEmail;
				}
		        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "180");
				msg = new StringBuffer();
				msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
				bodyPara[3] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getMailSender().setContactEmail(this.getContactEmail());
				getMailSender().setContactPassword(this.getContactPasword());
				getMailSender().sendHtmlMail(getEmailBean());
				setEmailBean(null);
				tableData=null;
				msg=null;
				bodyPara=null;
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}
	 
	 public boolean sendEmailToSchoolManagerFromSecretary(User user) {
	    	try {
		    	    StringBuffer msg = new StringBuffer();
		    	    msg.append("Dear" + "&nbsp;" + user.getFullPersonName());
		    	    msg.append(",");
		    	    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		    	    msg.append(getEmailBean().getBody());
		    	    msg.append("<br />");
		    	    getEmailBean().setBody(msg.toString());
		    	    msg=null;
		    	    getEmailBean().setFromAddress("admin@eazyschool.com");
		    	    getMailSender().sendHtmlMail(getEmailBean());
		    	    log.debug("Hurray success....+ email sent successfully");
		    	    // saveActiveLogs(getEmailBean());
		    		setEmailBean(null);
		    		user=null;
	    		} catch (Exception ex) {
	    	    ex.printStackTrace();
	    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    	    log.debug(ex.getMessage());

	    		}
	    	return false;
	    }
	 public boolean sendEmailAndSmsForSupportUrt()
	 {
		try {
    	    StringBuffer msg = new StringBuffer();
    	    msg.append("Dear Supporting Team, ");
    	    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
    	    msg.append(getEmailBean().getBody());
    	    msg.append("<br />");
    	    getEmailBean().setBody(msg.toString());
    	    msg=null;
    	    getMailSender().sendHtmlMail(getEmailBean());
    	    log.debug("Hurray success....+ email sent successfully");
	    	    // saveActiveLogs(getEmailBean());
	    		setEmailBean(null);
    		} catch (Exception ex) {
    	    ex.printStackTrace();
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	    log.debug(ex.getMessage());
    		}
    	return false;
    }
	 
	 public void sendMailAllottedSmsCountForSupportTeam(List<Customer> customers,String fromEmail) {
		try {
				String[] bodyPara = new String[4];
				StringBuffer msg = new StringBuffer();
				msg.append("Dear Supporting Team");
				msg.append(",");
				msg.append("<br>");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer(); 
				String msgContent;
				msgContent = "The following eazyschool customers are about to exceed the allotted SMS. Please talk to customer and help them to recharge there SMS.";
				msg.append(msgContent);
				msg.append("<br>");
				bodyPara[1] = msg.toString();
				msg = new StringBuffer();
				msg.append("<b> SMS Summary :- </b>");
				msg.append("<br>");
				msg.append("<br>");
				msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>C.Id</th><th class='head'>Organization Name</th><th class='head'>Username</th><th class='head'>Allotted</th><th class='head'>Used</th><th class='head'>Exceeded</th></tr></thead>");
		         if (!ObjectFunctions.isNullOrEmpty(customers)) {
		        	 for(Customer customer:customers){
		        		 if (!ObjectFunctions.isNullOrEmpty(customer)) {
								msg.append("<tr><td>"+	customer.getId()+"</td>");
								msg.append("<td>"+	customer.getOrganization()+"</td>");
								msg.append("<td>"+	customer.getCustEmail()+"</td>");
								msg.append("<td>"+	customer.getAllowedTotalSms()+"</td>");
								msg.append("<td>"+	customer.getOrgId()+"</td>");
								msg.append("<td>"+	Math.abs(customer.getOrgnizationTypeId())+"</td></tr>");
						 }
		        		 customer=null;
		        	 }
		         }
		        msg.append("</table>");
				msg.append("<br />");
				msg.append("<br />");
				bodyPara[2] = msg.toString();
				getEmailBean().setFromAddress(fromEmail);
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getMailSender().sendHtmlMail(getEmailBean());
				setEmailBean(null);
				msg = null;
				bodyPara = null;
	            msgContent = null;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
	}
	 public void sendMailForSupportTeam(String hostAddress) {
		try {
			String[] bodyPara = new String[4];
			String[][] tableData = new String[1][2];
			//log.debug("User Student : " + user.getUsername());
			StringBuffer msg = new StringBuffer();
			msg.append("Dear Support Team,Please check with this Ip Address "+hostAddress+" some one access our code.");
			bodyPara[0] = msg.toString();
			msg = new StringBuffer();
			bodyPara[1] = msg.toString();
			tableData[0][0] = "Team Alert Info &nbsp;:&nbsp;";
			tableData[0][1] = "Our code has been accessing by unauthorized server. Please check with this Ip Address "+hostAddress+" along with our access code Hyniva@India@143 .";
	        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "115");
			msg = new StringBuffer();
			msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
			bodyPara[3] = msg.toString();
			getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			getMailSender().sendHtmlMail(getEmailBean());
			setEmailBean(null);
			tableData=null;
			msg=null;
			bodyPara=null;
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
	}
	 public boolean sendEmailToSupportingTeam(String fromEmail) {
		try {
			    String[] bodyPara = new String[4];
			    StringBuffer msg = new StringBuffer();
			    msg.append(getEmailBean().getBody());
			    msg.append("<br />");
			    bodyPara[0] = msg.toString();
			    getEmailBean().setFromAddress(fromEmail);
			    getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			    getMailSender().sendHtmlMail(getEmailBean());
			    log.debug("Hurray success....+ email sent successfully");
				setEmailBean(null);
			} catch (Exception ex) {
			    ex.printStackTrace();
			    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			    log.debug(ex.getMessage());
			}
			return false;
	    }

	public int sendVarificationForValidEmail() {
		int responseCode;
		try {
			String[] bodyPara = new String[4];
			StringBuffer msg = new StringBuffer();
			msg.append(getEmailBean().getBody());
			msg.append("<br />");
			bodyPara[0] = msg.toString();
			getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			getMailSender().setContactEmail(this.getContactEmail());
			getMailSender().setContactPassword(this.getContactPasword());
			responseCode = getMailSender().sendHtmlMail(getEmailBean());
			setEmailBean(null);
			if(responseCode == 0){
				log.debug("Hurray success....+ email sent successfully");
				return responseCode;
			}else 
				return responseCode;
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
			responseCode = 2;
		}
		return responseCode;
	}
	 public MailUtil(String[] emailAddresses, String subject, String description, String fromCustomerEmail,String[] attachmentFiles) {
			// this.adminManager = adminManager;
			this.emailBean = new EmailBean();
			this.emailBean.setFromAddress(fromCustomerEmail);
			//this.emailBean.setToAddress(emailAddresses);
			this.emailBean.setBccAddress(emailAddresses);
			this.emailBean.setSubject(subject);
			//this.emailBean.setFromWhom(user.getUserRoleDescription());
			this.emailBean.setBody(description);
			this.emailBean.setAttachmentFiles(attachmentFiles);
	    }
	 public void sendMailToParentChildrenPermissions(List<ViewPermissionSettings> permissionsList,Student student,String status,String fromEmail) {
			try {
					String[] bodyPara = new String[4];
					StringBuffer msg = new StringBuffer();
					msg.append("Dear Parent");
					msg.append(",");
					msg.append("<br>");
					bodyPara[0] = msg.toString();
					msg = new StringBuffer(); 
					String msgContent;
					msgContent = " As per your permission request of your child  "+student.getAccount().getPerson().getFullPersonName()+"  below are the timings & dates ";
					msg.append(msgContent);
					msg.append("<br>");
					bodyPara[1] = msg.toString();
					msg = new StringBuffer();
					msg.append("<b> Permission Details :- </b>");
					msg.append("<br>");
					msg.append("<br>");
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Permission Date</th><th class='head'>Day Name</th><th class='head'>Start Time</th><th class='head'>End Time</th></tr></thead>");
			         if (!ObjectFunctions.isNullOrEmpty(permissionsList)) {
			        	 for(ViewPermissionSettings viewPermissionSettings:permissionsList){
			        		 if (!ObjectFunctions.isNullOrEmpty(viewPermissionSettings)) {
									msg.append("<tr><td>"+	viewPermissionSettings.getFormattedEndDate()+"</td>");
									msg.append("<td>"+	viewPermissionSettings.getDays()+"</td>"); 
									msg.append("<td>"+	viewPermissionSettings.getStartTime()+"</td>"); 
									msg.append("<td>"+	viewPermissionSettings.getEndTime()+"</td></tr>");
							 }
			        		 viewPermissionSettings=null;
			        	 }
			         }
			        msg.append("</table>");
					msg.append("<br />");
					msg.append("<br />");
					if("approve".equalsIgnoreCase(status))
						msg.append("The above permission request is approved. Make sure your child reach home on time (if approved).");
					else
						msg.append("The above permission request is rejected.");
					bodyPara[2] = msg.toString();
					msg = new StringBuffer();
					msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
					msg.append("<br />");
					bodyPara[3] = msg.toString();
					getEmailBean().setFromAddress(fromEmail);
					getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
					getMailSender().sendHtmlMail(getEmailBean());
					setEmailBean(null);
					msg = null;
					bodyPara = null;
		            msgContent = null;
				
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}
	 
	 public void sendMailToStaffRequest(ViewStaffPermissionRequests vPermissionRequests,String status,String fromEmail) {
			try {
					String[] bodyPara = new String[4];
					StringBuffer msg = new StringBuffer();
					msg.append("Dear "+vPermissionRequests.getFullName());
					msg.append(",");
					msg.append("<br>");
					bodyPara[0] = msg.toString();
					msg = new StringBuffer(); 
					String msgContent;
					msgContent = " As per your permission request below are the timings & dates ";
					msg.append(msgContent);
					msg.append("<br>");
					bodyPara[1] = msg.toString();
					msg = new StringBuffer();
					msg.append("<b> Permission Details :- </b>");
					msg.append("<br>");
					msg.append("<br>");
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Permission Date</th><th class='head'>Comment</th><th class='head'>Start Time</th><th class='head'>End Time</th></tr></thead>");
			         if (!ObjectFunctions.isNullOrEmpty(vPermissionRequests)) {
						msg.append("<tr><td>"+	vPermissionRequests.getPermissionDateStr()+"</td>");
						msg.append("<td>"+	vPermissionRequests.getApprovalsComment()+"</td>"); 
						msg.append("<td>"+	vPermissionRequests.getStartTime()+"</td>"); 
						msg.append("<td>"+	vPermissionRequests.getEndTime()+"</td></tr>");
						vPermissionRequests=null;
			         }
			        msg.append("</table>");
					msg.append("<br />");
					msg.append("<br />");
					if("approve".equalsIgnoreCase(status))
						msg.append("The above permission request is approved.");
					else
						msg.append("The above permission request is rejected.");
					bodyPara[2] = msg.toString();
					msg = new StringBuffer();
					msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
					msg.append("<br />");
					bodyPara[3] = msg.toString();
					getEmailBean().setFromAddress(fromEmail);
					getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
					getMailSender().sendHtmlMail(getEmailBean());
					setEmailBean(null);
					msg = null;
					bodyPara = null;
		            msgContent = null;
				
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}
	 public void sendMailToAdminWhenParentChangeEmail(User studUser,String Class,String oldMobilNum1,String oldMobNum2,String oldPhoneNumber,String oldCity,String oldStreet,String oldState,String oldPincode,String oldEmail,String newStateName,String fromEmail){
			try{
				
				String[] bodyPara = new String[6];
				String[][] tableData = new String[4][4];
				StringBuffer msg = new StringBuffer();
				msg.append("Dear Administrator");
				msg.append(",");
				msg.append("<br>");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer(); 
				String msgContent;
				msgContent = "The following student parent has updated their information.";
				msg.append(msgContent);
				msg.append("<br>");
				bodyPara[1] = msg.toString();
				msg = new StringBuffer();
				tableData[0][0] = "Student Name&nbsp;:&nbsp;";
				tableData[0][1] = studUser.getPerson().getFirstName();
				tableData[1][0] = "Class & Section &nbsp;:&nbsp;";
				tableData[1][1] = Class;
				tableData[2][0] = "Admission Number&nbsp;:&nbsp;";
				tableData[2][1] = studUser.getAdmissionNumber();
				tableData[3][0] = "Parent Name &nbsp;:&nbsp;";
				tableData[3][1] = studUser.getPerson().getFatherName();
		        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "115");
				msg.append("<br>");
				if(!studUser.getPerson().getParentEmail().equalsIgnoreCase(oldEmail)){
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Previous Email</th><th class='head'>New Email</th></tr></thead>");
					if(ObjectFunctions.isNullOrEmpty(oldEmail))
						msg.append("<tr><td>"+ "-" +"</td>");
					else
					msg.append("<tr><td>"+	oldEmail+"</td>");
					msg.append("<td>"+	studUser.getPerson().getParentEmail()+"</td></tr>");
			        msg.append("</table>");
				}
				bodyPara[3] = msg.toString();
				msg = new StringBuffer();
				if((!studUser.getPerson().getMobileNumber().equalsIgnoreCase(oldMobilNum1)) || (!studUser.getPerson().getSecondaryMobileNumber().equalsIgnoreCase(oldMobNum2)) || (!studUser.getPerson().getPhoneNumber().equalsIgnoreCase(oldPhoneNumber))){
					if((!studUser.getPerson().getMobileNumber().equalsIgnoreCase(oldMobilNum1)) && (!studUser.getPerson().getSecondaryMobileNumber().equalsIgnoreCase(oldMobNum2)) && (!studUser.getPerson().getPhoneNumber().equalsIgnoreCase(oldPhoneNumber))){
						msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Previous Mobile Number1</th><th class='head'>New Mobile Number1</th><th class='head'>Previous Mobile Number2</th><th class='head'>New Mobile Number2</th><th class='head'>Previous Phone Number</th><th class='head'>Previous Phone Number</th></tr></thead>");
						msg.append("<tr>");
						if(ObjectFunctions.isNullOrEmpty(oldMobilNum1))
							msg.append("<td>"+ "-" +"</td>");
						else
							msg.append("<td>"+	oldMobilNum1+"</td>");
						msg.append("<td>"+	studUser.getPerson().getMobileNumber()+"</td>");
						if(ObjectFunctions.isNullOrEmpty(oldMobNum2))
							msg.append("<td>"+ "-" +"</td>");
						else
							msg.append("<td>"+	oldMobNum2+"</td>");
						msg.append("<td>"+	studUser.getPerson().getSecondaryMobileNumber()+"</td>");
						if(ObjectFunctions.isNullOrEmpty(oldPhoneNumber))
							msg.append("<td>"+ "-" +"</td>");
						else
							msg.append("<td>"+	oldPhoneNumber+"</td>");
						msg.append("<td>"+	studUser.getPerson().getPhoneNumber()+"</td>");
						msg.append("</tr>");
				        msg.append("</table>");
					}else if(!studUser.getPerson().getMobileNumber().equalsIgnoreCase(oldMobilNum1)){
						msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Previous Mobile Number1</th><th class='head'>New Mobile Number1</th></tr></thead>");
						msg.append("<tr><td>"+	oldMobilNum1+"</td>");
						msg.append("<td>"+	studUser.getPerson().getMobileNumber()+"</td></tr>");
				        msg.append("</table>");
					}else if(!studUser.getPerson().getSecondaryMobileNumber().equalsIgnoreCase(oldMobNum2)){
						msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Previous Mobile Number2</th><th class='head'>New Mobile Number2</th></tr></thead>");
						msg.append("<tr><td>"+	oldMobNum2+"</td>");
						msg.append("<td>"+	studUser.getPerson().getSecondaryMobileNumber()+"</td></tr>");
				        msg.append("</table>");
					}else{
						msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Previous Mobile Number2</th><th class='head'>New Mobile Number2</th></tr></thead>");
						msg.append("<tr><td>"+	oldPhoneNumber+"</td>");
						msg.append("<td>"+	studUser.getPerson().getPhoneNumber()+"</td></tr>");
				        msg.append("</table>");
					}
				}
				bodyPara[4] = msg.toString();
				msg = new StringBuffer();
				if(((!studUser.getPrimaryAddress().getStreetName().equalsIgnoreCase(oldStreet)) || (!studUser.getPrimaryAddress().getCity().equalsIgnoreCase(oldCity)) || (!newStateName.equalsIgnoreCase(oldState)) || (!studUser.getPrimaryAddress().getPostalCode().equalsIgnoreCase(oldPincode)))){
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1''><thead><tr><th class='head'>Previous Address</th><th class='head'>New Address</th></tr></thead>");
					if(!studUser.getPrimaryAddress().getStreetName().equalsIgnoreCase(oldStreet)){
						if(ObjectFunctions.isNullOrEmpty(oldStreet))
							msg.append("<tr><td>"+ "-" +"</td>");
						else
							msg.append("<tr><td>"+	oldStreet+"</td>");
						msg.append("<td>"+	studUser.getPrimaryAddress().getStreetName()+"</td></tr>");
					}
					if(!studUser.getPrimaryAddress().getCity().equalsIgnoreCase(oldCity)){
						if(ObjectFunctions.isNullOrEmpty(oldCity))
							msg.append("<tr><td>"+ "-" +"</td>");
						else
							msg.append("<tr><td>"+	oldCity+"</td>");
						msg.append("<td>"+	studUser.getPrimaryAddress().getCity()+"</td></tr>");
					}
					if(!newStateName.equalsIgnoreCase(oldState)){
						if(ObjectFunctions.isNullOrEmpty(oldState))
							msg.append("<tr><td>"+ "-" +"</td>");
						else
							msg.append("<tr><td>"+	oldState+"</td>");
						msg.append("<td>"+	newStateName+"</td></tr>");
					}
					if(!studUser.getPrimaryAddress().getPostalCode().equalsIgnoreCase(oldPincode)){
						if(ObjectFunctions.isNullOrEmpty(oldPincode))
							msg.append("<tr><td>"+ "-" +"</td>");
						else
							msg.append("<tr><td>"+	oldPincode+"</td>");
						msg.append("<td>"+	studUser.getPrimaryAddress().getPostalCode()+"</td></tr>");
					}
					 msg.append("</table>");
				}
				bodyPara[5] = msg.toString();
				getEmailBean().setFromAddress(fromEmail);
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getMailSender().sendHtmlMail(getEmailBean());
				setEmailBean(null);
				msg = null;
				bodyPara = null;
	            msgContent = null;
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    		log.debug(ex.getMessage());
	    	}
		}
	 public boolean sendMailToUpdatedCredentials(String username,String pwd,String name,String schoolname,String fromEmail,String salutation){
			try{
				 String[] bodyPara = new String[4];
			        String[][] tableData = new String[2][2];
			        StringBuffer msg = new StringBuffer();
			        log.debug(name);
			        if("Parent".equalsIgnoreCase(salutation))
			        	msg.append("Dear Parent");
			        else
			        	msg.append("Dear" +"&nbsp;"+ name);
			        msg.append(",");
			        msg.append("<br>");
			        msg.append("<br>");
			        msg.append("Your updated eazyschool login credentials.");
			        bodyPara[0]=msg.toString();	  
			        tableData[0][0]="User Name : ";
		        	tableData[0][1]=username;
		        	tableData[1][0]="Password : ";
				    tableData[1][1]=pwd;
			       // bodyPara[0]=msg.toString();	
			        bodyPara[1] = "~" + StringFunctions.formatDataWithTable(tableData, "170");
			        msg = new StringBuffer();
			        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
			        bodyPara[2]=msg.toString();	    	        
			        msg = new StringBuffer();
				    msg.append("Please keep this information for your records .");
				    msg.append("<br>");
				    msg.append("Thank You,");
				    msg.append("<br>");
				    msg.append("<br>");
				    msg.append(schoolname);
			        msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
				   // msg.append("Note: This is a System generated Mail therefore please don't reply to this mail.");
			        msg.append("<br style='margin: 5px 0px 5px 0px;'>"); 
			        bodyPara[3]=msg.toString();
			        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
		            getEmailBean().setFromAddress(fromEmail);
		            getMailSender().setContactEmail(this.getContactEmail());
		            getMailSender().setContactPassword(this.getContactPasword());
			        getMailSender().sendHtmlMail(getEmailBean());
		               log.debug("Hurray success....+ email sent successfully");
		                //saveActiveLogs(getEmailBean());
		              setEmailBean(null);
		              return true;
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    		log.debug(ex.getMessage());
	    	}
	    	return false;
	}
	 public void sendEMailForPwdAndUserNameChange(String password,String username,String fromEmail,String personName) {
			try {
				
				String[] bodyPara = new String[4];
				String[][] tableData = new String[3][3];
				StringBuffer msg = new StringBuffer();
				 if(!ObjectFunctions.isNullOrEmpty(personName))
					 msg.append("Dear" +"&nbsp;"+ personName);
		        else
		        	msg.append("Dear Parent");
		        msg.append(",");
		        msg.append("<br>");
		        msg.append("<br>");
				msg.append("Thank you for requesting your Eazyschool account login details. Here is your credentials.");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer();
				bodyPara[1] = msg.toString();
				tableData[0][0] = "URL  &nbsp;:&nbsp;";
				tableData[0][1] = "www.eazyschool.in";
				tableData[1][0] = "Login Id &nbsp;:&nbsp;";
				tableData[1][1] = username;
				tableData[2][0] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password&nbsp;:&nbsp;";
				tableData[2][1] = password;
		        bodyPara[2] = "~" + StringFunctions.formatDataWithTable(tableData, "115");
				msg = new StringBuffer();
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from Eazy School. Please DO NOT REPLY to this email.");
				bodyPara[3] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getEmailBean().setFromAddress(fromEmail);
				getMailSender().setContactEmail(this.getContactEmail());
				getMailSender().setContactPassword(this.getContactPasword());
				getMailSender().sendHtmlMail(getEmailBean());
				setEmailBean(null);
				tableData=null;
				msg=null;
				bodyPara=null;
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}
	 public void sendEmailToAllCustomersTheirTopStduentsDetails(List<StudentMonthlyAttendance> studentMonthlyAttendanceList,List<StudentDailyAttendance> studentDailyAttendanceList,List<ViewStudentMarksDetails> stdentMarksDetailsList,List<ViewStaffPersonAccountDetails> subjectMarksDetailsList,String custEmail,String custOrganization,String fromEmail,boolean top5Attendance,boolean top5marks,boolean top5teachers) {
			try{
				String[] bodyPara = new String[6];
				StringBuffer msg = new StringBuffer();
				//Date todaysDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,new Date()));
				msg.append("EazySchool Monthly report as on "+DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN,new Date()));
				msg.append(",");
				//msg.append("<br>");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer(); 
				//msg.append("<br>");
				msg.append("<br>");
				log.debug(msg.toString());
				msg.append("<b>Top 5 best Stduents Attendance</b>");
				msg.append("<br>");
				msg.append("<br>");
				if (!ObjectFunctions.isNullOrEmpty(studentDailyAttendanceList)) {
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Ad No.</th><th class='head'>Student Name</th><th class='head'>Class & Section</th><th class='head'>Attendance(%)</th></tr></thead>");
					for(StudentDailyAttendance obj:studentDailyAttendanceList)
					{
						if (!ObjectFunctions.isNullOrEmpty(obj)) {
							msg.append("<tr><td>"+	obj.getAdmissionNumber()+"</td>");
							msg.append("<td>"+	obj.getStudentName()+"</td>");
							msg.append("<td>"+	obj.getClassNameAndSection()+"</td>");
							msg.append("<td>"+	obj.getAttendancePercentage()+"</td></tr>");
						}
						obj=null;
					}
					 msg.append("</table>");
				}else if (!ObjectFunctions.isNullOrEmpty(studentMonthlyAttendanceList)) {
					//msg.append("Top 5 best Stduents Attendance");
					//msg.append("<br>");	
					//msg.append("<br>");
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Ad No.</th><th class='head'>Student Name</th><th class='head'>Class & Section</th><th class='head'>Attendance(%)</th></tr></thead>");
					for(StudentMonthlyAttendance obj:studentMonthlyAttendanceList)
					{
						if (!ObjectFunctions.isNullOrEmpty(obj)) {
							msg.append("<tr><td>"+	obj.getAdmissionNumber()+"</td>");
							msg.append("<td>"+	obj.getStudentName()+"</td>");
							msg.append("<td>"+	obj.getClassNameAndSection()+"</td>");
							msg.append("<td>"+	obj.getAttendancePercentage()+"</td></tr>");
						}
						obj=null;
					}
					 msg.append("</table>");
				}
				else{
					msg.append("Kindly start using Attendance module to start getting this report.");
				}
				msg.append("<br>");
				if(top5Attendance){
					msg.append(" To view more information <a href=http://www.EazySchool.in>login to EazySchool</a> account and go through below link.");
				msg.append("<br>");
				msg.append("Login-> Go to Reports-> Monthly Report");
				}
				bodyPara[1] = msg.toString();
				msg = new StringBuffer(); 
				msg.append("<br>");
				//msg.append("<br>");
				msg.append("<b>Top 5 best Students Marks</b>");
				msg.append("<br>");	
				msg.append("<br>");
				if (!ObjectFunctions.isNullOrEmpty(stdentMarksDetailsList)) {
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Ad No.</th><th class='head'>Student Name</th><th class='head'>Class & Section</th><th class='head'>Marks(%)</th></tr></thead>");
					for(ViewStudentMarksDetails viewStudentMarksDetails:stdentMarksDetailsList)
					{
						if (!ObjectFunctions.isNullOrEmpty(viewStudentMarksDetails)) {
							msg.append("<tr><td>"+	viewStudentMarksDetails.getAdmissionNumber()+"</td>");
							msg.append("<td>"+	viewStudentMarksDetails.getStudentName()+"</td>");
							msg.append("<td>"+	viewStudentMarksDetails.getClassName()+"</td>");
							msg.append("<td>"+	viewStudentMarksDetails.getMarksPercentage()+"</td></tr>");
						}
						viewStudentMarksDetails=null;
					}
					 msg.append("</table>");
				}else{
					msg.append("Kindly start using Examination module to start getting this report.");
				}
				//msg.append("<br>");
				msg.append("<br>");
				if(top5marks){
					msg.append(" To view more information <a href=http://www.EazySchool.in>login to EazySchool</a> account and go through below link.");
				msg.append("<br>");
				msg.append("Login-> Go to Reports-> Monthly Report");
				}
				msg.append("<br>");
				bodyPara[2] = msg.toString();
				msg = new StringBuffer(); 
				msg.append("<b>Top 5 best Teachers Results</b>");
				msg.append("<br>");	
				msg.append("<br>");
				if (!ObjectFunctions.isNullOrEmpty(subjectMarksDetailsList)) {
					msg.append("<table border='1' style='margin-bottom:0;cellpadding='1' cellspacing='1' '><thead><tr><th class='head'>Teacher Name</th><th class='head'>Results(%)</th></tr></thead>");
					for(ViewStaffPersonAccountDetails subjTeac:subjectMarksDetailsList)
					{
						if (!ObjectFunctions.isNullOrEmpty(subjTeac)) {
							msg.append("<tr><td>"+	subjTeac.getFirstName()+"</td>");
							msg.append("<td>"+	subjTeac.getSubjectsMarksPercentage()+"</td></tr>");
						}
						subjTeac=null;
					}
					 msg.append("</table>");
				}else{
					msg.append("Kindly start using Academic module to start getting this report.");
				}
				msg.append("<br>");
				if(top5teachers){
					msg.append(" To view more information <a href=http://www.EazySchool.in>login to EazySchool</a> account and go through below link.");
				msg.append("<br>");
				msg.append("Login-> Go to Reports-> Monthly Report");
				}
				bodyPara[3] = msg.toString();
				msg = new StringBuffer(); 
				//msg.append("</table>");
				bodyPara[4] = msg.toString();
				msg = new StringBuffer();
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from Eazy School. Please DO NOT REPLY to this email.");
				msg.append("<br />");
				bodyPara[5] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getMailSender().sendHtmlMail(getEmailBean());
				log.debug("Hurray success....+ email sent successfully");
				setEmailBean(null);
				msg=null;
				bodyPara=null;
				stdentMarksDetailsList=null;
				subjectMarksDetailsList= null;
				studentDailyAttendanceList = null;
				studentMonthlyAttendanceList = null;
			}
			catch(Exception ex){
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}

	public boolean sendFeePaidAndUnpaidSummary(String fromEmail) {
		try {
			String[] bodyPara = new String[4];
			StringBuffer msg = new StringBuffer();
			msg.append(getEmailBean().getBody());
			msg.append("<br />");
			bodyPara[0] = msg.toString();
			getEmailBean().setFromAddress(fromEmail);
			getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			getMailSender().sendHtmlMail(getEmailBean());
			log.debug("Hurray success....+ email sent successfully");
			setEmailBean(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
		return false;
	}
	public int sendEmailPaySlipsToStaff(String fullName,String fromEmail,String monthName,Customer usrCust) {
		   int responseCode=0;
		    try {String[] bodyPara = new String[3];
		    StringBuffer msg = new StringBuffer();
		    msg.append("Dear" +"&nbsp;"+ fullName);
		    msg.append(",");
		    msg.append("<br/><br/>");
		    bodyPara[0] = msg.toString();
		    msg = new StringBuffer();
		    msg.append("Your "+monthName+" month Pay-slip has been generated and attached to this mail.");
		    //msg.append(getEmailBean().getBody());
		    msg.append("<br />");
		    bodyPara[1] = msg.toString();
		    msg = new StringBuffer();
			msg.append("<br /><strong>**NOTE:</strong> This email and any attachments are confidential and may also be privileged.If you are not the intended recipient, please delete all copies and notify the sender immediately. .");
			bodyPara[2] = msg.toString();
		    getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
		    getEmailBean().setFromAddress(fromEmail);
		    getEmailBean().setAttachmentFiles(getEmailBean().getAttachmentFiles());
		    getMailSender().setContactEmail(usrCust.getContactEmail());
		    getMailSender().setContactPassword(usrCust.getContactPassword());
		    responseCode = getMailSender().sendHtmlMail(getEmailBean());
		    log.debug("Hurray success....+ email sent successfully");
		    // saveActiveLogs(getEmailBean());
			setEmailBean(null);
			if(responseCode == 0){
				log.debug("Hurray success....+ email sent successfully");
				return responseCode;
			}else 
				return responseCode;} catch (Exception ex) {
				    ex.printStackTrace();
				    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				    log.debug(ex.getMessage());
				    responseCode = 3;
				}
				return responseCode;
		    }
	
	 public void sendEMailForInviteCustomerEnrollment(String schoolName,String totalStudents,String emailAddress,String accountType,long id) {
			try {
				
				String[] bodyPara = new String[4];
				String[][] tableData = new String[3][3];
				StringBuffer msg = new StringBuffer();
				msg.append("Dear" +"&nbsp; Valued Client");
		        msg.append(",");
		        msg.append("<br>");
		        msg.append("<br>");
				msg.append("Welcome to the family of EazySchool management software , a product Of HYNIVA Consulting Pvt Ltd. Thank you for choosing  EAZYSCHOOL and joining hands with us for the 'GO GREEN' motto for  school management systems to provide Quality education along with student safety through well organised administration.");
				msg.append("<br>");
				msg.append("<br>");
				msg.append("The product is ready to use once you complete the registration of the institution. Kindly fill the application or registration Form through the link Provided here "+getHostUrl()+"/signup/doCreateNewCustomerAccount.do?inviteId="+id+". Once you are finished with this registration our support team will guide you through the product .");
				msg.append("<br>");
				msg.append("<br>");
				msg.append("Enjoy a wonderful user experience.");
				msg.append("<br>");
				msg.append("<br>");
				msg.append("Thank you");
				msg.append("<br>");
				msg.append("Eazycollege /Eazyschool");
				msg.append("<br>");
				msg.append("Management team");
				bodyPara[0] = msg.toString();
				msg = new StringBuffer();
				msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
				bodyPara[1] = msg.toString();
				getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getEmailBean().setFromAddress(emailAddress);
				getMailSender().setContactEmail(this.getContactEmail());
				getMailSender().setContactPassword(this.getContactPasword());
				getMailSender().sendHtmlMail(getEmailBean());
				setEmailBean(null);
				tableData=null;
				msg=null;
				bodyPara=null;
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}
	 
	 
	 public void sendMailForNewCustomerRegistrationReject(CustomerEnrollmentRequest customerEnrollmentRequest){
			try{
		        String[] bodyPara = new String[4];
		        String[][] tableData = new String[2][2];
		        log.debug("Customer : " + customerEnrollmentRequest.getCustEmail());
		        StringBuffer msg = new StringBuffer();
		        msg.append("Dear" +"&nbsp;"+ customerEnrollmentRequest.getCustomerFullPersonName());
		        msg.append(",");
		        msg.append("<br>");
		        msg.append("<br>");
		        msg.append("Your request to the access for the EazySchool product could not be completed yet due to incomplete or inadequate data that was provided by you , so we here by request you to provide with complete and adequate data so that your request could be processed.");
		        msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services. Please DO NOT REPLY to this email.");
		        msg.append("<br /><br />");
		        msg.append("If you have any questions or need any help with getting your account set up, please email support@eazyschool.com or call 080-46620999");
		        bodyPara[3]=msg.toString();
		        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
				getMailSender().sendHtmlMail(getEmailBean());
	            log.debug("Hurray success....+ email sent successfully");
	            //saveActiveLogs(getEmailBean());
	            setEmailBean(null);
				tableData=null;
				msg=null;
				bodyPara=null;
				customerEnrollmentRequest=null;
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	    		log.debug(ex.getMessage());
	    	}
		}

	public boolean sendCustomerRequestToSupportingTeam(String fromEmail,StringBuffer msg) {
		try {
			String[] bodyPara = new String[4];
			msg.append(getEmailBean().getBody());
			msg.append("<br />");
			bodyPara[0] = msg.toString();
			getEmailBean().setFromAddress(fromEmail);
			getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			getMailSender().sendHtmlMail(getEmailBean());
			log.debug("Hurray success....+ email sent successfully");
			setEmailBean(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			log.debug(ex.getMessage());
		}
		return false;
	}
	public boolean sendEventEmail(String fromEmail) {
		try {
			    String[] bodyPara = new String[4];
			    StringBuffer msg = new StringBuffer();
			    msg.append(getEmailBean().getBody());
			    msg.append("<br />");
			    bodyPara[0] = msg.toString();
			    getEmailBean().setFromAddress(fromEmail);
			    getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			    getMailSender().sendHtmlMail(getEmailBean());
			    log.debug("Hurray success....+ email sent successfully");
				setEmailBean(null);
			} catch (Exception ex) {
			    ex.printStackTrace();
			    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			    log.debug(ex.getMessage());
			}
			return false;
	    }
	public void sendMailErrorLogToSupportTeam(JRException exception, String exceptionDescription, Customer customer, ViewAllUsers viewAllUsers) {
    	try {
    	    String[] bodyPara = new String[4];
	        String[][] tableData = new String[5][5];
	        StringBuffer msg = new StringBuffer();
	        msg.append("Dear Team,");
	        bodyPara[0]=msg.toString();
	        tableData[0][0]="Customer Id&nbsp;:&nbsp;";
	        tableData[0][1]=customer.getStrId();
	        tableData[1][0]="Customer E-mail&nbsp;:&nbsp;";
	        tableData[1][1]=customer.getCustEmail();
	        tableData[2][0]="User Id&nbsp;:&nbsp;";
	        tableData[2][1]=String.valueOf(viewAllUsers.getAccountId());
	        tableData[3][0]="Username&nbsp;:&nbsp;";
	        tableData[3][1]=viewAllUsers.getUsername();
	        tableData[4][0]="User Role&nbsp;:&nbsp;";
	        tableData[4][1]=viewAllUsers.getRoleName();
	        bodyPara[1] = "~" + StringFunctions.formatDataWithTable(tableData, "180");
	        msg = new StringBuffer();
	        msg.append(exceptionDescription);
	        bodyPara[2]=msg.toString();
	        msg = new StringBuffer();
	        msg.append("<br /><strong>**NOTE:</strong> This is an automated email from Hyniva Consulting Services Pvt. Ltd. Please DO NOT REPLY to this email.");
	        bodyPara[3]=msg.toString();
	        getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
	        getMailSender().sendHtmlMail(getEmailBean());
            log.debug("Hurray success....+ exception log email sent successfully");
            setEmailBean(null);
    	} catch(Exception ex){
    		ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    }
	/*Ravi Theja Panem  send Reminder mail*/
	 public void sendMailForRemider(String staffEmail,String fullStaffName,String custOrganizationName,String fromEmail,Customer customer, Reminder reminder) {
			try {
				String[] bodyPara = new String[4];
				if (!StringFunctions.isNullOrEmpty(staffEmail)) {
					StringBuffer msg = new StringBuffer();
					msg.append("Dear "+fullStaffName+",");
					msg.append("<br />");
					msg.append("This mail is a reminder for");
					msg.append(" <b>"+reminder.getName()+"</b> on "+ new SimpleDateFormat("dd-MMM-YYYY").format(reminder.getExpirationDate()));
					msg.append("<br />");
					//msg.append("<br /><br /><strong>**NOTE:</strong> This is an automated email from EazySchool. Please DO NOT REPLY to this email.");
					msg.append("<br />");
					bodyPara[3] = msg.toString();
					getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
					getEmailBean().setFromAddress(fromEmail);
					getMailSender().setContactEmail(this.getContactEmail());
					getMailSender().setContactPassword(this.getContactPasword());
					getMailSender().sendHtmlMail(getEmailBean());
					log.debug("Hurray success....+ email sent successfully");
				}
				setEmailBean(null);
			} catch (Exception ex) {
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				log.debug(ex.getMessage());
			}
		}
	 /**
	  * 
	  * @param fromEmail
	  * @return
	  */
	 public int sendEmailForAttendance(String fromEmail) {
	    	int responseCode=0;
		try {
			    String[] bodyPara = new String[4];
			    StringBuffer msg = new StringBuffer();
			    msg.append("Dear" + "&nbsp; Parent");
			    msg.append(",");
			    msg.append("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;");
			    msg.append(getEmailBean().getBody());
			    msg.append("<br />");
			    bodyPara[0] = msg.toString();
			    getEmailBean().setBody(StringFunctions.formatEmailBody(bodyPara));
			    getEmailBean().setFromAddress(fromEmail);
			    getEmailBean().setAttachmentFiles(getEmailBean().getAttachmentFiles());
			    getMailSender().setContactEmail(this.getContactEmail());
			    getMailSender().setContactPassword(this.getContactPasword());
			    responseCode = getMailSender().sendHtmlMail(getEmailBean());
			    log.debug("Hurray success....+ email sent successfully");
				setEmailBean(null);
				if(responseCode == 0){
					log.debug("Hurray success....+ email sent successfully");
					return responseCode;
				}else 
					return responseCode;
			} catch (Exception ex) {
			    ex.printStackTrace();
			    log.debug(ex.getMessage());
			    responseCode = 3;
			}
			return responseCode;
	    }
}