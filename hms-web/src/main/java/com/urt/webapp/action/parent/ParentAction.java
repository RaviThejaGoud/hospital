package com.urt.webapp.action.parent;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;
/**
 * Action for facilitating calendar Management feature.
 */

@Namespace("/parent")
@Actions( {
	@Action(value = "parentHome", results = { @Result(location = "parentHome.jsp", name = "success") }) 
	})

public class ParentAction extends BaseAction {

	private static final long serialVersionUID = -1646390427462403153L;
	
	protected StudentManager studentManager;
	private String anyId;
	private String balance;
	protected String subjectId;
	private String classId;

	public String getAnyId() {
		return anyId;
	}
	public void setAnyId(String anyId) {
		this.anyId = anyId;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public void setStudentManager(StudentManager studentManager) {
		this.studentManager =studentManager;
	}
	   @Actions({
			@Action(value = "ajaxDoGetLeaveDetails", results = { @Result(location = "leaveHome.jsp", name = "success") }), 
			@Action(value = "ajaxDoCancelLeave", results = { @Result(location = "viewLeavesList.jsp", name = "success") }) 
		})
			public String doGetLeaveDetails() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'doGetLeaveDetails' method");
			}
			try
			{
			//	prepareAcademicYearId();
				long id = getUser().getId();			
				setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails)studentManager.get(ViewStaffPersonAccountDetails.class,id,"accountId"));				
				List<ViewStudentPersonAccountDetails> childrens=studentManager.getMyChildren(getUser().getId(),getUserCustId(),getUserAcademicYearId(),Constants.YES_STRING);
				if(!ObjectFunctions.isNullOrEmpty(childrens)){				
	                for (ViewStudentPersonAccountDetails children : childrens)
	                {
	                	List<Leave> approvedLeavesList = studentManager.getLeavesListByAccountId(children.getAccountId(),Constants.ACTIVE_STATUS,getUserCustId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(approvedLeavesList))
						{
			                for (Leave leave : approvedLeavesList)
			                {
			                	leave.setChildRollNo(children.getRollNumber());
			                	leave.setChildAccountId(children.getAccountId());
			                	getApprovedLeavesList().add(leave);
			                	leave=null;
			                }
						}
						
						List<Leave> pendingLeavesList = studentManager.getLeavesListByAccountId(children.getAccountId(),Constants.PENDING_STATUS,getUserCustId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(pendingLeavesList))
						{
			                for (Leave leave: pendingLeavesList)
			                {
			                	leave.setChildRollNo(children.getRollNumber());
			                	leave.setChildAccountId(children.getAccountId());
			                	getLeavesList().add(leave);
			                	leave=null;
			                }
							//getLeavesList().addAll(pendingLeavesList);
						}	
						List<Leave> rejectedLeavesList = studentManager.getLeavesListByAccountId(children.getAccountId(),Constants.REJECTED_STATUS,getUserCustId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(rejectedLeavesList))
						{
			                for (Leave leave : rejectedLeavesList)
			                {
			                	leave.setChildRollNo(children.getRollNumber());
			                	leave.setChildAccountId(children.getAccountId());
			                	getRejectedLeavesList().add(leave);
			                	leave=null;
			                }
							//setRejectedLeavesList(rejectedLeavesList);
						}
						children=null;
	                }
				}
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}

			return SUCCESS;
		}
		
		@Actions({
			@Action(value = "ajaxDoGetleaveReportForStudent", results = { @Result(location = "parentLeave.jsp", name = "success") })})

			public String parentLeave() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'parentLeave' method");
			}

			try
			{
			/*//	String academicYearId = null;
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYear"))) {
					academicYearId = (String) getSession().getAttribute("academicYear");
				} else {
					academicYearId = (String) getSession().getAttribute("newYear");
				}*/
			  setObjectList(studentManager.getMyChildren(getUser().getId(),getUserCustId(),getAcademicYearId(),Constants.YES_STRING));
			  Leave leave = new Leave();
				leave.setStartDate(new Date());
				//leave.setSupervisorId(supervisorId);
				//leave.setEndDate(new Date());
				setLeave(leave);
			  setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
			}
			catch (Exception e) {
				e.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
			}
			return SUCCESS;
		}
		
		
		/*@Actions({
			@Action(value = "ajaxAddleaveReportForStudent", results = {  @Result(location = "viewLeavesList.jsp", name = "success") })})
			//@Action(value = "ajaxAddleaveReport", results = {  @Result(type="redirect", location = "staffHome.do", name = "success") }) })
			public String ajaxAddleaveReport() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddleaveReport' method");
			}
			try
			{
				if(!ObjectFunctions.isNullOrEmpty(getLeave()))
				{
					String studentAccountId = getParamValue("anyId");
					String name=getParamValue("viewStudentPersonAccountDetails.personFullName");
					//getviewStudentPersonAccountDetails();
					Date endDate1 = getLeave().getEndDate();
					Date startDate1 = getLeave().getStartDate();
					//String startTime = getLeave().getStartTime();
					//String endTime = getLeave().getEndTime();
					double diff=0;
					int daysBetween = DateFunctions.daysBetween(startDate1, endDate1);
					double days = daysBetween + diff;
					getLeave().setDays(days);
					getLeave().setLeaveStatus("P"); 
					getLeave().setCreatedBy(name);
					getLeave().setCreatedDate(new Date());
					getLeave().setLastUpdatedBy(name);
					getLeave().setLastUpdatedDate(new Date());
					getLeave().setLastAccessDate(new Date());
					getLeave().setCustId(getUserCustId());
					getLeave().setUser((User)studentManager.get(User.class,Long.parseLong(getAnyId())));
					if(startTime.equalsIgnoreCase("9AM") && endTime.equalsIgnoreCase("1PM"))
					{
						diff=0.5;
					}
					else if(startTime.equalsIgnoreCase("9AM") && endTime.equalsIgnoreCase("4PM")) 
					{
						diff=1;
					}
					else if(startTime.equalsIgnoreCase("1PM") && endTime.equalsIgnoreCase("4PM"))
					{
						diff=0.5;
					}
					else if(startTime.equalsIgnoreCase("1PM") && endTime.equalsIgnoreCase("1PM")) 
					{
					}
					
					if(!StringFunctions.isNullOrEmpty(studentAccountId))
					{
						Student student = studentManager.getStudentById(Long.valueOf(studentAccountId)); 
						if(!ObjectFunctions.isNullOrEmpty(student))
						{
							StudyClass studyClass = student.getClassSection();
							if(!ObjectFunctions.isNullOrEmpty(studyClass))
							{
								ClassTeacher classTeacher = adminManager.getClassTeacherByStudyClassIdAndClassTeacherStatus(studyClass.getId(),Constants.YES_STRING,studyClass.getAcademicYear().getId());
								if(!ObjectFunctions.isNullOrEmpty(classTeacher))
								{
									long classTeacherAccountId = classTeacher.getStaff().getAccount().getId();
									if(!StringFunctions.isNullOrEmpty(studentAccountId))
									{
										getLeave().setSupervisorId(classTeacherAccountId);
									}
								}
							}
						}
					}
					studentManager.save(getLeave());
					super.addActionMessage("Your Leave has posted successfully");
				}
			}
			catch(Exception ex)
			{
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();
			}
			doGetLeaveDetails();
			
			return SUCCESS;
		}*/
		/*@Actions({
			@Action(value = "ajaxDoEditleaveReport", results = { @Result(location = "editParentLeave.jsp", name = "success") })})

			public String doEditStaffLeave() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'doEditStaffLeave' method");
			}

			try
			{
				String academicYearId = null;
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYear"))) {
					academicYearId = (String) getSession().getAttribute("academicYear");
				} else {
					academicYearId = (String) getSession().getAttribute("newYear");
				}
				long id = getUser().getId();
				double leaveDays=0;
				String leavesId=getParamValue("id");
				String childAccountId=getParamValue("childAccountId");
				if(!StringFunctions.isNullOrEmpty(leavesId))
				{
					setSelectedId(leavesId);
					Leave leaves = (Leave)studentManager.get(Leave.class, Long.valueOf(leavesId));
					if(!ObjectFunctions.isNullOrEmpty(leaves))
					{
						setLeave(leaves);
					}
				}
				ViewStudentPersonAccountDetails viewStudentPersonAccountDetails= (ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class,Long.valueOf(childAccountId));
				if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails))
				{
					setViewStudentPersonAccountDetails(viewStudentPersonAccountDetails);
				}
				List<Leave> leavesList = studentManager.getLeavesListByAccountId(id,Constants.ACTIVE_STATUS,getUserCustId(),Long.valueOf(academicYearId));
				if(!ObjectFunctions.isNullOrEmpty(leavesList))
				{
	                for (Leave leaves : leavesList)
	                {
	                	leaveDays= leaveDays + leaves.getDays();
	                }
	                setAnyId(String.valueOf(leaveDays));
	               // setBalance(String.valueOf(getViewStudentPersonAccountDetails().getTotalLeaves() - leaveDays));
				}
				else
				{
					setAnyId("0");
				//	setBalance(String.valueOf(getViewStudentPersonAccountDetails().getTotalLeaves()));
				}
				setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return SUCCESS;
		}*/
		/*@Actions({
			@Action(value = "ajaxEditleaveReport", results = {  @Result(location = "viewLeavesList.jsp", name = "success") }) })
			//@Action(value = "ajaxAddleaveReport", results = {  @Result(type="redirect", location = "staffHome.do", name = "success") }) })
			public String ajaxEditleaveReport() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxEditleaveReport' method");
			}
			try
			{
				String leavesId = getSelectedId();
				Leave leaves = (Leave)studentManager.get(Leave.class, Long.valueOf(leavesId));
				if(!ObjectFunctions.isNullOrEmpty(getLeave()))
				{
					String name=getParamValue("viewStudentPersonAccountDetails.personFullName");
					//Customer customer = (Customer)studentManager.get(Customer.class,getUserCustId());
					Date endDate1 = getLeave().getEndDate();
					Date startDate1 = getLeave().getStartDate();
					double diff=0;
					if(getLeave().getStartTime().equalsIgnoreCase("9AM") && getLeave().getEndTime().equalsIgnoreCase("1PM"))
					{
						diff=0.5;
					}
					else if(getLeave().getStartTime().equalsIgnoreCase("9AM") && getLeave().getEndTime().equalsIgnoreCase("4PM")) 
					{
						diff=1;
					}
					else if(getLeave().getStartTime().equalsIgnoreCase("1PM") && getLeave().getEndTime().equalsIgnoreCase("4PM"))
					{
						diff=0.5;
					}
					else if(getLeave().getStartTime().equalsIgnoreCase("1PM") && getLeave().getEndTime().equalsIgnoreCase("1PM")) 
					{
					}
					int daysBetween = DateFunctions.daysBetween(startDate1, endDate1);
					double days = daysBetween + diff;
					leaves.setDays(days);
					//leaves.setSubject(getLeave().getSubject());
					leaves.setDescription(getLeave().getDescription());
					leaves.setLeaveStatus(getLeave().getLeaveStatus()); 
					leaves.setStartDate(getLeave().getStartDate());
					leaves.setEndDate(getLeave().getEndDate());
					leaves.setCreatedDate(new Date());
					leaves.setLastUpdatedDate(new Date());
					leaves.setLastAccessDate(new Date());
					leaves.setLeaveType(getLeave().getLeaveType());
					//getLeave().setCustId(customer.getId());
					
				}
				studentManager.save(leaves);
				super.addActionMessage("Your Leave has updated successfully");
				//setGroupTypesList(groupManager.getAllGroupTypesByCustomerId(getUserCustId()));
				//Collections.sort(getGroupTypesList());
			}
			catch(Exception ex)
			{
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();
			}
			doGetLeaveDetails();
			
			return SUCCESS;
		}*/
		@Actions({
			@Action(value = "ajaxDeleteLeave", results = { @Result(location = "viewLeavesList.jsp", name = "success") }) })

			public String ajaxDeleteLeave() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDeleteLeave' method");
			}

			try
			{
				String leavesId=getParamValue("id");
				if(StringFunctions.isNullOrEmpty(leavesId) && !StringFunctions.isNullOrEmpty(getParamValue("leave.id")))
				{
					//if(!StringFunctions.isNullOrEmpty(getParamValue("leave.id"))){
						leavesId=getParamValue("leave.id");
					//}
				}
				if(!StringFunctions.isNullOrEmpty(leavesId))
				{
					studentManager.remove(Leave.class, Long.valueOf(leavesId));
				}
				if(!StringFunctions.isNullOrEmpty(getParamValue("leave.id")))
				{
					doGetLeaveDetails();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			
			return SUCCESS;
		}
		
	
	}