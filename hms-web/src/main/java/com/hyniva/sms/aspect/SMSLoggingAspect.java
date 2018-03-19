/********************************************************************
 * HYNIVA
 * All Rights Reserved 
 *
 * File: SMSLoggingAspect.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  0.1   June 27, 2015     Sreeram		       Initial Version
/ ********************************************************************/
package com.hyniva.sms.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

import com.hyniva.sms.api.exception.SMSAPIException;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.exception.base.URTUniversalException;

@Aspect
public class SMSLoggingAspect {

	static Logger log = Logger.getLogger(SMSLoggingAspect.class);

	@Pointcut("execution(public * com.hyniva.sms.api..*.*(..)) "
			+ "||execution(public * com.urt..*.*(..))  ")
	
	public void aspectjLoadTimeWeaving() {

	}

	@Around("aspectjLoadTimeWeaving()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch(getClass().getSimpleName());
		try {
			sw.start(pjp.getSignature().getName());
			return pjp.proceed();
		} finally {
			sw.stop();
			log.info(sw.getLastTaskName() + " running in seconds :: "
					+ sw.getTotalTimeSeconds());
		}
	}

	@Before("execution(public * com.hyniva.sms..*.*(..))")
	public void doBefore(JoinPoint jp) {
		log.info("Before entering : "
				+ jp.getTarget().getClass().getSimpleName() + "."
				+ jp.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(public * com.hyniva.sms..*.*(..))", returning = "result")
	public void doAfterReturning(JoinPoint jp, Object result) {
		log.info(jp.getTarget().getClass().getSimpleName() + "."
				+ jp.getSignature().getName() + " execution completed!");
		log.info(jp.getSignature().getName() + " return values : "
				+ result.toString());
	}

	@Before("execution(public * com.hyniva.sms.api..*.*(..))&&"+"args(academicYearId,studyClassId,..)")
	public void doBefore(JoinPoint jp,Long academicYearId, Long studyClassId) {
		log.info("Before entering : "
				+ jp.getTarget().getClass().getSimpleName() + "."
				+ jp.getSignature().getName() + "academicYearId:"+academicYearId+", studyClassId:"+studyClassId);
	}
	
	@AfterThrowing(pointcut = "execution(public * com.hyniva.sms.api..*.*(..))", throwing = "error")
	public void doAfterThrowing(JoinPoint jp, Throwable error) {
		
		log.error(jp.getTarget().getClass().getSimpleName() + "."
				+ jp.getSignature().getName() + " exception :");
		if(null!=(jp.getArgs()[0])){
			if(((jp.getArgs()[0]) instanceof SMSBaseVO)){
				log.error("CustId:"+((SMSBaseVO)jp.getArgs()[0]).getIdentifier().getCustId());
				log.error("Academic Year Id:"+((SMSBaseVO)jp.getArgs()[0]).getIdentifier().getAcademicYearId());
				log.error("AccountId:"+((SMSBaseVO)jp.getArgs()[0]).getIdentifier().getAccountId());
			}
		}
		log.error("Error Message:"+error.getMessage());
		if(error instanceof SMSAPIException){
			log.error("Status:"+((SMSAPIException)error).getStatus());
			
			if(error instanceof SMSAPIException){
				SMSAPIException blbEx = (SMSAPIException)error;
				if(null!=blbEx.getStatus()){
				log.error("Status:"+blbEx.getStatus());
				}else{
					log.error("Status:"+blbEx.getCause());
				}
				log.error("Error Message:"+blbEx.getField());
			}else	if(error instanceof URTUniversalException){
				URTUniversalException blEx = (URTUniversalException)error;
				
				log.error("Error Message:"+blEx.getMessage());
				log.error("Error:",error);
			}else{
				log.error("Error Message:"+error.getMessage());
				log.error("Error:",error);
			}
		}
		log.error("Error:",error);
		
		
	}
	



}