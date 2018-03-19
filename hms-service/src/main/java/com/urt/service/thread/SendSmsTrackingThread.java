package com.urt.service.thread;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;

public class SendSmsTrackingThread  implements Runnable{
	private Thread t;
	Map<Messages, List> messageMobileMap;
	private SMSServiceProviders smsServiceProvider;
	
	@Autowired
	public StudentManager studentManager;
	
	@Autowired
	public CommunicationManager communicationManager;
	
	
	public SendSmsTrackingThread(Map<Messages, List> messageMobileMap,SMSServiceProviders smsServiceProvider) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.messageMobileMap = messageMobileMap;
		this.smsServiceProvider = smsServiceProvider;
	}



	@Override
	public void run() {
		
		for (Entry<Messages, List> entry : messageMobileMap.entrySet()) { 
			//log.debug(entry.getKey() + " : " + entry.getValue()); 
			if(!ObjectFunctions.isNullOrEmpty(entry.getValue()))
			{
				Student studObject = null;
				Staff staffObject = null;
				User parentUserObject = null;
				
				List valuesList =  (List) entry.getValue();
				
				if(!ObjectFunctions.isNullOrEmpty(valuesList))
				{
					if(!ObjectFunctions.isNullOrEmpty(valuesList.get(0)))
						studObject = (Student)valuesList.get(0);
					if(!ObjectFunctions.isNullOrEmpty(valuesList.get(1)))
						staffObject = (Staff)valuesList.get(1);
					if(!ObjectFunctions.isNullOrEmpty(valuesList.get(2)))
						parentUserObject = (User)valuesList.get(2);
					Set<String>  mobileNumberSet= (Set<String>)valuesList.get(3);
					Messages objMsg = (Messages)entry.getKey();
					objMsg = studentManager.saveMessageDetailsTracking(objMsg,studObject,staffObject,parentUserObject);
					
					communicationManager.deliverSms(objMsg,mobileNumberSet, smsServiceProvider);
				}
				valuesList = null;
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
