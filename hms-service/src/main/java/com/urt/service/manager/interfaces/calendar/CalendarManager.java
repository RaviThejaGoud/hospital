package com.urt.service.manager.interfaces.calendar;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="UserManager.java.html"><i>View Source</i></a></p>
 */
public interface CalendarManager extends UniversalManager {
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class )
    Set getGroupsEvents();
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class )
    List getCategoryByCustomerId(long custId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class )
    List getEventsWithoutCategory(long custId);
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class )
    List getAllEventsByEventId(String eventPid);
    
    void removeFromRecEvent(long eventPid);
    
    void removeInvitedUsersByEventId(long eventId);
}
