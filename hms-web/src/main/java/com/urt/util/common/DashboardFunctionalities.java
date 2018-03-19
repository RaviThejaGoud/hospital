package com.urt.util.common;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.object.ObjectFunctions;
import com.spring.context.SpringContextAware;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.student.StudentManager;


public class DashboardFunctionalities {
    protected static final Log log = LogFactory.getLog(DashboardFunctionalities.class);
    AdminManager adminManager = (AdminManager)SpringContextAware.getBean("adminManager");
    StudentManager studentManager = (StudentManager)SpringContextAware.getBean("studentManager");
    

	public List noticeBoardMessages(long accountId,long custId,long academicYearId)
	{
		ViewStudentPersonAccountDetails studentDetails = (ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class,accountId);
		if(!ObjectFunctions.isNullOrEmpty(studentDetails))
		{
			List noticeBoardMessagesList = studentManager.getAllNoticeBoardMessagesList("NB",studentDetails.getClassAndSection(),custId,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(noticeBoardMessagesList)){
				return noticeBoardMessagesList;
			}
		}
		return null;
	}
}