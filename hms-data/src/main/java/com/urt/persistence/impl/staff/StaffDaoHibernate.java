package com.urt.persistence.impl.staff;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTDataAccessException;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.staff.StaffDao;
import com.urt.persistence.model.common.ReplyScrapMessage;
import com.urt.persistence.model.common.ScrapMessage;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.VWStudentAttendance;
import com.urt.persistence.model.common.VWStudentDailyAttendance;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.KBankRating;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.exam.QuizQuestion;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.PromoteClass;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStaffLeaveDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.Role;
import com.urt.util.common.RayGunException;

@Transactional
public class StaffDaoHibernate extends UniversalHibernateDao implements	StaffDao {

	private static final Log log = LogFactory.getLog(StaffDaoHibernate.class);
	/*Changed by seshu on 26th April.*/
	public ClassTeacher getTeacherByClassandSubjectId(long staffId,long classId,long subjectId,String staffActiveStatus){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ClassTeacher cteacher where cteacher.studySubject = ");
           queryString.append(subjectId);	           
           queryString.append(" and cteacher.studyClass=");
           queryString.append(classId);
           if(staffId!=0)
        	   queryString.append(" and cteacher.staff =").append(staffId);
           if(StringFunctions.isNotNullOrEmpty(staffActiveStatus))
        	   queryString.append(" and cteacher.staff.status ='").append(staffActiveStatus).append("'");
           queryString.append(" order by cteacher.classTeacher DESC");
           List<ClassTeacher> staffSubjectsList = (List<ClassTeacher>) this.getAllHqlQuery(queryString.toString());
          if(ObjectFunctions.isNotNullOrEmpty(staffSubjectsList)){
				return (ClassTeacher)staffSubjectsList.get(0);
			}
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	public ClassTeacher getClassTeacherByAccountId(long accountId,long academicYearId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ClassTeacher cteacher where cteacher.staff.account.id= ");
           queryString.append(accountId);	           
           queryString.append(" and cteacher.staff.status='Y' ");
           queryString.append(" and cteacher.academicYear.id=");
           queryString.append(academicYearId);
           queryString.append(" and cteacher.classTeacher='Y' ");
          // List<ClassTeacher> staffSubjectsList = (List<ClassTeacher>) this.getAllHqlQuery(queryString.toString());
           List<ClassTeacher> staffSubjectsList = (List<ClassTeacher>) this.getAllHqlQuery(queryString.toString());
           if(ObjectFunctions.isNotNullOrEmpty(staffSubjectsList))
            	return (ClassTeacher)staffSubjectsList.get(0);
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	
	
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

		/*public Staff getStaffByAcountId(long accountId){
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
	    }*/
		 
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
		
		public List<ClassTeacher> getStudyClassSubjectByClassid(long classId){
			try{
				return (List<ClassTeacher>) this.getAllHqlQuery("from ClassTeacher where studyClassId=" +classId );
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

		public List getAllCalendarEventsByCustomerId(long customerId) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from CalendarEvent where custId = ");
	           queryString.append(customerId);
	           queryString.append(" and endDate >='");
	           queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
	           //queryString.append(" 11:59:59'");
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
		
		public List<ClassTeacher> getTeacherSubjectsByIdAndAcademicYear(long staffId,long academicYearId,long custId) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ClassTeacher where teacherId=");
	           queryString.append(staffId);
	           queryString.append(" and academicYearId=");		
	           queryString.append(academicYearId);	
	           queryString.append(" and custId=");
	           queryString.append(custId);
	           //List staffSubjectsList = this.getAllHqlQuery(queryString.toString());
	           List staffSubjectsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(staffSubjectsList)) {
	               return null;
	           } else {
	               return (List<ClassTeacher>) staffSubjectsList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
		
		public List getTeacherSubjectsByStaffAndClassId(long accountId,long classId,long academicYearId){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStaffSubjectsDetails where accountId=");
	           queryString.append(accountId);
	           queryString.append(" and studyClassId=");
	           queryString.append(classId);
	           queryString.append(" and academicYearId=");
	           queryString.append(academicYearId);
	           List staffSubjectsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(staffSubjectsList)) {
	               return null;
	           } else {
	               return staffSubjectsList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
		
		
		public Student getStudentByAcountId(long accountId){
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
	    }
		
		public List getStudentByClassIdAndStaffId(long classId,long customerId,long academicYearId,String status){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentPersonAccountDetails where classSectionId='"+Long.valueOf(classId)+"' and custId="+customerId+" and academicYearId="+academicYearId+" and status='"+status+"' ");
	           List staffStudentsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(staffStudentsList)) {
	               return null;
	           } else {
	               return staffStudentsList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
	    /*public StudyClass getAllStudyClassesByClassId(long classId){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from StudyClass where id=");
				queryBuff.append(classId);
				List studentClassList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(studentClassList)){
					return (StudyClass)studentClassList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }*/
	  /*  public ClassName getClassBystudyClassId(String className){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ClassName where className='");
				queryBuff.append(className);
				queryBuff.append("'");
				List classesList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(classesList)){
					return (ClassName)classesList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }*/
	    public Student getStudentsByAccountId(long stuAccountId){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from Student where accountId=");
				queryBuff.append(stuAccountId);
				List studentsList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(studentsList)){
					return (Student)studentsList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }
		public List<ViewStudentPersonAccountDetails> getViewStudentPersonAccountDetailsByStudyClassIdandStatus(long classId,String status,String academicYearId) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentPersonAccountDetails where classSectionId = ");
	           queryString.append(classId);
	           queryString.append(" and status='");
	           queryString.append(status);
	           queryString.append("' and academicYearId='");
	           queryString.append(academicYearId);
	           queryString.append("'");
	           List viewStudentPersonAccountDetailsList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsList))
	           {
	               return  (List<ViewStudentPersonAccountDetails>) viewStudentPersonAccountDetailsList;
	           }
	            return null;    
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
		
		
		public List getClassEventListByEventId(String eventId) {
			try {
	            //List classEventList = (List) getSession().createSQLQuery("select * from classEvent where eventId = "+eventId).list();
				List classEventList = this.getAll("select * from classEvent where eventId = "+eventId);
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
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
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
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}*/
		public List getAllStudentStatus(String studentId) {
			try {
				return this.getAllHqlQuery("from Attendance where studentId='"+studentId+"'");
				
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		} 
		public ViewStudentLeaveDetails getViewStudentLeaveDetailsByLeaveId(long leaveId, long customerId) {

		       try {
		    	  List leaveList = this.getAllHqlQuery("from ViewStudentLeaveDetails where custId= "+customerId+" and leavesId = "+leaveId);
			           if(!ObjectFunctions.isNullOrEmpty(leaveList))
			           {
			               return (ViewStudentLeaveDetails)leaveList.get(0);
			           }
		           }
		       catch (Exception ex) {
		           log.error("getting getViewStudentLeaveDetailsByLeaveId failed", ex);
		           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		       }
		       return null;
		
		}
		
		public Role getRoleDetailsByRoleName(String roleName) {
		       List rolesList = null;
		       try {
		    	   rolesList = this.getAllHqlQuery("from Role where name= '"+roleName+"'");
			           if(!ObjectFunctions.isNullOrEmpty(rolesList))
			           {
			               return (Role)rolesList.get(0);
			           }
		           }
		       catch (Exception ex) {
		           log.error("getting getRoleDetailsByRoleName failed", ex);
		           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		       }
		       return null;
		}
		
		public List<ViewStudentLeaveDetails> getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(long customerId, String leaveStatus, String roleName,long supervisorId,long academicYearId) {

		       try {
		    	   
		            StringBuffer queryString = new StringBuffer();
		            queryString.append("from ViewStudentLeaveDetails where leaveStatus= '");
		            queryString.append(leaveStatus);
		            queryString.append("' and custId =");
		            queryString.append(customerId);
		            queryString.append(" and roleName ='");
		            queryString.append(roleName);
		            queryString.append("'");
		            queryString.append(" and leaveSupervisorId ='");
		            queryString.append(supervisorId);
		            queryString.append("' and academicYearId='");
		            queryString.append(academicYearId);
		            queryString.append("'");
		            List leavesList =  this.getAllHqlQuery(queryString.toString());
			    	  //List leavesList = this.getAllHqlQuery("from ViewStaffLeaveDetails where leaveStatus= '"+leaveStatus +"' and custId = "+customerId+" and roleName ='" + roleName+"'"); 
			           if(!ObjectFunctions.isNullOrEmpty(leavesList))
			           {
			               return (List<ViewStudentLeaveDetails>) leavesList;
			           }
		           }
		       catch (Exception ex) {
		           log.error("getting getAllStudentLeavesByStatusAndRoleNameAndSupervisorId failed", ex);
		           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		       }
		       return null;
		}
		
		public List getStudySubjectsId(String subjectId,String classId) {
			try {
				if (!ObjectFunctions.isNullOrEmpty(classId)) 
				   {
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("select studyClassId,subjectId from ClassSubject where subjectId not in");
					sqlString.append(subjectId);
					sqlString.append(" and studyClassId =");
					sqlString.append(classId);
			        //List subjectsList = (List) getSession().createSQLQuery(sqlString.toString()).list();
					List subjectsList = this.getAll(sqlString.toString());
			           //List eventInvitedUserList = (List) getSession().createSQLQuery("select * from eventInvitedUser where accountId="+accountId+" and eventAccessed='"+ eventAccessed+"' eventId in " +eventIds).list();
			           if(!ObjectFunctions.isNullOrEmpty(subjectsList))
			           {
			               return  subjectsList;
			           }
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
		public List getTeacherSubjects(String subjectId){
			try
			{
			   return this.getAllHqlQuery("from StudySubject where id in"+subjectId);
			}
			catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}


		public void removeRegistrationStudentEvent(String accountId, String eventId,String eventAcceptedStatus) {
			try{
	            StringBuffer sqlString=new StringBuffer();
		           sqlString.append("delete from eventInvitedUser where eventAccepted ='"); 
		           sqlString.append(eventAcceptedStatus);
		           sqlString.append("' and accountId = ");
		           sqlString.append(Long.valueOf(accountId)); 
		           sqlString.append(" and eventId = ");
		           sqlString.append(Long.valueOf(eventId)); 
		          // queryString.append("'");
		           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
		            if(row == 0)
		            {
		                log.debug("The no of rows deleted:"+ row);
		            }
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
		}
		
		/*public Student getStudentByRollNumber(String rollNo){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from Student where rollNumber='");
				queryBuff.append(rollNo);
				queryBuff.append("'");
				List studentList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(studentList)){
					return (Student)studentList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	    }*/
		
		public List getStudentsByIds(String studentIds) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from Student where id in (");
	           queryString.append(studentIds);
	           queryString.append(")");
	           List studentList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studentList))
	           {
	               return  studentList;
	           }
	            return null;    
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
		
		public List getNotInvitedStudents(String accountIds){
			try {
				
				   StringBuffer queryString = new StringBuffer();
		           queryString.append("from Student where accountId not in");
		           queryString.append(accountIds);
		           List studentList = this.getAllHqlQuery(queryString.toString());
		           if(!ObjectFunctions.isNullOrEmpty(studentList))
		           {
		               return  studentList;
		           }
		            return null;  
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}

		public List<Leave> getAllLeavesListByAccountId(long accountId, long customerId) {

			try {
	            List leavesList = this.getAllHqlQuery("from Leave where accountId = " + accountId +" and custId = "+customerId);
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
	
/*		public ViewStudentPersonAccountDetails getFindStudentByIdOrName(String rollNumber, String firstName,long customerId) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentPersonAccountDetails where ");
	           if(!StringFunctions.isNullOrEmpty(rollNumber))
	           {
	        	   queryString.append("rollNumber = '");
	        	   queryString.append(rollNumber);
	        	   queryString.append("'");
	           }
	           else
	           {
	        	   queryString.append("firstName like '");
	        	   queryString.append(firstName);
	        	   queryString.append("%'");
	           }
	           
	           List studentList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studentList))
	           {
	               return  (ViewStudentPersonAccountDetails)studentList.get(0);
	           }
	            return null;    
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}*/
		
		public ViewStaffPersonAccountDetails getFindStaffByIdOrName(String username, String firstName,long customerId) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStaffPersonAccountDetails where accountExpired='N' and status='Y' and academicYearStatus='Y' and ");
	           if(!StringFunctions.isNullOrEmpty(username))
	           {
	        	   queryString.append("username = '");
	        	   queryString.append(username);
	           }
	           else
	           {
	        	   queryString.append("firstName like '%");
	        	   queryString.append(firstName);
	           }
	           queryString.append("'");
	           queryString.append(" and custId =");
        	   queryString.append(customerId);
	           List studentList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studentList))
	           {
	               return  (ViewStaffPersonAccountDetails)studentList.get(0);
	           }
	            return null;    
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
		public List getMyMessagesByReceiverAccountIdAndIsParent(String receiverAccountId, long customerId,String isParent) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from Messages where receiverAccountId='");
	           queryString.append(receiverAccountId);
	           queryString.append("' and custId = ");
	           queryString.append(customerId);
	           queryString.append(" and isParent = '");
	           queryString.append(isParent);
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
		/*public List getStudentMarksByExamTypeAndSubjectName(String studIds,String examType,String studySubject){
		   StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from StudentMarks where examType='");
			queryBuff.append(examType);
			queryBuff.append("' and subjectName='");
			queryBuff.append(studySubject);
			queryBuff.append("' and  studId in ");
			queryBuff.append(studIds);			
			return this.getAllHqlQuery(queryBuff.toString());
	   }*/
	   
	   public ClassTeacher getClassTeacherByStaffIdandAcademicYear(long staffId,long academicYear,long custId){
			try{
				//List classTeachers= this.getAllHqlQuery("from ClassTeacher where teacherId ="+staffId+" and classTeacher='Y' and academicYearId="+academicYear +" and custId="+custId);
				List classTeachers= this.getAllHqlQuery("from ClassTeacher where teacherId ="+staffId+" and classTeacher='Y' and academicYearId="+academicYear +" and custId="+custId);
				if(ObjectFunctions.isNotNullOrEmpty(classTeachers)){
					return (ClassTeacher)classTeachers.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
		}
	  public List<ViewClassExamDetails> getClassTeacherExamSchedules(long classSectionId,long academicYear){
			 return (List<ViewClassExamDetails>) this.getAllHqlQuery("from ViewClassExamDetails where classSectionId ="+classSectionId+" and academicYearId="+academicYear);
		 }
		 
	  	  public String getClassIdByNameAndSection(String className,String section,long custId,long academicYear){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select id from studyClass where className='");
				queryBuff.append(className);		
				queryBuff.append("' and section='");		
				queryBuff.append(section);		
				queryBuff.append("' and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYear);	
				//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();	
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					BigInteger var= ((BigInteger)resultList.get(0));
					return var.toString();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	  }
	  
	 /* public List<ViewClassExamDetails> getUpComingClassExamSchedules(String className, String sectionName, long custId) {
			StringBuffer queryString = new StringBuffer();
	      queryString.append("from ViewClassExamDetails where className='");
	      queryString.append(className);
	      queryString.append("' and section='");
	      queryString.append(sectionName);
	      queryString.append("' and custId=");
	      queryString.append(custId);
	      queryString.append(" and examDate >='");
	      queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,new Date()));
	      queryString.append(" 00:00:00'");
          queryString.append(" group by examDate");
	      //queryString.append(" '");
	      List classExamScheduleList = this.getAllHqlQuery(queryString.toString());
	      if (ObjectFunctions.isNullOrEmpty(classExamScheduleList)) {
	          return null;
	      } else {
	          return classExamScheduleList;
	      }
	}*/
	  public List getFindStudentsListByIdOrName(String rollNumber, String firstName,long customerId,long academicYearId,long studyClassId) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentPersonAccountDetails where custId=");
	           queryString.append(customerId);
	           queryString.append(" and academicYearId = "+academicYearId);
	           queryString.append(" and classSectionId = "+studyClassId);
	           if(!StringFunctions.isNullOrEmpty(rollNumber))
	           {
	        	   queryString.append(" and rollNumber = '");
	        	   queryString.append(rollNumber);
	        	   queryString.append("'");
	           }
	           else
	           {
	        	   queryString.append(" and firstName like '");
	        	   queryString.append(firstName);
	        	   queryString.append("%'");
	           }
	           List studentList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studentList))
	           {
	               return  studentList;
	           }
	            return null;    
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
	  
	  public List getAllMessagesByMessageStatus(String status, long customerId,String academicYearId,Date messageDate) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewMessagesDetails where ");
	           if(!StringFunctions.isNullOrEmpty(status))
	           {
	        	   queryString.append(" status='");
		           queryString.append(status + "' and ");
	           }
	    	   queryString.append(" custId = ");
	           queryString.append(customerId);
	           queryString.append(" and academicYearId = '");
	           queryString.append(academicYearId);
	           if(!ObjectFunctions.isNullOrEmpty(messageDate)){
	            queryString.append("' and messageDate >= '");
	            queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, messageDate));
	           }
	           queryString.append("' order by  createdDate DESC");
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
	 
	  public String getExamTypeIdByExamType(String examType,long custId,String academicYear){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select id from examTypes where examType='");
				queryBuff.append(examType);		
				queryBuff.append("' and custId=");		
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");		
				queryBuff.append(academicYear);
				//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();	
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					BigInteger var= ((BigInteger)resultList.get(0));
					return var.toString();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	  }
		
	public String getSubjectIdBySubjectName(String subjectName,long custId,long academicYear){
			  try{
					StringBuffer queryBuff=new StringBuffer();
					queryBuff.append("select id from studySubject where name='");
					queryBuff.append(subjectName);		
					queryBuff.append("' and custId=");		
					queryBuff.append(custId);
					queryBuff.append(" and academicYearId=");		
					queryBuff.append(academicYear);	
					//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();	
					List resultList=this.getAll(queryBuff.toString());	
					if(!ObjectFunctions.isNullOrEmpty(resultList)){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.toString();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();			
				}
				return null;
		}
	public ExamSchedules getExamSchedule(String studyClassId,String examTypeId,String subjectId,long academicYear,long custId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ExamSchedules where academicYearId=");
				queryBuff.append(academicYear);		
				queryBuff.append(" and classId='");		
				queryBuff.append(studyClassId);	
				queryBuff.append("' and examTypeId='");
				queryBuff.append(examTypeId);	
				queryBuff.append("' and classSubjectId='");
				queryBuff.append(subjectId);
				queryBuff.append("' and custId=");
				queryBuff.append(custId);
			    List examScheduleList = this.getAllHqlQuery(queryBuff.toString());
			    if(!ObjectFunctions.isNullOrEmpty(examScheduleList))
			      {
			               return  (ExamSchedules)examScheduleList.get(0);
			      }
			        return null;    
			      } catch (Exception ex) {
			        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			     }return null;
	}

	/*public String getClassIdByName(String className) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*public List getViewStudentPersonAccountDetailsByClassNameAndSectionId(long classId) {
		// TODO Auto-generated method stub
		return null;
	}*/	
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
	           //List eventInvitedUserList = (List) getSession().createSQLQuery(sqlString.toString()).list();
	           List eventInvitedUserList = this.getAll(sqlString.toString());
	           //List eventInvitedUserList = (List) getSession().createSQLQuery("select * from eventInvitedUser where accountId="+accountId+" and eventAccessed='"+ eventAccessed+"' eventId in " +eventIds).list();
	           if(!ObjectFunctions.isNullOrEmpty(eventInvitedUserList))
	           {
	               return  eventInvitedUserList;
	           }
	            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getAllCalendarEventsByEventIds(String eventsIds,long customerId) {
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
	/*public List getAllHolidayBoardMessagesList(long customerId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from SchoolHolidays where custId=");
          // queryString.append(attendanceStatus);
        		   
           //queryString.append(") and custId=");
           queryString.append(customerId);
           queryString.append(" and endDate >='");
           queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
          // queryString.append(" 11:59:59'");
           queryString.append("'");        
           List holidayBoardList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(holidayBoardList)) {
               return null;
           } else {
               return holidayBoardList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}*/
	public List getClassEventListByClassId(long classId) {
		try {
            //List classEventList = (List) getSession().createSQLQuery("select * from classEvent where classId = "+classId).list();
			List classEventList =this.getAll("select * from classEvent where classId = "+classId);
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
	public List getTeacherExamSchedulesByClassIdandSubjectId(long classId,long subjectId,long academicYear){
    	try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from ViewClassExamDetails where classSectionId=");
			queryBuff.append(classId);
			queryBuff.append(" and classSubjectId=");
			queryBuff.append(subjectId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYear);
			List scheduleList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(scheduleList)){
				return scheduleList;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return null;
	}
	
	public List getFindStaffListByIdOrName(String username, String firstName,long customerId) {

		try{
			StringBuffer queryString = new StringBuffer("select firstName,lastName,username,bloodGroup,maritalStatus,dateOfBirth,qualification1,qualification2,experience,imagePath,thumbNail from vw_staffDetails where ");
			if(!StringFunctions.isNullOrEmpty(username))
	           {
	        	   queryString.append("username = '");
	        	   queryString.append(username);
	        	   queryString.append("'");
	           }
	           else
	           {
	        	   queryString.append("firstName like '");
	        	   queryString.append(firstName);
	        	   queryString.append("%'");
	           }
			 return getSession().createSQLQuery(queryString.toString())
			 .addScalar("firstName").addScalar("lastName").addScalar("username").addScalar("bloodGroup").addScalar("maritalStatus")
			 .addScalar("dateOfBirth",StandardBasicTypes.DATE).addScalar("qualification1").addScalar("qualification2").addScalar("experience").addScalar("imagePath").addScalar("thumbNail")
			  .setResultTransformer( Transformers.aliasToBean(ViewStaffPersonAccountDetails.class))
			  .list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return null;
	}

	/*public List getAllVWStudentAttendanceListByclassIdAndDate(String classId, String attendanceDate, long customerId) {

		try 
		{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from VWStudentAttendance where  attendanceClassId= '");
		       queryString.append(classId);
	           queryString.append("' and custId=");
	           queryString.append(customerId);
	           queryString.append(" and attendanceDate like '");
	           queryString.append(attendanceDate);
	          // queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()));
	          queryString.append("%'");
	           //queryString.append("'");
	           List calendarEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(calendarEventsList)) {
	               return null;
	           } else {
	               return calendarEventsList;
	           }
			
			
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	
	}*/
	public ViewStaffPersonAccountDetails getStaffBirthDaysByAccountId(long staffId,long customerId,long staffAccountId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStaffPersonAccountDetails where ");
        	   queryString.append("staffId='");
        	   queryString.append(staffId);
        	   queryString.append("'and custId='");
        	   queryString.append(customerId);
               queryString.append("' and accountId!='");
               queryString.append(staffAccountId);
               queryString.append("' and academicYearStatus='Y''");
            List staffBirthDaysList = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(staffBirthDaysList))
           {
               return  (ViewStaffPersonAccountDetails)staffBirthDaysList.get(0);
           }
            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List<ViewStaffPersonAccountDetails> getAllStaffBirthDaysList(String date1,long custId){
		try 
		{
    	       StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStaffPersonAccountDetails where custId="+custId+" and academicYearStatus='Y' and  (dateOfBirth like '"+date1+"%')");
	           List staffBirthDayList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(staffBirthDayList)) {
	               return null;
	           } else {
	               return (List<ViewStaffPersonAccountDetails>) staffBirthDayList;
	           }
     } catch (RuntimeException ex) {
    	 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
         throw ex;
     } 
	}		
	
	public List<ViewStudentPersonAccountDetails> getAllStudentBirthDaysList(String date1,long custId){
		try 
		{
    	       StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentPersonAccountDetails where custId="+custId+" and (dateOfBirth like '"+date1+"%')");
	           List studentBirthDaysList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentBirthDaysList)) {
	               return null;
	           } else {
	               return (List<ViewStudentPersonAccountDetails>) studentBirthDaysList;
	           }
     } catch (RuntimeException ex) {
    	 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
         throw ex;
     } 
	}		
	/*public List getStudyClassObjectsByClassName(String className,long custId){
		try{
			  StringBuffer queryString = new StringBuffer();
	           queryString.append("from StudyClass where ");
	        	   queryString.append("className='");
	        	   queryString.append(className);
	        	   queryString.append("' and custId=");
	        	   queryString.append(custId);
					List studyClassList = this.getAllHqlQuery(queryString.toString());
					if (ObjectFunctions.isNullOrEmpty(studyClassList)) {
		               return null;
		           } else {
		               return studyClassList;
		           }
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
		return null;
	}*/

	public List getMyMessagesBySenderAccountIdAndIsParent(String receiverAccountId, long customerId, String isParent) {
		
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from Messages where senderAccountId='");
           queryString.append(receiverAccountId);
           queryString.append("' and custId = ");
           queryString.append(customerId);
           queryString.append(" and isParent = '");
           queryString.append(isParent);
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
	public List getstudentMarksListByExamScheduleId(long scheduleId){

		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStudentMarksDetails where scheduleId=");
           queryString.append(scheduleId);
           List studentMarksList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentMarksList)) {
               return null;
           } else {
               return studentMarksList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}
	public List<ViewStudentPersonAccountDetails> getViewStudentPersonAccountDetailsByStudyClassIdandAccount(long classId,String status,long accountId,String academicYearId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select accountId,rollNumber,firstName,lastName,dateOfBirth,className,section,CAST(imagePath as char(512)) AS imagePath,thumbNail from vw_studentDetails where classSectionId = ");
           queryString.append(classId);
           queryString.append(" and status='");
           queryString.append(status);
           queryString.append("' and academicYearId='");
           queryString.append(academicYearId);
           queryString.append("' and accountId !='");
           queryString.append(accountId);
           queryString.append("'");
           /*if(!StringFunctions.isNullOrEmpty(sectionName))
           {
        	   queryString.append("' and section='");
	           queryString.append(sectionName);
           }
           queryString.append("'");*/
           
           //List<Object[]> studentList =getSession().createSQLQuery(queryString.toString()).list();
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
		   				viewStudentPersonAccountDetails.setClassName(object[5].toString());
		   				if(!ObjectFunctions.isNullOrEmpty(object[6]))
			            {
		   					viewStudentPersonAccountDetails.setSection(object[6].toString());
			            }
		   				
			   			 if(!ObjectFunctions.isNullOrEmpty(object[7]))
			             {
			   				viewStudentPersonAccountDetails.setImagePath(object[7].toString());
			             }
			   			 if(!ObjectFunctions.isNullOrEmpty(object[8]))
			             {
			   				viewStudentPersonAccountDetails.setThumbNail(object[8].toString());
			             }
		   				
		   				viewStudentPersonAccountDetailsList.add(viewStudentPersonAccountDetails);
		   				viewStudentPersonAccountDetails = null;
	   			  }
	       		}
        	   
               return (List<ViewStudentPersonAccountDetails>) viewStudentPersonAccountDetailsList;
           }
            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
   }
	public double getPassStudentsCount(long scheduleId,double minMarks){
		  try{
				StringBuffer queryBuff=new StringBuffer();
			    queryBuff.append("select count(*) from vw_studentMarksDetails where scheduleId=");
				queryBuff.append(scheduleId);	
				queryBuff.append(" and obtainedMarks>=");
				queryBuff.append(minMarks);
				queryBuff.append(" and present='Y'");
				//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						return ((BigInteger)resultList.get(0)).doubleValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return 0;
	}
	public double getFailStudentsCount(long scheduleId,double minMarks){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from vw_studentMarksDetails where scheduleId=");
				queryBuff.append(scheduleId);	
				queryBuff.append(" and obtainedMarks<");
				queryBuff.append(minMarks);
				queryBuff.append(" and present='Y'");
				//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						return ((BigInteger)resultList.get(0)).doubleValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return 0;
	}
	public Set getExamTypeIdsByClassIdandAcademicYear(long classId,long academicYear){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select DISTINCT eid from vw_classExamDetails where classSectionId=");
				queryBuff.append(classId);	
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYear);
				//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					return ConvertUtil.convertListToSet(resultList);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	}
	public List getExamTypesByExamTypeIds(String typeIds,long academicYear){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ExamTypes where id in");
           queryString.append(typeIds);
           queryString.append(" and academicYearId=");
           queryString.append(academicYear);
           List examTypes = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(examTypes))
           {
               return  examTypes;
           }   
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List getClassExamSchedulesByExamTypeId(long examTypeId,long classId,long custId,long academicYear){
		StringBuffer queryString = new StringBuffer();
	      queryString.append("from ViewClassExamDetails where classSectionId=");
	      queryString.append(classId);
	      queryString.append(" and custId=");
	      queryString.append(custId);
	      queryString.append(" and academicYearId=");
	      queryString.append(academicYear);
	      queryString.append(" and eid=");
	      queryString.append(examTypeId);
	      log.debug(queryString.toString());
	      List classExamScheduleList = this.getAllHqlQuery(queryString.toString());
	      if (ObjectFunctions.isNullOrEmpty(classExamScheduleList)) {
	          return null;
	      } else {
	          return classExamScheduleList;
	      }
	 }
	public SchoolHolidays checkSchoolHolidayBySelectedDate(String selectedDate,long customerId,long academicYearId) {
        try {
			StringBuffer queryString = new StringBuffer();
			queryString .append("from SchoolHolidays where holidayDate = '");
			queryString.append(selectedDate);
			queryString.append("' and custId=");
			queryString.append(customerId);
			
			List attendanceList = this.getAllHqlQuery(queryString.toString());
			if (ObjectFunctions.isNullOrEmpty(attendanceList)) {
				return null;
			} else {
				return (SchoolHolidays)attendanceList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null; 
	}
	public List<KBank> getKBankBySearchKewordsKBankTypeId(String title,long kBankTypeId,long custId){
		try{
			return (List<KBank>) this.getAllHqlQuery("from KBank where custId="+custId+" and kBankTypeId="+kBankTypeId+" and title like '%"+title+"%'");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	/*public List getAllAbsentStudent(long studentId,long customerId) {
		try {
			return this.getAllHqlQuery("from ViewStudentPersonAccountDetails where studentId='"+studentId+"' and custId="+customerId);
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}*/
	public ScrapMessage saveScrapMessage(ScrapMessage scrapMessage) {
		try {
			//this.saveObject(scrapMessage);
			return (ScrapMessage) this.mergeObject(scrapMessage);
			//return scrapMessage;
		} catch (HibernateException ex) {
			ex.printStackTrace();

			throw new URTDataAccessException(ex.getMessage());
		}
	}

	public List<ScrapMessage> getAllScrapMessagesListBySenderAccId(long receiverAccountId,String status,String replyStatus,long customerId,String academicYearId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ScrapMessage where senderAccountId=");
           queryString.append(receiverAccountId);
           queryString.append(" and status = '");
           queryString.append(status);
           queryString.append("' and academicYearId = '");
           queryString.append(academicYearId);
           queryString.append("' and custId = ");
           queryString.append(customerId);
           if(!StringFunctions.isNullOrEmpty(replyStatus))
           {
        	   queryString.append(" and replyStatus = '");
               queryString.append(replyStatus);
               queryString.append("'");
           }
           
           List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return (List<ScrapMessage>) studentEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List<ScrapMessage> getAllScrapMessagesListByReceiverAccId(long receiverAccountId,String status,String replyStatus,long customerId,String academicYearId,String messageType) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ScrapMessage where");
           queryString.append(" status = '");
           queryString.append(status);
           queryString.append("' and academicYearId = '");
           queryString.append(academicYearId);
           queryString.append("' and custId = ");
           queryString.append(customerId);
           queryString.append(" and messageType = '");
           queryString.append(messageType);
           if(!StringFunctions.isNullOrEmpty(replyStatus))
           {
        	   queryString.append("' and replyStatus = '");
               queryString.append(replyStatus);
               queryString.append("'");
           }else{
        	   queryString.append("'");
           }
           queryString.append(" and (receiverAccountId=");
           queryString.append(receiverAccountId);
           queryString.append(" or senderAccountId = ");
           queryString.append(receiverAccountId+")");
           List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return (List<ScrapMessage>) studentEventsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	// using criteria ----- narahari
	/*public List getAllUsersByCustomerId(long customerId) {
		try{
			return this.getAllHqlQuery("from User where custId=" +customerId );
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return null;
	}*/
	/*public List getAllKBankTypeByCustId(long custId){
		try {
          StringBuffer queryString = new StringBuffer();
          queryString.append("from KBankType where custId =");
          queryString.append(custId);
          List kbankTypeList = this.getAllHqlQuery(queryString.toString());
          if(!ObjectFunctions.isNullOrEmpty(kbankTypeList)){
              return  kbankTypeList;
          }   
       }catch (Exception ex) {
       	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
       }
       return null;
	}*/
	public List getAllUsersByFirstNameOrLastName(String name, long customerId) {
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("from ViewAllUsers where custId=");
			queryString.append(customerId);
			queryString.append(" and (firstname like '%");
			queryString.append(name);
			queryString.append("%' or lastName like '%");
			queryString.append(name);
			queryString.append("%') order by accountId");
	        
            List classEventList = this.getAllHqlQuery(queryString.toString());
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
	public List getMyFavouritiesByUserId(long accountId){
		try {
            //List classEventList = (List) getSession().createSQLQuery("select userId,kBankId from kBankFavourite where userId = "+accountId).list();
			List classEventList = this.getAll("select userId,kBankId from kBankFavourite where userId = "+accountId);
            if(!ObjectFunctions.isNullOrEmpty(classEventList))
            {
            	return classEventList;
            }else{
            	return null;
            }
       }catch (Exception ex) {
       	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
       }
       return null;
	}
	public List getMyFavouritiesByUserIdAndKBankId(long accountId,long kBankId){
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select userId,kBankId from kBankFavourite where userId=");
			sqlString.append(accountId);
			sqlString.append(" and kBankId =");
			sqlString.append(kBankId);
	        //List kBankFavoutiteList = (List) getSession().createSQLQuery(sqlString.toString()).list();
			List kBankFavoutiteList = this.getAll(sqlString.toString());
	         if(!ObjectFunctions.isNullOrEmpty(kBankFavoutiteList))
	           {
	               return  kBankFavoutiteList;
	           }
         }catch (Exception ex) {
       	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
       }
       return null;
	}
	public KBankRating getKBankRateByKBankId(String kBankId) {
		List list = this.getAllHqlQuery("from KBankRating where kBankId=" + Long.valueOf(kBankId));
		if (ObjectFunctions.isNullOrEmpty(list)) {
			return null;
		} else {
			Collections.sort(list);
			return (KBankRating) list.get(0);
		}
	}
	public List getPlayListVideosByKeywords(String keywords) {
		return this.getAllHqlQuery("from PlayListVideo where keywords REGEXP '^["+keywords+"]'");
	}
	public List getQuizListWithCustId(long custId){
		log.debug("from Quiz where custId="+ custId);
		return this.getAllHqlQuery("from Quiz where custId="+ custId);
	}
	public List getQuizQuestionListWithCategoryId(long quizId,long custId){
		return this.getAllHqlQuery("from QuizQuestion where quizId="+quizId+" and custId="+custId+" and status='A' ");
	}
	public QuizQuestion getQuizQuestionId(long questionId,long custId){
    	try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from QuizQuestion where id=");
			queryBuff.append(questionId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			List questionList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(questionList)){
				return (QuizQuestion)questionList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return null;
    }
	public List getQuestionAnswersWithQuestionId(long questionId,long custId){
		log.debug("from ViewQuizQuestionAndAnswers where questionId="+questionId+" and custId="+custId+" order by anserOptions");
		return this.getAllHqlQuery("from ViewQuizQuestionAndAnswers where questionId="+questionId+" and custId="+custId+" order by anserOptions");
	}

	public QuestionAnswer getCorrectAnswerWithQuestionId(long questionId,long custId) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("from QuestionAnswer where questionId=");
			queryBuff.append(questionId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and (correctAnswer='Y')");
			List questionAnsList = this.getAllHqlQuery(queryBuff.toString());
			if (ObjectFunctions.isNotNullOrEmpty(questionAnsList)) {
				return (QuestionAnswer) questionAnsList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getAllAlertsByAlertStatus(long customerId,String alertType,String receiverType,boolean isAdmin,String academicYearId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           if(isAdmin)
           {
        	   queryString.append("from Messages where messageType='"+alertType+"' and academicYearId='"+academicYearId+"' ");
           }else{
        	   queryString.append("from Messages where messageType='"+alertType+"' and receiverType='"+receiverType+"' and academicYearId='"+academicYearId+"' ");
           }
    	   queryString.append(" and custId = ");
           queryString.append(customerId);
           queryString.append(" order by  createdDate DESC");
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
	/*public List getAbsentStudentsList(long classId,String studentId,long customerId){
		try {
			
			   StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentPersonAccountDetails where studentId not in");
	           queryString.append(studentId);
	           queryString.append(" and custId=");
	           queryString.append(customerId);
	           queryString.append(" and classSectionId=");
	           queryString.append(classId);
	           List studentList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(studentList))
	           {
	               return  studentList;
	           }
	            return null;  
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}*/
	public List deleteAbsentStudents(String attenDate,long classId,long customerId) {
		try{
               StringBuffer sqlString=new StringBuffer();
	           sqlString.append("delete from attendance where classId ='"); 
	           sqlString.append(classId);
	           sqlString.append("' and attendanceDate like '");
	           sqlString.append(attenDate);
	           sqlString.append(" 00:00:00'");
	           sqlString.append(" and custId = ");
	           sqlString.append(customerId); 
	           sqlString.append(" and status = 'A' ");
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows deleted:"+ row);
	            }
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return null;
	}
	public int getUpdateQuizQuestionStatus(long quizId,long custId,String selectdDate){
		try{
               StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update quizQuestion set status='A' where quizId ="); 
	           sqlString.append(quizId);
	           sqlString.append(" and endDate < '");
	           sqlString.append(selectdDate);
	           sqlString.append(" 00:00:00'");
	           sqlString.append(" and custId = ");
	           sqlString.append(Long.valueOf(custId)); 
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows updated:"+ row);
	            }else{
	            	return row;
	            }
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return 0;
	}
	public List getUpdateStudentQuizStatus(long custId){
		try{
               StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update studentQuizResults set status='IA' where custId ="); 
	           sqlString.append(Long.valueOf(custId)); 
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows updated:"+ row);
	            }
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return null;
	}
	public List getQuizQuestionListWithStudentList(long quizId,long custId){
		return this.getAllHqlQuery("from ViewStudentQuestionAnswers where quizId=" + quizId+ " and custId=" + custId+" group by questionId");
	}
	public List getQuizQuestionListWithCategoryIdAndTeacherId(long quizId,long staffId,long custId){
		return this.getAllHqlQuery("from QuizQuestion where quizId="+quizId+" and custId="+custId+" and teacherId="+staffId+" order by startDate DESC");
	}
	public String studentFailInAnnuallyExam(long studentId,long examTypeId,String minMarks){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from vw_studentMarksDetails where studId=");
				queryBuff.append(studentId);	
				queryBuff.append(" and examTypeId=");
				queryBuff.append(examTypeId);
				queryBuff.append(" and obtainedMarks<");
				queryBuff.append(minMarks);
				//queryBuff.append("'");
				//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.toString();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	}
	public PromoteClass getPromoteClassDetails(long classId,long custId){
		try{
			List promoteClass= this.getAllHqlQuery("from PromoteClass where classId ="+classId+" and custId="+custId );
			if(ObjectFunctions.isNotNullOrEmpty(promoteClass)){
				return (PromoteClass)promoteClass.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return null;
	}
	public String getStudentCountByClassId(long classId,long custId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from student where classSectionId=");
				queryBuff.append(classId);	
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and status='");
				queryBuff.append("Y");
				queryBuff.append("'");
				//List resultList=getSession().createSQLQuery(queryBuff.toString()).list();
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.toString();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	}
	public ViewStudentMarksDetails getStudentsMarksByClassIdandExamtypeId(long classId,long examTypeId,long custId,String academicYear,long studentId,long subjectId){
		try{
		List studentSubjectMarks= this.getAllHqlQuery("from ViewStudentMarksDetails where classId=" + classId+ " and custId=" + custId+" and examTypeId="+examTypeId+" and academicYearId='"+academicYear+"' and studId="+studentId+" and subjectId="+subjectId);
		if(ObjectFunctions.isNotNullOrEmpty(studentSubjectMarks)){
			return (ViewStudentMarksDetails)studentSubjectMarks.get(0);
		}
	}
	catch(Exception ex){
		ex.printStackTrace();			
	}
	return null;
	}
	public List getMarksByClassIdandExamtypeId(long classId,long custId){
		return this.getAllHqlQuery("from ViewStudentMarksDetails where classId=" + classId+ " and custId=" + custId+" group by examTypeId");
	}
	public String getStudentCountByClassNameClassId(long ClassNameClassId,long custId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from student where ClassNameClassId=");
				queryBuff.append(ClassNameClassId);	
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and status='");
				queryBuff.append("Y");
				queryBuff.append("'");
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.toString();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	}
	
	 public List getExamScheduleIdsByAcademicYearandClassIdandExamTypeId(String academicYear,String classId ,String examTypeId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select id from examSchedules where academicYearId=");
				queryBuff.append(academicYear);		
				queryBuff.append(" and classId=");		
				queryBuff.append(classId);		
				queryBuff.append(" and examTypeId=");
				queryBuff.append(examTypeId);	
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					return resultList;
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	  }
	 public List getAllStudentMarksChangesByExamScheduleIds(String scheduleIds){
		  try{
			  return this.getAllHqlQuery("from StudentMarksChanges where examScheduleId in"+scheduleIds);
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	 public int updateStudentMarksByStudentIdandScheduleId(String studentId,String scheduleId,String obtainedMarks){
		 try{
             StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update studentMarks set obtainedMarks="); 
	           sqlString.append(obtainedMarks);
	           sqlString.append(" where  studId=");
	           sqlString.append(studentId);
	           sqlString.append(" and examScheduleId=");
	           sqlString.append(scheduleId); 
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	           log.debug("The no of rows updated:"+ row);
	           return row;
		}
		catch(Exception ex){
			ex.printStackTrace();		
			return 0;
		}
	 }
	 public List getTeacherExamScheduleIdsByClassIdandSubjectId(long classId,long subjectId,long academicYear){
		 try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select id from examSchedules where classId=");
				queryBuff.append(classId);
				queryBuff.append(" and subjectId=");
				queryBuff.append(subjectId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYear);
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					return resultList;
				}
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	 public void removeStudentMarksChangesByClassIdandSubjectIdandAcademicYear(String scheduleIds){
		 try{
	            StringBuffer sqlString=new StringBuffer();
		           sqlString.append("delete from studentMarksChanges where examScheduleId in"); 
		           sqlString.append(scheduleIds);
		           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
		            if(row == 0)
		            {
		                log.debug("The no of rows deleted:"+ row);
		            }
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
	 }
	 public ViewStudentMarksDetails getStudentsMarksByStudentIdandExamTypeIdandSubjectId(long examTypeId,long custId,long studentId,long subjectId){
			try{
				log.debug("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId);
				List studentSubjectMarks= this.getAllHqlQuery("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId);
				if(ObjectFunctions.isNotNullOrEmpty(studentSubjectMarks)){
					return (ViewStudentMarksDetails)studentSubjectMarks.get(0);
				}
			}catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	public List<ReplyScrapMessage> getAllReplyMessagesListByScrapId(long scrapId,long custId) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" from ReplyScrapMessage where scrapId = " + scrapId);
			sb.append(" and custId = " + custId); 
			List tempList = this.getAllHqlQuery(sb.toString());
			if (ObjectFunctions.isNullOrEmpty(tempList)) {
				return null;
			} else {
				return (List<ReplyScrapMessage>) tempList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<VWStudentAttendance> getStudentsAttendance(long custId, long academicYearId,int month,long classId, long classSectionId, String date) {
		
		try {
			List tempList =null;	
			StringBuffer sb = new StringBuffer();
			sb.append(" from VWStudentAttendance where custId = " + custId);
			sb.append(" and academicYearId = " + academicYearId);
			//sb.append(" and classId = " + classId);
			sb.append(" and classSectionId = " + classSectionId);
			// include class and sectionId
			StringBuffer sbdate = new StringBuffer();
			if (month > 0)
				sbdate.append(" and monthNum = " + month);

			if (!StringFunctions.isNullOrEmpty(date)) {

				sbdate.append(" and ( attendanceDate >= '");
				sbdate.append(StringFunctions.getDateTimeBegin(date));
				sbdate.append("' and attendanceDate <= '");
				sbdate.append(StringFunctions.getDateTimeEnd(date));
				sbdate.append("') ");
			}
				sbdate.append("order by firstName,lastName");
			tempList = this.getAllHqlQuery(sb.toString() + sbdate.toString());
			
			/*if (ObjectFunctions.isNullOrEmpty(tempList)) {
				return this.getAllHqlQuery(sb.toString());
			}
			else*/
				return (List<VWStudentAttendance>) tempList;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return  null;
	}
	public long getStudentsAttendanceByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,String attendanceStatus) {

		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select count(*) from vw_StudentDailyAttendance where classSectionId=");
           queryString.append(classSectionId);
           queryString.append(" and month = ");
           queryString.append(monthId);
           queryString.append(" and custId = ");
           queryString.append(custId);
           queryString.append(" and academicYearId=");
           queryString.append(academicYearId);
           queryString.append(" and present='");
           queryString.append(attendanceStatus);
           queryString.append("'");
           List resultList=this.getAll(queryString.toString());
			if(ObjectFunctions.isNullOrEmpty(resultList)){
				return 0;
			}else if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					return ((BigInteger)resultList.get(0)).longValue();
				}else
					return 0;
           /*List studentEventsList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
               return null;
           } else {
               return studentEventsList;
           }*/
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return 0;
	}
	 public List getStaffAttendanceByPresentStatusAndLeaveRequestStaus(long custId,long academicYearId, long accountId, String present, String leaveRequest) {
		try {
		    StringBuffer queryString = new StringBuffer();
		    queryString.append("from VWStaffAttendance where ");
		    queryString.append(" custId = ");
		    queryString.append(custId);
		    queryString.append(" and accountId = ");
		    queryString.append(accountId);
		    queryString.append(" and academicYearId=");
		    queryString.append(academicYearId);
		    queryString.append(" and present='");
		    queryString.append(present);
		    queryString.append("'");
		    if(StringFunctions.isNullOrEmpty(leaveRequest))
			queryString.append(" and leaveRequest<>'");
		    else
			queryString.append(" and leaveRequest='");
		    queryString.append(leaveRequest);
		    queryString.append("' order by attendanceDate DESC");
		    //log.debug(queryString.toString());
		    return this.getAllHqlQuery(queryString.toString());
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	    }
	 public List<ViewStudentMarksDetails> getLatestUpdatedStudentMarksExamTypeIdsByClassSectionIdAndCustId(long classSectionId,long custId){
		 try{
			 StringBuffer sqlString = new StringBuffer();
				sqlString.append("from ViewStudentMarksDetails where classSectionId=");
				sqlString.append(classSectionId);
				sqlString.append(" and custId =");
				sqlString.append(custId);
				sqlString.append(" and examDate is not null group by examType order by lastUpdatedDate DESC");
				return (List<ViewStudentMarksDetails>) this.getAllHqlQuery(sqlString.toString());
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	 }
	/* public ViewStudentPersonAccountDetails getStudentDetailsByRollNumberAndAccountId(long userId,String rollNumber){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ViewStudentPersonAccountDetails where accountId=");
				queryBuff.append(userId);
				queryBuff.append(" and rollNumber='");
				queryBuff.append(rollNumber);
				queryBuff.append("'");
				queryBuff.append(" and status='Y'");
			    List examScheduleList = this.getAllHqlQuery(queryBuff.toString());
			    if(!ObjectFunctions.isNullOrEmpty(examScheduleList))
			      {
			               return (ViewStudentPersonAccountDetails)examScheduleList.get(0);
			      }
			        return null;    
			      } catch (Exception ex) {
			        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			     }return null;
	}*/
	 public List getTeacherSubjectsByStaffIdAndClassIdGroupByclassIdSubjectId(long staffId,long classId,long academicYearId){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStaffSubjectsDetails vss where vss.staffId=");
	           queryString.append(staffId);
	           queryString.append(" and vss.classId=");
	           queryString.append(classId);
	           queryString.append(" and vss.academicYearId=");
	           queryString.append(academicYearId);
	           queryString.append(" group by vss.classId,vss.classSubjectId");
	           List staffSubjectsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(staffSubjectsList)) {
	               return null;
	           } else {
	               return staffSubjectsList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
	 public double getStudentsTotalMarksByClassIdTypeIdAndSubjId(long classSectionId,long examTypeId,long subjectId,long academicYearId){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("select sum(vss.obtainedMarks) from ViewStudentMarksDetails vss where vss.examTypeId=");
	           queryString.append(examTypeId);
	           queryString.append(" and vss.classSectionId=");
	           queryString.append(classSectionId);
	           queryString.append(" and vss.academicYearId=");
	           queryString.append(academicYearId);
	           queryString.append(" and vss.subjectId=");
	           queryString.append(subjectId);
	           queryString.append(" and vss.present='Y'");
	           List totalMarks =  this.getAll(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(totalMarks)){
	        	   return Double.valueOf(totalMarks.get(0).toString());
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return 0;
		}
	 public long getStudentsCountByClassIdTypeIdAndSubjId(long classSectionId,long examTypeId,long subjectId,long academicYearId){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentMarksDetails vss where vss.examTypeId=");
	           queryString.append(examTypeId);
	           queryString.append(" and vss.classSectionId=");
	           queryString.append(classSectionId);
	           queryString.append(" and vss.academicYearId=");
	           queryString.append(academicYearId);
	           queryString.append(" and vss.subjectId=");
	           queryString.append(subjectId);
	           queryString.append(" and vss.present='Y' group by studId");
	           List studentsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentsList)) {
	               return 0;
	           } else {
	               return studentsList.size();
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return 0;
		}
	 public List getIsClassTeacherByClassSectionAndStaffIdAndAcademicYear(long classSectionId,long staffId,long academicYearId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ClassTeacher where teacherId=");
				queryBuff.append(staffId);
				queryBuff.append(" and studyClassId=");
				queryBuff.append(classSectionId);
				queryBuff.append(" and classTeacher='Y'");
				return this.getAllHqlQuery(queryBuff.toString());
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	 public long getPassStudentsCount(long classSectionId,long examTypeId,long subjectId,long academicYearId,double passMarks){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("select studId,sum(obtainedMarks) from vw_studentsLatestExamMarksDetails where examTypeId=");
	           queryString.append(examTypeId);
	           queryString.append(" and classSectionId=");
	           queryString.append(classSectionId);
	           queryString.append(" and academicYearId=");
	           queryString.append(academicYearId);
	           queryString.append(" and subjectId=");
	           queryString.append(subjectId);
	           queryString.append(" and present='Y' and status='Y' group by studId having sum(obtainedMarks)>=");
	           queryString.append(passMarks);
	           //List studentsList = this.find(queryString.toString());
	           List studentsList = this.getAll(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentsList)) {
	               return 0;
	           } else {
	               return studentsList.size();
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return 0;
		}
	 public long getFailStudentsCount(long classSectionId,long examTypeId,long subjectId,long academicYearId,double passMarks){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("select studId,sum(obtainedMarks) from vw_studentsLatestExamMarksDetails  where examTypeId=");
	           queryString.append(examTypeId);
	           queryString.append(" and classSectionId=");
	           queryString.append(classSectionId);
	           queryString.append(" and academicYearId=");
	           queryString.append(academicYearId);
	           queryString.append(" and subjectId=");
	           queryString.append(subjectId);
	           queryString.append(" and present='Y' and status='Y' group by studId having sum(obtainedMarks)<");
	           queryString.append(passMarks);
	           //List studentsList = this.find(queryString.toString());
	           List studentsList = this.getAll(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentsList)) {
	               return 0;
	           } else {
	               return studentsList.size();
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return 0;
		}
	 public int getTransportSmsCount(long custId,long academicYearId){
		 try{
			 StringBuffer queryString = new StringBuffer();
	         queryString.append("select sum(m.sentSms) from messages m where m.status='TR'");
	         queryString.append(" and m.custId="+ custId);
	         queryString.append(" and m.academicYearId="+ academicYearId);
			 List transportSmsCountList =  this.getAll(queryString.toString());
	         if(ObjectFunctions.isNullOrEmpty(transportSmsCountList)){
	        	   return 0;
	         } else {
	        	 if(ObjectFunctions.isNullOrEmpty(transportSmsCountList.get(0)))
	        		 return 0;
	        	 else
	        	   return Integer.valueOf(transportSmsCountList.get(0).toString());
	         }
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return 0;
	 }
	 public ViewStaffPersonAccountDetails getStaffDetailsByAccountIdandStatus(long accountId,long academicYearId,String status,long custId){
			try 
			{
				List studentList= this.getAllHqlQuery("from ViewStaffPersonAccountDetails where accountId=" + accountId+ " and academicYearId='" + academicYearId+"' and status='"+status+"' and custId = "+ custId);
				if(!ObjectFunctions.isNullOrEmpty(studentList)){
					return (ViewStaffPersonAccountDetails)studentList.get(0);
				}
			} catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
	 public ScrapMessage getScrapMessageBySenderAndReceiverId(long senderAccountId,long receiverAccountId,long customerId,long academicYearId) {
			try 
			{
				//select * from scrapMessage where (receiverAccountId=501 and senderAccountId=470) or (receiverAccountId=470 and senderAccountId=501);
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ScrapMessage where ");
	           
	           queryString.append("(");
	           
	           queryString.append("(");
	           queryString.append("receiverAccountId= ");
	           queryString.append(receiverAccountId);
	           queryString.append(" and senderAccountId = ");
	           queryString.append(senderAccountId);
	           queryString.append(")");
	           
	           queryString.append("or");
	           
	           queryString.append("(");
	           queryString.append("receiverAccountId= ");
	           queryString.append(senderAccountId);
	           queryString.append(" and senderAccountId = ");
	           queryString.append(receiverAccountId);
	           queryString.append(")");
	           
	           queryString.append(")");
	           
	           queryString.append(" and academicYearId = ");
	           queryString.append(academicYearId);
	           queryString.append(" and custId = ");
	           queryString.append(customerId);
	           List scrapMessageObj = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(scrapMessageObj)) {
	               return null;
	           } else {
	               return (ScrapMessage) scrapMessageObj.get(0);
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		}
	 public List getLatestSmsCount(long custId,long academicYearId){
		 try{
			 StringBuffer queryString = new StringBuffer();
			 queryString.append("select sum(m.sentSms),m.createdDate,m.status from messages m where m.messageType='SMS'");
	         queryString.append(" and m.custId="+ custId+" and m.academicYearId="+academicYearId);
	         queryString.append(" group by m.createdDate DESC");
			 List feeReminderSmsCountList =  this.getAll(queryString.toString());
	         if(!ObjectFunctions.isNullOrEmpty(feeReminderSmsCountList)){
	        	   return feeReminderSmsCountList;
	         }
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		return null;
	 }
	 public List<ViewStudentLeaveDetails> getAllFutureStudentLeavesByStatusAndRoleNameAndSupervisorId(long customerId, String leaveStatus, String roleName,long supervisorId,long academicYearId) {

	       try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ViewStudentLeaveDetails where leaveStatus= '");
	            queryString.append(leaveStatus);
	            queryString.append("' and custId =");
	            queryString.append(customerId);
	            queryString.append(" and roleName ='");
	            queryString.append(roleName);
	            queryString.append("'");
	            queryString.append(" and leaveSupervisorId ='");
	            queryString.append(supervisorId);
	            queryString.append("' and academicYearId='");
	            queryString.append(academicYearId);
	            queryString.append("' order by startDate desc");
	            return (List<ViewStudentLeaveDetails>) this.getAllHqlQuery(queryString.toString());
	           }
	       catch (Exception ex) {
	           log.error("getting getAllFutureStudentLeavesByStatusAndRoleNameAndSupervisorId failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
	 public List<ViewStudentMarksDetails> getStudentsMarksListByStudentIdandExamTypeIdandSubjectId(long examTypeId,long custId,long studentId,long subjectId){
			try{
				log.debug("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId);
				List studentSubjectMarks= this.getAllHqlQuery("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId);
				if(ObjectFunctions.isNotNullOrEmpty(studentSubjectMarks)){
					return (List<ViewStudentMarksDetails>) studentSubjectMarks;
				}
			}catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	 
	/* public ViewStudentMarksDetails getStudentsMarksByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId, long custId, long studentId, long subjectId,long subTypeId) {
			try{
				log.debug("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId +" and subTypeId = "+ subTypeId);
				List studentSubjectMarks= this.getAllHqlQuery("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId +" and subTypeId = "+ subTypeId);
				if(ObjectFunctions.isNotNullOrEmpty(studentSubjectMarks)){
					return (ViewStudentMarksDetails)studentSubjectMarks.get(0);
				}
			}catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }*/
	 
	 public List<ViewStudentMarksDetails> getStudentsMarksListByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId, long custId, long studentId, long subjectId,long subTypeId) 
	 {
			try{
				log.debug("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId +" and subTypeId = "+ subTypeId);
				List studentSubjectMarks= this.getAllHqlQuery("from ViewStudentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subjectId="+subjectId +" and subTypeId = "+ subTypeId);
				if(ObjectFunctions.isNotNullOrEmpty(studentSubjectMarks)){
					return (List<ViewStudentMarksDetails>) studentSubjectMarks;
				}
			}catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	 public String getSUMStudentsMarksByStudentIdandExamTypeIdandSubjectIdAndSubTypeId(long examTypeId, long custId, long studentId, long subTypeId) {
			try{
				 StringBuffer queryString = new StringBuffer();
		           queryString.append("select sum(obtainedMarks) from vw_studentMarksDetails where custId=" + custId+" and examTypeId="+examTypeId+" and studId="+studentId+" and subTypeId = "+ subTypeId);
		           log.debug(queryString.toString()); 
		           List studentSubjectMarks=this.getAll(queryString.toString());	
		           
				if(ObjectFunctions.isNotNullOrEmpty(studentSubjectMarks)){
					if(!ObjectFunctions.isNullOrEmpty(studentSubjectMarks.get(0))){
						Double totalMarks=((Double)studentSubjectMarks.get(0)); 
						return  String.valueOf(totalMarks);
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	 public void removeStaffAttendanceByaccountIdAndDates(long accountId,String startDate,String endate,long custId,long academicYearId){
		 try{
			 
			 StringBuffer sqlString=new StringBuffer();
	           sqlString.append("delete from attendance where accountId="); 
	           sqlString.append(accountId);
	           sqlString.append(" and attendanceDate >='");
	           sqlString.append(startDate);
	           sqlString.append(" 00:00:00");
	           sqlString.append("' and attendanceDate <='");
	           sqlString.append(endate);
	           sqlString.append(" 00:00:00");
	           sqlString.append("' and academicYearId =");
	           sqlString.append(academicYearId);
	           sqlString.append(" and custId = ");
	           sqlString.append(custId);
	           log.debug(sqlString.toString());
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows deleted:"+ row);
	            }
		 }catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 
	 }
	
	 public List<VWStudentDailyAttendance> getStudentDailyAttendance(long custId, long classId, String date) {
			
			try {
				List tempList =null;	
				StringBuffer sb = new StringBuffer();
				sb.append(" from VWStudentDailyAttendance where custId = " + custId);
				//sb.append(" and classId = " + classId);
				sb.append(" and classSectionId = " + classId);
				// include class and sectionId
				StringBuffer sbdate = new StringBuffer();
				if (!StringFunctions.isNullOrEmpty(date)) {

					sbdate.append(" and ( attendanceDate >= '");
					sbdate.append(StringFunctions.getDateTimeBegin(date));
					sbdate.append("' and attendanceDate <= '");
					sbdate.append(StringFunctions.getDateTimeEnd(date));
					sbdate.append("') ");
				}
					sbdate.append("order by studentname");
				tempList = this.getAllHqlQuery(sb.toString() + sbdate.toString());
				
				/*if (ObjectFunctions.isNullOrEmpty(tempList)) {
					return this.getAllHqlQuery(sb.toString());
				}
				else*/
					return (List<VWStudentDailyAttendance>) tempList;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return  null;
		}
		
	 public long getStudentsMonthlyAttendanceByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,long studentId) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("select sum(noOfPresentDays) from vw_StudentMonthlyAttendance where ");
	           queryString.append(" custId = ");
	           queryString.append(custId);
	           queryString.append(" and academicYearId=");
	           queryString.append(academicYearId); 
	           queryString.append(" and classSectionId = ");
	           queryString.append(classSectionId);
	           queryString.append(" and month = ");
	           queryString.append(monthId);
	          
	           if(studentId !=0)
	           {
	        	   queryString.append(" and studentId = ");
		           queryString.append(studentId);
		           queryString.append(" group by classSectionId,month,custId,academicYearId,studentId"); 
	           }
	           else
	           {
	        	   queryString.append(" group by classSectionId,month,custId,academicYearId");
	           }
	           //log.debug(queryString.toString());
	           List resultList=this.getAll(queryString.toString());
				if(ObjectFunctions.isNullOrEmpty(resultList)){
					return 0;
				}else if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						return ((Double)resultList.get(0)).longValue();
					}else
						return 0;
	           /*List studentEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
	               return null;
	           } else {
	               return studentEventsList;
	           }*/
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return 0;
		}
	 
	 public int getTotalWorkingDaysByClassSectionIdAndMonthId(long classSectionId,int monthId,long academicYearId,long custId,long studentId) {

			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("select sum(totalWorkingDays) from vw_StudentMonthlyAttendance where classSectionId=");
	           queryString.append(classSectionId);
	           queryString.append(" and month = ");
	           queryString.append(monthId);
	           queryString.append(" and custId = ");
	           queryString.append(custId);
	           queryString.append(" and academicYearId=");
	           queryString.append(academicYearId); 
	           if(studentId !=0)
	           {
	        	   queryString.append(" and studentId = ");
		           queryString.append(studentId);
		           queryString.append(" group by classSectionId,month,custId,academicYearId,studentId"); 
	           }
	           else
	           {
	        	   queryString.append(" group by classSectionId,month,custId,academicYearId");
	           }
	           List resultList=this.getAll(queryString.toString());
				if(ObjectFunctions.isNullOrEmpty(resultList)){
					return 0;
				}else if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						return (int) ((Double)resultList.get(0)).longValue();
					}else
						return 0;
	           /*List studentEventsList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(studentEventsList)) {
	               return null;
	           } else {
	               return studentEventsList;
	           }*/
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return 0;
		}
	 
	 public void removeStudentDailyAttendance(long studentId,String startDate,String endate){
		 try{
			 
			 StringBuffer sqlString=new StringBuffer();
	           sqlString.append("delete from studentDailyAttendance where studentId="); 
	           sqlString.append(studentId);
	           sqlString.append(" and attendanceDate >='");
	           sqlString.append(startDate);
	           sqlString.append(" 00:00:00");
	           sqlString.append("' and attendanceDate <='");
	           sqlString.append(endate);
	           sqlString.append(" 00:00:00' ");
	           log.debug(sqlString.toString());
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows deleted:"+ row);
	            }
		 }catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 
	 }
	 public StaffDailyAttendance getStaffDailyAttendance(long accountId, String date) {
			try {
				StringBuffer sb = new StringBuffer();
				sb.append(" from StaffDailyAttendance where accountId = " + accountId);
				// include class and sectionId
				sb.append(" and attendanceDate = '" + date+ "'");
				List tempList = this.getAllHqlQuery(sb.toString());
				if (ObjectFunctions.isNullOrEmpty(tempList)) {
					return null;
				} else {
					return (StaffDailyAttendance) tempList.get(0);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	 public List getAllMessagesByAccountId(String status, long accountId,String academicYearId,Date messageDate) {
			try 
			{
			   StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewMessagesDetails where status='");
	           queryString.append(status);
	    	   queryString.append("' and accountId = ");
	           queryString.append(accountId);
	           queryString.append(" and academicYearId = '");
	           queryString.append(academicYearId);
	           if(!ObjectFunctions.isNullOrEmpty(messageDate)){
	            queryString.append("' and messageDate >= '");
	            queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, messageDate));
	           }
	           queryString.append("' order by  createdDate DESC");
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
	 public List<ViewStaffLeaveDetails> getAllFutureLeavesByStatusAndRoleName(long customerId,String leaveStatus,String roleName,long academicYearId){
		 try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ViewStaffLeaveDetails where custId= ");
	            //queryString.append(leaveStatus);
	            //queryString.append("' and custId =");
	            queryString.append(customerId);
	            queryString.append(" and roleName ='");
	            queryString.append(roleName);
	            queryString.append("' and academicYearId =");
	            queryString.append(academicYearId);
	            queryString.append(" and (startDate >= '");
	            queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
	           // queryString.append("');
	            //queryString.append("' or startDate >= '");
	            ///queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
	            queryString.append("') order by startDate desc");
	            List leavesList =  this.getAllHqlQuery(queryString.toString());
		           if(!ObjectFunctions.isNullOrEmpty(leavesList))
		           {
		               return (List<ViewStaffLeaveDetails>) leavesList;
		           }
	           }
	       catch (Exception ex) {
	           log.error("getAllFutureLeavesByStatusAndRoleName failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	 }
	 public void updateRoleByUserId(long roleId,long userId){
			try{
	               StringBuffer sqlString=new StringBuffer();
	               sqlString.append("update UserRoles set roleId ="); 
		           sqlString.append(roleId);
		           sqlString.append(" where userId ="); 
		           sqlString.append(userId); 
		           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
		            if(row == 0)
		            {
		                log.debug("The no of rows updated:"+ row);
		            }
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
		}

	public List<BigInteger> getTeacherIdsByStudyClassSubject(long custId,long academicYearId, long studyClassId, long subjectId) {
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("select teacherId from classTeacher where custId=");
			queryString.append(custId);
			queryString.append(" and academicYearId=").append(academicYearId);
			queryString.append(" and studyClassId=").append(studyClassId);
			queryString.append(" and studySubjectId =");
			queryString.append(subjectId);
			return (List<BigInteger>) this.getAll(queryString.toString());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	 
}