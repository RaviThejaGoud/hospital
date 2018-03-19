package com.urt.service.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.urt.service.manager.interfaces.admin.AdminManager;

public class StudentMarksNotificationThread  implements Runnable{
	private Thread t;
	private long custId;
	
	
	@Autowired
	public AdminManager adminManager;
	
	public StudentMarksNotificationThread(long custId) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.custId = custId;
	}


	@Override
	public void run() {
		adminManager.sendNotificationForStudentMarks(custId);
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
