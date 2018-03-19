package com.urt.webapp.action.student;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
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
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.hyniva.Amazon.S3Wrapper;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.hyniva.sms.ws.vo.AcademicYearVo;
import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.ClassNameVO;
import com.hyniva.sms.ws.vo.ExcelStudentVO;
import com.hyniva.sms.ws.vo.ParentsEmploymentDetailsVO;
import com.hyniva.sms.ws.vo.PersonHealthDetailsVO;
import com.hyniva.sms.ws.vo.PersonOtherDetailsVO;
import com.hyniva.sms.ws.vo.PersonVO;
import com.hyniva.sms.ws.vo.StudentMarksVO;
import com.hyniva.sms.ws.vo.StudentVo;
import com.hyniva.sms.ws.vo.UserVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Appointment;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.FeedBackSettings;
import com.urt.persistence.model.common.FeedbackGrades;
import com.urt.persistence.model.common.FeedbackQuestions;
import com.urt.persistence.model.common.HouseType;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.Permissions;
import com.urt.persistence.model.common.PersonDocuments;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.common.VWFeedbackRatingDetails;
import com.urt.persistence.model.common.ViewPermissionSettings;
import com.urt.persistence.model.common.ViewUserRoles;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.HostelLeave;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.event.Events;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamSchedulesStartDateComparator;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.KBankType;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.exam.QuizQuestion;
import com.urt.persistence.model.exam.SchoolGrades;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.exam.ViewStaffSubjectsDetails;
import com.urt.persistence.model.exam.ViewStudentQuestionAnswers;
import com.urt.persistence.model.exam.WeekDays;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.rss.Feed;
import com.urt.persistence.model.rss.FeedMessage;
import com.urt.persistence.model.rss.RSSFeedParser;
import com.urt.persistence.model.study.ActivityType;
import com.urt.persistence.model.study.ClassAssignment;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.ParentFeedbackResult;
import com.urt.persistence.model.study.PromoteClass;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentClassAssignment;
import com.urt.persistence.model.study.StudentCurricularActivities;
import com.urt.persistence.model.study.StudentMarks;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudentQuestionAnswer;
import com.urt.persistence.model.study.StudentQuizResults;
import com.urt.persistence.model.study.StudentRollNumberComparator;
import com.urt.persistence.model.study.StudentSiblings;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.SuspendAndBlacklistStudents;
import com.urt.persistence.model.study.TimeTable;
import com.urt.persistence.model.study.TimetablePeriods;
import com.urt.persistence.model.study.TransferStudent;
import com.urt.persistence.model.study.ViewAppointmetDetails;
import com.urt.persistence.model.study.ViewClassSectionDetails;
import com.urt.persistence.model.study.ViewStaffAppointmetDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudyClassSubjects;
import com.urt.persistence.model.transport.ViewStaffVehicleTripdetails;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.ParentsEmploymentDetails;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.PersonHealthDetails;
import com.urt.persistence.model.user.PersonOtherDetails;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.persistence.util.excel.StudentExcelRow;
import com.urt.service.thread.EntryStudentCommittedPaymentThread;
import com.urt.service.thread.SendEmailToParentsAndStudentThread;
import com.urt.service.thread.SendNotificationForStudentUpdateThread;
import com.urt.service.thread.SendSmsForParentsAndStudentThread;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.excel.parser.SheetParser;
import com.urt.util.excel.student.PrepareStudentAdmissionExcel;
import com.urt.util.excel.student.PrepareStudentExcel;
import com.urt.util.excel.student.PrepareStudentNoEmailAndMobileExcel;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.event.DOMUtil;
import com.urt.webapp.action.event.RecurringEventsDOM;

/**
 * Action for facilitating ClassSubject Management feature.
 */
@Namespace("/student")
@Actions( {	@Action(value = "doStudentActivitiesHome", results = {   @Result(location = "class/manageMyClass.jsp", name = "success") }),
	@Action(value = "parentHome", results = { @Result(location = "parent/parentHome.jsp", name = "success") }),
	@Action(value = "ajaxParentProfile", results = { @Result(location = "parent/ajaxParentProfile.jsp", name = "success") }), 
@Action(value = "ajaxDoSearchKVideos", results = { @Result(location = "kBank/ajaxSearchKVideos.jsp", name = "success") }),
@Action(value = "ajaxGetFeedbackForm", results = { @Result(location = "parent/feedback/selectChildForFeedback.jsp", name = "success") }),
@Action(value = "ajaxDoAddChildren", results = { @Result(location = "parent/ajaxAddMyChildren.jsp", name = "success") }),
@Action(value = "ajaxStudentTransfer", results = { @Result(location = "../admin/student/transferStudent/ajaxViewStudentByAdmissionNumber.jsp", name = "success") })
 })

public class StudentAction extends BaseAction {

	private static final long serialVersionUID = -1646390427462403153L;

	private String anyId;
	protected Set<String> customerNamesSet;
	protected Fee feeStructure;
	protected List inviteAccepted;
	protected List inviteDecline;
	protected Set<String> eventDatesSet;
	protected StudyClass studyClass;
	protected String currentPwd;
	protected String newPwd;
	protected String confirmPwd;
	protected ViewStaffVehicleTripdetails viewStaffVehicleTripdetails;
	protected StudentPayment studentPayment;
	protected TimeTable timeTable;
	protected List termMarksOneList;
	protected List termMarksTwoList;
	protected List termMarksThreeList;
	protected List termMarksFourList;
	protected List halfYearlyMarksList;
	protected List quarterlyMarksList;
	protected List yearlyMarksList;
	protected double termExamOneTotalMarks;
	protected double termExamTwoTotalMarks;
	protected double termExamThreeTotalMarks;
	protected double termExamFourTotalMarks;
	protected double halfYearlyExamTotalMarks;
	protected double quarterlyExamTotalMarks;
	protected double yearlyExamTotalMarks;
	protected StudentMarks studentMarks;
	protected Double halfYearlyMinMarks;
	protected String halfYearlyMaxMarks;
	protected Double yearlyMinMarks;
	protected String yearlyMaxMarks;
	protected Double quarterlyMinMarks;
	protected String quarterlyMaxMarks;
	protected Double termExamOneMinMarks;
	protected String termExamOneMaxMarks;
	protected Double termExamTwoMinMarks;
	protected String termExamTwoMaxMarks;
	protected Double termExamThreeMinMarks;
	protected String termExamThreeMaxMarks;
	protected Double termExamFourMinMarks;
	protected String termExamFourMaxMarks;
	protected List viewStaffVehicleTripList;
	protected String wishTitle;
	protected String wishDescription;
	protected List studentsBirthdaysList;
	protected String editor;
	protected List unReadMsgsList;
	protected List myReplyMessages;
	private Set<Messages> studentMessagesList;
	private Date thresholdDate;
	protected String categoryName;
	private int thresholdMonths;
	protected List termExamMarksOneList;
	protected List termExamMarksTwoList;
	protected List termExamMarksThreeList;
	protected List termExamMarksFourList;
	protected List halfYearlyExamMarksList;
	protected List quarterlyExamMarksList;
	protected List yearlyExamMarksList;
	protected double termExamOneTotalMaxMarks;
	protected double termExamTwoTotalMaxMarks;
	protected double termExamThreeTotalMaxMarks;
	protected double termExamFourTotalMaxMarks;
	protected double halfYearlyExamTotalMaxMarks;
	protected double quarterlyExamTotalMaxMarks;
	protected double yearlyExamTotalMaxMarks;
	protected List childFeeDetails;
	protected KBank knowledgeBank;
	protected KBankType knowledgeBankType;
	protected List<KBankType> knowledgeBankTypeList;
	protected List<KBank> knowledgeBankList;
	private Attachment attachment;
	private String kBankTypeName;
	private StudentQuizResults studentQuizResults;
	private StudentQuestionAnswer studentQuestionAnswer;
	protected List parentFeedbackList;
	private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadFileName = new ArrayList<String>();
	private FeedBackSettings feedBackSettings;
	private Appointment appointment;
	private List<File> phFileUpload = new ArrayList<File>();
	private List<String> phFileUploadFileName = new ArrayList<String>();
	@Override
	public List<String> getPhFileUploadFileName() {
		return phFileUploadFileName;
	}
	@Override
	public void setPhFileUploadFileName(List<String> phFileUploadFileName) {
		this.phFileUploadFileName = phFileUploadFileName;
	}
	@Override
	public List<File> getPhFileUpload() {
			return phFileUpload;
		}
	@Override
		public void setPhFileUpload(List<File> phFileUpload) {
			this.phFileUpload = phFileUpload;
		}
	
 
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public FeedBackSettings getFeedBackSettings() {
		return feedBackSettings;
	}

	public void setFeedBackSettings(FeedBackSettings feedBackSettings) {
		this.feedBackSettings = feedBackSettings;
	}

	@Override
	public List<File> getFileUpload() {
		return fileUpload;
	}
 
	@Override
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}
 
	@Override
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	@Override
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	/**
	 * @return the parentFeedbackList
	 */
	public List getParentFeedbackList() {
		if (ObjectFunctions.isNullOrEmpty(this.parentFeedbackList)) {
			this.parentFeedbackList = new ArrayList();
		}
		return parentFeedbackList;
	}
	/**
	 * @param parentFeedbackList the parentFeedbackList to set
	 */
	public void setParentFeedbackList(List parentFeedbackList) {
		this.parentFeedbackList = parentFeedbackList;
	}
	/**
	 * @return the studentQuestionAnswer
	 */
	public StudentQuestionAnswer getStudentQuestionAnswer() {
		return studentQuestionAnswer;
	}
	/**
	 * @param studentQuestionAnswer the studentQuestionAnswer to set
	 */
	public void setStudentQuestionAnswer(StudentQuestionAnswer studentQuestionAnswer) {
		this.studentQuestionAnswer = studentQuestionAnswer;
	}
	/**
	 * @return the studentQuizResults
	 */
	public StudentQuizResults getStudentQuizResults() {
		return studentQuizResults;
	}
	/**
	 * @param studentQuizResults the studentQuizResults to set
	 */
	public void setStudentQuizResults(StudentQuizResults studentQuizResults) {
		this.studentQuizResults = studentQuizResults;
	}
	@Override
	public String getKBankTypeName() {
		return kBankTypeName;
	}
	@Override
	public void setKBankTypeName(String kBankTypeName) {
		this.kBankTypeName = kBankTypeName;
	}
	@Override
	public Attachment getAttachment() {
		if(ObjectFunctions.isNullOrEmpty(this.attachment)){
			this.attachment  = new Attachment();
		}
		return this.attachment;
	}
	@Override
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@Override
	public List<KBank> getKnowledgeBankList() {
		if (ObjectFunctions.isNullOrEmpty(this.knowledgeBankList)) {
			this.knowledgeBankList = new ArrayList<KBank>();
		}
		return knowledgeBankList;
	}
	@Override
	public void setKnowledgeBankList(List<KBank> knowledgeBankList) {
		this.knowledgeBankList = knowledgeBankList;
	}
	@Override
	public List<KBankType> getKnowledgeBankTypeList() {
		if (ObjectFunctions.isNullOrEmpty(this.knowledgeBankTypeList)) {
			this.knowledgeBankTypeList = new ArrayList<KBankType>();
		}
		return knowledgeBankTypeList;
	}
	@Override
	public void setKnowledgeBankTypeList(List<KBankType> knowledgeBankTypeList) {
		this.knowledgeBankTypeList = knowledgeBankTypeList;
	}
	@Override
	public KBank getKnowledgeBank() {
		return knowledgeBank;
	}
	@Override
	public void setKnowledgeBank(KBank knowledgeBank) {
		this.knowledgeBank = knowledgeBank;
	}
	@Override
	public KBankType getKnowledgeBankType() {
		return knowledgeBankType;
	}
	@Override
	public void setKnowledgeBankType(KBankType knowledgeBankType) {
		this.knowledgeBankType = knowledgeBankType;
	}
	
	public List getChildFeeDetails() {
		if(ObjectFunctions.isNullOrEmpty(this.childFeeDetails))
		{
			this.childFeeDetails=new ArrayList();
		}
		return childFeeDetails;
	}

	public void setChildFeeDetails(List childFeeDetails) {
		this.childFeeDetails = childFeeDetails;
	}

	/**
	 * @return the termExamOneTotalMaxMarks
	 */
	public double getTermExamOneTotalMaxMarks() {
		return termExamOneTotalMaxMarks;
	}

	/**
	 * @param termExamOneTotalMaxMarks the termExamOneTotalMaxMarks to set
	 */
	public void setTermExamOneTotalMaxMarks(double termExamOneTotalMaxMarks) {
		this.termExamOneTotalMaxMarks = termExamOneTotalMaxMarks;
	}

	/**
	 * @return the termExamTwoTotalMaxMarks
	 */
	public double getTermExamTwoTotalMaxMarks() {
		return termExamTwoTotalMaxMarks;
	}

	/**
	 * @param termExamTwoTotalMaxMarks the termExamTwoTotalMaxMarks to set
	 */
	public void setTermExamTwoTotalMaxMarks(double termExamTwoTotalMaxMarks) {
		this.termExamTwoTotalMaxMarks = termExamTwoTotalMaxMarks;
	}

	/**
	 * @return the termExamThreeTotalMaxMarks
	 */
	public double getTermExamThreeTotalMaxMarks() {
		return termExamThreeTotalMaxMarks;
	}

	/**
	 * @param termExamThreeTotalMaxMarks the termExamThreeTotalMaxMarks to set
	 */
	public void setTermExamThreeTotalMaxMarks(double termExamThreeTotalMaxMarks) {
		this.termExamThreeTotalMaxMarks = termExamThreeTotalMaxMarks;
	}

	/**
	 * @return the termExamFourTotalMaxMarks
	 */
	public double getTermExamFourTotalMaxMarks() {
		return termExamFourTotalMaxMarks;
	}

	/**
	 * @param termExamFourTotalMaxMarks the termExamFourTotalMaxMarks to set
	 */
	public void setTermExamFourTotalMaxMarks(double termExamFourTotalMaxMarks) {
		this.termExamFourTotalMaxMarks = termExamFourTotalMaxMarks;
	}

	/**
	 * @return the halfYearlyExamTotalMaxMarks
	 */
	public double getHalfYearlyExamTotalMaxMarks() {
		return halfYearlyExamTotalMaxMarks;
	}

	/**
	 * @param halfYearlyExamTotalMaxMarks the halfYearlyExamTotalMaxMarks to set
	 */
	public void setHalfYearlyExamTotalMaxMarks(double halfYearlyExamTotalMaxMarks) {
		this.halfYearlyExamTotalMaxMarks = halfYearlyExamTotalMaxMarks;
	}

	/**
	 * @return the quarterlyExamTotalMaxMarks
	 */
	public double getQuarterlyExamTotalMaxMarks() {
		return quarterlyExamTotalMaxMarks;
	}

	/**
	 * @param quarterlyExamTotalMaxMarks the quarterlyExamTotalMaxMarks to set
	 */
	public void setQuarterlyExamTotalMaxMarks(double quarterlyExamTotalMaxMarks) {
		this.quarterlyExamTotalMaxMarks = quarterlyExamTotalMaxMarks;
	}

	/**
	 * @return the yearlyExamTotalMaxMarks
	 */
	public double getYearlyExamTotalMaxMarks() {
		return yearlyExamTotalMaxMarks;
	}

	/**
	 * @param yearlyExamTotalMaxMarks the yearlyExamTotalMaxMarks to set
	 */
	public void setYearlyExamTotalMaxMarks(double yearlyExamTotalMaxMarks) {
		this.yearlyExamTotalMaxMarks = yearlyExamTotalMaxMarks;
	}

	/**
	 * @return the termExamMarksOneList
	 */
	public List getTermExamMarksOneList() {
		if(ObjectFunctions.isNullOrEmpty(this.termExamMarksOneList))
		{
			this.termExamMarksOneList=new ArrayList();
		}
		return termExamMarksOneList;
	}

	/**
	 * @param termExamMarksOneList the termExamMarksOneList to set
	 */
	public void setTermExamMarksOneList(List termExamMarksOneList) {
		this.termExamMarksOneList = termExamMarksOneList;
	}

	/**
	 * @return the termExamMarksTwoList
	 */
	public List getTermExamMarksTwoList() {
		if(ObjectFunctions.isNullOrEmpty(this.termExamMarksTwoList))
		{
			this.termExamMarksTwoList=new ArrayList();
		}
		return termExamMarksTwoList;
	}

	/**
	 * @param termExamMarksTwoList the termExamMarksTwoList to set
	 */
	public void setTermExamMarksTwoList(List termExamMarksTwoList) {
		this.termExamMarksTwoList = termExamMarksTwoList;
	}

	/**
	 * @return the termExamMarksThreeList
	 */
	public List getTermExamMarksThreeList() {
		if(ObjectFunctions.isNullOrEmpty(this.termExamMarksThreeList))
		{
			this.termExamMarksThreeList=new ArrayList();
		}
		return termExamMarksThreeList;
	}

	/**
	 * @param termExamMarksThreeList the termExamMarksThreeList to set
	 */
	public void setTermExamMarksThreeList(List termExamMarksThreeList) {
		this.termExamMarksThreeList = termExamMarksThreeList;
	}

	/**
	 * @return the termExamMarksFourList
	 */
	public List getTermExamMarksFourList() {
		if(ObjectFunctions.isNullOrEmpty(this.termExamMarksFourList))
		{
			this.termExamMarksFourList=new ArrayList();
		}
		return termExamMarksFourList;
	}

	/**
	 * @param termExamMarksFourList the termExamMarksFourList to set
	 */
	public void setTermExamMarksFourList(List termExamMarksFourList) {
		this.termExamMarksFourList = termExamMarksFourList;
	}

	/**
	 * @return the halfYearlyExamMarksList
	 */
	public List getHalfYearlyExamMarksList() {
		if(ObjectFunctions.isNullOrEmpty(this.halfYearlyExamMarksList))
		{
			this.halfYearlyExamMarksList=new ArrayList();
		}
		return halfYearlyExamMarksList;
	}

	/**
	 * @param halfYearlyExamMarksList the halfYearlyExamMarksList to set
	 */
	public void setHalfYearlyExamMarksList(List halfYearlyExamMarksList) {
		this.halfYearlyExamMarksList = halfYearlyExamMarksList;
	}

	/**
	 * @return the quarterlyExamMarksList
	 */
	public List getQuarterlyExamMarksList() {
		if(ObjectFunctions.isNullOrEmpty(this.quarterlyExamMarksList))
		{
			this.quarterlyExamMarksList=new ArrayList();
		}
		return quarterlyExamMarksList;
	}

	/**
	 * @param quarterlyExamMarksList the quarterlyExamMarksList to set
	 */
	public void setQuarterlyExamMarksList(List quarterlyExamMarksList) {
		this.quarterlyExamMarksList = quarterlyExamMarksList;
	}

	/**
	 * @return the yearlyExamMarksList
	 */
	public List getYearlyExamMarksList() {
		if(ObjectFunctions.isNullOrEmpty(this.yearlyExamMarksList))
		{
			this.yearlyExamMarksList=new ArrayList();
		}
		return yearlyExamMarksList;
	}

	/**
	 * @param yearlyExamMarksList the yearlyExamMarksList to set
	 */
	public void setYearlyExamMarksList(List yearlyExamMarksList) {
		this.yearlyExamMarksList = yearlyExamMarksList;
	}
	public Date getThresholdDate() {
		return thresholdDate;
	}

	public void setThresholdDate(Date thresholdDate) {
		this.thresholdDate = thresholdDate;
	}

	

	/**
	 * @return the categoryName
	 */
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


	/**
	 * @return the calendarSettings
	 */
	/**
	 * @return the thresholdMonths
	 */
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
	
	public String getHalfYearlyMaxMarks() {
		return halfYearlyMaxMarks;
	}
	public void setHalfYearlyMaxMarks(String halfYearlyMaxMarks) {
		this.halfYearlyMaxMarks = halfYearlyMaxMarks;
	}
	public String getYearlyMaxMarks() {
		return yearlyMaxMarks;
	}
	public void setYearlyMaxMarks(String yearlyMaxMarks) {
		this.yearlyMaxMarks = yearlyMaxMarks;
	}
	public String getQuarterlyMaxMarks() {
		return quarterlyMaxMarks;
	}
	public void setQuarterlyMaxMarks(String quarterlyMaxMarks) {
		this.quarterlyMaxMarks = quarterlyMaxMarks;
	}
	public String getTermExamOneMaxMarks() {
		return termExamOneMaxMarks;
	}
	public void setTermExamOneMaxMarks(String termExamOneMaxMarks) {
		this.termExamOneMaxMarks = termExamOneMaxMarks;
	}
	public String getTermExamTwoMaxMarks() {
		return termExamTwoMaxMarks;
	}
	public void setTermExamTwoMaxMarks(String termExamTwoMaxMarks) {
		this.termExamTwoMaxMarks = termExamTwoMaxMarks;
	}
	public String getTermExamThreeMaxMarks() {
		return termExamThreeMaxMarks;
	}
	public void setTermExamThreeMaxMarks(String termExamThreeMaxMarks) {
		this.termExamThreeMaxMarks = termExamThreeMaxMarks;
	}
	public String getTermExamFourMaxMarks() {
		return termExamFourMaxMarks;
	}
	public void setTermExamFourMaxMarks(String termExamFourMaxMarks) {
		this.termExamFourMaxMarks = termExamFourMaxMarks;
	}
	public Set<Messages> getStudentMessagesList() {
		if (ObjectFunctions.isNullOrEmpty(this.studentMessagesList)) {
			this.studentMessagesList = new HashSet<Messages>();
		}
		return this.studentMessagesList;
	}
	public void setStudentMessagesList(Set<Messages> studentMessagesList) {
		this.studentMessagesList = studentMessagesList;
	}

	public List getMyReplyMessages() {
		if(ObjectFunctions.isNullOrEmpty(this.myReplyMessages))
		{
			this.myReplyMessages = new ArrayList();
		}
		return myReplyMessages;
	}

	public void setMyReplyMessages(List myReplyMessages) {
		this.myReplyMessages = myReplyMessages;
	}
	
	public List getUnReadMsgsList() {
		if(ObjectFunctions.isNullOrEmpty(this.unReadMsgsList))
		{
			this.unReadMsgsList = new ArrayList();
		}
		return unReadMsgsList;
	}

	public void setUnReadMsgsList(List unReadMsgsList) {
		this.unReadMsgsList = unReadMsgsList;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
	public List getStudentsBirthdaysList() {
		if (ObjectFunctions.isNullOrEmpty(this.studentsBirthdaysList)) {
			this.studentsBirthdaysList = new ArrayList();
		}
		return studentsBirthdaysList;
	}

	public void setStudentsBirthdaysList(List studentsBirthdaysList) {
		this.studentsBirthdaysList = studentsBirthdaysList;
	}

	@Override
	public String getWishTitle() {
		return wishTitle;
	}

	@Override
	public void setWishTitle(String wishTitle) {
		this.wishTitle = wishTitle;
	}

	@Override
	public String getWishDescription() {
		return wishDescription;
	}

	@Override
	public void setWishDescription(String wishDescription) {
		this.wishDescription = wishDescription;
	}

	public List getViewStaffVehicleTripList() {
		if (ObjectFunctions.isNullOrEmpty(this.viewStaffVehicleTripList)) {
			this.viewStaffVehicleTripList = new ArrayList();
		}
		return viewStaffVehicleTripList;
	}

	public void setViewStaffVehicleTripList(List viewStaffVehicleTripList) {
		this.viewStaffVehicleTripList = viewStaffVehicleTripList;
	}

	public Double getYearlyMinMarks() {
		return yearlyMinMarks;
	}

	public void setYearlyMinMarks(Double yearlyMinMarks) {
		this.yearlyMinMarks = yearlyMinMarks;
	}

	public Double getQuarterlyMinMarks() {
		return quarterlyMinMarks;
	}

	public void setQuarterlyMinMarks(Double quarterlyMinMarks) {
		this.quarterlyMinMarks = quarterlyMinMarks;
	}

	public Double getTermExamOneMinMarks() {
		return termExamOneMinMarks;
	}

	public void setTermExamOneMinMarks(Double termExamOneMinMarks) {
		this.termExamOneMinMarks = termExamOneMinMarks;
	}

	public Double getTermExamTwoMinMarks() {
		return termExamTwoMinMarks;
	}

	public void setTermExamTwoMinMarks(Double termExamTwoMinMarks) {
		this.termExamTwoMinMarks = termExamTwoMinMarks;
	}

	public Double getTermExamThreeMinMarks() {
		return termExamThreeMinMarks;
	}

	public void setTermExamThreeMinMarks(Double termExamThreeMinMarks) {
		this.termExamThreeMinMarks = termExamThreeMinMarks;
	}

	public Double getTermExamFourMinMarks() {
		return termExamFourMinMarks;
	}

	public void setTermExamFourMinMarks(Double termExamFourMinMarks) {
		this.termExamFourMinMarks = termExamFourMinMarks;
	}

	public Double getHalfYearlyMinMarks() {
		return halfYearlyMinMarks;
	}

	public void setHalfYearlyMinMarks(Double halfYearlyMinMarks) {
		this.halfYearlyMinMarks = halfYearlyMinMarks;
	}

	public StudentMarks getStudentMarks() {
		return studentMarks;
	}

	public void setStudentMarks(StudentMarks studentMarks) {
		this.studentMarks = studentMarks;
	}

	public double getTermExamOneTotalMarks() {
		return termExamOneTotalMarks;
	}

	public void setTermExamOneTotalMarks(double termExamOneTotalMarks) {
		this.termExamOneTotalMarks = termExamOneTotalMarks;
	}

	public double getTermExamTwoTotalMarks() {
		return termExamTwoTotalMarks;
	}

	public void setTermExamTwoTotalMarks(double termExamTwoTotalMarks) {
		this.termExamTwoTotalMarks = termExamTwoTotalMarks;
	}

	public double getTermExamThreeTotalMarks() {
		return termExamThreeTotalMarks;
	}

	public void setTermExamThreeTotalMarks(double termExamThreeTotalMarks) {
		this.termExamThreeTotalMarks = termExamThreeTotalMarks;
	}

	public double getTermExamFourTotalMarks() {
		return termExamFourTotalMarks;
	}

	public void setTermExamFourTotalMarks(double termExamFourTotalMarks) {
		this.termExamFourTotalMarks = termExamFourTotalMarks;
	}

	public double getHalfYearlyExamTotalMarks() {
		return halfYearlyExamTotalMarks;
	}

	public void setHalfYearlyExamTotalMarks(double halfYearlyExamTotalMarks) {
		this.halfYearlyExamTotalMarks = halfYearlyExamTotalMarks;
	}

	public double getQuarterlyExamTotalMarks() {
		return quarterlyExamTotalMarks;
	}

	public void setQuarterlyExamTotalMarks(double quarterlyExamTotalMarks) {
		this.quarterlyExamTotalMarks = quarterlyExamTotalMarks;
	}

	public double getYearlyExamTotalMarks() {
		return yearlyExamTotalMarks;
	}

	public void setYearlyExamTotalMarks(double yearlyExamTotalMarks) {
		this.yearlyExamTotalMarks = yearlyExamTotalMarks;
	}

	public List getTermMarksOneList() {
		if (ObjectFunctions.isNullOrEmpty(this.termMarksOneList)) {
			this.termMarksOneList = new ArrayList();
		}
		return termMarksOneList;
	}

	public void setTermMarksOneList(List termMarksOneList) {
		this.termMarksOneList = termMarksOneList;
	}

	public List getTermMarksTwoList() {
		if (ObjectFunctions.isNullOrEmpty(this.termMarksTwoList)) {
			this.termMarksTwoList = new ArrayList();
		}
		return termMarksTwoList;
	}

	public void setTermMarksTwoList(List termMarksTwoList) {
		this.termMarksTwoList = termMarksTwoList;
	}

	public List getTermMarksThreeList() {
		if (ObjectFunctions.isNullOrEmpty(this.termMarksThreeList)) {
			this.termMarksThreeList = new ArrayList();
		}
		return termMarksThreeList;
	}

	public void setTermMarksThreeList(List termMarksThreeList) {
		this.termMarksThreeList = termMarksThreeList;
	}

	public List getTermMarksFourList() {
		if (ObjectFunctions.isNullOrEmpty(this.termMarksFourList)) {
			this.termMarksFourList = new ArrayList();
		}
		return termMarksFourList;
	}

	public void setTermMarksFourList(List termMarksFourList) {
		this.termMarksFourList = termMarksFourList;
	}

	public List getHalfYearlyMarksList() {
		if (ObjectFunctions.isNullOrEmpty(this.halfYearlyMarksList)) {
			this.halfYearlyMarksList = new ArrayList();
		}
		return halfYearlyMarksList;
	}

	public void setHalfYearlyMarksList(List halfYearlyMarksList) {
		this.halfYearlyMarksList = halfYearlyMarksList;
	}

	public List getQuarterlyMarksList() {
		if (ObjectFunctions.isNullOrEmpty(this.quarterlyMarksList)) {
			this.quarterlyMarksList = new ArrayList();
		}
		return quarterlyMarksList;
	}

	public void setQuarterlyMarksList(List quarterlyMarksList) {
		this.quarterlyMarksList = quarterlyMarksList;
	}

	public List getYearlyMarksList() {
		if (ObjectFunctions.isNullOrEmpty(this.yearlyMarksList)) {
			this.yearlyMarksList = new ArrayList();
		}
		return yearlyMarksList;
	}

	public void setYearlyMarksList(List yearlyMarksList) {
		this.yearlyMarksList = yearlyMarksList;
	}

	@Override
	public StudentPayment getStudentPayment() {
		return studentPayment;
	}

	@Override
	public void setStudentPayment(StudentPayment studentPayment) {
		this.studentPayment = studentPayment;
	}

	public ViewStaffVehicleTripdetails getViewStaffVehicleTripdetails() {
		return viewStaffVehicleTripdetails;
	}

	public void setViewStaffVehicleTripdetails(
			ViewStaffVehicleTripdetails viewStaffVehicleTripdetails) {
		this.viewStaffVehicleTripdetails = viewStaffVehicleTripdetails;
	}

	public List getInviteAccepted() {
		if (ObjectFunctions.isNullOrEmpty(this.inviteAccepted)) {
			this.inviteAccepted = new ArrayList();
		}
		return inviteAccepted;
	}

	public List getInviteDecline() {
		if (ObjectFunctions.isNullOrEmpty(this.inviteDecline)) {
			this.inviteDecline = new ArrayList();
		}
		return inviteDecline;
	}

	public void setInviteAccepted(List inviteAccepted) {
		this.inviteAccepted = inviteAccepted;
	}

	public void setInviteDecline(List inviteDecline) {
		this.inviteDecline = inviteDecline;
	}

	public Set getCustomerNamesSet() {
		if (ObjectFunctions.isNullOrEmpty(this.customerNamesSet)) {
			this.customerNamesSet = new HashSet();
		}
		return customerNamesSet;
	}

	public void setCustomerNamesSet(Set customerNamesSet) {
		this.customerNamesSet = customerNamesSet;
	}

	@Override
	public String getAnyId() {
		return anyId;
	}

	@Override
	public void setAnyId(String anyId) {
		this.anyId = anyId;
	}

	@Override
	public List getObjectList() {
		if (ObjectFunctions.isNullOrEmpty(this.objectList)) {
			this.objectList = new ArrayList();
		}
		return this.objectList;
	}

	@Override
	public void setObjectList(List objectList) {
		this.objectList = objectList;
	}

	/**
	 * @return the eventDatesSet
	 */
	public Set<String> getEventDatesSet() {
		if (ObjectFunctions.isNullOrEmpty(this.eventDatesSet)) {
			this.eventDatesSet = new HashSet<String>();
		}
		return this.eventDatesSet;
	}

	/**
	 * @param eventDatesSet
	 *            the eventDatesSet to set
	 */
	public void setEventDatesSet(Set<String> eventDatesSet) {
		this.eventDatesSet = eventDatesSet;
	}

	@Override
	public Map getJsonResult() {
		if (ObjectFunctions.isNullOrEmpty(super.jsonResult)) {
			super.jsonResult = new HashMap();
		}
		return super.jsonResult;
	}
	/**
	 * @return the studyClass
	 */
	@Override
	public StudyClass getStudyClass() {
		return studyClass;
	}

	/**
	 * @param studyClass
	 *            the studyClass to set
	 */
	@Override
	public void setStudyClass(StudyClass studyClass) {
		this.studyClass = studyClass;
	}

	/**
	 * @return the currentPwd
	 */
	public String getCurrentPwd() {
		return currentPwd;
	}

	/**
	 * @param currentPwd
	 *            the currentPwd to set
	 */
	public void setCurrentPwd(String currentPwd) {
		this.currentPwd = currentPwd;
	}

	/**
	 * @return the newPwd
	 */
	public String getNewPwd() {
		return newPwd;
	}

	/**
	 * @param newPwd
	 *            the newPwd to set
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	/**
	 * @return the confirmPwd
	 */
	public String getConfirmPwd() {
		return confirmPwd;
	}

	/**
	 * @param confirmPwd
	 *            the confirmPwd to set
	 */
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	/**
	 * @return the feeStructure
	 */
	public Fee getFeeStructure() {
		return feeStructure;
	}

	/**
	 * @return the timeTable
	 */
	@Override
	public TimeTable getTimeTable() {
		if (ObjectFunctions.isNullOrEmpty(this.timeTable)) {
			this.timeTable = new TimeTable();
		}
		return this.timeTable;
	}

	/**
	 * @param timeTable
	 *            the timeTable to set
	 */
	@Override
	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

	/**
	 * @param feeStructure
	 *            the feeStructure to set
	 */
	public void setFeeStructure(Fee feeStructure) {
		this.feeStructure = feeStructure;
	}
// Just validated exam schedules module. Need to validate remaining modules in this method.
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 8, 2013       Seshu		        For displaying student and parent dashboard.
/********************************************************************/	
	@Actions( {
		@Action(value = "studentHome", results = {@Result(location = "studentHome.jsp", name = "success"),
													@Result(location = "studentHomeOld.jsp", name = "parent") })
			 })
	public String studentHome() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'studentHome' method");
	}
	try {
	    setUser((User)subscriptionManager.get(User.class,getUser().getId()));
		if (getUser().isParent()) {
			long accountId=(long) getSession().getAttribute("selectedStudentId");
			//StringBuffer query = new StringBuffer("FROM Student stud WHERE stud.custId="+getUserCustId()+" and stud.academicYear="+getUserAcademicYearId()+" and stud.account.parentId=").append(getUser().getId()).append(" and stud.status='Y'");
			StringBuffer query = new StringBuffer("FROM Student stud WHERE stud.custId="+getUserCustId()+" and stud.academicYear="+getUserAcademicYearId()+" and stud.account.id=").append(accountId).append(" and stud.status='Y'");
			setStudentsList(studentManager.getAllHqlQuery(query.toString()));
			if(ObjectFunctions.isNotNullOrEmpty(getStudentsList())){
				setStudent((Student)getStudentsList().get(0));
				setTempId(getStudent().getId());
				setClassSectionId(String.valueOf(getStudent().getClassSectionId()));
				ajaxStudentsUpcomingExamSchedulesDetails();
				ClassAssignment assignment = (ClassAssignment)studentManager.get(ClassAssignment.class,"classSectionId="+((Student)getStudentsList().get(0)).getClassSectionId()+""
						+ " and assignmentDate >='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00'  order by assignmentDate asc LIMIT 1");
			setClassAssignment(assignment);
			}
			ajaxViewNoticeBoardMessagesForStudent();
			setTempString(getParamValue("totalAmount"));
			ajaxGetStudentLatestMarks();
			getSchoolWideAlertsforAllRoles();
			return "parent";
		}
		// it is for student
		else if (getUser().isSchoolStudent()) {
			Student student = (Student)studentManager.get(Student.class,"accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(student))
				setClassSectionId(String.valueOf(student.getClassSectionId()));
			ajaxStudentsUpcomingExamSchedulesDetails();
			//axDoGetStudentOrStaffPorfileDetailsForStudent(); Need to rework 
			ajaxViewNoticeBoardMessagesForStudent();
			ajaxDoGetSchoolWideAlertsList();
			ajaxGetStudentLatestMarks();
			getSchoolWideAlertsforAllRoles();
			student = null;
		}
		
		
		
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 8, 2013       Seshu		        For displaying student and parent dashboard.
/********************************************************************/
	@Actions( {
		@Action(value = "ajaxStudentsUpcomingExamSchedulesDetails", results = {@Result(location = "../exam/ajaxUpcomingExamSchedulesInfo.jsp", name = "success") })
			 })
	public String ajaxStudentsUpcomingExamSchedulesDetails(){
		if(log.isDebugEnabled())
			log.debug("Entering 'ajaxStudentsUpcomingExamSchedulesDetails' method");
		if(StringFunctions.isNotNullOrEmpty(getClassSectionId())){
			setExamScheduleList(adminManager.getUsersStartAndEndDateExamSchedulesDetails(0,0,"future",false,Long.valueOf(getClassSectionId()),0));
			if(ObjectFunctions.isNotNullOrEmpty(getExamScheduleList()))
				Collections.sort(getExamScheduleList(),new ExamSchedulesStartDateComparator());
		} 
		return SUCCESS;
	}
/*
 * Removed Old AcademicYear Id process and added getUserAcademicYearId() by Prasad 04-22-2013
 */
	@Actions( {
			@Action(value = "ajaxDoGetLeaveDetailsForStudent", results = { @Result(location = "leaves/leaveHome.jsp", name = "success") }),
			@Action(value = "ajaxDoCancelLeave", results = { @Result(location = "viewLeavesList.jsp", name = "success") }) })
	public String doGetLeaveDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'doGetLeaveDetails' method");
		}
		try {
			long id = getUser().getId();
			if(getUserAcademicYearId()>0){
				List approvedLeavesList = studentManager.getLeavesListByAccountId(id, Constants.ACTIVE_STATUS, getUserCustId(),getUserAcademicYearId());
				if (!ObjectFunctions.isNullOrEmpty(approvedLeavesList)) {
					setApprovedLeavesList(approvedLeavesList);
					approvedLeavesList = null;
				}
				List pendingLeavesList = studentManager.getLeavesListByAccountId(id, Constants.PENDING_STATUS, getUserCustId(),getUserAcademicYearId());
				if (!ObjectFunctions.isNullOrEmpty(pendingLeavesList)) {
					setLeavesList(pendingLeavesList);
					pendingLeavesList = null;
				}
				List rejectedLeavesList = studentManager.getLeavesListByAccountId(id, Constants.REJECTED_STATUS, getUserCustId(),getUserAcademicYearId());
				if (!ObjectFunctions.isNullOrEmpty(rejectedLeavesList)) {
					setRejectedLeavesList(rejectedLeavesList);
					rejectedLeavesList = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxStudentProfile", results = { @Result(location = "ajaxStudentProfile.jsp", name = "success") }) ,
				@Action(value = "ajaxViewStaffDetails", results = { @Result(location = "popupViewStaffDetails.jsp", name = "success") })
	})
	public String ajaxStudentProfile() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentProfile' method");
		}
		try {
			long id = Long.valueOf(getParamValue("id"));
			if(!ObjectFunctions.isNullOrEmpty(id)){
				setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails) studentManager.get(ViewStaffPersonAccountDetails.class, id,"accountId"));
			}
			if (!ObjectFunctions.isNullOrEmpty(getViewStudentDetails())) {
				setViewStudentPersonAccountDetails(getViewStudentDetails());
			}
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxDoEditleaveReport", results = { @Result(location = "editStudentLeave.jsp", name = "success") }) })
	public String doEditStudentLeave() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doEditStudentLeave' method");
		}
		try {
			String leavesId = getParamValue("id");
			if (!StringFunctions.isNullOrEmpty(leavesId)) {
				setSelectedId(leavesId);
				Leave leaves = (Leave) studentManager.get(Leave.class, Long.valueOf(leavesId));
				if (!ObjectFunctions.isNullOrEmpty(leaves)) {
					setLeave(leaves);
				}
				leaves=null;
			}
			if (!ObjectFunctions.isNullOrEmpty(getViewStudentDetails())) {
				setViewStudentPersonAccountDetails(getViewStudentDetails());
			}
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	public ViewStudentPersonAccountDetails getViewStudentDetails()
	{
		ViewStudentPersonAccountDetails viewStudentPersonAccountDetails = userManager.getViewStudentDetails(getUser().getId(),Constants.YES_STRING);
		if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails))
		{
			return viewStudentPersonAccountDetails;
		}
		return null;
	}
	@Actions( { @Action(value = "ajaxGetSchoolFee", results = { @Result(location = "ajaxGetSchoolFee.jsp", name = "success") }) })
	public String manageSchoolFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'manageSchoolFee' method");
		}
		try {
			Student student = studentManager.getStudentById(Long.valueOf(getUser().getId()));
			if (!ObjectFunctions.isNullOrEmpty(student)) {
				Fee fee = studentManager.getFeeByStudentId(student.getClassSection().getId());
				setStudentPayment(adminManager.getPaymentStatusByStudentId(Long.valueOf(student.getId())));
				setFeeStructure(fee);
			}
			student=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 @Actions({			
			@Action(value = "ajaxStudentEvents", results = { @Result(location = "../staff/ajaxStaffEvents.jsp", name = "success") })})
	 
	public String ajaxStudentEvents() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentEvents' method");
		}
		try {
			//setObjectList(studentManager.getAllByCustId("Events",getUserCustId(),getUserAcademicYearId()));
			String studyClassId = null;
			List<ViewStaffSubjectsDetails> staffClassList = null;
			if(getUser().isSchoolStudent() || getUser().isParent()){
				if(getUser().isParent())
				{
					List<ViewStudentPersonAccountDetails> viewStudentPersonAccountDetailsList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsList)){
						setViewStudentPersonAccountDetailsList(viewStudentPersonAccountDetailsList);
						
					}
					if(!ObjectFunctions.isNullOrEmpty(getStudyClassId()))
					{
						setTempId1(Long.valueOf(getStudyClassId()));
					}
					else
					{
						if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetailsList)){
							setTempId1(viewStudentPersonAccountDetailsList.get(0).getClassSectionId());
						}
					}
					
					if(!ObjectFunctions.isNullOrEmpty(getTempId1())){
						studyClassId = String.valueOf(getTempId1());
					}
				}
				else if(getUser().isSchoolStudent())
				{
					Object[] studDetails= studentManager.get("select id,classSectionId from student where academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId());
					if(!ObjectFunctions.isNullOrEmpty(studDetails[1]))
					{
						studyClassId = studDetails[1].toString();
					}
					studDetails =null;
				}
				
				//eventsforStaffStudents(studyClassId,0);
				setObjectList(adminManager.eventsforStaffStudents(studyClassId,0,getUserCustId(),getUserAcademicYearId(),Constants.ACTIVE_STATUS)); //getting all active events
				
				setTempList(adminManager.eventsforStaffStudents(studyClassId,0,getUserCustId(),getUserAcademicYearId(),"C")); //getting all completed events
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxDoRegisterStudentEvent", results = { @Result(location = "ajaxInvitationDetails.jsp", name = "success") }) })
	public String ajaxDoRegisterStudentEvent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoRegisterStudentEvent' method");
		}
		try {
			long accountId = getUser().getId();
			String eventId = getParamValue("id");
			setAnyId(eventId);
			setCasualLeave(0);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxCancelRegistration", results = { @Result(location = "viewStudentEventsLists.jsp", name = "success") }) })
	public String ajaxCancelRegistration() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCancelRegistration' method");
		}
		try {
			ajaxStudentAcceptedEvents();
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed getting from session academicYear  and used getUserAcademicYearId() as well as code modularities done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxStudentAcceptedEvents", results = { @Result(location = "ajaxStudentEvents.jsp", name = "success") }) })
	public String ajaxStudentAcceptedEvents() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentAcceptedEvents' method");
		}
		try {
			long accountId = getUser().getId();
			StringBuffer eventIds = new StringBuffer();
			if (!ObjectFunctions.isNullOrEmpty(getViewStudentDetails())) {
				StudyClass studyClass = (StudyClass) studentManager.get(StudyClass.class, getViewStudentDetails().getClassSectionId());
				if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
					List classEventList = studentManager.getClassEventListByClassId(studyClass.getId());
					if (!ObjectFunctions.isNullOrEmpty(classEventList)) {
						eventIds.append("(");
						for(Object obj:classEventList){
							Object[] ccuserArray = (Object[]) obj;
							String eventId = ccuserArray[0].toString();
							if (!ObjectFunctions.isNullOrEmpty(eventId)) {
								eventIds.append(eventId);
								eventIds.append(",");
							}
						}
						eventIds.append("0)");
						List eventAccepetdEventsList = studentManager.getEventAcceptedEventsByEventIdAndAccountId(eventIds.toString(), accountId,Constants.YES_STRING);
						if (!ObjectFunctions.isNullOrEmpty(eventAccepetdEventsList)) {
							eventIds = new StringBuffer();
							eventIds.append("(");
							for(Object obj1:eventAccepetdEventsList){
								Object[] eventAccArray = (Object[]) obj1;
								String eventId = eventAccArray[11].toString();
								if (!ObjectFunctions.isNullOrEmpty(eventId)) {
									eventIds.append(eventId);
									eventIds.append(",");
								}
							}
							eventIds.append("0)");
						}
					}
					setObjectList(studentManager.getAllByCustId("Events",getUserCustId(), getUserAcademicYearId()));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		getJsonResult().put("eventDetailsList",getObjectList());
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoCancelRegistrationStudentEvent", results = { @Result(location = "viewStudentAcceptedEventsLists.jsp", name = "success") }) })
	public String ajaxDoCancelRegistrationStudentEvent()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCancelRegistration' method");
		}
		try {
			String eventId = getParamValue("id");
			long accountId = getUser().getId();
			studentManager.cancelRegistrationStudentEvent(Long.valueOf(eventId), accountId);
			ajaxStudentAcceptedEvents();
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}


	@Actions( { @Action(value = "ajaxChangeStudentPwd", results = {
			@Result(location = "home.jsp", name = "success"),
			@Result(location = "changeStudentPwd.jsp", name = "error") }) })
	public String ajaxChangeStudentPwd() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxChangeStudentPwd' method");
		}
		try {
			int minLength = 6;
			User user = (User) studentManager.get(User.class, getUser().getId());
			if (!ObjectFunctions.isNullOrEmpty(user)) {
				if (!PasswordUtils.passwordEncoder(getCurrentPwd(), null).equalsIgnoreCase(user.getPassword())) {
					super.addActionError("current password is not correct.");
				}
				if (getNewPwd().length() < minLength) {
					super.addActionError("Your password must be at least " + minLength + " characters long. Try again.");
				}
				if (!getNewPwd().equalsIgnoreCase(getConfirmPwd())) {
					super.addActionError("new password and confirm password should be equal.");
				}
				if (super.hasActionErrors()) {
					return "error";
				}
				user.setPassword(PasswordUtils.passwordEncoder(getNewPwd(), null));
				studentManager.save(user);
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
		}
		return SUCCESS;
	}
	/*
	* Removed prepareAcademicYearId and prepareAcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
	*/
	@Actions( {
			@Action(value = "ajaxStudentAttendance", results = { @Result(location = "class/ajaxStudentAttendance.jsp", name = "success") }),
			@Action(value = "ajaxStudentAttendanceDetails", results = { @Result(location = "class/ajaxAttendanceDetails.jsp", name = "success") }) })
	public String getStudentAttendance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'studentAttendance' method");
		}
		try {
			if(getUser().isParent()){
				if(getUserAcademicYearId()>0){
					if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
						List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studList))
							setViewStudentPersonAccountDetailsList(studList);
					}
				}
				if(getTempId1() == 0){
					if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())){
						setTempId1(getViewStudentPersonAccountDetailsList().get(0).getStudentId());
					}
				}
				if(getTempId1() > 0)
					getBaseStudentAttendance();
			}else{
				getBaseStudentAttendance();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/*
	* Removed getAcademicYearId and prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions( {
			@Action(value = "ajaxDoGetStudentLeaveDetails", results = { @Result(location = "leaves/leaveHome.jsp", name = "success") }),
			@Action(value = "ajaxGetStudentLeaveDetails", results = { @Result(location = "leaves/viewLeavesList.jsp", name = "success") }),
			@Action(value = "ajaxCancelLeaveForm", results = { @Result(location = "leaves/leaveHome.jsp", name = "success") }) })
	public String ajaxDoGetStudentLeaveDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetStudentLeaveDetails' method");
		}
		try {
		ViewStudentPersonAccountDetails studentDetails=null;
			if(getUser().isParent()){
				setToDate(new Date());
				if(!StringFunctions.isNullOrEmpty(getTempString())){
					String[] accIdAndClassId=getTempString().split("_");
					if(!ObjectFunctions.isNullOrEmpty(accIdAndClassId)){
					long accountId=0;
					long academicYearId = 0;
					long custId = 0;
					accountId=Long.valueOf(accIdAndClassId[0]);
					academicYearId=Long.valueOf(accIdAndClassId[2]);
					custId=Long.valueOf(accIdAndClassId[3]);
					studentDetails=studentManager.getStudentDetailsByAccountIdandStatus(accountId, academicYearId,Constants.YES_STRING, custId);
					if(ObjectFunctions.isNullOrEmpty(studentDetails)){
						studentDetails=studentManager.getStudentDetailsByAccountIdandStatus(getUser().getSelectedStudentId(), academicYearId,Constants.YES_STRING, custId);
					}
				}
				}else{
						if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
							List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(studList))
								setViewStudentPersonAccountDetailsList(studList);
						}
					if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList()))
						studentDetails=getViewStudentPersonAccountDetailsList().get(0);
				}
			}else{
				if(getUserAcademicYearId() > 0)
					studentDetails=studentManager.getStudentDetailsByAccountIdandStatus(getUser().getId(),getUserAcademicYearId(),Constants.YES_STRING,getUserCustId());
			}
			if(!ObjectFunctions.isNullOrEmpty(studentDetails))
			{
				setAlertSendType(getAlertSendType());
				if("HL".equalsIgnoreCase(getAlertSendType()))
				{
					generateStudentLeaveDetails(studentDetails);
				}
				else
					generateStudentLeaveDetails(studentDetails);
				
				setViewStudentPersonAccountDetails(studentDetails);
				
				studentDetails=null;
			}
		   } catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	
	@Actions( {
			@Action(value = "ajaxDoLeaveForm", results = { @Result(location = "leaves/studentLeaveForm.jsp", name = "success") })
	})
	public String ajaxDoLeaveForm() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoLeaveForm' method");
		}
		try {
			if(getTempId1() > 0){
				setViewStudentLeaveDetails((ViewStudentLeaveDetails)studentManager.get(ViewStudentLeaveDetails.class,"leavesId="+getTempId1()+" and custId="+getTempId2()+" and academicYearId="+getAcademicYearId()));
				if(!ObjectFunctions.isNullOrEmpty(getViewStudentLeaveDetails())){
					setAcademicYearId(getViewStudentLeaveDetails().getAcademicYearId());
					setCustId(getViewStudentLeaveDetails().getCustId());
					if(getAcademicYearId() > 0)
						loadAcademicYearStartDateAndDates(getAcademicYearId());
					    Leave leave = new Leave();
					    leave.setSupervisorId(getViewStudentLeaveDetails().getLeaveSupervisorId());
					    setLeave(leave);
						prepareGetClassTeatherByclassSectionId(getViewStudentLeaveDetails().getClassSectionId());
				}
			}else{
				if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
					List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(studList))
						setViewStudentPersonAccountDetailsList(studList);
				}
				if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList()))
				{
					setViewStudentPersonAccountDetails(getViewStudentPersonAccountDetailsList().get(0));
					setAcademicYearId(getViewStudentPersonAccountDetailsList().get(0).getAcademicYearId());
					setCustId(getViewStudentPersonAccountDetailsList().get(0).getCustId());
					if(getAcademicYearId() > 0){
						loadAcademicYearStartDateAndDates(getAcademicYearId());
						prepareGetClassTeatherByclassSectionId(getViewStudentPersonAccountDetailsList().get(0).getClassSectionId());
					}
					ViewStudentLeaveDetails viewStudentLeaveDetails = new ViewStudentLeaveDetails();
					viewStudentLeaveDetails.setStatus("N");
					setViewStudentLeaveDetails(viewStudentLeaveDetails);
				}
				
			}
			} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed getUsrChgedAcademicId and prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	public void prepareGetClassTeatherByclassSectionId(long classSectionId){
		try {
			setClassTeacherList(null);
			ClassTeacher classTeacher = adminManager.getClassTeacherByStudyClassIdAndClassTeacherStatus(classSectionId,Constants.YES_STRING,getUserAcademicYearId());
			if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
				 getClassTeacherList().add(classTeacher);
			}else{
				List<ViewUserRoles> schoolAdmins=null;
				if("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()))
					schoolAdmins=adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_ADMIN, getUserCustId(),Constants.NO_STRING);
				else if ("ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole()))
					schoolAdmins=adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_ADMINOFFICER, getUserCustId(),Constants.NO_STRING);
				else if (getUser().isParent())
					schoolAdmins=adminManager.getViewUserRolesByRoleNameAndCustId(Constants.SCHOOL_PRINCIPAL, getUserCustId(),Constants.NO_STRING);
				if(ObjectFunctions.isNotNullOrEmpty(schoolAdmins)){
					for (ViewUserRoles classTeachers  : schoolAdmins )
					{			                	
						getSelectboxMap().put(classTeachers.getAcountId(), classTeachers.getPersonFullName()+" ("+classTeachers.getRoleDescription()+")");
						classTeachers=null;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	/*
	* Removed getAcademicYearId and used getUserAcademicYearId() as well as code modularity and remove commented code done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxApplyLeaveForm", results = { @Result(location = "leaves/leaveHome.jsp", name = "success") }) })
	public String ajaxApplyLeaveForm() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxApplyLeaveForm' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getViewStudentLeaveDetails()) && getUserAcademicYearId() > 0 && StringFunctions.isNotNullOrEmpty(getAnyId())) {
				String[] accIdAndClassId=getAnyId().split("_");
				if(!ObjectFunctions.isNullOrEmpty(accIdAndClassId)){
					long accountId=0;
					long academicYearId = 0;
					long custId = 0;
					String hostelLeave ="";
					if(!StringFunctions.isNullOrEmpty(getParamValue("hostelLeave")))
					{
						hostelLeave=getParamValue("hostelLeave");
					}
					accountId=Long.valueOf(accIdAndClassId[0]);
					academicYearId=Long.valueOf(accIdAndClassId[2]);
					custId=Long.valueOf(accIdAndClassId[3]);
					AcademicYear academicYear= (AcademicYear) studentManager.get(AcademicYear.class, academicYearId);
					User user = (User) studentManager.get(User.class, accountId);
					Leave leaveObj= new Leave();
					leaveObj.setLeaveType(getViewStudentLeaveDetails().getLeaveType());
					leaveObj.setLeavesCount(getViewStudentLeaveDetails().getLeavesCount());
					leaveObj.setStartDate(getViewStudentLeaveDetails().getStartDate());
					leaveObj.setEndDate(getViewStudentLeaveDetails().getEndDate());
					leaveObj.setDescription(getViewStudentLeaveDetails().getDescription());
					leaveObj.setHalfDay(getViewStudentLeaveDetails().isHalfDay());
					leaveObj.setSessionType(getViewStudentLeaveDetails().getSessionType());
					if(!ObjectFunctions.isNullOrEmpty(getLeave()) && getLeave().getSupervisorId()>0)
						leaveObj.setSupervisorId(getLeave().getSupervisorId());
					if(!ObjectFunctions.isNullOrEmpty(academicYear) && !ObjectFunctions.isNullOrEmpty(user)){
						int responseCode =staffManager.submitOrEditLeaves(leaveObj,academicYear,user,getUserCustId(),getTempId1(),"WEB",getParamValue("hostelLeave"));
						if(responseCode == 0)
							super.addActionMessage("Leave posted successfully.");
						else if(responseCode == 6)
							super.addActionMessage("Leave updated successfully.");
						else if(responseCode == 3)
							super.addActionError("The selected days are part of holidays.Please change selections.");

					}
				}
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxDoGetStudentLeaveDetails();

		return SUCCESS;
	}
	/*
	* Removed getting the session academicYearId and used getUserAcademicYearId() as well as code modularity and remove commented code done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxDoEditleaveReportForParent", results = { @Result(location = "parent/editParentLeave.jsp", name = "success") }) })
	public String doEditStudentLeaveForParent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doEditStudentLeaveForParent' method");
		}
		try {
			String leavesId = getParamValue("id");
			if (!StringFunctions.isNullOrEmpty(leavesId)) {
				setSelectedId(leavesId);
				Leave leaves = (Leave) studentManager.get(Leave.class, Long.valueOf(leavesId));
				if (!ObjectFunctions.isNullOrEmpty(leaves)) {
					setLeave(leaves);
				}
			}
			ViewStudentPersonAccountDetails viewStudentPersonAccountDetails = (ViewStudentPersonAccountDetails) studentManager.get(ViewStudentPersonAccountDetails.class, getLeave().getUser().getId());
			if (!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails)) {
				setViewStudentPersonAccountDetails(viewStudentPersonAccountDetails);
			}
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
	*/
	@Actions( {
			@Action(value = "ajaxDoGetChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/viewChildAbsentLeavesList.jsp", name = "success") }),
			@Action(value = "ajaxDoCancelChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/viewChildAbsentLeavesList.jsp", name = "success") }) })
	public String doGetChildAbsentLeaveDetailsForParent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doGetChildAbsentLeaveDetailsForParent' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getUser().getId())){
				long id = getUser().getId();
				setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails) studentManager.get(ViewStaffPersonAccountDetails.class, id,"accountId"));
				List childrens = studentManager.getMyChildren(getUser().getId(),getUserCustId(),getAcademicYearId(),Constants.YES_STRING);
				if (!ObjectFunctions.isNullOrEmpty(childrens)) {
					Iterator childrenListIterator = childrens.iterator();
					for (Iterator childrenIterator = childrenListIterator; childrenIterator.hasNext();) {
						ViewStudentPersonAccountDetails children = (ViewStudentPersonAccountDetails) childrenIterator.next();
						List pendingLeavesList = studentManager.getLeavesListByAccountId(children.getAccountId(),Constants.EVENT_FREQUENCY_WEEKLY,getUserCustId(),getUserAcademicYearId());
						if (!ObjectFunctions.isNullOrEmpty(pendingLeavesList)) {
							for(Object obj:pendingLeavesList){
								Leave leave = (Leave) obj;
								leave.setChildRollNo(children.getRollNumber());
								leave.setChildAccountId(children.getAccountId());
								getLeavesList().add(leave);
								leave = null;
							}
						}
						children = null;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( {
			@Action(value = "ajaxDoEditChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/editChildAbsentLeave.jsp", name = "success") }),
			@Action(value = "ajaxDoApplyChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/applyChildAbsentLeave.jsp", name = "success") })
	})
	public String doEditChildAbsentLeaveDetailsForParent()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoEditChildAbsentLeaveDetailsForParent' method");
		}
		try {
			String leavesId = getParamValue("id");
			if (!StringFunctions.isNullOrEmpty(leavesId)) {
				setSelectedId(leavesId);
				Leave leaves = (Leave) studentManager.get(Leave.class, Long.valueOf(leavesId));
				if (!ObjectFunctions.isNullOrEmpty(leaves)) {
					setLeave(leaves);
				}
			}
			ViewStudentPersonAccountDetails viewStudentPersonAccountDetails = (ViewStudentPersonAccountDetails) studentManager.get(ViewStudentPersonAccountDetails.class, getLeave().getUser().getId());
			if (!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails)) {
				setViewStudentPersonAccountDetails(viewStudentPersonAccountDetails);
			}
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed commented code done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxEditChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/viewChildAbsentLeavesList.jsp", name = "success") }) })
	public String ajaxEditChildAbsentLeaveDetailsForParent()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditChildAbsentLeaveDetailsForParent' method");
		}
		try {
			String leavesId = getSelectedId();
			Leave leaves = (Leave) studentManager.get(Leave.class, Long.valueOf(leavesId));
			if (!ObjectFunctions.isNullOrEmpty(getLeave())) {
				leaves.setApprovalsComment(getLeave().getApprovalsComment());
				leaves.setLeaveStatus(Constants.ADD_RIDE_SUBJECT);
				leaves.setLastUpdatedDate(new Date());
				leaves.setLastAccessDate(new Date());
				leaves.setLastUpdatedById(getUser().getId());
				leaves.setLeaveType(getLeave().getLeaveType());
			}
			studentManager.save(leaves);
			super.addActionMessage("Leave updated successfully");
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
		}
		doGetChildAbsentLeaveDetailsForParent();
		return SUCCESS;
	}
	/*
	* Removed getAcademicYearId and prepareAcademicYearId and used getUserAcademicYearId() as well as code modularity done by venkatesh - 04-23-2013
	*/
	@Actions( {
			@Action(value = "ajaxDoGetMyMessagesChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/viewMyMessagesHome.jsp", name = "success") }),
			@Action(value = "doGetMyMessagesChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/ajaxStudentInbox.jsp", name = "success") }),
			@Action(value = "ajaxCancelChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/viewMyMessagesChildAbsentLeavesList.jsp", name = "success") }) })
	public String doGetMyMessagesChildAbsentLeaveDetailsForParent()
			throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'doGetMyMessagesChildAbsentLeaveDetailsForParent' method");
		}
		try {
			long id = getUser().getId();
			setViewStaffPersonAccountDetails((ViewStaffPersonAccountDetails) studentManager.get(ViewStaffPersonAccountDetails.class, id,"accountId"));
			List childrens = studentManager.getMyChildren(getUser().getId(),getUserCustId(),getUserAcademicYearId(),Constants.YES_STRING);
			if (!ObjectFunctions.isNullOrEmpty(childrens)) {
				for(Object obj:childrens){
					ViewStudentPersonAccountDetails children = (ViewStudentPersonAccountDetails) obj;
					List pendingLeavesList = studentManager.getViewStudentLeaveDetailsByAccountId(children.getAccountId(), Constants.EVENT_FREQUENCY_WEEKLY,getUserCustId());
					if (!ObjectFunctions.isNullOrEmpty(pendingLeavesList)) {
						setViewStudentLeaveDetailsList(pendingLeavesList);
					}
					children = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxApplyChildAbsentLeaveDetailsForParent", results = { @Result(location = "parent/viewMyMessagesChildAbsentLeavesList.jsp", name = "success") })
	public String ajaxApplyChildAbsentLeaveDetailsForParent()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log
					.debug("Entering 'ajaxApplyChildAbsentLeaveDetailsForParent' method");
		}
		try {
			String leavesId = getSelectedId();
			Leave leaves = (Leave) studentManager.get(Leave.class, Long.valueOf(leavesId));
			if (!ObjectFunctions.isNullOrEmpty(getLeave())) {
				leaves.setApprovalsComment(getLeave().getApprovalsComment());
				leaves.setLeaveStatus(Constants.ADD_RIDE_SUBJECT);
				leaves.setLastUpdatedDate(new Date());
				leaves.setLastAccessDate(new Date());
				leaves.setLastUpdatedById(getUser().getId());
				leaves.setLeaveType(getLeave().getLeaveType());
			}
			studentManager.save(leaves);
			super.addActionMessage("Your Leave updated successfully");
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
		}
		doGetMyMessagesChildAbsentLeaveDetailsForParent();
		return SUCCESS;
	}
	/*
	* Code modularityis and remocve the commented code done by venkatesh - 04-23-2013
	*/
	@Actions( {
			@Action(value = "ajaxDoGetTeacherMessagesForParent", results = { @Result(location = "parent/viewMyMessagesCountHome.jsp", name = "success") }),
			@Action(value = "doGetTeacherMessagesForParent", results = { @Result(location = "parent/viewMyMessagesCountHome.jsp", name = "success") }),
			@Action(value = "doGetMyMessagesChildMessageDetailsForParent", results = { @Result(location = "parent/ajaxStudentInbox.jsp", name = "success") }),
			@Action(value = "ajaxDoGetTeacherMessagesCountForParent", results = { @Result(location = "parent/viewMyMessagesCountHome.jsp", name = "success") }),
			@Action(value = "ajaxDoGetTeacherAnswersList", results = { @Result(location = "viewTeacherAnswersListHome.jsp", name = "success") })

	})
	public String doGetTeacherMessagesForParent() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'doGetTeacherMessagesForParent' method");
		}
		try {
			List myMessagesList = null;
			List myFeeMessagesList = null;
			myMessagesList = studentManager.getMyMessagesByReceiverAccountIdAndStatus(String.valueOf(getUser().getId()), getUserCustId(), Constants.ACTIVE_STATUS);
			myFeeMessagesList = studentManager.getMyMessagesByReceiverAccountIdAndStatus(String.valueOf(getUser().getId()), getUserCustId(), "FM");
			if(!ObjectFunctions.isNullOrEmpty(myMessagesList)){
				  getMyReplyMessages().addAll(myMessagesList);
				}
			if(!ObjectFunctions.isNullOrEmpty(myFeeMessagesList)){
			  getMyReplyMessages().addAll(myFeeMessagesList);
			}
			if (!ObjectFunctions.isNullOrEmpty(getMyReplyMessages())) {
				setStudentMessagesList(ConvertUtil.convertListToSet(getMyReplyMessages()));
			 }
			if (!ObjectFunctions.isNullOrEmpty(getStudentMessagesList())) {
				for(Object obj:getStudentMessagesList()){
					Messages messages = (Messages) obj;
					log.debug(messages);
					/*if(!ObjectFunctions.isNullOrEmpty(messages)){
					//	long senderAccountId=Long.valueOf(messages.getSenderAccountId());
					}*/
					messages = null;
				}
				Collections.sort(getMessagesList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxViewReadMoreTeacherMessagesForParent", results = { @Result(location = "parent/expandedTeacherMessage.jsp", name = "success") }) })
	public String ajaxViewReadMoreTeacherMessagesForParent()
			throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewReadMoreTeacherMessagesForParent' method");
		}
		try {
			String messageId = getParamValue("id");
			Messages messages = (Messages) studentManager.get(Messages.class,Long.valueOf(messageId));
			if (!ObjectFunctions.isNullOrEmpty(messages)) {
				ViewStaffPersonAccountDetails staffDetails = (ViewStaffPersonAccountDetails) studentManager.get(ViewStaffPersonAccountDetails.class, Long.valueOf(messages.getSenderAccountId()),"accountId");
				messages.setFullPersonName(staffDetails.getPersonFullName());
				setMessages(messages);
			}

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxDoSendMyMessagesToTeacher", results = { @Result(location = "parent/getStaffsList.jsp", name = "success") }) })
	public String ajaxDoSendMyMessagesToTeacher() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSendMyMessagesToTeacher' method");
		}

		try {
			String accountId=getParamValue("accountId");
			ViewStudentPersonAccountDetails children = (ViewStudentPersonAccountDetails) studentManager.get(ViewStudentPersonAccountDetails.class, Long.valueOf(accountId));
			if (!ObjectFunctions.isNullOrEmpty(children)) {
				setViewStudentPersonAccountDetails(children);
				StudyClass studyClass = (StudyClass) studentManager.get(StudyClass.class,getViewStudentPersonAccountDetails().getClassSectionId());
				if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
					ClassTeacher classTeacher = adminManager.getClassTeacherByStudyClassIdAndClassTeacherStatus(studyClass.getId(), Constants.YES_STRING,studyClass.getAcademicYear().getId());
					if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
						String classTeacherAccountId = classTeacher.getStaff().getAccount().getUsername();
						if (!StringFunctions.isNullOrEmpty(classTeacherAccountId)) {
							ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = (ViewStaffPersonAccountDetails) studentManager.get(ViewStaffPersonAccountDetails.class,classTeacher.getStaff().getAccount().getId(),"accountId");
							if (!StringFunctions.isNullOrEmpty(classTeacherAccountId)) {
								getAllStudentsList().add(viewStaffPersonAccountDetails);
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

	/*
	* Remove the commented code done by venkatesh - 04-23-2013
	*/
	@Actions( {
			@Action(value = "ajaxFindStaffUsingNameOrId", results = { @Result(location = "parent/sendMessageToStaff.jsp", name = "success") }),
			@Action(value = "ajaxFindStaffProfileUsingNameOrId", results = { @Result(location = "ajaxStaffProfile.jsp", name = "success") })

	})
	public String ajaxFindStaffUsingNameOrId() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFindStaffUsingNameOrId' method");
		}
		try {
			String username = getParamValue("username");
			String staffFirstName = getParamValue("staffFirstName");
			String studentRollNumber = getParamValue("viewStudentPersonAccountDetails.rollNumber");
			ViewStaffPersonAccountDetails staffDetails = staffManager.getFindStaffByIdOrName(username, staffFirstName,getUserCustId());
			if (!ObjectFunctions.isNullOrEmpty(staffDetails)) {
				setViewStaffPersonAccountDetails(staffDetails);
				setAnyId(studentRollNumber);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/*
	* User getCustomerByCustId and remove the commented code done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxStaffSendMessageToStaff", results = { @Result(location = "parent/viewTeacherMessagesList.jsp", name = "success") })

	})
	public String ajaxStaffSendMessageToStaff() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStaffSendMessageToParent' method");
		}

		try {
			Customer customer;
			if (!ObjectFunctions.isNullOrEmpty(getMessages())) {
				customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(customer)){
				     getMessages().setCustomer(customer);
				}
				getMessages().setLastUpdatedById(getUser().getId());
				staffManager.save(getMessages());
				super.addActionMessage("Message posted Successfully");
				customer=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		doGetTeacherMessagesForParent();
		return SUCCESS;
	}

	@Actions( {
			@Action(value = "ajaxDoReplayMessage", results = { @Result(location = "parent/replayMessage.jsp", name = "success") }),
			@Action(value = "ajaxDoReplayMessageToStudent", results = { @Result(location = "ajaxReplyMessage.jsp", name = "success") }) })
	public String ajaxDoReplayMessage() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFindStaffUsingNameOrId' method");
		}
		try {
			String messageId = getParamValue("id");
			if (!StringFunctions.isNullOrEmpty(messageId)) {
				Messages messages = (Messages) studentManager.get(Messages.class, Long.valueOf(messageId));
				if (!ObjectFunctions.isNullOrEmpty(messages)) {
					messages.setTitle(null);
					messages.setMessageDescription(null);
					setMessages(messages);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed Commented Lines done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxReplayMessageToStaff", results = { @Result(location = "parent/viewTeacherMessagesList.jsp", name = "success") })

	})
	public String ajaxReplayMessageToStaff() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStaffSendMessageToParent' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getMessages())) {
				getMessages().setMessageDate(new Date());
				getMessages().setCreatedDate(new Date());
				getMessages().setLastUpdatedDate(new Date());
				getMessages().setLastAccessDate(new Date());
				staffManager.save(getMessages());
				super.addActionMessage("Message posted Successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		doGetTeacherMessagesForParent();
		return SUCCESS;
	}

	@Override
	public String rssFeedParserMessage() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'rssFeedParserMessage' method");
		}
		try {
			List rssFeedsList = new ArrayList();
			RSSFeedParser parser = new RSSFeedParser("http://www.indiaedunews.net/rss/today.xml");
			Feed feed = parser.readFeed();
			for (FeedMessage message : feed.getMessages()) {
				if (!ObjectFunctions.isNullOrEmpty(message)) {
					message.setPubDate(feed.getPubDate());
					rssFeedsList.add(message);
				}
			}
			setObjectList(rssFeedsList.subList(0, 3));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}

	/*
	* Removed getAcademicYearId and prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxGetClassMatesList", results = { @Result(location = "viewMyClassMatesListsHome.jsp", name = "success") })

	})
	public String ajaxGetStudentssList() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentsList' method");
		}

		try {
			ViewStudentPersonAccountDetails myDetails = null;
			List<ViewStudentPersonAccountDetails> studentsList = null;
			Calendar cale = Calendar.getInstance();
			cale.setTime(new Date());
			int month = cale.get(Calendar.MONTH) + 1;
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
			myDetails = studentManager.getStudentDetailsByAccountIdandStatus(getUser().getId(), getUserAcademicYearId(),Constants.YES_STRING, getUserCustId());
			if (!ObjectFunctions.isNullOrEmpty(myDetails)) {
				//if (Long.valueOf((String) getSession().getAttribute("newYear")) == getAcademicYearId()) 
				if (myDetails.getAcademicYearId() == getUserAcademicYearId())
				{
					studentsList = staffManager.getViewStudentPersonAccountDetailsByStudyClassIdandAccount(myDetails.getClassSectionId(),Constants.YES_STRING, getUser().getId(),String.valueOf(getUserAcademicYearId()));
				} else 
				{
					studentsList = staffManager.getViewStudentPersonAccountDetailsByStudyClassIdandAccount(myDetails.getClassSectionId(),Constants.NO_STRING, getUser().getId(),String.valueOf(getUserAcademicYearId()));
				}

				if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
					Collections.sort(studentsList);
					setClassStudentsList(studentsList);
					for (ViewStudentPersonAccountDetails studentDetails : studentsList) {
						if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
							Calendar doBCale = Calendar.getInstance();
							if (!ObjectFunctions.isNullOrEmpty(studentDetails.getDateOfBirth())) {
								doBCale.setTime(studentDetails.getDateOfBirth());
								int doBCaleMonth = doBCale.get(Calendar.MONTH) + 1;
								if (doBCaleMonth == month) {
									studentDetails.setTodayDateDOB("todayBirthDay");
								}
							}
							getAllStudentsList().add(studentDetails.getRollNumber());
							//getStudentsList().add(studentDetails.getFirstName());
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed prepareAcademicYearId and commented code done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxFindStudentUsingNameOrId", results = { @Result(location = "ajaxStudentProfileHome.jsp", name = "success") }),
		
		@Action(value = "ajaxGetSelectedStudent", results = { @Result(location = "ajaxStudentProfile.jsp", name = "success") })
	})
	public String ajaxFindStudentUsingNameOrId() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFindStudentUsingNameOrId' method");
		}

		try
		{
		List<ViewStudentPersonAccountDetails> studentsDetailsList =null;
		if(!StringFunctions.isNullOrEmpty(getAnyId())){
			studentsDetailsList =  studentManager.getAll(ViewStudentPersonAccountDetails.class,"accountId="+Long.valueOf(getAnyId())+" and accountExpired='"+Constants.NO_STRING+"' and custId='"+getUserCustId() +"' and academicYearId="+getUserAcademicYearId());
		}else{
			studentsDetailsList =  studentManager.getAll(ViewStudentPersonAccountDetails.class,"firstName like '%"+getParamValue("stuFirstName")+"%' and accountExpired='"+Constants.NO_STRING+"' and custId="+getUserCustId()  +" and academicYearId="+getUserAcademicYearId());
		}
		if (!ObjectFunctions.isNullOrEmpty(studentsDetailsList)) 
		{
			if (studentsDetailsList.size() == 1)
			{
				for(ViewStudentPersonAccountDetails studentsDetails : studentsDetailsList)
				{
					setViewStudentPersonAccountDetails(studentsDetails);
					User account = (User) staffManager.get(User.class,studentsDetails.getAccountId());
					if (!ObjectFunctions.isNullOrEmpty(studentsDetails)) {
						setUser(account);
					}
				}
			}
			else
			{
				setViewStudentPersonAccountDetailsList(studentsDetailsList);
			}
		}
	}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed getting the session AcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
	*/
	public String ajaxStudentBirthDays() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentBirthDays' method");
		}
		try {
			Student studentDetails=null; 
			long accountId = getUser().getId();
			if(accountId != 0 && getUserAcademicYearId()>0){
				if(getUserAcademicYearId()>0){
					 studentDetails = studentManager.getStudentByAccountId(accountId,getUserAcademicYearId(),getUserCustId());
				}
			}
			if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
				StudyClass classDetails = studentDetails.getClassSection();
				if (!ObjectFunctions.isNullOrEmpty(classDetails)) {
					List<ViewStudentClassDetails> studentsBirthdaysList = studentManager.getAllStudentsBirthDays(classDetails.getId(),accountId);
					if (!ObjectFunctions.isNullOrEmpty(studentsBirthdaysList)) {
						for (ViewStudentClassDetails birthdayStudent : studentsBirthdaysList) {
							if (!ObjectFunctions.isNullOrEmpty(birthdayStudent)) {
								String currentDayDate = new SimpleDateFormat("MM").format(birthdayStudent.getDateOfBirth());
								 List<ViewStudentPersonAccountDetails> studentsList=staffManager.getAllStudentBirthDaysList(currentDayDate,getUserCustId());
								 if(!ObjectFunctions.isNullOrEmpty(studentsList)){
										for (ViewStudentPersonAccountDetails viewStudentBirthDetails:studentsList) {
											 if(!ObjectFunctions.isNullOrEmpty(viewStudentBirthDetails)){
												Date stBirthDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,viewStudentBirthDetails.getDateOfBirth()));
												String studentDay = new SimpleDateFormat("dd").format(stBirthDate);
												String currentStudentBirthDay = new SimpleDateFormat("dd").format(new Date());
												int currentDay1=Integer.parseInt(studentDay);
												int birthDayDate1=Integer.parseInt(currentStudentBirthDay);
												if((currentDay1)>=(birthDayDate1)){
													getBirthDaysListSet().add(viewStudentBirthDetails);
												}
												viewStudentBirthDetails=null;
											  }
										}
								 }
								 birthdayStudent=null;
							}
						}
						studentsBirthdaysList=null;
					}
					}
				}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Override
	@Actions( { @Action(value = "ajaxBirthDayWishes", results = { @Result(location = "ajaxBirthDayWishes.jsp", name = "success") }) })
	public String ajaxBirthDayWishes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxBirthDayWishes' method");
		}
		try {
			String recieveAccountId = getParamValue("accountId");
			String rollNum = getParamValue("rollNumber");
			setSelectedId(recieveAccountId);
			setAnyId(rollNum);
			setWishTitle("Happy BirthDay");
			setEditor("Wish you many more happy returns of the day");

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSendWishesToFriend", results = { @Result(location = "ajaxViewMyMessages.jsp", name = "success") }) })
	public String ajaxSendWishesToFriends() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendWishesToFriend' method");
		}
		try {
			String rollNum = getParamValue("rollNumber");
			Messages messages = new Messages();
			messages.setUsername(rollNum);
			messages.setCreatedDate(new Date());
			messages.setLastUpdatedDate(new Date());
			messages.setLastAccessDate(new Date());
			messages.setCreatedById(getUser().getId());
			studentManager.save(messages);
			super.addActionMessage("wishes sent successfully.");
			ajaxDoGetMyMessages();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	/*
	* Removed getAcademicYearId and prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxDoViewStudentDetails", results = { @Result(location = "popupViewStudentDetails.jsp", name = "success") }) })
	public String ajaxDoViewStudentDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewStudentDetails' method");
		}
		try {
			String studentAccountId = getParamValue("id");
			setTempBoolean(getUser().isParent());
			if (!StringFunctions.isNullOrEmpty(studentAccountId) && getUserAcademicYearId()!=0) {
				if (getUserAcademicYearId()>0) {
					setStudent(studentManager.getStudentByAccountId(Long.valueOf(studentAccountId),getUserAcademicYearId(),getUserCustId()));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({
	    @Action(value = "ajaxDoGetMyMessages", results = { @Result(location = "ajaxViewMyMessages.jsp", name = "success") }),
	    @Action(value = "doGetMyMessages", results = { @Result(location = "ajaxStudentInbox.jsp", name = "success") })
	})
		public String ajaxDoGetMyMessages() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetMyMessages' method");
		   }
		try
		{
			long accountId = getUser().getId();
			Messages messages = studentManager.getMessagesByAccountId(String.valueOf(accountId));
			if(!ObjectFunctions.isNullOrEmpty(messages)){
				String senderId="1"; 
				List senderMessgesList=studentManager.getMyMessagesBySenderAccountIdAndStatus(String.valueOf(senderId),getUserCustId(),"N");
				List receivedMessageList=studentManager.getMyreplyMessagesAndStatus(String.valueOf(getUser().getId()),getUserCustId(),"N");
				if(!ObjectFunctions.isNullOrEmpty(senderMessgesList)){
				  getMyReplyMessages().addAll(senderMessgesList);
				}
				if(!ObjectFunctions.isNullOrEmpty(receivedMessageList)){
				  getMyReplyMessages().addAll(receivedMessageList);
				}
				if (!ObjectFunctions.isNullOrEmpty(getMyReplyMessages())) {
					setStudentMessagesList(null);
					setStudentMessagesList(ConvertUtil.convertListToSet(getMyReplyMessages()));
				 }
			}
			setMessagesList(studentManager.getMyMessagesByReceiverAccountIdAndStatus(String.valueOf(getUser().getId()),getUserCustId(),null));
			setUnReadMsgsList(studentManager.getMyUnreadMessagesAndStatus(String.valueOf(getUser().getId()),getUserCustId(),"UR"));
			ajaxStudentBirthDays();
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
			log.debug("Entering 'ajaxDoGetStudentOrStaffPorfileDetails' method");
		}
		try {
			ajaxDoGetStudentPorfileDetails();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	/*
	* Removed the commented code done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "getStudentCalendar", results = { @Result(location = "ajaxStudentCalendar.jsp", name = "success") }),
				@Action(value = "doStudentCalender", results = { @Result(location = "ajaxStudentCalendar.jsp", name = "success") })
	})
	@Action(value = "ajaxAddEvent", results = { @Result(location = "addEvent.jsp", name = "success") })
	public String addEvent() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddEvent' method");
		}
		try {
			List<SchoolHolidays> holidayBoardList = null;
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
			//List holidayBoardList = staffManager.getAllHolidayBoardMessagesList(getUserCustId());
			if(!ObjectFunctions.isNullOrEmpty(holidayBoardList))	
			{
				setHolidayBoardMessagesList(holidayBoardList);
				Collections.sort(getHolidayBoardMessagesList());
			}
			//transportInformation();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions({
		@Action(value = "ajaxStaffAllEvents", results = { @Result(location = "viewStaffEventsLists.jsp", name = "success") }) })
		public String ajaxStaffAllEvents() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxStaffAllEvents' method");
			}
			try
			{
			    setObjectList(studentManager.getAllByCustId("Events",getUserCustId(), getUserAcademicYearId()));
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	
	@Actions( { @Action(value = "eventsXml", results = { @Result(location = "ajaxStudentCalendar.jsp", name = "success") }) })
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
			ajaxViewSchoolSettingsHolidays();
			scDOM.setObjectList(getObjectList());
			scDOM.setHolidayBoardList(getHolidayBoardMessagesList());
			document = scDOM.emsEvents();
			DOMUtil.writeXmlToFile(toClient, document);
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	/*
	* Removed commented codedone by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxReplyMessageToStudent", results = { @Result(location = "ajaxReplyMessage.jsp", name = "success") }) })
	public String ajaxReplayMessageToStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxReplyMessageToStudent' method");
		}
		try {
			Messages messages = new Messages();
			messages.setLastUpdatedDate(new Date());
			messages.setLastAccessDate(new Date());
			messages.setCreatedDate(new Date());
			messages.setCreatedById(getUser().getId());
			studentManager.save(messages);
			setMessages(messages);
		} 
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value = "ajaxViewReadMoreBirthDayMessagesForStudent", results = { @Result(location = "ajaxExpandedStudentMessage.jsp", name = "success") })
	})
		public String ajaxViewReadMoreBirthDayMessagesForStudent() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewReadMoreBirthDayMessagesForStudent' method");
		}
		try
		{
			String newStatus="R";
			Messages messages=null;
			String messageId = getParamValue("id");
			setMyReplyMessages(studentManager.getViewreplyMessagesAndStatus("Y", getUserCustId(), messageId));
			messages = (Messages)studentManager.get(Messages.class, Long.valueOf(messageId));
			if(!ObjectFunctions.isNullOrEmpty(messages)){	
				ViewStudentPersonAccountDetails studentDetails = (ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class,Long.valueOf(messages.getSenderAccountId()));
            	messages.setFullPersonName(studentDetails.getPersonFullName());
            	setMessages(messages);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}    
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions({
		@Action(value = "ajaxViewNoticeBoardMessagesForStudent", results = { @Result(location = "ajaxExpandedStudentMessage.jsp", name = "success") })
	})
		public String ajaxViewNoticeBoardMessagesForStudent() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewNoticeBoardMessagesForStudent' method");
		}
		try
		{
			if (getUser().isParent()) 
			{
				List<ViewStudentPersonAccountDetails> myChildrensList = studentManager.getMyChildren(getUser().getId(),getUserCustId(),getUserAcademicYearId(),Constants.YES_STRING);
				for(ViewStudentPersonAccountDetails myChildren : myChildrensList)
				{
					long accountId  = myChildren.getAccountId();
					List noticeBoardMessagesList = noticeBoardMessages(accountId);
					if(!ObjectFunctions.isNullOrEmpty(noticeBoardMessagesList)){
						getNoticeBoardMessagesList().addAll(noticeBoardMessagesList);
					}
				}
			}
			else if (getUser().isSchoolStudent()) 
			{
				List noticeBoardMessagesList = noticeBoardMessages(getUser().getId());
				if(!ObjectFunctions.isNullOrEmpty(noticeBoardMessagesList)){
					getNoticeBoardMessagesList().addAll(noticeBoardMessagesList);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed getting the session AcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	public List noticeBoardMessages(long accountId)
	{
		ViewStudentPersonAccountDetails studentDetails = (ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class,accountId);
		if(!ObjectFunctions.isNullOrEmpty(studentDetails))
		{
			List noticeBoardMessagesList = studentManager.getAllNoticeBoardMessagesList("NB",studentDetails.getClassAndSection(),getUserCustId(),getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(noticeBoardMessagesList)){
				return noticeBoardMessagesList;
			}
		}
		return null;
	}
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxAcademicSchoolSettings", results = { @Result(location = "academic/ajaxCreateSchoolSettings.jsp", name = "success") }) })
	public String ajaxAcademicSchoolSettings() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAcademicSchoolSettings' method");
		}
		try {
			AcademicYear academicYear=(AcademicYear)studentManager.get(AcademicYear.class,getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate()) && !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
				setAcademicYear(academicYear);	
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() and commented code done by venkatesh - 04-23-2013
	*/
	@Actions( {
		@Action(value = "ajaxViewSchoolSettingsHolidays", results = { @Result(location = "academic/ajaxViewSchoolHolidaysList.jsp", name = "success") })
		 })
	public String ajaxViewSchoolSettingsHolidays() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewSchoolSettingsHolidays' method");
		}
		try {
				ajaxAcademicSchoolSettings();
				//List holidayList =adminManager.getAllHolidaysListByAcademicYearId("H", getUserCustId(),getUserAcademicYearId());
				List holidayList =getAllHolidaysListByAcademicYearId("H", getUserCustId(),getUserAcademicYearId(),0);
				if (!ObjectFunctions.isNullOrEmpty(holidayList)) {
					setHolidayBoardMessagesList(holidayList);
					Collections.sort(getHolidayBoardMessagesList());
					setToDate(new Date());
				}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() as well as code modularity done by venkatesh - 04-23-2013
	*/
	public String ajaxViewStudentSubjectsToParent() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStudentSubjects' method");
		}

		try {
			Student studentDetails=null;
			StudyClass studyClass=null;
			long accountId = getUser().getId();
			if(accountId !=0 && getUserAcademicYearId() > 0){
				setObjectList(studentManager.getMyChildren(accountId,getUserCustId(),getUserAcademicYearId(),Constants.YES_STRING));
				if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
					for (Object  typesIterator: getObjectList()) {
						ViewStudentPersonAccountDetails parentStudentClass = (ViewStudentPersonAccountDetails) typesIterator;
						if(!ObjectFunctions.isNullOrEmpty(parentStudentClass)){
							long parentAccountId = parentStudentClass.getAccountId();
							if(parentAccountId!=0){
								studentDetails = studentManager.getStudentByAccountId(parentAccountId,getUserAcademicYearId(),getUserCustId());
								if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
									studyClass = studentDetails.getClassSection();
									if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
										setStudyClass(studentManager.getStudentSubjects(studyClass.getClassName(), studyClass.getSection()));
										setClassTeacherList(staffManager.getStudyClassSubjectByClassid(studyClass.getId()));
										studyClass=null;
									}
								}
							}
							parentStudentClass=null;
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxDoGetMyFeeDetails", results = { @Result(location = "ajaxStudentFeeDetails.jsp", name = "success") })
	})
	public String ajaxDoGetMyFeeDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetMyFeeDetails' method");
		}
		try {
			long studentId = 0;
			if (!ObjectFunctions.isNullOrEmpty(getTempString())) {
				studentId = Long.valueOf(getTempString());
			} else if (getTempId() > 0) {
				studentId = getTempId();
			} else {
				setStudent((Student) studentManager.get(Student.class,"accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and status='"+Constants.YES_STRING+"' and description is null"));
			}
			if (studentId > 0) {
				setStudent((Student) studentManager.get(Student.class,studentId));
			}
			if (!ObjectFunctions.isNullOrEmpty(getStudent())) {
				prepareStudentSchoolFeeSettingList(getStudent());
				prepareStudentFeeDueAndCollection(getStudent());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed getting the session AcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions({
		@Action(value = "ajaxdoViewSectionwiseSubjectsPerformance", results = { @Result(location = "ajaxViewClasswiseSubjectsPerformance.jsp", name = "success"),
				 @Result(location = "parent/ajaxViewParentChilds.jsp", name = "childs")})
	})
		public String ajaxdoViewSectionwiseSubjectsPerformance() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxdoViewClasswiseSubjectsPerformance' method");
		}
		try
		{
			long accountId=0;
			if(getUser().getIsParent().equalsIgnoreCase("Y")){
				setObjectList(studentManager.getMyChildren(getUser().getId(),getUserCustId(),getUserAcademicYearId(),Constants.YES_STRING));
				return "childs";
			}else{
				accountId = getUser().getId();
				setTempId(accountId);
				if(accountId!=0){
					if(getUserAcademicYearId()>0){
						setStudent(studentManager.getStudentByAccountId(accountId,getUserAcademicYearId(),getUserCustId()));
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(getStudent())){
					//if(!ObjectFunctions.isNullOrEmpty(getStudent().getClassNameClassId())){
						//Set examTypes=getStudent().getClassNameClassId().getExamTypes();
					if(!ObjectFunctions.isNullOrEmpty(getStudent().getClassSection())){
					Set examTypes=getStudent().getClassSection().getExamTypes();
						if(ObjectFunctions.isNotNullOrEmpty(examTypes)){
							setExamTypeList(ConvertUtil.convertSetToList(examTypes));
							Collections.sort(getExamTypeList());
							examTypes=null;
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(getStudent().getClassSection())){
						setSubjectsList(ConvertUtil.convertSetToList(getStudent().getClassSection().getSubjects()));
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions({
		@Action(value = "ajaxGetStudentSubjects", results = {   @Result(location = "ajaxViewClasswiseSubjectsPerformance.jsp", name = "success") })
	})
		public String ajaxGetStudentDetails() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentClassandSubjects' method");
		}
		try
		{
			String accountId = getParamValue("studAccountId");
			if(!StringFunctions.isNullOrEmpty(accountId)){
				setTempId(Long.valueOf(accountId));
				if(getUserAcademicYearId()>0){
					setStudent(studentManager.getStudentByAccountId(getTempId(),getUserAcademicYearId(),getUserCustId()));
				}
				if(!ObjectFunctions.isNullOrEmpty(getStudent())){
					setExamTypeList(adminManager.getAllClassExamTypes(getUserCustId(),String.valueOf(getUserAcademicYearId())));
					StudyClass studyClass=getStudent().getClassSection();
					setSubjectsList(ConvertUtil.convertSetToList(studyClass.getSubjects()));
					studyClass=null;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions({
				 @Action(value = "viewMyPerformance", results = {   @Result(location = "class/studentPerformanceDetails.jsp", name = "success")})
	})
		public String studentActivitiesHome() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'studentActivitiesHome' method");
		}
		try
		{
			if(getUser().isParent()){
				if(getUserAcademicYearId()>0){
					if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
						List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studList))
							setViewStudentPersonAccountDetailsList(studList);
					}
				}
				if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())){
					setViewStudentPersonAccountDetails(getViewStudentPersonAccountDetailsList().get(0));
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
	*/
	@Actions( {
		@Action(value = "ajaxGetMyChildFeesDetails", results = { @Result(location = "parent/ajaxUpcomingPaymentDetails.jsp", name = "success") }) })
	public String ajaxDoGetMyChildrenFeeDetails() throws URTUniversalException {
	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetMyChildrenFeeDetails' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			long boardingPointId=0;
			long categoryId=0;
			long accountId=0;
			long academicYearId = 0;
			long custId = 0;
			if(!StringFunctions.isNullOrEmpty(getTempString())){
					String[] accIdAndClassId=getTempString().split("_");
					if(!ObjectFunctions.isNullOrEmpty(accIdAndClassId)){
					accountId=Long.valueOf(accIdAndClassId[0]);
					academicYearId=Long.valueOf(accIdAndClassId[2]);
					custId=Long.valueOf(accIdAndClassId[3]);
					setStudent((Student)studentManager.get(Student.class, "accountId="+accountId+" and academicYearId="+academicYearId+" and custId="+custId));
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(getStudent())){
				List<SchoolTerms> schoolTermsList=studentManager.getAllByCustId("SchoolTerms", custId, academicYearId);
				for(SchoolTerms schoolTerms : schoolTermsList){
					if(Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName())){
						//hear when login parent have different children with different custId and academicYearIds. so we get  academicyear from page By cvs on 6-5-2014
						AcademicYear academicYear = (AcademicYear) studentManager.get(AcademicYear.class, academicYearId);
						if(academicYear.isTransportFeeByBoardingPoint()){
							if(!ObjectFunctions.isNullOrEmpty(getStudent().getRouteBoardingPoints()))
								boardingPointId=getStudent().getRouteBoardingPoints().getId();
							categoryId=getStudent().getCategoryId();
						}else {
							categoryId=getStudent().getCategoryId();
						}
					}
					if ("E".equalsIgnoreCase(schoolTerms.getIsBetweenFeeDueDays())) {
						long totalTermAmount = adminManager.getFeeTotalAmountByTerm("Fee",getStudent().getCustId(),getStudent().getClassNameClassId().getId(),academicYearId,schoolTerms.getId(),categoryId,boardingPointId);
						if(totalTermAmount != 0){
							ViewStudentClassFeePaymentDetails feePaymentDetails = new ViewStudentClassFeePaymentDetails();
							feePaymentDetails.setTermName(schoolTerms.getMonthOfTermName());
							feePaymentDetails.setFromMonthName(schoolTerms.getDueDateStr());
							feePaymentDetails.setFeeAmount(Double.valueOf(totalTermAmount));
							getChildFeeDetails().add(feePaymentDetails);	
							feePaymentDetails=null;
						}
					}
					boardingPointId=0;
				}
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxGetMyChildrenClassMatesList", results = { @Result(location = "parent/ajaxViewMyChildrensClassmates.jsp", name = "success") })
	})
	 public String ajaxGetMyChildrenClassMatesList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetMyChildrenClassMatesList' method");
		}
		try {
			if(getUser().getIsParent().equalsIgnoreCase("Y")){
				setObjectList(studentManager.getMyChildren(getUser().getId(),getUserCustId(),getUserAcademicYearId(),Constants.YES_STRING));
				if(!ObjectFunctions.isNullOrEmpty(getObjectList()))
				{
					ViewStudentPersonAccountDetails parentStudentClass=(ViewStudentPersonAccountDetails)getObjectList().get(0);
				    ajaxMyChildsClassMates(parentStudentClass.getClassSectionId());
				    parentStudentClass = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	public void ajaxMyChildsClassMates(long classId){
	    setTempId(classId);
	    ajaxGetMyClassMates();
	}
	@Actions( { @Action(value = "ajaxGetMyClassMates", results = { @Result(location = "parent/ajaxViewMyChildrensClass.jsp", name = "success") })

	})
	public String ajaxGetMyClassMates() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetMyClassMates' method");
		}
		try {
			List<ViewStudentPersonAccountDetails> studentsList =null;
			String classId=getParamValue("classId");
			if(!ObjectFunctions.isNullOrEmpty(classId)){
				studentsList = studentManager.getMyChildrenClassmatesList(Long.valueOf(classId),"Y");
			}else if(!ObjectFunctions.isNullOrEmpty(getTempId())){
				studentsList = studentManager.getMyChildrenClassmatesList(getTempId(),"Y");
			}
			if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
					Collections.sort(studentsList);
					setClassStudentsList(studentsList);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "printExamSchedulesForMultipleClasses", results = {  @Result(location = "jasper/marks/examSchedulesWithClassesAndExamTypes.jasper", type="jasper", name = "success",params = {"dataSource","getExamScheduleList()","format", "PDF"}),
				                                                             @Result(location = "jasper/fee/errorMessageTemplet.jasper", type = "jasper", name = "errorMessage", params = {"dataSource", "alertSendType", "format", "PDF" }) }) })
	
	
	public String printExamSchedulesForMultipleClasses()
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'printExamSchedulesForMultipleClasses' method");
		}
		try
		{
			if(StringFunctions.isNotNullOrEmpty(getExamType()) && StringFunctions.isNotNullOrEmpty(getSelectedId())){
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					setCustomerName(customer.getOrganization());
					setTempString(customer.getOrganizationFullAddress());
				}
				if(getUser().isSchoolStudent())
					if("SO".equalsIgnoreCase(getAnyTitle())){
						setExamScheduleList(staffManager.getAll(ViewClassExamDetails.class,"classSectionId ="+getSelectedId()+" and eid in "+getExamType()+" Order by classSectionId,eid,examTypeSortingOrder,sortingOrder,examDate ASC"));
					}else{
						setExamScheduleList(staffManager.getAll(ViewClassExamDetails.class,"classSectionId ="+getSelectedId()+" and eid in "+getExamType()+" Order by classSectionId,eid,examTypeSortingOrder,examDate"));
					}
				else
					if("SO".equalsIgnoreCase(getAnyTitle())){
						setExamScheduleList(staffManager.getAll(ViewClassExamDetails.class,"classSectionId in"+getSelectedId()+" and eid in "+getExamType()+" Order by classSectionId,eid,examTypeSortingOrder,sortingOrder,examDate ASC"));
					}else{
						setExamScheduleList(staffManager.getAll(ViewClassExamDetails.class,"classSectionId in"+getSelectedId()+" and eid in "+getExamType()+" Order by classSectionId,eid,examTypeSortingOrder,examDate"));
					}
				if ("PDF".equalsIgnoreCase(getAnyId()) && !ObjectFunctions.isNullOrEmpty(getExamScheduleList()) ) {
					getResponse().setHeader("Content-Disposition","attachment; filename=ExamSchedulesForMultipleClasses.pdf");
				}else{
					setAlertSendType("There is no examSchedules for this class.");
					getResponse().setHeader("Content-Disposition","attachment; filename=ExamSchedulesForMultipleClasses.pdf");
					return "errorMessage";
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
	 @Actions({
			@Action(value = "getStudentKBank", results = { @Result(location = "kBank/ajaxGetStaffKBank.jsp", name = "success" )}),
			@Action(value = "ajaxStudentKBank", results = { @Result(location = "kBank/ajaxViewKBank.jsp", name = "success" )})
	 })
				public String getStudentKBank() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'getStaffKBank' method");
				}try{
					//synchronized (ERROR) {
						ajaxGetKBank();
					//}
				}catch(Exception ex){
					log.error("Entering into 'catch block':"+ex.getMessage());
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
	    @Actions({
			@Action(value = "ajaxGetKBankDetails", results = { @Result(location = "kBank/ajaxViewKBank.jsp", name = "success") })})
				public String ajaxGetKBankDetails() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxGetKBankDetails' method");
				}try{
					ajaxKBankDetails();
				}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			} 
	    @Actions({
		    @Action(value = "ajaxDoAddCaseStudy", results = { @Result(location = "kBank/ajaxAddCaseStudy.jsp", name = "success") })})
			public String ajaxDoPostCaseStudy() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxDoPostCaseStudy' method");
				}try{
					setStudySubjectList(staffManager.getAll(StudySubject.class));
					setSelectedId(getParamValue("kBankTypeId"));
				}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}	
	    @Actions({
			@Action(value = "ajaxAddPostCaseStudy", results = { @Result(location = "kBank/ajaxViewKBank.jsp", name = "success") })})
			public String ajaxAddPostCaseStudy() throws URTUniversalException {
				
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxDoPostCaseStudy' method");
				}
				try	{
						ajaxAddCaseStudy();
					}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
	   
	    @Actions({
			@Action(value = "ajaxRemoveKBankStudies", results = { @Result(location = "kBank/ajaxViewCaseStudy.jsp", name = "success") }) })
			public String removeCampus() throws URTUniversalException {
				try{		
					String kBankId = getParamValue("id");
					if (!ObjectFunctions.isNullOrEmpty(kBankId) ) {
						staffManager.remove(KBank.class,Long.valueOf(kBankId));	
					}super.addActionMessage("KBankStudies Deleted successfully.");
				}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
	    @Actions({
			@Action(value = "ajaxDoEditKBankStudies", results = { @Result(location = "kBank/editKBankStudies.jsp", name = "success") })})
			public String doEditCampus() throws URTUniversalException {
				try	{
					ajaxDoEditCampus();
					}catch(Exception ex){
						ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
					}
					return SUCCESS;
			}
	    @Actions({
			@Action(value = "ajaxEditKBankStudies", results = { @Result(location = "kBank/ajaxViewKBank.jsp", name = "success") }) })
			public String editKBankStudies() throws URTUniversalException {
				try
				{
					ajaxEditKBankStudies();
				}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
	    @Actions({
			@Action(value = "ajaxReadMoreKbankStudy", results = { @Result(location = "kBank/ajaxReadMoreKbankStudy.jsp", name = "viewReadMoreMinute") }) })
		    public String viewReadMoreKbankStudy() {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'viewReadMoreMinute' method");
				}
				try {
					ajaxViewReadMoreKbankStudy();
				}catch(Exception ex) {
					log.error(" entering Catch Block of viewReadMoreMinute():" + ex.getMessage());
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return "viewReadMoreMinute";
			} 
	    /*
		* Removed getting the session AcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
		*/
	    @Actions( { @Action(value = "ajaxViewAllKBankStudies", results = { @Result(location = "kBank/viewAllKBankStudies.jsp", name = "success") }) })
		public String viewAllKBankStudie() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'viewAllKBankStudie' method");
			}
			try {
				if (!StringFunctions.isNullOrEmpty(getParamValue("kBankTypeId"))) {
					setObjectList(staffManager.getAll(KBank.class, "kBankTypeId = "+getParamValue("kBankTypeId")+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
				}
			} catch (Exception ex) {
				log.error(" entering Catch Block of viewAllKBankStudie():"+ ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return "success";
		}
	    @Actions( {
			@Action(value = "ajaxSearchKBankStudies", results = { @Result(location = "kBank/ajaxViewAllKBankSearchDetails.jsp", name = "success") })})
			public String searchKBankStudies() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'searchKBankStudies' method");
				}
				try {
					if(!StringFunctions.isNullOrEmpty(getParamValue("searchKewords")) && !StringFunctions.isNullOrEmpty(getParamValue("selectedId"))){
						setSelectedId(getParamValue("selectedId"));
						setKBankTypeName(getParamValue("kBankTypeName"));
						List searchKewords =staffManager.getKBankBySearchKewordsKBankTypeId(getParamValue("searchKewords"),Long.valueOf(getParamValue("selectedId")),getUserCustId());
						if (!ObjectFunctions.isNullOrEmpty(searchKewords)) {
							setObjectList(searchKewords);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
	    @Actions({
			@Action(value = "ajaxDoAcceptKBankStudies", results = { @Result(location = "kBank/ajaxViewCaseStudy.jsp", name = "success") }) })
		   public String ajaxDoAcceptKBankStudies() throws URTUniversalException {
			try	{
				ajaxAcceptKBankStudies();
				}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
		}
	    @Actions({
			@Action(value = "ajaxDoKBankFavouriteStudies", results = { @Result(location = "kBank/ajaxViewCaseStudy.jsp", name = "success") }) })
		  public String ajaxDoKBankFavouriteStudies() throws URTUniversalException {
	    	try	{
	    		ajaxDoAddKBankFavouriteStudies();
	    		if (!StringFunctions.isNullOrEmpty(getParamValue("kBankTypeId")) &&  !StringFunctions.isNullOrEmpty(getParamValue("kBankTypeName"))){
	    			ajaxKBankDetails();
	    		}
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	    @Actions({
			@Action(value = "ajaxGetKBankFavourites", results = { @Result(location = "kBank/ajaxFavourites.jsp", name = "success") }) })
		  public String ajaxGetKBankFavourites() throws URTUniversalException {
	    	try	{
	    		ajaxKBankFavourites();
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	    @Actions({
			@Action(value = "ajaxDoAddComments", results = { @Result(location = "kBank/ajaxDoAddComment.jsp", name = "success") }) })
		  public String ajaxDoAddComments() throws URTUniversalException {
	    	try	{
	    		doAddComments();
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	    @Actions({
			@Action(value = "ajaxAddKBankComments", results = { @Result(location = "kBank/ajaxViewComment.jsp", name = "success") }) })
		  public String ajaxAddKBankComments() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddPostKBankComments' method");
			}
			try {	
				addKBankComments();
			}catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return "success";
		} 
	   
	    
	    @Actions( { @Action(value = "ajaxGetKhanPlayList", results = { @Result(location = "kBank/viewKhanPlayListHome.jsp", name = "success") }) })
		public String ajaxGetKhanPlayList() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetKhanPlayList' method");
			}
			try {
				ajaxGetKhanPlayListBase();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxGetPlayListVideosList", results = { @Result(location = "kBank/viewKhanPlayListVideosList.jsp", name = "success") }) })
		public String ajaxGetPlayListVideosList() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetPlayListVideosList' method");
			}
			try {
				String playListId = getParamValue("playListId");
				if(!StringFunctions.isNullOrEmpty(playListId))
				{
					ajaxGetPlayListVideosListBase(playListId);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxPlaySelectedVideo", results = { @Result(location = "kBank/videoPlayer.jsp", name = "success") }) })
		public String ajaxPlaySelectedVideo() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetPlayListVideosList' method");
			}
			try {
				String playListVideoId = getParamValue("id");
				if(!StringFunctions.isNullOrEmpty(playListVideoId))
				{
					ajaxPlaySelectedVideoBase(playListVideoId);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxKBankRating", results = { @Result(location = "ajaxKBankRate.jsp", name = "success") }) })
		  public String ajaxKBankRating() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxKBankRating' method");
			}
			try {	
				ajaxGetKBankRating();
			}catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
		@Actions( { 
			@Action(value = "ajaxDoSearchKVideos", results = { @Result(location = "kBank/ajaxSearchKVideos.jsp", name = "success") })
		})
		public String adminStudentDashboard() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'adminStudentDashboard' method");
			}
			try {

			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}

			return SUCCESS;
		}
		@Actions( {
			@Action(value = "ajaxSearchKVideosByKeywords", results = { @Result(location = "kBank/viewKhanPlayListVideosList.jsp", name = "success") })
			})
		public String ajaxSearchKVideosByKeywords() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'searchStudentsByRollNumber' method");
			}
			try {
				String keyword = getParamValue("keyword");
				if(!StringFunctions.isNullOrEmpty(keyword))
				{
					ajaxSearchKVideosByKeywordsBase(keyword);
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		
			return SUCCESS;
		}
		 @Actions( { @Action(value = "ajaxGetQuiz", results = { @Result(location = "kBank/viewQuizQuestions.jsp", name = "success") }),
			 @Action(value = "ajaxCancelQuiz", results = { @Result(location = "kBank/ajaxViewCategory.jsp", name = "success") }) })
			public String ajaxGetQuiz() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxGetQuiz' method");
				}
				try {
					setQuizList(staffManager.getQuizListWithCustId(getUserCustId()));
				} catch (Exception ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
		 	/*
			* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
			*/
		 @Actions( {
				@Action(value = "ajaxDoGetCategoryQuestion", results = { @Result(location = "kBank/quizQuestionList.jsp", name = "success") }) })
		public String ajaxDoGetCategoryQuestion() throws URTUniversalException {
	
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetCategoryQuestion' method");
			}
			try {
				Student studentDetails = null;
				String quizId = getParamValue("quizId");
				String quizStartDateStr = null;
				List questionAnserList=null;
				studentDetails = (Student)studentManager.get(Student.class,"accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and description is null");
				if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
					String todayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
					List categoryQuestionList = staffManager.getQuizQuestionListWithCategoryId(Long.valueOf(quizId),getUserCustId());
					setQuizId(Long.valueOf(quizId));
					if (!ObjectFunctions.isNullOrEmpty(categoryQuestionList)) {
						for (Object obj : categoryQuestionList) {
							QuizQuestion quizQuestion = (QuizQuestion) obj;
							if(!ObjectFunctions.isNullOrEmpty(quizQuestion)){
								quizStartDateStr = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, quizQuestion.getStartDateStr());
								setStudentQuestionAnswer(studentManager.getStudentAnswerAttemptWithQuestionId(studentDetails.getId(), quizQuestion.getId(), getUserCustId()));
								if (!ObjectFunctions.isNullOrEmpty(quizQuestion)) {
									if (todayDate.equalsIgnoreCase(quizStartDateStr)) {
										if (ObjectFunctions.isNullOrEmpty(getStudentQuestionAnswer())) {
											questionAnserList =staffManager.getQuestionAnswersWithQuestionId(quizQuestion.getId(),getUserCustId());
											getCategoryQuestionList().add(quizQuestion);
										}
									} else {
										int quizDays = DateFunctions.daysBetween(quizQuestion.getStartDate(), quizQuestion.getEndDate());
										if (quizDays >= 0) {
											for (int i = 1; i <= quizDays; i++) {
												if (!ObjectFunctions.isNullOrEmpty(quizQuestion.getStartDate())) {
													Date quizDate = DateFunctions.add(quizQuestion.getStartDate(), i);
													quizStartDateStr = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,quizDate);
													if (todayDate.equalsIgnoreCase(quizStartDateStr)) {
														if (ObjectFunctions.isNullOrEmpty(getStudentQuestionAnswer())) {
															questionAnserList=staffManager.getQuestionAnswersWithQuestionId(quizQuestion.getId(),getUserCustId());
															getCategoryQuestionList().add(quizQuestion);
														}
													}
												}
											}
										}
									}
									if (!ObjectFunctions.isNullOrEmpty(questionAnserList)) {
									 getQuestionAnswerList().addAll(questionAnserList);
									}
								}
								quizQuestion=null;
								quizStartDateStr=null;
							}
						}
						categoryQuestionList=null;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		 	/*
			* Removed prepareAcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
			*/
		@Actions( { @Action(value = "ajaxStudentQuizQuestionAnswer", results = { @Result(location = "kBank/ajaxViewCategory.jsp", name = "success") }) })
		public String ajaxStudentQuizQuestionAnswer() throws URTUniversalException {
	
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxStudentQuizQuestionAnswer' method");
			}
			try {
				Student studentDetails =null;
				int totalQuestionsCount = 0;
				Map mMap = new HashMap();
				QuizQuestion question=null;
				QuestionAnswer questionAns=null;
				QuestionAnswer questionAnswer=null;
				long accountId = getUser().getId();
				if(getUserAcademicYearId()>0){
					studentDetails=studentManager.getStudentByAccountId(accountId,getUserAcademicYearId(),getUserCustId());
				}
				if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
					totalQuestionsCount = getQuestionAnswerId().length;
					if (!ObjectFunctions.isNullOrEmpty(totalQuestionsCount<0)) {
						for (int i = 0; i < totalQuestionsCount; i++) {
							String questionId = getQuestionAnswerId()[i];
							question=(QuizQuestion)staffManager.get(QuizQuestion.class, Long.valueOf(questionId));
							String studentAnswer = getParamValue("correctAnswer"+ questionId);
							questionAnswer=staffManager.getCorrectAnswerWithQuestionId(Long.valueOf(questionId), getUserCustId());
							questionAns=studentManager.getQuestionAnswer(studentAnswer,Long.valueOf(questionId), getUserCustId());
							setStudentQuestionAnswer(studentManager.getStudentAnswerAttemptWithQuestionId(studentDetails.getId(),Long.valueOf(questionId),getUserCustId()));
							if(ObjectFunctions.isNullOrEmpty(getStudentQuestionAnswer())){
								StudentQuestionAnswer studentQuestionAnswer=new StudentQuestionAnswer();
								if(!ObjectFunctions.isNullOrEmpty(studentAnswer)){
									if(!ObjectFunctions.isNullOrEmpty(questionAns)){
										if(studentAnswer.equalsIgnoreCase(questionAns.getQuestionAnswer())){
											studentQuestionAnswer.setStudentId(studentDetails.getId());
											studentQuestionAnswer.setCustId(getUserCustId());
											studentQuestionAnswer.setQuestionId(Long.valueOf(questionId));
											studentQuestionAnswer.setStudentAnswer(studentAnswer);
											studentQuestionAnswer.setCorrectAnswer(questionAnswer.getQuestionAnswer());
											studentQuestionAnswer.setStudentCorrectAnswer("Y");
											staffManager.save(studentQuestionAnswer);
										//	correctAnswersCount++;
											String answer= studentAnswer+"$"+questionAns.getQuestionAnswer();
											mMap.put(question, answer);
										}
									}
									else{
										studentQuestionAnswer.setStudentId(studentDetails.getId());
										studentQuestionAnswer.setCustId(getUserCustId());
										studentQuestionAnswer.setQuestionId(Long.valueOf(questionId));
										studentQuestionAnswer.setStudentAnswer(studentAnswer);
										studentQuestionAnswer.setCorrectAnswer(questionAnswer.getQuestionAnswer());
										studentQuestionAnswer.setStudentCorrectAnswer("N");
										staffManager.save(studentQuestionAnswer);
										String answer= studentAnswer+"$"+questionAnswer.getQuestionAnswer();
										mMap.put(question, answer);
									}
								}
							}
							else {
								super.addActionError("Already you answer this question");
							}
							getSession().setAttribute("quetionAnswer", question);
							question=null;
						}
					}
					super.addActionMessage("Answer submited sucessfully");
					ajaxGetQuiz();
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxDoStudentQuizResults", results = { @Result(location = "kBank/ajaxViewStudentCategory.jsp", name = "success") }) })
			public String ajaxDoStudentQuizResults() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxDoStudentQuizResults' method");
				}
				try {
					setQuizList(staffManager.getQuizListWithCustId(getUserCustId()));
				} catch (Exception ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
		/*
		* Removed getting the session AcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
		*/
		 @Actions( {
				@Action(value = "ajaxGetStudentQuestionAnswer", results = { @Result(location = "kBank/viewStudentQuizAnswers.jsp", name = "success") }) })
		public String ajaxGetStudentQuestionAnswer() throws URTUniversalException {
	
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetStudentQuestionAnswer' method");
			}
			try {
				Student studentDetails =null;
				long accountId = getUser().getId();
				String quizId = getParamValue("quizId");
				String status = "A";
				List studentCorrectList = null;
				List studentNotCorrectList = null;
				String todayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
				studentDetails = (Student)studentManager.get(Student.class,"accountId="+accountId+" and academicYearId="+getUsrChgedAcademicId()+" and custId="+getUserCustId()+" and description is null");
				if(getUserAcademicYearId()>0){
					studentDetails = studentManager.getStudentByAccountId(accountId,getUserAcademicYearId(),getUserCustId());
				}
				if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
					List studentQuestionAnswerList = studentManager.getStudentQuestionAnswers(studentDetails.getId(), Long.valueOf(quizId), getUserCustId(), todayDate);
					if (!ObjectFunctions.isNullOrEmpty(studentQuestionAnswerList)) {
						setQuestionAnswerList(studentManager.getStudentQuizQuestionAnswer(Long.valueOf(quizId),getUserCustId(), status));
						for (Object obj : studentQuestionAnswerList) {
							ViewStudentQuestionAnswers studentQuestionAnswers = (ViewStudentQuestionAnswers) obj;
							if (!ObjectFunctions.isNullOrEmpty(studentQuestionAnswers)) {
								studentCorrectList = studentManager.getStudentCorrectAnswersList(studentQuestionAnswers.getQuestionId(),getUserCustId());
								studentNotCorrectList = studentManager.getStudentNotCorrectAnswersList(studentQuestionAnswers.getQuestionId(),getUserCustId());
		
								ViewStudentQuestionAnswers questionAnswers = new ViewStudentQuestionAnswers();
								questionAnswers.setStudentAnswer(studentQuestionAnswers.getStudentAnswer());
								questionAnswers.setCorrectAnswer(studentQuestionAnswers.getCorrectAnswer());
								questionAnswers.setQuestionName(studentQuestionAnswers.getQuestionName());
								questionAnswers.setStudentCorrectAnswer(String.valueOf(studentCorrectList.size()));
								questionAnswers.setQuestionId(studentNotCorrectList.size());
								getStudentQuestionAnswerList().add(questionAnswers);
							}
							studentQuestionAnswers=null;
						}
						studentQuestionAnswerList=null;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
    @Action(value = "ajaxCancelFeedback", results = { @Result(location = "parent/feedback/viewStudentAcceptedEventsLists.jsp", name = "success") })
    
    public String ajaxCancelFeedback() throws URTUniversalException {
	    if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxCancelFeedback' method");
		}
		try {
			String feedbackQuestionId = getParamValue("id");
			long accountId = getUser().getId();
			studentManager.cancelRegistrationStudentEvent(Long.valueOf(feedbackQuestionId), accountId);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    /*
	* Removed getting the session AcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "ajaxAddParentFeedbackForm", results = { @Result(location = "parent/feedback/selectChildForFeedback.jsp", name = "success") }) })
	public String ajaxAddParentFeedbackForm() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddParentFeedbackForm' method");
		}
		try {
			setToDate(new Date());
			Student studentDetails =null;
			long accountId = getUser().getId();
			int staffCount = 0;
			ParentFeedbackResult parentFeedbackResult = null;
			if (getUser().isParent()) {
				if (!ObjectFunctions.isNullOrEmpty(getQuestionAnswerId())) {
					setAnyTitle("School");
					for (int i = 0; i < getQuestionAnswerId().length; i++) {
						String feedbackQuestionId = getQuestionAnswerId()[i];
						String parentAns = getParamValue("feedbackGradeAns"+ feedbackQuestionId);
						//String feedBackAnswerDes = getParamValue("feedBackAnswerDes"+ feedbackQuestionId);
						parentFeedbackResult=(ParentFeedbackResult) studentManager.get(ParentFeedbackResult.class,"custId="+getUserCustId()+" and feedbackQuestionId="+feedbackQuestionId+" and parentId="+accountId);
						if(ObjectFunctions.isNullOrEmpty(parentFeedbackResult)){
							if (!ObjectFunctions.isNullOrEmpty(parentAns)) {
								parentFeedbackResult=new ParentFeedbackResult();
								parentFeedbackResult.setParentId(accountId);
								parentFeedbackResult.setCustId(getUserCustId());
								//parentFeedbackResult.setDescription(feedBackAnswerDes);
								parentFeedbackResult.setFeedbackQuestionId(Long.valueOf(feedbackQuestionId));
								parentFeedbackResult.setFeedbackGradeId(parentAns);
								parentFeedbackResult.setAcademicYearId(String.valueOf(getUserAcademicYearId()));
								studentManager.save(parentFeedbackResult);
							}
						}else{
							//parentFeedbackResult.setDescription(feedBackAnswerDes);
							parentFeedbackResult.setFeedbackGradeId(parentAns);
							studentManager.save(parentFeedbackResult);
						}
					}
					ajaxDoRatingInformation();
					super.addActionMessage("Feedback submitted successfully");
				}
			} else if (getUser().isSchoolStudent()) {
				if(getUserAcademicYearId()>0){
					studentDetails=studentManager.getStudentByAccountId(accountId,getUserAcademicYearId(),getUserCustId());
					AcademicYear academicYear= (AcademicYear)studentManager.get(AcademicYear.class,getUserAcademicYearId());
					String endDate = null;
					double totalWorkingDays = 0;
					double presentDaysCount=0;
					Object[] studAtt = null;
					if(!ObjectFunctions.isNullOrEmpty(academicYear)){
						if(DateFunctions.compare2Dates(new Date(),academicYear.getStartDate()) && DateFunctions.compare2Dates(academicYear.getEndDate(),new Date()))
							endDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
						else if(new Date().after(academicYear.getEndDate()))
							endDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, academicYear.getEndDate());
						if("D".equalsIgnoreCase(academicYear.getManageAttendanceBy())){
							totalWorkingDays = adminManager.fetchTotalWorkingDays(academicYear.getId(),endDate);
							presentDaysCount = totalWorkingDays - adminManager.fetchStudentAbsentiesCount(studentDetails.getId(),endDate);
						}else{
							studAtt = studentManager.get("select IFNULL(sum(totalWorkingDays),0) as totalWorkingDays,IFNULL(sum(noOfPresentDays),0) as noOfPresentDays from studentMonthlyAttendance where studentId="+studentDetails.getId());
							if(!ObjectFunctions.isNullOrEmpty(studAtt)){
								if(!ObjectFunctions.isNullOrEmpty(studAtt[0]) && !ObjectFunctions.isNullOrEmpty(studAtt[1])){
									if(!ObjectFunctions.isNullOrEmpty(studAtt[0]))
										totalWorkingDays = Double.valueOf(studAtt[0].toString());
									if(!ObjectFunctions.isNullOrEmpty(studAtt[1]))
										presentDaysCount = Double.valueOf(studAtt[1].toString());
								}
							}
						}
					}
				FeedBackSettings feedBackSettings=(FeedBackSettings) studentManager.get(FeedBackSettings.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
				if (!ObjectFunctions.isNullOrEmpty(feedBackSettings)) {
					if(feedBackSettings.getAttendancePercentage() <= ((presentDaysCount/totalWorkingDays)*100)){
						if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
							if(!ObjectFunctions.isNullOrEmpty(getQuestionAnswerId())){
								setAnyTitle("School");
								if (!ObjectFunctions.isNullOrEmpty(getQuestionAnswerId().length != 0)) {
									for (int i = 0; i < getQuestionAnswerId().length; i++) {
										String feedbackQuestionId = getQuestionAnswerId()[i];
										String studentAns = getParamValue("feedbackGradeAns"+ feedbackQuestionId);
										//String feedBackAnswerDes = getParamValue("feedBackAnswerDes"+ feedbackQuestionId);
										parentFeedbackResult=(ParentFeedbackResult) studentManager.get(ParentFeedbackResult.class,"custId="+getUserCustId()+" and feedbackQuestionId="+feedbackQuestionId+" and studentId="+studentDetails.getId());
										if (ObjectFunctions.isNullOrEmpty(parentFeedbackResult)) {
											if (!ObjectFunctions.isNullOrEmpty(studentAns)) {
												parentFeedbackResult=new ParentFeedbackResult();
												parentFeedbackResult.setStudentId(studentDetails.getId());
												parentFeedbackResult.setCustId(getUserCustId());
												parentFeedbackResult.setFeedbackQuestionId(Long.valueOf(feedbackQuestionId));
												//parentFeedbackResult.setDescription(feedBackAnswerDes);
												parentFeedbackResult.setFeedbackGradeId(studentAns);
												parentFeedbackResult.setAcademicYearId(String.valueOf(getUserAcademicYearId()));
												studentManager.save(parentFeedbackResult);
											}
										}else{
											parentFeedbackResult.setFeedbackGradeId(studentAns);
											//parentFeedbackResult.setDescription(feedBackAnswerDes);
											studentManager.save(parentFeedbackResult);
										}
									}
								}
								ajaxDoRatingInformation();
								super.addActionMessage("Feedback submitted successfully");
							}
							if (!ObjectFunctions.isNullOrEmpty(getStaffId())) {
								setAnyTitle("Teacher");
								staffCount = getStaffId().length;
								if (!ObjectFunctions.isNullOrEmpty(staffCount < 0)) {
									for (int j = 0; j < staffCount; j++) {
										String staffId = getStaffId()[j];
										List<FeedbackQuestions> feedbackQuestionlist= studentManager.getAll(FeedbackQuestions.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and roleDescription='Teacher Feedback Questions' ");
										for(FeedbackQuestions obj:feedbackQuestionlist){
											FeedbackQuestions objFeedback=obj;
											if(!ObjectFunctions.isNullOrEmpty(objFeedback)){
												String studentAns = getParamValue("studentStaffGradeAns"+objFeedback.getId()+""+staffId);
												//String feedBackAnswerDes = getParamValue("feedBackAnswerDes"+objFeedback.getId()+""+staffId);
												parentFeedbackResult=(ParentFeedbackResult) studentManager.get(ParentFeedbackResult.class,"custId="+getUserCustId()+" and staffId="+staffId+" and studentId="+studentDetails.getId()+" and feedbackQuestionId="+objFeedback.getId());
												if (ObjectFunctions.isNullOrEmpty(parentFeedbackResult)) {
													if (!ObjectFunctions.isNullOrEmpty(studentAns)) {
														parentFeedbackResult=new ParentFeedbackResult();
														parentFeedbackResult.setStudentId(studentDetails.getId());
														parentFeedbackResult.setCustId(getUserCustId());
														parentFeedbackResult.setStaffId(Long.valueOf(staffId));
														parentFeedbackResult.setFeedbackGradeId(studentAns);
														//parentFeedbackResult.setDescription(feedBackAnswerDes);
														parentFeedbackResult.setFeedbackQuestionId(objFeedback.getId());
														parentFeedbackResult.setAcademicYearId(String.valueOf(getUserAcademicYearId()));
														studentManager.save(parentFeedbackResult);
													}
													
												}else{
													//parentFeedbackResult.setDescription(feedBackAnswerDes);
													parentFeedbackResult.setFeedbackGradeId(studentAns);
													studentManager.save(parentFeedbackResult);
												}
											}
										}
									}
									ajaxDoAddTeacherRating();
									super.addActionMessage("Feedback submitted successfully");
								}
							}
						}
					}else{
						super.addActionError("You can not provide the rating,because your attendance percentage is very low.");
					}
				}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed AcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
	@Actions( {
		@Action(value = "ajaxViewMyRecommendations", results = { @Result(location = "class/ajaxViewStudentPerformance.jsp", name = "success") }) })
		public String ajaxViewMyRecommendations() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMyRecommendations' method");
		}
		try {
			if(getUser().isParent()){
				if(getUserAcademicYearId()>0){
					//setViewStudentPersonAccountDetailsList(adminManager.getParentChildrens(getUserCustId(),getUser().getId(),getUserAcademicYearId()));
					//setViewStudentPersonAccountDetailsList(adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId()));
					if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
						List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studList))
							setViewStudentPersonAccountDetailsList(studList);
					}
				}
				if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())){
					setViewStudentPersonAccountDetails(getViewStudentPersonAccountDetailsList().get(0));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed getting the session AcademicYearId and used getUserAcademicYearId() as well as code modularityis done by venkatesh - 04-23-2013
	*/
	@Actions( {
			@Action(value = "ajaxselectChildForFeedback", results = { @Result(location = "parent/feedback/selectChildForFeedback.jsp", name = "success") }) })
	public String ajaxselectChildForFeedback() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxselectChildForFeedback' method");
		}
		try {
			setToDate(new Date());
			setFeedBackSettings((FeedBackSettings) studentManager.get(FeedBackSettings.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
			Student studentDetails = null;
			if(getUserAcademicYearId()>0){
				 studentDetails = studentManager.getStudentByAccountId(getUser().getId(),getUserAcademicYearId(),getUserCustId());
			}
			List<FeedbackQuestions> feedbackQuestionsList =  studentManager.getAll(FeedbackQuestions.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" order by roleDescription");
			List<FeedbackGrades> feedbackGradeList=studentManager.getAllByCustId("FeedbackGrades",getUserCustId(),0);
			if (!ObjectFunctions.isNullOrEmpty(feedbackGradeList)) {
				setFeeTypeList(feedbackGradeList);
			}
			if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
				StudyClass studyClass = studentDetails.getClassSection();
				if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
					List<ClassTeacher> classTeacherList = staffManager.getStudyClassSubjectByClassid(studyClass.getId());
					if (!ObjectFunctions.isNullOrEmpty(classTeacherList)) {
						StringBuffer staffIds = new StringBuffer();
						staffIds.append("");
						for (ClassTeacher classTeacher : classTeacherList) {
							if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
								if (!ObjectFunctions.isNullOrEmpty(classTeacher.getStaff())) {
									staffIds.append(classTeacher.getStaff().getId());
									staffIds.append(",");
								}
							}
							classTeacher=null;
						}
						staffIds.append("0");
						List<ViewStaffPersonAccountDetails> subjectStaffsList = adminManager.getViewStaffDetailsListByStaffIds(staffIds.toString(), getUserCustId(),getUserAcademicYearId());
						if (!ObjectFunctions.isNullOrEmpty(subjectStaffsList)) {
							for(ViewStaffPersonAccountDetails staffPersonAccountDetails:subjectStaffsList){
								ParentFeedbackResult feedbackResult=studentManager.getStudentFeedbackStaffResult(staffPersonAccountDetails.getStaffId(),studentDetails.getId(),getUserCustId());
								if(ObjectFunctions.isNullOrEmpty(feedbackResult)){
									getStaffsList().add(staffPersonAccountDetails);
									feedbackResult=null;
								}else {
									ParentFeedbackResult studentFeedbackResult=new ParentFeedbackResult();
									studentFeedbackResult.setId(staffPersonAccountDetails.getStaffId());
									studentFeedbackResult.setFeedbackGradeId(staffPersonAccountDetails.getLastName()+" "+staffPersonAccountDetails.getFirstName());
									studentFeedbackResult.setFeedbackQuestionId(Long.valueOf(feedbackResult.getFeedbackGradeId()));
									studentFeedbackResult.setDescription(feedbackResult.getDescription());
									getStudentQuestionAnswerList().add(studentFeedbackResult);
									getStaffsList().add(staffPersonAccountDetails);
									studentFeedbackResult=null;
								}
								staffPersonAccountDetails=null;
							}
							
						}
						subjectStaffsList=null;
						classTeacherList=null;
					}
					studyClass=null;
				}
				if (!ObjectFunctions.isNullOrEmpty(feedbackQuestionsList)) {
					for(FeedbackQuestions feedbackQuestions : feedbackQuestionsList){
						ParentFeedbackResult parentFeedbackResult=studentManager.getStudentFeedbackResult(feedbackQuestions.getId(),getUserCustId(),studentDetails.getId());
						if(ObjectFunctions.isNullOrEmpty(parentFeedbackResult)){
							getFeedbackQuestionsList().add(feedbackQuestions);
							parentFeedbackResult=null;
						}else {
							/*Dummy method to store the parent feedback result*/
							ParentFeedbackResult result=new ParentFeedbackResult();
							result.setId(feedbackQuestions.getId());
							result.setFeedbackGradeId(feedbackQuestions.getDescription());
							result.setDescription(parentFeedbackResult.getDescription());
							result.setFeedbackQuestionId(Long.valueOf(parentFeedbackResult.getFeedbackGradeId()));
							getParentFeedbackList().add(result);
							getFeedbackQuestionsList().add(feedbackQuestions);
							result=null;
						}
					}
				}
				studentDetails=null;
			}else {
				if (!ObjectFunctions.isNullOrEmpty(feedbackQuestionsList)) {
					for(FeedbackQuestions feedbackQuestions : feedbackQuestionsList){
						ParentFeedbackResult parentFeedbackResult=studentManager.getParentFeedbackResult(feedbackQuestions.getId(),getUserCustId(),getUser().getId());
						if(ObjectFunctions.isNullOrEmpty(parentFeedbackResult)){
							getFeedbackQuestionsList().add(feedbackQuestions);
							parentFeedbackResult=null;
						}else {
							/*Dummy method to store the parent feedback result*/
							ParentFeedbackResult result=new ParentFeedbackResult();
							result.setId(feedbackQuestions.getId());
							result.setFeedbackGradeId(feedbackQuestions.getDescription());
							result.setDescription(parentFeedbackResult.getDescription());
							result.setFeedbackQuestionId(Long.valueOf(parentFeedbackResult.getFeedbackGradeId()));
							getParentFeedbackList().add(result);
							getFeedbackQuestionsList().add(feedbackQuestions);
							parentFeedbackResult=null;
							result=null;
						}
					}
					//setFeedbackQuestionsList(feedbackQuestionsList);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
   @Actions( { @Action(value = "ajaxViewSingleEvent", results = { @Result(location = "eventDetails.jsp", name = "success") }) })
	public String viewEvent() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewSingleEvent' method");
		}

		try {
			String eventId = getParamValue("eventId");
			if(!StringFunctions.isNullOrEmpty(eventId))
			{
			    setEvents((Events)studentManager.get(Events.class, Long.valueOf(eventId)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
   @Actions( {
		@Action(value = "ajaxCheckLeaveDatesForHolidays", results = { @Result(type = "json", name = "success",params = {"includeProperties","thresholdMonths,classTeacherStatus"}) }) })
	public String ajaxCheckLeaveDates() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckLeaveDates' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getLeave().getStartDate()) && !ObjectFunctions.isNullOrEmpty(getLeave().getEndDate())){
				long custId=0;
				if(getCustId() > 0)
					custId=getCustId();
				else
					custId=getUserCustId();
				Calendar startDate=Calendar.getInstance();
				startDate.setTime(getLeave().getStartDate());
				List<SchoolHolidays> holidayList=null;
				List<Leave> leavesList=null;
				AcademicYear academicYear = getCurrentAcademicYear();
				if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()))
				{
					Object[] classNameClassIds= studentManager.get("select classId,className from vw_classSectionDetails where custId="+custId+ " and academicYearId="+academicYear.getId()+" and classSectionId='"+getStudyClassId()+"'");
					if(!ObjectFunctions.isNullOrEmpty(classNameClassIds))
		    		{
						holidayList=adminManager.getSchoolHolidaysListByDatesAndCustId(custId,0, null, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getStartDate()), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getEndDate()),classNameClassIds[0].toString(),null,null,0,"OrderByDate",null);
		    		}
				}
				else
					holidayList=adminManager.getSchoolHolidaysListByDatesAndCustId(custId,0,null,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getStartDate()), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getLeave().getEndDate()),null,null,null,0,"OrderByDate",null);
				JSONObject leavesDetails=null;
				boolean isHoliday=false;
				if(ObjectFunctions.isNotNullOrEmpty(holidayList)){
					leavesDetails=new JSONObject();
					for(SchoolHolidays holiday:holidayList){
						getDisplayAttendanceSet().add(holiday.getHolidayDateStr());
					}
					isHoliday=true;
					setDisplayAttendanceSet(getDisplayAttendanceSet());
					//leavesDetails.put("holidays", getDisplayAttendanceSet());
					//setDisplayAttendanceSet(null);
					//getResponse().getOutputStream().print(leavesDetails.toString());
				}
				leavesDetails=new JSONObject();
				boolean isAppliedLeave=false;
				if(getLeave().getId()>0)
					leavesList=studentManager.getAll(Leave.class, "id!="+getLeave().getId()+" and accountId="+getAnyTitle()+" and custId="+custId+" and academicYearId="+getUserAcademicYearId());
				else
					leavesList=studentManager.getAll(Leave.class, "accountId="+getAnyTitle()+" and custId="+custId+" and academicYearId="+getUserAcademicYearId());
				if(ObjectFunctions.isNotNullOrEmpty(leavesList)){
					for(;DateFunctions.compare2Dates(getLeave().getEndDate(),startDate.getTime());startDate.add(Calendar.DATE, 1)){
						for(Leave leave:leavesList){
							if(!ObjectFunctions.isNullOrEmpty(leave) && !ObjectFunctions.isNullOrEmpty(leave.getStartDate()) && !ObjectFunctions.isNullOrEmpty(leave.getEndDate())){
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
						leavesDetails.put("leaveDesc", "You have already applied leave(s) between start data and end date(s). Please check in View Leave Details.");
					}
				}
				if(isHoliday && !isAppliedLeave){
					leavesDetails.put("holidays", getDisplayAttendanceSet());
				}
				setDisplayAttendanceSet(null);
				getResponse().getOutputStream().print(leavesDetails.toString());
				holidayList = null;
				academicYear = null;
			}
			} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
   public void generateStudentLeaveDetails(ViewStudentPersonAccountDetails studentDetails){
			setApprovedLeavesList(studentManager.getLeavesListByAccountId(studentDetails.getAccountId(),Constants.ACTIVE_STATUS, studentDetails.getCustId(),studentDetails.getAcademicYearId()));
			setLeavesList(studentManager.getLeavesListByAccountId(studentDetails.getAccountId(),Constants.PENDING_STATUS, studentDetails.getCustId(),studentDetails.getAcademicYearId()));
			setRejectedLeavesList(studentManager.getLeavesListByAccountId(studentDetails.getAccountId(),Constants.REJECTED_STATUS, studentDetails.getCustId(),studentDetails.getAcademicYearId()));
			studentDetails=null;
   } 
   @Actions( { @Action(value = "ajaxRemoveStudentPendingLeave", results = { @Result(location = "leaves/viewLeavesList.jsp", name = "success") }) })
	public String ajaxRemoveStudentPendingLeave() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveStudentPendingLeave' method");
		}
		try{
			if(getTempId1() > 0){
				//staffManager.remove(Leave.class, getTempId1());
				super.addActionMessage("Leave removed successfully.");
				int responseCode = staffManager.deleteLeaveInfo(getTempId1());
				if(responseCode == 0){
					super.addActionMessage("Leave removed successfully.");
				}else{
					super.addActionError("Leave not deleted.");
				}
				ajaxDoGetStudentLeaveDetails();
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   @Actions( { @Action(value = "ajaxGetStudentAcademicYearDetails", results = { @Result(location = "leaves/studentLeaveForm.jsp", name = "success") }) })
	public String ajaxGetStudentAcademicYearDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentAcademicYearDetails' method");
		}
		try{
			if(StringFunctions.isNotNullOrEmpty(getAnyId())){
				String[] accIdAndClassIdAndAcademicYearId=getAnyId().split("_");
				if(!ObjectFunctions.isNullOrEmpty(accIdAndClassIdAndAcademicYearId)){
					long academicYearId=Long.valueOf(accIdAndClassIdAndAcademicYearId[2]);
					setCustId(Long.valueOf(accIdAndClassIdAndAcademicYearId[3]));
					if(academicYearId > 0)
					{
						loadAcademicYearStartDateAndDates(academicYearId);
						//setViewStudentPersonAccountDetailsList(adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId()));
						if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
							List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(studList))
								setViewStudentPersonAccountDetailsList(studList);
						}
						prepareGetClassTeatherByclassSectionId(Long.valueOf(accIdAndClassIdAndAcademicYearId[1]));
						//getAccountId()+"_"+getClassSectionId()+"_"+getAcademicYearId()+"_"+getCustId();
						if(!ObjectFunctions.isNullOrEmpty(getViewStudentPersonAccountDetailsList())){
							for(ViewStudentPersonAccountDetails studentDetails : getViewStudentPersonAccountDetailsList())
							{
								if(studentDetails.getAccountId() == Long.valueOf(accIdAndClassIdAndAcademicYearId[0]) && studentDetails.getAcademicYearId() == academicYearId && studentDetails.getClassSectionId() == Long.valueOf(accIdAndClassIdAndAcademicYearId[1]))
								{
									log.debug(studentDetails.getHostelMode());
									setViewStudentPersonAccountDetails(studentDetails);
									break;
								}
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
   
   @Actions( {
		@Action(value = "ajaxStudentMarksDetails", results = { @Result(location = "class/ajaxViewExamResults.jsp", name = "success") })
   })
   public String ajaxStudentMarksDetails() throws URTUniversalException {

	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxStudentMarksDetails' method");
	}

	try {
		if(getUser().isParent()){
			if(getUserAcademicYearId() > 0 ){
				//setViewStudentPersonAccountDetailsList(adminManager.getParentChildrens(getUserCustId(),getUser().getId(),getUserAcademicYearId()));
				//setViewStudentPersonAccountDetailsList(adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId()));
				if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
					List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(studList))
						setViewStudentPersonAccountDetailsList(studList);
				}
			} 
			/*if(getTempId() > 0){
				studentSubjectsAndExamTypes();
			}else{*/
				if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())){
					setTempId(getViewStudentPersonAccountDetailsList().get(0).getStudentId());
					studentSubjectsAndExamTypes();
				}
			//}
		}else{
			studentSubjectsAndExamTypes();
		}
		if(!ObjectFunctions.isNullOrEmpty(getViewStudentPersonAccountDetails())){
			HashMap<Boolean,String>  fileDetails =  validateStudentFileExistOrNot(getViewStudentPersonAccountDetails().getFullName(),getViewStudentPersonAccountDetails().getAdmissionNumber(),getViewStudentPersonAccountDetails().getClassSectionId(),getViewStudentPersonAccountDetails().getCustomerShortName());
			for(Map.Entry<Boolean,String> fileDetail : fileDetails.entrySet()){
				setTempString(fileDetail.getValue());
				setTempBoolean(fileDetail.getKey());
				fileDetail = null;
			}
			fileDetails = null;
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
   }
   /*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
   public void studentSubjectsAndExamTypes(){
	   if (log.isDebugEnabled()) {
			log.debug("Entering 'studentSubjectsAndExamTypes' method");
		}
		try {
		 if(getTempId() > 0){
			 setViewStudentPersonAccountDetails((ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class, getTempId(),"studentId"));
		 }else{
			 if(getUserAcademicYearId()>0){
				 setViewStudentPersonAccountDetails((ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class," accountId="+getUser().getId() +" and academicYearId="+getUserAcademicYearId()));
			 }
		 }
			if(!ObjectFunctions.isNullOrEmpty(getViewStudentPersonAccountDetails())){
				//For showing examResults
				//ClassName className=(ClassName)studentManager.get(ClassName.class, getViewStudentPersonAccountDetails().getClassNameClassId());
				StudyClass studyClass=(StudyClass)studentManager.get(StudyClass.class, getViewStudentPersonAccountDetails().getClassSectionId());
				if(!ObjectFunctions.isNullOrEmpty(studyClass)){
					Set examTypes=studyClass.getExamTypes();
					if(ObjectFunctions.isNotNullOrEmpty(examTypes)){
						setExamTypeList(ConvertUtil.convertSetToList(examTypes));
						Collections.sort(getExamTypeList());
						examTypes=null;
					}
					//studyClass=null;
				}
				//StudyClass studyClass=(StudyClass)studentManager.get(StudyClass.class,getViewStudentPersonAccountDetails().getClassSectionId());
				if(!ObjectFunctions.isNullOrEmpty(studyClass)){
					Set subjectsSet=studyClass.getSubjects();
					if(ObjectFunctions.isNotNullOrEmpty(subjectsSet)){
						setSubjectsList(ConvertUtil.convertSetToList(subjectsSet));
						subjectsSet=null;
					}
				}
				setSubTypesList(studentManager.getAllByCustId("SubType",getViewStudentPersonAccountDetails().getCustId(), getViewStudentPersonAccountDetails().getAcademicYearId()));
			}
			}catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
   @Actions({
		@Action(value = "ajaxGetStudentMonthwiseAttendance", results = {  @Result(type = "json", name = "success", params = {"includeProperties","wishDescription" }) })
	})
		public String ajaxGetStudentMonthwiseAttendance() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentMonthwiseAttendance' method");
		}
		try
		{
				getBaseStudentAttendance();
				if(ObjectFunctions.isNotNullOrEmpty(getObjectList())){
					List<ExamSchedules> attendanceList=getObjectList();
					JSONObject ja = new JSONObject();
					JSONArray monthNames=new JSONArray();
					JSONArray workingDays=new JSONArray();
					JSONArray absentDays=new JSONArray();
					JSONArray totalSeriesArray=new JSONArray();
					JSONObject seriesObj = new JSONObject();
					//double failAttPercentage=0;
					for(ExamSchedules schedule:attendanceList){
						if(schedule.getTotalWorkingDays() >0 )
						{
							//failAttPercentage=roundTwoDecimals(((double) schedule.getTotalAbsentDays() / (double) schedule.getTotalWorkingDays()) * 100);
							if(schedule.getAttendancePercentage() > 0){
								monthNames.put(schedule.getMonthName());
								workingDays.put(schedule.getTotalWorkingDays());
								log.debug(schedule.getAttTotalAbsentDays());
								if(schedule.getAttTotalAbsentDays()>0.0)
									absentDays.put(schedule.getAttTotalAbsentDays());
								else
									absentDays.put(schedule.getTotalAbsentDays());
							}
						}
					}
					seriesObj.put("name", "Working Days");
					seriesObj.put("data", workingDays);
					totalSeriesArray.put(seriesObj);
					seriesObj=new JSONObject();
					seriesObj.put("name", "Absent");
					seriesObj.put("data", absentDays);
					totalSeriesArray.put(seriesObj);
					ja.put("series", totalSeriesArray);
					ja.put("categories", monthNames);
					getResponse().getOutputStream().print(ja.toString());
					log.debug(ja.toString());
				}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return null;
	}
   /*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() done by venkatesh - 04-23-2013
	*/
   @Actions( {
		@Action(value = "ajaxGetMyChildFees", results = { @Result(location = "parent/ajaxStudentFeeDetails.jsp", name = "success") }) })
	public String ajaxGetMyChildrenFeeDetails() throws URTUniversalException {
	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetMyChildrenFeeDetails' method");
		}
		try {
			long accountId = getUser().getId();
			if(getTempId() > 0){
				setStudent((Student)studentManager.get(Student.class, getTempId()));
			}else{
				if(getUserAcademicYearId() > 0 && getUser().getId() > 0){
					setStudent(studentManager.getStudentByAccountId(getUser().getId(),getUserAcademicYearId(),getUserCustId()));
				}
			}
			if (!ObjectFunctions.isNullOrEmpty(accountId)) {
				if(getUser().isParent()){
					if(getUserAcademicYearId()>0){
						//setViewStudentPersonAccountDetailsList(adminManager.getParentChildrens(getUserCustId(),getUser().getId(),getUserAcademicYearId()));
						if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
							List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(studList))
								setViewStudentPersonAccountDetailsList(studList);
						}
					}
					if(getTempId1() == 0){
						if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())){
							setViewStudentPersonAccountDetails(getViewStudentPersonAccountDetailsList().get(0));
						}
					}
					if(getTempId1() > 0)
						ajaxDoGetMyFeeDetails();
				}else{
					ajaxDoGetMyFeeDetails();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /*
	* Removed prepareAcademicYearId done by venkatesh - 04-23-2013
	*/
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
         // setHolidayBoardMessagesList(adminManager.getWorkingDayHolidaysByDates(getUserCustId(),"H", DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, calendar.getTime())));
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
   @Actions( { @Action(value = "viewMyChildFees", results = { @Result(location = "parent/viewMyChildFeesDetails.jsp", name = "success") }) })
	public String viewMyChildFees() throws URTUniversalException {
	    if (log.isDebugEnabled()) {
		log.debug("Entering 'viewMyChildFees' method");
	    }
	    try {
	    	ajaxGetMyChildrenFeeDetails();
	    }
	    catch (Exception ex) {
	 	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    }
	return SUCCESS;
	}
   @Actions( {
		@Action(value = "ajaxViewStudentTimeTable", results = { @Result(location = "class/ajaxManageStudentTimeTable.jsp", name = "success") }),
		@Action(value = "ajaxViewStudentTimeTableByClassId", results = { @Result(location = "class/ajaxViewStudentTimeTable.jsp", name = "success") })
  })
  public String ajaxViewStudentTimeTable() throws URTUniversalException {

	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxViewStudentTimeTable' method");
	}

	try {
		if(getUser().isParent()){
			if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
				List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(studList))
					setViewStudentPersonAccountDetailsList(studList);
			}
			if(StringFunctions.isNotNullOrEmpty(getClassId())){
				ajaxGetClassWiseTimeTable();
			}else{
				if(ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())){
					setClassId(String.valueOf(getViewStudentPersonAccountDetailsList().get(0).getClassSectionId()));
					ajaxGetClassWiseTimeTable();
				}
			}
		}else{
			Object[] studDetails= studentManager.get("select id,classSectionId from student where academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId());
			if(!ObjectFunctions.isNullOrEmpty(studDetails[1])){
				setClassId(studDetails[1].toString());
				ajaxGetClassWiseTimeTable();
			}
			studDetails =null;
		}
		/*below line used to when admin upload the timetabe doc it will show the login student and check the file exit or not --->timtable tab*/
		Customer customer = getCustomerByCustId();
		String  filePath = "userFiles/timetable/"+customer.getId()+"/"+getUserAcademicYearId()+"/classtimetables/"+getClassId()+".html".toString();
		//String  filePath = "userFiles/"+customer.getCustomerShortName()+getUserCustId()+"/Timetable/"+(getClassId()+".html").toString();
		File directory = new File(getSession().getServletContext().getRealPath(filePath.toString()));
		if(directory.exists()){
			setAnyTitle(filePath);
			}
		customer=null;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
  }

   /*
	* Removed prepareAcademicYearId and used getUserAcademicYearId() ,getCustomerByCustId() done by venkatesh - 04-23-2013
	*/
	@Actions( { @Action(value = "parentMakePayment", results = { @Result(location = "parent/ajaxDoParentMakePayment.jsp", name = "success") }),
		 @Action(value = "ajaxDoPaymentRecepts", results = { @Result(location = "parent/payment/ajaxStudentFeeReceipts.jsp", name = "success") }),
		 @Action(value = "ajaxParentMakePayment", results = { @Result(location = "parent/payment/ajaxStudentSchloolFeePayment.jsp", name = "success") }) })
	public String studentFeepay() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'studentFeepay' method");
		}
		try {
			Customer customer = getCustomerByCustId();
			if (getUser().isParent()) {
				if (StringFunctions.isNotNullOrEmpty(getParamValue("studentId"))) {
					setStudent((Student) studentManager.get(Student.class, "id="+ getParamValue("studentId") + " and custId=" + getUserCustId()+ " and academicYearId=" + getUserAcademicYearId() + " and description is null"));
				}else{
					//String clause = " parentId=" + getUser().getId()+ " and academicYearId=" + getUserAcademicYearId()+ " and custId=" + getUserCustId() +" and description is null";
					String clause = " accountId=" + getUser().getSelectedStudentId()+ " and academicYearId=" + getUserAcademicYearId()+ " and custId=" + getUserCustId() +" and description is null";
					setViewStudentPersonAccountDetailsList(studentManager.getAll(ViewStudentPersonAccountDetails.class, clause));
					if (ObjectFunctions.isNotNullOrEmpty(getViewStudentPersonAccountDetailsList())) {
						ViewStudentPersonAccountDetails viewStudentPersonAccountDetails = getViewStudentPersonAccountDetailsList().get(0);
						if (!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getStudentId())) {
							setStudent((Student) studentManager.get(Student.class, "id="+ viewStudentPersonAccountDetails.getStudentId() + " and custId=" + getUserCustId()+ " and academicYearId=" + getUserAcademicYearId() + " and description is null"));
						}
					}
				}
				if (!ObjectFunctions.isNullOrEmpty(getStudent())) {
					setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
					setClassFeeList(studentManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and academicYearId="+ getStudent().getAcademicYearId()+" and classId="+getStudent().getClassNameClassId().getId()+" and categoryId="+getStudent().getCategoryId()+" and paymentStatus='"+Constants.NO_STRING+"' group by schoolTermId order by schoolTermId,feeTypeId"));
					setSchoolFeeList(studentManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+ getStudent().getAcademicYearId()+" and categoryId="+getStudent().getCategoryId()));
					
					if(customer.isHostelModuleStatus()){
						if(getStudent().getBed().getId()!=0){
							setObjectList(studentManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and academicYearId="+ getStudent().getAcademicYearId()+" and classId="+getStudent().getClassNameClassId().getId()+" and hostelCategoryId="+getStudent().getCategoryId()+" and paymentStatus='"+Constants.NO_STRING+"' group by hostelTermId order by fromDate, hostelTermName, hostelFeeId"));
							setHostelFeeTypeList(studentManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+getStudent().getAcademicYearId()));
						}
					}
					if(customer.isTransportModuleStatus()){
						if("T".equalsIgnoreCase(getStudent().getTransportMode())){	
							setTransportSchoolFeeList(studentManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+getUserAcademicYearId()));
							setClassFeeCountList(studentManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and academicYearId="+ getStudent().getAcademicYearId()+" and classId="+getStudent().getClassNameId()+" and categoryId="+getStudent().getCategoryId()+" and paymentStatus='"+Constants.NO_STRING+"' group by schoolTermId order by fromDate, termName, feeId"));
						}
				   }
					setAnyTitle(getStudent().getClassSection().getClassAndSection());
					customer=null;
				}
				setExamTypeList(studentManager.getAll(ExamTypes.class, "custId ="+ getUserCustId() + " and academicYearId = "+ getUserAcademicYearId()));
		  }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 @Actions( { @Action(value = "ajaxAddMyChildren", results = { @Result(location = "parent/ajaxAddChildrenDetails.jsp", name = "success") }) })
		public String ajaxAddChildren() throws URTUniversalException {
		    if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddChildren' method");
		    }
		    try {
				if(!StringFunctions.isNullOrEmpty(getAnyTitle())){
					setViewStudentPersonAccountDetails((ViewStudentPersonAccountDetails) studentManager.get(ViewStudentPersonAccountDetails.class, "username='"+getAnyTitle()+"' and status='"+Constants.YES_STRING+"'"));
				}
		    }catch (Exception ex) {
		 	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    }
		return SUCCESS;
		}
	 @Actions( { @Action(value = "ajaxSaveChildren", results = { @Result(location = "parent/ajaxAddMyChildren.jsp", name = "success") }) })
		public String ajaxSaveChildren() throws URTUniversalException {
		    if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveChildren' method");
		    }
		    try {
		    	User user = null;
				if(!ObjectFunctions.isNullOrEmpty(getTempId())){
					user = (User) studentManager.get(User.class,getTempId());
					user.addParentChild(getUser());
					user.getPerson().setParentEmail(getUser().getUsername());
					studentManager.save(user);
					super.addActionMessage("Your children added successfully");
				}
		    }catch (Exception ex) {
		 	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    }
		return SUCCESS;
		}
 /********************************************************************
   * Date              Name               Description
   * ========          ============       ==================
   * Dec 05, 2013      Seshu		      Changed code for getting classes list
  /********************************************************************/   
	 @Actions( {
			@Action(value = "ajaxGetStudyClassList", results = { @Result(location = "../admin/common/ajaxViewStudyClassList.jsp", name = "success") }),
			@Action(value = "ajaxStudentStudyClassList", results = { @Result(location = "../staff/ajaxStudyClassList.jsp", name = "success") }),
			@Action(value = "ajaxDoStudentChangeSection", results = { @Result(location = "ajaxViewStudyClassList.jsp", name = "success") }),
		})
		public String ajaxGetStudyClassList() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetStudyClassList' method");
			}
			try {
				setClassList(null);
				setStudyClassList(null);
				//setStudyClassList(adminManager.GetAllStudyClasses(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" order by "));
				setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
				Collections.sort(getStudyClassList());
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 
	 /********************************************************************
	   * Date              Name               Description
	   * ========          ============       ==================
	   * April 19, 2013    Pvsl		      	  Modularization  bellow method used to get StudyClass list by available classCapacity
	   * Dec 5, 2013       Seshu			  Studyclass list code change.
	  /********************************************************************/   
	 @Actions( { @Action(value = "ajaxDoAddStudent", results = { @Result(location = "../admin/student/ajaxAddStudent.jsp", name = "success"),
				@Result(location = "../admin/student/ajaxViewStudentsList.jsp", name = "error")}) })
		public String doAddStudent() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'doAddStudent' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getUserAcademicYearId()))
				{
				setStudentsList(null);
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				setStudyClassList(adminManager.GetAllStudyClasses( getUserCustId(), getUserAcademicYearId(),null));
				setSchoolCategoriesList(studentManager.getAllByCustId("SchoolCategory", getUserCustId(),0));
				setHouseTypeList(studentManager.getAll(HouseType.class));
				Customer customer= getCustomerByCustId();
//				double committedConfiguredFee = this.getCommittedConfiguredFeeAmountCategoryWise(getUserCustId(),getUserAcademicYearId(),classSectionId,categoryId,feeSettingIds);
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					if(customer.isTransportModuleStatus())
					//getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());
					setCustomer(customer);
				}
				customer=null;
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 /*
		* changed the code for modularity by venkatesh - 05-16-2013
		*/
	@Actions( { @Action(value = "ajaxDoAddNewStudent", results = {@Result(location = "../admin/student/ajaxDoAddNewStudent.jsp", name = "success"),
			@Result(location = "../admin/student/ajaxAddStudent.jsp", name = "userNameAlreadyAdded") }) })
	public String ajaxDoAddNewStudent() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddNewStudent' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getUserAcademicYearId()))
			{
				setObjectList(null);
				setTempList1(null);
				if(StringFunctions.isNotNullOrEmpty(getParamValue("plTitle")))
					setPlTitle(getPlTitle());
				StudyClass classSection = null;
				if(StringFunctions.isNotNullOrEmpty(getParamValue("anyId")))
					setTempId1(Long.valueOf(getParamValue("anyId")));
				if(StringFunctions.isNotNullOrEmpty(getParamValue("eventId")))
					setTempId2(Long.valueOf(getParamValue("eventId")));
				
				getSession().removeAttribute("AdmisionNumber");
				getSession().removeAttribute("NewUserForStudent");
				getSession().removeAttribute("PersonForStudent");
				getSession().removeAttribute("StudentForStudent");
				//getSession().removeAttribute("upload");
				if (!ObjectFunctions.isNullOrEmpty(getUserVo())) { 
					
					log.debug("Adminssion Number:" + getUserVo().getAdmissionNumber());
					
					Customer customer= getCustomerByCustId();
					setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
					setCastSettingList(studentManager.getAllByCustId("CastSettings",getUserCustId(),0));
					setObjectList((List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST));	
					setTempList1(studentManager.getAll(CommonType.class, "custId="+getUserCustId()+" and type='RELIGION'"));
					
					if(!ObjectFunctions.isNullOrEmpty(getStudyClassId()))
					{
						classSection = (StudyClass)studentManager.get(StudyClass.class,"id="+getStudyClassId()+" ");
                    	if(!ObjectFunctions.isNullOrEmpty(classSection))
                    	{
                    		setTitle(classSection.getClassAndSection());
                    		
                    		UserImage attachment;
                    		long imageId= 0;
							if (getUploadFileName() != null && !ObjectFunctions.isNullOrEmpty(customer)) {
		                        try {
		                                attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE, customer.getId(),getUserAcademicYearId(),imageId);
		                                if (!ObjectFunctions.isNullOrEmpty(attachment)) {
		                                	getStudentVo().setProfileImageId(attachment.getId());
		                                }
		                                attachment =null;
		                        } catch (Throwable ex) {
		                               ex.printStackTrace();
									}
								}
								else if(!StringFunctions.isNullOrEmpty(getParamValue("customerImage"))){
									try {
										attachment = captureStudentImage(getParamValue("customerImage"),Constants.FILE_TYPE_IMAGE,customer);
										if (!ObjectFunctions.isNullOrEmpty(attachment)) {
											getStudentVo().setProfileImageId(attachment.getId());
										}
										attachment =null;
									}
									catch (Throwable ex) {
											ex.printStackTrace();
										}
									}
							getSession().setAttribute("NewUserForStudent", getUserVo());
							getSession().setAttribute("PersonForStudent", getPersonVo());
							getSession().setAttribute("StudentForStudent", getStudentVo());
							getSession().setAttribute("AddressForStudent", getAddressVo());
							classSection=null;
                    	}
					}
					customer = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally{
			doAddStudent();
		}
		
		return SUCCESS;
	}
	/*
	* used getCurrentAcademicYear() and getCustomerByCustId() for getting object done by venkatesh - 04-19-2013
	*/
	@Actions( { @Action(value = "ajaxAddStudent", results = { @Result(location = "../admin/common/ajaxViewStudyClassList.jsp", name = "success"),
															  @Result(location = "../admin/student/ajaxDoAddNewStudent.jsp", name = "classStrenthOver")}) })
	public String addStudent() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'addStudent' method");
		}
		try {
			String parentId = null;
			User checkParentEmail = null;
			CastSettings castSettings =null;
			CommonType commonType =null;
			double deductingAmnt  = 0;
			Customer customer = getCustomerByCustId();
			SMSServiceProviders smsServiceProvider=(SMSServiceProviders)  adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			UserVO studentAccountVo = (UserVO) getSession().getAttribute("NewUserForStudent");
			PersonVO studentPersonVo = (PersonVO) getSession().getAttribute("PersonForStudent");
			StudentVo studentStudentVo = (StudentVo) getSession().getAttribute("StudentForStudent");
			AddressVO studentAddressVo = (AddressVO) getSession().getAttribute("AddressForStudent");

			AcademicYear academicYear = getCurrentAcademicYear();
			if (!ObjectFunctions.isNullOrEmpty(studentAccountVo) && !ObjectFunctions.isNullOrEmpty(studentPersonVo) && !ObjectFunctions.isNullOrEmpty(academicYear) && !ObjectFunctions.isNullOrEmpty(customer)) {
				String classId = getParamValue("studyClassId");
				StudyClass studyClass = (StudyClass) studentManager.get(StudyClass.class, Long.valueOf(classId));
				if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
					String userName = "";
					
					if(!ObjectFunctions.isNullOrEmpty(studentAccountVo))
						studentAccountVo.setAdmissionNumber(studentAccountVo.getAdmissionNumber().replace("'", ""));
					
					if (customer.getId()>0 && !StringFunctions.isNullOrEmpty(studentAccountVo.getAdmissionNumber())) {
							userName = StringFunctions.stripSymbols(customer.getId()+"S"+studentAccountVo.getAdmissionNumber().replace("'", ""));
							studentAccountVo.setUsername(userName.toLowerCase());
							studentAccountVo.setSecondaryUsername(userName.toLowerCase());
					} else {
						super.addActionError("Admission number and customer short name is required.");
						return "classStrenthOver";
					}
					
					//setNewUser(studentAccountVo);
					studentPersonVo.setNationality(getPersonVo().getNationality());
					studentPersonVo.setCommunityNumber(getPersonVo().getCommunityNumber());
					studentPersonVo.setRationCardNumber(getPersonVo().getRationCardNumber());
					studentPersonVo.setIdentification1(getPersonVo().getIdentification1());
					studentPersonVo.setIdentification2(getPersonVo().getIdentification2());
					studentPersonVo.setMotherToungId(getPersonVo().getMotherToungId());
					if(getPersonVo().getCastId() !=0){
						studentPersonVo.setCastId(getPersonVo().getCastId());
					}
					else{
						castSettings =(CastSettings) studentManager.get(CastSettings.class,"custId="+getUserCustId()+" and castName like '%"+"Other"+"%' ");
						if(!ObjectFunctions.isNullOrEmpty(castSettings)){
							studentPersonVo.setCastId(castSettings.getId());
							castSettings = null;
						}
					}
					
					if(getPersonVo().getReligionId() !=0){
						studentPersonVo.setReligionId(getPersonVo().getReligionId());
					}
					else{
						commonType=(CommonType) studentManager.get(CommonType.class,"custId="+getUserCustId()+" and skillTypeName like '%"+"Other"+"%' and type like '%"+"RELIGION"+"%' ");
						if(!ObjectFunctions.isNullOrEmpty(commonType)){
							studentPersonVo.setReligionId(commonType.getId());
						}
						commonType = null;
					}
					
					studentPersonVo.setSubCastId(getStudentVo().getAccount().getPersonVo().getSubCastId());
					studentPersonVo.setSummary(getPersonVo().getSummary());
					if("Y".equalsIgnoreCase(getPlTitle())){
						studentPersonVo.setPhId(true);
						studentPersonVo.setPhysicallyHandicappedDesc(studentPersonVo.getPhysicallyHandicappedDesc());
					}else{
						studentPersonVo.setPhId(false);
						studentPersonVo.setPhysicallyHandicappedDesc(null);
					}
					if(!ObjectFunctions.isNullOrEmpty(getStudentVo())){
						if(!StringFunctions.isNullOrEmpty(getStudentVo().getGoals()))
							studentStudentVo.setGoals(getStudentVo().getGoals());
						if(!StringFunctions.isNullOrEmpty(getStudentVo().getStrengths()))
							studentStudentVo.setStrengths(getStudentVo().getStrengths());
						if(!StringFunctions.isNullOrEmpty(getStudentVo().getInterestsAndHobbies()))
							studentStudentVo.setInterestsAndHobbies(getStudentVo().getInterestsAndHobbies());
						if(!StringFunctions.isNullOrEmpty(getStudentVo().getResponsibilities()))
							studentStudentVo.setResponsibilities(getStudentVo().getResponsibilities());
						if(!StringFunctions.isNullOrEmpty(getStudentVo().getAchievements()))
							studentStudentVo.setAchievements(getStudentVo().getAchievements());
						if(!StringFunctions.isNullOrEmpty(getStudentVo().getRemarks()))
							studentStudentVo.setRemarks(getStudentVo().getRemarks());						
					}
					
					//studentPersonVo.setStudentMobile(StringFunctions.getMobileNumberLengthChecking(studentPersonVo.getStudentMobile()));
					studentPersonVo.setStudentMobile(studentPersonVo.getStudentMobile());
					
					AddressVO  taddressVo = new AddressVO();
					//taddressVo.setStreetName(studentStudentVo.getAccount().getTempararyAddressVo().getStreetName());
					taddressVo.setAddressLine1(studentStudentVo.getAccount().getTempararyAddressVo().getAddressLine1());
					taddressVo.setAddressLine2(studentStudentVo.getAccount().getTempararyAddressVo().getAddressLine2());
					taddressVo.setCity(studentStudentVo.getAccount().getTempararyAddressVo().getCity());
					taddressVo.setPostalCode(studentStudentVo.getAccount().getTempararyAddressVo().getPostalCode());
					taddressVo.setState(studentStudentVo.getAccount().getTempararyAddressVo().getState());
					studentAccountVo.setTempararyAddressVo(taddressVo);
					
					AddressVO  paddressVo = new AddressVO();
					//paddressVo.setStreetName(studentAddressVo.getStreetName());
					paddressVo.setAddressLine1(studentAddressVo.getAddressLine1());
					paddressVo.setAddressLine2(studentAddressVo.getAddressLine2());
					paddressVo.setCity(studentAddressVo.getCity());
					paddressVo.setPostalCode(studentAddressVo.getPostalCode());
					paddressVo.setState(studentAddressVo.getState());
					paddressVo.setHouseTypeId(studentAddressVo.getHouseTypeId());
					studentAccountVo.setPrimaryAddressVo(paddressVo);
					
					studentAccountVo.setCustId(getUserCustId());
					studentAccountVo.setPersonVo(studentPersonVo);
					
					studentAccountVo.setPassword(PasswordUtils.passwordEncoder(userName.toLowerCase(), null));
					StringBuffer pathName = new StringBuffer("userFiles/").append(customer.getId());
				    pathName.append("/");
				    pathName.append(studentAccountVo.getAdmissionNumber());
				    pathName.append("/");
				    if(StringFunctions.isNotNullOrEmpty(pathName.toString())){
				    	File destFile = new File(getSession().getServletContext().getRealPath(pathName.toString()));
				    	if(!ObjectFunctions.isNullOrEmpty(destFile)){
				    	if(destFile.mkdirs())
							log.debug(getSession().getServletContext().getRealPath(pathName.toString())+" directory created.");
						else
							log.debug(getSession().getServletContext().getRealPath(pathName.toString())+" directories Not Created");
				    	}
					}
				   
				    List<PersonDocuments> studentDocList=new ArrayList<PersonDocuments>();
				    for(int i=0;i<getFileUpload().size();i++)
			    	{
			    		 File file = getFileUpload().get(i);
			    		 if(!ObjectFunctions.isNullOrEmpty(file))
			    		 {
				    		 String fileName = getFileUploadFileName().get(i);
				    		 String filePath = adminManager.getUploadDocuments(file, academicYear.getAcademicYear(), fileName);
			    			if(!StringFunctions.isNullOrEmpty(filePath))
			    			{
			    				PersonDocuments personDocuments = new PersonDocuments();
			    				personDocuments.setFileName(fileName);
			    				personDocuments.setCreatedById(getUser().getId());
			    				personDocuments.setFilePath(filePath);
			    				personDocuments.setAcademicYearId(getUserAcademicYearId());
			    				studentDocList.add(personDocuments);
			    			}
			    		 }
			    	}
				    if("N".equalsIgnoreCase(customer.getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(customer.getAlphaNumericRollNumber())){
				    	if (StringFunctions.isNullOrEmpty(studentStudentVo.getRollNumber()))
				    		studentStudentVo.setRollNumber(studentManager.generateStudnetRollNumber(studyClass.getId(),studyClass.getCustId(),studyClass.getAcademicYearId()));
				    }
				    else
				    	studentStudentVo.setRollNumber(studentStudentVo.getRollNumber());
				    
				    AcademicYearVo academicYearVo = academicYear.copyFromEntityToVo(academicYear);
					studentStudentVo.setAcademicYearVo(academicYearVo);
					if (studentStudentVo.getProfileImageId() > 0) {
						
						UserImage profileImage = (UserImage) studentManager.get(UserImage.class, studentStudentVo.getProfileImageId());
						studentStudentVo.setProfileImage(profileImage.copyFromEntityToVo(profileImage));
					}
					if (!ObjectFunctions.isNullOrEmpty(studyClass)) 
					{
						ClassNameVO classNameVo = null;
						if (!ObjectFunctions.isNullOrEmpty(studyClass.getClassNameClassId().getId())) 
						{
							ClassName className=(ClassName)studentManager.get(ClassName.class, studyClass.getClassNameClassId().getId());
							classNameVo = studyClass.getClassNameClassId().copyFromEntityToVo(className, academicYearVo);
							studentStudentVo.setClassNameVo(classNameVo);
							className = null;
						}
						
						studentStudentVo.setStudyClassVo(studyClass.copyFromEntityToVo(studyClass, academicYearVo, classNameVo));
					}
					
					StringBuffer feeSettingIds = new StringBuffer("1,2");
					
					if(customer.isHostelModuleStatus()){
						studentStudentVo.setHostelMode(studentStudentVo.getHostelMode());
						feeSettingIds.append(",4");
					}else{
						studentStudentVo.setHostelMode("D");
					}
					List<String> schoolTermIds = studentManager.getAll("select id from schoolTerms where custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and feeSettingId in ("+feeSettingIds.toString()+")");
					if(ObjectFunctions.isNullOrEmpty(schoolTermIds))
						schoolTermIds.add("0");
					Fee fee =(Fee)studentManager.get(Fee.class, "classId="+studyClass.getClassNameClassId().getId()+" and categoryId="+studentStudentVo.getCategoryId()+" and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
					
					if(!ObjectFunctions.isNullOrEmpty(fee)){
						studentStudentVo.setFeeConfigured(Constants.YES_STRING);
					}
					else{
						studentStudentVo.setFeeConfigured(Constants.NO_STRING);
					}
					studentStudentVo.setFeePaidStatus(Constants.NO_STRING);
					studentStudentVo.setCommittedFee(getStudentVo().getCommittedFee());
					if(!StringFunctions.isNullOrEmpty(getStudentVo().getGoals()))
						studentStudentVo.setGoals(getStudentVo().getGoals());
					if(!StringFunctions.isNullOrEmpty(getStudentVo().getStrengths()))
						studentStudentVo.setStrengths(getStudentVo().getStrengths());
					if(!StringFunctions.isNullOrEmpty(getStudentVo().getInterestsAndHobbies()))
						studentStudentVo.setInterestsAndHobbies(getStudentVo().getInterestsAndHobbies());
					if(!StringFunctions.isNullOrEmpty(getStudentVo().getResponsibilities()))
						studentStudentVo.setResponsibilities(getStudentVo().getResponsibilities());
					if(!StringFunctions.isNullOrEmpty(getStudentVo().getAchievements()))
						studentStudentVo.setAchievements(getStudentVo().getAchievements());
					if(!StringFunctions.isNullOrEmpty(getStudentVo().getRemarks()))
						studentStudentVo.setRemarks(getStudentVo().getRemarks());
					
					if(!StringFunctions.isNullOrEmpty(studentAccountVo.getEnrollmentCode()))
					{
						Object[] enrollmentCodeUserObj = adminManager.get("select id,enrollmentCode from Account where custId=" +getUserCustId() + " and enrollmentCode ='" + studentAccountVo.getEnrollmentCode() + "'");
						if(!ObjectFunctions.isNullOrEmpty(enrollmentCodeUserObj))
						{
							super.addActionError("Student enrollment code already exists.");
							studentAccountVo.setEnrollmentCode(null);
						}
						enrollmentCodeUserObj = null;
					}
					log.debug(getFileUpload().size());
					//log.debug(upload);
					ajaxUpdateClassSectionCapacity(studyClass);
					User createduser = studentManager.saveOrUpdateStudent(studentStudentVo,studentAccountVo,studentPersonVo,studentAddressVo,customer,smsServiceProvider,studentDocList,getUser());
					if(!ObjectFunctions.isNullOrEmpty(createduser)) 
					{
						
						adminManager.generateBarcode(createduser.getId());
						super.addActionMessage("Student details added successfully.");
					}
					createduser = null;
					studyClass = null;
				}
				academicYear = null;
			}
			//academicYear.setAllotedsms(adminManager.getAllottedSmsCount(getUserCustId(),getUserAcademicYearId()));
			customer=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally{
			getSession().removeAttribute("NewUserForStudent");
			getSession().removeAttribute("PersonForStudent");
			getSession().removeAttribute("StudentForStudent");
			getSession().removeAttribute("AdmisionNumber");
			//getSession().removeAttribute("GetAllStudyClasses");
			getSession().removeAttribute("GetAllClassNames");
			getSession().removeAttribute("upload");
			getCustomerAndStudyClassAndAcademicYear();
		}
		
		return SUCCESS;
	}
	
	/*
	* used getUserAcademicYearId() done by venkatesh - 04-16-2013 Modified on 10/102014
	*/
	@Actions( { //@Action(value = "ajaxViewStudentAcademics", results = { @Result(location = "../common/ajaxViewStudentAndstaffInfo.jsp", name = "success") }),
		 //@Action(value = "ajaxViewStudentDetails", results = { @Result(location = "../common/ajaxViewStudentDetails.jsp", name = "success") }) 	
	})
	public String ajaxViewStudentAcademic() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudentAcademic' method");
		}
		try {
			if(getUserAcademicYearId() > 0)
			{
				setCustomer(getCustomerByCustId());
				
				if(StringFunctions.isNullOrEmpty(getAnyId()))
				{
					setAnyId(getParamValue("studentId"));  
				}
				
				if(!StringFunctions.isNullOrEmpty(getAnyId()))
				{
					//setStudent((Student)studentManager.get(Student.class,"id="+getAnyId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()));
					Student student = (Student)studentManager.get(Student.class,"id="+getAnyId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId());
					//StudentVo studentVo = convertStudentToStudentVo(student);
					
					StudentVo studentVo = student.copyFromEntityToVo(student);
					
					if("T".equalsIgnoreCase(studentVo.getTransportMode())){
						getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(student.getRouteBoardingPoints()))
						{	
							setAnyId(String.valueOf(student.getRouteBoardingPoints().getId()));
							setEventId(String.valueOf(student.getVehicleAcademicDetailsId()));
						}
					}
					setStudentVo(studentVo);
					studentVo = null;
					student = null;
				}
				
				setSchoolCategoriesList(studentManager.getAllByCustId("SchoolCategory", getUserCustId(),0)); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewStudentAcademics", results = { @Result(location = "../common/ajaxViewStudentAndstaffInfo.jsp", name = "success") }),
		@Action(value = "ajaxViewStudentDetails", results = { @Result(location = "../common/ajaxViewStudentDetails.jsp", name = "success") }) 	
	})
	public String ajaxViewStudentDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudentAcademic' method");
		}
		try {
			if(getUserAcademicYearId() > 0)
			{
				log.debug("getUserAcademicYearId() > 0 :"+getUserAcademicYearId());
				Student student = null;
				StudentVo studentVo = null;
				if(!ObjectFunctions.isNullOrEmpty(getCustomer()))
					setCustomer(getCustomerByCustId());
				if (getUser().isSchoolAdmin() || getUser().isSchoolClerk()) {
					//Here need to reduce the code for getting the studyClass details in drop down
					setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(), getUserAcademicYearId(),null));
					if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList()))
						Collections.sort(getStudyClassList());
				} else if (getUser().isSchoolTeacher()) {
					ajaxGetStaffStudyClasses();
				}
				if(StringFunctions.isNullOrEmpty(getAnyId()))
				{
					setAnyId(getParamValue("studentId"));  
				}
				if(!StringFunctions.isNullOrEmpty(getAnyId()) && !StringFunctions.isNullOrEmpty(getClassId())){
					//setStudent((Student)studentManager.get(Student.class,"id="+getAnyId()+" and classSectionId="+getClassId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()));
					student = (Student)studentManager.get(Student.class,"id="+getAnyId()+" and classSectionId="+getClassId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId());
				}else{
					if(!StringFunctions.isNullOrEmpty(getAnyId()))
						//setStudent((Student)studentManager.get(Student.class,"id="+getAnyId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()));
						student = (Student)studentManager.get(Student.class,"id="+getAnyId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId());
				}
				
				if(!ObjectFunctions.isNullOrEmpty(student)){
					studentVo = student.deepCopyEntityToVO(student);
					setStudentVo(studentVo);
					//Here need to reduce the code for getting the student details in drop down 
					setStudentsList(studentManager.getAll(Student.class,"classSectionId="+student.getClassSection().getId()+" and description is null"));
					if(ObjectFunctions.isNotNullOrEmpty(getStudentsList()))
						Collections.sort(getStudentsList());
				}else{
					//setStudentsList(studentManager.getAll(Student.class,"custId="+getUserCustId()+" and classSectionId="+getClassId()+" and academicYearId="+getUserAcademicYearId()+" and description is null"));
					if(!StringFunctions.isNullOrEmpty(getClassId())){
					setStudentsList(studentManager.getAll(Student.class,"classSectionId="+getClassId()+" and description is null"));
					if(ObjectFunctions.isNotNullOrEmpty(getStudentsList()))
						//setStudent((Student) getStudentsList().get(0));
						student = (Student) getStudentsList().get(0);
						if(!ObjectFunctions.isNullOrEmpty(student)){
							studentVo = student.deepCopyEntityToVO(student);
							setStudentVo(studentVo);
							Collections.sort(getStudentsList());
							if(!ObjectFunctions.isNullOrEmpty(getStudent()))	
								setAnyId(String.valueOf(studentVo.getId()));
						}
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(student)){
				setClassId(String.valueOf(studentVo.getStudyClassVo().getId()));
				setAnyId(String.valueOf(studentVo.getId()));
				setTempList(null);
				if(!ObjectFunctions.isNullOrEmpty(getStudyClassList())){
					for(StudyClass studyClass : getStudyClassList()){
						if(!ObjectFunctions.isNullOrEmpty(studyClass.getClassNameClassId())){
							if(!ObjectFunctions.isNullOrEmpty(studyClass.getClassNameClassId().getId())){
								if(studyClass.getClassNameClassId().getId() == getStudentVo().getClassNameVo().getId()){
									getTempList().add(studyClass);
								}
							}
						}
					}
				}
				//setClassList(studentManager.getAll(StudyClass.class, "classNameClassId="+getStudent().getClassNameClassId().getId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
				 if(ObjectFunctions.isNotNullOrEmpty(getTempList())){
					 setClassList(getTempList());
					 Collections.sort(getClassList());
				 }
				 /*if("T".equalsIgnoreCase(getStudentVo().getTransportMode())){
					getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());
				 }*/
				 //getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());
				 setSchoolCategoriesList(studentManager.getAllByCustId("SchoolCategory", getUserCustId(),0)); 
				 log.debug("setSchoolCategoriesList");
				}
				/*if(getCustomer().isHostelModuleStatus())
				setHostelCategoriesList(studentManager.getAllByCustId("HostelCategory", getUserCustId(),0));*/
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxSearchStudentByCriteriaDetails", results = { @Result(location = "../admin/common/ajaxViewSearchStudentList.jsp", name = "success") }),
				@Action(value = "ajaxSearchTransferStudentDetails", results = { @Result(location = "../admin/student/transferStudent/ajaxViewSearchStudentList.jsp", name = "success") }),
				@Action(value = "ajaxGetNoImageStudents", results = { @Result(location = "../admin/common/ajaxViewNoImageStudentList.jsp", name = "success") }),
				@Action(value = "ajaxGetCCReportStudents", results = { @Result(location = "../admin/reports/ajaxViewCCReportStudents.jsp", name = "success") })
	})
	public String studentByCriteriaDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchStudentByCriteriaDetails' method");
		}
		try {
			setCustomer(getCustomerByCustId());//customer have student permission settings or not checked in the page level
			//setEmpId("StudentInfo");
			prepareStudentsDetailsList();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed PrepareAcademicYearId and used getUserAcademicYearId() is done by venkatesh - 04-19-2013
	*/
	@Actions( { @Action(value = "ajaxEditStudent", results = { @Result(location = "../common/ajaxViewStudentAndstaffInfo.jsp", name = "success") }),
		@Action(value = "ajaxEditStudentPersonlInformation", results = { @Result(location = "../common/ajaxViewStudentPersonalInformation.jsp", name = "success") })
		})
	public String editStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editStudent' method");
		}
			long studId=getStudentVo().getId();
			try {
				if (studId != 0) {
					List<ViewStudentClassDetails> viewStudentClassDetailsList = null;
					List<User> userDetailsList = null;
					
					String admissionNumber = getStudentVo().getAccount().getAdmissionNumber();
					if(StringFunctions.isNullOrEmpty(admissionNumber))
						admissionNumber = getAnyTitle();
					else
						setAnyTitle(admissionNumber);
					
						if(!StringFunctions.isNullOrEmpty(admissionNumber)){
						java.util.regex.Matcher matcher = Pattern.compile("[\"'](.+)[\"']").matcher(admissionNumber);
				        if (matcher.find()) {
				        	admissionNumber= matcher.group(1);
				        }
					}
					Customer customer=getCustomerByCustId();
					SMSServiceProviders smsServiceProvider=(SMSServiceProviders)  adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
					//AcademicYear academicYear = (AcademicYear)adminManager.get(AcademicYear.class, "id="+getUserAcademicYearId());
					if(!StringFunctions.isNullOrEmpty(admissionNumber)){
						if (customer.isAddStudentsSameAdmissionNumber()) {
							StudyClass studyClass = (StudyClass) adminManager.get(StudyClass.class, "id="+ getStudentVo().getStudyClassVo().getId());
							viewStudentClassDetailsList = studentManager.getAll(ViewStudentClassDetails.class, "custId ="+ getUserCustId() + " and classId ="+ studyClass.getClassNameClassId().getId()+ " and accountExpired!='"+Constants.YES_STRING+"' and admissionNumber='"+ admissionNumber.trim()+ "' and studId <> " + studId);
							if (!ObjectFunctions.isNullOrEmpty(viewStudentClassDetailsList)) {
								super.addActionError("Addmission number already exists with in the class.");
								return SUCCESS;
							}
							studyClass = null;
						} else {
							userDetailsList = studentManager.getAll(User.class,"custId =" + getUserCustId()+ " and accountExpired!='"+Constants.YES_STRING+"' and admissionNumber= '"+ admissionNumber.trim() + "' and id <> "+ getStudentVo().getAccount().getId());
							if (!ObjectFunctions.isNullOrEmpty(userDetailsList)) {
								super.addActionError("Addmission number already exists.");
								return SUCCESS;
							}
						}
					}
					
					PromoteClass promoteClass = (PromoteClass)adminManager.get(PromoteClass.class, "currentClassSectionId="+getStudentVo().getStudyClassVo().getId()+" and academicYearId="+getUserAcademicYearId() + " and promoteProcessCompleted='"+Constants.YES_STRING+"'");
					if(!ObjectFunctions.isNullOrEmpty(promoteClass))
					{
						super.addActionError("You can only change the details after shifting the academic year.");
						return SUCCESS;
					}
					String transportStatus = getStudentVo().getTransportMode();
					String parentEmail=null;
					double deductingAmnt = 0;
					
					UserImage attachment;
					log.debug("editStudent prepareStudentSchoolFeeSettingListFromVo: 5260");
					log.debug("studId :"+studId);
					ViewStudentPersonAccountDetails stdDetails =  (ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class,"studentId="+studId+" ");
					if(!ObjectFunctions.isNullOrEmpty(stdDetails)){
						log.debug("stdDetails in side if:");
						long studImage = 0;
						if(!ObjectFunctions.isNullOrEmpty(stdDetails.getImageId()))
							studImage = Long.valueOf(stdDetails.getImageId());
						if (getUploadFileName() != null) {
							try {
								attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE,customer.getId(),getUserAcademicYearId(),studImage);
								if (!ObjectFunctions.isNullOrEmpty(attachment)) {
									log.debug("attachment in side if:");
									//getStudent().getAccount().setProfileImage(attachment); // when admin upload student image save imageId in student changed by cvs
									
									getStudentVo().setProfileImage(attachment.copyFromEntityToVo(attachment));
									log.debug("attachment.copyFromEntityToVo(attachment)");
								}
								attachment = null;
							} catch (Throwable ex) {
							   ex.printStackTrace();
						}
							
						}
						else if(!StringFunctions.isNullOrEmpty(getParamValue("customerImage"))){
							try {
								log.debug("captureStudentImage else if");
								attachment = captureStudentImage(getParamValue("customerImage"),Constants.FILE_TYPE_IMAGE,customer);
								if (!ObjectFunctions.isNullOrEmpty(attachment)) {
									log.debug("captureStudentImage in side else if");
									//getStudent().setProfileImage(attachment);
									getStudentVo().setProfileImage(attachment.copyFromEntityToVo(attachment));
								}
								attachment =null;
							}
							catch (Throwable ex) {
									ex.printStackTrace();
								}
						}
					}
					
						
					if(getStudentVo().getAccount().getPersonVo().isPhId()){
						getStudentVo().getAccount().getPersonVo().setPhysicallyHandicappedDesc(getStudentVo().getAccount().getPersonVo().getPhysicallyHandicappedDesc());
					}else
						getStudentVo().getAccount().getPersonVo().setPhysicallyHandicappedDesc(null);
					String adminNos = stdDetails.getAdmissionNumber();
					log.debug(adminNos);
					String addressType = getStudentVo().getAccount().getPersonVo().getAddressType();
					String mobileNumber = null;
					String secondaryMobileNumber = null;
					String studMobileNumber = null;
					//log.debug(adminNos);
					log.debug("addressType :"+addressType);
					if("R".equalsIgnoreCase(addressType)){
						mobileNumber = stdDetails.getMobileNumber();
						secondaryMobileNumber = stdDetails.getSecondaryMobileNumber();
					}else{
						mobileNumber = stdDetails.getAnotherMobileNumber();
						secondaryMobileNumber = stdDetails.getAnotherSecondaryMobileNumber();
					}
					studMobileNumber = stdDetails.getStudentMobile();
					
					if(!StringFunctions.isNullOrEmpty(getStudentVo().getAccount().getEnrollmentCode()))
					{
						log.debug("getStudentVo().getAccount().getEnrollmentCode() :");
						//User enrollmentCodeUser = (User)adminManager.get(User.class, " custId=" +getUserCustId() + " and enrollmentCode ='" + getStudentVo().getAccount().getEnrollmentCode() + "' and id !=" + stdDetails.getAccountId());
						Object[] enrollmentCodeUserObj = adminManager.get("select id,enrollmentCode from Account where custId=" +getUserCustId() + " and enrollmentCode ='" + getStudentVo().getAccount().getEnrollmentCode() + "' and id !=" + stdDetails.getAccountId());
						if(!ObjectFunctions.isNullOrEmpty(enrollmentCodeUserObj))
						{
							super.addActionError("Student enrollment code already exists.");
							//enrollmentCodeMessageBuffer.append(studentVoDetails.getAccount().getEnrollmentCode() +", " );
							getStudentVo().getAccount().setEnrollmentCode(null);
						}
						enrollmentCodeUserObj = null;
					}
					
					if(StringFunctions.isNotNullOrEmpty(getStudentVo().getRollNumber()))
					{
						StringBuffer sqlQuery = new StringBuffer(" classSectionId="+ getStudentVo().getStudyClassVo().getId() + " and rollNumber = '"+getStudentVo().getRollNumber()+"' and academicYearId="+getUserAcademicYearId() + " and status='"+Constants.YES_STRING+"'");
						if (stdDetails.getStudentId() >0) {
							sqlQuery.append(" and id <> " + stdDetails.getStudentId());
						}
						//log.debug("select * from  Student where " + sqlQuery.toString());
						Student rollNumberStudent = (Student)studentManager.get(Student.class,sqlQuery.toString());
						if(!ObjectFunctions.isNullOrEmpty(rollNumberStudent))
						{
							super.addActionError("Roll Number already exists.");
							return SUCCESS;
						}
					}
					else{
						if("N".equalsIgnoreCase(customer.getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(customer.getAlphaNumericRollNumber()))
							getStudentVo().setRollNumber(studentManager.generateStudnetRollNumber(getStudentVo().getStudyClassVo().getId(),getUserCustId(),getUserAcademicYearId()));
						else
							getStudentVo().setRollNumber(getStudentVo().getRollNumber());
					}
					
					prepareStudentSchoolFeeSettingListFromVo(getStudentVo());
					
					getStudentVo().setTempString3(getTempString3());
					Map<String, String> msg = studentManager.updateStudent(getStudentVo(), customer, getUserAcademicYearId(),getUser(),stdDetails.getClassNameAndSection(),getAnyTitle(),getTempString(),smsServiceProvider);
					String studentId = msg.get("99");
					if(StringFunctions.isNotNullOrEmpty(studentId))
					{
						log.debug("Student Id return value: " + studentId);
						setStudent((Student)studentManager.get(Student.class,Long.valueOf(studentId)));
						log.debug("Student Id: " + studentId + " custId: " + getStudent().getCustId());
						log.debug(getStudent().getStatus());
						String oldUsername = getStudent().getAccount().getUsername();
						User user = (User) studentManager.get(User.class,Long.valueOf(getStudent().getAccount().getId()));
						log.debug(" before username :"+getStudent().getAccount().getUsername());
						if(!ObjectFunctions.isNullOrEmpty(user)){
							setCustomer(customer);
							user = ajaxStudentDisableOrTransfer(user,getStudent());
						}
						log.debug(" after username :"+getStudent().getAccount().getUsername()+" new user :"+user.getUsername());
						//user = null;
						msg = null;
						log.debug("updateClassAndSectionCapacity");
						studentManager.updateClassAndSectionCapacity(getUserCustId(),getUserAcademicYearId(),getStudent().getClassNameId(),0);
							stdDetails = null;
						String StudentStatus=getParamValue("status");
						if("on".equalsIgnoreCase(StudentStatus))
							super.addActionMessage(getStudent().getStudentName()+" student disabled successfully.");
						else
							super.addActionMessage("Student details updated successfully.");
						
						if(!ObjectFunctions.isNullOrEmpty(getStudent().getStatus())){
							setAnyId(String.valueOf(getStudent().getId()));
							setTempId(getStudent().getId());
						}
						setClassId(String.valueOf(getStudent().getClassSection().getId()));
						if(StringFunctions.isNotNullOrEmpty(getStudentVo().getAccount().getAdmissionNumber()) && StringFunctions.isNotNullOrEmpty(addressType)){
							log.debug("addressType "+addressType);
							if(!ObjectFunctions.isNullOrEmpty(getStudent().getAccount().getStudentParent().getId())){
								User parentUserObject = (User) studentManager.get(User.class,Long.valueOf(getStudent().getAccount().getStudentParent().getId()));
								//Added by Siva on making parent mobile number as username
								String newPassword = StringUtil.generateRandomString();
								if(StringFunctions.isNotNullOrEmpty(user.getPerson().getMobileNumber())){
									 // Comment this code for remove the entire system in +91- before added the mobile number. RaviTeja 25-07-2016 
										if(!user.getPerson().getMobileNumber().equalsIgnoreCase(parentUserObject.getUsername())){
											String userName = user.getPerson().getMobileNumber();
											User parentUser = studentManager.usernameAvailabulity(userName,getUserCustId());
											if(!ObjectFunctions.isNullOrEmpty(parentUser)){
												/*Ganesh Here we will check mobile number available. If mobile number available we will check that mobile user parent or not. If that user parent we will not create new user and we will assign that parent id to existing user. If that existing user not parent we will create with "P"+mobilenumber. Here we wuill check with that username if user available we will assign this new student to exiting parent. */
												if(!parentUser.isParent() || !parentUser.isAdmin() || !parentUser.isSchoolStudent()){
													userName ="P"+userName;
													parentUser = studentManager.usernameAvailabulity(userName,getUserCustId());
												}
											}
											parentUserObject.setUsername(userName);
											parentUserObject.setSecondaryUsername(String.valueOf(parentUserObject.getId()));
											parentUserObject.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
											parentUserObject = (User)studentManager.saveOrUpdateObject(parentUserObject);
											String adminssionNumberStatus = "Yes";
											if(customer.isCheckMobileService()){
												int sentSmsCount=studentManager.getTotalSmsCount(customer.getId(),getUserAcademicYearId());
												int allottedSmsCount = (int) getStudent().getAcademicYear().getAllotedsms()+(int) getStudent().getAcademicYear().getPaidSms();
												if((allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)){
													if(!adminNos.equalsIgnoreCase(getAnyTitle()) && !ObjectFunctions.isNullOrEmpty(getAnyTitle()))
														adminssionNumberStatus = "Yes";
														studentManager.sendSmsForParentsAndStudent(mobileNumber,secondaryMobileNumber,studMobileNumber,user,parentUserObject,getStudent(),customer,adminssionNumberStatus,newPassword,smsServiceProvider);
												}
											}
										}else{
											if(customer.isCheckMobileService()){
												if(!user.getUsername().equalsIgnoreCase(getStudent().getAccount().getUsername())){
													int sentSmsCount=studentManager.getTotalSmsCount(customer.getId(),getUserAcademicYearId());
													int allottedSmsCount = (int) getStudent().getAcademicYear().getAllotedsms()+(int) getStudent().getAcademicYear().getPaidSms();
													if((allottedSmsCount!=0 && allottedSmsCount > sentSmsCount)){
														String adminssionNumberStatus = null;
														if(!adminNos.equalsIgnoreCase(getAnyTitle()) && !ObjectFunctions.isNullOrEmpty(getAnyTitle()))
															adminssionNumberStatus = "Yes";
														Set<String> mobileNumbersSet = null;
														String response = null;
														boolean smsStatus = false;
														Messages message = new Messages();
														message.setSmsSenderId(customer.getSender());
														message.setStatus("M");
														message.setCreatedById(getUser().getId());
														message.setSenderName(getUser().getUserRoleDescription());
														String mobileNum = null;
														String secondMobileNum = null;
														Person pobjPerson = getStudent().getAccount().getPerson();
														if("R".equalsIgnoreCase(pobjPerson.getAddressType())){
															mobileNum = pobjPerson.getMobileNumber();
															secondMobileNum = pobjPerson.getSecondaryMobileNumber();
														}else{
															mobileNum = pobjPerson.getAnotherMobileNumber();
															secondMobileNum = pobjPerson.getAnotherSecondaryMobileNumber();
														}
														mobileNumbersSet=new HashSet<String>();
														mobileNumbersSet.add(pobjPerson.getStudentMobile());
														if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
															smsStatus = studentManager.sendSMSForStudent(user,mobileNumbersSet,message,customer.getOrganization(),smsServiceProvider);
															if(smsStatus)
																message = studentManager.saveMessageDetailsTracking(message,getStudent(),null,null);
														}
														mobileNumbersSet=null;
														mobileNumbersSet=new HashSet<String>();
														if(!ObjectFunctions.isNullOrEmpty(mobileNum))
															mobileNumbersSet.add(mobileNum);
														if(!ObjectFunctions.isNullOrEmpty(secondMobileNum))
															mobileNumbersSet.add(secondMobileNum);
														smsStatus = studentManager.sendStudentCredentiolsToParent(customer,parentUserObject.getPerson(),message,user,mobileNumbersSet,smsServiceProvider);
														if(smsStatus)
															message = studentManager.saveMessageDetailsTracking(message,null,null,parentUserObject);
														
													}
												}
											}
											
										}
								}
								//End 
								if(!ObjectFunctions.isNullOrEmpty(parentUserObject)){
									if(customer.isCheckEmailService()){
										if(!adminNos.equalsIgnoreCase(getAnyTitle()) && !ObjectFunctions.isNullOrEmpty(getAnyTitle())){
											if(!adminNos.equalsIgnoreCase(getAnyTitle()) && !ObjectFunctions.isNullOrEmpty(getAnyTitle())){
												if(!ObjectFunctions.isNullOrEmpty(getStudent().getAccount().getPerson().getStudentEmail()))
													studentManager.sendEmailToParentsAndStudent(getStudent().getAccount().getPerson().getStudentEmail(),getStudent().getAccount().getPerson().getFullPersonName(),customer,getStudent().getClassAndSection(),user,null,"Students",null);
												if(!ObjectFunctions.isNullOrEmpty(getStudent().getAccount().getPerson().getParentEmail()))
													studentManager.sendEmailToParentsAndStudent(getStudent().getAccount().getPerson().getParentEmail(),getStudent().getAccount().getPerson().getFullPersonName(),customer,getStudent().getClassAndSection(),user,parentUserObject.getUsername(),"Parents",null);
												log.debug("sendEmailToParentsAndStudent or sendEmailToParentsAndStudent"+addressType);
											}
										}
									}
								}
							}
						}
						if("T".equalsIgnoreCase(transportStatus)){
							//This method id used to validate student fee paid status is "F" or then we can feePaidStatus to "P"  
							boolean studentFeePaidStatusUpdate = updateStudentFeePaidStatus(getUserCustId(),getUserAcademicYearId(),studId,getTempId1(),getStudent().getCategoryId());
							if(studentFeePaidStatusUpdate)
								adminManager.studentFeePaidStatusForTransport(studId,getUserCustId(),getUserAcademicYearId(),getStudent().getCategoryId());
						}
						else{
							adminManager.studentFeePaidStatusWithTransport(studId,getUserCustId(),getUserAcademicYearId(),getStudent().getCategoryId(),getStudent().getClassNameId());
						}
					}
					
					customer=null;
					getSession().removeAttribute("GetAllClassNames");
				}
			} catch (Exception ex) {
				log.error("Entering into 'catch block':" + ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			finally
			{
				log.debug("fainally method entering :");
				//ajaxViewStudentDetails();
				//ajaxViewMyPersonalInfo();
				
				setAnyId(String.valueOf(studId));
				setTempId(studId);
				//if(StringFunctions.isNullOrEmpty(getAnyTitle()))
					ajaxViewStudentDetails();
				ajaxViewMyPersonalInfo();
			}
			
			return SUCCESS;
	}
	
	public User ajaxStudentDisableOrTransfer(User user, Student student) throws URTUniversalException 
	{
	
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxStudentDisableOrTransfer' method");
			}
			try {
				String StudentStatus = getParamValue("status");
				if ("on".equalsIgnoreCase(StudentStatus)) {
					//student.setActiveStudent(false);
					student.setStatus(Constants.NO_STRING);

					user.setAccountExpired(true);
					user.setLastUpdatedDate(new Date());
					user.setLastUpdatedById(getUser().getId());
					user.setLastAccessDate(new Date());
					user.setEnabled(false);
					studentManager.updateClassAndSectionCapacity(getUserCustId(),getUserAcademicYearId(),0, student.getClassSection().getId());
				} else {
					if("S".equalsIgnoreCase(student.getStatus()) || "B".equalsIgnoreCase(student.getStatus())){
						student.setStatus(student.getStatus());
					}else
						student.setStatus(Constants.YES_STRING);
					//student.setActiveStudent(true);
					user.setAccountExpired(false);
				}
				
				if(!ObjectFunctions.isNullOrEmpty(getAnyTitle()) && !ObjectFunctions.isNullOrEmpty(getStudent().getAccount().getAdmissionNumber())){
					if(getAnyTitle().compareTo(getStudent().getAccount().getAdmissionNumber()) != 0){
						user.setAdmissionNumber(getAnyTitle());
						if(!String.valueOf(user.getId()).equalsIgnoreCase(user.getUsername())){
							user.setUsername(String.valueOf(user.getId()));
							user.setSecondaryUsername(String.valueOf(user.getId()));
							user.setPassword(PasswordUtils.passwordEncoder(String.valueOf(user.getId()), null));
						}
						
					}
				}
				user=(User)studentManager.saveOrUpdateObject(user);
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return user;
	}
	
	@Actions( { @Action(value = "ajaxViewMyPersonalInfo", results = { @Result(location = "../common/ajaxViewStudentPersonalInformation.jsp", name = "success") }) })
	public String ajaxViewMyPersonalInfo() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMyPersonalInfo' method");
		}
		try {
			if (getTempId() != 0) {
				//Student student = (Student) studentManager.get(Student.class,getTempId());
				  Student student = (Student) studentManager.get(Student.class, "id="+getTempId());
				if (!ObjectFunctions.isNullOrEmpty(student)) 
				{
					StudentVo studentVo = student.deepCopyEntityToVO(student);
					setStudentVo(studentVo);
					
					Date dateOfBirth = studentVo.getAccount().getPersonVo().getDateOfBirth();
					if(!ObjectFunctions.isNullOrEmpty(dateOfBirth)){
						String todayDate = DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,dateOfBirth);
						setSelectedId(todayDate);
					}	
					setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
					setCastSettingList(studentManager.getAllByCustId("CastSettings", getUserCustId(),0));
					setTempList2((List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST));
					setTempList1(studentManager.getAll(CommonType.class, "custId="+getUserCustId()+" and type='RELIGION'"));
					setObjectList(adminManager.getSubcastSettingsByCastIdAndCustId(studentVo.getAccount().getPersonVo().getCastId(),getUserCustId()));	
					setHouseTypeList(studentManager.getAll(HouseType.class));
					
					if(!ObjectFunctions.isNullOrEmpty(studentVo.getAccount().getPersonVo()))
					{
						if(studentVo.getAccount().getPersonVo().isPhId())
						{
							if("Y".equalsIgnoreCase(studentVo.getAccount().getPersonVo().getIsPHDocsUploaded()))
							{
								setTempList(null);
								StringBuffer pathName = new StringBuffer(generateStudentDisabilityUploadFilepath(getCustomerByCustId(),studentVo.getAccount().getPersonVo().getFirstName().replaceAll(" ", "_"),0,studentVo.getAccount().getId()));
								if(StringFunctions.isNotNullOrEmpty(pathName.toString()))
								{
							    	File destFile = new File(getSession().getServletContext().getRealPath(pathName.toString()));
							    	String[] children = destFile.list();
									if(!ObjectFunctions.isNullOrEmpty(children))
									{
										log.debug(children.length);
										Attachment attachment = new Attachment();
								    	for (int i=0; i<children.length; i++) {
							            	attachment = new Attachment();
							                // Get filename of file or directory
							                String filename = children[i];
							                attachment.setFileName(filename);
							                getTempList().add(attachment);
							            }
								    	attachment = null;
									}
							    }
							}
						}
					}
				}
				student = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
			return SUCCESS;
	}
	/*
	* Removed servie and used getCustomerByCustId() is done by venkatesh - 04-19-2013
	*/
	@Actions({
		@Action(value = "ajaxDoImportStudentExcelSheet", results = { @Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "success"),
				 @Result(location = "../admin/student/ajaxViewStudentsList.jsp", name = "error")})
		 })
	public String ajaxDoImportStudentExcelSheet() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoImportStudentExcelSheet' method");
		}
		try {
			Customer customer = getCustomerByCustId();
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				
				setTempString("userfiles/"+customer.getCustomerShortName().trim().toLowerCase());
				if(StringFunctions.isNotNullOrEmpty(getTempString())){
			    	File destFile = new File(getSession().getServletContext().getRealPath(getTempString()));
			    	if(destFile.mkdirs())
						log.debug(getSession().getServletContext().getRealPath(getTempString())+" directory created.");
					else
						log.debug(getSession().getServletContext().getRealPath(getTempString())+" directories Not Created");
				}
				log.debug(getTempString());
			}
			customer=null;
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	/*
	* Removed PrepareAcademicYearId and used getUserAcademicYearId() is done by venkatesh - 04-20-2013
	*/
	@Actions( { @Action(value = "ajaxViewExpiredStudents", results = { @Result(location = "../admin/student/ajaxViewExpiredStudentsList.jsp", name = "success") }) })
	public String ajaxViewExpiredStudents() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ViewExpiredStudents' method");
		}
		try {
			if(getUserAcademicYearId() > 0 ){
				List studList = studentManager.getAll(ViewStudentPersonAccountDetails.class,"accountExpired='"+Constants.YES_STRING+"' and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and description is not null");
				setCustomer(getCustomerByCustId());
				if(ObjectFunctions.isNotNullOrEmpty(studList)){
					for(Object studobj : studList)
					{
						ViewStudentPersonAccountDetails viewStudentPersonAccountDetails = (ViewStudentPersonAccountDetails)studobj;
						StringBuffer query = new StringBuffer(" studentId="+viewStudentPersonAccountDetails.getStudentId()+" and custId="+viewStudentPersonAccountDetails.getCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+ viewStudentPersonAccountDetails.getAcademicYearId());
						List<StudentPayment> studentPaymentList = adminManager.getAll(StudentPayment.class, query.toString());
						if(!ObjectFunctions.isNullOrEmpty(studentPaymentList)) viewStudentPersonAccountDetails.setPresent(true);
						else viewStudentPersonAccountDetails.setPresent(false);
						getObjectList().add(viewStudentPersonAccountDetails);
						query=null;
						studentPaymentList=null;
						studobj=null;
						viewStudentPersonAccountDetails=null;
					}
				}
						Collections.sort(getObjectList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/*
	* Removed PrepareAcademicYearId and used getUserAcademicYearId() as well as change the section capacity is done by venkatesh - 04-20-2013
	*/
	@Actions( { @Action(value = "ajaxEnableStudentDetails", results = { @Result(location = "../admin/student/ajaxViewExpiredStudentsList.jsp", name = "success") }) })
	public String ajaxEnableStudentDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEnableStudentDetails' method");
		}
		try {
			if(getUserAcademicYearId()>0){
				if(getTempId()>0 && StringFunctions.isNotNullOrEmpty(getClassSectionId())){  //here tempId is accountid
					Object[] studyClassIds= studentManager.get("select sectionCapacity,filledSeats from studyClass where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getClassSectionId());
					if(Integer.valueOf(studyClassIds[0].toString()) > Integer.valueOf(studyClassIds[1].toString())){
						User user =(User)studentManager.get(User.class,getTempId());
						enableStudentAndParentUserObject(user);
						Student student = (Student)studentManager.get(Student.class,"accountId="+getTempId()+" and status='"+Constants.NO_STRING+"' and academicYearId="+getUserAcademicYearId());
						student.setDescription(null);
						student.setStatus(Constants.YES_STRING);
						
						student.setLastUpdatedById(getUser().getId());
						student.setCreatedDate(new Date());
						student.setLastUpdatedDate(new Date());
						studentManager.save(student);
						studentManager.updateClassAndSectionCapacity(getUserCustId(),getUserAcademicYearId(),0,student.getClassSectionId());
						studentManager.sendNotificationForStudentUpdate(student);
						super.addActionMessage("Student activated successfully."); 
					}else{
						super.addActionError("Class capacity over please increase class capacity.");
					}
				}
				ajaxViewExpiredStudents();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/********************************************************************
	 * Date              	Name               Description
	 * ========          	============       ==================
	 * April 20th, 2013     Venki		       Removed PrepareAcademicYearId and used getUserAcademicYearId() is done
	 * Sep 19th, 2013		Seshu			   Mother toung changes 
	/********************************************************************/	
	@Actions( { @Action(value = "ajaxDownloadStudent1", results = {}) })
	public void ajaxDownloadStudent1() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadStudent' method");
		}
		try {
			if (getUserAcademicYearId() != 0 && StringFunctions.isNotNullOrEmpty(getSelectedId())) {
				String[] words = { "Own", "Private", "School Tranport" };
				Arrays.asList(words);
				String fileName = "Student_Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				PrepareStudentExcel prepareStudentExcel = new PrepareStudentExcel();
				List<Object[]> studentDetails = null;
				List<ViewClassSectionDetails> classSections = null;
				List<StudyClass> studyClasses = null;
				if("AdmissionProcess".equals(getAnyTitle()))
					classSections = studentManager.getAll(ViewClassSectionDetails.class," academicYearId = " + getAcademicYearId() + " and custId = " + getUserCustId()+ " order by sortingOrder asc");
				else
					classSections = studentManager.getAll(ViewClassSectionDetails.class," academicYearId = " + getUserAcademicYearId()+ " and custId = " + getUserCustId()+ " order by sortingOrder asc");
				List<State> states = (List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST);
				List<MotherTongue> motherTongues = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
				List<SchoolCategory> schoolCategory = studentManager.getAll(SchoolCategory.class," custId = " + getUserCustId());
				List<HouseType> houseTypeList =  studentManager.getAll(HouseType.class);
				if("AdmissionProcess".equals(getAnyTitle())){
					prepareStudentExcel.createClassSectionSheet("ClassSections",classSections, states,motherTongues,getAcademicYearId() ,getUserCustId(),schoolCategory,null,null,houseTypeList);
					studyClasses = studentManager.getAll(StudyClass.class, "classNameClassId in " + getSelectedId()+" and custId="+getUserCustId()+" and academicYearId="+getAcademicYearId());
				}else{
					prepareStudentExcel.createClassSectionSheet("ClassSections",classSections, states,motherTongues,getUserAcademicYearId(),getUserCustId(),schoolCategory,null,null,houseTypeList);
					studyClasses = studentManager.getAll(StudyClass.class, "id in " + getSelectedId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
				}
				StringBuffer sheetTitleDesc = new StringBuffer();
				sheetTitleDesc.append("School Name : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("organization"));
				}
				sheetTitleDesc.append(", Academic Year : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("academicYearName"));

				}
				if (ObjectFunctions.isNotNullOrEmpty(studyClasses)) {
					for (StudyClass studyClass : studyClasses) {
						if("AdmissionProcess".equals(getAnyTitle())){
							studentDetails = studentManager.getAll(PrepareStudentExcel.query+ " where classSectionId="+ studyClass.getId()+ "  and custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and joinedThroughAdmissions='Y' and description is null order by IF((registerNumber IS NULL or registerNumber = ''),fullName,registerNumber)");
						}else
							studentDetails = studentManager.getAll(PrepareStudentExcel.query+ " where classSectionId="+ studyClass.getId()+ "  and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and description is null ");
						if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
							prepareStudentExcel.createStudentSheet(studyClass.getClassAndSection(), studentDetails,sheetTitleDesc.toString(),getCustomerByCustId().getCommittedFeeStatus());
						}
					}
				} else {
					prepareStudentExcel.createStudentSheet("Student",studentDetails, sheetTitleDesc.toString(),getCustomerByCustId().getCommittedFeeStatus());
				}
				prepareStudentExcel.finalPrep("ClassSections", studentDetails);
				prepareStudentExcel.getWb().write(getResponse().getOutputStream());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
		/*
		* Removed PrepareAcademicYearId and used getUserAcademicYearId() is done by venkatesh - 04-22-2013
		*/
		@Actions( {@Action(value = "ajaxGetClassAndSetions", results = {@Result(location = "academic/tcGeneration/ajaxViewPendingFee.jsp", name = "success") })})
		public String ajaxGetClassAndSetions() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetClassAndSetions' method");
			}
			try {
				if(StringFunctions.isNotNullOrEmpty(getClassId())){
					List<BigInteger> studyClassIds= studentManager.getAll("select id from studyClass where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classNameClassId in "+getClassId());
					if(ObjectFunctions.isNotNullOrEmpty(studyClassIds)){
						 setSelectedId("("+StringUtils.collectionToCommaDelimitedString(studyClassIds)+")");
						 ajaxDownloadStudent();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	/********************************************************************
	 * Date              Name               Description
	 * ========          ============       ==================
	 * April 04, 2013    Narahari		    For import student excel sheet using poi method
	 * July  07, 2013	 Venkatesh			For import edit/upload student excel sheet using poi method
	 * Oct	 08, 2013	 Seshu				Validation for sheet data. If the data is not belongs to current academic year then we don't process sheet.
	 * Dec   05&06,2013  Seshu				Added role number edit option and if user not provided roll number system will generate roll number based on first name. 
	/********************************************************************/   
		@Actions( { @Action(value = "ajaxUploadStudentData1", results = {@Result(location = "../admin/reports/ajaxDownloadStudentDetails.jsp", name = "success"),
				@Result(location = "../admin/reports/ajaxDownloadStudentDetails.jsp", name = "dummyInit"),
				@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "createSuccess") }),
		@Action(value = "ajaxUploadNewStudentData1", results = {@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "success"),
						@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "dummyInit"),
						@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "createSuccess") })
		})
	public String ajaxUploadStudentData1() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUploadStudentData1' method");
		}
		try {
			log.debug(getEventId());
			log.debug(getUploadContentType());
			boolean excelFileType = false;
			
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
			
			String path1 = "INVALID_STUDENT_DATA.xls";
			 
			File  f1 = new File(getSession().getServletContext().getRealPath("userFiles/studentDataUpload/"+getUserCustId()+"/"+path1));
			
			FileUtils.copyFile(getUpload(), f1);
			
			FileInputStream inputStream = new FileInputStream(new File(getSession().getServletContext().getRealPath("userFiles/studentDataUpload/"+getUserCustId()+"/"+path1)));
			
			FileOutputStream fileOut = new FileOutputStream(f1);
			
			HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream(getUpload()));
	         
	        CellStyle hlink_style = workbook1.createCellStyle();
	         
		     Font hlink_font = workbook1.createFont();
		     hlink_style.setAlignment(hlink_style.ALIGN_RIGHT);
		     hlink_font.setColor(IndexedColors.RED.getIndex());
		     hlink_style.setFont(hlink_font);
		     XSSFColor color = new XSSFColor(new java.awt.Color(128, 0, 128));
		     hlink_style.setBottomBorderColor(hlink_style.BORDER_DOUBLE);
		     	 
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("ClassSections");
			long academicYearId = 0;
			long custId = 0;
			if (ObjectFunctions.isNullOrEmpty(sheet)) {
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				ajaxGetStudyClassList();
				return "dummyInit";
				
			} else {
				
				org.apache.poi.ss.usermodel.Sheet firstSheet1 = workbook.getSheetAt(0);
				Row secondRow = firstSheet1.getRow(1);
				if(!ObjectFunctions.isNullOrEmpty(secondRow))
				{
					if(!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1)))
					{
						String fistColumn = secondRow.getCell(0).getStringCellValue();
						String secondColumn = secondRow.getCell(1).getStringCellValue();
						
						if(!"Student Id".equalsIgnoreCase(fistColumn.toString()) || !"Admission Number".equalsIgnoreCase(secondColumn.toString()))
						{
							log.debug("No file to upload....");
							super.addActionError("File type not matched.");
							ajaxGetStudyClassList();
							return "dummyInit";
						}
						fistColumn = null;
						secondColumn = null;
					}
					else
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						ajaxGetStudyClassList();
						return "dummyInit";
					}
				}
				else
				{
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
					ajaxGetStudyClassList();
					return "dummyInit";
				}
				
				
				Row row = sheet.getRow(0);
				academicYearId = (long) row.getCell(1).getNumericCellValue();
				row = sheet.getRow(1);
				custId = (long) row.getCell(1).getNumericCellValue();
			}
			if(!"AdmissionCreate".equalsIgnoreCase(getEventId())){
				if(!isCurrentAcademicYearId(academicYearId)) {
					log.debug("Uploaded wrong file..");
					super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
					ajaxGetStudyClassList();
					return "dummyInit";
				}
			}	
			if(custId !=0){
				String missingMandatoryValues = null;
				Student stud = null;
				User account = null;
				Person person = null;
				Address address = null;
				String admissionStr =null;
				List<Student> studentDetailsList = null;
				List<StudentVo> studentVoDetailsList = null;
				boolean isChangedAdmissionNo = true;
				StringBuffer admissionNumberExistMsg = new StringBuffer();
				StringBuffer feepaidAdmissinNumber = new StringBuffer();
				StringBuffer errorMessageBuffer = new StringBuffer();
				StringBuffer enrollmentCodeMessageBuffer = new StringBuffer();
				StringBuffer cellNumber = new StringBuffer();
				StringBuffer commitFeeStr = new StringBuffer();
				Long settingsId = null;
				CommonType commonType = null;
				CastSettings castSettingObj = null;
				SubCastSettings subCastSettingObj = null;
				ClassName className = null;
				StudyClass studyClass = null;
				double deductingAmnt = 0;
				double previousCommittedFee = 0;
				Set<String> classNames = new HashSet<String>();
				Set<StudyClass> studyClassIdsSet = new HashSet<StudyClass>();
				Set<Long> rollNumberNotAssignedClasses = new HashSet<Long>();
				Customer customer = (Customer) studentManager.get(Customer.class,"id=" + custId);
				SMSServiceProviders smsServiceProvider=(SMSServiceProviders)  adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				AcademicYear academicYear = (AcademicYear) studentManager.get(AcademicYear.class, academicYearId);
				Role studentRole = adminManager.getRoleByName(Constants.SCHOOL_STUDENT);
				//SchoolCategory category = adminManager.getCategoryIdByCustId("General", custId);
				HashMap<String, SchoolCategory> schoolCategoryMap = new HashMap<String, SchoolCategory>();
				List<SchoolCategory> categoryList = studentManager.getAll(SchoolCategory.class,"custId="+getUserCustId());
				if (ObjectFunctions.isNotNullOrEmpty(categoryList)) {
					for (SchoolCategory categoryObj : categoryList) {
						schoolCategoryMap.put(categoryObj.getCategoryName(),categoryObj);
					}
				}
				HashMap<String, StudyClass> studyClassMap = new HashMap<String, StudyClass>();
				List<StudyClass> studyClasses = studentManager.getAll(StudyClass.class, "custId=" + custId +" and academicYearId=" + academicYearId);
				StringBuffer classObj = null;
				if (ObjectFunctions.isNotNullOrEmpty(studyClasses)) {
					for (StudyClass studyClas : studyClasses) {
						classObj = new StringBuffer();
						if(!ObjectFunctions.isNullOrEmpty(studyClas.getClassName())){
							classObj.append(studyClas.getClassName());
						}
						if(!ObjectFunctions.isNullOrEmpty(studyClas.getSection())){
								classObj.append("-"+studyClas.getSection());
						}
						studyClassMap.put(classObj.toString(),studyClas);
					}
					classObj = null;
				}
				
				HashMap<String, CastSettings> castSettingsMap = new HashMap<String, CastSettings>();
				List<CastSettings> castSettings = studentManager.getAll(CastSettings.class, "custId=" + custId);
				if (ObjectFunctions.isNotNullOrEmpty(castSettings)) {
					for (CastSettings castSetting : castSettings) {
						castSettingsMap.put(castSetting.getCastName().toLowerCase(),castSetting);
					}
				}
				HashMap<String, SubCastSettings> subCastSettingMap = new HashMap<String, SubCastSettings>();
				List<SubCastSettings> subcastSettings = studentManager.getAll(SubCastSettings.class, "custId=" + custId);
				if (ObjectFunctions.isNotNullOrEmpty(subcastSettings)) {
					for (SubCastSettings subCast : subcastSettings) {
						subCastSettingMap.put(subCast.getSubCastName().toLowerCase(), subCast);
					}
				}
				HashMap<String, Long> religionsMap = new HashMap<String, Long>();
				HashMap<String, Long> motherToungsMap = new HashMap<String, Long>();
				List<CommonType> commonTypeList = studentManager.getAll(CommonType.class, "custId=" + custId+ " and type in ('RELIGION')");
				if (ObjectFunctions.isNotNullOrEmpty(commonTypeList)) {
					for (CommonType commontype : commonTypeList) {
							religionsMap.put(commontype.getSkillTypeName().toLowerCase(),commontype.getId());
					}
				}
				List<MotherTongue> motherTongueList = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
				if (ObjectFunctions.isNotNullOrEmpty(motherTongueList)) {
					for (MotherTongue motherTongueObj : motherTongueList) {
							motherToungsMap.put(motherTongueObj.getName(),motherTongueObj.getId());
					}
				}
				List<HouseType> houseTypeList = studentManager.getAll(HouseType.class);
				HashMap<Long, HouseType> houseTypeMap = new HashMap<Long, HouseType>();
				if (ObjectFunctions.isNotNullOrEmpty(houseTypeList)) {
					for (HouseType houseTypeObj : houseTypeList) {
						houseTypeMap.put(houseTypeObj.getId(),houseTypeObj);
					}
				}				
				statelist = studentManager.getCountryStates(0);
				CommonType commonTypes =(CommonType)studentManager.get(CommonType.class,"custId="+getUserCustId()+" and skillTypeName like '%"+"Other"+"%' and type like '%"+"RELIGION"+"%' ");
				CastSettings castsettings =(CastSettings)studentManager.get(CastSettings.class,"custId ="+getUserCustId()+" and castName like '%"+"Other"+"%' ");
				String userName = null;
				User objUser= null;
				int loopCount=1;
				
				Sheet firstSheet=null;
				
				HashMap<String, Integer> removeRows = new HashMap<String, Integer>();
				
				Map<String, User> sendEmailToParentsAndStudentMap = new HashMap<String, User>();
				Map<String, List> sendSmsForParentsAndStudentMap = new HashMap<String, List>();
				List<Student> studentObjList = new ArrayList<Student>();
				Map<String, Student> entryStudentCommittedPaymentMap = new HashMap<String, Student>();
				
				int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
				
				for (int sheetNo = 0; sheetNo < (workbook.getNumberOfSheets() - 1); sheetNo++) {
					sheet = workbook.getSheetAt(sheetNo);
					firstSheet = workbook1.getSheetAt(sheetNo);
					int sheetRowCount=1;					
					int rowCount=2;
					SheetParser parser = new SheetParser();
					try {
						studentVoDetailsList = parser.createEntity(sheet, sheet.getSheetName(), StudentVo.class);
						long countNo = 0;
						
						if (ObjectFunctions.isNotNullOrEmpty(studentVoDetailsList)) {
							for (StudentVo studentVoDetails : studentVoDetailsList) {
								log.debug(studentVoDetails.getAccount().getAdmissionNumber()); 
									 if (!ObjectFunctions.isNullOrEmpty(studentVoDetails)
												&& !ObjectFunctions.isNullOrEmpty(studentVoDetails.getAccount())
												&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getAdmissionNumber())
												&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getStudyClassVo().getClassAndSection())
												&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getFirstName())) {
											log.debug("***************** " + studentVoDetails.getAccount().getPersonVo().getFirstName() + "***************** " );
											
											sheetRowCount++;											
												 if(loopCount > 4) break;
												 if(ObjectFunctions.isNullOrEmpty(sheet.getRow(rowCount++))){
													loopCount++;
													continue;
												  }
												loopCount=1;
												String mobileNumber = null;
												String secondaryMobileNumber = null;
												String studMobileNumber = null;
												isChangedAdmissionNo = true;
												missingMandatoryValues = "missed";
												
												int sentSmsCount=adminManager.getTotalSmsCount(customer.getId(),academicYear.getId());
												
												studyClass = studyClassMap.get(studentVoDetails.getStudyClassVo().getClassAndSection());
												if (ObjectFunctions.isNullOrEmpty(studyClass)) {
													classNames.add(studentVoDetails.getStudyClassVo().getClassAndSection()+ ", ");
													// log.debug("*** Classes not availale **");
													continue;
												} else {
													className = studyClass.getClassNameClassId();
													if (ObjectFunctions.isNullOrEmpty(className)) {
														classNames.add(studentVoDetails.getStudyClassVo().getClassAndSection()+ ", ");
														continue;
													}
												}
												String admisionNo = null;
												String oldEmail = null;
												String updatedAdmissionStatus = null;
												String studStatus = null;
												if (!StringFunctions.isNullOrEmpty(studentVoDetails.getStuId())) {
													//log.debug("student id : "+ studentDetails.getStuId());
													stud = (Student) studentManager.get(Student.class, Long.valueOf(studentVoDetails.getStuId()));
													//stud=(Student) studentManager.get(Student.class, "id="+Long.valueOf(studentVoDetails.getStuId()));
													if("R".equalsIgnoreCase(studentVoDetails.getAccount().getPersonVo().getAddressType())){
														mobileNumber = stud.getAccount().getPerson().getMobileNumber();
														secondaryMobileNumber = stud.getAccount().getPerson().getSecondaryMobileNumber();
													}else{
														mobileNumber = stud.getAccount().getPerson().getAnotherMobileNumber();
														secondaryMobileNumber = stud.getAccount().getPerson().getAnotherSecondaryMobileNumber();
													}
													studMobileNumber = stud.getAccount().getPerson().getStudentMobile();
													oldEmail = stud.getAccount().getPerson().getStudentEmail();
													account = (User) studentManager.get(User.class, "custId="+ custId+ " and id="+ stud.getAccount().getId());
													//account = (User) studentManager.get(User.class, stud.getAccount().getId());
													person = (Person) studentManager.get(Person.class, account.getPerson().getId());
													if(!ObjectFunctions.isNullOrEmpty(account.getPrimaryAddress()))
														address = (Address) studentManager.get(Address.class, account.getPrimaryAddress().getId());
													else
														address = new Address();
													admisionNo=account.getAdmissionNumber().trim();
													studStatus = stud.getStatus();
													stud.setLastUpdatedById(getUser().getId());
													account.setLastUpdatedById(getUser().getId());
													stud.setLastUpdatedDate(new Date());
												} else {
													log.debug("new student generation");
													stud = new Student();
													account = new User();
													person = new Person();
													address = new Address();
													
													stud.setStatus(Constants.YES_STRING);
													//stud.setActiveStudent(true);
													
													stud.setCreatedById(getUser().getId());
													stud.setCreatedDate(new Date());
													stud.setCustId(getUserCustId());
													
													account.setCustId(getUserCustId());
													account.addNewRole(studentRole);
													account.setCreatedById(getUser().getId());
													account.setCreatedDate(new Date());
													account.setAccountExpired(false);
													account.setEnabled(true);
												}
												// update the Existing Student
												if (!(studentVoDetails.getAccount().getAdmissionNumber().trim().equalsIgnoreCase(admisionNo))) {
													updatedAdmissionStatus ="Yes";
													userName = StringFunctions.prepareUserName(customer.getCustomerShortName(), studentVoDetails.getAccount().getAdmissionNumber(), "");
													//objUser= studentManager.getUserByUserName(userName);
													Row row=firstSheet.getRow(sheetRowCount);
													if (!ObjectFunctions.isNullOrEmpty(objUser)) {
														isChangedAdmissionNo = false;
														//admissionNumberExistMsg.append(studentVoDetails.getAccount().getAdmissionNumber()+ ", ");
														
														//workbook.getRootDirectory()
														
														//log.debug("**** Admission Number available *******"+ studentDetails.getAccount().getAdmissionNumber());
														if(StringFunctions.isNullOrEmpty(studentVoDetails.getStuId())){
															//firstSheet.removeRow(firstSheet.getRow(rowCount));
															Cell cell = (Cell) row.getCell(1);														
															cell.setCellStyle(hlink_style);	
															continue;
														}
													}
													else {														
														//row.setZeroHeight(true);
														//firstSheet.removeRow(row);														
														removeRows.put(sheetNo+"_"+sheetRowCount, sheetRowCount);
														//firstSheet.shiftRows(sheetRowCount, sheetRowCount, -1);
														//sheet.shiftRows(i+1, sheet.getLastRowNum(), -1);
														studentVoDetails.getAccount().setUsername(userName);
														studentVoDetails.getAccount().setSecondaryUsername(userName);
														studentVoDetails.getAccount().setPassword(PasswordUtils.passwordEncoder(userName,null));
														
													}
													objUser = null;
													userName=null;
												} else {
													isChangedAdmissionNo = false;
												}
 
												
												if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getReligion())) {
													settingsId = religionsMap.get(studentVoDetails.getAccount().getPersonVo().getReligion().trim().toLowerCase());
													if (ObjectFunctions.isNullOrEmpty(settingsId)) {
														commonType = new CommonType();
														commonType.setCustId(custId);
														commonType.setSkillTypeName(studentVoDetails.getAccount().getPersonVo().getReligion().trim().toUpperCase());
														commonType.setType("RELIGION");
														//commonType.setVersion(0);
														commonType = (CommonType) studentManager.saveOrUpdateObject(commonType);
														settingsId = commonType.getId();
														religionsMap.put(commonType.getSkillTypeName().toLowerCase(),commonType.getId());
													}
													studentVoDetails.getAccount().getPersonVo().setReligionId(settingsId);
												}else{
													if(!ObjectFunctions.isNullOrEmpty(commonTypes)){
														studentVoDetails.getAccount().getPersonVo().setReligionId(commonTypes.getId());
													}
												}
												if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getMotherToung())) {
													settingsId = motherToungsMap.get(studentVoDetails.getAccount().getPersonVo().getMotherToung().trim());
													if(!ObjectFunctions.isNullOrEmpty(settingsId))
														studentVoDetails.getAccount().getPersonVo().setMotherToungId(settingsId);
												} else
													studentVoDetails.getAccount().getPersonVo().setMotherToungId(0);

												if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getCommunity())|| StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getCastName())) {
													if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getCommunity())) {
														castSettingObj = castSettingsMap.get(studentVoDetails.getAccount().getPersonVo().getCommunity().trim().toLowerCase());
														if (ObjectFunctions.isNullOrEmpty(castSettingObj)) {
															castSettingObj = new CastSettings();
															castSettingObj.setCustId(custId);
															castSettingObj.setCastName(studentVoDetails.getAccount().getPersonVo().getCommunity().trim().toUpperCase());
															castSettingObj.setCreatedById(getUser().getId());
															castSettingObj.setCreatedDate(new Date());
															castSettingObj = (CastSettings) studentManager.saveOrUpdateObject(castSettingObj);
															castSettingsMap.put(castSettingObj.getCastName().toLowerCase(),castSettingObj);
														}
														studentVoDetails.getAccount().getPersonVo().setCastId(castSettingObj.getId());
													}else{
														if(!ObjectFunctions.isNullOrEmpty(castsettings)){
															studentVoDetails.getAccount().getPersonVo().setCastId(castsettings.getId());
														}
													}
													if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getCastName())) {
														subCastSettingObj = subCastSettingMap.get(studentVoDetails.getAccount().getPersonVo().getCastName().toLowerCase());
														if (ObjectFunctions.isNullOrEmpty(subCastSettingObj) && !ObjectFunctions.isNullOrEmpty(castSettingObj)) {
															subCastSettingObj = new SubCastSettings();
															subCastSettingObj.setCustId(custId);
															subCastSettingObj.setSubCastName(studentVoDetails.getAccount().getPersonVo().getCastName().trim().toUpperCase());
															subCastSettingObj.setCreatedById(getUser().getId());
															subCastSettingObj.setCreatedDate(new Date());
															castSettingObj.addSubCast(subCastSettingObj);
															studentManager.saveOrUpdateObject(castSettingObj);
															// subCastSettingObj =
															// (SubCastSettings)studentManager.saveOrUpdateObject(subCastSettingObj);
															subCastSettingMap.put(subCastSettingObj.getSubCastName().toLowerCase(),subCastSettingObj);
														}
														if (!ObjectFunctions.isNullOrEmpty(subCastSettingObj))
															studentVoDetails.getAccount().getPersonVo().setSubCastId(subCastSettingObj.getId());
													} else
														studentVoDetails.getAccount().getPersonVo().setSubCastId(0);
													castSettingObj = null;
													subCastSettingObj = null;
												}
												if(!ObjectFunctions.isNullOrEmpty(studentVoDetails.getAccount().getPrimaryAddressVo().getState()) && StringFunctions.isNotNullOrEmpty(studentVoDetails.getAccount().getPrimaryAddressVo().getState())){
													studentVoDetails.getAccount().getPrimaryAddressVo().setStateId(statelist.get(studentVoDetails.getAccount().getPrimaryAddressVo().getState()).getId());
													studentVoDetails.getAccount().getPrimaryAddressVo().setState(statelist.get(studentVoDetails.getAccount().getPrimaryAddressVo().getState()).getStateCode());
												}
												if (!ObjectFunctions.isNullOrEmpty(account.getPrimaryAddress())) {
													
													Address primaryAddress = account.getPrimaryAddress();
													primaryAddress = primaryAddress.copyFromVoToEntity(primaryAddress, studentVoDetails.getAccount().getPrimaryAddressVo());
													primaryAddress.setHouseType(houseTypeMap.get(studentVoDetails.getAccount().getPrimaryAddressVo().getHouseTypeId()));
													account.setPrimaryAddress(primaryAddress);
												} else {
													Address primaryAddress = new Address();
													primaryAddress = primaryAddress.copyFromVoToEntity(primaryAddress, studentVoDetails.getAccount().getPrimaryAddressVo());
													account.setPrimaryAddress(primaryAddress);
												}
												if (!ObjectFunctions.isNullOrEmpty(account.getPerson())) 
												{
													person = account.getPerson();
													person = person.copyFromVoToEntity(person, studentVoDetails.getAccount().getPersonVo());
													account.setPerson(person); 
												} else {
													person = new Person();
													person = person.copyFromVoToEntity(person, studentVoDetails.getAccount().getPersonVo());
													
													account.setPerson(person);
												}
												
												StringBuffer enrollmentCodeQuery = new StringBuffer();
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getAccount().getEnrollmentCode()))
												{
													enrollmentCodeQuery.append(" custId=" +getUserCustId() + " and enrollmentCode ='" + studentVoDetails.getAccount().getEnrollmentCode() + "'");
													if(account.getId() > 0)
														enrollmentCodeQuery.append(" and id !=" + account.getId());
													Object[] enrollmentCodeUserObj = adminManager.get("select id,enrollmentCode from Account where custId=" +getUserCustId() + " and enrollmentCode ='" + studentVoDetails.getAccount().getEnrollmentCode() + "'");
													if(!ObjectFunctions.isNullOrEmpty(enrollmentCodeUserObj))
													{
														enrollmentCodeMessageBuffer.append(studentVoDetails.getAccount().getEnrollmentCode() +", " );
														account.setEnrollmentCode(account.getEnrollmentCode());
														studentVoDetails.getAccount().setEnrollmentCode(account.getEnrollmentCode());
													}
													enrollmentCodeUserObj = null;
												}
												
												if (!ObjectFunctions.isNullOrEmpty(account)) 
												{
													if(StringFunctions.isNullOrEmpty(account.getSecondaryUsername()))
															account.setSecondaryUsername(String.valueOf(account.getId()));
													account = account.copyFromVoToEntity(account, studentVoDetails.getAccount());
													if(!studentVoDetails.getAccount().getAdmissionNumber().equalsIgnoreCase(admisionNo)){
														account.setUsername(studentVoDetails.getAccount().getUsername());
														account.setSecondaryUsername(studentVoDetails.getAccount().getUsername());
														account.setPassword(studentVoDetails.getAccount().getPassword());
													}
												} else {
													account = account.copyFromVoToEntity(account, studentVoDetails.getAccount());
												}
												
												try{
													account = (User) studentManager.save(account);
												}
												catch(Exception e){
													e.printStackTrace();
												}
												stud.setAccount(account);
												if(!StringFunctions.isNullOrEmpty(studStatus))
													stud.setStatus(studStatus);
												else
													stud.setStatus(Constants.YES_STRING);
												//stud.setActiveStudent(true);
												
												stud.setRegisterNumber(studentVoDetails.getRegisterNumber());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getHostelMode()))
													stud.setHostelMode(studentVoDetails.getHostelMode());
												else
													stud.setHostelMode("D");
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getIsJoinedThroughAdmissionStr())){
													if("Y".equalsIgnoreCase(studentVoDetails.getIsJoinedThroughAdmissionStr()))
														stud.setJoinedThroughAdmissions(true);
													else
														stud.setJoinedThroughAdmissions(false);	
												}else{
													stud.setJoinedThroughAdmissions(false);	
												}
												stud.setTransportMode(studentVoDetails.getTransportMode());
												stud.setAcademicYear(academicYear);
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getGoals()))
													if(studentVoDetails.getGoals().length() > 200)
														stud.setGoals(studentVoDetails.getGoals().substring(0,200));
													else
														stud.setGoals(studentVoDetails.getGoals());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getStrengths()))
													if(studentVoDetails.getStrengths().length() > 200)
														stud.setStrengths(studentVoDetails.getStrengths().substring(0,200));
													else
														stud.setStrengths(studentVoDetails.getStrengths());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getInterestsAndHobbies()))
													if(studentVoDetails.getInterestsAndHobbies().length() > 200)
														stud.setInterestsAndHobbies(studentVoDetails.getInterestsAndHobbies().substring(0,200));
													else
														stud.setInterestsAndHobbies(studentVoDetails.getInterestsAndHobbies());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getResponsibilities()))
													if(studentVoDetails.getResponsibilities().length() > 200)
														stud.setResponsibilities(studentVoDetails.getResponsibilities().substring(0,200));
													else
														stud.setResponsibilities(studentVoDetails.getResponsibilities());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getAchievements()))
													if(studentVoDetails.getAchievements().length() > 200)
														stud.setAchievements(studentVoDetails.getAchievements().substring(0,200));
													else
														stud.setAchievements(studentVoDetails.getAchievements());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getRemarks()))
													if(studentVoDetails.getRemarks().length() > 200)
														stud.setRemarks(studentVoDetails.getRemarks().substring(0,200));
													else
														stud.setRemarks(studentVoDetails.getRemarks());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getPromoteToClass())){
													if(studentVoDetails.getPromoteToClass().length() > 200)
														stud.setPromoteToClass(studentVoDetails.getPromoteToClass().substring(0,200));
													else
														stud.setPromoteToClass(studentVoDetails.getPromoteToClass());
												}else
													stud.setPromoteToClass(studentVoDetails.getPromoteToClass());
												
												if (StringFunctions.isNullOrEmpty(studentVoDetails.getOutSideSchoolDuty())) {
													stud.setOutSideSchoolDuty(Constants.NO_STRING);
												}
												else
													stud.setOutSideSchoolDuty(studentVoDetails.getOutSideSchoolDuty());
												if(StringFunctions.isNotNullOrEmpty(studentVoDetails.getRollNumberStr()) && studentVoDetails.getRollNumberStr().matches("[0-9]+") && !"0".equals(studentVoDetails.getRollNumberStr()))
													stud.setRollNumber(studentVoDetails.getRollNumberStr());
												else{
													stud.setRollNumber(null);
													rollNumberNotAssignedClasses.add(studyClass.getId());
												}
												if (StringFunctions.isNullOrEmpty(studentVoDetails.getStuId())) {
													stud.setClassSection(studyClass);
													stud.setClassNameClassId(className);
												} else if(studyClass.getClassNameClassId() == stud.getClassNameClassId()){
													if (studyClass.getId() != stud.getClassSection().getId()) {
														stud.setShippedSection("Section Changed from "+ stud.getClassAndSection() +" to " + studentVoDetails.getStudyClassVo().getClassAndSection());
														stud.setClassSection(studyClass);
														stud.setClassNameClassId(className);
														//studentManager.dupdateClassAndSectionCapacity(getUserCustId(),getUserAcademicYearId(),0,studentDetails.getClassSection().getId());
													}
												} else if(studyClass.getId() != stud.getClassSection().getId()){
														if("N".equalsIgnoreCase(stud.getFeePaidStatus())){
															stud.setShippedSection("Section Changed from "+ stud.getClassAndSection() +" to " + studentVoDetails.getStudyClassVo().getClassAndSection());
															stud.setClassSection(studyClass);
															stud.setClassNameClassId(className);
														}else{
															feepaidAdmissinNumber.append(studentVoDetails.getAccount().getAdmissionNumber()+ ", ");
														}
												}
												Fee fee = null;
												double committedConfiguredFee = 0;
												if("AdmissionCreate".equalsIgnoreCase(getEventId())){
													updateStudentOnlineApplicaton(stud,customer);
													stud.setJoinedThroughAdmissions(true);
													admissionStr = getEventId();
												}
												if ("N".equalsIgnoreCase(stud.getFeePaidStatus()) || "C".equalsIgnoreCase(stud.getFeePaidStatus())) {
													prepareStudentSchoolFeeSettingListFromVo(studentVoDetails);
													List<String> schoolTermIds = studentManager.getAll("select id from schoolTerms where custId="+custId+" and academicYearId="+academicYearId+" and feeSettingId in "+getTempString());
													if(ObjectFunctions.isNullOrEmpty(schoolTermIds))
														schoolTermIds.add("0");
													if (!ObjectFunctions.isNullOrEmpty(studentVoDetails.getCategoryName())) {
														fee = (Fee) studentManager.get(Fee.class,"classId="+ className.getId()+ " and categoryId="+ schoolCategoryMap.get(studentVoDetails.getCategoryName()).getId()+ " and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
														stud.setCategoryId(schoolCategoryMap.get(studentVoDetails.getCategoryName()).getId());
													} else {
														fee = (Fee) studentManager.get(Fee.class,"classId="+ className.getId()+ " and categoryId="+ schoolCategoryMap.get("General").getId()+ " and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
														stud.setCategoryId(schoolCategoryMap.get("General").getId());
													}
													/*
													 * Here we will remove committed fee amount when the fee paid status in "C" and user try to change the category name we will remove esisting committed fee records. */
													if (stud.getCategoryId() != studentVoDetails.getCategoryId()) {
														if ("C".equalsIgnoreCase(studentVoDetails.getFeePaidStatus())) {
															studentManager.removeCommittedFeeEntries(studentVoDetails);
														}
													}
													if (!ObjectFunctions.isNullOrEmpty(fee))
														stud.setFeeConfigured(Constants.YES_STRING);
													else
														stud.setFeeConfigured(Constants.NO_STRING);
													if(Constants.YES_STRING.equalsIgnoreCase(customer.getCommittedFeeStatus())){
														if(!StringFunctions.isNullOrEmpty(studentVoDetails.getCommittedFeeStr())){
															if (Double.valueOf(studentVoDetails.getCommittedFeeStr()) != 0d) {
																committedConfiguredFee = studentManager.getCommittedConfiguredFeeAmountCategoryWise(getUserCustId(),getUserAcademicYearId(),studyClass.getId(),stud.getCategoryId(),"");
																if (committedConfiguredFee != 0) {
																	previousCommittedFee = stud.getCommittedFee();
																	Map<String, String> msgs = studentManager.validateCommittedFeeConstrains(getUserCustId(),getUserAcademicYearId(),Double.valueOf(studentVoDetails.getCommittedFeeStr()),className.getId(),studyClass.getId(),stud.getCategoryId(),getTempString());
																	/* This temparary fix need to work on this more. Because above mothod i am using temp string so when we create admission value may change. That's why i set temp string again once above method finished. */
																	if("AdmissionCreate".equalsIgnoreCase(admissionStr))
																		setTempString(admissionStr);
																	if(!ObjectFunctions.isNullOrEmpty(msgs)){
																		commitFeeStr.append(studentVoDetails.getAccount().getAdmissionNumber()+ ",");
																	} else {
																		/* Here we will remove committed fee amount when the fee paid status in "C" and user try to change the commiteed fee amount we will remove esisting committed fee records. */
																		if ("C".equalsIgnoreCase(studentVoDetails.getFeePaidStatus())) {
																			if (stud.getCommittedFee() != Double.valueOf(studentVoDetails.getCommittedFeeStr())) {
																				studentManager.removeCommittedFeeEntries(studentVoDetails);
																			}
																		}
																		stud.setCommittedFee(Double.valueOf(studentVoDetails.getCommittedFeeStr()));
																	}
																}else
																	commitFeeStr.append(studentVoDetails.getAccount().getAdmissionNumber()+ ",");
																committedConfiguredFee = 0;
															}
														}
													}
												}
												
												studyClassIdsSet.add(stud.getClassSection());
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getSchoolMess()))
													stud.setSchoolMess(studentVoDetails.getSchoolMess());
												else
													stud.setSchoolMess("N");
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getPtaStatus()))
													stud.setPtaStatus(studentVoDetails.getPtaStatus());
												else
													stud.setPtaStatus("N");
												
												if("Yes".equalsIgnoreCase(studentVoDetails.getBplStatus())){
													stud.setBplStatus("Y");
										    	}else {
										    		stud.setBplStatus("N");
												}
												
												if("Yes".equalsIgnoreCase(studentVoDetails.getRteStatus())){
													stud.setRteStatus("Y");
										    	}else {
										    		stud.setRteStatus("N");
												}
												
												Student studObject  = (Student) studentManager.save(stud);
												//Sending notification for student profile update
												studentManager.sendNotificationForStudentUpdate(studObject);
												//Sending notification for student profile update
												studentObjList.add(studObject);
												if(customer.isCheckMobileService() || customer.isCheckEmailService()){
													if(!ObjectFunctions.isNullOrEmpty(stud.getAccount().getStudentParent())){
														User parentUserObject = (User) adminManager.get(User.class,Long.valueOf(stud.getAccount().getStudentParent().getId()));
														//Added by Siva on making parent mobile number as username on 13 JUN 2016
														// Comment this code for remove the entire system in +91- before added the mobile number. RaviTeja 25-07-2016 
														String newPassword = StringUtil.generateRandomString();
														if(StringFunctions.isNotNullOrEmpty(mobileNumber)){
															User parentAccountNew =adminManager.getUserEmailByUserName(mobileNumber);
															if(ObjectFunctions.isNullOrEmpty(parentAccountNew))
															{
																if(!mobileNumber.equalsIgnoreCase(parentUserObject.getUsername())){
																	parentUserObject.setUsername(mobileNumber);
																	parentUserObject.setSecondaryUsername(String.valueOf(parentUserObject.getId()));
																	parentUserObject.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
																	parentUserObject = (User)studentManager.saveOrUpdateObject(parentUserObject);
																	String updatedAdmissionNo = "Yes";
																	//studentManager.sendSmsForParentsAndStudent(mobileNumber,secondaryMobileNumber,studMobileNumber,parentUserObject.getPerson(),parentUserObject,studObject,customer.getSendSmsUpdatedMobile(),customer,customer.getCustomerShortName(),updatedAdmissionNo,getUser().getId(),getUser().getUserRoleDescription(),customer.isCheckParentSmsService(),newPassword);
																	
																	String key = mobileNumber+"$"+secondaryMobileNumber+"$"+studMobileNumber+"$"+updatedAdmissionNo+"$"+newPassword;
																	
																	List sendSmsParentList = new ArrayList();
																	sendSmsParentList.add(parentUserObject.getPerson());
																	sendSmsParentList.add(parentUserObject);
																	sendSmsParentList.add(studObject);
																	sendSmsParentList.add(getUser());
																	
																	sendSmsForParentsAndStudentMap.put(key, sendSmsParentList);
																	
																}
															}
															parentAccountNew = null;
														}
														//end
														
														if(customer.isCheckMobileService()){
															 if((allottedSmsCount!=0 && allottedSmsCount > sentSmsCount) && stud.getId() > 0 && !"AdmissionCreate".equalsIgnoreCase(admissionStr) && ("Yes".equalsIgnoreCase(updatedAdmissionStatus))){
																 if(!ObjectFunctions.isNullOrEmpty(stud.getAccount().getStudentParent())){
																	if(!ObjectFunctions.isNullOrEmpty(parentUserObject)){
																		String updatedAdmissionNo = null;
																		updatedAdmissionNo = updatedAdmissionStatus;
																		//studentManager.sendSmsForParentsAndStudent(mobileNumber,secondaryMobileNumber,studMobileNumber,parentUserObject.getPerson(),parentUserObject,studObject,customer.getSendSmsUpdatedMobile(),customer,customer.getCustomerShortName(),updatedAdmissionNo,getUser().getId(),getUser().getUserRoleDescription(),customer.isCheckParentSmsService(),newPassword);
																		
																		String key = mobileNumber+"$"+secondaryMobileNumber+"$"+studMobileNumber+"$"+updatedAdmissionNo+"$"+newPassword;
																		
																		List sendSmsParentList = new ArrayList();
																		sendSmsParentList.add(parentUserObject.getPerson());
																		sendSmsParentList.add(parentUserObject);
																		sendSmsParentList.add(studObject);
																		sendSmsParentList.add(getUser());
																		
																		sendSmsForParentsAndStudentMap.put(key, sendSmsParentList);
																		
																	}
																 }
															 }	
														 }
														if(customer.isCheckEmailService() && stud.getId() > 0 && !"AdmissionCreate".equalsIgnoreCase(admissionStr)){
															 String updatedAdmissionNo = null;
															 if("Yes".equalsIgnoreCase(updatedAdmissionStatus)){
																	updatedAdmissionNo = updatedAdmissionStatus;
																	
																String key= null;
																	
																 if(!ObjectFunctions.isNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getStudentEmail()))
																 {
																	 key = studentVoDetails.getAccount().getPersonVo().getStudentEmail()+"$"+studentVoDetails.getAccount().getPersonVo().getFullPersonName()+"$"+stud.getClassAndSection()+"Students$"+"";
																	 sendEmailToParentsAndStudentMap.put(key, studObject.getAccount());
																 }
																	
																 if(!ObjectFunctions.isNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getParentEmail()))
																 {
																	 key = studentVoDetails.getAccount().getPersonVo().getParentEmail()+"$"+studentVoDetails.getAccount().getPersonVo().getFullPersonName()+"$"+stud.getClassAndSection()+"Parents$"+parentUserObject.getUsername();
																	 sendEmailToParentsAndStudentMap.put(key, studObject.getAccount());
																 }
																	
															 }
														}
													}
												}
												if(Constants.YES_STRING.equalsIgnoreCase(customer.getCommittedFeeStatus())){
													Object[] congifuredFee = adminManager.get("select id,IFNULL(SUM(feeAmount),0) as feeAmount from Fee where custId="+getUserCustId()+" and classId="+studObject.getClassNameClassId().getId()+" and academicYearId="+studObject.getAcademicYearId()+" and categoryId="+studObject.getCategoryId());
													if(!ObjectFunctions.isNullOrEmpty(congifuredFee)){
														if(!StringFunctions.isNullOrEmpty(studentVoDetails.getCommittedFeeStr())){
															if(!ObjectFunctions.isNullOrEmpty(congifuredFee[1])){
																if(Double.valueOf(congifuredFee[1].toString()) > 0 && Double.valueOf(congifuredFee[1].toString()) > Double.valueOf(studentVoDetails.getCommittedFeeStr()))
																if (Double.valueOf(studentVoDetails.getCommittedFeeStr()) != 0d) {
																	if(Double.valueOf(studentVoDetails.getCommittedFeeStr()) != previousCommittedFee){
																		  deductingAmnt =  Double.valueOf(congifuredFee[1].toString()) - Double.valueOf(studentVoDetails.getCommittedFeeStr());
																		  
																		  String key = deductingAmnt+"#"+getUser().getId()+"#"+InetAddress.getLocalHost().getHostAddress();
																		  entryStudentCommittedPaymentMap.put(key, studObject);
																    } 
																}
															}
														}
														previousCommittedFee = 0;
													}
													congifuredFee = null;
												}
												
												if (StringFunctions.isNullOrEmpty(studentVoDetails.getStuId()) && "Y".equalsIgnoreCase(customer.getBarcodeStatus())) {
													//here generate the barcode for each student used in id cards 
													//generateBarcodeForStudent(account.getId());
													adminManager.generateBarcode(account.getId());
												}
												//studentManager.updateClassAndSectionCapacity(getUserCustId(),getUserAcademicYearId(),0, stud.getClassSectionId());
										// }
									 }else {
											// Provide message to user regd admission number is mandatory
										log.debug("**** Admission Number missed *******");
										
										if(!StringFunctions.isNullOrEmpty(studentVoDetails.getAccount().getPersonVo().getLastName()))
											errorMessageBuffer.append(studentVoDetails.getAccount().getPersonVo().getFirstName() + " " +studentVoDetails.getAccount().getPersonVo().getLastName() +", " );
										else
											errorMessageBuffer.append(studentVoDetails.getAccount().getPersonVo().getFirstName() +", " );
										cellNumber.append(countNo+",");
										//missingMandatoryValues = "missed";
									}
											
								countNo++;
								stud = null;
								account = null;
								person = null;
								address = null;
								studentVoDetails = null;
								rowCount=2;								
							}
							
						}else {
							super.addActionError("Few student(s) data not valid. Please verify the data.");
						}

					} catch (Exception ex) {
						ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
						continue;
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(sendEmailToParentsAndStudentMap))
				{
					SendEmailToParentsAndStudentThread R1 = new SendEmailToParentsAndStudentThread(sendEmailToParentsAndStudentMap,customer);
				    R1.start();
				}
				
				if(!ObjectFunctions.isNullOrEmpty(sendSmsForParentsAndStudentMap))
				{
					SendSmsForParentsAndStudentThread R1 = new SendSmsForParentsAndStudentThread(sendSmsForParentsAndStudentMap,customer,smsServiceProvider);
				    R1.start();
				}
				
				if(!ObjectFunctions.isNullOrEmpty(studentObjList))
				{
					SendNotificationForStudentUpdateThread R1 = new SendNotificationForStudentUpdateThread(studentObjList);
				    R1.start();
				}
				
				if(!ObjectFunctions.isNullOrEmpty(entryStudentCommittedPaymentMap))
				{
					EntryStudentCommittedPaymentThread R1 = new EntryStudentCommittedPaymentThread(entryStudentCommittedPaymentMap,customer);
				    R1.start();
				}
				
				sendEmailToParentsAndStudentMap = null;
				sendSmsForParentsAndStudentMap = null;
				studentObjList = null;
				entryStudentCommittedPaymentMap = null;
				
				
				if(rollNumberNotAssignedClasses.size() > 0){
					Object[] studentLastRecord = null;
					for(Long classSectionId : rollNumberNotAssignedClasses){
						studentLastRecord = studentManager.get("select IFNULL(max(rollNumber),0) as rollNumber,count(*) from student where classSectionId="+ classSectionId);
						if(!ObjectFunctions.isNullOrEmpty(studentLastRecord) && !ObjectFunctions.isNullOrEmpty(studentLastRecord[0])){
							adminManager.addStudentRollNumbersForNonAssignedStudentsByClassSectionId(classSectionId,Integer.valueOf(studentLastRecord[0].toString()));
						}
					}
					studentLastRecord = null;
				}
				
				if(errorMessageBuffer.length() > 0)
	  		    {
					errorMessageBuffer.insert(0,"Few student(s) data not valid. Please verify the data:");
	  		    	 //super.addActionError(errorMsg.toString().substring(0,errorMsg.toString().length()-1) + " Few student(s) data not valid. Please verify the data.");
	  		    	super.addActionError(errorMessageBuffer.toString().substring(0,errorMessageBuffer.toString().length()-1));
	  		    }
				if(enrollmentCodeMessageBuffer.length() > 0)
	  		    {
					enrollmentCodeMessageBuffer.insert(0,"Few student(s) enrollment data already exists. Please verify the data:");
	  		    	super.addActionError(enrollmentCodeMessageBuffer.toString().substring(0,enrollmentCodeMessageBuffer.toString().length()-1));
	  		    }
				
				if(feepaidAdmissinNumber.length() !=0){
					feepaidAdmissinNumber.insert(0,"The following admission number(s) already paid fee.So you can not change class for this students.");
					super.addActionError(feepaidAdmissinNumber.toString());
				}
				if (ObjectFunctions.isNotNullOrEmpty(classNames) || admissionNumberExistMsg.length() != 0) {
					StringBuffer failureMsg = new StringBuffer(StringUtil.convertSetToString(classNames));
					if (failureMsg.length() != 0) {
						failureMsg.delete(failureMsg.length() - 2, failureMsg.length());
						failureMsg.insert(0,"The following class(es) is(are) not available.");
					}
					if (ObjectFunctions.isNotNullOrEmpty(classNames) && admissionNumberExistMsg.length() != 0) {
						admissionNumberExistMsg.delete(admissionNumberExistMsg.length() - 2, admissionNumberExistMsg.length());
						admissionNumberExistMsg.insert(0,"The following admission number(s) is(are) already available.");
						super.addActionError(admissionNumberExistMsg.toString()+ "\n" + failureMsg.toString());
					} else if (ObjectFunctions.isNotNullOrEmpty(classNames)) {
						super.addActionError(failureMsg.toString());
					} else if(cellNumber.length() > 0){
						cellNumber.append("The fallowing row number(s) are not inserted because in this rows don't have mandatery values.");
						super.addActionError(cellNumber.toString());
					}
					else {
						admissionNumberExistMsg.delete(admissionNumberExistMsg.length() - 2, admissionNumberExistMsg.length());
						admissionNumberExistMsg.insert(0,"The following admission number(s) is(are) already available.");
						super.addActionError(admissionNumberExistMsg.toString());
					} 
					failureMsg = null;
				}
				if(!ObjectFunctions.isNullOrEmpty(studyClassIdsSet))
				{
					for(StudyClass studyCLass : studyClassIdsSet)
					{
						ajaxUpdateClassSectionAndFilledSeatsCapacity(studyCLass);
						studyCLass = null;
						//studentManager.updateClassAndSectionCapacity(getUserCustId(),getUserAcademicYearId(),0, studyCLass.getId());
						//ajaxUpdateClassSectionCapacity(studyClass);
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(removeRows)){	
					if(removeRows.size() > 0){
						String[] key=null;
						Sheet removeSheet=null;
					for (Map.Entry<String, Integer> entry : removeRows.entrySet()) {
						key=entry.getKey().split("_");
						removeSheet=workbook1.getSheetAt(Integer.valueOf(key[0]));
					    removeRow(removeSheet,entry.getValue());
					  }
					}
				}
				removeRows=null;
				workbook1.write(fileOut);
		        fileOut.flush();
				fileOut.close();
		        inputStream.close();
		        
				if(commitFeeStr.length() !=0)
					super.addActionError(commitFeeStr.toString()+ "\n The following admission number(s) committed fee is not saved.");
				
				if("Create".equalsIgnoreCase(getEventId()) && !StringFunctions.isNullOrEmpty(missingMandatoryValues)){
					super.addActionMessage("Student details created successfully.");
					return "createSuccess";
				}else if ("AdmissionCreate".equalsIgnoreCase(getEventId())) {
					if("Edit".equalsIgnoreCase(getAnyTitle()))
						super.addActionMessage("Student details updated successfully.");
					else
						super.addActionMessage("Student details created successfully.");
				}else if(!"Create".equalsIgnoreCase(getEventId())) {
					super.addActionMessage("Student details updated successfully.");
				}
				// ajaxGetStudyClassList();
				getSession().removeAttribute("GetAllStudyClasses");
				
				
				
				studyClassIdsSet = null;
				studentDetailsList = null;
				commonType = null;
				castSettingObj = null;
				subCastSettingObj = null;
				customer = null;
				academicYear = null;
				studentRole = null;
				//category = null;
				studyClassMap = null;
				studyClasses = null;
				castSettingsMap = null;
				subCastSettingMap = null;
				admissionNumberExistMsg = null;
				classNames = null;
				schoolCategoryMap = null;
				categoryList = null;
			}else{
				log.debug("Uploaded wrong file..");
				super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally
		{
			checkStudyClassHavingStudentsOrNot();
		}
		log.debug("******************** end method *******************");
		return SUCCESS;
	}
	    
	public static void removeRow(Sheet sheet, int rowIndex) {
		    int lastRowNum = sheet.getLastRowNum();
		    if (rowIndex >= 0 && rowIndex < lastRowNum) {
		        sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		    }
		    if (rowIndex == lastRowNum) {
		        Row removingRow = sheet.getRow(rowIndex);
		        if (removingRow != null) {
		            sheet.removeRow(removingRow);
		        }
		    }
		}
		
	 /*
		* Removed PrepareAcademicYearId and used getUserAcademicYearId() is done by venkatesh - 04-22-2013
		*/
	 @Actions( { @Action(value = "ajaxGetBaseStudentAttendance", results = { @Result(location = "../common/ajaxStudentAttendance.jsp", name = "success") }) })
		public String getBaseStudentAttendance() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'studentAttendance' method");
			}
			try {
				ajaxGetBaseStudentsAttendance();
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		} 
	    
    @Actions({
		@Action(value = "printMyAttendance", results = {  @Result(location = "jasper/marks/studentAttendance.jasper", type="jasper", name = "success",params = {"dataSource","objectList","format", "PDF"}) })
	})
		public String printMyAttendance()
        {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'printMyAttendance' method");
        }
        try
            {
                getBaseStudentAttendance();
                getObjectList().addAll(getAbsentList());
                log.debug("getAbsentList-->"+getAbsentList().size());
            	String date=DateFormatter.formatDate(DateFormatter.YYYYMMDDHHMMSS_PATTERN, new Date());
                String path=getSession().getServletContext().getRealPath("/jasper/marks/studentAttendance.jrxml");
                InputStream input = new FileInputStream(new File(path));
                log.debug(input);
                //JasperDesign design = JRXmlLoader.load(input);                          
           //     JasperReport report = JasperCompileManager.compileReport(design);
            //    JRBeanCollectionDataSource jasperReports = new JRBeanCollectionDataSource(getAbsentList());
                getResponse().setHeader("Content-Disposition","attachment; filename=ExamSchedules"+date+".pdf");
                getResponse().setHeader("Content-Disposition","attachment; filename=ExamSchedules"+date+".pdf");
            }
            catch(Exception ex)
            {
                log.error("Entering into 'catch block':"+ex.getMessage());
                ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            }
            return SUCCESS;
        }
    @Actions( {
		@Action(value = "ajaxDoStudentTransfer", results = { @Result(location = "../admin/student/transferStudent/ajaxStudentTransferToAntoherSchool.jsp", name = "success") })
	})
	public String ajaxDoStudentTransfer() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoStudentTransfer' method");
		}
		try {
			if(getTempId()!=0){
				setStudent((Student)studentManager.get(Student.class, getTempId()));
			}
			 Customer customer=getCustomerByCustId();
			 if(!ObjectFunctions.isNullOrEmpty(customer)){
			  if(!ObjectFunctions.isNullOrEmpty(customer.getMasterCustomer()))
				  setCustomerList(studentManager.getAll(Customer.class, " masterCustId="+customer.getMasterCustomer().getId()+" and id!="+getUserCustId()));
			 }
			 
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    @Actions( {
		@Action(value = "ajaxAddTransferStudentDetails", results = { @Result(location = "../admin/student/transferStudent/ajaxViewSearchStudentList.jsp", name = "success") })
	})
	public String ajaxAddTransferStudentDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddTransferStudentDetails' method");
		}
		try {
			if(getStudent().getId()!=0 && getTempId()!=0){
				Student student=(Student)studentManager.get(Student.class, getStudent().getId());
				Customer newCustomer=(Customer)studentManager.get(Customer.class, getTempId());
				AcademicYear newAcademicYear=(AcademicYear)studentManager.get(AcademicYear.class, " custId="+newCustomer.getId()+" and status='"+Constants.YES_STRING+"'");
				if(!ObjectFunctions.isNullOrEmpty(student)){
					ajaxStudentDisableOrTransfer(student.getAccount(),student);
					student.setDescription("Transfered to "+newCustomer.getOrganization());
					TransferStudent transferStudent=new TransferStudent();
					transferStudent.setAcademicYear(getCurrentAcademicYear());
					transferStudent.setAccount(student.getAccount());
					transferStudent.setCustomer(getCustomerByCustId());
					transferStudent.setNewCustomer(newCustomer);
					transferStudent.setNewSchoolAcademicYearId(newAcademicYear.getId());
					transferStudent.setStatus("A");
					studentManager.save(transferStudent);
					studentManager.save(student);
					super.addActionMessage("Student transfered sucessfully.");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    
    @Actions( {
		@Action(value = "ajaxTransferStudentDetails", results = { @Result(location = "../admin/student/transferStudent/ajaxViewTransferStudentList.jsp", name = "success") })
	})
	public String ajaxTransferStudentDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxTransferStudentDetails' method");
		}
		try {
			setObjectList(studentManager.getAll(TransferStudent.class, " custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    @Actions( {
		@Action(value = "ajaxIntraTransferStudentDetails", results = { @Result(location = "../admin/student/transferStudent/ajaxViewIntraTransferStudentList.jsp", name = "success") })
	})
	public String ajaxIntraTransferStudentDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxIntraTransferStudentDetails' method");
		}
		try {
			setObjectList(studentManager.getAll(TransferStudent.class, " newSchoolId="+getUserCustId()+" and newSchoolAcademicYearId="+getUserAcademicYearId()+" and status='"+Constants.ACTIVE_STATUS+"'"));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    @Actions( {
		@Action(value = "ajaxDoTransferEnableStudent", results = { @Result(location = "../admin/student/transferStudent/ajaxEnableStudentNewSchool.jsp", name = "success") })
	})
	public String ajaxDoTransferEnableStudent() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoTransferEnableStudent' method");
		}
		try {
			if(getTempId()!=0){
				setTransferStudent((TransferStudent)studentManager.get(TransferStudent.class, getTempId()));
				setStudyClassList(studentManager.getAll(StudyClass.class, " custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
				setSchoolCategoriesList(studentManager.getAll(SchoolCategory.class, "custId="+getUserCustId()));
				setCustomer(getCustomerByCustId());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    @Actions( {
		@Action(value = "ajaxEnableTransferStudentDetails", results = { @Result(location = "../admin/student/transferStudent/ajaxViewStudentByAdmissionNumber.jsp", name = "success") })
	})
	public String ajaxEnableTransferStudentDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEnableTransferStudentDetails' method");
		}
		try {
			if(getTempId()!=0){
				TransferStudent transferStudent=(TransferStudent)studentManager.get(TransferStudent.class, getTempId());
				if(!ObjectFunctions.isNullOrEmpty(transferStudent)){
					
					if (!StringFunctions.isNullOrEmpty(getClassSectionId())) {
						StudyClass studyClass = (StudyClass) studentManager.get(StudyClass.class, Long.valueOf(getClassSectionId()));
						if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
							int classStrength = studentManager.getClassStudentsCountByClassIdandStatus(Long.valueOf(studyClass.getId()),Constants.YES_STRING,getUserCustId());
							int remainingSeats = 0;
							remainingSeats = studyClass.getSectionCapacity() - classStrength;
							if (remainingSeats != 0) {
								String admissionNumber=getParamValue("admissionNumber");
								User newUser=new User();
								Address communicationAddress=new Address();//caddressId
								if(!ObjectFunctions.isNullOrEmpty(transferStudent.getAccount().getCommunicationAddress()))
								communicationAddress.copyFrom(transferStudent.getAccount().getCommunicationAddress());
								
								Person person = new Person();//personId
								if(!ObjectFunctions.isNullOrEmpty(transferStudent.getAccount().getPerson()))
								person.copyFrom(transferStudent.getAccount().getPerson());
								
								Address primaryAddress =new Address();//paddressId 
								if(!ObjectFunctions.isNullOrEmpty(transferStudent.getAccount().getPrimaryAddress()))
								primaryAddress.copyFrom(transferStudent.getAccount().getPrimaryAddress());
								
								UserImage profileImage =new UserImage();//imageId 
								if(!ObjectFunctions.isNullOrEmpty(transferStudent.getAccount().getProfileImage()))
								profileImage.copyFrom(transferStudent.getAccount().getProfileImage());
								
								Address tempararyAddress = new Address();//taddressId
								if(!ObjectFunctions.isNullOrEmpty(transferStudent.getAccount().getTempararyAddress()))
								tempararyAddress.copyFrom(transferStudent.getAccount().getTempararyAddress());
								
								newUser.copyFrom(transferStudent.getAccount());
								//String username=getCustomerByCustId().getCustomerShortName()+""+admissionNumber;
								String userName = StringFunctions.stripSymbols(getUserCustId()+"S"+admissionNumber.replace("'", ""));
								newUser.setAdmissionNumber(admissionNumber);
								newUser.setUsername(userName.toLowerCase());
								newUser.setSecondaryUsername(userName.toLowerCase());
								newUser.setPassword(PasswordUtils.passwordEncoder(userName.toLowerCase(), null));
								newUser.setCustId(getUserCustId());
								newUser.setCommunicationAddress(communicationAddress);
								newUser.setPerson(person);
								newUser.setPrimaryAddress(primaryAddress);
								newUser.setProfileImage(profileImage);
								newUser.setTempararyAddress(tempararyAddress);
								
								newUser.setAccountExpired(false);
								newUser.setEnabled(true);
								 Role role = studentManager.getRoleByName(Constants.SCHOOL_STUDENT);
								 newUser.addNewRole(role);
								 role = null;
								 
								User createduser = (User) studentManager.save(newUser);
								if(!ObjectFunctions.isNullOrEmpty(createduser)){
									studentManager.updateStudentUserName(createduser.getId(),PasswordUtils.passwordEncoder(String.valueOf(createduser.getId()),null));
								}
								AcademicYear academicYear = (AcademicYear) studentManager.get(AcademicYear.class, getUserAcademicYearId());
								if (!ObjectFunctions.isNullOrEmpty(createduser)) {
									studentManager.CreateStudentUserIsAvailable(transferStudent,getUserCustId(),academicYear,createduser,studyClass,getPerson(),username,Long.valueOf(getParamValue("categoryName")), getAnyId(),"",profileImage,0,0,0,null,null,null,null,null,null,null,null);
									transferStudent.setStatus("D");//after enable student we will change the status in transport student
									studentManager.save(transferStudent);
									super.addActionMessage("Intra branch student enabled successfully.");
								}
							}else
								super.addActionError("Please increase class capacity.");
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    
    /* @Description 15rd Apr cvs: Modularization  below method  show disable the staff*/  
	@Actions( { @Action(value = "ajaxDoDisableStudent", results = { @Result(location = "../common/ajaxDoStudentDisable.jsp", name = "success") }) })
    public String ajaxDoDisableStaff() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDoDisableStaff' method");
	}
	try {
		    setTempId(getTempId());
		   // setStudent(null);
		  //  getSmsCount();
			//setCustomer(getCustomerByCustId());
		    setStudent((Student) studentManager.get(Student.class,"id="+getTempId()+" and classSectionId="+getSelectedId()));
		    setBedId(getSelectedId());
		    setPlTitle(getStudent().getStatus());
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
    }
	
    /* @Description 15rd Apr cvs: Modularization  below method  show disable the student*/  
	@Actions( { @Action(value = "ajaxDisableStudent", results = { @Result(location = "../admin/common/ajaxViewSearchStudentList.jsp", name = "success"),
		                                                        	@Result(location = "../common/ajaxViewSuspendAndBlacklistedStudentsDetails.jsp", name = "edit")
	}) })
    public String ajaxDoDisableStudent() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDoDisableStudent' method");
	}
	try {
		if(getTempId() != 0){
			Student student=(Student)studentManager.get(Student.class, getTempId());
			if(!ObjectFunctions.isNullOrEmpty(student)){
				String studentStatus = getStudent().getStatus();
				if("S".equalsIgnoreCase(studentStatus) || "B".equalsIgnoreCase(studentStatus)){
					long suspendStudId = getSuspendAndBlacklistStudents().getId();
					Date suspendStudFromDate = getSuspendAndBlacklistStudents().getBlackedOrSuspendFromDate();
					Date suspendStudToDate = getSuspendAndBlacklistStudents().getBlackedOrSuspendToDate();
					SuspendAndBlacklistStudents suspendandBlackstu = null;
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String newDate = formatter.format(suspendStudToDate);
					if(suspendStudId > 0){
						suspendandBlackstu = (SuspendAndBlacklistStudents)studentManager.get(SuspendAndBlacklistStudents.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+suspendStudId);
					}else
						 suspendandBlackstu = new SuspendAndBlacklistStudents();

					suspendandBlackstu.setSuspendDays(getSuspendAndBlacklistStudents().getSuspendDays());
					if(!ObjectFunctions.isNullOrEmpty(suspendStudFromDate))
						suspendandBlackstu.setBlackedOrSuspendFromDate(suspendStudFromDate);
					suspendandBlackstu.setBlackedOrSuspendToDate(suspendStudToDate);
					suspendandBlackstu.setDescription(getSuspendAndBlacklistStudents().getDescription());
					suspendandBlackstu.setStatus(studentStatus);
					suspendandBlackstu.setStudentId(getTempId());
					suspendandBlackstu.setCustId(getUserCustId());
					suspendandBlackstu.setAcademicYearId(getUserAcademicYearId());
					studentManager.save(suspendandBlackstu);
					Date todayDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,new Date()));
					Date susptODate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,suspendStudToDate));
					if(!ObjectFunctions.isNullOrEmpty(suspendStudFromDate)){
						Date suspFromDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,suspendStudFromDate));
						boolean status=DateFormatter.between(todayDate,suspFromDate,susptODate);
						if(status)
							student.setStatus(Constants.YES_STRING);
						else
							student.setStatus(studentStatus);
					}else{
						boolean status= DateFormatter.between(todayDate,null,susptODate);
						if(status)
							student.setStatus(Constants.YES_STRING);
						else
							student.setStatus(studentStatus);
					}
					boolean smsStatus = false;
					if("SMS".equalsIgnoreCase(getBalance())){
						Set<String> mobileNumbers = null;
						Messages message = new Messages();
						StringBuffer buffer = null;
						Customer customer = getCustomerByCustId();
						if (customer.isCheckMobileService()) {
							SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
							String mobileType = customer.getMobileType();
							mobileNumbers = adminManager.addMobileNumbersBasedOnAddressType(mobileType,student.getAccount().getPerson().getMobileNumber(),student.getAccount().getPerson().getSecondaryMobileNumber(),student.getAccount().getPerson().getAnotherMobileNumber(),student.getAccount().getPerson().getAnotherSecondaryMobileNumber(),student.getAccount().getPerson().getAddressType());
							if (!ObjectFunctions.isNullOrEmpty(mobileNumbers) && !"[null]".equalsIgnoreCase(mobileNumbers.toString())) {
								getMobileNumbersSet().addAll(mobileNumbers); 
								message.setCustomer(customer);	
								message.setSmsSenderId(customer.getSender());
								String suspfromDate = null;
								if(ObjectFunctions.isNullOrEmpty(getSuspendAndBlacklistStudents().getBlackedOrSuspendFromDateStrs()))
									suspfromDate =  suspendandBlackstu.getBlackedOrSuspendFromDateStr();
								else
									suspfromDate = getSuspendAndBlacklistStudents().getBlackedOrSuspendFromDateStrs();
								message.setCustomer(customer);    
								buffer = new StringBuffer();
								buffer.append("Dear Parent, "+student.getAccount().getPerson().getFirstName()+" is suspended From "+suspfromDate.replace("00:00:00", "")+" to "+getSuspendAndBlacklistStudents().getBlackedOrSuspendToDateStrs().replace("00:00:00", "")+getSuspendAndBlacklistStudents().getDescription().toString()+" From ");
								message.setMessageDescription(buffer.toString());
								AcademicYear academicYear = (AcademicYear)staffManager.get(AcademicYear.class,getUserAcademicYearId());
								message.setAcademicYear(academicYear);
								message.setStatus(Constants.MODIFY_STATUS);
								message.setCreatedById(getUser().getId());
								message.setPurposeType("Student Suspend SMS");
								log.debug(buffer.toString());
								message = communicationManager.saveMessageDetailsTracking(message,student,null,null);	
								 smsStatus = communicationManager.deliverSms(message,getMobileNumbersSet(), smsServiceProvider);
								 if (smsStatus) {
									 if(suspendStudId > 0)
										 super.addActionMessage("Student status updated successfully.");
									else
										super.addActionMessage("Student suspended and Message has been delivered successfully.");
								} else {
									super.addActionError(" Message is not delivered. ");
								}
							}else
							{
								if(suspendStudId > 0)
									 super.addActionMessage("Student status updated successfully.");
								else
									super.addActionMessage("Student suspended successfully. Message is not delivered.");
							}
								
							 setMobileNumbersSet(null);
							 mobileNumbers = null;
						}
					}
					if("S".equalsIgnoreCase(studentStatus) && !"SMS".equalsIgnoreCase(getBalance()))
					{
						if(suspendStudId > 0)
							 super.addActionMessage("Student status updated successfully.");
						else
							super.addActionMessage("Student suspended successfully.");
					}
						
					else if("B".equalsIgnoreCase(studentStatus) && !"SMS".equalsIgnoreCase(getBalance()))
					{
						if(suspendStudId > 0)
							 super.addActionMessage("Student status updated successfully.");
						else
							super.addActionMessage("Student blacklisted successfully.");
					}
						
					if(suspendStudId>0){
						student.setLastUpdatedById(getUser().getId());
						student.setCreatedDate(new Date());
						student.setLastUpdatedDate(new Date());
						studentManager.save(student);
						ajaxViewStudentStatus();
						return "edit";
					}
				}else{
					User user = student.getAccount();
					//long parentId = user.getStudentParent().getId();
					if(!ObjectFunctions.isNullOrEmpty(user)){
						//user.removeParentChild(user.getStudentParent());
						disableStudentAndParentUserObject(user);
						student.setDescription(getStudent().getDescription());
						student.setStatus(studentStatus);
					}
					//If Parent  doen't have active student then we need to disable parent also
					log.debug(student.getAccount().getId());
					
				}
				student.setLastUpdatedById(getUser().getId());
				student.setCreatedDate(new Date());
				student.setLastUpdatedDate(new Date());
				studentManager.save(student);
				//Sending notification for student profile update
				studentManager.sendNotificationForStudentUpdate(student);
				
				studentManager.updateClassAndSectionCapacity(getUserCustId(),getUserAcademicYearId(),0, student.getClassSection().getId());
				if("N".equalsIgnoreCase(studentStatus) && !"SMS".equalsIgnoreCase(getBalance()))
					super.addActionMessage("Student inactivated successfully.");
				/*else if("Y".equalsIgnoreCase(studentStatus) && !"SMS".equalsIgnoreCase(getBalance()))	
					super.addActionMessage("Student activated successfully.");*/
				student = null;
			}
		}
		setClassId(getBedId()); 
	 studentByCriteriaDetails();
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
    }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Feb 6, 2014       Seshu		        ajaxUploadAdmissionStudentsDetails(Calling from Admissions -> Update Applications -> Upload Student Details) 
 * Feb 6, 2014		 Seshu				ajaxUploadNewAdmissionStudentsDetails(Calling from Admissions -> Application Details -> Download / Upload Admissions -> Upload Student Admission Template )
/********************************************************************/	
	@Actions( { @Action(value = "ajaxUploadAdmissionStudentsDetails", results = {
			@Result(location = "../admin/admission/ajaxDoEditAdmissionDetails.jsp", name = "success"),
			@Result(location = "../admin/admission/ajaxDoEditAdmissionDetails.jsp", name = "userNameAlreadyAdded"),
			@Result(location = "../admin/admission/ajaxDoEditAdmissionDetails.jsp", name = "admissionSuccess"),
			@Result(location = "../admin/admission/ajaxDoEditAdmissionDetails.jsp", name = "dummyInit")}) ,
		@Action(value = "ajaxUploadNewAdmissionStudentsDetails", results = {
			@Result(location = "../admin/admission/admissionDetails.jsp", name = "success")
		})
	})
	public String ajaxUploadAdmissionStudentsDetails()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUploadAdmissionStudentsDetails' method");
		}
		try {
			boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				return "dummyInit";
			}
			long academicYearId = 0;
			String admissionsByFee = "N"; 
			String applicationsType = null;
			long custId = 0;
			HSSFWorkbook workbooks = new HSSFWorkbook(new FileInputStream(getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheets = workbooks.getSheet("ClassSections");
			if (!ObjectFunctions.isNullOrEmpty(sheets)) {
				Row row = sheets.getRow(0);
				academicYearId = (long) row.getCell(1).getNumericCellValue();
				if(!ObjectFunctions.isNullOrEmpty(row.getCell(3))){
					admissionsByFee = row.getCell(3).getStringCellValue();
				}
				if(!ObjectFunctions.isNullOrEmpty(row.getCell(5))){
					applicationsType = row.getCell(5).getStringCellValue();
				}
				row = sheets.getRow(1);
				custId = (long) row.getCell(1).getNumericCellValue();
			}
			if(academicYearId > 0 && custId > 0){
				Customer customer = (Customer) studentManager.get(Customer.class,custId);
				AcademicYear academicYear = (AcademicYear) studentManager.get(AcademicYear.class, academicYearId);
				//Upload new applications into student table if admissionsByFee = false else store them into Pending applications. 
				//Upload edit applications of Admitted students into Student table with fee configured or not.
				//Upload edit applications of pending,Rejected and Shortlisted into OnlineApplicationDetails table.
				if(StringFunctions.isNullOrEmpty(applicationsType)){
					
					processAdmissionsApplications(academicYearId,customer,custId,academicYear,applicationsType);
					
				}else{
					if("'P'".equals(applicationsType) || "'R'".equals(applicationsType) || "'S'".equals(applicationsType)){
						processAdmissionsApplications(academicYearId,customer,custId,academicYear,applicationsType);
					}
				}
			}else{
				super.addActionError("Please download file from system and reupload.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}finally{
			getAdmissionsOnlineApplicationDetails();
			getSmsCount();
		}
		return SUCCESS;
	}
	
	public String processAdmissionsApplications(long academicYearId,Customer customer,long custId,AcademicYear academicYear,String applicationsType){
		try{
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("ClassSections");
			statelist = staffManager.getCountryStates(0);
			HashMap<String, ClassName> clazzMap = new HashMap<String, ClassName>();
			SheetParser parser = null;
			double committedConfiguredFee = 0;
			StringBuffer commitFeeStr = new StringBuffer();
			ClassName className = null;
			String applicationNumber = null;
			Address address = null;
			OnlineApplicationDetails applicationDetails = null;
			Long settingsId = null;
			List<OnlineApplicationDetails> onlineApplicationDetailsList = null;
			HashMap<String, Long> motherToungsMap = new HashMap<String, Long>();
			HashMap<String, SchoolCategory> schoolCategoryMap = new HashMap<String, SchoolCategory>();
			List<SchoolCategory> categoryList = studentManager.getAll(SchoolCategory.class,"custId="+getUserCustId());
			if (ObjectFunctions.isNotNullOrEmpty(categoryList)) {
				for (SchoolCategory categorySetting : categoryList) {
					schoolCategoryMap.put(categorySetting.getCategoryName(),categorySetting);
				}
			}
			List<MotherTongue> motherTongueList = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
			if (ObjectFunctions.isNotNullOrEmpty(motherTongueList)) {
				for (MotherTongue motherTongueObj : motherTongueList) {
						motherToungsMap.put(motherTongueObj.getName(),motherTongueObj.getId());
				}
			}
			HashMap<String, CastSettings> castSettingsMap = new HashMap<String, CastSettings>();
			
			List<CastSettings> castSettingsList = studentManager.getAll(CastSettings.class,"custId="+getUserCustId());
			if (ObjectFunctions.isNotNullOrEmpty(castSettingsList)) {
				for (CastSettings castSettingsObj : castSettingsList) {
					castSettingsMap.put(castSettingsObj.getCastName(),castSettingsObj);
				}
			}
		     HashMap<String, SubCastSettings> subCastSettingsMap = new HashMap<String, SubCastSettings>();
					List<SubCastSettings> subCastSettingsList = studentManager.getAll(SubCastSettings.class,"custId="+getUserCustId());
					if (ObjectFunctions.isNotNullOrEmpty(subCastSettingsList)) {
						for (SubCastSettings castSettingsObj : subCastSettingsList) {
							subCastSettingsMap.put(castSettingsObj.getSubCastName(),castSettingsObj);
						}
					}
			List<ClassName> classList = studentManager.getAll(ClassName.class, "academicYearId=" + academicYearId+ " order by sortingOrder");
			if (ObjectFunctions.isNotNullOrEmpty(classList)) {
				for (ClassName clazz : classList) {
					clazzMap.put(clazz.getClassName().toUpperCase().trim(), clazz);
				}
			}
			int currentRow;int i=0;
			for (int sheetNum = 0; sheetNum < (workbook.getNumberOfSheets() - 1); sheetNum++) {
				sheet = workbook.getSheetAt(sheetNum);
				parser = new SheetParser();
				currentRow = 2;
				onlineApplicationDetailsList = parser.createEntity(sheet, sheet.getSheetName(), OnlineApplicationDetails.class);
				if (ObjectFunctions.isNotNullOrEmpty(onlineApplicationDetailsList)) {
					
					log.debug("appli size :"+onlineApplicationDetailsList.size());
					for (OnlineApplicationDetails onlineApplicationDetail : onlineApplicationDetailsList) {
						
						if (!ObjectFunctions.isNullOrEmpty(onlineApplicationDetail)	&& !ObjectFunctions.isNullOrEmpty(onlineApplicationDetail.getFirstName()) && StringFunctions.isNotNullOrEmpty(onlineApplicationDetail.getClassName())) {
							if(StringFunctions.isNotNullOrEmpty(onlineApplicationDetail.getApplicationIdStr()) && !"0".equals(onlineApplicationDetail.getApplicationIdStr())){
								applicationDetails = (OnlineApplicationDetails)studentManager.get(OnlineApplicationDetails.class,Long.valueOf(onlineApplicationDetail.getApplicationIdStr()));
							}else{
								applicationDetails = new OnlineApplicationDetails();
							}
								className = clazzMap.get(onlineApplicationDetail.getClassName().toUpperCase().trim());
								if (ObjectFunctions.isNullOrEmpty(className)) {
									//classNames.add(applicationDetails.getClassName()+", ");
									log.debug("*** Class admission is not open **");
									continue;
								} else {
									applicationDetails.setClassId(className);
								}
								applicationDetails.copyFrom(onlineApplicationDetail);
								if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetail.getCategoryName())){
									applicationDetails.setCategoryId(schoolCategoryMap.get(onlineApplicationDetail.getCategoryName()).getId());
								}
								else{
									applicationDetails.setCategoryId(schoolCategoryMap.get("General").getId());
								}
								if (StringFunctions.isNotNullOrEmpty(onlineApplicationDetail.getMotherToung())) {
									settingsId = motherToungsMap.get(onlineApplicationDetail.getMotherToung().trim());
									if(!ObjectFunctions.isNullOrEmpty(settingsId))
										applicationDetails.setMotherToungId(settingsId);
								} else
									applicationDetails.setMotherToungId(0);
								if(!StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getStreetName()) || !StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getCity()) || !StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getState()) || !StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getPostalCode())){
									address = applicationDetails.getPrimaryAddress();
									if(ObjectFunctions.isNullOrEmpty(address))
										address = new Address();
										if(!StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getStreetName()))
											address.setStreetName(onlineApplicationDetail.getPrimaryAddress().getStreetName());
										if(!StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getCity()))
											address.setCity(onlineApplicationDetail.getPrimaryAddress().getCity());
										if(!StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getState())){
											address.setState(statelist.get(onlineApplicationDetail.getPrimaryAddress().getState()).getStateCode());
											address.setStateId(statelist.get(onlineApplicationDetail.getPrimaryAddress().getState()).getId());
										}
										if(!StringFunctions.isNullOrEmpty(onlineApplicationDetail.getPrimaryAddress().getPostalCode()))
											address.setPostalCode(onlineApplicationDetail.getPrimaryAddress().getPostalCode());
										address.setLastUpdatedDate(new Date());
										applicationDetails.setPrimaryAddress(address);
									}else
										applicationDetails.setPrimaryAddress(null);
								if (StringFunctions.isNotNullOrEmpty(onlineApplicationDetail.getReligion()))
									createOrUpdateReligion(onlineApplicationDetail.getReligion());
								if (!ObjectFunctions.isNullOrEmpty(getCommonType())) {
									applicationDetails.setReligion(getCommonType().getSkillTypeName());
									applicationDetails.setReligionId(getCommonType());
								}
								
								if (!ObjectFunctions.isNullOrEmpty(onlineApplicationDetail.getSubCastName())) 
									applicationDetails.setCastId(castSettingsMap.get(onlineApplicationDetail.getSubCastName()));
								if (!ObjectFunctions.isNullOrEmpty(onlineApplicationDetail.getCastName()))
									applicationDetails.setSubCastId(subCastSettingsMap.get(onlineApplicationDetail.getCastName()));
								if (!ObjectFunctions.isNullOrEmpty(onlineApplicationDetail.getCommittedFeeStr())) {
									if (Double.valueOf(onlineApplicationDetail.getCommittedFeeStr()) != 0d) {
										StudyClass studyClass = (StudyClass) adminManager.get(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and className ='"+ onlineApplicationDetail.getClassName()+ "' ");
										committedConfiguredFee = studentManager.getCommittedConfiguredFeeAmountCategoryWise(getUserCustId(),academicYear.getId(),studyClass.getId(),applicationDetails.getCategoryId(),"");
										if (Double.valueOf(onlineApplicationDetail.getCommittedFeeStr()) != 0) {
											prepareAdmissionApplicantStudentSchoolFeeSettingList(applicationDetails);
											Map<String, String> msgs = studentManager.validateCommittedFeeConstrains(getUserCustId(),academicYear.getId(),Double.valueOf(onlineApplicationDetail.getCommittedFeeStr()),className.getId(),studyClass.getId(),applicationDetails.getCategoryId(),getTempString());

											if (!ObjectFunctions.isNullOrEmpty(msgs)) {
												if(StringFunctions.isNullOrEmpty(onlineApplicationDetail.getApplicationIdStr()) || "0".equals(onlineApplicationDetail.getApplicationIdStr()))
													commitFeeStr.append(studentManager.getApplicationNumber(customer,academicYear)+ ",");
											} else
												applicationDetails.setCommittedFee(Double.valueOf(onlineApplicationDetail.getCommittedFeeStr()));
										}
									}
								}
								applicationDetails.setCustId(custId);
								if(StringFunctions.isNullOrEmpty(applicationsType) || "'P'".equals(applicationsType))
									applicationDetails.setStatus("P");
								else if("'S'".equals(applicationsType))
									applicationDetails.setStatus("S");
								else if("'R'".equals(applicationsType))
									applicationDetails.setStatus("R");
								applicationDetails.setAcademicYear(academicYear);
								if(StringFunctions.isNullOrEmpty(onlineApplicationDetail.getApplicationIdStr()) || "0".equals(onlineApplicationDetail.getApplicationIdStr())){
									applicationNumber = studentManager.getApplicationNumber(customer,academicYear);
									applicationDetails.setApplicationNumber(applicationNumber);
								}
								studentManager.saveOrUpdateObject(applicationDetails);
								applicationDetails = null;
						}else {
							i++;
						log.debug("manadataray data not available"+i);
						}
						currentRow++;
					}
				}
				getAdmissionsOnlineApplicationDetails();
			}
		if(commitFeeStr.length() !=0)
			super.addActionError(commitFeeStr.toString()+ "\n The following application number(s) committed fee is not saved.");
			if(i==onlineApplicationDetailsList.size())
				super.addActionError("Please add at least one application details.");
			else if (onlineApplicationDetailsList.size() > i & i!=0){ 
				super.addActionError("Please add at least one application details.");
				super.addActionMessage("Applications added successfully.");
			}else if(StringFunctions.isNullOrEmpty(applicationsType)){
				super.addActionMessage("Applications added successfully.");
			}else
				super.addActionMessage("Applications updated successfully.");
			parser = null;
		}catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return "admissionSuccess";
	}
	
	 /* @Description 4th Sep PVSL:show pending fee student details */ 
	@Actions( { @Action(value = "ajaxCkeckForDisableStudent", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "objectList.*" }) }) })
	public String ajaxCkeckForDisableStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'EventDetails' method");
		}
		try {
			if(getTempId() != 0){
				List<Object[]> partialPaidAmountDetails = studentManager.getAll("select id,SUM(feeAmount),feeType,studentId,settingName from vw_studentFeePaymentDetails where studentId="+getTempId()+ "  and paymentStatus='"+ Constants.NO_STRING + "' group by feeType");
				if (!ObjectFunctions.isNullOrEmpty(partialPaidAmountDetails)) {
					String pending="";
					for(Object[] feeTypeObj : partialPaidAmountDetails){
						if("N".equalsIgnoreCase(feeTypeObj[2].toString())){
							pending="Pending "+ feeTypeObj[4].toString();
						}else{
							pending= "Pending "+ feeTypeObj[2].toString();
						}
						getObjectList().add(pending+":"+feeTypeObj[1].toString());
					}
				}
				Student student=(Student) studentManager.get(Student.class, "id="+getTempId());
				List<Object[]> issuedBookDetails = studentManager.getAll("select accountId,bookName,lableCode from vw_issuedBookAndSettings where accountId="+student.getAccount().getId()+ "  and status='"+ Constants.PAYMENT_STATUS + "' group by lableCode");
				if (!ObjectFunctions.isNullOrEmpty(issuedBookDetails)) {
					getObjectList().add("");
					for(Object[] issuedBookObj : issuedBookDetails){
						getObjectList().add("Pending book is "+""+issuedBookObj[1].toString()+"("+issuedBookObj[2].toString()+")");
					}
				}
				getJsonResult().put("objectList", getObjectList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	public void updateStudentOnlineApplicaton(Student student,Customer customer){
		try{
			if(!ObjectFunctions.isNullOrEmpty(student.getOnlineApplicationDetailsId())){
				OnlineApplicationDetails applicationDetails = (OnlineApplicationDetails)studentManager.get(OnlineApplicationDetails.class,student.getOnlineApplicationDetailsId());
				if(!ObjectFunctions.isNullOrEmpty(applicationDetails)){
					CastSettings castSetting = null;
					SubCastSettings subCastSettings = null;
					CommonType religion = null;
					if(!ObjectFunctions.isNullOrEmpty(applicationDetails)){
						applicationDetails.copyStudentInfo(student);
							if(!ObjectFunctions.isNullOrEmpty(student.getAccount().getPerson().getReligion()))
								religion = (CommonType)studentManager.get(CommonType.class, student.getAccount().getPerson().getReligionId());
							applicationDetails.setReligionId(religion);
							if(student.getAccount().getPerson().getCastId() > 0)
								castSetting =(CastSettings)studentManager.get(CastSettings.class, student.getAccount().getPerson().getCastId());
							applicationDetails.setCastId(castSetting);
							if(student.getAccount().getPerson().getSubCastId() > 0)
								subCastSettings = (SubCastSettings)studentManager.get(SubCastSettings.class, student.getAccount().getPerson().getSubCastId());
							applicationDetails.setSubCastId(subCastSettings);
							applicationDetails.setStatus("C");
							applicationDetails.setAcademicYear(student.getAcademicYear());
							studentManager.save(applicationDetails);
							applicationDetails = null;
						}
					castSetting = null;
				}
				applicationDetails = null;
			}
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			// TODO: handle exception
		}
	}
	/** * @Description 8th Apr cvs: Modularization add new method for report */
	@Actions( { @Action(value = "ajaxDoDownloadAdmissionStudents", results = {}) })
	public void ajaxDoDownloadAdmissionStudents() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoDownloadAdmissionStudents' method");
		}
		try {
			setAcademicYear((AcademicYear)studentManager.get(AcademicYear.class, getAcademicYearId()));
			if(!ObjectFunctions.isNullOrEmpty(getAcademicYear())){
				
				ajaxAllStudentsAdmissionsTemplet();
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions( { @Action(value = "ajaxAllStudentsAdmissionsTemplet", results = {}) })
    public void ajaxAllStudentsAdmissionsTemplet() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxAllStudentsAdmissionsTemplet' method");
	}
	try {
            String[] words = { "Own", "Private", "School Tranport" };
            Arrays.asList(words);
            String fileName = null;
            List<ClassName> classes = null;
            if("AdmissionsEditProcess".equals(getAnyTitle())){
            	if("'P'".equalsIgnoreCase(getAnyId()))
            		fileName = "PendingApplicationDetails_" + DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN, new Date());
            	else if("'R'".equalsIgnoreCase(getAnyId()))
            		fileName = "RejectedApplicationDetails_" + DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN, new Date());
            	else if("'S'".equalsIgnoreCase(getAnyId()))
            		fileName = "ShortlistedApplicationDetails_" + DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN, new Date());
            }else
            	fileName = "ApplicationDetails_" + DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN, new Date());
            getResponse().setContentType("application/vnd.ms-excel");
            getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_') + ".xls");
            PrepareStudentAdmissionExcel prepareStudentAdmissExcel = new PrepareStudentAdmissionExcel();
            List<Object[]> studentDetails=null;
            List<ClassName> classList= studentManager.getAll(ClassName.class," academicYearId = " + getAcademicYearId() + " and custId = " + getUserCustId()+ " and admissionsOpen='"+Constants.YES_STRING+"' order by sortingOrder asc");
            List<State> states = (List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST);
            //List<MotherTongue> motherTongues = studentManager.getAll(MotherTongue.class);
            List<MotherTongue> motherTongues = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
            List<SchoolCategory> schoolCategory = studentManager.getAll(SchoolCategory.class," custId = " + getUserCustId());
            List<CastSettings> communityList = studentManager.getAllByCustId("CastSettings", getUserCustId(),0);
			List<SubCastSettings> castNameList = studentManager.getAll(SubCastSettings.class,"custId ="+getUserCustId());
            AcademicYear academicYear = null;
            if(ObjectFunctions.isNotNullOrEmpty(classList)){
            	academicYear = classList.get(0).getAcademicYear();
            }
            if(!ObjectFunctions.isNullOrEmpty(academicYear)){
            	prepareStudentAdmissExcel.createClassSectionSheet("ClassSections", classList,states, getAcademicYearId(),getUserCustId(),academicYear.getManageStudentsAdmissionsByFee(),getAnyId(),schoolCategory,motherTongues,communityList,castNameList);
            }
            StringBuffer sheetTitleDesc = new StringBuffer();
        	sheetTitleDesc.append("School Name : ");
        	if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))){
        		sheetTitleDesc.append((String)getSession().getAttribute("organization"));
        	}
        	if(getAcademicYearId() >0){
        		AcademicYear academicyear = (AcademicYear)adminManager.get(AcademicYear.class,"id="+getAcademicYearId());
        		if(!ObjectFunctions.isNullOrEmpty(academicyear)){
        			setAcademicYear(academicyear);
        		}
        	}
        	sheetTitleDesc.append(", Academic Year : ");
        	if (!ObjectFunctions.isNullOrEmpty(getAcademicYear())) {
				sheetTitleDesc.append(getAcademicYear().getAcademicYear());
			}
        	if("AdmissionsEditProcess".equals(getAnyTitle())){
        		classes = studentManager.getAll(ClassName.class, "id in " + getSelectedId());
			}
        	if (ObjectFunctions.isNotNullOrEmpty(classes)) {
				for (ClassName clas : classes) {
					log.debug(PrepareStudentAdmissionExcel.query+ " where classId="+ clas.getId()+ " and status="+getAnyId()+" order by firstName,lastName,applicationNumber");
					studentDetails = studentManager.getAll(PrepareStudentAdmissionExcel.query+ " where classId="+ clas.getId()+ " and status="+getAnyId()+" order by firstName,lastName,applicationNumber");
					if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
						prepareStudentAdmissExcel.createStudentSheet(clas.getClassName(), studentDetails,sheetTitleDesc.toString(),getCustomerByCustId().getCommittedFeeStatus());
					}
				}
			} else {			
				prepareStudentAdmissExcel.createStudentSheet("Student",studentDetails, sheetTitleDesc.toString(),getCustomerByCustId().getCommittedFeeStatus());
			}
        	if(!ObjectFunctions.isNullOrEmpty(academicYear))
            prepareStudentAdmissExcel.finalPrep("ClassSections",studentDetails);
            studentDetails=null;
            prepareStudentAdmissExcel.getWb().write(getResponse().getOutputStream());
            
    } catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    }
	}
	@Actions( { @Action(value = "ajaxDownloadOnlineApplicationDetails", results = {}) })
	public void ajaxDownloadOnlineApplicationDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadOnlineApplicationDetails' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getAnyId()) && getAcademicYearId() > 0){
				if("'C'".equalsIgnoreCase(getAnyId())){
					setAnyTitle("AdmissionProcess");
					ajaxDownloadStudent();
				}else {
					setAnyTitle("AdmissionsEditProcess");
					ajaxAllStudentsAdmissionsTemplet();
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	@Actions( { @Action(value = "ajaxViewMyDocumentsInfo", results = { @Result(location = "ajaxViewStudentDocumentsInformation.jsp", name = "success") }),
		        @Action(value = "ajaxViewUploadOrDownloadDocumentsInfo", results = { @Result(location = "parent/ajaxViewUploadOrDownloadDocumentsInfo.jsp", name = "success") }) })
	public String ajaxViewMyDocumentsInfo() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMyDocumentsInfo' method");
		}
		try {
			StringBuffer pathName =null;
			
			if (getTempId() != 0) 
			{
				Student student = (Student) studentManager.get(Student.class,getTempId());
				if (!ObjectFunctions.isNullOrEmpty(student)) 
				{
					if (!ObjectFunctions.isNullOrEmpty(student.getAccount().getPersonDocuments())) 
					{
						setTempList(student.getAccount().getPersonDocuments());
					}
					setStudent(student);
				}
				student = null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
			return SUCCESS;
		}
	
	@Actions( { @Action(value = "ajaxAddDocumentsToStudent", results = { @Result(location = "ajaxViewStudentDocumentsInformation.jsp", name = "success") }) ,
		        @Action(value = "ajaxAddDocumentsOfStudent", results = { @Result(location = "parent/ajaxViewUploadOrDownloadDocumentsInfo.jsp", name = "success") }) })
	public String ajaxAddDocumentsToStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddDocumentsToStudent' method");
		}
		try 
		{
			User userObj = null;
			AcademicYear academicYear = getCurrentAcademicYear();
			if (getTempId() != 0) 
			{
				Student student = (Student) studentManager.get(Student.class,getTempId());
				if (!ObjectFunctions.isNullOrEmpty(student)) 
				{
					userObj = student.getAccount();
				}
				student = null;
			}
			else
			{
				String admissionNumber=getParamValue("admissionNumber");
				userObj=(User)studentManager.get(User.class,"admissionNumber='"+admissionNumber+"' and custId="+getUserCustId());
				admissionNumber = null;
			}
			if (!ObjectFunctions.isNullOrEmpty(userObj)) 
			{
				if(getFileUpload().size()!=0)
				{
			    	for(int i=0;i<getFileUpload().size();i++)
			    	{
			    		 File file = getFileUpload().get(i);
			    		 if(!ObjectFunctions.isNullOrEmpty(file))
			    		 {
				    		 String fileName = getFileUploadFileName().get(i);
				    		 String filePath = adminManager.getUploadDocuments(file, academicYear.getAcademicYear(), fileName);
			    			if(!StringFunctions.isNullOrEmpty(filePath))
			    			{
			    				PersonDocuments personDocuments = new PersonDocuments();
			    				personDocuments.setFileName(fileName);
			    				personDocuments.setCreatedById(getUser().getId());
			    				personDocuments.setFilePath(filePath);
			    				personDocuments.setAcademicYearId(getUserAcademicYearId());
			    				userObj.addPersonDocuments(personDocuments);
			    			}
			    		 }
			    	}
			    	studentManager.save(userObj);
				}
			}
			
			academicYear = null;
			
			    ajaxViewMyDocumentsInfo();
			    super.addActionMessage("Documents added successfully.");
			    /*if (isCheck) {
					return "parentAndAdmin";
				}*/
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "downloadStudentDocuments", results = {}) })
	public String ajaxDownloadStudentScannedDocuments() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadStudentScannedDocuments' method");
		}
		try {
			
			if (!ObjectFunctions.isNullOrEmpty(getStudent()) || getUser().isOnlySchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolClerk() || getUser().isSchoolDirector()) 
			{
				if (getTempId1() != 0) {
			    {
			    	if(StringFunctions.isNotNullOrEmpty(getFileName()))
			    	{
			    		PersonDocuments personDocuments = (PersonDocuments) studentManager.get(PersonDocuments.class,getTempId1());
			    		
				    	URL url = new URL(personDocuments.getFilePath());
						URLConnection conn = url.openConnection();
						InputStream is = conn.getInputStream();
						
						File destFile = File.createTempFile(personDocuments.getFileName(), null);
						destFile.deleteOnExit();
						FileUtils.copyInputStreamToFile(is, destFile);
						
			    		getResponse().setContentType("application/octet-stream");
			    		getResponse().addHeader("Content-Disposition", "attachment; filename="+personDocuments.getFileName().replace(' ', '_'));
			    		ServletOutputStream out = getResponse().getOutputStream();
		                DataInputStream in = new DataInputStream(new FileInputStream(destFile));
		                byte[] bbuf = new byte[1024];
		                int length = 0;
		                while (in != null && (length = in.read(bbuf)) != -1) {
		                    out.write(bbuf, 0, length);
		                }
		                in.close();
		                out.flush();
		                out.close();
		                personDocuments = null;
		                destFile.delete();
			    	}else
			    	{
			    		ZipOutputStream zipOutStream = null;
			    		File directory = Files.createTempDirectory("Documents").toFile();
			    		List<PersonDocuments> personDocumentsList = studentManager.getAll(PersonDocuments.class,"accountId="+getTempId1());
			    		if(!ObjectFunctions.isNullOrEmpty(personDocumentsList))
			    		{
			    			zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
							getResponse().setContentType("application/zip");
							getResponse().addHeader("Content-Disposition", "attachment; filename=STUDENT_DOCUMENTS.zip");
							
							for(PersonDocuments personDocumentsObj : personDocumentsList)
							{
								URL url = new URL(personDocumentsObj.getFilePath());
								URLConnection conn = url.openConnection();
								InputStream is = conn.getInputStream();
								
								String ext = FilenameUtils.getExtension(personDocumentsObj.getFileName());
								//File file = File.createTempFile(name, "",directory);
								File file = new File(directory,personDocumentsObj.getFileName());

								FileUtils.copyInputStreamToFile(is, file);
							}
							
							StringFunctions.zipFiles(directory,zipOutStream);
                            FileUtils.deleteDirectory(directory);
			    		}
						
							zipOutStream = null;
							//destFile = null;
			    	}
			    }
			}
			}
			super.addActionMessage("Documents Download successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( { @Action(value = "ajaxRemoveDocuments", results = { @Result(location = "ajaxViewStudentDocumentsInformation.jsp", name = "success") }),
				@Action(value = "ajaxRemoveDocumentsOfStudent", results = { @Result(location = "parent/ajaxViewUploadOrDownloadDocumentsInfo.jsp", name = "success") }) })
	public String ajaxRemoveDocuments() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveDocuments' method");
		}
		try {
			if (getTempId1() != 0) 
			{
				S3Wrapper s3Wrapper = new S3Wrapper();
				if (!StringFunctions.isNullOrEmpty(getTempString()))
				{
					PersonDocuments personDocuments = (PersonDocuments) studentManager.get(PersonDocuments.class,getTempId1());
					if (!ObjectFunctions.isNullOrEmpty(personDocuments)) 
					{
						/*try {
							URL url = new URL(personDocuments.getFilePath());
							s3Wrapper.delete(url);
						} catch (Exception e) {
							e.printStackTrace();
						}*/
						adminManager.remove(PersonDocuments.class, getTempId1());
					}
					personDocuments = null;
					super.addActionMessage("Document removed successfully.");
				}
				else
				{
					List<PersonDocuments>  personDocumentsList  = studentManager.getAll(PersonDocuments.class,"accountId="+getTempId1());
					if (!ObjectFunctions.isNullOrEmpty(personDocumentsList)) 
					{
						for(PersonDocuments personDocuments : personDocumentsList)
						{
								adminManager.remove(PersonDocuments.class, personDocuments.getId());
						}
					}
					personDocumentsList = null;
					super.addActionMessage("Documents removed successfully.");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewMyDocumentsInfo();
			return SUCCESS;
	}
	 public void showDocumentsToAdmin(String pathName) {
		try {
			File directory = new File(getSession().getServletContext().getRealPath(pathName));
			File[] fList = directory.listFiles();
			List FileNamesList = new ArrayList<Attachment>();
	    	Attachment attachment = new Attachment();
	    	if(!ObjectFunctions.isNullOrEmpty(fList)){
	    		for (File file : fList) {
			        if (file.isFile()) {
			            log.debug(file.getAbsolutePath());
			        } else if (file.isDirectory()) {
			        	String[] children = file.list();
				    	 for (int i=0; i<children.length; i++) {
				    		 for(ViewStudentPersonAccountDetails studDetails : getViewStudentPersonAccountDetailsList()){
				    			 if(!ObjectFunctions.isNullOrEmpty(studDetails)){
				    				 if(file.getName().equalsIgnoreCase(String.valueOf(studDetails.getAccountId()))){
				    				   attachment = new Attachment();
						                // Get filename of file or directory
						                String filename = children[i];
						                attachment.setFileTypePath(studDetails.getAdmissionNumber());
						                attachment.setFileName(filename);
						                attachment.setFilePath(file.getName());
						                FileNamesList.add(attachment);
				    				 }
				    				
				    			 }
				    			
				    		 }
			            	
			            }
			        	setTempList(FileNamesList);
			        }
			    }
	    	}
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	 
		// changed by rama on 6/6/2014, for showing validation of the validation of the files. 
	 @Actions( { @Action(value = "ajaxUploadStudentImages", results = { @Result(location = "../admin/common/ajaxViewNoImageStudentList.jsp", name = "success") })})
    public String ajaxUploadStudentImages() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxUploadStudentImages' method");
	}
	try {
		JSONObject formData = new JSONObject(getAnyTitle());
		JSONArray jsonArray =(JSONArray) formData.get("JSONOBJ");
		JSONObject jsonObj = null;
		UserImage attachment = null;
		String adminssionNum=null;
		String browserUrl=null;
		String imageData=null;
		//long studentId=0;
		long accountId=0;
		String className=null;
		int indexCount=0;
		boolean isImageUpload = false;
		StringBuffer notformat =  new StringBuffer();
		// Uploading the images for students.
		Customer customer = getCustomerByCustId();
		if(!ObjectFunctions.isNullOrEmpty(jsonArray)){
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONArray jsnArr =(JSONArray) jsonArray.get(i);
			if (!ObjectFunctions.isNullOrEmpty(jsnArr)) {
				for (int j = 0; j < jsnArr.length(); j++) {
					jsonObj = jsnArr.getJSONObject(j);
					if (!ObjectFunctions.isNullOrEmpty(jsonObj)) {
						Iterator<String> iter = jsonObj.keys();
						 while (iter.hasNext()) {
						   String key = iter.next();
						   if(key.equalsIgnoreCase("IMAGEDATA")){
							   imageData=  (String)jsonObj.get(key);							  
							 }
						   else if(key.equalsIgnoreCase("ADMISSIONNUM")){
							   adminssionNum=  (String)jsonObj.get(key);							  
							 }
						   else if(key.equalsIgnoreCase("BROWSEURL")){
							   browserUrl=  ((String)jsonObj.get(key)).toLowerCase();							  
							 }
						   else if(key.equalsIgnoreCase("ACCOUNTID")){
							   accountId= Long.valueOf((String)jsonObj.get(key));							  
							 }
						   else if(key.equalsIgnoreCase("CLASSNAME")){
							   className= (String)jsonObj.get(key);							  
							 }
						 }
						 //The line committed by Sunanda due to image is not updated for promoted students but academciYear not switched 
				        // student=(Student) studentManager.get(Student.class, "custId=" + getUserCustId()+" and accountId ='" + accountId + "' and status = '" + Constants.YES_STRING+"'");
						// log.debug("Before getting student:" + DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date())));
						 Student student=(Student) studentManager.get(Student.class, "custId=" + getUserCustId()+" and accountId ='" + accountId + "' and academicYearId ="+getUserAcademicYearId());
						// log.debug("After getting student:" + DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date())));
				         log.debug(accountId);
				        // log.debug("---------------");
						if(!ObjectFunctions.isNullOrEmpty(student)){
						if(!StringFunctions.isNullOrEmpty(browserUrl)){
							try {
								if(browserUrl.lastIndexOf(".jpg") == -1 && browserUrl.lastIndexOf(".jpeg")== -1 && browserUrl.lastIndexOf(".png") == -1){
									notformat.append(browserUrl);
									notformat.append(",");
								  }
								else{
									long imageId = 0;
									setUploadFileName(browserUrl.trim());
									setSelectedId(String.valueOf(indexCount)); // Here we are sending file index to baseAction.
								    attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE,customer.getId(),getUserAcademicYearId(),imageId);
								    student.setProfileImage(attachment);
								    adminManager.save(student);
								    isImageUpload = true;
								}
							    indexCount++;
							}
							catch (Throwable ex) {
								ex.printStackTrace();
							}
						 } 
						 else if(!StringFunctions.isNullOrEmpty(imageData)){
							if(!imageData.contains("photo_notAvailable.jpg")){
								try {
								 imageData=imageData.replace("data:image/png;base64,", "");
								 attachment = captureStudentImage(imageData,Constants.FILE_TYPE_IMAGE,customer);	
								 //student.getAccount().setProfileImage(attachment);
								 student.setProfileImage(attachment);
								 adminManager.save(student);
								 isImageUpload = true;
								}
								catch (Throwable ex) {
									ex.printStackTrace();
								}
							}
						 }
						
						attachment = null;
						} 
						student = null;
						 adminssionNum=null;
						 browserUrl=null;
						 imageData=null;
						 //studentId=0;
						 accountId=0;
					 }
				}
			}
		 }

		if(!ObjectFunctions.isNullOrEmpty(notformat.toString())){
			super.addActionError("You can't upload ["+notformat+"] this unformatted files.Please use png or jpg or jpeg format.");
		}
		if(!ObjectFunctions.isNullOrEmpty(isImageUpload)){
			super.addActionMessage("You have successfully uploaded image.");
		}
		if(!isImageUpload && ObjectFunctions.isNullOrEmpty(notformat.toString())){
			super.addActionError("You have not selected browse or capture for uploading image.");
		}
	}
		setAnyTitle(null);
		setAnyId("Upload");
		setClassId(getClassId());
		prepareStudentsDetailsList();
		studentByCriteriaDetails();
	}
	catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	 
	 @Actions( { @Action(value = "ajaxViewStudentAssignment", results = { @Result(location = "../common/classAssignment/ajaxViewStudentAssignmentInfo.jsp", name = "success") })})
		public String ajaxViewStudentAssignment() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxViewStudentAssignment' method");
	}
	try {
		Date todayDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,new Date()));
		String thisYear = DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, todayDate);
		ClassAssignment clsAssignment = null;
		Student student = null;
		if(!ObjectFunctions.isNullOrEmpty(getTempId()) && getTempId() > 0)
			student = (Student)studentManager.get(Student.class,"id="+getTempId());
		else
			student = (Student)studentManager.get(Student.class,"accountId="+getUser().getId());
		if(!ObjectFunctions.isNullOrEmpty(student)){
			List<ClassAssignment> assignments = studentManager.getAll(ClassAssignment.class,"classSectionId="+student.getClassSectionId()+" order by assignmentDate desc");
			if(!ObjectFunctions.isNullOrEmpty(assignments)){
				setTempList(assignments);
				for(Object stdassignment : getTempList() ){
					String completionStatus =null;
					clsAssignment = (ClassAssignment)stdassignment;
					StudySubject subject =(StudySubject)studentManager.get(StudySubject.class," id="+clsAssignment.getSubjectId());
					Date assignmentDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,clsAssignment.getAssignmentDate()));
					String assignmentYear =DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, assignmentDate);
					if(!ObjectFunctions.isNullOrEmpty(assignmentDate) && !ObjectFunctions.isNullOrEmpty(assignmentYear)){
						
						StudentClassAssignment stClassAssignment = null;
						if("A".equalsIgnoreCase(clsAssignment.getStatus()))
						{
							stClassAssignment = (StudentClassAssignment) studentManager.get(StudentClassAssignment.class,"assignmentId="+clsAssignment.getId() + " and studentId="+student.getId());
							if(ObjectFunctions.isNullOrEmpty(stClassAssignment)){
								completionStatus = "Done";
								clsAssignment.setAssignmentDate(assignmentDate);
							}
							else
							{
								completionStatus = "Need To Do";
								clsAssignment.setAssignmentDate(assignmentDate);
							}
						}
						else
						{
							if(assignmentDate.compareTo(todayDate)<=0 && assignmentYear.compareTo(thisYear) ==0){
								completionStatus = "Not Done";
								Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,assignmentDate));
								clsAssignment.setAssignmentDate(aDate);
							}
							else{
								completionStatus = "Need To Do";
								clsAssignment.setAssignmentDate(assignmentDate);
							}
						}
						
						//clsAssignment.setSubjectName(subject.getName());
						clsAssignment.setCompletionStatus(completionStatus);
						getObjectList().add(clsAssignment);
						
					
				}
				setTempList1(getObjectList());
			}
			}
			assignments = null;
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
}

	 @Actions( { @Action(value = "ajaxViewYourStudentAssignment", results = { @Result(location = "../common/classAssignment/ajaxViewYourStudentAssignmentInfo.jsp", name = "success") })})
		public String ajaxViewYourStudentAssignment() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxViewYourStudentAssigsnment' method");
	}
	try {
		StringBuffer query = new StringBuffer();
		if(getUser().isParent()){
			query.append("FROM Student stud WHERE  stud.account.id=").append(getUser().getSelectedStudentId()).append(" and stud.status='Y'");
			setTempList2(studentManager.getAllHqlQuery(query.toString()));
			if(getTempId() > 0){
				ajaxSelectedStudentInfo();
			}else if(ObjectFunctions.isNotNullOrEmpty(getTempList2())){
				setTempId(((Student)getTempList2().get(0)).getId());
				ajaxViewStudentAssignment();
			}
		}else
			ajaxViewStudentAssignment();
		query = null;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
}
	 
	 @Actions( { @Action(value = "ajaxSelectedStudentInfo", results = { @Result(location = "../common/classAssignment/ajaxStudentAssignmentInfo.jsp", name = "success") })})
		public String ajaxSelectedStudentInfo() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxSelectedStudentInfo' method");
	}
	try {
		if(getTempId() > 0){
			
			ajaxViewStudentAssignment();
			
			}
		
		
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
}
	
	@Actions( {@Action(value = "ajaxDoFeedbackLink", results = {@Result(location = "parent/feedback/selectChildForFeedback.jsp", name = "success") }),
	   @Action(value = "ajaxDoAddSchoolFeedbackRating", results = {@Result(location = "parent/feedback/selectChildForFeedback.jsp", name = "success")})
	})
	public String ajaxDoRatingInformation() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoRatingInformation' method");
		}
		try {
			getAnyTitle();
			ajaxCheckStudentspercentagewithFbSettings();
			List<FeedbackGrades> feedbackGradeList=studentManager.getAllByCustId("FeedbackGrades",getUserCustId(),0);
			if (!ObjectFunctions.isNullOrEmpty(feedbackGradeList)) {
				setFeeTypeList(feedbackGradeList);
			}
			setObjectList(studentManager.getAll(FeedbackQuestions.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and roleDescription='School Feedback Questions' group by description order by roleDescription"));
			if(getUser().isParent()){
				if(!ObjectFunctions.isNullOrEmpty(getObjectList())){
					//setTempList(studentManager.getAll(VWFeedbackRatingDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and parentId="+getUser().getId()+" and roleDescription='School Feedback Questions' order by parentId,feedbackGradeId"));
					setTempList(studentManager.getAll(VWFeedbackRatingDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getUser().getSelectedStudentId()+" and roleDescription='School Feedback Questions' order by parentId,feedbackGradeId"));
				}
			}else{
				Student student=(Student) studentManager.get(Student.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and status='Y' and accountId="+getUser().getId());
				if(!ObjectFunctions.isNullOrEmpty(student)){
					setTempList(studentManager.getAll(VWFeedbackRatingDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+student.getId()+" and roleDescription='School Feedback Questions' order by staffId,feedbackGradeId"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	} 
	@Actions( { @Action(value = "ajaxDoAddTeacherRating", results = { @Result(location = "parent/feedback/selectChildForFeedback.jsp", name = "success") }) })
	public String ajaxDoAddTeacherRating() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddTeacherRating' method");
		}
		try {
			getAnyTitle();
			Student studentDetails = null;
			if(getUserAcademicYearId()>0){
				 studentDetails = studentManager.getStudentByAccountId(getUser().getId(),getUserAcademicYearId(),getUserCustId());
				 ajaxCheckStudentspercentagewithFbSettings();
			}
			List<FeedbackGrades> feedbackGradeList=studentManager.getAllByCustId("FeedbackGrades",getUserCustId(),0);
			if (!ObjectFunctions.isNullOrEmpty(feedbackGradeList)) {
				setFeeTypeList(feedbackGradeList);
			}
			if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
				setFeedbackQuestionsList(studentManager.getAll(FeedbackQuestions.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and roleDescription='Teacher Feedback Questions' group by description order by roleDescription"));
				StudyClass studyClass = studentDetails.getClassSection();
				if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
					List<ClassTeacher> classTeacherList = staffManager.getStudyClassSubjectByClassid(studyClass.getId());
					if (!ObjectFunctions.isNullOrEmpty(classTeacherList)) {
						StringBuffer staffIds = new StringBuffer();
						staffIds.append("");
						for (ClassTeacher classTeacher : classTeacherList) {
							if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
								if (!ObjectFunctions.isNullOrEmpty(classTeacher.getStaff())) {
									staffIds.append(classTeacher.getStaff().getId());
									staffIds.append(",");
								}
							}
							classTeacher=null;
						}
						staffIds.append("0");
						List<ViewStaffPersonAccountDetails> subjectStaffsList = adminManager.getViewStaffDetailsListByStaffIds(staffIds.toString(), getUserCustId(),getUserAcademicYearId());
						if (!ObjectFunctions.isNullOrEmpty(subjectStaffsList)) {
						    setStaffsList(subjectStaffsList);
							setParentFeedbackList(studentManager.getAll(VWFeedbackRatingDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+studentDetails.getId()+" and roleDescription='Teacher Feedback Questions' order by staffId,feedbackGradeId"));
						}
					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	} 
	
	public void generateHostelStudentLeaveDetails(ViewStudentPersonAccountDetails studentDetails)
	{
		setApprovedLeavesList(studentManager.getAll(HostelLeave.class, "leaveStatus ='" +Constants.ACTIVE_STATUS+ "' and accountId = " + studentDetails.getAccountId() +" and custId = "+studentDetails.getCustId()+" and academicYearId="+studentDetails.getAcademicYearId()));
		setLeavesList(studentManager.getAll(HostelLeave.class, "leaveStatus ='" +Constants.PENDING_STATUS+ "' and accountId = " + studentDetails.getAccountId() +" and custId = "+studentDetails.getCustId()+" and academicYearId="+studentDetails.getAcademicYearId()));
		setRejectedLeavesList(studentManager.getAll(HostelLeave.class, "leaveStatus ='" +Constants.REJECTED_STATUS+ "' and accountId = " + studentDetails.getAccountId() +" and custId = "+studentDetails.getCustId()+" and academicYearId="+studentDetails.getAcademicYearId()));
		studentDetails=null;
}
	
	
	@Actions({
   		@Action(value = "ajaxDoAddStudentSiblings", results = { @Result(location = "../admin/student/ajaxAddStudentSiblings.jsp", name = "success" )})
   	})
   		public String ajaxDoAddStudentSiblings() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxDoAddStudentSiblings' method");
   		}
   		try {	
   			setTempId(getTempId()); //this is student account Id
   			if(getTempId() > 0)
   			{
   				User newUser = (User) adminManager.get(User.class, " custId="+getUserCustId()+" and id="+getTempId());
                List<StudentSiblings> studentSIblinglist = newUser.getStudentSiblings();
                for(StudentSiblings stu : studentSIblinglist)
                {
                   long accountId = stu.getSiblingAccount().getId();
                   Student studentDetailsList = (Student) adminManager.get(Student.class,"custId="+getUserCustId() +" and accountId="+accountId+" order by id desc limit 1");
                   stu.setAcademicYear(studentDetailsList.getAcademicYear().getAcademicYear());
                   getObjectList().add(stu);
                }
                setTempId(getTempId());
   			}
   		}
   		catch(Exception ex)
   		{
   			log.error("Entering into 'catch block':"+ex.getMessage());
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
	public void addStudentSiblings(User account,Student student){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getKhanVideoPlaylists' method");
		}
		try {
			StudentSiblings studentSiblings = new StudentSiblings();
			studentSiblings.setCreatedById(getUser().getId());
			studentSiblings.setCreatedDate(new Date());
			studentSiblings.setLastAccessDate(new Date());
			studentSiblings.setLastUpdatedDate(new Date());
			studentSiblings.setLastUpdatedById(getUser().getId());
			studentSiblings.setCustId(getUserCustId());
			studentSiblings.setSiblingAccount(student.getAccount());
			studentSiblings.setSiblingStatus("S"); // this status "S" for Student
			studentSiblings.setStatus(Constants.YES_STRING);
			studentSiblings.setStudyClass(student.getClassSection());
			account.addStudentSiblings(studentSiblings);
			adminManager.save(account);
			studentSiblings = null;
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions({
   		@Action(value = "ajaxAddStudentSiblings", results = { @Result(location = "../admin/student/ajaxAddStudentSiblings.jsp", name = "success" )})
   	})
   		public String ajaxAddStudentSiblings() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxAddStudentSiblings' method");
   		}
   		try {
			if(getTempId1() > 0  && !ObjectFunctions.isNullOrEmpty(getParamValue("accountIds"))){
				Student studentObj = (Student) adminManager.get(Student.class, " custId="+getUserCustId()+" and id="+getTempId1());
				if(!ObjectFunctions.isNullOrEmpty(studentObj))
				{
					User account = studentObj.getAccount();
					if(!ObjectFunctions.isNullOrEmpty(account))
					{
						List<Student> studentDetailsList = studentManager.getAll(Student.class," custId="+getUserCustId()+" and accountId in ("+getParamValue("accountIds") +"0) and description is null group by accountId");
						if(!ObjectFunctions.isNullOrEmpty(studentDetailsList))
						{
							for(Student student : studentDetailsList)
							{
								addStudentSiblings(account,student);
								addStudentSiblings(student.getAccount(),studentObj);//studentadding in reverse
								setTempId(account.getId());
								student = null;
							}
							ajaxDoAddStudentSiblings();
							super.addActionMessage("Student sibling added successfully.");
						}
						studentDetailsList=null;
					}
					account=null;
				}
				studentObj = null;
			}
		}
   		catch(Exception ex)
   		{
   			log.error("Entering into 'catch block':"+ex.getMessage());
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
	
	@Actions( {
		@Action(value = "ajaxRemoveStudentSiblings", results = { @Result(location = "../admin/student/ajaxViewStudentSiblingsList.jsp", name = "success") })})
		public String ajaxRemoveStudentSiblings() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveStudentSiblings' method");
			}
			try {
				if(getTempId2() > 0 )
				{
					StudentSiblings studentSiblings = (StudentSiblings)adminManager.get(StudentSiblings.class, getTempId2());
					if(!ObjectFunctions.isNullOrEmpty(studentSiblings))
					{
						//setTempId(studentSiblings.getId());
						adminManager.remove(StudentSiblings.class, studentSiblings.getId());
						studentSiblings = (StudentSiblings)adminManager.get(StudentSiblings.class, " custId="+getUserCustId() + " and accountId="+studentSiblings.getSiblingAccount().getId()+" and siblingAccountId="+ getTempId());
						if(!ObjectFunctions.isNullOrEmpty(studentSiblings))
						{
							adminManager.remove(StudentSiblings.class, studentSiblings.getId());
						}
					}
					super.addActionMessage("Student sibling removed successfully.");
				}
				ajaxDoAddStudentSiblings();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
	}
	@Actions({
   		@Action(value = "ajaxViewStudentCurricularActivities", results = { @Result(location = "../admin/student/ajaxViewStudentCurricularActivities.jsp", name = "success" )})
   	})
   		public String ajaxViewStudentCurricularActivities() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxViewStudentCurricularActivities' method");
   		}
   		try {	
   			List<ActivityType> activityTypeList = studentManager.getAll(ActivityType.class);
			Map<Long,String> activityTypeMap = new HashMap<Long, String>();
			if(getUser().isSchoolAdmin())
			{
				if (!ObjectFunctions.isNullOrEmpty(activityTypeList)) 
				{
					setObjectList(activityTypeList);
					for(ActivityType activityType : activityTypeList)
					{
						activityTypeMap.put(activityType.getId(), activityType.getActivityTypeName());
					}
					User account = (User)studentManager.get(User.class, getTempId());
					List<StudentCurricularActivities> curriculamActivitiesList = account.getStudentCurricularActivities();
					if (!ObjectFunctions.isNullOrEmpty(curriculamActivitiesList)) 
					{
						for(StudentCurricularActivities studentCurricularActivities : curriculamActivitiesList)
						{
							if(!StringFunctions.isNullOrEmpty(activityTypeMap.get(studentCurricularActivities.getActivityTypeId())))
							{
								studentCurricularActivities.setActivityTypeName(activityTypeMap.get(studentCurricularActivities.getActivityTypeId()));
							}
							getTempList().add(studentCurricularActivities);
						}
					}
					
				}
				setTempId1(getTempId1()); //Student Id
				setTempId(getTempId()); // Student Account Id
			}
   		}
   		catch(Exception ex)
   		{
   			log.error("Entering into 'catch block':"+ex.getMessage());
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
 
 @Actions( { @Action(value = "ajaxAddStudentCurricularActivities", results = { @Result(location = "../admin/student/ajaxViewStudentCurricularActivities.jsp", name = "success") }) })
	public String ajaxAddStudentCurricularActivities() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddStudentCurricularActivities' method");
		}
		try {
			StringBuffer pathName = null;
			if(getUser().isSchoolAdmin())
			{
				if(!ObjectFunctions.isNullOrEmpty(getStudentCurricularActivities()))
				{
					Student student = (Student) studentManager.get(Student.class, " id = " + getTempId1());
					if (!ObjectFunctions.isNullOrEmpty(student)) 
					{
						User userUploadObj=student.getAccount();
						if(!ObjectFunctions.isNullOrEmpty(userUploadObj))
						{
							Customer customer = getCustomerByCustId();
							pathName = new StringBuffer(generateStudentCurricularUploadFilepath(customer,userUploadObj.getPerson().getFirstName().replaceAll(" ", "_"),userUploadObj.getId(),student.getId(),getStudentCurricularActivities()));
							log.debug(pathName);
							if(getFileUpload().size()!=0){
								 for(int i=0;i<getFileUpload().size();i++){
									 if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i))){
						    		     File file = getFileUpload().get(i);
						    			 String fileName = getFileUploadFileName().get(i);
							    		 File destDir = new File(getSession().getServletContext().getRealPath(pathName+fileName));
										 FileUtils.copyFile(file, destDir);	 
										 getStudentCurricularActivities().setIsDocsUploaded(Constants.YES_STRING);
						    		 }
									 else
										 getStudentCurricularActivities().setIsDocsUploaded(Constants.NO_STRING);
						    	}
							}
							getStudentCurricularActivities().setCreatedById(getUser().getId());
							getStudentCurricularActivities().setCreatedDate(new Date());
							getStudentCurricularActivities().setLastAccessDate(new Date());
							getStudentCurricularActivities().setLastUpdatedDate(new Date());
							getStudentCurricularActivities().setLastUpdatedById(getUser().getId());
							getStudentCurricularActivities().setCustId(getUserCustId());
							getStudentCurricularActivities().setAcademicYearId(getUserAcademicYearId());
							
							userUploadObj.addStudentCurricularActivities(getStudentCurricularActivities());
							studentManager.save(userUploadObj);
							userUploadObj=null;
							setStudentCurricularActivities(null);
							setTempId1(student.getId());
							setTempId(student.getAccount().getId());
							student=null;
							super.addActionMessage("Student Curricular Activities added successfully.");
						}
					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewStudentCurricularActivities();
		return SUCCESS;
	}
 
 @Actions( { @Action(value = "ajaxDownloadStudentCurricularDocs", results = {}) })
	public String ajaxDownloadStudentCurricularDocs() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadStudentCurricularDocs' method");
		}
		try {
			 if(!ObjectFunctions.isNullOrEmpty(getTempId()) && !ObjectFunctions.isNullOrEmpty(getTempId1())){
				 
				 Customer customer = getCustomerByCustId();
				 Student student = (Student) studentManager.get(Student.class, " accountId = " + getTempId() + " and status = '" + Constants.YES_STRING+"'");
				 StudentCurricularActivities studentCurricularActivities = (StudentCurricularActivities) studentManager.get(StudentCurricularActivities.class, getTempId1());
				 if(!ObjectFunctions.isNullOrEmpty(student) && !ObjectFunctions.isNullOrEmpty(studentCurricularActivities))
				 {
					 String memberName = student.getAccount().getPerson().getFirstName();
					 
					 downloadStudentCurricularDocuments(customer,memberName,student,studentCurricularActivities);
				 }
				 student = null;
				 studentCurricularActivities = null;
			 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( { @Action(value = "ajaxRemovePhysicallyHandicappedDocuments", results = { @Result(location = "../common/ajaxViewStudentPersonalInformation.jsp", name = "success") }) })
	public String ajaxRemovePhysicallyHandicappedDocuments() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemovePhysicallyHandicappedDocuments' method");
		}
		try {
			removeStudentDisabiltyDocuments(0,getTempId());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewMyPersonalInfo();
		return SUCCESS;
	}
	@Actions( { @Action(value = "downloadPhysicallyHandicappedDocuments", results = {}) })
	public String ajaxDownloadPhysicallyHandicappedDocuments() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadPhysicallyHandicappedDocuments' method");
		}
		try {
			Boolean isBoolean=false;
			StringBuffer pathName =null;
			Customer customer = getCustomerByCustId();
			if (getTempId() != 0) {
				setStudent((Student) studentManager.get(Student.class,getTempId()));
			}
			if (getTempId1() != 0) {
				setOnlineApplicationDetails((OnlineApplicationDetails) adminManager.get(OnlineApplicationDetails.class,getTempId1()));
			}
			if (!ObjectFunctions.isNullOrEmpty(getUser().isOnlySchoolAdmin())) 
			{
				if(getTempId1()>0)
					pathName = new StringBuffer(generateStudentDisabilityUploadFilepath(customer,getOnlineApplicationDetails().getFirstName().replaceAll(" ", "_"),getOnlineApplicationDetails().getId(),0));
				if(getTempId()>0)
					pathName = new StringBuffer(generateStudentDisabilityUploadFilepath(customer,getStudent().getAccount().getPerson().getFirstName().replaceAll(" ", "_"),0,getStudent().getAccount().getId()));
			    if(StringFunctions.isNotNullOrEmpty(pathName.toString())){
			    	File destFile = new File(getSession().getServletContext().getRealPath(pathName.toString()));
			    	if(StringFunctions.isNotNullOrEmpty(getFileName())){
			    		getResponse().setContentType("application/octet-stream");
			    		getResponse().addHeader("Content-Disposition", "attachment; filename="+getFileName().replace(' ', '_'));
			    		ServletOutputStream out = getResponse().getOutputStream();
		                DataInputStream in = new DataInputStream(new FileInputStream(destFile+"/"+getFileName()));
		                byte[] bbuf = new byte[1024];
		                int length = 0;
		                while (in != null && (length = in.read(bbuf)) != -1) {
		                    out.write(bbuf, 0, length);
		                }
		                in.close();
		                out.flush();
		                out.close();
			    	}else{
				    	ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
						getResponse().setContentType("application/zip");
						if(StringFunctions.isNullOrEmpty(destFile.toString()))
							getResponse().addHeader("Content-Disposition", "attachment; filename=STUDENT_DOCUMENTS.zip");
						else
							getResponse().addHeader("Content-Disposition", "attachment; filename=STUDENT_DOCUMENTS.zip");
						
							if(isBoolean){
								File destFile1 = new File(getSession().getServletContext().getRealPath(pathName.toString()));
								File[] fList = destFile1.listFiles();
								   for (File file:fList) {
								     if (file.isFile()) {
								       //log.debug("File " + listOfFiles[i].getName());
								     } else if (file.isDirectory()) {
								    	 String[] children = file.list();
								    	 for (int i=0; i<children.length; i++) {
								    	 }
								    	 File directory = null;	
											directory = new File(file.toString());
											StringFunctions.zipFiles(directory,zipOutStream);
								     }
								   }
							}else{
								File directory = null;	
								directory = new File(destFile.toString());
								StringFunctions.zipFiles(directory,zipOutStream);
							}
							zipOutStream = null;
							destFile = null;
			    	}
			    }
			} super.addActionMessage("Documents Download successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	@Actions( { @Action(value = "ajaxRemoveStudentAndStaffImage", results = { @Result(location = "json", name = "success",params = {
			"studentAacademicImage", "studentAacademicImage.*" }) }) })
	public String ajaxRemoveStudentAndStaffImage() throws URTUniversalException {
		if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveStudentAndStaffImage' method");
			}
			try {
				String imagepath = null;
					if(!ObjectFunctions.isNullOrEmpty(getStudentVo())){
						if(!ObjectFunctions.isNullOrEmpty(getStudentVo().getProfileImage())){
							if (!ObjectFunctions.isNullOrEmpty(getStudentVo().getProfileImage().getId()) && getStudentVo().getProfileImage().getId() >0) {
								Student studentObject = (Student) adminManager.get(Student.class,"id="+getStudentVo().getId());
								imagepath = studentObject.getProfileImage().getPath();
								//adminManager.saveOrUpdateObject(studentObject);
								studentObject.setProfileImage(null);
								adminManager.saveOrUpdateObject(studentObject);
								studentManager.sendNotificationForStudentUpdate(studentObject);
								if(ObjectFunctions.isNullOrEmpty(studentObject.getProfileImage()))
								adminManager.remove(UserImage.class, getStudentVo().getProfileImage().getId());
								studentObject = null;
							}
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails())){
						if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getAccountId())){
							if(!ObjectFunctions.isNullOrEmpty(getViewStaffPersonAccountDetails().getImageId())){
								User userObject = (User) adminManager.get(User.class,"id="+getViewStaffPersonAccountDetails().getAccountId());
								imagepath = userObject.getProfileImage().getPath();
								userObject.setProfileImage(null);
								adminManager.saveOrUpdateObject(userObject);
								if(ObjectFunctions.isNullOrEmpty(userObject.getProfileImage()))
								adminManager.remove(UserImage.class,Long.valueOf(getViewStaffPersonAccountDetails().getImageId()));
								adminManager.sendStaffNotificationWhenEditOrAdd(getTempId(),"Staff Profile Update","Staff Profile Update");
								userObject = null;
							}
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())){
						if(!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails().getId())){
						 if(!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails().getProfileImage())){
							OnlineApplicationDetails onlineApplicationObject = (OnlineApplicationDetails) adminManager.get(OnlineApplicationDetails.class,"id="+getOnlineApplicationDetails().getId());
							 if(!ObjectFunctions.isNullOrEmpty(onlineApplicationObject.getProfileImage())){
								 imagepath = onlineApplicationObject.getProfileImage().getPath();
									onlineApplicationObject.setProfileImage(null);
									adminManager.saveOrUpdateObject(onlineApplicationObject);
									if(ObjectFunctions.isNullOrEmpty(onlineApplicationObject.getProfileImage()))
									adminManager.remove(UserImage.class, Long.valueOf(getOnlineApplicationDetails().getProfileImage().getId()));
							 }
							onlineApplicationObject = null;
						 	}
						}					
					}
					if(!ObjectFunctions.isNullOrEmpty(getNewUser())){
						if (!ObjectFunctions.isNullOrEmpty(getNewUser().getId()) && getNewUser().getId() >0) {	
							if (!ObjectFunctions.isNullOrEmpty(getNewUser().getProfileImage().getId()) && getNewUser().getProfileImage().getId() >0) {
								User userObject = (User) adminManager.get(User.class,"id="+getNewUser().getId());		
								imagepath = userObject.getProfileImage().getPath();
								userObject.setProfileImage(null);
								adminManager.saveOrUpdateObject(userObject);
								if(ObjectFunctions.isNullOrEmpty(userObject.getProfileImage()))
								adminManager.remove(UserImage.class, getNewUser().getProfileImage().getId());							
								userObject = null;							
							}
						}
					}
					
					if(!StringFunctions.isNullOrEmpty(imagepath))
					{
						/*S3Wrapper s3Wrapper = new S3Wrapper();
						URL url = new URL(imagepath);
						s3Wrapper.delete(url);*/
					}
					imagepath = null;
				}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
			return null;
	}
	@Actions( {
		@Action(value = "ajaxViewMyAttendanceGraph", results = { @Result(location = "ajaxStudentAttendancePerformance.jsp", name = "success") })
	})
		public String ajaxViewMyAttendanceGraph() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMyAttendanceGraph' method");
		}
		try {
			if(getUser().isParent()){
				
				//StringBuffer query = new StringBuffer("FROM Student stud WHERE stud.custId="+getUserCustId()+" and stud.academicYear="+getUserAcademicYearId()+" and stud.account.parentId=").append(getUser().getId()).append(" and stud.status='Y'");
				StringBuffer query = new StringBuffer("FROM Student stud WHERE stud.custId="+getUserCustId()+" and stud.academicYear="+getUserAcademicYearId()+" and stud.account.id=").append(getUser().getSelectedStudentId()).append(" and stud.status='Y'");
				setStudentsList(studentManager.getAllHqlQuery(query.toString()));
				if(ObjectFunctions.isNotNullOrEmpty(getStudentsList())){
					setStudent((Student)getStudentsList().get(0));
				}
			}
			if(!StringFunctions.isNotNullOrEmpty(getAnyTitle()))
				setAnyTitle(getAnyTitle());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDownloadSelectedNoEmailAndMobileStudentDetails", results = {}) })
	public void ajaxDownloadSelectedNoEmailAndMobileStudentDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadSelectedNoEmailAndMobileStudentDetails' method");
		}
		try {
			if (getUserAcademicYearId() != 0 && StringFunctions.isNotNullOrEmpty(getSelectedId())) {
				String fileName = "Student_Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				PrepareStudentNoEmailAndMobileExcel prepareStudentExcel = new PrepareStudentNoEmailAndMobileExcel();
				List<Object[]> studentDetails = null;
				List<ViewClassSectionDetails> classSections = null;
				List<StudyClass> studyClasses = null;
				classSections = studentManager.getAll(ViewClassSectionDetails.class," academicYearId = " + getUserAcademicYearId()+ " and custId = " + getUserCustId()+ " order by sortingOrder asc");
				List<SchoolCategory> schoolCategory = studentManager.getAll(SchoolCategory.class," custId = " + getUserCustId());
				StringBuffer sheetTitleDesc = new StringBuffer();
				sheetTitleDesc.append("School Name : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("organization"));
				}
				sheetTitleDesc.append(", Academic Year : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("academicYearName"));
				}
				studyClasses = studentManager.getAll(StudyClass.class, "id in " + getSelectedId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
				if (ObjectFunctions.isNotNullOrEmpty(studyClasses)) {
					String[] s = getAnyId().split(",");
					StringBuffer splictAnyId = new StringBuffer();
					splictAnyId.append("(");
					for (int n = 0; n < s.length; n++) {
						splictAnyId.append("(");
						splictAnyId.append(s[n]+" is null or ");
						splictAnyId.append(s[n]+" ='' )");
						splictAnyId.append(" or ");
						}
					String str=splictAnyId.substring(0, splictAnyId.lastIndexOf(" or")) + ")";
					log.debug(splictAnyId.substring(0, splictAnyId.lastIndexOf(" or")) + ")");
					for (StudyClass studyClass : studyClasses) {
							studentDetails = studentManager.getAll(PrepareStudentNoEmailAndMobileExcel.query+ " where classSectionId="+ studyClass.getId()+ "  and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and "+ str +" and description is null order by "+getAnyId());
						if (!ObjectFunctions.isNullOrEmpty(studentDetails)) 
							prepareStudentExcel.createStudentSheet(studyClass.getClassAndSection(), studentDetails,sheetTitleDesc.toString(),getAnyId());
						else{
							prepareStudentExcel.createStudentSheet(studyClass.getClassAndSection(),studentDetails, sheetTitleDesc.toString(),getAnyId());
						}
					}
				} else {
					prepareStudentExcel.createStudentSheet("Student",studentDetails, sheetTitleDesc.toString(),getAnyId());
				}
				prepareStudentExcel.getWb().write(getResponse().getOutputStream());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}

	@Actions({ @Action(value = "ajaxUploadNoEmailAnaMobileStudentData", results = { @Result(location = "../admin/reports/ajaxDownloadNoEmailAndMobileStudentDetails.jsp", name = "success"),
																					@Result(location = "../admin/reports/ajaxDownloadNoEmailAndMobileStudentDetails.jsp", name = "dummyInit")}) })
	public String ajaxUploadNoEmailAnaMobileStudentData() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUploadNoEmailAnaMobileStudentData' method");
		}
		try {
			boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
			
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("ClassSections");
			long academicYearId = 0;
			long custId = 0;
			if (ObjectFunctions.isNullOrEmpty(sheet)) {
				academicYearId = getUserAcademicYearId();
				custId = getUserCustId();
			} else {
				Row row = sheet.getRow(0);
				academicYearId = (long) row.getCell(1).getNumericCellValue();
				row = sheet.getRow(1);
				custId = (long) row.getCell(1).getNumericCellValue();
			}
			if (!"AdmissionCreate".equalsIgnoreCase(getTempString())) {
				if (!isCurrentAcademicYearId(academicYearId)) {
					log.debug("Uploaded wrong file..");
					super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
					ajaxGetStudyClassList();
					return "dummyInit";
				}
			}
			if (custId != 0) {
				Student stud = null;
				User account = null;
				Person person = null;
				String userName = null;
				User objUser= null;
				int loopCount=1;
				int rowCount=2;
				List<StudentExcelRow> studentDetailsList = null;
				boolean isChangedAdmissionNo = true;
				StringBuffer admissionNumberExistMsg = new StringBuffer();
				StringBuffer cellNumber = new StringBuffer();
				Customer customer = (Customer) studentManager.get(Customer.class,"id=" + custId);
				Customer masterCustomer = studentManager.getMasterCustomerById();
				SMSServiceProviders smsServiceProvider=(SMSServiceProviders)  adminManager.getSMSServiceProviderByCustId(masterCustomer.getSmsServiceProviderId());
				for (int sheetNo = 0; sheetNo < (workbook.getNumberOfSheets()); sheetNo++) {
					sheet = workbook.getSheetAt(sheetNo);
					SheetParser parser = new SheetParser();
					try {
						studentDetailsList = parser.createEntity(sheet, sheet.getSheetName(), StudentExcelRow.class);
						long countNo = 0;
						if (ObjectFunctions.isNotNullOrEmpty(studentDetailsList)) {
							for (StudentExcelRow studentDetails : studentDetailsList) {
								if (!ObjectFunctions.isNullOrEmpty(studentDetails) && StringFunctions.isNotNullOrEmpty(studentDetails.getAdmissionNumber())) {
									log.debug("***************** " + studentDetails.getFirstName() + "***************** " );
									if(loopCount > 4) break;
									if(ObjectFunctions.isNullOrEmpty(sheet.getRow(rowCount++))){
										loopCount++;
										continue;
									}
									loopCount=1;
									isChangedAdmissionNo = true;
									String admisionNo = null;
									String updatedAdmissionStatus = null;
									if (!StringFunctions.isNullOrEmpty(studentDetails.getStudentId())) {
										log.debug("student id : "+ studentDetails.getStudentId());
										stud = (Student) studentManager.get(Student.class, Long.valueOf(studentDetails.getStudentId()));
										account = (User) studentManager.get(User.class, "custId="+ custId+ " and id="+ stud.getAccount().getId());
										person = (Person) studentManager.get(Person.class, account.getPerson().getId());
										admisionNo=account.getAdmissionNumber().trim();
										stud.setLastUpdatedById(getUser().getId());
										account.setLastUpdatedById(getUser().getId());
										stud.setLastUpdatedDate(new Date());
										if (!(studentDetails.getAdmissionNumber().trim().equalsIgnoreCase(admisionNo))) {
											updatedAdmissionStatus ="Yes";
											userName = StringFunctions.prepareUserName(customer.getCustomerShortName(), studentDetails.getAdmissionNumber(), "");
											if (!ObjectFunctions.isNullOrEmpty(objUser)) {
												isChangedAdmissionNo = false;
												admissionNumberExistMsg.append(studentDetails.getAdmissionNumber()+ ", ");
												if(StringFunctions.isNullOrEmpty(studentDetails.getStudentId()))
													continue;
											}
											objUser = null;
										} else {
											isChangedAdmissionNo = false;
										}
											if(!ObjectFunctions.isNullOrEmpty(person)){
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getFirstName()))
													person.setFirstName(studentDetails.getFirstName());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getLastName()))
													person.setLastName(studentDetails.getLastName());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getFatherName()))
													person.setFatherName(studentDetails.getFatherName());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getMotherName()))
													person.setMotherName(studentDetails.getMotherName());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getMobileNumber()))
													person.setMobileNumber(studentDetails.getMobileNumber());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getSecondaryMobileNumber()))
													person.setSecondaryMobileNumber(studentDetails.getSecondaryMobileNumber());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getStudentMobile()))
													person.setStudentMobile(studentDetails.getStudentMobile());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getParentEmail()))
													person.setParentEmail(studentDetails.getParentEmail());
												if(!ObjectFunctions.isNullOrEmpty(studentDetails.getStudentEmail()))
													person.setStudentEmail(studentDetails.getStudentEmail());
											}
											log.debug("First Name :"+ studentDetails.getFirstName() +"Last Name :"+ studentDetails.getFirstName() +"Mobile Number :"+ studentDetails.getMobileNumber()+"Phone NUmber :"+ studentDetails.getSecondaryMobileNumber()+"Student Mobile :"+ studentDetails.getStudentMobile()+"Parent Email :"+ studentDetails.getParentEmail()+"Student Email :"+ studentDetails.getStudentEmail()+"Last Name :"+ studentDetails.getLastName()+"First Name :"+ studentDetails.getAdmissionNumber() +"*******" );
											if(!ObjectFunctions.isNullOrEmpty(account)){
												account.setAdmissionNumber(studentDetails.getAdmissionNumber());
												account.setPerson(person);
											}
										if(account.getId() == 0)
											account = (User) studentManager.save(account);
										stud.setAccount(account);
										
										student.setStatus(Constants.YES_STRING);
									} 
									Student studObject  = (Student) studentManager.save(stud);
									if(!ObjectFunctions.isNullOrEmpty(studentDetails.getMobileNumber()) || !ObjectFunctions.isNullOrEmpty(studentDetails.getSecondaryMobileNumber()) || !ObjectFunctions.isNullOrEmpty(studentDetails.getStudentMobile()) ){
										int sentSmsCount=adminManager.getTotalSmsCount(custId,stud.getAcademicYear().getId());
										int allottedSmsCount = (int) stud.getAcademicYear().getAllotedsms()+(int) stud.getAcademicYear().getPaidSms();
										String response = studentManager.createParentLoginAccount(customer,stud.getAcademicYear(),stud,false,masterCustomer,getUser());

									}
								} else {
									log.debug("**** Admission Number missed *******");
									cellNumber.append(countNo+",");
								}
								countNo++;
								stud = null;
								account = null;
								person = null;
								rowCount=2;
							}
						}else {
							super.addActionError("Few student(s) data not valid. Please verify the data.");
						}
					} catch (Exception ex) {
						ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
						continue;
					}
				}
				if("Edit".equalsIgnoreCase(getTempString())){
					super.addActionMessage("Student details updated successfully.");
				}
				getSession().removeAttribute("GetAllStudyClasses");
				studentDetailsList = null;
				customer = null;
				admissionNumberExistMsg = null;
			} else {
				log.debug("Uploaded wrong file..");
				super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			RayGunException raygex = new RayGunException();
			raygex.sendRayGunException(ex);
			raygex = null;
		}
		checkStudyClassHavingStudentsOrNot();
		log.debug("******************** end method *******************");
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxShowStudentProfileDetails", results = { @Result(location = "../admin/student/ajaxPopUpStudentProfileDetails.jsp", name = "success") }) })
	    public String ajaxShowStudentProfileDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxShowStudentProfileDetails' method");
		}
		try {
			    ViewStudentPersonAccountDetails studentProfileDetails = (ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class,"studentId="+getTempId()+" and classSectionId="+getTempId2()+" and custId="+getUserCustId());
			    if(!ObjectFunctions.isNullOrEmpty(studentProfileDetails))
			    	setViewStudentPersonAccountDetails(studentProfileDetails);
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
    }
	@Actions( { @Action(value = "ajaxPermissionsDetails", results = { @Result(location = "permissions/ajaxPopupPermissionsDetails.jsp", name = "success") }),
		@Action(value = "ajaxDoAddParentPermissionsRequest", results = { @Result(location = "permissions/ajaxDoAddPermissionRequest.jsp", name = "success") })
		})
    public String ajaxPermissionsDetails() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxPermissionsDetails' method");
	}
	try {
		setWeekDayList(adminManager.getAll(WeekDays.class, "id not in (1)"));
		if (getUser().isParent()) 
			if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
				List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(studList))
					setViewStudentPersonAccountDetailsList(studList);
			}
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	
	 @Actions( { @Action(value = "ajaxViewParentPermissions", results = { @Result(location = "permissions/ajaxViewParentPermissionRequest.jsp", name = "success") }),
		 		@Action(value = "ajaxAdminViewParentPermissions", results = { @Result(location = "permissions/ajaxViewAllPermissionDetails.jsp", name = "success") }),
		 		@Action(value = "ajaxViewAllPermissions", results = { @Result(location = "permissions/ajaxViewAllPermissionRequests.jsp", name = "success") })
	 })
	    public String ajaxViewParentPermissions() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxViewParentPermissions' method");
		}
		try {
			setIsClassTeacher(getBankName()); //bankName is check the classteacher
			setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.YYYY_MM_DD_PATTERN));// check the today date
			List<ViewPermissionSettings> pendingList =null;
			List<ViewPermissionSettings> acceptList =null;
			List<ViewPermissionSettings> rejectedList =null;
			StringBuffer query = new StringBuffer();
			query.append("custId=").append(getUserCustId());
			query.append(" and academicYearId=").append(getUserAcademicYearId());
			if(getUser().isParent()){
				query.append(" and createdById=").append(getUser().getId());
				pendingList = studentManager.getAll(ViewPermissionSettings.class, query.toString()+" and status='"+Constants.PENDING_STATUS+"' group by permissionsId order by startDate");
				acceptList = studentManager.getAll(ViewPermissionSettings.class, query.toString()+" and status='"+Constants.ACTIVE_STATUS+"' group by permissionsId");
				rejectedList = studentManager.getAll(ViewPermissionSettings.class, query.toString()+" and status='"+Constants.REJECTED_STATUS+"' group by permissionsId");
			}else{
				pendingList = studentManager.getAll(ViewPermissionSettings.class, query.toString()+" and status='"+Constants.PENDING_STATUS+"' group by permissionsId order by startDate");
				acceptList = studentManager.getAll(ViewPermissionSettings.class, query.toString()+" and status='"+Constants.ACTIVE_STATUS+"' group by permissionsId");
				rejectedList = studentManager.getAll(ViewPermissionSettings.class, query.toString()+" and status='"+Constants.REJECTED_STATUS+"' group by permissionsId");
			}
			query=null;
			if(ObjectFunctions.isNotNullOrEmpty(pendingList)){
				for(ViewPermissionSettings permissionSettings: pendingList){
					if(!ObjectFunctions.isNullOrEmpty(permissionSettings)){
						permissionSettings.setPermissionTimesList(studentManager.getAll(ViewPermissionSettings.class," status='"+Constants.PENDING_STATUS+"' and permissionsId="+permissionSettings.getPermissionsId()));
					}
					getTempList().add(permissionSettings);
				}
			}
			if(ObjectFunctions.isNotNullOrEmpty(acceptList)){
				for(ViewPermissionSettings acceptedSettings: acceptList){
					if(!ObjectFunctions.isNullOrEmpty(acceptedSettings)){
						acceptedSettings.setPermissionTimesList(studentManager.getAll(ViewPermissionSettings.class," status='"+Constants.ACTIVE_STATUS+"' and permissionsId="+acceptedSettings.getPermissionsId()));
					}
					getTempList1().add(acceptedSettings);
				}
			}
			if(ObjectFunctions.isNotNullOrEmpty(rejectedList)){
				for(ViewPermissionSettings rejectedSettings: rejectedList){
					if(!ObjectFunctions.isNullOrEmpty(rejectedSettings)){
						rejectedSettings.setPermissionTimesList(studentManager.getAll(ViewPermissionSettings.class," status='"+Constants.REJECTED_STATUS+"' and permissionsId="+rejectedSettings.getPermissionsId()));
					}
					getTempList2().add(rejectedSettings);
				}
			}
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 
	 @Actions( { @Action(value = "ajaxAddStudentPermissions", results = { @Result(location = "../admin/common/ajaxViewStudyClassList.jsp", name = "success") }),
		         @Action(value = "ajaxAddStudentPermissionsRequest", results = { @Result(location = "permissions/ajaxViewParentPermissionRequest.jsp", name = "success") })
	 })
    public String ajaxAddStudentPermissions() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxAddStudentPermissions' method");
	}
	try {
		if (!StringFunctions.isNullOrEmpty(getTempString())) {
			log.debug("getAnyTitle() : " + getTempString()); //below classId used to get the holidays for class wise. if school have class wise holidays in school settings tab
			addActionMessages(studentManager.addOrUpdateStudentPermissions(getTempString(),getUser().getId()));
			if(getUser().isParent())
				ajaxViewParentPermissions();
			else
				ajaxGetStudyClassList();
		}
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxApproveOrRejectPermisssion", results = { @Result(location = "permissions/ajaxViewAllPermissionDetails.jsp", name = "success") }) })
	    public String ajaxApproveOrRejectPermisssion() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxApproveOrRejectPermisssion' method");
		}
		try {
			if(getTempId2()>0){
			Permissions permissions=(Permissions)studentManager.get(Permissions.class,getTempId2());
			if(!ObjectFunctions.isNullOrEmpty(permissions)){
				permissions.setApprovalsComment(getPlTitle());
				permissions.setLastUpdatedById(getUser().getId());
				permissions.setLastUpdatedDate(new Date());
				if(getAnyTitle().equalsIgnoreCase("reject")){
					permissions.setStatus(Constants.REJECTED_STATUS);
					super.addActionMessage("Permission rejected.");
				}else if(getAnyTitle().equalsIgnoreCase("approve")){
					permissions.setStatus(Constants.ACTIVE_STATUS);
					super.addActionMessage("Permission approved successfully");
				}
				permissions = (Permissions)studentManager.saveOrUpdateObject(permissions);
			}
			ajaxViewParentPermissions();
			Customer customer=getCustomerByCustId();
			Student student = (Student)studentManager.get(Student.class,"id="+permissions.getStudentId()+" and academicYearId="+getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(student) && !StringFunctions.isNullOrEmpty(student.getAccount().getPerson().getParentEmail()) && customer.isCheckEmailService()){
				String[] emailAddresses = new String[1];
				emailAddresses[0] = student.getAccount().getPerson().getParentEmail();
				List<ViewPermissionSettings> permissionsList = studentManager.getAll(ViewPermissionSettings.class, "custId="+customer.getId()+" and academicYearId="+getUserAcademicYearId()+" and permissionsId="+permissions.getId());
				MailUtil mailUtil=new MailUtil(emailAddresses,"Regd : Your child  permission Request",customer.getId(),customer.getSender(),"Administrator",adminManager.getContactFromEmail(customer));
				mailUtil.setContactEmail(customer.getContactEmail());
				mailUtil.setContactPasword(customer.getContactPassword());
				mailUtil.sendMailToParentChildrenPermissions(permissionsList,student,getAnyTitle(),adminManager.getContactFromEmail(customer));
				mailUtil=null;
				emailAddresses=null;
			}
		  }
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 @Actions({
			@Action(value = "ajaxRemoveParentPermissions", results = { @Result(location = "permissions/ajaxViewParentPermissionDetails.jsp", name = "success") }) })
			public String ajaxDeleteStaffPermissionRequests() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveParentPermissions' method");
			}
			try {
				if(getTempId1() > 0) {
					studentManager.removePermissionTimingsByPermissionId(getTempId1());
					studentManager.remove(Permissions.class, getTempId1());	
					super.addActionMessage("Successfully deleted permissions.");
					ajaxViewParentPermissions();
				}
			} catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 @Actions( { @Action(value = "ajaxAddressToCommunication", results = { @Result(location = "parent/ajaxAddressToCommunicationDetails.jsp", name = "success") }) })
	 public String ajaxAddressToCommunication() throws URTUniversalException {
	     if (log.isDebugEnabled()) {
	 	log.debug("Entering 'ajaxAddressToCommunication' method");
	     }
	     try {
	    	 if(getUser().isSchoolStudent()){
	 			StringBuffer query = new StringBuffer();
	 			//query.append("FROM Student stud WHERE stud.custId="+getUserCustId()+" and stud.academicYear="+getUserAcademicYearId()+" and stud.account.parentId=").append(getUser().getId()).append(" and stud.status='Y'");
	 			query.append("FROM Student stud WHERE stud.custId="+getUserCustId()+" and stud.academicYear="+getUserAcademicYearId()+" and stud.account.id=").append(getUser().getSelectedStudentId()).append(" and stud.status='Y'");
	 			setObjectList(studentManager.getAllHqlQuery(query.toString()));
	 			if(ObjectFunctions.isNotNullOrEmpty(getObjectList())){
	 				setTempId(((Student)getObjectList().get(0)).getId());
	 				ajaxCommunicationDetails();
	 			}
	 			query = null;
	 		}
	     }
	     catch (Exception ex) {
	  	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	     }
	 return SUCCESS;
	 }
	 @Actions( { @Action(value = "ajaxCommunicationDetails", results = { @Result(location = "parent/ajaxCommunicationDetails.jsp", name = "success") })})
	 public String ajaxCommunicationDetails() throws URTUniversalException {
	 	if (log.isDebugEnabled()) {
	 		log.debug("Entering 'ajaxCommunicationDetails' method");
	 	}
	 	try {
	 		Student student = (Student)studentManager.get(Student.class,"id="+getTempId());
	 		if(!ObjectFunctions.isNullOrEmpty(student))
	 			setStudent(student);
	 		setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
	 	} catch (Exception ex) {
	 		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 	}
	 	return SUCCESS;
	 }
	 @Actions( { @Action(value = "ajaxSaveCommunicationDetails", results = { @Result(location = "parent/ajaxAddressToCommunicationDetails.jsp", name = "success") })})
	 public String ajaxSaveCommunicationDetails() throws URTUniversalException {
	 	if (log.isDebugEnabled()) {
	 		log.debug("Entering 'ajaxSaveCommunicationDetails' method");
	 	}
	 	try {
	 		if(getStudent().getId()!=0){
	 			Student lobjStudent = (Student) studentManager.get(Student.class,getStudent().getId());
	 			Person lobjPerson = lobjStudent.getAccount().getPerson();
	 			if(!ObjectFunctions.isNullOrEmpty(lobjStudent)){
	 				//TemporaryMobileNumbers tempMobileNum = null;
	 				String addressType = getStudent().getAccount().getPerson().getAddressType();
	 				lobjPerson.setMobileNumber(getStudent().getAccount().getPerson().getMobileNumber());
	 				lobjPerson.setSecondaryMobileNumber(getStudent().getAccount().getPerson().getSecondaryMobileNumber());
	 				lobjPerson.setParentEmail(getStudent().getAccount().getPerson().getParentEmail());
	 				lobjPerson.setAnotherMobileNumber(getStudent().getAccount().getPerson().getAnotherMobileNumber());
	 				lobjPerson.setAnotherSecondaryMobileNumber(getStudent().getAccount().getPerson().getAnotherSecondaryMobileNumber());
	 				lobjPerson.setAnotherParentEmail(getStudent().getAccount().getPerson().getAnotherParentEmail());
	 				lobjPerson.setAddressType(addressType);
	 				super.addActionMessage("Communication details created successfully.");
	 			}
	 			ajaxAddressToCommunication();
	 		}
	 	} catch (Exception ex) {
	 		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 	}
	 	return SUCCESS;
	 }
	 /* @Description 2nd Nov 2015 RaviTeja: added onloineappoinment */
	 @Actions( { @Action(value = "ajaxViewOnlineAppointment", results = { @Result(location = "../admin/appointments/ajaxOnileAppointmentDetails.jsp", name = "success") }) })
		public String ajaxViewOnlineAppointment() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewOnlineAppointment' method");
			}
			try {
				List<ViewAppointmetDetails> pendingList =null;
				List<ViewAppointmetDetails> acceptList =null;
				List<ViewAppointmetDetails> rejectedList =null;
				List<ViewStaffAppointmetDetails> spendingList =null;
				List<ViewStaffAppointmetDetails> sacceptList =null;
				List<ViewStaffAppointmetDetails> srejectedList =null;
				StringBuffer query = new StringBuffer();
				query.append("custId=").append(getUserCustId());
				
				if(getUser().isParent()){
					Long studentAccountId =(Long) getSession().getAttribute("selectedStudentId");
					query.append(" and academicYearId=").append(getUserAcademicYearId());
					setTempList(pendingList = studentManager.getAll(ViewAppointmetDetails.class, query.toString()+" and status='"+Constants.PENDING_STATUS+"' and requestAccountId="+getUser().getId()+" and studentAccountId="+studentAccountId+" order by scheduleDate desc"));
					setTempList1(acceptList = studentManager.getAll(ViewAppointmetDetails.class, query.toString()+" and status='"+Constants.ACTIVE_STATUS+"' and requestAccountId="+getUser().getId()+" and studentAccountId="+studentAccountId+" order by scheduleDate desc"));
					setTempList2(rejectedList = studentManager.getAll(ViewAppointmetDetails.class, query.toString()+" and status='"+Constants.REJECTED_STATUS+"' and requestAccountId="+getUser().getId()+" and studentAccountId="+studentAccountId+"  order by scheduleDate desc"));
					studentAccountId = null;
				}else{
					query.append(" and academicYearId<=").append(getUserAcademicYearId());
					setTempList(spendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and status='"+Constants.PENDING_STATUS+"' and requestAccountId="+getUser().getId()+" order by scheduleDate desc"));
					setTempList1(sacceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and status='"+Constants.ACTIVE_STATUS+"' and requestAccountId="+getUser().getId()+" order by scheduleDate desc"));
					setTempList2(srejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and status='"+Constants.REJECTED_STATUS+"' and requestAccountId="+getUser().getId()+" order by scheduleDate desc"));
				}
				query=null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 /* @Description 2nd Nov 2015 RaviTeja: added onloineappoinment */
		@Actions( { @Action(value = "ajaxOnlineAppointment", results = { @Result(location = "../admin/appointments/ajaxCreateOnlineAppoinment.jsp", name = "success") }) })
		public String ajaxOnlineAppointment() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxOnlineAppointment' method");
			}
			try {
				Appointment appointment =null;
				List<ViewUserRoles> rolesList= null;
				List<String> techerIds= null;
				List<ViewUserRoles> teacherList=null;
				if(!ObjectFunctions.isNullOrEmpty(getUser().getUserRole())){
					if(getUser().getUserRole().equalsIgnoreCase("ROLE_TEACHER") || getUser().getUserRole().equalsIgnoreCase("ROLE_ADMIN_COORDINATOR") || getUser().getUserRole().equalsIgnoreCase("ROLE_HOD")){
						rolesList= staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_HOD' or roleName='ROLE_ADMIN_COORDINATOR' or roleName='ROLE_PRINCIPAL' or roleName='ROLE_VICEPRINCIPAL' or roleName='ROLE_ADMIN' or roleName='ROLE_ADMINOFFICER') and (firstName!='' AND firstName is not null)");
					}else{
						rolesList= staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountId !="+getUser().getId()+" and accountExpired='N' and (roleName='ROLE_HOD'  or roleName='ROLE_ADMIN_COORDINATOR' or roleName='ROLE_PRINCIPAL' or roleName='ROLE_VICEPRINCIPAL' or roleName='ROLE_ADMIN' or roleName='ROLE_ADMINOFFICER') and (firstName!='' AND firstName is not null)");
					}
				}else{
					rolesList= staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_HOD' or roleName='ROLE_ADMIN_COORDINATOR' or roleName='ROLE_PRINCIPAL' or roleName='ROLE_VICEPRINCIPAL' or roleName='ROLE_ADMIN' or roleName='ROLE_ADMINOFFICER') and (firstName!='' AND firstName is not null)");
					//techerIds =  staffManager.getAll("select accountId from staff where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id in (select teacherId from classTeacher where studyClassId in (select classSectionId from student where accountId in (select id from Account where parentId="+getUser().getId()+" and custId="+getUserCustId()+") ))");
					techerIds =  staffManager.getAll("select accountId from staff where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id in (select teacherId from classTeacher where studyClassId in (select classSectionId from student where accountId in (select id from Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE sp.parentAccountId="+getUser().getId()+" and custId="+getUserCustId()+") ))");
					if(!ObjectFunctions.isNullOrEmpty(techerIds))
						teacherList = staffManager.getAll(ViewUserRoles.class,"custId="+getUserCustId()+" and accountId in ("+StringUtil.convertListToString(techerIds)+")");
				}
				getCustomerNamesSet().addAll(rolesList);
				if(!ObjectFunctions.isNullOrEmpty(teacherList))
					getCustomerNamesSet().addAll(teacherList);
				if(getTempId() > 0){
					appointment =(Appointment) adminManager.get(Appointment.class,"id="+getTempId());
					setAppointment(appointment);
					setAnyTitle(appointment.getAppointmentRoleId()+","+appointment.getReceivedAccountId());
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		 /* @Description 3nd Nov 2015 RaviTeja: add and Edit appoinment */
		@Actions( { @Action(value = "ajaxDoAddOnlineAppoinments", results = { @Result(location = "../admin/appointments/ajaxOnileAppointmentDetails.jsp", name = "success") }) })
		public String ajaxDoAddOnlineAppoinments() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddOnlineAppoinments' method");
			}
			try {
				Appointment appointment = null;
				if(!ObjectFunctions.isNullOrEmpty(getAppointment())){
					if(getTempId() != 0){
						appointment =(Appointment) adminManager.get(Appointment.class,"id="+getTempId());
						appointment.setLastAccessDate(new Date());
						appointment.setLastUpdatedById(getUser().getId());
						appointment.setLastUpdatedDate(new Date());
						super.addActionMessage("Appointment updated successfully.");
					}else{
						appointment = new Appointment();
						appointment.setCreatedById(getUser().getId());
						appointment.setCreatedDate(new Date());
						super.addActionMessage("Appointment created successfully.");
						
						if(getUser().isParent()){
							appointment.setStudentAccountId(getAppointment().getStudentAccountId());
						}
					}
					String roleAccountId[]=getAnyTitle().split(",");
					if(!ObjectFunctions.isNullOrEmpty(roleAccountId)){
						appointment.setAppointmentRoleId(Long.parseLong(roleAccountId[0]));
						appointment.setReceivedAccountId(Long.parseLong(roleAccountId[1]));
					}
					appointment.setCustId(getUserCustId());
					appointment.setSubject(getAppointment().getSubject());
					appointment.setEmail(getAppointment().getEmail());
					appointment.setAcademicYearId(getUserAcademicYearId());
					appointment.setMobileNumber(getAppointment().getMobileNumber());
					appointment.setScheduleDate(getAppointment().getScheduleDate());
					appointment.setScheduleTime(getAppointment().getScheduleTime());
					appointment.setRequestAccountId(getUser().getId());
					appointment.setDescription(getAppointment().getDescription());
					adminManager.saveOrUpdateObject(appointment);
					setTempId(appointment.getId());
				}
				if(!ObjectFunctions.isNullOrEmpty(getUser().getUserRole())){
					if(getUser().getUserRole().equalsIgnoreCase("ROLE_TEACHER")){
						setPlTitle("S");
					}
				}else{
						setPlTitle("P");
					}
				appointment =null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			ajaxViewOnlineAppointment();
			ajaxSendMessageForAPpointmentApproveOrReject();
			return SUCCESS;
		}
		/* @Description 3nd Nov 2015 RaviTeja: view appoinment for apply staff */
		 @Actions( { @Action(value = "ajaxApproveOrRejectOnlineAppointment", results = { @Result(location = "../admin/appointments/ajaxApproveOrRejectAppointmentDetails.jsp", name = "success") }),
			 		@Action(value = "ajaxViewStaffOnlineAppointment", results = { @Result(location = "../admin/appointments/ajaxViewStaffOnlineAppointment.jsp", name = "success") }),
			 		@Action(value = "ajaxViewParentOnlineAppointment", results = { @Result(location = "../admin/appointments/ajaxViewParentsOnlineAppointment.jsp", name = "success") })
		 			})	
		public String ajaxApproveOrRejectOnlineAppointment() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxApproveOrRejectOnlineAppointment' method");
				}
				try {
					if(!ObjectFunctions.isNullOrEmpty(getAnyTitle())) {
						if(getParamValue("anyTitle").equalsIgnoreCase("appointments")){
							setPlTitle("A"); // i will pass A parameter not display the Header click on the Parent Appointments View 
						}
					}
					List<ViewStaffAppointmetDetails> pendingList =null;
					List<ViewStaffAppointmetDetails> acceptList =null;
					List<ViewStaffAppointmetDetails> rejectedList =null;
					StringBuffer query = new StringBuffer();
					query.append("custId=").append(getUserCustId());
					query.append(" and academicYearId=").append(getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(getTempString())){
						if(getParamValue("tempString").equalsIgnoreCase("staff")){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and (requestRoleName='ROLE_HOD' or requestRoleName='ROLE_PRINCIPAL' or requestRoleName='ROLE_VICEPRINCIPAL' or requestRoleName='ROLE_TEACHER' or requestRoleName='ROLE_ADMIN_COORDINATOR' or requestRoleName='ROLE_ASST_TEACHER') and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and  (requestRoleName='ROLE_HOD' or requestRoleName='ROLE_PRINCIPAL' or requestRoleName='ROLE_VICEPRINCIPAL' or requestRoleName='ROLE_TEACHER' or requestRoleName='ROLE_ADMIN_COORDINATOR' or requestRoleName='ROLE_ASST_TEACHER') and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and  (requestRoleName='ROLE_HOD' or requestRoleName='ROLE_PRINCIPAL' or requestRoleName='ROLE_VICEPRINCIPAL' or requestRoleName='ROLE_TEACHER' or requestRoleName='ROLE_ADMIN_COORDINATOR' or requestRoleName='ROLE_ASST_TEACHER') and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}else if(getParamValue("tempString").equalsIgnoreCase("parents")){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and requestRoleName='ROLE_PARENT' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and requestRoleName='ROLE_PARENT' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and requestRoleName='ROLE_PARENT' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}
					}else{
						if("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole())){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMIN+"' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMIN+"' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMIN+"' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}else if("ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole())){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMINOFFICER+"' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMINOFFICER+"' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMINOFFICER+"' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}
						 if("ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole())){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_PRINCIPAL+"' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_PRINCIPAL+"' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_PRINCIPAL+"' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}else if("ROLE_VICEPRINCIPAL".equalsIgnoreCase(getUser().getUserRole())){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_VICEPRINCIPAL+"' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_VICEPRINCIPAL+"' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_VICEPRINCIPAL+"' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}else if("ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole())){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_TEACHER+"' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_TEACHER+"' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_TEACHER+"' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}else if("ROLE_HOD".equalsIgnoreCase(getUser().getUserRole())){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_HOD+"' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_HOD+"' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_HOD+"' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}else if("ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole())){
							pendingList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMIN_COORDINATOR+"' and status='"+Constants.PENDING_STATUS+"' order by scheduleDate desc");
							acceptList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMIN_COORDINATOR+"' and status='"+Constants.ACTIVE_STATUS+"' order by scheduleDate desc");
							rejectedList = studentManager.getAll(ViewStaffAppointmetDetails.class, query.toString()+" and receivedRoleName='"+Constants.SCHOOL_ADMIN_COORDINATOR+"' and status='"+Constants.REJECTED_STATUS+"' order by scheduleDate desc");
						}
					}
					query=null;
					if(ObjectFunctions.isNotNullOrEmpty(pendingList))
						setTempList(pendingList);
					if(ObjectFunctions.isNotNullOrEmpty(acceptList))
						setTempList1(acceptList);
					if(ObjectFunctions.isNotNullOrEmpty(rejectedList))
						setTempList2(rejectedList);
				} catch (Exception ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
			}
		 /* @Description 4th Nov 2015 RaviTeja: approve or reject appoinment */
		@Actions( { @Action(value = "ajaxApproveOrRejectAppointment", results = { @Result(location = "../admin/appointments/ajaxViewApproveOrRejectAppointmentDetails.jsp", name = "success") }) })
		public String ajaxApproveOrRejectAppointment() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxApproveOrRejectAppointment' method");
			}
			try{
				Appointment appointment=(Appointment)staffManager.get(Appointment.class, Long.valueOf(getAnyId()));
				if(!ObjectFunctions.isNullOrEmpty(appointment)){
					if(getAnyTitle().equalsIgnoreCase("reject")){
						appointment.setStatus(Constants.REJECTED_STATUS);
						super.addActionMessage("Appointment rejected successfully");
						setPlTitle("R");
					}else if(getAnyTitle().equalsIgnoreCase("approve")){
						appointment.setStatus(Constants.ACTIVE_STATUS);
						super.addActionMessage("Appointment approve successfully");
						setPlTitle("A");
					}
					appointment.setReceivedAccountId(getUser().getId());
					appointment.setApporveDescription(getAppointment().getApporveDescription());
					appointment.setLastUpdatedById(getUser().getId());
					appointment.setLastUpdatedDate(new Date());
					staffManager.save(appointment);
					setTempId(Long.valueOf(getAnyId()));
					ajaxSendMessageForAPpointmentApproveOrReject();
					log.debug(getTempString());
					
					if("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_VICEPRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole())|| "ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_HOD".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_DIRECTOR".equalsIgnoreCase(getUser().getUserRole())){
						setTempString(null);
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			ajaxApproveOrRejectOnlineAppointment();
			return SUCCESS;
		}
		/* @Description 4th Nov 2015  RaviTejaGoud : Send Mail and SMS to Appointment*/  
		@Actions( { @Action(value = "ajaxSendMessageForAPpointmentApproveOrReject", results = { @Result(location = "../admin/appointments/ajaxViewApproveOrRejectAppointmentDetails.jsp", name = "success") }) })
		public String ajaxSendMessageForAPpointmentApproveOrReject() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSendMessageForAPpointmentApproveOrReject' method");
			}
			try {
				ViewAppointmetDetails vwAppointmetDetails = null;
				Customer customer = (Customer)adminManager.get(Customer.class, getUserCustId());
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					if(!ObjectFunctions.isNullOrEmpty(getPlTitle()))
						if(getPlTitle().equalsIgnoreCase("S")){ // S define as apply for staff appoinment
							vwAppointmetDetails =(ViewAppointmetDetails) adminManager.get(ViewAppointmetDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and requestAccountId="+getUser().getId()+" and appointmentId="+getTempId());
						}
					else{
						if("ROLE_HOD".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_VICEPRINCIPAL".equalsIgnoreCase(getUser().getUserRole())) {
							vwAppointmetDetails =(ViewAppointmetDetails) adminManager.get(ViewAppointmetDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and receivedAccountId="+getUser().getId()+" and appointmentId="+getTempId());
						}else
							vwAppointmetDetails =(ViewAppointmetDetails) adminManager.get(ViewAppointmetDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and requestAccountId="+getUser().getId()+" and appointmentId="+getTempId());
					}
				if (!ObjectFunctions.isNullOrEmpty(vwAppointmetDetails)) {
					if (customer.isCheckMobileService()) {
						SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
						Set<String> mobileNumbersSet = new HashSet<String>();
						StringBuffer smsContent;
								Messages objMsg = new Messages();
								objMsg.setCreatedById(getUser().getId());
								objMsg.setCreatedDate(new Date());
								objMsg.setLastAccessDate(new Date());
								objMsg.setLastUpdatedDate(new Date());
								objMsg.setStatus("M");
								objMsg.setSenderName(getUser().getUserRoleDescription());
								smsContent= new StringBuffer();
								if(!ObjectFunctions.isNullOrEmpty(getPlTitle())){
									if(getPlTitle().equalsIgnoreCase("P")){
										mobileNumbersSet.add(vwAppointmetDetails.getReciveMobileNumber());
										smsContent.append("Dear "+vwAppointmetDetails.getReceiveFullName()+",");
										smsContent.append("I, "+vwAppointmetDetails.getRequestFullName()+" parent of "+vwAppointmetDetails.getStudentFullName()+" of < "+vwAppointmetDetails.getStudClassAndSection()+" > request your appointment on "+vwAppointmetDetails.getScheduleDateWithTimeStr()+".");
									}else if(getPlTitle().equalsIgnoreCase("S")){
										smsContent.append("Dear "+vwAppointmetDetails.getReceiveFullName()+",");
										smsContent.append("I, "+vwAppointmetDetails.getRequestFullName()+" staff of "+vwAppointmetDetails.getStaffClassAndSection()+" request your appointment on "+vwAppointmetDetails.getScheduleDateWithTimeStr()+".");
									}
								}else if(vwAppointmetDetails.getStatus().equalsIgnoreCase("A")){
									mobileNumbersSet.add(vwAppointmetDetails.getMobileNumber());
									smsContent.append("Dear "+vwAppointmetDetails.getRequestFullName()+",");
									//smsContent.append("Your request for appointment with "+vwAppointmetDetails.getReceiveFullName()+" on "+vwAppointmetDetails.getScheduleDateStr()+" at "+vwAppointmetDetails.getScheduleTime()+" has been approved.");
									smsContent.append("Your request for appointment with "+vwAppointmetDetails.getReceiveFullName()+" on "+vwAppointmetDetails.getScheduleDateWithTimeStr()+" has been approved.");
								}else if(vwAppointmetDetails.getStatus().equalsIgnoreCase("R")){
									mobileNumbersSet.add(vwAppointmetDetails.getMobileNumber());
									smsContent.append("Dear "+vwAppointmetDetails.getRequestFullName()+",");
									smsContent.append("Your request for appointment with "+vwAppointmetDetails.getReceiveFullName()+" on "+vwAppointmetDetails.getScheduleDateWithTimeStr()+" has been rejected.");
									smsContent.append("Reason :"+vwAppointmetDetails.getApporveDescription());
								}
								objMsg.setMessageDescription(smsContent.toString());
								objMsg.setCustomer(customer);
								objMsg.setSmsSenderId(customer.getSender());
								objMsg.setAcademicYear(getCurrentAcademicYear());
								if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)) {
									log.debug("Before Calling Deviler SMS.....mobileNumberset---"+mobileNumbersSet+"-------messageDesc-----Your Eazyschool account status is in Inactive");
									objMsg = adminManager.saveMessageDetailsTracking(objMsg,null,null,getUser());
									adminManager.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
								}
								mobileNumbersSet = null;
								objMsg = null;
								smsContent=null;
								adminManager.save(customer);
						}
					if(customer.isCheckEmailService()){
							DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
							Date date = new Date();
							List emailsList = new ArrayList();
							StringBuffer br=new StringBuffer();
							if(getPlTitle().equalsIgnoreCase("P") || getPlTitle().equalsIgnoreCase("S")){
								if(!ObjectFunctions.isNullOrEmpty(vwAppointmetDetails.getReceivStaffEmail())){
									emailsList.add(vwAppointmetDetails.getReceivStaffEmail());
										br.append("Dear "+vwAppointmetDetails.getReceiveFullName()+",");
										br.append( "<br>" );br.append( " " );br.append( "<br>" );
										
										User parentObj = null;
										if(getPlTitle().equalsIgnoreCase("P"))
										{
											parentObj = (User) studentManager.get(User.class, "id="+vwAppointmetDetails.getRequestAccountId());
											br.append(parentObj.getFullPersonName()+ " parent of "+vwAppointmetDetails.getRequestFullName() +" from "+vwAppointmetDetails.getStudClassAndSection()+ " need your appointment on "+vwAppointmetDetails.getScheduleDateFormatStr()+" at "+vwAppointmetDetails.getScheduleTime());
										}
										else
											br.append(vwAppointmetDetails.getRequestPersonNameWithRoleDesc()+ " need your appointment on "+vwAppointmetDetails.getScheduleDateFormatStr()+" at "+vwAppointmetDetails.getScheduleTime());
										
										br.append( "<br>" );br.append( "Details on given Below " );br.append( "<br>" );
										br.append( "<br>" );br.append( " " );
										if(getPlTitle().equalsIgnoreCase("P"))
										{
											if(!StringFunctions.isNullOrEmpty(parentObj.getFullPersonName()))
											{
												br.append("Parent Name : "  +parentObj.getFullPersonName());
											}
										}
										else
											br.append("Student Name : "  +vwAppointmetDetails.getRequestFullName());
										
										br.append( "<br>" );
										br.append("Phone Number :" +vwAppointmetDetails.getMobileNumber());
										br.append( "<br>" );
										br.append("Email :" +vwAppointmetDetails.getEmail());
										br.append( "<br>" );
										br.append("Schedule Date : "  +vwAppointmetDetails.getScheduleDateFormatStr());
										br.append( "<br>" );
										br.append("Schedule Time : "  +vwAppointmetDetails.getScheduleTime());
										br.append( "<br>" );
										br.append("Description  : " +vwAppointmetDetails.getDescription());
										br.append( "<br>" );br.append( " " );br.append( "<br>" );
										br.append("**NOTE: Please open eazyschool software and accept or reject the appointment.");
										//log.debug(br.toString());
								}
							}
							else if(vwAppointmetDetails.getStatus().equalsIgnoreCase("A") || vwAppointmetDetails.getStatus().equalsIgnoreCase("R")){
								if(!ObjectFunctions.isNullOrEmpty(vwAppointmetDetails.getEmail())){
									emailsList.add(vwAppointmetDetails.getEmail());
									if(getPlTitle().equalsIgnoreCase("A")){  //A for Active(enableCustomer) the customers
										br.append("Dear "+vwAppointmetDetails.getRequestFullName()+",");
										br.append( "<br>" );br.append( " " );br.append( "<br>" );
										br.append("Your appointment with "+vwAppointmetDetails.getPersonNameWithRoleDesc()+" on "+vwAppointmetDetails.getScheduleDateStr()+" at "+vwAppointmetDetails.getScheduleTime()+" has been accepted .");
										br.append( "<br>" );br.append( " " );br.append( "<br>" );
										br.append("**NOTE: This is an automated email from EazySchool Software. Please DO NOT REPLY to this email.");
									}else if(getPlTitle().equalsIgnoreCase("R")){
										br.append("Dear "+vwAppointmetDetails.getRequestFullName()+",");
										br.append( "<br>" );br.append( " " );br.append( "<br>" );
										br.append("Your appointment with "+vwAppointmetDetails.getPersonNameWithRoleDesc()+" on "+vwAppointmetDetails.getScheduleDateStr()+" at "+vwAppointmetDetails.getScheduleTime()+" has been rejected .");
										br.append( "<br>" );br.append( " " );br.append( "<br>" );
										if(vwAppointmetDetails.getStatus().equalsIgnoreCase("R")){
											br.append("Description : "+vwAppointmetDetails.getApporveDescription());
										}
										br.append( "<br>" );br.append( " " );br.append( "<br>" );
										br.append("**NOTE: This is an automated email from EazySchool Software. Please DO NOT REPLY to this email.");
									}
								}
							}
							if(!ObjectFunctions.isNullOrEmpty(emailsList)){
								List emailsIdsList = new ArrayList(new HashSet(emailsList));
								for(Object staffEmailIds : emailsIdsList) {
									String[] emailAddresses = new String[1];
									emailAddresses[0] = staffEmailIds.toString();
									if(!ObjectFunctions.isNullOrEmpty(getPlTitle())){
										if(getPlTitle().equalsIgnoreCase("A")){  //A define as approve
											MailUtil mailUtil = new MailUtil(emailAddresses,"Accept on your Appointment ",br.toString(), getUser(),null,"messages@eazyschool.com");
											mailUtil.sendEmailToSupportingTeam("messages@eazyschool.com");
											mailUtil = null;
										}else if(getPlTitle().equalsIgnoreCase("R")){ //R define as reject
											MailUtil mailUtil1 = new MailUtil(emailAddresses,"Reject on your Appointment ",br.toString(), getUser(),null,"messages@eazyschool.com");
											mailUtil1.sendEmailToSupportingTeam("messages@eazyschool.com");
											mailUtil1 = null;
										}else if(getPlTitle().equalsIgnoreCase("P") || getPlTitle().equalsIgnoreCase("S")){ // P define as pending and S define as Staff
											MailUtil mailUtil1 = new MailUtil(emailAddresses,"Request for an appointment on ["+vwAppointmetDetails.getSubject()+"] ",br.toString(), getUser(),null,"messages@eazyschool.com");
											mailUtil1.sendEmailToSupportingTeam("messages@eazyschool.com");
											mailUtil1 = null;
										}
									}
								}
								br=null;
							}
						}
					}
				customer=null;
			}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
		
		@Actions( { 
			 @Action(value = "ajaxViewStudentTimetable", results = { @Result(location = "class/ajaxStudentTimetable.jsp", name = "success") }) 
		})
		public String ajaxViewStaffTimetable() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewStaffTimetable' method");
			}
			try {
				if(getUserAcademicYearId()>0){
					if(getUser().isParent()){
							if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
								List<ViewStudentPersonAccountDetails> studList = adminManager.getParentChildrens(getUserCustId(),getUser().getSelectedStudentId(),getUserAcademicYearId());
								if(!ObjectFunctions.isNullOrEmpty(studList))
									setViewStudentPersonAccountDetailsList(studList);
							}
					}
					else
						setViewStudentPersonAccountDetails((ViewStudentPersonAccountDetails)studentManager.get(ViewStudentPersonAccountDetails.class,"accountId="+Long.valueOf(getUser().getId())+" and accountExpired='"+Constants.NO_STRING+"' and custId='"+getUserCustId() +"' and academicYearId="+getUserAcademicYearId()));
				}
				setObjectList(adminManager.getAll(TimetablePeriods.class,"custId="+getUserCustId()+" and status='"+Constants.ACTIVE_STATUS+"'"));
				setTempList(adminManager.getAll(WeekDays.class));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxViewDisableStuents", results = { @Result(location = "../common/ajaxViewStudentDisable.jsp", name = "success"),
																			@Result(location = "../common/ajaxViewSuspendStudentsDetails.jsp", name = "suspend"),
																			@Result(location = "../common/ajaxViewActiveStudentDetails.jsp", name = "active")
		}) })
	    public String ajaxViewDisableStuents() throws URTUniversalException {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxViewDisableStuents' method");
			}
			try {
					setStudent(null);
				    setTempId(getTempId());
				   if(getEventId().equalsIgnoreCase(getPlTitle())){
					    setStudent((Student) studentManager.get(Student.class,"id="+getTempId()+" and classSectionId="+getSelectedId()));
				   }
				    setBedId(getSelectedId());
				   if("S".equalsIgnoreCase(getPlTitle()) || "B".equalsIgnoreCase(getPlTitle())){
					   loadAcademicYearStartDateAndDates(getUserAcademicYearId());
				    	if("S".equalsIgnoreCase(getPlTitle())){
				    		getSmsCount();
				    		setCustomer(getCustomerByCustId());
				    	}
				    	return "suspend";
				    }
			 		else if("Y".equalsIgnoreCase(getPlTitle()))
			 			return "active";
				  
			} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	    }
		
		@Actions( { 
			 @Action(value = "ajaxViewStudentStatus", results = { @Result(location = "../common/ajaxViewSuspendAndBlacklistedStudentsDetails.jsp", name = "success") }) 
		})
		public String ajaxViewStudentStatus() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewStudentStatus' method");
			}
			try {
			   if(getTempId()>0)
				   setSuspendBlackstudentList(studentManager.getAll(SuspendAndBlacklistStudents.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+getTempId()+" order by createdDate desc "));
			   setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
		@Actions( { @Action(value = "ajaxDeleteSuspendAndBlackListedStudent", results = { @Result(location = "../common/ajaxViewSuspendAndBlacklistedStudentsDetails.jsp", name = "success") }) })
		public String ajaxDeleteSuspendAndBlackListedStudent() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDeleteSuspendAndBlackListedStudent' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getSuspendAndBlacklistStudents().getId()))
				{
					SuspendAndBlacklistStudents suspendAndBlacklistStudents=(SuspendAndBlacklistStudents)staffManager.get(SuspendAndBlacklistStudents.class, getSuspendAndBlacklistStudents().getId());
					staffManager.remove(SuspendAndBlacklistStudents.class, getSuspendAndBlacklistStudents().getId());
					if(getTempId()>0 && (getTodayDate().equalsIgnoreCase(getPlTitle()) || (getTodayDate().equalsIgnoreCase(getPlSubTopic())))){
						Student student=(Student)studentManager.get(Student.class, getTempId());
						student.setStatus(Constants.YES_STRING);
						studentManager.save(student);
					}
					super.addActionMessage("student status is deleted successfully.");
				}
				ajaxViewStudentStatus();
			} 
			catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}

			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxEditStudentsStatus", results = { @Result(location = "../common/ajaxEditStudentStatus.jsp", name = "success") }) })
	    public String ajaxEditStudentsStatus() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxEditStudentsStatus' method");
		}
		try {
			getSmsCount();
			setCustomer(getCustomerByCustId());
		    setSuspendAndBlacklistStudents((SuspendAndBlacklistStudents) studentManager.get(SuspendAndBlacklistStudents.class,"id="+getSuspendAndBlacklistStudents().getId()+" and studentId="+getTempId()));
			setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
			loadAcademicYearStartDateAndDates(getUserAcademicYearId());
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	    }
		
	@Actions({ @Action(value = "ajaxDoSelectStudentOptionalSubject", results = { @Result(location = "optionalSubject/ajaxDoSelectStudentOptionalSubject.jsp", name = "success") }) })
	public String ajaxDoSelectStudentOptionalSubject() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSelectStudentOptionalSubject' method");
		}
		try {
			setStudyClassList(adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	@Action(value = "ajaxSearchStudentByStudyClass", results = { @Result(location = "optionalSubject/ajaxViewStudentList.jsp", name = "success") })
	public String ajaxSearchStudentByStudyClass() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchStudentByStudyClass' method");
		}
		try {
			setStudySubjectList(adminManager.getAll(ViewStudyClassSubjects.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studyClassId="+getClassSectionId()+" and subjectType='"+Constants.YES_STRING+"' "));
			prepareStudentsDetailsList();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Action(value = "ajaxAddStudentOptionalSubject", results = { @Result(location = "optionalSubject/ajaxViewStudentList.jsp", name = "success") })
	public String ajaxAddStudentOptionalSubject() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddStudentOptionalSubject' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getTempString()) && StringFunctions.isNotNullOrEmpty(getClassSectionId())){
				long returnCode=studentManager.addStudyClassWiseStudentOptionlaSubject(getUserCustId(),getUserAcademicYearId(),getTempString(),Long.valueOf(getClassSectionId()));
				if(returnCode == 1)
					super.addActionMessage("Student optional subject assigned successfully.");
				else if (returnCode == 0) 
					super.addActionError("Student optional subject not assigned successfully. Please contact administrator.");
			}
			ajaxSearchStudentByStudyClass();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxEditStudyClassWiseStudentOptionalSubj", results = { @Result(type = "json", name = "success", params = {"includeProperties", "classFeeList.*" }) }) })
	public String ajaxEditStudyClassWiseStudentOptionalSubj() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditStudyClassWiseStudentOptionalSubj' method");
		}
		try {
			List<Object[]> studentOptionalMarksList=null;
			if (!StringFunctions.isNullOrEmpty(getClassSectionId())) {
				List<Student> studentList=studentManager.getAll(Student.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionId()+" and description is null");
				if (ObjectFunctions.isNotNullOrEmpty(studentList)) {
					JSONArray res = new JSONArray();
					JSONObject j;
					for (Student student : studentList) {
						if(student.getOptionalSubjectId() >0 ){
							log.debug("select * from studentMarks where studId="+student.getId()+" and examScheduleId in (select id from examSchedules where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionId()+" and classSubjectId ="+student.getOptionalSubjectId()+")");
							studentOptionalMarksList = studentManager.getAll("select * from studentMarks where studId="+student.getId()+" and examScheduleId in (select id from examSchedules where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassSectionId()+" and classSubjectId ="+student.getOptionalSubjectId()+")");
							
						}
						j = new JSONObject();
						j.put("studentId", student.getId());
						j.put("optionalSubjectId", student.getOptionalSubjectId());
						if(ObjectFunctions.isNotNullOrEmpty(studentOptionalMarksList))
							j.put("optionalSubjMarksCount", studentOptionalMarksList.size());
						else
							j.put("optionalSubjMarksCount", 0);
						
						res.put(j);
						studentOptionalMarksList=null;
					}
					j = new JSONObject();
					j.put("data", res);
					getResponse().getOutputStream().print(j.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( {
		@Action(value = "ajaxCheckStudentFeepaymentAndMarks", results = { @Result(type = "json", name = "success",params = {"includeProperties","thresholdMonths*"}) }) })
	public String ajaxCheckStudentFeepaymentAndMarks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckStudentFeepaymentAndMarks' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTempId())){ 
				
				Student student=(Student)studentManager.get(Student.class, getTempId());
				if(!ObjectFunctions.isNullOrEmpty(student))
				{
					JSONObject msgDetails=new JSONObject();
					
					if(!StringFunctions.isNullOrEmpty(getClassId())) //getClassId() means studyClassId
					{
						StudyClass studyClass = (StudyClass)studentManager.get(StudyClass.class, "id="+getClassId());
						if(!ObjectFunctions.isNullOrEmpty(studyClass))
						{
							if(studyClass.getClassNameClassId().getId() != student.getClassNameClassId().getId())
							{
								if(!"N".equalsIgnoreCase(student.getFeePaidStatus())){
									msgDetails.put("feeDesc", "fee paid already");
								}
							}
						}
					}
					
					List<StudentMarks> studentMarksList = studentManager.getAll(StudentMarks.class, "studId="+student.getId());
					if(!ObjectFunctions.isNullOrEmpty(studentMarksList))
					{
						msgDetails.put("marksDesc", "already added the marks");
					}
					getResponse().getOutputStream().print(msgDetails.toString());
				}
			}
		} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return null;
	}
	
	
	
	@Action(value = "ajaxGetStudentLatestMarks", results = { @Result(location = "ajaxViewStudentLatestMarks", name = "success") })
	public String ajaxGetStudentLatestMarks() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentLatestMarks' method");
		}
		try
		{
			Student student = null;
			StringBuffer query = null;
			if(getTempId() > 0){
				student = (Student)adminManager.get(Student.class, getTempId());
			}else{
				if(getUserAcademicYearId() > 0 && getUser().getId() > 0){
					student = (Student)studentManager.get(Student.class,"accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and description is null");
				}
			}
				if(!ObjectFunctions.isNullOrEmpty(student)){
					long classSectionId=student.getClassSectionId();
					long custId=student.getCustId();
					long studId = student.getId();
					setPaymentType(student.getPopUpDisplay());// this is for checking that, is student need pop up or not
					setStudent(student);
					student = null;
					int notAvailableCount = 0;
					StringBuffer failedSubjects = new StringBuffer();
					
					SchoolGrades schoolGrades = (SchoolGrades)adminManager.get(SchoolGrades.class, "academicYearId="+getUserAcademicYearId());
					
					List<StudentMarks> latestStudentMarks=adminManager.getLatestUploadedExamTypesByStudentId(studId, 1);
					//List<ViewStudentMarksDetails> latestStudentMarks=adminManager.getAll(ViewStudentMarksDetails.class,"studId="+studId+" and  examDate is not null group by examType order by lastUpdatedDate DESC");
					
					if(ObjectFunctions.isNotNullOrEmpty(latestStudentMarks)){
						StudentMarks studentMarksExamType=latestStudentMarks.get(0);
						
						double maxMarks = Double.parseDouble(studentMarksExamType.getExamSchedule().getExamType().getMaxMarks());
						double minMarks = Double.parseDouble(studentMarksExamType.getExamSchedule().getExamType().getMinMarks());
						setTempString(studentMarksExamType.getExamSchedule().getExamType().getExamType());
						
						double totalObtainedMarks = 0;
						
						List<Object[]> classSubjectsList = adminManager.getAll("select name,subTypeName,maxMarks,classSubjectId,scheduleId,examDate from vw_classExamDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId() +" and classSectionId=" + getStudent().getClassSectionId()+" and eid=" + studentMarksExamType.getExamTypeId() +"  group by classSubjectId");
						if(ObjectFunctions.isNotNullOrEmpty(classSubjectsList)){
							query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule es WHERE  marks.student=").append(studId)
							.append(" and es.examType=").append(studentMarksExamType.getExamTypeId()).append("  group by es.subType");
							List<StudentMarks> subTypes=adminManager.getAllHqlQuery(query.toString());
							if(ObjectFunctions.isNotNullOrEmpty(subTypes)){
								
								int overallCount = 0;
									
									for(Object[] subjectObj:classSubjectsList){
										StudentMarksVO studentMarksVO = new StudentMarksVO();
										studentMarksVO.setSubjectName(subjectObj[0].toString());
											query = new StringBuffer("SELECT marks FROM StudentMarks marks JOIN marks.examSchedule schedule WHERE marks.student=").append(studId)
											  .append(" and schedule.examType=").append(studentMarksExamType.getExamTypeId())
											  .append(" and schedule.classSectionSubject=").append(subjectObj[3].toString());
											List<StudentMarks> studentMarks=adminManager.getAllHqlQuery(query.toString());
											if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
												double totalSubjectMarks = 0;
												
												for(StudentMarks marks : studentMarks){
													
													if(marks.isPresent())
													{
														totalSubjectMarks = totalSubjectMarks + marks.getObtainedMarks()+marks.getModerationMarks();
													}
												}
												
												studentMarksVO.setObtainedMarks(totalSubjectMarks);
												
												totalObtainedMarks = totalObtainedMarks + studentMarksVO.getObtainedMarks();
												log.debug("Subject Name:" + studentMarksVO.getSubjectName() + " toatal Marks:" + studentMarksVO.getObtainedMarks());
												//studentMarksVO.setSubjectResult(String.valueOf(marks.getObtainedMarks()+marks.getModerationMarks()));
												
												if(!ObjectFunctions.isNullOrEmpty(schoolGrades))
												{
													
													Object[] gradeObj = studentManager.get("SELECT id,gradeName FROM schoolGrades WHERE (" +studentMarksVO.getObtainedMarks()+">=minMarks and "+studentMarksVO.getObtainedMarks()+ "<=maxMarks) and academicYearId="+getUserAcademicYearId());
													if(!ObjectFunctions.isNullOrEmpty(gradeObj))
													{
														studentMarksVO.setSubjectResult(gradeObj[1].toString());
														gradeObj=null;
													}
													else
													{
														studentMarksVO.setSubjectResult("NA");
														notAvailableCount++;
													}
												}
												else
												{
													studentMarksVO.setSubjectResult(String.valueOf(studentMarksVO.getObtainedMarks()));
												}
												if(!ObjectFunctions.isNullOrEmpty(minMarks))
												{
													
													if(studentMarksVO.getObtainedMarks() >= minMarks)
													{
														studentMarksVO.setPassOrFail("Pass");
													}
													else
													{
														overallCount = overallCount +1;
														studentMarksVO.setPassOrFail("Fail");
														failedSubjects.append(studentMarksVO.getSubjectName()+",");
													}
												}
											}else{
												if(!ObjectFunctions.isNullOrEmpty(schoolGrades))
												{
													studentMarksVO.setSubjectResult("NA");
													studentMarksVO.setPassOrFail("Fail");
													notAvailableCount++;
												}
												else
												{
													studentMarksVO.setObtainedMarks(0);
													studentMarksVO.setPassOrFail("Fail");
												}
											}
											studentMarks=null;
											
										getTempList().add(studentMarksVO);
									 }
								
								if(overallCount>0)
								{
									setAnyId("Fail");
								}
								else
								{
									setAnyId("Fail");
								}
								
								double totalMasMarks = (classSubjectsList.size() * maxMarks);
								double overallPercentage = (totalObtainedMarks/totalMasMarks)*100;
								
								if(!ObjectFunctions.isNullOrEmpty(schoolGrades))
								{
									
									Object[] gradeObj = studentManager.get("SELECT id,gradeName,description FROM overAllGrades WHERE (" +overallPercentage+">=minMarks and "+overallPercentage+ "<=maxMarks) and academicYearId="+getUserAcademicYearId());
									if(!ObjectFunctions.isNullOrEmpty(gradeObj))
									{
										setTempString2(gradeObj[1].toString());
										
										gradeObj=null;
									}
									else
									{
										setTempString2("NA");
										setTempString3("NA");
									}
								}
								else
								{
									setTempString2("NA");
								}
								setTempString3(failedSubjects.toString());
							}
						}
					}
				}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Action(value = "ajaxUpdateStudentPopupDisplay", results = { @Result(location = "optionalSubject/ajaxViewStudentList.jsp", name = "success") })
	public String ajaxUpdateStudentPopupDisplay() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateStudentPopupDisplay' method");
		}
		try {
			if(getStudent().getId() > 0)
			{
				Student student = (Student)studentManager.get(Student.class, "id="+ getStudent().getId());
				if(!ObjectFunctions.isNullOrEmpty(student)){
					student.setPopUpDisplay(getStudent().getPopUpDisplay());
					studentManager.save(student);
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxGetNotUploadedStudents", results = {}) })
	public void ajaxGetNotUploadedStudents() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetNotUploadedStudents' method");
		}
		try { 
			 
			Customer customer = getCustomerByCustId();
			if(!ObjectFunctions.isNullOrEmpty(customer)) { 
			String filePath="userFiles/studentDataUpload/"+customer.getId()+"/";
			File destDir=null;
			//File originalFile=null;
			if(customer.isCheckEmailService())
			{
				destDir = new File(getSession().getServletContext().getRealPath(filePath+"INVALID_STUDENT_DATA.xls"));
				//originalFile = new File(getSession().getServletContext().getRealPath(filePath+"STUDENT_DATA.xls"));
				String[] emailAddresses = new String[2];
				String[] attachmentFiles = new String[1];
				attachmentFiles[0] = destDir.getAbsolutePath();
				emailAddresses[0] = "support@eazyschool.com";
				emailAddresses[1] = customer.getCustEmail();
				log.debug(attachmentFiles[0]);
				String body = "Student data sheet has an invalid data.Please verify the sheet and reupload it.";
				MailUtil mailUtil=new MailUtil(emailAddresses, "Regd:Invalid Student Data Sheet of "+customer.getFirstName()+" "+customer.getLastName()+ " customer("+customer.getId()+")", body,getUser() ,attachmentFiles,adminManager.getContactFromEmail(customer));
				mailUtil.sendEmailToSupportTeam(adminManager.getContactFromEmail(customer));
				mailUtil=null;
			}
			
			StringBuffer pathName =null;
            pathName = new StringBuffer("userFiles/").append("studentDataUpload/").append(getUserCustId()).append("/INVALID_STUDENT_DATA.xls");
    	    File destFile = new File(getSession().getServletContext().getRealPath(pathName.toString()));
    	 
    		getResponse().setContentType("application/octet-stream");
    		getResponse().addHeader("Content-Disposition", "attachment; filename=INVALID_STUDENT_DATA_SHEET.xls");
    		ServletOutputStream out = getResponse().getOutputStream();
            DataInputStream in = new DataInputStream(new FileInputStream(destFile));
            byte[] bbuf = new byte[1024];
            int length = 0;
            while (in != null && (length = in.read(bbuf)) != -1) {
                out.write(bbuf, 0, length);
            }
            in.close();
            out.flush();
            out.close();
            if(destFile.exists()){
            	destFile.delete();
            	destFile.getParentFile().delete();
            }
		}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	//This comman method used for enabled student and parent user written by Sunanda 
	public void enableStudentAndParentUserObject(User user){
		try {
			user.setAccountExpired(false);
			user.setEnabled(true);	
			user.setCreatedById(getUser().getId());
			user.setLastUpdatedById(getUser().getId());
			user.setCreatedDate(new Date());
			user.setLastUpdatedDate(new Date());
			studentManager.save(user);
			user = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	//This comman method used for disable student and parent user written by Sunanda 
	public void disableStudentAndParentUserObject(User user){
		try {
			user.setAccountExpired(true);
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedById(getUser().getId());
			user.setLastAccessDate(new Date());
			user.setEnabled(false);	
			studentManager.save(user);
			user = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	@Actions( { @Action(value = "ajaxUploadStudentData", results = {@Result(location = "../admin/reports/ajaxDownloadStudentDetails.jsp", name = "success"),
			@Result(location = "../admin/reports/ajaxDownloadStudentDetails.jsp", name = "dummyInit"),
			@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "createSuccess") }),
	@Action(value = "ajaxUploadNewStudentData", results = {@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "success"),
					@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "dummyInit"),
					@Result(location = "../admin/student/importStudentExcelSheet.jsp", name = "createSuccess") })
	})
	public String ajaxUploadStudentData() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUploadNewStudentData' method");
		}
		try {
			log.debug(getEventId());
			log.debug(getUploadContentType());
			boolean excelFileType = false;
			String moblieNumberDigitsOrNot="\\d+";
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
			
			String path1 = "INVALID_STUDENT_DATA.xls";
			 
			File  f1 = new File(getSession().getServletContext().getRealPath("userFiles/studentDataUpload/"+getUserCustId()+"/"+path1));
			
			FileUtils.copyFile(getUpload(), f1);
			
			FileInputStream inputStream = new FileInputStream(new File(getSession().getServletContext().getRealPath("userFiles/studentDataUpload/"+getUserCustId()+"/"+path1)));
			
			FileOutputStream fileOut = new FileOutputStream(f1);
			
			HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream(getUpload()));
	         
	        CellStyle hlink_style = workbook1.createCellStyle();
	         
		     Font hlink_font = workbook1.createFont();
		     hlink_style.setAlignment(hlink_style.ALIGN_RIGHT);
		     hlink_font.setColor(IndexedColors.RED.getIndex());
		     hlink_style.setFont(hlink_font);
		     XSSFColor color = new XSSFColor(new java.awt.Color(128, 0, 128));
		     hlink_style.setBottomBorderColor(hlink_style.BORDER_DOUBLE);
		     	 
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("ClassSections");
			long academicYearId = 0;
			long custId = 0;
			if (ObjectFunctions.isNullOrEmpty(sheet)) {
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				ajaxGetStudyClassList();
				return "dummyInit";
				
				/*academicYearId = getUserAcademicYearId();
				custId = getCustId();*/
			} else {
				
				org.apache.poi.ss.usermodel.Sheet firstSheet1 = workbook.getSheetAt(0);
				Row secondRow = firstSheet1.getRow(1);
				if(!ObjectFunctions.isNullOrEmpty(secondRow))
				{
					if(!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1)))
					{
						String fistColumn = secondRow.getCell(0).getStringCellValue();
						String secondColumn = secondRow.getCell(1).getStringCellValue();
						
						if(!"Student Id".equalsIgnoreCase(fistColumn.toString()) || !"Admission Number".equalsIgnoreCase(secondColumn.toString()))
						{
							log.debug("No file to upload....");
							super.addActionError("File type not matched.");
							ajaxGetStudyClassList();
							return "dummyInit";
						}
						fistColumn = null;
						secondColumn = null;
					}
					else
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						ajaxGetStudyClassList();
						return "dummyInit";
					}
				}
				else
				{
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
					ajaxGetStudyClassList();
					return "dummyInit";
				}
				
				
				Row row = sheet.getRow(0);
				academicYearId = (long) row.getCell(1).getNumericCellValue();
				row = sheet.getRow(1);
				custId = (long) row.getCell(1).getNumericCellValue();
			}
			if(!"AdmissionCreate".equalsIgnoreCase(getEventId())){
				if(!isCurrentAcademicYearId(academicYearId)) {
					log.debug("Uploaded wrong file..");
					super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
					ajaxGetStudyClassList();
					return "dummyInit";
				}
			}	
			if(custId !=0){
				String missingMandatoryValues = null;
				Student stud = null;
				User account = null;
				Person person = null;
				ParentsEmploymentDetails parentsEmploymentDetails = null;
				PersonHealthDetails personHealthDetails = null;
				PersonOtherDetails personOtherDetails = null;
				Address address = null;
				String admissionStr =null;
				List<Student> studentDetailsList = null;
				List<ExcelStudentVO> studentVoDetailsList = null;
				boolean isChangedAdmissionNo = true;
				StringBuffer admissionNumberExistMsg = new StringBuffer();
				StringBuffer feepaidAdmissinNumber = new StringBuffer();
				StringBuffer errorMessageBuffer = new StringBuffer();
				StringBuffer mandatoryErrorMessageBuffer = new StringBuffer();
				StringBuffer enrollmentCodeMessageBuffer = new StringBuffer();
				StringBuffer castNameMessageBuffer = new StringBuffer();
				StringBuffer fatherAadharNumberMessageBuffer = new StringBuffer();
				StringBuffer motherAadharNumberMessageBuffer = new StringBuffer();
				StringBuffer duplicateRollNumberMessageBuffer = new StringBuffer();
				StringBuilder mobileNumberNumberLengthMsg = new StringBuilder();
				StringBuffer cellNumber = new StringBuffer();
				StringBuffer commitFeeStr = new StringBuffer();
				Long settingsId = null;
				CommonType commonType = null;
				CastSettings castSettingObj = null;
				SubCastSettings subCastSettingObj = null;
				ClassName className = null;
				StudyClass studyClass = null;
				double deductingAmnt = 0;
				double previousCommittedFee = 0;
				Set<String> classNames = new HashSet<String>();
				Set<StudyClass> studyClassIdsSet = new HashSet<StudyClass>();
				Set<Long> rollNumberNotAssignedClasses = new HashSet<Long>();
				Customer customer = (Customer) studentManager.get(Customer.class,"id=" + custId);
				SMSServiceProviders smsServiceProvider=(SMSServiceProviders)  adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				AcademicYear academicYear = (AcademicYear) studentManager.get(AcademicYear.class, academicYearId);
				Role studentRole = adminManager.getRoleByName(Constants.SCHOOL_STUDENT);
				//SchoolCategory category = adminManager.getCategoryIdByCustId("General", custId);
				HashMap<String, SchoolCategory> schoolCategoryMap = new HashMap<String, SchoolCategory>();
				List<SchoolCategory> categoryList = studentManager.getAll(SchoolCategory.class,"custId="+getUserCustId());
				if (ObjectFunctions.isNotNullOrEmpty(categoryList)) {
					for (SchoolCategory categoryObj : categoryList) {
						schoolCategoryMap.put(categoryObj.getCategoryName(),categoryObj);
					}
				}
				HashMap<String, StudyClass> studyClassMap = new HashMap<String, StudyClass>();
				List<StudyClass> studyClasses = studentManager.getAll(StudyClass.class, "custId=" + custId +" and academicYearId=" + academicYearId);
				StringBuffer classObj = null;
				if (ObjectFunctions.isNotNullOrEmpty(studyClasses)) {
					for (StudyClass studyClas : studyClasses) {
						classObj = new StringBuffer();
						if(!ObjectFunctions.isNullOrEmpty(studyClas.getClassName())){
							classObj.append(studyClas.getClassName());
						}
						if(!ObjectFunctions.isNullOrEmpty(studyClas.getSection())){
								classObj.append("-"+studyClas.getSection());
						}
						studyClassMap.put(classObj.toString(),studyClas);
					}
					classObj = null;
				}
				
				HashMap<String, CastSettings> castSettingsMap = new HashMap<String, CastSettings>();
				List<CastSettings> castSettings = studentManager.getAll(CastSettings.class, "custId=" + custId);
				if (ObjectFunctions.isNotNullOrEmpty(castSettings)) {
					for (CastSettings castSetting : castSettings) {
						castSettingsMap.put(castSetting.getCastName().toLowerCase(),castSetting);
					}
				}
				HashMap<String, SubCastSettings> subCastSettingMap = new HashMap<String, SubCastSettings>();
				List<SubCastSettings> subcastSettings = studentManager.getAll(SubCastSettings.class, "custId=" + custId);
				if (ObjectFunctions.isNotNullOrEmpty(subcastSettings)) {
					for (SubCastSettings subCast : subcastSettings) {
						subCastSettingMap.put(subCast.getSubCastName().toLowerCase(), subCast);
					}
				}
				HashMap<String, Long> religionsMap = new HashMap<String, Long>();
				HashMap<String, Long> motherToungsMap = new HashMap<String, Long>();
				List<CommonType> commonTypeList = studentManager.getAll(CommonType.class, "custId=" + custId+ " and type in ('RELIGION')");
				if (ObjectFunctions.isNotNullOrEmpty(commonTypeList)) {
					for (CommonType commontype : commonTypeList) {
							religionsMap.put(commontype.getSkillTypeName().toLowerCase(),commontype.getId());
					}
				}
				List<MotherTongue> motherTongueList = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
				if (ObjectFunctions.isNotNullOrEmpty(motherTongueList)) {
					for (MotherTongue motherTongueObj : motherTongueList) {
							motherToungsMap.put(motherTongueObj.getName(),motherTongueObj.getId());
					}
				}
				List<HouseType> houseTypeList = studentManager.getAll(HouseType.class);
				HashMap<Long, HouseType> houseTypeMap = new HashMap<Long, HouseType>();
				HashMap<String, Long> houseTypeNameMap = new HashMap<String, Long>();
				if (ObjectFunctions.isNotNullOrEmpty(houseTypeList)) {
					for (HouseType houseTypeObj : houseTypeList) {
						houseTypeMap.put(houseTypeObj.getId(),houseTypeObj);
						houseTypeNameMap.put(houseTypeObj.getType().trim(), houseTypeObj.getId());
					}
				}
				statelist = studentManager.getCountryStates(0);
				CommonType commonTypes =(CommonType)studentManager.get(CommonType.class,"custId="+getUserCustId()+" and skillTypeName like '%"+"Other"+"%' and type like '%"+"RELIGION"+"%' ");
				//CastSettings castsettings =(CastSettings)studentManager.get(CastSettings.class,"custId ="+getUserCustId()+" and castName like '%"+"Other"+"%' ");
				String userName = null;
				User objUser= null;
				int loopCount=1;
				
				Sheet firstSheet=null;
				
				HashMap<String, Integer> removeRows = new HashMap<String, Integer>();
				
				Map<String, User> sendEmailToParentsAndStudentMap = new HashMap<String, User>();
				Map<String, List> sendSmsForParentsAndStudentMap = new HashMap<String, List>();
				List<Student> studentObjList = new ArrayList<Student>();
				Map<String, Student> entryStudentCommittedPaymentMap = new HashMap<String, Student>();
				
				int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
				
				for (int sheetNo = 0; sheetNo < (workbook.getNumberOfSheets() - 1); sheetNo++) {
					sheet = workbook.getSheetAt(sheetNo);
					firstSheet = workbook1.getSheetAt(sheetNo);
					int sheetRowCount=1;					
					int rowCount=2;
					boolean isUploadStudentMarks = false;
					boolean isCheckedStudentMarks = false;
					SheetParser parser = new SheetParser();
					
					if("ClassSections".equalsIgnoreCase(sheet.getSheetName()))
					{
						continue;
					}
					try {
						studentVoDetailsList = parser.createEntity(sheet, sheet.getSheetName(), ExcelStudentVO.class);
						long countNo = 0;
						
						if (ObjectFunctions.isNotNullOrEmpty(studentVoDetailsList)) {
							for (ExcelStudentVO studentVoDetails : studentVoDetailsList) {
								
								try {
									log.debug(studentVoDetails.getFirstName()); 
										 if (!ObjectFunctions.isNullOrEmpty(studentVoDetails)
													&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getAdmissionNumber())
													&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getClassAndSection())
													&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getFirstName())) {
												log.debug("***************** " + studentVoDetails.getFirstName() + "***************** " );
												
												sheetRowCount++;											
												
												
												UserVO accountVo = new UserVO();
												PersonVO personVO = new PersonVO();
												AddressVO primaryAddressVo = new AddressVO();
												AddressVO corAddressVo = new AddressVO();
												PersonOtherDetailsVO personOtherDetailsVO = new PersonOtherDetailsVO();
												ParentsEmploymentDetailsVO parentsEmploymentDetailsVO = new ParentsEmploymentDetailsVO();
												PersonHealthDetailsVO healthDetailsVO = new PersonHealthDetailsVO();
												
												StudentVo studentVo = new StudentVo();
												AcademicYearVo academicYearVo = new AcademicYearVo();
												academicYearVo.setId(getUserAcademicYearId());
												
													 if(loopCount > 4) break;
													 if(ObjectFunctions.isNullOrEmpty(sheet.getRow(rowCount++))){
														loopCount++;
														continue;
													  }
													loopCount=1;
													String mobileNumber = null;
													String secondaryMobileNumber = null;
													String studMobileNumber = null;
													isChangedAdmissionNo = true;
													missingMandatoryValues = "missed";
													
													int sentSmsCount=adminManager.getTotalSmsCount(customer.getId(),academicYear.getId());
													
													studyClass = studyClassMap.get(studentVoDetails.getClassAndSection());
													if (ObjectFunctions.isNullOrEmpty(studyClass)) {
														classNames.add(studentVoDetails.getClassAndSection()+ ", ");
														continue;
													} else {
														className = studyClass.getClassNameClassId();
														if (ObjectFunctions.isNullOrEmpty(className)) {
															classNames.add(studentVoDetails.getClassAndSection()+ ", ");
															continue;
														}
													}
													
													if(!isCheckedStudentMarks)
													{
														List studentMarksList = studentManager.getAll(ViewStudentMarks.class, "classSectionId="+studyClass.getId()+" and academicYearId="+getUserAcademicYearId());
														if(!ObjectFunctions.isNullOrEmpty(studentMarksList))
														{
															isUploadStudentMarks = true;
														}
														studentMarksList = null;
														isCheckedStudentMarks = true;
													}
													String admisionNo = null;
													String oldEmail = null;
													String updatedAdmissionStatus = null;
													String studStatus = null;
													
													userName = StringFunctions.prepareUserName(String.valueOf(customer.getId()+"S"), studentVoDetails.getAdmissionNumber(), "");
													
													if (!StringFunctions.isNullOrEmpty(studentVoDetails.getStuId())) {
														stud = (Student) studentManager.get(Student.class, Long.valueOf(studentVoDetails.getStuId()));
														
														if(!StringFunctions.isNullOrEmpty(stud.getAccount().getPerson().getAnotherMobileNumber()))
															mobileNumber = stud.getAccount().getPerson().getAnotherMobileNumber();
														if(!StringFunctions.isNullOrEmpty(stud.getAccount().getPerson().getAnotherSecondaryMobileNumber()))
															secondaryMobileNumber = stud.getAccount().getPerson().getAnotherSecondaryMobileNumber();
														
														studMobileNumber = stud.getAccount().getPerson().getStudentMobile();
														oldEmail = stud.getAccount().getPerson().getStudentEmail();
														account = (User) studentManager.get(User.class, "custId="+ custId+ " and id="+ stud.getAccount().getId());
														person = (Person) studentManager.get(Person.class, account.getPerson().getId());
														if(!ObjectFunctions.isNullOrEmpty(account.getPrimaryAddress()))
															address = (Address) studentManager.get(Address.class, account.getPrimaryAddress().getId());
														else
															address = new Address();
														admisionNo=account.getAdmissionNumber().trim();
														studStatus = stud.getStatus();
														stud.setLastUpdatedById(getUser().getId());
														account.setLastUpdatedById(getUser().getId());
														stud.setLastUpdatedDate(new Date());
														accountVo.setId(account.getId());
														personVO.setId(account.getPerson().getId());
														
														// update the Existing Student
														if (!(studentVoDetails.getAdmissionNumber().trim().equalsIgnoreCase(admisionNo))) {
															
															updatedAdmissionStatus ="Yes";
															
															
															List viewStudentClassDetailsList = null;
															
															if(customer.isAddStudentsSameAdmissionNumber())
															{
																viewStudentClassDetailsList = studentManager.getAll(ViewStudentClassDetails.class,"custId ="+getUserCustId()+" and classId ="+ studyClass.getClassNameClassId().getId()+ " and admissionNumber='"+studentVoDetails.getAdmissionNumber().trim()+"' and studId <>"+studentVoDetails.getStuId());
															}
															else
															{
																viewStudentClassDetailsList = studentManager.getAll(User.class,"custId ="+getUserCustId()+" and admissionNumber='"+studentVoDetails.getAdmissionNumber().trim()+"' ");//and studId <> "+studentVoDetails.getStuId()
															}
															Row row=firstSheet.getRow(sheetRowCount);
															if (!ObjectFunctions.isNullOrEmpty(viewStudentClassDetailsList)) {
																isChangedAdmissionNo = false;
																admissionNumberExistMsg.append(studentVoDetails.getAdmissionNumber()+ ", ");
																Cell cell = (Cell) row.getCell(1);														
																cell.setCellStyle(hlink_style);	
																continue;
																
															}
															else {														
																accountVo.setUsername(userName);
																accountVo.setSecondaryUsername(userName);
																accountVo.setPassword(PasswordUtils.passwordEncoder(userName,null));
															}
															objUser = null;
															userName=null;
															viewStudentClassDetailsList = null;
														} else {
															isChangedAdmissionNo = false;
														}
														
														
													} else {
														log.debug("new student generation");
														stud = new Student();
														account = new User();
														person = new Person();
														address = new Address();
														
														stud.setStatus(Constants.YES_STRING);
														stud.setCreatedById(getUser().getId());
														stud.setCreatedDate(new Date());
														stud.setCustId(getUserCustId());
														
														account.setCustId(getUserCustId());
														account.addNewRole(studentRole);
														account.setCreatedById(getUser().getId());
														account.setCreatedDate(new Date());
														account.setAccountExpired(false);
														account.setEnabled(true);
														accountVo.setUsername(userName);
														accountVo.setSecondaryUsername(userName);
														accountVo.setPassword(PasswordUtils.passwordEncoder(userName,null));
														
														
														List viewStudentClassDetailsList = null;
														if(customer.isAddStudentsSameAdmissionNumber())
														{
															viewStudentClassDetailsList = studentManager.getAll(ViewStudentClassDetails.class,"custId ="+getUserCustId()+" and classId ="+ studyClass.getClassNameClassId().getId()+ " and admissionNumber='"+studentVoDetails.getAdmissionNumber().trim()+"'");
														}
														else
														{
															viewStudentClassDetailsList = studentManager.getAll(User.class,"custId ="+getUserCustId()+" and admissionNumber='"+studentVoDetails.getAdmissionNumber().trim()+"'");
														}
														
														Row row=firstSheet.getRow(sheetRowCount);
														if (!ObjectFunctions.isNullOrEmpty(viewStudentClassDetailsList)) {
															isChangedAdmissionNo = false;
															admissionNumberExistMsg.append(studentVoDetails.getAdmissionNumber()+ ", ");
															
															Cell cell = (Cell) row.getCell(1);														
															cell.setCellStyle(hlink_style);	
															continue;
															
														}
														viewStudentClassDetailsList = null;
														userName=null;
													}
													
													if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getReligion())) {
														settingsId = religionsMap.get(studentVoDetails.getReligion().trim().toLowerCase());
														if (ObjectFunctions.isNullOrEmpty(settingsId)) {
															commonType = new CommonType();
															commonType.setCustId(custId);
															commonType.setSkillTypeName(studentVoDetails.getReligion().trim().toUpperCase());
															commonType.setType("RELIGION");
															//commonType.setVersion(0);
															commonType = (CommonType) studentManager.saveOrUpdateObject(commonType);
															settingsId = commonType.getId();
															religionsMap.put(commonType.getSkillTypeName().toLowerCase(),commonType.getId());
														}
														personVO.setReligionId(settingsId);
													}else{
														if(!ObjectFunctions.isNullOrEmpty(commonTypes)){
															personVO.setReligionId(commonTypes.getId());
														}
													}
													if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getMotherToung())) {
														settingsId = motherToungsMap.get(studentVoDetails.getMotherToung().trim());
														if(!ObjectFunctions.isNullOrEmpty(settingsId))
														personVO.setMotherToungId(settingsId);
													} else
														personVO.setMotherToungId(0);

														if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getCommunity())) {
															castSettingObj = castSettingsMap.get(studentVoDetails.getCommunity().trim().toLowerCase());
															if(!ObjectFunctions.isNullOrEmpty(castSettingObj))
																personVO.setCastId(castSettingObj.getId());
														}
														if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getCastName())) {
															subCastSettingObj = subCastSettingMap.get(studentVoDetails.getCastName().toLowerCase());
															if (!ObjectFunctions.isNullOrEmpty(subCastSettingObj) && !ObjectFunctions.isNullOrEmpty(castSettingObj))
															{
																if (subCastSettingObj.getCastSettings().getId() == castSettingObj.getId())
																	personVO.setSubCastId(subCastSettingObj.getId());
																else
																{
																	castNameMessageBuffer.append(studentVoDetails.getAdmissionNumber() +", " );
																	Row row=firstSheet.getRow(sheetRowCount);
																	Cell cell = (Cell) row.getCell(40);														
																	cell.setCellStyle(hlink_style);	
																	//continue;
																}
															}
															
														} else
															personVO.setSubCastId(0);
														castSettingObj = null;
														subCastSettingObj = null;
													
													
													studentVo.setId(stud.getId());
													studentVo.setAcademicYearVo(academicYearVo);
													studentVo.setCustId(getUserCustId());
													studentVo.setRteStatus(studentVoDetails.getRteStatus());
													studentVo.setTransportMode(studentVoDetails.getTransportMode());
													studentVo.setBplStatus(studentVoDetails.getBplStatus());
													studentVo.setHostelMode(studentVoDetails.getHostelMode());
													
													accountVo.setAdmissionNumber(studentVoDetails.getAdmissionNumber().trim());
													
													primaryAddressVo.setAddressLine1(studentVoDetails.getAddressLine1());
													primaryAddressVo.setAddressLine2(studentVoDetails.getAddressLine2());
													primaryAddressVo.setCity(studentVoDetails.getCity());
													primaryAddressVo.setDistrictName(studentVoDetails.getDistrictName());
													primaryAddressVo.setState(studentVoDetails.getState());
													primaryAddressVo.setPostalCode(studentVoDetails.getPostalCode());
													
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getHouseType())){
													primaryAddressVo.setHouseTypeId(houseTypeNameMap.get(studentVoDetails.getHouseType().trim()));
													}
													
													//Setting Correspondence Address
													if("No".equalsIgnoreCase(studentVoDetails.getSameAsResidentialAddress())){
													corAddressVo.setAddressLine1(studentVoDetails.getCorAddressLine1());
													corAddressVo.setAddressLine2(studentVoDetails.getCorAddressLine2());
													corAddressVo.setCity(studentVoDetails.getCorCity());
													corAddressVo.setDistrictName(studentVoDetails.getCorDistrictName());
													corAddressVo.setState(studentVoDetails.getCorstate());
													corAddressVo.setPostalCode(studentVoDetails.getCorPostalCode());
													}
													
													personVO.setFirstName(studentVoDetails.getFirstName());
													personVO.setLastName(studentVoDetails.getLastName());
													personVO.setDateOfBirth(studentVoDetails.getDateOfBirth());
													personVO.setGender(studentVoDetails.getGender());
													personVO.setDateOfJoining(studentVoDetails.getDateOfJoining());
													personVO.setClassJoined(studentVoDetails.getClassJoined());
													personVO.setFatherName(studentVoDetails.getFatherName());
													personVO.setOccupation(studentVoDetails.getOccupation());
													personVO.setMotherName(studentVoDetails.getMotherName());
													personVO.setMotherOccupation(studentVoDetails.getMotherOccupation());
													
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getAnnualIncomeStr()))
													{
														try{
															personVO.setAnnualIncomeStr(studentVoDetails.getAnnualIncomeStr());
														}catch (NumberFormatException e) {
															e.printStackTrace();
														}
													}
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getMobileNumber()))
													{
														if(studentVoDetails.getMobileNumber().length() == 10 && studentVoDetails.getMobileNumber().matches(moblieNumberDigitsOrNot))
															personVO.setMobileNumber(studentVoDetails.getMobileNumber());
														else
															mobileNumberNumberLengthMsg.append(studentVoDetails.getAdmissionNumber() +", " );
													}
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getSecondaryMobileNumber()))
													{
														if(studentVoDetails.getSecondaryMobileNumber().length() == 10)
															personVO.setSecondaryMobileNumber(studentVoDetails.getSecondaryMobileNumber());
													}
													
													personVO.setPhoneNumber(studentVoDetails.getPhoneNumber());
													personVO.setParentEmail(studentVoDetails.getParentEmail());
													personVO.setStudentEmail(studentVoDetails.getStudentEmail());
													personVO.setStudentMobile(studentVoDetails.getStudentMobile());
													personVO.setStudentUniqueNumber(studentVoDetails.getStudentUniqueNumber());
													personVO.setIdentification1(studentVoDetails.getIdentification1());
													personVO.setIdentification2(studentVoDetails.getIdentification2());
													personVO.setMotherToung(studentVoDetails.getMotherToung());
													personVO.setPlaceOfBirth(studentVoDetails.getPlaceOfBirth());
													personVO.setNationality(studentVoDetails.getNationality());
													personVO.setReligion(studentVoDetails.getReligion());
													personVO.setCommunity(studentVoDetails.getCommunity());
													personVO.setCastName(studentVoDetails.getCastName());
													personVO.setScholarShipInfo(studentVoDetails.getScholarShipInfo());
													personVO.setScholarShipAmountStr(studentVoDetails.getScholarShipAmount());
													personVO.setRationCardNumber(studentVoDetails.getRationCardNumber());
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getAadharNumber()))
													{
														if(studentVoDetails.getAadharNumber().length() <= 14 && studentVoDetails.getAadharNumber().length() >=12)
															personVO.setAadharNumber(studentVoDetails.getAadharNumber());
													}
													personVO.setBloodGroup(studentVoDetails.getBloodGroup());
													personVO.setHeightStr(studentVoDetails.getHeightStr());
													personVO.setHeight2Str(studentVoDetails.getHeight2Str());
													personVO.setWeightStr(studentVoDetails.getWeightStr());
													personVO.setWeight2Str(studentVoDetails.getWeight2Str());
													personVO.setVisionR(studentVoDetails.getVisionR());
													personVO.setVisionL(studentVoDetails.getVisionL());
													personVO.setTeeth(studentVoDetails.getTeeth());
													personVO.setPhIdStr(studentVoDetails.getPhIdStr());
													personVO.setPhysicallyHandicappedDesc(studentVoDetails.getPhysicallyHandicappedDesc());
													personVO.setFamilyDoctor(studentVoDetails.getFamilyDoctor());
													//Setting new columns for Cambridge school
													personVO.setLastSchool(studentVoDetails.getPreviousSchoolName());
													personVO.setFatherQualification(studentVoDetails.getFatherQualification());
													personVO.setMotherQualification(studentVoDetails.getMotherQualification());
													
													personOtherDetailsVO.setNoOfDependents(studentVoDetails.getNoOfDependents());
													personOtherDetailsVO.setScstCommunity(studentVoDetails.getScstCommunity());
													personOtherDetailsVO.setMotherEmail(studentVoDetails.getMotherEmail());
													personOtherDetailsVO.setSiblingName1(studentVoDetails.getSiblingName1());
													personOtherDetailsVO.setSiblingName2(studentVoDetails.getSiblingName2());
													personOtherDetailsVO.setSiblingName3(studentVoDetails.getSiblingName3());
													personVO.setPersonOtherDetailsVO(personOtherDetailsVO);
													if(StringFunctions.isNotNullOrEmpty(studentVoDetails.getStsNumber())&& studentVoDetails.getStsNumber().length() <= 15){
														personVO.setStsNumber(studentVoDetails.getStsNumber());
													}else{
														if(!ObjectFunctions.isNullOrEmpty(stud.getAccount()))
														personVO.setStsNumber(stud.getAccount().getPerson().getStsNumber());
														
													}
													//Setting parents employment details
													parentsEmploymentDetailsVO.setFatherOrganizationName(studentVoDetails.getFatherOrganizationName());
													parentsEmploymentDetailsVO.setFatherDesignation(studentVoDetails.getFatherDesignation());
													parentsEmploymentDetailsVO.setFatherSelfEmployed(studentVoDetails.getFatherSelfEmployed());
													parentsEmploymentDetailsVO.setFatherNatureofBusiness(studentVoDetails.getFatherNatureofBusiness());
													parentsEmploymentDetailsVO.setMotherOrganizationName(studentVoDetails.getMotherOrganizationName());
													parentsEmploymentDetailsVO.setMotherDesignation(studentVoDetails.getMotherDesignation());
													parentsEmploymentDetailsVO.setMotherSelfEmployed(studentVoDetails.getMotherSelfEmployed());
													parentsEmploymentDetailsVO.setMotherNatureofBusiness(studentVoDetails.getMotherNatureofBusiness());
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getMotherAnnualIncome()))
													{
														try{
														parentsEmploymentDetailsVO.setMotherAnnualIncome(Double.valueOf(studentVoDetails.getMotherAnnualIncome()));
														}
														catch (NumberFormatException e) {
															log.debug("studentVoDetails.getMotherAnnualIncome() :"+studentVoDetails.getMotherAnnualIncome());
															e.printStackTrace();
														}
													}
													personVO.setParentsEmploymentDetailsVO(parentsEmploymentDetailsVO);
													//Setting student health details
													healthDetailsVO.setAllergies(studentVoDetails.getAllergies());
													healthDetailsVO.setHeartProblem(studentVoDetails.getHeartProblem());
													healthDetailsVO.setDiabetes(studentVoDetails.getDiabetes());
													healthDetailsVO.setAsthma(studentVoDetails.getAsthma());
													healthDetailsVO.setOtherMedicalCondition(studentVoDetails.getOtherMedicalCondition());
													healthDetailsVO.setFamilyDoctor(studentVoDetails.getFamilyDoctor());
													healthDetailsVO.setDoctorsContactNo(studentVoDetails.getDoctorsContactNo());
													healthDetailsVO.setEmergencyNo1(studentVoDetails.getEmergencyNo1());
													healthDetailsVO.setEmergencyNo2(studentVoDetails.getEmergencyNo2());
													personVO.setPersonHealthDetailsVO(healthDetailsVO);
													
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getFatherAadharNumber()))
													{
														if(studentVoDetails.getFatherAadharNumber().length() <= 14 && studentVoDetails.getFatherAadharNumber().length() >=12)
															personVO.setFatherAadharNumber(studentVoDetails.getFatherAadharNumber()); 
														else
														{
															fatherAadharNumberMessageBuffer.append(studentVoDetails.getAdmissionNumber() +", " );
															Row row=firstSheet.getRow(sheetRowCount);
															Cell cell = (Cell) row.getCell(59);														
															cell.setCellStyle(hlink_style);	
															//continue;
														}
													}
													
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getMotherAadharNumber()))
													{
														if(studentVoDetails.getMotherAadharNumber().length() <= 14 && studentVoDetails.getMotherAadharNumber().length() >=12)
															personVO.setMotherAadharNumber(studentVoDetails.getMotherAadharNumber());
														else
														{
															motherAadharNumberMessageBuffer.append(studentVoDetails.getAdmissionNumber() +", " );
															Row row=firstSheet.getRow(sheetRowCount);
															Cell cell = (Cell) row.getCell(60);														
															cell.setCellStyle(hlink_style);	
															//continue;
														}
													}
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getStudentBankId()))
													{
														personVO.setStudentBankId(studentVoDetails.getStudentBankId());	
													}
													//Setting Primary(Residential) address
													if(StringFunctions.isNotNullOrEmpty(studentVoDetails.getState()) && !ObjectFunctions.isNullOrEmpty(statelist.get(studentVoDetails.getState()))){
														primaryAddressVo.setStateId(statelist.get(studentVoDetails.getState()).getId());
														primaryAddressVo.setState(statelist.get(studentVoDetails.getState()).getStateCode());
													}
													if (!ObjectFunctions.isNullOrEmpty(account.getPrimaryAddress())) {
														
														Address primaryAddress = account.getPrimaryAddress();
														primaryAddress = primaryAddress.copyFromVoToEntity(primaryAddress, primaryAddressVo);
														primaryAddress.setHouseType(houseTypeMap.get(primaryAddressVo.getHouseTypeId()));
														account.setPrimaryAddress(primaryAddress);
													} else {
														Address primaryAddress = new Address();
														primaryAddress = primaryAddress.copyFromVoToEntity(primaryAddress, primaryAddressVo);
														primaryAddress.setHouseType(houseTypeMap.get(primaryAddressVo.getHouseTypeId()));
														account.setPrimaryAddress(primaryAddress);
													}
													//Setting correspondence address
													if("No".equalsIgnoreCase(studentVoDetails.getSameAsResidentialAddress()))
													{
														personVO.setSameAsResidentialAddress("No");
														if(StringFunctions.isNotNullOrEmpty(studentVoDetails.getCorstate()) && !ObjectFunctions.isNullOrEmpty(statelist.get(studentVoDetails.getCorstate()))){
															corAddressVo.setStateId(statelist.get(studentVoDetails.getCorstate()).getId());
															corAddressVo.setState(statelist.get(studentVoDetails.getCorstate()).getStateCode());
														}
														if (!ObjectFunctions.isNullOrEmpty(account.getTempararyAddress())) {
															Address temporaryAddress = account.getTempararyAddress();
															temporaryAddress = temporaryAddress.copyFromVoToEntity(temporaryAddress, corAddressVo);
															account.setTempararyAddress(temporaryAddress);
														} else {
															Address tempararyAddress = new Address();
															tempararyAddress = tempararyAddress.copyFromVoToEntity(tempararyAddress, corAddressVo);
															account.setTempararyAddress(tempararyAddress);
														}
													}else personVO.setSameAsResidentialAddress("Yes");
													if (!ObjectFunctions.isNullOrEmpty(account.getPerson())) 
													{
														person = account.getPerson();
														person = person.copyFromVoToEntity(person, personVO);
														
														//Setting person other details
														personOtherDetails = account.getPerson().getPersonOtherDetails();
														if (ObjectFunctions.isNullOrEmpty(personOtherDetails)) 
														personOtherDetails = new PersonOtherDetails();
														personOtherDetails = personOtherDetails.deepCopyVoToEntity(personVO.getPersonOtherDetailsVO());
														person.setPersonOtherDetails(personOtherDetails);
														
														//Setting parents employment details
														parentsEmploymentDetails = account.getPerson().getParentsEmploymentDetails();
														if (ObjectFunctions.isNullOrEmpty(parentsEmploymentDetails)) 
														parentsEmploymentDetails = new ParentsEmploymentDetails();
														parentsEmploymentDetails = parentsEmploymentDetails.deepCopyVoToEntity(personVO.getParentsEmploymentDetailsVO());
														person.setParentsEmploymentDetails(parentsEmploymentDetails);
														
														//Setting person health details
														personHealthDetails = account.getPerson().getPersonHealthDetails();
														if (ObjectFunctions.isNullOrEmpty(personHealthDetails))
														personHealthDetails = new PersonHealthDetails();
														personHealthDetails = personHealthDetails.deepCopyVoToEntity(personVO.getPersonHealthDetailsVO());
														person.setPersonHealthDetails(personHealthDetails);
														//Setting Person details
														account.setPerson(person); 
													} else {
														person = new Person();
														person = person.copyFromVoToEntity(person, personVO);
														
														//Setting person other details
														personOtherDetails = new PersonOtherDetails();
														personOtherDetails = personOtherDetails.deepCopyVoToEntity(personVO.getPersonOtherDetailsVO());
														person.setPersonOtherDetails(personOtherDetails);
														
														//Setting parents employment details
														parentsEmploymentDetails = new ParentsEmploymentDetails();
														parentsEmploymentDetails = parentsEmploymentDetails.deepCopyVoToEntity(personVO.getParentsEmploymentDetailsVO());
														person.setParentsEmploymentDetails(parentsEmploymentDetails);
														
														//Setting person health details
														personHealthDetails = new PersonHealthDetails();
														personHealthDetails = personHealthDetails.deepCopyVoToEntity(personVO.getPersonHealthDetailsVO());
														person.setPersonHealthDetails(personHealthDetails);
														//Setting Person details
														account.setPerson(person);
													}
													
													StringBuffer enrollmentCodeQuery = new StringBuffer();
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getEnrollmentCode()))
													{
														accountVo.setEnrollmentCode(studentVoDetails.getEnrollmentCode());
														enrollmentCodeQuery.append(" custId=" +getUserCustId() + " and enrollmentCode ='" + studentVoDetails.getEnrollmentCode() + "'");
														if(account.getId() > 0)
															enrollmentCodeQuery.append(" and id !=" + account.getId());
														//Object[] enrollmentCodeUserObj = adminManager.get("select id,enrollmentCode from Account where custId=" +getUserCustId() + " and enrollmentCode ='" + studentVoDetails.getEnrollmentCode() + "'");
														Object[] enrollmentCodeUserObj = adminManager.get("select id,enrollmentCode from Account where " + enrollmentCodeQuery.toString());
														if(!ObjectFunctions.isNullOrEmpty(enrollmentCodeUserObj))
														{
															enrollmentCodeMessageBuffer.append(studentVoDetails.getEnrollmentCode() +", " );
															account.setEnrollmentCode(account.getEnrollmentCode());
															studentVoDetails.setEnrollmentCode(account.getEnrollmentCode());
														}
														enrollmentCodeUserObj = null;
													}
													
													if (!ObjectFunctions.isNullOrEmpty(account)) 
													{
														if(StringFunctions.isNullOrEmpty(account.getSecondaryUsername()))
																account.setSecondaryUsername(String.valueOf(account.getId()));
														account = account.copyFromVoToEntity(account, accountVo);
														if(!studentVoDetails.getAdmissionNumber().equalsIgnoreCase(admisionNo)){
															account.setUsername(accountVo.getUsername());
															account.setSecondaryUsername(accountVo.getUsername());
															account.setPassword(accountVo.getPassword());
														}
														//account.copyBeans(studentVoDetails.getAccount(), account,isChangedAdmissionNo);
													} else {
														account = account.copyFromVoToEntity(account, accountVo);
													}
													
													try{
														account = (User) studentManager.save(account);
														if(!ObjectFunctions.isNullOrEmpty(account)){
															studentManager.updateStudentUserName(account.getId(),PasswordUtils.passwordEncoder(String.valueOf(account.getId()),null));
														}
													}
													catch(Exception e){
														e.printStackTrace();
													}
													stud.setAccount(account);
													if(!StringFunctions.isNullOrEmpty(studStatus))
														stud.setStatus(studStatus);
													else
														stud.setStatus(Constants.YES_STRING);
													//stud.setActiveStudent(true);
													
													stud.setRegisterNumber(studentVoDetails.getRegisterNumber());
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getHostelMode()))
														stud.setHostelMode(studentVoDetails.getHostelMode());
													else
														stud.setHostelMode("D");
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getIsJoinedThroughAdmissionStr())){
														if("Y".equalsIgnoreCase(studentVoDetails.getIsJoinedThroughAdmissionStr()))
															stud.setJoinedThroughAdmissions(true);
														else
															stud.setJoinedThroughAdmissions(false);	
													}else{
														stud.setJoinedThroughAdmissions(false);	
													}
													//stud.setTransportMode(studentVoDetails.getTransportMode());
													stud.setAcademicYear(academicYear);
													Student rollNumberStudent =null;
													StringBuffer sqlQuery=null;
													if(!StringFunctions.isNullOrEmpty(studentVoDetails.getRollNumberStr())){
														sqlQuery = new StringBuffer(" classSectionId="+ studyClass.getId() + " and rollNumber = '"+studentVoDetails.getRollNumberStr()+"'and status='"+Constants.YES_STRING+"'");
														if (!StringFunctions.isNullOrEmpty(studentVoDetails.getStuId())) {
															sqlQuery.append(" and id <> " + studentVoDetails.getStuId());
														}
														rollNumberStudent = (Student)adminManager.get(Student.class,sqlQuery.toString());
													}
													if("N".equalsIgnoreCase(customer.getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(customer.getAlphaNumericRollNumber()) )	{
														if(StringFunctions.isNotNullOrEmpty(studentVoDetails.getRollNumberStr()) && studentVoDetails.getRollNumberStr().matches("[0-9]+") && !"0".equals(studentVoDetails.getRollNumberStr()))
														{
															if(ObjectFunctions.isNullOrEmpty(rollNumberStudent))
															{
																stud.setRollNumber(studentVoDetails.getRollNumberStr());
															}
															else
															{
																duplicateRollNumberMessageBuffer.append(studentVoDetails.getRollNumberStr() +", " );
																Row row=firstSheet.getRow(sheetRowCount);
																Cell cell = (Cell) row.getCell(3);														
																cell.setCellStyle(hlink_style);	
																continue;
															}
															rollNumberStudent=null;
															sqlQuery=null;
														}
														else{
															stud.setRollNumber("0");
															rollNumberNotAssignedClasses.add(studyClass.getId());
														}
													}
													else{
														if(ObjectFunctions.isNullOrEmpty(rollNumberStudent))
															stud.setRollNumber(studentVoDetails.getRollNumberStr());
														else
														{
															duplicateRollNumberMessageBuffer.append(studentVoDetails.getRollNumberStr() +", " );
															Row row=firstSheet.getRow(sheetRowCount);
															Cell cell = (Cell) row.getCell(3);														
															cell.setCellStyle(hlink_style);	
															continue;
														}
														rollNumberStudent=null;
														sqlQuery=null;
													}
														
													if (StringFunctions.isNullOrEmpty(studentVoDetails.getStuId())) {
														//stud.setRollNumber(generateStudnetRollNumber(studyClass.getId(),studyClass.getCustId(),studyClass.getAcademicYearId()));
														stud.setClassSection(studyClass);
														stud.setClassNameClassId(className);
													} else if(studyClass.getClassNameClassId().getId() == stud.getClassNameClassId().getId()){
														if ((studyClass.getId() != stud.getClassSection().getId()) && !isUploadStudentMarks) {
															stud.setShippedSection("Section Changed from "+ stud.getClassAndSection() +" to " + studentVoDetails.getClassAndSection());
															stud.setClassSection(studyClass);
															stud.setClassNameClassId(className);
														}
													} else if(studyClass.getId() != stud.getClassSection().getId()){
															if("N".equalsIgnoreCase(stud.getFeePaidStatus())){
																stud.setShippedSection("Section Changed from "+ stud.getClassAndSection() +" to " + studentVoDetails.getClassAndSection());
																stud.setClassSection(studyClass);
																stud.setClassNameClassId(className);
															}
															else{
																feepaidAdmissinNumber.append(studentVoDetails.getAdmissionNumber()+ ", ");
															}
													}
													Fee fee = null;
													double committedConfiguredFee = 0;
													if("AdmissionCreate".equalsIgnoreCase(getEventId())){
														updateStudentOnlineApplicaton(stud,customer);
														stud.setJoinedThroughAdmissions(true);
														admissionStr = getEventId();
													}
													
													if ("N".equalsIgnoreCase(stud.getFeePaidStatus()) || "C".equalsIgnoreCase(stud.getFeePaidStatus())) {
														prepareStudentSchoolFeeSettingListFromVo(studentVo);
														List<String> schoolTermIds = studentManager.getAll("select id from schoolTerms where custId="+custId+" and academicYearId="+academicYearId+" and feeSettingId in "+getTempString());
														if(ObjectFunctions.isNullOrEmpty(schoolTermIds))
															schoolTermIds.add("0");
														if (!ObjectFunctions.isNullOrEmpty(studentVoDetails.getCategoryName())) {
															fee = (Fee) studentManager.get(Fee.class,"classId="+ className.getId()+ " and categoryId="+ schoolCategoryMap.get(studentVoDetails.getCategoryName()).getId()+ " and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
															stud.setCategoryId(schoolCategoryMap.get(studentVoDetails.getCategoryName()).getId());
														} else {
															fee = (Fee) studentManager.get(Fee.class,"classId="+ className.getId()+ " and categoryId="+ schoolCategoryMap.get("General").getId()+ " and feeAmount != 0 and schoolTermId in ("+StringUtil.convertListToString(schoolTermIds)+")");
															stud.setCategoryId(schoolCategoryMap.get("General").getId());
														}
														if (!ObjectFunctions.isNullOrEmpty(fee))
															stud.setFeeConfigured(Constants.YES_STRING);
														else
															stud.setFeeConfigured(Constants.NO_STRING);
														stud.setCommittedFee(0.0);
														fee=null;
														schoolTermIds=null;
													}
													studyClassIdsSet.add(stud.getClassSection());
													
													stud.setSchoolMess("N");
													stud.setPtaStatus("N");
													
													
													if("Yes".equalsIgnoreCase(studentVoDetails.getBplStatus())){
														stud.setBplStatus("Y");
														stud.setBplNumber(studentVoDetails.getBplNumber());
											    	}else {
											    		stud.setBplStatus("N");
													}
													
													if("Yes".equalsIgnoreCase(studentVoDetails.getRteStatus())){
														stud.setRteStatus("Y");
											    	}else {
											    		stud.setRteStatus("N");
													}
													
													Student studObject  = (Student) studentManager.save(stud);
													removeRows.put(sheetNo+"_"+sheetRowCount, sheetRowCount);
													//Sending notification for student profile update
													//studentManager.sendNotificationForStudentUpdate(studObject);
													//Sending notification for student profile update
													studentObjList.add(studObject);
													if(customer.isCheckMobileService() || customer.isCheckEmailService()){
														if(!ObjectFunctions.isNullOrEmpty(stud.getAccount().getStudentParent())){
															User parentUserObject = (User) adminManager.get(User.class,Long.valueOf(stud.getAccount().getStudentParent().getId()));
															//Added by Siva on making parent mobile number as username on 13 JUN 2016
															// Comment this code for remove the entire system in +91- before added the mobile number. RaviTeja 25-07-2016 
															String newPassword = StringUtil.generateRandomString();
															if(StringFunctions.isNotNullOrEmpty(mobileNumber)){
																User parentAccountNew =adminManager.getUserEmailByUserName(mobileNumber);
																if(ObjectFunctions.isNullOrEmpty(parentAccountNew))
																{
																	if(!mobileNumber.equalsIgnoreCase(parentUserObject.getUsername())){
																		parentUserObject.setUsername(mobileNumber);
																		parentUserObject.setSecondaryUsername(String.valueOf(parentUserObject.getId()));
																		parentUserObject.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
																		parentUserObject = (User)studentManager.saveOrUpdateObject(parentUserObject);
																		String updatedAdmissionNo = "Yes";
																		
																		String key = mobileNumber+"$"+secondaryMobileNumber+"$"+studMobileNumber+"$"+updatedAdmissionNo+"$"+newPassword;
																		
																		List sendSmsParentList = new ArrayList();
																		sendSmsParentList.add(parentUserObject.getPerson());
																		sendSmsParentList.add(parentUserObject);
																		sendSmsParentList.add(studObject);
																		sendSmsParentList.add(getUser());
																		
																		sendSmsForParentsAndStudentMap.put(key, sendSmsParentList);
																		
																	}
																}
																parentAccountNew = null;
															}
															//end
															
															if(customer.isCheckMobileService()){
																 if((allottedSmsCount!=0 && allottedSmsCount > sentSmsCount) && stud.getId() > 0 && !"AdmissionCreate".equalsIgnoreCase(admissionStr) && ("Yes".equalsIgnoreCase(updatedAdmissionStatus))){
																	 if(!ObjectFunctions.isNullOrEmpty(stud.getAccount().getStudentParent())){
																		if(!ObjectFunctions.isNullOrEmpty(parentUserObject)){
																			String updatedAdmissionNo = null;
																			updatedAdmissionNo = updatedAdmissionStatus;
																			
																			String key = mobileNumber+"$"+secondaryMobileNumber+"$"+studMobileNumber+"$"+updatedAdmissionNo+"$"+newPassword;
																			
																			List sendSmsParentList = new ArrayList();
																			sendSmsParentList.add(parentUserObject.getPerson());
																			sendSmsParentList.add(parentUserObject);
																			sendSmsParentList.add(studObject);
																			sendSmsParentList.add(getUser());
																			
																			sendSmsForParentsAndStudentMap.put(key, sendSmsParentList);
																		}
																	 }
																 }	
															 }
															if(customer.isCheckEmailService() && stud.getId() > 0 && !"AdmissionCreate".equalsIgnoreCase(admissionStr)){
																 String updatedAdmissionNo = null;
																 if("Yes".equalsIgnoreCase(updatedAdmissionStatus)){
																		updatedAdmissionNo = updatedAdmissionStatus;
																		
																	String key= null;
																		
																	 if(!ObjectFunctions.isNullOrEmpty(studentVoDetails.getStudentEmail()))
																	 {
																		 key = studentVoDetails.getStudentEmail()+"$"+studentVoDetails.getFullPersonName()+"$"+stud.getClassAndSection()+"Students$"+"";
																		 sendEmailToParentsAndStudentMap.put(key, studObject.getAccount());
																	 }
																		
																	 if(!ObjectFunctions.isNullOrEmpty(studentVoDetails.getParentEmail()))
																	 {
																		 key = studentVoDetails.getParentEmail()+"$"+studentVoDetails.getFullPersonName()+"$"+stud.getClassAndSection()+"Parents$"+parentUserObject.getUsername();
																		 sendEmailToParentsAndStudentMap.put(key, studObject.getAccount());
																	 }
																		
																 }
															}
														}
													}
													
													if (StringFunctions.isNullOrEmpty(studentVoDetails.getStuId()) && "Y".equalsIgnoreCase(customer.getBarcodeStatus())) {
														//here generate the barcode for each student used in id cards 
														//generateBarcodeForStudent(account.getId());
														adminManager.generateBarcode(account.getId());
													}
										 }else {
												// Provide message to user regd admission number is mandatory
											log.debug("**** Admission Number missed *******");
											//super.addActionError("Please add mandotory fields for the student");
											/*if(!StringFunctions.isNullOrEmpty(lastName))
												mandatoryErrorMessageBuffer.append(firstName + " " +lastName +", " );
											else
												mandatoryErrorMessageBuffer.append(firstName +", " );*/
											cellNumber.append(countNo+",");
										}
												
									countNo++;
									stud = null;
									account = null;
									person = null;
									address = null;
									//studentVoDetails = null;
									rowCount=2;
								} catch (Exception e) {
									e.printStackTrace();
									continue;
								}								
							}
							
						}else {
							super.addActionError("Few student(s) data not valid. Please verify the data.");
						}
	
					} catch (Exception ex) {
						ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
						continue;
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(sendEmailToParentsAndStudentMap))
				{
					SendEmailToParentsAndStudentThread R1 = new SendEmailToParentsAndStudentThread(sendEmailToParentsAndStudentMap,customer);
				    R1.start();
				}
				
				if(!ObjectFunctions.isNullOrEmpty(sendSmsForParentsAndStudentMap))
				{
					SendSmsForParentsAndStudentThread R1 = new SendSmsForParentsAndStudentThread(sendSmsForParentsAndStudentMap,customer,smsServiceProvider);
				    R1.start();
				}
				
				//Sending notification for student profile update
				if(!ObjectFunctions.isNullOrEmpty(studentObjList))
				{
					SendNotificationForStudentUpdateThread R1 = new SendNotificationForStudentUpdateThread(studentObjList);
				    R1.start();
				}
				
				if(!ObjectFunctions.isNullOrEmpty(entryStudentCommittedPaymentMap))
				{
					EntryStudentCommittedPaymentThread R1 = new EntryStudentCommittedPaymentThread(entryStudentCommittedPaymentMap,customer);
				    R1.start();
				}
				
				sendEmailToParentsAndStudentMap = null;
				sendSmsForParentsAndStudentMap = null;
				studentObjList = null;
				entryStudentCommittedPaymentMap = null;
				
				
				if(rollNumberNotAssignedClasses.size() > 0){
					List<Student> studentDetails=null;
					for(Long classSectionId : rollNumberNotAssignedClasses){
						studentDetails = studentManager.getAll(Student.class,"classSectionId= "+classSectionId);
						Collections.sort(studentDetails,new StudentRollNumberComparator());
						Student st=studentDetails.get(studentDetails.size()-1);
						if(!ObjectFunctions.isNullOrEmpty(st) && !ObjectFunctions.isNullOrEmpty(st.getRollNumber())){
							adminManager.addStudentRollNumbersForNonAssignedStudentsByClassSectionId(classSectionId,Integer.valueOf(st.getRollNumber()));
						}
						studentDetails = null;
					}
				}
				
				if(mandatoryErrorMessageBuffer.length() > 0)
	  		    {
					mandatoryErrorMessageBuffer.insert(0,"Few student(s) data not valid. Please verify the data:");
	  		    	super.addActionError(mandatoryErrorMessageBuffer.toString().substring(0,mandatoryErrorMessageBuffer.toString().length()-1));
	  		    }
				if(enrollmentCodeMessageBuffer.length() > 0)
	  		    {
					enrollmentCodeMessageBuffer.insert(0,"Few student(s) enrollment data already exists. Please verify the data:");
	  		    	super.addActionError(enrollmentCodeMessageBuffer.toString().substring(0,enrollmentCodeMessageBuffer.toString().length()-1));
	  		    }
				if(castNameMessageBuffer.length() > 0)
	  		    {
					castNameMessageBuffer.insert(0,"The following admission number(s) students caste name is not matched with respect to community:");
	  		    	super.addActionError(castNameMessageBuffer.toString().substring(0,castNameMessageBuffer.toString().length()-1));
	  		    }
				
				if(fatherAadharNumberMessageBuffer.length() > 0)
	  		    {
					fatherAadharNumberMessageBuffer.insert(0,"The following admission number(s) students father aadhar number is not valid:");
	  		    	super.addActionError(fatherAadharNumberMessageBuffer.toString().substring(0,fatherAadharNumberMessageBuffer.toString().length()-1));
	  		    }
				
				if(motherAadharNumberMessageBuffer.length() > 0)
	  		    {
					motherAadharNumberMessageBuffer.insert(0,"The following admission number(s) students mohter aadhar number is not valid:");
	  		    	super.addActionError(motherAadharNumberMessageBuffer.toString().substring(0,motherAadharNumberMessageBuffer.toString().length()-1));
	  		    }
				if(duplicateRollNumberMessageBuffer.length() > 0)
	  		    {
					duplicateRollNumberMessageBuffer.delete(duplicateRollNumberMessageBuffer.length()-1, duplicateRollNumberMessageBuffer.length());
					duplicateRollNumberMessageBuffer.insert(0,"Roll number(s) already exist:");
	  		    	super.addActionError(duplicateRollNumberMessageBuffer.toString().substring(0,duplicateRollNumberMessageBuffer.toString().length()-1));
	  		    }
				if(mobileNumberNumberLengthMsg.length() > 0)
	  		    {
					mobileNumberNumberLengthMsg.insert(0,"The following admission number(s) students primary mobile numbers is not valid:");
					super.addActionError(mobileNumberNumberLengthMsg.toString().substring(0,mobileNumberNumberLengthMsg.toString().length()-1));
	  		    }
				
				if(feepaidAdmissinNumber.length() !=0){
					feepaidAdmissinNumber.insert(0,"The following admission number(s) already paid fee.So you can not change class for this students.");
					super.addActionError(feepaidAdmissinNumber.toString());
				}
				if (ObjectFunctions.isNotNullOrEmpty(classNames) || admissionNumberExistMsg.length() != 0) {
					StringBuffer failureMsg = new StringBuffer(StringUtil.convertSetToString(classNames));
					if (failureMsg.length() != 0) {
						failureMsg.delete(failureMsg.length() - 2, failureMsg.length());
						failureMsg.insert(0,"The following class(es) is(are) not available.");
					}
					if (ObjectFunctions.isNotNullOrEmpty(classNames) && admissionNumberExistMsg.length() != 0) {
						admissionNumberExistMsg.delete(admissionNumberExistMsg.length() - 2, admissionNumberExistMsg.length());
						admissionNumberExistMsg.insert(0,"The following admission number(s) is(are) already available.");
						super.addActionError(admissionNumberExistMsg.toString()+ "\n" + failureMsg.toString());
					} else if (ObjectFunctions.isNotNullOrEmpty(classNames)) {
						super.addActionError(failureMsg.toString());
					} else if(cellNumber.length() > 0){
						cellNumber.append("The fallowing row number(s) are not inserted because in this rows don't have mandatery values.");
						super.addActionError(cellNumber.toString());
					}
					else {
						admissionNumberExistMsg.delete(admissionNumberExistMsg.length() - 2, admissionNumberExistMsg.length());
						admissionNumberExistMsg.insert(0,"The following admission number(s) is(are) already available.");
						super.addActionError(admissionNumberExistMsg.toString());
					} 
					failureMsg = null;
				}
				if(!ObjectFunctions.isNullOrEmpty(studyClassIdsSet))
				{
					for(StudyClass studyCLass : studyClassIdsSet)
					{
						ajaxUpdateClassSectionAndFilledSeatsCapacity(studyCLass);
						studyCLass = null;
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(removeRows)){	
					if(removeRows.size() > 0){
						String[] key=null;
						Sheet removeSheet=null;
					for (Map.Entry<String, Integer> entry : removeRows.entrySet()) {
						key=entry.getKey().split("_");
						removeSheet=workbook1.getSheetAt(Integer.valueOf(key[0]));
					    removeRow(removeSheet,entry.getValue());
					  }
					}
				}
				removeRows=null;
				workbook1.write(fileOut);
		        fileOut.flush();
				fileOut.close();
		        inputStream.close();
		        
				if(commitFeeStr.length() !=0)
					super.addActionError(commitFeeStr.toString()+ "\n The following admission number(s) committed fee is not saved.");
				
				if("Create".equalsIgnoreCase(getEventId()) && !StringFunctions.isNullOrEmpty(missingMandatoryValues)){
					super.addActionMessage("Student details created successfully.");
					return "createSuccess";
				}else if ("AdmissionCreate".equalsIgnoreCase(getEventId())) {
					if("Edit".equalsIgnoreCase(getAnyTitle()))
						super.addActionMessage("Student details updated successfully.");
					else
						super.addActionMessage("Student details created successfully.");
				}else if(!"Create".equalsIgnoreCase(getEventId())) {
					super.addActionMessage("Student details updated successfully.");
				}
				// ajaxGetStudyClassList();
				getSession().removeAttribute("GetAllStudyClasses");
				
				
				
				studyClassIdsSet = null;
				studentDetailsList = null;
				commonType = null;
				castSettingObj = null;
				subCastSettingObj = null;
				customer = null;
				academicYear = null;
				studentRole = null;
				//category = null;
				studyClassMap = null;
				studyClasses = null;
				castSettingsMap = null;
				subCastSettingMap = null;
				admissionNumberExistMsg = null;
				classNames = null;
				schoolCategoryMap = null;
				categoryList = null;
			}else{
				log.debug("Uploaded wrong file..");
				super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally
		{
			checkStudyClassHavingStudentsOrNot();
		}
		log.debug("******************** end method *******************");
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDownloadStudent", results = {}) })
	public void ajaxDownloadStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadStudent' method");
		}
		try {
			if (getUserAcademicYearId() != 0 && StringFunctions.isNotNullOrEmpty(getSelectedId())) {
				String[] words = { "Own", "Private", "School Tranport" };
				Arrays.asList(words);
				
				PrepareStudentExcel prepareStudentExcel = new PrepareStudentExcel();
				List<Object[]> studentDetails = null;
				List<ViewClassSectionDetails> classSections = null;
				List<StudyClass> studyClasses = null;
				if("AdmissionProcess".equals(getAnyTitle()))
					classSections = studentManager.getAll(ViewClassSectionDetails.class," academicYearId = " + getAcademicYearId() + " and custId = " + getUserCustId()+ " order by sortingOrder asc");
				else
					classSections = studentManager.getAll(ViewClassSectionDetails.class," academicYearId = " + getUserAcademicYearId()+ " and custId = " + getUserCustId()+ " order by sortingOrder asc");
				List<State> states = (List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST);
				List<MotherTongue> motherTongues = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
				List<SchoolCategory> schoolCategory = studentManager.getAll(SchoolCategory.class," custId = " + getUserCustId());
				
				//List<CastSettings> communityList = studentManager.getAllByCustId("CastSettings", getUserCustId(),0);
				List<CastSettings> communityList = studentManager.getAll(CastSettings.class, "custId="+getUserCustId()+" order by castName");
				List<SubCastSettings> castNameList = adminManager.getAll(SubCastSettings.class,"custId="+getUserCustId()+" order by subCastName");
				List<HouseType> houseTypeList =  studentManager.getAll(HouseType.class);
				if("AdmissionProcess".equals(getAnyTitle())){
					prepareStudentExcel.createClassSectionSheet("ClassSections",classSections, states,motherTongues,getAcademicYearId() ,getUserCustId(),schoolCategory,communityList,castNameList,houseTypeList);
					studyClasses = studentManager.getAll(StudyClass.class, "classNameClassId in " + getSelectedId()+" and custId="+getUserCustId()+" and academicYearId="+getAcademicYearId());
				}else{
					prepareStudentExcel.createClassSectionSheet("ClassSections",classSections, states,motherTongues,getUserAcademicYearId(),getUserCustId(),schoolCategory,communityList,castNameList,houseTypeList);
					studyClasses = studentManager.getAll(StudyClass.class, "id in " + getSelectedId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
				}
				StringBuffer sheetTitleDesc = new StringBuffer();
				sheetTitleDesc.append("School Name : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("organization"));
				}
				sheetTitleDesc.append(", Academic Year : ");
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName"))) {
					sheetTitleDesc.append((String) getSession().getAttribute("academicYearName"));

				}
				String fileName = null;
				if (ObjectFunctions.isNotNullOrEmpty(studyClasses)) {
					for (StudyClass studyClass : studyClasses) {
						if("AdmissionProcess".equals(getAnyTitle())){
							studentDetails = studentManager.getAll(PrepareStudentExcel.query+ " where classSectionId="+ studyClass.getId()+ "  and custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and joinedThroughAdmissions='Y' and description is null order by IF((registerNumber IS NULL or registerNumber = ''),fullName,registerNumber)");
						}else{
							studentDetails = studentManager.getAll(PrepareStudentExcel.query+ " where classSectionId="+ studyClass.getId()+ "  and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and description is null order by "+getTempString1()+" ");
						    if(("N".equalsIgnoreCase(getCustomerByCustId().getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(getCustomerByCustId().getAlphaNumericRollNumber())) && "rollNumber".equalsIgnoreCase(getTempString1()))
							  Collections.sort(studentDetails,new StudentRollNumberComparator());
						}

						if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
							
							fileName = "Edit_Student_Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
							
							prepareStudentExcel.createStudentSheet(studyClass.getClassAndSection(), studentDetails,sheetTitleDesc.toString(),getCustomerByCustId().getCommittedFeeStatus());
						}
					}
				} else {
					fileName = "Import_Student_Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
					prepareStudentExcel.createStudentSheet("Student",studentDetails, sheetTitleDesc.toString(),getCustomerByCustId().getCommittedFeeStatus());
				}
				
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				
				prepareStudentExcel.finalPrep("ClassSections", studentDetails);
				prepareStudentExcel.getWb().write(getResponse().getOutputStream());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	
	public String ajaxUploadAdmissionStudentData() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUploadAdmissionStudentData' method");
		}
		try {
			log.debug(getEventId());
			log.debug(getUploadContentType());
			boolean excelFileType = false;
			
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
			
			
		     	 
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("ClassSections");
			long academicYearId = 0;
			long custId = 0;
			if (ObjectFunctions.isNullOrEmpty(sheet)) {
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				ajaxGetStudyClassList();
				return "dummyInit";
				
			} else {
				
				org.apache.poi.ss.usermodel.Sheet firstSheet1 = workbook.getSheetAt(0);
				Row secondRow = firstSheet1.getRow(1);
				if(!ObjectFunctions.isNullOrEmpty(secondRow))
				{
					if(!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1)))
					{
						String fistColumn = secondRow.getCell(0).getStringCellValue();
						String secondColumn = secondRow.getCell(1).getStringCellValue();
						
						if(!"Student Id".equalsIgnoreCase(fistColumn.toString()) || !"Admission Number".equalsIgnoreCase(secondColumn.toString()))
						{
							log.debug("No file to upload....");
							super.addActionError("File type not matched.");
							ajaxGetStudyClassList();
							return "dummyInit";
						}
						fistColumn = null;
						secondColumn = null;
					}
					else
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						ajaxGetStudyClassList();
						return "dummyInit";
					}
				}
				else
				{
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
					ajaxGetStudyClassList();
					return "dummyInit";
				}
				
				
				Row row = sheet.getRow(0);
				academicYearId = (long) row.getCell(1).getNumericCellValue();
				row = sheet.getRow(1);
				custId = (long) row.getCell(1).getNumericCellValue();
			}
			if(!"AdmissionCreate".equalsIgnoreCase(getEventId())){
				if(!isCurrentAcademicYearId(academicYearId)) {
					log.debug("Uploaded wrong file..");
					super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
					ajaxGetStudyClassList();
					return "dummyInit";
				}
			}	
			if(custId !=0){
				String missingMandatoryValues = null;
				OnlineApplicationDetails onlineApplicationDetails = null;
				String admissionStr =null;
				List<Student> studentDetailsList = null;
				List<ExcelStudentVO> studentVoDetailsList = null;
				boolean isChangedAdmissionNo = true;
				StringBuffer admissionNumberExistMsg = new StringBuffer();
				StringBuffer feepaidAdmissinNumber = new StringBuffer();
				StringBuffer errorMessageBuffer = new StringBuffer();
				StringBuffer enrollmentCodeMessageBuffer = new StringBuffer();
				StringBuffer cellNumber = new StringBuffer();
				StringBuffer commitFeeStr = new StringBuffer();
				Long settingsId = null;
				CommonType commonType = null;
				CastSettings castSettingObj = null;
				SubCastSettings subCastSettingObj = null;
				ClassName className = null;
				StudyClass studyClass = null;
				double deductingAmnt = 0;
				double previousCommittedFee = 0;
				Set<String> classNames = new HashSet<String>();
				Set<StudyClass> studyClassIdsSet = new HashSet<StudyClass>();
				Set<Long> rollNumberNotAssignedClasses = new HashSet<Long>();
				Customer customer = (Customer) studentManager.get(Customer.class,"id=" + custId);
				
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, getAcademicYearId());
				
				Role studentRole = adminManager.getRoleByName(Constants.SCHOOL_STUDENT);
				//SchoolCategory category = adminManager.getCategoryIdByCustId("General", custId);
				HashMap<String, SchoolCategory> schoolCategoryMap = new HashMap<String, SchoolCategory>();
				List<SchoolCategory> categoryList = studentManager.getAll(SchoolCategory.class,"custId="+getUserCustId());
				if (ObjectFunctions.isNotNullOrEmpty(categoryList)) {
					for (SchoolCategory categoryObj : categoryList) {
						schoolCategoryMap.put(categoryObj.getCategoryName(),categoryObj);
					}
				}
				HashMap<String, StudyClass> studyClassMap = new HashMap<String, StudyClass>();
				List<StudyClass> studyClasses = studentManager.getAll(StudyClass.class, "custId=" + custId +" and academicYearId=" + academicYearId);
				StringBuffer classObj = null;
				if (ObjectFunctions.isNotNullOrEmpty(studyClasses)) {
					for (StudyClass studyClas : studyClasses) {
						classObj = new StringBuffer();
						if(!ObjectFunctions.isNullOrEmpty(studyClas.getClassName())){
							classObj.append(studyClas.getClassName());
						}
						if(!ObjectFunctions.isNullOrEmpty(studyClas.getSection())){
								classObj.append("-"+studyClas.getSection());
						}
						studyClassMap.put(classObj.toString(),studyClas);
					}
					classObj = null;
				}
				
				HashMap<String, CastSettings> castSettingsMap = new HashMap<String, CastSettings>();
				List<CastSettings> castSettings = studentManager.getAll(CastSettings.class, "custId=" + custId);
				if (ObjectFunctions.isNotNullOrEmpty(castSettings)) {
					for (CastSettings castSetting : castSettings) {
						castSettingsMap.put(castSetting.getCastName().toLowerCase(),castSetting);
					}
				}
				HashMap<String, SubCastSettings> subCastSettingMap = new HashMap<String, SubCastSettings>();
				List<SubCastSettings> subcastSettings = studentManager.getAll(SubCastSettings.class, "custId=" + custId);
				if (ObjectFunctions.isNotNullOrEmpty(subcastSettings)) {
					for (SubCastSettings subCast : subcastSettings) {
						subCastSettingMap.put(subCast.getSubCastName().toLowerCase(), subCast);
					}
				}
				HashMap<String, CommonType> religionsMap = new HashMap<String, CommonType>();
				HashMap<String, Long> motherToungsMap = new HashMap<String, Long>();
				List<CommonType> commonTypeList = studentManager.getAll(CommonType.class, "custId=" + custId+ " and type in ('RELIGION')");
				if (ObjectFunctions.isNotNullOrEmpty(commonTypeList)) {
					for (CommonType commontype : commonTypeList) {
							religionsMap.put(commontype.getSkillTypeName().toLowerCase(),commontype);
					}
				}
				List<MotherTongue> motherTongueList = (List<MotherTongue>)SMSLookUpDataCache.lookUpDataMap.get(Constants.MOTHER_TONGUE_LIST);
				if (ObjectFunctions.isNotNullOrEmpty(motherTongueList)) {
					for (MotherTongue motherTongueObj : motherTongueList) {
							motherToungsMap.put(motherTongueObj.getName(),motherTongueObj.getId());
					}
				}
				statelist = studentManager.getCountryStates(0);
				CommonType commonTypes =(CommonType)studentManager.get(CommonType.class,"custId="+getUserCustId()+" and skillTypeName like '%"+"Other"+"%' and type like '%"+"RELIGION"+"%' ");
				CastSettings castsettings =(CastSettings)studentManager.get(CastSettings.class,"custId ="+getUserCustId()+" and castName like '%"+"Other"+"%' ");
				String userName = null;
				User objUser= null;
				int loopCount=1;
				
				Sheet firstSheet=null;
				
				HashMap<String, Integer> removeRows = new HashMap<String, Integer>();
				
				Map<String, User> sendEmailToParentsAndStudentMap = new HashMap<String, User>();
				Map<String, List> sendSmsForParentsAndStudentMap = new HashMap<String, List>();
				List<Student> studentObjList = new ArrayList<Student>();
				Map<String, Student> entryStudentCommittedPaymentMap = new HashMap<String, Student>();
				
				int allottedSmsCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
				
				for (int sheetNo = 0; sheetNo < (workbook.getNumberOfSheets() - 1); sheetNo++) {
					sheet = workbook.getSheetAt(sheetNo);
					int sheetRowCount=1;			
					int rowCount=2;
					SheetParser parser = new SheetParser();
					try {
						studentVoDetailsList = parser.createEntity(sheet, sheet.getSheetName(), ExcelStudentVO.class);
						long countNo = 0;
						
						if (ObjectFunctions.isNotNullOrEmpty(studentVoDetailsList)) {
							for (ExcelStudentVO studentVoDetails : studentVoDetailsList) {
								log.debug(studentVoDetails.getAdmissionNumber()); 
									 if (!ObjectFunctions.isNullOrEmpty(studentVoDetails)
												&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getAdmissionNumber())
												&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getClassAndSection())
												&& StringFunctions.isNotNullOrEmpty(studentVoDetails.getFirstName())) {
											log.debug("***************** " + studentVoDetails.getFirstName() + "***************** " );
											
											sheetRowCount++;											
											
											UserVO accountVo = new UserVO();
											PersonVO personVO = new PersonVO();
											AddressVO primaryAddressVo = new AddressVO();
											
											StudentVo studentVo = new StudentVo();
											AcademicYearVo academicYearVo = new AcademicYearVo();
											academicYearVo.setId(getUserAcademicYearId());
											
												 if(loopCount > 4) break;
												 if(ObjectFunctions.isNullOrEmpty(sheet.getRow(rowCount++))){
													loopCount++;
													continue;
												  }
												loopCount=1;
												String mobileNumber = null;
												String secondaryMobileNumber = null;
												String studMobileNumber = null;
												isChangedAdmissionNo = true;
												missingMandatoryValues = "missed";
												
												int sentSmsCount=adminManager.getTotalSmsCount(customer.getId(),academicYear.getId());
												
												studyClass = studyClassMap.get(studentVoDetails.getClassAndSection());
												if (ObjectFunctions.isNullOrEmpty(studyClass)) {
													classNames.add(studentVoDetails.getClassAndSection()+ ", ");
													continue;
												} else {
													className = studyClass.getClassNameClassId();
													if (ObjectFunctions.isNullOrEmpty(className)) {
														classNames.add(studentVoDetails.getClassAndSection()+ ", ");
														continue;
													}
												}
												String admisionNo = null;
												String oldEmail = null;
												String updatedAdmissionStatus = null;
												String studStatus = null;
												if (!StringFunctions.isNullOrEmpty(studentVoDetails.getStuId())) {
													
												} else {
													log.debug("new student generation");
													onlineApplicationDetails = new OnlineApplicationDetails();
													
													onlineApplicationDetails.setCreatedById(getUser().getId());
													onlineApplicationDetails.setCreatedDate(new Date());
													onlineApplicationDetails.setCustId(getUserCustId());
													
												}
	
												
												if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getReligion())) {
													commonType = religionsMap.get(studentVoDetails.getReligion().trim().toLowerCase());
													if (ObjectFunctions.isNullOrEmpty(commonType)) {
														commonType = new CommonType();
														commonType.setCustId(custId);
														commonType.setSkillTypeName(studentVoDetails.getReligion().trim().toUpperCase());
														commonType.setType("RELIGION");
														//commonType.setVersion(0);
														commonType = (CommonType) studentManager.saveOrUpdateObject(commonType);
														religionsMap.put(commonType.getSkillTypeName().toLowerCase(),commonType);
													}
													onlineApplicationDetails.setReligionId(commonType);
													onlineApplicationDetails.setReligion(commonType.getSkillTypeName());
												}else{
													if(!ObjectFunctions.isNullOrEmpty(commonTypes)){
														onlineApplicationDetails.setReligionId(commonTypes);
														onlineApplicationDetails.setReligion(commonTypes.getSkillTypeName());
													}
												}
												
												if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getMotherToung())) {
													settingsId = motherToungsMap.get(studentVoDetails.getMotherToung().trim());
													if(!ObjectFunctions.isNullOrEmpty(settingsId))
														onlineApplicationDetails.setMotherToungId(settingsId);
												} else
													onlineApplicationDetails.setMotherToungId(0);
												
												if("Yes".equalsIgnoreCase(studentVoDetails.getPhIdStr())){
													onlineApplicationDetails.setPhId(true);
													onlineApplicationDetails.setPhysicallyHandicappedDesc(studentVoDetails.getPhysicallyHandicappedDesc());
												}else{
													onlineApplicationDetails.setPhId(false);
													onlineApplicationDetails.setPhysicallyHandicappedDesc(null);
													
												}
												onlineApplicationDetails.setIsPHDocsUploaded("N");
												
												onlineApplicationDetails.setTransportMode(studentVoDetails.getTransportMode());
												onlineApplicationDetails.setBoardingPointId(Long.valueOf(0));
												onlineApplicationDetails.setVehicleAcademicDetailsId(Long.valueOf(0));
												
												
												String applicationNumber = studentManager.getApplicationNumber(customer,academicYear);
												onlineApplicationDetails.setApplicationNumber(applicationNumber);
												onlineApplicationDetails.setStatus("P");
												onlineApplicationDetails.setAcademicYear(academicYear);
												onlineApplicationDetails.setCustId(customer.getId());
												onlineApplicationDetails.setAddressType("R");
												if("Yes".equalsIgnoreCase(studentVoDetails.getBplStatus()))
												{
													onlineApplicationDetails.setBplStatus("Y");
													onlineApplicationDetails.setBplNumber(studentVoDetails.getBplNumber());
												}
												else
													onlineApplicationDetails.setBplStatus("N");
												
												if("Yes".equalsIgnoreCase(studentVoDetails.getRteStatus()))
													onlineApplicationDetails.setRteStatus("Y");
												else
													onlineApplicationDetails.setRteStatus("N");
												
												
												Address primaryAddress = new Address();
												primaryAddressVo.setAddressLine1(studentVoDetails.getAddressLine1());
												primaryAddressVo.setAddressLine2(studentVoDetails.getAddressLine2());
												primaryAddressVo.setCity(studentVoDetails.getCity());
												primaryAddressVo.setDistrictName(studentVoDetails.getDistrictName());
												primaryAddressVo.setState(studentVoDetails.getState());
												primaryAddressVo.setPostalCode(studentVoDetails.getPostalCode());
												primaryAddress.copyFromVoToEntity(primaryAddress,primaryAddressVo);
												
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getState()))
													primaryAddress.setStateId(statelist.get(studentVoDetails.getState()).getId());
												onlineApplicationDetails.setPrimaryAddress(primaryAddress);
	
												if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getCommunity())|| StringFunctions.isNotNullOrEmpty(studentVoDetails.getCastName())) {
													if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getCommunity())) {
														castSettingObj = castSettingsMap.get(studentVoDetails.getCommunity().trim().toLowerCase());
														if (ObjectFunctions.isNullOrEmpty(castSettingObj)) {
															castSettingObj = new CastSettings();
															castSettingObj.setCustId(custId);
															castSettingObj.setCastName(studentVoDetails.getCommunity().trim().toUpperCase());
															castSettingObj.setCreatedById(getUser().getId());
															castSettingObj.setCreatedDate(new Date());
															castSettingObj = (CastSettings) studentManager.saveOrUpdateObject(castSettingObj);
															castSettingsMap.put(castSettingObj.getCastName().toLowerCase(),castSettingObj);
														}
														//studentVoDetails.getAccount().getPersonVo().setCastId(castSettingObj.getId());
														onlineApplicationDetails.setCastId(castSettingObj);
													}else{
														if(!ObjectFunctions.isNullOrEmpty(castsettings)){
															//studentVoDetails.getAccount().getPersonVo().setCastId(castsettings.getId());
															onlineApplicationDetails.setCastId(castsettings);
														}
													}
													if (StringFunctions.isNotNullOrEmpty(studentVoDetails.getCastName())) {
														
														subCastSettingObj = subCastSettingMap.get(studentVoDetails.getCastName().toLowerCase());
														if (ObjectFunctions.isNullOrEmpty(subCastSettingObj) && !ObjectFunctions.isNullOrEmpty(castSettingObj)) {
															subCastSettingObj = new SubCastSettings();
															subCastSettingObj.setCustId(custId);
															subCastSettingObj.setSubCastName(studentVoDetails.getCastName().trim().toUpperCase());
															subCastSettingObj.setCreatedById(getUser().getId());
															subCastSettingObj.setCreatedDate(new Date());
															castSettingObj.addSubCast(subCastSettingObj);
															studentManager.saveOrUpdateObject(castSettingObj);
															subCastSettingMap.put(subCastSettingObj.getSubCastName().toLowerCase(),subCastSettingObj);
														}
														if (!ObjectFunctions.isNullOrEmpty(subCastSettingObj))
															onlineApplicationDetails.setSubCastId(subCastSettingObj);
													} else
														onlineApplicationDetails.setSubCastId(null);
													castSettingObj = null;
													subCastSettingObj = null;
												}
												
												
												onlineApplicationDetails.setFirstName(studentVoDetails.getFirstName());
												onlineApplicationDetails.setLastName(studentVoDetails.getLastName());
												onlineApplicationDetails.setDateOfBirth(studentVoDetails.getDateOfBirth());
												
												onlineApplicationDetails.setGender(studentVoDetails.getGender());
												onlineApplicationDetails.setHostelMode(studentVoDetails.getHostelMode());
												
												onlineApplicationDetails.setStudentEmail(studentVoDetails.getStudentEmail());
												onlineApplicationDetails.setStudentMobile(studentVoDetails.getStudentMobile());
												onlineApplicationDetails.setStudentUniqueNumber(studentVoDetails.getStudentUniqueNumber());
												onlineApplicationDetails.setAadharCardNumber(studentVoDetails.getAadharNumber());
												
												
												if (!ObjectFunctions.isNullOrEmpty(studentVoDetails.getCategoryName())) {
													onlineApplicationDetails.setCategoryId(schoolCategoryMap.get(studentVoDetails.getCategoryName()).getId());
												} else {
													onlineApplicationDetails.setCategoryId(schoolCategoryMap.get("General").getId());
												}
												onlineApplicationDetails.setCommittedFee(0.0);
												
												onlineApplicationDetails.setClassId(studyClass.getClassNameClassId());
												onlineApplicationDetails.setDateOfJoining(studentVoDetails.getDateOfBirth());
												onlineApplicationDetails.setFatherName(studentVoDetails.getFatherName());
												onlineApplicationDetails.setOccupation(studentVoDetails.getOccupation());
												onlineApplicationDetails.setMotherName(studentVoDetails.getMotherName());
												onlineApplicationDetails.setMotherQualification(studentVoDetails.getMotherOccupation());
												
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getAnnualIncomeStr()))
												{
													onlineApplicationDetails.setAnnualIncome(Double.valueOf(studentVoDetails.getAnnualIncomeStr()));
												}
												onlineApplicationDetails.setParentEmail(studentVoDetails.getParentEmail());
												
												onlineApplicationDetails.setMobileNumber(studentVoDetails.getMobileNumber());
												onlineApplicationDetails.setSecondaryMobileNumber(studentVoDetails.getSecondaryMobileNumber());
												onlineApplicationDetails.setPhoneNumber(studentVoDetails.getPhoneNumber());
												
												
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getHeightStr()))
													onlineApplicationDetails.setHeight(Double.valueOf(studentVoDetails.getHeightStr()));
												else
													onlineApplicationDetails.setHeight(Double.valueOf(0.0));
												
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getHeight2Str()))
													onlineApplicationDetails.setHeight2(Double.valueOf(studentVoDetails.getHeight2Str()));
												else
													onlineApplicationDetails.setHeight2(Double.valueOf(0.0));
												
												
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getWeightStr()))
													onlineApplicationDetails.setWeight(Double.valueOf(studentVoDetails.getWeightStr()));
												else
													onlineApplicationDetails.setWeight(Double.valueOf(0.0));
												
												if(!StringFunctions.isNullOrEmpty(studentVoDetails.getWeight2Str()))
													onlineApplicationDetails.setWeight2(Double.valueOf(studentVoDetails.getWeight2Str()));
												else
													onlineApplicationDetails.setWeight2(Double.valueOf(0.0));
												
												onlineApplicationDetails.setVisionR(studentVoDetails.getVisionR());
												onlineApplicationDetails.setVisionL(studentVoDetails.getVisionL());
												onlineApplicationDetails.setTeeth(studentVoDetails.getTeeth());
												
												studentManager.save(onlineApplicationDetails);
												
									 }else {
											// Provide message to user regd admission number is mandatory
										log.debug("**** Admission Number missed *******");
										
										if(!StringFunctions.isNullOrEmpty(studentVoDetails.getLastName()))
											errorMessageBuffer.append(studentVoDetails.getFirstName() + " " +studentVoDetails.getLastName() +", " );
										else
											errorMessageBuffer.append(studentVoDetails.getFirstName() +", " );
										cellNumber.append(countNo+",");
									}
											
								countNo++;
								studentVoDetails = null;
								rowCount=2;								
							}
							
						}else {
							super.addActionError("Few student(s) data not valid. Please verify the data.");
						}
	
					} catch (Exception ex) {
						ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
						continue;
					}
				}
				
				sendEmailToParentsAndStudentMap = null;
				sendSmsForParentsAndStudentMap = null;
				studentObjList = null;
				entryStudentCommittedPaymentMap = null;
				
				
				
				if(errorMessageBuffer.length() > 0)
	  		    {
					errorMessageBuffer.insert(0,"Few student(s) data not valid. Please verify the data:");
	  		    	super.addActionError(errorMessageBuffer.toString().substring(0,errorMessageBuffer.toString().length()-1));
	  		    }
				if(enrollmentCodeMessageBuffer.length() > 0)
	  		    {
					enrollmentCodeMessageBuffer.insert(0,"Few student(s) enrollment data already exists. Please verify the data:");
	  		    	super.addActionError(enrollmentCodeMessageBuffer.toString().substring(0,enrollmentCodeMessageBuffer.toString().length()-1));
	  		    }
				
				if(feepaidAdmissinNumber.length() !=0){
					feepaidAdmissinNumber.insert(0,"The following admission number(s) already paid fee.So you can not change class for this students.");
					super.addActionError(feepaidAdmissinNumber.toString());
				}
				if (ObjectFunctions.isNotNullOrEmpty(classNames) || admissionNumberExistMsg.length() != 0) {
					StringBuffer failureMsg = new StringBuffer(StringUtil.convertSetToString(classNames));
					if (failureMsg.length() != 0) {
						failureMsg.delete(failureMsg.length() - 2, failureMsg.length());
						failureMsg.insert(0,"The following class(es) is(are) not available.");
					}
					if (ObjectFunctions.isNotNullOrEmpty(classNames) && admissionNumberExistMsg.length() != 0) {
						admissionNumberExistMsg.delete(admissionNumberExistMsg.length() - 2, admissionNumberExistMsg.length());
						admissionNumberExistMsg.insert(0,"The following admission number(s) is(are) already available.");
						super.addActionError(admissionNumberExistMsg.toString()+ "\n" + failureMsg.toString());
					} else if (ObjectFunctions.isNotNullOrEmpty(classNames)) {
						super.addActionError(failureMsg.toString());
					} else if(cellNumber.length() > 0){
						cellNumber.append("The fallowing row number(s) are not inserted because in this rows don't have mandatery values.");
						super.addActionError(cellNumber.toString());
					}
					else {
						admissionNumberExistMsg.delete(admissionNumberExistMsg.length() - 2, admissionNumberExistMsg.length());
						admissionNumberExistMsg.insert(0,"The following admission number(s) is(are) already available.");
						super.addActionError(admissionNumberExistMsg.toString());
					} 
					failureMsg = null;
				}
				
		        
				if(commitFeeStr.length() !=0)
					super.addActionError(commitFeeStr.toString()+ "\n The following admission number(s) committed fee is not saved.");
				
				super.addActionMessage("Application(s) uploaded successfully");
				
				
				
				studyClassIdsSet = null;
				studentDetailsList = null;
				commonType = null;
				castSettingObj = null;
				subCastSettingObj = null;
				customer = null;
				academicYear = null;
				studentRole = null;
				//category = null;
				studyClassMap = null;
				studyClasses = null;
				castSettingsMap = null;
				subCastSettingMap = null;
				admissionNumberExistMsg = null;
				classNames = null;
				schoolCategoryMap = null;
				categoryList = null;
			}else{
				log.debug("Uploaded wrong file..");
				super.addActionError("You have uploaded wrong file. Please download latest sheet from system and upload.");
				ajaxGetStudyClassList();
				return "dummyInit";
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally
		{
			checkStudyClassHavingStudentsOrNot();
		}
		log.debug("******************** end method *******************");
		return SUCCESS;
	}
	public void prepareStudentSchoolFeeSettingListFromVo(StudentVo studentVo) {
		StringBuffer queryString = new StringBuffer();
		if(getUser().isSchoolHostel() || getUser().isHostelFinance()){
			if(!ObjectFunctions.isNullOrEmpty(studentVo.getBedVo()))
			queryString.append("status in ('"+Constants.HOSTEL_STATUS+"')");
		}else if (Constants.TRANSPORT_STATUS.equalsIgnoreCase(studentVo.getTransportMode()) && getUser().isSchoolTransport() || getUser().isTransportFinance()) {
			queryString.append("status in ('"+Constants.TRANSPORT_STATUS+"')");
		}else {
			if (!ObjectFunctions.isNullOrEmpty(studentVo)) {
				queryString.append("status in ('" + Constants.SCHOOL_MODULE+ "'");
				if (Constants.TRANSPORT_STATUS.equalsIgnoreCase(studentVo.getTransportMode())&& !ObjectFunctions.isNullOrEmpty(studentVo.getBedVo())) {
					queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
					if(studentVo.getBedVo().getId() !=0)
						queryString.append(",'" + Constants.HOSTEL_STATUS + "'");
				}if (Constants.TRANSPORT_STATUS.equalsIgnoreCase(studentVo.getTransportMode())) {
					queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
				//}else if (!ObjectFunctions.isNullOrEmpty(student.getBed())) { change the below line code for hostel by cvs 12-3-2014.
				}if (StringFunctions.isNotNullOrEmpty(studentVo.getHostelMode()) && "H".equalsIgnoreCase(studentVo.getHostelMode().trim())) {
					queryString.append(",'" + Constants.HOSTEL_STATUS + "'");
				}
				queryString.append(")");
			}
		}
		List<SchoolFeeSetting> feeSettingList=adminManager.getAll(SchoolFeeSetting.class,queryString.toString());
		StringBuffer feeSettingIds=new StringBuffer();
		if (!ObjectFunctions.isNullOrEmpty(feeSettingList)) {
			if (getTempId() == 0) {
				SchoolFeeSetting feeSetting = feeSettingList.get(0);
				setTempId(feeSetting.getId());
				setTitle(feeSetting.getSettingName());
			}
			feeSettingIds.append("(");
			for(SchoolFeeSetting feeSetting: feeSettingList){
				feeSettingIds.append(feeSetting.getId()+",");
				getChkBoxSelectedIds().add(String.valueOf(feeSetting.getId()));
			}
			feeSettingIds.append("0)");
			setTempString(feeSettingIds.toString());
			setObjectList(feeSettingList);
		}
		queryString = null;
	}
	
	public String ajaxDoGetStudentPorfileDetails()
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetStudentPorfileDetails' method");
		}
		try {
			StudyClass st = null;
			List<ViewStudentPersonAccountDetails> studentsList = null;
			String frequency = getParamValue("frequency");
			if (StringFunctions.isNullOrEmpty(frequency)) {
				frequency = "ST";
			}
			if (!StringFunctions.isNullOrEmpty(frequency)) {
				if(getUser().getId() > 0 )
				{
					setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
				}
				long accountId = getUser().getId();
				List<ClassTeacher> classTeachersList = null;
				StringBuffer studyClassIdes = null;
					if (!ObjectFunctions.isNullOrEmpty(accountId)) {
						setAllStudentsList(null);
						if(getTempId1() > 0){
							 if(getUser().isSchoolTeacher()){
								//prepareStudentsListBystaffIdAndAcademicYearId(getUser().getId());
								 studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class, "classSectionId ="+getTempId1()+" and academicYearId="+getUserAcademicYearId()+" and description is null");
								 setClassStudentsList(studentsList);
							 }
						}
						else{
							studyClassIdes = new StringBuffer("(");
							classTeachersList= adminManager.getAllHqlQuery("FROM ClassTeacher cteacher WHERE cteacher.staff.account="+accountId+" and cteacher.staff.status='Y' and cteacher.academicYear="+getUserAcademicYearId()+" group by studyClassId ");
							if(ObjectFunctions.isNotNullOrEmpty(classTeachersList)){
								for(ClassTeacher classTeacher : classTeachersList ){
									if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
										 st = classTeacher.getStudyClass();
										if (!ObjectFunctions.isNullOrEmpty(st)) {
											studyClassIdes.append(st.getId()).append(",");
										}
									}
									classTeacher = null;
								}
							 }
							if(getUser().isOnlySchoolHod()){
								StringBuffer sqlQuery = new StringBuffer("select  st.id,st.className,st.section,st.classNameClassId  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
						    	.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+getUserAcademicYearId()).append(" and sm.staffId=").append(getStaff().getId());
								List<Object[]> studyclassesList=staffManager.getAll(sqlQuery.toString());
						    	if(!ObjectFunctions.isNullOrEmpty(studyclassesList)){
						    		for(Object[] obj :studyclassesList){
						    			if (!ObjectFunctions.isNullOrEmpty(obj[0])) {
						    				studyClassIdes.append(obj[0].toString()).append(",");
						    				 st= new StudyClass();
						    			}
						    		}
						    	}
							}
							studyClassIdes.append("0)");
					    	studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class, "classSectionId in "+studyClassIdes.toString()+" and academicYearId="+getUserAcademicYearId()+" and description is null");
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
							studentsList=null;
						}
				}	
				if(ObjectFunctions.isNotNullOrEmpty(getStudentsList()))
					Collections.sort(getStudentsList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
	
	public boolean updateStudentFeePaidStatus(long custId,long academicYearId,long studentId,long boardingPointId,long categoryId)
	{
		try{
			Fee feeObj = (Fee) adminManager.get(Fee.class,"custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+" and routeBoardingPointId="+boardingPointId+" and categoryId="+categoryId);
			if(!ObjectFunctions.isNullOrEmpty(feeObj)){
				//This method is used to change the feeConfgured status is "Y" if fee table contains records with boadrding pointId and categoryId
				adminManager.updateFeeConfiguredStatusInStudentForTransport(0,categoryId,"Y",studentId);
				Object[] studentFeeObj = adminManager.get("select paymentStatus,studentId from vw_studentFeePaymentDetails where custId="+custId+" and academicYearId="+academicYearId+" and studentId="+studentId+" and paymentStatus='"+Constants.NO_STRING+"'");
				if(!ObjectFunctions.isNullOrEmpty(studentFeeObj)){
					Object[] studentOb = adminManager.get("select feePaidStatus,id from student where custId="+custId+" and academicYearId="+academicYearId+" and id="+studentId+" and feePaidStatus='F'");
					if(!ObjectFunctions.isNullOrEmpty(studentOb)){
						return true;
					}
				}
			}
		 }catch(Exception ex){
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		return false;
	}
	
	@Actions( { 
		@Action(value = "ajaxGetStudentDetailsForChangeClass", results = { @Result(location = "ajaxViewSearchStudentList.jsp", name = "success") })
		})
		public String ajaxGetStudentDetailsForChangeClass() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentDetailsForChangeClass' method");
		}
		try {
			//setCustomer(getCustomerByCustId());//customer have student permission settings or not checked in the page level
			prepareStudentsDetailsList();
			
			if(!ObjectFunctions.isNullOrEmpty(getStudentsList())){
				ViewStudentClassDetails viewStudentClassDetails = (ViewStudentClassDetails)getStudentsList().get(0);
				setStudyClassList(adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classNameClassId="+viewStudentClassDetails.getClassId()+ " and id !="+getClassId()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
		}
	
	@Actions( { 
		@Action(value = "ajaxChangeStudentChangeSection", results = { @Result(location = "ajaxViewStudyClassList.jsp", name = "success") })
		})
		public String ajaxChangeStudentChangeSection() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxChangeStudentChangeSection' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getTempString()))
			{
				JSONObject formData = new JSONObject(getTempString());
				JSONArray classStudentJsonArray = (JSONArray) formData.get("data");
				
				JSONObject examScheduleJson = null;
				
				HashMap<Long,String> rollNumberMap = new HashMap<Long,String>();
				if(!ObjectFunctions.isNullOrEmpty(classStudentJsonArray))
				{
					for (int i = 0; i < classStudentJsonArray.length(); i++) 
					{
						long studyClassId = 0;
						long studentId = 0;
						String rollNumber = null;
						
						examScheduleJson = classStudentJsonArray.getJSONObject(i);
						if (!ObjectFunctions.isNullOrEmpty(examScheduleJson)) 
						{
							if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("STUDYCLASSID"))) {
								studyClassId = Long.valueOf((String) examScheduleJson.get("STUDYCLASSID"));
							}
							if (!ObjectFunctions.isNullOrEmpty(examScheduleJson.get("STUDENTID"))) {
								studentId = Long.valueOf((String) examScheduleJson.get("STUDENTID"));
							}
							
							if(!ObjectFunctions.isNullOrEmpty(rollNumberMap.get(studyClassId)) && ("N".equalsIgnoreCase(getCustomerByCustId().getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(getCustomerByCustId().getAlphaNumericRollNumber())))
							{
								rollNumber = Integer.toString(Integer.parseInt(rollNumberMap.get(studyClassId))+1);
							}
							else
							{
								if("N".equalsIgnoreCase(getCustomerByCustId().getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(getCustomerByCustId().getAlphaNumericRollNumber())){
									//Object[] studentRollObj=adminManager.get("select id,rollNumber from student  where custId="+getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+" and classSectionId="+studyClassId +" order by rollNumber Desc limit 1");
									Student student=null;
									List<Student> studentRollObj=studentManager.getAll(Student.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+studyClassId);
									if(!ObjectFunctions.isNullOrEmpty(studentRollObj))
									{
										Collections.sort(studentRollObj,new StudentRollNumberComparator());
										student=studentRollObj.get(studentRollObj.size()-1);
										rollNumber = Integer.toString(Integer.parseInt(student.getRollNumber())+1);
									}
									else
										rollNumber = "1";
									student=null;
									studentRollObj = null;
								}else{
									Object[] studentRollObj=adminManager.get("select id,rollNumber from student  where custId="+getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+" and id="+studentId);
									rollNumber=studentRollObj[1].toString();
									studentRollObj = null;
								}
							}
							adminManager.updateQuery("update student set classSectionId="+studyClassId +",rollNumber='"+rollNumber+"' where id="+studentId);
							
							adminManager.updateQuery("UPDATE studyClass SET filledSeats=(filledSeats-1) where id="+getClassSectionId());
							adminManager.updateQuery("UPDATE studyClass SET filledSeats=(filledSeats+1) where id="+studyClassId);
							
							rollNumberMap.put(studyClassId, rollNumber);
							
						}
					}
				}
				
				super.addActionMessage("Students section updated successfully");
				classStudentJsonArray = null;
				rollNumberMap = null;
				setClassSectionId(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxGetStudyClassList();
		return SUCCESS;
		}
			
}	

