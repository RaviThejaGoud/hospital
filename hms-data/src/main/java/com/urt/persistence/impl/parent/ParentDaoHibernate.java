package com.urt.persistence.impl.parent;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.impl.calendar.CalendarDaoHibernate;
import com.urt.persistence.interfaces.parent.ParentDao;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;

@Transactional
public class ParentDaoHibernate extends UniversalHibernateDao implements ParentDao {

	private static final Log log = LogFactory.getLog(CalendarDaoHibernate.class);
	
		public List getLeavesListByAccountId(long accountId,String leaveStatus,long customerId)
		{
			try {
	            List groupsList = this.getAllHqlQuery("from Leave where leaveStatus ='" +leaveStatus+ "' and accountId = " + accountId +" and custId = "+customerId);
	            if(!ObjectFunctions.isNullOrEmpty(groupsList))
	            {
	            	return groupsList;
	            }else{
	            	return null;
	            }
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}

		
		public Staff getStaffByAcountId(long accountId){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from Staff where accountId=");
				queryBuff.append(accountId);
				List staffList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(staffList)){
					return (Staff)staffList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }
		 
		public ClassTeacher getStudyClassSubject(long classId, long staffId){
			try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ClassTeacher where teacherId=");
				queryBuff.append(staffId);
				queryBuff.append(" and studyClassId=");
				queryBuff.append(classId);
				List classTeachers=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(classTeachers)){
					return (ClassTeacher)classTeachers.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
		}
		
		public List<Student> getStudentByClassId(long classId){
			try{
				 return (List<Student>) this.getAllHqlQuery("from Student where classSectionId=" +classId );
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }
		
		/*public Period getPeriodByClassAndSubject(long classId, long subjectId){
			try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from Period where classId=");
				queryBuff.append(classId);
				queryBuff.append(" and subjectId=");
				queryBuff.append(subjectId);
				List periodsList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(periodsList)){
					return (Period)periodsList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
		}*/
		public Student getStudentById(long studentId){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from Student where id=");
				queryBuff.append(studentId);
				List studentList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(studentList)){
					return (Student)studentList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }

		public List getAllCalendarEventsByStaffIdAndAccountId(long staffId,long customerId) {

			try 
			{
				
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from CalendarEvent where organizerId = ");
		           queryString.append(staffId);
		           queryString.append(" and custId=");
		           queryString.append(customerId);
		           queryString.append(" and endDate >='");
		           queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
		           //queryString.append(" 11:59:59'");
		           queryString.append(" '");
		           List calendarEventsList = this.getAllHqlQuery(queryString.toString());
		           if (ObjectFunctions.isNullOrEmpty(calendarEventsList)) {
		               return null;
		           } else {
		               return calendarEventsList;
		           }
				
				
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}
		public List getAllCalendarEventsByCustomerId(long customerId) {

			try 
			{
				
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from CalendarEvent where custId = ");
		           queryString.append(customerId);
		           queryString.append(" and endDate >='");
		           queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
		           //queryString.append(" 11:59:59'");
		           queryString.append(" '");
		           List calendarEventsList = this.getAllHqlQuery(queryString.toString());
		           if (ObjectFunctions.isNullOrEmpty(calendarEventsList)) {
		               return null;
		           } else {
		               return calendarEventsList;
		           }
				
				
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		
		}
		public List getEventInvitedUserListByEventId(String eventId) {

			try 
			{
				
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from EventInvitedUser where eventId = ");
		           queryString.append(Long.valueOf(eventId));
		           List eventInvitedUserList = this.getAllHqlQuery(queryString.toString());
		           if (ObjectFunctions.isNullOrEmpty(eventInvitedUserList)) {
		               return null;
		           } else {
		               return eventInvitedUserList;
		           }
				
				
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		
		}

		public User getAccountByUserName(String userName) {

	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from User where userName='");
				queryBuff.append(userName);
				queryBuff.append("'");
				List userList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(userList)){
					return (User)userList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    
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
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}
		
		public List getTeacherSubjectsById(long staffId) {
			try 
			{
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from ClassTeacher where teacherId=");
		           queryString.append(staffId);		           
		           List staffSubjectsList = this.getAllHqlQuery(queryString.toString());
		           if (ObjectFunctions.isNullOrEmpty(staffSubjectsList)) {
		               return null;
		           } else {
		               return staffSubjectsList;
		           }
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}
		
		public List getTeacherSubjectsByStaffAndClassId(String staffId,String classId){
			try 
			{
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from ClassTeacher where teacherId=");
		           queryString.append(staffId);
		           queryString.append(" and studyClassId=");
		           queryString.append(classId);
		           List staffSubjectsList = this.getAllHqlQuery(queryString.toString());
		           if (ObjectFunctions.isNullOrEmpty(staffSubjectsList)) {
		               return null;
		           } else {
		               return staffSubjectsList;
		           }
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}
		
		public ClassTeacher getTeacherByClassandSubjectId(long staffId,long classId,String subjectId){
			try 
			{
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from ClassTeacher where teacherId=");
		           queryString.append(staffId);
		           queryString.append(" and studyClassId=");
		           queryString.append(classId);
		           queryString.append(" and studySubjectId=");
		           queryString.append(subjectId);
		           List staffSubjectsList = this.getAllHqlQuery(queryString.toString());
		          if(ObjectFunctions.isNotNullOrEmpty(staffSubjectsList)){
						return (ClassTeacher)staffSubjectsList.get(0);
					}
		           
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}
		/*public Student getStudentByAcountId(long accountId){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from Student where accountId=");
				queryBuff.append(accountId);
				List staffList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(staffList)){
					return (Student)staffList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }*/
		public List getViewStudentPersonAccountDetailsByClassNameAndSectionId(String className,String sectionName) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentPersonAccountDetails where className = '");
	           queryString.append(className);
	           if(!StringFunctions.isNullOrEmpty(sectionName))
	           {
	        	   queryString.append("' and section='");
		           queryString.append(sectionName);
	           }
	           queryString.append("'");
	           List viewStudentPersonAccountDetailsList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsList))
	           {
	               return  viewStudentPersonAccountDetailsList;
	           }
	            return null;    
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}
		
		public List getAllLeavesByStatusAndRoleId(long customerId,String leaveStatus,String roleName) {
		       try {
			    	  List leavesList = this.getAllHqlQuery("from ViewStudentLeaveDetails where leaveStatus= '"+leaveStatus +"' and custId = "+customerId+" and roleName ='" + roleName +"'"); 
			           if(!ObjectFunctions.isNullOrEmpty(leavesList))
			           {
			               return leavesList;
			           }
		           }
		       catch (Exception re) {
		           log.error("getting getAllPendingStatusLeaves failed", re);
		       }
		       return null;
		}
		public List getClassEventListByEventId(String eventId) {
			try {
	            List classEventList = (List) this.getAll("select * from classEvent where eventId = "+eventId);
	            if(!ObjectFunctions.isNullOrEmpty(classEventList))
	            {
	            	return classEventList;
	            }else{
	            	return null;
	            }
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}

		/*public List getStudyClassListBystudyClassIds(String studyClassIds) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from StudyClass where id in ");
	           queryString.append(studyClassIds);
	           List studyClassList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studyClassList))
	           {
	               return  studyClassList;
	           }
	            return null;    
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}*/
		
		/*public List getALLViewStudentPersonAccountDetailsByStudentRole(String studentRole) {

			try 
			{
				
		           StringBuffer queryString = new StringBuffer();
		           queryString.append("from ViewStudentPersonAccountDetails where roleName ='");
		           queryString.append(studentRole);
		           queryString.append("'");
		           List viewStudentPersonAccountDetailsList = this.getAllHqlQuery(queryString.toString());
		           if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsList))
		           {
		               return  viewStudentPersonAccountDetailsList;
		           }
		            return null;    
	        } catch (Exception re) {
	        	re.printStackTrace();
	        }return null;
		}*/
		
		public ViewStudentLeaveDetails getViewStudentLeaveDetailsByLeaveId(long leaveId, long customerId) {

		       try {
		    	  List leaveList = this.getAllHqlQuery("from ViewStudentLeaveDetails where custId= "+customerId+" and leavesId = "+leaveId);
			           if(!ObjectFunctions.isNullOrEmpty(leaveList))
			           {
			               return (ViewStudentLeaveDetails)leaveList.get(0);
			           }
		           }
		       catch (Exception re) {
		           log.error("getting getViewStudentLeaveDetailsByLeaveId failed", re);
		       }
		       return null;
		
		}


		public Role getRoleDetailsByRoleName(String roleName) {
			// TODO Auto-generated method stub
			return null;
		}

}
