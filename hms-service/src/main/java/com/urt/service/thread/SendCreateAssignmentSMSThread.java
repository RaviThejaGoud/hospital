package com.urt.service.thread;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;

public class SendCreateAssignmentSMSThread  implements Runnable{
	
	private static final Log log = LogFactory.getLog(SendCreateAssignmentSMSThread.class);
	private Thread t;
	//private Map<Messages, Set<String>> messageMobileMap;
	private AcademicYear academicYear;
	private Customer customer;
	private String studyClassId;
	private String subjectId;
	private Date assignmentDate;
	private long createdUserId;
	private SMSServiceProviders smsServiceProvider;
	private String description;
	
	@Autowired
	public CommunicationManager communicationManager;
	
	@Autowired
	public AdminManager adminManager;
	
	public SendCreateAssignmentSMSThread(Customer customer,AcademicYear academicYear,String studyClassId,String subjectId,Date assignmentDate,long createdUserId,SMSServiceProviders smsServiceProvider, String description) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		this.customer = customer;
		this.academicYear = academicYear;
		this.studyClassId = studyClassId;
		this.subjectId = subjectId;
		this.assignmentDate = assignmentDate;
		this.createdUserId = createdUserId;
		this.smsServiceProvider = smsServiceProvider;
		this.description = description;
	}



	@Override
	public void run() {
		
		if (!ObjectFunctions.isNullOrEmpty(customer)) {
			Messages message = null;
			List<Object[]> studentsList = null;
			String mobileNumbers = null;
			String mobileNumber = null;
			String firstName = null;
			String studentId=null;
			StringBuffer buffer = null;
			StringBuffer failedMsgs = null;
			String mobileType = customer.getMobileType();
			failedMsgs = new StringBuffer();
			failedMsgs.append("(");
			if(!ObjectFunctions.isNullOrEmpty(studyClassId) && !ObjectFunctions.isNullOrEmpty(subjectId)){
				StudySubject subject = (StudySubject) adminManager.get(StudySubject.class,Long.valueOf(subjectId));
				if(ObjectFunctions.isNullOrEmpty(subject)){
					subject = new StudySubject();
					
				}
				
				if(291 == customer.getId())
				{
					Set<String> mobileNumbersSet = new HashSet<String>();
					mobileNumbersSet.add("9845011811");
					mobileNumbersSet.add("9880066516");
					mobileNumbersSet.add("9341949465");
					mobileNumbersSet.add("9900631774");
					mobileNumbersSet.add("9739911811");
					firstName = "Student_Name";
					if (ObjectFunctions.isNotNullOrEmpty(mobileNumbersSet))
					{
						message = new Messages();
							//getMobileNumbersSet().add(mobileNumbers);
							message.setCustomer(customer);		
							message.setSmsSenderId(customer.getSender());
							buffer = new StringBuffer();
							System.out.println("desc is::"+description);
							if(ObjectFunctions.isNullOrEmpty(subject.getName()) && !ObjectFunctions.isNullOrEmpty(description)){
								buffer.append("Dear Parent, Your child "+ firstName+ " has to complete the assignment \""+description+"\" on "+DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, assignmentDate)+" ");
							}
							else
							buffer.append("Dear Parent, Your child "+ firstName+ " has to complete "+ subject.getName() +" assignment on "+DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, assignmentDate)+" ");
							message.setMessageDescription(buffer.toString());
							message.setAcademicYear(academicYear);
							message.setStatus(Constants.MODIFY_STATUS);
							message.setCreatedById(createdUserId);
							message.setPurposeType("Send Class Assignment");
							log.debug(buffer.toString());
							Student student = null;
							student = (Student) adminManager.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and id="+ studentId);
							message = communicationManager.saveMessageDetailsTracking(message,student,null,null);	
							boolean smsStatus = communicationManager.deliverSms(message,mobileNumbersSet, smsServiceProvider);
							mobileNumbersSet = null;
							if (smsStatus) {
							} else {
								failedMsgs.append(firstName+ ",");
							}
					}
					mobileNumbersSet = null;
				}
				else
				{
					StringBuffer query = new StringBuffer("select firstName,studId,");
					if("B".equals(mobileType))
						query.append("mobileNumber,secondaryMobileNumber,anotherMobileNumber,anotherSecondaryMobileNumber");
					else if("P".equalsIgnoreCase(mobileType))
						query.append("mobileNumber,anotherMobileNumber");
					else
						query.append("secondaryMobileNumber,anotherSecondaryMobileNumber");
					query.append(",addressType from vw_studentClassDetails where academicYearId="+ academicYear.getId()+ " and custId="+ customer.getId()+ " and studDiscontinueDesc is null and classSectionId="+ studyClassId + " and (mobileNumber!='' or anotherMobileNumber!='')");
					studentsList = adminManager.getAll(query.toString()); 
					if (ObjectFunctions.isNotNullOrEmpty(studentsList)) {
						for (Object[] obje : studentsList) {
							message = new Messages();
							Set<String> mobileNumbersSet = new HashSet<String>();
							firstName = obje[0].toString();
							studentId = obje[1].toString();
							if("B".equals(mobileType)){
								if("R".equalsIgnoreCase(obje[6].toString())){
									if(!ObjectFunctions.isNullOrEmpty(obje[3]))
										mobileNumbers = obje[3].toString();
									if(!ObjectFunctions.isNullOrEmpty(obje[2]))
										mobileNumber = obje[2].toString();
								}else{
									if(!ObjectFunctions.isNullOrEmpty(obje[4]))
										mobileNumbers = obje[4].toString();
									if(!ObjectFunctions.isNullOrEmpty(obje[5]))
										mobileNumber = obje[5].toString();
								}
								mobileNumbersSet.addAll(adminManager.addMobileNumberBasedOnSettings(mobileType,mobileNumber,mobileNumbers));
								mobileNumbers = null;
								mobileNumber = null;
							}else{
								if("R".equalsIgnoreCase(obje[4].toString())){
									if(!ObjectFunctions.isNullOrEmpty(obje[2]))
										mobileNumbersSet.add(obje[2].toString());
								}else{
									if(!ObjectFunctions.isNullOrEmpty(obje[3]))
										mobileNumbersSet.add(obje[3].toString());
								}
							}
							if (ObjectFunctions.isNotNullOrEmpty(mobileNumbersSet))
							{
									//getMobileNumbersSet().add(mobileNumbers);
									message.setCustomer(customer);	 
									message.setSmsSenderId(customer.getSender());
									buffer = new StringBuffer();
									//buffer.append("Dear Parent, Your child "+ firstName+ ", has to complete "+ subject.getName() +" assignment on "+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, assignmentDate)+" So, please take care. Thank you from the Principal ");
									if(ObjectFunctions.isNullOrEmpty(subject.getName()) && !ObjectFunctions.isNullOrEmpty(description)){
										buffer.append("Dear Parent, Your child "+ firstName+ " has to complete the assignment \""+description+"\" on "+DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, assignmentDate)+" ");
									}
									else
									buffer.append("Dear Parent, Your child "+ firstName+ " has to complete "+ subject.getName() +" assignment on "+DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, assignmentDate)+" ");
									message.setMessageDescription(buffer.toString());
									message.setAcademicYear(academicYear);
									message.setStatus(Constants.MODIFY_STATUS);
									message.setCreatedById(createdUserId);
									message.setPurposeType("Send Class Assignment");
									log.debug(buffer.toString());
									Student student = null;
									student = (Student) adminManager.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and id="+ studentId);
									message = communicationManager.saveMessageDetailsTracking(message,student,null,null);	
									boolean smsStatus = communicationManager.deliverSms(message,mobileNumbersSet, smsServiceProvider);
									mobileNumbersSet = null;
									if (smsStatus) {
									} else {
										failedMsgs.append(firstName+ ",");
									}
							}
						mobileNumbersSet = null;
						}
					}
				}
				
				
				if(!ObjectFunctions.isNullOrEmpty(message))
				{
					User loginUserAcc = (User) adminManager.get(User.class,"id="+createdUserId);
					if (!ObjectFunctions.isNullOrEmpty(loginUserAcc))
					{
						Set<String> mobileNumbersSet = new HashSet<String>();
						
						if (!ObjectFunctions.isNullOrEmpty(loginUserAcc.getPerson()))
						{
							if (!ObjectFunctions.isNullOrEmpty(loginUserAcc.getPerson().getMobileNumber()))
							{
								mobileNumbersSet.add(loginUserAcc.getPerson().getMobileNumber());
								
								message = communicationManager.saveMessageDetailsTracking(message,null,null,loginUserAcc);	
								
								communicationManager.deliverSms(message,mobileNumbersSet, smsServiceProvider);
							}
						}
						mobileNumbersSet = null;
					}
				}
				message = null;
			}
		} 
		
	}

	
	 public void start ()
	   {
	      if (t == null)
	      {
	         t = new Thread (this);
	         t.start ();
	      }
	   }
	 
}
