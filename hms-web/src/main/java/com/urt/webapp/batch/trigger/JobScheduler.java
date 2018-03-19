/********************************************************************
 * Copyright (C) 2007-08
 * IFS
 * All Rights Reserved 
 *
 * File: JobScheduler.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Apr 28, 2008       Sreeram J          Created
/********************************************************************/

package com.urt.webapp.batch.trigger;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

/**
 * This is the primary scheduler for GI, schedules all the bactch jobs
 * to run between 10PM - 4AM.
 * 
 * @author Sreeramulu J
 */
public class JobScheduler {

    	 public void run() throws Exception {
    	        final Log log = LogFactory.getLog(CampaignBatchJob.class);

    	        log.info("------- Initializing ----------------------");

    	        // First we must get a reference to a scheduler
    	        SchedulerFactory sf = new StdSchedulerFactory();
    	        Scheduler sched = sf.getScheduler();

    	        log.info("------- Initialization Complete -----------");

    	        log.info("------- Scheduling Jobs -------------------");

    	        // Add the holiday calendar to the schedule
    	        AnnualCalendar holidays = new AnnualCalendar();

    	        // fourth of July (July 4)
    	        Calendar fourthOfJuly = new GregorianCalendar(2005, 6, 4);
    	        holidays.setDayExcluded(fourthOfJuly, true);
    	        // halloween (Oct 31)
    	        Calendar halloween = new GregorianCalendar(2005, 9, 31);
    	        holidays.setDayExcluded(halloween, true);
    	        // christmas (Dec 25)
    	        Calendar christmas = new GregorianCalendar(2005, 11, 25);
    	        holidays.setDayExcluded(christmas, true);

    	        // tell the schedule about our holiday calendar
    	        sched.addCalendar("holidays", holidays, false, false);
    	        

    	        // schedule a job to run hourly, starting on halloween
    	        // at 10 am
    	        
    	 //       Date runDate = TriggerUtils.getDateOf(0,0, 2, 12, 02);
    	  //      JobDetail job = new JobDetail("batchJob", "giBatchJob", CampaignBatchJob.class);
    	       /* SimpleTrigger trigger = new SimpleTrigger("giBatchJobTrigger", "giBatchJob");
    	        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    	        // 24 hours * 60(minutes per hour) * 60(seconds per minute) * 1000(milliseconds per second)
    	        trigger.setRepeatInterval(1000L * 60L * 60L * 24L);
    	        
    	       
    	        // tell the trigger to obey the Holidays calendar!
    	        trigger.setCalendarName("holidays");
    	        
    	        // schedule the job and print the first run date
    	        Date firstRunTime = sched.scheduleJob(job, trigger);
    	        
    	        // print out the first execution date.
    	        // Note:  Since Halloween (Oct 31) is a holiday, then
    	        // we will not run unti the next day! (Nov 1)
    	        log.info(job.getFullName() +
    	                " will run at: " + firstRunTime +  
    	                " and repeat: " + trigger.getRepeatCount() + 
    	                " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");*/
    	        
    	        // All of the jobs have been added to the scheduler, but none of the jobs
    	        // will run until the scheduler has been started
    	        log.info("------- Starting Scheduler ----------------");
    	        sched.start();
    	   //     SchedulerMetaData metaData = sched.getMetaData();
    	       // log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");

    }
}