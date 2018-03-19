/********************************************************************
* HYNIVA
* All Rights Reserved 
*
* File: WebUserMetaData.java
********************************************************************
*
*  Ver   Date            Author                Description
*  ====  ========        ============          ==================
*  0.1   Sep 10, 2015    Sreeram			   Created
/********************************************************************/
package com.urt.service.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.urt.persistence.model.common.LoginUserMetaData;
import com.urt.service.manager.interfaces.user.UserManager;

public class WebUserMetaDataTaskExecutor {

	@Autowired
	public UserManager userManager;
	
	private TaskExecutor taskExecutor;

	/**
	 * @param taskExecutor the taskExecutor to set
	 */
	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	
	  
	private class LoginUserMetaDataTask implements Runnable {

		private LoginUserMetaData luMetaData;
	    public LoginUserMetaDataTask(LoginUserMetaData luMetaData ) {
	          this.luMetaData =luMetaData;
	    }
	    
	    public void run() {
	    	WebUserMetaDataTaskExecutor.this.userManager.save(luMetaData);
	    }
	  }

	  public void execute(LoginUserMetaData luMetaData ) {
		  taskExecutor.execute(new LoginUserMetaDataTask(luMetaData));
	  }
}