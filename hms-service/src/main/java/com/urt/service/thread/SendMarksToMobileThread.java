package com.urt.service.thread;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.urt.persistence.model.common.AcademicYear;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;

public class SendMarksToMobileThread  implements Runnable{
	private Thread t;
	//private Map<Messages, Set<String>> messageMobileMap;
	private HashMap keys;
	private AcademicYear academicYear;
	private String studentIds;
	
	
	@Autowired
	public CommunicationManager communicationManager;
	
	@Autowired
	public AdminManager adminManager;
	
	public SendMarksToMobileThread(HashMap keys, AcademicYear academicYear,String studentIds) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.keys = keys;
		this.academicYear = academicYear;
		this.studentIds = studentIds;
	}


	@Override
	public void run() {
		communicationManager.sendMarksToUsers(keys, academicYear,studentIds);
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
