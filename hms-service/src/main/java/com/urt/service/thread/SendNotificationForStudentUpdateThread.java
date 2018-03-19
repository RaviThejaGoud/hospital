package com.urt.service.thread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.study.Student;
import com.urt.service.manager.interfaces.student.StudentManager;

public class SendNotificationForStudentUpdateThread  implements Runnable{
	private Thread t;
	List<Student> studentObjList;
	
	
	@Autowired
	public StudentManager studentManager;
	
	public SendNotificationForStudentUpdateThread(List<Student> studentObjList) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.studentObjList = studentObjList;
	}



	@Override
	public void run() {
		for (Student studObject : studentObjList) { 
			//log.debug(entry.getKey() + " : " + entry.getValue()); 
			if(!ObjectFunctions.isNullOrEmpty(studObject))
			{
				studentManager.sendNotificationForStudentUpdate(studObject);
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
