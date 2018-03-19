package com.urt.webapp.action.staff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.CommonTypeMainVO;
import com.hyniva.sms.ws.vo.CommonTypeVO;
import com.hyniva.sms.ws.vo.PersonVO;
import com.hyniva.sms.ws.vo.ReminderVO;
import com.hyniva.sms.ws.vo.SalaryVO;
import com.hyniva.sms.ws.vo.StaffHistoryVO;
import com.hyniva.sms.ws.vo.StaffPermissionRequestsVO;
import com.hyniva.sms.ws.vo.StaffStatutoryVO;
import com.hyniva.sms.ws.vo.StaffTimetablesVO;
import com.hyniva.sms.ws.vo.StaffVO;
import com.hyniva.sms.ws.vo.TaskDetailsVO;
import com.hyniva.sms.ws.vo.TaskHistoryVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.MessageEnquiryDetails;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.PaySlip;
import com.urt.persistence.model.common.PayrollSettings;
import com.urt.persistence.model.common.PayrollTypes;
import com.urt.persistence.model.common.PersonDocuments;
import com.urt.persistence.model.common.Reminder;
import com.urt.persistence.model.common.Salary;
import com.urt.persistence.model.common.StaffMonthlyPaySlips;
import com.urt.persistence.model.common.StaffPermissionRequests;
import com.urt.persistence.model.common.StaffStatutory;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.TaskDetails;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.common.ViewStaffPermissionRequests;
import com.urt.persistence.model.common.ViewStaffPermissionsSettings;
import com.urt.persistence.model.common.ViewTaskDetailsAndTaskHistory;
import com.urt.persistence.model.common.ViewUserRoles;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.event.Events;
import com.urt.persistence.model.event.EventsAlbum;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.exam.Quiz;
import com.urt.persistence.model.exam.QuizQuestion;
import com.urt.persistence.model.exam.ViewStaffSubjectsDetails;
import com.urt.persistence.model.exam.ViewStudentQuestionAnswers;
import com.urt.persistence.model.exam.WeekDays;
import com.urt.persistence.model.study.ActivityType;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.SchoolShiftInfo;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StaffCurricularActivities;
import com.urt.persistence.model.study.StaffElgibleSubjects;
import com.urt.persistence.model.study.StaffHistory;
import com.urt.persistence.model.study.StaffSyllabusPlanner;
import com.urt.persistence.model.study.StaffTimetables;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.SyllabusPlannerComments;
import com.urt.persistence.model.study.TimetablePeriods;
import com.urt.persistence.model.study.ViewHostelStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStaffLeaveDetails;
import com.urt.persistence.model.study.ViewStaffPaySlipDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStaffSyllabusPlanner;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentsLatestExamMarksDetails;
import com.urt.persistence.model.study.ViewSyllabusPlannerComments;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.persistence.util.excel.StaffExcelRow;
import com.urt.service.manager.interfaces.calendar.CalendarManager;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.excel.parser.SheetParser;
import com.urt.util.excel.staff.PrepareStaffExcel;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.event.DOMUtil;
import com.urt.webapp.action.event.RecurringEventsDOM;
import com.urt.webapp.action.jrexception.JRExceptionClient;
/**
 * Action for facilitating calendar Management feature.
 */
@Namespace("/staff")
@Actions( { @Action(value = "adminCalendar", results = { @Result(location = "eventCalendar.jsp", name = "success") }),
	        @Action(value = "ajaxDoAddCategory", results = { @Result(location = "createCategory.jsp", name = "success") }),
	        @Action(value = "ajaxDoGetLeaveDetails", results = { @Result(location = "viewLeavesList.jsp", name = "success") }),
			@Action(value = "doLeaveApprovalsCountHome", results = { @Result(location = "ajaxLeaveApprovalsCountHome.jsp", name = "success") }),
			@Action(value = "ajaxStudentAttendence", results = { @Result(location = "studentAttendence.jsp", name = "success") }),
			@Action(value = "ajaxStaffPerformance", results = { @Result(location = "ajaxStaffPerformance.jsp", name = "success") }),
			@Action(value = "staffSubjectsPerformance", results = { @Result(location = "class/myPerformance.jsp", name = "success") }),
			@Action(value = "manageStaffLeaves", results = { @Result(location = "leaves/nonTeachingStaffLeaveHome.jsp", name = "success") }),
			@Action(value = "ajaxDoAddNewQuiz", results = { @Result(location = "quiz/newQuiz.jsp", name = "success") }),
			@Action(value = "ajaxDoSearchKVideos", results = { @Result(location = "ajaxSearchKVideos.jsp", name = "success") }),
			@Action(value = "ajaxViewUploadAndDownloadDocs", results = { @Result(location = "manageStaff/ajaxViewUploadAndDownloadDocs.jsp", name = "success") }),
			@Action(value = "ajaxMyTask", results = { @Result(location = "myTask.jsp", name = "success") }),
			@Action(value = "ajaxDoImportStaffExcelSheet", results = { @Result(location = "manageStaff/importStaffExcelSheet.jsp", name = "success")})
			})
			
public class StaffAction extends BaseAction {

	
	private static final long serialVersionUID = -1646390427462403153L;
     
	private List<Student> studentList;
	protected Staff staff;
	protected String classId;
	
	protected Set<String> eventDatesSet;
	private String eventBelongs;
	protected String eventFrequency;
	protected CalendarManager calendarManager;
	
	protected String className;
    private String section;
	protected String categoryName;
	
	private int thresholdMonths;
    protected String classNameClassId;
	protected QuizQuestion quizQuestion;
	protected String myStudents;
	protected int bioMetricId;
	protected StaffSyllabusPlanner staffSyllabusPlanner;
	protected SyllabusPlannerComments syllabusPlannerComments;
	protected SchoolShiftInfo schoolShiftInfo;
	protected StaffPermissionRequests staffPermissionRequests;
	protected StaffPermissionRequestsVO StaffPermissionRequestsVO;
	public String autoCheck;
	protected TaskDetailsVO taskDetailsVO;
	protected TaskHistoryVO taskHistoryVO;
	private   List<TaskDetailsVO> taskDetailsVOList;
	protected ReminderVO reminderVO;
	protected long taskId;
	private boolean storeAssigned = false;
	private List messageDetails;
	private MessageEnquiryDetails details;
	private Messages approvedMessages;
	//private long checkBoxSelectedStudIds
	private String rejectedStatus;
	public List rejectedDetails;
	public List approvedList;
	public List getApprovedList() {
		return approvedList;
	}
	public void setApprovedList(List approvedList) {
		this.approvedList = approvedList;
	}
	public List getRejectedDetails() {
		return rejectedDetails;
	}
	public void setRejectedDetails(List rejectedDetails) {
		this.rejectedDetails = rejectedDetails;
	}
	public String getRejectedStatus() {
		return rejectedStatus;
	}
	public void setRejectedStatus(String rejectedStatus) {
		this.rejectedStatus = rejectedStatus;
	}
	public Messages getApprovedMessages() {
		return approvedMessages;
	}
	public void setApprovedMessages(Messages approvedMessages) {
		this.approvedMessages = approvedMessages;
	}
	public long getTaskId() {
		return taskId;
	}
	public MessageEnquiryDetails getDetails() {
		return details;
	}
	public void setDetails(MessageEnquiryDetails details) {
		this.details = details;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public ReminderVO getReminderVO() {
		return reminderVO;
	}
	public void setReminderVO(ReminderVO reminderVO) {
		this.reminderVO = reminderVO;
	}
	public List getMessageDetails() {
		return messageDetails;
	}
	public void setMessageDetails(List messageDetails) {
		this.messageDetails = messageDetails;
	}
	public List<TaskDetailsVO> getTaskDetailsVOList() {
		return taskDetailsVOList;
	}
	public void setTaskDetailsVOList(List<TaskDetailsVO> taskDetailsList) {
		this.taskDetailsVOList = taskDetailsVOList;
	}
	public TaskDetailsVO getTaskDetailsVO() {
		return taskDetailsVO;
	}
	public void setTaskDetailsVO(TaskDetailsVO taskDetailsVO) {
		this.taskDetailsVO = taskDetailsVO;
	}
	public TaskHistoryVO getTaskHistoryVO() {
		return taskHistoryVO;
	}
	public void setTaskHistoryVO(TaskHistoryVO taskHistoryVO) {
		this.taskHistoryVO = taskHistoryVO;
	}
	public String getAutoCheck() {
		return autoCheck;
	}
	public void setAutoCheck(String autoCheck) {
		this.autoCheck = autoCheck;
	}
	public StaffPermissionRequestsVO getStaffPermissionRequestsVO() {
		return StaffPermissionRequestsVO;
	}
	public void setStaffPermissionRequestsVO(
			StaffPermissionRequestsVO staffPermissionRequestsVO) {
		StaffPermissionRequestsVO = staffPermissionRequestsVO;
	}
	public StaffPermissionRequests getStaffPermissionRequests() {
		return staffPermissionRequests;
	}
	public void setStaffPermissionRequests(
			StaffPermissionRequests staffPermissionRequests) {
		this.staffPermissionRequests = staffPermissionRequests;
	}
	public SchoolShiftInfo getSchoolShiftInfo() {
		return schoolShiftInfo;
	}
	public void setSchoolShiftInfo(SchoolShiftInfo schoolShiftInfo) {
		this.schoolShiftInfo = schoolShiftInfo;
	}
	
	/**
	 * @return the syllabusPlannerComments
	 */
	public SyllabusPlannerComments getSyllabusPlannerComments() {
		return syllabusPlannerComments;
	}
	/**
	 * @param syllabusPlannerComments the syllabusPlannerComments to set
	 */
	public void setSyllabusPlannerComments(
			SyllabusPlannerComments syllabusPlannerComments) {
		this.syllabusPlannerComments = syllabusPlannerComments;
	}
	public StaffSyllabusPlanner getStaffSyllabusPlanner() {
		return staffSyllabusPlanner;
	}
	public void setStaffSyllabusPlanner(StaffSyllabusPlanner staffSyllabusPlanner) {
		this.staffSyllabusPlanner = staffSyllabusPlanner;
	}
	/**
	 * @return the bioMetricId
	 */
	public int getBioMetricId() {
		return bioMetricId;
	}
	/**
	 * @param bioMetricId the bioMetricId to set
	 */
	public void setBioMetricId(int bioMetricId) {
		this.bioMetricId = bioMetricId;
	}
	/**
	 * @return the quizQuestion
	 */
	public QuizQuestion getQuizQuestion() {
		return quizQuestion;
	}
	public void setQuizQuestion(QuizQuestion quizQuestion) {
		this.quizQuestion = quizQuestion;
	}

	/**
	 * @return the classNameClassId
	 */
	public String getClassNameClassId() {
		return classNameClassId;
	}

	/**
	 * @param classNameClassId the classNameClassId to set
	 */
	public void setClassNameClassId(String classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getThresholdMonths() {
		return thresholdMonths;
	}

	/**
	 * @param thresholdMonths
	 *            the thresholdMonths to set
	 */
	public void setThresholdMonths(int thresholdMonths) {
		this.thresholdMonths = thresholdMonths;
	}
	
	@Override
	public Staff getStaff() {
		return staff;
	}
	@Override
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String getSection() {
		return section;
	}
	@Override
	public void setSection(String section) {
		this.section = section;
	}
	
	

	public String getMyStudents() {
		return myStudents;
	}

	public void setMyStudents(String myStudents) {
		this.myStudents = myStudents;
	}

	/**
	 * @return the classId
	 */
	@Override
	public String getClassId() {
		return classId;
	}

	/**
	 * @param classId the classId to set
	 */
	@Override
	public void setClassId(String classId) {
		this.classId = classId;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}


	/**
	 * @return the eventDatesSet
	 */
	public Set<String> getEventDatesSet() {
		if(ObjectFunctions.isNullOrEmpty(this.eventDatesSet)){
			this.eventDatesSet=new HashSet<String>();
		}
		return this.eventDatesSet;
	}

	/**
	 * @param eventDatesSet the eventDatesSet to set
	 */
	public void setEventDatesSet(Set<String> eventDatesSet) {
		this.eventDatesSet = eventDatesSet;
	}

	
	
	
	@Override
	public Map getJsonResult() {
		if(ObjectFunctions.isNullOrEmpty(super.jsonResult)){
		  super.jsonResult=new HashMap();
		}
		return super.jsonResult;
	}
	
	
		
  public CalendarManager getCalendarManager() {
	return calendarManager;
	}
	
	public void setCalendarManager(CalendarManager calendarManager) {
		this.calendarManager = calendarManager;
	}
	
	
	public String getEventBelongs() {
		return eventBelongs;
	}

	public void setEventBelongs(String eventBelongs) {
		this.eventBelongs = eventBelongs;
	}

	public String getEventFrequency() {
		return eventFrequency;
	}

	public void setEventFrequency(String eventFrequency) {
		this.eventFrequency = eventFrequency;
	}
	
  
   /* @Description 15rd Apr cvs: Modularization   */
   @Actions({
		@Action(value = "ajaxMySubjectsComparison", results = { @Result(location = "ajaxDoSubjectComparison.jsp", name = "success") })
	})
		public String ajaxStaffPerformance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStaffPerformance' method");
		}
		try {
			ajaxGetStaffStudyClasses();
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 15rd Apr cvs: Modularization  remove the unnecessary variables and get the staff events */
   @Actions({			
		@Action(value = "ajaxStaffCancelEvent", results = { @Result(location = "viewStaffEventsLists.jsp", name = "success") }),
		@Action(value = "ajaxStaffEvents", results = { @Result(location = "ajaxStaffEvents.jsp", name = "success") })})
		public String ajaxStaffEvents() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxStaffEvents' method");
			}
			try {
				if(getUser().getId()> 0)
				{
					List<Events> allStaffEventsList = new ArrayList<Events>();
					Set<Long> allStaffEventsSet = new HashSet<Long>();
					String studyClassIds=null;
					List<BigInteger> staffStudyClassIds =  adminManager.getAll("select studyClassId from vw_staffSubjectsDetails where custId ="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" group by studyClassId");
					if (ObjectFunctions.isNotNullOrEmpty(staffStudyClassIds)) 
						studyClassIds = StringFunctions.convertListToCommaDelimitedString(staffStudyClassIds);
					else
						studyClassIds="0";
					
					List<Events> eventsList = adminManager.eventsforStaffStudents(studyClassIds,getUser().getRoleId(),getUserCustId(),getUserAcademicYearId(),Constants.ACTIVE_STATUS);
					if(!ObjectFunctions.isNullOrEmpty(eventsList))
					{
						allStaffEventsList.addAll(eventsList);
					}
					eventsList = null;
					ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = (ViewStaffPersonAccountDetails)staffManager.get(ViewStaffPersonAccountDetails.class,"accountId="+getUser().getId());
					if(!ObjectFunctions.isNullOrEmpty(viewStaffPersonAccountDetails))
					{
						List<Events> staffEventsList = null;
						if(Constants.SCHOOL_TEACHER.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()) || Constants.SCHOOL_ASST_STAFF.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()) || Constants.SCHOOL_HOD.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()))
							staffEventsList = adminManager.getAll(Events.class,"(staffEvent='T' OR staffEvent='A') and academicYearId = "+ getUserAcademicYearId() +" and endDate >='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00' ORDER BY startDate");
						else
							staffEventsList = adminManager.getAll(Events.class," (staffEvent='N' OR staffEvent='A') and academicYearId = "+ getUserAcademicYearId() +" and endDate >='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00' ORDER BY startDate");
						
						if(!ObjectFunctions.isNullOrEmpty(staffEventsList))
						{
							for( Events events : staffEventsList ) {
						    	events.setEventsAlbum(adminManager.getAll(EventsAlbum.class," eventId="+events.getId()));
						    	allStaffEventsList.add(events);
							}
						}
						staffEventsList = null;
						for( Events events : allStaffEventsList ) {
						    if(allStaffEventsSet.add(events.getId())) {
						    	getObjectList().add(events);
						    }
						}
						allStaffEventsList = null;
						allStaffEventsSet = null;
						
						allStaffEventsList = new ArrayList<Events>();
						allStaffEventsSet = new HashSet<Long>();
						
						eventsList = adminManager.eventsforStaffStudents(studyClassIds,getUser().getRoleId(),getUserCustId(),getUserAcademicYearId(),"C");
						if(!ObjectFunctions.isNullOrEmpty(eventsList))
						{
							allStaffEventsList.addAll(eventsList);
						}
						
						if(Constants.SCHOOL_TEACHER.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()) || Constants.SCHOOL_ASST_STAFF.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()) || Constants.SCHOOL_HOD.equalsIgnoreCase(viewStaffPersonAccountDetails.getRoleName()))
							staffEventsList = adminManager.getAll(Events.class,"(staffEvent='T' OR staffEvent='A') and academicYearId = "+ getUserAcademicYearId() +" and endDate <'"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00' ORDER BY startDate");
						else
							staffEventsList = adminManager.getAll(Events.class," (staffEvent='N' OR staffEvent='A') and academicYearId = "+ getUserAcademicYearId() +" and endDate <'"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00' ORDER BY startDate");
						
						
						if(!ObjectFunctions.isNullOrEmpty(staffEventsList))
						{
							for( Events events : staffEventsList ) {
						    	events.setEventsAlbum(adminManager.getAll(EventsAlbum.class," eventId="+events.getId()));
						    	allStaffEventsList.add(events);
							}
						}
						staffEventsList = null;
						
						//For Calendar
						setEventsList(allStaffEventsList);
						getEventsList().addAll(getObjectList());
						for( Events events : allStaffEventsList ) {
						    if(allStaffEventsSet.add(events.getId())) {
						    	getTempList().add(events);
						    }
						}
						
						staffEventsList = null;
					}
					viewStaffPersonAccountDetails = null;
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
   /* @Description 15rd Apr cvs: Modularization set the quize list */
   @Actions({
		@Action(value = "ajaxDoGetCreateQuiz", results = { @Result(location = "quiz/ajaxCreateQuiz.jsp", name = "success") }),
		@Action(value = "ajaxDoAddNewQuizQuestion", results = { @Result(location = "quiz/newQuizQuestion.jsp", name = "success") }),
		@Action(value = "ajaxDoStaffQuestionResults", results = { @Result(location = "quiz/viewStaffQuizResultsLists.jsp", name = "success") })
		})
	public String ajaxDoGeCreateQuiz() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGeCreateQuiz' method");
		}
		try {
			setQuizList(staffManager.getQuizListWithCustId(getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 15rd Apr cvs: Modularization set the ViewStaffTimeTable */
   @Actions({ @Action(value = "ajaxDoViewStaffTimeTable", results = { @Result(location = "class/ajaxManageStaffTimeTable.jsp", name = "success") }) })
		public String ajaxDoViewStaffTimeTable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStaffTimeTable' method in staff Action");
		}
		try
		{
			Object[] staffDetails = staffManager.get("select accountId,staffId from vw_staffDetails where accountId="+getUser().getId());
			if(!ObjectFunctions.isNullOrEmpty(staffDetails) && !ObjectFunctions.isNullOrEmpty(staffDetails[1])){
				setTempId(Long.valueOf(staffDetails[1].toString()));
				ajaxGetStaffTimeTable();
			}
			staffDetails=null;
			/*below line used to when admin upload the timetabe doc it will show the login staff and check the file exit or not */
			Customer customer = getCustomerByCustId();
			String  filePath = "userFiles/timetable/"+customer.getId()+"/"+getUserAcademicYearId()+"/stafftimetables/"+getUser().getId()+".html".toString();
			File directory = new File(getSession().getServletContext().getRealPath(filePath.toString()));
			if(directory.exists()){
				setAnyTitle(filePath);
			}
			customer=null;
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 15rd Apr cvs: Modularization set the ExamTypeList */
   @Actions( { @Action(value = "ajaxGetExamTypesAndStaffSubjects", results = { @Result(location = "ajaxExamTypesAndStaffSubjets.jsp", name = "success") }) })
		public String ajaxGetExamTypesAndStaffSubjects() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetExamTypesAndStaffSubjects' method");
		}
		try {
			if(getClassName().getId() > 0) {
				setExamTypeList(getAllExamTypesByClassId(getClassName().getId(),0,getUserCustId(),getUserAcademicYearId()));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 15rd Apr cvs: Modularization show the pie charts in subject wise marks */
   @Actions({
		@Action(value = "ajaxSubjectPerformance", results = { @Result(type = "json", name = "success", params = {"includeProperties","tempString" }) }) })
		public String ajaxSubjectPerformance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSubjectPerformance' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getExamType()) && getClassName().getId() > 0 && getUserAcademicYearId() > 0 && getStaff().getId() > 0 ){
				List<ViewStaffSubjectsDetails> staffSubjects=null;
				List<StudyClass> studyClassesList=studentManager.getStudyClassesByClassNameClassId(getClassName().getId(),getUserCustId(),getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(studyClassesList)){
						staffSubjects=staffManager.getTeacherSubjectsByStaffIdAndClassIdGroupByclassIdSubjectId(getStaff().getId(),getClassName().getId(),getUserAcademicYearId());
						if(ObjectFunctions.isNotNullOrEmpty(staffSubjects)){
							JSONObject ja = new JSONObject();
							JSONArray catSubjects=new JSONArray();
							JSONArray[] subAvgsAry=new JSONArray[studyClassesList.size()];
							JSONObject[] subAvgsObj =new JSONObject[studyClassesList.size()];
							JSONArray totalSeriesArray=new JSONArray();
							for(int i=0;i<studyClassesList.size();i++){
								subAvgsAry[i]=new JSONArray();
								subAvgsObj[i]=new JSONObject();
							}
							for(ViewStaffSubjectsDetails subject:staffSubjects){
								catSubjects.put(subject.getSubjectName());
								if(!ObjectFunctions.isNullOrEmpty(subject)){
									int i=0;
									for(StudyClass studyClass : studyClassesList){ 
										Object[] studentMarks = staffManager.get("select IFNULL(count(*),0) as studentcount,IFNULL(sum(obtainedMarks),0) as obtainedMarks  from vw_studentMarksDetails where examTypeId="+ getExamType()+ " and subjectId="+ subject.getStudySubjectId()+" and classSectionId="+ studyClass.getId()+ " and academicYearId="+ getUserAcademicYearId()+" group by classSectionId ");
										if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
											subAvgsAry[i].put(roundTwoDecimals(Double.valueOf(studentMarks[1].toString())/Double.valueOf(studentMarks[0].toString())));
										}
										i++;
									}
								}
							}
							int i=0;
							for(StudyClass studyClass : studyClassesList){
								subAvgsObj[i].put("name", studyClass.getClassAndSection());
								subAvgsObj[i].put("data", subAvgsAry[i]);
								totalSeriesArray.put(subAvgsObj[i]);
								i++;
							}
							ja.put("series", totalSeriesArray);
							ja.put("categories", catSubjects);
							if(getViewStudentMarksDetails().getMaxMarks() > 0)
							ja.put("maxMarks", getViewStudentMarksDetails().getMaxMarks());
						    getResponse().getOutputStream().print(ja.toString());
						}
					}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
   /* @Description 15rd Apr cvs: Modularization show and hilight Events in claender */
   @Actions({ @Action(value = "ajaxCancelRegistration", results = { @Result(location = "viewStaffEventsLists.jsp", name = "success") }) })
		public String ajaxStaffAllEvents() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxStaffAllEvents' method");
			}
			try {
				setObjectList(adminManager.getAllByCustId("Events",getUserCustId(), getUserAcademicYearId()));
			}
			catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
   /* @Description 15rd Apr cvs: Modularization show weekly wise claender */
   @Actions( { @Action(value = "ajaxViewWeekdayCalendar", results = { @Result(location = "viewWeekCalendar.jsp", name = "success") }) })
	public String ajaxViewWeekdayCalendar() throws URTUniversalException {
	    if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxViewWeekdayCalendar' method");
	    }
	    try {
           Calendar calendar =Calendar.getInstance();
           calendar.add(Calendar.DATE, 7);
           setObjectList(adminManager.getEventsByDates(getUserCustId(),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, calendar.getTime())));
           if (ObjectFunctions.isNotNullOrEmpty(getObjectList())) {
               Collections.sort(getObjectList());
           }
           setHolidayBoardMessagesList(adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getAcademicYearId(),null,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, calendar.getTime()),null,null,"H",0,"OrderByDate",null));
           if (ObjectFunctions.isNotNullOrEmpty(getHolidayBoardMessagesList())) {
               Collections.sort(getHolidayBoardMessagesList());
           }
   		}
       catch (Exception ex) {
   	 	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
       }
       	return SUCCESS;
	}

   /* @Description 15rd Apr cvs: Modularization staffEventDetail */
	@Actions( {
		@Action(value = "ajaxGetStaffEventDetails", results = { @Result(location = "staffEventDetails.jsp", name = "success") }) })
		public String getStaffEventDetails() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'getStaffEventDetails' method");
			}
			try {
				//setEventListByDate(staffManager.getEventsByDate(getAnyTitle().replace('/', '-')));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		
			return SUCCESS;
	}
   /* @Description 15rd Apr cvs: Modularization  remove the unnecessary  variables bellow method */  
   @Actions( { @Action(value = "ajaxAddQuizQuestion", results = { @Result(location = "quiz/viewStaffQuizLists.jsp", name = "success") }) })
	public String ajaxAddQuizQuestion() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddQuizQuestion' method");
		}
		try {
			Date startDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getParamValue("startDate")));
			Date endDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getParamValue("endDate")));
			if(getTempId() > 0){
				String selectdDate= DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date());
				int questionRows=staffManager.getUpdateQuizQuestionStatus(getTempId(),getUserCustId(),selectdDate);
				if(questionRows>0){
					staffManager.getUpdateStudentQuizStatus(Long.valueOf(getUserCustId()));
				}
			}
			QuizQuestion quizQuestion = new QuizQuestion();
			quizQuestion.setQuizId(getTempId());
			quizQuestion.setQuestionName(getBankName());
			quizQuestion.setStatus("A");
			quizQuestion.setCustId(getUserCustId());
			quizQuestion.setStartDate(startDate);
			quizQuestion.setEndDate(endDate);
			quizQuestion.setTeacherId(getUser().getId());
			if (!StringFunctions.isNullOrEmpty(getSelectedId())) {
				QuestionAnswer questionAnswerA = new QuestionAnswer();
				if ("A".equalsIgnoreCase(getAnyTitle())) {
					questionAnswerA.setCorrectAnswer(Constants.YES_STRING);
				} else {
					questionAnswerA.setCorrectAnswer(Constants.NO_STRING);
				}
				questionAnswerA.setAnserOptions("A");
				questionAnswerA.setQuestionAnswer(getSelectedId());
				questionAnswerA.setCustId(getUserCustId());
				quizQuestion.addQuesationAnswers(questionAnswerA);
			}
			if (!StringFunctions.isNullOrEmpty(getTempString())) {
				QuestionAnswer questionAnswerB = new QuestionAnswer();
				if ("B".equalsIgnoreCase(getAnyTitle())) {
					questionAnswerB.setCorrectAnswer(Constants.YES_STRING);
				} else {
					questionAnswerB.setCorrectAnswer(Constants.NO_STRING);
				}
				questionAnswerB.setAnserOptions("B");
				questionAnswerB.setQuestionAnswer(getTempString());
				questionAnswerB.setCustId(getUserCustId());
				quizQuestion.addQuesationAnswers(questionAnswerB);
			}
			if (!StringFunctions.isNullOrEmpty(getBalance())) {
				QuestionAnswer questionAnswerC = new QuestionAnswer();
				if ("C".equalsIgnoreCase(getAnyTitle())) {
					questionAnswerC.setCorrectAnswer(Constants.YES_STRING);
				} else {
					questionAnswerC.setCorrectAnswer(Constants.NO_STRING);
				}
				questionAnswerC.setAnserOptions("C");
				questionAnswerC.setQuestionAnswer(getBalance());
				questionAnswerC.setCustId(getUserCustId());
				quizQuestion.addQuesationAnswers(questionAnswerC);
			}
			if (!StringFunctions.isNullOrEmpty(getSubject())) {
				QuestionAnswer questionAnswerD = new QuestionAnswer();
				if ("D".equalsIgnoreCase(getAnyTitle())) {
					questionAnswerD.setCorrectAnswer(Constants.YES_STRING);
				} else {
					questionAnswerD.setCorrectAnswer(Constants.NO_STRING);
				}
				questionAnswerD.setAnserOptions("D");
				questionAnswerD.setQuestionAnswer(getSubject());
				questionAnswerD.setCustId(getUserCustId());
				quizQuestion.addQuesationAnswers(questionAnswerD);
				super.addActionMessage("Quiz question created successfully.");
			}
			staffManager.save(quizQuestion);
			ajaxDoGeCreateQuiz();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   
   @Actions({
		@Action(value = "ajaxDoGetleaveReport", results = { @Result(location = "leaves/staffLeave.jsp", name = "success"),
				 @Result(location = "staffLeave.jsp", name = "exceedLeaves") 		
		})})
		public String staffLeave() throws URTUniversalException {  
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetleaveReport' method");
		}
		try {
			loadAcademicYearStartDateAndDates(getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(getStartDate()) && !ObjectFunctions.isNullOrEmpty(getEndDate())){
					if(!StringFunctions.isNullOrEmpty(getAnyId()))
					{
						setLeave((Leave)staffManager.get(Leave.class, Long.valueOf(getAnyId())));
					}
					setAcademicYear(getCurrentAcademicYear());
					if(!ObjectFunctions.isNullOrEmpty(getAcademicYear())){
						ViewUserRoles userRoles=adminManager.getViewUserRolesByUserIdAndCustId(getUser().getId(),getUserCustId(),Constants.NO_STRING);
						if(!ObjectFunctions.isNullOrEmpty(userRoles)){
							String staffType = userRoles.getRoleName();
							if(!StringFunctions.isNullOrEmpty(staffType))
							{
								generateStaffApprovararsList(staffType);
								setLeaveManagement(adminManager.getLeaveManagementByRoleName(userRoles.getRoleId(), getUserCustId(),getUserAcademicYearId()));
							}
						}
					}
					staffLeavesManagement(getUser().getId());
					double casualDays=getCasualLeave();
					double sickLeaves=getSickLeave();
					double earnedLeaves=getEarnedLeave();
					if(!ObjectFunctions.isNullOrEmpty(getLeaveManagement()) && (getLeaveManagement().getCasualLeaves() > 0 || getLeaveManagement().getSickLeaves() > 0 || getLeaveManagement().getEarnedLeaves() > 0)) {
						List<Leave> approvedLeavesList = staffManager.getLeavesListByAccountId(getUser().getId(),Constants.PENDING_STATUS, getUserCustId(),getAcademicYear().getId());
						if (!ObjectFunctions.isNullOrEmpty(approvedLeavesList)) {
							for(Leave leave:approvedLeavesList){
								if(leave.getLeaveType().equalsIgnoreCase("CL")){
									casualDays+=leave.getLeavesCount();
								}else if(leave.getLeaveType().equalsIgnoreCase("SL")){
									sickLeaves+=leave.getLeavesCount();
								}else if(leave.getLeaveType().equalsIgnoreCase("EL")){
									earnedLeaves+=leave.getLeavesCount();
								}
								leave=null;
							}
						}
						if(!ObjectFunctions.isNullOrEmpty(getLeave())){
							if(getLeave().getLeaveType().equalsIgnoreCase("CL")){
								casualDays-=getLeave().getLeavesCount();
							}else if(getLeave().getLeaveType().equalsIgnoreCase("SL")){
								sickLeaves-=getLeave().getLeavesCount();
							}
							else if(getLeave().getLeaveType().equalsIgnoreCase("EL")){
								earnedLeaves-=getLeave().getLeavesCount();
							}
						}
						prepareLeaveTypes();
						// For checking in page
						if(!ObjectFunctions.isNullOrEmpty(getLeaveManagement())){
							setCasualLeave(getLeaveManagement().getCasualLeaves() - casualDays);
							setSickLeave(getLeaveManagement().getSickLeaves() - sickLeaves);
							if(casualDays >= getLeaveManagement().getCasualLeaves()){
								getLeaveTypes().remove("CL");
							}
							if(sickLeaves >= getLeaveManagement().getSickLeaves()){
								getLeaveTypes().remove("SL");
							}
							if(sickLeaves >= getLeaveManagement().getEarnedLeaves()){
								getLeaveTypes().remove("EL");
							}
							if(getLeaveTypes().size() < 2){
								getLeaveTypes().put("PL", "Pay Leave");
							}
						}
						approvedLeavesList = null;
					}else{
						setTempString("LNA"); //Ravi Theja Here set tempString LNA, LNA means Leaves count not assign to particualr UserRoles.
					}
				}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 15rd Apr cvs: Modularization  below method apply leave  staff*/  
   @Actions({
		@Action(value = "ajaxAddleaveReport", results = {  @Result(location = "leaves/ajaxViewStaffLeaves.jsp", name = "success") }) })
	public String ajaxAddleaveReport() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxAddleaveReport' method");
	}
	try {
	AcademicYear academicYear=getCurrentAcademicYear();
	if(!ObjectFunctions.isNullOrEmpty(getLeave())) {
		if(!ObjectFunctions.isNullOrEmpty(academicYear)){
			prepareLeaveTypes();
			long leaveId=0;
			if(!StringFunctions.isNullOrEmpty(getAnyId()))
				leaveId= Long.valueOf(getAnyId());
			int responseCode =staffManager.submitOrEditLeaves(getLeave(),academicYear,getUser(),getUserCustId(),leaveId,"WEB","");
			if(responseCode == 0)
				super.addActionMessage("Leave posted successfully.");
			else if(responseCode == 6)
				super.addActionMessage("Leave updated successfully.");
			else if(responseCode == 3)
				super.addActionError("The selected days are part of holidays.Please change selections.");
			else if(responseCode==7)
				super.addActionMessage("Leave updated successfully but sms is not delivered due to insufficient sms balance.");
			else if(responseCode==8)
				super.addActionMessage("Leave posted successfully but sms is not delivered due to insufficient sms balance.");
			}
	}
	ajaxViewStaffLeaves();
	}
	catch(Exception ex) {
		log.error("Entering into 'catch block':"+ex.getMessage());
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	} //TODO: SMS doGetLeaveDetails();
	return SUCCESS;
   }
   /* @Description 15rd Apr cvs: Modularization  remove the unnecessary  variables bellow method */  
   @Actions( { @Action(value = "ajaxAddQuiz", results = { @Result(location = "quiz/viewStaffQuizLists.jsp", name = "success") }) })
	public String ajaxAddQuiz() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddQuiz' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getTitle())) {
					Quiz quiz= new Quiz();
					quiz.setCustId(getUserCustId());
					quiz.setTitle(getTitle());
					quiz.setDescription(getDescription());
					staffManager.save(quiz);
					super.addActionMessage("Quiz added successfully.");
					quiz=null;
			}
			ajaxDoGeCreateQuiz();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
    }
   /* @Description 15rd Apr cvs: Modularization  below method reuseable the method*/  
   /*Change by Venkatesh on 09/12/2013 */  
	@Actions( { @Action(value = "ajaxDoGetLeaveDetailsLeft", results = { @Result(location = "leaves/leaveHome.jsp", name = "success") }) })
	public String ajaxDoGetLeaveDetailsLeft() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetLeaveDetailsLeft' method");
		}
		try{
			ClassTeacher classTeacher=staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
				setClassTeacher(classTeacher);
			}
			getSmsCount();
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   	
   /* @Description 15rd Apr cvs: Modularization  bellow method used to get dash boadrd  */
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * April 15, 2013    Cvs		        Modularization  bellow method used to get dash boadrd
 * May 9, 2013		 Seshu				Changed method name for getting upcoming exam schedules	
/********************************************************************/	
      @Actions({
   		@Action(value = "staffHome", results = { @Result(location = "staffHome.jsp", name = "success") })})
   		public String home() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'Staff Home' method");
   		}
   		try
   		{
   			/* Need to work on this coming performance locking */
   			//ajaxClassWiseUpcomingExamSchedules();
   			getIsClassTeacherOrNot();
   			if(getUser().isOnlySchoolHod() || getUser().isSchoolTeacher() && getIsClassTeacherOrNot())
   			{
   				if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showReminder")))
   					getReminderToUserLogin();
   			}
   			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showTaskReminder")))
				getTaskReminderToUserLogin();
   			ajaxGetStaffStudyClasses();
   			ajaxShowTaskDetailsLoginUser();
   			getSchoolWideAlertsforAllRoles();
   			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("timetableGenerationByManual"))){
   				ajaxViewStaffAndStudentTimetableDetails(); // Manual Timetable
   			}
   		}
   		catch(Exception ex)
   		{
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
      
      /* @Description 15rd Apr cvs: Modularization  below method used to get exam schedules  */
      @Actions({
   		@Action(value = "staffClassHome", results = { @Result(location = "class/staffClassHome.jsp", name = "success" )})
   	})
   		public String staffClassHome() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'staffClassHome' method");
   		}
   		try {	
   			setTempBoolean(adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId()));
   			setAcademicYear(getCurrentAcademicYear());
   			ajaxGetStaffStudyClasses();
   			//setClassTeacher(staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId()));
   		}
   		catch(Exception ex)
   		{
   			log.error("Entering into 'catch block':"+ex.getMessage());
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
  /* @Description 15rd Apr cvs: Modularization  below method  remove the unnecessary code  change the adminAction to staffAction*/  
  	@Actions({
  		@Action(value = "ajaxGetStaffList", results = { @Result(location = "getStaffsList.jsp", name = "success" )}) })
  		public String ajaxGetStaffList() throws URTUniversalException {
  		if (log.isDebugEnabled()) {
  			log.debug("Entering 'ajaxGetStaffList' method");
  		}
  		try {
  			List<ViewStaffPersonAccountDetails> StaffDetails=adminManager.getAllStaffList(getUserCustId(),getUserAcademicYearId());
  			if(ObjectFunctions.isNotNullOrEmpty(StaffDetails)){
  				for(ViewStaffPersonAccountDetails staffDetail:StaffDetails) {
  					if(!ObjectFunctions.isNullOrEmpty(staffDetail)) {
  						getAllStaffList().add(staffDetail.getUsername());
  						getStaffsList().add(staffDetail.getFirstName());
  					}
  				}
  				StaffDetails=null;
  			}
  		}
  		catch(Exception ex) {
  			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		}
  		return SUCCESS;
  	}

   /*  =============================================code move adminAction to staffAction  below ================================== */
   /* @Description 15rd Apr cvs: Modularization  below method  disable the staff   change the adminAction to staffAction*/  
   @Actions( { @Action(value = "ajaxManageStaff", results = { @Result(location = "manageStaff/ajaxAddStaff.jsp", name = "success") }) })
	public String manageStaff() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageStaff' method");
		}
		try {
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			prepareTeachingRolesMap();
			prepareNonTeachingRolesMap(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
 /* @Description 15rd Apr cvs: Modularization  below method  disable the staff   change the adminAction to staffAction*/  
	@Actions( { @Action(value = "ajaxViewExpiredStaffs", results = { @Result(location = "disableStaff/ajaxViewExpiredStaffsList.jsp", name = "success") }) })
	public String ajaxViewExpiredStaffs() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewExpiredStaffs' method");
		}
		try {
			setObjectList(staffManager.getAll(ViewStaffPersonAccountDetails.class,"accountExpired='"+Constants.YES_STRING+"' and custId="+getUserCustId()+" and academicYearId<="+getUserAcademicYearId()+" and description is not null"));
			if(ObjectFunctions.isNotNullOrEmpty(getObjectList())) 
					Collections.sort(getObjectList());
 		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Description 15rd Apr cvs: Modularization  below method  enable the staff   change the adminAction to staffAction*/  
	@Actions( { @Action(value = "ajaxEnableStaffDetails", results = { @Result(location = "disableStaff/ajaxViewExpiredStaffsList.jsp", name = "success") }) })
	public String ajaxEnableStaffDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEnableStaffDetails' method");
		}
		try {
			boolean sendSms = false;
			if(getTempId()>0){  //here tempId is accountid
				Person person = null;
				String userName=null;
				String newPassword =null;
				User user =(User)staffManager.get(User.class,getTempId());
				user.setAccountExpired(false);
				user.setEnabled(true);
				user.setCreatedById(getUser().getId());
				user.setLastUpdatedById(getUser().getId());
				user.setCreatedDate(new Date());
				//Person person=(Person)staffManager.get(Person.class,user.getPerson().getId());
				String roleName=user.getUserRoleName();
				Customer customer =getCustomerByCustId();
				/*Ganesh : Below code we implemented for when we enable staff we will generate username with mobile number if that mobile number available in out system. If the mobile number not available we will keep same username what we have previously */
				if(!StringFunctions.isNullOrEmpty(user.getPerson().getMobileNumber())){
					userName = staffSecondaryUsernameWithMobileNumber(roleName,user.getPerson().getMobileNumber());
					//user.setUsername(userName);
					newPassword = StringUtil.generateRandomString();
					User staffUser =staffUsernameAvailabulity(userName);
					if(!ObjectFunctions.isNullOrEmpty(staffUser)){
						/*Ganesh If enter mobile number already exist with user we will check user if user is staff we will not allow to add user if the existing user is parent we will allow the user and add "S" before the username create the username even that username available with "S" also we will restrict user.*/
						if(staffUser.isParent()){
							userName ="S"+userName;
							staffUser =staffUsernameAvailabulity(userName);
							if(ObjectFunctions.isNullOrEmpty(staffUser)){
								user.setUsername(userName);
								sendSms=true;
							}else{
								String secondaryUserName=StringFunctions.stripSymbols(customer.getCustomerShortName().toLowerCase()+ user.getFullPersonName());
								boolean secUsernameExist=	staffSecondaryUsernameAvailabulity(secondaryUserName);
								if(!secUsernameExist){
									String randamNumber=Math.round(Math.random()*10)+DateFormatter.formatDate(DateFormatter.HHMM_GMT_PATTERN, new Date());
									secondaryUserName=secondaryUserName+randamNumber;
									secUsernameExist = staffSecondaryUsernameAvailabulity(secondaryUserName);
								}
								if(!secUsernameExist){
									user.setUsername(secondaryUserName);
									user.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
									sendSms=true;
								}
							}
						}else{
							String secondaryUserName=StringFunctions.stripSymbols(customer.getCustomerShortName().toLowerCase()+ user.getFullPersonName());
							boolean secUsernameExist=	staffSecondaryUsernameAvailabulity(secondaryUserName);
							if(!secUsernameExist){
								String randamNumber=Math.round(Math.random()*10)+DateFormatter.formatDate(DateFormatter.HHMM_GMT_PATTERN, new Date());
								secondaryUserName=secondaryUserName+randamNumber;
								secUsernameExist = staffSecondaryUsernameAvailabulity(secondaryUserName);
							}
							if(!secUsernameExist){
								user.setUsername(secondaryUserName);
								user.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
								sendSms=true;
							}
						}
					}
				}
				
				person=user.getPerson();
				person.setCreatedById(getUser().getId());
				person.setLastUpdatedById(getUser().getId());
				person.setCreatedDate(new Date());
				person.setContractStartDate(null);
				person.setContractEndDate(null);
				user.setPerson(person);
				Staff staff = (Staff) staffManager.get(Staff.class, " accountId = " + getTempId() + " and status = 'N'");
				staff.setDescription(null);
				staff.setStatus(Constants.YES_STRING);
				staff.setLastUpdatedById(getUser().getId());
				staff.setCreatedDate(new Date());
				staff.setAccount(user);
				staff =(Staff)staffManager.save(staff);
				if(sendSms){
					if(!ObjectFunctions.isNullOrEmpty(staff)){
						if(customer.isCheckMobileService()){
							sendLogincredentailsToStaff(staff,customer,getCurrentAcademicYear(),newPassword);
						}
					}
				}
				communicationManager.sendStaffNotificationWhenEditOrAdd(staff.getId()," Admin made "+user.getPerson().getFullPersonName()+" has active.","Admin made staff has active.");
				super.addActionMessage("Staff Acivated successfully.");
				user=null;
				person=null;
				staff=null;
			}
			ajaxViewExpiredStaffs();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Description 15rd Apr cvs: Modularization  below method  */  
	@Actions( { @Action(value = "getStaffCalendar", results = { @Result(location = "ajaxStaffCalendar.jsp", name = "success") }) })
	@Action(value = "ajaxAddStaffEvent1", results = { @Result(location = "addStaffEvent.jsp", name = "success") })
	public String addStaffEvent() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddStaffEvent' method");
		}
		try {
			AcademicYear academicYear=getCurrentAcademicYear();
			if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()) && !StringFunctions.isNullOrEmpty(getClassId())){
				Object[] classNameClassIds= staffManager.get("select classId,className from vw_classSectionDetails where custId="+getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassId());
	 			if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
	 				setHolidayBoardMessagesList(staffManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()),null,null,classNameClassIds[0].toString(),null,null,0,"featureHolidayDate",null));
	 			}
	 		  }else{
	 			 setHolidayBoardMessagesList(staffManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()),null,null,null,null,null,0,"featureHolidayDate",null));
	 		}
			academicYear=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/* @Description 15rd Apr cvs: Modularization  below method  show the staff/non staff   change the adminAction to staffAction*/  
	@Actions( { @Action(value = "ajaxDoManageStaff", results = { @Result(location = "manageStaff/ajaxManageStaff.jsp", name = "success") }) })
    public String ajaxDoManageStaff() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDoManageStaff' method");
	}
	try {
		Customer cust = getCustomerByCustId();
		prepareStaffRolesMap(cust);
		prepareNonTeachingRolesMap(cust);
		cust = null;
		if(getUserCustId() > 0){
			StringBuffer sqlQuery = new StringBuffer("select  distinct(sd.staffId),sd.firstName,sd.lastName,sd.fullName,sd.mobileNumber,sd.roleDescription,sd.roleName,sd.imagePath,sd.thumbNail  from vw_staffDetails sd") 
        	.append(" where sd.custId=").append(getUserCustId()).append(" and sd.academicYearId <="+getUserAcademicYearId()+"").append(" and sd.status='"+Constants.YES_STRING+"'").append(" order by sd.roleName");
        	setObjectList(staffManager.getAll(sqlQuery.toString()));
		}  
			/*setObjectList(staffManager.getAll(ViewStaffPersonAccountDetails.class,"custId="+getUserCustId()+" and  academicYearId="+getUserAcademicYearId()+" and status='Y' order by roleName"));*/
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
    }
	/* @Description 15rd Apr cvs: Modularization  below method  remove the unnecessary code  change the adminAction to staffAction*/  
	@Actions( { @Action(value = "ajaxDoAddStaff", results = { @Result(location = "manageStaff/ajaxAddStaff.jsp", name = "success") }) })
	public String doAddStaff() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'doAddStaff' method");
		}
		try {
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			setStudySubjectList(adminManager.GetAllStudySubjects(getUserCustId(),getUserAcademicYearId()));
			Customer cust = getCustomerByCustId();
			prepareTeachingRolesMap();
			//prepareNonTeachingRolesMap(cust);
			prepareManagementRolesMap();
			//prepareStaffRolesMap(cust);
			setTempList((List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST));
			setTempList1(adminManager.getAllCommonTypesByCustIdandType(getUserCustId(),"RELIGION"));
			setCastSettingList(adminManager.getAllByCustId("CastSettings",getUserCustId(),0));
			setSchoolCategoriesList(adminManager.getAllByCustId("SchoolCategory", getUserCustId(),0));
			List<ViewStaffPersonAccountDetails> schoolHodsList = adminManager.getViewStaffDetailsByRoleName(Constants.SCHOOL_HOD, getUserCustId(),Constants.YES_STRING);
			if (!ObjectFunctions.isNullOrEmpty(schoolHodsList)) {
				for (ViewStaffPersonAccountDetails classTeacher:schoolHodsList) {
					if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
						getSelectboxMap().put(classTeacher.getUsername(),classTeacher.getPersonFullName());
						classTeacher = null;
					}
				}
				schoolHodsList=null;
			}
			ajaxDoManageStaff();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	

/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * April 15, 2013    cvs		        Modularization  below method  remove the unnecessary code  change the adminAction to staffAction
 * Sep 30, 2013		 Seshu				Validate whether staff number is already assigned to some other staff. If not assigned to
 * 										other staff allow user to create staff.
/********************************************************************/  
	@Actions( { @Action(value = "ajaxDoAddNewStaff", results = { @Result(location = "manageStaff/ajaxDoAddNewStaff.jsp", name = "success"),
			@Result(location = "manageStaff/ajaxAddStaff.jsp", name = "userNameAlreadyAdded") }) })
	public String ajaxDoAddNewStaff() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddNewStaff' method");
		}
		try {
			String userName = null;
			if(!ObjectFunctions.isNullOrEmpty(getAnyId()));{
				log.debug("Selected Subjects Id " +getAnyId());
				setAnyId(getAnyId());
			}
			getSession().removeAttribute("objectList");
			getSession().removeAttribute("NewUserForStaff");
			getSession().removeAttribute("PersonForStaff");
			getSession().removeAttribute("StaffForStaff");
			getSession().removeAttribute("studyClassList");
			getSession().removeAttribute("StaffSubcaste");
			if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
				getSession().setAttribute("subjectsList", getObjectList());
			}
			if (!ObjectFunctions.isNullOrEmpty(getTempList2())) {
				getSession().setAttribute("studyClassList", getTempList2());
			}
			if (!ObjectFunctions.isNullOrEmpty(getUserVo())) {
				
				setCustomer((Customer) staffManager.get(Customer.class, getUserCustId()));
				 UserImage attachment;
				 long staffImage = 0;
				if (getUploadFileName() != null && !ObjectFunctions.isNullOrEmpty(getCustomer())) {
                   try {
                           attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE, getCustomer().getId(),getUserAcademicYearId(),staffImage);
                           if (!ObjectFunctions.isNullOrEmpty(attachment)) {
                        	   getUserVo().setUserImageId(attachment.getId());
                           }
                           attachment = null;
                   } catch (Throwable ex) {
                           ex.printStackTrace();
                   }
               }
				else if(!StringFunctions.isNullOrEmpty(getParamValue("customerImage"))){
					try {
						attachment = captureStudentImage(getParamValue("customerImage"),Constants.FILE_TYPE_IMAGE,getCustomer());
						if (!ObjectFunctions.isNullOrEmpty(attachment)) {
							getUserVo().setUserImageId(attachment.getId());
						}
						attachment =null;
					}
					catch (Throwable ex) {
							ex.printStackTrace();
						}

					}
				getSession().setAttribute("objectList", getAnyId());
				getSession().setAttribute("NewUserForStaff", getUserVo());
				getSession().setAttribute("PersonForStaff", getPersonVo());
				getSession().setAttribute("StaffForStaff", getStaffVo());
				getSession().setAttribute("PrimaryAddress", getAddressVo());
				getSession().setAttribute("StaffSubcaste", getViewStaffPersonAccountDetails());
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				setStudyClassId(getParamValue("studyClassId"));
				if (!ObjectFunctions.isNullOrEmpty(getCustomer())) {
					setAnyTitle("staff");
				}
				if(StringFunctions.isNotNullOrEmpty(getUserVo().getStaffNumber())){
					User userExist = (User)staffManager.get(User.class, new StringBuffer("custId=").append(getUserCustId()).append(" and staffNumber='").append(getUserVo().getStaffNumber().trim()).append("'").toString());
					if(!ObjectFunctions.isNullOrEmpty(userExist)){
						super.addActionError(new StringBuffer(getUserVo().getStaffNumber()).append(" is already assigned to ").append(userExist.getFullPersonName()).append(". Please change staff number.").toString());
						doAddStaff();
						return "userNameAlreadyAdded";
					}else
						getUserVo().getStaffNumber().trim();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/* @Description 15rd Apr cvs: Modularization  change the adminAction to staffAction*/  
	@Actions( { @Action(value = "ajaxDocancelStaff", results = { @Result(location = "manageStaff/ajaxViewStaffList.jsp", name = "success") }) })
	public String docancelStaff() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'docancelStaff' method");
		}
		try {
			ajaxDoManageStaff();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Description 15rd Apr cvs: Modularization  below method*/  
	@Actions( { @Action(value = "ajaxDoEditQuestionAnswers", results = { @Result(location = "quiz/ajaxEditQuestionAnswer.jsp", name = "success") }) })
	public String ajaxDoEditQuestionAnswers() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoEditQuestionAnswers' method");
		}
		try {
			setQuizQuestion(staffManager.getQuizQuestionId(getTempId(),getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/* @Description 15rd Apr cvs: Modularization  change the adminAction to staffAction*/ 
	@Actions( { @Action(value = "ajaxAddStaff", results = { @Result(location = "manageStaff/ajaxViewStaffList.jsp", name = "success"),	
			@Result(location = "manageStaff/ajaxViewStaffList.jsp", name = "organizationShortName")}) })
	public String addStaff() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'addStaff' method");
		}
		String  userName =null;
		String  secondaryUserName =null;
		String newPassword = null;
		try {
			UserVO userVO = (UserVO) getSession().getAttribute("NewUserForStaff");
			PersonVO staffPersonVo = (PersonVO) getSession().getAttribute("PersonForStaff");
			AddressVO staffAaddressVo = (AddressVO) getSession().getAttribute("PrimaryAddress");
			StaffVO staffVO=(StaffVO) getSession().getAttribute("StaffForStaff");
			ViewStaffPersonAccountDetails vsp = (ViewStaffPersonAccountDetails) getSession().getAttribute("StaffSubcaste");
			StaffStatutoryVO staffStatutoryVo=new StaffStatutoryVO();
			SalaryVO salaryVo = new SalaryVO();
			User createduser = new User();
			Address primAddress = new Address();
			Address tempararyAddress = new Address();
			StaffHistory staffHistory = new StaffHistory();
			Staff staff =new Staff();
			String roleName = null;
			 String randamNumber=null;
			setAcademicYear(getCurrentAcademicYear());
			setCustomer(getCustomerByCustId());
			boolean userExists=false;
			if (!ObjectFunctions.isNullOrEmpty(userVO) && !ObjectFunctions.isNullOrEmpty(getAcademicYear())) {
			//if (!ObjectFunctions.isNullOrEmpty(getNewUser()) && !ObjectFunctions.isNullOrEmpty(getAcademicYear())) {
				roleName = getTeachingRoleName().replaceAll(",", "");
				log.debug(roleName);
				Role role = staffManager.getRoleByName(roleName);
				if(!ObjectFunctions.isNullOrEmpty(getCustomer().getCustomerShortName()) && !StringFunctions.isNullOrEmpty(staffPersonVo.getFirstName())){
					User user=null;
					userName =staffPersonVo.getMobileNumber();
					user =staffUsernameAvailabulity(userName);
					if(!ObjectFunctions.isNullOrEmpty(user)){
						/*Ganesh If enter mobile number already exist with user we will check user if user is staff we will not allow to add user if the existing user is parent we will allow the user and add "S" before the username create the username even that username available with "S" also we will restrict user.*/
						if(user.isParent()){
							userName ="S"+userName;
							user =staffUsernameAvailabulity(userName);
							if(!ObjectFunctions.isNullOrEmpty(user)){
								super.addActionError("Mobile number already available.");
								ajaxDoManageStaff();
								return "organizationShortName";
							}
						}else{
							super.addActionError("Mobile number already available.");
							ajaxDoManageStaff();
							return "organizationShortName";
						}
					}
					secondaryUserName=StringFunctions.stripSymbols(getCustomer().getCustomerShortName().toLowerCase()+ staffPersonVo.getFirstName().toLowerCase()+ staffPersonVo.getLastName().toLowerCase());
					//staffNumberExist = adminManager.getUserByUserName(secondaryUserName);
					boolean secUsernameExist=	staffSecondaryUsernameAvailabulity(secondaryUserName);
					if(!secUsernameExist){
						randamNumber=Math.round(Math.random()*10)+DateFormatter.formatDate(DateFormatter.HHMM_GMT_PATTERN, new Date());
						secondaryUserName=secondaryUserName+randamNumber;
					}
					secUsernameExist = staffSecondaryUsernameAvailabulity(secondaryUserName);
					if(!secUsernameExist){
						randamNumber=Math.round(Math.random()*10)+DateFormatter.formatDate(DateFormatter.HHMM_GMT_PATTERN, new Date());
						secondaryUserName=secondaryUserName+randamNumber;
					}
					if(StringFunctions.isNullOrEmpty(userName)){
						userName=secondaryUserName;
					}
						
				}else{
					super.addActionError("Please update organization shortname. This is used to prefix the username.");
					ajaxDoManageStaff();
					return "organizationShortName";
				}
				if (!ObjectFunctions.isNullOrEmpty(getCustomer().getCustomerShortName())){
					if(!ObjectFunctions.isNullOrEmpty(staffPersonVo))
					{
						staffPersonVo.setPhoneNumber(getPersonVo().getPhoneNumber());
						staffPersonVo.setPanNumber(getPersonVo().getPanNumber());
						staffPersonVo.setGpfNumber(getPersonVo().getGpfNumber());
						staffPersonVo.setOfficeNumber(getPersonVo().getOfficeNumber());
						staffPersonVo.setBankName(getPersonVo().getBankName());
						staffPersonVo.setBankAccountNumber(getPersonVo().getBankAccountNumber());
						staffPersonVo.setBankBranchName(getPersonVo().getBankBranchName());
						staffPersonVo.setIfscCode(getPersonVo().getIfscCode());
						staffPersonVo.setCastId(staffPersonVo.getCastId());
						staffPersonVo.setSubCastId(vsp.getSubCastId());
						staffPersonVo.setAadharNumber(staffPersonVo.getAadharNumber());
						staffPersonVo.setAnotherMobileNumber(staffPersonVo.getFatherContactNumber());
						staffPersonVo.setAddressType("R");
						staffPersonVo.setStaffLocation(getPersonVo().getStaffLocation());
						staffPersonVo.setSalaryPaymentMode(getPersonVo().getSalaryPaymentMode());
						if(getFileUpload().size()!=0){
							for(int i=0;i<getFileUpload().size();i++){
								 if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i))){
									 staffPersonVo.setIsDocsUploaded(Constants.YES_STRING);
					    		 }else
									 staffPersonVo.setIsDocsUploaded(Constants.NO_STRING);
					    	 }
						} 
						staffPersonVo.setStaffUniqueNumber(staffPersonVo.getStaffUniqueNumber());
						
					}
					if(!ObjectFunctions.isNullOrEmpty(userVO)){
						AddressVO  taddressVo = new AddressVO();
						AddressVO  paddressVo = new AddressVO();
						taddressVo.setAddressLine1(getUserVo().getTempararyAddressVo().getAddressLine1());
						taddressVo.setCity(getUserVo().getTempararyAddressVo().getCity());
						taddressVo.setState(getUserVo().getTempararyAddressVo().getState());
						taddressVo.setPostalCode(getUserVo().getTempararyAddressVo().getPostalCode());
						
						paddressVo.setAddressLine1(getAddressVo().getAddressLine1());
						paddressVo.setCity(getAddressVo().getCity());
						paddressVo.setState(getAddressVo().getState());
						if(!StringFunctions.isNullOrEmpty(getAddressVo().getState())){
							Object[] stateIdObj = staffManager.get("select id,stateCode from State where stateCode='"+getAddressVo().getState()+"'");
							if(!ObjectFunctions.isNullOrEmpty(stateIdObj))
								paddressVo.setStateId(Long.valueOf(stateIdObj[0].toString()));
						}
						paddressVo.setPostalCode(getAddressVo().getPostalCode());
						paddressVo.setEmail(staffAaddressVo.getEmail());
						userVO.setTempararyAddressVo(taddressVo);						
						userVO.setPrimaryAddressVo(paddressVo);
						userVO.setCustId(getUserCustId());			
						userVO.setPersonVo(staffPersonVo);
						userVO.setUsername(userName);
						userVO.setSecondaryUsername(secondaryUserName);
						newPassword = StringUtil.generateRandomString();
						userVO.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
						
						if(!ObjectFunctions.isNullOrEmpty(getStaffHistoryVo()))
						{
							if(!StringFunctions.isNullOrEmpty(getStaffHistoryVo().getSchoolName()))
							{
								getStaffHistoryVo().setCreatedById(getUser().getId());
								getStaffHistoryVo().setCreatedDate(new Date());
								getStaffHistoryVo().setLastAccessDate(new Date());
								getStaffHistoryVo().setLastUpdatedDate(new Date());
								getStaffHistoryVo().setLastUpdatedById(getUser().getId());
								getStaffHistoryVo().setCustId(getUserCustId());
								if(!ObjectFunctions.isNullOrEmpty(getStaffHistoryVo().getStartDate()) && !ObjectFunctions.isNullOrEmpty(getStaffHistoryVo().getEndDate()))
									getStaffHistoryVo().setExperience(Double.valueOf(DateFunctions.calculateExperience(getStaffHistoryVo().getStartDate(), getStaffHistoryVo().getEndDate())));
								else
									getStaffHistoryVo().setExperience(0);
							}
						}
						createduser = createduser.copyFromVoToEntity(createduser, userVO);
						
						 log.debug(role);
						 createduser.addNewRole(role);
						 role = null;
						Person person = new Person();
						person = person.copyFromVoToEntity(person, staffPersonVo);
						createduser.setPerson(person);
						if (!ObjectFunctions.isNullOrEmpty(taddressVo)) 
						{
							tempararyAddress = tempararyAddress.copyFromVoToEntity(tempararyAddress, taddressVo);
							createduser.setTempararyAddress(tempararyAddress);
						}
						if (!ObjectFunctions.isNullOrEmpty(paddressVo)) 
						{
							primAddress = primAddress.copyFromVoToEntity(primAddress, paddressVo);
							createduser.setPrimaryAddress(primAddress);
						}
						if (!ObjectFunctions.isNullOrEmpty(getStaffHistoryVo()) && !ObjectFunctions.isNullOrEmpty(getStaffHistoryVo().getSchoolName())) 
						{
							staffHistory = staffHistory.copyFromVoToEntity(staffHistory, getStaffHistoryVo());
							createduser.addStaffHistory(staffHistory);
						}
						if (!ObjectFunctions.isNullOrEmpty(userVO.getUserImageId())) 
						{
							 UserImage staffImg=(UserImage) staffManager.get(UserImage.class,userVO.getUserImageId());
							 createduser.setProfileImage(staffImg);
						}
						createduser =(User) staffManager.saveOrUpdateObject(createduser);
						staff.setHostelCategoryId(staffVO.getHostelCategoryId());
					}
					if(!ObjectFunctions.isNullOrEmpty(getStaffStatutoryVo())){
						staffStatutoryVo.setEsiNo(getStaffStatutoryVo().getEsiNo());
						staffStatutoryVo.setEsiDateofJoin(getStaffStatutoryVo().getEsiDateofJoin());
						staffStatutoryVo.setEsiPercentage(getStaffStatutoryVo().getEsiPercentage());
						staffStatutoryVo.setPfNo(getStaffStatutoryVo().getPfNo());
						staffStatutoryVo.setPfDateofJoin(getStaffStatutoryVo().getPfDateofJoin());
						staffStatutoryVo.setPfPercentage(getStaffStatutoryVo().getPfPercentage());
						
						if (!ObjectFunctions.isNullOrEmpty(staffStatutoryVo)) 
						{
							StaffStatutory staffStatutory = new StaffStatutory();
							staffStatutory = staffStatutory.copyFromVoToEntity(staffStatutory, staffStatutoryVo);
							staff.addStaffStatutory(staffStatutory);
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(getSalaryVo())){
						salaryVo.setSalary(getSalaryVo().getSalary());
						salaryVo.setCreatedDate(new Date());
						salaryVo.setCustId(getUserCustId());
						salaryVo.setLastAccessDate(new Date());
						salaryVo.setLastUpdatedDate(new Date());
						salaryVo.setCreatedById(getUser().getId());
						salaryVo.setLastUpdatedById(getUser().getId());
						int monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
						SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
						int year = Integer.valueOf(simpleDateformat.format(new Date()));
						salaryVo.setMonth(monthNum);
						salaryVo.setYear(year);
						if (!ObjectFunctions.isNullOrEmpty(salaryVo)) 
						{
							Salary salary = new Salary();
							salary = salary.copyFromVoToEntity(salary, salaryVo);
							staff.addSalaryDetailsSettings(salary);
						}
					}
					if (!ObjectFunctions.isNullOrEmpty(createduser)) {
						if (!ObjectFunctions.isNullOrEmpty(staffVO)) {
							staff.setAccount(createduser);
							staffVO.setCustId(getUserCustId());
							staff.setAcademicYear(getAcademicYear());
							staff.setStatus(Constants.YES_STRING);
							staff.setSchoolMess(staffVO.getSchoolMess());
							staffVO.setStaffGrade(getStaffVo().getStaffGrade());
							if("ROLE_HOD".equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMIN_COORDINATOR.equalsIgnoreCase(roleName)){
								List<StudyClass> studyClassesList = null; 
								if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("studyClassList"))){
									List<String> studyClassList=(List)getSession().getAttribute("studyClassList");
									if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
										StringBuffer qry = new StringBuffer("(");
										for (String studyclassId:studyClassList) {
											qry.append(studyclassId+",");
										}
										qry.append("0)");
										log.debug(qry.toString());
										studyClassesList = staffManager.getAll(StudyClass.class, "id in "+qry.toString());
										if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
											staff.setStudyClasses(ConvertUtil.convertListToSet(studyClassesList));
										}
									}
								}
							}
							staff=staff.copyFromVoToEntity(staff,staffVO);
							Staff lstaff=(Staff)staffManager.save(staff);
							if(!ObjectFunctions.isNullOrEmpty(lstaff)){
								if(getCustomer().isCheckMobileService()){
									sendLogincredentailsToStaff(lstaff,getCustomer(),getAcademicYear(),newPassword);
								}
							}
							if(Constants.SCHOOL_MANAGER.equalsIgnoreCase(roleName))
							{
								adminManager.createLoginAccessbilityRolesForRole(lstaff.getAccount().getRoleId(), getUserCustId(), Constants.YES_STRING);
							}
							if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("subjectsList")) && !ObjectFunctions.isNullOrEmpty(lstaff)){
								List<String> subjectsList=(List)getSession().getAttribute("subjectsList");
								if(!ObjectFunctions.isNullOrEmpty(subjectsList)){
									for (String subjectId:subjectsList) {
										try {
											if(!StringFunctions.isNullOrEmpty(subjectId)){
												StudySubject studySubject = (StudySubject) staffManager.get(StudySubject.class, Long.valueOf(subjectId));
												StaffElgibleSubjects eligibleSubject=new StaffElgibleSubjects();
												eligibleSubject.setAcademicYear(getAcademicYear());
												eligibleSubject.setStaffId(lstaff);
												eligibleSubject.setStudySubjectId(studySubject);
												staffManager.save(eligibleSubject);
												studySubject = null;
												eligibleSubject=null;
											}
										} catch (Exception ex) {
											log.debug(ex.getMessage());
											ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
										}
									}
								}
								subjectsList=null;
							}
							if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("subjectsList"))){
							setStaff(lstaff);
							super.addActionMessage("Staff is created, please verify subject and class assignment if necessary.");
							}else{
								setStaff(lstaff);
								if("ROLE_HOD".equalsIgnoreCase(roleName) || "ROLE_PRINCIPAL".equalsIgnoreCase(roleName) || "ROLE_TEACHER".equalsIgnoreCase(roleName) || "ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(roleName) || "ROLE_VICEPRINCIPAL".equalsIgnoreCase(roleName) || "ROLE_ASST_TEACHER".equalsIgnoreCase(roleName))
									super.addActionMessage("Teaching staff created successfully.");
								else if("ROLE_EXECUTIVE_VICE_CHAIRMAN".equalsIgnoreCase(roleName) || "ROLE_VICE_CHAIRMAN".equalsIgnoreCase(roleName) || "ROLE_TREASURER".equalsIgnoreCase(roleName) || "ROLE_JOINT_SECRETARIES".equalsIgnoreCase(roleName) || "ROLE_EXECUTIVE_MEMBER".equalsIgnoreCase(roleName) || "ROLE_MEMBER".equalsIgnoreCase(roleName) || "ROLE_SECRETARY".equalsIgnoreCase(roleName) || "ROLE_CHAIR_MAN".equalsIgnoreCase(roleName))
									super.addActionMessage("Management details added successfully.");
								else
									super.addActionMessage("Non teaching staff created successfully.");
								
							}
							communicationManager.sendStaffNotificationWhenEditOrAdd(lstaff.getId(),"A new staff "+lstaff.getAccount().getPerson().getFullPersonName()+" added.","A new staff Added.");
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(createduser) && !ObjectFunctions.isNullOrEmpty(getStaff())){
						StringBuffer pathName = new StringBuffer(generateUploadFilepath(getCustomer(),staffPersonVo.getFirstName().toString(),createduser.getId(),getStaff().getId()));
						if(getFileUpload().size()!=0){
							 for(int i=0;i<getFileUpload().size();i++){
								 if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i))){
					    		     File file = getFileUpload().get(i);
					    			 String fileName = getFileUploadFileName().get(i);
						    		 File destDir = new File(getSession().getServletContext().getRealPath(pathName+fileName));
									 FileUtils.copyFile(file, destDir);	 
					    		 }
					    	}
						} 
				    }
					//generateBarcodeForStudent(createduser.getId());
					adminManager.generateBarcode(createduser.getId());
				}
			}
			staffStatutory=null;
			salary=null;
			staffStatutory=null;
			tempararyAddress=null;
			staffHistory=null;
			primAddress=null;
			newPassword=null;
			secondaryUserName=null;
			userName=null;
			ajaxDoManageStaff();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
 		getSession().removeAttribute("NewUserForStaff");
		getSession().removeAttribute("PersonForStaff");
		getSession().removeAttribute("StaffForStaff");
		getSession().removeAttribute("subjectsList");
		getSession().removeAttribute("AddressForStaff");
		return SUCCESS;
	}

	/* @Description 17rd Apr cvs: Modularization remove the 10 lines code in below method  */
	@Actions( { @Action(value = "ajaxEditCancelQuestion", results = { @Result(location = "quiz/quizQuestionList.jsp", name = "success") }) })
	public String ajaxEditCancelQuestion() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditCancelQuestion' method");
		}
		try {
			ajaxDoGetCategoryQuestion();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Description 17rd Apr cvs: Modularization remove the 10 lines code in below method  */
	@Actions( { @Action(value = "ajaxStaffPersonalInfo", results = { @Result(location = "manageStaff/ajaxViewStaffPersonalInformation.jsp", name = "success") }) })
	public String  ajaxStaffPersonalInfo() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStaffPersonalInfo' method");
		}
		try {
			if(getTempId() != 0 && !StringFunctions.isNullOrEmpty(getTempString())){
				setViewStaffPersonAccountDetails(adminManager.getStaffDetailsByRoleNameAndStaffIdAndCustId(getTempString(),getUserCustId(),getTempId(),Constants.YES_STRING,getUserAcademicYearId()));
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				setCastSettingList(adminManager.getAllByCustId("CastSettings", getUserCustId(),0));
				setTempList2((List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST));
				setTempList1(adminManager.getAllCommonTypesByCustIdandType(getUserCustId(),"RELIGION"));
					setStaff((Staff)staffManager.get(Staff.class,getTempId()));
				if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
					if(!ObjectFunctions.isNullOrEmpty(getStaff().getAccount())){
						setAnyId(String.valueOf(getStaff().getAccount().getPerson().getId()));
						setPerson(new Person());
						if(!ObjectFunctions.isNullOrEmpty(getStaff().getAccount().getPrimaryAddress())){
								getPerson().setCastId(getStaff().getAccount().getPerson().getCastId());
						}
						if(!ObjectFunctions.isNullOrEmpty(getStaff().getAccount().getPerson())){
							long castId=getStaff().getAccount().getPerson().getCastId();
							if(castId > 0){
								setQuizList(adminManager.getSubcastSettingsByCastIdAndCustId(castId,getUserCustId()));
								getPerson().setSubCastId(getStaff().getAccount().getPerson().getSubCastId());
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Description 17rd Apr cvs: Modularization gStaffByRole below method  */
	@Actions( { @Action(value = "ajaxDoGetStaffByRole", results = { @Result(location = "manageStaff/ajaxViewStaffDetails.jsp", name = "success") }) })
	public String ajaxDoGetStaffByRole() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetStaffByRole' method");
		}
		try {
			prepareStaffRolesMap(null);
			getStaffRoles().put(Constants.SCHOOL_ADMIN, "Admin"); // this is used to edit staff select Role for admin.
			if(!StringFunctions.isNullOrEmpty(getTempString())){  //getTempString() gives roleName and getTempId() gives staffId
				setStaffsList(adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+getTempString()+"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId()));
				if(ObjectFunctions.isNotNullOrEmpty(getStaffsList()))
					Collections.sort(getStaffsList());
				if(getTempId() != 0){
					ajaxStaffPersonalInfo();
				}else if(!ObjectFunctions.isNullOrEmpty(getStaffsList())){
					setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails)getStaffsList().get(0));
				}
				if (!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails())) {
					if(!("ROLE_ADMIN".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()))){
						if("ROLE_PRINCIPAL".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_TEACHER".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_HOD".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_ASST_TEACHER".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName())){
							prepareStaffRolesMap(getCustomer());
						 }else if("ROLE_EXECUTIVE_VICE_CHAIRMAN".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_VICE_CHAIRMAN".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_TREASURER".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_JOINT_SECRETARIES".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_EXECUTIVE_MEMBER".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_MEMBER".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_SECRETARY".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName()) || "ROLE_CHAIR_MAN".equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName())){
							 prepareManagementRolesMap();
						 }
						else{
							Customer customer = getCustomerByCustId();
							prepareNonTeachingRolesMap(customer);
							customer=null;
						} 
					}
					if(Constants.SCHOOL_ROLE_STOREKEEPER.equalsIgnoreCase(getViewStaffPersonAccountDetails().getRoleName())){
						
						if(staffManager.getCount("storeData","custId="+getViewStaffPersonAccountDetails().getCustId()+" and storeKeeperAccountId="+getViewStaffPersonAccountDetails().getAccountId()) > 0){
							setStoreAssigned(true);
						}else{
							setStoreAssigned(false);
						}
						
					}else{
						setStoreAssigned(false);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxEditStaff", results = { @Result(location = "manageStaff/ajaxViewStaffDetails.jsp", name = "success"),@Result(location = "manageStaff/ajaxViewStaffList.jsp", name = "staffHome") }),
		 @Action(value = "ajaxEditStaffPersonalInfo", results = { @Result(location = "manageStaff/ajaxViewStaffPersonalInformation.jsp", name = "success") }) })
	public String  ajaxEditStaff() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditStaff' method");
		}
		try {
			if(getTempId() != 0 ){
				Staff staff=(Staff)staffManager.get(Staff.class, getTempId());
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(staff)){
					User staffAccount = staff.getAccount();
					//setTempId(0);
					long staffImageId= 0;
					String staffPreviousName="";
					if(!ObjectFunctions.isNullOrEmpty(staffAccount))
					{
						staffPreviousName=staffAccount.getPerson().getFullPersonName();
						UserImage staffImage = staffAccount.getProfileImage();
						if(!ObjectFunctions.isNullOrEmpty(staffImage))
						{
							staffImageId = staffImage.getId();
						}
					}
					if (getViewStaffPersonAccountDetails().getHostelCategoryId() != 0) {
		   		    	    staff.setHostelCategoryId(getViewStaffPersonAccountDetails().getHostelCategoryId());
		   		    }
					UserImage attachment;
					if (getUploadFileName() != null) {
						try {
							attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE,customer.getId(),getUserAcademicYearId(),staffImageId);
							if (!ObjectFunctions.isNullOrEmpty(attachment)) {
								staff.getAccount().setProfileImage(attachment);
							}
							attachment = null;
						} catch (Throwable ex) {
							ex.printStackTrace();
						}
					}
					else if(!StringFunctions.isNullOrEmpty(getParamValue("customerImage"))){
						try {
							attachment = captureStudentImage(getParamValue("customerImage"),Constants.FILE_TYPE_IMAGE,customer);
							if (!ObjectFunctions.isNullOrEmpty(attachment)) {
								staff.getAccount().setProfileImage(attachment);
							}
							attachment =null;
						}
						catch (Throwable ex) {
								ex.printStackTrace();
							}

						}
						
						if("personalInfo".equalsIgnoreCase(getTempString3()))
						{
							Object[] stateIdObj = null;
							if(!StringFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getCity()) && !StringFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getState())){
								if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getDateofJoining()))
									staff.getAccount().getPerson().setDateOfJoining(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getViewStaffPersonAccountDetails().getDateofJoining())));
								else
									staff.getAccount().getPerson().setDateOfJoining(null);
								staff.setQualification1(getViewStaffPersonAccountDetails().getQualification1());
								staff.setStaffType(getViewStaffPersonAccountDetails().getStaffType());
						    if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getContractStartDate()))
									staff.getAccount().getPerson().setContractStartDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getViewStaffPersonAccountDetails().getContractStartDate())));
								else
									staff.getAccount().getPerson().setContractStartDate(null);
							if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getContractEndDate()))
									staff.getAccount().getPerson().setContractEndDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getViewStaffPersonAccountDetails().getContractEndDate())));
								else
									staff.getAccount().getPerson().setContractEndDate(null);
								//Siva: Updating the username with mobile number if the person is a driver role
								if(staff.getAccount().isDriver() && !ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getMobileNumber()) && !getViewStaffPersonAccountDetails().getMobileNumber().equalsIgnoreCase(staff.getAccount().getPerson().getMobileNumber())){
									if(!ObjectFunctions.isNullOrEmpty(staff.getAccount())){
											User muser =staffUsernameAvailabulity(getViewStaffPersonAccountDetails().getMobileNumber());
											if(!ObjectFunctions.isNullOrEmpty(muser)){
												super.addActionError("Mobile number already exists. Change mobile number for '"+staff.getAccount().getPerson().getFirstName()+"'");
												ajaxDoManageStaff();
												return SUCCESS;
											}
											staff.getAccount().setUsername(getViewStaffPersonAccountDetails().getMobileNumber());
											String newPassword = StringUtil.generateRandomString();
											staff.getAccount().setPassword(PasswordUtils.passwordEncoder(newPassword,null));
											if(customer.isCheckMobileService()){
												staff.getAccount().getPerson().setMobileNumber(getViewStaffPersonAccountDetails().getMobileNumber());
												sendLogincredentailsToStaff(staff,customer,getCurrentAcademicYear(),newPassword);
											}
									}
								}
								//End
								staff.getAccount().getPerson().setExperience(getViewStaffPersonAccountDetails().getExperience());
								staff.getAccount().getPerson().setBloodGroup(getViewStaffPersonAccountDetails().getBloodGroup());
								staff.getAccount().getPerson().setFamilyDoctor(getViewStaffPersonAccountDetails().getFamilyDoctor());
								staff.getAccount().getPerson().setDesignation(getViewStaffPersonAccountDetails().getDesignation());
								staff.getAccount().getPerson().setPrefferedHospital(getViewStaffPersonAccountDetails().getPrefferedHospital());
								staff.getAccount().getPerson().setPhoneNumber(getViewStaffPersonAccountDetails().getPhoneNumber());
								staff.getAccount().getPerson().setMobileNumber(getViewStaffPersonAccountDetails().getMobileNumber());
								staff.getAccount().getPerson().setBankName(getViewStaffPersonAccountDetails().getBankName());
								staff.getAccount().getPerson().setBankAccountNumber(getViewStaffPersonAccountDetails().getBankAccountNumber());
								staff.getAccount().getPerson().setBankBranchName(getViewStaffPersonAccountDetails().getBankBranchName());
								staff.getAccount().getPerson().setNationality(getViewStaffPersonAccountDetails().getNationality());
								staff.getAccount().getPerson().setCastId(getPerson().getCastId());
								staff.getAccount().getPerson().setSubCastId(getViewStaffPersonAccountDetails().getSubCastId());
								staff.getAccount().getPrimaryAddress().setAddressLine1(getViewStaffPersonAccountDetails().getAddressLine1());
								staff.getAccount().getPrimaryAddress().setCity(getViewStaffPersonAccountDetails().getCity());
								stateIdObj = staffManager.get("select id,stateCode from State where stateCode='"+getViewStaffPersonAccountDetails().getState()+"'");
								if(!ObjectFunctions.isNullOrEmpty(stateIdObj))
									staff.getAccount().getPrimaryAddress().setStateId(Long.valueOf(stateIdObj[0].toString()));
								staff.getAccount().getPrimaryAddress().setState(getViewStaffPersonAccountDetails().getState());
								staff.getAccount().getPrimaryAddress().setPostalCode(getViewStaffPersonAccountDetails().getPostalCode());
								staff.getAccount().getPrimaryAddress().setEmail(getViewStaffPersonAccountDetails().getEmail());
								staff.getAccount().getPrimaryAddress().setLastUpdatedDate(new Date());
								if(ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getReligionId()))
									staff.getAccount().getPerson().setReligionId(0);
								else
									staff.getAccount().getPerson().setReligionId(getViewStaffPersonAccountDetails().getReligionId());
								staff.getAccount().getPerson().setMotherToungId(getViewStaffPersonAccountDetails().getMotherToungId());
								staff.getAccount().getPerson().setPanNumber(getViewStaffPersonAccountDetails().getPanNumber());
								staff.getAccount().getPerson().setGpfNumber(getViewStaffPersonAccountDetails().getGpfNumber());
								staff.getAccount().getPerson().setOfficeNumber(getViewStaffPersonAccountDetails().getOfficeNumber());
								staff.getAccount().getPerson().setIfscCode(getViewStaffPersonAccountDetails().getIfscCode());
								
								staff.getAccount().getPerson().setFatherName(getViewStaffPersonAccountDetails().getFatherName());
								staff.getAccount().getPerson().setAnotherMobileNumber(getViewStaffPersonAccountDetails().getFatherContactNumber());// anotherMobileNumber column was used to store father/husban mobile number for staff.
								
								staff.setOutSideSchoolDuty(getViewStaffPersonAccountDetails().getOutSideSchoolDuty());
								if(!ObjectFunctions.isNullOrEmpty(staff.getAccount().getTempararyAddress())){
									staff.getAccount().getTempararyAddress().setAddressLine1(getViewStaffPersonAccountDetails().getTaddressLine1());
									staff.getAccount().getTempararyAddress().setCity(getViewStaffPersonAccountDetails().getTcity());
									staff.getAccount().getTempararyAddress().setState(getViewStaffPersonAccountDetails().getTstate());
									staff.getAccount().getTempararyAddress().setPostalCode(getViewStaffPersonAccountDetails().getTpostalCode());
									staff.getAccount().getTempararyAddress().setLastUpdatedDate(new Date());
								}
								else{
									Address  tempAddress = null;
									tempAddress = new Address();
									tempAddress.setAddressLine1(getViewStaffPersonAccountDetails().getTaddressLine1());
									tempAddress.setCity(getViewStaffPersonAccountDetails().getTcity());
									tempAddress.setState(getViewStaffPersonAccountDetails().getTstate());
									tempAddress.setPostalCode(getViewStaffPersonAccountDetails().getTpostalCode());
									staff.getAccount().setTempararyAddress(tempAddress);
								}
								staff.getAccount().getPerson().setSalaryPaymentMode(getViewStaffPersonAccountDetails().getSalaryPaymentMode().trim());
								staff.getAccount().getPerson().setStaffLocation(getViewStaffPersonAccountDetails().getStaffLocation().trim());
								staff.setStaffGrade(getViewStaffPersonAccountDetails().getStaffGrade());
							}
							
							Salary salary=null;
							if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getSalary()) && getViewStaffPersonAccountDetails().getSalary() > 0.0) {
								salary=(Salary) staffManager.get(Salary.class, "staffId="+staff.getId());
								if(ObjectFunctions.isNullOrEmpty(salary)){
									salary=new Salary();
								}
								salary.setSalary(getViewStaffPersonAccountDetails().getSalary());
								salary.setCreatedDate(new Date());
								salary.setCustId(getUserCustId());
								salary.setLastAccessDate(new Date());
								salary.setLastUpdatedDate(new Date());
								salary.setCreatedById(getUser().getId());
								salary.setLastUpdatedById(getUser().getId());
								int monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
								SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
								int year = Integer.valueOf(simpleDateformat.format(new Date()));
								salary.setMonth(monthNum);
								salary.setYear(year);
								staff.setSalaryDetails(null);
								staff.addSalaryDetailsSettings(salary);
							}
							if(!StringFunctions.isNullOrEmpty(getParamValue("esiName")) || !StringFunctions.isNullOrEmpty(getParamValue("pfname")) || (StringFunctions.isNullOrEmpty(getParamValue("esiName")) && (StringFunctions.isNullOrEmpty(getParamValue("pfname")))))
							{
								StaffStatutory staffStatutory=(StaffStatutory) staffManager.get(StaffStatutory.class, "staffId="+staff.getId());
								if(ObjectFunctions.isNullOrEmpty(staffStatutory)){
									staffStatutory=new StaffStatutory();
								}
								staffStatutory.setEsiNo(getViewStaffPersonAccountDetails().getEsiNo());
								staffStatutory.setEsiDateofJoin(getViewStaffPersonAccountDetails().getEsiDateofJoin());
								staffStatutory.setEsiPercentage(getViewStaffPersonAccountDetails().getEsiPercentage());
								staffStatutory.setPfNo(getViewStaffPersonAccountDetails().getPfNo());
								staffStatutory.setPfDateofJoin(getViewStaffPersonAccountDetails().getPfDateofJoin());
								staffStatutory.setPfPercentage(getViewStaffPersonAccountDetails().getPfPercentage());
								staffStatutory.setCustId(getUserCustId());
								staff.addStaffStatutory(staffStatutory);
							}
							
							staff.getAccount().getPerson().setAadharNumber(getViewStaffPersonAccountDetails().getAadharNumber());
							staff.setSchoolMess(getViewStaffPersonAccountDetails().getSchoolMess());
							
						}
						else
						{
							if((!StringFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getFirstName()) || !StringFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getLastName()) || !ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getDateOfBirth())) 
									&& !StringFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getGender()) && !StringFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getMaritalStatus())){
								User user = staff.getAccount();
								user.setBioMetricId(getViewStaffPersonAccountDetails().getBioMetricId());
								if(StringFunctions.isNotNullOrEmpty(getViewStaffPersonAccountDetails().getStaffNumber())){
										User userExist = (User)staffManager.get(User.class,new StringBuffer("custId=").append(getUserCustId()).append(" and staffNumber='").append(getViewStaffPersonAccountDetails().getStaffNumber().trim()).append("' ")
												.append(" and id!=").append(user.getId()).toString());
										if(!ObjectFunctions.isNullOrEmpty(userExist)){
											super.addActionError(new StringBuffer("Staff number ").append(getViewStaffPersonAccountDetails().getStaffNumber()).append(" is already assigned to ").append(userExist.getFullPersonName()).append(". Please change staff number.").toString());
											ajaxDoGetStaffByRole();
											return SUCCESS;
										}else{
											user.setStaffNumber(getViewStaffPersonAccountDetails().getStaffNumber().trim());
										}
								}else{
									user.setStaffNumber(null);
								}
								if(!StringFunctions.isNullOrEmpty(getTeachingRoleName())){
									if(!(getTeachingRoleName().equalsIgnoreCase(getTempString()))){
										Role role = staffManager.getRoleByName(getTeachingRoleName());
										staffManager.updateRoleByUserId(role.getId(),user.getId());
										setPlTitle(getTeachingRoleName());
										role = null;
									}
								}
								staff.getAccount().getPerson().setFirstName(getViewStaffPersonAccountDetails().getFirstName());
								staff.getAccount().getPerson().setLastName(getViewStaffPersonAccountDetails().getLastName());
								staff.getAccount().getPerson().setGender(getViewStaffPersonAccountDetails().getGender());
								staff.getAccount().getPerson().setDateOfBirth(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getViewStaffPersonAccountDetails().getDateOfBirth())));
								staff.getAccount().getPerson().setMaritalStatus(getViewStaffPersonAccountDetails().getMaritalStatus());
								staff.getAccount().getPerson().setStaffUniqueNumber(getViewStaffPersonAccountDetails().getStaffUniqueNumber().trim());
							}
						}
						
						
						staff.setLastUpdatedById(getUser().getId());
						staff.setLastUpdatedDate(new Date());
						staff.setLastAccessDate(new Date());
						setTempId(staff.getId());
						
						
						staff=(Staff) staffManager.merge(staff); 
						communicationManager.sendStaffNotificationWhenEditOrAdd(staff.getId(), staffPreviousName+" details are updated.","Staff details are updated.");
						String StaffStatus=getParamValue("status");
						if("on".equalsIgnoreCase(StaffStatus))
							super.addActionMessage("Staff disabled successfully.");
						else
							super.addActionMessage("Staff details updated successfully.");
				}
				staff=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally
		{
			ajaxDoGetStaffByRole();
		}
		
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxEditQuizQuestion", results = { @Result(location = "quiz/quizQuestionList.jsp", name = "success") }) })
	public String ajaxEditQuizQuestion() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditQuizQuestion' method");
		}
		try {
			Date startDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getParamValue("startDate")));
			Date endDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getParamValue("endDate")));
			QuizQuestion quizQuestion = (QuizQuestion) staffManager.get(QuizQuestion.class, getQuizQuestion().getId());
			quizQuestion.setQuestionName(getQuizQuestion().getQuestionName());
		    quizQuestion.setStartDate(startDate);			
			quizQuestion.setEndDate(endDate);
			int iterationCount = getQuestionAnswerId().length;
			if (!ObjectFunctions.isNullOrEmpty(iterationCount)) {
				for (int i = 0; i < iterationCount; i++) {
					String answerId = getQuestionAnswerId()[i];
					String anserOptions=getParamValue("anserOptions"+answerId);
					String answer=getParamValue("questionAnswer"+anserOptions);
					String questionAnsA = getParamValue("questionAnswerA");
					String questionAnsB = getParamValue("questionAnswerB");
					String questionAnsC = getParamValue("questionAnswerC");
					String questionAnsD = getParamValue("questionAnswerD");
					if (answer.equalsIgnoreCase(questionAnsA)) {
						QuestionAnswer questionAnswerA=(QuestionAnswer)staffManager.get(QuestionAnswer.class, Long.valueOf(answerId));
						if (anserOptions.equalsIgnoreCase(getAnyTitle())) {
							questionAnswerA.setCorrectAnswer("Y");
						} else {
							questionAnswerA.setCorrectAnswer("N");
						}
						questionAnswerA.setQuestionAnswer(answer);
						quizQuestion.addQuesationAnswers(questionAnswerA);
					}
					if (answer.equalsIgnoreCase(questionAnsB)) {
						QuestionAnswer questionAnswerB=(QuestionAnswer)staffManager.get(QuestionAnswer.class, Long.valueOf(answerId));
						if (anserOptions.equalsIgnoreCase(getAnyTitle())) {
							questionAnswerB.setCorrectAnswer("Y");
						} else {
							questionAnswerB.setCorrectAnswer("N");
						}
						questionAnswerB.setQuestionAnswer(answer);
						quizQuestion.addQuesationAnswers(questionAnswerB);
					}
					if (answer.equalsIgnoreCase(questionAnsC)) {
						QuestionAnswer questionAnswerC=(QuestionAnswer)staffManager.get(QuestionAnswer.class, Long.valueOf(answerId));
						if (anserOptions.equalsIgnoreCase(getAnyTitle())) {
							questionAnswerC.setCorrectAnswer("Y");
						} else {
							questionAnswerC.setCorrectAnswer("N");
						}
						questionAnswerC.setQuestionAnswer(answer);
						quizQuestion.addQuesationAnswers(questionAnswerC);
					}
					if (answer.equalsIgnoreCase(questionAnsD)) {
						QuestionAnswer questionAnswerD=(QuestionAnswer)staffManager.get(QuestionAnswer.class, Long.valueOf(answerId));
						if (anserOptions.equalsIgnoreCase(getAnyTitle())) {
							questionAnswerD.setCorrectAnswer("Y");
						} else {
							questionAnswerD.setCorrectAnswer("N");
						}
						questionAnswerD.setQuestionAnswer(answer);
						quizQuestion.addQuesationAnswers(questionAnswerD);
						super.addActionMessage("Quiz question Updated successfully.");
					}
				}
			}
			setAnyId(String.valueOf(quizQuestion.getQuizId()));
			staffManager.merge(quizQuestion);
			ajaxDoGetCategoryQuestion();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	 }
	
	/* @Description 15rd Apr cvs: Modularization  change the adminAction to staffAction*/ 
	@Actions({ @Action(value = "ajaxStaffProfile", results = { @Result(location = "ajaxStaffProfile.jsp", name = "success") })})
	public String ajaxStaffProfile() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxStaffProfile' method");
	}
	try {
		setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails)staffManager.get(ViewStaffPersonAccountDetails.class,getUser().getId(),"accountId"));
	}
	catch(Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
   }
	
	/* @Description 15rd Apr cvs: Modularization  remove the unnecessary code*/ 
	@Actions( {
		@Action(value = "eventCalendar", results = { @Result(location = "event/eventHome.jsp", name = "success") }),
		@Action(value = "ajaxViewEvents", results = { @Result(location = "event/viewEvents.jsp", name = "success") }),
		@Action(value = "ajaxViewEvent", results = { @Result(type = "json", name = "success", location = "viewEvents.jsp", params = {
				"includeProperties", "eventDetailsList.*" }) }) })
	public String eventHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'eventHome' method");
		}
		try {
			setObjectList(adminManager.getAllByCustId("Events",getUserCustId(), getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/* @Description 15rd Apr cvs: Modularization  below method  remove the unnecessary code  change the adminAction to staffAction*/  
	@Actions({
		@Action(value = "ajaxDeleteLeave", results = { @Result(location = "viewLeavesList.jsp", name = "success") }) })
		public String ajaxDeleteLeave() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteLeave' method");
		}
		try {
			long leavesId=Long.valueOf(getParamValue("leave.id"));
			if(leavesId > 0) {
				staffManager.remove(Leave.class, leavesId);
			}
		} catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	
	/* @Description 15rd Apr cvs: Modularization  below method  remove the unnecessary code  change the adminAction to staffAction*/  
	@Actions( {
		@Action(value = "ajaxCheckHolidaysDates", results = { @Result(type = "json", name = "success",params = {"includeProperties","thresholdMonths,classTeacherStatus"}) }) })
	public String ajaxCheckHolidaysDates() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckHolidaysDates' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getStartDate())){
				Calendar startDate=Calendar.getInstance();
				startDate.setTime(getStartDate());
 				JSONObject leavesDetails=new JSONObject();
 				String date=DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, startDate.getTime());
				SchoolHolidays schoolHolidays=adminManager.getHolidayByCustIdAndAcademicYearId(getUserCustId(),0,date,0,null,null,"holidayDateLike");
				if(!ObjectFunctions.isNullOrEmpty(schoolHolidays)){
					leavesDetails.put("holidays", date+"  day is  holiday.You can't create exam schedule.Please change date(s). ");
					getResponse().getOutputStream().print(leavesDetails.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	/* @Description 15rd Apr cvs: Modularization  below method  remove the unnecessary code  in cancelQuestion*/  
	@Actions( { @Action(value = "ajaxCancelQuestion", results = { @Result(location = "quiz/viewStaffQuizLists.jsp", name = "success") }),
		@Action(value = "ajaxCancelQuiz", results = { @Result(location = "quiz/viewStaffQuizLists.jsp", name = "success") })
	   })
    public String ajaxCancelQuestion() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxCancelQuestion' method");
	}
	try {
		setQuizList(staffManager.getQuizListWithCustId(getUserCustId()));
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	/* @Description 15rd Apr cvs: Modularization  below method  remove the unnecessary code  in cancelQuestion*/  
	@Actions({
	    @Action(value = "ajaxDoGetMyClass", results = { @Result(location = "selectMyClassSubjects.jsp", name = "success") }) })
		public String ajaxDoGetMyClass() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetMyClass' method");
		}
		try {
			ajaxDoSendMyMessagesToParent();
                setExamTypeList(getAllExamTypesByClassId(0,0,getUserCustId(),getUserAcademicYearId()));
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/**************************************************************************************************
 *   Date              Name              Description
 * 	========          ============      ==================
 *  April 15, 2013     cvs				Modularization  below method  remove the unnecessary code  in cancelQuestion
 *  May 2, 2013		  Seshu				Changed 'getClassTeacherByAccountId' service, previously it returns list of ClassTeacher objs, now changed to ClasTeacher object.     		          		
/**************************************************************************************************/
	/* @Description 15rd Apr cvs: Modularization  below method  remove the unnecessary code  in cancelQuestion*/  
	@Actions({
		@Action(value = "ajaxDoSendMyMessagesToParent", results = { @Result(location = "selectStaffOrParentMsg.jsp", name = "success" )}),
		@Action(value = "ajaxDoSendMyMessagesToParent1", results = { @Result(location = "sendMessagesToParent.jsp", name = "success" )}),
	    @Action(value = "ajaxDoSendExamAlertToParent", results = { @Result(location = "sendParentorStudentExamAlertMsg.jsp", name = "success" )}),
	    @Action(value = "ajaxDoSendExamAlertToParent1", results = { @Result(location = "sendExamAlertsToParent.jsp", name = "success" )})
		})
		public String ajaxDoSendMyMessagesToParent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSendMyMessagesToParent' method");
		}
		try {
			setAllStudentsList(null);
			ClassTeacher classTeacher = staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(classTeacher)) 
				getSelectboxMap().put(classTeacher.getStudyClass().getId(), classTeacher.getStudyClass().getClassAndSection());
			classTeacher= null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Description 15rd Apr cvs: Modularization  remove the unnecessary  variables bellow method */  
	@Actions({
		@Action(value = "ajaxStaffSendMessageToParent", results = { @Result(location = "viewTeacherMessagesList.jsp", name = "success" )}),
		@Action(value = "ajaxStaffSendMessageToStaff", results = { @Result(location = "viewTeacherMessagesList.jsp", name = "success" )}) ,
		@Action(value = "ajaxSendExamAlertMsgToParent", results = { @Result(location = "viewTeacherMessagesList.jsp", name = "success" )})
					
		})
		public String ajaxStaffSendMessageToParent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStaffSendMessageToParent' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getMessages())) {
				setCustomer(getCustomerByCustId());
				getMessages().setCreatedById(getUser().getId());
				getMessages().setUsername(getViewStudentPersonAccountDetails().getUsername());
				if(!ObjectFunctions.isNullOrEmpty(getCustomer())){
				     getMessages().setCustomer(getCustomer());
				}
				getMessages().setCreatedDate(new Date());
				getMessages().setLastUpdatedDate(new Date());
				getMessages().setLastAccessDate(new Date());
				staffManager.save(getMessages());
				super.addActionMessage("Message posted Successfully");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxDoGetTeacherMessages();
		return SUCCESS;
	}
	/* @Description 15rd Apr cvs: Modularization  remove the unnecessary  variables bellow method */  
	@Actions({
		@Action(value = "ajaxViewReadMoreTeacherMessagesForParent", results = { @Result(location = "expandedTeacherMessage.jsp", name = "success") }) })
		public String ajaxViewReadMoreTeacherMessagesForParent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewReadMoreTeacherMessagesForParent' method");
		}
		try {
			Messages messages = (Messages)staffManager.get(Messages.class, Long.valueOf(getParamValue("id")));
			if(!ObjectFunctions.isNullOrEmpty(messages)){	
				ViewStaffPersonAccountDetails staffDetails = (ViewStaffPersonAccountDetails)staffManager.get(ViewStaffPersonAccountDetails.class,Long.valueOf(messages.getSenderAccountId()),"accountId");
				if(!ObjectFunctions.isNullOrEmpty(staffDetails)){	
					messages.setFullPersonName(staffDetails.getPersonFullName());
				}
				setMessages(messages);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( {
   		@Action(value = "ajaxGetStudentsList", results = { @Result(location = "myClassmatesList.jsp", name = "success") }),
   		@Action(value = "ajaxDoGetStaffProfile", results = { @Result(location = "getStaffProfile.jsp", name = "success") }) })
   public String ajaxDoGetStudentOrStaffPorfileDetailsForStudent()
   		throws URTUniversalException {
   	if (log.isDebugEnabled()) {
   		log.debug("Entering 'ajaxDoGetStudentOrStaffPorfileDetailsForStudent' method");
   	}
   	try {
   		ajaxDoGetStudentOrStaffPorfileDetails();
   	} catch (Exception ex) {
   		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   	}
   	return SUCCESS;
   }
	@Actions({
		@Action(value = "ajaxDoStaffLeaveApprovalsAsApprover", results = { @Result(location = "leaves/staffLeavesList.jsp", name = "success") }), 
		@Action(value = "ajaxDoCancelLeave", results = { @Result(location = "leaves/staffLeavesList.jsp", name = "success") }),
		@Action(value = "ajaxLeaveApprovalsCountHome", results = { @Result(location = "leaveApprovalsCountHome.jsp", name = "success") })
		})
		public String doStaffLeaveApprovalsAsApprover() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doStaffLeaveApprovalsAsApprover' method");
		}
		try {
			staffLeaveApprovalsAsApprover();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxFindExamStudentUsingNameOrId", results = { @Result(location = "sendExamAlertMsgToParent.jsp", name = "success" )}),
		@Action(value = "ajaxFindStudentUsingNameOrId", results = { @Result(location = "sendMessageToParent.jsp", name = "success" )}),
		@Action(value = "ajaxGetSelectedStudent", results = { @Result(location = "ajaxStudentProfile.jsp", name = "success") }),
		@Action(value = "ajaxFindStudentProfileUsingNameOrId", results = { @Result(location = "ajaxStudentProfileHome.jsp", name = "success") })
		})
		public String ajaxFindStudentUsingNameOrId() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFindStudentUsingNameOrId' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			getSmsCount();
			setTempBoolean(adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId()));
			List<ViewStudentPersonAccountDetails> studentsDetailsList = null;
			String stuFirstName = getParamValue("stuFirstName");
			if(getTempId1() > 0)
				studentsDetailsList = staffManager.getFindStudentsListByIdOrName(getUsername(),stuFirstName,getUserCustId(),getUserAcademicYearId(),getTempId1());
				else if (getTempId1() == 0){
				 StringBuffer queryString = new StringBuffer("from ViewStudentPersonAccountDetails where custId="+getUserCustId()+" and academicYearId = "+getUserAcademicYearId()+" and classSectionId in "+getAnyTitle().toString());
		           if(!StringFunctions.isNullOrEmpty(getUsername())){
		        	   queryString.append(" and rollNumber = '"+getUsername()+"'");
		           }else{
		        	   queryString.append(" and firstName like '"+stuFirstName+"%'");
		           }
		           studentsDetailsList=staffManager.getAllHqlQuery(queryString.toString());
				}
			if (!ObjectFunctions.isNullOrEmpty(studentsDetailsList)){
				setViewStudentPersonAccountDetailsList(studentsDetailsList);
			}
		}	
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 
	@Actions({
		@Action(value = "ajaxFindStaffUsingNameOrId", results = { @Result(location = "sendMessageToStaff.jsp", name = "success" )}),
		@Action(value = "ajaxFindStaffProfileUsingNameOrId", results = { @Result(location = "ajaxStaffProfileHome.jsp", name = "success") })
		})
		public String ajaxFindStaffUsingNameOrId() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFindStaffUsingNameOrId' method");
		}
		try {
			String username = getParamValue("username");
			String staffFirstName = getParamValue("staffFirstName");
			String studentRollNumber = getParamValue("viewStudentPersonAccountDetails.rollNumber");
			ViewStaffPersonAccountDetails staffDetails = staffManager.getFindStaffByIdOrName(username,staffFirstName,getUserCustId());
			if (!ObjectFunctions.isNullOrEmpty(staffDetails)) {
				User account = (User) staffManager.get(User.class,staffDetails.getAccountId());
				if (!ObjectFunctions.isNullOrEmpty(staffDetails)) {
					setUser(account);
				}
				setViewStaffPersonAccountDetails(staffDetails);
				setAnyId(studentRollNumber);// setting the student roll number to the anyId
				staffDetails=null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed unnecessary code is done by venkatesh - 04-29-2013
	*/
	@Actions( { @Action(value = "ajaxViewStaffLeaves", results = { @Result(location = "leaves/ajaxViewStaffLeaves.jsp", name = "success") }) })
	public String ajaxViewStaffLeaves() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStaffLeaves' method");
		}
		try{
			long id = getUser().getId();
			if(id != 0){
				setToDate(new Date());
				ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = (ViewStaffPersonAccountDetails) staffManager.get(ViewStaffPersonAccountDetails.class, id,"accountId");
				if(!ObjectFunctions.isNullOrEmpty(viewStaffPersonAccountDetails))
				{
					setLeaveManagement((LeaveManagement) adminManager.get(LeaveManagement.class,"custId="+getUserCustId()+" and permanentOrContract='"+viewStaffPersonAccountDetails.getStaffType()+"' and roleId="+viewStaffPersonAccountDetails.getRoleId()+" and academicYearId="+getUserAcademicYearId()));
				}
				staffLeavesManagement(id);
				staffLeaves(id);
				viewStaffPersonAccountDetails=null;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/********************************************************************
	 *
	 * Date              Name               Description
	 * ========          ============       ==================
	 * May 6, 2013		 CVS				remove the unnecessary parameters below methods 

	/********************************************************************/
	 @Actions({ @Action(value = "ajaxDoSendEmail", results = { @Result(location = "popupSendEmail.jsp", name = "success") }) })
			public String ajaxDoSendEmail() throws URTUniversalException{
			if(log.isDebugEnabled()){
				log.debug("Entering 'ajaxDoSendEmail' method");
			}
			try {
				setTempString(getTempString()); //here tempString is a paremtEmail,getTempId is accountId and getTempId1 is a studyClassIs
				setTempId(getTempId());
				setTempId1(getTempId1());
				setAnyTitle(getAnyTitle());
				String studName = getParamValue("stuFirstName");
				setAnyId(studName);
			}
			catch(Exception err)
			{
				err.printStackTrace();
			}
			return SUCCESS;
		}
	 @Actions({ @Action(value = "ajaxSendEmailToParent", results = { @Result(location = "viewMyStudentsLists.jsp", name = "success") }) })
			public String ajaxSendEmailToParent() throws URTUniversalException{
			
			if(log.isDebugEnabled()){
				log.debug("Entering 'ajaxSendEmailToParent' method");
			}
			try {
				if(!StringFunctions.isNullOrEmpty(getTempString())) {
					ViewAllUsers viewAllUsers = (ViewAllUsers)staffManager.get(ViewAllUsers.class,getTempId(),"accountId");
					if(!ObjectFunctions.isNullOrEmpty(viewAllUsers))
					{
						String[] emailAddresses = new String[1];
						if(Constants.SCHOOL_STUDENT.equalsIgnoreCase(viewAllUsers.getRoleName()) || Constants.SCHOOL_PARENT.equalsIgnoreCase(viewAllUsers.getRoleName()))
						{
							emailAddresses[0] = viewAllUsers.getParentEmail();
						}
						else
						{
							emailAddresses[0] = viewAllUsers.getStaffEmail();
						}
						MailUtil mailUtil=new MailUtil(emailAddresses, getSubject(), getDescription(), getUser());
						mailUtil.sendEmailToParentOrStaff(viewAllUsers);
						mailUtil=null;
						viewAllUsers=null;
						super.addActionMessage("Email sent successfully.");
					}
				}
			}
			catch(Exception err) {
				err.printStackTrace();
			}
			//ajaxDoGetClassTeacherClass();
			ajaxFindStudentUsingNameOrId();
			return SUCCESS;
		}
		
	 
	@Actions({
		@Action(value = "ajaxDoReplayMessage", results = { @Result(location = "replayMessage.jsp", name = "success" )}) })
		public String ajaxDoReplayMessage() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFindStaffUsingNameOrId' method");
		}
		try
		{
			String messageId = getParamValue("id");
			if(!StringFunctions.isNullOrEmpty(messageId))
			{
				Messages messages = (Messages)staffManager.get(Messages.class,Long.valueOf(messageId));
				if(!ObjectFunctions.isNullOrEmpty(messages))
				{
					messages.setTitle(null);
					messages.setMessageDescription(null);
					setMessages(messages);
				}
				messages=null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
		@Actions( {
				@Action(value = "ajaxViewAllEvents", results = { @Result(location = "ajaxManageEvents.jsp", name = "success") }),
				@Action(value = "adminEventDashboard", results = { @Result(location = "adminEventDashboard.jsp", name = "success") }),
				@Action(value = "ajaxEventDashboard", results = { @Result(location = "adminEventDashboard.jsp", name = "success") }) })
		public String ViewAllEvents() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ViewAllEvents' method");
			}
			try {
			} catch (Exception ex) {
				log.error("Entering into 'catch block':" + ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}

			return SUCCESS;
		}
		 
        @Actions( { @Action(value = "eventsXml", results = { @Result(location = "ajaxStaffCalendar.jsp", name = "success") }) })
        public String recurringEventXml() throws IOException {
    	if (log.isDebugEnabled()) {
    	    log.debug("Entering 'recurringEventXml' method");
    	}
    	try {
    	    PrintWriter toClient = getResponse().getWriter();
    	    getResponse().setContentType("text/xml");
    	    Document document = null;
    	    RecurringEventsDOM scDOM = new RecurringEventsDOM();
    	    ajaxStaffAllEvents();
    	    //ajaxViewSchoolSettingsHolidays();
    	    scDOM.setObjectList(getObjectList());
    	    scDOM.setHolidayBoardList(getHolidayBoardMessagesList());
    	    if (ObjectFunctions.isNotNullOrEmpty(scDOM.getObjectList())) {
    		StringBuffer recEventBuffer = new StringBuffer();
    		recEventBuffer.append("(");
    		Iterator eventsListIterator = scDOM.getObjectList().iterator();
    		for (Iterator objectsListIterator = eventsListIterator; objectsListIterator.hasNext();) {
    		    objectsListIterator.next();
    		    if (!recEventBuffer.toString().equals("(")) {
    			recEventBuffer.append(",");
    		    }
    		    recEventBuffer.append(Events.getSerialversionuid());
    		}
    		recEventBuffer.append(")");
    	    }
    	    document = scDOM.emsEvents();
    	    DOMUtil.writeXmlToFile(toClient, document);
    	    // document = scDOM.HolidayEvents();
    	} catch (Exception ex) {
    	    log.error("Entering into 'catch block':" + ex.getMessage());
    	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    	}
    	return null;
        }
        @Actions({
			@Action(value = "ajaxFindStudentOrStaffUsingNameForPSearch", results = { @Result(location = "ajaxStudentProfileHome.jsp", name = "success"),
					 @Result(location = "ajaxStaffProfileHome.jsp", name = "staff")  }),
			@Action(value = "ajaxSelectOneStudent", results = { @Result(location = "ajaxStudentProfile.jsp", name = "success") }),
			@Action(value = "ajaxSelectOneStaff", results = { @Result(location = "ajaxStaffProfile.jsp", name = "staff") })
			})
		public String ajaxFindStudentOrStaffUsingNameForPSearch() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxFindStudentOrStaffUsingNameForPSearch' method");
			}
			try {
				//long accountId = getUser().getId();
				String username = getParamValue("username");
				String SfirstName = getParamValue("stuFirstName");
				String searchType = getParamValue("eventBelongs");
				if("ST".equalsIgnoreCase(searchType))
				{
					List<ViewStudentPersonAccountDetails> studentsDetailsList = staffManager.getFindStudentsListByIdOrName(username, SfirstName,getUserCustId(),getUserAcademicYearId(),getTempId1());
					if (!ObjectFunctions.isNullOrEmpty(studentsDetailsList)) 
					{
						if (studentsDetailsList.size() == 1) {
							for(ViewStudentPersonAccountDetails studentsDetails : studentsDetailsList) {
								setViewStudentPersonAccountDetails(studentsDetails);
							}
						}
						else {
							setViewStudentPersonAccountDetailsList(studentsDetailsList);
						}
					}
					studentsDetailsList=null;
				}
				if("S".equalsIgnoreCase(searchType))
				{
					List<ViewStaffPersonAccountDetails> staffDetailsList = staffManager.getFindStaffListByIdOrName(username, SfirstName,getUserCustId());
					if (!ObjectFunctions.isNullOrEmpty(staffDetailsList)) 
					{
						if (staffDetailsList.size() == 1) {
							String studentRollNumber = getParamValue("viewStudentPersonAccountDetails.rollNumber");
							for(ViewStaffPersonAccountDetails staffDetails : staffDetailsList) {
								setViewStaffPersonAccountDetails(staffDetails);
								setAnyId(studentRollNumber);
							}
						}
						else {
							setViewStaffPersonAccountDetailsList(staffDetailsList);
						}
					}
					staffDetailsList=null;
					return "staff";
				}
				setEventBelongs(searchType);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( {
				@Action(value = "ajaxDoGetStudentsByTeacherClass", results = { @Result(location = "selectMyClassSubjectsHome.jsp", name = "success") }),
				@Action(value = "ajaxDoCancelSendMailToParent", results = { @Result(location = "viewMyStudentsLists.jsp", name = "success") })
		})
		public String ajaxDoGetClassTeacherClass() throws URTUniversalException {
	
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetClassTeacherClass' method");
			}
			try {
				if (!ObjectFunctions.isNullOrEmpty(getUser().getId())) {
					setAllStudentsList(null);
					Staff staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
					prepareStudentsListBystaffIdAndAcademicYearId(getUser().getId());
					List<ClassTeacher> teacherSubjetsList=staffManager.getTeacherSubjectsByIdAndAcademicYear(staff.getId(),getUserAcademicYearId(),getUserCustId());
					if(!ObjectFunctions.isNullOrEmpty(teacherSubjetsList)){
						ClassTeacher teacher=teacherSubjetsList.get(0);
						if(!ObjectFunctions.isNullOrEmpty(teacher)){
							StudyClass studyClass = teacher.getStudyClass();
							if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
								List<ViewStudentPersonAccountDetails> studentsList = staffManager.getViewStudentPersonAccountDetailsByStudyClassIdandStatus(studyClass.getId(),Constants.YES_STRING,String.valueOf(getUserAcademicYearId()));
								if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
									setClassStudentsList(studentsList);
								}
								studentsList=null;
							}
						}
					}
				}
				ajaxDoGetMyClass();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxMySentMessages", results = { @Result(location = "ajaxViewMySentMessagesList.jsp", name = "success" )})
		})
			public String ajaxMySentMessages() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxMySentMessages' method");
			}
			try
			{
				//long id = getUser().getId();			
				List<Messages> myMessagesList = staffManager.getMyMessagesBySenderAccountIdAndIsParent(String.valueOf(getUser().getId()),getUserCustId(),Constants.NO_STRING);
				if(!ObjectFunctions.isNullOrEmpty(myMessagesList)){
	                for (Messages messages : myMessagesList)
	                {
	                	ViewStaffPersonAccountDetails staffDetails = (ViewStaffPersonAccountDetails)staffManager.get(ViewStaffPersonAccountDetails.class,Long.valueOf(messages.getSenderAccountId()),"accountId");
	                	if(!ObjectFunctions.isNullOrEmpty(staffDetails)) {
	                		messages.setFullPersonName(staffDetails.getPersonFullName());
	                	}
	                	getMessagesList().add(messages);
	                	staffDetails=null;
	                	messages = null;
	                }
	                
	                Collections.sort(getMessagesList());
				}
				myMessagesList=null;
			}
			catch(Exception ex)
			{
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			
			return SUCCESS;
		}
		//Need to modify following code by seshu 
		@Actions({
			@Action(value = "ajaxGetLatestMySubjectsPerformance", results = { @Result(type = "json", name = "success", params = {"includeProperties","tempString.*" }) }) })
			public String ajaxGetLatestMySubjectsPerformance() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetLatestMySubjectsPerformance' method");
			}
			try {
				if(getUserAcademicYearId() > 0) {
 						JSONObject ja = new JSONObject();
 						JSONArray catogeryArray = new JSONArray();
 						ClassTeacher teacherSubject = staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
 						 if(!ObjectFunctions.isNullOrEmpty(teacherSubject)){
 							 	JSONArray passStudsCountArray = new JSONArray();
		 						JSONArray failStudsCountArray = new JSONArray();
		 						JSONArray absentStudsCountArray = new JSONArray();
		 						JSONArray totalSeriesArray = new JSONArray();
		 						JSONObject passSeries = new JSONObject();
		 						JSONObject failSeries = new JSONObject();
		 						JSONObject absentSeries = new JSONObject();
		 						double passStudentCount=0f;
		 						double failStudentCount=0f;
		 						double failPercentage=0f;
								double passPercentage=0f;
								double absentPercentage=0f;
								int totalStudents=0;
								List<ViewStudentsLatestExamMarksDetails> latestStudentMarks = staffManager.getAll(ViewStudentsLatestExamMarksDetails.class,"classSectionId="+teacherSubject.getStudyClass().getId()+" and custId ="+getUserCustId()+" and examStartDate is not null group by examTypeName order by lastUpdatedDate DESC");
 									if(ObjectFunctions.isNotNullOrEmpty(latestStudentMarks)){
 										ViewStudentsLatestExamMarksDetails studentMarks=latestStudentMarks.get(0);
 										totalStudents=studentManager.getClassStudentsCountByClassIdandStatus(teacherSubject.getStudyClass().getId(),Constants.YES_STRING,getUserCustId());
 										if(totalStudents > 0){
 											passStudentCount=staffManager.getPassStudentsCount(teacherSubject.getStudyClass().getId(), Long.valueOf(studentMarks.getExamTypeId()),teacherSubject.getStudySubject().getId(),getUserAcademicYearId(),Double.valueOf(studentMarks.getMinMarks()));
 	 										failStudentCount=staffManager.getFailStudentsCount(teacherSubject.getStudyClass().getId(), Long.valueOf(studentMarks.getExamTypeId()),teacherSubject.getStudySubject().getId(),getUserAcademicYearId(),Double.valueOf(studentMarks.getMinMarks()));
 	 										if(passStudentCount == 0.0 && failStudentCount==0.0){
 		 											 // do nothing
 		 										 }else{
 													failPercentage=(failStudentCount/(Double.valueOf(totalStudents)))*100;
 													passPercentage=(passStudentCount/(Double.valueOf(totalStudents)))*100;
 		 											catogeryArray.put(studentMarks.getExamTypeName()+": "+studentMarks.getClassAndSection()+"-"+teacherSubject.getSubjectName());
 		 											passStudsCountArray.put(roundTwoDecimals(passPercentage));
 		 											failStudsCountArray.put(roundTwoDecimals(failPercentage));
 		 											absentPercentage=(Double.valueOf(totalStudents)- passStudentCount+failStudentCount)/Double.valueOf(totalStudents)*100;
 		 											absentStudsCountArray.put(roundTwoDecimals(absentPercentage));
 												}
 										}
 	 									studentMarks=null;
 								 }
 							    passSeries.put("name", "Pass Percentage");
								passSeries.put("data", passStudsCountArray);
								totalSeriesArray.put(passSeries);
								failSeries.put("name", "Fail Percentage");
								failSeries.put("data", failStudsCountArray);
								totalSeriesArray.put(failSeries);
								absentSeries.put("name", "Absent Percentage");
								absentSeries.put("data", absentStudsCountArray);
								totalSeriesArray.put(absentSeries);
	 							ja.put("series", totalSeriesArray);
	 							ja.put("categories", catogeryArray);
	 							getResponse().getOutputStream().print(ja.toString());
	 							teacherSubject=null;
	 							passStudsCountArray=null;
	 							failStudsCountArray=null;
	 							absentStudsCountArray=null;
	 							totalSeriesArray=null;
	 							passSeries=null;
	 							failSeries=null;
	 							absentSeries=null;
 					}
 						ja=null;
 						catogeryArray=null;
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
		 
		@Actions( { 
			 @Action(value = "ajaxDoGetStaffByRoleForPayroll", results = { @Result(location = "../admin/payroll/ajaxViewStaffDetails.jsp", name = "success") }),
			 @Action(value = "ajaxGetStaffByRoleForLoan", results = { @Result(location = "../admin/loan/ajaxViewStaffLoanDetails.jsp", name = "success") }),
			 @Action(value = "ajaxDoGetStaffByRoleForLoan", results = { @Result(location = "../admin/loan/ajaxAddStaffLoanDetails.jsp", name = "success") }),
			 @Action(value = "ajaxGetStaffByRoleForPayrollSettings", results = { @Result(location = "../admin/payroll/ajaxViewStaffForPayrollSettings.jsp", name = "success") })
		})
		public String ajaxDoGetStaffByRoleForPayroll() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetStaffByRoleForPayroll' method");
			}
			try {
				String stLoan = getParamValue("stLoan");
				prepareStaffRolesMap(null);
				if(!StringFunctions.isNullOrEmpty(getTempString())){
					setStaffsList(adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+getTempString()+"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId()));
					if(ObjectFunctions.isNotNullOrEmpty(getStaffsList()))
						Collections.sort(getStaffsList());
					if(getTempId() != 0){
						ajaxStaffPersonalInfo();
					}else if(!ObjectFunctions.isNullOrEmpty(getStaffsList())){
						setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails)getStaffsList().get(0));
					}
				}
				if(!StringFunctions.isNullOrEmpty(getTempString())){
					setStaffsList(adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+getTempString()+"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId()));
					if(ObjectFunctions.isNotNullOrEmpty(getStaffsList()))
						Collections.sort(getStaffsList());
					if(!ObjectFunctions.isNullOrEmpty(getStaffsList()))
					{
						if(getTempId() != 0){
							ajaxStaffPersonalInfo();
						}else if(!ObjectFunctions.isNullOrEmpty(getStaffsList())){
							setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails)getStaffsList().get(0));
						}
						if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails()))
						{
							List<PayrollTypes> prlList = new ArrayList<PayrollTypes>();
							String sqlQuery = " defaultStatus='Y' ";
							List<PayrollTypes> payrollList1 = adminManager.getAll(PayrollTypes.class,sqlQuery);
							if(!ObjectFunctions.isNullOrEmpty(payrollList1))
							{
								prlList.addAll(payrollList1);
							}
							sqlQuery = " custId = " + getUserCustId();
							List<PayrollTypes> payrollList = adminManager.getAll(PayrollTypes.class,sqlQuery);
							if(!ObjectFunctions.isNullOrEmpty(payrollList))
							{
								prlList.addAll(payrollList);
							}
							setPayrollTypesList(prlList);
							sqlQuery = "SELECT *  FROM payrollSettings  o1 WHERE o1.staffId = "+ getViewStaffPersonAccountDetails().getStaffId()+ " and o1.custId = "+ getUserCustId() + " and o1.payrollSettingStatus = 'A' and o1.lastUpdatedDate ORDER BY o1.lastUpdatedDate";
	                       log.debug(sqlQuery);
	                       List<Object[]> payrollsettingsList = staffManager.getAll(sqlQuery);
							if(!ObjectFunctions.isNullOrEmpty(payrollsettingsList))
							{
								//List list = new ArrayList();
								for(Object[] obj: payrollsettingsList)
								{
									PayrollSettings payrollSettings = (PayrollSettings) staffManager.get(PayrollSettings.class, Long.valueOf(obj[0].toString()));
									if(!ObjectFunctions.isNullOrEmpty(payrollSettings))
									{
										getPayrollSettingsList().add(payrollSettings);
									}
								}
							}
							prlList=null;
							payrollList1=null;
							payrollList=null;
							payrollsettingsList=null;
						}
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
@Actions( {
		@Action(value = "ajaxDoGetCategoryQuestion", results = { @Result(location = "quiz/quizQuestionList.jsp", name = "success") }),
		@Action(value = "ajaxStaffCategoryQuestionAnswer", results = { @Result(location = "quiz/staffQuizQuestionAnswerList.jsp", name = "success") }) })
public String ajaxDoGetCategoryQuestion() throws URTUniversalException {

	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxCancelQuestion' method");
	}
	try {
		List<ViewStudentQuestionAnswers> questionList=null;
		if(!ObjectFunctions.isNullOrEmpty(getUser().getId())){
			    setCategoryQuestionList(staffManager.getQuizQuestionListWithCategoryIdAndTeacherId(Long.valueOf(getAnyId()),getUser().getId(),getUserCustId()));
				questionList=staffManager.getQuizQuestionListWithStudentList(Long.valueOf(getAnyId()),getUserCustId());
		}
		if (!ObjectFunctions.isNullOrEmpty(questionList)) {
			for (ViewStudentQuestionAnswers quizQuestion : questionList) {
				if (!ObjectFunctions.isNullOrEmpty(quizQuestion)) {
					List studentCorrectList = studentManager.getStudentCorrectAnswersList(quizQuestion.getQuestionId(),getUserCustId());
					List studentNotCorrectList = studentManager.getStudentNotCorrectAnswersList(quizQuestion.getQuestionId(),getUserCustId());
					ViewStudentQuestionAnswers questionAnswers = new ViewStudentQuestionAnswers();
					questionAnswers.setStudentAnswer(quizQuestion.getStudentAnswer());
					questionAnswers.setCorrectAnswer(quizQuestion.getCorrectAnswer());
					questionAnswers.setQuestionName(quizQuestion.getQuestionName());
					questionAnswers.setQuestionId(quizQuestion.getQuestionId());
					questionAnswers.setCustId(studentCorrectList.size());
					questionAnswers.setStudentId(studentNotCorrectList.size());
					getStudentQuestionAnswerList().add(questionAnswers);
					questionAnswers=null;
					quizQuestion=null;
					studentCorrectList=null;
					studentNotCorrectList=null;
				}
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
}
	public void generateStaffApprovararsList(String staffType){
		if(Constants.SCHOOL_TEACHER.equalsIgnoreCase(staffType) || Constants.SCHOOL_ASST_STAFF.equalsIgnoreCase(staffType))
		{
			List<ViewStaffPersonAccountDetails> schoolHodsList = adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+Constants.SCHOOL_HOD+"','"+Constants.SCHOOL_PRINCIPAL+"','"+Constants.SCHOOL_VICEPRINCIPAL+"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(schoolHodsList))
			{
                for (ViewStaffPersonAccountDetails classTeacher  : schoolHodsList )
                {			                	
                	getSelectboxMap().put(classTeacher.getAccountId(), classTeacher.getPersonFullName()+" ("+classTeacher.getRoleDescription()+")");
                	classTeacher=null;
                }
			}
			schoolHodsList=null;
		}
		else if(Constants.SCHOOL_ROLE_MESS_MANAGER.equalsIgnoreCase(staffType))
		{
			List<ViewStaffPersonAccountDetails> schoolAdminPrincipalList = adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+Constants.SCHOOL_ADMIN+"','"+Constants.SCHOOL_PRINCIPAL+"','"+Constants.SCHOOL_ADMINOFFICER+"','"+Constants.SCHOOL_VICEPRINCIPAL+"','"+Constants.SCHOOL_HOSTEL+"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(schoolAdminPrincipalList))
			{
                for (ViewStaffPersonAccountDetails classTeacher  : schoolAdminPrincipalList )
                {			                	
                	getSelectboxMap().put(classTeacher.getAccountId(), classTeacher.getPersonFullName()+" ("+classTeacher.getRoleDescription()+")");
                	classTeacher=null;
                }
			}
			schoolAdminPrincipalList=null;
		}
		else if(Constants.SCHOOL_CLERK.equalsIgnoreCase(staffType))
		{
			List<ViewStaffPersonAccountDetails> schoolAdminPrincipalList = adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+Constants.SCHOOL_ADMIN+"','"+Constants.SCHOOL_PRINCIPAL+"','"+Constants.SCHOOL_ADMINOFFICER+"','"+Constants.SCHOOL_VICEPRINCIPAL+"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(schoolAdminPrincipalList))
			{
                for (ViewStaffPersonAccountDetails classTeacher  : schoolAdminPrincipalList )
                {			                	
                	getSelectboxMap().put(classTeacher.getAccountId(), classTeacher.getPersonFullName()+" ("+classTeacher.getRoleDescription()+")");
                	classTeacher=null;
                }
			}
			schoolAdminPrincipalList=null;
		}
		else if(Constants.SCHOOL_HOD.equalsIgnoreCase(staffType)|| Constants.SCHOOL_FINANCE.equalsIgnoreCase(staffType) || Constants.SCHOOL_LIBRARIAN.equalsIgnoreCase(staffType) || Constants.SCHOOL_TRANSPORT.equalsIgnoreCase(staffType) || Constants.SCHOOL_HOSTEL.equalsIgnoreCase(staffType) || Constants.SCHOOL_HOSTELFINANCE.equalsIgnoreCase(staffType) || Constants.SCHOOL_TRANSPORT.equalsIgnoreCase(staffType) || Constants.SCHOOL_TRANSPORTFINANCE.equalsIgnoreCase(staffType))
		{
			List<ViewStaffPersonAccountDetails> schoolHodsList = adminManager.getViewStaffDetailsByRoleName(Constants.SCHOOL_PRINCIPAL,getUserCustId(),Constants.YES_STRING);
			if(!ObjectFunctions.isNullOrEmpty(schoolHodsList))
			{
                for (ViewStaffPersonAccountDetails classTeacher : schoolHodsList )
                {			                	
                	getSelectboxMap().put(classTeacher.getAccountId(), classTeacher.getPersonFullName()+" ("+classTeacher.getRoleDescription()+")");
                	classTeacher=null;
                }
			}
			schoolHodsList=null;
		}else if(Constants.SCHOOL_PRINCIPAL.equalsIgnoreCase(staffType) || Constants.SCHOOL_VICEPRINCIPAL.equalsIgnoreCase(staffType) || Constants.SCHOOL_FINANCE.equalsIgnoreCase(staffType) || Constants.SCHOOL_LIBRARIAN.equalsIgnoreCase(staffType) || Constants.SCHOOL_TRANSPORT.equalsIgnoreCase(staffType) || Constants.SCHOOL_HOSTEL.equalsIgnoreCase(staffType) || Constants.SCHOOL_HOSTELFINANCE.equalsIgnoreCase(staffType) || Constants.SCHOOL_TRANSPORT.equalsIgnoreCase(staffType) || Constants.SCHOOL_TRANSPORTFINANCE.equalsIgnoreCase(staffType) || Constants.SCHOOL_ROLE_DIRECTOR.equalsIgnoreCase(staffType))
		{
			List<ViewUserRoles> userRoles = null;
			if("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()))
				userRoles=adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_ADMIN, getUserCustId(), Constants.NO_STRING);
			else if ("ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_VICEPRINCIPAL".equalsIgnoreCase(getUser().getUserRole()))
				userRoles=adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_ADMIN, getUserCustId(), Constants.NO_STRING);
			else if ("ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole()))
				userRoles=adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_ADMINOFFICER, getUserCustId(), Constants.NO_STRING);
			else if("ROLE_DIRECTOR".equalsIgnoreCase(getUser().getUserRole()))
				userRoles=adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_ADMIN, getUserCustId(), Constants.NO_STRING);
			if(!ObjectFunctions.isNullOrEmpty(userRoles)){
				for(ViewUserRoles userRole:userRoles){
					getSelectboxMap().put(userRole.getAcountId(),userRole.getPersonFullName()+" ("+userRole.getRoleDescription()+")");
					userRole=null;
				}
			}
			userRoles=null;
		}else if (Constants.SCHOOL_HOSTELFINANCE.equalsIgnoreCase(staffType)) {
			List<ViewUserRoles> hostelUserRoles = adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_HOSTEL, getUserCustId(),Constants.NO_STRING);
			if (!ObjectFunctions.isNullOrEmpty(hostelUserRoles)) {
				for (ViewUserRoles userRole : hostelUserRoles) {
					getSelectboxMap().put(userRole.getAcountId(),userRole.getPersonFullName() + " ("+ userRole.getRoleDescription() + ")");
					userRole = null;
				}
			}
			hostelUserRoles=null;
		}else if (Constants.SCHOOL_TRANSPORTFINANCE.equalsIgnoreCase(staffType)) {
			List<ViewUserRoles> hostelUserRoles = adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_TRANSPORT, getUserCustId(),Constants.NO_STRING);
			if (!ObjectFunctions.isNullOrEmpty(hostelUserRoles)) {
				for (ViewUserRoles userRole : hostelUserRoles) {
					getSelectboxMap().put(userRole.getAcountId(),userRole.getPersonFullName() + " ("+ userRole.getRoleDescription() + ")");
					userRole = null;
				}
			}
			hostelUserRoles=null;
		}else if (Constants.SCHOOL_ADMIN.equalsIgnoreCase(staffType) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(staffType) || Constants.SCHOOL_ADMIN_COORDINATOR.equalsIgnoreCase(staffType)) {
			
			List<ViewStaffPersonAccountDetails> schoolPrincipalsList = adminManager.getViewStaffDetailsByRoleName(Constants.SCHOOL_PRINCIPAL,getUserCustId(),Constants.YES_STRING);
			if(!ObjectFunctions.isNullOrEmpty(schoolPrincipalsList))
			{
                for (ViewStaffPersonAccountDetails principal : schoolPrincipalsList )
                {			                	
                	getSelectboxMap().put(principal.getAccountId(), principal.getPersonFullName()+" ("+principal.getRoleDescription()+")");
                	classTeacher=null;
                }            
			}
		}else if (Constants.SCHOOL_ROLE_STOREKEEPER.equalsIgnoreCase(staffType) || Constants.SCHOOL_ROLE_RECEPTIONIST.equalsIgnoreCase(staffType)) {
			
			List<ViewStaffPersonAccountDetails> schoolAdminPrincipalList = adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+Constants.SCHOOL_ADMIN+"','"+Constants.SCHOOL_PRINCIPAL+"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(schoolAdminPrincipalList))
			{
                for (ViewStaffPersonAccountDetails classTeacher  : schoolAdminPrincipalList )
                {			                	
                	getSelectboxMap().put(classTeacher.getAccountId(), classTeacher.getPersonFullName()+" ("+classTeacher.getRoleDescription()+")");
                	classTeacher=null;
                }
			}
			schoolAdminPrincipalList=null;
		}
		
	}
	@Actions( { @Action(value = "ajaxRemoveStaffPendingLeave", results = { @Result(location = "leaves/ajaxViewStaffLeaves.jsp", name = "success") }) })
	public String ajaxRemoveStaffPendingLeave() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveStaffPendingLeave' method");
		}
		try{
			if(!StringFunctions.isNullOrEmpty(getAnyId())){
				int responseCode = staffManager.deleteLeaveInfo(Long.valueOf(getAnyId()));
				if(responseCode == 0){
					super.addActionMessage("Leave removed successfully.");
				}else{
					super.addActionError("Leave not deleted.");
				}
				ajaxViewStaffLeaves();				
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxViewStudentAndStaffLeaves", results = { @Result(location = "leaves/ajaxViewStaffAndStudentLeavesForApprovals.jsp", name = "success") }) })
	public String ajaxViewStudentAndStaffLeaves() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudentAndStaffLeaves' method");
		}
		try{
			ViewUserRoles userRoles=adminManager.getViewUserRolesByUserIdAndCustId(getUser().getId(),getUserCustId(),Constants.NO_STRING);
			if(!ObjectFunctions.isNullOrEmpty(userRoles)){
				ajaxGetStaffAndStudentPendingLeavesList(getUserAcademicYearId(),userRoles.getRoleName(),getTempString());
			}
			setTempString(getTempString());
			log.debug(getTempString());
			getSmsCount();
			userRoles=null;
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 /*
	* Removed PrepareAcademicYearId and used getUserAcademicYearId() as well as modularity is done by venkatesh - 04-26-2013
	*/
	public void ajaxGetStaffAndStudentPendingLeavesList(long userReqYearId,String roleName,String studentOrStaff)
	{
		List<ViewStaffLeaveDetails> viewStaffPendingLeaveDetailsList=null;
		List<ViewStudentLeaveDetails> viewStudentPendingLeavesDetailsList=null;
		List<ViewHostelStudentLeaveDetails> viewHostelStudentPendingLeavesDetailsList=null;
		setTempList(null);
		setClassNameList(null);
		if (Constants.SCHOOL_TEACHER.equalsIgnoreCase(roleName) || Constants.SCHOOL_HOD.equalsIgnoreCase(roleName)) {
			/*below lines used to when logn with ClassTeacher. Classtacher can only view the leaves for staff login in staffLeaves tab.Classteacher obj used in page changed by cvs on 9-6-2014*/
			Staff staff = (Staff)  staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
			if(!ObjectFunctions.isNullOrEmpty(staff)){
				 ClassTeacher classTeacherClass=staffManager.getClassTeacherByStaffIdandAcademicYear(staff.getId(),getUserAcademicYearId(),getUserCustId());
				 if(!ObjectFunctions.isNullOrEmpty(classTeacherClass)){
					 setClassTeacher(classTeacherClass);
					 viewStaffPendingLeaveDetailsList =staffManager.getAllFutureLeavesByStatusAndRoleName(getUserCustId(), Constants.PENDING_STATUS,Constants.SCHOOL_TEACHER,userReqYearId);
				 }
			}
			if(studentOrStaff.equalsIgnoreCase("student"))
				viewStudentPendingLeavesDetailsList =staffManager.getAllFutureStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(), Constants.PENDING_STATUS,Constants.SCHOOL_STUDENT, getUser().getId(),userReqYearId);
			if (Constants.SCHOOL_HOD.equalsIgnoreCase(roleName))
				if(studentOrStaff.equalsIgnoreCase("staff"))
				{
					List<ViewStaffLeaveDetails> staffPendingList = adminManager.getAllFutureLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(), Constants.PENDING_STATUS,Constants.SCHOOL_TEACHER, getUser().getId(),userReqYearId);
					if(!ObjectFunctions.isNullOrEmpty(staffPendingList))
						viewStaffPendingLeaveDetailsList.addAll(staffPendingList);
					List<ViewStaffLeaveDetails> asstStaffPendingList =adminManager.getAllFutureLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(), Constants.PENDING_STATUS,Constants.SCHOOL_ASST_STAFF, getUser().getId(),userReqYearId);
					if(!ObjectFunctions.isNullOrEmpty(asstStaffPendingList))
						viewStaffPendingLeaveDetailsList.addAll(asstStaffPendingList);
				}
					
		}else if (Constants.SCHOOL_PRINCIPAL.equalsIgnoreCase(roleName) || Constants.SCHOOL_VICEPRINCIPAL.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMIN.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(roleName)) {
			if(studentOrStaff.equalsIgnoreCase("staff")){
				if(Constants.SCHOOL_ADMIN.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(roleName))
					viewStaffPendingLeaveDetailsList = adminManager.getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(getUserCustId(),"'"+Constants.SCHOOL_HOD+"','"+Constants.SCHOOL_TEACHER+"','"+Constants.SCHOOL_PRINCIPAL+"','"+Constants.SCHOOL_VICEPRINCIPAL+"','"+Constants.SCHOOL_FINANCE+"','"+Constants.SCHOOL_LIBRARIAN+"','"+Constants.SCHOOL_TRANSPORT+"','"+Constants.SCHOOL_TRANSPORTFINANCE+"','"+Constants.SCHOOL_HOSTEL+"','"+Constants.SCHOOL_HOSTELFINANCE+"','"+Constants.SCHOOL_ASST_STAFF+"','"+Constants.SCHOOL_ROLE_MESS_MANAGER+"','"+Constants.SCHOOL_CLERK+"','"+Constants.SCHOOL_ROLE_DIRECTOR+"','"+Constants.SCHOOL_ROLE_STOREKEEPER+"','"+Constants.SCHOOL_ROLE_RECEPTIONIST+"','"+Constants.SCHOOL_ADMIN_COORDINATOR+"'",Constants.PENDING_STATUS,userReqYearId);
				else if(Constants.SCHOOL_PRINCIPAL.equalsIgnoreCase(roleName))
					viewStaffPendingLeaveDetailsList = adminManager.getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(getUserCustId(),"'"+Constants.SCHOOL_HOD+"','"+Constants.SCHOOL_TEACHER+"','"+Constants.SCHOOL_FINANCE+"','"+Constants.SCHOOL_LIBRARIAN+"','"+Constants.SCHOOL_TRANSPORT+"','"+Constants.SCHOOL_TRANSPORTFINANCE+"','"+Constants.SCHOOL_HOSTEL+"','"+Constants.SCHOOL_HOSTELFINANCE+"','"+Constants.SCHOOL_VICEPRINCIPAL+"','"+Constants.SCHOOL_ADMIN+"','"+Constants.SCHOOL_ADMINOFFICER+"','"+Constants.SCHOOL_ASST_STAFF+"','"+Constants.SCHOOL_ROLE_MESS_MANAGER+"','"+Constants.SCHOOL_ROLE_STOREKEEPER+"','"+Constants.SCHOOL_ADMIN_COORDINATOR+"'",Constants.PENDING_STATUS,userReqYearId);
				else
					viewStaffPendingLeaveDetailsList = adminManager.getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(getUserCustId(),"'"+Constants.SCHOOL_HOD+"','"+Constants.SCHOOL_TEACHER+"','"+Constants.SCHOOL_FINANCE+"','"+Constants.SCHOOL_LIBRARIAN+"','"+Constants.SCHOOL_TRANSPORT+"','"+Constants.SCHOOL_TRANSPORTFINANCE+"','"+Constants.SCHOOL_HOSTEL+"','"+Constants.SCHOOL_HOSTELFINANCE+"','"+Constants.SCHOOL_ASST_STAFF+"','"+Constants.SCHOOL_ROLE_MESS_MANAGER+"'",Constants.PENDING_STATUS,userReqYearId);
			}else{
				viewStudentPendingLeavesDetailsList =  adminManager.getAllStudentLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(getUserCustId(),Constants.SCHOOL_STUDENT,Constants.PENDING_STATUS,userReqYearId);
			}
		}else if (Constants.SCHOOL_HOSTEL.equalsIgnoreCase(roleName) || Constants.SCHOOL_TRANSPORT.equalsIgnoreCase(roleName) ) {
			if(studentOrStaff.equalsIgnoreCase("student")){
				if (Constants.SCHOOL_HOSTEL.equalsIgnoreCase(roleName))
				{
					viewHostelStudentPendingLeavesDetailsList =  adminManager.getAll(ViewHostelStudentLeaveDetails.class, "leaveStatus= '" + Constants.PENDING_STATUS +"' and custId ="+ getUserCustId() + " and roleName ='" + Constants.SCHOOL_STUDENT+ "' and academicYearId ="+userReqYearId + " order by startDate desc");
		            
				}
				else
					viewStaffPendingLeaveDetailsList = adminManager.getAllStaffLeavesByCustIdandRoleNameAndLeavesStatusAcademicYearId(getUserCustId(),"'"+Constants.SCHOOL_TRANSPORTFINANCE+"'",Constants.PENDING_STATUS,userReqYearId);
			}
			else
			{
				if (Constants.SCHOOL_HOSTEL.equalsIgnoreCase(roleName))
				{
					viewStaffPendingLeaveDetailsList =staffManager.getAllFutureLeavesByStatusAndRoleName(getUserCustId(), Constants.PENDING_STATUS,Constants.SCHOOL_ROLE_MESS_MANAGER,userReqYearId);
				}
			}
		}
		
		if(studentOrStaff.equalsIgnoreCase("student"))
		{
			if (!ObjectFunctions.isNullOrEmpty(viewStudentPendingLeavesDetailsList)) {
				for(ViewStudentLeaveDetails studentLeave:viewStudentPendingLeavesDetailsList){
					if(!ObjectFunctions.isNullOrEmpty(studentLeave)){
						double leavesTaken=staffAndStudentLeavesCount(studentLeave.getAccountId(),userReqYearId,studentLeave.getLeaveType());
						studentLeave.setTotalLeavesTaken(leavesTaken);
						if(Constants.SCHOOL_HOD.equalsIgnoreCase(roleName)|| Constants.SCHOOL_PRINCIPAL.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMIN.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(roleName) || Constants.SCHOOL_TEACHER.equalsIgnoreCase(roleName)){
							getTempList().add(studentLeave);
						}
					}
				}
				
			}
			if (Constants.SCHOOL_HOSTEL.equalsIgnoreCase(roleName))
			{
				if (!ObjectFunctions.isNullOrEmpty(viewHostelStudentPendingLeavesDetailsList)) 
				{
					for(ViewHostelStudentLeaveDetails studentLeave:viewHostelStudentPendingLeavesDetailsList){
						if(!ObjectFunctions.isNullOrEmpty(studentLeave)){
							double leavesTaken=staffAndStudentLeavesCount(studentLeave.getAccountId(),userReqYearId,studentLeave.getLeaveType());
							studentLeave.setTotalLeavesTaken(leavesTaken);
							getTempList().add(studentLeave);
							studentLeave=null;
						}
					}
				}
				
			}
			
			if(Constants.SCHOOL_HOD.equalsIgnoreCase(roleName) || Constants.SCHOOL_PRINCIPAL.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMIN.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(roleName) || Constants.SCHOOL_HOSTEL.equalsIgnoreCase(roleName) || Constants.SCHOOL_TEACHER.equalsIgnoreCase(roleName)){
				setClassNameList(getTempList());
			}else{
				setClassNameList(viewStudentPendingLeavesDetailsList);
			}
		}
		
		else{
			if(!ObjectFunctions.isNullOrEmpty(viewStaffPendingLeaveDetailsList)){
				for(ViewStaffLeaveDetails staffLeave:viewStaffPendingLeaveDetailsList){
					if(!ObjectFunctions.isNullOrEmpty(staffLeave)){
						setLeaveManagement((LeaveManagement) adminManager.get(LeaveManagement.class,"custId="+getUserCustId()+" and roleId="+staffLeave.getRoleId()+" and permanentOrContract='"+staffLeave.getPermanentOrContract()+ "'  and academicYearId="+getUserAcademicYearId()));
						staffLeavesManagement(staffLeave.getAccountId());
						staffLeave.setTotalLeavesTaken(getCasualLeave()+getSickLeave()+getPayLeaves()+getEarnedLeave());
						if(getPayLeaves() > 0)
							staffLeave.setSummary("Casual Leaves: "+getCasualLeave()+", Sick Leaves: "+getSickLeave()+", Earned Leaves: "+getEarnedLeave()+", Pay Leaves: "+getPayLeaves());
						else
							staffLeave.setSummary("Casual Leaves: "+getCasualLeave()+", Sick Leaves: "+getSickLeave()+", Earned Leaves: "+getEarnedLeave());
						getTempList().add(staffLeave);
					}
				}
				setClassNameList(getTempList());
			}
		}
		viewStudentPendingLeavesDetailsList=null;
	}
	
	public double staffAndStudentLeavesCount(long accountId,long academicYearId,String leaveStatus)
	{
		double leavesTaken=0;
		List<Leave> leavesList = staffManager.getLeavesListByAccountId(accountId,Constants.ACTIVE_STATUS,getUserCustId(),academicYearId);
		if(!ObjectFunctions.isNullOrEmpty(leavesList))
		{
                for (Leave leave : leavesList)
                {
                	if(StringFunctions.isNotNullOrEmpty(leave.getLeaveType())){
                		if(leaveStatus.equalsIgnoreCase(leave.getLeaveType()))
	                	{
	                		leavesTaken= leavesTaken + leave.getNoofDays();
	                	}
                	}else{
                		leavesTaken= leavesTaken + leave.getNoofDays();
                	}
                	leave = null;
                }
		 }
		leavesList=null;
		return leavesTaken;
	}
	
	/*
	* Removed unnecessary code is done by venkatesh - 09-12-2013
	*/
	@Actions( { @Action(value = "ajaxApproveOrRejectLeave", results = { @Result(location = "leaves/ajaxViewStaffAndStudentLeavesForApprovals.jsp", name = "success") }) })
	public String ajaxApproveLeave() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxApproveLeave' method");
		}
		try{
			if(StringFunctions.isNotNullOrEmpty(getAnyId()) && StringFunctions.isNotNullOrEmpty(getAnyTitle()) && StringFunctions.isNotNullOrEmpty(getTempString())){
				int responseCode =staffManager.approveOrRejectLeaves(getUser(),getAnyTitle(),getLeave().getApprovalsComment(),Long.valueOf(getAnyId()),"APP",getTempString());
				if(responseCode == 0)
					super.addActionMessage("Leave rejected successfully");
				else if(responseCode == 1)
					super.addActionMessage("Leave approved successfully");
				
				}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally
		{
			ajaxViewStudentAndStaffLeaves();
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxMyClassViewAttendence", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "myStudents" }) }) })
	public String ajaxMyClassViewAttendence() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxMyClassViewAttendence' method");
		}
		try {
			if(getUser().getId() > 0){
				Staff staff = (Staff)  staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
				if(!ObjectFunctions.isNullOrEmpty(staff)){
					 ClassTeacher classTeacherClass=staffManager.getClassTeacherByStaffIdandAcademicYear(staff.getId(),getUserAcademicYearId(),getUserCustId());
					 if(!ObjectFunctions.isNullOrEmpty(classTeacherClass)){
						 setTempId(classTeacherClass.getStudyClass().getId());
						 setTempString(classTeacherClass.getStudyClass().getClassAndSection());
						 generateChartForClassWiseStudentAttendance();
						 classTeacherClass=null;
					 }
					 staff=null;
				}
			}
			}
		 catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( {
		@Action(value = "ajaxCheckLeaveDates", results = { @Result(type = "json", name = "success",params = {"includeProperties","thresholdMonths,classTeacherStatus"}) }) })
	public String ajaxCheckLeaveDates() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckLeaveDates' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getLeave().getStartDate()) && !ObjectFunctions.isNullOrEmpty(getLeave().getEndDate()) && getUserAcademicYearId() > 0){
				Calendar startDate=Calendar.getInstance();
				startDate.setTime(getLeave().getStartDate());
				List<SchoolHolidays> holidayList=null;
				List<Leave> leavesList=null;
				AcademicYear academicYear=getCurrentAcademicYear();
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()) && (getUser().isSchoolTeacher() ||  getUser().isSchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector())){
						Staff staff=null;
						if(tempId2>0){ //here tempId2 is accountId get the value from  ajaxAppalyLeaves.jsp 
							staff = adminManager.getStaffByAcountId(tempId2,Constants.YES_STRING);
						}else{
							staff = adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
						}
						if (!ObjectFunctions.isNullOrEmpty(staff)){
							StringBuffer query =  new StringBuffer("select studyClassId from classTeacher ").append(" where custId=").append(getUserCustId()).append(" and academicYearId=").append(academicYear.getId()).append(" and teacherId=").append(staff.getId()).append(" group by studyClassId");
							List<BigInteger> studyclassIds = adminManager.getAll(query.toString());
							query = null;
							String classIdsString=null;
							if (ObjectFunctions.isNotNullOrEmpty(studyclassIds)) 
								classIdsString = StringFunctions.convertListToCommaDelimitedString(studyclassIds);
							else
								classIdsString="0";
							
							StringBuffer classQquery =  new StringBuffer("select classId from vw_classSectionDetails ").append(" where custId=").append(getUserCustId()).append(" and academicYearId=").append(academicYear.getId()).append(" and classSectionId in (").append(classIdsString).append(")");
							List<BigInteger> classNameClassIds = adminManager.getAll(classQquery.toString());
							classQquery = null;
							String classNameIdsString=null;
							if (ObjectFunctions.isNotNullOrEmpty(classNameClassIds)) 
								classNameIdsString = StringFunctions.convertListToCommaDelimitedString(classNameClassIds);
							else
								classNameIdsString="";
							holidayList = adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),academicYear.getId(),null,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getStartDate()),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getEndDate()),classNameIdsString.toString(),null,null,0,"OrderByDate",null);
							
						}
						staff=null;
					}else{
						holidayList=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),academicYear.getId(),null,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getStartDate()),DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getEndDate()),null,null,null,0,"OrderByDate",null);
					}
				JSONObject leavesDetails=null;
				//JSONArray holidays =null;
				if(ObjectFunctions.isNotNullOrEmpty(holidayList)){
					leavesDetails=new JSONObject();
					//holidays = new JSONArray();
					for(SchoolHolidays holiday:holidayList){
						//holidays.put(holiday.getHolidayDateStr()); // academicyear settings have classwise holidays(CH) then no of recods with classIds in holidays.That why we are remove the duplicate dates 
						getDisplayAttendanceSet().add(holiday.getHolidayDateStr());
					}
					leavesDetails.put("holidays", getDisplayAttendanceSet());
					setDisplayAttendanceSet(null);
					getResponse().getOutputStream().print(leavesDetails.toString());
				}else{
					leavesDetails=new JSONObject();
					//leavesList=adminManager.getAll(Leave.class, "accountId="+getUser().getId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
					if(getTempId2()>0 && getUser().isAdminOrDelegate()){
						leavesList=staffManager.getAll(Leave.class, "accountId="+getTempId2()+" and custId="+getUserCustId()+" and academicYearId="+academicYear.getId());
					}else{
						if(getLeave().getId()>0)
							leavesList=staffManager.getAll(Leave.class, "id!="+getLeave().getId()+" and accountId="+getUser().getId()+" and custId="+getUserCustId()+" and academicYearId="+academicYear.getId());
						else
							leavesList=staffManager.getAll(Leave.class, "accountId="+getUser().getId()+" and custId="+getUserCustId()+" and academicYearId="+academicYear.getId());
					}
					if(ObjectFunctions.isNotNullOrEmpty(leavesList)){
						boolean isAppliedLeave=false;
						for(;DateFunctions.compare2Dates(getLeave().getEndDate(),startDate.getTime());startDate.add(Calendar.DATE, 1)){
							for(Leave leave:leavesList){
								if(!ObjectFunctions.isNullOrEmpty(leave)){
									if(DateFunctions.compare2Dates(startDate.getTime(),leave.getStartDate()) && DateFunctions.compare2Dates(leave.getEndDate(),startDate.getTime())){
										isAppliedLeave=true;
										break;
									}
								}
							}
							if(isAppliedLeave)
								break;
						}
						if(isAppliedLeave){
							leavesDetails.put("leaveDesc", "You have already applied leave(s) between start date and end date(s).");
						}
					}
					leavesDetails.put("noOfDays", getLeave().getLeavesCount());
					getResponse().getOutputStream().print(leavesDetails.toString());
					leavesList=null;
				}
				holidayList=null;
			  }
			  academicYear=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	@Actions({
		@Action(value = "ajaxDoRegisterStudentEvent", results = { @Result(location = "ajaxInvitationDetails.jsp", name = "success") }),
		@Action(value = "ajaxDoShowEventDetails", results = { @Result(location = "showEventDetails.jsp", name = "success") }) })
		public String ajaxDoRegisterStudentEvent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoRegisterStudentEvent' method");
		}
		try {
			String eventId = null;
			if(!StringFunctions.isNullOrEmpty(getEventId())) {
				eventId = getEventId();
			}
			if(StringFunctions.isNullOrEmpty(eventId)) {
				eventId = getParamValue("id");
			}
			StringBuffer stringBuffer=new StringBuffer();
			stringBuffer.append("(");
			if(!ObjectFunctions.isNullOrEmpty(getEventInvitedUserList()))
			{
				List<Long> eventInvitedUserLongList = getEventInvitedUserList();
			      for(Long accountIds : eventInvitedUserLongList){
			    	  stringBuffer.append(accountIds);
			    	  stringBuffer.append(",");
			      }
			}
			stringBuffer.append("0)");
			List<Student> studentList=staffManager.getNotInvitedStudents(stringBuffer.toString());
			if(ObjectFunctions.isNotNullOrEmpty(studentList)){
				for(Student student : studentList){
					if(!ObjectFunctions.isNullOrEmpty(student)){
						getAllStudentsList().add(student.getRollNumber());
					}
				}
			}
			
			setClassNameList(getAllStudentsList());
			//eventInvitedUser=null;
			studentList=null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("studentIds"))){
			getSession().removeAttribute("studentIds");
		}
		return SUCCESS;
	}
	/********************************************************************
	 *
	 * Date              Name               Description
	 * ========          ============       ==================
	 * May 7, 2013		 CVS				 set student details when u click the staudent name in profile search 
	/********************************************************************/
	@Actions({ @Action(value = "ajaxDoViewStudentDetails", results = { @Result(location = "popupViewStudentDetails.jsp", name = "success") }) })
		public String ajaxDoViewStudentDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStudentDetails' method");
		   }
		try {
			if(getTempId2()>0){
			setViewStudentPersonAccountDetails((ViewStudentPersonAccountDetails)staffManager.get(ViewStudentPersonAccountDetails.class," custId="+getUserCustId()+" and accountId="+getTempId()+" and classsectionId="+getTempId2()));
			}else if (getTempId2() == 0 && !ObjectFunctions.isNullOrEmpty(getAnyTitle())){
				setViewStudentPersonAccountDetails((ViewStudentPersonAccountDetails)staffManager.get(ViewStudentPersonAccountDetails.class," custId="+getUserCustId()+" and accountId="+getTempId()+" and classsectionId in "+getAnyTitle()));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/* @Description 3rd Jul cvs: Modularization  below method  show the search the  staff*/  
	@Actions( { @Action(value = "ajaxSearchStaffByName", results = { @Result(location = "manageStaff/ajaxViewEditStaffDetails.jsp", name = "success") }) })
    public String ajaxSearchStaffByName() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxSearchStaffByName' method");
	}
	try {
		StringBuffer sqlQuery = null;
		if(getUserCustId() > 0){
        	sqlQuery = new StringBuffer("select  distinct(sd.staffId),sd.firstName,sd.lastName,sd.fullName,sd.mobileNumber,sd.roleDescription,sd.roleName,sd.qualification1  from vw_staffDetails sd LEFT JOIN classTeacher ct on (sd.staffId=ct.teacherId)") 
        	.append(" where sd.custId=").append(getUserCustId()).append(" and sd.academicYearId="+getUserAcademicYearId()+"").append(" and sd.status='"+Constants.YES_STRING+"'").append(" and (firstName like '%"+ getAnyTitle() + "%' or lastName like '%"+ getAnyTitle()+ "%' or fullName like '%"+ getAnyTitle().trim()+ "%') "). append(" order by roleName");
        	setObjectList(staffManager.getAll(sqlQuery.toString()));
		} 
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
    }
	
	/* @Description 5th Jul cvs: Modularization  below method  show disable the staff*/  
	@Actions( { @Action(value = "ajaxDoDisableStaff", results = { @Result(location = "manageStaff/ajaxDoStaffDisable.jsp", name = "success") }) })
    public String ajaxDoDisableStaff() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDoDisableStaff' method");
		}
		try {
			    setStaff(null);
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
    }
	/* @Description 5th Jul cvs: Modularization  below method  show disable the staff*/  
	@Action(value = "ajaxDisableStaff", results = { @Result(location = "manageStaff/ajaxViewEditStaffDetails.jsp", name = "success") }) 
	 public String ajaxDisableStaff() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDisableStaff' method");
		}
		try {
			 Map<String,String> msg = staffManager.disableStaff(getTempId(),getStaffVo().getDescription(), getUser().getId());
			 addActionMessages(msg);
			ajaxDoManageStaff();
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
    }
	
	/********************************************************************
	 * version		Date              	Name               Description
	 * ========     ===========     	============       ==================
	 * 1.0			Oct 31, 2013		Seshu			   Downloading staff template in edit and creation process.
	/********************************************************************/	
	@Actions( { @Action(value = "ajaxDownloadStaffsDetails", results = {}) })
	public void ajaxDownloadStaffsDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadStaffsDetails' method");
		}
		try {
			if (getUserAcademicYearId() != 0) {
				String fileName=null;
				if ("update".equals(getAnyTitle())) {
				fileName = "Update_Staff_Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				}else
					fileName = "Import_Staff_Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
					
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				PrepareStaffExcel prepareStaffExcel = new PrepareStaffExcel();
				List<Object[]> staffDetails = null;
				List<State> states = (List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST);
				List<MotherTongue> motherTongues = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
				
				List<CastSettings> communityList = studentManager.getAllByCustId("CastSettings", getUserCustId(),0);
				List<SubCastSettings> castNameList = adminManager.getAll(SubCastSettings.class,"custId="+getUserCustId());
				
				prepareStaffRolesMap(null);
				prepareStaffExcel.createConfigurationsSheet("Configurations", states,motherTongues,getStaffRoles(),getUserAcademicYearId(),getUserCustId(),communityList,castNameList);
				StringBuffer sheetTitleDesc = new StringBuffer();
				StringBuffer query = null;
				sheetTitleDesc.append("School Name : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("organization"));
				}
				sheetTitleDesc.append(", Academic Year : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("academicYearName"));

				}
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					
					if ("update".equals(getAnyTitle())) {
						if(customer.isHostelModuleStatus() && customer.isTransportModuleStatus())
						{
							query = new StringBuffer(PrepareStaffExcel.query).append(" where custId=").append(getUserCustId()).append(" and status='Y' and roleName!='ROLE_ADMIN' and academicYearId <="+getUserAcademicYearId()+" and accountExpired='N' order by roleName,firstName,lastname");
							staffDetails = staffManager.getAll(query.toString());
						}
						else if(customer.isHostelModuleStatus())
						{
							query = new StringBuffer(PrepareStaffExcel.query).append(" where custId=").append(getUserCustId()).append(" and status='Y' and academicYearId <="+getUserAcademicYearId()+" and accountExpired='N' and roleName!='ROLE_ADMIN' and roleName!='ROLE_TRANSPORTFINANCE' and roleName!='ROLE_TRANSPORT' and roleName!='ROLE_CONDUCTOR' and roleName!='ROLE_DRIVER' and roleName!='ROLE_HELPER' order by roleName,firstName,lastname");
							staffDetails = staffManager.getAll(query.toString());
						}
						else if(customer.isTransportModuleStatus())
						{
							query = new StringBuffer(PrepareStaffExcel.query).append(" where custId=").append(getUserCustId()).append(" and status='Y' and academicYearId <="+getUserAcademicYearId()+" and accountExpired='N' and roleName!='ROLE_ADMIN' and roleName!='ROLE_HOSTEL' and roleName!='ROLE_HOSTELFINANCE' order by roleName,firstName,lastname");
							staffDetails = staffManager.getAll(query.toString());
						}
						else
						{
							query = new StringBuffer(PrepareStaffExcel.query).append(" where custId=").append(getUserCustId()).append(" and status='Y' and academicYearId <="+getUserAcademicYearId()+" and accountExpired='N' and roleName!='ROLE_ADMIN' and roleName!='ROLE_TRANSPORTFINANCE' and roleName!='ROLE_TRANSPORT' and roleName!='ROLE_CONDUCTOR' and roleName!='ROLE_DRIVER' and roleName!='ROLE_HELPER' and roleName!='ROLE_HOSTEL' and roleName!='ROLE_HOSTELFINANCE' order by roleName,firstName,lastname");
							staffDetails = staffManager.getAll(query.toString());
						}
						//if (ObjectFunctions.isNullOrEmpty(staffDetails)) {
							prepareStaffExcel.createStaffSheet("Staff",staffDetails,sheetTitleDesc.toString());
						//}
					} else {
						prepareStaffExcel.createStaffSheet("Staff",staffDetails, sheetTitleDesc.toString());
					}
				}
				prepareStaffExcel.finalPrep("Configurations", staffDetails);
				prepareStaffExcel.getWb().write(getResponse().getOutputStream());
				prepareStaffExcel = null;
				staffDetails = null;
				states = null;
				motherTongues = null;
				customer = null;
				query = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions( { @Action(value = "ajaxUploadStaffData", results = { @Result(location = "../admin/reports/ajaxDownloadStaffDetails.jsp", name = "success"),
			 @Result(location = "manageStaff/importStaffExcelSheet.jsp", name = "staffImport"),@Result(location = "manageStaff/ajaxViewStaffList.jsp", name = "staffHome") }) })
	    public String ajaxUploadStaffData() {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxUploadStaffData' method");
		}
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   Date date = new Date();
			   
			log.debug("*********** Entering 'ajaxUploadStaffData' method ********************" + dateFormat.format(date));
			
			boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				if("createStaff".equals(getTempString()))
					return "staffImport";
				else
					return SUCCESS;
			}
				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
				org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("Configurations");
				long academicYearId = 0;
				long custId = 0;
				if (ObjectFunctions.isNullOrEmpty(sheet)) {
					
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
						return "staffImport";
					
				} else 
				{
					org.apache.poi.ss.usermodel.Sheet firstSheet1 = workbook.getSheetAt(0);
					Row secondRow = firstSheet1.getRow(1);
					if(!ObjectFunctions.isNullOrEmpty(secondRow))
					{
						if(!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1)))
						{
							String fistColumn = secondRow.getCell(0).getStringCellValue();
							String secondColumn = secondRow.getCell(1).getStringCellValue();
							
							if(!"Staff Id".equalsIgnoreCase(fistColumn.toString()) || !"Role".equalsIgnoreCase(secondColumn.toString()))
							{
								log.debug("No file to upload....");
								super.addActionError("File type not matched.");
								return "staffImport";
							}
							fistColumn = null;
							secondColumn = null;
						}
						else
						{
							log.debug("No file to upload....");
							super.addActionError("File type not matched.");
							return "staffImport";
						}
					}
					else
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						return "staffImport";
					}
					
					Row row = sheet.getRow(0);
					academicYearId = (long) row.getCell(1).getNumericCellValue();
					row = sheet.getRow(1);
					custId = (long) row.getCell(1).getNumericCellValue();
				}
				if(getUserCustId()!=custId) {//If  customer upload wrong file
					log.debug("Uploaded wrong file..");
					super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
					return "staffImport";
				}
				SheetParser parser = null;
				if(custId !=0){
				Staff staff = null;
				User account = null;
				Person person = null;
				Address address=null;
				List<StaffExcelRow> staffDetailsList = null;
				Customer customer = null;
				StringBuffer query = null;
				String roleName = null;
				String userName = null;
				User staffNumberExist = null;
				CommonType religion  =null;
				CastSettings castSettings=null;
				SubCastSettings subCastSettings = null;
				AcademicYear academicYear = null;
				String moblieNumberDigitsOrNot="\\d+";
				Map<String,Long> motherTongueMap = new HashMap<String, Long>();
				Role role =null;
				HashMap<String, Role> rolesMap = null;
				Salary salary = null;
				String randamNumber=null;
				boolean userExists=false;
				String secondaryUserName=null;
				String newPassword=null;
				boolean dataInserted=false;
				Map<String,Long> commonTypeMap = new HashMap<String, Long>();
				if (ObjectFunctions.isNullOrEmpty(sheet)) {
					academicYearId = getUserAcademicYearId();
					custId = getCustId();
				} else {
					Row row = sheet.getRow(0);
					academicYearId = (long) row.getCell(1).getNumericCellValue();
					row = sheet.getRow(1);
					custId = (long) row.getCell(1).getNumericCellValue();
				}
				List<Integer> duplicateStaffUniqueRowNum = new ArrayList<Integer>();
				List<Integer> duplicateStaffBiometricIdRowNum = new ArrayList<Integer>();
				List<Integer> failedRecordRowNum = new ArrayList<Integer>();
				List<Integer> duplicateNameRowNum = new ArrayList<Integer>();
				StringBuilder mobileNumberMandatory =new StringBuilder();
				StringBuilder mobileNumberLength =new StringBuilder();
				StringBuilder gpfNumberLength =new StringBuilder();
				if(isCurrentAcademicYearId(academicYearId) && custId !=0){
					customer = (Customer)staffManager.get(Customer.class,custId);
					academicYear = (AcademicYear)staffManager.get(AcademicYear.class,academicYearId);
					statelist = staffManager.getCountryStates(0);
					List<Role> roles = staffManager.getAll(Role.class);
					if(ObjectFunctions.isNotNullOrEmpty(roles)){
						rolesMap = new HashMap<String, Role>();
						for(Role rolesobj : roles){
							rolesMap.put(rolesobj.getDescription().toLowerCase(), rolesobj);
						}
						roles = null;
					}
					List<MotherTongue> motherTonguelist = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
						if (!ObjectFunctions.isNullOrEmpty(motherTonguelist)) 
					{
						for(MotherTongue motherTongueObj : motherTonguelist)
						{
							motherTongueMap.put(motherTongueObj.getName(),motherTongueObj.getId());
						}
						
						motherTonguelist = null;
					}
					
					CommonTypeMainVO commonTypeMainVO = adminManager.getCommonTypes(custId, "RELIGION");
					if (!ObjectFunctions.isNullOrEmpty(commonTypeMainVO)) 
					{
						List<CommonTypeVO> commonTypeVOList = commonTypeMainVO.getCommonTypeVOList();
						if (!ObjectFunctions.isNullOrEmpty(commonTypeVOList)) 
						{
							for(CommonTypeVO commonTypeVO : commonTypeVOList)
							{
								commonTypeMap.put(commonTypeVO.getSkillTypeName().trim().toUpperCase(), commonTypeVO.getId());
							}
							commonTypeVOList = null;
						}
						commonTypeMainVO = null;
					}
					int monthNum = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
					SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
					int year = Integer.valueOf(simpleDateformat.format(new Date()));
					int currentRow;
					ViewStaffPersonAccountDetails staffUniqueNum = null;
					ViewStaffPersonAccountDetails staffBiometricId = null;
					for (int sheetNo = 0; sheetNo < (workbook.getNumberOfSheets() - 1); sheetNo++) {
						sheet = workbook.getSheetAt(sheetNo);
						parser = new SheetParser();
						currentRow = 3;
						try {
							staffDetailsList = parser.createEntity(sheet, sheet.getSheetName(), StaffExcelRow.class);
							if (ObjectFunctions.isNotNullOrEmpty(staffDetailsList)) {
								for (StaffExcelRow staffDetail : staffDetailsList) {
									String existingStaffName="";
									User user =null;
									if (!ObjectFunctions.isNullOrEmpty(staffDetail)
											&& !ObjectFunctions.isNullOrEmpty(staffDetail.getAccount())
											&& StringFunctions.isNotNullOrEmpty(staffDetail.getRoleDescription())
											&& StringFunctions.isNotNullOrEmpty(staffDetail.getAccount().getPerson().getFirstName())) {
										log.debug("Staff Id : "+ staffDetail.getStaffIdStr());
										boolean smsStatus = false;
										role=rolesMap.get(staffDetail.getRoleDescription().toLowerCase()); 
										if (StringFunctions.isNullOrEmpty(staffDetail.getStaffIdStr())) {
											 smsStatus = true;
											log.debug("New staff generation");
											staff = new Staff();
											account = new User();
											person = new Person();
											address = new Address();
											/*Ganesh Now we change the requerment we will create static username or secondary username only once time when we create staff through admin. In edit option we are not going to give updation for username it should me one time process. If you referance check EAZ-1979 */
											newPassword = StringUtil.generateRandomString();
											/*Ganesh- If the user have we will create username with mobile nuber even respective user not have login access. If entered user don't have mobile number first we will check that person have login acccess if that person don't have login access we will create normal other will we show error to user.*/
											if(!StringFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson().getMobileNumber())){
												userName =staffDetail.getAccount().getPerson().getMobileNumber();
												if(userName.length() ==10 && userName.matches("[0-9]+")){
													user =staffUsernameAvailabulity(userName);
													if(!ObjectFunctions.isNullOrEmpty(user)){
														/*Ganesh If enter mobile number already exist with user we will check user if user is staff we will not allow to add user if the existing user is parent we will allow the user and add "S" before the username create the username even that username available with "S" also we will restrict user.*/
														if(user.isParent()){
															userName ="S"+userName;
															user =staffUsernameAvailabulity(userName);
															if(!ObjectFunctions.isNullOrEmpty(user)){
																duplicateNameRowNum.add(currentRow);
																currentRow++;
																continue;
															}else
																userExists=true;
														}else{
															duplicateNameRowNum.add(currentRow);
															currentRow++;
															continue;
														}
													}else
														userExists=true;
													if(!StringFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson().getInitial()))
														secondaryUserName=StringFunctions.stripSymbols(customer.getCustomerShortName().toLowerCase()+ staffDetail.getAccount().getPerson().getStudentFullName().toLowerCase()+ staffDetail.getAccount().getPerson().getInitial().toLowerCase());
													else
														secondaryUserName=StringFunctions.stripSymbols(customer.getCustomerShortName().toLowerCase()+ staffDetail.getAccount().getPerson().getStudentFullName().toLowerCase());
													//staffNumberExist = adminManager.getUserByUserName(secondaryUserName);
													boolean secUsernameExist=	staffSecondaryUsernameAvailabulity(secondaryUserName);
													if(!secUsernameExist){
														randamNumber=Math.round(Math.random()*10)+DateFormatter.formatDate(DateFormatter.HHMM_GMT_PATTERN, new Date());
														secondaryUserName=secondaryUserName+randamNumber;
														secUsernameExist = staffSecondaryUsernameAvailabulity(secondaryUserName);
													}
												}else{
													mobileNumberLength.append(currentRow).append(",");
													currentRow++;
													continue;
												}
											}else{
												if (Constants.SCHOOL_PRINCIPAL.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_VICEPRINCIPAL.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_TEACHER.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_ASST_STAFF.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_HOD.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_TRANSPORT.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_TRANSPORTFINANCE.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_FINANCE.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_LIBRARIAN.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_HOSTELFINANCE.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_HOSTEL.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_SEO.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_DEO.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_CEO.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_ROLE_DIRECTOR.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_BEO.equalsIgnoreCase(role.getName())
														|| Constants.SCHOOL_ROLE_STOREKEEPER.equalsIgnoreCase(role.getName()) || Constants.SCHOOL_ROLE_RECEPTIONIST.equalsIgnoreCase(role.getName())) {
													mobileNumberMandatory.append(currentRow).append(",");
													currentRow++;
													continue;
												}else{
													if(!StringFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson().getInitial()))
														secondaryUserName=StringFunctions.stripSymbols(customer.getCustomerShortName().toLowerCase()+ staffDetail.getAccount().getPerson().getStudentFullName().toLowerCase()+ staffDetail.getAccount().getPerson().getInitial().toLowerCase());
													else
														secondaryUserName=StringFunctions.stripSymbols(customer.getCustomerShortName().toLowerCase()+ staffDetail.getAccount().getPerson().getStudentFullName().toLowerCase());
													boolean secUsernameExist=	staffSecondaryUsernameAvailabulity(secondaryUserName);
													/*Ganesh - If static user name avilable we have to check two colums because we are maintaining two columns now username and secondary name. In secondary username we will add static username as well as we need add this user name in username column also because this user don't have mobile number and login access also. SO we need to check in both columns */
													if(!secUsernameExist){
														randamNumber=Math.round(Math.random()*10)+DateFormatter.formatDate(DateFormatter.HHMM_GMT_PATTERN, new Date());
														secondaryUserName=secondaryUserName+randamNumber;
														secUsernameExist =staffSecondaryUsernameAvailabulity(secondaryUserName);
														if(secUsernameExist){
															user = staffUsernameAvailabulity(secondaryUserName);
															if(ObjectFunctions.isNullOrEmpty(user)){
																userName =secondaryUserName;
																userExists=true;
															}
														}else{
															userName=secondaryUserName;
															userExists=true;
														}
													}else{
														userName=secondaryUserName;
														userExists=true;
													}
												}
											}
											if (!userExists) {
												//Staff creation process
												if(StringFunctions.isNullOrEmpty(staffDetail.getStaffIdStr())){
													log.debug("Mobilenumber already available :"+staffDetail.getAccount().getPerson().getMobileNumber());
													duplicateNameRowNum.add(currentRow);
													currentRow++;
													continue;
												}
												/*Ganesh -  In edit process we are not updateing username so below conditions not required*/
												/*else if(!account.getUsername().equals(secondaryUserName)){//Staff Edit process. If user changes firstName or lastName or initial, we have to update username of user.
													log.debug("User Name already available :"+staffDetail.getAccount().getPerson().getFirstName());
													duplicateNameRowNum.add(currentRow);
													currentRow++;
													continue;
												}*/else{
													staffDetail.getAccount().setUsername(userName);
													staffDetail.getAccount().setSecondaryUsername(secondaryUserName);
													staffDetail.getAccount().setPassword(PasswordUtils.passwordEncoder(newPassword,null));
												}
											} else {
												staffDetail.getAccount().setUsername(userName);
												staffDetail.getAccount().setSecondaryUsername(secondaryUserName);
												staffDetail.getAccount().setPassword(PasswordUtils.passwordEncoder(newPassword,null));
											}
											account.copyStaffExcelData(staffDetail.getAccount());
											salary = null;
											roleName = null;
										} else {
											staff = (Staff) staffManager.get(Staff.class, Long.valueOf(staffDetail.getStaffIdStr()));
											query = new StringBuffer("custId=").append(custId).append(" and id=").append(staff.getAccount().getId());
											//account = (User) staffManager.get(User.class, query.toString() );
											account = (User) staffManager.get(User.class, staff.getAccount().getId());
											person = (Person) staffManager.get(Person.class, account.getPerson().getId());
											existingStaffName=person.getFullPersonName();
											address = (Address) staffManager.get(Address.class, account.getPrimaryAddress().getId());
											query = new StringBuffer("staffId=").append(staffDetail.getStaffIdStr());
											salary=(Salary) staffManager.get(Salary.class,query.toString());
											roleName = account.getUserRoleDescription();
											//We cannot provide edit option for role change.
											if(StringFunctions.isNotNullOrEmpty(roleName) && !roleName.equals(staffDetail.getRoleDescription())){
												failedRecordRowNum.add(currentRow);
												continue;
											}
											//Siva: Updating the username with mobile number if the person is a driver role
											if(!ObjectFunctions.isNullOrEmpty(staff.getAccount()) && !ObjectFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson().getMobileNumber()) && !"N/A".equalsIgnoreCase(staffDetail.getAccount().getPerson().getMobileNumber()) && staffDetail.getAccount().getPerson().getMobileNumber().equalsIgnoreCase(staff.getAccount().getPerson().getMobileNumber()) && staffDetail.getAccount().getPerson().getMobileNumber().matches(moblieNumberDigitsOrNot)){
												if(staff.getAccount().isDriver()){
													User muser =staffUsernameAvailabulity(staffDetail.getAccount().getPerson().getMobileNumber());
													if(!ObjectFunctions.isNullOrEmpty(muser)){
														//super.addActionError("Mobile number already exists. Change mobile number for '"+staffDetail.getAccount().getPerson().getFirstName()+"'");
														duplicateNameRowNum.add(currentRow);
														currentRow++;
														continue;
													}
													else{
														account.setUsername(staffDetail.getAccount().getPerson().getMobileNumber());
														newPassword = StringUtil.generateRandomString();
														account.setPassword(PasswordUtils.passwordEncoder(newPassword,null));
														//account.getPerson().setMobileNumber(staffDetail.getAccount().getPerson().getMobileNumber());
														if(customer.isCheckMobileService()){
															staff.getAccount().setUsername(account.getUsername());
															staff.getAccount().getPerson().setMobileNumber(staffDetail.getAccount().getPerson().getMobileNumber());
															sendLogincredentailsToStaff(staff,customer,getCurrentAcademicYear(),newPassword);
														}
													}
												}
												else{ 
															query = new StringBuffer("mobileNumber=").append(staffDetail.getAccount().getPerson().getMobileNumber());
															person = (Person) staffManager.get(Person.class,query.toString());
															person=staff.getAccount().getPerson();
													}
												}
												else{
													mobileNumberLength.append(currentRow).append(",");
													currentRow++;
													continue;
												}
											}
										if(!ObjectFunctions.isNullOrEmpty(staffDetail.getAccount())){
											if (StringFunctions.isNullOrEmpty(staffDetail.getStaffIdStr())) {
												if (StringFunctions.isNotNullOrEmpty(staffDetail.getAccount().getStaffNumber())){
													query = new StringBuffer("custId=").append(custId).append(" and staffNumber='").append(staffDetail.getAccount().getStaffNumber().trim()).append("'");
													staffNumberExist = (User)staffManager.get(User.class,query.toString());
													if(!ObjectFunctions.isNullOrEmpty(staffNumberExist)){
														failedRecordRowNum.add(currentRow);//Subject number is already exist.
														staffDetail.getAccount().setStaffNumber(null);
													}
												}
											}else{
												if (StringFunctions.isNotNullOrEmpty(staffDetail.getAccount().getStaffNumber())){
													query = new StringBuffer("custId=").append(custId).append(" and staffNumber='").append(staffDetail.getAccount().getStaffNumber().trim()).append("' and id!=")
													.append(staff.getAccount().getId());
													staffNumberExist = (User)staffManager.get(User.class,query.toString());
													if(!ObjectFunctions.isNullOrEmpty(staffNumberExist)){
														failedRecordRowNum.add(currentRow);//Subject number is already exist.
														staffDetail.getAccount().setStaffNumber(null);
													}	
												}
											}
											if(!ObjectFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson())){
												//For checking religion
												if(StringFunctions.isNotNullOrEmpty(staffDetail.getAccount().getPerson().getReligionName()))
												{
													if(!ObjectFunctions.isNullOrEmpty(commonTypeMap))
													{
														if(!ObjectFunctions.isNullOrEmpty(commonTypeMap.get(staffDetail.getAccount().getPerson().getReligionName().trim().toUpperCase())))
														{
															staffDetail.getAccount().getPerson().setReligionId(commonTypeMap.get(staffDetail.getAccount().getPerson().getReligionName().trim().toUpperCase()));
														}
														else
														{
															religion = new CommonType();
															religion.setCustId(custId);
															religion.setSkillTypeName(staffDetail.getAccount().getPerson().getReligionName().trim().toUpperCase());
															religion.setType("RELIGION");
															//religion.setVersion(0);
															religion = (CommonType) staffManager.save(religion);
															staffDetail.getAccount().getPerson().setReligionId(religion.getId());
															commonTypeMap.put(religion.getSkillTypeName(), religion.getId());
															religion = null;
														}
													}
												}
												//For checking mothertounge
												if (!StringFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson().getMotherToungName())) 
												{
													if(!ObjectFunctions.isNullOrEmpty(motherTongueMap.get(staffDetail.getAccount().getPerson().getMotherToungName())))
													{
														//motherTongue = (MotherTongue) staffManager.get(MotherTongue.class,"name= '"+staffDetail.getAccount().getPerson().getMotherToungName()+"'");
														//if (!ObjectFunctions.isNullOrEmpty(motherTongue)) {
															staffDetail.getAccount().getPerson().setMotherToungId(motherTongueMap.get(staffDetail.getAccount().getPerson().getMotherToungName()));
													}
												}
												//For checking cast and subcase details
												if (StringFunctions.isNotNullOrEmpty(staffDetail.getAccount().getPerson().getCastName())) {
													castSettings = staffManager.getCastNames(staffDetail.getAccount().getPerson().getCastName().trim(),custId);
													if (ObjectFunctions.isNullOrEmpty(castSettings)) {
														castSettings=new CastSettings();
														castSettings.setCustId(custId);
														castSettings.setCastName(staffDetail.getAccount().getPerson().getCastName().toUpperCase().trim());
														castSettings.setCreatedById(getUser().getId());
														castSettings.setCreatedDate(new Date());
													}else
														castSettings.setLastUpdatedById(getUser().getId());
													if (!ObjectFunctions.isNullOrEmpty(castSettings)) {
														if (StringFunctions.isNotNullOrEmpty(staffDetail.getAccount().getPerson().getSubCastName())) {
															subCastSettings = adminManager.getSubCast(custId,staffDetail.getAccount().getPerson().getSubCastName().trim(),castSettings.getId());
															if (ObjectFunctions.isNullOrEmpty(subCastSettings)) {
																subCastSettings=new SubCastSettings();
																subCastSettings.setSubCastName(staffDetail.getAccount().getPerson().getSubCastName().toUpperCase().trim());
																subCastSettings.setCustId(custId);
																subCastSettings.setCreatedById(getUser().getId());
																subCastSettings.setCreatedDate(new Date());
															}else
																subCastSettings.setLastUpdatedById(getUser().getId());
															if(!ObjectFunctions.isNullOrEmpty(subCastSettings)){
																castSettings.addSubCast(subCastSettings);
															}
															castSettings.setLastAccessDate(new Date());
															castSettings.setLastUpdatedDate(new Date());
															subCastSettings.setLastAccessDate(new Date());
															subCastSettings.setLastUpdatedDate(new Date());
															if(castSettings.getId() == 0 || subCastSettings.getId() == 0 )
																castSettings=(CastSettings)staffManager.save(castSettings);
															if (!ObjectFunctions.isNullOrEmpty(subCastSettings)) {
																staffDetail.getAccount().getPerson().setCastId(castSettings.getId());
																staffDetail.getAccount().getPerson().setSubCastId(subCastSettings.getId());
															}
														}
													}
												}
											}
											if(!ObjectFunctions.isNullOrEmpty(statelist) && !ObjectFunctions.isNullOrEmpty(staffDetail.getAccount().getPrimaryAddress()) && !ObjectFunctions.isNullOrEmpty(staffDetail.getAccount().getPrimaryAddress().getState())){ 
												staffDetail.getAccount().getPrimaryAddress().setStateId(statelist.get(staffDetail.getAccount().getPrimaryAddress().getState()).getId());
												staffDetail.getAccount().getPrimaryAddress().setState(statelist.get(staffDetail.getAccount().getPrimaryAddress().getState()).getStateCode());
											}
										}
										person.copyStaffExcelDate(staffDetail.getAccount().getPerson());
										address.copyStaffExcelData(staffDetail.getAccount().getPrimaryAddress());
										
										
										
										if(!StringFunctions.isNullOrEmpty(staffDetail.getAccount().getBioMetricId()) && Long.valueOf(staffDetail.getAccount().getBioMetricId()) > 0){
											if (StringFunctions.isNotNullOrEmpty(staffDetail.getStaffIdStr())) {
												 staffBiometricId = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class,"bioMetricId="+staffDetail.getAccount().getBioMetricId()+" and custId="+custId+" and accountId not in ("+staff.getAccount().getId()+")");
											}else
												 staffBiometricId = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class,"bioMetricId="+staffDetail.getAccount().getBioMetricId()+" and custId="+custId);
											if(ObjectFunctions.isNullOrEmpty(staffBiometricId))
											{
												if(StringFunctions.isNotNullOrEmpty(staffDetail.getAccount().getBioMetricId()) && staffDetail.getAccount().getBioMetricId().matches("[0-9]+"))
													account.setBioMetricId(Integer.valueOf(staffDetail.getAccount().getBioMetricId()));
												else
													account.setBioMetricId(0);
											}else {
												duplicateStaffBiometricIdRowNum.add(currentRow);
											}
										}
										account.setCustId(custId);
										
										if(!ObjectFunctions.isNullOrEmpty(role))
										{
											account.addRole(role);
										}
										if(!StringFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson().getStaffUniqueNumber())){
											if (StringFunctions.isNotNullOrEmpty(staffDetail.getStaffIdStr())) {
												 staffUniqueNum = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class,"staffUniqueNumber='"+staffDetail.getAccount().getPerson().getStaffUniqueNumber()+"' and custId="+custId+" and accountId not in ("+staff.getAccount().getId()+")");
											}else
												 staffUniqueNum = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class,"staffUniqueNumber='"+staffDetail.getAccount().getPerson().getStaffUniqueNumber()+"' and custId="+custId);
												//Person personObj = (Person) adminManager.get(Person.class, "staffUniqueNumber='"+staffDetail.getAccount().getPerson().getStaffUniqueNumber()+"'");
											if(ObjectFunctions.isNullOrEmpty(staffUniqueNum))
											{
												person.setStaffUniqueNumber(staffDetail.getAccount().getPerson().getStaffUniqueNumber().trim());
											}else {
													duplicateStaffUniqueRowNum.add(currentRow);
											}
										}
										if(!StringFunctions.isNullOrEmpty(staffDetail.getAccount().getPerson().getGpfNumber())){
											if(staffDetail.getAccount().getPerson().getGpfNumber().length() >20){
												gpfNumberLength.append(currentRow).append(",");
											}
										}
										account.setPerson(person);
										account.setPrimaryAddress(address);
										staff.setQualification1(staffDetail.getQualification1());
										staff.setAcademicYear(academicYear);
										staff.setCustId(custId);
										staff.setStatus("Y");
										staff.setOrganizationTypeId(customer.getOrgnizationTypeId());
										staff.setAccount(account);
										staff.setLastUpdatedDate(new Date());
										if(!ObjectFunctions.isNullOrEmpty(staffDetail.getStaffTypeStatus()) && "Temporary".equalsIgnoreCase(staffDetail.getStaffTypeStatus())){
											staff.setStaffType("C");
										}else 
											staff.setStaffType("P");
											log.debug("Staff Type:"+staffDetail.getStaffTypeStatus());
										if(StringFunctions.isNotNullOrEmpty(staffDetail.getSalary()) && staffDetail.getSalary().length() <= 8 && Double.valueOf(staffDetail.getSalary()) > 0.0)
										{
											if(ObjectFunctions.isNullOrEmpty(salary)){
												salary = new Salary();
											    salary.setCreatedById(getUser().getId());
											}
											salary.setCustId(custId);
											salary.setLastUpdatedById(getUser().getId());
											salary.setLastAccessDate(new Date());
											salary.setSalary(Double.valueOf(staffDetail.getSalary()));
											salary.setMonth(monthNum);
											salary.setYear(year);
											staff.setSalaryDetails(null);
											staff.addSalaryDetailsSettings(salary);
											salary=null;
										}
										if (StringFunctions.isNullOrEmpty(staffDetail.getOutSideSchoolDuty())) {
											staff.setOutSideSchoolDuty(Constants.NO_STRING);
										}
										else
											staff.setOutSideSchoolDuty(staffDetail.getOutSideSchoolDuty());
										if(!StringFunctions.isNullOrEmpty(staffDetail.getSchoolMess()))
											staff.setSchoolMess(staffDetail.getSchoolMess());
										else
											staff.setSchoolMess("N");
										staff.setStaffGrade(staffDetail.getStaffGrade());
										try{
										Staff staffObj = (Staff)staffManager.save(staff);
										if(StringFunctions.isNotNullOrEmpty(existingStaffName)){
											communicationManager.sendStaffNotificationWhenEditOrAdd(staffObj.getId(), existingStaffName+" details are updated.","Staff details are updated.");
										}else{
											communicationManager.sendStaffNotificationWhenEditOrAdd(staffObj.getId(),"A new staff "+staffObj.getAccount().getPerson().getFullPersonName()+" added.","A new staff Added.");
										}
										existingStaffName="";
										if(smsStatus){
											if(customer.isCheckMobileService()){
												sendLogincredentailsToStaff(staffObj,customer,academicYear,newPassword);
											}
										}
										if (!ObjectFunctions.isNullOrEmpty(staffObj) && customer.getBarcodeStatus()=="Y" && StringFunctions.isNullOrEmpty(staffDetail.getStaffIdStr())) {
											adminManager.generateBarcode(staffObj.getAccount().getId());
											staffObj=null;
											//staff = null;
										}
										}
										catch(Exception e){
											e.printStackTrace();
										}
										dataInserted=true;
									}else{
										failedRecordRowNum.add(currentRow);
									}
									currentRow++;
								}			
							}else{
								super.addActionError("No data entered in the sheet.");
							}
						}catch(Exception ex){
							ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
						}
					}
					parser = null;
					staff = null;
					account = null;
					person = null;
					address=null;
					customer = null;
					query = null;
					roleName = null;
					staffNumberExist = null;
					religion  =null;
					castSettings=null;
					subCastSettings = null;
					academicYear = null;
					role =null;
					rolesMap = null;
					salary = null;
				}
				if(failedRecordRowNum.size() > 0){
					query = new StringBuffer("Following rows staff data not uploaded into system. Please check the data.").append(StringFunctions.convertListToCommaDelimitedString(failedRecordRowNum));
					super.addActionError(query.toString());
				}
				if(duplicateStaffBiometricIdRowNum.size() > 0){
					query = new StringBuffer("Following rows staff biometric Id already exists in the system. Please change the biometric Id for ").append(StringFunctions.convertListToCommaDelimitedString(duplicateStaffBiometricIdRowNum));
					super.addActionError(query.toString());
				}
				if(duplicateStaffUniqueRowNum.size() > 0){
					query = new StringBuffer("Following rows staff Unique Number already exists in the system. Please change the  staff Unique Number for ").append(StringFunctions.convertListToCommaDelimitedString(duplicateStaffUniqueRowNum));
					super.addActionError(query.toString());
				}
				if(duplicateNameRowNum.size() > 0){
					query = new StringBuffer("Following rows staff mobilenumber already exists in the system. Please change the mobile number for ").append(StringFunctions.convertListToCommaDelimitedString(duplicateNameRowNum));
					super.addActionError(query.toString());
				}if(staffDetailsList.size() > 0 && dataInserted){
					super.addActionMessage("You have successfully uploaded staff data.");
				}
				if(!StringFunctions.isNullOrEmpty(mobileNumberMandatory.toString())){
					query = new StringBuffer("Following rows mobilenumber are mandatory ").append(mobileNumberMandatory.toString());
					super.addActionError(query.toString());
				}
				if(!StringFunctions.isNullOrEmpty(mobileNumberLength.toString())){
					query = new StringBuffer("Please enter valid mobile number of 10 digits for the following rows ").append(mobileNumberLength.toString());
					super.addActionError(query.toString());
				}
				if(!StringFunctions.isNullOrEmpty(gpfNumberLength.toString())){
					query = new StringBuffer("Following rows gpfnumber are More than 20 digit ").append(gpfNumberLength.toString());
					super.addActionError(query.toString());
				}
				staffDetailsList =null;
				failedRecordRowNum = null;
				duplicateNameRowNum = null;
				
				if(StringFunctions.isNotNullOrEmpty(getPlTitle())) {
					if(getPlTitle().equalsIgnoreCase("Staff Details")){
						if(getUserCustId() > 0){
				        	setObjectList(staffManager.getAll(Staff.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'"));
						}
						return "staffDetails";
					}
				}
				else {
					prepareStaffRolesMap(null);
					setObjectList(staffManager.getAll(ViewAllUsers.class, "custId="+getUserCustId()+" and roleName!='ROLE_STUDENT' and roleName!='ROLE_PARENT' and roleName!='ROLE_ADMIN' and roleName!='ROLE_ADMINOFFICER' group by roleName"));
				}
				}else{
					log.debug("Uploaded wrong file..");
					super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
					//ajaxGetStudyClassList();
					return "staffImport";
				}
				
				date = new Date();
				log.debug(" ****************** End 'ajaxUploadStaffData' method *********************" + dateFormat.format(date));
				
			} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			if("createStaff".equals(getTempString()))
				return "staffImport";
			else
				return SUCCESS;
	    }
	
	
	@Actions({
		@Action(value = "ajaxEditStudentAssignment", results = {
				@Result(location = "../common/classAssignment/ajaxEditStudentAssignment.jsp", name = "success"),
				@Result(location = "AttendanceList.jsp", name = "Failure") }) })
public String ajaxEditStudentAssignment() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxEditStudentAssignment' method in Admin Action");
	}
	try {
		
		if (!StringFunctions.isNullOrEmpty(getAnyTitle())) {
			// this is for to find Add attendance or Update Attendance
			if (!StringFunctions.isNullOrEmpty(getParamValue("attendanceType"))) {
				log.debug(getParamValue("attendanceType"));
				setAttendanceType(getParamValue("attendanceType"));
			}
			addOrUpdateStudentAssignment();
			if(!ObjectFunctions.isNullOrEmpty(getClassId()))
			{ 
				Customer customer = getCustomerByCustId();
				setCustomer(customer);
				setEventId(getEventId());
				getStudentsClassAssignmentList();
			}
		}
		super.addActionMessage("Student Assignment is recorded successfully.");
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
}
	/* Upload / Download Documnets to Staff */
	 @Actions( { @Action(value = "ajaxDoUploadAndDownloadDocs", results = { @Result(location = "manageStaff/ajaxDoUploadAndDownloadDocs.jsp", name = "success"),
		 																	@Result(location = "manageStaff/ajaxClassWiseTimetableUpload.jsp", name = "classWiseTimetable")}),
		 																	
		 		@Action(value = "ajaxUploadTimetableDocuments", results = { @Result(location = "../admin/academic/timeTable/ajaxUploadTimetableDocumentsHome.jsp", name = "success")}),
		 		
		 		@Action(value = "ajaxClassWiseTimetable", results = { @Result(location = "../admin/academic/timeTable/ajaxClassWiseTimetableHome.jsp", name = "classWiseTimetable")})
		 	
	})
		public String ajaxDoUploadAndDownloadDocs() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoUploadAndDownloadDocs' method");
			}
			try {
				StringBuffer sqlQuery = null;
				if (getUserCustId() > 0) {
					//Customer customer = getCustomerByCustId();
					if(getTempString().equalsIgnoreCase("uploadTimetable")){
						
						sqlQuery = new StringBuffer("select  distinct(sd.staffId),sd.fullName,sd.mobileNumber,sd.roleDescription,sd.roleName,sd.qualification1,sd.accountId,sd.isTimetableUploaded,sd.custId  from vw_staffDetails sd LEFT JOIN classTeacher ct on (sd.staffId=ct.teacherId)") 
						.append(" where sd.custId=").append(getUserCustId()).append(" and sd.academicYearId<="+getUserAcademicYearId()+"").append(" and sd.status='"+Constants.YES_STRING+"'")
						//.append(" and roleId in(2,8,12)")
						/*Ganesh - Time table will come only teaching staff so we taken out un necessacery roles*/
						.append(" and roleId in (2,8,12,31,35)")//1,2,4,5,6,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,30,31,32,33,35
						.append(" order by roleName");
						//setObjectList(staffManager.getAll(sqlQuery.toString()));
						List<Object[]> staffList = staffManager.getAll(sqlQuery.toString());
						if(!ObjectFunctions.isNullOrEmpty(staffList))
						{
							Map<Long,String> staffTimetablePath = new HashMap<Long,String>();
							List<StaffTimetables> staffTimetablesList =adminManager.getAll(StaffTimetables.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(staffList))
							{
								for(StaffTimetables staffTimetables : staffTimetablesList)
								{
									staffTimetablePath.put(staffTimetables.getStaffId(), staffTimetables.getFilePath());
								}
							}
							staffTimetablesList = null;
							
							List<StaffTimetablesVO> staffTimeTableList = new ArrayList<StaffTimetablesVO>(); 
							for(Object[] staffObj : staffList)
							{
								StaffTimetablesVO staffTimetablesVO = new StaffTimetablesVO();
								staffTimetablesVO.setStaffName(staffObj[1].toString());
								if(!ObjectFunctions.isNullOrEmpty(staffObj[2]))
								{
									staffTimetablesVO.setStaffMobileNumber(staffObj[2].toString());
								}
								if(!ObjectFunctions.isNullOrEmpty(staffObj[3]))
								{
									staffTimetablesVO.setRoleName(staffObj[3].toString());
								}
								if(!ObjectFunctions.isNullOrEmpty(staffObj[5]))
								{
									staffTimetablesVO.setQualification(staffObj[5].toString());
								}
								if(!ObjectFunctions.isNullOrEmpty(staffTimetablePath.get(Long.valueOf(staffObj[0].toString()))))
								{
									staffTimetablesVO.setFilePath(staffTimetablePath.get(Long.valueOf(staffObj[0].toString())));
								}
								staffTimeTableList.add(staffTimetablesVO);
							}
							
							setObjectList(staffTimeTableList);
							staffTimeTableList = null;
						}
						
					}else if(getTempString().equalsIgnoreCase("uploadClassWiseTimetable")) {
						setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
						return "classWiseTimetable";
					}else{
						sqlQuery = new StringBuffer("select  distinct(sd.staffId),sd.firstName,sd.lastName,sd.mobileNumber,sd.roleDescription,sd.roleName,sd.qualification1,sd.accountId,sd.isDocsUploaded  from vw_staffDetails sd LEFT JOIN classTeacher ct on (sd.staffId=ct.teacherId)") 
						.append(" where sd.custId=").append(getUserCustId()).append(" and sd.academicYearId<="+getUserAcademicYearId()+"").append(" and sd.status='"+Constants.YES_STRING+"'").append(" order by roleName");
						
						log.debug(sqlQuery.toString());
						setTempString(getTempString());
						setObjectList(staffManager.getAll(sqlQuery.toString()));
					}
					
					//customer=null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 
	 @Actions( { @Action(value = "ajaxDoUploadDocuments", results = { @Result(location = "manageStaff/ajaxDoUploadAndDownloadDocs.jsp", name = "success") }) })
		public String ajaxDoUploadDocuments() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoUploadDocuments' method");
			}
			try {
				JSONObject formData = new JSONObject(getAnyTitle());
				JSONArray jsonArray =(JSONArray) formData.get("JSONOBJ");
				JSONObject jsonObj = null;
				long accountId=0;
				long classSectionId=0;
				Staff staff=null;
				JSONArray jsnArr =null;
				JSONArray jsnArr1 =null;
				int indexCount=0;
				String pathName="";
				String fileName="";
				// Uploading the Documents for staff.
				Customer customer = getCustomerByCustId();
				AcademicYear academicYear = getCurrentAcademicYear();
				if(!ObjectFunctions.isNullOrEmpty(jsonArray)){
				for (int i = 0; i < jsonArray.length(); i++) {
					jsnArr =(JSONArray) jsonArray.get(i);
					if (!ObjectFunctions.isNullOrEmpty(jsnArr)) {
						for (int j = 0; j < jsnArr.length(); j++) {
							jsonObj = jsnArr.getJSONObject(j);
							if (!ObjectFunctions.isNullOrEmpty(jsonObj)) {
								Iterator<String> iter = jsonObj.keys();
								 while (iter.hasNext()) {
								   String key = iter.next();
								   if(key.equalsIgnoreCase("ACCOUNTID")){
									   accountId=  Long.valueOf((String)jsonObj.get(key));							  
									 }
								   else if(key.equalsIgnoreCase("CLASSID")){
									   classSectionId=  Long.valueOf((String)jsonObj.get(key));
									 }
								   else if(key.equalsIgnoreCase("BROWSEURL")){
									   jsnArr1=(JSONArray) jsonObj.get(key);
									 }
								 }
								 if(accountId>0)
								 {
									User userObj=(User)  staffManager.get(User.class, " id = " + accountId + " and custId = " +customer.getId());
									if(!ObjectFunctions.isNullOrEmpty(userObj))
									{
										if(!ObjectFunctions.isNullOrEmpty(jsnArr1)){
											userObj.getPerson().setIsDocsUploaded(Constants.YES_STRING);
											for (int l = 0; l < jsnArr1.length(); l++) 
											{
												 File file = getFileUpload().get(indexCount);
												 if(getTempString().equalsIgnoreCase("uploadTimetable"))
													 fileName = accountId+".html";
												 else
													 fileName = getFileUploadFileName().get(indexCount);
												 
												 	String filePath = adminManager.getUploadDocuments(file, academicYear.getAcademicYear(), fileName);
									    			if(!StringFunctions.isNullOrEmpty(filePath))
									    			{
									    				PersonDocuments personDocuments = new PersonDocuments();
									    				personDocuments.setFileName(fileName);
									    				personDocuments.setCreatedById(getUser().getId());
									    				personDocuments.setFilePath(filePath);
									    				personDocuments.setAcademicYearId(academicYear.getId());
									    				userObj.addPersonDocuments(personDocuments);
									    			}
												 indexCount ++;
											}
											staffManager.save(userObj);
										 }
									}
									 accountId=0;
								}
							   jsnArr1=null;
							 }
						}
					}
				  }	 
				}
				 if(indexCount > 0){
					 super.addActionMessage("Documents uploaded successfully.");
				 }else{
					 super.addActionError("You have not uploaded any documents.");
				 }
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			ajaxDoUploadAndDownloadDocs();
			return SUCCESS;
		}
	   
	   @Actions( { @Action(value = "ajaxDownloadStaffDocs", results = {}) })
		public String ajaxDownloadMemberDocs() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDownloadStaffDocs' method");
			}
			try {
					 if(!ObjectFunctions.isNullOrEmpty(getTempId()) && !ObjectFunctions.isNullOrEmpty(getTempId1())){
						 String memberName = getAnyTitle();
						 Customer customer = getCustomerByCustId();
						 downloadStaffDocuments(customer,memberName,getTempId(),getTempId1());
					 }
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	   @Actions( { @Action(value = "ajaxDoGetUploadDownloadDocs", results = { @Result(location = "manageStaff/ajaxDoGetUploadDownload.jsp", name = "success") }) })
		public String ajaxDoGetUploadDownloadDocs() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetUploadDownloadDocs' method");
			}
			try {

				StringBuffer pathName =null;
				Attachment attachment =null;
				if (getTempId() != 0) { // This is the staffId to get the staff Obj
					setStaff((Staff) staffManager.get(Staff.class,getTempId()));
					if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
						Customer customer = getCustomerByCustId();
						pathName = new StringBuffer(generateUploadFilepath(customer,getStaff().getAccount().getPerson().getFirstName(),getStaff().getAccount().getId(),getStaff().getId()));
					    setUser(getStaff().getAccount());
					   if(StringFunctions.isNotNullOrEmpty(pathName.toString())){
				    	File destFile = new File(getSession().getServletContext().getRealPath(pathName.toString()));
				    	String[] children = destFile.list();
						List FileNamesList = new ArrayList();
				        if (children == null) {
				            // Either dir does not exist or is not a directory
				        	FileUtils.deleteDirectory(destFile);
				        } else {
				            for (int i=0; i<children.length; i++) {
				            	attachment = new Attachment();
				                // Get filename of file or directory
				                String filename = children[i];
				                attachment.setFileName(filename);
				                FileNamesList.add(attachment);
				            }
				        }
				    	setTempList(FileNamesList);	
				      }
					}
				}  
				attachment=null;
				
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	   
	   @Actions({
			@Action(value = "ajaxReadSMSEnquiriesDetails", results = { @Result(location = "../staff/ajaxReadSMSEnquiries.jsp", name = "success" )})
			})
			public String ajaxReadSMSEnquiriesDetails(){
			try {
					List allInquiredMessages = null;
					if( getUser().isSchoolAsstStaff() || getUser().isSchoolTeacher()){
							allInquiredMessages = staffManager.getAll(MessageEnquiryDetails.class, "custId="+getUserCustId()+" and createdById="+getUser().getId()+"  and inquiryStatus not in ('A','R') order by id DESC ");
							setMessageDetails(allInquiredMessages);
						}
					else {
							allInquiredMessages = staffManager.getAll(MessageEnquiryDetails.class, "custId="+getUserCustId()+" and inquiryStatus not in ('A','R') order by id DESC ");
							setMessageDetails(allInquiredMessages);
						}
					if(getUser().isSchoolAsstStaff() || getUser().isSchoolTeacher()){
						List<MessageEnquiryDetails> rejectedSMSList=staffManager.getAll(MessageEnquiryDetails.class, "custId="+getUserCustId()+" and createdById="+getUser().getId()+" and academicYearId="+getAcademicYearId()+" and inquiryStatus = 'R' order by id DESC");
							if(!ObjectFunctions.isNullOrEmpty(rejectedSMSList)){
								setRejectedDetails(rejectedSMSList);
							}
						List<Messages> approvedSMSList=staffManager.getAll(MessageEnquiryDetails.class, "custId="+getUserCustId()+" and createdById="+getUser().getId()+" and academicYearId="+getAcademicYearId()+" and inquiryStatus = 'A' order by id DESC");
							if(!ObjectFunctions.isNullOrEmpty(approvedSMSList)){
								setApprovedList(approvedSMSList);
							}
						}
				} catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
	   
	   @Actions({
			@Action(value = "ajaxReadSMSDeatils", results = { @Result(location = "../common/messages/ajaxEditSMSDetails.jsp", name = "success" )})
			})
			public String ajaxReadSMSDeatils(){
			try {
				List allMessages = null;
				if(getUser().isSchoolAdmin() || getUser().isSchoolPrincipal() || !ObjectFunctions.isNullOrEmpty(getTempId())) 
					{
						MessageEnquiryDetails	InquiryMessages = (MessageEnquiryDetails) staffManager.get(MessageEnquiryDetails.class,"id="+getTempId()+" order by id DESC");
						setDetails(InquiryMessages);
					}
				allMessages=null;
				} catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}  
		
		@Actions({
			@Action(value = "ajaxSendEnquiredMessages", results = { @Result(location = "../common/messages/sendSchoolWideMessagesList.jsp", name = "success" ),
																	@Result(location = "../staff/ajaxReadSMSEnquiries.jsp", name = "RejectedDetails" )})
			})
			public String ajaxSendEnquiredMessages(){
			try {
					MessageEnquiryDetails messageEnquiryDetails=null;
					Messages messageDetails=new Messages();
					if(StringFunctions.isNotNullOrEmpty(getRejectedStatus()))
					{
						messageEnquiryDetails=(MessageEnquiryDetails) staffManager.get(MessageEnquiryDetails.class,"Id="+getTempId());
						messageEnquiryDetails.setInquiryStatus("R");
						messageEnquiryDetails.setMessageDescription(getDetails().getMessageDescription());
						messageEnquiryDetails = (MessageEnquiryDetails) adminManager.save(messageEnquiryDetails);
						ajaxReadSMSEnquiriesDetails();
						super.addActionError("SMS has been Rejected Succussfully.");
						return "RejectedDetails";
					}
					else{
						messageEnquiryDetails=(MessageEnquiryDetails) staffManager.get(MessageEnquiryDetails.class,"Id="+getTempId());
						messageEnquiryDetails.setInquiryStatus("A");
						messageEnquiryDetails.setMessageDescription(getDetails().getMessageDescription());
						messageEnquiryDetails = (MessageEnquiryDetails) adminManager.save(messageEnquiryDetails);
						messageDetails.setMessageDate(messageDetails.getMessageDate());
						messageDetails.setMessageDescription(getDetails().getMessageDescription());
						messageDetails.setStatus("M");
						messageDetails.setReceiverType("P");
						setAcademicYear(adminManager.getCurrentAcademicYear("Y",getUserCustId()));
						messageDetails.setSenderName(getUser().getFullPersonName());
						getChkBoxSelectedIds().add("M");
						List<String> studentAccountIds = new ArrayList<String>(Arrays.asList((messageEnquiryDetails).getCheckBoxSelectedStudIds().split(" , ")));
						addActionMessages(communicationManager.sendSchoolWideMessages(messageDetails, getUserCustId(), getAcademicYear(), getUser(), getChkBoxSelectedIds(), studentAccountIds, getChkBoxClassSelectedIds(), getParamValue("trStatus"),null,getMobileNumbersSet(),getAnyTitle(),getWishTitle()));
						ajaxDoGetSchoolWideMessagesList();
					}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}  
	   @Actions( { @Action(value = "ajaxRemoveDocsDirectory", results = { @Result(location = "manageStaff/ajaxDoGetUploadDownload.jsp", name = "success") }) })
		public String ajaxRemoveDocsDirectory() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveDocsDirectory' method");
			}
			try {
				StringBuffer pathName = null;
				File directory=null;
				if(!ObjectFunctions.isNullOrEmpty(getTempId()) && !ObjectFunctions.isNullOrEmpty(getTempId1())){
					Customer customer = getCustomerByCustId();
					pathName = new StringBuffer(generateUploadFilepath(customer,getAnyTitle().toString(),getTempId(),getTempId1()));
				   if(!ObjectFunctions.isNullOrEmpty(pathName)){
					Boolean fileExists=false;
	        	    directory = new File(getSession().getServletContext().getRealPath(pathName.toString()));
	        	    if(!ObjectFunctions.isNullOrEmpty(directory)){
        	    	 if(!directory.exists()){
				           log.debug("Directory does not exist.");
				         //  System.exit(0);
				      }else{
				    	  if(directory.isDirectory()){
		        				if(directory.listFiles().length == 1){
		        					fileExists=true;
		        				}
		                	}
							if(!ObjectFunctions.isNullOrEmpty(pathName)){
								if(!StringFunctions.isNullOrEmpty(getFileName())){
									pathName.append(getFileName()+"/");
								}
								directory = new File(getSession().getServletContext().getRealPath(pathName.toString()));
						        	try{
						                ObjectFunctions.fileDelete(directory);
						                if(!StringFunctions.isNullOrEmpty(getTempString()) && !ObjectFunctions.isNullOrEmpty(getTempId())){
						                	if("DELETEALL".equalsIgnoreCase(getTempString()) || fileExists){
						                	  User user=(User) staffManager.get(User.class,getTempId());
						                	  Person person= user.getPerson();
						                	  person.setIsDocsUploaded(Constants.NO_STRING);
						                	  staffManager.save(person);
						                	  user=null;
						                	  person=null;
						                	}
						                	 super.addActionMessage("Documents deleted successfully.");
						                	 setTempId(getTempId1());
						                	 ajaxDoGetUploadDownloadDocs();
							             }
						            }catch(IOException ex){
						                ex.printStackTrace();
						                ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
						               // System.exit(0);
						            }
							   }  
				        }
					}
				}
			 }
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	   @Actions( { @Action(value = "ajaxUploadIndividualDocuments", results = { @Result(location = "manageStaff/ajaxDoGetUploadDownload.jsp", name = "success") }) })
		public String ajaxUploadIndividualDocuments() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxUploadIndividualDocuments' method");
			}
			try {
				StringBuffer pathName = null;
				if(!ObjectFunctions.isNullOrEmpty(getTempId()) && !ObjectFunctions.isNullOrEmpty(getTempId1())){
					Customer customer = getCustomerByCustId();
					pathName = new StringBuffer(generateUploadFilepath(customer,getAnyTitle().toString(),getTempId(),getTempId1()));
					
					if(getFileUpload().size()!=0){
						 for(int i=0;i<getFileUpload().size();i++){
							 if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i))){
				    		     File file = getFileUpload().get(i);
				    			 String fileName = getFileUploadFileName().get(i);
					    		 File destDir = new File(getSession().getServletContext().getRealPath(pathName+fileName));
								 FileUtils.copyFile(file, destDir);	 
				    		 }
				    	}
					User userUploadObj=(User)staffManager.get(User.class,getTempId());
					if(!ObjectFunctions.isNullOrEmpty(userUploadObj)){
						userUploadObj.getPerson().setIsDocsUploaded(Constants.YES_STRING);
						staffManager.save(userUploadObj);
						super.addActionMessage("Documents added successfully.");
						userUploadObj=null;
						setTempId(getTempId1());
						ajaxDoGetUploadDownloadDocs();
					 }
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
		@Actions( {@Action(value = "ajaxViewSubPlannerDetails", results = { @Result(location = "class/ajaxSubjectPlannerInformation.jsp", name = "success") })
	})
		public String ajaxViewSubPlannerDetails() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewSubPlannerDetails' method");
			}
			try {
				StudyClass staffStudyClass = null;
		 		//ClassTeacher staffClasses = null;
		 		if(getUser().getId() > 0 )
				{
					setStaff((Staff)staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'"));
				}
				if(getUser().isSchoolTeacher() || getUser().isSchoolAsstStaff())
				{
					if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
						HashSet<StudyClass> classSections = new HashSet<StudyClass>();
						if (getStaff().getId() > 0 && getUserAcademicYearId() > 0) {
							List<ClassTeacher> staffs = staffManager.getAll(ClassTeacher.class,"teacherId="+getStaff().getId()+" and academicYearId="+getUserAcademicYearId()+" group by studyClassId ");
							if(!ObjectFunctions.isNullOrEmpty(staffs)){
								for(ClassTeacher obj : staffs){
									//staffClasses = (ClassTeacher)obj;
									staffStudyClass = (StudyClass) staffManager.get(StudyClass.class, Long.valueOf(obj.getStudyClassId()));
									if(!ObjectFunctions.isNullOrEmpty(staffStudyClass)){
										classSections.add(staffStudyClass);
									}
								}
								if(getUser().isOnlySchoolHod()){
									List studyClassesList = getHodStudyClassesList(getStaff().getId(),getUserAcademicYearId());
									if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
									{
										classSections.addAll(studyClassesList);
									}
									studyClassesList = null;
								}
								if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
									setStudyClassList(ConvertUtil.convertSetToList(classSections));
									Collections.sort(getStudyClassList());	
								}
								classSections = null;
							}
						}
					}
				}else if (getUser().isSchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector()) {
					setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
				}
				//below lines are check the staff deal any subjects or not we are checking in page add subject planner option
				if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
					setTeacherList(staffManager.getAll(ClassTeacher.class,"teacherId="+getStaff().getId()+" and academicYearId="+getUserAcademicYearId()+" group by studyClassId "));
				}
				
			} catch (Exception ex) {
				log.error("Entering into 'catch block':" + ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}	
	@Actions( {@Action(value = "ajaxDoAddSubPlanner", results = { @Result(location = "class/ajaxCreateSubPlanner.jsp", name = "success")})
	 })
	 public String ajaxDoAddSubPlanner() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddAssignment' method");
			}
			try {
				setStaffSyllabusPlanner((StaffSyllabusPlanner) staffManager.get(StaffSyllabusPlanner.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getTempId2()));
				if(!ObjectFunctions.isNullOrEmpty(getUserAcademicYearId()))
				{ 
					if(getUser().isSchoolTeacher() || getUser().isAdminOrDelegate() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector() || getUser().isSchoolAsstStaff())
					{
						StudyClass staffStudyClass = null;
						if(getUser().getId() > 0 )
						{
							setStaff((Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'"));
						}
						List<ClassTeacher> staffs =null;
						if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
							if (getStaff().getId() > 0 && getUserAcademicYearId() > 0) {
								HashSet<StudyClass> classSections = new HashSet<StudyClass>();
								staffs = staffManager.getAll(ClassTeacher.class,"teacherId="+getStaff().getId()+" and academicYearId="+getUserAcademicYearId()+" group by studyClassId ");
								if(!ObjectFunctions.isNullOrEmpty(staffs)){
								for(ClassTeacher obj : staffs){
									//staffClasses = (ClassTeacher)obj;
									staffStudyClass = (StudyClass) staffManager.get(StudyClass.class, Long.valueOf(obj.getStudyClassId()));
									if(!ObjectFunctions.isNullOrEmpty(staffStudyClass)){
										classSections.add(staffStudyClass);
									}
								}
								if(getUser().isOnlySchoolHod()){
									List studyClassesList = getHodStudyClassesList(getStaff().getId(),getUserAcademicYearId());
									if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
									{
										classSections.addAll(studyClassesList);
									}
									studyClassesList = null;
								}
								if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
									setStudyClassList(ConvertUtil.convertSetToList(classSections));
									Collections.sort(getStudyClassList());
								}	
								classSections = null;
							}
						}
					 }
				   }
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	@Actions( {@Action(value = "ajaxAddSubPlanner", results = { @Result(location = "class/ajaxSubjectPlannerInformation.jsp", name = "success"),
			 													@Result(location = "class/ajaxSubjectPlannerViewDetails.jsp", name = "editStaffPlanner") 
	})})
	public String ajaxAddSubPlanner(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddSubPlanner' method");
		}
		try {	
			StaffSyllabusPlanner syllabusPlanner=null;
			Staff staff =null;
			staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
			if(getTempId2()>0){
				syllabusPlanner=(StaffSyllabusPlanner) staffManager.get(StaffSyllabusPlanner.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getTempId2());
			}else{
				syllabusPlanner = new StaffSyllabusPlanner();
				if(!ObjectFunctions.isNullOrEmpty(staff)){
					syllabusPlanner.setStaffId(staff.getId());
					staff=null;
				}
			}
				syllabusPlanner.setCustId(getUserCustId());
				syllabusPlanner.setStudySubjectId(getStaffSyllabusPlanner().getStudySubjectId());
				syllabusPlanner.setStudyClassId(getStaffSyllabusPlanner().getStudyClassId());
				syllabusPlanner.setAcademicYearId(getUserAcademicYearId());
				syllabusPlanner.setChapterName(getStaffSyllabusPlanner().getChapterName());
				syllabusPlanner.setTopicName(getStaffSyllabusPlanner().getTopicName());
				syllabusPlanner.setUnitName(getStaffSyllabusPlanner().getUnitName());
				syllabusPlanner.setStartDate(getStaffSyllabusPlanner().getStartDate());
				syllabusPlanner.setCompletedDate(getStaffSyllabusPlanner().getCompletedDate());
				syllabusPlanner.setPeriodsRequired(getStaffSyllabusPlanner().getPeriodsRequired());
				syllabusPlanner.setPeriodsTaken(getStaffSyllabusPlanner().getPeriodsTaken());
				setTempId(getStaffSyllabusPlanner().getStudyClassId());
				setTempId1(getStaffSyllabusPlanner().getStudySubjectId());
				staffManager.save(syllabusPlanner);
				setQuizId(getStaffSyllabusPlanner().getStudySubjectId());// here quizId used to when view page onload select the subject
				if(getTempId2()>0){
					super.addActionMessage("Subject planner updated successfully.");
					ajaxSubjectPlannerViewDetails();
					return "editStaffPlanner";
				}else{
					super.addActionMessage("Subject planner created successfully.");
					ajaxViewSubPlannerDetails();
				}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxGetClassPlannerSubject", results = { @Result(location = "class/ajaxGetClassSubjectForPlanner.jsp", name = "success") }) })
	public String ajaxGetClassPlannerSubject() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetClassPlannerSubject' method");
		}
		try {
			//this method call add planner and view planner when on change the class name
			List<ClassTeacher> classTeacherSubjectsList = null;
			List<ViewStaffSyllabusPlanner> classTeacherSubjectsLists = null;
			if(!ObjectFunctions.isNullOrEmpty(getStudent().getCategoryId())){
				StringBuffer studySubjectId= new StringBuffer("(");
				classTeacherSubjectsLists = staffManager.getAll(ViewStaffSyllabusPlanner.class,"studyClassId="+getStudent().getCategoryId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" group by studySubjectId ");
				for(ViewStaffSyllabusPlanner cteacher : classTeacherSubjectsLists){
					studySubjectId.append(cteacher.getStudySubjectId()+",");
				}	
				studySubjectId.append("0)");
				if(getUser().isSchoolTeacher() || getUser().isAdminOrDelegate() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector() || getUser().isSchoolAsstStaff()){
					Staff staff=null;	
					staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
					if (!ObjectFunctions.isNullOrEmpty(staff)) {
						classTeacherSubjectsList = staffManager.getAll(ClassTeacher.class,"studyClassId="+getStudent().getCategoryId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and teacherId="+staff.getId()+" group by studySubjectId "); //and studySubjectId not in "+studySubjectId.toString()+"
						setTempList1(staffManager.getAll(ClassTeacher.class,"studyClassId="+getStudent().getCategoryId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and teacherId="+staff.getId()+" and studySubjectId not in "+studySubjectId.toString()+" group by studySubjectId ")); 
					    setStaff(staff);
				    }
				  // below anyTitle value(ViewPlanner) come to the view page. Here show the all subject for principal,viceprincipal,hod and admin in view when change the class name 
				  if("ViewPlanner".equalsIgnoreCase(getAnyTitle()) && (getUser().isOnlySchoolHod() || getUser().isAdminOrDelegate() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector())){
					  classTeacherSubjectsList = staffManager.getAll(ClassTeacher.class,"studyClassId="+getStudent().getCategoryId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studySubjectId not in "+studySubjectId.toString()+" group by studySubjectId ");
				  }
				  
				  if (!ObjectFunctions.isNullOrEmpty(classTeacherSubjectsList)) 
					setTempList(classTeacherSubjectsList);
				  if (!ObjectFunctions.isNullOrEmpty(classTeacherSubjectsLists)) 
					setTempList2(classTeacherSubjectsLists);
				  staff=null;
			   }
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSubjectPlannerViewDetails", results = { @Result(location = "class/ajaxSubjectPlannerViewDetails.jsp", name = "success") }) })
	public String ajaxSubjectPlannerViewDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSubjectPlannerViewDetails' method");
		}
		try {
				if(getTempId() >0 && getTempId1() >0){
					if(getUser().isSchoolTeacher() || getUser().isSchoolAsstStaff()){
						if(!StringFunctions.isNullOrEmpty(getSelectedId()))
							setObjectList(staffManager.getAll(StaffSyllabusPlanner.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and staffId="+getSelectedId()+" and studyClassId="+getTempId()+" and studySubjectId="+getTempId1()));
							setSelectedId(getSelectedId()); //selectedId means staffId used in page
					}
					if(getUser().isSchoolPrincipal() || getUser().isAdminOrDelegate() || getUser().isSchoolDirector()){
						setObjectList(staffManager.getAll(StaffSyllabusPlanner.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getTempId()+" and studySubjectId="+getTempId1()));
					}
				}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxRemoveSubjectPlanner", results = { @Result(location = "class/ajaxSubjectPlannerViewDetails.jsp", name = "success") }) })
	public String ajaxRemoveSubjectPlanner() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveSubjectPlanner' method");
		}
		try {
			if(getTempId2() > 0) {
				staffManager.remove("syllabusPlannerComments", "staffSyllabusPlannerId="+getTempId2()+" and custId="+getUserCustId());
				staffManager.remove(StaffSyllabusPlanner.class, getTempId2());
				super.addActionMessage("Subject planner removed successfully.");
			}
			ajaxSubjectPlannerViewDetails();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoAddCommentsSyllebusPlanner", results = { @Result(location = "class/ajaxReplySyllabusComments.jsp", name = "success") }) })
	public String ajaxDoAddCommentsSyllebusPlanner() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddCommentsSyllebusPlanner' method");
		}
		try {
			if(getTempId() > 0) {
				setTempList2(staffManager.getAll(ViewSyllabusPlannerComments.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and staffSyllabusPlannerId="+getTempId2()));				 
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxAddCommentsSyllebusPlanner", results = { @Result(location = "class/ajaxSubjectPlannerViewDetails.jsp", name = "success") }) })
	public String ajaxDoAddCommentsPlanner() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddCommentsPlanner' method");
		}
		try {
			if(getTempId2() > 0) {
				 SyllabusPlannerComments comments = new SyllabusPlannerComments();
				 comments.setAcademicYearId(getUserAcademicYearId());
				 comments.setCustId(getUserCustId());
				 comments.setCommentsDate(new Date());
				 comments.setReceiverAccountId(getUser().getId());
				 comments.setStatus(Constants.UNIT_TEST);//here UNIT_TEST means Un reading messags
				 comments.setStaffSyllabusPlannerId(getTempId2());
				 comments.setMessageContent(getSyllabusPlannerComments().getMessageContent());
				 staffManager.save(comments);
				 setSelectedId(getSelectedId());
				 super.addActionMessage("Comments added successfully.");
				 ajaxSubjectPlannerViewDetails();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxViewMessageHistory", results = { @Result(location = "../common/messages/sendSchoolWideMessagesList.jsp", name = "success"),
		         @Result(location = "../common/messages/sendClassWideMessagesList.jsp", name = "classTeacher")
		})})
	public String ajaxViewMessageHistory() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMessageHistory' method");
		}
		try {
			if (getUserAcademicYearId() != 0) {
				String fromDate = null;
				String enddateDate = null;
				List schoolWideMessagesList = null;
				fromDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,getParamValue("startDate"));
				enddateDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, getParamValue("endDate"));
				Date endDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, enddateDate);
			    Date oneDaytoendDate = DateFunctions.add(endDate, 1);
			    if(!StringFunctions.isNullOrEmpty(getPlTitle())){
			    	schoolWideMessagesList = staffManager.getAll(Messages.class,"createdById="+ getUser().getId()+ " and status='"+getPlTitle()+"' and academicYearId="+ getUserAcademicYearId()+ " and messageDate between '"+ fromDate+"' and '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,oneDaytoendDate) +"' order by  createdDate DESC");
			    	if (!ObjectFunctions.isNullOrEmpty(schoolWideMessagesList)) {
						setNoticeBoardMessagesList(schoolWideMessagesList);
					}
			    	return "classTeacher";
			    }
			    else if(!StringFunctions.isNullOrEmpty(getAnyTitle())){
			    	schoolWideMessagesList = staffManager.getAll(Messages.class,"custId="+ getUserCustId()+ " and status='"+getAnyTitle()+"' and academicYearId="+ getUserAcademicYearId()+ " and messageDate between '"+ fromDate+"' and '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,oneDaytoendDate) +"' order by  createdDate DESC");
			    }
			    else if(getUser().isSchoolAsstStaff()){
			    	schoolWideMessagesList = staffManager.getAll(Messages.class,"custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and createdById="+ getUser().getId()+ " and messageDate between '"+ fromDate+"' and '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,oneDaytoendDate) +"' order by  createdDate DESC");
			    }
			    else{
				  schoolWideMessagesList = staffManager.getAll(Messages.class,"custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and messageDate between '"+ fromDate+"' and '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,oneDaytoendDate) +"' order by  createdDate DESC");
				}
				if (!ObjectFunctions.isNullOrEmpty(schoolWideMessagesList)) {
					setNoticeBoardMessagesList(schoolWideMessagesList);
				}
				schoolWideMessagesList = null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions({
   		@Action(value = "clerkStaffDashboard", results = { @Result(location = "clerkDashboard.jsp", name = "success" )})
   	})
   		public String clerkStaffDashboard() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'clerkStaffDashboard' method");
   		}
   		try {	
   			setTempBoolean(adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId()));
   			setAcademicYear(getCurrentAcademicYear());
   			String todayDate = DateFormatter.formatDate (DateFormatter.CCYY_MM_DD_PATTERN, new Date());
			prepareNotifications(todayDate,getAcademicYear());
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showTaskReminder")))
				getTaskReminderToUserLogin();
   			//setClassTeacher(staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId()));
   		}
   		catch(Exception ex)
   		{
   			log.error("Entering into 'catch block':"+ex.getMessage());
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
	@Actions({
   		@Action(value = "ajaxDoEditStaffHistory", results = { @Result(location = "manageStaff/ajaxEditStaffHistory.jsp", name = "success" )})
   	})
   		public String ajaxDoEditStaffHistory() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxDoEditStaffHistory' method");
   		}
   		try {	
   			setTempId(getTempId()); //this is staff account Id
   			if(getTempId() > 0 && getTempId1() == 0)
   			{
   				setNewUser((User) adminManager.get(User.class, " custId="+getUserCustId()+" and id="+getTempId()));
   			}
   			else if(getTempId1() > 0)
   			{
   				StaffHistoryVO staffHistoryVo=null;
   				StaffHistory staffHistory = (StaffHistory) adminManager.get(StaffHistory.class, " custId="+getUserCustId()+" and id="+getTempId1());
   				if(!ObjectFunctions.isNullOrEmpty(staffHistory))
				{
   					staffHistoryVo = staffHistory.copyFromEntityToVo(staffHistory);
   					setStaffHistoryVo(staffHistoryVo);
   					setTempId1(getTempId1()); //this is staff account Id
				}
   			}
   		}
   		catch(Exception ex)
   		{
   			log.error("Entering into 'catch block':"+ex.getMessage());
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
	 @Actions( { @Action(value = "ajaxEditStaffHistory", results = { @Result(location = "manageStaff/ajaxEditStaffHistory.jsp", name = "success") }) })
		public String ajaxEditStaffHistory() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxEditStaffHistory' method");
			}
			try {
				if(getTempId() > 0  && !ObjectFunctions.isNullOrEmpty(getStaffHistoryVo())){
					User account = (User) adminManager.get(User.class, " custId="+getUserCustId()+" and id="+getTempId());
					if(!ObjectFunctions.isNullOrEmpty(account) && getTempId1() == 0)
					{
						StaffHistory staffHistory=new StaffHistory();
						getStaffHistoryVo().setCreatedById(getUser().getId());
						getStaffHistoryVo().setCreatedDate(new Date());
						getStaffHistoryVo().setLastAccessDate(new Date());
						getStaffHistoryVo().setLastUpdatedDate(new Date());
						getStaffHistoryVo().setLastUpdatedById(getUser().getId());
						getStaffHistoryVo().setCustId(getUserCustId());
						getStaffHistoryVo().setSchoolName(getStaffHistoryVo().getSchoolName());
						getStaffHistoryVo().setSalary(getStaffHistoryVo().getSalary());
						getStaffHistoryVo().setStartDate(getStaffHistoryVo().getStartDate());
						getStaffHistoryVo().setEndDate(getStaffHistoryVo().getEndDate());
						getStaffHistoryVo().setOtherExperience(getStaffHistoryVo().getOtherExperience());
						if(!ObjectFunctions.isNullOrEmpty(getStaffHistoryVo().getStartDate()) && !ObjectFunctions.isNullOrEmpty(getStaffHistoryVo().getEndDate()))
							getStaffHistoryVo().setExperience(Double.valueOf(DateFunctions.calculateExperience(getStaffHistoryVo().getStartDate(), getStaffHistoryVo().getEndDate())));
						else
							getStaffHistoryVo().setExperience(0);
						if (!ObjectFunctions.isNullOrEmpty(getStaffHistoryVo())) 
						{
							staffHistory = staffHistory.copyFromVoToEntity(staffHistory, getStaffHistoryVo());
							account.addStaffHistory(staffHistory);
							super.addActionMessage("Staff history added successfully.");
						}
					}
					else if(getTempId1() > 0)
		   			{
		   				StaffHistory staffHistory = (StaffHistory) adminManager.get(StaffHistory.class, "custId="+getUserCustId()+" and id="+getTempId1());
		   				if(!ObjectFunctions.isNullOrEmpty(account) && !ObjectFunctions.isNullOrEmpty(staffHistory) && !ObjectFunctions.isNullOrEmpty(getStaffHistoryVo()))
						{
		   					staffHistory.setSchoolName(getStaffHistoryVo().getSchoolName());
		   					staffHistory.setSalary(getStaffHistoryVo().getSalary());
		   					staffHistory.setStartDate(getStaffHistoryVo().getStartDate());
		   					staffHistory.setEndDate(getStaffHistoryVo().getEndDate());
		   					staffHistory.setOtherExperience(getStaffHistoryVo().getOtherExperience());
		   					if(!ObjectFunctions.isNullOrEmpty(getStaffHistoryVo().getStartDate()) && !ObjectFunctions.isNullOrEmpty(getStaffHistoryVo().getEndDate()))
		   						staffHistory.setExperience(Double.valueOf(DateFunctions.calculateExperience(getStaffHistoryVo().getStartDate(), getStaffHistoryVo().getEndDate())));
		   					else
		   						staffHistory.setExperience(0);
		   					staffManager.save(staffHistory);
							super.addActionMessage("Staff history updated successfully.");
						}
		   			}
					setNewUser((User) adminManager.save(account));
					setTempId(account.getId());
					setStaffHistoryVo(null);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 @Actions({
	   		@Action(value = "ajaxViewStaffCurricularActivities", results = { @Result(location = "manageStaff/ajaxViewStaffCurricularActivities.jsp", name = "success" )})
	   	})
	   		public String ajaxViewStaffCurricularActivities() throws URTUniversalException {
	   		if (log.isDebugEnabled()) {
	   			log.debug("Entering 'ajaxViewStaffCurricularActivities' method");
	   		}
	   		try {	
	   			List<ActivityType> activityTypeList = adminManager.getAll(ActivityType.class);
				Map<Long,String> activityTypeMap = new HashMap<Long, String>();
				if(getUser().isSchoolTeacher())
				{
					if (!ObjectFunctions.isNullOrEmpty(activityTypeList)) 
					{
						setObjectList(activityTypeList);
						for(ActivityType activityType : activityTypeList)
						{
							activityTypeMap.put(activityType.getId(), activityType.getActivityTypeName());
						}
						User account = (User)staffManager.get(User.class, getUser().getId());
						List<StaffCurricularActivities> curriculamActivitiesList = account.getStaffCurricularActivities();
						if (!ObjectFunctions.isNullOrEmpty(curriculamActivitiesList)) 
						{
							for(StaffCurricularActivities staffCurricularActivities : curriculamActivitiesList)
							{
								if(!StringFunctions.isNullOrEmpty(activityTypeMap.get(staffCurricularActivities.getActivityTypeId())))
								{
									staffCurricularActivities.setActivityTypeName(activityTypeMap.get(staffCurricularActivities.getActivityTypeId()));
								}
								getTempList().add(staffCurricularActivities);
							}
						}
						
					}
				}
	   		}
	   		catch(Exception ex)
	   		{
	   			log.error("Entering into 'catch block':"+ex.getMessage());
	   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	   		}
	   		return SUCCESS;
	   	}
	 
	 @Actions( { @Action(value = "ajaxAddStaffCurricularActivities", results = { @Result(location = "manageStaff/ajaxViewStaffCurricularActivities.jsp", name = "success") }) })
		public String ajaxAddStaffCurricularActivities() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddStaffCurricularActivities' method");
			}
			try {
				StringBuffer pathName = null;
				if(getUser().isSchoolTeacher())
				{
					if(!ObjectFunctions.isNullOrEmpty(getStaffCurricularActivities()))
					{
						Staff staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
						User userUploadObj=staff.getAccount();
						
						if(!ObjectFunctions.isNullOrEmpty(staff) && !ObjectFunctions.isNullOrEmpty(userUploadObj))
						{
							Customer customer = getCustomerByCustId();
							pathName = new StringBuffer(generateStaffCurricularUploadFilepath(customer,userUploadObj.getPerson().getFirstName().replaceAll(" ", "_"),userUploadObj.getId(),staff.getId(),getStaffCurricularActivities()));
							log.debug(pathName);
							if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(0))){
								if(getFileUpload().size()!=0){
									 for(int i=0;i<getFileUpload().size();i++){
										 if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i))){
							    		     File file = getFileUpload().get(i);
							    			 String fileName = getFileUploadFileName().get(i);
								    		 File destDir = new File(getSession().getServletContext().getRealPath(pathName+fileName));
											 FileUtils.copyFile(file, destDir);	 
							    		 }
							    	}
									 getStaffCurricularActivities().setIsDocsUploaded(Constants.YES_STRING);
								}
							}else{
								 getStaffCurricularActivities().setIsDocsUploaded(Constants.NO_STRING);
							}
							getStaffCurricularActivities().setCreatedById(getUser().getId());
							getStaffCurricularActivities().setCreatedDate(new Date());
							getStaffCurricularActivities().setLastAccessDate(new Date());
							getStaffCurricularActivities().setLastUpdatedDate(new Date());
							getStaffCurricularActivities().setLastUpdatedById(getUser().getId());
							getStaffCurricularActivities().setCustId(getUserCustId());
							
							userUploadObj.addStaffCurricularActivities(getStaffCurricularActivities());
							staffManager.save(userUploadObj);
							userUploadObj=null;
							setStaffCurricularActivities(null);
							setTempId(staff.getId());
							
							staff=null;
						}
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			ajaxViewStaffCurricularActivities();
			return SUCCESS;
		}
	 
	 @Actions( { @Action(value = "ajaxDownloadStaffCurricularDocs", results = {}) })
		public String ajaxDownloadStaffCurricularDocs() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDownloadStaffCurricularDocs' method");
			}
			try {
				 if(!ObjectFunctions.isNullOrEmpty(getTempId()) && !ObjectFunctions.isNullOrEmpty(getTempId1())){
					 String memberName = getAnyTitle();
					 Customer customer = getCustomerByCustId();
					 Staff staff = (Staff) staffManager.get(Staff.class, " accountId = " + getTempId() + " and status = '" + Constants.YES_STRING+"'");
					 StaffCurricularActivities staffCurricularActivities = (StaffCurricularActivities) staffManager.get(StaffCurricularActivities.class, getTempId1());
					 if(!ObjectFunctions.isNullOrEmpty(staff) && !ObjectFunctions.isNullOrEmpty(staffCurricularActivities))
					 {
						 downloadStaffCurricularDocuments(customer,memberName,staff,staffCurricularActivities);
					 }
					 staff = null;
					 staffCurricularActivities = null;
				 }
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	 
	 @Actions({ @Action(value = "ajaxDoSendSms", results = { @Result(location = "popupSendSms.jsp", name = "success") }) })
		public String ajaxDoSendSms() throws URTUniversalException{
		if(log.isDebugEnabled()){
			log.debug("Entering 'ajaxDoSendSms' method");
		}
		try {
			
			setTempString(getTempString()); //here tempString is a paremtEmail,getTempId is accountId and getTempId1 is a studyClassIs
			setTempId(getTempId());
			setTempId1(getTempId1());
			setAnyTitle(getAnyTitle());
			String studName = getParamValue("stuFirstName");
			setAnyId(studName);
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		return SUCCESS;
	}
	 @Actions({ @Action(value = "ajaxSendSmsToParent", results = { @Result(location = "viewMyStudentsLists.jsp", name = "success") }) })
		public String ajaxSendSmsToParent() throws URTUniversalException{
		
		if(log.isDebugEnabled()){
			log.debug("Entering 'ajaxSendSmsToParent' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getTempString()) && !ObjectFunctions.isNullOrEmpty(getMessages())) {
				ViewAllUsers viewAllUsers = (ViewAllUsers)staffManager.get(ViewAllUsers.class,getTempId(),"accountId");
				if(!ObjectFunctions.isNullOrEmpty(viewAllUsers))
				{
					if(Constants.SCHOOL_STUDENT.equalsIgnoreCase(viewAllUsers.getRoleName()) || Constants.SCHOOL_PARENT.equalsIgnoreCase(viewAllUsers.getRoleName()))
					{
						List<String> studentAccountId =  new ArrayList<String>();						
						List<String> selectedIds =  new ArrayList<String>();		 
						AcademicYear academicYear = null;
						getMessages().setStatus("M");
						getMessages().setReceiverType("D");
						getMobileNumbersSet().add(getTempString());
						if(!ObjectFunctions.isNullOrEmpty(getTempId()))
							studentAccountId.add(String.valueOf(getTempId()));
						academicYear = (AcademicYear) staffManager.get(AcademicYear.class,getUserAcademicYearId());
						selectedIds.add("M");
						addActionMessages(communicationManager.sendSchoolWideMessages(getMessages(), getUserCustId(), academicYear, getUser(), selectedIds, studentAccountId, null,null,null,getMobileNumbersSet(),null,null));
					}
					viewAllUsers=null;
				}
			}
		}
		catch(Exception err) {
			err.printStackTrace();
		}
		ajaxFindStudentUsingNameOrId();
		return SUCCESS;
	}
	 @Actions( { @Action(value = "ajaxViewSchoolShiftInfo", results = { @Result(location = "manageStaff/ajaxViewSchoolShiftHomePage.jsp", name = "success") })})
	    public String ajaxViewSchoolShiftInfo() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxViewSchoolShiftInfo' method");
		}
			try {
				setTempList(staffManager.getAll(SchoolShiftInfo.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
				} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		return SUCCESS;
	    }
	 @Actions( { @Action(value = "ajaxDoManageSchoolShiftInfo", results = { @Result(location = "manageStaff/ajaxDoCreateSchoolShiftForm.jsp", name = "success") }) })
	    public String ajaxDoManageSchoolShiftInfo() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDoManageSchoolShiftInfo' method");
		}
			try {
				if(getSchoolShiftInfo().getId()>0){
					setSchoolShiftInfo((SchoolShiftInfo) staffManager.get(SchoolShiftInfo.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getSchoolShiftInfo().getId()));
				}else{
					setSchoolShiftInfo(null);
				}
				getAnyTitle();
				ajaxDoSendMarksToMobile();
			} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		return SUCCESS;
	    }
	 @Actions( { @Action(value = "ajaxAddSchoolShiftInfo", results = { @Result(location = "manageStaff/ajaxViewSchoolShiftHomePage.jsp", name = "success") }) })
	    public String ajaxAddSchoolShiftInfo() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxAddSchoolShiftInfo' method");
		}
			try {
				SchoolShiftInfo schoolShiftInfo=null;
				if(getSchoolShiftInfo().getId()>0){
					schoolShiftInfo=(SchoolShiftInfo) staffManager.get(SchoolShiftInfo.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getSchoolShiftInfo().getId());
					super.addActionMessage("Staff batch info details updated successfully.");
				}else{
					schoolShiftInfo=new SchoolShiftInfo();
					schoolShiftInfo.setAcademicYearId(getUserAcademicYearId());
					schoolShiftInfo.setCustId(getUserCustId());
					super.addActionMessage("School shift info details cretaed successfully.");
				}
				schoolShiftInfo.setShiftName(getSchoolShiftInfo().getShiftName());
				schoolShiftInfo.setStartTime(getSchoolShiftInfo().getStartTime());
				schoolShiftInfo.setEndTime(getSchoolShiftInfo().getEndTime());
				schoolShiftInfo.setIsStaffOrNonStaff(getTempString());
				if(StringFunctions.isNotNullOrEmpty(getEmpId().toString())){
					String staAccountIds[]=getEmpId().split(",");
					for(String staAccountId:staAccountIds){
						if(Long.valueOf(staAccountId)!=0){
							Staff staff=(Staff) adminManager.get(Staff.class,"accountId="+Long.valueOf(staAccountId));
							   if(!ObjectFunctions.isNullOrEmpty(staff))
								schoolShiftInfo.addSchoolShiftInfo(staff);
							   staff=null;
						}
					}
				}
				staffManager.save(schoolShiftInfo);
				schoolShiftInfo=null;
				ajaxViewSchoolShiftInfo();
			} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		return SUCCESS;
	    }
	 @Actions({
			@Action(value = "ajaxRemoveShiftInfor", results = { @Result(location = "manageStaff/ajaxViewSchoolShiftHomePage.jsp", name = "success")})
		})
		public String ajaxRemoveShiftInfor() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveShiftInfor' method");
			}
			try{
				if(getTempId()>0){
					SchoolShiftInfo schoolShiftObj=(SchoolShiftInfo) staffManager.get(SchoolShiftInfo.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getTempId());
					if(ObjectFunctions.isNotNullOrEmpty(schoolShiftObj.getStaffShiftInfo())){
						schoolShiftObj.setStaffShiftInfo(null);
						staffManager.save(schoolShiftObj);
					}staffManager.remove(SchoolShiftInfo.class,schoolShiftObj.getId());
					schoolShiftObj=null;
				}
				ajaxViewSchoolShiftInfo();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
	 @Actions({
	   		@Action(value = "chairManDashboard", results = { @Result(location = "chairManHome.jsp", name = "success" )})
	   	})
	   		public String chairManDashboard() throws URTUniversalException {
	   		if (log.isDebugEnabled()) {
	   			log.debug("Entering 'chairManDashboard' method");
	   		}
	   		try {	
				Object[] staffCount = staffManager.get("select IFNULL(count(*),0) as staffcount,id from staff where custId="+getUserCustId()+" and academicYearId<="+ getCurrentAcademicYear().getId()+" and status ='Y'");
				Object[] studentCount = staffManager.get("select IFNULL(count(*),0) as studentcount,id from student where custId="+getUserCustId()+" and academicYearId="+ getCurrentAcademicYear().getId()+" and description is null");
				setTempId(Long.valueOf(staffCount[0].toString()));
				setTempId1(Long.valueOf(studentCount[0].toString()));
				getAdmissionsOnlineApplicationDetails();
				Object[] totalAmount = staffManager.get("select IFNULL(sum(feeAmount),0),schoolTermId from vw_studentClassFees where custId="+getUserCustId()+" and academicYearId="+ getCurrentAcademicYear().getId()+" order by schoolTermId");
				Object[] paidAmount = staffManager.get("select IFNULL(sum(paymentAmount),0),IFNULL(sum(discountAmount),0) from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+ getCurrentAcademicYear().getId()+" and deletestatus='N' and status='Y'; ");
				setTotalAmount(Double.valueOf(totalAmount[0].toString()));
				setPaymentAmount(Double.valueOf(paidAmount[0].toString()));
				setObjectList(staffManager.getAll(AcademicYear.class,"custId="+getUserCustId()+" and id <="+getCurrentAcademicYear().getId()+" order by id DESC"));
				Object[] expencesses = staffManager.get("select IFNULL(sum(amount),0),id from daybook where custId="+getUserCustId()+" and academicYearId="+ getCurrentAcademicYear().getId());
				setPayLeaves(Double.valueOf(expencesses[0].toString()));
	   		}
	   		catch(Exception ex)
	   		{
	   			log.error("Entering into 'catch block':"+ex.getMessage());
	   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	   		}
	   		return SUCCESS;
	   	}
	 @Actions( { @Action(value = "ajaxGetApplicationsDetails", results = { @Result(location = "ajaxGetAdmissionDetailsForChairMan.jsp", name = "success") }) })
		public String ajaxApplyAdmission() {
			try {
				if (getAcademicYearId() > 0) {
					Object[] totalApplications = staffManager.get("select IFNULL(count(*),0) as totalApplications,id from onlineApplicationDetails where custId="+getUserCustId()+" and academicYearId="+getAcademicYearId());
					Object[] admittedStudentsApplications = staffManager.get("select IFNULL(count(*),0) as admittedStudentsApplications,custId from vw_studentClassDetails where custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and joinedThroughAdmissions='"+ Constants.YES_STRING+ "'");
					setBankId(Long.valueOf(totalApplications[0].toString()));
					setQuizId(Long.valueOf(admittedStudentsApplications[0].toString()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 @Actions( { @Action(value = "ajaxDoGetStaffAndStudenTLeaveReq", results = { @Result(location = "leaves/staffLeavesList.jsp", name = "success") }) })
		public String ajaxDoGetStaffAndStudenTLeaveReq() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetStaffAndStudenTLeaveReq' method");
			}
			try{
				ClassTeacher classTeacher=staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
					setClassTeacher(classTeacher);
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}finally{
				ajaxViewStudentAndStaffLeaves();
			}
			return SUCCESS;
		}
	 @Actions( { @Action(value = "ajaxShowStaffProfileDetails", results = { @Result(location = "../admin/staff/ajaxPopUpStaffProfileDetails.jsp", name = "success") }) })
	    public String ajaxShowStaffProfileDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxShowStaffProfileDetails' method");
		}
		try {
			    ViewStaffPersonAccountDetails staffProfileDetails = (ViewStaffPersonAccountDetails)studentManager.get(ViewStaffPersonAccountDetails.class,"staffId="+getTempId()+" and custId="+getUserCustId());
			    if(!ObjectFunctions.isNullOrEmpty(staffProfileDetails))
			    	setViewStaffPersonAccountDetails(staffProfileDetails);
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
 }
	 @Actions( { @Action(value = "ajaxDoAddStaffPermissions", results = { @Result(location = "permissions/ajaxDoAddStaffPermissionRequest.jsp", name = "success") }) })
	    public String ajaxDoAddStaffPermissions() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDoAddStaffPermissions' method");
		}
		try {
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
     }
	 @Actions( { @Action(value = "ajaxGetStaffPermissionsByDate", results = { @Result(location = "permissions/ajaxAddStaffPermissionRequest.jsp", name = "success") }),
		         @Action(value = "ajaxDoPermisssionApproveOrReject", results = { @Result(location = "permissions/ajaxPopupStaffApproveOrReject.jsp", name = "success") })
	 })
	    public String ajaxGetStaffPermissionsByDate() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxGetStaffPermissionsByDate' method");
		}
		try {
			List<BigInteger> monthPermissionIds = null;
			List<BigInteger> yearPermissionIds = null;
			Staff staff =null;
			if(getUser().isSchoolPrincipal() || getUser().isSchoolDirector()){
				  staff = (Staff) staffManager.get(Staff.class, " id= "+getTempId1()+" and status = '" + Constants.YES_STRING+"'");
				  setTempId2(getTempId2());
			}else
				 staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
			if(!ObjectFunctions.isNullOrEmpty(staff)){
				ViewUserRoles userRoles=adminManager.getViewUserRolesByUserIdAndCustId(staff.getAccount().getId(),getUserCustId(),Constants.NO_STRING);
				if(!ObjectFunctions.isNullOrEmpty(userRoles)){
					ViewStaffPermissionsSettings vePermissionsSettings = (ViewStaffPermissionsSettings)staffManager.get(ViewStaffPermissionsSettings.class, "roleId in (0,"+ userRoles.getRoleId()+")");
					if(!ObjectFunctions.isNullOrEmpty(vePermissionsSettings)){
						int days=vePermissionsSettings.getDays();
						if("Monthly Wise".equalsIgnoreCase(vePermissionsSettings.getMonthOrYear())){
							monthPermissionIds = adminManager.getAll("select permissionRequestId from vw_staffPermissionRequests where custId="+getUserCustId()+" and staffId="+staff.getId()+" and MONTH(permissionDate)=MONTH('"+getAnyId()+"') and status='"+Constants.ACTIVE_STATUS+"' ");
							if (ObjectFunctions.isNotNullOrEmpty(monthPermissionIds)) {
								if(monthPermissionIds.size() >= days){
									super.addActionError("Your monthly permissions limit "+days+" exceeded.");
								}
							}
						}else{
							yearPermissionIds = adminManager.getAll("select permissionRequestId from vw_staffPermissionRequests where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and stffId="+staff.getId()+" and status='"+Constants.ACTIVE_STATUS+"'");
							if (ObjectFunctions.isNotNullOrEmpty(yearPermissionIds)) {
								if(yearPermissionIds.size() == days || monthPermissionIds.size() < days){
									super.addActionError("Your yearly permissions limit is "+days+" exceeded.");
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
     }
	 
	 
	 @Actions( { @Action(value = "ajaxAddStaffPermissionsRequest", results = { @Result(location = "permissions/ajaxViewStaffRequests.jsp", name = "success") }) })
	    public String ajaxAddStaffPermissionsRequest() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxAddStaffPermissionsRequest' method");
		}
		try {
			if(getUserAcademicYearId()> 0) {
			Staff staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
			if(!ObjectFunctions.isNullOrEmpty(staff)){
				Map<String,String> msgs = studentManager.addStaffRequests(getUserCustId(),getUserAcademicYearId(),staff.getId(),getStaffPermissionRequestsVO());
				if (!StringFunctions.isNullOrEmpty(msgs.get("1")))
					super.addActionError("Requests not added.");
				if (!StringFunctions.isNullOrEmpty(msgs.get("0")))
					super.addActionMessage("Permission request added successfully.");
				}
			}
			getViewStaffPermissionRequests(); 
			setIsClassTeacher(getBankName());
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
     }
	 
	 @Actions( { @Action(value = "ajaxViewStaffRequest", results = { @Result(location = "permissions/ajaxViewStaffRequests.jsp", name = "success") }),
		 		 @Action(value = "ajaxAadminViewStaffRequest", results = { @Result(location = "permissions/ajaxViewStaffPermissionDetails.jsp", name = "success") }),
		         @Action(value = "ajaxViewAllStaffRequests", results = { @Result(location = "permissions/ajaxViewStaffPermissionDetails.jsp", name = "success") })
	 })
	    public String ajaxViewStaffRequest() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxViewStaffRequest' method");
		}
		try {
			getViewStaffPermissionRequests();
			setIsClassTeacher(getBankName());
			ajaxGetStaffPermissionsByDate();
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
     }
	 public void getViewStaffPermissionRequests(){
		 try {
			 setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.YYYY_MM_DD_PATTERN));// check the today date in jsp for delete
				StringBuffer query = new StringBuffer();
				query.append("custId=").append(getUserCustId());
				query.append(" and academicYearId<=").append(getUserAcademicYearId());
				if(getUser().isSchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector()){
					setTempList(staffManager.getAll(ViewStaffPermissionRequests.class, query.toString()+" and status='"+Constants.PENDING_STATUS+"'  order by permissionDate"));
					setTempList1(staffManager.getAll(ViewStaffPermissionRequests.class, query.toString()+" and status='"+Constants.ACTIVE_STATUS+"' "));
					setTempList2(staffManager.getAll(ViewStaffPermissionRequests.class, query.toString()+" and status='"+Constants.REJECTED_STATUS+"' "));
				}else{
					Staff staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
					if(!ObjectFunctions.isNullOrEmpty(staff)){
						setTempList(staffManager.getAll(ViewStaffPermissionRequests.class, query.toString()+" and status='"+Constants.PENDING_STATUS+"' and staffId="+staff.getId()+" order by permissionDate"));
						setTempList1(staffManager.getAll(ViewStaffPermissionRequests.class, query.toString()+" and status='"+Constants.ACTIVE_STATUS+"' and staffId="+staff.getId()));
						setTempList2(staffManager.getAll(ViewStaffPermissionRequests.class, query.toString()+" and status='"+Constants.REJECTED_STATUS+"' and staffId="+staff.getId()));
					}
				}
				query=null;
			} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
	 }
	 @Actions( { @Action(value = "ajaxStaffPermisssionApproveOrReject", results = { @Result(location = "permissions/ajaxViewStaffPermissionDetails.jsp", name = "success") }) })
	    public String ajaxStaffPermisssionApproveOrReject() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxStaffPermisssionApproveOrReject' method");
		}
		try {
			if(getTempId2()>0){
			StaffPermissionRequests staffRequest=(StaffPermissionRequests)studentManager.get(StaffPermissionRequests.class,getTempId2());
			if(!ObjectFunctions.isNullOrEmpty(staffRequest)){
				staffRequest.setApprovalsComment(getPlTitle());
				staffRequest.setLastUpdatedById(getUser().getId());
				staffRequest.setLastUpdatedDate(new Date());
				if(getAnyTitle().equalsIgnoreCase("R")){
					staffRequest.setStatus(Constants.REJECTED_STATUS);
					super.addActionMessage("Staff Request rejected.");
				}else if(getAnyTitle().equalsIgnoreCase("A")){
					staffRequest.setStatus(Constants.ACTIVE_STATUS);
					super.addActionMessage("Staff Request approved successfully");
				}
				staffRequest = (StaffPermissionRequests)staffManager.saveOrUpdateObject(staffRequest);
			}
			ajaxViewStaffRequest();
			
			Customer customer=getCustomerByCustId();
			Staff staff = (Staff)staffManager.get(Staff.class,"id="+staffRequest.getStaffId()+" and academicYearId<="+getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(staff) && !StringFunctions.isNullOrEmpty(staff.getAccount().getPrimaryAddress().getEmail()) && customer.isCheckEmailService()){
				String[] emailAddresses = new String[1];
				emailAddresses[0] = staff.getAccount().getPrimaryAddress().getEmail();
				ViewStaffPermissionRequests vPermissionRequests=(ViewStaffPermissionRequests)staffManager.get(ViewStaffPermissionRequests.class,"permissionRequestId="+staffRequest.getId());
				MailUtil mailUtil=new MailUtil(emailAddresses,"Regd : Your permission Request",customer.getId(),customer.getSender(),"Administrator",staffManager.getContactFromEmail(customer));
				mailUtil.setContactEmail(customer.getContactEmail());
				mailUtil.setContactPasword(customer.getContactPassword());
				mailUtil.sendMailToStaffRequest(vPermissionRequests,getAnyTitle(),staffManager.getContactFromEmail(customer));
				mailUtil=null;
				emailAddresses=null;
				staff=null;
			}
		}
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 @Actions({
			@Action(value = "ajaxDeleteStaffPermissionRequests", results = { @Result(location = "permissions/ajaxViewStaffPermissionDetails.jsp", name = "success") }) })
			public String ajaxDeleteStaffPermissionRequests() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDeleteStaffPermissionRequests' method");
			}
			try {
				if(Long.valueOf(getSelectedId()) > 0) {
					staffManager.remove(StaffPermissionRequests.class, Long.valueOf(getSelectedId()));
					super.addActionMessage("Permission request deleted successfully.");
					getViewStaffPermissionRequests(); 
				}
			} catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 
	 @Actions({
	   		@Action(value = "messManagerDashboard", results = { @Result(location = "messManagerHome.jsp", name = "success" )})
	   	})
	   		public String messManagerDashboard() throws URTUniversalException {
	   		if (log.isDebugEnabled()) {
	   			log.debug("Entering 'messManagerDashboard' method");
	   		}
	   		try {
	   			
	   			if(!ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear())){
	   				int staffCount,studentCount,totalStaff,totalStudent,presentStudent,presentStaff = 0;
		   			String inTime =  null;
		   			long academicYearId = getCurrentAcademicYear().getId();
		   			String schoolStarttime = getCurrentAcademicYear().getStartTime();
					setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.YYYY_MM_DD_PATTERN));
					if(!StringFunctions.isNullOrEmpty(getPlTitle())){
						String time[]= getPlTitle().split(" ");
						inTime = time[0].toString().replace(":", "");
						log.debug(inTime);
					}else	
						inTime = "9:30:00";
		   			if(!ObjectFunctions.isNullOrEmpty(schoolStarttime))
		   				staffCount = adminManager.getCount("staffDailyAttendance", "custId="+getUserCustId()+" and academicYearId="+academicYearId+" and inTime='"+schoolStarttime+"' and attendanceDate='"+getAttendanceDate()+"'");
		   			else{
		   				if("Y".equalsIgnoreCase(getCurrentAcademicYear().getUseBiometricForStaff())){
		   					if(ObjectFunctions.isNullOrEmpty(getPlTitle()))
			   					staffCount = adminManager.getCount("staffDailyAttendance", "custId="+getUserCustId()+" and academicYearId="+academicYearId+" and inTime='"+inTime+"' and attendanceDate='"+getAttendanceDate()+"'");
			   				else
				   				staffCount = adminManager.getCount("staffDailyAttendance", "custId="+getUserCustId()+" and academicYearId="+academicYearId+" and inTime BETWEEN STR_TO_DATE('093000','%h%i%s') and STR_TO_DATE('"+inTime+"','%h%i%s') and attendanceDate='"+getAttendanceDate()+"'");
		   				}else
			   				staffCount = adminManager.getCount("staffDailyAttendance", "custId="+getUserCustId()+" and academicYearId="+academicYearId+" and attendanceDate='"+getAttendanceDate()+"'");
		   			}
	   				studentCount = adminManager.getCount("studentDailyAttendance", "custId="+getUserCustId()+" and academicYearId="+academicYearId+" and attendanceDate='"+getAttendanceDate()+"'");
	   				totalStaff = adminManager.getCount("staff", "custId="+getUserCustId()+" and academicYearId="+academicYearId+" and description is null");
	   				totalStudent = adminManager.getCount("student", "custId="+getUserCustId()+" and academicYearId="+academicYearId+" and description is null");
	   				setTempId(totalStaff-staffCount);
					setTempId1(totalStudent-studentCount);
					setTempId2(getTempId()+getTempId1());
	   			}
	   		}
	   		catch(Exception ex)
	   		{
	   			log.error("Entering into 'catch block':"+ex.getMessage());
	   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	   		}
	   		return SUCCESS;
	   	}
	 @Actions({
	   		@Action(value = "ajaxGetStudentAndStaffCountBasedOnTime", results = { @Result(location = "ajaxViewStudentAndStaffCountBasedOnTime.jsp", name = "success" )})
	   	})
	   		public String ajaxGetStudentAndStaffCountBasedOnTime() throws URTUniversalException {
	   		if (log.isDebugEnabled()) {
	   			log.debug("Entering 'ajaxGetStudentAndStaffCountBasedOnTime' method");
	   		}
	   		try {
	   			messManagerDashboard();
	   		}catch(Exception ex){
	   			log.error("Entering into 'catch block':"+ex.getMessage());
	   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	   		}
	   		return SUCCESS;
	   	}
	 
	 
	 @Actions( { 
		 @Action(value = "ajaxViewStaffTimetable", results = { @Result(location = "manageStaff/ajaxViewStaffTimetable.jsp", name = "success") }) 
	})
	public String ajaxViewStaffTimetable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStaffTimetable' method");
		}
		try {
			setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.YYYY_MM_DD_PATTERN));
			setStaff((Staff)staffManager.get(Staff.class, " accountId = " + getUser().getId()));
			setObjectList(adminManager.getAll(TimetablePeriods.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+ " and status='"+Constants.ACTIVE_STATUS+"'"));
			setTempList(adminManager.getAll(WeekDays.class));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 
	 /* @Description 29 Apr Sunanda: Modularization  below method  disable the staff   change the adminAction to staffAction*/  
		@Actions( { @Action(value = "ajaxDisableStaffDocuments", results = { @Result(location = "disableStaff/ajaxViewExpiredStaffsList.jsp", name = "success") }) })
		public String ajaxDisableStaffDocuments() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDisableStaffDocuments' method");
			}
			try {
				StringBuffer query = new StringBuffer(" status='Y' and (checkEmailService = 'Y' or checkMobileService='Y')");
				List<Customer> customers = adminManager.getAll(Customer.class, query.toString());
				if(!ObjectFunctions.isNullOrEmpty(customers)){
					List<Staff> staffList = null;
					for(Customer customer : customers){
						staffList = adminManager.getAll(Staff.class, "custId="+customer.getId());
						if(!ObjectFunctions.isNullOrEmpty(staffList)){
							for(Staff staff : staffList){
								Person person= (Person)adminManager.get(Person.class,staff.getAccount().getPerson().getId());
								if(!ObjectFunctions.isNullOrEmpty(person)){
									//String memberName = null;
									String pathName = null;
									File directory = null;
									pathName = generateUploadFilepath(customer,person.getFirstName(),staff.getAccount().getId(),staff.getId());
									pathName = getSession().getServletContext().getRealPath(pathName);
									directory = new File(pathName);
									if(ObjectFunctions.isNullOrEmpty(directory.listFiles())){
										person.setIsDocsUploaded(Constants.NO_STRING);
										adminManager.save(person);									
									}
								}
								person = null;
								staffList = null;
							}
						}
						//customers = null;
					}
				}	
						
	 		} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxDoManageStaffPaySlips", results = { @Result(location = "paySlips/ajaxDoStaffPaySlips.jsp", name = "success") }),
			@Action(value = "ajaxViewStaffPaySlips", results = { @Result(location = "paySlips/ajaxViewStaffPaySlips.jsp", name = "success") })  })
		public String ajaxDoManageStaffPaySlips() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoManageStaffPaySlips' method");
			}
			try {
				AcademicYear academicYear =(AcademicYear)adminManager.get(AcademicYear.class,"id="+getUserAcademicYearId());
				String year[] = academicYear.getAcademicYear().split("-");
				log.debug("select * from paySlips where  (yearName="+Integer.valueOf(year[0].toString())+" and monthId>="+DateFunctions.getMonthOfDate(academicYear.getStartDate())+" and custId="+getUserCustId()+") or (yearName="+(Integer.valueOf(year[0].toString())+1)+" and monthId<="+DateFunctions.getMonthOfDate(academicYear.getEndDate())+" and custId="+getUserCustId()+") order by monthId ");
				if(getUser().isSchoolAdmin()){
					setObjectList(staffManager.getAll(PaySlip.class,"(yearName="+Integer.valueOf(year[0].toString())+" and monthId>="+DateFunctions.getMonthOfDate(academicYear.getStartDate())+" and custId="+getUserCustId()+") or (yearName="+(Integer.valueOf(year[0].toString())+1)+" and monthId<="+DateFunctions.getMonthOfDate(academicYear.getEndDate())+" and custId="+getUserCustId()+") order by monthId "));
				}else{
					setObjectList(staffManager.getAll(ViewStaffPaySlipDetails.class,"custId="+getUserCustId()+" and (yearName="+Integer.valueOf(year[0].toString())+" and monthId>="+DateFunctions.getMonthOfDate(academicYear.getStartDate())+" and accountId="+getUser().getId()+") or (yearName="+Integer.valueOf(year[0].toString())+1+" and monthId<="+DateFunctions.getMonthOfDate(academicYear.getEndDate())+" and accountId="+getUser().getId()+") order by monthId " ));
				}
	 		} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxGetRoleByMonthId", results = { @Result(location = "paySlips/ajaxGetStaffRoleByMonth.jsp", name = "success"),
				                                                        @Result(location = "paySlips/ajaxViewStaffPaySlipsDetails.jsp", name = "staffInfo") }) })
		public String ajaxGetRoleByMonthId() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoManageStaffPaySlips' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getTempString())){
					setCustomer(getCustomerByCustId());
					if("A".equalsIgnoreCase(getTempString()))
						setTempList1(staffManager.getAll(ViewStaffPaySlipDetails.class,"custId="+getUserCustId()+" and monthId="+getTempId1()+" and yearName="+getTempId2()));
					else
						 setTempList1(staffManager.getAll(ViewStaffPaySlipDetails.class,"custId="+getUserCustId()+" and monthId="+getTempId1()+" and yearName="+getTempId2()+" and roleId="+getTempString()));
					 return "staffInfo";
				} else
					setTempList(staffManager.getAll(ViewStaffPaySlipDetails.class,"custId="+getUserCustId()+" and monthId="+getTempId1()+" and yearName="+getTempId2()+" group by roleId"));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxSendEmailsPaySlips", results = { @Result(location = "paySlips/ajaxViewStaffPaySlipsDetails.jsp", name = "success") }) })
		public String ajaxSendEmailsPaySlips() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSendEmailsPaySlips' method");
			}
			File tempFolfer = null;
			try {
				
				String[] mb = getPlTitle().split(",");
				if(!ObjectFunctions.isNullOrEmpty(mb)){
					StringBuffer pathName = null;
					int responseCode = 0;
					boolean emailStatus= false;
					ZipOutputStream zipOutStream = null;
					String newfolderpath = null;
					 File folder = null;
					if(mb.length>1 && "D".equalsIgnoreCase(getAnyId())){// Staff paySlips download In Zip format(if we download more than one staff)
						getResponse().setContentType("application/zip");
						zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
						getResponse().addHeader("Content-Disposition", "attachment; filename=STAFF_PAYSLIPS.zip");
					}
					tempFolfer = File.createTempFile("paySlipsTempFolder","");
					tempFolfer.delete();
					tempFolfer.mkdir();
		            newfolderpath =  tempFolfer.getAbsolutePath();
					
					PaySlip paySlip =(PaySlip)adminManager.get(PaySlip.class,"custId="+getUserCustId() + " and yearName="+getTempId2()+" and monthId="+getTempId1());
					if(!ObjectFunctions.isNullOrEmpty(paySlip))
					{
						Customer customer = adminManager.getCustomerByCustId(paySlip.getId());
						List<StaffMonthlyPaySlips> staffMonthlyPaySlipsList = adminManager.getAll(StaffMonthlyPaySlips.class,"custId="+getUserCustId()+" and month="+getTempId1()+" and paySlipId="+paySlip.getId()+" and accountId in ("+getPlTitle()+"0)");
						if(!ObjectFunctions.isNullOrEmpty(staffMonthlyPaySlipsList))
						{
							for(StaffMonthlyPaySlips staffMonthlyPaySlips : staffMonthlyPaySlipsList)
							{
								if(!StringFunctions.isNullOrEmpty(staffMonthlyPaySlips.getFilePath()))
								{
									try {
										URL url = new URL(staffMonthlyPaySlips.getFilePath());
										URLConnection conn = url.openConnection();
										InputStream is = conn.getInputStream();
										
										//File tempFile = File.createTempFile(""+staffMonthlyPaySlips.getAccountId(), ".pdf");
										File tempFile = File.createTempFile(""+staffMonthlyPaySlips.getAccountId(), ".pdf",tempFolfer);
									    tempFile.deleteOnExit();
										FileUtils.copyInputStreamToFile(is, tempFile);
										
										if("D".equalsIgnoreCase(getAnyId())){
											if(mb.length==1){
												//File imageFile = new File(staffMonthlyPaySlips.getFilePath());
												paySlipDownloadInPDF(staffMonthlyPaySlips.getAccountId(),tempFile);
												tempFile.delete();
												FileUtils.deleteDirectory(tempFolfer);
											}
										}
										else{
											String formEmail = "messages@eazyschool.com";
											 if(!StringFunctions.isNullOrEmpty(customer.getContactEmail()))
												 formEmail = customer.getContactEmail();
											ViewStaffPaySlipDetails staffObj = (ViewStaffPaySlipDetails)staffManager.get(ViewStaffPaySlipDetails.class,"custId="+getUserCustId()+" and monthId="+getTempId1()+" and yearName="+getTempId2()+" and accountId="+staffMonthlyPaySlips.getAccountId());
											if(!ObjectFunctions.isNullOrEmpty(staffObj) && !ObjectFunctions.isNullOrEmpty(staffObj.getEmail())){
												String[] attachedFiles = new String[1];
												 attachedFiles[0] =tempFile.getAbsolutePath();
												 String[] emailAddresses = new String[1];
												 emailAddresses[0] = staffObj.getEmail();
												 MailUtil mailUtil = new MailUtil(emailAddresses, staffObj.getMonthName()+" - PaySlip", null, getUser(),attachedFiles,formEmail);
												 Customer usrCust = (Customer)adminManager.get(Customer.class,"id="+getUserCustId());
												 //if(!ObjectFunctions.isNullOrEmpty(usrCust.getContactEmail())){
													 responseCode = mailUtil.sendEmailPaySlipsToStaff(staffObj.getFullName(),formEmail,staffObj.getMonthName(),usrCust);
												 if(responseCode !=3)
													 emailStatus= true;
												 
											 tempFile.delete();
											 mailUtil = null;
											 emailAddresses = null;
											 attachedFiles = null;
											 pathName = null;
									  }
									 }
										
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				if("D".equalsIgnoreCase(getAnyId())){
					if(mb.length>1){
						  File tempFile1 = new File(newfolderpath.toString());
			              StringFunctions.zipFiles(tempFile1,zipOutStream);
			              zipOutStream = null;
			              folder = null;
					}
	              return null;
				}else{
					if(responseCode == 0 && emailStatus)
						super.addActionMessage("E-Mail(s) has been delivered successfully.");
					else
						super.addActionError("E-Mail(s) has not been delivered successfully.");
					 ajaxGetRoleByMonthId();
				}
			}
 		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
			finally
			{
				try {
					FileUtils.deleteDirectory(tempFolfer);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return SUCCESS;
	}
		
	 @Action(value = "ajaxDownloadStaffPaySlipsInPdfFormat", results = {})
		public void ajaxDownloadStaffPaySlipsInPdfFormat() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDownloadStaffPaySlipsInPdfFormat' method");
			}
			try {
				String[] mb = getAnyTitle().split("_");
				StringBuffer pathName = new StringBuffer("userFiles/").append(getUserCustId()).append("/").append("payslips").append("/").append(Integer.valueOf(mb[1].toString())).append("/").append(Integer.valueOf(mb[0].toString())).append("/").append(getUser().getId()).append(".pdf");
				File folder = new File(getSession().getServletContext().getRealPath(pathName.toString()));
				paySlipDownloadInPDF(getUser().getId(),folder);
				folder = null;
				pathName = null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
	 }
	 
	 public String staffSecondaryUsernameWithMobileNumber(String roleName,String mobileNumber){
		 try {
			 String userName=null;
			 if (Constants.SCHOOL_PRINCIPAL.equalsIgnoreCase(roleName) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_VICEPRINCIPAL.equalsIgnoreCase(roleName) || Constants.SCHOOL_TEACHER.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_ASST_STAFF.equalsIgnoreCase(roleName) || Constants.SCHOOL_HOD.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_TRANSPORT.equalsIgnoreCase(roleName) || Constants.SCHOOL_TRANSPORTFINANCE.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_FINANCE.equalsIgnoreCase(roleName) || Constants.SCHOOL_LIBRARIAN.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_HOSTELFINANCE.equalsIgnoreCase(roleName) || Constants.SCHOOL_HOSTEL.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_SEO.equalsIgnoreCase(roleName) || Constants.SCHOOL_DEO.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_CEO.equalsIgnoreCase(roleName) || Constants.SCHOOL_BEO.equalsIgnoreCase(roleName)
						|| Constants.SCHOOL_ROLE_RECEPTIONIST.equalsIgnoreCase(roleName) || Constants.SCHOOL_ROLE_STOREKEEPER.equalsIgnoreCase(roleName)) {
					if(!StringFunctions.isNullOrEmpty(mobileNumber)){
						userName =  mobileNumber;
					}
				}else{
					if(!StringFunctions.isNullOrEmpty(mobileNumber)){
						userName =  mobileNumber;
					}
				}
			 return userName;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	 }

	public User staffUsernameAvailabulity(String userName) {
		boolean userExists = false;
		try {
			if (!StringFunctions.isNullOrEmpty(userName)) {
				List<User> staffSeconrdyUserList = null;
				List<User> custStaffSeconrdyUserList = null;
				if (StringFunctions.isNotNullOrEmpty(userName)) {
					staffSeconrdyUserList = adminManager.getAll(User.class," username like '" + userName+ "' and accountEnabled='Y' ");
					if (staffSeconrdyUserList.size() > 0) {
						return (User)staffSeconrdyUserList.get(0);
					} else {
						custStaffSeconrdyUserList = adminManager.getAll(User.class," username like '"+ userName+ "' and accountEnabled='N' and custId ="+ getUserCustId());
						if (custStaffSeconrdyUserList.size() > 0) {
							return (User)custStaffSeconrdyUserList.get(0);
						}
					}
				}
				staffSeconrdyUserList = null;
				custStaffSeconrdyUserList = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}

	public boolean staffSecondaryUsernameAvailabulity(String secondaryUserName) {
		boolean userExists = false;
		try {
			if (!StringFunctions.isNullOrEmpty(secondaryUserName)) {
				List<User> staffSeconrdyUserList = null;
				if (StringFunctions.isNotNullOrEmpty(secondaryUserName)) {
					staffSeconrdyUserList = adminManager.getAll(User.class," secondaryUsername like '" + secondaryUserName+ "'");
					if (staffSeconrdyUserList.size() > 0) {
						userExists = false;
					} else{
						userExists = true;
					}
				}
				staffSeconrdyUserList = null;
			}
			return userExists;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return false;
	}
	
	@Actions( { @Action(value = "ajaxViewSmsHistory", results = { @Result(location = "manageStaff/ajaxViewSmsHistory.jsp", name = "success") }) })
	public String ajaxViewSmsHistory() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewSmsHistory' method");
		}
		try {
			if(getUserAcademicYearId() > 0)
				loadAcademicYearStartDateAndDates(getUserAcademicYearId());
			
			setAnyTitle(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxSecondaryUsernameAvailableOrNot", results = { @Result(type = "json", name = "success", params = { "includeProperties", "autoCheck" }) }) })
	public String ajaxSecondaryUsernameAvailableOrNot()throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSecondaryUsernameAvailableOrNot' method");
		}
		try {
			String username = getParamValue("keyWord");
			setAutoCheck(SecondaryUsernameAvailableOrNot(username));
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	
	public void sendLogincredentailsToStaff(Staff staff,Customer customer,AcademicYear academciYear,String newPassword) {
		String msgStatus =  null;
		Set<String> mobileNumbersSet = null; 
		Messages message = new Messages();
		StringBuffer buffer = null;
		String roleName = staff.getAccount().getRoleName();
		if(!"ROLE_AYAH".equalsIgnoreCase(roleName) && !"ROLE_MANAGEMENTTRAINEE".equalsIgnoreCase(roleName) && !"ROLE_CONDUCTOR".equalsIgnoreCase(roleName) 
				&& !"ROLE_HELPER".equalsIgnoreCase(roleName) && !"ROLE_LABASST".equalsIgnoreCase(roleName) && !"ROLE_PEON".equalsIgnoreCase(roleName) && !"ROLE_OTHER".equalsIgnoreCase(roleName)
				&& !"ROLE_PUBLICRELATIONOFFICER".equalsIgnoreCase(roleName) && !"ROLE_SWEEPER".equalsIgnoreCase(roleName) && !"ROLE_TYPIST".equalsIgnoreCase(roleName)
				&& !"ROLE_WATCHMAN".equalsIgnoreCase(roleName) && !"ROLE_SYSTEMADMINISTRATOR".equalsIgnoreCase(roleName) && !"ROLE_MEMBER".equalsIgnoreCase(roleName) 
				&& !"ROLE_VICE_CHAIRMAN".equalsIgnoreCase(roleName) && !"ROLE_EXECUTIVE_VICE_CHAIRMAN".equalsIgnoreCase(roleName)
				&& !"ROLE_BDM".equalsIgnoreCase(roleName) 	&& !"ROLE_MESS_MANAGER".equalsIgnoreCase(roleName) && !"ROLE_SALES_EXECUTIVE".equalsIgnoreCase(roleName)
				&& !"ROLE_SALES_HEAD".equalsIgnoreCase(roleName) && !"ROLE_TRANSPORT".equalsIgnoreCase(roleName)
				&& !"ROLE_TRANSPORTFINANCE".equalsIgnoreCase(roleName) && !"ROLE_EXECUTIVE_MEMBER".equalsIgnoreCase(roleName)
				&& !"ROLE_JOINT_SECRETARIES".equalsIgnoreCase(roleName) && !"ROLE_STAFF_NURSE".equalsIgnoreCase(roleName))
		{
			if (!ObjectFunctions.isNullOrEmpty(staff.getAccount().getPerson().getMobileNumber())) {
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				mobileNumbersSet = new HashSet<String>(); 
				mobileNumbersSet.add(staff.getAccount().getPerson().getMobileNumber()); 
				message.setCustomer(customer);		
				message.setSmsSenderId(customer.getSender());
				buffer = new StringBuffer();
				buffer.append("Dear Staff,");
				if("ROLE_DRIVER".equalsIgnoreCase(roleName)){ 
				buffer.append(" Your "+customer.getOrganization()+"  ,Android App login details are Username:");
				}else{
					buffer.append(" Your "+customer.getOrganization()+" account login details are below URL:www.eazyschool.in Username:");
				}
				buffer.append(staff.getAccount().getUsername());
				buffer.append(" Password:"+newPassword+" from ");
				message.setMessageDescription(buffer.toString());
				message.setAcademicYear(academciYear);
				message.setStatus("A");// here remove Constants.MODIFY_STATUS and add A for send login creds to automatic
				message.setCreatedById(getUser().getId());
				message.setPurposeType("Staff Login Credentials");
				log.debug(buffer.toString());
				message = communicationManager.saveMessageDetailsTracking(message,null,staff,null);	
				communicationManager.deliverSms(message,mobileNumbersSet, smsServiceProvider);
				 mobileNumbersSet = null;
			}
		}
	}
	
	
	public String ajaxDoGetStudentOrStaffPorfileDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetStudentOrStaffPorfileDetails' method");
		}
		try {
			StudyClass st = null;
			List<ViewStudentPersonAccountDetails> studentsList = null;
			String frequency = getParamValue("frequency");
			if (StringFunctions.isNullOrEmpty(frequency)) {
				frequency = "ST";
			}
			if (!StringFunctions.isNullOrEmpty(frequency)) {
				if (getUser().getId() > 0) {
					setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
				}
				long accountId = getUser().getId();
				List<Object[]> classTeachersList = null;
				StringBuffer studyClassIdes = null;
				if ("ST".equalsIgnoreCase(frequency)) {
					if (!ObjectFunctions.isNullOrEmpty(accountId)) {
						setAllStudentsList(null);
						if (getTempId1() > 0) {
							if (getUser().isSchoolTeacher()) {
								studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class,"classSectionId =" + getTempId1()+ " and academicYearId="+ getUserAcademicYearId()+ " and description is null");
								setClassStudentsList(studentsList);
							}
						} else {
							studyClassIdes = new StringBuffer("(");
							classTeachersList = adminManager.getAll("Select IFNULL(studyClassId,0) as studyClassId,IFNULL(teacherId,0) as teacherId from classTeacher WHERE teacherId="+ getStaff().getId()+ " and academicYearId="+ getUserAcademicYearId()+ " group by studyClassId ");
							if (ObjectFunctions.isNotNullOrEmpty(classTeachersList)) {
								for (Object[] classTeacher : classTeachersList) {
									if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
										if (Long.valueOf(classTeacher[0].toString())>0) {
											studyClassIdes.append(classTeacher[0].toString()).append(",");
										}
									}
									classTeacher = null;
								}
							}
							if (getUser().isOnlySchoolHod()) {
								StringBuffer sqlQuery = new StringBuffer("select  st.id,st.className,st.section,st.classNameClassId  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)");
								sqlQuery.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+ getUserAcademicYearId()).append(" and sm.staffId=").append(getStaff().getId());
								List<Object[]> studyclassesList = staffManager.getAll(sqlQuery.toString());
								if (!ObjectFunctions.isNullOrEmpty(studyclassesList)) {
									for (Object[] obj : studyclassesList) {
										if (!ObjectFunctions.isNullOrEmpty(obj[0])) {
											studyClassIdes.append(obj[0].toString()).append(",");
											st = new StudyClass();
										}
									}
								}
							}
							studyClassIdes.append("0)");
							studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class,"classSectionId in "+ studyClassIdes.toString()+ " and academicYearId="+ getUserAcademicYearId()+ " and description is null");
							setAnyTitle(studyClassIdes.toString());
						}
						if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
							setStudentsList(new ArrayList());
							setClassStudentsList(studentsList);
							setViewStudentPersonAccountDetailsList(studentsList);
							for (ViewStudentPersonAccountDetails studentDetails : studentsList) {
								if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
									getAllStudentsList().add(studentDetails.getRollNumber());
									getStudentsList().add(studentDetails.getFirstName());
								}
								studentDetails = null;
							}
							setStudyClass(st);
							studentsList = null;
						}
					}
				} else if ("S".equalsIgnoreCase(frequency)) {
					List<ViewStaffPersonAccountDetails> staffsList = null;
					if (getTempId1() > 0) {
						studyClassIdes = new StringBuffer("(");
						StringBuffer sqlQuery = new StringBuffer("select  st.id,sm.staffId  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)");
						sqlQuery.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+ getUserAcademicYearId()).append(" and sm.staffId=");
						sqlQuery.append(getStaff().getId()).append(" and sm.studyClassId=").append(getTempId1());
						List<Object[]> studyclassesList = staffManager.getAll(sqlQuery.toString());
						StringBuffer studyClassIds = new StringBuffer("(");
						if (!ObjectFunctions.isNullOrEmpty(studyclassesList)) {
							for (Object[] obj : studyclassesList) {
								if (!ObjectFunctions.isNullOrEmpty(obj[1])) {
									studyClassIdes.append(obj[1].toString()).append(",");
									studyClassIds.append(obj[0].toString()).append(",");
									st = new StudyClass();
								}
							}
						}
						studyClassIds.append("0)");
						if (!ObjectFunctions.isNullOrEmpty(studyClassIds.toString())) {
							classTeachersList = adminManager.getAllHqlQuery("Select IFNULL(teacherId,0) as teacherId,IFNULL(studyClassId,0) as studyClassId from classTeacher WHERE  academicYearId="+ getUserAcademicYearId()+ " and studyClassId in "+ studyClassIds.toString()+ " and teacherId not in (select id from staff where custId="+getUserCustId()+" and status='N') group by teacherId ");
						} else{
							classTeachersList = adminManager.getAll("Select IFNULL(teacherId,0) as teacherId,IFNULL(studyClassId,0) as studyClassId from classTeacher custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and studyClassId="+ getTempId1()+ " and classTeacher='Y'");
						}
					} else {
						studyClassIdes = new StringBuffer("(");
						StringBuffer sqlQuery = new StringBuffer("select  st.id,sm.staffId  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)");
						sqlQuery.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+ getUserAcademicYearId()).append(" and sm.staffId=").append(getStaff().getId());
						List<Object[]> studyclassesList = staffManager.getAll(sqlQuery.toString());
						StringBuffer studyClassIds = new StringBuffer("(");
						if (!ObjectFunctions.isNullOrEmpty(studyclassesList)) {
							for (Object[] obj : studyclassesList) {
								if (!ObjectFunctions.isNullOrEmpty(obj[1]) && !ObjectFunctions.isNullOrEmpty(obj[0].toString())) {
									studyClassIds.append(obj[0].toString()).append(",");
									studyClassIdes.append(obj[1].toString()).append(",");
									st = new StudyClass();
								}
							}
						}
						studyClassIds.append("0)");
						if (!ObjectFunctions.isNullOrEmpty(studyClassIds.toString())) {
							classTeachersList = adminManager.getAll("Select IFNULL(teacherId,0) as teacherId,IFNULL(studyClassId,0) as studyClassId from classTeacher WHERE  academicYearId="+ getUserAcademicYearId()+ " and studyClassId in "+ studyClassIds.toString()+ " and teacherId not in (select id from staff where custId="+getUserCustId()+" and status='N') group by teacherId ");
						} else{
							classTeachersList = adminManager.getAll("Select IFNULL(teacherId,0) as teacherId,IFNULL(studyClassId,0) as studyClassId from classTeacher WHERE teacherId="+ getStaff().getId()+ " and academicYearId="+ getUserAcademicYearId()+ " and classTeacher='Y'");
						}
						setAnyTitle(studyClassIdes.toString());
					}
					if (ObjectFunctions.isNotNullOrEmpty(classTeachersList)) {
						for (Object[] classTeacher : classTeachersList) {
							if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
								if (Long.valueOf(classTeacher[1].toString())>0) {
									studyClassIdes.append(classTeacher[1].toString()).append(",");
								}
							}
							classTeacher = null;
						}
						studyClassIdes.append("0)");
						staffsList = adminManager.getAll(ViewStaffPersonAccountDetails.class,"custId="+ getUserCustId()+ " and academicYearStatus='Y' and accountExpired='N' and staffId in "+ studyClassIdes.toString()+ " group by staffId");
					}
					if (!ObjectFunctions.isNullOrEmpty(staffsList)) {
						for (ViewStaffPersonAccountDetails staffDetails : staffsList) {
							if (!ObjectFunctions.isNullOrEmpty(staffDetails)) {
								if (Constants.SCHOOL_TEACHER.equalsIgnoreCase(staffDetails.getRoleName())) {
									getAllStaffList().add(staffDetails.getUsername());
									getStaffsList().add(staffDetails.getFirstName());
								}
							}
							staffDetails = null;
						}
						staffsList = null;
					}
				}
				if (ObjectFunctions.isNotNullOrEmpty(getStudentsList()))
					Collections.sort(getStudentsList());
				if (ObjectFunctions.isNotNullOrEmpty(getStaffsList()))
					Collections.sort(getStaffsList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxTaskInformationHome", results = { @Result(location = "task/ajaxTaskInformationHome.jsp", name = "success") })})
	public String ajaxTaskInformationHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxTaskInformationHome' method");
		}
		try {
			setObjectList(staffManager.getAllTaskdetails(getAnyTitle(),getUser(),getIsClassTeacherOrNot()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoCreateTaskInformaion", results = { @Result(location = "task/ajaxDoCreateTaskInformation.jsp", name = "success") }),
				@Action(value = "ajaxEditTaskInformation", results = { @Result(location = "task/ajaxEditTaskInformation.jsp", name = "success") })	})
	public String ajaxDoCreateTaskInformaion() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoCreateTaskInformaion' method");
		}
		try {	
			if(!ObjectFunctions.isNullOrEmpty(getTaskDetailsVO().getId()) && getTaskDetailsVO().getId() > 0){
				setTaskDetailsVO(staffManager.getTaskDetailsById(getTaskDetailsVO().getId()));
			}
			getStaffDetailsBasedOnUser();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxAddTaskInformation", results = { @Result(location = "task/ajaxTaskInformationHome.jsp", name = "success"),
																	  @Result(location = "task/ajaxReminderInformationHome.jsp", name = "reminderPage") }) })
	public String ajaxAddTaskInformation() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddTaskInformation' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTaskDetailsVO())){
				if("T".equalsIgnoreCase(getTempString())){
					String isCheckMobileServiceForTask = getParamValue("taskDetailsVO.checkMobileService");
					String isCheckEmailServiceForTask = getParamValue("taskDetailsVO.checkEmailService");
					int returnCode = staffManager.addOrUpdateTaskDetails(getTaskDetailsVO(),getUser(),getUserAcademicYearId(),isCheckMobileServiceForTask,isCheckEmailServiceForTask);
					if(returnCode==0)
						super.addActionError("Task not added. Please contact administrator");
					else if (returnCode==1)
						super.addActionMessage("Task details updated successfully.");
					else if (returnCode==2)
						super.addActionMessage("Task details created successfully.");
					setTitle("R"); // Here pass the R value in after change the action reload the page and update the task Details count in the left Navigation
				}else if("R".equalsIgnoreCase(getTempString())){
					String isCheckMobileService = getParamValue("reminderVO.checkMobileService");
					String isCheckEmailService = getParamValue("reminderVO.checkEmailService");
					int returnCode = staffManager.addOrUpdateReminder(getReminderVO(),getUser(),getUserAcademicYearId(),isCheckMobileService,isCheckEmailService);
					if(returnCode==0)
						super.addActionError("Reminder not added. Please contact administrator");
					else if (returnCode==1)
						super.addActionMessage("Reminder details updated successfully.");
					else if (returnCode==2)
						super.addActionMessage("Reminder details created successfully.");
					setTempString("R");
					return "reminderPage";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			if("T".equalsIgnoreCase(getTempString()))
				ajaxTaskInformationHome();
			else
				ajaxReminderInformation();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxShowTaskDetailsProcess", results = { @Result(location = "task/ajaxPopUpTaskDetails.jsp", name = "success") }) })
    public String ajaxShowTaskDetailsProcess() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxShowTaskDetailsProcess' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTaskId())){
				setTaskDetailsVO(staffManager.showTaskInfromationInTaskProcess(getTaskId()));
			}
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Actions( { @Action(value = "ajaxUpdateInprogressOrCompleteTaskDetails", results = { @Result(location = "task/ajaxTaskInformationHome.jsp", name = "success") }) })
    public String ajaxUpdateInprogressOrCompleteTaskDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxUpdateInprogressOrCompleteTaskDetails' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTaskId()) && !ObjectFunctions.isNullOrEmpty(getTaskHistoryVO())){
				int returnCode = staffManager.addOrUpdateTaskInprogressOrComplete(getTaskId(),getTaskHistoryVO(),getUserAcademicYearId());
				if(returnCode==0)
					super.addActionError("Task details not added. Please contact administrator");
				else if (returnCode==1)
					super.addActionMessage("Task details updated successfully.");
				setTitle("R"); // Here pass the R value in after change the action reload the page and update the task Details count in the left Navigation
			}
		} catch (Exception ex) {
		    ex.printStackTrace();
		}finally{
			ajaxTaskInformationHome();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoPopUpTaskDescPreview", results = { @Result(location = "task/ajaxPopUpTaskDescription.jsp", name = "success") }) })
    public String ajaxDoPopUpTaskDescPreview() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDoPopUpTaskDescPreview' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTempId())){
				TaskDetails taskDetails = (TaskDetails) staffManager.get(TaskDetails.class,getTempId());
				setTempString(taskDetails.getDescription());
			}
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return SUCCESS;
    }
	
	@Actions( { @Action(value = "ajaxDoPopUpTaskComments", results = { @Result(location = "task/ajaxPopUpTaskComments.jsp", name = "success") }) })
    public String ajaxDoPopUpTaskComments() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDoPopUpTaskComments' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTaskId())){
				setTaskDetailsVO(staffManager.showTaskDetailsComments(getTaskId()));
			}
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return SUCCESS;
    }
	@Actions( { @Action(value = "ajaxRemoveTaskDetails", results = { @Result(location = "task/ajaxTaskInformationHome.jsp", name = "success") }) })
	public String ajaxRemoveTaskDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveTaskDetails' method");
		}
		try{
			if(!ObjectFunctions.isNullOrEmpty(getTaskDetailsVO().getId())){
				int responseCode = staffManager.deleteTaskDetails(Long.valueOf(getTaskDetailsVO().getId()));
				if(responseCode == 0){
					super.addActionMessage("Task removed successfully.");
					setTitle("R"); // Here pass the R value in after change the action reload the page and update the task Details count in the left Navigation
				}else{
					super.addActionError("Task not deleted.");
				}
				ajaxTaskInformationHome();				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Actions( { @Action(value = "ajaxReminderInformation", results = { @Result(location = "task/ajaxReminderInformationHome.jsp", name = "success") })})
	public String ajaxReminderInformation() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxReminderInformation' method");
		}
		try { 
			setObjectList(staffManager.getRemindersList(getUserCustId(),getUser().getId()));
			SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
			Date date= new Date();
			setTempString(dateformat.format(date));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxEditReminders", results = { @Result(location = "task/ajaxEditReminderInformation.jsp") }) })
public String ajaxEditReminders() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxEditReminders' method");
	}
	try {
		if (!ObjectFunctions.isNullOrEmpty(getReminderVO())) {
			if (getReminderVO().getId() > 0 && !ObjectFunctions.isNullOrEmpty(getReminderVO().getId())) {
				setReminderVO(staffManager.getReminderDetailsById(getReminderVO().getId()));
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return SUCCESS;
}
	
	@Actions( { @Action(value = "ajaxRemoveReminderDetails", results = { @Result(location = "task/ajaxReminderInformationHome.jsp", name = "success") }) })
	public String ajaxRemoveReminderDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveReminderDetails' method");
		}
		try{
			if(getReminderVO().getId()>0){
				staffManager.remove(Reminder.class, getReminderVO().getId());
					super.addActionMessage("Reminder removed successfully.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		finally{
			ajaxReminderInformation();
		}
		return SUCCESS;
	}
	/**
	 * @return the storeAssigned
	 */
	public boolean isStoreAssigned() {
		return storeAssigned;
	}
	/**
	 * @param storeAssigned the storeAssigned to set
	 */
	public void setStoreAssigned(boolean storeAssigned) {
		this.storeAssigned = storeAssigned;
	}
	
	@Actions( { @Action(value = "ajaxCalendarInformationHome", results = { @Result(location = "calendar/ajaxCalendarInformationHome.jsp", name = "success") }),
				@Action(value = "ajaxViewCalendarInformation", results = { @Result(location = "calendar/ajaxViewCalendarInformation.jsp", name = "success") })
	})
	public String ajaxCalendarInformationHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCalendarInformationHome' method");
		}
		try{} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		finally{
			//ajaxCalendarInformationHome();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetStaffCalendar", results = { @Result(type = "json", name = "success", params = {"includeProperties", "timingsList.*" }) }) })
	public String ajaxGetStaffCalendar() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStaffCalendar' method");
		}
		try{
			 JSONObject calInfo = new JSONObject();
			 GsonBuilder gsonBuilder = new GsonBuilder(); gsonBuilder.serializeNulls(); 
			 Gson gson = gsonBuilder.create();
			 JSONParser parser = new JSONParser();
			 JSONArray res = new JSONArray();
			 JSONObject j=new JSONObject();
			//For Tasks
			 List<ViewTaskDetailsAndTaskHistory> taskReminderList = studentManager.getAll(ViewTaskDetailsAndTaskHistory.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" and status in ('O','H') and taskDate >= curDate() and (reminderOption = 'E' or specificDate >= curDate())");
			 if(!ObjectFunctions.isNullOrEmpty(taskReminderList)){
				 	//calInfo.put("calendarInfo", (org.json.simple.JSONArray)parser.parse(gson.toJson(taskReminderList)));
					for(ViewTaskDetailsAndTaskHistory task: taskReminderList){
						j=new JSONObject();
						j.put("title",task.getTaskName()); 
						if(!ObjectFunctions.isNullOrEmpty(task.getSpecificDate())){
							j.put("start",task.getSpecificDate());
						    j.put("end",task.getSpecificDate());
						}
						else{
					    j.put("start",task.getTaskCreatedDate());
					    j.put("end",task.getTaskDate());
						}
					    j.put("color", "#0c7518");
					    j.put("allDay",true);
					    j.put("id",task.getTaskDetailsId());
					    res.put(j);
						task=null; 
					}
					
			}
			 //For Reminders
			 List<Reminder> reminderList=studentManager.getAll(Reminder.class,"custId="+getUserCustId()+" and accountId="+getUser().getId()+" and expirationDate >= curDate() and (reminderoption = 'E' or specificDate >= curDate())");
			 if(!ObjectFunctions.isNullOrEmpty(reminderList)){
					for(Reminder reminder: reminderList){
						j=new JSONObject();
						j.put("title",reminder.getName()); 
						if(!ObjectFunctions.isNullOrEmpty(reminder.getSpecificDate())){
							j.put("start",reminder.getSpecificDate());
						    j.put("end",reminder.getSpecificDate());
						}
						else{
							j.put("start",reminder.getCreatedDate());
						    j.put("end",reminder.getExpirationDate());
						}
					    
					    j.put("color", "#FF0000");
					    j.put("allDay",true);
					    j.put("id",reminder.getId());
					    res.put(j);
					    reminder=null; 
					}
			}
			//For Events
			ajaxStaffEvents();
			/* String studyClassIds=null;
				List<BigInteger> staffStudyClassIds =  adminManager.getAll("select studyClassId from vw_staffSubjectsDetails where custId ="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" group by studyClassId");
				if (ObjectFunctions.isNotNullOrEmpty(staffStudyClassIds)) 
					studyClassIds = StringFunctions.convertListToCommaDelimitedString(staffStudyClassIds);
				else
					studyClassIds="0";
			 List<Events> eventsList = adminManager.eventsforStaffStudents(studyClassIds,getUser().getRoleId(),getUserCustId(),getUserAcademicYearId(),Constants.ACTIVE_STATUS);*/
			 if(!ObjectFunctions.isNullOrEmpty(getEventsList())){
				 for( Events event : getEventsList()) {
						//Events event = (Events)obj;
						if(event.getStartDate().after(DateUtil.getYesterday())){
						j=new JSONObject();
						j.put("title",event.getEventName()); 
					    j.put("start",event.getStartDate());
					    j.put("end",event.getEndDate());
					    j.put("color", "#800080");
					    j.put("allDay",false);
					    j.put("id",event.getId());
					    res.put(j);
						}
					    event=null; 
					}
			}
			 calInfo.put("calendarInfo", res);
			 getResponse().getOutputStream().print(calInfo.toString());
	}
	catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return null;
	}
} 