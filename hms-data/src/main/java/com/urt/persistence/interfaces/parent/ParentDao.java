package com.urt.persistence.interfaces.parent;

import java.util.List;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.user.User;


public interface ParentDao extends UniversalDao {

	
	Staff getStaffByAcountId(long accountId);
	
	ClassTeacher getStudyClassSubject(long classId, long staffId);
	
	List<Student> getStudentByClassId(long classId);
	
	//Period getPeriodByClassAndSubject(long classId, long subjectId);
	
	Student getStudentById(long studentId);
	
	List getLeavesListByAccountId(long accountId,String leaveStatus,long customerId);
	
	List getAllCalendarEventsByStaffIdAndAccountId(long staffId,long customerId);
	
	List getAllCalendarEventsByCustomerId(long customerId);
	
	List getEventInvitedUserListByEventId(String eventId);
	
	User getAccountByUserName(String userName);
	
	//EventInvitedUser getEventInvitedUserByEventIdAndAccountId(String eventId,long accountId,String eventAccepted);
	
	/*List getViewStudentPersonAccountDetailsByAccountIds(String accountIds);*/
	
	List getEventsByDate(String eventDate);
	
	List getTeacherSubjectsById(long staffid);
	
	List getTeacherSubjectsByStaffAndClassId(String staffId,String classId);
	
	ClassTeacher getTeacherByClassandSubjectId(long staffId,long classId,String subjectId);
	
	/*Student getStudentByAcountId(long accountId);*/
	
	List getViewStudentPersonAccountDetailsByClassNameAndSectionId(String className,String sectionName);
	
	List getClassEventListByEventId(String eventId);
	
	/*List getStudyClassListBystudyClassIds(String studyClassIds);*/
	
	/*List getALLViewStudentPersonAccountDetailsByStudentRole(String studentRole);*/
	
	List getAllLeavesByStatusAndRoleId(long customerId,String leaveStatus,String roleName);
	
	ViewStudentLeaveDetails getViewStudentLeaveDetailsByLeaveId(long leaveid,long customerId);
}
