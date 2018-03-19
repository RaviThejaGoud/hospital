package com.urt.service.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.study.ClassAssignment;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.util.email.MailUtil;

public class SendAssignmentEmailThread  implements Runnable{
	
	private static final Log log = LogFactory.getLog(SendAssignmentEmailThread.class);
	private Thread t;
	
	
	private List<Object> studentsContactDetails;
	private Customer customer; 
	private ClassAssignment classAssignment;
	private String[] fileUploadStr;
	
	
	@Autowired
	public CommunicationManager communicationManager;
	
	@Autowired
	public AdminManager adminManager;
	
	public SendAssignmentEmailThread(List<Object> studentsContactDetails, Customer customer, ClassAssignment classAssignment,String[] fileUploadStr) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.studentsContactDetails = studentsContactDetails;
		this.customer = customer;
		this.classAssignment = classAssignment;
		this.fileUploadStr = fileUploadStr;
	}



	@Override
	public void run() {
		
		log.debug("starting the thread");
		if(!ObjectFunctions.isNullOrEmpty(studentsContactDetails))
		{
			MailUtil.SendAttachmentFilesToParentAndStudent(studentsContactDetails , customer, classAssignment,fileUploadStr,adminManager.getContactFromEmail(customer));
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
