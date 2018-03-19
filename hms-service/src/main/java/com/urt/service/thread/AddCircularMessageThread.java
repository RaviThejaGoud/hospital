package com.urt.service.thread;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.util.common.RayGunException;
import com.urt.util.jrexception.JRExceptionClient;

public class AddCircularMessageThread  implements Runnable{
	private Thread t;
	private Messages messages;
	private AcademicYear academicYear;
	private User user;
	private long custId;
	private List<String> alertTypeIds;
	private List<String> userAcountIds;
	List<String> classIds;
	
	@Autowired
	public CommunicationManager communicationManager;
	
	public AddCircularMessageThread(Messages messageObj, long userCustId, AcademicYear academicYearObj,User userObj, List<String> alertTypeIds, List<String> userAcountIds, List<String> classIds) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.messages = messageObj;
		this.academicYear = academicYearObj;
		this.user = userObj;
		this.custId = userCustId;
		this.alertTypeIds = alertTypeIds;
		this.userAcountIds = userAcountIds;
		this.classIds = classIds;
	}
	
   @Override
   public void run() 
   {
		try {
			getCircularMessageResponse();
		} catch (Exception e) {
			e.printStackTrace();
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
   
   public Map<String,String> getCircularMessageResponse()
 	{   Map<String,String> messageInfo=null;
		try {
			messageInfo = communicationManager.sendSchoolWideMessages(messages, custId, academicYear, user, alertTypeIds, userAcountIds, classIds, null, null, null, null, null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return messageInfo;
 	}
}
