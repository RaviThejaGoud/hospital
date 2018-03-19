package com.urt.persistence.interfaces.calendar;



import java.util.List;
import java.util.Set;

import com.urt.persistence.interfaces.base.UniversalDao;

/**
 * User Data Access Object (Dao) interface.
 *
 * <p>
 * <a href="UserDao.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface CalendarDao extends UniversalDao {
    
	Set getGroupsEvents() ;

	List getCategoryByCustomerId(long custId);
	List getEventsWithoutCategory(long custId);
	
	List getAllEventsByEventId(String eventPid);
	
	void removeFromRecEvent(long eventPid);
	
	void removeInvitedUsersByEventId(long eventPid);
	
}
