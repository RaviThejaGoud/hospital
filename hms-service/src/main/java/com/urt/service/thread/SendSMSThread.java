package com.urt.service.thread;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.service.manager.interfaces.user.CommunicationManager;

public class SendSMSThread  implements Runnable{
	private Thread t;
	private Map<Messages, Set<String>> messageMobileMap;
	private long custId;
	private SMSServiceProviders smsServiceProvider;
	
	@Autowired
	public CommunicationManager communicationManager;
	
	public SendSMSThread(Map<Messages, Set<String>> messageMobileMap,long custId,SMSServiceProviders smsServiceProvider) {
		super();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.messageMobileMap = messageMobileMap;
		this.custId = custId;
		this.smsServiceProvider = smsServiceProvider;
	}



	@Override
	public void run() {
		for (Entry<Messages, Set<String>> entry : messageMobileMap.entrySet()) { 
			//log.debug(entry.getKey() + " : " + entry.getValue()); 
			if(!ObjectFunctions.isNullOrEmpty(entry.getValue()))
				communicationManager.deliverSms(entry.getKey(),entry.getValue(), smsServiceProvider);
			
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
