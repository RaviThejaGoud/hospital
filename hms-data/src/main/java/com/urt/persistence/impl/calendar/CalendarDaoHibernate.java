package com.urt.persistence.impl.calendar;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.calendar.CalendarDao;


@Transactional
public class CalendarDaoHibernate extends UniversalHibernateDao implements CalendarDao
{
	//private static final Log log = LogFactory.getLog(CalendarDaoHibernate.class);
	
	  public Set getGroupsEvents() {
	        StringBuffer queryString = new StringBuffer();
	        queryString.append("from Attendance ");	        
	        return executeQuery(queryString.toString());
	    }
	  public Set executeQuery(String query) 
	    {
	        List oList = new ArrayList();
	        try
	        {
	            oList = this.getAllHqlQuery(query);
	            if (ObjectFunctions.isNullOrEmpty(oList)) {
	                return null;
	                //throw new DataNotFoundException("No Data found for the given query.");
	            }
	        }
	        catch(Exception ex)
	        {
	            ex.printStackTrace();
	        }
	        return ConvertUtil.convertListToSet(oList);
	    }
		
	  public List getCategoryByCustomerId(long custId){
		  try{
				if(custId!=0){
					return this.getAllHqlQuery("from Category where custId="+custId);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
		}
	  
	  public List getEventsWithoutCategory(long custId){
		  try{
	    		return this.getAllHqlQuery("from Attendance where custId="+custId+ " and categoryId is null");
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }
	  
	  public List getAllEventsByEventId(String eventPid){
		  try{
	    		return this.getAllHqlQuery("from RecEvent where eventPid in"+eventPid);
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }
	  
	  public void removeFromRecEvent(long eventPid){
		  try{
			    StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("delete from classEvent where eventId=");
				queryBuff.append(eventPid);
				@SuppressWarnings("unused")
				int deletedCount=getSession().createSQLQuery(queryBuff.toString()).executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
	    }
	  public void removeInvitedUsersByEventId(long eventId){
		  try{
			    StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("delete from eventInvitedUser where eventId=");
				queryBuff.append(eventId);
				int deletedCount=getSession().createSQLQuery(queryBuff.toString()).executeUpdate();
				queryBuff = null;
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
	    }
	  
}
