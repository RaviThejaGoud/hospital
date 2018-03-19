package com.urt.webapp.action.jrexception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.spring.context.SpringContextAware;
import com.urt.webapp.action.base.BaseAction;

public class JRExceptionClient extends BaseAction {

	private static final long serialVersionUID = -2879755898681387807L;

	public void sendException(final Throwable throwable){
		try{
			if(!ObjectFunctions.isNullOrEmpty(throwable)) {
				final StringWriter sw = new StringWriter();
				final PrintWriter pw = new PrintWriter(sw, true);
				throwable.printStackTrace(pw);
				ThreadPoolTaskExecutor jrExceptionExecutor = (ThreadPoolTaskExecutor) SpringContextAware.getBean("taskExecutor");
				JRExceptionThread jrExceptionThread = new JRExceptionThread();
				if(!ObjectFunctions.isNullOrEmpty(getUser())) {
					jrExceptionThread.setUserId(getUser().getId());
					jrExceptionThread.setAcademicYearId((int)getUserAcademicYearId());
					jrExceptionThread.setCustId((int)getUserCustId());
				}
				jrExceptionThread.setFileName(throwable.getStackTrace()[0].getFileName());
				jrExceptionThread.setClassName(throwable.getStackTrace()[0].getClassName());
				jrExceptionThread.setMethodName(throwable.getStackTrace()[0].getMethodName());
				jrExceptionThread.setExceptionLineNumber(throwable.getStackTrace()[0].toString());
				if(!ObjectFunctions.isNullOrEmpty(throwable.getCause())) {
					jrExceptionThread.setCause(throwable.getCause().toString());
				}
				jrExceptionThread.setExceptionName(throwable.toString());
				jrExceptionThread.setIpAddress(getUserIpAddress());
				jrExceptionThread.setComputerName(getUserComputerName());
				jrExceptionThread.setComputerUsername(getUserComputerUsername());
				jrExceptionThread.setStatus(Constants.PENDING_STATUS);
				jrExceptionThread.setExceptionDescription(sw.getBuffer().toString());
				jrExceptionExecutor.execute(jrExceptionThread);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}