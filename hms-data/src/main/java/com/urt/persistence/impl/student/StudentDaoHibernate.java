package com.urt.persistence.impl.student;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.StudentVo;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.student.StudentDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentQuestionAnswer;
import com.urt.persistence.model.study.StudentQuizResults;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.TimeTable;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentsTCDetails;
import com.urt.persistence.model.transport.ViewStaffVehicleTripdetails;
import com.urt.util.common.RayGunException;

@Transactional
public class StudentDaoHibernate extends UniversalHibernateDao implements StudentDao
{
	private static final Log log = LogFactory.getLog(StudentDaoHibernate.class);

	public List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId,long academicYearId)
	{
		try {
            List leavesList = this.getAllHqlQuery("from Leave where leaveStatus ='" +leaveStatus+ "' and accountId = " + accountId +" and custId = "+customerId+" and academicYearId="+academicYearId);
            if(!ObjectFunctions.isNullOrEmpty(leavesList))
            {
            	return (List<Leave>) leavesList;
            }else{
            	return null;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public Student getStudentById(long accountId){
		try
		{
            StringBuffer queryString = new StringBuffer();
            queryString.append("from Student where ");
            queryString.append(" accountId ='");
            queryString.append(accountId);
            queryString.append("'");
            List studentDetails =  this.getAllHqlQuery(queryString.toString());
            
            if(!ObjectFunctions.isNullOrEmpty(studentDetails))
            {
                return (Student) studentDetails.get(0);
            }
            return null;        	
		}
		catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	}
	public List getClassEventListByClassId(long classId) {
		try {
            List classEventList = (List) this.getAll("select * from classEvent where classId = "+classId);
            if(!ObjectFunctions.isNullOrEmpty(classEventList))
            {
            	return classEventList;
            }else{
            	return null;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getAllCalendarEventsByEventIds(String eventsIds,long customerId,long academicYearId) {
		try 
		{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from CalendarEvent where ( eventBelongs='ToALL'  ");
	           if(!StringFunctions.isNullOrEmpty(eventsIds))
	           {
		           queryString.append("or id in ");
		           queryString.append(eventsIds);
	           }
	           queryString.append(") and custId=");
	           queryString.append(customerId);
	           queryString.append(" and academicYearId=");
	           queryString.append(academicYearId);
	           queryString.append(" and endDate >='");
	           queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
	          // queryString.append(" 11:59:59'");
	           queryString.append("'");
	           List calendarEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(calendarEventsList)) {
	               return null;
	           } else {
	               return calendarEventsList;
	           }
			
			
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getEventInvitedUserListByEventId(String eventId) {

		try 
		{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from EventInvitedUser where eventId = ");
	           queryString.append(Long.valueOf(eventId));
	           //queryString.append(" and endDate >='");
	           //queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
	           //queryString.append(" 11:59:59'");
	           //queryString.append(" '");
	           List eventInvitedUserList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(eventInvitedUserList)) {
	               return null;
	           } else {
	               return eventInvitedUserList;
	           }
			
			
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	
	}
	
	public List getEventAcceptedEventsByEventIdAndAccountId(String eventIds, long accountId,String eventAccepted) {

		try 
		{
			
	           StringBuffer sqlString = new StringBuffer();
	           sqlString.append("select * from eventInvitedUser where accountId = ");
	           sqlString.append(accountId);
	           sqlString.append(" and eventAccepted='");
	           sqlString.append(eventAccepted);
	           sqlString.append("'");
	           if(!StringFunctions.isNullOrEmpty(eventIds))
	           {
	        	   sqlString.append(" and eventId in");
	        	   sqlString.append(eventIds);
	           }
	           List eventInvitedUserList = (List) this.getAll(sqlString.toString());
	           //List eventInvitedUserList = (List) this.getAll("select * from eventInvitedUser where accountId="+accountId+" and eventAccessed='"+ eventAccessed+"' eventId in " +eventIds);
	           if(!ObjectFunctions.isNullOrEmpty(eventInvitedUserList))
	           {
	               return  eventInvitedUserList;
	           }
	            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getRemainingCalendarEventsByEventIds(String eventsIds,long customerId) {
		try 
		{
			
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from CalendarEvent where id in ");
	           queryString.append(eventsIds);
	           queryString.append(" and custId=");
	           queryString.append(customerId);
	           queryString.append(" and endDate >='");
	           queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
	           queryString.append(" 11:59:59'");
	           //queryString.append(" '");
	           List calendarEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(calendarEventsList)) {
	               return null;
	           } else {
	               return calendarEventsList;
	           }
			
			
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public void cancelRegistrationStudentEvent(long eventId, long accountId) {

        try {
            StringBuffer sqlString = new StringBuffer();
            sqlString.append("delete from EventInvitedUser where eventId =");
            sqlString.append(eventId);
            sqlString.append(" and  accountId =");
            sqlString.append(accountId);
            int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
        } catch (Exception ex) {
            log.error("delete cancelRegistrationStudentEvent failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
	}
	
	public List getEventsByDate(String eventDate) {
		try 
		{
			
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from CalendarEvent where startDate='");
	           queryString.append(eventDate);
	           queryString.append("'");
	           List studentEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
	               return null;
	           } else {
	               return studentEventsList;
	           }
			
			
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public StudyClass getStudentSubjects(String className,String section)
	{
		try {
	        StringBuffer queryString = new StringBuffer();
	        queryString.append("from StudyClass where className = '");
	        queryString.append(className);
	        queryString.append("' and section='");
	        queryString.append(section);
	        queryString.append("'");
	        //queryString.append("");
	        List subjectList = this.getAllHqlQuery(queryString.toString());
        if(!ObjectFunctions.isNullOrEmpty(subjectList))
         {
             return (StudyClass) subjectList.get(0);
         }} catch (Exception ex) {
        	 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	public Student getStudentByAccountId(long accountId,long academicYearId,long custId)
	{
		try {			
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from Student where accountId = ");
	           queryString.append(accountId);
	           queryString.append(" and custId=");
	           queryString.append(custId);
	           queryString.append(" and academicYearId='");
	           queryString.append(academicYearId);
	           queryString.append("' and description is null");
	           List studentList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studentList))
	            {
	                return (Student) studentList.get(0);
	            }
 
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List<ViewStudentPersonAccountDetails> getMyChildren(long parentId, long custId, long academicYearId,String status){
		try
		{
		   return (List<ViewStudentPersonAccountDetails>) this.getAllHqlQuery("from ViewStudentPersonAccountDetails where parentId='"+parentId+"' and academicYearId='"+academicYearId+"' and custId="+custId+" and status='"+status+"'");
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return null;
		}
	}
	public Fee getFeeByStudentId(long classId)
	{
		try
		{
			StringBuffer queryString = new StringBuffer();
			queryString.append("from Fee where classId=");
			queryString.append(classId);
	           List feeList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(feeList))
	            {
	                return (Fee) feeList.get(0);
	            }
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewStaffVehicleTripdetails getStaffVehicleTripByTripId(long vehicleTripId)
	{
		try {			
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStaffVehicleTripdetails where vehicleTripId = ");
	           queryString.append(vehicleTripId);
	           //queryString.append("");
	           List vehicleTripList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(vehicleTripList))
	            {
	                return (ViewStaffVehicleTripdetails) vehicleTripList.get(0);
	            }
 
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	/*public StudyClass getStudyClassByClassName(String className) 
	{
		try {			
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from StudyClass where className = '");
	           queryString.append(className);
	           queryString.append("'");
	           List studyClassList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studyClassList))
	            {
	                return (StudyClass) studyClassList.get(0);
	            }
 
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}*/
/*	public StudentPayment getPaymentStatusByStudentId(long studentId) {
		List studentPaymentList = this.getAllHqlQuery("from StudentPayment where studentId=" + studentId);
		if (!ObjectFunctions.isNullOrEmpty(studentPaymentList)) {
			StudentPayment studentPayment = (StudentPayment) studentPaymentList.get(0);
			return studentPayment;
		} else {
			return null;
		}

	}*/
	
	public List getStudentAttendanceByStudentId(long studentId){
		try
		{
		   return this.getAllHqlQuery("from Attendance where studentId='"+studentId+"'");
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return null;
		}
	}
	
	public TimeTable getTimeTableByClassAndSec(String className, String sectionName, String dayName){
		try{
			 StringBuffer queryString = new StringBuffer();
	           queryString.append("from TimeTable where className ='");
	           queryString.append(className);
	           queryString.append("'");
	           queryString.append(" and section ='");
	           queryString.append(sectionName);
	           queryString.append("'");
	           queryString.append(" and weekDay='");
	           queryString.append(dayName);
	           queryString.append("'");
	           List timeTableList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(timeTableList))
	            {
	                return (TimeTable) timeTableList.get(0);
	            }
		}
		catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getViewStudentLeaveDetailsByAccountId(long accountId,String leaveStatus,long customerId)
	{
		try {
            List leavesList = this.getAllHqlQuery("from ViewStudentLeaveDetails where leaveStatus ='" +leaveStatus+ "' and accountId = " + accountId +" and custId = "+customerId);
            if(!ObjectFunctions.isNullOrEmpty(leavesList))
            {
            	return leavesList;
            }else{
            	return null;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	public List<ViewStudentMarks> getAllMarksByStudId(long studentId, long academicYearId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
          // queryString.append("from ViewStudentMarksDetails where studId=");
           queryString.append("from ViewStudentMarks where studId=");
           queryString.append(studentId);
           queryString.append(" and academicyearId="+academicYearId+" group by subjectId");
           List studentMarksList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentMarksList)) {
               return null;
           } else {
               return (List<ViewStudentMarks>) studentMarksList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	public List getAllStudentsBirthDays(long classId,long accountId) {
		
		try{
			StringBuffer query = new StringBuffer("select accountId,dateOfBirth from vw_studentClassDetails where classSectionId ='" +classId+ "'and accountId !='"+accountId+"'");
			 return getSession().createSQLQuery(query.toString())
			 .addScalar("accountId",StandardBasicTypes.LONG).addScalar("dateOfBirth",StandardBasicTypes.DATE)
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentClassDetails.class)).list()
			  ;
		}
	
		 catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getMyMessagesByReceiverAccountIdAndStatus(String receiverAccountId, long customerId,String status) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from Messages where receiverAccountId='");
           queryString.append(receiverAccountId);
    	   queryString.append("' and custId = ");
           queryString.append(customerId);
			if(!StringFunctions.isNullOrEmpty(status))
	        {
	           queryString.append(" and status = '");
	           queryString.append(status);
	        }
           queryString.append("'");
           List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return studentEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	public List getMyMessagesByReceiverAccountId(String receiverAccountId,
			long customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	 public Messages updateStudentsReadMessages(String msgId,String newStatus){
		    	try{
			               StringBuffer sqlString=new StringBuffer();
				           sqlString.append("update messages set isNewMessage ='"); 
				           sqlString.append(newStatus);
				           sqlString.append("' where id="+msgId);
				           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
				            if(row == 0)
				            {
				                log.debug("The no of rows Updated:"+ row);
				            }
				}
				catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
				}
				return null;
		    }
	 public List getMyUnreadMessagesAndStatus(String receiverAccountId, long customerId,String status) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from Messages where receiverAccountId='");
	           queryString.append(receiverAccountId);
	    	   queryString.append("' and custId = ");
	           queryString.append(customerId);
				if(!StringFunctions.isNullOrEmpty(status))
		        {
		           queryString.append(" and isNewMessage = '");
		           queryString.append(status);
		        }
	           queryString.append("'");
	           List studentEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
	               return null;
	           } else {
	               return studentEventsList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
	public List<Messages> getAllNoticeBoardMessagesList(String status, String className,long customerId,long academicYearId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from Messages where status='");
           queryString.append(status);
    	   queryString.append("' and custId = ");
           queryString.append(customerId);
           queryString.append(" and academicYearId = ");
           queryString.append(academicYearId);
           queryString.append(" and receiverAccountId = 'ToALL");
           if(!StringFunctions.isNullOrEmpty(className))
	       {
        	   queryString.append("' or receiverAccountId like '%");
        	   queryString.append(className);
	       }
           queryString.append("%'");
           log.debug("queryString.toString():"+queryString.toString());
           List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return (List<Messages>) studentEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	 public List getMyreplyMessagesAndStatus(String receiverAccountId, long customerId,String status) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from Messages where receiverAccountId='");
	           queryString.append(receiverAccountId);
	    	   queryString.append("' and custId = ");
	           queryString.append(customerId);
				if(!StringFunctions.isNullOrEmpty(status))
		        {
		           queryString.append(" and isReplyId = '");
		           queryString.append(status);
		        }
	           queryString.append("'");
	           List studentEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
	               return null;
	           } else {
	               return studentEventsList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
	 public List getViewreplyMessagesAndStatus(String status, long customerId,String msgParentId) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from Messages where isReplyId='");
	           queryString.append(status);
	    	   queryString.append("' and custId = ");
	           queryString.append(customerId);
				if(!StringFunctions.isNullOrEmpty(status))
		        {
		           queryString.append(" and replyMessageParentId= '");
		           queryString.append(msgParentId);
		        }
	            queryString.append("'");
	           List studentEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
	               return null;
	           } else {
	               return studentEventsList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
		public Messages getMessagesByAccountId(String msgAccountId) {
			try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("from Messages where receiverAccountId='");
			queryString.append(msgAccountId);
			 queryString.append("' or senderAccountId = '");
	         queryString.append(msgAccountId);
	         queryString.append("'");
	           List studentMessageList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studentMessageList))
	            {
	                return (Messages) studentMessageList.get(0);
	            }
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;

		}
		
	public List getMyMessagesBySenderAccountIdAndStatus(String senderAccountId, long customerId,String status) {
				try 
				{
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from Messages where senderAccountId='");
		           queryString.append(senderAccountId);
		    	   queryString.append("' and custId = ");
		           queryString.append(customerId);
					if(!StringFunctions.isNullOrEmpty(status))
			        {
			           queryString.append(" and isReplyId = '");
			           queryString.append(status);
			        }
		           queryString.append("'");
		           List studentEventsList = this.getAllHqlQuery(queryString.toString());
		           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
		               return null;
		           } else {
		               return studentEventsList;
		           }
		        } catch (Exception ex) {
		        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		        }return null;
			}
	/*public List getAllHolidayBoardMessagesList(String attendanceStatus,long customerId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from SchoolHolidays where status='");
           queryString.append(attendanceStatus);
    	   queryString.append("' and custId = ");
           queryString.append(customerId);           
           queryString.append("'");
           List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return studentEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}*/
	/*public List getAllStudentMarksByScheduleIds(String sheduleIds,long studId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from StudentMarks where examScheduleId in ");
           queryString.append(sheduleIds);
           queryString.append("and studId=");
           queryString.append(studId);
           List marksList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(marksList))
           {
               return  marksList;
           }
            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}*/
	
	/*public List getAllStudentMarksByTypeAndClassandstudId(long studId,String examtype){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStudentMarksDetails where examTypeId=");
           queryString.append(examtype);
           queryString.append(" and studId=");
           queryString.append(studId);
           List marksList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(marksList))
           {
               return  marksList;
           }
            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}*/
	public List getStudentMarksUploadList(long classId,String academicYear){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStudentMarksDetails where classSectionId=");
           queryString.append(classId);
           queryString.append(" and academicYearId='");
           queryString.append(academicYear);
           queryString.append("' group by lastUpdatedDate");
           List marksList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(marksList))
           {
               return  marksList;
           }
            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	/*public List getMyFeeDetailsByStudentId(long studentId)
	{
		try
		{
			StringBuffer queryString = new StringBuffer();
			queryString.append("from ViewStudentClassFeePaymentDetails where ");
			queryString.append(" studentId =");
			queryString.append(studentId);
			queryString.append(" and (paymentStatus='P')");
	        List feePaymentList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(feePaymentList))
            {
                return feePaymentList;
            }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
	public String getSubjectMaxMarksByClassIdandSubjectIdandExamtypId(long classId,String subjectName,long examTypeId,String academicYear){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select MAX(obtainedMarks) from vw_studentMarksDetails where classSectionId=");
				queryBuff.append(classId);		
				queryBuff.append(" and examTypeId=");		
				queryBuff.append(examTypeId);
				queryBuff.append(" and subjectName='");		
				queryBuff.append(subjectName);		
				queryBuff.append("' and academicYearId='");
				queryBuff.append(academicYear);	
				queryBuff.append("'");
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return String.valueOf(var);
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	}
	public String getSubjectMinMarksByClassIdandSubjectIdandExamtypId(long classId,String subjectName,long examTypeId,String academicYear){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select MIN(obtainedMarks) from vw_studentMarksDetails where classSectionId=");
				queryBuff.append(classId);		
				queryBuff.append(" and examTypeId=");		
				queryBuff.append(examTypeId);
				queryBuff.append(" and subjectName='");		
				queryBuff.append(subjectName);		
				queryBuff.append("' and academicYearId='");
				queryBuff.append(academicYear);	
				queryBuff.append("'");
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return String.valueOf(var);
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	}
	public double getSubjectAvgMarksByClassIdandSubjectIdandExamtypId(long classId,long subjectId,long examTypeId,long academicYear){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select AVG(ifnull(obtainedMarks,0)) from vw_studentMarksDetails where classSectionId=");
				queryBuff.append(classId);		
				queryBuff.append(" and examTypeId=");		
				queryBuff.append(examTypeId);
				queryBuff.append(" and subjectId=");		
				queryBuff.append(subjectId);		
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYear);	
				log.debug(queryBuff.toString());
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						return ((Double)resultList.get(0));
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	}
	public String getSubjectObtainedMarksByClassIdandSubjectIdandExamtypIdandStudentId(long classId,String subjectName,long examTypeId,String academicYear,long studentId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select obtainedMarks from vw_studentMarksDetails where classSectionId=");
				queryBuff.append(classId);		
				queryBuff.append(" and examTypeId=");		
				queryBuff.append(examTypeId);
				queryBuff.append(" and subjectName='");		
				queryBuff.append(subjectName);		
				queryBuff.append("' and academicYearId='");
				queryBuff.append(academicYear);	
				queryBuff.append("' and studId=");
				queryBuff.append(studentId);	
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return String.valueOf(var);
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	}
	public List<ViewStudentClassFeePaymentDetails> getMyChildrenFeeDetails(long studentId){
		try
		{
			StringBuffer queryString = new StringBuffer();
			queryString.append("from ViewStudentClassFeePaymentDetails where ");
			queryString.append(" studentId =");
			queryString.append(studentId);
			//queryString.append(" and (paymentStatus='L')");
			List viewStudentFeeDetails = this.getAllHqlQuery(queryString.toString());
			if(!ObjectFunctions.isNullOrEmpty(viewStudentFeeDetails))
			{
			 return  (List<ViewStudentClassFeePaymentDetails>) viewStudentFeeDetails;
			}
			         return null;
			 } catch (Exception ex) {
			         ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 }return null;
		}
	
	public List getMyChildrenClassmatesList(long classId,String status) {

		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select accountId,rollNumber,firstName,lastName,dateOfBirth,mobileNumber,fatherName,CAST(imagePath as char(512)) AS imagePath,thumbNail,parentEmail,dateOfJoining from vw_studentDetails where classSectionId = ");
           queryString.append(classId);
           queryString.append(" and status='");
           queryString.append(status);
           queryString.append("' order by rollNumber");
           
           log.debug(queryString.toString());
           List<Object[]> studentList =this.getAll(queryString.toString());
           List<ViewStudentPersonAccountDetails> viewStudentPersonAccountDetailsList =  new ArrayList<ViewStudentPersonAccountDetails>();
           if(!ObjectFunctions.isNullOrEmpty(studentList))
           {
        	   for(Object[] object : studentList)
	       	   {
	   			  if(!ObjectFunctions.isNullOrEmpty(object)){
	   				    ViewStudentPersonAccountDetails viewStudentPersonAccountDetails=new ViewStudentPersonAccountDetails();
		   				viewStudentPersonAccountDetails.setAccountId(Long.valueOf(object[0].toString()));
		   				if(!ObjectFunctions.isNullOrEmpty(object[1]))
			            {
		   					viewStudentPersonAccountDetails.setRollNumber(object[1].toString());
			            }
		   				viewStudentPersonAccountDetails.setFirstName(object[2].toString());
		   				if(!ObjectFunctions.isNullOrEmpty(object[3]))
			            {
		   					viewStudentPersonAccountDetails.setLastName(object[3].toString());
			            }
		   				if(!ObjectFunctions.isNullOrEmpty(object[4]))
			            {
		   					viewStudentPersonAccountDetails.setDateOfBirth(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[4].toString()));
			            }
		   				if(!ObjectFunctions.isNullOrEmpty(object[5]))
			            {
		   					viewStudentPersonAccountDetails.setMobileNumber(object[5].toString());
			            }
		   				if(!ObjectFunctions.isNullOrEmpty(object[6]))
			            {
		   					viewStudentPersonAccountDetails.setFatherName(object[6].toString());
			            }
			   			 if(!ObjectFunctions.isNullOrEmpty(object[7]))
			             {
			   				viewStudentPersonAccountDetails.setImagePath(object[7].toString());
			             }
			   			 if(!ObjectFunctions.isNullOrEmpty(object[8]))
			             {
			   				viewStudentPersonAccountDetails.setThumbNail(object[8].toString());
			             }
			   			if(!ObjectFunctions.isNullOrEmpty(object[9]))
			             {
			   				viewStudentPersonAccountDetails.setParentEmail(object[9].toString());
			             }
			   			if(!ObjectFunctions.isNullOrEmpty(object[10]))
			             {
			   				viewStudentPersonAccountDetails.setDateOfJoining(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[10].toString()));
			             }
		   				
		   				viewStudentPersonAccountDetailsList.add(viewStudentPersonAccountDetails);
		   				viewStudentPersonAccountDetails = null;
	   			  }
	       		}
        	   
               return viewStudentPersonAccountDetailsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
   }
	public List<ViewStudentMarksDetails> getStudentMarksByStudIdandExamtypeId(long studId,long examTypeId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStudentMarksDetails where studId=");
           queryString.append(studId);
           queryString.append(" and examTypeId=");
           queryString.append(examTypeId);
           //log.debug(queryString.toString());
           List marksList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(marksList))
           {
               return (List<ViewStudentMarksDetails>) marksList;
           }
            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public QuestionAnswer getQuestionAnswer(String studentAnswer,long questionId, long custId){
		/*return (QuestionAnswer)this.getAllHqlQuery("from QuestionAnswer where questionAnswer="+studentAnswer+" and questionId="+questionId+" and custId="+custId+" and (correctAnswer='Y') and order by anserOptions ");*/
		try{
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("from QuestionAnswer where questionId=");
			queryBuff.append(questionId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and questionAnswer='");
			queryBuff.append(studentAnswer);
			queryBuff.append("' and (correctAnswer='Y')");
			List questionAnsList = this.getAllHqlQuery(queryBuff.toString());
			if (ObjectFunctions.isNotNullOrEmpty(questionAnsList)) {
				return (QuestionAnswer) questionAnsList.get(0);
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getStudentQuizQuestionAnswer(long quizId,long custId,String status){
		return this.getAllHqlQuery("from ViewQuizQuestionAndAnswers where quizId=" + quizId+ " and correctAnswer='Y' and custId=" + custId+ " and status='"+status+"' ");
	}
	public StudentQuizResults getQuizResultsWithId(long studentId,long quizId,long custId,String status){
		try{
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("from StudentQuizResults where quizId=");
			queryBuff.append(quizId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and studentId=");
			queryBuff.append(studentId);
			queryBuff.append(" and status='");
			queryBuff.append(status);
			queryBuff.append("' ");
			List quizResultsList = this.getAllHqlQuery(queryBuff.toString());
			if (ObjectFunctions.isNotNullOrEmpty(quizResultsList)) {
				return (StudentQuizResults) quizResultsList.get(0);
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getAllCalendarEventsByStudentIdAndAccountId(String studentId,long customerId) {

		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from CalendarEvent where eventBelongs='ToALL' or (organizerId = '");
           queryString.append(studentId);
           
           queryString.append("') and endDate >='");
           queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
           queryString.append("' and custId=");
           queryString.append(customerId);
           List calendarEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(calendarEventsList)) {
               return null;
           } else {
               return calendarEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	public List getTermStudentAttendanceByClassId(long classId,int term,String year) {

		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from Attendance where classId=");
           queryString.append(classId);
           queryString.append(" and monthId = ");
           queryString.append(term);
           queryString.append(" and yearId = '");
           queryString.append(year);
           queryString.append("'");
           List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return studentEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getStudentQuestionAnswers(long studentId,long quizId,long custId,String todayDate){
		try{
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("from ViewStudentQuestionAnswers where quizId=");
			queryBuff.append(quizId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and studentId=");
			queryBuff.append(studentId);
			queryBuff.append(" and endDate < '");
			queryBuff.append(todayDate);
			queryBuff.append(" 00:00:00'");
			List quizResultsList = this.getAllHqlQuery(queryBuff.toString());
			if (ObjectFunctions.isNotNullOrEmpty(quizResultsList)) {
				return quizResultsList;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getStudentCorrectAnswersList(long questionId,long custId){
		return this.getAllHqlQuery("from ViewStudentQuestionAnswers where questionId=" + questionId+ " and custId=" + custId+" and (studentCorrectAnswer='Y')");
	}
	public List getStudentNotCorrectAnswersList(long questionId,long custId){
		return this.getAllHqlQuery("from ViewStudentQuestionAnswers where questionId=" + questionId+ " and custId=" + custId+" and (studentCorrectAnswer='N')");
	}
	public StudentQuestionAnswer getStudentAnswerAttemptWithQuestionId(long studentId,long questionId,long custId){
		try{
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("from StudentQuestionAnswer where studentId=");
			queryBuff.append(studentId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and questionId=");
			queryBuff.append(questionId);
			List studentQuestionAnswerList = this.getAllHqlQuery(queryBuff.toString());
			if (ObjectFunctions.isNotNullOrEmpty(studentQuestionAnswerList)) {
				return (StudentQuestionAnswer) studentQuestionAnswerList.get(0);
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	public ParentFeedbackResult getStudentFeedbackResult(long questionId,long custId,long studentId) {
		try {
			List feedbackStudentResultList = this.getAllHqlQuery("from ParentFeedbackResult where custId=" + custId+ " and feedbackQuestionId= " + questionId +" and studentId="+Long.valueOf(studentId));

			if (!ObjectFunctions.isNullOrEmpty(feedbackStudentResultList)) {
				return (ParentFeedbackResult) feedbackStudentResultList.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public ParentFeedbackResult getParentFeedbackResult(long questionId,long custId,long parentId) {
		try {
			List feedbackParentResultList = this.getAllHqlQuery("from ParentFeedbackResult where custId=" + custId+ " and feedbackQuestionId= " + questionId +" and parentId="+Long.valueOf(parentId));

			if (!ObjectFunctions.isNullOrEmpty(feedbackParentResultList)) {
				return (ParentFeedbackResult) feedbackParentResultList.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ParentFeedbackResult getStudentFeedbackStaffResult(long staffId,long studentId,long custId){
		try {
			List feedbackStudentStaffResultList = this.getAllHqlQuery("from ParentFeedbackResult where custId=" + custId+ " and staffId= " + staffId +" and studentId="+studentId);

			if (!ObjectFunctions.isNullOrEmpty(feedbackStudentStaffResultList)) {
				return (ParentFeedbackResult) feedbackStudentStaffResultList.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandStatus(long accountId,long academicYear,String status,long custId){
		try 
		{
			List studentList= this.getAllHqlQuery("from ViewStudentPersonAccountDetails where accountId=" + accountId+ " and academicYearId='" + academicYear+"' and status='"+status+"' and custId = "+ custId);
			if(!ObjectFunctions.isNullOrEmpty(studentList)){
				return (ViewStudentPersonAccountDetails)studentList.get(0);
			}
		} catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List<AcademicYear> getAllAcademicYearsBycustId(long custId){
		return (List<AcademicYear>) this.getAllHqlQuery("from AcademicYear where custId=" + custId);
	}
	public int getAllStudentMarksCountByTypeAndClassandstudId(long studId,long examtype){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStudentMarksDetails where examTypeId=");
           queryString.append(examtype);
           queryString.append(" and studId=");
           queryString.append(studId);
           List marksList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(marksList))
           {
               return  marksList.size();
           }
            return 0;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return 0;
	}
	public List getAllStudentsByCustIdAndStatus(long custId,String status) {

		try 
		{
		 List StudentList = this.getAllHqlQuery("from ViewStudentPersonAccountDetails where status ='"+status+"' and custId="+custId);
            if(!ObjectFunctions.isNullOrEmpty(StudentList))
            {
            	return StudentList;
            }else{
            	return null;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public String getSUMOfStudentMarksByStudIdandExamtypeId(long studId,long examTypeId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select sum(obtainedMarks) from vw_studentMarksDetails where studId="+studId+" and examTypeId="+examTypeId);
           List resultList=this.getAll(queryString.toString());	
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					Double totalMarks=((Double)resultList.get(0)); 
					return  String.valueOf(totalMarks);
				}
			}
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getstAllStudentsByClassId(long classId,String status){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStudentPersonAccountDetails where classSectionId=");
           queryString.append(classId);
           queryString.append(" and status='");
           queryString.append(status);
           queryString.append("'");
           List StudentList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(StudentList))
           {
           	return StudentList;
           }else{
           	return null;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}
	public long getUpcomingFeeTotalAmountByTerm(String queryString,long studentId,long custId,long classId,long academicYearId,long schoolTermId,String today,long boardingPointId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(paymentAmount) from " + queryString + " where studentId=");
				queryBuff.append(studentId);
				if(boardingPointId!=0){
					queryBuff.append(" and routeBoardingPointId=");
					queryBuff.append(boardingPointId);
				}else {
					queryBuff.append(" and classId=");
					queryBuff.append(classId);
				}
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and schoolTermId=");
				queryBuff.append(schoolTermId);
				if(!StringFunctions.isNullOrEmpty(today)){
					queryBuff.append(" and dueDate<'");
					queryBuff.append(today);
					queryBuff.append("' ");
				}
				queryBuff.append(" and paymentCommitFeeStatus='N' ");
				//log.debug("queryBuff.toString()"+queryBuff.toString());
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return var.longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;	
			}
			return 0;
	}
	/*public List<ViewUserRoles> getViewUserRolesByRoleNameAndCustId(String rollName, long customerId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewUserRoles where roleName='");
           queryString.append(rollName);
           queryString.append("' and custId="+customerId);
           log.debug(queryString.toString());
           List userRoles = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(userRoles))
           {
               return  userRoles;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}*/
	public ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandStatus(long accountId,String status){
		try 
		{
			List studentList= this.getAllHqlQuery("from ViewStudentPersonAccountDetails where accountId=" + accountId+ " and status='"+status+"'");
			if(!ObjectFunctions.isNullOrEmpty(studentList)){
				return (ViewStudentPersonAccountDetails)studentList.get(0);
			}
		} catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public ViewStudentPersonAccountDetails getStudentDetailsByAccountIdandAcademicYearId(long accountId,long academicYearId){
		try 
		{
			List studentList= this.getAllHqlQuery("from ViewStudentPersonAccountDetails where accountId=" + accountId+ " and academicYearId="+academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(studentList)){
				return (ViewStudentPersonAccountDetails)studentList.get(0);
			}
		} catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getAllClassesAndSetionWiseReportsByCustId(String className ,long custId,long academicYearId,String classIds){
		try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select classNameClassId,classSectionId,");
				queryBuff.append("CAST(CONCAT(IF(className IS NULL, '', className),IF(section IS NULL || section = '','',CONCAT(' - ', section))) AS CHAR(60)) as classNameAndSection ,");
				queryBuff.append("SUM(IF(gender='M',1,0))  as maleCount,SUM(IF(gender='F',1,0))  as femaleCount,");
				queryBuff.append("SUM(IF(joinedThroughAdmissions='Y',1,0)) as newAdmissionsCount, className,COUNT(*) from vw_studentDetails where custId=");
				queryBuff.append(custId);	
				queryBuff.append(" and academicYearId =");
				queryBuff.append(academicYearId);
				queryBuff.append(" and classSectionId in");
				queryBuff.append(classIds);
				queryBuff.append(" and description is null group by classSectionId order by sortingOrder ,classNameClassId,classSectionId asc");
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				}
		  }
		catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getTermStudentAttendanceByAccountIdIdAndTerms(long accountId,int monthNum,String year,Date date) {

		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from Attendance where accountId=");
           queryString.append(accountId);
           queryString.append(" and monthNum = ");
           queryString.append(monthNum);
           queryString.append(" and year = '");
           queryString.append(year);
           queryString.append("' and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, date));
           queryString.append("'");
           //queryString.append(" and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
           log.debug(queryString.toString());
           List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return studentEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	 public StudentDailyAttendance getStudentDailyAttendance(long studentId, String date,long custId,long academicYearId) {
			try {
				StringBuffer sb = new StringBuffer();
				sb.append(" from StudentDailyAttendance where custId="+custId+" and academicYearId="+academicYearId+" and studentId = " + studentId);
				// include class and sectionId
				sb.append(" and attendanceDate = '" + date+ "'");
				List tempList = this.getAllHqlQuery(sb.toString());
				if (ObjectFunctions.isNullOrEmpty(tempList)) {
					return null;
				} else {
					return (StudentDailyAttendance) tempList.get(0);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	}
	 
	public int getClassStudentsCountByClassIdandStatus(long classId,String status,long custId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from student where classSectionId=");
				queryBuff.append(classId);	
				queryBuff.append(" and status='");
				queryBuff.append(status);
				queryBuff.append("' and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and description is null");
				//List resultList=this.getAll(queryBuff.toString());
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList) && !ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.intValue();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	}
	
	public List<StudyClass> getStudyClassesByClassNameClassId(long classNameClassId,long customerId,long academicYearId) {
		return (List<StudyClass>) this.getAllHqlQuery("from StudyClass where classNameClassId = "+ classNameClassId +" and custId="+customerId+" and academicYearId="+academicYearId);
		
	}
	public int getClassStudentsCountByClassId(long classId,long custId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from student where classSectionId=");
				queryBuff.append(classId);	
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and description is null");
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList) && !ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.intValue();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return 0;
	}
	public int getApplicationMaxId(long custId,long academicYearId,int length) {
    	try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select IFNULL(max(CONVERT(SUBSTRING(applicationNumber,"+length+"),UNSIGNED INTEGER)),0) from onlineApplicationDetails where custId="+custId);
			queryBuff.append(" and academicYearId="+academicYearId);
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList))
			{
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					BigInteger var= ((BigInteger)resultList.get(0));
					return var.intValue();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return 0;
    }

	public void removeCommittedStudentFeePaidDetails(StudentVo studentVo,long studentPaymentId) {
		try {
			if (!ObjectFunctions.isNullOrEmpty(studentVo)) {
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from studentFeePaidDetails where ");
				sqlString.append("custId=" + studentVo.getCustId()+ " and studentPaymentId=" + studentPaymentId+ " and studentId=" + studentVo.getId()+ " and committedFeeStatus='Y' ");
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}

	public void removeCommittedStudentPayment(StudentVo studentVo,long studentPaymentId) {
		try {
			if (!ObjectFunctions.isNullOrEmpty(studentVo)) {
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from studentPayment where ");
				sqlString.append("custId=" + studentVo.getCustId()+ " and academicYearId=" + studentVo.getAcademicYearVo().getId()+ " and id=" + studentPaymentId + " and studentId="+ studentVo.getId());
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	public double getCommittedConfiguredFeeAmountCategoryWise(long custId,long academicYearId,long classId,long categoryId,String feeSettingIds){
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select IFNULL(SUM(feeAmount),0) as feeAmount from vw_classFeeDetails where custId="+custId+" and academicYearId="+academicYearId);
			queryBuff.append(" and classSectionId="+classId+" and committedFeeStatus='Y' and categoryId="+categoryId);
			if(!StringFunctions.isNullOrEmpty(feeSettingIds))
				queryBuff.append(" and feeSettingId in "+feeSettingIds);
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList))
			{
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					Double var= new Double(String.valueOf(resultList.get(0)));
					return var.doubleValue();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return 0;
	}
	public long getCommittedFeeAmountTermWise(String queryString,long studentId,long custId,long classId,long academicYearId,long schoolTermId,String today,long boardingPointId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(paymentAmount) from " + queryString + " where studentId=");
				queryBuff.append(studentId);
				if(boardingPointId!=0){
					queryBuff.append(" and routeBoardingPointId=");
					queryBuff.append(boardingPointId);
				}else {
					queryBuff.append(" and classId=");
					queryBuff.append(classId);
				}
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and schoolTermId=");
				queryBuff.append(schoolTermId);
				if(!StringFunctions.isNullOrEmpty(today)){
					queryBuff.append(" and dueDate<'");
					queryBuff.append(today);
					queryBuff.append("' ");
				}
				queryBuff.append(" and paymentCommitFeeStatus='Y' ");
				//log.debug("queryBuff.toString()"+queryBuff.toString());
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return var.longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;	
			}
			return 0;
	}
	public void removePermissionTimingsByPermissionId(long permissionId) {

        try {
            StringBuffer sqlString = new StringBuffer();
            sqlString.append("delete from permissionTimings where permissionsId =");
            sqlString.append(permissionId);
            int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
        } catch (Exception ex) {
            log.error("delete removePermissionTimingsByPermissionId failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
	}
	public void updateTcGenerateInActiveStudents(String wishTitle, long custId) {

        try {
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update student set status ='N'"); 
	           sqlString.append(", description ='");
	           sqlString.append("Tc generated.'");
	           sqlString.append(" where id in ");
	           sqlString.append(wishTitle);
	           sqlString.append(" and custId="+custId);

	           StringBuffer sqlString1=new StringBuffer();
	           sqlString1.append("update Account set accountEnabled ='N'"); 
	           sqlString1.append(", accountExpired='Y' ");
	           sqlString1.append(" where id in (");
	           sqlString1.append(" select accountId from student");
	           sqlString1.append(" where id in ");
	           sqlString1.append(wishTitle);
	           sqlString1.append(" and custId="+custId +" )");
	           log.debug("Update Query"  +sqlString.toString());
	           log.debug("Update Query"  +sqlString1.toString());
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	           int row1 = getSession().createSQLQuery(sqlString1.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            } if(row1 == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       } catch (Exception ex) {
            log.error("update  updateTcGenerateInActiveStudents failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
	}

	public void updateParentSecondaryUsername(long accountId) {
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("update Account set secondaryUsername ="+ accountId);
			sqlString.append(" where id=");
			sqlString.append(accountId);
			log.debug("Update Query" + sqlString.toString());
			int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
			if (row == 0) {
				log.debug("The no of rows Updated:" + row);
			}
		} catch (Exception ex) {
			log.error("update  updateParentSecondaryUsername failed", ex);
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	public void updateStudentUserName(long accountId,String password) {
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("update Account set secondaryUsername ="+ accountId);
			sqlString.append(" ,username=").append(accountId);
			sqlString.append(" ,password='"+password+"' ");
			sqlString.append(" where id=");
			sqlString.append(accountId);
			log.debug("Update Query" + sqlString.toString());
			int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
			if (row == 0) {
				log.debug("The no of updateStudentUserName Updated:" + row);
			}
		} catch (Exception ex) {
			log.error("update  updateStudentUserName failed", ex);
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	/**
	 * This method used to get the student count with respective to mother tong wise
	 */
	@Override
	public List<Object[]> getAllMotherTongueWiseStudentSummaryDetails(Long custId,	Long academicYearId, String classIds, String motherTongueIds) {
		String query = "SELECT IFNULL(p.motherToungId, 0) as motherToungId,IF(mtype.name IS NULL, 'With Out Mother Tongue', mtype.name)  as motherToungeName,"
				+ " CAST(CONCAT(IF(c.className IS NULL, '', c.className),IF(c.section IS NULL || c.section = '','',CONCAT(' - ', c.section))) AS CHAR(60)) as classNameAndSection,"
				+ " s.classSectionId,s.classNameClassId,SUM(IF(gender='M',1,0))  as maleCount,SUM(IF(gender='F',1,0))  as femaleCount,SUM(IF(IFNULL (gender,'')='',1,0))  as transgenderCount "
				+ " FROM student s JOIN Account a ON (s.accountId = a.id and s.status='Y') LEFT JOIN Person p ON (a.personId = p.id)"
				+ " LEFT JOIN studyClass c ON (c.id = s.classSectionId) LEFT JOIN motherTongue mtype ON (mtype.id = p.motherToungId) "
				+ " WHERE a.custId ="
				+ custId
				+ " AND s.academicYearId = "
				+ academicYearId
				+ " AND p.motherToungId in "
				+ motherTongueIds
				+ " AND s.classSectionId in "
				+ classIds
				+ " group by  p.motherToungId,s.classSectionId order by s.classNameClassId,s.classSectionId,p.motherToungId ";
		 
		List<Object[]> motherTongueSummaryList = (List<Object[]>) this.getAll(query);
		return motherTongueSummaryList;
	}
	@Override
	public List getAllClassesWiseTCTakenStudentCountByCustId(long custId,long academicYearId,String classIds){
		try{
				StringBuffer queryBuff=new StringBuffer();
				
				queryBuff.append("select classSectionId,count(*) from vw_studentsTCDetails where custId = ");
				queryBuff.append(custId);	
				queryBuff.append(" and academicYearId =");
				queryBuff.append(academicYearId);
				queryBuff.append(" and classSectionId in");
				queryBuff.append(classIds);
				queryBuff.append(" and serialNumber != 0 ");
				queryBuff.append(" group by classSectionId");
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				}
		  }
		catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	@Override
	public List getAllClassesWiseNewAdmittedStudentCountByCustId(long custId,
			long academicYearId, String classIds) {
		try{
				StringBuffer queryBuff=new StringBuffer();
				
				queryBuff.append("select S.academicYearId, SC.className, COUNT(S.id) count from student S Join studyClass SC on S.classSectionId=SC.id");
				queryBuff.append(" Join ( Select DISTINCT(className) className from studyClass where id in ");
				queryBuff.append(classIds);
				queryBuff.append(" ) clsNames ON clsNames.className=SC.className where S.custId = ");
				queryBuff.append( custId);
				queryBuff.append(" and SC.academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and joinedThroughAdmissions='Y' AND S.status='Y' Group By S.classSectionId");
					List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				}
		  }
		catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	
	}

	
	@Override
	public List<Object[]> getSubjectsMarksForStudent(long studentId, String subjectIds) {
		try{
				StringBuffer queryBuff=new StringBuffer();
				
				queryBuff.append("select SM.studId,ES.classSubjectId,SS.name, ET.examType,GROUP_CONCAT(CONCAT(ST.subTypeName,'-',SM.obtainedMarks)) SubTypes, SUM(SM.obtainedMarks ) ObtainedMarks,");
				queryBuff.append( "SUM(ES.maxMarks) MaxMarks,SUM(SM.moderationMarks ) ModerationMarks FROM studentMarks SM Left Join examschedules ES on ES.id=SM.examScheduleId ");
				queryBuff.append("Left Join studySubject SS ON SS.id=ES.classSubjectId Left Join examTypes ET ON ET.id=ES.examTypeId Left Join subType ST ON ST.id=ES.subTypeId ");
				queryBuff.append("Where SM.studId=");
				queryBuff.append(studentId);
				queryBuff.append(" and ES.classSubjectId in ");
				queryBuff.append(subjectIds);
				queryBuff.append(" Group By ES.classSubjectId,ES.examTypeId order by ES.examTypeId,ES.classSubjectId ");
				
				
				/*queryBuff.append(" Join ( Select DISTINCT(className) className from studyClass where id in ");
				queryBuff.append(classIds);
				queryBuff.append(" ) clsNames ON clsNames.className=SC.className where S.custId = ");
				queryBuff.append( custId);
				queryBuff.append(" and SC.academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and joinedThroughAdmissions='Y' AND S.status='Y' Group By S.classSectionId");
				*/	
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				}
		  }
		catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	
	}
	
	
	
	//"select SM.studId,ES.classSubjectId,SS.name, ET.examType,GROUP_CONCAT(CONCAT(ST.subTypeName,'-',SM.obtainedMarks)) SubTypes, SUM(SM.obtainedMarks ) ObtainedMarks,SUM(ES.maxMarks) MaxMarks,SUM(SM.moderationMarks ) ModerationMarks FROM studentMarks SM Left Join examschedules ES on ES.id=SM.examScheduleId Left Join studySubject SS ON SS.id=ES.classSubjectId Left Join examTypes ET ON ET.id=ES.examTypeId Left Join subType ST ON ST.id=ES.subTypeId Where SM.studId=27259  and ES.classSubjectId in  (2840, 2843, 2844, 2845, 2846, 2847, 2848, 0) Group By ES.classSubjectId,ES.examTypeId order by ES.examTypeId,ES.classSubjectId ";
}