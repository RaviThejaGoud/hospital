package com.urt.service.manager.interfaces.parent;

import java.util.List;

import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="UserManager.java.html"><i>View Source</i></a></p>
 */
public interface ParentManager extends UniversalManager {
	
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
	
	List getEventsByDate(String eventDate);
	
	List getTeacherSubjectsById(long staffId);
	
	List getAllLeavesByStatusAndRoleId(long customerId,String leaveStatus,String roleName);
	
	List getTeacherSubjectsByStaffAndClassId(String staffId,String classId);
	
	ClassTeacher getTeacherByClassandSubjectId(long staffId,long classId,String subjectId);
	
	List getViewStudentPersonAccountDetailsByClassNameAndSectionId(String className,String sectionName);
	
	List getClassEventListByEventId(String eventId);

	ViewStudentLeaveDetails getViewStudentLeaveDetailsByLeaveId(long leaveid,long customerId);
	
}
