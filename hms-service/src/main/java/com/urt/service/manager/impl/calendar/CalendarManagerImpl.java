package com.urt.service.manager.impl.calendar;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urt.persistence.interfaces.calendar.CalendarDao;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.calendar.CalendarManager;


/**
 * Implementation of UserManager interface.</p>
 * 
 * <p>
 * <a href="UserManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class CalendarManagerImpl extends UniversalManagerImpl implements CalendarManager {
	
	  @Autowired
      private CalendarDao calendarDao;

	  //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	  public Set getGroupsEvents() {
	        return calendarDao.getGroupsEvents();
	  }

	  //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	  public List getCategoryByCustomerId(long custId){
		  return calendarDao.getCategoryByCustomerId(custId);
	  }
	  
	  //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	  public List getEventsWithoutCategory(long custId){
		  return calendarDao.getEventsWithoutCategory(custId);
	  }
	  //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	  public List getAllEventsByEventId(String eventPid){
		  return calendarDao.getAllEventsByEventId(eventPid);
	  }
	  
	  public void removeFromRecEvent(long eventPid){
		  calendarDao.removeFromRecEvent(eventPid);
	  }
	  
	  public void removeInvitedUsersByEventId(long eventId){
		  calendarDao.removeInvitedUsersByEventId(eventId);
	  }
}
