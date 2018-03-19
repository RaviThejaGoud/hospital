package com.urt.service.manager.impl.parent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urt.persistence.interfaces.parent.ParentDao;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.parent.ParentManager;


/**
 * Implementation of UserManager interface.</p>
 * 
 * <p>
 * <a href="UserManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */

@Component
public class ParentManagerImpl extends UniversalManagerImpl implements ParentManager {
	
	
	@Autowired
    private ParentDao parentDao;

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Staff getStaffByAcountId(long accountId){
		 return parentDao.getStaffByAcountId(accountId);
	}

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getStudyClassSubject(long classId, long staffId){
		 return parentDao.getStudyClassSubject(classId, staffId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Student> getStudentByClassId(long classId){
		 return parentDao.getStudentByClassId(classId);
	}
	
	/*public Period getPeriodByClassAndSubject(long classId, long subjectId){
		return parentDao.getPeriodByClassAndSubject(classId, subjectId);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Student getStudentById(long studentId){
		return parentDao.getStudentById(studentId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getLeavesListByAccountId(long accountId,String leaveStatus,long customerId) {
		return parentDao.getLeavesListByAccountId(accountId,leaveStatus,customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllCalendarEventsByStaffIdAndAccountId(long staffId,long customerId) {
		return parentDao.getAllCalendarEventsByStaffIdAndAccountId(staffId,customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllCalendarEventsByCustomerId(long customerId) {
		return parentDao.getAllCalendarEventsByCustomerId(customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getEventInvitedUserListByEventId(String eventId) {
		return parentDao.getEventInvitedUserListByEventId(eventId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public User getAccountByUserName(String userName) {
		return parentDao.getAccountByUserName(userName);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getEventsByDate(String eventDate){
		return parentDao.getEventsByDate(eventDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getTeacherSubjectsById(long staffId){
		return parentDao.getTeacherSubjectsById(staffId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getTeacherSubjectsByStaffAndClassId(String staffId,String classId){
		return parentDao.getTeacherSubjectsByStaffAndClassId(staffId,classId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ClassTeacher getTeacherByClassandSubjectId(long staffId,long classId,String subjectId){
		return parentDao.getTeacherByClassandSubjectId(staffId,classId,subjectId);
	}
	
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getViewStudentPersonAccountDetailsByClassNameAndSectionId(String className,String sectionName) {
		return parentDao.getViewStudentPersonAccountDetailsByClassNameAndSectionId(className,sectionName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassEventListByEventId(String eventId) {
		return parentDao.getClassEventListByEventId(eventId);
	}

	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllLeavesByStatusAndRoleId(long customerId,String leaveStatus,String roleName) {
		return parentDao.getAllLeavesByStatusAndRoleId(customerId,leaveStatus,roleName);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentLeaveDetails getViewStudentLeaveDetailsByLeaveId(long leaveid, long customerId) {
		return parentDao.getViewStudentLeaveDetailsByLeaveId(leaveid,customerId);
	}

}
