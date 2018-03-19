/********************************************************************
 * Copyright (C) 2007-08
 * IFS
 * All Rights Reserved 
 *
 * File: CampaignBatchJob.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Apr 28, 2008       Sreeram J          Created
/********************************************************************/

package com.urt.webapp.batch.trigger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.Messages;
import com.urt.service.manager.impl.base.MobileSMSDataTaskExecutor;
import com.urt.service.manager.interfaces.admin.AdminManager;



/**
 * <p>
 *  This job removes from the system all expired events and messages
 *  including churchwide messages/events
 * </p>cd 
 * 
 * @author  Ganesh
 */
public class MessagesGuidCodeBatchJob extends MobileSMSDataTaskExecutor implements Job {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6137644058516625485L;
	private static Log _log = LogFactory.getLog(MessagesGuidCodeBatchJob.class);
  //  private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
    /**
     * <p>
     * Empty constructor for job initilization
     * </p>
     * <p>
     * Quartz requires a public empty constructor so that the
     * scheduler can instantiate the class whenever it needs.
     * </p>
     */
    public MessagesGuidCodeBatchJob() {
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     * 
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
    /********************************************************************
     * Date              Name               Description
     * ========          ============       ==================
     * Dec 2, 2015       Ganesh		        Job for sending birth day wishes. 
    /********************************************************************/
	@Autowired
	private AdminManager adminManager;
	
    public void execute(JobExecutionContext context)throws JobExecutionException {
    	       
    	StringBuffer successMobileNos = new StringBuffer();
  	  	StringBuffer failedMobileNos = new StringBuffer();
  	  	int smscount=0;
    	String jobName = context.getJobDetail().getDescription();
        _log.info("MessagesGuidCodeBatchJob Batch Process Started : " + jobName + " executing at " + new Date());
		try {
			List<Messages> messageList = adminManager.getAll(Messages.class, "createdDate between DATE_ADD(NOW(), INTERVAL -1 HOUR)  and now()");
			if(!ObjectFunctions.isNullOrEmpty(messageList)){
				for(Messages message : messageList){
					if("MsgClub".equalsIgnoreCase(message.getSmsProviders().getServiceProvider())){
						 StringBuilder statusFromMsgClub = new StringBuilder();
						 URLConnection urlConn = null;
						 InputStreamReader inStreamReader = null;
						 _log.debug("MsgClub .......");
						 try {
							URL url = new URL("http://msg.msgclub.net/rest/services/delivery/pullDeliveryReport?AUTH_KEY=53b5b4f961b86fea1c3aeebbe5dfb6e&requestId="+message.getGuid()+"");
							urlConn = url.openConnection();
							if (urlConn != null)
								urlConn.setReadTimeout(60 * 1000);
							if (urlConn != null && urlConn.getInputStream() != null) {
								inStreamReader = new InputStreamReader(urlConn.getInputStream(),Charset.defaultCharset());
								BufferedReader bufferedReader = new BufferedReader(inStreamReader);
								if (bufferedReader != null) {
									int cp;
									while ((cp = bufferedReader.read()) != -1) {
										statusFromMsgClub.append((char) cp);
									}
									bufferedReader.close();
								}
							}
							inStreamReader.close();
						 } catch (Exception e) {
							  e.printStackTrace();
						 } 
						 Map<String, Character> mobileNumberStatusMap = new HashMap<String, Character>();
						 smscount = deliverySMSCountFromMsgClub(statusFromMsgClub.toString(),mobileNumberStatusMap);
						 message.setSmsCount(smscount);
						 for(MessageDetailsTracking detailsTracking : message.getMessageDetailsTracking()){
					    	if(mobileNumberStatusMap.containsKey(detailsTracking.getMobileNumber().replaceFirst("\\+91-", "")))
					    		detailsTracking.setDeliveryStatus(mobileNumberStatusMap.get(detailsTracking.getMobileNumber().replaceFirst("\\+91-", "")));
					    	message.addMessageDetails(detailsTracking);
						 }
						 message.setSuccessMobileNos(successMobileNos.toString());
						 message.setFailedMobileNos(failedMobileNos.toString());
						 adminManager.save(message);
					 }else if("ValueFirst".equalsIgnoreCase(message.getSmsProviders().getServiceProvider())){
						//Messages msg = (Messages)MobileSMSDataTaskExecutor.this.userDao.get(Messages.class, "guid='"+message.getGuid()+"'");
						//smscount = 
						sendStatusRequest(message,message.getSmsProviders().getUrl());
						File destFile = null;
						File file = null;
						if (StringFunctions.isNotNullOrEmpty(message.getGuid()) && StringFunctions.isNotNullOrEmpty(message.getSmsResXmlLocation())) {
						    destFile = new File(message.getSmsResXmlLocation());
						    if (destFile.mkdirs()) {
						    	_log.debug("Directories Created");
						    } else {
						    	_log.debug("Directories Not Created");
						    }
						    file = new File(message.getSmsResXmlLocation(), message.getGuid() + ".xml");
						}
						if(!ObjectFunctions.isNullOrEmpty(file)){
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(message.getResString());
							bw.close();
						}
					 }
					smscount=0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	_log.info("MobileAndEmailRemaindersBatchJob Batch Completed successfully.....");
    }
}