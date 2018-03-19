package com.urt.service.thread;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.study.Student;
import com.urt.service.manager.interfaces.admin.AdminManager;

public class EntryStudentCommittedPaymentThread  implements Runnable{
	private Thread t;
	private Map<String, Student> entryStudentCommittedPaymentMap;
	private Customer customer;
	
	
	@Autowired
	public AdminManager adminManager;
	
	public EntryStudentCommittedPaymentThread(Map<String, Student> entryStudentCommittedPaymentMap,Customer customer) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.entryStudentCommittedPaymentMap = entryStudentCommittedPaymentMap;
		this.customer = customer;
	}



	@Override
	public void run() {
		for (Entry<String, Student> entry : entryStudentCommittedPaymentMap.entrySet()) { 
			//log.debug(entry.getKey() + " : " + entry.getValue()); 
			if(!ObjectFunctions.isNullOrEmpty(entry.getValue()))
			{
				String key = entry.getKey();
				
				String[] keyArr =  key.split("#");
				
				String deductingAmntStr = keyArr[0].toString();
				String userIdStr = keyArr[1].toString();
				String hostAddress = keyArr[2].toString();
				
				Student studObject = (Student) entry.getValue();
				
				adminManager.entryStudentCommittedPayment(customer,studObject,Double.valueOf(deductingAmntStr),Long.valueOf(userIdStr),hostAddress);
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
