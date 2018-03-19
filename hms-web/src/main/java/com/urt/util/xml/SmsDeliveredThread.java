package com.urt.util.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.object.ObjectFunctions;
import com.spring.context.SpringContextAware;
import com.urt.persistence.model.common.Messages;
import com.urt.service.manager.interfaces.user.UserManager;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class SmsDeliveredThread  implements Runnable {
	protected transient final Log log = LogFactory.getLog(getClass());
	private String hostUrl;
	private File file;
	private String guid;
	
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	
	
	public SmsDeliveredThread(String hostUrl, String guid) {
		super();
		this.hostUrl = hostUrl;
		this.guid = guid;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public SmsDeliveredThread() {
		
	}
	public void run(){
		try{
			UserManager userManager= (UserManager)SpringContextAware.getBean("userManager");
        	int smscount = 0;
			try {
			Thread.sleep(1000*60*5);
			synchronized (this) {
				Messages msg = (Messages)userManager.get(Messages.class, "guid='"+this.guid+"'");
				if(!ObjectFunctions.isNullOrEmpty(msg))
				{
					smscount = SendSms.sendStatusRequest(msg,this.hostUrl);
					/*if(smscount!=0){
						msg.setSmsCount(smscount);
						msg.setDeliveredSmsStatus(true);
						userManager.save(msg);
					}*/
					msg.setSmsCount(smscount);
					msg.setDeliveredSmsStatus(true);
					userManager.save(msg);
					if(!ObjectFunctions.isNullOrEmpty(file)){
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(msg.getResString());
						bw.close();
					}
				}
				msg = null;
			}
			Thread.interrupted();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}	      
    }
	
}
