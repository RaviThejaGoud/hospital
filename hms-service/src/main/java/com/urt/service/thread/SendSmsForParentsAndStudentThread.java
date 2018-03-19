package com.urt.service.thread;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.student.StudentManager;

public class SendSmsForParentsAndStudentThread  implements Runnable{
	private Thread t;
	Map<String, List> sendSmsForParentsAndStudentMap;
	private Customer customer;
	private SMSServiceProviders smsServiceProvider;
	
	@Autowired
	public StudentManager studentManager;
	
	public SendSmsForParentsAndStudentThread(Map<String, List> sendEmailToParentsAndStudentMap,Customer customer,SMSServiceProviders smsServiceProvider) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.sendSmsForParentsAndStudentMap = sendEmailToParentsAndStudentMap;
		this.customer = customer;
		this.smsServiceProvider = smsServiceProvider;
	}



	@Override
	public void run() {
		for (Entry<String, List> entry : sendSmsForParentsAndStudentMap.entrySet()) { 
			//log.debug(entry.getKey() + " : " + entry.getValue()); 
			if(!ObjectFunctions.isNullOrEmpty(entry.getValue()))
			{
				String key = entry.getKey();
				
				String[] keyArr =  key.split("$");
				if(keyArr.length >=5){
				String mobileNumber = keyArr[0].toString();
				String secondaryMobileNumber = keyArr[1].toString();
				String studMobileNumber = keyArr[2].toString();
				String updatedAdmissionNo = keyArr[3].toString();
				
				String newPassword = keyArr[4].toString();
				
				List valuesList =  (List) entry.getValue();
				
				Person person = (Person)valuesList.get(0);
				User parentUserObject = (User)valuesList.get(1);
				Student studObject = (Student)valuesList.get(2);
				User account = (User)valuesList.get(3);
				
				studentManager.sendSmsForParentsAndStudent(mobileNumber,secondaryMobileNumber,studMobileNumber,account,parentUserObject,studObject,customer,updatedAdmissionNo,newPassword,smsServiceProvider);
				
				person = null;
				parentUserObject = null;
				studObject = null;
				account = null;
				valuesList = null;
				}
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
