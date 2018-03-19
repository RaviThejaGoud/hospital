package com.urt.persistence.impl.admin;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.dao.DataAccessException;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.urt.exception.base.URTDataAccessException;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.admin.AdminDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.Medium;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.VWStaffAttendance;
import com.urt.persistence.model.common.VWStudentDailyAttendance;
import com.urt.persistence.model.common.ViewHostelStudentDailyAttendance;
import com.urt.persistence.model.common.ViewUserRoles;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.persistence.model.exam.OverAllGrades;
import com.urt.persistence.model.exam.SchoolGrades;
import com.urt.persistence.model.exam.ScoreCardTemplates;
import com.urt.persistence.model.exam.SubType;
import com.urt.persistence.model.exam.Syllabus;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.exam.ViewExamSchedule;
import com.urt.persistence.model.exam.ViewStaffSubjectsDetails;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.fee.ViewStudentDeleteFeeDetails;
import com.urt.persistence.model.fee.ViewStudentsExcessPaymentDetails;
import com.urt.persistence.model.hostel.Building;
import com.urt.persistence.model.secretary.ViewBudgetRequestDetails;
import com.urt.persistence.model.secretary.ViewMeetingRequestDetails;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.PromoteClass;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Section;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StaffElgibleSubjects;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentAcademicPerformance;
import com.urt.persistence.model.study.StudentMarks;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.TransferCertificate;
import com.urt.persistence.model.study.ViewClassFeeDetails;
import com.urt.persistence.model.study.ViewClassSectionTeacher;
import com.urt.persistence.model.study.ViewClassSubjectsSettings;
import com.urt.persistence.model.study.ViewOnlineApplicationStudentClassFees;
import com.urt.persistence.model.study.ViewStaffLeaveDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentFeePaidAndNotPaidDetails;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentTransportFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentsLatestExamMarksDetails;
import com.urt.persistence.model.study.ViewStudyClassSubjects;
import com.urt.persistence.model.transport.ViewAssignedVehiclestoRoutesWithBoardingPoints;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.OnlineApplicationDetailsView;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.views.VWStudentPaymentDetails;
import com.urt.persistence.model.webservice.ViewFee;
import com.urt.util.common.RayGunException;

public class AdminDaoHibernate extends UniversalHibernateDao implements AdminDao
{
	private static final Log log = LogFactory.getLog(AdminDaoHibernate.class);
	
	public List<ViewStaffPersonAccountDetails> getAllTeacherListByStatus(long custId,String status){
		List<ViewStaffPersonAccountDetails> viewStaffPersonAccountDetailsList = new ArrayList<ViewStaffPersonAccountDetails>();
		//@ Ganesh, removed "and academicYearStatus='Y' " because if we promote staff next academic year we will not get staff lint because we are not creating new staff record to every academic year.
		List<Object[]> staffsObjList =this.getAll("select staffId,accountId,firstName,lastName,roleDescription,fullName from vw_staffDetails where custId="+custId+" and roleName in ('ROLE_TEACHER','ROLE_ASST_TEACHER','ROLE_HOD','ROLE_PRINCIPAL','ROLE_VICEPRINCIPAL','ROLE_ADMIN_COORDINATOR') and status='"+status+"' order by FIELD(roleName,'ROLE_PRINCIPAL','ROLE_VICEPRINCIPAL','ROLE_HOD','ROLE_ADMIN_COORDINATOR','ROLE_TEACHER','ROLE_ASST_TEACHER')");
		if(ObjectFunctions.isNotNullOrEmpty(staffsObjList)){
			for(Object[] object : staffsObjList)
    		{
			  if(!ObjectFunctions.isNullOrEmpty(object)){
				  ViewStaffPersonAccountDetails viewStaffPersonAccountDetails=new ViewStaffPersonAccountDetails();
				  viewStaffPersonAccountDetails.setStaffId(Long.valueOf(object[0].toString()));
				  viewStaffPersonAccountDetails.setAccountId(Long.valueOf(object[1].toString()));
				  viewStaffPersonAccountDetails.setFirstName(object[2].toString());
				  viewStaffPersonAccountDetails.setRoleDescription(object[4].toString());
				  viewStaffPersonAccountDetails.setFullName(object[5].toString());
				  if(!ObjectFunctions.isNullOrEmpty(object[3])){
					  viewStaffPersonAccountDetails.setLastName(object[3].toString());
				  }
				  viewStaffPersonAccountDetailsList.add(viewStaffPersonAccountDetails);
				  viewStaffPersonAccountDetails = null;
			  }
    		}
		}
		return (List<ViewStaffPersonAccountDetails>) viewStaffPersonAccountDetailsList;
		 //return this.getAllHqlQuery("from ViewStaffPersonAccountDetails where custId="+custId+" and roleName in ('ROLE_TEACHER','ROLE_HOD','ROLE_PRINCIPAL') and status='"+status+"' and academicYearStatus='Y' order by FIELD(roleName,'ROLE_PRINCIPAL','ROLE_HOD','ROLE_TEACHER')");
	}
	public List<BigInteger> getStaffElgibleSubjectsListByStudySubjectId(long studySubjectId,String staffIds) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select staffId from staffElgibleSubjects where studySubjectId =");
           queryString.append(studySubjectId);
           queryString.append(" and staffId not in (").append(staffIds).append(")");
           return (List<BigInteger>) this.getAll(queryString.toString());  
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	
	public List<ViewStaffPersonAccountDetails> getViewStaffDetailsListByStaffIds(String staffIds, long customerId, long academicYearId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStaffPersonAccountDetails where staffId in (");
           queryString.append(staffIds);
    	   queryString.append(") and custId = ");
           queryString.append(customerId);    
           queryString.append(" and academicYearId <= ");
           queryString.append(academicYearId);  
           queryString.append(" and status = 'Y' ");
           //return (List<ViewStaffPersonAccountDetails>) this.getAllHqlQuery(queryString.toString());
           return (List<ViewStaffPersonAccountDetails>) this.getAllHqlQuery(queryString.toString());
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	public Section getSectionBySectionName(String sectionName,long custId){
		 try{
			 //List sectionList = this.getAllHqlQuery("from Section where section='"+sectionName+"' and custId="+custId);
			 List sectionList = this.getAllHqlQuery("from Section where section='"+sectionName+"' and custId="+custId);
			 if (!ObjectFunctions.isNullOrEmpty(sectionList)) {
					return (Section) sectionList.get(0);
				} 
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	public void updateClassesOrder(long classId,long sortingOrder){
		  try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update class set sortingOrder ="); 
	           sqlString.append(sortingOrder);
	           sqlString.append(" where id=");
	           sqlString.append(classId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	/*Changed by seshu on 8th April.Removed unnecessary code.*/
	 public List<CommonType> getAllCommonTypesByCustIdandType(long custId,String type){
		 try{
			 StringBuffer queryString = new StringBuffer();
		        queryString.append("from CommonType where custId= ");
		        queryString.append(custId);
		        if(!StringFunctions.isNullOrEmpty(type))
		        {
		        	queryString.append(" and type='");
			        queryString.append(type);
			        queryString.append("'");
		        }
			 //return (List<CommonType>) this.getAllHqlQuery(queryString.toString());
		        return (List<CommonType>) this.getAllHqlQuery(queryString.toString());
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	 }
	 public List<StudySubject> getStudySubjectByCustIdAndAcademicYear(long custId,long academicYear){
		try {
			StringBuffer query = new StringBuffer();
			query.append("from StudySubject where custId=").append(custId).append(" and academicYearId=");
			query.append(academicYear);
			//return (List<StudySubject>) this.getAllHqlQuery(query.toString());
			return (List<StudySubject>) this.getAllHqlQuery(query.toString());
	    } catch (Exception ex) {
	    	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
	    }
	    return null;
	}
	 public List<ClassTeacher> getClassTeacherByStudyClass(long studyClassId,long academicYear) {
	        try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ClassTeacher cteacher where ");
	            queryString.append(" cteacher.studyClass =");
	            queryString.append(studyClassId);
	            queryString.append(" and cteacher.academicYear=");
	            queryString.append(academicYear);
	            queryString.append(" and cteacher.staff.status = 'Y'");
	            //return (List<ClassTeacher>) this.getAllHqlQuery(queryString.toString());
	            return (List<ClassTeacher>) this.getAllHqlQuery(queryString.toString());
	        } catch (RuntimeException ex) {
	            log.error("Get failed", ex);
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	            throw ex;
	        }
		} 
	 
	 public void updateClassNameByClassId(String className,long classId){
		 try{
			 StringBuffer query = new StringBuffer("Update studyClass set className='");
			 query.append(className);
			 query.append("' where classNameClassId=");
			 query.append(classId);
			 getSession().createSQLQuery(query.toString()).executeUpdate();
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	 }
	 public List getRemainingClassIdsByExamTypeIdAndClassIds(long examtypeId,String classIds){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("select classSectionId from classSectionExamTypes where examTypeId=");
	           queryString.append(examtypeId);
	           queryString.append(" and classSectionId not in(");
	           queryString.append(classIds);
	           queryString.append(")");
	           return this.getAll(queryString.toString());  
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
			return null;
	}
	 public List<ClassName> getClassesByClassIdsAndAdmissionStatus(long custId,long academicYearId,String status,String classIds,boolean isClassIdIn){
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("from ClassName where custId="+custId+" and academicYearId="+academicYearId);			
			if (!StringFunctions.isNullOrEmpty(status)){
				queryString.append(" and admissionsOpen='"+status+"'");
			}
			if	(!StringFunctions.isNullOrEmpty(classIds)){
				if (isClassIdIn){
					if(classIds.contains("("))
						queryString.append(" and id in "+classIds+"");
					else
					queryString.append(" and id in("+classIds+")");
				}
				else {			
					if(classIds.contains("("))
						queryString.append(" and id not in "+classIds+"");
					else
					queryString.append(" and id not in ("+classIds+")");	
				}
			}
			queryString.append(" order by sortingOrder");
			return (List<ClassName>) this.getAllHqlQuery(queryString.toString());				
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public Integer fetchStudentAbsentiesCount(long studentId,String endDate){
		StringBuffer sqlString = new StringBuffer("select COUNT(attendanceDate) from studentDailyAttendance where studentId =")
		.append(studentId).append(" and attendanceDate<='")
		.append(endDate).append("' and present='N'");
		SQLQuery qry = getSession().createSQLQuery(sqlString.toString());
		List result =qry.list();
		if(ObjectFunctions.isNotNullOrEmpty(result) && !ObjectFunctions.isNullOrEmpty(result.get(0)))
			return new BigInteger(String.valueOf(result.get(0))).intValue(); 
		else
			return 0;	
	}
	 
	private final static String GET_ALL_INFO = "select sm.studId,et.examType, st.subTypeName, ss.name , es.maxMarks, " +
	" sm.obtainedMarks+ sm.moderationMarks ,sm.present from examSchedules  es"+
	" join examTypes et on es.examTypeId = et.id join subtype st on es.subTypeId = st.id"+
	" join studentMarks sm on es.id = sm.examScheduleId join student stud on stud.id = sm.studId"+
	" join studySubject ss on ss.id  = es.classSubjectId where "+
	" es.classSectionId = ? and stud.status ='Y'"
	+ " order by sm.studId,et.id,  ss.id ,st.id";
	
	
	public Integer fetchTotalWorkingDays(long academicYearId,String endDate){
		StringBuffer sqlString = new StringBuffer("select (DATEDIFF('")
		.append(endDate).append("', academicYear.startDate ) -count(holidayDate))+1  from academicYear join schoolHolidays on academicYear.id  = schoolHolidays.academicYearId ")
		.append(" where   holidayDate between academicYear.startDate and '")
		.append(endDate).append("' and  academicYear.id=").append(academicYearId);
		SQLQuery qry = getSession().createSQLQuery(sqlString.toString());
		List result =qry.list();
		if(ObjectFunctions.isNotNullOrEmpty(result) && !ObjectFunctions.isNullOrEmpty(result.get(0)))
			return new BigInteger(String.valueOf(result.get(0))).intValue();
		else
			return 0;
	}
	/*@SuppressWarnings("unchecked")
	public List<StudyClass> getStudyClassesByCustId(long custId,long academicYearId){
			List <StudyClass> sclist = (List<StudyClass>)this.getAllHqlQuery("from StudyClass where custId="+custId+" and academicYearId="+academicYearId);
			Collections.sort(sclist);
			return sclist;
	}*/
	
	@SuppressWarnings("unchecked")
	public StudyClass getclassByClassAndsection(String className,String sectionName,long customerId,long academicYearId){
	       try {	    	   
	    	   	StringBuffer queryString = new StringBuffer();
	    	   	queryString.append("from StudyClass where custId="+customerId+" and academicYearId="+academicYearId+" and className= '"+className+"'");  	
	    	   	if(!StringFunctions.isNullOrEmpty(sectionName)){
	    	   		queryString.append(" and section = '"+sectionName+"'");
	    	   	}	    	   	
	    	   	List studyClassList= this.getAllHqlQuery(queryString.toString());
	    	   	if(!ObjectFunctions.isNullOrEmpty(studyClassList))
		           {
		               return (StudyClass)studyClassList.get(0);
		           }   	   	
	           }
	       catch (Exception ex) {
	           log.error("getting ConstantContact failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
	/*public List<ClassName> getAllClassesById(long classId,String status){
		return this.getAllHqlQuery("from ClassName where (parentId="+classId+" or id="+classId+") and (feeStatus like'"+status+"')");
	}*/
	
	public List<Fee> getAllFeesByCustId(long classId){
		return (List<Fee>) this.getAllHqlQuery("from Fee where custId="+classId);
	}
	
	public Fee getFeeByClassId(long classId){
		try
		{
            StringBuffer queryString = new StringBuffer();
            queryString.append("from Fee where ");
            queryString.append(" classId ='");
            queryString.append(classId);
            queryString.append("'");
            List campusDetails =  this.getAllHqlQuery(queryString.toString());
            
            if(!ObjectFunctions.isNullOrEmpty(campusDetails))
            {
                return (Fee) campusDetails.get(0);
            }
            return null;        	
		}
		catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	}
	
	public ViewStaffLeaveDetails getViewStaffLeaveDetailsByLeaveId(long leaveId, long customerId) {
	       try {
	    	  List leaveList = this.getAllHqlQuery("from ViewStaffLeaveDetails where custId= "+customerId+" and leavesId = "+leaveId);
		           if(!ObjectFunctions.isNullOrEmpty(leaveList))
		           {
		               return (ViewStaffLeaveDetails)leaveList.get(0);
		           }
	           }
	       catch (Exception ex) {
	           log.error("getting getViewStaffLeaveDetailsByLeaveId failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
	public List<ViewStaffLeaveDetails> getAllLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId) {
	       try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ViewStaffLeaveDetails where leaveStatus= '");
	            queryString.append(leaveStatus);
	            queryString.append("' and custId =");
	            queryString.append(customerId);
	            queryString.append(" and roleName ='");
	            queryString.append(roleName);
	            queryString.append("' and academicYearId ='");
	            queryString.append(academicYearId);
	            queryString.append("' and leaveSupervisorId ='");
	            queryString.append(supervisorId);
	            queryString.append("'");
	            List leavesList =  this.getAllHqlQuery(queryString.toString());
		    	  //List leavesList = this.getAllHqlQuery("from ViewStaffLeaveDetails where leaveStatus= '"+leaveStatus +"' and custId = "+customerId+" and roleName ='" + roleName+"'"); 
		           if(!ObjectFunctions.isNullOrEmpty(leavesList))
		           {
		               return (List<ViewStaffLeaveDetails>) leavesList;
		           }
	           }
	       catch (Exception ex) {
	           log.error("getting getAllPendingStatusLeaves failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
	public List<Leave> getLeavesListByAccountId(long accountId,String leaveStatus,long customerId)
	{
		try {
            List leavesList = this.getAllHqlQuery("from Leave where leaveStatus ='" +leaveStatus+ "' and accountId = " + accountId +" and custId = "+customerId);
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

	public List<Student> getAllStudentsByCustId(long custId,String status,long academicYearId){
		return (List<Student>) this.getAllHqlQuery("from Student where custId="+custId+" and status='"+status+"' and academicYearId="+academicYearId);
	}
	public List<Student> getAllStudentsByClassName(long classId,long custId,String status,long academicYearId){
		return (List<Student>) this.getAllHqlQuery("from Student where custId="+custId+" and status='"+status+"' and classSectionId="+classId+" and academicYearId="+academicYearId);
	}
    public List<Student> getAllStudentsByRollNumber(String rollNumber,long custId){
		try{
			return (List<Student>) this.getAllHqlQuery("from Student where custId="+custId+" and rollNumber like '%"+rollNumber+"%'");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
   
    public List<StudyClass> getSubjectsByClassName(String className,long custId,long academicYearId){
//	       List studyClassList = null;
	       try {
	    	   return (List<StudyClass>) this.getAllHqlQuery("from StudyClass where className='"+className+"' and custId="+custId+" and academicYearId="+academicYearId);
	           }
	       catch (Exception ex) {
	           log.error("getting ConstantContact failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
    
    public List<Section> getSectionByStudyClass(String sectionName){
	       try {
	    	   List sectionList= this.getAllHqlQuery("from Section where section not in ("+sectionName+")");
	           
		       if(!ObjectFunctions.isNullOrEmpty(sectionList))
	           {
	           	return (List<Section>) sectionList;
	           }
		       else
		       {
	           	return null;
	           }
	       }
	       catch (Exception ex) {
	           log.error("getting ConstantContact failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
    
    /*public List<ViewStudentPersonAccountDetails> getStudentsByRollNumber(String rollNumber,long custId){
		try{
			return this.getAllHqlQuery("from ViewStudentPersonAccountDetails where custId="+custId+" and rollNumber like '%"+rollNumber+"%'");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
    public List<ViewStudentClassDetails> getStudentsPayentByRollNumber(String rollNumber,long custId,long academicYearId){
		try{
			return (List<ViewStudentClassDetails>) this.getAllHqlQuery("from ViewStudentClassDetails where custId="+custId+" and (firstName like '%"+rollNumber+"%' or lastName like '%"+rollNumber+"%' || admissionNumber like '%"+rollNumber+"%') and academicYearId="+academicYearId+" and accountExpired='N' order by firstName,lastName,sortingOrder,classId");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
    public List<ViewStudentPersonAccountDetails> getStudentTransportPayentByRollNumber(String rollNumber,long custId,String status,String transportStatus){
		try{
			return (List<ViewStudentPersonAccountDetails>) this.getAllHqlQuery("from ViewStudentPersonAccountDetails where custId="+custId+" and (firstName like '%"+rollNumber+"%' or lastName like '%"+rollNumber+"%') and status='"+status+"' and transportMode='"+transportStatus+"' and boardingPointId is not null");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
    public List<ViewStaffPersonAccountDetails> getStaffDetailsBySearchName(String rollNumber,long custId,String status){
    	try{
    		return (List<ViewStaffPersonAccountDetails>) this.getAllHqlQuery("from ViewStaffPersonAccountDetails where custId="+custId+" and (firstName like '%"+rollNumber+"%' or lastName like '%"+rollNumber+"%') and status='"+status+"' and accountExpired='N' and academicYearStatus='Y'");
    	}catch(Exception ex){
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    	}
    	return null;
    }
    public Student getStudentByRollNumber(String rollNumber,long custId){
	       List studentList = null;
	       try {
	    	   studentList = this.getAllHqlQuery("from Student where custId="+custId+" and rollNumber='"+rollNumber+"' and status='Y'");
		           if(!ObjectFunctions.isNullOrEmpty(studentList))
		           {
		               return (Student)studentList.get(0);
		           }
	           }
	       catch (Exception ex) {
	           log.error("getting ConstantContact failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	   
	}
    public List checkPersonId(String userId) {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append("from User where ");
            queryString.append(" username ='");
            queryString.append(userId);
            queryString.append("'");
            List personNames =  this.getAllHqlQuery(queryString.toString());
            
            if(!ObjectFunctions.isNullOrEmpty(personNames))
            {
                Collections.sort(personNames);
                return personNames;
            }
            return null;
        } catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	} 
    
    public List getSubjectsForHigherClass(String subjectId) {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append("from StudySubject  ");
            if(!StringFunctions.isNullOrEmpty(subjectId))
            {
	            queryString.append(" where parentId !='");
	            queryString.append(subjectId);
	            queryString.append("'");
            }
            List personNames =  this.getAllHqlQuery(queryString.toString());
            
            if(!ObjectFunctions.isNullOrEmpty(personNames))
            {
                Collections.sort(personNames);
                return personNames;
            }
            return null;
        } catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	}
    /*public Student getStudentFeeDetailsByRoleNum(String rollNumber,long custId,long academicYearId,String status){
    	List studentsList=this.getAllHqlQuery("from Student where rollNumber='"+rollNumber+"' and custId="+custId+" and academicYearId="+academicYearId+" and status='"+status+"' ");
    	if(!ObjectFunctions.isNullOrEmpty(studentsList)){
    		Student student=(Student)studentsList.get(0);
    		return student;
    	}
    	else{
    		return null;
    	}
    }*/

	public StudentPayment getPaymentStatusByStudentId(long studentId) {
		List studentPaymentList = this.getAllHqlQuery("from StudentPayment where studentId=" + Long.valueOf(studentId));
		if (!ObjectFunctions.isNullOrEmpty(studentPaymentList)) {
			StudentPayment studentPayment = (StudentPayment) studentPaymentList.get(0);
			return studentPayment;
		} else {
			return null;
		}

	}
	
	 public List  getSubjectsByClass(long classId){
		  try{
				if (!ObjectFunctions.isNullOrEmpty(classId)) {
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from ClassSubject where studyClassId=");
					sqlString.append(classId);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
				}
			/*
	            String hqlQuery=null;
	            hqlQuery = "delete from classEvent WHERE classId="+classId;
	            Query qry=this.getAll(hqlQuery.toString());*/	            
			
	      }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	    }
	 public LeaveManagement getLeaveManagementByRoleName(long roleId,long custId,long academicYearId) {
	        try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from LeaveManagement where ");
	            queryString.append(" roleId =");
	            queryString.append(roleId);
	            queryString.append(" and custId=");
	            queryString.append(custId);
	            queryString.append(" and academicYearId=");
	            queryString.append(academicYearId);
	            List leaveManagementList =  this.getAllHqlQuery(queryString.toString());
	            if(!ObjectFunctions.isNullOrEmpty(leaveManagementList))
	            {
		            LeaveManagement leaveManagement = (LeaveManagement) leaveManagementList.get(0);
					return leaveManagement;
	            }
	        } catch (RuntimeException ex) {
	            log.error("Get failed", ex);
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	            throw ex;
	        }
	        return null;
		}
	public List<ViewStaffPersonAccountDetails> getViewStaffDetailsByRoleName(String rollName, long customerId,String status) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStaffPersonAccountDetails where roleName ='");
           queryString.append(rollName);
           queryString.append("' and custId = "+customerId);
           //queryString.append(" and status = '"+status+"' and academicYearStatus='Y'");
           queryString.append(" and status = '"+status+"'");
           List viewStaffPersonAccountDetails = this.getAllHqlQuery(queryString.toString());
           if(!ObjectFunctions.isNullOrEmpty(viewStaffPersonAccountDetails))
           {
               return (List<ViewStaffPersonAccountDetails>) viewStaffPersonAccountDetails;
           }
            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	
	}
	public List getStudentsPaymetDetailsByCustId(String paymentType,long custId)
	{
		try {
            List studentFullPaymentList = this.getAllHqlQuery("from ViewStudentFeePaymentDetails where paymentType ='"+paymentType+"' and custId="+custId+"");
            if(!ObjectFunctions.isNullOrEmpty(studentFullPaymentList))
            {
            	return studentFullPaymentList;
            }else{
            	return null;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	/*public User getUserByUserName(String emailId, long customerId) {

        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append("from User where ");
            queryString.append(" username ='");
            queryString.append(emailId);
            queryString.append("' and custId=");
            queryString.append(customerId);
            List usersList =  this.getAllHqlQuery(queryString.toString());
            if(!ObjectFunctions.isNullOrEmpty(usersList))
            {
                return (User)usersList.get(0);
            }
            return null;
        } catch (RuntimeException ex) {
            log.error("Get failed", re);
            throw re;
        }
	}
*/	
	public List<Leave> getLeavesByAccountId(long accountId){
		return (List<Leave>) this.getAllHqlQuery("from Leave where accountId="+accountId);
	}
	public void updateAllStaffLeaveDetailsByCasualANDSickLivesAndStaffIds(String staffIds, long casualLeaves, long sickLeaves, long custId) {
	       try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update staff set casualLeaves ="); 
	           sqlString.append(casualLeaves);
	           sqlString.append(", sickLeaves = ");
	           sqlString.append(sickLeaves);
	           sqlString.append(", totalLeaves = ");
	           sqlString.append(casualLeaves + sickLeaves);
	           sqlString.append(" where id in ");
	           sqlString.append(staffIds);
	           sqlString.append(" and custId="+custId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public List getClassSubjectByClassId(long classId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select * from ClassSubject where studyClassId="+classId);
           List subjectsList = this.getAll(queryString.toString());  
           if (ObjectFunctions.isNullOrEmpty(subjectsList)) {
               return null;
           } else {
               return subjectsList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	
	     public Fee getPaymentStatusByClassId(long classId) {
	    	 try{
		    		StringBuffer queryBuff=new StringBuffer();
					queryBuff.append("from Fee where classId='");
					queryBuff.append(classId);
					queryBuff.append("'");
					List classesList=this.getAllHqlQuery(queryBuff.toString());
					if(ObjectFunctions.isNotNullOrEmpty(classesList)){
						return (Fee)classesList.get(0);
					}
		     }
	    	 catch(Exception ex){
	    		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
				}
				return null;
		    } 
	
	/* public ClassName getClassBystudyClassId(long classId){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ClassName where id='");
				queryBuff.append(classId);
				queryBuff.append("'");
				List classesList=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(classesList)){
					return (ClassName)classesList.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	    }*/

	public List<ViewStudentClassFeePaymentDetails> getStudentByClassIdAndFee(long termId,long custId) {
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("from ViewStudentClassFeePaymentDetails where ");
			queryString.append(" custId =");
			queryString.append(custId);
			queryString.append(" and (paymentStatus='N')");
			List viewStudentFeeDetails = this.getAllHqlQuery(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(viewStudentFeeDetails)) {
				return (List<ViewStudentClassFeePaymentDetails>) viewStudentFeeDetails;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 
	public void deleteStudentAttendanceByStudentId(long studentId) {
		 try{
			if (!ObjectFunctions.isNullOrEmpty(studentId)) {
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from Attendance where studentId=");
				sqlString.append(studentId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
			}
	     }
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
	}
	public List<Fee> getAllAdminFeeStausList(String status){
		 return (List<Fee>) this.getAllHqlQuery("from Fee where status = '"+status+"'");
	 }
	 
	public List<StudentPayment> getALlReceiptsByPaymentId(long spId,String createdDate){
		 try {
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from  StudentPayment where studentId=");
	           queryString.append(spId);
	           queryString.append(" and createdDate like '%");
	           queryString.append(createdDate);
	           queryString.append("%'");
	           List feesPaymentReceiptList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(feesPaymentReceiptList))
	           {
	               return (List<StudentPayment>) feesPaymentReceiptList;
	           }
	            return null;    
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
   }
	public List getFeesPaymentListByStudentPaymentId(String table,long studentId,long classId,long custId,long academicYearId,String today,String paymentStatus,long invoiceNumber){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from "+table+" where studentId ="+studentId);
           if(!StringFunctions.isNullOrEmpty(today)){
        	   queryString.append(" and paymentDate=");
               queryString.append("'"+today+"'"); 
           }
           queryString.append(" and academicYearId=");
           queryString.append(academicYearId);
           queryString.append(" and custId=");
           queryString.append(custId);
           /*queryString.append(" and classId=");
           queryString.append(classId);*/
           if(!StringFunctions.isNullOrEmpty(paymentStatus)){
        	   queryString.append(" and paymentStatus='");
               queryString.append(paymentStatus);
               queryString.append("'");
           }
           if(invoiceNumber !=0){
        	   queryString.append(" and invoiceNumber=");
        	   queryString.append(invoiceNumber);
           }
           queryString.append(" and invoiceNumber != 0");
           if(StringFunctions.isNullOrEmpty(today)){
        	   queryString.append(" group by invoiceNumber");
           }
//           log.debug(queryString.toString());
		   List paymentDetailsList=this.getAllHqlQuery(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(paymentDetailsList)) {
				return paymentDetailsList;
			} return null;
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	 }
	public List getAllNoticeBoardMessagesList(String attendanceStatus,long customerId,String academicYearId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from Messages where status='");
           queryString.append(attendanceStatus);
    	   queryString.append("' and custId = ");
           queryString.append(customerId);
           queryString.append(" and academicYearId = '");
           queryString.append(academicYearId);
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
	}
	public List<ExamTypes> getAllClassExamTypes(long custId,String academicYear){
		return (List<ExamTypes>) this.getAllHqlQuery("from ExamTypes where custId="+custId+" and academicYearId="+academicYear);
	}
	
	public List getAllHolidayBoardMessagesList(String attendanceStatus,long customerId) {
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
	}
	
	 public ClassName getClassByClassName(String className,long custId,long academicYearId,boolean isAutoCheck){
	    	try{
	    		StringBuffer query=new StringBuffer();
	    		query.append("from ClassName where custId="+custId+" and academicYearId="+academicYearId);
	    		if (StringFunctions.isNotNullOrEmpty(className)){
	    			if(isAutoCheck){	    			
	    				query.append(" and className like"+className);
	    			}
	    			else{
	    			query.append(" and className='"+className+"'");
	    			}
	    			query.append(" order by sortingOrder");
	    			List classesList = this.getAllHqlQuery(query.toString());
	    			if(!ObjectFunctions.isNullOrEmpty(classesList))
	    				return(ClassName)classesList.get(0);
	    				
	    		}
	    		return null;	    		
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	    }	 


	 /* public List getClassExamTypesList(String examTypeIds,long custId,String academicYear){
		 	return this.getAllHqlQuery("from ExamTypes where custId="+custId+" and id not in"+examTypeIds+" and academicYearId="+academicYear);
		}*/
	 
	 public ViewStaffPersonAccountDetails getStaffLastRecordByRoleName(String roleName, long customerId) {
			try{
				//SELECT * FROM student WHERE classId=1 ORDER BY rollNumber DESC LIMIT 1;
				
		            StringBuffer sqlString=new StringBuffer();
			           sqlString.append("from ViewStaffPersonAccountDetails WHERE roleName = '"); 
			           sqlString.append(roleName);
			           sqlString.append("' and custId = "); 
			           sqlString.append(customerId);
			           sqlString.append(" and academicYearStatus='Y' ORDER BY id DESC LIMIT 1 ");
			           List resultList=this.getAllHqlQuery(sqlString.toString());
			           //List resultList=this.getAll(sqlString.toString());	
			           if(ObjectFunctions.isNotNullOrEmpty(resultList)){
							return (ViewStaffPersonAccountDetails)resultList.get(0);
						}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
		}
	 public long getMaxExamTypeIdFromStudentMarks(long classId,long academicYear){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select max(ifnull(examtypeId,0)) from vw_studentMarksDetails where classSectionId=");
				queryBuff.append(classId);	
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYear);
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						return  ((BigInteger)resultList.get(0)).longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	}
	public ClassName saveClassName(ClassName className) {
		try{
			
			 this.saveObject(className);
			 return className;
		}
		catch (HibernateException ex)
		{
		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 throw new URTDataAccessException(ex.getMessage());
		}
	}
	
	public List getSyllabusByClassIdAndCustId(long classId,long custId){
		try{
			List syllabus=this.getAllHqlQuery("from Syllabus where  studyClassId="+classId+" and custId="+custId);
			if(!ObjectFunctions.isNullOrEmpty(syllabus)){
				return syllabus;
			}
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	}
	

	public AdmissionSettings getAdmissionSettingsByCustIdAndClassId(long custId,long classId)
	{

 	   List admissionSettings= this.getAllHqlQuery("from AdmissionSettings where custId="+custId+" and classId="+classId);
        
	       if(!ObjectFunctions.isNullOrEmpty(admissionSettings))
        {
	    	   return (AdmissionSettings)admissionSettings.get(0);
        }
	       else
	       {
        	return null;
        }
    
	}
	
	/*public List<ClassName> getClassesByCustId(long custId,long academicYearId)
	{
		return this.getAllHqlQuery("from ClassName where custId="+custId+" and academicYearId="+academicYearId);
	}*/
	public OnlineApplicationDetails getDetailsByApplicationNumber(String applicationNumber)
	{
 	   List admissionDetails= this.getAllHqlQuery("from OnlineApplicationDetails where applicationNumber='"+applicationNumber+"'");
		if (!ObjectFunctions.isNullOrEmpty(admissionDetails)) {
			return (OnlineApplicationDetails) admissionDetails.get(0);
		} else {
			return null;
		}
	}
	 /*public List<ClassName> checkClassId(String className,long custId,long academicYearId) {
		if (StringFunctions.isNotNullOrEmpty(className)) {
			// className=className.replaceAll("'", "''");			
			return this.getAllHqlQuery("from ClassName where className like '" + className + "' and custId="+custId+" and academicYearId="+academicYearId);
		}
		return null;
	}*/
	/*public List<StudyClass> getStudyClassesByCustIdAndGroupByClassNameClassId(long customerId,String academicYearId) {
		return this.getAllHqlQuery("from StudyClass where custId="+customerId+" and academicYearId='"+academicYearId+"' group by classNameClassId");
	}*/
	public List getStudentsByClassNameClassId(long classNameClassId,long customerId) {
		return this.getAllHqlQuery("from Student where custId="+customerId+" and classNameClassId="+classNameClassId);
	}
	public List getAllHolidaysListByAcademicYearId(String status,long customerId,long academicYearId,long classNameClassId) {
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from SchoolHolidays where (status='");
           queryString.append(status);
    	   queryString.append("' OR  status='WH') and custId = ");
           queryString.append(customerId); 
           queryString.append(" and academicYearId = ");
           queryString.append(Long.valueOf(academicYearId));
           if(classNameClassId>0){
        	   queryString.append(" and classId=");
           	   queryString.append(classNameClassId); 
           }
           if(status.equalsIgnoreCase("H"))
        	   queryString.append(" group by description,startDate,endDate");   
           //log.debug(queryString.toString());
           List yearHolidaysList = this.getAllHqlQuery(queryString.toString());
           if (ObjectFunctions.isNullOrEmpty(yearHolidaysList)) {
               return null;
           } else {
               return yearHolidaysList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}

/* public List getAllClassExamTypesByAcademicYearId(long custId,long academicYearId){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ExamTypes where custId='");
	           queryString.append(custId);
	           queryString.append("' and academicYearId = ");
	           queryString.append(Long.valueOf(academicYearId));      
	           queryString.append("'");
	           List examHolidaysList = this.getAllHqlQuery(queryString.toString());
	           if (ObjectFunctions.isNullOrEmpty(examHolidaysList)) {
	               return null;
	           } else {
	               return examHolidaysList;
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException (ex);raygex=null;
	        }
	        return null;
		}*/
	public List<ViewClassExamDetails> getAllUpcomingClassExamSchedulesGroupByClassIdandExamTypeId(long custId,long academicYearId,String date){
		 return (List<ViewClassExamDetails>) this.getAllHqlQuery("from ViewClassExamDetails where custId="+custId+" and academicYearId="+academicYearId+" and examDate >= '"+date+"' group by classSectionId,eid");
	 }
	public List getClassSyllabusList(long classId){
		return this.getAllHqlQuery("from Syllabus where studyClassId="+classId);
	}
	public String removeAllSyllabusWithClassId(long classId){
		  try{
			  if (!ObjectFunctions.isNullOrEmpty(classId)) {
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from syllabus where studyClassId=");
					sqlString.append(classId);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					@SuppressWarnings("unused")
					int row = qry.executeUpdate();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	}
	/*public List getClassesByClassId(String classId,long custId,long academicYearId)
	{
		return this.getAllHqlQuery("from ClassName where id not in ("+classId+") and custId="+custId+" and academicYearId="+academicYearId);
	}*/
	 public List getAcademicsByAcademicYear(String academicYear,long custId){
			return this.getAllHqlQuery("from AcademicYear where custId="+custId+" and academicYear not in "+academicYear);
		}
	public List getAllCompletedClassExamSchedulesGroupByClassIdandExamTypeId(long custId,long academicYearId,String date){
		 return this.getAllHqlQuery("from ViewClassExamDetails where custId="+custId+" and academicYearId="+academicYearId+" and examDate < '"+date+"' group by classSectionId,eid");
	}
	public List<ViewStudentPersonAccountDetails> getStudentsByFirstName(String firstName,long custId){
		try{
			return (List<ViewStudentPersonAccountDetails>) this.getAllHqlQuery("from ViewStudentPersonAccountDetails where custId="+custId+" and firstName like '%"+firstName+"%'");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/*public List<ClassName> getAllClasseNames(long custId,String status,long academicYearId){
		return this.getAllHqlQuery("from ClassName where (custId="+custId+" and academicYearId="+academicYearId+") and (feeStatus like '"+status+"')");
	}*/

	public OnlineApplicationDetails getDetailsByApplicationNumber(String applicationNumber,long custId)
	{
 	   List admissionDetails= this.getAllHqlQuery("from OnlineApplicationDetails where applicationNumber='"+applicationNumber+"' and custId="+custId );
		if (!ObjectFunctions.isNullOrEmpty(admissionDetails)) {
			return (OnlineApplicationDetails) admissionDetails.get(0);
		} else {
			return null;
		}
	}
	public List getApplicationsByStatus(String status,long classId,long custId,long academicYearId,String todayDate)
	{
		if(classId==0)
		{
			if (!StringFunctions.isNullOrEmpty(todayDate)) {
				return this.getAllHqlQuery("from OnlineApplicationDetailsView where applicationStatus='"+status+"' and custId="+custId+" and academicYearId="+academicYearId+" and  admissionEndDate >= '"+todayDate+" 00:00:00' order by entranceMarks DESC, className ASC,firstName");
			}else{
				return this.getAllHqlQuery("from OnlineApplicationDetailsView where applicationStatus='"+status+"' and custId="+custId+" and academicYearId="+academicYearId+" order by entranceMarks DESC, className ASC,firstName");
			}
		}
		else
		{
			return this.getAllHqlQuery("from OnlineApplicationDetailsView where status='"+status+"' and classId="+classId+" and custId="+custId+" and academicYearId="+academicYearId+" order by entranceMarks DESC, className ASC,firstName");
		 
		}
	}
	public AdmissionSettings getAdmissionSettingsByCustId(long custId)
	{
	 	   List admissionDetails= this.getAllHqlQuery("from AdmissionSettings where custId="+custId);
			if (!ObjectFunctions.isNullOrEmpty(admissionDetails)) {
				return (AdmissionSettings) admissionDetails.get(0);
			} else {
				return null;
			}
	}
	/*public List<ClassName> getClassesByAdmissionStatus(long custId,String status,long academicYearId)
	{
		return this.getAllHqlQuery("from ClassName where custId="+custId+" and admissionsOpen='"+status+"' and academicYearId="+academicYearId);
	}*/
	public OnlineApplicationDetailsView getDetailsByApplicationNumberAndView(String applicationNumber)
	{
 	   List admissionDetails= this.getAllHqlQuery("from OnlineApplicationDetailsView where applicationNumber='"+applicationNumber+"'");
		if (!ObjectFunctions.isNullOrEmpty(admissionDetails)) {
			return (OnlineApplicationDetailsView) admissionDetails.get(0);
		} else {
			return null;
		}
	}
	public List getApplicationDetailsByClassName(long classId,long custId)
	{
		return this.getAllHqlQuery("from OnlineApplicationDetails where classId='"+classId+"' and custId="+custId+" and status='P' ");
	}
	public List getApplicationDetailsByMarks(long classId,long custId)
	{
		return this.getAllHqlQuery("from OnlineApplicationDetails where classId='"+classId+"' and custId="+custId+" order by order by entranceMarks desc");
	}
	public String getClassStudentStrengthByClassId(long classId,long custId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from student where classNameClassId=");
				queryBuff.append(classId);	
				queryBuff.append(" and custId='");
				queryBuff.append(custId);
				queryBuff.append("' and status='"+ Constants.YES_STRING+ "' and description is null");	
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList) && !ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.toString();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	}
	public List getAdmissionSettingsForAcademicYears(long custId,long academicYearId)
	{
		return this.getAllHqlQuery("from AdmissionSettings where custId="+custId+" and academicYearId="+academicYearId);
	}
	public List<AcademicYear> getAcademicYears(long pastYear,String academicYear,long custId)
	{
		if(StringFunctions.isNotNullOrEmpty(academicYear))
		{
			return (List<AcademicYear>) this.getAllHqlQuery("from AcademicYear where pastYear>"+pastYear+" and custId="+custId+" and academicYear not in ("+academicYear+")");
		}
		else
		{
			return (List<AcademicYear>) this.getAllHqlQuery("from AcademicYear where pastYear="+pastYear+" and custId="+custId);
		}
	}
	public AdmissionSettings getAdmissionSettingsByCustIdAndYear(long custId,long academicYearId)
	{
 	   List admissionSettings= this.getAllHqlQuery("from AdmissionSettings where custId="+custId+" and academicYearId="+academicYearId);
	       if(!ObjectFunctions.isNullOrEmpty(admissionSettings))
	       	{
	    	   return (AdmissionSettings)admissionSettings.get(0);
	       	}
	       else
	       {
        	return null;
        }
    
	}
	public List<KBank> getKBankBySearchKewordsKBankTypeId(String searchKeywords,long kBankTypeId,long custId){
		try{
			return (List<KBank>) this.getAllHqlQuery("from KBank where custId="+custId+" and kBankTypeId="+kBankTypeId+" and searchKewords like '%"+searchKeywords+"%'");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public AcademicYear saveAcademicYear(AcademicYear academicYear) throws DataAccessException{
		try {
			
			this.saveObject(academicYear);
			return academicYear;
		} catch (HibernateException ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw new URTDataAccessException(ex.getMessage());
		}
	}
/*	public SchoolHolidays getHolidaysListForUpdate(String fromDate,long custId,long classId){
		
		StringBuffer queryBuff=new StringBuffer();
		queryBuff.append("from SchoolHolidays where custId=");
		queryBuff.append(custId);	
		if(classId > 0)
		{
			queryBuff.append(" and classId=");
			queryBuff.append(classId);
		}
		queryBuff.append(" and holidayDate like '"+ fromDate+ "%'");	
		List holidaysList=this.getAllHqlQuery(queryBuff.toString());
		if(!ObjectFunctions.isNullOrEmpty(holidaysList)){
			return (SchoolHolidays)holidaysList.get(0);
		}
		else{
			return null;
		}
	}*/
	public List geAllHolidaysByAcademicYearId(long custId,long academicYearId)
	{
		return this.getAllHqlQuery("from SchoolHolidays where custId='"+custId+"' and academicYearId="+academicYearId);
	}
	public List<ViewClassFeeDetails> getClassFeeDetails(long classId,long custId,String academicYear){
		try{
			return (List<ViewClassFeeDetails>) this.getAllHqlQuery("from ViewClassFeeDetails where classId="+classId+" and custId ="+custId+" and academicYearId="+academicYear);
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<Fee> getClassFeeTermDetails(long classId,long custId,String academicYear){
		try{
			return (List<Fee>) this.getAllHqlQuery("from Fee where classId="+classId+" and custId ="+custId+" and academicYearId="+academicYear+" group by schoolTermId");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getClassFeeTermsByStudentId(String table,long studentId,long custId,long academicYearId,long classId,long categoryId,long feeSettingId){
		try{
			return this.getAllHqlQuery("from "+table+" where custId ="+custId+" and academicYearId="+academicYearId+" and classId="+classId+" and studentId="+studentId+" and categoryId="+categoryId+" and deleteStatus='"+Constants.NO_STRING+"' and feeSettingId=" +feeSettingId+ " group by feeId order by fromDate, termName, feeId ");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/*public List getStudentPaymentList(long studentId,long custId){
		try{
			return this.getAllHqlQuery("from StudentPayment where studentId="+studentId+" and custId="+custId);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
	/*public SchoolOrganization getCurrentYearForAttendance(String academicYear,long custId)
	{
 	   List orgObjectList= this.getAllHqlQuery("from SchoolOrganization where custId="+custId+" and academicYearId='"+academicYear+"'");
	       if(!ObjectFunctions.isNullOrEmpty(orgObjectList))
	       {
	    	   return (SchoolOrganization)orgObjectList.get(0);
	       }
	       else
	       {
        	return null;
        }
    
	}*/
	public List getAllHolidaysByMonthId(int monthId,long orgd,String holidayYear)
	{
		return this.getAllHqlQuery("from SchoolHolidays where monthId='"+monthId+"' and academicYearId='"+orgd+"' and yearId='"+holidayYear+"' and holidayDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
	}
	public StudentPayment getRemainingPaymentByStudentPaymentId(long studentId,long classId,long feeId,long custId,long academicYearId,String status) {
		
		try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from StudentPayment where studentId=");
			queryBuff.append(studentId);
			queryBuff.append(" and classId=");
			queryBuff.append(classId);
			queryBuff.append(" and feeId=");
			queryBuff.append(feeId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			queryBuff.append(" and paymentStatus='");
			queryBuff.append(status);
			queryBuff.append("'");
			List paymentList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(paymentList)){
				return (StudentPayment)paymentList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return null;
	}
	/*public List getClassExamSchedulesList(long custId,long classId,long academicYear,String date){
		try{
			return this.getAllHqlQuery("from ViewClassExamDetails where custId="+custId+" and classNameClassId="+classId+" and academicYearId="+academicYear+" and examDate > '"+date+"' group by classNameClassId,eid,subjectId order by examdate");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
	/*public List getClassCompletedExamSchedulesList(long custId,long classId,long academicYear,String date){
		try{
			return this.getAllHqlQuery("from ViewClassExamDetails where custId="+custId+" and classNameClassId="+classId+" and academicYearId="+academicYear+" and examDate < '"+date+"' group by classNameClassId,eid,subjectId order by examdate");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
	
	/*public List getClassExamTypeList(long custId,long classId,long academicYear,String date){
		try{
			return this.getAllHqlQuery("from ViewClassExamDetails where custId="+custId+" and classNameClassId="+classId+" and academicYearId="+academicYear+" and examDate > '"+date+"' group by classNameClassId,eid");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
	/*public List getClassCompletedExamTypeList(long custId,long classId,long academicYear,String date){
		try{
			return this.getAllHqlQuery("from ViewClassExamDetails where custId="+custId+" and classNameClassId="+classId+" and academicYearId="+academicYear+" and examDate < '"+date+"' group by classNameClassId,eid");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
	/*public List getPaymentStatusListByStudentId(long studentId) {
		List studentPaymentList = this.getAllHqlQuery("from StudentPayment where studentId=" + Long.valueOf(studentId));
		if (!ObjectFunctions.isNullOrEmpty(studentPaymentList)) {
			return studentPaymentList;
		} else {
			return null;
		}
	}*/
	public FeeType getFeeTypeId(long feeTypeId,long custId)
	{
 	   List feeTypeList= this.getAllHqlQuery("from FeeType where id="+feeTypeId+" and custId="+custId+"");
	       if(!ObjectFunctions.isNullOrEmpty(feeTypeList))
	       {
	    	   return (FeeType)feeTypeList.get(0);
	       }
	       else
	       {
        	return null;
        }
    
	}
	public ViewStudentClassFeePaymentDetails getClassFeePendingTerms(long classId,long feeId,long custId,String studentNumber){
		try{
			List instalmentList=this.getAllHqlQuery("from ViewStudentClassFeePaymentDetails where classNameClassId="+classId+" and custId ="+custId+" and feeTypeId="+feeId+" and rollNumber='"+studentNumber+"' and (paymentStatus='L')");
			if(!ObjectFunctions.isNullOrEmpty(instalmentList))
		       {
		    	   return (ViewStudentClassFeePaymentDetails)instalmentList.get(0);
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public AcademicYear getAcademicYearByAcademicYear(String academicYear,long custId){
    	try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from AcademicYear where academicYear='");
			queryBuff.append(academicYear);
			queryBuff.append("' ");
			queryBuff.append("and custId=");
			queryBuff.append(custId);
			List acedamicDetails=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(acedamicDetails)){
				return (AcademicYear)acedamicDetails.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return null;
    }

	public List getAllStudentsDetailsByCustId(long custId) {
		try {
			return this.getAllHqlQuery("from ViewStudentClassFeePaymentDetails where custId="+ custId+" and (paymentStatus='L') group by rollNumber");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 
	public List removeStaffSubjectList(long staffId,long academicYearId) {
		try{
               StringBuffer sqlString=new StringBuffer();
	           sqlString.append("delete from staffElgibleSubjects where staffId ="); 
	           sqlString.append(staffId);
	           sqlString.append(" and academicYearId=");
	           sqlString.append(academicYearId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows deleted:"+ row);
	            }
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}

	public List getAllStaffLeavesByStatus(long custId,String leaveStatus) {
		try {
			return this.getAllHqlQuery("from ViewStaffLeaveDetails where custId= " + custId+" and leaveStatus='"+leaveStatus+"'");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStaffPersonAccountDetails> getAllStaffList(long custId,long academicYearId){
		try {
			return (List<ViewStaffPersonAccountDetails>) this.getAllHqlQuery("from ViewStaffPersonAccountDetails where custId= " + custId+" and status='Y' and accountExpired='N' and academicYearId<="+academicYearId);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public Fee getFeeAndFeeTypeByClassId(String table,long classId,long feeTypeId,long custId,long academicYearId,long schoolTermId,long categoryId){
		try
		{
            StringBuffer queryString = new StringBuffer();
            queryString.append("from "+table+" where classId =");
            queryString.append(classId);
            queryString.append(" and feeTypeId =");
            queryString.append(feeTypeId);
            queryString.append(" and custId =");
            queryString.append(custId);
            queryString.append(" and academicYearId =");
            queryString.append(academicYearId);
            queryString.append(" and schoolTermId =");
            queryString.append(schoolTermId);
            queryString.append(" and categoryId =");
            queryString.append(categoryId);
            //List campusDetails =  this.getAllHqlQuery(queryString.toString());
            List campusDetails =  this.getAllHqlQuery(queryString.toString());
            
            if(!ObjectFunctions.isNullOrEmpty(campusDetails))
            {
                return (Fee) campusDetails.get(0);
            }
            return null;        	
		}
		catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	}
	/*public List<StudentPayment> getStudentFeePendingList(long satudentId){
		try {
			return this.getAllHqlQuery("from StudentPayment where studentId= "+satudentId+" and (paymentStatus='L') ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}*/
	public List getQuizQuestionListWithStartDate(long quizId){
		try {
			return this.getAllHqlQuery("from QuizQuestion where quizId="+quizId+" group by startDate");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public FeeType saveFeeTypeName(FeeType feeType) {
		try{
			
			 this.saveObject(feeType);
			 return feeType;
		}
		catch (HibernateException ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 
		 throw new URTDataAccessException(ex.getMessage());
		}
	}

	public List getFeeTypeList(String table,String feeType, long custId,long acadmeicYearId) {
		try {
			return this.getAllHqlQuery("from "+table+" where custId=" + custId + " and description='"+ feeType + "' and academicYearId="+acadmeicYearId);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/*public List<ParentFeedbackResult> getFeedbackResultList(long questionId,long custId,String academicYearId){
		try {
			return this.getAllHqlQuery("from ParentFeedbackResult where custId=" + custId + " and academicYearId='"+academicYearId+"' and feedbackQuestionId="+questionId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}*/
	public List<ParentFeedbackResult> getStaffFeedbackResultList(long staffId,long custId,String academicYearId){
		try {
			return (List<ParentFeedbackResult>) this.getAllHqlQuery("from ParentFeedbackResult where custId=" + custId + " and academicYearId='"+academicYearId+"' and staffId="+staffId);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List removeFeedbackParentsResults(long feedbackQuestionId,long custId)
	{
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("delete from parentFeedbackResult where feedbackQuestionId ="); 
	           sqlString.append(feedbackQuestionId);
	           sqlString.append(" and custId =");
	           sqlString.append(custId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows deleted:"+ row);
	            }
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return null;
	}
	public ParentFeedbackResult getTeacherGradeByStudent(long staffId,long studentId,long custId){
		List staffFeedbackReultList= this.getAllHqlQuery("from ParentFeedbackResult where staffId="+staffId+" and custId="+custId+" and studentId="+studentId);
        
	       if(!ObjectFunctions.isNullOrEmpty(staffFeedbackReultList))
	       {
	    	   return (ParentFeedbackResult)staffFeedbackReultList.get(0);
	       }
	       else
	       {
     	return null;
     }
	}
	public ViewClassFeeDetails getClassFeeDetails(long classId,long custId,String academicYear,long feeTypeId){
		try{
			 List classFeeList= this.getAllHqlQuery("from ViewClassFeeDetails where classId="+classId+" and custId ="+custId+" and academicYearId='"+academicYear+"' and feeTypeId="+feeTypeId);
		       if(!ObjectFunctions.isNullOrEmpty(classFeeList))
		       {
		    	   return (ViewClassFeeDetails)classFeeList.get(0);
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewClassFeeDetails getAdminssionFeeDetails(String feeType,long custId,long classId){
		try{
			 List classFeeList= this.getAllHqlQuery("from ViewClassFeeDetails where classId="+classId+" and custId ="+custId+" and feeType='"+feeType+"'");
		       if(!ObjectFunctions.isNullOrEmpty(classFeeList))
		       {
		    	   return (ViewClassFeeDetails)classFeeList.get(0);
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List updateOnlineApplicationDetails(long classId){
		  try{
			  if (!ObjectFunctions.isNullOrEmpty(classId)) {
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("update onlineApplicationDetails set status ='N'"); 
			        sqlString.append(", lastUpdatedDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
			        sqlString.append(" where classId=");
			        sqlString.append(classId);
			        sqlString.append(" and status='Y'");
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	}
	public List<ViewClassFeeDetails> getClassFeeDetailsList(long classId,String academicYear,long custId){
		try {
			return (List<ViewClassFeeDetails>) this.getAllHqlQuery("from ViewClassFeeDetails where custId=" + custId + " and classId="+classId+" and academicYearId='"+academicYear+"'");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public Staff getStaffByAcountId(long accountId,String status){
    	try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from Staff where accountId=");
			queryBuff.append(accountId);
			queryBuff.append(" and status='");
			queryBuff.append(status);
			queryBuff.append("'");
			//List staffList=this.getAllHqlQuery(queryBuff.toString());
			List staffList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(staffList)){
				return (Staff)staffList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return null;
    }
	public List<PromoteClass> getPromoteClassDetailsByCustId(long custId,long academicYearId){
		try {
			return (List<PromoteClass>) this.getAllHqlQuery("from PromoteClass where custId=" + custId+" and academicYearId="+academicYearId);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getAllAdminsByUrlPath(String urlPath) {
		try {
			List adminsList = this.getAllHqlQuery("from Customer");
			if (ObjectFunctions.isNullOrEmpty(adminsList)) {
				return null;
			} else {
				return adminsList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	public Student saveStudentDetails(Student student) {
		try {
			
			//this.saveObject(student);
			this.mergeObject(student);
			return student;
		} catch (HibernateException ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;

			throw new URTDataAccessException(ex.getMessage());
		}
	}
	public List<StudySubject> getStudySubjectByCustId(long custId){
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append("from StudySubject  ");
	            queryString.append(" where custId ='");
	            queryString.append(custId);
	            queryString.append("'");
            List subjectList =  this.getAllHqlQuery(queryString.toString());
            
            if(!ObjectFunctions.isNullOrEmpty(subjectList))
            {
                Collections.sort(subjectList);
                return (List<StudySubject>) subjectList;
            }
            return null;
        } catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	}
	public String getMaxExamTypeIdFromStudentMarksBydate(String date){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select examTypeId from vw_studentMarksDetails where createdDate='");
				queryBuff.append(date);
				queryBuff.append("'");
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Timestamp var= ((Timestamp)resultList.get(0));
						return var.toString();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	}
	public List<AcademicYear> getPastAcademicYears(long academicYearId,long custId){
		try {
            StringBuffer queryString = new StringBuffer();
            queryString.append("from AcademicYear  where id <=");
	            queryString.append(academicYearId);
	            queryString.append(" and custId=");
	            queryString.append(custId);
            List academicsList= this.getAllHqlQuery(queryString.toString());
            if(!ObjectFunctions.isNullOrEmpty(academicsList))
            {
                return (List<AcademicYear>) academicsList;
            }
            return null;
        } catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	}
	public List<FeeType> getFeeTypeListByAcademicYearId(String queryString,long custId,long academicYearId,long feeSettingId){
		try {
            StringBuffer queryStr = new StringBuffer();
            queryStr.append("from "+queryString+"  where academicYearId=");
            queryStr.append(academicYearId);
            queryStr.append(" and custId=");
            queryStr.append(custId);
            queryStr.append(" and feeSettingId="+feeSettingId);
            List feeTypeList =  this.getAllHqlQuery(queryStr.toString());
            if(!ObjectFunctions.isNullOrEmpty(feeTypeList))
            {
                return (List<FeeType>) feeTypeList;
            }
            return null;
        } catch (RuntimeException ex) {
            log.error("Get failed", ex);
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            throw ex;
        }
	}
	public ClassTeacher getClassTeacherByAcademicId(long staffId,long custId,long academicYearId){
		try{
			 List classTeacherList= this.getAllHqlQuery("from ClassTeacher where teacherId="+staffId+" and custId ="+custId+" and academicYearId="+academicYearId+" and classTeacher='Y'");
		       if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
		       {
		    	   return (ClassTeacher)classTeacherList.get(0);
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<LeaveManagement> getLeavesListByCustId(long custId,long academicYearId){
		try{
			return (List<LeaveManagement>) this.getAllHqlQuery("from LeaveManagement where custId="+custId+" and academicYearId="+academicYearId);
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	
	public List<AdmissionSettings> getAdmissionSettingsList(String status){
		try{
			return (List<AdmissionSettings>) this.getAllHqlQuery("from AdmissionSettings where status='"+status+"' group by custId");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public Customer getCustomerByUrlPath(String urlPath){
		try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from Customer where webSiteUrl='");
			queryBuff.append(urlPath);
			queryBuff.append("'");
			List cusomerList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(cusomerList)){
				return (Customer)cusomerList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return null;
	}
	
	/*public List<ClassName> getAllClassesByCustIdandAcademicYear(long custId,String academicYearId){
		return this.getAllHqlQuery("from ClassName where custId="+custId+" and academicYearId='"+academicYearId+"'");
	}*/
	public AcademicYear getAcademicYearByPastYearandCustId(String pastYear,long custId){
		 try {
		        StringBuffer queryString = new StringBuffer();
		        queryString.append("from AcademicYear where ");
		        queryString.append(" pastYear ='");
		        queryString.append(pastYear);
		        queryString.append("'");
		        queryString.append(" and custId=");
		        queryString.append(custId);
		        List academicsList =  this.getAllHqlQuery(queryString.toString());
		        if(!ObjectFunctions.isNullOrEmpty(academicsList))
		        {
		            return (AcademicYear)academicsList.get(0);
		        }
		    } catch (Exception ex) {
		    	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		    }
		    return null;
	}
	/*public ClassName getClassNameByclassNameandCustIdandAcademicYear(String className,long custId,long academicYearId){
		try {
	        StringBuffer queryString = new StringBuffer();
	        queryString.append("from ClassName where ");
	        queryString.append(" className ='");
	        queryString.append(className);
	        queryString.append("'");
	        queryString.append(" and custId=");
	        queryString.append(custId);
	        queryString.append(" and academicYearId=");
	        queryString.append(academicYearId);
	        List classNameList =  this.getAllHqlQuery(queryString.toString());
	        if(!ObjectFunctions.isNullOrEmpty(classNameList))
	        {
	            return (ClassName)classNameList.get(0);
	        }
	    } catch (Exception ex) {
	    	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;	
	    }
	    return null;
	}*/
	
	public StudySubject getStudySubjectBySubjectNameandAcademicYearandCustId(String subjectName,long academicYear,long custId){
		try {
	        StringBuffer queryString = new StringBuffer();
	        queryString.append("from StudySubject where ");
	        queryString.append(" name ='");
	        queryString.append(subjectName);
	        queryString.append("'");
	        queryString.append(" and custId=");
	        queryString.append(custId);
	        queryString.append(" and academicYearId=");
	        queryString.append(academicYear);
	        List studySubjectsList =  this.getAllHqlQuery(queryString.toString());
	        if(!ObjectFunctions.isNullOrEmpty(studySubjectsList))
	        {
	            return (StudySubject)studySubjectsList.get(0);
	        }
	    } catch (Exception ex) {
	    	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
	    }
	    return null;
	}
	public List<StaffElgibleSubjects> getStaffElgibleSubjectsByAcademicYearId(long staffId,long academicYearId){
		try {
			return (List<StaffElgibleSubjects>) this.getAllHqlQuery("from StaffElgibleSubjects where staffId="+staffId+" and academicYearId="+academicYearId);
	    } catch (Exception ex) {
	    	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;	
	    }
	    return null;
	}
	public StudySubject saveStudySubject(StudySubject subject){
		try{
			
			 this.saveObject(subject);
			 return subject;
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getClassTeachersByAcademicIdAndStaffIdandCustId(long staffId,long custId,long academicYearId){
		try{
			 List classTeacherList= this.getAllHqlQuery("from ClassTeacher where teacherId="+staffId+" and custId ="+custId+" and academicYearId="+academicYearId);
		       if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
		       {
		    	   return classTeacherList;
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/*public StudyClass getStudyClassByClassNameAndSectionandAcademicYear(String className,String section,long custId,long academicYear){
		try{
			 List classTeacherList= this.getAllHqlQuery("from StudyClass where className='"+className+"' and section='"+section+"' and custId ="+custId+" and academicYearId="+academicYear);
		       if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
		       {
		    	   return (StudyClass)classTeacherList.get(0);
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}*/
	/*public StudentPayment getPaymentStatusByStudentClassIdAndPaymentId(long studentId,long custId,long feeTypeId,long classId){
		try{
			 List studentPaymentList= this.getAllHqlQuery("from StudentPayment where studentId="+studentId+" and custId ="+custId+" and paymentTypeId="+feeTypeId+" and classId="+classId);
		       if(!ObjectFunctions.isNullOrEmpty(studentPaymentList))
		       {
		    	   return (StudentPayment)studentPaymentList.get(0);
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}*/
	
	public List<Customer> getCustomerListByStatus(String status){
		try {
			return (List<Customer>) this.getAllHqlQuery("from Customer where  status='"+status+"' ");
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	public void removeSyllabusByAcademicYearAndCustId(long custId,long academicYear){
		try{
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("delete from syllabus where custId=");
			sqlString.append(custId+" and academicYearId=");
			sqlString.append(academicYear);
			Query qry = getSession().createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}
	public List getAllTeachingStaffListByStatus(long custId,String status,long academicYearId){
		return this.getAll("select staffId,accountId from vw_staffDetails where custId="+custId+" and status='"+status+"' and academicYearId <="+academicYearId+" and (roleName='ROLE_TEACHER' or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL' or roleName='ROLE_ADMIN_COORDINATOR')");
		 //return this.getAllHqlQuery("from ViewStaffPersonAccountDetails where custId="+custId+" and status='"+status+"' and academicYearId="+academicYearId+" and (roleName='ROLE_TEACHER' or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL')");
	 }
	 public StudySubject getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(long custId, String academiYear, String subjectName) {
		try {
			List studySubjectList = this.getAllHqlQuery("from StudySubject where  custId=" + custId+ " and academicYearId=" + academiYear+ " and name='" + subjectName + "'");
			if (!ObjectFunctions.isNullOrEmpty(studySubjectList)) {
				return (StudySubject) studySubjectList.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 public List getAllAcademicYearsByCustIdAndStartDateAndEndDateisNull(long custId){
		 return this.getAllHqlQuery("from AcademicYear where custId="+custId+" and startDate is null and endDate is null");
	 }
	 public CommonType getSkillTypeNameByCustIdAndAcademicYear(long custId, String skillTypeName) {
         try {
                 
                 List skillTypeList = this.getAllHqlQuery("from CommonType where  custId=" + custId+" and skillTypeName='" + skillTypeName + "'");
                 
                 if (!ObjectFunctions.isNullOrEmpty(skillTypeList)) {
                         return (CommonType) skillTypeList.get(0);
                 } else {
                         return null;
                 }
         } catch (Exception ex) {
        	 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
         }
         return null;
	 }

	
	 public long getStudentsCountBycustIdandStatus(long custId,String status){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from student where custId=");
				queryBuff.append(custId);	
				queryBuff.append(" and status='"+status+"' ");	
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	 }
	 public List getAllFuturePackagesBycurrentPackage(long studentsRange){
		 try{
			 return this.getAllHqlQuery("from PackageDetails where studentsRange > '"+studentsRange+"'");
		 }catch (Exception ex) {
			// TODO: handle exception
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	}
	 public List<Student> getStudentsByClassIdAndCustIdAndYearAndStatus(long classId,long custId,long academiYearId, String status){
		 try{
			 List studentList= this.getAllHqlQuery("from Student where classNameClassId="+classId+" and custId ="+custId+" and status='"+status+"'and academicYearId="+academiYearId);
		       if(!ObjectFunctions.isNullOrEmpty(studentList))
		       {
		    	   return (List<Student>) studentList;
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	 }
	/* public List getAllClasseNamesNotinClass(long custId,String status,long academicYearId,long classId){
			return this.getAllHqlQuery("from ClassName where (custId="+custId+" and academicYearId="+academicYearId+" and id not in ("+classId+") )");
		}*/
	 public List getAllFeeByClasIdAndCustId(String table,long custId,long classId,long academicYearId,long categoryId){
		 return this.getAllHqlQuery("from "+table+" where custId="+custId+" and classId="+classId+" and academicYearId="+academicYearId+" and categoryId="+categoryId);
	 }
	 public long getFeeTotalAmountByTerm(String queryString,long custId,long classId,long academicYearId,long schoolTermId,long categoryId,long boardingPointId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				 if ("hostelFee".equalsIgnoreCase(queryString)) { 
					 queryBuff.append("select sum(feeAmount) from "+queryString+" where custId=");
				 }else{ 
					 queryBuff.append("select sum(feeAmount) from "+queryString+" where custId=");
				 }
				queryBuff.append(custId);
				if(boardingPointId!=0){
					queryBuff.append(" and routeBoardingPointId=");
					queryBuff.append(boardingPointId);	
				}else {
					queryBuff.append(" and classId=");
					queryBuff.append(classId);	
				}
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				if ("hostelFee".equalsIgnoreCase(queryString)) {
					queryBuff.append(" and hostelTermId=");
				}else{
					queryBuff.append(" and schoolTermId=");
				}
				queryBuff.append(schoolTermId);
				if ("hostelFee".equalsIgnoreCase(queryString)) { 
					queryBuff.append(" and hostelCategoryId=");
				}else{
					queryBuff.append(" and categoryId=");
				}
				queryBuff.append(categoryId);
				//log.debug(queryBuff.toString());
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
	 public ExamTypes saveExamType(ExamTypes examType){
			try{
				
				 this.saveObject(examType);
				 return examType;
			}
			catch (HibernateException ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 
			 throw new URTDataAccessException(ex.getMessage());
		}
	}
	
	public List<ViewExamSchedule> getExamSchedulesByClassIdAndExamTypeId(long classSectionId,long examTypeId){
		try{
			List<Object[]> schdules =this.getAll("select classSectionId,academicYearId,className,section,subjectName,examTypeId,examType,minMarks,scheduleMaxMarks,examDate,startTime,endTime,custId,scheduleId,subjectId,subTypeId,subTypeName,subTypeSchedule,scheduled,classId,examTypeMaxMarks,predefinedSubType,subjectNumber,subjectSortingOrder,classSortingOrder,examTypeSortingOrder,subTypeSortingOrder from vw_examSchedule where classSectionId="+classSectionId+" and examTypeId="+examTypeId+" and subTypeSchedule='"+Constants.YES_STRING+"'");
			List<ViewExamSchedule> retList = new ArrayList<ViewExamSchedule>();
			if(ObjectFunctions.isNotNullOrEmpty(schdules)){
				for(Object[] object : schdules)
	    		{
				  if(!ObjectFunctions.isNullOrEmpty(object)){
					  ViewExamSchedule schedule=new ViewExamSchedule();
					  schedule.setClassSectionId(Long.valueOf(object[0].toString()));
					  schedule.setAcademicYearId(Long.valueOf(object[1].toString()));
					  schedule.setClassName(object[2].toString());
					  schedule.setSection(object[3].toString());
					  schedule.setSubjectName(object[4].toString());
					  schedule.setExamTypeId(Long.valueOf(object[5].toString()));
					  schedule.setExamType(object[6].toString());
					  schedule.setMinMarks(Double.valueOf(object[7].toString()));
					  if(!ObjectFunctions.isNullOrEmpty(object[8]))
						  schedule.setScheduleMaxMarks(Double.valueOf(object[8].toString()));
					  else
						  schedule.setScheduleMaxMarks(0);
					  if(!ObjectFunctions.isNullOrEmpty(object[9]))
						  schedule.setExamDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[9].toString()));
					  else
						  schedule.setExamDate(null);
					  if(!ObjectFunctions.isNullOrEmpty(object[10]))
						  schedule.setStartTime(object[10].toString());
					  if(!ObjectFunctions.isNullOrEmpty(object[11]))
						  schedule.setEndTime(object[11].toString());
					  schedule.setCustId(Long.valueOf(object[12].toString()));
					  if(!ObjectFunctions.isNullOrEmpty(object[13]))
						  schedule.setScheduleId(Long.valueOf(object[13].toString()));
					  schedule.setSubjectId(Long.valueOf(object[14].toString()));
					  schedule.setSubTypeId(Long.valueOf(object[15].toString()));
					  schedule.setSubTypeName(object[16].toString());
					  if("Y".equalsIgnoreCase(object[17].toString()))
						  schedule.setSubTypeSchedule(true);
					  else
						  schedule.setSubTypeSchedule(false);
					  if("Y".equalsIgnoreCase(object[18].toString()))
						  schedule.setScheduled(true);
					  else
						  schedule.setScheduled(false);
					  schedule.setClassId(Long.valueOf(object[19].toString()));
					  schedule.setExamTypeMaxMarks(Long.valueOf(object[20].toString()));
					  if("Y".equalsIgnoreCase(object[21].toString()))
						  schedule.setPredefinedSubType(true);
					  else
						  schedule.setPredefinedSubType(false);
					  if(!ObjectFunctions.isNullOrEmpty(object[22]))
						  schedule.setSubjectNumber(object[22].toString());
					  schedule.setSubjectSortingOrder(Integer.valueOf(object[23].toString()));
					  schedule.setClassSortingOrder(Integer.valueOf(object[24].toString()));
					  schedule.setExamTypeSortingOrder(Integer.valueOf(object[25].toString()));
					  schedule.setSubTypeSortingOrder(Integer.valueOf(object[26].toString()));
					  retList.add(schedule);
				  }
				  object = null;
	    		}
				schdules = null;
				return (List<ViewExamSchedule>) retList;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 public List getExamScheduleIdsByAcademicYearandClassNameClassIdandExamTypeIdAndSubjectIdAndSubTypeId(long academicYear,long classSectionId ,long examTypeId,long subjectId,long subTypeId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select id from examSchedules where academicYearId=");
				queryBuff.append(academicYear);		
				queryBuff.append(" and classSectionId=");		
				queryBuff.append(classSectionId);		
				queryBuff.append(" and examTypeId=");
				queryBuff.append(examTypeId);
				queryBuff.append(" and classSubjectId=");
				queryBuff.append(subjectId);	
				queryBuff.append(" and subTypeId=");
				queryBuff.append(subTypeId);
				List resultList=this.getAll(queryBuff.toString());	
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					return resultList;
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	  }
	 public List getFeetypesByClassId(String table,long classId,long custId,long academicYearId){
		 return this.getAllHqlQuery("from "+table+" where classId="+classId+" and custId="+custId+" and academicYearId="+academicYearId);
	 }
	 public List<ViewStaffSubjectsDetails> getStaffSubjectsByStaffIdAndAcademicYearIdAndCustId(long staffId,long academicYearId,long custId){
		 try{
				return (List<ViewStaffSubjectsDetails>) this.getAllHqlQuery("from ViewStaffSubjectsDetails where staffId="+staffId+" and academicYearId="+academicYearId+" and custId="+custId);
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 /*public List<ViewStudyClassSubjects> getStudyClassSubjectsByStudyClassIdAndCustId(long studyClassId,long custId){
		 try{
				return this.getAllHqlQuery("from ViewStudyClassSubjects where studyClassId="+studyClassId+" and custId="+custId);
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }*/
	 public List<ViewClassExamDetails> getExamSchedulesByClassIdandSubjectIdandExamTypeIdandAcademicYearId(long classId,long subjectId,long examTypeId,long academicYear){
	    	try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ViewClassExamDetails where classSectionId=");
				queryBuff.append(classId);
				queryBuff.append(" and classSubjectId=");
				queryBuff.append(subjectId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYear);
				queryBuff.append(" and eid=");
				queryBuff.append(examTypeId);
				return (List<ViewClassExamDetails>) this.getAllHqlQuery(queryBuff.toString());
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
		}
	 public ViewStudentMarks getStudentMarksByScheduleIdAndStudentId(long scheduleId, long studentId){
		 try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ViewStudentMarks where scheduleId=");
				queryBuff.append(scheduleId);
				queryBuff.append(" and studId=");
				queryBuff.append(studentId);
				List studentMarks=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(studentMarks)){
					return (ViewStudentMarks)studentMarks.get(0);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	 }
	 public Student getStudentByCustIdAndStudentIdAndStatus(long studentId,long custId,String status,long academicYearId){
		 try{
	    		StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select * from student WHERE id=");
				queryBuff.append(studentId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				if(!StringFunctions.isNullOrEmpty(status)){
					queryBuff.append(" and status='");
					queryBuff.append(status);
					queryBuff.append("'");
				}
				//List students=this.getAllHqlQuery(queryBuff.toString());
				List studentsList=  (List<Student>) getSession().createSQLQuery(queryBuff.toString()).addEntity(Student.class).list();
		       if(!ObjectFunctions.isNullOrEmpty(studentsList))
		       {
		    	   return (Student)studentsList.get(0);
		       }
		       else
		       {
	        	return null;
	          }
				/*if(ObjectFunctions.isNotNullOrEmpty(students)){
					return (Student)students.get(0);
				}*/
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	 }
	/* public List<ViewStudyClassSubjects> getStudyClassSubjectsBySubjectIdandCustIdandAcademicYearId(long subjectId,long custId,long academicYearId){
		 try{
				return this.getAllHqlQuery("from ViewStudyClassSubjects where subjectId="+subjectId+" and custId="+custId+" and academicYearId="+academicYearId);
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }*/
	 public List<SchoolTerms> getSchoolTermsByCustId(String clazz,long custId,String academicYear){
		 try{
				return (List<SchoolTerms>) this.getAllHqlQuery("from "+clazz+" where  custId="+custId+" and academicYearId="+academicYear+" order by fromDate");
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 public SchoolTerms saveSchoolTerms(SchoolTerms schoolTerms){
		 try {
			 
				this.saveObject(schoolTerms);
				return schoolTerms;
			} catch (HibernateException ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;

				throw new URTDataAccessException(ex.getMessage());
			}
	 }
	 public List getAllFeeByClasIdAndCustIdAndTermId(String table,List<String> classIds,long custId,long academicYearId,long termId,String categoryIds){
		 try{
			 if(!ObjectFunctions.isNullOrEmpty(classIds)){
					String commaDelimitedString = StringUtil.convertListToString(classIds);
					if(!ObjectFunctions.isNullOrEmpty(commaDelimitedString)){
						return this.getAllHqlQuery("from "+table+" where  custId="+custId+" and academicYearId="+academicYearId+" and classId in ("+commaDelimitedString+") and schoolTermId="+termId+" and categoryId in "+categoryIds);
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 public Fee getFeeByClasIdAndCustIdAndTermIdAndTypeId(long classId,long typeId,long termId,long custId,long academicYearId){
	 try{
		 List termFeeTypeDetails = this.getAllHqlQuery("from Fee where  custId="+custId+" and academicYearId="+academicYearId+" and classId="+classId+" and schoolTermId="+termId+" and feeTypeId="+typeId);
         
         if (!ObjectFunctions.isNullOrEmpty(termFeeTypeDetails)) {
                 return (Fee) termFeeTypeDetails.get(0);
         }
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	 }
	 public List<Syllabus> getSyllabusByClassIdAndSubjectIdAndCustId(long subjectId,long custId,long studyClassId){
		 try{
				return (List<Syllabus>) this.getAllHqlQuery("from Syllabus where subjectId="+subjectId+" and custId="+custId+" and studyClassId="+studyClassId);
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 public List<Syllabus> getSyllabusBySyllabusIdsAndCustId(String syllabusIds,long custId){
		 try{
				return (List<Syllabus>) this.getAllHqlQuery("from Syllabus where id in "+syllabusIds+" and custId="+custId);
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 
	public long getStudentPaidAmountByClassId(String queryString,long studentId, long classId, long custId, long academicYearId,long termId, long boardingPointId) {
		try {
			StringBuffer queryBuff = new StringBuffer();

			queryBuff.append("select sum(paymentAmount)+IFNULL(sum(paymentConcessionAmount),0) as paymentConcessionAmount from "+ queryString + " where studentId=");
			queryBuff.append(studentId);
			if (boardingPointId != 0) {
				queryBuff.append(" and routeBoardingPointId=");
				queryBuff.append(boardingPointId);
			} else {
				queryBuff.append(" and classId=");
				queryBuff.append(classId);
			}
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			queryBuff.append(" and schoolTermId=");
			queryBuff.append(termId);
			/*@Ganesh: I taken out and condition paymentCommitFeeStatus because here while sending sms to parents we need to less the concession amount while sending sms If we put paymentCommitFeeStatus=N those records not coming and considering due amount*/
			queryBuff.append(" and deleteStatus='N'").append(" and status='Y'");//.append(" and paymentCommitFeeStatus='N' ")
			
			// log.debug(queryBuff.toString());
			List resultList = this.getAll(queryBuff.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				if (!ObjectFunctions.isNullOrEmpty(resultList.get(0))) {
					Double var = ((Double) resultList.get(0));
					return var.longValue();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			RayGunException raygex = new RayGunException();
			raygex.sendRayGunException(ex);
			raygex = null;
		}
		return 0;
	}
	  public List getAllTitlesByPlaylist(String subjectName) {
			try {
				List subList = this.getAllHqlQuery("from PlayList where subjectName='"+subjectName+"'");
				
				 if (ObjectFunctions.isNotNullOrEmpty(subList)) {
					 return subList;
				 }
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
		}
	 public List getPlayListSubjects(String clazz){
		 try {
				List subList = this.getAllHqlQuery("from "+clazz+" group by subjectName");
				
				 if (ObjectFunctions.isNotNullOrEmpty(subList)) {
					 return subList;
				 }
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	 }
	 public List getPlayListForSubTopics(String subName){
		 try {
				List subTopicList = this.getAllHqlQuery("from PlayList where title='"+subName+"'");
				 if (ObjectFunctions.isNotNullOrEmpty(subTopicList)) {
					 return subTopicList;
				 }
			} catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	 public List getVideoPlayList(long playListId){
		 try {
				List videoTopicList = this.getAllHqlQuery("from PlayListVideo where playListId="+playListId);
				 if (ObjectFunctions.isNotNullOrEmpty(videoTopicList)) {
					 return videoTopicList;
				 }
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	 }
	 public List getStaffsListByRoleAndCustIdAndStatus(String roleName,long custId,String status,long academicYearId){
		 try{
			 StringBuffer queryBuff=new StringBuffer();
			 queryBuff.append("from ViewStaffPersonAccountDetails where" );
			 
			 if(!StringFunctions.isNullOrEmpty(roleName))
			 {
				queryBuff.append(" roleName in (");
				queryBuff.append(roleName);
				queryBuff.append(") and");
			 }
			queryBuff.append(" status='");
			queryBuff.append(status);
			queryBuff.append("' and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and accountExpired='N' and academicYearId<="+academicYearId);
			 //List staffList = this.getAllHqlQuery("from ViewStaffPersonAccountDetails where roleName in ("+roleName+") and custId="+custId+" and status='"+status+"' and accountExpired='N'" );
				List staffList = this.getAllHqlQuery(queryBuff.toString());
			 if (ObjectFunctions.isNotNullOrEmpty(staffList)) 
				 return staffList;
		 }catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null; 
	 } 
	 public ViewStaffPersonAccountDetails getStaffDetailsByRoleNameAndStaffIdAndCustId(String roleName,long custId,long staffId,String status,long academicYearId){
		 try{
			 List staffList = this.getAllHqlQuery("from ViewStaffPersonAccountDetails where roleName ='"+roleName+"' and custId="+custId+" and status='"+status+"' and academicYearId<="+academicYearId+" and staffId="+staffId);
			 if (ObjectFunctions.isNotNullOrEmpty(staffList)) 
				 return (ViewStaffPersonAccountDetails)staffList.get(0);
		 }catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 public long getStudentDiscountAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber){//long termId,String status
		 try{
			 StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(discountAmount) from "+queryString+" where studentId=");
				queryBuff.append(studentId);
				queryBuff.append(" and feeId in ");
				queryBuff.append(feeIds);
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and deleteStatus='");
				queryBuff.append(Constants.NO_STRING);
				queryBuff.append("' ");
				if(invoiceNumber!=0){
					queryBuff.append(" and invoiceNumber=");
					queryBuff.append(invoiceNumber);
				}
				if(queryString.equalsIgnoreCase("vw_studentFeePaymentDetails")){
					queryBuff.append(" and paymentCommitFeeStatus='N' ").append(" and status='Y'");
				}else{
					queryBuff.append(" and committedFeeStatus='N' ");
				}
				//queryBuff.append(" and committedFeeStatus='N' ");
				//log.debug(queryBuff.toString());
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return var.longValue();
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return 0; 
	 }
	 
	 
	 
	 public ClassTeacher getClassTeacherByAcademicIdAndNotInCurrentClass(long staffId,long custId,long academicYearId,long classId){
			try{
				 List classTeacherList= this.getAllHqlQuery("from ClassTeacher where teacherId="+staffId+" and custId ="+custId+" and academicYearId="+academicYearId+" and classTeacher='Y' and studyClassId not in ("+classId+")");
			       if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
			       {
			    	   return (ClassTeacher)classTeacherList.get(0);
			       }
			       else
			       {
		        	return null;
		        }
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}	
	 public void removeClassTeacherByCustId(long classTeacherId,long custId){
		 try{
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from classTeacher where id=");
					sqlString.append(classTeacherId);
					sqlString.append(" and custId=");
					sqlString.append(custId);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
			
	      }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
	 }
	 public State getStateCodeByStateName(String stateName){
		 try {
			 	StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from State where stateName='");
				queryBuff.append(stateName);
				queryBuff.append("'");
				List state=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(state)){
					return (State)state.get(0);
				}
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	 }
	 public List<ViewStaffLeaveDetails> getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId) {
	       try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ViewStaffLeaveDetails where leaveStatus= '");
	            queryString.append(leaveStatus);
	            queryString.append("' and custId =");
	            queryString.append(customerId);
	            queryString.append(" and roleName in (");
	            queryString.append(roleName);
	            queryString.append(") and academicYearId =");
	            queryString.append(academicYearId);
	            queryString.append(" and status='Y' order by startDate desc");
	            /*below lines comments on 2-07-13 by cvs .Because of staff absent before day next day staff apply the leave then we show requistList to admin */
	           /* queryString.append(" and startDate >= '");
	            queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
	            queryString.append("'");*/
	            List leavesList =  this.getAllHqlQuery(queryString.toString());
		           if(!ObjectFunctions.isNullOrEmpty(leavesList))
		           {
		               return (List<ViewStaffLeaveDetails>) leavesList;
		           }
	           }
	       catch (Exception ex) {
	           log.error("getting getAllPendingStatusLeaves failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
	 public List<ViewStudentLeaveDetails> getAllStudentLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(long customerId,String roleName,String leaveStatus,long academicYearId) {
	       try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ViewStudentLeaveDetails where leaveStatus= '");
	            queryString.append(leaveStatus);
	            queryString.append("' and custId =");
	            queryString.append(customerId);
	            queryString.append(" and roleName ='");
	            queryString.append(roleName);
	            queryString.append("' and academicYearId =");
	            queryString.append(academicYearId);
	            queryString.append(" order by startDate desc");
	            List leavesList =  this.getAllHqlQuery(queryString.toString());
		           if(!ObjectFunctions.isNullOrEmpty(leavesList))
		           {
		               return (List<ViewStudentLeaveDetails>) leavesList;
		           }
	           }
	       catch (Exception ex) {
	           log.error("getting getAllPendingStatusLeaves failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
	 public ViewUserRoles getViewUserRolesByUserIdAndCustId(long userId, long customerId,String status) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewUserRoles where accountId=");
	           queryString.append(userId);
	           queryString.append(" and custId="+customerId);
	           queryString.append(" and accountExpired='"+status+"'");
	           List userRoles = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(userRoles))
	           {
	               return  (ViewUserRoles)userRoles.get(0);
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		
		}
	 public List getStudentClassFeePamentByTodayDateAndcustIdAndStatus(long userCustId, long reqYear,String todayDate,String status){
		 try{
			 List feeDayList = this.getAllHqlQuery("from ViewStudentClassFeePaymentDetails where custId="+userCustId+" and academicYearId="+reqYear+" and paymentDate='"+todayDate+" 00:00:00' and paymentStatus='"+status+"'");
			 if (ObjectFunctions.isNotNullOrEmpty(feeDayList)) 
				 return feeDayList;
		 }catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null; 
	 } 	
	 public List getStudentPaidTermsAmountByClassIdAndStatus(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds,long invoiceNumber){//long termId,String status
		 try{
				 StringBuffer queryBuff=new StringBuffer();
				 if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
					 queryBuff.append("select sum(paymentAmount),paymentDate,hostelStudentPaymentId,rollNumber,firstName,lastName,paymentType,hostelTermName,hostelFeeType,ddNumber,chequeNumber,invoiceNumber,sum(discountAmount) from "+queryString+" where studentId=");
					}else{
						queryBuff.append("select sum(paymentAmount),paymentDate,studentPaymentId,rollNumber,firstName,lastName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber from "+queryString+" where studentId=");
					}
					queryBuff.append(studentId);	
					queryBuff.append(" and classId=");
					queryBuff.append(classId);
					queryBuff.append(" and custId=");
					queryBuff.append(custId);
					queryBuff.append(" and academicYearId=");
					queryBuff.append(academicYearId);
					if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
						queryBuff.append(" and feeId in ");
					}else{
						queryBuff.append(" and feeId in ");
					}
					queryBuff.append(feeIds);
					queryBuff.append(" and deleteStatus='N'");
					queryBuff.append(" and invoiceNumber=");
					queryBuff.append(invoiceNumber);
					queryBuff.append(" group by studentId");
					List resultOverList=this.getAll(queryBuff.toString());
					if (!ObjectFunctions.isNullOrEmpty(resultOverList)) {
						return resultOverList;
					} return null;    
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return null; 
	 }
	 public List getStaffPaidTermsAmountByStaffIdAndStatus(String queryString,long staffId,long custId,long academicYearId,String feeIds,long invoiceNumber){//long termId,String status
		 try{
				 StringBuffer queryBuff=new StringBuffer();
				 if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
					 queryBuff.append("select sum(paymentAmount),paymentDate,hostelStaffPaymentId,firstName,lastName,paymentType,hostelTermName,hostelFeeType,ddNumber,chequeNumber,invoiceNumber,sum(discountAmount) from "+queryString+" where staffId=");
					}else{
						queryBuff.append("select sum(paymentAmount),paymentDate,hostelStudentPaymentId,firstName,lastName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber from "+queryString+" where staffId=");
					}
					queryBuff.append(staffId);	
					queryBuff.append(" and custId=");
					queryBuff.append(custId);
					queryBuff.append(" and academicYearId=");
					queryBuff.append(academicYearId);
					if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
						queryBuff.append(" and staffHostelFeeId in ");
					}else{
						queryBuff.append(" and feeId in ");
					}
					queryBuff.append(feeIds);
					if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
						queryBuff.append(" and deleteStatus='Y'");
					}
					queryBuff.append(" and invoiceNumber=");
					queryBuff.append(invoiceNumber);
					queryBuff.append(" group by staffId");
					List resultOverList=this.getAll(queryBuff.toString());
					if (!ObjectFunctions.isNullOrEmpty(resultOverList)) {
						return resultOverList;
					} return null;    
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null; 
	 }
	public List getClassFeeTermsByStudentIdAndStatusAndCurrentDate(String queryString,long studentId, long custId, long academicYearId, long classId,String todayDate, String feeIds, long invoiceNumber) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("select sum(paymentAmount),paymentDate,studentPaymentId,rollNumber,firstName,lastName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber,feeSettingId,settingName,admissionNumber,id,sum(discountAmount),committedFee,paymentConcessionAmount from "+queryString+" where studentId=");
			queryBuff.append(studentId).append(" and custId=").append(custId).append(" and academicYearId=").append(academicYearId).append(" and feeId in ").append(feeIds).append(" and paymentCommitFeeStatus='N' ").append(" and deleteStatus='N' and status='Y' and paymentAmount !=0 ");
			if(!StringFunctions.isNullOrEmpty(todayDate)){
			queryBuff.append(" and paymentDate like '%"+todayDate+"%'");
			}
			if(invoiceNumber !=0){
			queryBuff.append(" and invoiceNumber=");
			queryBuff.append(invoiceNumber);
			}
			queryBuff.append(" group by studentId");
			log.debug(queryBuff.toString());
			List resultList = this.getAll(queryBuff.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List getHostelFeeTermsByStaffIdAndStatusAndCurrentDate(String queryString,long staffId, long custId, long academicYearId,String todayDate, String feeIds, long invoiceNumber) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			 if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
	        	   queryBuff.append("select sum(paymentAmount),paymentDate,hostelStaffPaymentId,firstName,lastName,paymentType,hostelTermName,hostelFeeType,ddNumber,chequeNumber,invoiceNumber,sum(discountAmount) from "+queryString+" where staffId=");
				}else{
					queryBuff.append("select sum(paymentAmount),paymentDate,studentPaymentId,firstName,lastName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber from "+queryString+" where studentId=");
				}
			queryBuff.append(staffId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryBuff.append(" and deleteStatus='Y'");
			}
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryBuff.append(" and staffHostelFeeId in ");
			}else{
				queryBuff.append(" and feeId in ");
			}
			queryBuff.append(feeIds);
			queryBuff.append(" and paymentDate='");
			queryBuff.append(todayDate);
			queryBuff.append(" 00:00:00'");
			queryBuff.append(" and invoiceNumber=");
			queryBuff.append(invoiceNumber);
			queryBuff.append(" group by staffId");
			List resultList = this.getAll(queryBuff.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	
	
	 public List getAllSmsEventsByAcademicYear(String academicYear){
		 try {
				List smsEventsList = this.getAllHqlQuery("from SmsEvents where academicYear='"+academicYear+"'");
				 if (ObjectFunctions.isNotNullOrEmpty(smsEventsList)) {
					 return smsEventsList;
				 }
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null; 
	 }
	 public List<SubType> getAllSubTypesBySubTypeIds(String subTypeIds,long custId){
		 try {
				List subTypes = this.getAllHqlQuery("from SubType where id in "+subTypeIds+" and custId="+custId );
				 if (ObjectFunctions.isNotNullOrEmpty(subTypes)) {
					 return subTypes;
				 }
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;	
			}
			return null; 
	 }
	 public SchoolGrades getSchoolGradeByName(String gradName, long academicYearId){
		 try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from SchoolGrades where gradeName='");
	           queryString.append(gradName);
	           queryString.append("'");
	           queryString.append(" and academicYearId="+academicYearId);
	           List SchoolGradesList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(SchoolGradesList))
	           {
	               return (SchoolGrades)SchoolGradesList.get(0);
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
	 }
	 public List<ExamTypes> checkExamTypeByNameAndCustId(String examType,long custId) {
	        try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ExamTypes where ");
	            queryString.append(" examType ='");
	            queryString.append(examType);
	            queryString.append("'");
	            queryString.append(" and custId="+custId);
	            List examTypNames =  this.getAllHqlQuery(queryString.toString());
	            
	            if(!ObjectFunctions.isNullOrEmpty(examTypNames))
	            {
	                Collections.sort(examTypNames);
	                return (List<ExamTypes>) examTypNames;
	            }
	            return null;
	        } catch (RuntimeException ex) {
	            log.error("Get failed", ex);
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	            throw ex;
	        }
		}
	 public List getSubTypesByClassSectionIdAndExamTypeId(long classSectionId,long examTypeId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ViewClassExamDetails where classSectionId=");
				queryBuff.append(classSectionId);	
				queryBuff.append(" and eid=");
				queryBuff.append(examTypeId);
				queryBuff.append(" group by subTypeId");
				return  this.getAllHqlQuery(queryBuff.toString());
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	}
	 public void removeClassTeachersByStudyClassIdandSubjectIdsandCustId(long classSectionId,long custId,String subjectIds){
		 try{
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from classTeacher where studyClassId=");
					sqlString.append(classSectionId);
					sqlString.append(" and custId=");
					sqlString.append(custId);
					sqlString.append(" and studySubjectId in");
					sqlString.append(subjectIds);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	 public void removeSyllabusByStudyClassIdandSubjectIdsCustId(long classSectionId,long custId,String subjectIds){
		 try{
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from syllabus where studyClassId=");
					sqlString.append(classSectionId);
					sqlString.append(" and custId=");
					sqlString.append(custId);
					sqlString.append(" and subjectId in ");
					sqlString.append(subjectIds);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	 public List getClassTeachersByAcademicIdAndSubjectIdAndStaffIdandCustId(long staffId,long subjectId,long custId,long academicYearId){
		 if (log.isDebugEnabled()) {
				log.debug("Entering 'getClassTeachersByAcademicIdAndSubjectIdAndStaffIdandCustId' method");
			}
		try{
			// List classTeacherList= this.getAllHqlQuery("from ClassTeacher where teacherId="+staffId+" and studySubjectId="+subjectId+" and custId ="+custId+" and academicYearId="+academicYearId);
			StringBuffer query = new StringBuffer("select * from classTeacher WHERE teacherId="+staffId+" and studySubjectId="+subjectId+" and custId ="+custId+" and academicYearId="+academicYearId);
			List classTeacherList=  (List<ClassTeacher>) getSession().createSQLQuery(query.toString()).addEntity(ClassTeacher.class).list();
	       if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
	       {
	    	   return classTeacherList;
	       }
	       else
	       {
        	return null;
        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 
	 public void removeClassTeacherByClassTeacherIdAndSubjectIdAndCustId( long classTeacherId, long subjectId, long custId, long academicYearId) {
		 try{
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from classTeacher where id=");
					sqlString.append(classTeacherId);
					sqlString.append(" and studySubjectId=");
					sqlString.append(subjectId);
					sqlString.append(" and custId=");
					sqlString.append(custId);
					sqlString.append(" and academicYearId=");
					sqlString.append(academicYearId);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
			
	      }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
	 }
	 
	 public List getClassSubjectBySubjectId(long subjectId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select * from ClassSubject where subjectId="+subjectId);
           List classList = this.getAll(queryString.toString());  
           if (ObjectFunctions.isNullOrEmpty(classList)) {
               return null;
           } else {
               return classList;
           }
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	 public ClassTeacher getClassTeachersByAcademicIdAndSubjectIdAndClassIdandCustId(long studyClassId,long subjectId,long custId,long academicYearId){
		 if (log.isDebugEnabled()) {
				log.debug("Entering 'getClassTeachersByAcademicIdAndSubjectIdAndClassIdandCustId' method");
			}
		try{
			 List classTeacherList= this.getAllHqlQuery("from ClassTeacher where studyClassId="+studyClassId+" and studySubjectId="+subjectId+" and custId ="+custId+" and academicYearId="+academicYearId);
		       if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
		       {
		    	   if(!ObjectFunctions.isNullOrEmpty(classTeacherList.get(0)))
		    	   {
		    		   return (ClassTeacher) classTeacherList.get(0);
		    	   }
		       }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 public List<ViewStaffSubjectsDetails> getClassTeacherClassesListByStaffIdCustIdAcademicYearIdGroupByClassTecherStatusAndStudyClassId(long academicYearId,long staffId,long custId){
		try{
			 return (List<ViewStaffSubjectsDetails>) this.getAllHqlQuery("from ViewStaffSubjectsDetails where  academicYearId="+academicYearId+" and staffId="+staffId+" and custId="+custId+" and classTeacher='Y' group by classTeacher,studyClassId");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
		 
	 }
	 public List<ViewStaffSubjectsDetails> getTeacherClassesAndSubjectsListByStaffIdCustIdAcademicYearIdOrderbyStudyClassId(long academicYearId,long staffId,long custId){
			try{
				 return ( List<ViewStaffSubjectsDetails>) this.getAllHqlQuery("from ViewStaffSubjectsDetails where  academicYearId="+academicYearId+" and staffId="+staffId+" and custId="+custId+" and classTeacher='N' order by studyClassId");
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
			 
		 }

	public List<SchoolTerms> checkSchoolTermsByNameAndCustId(String termName,long custId,long academicYearId) {
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("from SchoolTerms where ");
			queryString.append(" termName ='");
			queryString.append(termName);
			queryString.append("'");
			queryString.append(" and custId=" + custId);
			queryString.append(" and academicYearId=" + academicYearId);
			List termNameNames = this.getAllHqlQuery(queryString.toString());

			if (!ObjectFunctions.isNullOrEmpty(termNameNames)) {
				Collections.sort(termNameNames);
				return (List<SchoolTerms>) termNameNames;
			}
			return null;
		} catch (RuntimeException ex) {
			log.error("Get failed", ex);
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw ex;
		}
	}

	public List<SchoolTerms> getSchoolTermsByDuedate(String table,long custId,long academicYearId, String todayDate,long feeSettingId) {
		try {
			// Criteria schoolTerms=
			// getSession().createCriteria(SchoolTerms.class);
			// schoolTerms.add(Expression.between(propertyName, lo, hi))
			StringBuffer queryString = new StringBuffer();
			queryString.append("from "+table+" where ");
			queryString.append(" academicYearId =");
			queryString.append(academicYearId);
			queryString.append(" and feeSettingId =");
			queryString.append(feeSettingId);
			queryString.append(" and custId=" + custId);
			queryString.append(" and dueDate >'");
			queryString.append(todayDate);
			queryString.append(" 00:00:00' order by dueDate");
			List termNameNames = this.getAllHqlQuery(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(termNameNames)) {
				Collections.sort(termNameNames);
				return (List<SchoolTerms>) termNameNames;
			}
			return null;
		} catch (RuntimeException ex) {
			log.error("Get failed", ex);
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw ex;
		}
	}
	public List<SchoolTerms> getSchoolTermsOrderByDuedate(String table,long custId,long academicYearId,long feeSetiingId){
		try{
			 return (List<SchoolTerms>) this.getAllHqlQuery("from "+table+" where  academicYearId="+academicYearId+" and custId="+custId+" and feeSettingId="+feeSetiingId+" order by dueDate");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List getDescriptionByCustIdAndAcademicYear(long custId,long academicYearId){
		return this.getAllHqlQuery("from SchoolHolidays where custId="+custId+" and academicYearId='"+academicYearId+"' group by description");
	}
	public List<StaffDailyAttendance> getStaffAttendance(long custId,long academicYearId,String date) {
		try {
			List tempList =null;	
			StringBuffer sb = new StringBuffer();
			sb.append(" from StaffDailyAttendance where custId = " + custId);
			sb.append(" and academicYearId = " + academicYearId);
			sb.append(" and present = 'N'");
			StringBuffer sbdate = new StringBuffer();
			if (!StringFunctions.isNullOrEmpty(date)) {
				sbdate.append(" and attendanceDate >= '");
				sbdate.append(StringFunctions.getDateTimeBegin(date));
				sbdate.append("' and attendanceDate <= '");
				sbdate.append(StringFunctions.getDateTimeEnd(date)+"'"); 
			} 
			tempList = this.getAllHqlQuery(sb.toString() + sbdate.toString());
			return (List<StaffDailyAttendance>) tempList;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentClassFeePaymentDetails> getAllReceiptsStudentClassFeeByPaymentId(long spId,long invoiceNumber){
        try {
              StringBuffer queryString = new StringBuffer();
              queryString.append("from ViewStudentClassFeePaymentDetails where studentId=");
              queryString.append(spId);
              queryString.append(" and invoiceNumber=");
              queryString.append(invoiceNumber);
             /// queryString.append(" group by schoolTermId");
              List feesPaymentReceiptList = this.getAllHqlQuery(queryString.toString());
              if(!ObjectFunctions.isNullOrEmpty(feesPaymentReceiptList))
              {
                  return (List<ViewStudentClassFeePaymentDetails>) feesPaymentReceiptList;
              }
               return null;    
           } catch (Exception ex) {
               ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
           }return null;
	}
	public long getFeeTotalAmountByCustId(long custId,long termId,long academicYearId,long classId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(feeAmount) from vw_studentClassFeePaymentDetails where custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and schoolTermId=");
				queryBuff.append(termId);
				queryBuff.append(" and classId=");
				queryBuff.append(classId);
				queryBuff.append(" and studentId !=0 and status='"+Constants.YES_STRING+"' and description is null");
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
	public long getDiscountTotalByCustId(long custId,long termId,long academicYearId,String status,long classId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(discountAmount) from vw_studentFeePaymentDetails where custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and schoolTermId=");
				queryBuff.append(termId);
				if(!StringFunctions.isNullOrEmpty(status)){
					queryBuff.append(" and paymentStatus='");
					queryBuff.append(status);
					queryBuff.append(" ' ");
				}
				queryBuff.append(" and classId="+classId);
				queryBuff.append(" and status='"+Constants.YES_STRING+"' and description is null");
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
	public long getTotalStudentsPaidAmount(long custId,long termId,long academicYearId,String status,long classId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(paymentAmount) from vw_studentFeePaymentDetails where custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and schoolTermId=");
				queryBuff.append(termId);
				if(!StringFunctions.isNullOrEmpty(status)){
					queryBuff.append(" and paymentStatus='");
					queryBuff.append(status);
					queryBuff.append("'");
				}
				queryBuff.append(" and classId=");
				queryBuff.append(classId);
				queryBuff.append(" and status='"+Constants.YES_STRING+"' and description is null");
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
	
	public List getSchoolTermsByCustIdAndAcademicYearId(String table,long custId,long academicYearId) {
		try {
			StringBuffer queryString = new StringBuffer();
			 if ("HostelTerms".equalsIgnoreCase(table)) { queryString.append("from "+table+" where ");
			 }else{ queryString.append("from "+table+" where "); 
			 }
			queryString.append(" academicYearId =");
			queryString.append(academicYearId);
			queryString.append(" and custId=" + custId);
			List termNameNames = this.getAllHqlQuery(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(termNameNames)) {
				Collections.sort(termNameNames);
				return termNameNames;
			}
			return null;
		} catch (RuntimeException ex) {
			log.error("Get failed", ex);
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw ex;
		}
	}
	public List getSchoolTermsByCompleteDuedate(String table,long custId,long academicYearId, String todayDate) {
		try {
			// Criteria schoolTerms=
			// getSession().createCriteria(SchoolTerms.class);
			// schoolTerms.add(Expression.between(propertyName, lo, hi))
			StringBuffer queryString = new StringBuffer();
			 if ("HostelTerms".equalsIgnoreCase(table)) { queryString.append("from "+table+" where ");
			 }else{ queryString.append("from "+table+" where "); 
			 }
			queryString.append(" academicYearId =");
			queryString.append(academicYearId);
			queryString.append(" and custId=" + custId);
			queryString.append(" and dueDate <'");
			queryString.append(todayDate);
			queryString.append(" 00:00:00' order by dueDate");
			List termNameNames = this.getAllHqlQuery(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(termNameNames)) {
				Collections.sort(termNameNames);
				return termNameNames;
			}
			return null;
		} catch (RuntimeException ex) {
			log.error("Get failed", ex);
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw ex;
		}
	}
	public List getClassFeeTermsByStudentIdAndStatusAndCurrentAndEndDate(String queryString,long studentId,long custId,long academicYearId,long classId,long termId,String fromdate,String todayDate,String status,long invoiceNumber){
		try{
			 StringBuffer queryBuff=new StringBuffer();
			 if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
	        	   queryBuff.append("select sum(paymentAmount),paymentDate,hostelStudentPaymentId,rollNumber,firstName,lastName,paymentType,hostelTermName,hostelFeeType,ddNumber,chequeNumber,invoiceNumber,sum(discountAmount) from "+queryString+" where studentId=");
				}else{
					queryBuff.append("select sum(paymentAmount),sum(discountAmount),paymentDate,studentPaymentId,rollNumber,firstName,lastName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber from "+queryString+" where id=");
				}
				queryBuff.append(studentId);	
				queryBuff.append(" and classId=");
				queryBuff.append(classId);
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				 if ("vw_studentFeePaymentDetails".equalsIgnoreCase(queryString)) {
					queryBuff.append(" and deleteStatus='N'");
				 }
				 if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
						queryBuff.append(" and deleteStatus='Y'");
				 }
				 if ("vw_studentTransportFeePaymentDetails".equalsIgnoreCase(queryString)) {
						queryBuff.append(" and deleteStatus='Y'");
					 }
				 if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
						queryBuff.append(" and hostelTermId=");
				 }else{
						queryBuff.append(" and schoolTermId=");
				 }
				queryBuff.append(termId);
				queryBuff.append(" and paymentDate BETWEEN '");
				queryBuff.append(fromdate);
				queryBuff.append(" 00:00:00'");
				queryBuff.append(" and '");
				queryBuff.append(todayDate);
				queryBuff.append(" 00:00:00'");
				if(!StringFunctions.isNullOrEmpty(status)){
					queryBuff.append(" and paymentStatus='");
					queryBuff.append(status);
					queryBuff.append("'");
				}
				queryBuff.append(" and invoiceNumber=");
				queryBuff.append(invoiceNumber);
				queryBuff.append(" group by studentId");
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				} return null;    
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getFeeTermsByStaffIdAndStatusAndCurrentAndEndDate(String queryString,long staffId,long custId,long academicYearId,long termId,String fromdate,String todayDate,String status,long invoiceNumber){
		try{
			 StringBuffer queryBuff=new StringBuffer();
			 if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
	        	   queryBuff.append("select sum(paymentAmount),paymentDate,hostelStaffPaymentId,firstName,lastName,paymentType,hostelTermName,hostelFeeType,ddNumber,chequeNumber,invoiceNumber,sum(discountAmount) from "+queryString+" where staffId=");
				}else{
					queryBuff.append("select sum(paymentAmount),sum(discountAmount),paymentDate,studentPaymentId,rollNumber,lastName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber from "+queryString+" where staffId=");
				}
				queryBuff.append(staffId);	
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				 if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
					 queryBuff.append(" and deleteStatus=''");
				 }
				 if ("vw_hostelStaffFeePaymentDetails".equalsIgnoreCase(queryString)) {
						queryBuff.append(" and staffHostelFeeId=");
				 }else{
						queryBuff.append(" and schoolTermId=");
				 }
				queryBuff.append(termId);
				queryBuff.append(" and paymentDate BETWEEN '");
				queryBuff.append(fromdate);
				queryBuff.append(" 00:00:00'");
				queryBuff.append(" and '");
				queryBuff.append(todayDate);
				queryBuff.append(" 00:00:00'");
				if(!StringFunctions.isNullOrEmpty(status)){
					queryBuff.append(" and paymentStatus='");
					queryBuff.append(status);
					queryBuff.append("'");
				}
				queryBuff.append(" and invoiceNumber=");
				queryBuff.append(invoiceNumber);
				queryBuff.append(" group by staffId");
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				} return null;    
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/*public StudentPayment getStudentDetailsByIdAndCustIdAndYearId(long studentId,long custId,long academicYearId) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("from StudentPayment where ");
		queryString.append(" academicYearId =");
		queryString.append(academicYearId);
		queryString.append("' and custId=");	
		queryString.append(custId);
		queryString.append(" and studentId=");
		queryString.append(studentId);
		List studentPaymentList = this.getAllHqlQuery(queryString.toString());
		if (!ObjectFunctions.isNullOrEmpty(studentPaymentList)) {
			StudentPayment studentPayment = (StudentPayment) studentPaymentList.get(0);
			return studentPayment;
		} else {
			return null;
		}
	}*/
	public List<ViewStudentPersonAccountDetails> getStudentbyClassNameClassIdAndCustIdAndAcademicYear(long classNameClassId, long custId, long academicYearId,String status) {
		try {
			return (List<ViewStudentPersonAccountDetails>) this.getAllHqlQuery("from ViewStudentPersonAccountDetails where custId="+ custId + " and classNameClassId="+classNameClassId+" and academicYearId="+academicYearId+" and status='"+status+"' ");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List getAllStudentInvoiceFeeDetailsByCustId(String queryString,long customerId, long academicYearId, long termId, String today,String daysBetwwenenfromDate,String daysBetwwenendDate,String transportmode,long feeSettingId,String selectedClassSectionId) {// ,String paymentStatus
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select studentId,invoiceNumber,paymentStatus,id from "+ queryString + " where custId=" + customerId);
			if (!StringFunctions.isNullOrEmpty(today)) {
				queryStr.append(" and paymentDate like ");
				queryStr.append("'%" +today+ "%'");
			}
			queryStr.append(" and academicYearId=");
			queryStr.append(academicYearId);
			if(feeSettingId !=0){
				queryStr.append(" and feeSettingId=");
				queryStr.append(feeSettingId);
			}
			queryStr.append(" and schoolTermId=");
			queryStr.append(termId);
			queryStr.append(" and paymentCommitFeeStatus='N' ");
			if(!ObjectFunctions.isNullOrEmpty(selectedClassSectionId)){
				queryStr.append(" and classSectionId in");
				queryStr.append(selectedClassSectionId);
			}
			if (!StringFunctions.isNullOrEmpty(daysBetwwenenfromDate) && !StringFunctions.isNullOrEmpty(daysBetwwenendDate)) {
				queryStr.append(" and paymentDate BETWEEN '");
				queryStr.append(daysBetwwenenfromDate);
				queryStr.append(" 00:00:00'");
				queryStr.append(" and '");
				queryStr.append(daysBetwwenendDate);
				queryStr.append(" 00:00:00'");
			}

			if (StringFunctions.isNotNullOrEmpty(transportmode)) {
			queryStr.append(" and transportMode='");
			queryStr.append(transportmode+"'");
			queryStr.append(" and vehicleAcademicDetailsId !=0");
			}
			queryStr.append(" and description is null and  invoiceNumber!=0  group by invoiceNumber order by sortingOrder,classSectionId");
			log.debug(queryStr.toString());
			List resultList = this.getAll(queryStr.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List getClassWiseFeeDefaultersByCustId(String table,long customerId, long academicYearId, long classId, long termId, String transportmode,String hostelmode) {
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("select studentId,invoiceNumber,id from " + table+ " where custId=" + customerId);
			queryString.append(" and classId=");
			queryString.append("'" + classId + "'");
			queryString.append(" and academicYearId=");
			queryString.append(academicYearId);
			queryString.append(" and schoolTermId=");
			queryString.append(termId);
			queryString.append(" and description is null");
			if (StringFunctions.isNotNullOrEmpty(transportmode)) {
				queryString.append(" and transportMode='");
				queryString.append(transportmode + "'");
				queryString.append(" and vehicleAcademicDetailsId !=0");
			}
			if (StringFunctions.isNotNullOrEmpty(hostelmode)) {
				queryString.append(" and hostelmode='");
				queryString.append(hostelmode + "'");
			}
			queryString.append(" group by studentId");
			List resultList = this.getAll(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				// Collections.sort(resultList);
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getAllStaffInvoiceFeeDetailsByCustId(String queryString,long customerId, long academicYearId, long termId, String today,String status) {// ,String paymentStatus
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select staffId,invoiceNumber from "+ queryString + " where custId=" + customerId);
			if (!StringFunctions.isNullOrEmpty(today)) {
				queryStr.append(" and paymentDate=");
				queryStr.append("'" + today + " 00:00:00'");
			}
			queryStr.append(" and academicYearId=");
			queryStr.append(academicYearId);
			if ("vw_staffHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryStr.append(" and hostelTermId=");
			} else {
				queryStr.append(" and schoolTermId=");
			}
			queryStr.append(termId);
			queryStr.append(" and status='");
			queryStr.append(status);
			queryStr.append("'");
			if ("vw_staffHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryStr.append(" and bedId !=0 ");
			}
			queryStr.append(" group by staffId,invoiceNumber");//
			List resultList = this.getAll(queryStr.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewStudentMarksDetails getStudentMarksByStudIdAndExamTypeIdAndSubTypeIdAndSubjectId(long studId,long examTypeId,long subTypeId,long subjectId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("from ViewStudentMarksDetails where studId="+studId);
           queryString.append(" and examTypeId=");
           queryString.append(examTypeId);
           queryString.append(" and subTypeId=");
           queryString.append(subTypeId);
           queryString.append(" and subjectId=");
           queryString.append(subjectId);
           List classList = this.getAllHqlQuery(queryString.toString());
           if (!ObjectFunctions.isNullOrEmpty(classList)) {
               return (ViewStudentMarksDetails)classList.get(0);
           } 
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	public SchoolCategory getCategoryIdByCustId(String status,long custId){
		 try {
				List categoryIdList = this.getAllHqlQuery("from SchoolCategory where categoryName='"+status+"' and custId="+custId);
				if(!ObjectFunctions.isNullOrEmpty(categoryIdList))
		           {
		               return (SchoolCategory)categoryIdList.get(0);
		           }
				 else{
					 return null;
				 }
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null; 
	 }
	public void removeSchedulesByScheduleIds(String scheduleIds){
		try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from examSchedules where id in");
				sqlString.append(scheduleIds);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
	}
	public void updateExamScheduleStartDateAndEndDateByClassSectionIdAndExamTypeId(long classSectionId,long examType,String startDate,String endDate){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update examSchedules set startDate ='"); 
	           sqlString.append(startDate);
	           sqlString.append("', endDate = '");
	           sqlString.append(endDate);
	           sqlString.append("' where classSectionId=");
	           sqlString.append(classSectionId);
	           sqlString.append(" and examTypeId="+examType);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}

	public ClassTeacher getStudySubjectTeacherByStudyClassAndCustId(long custId, long academicYearId, long staffId, long studyClassId,long subjectId) {
		try {
			List classTeacherList = this.getAllHqlQuery("from ClassTeacher where custId=" + custId+ " and academicYearId=" + academicYearId+ " and teacherId=" + staffId+ " and studyClassId=" + studyClassId+ " and studySubjectId=" + subjectId);
			if (!ObjectFunctions.isNullOrEmpty(classTeacherList)) {
				return (ClassTeacher) classTeacherList.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public void removeClassTeachersByStudyClassIdandSubjectIds(long staffId,long custId,long academicYearId,String subjectId){
		try{
			Query qry = getSession().createSQLQuery("delete from classTeacher where teacherId="+staffId+" and custId ="+custId+" and academicYearId="+academicYearId+" and studySubjectId not in "+subjectId);
			log.debug(qry);
			int row = qry.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public ClassTeacher getAllClasseNamesByClassId(long subjectId, long staffId, String classId){
		try{
			 List classTeacherList= this.getAllHqlQuery("from ClassTeacher where studySubjectId="+subjectId+" and teacherId ="+staffId+" and studyClassId="+classId);
		       if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
		       {
		    	   return (ClassTeacher)classTeacherList.get(0);
		       }
		       else
		       {
	        	return null;
	        }
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public Staff getStaffByAcountIdAndCustId(long accountId,String status,long custId){
    	try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from Staff where accountId=");
			queryBuff.append(accountId);
			queryBuff.append(" and status='");
			queryBuff.append(status);
			queryBuff.append("'");
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			List staffList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(staffList)){
				return (Staff)staffList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
    }
	public StaffElgibleSubjects getStudySubjectTeacherByStudyClassAndCustId(long academicYearId, long staffId, long subjectId) {
		try {
			List staffElgibleSubList = this.getAllHqlQuery("from StaffElgibleSubjects where academicYearId="+academicYearId+" and staffId=" + staffId+" and studySubjectId="+subjectId);
			if (!ObjectFunctions.isNullOrEmpty(staffElgibleSubList)) {
				return (StaffElgibleSubjects) staffElgibleSubList.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	public void removeSelecteEndDateHolidays(long custId, long academicYearId,String endDate) {
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("delete from schoolHolidays where custId=");
			sqlString.append(custId + " and academicYearId=");
			sqlString.append(academicYearId);
			sqlString.append(" and startDate > '");
			sqlString.append(endDate);
			sqlString.append(" 00:00:00'");
			Query qry = getSession().createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	public List getSchoolHolidaysByDescription(long custId, long academicYearId,String description) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("from SchoolHolidays where custId=");
			queryBuff.append(custId + " and academicYearId=");
			queryBuff.append(academicYearId);
			queryBuff.append(" and description='");
			queryBuff.append(description);
			queryBuff.append("'");
			return  this.getAllHqlQuery(queryBuff.toString());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 public List getSubTypesByClassSectionIdAndExamTypeIdAndSubjectId(long classSectionId,long examTypeId,long subjectId){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ViewClassExamDetails where classSectionId=");
				queryBuff.append(classSectionId);	
				queryBuff.append(" and eid=");
				queryBuff.append(examTypeId);
				queryBuff.append(" and classSubjectId=");
				queryBuff.append(subjectId);
				queryBuff.append(" group by subTypeId");
				return  this.getAllHqlQuery(queryBuff.toString());
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	}
	 public List getAllByCustIdAndStatus(String clazz,long custId,String status,long academicYearId){
		 try {
			 if(academicYearId > 0)
				return this.getAllHqlQuery("from "+clazz+" where  accountExpired='"+status+"' and custId="+custId+" and academicYearId="+academicYearId);
			 else
				 return this.getAllHqlQuery("from "+clazz+" where  accountExpired='"+status+"' and custId="+custId);
			 
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	 }
	 public List getParentSearchResultsByStudentDetails(long custId,String fName,String lName,String pName,String adNumber,String status){
		 try {
		//	 String NULL=null;
			 List parentSearchResultsList =null;
			 if (custId>0) {
				 parentSearchResultsList= this.getAllHqlQuery("from ViewStudentPersonAccountDetails where firstName like '%"+fName+"%' and lastName like '%"+lName+"%' and fatherName like '%"+pName+ "%' and admissionNumber= '"+adNumber+"' and status='"+status+"' and custId=" + custId);
			 }else{
				 parentSearchResultsList= this.getAllHqlQuery("from ViewStudentPersonAccountDetails where firstName like '%"+fName+"%' and lastName like '%"+lName+"%' and fatherName like '%"+pName+"%' and admissionNumber ='"+adNumber+"' and status='"+status+"' and parentId is null");
			 }
			 if (ObjectFunctions.isNotNullOrEmpty(parentSearchResultsList)) {
					return parentSearchResultsList;
			 }
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	 }
	 public StaffDailyAttendance getStaffAttendanceByAttendanceDateAndAccountIdAndCustId(long custId,long accountId,String attendanceDate){
		 try {
				List attendance = this.getAllHqlQuery("from StaffDailyAttendance where accountId=" + accountId+ " and custId=" + custId+" and attendanceDate='"+attendanceDate+" 00:00:00'");
				if (!ObjectFunctions.isNullOrEmpty(attendance)) {
					return (StaffDailyAttendance) attendance.get(0);
				} 
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 public List<ViewUserRoles> getViewUserRolesByRoleNameAndCustId(String roleName, long customerId,String status) {
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewUserRoles where roleName='");
	           queryString.append(roleName);
	           queryString.append("' and custId="+customerId);
	           queryString.append(" and accountExpired='"+status+"'");
	           return (List<ViewUserRoles>) this.getAllHqlQuery(queryString.toString());
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
		
		}
	/*public List getHolidaysByDates(long custId,String startDate,String endDate)
	{
		return this.getAllHqlQuery("from SchoolHolidays where custId="+custId+" and ( holidayDate >='"+startDate+"' and holidayDate <='"+endDate+"' ) order by holidayDate");
	}*/
	public List getEventsByDates(long custId,String startDate,String endDate)
	{
		return this.getAllHqlQuery("from Events where custId="+custId+" and ( startDate >='"+startDate+"' and endDate <='"+endDate+"' ) order by startDate");
	}
	public ClassTeacher getClassTeacherByStudyClassIdAndClassTeacherStatus(long studyClassId, String classTeacherType,long academicYearId) {
		try 
		{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ClassTeacher where studyClassId = ");
	           queryString.append(studyClassId);
	           queryString.append(" and classTeacher='");
	           queryString.append(classTeacherType);
	           queryString.append("'");
	           queryString.append(" and academicYearId=");
	           queryString.append(academicYearId);
	           List classTeacherList = this.getAllHqlQuery(queryString.toString());
	           if(ObjectFunctions.isNotNullOrEmpty(classTeacherList))
	            {
	                return (ClassTeacher) classTeacherList.get(0);
	            }
	            return null;    
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }return null;
	}
	public List<ViewStudentPersonAccountDetails> getParentChildrens(long custId,long parentId,long academicYearId){
		try
		{
		   //return (List<ViewStudentPersonAccountDetails>) this.getAllHqlQuery("from ViewStudentPersonAccountDetails where custId="+custId+" and academicYearId="+academicYearId+" and parentId="+parentId);
			return (List<ViewStudentPersonAccountDetails>) this.getAllHqlQuery("from ViewStudentPersonAccountDetails where custId="+custId+" and academicYearId="+academicYearId+" and accountId="+parentId);
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return null;
		}
	}
	public List<ViewStaffLeaveDetails> getAllFutureLeavesByStatusAndRoleNameAndSupervisorId(long customerId,String leaveStatus,String roleName,long supervisorId,long academicYearId) {
	       try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from ViewStaffLeaveDetails where leaveStatus= '");
	            queryString.append(leaveStatus);
	            queryString.append("' and custId =");
	            queryString.append(customerId);
	            queryString.append(" and roleName ='");
	            queryString.append(roleName);
	            queryString.append("' and academicYearId =");
	            queryString.append(academicYearId);
	            queryString.append(" and leaveSupervisorId =");
	            queryString.append(supervisorId);
	            queryString.append(" and (startDate <= '");
	            queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
	            queryString.append("' or startDate >= '");
	            queryString.append(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
	            queryString.append("') order by startDate desc");
	            List leavesList =  this.getAllHqlQuery(queryString.toString());
		    	  //List leavesList = this.getAllHqlQuery("from ViewStaffLeaveDetails where leaveStatus= '"+leaveStatus +"' and custId = "+customerId+" and roleName ='" + roleName+"'"); 
		           if(!ObjectFunctions.isNullOrEmpty(leavesList))
		           {
		               return (List<ViewStaffLeaveDetails>) leavesList;
		           }
	           }
	       catch (Exception ex) {
	           log.error("getAllFutureLeavesByStatusAndRoleNameAndSupervisorId failed", ex);
	           ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}
	public TransferCertificate saveTCSerialNumber(TransferCertificate transferCertificate) throws DataAccessException{
		try {
			
			this.saveObject(transferCertificate);
			return transferCertificate;
		} catch (HibernateException ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw new URTDataAccessException(ex.getMessage());
		}
	}
	public List<SubCastSettings> getSubcastBySubCastNameAndCastId(long customerId,String subCastName,long castId){
		try
		{
		return (List<SubCastSettings>) this.getAllHqlQuery("from SubCastSettings where custId=" + customerId+ " and subCastName='" + subCastName+ "' and castId=" + castId);
	   }catch (Exception ex) {
		   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	  }	
	public List getSubcastSettingsByCastIdAndCustId(long castId, long customerId){
		try{
			return this.getAllHqlQuery("from SubCastSettings where castId="+castId+" and custId = "+customerId);
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	       return null;
	}

	public List getWorkingDayHolidaysByDates(long custId,String status,String startDate,String endDate)
	{
		return this.getAllHqlQuery("from SchoolHolidays where custId="+custId+" and status=('H' OR  status='WH') and ( holidayDate >='"+startDate+"' and holidayDate <='"+endDate+"' ) order by holidayDate");
	}
	 
	public List<StaffElgibleSubjects> getAllStaffElgibleSubjectsByStudySubjectId(long studySubjectId) {
		try 
		{
			return (List<StaffElgibleSubjects>) this.getAllHqlQuery("from StaffElgibleSubjects where studySubjectId=" + studySubjectId );
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	 public Object[] getAllStudentsByClassNameAndCastName(long custId,long academicYearId,List<CastSettings> castList,long classId,String userName){
		  try{
			  /*select className,section,sum(gender='M'),sum(gender='F'),castId,COUNT(*) total from vw_studentDetails where custId=7  and academicYearId =7 GROUP BY classNameClassId,castId;*/
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select ");
				if(!ObjectFunctions.isNullOrEmpty(castList)){
					queryBuff.append("count(*) GrandTotal,");
					for(CastSettings castSettings:castList){
						if ( castSettings.getCastName().toUpperCase().equals("OTHER") ){
							queryBuff.append("sum(gender='M' and castId in ("+castSettings.getId()+",0))");	
						}else{
							queryBuff.append("sum(gender='M' and castId="+castSettings.getId()+")");	
						}
						 queryBuff.append(",");
					}
					queryBuff.append("sum(gender='M'),");
					for(CastSettings castSettings:castList){
						if ( castSettings.getCastName().toUpperCase().equals("OTHER") ){
							queryBuff.append("sum(gender='F' and castId in ("+castSettings.getId()+",0))");
						}else{
							queryBuff.append("sum(gender='F' and castId="+castSettings.getId()+")");
						}
						queryBuff.append(",");
					}
					queryBuff.append("sum(gender='F')");
				}
				queryBuff.append(" from vw_studentDetails where custId=");
				queryBuff.append(custId);	
				queryBuff.append(" and academicYearId =");
				queryBuff.append(academicYearId);
				if(userName.equalsIgnoreCase("ROLE_HOSTEL")){
					queryBuff.append(" and hostelMode ='H'");
				}
				queryBuff.append(" and classNameClassId=");
				queryBuff.append(classId);
				queryBuff.append(" and status='Y'");
				queryBuff.append(" and description is null");
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return  (Object[])resultList.get(0);
				}
		  }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	}
	 public Object[] getAllStudentsCountByCastWise(long custId,long academicYearId,List<CastSettings> castList,String username,String classNameClassIds){
		 try{
			 StringBuffer queryBuff=new StringBuffer();
			 queryBuff.append("select ");
				if(!ObjectFunctions.isNullOrEmpty(castList)){
					queryBuff.append("count(*) GrandTotal,");
					for(CastSettings castSettings:castList){
						if ( castSettings.getCastName().toUpperCase().equals("OTHER") ){
							queryBuff.append("sum(gender='M' and castId in ("+castSettings.getId()+",0))");	
						}else{
							queryBuff.append("sum(gender='M' and castId="+castSettings.getId()+")");	
						}
						 queryBuff.append(",");
					}
					queryBuff.append("sum(gender='M'),");
					for(CastSettings castSettings:castList){
						if ( castSettings.getCastName().toUpperCase().equals("OTHER") ){
							queryBuff.append("sum(gender='F' and castId in ("+castSettings.getId()+",0))");	
						}else{
							queryBuff.append("sum(gender='F' and castId="+castSettings.getId()+")");
						}
						queryBuff.append(",");
					}
					queryBuff.append("sum(gender='F')");
				}
				queryBuff.append(" from vw_studentDetails where custId=");
				queryBuff.append(custId);	
				if(username.equalsIgnoreCase("ROLE_HOSTEL")){
					queryBuff.append(" and hostelMode ='H'");
				}
				queryBuff.append(" and classNameClassId in");
				queryBuff.append(classNameClassIds);
				queryBuff.append(" and academicYearId =");
				queryBuff.append(academicYearId);
				queryBuff.append(" and status='Y'");
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return  (Object[])resultList.get(0);
				}
		 }catch(Exception ex){
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	 }
	
	 
	public Section saveSectionName(Section sectionName) {
		try {
			
			this.saveObject(sectionName);
			return sectionName;
		} catch (HibernateException ex) {
			ex.printStackTrace();

			throw new URTDataAccessException(ex.getMessage());
		}
	}
	public CastSettings saveComunityName(CastSettings castSettings){
		try {
			
			this.saveObject(castSettings);
			return castSettings;
		} catch (HibernateException ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;

			throw new URTDataAccessException(ex.getMessage());
		}
	}
	 public void removeClassTeachersByStudyClassIdCustId(long classSectionId,long custId){
		 try{
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from classTeacher where studyClassId=");
					sqlString.append(classSectionId);
					sqlString.append(" and custId=");
					sqlString.append(custId);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	public void removeClassSubjectsByClassSectionId(long studyClassId){
		 try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from ClassSubject where studyClassId=");
				sqlString.append(studyClassId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
	 }
	public void removeClassEventsByClassSectionId(long studyClassId){
		 try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from classEvent where classId=");
				sqlString.append(studyClassId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	public void removeExamSchedulesByClassSectionId(long studyClassId){
		 try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from examSchedules where classSectionId=");
				sqlString.append(studyClassId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	public void removeKbanksByClassSectionId(long studyClassId){
		 try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from kBank where classId=");
				sqlString.append(studyClassId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	public AcademicYear getFutureAcademicYearByCustIdAndStatus(long custId, String status){
    	try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from AcademicYear where custId =");
			queryBuff.append(custId);
			queryBuff.append(" and status='");
			queryBuff.append(status);
			queryBuff.append("'");
			List acedamicDetails=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(acedamicDetails)){
				return (AcademicYear)acedamicDetails.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
    }
	public List<Object[]> getAllStaffDetailsByDesignation(long custId,long academicYearId,List<CastSettings> castList,String username){
		  try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select designation,");
				if(!ObjectFunctions.isNullOrEmpty(castList)){
					for(CastSettings castSettings:castList){
						if ( castSettings.getCastName().toUpperCase().equals("OTHER") ){
							queryBuff.append("sum(gender='M' and castId in ("+castSettings.getId()+",0)),sum(gender='F' and castId in ("+castSettings.getId()+",0))");	
						}else{
							queryBuff.append("sum(gender='M' and castId="+castSettings.getId()+"),"+"sum(gender='F' and castId="+castSettings.getId()+")");	
						}
						 queryBuff.append(",");
					}
					queryBuff.append("sum(gender='M'),");
					queryBuff.append("sum(gender='F'),");
				}
				queryBuff.append("count(*) GrandTotal");
				queryBuff.append(" from vw_staffDetails where custId=");
				queryBuff.append(custId);
				if(username.equalsIgnoreCase("ROLE_HOSTEL")){
					queryBuff.append(" and bedId !=0");
				}
				queryBuff.append(" and academicYearId <=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and status='Y'");
				queryBuff.append(" GROUP BY designation");
				return this.getAll(queryBuff.toString());
				
		  }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	}
	public Object[] getAllStaffsCountByCastWise(long custId,long academicYearId,List<CastSettings> castList,String username){
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select ");
			if(!ObjectFunctions.isNullOrEmpty(castList)){
				for(CastSettings castSettings:castList){
					if ( castSettings.getCastName().toUpperCase().equals("OTHER") ){
						long castId = castSettings.getId();
						queryBuff.append("sum(gender='M' and castId in ("+castId+",0)),sum(gender='F' and castId in ("+castId+",0))");	
					}else{
						queryBuff.append("sum(gender='M' and castId="+castSettings.getId()+"),"+"sum(gender='F' and castId="+castSettings.getId()+")");	
					}
					queryBuff.append(",");
				}
				queryBuff.append("sum(gender='M'),");
				queryBuff.append("sum(gender='F'),");
			}
			queryBuff.append("count(*) GrandTotal");
			queryBuff.append(" from vw_staffDetails where custId=");
			queryBuff.append(custId);	
			if(username.equalsIgnoreCase("ROLE_HOSTEL")){
				queryBuff.append(" and bedId !=0");
			}
			queryBuff.append(" and academicYearId <=");
			queryBuff.append(academicYearId);
			queryBuff.append(" and status='Y'");
			List resultList=this.getAll(queryBuff.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return  (Object[])resultList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	public int getMaxOfClassNameSortingOrder(long academicYearId, long customerId) {
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select ifnull(max(sortingOrder),0) from class where custId="+customerId+" and academicYearId="+academicYearId);
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList))
			{
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					BigInteger var= new BigInteger(String.valueOf(resultList.get(0)));
					return var.intValue();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return 0;
	}
	public List<Object[]> getAllClassAndSectionsByCustIdAndAcademicYearId(long custId,long academicYearId){
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select className,section,sectionCapacity,mediumId,id,groupNumber,higherStandard from studyClass where custId =");
			queryBuff.append(custId);	
			queryBuff.append(" and academicYearId =");
			queryBuff.append(academicYearId);
			return (List<Object[]>) this.getAll(queryBuff.toString());
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return null;
	}
    public Object[] getMediumWiseStudentDetailsForEachClass(long custId,long academicYearId, String classNameClassIds,List schoolMediumsListList,String username) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("select ");
			if (!ObjectFunctions.isNullOrEmpty(schoolMediumsListList)) {
				for (Object object : schoolMediumsListList) {
					Medium medium = (Medium) object;
					queryBuff.append("sum(mediumId='"+ medium.getId() + "')");
					queryBuff.append(",");
				}
			}
			queryBuff.append("count(*) Total");
			queryBuff.append(" from vw_studentDetails where custId=");
			queryBuff.append(custId);
			if(username.equalsIgnoreCase("ROLE_HOSTEL")){
				queryBuff.append(" and hostelMode ='H'");
			}
			queryBuff.append(" and classNameClassId in");
			queryBuff.append(classNameClassIds);
			queryBuff.append(" and academicYearId =");
			queryBuff.append(academicYearId);
			queryBuff.append(" and status='Y'");
			List resultList = this.getAll(queryBuff.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return (Object[]) resultList.get(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
    public Date getMaxExamDateByExamTypeIdAndClassSectionId(long examtypeId,long classSectionId,String dateType){
		try{
			StringBuffer queryBuff=new StringBuffer();
			if("MX".equalsIgnoreCase(dateType))
				queryBuff.append("select max(examDate) from examSchedules where examTypeId="+examtypeId+" and classSectionId="+classSectionId);
			if("MI".equalsIgnoreCase(dateType))
				queryBuff.append("select min(examDate) from examSchedules where examTypeId="+examtypeId+" and classSectionId="+classSectionId);
			List resultList=this.getAll(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(resultList))
				return (Date)resultList.get(0);
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException (ex);raygex=null;		
		}
		return null;
	}


    /*public Date getMinExamDateByExamTypeIdAndClassSectionId(long examtypeId,long classSectionId){
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select min(examDate) from examSchedules where examTypeId="+examtypeId+" and classSectionId="+classSectionId);
			List resultList=this.getAll(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(resultList))
				return (Date)resultList.get(0);
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException (ex);raygex=null;			
		}
		return null;
	}*/

	 public Object[] getAllStaffCountByReligionWide(long custId,long academicYearId,List  religionsList, List motherToungsList,long organiZationTypeId,String religionIds,String username){
		 try {
				StringBuffer queryBuff = new StringBuffer();
				StringBuffer queryBuff1 = new StringBuffer();
				queryBuff.append("select ");
				CommonType commonType =null;
				MotherTongue motherTongue =null;
				if (!ObjectFunctions.isNullOrEmpty(religionsList)) {
					for (Object object : religionsList) {
						 commonType = (CommonType) object;
						queryBuff1.append(commonType.getId());
						queryBuff.append("sum(religionId="+ commonType.getId() + ")");
						queryBuff.append(",");
					}
				}
				queryBuff.append("sum(religionId not in"+ religionIds + "),");
				queryBuff.append("count(*) Total,");
				if (!ObjectFunctions.isNullOrEmpty(motherToungsList)) {
					for (Object object : motherToungsList) {
						motherTongue = (MotherTongue) object;
						queryBuff.append("sum(motherToungId="+ motherTongue.getId() + ")");
						queryBuff.append(",");
					}
					queryBuff.append("sum(motherToungId="+0+ "),");
				}
				queryBuff.append("count(*) TotalMotherToung,");
				queryBuff.append("count(*) GrandTotal");
				queryBuff.append(" from vw_staffDetails where custId=");
				queryBuff.append(custId);
				if(username.equalsIgnoreCase("ROLE_HOSTEL")){
					queryBuff.append(" and bedId !=0");
				}
				queryBuff.append(" and academicYearId =");
				queryBuff.append(academicYearId);
				if(organiZationTypeId!=0){
					queryBuff.append(" and organizationTypeId =");
					queryBuff.append(organiZationTypeId);
				}
				queryBuff.append(" and status='Y'");
				List resultList = this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return (Object[]) resultList.get(0);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 public Object[] getAllStudentsCountByReligionWide(long custId,long academicYearId,List religionsList,List motherToungsList,long classId,String religionIds,String username){
		 try {
				StringBuffer queryBuff = new StringBuffer();
				queryBuff.append("select ");
				if (!ObjectFunctions.isNullOrEmpty(religionsList)) {
					for (Object object : religionsList) {
						CommonType commonType = (CommonType) object;
						queryBuff.append("sum(religionId="+ commonType.getId() + ")");
						queryBuff.append(",");
					}
				}
				queryBuff.append("sum(religionId not in"+ religionIds + "),");
				queryBuff.append("count(*) Total,");
				if (!ObjectFunctions.isNullOrEmpty(motherToungsList)) {
					for (Object object : motherToungsList) {
						MotherTongue motherTongue = (MotherTongue) object;
						queryBuff.append("sum(motherToungId='"+ motherTongue.getId() + "')");
						queryBuff.append(",");
					}
					queryBuff.append("sum(motherToungId='"+0+ "'),");
				}
				queryBuff.append("count(*) TotalMotherToung,");
				queryBuff.append("count(*) GrandTotal");
				queryBuff.append(" from vw_studentDetails where custId=");
				queryBuff.append(custId);
				if(username.equalsIgnoreCase("ROLE_HOSTEL")){
					queryBuff.append(" and hostelMode ='H'");
				}
				queryBuff.append(" and academicYearId =");
				queryBuff.append(academicYearId);
				if(classId!=0){
				queryBuff.append(" and classNameClassId =");
				queryBuff.append(classId);
				}
				queryBuff.append(" and status='Y'");
				List resultList = this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return (Object[]) resultList.get(0);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null; 
	 }
	public int getPaidAmountByClassId(String table,long custId,long classId,long academicYearId,long categoryId,SchoolFeeSetting schoolFeeSetting){
	 try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select IFNULL(sum(paymentAmount),0) from "+table+" where custId=");
			queryBuff.append(custId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			queryBuff.append(" and categoryId=");
			queryBuff.append(categoryId);
			queryBuff.append(" and classId=");
			queryBuff.append(classId);
			queryBuff.append(" and feeSettingId=");
			queryBuff.append(schoolFeeSetting.getId());
			queryBuff.append(" and status='Y' and description is null");
			if(Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolFeeSetting.getSettingName())){
				queryBuff.append(" and vehicleAcademicDetailsId!=0 and routeId!=0");
			}
//			log.debug(queryBuff.toString());
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					//if((Double)resultList.get(0)==0.0){
					//	return 0;
					//}
					Double var= ((Double)resultList.get(0));
					return var.intValue();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return 0; 
	}
	public Object[] getAllSyllabusTypesByCustId(long custId){
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("select * from syllabusTypeInfo where customerId=");
			queryString.append(custId);
			//List schoolSyllabusList = this.getAll(queryString.toString());
			List resultList=this.getAll(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return  (Object[])resultList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null; 
	}
	
	
	public StudentAcademicPerformance getStudentAcademicPerformanceByStudentIdAndstudentActivityTypeIdAndExamTypeId(long studId, long studentActivityTypeId, long examTypeId,long custId, long academicYearId) {
		try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from StudentAcademicPerformance where studId =");
			queryBuff.append(studId);
			queryBuff.append(" and studentActivityTypeId=");
			queryBuff.append(studentActivityTypeId);
			queryBuff.append(" and examTypeId=");
			queryBuff.append(examTypeId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			List studentAcademicPerformanceList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(studentAcademicPerformanceList)){
				return (StudentAcademicPerformance)studentAcademicPerformanceList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
		}
		return null;
	}
	
	public void removeSyllabusTypeInfoByCustId(long customerId){
		 try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from syllabusTypeInfo where customerId=");
				sqlString.append(customerId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;	
			}
	 }
	public Address saveAddress(Address address) throws DataAccessException{
		try {
			
			this.saveObject(address);
			return address;
		} catch (HibernateException ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw new URTDataAccessException(ex.getMessage());
		}
	}
	public List getClassFeeTermsByTransportStudentId(String table,long studentId,long custId,long academicYearId,long classId,long categoryId){
			try{
				return this.getAllHqlQuery("from "+table+" where custId ="+custId+" and academicYearId="+academicYearId+" and classId="+classId+" and studentId="+studentId+" and transportCategoryId="+categoryId+" group by feeId order by fromDate, termName, feeId");
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	
	
	public long getAdmissionReceiptNumberByCustId(String table,long custId,long academicYearId) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("select max(receiptNumber) from "+table+" where custId=");
			queryBuff.append(custId);
			if(academicYearId != 0){
				queryBuff.append(" and academicYearId=").append(academicYearId);
			}
			List resultList = this.getAll(queryBuff.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				if (!ObjectFunctions.isNullOrEmpty(resultList.get(0))) {
					BigInteger var = ((BigInteger) resultList.get(0));
					return var.longValue();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return 0;
	}
	
	 public Student getStudentByCustIdAndStudentIdAndTransportStatus(long studentId,long custId,String status,String transportStatus,long academicYearId){
			 try{
		    		StringBuffer queryBuff=new StringBuffer();
					queryBuff.append("from Student where id=");
					queryBuff.append(studentId);
					queryBuff.append(" and academicYearId=");
					queryBuff.append(academicYearId);
					queryBuff.append(" and custId=");
					queryBuff.append(custId);
					queryBuff.append(" and status='");
					queryBuff.append(status);
					queryBuff.append("'");
					queryBuff.append(" and transportMode='");
					queryBuff.append(transportStatus);
					queryBuff.append("'");
					List students=this.getAllHqlQuery(queryBuff.toString());
					if(ObjectFunctions.isNotNullOrEmpty(students)){
						return (Student)students.get(0);
					}
				}
				catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
				}
				return null;
		 }
	public List<AbstractEntityPersister> getAllTableNames(){
		Map m=getSessionFactory().getAllClassMetadata();
		Collection c = m.values();
		if(!ObjectFunctions.isNullOrEmpty(c))
			return new ArrayList(c);
	    return null;
	}
	public List<Object[]> getAllUsersByCretedBy(String table){
			try 
			{
				List<Object[]> resultList=new ArrayList<Object[]>();
				List tempList=null;
				tempList = this.getAll("select fullName,accountId from vw_userRoles where fullName in(select distinct(createdBy) from "+table+")");
				if(ObjectFunctions.isNotNullOrEmpty(tempList))
					resultList.addAll(tempList);
				tempList = this.getAll("select formatedFullName,accountId from vw_userRoles where formatedFullName in(select distinct(createdBy) from "+table+")");
				if(ObjectFunctions.isNotNullOrEmpty(tempList))
					resultList.addAll(tempList);
				return resultList;
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
			return null;
		}
	public void updateCreatedByRecords(String table,String accountId,String fullName){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update "+table+" set createdById="); 
	           sqlString.append(accountId);
	           sqlString.append(" where createdBy='");
	           sqlString.append(fullName);
	           sqlString.append("'");
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public List<Object[]> getAllUsersByUpdatedBy(String table){
		try 
		{
			List<Object[]> resultList=new ArrayList<Object[]>();
			List tempList=null;
			tempList =  this.getAll("select fullName,accountId from vw_userRoles where fullName in(select distinct(lastUpdatedBy) from "+table+")");
			if(ObjectFunctions.isNotNullOrEmpty(tempList))
				resultList.addAll(tempList);
			tempList =  this.getAll("select formatedFullName,accountId from vw_userRoles where formatedFullName in(select distinct(lastUpdatedBy) from "+table+")");
			if(ObjectFunctions.isNotNullOrEmpty(tempList))
				resultList.addAll(tempList);
			return resultList;
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	public void updateLastUpdatedByRecords(String table,String accountId,String fullName){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update "+table+" set lastUpdatedById="); 
	           sqlString.append(accountId);
	           sqlString.append(" where lastUpdatedBy='");
	           sqlString.append(fullName);
	           sqlString.append("'");
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public void removeLastUpdatedByAndCreatedByRecords(String table){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("alter table  "+table+" drop column createdBy,drop column lastUpdatedBy"); 
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}

	public List getAllStudentFeeDefaultersByCustId(String queryString,long customerId, long academicYearId, long termId, String today,String transportmode) {// ,String paymentStatus
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select studentId from "+ queryString + " where custId=" + customerId);
			if (!StringFunctions.isNullOrEmpty(today)) {
				queryStr.append(" and paymentDate=");
				queryStr.append("'" + today + "'");
			}
			queryStr.append(" and academicYearId=");
			queryStr.append(academicYearId);
			queryStr.append(" and schoolTermId=");
			queryStr.append(termId);
			if (StringFunctions.isNotNullOrEmpty(transportmode)) {
				queryStr.append(" and transportMode='");
				queryStr.append(transportmode + "'");
				queryStr.append(" and vehicleAcademicDetailsId !=0");
			}
			queryStr.append(" and description is null group by id");//
			List resultList = this.getAll(queryStr.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public Staff getStaffByCustIdAndStaffIdAndStatus(long staffId,long custId,String status,long academicYearId){
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from Staff where id=");
			queryBuff.append(staffId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and status='");
			queryBuff.append(status);
			queryBuff.append("'");
			List staffs=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(staffs)){
				return (Staff)staffs.get(0);
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getAllVisitorsByBetweenDated(long custId, long academicYearId,String inDate, String outDate) {
		try {
			 StringBuffer queryBuff=new StringBuffer();
			    queryBuff.append("from Visitors where custId= ");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and inDate >='");
				queryBuff.append(outDate);
				queryBuff.append(" 00:00:00'");
				queryBuff.append(" and inDate <='");
				queryBuff.append(inDate);
				queryBuff.append(" 00:00:00' order by outDate desc");
				//queryBuff.append(" 00:00:00' order by ");
				List resultList=this.getAllHqlQuery(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				} 
		} catch (Exception ex) { 
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/*public List getAllViewStaffStudentVehicleTripDetailsByVehicleId(String queryString,long custId,long academicYearId,String vehicleIds){
		try 
		{
			StringBuffer queryString1=new StringBuffer();
			queryString1.append("select vehicleType,vehicleNumber,driverFirstName,driverLastName,driverMobileNumber,helperFirstName,helperLastName,helperMobileNumber,boardingPointName,boardingPointStatTime,routePointEndTime,firstName,lastName,mobileNumber,rollNumber,className,section,parentEmail from "+ queryString + " where custId=");
			queryString1.append(custId);
			queryString1.append(" and academicYearId=");
			queryString1.append(academicYearId);
			queryString1.append(" and vehicleId in ");
			queryString1.append(vehicleIds);
			queryString1.append(" ");
	         return this.getAll(queryString1.toString());  
           
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}*/
	public List getClassNameIdsFromStudyClassIds(String studyClassIds){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select classNameClassId from studyClass where id in");
           queryString.append(studyClassIds);
           return this.getAll(queryString.toString());  
           
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}
	/*public List getExamTypeIdsByClassIds(long classId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select examTypeId from classExamTypes where classNameId =");
           queryString.append(classId);
           return this.getAll(queryString.toString());  
           
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
		return null;
	}*/
	public void updateCourseCompletedStudents(long classId,String failureStudIds,long userId){
		  try{
			  	StringBuffer sqlString = new StringBuffer();
				sqlString.append("update Account set accountExpired ='Y', accountEnabled='N'"); 
		        sqlString.append(", lastUpdatedDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
		        sqlString.append(", lastAccessDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
		        sqlString.append(", lastUpdatedById="+userId);
		        sqlString.append(" where id in (");
		        sqlString.append("select accountId from student where classSectionId=");
		        sqlString.append(classId);
		        sqlString.append(" and status='Y'");
		        if(StringFunctions.isNotNullOrEmpty(failureStudIds))
		        	sqlString.append(" and id not in "+failureStudIds);
		        sqlString.append(" )");
				Query qry =getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
				
				sqlString = new StringBuffer();
				sqlString.append("update student set status ='N'"); 
		        sqlString.append(", lastUpdatedDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
		        sqlString.append(", lastAccessDate='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+"'");
		        sqlString.append(", lastUpdatedById="+userId);
		        sqlString.append(" where classSectionId=");
		        sqlString.append(classId);
		        sqlString.append(" and status='Y'");
		        if(StringFunctions.isNotNullOrEmpty(failureStudIds))
		        	sqlString.append(" and id not in "+failureStudIds);
				qry = getSession().createSQLQuery(sqlString.toString());
				log.debug(qry);
				row = qry.executeUpdate();
					
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}
	public void updateStudentRollNumberByClassSectionId(long classSectionId){
		  try{
				Query qry = getSession().createSQLQuery("CALL UpdateStudentRollNOs(:classSectionId)").addEntity(Student.class).setParameter("classSectionId", classSectionId);
			log.debug(qry);
					int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}
	public void updateSubjectsOrder(long subjectId,long sortingOrder){
		  try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update studySubject set sortingOrder ="); 
	           sqlString.append(sortingOrder);
	           sqlString.append(" where id=");
	           sqlString.append(subjectId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	
	public List getAllStudentInvoiceFeeDetailsByVehicleIdAndCustId(String queryString,long customerId, long academicYearId, long termId, String vehicleAcademicId,String status) {// ,String paymentStatus
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select studentId,invoiceNumber from "+ queryString + " where custId=" + customerId);
			if (!ObjectFunctions.isNullOrEmpty(vehicleAcademicId)) {
				queryStr.append(" and vehicleAcademicDetailsId in ");
				queryStr.append(vehicleAcademicId);
			}
			queryStr.append(" and academicYearId=");
			queryStr.append(academicYearId);
			if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryStr.append(" and hostelTermId=");
			} else {
				queryStr.append(" and schoolTermId=");
			}
			queryStr.append(termId);
			queryStr.append(" and status='");
			queryStr.append(status);
			queryStr.append("'");
			if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryStr.append(" and bedId !=0 ");
			}
			queryStr.append(" group by studentId,invoiceNumber");//
			List resultList = this.getAll(queryStr.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	//Commented by seshu on Jan20,2014
	/*public List getClassFeeTermsByStudentIdAndStatusAndVehicleId(String queryString,long studentId, long custId, long academicYearId, long classId,String vehicleId, String feeIds, long invoiceNumber) {
		try {
			StringBuffer queryBuff = new StringBuffer();
			 if (("vw_studentHostelFeePaymentDetails").equalsIgnoreCase(queryString)) {
	        	   queryBuff.append("select sum(paymentAmount),paymentDate,hostelStudentPaymentId,rollNumber,firstName,lastName,paymentType,hostelTermName,hostelFeeType,ddNumber,chequeNumber,invoiceNumber,sum(discountAmount) from "+queryString+" where studentId=");
				}else{
					queryBuff.append("select sum(paymentAmount),paymentDate,studentPaymentId,rollNumber,firstName,lastName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber from "+queryString+" where studentId=");
				}
			queryBuff.append(studentId);
			queryBuff.append(" and classId=");
			queryBuff.append(classId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			if (("vw_studentHostelFeePaymentDetails").equalsIgnoreCase(queryString)) {
				queryBuff.append(" and hostelFeeId in ");
			}else{
				queryBuff.append(" and feeId in ");
			}
			queryBuff.append(feeIds);
			if (("vw_studentFeePaymentDetails").equalsIgnoreCase(queryString)) {
				queryBuff.append(" and deleteStatus='Y'");
			}
			if (("vw_studentTransportFeePaymentDetails").equalsIgnoreCase(queryString)) {
				queryBuff.append(" and deleteStatus='Y'");
			}
			if (("vw_studentHostelFeePaymentDetails").equalsIgnoreCase(queryString)) {
				queryBuff.append(" and deleteStatus='Y'");
			}
			queryBuff.append(" and vehicleId in ");
			queryBuff.append(vehicleId);
			queryBuff.append(" and invoiceNumber=");
			queryBuff.append(invoiceNumber);
			queryBuff.append(" group by studentId");
			List resultList = this.getAll(queryBuff.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}*/
	/*public List getAllVehicleWiseStudentFeeDefaultersByCustId(String queryString,long customerId, long academicYearId, long termId,  String vehicleId,String status) {// ,String paymentStatus
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select studentId,invoiceNumber from "+ queryString + " where custId=" + customerId);
			if (!StringFunctions.isNullOrEmpty(vehicleId)) {
				queryStr.append(" and vehicleId in ");
				queryStr.append(vehicleId);
			}
			queryStr.append(" and academicYearId=");
			queryStr.append(academicYearId);
			if (("vw_studentHostelFeePaymentDetails").equalsIgnoreCase(queryString)) {
				queryStr.append(" and hostelTermId=");
			} else {
				queryStr.append(" and schoolTermId=");
			}
			queryStr.append(termId);
			queryStr.append(" and status='");
			queryStr.append(status);
			queryStr.append("'");
			if (("vw_studentHostelFeePaymentDetails").equalsIgnoreCase(queryString)) {
				queryStr.append(" and bedId !=0 ");
			}
			queryStr.append(" group by studentId");//
			List resultList = this.getAll(queryStr.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return resultList;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}*/
	/*public long getStudentPaidAmountByVehicleIdAndClassId(String queryString,long studentId,long classId,long custId,long academicYearId,long termId,String vehicleId){
		 try{
			 StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(paymentAmount) from "+queryString+" where id=");
				queryBuff.append(studentId);	
				queryBuff.append(" and classId=");
				queryBuff.append(classId);
				queryBuff.append(" and vehicleId in ");
				queryBuff.append(vehicleId);
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and schoolTermId=");
				queryBuff.append(termId);
				if (("vw_studentFeePaymentDetails").equalsIgnoreCase(queryString)) {
					queryBuff.append(" and deleteStatus='Y'");
				}
				if (("vw_studentTransportFeePaymentDetails").equalsIgnoreCase(queryString)) {
					queryBuff.append(" and deleteStatus='Y'");
				}
				//queryBuff.append(" and paymentStatus='");
				//queryBuff.append(status);
				//queryBuff.append("' ");
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigDecimal var= ((BigDecimal)resultList.get(0));
						return var.longValue();
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return 0; 
	 }*/
	public void removeExamScheduleMarks(long classScctionId,long examTypeId,long academicYearId,long custId) {
		 try{
			 StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from examSchedules where classSectionId=");
				sqlString.append(classScctionId);
				sqlString.append(" and examTypeId=");
				sqlString.append(examTypeId);
				sqlString.append(" and academicYearId=");
				sqlString.append(academicYearId);
				sqlString.append(" and custId=");
				sqlString.append(custId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
	     }
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
	}
	public void removeStudentMarksByClasssSectionId(long classScctionId,long examTypeId,long academicYearId,long custId) {
		 try{
			 
			 StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("delete from studentMarks where examScheduleId in (select scheduleId from vw_classExamDetails where classSectionId="+classScctionId+" and eid="+examTypeId+" and academicYearId="+academicYearId+" and custId="+custId+")");
				Query qry = getSession().createSQLQuery(queryBuff.toString());
				int row = qry.executeUpdate();
				log.debug(row);
	     }
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
	}
	 public SMSServiceProviders saveSMSUrl(SMSServiceProviders smsProvider){
		try {
			
			this.saveObject(smsProvider);
			return smsProvider;
		} catch (HibernateException ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;

			throw new URTDataAccessException(ex.getMessage());
		}
	}
	public void updateRemainingSMSProviderstoInactiveState(long providerId) {
	       try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update smsServiceProviders set activeUrl='N'"); 
	           sqlString.append(" where id !=");
	           sqlString.append(providerId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	 public List<Building> checkBuildingId(String buildingName,long custId,long academicYearId) {
		 if (StringFunctions.isNotNullOrEmpty(buildingName)) {
			 // className=className.replaceAll("'", "''");
			 return (List<Building>) this.getAllHqlQuery("from Building where buildingName like '" + buildingName + "' and custId="+custId+" and academicYearId="+academicYearId);
		 }
		 return null;
	 }
	
	public int getMaxPeriodsByClassSectionIdAndDaydId(String classSectionId, String dayId,String periodType) {
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select max(periodName) from timeTable where classSectionId="+classSectionId+" and dayId="+dayId+" and periodType='"+periodType+"'");
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList))
			{
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					BigInteger var= new BigInteger(String.valueOf(resultList.get(0)));
					return var.intValue();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return 0;
	}
	public List<ViewClassSubjectsSettings> getAllClassWiseSubjects(String clause){
		try{
			List<Object[]> classWiseSubjects=null;
			ViewClassSubjectsSettings classSubject=null;
			classWiseSubjects=this.getAll("select classSectionId,academicYearId,className,section,subjectName,custId,subjectId,classId,subjectNumber,sortingOrder,periodsPerWeek,subjectPriority,continuousPeriodsCount,classSubjectSettingId,classSortingOrder from vw_classSubjectsSettings where "+clause);
			List<ViewClassSubjectsSettings> retList = new ArrayList();
			if(ObjectFunctions.isNotNullOrEmpty(classWiseSubjects)){
				for(Object[] object : classWiseSubjects)
	    		{
				  if(!ObjectFunctions.isNullOrEmpty(object)){
					  classSubject=new ViewClassSubjectsSettings();
					  classSubject.setClassSectionId(Long.valueOf(object[0].toString()));
					  classSubject.setAcademicYearId(Long.valueOf(object[1].toString()));
					  classSubject.setClassName(object[2].toString());
					  classSubject.setSection(object[3].toString());
					  classSubject.setSubjectName(object[4].toString());
					  classSubject.setCustId(Long.valueOf(object[5].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[6]))
						  classSubject.setSubjectId(0);
					  else
						  classSubject.setSubjectId(Long.valueOf(object[6].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[7]))
						  classSubject.setClassId(0);
					  else
						  classSubject.setClassId(Long.valueOf(object[7].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[8]))
						  classSubject.setSubjectNumber("");
					  else
						  classSubject.setSubjectNumber(object[8].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[9]))
						  classSubject.setSortingOrder(0);
					  else
						  classSubject.setSortingOrder(Integer.valueOf(object[9].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[10]))
						  classSubject.setPeriodsPerWeek(0);
					  else
						  classSubject.setPeriodsPerWeek(Integer.valueOf(object[10].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[11]))
						  classSubject.setSubjectPriority(0);
					  else
						  classSubject.setSubjectPriority(Integer.valueOf(object[11].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[12]))
						  classSubject.setContinuousPeriodsCount(0);
					  else
						  classSubject.setContinuousPeriodsCount(Integer.valueOf(object[12].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[13]))
						  classSubject.setClassSubjectSettingId(0);
					  else
						  classSubject.setClassSubjectSettingId(Integer.valueOf(object[13].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[14]))
						  classSubject.setClassSortingOrder(0);
					  else
						  classSubject.setClassSortingOrder(Integer.valueOf(object[14].toString()));
					  retList.add(classSubject);
					  classSubject=null;
				  }
				  object = null;
	    		}
				classWiseSubjects = null;
				return (List<ViewClassSubjectsSettings>) retList;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public void updateTimeTableTeacherIdandSubjectIdsByAcademicYearId(long academicYearId) {
	       try{
	    	   int row = getSession().createSQLQuery("delete from staffTimeTablePeriods where timeTableId in (select id from timeTable where academicYearId="+academicYearId+")").executeUpdate();
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update timeTable set subjectId = null, combinedPeriod='N', prioritiesBasedPeriod='N'");
	           sqlString.append(" where academicYearId=");
	           sqlString.append(academicYearId);
	           row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            log.debug("The no of rows Updated:"+ row);
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public List<ViewStudyClassSubjects> getAllStudyClassSubjects(String clause){
		try{
			List<Object[]> classWiseSubjects=null;
			ViewStudyClassSubjects classSubject=null;
			classWiseSubjects=this.getAll("select classId,studyClassId,className,section,custId,academicYearId,subjectId,subjectName,subjectNumber,sortingOrder,classSortingOrder from vw_studyClassSubjectDetails where "+clause);
			List<ViewStudyClassSubjects> retList = new ArrayList();
			if(ObjectFunctions.isNotNullOrEmpty(classWiseSubjects)){
				for(Object[] object : classWiseSubjects)
	    		{
				  if(!ObjectFunctions.isNullOrEmpty(object)){
					  classSubject=new ViewStudyClassSubjects();
					  if(!ObjectFunctions.isNullOrEmpty(object[0]))
						  classSubject.setClassId(Long.valueOf(object[0].toString()));
					  else
						  classSubject.setClassId(0l);
					  if(!ObjectFunctions.isNullOrEmpty(object[1]))
						  classSubject.setStudyClassId(Long.valueOf(object[1].toString()));
					  else
						  classSubject.setStudyClassId(0l);
					  if(!ObjectFunctions.isNullOrEmpty(object[2]))
						  classSubject.setClassName(object[2].toString());
					  else
						  classSubject.setClassName("");
					  if(!ObjectFunctions.isNullOrEmpty(object[3]))
						  classSubject.setSection(object[3].toString());
					  else
						  classSubject.setSection("");
					  if(!ObjectFunctions.isNullOrEmpty(object[4]))
						  classSubject.setCustId(Long.valueOf(object[4].toString()));
					  else
						  classSubject.setCustId(0l);  
					  if(!ObjectFunctions.isNullOrEmpty(object[5]))
						  classSubject.setAcademicYearId(Long.valueOf(object[5].toString()));
					  else
						  classSubject.setAcademicYearId(0l);
					  if(!ObjectFunctions.isNullOrEmpty(object[6]))
						  classSubject.setSubjectId(Long.valueOf(object[6].toString()));
					  else
						  classSubject.setSubjectId(0l);
					  if(!ObjectFunctions.isNullOrEmpty(object[7]))
						  classSubject.setSubjectName(object[7].toString());
					  else
						  classSubject.setSubjectName("");
					  if(!ObjectFunctions.isNullOrEmpty(object[8]))
						  classSubject.setSubjectNumber(object[8].toString());
					  else
						  classSubject.setSubjectNumber("");
					  if(!ObjectFunctions.isNullOrEmpty(object[9]))
						  classSubject.setSortingOrder(Long.valueOf(object[9].toString()));
					  else
						  classSubject.setSortingOrder(0);
					  if(!ObjectFunctions.isNullOrEmpty(object[10]))
						  classSubject.setClassSortingOrder(Long.valueOf(object[10].toString()));
					  else
						  classSubject.setClassSortingOrder(0);
					  retList.add(classSubject);
					  classSubject=null;
					  object = null;
				  }
	    		}
				classWiseSubjects = null;
				if(!ObjectFunctions.isNullOrEmpty(retList))
					Collections.sort(retList);
				return (List<ViewStudyClassSubjects>) retList;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public void updateClassSubjectsPeriodsCount(long classSectionId){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update classSubjectsSettings set periodsPerWeek = 0"); 
	           sqlString.append(" where classSectionId=");
	           sqlString.append(classSectionId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public void updateClassTeacherHandlePeriodsCount(long classSectionId){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update classTeacher set periodsCount = 0"); 
	           sqlString.append(" where  studyClassId=");
	           sqlString.append(classSectionId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public void updateClassStatus(long custId,long academicYearId){
		  try{
			  StringBuffer sqlString = new StringBuffer();
			  sqlString.append("update class set admissionsOpen ='N',extendableClassCapacity=0 where academicYearId="+academicYearId);
			  Query qry = getSession().createSQLQuery(sqlString.toString());
			  int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}
	public List<ViewStudentClassDetails> getAllStudentDetailsByGender(long custId,long academicYearId,long classSectionId){
		StringBuffer queryString = new StringBuffer();
	      queryString.append("from ViewStudentClassDetails where custId=");
	      queryString.append(custId);
	      queryString.append(" and academicYearId=");
	      queryString.append(academicYearId);
	      queryString.append(" and classSectionId=");
	      queryString.append(classSectionId);
	      queryString.append(" and studDiscontinueDesc is null  order by gender desc,firstName");
	     // queryString.append(" and status='Y' order by gender desc,firstName");
	      List classExamScheduleList = this.getAllHqlQuery(queryString.toString());
	      if (ObjectFunctions.isNullOrEmpty(classExamScheduleList)) {
	          return null;
	      } else {
	          return (List<ViewStudentClassDetails>) classExamScheduleList;
	      }
		
	}
	public OverAllGrades getOverAllGradeByName(String gradName, long academicYearId){
		 try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from OverAllGrades where gradeName='");
	           queryString.append(gradName);
	           queryString.append("'");
	           queryString.append(" and academicYearId="+academicYearId);
	           List SchoolGradesList = this.getAllHqlQuery(queryString.toString());
	           if(!ObjectFunctions.isNullOrEmpty(SchoolGradesList))
	           {
	               return (OverAllGrades)SchoolGradesList.get(0);
	           }
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }return null;
	 }
	public void updateExamTypesOrder(long examTypeId,long sortingOrder){
		  try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update examTypes set sortingOrder ="); 
	           sqlString.append(sortingOrder);
	           sqlString.append(" where id=");
	           sqlString.append(examTypeId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public void updateSubTypesOrder(long subTypeId,long sortingOrder){
		  try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update subType set sortingOrder ="); 
	           sqlString.append(sortingOrder);
	           sqlString.append(" where id=");
	           sqlString.append(subTypeId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public void updateTimeTableDetailsByClassSectionIdAndSubjectIds(long classSectionId,String subjectIds){
		try{
		 StringBuffer sqlString=new StringBuffer();
         sqlString.append("update timeTable set subjectId = null, combinedPeriod='N', prioritiesBasedPeriod='N'");
         sqlString.append(" where classSectionId=");
         sqlString.append(classSectionId);
         sqlString.append(" and subjectId in "+subjectIds);
         int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
          log.debug("The no of rows Updated:"+ row);
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			// TODO: handle exception
		}
	}
	public void updateAdmissionSettingsStatus(long admissionSettingId,long custId){
		try{
			 StringBuffer sqlString=new StringBuffer();
	         sqlString.append("update admissionSettings set status = 'N' ");
	         sqlString.append(" where custId=");
	         sqlString.append(custId);
	         sqlString.append(" and id!="+admissionSettingId);
	         int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
//	         log.debug(sqlString.toString());
	          log.debug("No of admissionSettings status updated count :"+ row);
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			// TODO: handle exception
		}
	}
	public void removeClassSubjectsSettingsByClassSectionId(long studyClassId){
		 try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from classSubjectsSettings where classSectionId="+studyClassId);
				//sqlString.append(studyClassId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
				log.debug("No.f classSubjectsSettings deleted :"+row);
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	public void removeTimeTablePeriodsByClassSectionId(long studyClassId){
		 try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from timeTable where classSectionId="+studyClassId);
				//sqlString.append(studyClassId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
				log.debug("No of timeTable records deleted : "+row);
		 	}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }
	public AdmissionSettings saveAdmissionSettings(AdmissionSettings admissionSettings) throws DataAccessException{
		try {
			
			this.saveObject(admissionSettings);
			return admissionSettings;
		} catch (HibernateException ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			throw new URTDataAccessException(ex.getMessage());
		}
	}
	public void updateClassAdmissionStatusToActive(String classIds,long academicYearId){
		try{
			  StringBuffer sqlString = new StringBuffer();
			  sqlString.append("update class set admissionsOpen ='Y' where academicYearId="+academicYearId+" and id in"+classIds);
			  Query qry = getSession().createSQLQuery(sqlString.toString());
			  int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
	}
	public void generateShortListApplications(long custId,long academicYearId,String applicationId){
		try{
		  StringBuffer sqlString = new StringBuffer();
		  sqlString.append("update onlineApplicationDetails set status ='S' where custId="+ custId+ " and academicYearId="+academicYearId+ " and id in"+applicationId);
		 /* if(entranceExamPassMarks !=0 ){
			  sqlString.append(" and entranceMarks>="+ entranceExamPassMarks+ " and entranceMarks is not null");
		  }
		  sqlString.append(" and status='P' order by entranceMarks DESC");*/
		  //log.debug(sqlString.toString());
		  Query qry = getSession().createSQLQuery(sqlString.toString());
		  int row = qry.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
	}
	public void generateRejectedListApplications(long custId,long academicYearId,String applicationId){
		try{
		  StringBuffer sqlString = new StringBuffer();
		  sqlString.append("update onlineApplicationDetails set status ='R' where custId="+ custId+ " and academicYearId="+academicYearId+ " and id in"+ applicationId);
		 /* if(entranceExamPassMarks !=0 ){
			  sqlString.append(" and entranceMarks<"+ entranceExamPassMarks+ " and entranceMarks is not null");
		  }
		  sqlString.append(" and status='P' order by entranceMarks DESC");*/
		  //log.debug(sqlString.toString());
		  Query qry = getSession().createSQLQuery(sqlString.toString());
		  int row = qry.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
	}
	public SchoolHolidays getSchoolHolidayByDate(long custId,String selectedDate)
	{
		try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from SchoolHolidays where custId ="+custId+"  and holidayDate ='" + selectedDate + "'");
			List holidayList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(holidayList)){
				return (SchoolHolidays)holidayList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	
	public void updateStudentDailyAttendance(long studentId,String leaveRequest,String attendanceDate){
		try{
			  StringBuffer sqlString = new StringBuffer();
			  String present= "";
			  if("H".equalsIgnoreCase(leaveRequest))
			  {
				  present = "";
			  }
			  else
			  {
				  present ="N";
			  }
			  sqlString.append("update studentDailyAttendance set leaveRequest ='"+ leaveRequest +"' , present='"+ present  +"'  where studentId="+studentId +" and attendanceDate='"+ attendanceDate +"'" );
			  Query qry = getSession().createSQLQuery(sqlString.toString());
			  int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
	}
	
	public void updateStudentTransport(long studentId,Long vehicleAcademicDetailId,Long boardingPointId){
		try{
			  StringBuffer sqlString = new StringBuffer();
			  if(ObjectFunctions.isNullOrEmpty(vehicleAcademicDetailId) || ObjectFunctions.isNullOrEmpty(boardingPointId))
				  sqlString.append("update student set vehicleAcademicDetailsId =null , boardingPointId=null,transportMode=null  where id="+studentId);
			  else
				  sqlString.append("update student set vehicleAcademicDetailsId ="+ vehicleAcademicDetailId +" , boardingPointId="+ boardingPointId  +",transportMode='T'  where id="+studentId);
			  Query qry = getSession().createSQLQuery(sqlString.toString());
			  int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
	}
	
	public List getStudentObtainedActivitiesGradesByStudIdAndExamTypeIds(long studId,String examTypeIds,long activityId,long classId){
		try{
			StringBuffer sqlString = new StringBuffer("select sap.grade,sat.activityTypeName,ag.gradePoint,sap.description from studentActivityTypes sat ")
			.append("JOIN activityTypeClasses type on (type.activityTypeId = sat.id and type.classId=").append(classId).append(") ")
			.append("JOIN examTypes et on (et.academicYearId = sat.academicYearId) ")
			.append("JOIN student s on (s.academicYearId=sat.academicYearId and s.description is null) ")
			.append("LEFT JOIN studentAcademicPerformance sap on (sap.studentActivityTypeId = sat.id and sap.examTypeId = et.id and sap.studId= s.id) ")
			.append("LEFT JOIN activitiesGrades ag on (ag.academicYearId = sat.academicYearId and ag.gradeName = sap.grade) ")
			.append("where s.id=").append(studId).append(" and et.id in ").append(examTypeIds).append(" and sat.studentActivityId=").append(activityId)
			.append(" order by sat.id,et.sortingOrder");
//			log.debug(sqlString.toString());
			return this.getAll(sqlString.toString());
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			// TODO: handle exception
		}
		return null;
	}
	public void updateClassTeacherStatus(long custId,long academicYearId,String classTeacher,long studyClassId){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update classTeacher set classTeacher='"); 
	           sqlString.append(classTeacher);
	           sqlString.append("' where custId="+custId+" and academicYearId="+academicYearId+" and studyClassId="+studyClassId);
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public List<ViewStudentFeePaidAndNotPaidDetails> getStudentsFeePaidAndNotPaidDetails(String clause){
		try{
			List<Object[]> studentFeePaymentDetailsList=null;
			ViewStudentFeePaidAndNotPaidDetails feePaymentDetails=null;
			StringBuffer query= new StringBuffer();
			query.append("select studId,firstName,lastName,admissionNumber,custId,academicYearId,classId,rollNumber,accountId,studentStatus,studDiscontinueDesc,").
			append("feeId,feeAmount,feeTypeId,schoolTermId,categoryId,paymentStatus,paidAmount,categoryName,feeType,termName,paymentId,registerNumber,").
			append("className,section  from vw_studentFeePaidAndNotPaidDetails where ").append(clause);
			studentFeePaymentDetailsList=getSession().createSQLQuery(query.toString()).list();
			List<ViewStudentFeePaidAndNotPaidDetails> retList = new ArrayList();
			if(ObjectFunctions.isNotNullOrEmpty(studentFeePaymentDetailsList)){
				for(Object[] object : studentFeePaymentDetailsList)
	    		{
				  if(!ObjectFunctions.isNullOrEmpty(object)){
					  feePaymentDetails=new ViewStudentFeePaidAndNotPaidDetails();
					  if(ObjectFunctions.isNullOrEmpty(object[0]))
						  feePaymentDetails.setStudId(0);
					  else
						  feePaymentDetails.setStudId(Long.valueOf(object[0].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[1]))
						  feePaymentDetails.setFirstName("");
					  else
						  feePaymentDetails.setFirstName(object[1].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[2]))
						  feePaymentDetails.setLastName("");
					  else
						  feePaymentDetails.setLastName(object[2].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[3]))
						  feePaymentDetails.setAdmissionNumber("");
					  else
						  feePaymentDetails.setAdmissionNumber(object[3].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[4]))
						  feePaymentDetails.setCustId(0);
					  else
						  feePaymentDetails.setCustId(Long.valueOf(object[4].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[5]))
						  feePaymentDetails.setAcademicYearId(0);
					  else
						  feePaymentDetails.setAcademicYearId(Long.valueOf(object[5].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[6]))
						  feePaymentDetails.setClassId(0);
					  else
						  feePaymentDetails.setClassId(Long.valueOf(object[6].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[7]))
						  feePaymentDetails.setRollNumber("0");
					  else
						  feePaymentDetails.setRollNumber(object[7].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[8]))
						  feePaymentDetails.setAccountId(0);
					  else
						  feePaymentDetails.setAccountId(Long.valueOf(object[8].toString()));
					  if(!ObjectFunctions.isNullOrEmpty(object[9]) && "Y".equalsIgnoreCase(object[9].toString()))
						  feePaymentDetails.setStudentStatus(true);
					  else
						  feePaymentDetails.setStudentStatus(false);
					  if(ObjectFunctions.isNullOrEmpty(object[10]))
						  feePaymentDetails.setStudDiscontinueDesc(null);
					  else
						  feePaymentDetails.setStudDiscontinueDesc(object[10].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[11]))
						  feePaymentDetails.setFeeId(0);
					  else
						  feePaymentDetails.setFeeId(Long.valueOf(object[11].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[12]))
						  feePaymentDetails.setFeeAmount(0);
					  else
						  feePaymentDetails.setFeeAmount(Double.valueOf(object[12].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[13]))
						  feePaymentDetails.setFeeTypeId(0);
					  else
						  feePaymentDetails.setFeeTypeId(Long.valueOf(object[13].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[14]))
						  feePaymentDetails.setSchoolTermId(0);
					  else
						  feePaymentDetails.setSchoolTermId(Long.valueOf(object[14].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[15]))
						  feePaymentDetails.setCategoryId(0);
					  else
						  feePaymentDetails.setCategoryId(Long.valueOf(object[15].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[16]))
						  feePaymentDetails.setPaymentStatus(null);
					  else
						  feePaymentDetails.setPaymentStatus(object[16].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[17]))
						  feePaymentDetails.setPaidAmount(0);
					  else
						  feePaymentDetails.setPaidAmount(Double.valueOf(object[17].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[18]))
						  feePaymentDetails.setCategoryName(null);
					  else
						  feePaymentDetails.setCategoryName(object[18].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[19]))
						  feePaymentDetails.setFeeType(null);
					  else
						  feePaymentDetails.setFeeType(object[19].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[20]))
						  feePaymentDetails.setTermName(null);
					  else
						  feePaymentDetails.setTermName(object[20].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[21]))
						  feePaymentDetails.setPaymentId(0);
					  else
						  feePaymentDetails.setPaymentId(Long.valueOf(object[21].toString()));
					  if(ObjectFunctions.isNullOrEmpty(object[22]))
						  feePaymentDetails.setRegisterNumber(null);
					  else
						  feePaymentDetails.setRegisterNumber(object[22].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[23]))
						  feePaymentDetails.setClassName(null);
					  else
						  feePaymentDetails.setClassName(object[23].toString());
					  if(ObjectFunctions.isNullOrEmpty(object[24]))
						  feePaymentDetails.setSection(null);
					  else
						  feePaymentDetails.setSection(object[24].toString());
					  retList.add(feePaymentDetails);
					  object = null;
					  feePaymentDetails=null;
				  }
	    		}
				//studentFeePaymentDetailsList = null;
				query = null;
				return (List<ViewStudentFeePaidAndNotPaidDetails>) retList;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List geAllSchoolHolidaysListByAcademicYearId(long custId,long academicYearId)
	{
		List<String> schoolHolidaysObjectList = this.getAll("select holidayDate from schoolHolidays where custId='"+custId+"' and academicYearId="+academicYearId);
		List schoolHolidaysList = new ArrayList();
		if(ObjectFunctions.isNotNullOrEmpty(schoolHolidaysObjectList))
		{
			for(String holidayDate : schoolHolidaysObjectList)
    		{
			  if(!ObjectFunctions.isNullOrEmpty(holidayDate))
			  {
					  schoolHolidaysList.add(holidayDate.toString());
			  }
    		}
		}
		return schoolHolidaysList;
	}
	public String getBookSettingsCreatedClassIdsByAcademicYearId(long academicYearId,String type){
		try{
			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append("select group_concat(CONVERT(classId, CHAR)) from tcBookSettingsClasses bsc join tcBookSettings bs on (bsc.tcBookId=bs.id and bs.academicYearId=").
			append(academicYearId);
			sqlQuery.append(" and bs.type='");
			sqlQuery.append(type).append("'").append(")");
			List resultList=this.getAll(sqlQuery.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					return resultList.get(0).toString();
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Customer> findExistCustomer(String keyWord) {
		 try{
			 List customerList=this.getAllHqlQuery("from Customer where custEmail='"+keyWord+"'");
			 if(!ObjectFunctions.isNullOrEmpty(customerList)){
				 return (List<Customer>) customerList;
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	  }
	public List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeId(long studId,long examTypeId,long custId,long academicYearId){
		try 
		{
           StringBuffer queryString = new StringBuffer();
          // queryString.append("from ViewStudentMarks where studId = ");
           queryString.append(" custId = ");
           queryString.append(custId);	
           queryString.append(" and academicYearId = ");
           queryString.append(academicYearId);	
           queryString.append(" and studId = ");
           queryString.append(studId);	
           queryString.append(" and examTypeId= ");
           queryString.append(examTypeId);
           queryString.append(" group by scheduleId");
           
           StringBuffer query = new StringBuffer("select * from vw_studentMarks WHERE ").append(queryString.toString());
   		   return (List<ViewStudentMarks>) getSession().createSQLQuery(query.toString()).addEntity(ViewStudentMarks.class).list();
   		
          // return (List<ViewStudentMarks>) this.getAllHqlQuery(queryString.toString());
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}
	public boolean isUserAsClassTeacher(long accountId,long classSectionId,long academicYearId){
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select cteacher.classTeacher from ClassTeacher cteacher WHERE cteacher.staff.account = ");
			queryString.append(accountId);	
			if(classSectionId !=0 ){
				queryString.append(" and cteacher.studyClass= ");
				queryString.append(classSectionId);
			}
			queryString.append(" and cteacher.academicYear= ");
			queryString.append(academicYearId);
			queryString.append(" and cteacher.classTeacher='Y' ");
			queryString.append(" and cteacher.staff.status='Y' ");
			List classTeachers = this.getAllHqlQuery(queryString.toString());
			if(ObjectFunctions.isNotNullOrEmpty(classTeachers) && !ObjectFunctions.isNullOrEmpty(classTeachers.get(0)))
				return Boolean.valueOf(classTeachers.get(0).toString());
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			// TODO: handle exception
		}
		return false;
	}
	public int getAssignedTimeTableSubjectsSettingsCount(long academicYearId){
		try{
			StringBuffer hqlQuery = new StringBuffer("SELECT COUNT(*) from ClassSubjectsSettings css WHERE css.classSection.academicYear=");
			hqlQuery.append(academicYearId);
			hqlQuery.append(" and css.periodsPerWeek > 0");
			Query query = getSession().createQuery(hqlQuery.toString());
			List results = query.list();
			if(ObjectFunctions.isNotNullOrEmpty(results))
				return  ((Long)results.get(0)).intValue();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return 0;
	}
	public int getPaidAmountByBoardingPointWise(String table,long custId,long academicYearId,long categoryId,long feeSettingId,String trnaportMode,String boardingPointIds){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select IFNULL(sum(paymentAmount),0) from "+table+" where custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and categoryId=");
				queryBuff.append(categoryId);
				queryBuff.append(" and feeSettingId=");
				queryBuff.append(feeSettingId);
				queryBuff.append(" and transportMode=");
				queryBuff.append("'"+trnaportMode+"'");
				queryBuff.append(" and description is null and vehicleAcademicDetailsId!=0 and routeId="+boardingPointIds);
//				log.debug(queryBuff.toString());
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return var.intValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0; 
		}
	public void calculateStudentsSubjectPosition(long classSectionId,long examTypeId){
		  try{
				Query qry = getSession().createSQLQuery("CALL calculateStudentsSubjectPosition(:classSectionId,:examTypeId)").setParameter("classSectionId", classSectionId).setParameter("examTypeId", examTypeId);
				int row = qry.executeUpdate();
				log.debug(row);
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
	}
	public void updateStudentHostel(long studentId,long roomId){
		try{
			  StringBuffer sqlString = new StringBuffer();
			  sqlString.append("update student set roomId='"+ roomId  +"' where id="+studentId);
			  Query qry = getSession().createSQLQuery(sqlString.toString());
			  int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}
	public List<ViewClassSectionTeacher> getAllClassSectionTeachersDetails(String clause){
		StringBuffer query = new StringBuffer("select * from vw_classSectionTeacher WHERE ").append(clause);
		return (List<ViewClassSectionTeacher>) getSession().createSQLQuery(query.toString()).addEntity(ViewClassSectionTeacher.class).list();
    }
	public List<SchoolTerms> getFeeTermsByRemainderDays(long academicYearId){
		StringBuffer query = new StringBuffer("select * from schoolTerms st JOIN customer c on (c.id = st.custId and st.academicYearId=").append(academicYearId)
		.append(") where st.academicYearId=").append(academicYearId).append(" and st.feeSettingId in(select id from schoolFeeSetting where status in(IF(c.transportModuleStatus = 'Y',IF(c.hostelModuleStatus = 'Y',")
		.append("concat('\"',\"'T','H'\",'\"'),'T')").append(",IF(c.hostelModuleStatus = 'Y','H','')),'S')) and ( (DATEDIFF(st.dueDate,CURDATE()) = IFNULL(st.noOfDays,0)) or (CURDATE() = DATE_ADD(st.dueDate1, INTERVAL 0 DAY)) or (CURDATE() = DATE_ADD(st.dueDate2, INTERVAL 0 DAY)))");
		log.debug(query.toString());
		return (List<SchoolTerms>) getSession().createSQLQuery(query.toString()).addEntity(SchoolTerms.class).list();
    }
	public List<ExamSchedules> getExamSchedulesForSendingMobileAlerts(long academicYearId){
		StringBuffer query = new StringBuffer("select * from examSchedules es JOIN examTypes et on (es.examTypeId = et.id and es.academicYearId=").append(academicYearId)
		.append(") WHERE et.noOfDays is not null and (DATEDIFF(es.startDate,CURDATE()) = IFNULL(et.noOfDays,0)) and es.academicYearId=").append(academicYearId).append(" group by es.classSectionId");
		log.debug(query.toString());
		return (List<ExamSchedules>) getSession().createSQLQuery(query.toString()).addEntity(ExamSchedules.class).list();
    }
	public List<ExamSchedules> getAllExamSchedulesForSendingEmailAlerts(long academicYearId,long classSectionId){
		StringBuffer query = new StringBuffer("select * from examSchedules es JOIN examTypes et on (es.examTypeId = et.id and es.academicYearId=").append(academicYearId)
		.append(") WHERE es.classSectionId=").append(classSectionId).append(" and et.noOfDays is not null and (DATEDIFF(es.startDate,CURDATE()) = IFNULL(et.noOfDays,0)) and es.academicYearId=").append(academicYearId);
		log.debug(query.toString());
		return (List<ExamSchedules>) getSession().createSQLQuery(query.toString()).addEntity(ExamSchedules.class).list();
    }
	public List<StudentMarks> getLatestUploadedStudentsMarks(long classSectionId,long academicYearId,int noOfExamTypes){
		try{
			StringBuffer hqlQuery = new StringBuffer("SELECT stMarks FROM StudentMarks as stMarks INNER JOIN stMarks.examSchedule as es WHERE es.classSection=").append(classSectionId)
			.append(" and es.academicYear=").append(academicYearId).append(" and es.examDate is not null group by es.examType order by  stMarks.lastUpdatedDate DESC");
			if(noOfExamTypes > 0)
				hqlQuery.append(" limit ").append(noOfExamTypes);
			return (List<StudentMarks>) getSession().createQuery(hqlQuery.toString()).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<StudentMarks> getLatestUploadedExamTypesByStudentId(long studId,int noOfExamTypes){
		try{
			StringBuffer query  = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule es WHERE marks.student=:studentId and es.examDate is not null GROUP BY es.examType ORDER BY marks.lastUpdatedDate DESC");
			if(noOfExamTypes > 0)
				query.append(" limit ").append(noOfExamTypes);
			return (List<StudentMarks>) getSession().createQuery(query.toString()).setLong("studentId", studId).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 public long getStudentPaidAmountByTermIdAndClassId(long studentId,long classId,long custId,long academicYearId,long termId,String dueDate,long feeSettingId,String checkDueDate){
		 try{
			 StringBuffer queryBuff=new StringBuffer("select SUM(feePayment.feeAmount)-SUM(feePayment.paymentAmount) FROM (select feeAmount,SUM(paymentAmount) as paymentAmount from vw_studentFeePaymentDetails where studentId=")
			 .append(studentId).append(" and classId=").append(classId).append(" and custId=").append(custId).append(" and academicYearId=").append(academicYearId)
			 .append(" and feeSettingId=").append(feeSettingId).append(" and schoolTermId not in(").append(termId);
			 if("D1".equalsIgnoreCase(checkDueDate))
			  {
				 queryBuff.append(") and deleteStatus='N' and paymentStatus='N' and dueDate1 < '");
			  }else if("D2".equalsIgnoreCase(checkDueDate)){
				  queryBuff.append(") and deleteStatus='N' and paymentStatus='N' and dueDate2 < '");
			  }else{
				  queryBuff.append(") and deleteStatus='N' and paymentStatus='N' and dueDate < '");
			  }
			 queryBuff.append(dueDate).append("' group by schoolTermId) feePayment");
			 log.debug(queryBuff.toString());
			 List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					Double var= ((Double)resultList.get(0));
					return var.longValue();
				}
			}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return 0; 
	 }
		public String removeAllAcademicYearTimingsByAcademicYearId(long academicYearId){
			  try{
				  if (!ObjectFunctions.isNullOrEmpty(academicYearId)) {
						StringBuffer sqlString = new StringBuffer();
						sqlString.append("delete from academicYearTimings where academicYearId=");
						sqlString.append(academicYearId);
						Query qry = getSession().createSQLQuery(sqlString.toString());
						int row = qry.executeUpdate();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
				}
				return null;
		}
		public String getStudyCertificateBookSettingsCreatedClassIdsByAcademicYearId(long academicYearId){
			try{
				StringBuffer sqlQuery = new StringBuffer();
				sqlQuery.append("select group_concat(CONVERT(classId, CHAR)) from studyBookSettingsClasses bsc join studyBookSettings bs on (bsc.studyBookId=bs.id and bs.academicYearId=").
				append(academicYearId).append(")");
				List resultList=this.getAll(sqlQuery.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						return resultList.get(0).toString();
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				// TODO: handle exception
			}
			return null;
		}
		public void addStudentRollNumbersForNonAssignedStudentsByClassSectionId(long classSectionId,int lastAssignedRollNumber){
			  try{
				  Query qry = getSession().createSQLQuery("CALL CreateStudentRollNOs(:classSectionId,:rollNumber)").addEntity(Student.class).setParameter("classSectionId", classSectionId).setParameter("rollNumber", lastAssignedRollNumber);
				  log.debug(qry);
				  int row = qry.executeUpdate();
			  }
				catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
				}
		}
		 public List<ViewAssignedVehiclestoRoutesWithBoardingPoints> getAllViewAssignedVehiclestoRoutesWithBoardingPoints(String clause){
			 StringBuffer query = new StringBuffer("SELECT vehicleAcademicId,routeId,name,academicYearId,custId,driverId,helperId,").
			 append("status,vehicleId,routeName,routeStatus,boardingPointName,boardingPointId,noOfSeats from vw_assignedVehiclestoRoutesWithBoardingPoints WHERE ").append(clause);
			 List<Object[]> assignedVehicleToRoutesWithBoardingPnts =  this.getAll(query.toString());
			 List<ViewAssignedVehiclestoRoutesWithBoardingPoints> retList = new ArrayList();
			 ViewAssignedVehiclestoRoutesWithBoardingPoints vehicleAssignedRoute = null;
				if(ObjectFunctions.isNotNullOrEmpty(assignedVehicleToRoutesWithBoardingPnts)){
					for(Object[] object : assignedVehicleToRoutesWithBoardingPnts)
		    		{
						if(!ObjectFunctions.isNullOrEmpty(object)){
							vehicleAssignedRoute =new ViewAssignedVehiclestoRoutesWithBoardingPoints();
							if(ObjectFunctions.isNullOrEmpty(object[0]))
								vehicleAssignedRoute.setVehicleAcademicId(0);
							else
								vehicleAssignedRoute.setVehicleAcademicId(Long.valueOf(object[0].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[1]))
								vehicleAssignedRoute.setRouteId(0);
							else
								vehicleAssignedRoute.setRouteId(Long.valueOf(object[1].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[2]))
								vehicleAssignedRoute.setName(null);
							else
								vehicleAssignedRoute.setName(object[2].toString());
							if(ObjectFunctions.isNullOrEmpty(object[3]))
								vehicleAssignedRoute.setAcademicYearId(0);
							else
								vehicleAssignedRoute.setAcademicYearId(Long.valueOf(object[3].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[4]))
								vehicleAssignedRoute.setCustId(0);
							else
								vehicleAssignedRoute.setCustId(Long.valueOf(object[4].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[5]))
								vehicleAssignedRoute.setDriverId(0);
							else
								vehicleAssignedRoute.setDriverId(Long.valueOf(object[5].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[6]))
								vehicleAssignedRoute.setHelperId(0);
							else
								vehicleAssignedRoute.setHelperId(Long.valueOf(object[6].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[7]))
								vehicleAssignedRoute.setStatus(false);
							else{
								if("Y".equalsIgnoreCase(object[7].toString()))
									vehicleAssignedRoute.setStatus(true);
								else
									vehicleAssignedRoute.setStatus(false);
							}
							if(ObjectFunctions.isNullOrEmpty(object[8]))
								vehicleAssignedRoute.setVehicleId(0);
							else
								vehicleAssignedRoute.setVehicleId(Long.valueOf(object[8].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[9]))
								vehicleAssignedRoute.setRouteName(object[9].toString());
							if("Y".equalsIgnoreCase(object[10].toString()))
								vehicleAssignedRoute.setRouteStatus(true);
							else
								vehicleAssignedRoute.setRouteStatus(false);
							if(!ObjectFunctions.isNullOrEmpty(object[11]))
								vehicleAssignedRoute.setBoardingPointName(object[11].toString());
							if(ObjectFunctions.isNullOrEmpty(object[12]))
								vehicleAssignedRoute.setBoardingPointId(0);
							else
								vehicleAssignedRoute.setBoardingPointId(Long.valueOf(object[12].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[13]))
								vehicleAssignedRoute.setNoOfSeats(0);
							else
								vehicleAssignedRoute.setNoOfSeats(Integer.valueOf(object[13].toString()));
							retList.add(vehicleAssignedRoute);
						}
		    		}
				}
				assignedVehicleToRoutesWithBoardingPnts = null;
				return ( List<ViewAssignedVehiclestoRoutesWithBoardingPoints>) retList;
		 }
		 //Don't Change this query. It will impact to make payment process.
	public List<ViewStudentFeePaymentDetails> getAllStudentNonPaidClassFeePaymentDetails(String tableName,long studentId,long academicYearId,long termId){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				query.append("feeId ");
			else if ("vw_studentTransportFeePaymentDetails".equalsIgnoreCase(tableName)) 
				query.append("transportFeeId as feeId ");
			query.append("  from "+tableName+" where studentId=");
			query.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='N' and paymentStatus='N' group by ");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				query.append("feeId ");
			else if ("vw_studentTransportFeePaymentDetails".equalsIgnoreCase(tableName)) 
				query.append("transportFeeId");
			log.debug(query.toString());
			 return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate").addScalar("feeId",StandardBasicTypes.LONG)
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 //Don't Change this query. It will impact to make payment process. For paying future academic year fee.
	public List<ViewStudentFeePaymentDetails> getStudentNonPaidFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId){
		try{
			StringBuffer query = new StringBuffer("select cf.feeSettingId,cf.settingName,cf.schoolTermId,cf.termName,cf.feeTypeId,cf.feeType,").
			append("cf.feeId,cf.feeAmount,IFNULL(sum(sfp.paymentAmount),0) as paymentAmount,IFNULL(sum(sfp.discountAmount),0)  as discountAmount,sp.paymentDate ").
			append("from vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',").
			append("(s.boardingPointId = cf.routeBoardingPointId and cf.custId = s.custId and cf.academicYearId =").append(academicYearId).append(" and s.transportMode = 'T' and cf.categoryId = s.categoryId),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and s.boardingPointId != 0 and cf.categoryId = s.categoryId)),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and cf.categoryId = s.categoryId)) and cf.classSectionId = ").
			append(classSectionId).append(" and cf.schoolTermId =").append(termId).append(" )").append(" left join class c ON (c.id = cf.classId) left join ").
			append("studentFeePaidDetails sfp ON (sfp.studentId = ").append(studentId).append(" and sfp.feeId = cf.feeId and sfp.custId = s.custId and sfp.deleteStatus = 'N' and sfp.futureFeeStatus = 'Y')").
			append(" left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId =").append(studentId).append("  and sp.academicYearId =").append(academicYearId).append(" )").
			append(" where s.studentId = ").append(studentId).append(" and cf.academicYearId = ").append(academicYearId).append(" and cf.schoolTermId = ").append(termId).append("  and (sp.paymentStatus='N' or sfp.paymentStatus is null) group by cf.feeId");
			/*StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate from vw_studentFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='Y' and paymentStatus='N' group by feeId");*/
			log.debug(query.toString());
			 return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseNonPaidClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId){
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,IF(SUM(IF(paymentStatus ='N', 1 , 0))>0,'N','P') as paymentStatus,dueDate from (select schoolTermId,feeAmount,paymentAmount,discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,paymentStatus,dueDate from vw_studentFeeParticularsPayment where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and deleteStatus='"+Constants.NO_STRING+"' ").append(" and feeSettingId in ").append(feeSettingId).append(" and feeSettingId != ").append(3).append(" order by paymentStatus) feeDetails group by schoolTermId order by feeSettingId,dueDate ASC");
			/*StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,paymentStatus from vw_studentFeePaymentDetails where studentId=").append(studentId)
			.append(" and academicYearId=").append(academicYearId).append(" and feeSettingId in ").append(feeSettingId).append(" group by schoolTermId order by feeSettingId,schoolTermId");*/
			log.debug(query.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName").addScalar("paymentStatus").addScalar("dueDate")
			.setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
		
	   /* payment_SequanceId() AS id,
	    IFNULL(sp.id, 0) as studentPaymentId,IFNULL(sp.paidAmount, 0) as paidAmount,IFNULL(sp.bankId, 0) as bankId,
	    sp.chequeIssuedDate,sp.ddDrawnDate,sp.branchName,sp.lastUpdatedDate,sp.paymentType,sp.ddNumber,sp.chequeNumber,IFNULL(sp.invoiceNumber, 0) as invoiceNumber,
	    IFNULL(sfp.deleteStatus, 'Y') as deleteStatus,sp.paymentDate,IFNULL(sfp.id,0) as paymentId,IFNULL(sfp.futureFeeStatus,'N') AS futureFeeStatus,
	    cf.feeId,cf.custId,cf.feeTypeId,IFNULL(cf.classId,0) as classId,cf.academicYearId,c.sortingOrder,
	    if(((s.transportMode = 'O' and cf.settingName = 'Transport Fee') or (s.transportMode = 'p' and cf.settingName = 'Transport Fee') or (s.hostelMode='D' and cf.settingName = 'Hostel Fee')),'N',cf.feeType) as feeType,
	    cf.fromdate,cf.toDate,cf.settingType,cf.applToNewStuds,cf.dueDate,cf.dueDate2,cf.dueDate1,cf.categoryId,cf.categoryName,
	    cf.routeBoardingPointId,s.id as studentId,IFNULL(s.rollNumber,0) as rollNumber,s.accountId,s.status,s.description,s.transportMode,s.joinedThroughAdmissions,IFNULL(s.vehicleAcademicDetailsId,0)as vehicleAcademicDetailsId,s.hostelMode,
	    s.boardingPointId,cf.className,cf.section,cf.classSectionId,rb.routeId,a.username,a.admissionNumber,p.firstName,p.lastName,p.middleName,p.mobileNumber,
	    p.phoneNumber,p.parentEmail,s.registerNumber,CONCAT(IF(p.firstName IS NULL,'',p.firstName), IF(p.lastName IS NULL || p.lastName  <=> '','',CONCAT(' ',p.lastName))) as fullName
	FROM
	    vw_classFeeDetails cf JOIN student s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',(s.boardingPointId = cf.routeBoardingPointId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and s.transportMode = 'T' and cf.categoryId = s.categoryId),(cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and s.boardingPointId!=0 and cf.categoryId = s.categoryId)),
	    cf.classId = s.classNameClassId and cf.custId=s.custId and cf.academicYearId=s.academicYearId  and IF(s.joinedThroughAdmissions = 'N',(cf.categoryId = s.categoryId and cf.custId=s.custId and cf.academicYearId=s.academicYearId and cf.applToNewStuds = 'N'),cf.categoryId = s.categoryId) ))  and s.classSectionId = cf.ClassSectionId
	    left join class c on (c.id=cf.classId) 
	    left join studentFeePaidDetails sfp ON (s.id = sfp.studentId and cf.feeId = sfp.feeId and s.custId=sfp.custId and sfp.deleteStatus = 'Y')
	    left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId=sfp.custId and sp.studentId=sfp.studentId)
	    left join Account a ON (s.accountId = a.id)
	    LEFT JOIN Person p ON (a.personId = p.id) 
	    left join routeBoardingPoints rb ON (rb.id = s.boardingPointId and rb.custId=s.custId and rb.academicYearId=s.academicYearId);
	*/
	    
	public List<ViewStudentFeePaymentDetails> getStudentFutureAcademicTermsWiseNonPaidClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId){
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,paymentStatus,classId,classSectionId,academicYearId from (").
			append("SELECT cf.schoolTermId,IFNULL(cf.feeAmount, 0) as feeAmount,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.discountAmount, 0) as discountAmount,cf.settingName,cf.termName,cf.feeSettingId,cf.fromMonthName,cf.toMonthName,IFNULL(sfp.paymentStatus, 'N') as paymentStatus,cf.classId,cf.classSectionId,cf.academicYearId ").
			append(" from vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s on (IF(cf.settingName= 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',(s.boardingPointId = cf.routeBoardingPointId and cf.custId=s.custId and cf.academicYearId=").append(academicYearId).append(" and s.transportMode = 'T' and cf.categoryId = s.categoryId),(cf.classId = ").
			append(classId).append(" and cf.custId=s.custId and cf.academicYearId=").append(academicYearId).append(" and s.boardingPointId!=0 and cf.categoryId = s.categoryId)),").
			append("cf.classId = ").append(classId).append(" and cf.custId=s.custId and cf.academicYearId=").append(academicYearId).append(" and cf.categoryId = s.categoryId").append(" ))  and cf.classSectionId = ").append(classSectionId).
			append(" left join class c on (c.id=cf.classId) ").append(" left join vw_studentParticularwiseFeePayments sfp ON (s.studentId = sfp.studentId and cf.feeId = sfp.feeId and s.custId=sfp.custId and sfp.deleteStatus = 'N' and sfp.futureFeeStatus='Y')").
			append(" where s.studentId=").append(studentId).append(" and cf.academicYearId=").append(academicYearId).append(" and cf.feeSettingId in ").append(feeSettingId).append(" order by sfp.paymentStatus) feeDetails group by schoolTermId order by feeSettingId,schoolTermId");
			log.debug(query.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName").addScalar("paymentStatus").addScalar("classId",StandardBasicTypes.LONG).addScalar("classSectionId",StandardBasicTypes.LONG).addScalar("academicYearId",StandardBasicTypes.LONG)
			.setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	
	
	public void updateStudentPartialFeePaymentStatus(long studentId,long feeId, long transportFeeId) {
		try {
			StringBuffer query = new StringBuffer("update studentFeePaidDetails set paymentStatus='P' where studentId=").append(studentId);
			if (feeId > 0)
				query.append(" and feeId=").append(feeId);
			else if (transportFeeId > 0)
				query.append(" and studTransportDetailsId=").append(transportFeeId);
			query.append(" and paymentStatus='N'");
			int row = getSession().createSQLQuery(query.toString()).executeUpdate();
			log.debug("The no of rows Updated:" + row);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	public ViewStudentFeePaymentDetails getStudentNonPaidClassFeePaymentDetails(long studentId,long academicYearId,long termId,long feeTypeId){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate from vw_studentFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='N' and paymentStatus='N' and feeTypeId=").append(feeTypeId).append(" group by feeId");
			 List<ViewStudentFeePaymentDetails> nonPaidFee =  getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
			 if(ObjectFunctions.isNotNullOrEmpty(nonPaidFee)){
				 return nonPaidFee.get(0);
			 }
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewStudentFeePaymentDetails getStudentNonPaidFutureClassFeePaymentDetailsByFeeType(long studentId,long academicYearId,long termId,long feeTypeId,long classId,long classSectionId){
		try{
			StringBuffer query = new StringBuffer("select cf.feeSettingId,cf.settingName,cf.schoolTermId,cf.termName,cf.feeTypeId,cf.feeType,").
			append("cf.feeId,cf.feeAmount,IFNULL(sum(sfp.paymentAmount),0) as paymentAmount,IFNULL(sum(sfp.discountAmount),0)  as discountAmount,sp.paymentDate ").
			append("from vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',").
			append("(s.boardingPointId = cf.routeBoardingPointId and cf.custId = s.custId and cf.academicYearId =").append(academicYearId).append(" and s.transportMode = 'T' and cf.categoryId = s.categoryId),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and s.boardingPointId != 0 and cf.categoryId = s.categoryId)),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and cf.categoryId = s.categoryId)) and cf.classSectionId = ").
			append(classSectionId).append(" and cf.schoolTermId =").append(termId).append(" )").append(" left join class c ON (c.id = cf.classId) left join ").
			append("studentFeePaidDetails sfp ON (sfp.studentId = ").append(studentId).append(" and sfp.feeId = cf.feeId and sfp.custId = s.custId and sfp.deleteStatus = 'N' and sfp.futureFeeStatus = 'Y')").
			append(" left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId =").append(studentId).append("  and sp.academicYearId =").append(academicYearId).append(" )").
			append(" where s.studentId = ").append(studentId).append(" and cf.academicYearId = ").append(academicYearId).append(" and cf.schoolTermId = ").append(termId).append(" and feeTypeId=").append(feeTypeId).append(" group by cf.feeId");
			/*StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate from vw_studentFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='Y' and paymentStatus='N' and feeTypeId=").append(feeTypeId).append(" group by feeId");*/
			 List<ViewStudentFeePaymentDetails> nonPaidFee =  getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
			 if(ObjectFunctions.isNotNullOrEmpty(nonPaidFee)){
				 return nonPaidFee.get(0);
			 }
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewOnlineApplicationStudentClassFees> getTermsWiseNonPaidClassFeePaymentDetailsForAdmissions(long classId,long academicYearId,String feeSettingId,long categoryId,String feeIds,long partialFeeId,double partialFeeAmount){
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,if((feeId = "+partialFeeId+"),(sum(feeAmount)-"+partialFeeAmount+"),sum(feeAmount)) as feeAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,if((feeId = "+partialFeeId+"),(sum(feeAmount)-"+partialFeeAmount+"),sum(feeAmount)) as payableAmount,'N' as paymentStatus from vw_onlineApplicationStudentClassFees where classId=").append(classId)
			.append(" and academicYearId=").append(academicYearId).append(" and feeSettingId in ").append(feeSettingId).append(" and categoryId=").append(categoryId).append(" and feeId not in").append(feeIds).append(" group by schoolTermId order by feeSettingId,schoolTermId");
			log.debug("query.toString()"+query.toString());
			return (List<ViewOnlineApplicationStudentClassFees>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName").addScalar("payableAmount",StandardBasicTypes.DOUBLE).addScalar("paymentStatus")
			.setResultTransformer( Transformers.aliasToBean(ViewOnlineApplicationStudentClassFees.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewOnlineApplicationStudentClassFees> getNonPaidClassFeePaymentDetailsForAdmissions(long classId,long academicYearId,long termId,long categoryId,String feeIds,long partialFeeId,double partialFeeAmount){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,if((feeId = "+partialFeeId+"),(feeAmount-"+partialFeeAmount+"),feeAmount) as feeAmount,if((feeId = "+partialFeeId+"),(feeAmount-"+partialFeeAmount+"),feeAmount) as payableAmount,'N' as paymentStatus from vw_onlineApplicationStudentClassFees where classId=")
			.append(classId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and categoryId=").append(categoryId).append(" and feeId not in").append(feeIds).append(" group by feeId");
			log.debug(query.toString());
			 return (List<ViewOnlineApplicationStudentClassFees>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("payableAmount",StandardBasicTypes.DOUBLE).addScalar("paymentStatus")
			  .setResultTransformer( Transformers.aliasToBean(ViewOnlineApplicationStudentClassFees.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId){
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,categoryName from vw_studentFeeParticularsPayment where studentId=").append(studentId)
			.append(" and academicYearId=").append(academicYearId).append(" and feeSettingId in ").append(feeSettingId).append(" and feeSettingId != ").append(3).append(" group by schoolTermId order by feeSettingId,schoolTermId");
			log.debug(query.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName").addScalar("categoryName")
			.setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getAllStudenClassFeePaymentDetails(long studentId,long academicYearId,long termId){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,sum(paymentConcessionAmount) as paymentConcessionAmount,concessionAmount from vw_studentFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and feeSettingId !=").append(3).append(" and deleteStatus='N' group by feeId");
			log.debug(query.toString());
			 return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate").addScalar("paymentConcessionAmount").addScalar("concessionAmount")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentsExcessPaymentDetails> getAllStudentsExcessPayments(String classSectionId,long academicYearId,long custId){
		try{
			/* Old Query when we use usedAccessAmount*/
			/*StringBuffer query = new StringBuffer("select excessPay.admissionNumber,excessPay.fullName,excessPay.className,excessPay.section,excessPay.mobileNumber,SUM(excessPay.excessAmount) - SUM(excessPay.usedExcessAmount) as remainingExcessAmount,excessPay.rollNumber from vw_studentsExcessPaymentDetails excessPay where accountId in (select accountId from student s where s.classSectionId =")
			.append(classSectionId).append(" and excessPay.academicYearId =").append(academicYearId).append(" and s.description is null ) group by accountId having (SUM(excessPay.excessAmount) - SUM(excessPay.usedExcessAmount)) !=0");*/
			/*New Query after remove usedAccessAmount*/
			StringBuffer query = new StringBuffer("select excessPay.admissionNumber,excessPay.fullName,excessPay.className,excessPay.section,excessPay.mobileNumber,SUM(excessPay.excessAmount) as remainingExcessAmount,excessPay.rollNumber from vw_studentsExcessPaymentDetails excessPay where excessPay.excessPaymentStatus='"+Constants.NO_STRING+"' and excessPay.accountId in (select accountId from student s where s.classSectionId in")
			.append(classSectionId).append(" and excessPay.academicYearId =").append(academicYearId).append(" and s.custId="+custId+" and s.description is null ) and custId="+custId+" group by accountId having SUM(excessPay.excessAmount) !=0");
			 return (List<ViewStudentsExcessPaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("admissionNumber").addScalar("fullName").addScalar("className").addScalar("section").addScalar("mobileNumber")
			 .addScalar("remainingExcessAmount").addScalar("rollNumber",StandardBasicTypes.STRING)
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentsExcessPaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getStudentClassFeeDetails(long studentId,long academicYearId,long termId){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,paymentStatus,priorityPosition,committedFeeStatus,concessionAmount from vw_studentFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and feeSettingId !=").append(3).append(" and schoolTermId=").append(termId).append(" and deleteStatus='N' group by feeId");
			log.debug(query.toString());
			 return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate").
			  addScalar("paymentStatus").addScalar("priorityPosition",StandardBasicTypes.LONG).addScalar("committedFeeStatus").addScalar("concessionAmount").setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getStudentFutureClassFeeDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId){
		try{
			StringBuffer query = new StringBuffer("select cf.feeSettingId,cf.settingName,cf.schoolTermId,cf.termName,cf.feeTypeId,cf.feeType,").
			append("cf.feeId,cf.feeAmount,IFNULL(sum(sfp.paymentAmount),0) as paymentAmount,IFNULL(sum(sfp.discountAmount),0)  as discountAmount,sp.paymentDate,IFNULL(sfp.paymentStatus,'N') as paymentStatus ").
			append("from vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',").
			append("(s.boardingPointId = cf.routeBoardingPointId and cf.custId = s.custId and cf.academicYearId =").append(academicYearId).append(" and s.transportMode = 'T' and cf.categoryId = s.categoryId),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and s.boardingPointId != 0 and cf.categoryId = s.categoryId)),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and cf.categoryId = s.categoryId)) and cf.classSectionId = ").
			append(classSectionId).append(" and cf.schoolTermId =").append(termId).append(" )").append(" left join class c ON (c.id = cf.classId) left join ").
			append("studentFeePaidDetails sfp ON (sfp.studentId = ").append(studentId).append(" and sfp.feeId = cf.feeId and sfp.custId = s.custId and sfp.deleteStatus = 'N' and sfp.futureFeeStatus = 'Y')").
			append(" left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId =").append(studentId).append("  and sp.academicYearId =").append(academicYearId).append(" )").
			append(" where s.studentId = ").append(studentId).append(" and cf.academicYearId = ").append(academicYearId).append(" and cf.schoolTermId = ").append(termId).append(" group by cf.feeId");
			log.debug(query.toString());
			 return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate").
			  addScalar("paymentStatus").setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getAllStudentTermsWiseFutureClassFeePaymentDetails(long studentId,long academicYearId,String feeSettingId,long classId,long classSectionId){
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName from (").
			append("SELECT cf.schoolTermId,IFNULL(cf.feeAmount, 0) as feeAmount,IFNULL(sfp.paymentAmount, 0) as paymentAmount,IFNULL(sfp.discountAmount, 0) as discountAmount,cf.settingName,cf.termName,cf.feeSettingId,cf.fromMonthName,cf.toMonthName ").
			append(" from vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s on (IF(cf.settingName= 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',(s.boardingPointId = cf.routeBoardingPointId and cf.custId=s.custId and cf.academicYearId=").append(academicYearId).append(" and s.transportMode = 'T' and cf.categoryId = s.categoryId),(cf.classId = ").
			append(classId).append(" and cf.custId=s.custId and cf.academicYearId=").append(academicYearId).append(" and s.boardingPointId!=0 and cf.categoryId = s.categoryId)),").
			append("cf.classId = ").append(classId).append(" and cf.custId=s.custId and cf.academicYearId=").append(academicYearId).append(" and cf.categoryId = s.categoryId").append(" ))  and cf.classSectionId = ").append(classSectionId).
			append(" left join class c on (c.id=cf.classId) ").append(" left join studentFeePaidDetails sfp ON (s.studentId = sfp.studentId and cf.feeId = sfp.feeId and s.custId=sfp.custId and sfp.deleteStatus = 'N' and sfp.futureFeeStatus='Y')").
			append(" where s.studentId=").append(studentId).append(" and cf.academicYearId=").append(academicYearId).append(" and cf.feeSettingId in ").append(feeSettingId).append(" order by sfp.paymentStatus) feeDetails group by schoolTermId order by feeSettingId,schoolTermId");
			/*StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName from vw_studentFeePaymentDetails where studentId=").append(studentId)
			.append(" and academicYearId=").append(academicYearId).append(" and feeSettingId in ").append(feeSettingId).append(" group by schoolTermId order by feeSettingId,schoolTermId");*/
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName")
			.setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getAllStudentFutureClassFeePaymentDetails(long studentId,long academicYearId,long termId,long classId,long classSectionId){
		try{
			StringBuffer query = new StringBuffer("select cf.feeSettingId,cf.settingName,cf.schoolTermId,cf.termName,cf.feeTypeId,cf.feeType,").
			append("cf.feeId,cf.feeAmount,IFNULL(sum(sfp.paymentAmount),0) as paymentAmount,IFNULL(sum(sfp.discountAmount),0)  as discountAmount,sp.paymentDate ").
			append("from vw_classFeeDetails cf JOIN vw_studentsFutureAcademicTransportDetails s ON (IF(cf.settingName = 'Transport Fee',IF(cf.transportFeeByBoardingPoint = 'Y',").
			append("(s.boardingPointId = cf.routeBoardingPointId and cf.custId = s.custId and cf.academicYearId =").append(academicYearId).append(" and s.transportMode = 'T' and cf.categoryId = s.categoryId),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and s.boardingPointId != 0 and cf.categoryId = s.categoryId)),").
			append("(cf.classId =").append(classId).append(" and cf.custId = s.custId and cf.academicYearId = ").append(academicYearId).append(" and cf.categoryId = s.categoryId)) and cf.classSectionId = ").
			append(classSectionId).append(" and cf.schoolTermId =").append(termId).append(" )").append(" left join class c ON (c.id = cf.classId) left join ").
			append("studentFeePaidDetails sfp ON (sfp.studentId = ").append(studentId).append(" and sfp.feeId = cf.feeId and sfp.custId = s.custId and sfp.deleteStatus = 'N' and sfp.futureFeeStatus = 'Y')").
			append(" left join studentPayment sp ON (sp.id = sfp.studentPaymentId and sp.custId = sfp.custId and sp.studentId =").append(studentId).append("  and sp.academicYearId =").append(academicYearId).append(" )").
			append(" where s.studentId = ").append(studentId).append(" and cf.academicYearId = ").append(academicYearId).append(" and cf.schoolTermId = ").append(termId).append(" group by cf.feeId");
			/*StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate from vw_studentFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='Y' group by feeId");*/
			 return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getFutureAcademicYearStudentFeeReceipts(long studentId,long academicYearId,String invoiceNumber){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,paymentAmount,discountAmount,paymentDate,studentId,").
			append("custId,academicYearId,futureAcademicId,fromMonthName,toMonthName,categoryId,invoiceNumber,paidAmount,accountId,status,description,").
			append("className,section,classSectionId,admissionNumber,firstName,lastName,middleName,mobileNumber,phoneNumber,parentEmail,registerNumber,fullName,IFNULL(transactionNumber,0) as transactionNumber,IFNULL(bankId, 0) as bankId,chequeIssuedDate,ddDrawnDate,branchName,lastUpdatedDate,paymentType,ddNumber,bankName,chequeNumber from vw_studentsFutureAcademicFeeDetails ").
			append(" where studentId = ").append(studentId).append(" and futureAcademicId = ").append(academicYearId);
			if(StringFunctions.isNullOrEmpty(invoiceNumber)){
				query.append(" and invoiceNumber !=0 group by invoiceNumber");
			}else{
				query.append(" and invoiceNumber=").append(invoiceNumber).append(" order by schoolTermId,feeTypeId");
			}
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString()).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("termName")
			.addScalar("feeTypeId",StandardBasicTypes.LONG).addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount")
			.addScalar("studentId",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG).addScalar("academicYearId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName").addScalar("categoryId",StandardBasicTypes.LONG).addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("paidAmount",StandardBasicTypes.DOUBLE)
			.addScalar("status",StandardBasicTypes.STRING).addScalar("description").addScalar("paymentDate").addScalar("transactionNumber",StandardBasicTypes.STRING).addScalar("bankId",StandardBasicTypes.LONG)
			.addScalar("chequeIssuedDate",StandardBasicTypes.DATE).addScalar("ddDrawnDate",StandardBasicTypes.DATE).addScalar("branchName",StandardBasicTypes.STRING).addScalar("lastUpdatedDate",StandardBasicTypes.DATE)
			.addScalar("paymentType",StandardBasicTypes.STRING).addScalar("ddNumber",StandardBasicTypes.STRING).addScalar("bankName",StandardBasicTypes.STRING)
			.addScalar("chequeNumber",StandardBasicTypes.STRING).setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds){
		try{
			StringBuffer query = new StringBuffer("select studentId,paymentDate,invoiceNumber,fullName,className,section,admissionNumber,paymentAmount,feeTypeId,invoiceString from "+tableName+" where custId=")
			.append(custId).append(" and academicYearId=").append(academicYearId).append(" and classSectionId in").append(classSectionIds).append( " and paymentDate='").append(fromDate).append("' and studentPaymentId!=0 and feeSettingId in").append(feeSettingIds).append(" and paymentCommitFeeStatus='N' ").append(" order by sortingOrder,studentId,invoiceNumber,paymentDate,studentPaymentId");//append(" and status='Y'") ORDER BY paymentDate
			log.debug(query.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString()).addScalar("studentId",StandardBasicTypes.LONG).addScalar("paymentDate",StandardBasicTypes.DATE).addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("fullName").addScalar("className").addScalar("section")
			.addScalar("admissionNumber").addScalar("paymentAmount",StandardBasicTypes.DOUBLE).addScalar("feeTypeId",StandardBasicTypes.LONG).addScalar("invoiceString").setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewFee> getViewFeeDetails(String clause){
		StringBuffer query = new StringBuffer("select * from Fee WHERE ").append(clause);
		return (List<ViewFee>) getSession().createSQLQuery(query.toString()).addScalar("id",StandardBasicTypes.LONG).addScalar("version",StandardBasicTypes.INTEGER).addScalar("lastAccessDate",StandardBasicTypes.DATE)
		.addScalar("lastUpdatedDate",StandardBasicTypes.DATE).addScalar("createdDate",StandardBasicTypes.DATE).addScalar("createdById",StandardBasicTypes.LONG)
		.addScalar("lastUpdatedById",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG).addScalar("classId",StandardBasicTypes.LONG)
		.addScalar("status",StandardBasicTypes.STRING).addScalar("feeAmount",StandardBasicTypes.DOUBLE).addScalar("academicYearId",StandardBasicTypes.LONG)
		.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeTypeId",StandardBasicTypes.LONG).addScalar("categoryId",StandardBasicTypes.LONG)
		.addScalar("routeBoardingPointId",StandardBasicTypes.LONG).
		setResultTransformer( Transformers.aliasToBean(ViewFee.class)).list();
    }

	public void updateStudentPaymentStatus(long classId,long categoryId,String status) {
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("update student set feePaidStatus='"+ status + "' where classNameClassId="+ classId +" and categoryId="+categoryId);
			if("P".equalsIgnoreCase(status))
				sqlString.append(" and feePaidStatus in('F','C') ");
			Query qry = getSession().createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void updateFeeConfiguredStatusInStudent(long classId,long categoryId,String status){
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("update student set feeConfigured='"+ status + "' where classNameClassId="+ classId +" and categoryId="+categoryId);
			Query qry = session.createSQLQuery(sqlString.toString());
			log.debug(qry);
			int row = qry.executeUpdate();
			 session.flush();tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
	}
	public List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate){
		try{
			StringBuffer query = new StringBuffer("select stud.rollNumber,stud.fullName as studentname,stud.studId as studentId,stud.custId,stud.academicYearId,")
			.append("stud.accountId,stud.classSectionId,stud.classId,stud.studDiscontinueDesc,stud.status,IFNULL(att.present,'Y') as present,IFNULL(att.afternoonSession,'Y') as afternoonSession,att.leaveSessionType,IFNULL(att.id,0) as studentAttendanceId,IFNULL(att.leaveRequest,' ') as leaveRequest,")
			.append("stud.admissionNumber,stud.mobileNumber")
			.append(" from vw_studentPersonInfo as stud LEFT JOIN studentDailyAttendance att on (stud.studId =  att.studentId and att.attendanceDate='"+attendanceDate+"')").
			append(" where stud.studDiscontinueDesc is null and stud.classSectionId=").append(classSectionId);
			log.debug(query.toString());
			 return (List<VWStudentDailyAttendance>) getSession().createSQLQuery(query.toString())
			 .addScalar("rollNumber",StandardBasicTypes.STRING).addScalar("studentname").addScalar("studentId",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG)
			 .addScalar("academicYearId",StandardBasicTypes.LONG).addScalar("accountId",StandardBasicTypes.LONG).addScalar("classSectionId",StandardBasicTypes.LONG).addScalar("classId",StandardBasicTypes.LONG).addScalar("studDiscontinueDesc")
			 .addScalar("status",StandardBasicTypes.STRING).addScalar("present",StandardBasicTypes.BOOLEAN).addScalar("studentAttendanceId",StandardBasicTypes.LONG).addScalar("leaveRequest", StandardBasicTypes.CHARACTER).addScalar("afternoonSession",StandardBasicTypes.BOOLEAN).addScalar("leaveSessionType",StandardBasicTypes.STRING)
			 .addScalar("admissionNumber", StandardBasicTypes.STRING) .addScalar("mobileNumber", StandardBasicTypes.STRING)
			  .setResultTransformer( Transformers.aliasToBean(VWStudentDailyAttendance.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<VWStaffAttendance> getStaffAttendanceByAttendanceDate(String attendanceDate,long custId,long academicYearId,String staffBiometric){
		try{
			if("staffBiometric".equalsIgnoreCase(staffBiometric)){
				StringBuffer query = new StringBuffer("select staff.roleDescription,staff.username,staff.firstName,staff.lastName,staff.staffId,staff.custId,staff.academicYearId,att.staffLateTime,")
				.append("staff.accountId,staff.roleName,staff.description,staff.status,IFNULL(att.present,'Y') as present,IFNULL(att.id,0) as attendanceId,IFNULL(att.leaveRequest,' ') as leaveRequest,staff.mobileNumber AS mobileNumber ")
				.append(" from vw_staffDetails as staff LEFT JOIN staffDailyAttendance att on (staff.accountId =  att.accountId and att.attendanceDate='").append(attendanceDate).append("' and staff.academicYearId <=").
				append(academicYearId).append(") where staff.description is null and staff.custId = ").append(custId).append(" and staff.academicYearId <=").append(academicYearId).append(" and staff.bioMetricId >0").append(" and att.present='Y'").append(" and att.staffLateTime is not null").append(" order by staff.roleId,staff.firstName");
				log.debug(query.toString());
				 return (List<VWStaffAttendance>) getSession().createSQLQuery(query.toString()).addScalar("username").addScalar("roleDescription")
				 .addScalar("firstName").addScalar("lastName").addScalar("staffId",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG)
				 .addScalar("academicYearId",StandardBasicTypes.LONG).addScalar("accountId",StandardBasicTypes.LONG).addScalar("roleName").addScalar("description").addScalar("status").addScalar("staffLateTime")
				 .addScalar("present",StandardBasicTypes.BOOLEAN).addScalar("attendanceId",StandardBasicTypes.LONG).addScalar("leaveRequest", StandardBasicTypes.CHARACTER)
				  .addScalar("mobileNumber", StandardBasicTypes.STRING).setResultTransformer( Transformers.aliasToBean(VWStaffAttendance.class)).list()
				  ;
			}else{
				StringBuffer query = new StringBuffer("select staff.roleDescription,staff.username,staff.firstName,staff.lastName,staff.staffId,staff.custId,staff.academicYearId,")
				.append("staff.accountId,staff.roleName,staff.description,staff.status,IFNULL(att.present,'Y') as present,IFNULL(att.afternoonSession,'Y') as afternoonSession,att.leaveSessionType,IFNULL(att.id,0) as attendanceId,IFNULL(att.leaveRequest,' ') as leaveRequest,staff.mobileNumber AS mobileNumber ")
				.append(" from vw_staffDetails as staff LEFT JOIN staffDailyAttendance att on (staff.accountId =  att.accountId and att.attendanceDate='").append(attendanceDate).append("' and staff.academicYearId <=").
				append(academicYearId).append(") where staff.description is null and staff.custId = ").append(custId).append(" and staff.academicYearId <=").append(academicYearId).append(" order by staff.roleId,staff.firstName");
				log.debug(query.toString());
				 return (List<VWStaffAttendance>) getSession().createSQLQuery(query.toString()).addScalar("username").addScalar("roleDescription")
				 .addScalar("firstName").addScalar("lastName").addScalar("staffId",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG)
				 .addScalar("academicYearId",StandardBasicTypes.LONG).addScalar("accountId",StandardBasicTypes.LONG).addScalar("roleName").addScalar("description").addScalar("status")
				 .addScalar("present",StandardBasicTypes.BOOLEAN).addScalar("attendanceId",StandardBasicTypes.LONG).addScalar("leaveRequest", StandardBasicTypes.CHARACTER).addScalar("afternoonSession",StandardBasicTypes.BOOLEAN).addScalar("leaveSessionType",StandardBasicTypes.STRING)
				  .addScalar("mobileNumber", StandardBasicTypes.STRING).setResultTransformer( Transformers.aliasToBean(VWStaffAttendance.class)).list()
				  ;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<Fee> getClassFeeListByTermId(String schoolTermIds,String classIds,long academicYearId,long custId){
		try{
			StringBuffer query = new StringBuffer("select f.classId as className,f.categoryId,f.schoolTermId as schoolTerms,sum(f.feeAmount) as feeAmount from Fee f LEFT JOIN schoolTerms st on (f.schoolTermId=st.id) LEFT JOIN class c on (f.classId=c.id) where f.custId=")
			.append(custId).append(" and f.academicYearId=").append(academicYearId).append(" and f.schoolTermId in ").append(schoolTermIds).append(" and f.classId in ").append(classIds).append(" group by f.schoolTermId,f.classId,f.categoryId order by st.dueDate,c.sortingOrder,f.categoryId");
			//log.debug(query.toString());
			// return (List<Fee>) this.getAll(query.toString())
			// .addScalar("className",Hibernate.entity(ClassName.class)).addScalar("categoryId",StandardBasicTypes.LONG).addScalar("schoolTerms",Hibernate.entity(SchoolTerms.class)).addScalar("feeAmount",StandardBasicTypes.DOUBLE).setResultTransformer( Transformers.aliasToBean(Fee.class));
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	public List<ViewStudentFeePaymentDetails> getFeeCollectionList(String tableName,long custId,long academicYearId,String toDayDate,String daysBeetweenStartDate,String daysBeetweenEndDate,String receiptType) {
		try {
			StringBuffer queryBuff = new StringBuffer().append("select sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,studentPaymentId,rollNumber,fullName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber,feeSettingId,settingName,admissionNumber,className,section,id,schoolTermId,sum(paymentConcessionAmount) as paymentConcessionAmount,invoiceString,studentId,");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append("feeId ");
			else if ("vw_studentTransportFeePaymentDetails".equalsIgnoreCase(tableName)) 
				queryBuff.append("transportFeeId as feeId ");
			queryBuff.append("from "+tableName+" where custId=");
			queryBuff.append(custId).append(" and academicYearId=").append(academicYearId).append(" and deleteStatus='N'").append(" and paymentCommitFeeStatus='N' ").append(" and paymentAmount !=0");//.append(" and status='Y'")
			if("A".equalsIgnoreCase(receiptType))
				queryBuff.append(" and invoiceNumber!=0");
			
			if (!StringFunctions.isNullOrEmpty(toDayDate)) {
				queryBuff.append(" and paymentDate like ");
				queryBuff.append("'%" +toDayDate+ "%'");
			}
			if (!StringFunctions.isNullOrEmpty(daysBeetweenStartDate) && !StringFunctions.isNullOrEmpty(daysBeetweenEndDate)) {
				queryBuff.append(" and paymentDate BETWEEN '").append(daysBeetweenStartDate).append(" 00:00:00'").append(" and '").append(daysBeetweenEndDate).append(" 00:00:00'");
			}
			queryBuff.append(" group by studentId,schoolTermId, ");
			if("A".equalsIgnoreCase(receiptType))
				queryBuff.append("invoiceNumber");
			else
				queryBuff.append("invoiceString");
			queryBuff.append(" order by schoolTermId,dueDate,");
			if("A".equalsIgnoreCase(receiptType))
				queryBuff.append("invoiceNumber");
			else
				queryBuff.append("invoiceString");
			
			log.debug(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
			.addScalar("discountAmount", StandardBasicTypes.DOUBLE).addScalar("paymentDate").addScalar("studentPaymentId", StandardBasicTypes.LONG).addScalar("rollNumber", StandardBasicTypes.STRING)
			.addScalar("fullName").addScalar("paymentType").addScalar("termName").addScalar("feeType").addScalar("ddNumber")
			.addScalar("chequeNumber").addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("feeSettingId", StandardBasicTypes.LONG).addScalar("settingName")
			.addScalar("admissionNumber").addScalar("className").addScalar("section").addScalar("id", StandardBasicTypes.LONG).addScalar("schoolTermId", StandardBasicTypes.LONG).addScalar("paymentConcessionAmount").addScalar("invoiceString",StandardBasicTypes.STRING).addScalar("studentId", StandardBasicTypes.LONG).addScalar("feeId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getTermWiseFeeCollectionList(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String receiptType){
		try {
			StringBuffer queryBuff = new StringBuffer().append("select sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,studentPaymentId,rollNumber,fullName,paymentType,termName,feeType,ddNumber,chequeNumber,invoiceNumber,feeSettingId,settingName,admissionNumber,className,section,schoolTermId,id,sum(paymentConcessionAmount) as paymentConcessionAmount,invoiceString,studentId,");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append("feeId ");
			else if ("vw_studentTransportFeePaymentDetails".equalsIgnoreCase(tableName)) 
				queryBuff.append("transportFeeId as feeId ");
			queryBuff.append("from "+tableName+" where custId=");
			queryBuff.append(custId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId in ").append(termIds).append(" and paymentCommitFeeStatus='N'");//.append(" and status='Y'")
			if(!ObjectFunctions.isNullOrEmpty(selectedClassSectionIds)){
				queryBuff.append(" and classSectionId in ").append(selectedClassSectionIds);
			}
			queryBuff.append(" and deleteStatus='N'").append(" and paymentAmount !=0");
			if("A".equalsIgnoreCase(receiptType))
				queryBuff.append(" and invoiceNumber!=0");
			queryBuff.append(" group by schoolTermId,studentId, ");
			if("A".equalsIgnoreCase(receiptType))
				queryBuff.append("invoiceNumber");
			else
				queryBuff.append("invoiceString");
			queryBuff.append(" order by schoolTermId,classSectionId,dueDate,");
			if("A".equalsIgnoreCase(receiptType))
				queryBuff.append("invoiceNumber");
			else
				queryBuff.append("invoiceString");
			
			//log.debug(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
			.addScalar("discountAmount", StandardBasicTypes.DOUBLE).addScalar("paymentDate").addScalar("studentPaymentId", StandardBasicTypes.LONG).addScalar("rollNumber", StandardBasicTypes.STRING)
			.addScalar("fullName").addScalar("paymentType").addScalar("termName").addScalar("feeType").addScalar("ddNumber")
			.addScalar("chequeNumber").addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("feeSettingId", StandardBasicTypes.LONG).addScalar("settingName")
			.addScalar("admissionNumber").addScalar("className").addScalar("section").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("id", StandardBasicTypes.LONG).addScalar("paymentConcessionAmount").addScalar("invoiceString",StandardBasicTypes.STRING).addScalar("studentId", StandardBasicTypes.LONG).addScalar("feeId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate){
		try { 
			StringBuffer queryBuff = new StringBuffer().append("select sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,(sum(feeAmount)-(sum(paymentAmount)+sum(discountAmount)+sum(concessionAmount))) as paidAmount,sum(discountAmount) as discountAmount,fullName,termName,admissionNumber,className,section,schoolTermId,dueDate,mobileNumber,id,settingName,committedFee,feeAmount as fineAmount,classId,studentId from vw_studentFeePaymentDetails where custId=");
			queryBuff.append(custId).append(" and academicYearId=").append(academicYearId).append(" and status='Y' and description is null");
			if (!StringFunctions.isNullOrEmpty(classIds)) {
				queryBuff.append(" and classId in").append(classIds);
				log.debug(" classIds"+classIds);
			}
			if (!StringFunctions.isNullOrEmpty(termsIs)) {
				queryBuff.append(" and schoolTermId in").append(termsIs);
			}
			queryBuff.append(" and deleteStatus='N'").append(" and paymentStatus='"+Constants.NO_STRING+"'").append(" and paymentCommitFeeStatus='N' ").append(" and dueDate <='"+toDayDate+" 00:00:00' ").append(" group by studentId");
			log.info(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE).addScalar("paidAmount").addScalar("discountAmount").addScalar("committedFee", StandardBasicTypes.DOUBLE).addScalar("fineAmount", StandardBasicTypes.DOUBLE)
			.addScalar("fullName").addScalar("termName").addScalar("admissionNumber").addScalar("className").addScalar("section").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("dueDate").addScalar("mobileNumber").addScalar("id", StandardBasicTypes.LONG).addScalar("settingName").addScalar("classId", StandardBasicTypes.LONG).addScalar("studentId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getStudentFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,String termIds,String selectedClassSectionIds,String studentStatus){
		try {
			StringBuilder queryBuff = new StringBuilder();
			queryBuff.append("select fee.TermFee as feeAmount,sum(sfp.paymentAmount) as paymentAmount,((fee.TermFee)-(IFNULL(sum(sfp.paymentAmount),0)+IFNULL(sum(sfp.discountAmount),0)+IFNULL(fee.concessionAmount,0))) as paidAmount,sum(sfp.discountAmount) as discountAmount,fee.concessionAmount as concessionAmount,sfp.fullName,sfp.termName,sfp.admissionNumber,sfp.className,sfp.section,sfp.schoolTermId,sfp.dueDate,sfp.mobileNumber,sfp.id,sfp.settingName,sfp.classId,sfp.studentId from "+tableName+" sfp ");
			queryBuff.append(" LEFT JOIN (select sum(feeAmount) TermFee,schoolTermId,studentId,IFNULL(sum(concessionAmount),0) as concessionAmount from ");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append(" vw_studentClassFees ");
			else if ("vw_studentTransportFeeParticularsPayment".equalsIgnoreCase(tableName)) 
				queryBuff.append(" vw_studentTransportFees");
			
			queryBuff.append(" where custId=").append(custId).append(" and academicYearId=").append(academicYearId);
			if (!StringFunctions.isNullOrEmpty(termIds)) {
				queryBuff.append(" and schoolTermId in").append(termIds);
			}
			if(!ObjectFunctions.isNullOrEmpty(selectedClassSectionIds)){
				queryBuff.append(" and classSectionId in").append(selectedClassSectionIds);
			} 
			if("N".equalsIgnoreCase(studentStatus))
				queryBuff.append(" and status='Y' and description is null");
			queryBuff.append(" group by schoolTermId,studentId ) fee ON (fee.schoolTermId=sfp.schoolTermId  and fee.studentId=sfp.studentId)");
			
			queryBuff.append(" where sfp.custId=").append(custId).append(" and sfp.academicYearId=").append(academicYearId);
			if (!StringFunctions.isNullOrEmpty(termIds)) {
				queryBuff.append(" and sfp.schoolTermId in").append(termIds);
			}
			if(!ObjectFunctions.isNullOrEmpty(selectedClassSectionIds)){
				queryBuff.append(" and sfp.classSectionId in").append(selectedClassSectionIds);
			} 
			queryBuff.append(" and sfp.deleteStatus='N' ");
			if("N".equalsIgnoreCase(studentStatus))
				queryBuff.append(" and sfp.status='Y' and sfp.description is null");
			queryBuff.append(" group by sfp.schoolTermId,sfp.studentId order by sfp.classSectionId,sfp.schoolTermId");
			/*StringBuilder queryBuff = new StringBuilder().append("select sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,(sum(feeAmount)-(sum(paymentAmount)+sum(discountAmount))) as paidAmount,sum(discountAmount) as discountAmount,fullName,termName,admissionNumber,className,section,schoolTermId,dueDate,mobileNumber,id,settingName,committedFee,classId,studentId from vw_studentFeePaymentDetails where custId=");
			queryBuff.append(custId).append(" and academicYearId=").append(academicYearId);//.append(" and status='Y'")
			if (!StringFunctions.isNullOrEmpty(termIds)) {
				queryBuff.append(" and schoolTermId in").append(termIds);
			}
			if(!ObjectFunctions.isNullOrEmpty(selectedClassSectionIds)){
				queryBuff.append(" and classSectionId in").append(selectedClassSectionIds);
			}
			queryBuff.append(" and deleteStatus='N'");
			if("N".equalsIgnoreCase(studentStatus))
				queryBuff.append(" and status='Y' and description is null");
			 @Ganesh Below "paymentCommitFeeStatus" condition is taken out because if we give concession amount to any student we can not get student concession details. So that student we can not able to show in list 
			queryBuff.append(" group by schoolTermId,studentId order by classSectionId,schoolTermId");//.append(" and paymentCommitFeeStatus='N' ")//.append(" and paymentStatus='"+Constants.NO_STRING+"'")*/
			log.info(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
					.addScalar("paidAmount").addScalar("discountAmount").addScalar("concessionAmount").addScalar("fullName").addScalar("termName").addScalar("admissionNumber")
					.addScalar("className").addScalar("section").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("dueDate").addScalar("mobileNumber").addScalar("id", StandardBasicTypes.LONG)
					.addScalar("settingName").addScalar("classId", StandardBasicTypes.LONG).addScalar("studentId", StandardBasicTypes.LONG)
					.setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	public void updateSelectedLoginRoles(String selectedId,Long custId){
		try{
			StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update loginAccessbilityRoles set status ='Y'"); 
	           sqlString.append(" where customerId=");
	           sqlString.append(custId);
	           sqlString.append(" and roleId in(");
	           sqlString.append(selectedId);
	           sqlString.append(")");
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
			
		
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	public void updateUnselectedLoginRoles(String unselectedId,Long custId){
		try{
			StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update loginAccessbilityRoles set status ='N'"); 
	           sqlString.append(" where customerId=");
	           sqlString.append(custId);
	           sqlString.append(" and roleId in(");
	           sqlString.append(unselectedId);
	           sqlString.append(")");
	           int rows = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(rows == 0)
	            {
	                log.debug("The no of rows Updated:"+ rows);
	            }
			
		
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	
	public List<ViewHostelStudentDailyAttendance> getHostelStudentsAttendanceByClassSectionIdAndAttendanceDate(long classSectionId, String attendanceDate){
		try{
			StringBuffer query = new StringBuffer("select stud.rollNumber,stud.fullName as studentname,stud.studId as studentId,stud.custId,stud.academicYearId,")
			.append("stud.accountId,stud.classSectionId,stud.classId,stud.studDiscontinueDesc,stud.status,IFNULL(att.present,'Y') as present,IFNULL(att.id,0) as studentAttendanceId,IFNULL(att.leaveRequest,' ') as leaveRequest,")
			.append("stud.admissionNumber")
			.append(" from vw_studentPersonInfo as stud LEFT JOIN hostelStudentDailyAttendance att on (stud.studId =  att.studentId and att.attendanceDate='"+attendanceDate+"')").
			append(" where stud.studDiscontinueDesc is null and stud.hostelMode='H' and stud.classSectionId=").append(classSectionId);
			log.debug(query.toString());
			 return (List<ViewHostelStudentDailyAttendance>) getSession().createSQLQuery(query.toString())
			 .addScalar("rollNumber",StandardBasicTypes.STRING).addScalar("studentname").addScalar("studentId",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG)
			 .addScalar("academicYearId",StandardBasicTypes.LONG).addScalar("accountId",StandardBasicTypes.LONG).addScalar("classSectionId",StandardBasicTypes.LONG).addScalar("classId",StandardBasicTypes.LONG).addScalar("studDiscontinueDesc",StandardBasicTypes.STRING)
			 .addScalar("status",StandardBasicTypes.STRING).addScalar("present",StandardBasicTypes.BOOLEAN).addScalar("studentAttendanceId",StandardBasicTypes.LONG).addScalar("leaveRequest", StandardBasicTypes.CHARACTER)
			 .addScalar("admissionNumber",StandardBasicTypes.STRING)
			  .setResultTransformer( Transformers.aliasToBean(VWStudentDailyAttendance.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 public void insertIntoLoginAcceessbilityRoles(List<Role> roles,long custId){
		  try{
			    log.debug("Started for the custId of "+custId+" roles insertion.....");
				for(Role roleObj : roles){
					Query query = getSession().createSQLQuery("INSERT INTO loginAccessbilityRoles(version,customerId,roleId,status,androidStatus) VALUES(:version,:customerId,:roleId,:status,:androidStatus)");
					query.setParameter("version",0);
					query.setParameter("customerId", custId);
					query.setParameter("roleId", roleObj.getId());
					query.setParameter("status", 'Y');
					query.setParameter("androidStatus", 'Y');
					query.executeUpdate();
	  	    	}
				log.debug("Completed for the custId of "+custId+" roles insertion.....");
	      }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
	    }
	 public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermId(String tableName,long custId,long academicYearId,String termIds,String fromDate,String toDate,String feeSettingIds){
		try{
			StringBuffer query = new StringBuffer("select studentId,paymentDate,invoiceNumber,fullName,className,section,admissionNumber,paymentAmount,feeTypeId,termName,paymentType,chequeNumber,discountAmount,feeAmount,fineAmount from "+tableName+" where custId=")
			.append(custId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId in").append(termIds).append( " and paymentDate>='").append(fromDate).append(" 00:00:00' and paymentDate<='")
			.append(toDate).append(" 00:00:00' and studentPaymentId!=0 and feeSettingId in").append(feeSettingIds).append(" order by invoiceNumber,studentId,paymentDate,studentPaymentId");
			log.info(query.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString()).addScalar("studentId",StandardBasicTypes.LONG).addScalar("paymentDate",StandardBasicTypes.DATE).addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("fullName").addScalar("className").addScalar("section")
			.addScalar("admissionNumber").addScalar("paymentAmount",StandardBasicTypes.DOUBLE).addScalar("feeTypeId",StandardBasicTypes.LONG).addScalar("termName").addScalar("paymentType").addScalar("chequeNumber").addScalar("discountAmount",StandardBasicTypes.DOUBLE).addScalar("feeAmount",StandardBasicTypes.DOUBLE).addScalar("fineAmount",StandardBasicTypes.DOUBLE).setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySchoolTermIdAndEndDateAndStatus(String tableName,long custId,long academicYearId,String termIds,String toDate,String feeSettingIds,String isAdvancedOrOutstand){
		try{
			 StringBuffer query = new StringBuffer("select studentId,paymentDate,invoiceNumber,fullName,className,section,admissionNumber,paymentAmount,feeTypeId,termName,paymentType,chequeNumber,discountAmount,feeAmount,feeType,status,paymentStatus from "+tableName+" where custId=")
			.append(custId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId in");//.append(" and status='Y' ")
			if("advancedCollectionReport".equalsIgnoreCase(isAdvancedOrOutstand)){
				query.append(termIds).append( " and paymentDate<='").append(toDate).append(" 00:00:00' and studentPaymentId!=0 and feeSettingId in").append(feeSettingIds);
			}else{
				String[] feeSettingArr=termIds.split(",");
				if(feeSettingArr.length == 3)
				 query.append(termIds).append(" and feeSettingId in").append(feeSettingIds);
				else
				 query.append(termIds).append(" and feeSettingId in").append(feeSettingIds).append(" and paymentStatus='N'");	
			}
			query.append(" and paymentCommitFeeStatus='N'");
			query.append(" order by sortingOrder,section,firstName,studentId,paymentDate");
			log.debug("This is query for "+isAdvancedOrOutstand+"  report...."+query.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString()).addScalar("studentId",StandardBasicTypes.LONG).addScalar("paymentDate",StandardBasicTypes.DATE)
			.addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("fullName").addScalar("className").addScalar("section").addScalar("status",StandardBasicTypes.STRING)
			.addScalar("admissionNumber").addScalar("paymentAmount",StandardBasicTypes.DOUBLE).addScalar("feeTypeId",StandardBasicTypes.LONG)
			.addScalar("feeType").addScalar("paymentType").addScalar("chequeNumber").addScalar("discountAmount",StandardBasicTypes.DOUBLE)
			.addScalar("feeAmount",StandardBasicTypes.DOUBLE).addScalar("paymentStatus",StandardBasicTypes.STRING).setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List<OnlineApplicationDetailsView> getAdminssionApplicationFeeDetails(long custId,long academicYearId)
	{
		try{
		String todayDate= DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
		List admissionDetails= this.getAllHqlQuery("from OnlineApplicationDetailsView where custId="+custId+" and academicYearId="+academicYearId+" and createdDate like'%"+todayDate+"%'");

 	   if (!ObjectFunctions.isNullOrEmpty(admissionDetails)) {
			return (List<OnlineApplicationDetailsView>) admissionDetails;
		 } 
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentMarksDetails> getStudentMarksDetailsByClassIdAndAcademicYearIdAndCustId(long custId,long academicYearId,long classSectionId)
	{
		try{
			 StringBuffer query = new StringBuffer("select examTypeId,examType from vw_examTypesSchedules  where custId=")
				.append(custId).append(" and academicYearId=").append(academicYearId).append(" and classSectionId=").append(classSectionId);
			 /*StringBuffer query = new StringBuffer("select studId,examTypeId,examType from vw_studentMarksDetails where custId=")
				.append(custId).append(" and academicYearId=").append(academicYearId).append(" and totalSubjectMarksObtained!=0.0 ").append(" and classSectionId=").append(classSectionId).append(" group by examTypeId");*/
			 return (List<ViewStudentMarksDetails>) getSession().createSQLQuery(query.toString()).addScalar("examTypeId",StandardBasicTypes.LONG)
				.addScalar("examType").setResultTransformer( Transformers.aliasToBean(ViewStudentMarksDetails.class)).list();
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	//this code commented by Sunanda because we have already implemented this method in adminMananagerImpl
	/*public List<StudyClass> GetAllStudyClasses(long custId,long academicYearId,String studyClassIds){
		try{			
			StringBuffer query = new StringBuffer("from StudyClass where custId=").append(custId).append(" and academicYearId=").append(academicYearId);
			if (!StringFunctions.isNullOrEmpty(studyClassIds)){
				if(studyClassIds.contains("("))
					query.append(" and id in "+studyClassIds+"");
				else
				query.append(" and id in ("+studyClassIds+")");
			}						
			List studyClass= this.getAllHqlQuery(query.toString());	 
			if(ObjectFunctions.isNullOrEmpty(studyClass))
				studyClass=null;
			else
				Collections.sort(studyClass);
			
			return (List<StudyClass>) studyClass;					
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		} 
		return null;
	}*/
	public List<StudySubject> GetAllStudySubjects(long custId,long academicYearId){
		try{
			StringBuffer query = new StringBuffer("select id,name,sortingOrder,subjectNumber from studySubject where academicYearId=").append(academicYearId).append(" and custId=").append(custId);
			List<StudySubject> studySubjects= getSession().createSQLQuery(query.toString()).addScalar("id",StandardBasicTypes.LONG).addScalar("sortingOrder",StandardBasicTypes.INTEGER).addScalar("name")
			.addScalar("subjectNumber").setResultTransformer( Transformers.aliasToBean(StudySubject.class)).list();
			if(ObjectFunctions.isNullOrEmpty(studySubjects))
				studySubjects=null;
			else
				Collections.sort(studySubjects);
			
			return (List<StudySubject>) studySubjects;
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getFeeCollecttionWithPercentageDetailsByClassWise(String tableName,long custId,long academicYearId,String classSectionIds,String perValue,String statusValue){
		try {
			/*StringBuffer queryBuff = new StringBuffer().append("select sum(SCF.feeAmount) feeAmount,(sum(feeAmount) * ("+Double.valueOf(perValue)+" /100.0)) as paidAmount,SUM(SPD.paymentAmount) paymentAmount,sum(SPD.discountAmount) as discountAmount,sum(SPD.concessionAmount) as concessionAmount,SPD.fullName,");
			queryBuff.append("SPD.admissionNumber,SPD.className,SPD.section,SPD.studentId,SPD.categoryId,SPD.mobileNumber,SPD.id from"); 
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append(" vw_studentClassFees");
			else if("vw_studentTransportFeeParticularsPayment".equalsIgnoreCase(tableName))
				queryBuff.append(" vw_studentTransportFees");
			queryBuff.append(" SCF");
			//queryBuff.append(" Left JOIN (Select (sum(paymentAmount)+sum(discountAmount)) as paymentAmount,sum(discountAmount) as discountAmount,fullName,admissionNumber,className,section,categoryId,studentId,mobileNumber,feeId,id");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append(" Left JOIN (Select sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,sum(paymentConcessionAmount) as concessionAmount, fullName,admissionNumber,className,section,categoryId,studentId,mobileNumber,feeId,id From vw_studentFeePaymentDetails");
			else if("vw_studentTransportFeeParticularsPayment".equalsIgnoreCase(tableName))
				queryBuff.append(" Left JOIN (Select sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,sum(paymentConcessionAmount) as concessionAmount, fullName,admissionNumber,className,section,categoryId,studentId,mobileNumber,transportFeeId as feeId,id From vw_studentTransportFeeParticularsPayment");
			queryBuff.append(" WHERE custId=").append(custId).append(" and academicYearId=").append(academicYearId);
			if (!StringFunctions.isNullOrEmpty(classSectionIds)) {
				queryBuff.append(" and classSectionId in").append(classSectionIds);
			}
			queryBuff.append(" group by classSectionId,studentId ) SPD ON SCF.studentId=SPD.studentId ");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append(" AND SCF.feeId=SPD.feeId");
			else if("vw_studentTransportFeeParticularsPayment".equalsIgnoreCase(tableName))
				queryBuff.append(" AND SCF.transportFeeId=SPD.feeId");
			queryBuff.append(" where SCF.custId=").append(custId).append(" and SCF.academicYearId=").append(academicYearId); 
			if (!StringFunctions.isNullOrEmpty(classSectionIds)) {
				queryBuff.append(" and classSectionId in").append(classSectionIds);
			
			}
			queryBuff.append(" and description is null");
			queryBuff.append(" group by SCF.classSectionId,SCF.studentId ");
			
			if("feeCollectionReport".equalsIgnoreCase(statusValue)){
				queryBuff.append(" having paymentAmount >= paidAmount ");//paymentAmount >= paidAmount 
			}else{
				queryBuff.append(" having paymentAmount < paidAmount ");//paymentAmount <= paidAmount
			}
			log.debug(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
					.addScalar("discountAmount").addScalar("concessionAmount").addScalar("paidAmount").addScalar("fullName").addScalar("admissionNumber").addScalar("className").addScalar("section").addScalar("studentId",StandardBasicTypes.LONG)
					.addScalar("mobileNumber").addScalar("id", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();*/
			StringBuffer queryBuff = new StringBuffer();
			queryBuff.append("SELECT S.id StudentId,CONCAT(p.firstName,' ',IFNULL(p.lastName,'')) fullName, p.mobileNumber mobileNumber, "
					+ "		a.admissionNumber,"
					+ "		sc.className,sc.section,sc.id classSectionId,"
					+ "        IFNULL(schRegularFee.SchoolFee,0) SchoolFee,"
					+ "		IFNULL(transportFee.TransportFee,0) TransportFee,"
					+ "		IFNULL(concessionFee.ConcessionAmount,0) concessionAmount,"
					+ "		(IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0)) feeAmount,"
					+ " 	((IFNULL(schRegularFee.SchoolFee, 0) + IFNULL(transportFee.TransportFee, 0)) * ("+Double.valueOf(perValue)+" /100.0)) as paidAmount,"
					+ "		IFNULL(SP.totalPaid,0.00) paymentAmount,"
					+ "        IFNULL(SP.totalDiscount,0.00) discountAmount,"
					+ "        IF((IFNULL(concessionFee.ConcessionAmount,0)+IFNULL(SP.totalPaid,0.00)+IFNULL(SP.totalDiscount,0.00))<="
					+ "			(IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0)),0,"
					+ "				(IFNULL(concessionFee.ConcessionAmount,0)+IFNULL(SP.totalPaid,0.00)+IFNULL(SP.totalDiscount,0.00))-"
					+ "				(IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0))) excessAmount,"
					+ "        (IFNULL(schRegularFee.SchoolFee,0)+IFNULL(transportFee.TransportFee,0)-"
					+ "        (IFNULL(concessionFee.ConcessionAmount,0)+IFNULL(SP.totalPaid,0.00)+IFNULL(SP.totalDiscount,0.00))) totalBalanceAmount"
					+ "	FROM student S"
					+ "    LEFT JOIN Account a ON (a.id = S.accountId)"
					+ "	LEFT JOIN Person p ON (p.id = a.personId)"
					+ "	LEFT JOIN studyClass sc ON (sc.id = S.classSectionId)"
					+ "	LEFT JOIN class cl ON (cl.id = sc.classNameClassId)"
					+ "    JOIN customer C ON S.custId=C.id AND C.status='Y'"
					+ "	JOIN academicYear AC ON S.academicYearId=AC.id AND AC.status='Y'"
					+ "	LEFT JOIN ("
					+ "		SELECT classId,categoryId,SUM(IFNULL(feeAmount,0)) SchoolFee FROM Fee"
					+ "		WHERE IFNULL(routeBoardingPointId,0)=0 AND IFNULL(classId,0)>0"
					+ "		GROUP BY categoryId,classId"
					+ "	) schRegularFee ON schRegularFee.classId=S.classNameClassId AND schRegularFee.categoryId=S.categoryId"
					+ "	LEFT JOIN ("
					+ "		select s.Id AS studentId,"
					+ "			sum(((ifnull(pbp.boardingPointFeeAmount, 0) / 2) + (ifnull(dbp.boardingPointFeeAmount, 0) / 2))) AS TransportFee"
					+ "		from"
					+ "			(((student s"
					+ "			join studentTransportDetails std ON ((s.Id = std.StudentId)))"
					+ "			left join routeBoardingPoints pbp ON ((std.PickupBoardingPointId = pbp.Id)))"
					+ "			left join routeBoardingPoints dbp ON ((std.DropBoardingPointId = dbp.Id)))"
					+ "		group by s.Id) transportFee ON transportFee.studentId=S.id"
					+ "	LEFT JOIN  ("
					+ "		SELECT SUM(IFNULL(concessionAmount,0)) ConcessionAmount,studentId FROM studentFeeConcession GROUP BY studentId"
					+ "	)concessionFee ON concessionFee.studentId=S.id"
					+ "    LEFT JOIN ("
					+ "		select SUM(paidAmount) totalPaid,SUM(discountAmount) totalDiscount,studentId from studentPayment WHERE deleteStatus = 'N' AND concessionStatus = 'N' GROUP BY studentId"
					+ "	) SP ON SP.studentId=S.id"
					+ "	WHERE S.status='Y'and S.description is null AND S.custId="+custId+" AND S.academicYearId="+academicYearId+" AND S.classSectionId IN "+classSectionIds
					//+ "    ORDER BY cl.sortingOrder,p.firstName ");
					+ "    group by classSectionId,S.id ");
			if("feeCollectionReport".equalsIgnoreCase(statusValue)){
				queryBuff.append(" having paymentAmount >= paidAmount ");//paymentAmount >= paidAmount 
			}else{
				queryBuff.append(" having paymentAmount < paidAmount ");//paymentAmount <= paidAmount
			}
			log.info(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
					.addScalar("discountAmount").addScalar("concessionAmount").addScalar("paidAmount").addScalar("fullName").addScalar("admissionNumber").addScalar("className").addScalar("section").addScalar("studentId",StandardBasicTypes.LONG)
					.addScalar("totalBalanceAmount", StandardBasicTypes.DOUBLE).addScalar("mobileNumber").setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public static String getGetAllInfo() {
		return GET_ALL_INFO;
	}
	// For SMS App  02/12/2014  Ramarao Jadapolu 
	// Changed by SUBBU on 22JUL15 for getting only one staff data.
	public List<Object[]> getStaffLeavesSummary(long staffId, long academicYearId){
		try {
			Query qry = getSession().createSQLQuery("CALL sp_staffLeavesSummary(:staffId, :academicYearId)")
			.setParameter("staffId", staffId)
			.setParameter("academicYearId", academicYearId);
			List resultList = qry.list();
			return (List<Object[]>) resultList;
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}

	/*public Object[] entryStudentCommittedPayment(Student student,double pendingAmount) {
		try {
			Query qry = this.getAll("CALL entryStudentCommittedFee(:studentId,:customerId,:classId,:committedFee,:categoryId,:academicYearId)")
					.setParameter("studentId", student.getId())
					.setParameter("customerId", student.getCustId())
					.setParameter("classId", student.getClassNameId())
					.setParameter("committedFee", pendingAmount)
					.setParameter("categoryId", student.getCategoryId())
					.setParameter("academicYearId", student.getAcademicYearId());
			log.debug(qry);
			//int row = qry.executeUpdate();
			
			List resultList = qry;
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				return  (Object[])resultList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			RayGunException raygex = new RayGunException();
			raygex.sendRayGunException(ex);
			raygex = null;
		}
		return null;
	}*/
	
	public List<ViewStudentFeePaymentDetails> getStudentFeeDefaultersList(long custId,long academicYearId,String classIds,String termsIs,String toDayDate){
		try {
			StringBuffer queryBuff = new StringBuffer().append("select sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,(sum(feeAmount)-(sum(paymentAmount)+sum(discountAmount))) as paidAmount,sum(discountAmount) as discountAmount,fullName,termName,admissionNumber,className,section,schoolTermId,dueDate,mobileNumber,id,classId,categoryId,settingName,routeBoardingPointId,committedFee,feeAmount as fineAmount,studentId from vw_studentFeePaymentDetails where custId=");
			queryBuff.append(custId).append(" and academicYearId=").append(academicYearId).append(" and dueDate <='"+toDayDate+" 00:00:00' ");//.append(" and status='Y'")
			if (!StringFunctions.isNullOrEmpty(classIds)) {
				queryBuff.append(" and classId in (").append(classIds).append(")");
				log.debug(" classIds"+classIds);
			}
			if (!StringFunctions.isNullOrEmpty(termsIs)) {
				queryBuff.append(" and schoolTermId in").append(termsIs);
			}
			queryBuff.append(" and deleteStatus='N'").append(" and paymentStatus='N'").append(" and paymentCommitFeeStatus='N' ").append(" and description is null ").append(" group by schoolTermId,studentId");
			//log.debug(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE).addScalar("paidAmount").addScalar("discountAmount").addScalar("committedFee", StandardBasicTypes.DOUBLE).addScalar("fineAmount", StandardBasicTypes.DOUBLE).addScalar("fullName").addScalar("termName").addScalar("admissionNumber").addScalar("className").addScalar("section").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("dueDate").addScalar("mobileNumber").addScalar("id", StandardBasicTypes.LONG).addScalar("classId", StandardBasicTypes.LONG).addScalar("categoryId",StandardBasicTypes.LONG).addScalar("settingName").addScalar("routeBoardingPointId", StandardBasicTypes.LONG).addScalar("studentId", StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	// For SMS App  02/12/2014  CVS 
	public List<Object[]> getStudentAttendanceSummaryDetails(long academicYearId,long accountId, String type){
		try {
			Query qry = getSession().createSQLQuery("CALL GetStudentAttendanceSummary(:academicYearId, :accountId, :type)")
			.setParameter("type", type)
			.setParameter("accountId", accountId)
			.setParameter("academicYearId", academicYearId);
			List resultList = qry.list();
			return (List<Object[]>) resultList;
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	//below updateHolidayStatus used to when update the class wise holidays update the WH to H. Because we are delete the 'W' status in schoolHolidays
	public void updateHolidayStatusForClassWiseHolidays(String table, long custId, long academicYearId){
		try{
	           StringBuffer sqlString=new StringBuffer();
	           sqlString.append("update "+table+" set classHolidayDescription='' "); 
	           sqlString.append(",classId=0");
	           sqlString.append(" where custId=");
	           sqlString.append(custId);
	           sqlString.append(" and academicYearId=");
	           sqlString.append(academicYearId);
	           sqlString.append(" and (status='WH' or status='H') ");
	           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	            if(row == 0)
	            {
	                log.debug("The no of rows Updated:"+ row);
	            }
	       }catch (Exception ex) {
	    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	       }
	}
	
	public List<Object[]> getStudentDailyAttendanceSP(long custId, long academicYearId,long classSectionId){
		try {
			Query qry = getSession().createSQLQuery("CALL sp_studentDailyAttendanceByClassId(:custId, :academicYearId, :classSectionId)")
			.setParameter("custId", custId)
			.setParameter("academicYearId", academicYearId)
			.setParameter("classSectionId", classSectionId);
			List resultList = qry.list();
			return (List<Object[]>) resultList;
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	
	@Override
	public List<ViewBudgetRequestDetails> getAllViewBudgetRequestDetailsByFinancialYearIdCustIdStatus(long financialYearId, long custId, String status) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("from ViewBudgetRequestDetails where ");
			queryString.append(" financialYearId =" + financialYearId);
			
			if(custId > 0)
				queryString.append(" and custId=" + custId);
			
			if(!StringFunctions.isNullOrEmpty(status))
			{
				if(status.length() > 1){
					queryString.append(" and (");
					String statusArr[] = status.split(",");
					for(String status1 : statusArr)
					{
						queryString.append(" status='" + status1 + "' OR ");
					}
					queryString.append(" status='UN' )");
				}else{
					queryString.append(" and status='");
					queryString.append(status);
					queryString.append("'");
				}
			}
			queryString.append(" order by requestedMonth");
			
			log.debug(queryString.toString());
			return (List<ViewBudgetRequestDetails>) this.getAllHqlQuery(queryString.toString());
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	@Override
	public List<ViewMeetingRequestDetails> getAllViewMeetingRequestDetailsByOrgIdCustIdDate(long orgId, long custId, String status) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("from ViewMeetingRequestDetails where ");
			queryString.append(" orgId =" + orgId);
			
			if(custId > 0)
				queryString.append(" and custId=" + custId);
			if(StringFunctions.isNotNullOrEmpty(status)){
				queryString.append(" AND DATE_FORMAT(meetingDate,'%Y-%m-%d') <= CURDATE() order by meetingDate  DESC");
			}else{
				queryString.append(" AND DATE_FORMAT(meetingDate,'%Y-%m-%d') >= CURDATE() order by meetingDate DESC");
			}
			log.debug(queryString.toString());
			return (List<ViewMeetingRequestDetails>) this.getAllHqlQuery(queryString.toString());
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List<Object[]> getMonthlyStudentAttendanceSP(int pMonth,long classSectionId, long custId, long academicYearId){
		try {
			Query qry = getSession().createSQLQuery("CALL sp_MonthlyStudentAttendance(:pMonth, :studyClassId, :custId, :acdYearId)")
			.setParameter("pMonth", pMonth)
			.setParameter("studyClassId", classSectionId)
			.setParameter("custId", custId)
			.setParameter("acdYearId", academicYearId);
			return (List<Object[]>) qry.list();
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	 public void insertStudyClassIdInsteadOfClassId(long studyClassId, long examTypeId){
		  try{
			    log.debug("Started for the studyClassId of "+studyClassId+" insertion.....");
			    Query query = getSession().createSQLQuery("INSERT INTO classSectionExamTypes(classSectionId,examTypeId) VALUES(:classSectionId,:examTypeId)");
			    	query.setParameter("examTypeId",examTypeId);
			    	query.setParameter("classSectionId", studyClassId);
			    	query.executeUpdate();
				log.debug("Completed for the custId of "+studyClassId+" insertion.....");
	      }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
	    }
		public void updateSelectedCustomersOrgId(String selectedId,Long orgId, String tempString){
			try{
				
				if(!ObjectFunctions.isNullOrEmpty(selectedId)){
					StringBuffer sqlString=new StringBuffer();
			           sqlString.append("update customer set orgId=");
			           sqlString.append(orgId);
			           sqlString.append(" where id in(");
			           sqlString.append(selectedId);
			           sqlString.append(")");
			           int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
			            if(row == 0)
			            {
			                log.debug("The no of rows Updated:"+ row);
			            }
				}if(!ObjectFunctions.isNullOrEmpty(tempString)){
				
		            StringBuffer sqlString1=new StringBuffer();
		            sqlString1.append("update customer set orgId=0");
		            sqlString1.append(" where id in(");
		            sqlString1.append(tempString);
		            sqlString1.append(")");
			           int row1 = getSession().createSQLQuery(sqlString1.toString()).executeUpdate();
			           if(row1 == 0)
			            {
			                log.debug("The no of rows Updated:"+ row1);
			            }
				}
		       }catch (Exception ex) {
		    	   ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		       }
		}
		
		public List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeIdAndSubTypeId(long studId,long examTypeId,long subtypeId){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	           queryString.append("from ViewStudentMarks sMarks where sMarks.studId = ");
	           queryString.append(studId);	
	           queryString.append(" and sMarks.examTypeId= ");
	           queryString.append(examTypeId);
	           queryString.append(" and sMarks.subTypeId= ");
	           queryString.append(subtypeId);
	           queryString.append(" group by sMarks.scheduleId");
	           log.debug(queryString.toString());
	           return (List<ViewStudentMarks>) this.getAllHqlQuery(queryString.toString());
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return null;
		}
		
		public List<ViewStudentFeePaymentDetails> getStudentsFeePaidDetailsBySettingAndClassIdAndFinanceUserId(String tableName,long custId,long academicYearId,String classSectionIds,String fromDate,String toDate,String feeSettingIds,long financeUserId){
			try{
				StringBuffer query = new StringBuffer("select studentId,paymentDate,invoiceNumber,fullName,className,section,admissionNumber,paymentAmount,feeTypeId from "+tableName+" where custId=")
				.append(custId).append(" and academicYearId=").append(academicYearId).append(" and financeUserId=").append(financeUserId).append(" and classSectionId in").append(classSectionIds).append( " and paymentDate>='").append(fromDate).append(" 00:00:00' and paymentDate<='")
				.append(toDate).append(" 00:00:00' and studentPaymentId!=0 and feeSettingId in").append(feeSettingIds).append(" and paymentCommitFeeStatus='N' ").append(" and status='Y'").append(" order by sortingOrder,invoiceNumber,studentId,paymentDate,studentPaymentId");
				return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString()).addScalar("studentId",StandardBasicTypes.LONG).addScalar("paymentDate",StandardBasicTypes.DATE).addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("fullName").addScalar("className").addScalar("section")
				.addScalar("admissionNumber").addScalar("paymentAmount",StandardBasicTypes.DOUBLE).addScalar("feeTypeId",StandardBasicTypes.LONG).setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
		public void removeAllSchoolHolidaysByDescriptionAndStartAndEndDate(long custId,String description,String startDate,String endDate) {
			try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from schoolHolidays where custId="+custId+" and description='"+description+"' and startDate='"+startDate+"' and endDate='"+endDate+"'" );
				int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
				if(row == 0)
	            {
	                log.debug("The no of rows deleted:"+ row);
	            }
                /*StringBuffer sqlString=new StringBuffer();
                StringBuffer query =  new StringBuffer("select id from schoolHolidays where custId="+custId+" and description='").append(description).append("' and startDate='").append(startDate).append("' and endDate='").append(endDate).append("'");
				List<Object[]> schoolHolidayIds = this.getAll(query.toString());
				query = null;
				String schoolHolidayIdsString=null;
				if (ObjectFunctions.isNotNullOrEmpty(schoolHolidayIds)) 
					schoolHolidayIdsString = StringFunctions.convertListToCommaDelimitedString(schoolHolidayIds);
				else
					schoolHolidayIdsString="0";
				log.debug(schoolHolidayIdsString);
				sqlString.append("delete from schoolHolidays where id in ("+schoolHolidayIdsString+")");
				int row = this.getAll(sqlString.toString()).executeUpdate();
				if(row == 0)
	            {
	                log.debug("The no of rows deleted:"+ row);
	            }*/
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
		}
		/*public String removeAllSchoolHolidaysByDescriptionAndStartAndEndDate(long custId,String description,String startDate,String endDate) {
		  try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from schoolHolidays where custId="+custId+" and description='"+description+"' and startDate='"+startDate+"' and endDate='"+endDate+"'" );
				Query qry = this.getAll(sqlString.toString());
				@SuppressWarnings("unused")
				int row = qry.executeUpdate();
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
	    }*/
	  public List checkCustomerShortName(String shortName) {
	        try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from Customer where ");
	            queryString.append(" customerShortName ='");
	            queryString.append(shortName);
	            queryString.append("'");
	            List personNames =  this.getAllHqlQuery(queryString.toString());
	            
	            if(!ObjectFunctions.isNullOrEmpty(personNames))
	            {
	                Collections.sort(personNames);
	                return personNames;
	            }
	            return null;
	        } catch (RuntimeException ex) {
	            log.error("Get failed", ex);
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	            throw ex;
	        }
		} 
	  
	  public void updateAndGetTopStudentsMarksDetails(long custId,long academicYearId){
			Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
			try {
				StringBuffer sqlString = new StringBuffer();
				Query qry = session.createSQLQuery("CALL topStudentMarksDetails(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);
				log.debug(qry);
				int row = qry.executeUpdate();
				 session.flush();tx.commit();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
		}
	  
	  /*public void updateAndGetTopStudentsMarksDetails(long custId,long academicYearId){
		  try{
				Query qry = getSession().createSQLQuery("CALL topStudentMarksDetails(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);
				log.debug(qry);
				int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	 }*/
	  
	  public void updateAndGetTopStaffPerformanceDetails(long custId,long academicYearId){
			Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
			try {
				StringBuffer sqlString = new StringBuffer();
				Query qry = session.createSQLQuery("CALL topTeacherPerformanceDetails(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);
				log.debug(qry);
				int row = qry.executeUpdate();
				 session.flush();tx.commit();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
		}
	  
	  
	 /*public void updateAndGetTopStaffPerformanceDetails(long custId,long academicYearId){
		  try{
				Query qry = getSession().createSQLQuery("CALL topTeacherPerformanceDetails(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);
				log.debug(qry);
				int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}*/
	  
	  public void updateAndGetTopStudentsMonthlyAttendanceDetails(long custId,long academicYearId){
			Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
			try {
				StringBuffer sqlString = new StringBuffer();
				Query qry = session.createSQLQuery("CALL topStudentsMonthlyAttendance(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);
				log.debug(qry);
				int row = qry.executeUpdate();
				 session.flush();tx.commit();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
		}
	  
	  
	 /*public void updateAndGetTopStudentsMonthlyAttendanceDetails(long custId,long academicYearId){
		  try{
				Query qry = getSession().createSQLQuery("CALL topStudentsMonthlyAttendance(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);
				log.debug(qry);
				int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}*/
	  
	  public void updateAndGetTopStudentsDailyAttendanceDetails(long custId,long academicYearId){
			Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
			try {
				StringBuffer sqlString = new StringBuffer();
				Query qry = session.createSQLQuery("CALL topStudentsDailyAttendance(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);
				log.debug(qry);
				int row = qry.executeUpdate();
				 session.flush();tx.commit();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}finally {
				if(session != null) {
				session.close(); // extra work 
				} 
			}
		}
	  
	 /*public void updateAndGetTopStudentsDailyAttendanceDetails(long custId,long academicYearId,int monthId){
		  try{
				//Query qry = getSession().createSQLQuery("CALL topStudentsDailyAttendance(:custId,:academicYearId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId);//.setParameter("monthId", monthId);
				Query qry = getSession().createSQLQuery("CALL topStudentsDailyAttendance(:custId,:academicYearId,:monthId)").setParameter("custId", custId).setParameter("academicYearId", academicYearId).setParameter("monthId", monthId);
				log.debug(qry);
				int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	}*/
	  public void updateStudentPaymentAndConfifuredStatusForSchoolFee(long classId,long categoryId,String status) {
			try {
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("update student set feePaidStatus='"+ status + "',feeConfigured='"+ status + "' where classNameClassId="+ classId +" and categoryId="+categoryId);
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
	  public List<Object[]> getCustomerFeePaidAndUnpaiddetailSummary(){
			try {
				Query qry = getSession().createSQLQuery("CALL customerWiseCollectionAndPendingAmountDetailsSummaryMonthWise()");
				List resultList = qry.list();
				return (List<Object[]>) resultList;
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
		}
	  
	  public List<Integer[]> getMarksIdByStudentIdAndExamScheduleId(long studentId,long examScheduleId){
		  try{
				Query qry = getSession().createSQLQuery("CALL getMarksIdByStudentIdAndExamScheduleId(:studentId,:examScheduleId)").setParameter("studentId", studentId).setParameter("examScheduleId", examScheduleId);
				List resultList = qry.list();
				if(!ObjectFunctions.isNullOrEmpty(resultList))
				return (List<Integer[]>) resultList;
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
		  return null;
	}
	  
   public void studentFeePaidStatusForTransport(long studentId,long custId,long academicYearId,long categoryId){
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("update student set feePaidStatus='P' where id="+ studentId +" and custId="+custId+" and academicYearId="+academicYearId+" and categoryId="+categoryId);
			Query qry = getSession().createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	 public void updateFeeConfiguredStatusInStudentForTransport(long boardingPointId,long categoryId,String status,long studentId){
		Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		try {
			StringBuffer sqlString = new StringBuffer();
			if(boardingPointId>0)
				sqlString.append("update student set feeConfigured='"+ status + "' where boardingPointId="+ boardingPointId +" and categoryId="+categoryId);
			if(studentId>0)
				sqlString.append("update student set feeConfigured='"+ status + "' where id="+ studentId +" and categoryId="+categoryId);
			Query qry = session.createSQLQuery(sqlString.toString());
			log.debug(qry);
			int row = qry.executeUpdate();
			 session.flush();tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally {
			if(session != null) {
			session.close(); // extra work 
			} 
		}
	}
	  public void updateStudentPaymentStatusForTransport(long boardingPointId,long categoryId,String status,long studentId) {
			try {
				StringBuffer sqlString = new StringBuffer();
				if(boardingPointId>0){
					sqlString.append("update student set feePaidStatus='"+ status + "' where boardingPointId="+ boardingPointId +" and categoryId="+categoryId);
					if("P".equalsIgnoreCase(status))
						sqlString.append(" and feePaidStatus in('F','C') ");
				}
				if(studentId>0){
					sqlString.append("update student set feePaidStatus='"+ status + "' where id="+ studentId +" and categoryId="+categoryId);
				}
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
	  public void updateStudentPaymentAndConfifuredStatusForTransport(long boardingPointId,long categoryId,String status,long studentId) {
			try {
				StringBuffer sqlString = new StringBuffer();
				if(boardingPointId>0)
					sqlString.append("update student set feePaidStatus='"+ status + "',feeConfigured='"+ status + "' where boardingPointId="+ boardingPointId +" and categoryId="+categoryId);
				if(studentId>0)
					sqlString.append("update student set feePaidStatus='"+ status + "',feeConfigured='"+ status + "' where id="+ studentId +" and categoryId="+categoryId);

				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
	  //If student is assigned to transport then need to change feeConfigured and feePaidStatus
		public void updateStudentPaymentStatusForTransportFee(long boardingPointId,long categoryId){
			Fee fee =(Fee)this.get(Fee.class, "routeBoardingPointId="+boardingPointId+" and categoryId="+categoryId+" and feeAmount != 0");
			if(!ObjectFunctions.isNullOrEmpty(fee)){
				/* If we update any or create any fee particular we will update feeConfigured column as "Y" and we will update student feePaidStatus as "P". Who are have "F" status in feePaidStatus */
				this.updateFeeConfiguredStatusInStudentForTransport(boardingPointId,categoryId,"Y",0);
				/* Here we have update student feePaidStatus "F" if student paid all terms fee even we have remove transport fee with boardingPointId(single student) */
				this.updateStudentPaymentStatusForTransport(boardingPointId,categoryId,"P",0);
			}else {
				this.updateStudentPaymentAndConfifuredStatusForTransport(boardingPointId,categoryId,"N",0);
				this.updateStudentPaymentStatusForTransport(boardingPointId,categoryId,"N",0);

			}
		}
		// If student is unassigned to transport then we need to change the fee Paid status
		public void studentFeePaidStatusWithTransport(long studentId,long custId,long academicYearId,long categoryId,long classId){
			Fee fee =(Fee)this.get(Fee.class, "classId="+classId+" and categoryId="+categoryId+" and feeAmount != 0");
			if(!ObjectFunctions.isNullOrEmpty(fee)){
				// If we have updated any or create any fee particular we will update feeConfigured column as "Y" and we will update student feePaidStatus as "P". Who are have "F" status in feePaidStatus 
				this.updateFeeConfiguredStatusInStudentForTransport(0,categoryId,"Y",studentId);
				//Here we have update student feePaidStatus "F" if student paid all terms fee even we have remove transport fee with studentId(single student)
				List<Object[]> stdFeeObject = null;
				stdFeeObject = this.getAll("select paymentStatus,studentId from vw_studentFeePaymentDetails where custId="+custId+" and academicYearId="+academicYearId+" and studentId="+studentId+" and paymentStatus='"+Constants.NO_STRING+"'");
				if(ObjectFunctions.isNullOrEmpty(stdFeeObject)){
					this.updateStudentPaymentStatusForTransport(0,categoryId,"F",studentId);
				}
			}else {
				//Here we have update student feePaidStatus  and feeConfigured status "N" if student didn't configured any terms
				this.updateStudentPaymentAndConfifuredStatusForTransport(0,categoryId,"N",studentId);
				this.updateStudentPaymentStatusForTransport(0,categoryId,"N",studentId);
			}
		}

	public void removeFee(long feeId) {
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("delete from Fee where id=");
			sqlString.append(feeId);
			Query qry = getSession().createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	public void updateStudentsFeePaidStatusClassWise(long custId,long categoryId,long classId){
		try {
			String studenIds=null;
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select group_concat(CONVERT(studentId,CHAR)) from vw_studentFeePaymentDetails where custId="+custId+" and classId="+classId+" and categoryId="+categoryId+" and paymentStatus ='N'");
			List resultList=this.getAll(sqlQuery.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					studenIds= resultList.get(0).toString();
				}
			}
			StringBuilder sqlString = new StringBuilder("update student set feePaidStatus='F' where custId="+custId+" and classNameClassId="+classId+" and categoryId="+categoryId+" and id not in (");
			sqlString.append(studenIds).append(")");
			//sqlString.append("(select studentId from vw_studentFeePaymentDetails where custId="+custId+" and classId="+classId+" and categoryId="+categoryId+" and paymentStatus ='N' group by feeId,studentId)");
			Query qry = getSession().createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
	
	public boolean removeStudyClassSubjectAssignedTecher(long custId,long academicYearId, long studyClassId, long subjectId,long teacherId) {
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("delete from classTeacher where custId=");
			sqlString.append(custId);
			sqlString.append(" and academicYearId=");
			sqlString.append(academicYearId);
			sqlString.append(" and studyClassId=");
			sqlString.append(studyClassId);
			sqlString.append(" and studySubjectId=");
			sqlString.append(subjectId);
			sqlString.append(" and teacherId=");
			sqlString.append(teacherId);
			Query qry = getSession().createSQLQuery(sqlString.toString());
			int row = qry.executeUpdate();
			if (row > 0)
				return true;
			else
				return false;

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return false;
	}
		
	public List<ViewStudentsLatestExamMarksDetails> getLatestExamsClasses(long custId,long academicYearId){
		try{
			StringBuffer query = new StringBuffer("select vw.* from (select * from vw_studentsLatestExamMarksDetails where custId="+custId+" and academicYearId="+academicYearId+" order by examStartDate desc) vw group by vw.classNameClassId order by  vw.examStartDate desc,vw.className Asc");
			log.debug(query.toString());
			return (List<ViewStudentsLatestExamMarksDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("className").addScalar("classSectionId",StandardBasicTypes.LONG).addScalar("classNameClassId",StandardBasicTypes.LONG).addScalar("section").addScalar("examTypeId",StandardBasicTypes.LONG).addScalar("examTypeName").addScalar("marksId",StandardBasicTypes.LONG)
			.addScalar("custId",StandardBasicTypes.LONG).addScalar("academicYearId",StandardBasicTypes.LONG).addScalar("examStartDate",StandardBasicTypes.DATE).addScalar("lastUpdatedDate",StandardBasicTypes.DATE).addScalar("minMarks")
			.addScalar("studId",StandardBasicTypes.LONG).addScalar("obtainedMarks",StandardBasicTypes.DOUBLE).addScalar("subjectId",StandardBasicTypes.LONG).addScalar("present",StandardBasicTypes.STRING).addScalar("status",StandardBasicTypes.STRING)
			.setResultTransformer( Transformers.aliasToBean(ViewStudentsLatestExamMarksDetails.class)).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	    
	public List<ViewClassExamDetails>  getAllSectionsExamSchedulesDetails(long custId,long academicYearId,String classSectionIds,long examId,String subtypeIds){
		try{
			StringBuffer query = new StringBuffer("select group_concat(scheduleId) as description,name,subTypeName,subTypeId from vw_classExamDetails  where custId="+custId+" and academicYearId="+academicYearId+" and classSectionId in "+classSectionIds+" and eid="+examId);
			if(!StringFunctions.isNullOrEmpty(subtypeIds))
				query.append(" and subTypeId in "+subtypeIds); 
			query.append(" GROUP BY subTypeId,classSubjectId,classSubjectId  order by subTypeId,classSubjectId");
				log.debug(query.toString());
			return (List<ViewClassExamDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("description").addScalar("name").addScalar("subTypeName").addScalar("subTypeId",StandardBasicTypes.LONG).setResultTransformer( Transformers.aliasToBean(ViewClassExamDetails.class)).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	
	}
	public List<ScoreCardTemplates> getAllScorecardTemplatesByAcademicYearId(long custId,long academicYearId){
		try {
			StringBuffer query = new StringBuffer("select * from scoreCardTemplates WHERE custId=").append(custId).append(" and examTypeId in (select id from examTypes where custId=").append(custId).append(" and academicYearId=").append(academicYearId).append(")");
			return (List<ScoreCardTemplates>) getSession().createSQLQuery(query.toString()).addEntity(ScoreCardTemplates.class).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	 public Object[] getAllStudentsByClassNameAndCastAndSubCastName(long custId,long academicYearId,List<CastSettings> castList, String classSectionIds,String userName){
		  try{
			  /*select className,section,sum(gender='M'),sum(gender='F'),castId,COUNT(*) total from vw_studentDetails where custId=7  and academicYearId =7 GROUP BY classNameClassId,castId;*/
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select ");
				if(!ObjectFunctions.isNullOrEmpty(castList)){
					//queryBuff.append("count(*) GrandTotal,");
					int i=1;
					boolean areSubCastes = false;
					for(CastSettings castSettings:castList){
						if(!ObjectFunctions.isNullOrEmpty(castSettings.getSubCastSettings())){
							if(!areSubCastes)
								areSubCastes=true;
							for(SubCastSettings subCastSettings:castSettings.getSubCastSettings()){
							queryBuff.append("sum(castId="+castSettings.getId()+" and subCastId="+subCastSettings.getId()+")");						
							queryBuff.append(",");
							}
							queryBuff.append("sum(castId="+castSettings.getId()+" and (");
							int j=1;
							for(SubCastSettings subCastSettings1:castSettings.getSubCastSettings()){
								queryBuff.append("subCastId="+subCastSettings1.getId());
								if(j < castSettings.getSubCastSettings().size())
								queryBuff.append(" || ");
								j++;
							}
							queryBuff.append("))");
							if(i < castList.size())
							queryBuff.append(",");
							i++;
					}
				}
					if(areSubCastes){
						queryBuff.append("''");
					queryBuff.append(" from vw_studentDetails where custId=");
					queryBuff.append(custId);	
					queryBuff.append(" and academicYearId =");
					queryBuff.append(academicYearId);
					if(userName.equalsIgnoreCase("ROLE_HOSTEL")){
						queryBuff.append(" and hostelMode ='H'");
					}
					queryBuff.append(" and classSectionId in");
					queryBuff.append(classSectionIds);
					queryBuff.append(" and status='Y'");
					queryBuff.append(" and description is null");
					//System.out.println("Cast Query:" + queryBuff.toString());
					List resultList=this.getAll(queryBuff.toString());
					if (!ObjectFunctions.isNullOrEmpty(resultList)) {
						return  (Object[])resultList.get(0);
					}
				}
				return null;
			}
		  }
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;		
			}
			return null;
	}
	 /**
	  * 
	  */
	 public List<VWStudentDailyAttendance> getStudentsAttendanceByClassSectionIdAndAttendanceDateForPreSchool(long classSectionId, String attendanceDate){
			try{
				StringBuffer query = new StringBuffer("select stud.rollNumber,stud.fullName as studentname,stud.studId as studentId,stud.custId,stud.academicYearId,")
				.append("stud.accountId,stud.classSectionId,stud.classId,stud.status,")
				.append("stud.admissionNumber,stud.mobileNumber,sdatt.inTime,sdatt.outTime")
				.append(" from vw_studentPersonInfo as stud ")
				.append("LEFT JOIN studentDailyAttendanceTimeTrack sdatt on (sdatt.studentId =  stud.studId and sdatt.attendanceDate='"+attendanceDate+"')")
				.append(" where stud.studDiscontinueDesc is null and stud.classSectionId=").append(classSectionId);
				log.debug(query.toString());
				 return (List<VWStudentDailyAttendance>) getSession().createSQLQuery(query.toString())
				 .addScalar("rollNumber",StandardBasicTypes.STRING).addScalar("studentname").addScalar("studentId",StandardBasicTypes.LONG).addScalar("custId",StandardBasicTypes.LONG)
				 .addScalar("academicYearId",StandardBasicTypes.LONG).addScalar("accountId",StandardBasicTypes.LONG).addScalar("classSectionId",StandardBasicTypes.LONG).addScalar("classId",StandardBasicTypes.LONG)
				 .addScalar("status",StandardBasicTypes.STRING).addScalar("admissionNumber", StandardBasicTypes.STRING) .addScalar("mobileNumber", StandardBasicTypes.STRING).addScalar("inTime", StandardBasicTypes.TIMESTAMP).addScalar("outTime", StandardBasicTypes.TIMESTAMP)
				  .setResultTransformer( Transformers.aliasToBean(VWStudentDailyAttendance.class)).list()
				  ;
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	 /**
	  * 
	  * @param academicYearId
	  * @param accountId
	  * @param type
	  * @return
	  */
	 public List<Object[]> getPreSchoolStudentAttendanceSummaryDetails(long academicYearId,long accountId, String type){
			try {
				Query qry = getSession().createSQLQuery("CALL GetPreSchoolStudentAttendanceSummary(:academicYearId, :accountId, :type)")
				.setParameter("type", type)
				.setParameter("accountId", accountId)
				.setParameter("academicYearId", academicYearId);
				List resultList = qry.list();
				return (List<Object[]>) resultList;
			} catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return null;
		}
	 public List<ClassTeacher> getClassTeachersListByStaffId(String query)
	 {
		 try{
			 List classTeacherList=  (List<ClassTeacher>) getSession().createSQLQuery(query.toString()).addEntity(ClassTeacher.class).list();
		     if(!ObjectFunctions.isNullOrEmpty(classTeacherList))
		     {
		  	   return classTeacherList;
		     }
			}
	    catch (Exception e) {
		e.printStackTrace();
		}
	  	return null;
	 }
	 
	 public List<ViewStudentMarks> getStudentMarksByStudentIdAndExamtypeIdAndScheduleId(long studId,long examTypeId,long custId,long academicYearId,long scheduleId){
			try 
			{
	           StringBuffer queryString = new StringBuffer();
	          // queryString.append("from ViewStudentMarks where studId = ");
	           queryString.append(" custId = ");
	           queryString.append(custId);	
	           queryString.append(" and academicYearId = ");
	           queryString.append(academicYearId);	
	           queryString.append(" and studId = ");
	           queryString.append(studId);	
	           queryString.append(" and examTypeId= ");
	           queryString.append(examTypeId);
	           queryString.append(" and scheduleId= ");
	           queryString.append(scheduleId);
	           queryString.append(" group by scheduleId");
	           
	           StringBuffer query = new StringBuffer("select * from vw_studentMarks WHERE ").append(queryString.toString());
	   		   return (List<ViewStudentMarks>) getSession().createSQLQuery(query.toString()).addEntity(ViewStudentMarks.class).list();
	   		
	          // return (List<ViewStudentMarks>) this.getAllHqlQuery(queryString.toString());
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return null;
		}
	 public List getAllStaffMobileNumbersByRole(long custId,String roleIds){
		 
		 StringBuffer sqlString = new StringBuffer();
			sqlString.append("SELECT P.mobileNumber FROM Person P JOIN Account A ON A.PersonId = P.id AND A.custId = "
					+ custId
					+ " JOIN UserRoles UR ON UR.userId= A.id WHERE LENGTH(P.mobileNumber)>=10 AND UR.roleId in ("
					+ roleIds
					+ " )");
			
			return this.getAll(sqlString.toString());
	 }
	 
	 public List<StudyClass> getStudyClasses(String queryString){
			try 
			{ 
	           StringBuffer query = new StringBuffer("select * from studyClass WHERE ").append(queryString.toString());
	   		   return (List<StudyClass>) getSession().createSQLQuery(query.toString()).addEntity(StudyClass.class).list();
	   		
	        } catch (Exception ex) {
	        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return null;
		}
	@Override
	/**
	 * This Method is used to get the all student payment details based on the paymentId send by the desktop application.
	 */
	public List<VWStudentPaymentDetails> getStudentPaymentDetails(long custId, long academicYearId, long paymnetId) {
		StringBuffer query = new StringBuffer("SELECT sp.Id AS StudentPaymentId,  sp.invoiceNumber  AS InvoiceNumber,DATE(sp.paymentDate) PaidDate,	IFNULL(sp.paidAmount,0.00) PaidAmount,");
					 query.append(" IFNULL(sp.discountAmount,0.00) DiscountAmount,	'0.00' AS DueAmount,CASE sp.paymentType		WHEN 'C' THEN 'CA'	WHEN 'CH' THEN 'CQ'	WHEN 'D' THEN 'DD'		WHEN 'NEFT' THEN 'NR'	ELSE IFNULL(sp.paymentType ,'CA')	END AS PaymentType,	");
					 query.append(" sp.studentId StudentId,bm.bankName BankName,	sp.branchName BranchName,IFNULL(sp.chequeNumber,sp.ddNumber) AS InstrumentNumber,DATE(IFNULL(sp.chequeIssuedDate,sp.ddDrawnDate)) AS InstrumentDate,");
					 query.append("	sp.deleteStatus DeleteStatus,dsp.deleteRemarks DeleteCause,	spd.id AS StudentFeePaidDetailsId,	IFNULL(spd.paymentAmount,0) SPD_PaymentAmount,	IFNULL(spd.discountAmount,0.00) SPD_DiscountAmount,");
					 query.append(" spd.feeId SPD_ClassFeeId,ep.id ExcessPaymentId,IFNULL(ep.excessAmount,0) ExcessAmount,ep.status AS IsUsed, ep.usedPaymentId AS UsedPaymentId,spd.studTransportDetailsId spd_TransportFeeId,IFNULL(sp.dpPaymentDetailsId,0) dpPaymentDetailsId,");
					 query.append(" IFNULL(cp.id,0) CP_challanaId,cp.challanaNumber as CP_challanaNumber,cp.deleteStatus as CP_deleteStatus,cp.challanaDate as CP_challanaDate,cp.receivedDate as CP_receivedDate");
					 query.append( "   FROM studentPayment sp ");
					 query.append( " JOIN studentFeePaidDetails spd ON sp.id=spd.StudentPaymentId LEFT JOIN excessPayment ep ON sp.id= ep.PaymentId LEFT JOIN bankMaster bm ON (sp.bankId=bm.id)");
					 query.append( " LEFT JOIN deleteStudentPayment dsp ON dsp.studentPaymentId=sp.id ");
					 query.append(" LEFT JOIN challanaPayment cp ON cp.studentPaymentId=sp.id");
					 query.append(" WHERE sp.concessionStatus='N' AND sp.custId=");
					 query.append( custId);
					 query.append( " AND sp.academicYearId=");
					 query.append( academicYearId);
					 if(paymnetId > 0){
						 query.append( " AND (sp.id > "	+ paymnetId	+ " OR sp.lastUpdatedDate >= (SELECT createdDate FROM studentPayment WHERE id= "+ paymnetId	+ "))");
					 }
					 query.append( " ORDER BY sp.id")	;
				
		log.debug(query.toString());
		 return (List<VWStudentPaymentDetails>) getSession().createSQLQuery(query.toString())
		 .addScalar("studentPaymentId",StandardBasicTypes.LONG).addScalar("invoiceNumber",StandardBasicTypes.LONG).addScalar("PaidDate",StandardBasicTypes.DATE)
		 .addScalar("paidAmount",StandardBasicTypes.DOUBLE).addScalar("discountAmount",StandardBasicTypes.DOUBLE).addScalar("dueAmount",StandardBasicTypes.DOUBLE).
		 addScalar("paymentType",StandardBasicTypes.STRING).addScalar("studentId",StandardBasicTypes.LONG).addScalar("bankName", StandardBasicTypes.STRING).
		 addScalar("branchName", StandardBasicTypes.STRING).addScalar("instrumentNumber", StandardBasicTypes.STRING).addScalar("instrumentDate", StandardBasicTypes.DATE).
		 addScalar("deleteStatus", StandardBasicTypes.STRING).addScalar("deleteCause", StandardBasicTypes.STRING).addScalar("studentFeePaidDetailsId", StandardBasicTypes.LONG).
		 addScalar("spd_PaymentAmount", StandardBasicTypes.LONG).addScalar("spd_DiscountAmount", StandardBasicTypes.DOUBLE).addScalar("spd_ClassFeeId", StandardBasicTypes.LONG).
		 addScalar("excessPaymentId", StandardBasicTypes.LONG).addScalar("excessAmount", StandardBasicTypes.DOUBLE).addScalar("isUsed", StandardBasicTypes.BOOLEAN).
		 addScalar("usedPaymentId", StandardBasicTypes.LONG).addScalar("spd_TransportFeeId", StandardBasicTypes.LONG).addScalar("dpPaymentDetailsId", StandardBasicTypes.LONG).
		 addScalar("CP_challanaId",StandardBasicTypes.LONG).addScalar("CP_challanaNumber",StandardBasicTypes.LONG).addScalar("CP_deleteStatus",StandardBasicTypes.STRING).
		 addScalar("CP_challanaDate",StandardBasicTypes.DATE).addScalar("CP_receivedDate",StandardBasicTypes.DATE)
		 .setResultTransformer( Transformers.aliasToBean(VWStudentPaymentDetails.class)).list();
	}
	
	public List<ExamTypes> getExamTypesByStudyClassId(long studyClassId){
		try 
		{ 
           return (List<ExamTypes>) getSession().createSQLQuery("SELECT e.* FROM examTypes e LEFT JOIN classSectionExamTypes cs ON(cs.examTypeId = e.id) WHERE cs.classSectionId ="+studyClassId+" ORDER BY e.sortingOrder").addEntity(ExamTypes.class).list();
   		
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}
 
	public List<StudySubject> getSubjectsByStudyClassId(long studyClassId){
		try 
		{ 
           return (List<StudySubject>) getSession().createSQLQuery("SELECT ss.* FROM studySubject ss LEFT JOIN ClassSubject cs ON(cs.subjectId = ss.id) WHERE cs.studyClassId ="+studyClassId+" ORDER BY ss.sortingOrder").addEntity(StudySubject.class).list();
   		
        } catch (Exception ex) {
        	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return null;
	}
	public List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeePaymentDetails(long studentId, long academicYearId, long feeSettingId)
	{
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,IF(SUM(IF(paymentStatus ='N', 1 , 0))>0,'N','P') as paymentStatus,dueDate from (select schoolTermId,feeAmount,paymentAmount,discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,paymentStatus,dueDate from vw_studentTransportFeeParticularsPayment where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and deleteStatus='"+Constants.NO_STRING+"' ").append(" and feeSettingId =").append(feeSettingId).append(" order by paymentStatus) feeDetails group by schoolTermId order by dueDate ASC");
			/*StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName,paymentStatus from vw_studentFeePaymentDetails where studentId=").append(studentId)
			.append(" and academicYearId=").append(academicYearId).append(" and feeSettingId in ").append(feeSettingId).append(" group by schoolTermId order by feeSettingId,schoolTermId");*/
			log.debug(query.toString());
			return (List<ViewStudentTransportFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName").addScalar("paymentStatus").addScalar("dueDate")
			.setResultTransformer( Transformers.aliasToBean(ViewStudentTransportFeePaymentDetails.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List<ViewStudentTransportFeePaymentDetails> getStudentTransportFeeDetails(long studentId,long academicYearId,long termId){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,transportFeeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,paymentStatus,concessionAmount from vw_studentTransportFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='N' group by transportFeeId");
			log.debug(query.toString());
			 return (List<ViewStudentTransportFeePaymentDetails>) getSession().createSQLQuery(query.toString())
					 .addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("transportFeeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate").
			  addScalar("paymentStatus").addScalar("concessionAmount").setResultTransformer(Transformers.aliasToBean(ViewStudentTransportFeePaymentDetails.class)).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentTransportFeePaymentDetails> getAllStudentTermsWiseTransportFeeViewPaymentDetails(long studentId, long academicYearId, long feeSettingId) {
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName from vw_studentTransportFeeParticularsPayment where studentId=").append(studentId)
			.append(" and academicYearId=").append(academicYearId).append(" and feeSettingId =").append(feeSettingId).append(" group by schoolTermId order by feeSettingId,schoolTermId");
			log.debug(query.toString());
			return (List<ViewStudentTransportFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName")
			.setResultTransformer( Transformers.aliasToBean(ViewStudentTransportFeePaymentDetails.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List<ViewStudentTransportFeePaymentDetails> getAllStudenTransportFeePaymentDetails(long studentId, long academicYearId, long termId) {
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,transportFeeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,sum(paymentConcessionAmount) as paymentConcessionAmount,concessionAmount from vw_studentTransportFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='N' group by transportFeeId");
			log.debug(query.toString());
			 return (List<ViewStudentTransportFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("transportFeeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate").addScalar("paymentConcessionAmount").addScalar("concessionAmount")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentTransportFeePaymentDetails.class)).list();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public ViewStudentTransportFeePaymentDetails getStudentNonPaidTransportFeePaymentDetails(long studentId,long academicYearId,long termId,long feeTypeId){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,transportFeeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate from vw_studentTransportFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and deleteStatus='N' and paymentStatus='N' and feeTypeId=").append(feeTypeId).append(" group by transportFeeId");
			 List<ViewStudentTransportFeePaymentDetails> nonPaidFee =  getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("transportFeeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentTransportFeePaymentDetails.class)).list()
			  ;
			 if(ObjectFunctions.isNotNullOrEmpty(nonPaidFee)){
				 return nonPaidFee.get(0);
			 }
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List<ViewStudentFeePaymentDetails> getStudentFeeUnpaidDetails(String tableName,long custId,long academicYearId,String termId,String selectedClassIds){
		try {
			StringBuilder queryBuff = new StringBuilder();
			queryBuff.append("select fee.TermFee as feeAmount,sum(sfp.paymentAmount) as paymentAmount,((fee.TermFee)-(IFNULL(sum(sfp.paymentAmount),0)+IFNULL(sum(sfp.discountAmount),0)+IFNULL(sum(sfp.concessionAmount),0))) as paidAmount,sum(sfp.discountAmount) as discountAmount,fee.concessionAmount as concessionAmount,sfp.fullName,sfp.termName,sfp.admissionNumber,sfp.className,sfp.section,sfp.schoolTermId,sfp.dueDate,sfp.mobileNumber,sfp.id,sfp.settingName,sfp.classId,sfp.studentId from "+tableName+" sfp ");
			queryBuff.append(" LEFT JOIN (select sum(feeAmount) TermFee,schoolTermId,studentId,IFNULL(sum(concessionAmount),0) as concessionAmount from ");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append(" vw_studentClassFees ");
			else if ("vw_studentTransportFeeParticularsPayment".equalsIgnoreCase(tableName)) 
				queryBuff.append(" vw_studentTransportFees");
			
			queryBuff.append(" where custId=").append(custId).append(" and academicYearId=").append(academicYearId);
			if (!StringFunctions.isNullOrEmpty(termId)) {
				queryBuff.append(" and schoolTermId in ").append(termId);
			}
			if(!ObjectFunctions.isNullOrEmpty(selectedClassIds)){
				queryBuff.append(" and classId in (").append(selectedClassIds).append(")");
			} 
			queryBuff.append(" and description is null group by schoolTermId,studentId ) fee ON (fee.schoolTermId=sfp.schoolTermId  and fee.studentId=sfp.studentId)");
			
			queryBuff.append(" where sfp.custId=").append(custId).append(" and sfp.academicYearId=").append(academicYearId);
			if (!StringFunctions.isNullOrEmpty(termId)) {
				queryBuff.append(" and sfp.schoolTermId in ").append(termId);
			}
			if(!ObjectFunctions.isNullOrEmpty(selectedClassIds)){
				queryBuff.append(" and sfp.classId in (").append(selectedClassIds).append(")");
			} 
			queryBuff.append(" and sfp.deleteStatus='N' and sfp.status='Y' and sfp.description is null ");
			queryBuff.append(" and sfp.description is null");
			queryBuff.append(" group by sfp.schoolTermId,sfp.studentId having paidAmount > 0 order by sfp.classSectionId,sfp.schoolTermId");
			log.debug(queryBuff.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
					.addScalar("paidAmount").addScalar("discountAmount").addScalar("concessionAmount").addScalar("fullName").addScalar("termName").addScalar("admissionNumber")
					.addScalar("className").addScalar("section").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("dueDate").addScalar("mobileNumber").addScalar("id", StandardBasicTypes.LONG)
					.addScalar("settingName").addScalar("classId", StandardBasicTypes.LONG).addScalar("studentId", StandardBasicTypes.LONG)
					.setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public ViewStudentFeePaymentDetails getStudentWiseFeePaidAndUnpaidDetails(String tableName,long custId,long academicYearId,long termId,long classSectionId,long studentId){
		try {
			StringBuilder queryBuff = new StringBuilder();
			queryBuff.append("select fee.TermFee as feeAmount,sum(sfp.paymentAmount) as paymentAmount,((fee.TermFee)-(IFNULL(sum(sfp.paymentAmount),0)+IFNULL(sum(sfp.discountAmount),0)+IFNULL(fee.concessionAmount,0))) as paidAmount,sum(sfp.discountAmount) as discountAmount,fee.concessionAmount as concessionAmount,sfp.fullName,sfp.termName,sfp.admissionNumber,sfp.className,sfp.section,sfp.schoolTermId,sfp.dueDate,sfp.mobileNumber,sfp.id,sfp.settingName,sfp.classId,sfp.studentId from "+tableName+" sfp ");
			queryBuff.append(" LEFT JOIN (select sum(feeAmount) TermFee,schoolTermId,studentId,IFNULL(sum(concessionAmount),0) as concessionAmount from ");
			if("vw_studentFeePaymentDetails".equalsIgnoreCase(tableName))
				queryBuff.append(" vw_studentClassFees ");
			else if ("vw_studentTransportFeePaymentDetails".equalsIgnoreCase(tableName)) 
				queryBuff.append(" vw_studentTransportFees");
			
			queryBuff.append(" where custId=").append(custId).append(" and academicYearId=").append(academicYearId).append(" and studentId=").append(studentId);
			if (termId>0) {
				queryBuff.append(" and schoolTermId =").append(termId);
			}
			if(classSectionId>0){
				queryBuff.append(" and classSectionId =").append(classSectionId);
			} 
			queryBuff.append(" and description is null group by schoolTermId,studentId ) fee ON (fee.schoolTermId=sfp.schoolTermId  and fee.studentId=sfp.studentId)");
			
			queryBuff.append(" where sfp.custId=").append(custId).append(" and sfp.academicYearId=").append(academicYearId).append(" and sfp.studentId=").append(studentId);
			if (termId>0) {
				queryBuff.append(" and sfp.schoolTermId =").append(termId);
			}
			if(classSectionId>0){
				queryBuff.append(" and sfp.classSectionId =").append(classSectionId);
			} 
			queryBuff.append(" and sfp.deleteStatus='N' and sfp.status='Y' and sfp.description is null ");
			queryBuff.append(" group by sfp.schoolTermId,sfp.studentId order by sfp.classSectionId,sfp.schoolTermId");
			
			log.debug(queryBuff.toString());
			List<ViewStudentFeePaymentDetails> studentPaidUnPiad = getSession().createSQLQuery(queryBuff.toString()).addScalar("feeAmount", StandardBasicTypes.DOUBLE).addScalar("paymentAmount", StandardBasicTypes.DOUBLE)
					.addScalar("paidAmount").addScalar("discountAmount").addScalar("concessionAmount").addScalar("fullName").addScalar("termName").addScalar("admissionNumber")
					.addScalar("className").addScalar("section").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("dueDate").addScalar("mobileNumber").addScalar("id", StandardBasicTypes.LONG)
					.addScalar("settingName").addScalar("classId", StandardBasicTypes.LONG).addScalar("studentId", StandardBasicTypes.LONG)
					.setResultTransformer(Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
			if(ObjectFunctions.isNotNullOrEmpty(studentPaidUnPiad)){
				 return studentPaidUnPiad.get(0);
			 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public List<ViewStudentDeleteFeeDetails> getStudentDeleteInvoiceDetailsById(long userCustId, long userAcademicYearId, String feeType) {
		try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from ViewStudentDeleteFeeDetails where custId =");
			queryBuff.append(userCustId);
			queryBuff.append(" and academicYearId =");
			queryBuff.append(userAcademicYearId);
			queryBuff.append(" and feeType='");
			queryBuff.append(feeType);
			queryBuff.append("'");
				return 	this.getAllHqlQuery(queryBuff.toString());
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	
	public List<ViewStudentDeleteFeeDetails> getStudentDeleteOtherFeeDetailsById(long userCustId, long userAcademicYearId, String feeType){
		try {
			StringBuilder sqlQuery = new StringBuilder("SELECT sum(paidAmount) as paidAmount,admissionNumber,studentFullName,classNameAndSection,invoiceNumber,reportedPerson,deleteRemarks,deleteDate,invoiceNumber,paymentDate,custId,academicYearId from vw_studentDeleteFeeDetails where custId=");
			sqlQuery.append(userCustId);
			sqlQuery.append(" and academicYearId=").append(userAcademicYearId);
			sqlQuery.append(" and deleteStatus='Y' ");
			sqlQuery.append(" and feeType='F' ");
			sqlQuery.append(" group by invoiceNumber,paymentDate");
			log.debug(sqlQuery.toString());
			return (List<ViewStudentDeleteFeeDetails>) getSession().createSQLQuery(sqlQuery.toString()).addScalar("paidAmount", StandardBasicTypes.DOUBLE).addScalar("admissionNumber").addScalar("studentFullName")
					.addScalar("classNameAndSection").addScalar("reportedPerson").addScalar("invoiceNumber",StandardBasicTypes.STRING).addScalar("paymentDate",StandardBasicTypes.DATE).addScalar("custId",StandardBasicTypes.STRING)
					.addScalar("deleteRemarks").addScalar("deleteDate").addScalar("academicYearId",StandardBasicTypes.LONG).setResultTransformer(Transformers.aliasToBean(ViewStudentDeleteFeeDetails.class)).list();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getStudentTermsWisePaidClassFeePaymentDetails(long studentId, long academicYearId){
		try{
			StringBuffer query = new StringBuffer("select schoolTermId,sum(feeAmount) as feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,settingName,termName,feeSettingId,fromMonthName,toMonthName from vw_studentFeeParticularsPayment where studentId=").append(studentId)
			.append(" and academicYearId=").append(academicYearId).append(" and paymentAmount != 0 ").append(" and feeSettingId != ").append(3).append(" group by schoolTermId order by feeSettingId,schoolTermId");
			log.debug(query.toString());
			return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			.addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("settingName").addScalar("termName").addScalar("feeSettingId",StandardBasicTypes.LONG)
			.addScalar("fromMonthName").addScalar("toMonthName")
			.setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list();
		}catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return null;
	}
	public List<ViewStudentFeePaymentDetails> getStudenClassFeePaymentDetails(long studentId,long academicYearId,long termId){
		try{
			StringBuffer query = new StringBuffer("select feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount) as paymentAmount,sum(discountAmount) as discountAmount,paymentDate,sum(paymentConcessionAmount) as paymentConcessionAmount,concessionAmount from vw_studentFeePaymentDetails where studentId=")
			.append(studentId).append(" and academicYearId=").append(academicYearId).append(" and schoolTermId=").append(termId).append(" and paymentAmount != 0 ").append(" and feeSettingId !=").append(3).append(" and deleteStatus='N' group by feeId");
			log.debug(query.toString());
			 return (List<ViewStudentFeePaymentDetails>) getSession().createSQLQuery(query.toString())
			 .addScalar("settingName").addScalar("schoolTermId",StandardBasicTypes.LONG).addScalar("feeSettingId",StandardBasicTypes.LONG).addScalar("termName").addScalar("feeTypeId",StandardBasicTypes.LONG)
			 .addScalar("feeType").addScalar("feeId",StandardBasicTypes.LONG).addScalar("feeAmount").addScalar("paymentAmount").addScalar("discountAmount").addScalar("paymentDate").addScalar("paymentConcessionAmount").addScalar("concessionAmount")
			  .setResultTransformer( Transformers.aliasToBean(ViewStudentFeePaymentDetails.class)).list()
			  ;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	@Override
	public List<Object[]> getAttendanceNotSubmittedDates(long classSectionId ,long academicYearId,String monthIds){
		try {
			Query qry = getSession().createSQLQuery("CALL sp_AttendanceNotSubmittedDatesByMonths(:monthIds, :academicYearId,:classSectionId)")
			.setParameter("monthIds", monthIds)
			.setParameter("academicYearId", academicYearId)
			.setParameter("classSectionId", classSectionId);
			List resultList = qry.list();
			return (List<Object[]>) resultList;
		} catch(Exception ex){
			ex.printStackTrace();		
		}
		return null;
	}
	
	@Override
	public List getParentOccupationDetails(long custId,	long academicYearId, String classIds) {
		try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select IF(fatherOccupation IS NULL || fatherOccupation = '','NoOccupation',fatherOccupation) as occupation,COUNT(*) from vw_studentDetails where custId=");
				queryBuff.append(custId);	
				queryBuff.append(" and academicYearId =");
				queryBuff.append(academicYearId);
				queryBuff.append(" and classSectionId in");
				queryBuff.append(classIds);
				queryBuff.append(" and description is null group by occupation order by occupation asc");
				List resultList=this.getAll(queryBuff.toString());
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					return resultList;
				}
		  }
		catch (Exception ex) {
        	ex.printStackTrace();
        }
		return null;
	
	}
	@Override
	public List<StudentMarks> getLatestUploadedMarksForStudent(	long classSectionId, long academicYearId, int noOfExamTypes,long studentId) {

		try{
			StringBuffer hqlQuery = new StringBuffer("SELECT stMarks FROM StudentMarks as stMarks INNER JOIN stMarks.examSchedule as es WHERE es.classSection=").append(classSectionId)
			.append(" and es.academicYear=").append(academicYearId).append(" and  stMarks.student.id =").append(studentId).append("  and  es.examDate is not null group by es.examType order by  stMarks.lastUpdatedDate DESC");
			if(noOfExamTypes > 0)
				hqlQuery.append(" limit ").append(noOfExamTypes);
			return (List<StudentMarks>) getSession().createQuery(hqlQuery.toString()).list();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	
	}
	
	public List<Object[]> getVehiclesWithDriverDetails(long custId,boolean status){
		try {
			Query qry = getSession().createSQLQuery("CALL SP_VehicleWithDriverDetails(:custId, :pickCurrentTerm)")
			.setParameter("custId", custId)
			.setParameter("pickCurrentTerm", status);
			List resultList = qry.list();
			return (List<Object[]>) resultList;
		} catch(Exception ex){
			ex.printStackTrace();		
		}
		return null;
	}
	@Override
	public List getParentIncomeWiseDetails(long custId,long academicYearId, String classIds, String incomeRangeIds) {
			try{
			    List resultList =new ArrayList();
			    String values=incomeRangeIds.replace("(", "");
				String incomeIds[] = values.replace(")","").split(",");
				Object[] incomeDetails=this.get("select max(id),maxRange from parentIncomeRange");
				long maxRangeId=Long.valueOf(incomeDetails[0].toString());
				for(int i=0;i<incomeIds.length;i++){
					Object[] incomeRanges=this.get("select minRange,maxRange from parentIncomeRange where id="+incomeIds[i]);
					if(!ObjectFunctions.isNullOrEmpty(incomeRanges)){
						StringBuffer queryBuff=new StringBuffer();
						if(Long.valueOf(incomeIds[i]) == maxRangeId)
							queryBuff.append("select (select CONCAT('Range',CONCAT(' ',CONCAT(id,CONCAT(' ',CONCAT('(',CONCAT(minRange,CONCAT('-',(CONCAT(maxRange,' and Above)'))))))))) from parentIncomeRange where id="+incomeIds[i]+") as rangeValues,COUNT(*) from vw_parentIncomeWiseStudentList where custId=");
						else
							queryBuff.append("select (select CONCAT('Range',CONCAT(' ',CONCAT(id,CONCAT(' ',CONCAT('(',CONCAT(minRange,CONCAT('-',(CONCAT(maxRange,')'))))))))) from parentIncomeRange where id="+incomeIds[i]+") as rangeValues,COUNT(*) from vw_parentIncomeWiseStudentList where custId=");
						queryBuff.append(custId);	
						queryBuff.append(" and academicYearId =");
						queryBuff.append(academicYearId);
						queryBuff.append(" and classSectionId in");
						queryBuff.append(classIds);
						if(Long.valueOf(incomeIds[i]) == maxRangeId)
							queryBuff.append(" and (annualIncome between "+incomeRanges[0].toString()+" and "+incomeRanges[1].toString()+" or annualIncome >"+incomeRanges[1].toString()+")");
						else
							queryBuff.append(" and annualIncome between "+incomeRanges[0].toString()+" and "+incomeRanges[1].toString());
						queryBuff.append(" and description is null");
						Object[] studentList=this.get(queryBuff.toString());
						resultList.add(studentList);
						queryBuff=null;
						incomeRanges = null;
						studentList = null;
					}
				}
				if (!ObjectFunctions.isNullOrEmpty(resultList)) {
					incomeDetails = null;
					return resultList;
				}
			}catch(Exception exp){
			  exp.printStackTrace();
			}
		return null;
	}
	
	
}