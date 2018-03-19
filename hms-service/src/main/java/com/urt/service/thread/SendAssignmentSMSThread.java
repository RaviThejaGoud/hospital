package com.urt.service.thread;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;

public class SendAssignmentSMSThread  implements Runnable{
	private Thread t;
	//private Map<Messages, Set<String>> messageMobileMap;
	private List<Student> studentObjList;
	private Customer customer;
	private String subjectName;
	private Date assignmentDate;
	private User createduserObj;
	
	
	@Autowired
	public CommunicationManager communicationManager;
	
	@Autowired
	public AdminManager adminManager;
	
	public SendAssignmentSMSThread(List<Student> studentObjList, Customer customer,String subjectName,Date assignmentDate,User createduserObj) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.studentObjList = studentObjList;
		this.customer = customer;
		this.subjectName = subjectName;
		this.assignmentDate = assignmentDate;
		this.createduserObj = createduserObj;
	}



	@Override
	public void run() {
		
		if(!ObjectFunctions.isNullOrEmpty(studentObjList))
		{
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			for(Student studentObj : studentObjList)
			{
				User userObj = studentObj.getAccount();
				Messages messages = new Messages();
				String msgContent = "Dear Parent, your child "+userObj.getFullPersonName()+", has not done "+ subjectName +" assignment on "+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, assignmentDate) +" So, please take care. Thank you from the Principal ";
				messages.setMessageDescription(msgContent);
				messages.setCreatedById(createduserObj.getId());
				messages.setCreatedDate(new Date());
				messages.setLastAccessDate(new Date());
				messages.setLastUpdatedDate(new Date());
				messages.setStatus("A");
				messages.setSenderName(createduserObj.getUserRoleDescription());
				messages.setPurposeType("regd: Assignment not completed of the "+ userObj.getFullPersonName());
				if (!ObjectFunctions.isNullOrEmpty(customer)) {
					messages.setCustomer(customer);
				}
				messages.setSmsSenderId(customer.getSender());
				Set<String> mobileNumbers = adminManager.addMobileNumbersBasedOnAddressType(customer.getMobileType(),userObj.getPerson().getMobileNumber(),userObj.getPerson().getSecondaryMobileNumber(),userObj.getPerson().getAnotherMobileNumber(),userObj.getPerson().getAnotherSecondaryMobileNumber(),userObj.getPerson().getAddressType());
				if (!ObjectFunctions.isNullOrEmpty(mobileNumbers)) {
					//getMobileNumbersSet().addAll(mobileNumbers); 
					messages = communicationManager.saveMessageDetailsTracking(messages,studentObj,null,null);		
					communicationManager.deliverSms(messages,mobileNumbers,smsServiceProvider);
				}
				mobileNumbers = null;
			}
		}
		
		
		/*for (Entry<Messages, Set<String>> entry : messageMobileMap.entrySet()) { 
			//log.debug(entry.getKey() + " : " + entry.getValue()); 
			if(!ObjectFunctions.isNullOrEmpty(entry.getValue()))
				communicationManager.deliverSms(entry.getKey(),entry.getValue(), custId);
			
		}*/
		
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
