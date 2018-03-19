package com.urt.service.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.util.jrexception.JRExceptionClient;
public class ProcessStudentsSubjectwisePosition  implements Runnable {
	protected transient final Log log = LogFactory.getLog(getClass());
	@Autowired
	AdminManager adminManager;
	List<Object[]> studentObtainedMarks;
	private long classSectionId;
	private long examTypeId;
	
	
	
	
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}
	/**
	 * @return the examTypeId
	 */
	public long getExamTypeId() {
		return examTypeId;
	}
	/**
	 * @param examTypeId the examTypeId to set
	 */
	public void setExamTypeId(long examTypeId) {
		this.examTypeId = examTypeId;
	}
	public ProcessStudentsSubjectwisePosition() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	public void run(){
		try{
		//	StringBuffer query = new StringBuffer();
			synchronized(ProcessStudentsSubjectwisePosition.class){
				adminManager.calculateStudentsSubjectPosition(getClassSectionId(),getExamTypeId());
			}
			log.debug("Thread Process completed successfully.");
		}
		catch(Exception ex){
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}	      
    }
	
}
