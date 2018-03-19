package com.urt.service.thread;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.student.StudentManager;

public class SendEmailToParentsAndStudentThread  implements Runnable{
	private Thread t;
	Map<String, User> sendEmailToParentsAndStudentMap;
	private Customer customer;
	
	
	@Autowired
	public StudentManager studentManager;
	
	public SendEmailToParentsAndStudentThread(Map<String, User> sendEmailToParentsAndStudentMap,Customer customer) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.sendEmailToParentsAndStudentMap = sendEmailToParentsAndStudentMap;
		this.customer = customer;
	}



	@Override
	public void run() {
		for (Entry<String, User> entry : sendEmailToParentsAndStudentMap.entrySet()) { 
			//log.debug(entry.getKey() + " : " + entry.getValue()); 
			if(!ObjectFunctions.isNullOrEmpty(entry.getValue()))
			{
				String parentUserName = null;
				String key = entry.getKey();
				
				String[] keyArr =  key.split("$");
				
				String emailEmail = keyArr[0].toString();
				String fullName = keyArr[1].toString();
				String classSection = keyArr[2].toString();
				String mailType = keyArr[3].toString();
				
				if(!ObjectFunctions.isNullOrEmpty(keyArr[4]))
					parentUserName = keyArr[4].toString();
				
				User account = (User) entry.getValue();
				
				//Customer customer = (Customer) valueList.get(0);
				if("Students".equalsIgnoreCase(mailType))
				{
					if(!ObjectFunctions.isNullOrEmpty(emailEmail))
					 {
						 studentManager.sendEmailToParentsAndStudent(emailEmail,fullName,customer,classSection,account,null,"Students",null);
					 }
				}
				else if("Parents".equalsIgnoreCase(mailType))
				{
					if(!ObjectFunctions.isNullOrEmpty(emailEmail))
					 {
						 studentManager.sendEmailToParentsAndStudent(emailEmail,fullName,customer,classSection,account,parentUserName,"Parents",null);
						 
					 }
				}
				account = null;
			}
				//communicationManager.deliverSms(entry.getKey(),entry.getValue(), custId);
			
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
