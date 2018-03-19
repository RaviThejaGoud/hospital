package com.urt.webapp.action.base;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.zip.ZipOutputStream;

import javax.persistence.Transient;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.util.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import sun.misc.BASE64Decoder;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.image.ThumbNail;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.pdf.PDFGenerator;
import com.churchgroup.util.pdf.PdfHeaderFooterMarkJasper;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.churchgroup.util.xls.ExcelView;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.hyniva.sms.ws.vo.AcademicYearVo;
import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.ClassNameAutoAcademicNumberVO;
import com.hyniva.sms.ws.vo.DefaultScoreCardTemplatesVO;
import com.hyniva.sms.ws.vo.EventsVO;
import com.hyniva.sms.ws.vo.PersonVO;
import com.hyniva.sms.ws.vo.ReplyScrapMessageVO;
import com.hyniva.sms.ws.vo.SalaryVO;
import com.hyniva.sms.ws.vo.ScrapMessageVO;
import com.hyniva.sms.ws.vo.ShareUserActivitiesCommentsVO;
import com.hyniva.sms.ws.vo.ShareUserActivitiesVO;
import com.hyniva.sms.ws.vo.SocialDiscussionsCommentsVO;
import com.hyniva.sms.ws.vo.SocialDiscussionsVO;
import com.hyniva.sms.ws.vo.StaffHistoryVO;
import com.hyniva.sms.ws.vo.StaffStatutoryVO;
import com.hyniva.sms.ws.vo.StaffVO;
import com.hyniva.sms.ws.vo.StudentVo;
import com.hyniva.sms.ws.vo.StudySubjectVO;
import com.hyniva.sms.ws.vo.TicketDetailsVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.hyniva.sms.ws.vo.ViewStaffPersonAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.CustomerBankAccountDetailsVO;
import com.hyniva.sms.ws.vo.account.FinancialYearVO;
import com.hyniva.sms.ws.vo.attendance.ClassAttendanceMainVO;
import com.hyniva.sms.ws.vo.fee.DeleteStudentPaymentVo;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import com.spring.context.SpringContextAware;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.alumnee.ShareUserActivitiesComments;
import com.urt.persistence.model.alumnee.SocialDiscussionsComments;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.AdmissionInquiry;
import com.urt.persistence.model.common.AdmissionNumberSettings;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.Circular;
import com.urt.persistence.model.common.ContactUs;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.FeedBackSettings;
import com.urt.persistence.model.common.HallTicketSettings;
import com.urt.persistence.model.common.HouseType;
import com.urt.persistence.model.common.LeaveManagement;
import com.urt.persistence.model.common.Medium;
import com.urt.persistence.model.common.MessageActivity;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.MessageEnquiryDetails;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.ParentIncomeRange;
import com.urt.persistence.model.common.Payroll;
import com.urt.persistence.model.common.PayrollSettings;
import com.urt.persistence.model.common.PayrollTypes;
import com.urt.persistence.model.common.PersonDocuments;
import com.urt.persistence.model.common.Reminder;
import com.urt.persistence.model.common.ReplyScrapMessage;
import com.urt.persistence.model.common.Salary;
import com.urt.persistence.model.common.SchoolVideos;
import com.urt.persistence.model.common.ScrapMessage;
import com.urt.persistence.model.common.StaffDailyAttendance;
import com.urt.persistence.model.common.StaffMonthlyAttendance;
import com.urt.persistence.model.common.StaffStatutory;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.StudentDailyAttendance;
import com.urt.persistence.model.common.StudentMonthlyAttendance;
import com.urt.persistence.model.common.StudyAndBonafiedSettings;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.common.TaxAccount;
import com.urt.persistence.model.common.TcSettings;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.common.VWStudentClassAssignment;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.common.ViewTaskDetailsAndTaskHistory;
import com.urt.persistence.model.common.ViewUserRoles;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.CustomerEnrollmentRequest;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.Hostel;
import com.urt.persistence.model.customer.InviteCustomerEnrollment;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.MasterCustomer;
import com.urt.persistence.model.customer.Organization;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.customer.StaffRoom;
import com.urt.persistence.model.customer.WashRoom;
import com.urt.persistence.model.event.Events;
import com.urt.persistence.model.event.SmsEvents;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.Developers;
import com.urt.persistence.model.exam.ExamSchedules;
import com.urt.persistence.model.exam.ExamSchedulesStartDateComparator;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.exam.KBankComments;
import com.urt.persistence.model.exam.KBankRating;
import com.urt.persistence.model.exam.KBankType;
import com.urt.persistence.model.exam.OverAllGrades;
import com.urt.persistence.model.exam.PlayList;
import com.urt.persistence.model.exam.PlayListVideo;
import com.urt.persistence.model.exam.QuestionAnswer;
import com.urt.persistence.model.exam.SchoolGrades;
import com.urt.persistence.model.exam.SubType;
import com.urt.persistence.model.exam.SupportTicket;
import com.urt.persistence.model.exam.ViewClassExamDetails;
import com.urt.persistence.model.exam.ViewStaffSubjectsDetails;
import com.urt.persistence.model.exam.WeekDays;
import com.urt.persistence.model.fee.ChallanaPayment;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.fee.StudentFeeRefund;
import com.urt.persistence.model.fee.ViewStudentFeeRefundDetails;
import com.urt.persistence.model.hostel.Bed;
import com.urt.persistence.model.hostel.Building;
import com.urt.persistence.model.hostel.Floor;
import com.urt.persistence.model.hostel.FoodTypes;
import com.urt.persistence.model.hostel.HostelStudents;
import com.urt.persistence.model.hostel.Merchant;
import com.urt.persistence.model.hostel.Mess;
import com.urt.persistence.model.hostel.MessFoodType;
import com.urt.persistence.model.hostel.ProvisionItems;
import com.urt.persistence.model.hostel.Room;
import com.urt.persistence.model.hostel.StudentOut;
import com.urt.persistence.model.hostel.ViewBuildingFloorDetails;
import com.urt.persistence.model.hostel.ViewHostelBuildingDetails;
import com.urt.persistence.model.hostel.Visitors;
import com.urt.persistence.model.manuatimetable.TimeTableSettings;
import com.urt.persistence.model.rss.Feed;
import com.urt.persistence.model.rss.FeedMessage;
import com.urt.persistence.model.rss.RSSFeedParser;
import com.urt.persistence.model.secretary.BudgetRequest;
import com.urt.persistence.model.secretary.FinancialYear;
import com.urt.persistence.model.secretary.MeetingDetails;
import com.urt.persistence.model.secretary.MeetingRequests;
import com.urt.persistence.model.secretary.Particular;
import com.urt.persistence.model.secretary.ParticularType;
import com.urt.persistence.model.study.ClassAssignment;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.SchoolShiftInfo;
import com.urt.persistence.model.study.Section;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StaffCurricularActivities;
import com.urt.persistence.model.study.StaffElgibleSubjects;
import com.urt.persistence.model.study.StaffHistory;
import com.urt.persistence.model.study.StaffSyllabusPlanner;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentClassAssignment;
import com.urt.persistence.model.study.StudentCurricularActivities;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudentPocketMoney;
import com.urt.persistence.model.study.StudentRollNumberComparator;
import com.urt.persistence.model.study.StudentSiblings;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.SuspendAndBlacklistStudents;
import com.urt.persistence.model.study.SyllabusType;
import com.urt.persistence.model.study.SyllabusTypeSchoolCode;
import com.urt.persistence.model.study.TimeTable;
import com.urt.persistence.model.study.TransferCertificate;
import com.urt.persistence.model.study.TransferStudent;
import com.urt.persistence.model.study.ViewClassAssignmentDetails;
import com.urt.persistence.model.study.ViewClassFeeDetails;
import com.urt.persistence.model.study.ViewClassSectionDetails;
import com.urt.persistence.model.study.ViewClassSectionTeacher;
import com.urt.persistence.model.study.ViewClassSubjectsSettings;
import com.urt.persistence.model.study.ViewClassWisePeriodsCountDetails;
import com.urt.persistence.model.study.ViewOnlineApplicationStudentClassFees;
import com.urt.persistence.model.study.ViewStaffLeaveDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentClassFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentLeaveDetails;
import com.urt.persistence.model.study.ViewStudentMarks;
import com.urt.persistence.model.study.ViewStudentMarksDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentTransportFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentsLatestExamMarksDetails;
import com.urt.persistence.model.study.ViewStudentsScoreCardProfileDetails;
import com.urt.persistence.model.study.ViewStudyClassSubjects;
import com.urt.persistence.model.study.ViewTimeTableDetails;
import com.urt.persistence.model.subscription.OrganizationTypes;
import com.urt.persistence.model.subscription.PackageDetails;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.transport.ViewRouteWiseStudents;
import com.urt.persistence.model.transport.ViewVehiclesWithExpiryDatesInformation;
import com.urt.persistence.model.user.OnlineApplicationDetails;
import com.urt.persistence.model.user.OnlineApplicationDetailsView;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.account.AccountManager;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.calendar.CalendarManager;
import com.urt.service.manager.interfaces.hostel.HostelManager;
import com.urt.service.manager.interfaces.library.LibraryManager;
import com.urt.service.manager.interfaces.schoolfee.SchoolFeeManager;
import com.urt.service.manager.interfaces.sports.SportsManager;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.manager.interfaces.store.StoreManager;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.service.manager.interfaces.studentmarks.StudentMarksManager;
import com.urt.service.manager.interfaces.subscription.SubscriptionManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.service.manager.interfaces.user.RoleManager;
import com.urt.service.manager.interfaces.user.UserManager;
import com.urt.service.thread.SendAssignmentSMSThread;
import com.urt.service.thread.WebUserMetaDataTaskExecutor;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.excel.student.PrepareStudentExcel;
import com.urt.webapp.action.jrexception.JRExceptionClient;
import com.urt.webapp.action.userloginmetatdata.UserLoginMetaDataThread;

@Namespace("/common")
@ParentPackage("urt-struts")
@Results(value = {
		@Result(name = ActionSupport.ERROR, location = "/error.jsp"),
		@Result(name = ActionSupport.INPUT, location = "/error.jsp") })

@Actions({
@Action(value = "ajaxDoSendSchoolWideAlerts", results = { @Result(location = "alerts/sendSchoolWideAlert.jsp", name = "success" )})

})	

public class BaseAction extends ActionSupport {
	
	
	private static final long serialVersionUID = 3525445612504421307L;
	protected transient final Log log = LogFactory.getLog(getClass());
    /**
     * Constant for cancel result String
     */
    public static final String CANCEL = "cancel";

    public static final String COUNT_KEY = "userCounter";
    /**
     * Student of users Set in the ServletContext
     */
    public static final String USERS_KEY = "userNames";
    

	
    @Autowired
    public UserManager userManager;
    @Autowired
    public StudentManager studentManager;
    @Autowired
    public StaffManager staffManager;
    @Autowired
    public AdminManager adminManager;
    @Autowired
    public RoleManager roleManager;
    @Autowired
    public LibraryManager libraryManager;
    @Autowired
    public HostelManager hostelManager;
    @Autowired
    public CommunicationManager communicationManager;
   	@Autowired
   	protected SubscriptionManager subscriptionManager;
    @Autowired
    public CalendarManager calendarManager;
    @Autowired
    public AccountManager accountManager;
    @Autowired
    public SchoolFeeManager schoolFeeManager;   
    @Autowired
    public StudentMarksManager studentMarksManager;
    @Autowired
    public SportsManager sportsManager;
    @Autowired
    public StoreManager storeManager;
    
    
    private Customer customer;
    protected Staff staff;
    protected Student student;
    public WebUserMetaDataTaskExecutor webUserMetaDataTaskExecutor;
    private Leave leave;
    private User newUser;
    private ViewStaffPersonAccountDetails viewStaffPersonAccountDetails;
    private ViewStudentPersonAccountDetails viewStudentPersonAccountDetails;
    protected StudyClass studyClass;
    public LeaveManagement leaveManagement;
    protected ViewStaffLeaveDetails viewStaffLeaveDetails;
    protected ViewStudentLeaveDetails viewStudentLeaveDetails;
    protected Messages messages;
    protected SchoolHolidays schoolHolidays;
    protected ViewStudentFeePaymentDetails viewStudentFeeDetails;
    protected ClassTeacher classTeacher;
    private AdmissionSettings admissionSettings;
    private OnlineApplicationDetails onlineApplicationDetails;
    private OnlineApplicationDetailsView onlineApplicationDetailsView;
    protected ExamTypes examTypes;
    protected ScrapMessage scrapMessage;
    protected ReplyScrapMessage replyScrapMessage;
    protected KBank knowledgeBank;
    protected KBankType knowledgeBankType;
    private Attachment attachment;
    protected ViewAllUsers viewAllUsers;
    protected KBankComments kBankComments;
    protected KBankRating kBankRating;
    protected PlayList playList;
    protected PlayListVideo playListVideo;
    protected QuestionAnswer questionAnswer;
    protected ExamSchedules examSchedules;
    private ContactUs contactUs;
    protected AcademicYear academicYear;
    protected PackageDetails packageDetails;
    protected SchoolTerms schoolTerms;
    private Developers developers;
    protected SupportTicket supportTicket;
    protected Person person;
    private SchoolGrades schoolGrades;
    private OverAllGrades overAllGrades;
    private SchoolCategory schoolCategory;
    protected SmsEvents smsEvents;
	protected Events events;
	protected TransferCertificate transferCertificate;
	protected ClassName className;
	protected SubType subType;
	public User staffAccount;
	private StudentPayment studentPayment;
	private ViewStudentMarksDetails  viewStudentMarksDetails;
	private SubCastSettings  subCastSettings;
	protected StudentFeePaidDetails studentFeePaidDetails;
	protected CastSettings castSettings;
	protected CommonType commonType;
    protected FoodTypes foodTypes;
    protected SyllabusType syllabusType;
    private Building building;
	private Floor floor;
	private Room room;
	private Hostel hostel;
	private Bed bed;
	private StudentOut studentOut;
	protected Visitors visitors;
	protected SMSServiceProviders smsServiceProvider;
	protected Salary salary;
  	protected PayrollTypes payrollTypes;
  	protected TimeTable timeTable;
  	protected StaffStatutory staffStatutory;
  	protected Payroll payroll;
  	protected PDFGenerator pDFGenerator;
  	protected TaxAccount taxAccount;
  	protected SchoolFeeSetting schoolFeeSetting;
  	private State state;
  	private MasterCustomer masterCustomer;
	private TransferStudent transferStudent;
	public HallTicketSettings hallTicketSettings;
	public StudyAndBonafiedSettings studyAndBonafiedSettings;
	public WorkbookSettings ws;
	public Workbook workbook;
	protected StaffHistory staffHistory;
	protected StudentSiblings studentSiblings;
	protected StaffCurricularActivities staffCurricularActivities;
	protected WashRoom washRoom;
	protected StudentCurricularActivities studentCurricularActivities;
	protected StudentPocketMoney studentPocketMoney;
	protected StaffRoom staffRoom;
	protected Mess mess;
	protected Merchant merchant;
	protected MessFoodType messFoodType;
	protected ProvisionItems provisionItems;
	protected Organization organizationObj;
	protected ParticularType particularType;
	protected Particular particular;
	protected FinancialYear financialYear;
	protected BudgetRequest budgetRequest;
	protected MeetingDetails meetingDetails;
	protected MeetingRequests meetingRequests;
	protected ShareUserActivitiesVO shareUserActivitiesVO;
	protected ShareUserActivitiesCommentsVO shareUserActivitiesCommentsVO;
	protected SocialDiscussionsVO socialDiscussionsVO;
	protected SocialDiscussionsCommentsVO socialDiscussionsCommentsVO;
	protected HostelStudents hostelStudents;
	protected DefaultScoreCardTemplatesVO defaultScoreCardTemplatesVO;
	protected UserVO userVo;
	protected PersonVO personVo;
	protected StudentVo studentVo;
	protected AddressVO addressVo;
	protected StaffVO staffVo;
	protected StaffHistoryVO staffHistoryVo;
	protected StaffStatutoryVO staffStatutoryVo;
	protected SalaryVO salaryVo;
	protected ViewStaffPersonAccountDetailsVO viewStaffPersonAccountDetailsVo;
	protected AcademicYearVo academicYearVo;
    protected ClassAttendanceMainVO classAttendanceMainVO;
    protected SchoolVideos schoolVideos;
    private TicketDetailsVO ticketDetailsVO;
    private InviteCustomerEnrollment inviteCustomerEnrollment;
    private CustomerEnrollmentRequest customerEnrollmentRequest;
    private ViewClassAssignmentDetails viewClassAssignmentDetails;
    private Country country;
    private EventsVO eventsVO;
    protected SuspendAndBlacklistStudents suspendAndBlacklistStudents;
    protected ClassAssignment classAssignment;
	protected FinancialYearVO financialYearVO;
	protected ChallanaPayment challanaPayment;
	protected CustomerBankAccountDetailsVO bankAccountDetailsVO;
	private AdmissionInquiry admissionInquiry;
	private SyllabusTypeSchoolCode syllabusTypeSchoolCode;
	private ViewStudentClassDetails viewStudentClassDetails;
	private DeleteStudentPaymentVo deleteStudentPaymentVo;
	private AdmissionNumberSettings admissionNumberSettings;
	private ClassNameAutoAcademicNumberVO classNameAutoAcademicNumberVO;
	private User user;
	private Address address;
	private ViewStudentFeeRefundDetails refundedFeeDetails;
	protected String inquiryStatus;
	private File upload;
	private String contentType;
	private String fileName;
    public String saveDir;
    public String autoCheck;
    public String paypalServerType;
    private String footer="Copyright &copy; 2015 HYNIVA. All Rights Reserved.";
    private String todayDate;
    private String selectedId;
    protected String studyClassId; 
    public String empId;
    private String eventId;
    private String anyId;
    private String roomId;
    private String bedId;
    private String balance;
    protected String subjectId;
    protected String filterName;
    private String startTime[];
    private String endTime[];
    private String subId[];
    protected String wishTitle;
    protected String wishDescription;
    private String kBankTypeName;
    protected String tempString;
    protected String isClassTeacher;
    private String[] staffAccountId;
    private String chapterName[];
    protected String feeTypeId[];
    protected String feeAmount[];
    protected String currentMonth;
    protected String questionAnswerId[];
    protected String classId;
    private String alertSendType;
    protected String staffId[];
    public String oldYear;
    public String newYear;
    protected String schoolTermId[];
    public String plSubjectName;
    public String plTitle;
    public String plSubTopic;
    private String customerName;
	protected String anyTitle;
	private String selectedDate;
	protected String attendanceDate;
	protected String section;
	public String username;
	private String examType;
	protected String attendanceType;
	private String webSiteUrl;
	private String queryString;
    protected String title;
    protected String acdmcYearRange;
	protected String chequeNumber;
	protected String bankName;
	protected String feeDDNumber;
	protected String paymentType;
	private String classSectionId;
	protected String organization;
  	protected String staffRoleName;
  	private String description;
	protected String teachingRoleName;
	protected String studentNumber;
	private String subject;
	private String feePaymentType;
	private String sectionName;
	protected String schoolName;
	private String status;
	private String tempString2;
    private String tempString3;
    protected String tempString1;
	private String admissionNumber;
	private String accountType;
    private String feeModuleUsege;
    private String email;
    public String  reportType;
    public String messageRecieverType;
    public String lessonId;
    public String questionBankId;
    public long studentsTransportCount;
    protected List<String> chkBoxSelectedIds;
    private List tempList;
    private List tempList1;
    private List tempList2;
    protected List statesList;
    protected List messList;
    protected List objectList;
    private List leavesList;
    private List approvedLeavesList;
    private List rejectedLeavesList;
    private List eventInvitedUserList;
    private List calendarEventsList;
    private List messageDetails;
   
	protected List<ViewStudentPersonAccountDetails> viewStudentPersonAccountDetailsList;
    protected List<StudyClass> studyClassList;
    protected List<ClassName> classList;
    protected List<Section> sectionList;
    protected List allStudentsList;
    protected List<ClassTeacher> classTeacherList;
    protected List attendanceList;
    private List presentList;
    private List absentList;
    protected List viewStudentLeaveDetailsList;
    protected List messagesList;
    private List<ViewClassExamDetails> viewClassExamDetails;
    private List halfYearlyClassList; 
    private List quarterYearlyClassList;
    private List annualYearlyClassList;
    private List term1ExamClassList;
    private List term2ExamClassList;
    private List term3ExamClassList;
    private List term4ExamClassList;
    protected List classStudentsList;
    protected List<Student> studentsList;
    protected List allStaffList;
    protected List staffsList;
    private List halfYearlyEndClassList; 
    private List quarterYearlyEndClassList;
    private List annualYearlyEndClassList;
    private List noticeBoardMessagesList;
    protected List<ExamTypes> examTypeList;
    protected List holidayBoardMessagesList;
    protected List viewStaffPersonAccountDetailsList;;
    protected List subjectsList;
    protected List classExamTypeList;
    protected List teacherSubjectsList;
    protected List classSubjectsList;
    protected List teacherList;
    protected List<StudySubject> studySubjectList;
    private List syllabusList;
    private List examScheduleList;
    protected List<String> selectBoxIdList;
    protected List scrapMessagesList;
    protected List feeTypeList;
    protected List<ViewClassFeeDetails> classFeeList;
    protected List<KBankType> knowledgeBankTypeList;
    protected List<KBank> knowledgeBankList;
    protected List classFeeCountList;
    protected List studentPaymentList;
    protected List feedbackQuestionsList;
    protected List<String> chkBoxFeeSelectedIds;
    protected List studentMarksList;
    protected List quizList;
    protected List categoryQuestionList;
    protected List questionAnswerList;
    private List alertsList;
    protected List studentQuestionAnswerList;
    public List<AcademicYear> academicYearList;
    protected List<AdmissionSettings> admissionSettingsList;
    protected List<PackageDetails> packageDetailsList;
    protected List feedbackQuestionsAnswerList;
    protected List<CastSettings> castSettingList;
    protected List<SchoolTerms> schoolTermsList;
    protected List developersList;
    protected List<String> schoolTermchkBoxSelectedIds;
    private List supportTicketMessagesList;
    protected List studentsFeeTypeList;	
    protected List subTopicList;
    protected List<ViewStudyClassSubjects> studyClassSubjectsList;
    protected List<SchoolGrades> schoolGradesList;
    protected List<OverAllGrades> overAllGradesList;
	protected List<SchoolCategory> schoolCategoriesList;
	protected List<SubType> subTypesList;
	protected List studentAttendanceStatusList;
	protected List classNameList;
	protected List<OrganizationTypes> organizationTypesList;
    protected List weekDayList;
    protected List<SyllabusType> syllabusTypeList;
    protected List hostelFeeTypeList;
    protected List<String> hostelTermchkBoxSelectedIds;
	private List classFeeTypeList;
	protected List tempActivePrekgClassList;
	protected List<Building> buildingList;
	protected List<Customer> customerList;
	protected List staffPaymentList;
	protected List staffFeeList;
	protected List<PayrollTypes> payrollTypesList;
	protected List<ViewClassSubjectsSettings> viewClassSubjectsSettings;
	protected List<StaffStatutory> staffStatutoryList;
  	protected List<Payroll> payrollList;
  	protected List<PayrollSettings> payrollSettingsList;
  	protected List<ViewStudentMarksDetails> viewStudentMarksDetailsList;
  	protected List transportSchoolFeeList;
  	protected List schoolFeeList;
  	protected List<String> chkBoxSelectedAccountIds;
    protected List onlineApplicationDetailsList;
    protected List<String> chkBoxClassSelectedIds;
    public List<Route> routeList;
    protected List<ShareUserActivitiesVO> shareUserActivitiesVOList;
	protected List<ShareUserActivitiesCommentsVO> shareUserActivitiesCommentsVOList;
	protected List<SocialDiscussionsVO> socialDiscussionsVOList;
	protected List<SocialDiscussionsCommentsVO> socialDiscussionsCommentsVOList;
    protected List<ShareUserActivitiesComments> shareUserActivitiesCommentsList;
    protected List<SocialDiscussionsComments> socialDiscussionsCommentsList;
    protected List<SMSServiceProviders> smsServiceProvidersList;
    protected List<StudySubjectVO> studySubjectListVo;
    protected List financialYearList;
    protected List<SuspendAndBlacklistStudents> suspendBlackstudentList;
    protected List<StudentDailyAttendance> studentDailyAttendanceList;
    protected List<StudentMonthlyAttendance> studentMonthlyAttendanceList;
    private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadFileName = new ArrayList<String>();
	protected List countryList;
	protected List<String> chkBoxNonTeachingRoleIds;
	private List<File> phFileUpload = new ArrayList<File>();
	private List<String> phFileUploadFileName = new ArrayList<String>();
	protected List<ViewStudentsLatestExamMarksDetails> viewStudentsLatestExamMarksDetailsList;
	private List<AdmissionInquiry> admissionInquiryList;
	private List fieldErrorsList;
	private List<HouseType> houseTypeList;
	private List<Object[]> absentStudOrStaffList;
	private List taskReminderDetails;
	private List<ViewStudyClassSubjects> studyClassSubjectDetails;
	public List<ParentIncomeRange> parentIncomeRangesList;
	protected List rejectedList;
	protected Set displayAttendanceSet;
    protected Set<User> totalOnlineAccounts;
    protected Set birthDaysListSet;
    protected Set studentFeeAbove15List;
    protected Set studentFee14List;
    protected Set studentFeeAbove30List;
    protected Set studentFeeAbove60List;
    protected Set studentFeeUpcomeingList;
    protected Set studySubjectSet;
    protected Set allUsersSet;
    protected Set replyScrapMessageSet;
    protected Set playListVideoSet;
    protected Set<StudyClass> studyClassSet;
	protected Set<String> mobileNumbersSet;
    
    protected Map jsonResult;
    private Map selectboxMap;
	protected HashMap<String, String> leaveTypes;
	protected HashMap<Integer,String> weekDaysList;
	protected Map<String ,Integer> monthNamesList;
	protected HashMap<String, String> smsFeeReports;
    protected HashMap<String, String> collectionAndFeeDuesList;
  	protected HashMap<Long, Integer> tempHashMap;
	public Map<String, State>  statelist;
    protected TreeMap<String, String> teachingRoleMap;
	protected TreeMap<String, String> nonTeachingRoleMap;
	protected TreeMap<String, String> staffRoles;
	protected TreeMap<Long, String> studyClassesMap;
	protected TreeMap<String, String> managementRoleMap;
	
    protected long  custId;
    protected long leaveApprovalCount;
    protected long tempId;
    protected long quizId;
    protected long studentLeaveApprovalCount;
    private long academicYearId;
    protected long tempId1;
	protected long tempId2;
	private long usrChgedAcademicId;
	private long currentAcademicId;
	protected long bankId;
  	public long currentUserCustId;
  	private long studentSize;
    
    private double casualLeave;
    private double sickLeave;
    private double payLeaves;
    public double totalAmount;
    public double thirtyto60totalAmount;
    public double thirtyTotalAmount;
    public double fourteenTotalAmount;
    public double above60TotalAmount;
    protected double discountAmount;
	protected double paymentAmount;
	private double earnedLeave;
    
    protected int currentYear;
    private int numberOfDays;
    protected int smsCnt;
	protected int smsAlloted;
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	private int availableSMSCount;
	private int attendanceSubmittedClassesCount;
	
    private Date examDate[];
    protected Date feeDueDate[];
    protected Date startDate; // required
	protected Date endDate;
	protected Date fromDate;
	protected Date toDate;
	
    public boolean mailSent;
	public boolean tempBoolean;
	private boolean google = false;
	private boolean ajaxhistory = false;
	private long countryId;
	private String paymentDateStr;
	private double balanceAmount;

	protected char tempChar;
	
	private List rolesList;
	public List studentTransportTermsList;
	private List dashBoardAlertsList;
	private List TotalStudentsCount;
	private String paymentTime;
	private StudentFeeRefund studentFeeRefund;
	private String receiptGenarationDate;
	public String fullName;
	public String classAndSectionName;
	public List reminderDetails;
	public long studySubjectId;
	private TimeTableSettings timeTableSettings;
	private List timeTablePeriodList;
	private int timeTableDetailsCount;
	private List objectList1;
	public String classSectionNameId;
	public String studyClassSubjectId;
	public List<StaffSyllabusPlanner> syllabusPlannerList;
	public List questionBankDetails;
	public String selectedIncomeId;
	private List<Events> eventsList;
	
	 public List getRejectedList() {
			return rejectedList;
		}
		public void setRejectedList(List rejectedList) {
			this.rejectedList = rejectedList;
		}
	public long getStudySubjectId() {
		return studySubjectId;
	}
	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
	}
	public List getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(List messageDetails) {
		this.messageDetails = messageDetails;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	 
    public String getClassAndSectionName() {
		return classAndSectionName;
	}

	public void setClassAndSectionName(String classAndSectionName) {
		this.classAndSectionName = classAndSectionName;
	}

	
	/**
	 * @param webUserMetaDataTaskExecutor the webUserMetaDataTaskExecutor to set
	 */
		public void setAccountManager(AccountManager accountManager) {
			this.accountManager = accountManager;
		}

		/**
		 * @param webUserMetaDataTaskExecutor the webUserMetaDataTaskExecutor to set
		 */
		public void setWebUserMetaDataTaskExecutor(WebUserMetaDataTaskExecutor webUserMetaDataTaskExecutor) {
			this.webUserMetaDataTaskExecutor = webUserMetaDataTaskExecutor;
		}
	
	public long getStudentSize() {
		return studentSize;
	}

	public void setStudentSize(long studentSize) {
		this.studentSize = studentSize;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public ClassNameAutoAcademicNumberVO getClassNameAutoAcademicNumberVO() {
		return classNameAutoAcademicNumberVO;
	}

	public void setClassNameAutoAcademicNumberVO(
			ClassNameAutoAcademicNumberVO classNameAutoAcademicNumberVO) {
		this.classNameAutoAcademicNumberVO = classNameAutoAcademicNumberVO;
	}

	public AdmissionNumberSettings getAdmissionNumberSettings() {
		return admissionNumberSettings;
	}

	public void setAdmissionNumberSettings(
			AdmissionNumberSettings admissionNumberSettings) {
		this.admissionNumberSettings = admissionNumberSettings;
	}

	public int getAvailableSMSCount() {
		return availableSMSCount;
	}

	public void setAvailableSMSCount(int availableSMSCount) {
		this.availableSMSCount = availableSMSCount;
	}

	public DeleteStudentPaymentVo getDeleteStudentPaymentVo() {
		return deleteStudentPaymentVo;
	}

	public void setDeleteStudentPaymentVo(
			DeleteStudentPaymentVo deleteStudentPaymentVo) {
		this.deleteStudentPaymentVo = deleteStudentPaymentVo;
	}

	public ViewStudentClassDetails getViewStudentClassDetails() {
		return viewStudentClassDetails;
	}

	public void setViewStudentClassDetails(ViewStudentClassDetails viewStudentClassDetails) {
		this.viewStudentClassDetails = viewStudentClassDetails;
	}

	public SyllabusTypeSchoolCode getSyllabusTypeSchoolCode() {
		return syllabusTypeSchoolCode;
	}

	public void setSyllabusTypeSchoolCode(
			SyllabusTypeSchoolCode syllabusTypeSchoolCode) {
		this.syllabusTypeSchoolCode = syllabusTypeSchoolCode;
	}

	public String getTempString1() {
		return tempString1;
	}

	public void setTempString1(String tempString1) {
		this.tempString1 = tempString1;
	}

	public List<AdmissionInquiry> getAdmissionInquiryList() {
		if (ObjectFunctions.isNullOrEmpty(this.admissionInquiryList)) {
			this.admissionInquiryList = new ArrayList();
		}
		return admissionInquiryList;
	}

	public void setAdmissionInquiryList(List<AdmissionInquiry> admissionInquiryList) {
		this.admissionInquiryList = admissionInquiryList;
	}

	public AdmissionInquiry getAdmissionInquiry() {
		return admissionInquiry;
	}

	public void setAdmissionInquiry(AdmissionInquiry admissionInquiry) {
		this.admissionInquiry = admissionInquiry;
	}

	public List<ViewStudentsLatestExamMarksDetails> getViewStudentsLatestExamMarksDetailsList() {
		return viewStudentsLatestExamMarksDetailsList;
	}

	public void setViewStudentsLatestExamMarksDetailsList(
			List<ViewStudentsLatestExamMarksDetails> viewStudentsLatestExamMarksDetailsList) {
		this.viewStudentsLatestExamMarksDetailsList = viewStudentsLatestExamMarksDetailsList;
	}

	
	public CustomerBankAccountDetailsVO getBankAccountDetailsVO() {
		return bankAccountDetailsVO;
	}

	public void setBankAccountDetailsVO(CustomerBankAccountDetailsVO bankAccountDetailsVO) {
		this.bankAccountDetailsVO = bankAccountDetailsVO;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public ChallanaPayment getChallanaPayment() {
		return challanaPayment;
	}

	public void setChallanaPayment(ChallanaPayment challanaPayment) {
		this.challanaPayment = challanaPayment;
	}

	public FinancialYearVO getFinancialYearVO() {
		return financialYearVO;
	}

	public void setFinancialYearVO(FinancialYearVO financialYearVO) {
		this.financialYearVO = financialYearVO;
	}

	public ClassAssignment getClassAssignment() {
		return classAssignment;
	}
	public void setClassAssignment(ClassAssignment classAssignment) {
		this.classAssignment = classAssignment;
	}
	public String getInquiryStatus() {
		return inquiryStatus;
	}
	public void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}
	public List<String> getPhFileUploadFileName() {
		return phFileUploadFileName;
	}

	public void setPhFileUploadFileName(List<String> setPhFileUploadFileName) {
		this.phFileUploadFileName = phFileUploadFileName;
	}
	

public List<File> getPhFileUpload() {
		return phFileUpload;
	}

	public void setPhFileUpload(List<File> phFileUpload) {
		this.phFileUpload = phFileUpload;
	}

public List<String> getChkBoxNonTeachingRoleIds() {
	   
	   if(ObjectFunctions.isNullOrEmpty(this.chkBoxNonTeachingRoleIds)){
			this.chkBoxNonTeachingRoleIds=new ArrayList<String>();
		}
		return chkBoxNonTeachingRoleIds;
	}

	public void setChkBoxNonTeachingRoleIds(List<String> chkBoxNonTeachingRoleIds) {
		this.chkBoxNonTeachingRoleIds = chkBoxNonTeachingRoleIds;
	}

public EventsVO getEventsVO() {
		return eventsVO;
	}

	public void setEventsVO(EventsVO eventsVO) {
		this.eventsVO = eventsVO;
	}

public ViewClassAssignmentDetails getViewClassAssignmentDetails() {
		return viewClassAssignmentDetails;
	}

	public void setViewClassAssignmentDetails(
			ViewClassAssignmentDetails viewClassAssignmentDetails) {
		this.viewClassAssignmentDetails = viewClassAssignmentDetails;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

  public List<Country> getCountryList() {
	   if (ObjectFunctions.isNullOrEmpty(this.countryList)) {
			this.countryList = new ArrayList<Country>();
		}
		return this.countryList;
	}

	public void setCountryList(List countryList) {
		this.countryList = countryList;
	}
    
    
    
    public CustomerEnrollmentRequest getCustomerEnrollmentRequest() {
		return customerEnrollmentRequest;
	}

	public void setCustomerEnrollmentRequest(
			CustomerEnrollmentRequest customerEnrollmentRequest) {
		this.customerEnrollmentRequest = customerEnrollmentRequest;
	}

	public InviteCustomerEnrollment getInviteCustomerEnrollment() {
		return inviteCustomerEnrollment;
	}

	public void setInviteCustomerEnrollment(
			InviteCustomerEnrollment inviteCustomerEnrollment) {
		this.inviteCustomerEnrollment = inviteCustomerEnrollment;
	}

	public String getTempString2() {
		return tempString2;
	}

	public String getTempString3() {
		return tempString3;
	}

	public void setTempString3(String tempString3) {
		this.tempString3 = tempString3;
	}

	public void setTempString2(String tempString2) {
		this.tempString2 = tempString2;
	}

	public TicketDetailsVO getTicketDetailsVO() {
		return ticketDetailsVO;
	}

	public void setTicketDetailsVO(TicketDetailsVO ticketDetailsVO) {
		this.ticketDetailsVO = ticketDetailsVO;
	}

	public SchoolVideos getSchoolVideos() {
		return schoolVideos;
	}

	public void setSchoolVideos(SchoolVideos schoolVideos) {
		this.schoolVideos = schoolVideos;
	}
	
    public List<StudentDailyAttendance> getStudentDailyAttendanceList() {
    	if (ObjectFunctions.isNullOrEmpty(this.studentDailyAttendanceList)) {
			this.studentDailyAttendanceList = new ArrayList<StudentDailyAttendance>();
		}
		return studentDailyAttendanceList;
	}
	public void setStudentDailyAttendanceList(
			List<StudentDailyAttendance> studentDailyAttendanceList) {
		this.studentDailyAttendanceList = studentDailyAttendanceList;
	}
	public List<StudentMonthlyAttendance> getStudentMonthlyAttendanceList() {
		if (ObjectFunctions.isNullOrEmpty(this.studentMonthlyAttendanceList)) {
			this.studentMonthlyAttendanceList = new ArrayList<StudentMonthlyAttendance>();
		}
		
		return studentMonthlyAttendanceList;
	}

	public void setStudentMonthlyAttendanceList(
			List<StudentMonthlyAttendance> studentMonthlyAttendanceList) {
		this.studentMonthlyAttendanceList = studentMonthlyAttendanceList;
	}

	public List<SuspendAndBlacklistStudents> getSuspendBlackstudentList() {
    	if (ObjectFunctions.isNullOrEmpty(this.suspendBlackstudentList)) {
			this.suspendBlackstudentList = new ArrayList<SuspendAndBlacklistStudents>();
		}
		return suspendBlackstudentList;
	}

	public void setSuspendBlackstudentList(
			List<SuspendAndBlacklistStudents> suspendBlackstudentList) {
		this.suspendBlackstudentList = suspendBlackstudentList;
	}

	public SuspendAndBlacklistStudents getSuspendAndBlacklistStudents() {
		if(ObjectFunctions.isNullOrEmpty(this.suspendAndBlacklistStudents)){
			this.suspendAndBlacklistStudents=new SuspendAndBlacklistStudents();			
		}
		return suspendAndBlacklistStudents;
	}

	public void setSuspendAndBlacklistStudents(
			SuspendAndBlacklistStudents suspendAndBlacklistStudents) {
		this.suspendAndBlacklistStudents = suspendAndBlacklistStudents;
	}  
    
    public List<FinancialYear> getFinancialYearList() {
    	if (ObjectFunctions.isNullOrEmpty(this.financialYearList)) {
			this.financialYearList = new ArrayList<FinancialYear>();
		}
		return financialYearList;
	}

	public void setFinancialYearList(List<FinancialYear> financialYearList) {
		this.financialYearList = financialYearList;
	}

	public ClassAttendanceMainVO getClassAttendanceMainVO() {
		return classAttendanceMainVO;
	}

	public void setClassAttendanceMainVO(ClassAttendanceMainVO classAttendanceMainVO) {
		this.classAttendanceMainVO = classAttendanceMainVO;
	} 
    public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}

	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}

	public StaffVO getStaffVo() {
		return staffVo;
	}

	public void setStaffVo(StaffVO staffVo) {
		this.staffVo = staffVo;
	}

	public StaffHistoryVO getStaffHistoryVo() {
		return staffHistoryVo;
	}

	public void setStaffHistoryVo(StaffHistoryVO staffHistoryVo) {
		this.staffHistoryVo = staffHistoryVo;
	}

	public StaffStatutoryVO getStaffStatutoryVo() {
		return staffStatutoryVo;
	}

	public void setStaffStatutoryVo(StaffStatutoryVO staffStatutoryVo) {
		this.staffStatutoryVo = staffStatutoryVo;
	}

	public SalaryVO getSalaryVo() {
		return salaryVo;
	}

	public void setSalaryVo(SalaryVO salaryVo) {
		this.salaryVo = salaryVo;
	}

	public List<StudySubjectVO> getStudySubjectListVo() {
		return studySubjectListVo;
	}

	public void setStudySubjectListVo(List<StudySubjectVO> studySubjectListVo) {
		this.studySubjectListVo = studySubjectListVo;
	}
    
	public StudentVo getStudentVo() {
		return studentVo;
	}

	public void setStudentVo(StudentVo studentVo) {
		this.studentVo = studentVo;
	}

	public AddressVO getAddressVo() {
		return addressVo;
	}

	public void setAddressVo(AddressVO addressVo) {
		this.addressVo = addressVo;
	}

	public PersonVO getPersonVo() {
		return personVo;
	}

	public void setPersonVo(PersonVO personVo) {
		this.personVo = personVo;
	}

	public UserVO getUserVo() {
		if(ObjectFunctions.isNullOrEmpty(this.userVo)){
			this.userVo=new UserVO();			
		}
		
		return userVo;
	}

	public void setUserVo(UserVO userVo) {
		this.userVo = userVo;
	}

	public DefaultScoreCardTemplatesVO getDefaultScoreCardTemplatesVO() {
		return defaultScoreCardTemplatesVO;
	}

	public void setDefaultScoreCardTemplatesVO(DefaultScoreCardTemplatesVO defaultScoreCardTemplatesVO) {
		this.defaultScoreCardTemplatesVO = defaultScoreCardTemplatesVO;
	}
	public HostelStudents getHostelStudents() {
		return hostelStudents;
	}

	public void setHostelStudents(HostelStudents hostelStudents) {
		this.hostelStudents = hostelStudents;
	}

	public List<SMSServiceProviders> getSmsServiceProvidersList() {
		if (ObjectFunctions.isNullOrEmpty(this.smsServiceProvidersList)) {
			this.smsServiceProvidersList = new ArrayList<SMSServiceProviders>();
		}
		return this.smsServiceProvidersList;
	}

	public void setSmsServiceProvidersList(List smsServiceProvidersList) {
		this.smsServiceProvidersList = smsServiceProvidersList;
	}

	public List<SocialDiscussionsComments> getSocialDiscussionsCommentsList() {
		if (ObjectFunctions.isNullOrEmpty(this.socialDiscussionsCommentsList)) {
			this.socialDiscussionsCommentsList = new ArrayList();
		}
		return this.socialDiscussionsCommentsList;
	}
	public void setSocialDiscussionsCommentsList(List<SocialDiscussionsComments> socialDiscussionsCommentsList) {
		this.socialDiscussionsCommentsList = socialDiscussionsCommentsList;
	}
	
	public List<ShareUserActivitiesComments> getShareUserActivitiesCommentsList() {
		if (ObjectFunctions.isNullOrEmpty(this.shareUserActivitiesCommentsList)) {
			this.shareUserActivitiesCommentsList = new ArrayList();
		}
		return this.shareUserActivitiesCommentsList;
	}

	public void setShareUserActivitiesCommentsList(List<ShareUserActivitiesComments> shareUserActivitiesCommentsList) {
		this.shareUserActivitiesCommentsList = shareUserActivitiesCommentsList;
	}
	
	public SocialDiscussionsVO getSocialDiscussionsVO() {
		return socialDiscussionsVO;
	}

	public void setSocialDiscussionsVO(SocialDiscussionsVO socialDiscussionsVO) {
		this.socialDiscussionsVO = socialDiscussionsVO;
	}

	public List<SocialDiscussionsVO> getSocialDiscussionsVOList() {
		if (ObjectFunctions.isNullOrEmpty(this.socialDiscussionsVOList)) {
			this.socialDiscussionsVOList = new ArrayList();
		}
		return socialDiscussionsVOList;
	}

	public void setSocialDiscussionsVOList(List<SocialDiscussionsVO> socialDiscussionsVOList) {
		this.socialDiscussionsVOList = socialDiscussionsVOList;
	}

	public SocialDiscussionsCommentsVO getSocialDiscussionsCommentsVO() {
		return socialDiscussionsCommentsVO;
	}

	public void setSocialDiscussionsCommentsVO(SocialDiscussionsCommentsVO socialDiscussionsCommentsVO) {
		this.socialDiscussionsCommentsVO = socialDiscussionsCommentsVO;
	}

	public List<SocialDiscussionsCommentsVO> getSocialDiscussionsCommentsVOList() {
		if (ObjectFunctions.isNullOrEmpty(this.socialDiscussionsCommentsVOList)) {
			this.socialDiscussionsCommentsVOList = new ArrayList();
		}
		return socialDiscussionsCommentsVOList;
	}

	public void setSocialDiscussionsCommentsVOList(List<SocialDiscussionsCommentsVO> socialDiscussionsCommentsVOList) {
		this.socialDiscussionsCommentsVOList = socialDiscussionsCommentsVOList;
	}

	public ShareUserActivitiesVO getShareUserActivitiesVO() {
		return shareUserActivitiesVO;
	}

	public void setShareUserActivitiesVO(ShareUserActivitiesVO shareUserActivitiesVO) {
		this.shareUserActivitiesVO = shareUserActivitiesVO;
	}

	public List<ShareUserActivitiesVO> getShareUserActivitiesVOList() {
		if (ObjectFunctions.isNullOrEmpty(this.shareUserActivitiesVOList)) {
			this.shareUserActivitiesVOList = new ArrayList();
		}
		return shareUserActivitiesVOList;
	}

	public void setShareUserActivitiesVOList(List<ShareUserActivitiesVO> shareUserActivitiesVOList) {
		this.shareUserActivitiesVOList = shareUserActivitiesVOList;
	}

	public ShareUserActivitiesCommentsVO getShareUserActivitiesCommentsVO() {
		return shareUserActivitiesCommentsVO;
	}

	public void setShareUserActivitiesCommentsVO(ShareUserActivitiesCommentsVO shareUserActivitiesCommentsVO) {
		this.shareUserActivitiesCommentsVO = shareUserActivitiesCommentsVO;
	}

	public List<ShareUserActivitiesCommentsVO> getShareUserActivitiesCommentsVOList() {
		if (ObjectFunctions.isNullOrEmpty(this.shareUserActivitiesCommentsVOList)) {
			this.shareUserActivitiesCommentsVOList = new ArrayList();
		}
		return shareUserActivitiesCommentsVOList;
	}

	public void setShareUserActivitiesCommentsVOList(List<ShareUserActivitiesCommentsVO> shareUserActivitiesCommentsVOList) {
		this.shareUserActivitiesCommentsVOList = shareUserActivitiesCommentsVOList;
	}
	 
	public MeetingDetails getMeetingDetails() {
		return meetingDetails;
	}

	public void setMeetingDetails(MeetingDetails meetingDetails) {
		this.meetingDetails = meetingDetails;
	}

	public MeetingRequests getMeetingRequests() {
		return meetingRequests;
	}

	public void setMeetingRequests(MeetingRequests meetingRequests) {
		this.meetingRequests = meetingRequests;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public BudgetRequest getBudgetRequest() {
		return budgetRequest;
	}

	public void setBudgetRequest(BudgetRequest budgetRequest) {
		this.budgetRequest = budgetRequest;
	}

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}
	
	public Particular getParticular() {
		return particular;
	}

	public void setParticular(Particular particular) {
		this.particular = particular;
	}

	public ParticularType getParticularType() {
		return particularType;
	}

	public void setParticularType(ParticularType particularType) {
		this.particularType = particularType;
	}

	public Organization getOrganizationObj() {
		return organizationObj;
	}

	public void setOrganizationObj(Organization organizationObj) {
		this.organizationObj = organizationObj;
	}

	public TreeMap<Long, String> getStudyClassesMap() {
		if(ObjectFunctions.isNullOrEmpty(this.studyClassesMap)){
			this.studyClassesMap = new TreeMap<Long, String>();
		}
		return this.studyClassesMap;
	}

	public void setStudyClassesMap(TreeMap<Long, String> studyClassesMap) {
		this.studyClassesMap = studyClassesMap;
	}

	public ProvisionItems getProvisionItems() {
		return provisionItems;
	}

	public void setProvisionItems(ProvisionItems provisionItems) {
		this.provisionItems = provisionItems;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Mess getMess() {
		return mess;
	}

	public void setMess(Mess mess) {
		this.mess = mess;
	}
	
	public MessFoodType getMessFoodType() {
		return messFoodType;
	}

	public void setMessFoodType(MessFoodType messFoodType) {
		this.messFoodType = messFoodType;
	}
	
	public StaffRoom getStaffRoom() {
		return staffRoom;
	}

	public void setStaffRoom(StaffRoom staffRoom) {
		this.staffRoom = staffRoom;
	}
	
	public StudentPocketMoney getStudentPocketMoney() {
		return studentPocketMoney;
	}

	public void setStudentPocketMoney(StudentPocketMoney studentPocketMoney) {
		this.studentPocketMoney = studentPocketMoney;
	}
	
	public StudentCurricularActivities getStudentCurricularActivities() {
		return studentCurricularActivities;
	}

	public void setStudentCurricularActivities(
			StudentCurricularActivities studentCurricularActivities) {
		this.studentCurricularActivities = studentCurricularActivities;
	}

	public WashRoom getWashRoom() {
		return washRoom;
	}

	public void setWashRoom(WashRoom washRoom) {
		this.washRoom = washRoom;
	}

	public StaffCurricularActivities getStaffCurricularActivities() {
		return staffCurricularActivities;
	}

	public void setStaffCurricularActivities(
			StaffCurricularActivities staffCurricularActivities) {
		this.staffCurricularActivities = staffCurricularActivities;
	}

	public StudentSiblings getStudentSiblings() {
		return studentSiblings;
	}

	public void setStudentSiblings(StudentSiblings studentSiblings) {
		this.studentSiblings = studentSiblings;
	}

	public StaffHistory getStaffHistory() {
		return staffHistory;
	}

	public void setStaffHistory(StaffHistory staffHistory) {
		this.staffHistory = staffHistory;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/**
	 * @return the ws
	 */
	public WorkbookSettings getWs() {
		return ws;
	}

	/**
	 * @param ws the ws to set
	 */
	public void setWs(WorkbookSettings ws) {
		this.ws = ws;
	}

	/**
	 * @return the workbook
	 */
	public Workbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook the workbook to set
	 */
	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

		
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}		
	public double getEarnedLeave() {
		return earnedLeave;
	}
	public void setEarnedLeave(double earnedLeave) {
		this.earnedLeave = earnedLeave;
	}
	public String getFeePaymentType() {
		return feePaymentType;
	}
	public void setFeePaymentType(String feePaymentType) {
		this.feePaymentType = feePaymentType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String s) {
		this.subject = s;
	}
	
	public StudyAndBonafiedSettings getStudyAndBonafiedSettings() {
		return studyAndBonafiedSettings;
	}

	public void setStudyAndBonafiedSettings(
			StudyAndBonafiedSettings studyAndBonafiedSettings) {
		this.studyAndBonafiedSettings = studyAndBonafiedSettings;
	}

	public HallTicketSettings getHallTicketSettings() {
		return hallTicketSettings;
	}

	public void setHallTicketSettings(HallTicketSettings hallTicketSettings) {
		this.hallTicketSettings = hallTicketSettings;
	}

	public TransferStudent getTransferStudent() {
		return transferStudent;
	}

	public void setTransferStudent(TransferStudent transferStudent) {
		this.transferStudent = transferStudent;
	}
	
	public MasterCustomer getMasterCustomer() {
		return masterCustomer;
	}

	public void setMasterCustomer(MasterCustomer masterCustomer) {
		this.masterCustomer = masterCustomer;
	}
	
	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getTeachingRoleName() {
		return teachingRoleName;
	}

	public void setTeachingRoleName(String teachingRoleName) {
		this.teachingRoleName = teachingRoleName;
	}
	public TreeMap<String, String> getStaffRoles() {
		if (ObjectFunctions.isNullOrEmpty(this.staffRoles)) {
			return this.staffRoles = new TreeMap<String, String>();
		}
		return this.staffRoles;
	}

	public void setStaffRoles(TreeMap<String, String> staffRoles) {
		this.staffRoles = staffRoles;
	}
	public TreeMap<String, String> getTeachingRoleMap() {
		if (ObjectFunctions.isNullOrEmpty(this.teachingRoleMap)) {
			this.teachingRoleMap = new TreeMap<String, String>();
		}
		return teachingRoleMap;
	}

	public void setTeachingRoleMap(TreeMap<String, String> teachingRoleMap) {
		this.teachingRoleMap = teachingRoleMap;
	}

	public TreeMap<String, String> getNonTeachingRoleMap() {
		if (ObjectFunctions.isNullOrEmpty(this.nonTeachingRoleMap)) {
			return this.nonTeachingRoleMap = new TreeMap<String, String>();
		}
		return this.nonTeachingRoleMap;
	}

	public void setNonTeachingRoleMap(TreeMap<String, String> nonTeachingRoleMap) {
		this.nonTeachingRoleMap = nonTeachingRoleMap;
	}
	public TreeMap<String, String> getManagementRoleMap() {
		if (ObjectFunctions.isNullOrEmpty(this.managementRoleMap)) {
			return this.managementRoleMap = new TreeMap<String, String>();
		}
		return managementRoleMap;
	}

	public void setManagementRoleMap(TreeMap<String, String> managementRoleMap) {
		this.managementRoleMap = managementRoleMap;
	}
	 public SchoolFeeSetting getSchoolFeeSetting() {
			return schoolFeeSetting;
		}

		public void setSchoolFeeSetting(SchoolFeeSetting schoolFeeSetting) {
			this.schoolFeeSetting = schoolFeeSetting;
		}
	    public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public int getH() {
			return h;
		}

		public void setH(int h) {
			this.h = h;
		}
		public List<Route> getRouteList() {
			if(ObjectFunctions.isNullOrEmpty(this.routeList))
			{
				this.routeList = new ArrayList<Route>(); 
			}
			return this.routeList;
		}

		public void setRouteList(List<Route> routeList) {
			this.routeList = routeList;
		}

		public List<String> getChkBoxSelectedAccountIds() {
			if(ObjectFunctions.isNullOrEmpty(this.chkBoxSelectedAccountIds)){
				this.chkBoxSelectedAccountIds=new ArrayList<String>();
			}
			return this.chkBoxSelectedAccountIds;
		}
		
		public void setChkBoxSelectedAccountIds(List<String> chkBoxSelectedAccountIds) {
			this.chkBoxSelectedAccountIds = chkBoxSelectedAccountIds;
		}
		
		public List<String> getChkBoxClassSelectedIds() {
			if(ObjectFunctions.isNullOrEmpty(this.chkBoxClassSelectedIds)){
				this.chkBoxClassSelectedIds=new ArrayList<String>();
			}
			return this.chkBoxClassSelectedIds;
		}

		public void setChkBoxClassSelectedIds(List<String> chkBoxClassSelectedIds) {
			this.chkBoxClassSelectedIds = chkBoxClassSelectedIds;
		}

	  	
	  	public long getCurrentUserCustId() {
			return currentUserCustId;
		}

		public void setCurrentUserCustId(long currentUserCustId) {
			this.currentUserCustId = currentUserCustId;
		}
	  	
	  	public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		} 
		
	  	public long getBankId() {
			return bankId;
		}

		public void setBankId(long bankId) {
			this.bankId = bankId;
		} 
	  	public List<OverAllGrades> getOverAllGradesList() {
			return overAllGradesList;
		}

		public void setOverAllGradesList(List<OverAllGrades> overAllGradesList) {
			this.overAllGradesList = overAllGradesList;
		}

		public OverAllGrades getOverAllGrades() {
			return overAllGrades;
		}

		public void setOverAllGrades(OverAllGrades overAllGrades) {
			this.overAllGrades = overAllGrades;
		}
	  	/**
		 * @return the schoolFeeList
		 */
		public List getSchoolFeeList() {
			if (ObjectFunctions.isNullOrEmpty(this.schoolFeeList)) {
				this.schoolFeeList = new ArrayList();
			}
			return schoolFeeList;
		}

		/**
		 * @param schoolFeeList
		 *            the schoolFeeList to set
		 */
		public void setSchoolFeeList(List schoolFeeList) {
			this.schoolFeeList = schoolFeeList;
		}
	  	public List getTransportSchoolFeeList() {
			if (ObjectFunctions.isNullOrEmpty(this.transportSchoolFeeList)) {
				this.transportSchoolFeeList = new ArrayList();
			}
			return transportSchoolFeeList;
		}

		public void setTransportSchoolFeeList(List transportSchoolFeeList) {
			this.transportSchoolFeeList = transportSchoolFeeList;
		}

	  	/**
		 * @return the viewStudentMarksDetailsList
		 */
		public List<ViewStudentMarksDetails> getViewStudentMarksDetailsList() {
			if (ObjectFunctions.isNullOrEmpty(this.viewStudentMarksDetailsList)) {
	  		    this.viewStudentMarksDetailsList = new ArrayList<ViewStudentMarksDetails>();
	  		}
			return viewStudentMarksDetailsList;
		}

		/**
		 * @param viewStudentMarksDetailsList the viewStudentMarksDetailsList to set
		 */
		public void setViewStudentMarksDetailsList(
				List<ViewStudentMarksDetails> viewStudentMarksDetailsList) {
			this.viewStudentMarksDetailsList = viewStudentMarksDetailsList;
		}
	  	
	  	
	  	public TaxAccount getTaxAccount() {
			return taxAccount;
		}

		public void setTaxAccount(TaxAccount taxAccount) {
			this.taxAccount = taxAccount;
		}

		public PDFGenerator getpDFGenerator() {
			return pDFGenerator;
		}

		public void setpDFGenerator(PDFGenerator pDFGenerator) {
			this.pDFGenerator = pDFGenerator;
		}

	  	public String getStaffRoleName() {
			return staffRoleName;
		}

		public void setStaffRoleName(String staffRoleName) {
			this.staffRoleName = staffRoleName;
		}
	  	public Payroll getPayroll() {
			return payroll;
		}

		public void setPayroll(Payroll payroll) {
			this.payroll = payroll;
		}

		public List<Payroll> getPayrollList() {
	  		
	  		if (ObjectFunctions.isNullOrEmpty(this.payrollList)) {
	  		    this.payrollList = new ArrayList<Payroll>();
	  		}
			return payrollList;
		}

		public void setPayrollList(List<Payroll> payrollList) {
			this.payrollList = payrollList;
		}
		public List<PayrollSettings> getPayrollSettingsList() {
			if (ObjectFunctions.isNullOrEmpty(this.payrollSettingsList)) {
	  		    this.payrollSettingsList = new ArrayList<PayrollSettings>();
	  		}
			return this.payrollSettingsList;
		}

		public void setPayrollSettingsList(List<PayrollSettings> payrollSettingsList) {
			this.payrollSettingsList = payrollSettingsList;
		}
		public List<StaffStatutory> getStaffStatutoryList()
	  	{
	  		if (ObjectFunctions.isNullOrEmpty(this.staffStatutoryList)) {
	  		    this.staffStatutoryList = new ArrayList<StaffStatutory>();
	  		}
	  		return this.staffStatutoryList;
	  	 }

		public void setStaffStatutoryList(List<StaffStatutory> staffStatutoryList) {
			this.staffStatutoryList = staffStatutoryList;
		}

		public StaffStatutory getStaffStatutory() {
			return staffStatutory;
		}

		public void setStaffStatutory(StaffStatutory staffStatutory) {
			this.staffStatutory = staffStatutory;
		}

		public List<PayrollTypes> getPayrollTypesList() 
	  	{
	  		if (ObjectFunctions.isNullOrEmpty(this.payrollTypesList)) {
	  		    this.payrollTypesList = new ArrayList<PayrollTypes>();
	  		}
	  		return this.payrollTypesList;
	  	 }

		public void setPayrollTypesList(List<PayrollTypes> payrollTypesList) {
			this.payrollTypesList = payrollTypesList;
		}

		public PayrollTypes getPayrollTypes() 
	  	{
	  		if (ObjectFunctions.isNullOrEmpty(this.payrollTypes)) {
				this.payrollTypes = new PayrollTypes();
			}
			return this.payrollTypes;
		}

		public void setPayrollTypes(PayrollTypes payrollTypes) {
			this.payrollTypes = payrollTypes;
		}

		public Salary getSalary() {
			if (ObjectFunctions.isNullOrEmpty(this.salary)) {
				this.salary = new Salary();
			}
			return this.salary;
		}
		
		public void setSalary(Salary salary) {
			this.salary = salary;
		}
		
		public List<ViewClassSubjectsSettings> getViewClassSubjectsSettings() {
			return viewClassSubjectsSettings;
		}
		public void setViewClassSubjectsSettings(
				List<ViewClassSubjectsSettings> viewClassSubjectsSettings) {
			this.viewClassSubjectsSettings = viewClassSubjectsSettings;
		}
		public HashMap<Long, Integer> getTempHashMap() {
			if(ObjectFunctions.isNullOrEmpty(this.tempHashMap)){
				this.tempHashMap = new HashMap<Long, Integer>();
			}
			return this.tempHashMap;
		}
		public void setTempHashMap(HashMap<Long, Integer> tempHashMap) {
			this.tempHashMap = tempHashMap;
		}
		public TimeTable getTimeTable() {
			return timeTable;
		}
		public void setTimeTable(TimeTable timeTable) {
			this.timeTable = timeTable;
		}
		public char getTempChar() {
			return tempChar;
		}
		public void setTempChar(char tempChar) {
			this.tempChar = tempChar;
		}
		/**
		 * @return the organization
		 */
		public String getOrganization() {
			return organization;
		}
		/**
		 * @param organization the organization to set
		 */
		public void setOrganization(String organization) {
			this.organization = organization;
		}
	    public String getClassSectionId() {
			return classSectionId;
		}

		public void setClassSectionId(String classSectionId) {
			this.classSectionId = classSectionId;
		}
		/**
		 * @return the smsServiceProviders
		 */
		public SMSServiceProviders getSmsServiceProvider() {
			return smsServiceProvider;
		}
		/**
		 * @param smsServiceProviders the smsServiceProviders to set
		 */
		public void setSmsServiceProvider(SMSServiceProviders smsServiceProvider) {
			this.smsServiceProvider = smsServiceProvider;
		}
		public String getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
		public String getFeeDDNumber() {
			return feeDDNumber;
		}

		public void setFeeDDNumber(String feeDDNumber) {
			this.feeDDNumber = feeDDNumber;
		} 
		public String getChequeNumber() {
			return chequeNumber;
		}
		public void setChequeNumber(String chequeNumber) {
			this.chequeNumber = chequeNumber;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}	
		public double getDiscountAmount() {
			return discountAmount;
		}
		public void setDiscountAmount(double discountAmount) {
			this.discountAmount = discountAmount;
		}

		public double getPaymentAmount() {
			return paymentAmount;
		}

		public void setPaymentAmount(double paymentAmount) {
			this.paymentAmount = paymentAmount;
		}
		public Map getJsonResult() {
			if (ObjectFunctions.isNullOrEmpty(jsonResult)) {
				jsonResult = new HashMap();
			}
			return jsonResult;
		}
		
		/**
		 * @return the visitors
		 */
		public Visitors getVisitors() {
			return visitors;
		}

		/**
		 * @param visitors the visitors to set
		 */
		public void setVisitors(Visitors visitors) {
			this.visitors = visitors;
		}

		public List getStaffFeeList() {
			if (ObjectFunctions.isNullOrEmpty(this.staffFeeList)) {
				this.staffFeeList = new ArrayList();
			}
			return staffFeeList;
		}

		public void setStaffFeeList(List staffFeeList) {
			this.staffFeeList = staffFeeList;
		}

		public List getStaffPaymentList() {
			if (ObjectFunctions.isNullOrEmpty(this.staffPaymentList)) {
				this.staffPaymentList = new ArrayList();
			}
			return staffPaymentList;
		}

		public void setStaffPaymentList(List staffPaymentList) {
			this.staffPaymentList = staffPaymentList;
		}
	  	
		/**
		 * @return the studentOut
		 */
		public StudentOut getStudentOut() {
		    return studentOut;
		}

		/**
		 * @param studentOut the studentOut to set
		 */
		public void setStudentOut(StudentOut studentOut) {
		    this.studentOut = studentOut;
		}

		/**
		 * @return the acdmcYearRange
		 */
		public String getAcdmcYearRange() {
			return acdmcYearRange;
		}

		/**
		 * @param acdmcYearRange the acdmcYearRange to set
		 */
		public void setAcdmcYearRange(String acdmcYearRange) {
			this.acdmcYearRange = acdmcYearRange;
		}

		/**
		 * @return the currentAcademicId
		 */
		public long getCurrentAcademicId() {
			return currentAcademicId;
		}

		/**
		 * @param currentAcademicId the currentAcademicId to set
		 */
		public void setCurrentAcademicId(long currentAcademicId) {
			this.currentAcademicId = currentAcademicId;
		}
	    /**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @return the collectionAndFeeDuesList
		 */
		public HashMap<String, String> getCollectionAndFeeDuesList() {
			if (ObjectFunctions.isNullOrEmpty(this.collectionAndFeeDuesList)) {
				return this.collectionAndFeeDuesList = new HashMap<String, String>();
			}
			return collectionAndFeeDuesList;
		}

		/**
		 * @param collectionAndFeeDuesList the collectionAndFeeDuesList to set
		 */
		public void setCollectionAndFeeDuesList(
				HashMap<String, String> collectionAndFeeDuesList) {
			this.collectionAndFeeDuesList = collectionAndFeeDuesList;
		}

		/**
		 * @return the smsFeeReports
		 */
		public HashMap<String, String> getSmsFeeReports() {
			if (ObjectFunctions.isNullOrEmpty(this.smsFeeReports)) {
				return this.smsFeeReports = new HashMap<String, String>();
			}
			return smsFeeReports;
		}

		/**
		 * @param smsFeeReports the smsFeeReports to set
		 */
		public void setSmsFeeReports(HashMap<String, String> smsFeeReports) {
			this.smsFeeReports = smsFeeReports;
		}
		/**
		 * @return the customerList
		 */
		public List<Customer> getCustomerList() {
			return customerList;
		}
		/**
		 * @param customerList the customerList to set
		 */
		public void setCustomerList(List<Customer> customerList) {
			this.customerList = customerList;
		}
		/**
	     * @return the buildingList
	     */
	    public List<Building> getBuildingList() {
		if (ObjectFunctions.isNullOrEmpty(this.buildingList)) {
		    this.buildingList = new ArrayList<Building>();
		}
		return this.buildingList;
	    }
	    /**
	     * @param buildingList
	     *            the buildingList to set
	     */
	    public void setBuildingList(List<Building> buildingList) {
		this.buildingList = buildingList;
	    }
			
		/**
			 * @return the tempActivePrekgClassList
			 */
			public List getTempActivePrekgClassList() {
				return tempActivePrekgClassList;
			}

			/**
			 * @param tempActivePrekgClassList
			 *            the tempActivePrekgClassList to set
			 */
			public void setTempActivePrekgClassList(List tempActivePrekgClassList) {
				this.tempActivePrekgClassList = tempActivePrekgClassList;
			}
			/**
			 * @return the classFeeTypeList
			 */
			public List getClassFeeTypeList() {
				if (ObjectFunctions.isNullOrEmpty(this.classFeeTypeList)) {
					this.classFeeTypeList = new ArrayList();
				}
				return classFeeTypeList;
			}

			/**
			 * @param classFeeTypeList the classFeeTypeList to set
			 */
			public void setClassFeeTypeList(List classFeeTypeList) {
				this.classFeeTypeList = classFeeTypeList;
			}

		/**
		 * @return the queryString
		 */
		public String getQueryString() {
			return queryString;
		}

		/**
		 * @param queryString the queryString to set
		 */
		public void setQueryString(String queryString) {
			this.queryString = queryString;
		}

		/**
		 * @return the building
		 */
		public Building getBuilding() {
		    return building;
		}

		/**
		 * @param building the building to set
		 */
		public void setBuilding(Building building) {
		    this.building = building;
		}

		/**
		 * @return the floor
		 */
		public Floor getFloor() {
		    return floor;
		}

		/**
		 * @param floor the floor to set
		 */
		public void setFloor(Floor floor) {
		    this.floor = floor;
		}

		/**
		 * @return the room
		 */
		public Room getRoom() {
		    return room;
		}

		/**
		 * @param room the room to set
		 */
		public void setRoom(Room room) {
		    this.room = room;
		}

		/**
		 * @param hostelManager the hostelManager to set
		 */
		public void setHostelManager(HostelManager hostelManager) {
		    this.hostelManager = hostelManager;
		}
		
	    /**
		 * @param communicationManager the communicationManager to set
		 */
		public void setCommunicationManager(CommunicationManager communicationManager) {
			this.communicationManager = communicationManager;
		}

		/**
	     * @return the bed
	     */
	    public Bed getBed() {
	        return bed;
	    }
	    /**
	     * @param bed the bed to set
	     */
	    public void setBed(Bed bed) {
	        this.bed = bed;
	    }
	    public FoodTypes getFoodTypes() {
			return foodTypes;
		}
		public void setFoodTypes(FoodTypes foodTypes) {
			this.foodTypes = foodTypes;
		}
		
		public SyllabusType getSyllabusType() {
			return syllabusType;
		}
		public void setSyllabusType(SyllabusType syllabusType) {
			this.syllabusType = syllabusType;
		}
		public List<SyllabusType> getSyllabusTypeList() 
	    {
			if(ObjectFunctions.isNullOrEmpty(this.syllabusTypeList))
			{
				this.syllabusTypeList=new ArrayList<SyllabusType>();
			}
			return this.syllabusTypeList;
		}
		public void setSyllabusTypeList(List<SyllabusType> syllabusTypeList) {
			this.syllabusTypeList = syllabusTypeList;
		}
		
		public List getWeekDayList() {
			if(ObjectFunctions.isNullOrEmpty(this.weekDayList)){
				this.weekDayList=new ArrayList();
			}
			return weekDayList;
		}

		public void setWeekDayList(List weekDayList) {
			this.weekDayList = weekDayList;
		}
		/**
		 * @return the organizationTypesList
		 */
		public List<OrganizationTypes> getOrganizationTypesList() {
			if(ObjectFunctions.isNullOrEmpty(this.organizationTypesList)){
				this.organizationTypesList=new ArrayList();
			}
			return organizationTypesList;
		}

		/**
		 * @param organizationTypesList the organizationTypesList to set
		 */
		public void setOrganizationTypesList(
				List<OrganizationTypes> organizationTypesList) {
			this.organizationTypesList = organizationTypesList;
		}

		/**
		     * @return the hostel
		     */
		    public Hostel getHostel() {
		        return hostel;
		    }

		    /**
		     * @param hostel the hostel to set
		     */
		    public void setHostel(Hostel hostel) {
		        this.hostel = hostel;
		    }
		/**
		 * @return the studentFeePaidDetails
		 */
		public StudentFeePaidDetails getStudentFeePaidDetails() {
			return studentFeePaidDetails;
		}

		/**
		 * @param studentFeePaidDetails the studentFeePaidDetails to set
		 */
		public void setStudentFeePaidDetails(StudentFeePaidDetails studentFeePaidDetails) {
			this.studentFeePaidDetails = studentFeePaidDetails;
		}
		/**
		 * @return the commonType
		 */
		public CommonType getCommonType() {
			return commonType;
		}

		/**
		 * @param commonType the commonType to set
		 */
		public void setCommonType(CommonType commonType) {
			this.commonType = commonType;
		}
		public CastSettings getCastSettings() {
			return castSettings;
		}

		/**
		 * @param castSettings the castSettings to set
		 */
		public void setCastSettings(CastSettings castSettings) {
			this.castSettings = castSettings;
		}
		
		
		/**
		 * @return the tempBoolean
		 */
		public boolean isTempBoolean() {
			return tempBoolean;
		}

		/**
		 * @param tempBoolean the tempBoolean to set
		 */
		public void setTempBoolean(boolean tempBoolean) {
			this.tempBoolean = tempBoolean;
		}

		/**
		 * @return the usrChgedAcademicId
		 */
		public long getUsrChgedAcademicId() {
			return usrChgedAcademicId;
		}

		/**
		 * @param usrChgedAcademicId the usrChgedAcademicId to set
		 */
		public void setUsrChgedAcademicId(long usrChgedAcademicId) {
			this.usrChgedAcademicId = usrChgedAcademicId;
		}

		/**
		 * @return the subCastSettings
		 */
		public SubCastSettings getSubCastSettings() {
			return subCastSettings;
		}

		/**
		 * @param subCastSettings the subCastSettings to set
		 */
		public void setSubCastSettings(SubCastSettings subCastSettings) {
			this.subCastSettings = subCastSettings;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public Date getFromDate() {
			return fromDate;
		}

		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}

		public Date getToDate() {
			return toDate;
		}

		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}

		/**
		 * @return the viewStudentMarksDetails
		 */
		public ViewStudentMarksDetails getViewStudentMarksDetails() {
			return viewStudentMarksDetails;
		}

		/**
		 * @param viewStudentMarksDetails the viewStudentMarksDetails to set
		 */
		public void setViewStudentMarksDetails(
				ViewStudentMarksDetails viewStudentMarksDetails) {
			this.viewStudentMarksDetails = viewStudentMarksDetails;
		}
		
		public String getWebSiteUrl() {
			return webSiteUrl;
		}

		public void setWebSiteUrl(String webSiteUrl) {
			this.webSiteUrl = webSiteUrl;
		}
		public String getAttendanceType() {
			return attendanceType;
		}

		public void setAttendanceType(String attendanceType) {
			this.attendanceType = attendanceType;
		}

		/**
		 * @return the studentPayment
		 */
		public StudentPayment getStudentPayment() {
			return studentPayment;
		}

		/**
		 * @param studentPayment the studentPayment to set
		 */
		public void setStudentPayment(StudentPayment studentPayment) {
			this.studentPayment = studentPayment;
		}
		public String getExamType() {
			return examType;
		}

		public void setExamType(String examType) {
			this.examType = examType;
		}
		public String getUsername() {
			return username;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}
		/**
		 * @return the staffAccount
		 */
		public User getStaffAccount() {
			return staffAccount;
		}
		/**
		 * @param staffAccount the staffAccount to set
		 */
		public void setStaffAccount(User staffAccount) {
			this.staffAccount = staffAccount;
		}
		
		
		public String getSection() {
			return section;
		}

		public void setSection(String section) {
			this.section = section;
		}
		
		public List getStudentAttendanceStatusList() {
			if(ObjectFunctions.isNullOrEmpty(this.studentAttendanceStatusList)){
				this.studentAttendanceStatusList=new ArrayList();
			}
			return studentAttendanceStatusList;
		}
		public void setStudentAttendanceStatusList(List studentAttendanceStatusList) {
			this.studentAttendanceStatusList = studentAttendanceStatusList;
		}
		
		public String getAttendanceDate() {
			return attendanceDate;
		}

		public void setAttendanceDate(String attendanceDate) {
			this.attendanceDate = attendanceDate;
		}
		
		public String getSelectedDate() {
			return selectedDate;
		}
		public void setSelectedDate(String selectedDate) {
			this.selectedDate = selectedDate;
		}
	       
	       
	       /**
	        * @return the mobileNumbersSet
	        */
	       public Set<String> getMobileNumbersSet() {
	               if(ObjectFunctions.isNullOrEmpty(this.mobileNumbersSet)){
	                       this.mobileNumbersSet=new HashSet<String>();
	               }
	               return this.mobileNumbersSet;
	       }

	       /**
	        * @param mobileNumbersSet the mobileNumbersSet to set
	        */
	       public void setMobileNumbersSet(Set<String> mobileNumbersSet) {
	               this.mobileNumbersSet = mobileNumbersSet;
	       }
		
		
		/**
		 * @return the subTypesList
		 */
		public List<SubType> getSubTypesList() {
			if (ObjectFunctions.isNullOrEmpty(this.subTypesList)) {
				this.subTypesList = new ArrayList();
			}
			return subTypesList;
		}
		/**
		 * @param subTypesList the subTypesList to set
		 */
		public void setSubTypesList(List<SubType> subTypesList) {
			this.subTypesList = subTypesList;
		}
		/**
		 * @return the subType
		 */
		public SubType getSubType() {
			return subType;
		}
		/**
		 * @param subType the subType to set
		 */
		public void setSubType(SubType subType) {
			this.subType = subType;
		}


		/**
		 * @return the anyTitle
		 */
		public String getAnyTitle() {
			return anyTitle;
		}


		/**
		 * @param anyTitle the anyTitle to set
		 */
		public void setAnyTitle(String anyTitle) {
			this.anyTitle = anyTitle;
		}


		/**
		 * @return the events
		 */
		public Events getEvents() {
			return events;
		}


		/**
		 * @param events the events to set
		 */
		public void setEvents(Events events) {
			this.events = events;
		}


		/**
		 * @return the smsEvents
		 */
		public SmsEvents getSmsEvents() {
			return smsEvents;
		}


		/**
		 * @param smsEvents the smsEvents to set
		 */
		public void setSmsEvents(SmsEvents smsEvents) {
			this.smsEvents = smsEvents;
		}
		
		
		
		/**
		 * @return the monthNamesList
		 */
		public Map<String, Integer> getMonthNamesList() {
			if(ObjectFunctions.isNullOrEmpty(this.monthNamesList)){
				this.monthNamesList=new LinkedHashMap<String,Integer>();
			}
			return monthNamesList;
		}
		/**
		 * @param monthNamesList the monthNamesList to set
		 */
		public void setMonthNamesList(Map<String, Integer> monthNamesList) {
			this.monthNamesList = monthNamesList;
		}
		
		public HashMap<Integer,String> getWeekDaysList() {
			if(ObjectFunctions.isNullOrEmpty(this.weekDaysList)){
				this.weekDaysList=new HashMap<Integer,String>();
			}
			return this.weekDaysList;
		}
		public void setWeekDaysList(HashMap<Integer,String> weekDaysList) {
			this.weekDaysList = weekDaysList;
		}
		public HashMap<String, String> getLeaveTypes() {
			if(ObjectFunctions.isNullOrEmpty(this.leaveTypes)){
				this.leaveTypes=new HashMap<String, String>();
			}
			return this.leaveTypes;
		}
		public void setLeaveTypes(HashMap<String, String> leaveTypes) {
			this.leaveTypes = leaveTypes;
		}

		/**
		 * @return the schoolCategory
		 */
		public SchoolCategory getSchoolCategory() {
			return schoolCategory;
		}

		/**
		 * @param schoolCategory the schoolCategory to set
		 */
		public void setSchoolCategory(SchoolCategory schoolCategory) {
			this.schoolCategory = schoolCategory;
		}

		/**
		 * @return the schoolCatecoriesList
		 */
		public List<SchoolCategory> getSchoolCategoriesList() {
			if (ObjectFunctions.isNullOrEmpty(this.schoolCategoriesList)) {
				this.schoolCategoriesList = new ArrayList();
			}
			return schoolCategoriesList;
		}

		/**
		 * @param schoolCatecoriesList the schoolCatecoriesList to set
		 */
		public void setSchoolCategoriesList(List<SchoolCategory> schoolCategoriesList) {
			this.schoolCategoriesList = schoolCategoriesList;
		}

		
		/**
		 * @return the schoolGradesList
		 */
		public List<SchoolGrades> getSchoolGradesList() {
			if (ObjectFunctions.isNullOrEmpty(this.schoolGradesList)) {
				this.schoolGradesList = new ArrayList();
			}
			return schoolGradesList;
		}

		/**
		 * @param schoolGradesList the schoolGradesList to set
		 */
		public void setSchoolGradesList(List<SchoolGrades> schoolGradesList) {
			this.schoolGradesList = schoolGradesList;
		}

		/**
		 * @return the schoolGrades
		 */
		public SchoolGrades getSchoolGrades() {
			return schoolGrades;
		}

		/**
		 * @param schoolGrades the schoolGrades to set
		 */
		public void setSchoolGrades(SchoolGrades schoolGrades) {
			this.schoolGrades = schoolGrades;
		}

		public long getTempId1() {
			return tempId1;
		}

		public void setTempId1(long tempId1) {
			this.tempId1 = tempId1;
		}

		public long getTempId2() {
			return tempId2;
		}

		public void setTempId2(long tempId2) {
			this.tempId2 = tempId2;
		}

		public List<ViewStudyClassSubjects> getStudyClassSubjectsList() {
			return studyClassSubjectsList;
		}

		public void setStudyClassSubjectsList(
				List<ViewStudyClassSubjects> studyClassSubjectsList) {
			this.studyClassSubjectsList = studyClassSubjectsList;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public List getStudentsFeeTypeList() {
			if (ObjectFunctions.isNullOrEmpty(this.studentsFeeTypeList)) {
				this.studentsFeeTypeList = new ArrayList();
			}
			return this.studentsFeeTypeList;
		}

		public void setStudentsFeeTypeList(List studentsFeeTypeList) {
			this.studentsFeeTypeList = studentsFeeTypeList;
		}
		
		
		public Set<StudyClass> getStudyClassSet() {
			if(ObjectFunctions.isNullOrEmpty(this.studyClassSet)){
				this.studyClassSet = new HashSet<StudyClass>();
			}
			return this.studyClassSet;
		}

		public void setStudyClassSet(Set<StudyClass> studyClassSet) {
			this.studyClassSet = studyClassSet;
		}

		/**
		 * @return the plSubjectName
		 */
		public String getPlSubjectName() {
			return plSubjectName;
		}

		/**
		 * @param plSubjectName the plSubjectName to set
		 */
		public void setPlSubjectName(String plSubjectName) {
			this.plSubjectName = plSubjectName;
		}

		/**
		 * @return the plTitle
		 */
		public String getPlTitle() {
			return plTitle;
		}

		/**
		 * @param plTitle the plTitle to set
		 */
		public void setPlTitle(String plTitle) {
			this.plTitle = plTitle;
		}

		/**
		 * @return the plSubTopic
		 */
		public String getPlSubTopic() {
			return plSubTopic;
		}

		/**
		 * @param plSubTopic the plSubTopic to set
		 */
		public void setPlSubTopic(String plSubTopic) {
			this.plSubTopic = plSubTopic;
		}

		/**
		 * @return the subTopicList
		 */
		public List getSubTopicList() {
			return subTopicList;
		}

		/**
		 * @param subTopicList the subTopicList to set
		 */
		public void setSubTopicList(List subTopicList) {
			this.subTopicList = subTopicList;
		}
		
		
		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}
		/**
		 * @return the schoolTermId
		 */
		public String[] getSchoolTermId() {
			return schoolTermId;
		}
		/**
		 * @param schoolTermId the schoolTermId to set
		 */
		public void setSchoolTermId(String[] schoolTermId) {
			this.schoolTermId = schoolTermId;
		}
		/**
		 * @return the schoolTermchkBoxSelectedIds
		 */
		public List<String> getSchoolTermchkBoxSelectedIds() {
			if(ObjectFunctions.isNullOrEmpty(this.schoolTermchkBoxSelectedIds)){
				this.schoolTermchkBoxSelectedIds=new ArrayList<String>();
			}
			return schoolTermchkBoxSelectedIds;
		}
		/**
		 * @param hostelTermchkBoxSelectedIds the hostelTermchkBoxSelectedIds to set
		 */
		public void setHostelTermchkBoxSelectedIds(
				List<String> hostelTermchkBoxSelectedIds) {
			this.hostelTermchkBoxSelectedIds = hostelTermchkBoxSelectedIds;
		}
		/**
		 * @return the schoolTermchkBoxSelectedIds
		 */
		public List<String> getHostelTermchkBoxSelectedIds() {
			if(ObjectFunctions.isNullOrEmpty(this.hostelTermchkBoxSelectedIds)){
				this.hostelTermchkBoxSelectedIds=new ArrayList<String>();
			}
			return hostelTermchkBoxSelectedIds;
		}
		/**
		 * @param schoolTermchkBoxSelectedIds the schoolTermchkBoxSelectedIds to set
		 */
		public void setSchoolTermchkBoxSelectedIds(
				List<String> schoolTermchkBoxSelectedIds) {
			this.schoolTermchkBoxSelectedIds = schoolTermchkBoxSelectedIds;
		}
		public List getSupportTicketMessagesList() {
			return supportTicketMessagesList;
		}

		public void setSupportTicketMessagesList(List supportTicketMessagesList) {
			this.supportTicketMessagesList = supportTicketMessagesList;
		}

		public SupportTicket getSupportTicket() {
				return supportTicket;
			}

			public void setSupportTicket(SupportTicket supportTicket) {
				this.supportTicket = supportTicket;
			}
		
		public Developers getDevelopers() {
			return developers;
		}

		public void setDevelopers(Developers developers) {
			this.developers = developers;
		}

			/* @return the developersList
			 */
			public List getDevelopersList() {
				if (ObjectFunctions.isNullOrEmpty(this.developersList)) {
					this.developersList = new ArrayList();
				}
				return this.developersList;
			}

			/**
			 * @param developersList the developersList to set
			 */
			public void setDevelopersList(List developersList) {
				this.developersList = developersList;
			}
		    
		    
			/**
		/**
		 * @return the schoolTermsList
		 */
		public List<SchoolTerms> getSchoolTermsList() {
			if(ObjectFunctions.isNullOrEmpty(this.schoolTermsList)){
				this.schoolTermsList=new ArrayList();
			}
			return schoolTermsList;
		}
		/**
		 * @param schoolTermsList the schoolTermsList to set
		 */
		public void setSchoolTermsList(List<SchoolTerms> schoolTermsList) {
			this.schoolTermsList = schoolTermsList;
		}
		
		/**
		 * @return the schoolTerms
		 */
		public SchoolTerms getSchoolTerms() {
			return schoolTerms;
		}
		/**
		 * @param schoolTerms the schoolTerms to set
		 */
		public void setSchoolTerms(SchoolTerms schoolTerms) {
			this.schoolTerms = schoolTerms;
		}
		public List<CastSettings> getCastSettingList() {
			if(ObjectFunctions.isNullOrEmpty(this.castSettingList)){
				this.castSettingList=new ArrayList();
			}
			return this.castSettingList;
		}
		public void setCastSettingList(List<CastSettings> castSettingList) {
			this.castSettingList = castSettingList;
		}
		
		/**
		 * @return the feedbackQuestionsAnserList
		 */
		public List getFeedbackQuestionsAnswerList() {
			if(ObjectFunctions.isNullOrEmpty(this.feedbackQuestionsAnswerList)){
				this.feedbackQuestionsAnswerList=new ArrayList();
			}
			return feedbackQuestionsAnswerList;
		}

		/**
		 * @param feedbackQuestionsAnserList the feedbackQuestionsAnserList to set
		 */
		public void setFeedbackQuestionsAnswerList(List feedbackQuestionsAnswerList) {
			this.feedbackQuestionsAnswerList = feedbackQuestionsAnswerList;
		}

		public PackageDetails getPackageDetails() {
			return packageDetails;
		}

		public void setPackageDetails(PackageDetails packageDetails) {
			this.packageDetails = packageDetails;
		}
		
		public List<PackageDetails> getPackageDetailsList() {
			return packageDetailsList;
		}

		public void setPackageDetailsList(List<PackageDetails> packageDetailsList) {
			this.packageDetailsList = packageDetailsList;
		}
		
		public AcademicYear getAcademicYear() {
			return academicYear;
		}

		public void setAcademicYear(AcademicYear academicYear) {
			this.academicYear = academicYear;
		}

		/**
		 * @return the admissionSettingsList
		 */
		public List<AdmissionSettings> getAdmissionSettingsList() {
			if(ObjectFunctions.isNullOrEmpty(this.admissionSettingsList)){
				this.admissionSettingsList=new ArrayList();
			}
			return admissionSettingsList;
		}

		/**
		 * @param admissionSettingsList the admissionSettingsList to set
		 */
		public void setAdmissionSettingsList(
				List<AdmissionSettings> admissionSettingsList) {
			this.admissionSettingsList = admissionSettingsList;
		}
		
		
		/**
		 * @return the academicYearId
		 */
		public long getAcademicYearId() {
			return academicYearId;
		}

		/**
		 * @param academicYearId the academicYearId to set
		 */
		public void setAcademicYearId(long academicYearId) {
			this.academicYearId = academicYearId;
		}

		/**
		 * @return the oldYear
		 */
		public String getOldYear() {
			return oldYear;
		}

		/**
		 * @param oldYear the oldYear to set
		 */
		public void setOldYear(String oldYear) {
			this.oldYear = oldYear;
		}

		/**
		 * @return the newYear
		 */
		public String getNewYear() {
			return newYear;
		}

		/**
		 * @param newYear the newYear to set
		 */
		public void setNewYear(String newYear) {
			this.newYear = newYear;
		}

		public List<AcademicYear> getAcademicYearList() {
			return academicYearList;
		}

		public void setAcademicYearList(List<AcademicYear> academicYearList) {
			this.academicYearList = academicYearList;
		}

		public ContactUs getContactUs() {
			return contactUs;
		}

		public void setContactUs(ContactUs contactUs) {
			this.contactUs = contactUs;
		}

		/**
		 * @param libraryManager the libraryManager to set
		 */
		public void setLibraryManager(LibraryManager libraryManager) {
			this.libraryManager = libraryManager;
		}

		/**
		 * @return the staffId
		 */
		public String[] getStaffId() {
			return staffId;
		}

		/**
		 * @param staffId the staffId to set
		 */
		public void setStaffId(String[] staffId) {
			this.staffId = staffId;
		}

		/**
		 * @return the studentQuestionAnswerList
		 */
		public List getStudentQuestionAnswerList() {
			if(ObjectFunctions.isNullOrEmpty(this.studentQuestionAnswerList)){
				this.studentQuestionAnswerList=new ArrayList();
			}
			return studentQuestionAnswerList;
		}

		/**
		 * @param studentQuestionAnswerList the studentQuestionAnswerList to set
		 */
		public void setStudentQuestionAnswerList(List studentQuestionAnswerList) {
			this.studentQuestionAnswerList = studentQuestionAnswerList;
		}

		public ClassName getClassName() {
			return className;
		}

		public void setClassName(ClassName className) {
			this.className = className;
		}
		
		
		/**
		 * @return the studentLeaveApprovalCount
		 */
		public long getStudentLeaveApprovalCount() {
			return studentLeaveApprovalCount;
		}
		/**
		 * @param studentLeaveApprovalCount the studentLeaveApprovalCount to set
		 */
		public void setStudentLeaveApprovalCount(long studentLeaveApprovalCount) {
			this.studentLeaveApprovalCount = studentLeaveApprovalCount;
		}
		/**
		 * @return the above60TotalAmount
		 */
		public double getAbove60TotalAmount() {
			return above60TotalAmount;
		}
		/**
		 * @param above60TotalAmount the above60TotalAmount to set
		 */
		public void setAbove60TotalAmount(double above60TotalAmount) {
			this.above60TotalAmount = above60TotalAmount;
		}
		/**
		 * @return the thirtyto60totalAmount
		 */
		public double getThirtyto60totalAmount() {
			return thirtyto60totalAmount;
		}
		/**
		 * @param thirtyto60totalAmount the thirtyto60totalAmount to set
		 */
		public void setThirtyto60totalAmount(double thirtyto60totalAmount) {
			this.thirtyto60totalAmount = thirtyto60totalAmount;
		}
		/**
		 * @return the thirtyTotalAmount
		 */
		public double getThirtyTotalAmount() {
			return thirtyTotalAmount;
		}
		/**
		 * @param thirtyTotalAmount the thirtyTotalAmount to set
		 */
		public void setThirtyTotalAmount(double thirtyTotalAmount) {
			this.thirtyTotalAmount = thirtyTotalAmount;
		}
		/**
		 * @return the fourteenTotalAmount
		 */
		public double getFourteenTotalAmount() {
			return fourteenTotalAmount;
		}
		/**
		 * @param fourteenTotalAmount the fourteenTotalAmount to set
		 */
		public void setFourteenTotalAmount(double fourteenTotalAmount) {
			this.fourteenTotalAmount = fourteenTotalAmount;
		}
		public String getClassId() {
			return classId;
		}
		public void setClassId(String classId) {
			this.classId = classId;
		}
		/**
		 * @return the questionAnswer
		 */
		public QuestionAnswer getQuestionAnswer() {
			return questionAnswer;
		}
		/**
		 * @param questionAnswer the questionAnswer to set
		 */
		public void setQuestionAnswer(QuestionAnswer questionAnswer) {
			this.questionAnswer = questionAnswer;
		}
		
		public String getAlertSendType() {
			return alertSendType;
		}
		public void setAlertSendType(String alertSendType) {
			this.alertSendType = alertSendType;
		}
		/**
		 * @return the quizId
		 */
		public long getQuizId() {
			return quizId;
		}
		/**
		 * @param quizId the quizId to set
		 */
		public void setQuizId(long quizId) {
			this.quizId = quizId;
		}
		public List getAlertsList() {
			if(ObjectFunctions.isNullOrEmpty(this.alertsList)){
				this.alertsList=new ArrayList();
			}
			return alertsList;
		}
		public void setAlertsList(List alertsList) {
			this.alertsList = alertsList;
		}
		/**
		 * @return the questionAnswerList
		 */
		public List getQuestionAnswerList() {
			if(ObjectFunctions.isNullOrEmpty(this.questionAnswerList)){
				this.questionAnswerList=new ArrayList();
			}
			return questionAnswerList;
		}
		/**
		 * @param questionAnswerList the questionAnswerList to set
		 */
		public void setQuestionAnswerList(List questionAnswerList) {
			this.questionAnswerList = questionAnswerList;
		}
		/**
		 * @return the categoryQuestionList
		 */
		public List getCategoryQuestionList() {
			if(ObjectFunctions.isNullOrEmpty(this.categoryQuestionList)){
				this.categoryQuestionList=new ArrayList();
			}
			return categoryQuestionList;
		}
		/**
		 * @param categoryQuestionList the categoryQuestionList to set
		 */
		public void setCategoryQuestionList(List categoryQuestionList) {
			this.categoryQuestionList = categoryQuestionList;
		}
		/**
		 * @return the questionAnswer
		 */
		public String[] getQuestionAnswerId() {
			return questionAnswerId;
		}
		/**
		 * @param questionAnswer the questionAnswer to set
		 */
		public void setQuestionAnswerId(String[] questionAnswerId) {
			this.questionAnswerId = questionAnswerId;
		}
		/**
		 * @return the quizList
		 */
		public List getQuizList() {
			if(ObjectFunctions.isNullOrEmpty(this.quizList)){
				this.quizList=new ArrayList();
			}
			return quizList;
		}
		/**
		 * @param quizList the quizList to set
		 */
		public void setQuizList(List quizList) {
			this.quizList = quizList;
		}
		/**
		 * @return the studentMraksList
		 */
		public List getStudentMarksList() {
			if(ObjectFunctions.isNullOrEmpty(this.studentMarksList)){
				this.studentMarksList=new ArrayList();
			}
			return this.studentMarksList;
		}
		/**
		 * @param studentMraksList the studentMraksList to set
		 */
		public void setStudentMarksList(List studentMarksList) {
			this.studentMarksList = studentMarksList;
		}
		public int getCurrentYear() {
			return currentYear;
		}

		public void setCurrentYear(int currentYear) {
			this.currentYear = currentYear;
		}

		public String getCurrentMonth() {
			return currentMonth;
		}

		public void setCurrentMonth(String currentMonth) {
			this.currentMonth = currentMonth;
		}
		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public int getNumberOfDays() {
			return numberOfDays;
		}

		public void setNumberOfDays(int numberOfDays) {
			this.numberOfDays = numberOfDays;
		}
		
		/**
		 * @return the studentFee14List
		 */
		public Set getStudentFee14List() {
			if (ObjectFunctions.isNullOrEmpty(this.studentFee14List)) {
				this.studentFee14List = new HashSet();
			}
			return studentFee14List;
		}

		/**
		 * @param studentFee14List the studentFee14List to set
		 */
		public void setStudentFee14List(Set studentFee14List) {
			this.studentFee14List = studentFee14List;
		}

		/**
		 * @return the studentFeeAbove30List
		 */
		public Set getStudentFeeAbove30List() {
			if (ObjectFunctions.isNullOrEmpty(this.studentFeeAbove30List)) {
				this.studentFeeAbove30List = new HashSet();
			}
			return studentFeeAbove30List;
		}

		/**
		 * @param studentFeeAbove30List the studentFeeAbove30List to set
		 */
		public void setStudentFeeAbove30List(Set studentFeeAbove30List) {
			this.studentFeeAbove30List = studentFeeAbove30List;
		}

		/**
		 * @return the studentFeeAbove60List
		 */
		public Set getStudentFeeAbove60List() {
			if (ObjectFunctions.isNullOrEmpty(this.studentFeeAbove60List)) {
				this.studentFeeAbove60List = new HashSet();
			}
			return studentFeeAbove60List;
		}

		/**
		 * @param studentFeeAbove60List the studentFeeAbove60List to set
		 */
		public void setStudentFeeAbove60List(Set studentFeeAbove60List) {
			this.studentFeeAbove60List = studentFeeAbove60List;
		}

		/**
		 * @return the studentFeeUpcomeingList
		 */
		public Set getStudentFeeUpcomeingList() {
			if (ObjectFunctions.isNullOrEmpty(this.studentFeeUpcomeingList)) {
				this.studentFeeUpcomeingList = new HashSet();
			}
			return studentFeeUpcomeingList;
		}

		/**
		 * @param studentFeeUpcomeingList the studentFeeUpcomeingList to set
		 */
		public void setStudentFeeUpcomeingList(Set studentFeeUpcomeingList) {
			this.studentFeeUpcomeingList = studentFeeUpcomeingList;
		}

		/**
		 * @return the studentFeeAbove30List
		 */
		public Set getStudentFeeAbove15List() {
			if (ObjectFunctions.isNullOrEmpty(this.studentFeeAbove15List)) {
				this.studentFeeAbove15List = new HashSet();
			}
			return studentFeeAbove15List;
		}

		/**
		 * @param studentFeeAbove30List the studentFeeAbove30List to set
		 */
		public void setStudentFeeAbove15List(Set studentFeeAbove15List) {
			this.studentFeeAbove15List = studentFeeAbove15List;
		}

		/**
		 * @return the chkBoxFeeSelectedIds
		*/
		public List<String> getChkBoxFeeSelectedIds() {
			if(ObjectFunctions.isNullOrEmpty(this.chkBoxFeeSelectedIds)){
				this.chkBoxFeeSelectedIds=new ArrayList<String>();
			}
			return this.chkBoxFeeSelectedIds;
		}

		/**
		 * @param chkBoxFeeSelectedIds the chkBoxFeeSelectedIds to set
		 */
		public void setChkBoxFeeSelectedIds(List<String> chkBoxFeeSelectedIds) {
			this.chkBoxFeeSelectedIds = chkBoxFeeSelectedIds;
		}
		
		
		public String getTempString() {
			return this.tempString;
		}
		public void setTempString(String tempString) {
			this.tempString = tempString;
		}
		public PlayListVideo getPlayListVideo() {
			return playListVideo;
		}

		public void setPlayListVideo(PlayListVideo playListVideo) {
			this.playListVideo = playListVideo;
		}

		public Set getPlayListVideoSet() {
			if(ObjectFunctions.isNullOrEmpty(this.playListVideoSet))
			{
				this.playListVideoSet = new TreeSet();
			}
			return this.playListVideoSet;
		}

		public void setPlayListVideoSet(Set playListVideoSet) {
			this.playListVideoSet = playListVideoSet;
		}

		public PlayList getPlayList() {
			return this.playList;
		}

		public void setPlayList(PlayList playList) {
			this.playList = playList;
		}

		public KBankRating getKBankRating() {
			return kBankRating;
		}

		public void setKBankRating(KBankRating kBankRating) {
			this.kBankRating = kBankRating;
		}
		public KBankComments getKBankComments() {
			if (ObjectFunctions.isNullOrEmpty(this.kBankComments)) {
				this.kBankComments = new KBankComments();
			}
			return kBankComments;
		}

		public void setKBankComments(KBankComments kBankComments) {
			this.kBankComments = kBankComments;
		}
		public List getFeedbackQuestionsList() {
			if (ObjectFunctions.isNullOrEmpty(this.feedbackQuestionsList)) {
				this.feedbackQuestionsList = new ArrayList();
			}
			return this.feedbackQuestionsList;
		}
		public void setFeedbackQuestionsList(List feedbackQuestionsList) {
			this.feedbackQuestionsList = feedbackQuestionsList;
		}
		/**
		 * @return the studentPaymentList
		 */
		public List getStudentPaymentList() {
			if (ObjectFunctions.isNullOrEmpty(this.studentPaymentList)) {
				this.studentPaymentList = new ArrayList();
			}
			return studentPaymentList;
		}

		/**
		 * @param studentPaymentList the studentPaymentList to set
		 */
		public void setStudentPaymentList(List studentPaymentList) {
			this.studentPaymentList = studentPaymentList;
		}

		//Commented by seshu on 19th April. Please check null or empty in u r own methods. Don't check here.
		public Set getAllUsersSet() {
			/*if (ObjectFunctions.isNullOrEmpty(this.allUsersSet)) {
				this.allUsersSet = new HashSet();
			}*/
			return this.allUsersSet;
		}

		public void setAllUsersSet(Set allUsersSet) {
			this.allUsersSet = allUsersSet;
		}

		public Set getStudySubjectSet() {
			if (ObjectFunctions.isNullOrEmpty(this.studySubjectSet)) {
				this.studySubjectSet = new HashSet();
			}
			return this.studySubjectSet;
		}

		public void setStudySubjectSet(Set studySubjectSet) {
			this.studySubjectSet = studySubjectSet;
		}
		public ViewAllUsers getViewAllUsers() {
			return this.viewAllUsers;
		}

		public void setViewAllUsers(ViewAllUsers viewAllUsers) {
			this.viewAllUsers = viewAllUsers;
		}

		/**
		 * @return the classFeeCountList
		 */
		public List getClassFeeCountList() {
			if (ObjectFunctions.isNullOrEmpty(this.classFeeCountList)) {
				this.classFeeCountList = new ArrayList();
			}
			return classFeeCountList;
		}

		/**
		 * @param classFeeCountList the classFeeCountList to set
		 */
		public void setClassFeeCountList(List classFeeCountList) {
			this.classFeeCountList = classFeeCountList;
		}

		public List getClassNameList() {
			/*if(ObjectFunctions.isNullOrEmpty(this.classNameList))
			{
				this.classNameList = new ArrayList();
			}*/
			return this.classNameList;
		}

		public void setClassNameList(List classNameList) {
			this.classNameList = classNameList;
		}
		public String getKBankTypeName() {
			return kBankTypeName;
		}
		public void setKBankTypeName(String kBankTypeName) {
			this.kBankTypeName = kBankTypeName;
		}
		public Attachment getAttachment() {
			if(ObjectFunctions.isNullOrEmpty(this.attachment)){
				this.attachment  = new Attachment();
			}
			return this.attachment;
		}
		public void setAttachment(Attachment attachment) {
			this.attachment = attachment;
		}
		public List<KBank> getKnowledgeBankList() {
			if (ObjectFunctions.isNullOrEmpty(this.knowledgeBankList)) {
				this.knowledgeBankList = new ArrayList<KBank>();
			}
			return knowledgeBankList;
		}
		public void setKnowledgeBankList(List<KBank> knowledgeBankList) {
			this.knowledgeBankList = knowledgeBankList;
		}
		public List<KBankType> getKnowledgeBankTypeList() {
			if (ObjectFunctions.isNullOrEmpty(this.knowledgeBankTypeList)) {
				this.knowledgeBankTypeList = new ArrayList<KBankType>();
			}
			return knowledgeBankTypeList;
		}
		public void setKnowledgeBankTypeList(List<KBankType> knowledgeBankTypeList) {
			this.knowledgeBankTypeList = knowledgeBankTypeList;
		}
		public KBank getKnowledgeBank() {
			return knowledgeBank;
		}
		public void setKnowledgeBank(KBank knowledgeBank) {
			this.knowledgeBank = knowledgeBank;
		}
		public KBankType getKnowledgeBankType() {
			return knowledgeBankType;
		}
		public void setKnowledgeBankType(KBankType knowledgeBankType) {
			this.knowledgeBankType = knowledgeBankType;
		}

		
		/**
		 * @return the feeTypeList
		 */
		public List getFeeTypeList() {
			if (ObjectFunctions.isNullOrEmpty(this.feeTypeList)) {
				this.feeTypeList = new ArrayList();
			}
			return feeTypeList;
		}

		/**
		 * @param feeTypeList the feeTypeList to set
		 */
		public void setHostelFeeTypeList(List hostelFeeTypeList) {
			this.hostelFeeTypeList = hostelFeeTypeList;
		}
		/**
		 * @return the feeTypeList
		 */
		public List getHostelFeeTypeList() {
			if (ObjectFunctions.isNullOrEmpty(this.hostelFeeTypeList)) {
				this.hostelFeeTypeList = new ArrayList();
			}
			return hostelFeeTypeList;
		}

		/**
		 * @param feeTypeList the feeTypeList to set
		 */
		public void setFeeTypeList(List feeTypeList) {
			this.feeTypeList = feeTypeList;
		}

		/**
		 * @return the feeTypeId
		 */
		public String[] getFeeTypeId() {
			return feeTypeId;
		}

		/**
		 * @param feeTypeId the feeTypeId to set
		 */
		public void setFeeTypeId(String[] feeTypeId) {
			this.feeTypeId = feeTypeId;
		}

		/**
		 * @return the feeAmount
		 */
		public String[] getFeeAmount() {
			return feeAmount;
		}

		/**
		 * @param feeAmount the feeAmount to set
		 */
		public void setFeeAmount(String[] feeAmount) {
			this.feeAmount = feeAmount;
		}

		/**
		 * @return the feeDueDate
		 */
		public Date[] getFeeDueDate() {
			return feeDueDate;
		}

		/**
		 * @param feeDueDate the feeDueDate to set
		 */
		public void setFeeDueDate(Date[] feeDueDate) {
			this.feeDueDate = feeDueDate;
		}

		/**
		 * @return the classFeeList
		 */
		public List getClassFeeList() {
			if(ObjectFunctions.isNullOrEmpty(this.classFeeList)){
				this.classFeeList=new ArrayList();
			}
			return classFeeList;
		}

		/**
		 * @param classFeeList the classFeeList to set
		 */
		public void setClassFeeList(List classFeeList) {
			this.classFeeList = classFeeList;
		}

		public Set getReplyScrapMessageSet() {
			if(ObjectFunctions.isNullOrEmpty(this.replyScrapMessageSet))
			{
				this.replyScrapMessageSet = new HashSet();
			}
			return this.replyScrapMessageSet;
		}

		public void setReplyScrapMessageSet(Set replyScrapMessageSet) {
			this.replyScrapMessageSet = replyScrapMessageSet;
		}

		public ScrapMessage getScrapMessage() {
			return this.scrapMessage;
		}

		public void setScrapMessage(ScrapMessage scrapMessage) {
			this.scrapMessage = scrapMessage;
		}

		public ReplyScrapMessage getReplyScrapMessage() {
			return this.replyScrapMessage;
		}

		public void setReplyScrapMessage(ReplyScrapMessage replyScrapMessage) {
			this.replyScrapMessage = replyScrapMessage;
		}

		public List getScrapMessagesList() {
			if(ObjectFunctions.isNullOrEmpty(this.scrapMessagesList)){
				this.scrapMessagesList=new ArrayList();
			}
			return this.scrapMessagesList;
		}

		public void setScrapMessagesList(List scrapMessagesList) {
			this.scrapMessagesList = scrapMessagesList;
		}

		public List<String> getSelectBoxIdList() {
			return selectBoxIdList;
		}

		public void setSelectBoxIdList(List<String> selectBoxIdList) {
			this.selectBoxIdList = selectBoxIdList;
		}

		public List getExamScheduleList() {
			/*if(ObjectFunctions.isNullOrEmpty(this.examScheduleList)){
				this.examScheduleList=new ArrayList();
			}*/
			return examScheduleList;
		}

		public void setExamScheduleList(List examScheduleList) {
			this.examScheduleList = examScheduleList;
		}

		public List getSyllabusList() {
			if(ObjectFunctions.isNullOrEmpty(this.syllabusList)){
				this.syllabusList=new ArrayList();
			}
			return this.syllabusList;
		}
		
		public void setSyllabusList(List syllabusList) {
			this.syllabusList = syllabusList;
		}

		public String[] getChapterName() {
			return chapterName;
		}

		public void setChapterName(String[] chapterName) {
			this.chapterName = chapterName;
		}

		public List<StudySubject> getStudySubjectList() {
			if(ObjectFunctions.isNullOrEmpty(this.studySubjectList))
			{
				this.studySubjectList=new ArrayList<StudySubject>();
			}
			return this.studySubjectList;
		}
		public void setStudySubjectList(List<StudySubject> studySubjectList) {
			this.studySubjectList = studySubjectList;
		}
		public String[] getStaffAccountId() {
			return staffAccountId;
		}

		public void setStaffAccountId(String[] staffAccountId) {
			this.staffAccountId = staffAccountId;
		}
		public List getTeacherList() {
			if(ObjectFunctions.isNullOrEmpty(this.teacherList)){
				this.teacherList=new ArrayList();
			}
			return teacherList;
		}
		public void setTeacherList(List teacherList) {
			this.teacherList = teacherList;
		}
		public String getIsClassTeacher() {
			return isClassTeacher;
		}
		public void setIsClassTeacher(String isClassTeacher) {
			this.isClassTeacher = isClassTeacher;
		}
		//Commented by seshu on 10th May
		public ClassTeacher getClassTeacher() {
			/*if(ObjectFunctions.isNullOrEmpty(this.classTeacher))
			{
				this.classTeacher = new ClassTeacher();
			}*/
			return classTeacher;
		}
		public void setClassTeacher(ClassTeacher classTeacher) {
			this.classTeacher = classTeacher;
		}
		public List getClassSubjectsList() {
			return classSubjectsList;
		}
		public void setClassSubjectsList(List classSubjectsList) {
			this.classSubjectsList = classSubjectsList;
		}
		public List getTeacherSubjectsList() {
			if(ObjectFunctions.isNullOrEmpty(this.teacherSubjectsList)){
				this.teacherSubjectsList=new ArrayList();
			}
			return this.teacherSubjectsList;
		}
		
		public void setTeacherSubjectsList(List teacherSubjectsList) {
			this.teacherSubjectsList = teacherSubjectsList;
		}
		
		public List getClassExamTypeList() {
			if(ObjectFunctions.isNullOrEmpty(this.classExamTypeList))
			{
				this.classExamTypeList=new ArrayList();
			}
			return classExamTypeList;
		}

		public void setClassExamTypeList(List classExamTypeList) {
			this.classExamTypeList = classExamTypeList;
		}
		
		
		public long getTempId() {
			return tempId;
		}

		public void setTempId(long tempId) {
			this.tempId = tempId;
		}

		public long getLeaveApprovalCount() {
			return leaveApprovalCount;
		}

		public void setLeaveApprovalCount(long leaveApprovalCount) {
			this.leaveApprovalCount = leaveApprovalCount;
		}

		public String getWishTitle() {
			return wishTitle;
		}

		public void setWishTitle(String wishTitle) {
			this.wishTitle = wishTitle;
		}

		public String getWishDescription() {
			return wishDescription;
		}

		public void setWishDescription(String wishDescription) {
			this.wishDescription = wishDescription;
		}

		public List getSubjectsList() {
			if(ObjectFunctions.isNullOrEmpty(this.subjectsList))
			{
				this.subjectsList=new ArrayList();
			}
			return subjectsList;
		}

		public void setSubjectsList(List subjectsList) {
			this.subjectsList = subjectsList;
		}
		
		
		public ViewStudentFeePaymentDetails getViewStudentFeeDetails() {
			return viewStudentFeeDetails;
		}

		public void setViewStudentFeeDetails(
				ViewStudentFeePaymentDetails viewStudentFeeDetails) {
			this.viewStudentFeeDetails = viewStudentFeeDetails;
		}

		public Set getBirthDaysListSet() {
			if(ObjectFunctions.isNullOrEmpty(this.birthDaysListSet))
			{
				this.birthDaysListSet = new HashSet();
			}
			return this.birthDaysListSet;
		}

		public void setBirthDaysListSet(Set birthDaysListSet) {
			this.birthDaysListSet = birthDaysListSet;
		}

		public List getViewStaffPersonAccountDetailsList() {
			if(ObjectFunctions.isNullOrEmpty(this.viewStaffPersonAccountDetailsList))
			{
				this.viewStaffPersonAccountDetailsList=new ArrayList();
			}
			return this.viewStaffPersonAccountDetailsList;
		}

		public void setViewStaffPersonAccountDetailsList(List viewStaffPersonAccountDetailsList) {
			this.viewStaffPersonAccountDetailsList = viewStaffPersonAccountDetailsList;
		}

		public List getHolidayBoardMessagesList() {
			return holidayBoardMessagesList;
		}

		public void setHolidayBoardMessagesList(List holidayBoardMessagesList) {
			this.holidayBoardMessagesList = holidayBoardMessagesList;
		}

		public SchoolHolidays getSchoolHolidays() {
			return this.schoolHolidays;
		}

		public void setSchoolHolidays(SchoolHolidays schoolHolidays) {
			this.schoolHolidays = schoolHolidays;
		}

		public ExamTypes getExamTypes() {
			return examTypes;
		}

		public void setExamTypes(ExamTypes examTypes) {
			this.examTypes = examTypes;
		}

		/**
		 * @return the examTypeList
		 */
		
		
		public void setUserManager(UserManager userManager) {
			this.userManager = userManager;
		}
		public void setStudentManager(StudentManager studentManager) {
			this.studentManager = studentManager;
		}
		public void setAdminManager(AdminManager adminManager) {
			this.adminManager = adminManager;
		}
		public void setStaffManager(StaffManager staffManager) {
			this.staffManager = staffManager;
		}

		public List<ExamTypes> getExamTypeList() {
			return examTypeList;
		}

		public void setExamTypeList(List<ExamTypes> examTypeList) {
			this.examTypeList = examTypeList;
		}
		public List getNoticeBoardMessagesList() {
			if(ObjectFunctions.isNullOrEmpty(this.noticeBoardMessagesList))
			{
				this.noticeBoardMessagesList=new ArrayList();
			}
			return this.noticeBoardMessagesList;
		}

		public void setNoticeBoardMessagesList(List noticeBoardMessagesList) {
			this.noticeBoardMessagesList = noticeBoardMessagesList;
		}

		public List getHalfYearlyEndClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.halfYearlyEndClassList))
			{
				this.halfYearlyEndClassList=new ArrayList();
			}
			return this.halfYearlyEndClassList;
		}

		public void setHalfYearlyEndClassList(List halfYearlyEndClassList) {
			this.halfYearlyEndClassList = halfYearlyEndClassList;
		}

		public List getQuarterYearlyEndClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.quarterYearlyEndClassList))
			{
				this.quarterYearlyEndClassList=new ArrayList();
			}
			return quarterYearlyEndClassList;
		}

		/**
		 * @param quarterYearlyEndClassList the quarterYearlyEndClassList to set
		 */
		public void setQuarterYearlyEndClassList(List quarterYearlyEndClassList) {
			this.quarterYearlyEndClassList = quarterYearlyEndClassList;
		}

		/**
		 * @return the annualYearlyEndClassList
		 */
		public List getAnnualYearlyEndClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.annualYearlyEndClassList))
			{
				this.annualYearlyEndClassList=new ArrayList();
			}
			return annualYearlyEndClassList;
		}

		/**
		 * @param annualYearlyEndClassList the annualYearlyEndClassList to set
		 */
		public void setAnnualYearlyEndClassList(List annualYearlyEndClassList) {
			this.annualYearlyEndClassList = annualYearlyEndClassList;
		}

		

		
		public Set<User> getTotalOnlineAccounts() {
			if(ObjectFunctions.isNullOrEmpty(this.totalOnlineAccounts)){
				this.totalOnlineAccounts=new HashSet<User>();
			}
			return this.totalOnlineAccounts;
		}

		public List getAllStaffList() {
			if(ObjectFunctions.isNullOrEmpty(this.allStaffList)){
				this.allStaffList=new ArrayList();
			}
			return this.allStaffList;
		}

		public void setAllStaffList(List allStaffList) {
			this.allStaffList = allStaffList;
		}

		public List getStaffsList() {
			if(ObjectFunctions.isNullOrEmpty(this.staffsList)){
				this.staffsList=new ArrayList();
			}
			return this.staffsList;
		}

		public void setStaffsList(List staffsList) {
			this.staffsList = staffsList;
		}

		public void setTotalOnlineAccounts(Set<User> totalOnlineAccounts) {
			this.totalOnlineAccounts = totalOnlineAccounts;
		}

		public List  getStudentsList() {
			/*if(ObjectFunctions.isNullOrEmpty(this.studentsList)){
				this.studentsList=new ArrayList();
			}*/
			return this.studentsList;
		}

		/**
		 * @param studentsList the studentsList to set
		 */
		public void setStudentsList(List studentsList) {
			this.studentsList = studentsList;
		}
		public List getClassStudentsList() {
			if(ObjectFunctions.isNullOrEmpty(this.classStudentsList))
			{
				this.classStudentsList = new ArrayList();
			}
			return this.classStudentsList;
		}

		public void setClassStudentsList(List classStudentsList) {
			this.classStudentsList = classStudentsList;
		}
		/**
		 * @return the halfYearlyClassList
		 */
		public List getHalfYearlyClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.halfYearlyClassList))
			{
				this.halfYearlyClassList=new ArrayList();
			}
			return halfYearlyClassList;
		}

		/**
		 * @param halfYearlyClassList the halfYearlyClassList to set
		 */
		public void setHalfYearlyClassList(List halfYearlyClassList) {
			this.halfYearlyClassList = halfYearlyClassList;
		}

		/**
		 * @return the quarterYearlyClassList
		 */
		public List getQuarterYearlyClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.quarterYearlyClassList))
			{
				this.quarterYearlyClassList=new ArrayList();
			}
			return quarterYearlyClassList;
		}

		/**
		 * @param quarterYearlyClassList the quarterYearlyClassList to set
		 */
		public void setQuarterYearlyClassList(List quarterYearlyClassList) {
			this.quarterYearlyClassList = quarterYearlyClassList;
		}

		/**
		 * @return the annualYearlyClassList
		 */
		public List getAnnualYearlyClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.annualYearlyClassList))
			{
				this.annualYearlyClassList=new ArrayList();
			}
			return annualYearlyClassList;
		}

		/**
		 * @param annualYearlyClassList the annualYearlyClassList to set
		 */
		public void setAnnualYearlyClassList(List annualYearlyClassList) {
			this.annualYearlyClassList = annualYearlyClassList;
		}

		/**
		 * @return the term1ExamClassList
		 */
		public List getTerm1ExamClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.term1ExamClassList))
			{
				this.term1ExamClassList=new ArrayList();
			}
			return term1ExamClassList;
		}

		/**
		 * @param term1ExamClassList the term1ExamClassList to set
		 */
		public void setTerm1ExamClassList(List term1ExamClassList) {
			this.term1ExamClassList = term1ExamClassList;
		}

		/**
		 * @return the term2ExamClassList
		 */
		public List getTerm2ExamClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.term2ExamClassList))
			{
				this.term2ExamClassList=new ArrayList();
			}
			return term2ExamClassList;
		}

		/**
		 * @param term2ExamClassList the term2ExamClassList to set
		 */
		public void setTerm2ExamClassList(List term2ExamClassList) {
			this.term2ExamClassList = term2ExamClassList;
		}

		/**
		 * @return the term3ExamClassList
		 */
		public List getTerm3ExamClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.term3ExamClassList))
			{
				this.term3ExamClassList=new ArrayList();
			}
			return term3ExamClassList;
		}

		/**
		 * @param term3ExamClassList the term3ExamClassList to set
		 */
		public void setTerm3ExamClassList(List term3ExamClassList) {
			this.term3ExamClassList = term3ExamClassList;
		}

		/**
		 * @return the term4ExamClassList
		 */
		public List getTerm4ExamClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.term4ExamClassList))
			{
				this.term4ExamClassList=new ArrayList();
			}
			return term4ExamClassList;
		}

		/**
		 * @param term4ExamClassList the term4ExamClassList to set
		 */
		public void setTerm4ExamClassList(List term4ExamClassList) {
			this.term4ExamClassList = term4ExamClassList;
		}
		/**
		 * @return the viewClassExamDetails
		 */
		public List<ViewClassExamDetails> getViewClassExamDetails() {
			if(ObjectFunctions.isNullOrEmpty(this.viewClassExamDetails))
			{
				this.viewClassExamDetails=new ArrayList<ViewClassExamDetails>();
			}
			return viewClassExamDetails;
		}

		/**
		 * @param viewClassExamDetails the viewClassExamDetails to set
		 */
		public void setViewClassExamDetails(
				List<ViewClassExamDetails> viewClassExamDetails) {
			this.viewClassExamDetails = viewClassExamDetails;
		}
		/**
		 * @return the subId
		 */
		public String[] getSubId() {
			return subId;
		}

		/**
		 * @param subId the subId to set
		 */
		public void setSubId(String[] subId) {
			this.subId = subId;
		}

		/**
		 * @return the date
		 */
		public Date[] getExamDate() {
			return examDate;
		}

		/**
		 * @param date the date to set
		 */
		public void setExamDate(Date[] examDate) {
			this.examDate = examDate;
		}

		/**
		 * @return the endTime
		 */
		public String[] getEndTime() {
			return endTime;
		}

		/**
		 * @param endTime the endTime to set
		 */
		public void setEndTime(String[] endTime) {
			this.endTime = endTime;
		}

		/**
		 * @return the startTime
		 */
		public String[] getStartTime() {
			return startTime;
		}

		/**
		 * @param startTime the startTime to set
		 */
		public void setStartTime(String[] startTime) {
			this.startTime = startTime;
		}
			
		
		
		public List getMessagesList() {
			if(ObjectFunctions.isNullOrEmpty(this.messagesList))
			{
				this.messagesList = new ArrayList();
			}
			return messagesList;
		}

		public void setMessagesList(List messagesList) {
			this.messagesList = messagesList;
		}

		public Messages getMessages() {
			return messages;
		}

		public void setMessages(Messages messages) {
			this.messages = messages;
		}

		public List getViewStudentLeaveDetailsList() {
			if(ObjectFunctions.isNullOrEmpty(this.viewStudentLeaveDetailsList))
			{
				this.viewStudentLeaveDetailsList = new ArrayList();
			}
			return viewStudentLeaveDetailsList;
		}

		public void setViewStudentLeaveDetailsList(List viewStudentLeaveDetailsList) {
			this.viewStudentLeaveDetailsList = viewStudentLeaveDetailsList;
		}

		public Set getDisplayAttendanceSet() {
			if(ObjectFunctions.isNullOrEmpty(this.displayAttendanceSet))
			{
				this.displayAttendanceSet = new HashSet();
			}
			return this.displayAttendanceSet;
		}

		public void setDisplayAttendanceSet(Set displayAttendanceSet) {
			this.displayAttendanceSet = displayAttendanceSet;
		}

		public String getSubjectId() {
			return subjectId;
		}

		public void setSubjectId(String subjectId) {
			this.subjectId = subjectId;
		}
		public String getBalance() {
			return balance;
		}

		public void setBalance(String balance) {
			this.balance = balance;
		}
		public String getAnyId() {
			return anyId;
		}

		public void setAnyId(String anyId) {
			this.anyId = anyId;
		}
		public String getEventId() {
			return eventId;
		}
		/**
		 * @return the roomId
		 */
		public String getRoomId() {
			return roomId;
		}
		/**
		 * @param roomId the roomId to set
		 */
		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}

		
		
		/**
		 * @return the bedId
		 */
		public String getBedId() {
			return bedId;
		}
		/**
		 * @param bedId the bedId to set
		 */
		public void setBedId(String bedId) {
			this.bedId = bedId;
		}
		public void setEventId(String eventId) {
			this.eventId = eventId;
		}
		public List getPresentList() {
			if(ObjectFunctions.isNullOrEmpty(this.presentList)){
				this.presentList=new ArrayList();
			}
			return this.presentList;
		}
		public void setPresentList(List presentList) {
			this.presentList = presentList;
		}
		public List getAbsentList() {
			if(ObjectFunctions.isNullOrEmpty(this.absentList)){
				this.absentList=new ArrayList();
			}
			return this.absentList;
		}
		public void setAbsentList(List absentList) {
			this.absentList = absentList;
		}

		
		public ViewStudentLeaveDetails getViewStudentLeaveDetails() {
			return viewStudentLeaveDetails;
		}

		public void setViewStudentLeaveDetails(
				ViewStudentLeaveDetails viewStudentLeaveDetails) {
			this.viewStudentLeaveDetails = viewStudentLeaveDetails;
		}

		public ViewStaffLeaveDetails getViewStaffLeaveDetails() {
			return viewStaffLeaveDetails;
		}

		public void setViewStaffLeaveDetails(ViewStaffLeaveDetails viewStaffLeaveDetails) {
			this.viewStaffLeaveDetails = viewStaffLeaveDetails;
		}
		public Map getSelectboxMap() {
			if(ObjectFunctions.isNullOrEmpty(this.selectboxMap)){
				this.selectboxMap=new HashMap();
			}
			return this.selectboxMap;
		}
		public void setSelectboxMap(Map selectboxMap) {
			this.selectboxMap = selectboxMap;
		}
		
		public LeaveManagement getLeaveManagement() {
			return leaveManagement;
		}

		public void setLeaveManagement(LeaveManagement leaveManagement) {
			this.leaveManagement = leaveManagement;
		}

		public List getAllStudentsList() {
			if (ObjectFunctions.isNullOrEmpty(this.allStudentsList)) {
				this.allStudentsList = new ArrayList();
			}
			return this.allStudentsList;
		}

		public void setAllStudentsList(List allStudentsList) {
			this.allStudentsList = allStudentsList;
		}

		public List<ViewStudentPersonAccountDetails> getViewStudentPersonAccountDetailsList() {
			if (ObjectFunctions.isNullOrEmpty(this.viewStudentPersonAccountDetailsList)) {
				this.viewStudentPersonAccountDetailsList = new ArrayList();
			}
			return this.viewStudentPersonAccountDetailsList;
		}

		public void setViewStudentPersonAccountDetailsList(List<ViewStudentPersonAccountDetails> viewStudentPersonAccountDetailsList) {
			this.viewStudentPersonAccountDetailsList = viewStudentPersonAccountDetailsList;
		}

		public List getCalendarEventsList() {
			if (ObjectFunctions.isNullOrEmpty(this.calendarEventsList)) {
				this.calendarEventsList = new ArrayList();
			}
			return calendarEventsList;
		}

		public void setCalendarEventsList(List calendarEventsList) {
			this.calendarEventsList = calendarEventsList;
		}

		public ViewStudentPersonAccountDetails getViewStudentPersonAccountDetails() {
			return viewStudentPersonAccountDetails;
		}

		public void setViewStudentPersonAccountDetails(
				ViewStudentPersonAccountDetails viewStudentPersonAccountDetails) {
			this.viewStudentPersonAccountDetails = viewStudentPersonAccountDetails;
		}
		public List getEventInvitedUserList() {
			if(ObjectFunctions.isNullOrEmpty(this.eventInvitedUserList))
			{
				this.eventInvitedUserList = new ArrayList();
			}
			return eventInvitedUserList;
		}

		public void setEventInvitedUserList(List eventInvitedUserList) {
			this.eventInvitedUserList = eventInvitedUserList;
		}
		public ViewStaffPersonAccountDetails getViewStaffPersonAccountDetails() {
			return this.viewStaffPersonAccountDetails;
		}
		public void setViewStaffPersonAccountDetails(
				ViewStaffPersonAccountDetails viewStaffPersonAccountDetails) {
			this.viewStaffPersonAccountDetails = viewStaffPersonAccountDetails;
		}
		public User getNewUser() {
			if(ObjectFunctions.isNullOrEmpty(this.newUser)){
				this.newUser=new User();			
			}
			return this.newUser;
		}

		public void setNewUser(User newUser) {
			this.newUser = newUser;
		}
		
		public String getSelectedId() {
			return selectedId;
		}

		public void setSelectedId(String selectedId) {
			this.selectedId = selectedId;
		}
		public Leave getLeave() {
			return leave;
		}

		public void setLeave(Leave leave) {
			this.leave = leave;
		}
		public List getRejectedLeavesList() {
			if(ObjectFunctions.isNullOrEmpty(this.rejectedLeavesList))
			{
				this.rejectedLeavesList = new ArrayList();
			}
			return rejectedLeavesList;
		}
		public void setRejectedLeavesList(List rejectedLeavesList) {
			this.rejectedLeavesList = rejectedLeavesList;
		}
		public List getApprovedLeavesList() {
			if(ObjectFunctions.isNullOrEmpty(this.approvedLeavesList))
			{
				this.approvedLeavesList = new ArrayList();
			}
			return approvedLeavesList;
		}

		public void setApprovedLeavesList(List approvedLeavesList) {
			this.approvedLeavesList = approvedLeavesList;
		}
		public String getTodayDate() {
			return todayDate;
		}

		public void setTodayDate(String todayDate) {
			this.todayDate = todayDate;
		}
		public List getLeavesList() {
			if(ObjectFunctions.isNullOrEmpty(this.leavesList))
			{
				this.leavesList = new ArrayList();
			}
			return leavesList;
		}

		public void setLeavesList(List leavesList) {
			this.leavesList = leavesList;
		}
		public double getCasualLeave() {
			return casualLeave;
		}
		public double getSickLeave() {
			return sickLeave;
		}
		public void setCasualLeave(double casualLeave) {
			this.casualLeave = casualLeave;
		}
		public void setSickLeave(double sickLeave) {
			this.sickLeave = sickLeave;
		}
	    /**
		 * @return the payLeaves
		 */
		public double getPayLeaves() {
			return payLeaves;
		}

		/**
		 * @param payLeaves the payLeaves to set
		 */
		public void setPayLeaves(double payLeaves) {
			this.payLeaves = payLeaves;
		}

		public List<State> getStatesList() {
			if (ObjectFunctions.isNullOrEmpty(this.statesList)) {
				this.statesList = new ArrayList<State>();
			}
			return this.statesList;
		}

		public void setStatesList(List statesList) {
			this.statesList = statesList;
		}
		
		public List<Mess> getMessList() {
		   if (ObjectFunctions.isNullOrEmpty(this.messList)) {
			   this.messList = new ArrayList<Mess>();		
		   }
			return this.messList;
		}

		public void setMessList(List messList) {
			this.messList = messList;
		}

		
		
	    
		/**
		 * @return the chkBoxSelectedIds
		*/
		public List<String> getChkBoxSelectedIds() {
			if(ObjectFunctions.isNullOrEmpty(this.chkBoxSelectedIds)){
				this.chkBoxSelectedIds=new ArrayList<String>();
			}
			return this.chkBoxSelectedIds;
		}

		/**
		 * @param chkBoxSelectedIds the chkBoxSelectedIds to set
		 */
		public void setChkBoxSelectedIds(List<String> chkBoxSelectedIds) {
			this.chkBoxSelectedIds = chkBoxSelectedIds;
		}
		
		/**
		 * @return the fileUpload
		 */
		public File getUpload() {
			return upload;
		}
		/**
		 * @param fileUpload the fileUpload to set
		 */
		public void setUpload(File upload) {
			this.upload = upload;
		}
		
		public List<File> getFileUpload() {
			return fileUpload;
		}
	 
		public void setFileUpload(List<File> fileUpload) {
			this.fileUpload = fileUpload;
		}
		 
		public List<String> getFileUploadFileName() {
			return fileUploadFileName;
		}

		public void setFileUploadFileName(List<String> fileUploadFileName) {
			this.fileUploadFileName = fileUploadFileName;
		}
		/**
		 * @param jsonResult the jsonResult to set
		 */
		public void setJsonResult(Map jsonResult) {
			this.jsonResult = jsonResult;
		}
		/** Params: request, paramString
		 * Returns: String
		 */
	   protected String getParamValue(String paramString)
	   {
	       return TokenHelper.getToken(paramString);
	   }
	   
	   /**
		 * @return the attendanceList
		 */
		public List getAttendanceList() {
			if(ObjectFunctions.isNullOrEmpty(this.attendanceList))
			{
				this.attendanceList = new ArrayList();
			}
			return this.attendanceList;
		}

		/**
		 * @param attendanceList the attendanceList to set
		 */
		public void setAttendanceList(List attendanceList) {
			this.attendanceList = attendanceList;
		}
	    

		
		public String getHostUrl(){
			return ServletActionContext.getServletContext().getInitParameter("hostUrl");
		}
		
		
		public String getUploadContentType() {
			return contentType;
		}

		public void setUploadContentType(String contentType) {
			this.contentType = contentType;
		}
	   
		/**
	     * Convenience method to get the request
	     * @return current request
	     */
	    protected HttpServletRequest getRequest() {
	        return ServletActionContext.getRequest();
	    }

	    /**
	     * Convenience method to get the response
	     * @return current response
	     */
	    protected HttpServletResponse getResponse() {
	        return ServletActionContext.getResponse();
	    }

	    /**
	     * Convenience method to get the session. This will create a session if one doesn't exist.
	     * @return the session from the request (request.getSession()).
	     */
	    protected HttpSession getSession() {
	        return getRequest().getSession();
	    }
	   
		public Address getAddress() {
			if(ObjectFunctions.isNullOrEmpty(this.address))
			{
				this.address= new Address();
			}
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}
		
		/**
		 * @return the google
		 */
		public boolean isGoogle() {
			return google;
		}

		/**
		 * @param google the google to set
		 */
		public void setGoogle(boolean google) {
			this.google = google;
		}

		/**
		 * @return the ajaxhistory
		 */
		public boolean isAjaxhistory() {
			return ajaxhistory;
		}

		/**
		 * @param ajaxhistory the ajaxhistory to set
		 */
		public void setAjaxhistory(boolean ajaxhistory) {
			this.ajaxhistory = ajaxhistory;
		}
		 
		public String getUploadFileName() {
			return fileName;
		}
		
		public void setUploadFileName(String fileName) {
			this.fileName = fileName;
		}
		
		/**
		 * @return the fieldErrorsList
		 */
		public List getFieldErrorsList() {
			if(ObjectFunctions.isNullOrEmpty(this.fieldErrorsList)){
				this.fieldErrorsList=new ArrayList();
			}
			return this.fieldErrorsList;
		}

		/**
		 * @param fieldErrorsList the fieldErrorsList to set
		 */
		public void setFieldErrorsList(List fieldErrorsList) {
			this.fieldErrorsList = fieldErrorsList;
		}

		
		/**
		 * @return the fileName
		 */
		public String getFileName() {
			return fileName;
		}

		/**
		 * @param fileName the fileName to set
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		/**
		 * @return the smsCnt
		 */
		public int getSmsCnt() {
			return smsCnt;
		}

		/**
		 * @param smsCnt the smsCnt to set
		 */
		public void setSmsCnt(int smsCnt) {
			this.smsCnt = smsCnt;
		}
		/**
		 * @return the smsAlloted
		 */
		public int getSmsAlloted() {
			return smsAlloted;
		}

		/**
		 * @param smsAlloted the smsAlloted to set
		 */
		public void setSmsAlloted(int smsAlloted) {
			this.smsAlloted = smsAlloted;
		}

		/**
		 * @param user the user to set
		 */
		public void setUser(User user) {
			this.user = user;
		}
		/**
		 * @param roleManager the roleManager to set
		 */
		public void setRoleManager(RoleManager roleManager) {
			this.roleManager = roleManager;
		}
		
		public long getCustId() {
			return custId;
		}
		public void setCustId(long custId) {
			this.custId = custId;
		}
		/**
		 * @return the accountType
		 */
		public String getAccountType() {
			return accountType;
		}

		/**
		 * @param accountType the accountType to set
		 */
		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}
		/**
		 * @param autoCheck the autoCheck to set
		 */
		public void setAutoCheck(String autoCheck) {
			this.autoCheck = autoCheck;
		}
		
		public String getAutoCheck() {
		    
			return autoCheck;
		}
		 

		
		/**
		 * @return the paypalServerType
		 */
		public String getPaypalServerType() {
			return this.paypalServerType;
		}

		/**
		 * @param paypalServerType the paypalServerType to set
		 */
		/**
		 * @param saveDir the saveDir to set
		 */
		@Inject("struts.paypal.server.type") 
		public void setPaypalServerType(String paypalServerType) {
			this.paypalServerType = paypalServerType;
		}
		
		
		public List getTempList() {
			if(ObjectFunctions.isNullOrEmpty(this.tempList))
			{
				this.tempList=new ArrayList();
			}
			return tempList;
		}

		public void urtLogger(int level,String message){
			if(level==0){
				log.debug(message);
			}
		}

		public void setTempList(List tempList) {
			this.tempList = tempList;
		}
		
		
		public List getTempList1() {
			if(ObjectFunctions.isNullOrEmpty(this.tempList1))
			{
				this.tempList1=new ArrayList();
			}
			return tempList1;
		}

		public void setTempList1(List tempList1) {
			this.tempList1 = tempList1;
		}

		public List getTempList2() {
			if(ObjectFunctions.isNullOrEmpty(this.tempList2))
			{
				this.tempList2=new ArrayList();
			}
			return tempList2;
		}

		public void setTempList2(List tempList2) {
			this.tempList2 = tempList2;
		}

		public String getUrlWithHost(String action)
		{
			return getText("hostname")+action;
		}

		/**
		 * @return the footer
		 */
		public String getFooter() {
			return footer;
		}

		/**
		 * @param footer the footer to set
		 */
		public void setFooter(String footer) {
			this.footer = footer;
		}
		/**
		 * @return the customer
		 */
	     public Customer getCustomer() {
	 		if (ObjectFunctions.isNullOrEmpty(this.customer)) {
	 			this.customer = new Customer();
	 		}
	 		return this.customer;
	 	}
		public ExamSchedules getExamSchedules() {
			return examSchedules;
		}
		public void setExamSchedules(ExamSchedules examSchedules) {
			this.examSchedules = examSchedules;
		}
		/**
		 * @param customer the customer to set
		 */
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		/**
		 * @return the objectList
		 */
		public List getObjectList() {
			if (ObjectFunctions.isNullOrEmpty(this.objectList)) {
				this.objectList = new ArrayList();
			}
			return this.objectList;
		}

		/**
		 * @param objectList the objectList to set
		 */
		public void setObjectList(List objectList) {
			this.objectList = objectList;
		}
		
		 /**
		 * @return the studyClassList
		 */
		public List<StudyClass> getStudyClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.studyClassList))
			{
				this.studyClassList=new ArrayList<StudyClass>();
			}
			return this.studyClassList;
		}

		/**
		 * @param studyClassList the studyClassList to set
		 */
		public void setStudyClassList(List<StudyClass> studyClassList) {
			this.studyClassList = studyClassList;
		}

		/**
		 * @return the studyClass
		 */
		public StudyClass getStudyClass() {
			return studyClass;
		}

		/**
		 * @param studyClass the studyClass to set
		 */
		public void setStudyClass(StudyClass studyClass) {
			this.studyClass = studyClass;
		}

		/**
		 * @return the studyClassId
		 */
		public String getStudyClassId() {
			return this.studyClassId;
		}

		/**
		 * @param studyClassId the studyClassId to set
		 */
		public void setStudyClassId(String studyClassId) {
			this.studyClassId = studyClassId;
		}

		/**
		 * @return the classList
		 */
		//Commented by seshu on 17th April.
		public List<ClassName> getClassList() {
			if (ObjectFunctions.isNullOrEmpty(this.classList)) {
				this.classList = new ArrayList<ClassName>();
			}
			return this.classList;
		}

		/**
		 * @param classList
		 *            the classList to set
		 */
		public void setClassList(List<ClassName> classList) {
			this.classList = classList;
		}

		/**
		 * @return the sectionList
		 */
		public List<Section> getSectionList()
		{
			return this.sectionList;
		}

		/**
		 * @param sectionList
		 *            the sectionList to set
		 */
		public void setSectionList(List<Section> sectionList) {
			this.sectionList = sectionList;
		}
		
		
		public Staff getStaff() {
			return staff;
		}

		public void setStaff(Staff staff) {
			this.staff = staff;
		}
		
		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		/**
		 * @return the classTeacherList
		 */
		public List<ClassTeacher> getClassTeacherList() {
			if (ObjectFunctions.isNullOrEmpty(this.classTeacherList)) {
				this.classTeacherList = new ArrayList<ClassTeacher>();
			}
			return this.classTeacherList;
		}

		/**
		 * @param classTeacherList the classTeacherList to set
		 */
		public void setClassTeacherList(List<ClassTeacher> classTeacherList) {
			this.classTeacherList = classTeacherList;
		}

		/**
		 * @return the empId
		 */
		public String getEmpId() {
			return empId;
		}

		/**
		 * @param empId the empId to set
		 */
		public void setEmpId(String empId) {
			this.empId = empId;
		}

		
		/**
		 * @return the admissionSettings
		 */
		public AdmissionSettings getAdmissionSettings() {
			return admissionSettings;
		}

		/**
		 * @param admissionSettings the admissionSettings to set
		 */
		public void setAdmissionSettings(AdmissionSettings admissionSettings) {
			this.admissionSettings = admissionSettings;
		}

		/**
		 * @return the onlineApplicationDetails
		 */
		public OnlineApplicationDetails getOnlineApplicationDetails() {
			return onlineApplicationDetails;
		}

		/**
		 * @param onlineApplicationDetails the onlineApplicationDetails to set
		 */
		public void setOnlineApplicationDetails(
				OnlineApplicationDetails onlineApplicationDetails) {
			this.onlineApplicationDetails = onlineApplicationDetails;
		}

		/**
		 * @return the onlineApplicationDetailsView
		 */
		public OnlineApplicationDetailsView getOnlineApplicationDetailsView() {
			return onlineApplicationDetailsView;
		}

		/**
		 * @param onlineApplicationDetailsView the onlineApplicationDetailsView to set
		 */
		public void setOnlineApplicationDetailsView(
				OnlineApplicationDetailsView onlineApplicationDetailsView) {
			this.onlineApplicationDetailsView = onlineApplicationDetailsView;
		}
		/**
		 * @return the transferCertificate
		 */
		public TransferCertificate getTransferCertificate() {
			return transferCertificate;
		}

		/**
		 * @param transferCertificate the transferCertificate to set
		 */
		public void setTransferCertificate(TransferCertificate transferCertificate) {
			this.transferCertificate = transferCertificate;
		}
		
	public void prepareFeeDuesList() {
			getCollectionAndFeeDuesList().put("Total Defaulters", "Total Defaulters");
			getCollectionAndFeeDuesList().put("Class Wise Defaulters","Class And Term Wise Defaulters");
		}

	
	public boolean loadWorkBook()
	{
		try
		{
			setWs(new WorkbookSettings());
	 	    getWs().setLocale(new Locale("en", "EN"));
	 	    setWorkbook(Workbook.getWorkbook(getUpload(),getWs()));
	 	    if (ObjectFunctions.isNullOrEmpty(getWorkbook()))
	 	    {
	 	    	return false;
	 	    }
	 	    return true;
		}
		catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return false;
	}
	public void prepareTeachingRolesMap() {
		getTeachingRoleMap().put("", Constants.SELECT);
		getTeachingRoleMap().put(Constants.SCHOOL_PRINCIPAL, "Principal");
		getTeachingRoleMap().put(Constants.SCHOOL_VICEPRINCIPAL, "Vice Principal");
		getTeachingRoleMap().put(Constants.SCHOOL_HOD, "Hod");
		getTeachingRoleMap().put(Constants.SCHOOL_TEACHER, "Teacher");
		getTeachingRoleMap().put(Constants.SCHOOL_ASST_STAFF, "Asst Teacher");
		getTeachingRoleMap().put(Constants.SCHOOL_ADMIN_COORDINATOR, "Admin Coordinator");
		
	}

	public void prepareNonTeachingRolesMap(Customer cust) {
		if(ObjectFunctions.isNullOrEmpty(cust))
			cust = getCustomerByCustId();
		if(!ObjectFunctions.isNullOrEmpty(cust)){
			if(cust.isHostelModuleStatus()) {
				getNonTeachingRoleMap().put(Constants.SCHOOL_HOSTEL, "Hostel");
				getNonTeachingRoleMap().put(Constants.SCHOOL_HOSTELFINANCE, "HostelFinance");
			}
			if (cust.isTransportModuleStatus()) {
				getNonTeachingRoleMap().put(Constants.SCHOOL_TRANSPORT,"Transport");
				getNonTeachingRoleMap().put(Constants.SCHOOL_TRANSPORTFINANCE,"TransportFinance");
				getNonTeachingRoleMap().put(Constants.SCHOOL_HELPER, "Helper");
				getNonTeachingRoleMap().put(Constants.SCHOOL_DRIVER, "Driver");
			}
			cust = null;
		}
		getNonTeachingRoleMap().put(Constants.SCHOOL_LIBRARIAN, "Librarian");
		getNonTeachingRoleMap().put(Constants.SCHOOL_CLERK, "Clerk");
		getNonTeachingRoleMap().put(Constants.SCHOOL_FINANCE, "Finance");
		
		getNonTeachingRoleMap().put(Constants.SCHOOL_PEON, "Peon");
		getNonTeachingRoleMap().put(Constants.SCHOOL_AYAH, "Ayah");
		getNonTeachingRoleMap().put(Constants.SCHOOL_CONDUCTOR, "Conductor");
		getNonTeachingRoleMap().put(Constants.SCHOOL_SYSTEMADMINISTRATOR, "System Administrator");
		getNonTeachingRoleMap().put(Constants.SCHOOL_LABASST, "LabAsst");
		getNonTeachingRoleMap().put(Constants.SCHOOL_MANAGEMENTTRAINEE, "Management Trainee");
		getNonTeachingRoleMap().put(Constants.SCHOOL_WATCHMAN, "Watchman");
		getNonTeachingRoleMap().put(Constants.SCHOOL_TYPIST, "Typist");
		getNonTeachingRoleMap().put(Constants.SCHOOL_SWEEPER, "Sweeper");
		getNonTeachingRoleMap().put(Constants.SCHOOL_OTHER, "Other");
		getNonTeachingRoleMap().put(Constants.SCHOOL_PUBLICRELATIONOFFICER, "Public Relation Officer");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ADMINOFFICER, "Admin Officer");
		getNonTeachingRoleMap().put(Constants.SCHOOL_MANAGER, "Manager");
		//getNonTeachingRoleMap().put(Constants.SCHOOL_HELPER, "Helper");
		//getNonTeachingRoleMap().put(Constants.SCHOOL_DRIVER, "Driver");
		//getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_CHAIR_MAN, "Chairman");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_MESS_MANAGER, "Mess Manager");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_DIRECTOR, "Director");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_SALES_HEAD, "Sales Head");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_SALES_EXECUTIVE, "Sales Executive");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_COORDINATOR, "Coordinator");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_BDM, "BDM");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_STOREKEEPER, "Store Keeper");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_RECEPTIONIST, "Receptionist");
		getNonTeachingRoleMap().put(Constants.SCHOOL_ROLE_STAFF_NURSE, "Staff Nurse");
		
		getNonTeachingRoleMap().put("", Constants.SELECT);
	}
	
	public void prepareStaffRolesMap(Customer cust) {
	    if(ObjectFunctions.isNullOrEmpty(cust)){
	    	cust = getCustomerByCustId();
	    }
		getStaffRoles().put(Constants.SCHOOL_PRINCIPAL, "Principal");
		getStaffRoles().put(Constants.SCHOOL_VICEPRINCIPAL, "Vice Principal");
		getStaffRoles().put(Constants.SCHOOL_TEACHER, "Teacher");
		getStaffRoles().put(Constants.SCHOOL_LIBRARIAN, "Librarian");
		if(!ObjectFunctions.isNullOrEmpty(cust)){
			if(cust.isHostelModuleStatus()){
			    getStaffRoles().put(Constants.SCHOOL_HOSTEL, "Hostel");
			    getStaffRoles().put(Constants.SCHOOL_HOSTELFINANCE, "HostelFinance");
			}
			if (cust.isTransportModuleStatus() ) {
				getStaffRoles().put(Constants.SCHOOL_TRANSPORT, "Transport");
				getStaffRoles().put(Constants.SCHOOL_TRANSPORTFINANCE,"TransportFinance");
				getStaffRoles().put(Constants.SCHOOL_HELPER, "Helper");
				getStaffRoles().put(Constants.SCHOOL_DRIVER, "Driver");
			}
			cust = null;
		}
		getStaffRoles().put(Constants.SCHOOL_FINANCE, "Finance");
		//getStaffRoles().put(Constants.SCHOOL_DRIVER, "Driver");
		getStaffRoles().put(Constants.SCHOOL_CLERK, "Clerk");
		//getStaffRoles().put(Constants.SCHOOL_HELPER, "Helper");
		getStaffRoles().put(Constants.SCHOOL_HOD, "Hod");
		getStaffRoles().put(Constants.SCHOOL_ASST_STAFF, "Asst Teacher");
		getStaffRoles().put(Constants.SCHOOL_PEON, "Peon");
		getStaffRoles().put(Constants.SCHOOL_AYAH, "Ayah");
		getStaffRoles().put(Constants.SCHOOL_CONDUCTOR, "Conductor");
		getStaffRoles().put(Constants.SCHOOL_SYSTEMADMINISTRATOR, "System Administrator");
		getStaffRoles().put(Constants.SCHOOL_PUBLICRELATIONOFFICER, "Public Relation Officer");
		getStaffRoles().put(Constants.SCHOOL_ADMINOFFICER, "Admin Officer");
		getStaffRoles().put(Constants.SCHOOL_LABASST, "LabAsst");
		getStaffRoles().put(Constants.SCHOOL_MANAGEMENTTRAINEE, "Management Trainee");
		getStaffRoles().put(Constants.SCHOOL_WATCHMAN, "Watchman");
		getStaffRoles().put(Constants.SCHOOL_TYPIST, "Typist");
		getStaffRoles().put(Constants.SCHOOL_SWEEPER, "Sweeper");
		getStaffRoles().put(Constants.SCHOOL_SECRETARY, "Secretary");
		getStaffRoles().put(Constants.SCHOOL_MANAGER, "Manager");
		getStaffRoles().put(Constants.SCHOOL_OTHER, "Other");
		getStaffRoles().put(Constants.SCHOOL_ROLE_CHAIR_MAN, "Chairman");
		getStaffRoles().put(Constants.SCHOOL_ROLE_MESS_MANAGER, "Mess Manager");
		getStaffRoles().put(Constants.SCHOOL_ROLE_DIRECTOR, "Director");
		getStaffRoles().put(Constants.SCHOOL_ROLE_SALES_HEAD, "Sales Head");
		getStaffRoles().put(Constants.SCHOOL_ROLE_SALES_EXECUTIVE, "Sales Executive");
		getStaffRoles().put(Constants.SCHOOL_ROLE_COORDINATOR, "Coordinator");
		getStaffRoles().put(Constants.SCHOOL_ROLE_BDM, "BDM");
		getStaffRoles().put(Constants.SCHOOL_ROLE_EXECUTIVE_VICE_CHAIRMAN, "Executive Vice Chairman");
		getStaffRoles().put(Constants.SCHOOL_ROLE_VICE_CHAIRMAN, "Vice Chairman");
		getStaffRoles().put(Constants.SCHOOL_ROLE_TREASURER, "Treasurer");
		getStaffRoles().put(Constants.SCHOOL_ROLE_JOINT_SECRETARIES, "Joint Secretary");
		getStaffRoles().put(Constants.SCHOOL_ROLE_EXECUTIVE_MEMBER, "Executive Member");
		getStaffRoles().put(Constants.SCHOOL_ROLE_MEMBER, "Member");
		getStaffRoles().put(Constants.SCHOOL_ADMIN_COORDINATOR, "Admin Coordinator");
		getStaffRoles().put(Constants.SCHOOL_ROLE_STOREKEEPER, "Store keeper");
		getStaffRoles().put(Constants.SCHOOL_ROLE_RECEPTIONIST, "Receptionist");
		getStaffRoles().put(Constants.SCHOOL_ROLE_STAFF_NURSE, "Staff Nurse");
		getStaffRoles().put("", Constants.SELECTROLE);
	}
	public void prepareManagementRolesMap() {
		getManagementRoleMap().put("", Constants.SELECT);
		getManagementRoleMap().put(Constants.SCHOOL_ROLE_CHAIR_MAN, "Chairman");
		getManagementRoleMap().put(Constants.SCHOOL_ROLE_EXECUTIVE_VICE_CHAIRMAN, "Executive Vice Chairman");
		getManagementRoleMap().put(Constants.SCHOOL_ROLE_VICE_CHAIRMAN, "Vice Chairman");
		getManagementRoleMap().put(Constants.SCHOOL_SECRETARY, "Secretary");
		getManagementRoleMap().put(Constants.SCHOOL_ROLE_TREASURER, "Treasurer");
		getManagementRoleMap().put(Constants.SCHOOL_ROLE_JOINT_SECRETARIES, "Joint Secretary");
		getManagementRoleMap().put(Constants.SCHOOL_ROLE_EXECUTIVE_MEMBER, "Executive Member");
		getManagementRoleMap().put(Constants.SCHOOL_ROLE_MEMBER, "Member");
	}
	/* @Description 15rd Apr cvs: Modularization  above  method  disable the staff   change the adminAction to staffAction*/  
	
   	public void prepareSmsReportsMap() {
		if (getUser().getIsSchoolAdmin() == "Y" || getUser().getIsSchoolTeacher() == "Y" || getUser().getIsSchoolDirector() == "Y" || getUser().getIsOnlySchoolHod() == "Y" || getUser().getIsSchoolPrincipal() == "Y" || getUser().getIsSchoolHostel() == "Y" || getUser().getIsHostelFinance() == "Y" || getUser().getIsSchoolStudent() == "Y" || getUser().getIsParent() == "Y" || getUser().getIsSchoolFinance() == "Y" || getUser().getIsSchoolTransport() == "Y" || getUser().getIsTransportFinance() == "Y") {
			getSmsFeeReports().put("Today", "Today");
		}
		if (getUser().getIsSchoolAdmin() == "Y" || getUser().getIsSchoolTeacher() == "Y" || getUser().getIsSchoolDirector() == "Y" || getUser().getIsOnlySchoolHod() == "Y" || getUser().getIsSchoolPrincipal() == "Y" || getUser().getIsSchoolHostel() == "Y" || getUser().getIsHostelFinance() == "Y" || getUser().getIsSchoolFinance() == "Y" || getUser().getIsSchoolTransport() == "Y" || getUser().getIsTransportFinance() == "Y") {
			getSmsFeeReports().put("Days Between", "Days Between");
		}
		if (getUser().getIsSchoolAdmin() == "Y" || getUser().getIsSchoolTeacher() == "Y" || getUser().getIsSchoolDirector() == "Y" || getUser().getIsOnlySchoolHod() == "Y" || getUser().getIsSchoolPrincipal() == "Y" || getUser().getIsSchoolTransport() == "Y" || getUser().getIsTransportFinance() == "Y" || getUser().getIsSchoolFinance() == "Y" || getUser().getIsSchoolHostel() == "Y" || getUser().getIsHostelFinance() == "Y") {
			getSmsFeeReports().put("Term Wise", "Term Wise");
		}
		if (getUser().getIsSchoolAdmin() == "Y" || getUser().getIsSchoolTeacher() == "Y" || getUser().getIsSchoolDirector() == "Y" || getUser().getIsOnlySchoolHod() == "Y" || getUser().getIsSchoolPrincipal() == "Y" || getUser().getIsSchoolTransport() == "Y" || getUser().getIsTransportFinance() == "Y" || getUser().getIsSchoolFinance() == "Y" || getUser().getIsSchoolHostel() == "Y" || getUser().getIsHostelFinance() == "Y") {
			getSmsFeeReports().put("Over All", "Over All");
		}
	}

	public void prepareLeaveTypes() {
		getLeaveTypes().put("CL", "Casual Leave");
		getLeaveTypes().put("SL", "Sick Leave");
		getLeaveTypes().put("EL", "Earned Leave");
	}
	
	public void prepareWeekDays() {
		getWeekDaysList().put(1, "SUNDAY");
		getWeekDaysList().put(2, "MONDAY");
		getWeekDaysList().put(3, "TUESDAY");
		getWeekDaysList().put(4, "WEDNESDAY");
		getWeekDaysList().put(5, "THURSDAY");
		getWeekDaysList().put(6, "FRIDAY");
		getWeekDaysList().put(7, "SATURDAY");
		
	}
	
	public void prepareTimeTablePriorityDetails() {
		getWeekDaysList().put(0, "No Priority");
		getWeekDaysList().put(1, "Morning Session");
		getWeekDaysList().put(2, "Afternoon Session");
		getWeekDaysList().put(3, "Morning Last Period");
		getWeekDaysList().put(4, "Afternoon Last Period");
	}
	
	
	/**
	 * @return the user
	 */
	public User getUser() {
		try
		{
		
	        if(!ObjectFunctions.isNullOrEmpty(this.user))
	    	{
	    		return this.user;
	    	}	
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	if(!ObjectFunctions.isNullOrEmpty(auth)){
				Object obj =auth.getPrincipal();
				if(obj instanceof User)
				{
					this.user=(User)obj;
				}
				else if(obj instanceof String)
				{
					String luserName=(String) obj;
					if(!"roleAnonymous".equalsIgnoreCase(luserName))
					{
						setUser(userManager.getUserByUserName(luserName));
					}
				}
				else
				{
					log.debug("User session time-out. User has to re-login");
					return null;
				}
		    }
		}
		catch(Exception ex)
		{
			ex.getMessage();
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
	    return this.user;
   }
	
   public long getUserCustId(){
	   if(!ObjectFunctions.isNullOrEmpty(getUser())){
		   String branchId=(String)getSession().getAttribute("branch");
		   if(StringFunctions.isNullOrEmpty(branchId)){
			   Customer customer = (Customer)getSession().getAttribute("userCustomer");
			   if(!ObjectFunctions.isNullOrEmpty(customer)) return customer.getId();
			   else return getUser().getCustId(); 
		   }
		   else
			   return Long.valueOf(branchId);
			    
	   }
	   return 0;
   }
   /*
    * Added by Prasad to get Current acadmiecYearId or Previous academicYearId when an user changes from Existing Year to Previous Year
    * according to our requirements and use session.previousYear='N' for CurrentYear and session.previousYear='Y' for Past year
    */
   public long getUserAcademicYearId(){
	   
		if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYear"))){
			return Long.valueOf((String) getSession().getAttribute("academicYear"));
		}  
	 	   return 0;
   }
   /*
    * Added by Prasad to get Current AcademicYear Object which can be used this object istead of writing the same code many times 
    * 
    */
   public AcademicYear getCurrentAcademicYear(){
	   AcademicYear academicYear  = adminManager.getCurrentAcademicYear("Y",getUserCustId());
		if (!ObjectFunctions.isNullOrEmpty(academicYear)) 
		{
			return academicYear;
		}  
	 	   return null;
   } 
   public void settingSessionCustomerByCustId()
	{
		getSession().removeAttribute("CustomerByCustId");
		Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
		if(!ObjectFunctions.isNullOrEmpty(customer))
		{
			getSession().setAttribute("CustomerByCustId", customer);
		}
		customer = null;
	}
	//Method created and modified by Narahari 23/April/2013 for performance issue 
	public Customer getCustomerByCustId() {
        if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("customer"))) {
            return (Customer)getSession().getAttribute("customer");
        }else{
            return (Customer)adminManager.get(Customer.class,"id="+getUserCustId()); 
        }
    }
	
    public String getFileTypeDocPath(String fileType) throws Throwable {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'getFileTypeDocPath' method");
	}

	if (StringFunctions.isNullOrEmpty(fileType)) {
	    return Constants.FILE_TYPE_PATH + "unknown.gif";
	}
	if (Constants.FILE_TYPE_WORD.equalsIgnoreCase(fileType)) {
	    return Constants.FILE_TYPE_PATH + "doc.gif";
	} else if (Constants.FILE_TYPE_PPT.equalsIgnoreCase(fileType)) {
	    return Constants.FILE_TYPE_PATH + "ppt.gif";
	} else if (Constants.FILE_TYPE_PDF.equalsIgnoreCase(fileType)) {
	    return Constants.FILE_TYPE_PATH + "pdf.jpg";
	} else if (Constants.FILE_TYPE_ZIP.equalsIgnoreCase(fileType)) {
	    return Constants.FILE_TYPE_PATH + "zip.gif";
	} else if (Constants.FILE_TYPE_XLS.equalsIgnoreCase(fileType)) {
	    return Constants.FILE_TYPE_PATH + "xls.gif";
	} else if (Constants.FILE_TYPE_XML.equalsIgnoreCase(fileType)) {
	    return Constants.FILE_TYPE_PATH + "xml.gif";
	}
	return Constants.FILE_TYPE_PATH + "unknown.gif";
    }

    public String getFileTypeImage() throws Throwable {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'getFileTypeImage' method");
	}

	String lcontentType = getUploadContentType();
	if (StringFunctions.isNullOrEmpty(lcontentType)) {
	    return Constants.FILE_TYPE_PATH + "unknown.gif";
	}
	String fileType[] = lcontentType.split("/");
	if (Constants.FILE_TYPE_IMAGE.equalsIgnoreCase(fileType[0])) {
	    return Constants.FILE_TYPE_PATH + "img.gif";
	} else if (Constants.FILE_TYPE_APPLICATION.equalsIgnoreCase(fileType[0])) {
	    return getFileTypeDocPath(fileType[1]);
	} else if (Constants.FILE_TYPE_TEXT.equalsIgnoreCase(fileType[0])) {
	    return Constants.FILE_TYPE_PATH + "txt.gif";
	}
	return Constants.FILE_TYPE_PATH + "unknown.gif";
    }

    public Attachment imageUpload(String userName, String usage) throws Throwable {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'imageUpload' method");
	}

	// this line is here for when the input page is upload-utf8.jsp,
	// it sets the correct character encoding for the response
	Attachment attachment = null;
	try {
	    MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
	    if (multiWrapper.hasErrors()) {
		Collection errors = multiWrapper.getErrors();
		Iterator i = errors.iterator();
		while (i.hasNext()) {
		    addActionError((String) i.next());
		}
		return null;
	    }
	    userName = StringFunctions.stripSymbols(userName);
	    if (userName == null || userName.length() == 0 || "".equals(userName)) {
		userName = "default";
	    }

	    // the directory to upload to
	    StringBuffer srcFileWithDir = new StringBuffer();
	    StringBuffer userDir = new StringBuffer();
	    StringBuffer url = new StringBuffer();
	    if (Constants.FILE_PROFILE_USAGE.equalsIgnoreCase(usage)) {
		url.append(getText(Constants.FILE_PROFILE_DIR));
	    } else {
		url.append(getSaveDir());
	    }
	    userDir.append(getSession().getServletContext().getRealPath(url.toString()));
	    // userDir.append("/");
	    // userDir.append(userName);
	    srcFileWithDir.append(userDir.toString());
	    srcFileWithDir.append("/");
	    setUploadFileName(getUploadFileName().replaceAll(" ", "_"));
	    setUploadFileName(StringFunctions.stripSymbols(getUploadFileName()));
	    srcFileWithDir.append(getUploadFileName());

	    File destDir = new File(srcFileWithDir.toString());
	    FileUtils.copyFile(getUpload(), destDir);
	    /*
	     * Create a ThumNail for a uploaded file which is used mainly for
	     * Group Directory, Personal Profile section
	     */
	    int pos = getUploadFileName().indexOf(".");

	    // log.debug(" thumbNailname : " +
	    // getUploadFileName().substring(0,pos)+"_thumb"+getUploadFileName().substring(pos,getUploadFileName().length()));
	    String thumbNailFileName = getUploadFileName().substring(0, pos) + "_thumb"
		    + getUploadFileName().substring(pos, getUploadFileName().length());
	    userDir.append("/");
	    userDir.append(thumbNailFileName);
	    String thumbNailName = userDir.toString();
	    // srcFilewithDir.substring(0,pos)+"_thumb"+srcFilewithDir.substring(pos,srcFilewithDir.length());

	    // log.debug("Calling ThumbNail CreateThumbNail method with " +
	    // userDir+","+ thumbNailName);
	    ThumbNail thumbNail = new ThumbNail(585, 337);
	    thumbNail.createThumbNail(srcFileWithDir.toString(), thumbNailName);
	    // PhotoAlbum.getThumbNail(srcFilewithDir,thumbNailName);
	    // log.debug("Successfully created thumb nail!!!!!");

	    attachment = new Attachment();
	    url.append("/");
	    // url.append(userName);
	    // url.append("/");
	    attachment.setFileName(getUploadFileName());
	    attachment.setThumbNail(thumbNailFileName);
	    attachment.setFilePath(url.toString());
	    attachment.setFileType(contentType);
	    attachment.setFileTypePath(getFileTypeImage());
	    attachment.setFileUsed(usage);
	    if (log.isDebugEnabled()) {
		log.debug(" Created Party Files Record : ");
	    }
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	} catch (IOException ex) {
	    log.warn(" Exception imageUpload ");
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}

	return attachment;
    }
	/**
	 * @return the saveDir
	 */
	public String getSaveDir() {
		return saveDir;
	}
	/**
	 * @param saveDir the saveDir to set
	 */
	@Inject("struts.multipart.userProfileImgDir") 
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
	
	public String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		char[] chars = new char[] { 'x', 'b', 'c', 'm','d', 'q', 'a', 'g','k','r','o'};
		for ( int i = 0; i < 9; i++ ) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}


	
    public String getFirstName()
    { 
    	try{   
	    	setCustomer((Customer)userManager.get(Customer.class, getUserCustId()));      	 
			return getCustomer().getFirstName(); 
    	}
    	catch(Exception ex){
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    		return "";
    	}
    }
    

	
	 /**
	     * Convenience method to get the Configuration HashMap
	     * from the servlet context.
	     *
	     * @return the user's populated form from the session
	     */
	    protected Map getConfiguration() {
	        Map config = (HashMap) getSession().getServletContext().getAttribute(Constants.CONFIG);
	        // so unit tests don't puke when nothing's been set
	        if (config == null) {
	            return new HashMap();
	        }
	        return config;
	    }
	    
		public UserImage profileImageUpload(String usage,Long customerId,Long academicYearId,Long imageId) throws Throwable
	    {
	    	 if (log.isDebugEnabled()) {
	             log.debug("Entering 'profileImageUpload' method");
	         }
	    	 
	             UserImage profileImage=null;
	        try {
	        	MultiPartRequestWrapper multiWrapper =(MultiPartRequestWrapper)ServletActionContext.getRequest();
		        	if(!StringFunctions.isNullOrEmpty(getSelectedId())){
		        		Enumeration fileParaNames = multiWrapper.getFileParameterNames();
		     			while (fileParaNames.hasMoreElements()) {
		    				String key = (String) fileParaNames.nextElement();
		    				String[] cTypes = multiWrapper.getContentTypes(key);
		    				setUploadContentType(cTypes[0].toString());
		     			}
		        	 }
					if (multiWrapper.hasErrors()) {
						Collection errors = multiWrapper.getErrors();
						Iterator i = errors.iterator();
						while (i.hasNext()) {
							addActionError((String) i.next());
						}
						return null;
					}
			        String thumbNailFileName = null;
			        if ((Constants.FILE_TYPE_IMAGE_PNG.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_IMAGE_JPG.equalsIgnoreCase(getUploadContentType())
							|| Constants.FILE_TYPE_IMAGE_JPEG.equalsIgnoreCase(getUploadContentType()))) {
			        	
			        		AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, academicYearId);
			        	
					        if(!StringFunctions.isNullOrEmpty(getSelectedId())){ // Here we are getting selectedId for multiple Image upload.
					        	 profileImage  = new UserImage();
					        	 //profileImage=(UserImage) adminManager.saveOrUpdateObject(profileImage);
					        	 setUploadFileName(String.valueOf(profileImage.getId())+".jpg");
					        }
					        else{
					        	 if(imageId > 0) {
						        	 profileImage = (UserImage)studentManager.get(UserImage.class, "id="+imageId);
						        	 if(!StringFunctions.isNullOrEmpty(profileImage.getPath()))
						        	 {
						        		 /*try {
											S3Wrapper s3Wrapper = new S3Wrapper();
											 URL url = new URL(profileImage.getPath());
											 s3Wrapper.delete(url);
										} catch (Exception e) {
											log.debug("Error msg:" + e.getMessage());
										}*/
						        	 }
						        	 setUploadFileName(String.valueOf(profileImage.getId())+".jpg");
						         }else{
						        	 profileImage  = new UserImage();
						        	 //profileImage=(UserImage) adminManager.saveOrUpdateObject(profileImage);
						        	 setUploadFileName(String.valueOf(profileImage.getId())+".jpg");
						         }
					       }
					        setUploadFileName(getUploadFileName().replaceAll(" ","_"));
					        setUploadFileName(StringFunctions.stripSymbols(getUploadFileName()));
					        File file = null;
					        if(!StringFunctions.isNullOrEmpty(getSelectedId())){ // Here we are getting selectedId for multiple Image upload.
					        	//FileUtils.copyFile(getFileUpload().get(Integer.parseInt(getSelectedId())), destDir);
					        	file = getFileUpload().get(Integer.parseInt(getSelectedId()));
					        }
					      else{
					    	  file = getUpload();
					       }
					        
					         String path = adminManager.getUploadImageFilePath(file, academicYear.getAcademicYear(),getUploadFileName());
					         log.debug("path:"+path);
						     profileImage.setName(getUploadFileName());   
					         profileImage.setThumbNail(getUploadFileName());
					         profileImage.setPath(path);
					        // profileImage.setType(getUploadContentType());
					        // profileImage.setTypePath(getFileTypeImage());
					         profileImage.setFileUsed(usage);	
					         profileImage = (UserImage)adminManager.saveOrUpdateObject(profileImage);
					         if (log.isDebugEnabled()) {
					             log.debug(" Created Party Files Record : ");
					         }	 
					         thumbNailFileName = null;
					}
			        else
			        {
			        	super.addActionError("File not acceped. Please upload your file in jpg or jpeg or  png");
			        }
			       
	        } catch (Exception ex) {
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	            JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	        }
	        

	        return profileImage;
	    }

		public UserImage captureStudentImage(String imageData,String usage, Customer custmer) throws Throwable{
			if (log.isDebugEnabled()) {
				log.debug("Entering 'rssFeedParserMessage' method");
			}
			 UserImage profileImage=null;
			try {
				if(!StringFunctions.isNullOrEmpty(imageData)) 
				{
					//Customer custmer = getCustomerByCustId();
					if (!ObjectFunctions.isNullOrEmpty(custmer) && StringFunctions.isNotNullOrEmpty(custmer.getCustomerShortName())) {
						AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, getAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(academicYear)){
							if(!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())){
								if(ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails().getProfileImage())){
									profileImage  = new UserImage();
						        	//profileImage=(UserImage) adminManager.saveOrUpdateObject(profileImage);
								}
							}
						}
						else{
							OnlineApplicationDetails onlineApplicationDetails = (OnlineApplicationDetails) adminManager.get(OnlineApplicationDetails.class, "id=" + getEmpId());
							if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetails)){
								if(ObjectFunctions.isNullOrEmpty(onlineApplicationDetails.getProfileImage())){
									profileImage  = new UserImage();
						        	//profileImage=(UserImage) adminManager.saveOrUpdateObject(profileImage);
								}
								else
									profileImage  = onlineApplicationDetails.getProfileImage();
							}
							else if(ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())){
								if(getTempId() >0){
									profileImage = (UserImage)adminManager.get(UserImage.class,getTempId());
										if(ObjectFunctions.isNullOrEmpty(profileImage)){
												profileImage  = new UserImage();
												//profileImage=(UserImage) adminManager.saveOrUpdateObject(profileImage);
									}
								}else if(getTempId() == 0){
									profileImage  = new UserImage();
									//profileImage=(UserImage) adminManager.saveOrUpdateObject(profileImage);
								}
	
							}
						}
						if(!ObjectFunctions.isNullOrEmpty(profileImage)){
					        StringBuffer fileName = new StringBuffer();
					        fileName.append(profileImage.getId()+".jpg");
						StudentsImagesCapturing(usage,fileName,imageData, profileImage,custmer.getId(),getUserAcademicYearId());
						fileName = null;
						}
					}
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			} catch (Throwable ex) {
				log.warn(" Exception imageCapture ");
				ex.printStackTrace();
			}
			return profileImage;
		}
		public String StudentsImagesCapturing(String usage,StringBuffer fileName,String imageData,UserImage profileImage,Long customerId,Long academicYearId) throws Throwable {
			try{				
		        AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, academicYearId);
		        BASE64Decoder decoder = new BASE64Decoder();
		        byte[] decodedBytes = decoder.decodeBuffer(imageData);//taking input string i.e the image contents in base 64
		        InputStream is = new ByteArrayInputStream(decodedBytes);
		        File tempFile = File.createTempFile(fileName.toString(), ".jpg");
	            tempFile.deleteOnExit();
	            try(FileOutputStream out = new FileOutputStream(tempFile)){
	                IOUtils.copy(is, out);
	            }
		        if(getTempId() > 0) {
			       	 profileImage = (UserImage)studentManager.get(UserImage.class, "id="+getTempId());
			       	/*S3Wrapper s3Wrapper = new S3Wrapper();
					URL url = new URL(profileImage.getPath());
					s3Wrapper.delete(url);*/
		        }
		         profileImage.setName(fileName.toString());   
		         profileImage.setThumbNail(fileName.toString());
		         profileImage.setPath(adminManager.getUploadImageFilePath(tempFile, academicYear.getAcademicYear(),fileName.toString()));
		         //profileImage.setType(getUploadContentType());
		         //profileImage.setTypePath(getFileTypeImage());
		         //profileImage.setFileUsed(usage);
		         profileImage = (UserImage)adminManager.saveOrUpdateObject(profileImage);
		         if (log.isDebugEnabled()) {
		             log.debug(" Created Student Files Record : ");
		         }	
		         fileName =null;
		         academicYear = null;
			}
			catch (Exception ex) {
			    log.warn(" Exception imageCapture Saving ");
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
				return SUCCESS;
		}
	public String rssFeedParserMessage() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'rssFeedParserMessage' method");
		}
		try {
			List rssFeedsList = new ArrayList();
			RSSFeedParser parser = new RSSFeedParser(
					"http://www.indiaedunews.net/rss/today.xml");
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
		public String ajaxGetKBank() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getStaffKBank' method");
		}try{
			List kBankList=null;
			KBankType kankType=null;
			//prepareAcademicYearId();
			/*long academicYearId = 0;
			   AcademicYear academicYear = null; 
			   if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYear"))) {
				academicYearId = Long.valueOf((String) getSession().getAttribute("academicYear"));
			} else {
				academicYearId = Long.valueOf((String) getSession().getAttribute("newYear"));
			}*/
			
			String kBankTypeName = getParamValue("kBankTypeName");
			if(StringFunctions.isNullOrEmpty(kBankTypeName)){
				setKBankTypeName(kBankTypeName);
			}
			List kBankTypeList=staffManager.getAll(KBankType.class, "custId="+getUserCustId());
			if(!ObjectFunctions.isNullOrEmpty(kBankTypeList)){
				for(Object obj :kBankTypeList){
					kankType=(KBankType)obj;
					setSelectedId(String.valueOf(kankType.getId()));
					if(!ObjectFunctions.isNullOrEmpty(kankType)){
					    if(kankType.getTypeName().equalsIgnoreCase("My Class Material")) {
						if (getUser().getIsSchoolStudent().equalsIgnoreCase("Y")) {
							student = (Student) adminManager.get(Student.class, " accountId = " +getUser().getId() + " and academicYearId = " + getUsrChgedAcademicId() + " and custId= " +getUserCustId()+ " and description is null");
							if(!ObjectFunctions.isNullOrEmpty(student)) {
							    long classId =  student.getClassNameClassId().getId();
								if(classId > 0) {
								    kBankList=adminManager.getAll(KBank.class, "kBankTypeId = "+kankType.getId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classId="+String.valueOf(classId));
									if(!ObjectFunctions.isNullOrEmpty(kBankList)){
										kankType.setKBankCount(kBankList.size());
									}
									 kBankList=null;
								}
							}
						    }else {
						    	kBankList=adminManager.getAll(KBank.class, "kBankTypeId = "+kankType.getId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
								if(!ObjectFunctions.isNullOrEmpty(kBankList)){
									kankType.setKBankCount(kBankList.size());
								}
								 kBankList=null;
						    }
						getKnowledgeBankTypeList().add(kankType);
					    }else {
					    	kBankList=adminManager.getAll(KBank.class, "kBankTypeId = "+kankType.getId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(kBankList)){
							kankType.setKBankCount(kBankList.size());
						}
						getKnowledgeBankTypeList().add(kankType);
						kBankList=null;
						kankType=null;
					    }
					}
				}
			}
		}catch(Exception ex){
			log.error("Entering into 'catch block':"+ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	public String ajaxKBankDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetKBankDetails' method");
		}
		try {
			if(getUserAcademicYearId()!=0){
				String kBankTypeId = getParamValue("kBankTypeId");
				if(StringFunctions.isNullOrEmpty(kBankTypeId))
					kBankTypeId=getSelectedId();
				String kBankTypeName = getParamValue("kBankTypeName");
				setKBankTypeName(kBankTypeName);
				setSelectedId(kBankTypeId);
				KBankType kBankType = null;
				List<KBank> kBankList = null;
				long classId = 0;
				Student student = null;
				setObjectList(null);
				if (!ObjectFunctions.isNullOrEmpty(kBankTypeId)) {
					if (getUser().getIsParent().equalsIgnoreCase("Y")) {
						List myChildrenList=studentManager.getMyChildren(getUser().getId(), getUserCustId(),getUserAcademicYearId() ,Constants.YES_STRING);
						ViewStudentPersonAccountDetails parentStudentClass = (ViewStudentPersonAccountDetails) myChildrenList.get(0);
						classId = parentStudentClass.getClassSectionId();
					} else {
						if (getUser().getIsSchoolStudent().equalsIgnoreCase("Y")) {
							student = studentManager.getStudentByAccountId(getUser().getId(), getUserAcademicYearId() ,getUserCustId());
							classId = student.getClassNameClassId().getId();
						}
					}

					if (classId != 0) {
						kBankType = (KBankType) adminManager.get(KBankType.class,Long.valueOf(kBankTypeId));
						if (!ObjectFunctions.isNullOrEmpty(kBankType)) {
								kBankList=adminManager.getAll(KBank.class, "kBankTypeId = "+kBankTypeId+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
						}
						if (!ObjectFunctions.isNullOrEmpty(kBankList)) {
							for (KBank kBank : kBankList) {
								if (!ObjectFunctions.isNullOrEmpty(kBank)) {
									List kBankFavouriteList = staffManager.getMyFavouritiesByUserIdAndKBankId(getUser().getId(), kBank.getId());
									if (ObjectFunctions.isNullOrEmpty(kBankFavouriteList)) {
										kBank.setKBankFavourite("N");
									} else {
										kBank.setKBankFavourite("Y");
									}
									getObjectList().add(kBank);
								}
								kBank = null;
							}
							if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
								Collections.sort(getObjectList());
								if (getObjectList().size() >= 3) {
									setObjectList(getObjectList().subList(0, 3));
								} else {
									setObjectList(getObjectList());
								}
							}
						}
					}

					else {
						if (getUser().getIsSchoolAdmin() == "Y" || getUser().getIsSchoolTeacher() == "Y" || getUser().getIsSchoolFinance() == "Y") {
							kBankList=staffManager.getAll(KBank.class, "kBankTypeId = "+kBankTypeId+" and custId="+getUserCustId());
							if (!ObjectFunctions.isNullOrEmpty(kBankList)) {
								for (KBank kBank : kBankList) {
									if (!ObjectFunctions.isNullOrEmpty(kBank)) {
										List kBankFavouriteList = staffManager.getMyFavouritiesByUserIdAndKBankId(getUser().getId(), kBank.getId());
										if (ObjectFunctions.isNullOrEmpty(kBankFavouriteList)) {
											kBank.setKBankFavourite("N");
										} else {
											kBank.setKBankFavourite("Y");
										}
										getObjectList().add(kBank);
									}
									kBank = null;
								}
								if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
									Collections.sort(getObjectList());
									if (getObjectList().size() >= 3) {
										setObjectList(getObjectList().subList(0, 3));
									} else {
										setObjectList(getObjectList());
									}
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
	public String ajaxAddCaseStudy() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoPostCaseStudy' method");
		}
		try	{
			long accountId=getUser().getId();
			if(!StringFunctions.isNullOrEmpty(getParamValue("selectedId")) && !StringFunctions.isNullOrEmpty(getParamValue("subjectId")) && !ObjectFunctions.isNullOrEmpty(getKnowledgeBank()) && !StringFunctions.isNullOrEmpty(getParamValue("commonTypeId"))){
				KBankType kBankType=(KBankType) staffManager.get(KBankType.class, Long.valueOf(getParamValue("selectedId")));
				if(!ObjectFunctions.isNullOrEmpty(kBankType)){
					if(kBankType.getTypeName().equalsIgnoreCase("My Class Material")){
						getKnowledgeBank().setClassId(Long.valueOf(getParamValue("classId")));
					}
					setKBankTypeName(kBankType.getTypeName());
				}
				getKnowledgeBank().setkBankType(kBankType);
				getKnowledgeBank().setCustId(getUserCustId());
				getKnowledgeBank().setUserId(accountId);
				getKnowledgeBank().setSkillCommonType((CommonType)adminManager.get(CommonType.class, Long.valueOf(getParamValue("commonTypeId"))));
				getKnowledgeBank().setSubjectId(Long.valueOf(getParamValue("subjectId")));
				getKnowledgeBank().setCreatedById(getUser().getId());
				getKnowledgeBank().setCreatedDate(new Date());
				getKnowledgeBank().setLastAccessDate(new Date());
				getKnowledgeBank().setAcademicYear(getCurrentAcademicYear());
				if("Y".equalsIgnoreCase(getUser().getIsSchoolTeacher()) || "ROLE_ADMIN".equalsIgnoreCase(getUser().getAdminOrDelegate())){
					getKnowledgeBank().setStatus("A");
				}else if("Y".equalsIgnoreCase(getUser().getIsSchoolStudent())){
					getKnowledgeBank().setStatus("I");
				}
				if(getUploadFileName()!= null){
					Attachment attachment;
					try {
						attachment =uploadKBankResources(getKnowledgeBank().getTitle());
						if(!ObjectFunctions.isNullOrEmpty(attachment)) {
							getKnowledgeBank().setAttachment(attachment);
			             }
					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
				staffManager.save(getKnowledgeBank());
				super.addActionMessage("Attachment created successfully.");
				setKnowledgeBankList(staffManager.getAll(KBank.class, "kBankTypeId = "+getParamValue("selectedId")+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
				if(!ObjectFunctions.isNullOrEmpty(getKnowledgeBankList())){
					Iterator kBankListIterator=getKnowledgeBankList().iterator();
					for(Iterator kBankListObjIterator=kBankListIterator;kBankListObjIterator.hasNext();){
						KBank kBankFav=(KBank) kBankListObjIterator.next();
						if(!ObjectFunctions.isNullOrEmpty(kBankFav)){
							List kBankFavouriteList= staffManager.getMyFavouritiesByUserIdAndKBankId(getUser().getId(),kBankFav.getId());
							if(ObjectFunctions.isNullOrEmpty(kBankFavouriteList)){
								kBankFav.setKBankFavourite("N");
							}else{
								kBankFav.setKBankFavourite("Y");
							}
							getObjectList().add(kBankFav);
						}
						kBankFav=null;
					}kBankListIterator=null;
					Collections.sort(getObjectList());
					if(getKnowledgeBankList().size()>=3){
						setObjectList(getObjectList().subList(0,3));
		          	}
					else{
						setObjectList(getObjectList());
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**
     * Convenience method to create a partyFile objects in it's scope
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param request
     *            The HTTP request we are processing
     * @param form
     *            The partyFiles
     */
public Attachment uploadKBankResources(String bookName) throws Throwable {
    if (log.isDebugEnabled()) {
        log.debug("Entering 'uploadFiles' method");
    }
    // this line is here for when the input page is upload-utf8.jsp,
    // it sets the correct character encoding for the response
    Attachment resource = new Attachment();
    try {
        MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
        Enumeration fileParaNames =multiWrapper.getFileParameterNames();
        while (fileParaNames.hasMoreElements()) {
            String key = (String) fileParaNames.nextElement();
            File[] fileObject = multiWrapper.getFiles(key);
            if(fileObject[0] instanceof File) {
                /*
                 * The following logic is to determine the order of the file
                 * upload from the html. Due to strange requirements, we have
                 * single file upload - upload as name and mulitple files -
                 * uploadList[] as a name So in order to handle all the
                 * situations, check the length of the name before ??? the chars
                 * Also this supports more than 10 file uploads from a single
                 * html submit
                 */
                String[] localSysfileNames = multiWrapper.getFileNames(key);
                StringBuffer srcFileWithDir = new StringBuffer();
                StringBuffer userDir = new StringBuffer();
                StringBuffer url = new StringBuffer();
                url.append(getText(Constants.FILE_DOCS_DIR));
                userDir.append(getSession().getServletContext().getRealPath(url.toString()));
                userDir.append("/");
                userDir.append(bookName);
                srcFileWithDir.append(userDir.toString());
                srcFileWithDir.append("/");
                setUploadFileName(localSysfileNames[0].replaceAll(" ","_"));
                setUploadFileName(StringFunctions.stripSymbols(getUploadFileName()));
                srcFileWithDir.append(getUploadFileName());
                File destDir = new File(srcFileWithDir.toString());
                FileUtils.copyFile(fileObject[0], destDir);
                url.append("/");
                url.append(bookName);
                url.append("/");
                resource.setFilePath(url.toString() + getUploadFileName());
                resource.setFileName(getUploadFileName());
                //staffManager.save(resource);
            }
     }
     log.debug("Files uploaded Successfully.");
    } catch (FileNotFoundException ex) {
        log.error("uploadFiles : " + ex.getMessage());
        log.error(ex.getCause());
    } catch (IOException ex) {
        log.error("uploadFiles : " + ex.getMessage());
        log.error(ex.getCause());if("Y".equalsIgnoreCase(getUser().getIsSchoolTeacher()) || "ROLE_ADMIN".equalsIgnoreCase(getUser().getAdminOrDelegate())){
			getKnowledgeBank().setStatus("A");
		}else if("Y".equalsIgnoreCase(getUser().getIsSchoolStudent())){
			getKnowledgeBank().setStatus("I");
		}
    } catch (Exception ex) {
        log.error("uploadFiles : " + ex.getMessage());
        log.error(ex.getCause());
    }
    return resource;
}
	 @Actions({
			@Action(value = "ajaxDownloadFiles", results = { @Result(location = "ajaxViewAddCaseStudy.jsp", name = "success") })})
		    public String downloadFiles(){
		        if (log.isDebugEnabled()) {
		            log.debug("Entering 'downloadFiles' method");
		        }
		        try {
		            String linkId = getParamValue("id");
		            if (!StringFunctions.isNullOrEmpty(linkId)) {
		                setKnowledgeBank((KBank) staffManager.get(KBank.class, Long.valueOf(linkId)));
		                if (!ObjectFunctions.isNullOrEmpty(getKnowledgeBank().getAttachment())) {
			                setAttachment((Attachment)staffManager.get(Attachment.class,getKnowledgeBank().getAttachment().getId()));
			                String fileName = "";
			                String filePath = "";
			                fileName = getAttachment().getFileName();
			                filePath = getAttachment().getFilePath();
			                StringBuffer userDir = new StringBuffer();
			                userDir.append(getSession().getServletContext().getRealPath(filePath));
			                String mimetype = getRequest().getSession().getServletContext().getMimeType(filePath);
			                getResponse().setContentType((mimetype != null) ? mimetype: "application/octet-stream");
			                File f = new File(userDir.toString());
			                getResponse().setContentLength((int) f.length());
			                getResponse().setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
			                // Stream to the requester.
			                //
			                byte[] bbuf = new byte[1024];
			                DataInputStream in = new DataInputStream(new FileInputStream(f));
			                int length = 0;
			                ServletOutputStream op = getResponse().getOutputStream();
			                while (in != null && (length = in.read(bbuf)) != -1) {
			                    op.write(bbuf, 0, length);
			                }
			                in.close();
			                op.flush();
			                op.close();
			                return null;
		                }
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		            JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		        }
		        return null;
		    }
	 public String ajaxDoEditCampus() throws URTUniversalException {
			try	{
				if(StringFunctions.isNotNullOrEmpty(getParamValue("id"))){
					 KBank kBank=(KBank) (staffManager.get(KBank.class,Long.valueOf(getParamValue("id"))));
					 
					 setKBankTypeName(String.valueOf(kBank.getkBankType().getTypeName()));
					 setSelectedId(String.valueOf(kBank.getkBankType().getId()));
					 setStudySubjectList(staffManager.getAll(StudySubject.class));
					 
						 if(!ObjectFunctions.isNullOrEmpty(kBank)){
							setKnowledgeBank((KBank)staffManager.get(KBank.class,kBank.getId()));
							setClassList(staffManager.getAll(ClassName.class));
							setClassId(String.valueOf(kBank.getClassId()));
							//ClassName className=(ClassName) (staffManager.get(ClassName.class,Long.valueOf(getClassId())));
							//setClassl(className.getClassName());
							//kBank.setClassId(className.getId());
							setKnowledgeBank(kBank); 
							
							setSubjectId(String.valueOf(kBank.getSubjectId()));
							StudySubject studySubject=(StudySubject) (staffManager.get(StudySubject.class,Long.valueOf(getSubjectId())));
							// JSR TO-DO: see the below commented line whether required or not
							//setSubject(studySubject.getName());
							kBank.setSubjectId(studySubject.getId());
							setKnowledgeBank(kBank); 
						 }
					}
				}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
		}
	 public String ajaxEditKBankStudies() throws URTUniversalException {
			try
			{
			long accountId=getUser().getId();
			if(!ObjectFunctions.isNullOrEmpty(getParamValue("knowledgeBank.id")) && !ObjectFunctions.isNullOrEmpty(getParamValue("subjectId"))){
				KBank kBank=(KBank) staffManager.get(KBank.class, Long.valueOf(getParamValue("knowledgeBank.id")));
				if(!ObjectFunctions.isNullOrEmpty(kBank)){
					KBankType kBankType=(KBankType) staffManager.get(KBankType.class, Long.valueOf(kBank.getkBankType().getId()));
					if(!ObjectFunctions.isNullOrEmpty(kBankType)){
						if(kBankType.getTypeName().equalsIgnoreCase("My Class Material")){
							kBank.setClassId(Long.valueOf(getParamValue("classId")));
						}
						setKBankTypeName(kBankType.getTypeName());
					}
					setSelectedId(String.valueOf(kBankType.getId()));
					kBank.setkBankType(kBankType);
					kBank.setCustId(getUserCustId());
					kBank.setTitle(getKnowledgeBank().getTitle());
					kBank.setSearchKewords(getKnowledgeBank().getSearchKewords());
					kBank.setDescription(getKnowledgeBank().getDescription());
					kBank.setSubjectId(Long.valueOf(getParamValue("subjectId")));
					//kBank.setClassId(Long.valueOf(getParamValue("classId")));
					kBank.setUserId(accountId);
					if(getUploadFileName()!= null){
						Attachment attachment;
						try {
							attachment =uploadKBankResources(getKnowledgeBank().getTitle());
							if(!ObjectFunctions.isNullOrEmpty(attachment)) {
								kBank.setAttachment(attachment);
				             }
						} catch (Throwable ex) {
							ex.printStackTrace();
						}
					}
					staffManager.save(kBank);
					super.addActionMessage("Attachment created successfully.");
					setKnowledgeBankList(staffManager.getAll(KBank.class, "kBankTypeId = "+getParamValue("kBankTypeId")+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classId="+classId));
					if(!ObjectFunctions.isNullOrEmpty(getKnowledgeBankList())){
						Iterator kBankListIterator=getKnowledgeBankList().iterator();
						for(Iterator kBankListObjIterator=kBankListIterator;kBankListObjIterator.hasNext();){
							KBank kBankFav=(KBank) kBankListObjIterator.next();
							if(!ObjectFunctions.isNullOrEmpty(kBankFav)){
								List kBankFavouriteList= staffManager.getMyFavouritiesByUserIdAndKBankId(getUser().getId(),kBankFav.getId());
								if(ObjectFunctions.isNullOrEmpty(kBankFavouriteList)){
									kBankFav.setKBankFavourite("N");
								}else{
									kBankFav.setKBankFavourite("Y");
								}
								getObjectList().add(kBankFav);
							}
							kBankFav=null;
						}kBankListIterator=null;
						Collections.sort(getObjectList());
						if(getObjectList().size()>=3){
							setObjectList(getObjectList().subList(0,3));
			          	}
						else{
							setObjectList(getObjectList());
						}
					}
				}
			}
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 public String ajaxViewReadMoreKbankStudy() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'viewReadMoreMinute' method");
			}
			try {
				if(StringFunctions.isNotNullOrEmpty(getParamValue("id")))
				{
				 KBank kBank=(KBank) (staffManager.get(KBank.class,Long.valueOf(getParamValue("id"))));
				 setStudySubjectList(staffManager.getAll(StudySubject.class));
					 if(!ObjectFunctions.isNullOrEmpty(kBank)){
						setKnowledgeBank((KBank)staffManager.get(KBank.class,kBank.getId()));
						setSubjectId(String.valueOf(kBank.getSubjectId()));
						StudySubject studySubject=(StudySubject) (staffManager.get(StudySubject.class,Long.valueOf(getSubjectId())));
						// JSR TO-DO: see the below commented line whether required or not
						//setSubject(studySubject.getName());
						kBank.setSubjectId(studySubject.getId());
						setKnowledgeBank(kBank); 
					 }
				}
			}catch(Exception ex) {
				log.error(" entering Catch Block of viewReadMoreMinute():" + ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return "viewReadMoreMinute";
		} 
	 @Actions({
			@Action(value = "ajaxDoGetTeacherMessages", results = { @Result(location = "myMessagesHome.jsp", name = "success") }),
			@Action(value = "ajaxDoGetReadTeacherMessages", results = { @Result(location = "viewTeacherMessagesList.jsp", name = "success") }),
				@Action(value = "ajaxGetMyInbox", results = { @Result(location = "ajaxViewMyInboxList.jsp", name = "success" )})		
		})
	 
	 public String ajaxDoGetTeacherMessages() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetTeacherMessages' method");
			}
			try
			{
				
				List myMessagesList = staffManager.getMyMessagesByReceiverAccountIdAndIsParent(String.valueOf(getUser().getId()),getUserCustId(),Constants.NO_STRING);
				if(!ObjectFunctions.isNullOrEmpty(myMessagesList)){
					Iterator myMessagesListIterator=myMessagesList.iterator();
	                for (Iterator myMessagesIterator = myMessagesListIterator;myMessagesIterator.hasNext();)
	                {
	                	Messages messages=(Messages)myMessagesIterator.next();
	                	//ViewStaffPersonAccountDetails staffDetails = (ViewStaffPersonAccountDetails)staffManager.get(ViewStaffPersonAccountDetails.class,Long.valueOf(messages.getSenderAccountId()));
	                	User account = (User)staffManager.get(User.class,Long.valueOf(messages.getSenderAccountId()));
	                	if(!ObjectFunctions.isNullOrEmpty(account))
	                	{
	                		messages.setFullPersonName(account.getPerson().getPersonFullName());
	                	}
	                	getMessagesList().add(messages);
	                	account=null;
	                	messages = null;
	                }
	                Collections.sort( getMessagesList());
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}

			return SUCCESS;
		}
	 
	 
	
	 @Actions({
			@Action(value = "ajaxDoSentBoxGetScrapMessagesList", results = { @Result(location = "messages/doGetScrapMessagesList.jsp", name = "success") })
		})
			public String ajaxDoSentBoxGetScrapMessagesList() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoSentBoxGetScrapMessagesList' method");
			}
			try
			{
				List scrapMessagesList = staffManager.getAllScrapMessagesListByReceiverAccId(getUser().getId(),Constants.ACTIVE_STATUS,null,getUserCustId(),String.valueOf(getUserAcademicYearId()),"G");
				setTempList(staffManager.getAllScrapMessagesListByReceiverAccId(getUser().getId(),Constants.ACTIVE_STATUS,"",getUserCustId(),String.valueOf(getUserAcademicYearId()),"C"));
				setTempList1(staffManager.getAllScrapMessagesListByReceiverAccId(getUser().getId(),Constants.ACTIVE_STATUS,"",getUserCustId(),String.valueOf(getUserAcademicYearId()),"S"));
				if(!ObjectFunctions.isNullOrEmpty(scrapMessagesList))
				{
					getScrapMessagesList().addAll(scrapMessagesList);
				}
				scrapMessagesList=null;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
	
			return SUCCESS;
		}
	 @Actions({
		    @Action(value = "ajaxGetMapBoundaries", results = { @Result(type = "json", name = "success", params = {"includeProperties","allUsersSet.*"}) }),
			@Action(value = "ajaxDoSendScrapMessage", results = { @Result(location = "messages/sendScrapMessage.jsp", name = "success" )}),
			@Action(value = "ajaxDoComposeScrapMessage", results = { @Result(location = "messages/composeScrapMessage.jsp", name = "success" )})
			})
			public String ajaxDoSendScrapMessage() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoSendScrapMessage' method");
			}
			try
			{
				ajaxDoGetUserPorfileDetails();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}

	 @Actions( { @Action(value = "ajaxSearchFirstNameAndLastName", results = { @Result(type = "json", name = "success", params = {"includeProperties", "objectList.*" }) }) })
		public String ajaxSearchFirstNameAndLastName() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSearchFirstNameAndLastName' method");
			}
			try {
				List<ViewAllUsers> allUsersList=null;
				String searchword = getParamValue("searchword");
				// setAbsentList(staffManager.getAllUsersByFirstNameOrLastName(searchword,getUserCustId()));
				if (!StringFunctions.isNullOrEmpty(searchword)) {
					StringBuffer queryString = new StringBuffer();
					//below lines are used when Reset password & send login credentials via SMS and E-mail  in search process (staff,parent,student)
					if(getUser().isParent()){
						queryString.append("custId="+ getUserCustId()+ " and accountExpired='"+ Constants.NO_STRING+ "' and (firstName like '%"+ searchword+ "%' or lastName like '%"+ searchword+ "%') and accountId not in ("+ getAnyTitle().substring(0, getAnyTitle().length() - 1)+ ") and (roleName='ROLE_TEACHER' or roleName='ROLE_ADMIN_COORDINATOR' or roleName='ROLE_PRINCIPAL' or roleName='ROLE_ADMIN' or roleName='ROLE_ADMINOFFICER' or roleName='ROLE_VICEPRINCIPAL' or roleName='ROLE_ASST_TEACHER' or roleName='ROLE_ADMINOFFICER')");
					}else{
						queryString.append("custId="+ getUserCustId()+ " and accountExpired='"+ Constants.NO_STRING+ "' and (firstName like '%"+ searchword+ "%' or lastName like '%"+ searchword+ "%')");
						if(!StringFunctions.isNullOrEmpty(getPlTitle())){
							if(getAnyTitle().length()>1)
								queryString.append("and accountId not in ("+ getAnyTitle().substring(0, getAnyTitle().length() - 1)+")");
							else
								queryString.append("and accountId not in (0)");
							if("S".equalsIgnoreCase(getPlTitle()) || "AS".equalsIgnoreCase(getPlTitle())){
								queryString.append(" and (roleName='ROLE_TEACHER' or roleName='ROLE_ADMIN_COORDINATOR'  or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL')");
							}else if("ST".equalsIgnoreCase(getPlTitle()) || "AC".equalsIgnoreCase(getPlTitle())){
								queryString.append(" and roleName='ROLE_STUDENT' ");
							}else if("N".equalsIgnoreCase(getPlTitle()) || "AN".equalsIgnoreCase(getPlTitle())){
								queryString.append(" and (roleName='ROLE_AYAH' or roleName='ROLE_CLERK' or roleName='ROLE_DRIVER' or roleName='ROLE_HELPER' or roleName='ROLE_SWEEPER' or roleName='ROLE_PEON' or roleName='ROLE_TYPIST' or roleName='ROLE_WATCHMAN' or roleName='ROLE_ADMIN' or  roleName='ROLE_ADMINOFFICER' or roleName='ROLE_CONDUCTOR' or roleName='ROLE_SYSTEMADMINISTRATOR' or roleName='ROLE_LABASST' or roleName='ROLE_TRANSPORT'  or roleName='ROLE_TRANSPORTFINANCE' or roleName='ROLE_FINANCE' or roleName='ROLE_OTHERS' or roleName='ROLE_STOREKEEPER' or roleName='ROLE_RECEPTIONIST' or roleName='ROLE_STAFF_NURSE')");
							}else if("P".equalsIgnoreCase(getPlTitle()) || "AP".equalsIgnoreCase(getPlTitle())){
								queryString.append(" and roleName='ROLE_PARENT'");
							}
						}else{
							//queryString.append("custId="+ getUserCustId()+ " and accountExpired='"+ Constants.NO_STRING+ "' and (firstName like '%"+ searchword+ "%' or lastName like '%"+ searchword+ "%') ");
							if (getUser().isHostelFinance() || getUser().isHostelFinance()) {
								queryString.append(" and bedId != 0");
							}
							if (!StringFunctions.isNullOrEmpty(getAnyTitle()) && !"0".equalsIgnoreCase(getAnyTitle()) && StringFunctions.isNullOrEmpty(getAnyId())) {
								queryString.append(" and accountId not in ("+ getAnyTitle().substring(0, getAnyTitle().length() - 1)+ ")" );
							}
							if (!StringFunctions.isNullOrEmpty(getAnyId()) && !StringFunctions.isNullOrEmpty(getAnyTitle())) {
								List<Object[]> siblingsList = adminManager.getAll("select siblingAccountId from studentSiblings where custId="+getUserCustId()+" and accountId="+getAnyTitle()+"");
								String siblingsIdsString=null;
								if (ObjectFunctions.isNotNullOrEmpty(siblingsList)) 
									siblingsIdsString = StringFunctions.convertListToCommaDelimitedString(siblingsList);
								else
									siblingsIdsString="0";
								//log.debug(siblingsIdsString);
								//queryString.append(" and accountId not in ("+ siblingsIdsString+","+getAnyTitle()+")").append(" and roleName= '"+getAnyId()+"' and parentId > 0" );
								queryString.append(" and accountId not in ("+ siblingsIdsString+","+getAnyTitle()+")").append(" and roleName= '"+getAnyId()+"'" );
							}
							
							/*else if (getUser().isSchoolTransport() || getUser().isTransportFinance()) {
								queryString.append(" and transportMode='"+ Constants.TRANSPORT_STATUS + "' ");
							}*/
						}
					}
					
					JSONArray res = new JSONArray();
					JSONObject j;
					allUsersList = adminManager.getAll(ViewAllUsers.class,queryString.toString());
					if (!ObjectFunctions.isNullOrEmpty(allUsersList)) {
						Collections.sort(allUsersList);
							for (ViewAllUsers users : allUsersList) {
								j = new JSONObject();
								j.put("accountId", users.getAccountId());
								j.put("firstName", users.getFirstName());
								j.put("lastName", users.getLastName());
								j.put("role", users.getRoleDescription());
								res.put(j);
							}
					} else {
						j = new JSONObject();
						j.put("accountId", 0);
						j.put("firstName", "No Results Found !!");
						j.put("lastName", "");
						j.put("role","");
						res.put(j);
					}
					j = new JSONObject();
					j.put("data", res);
					getResponse().getOutputStream().print(j.toString());
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}

	 @Actions({
			@Action(value = "ajaxDoGetDisplayName", results = { @Result(location = "messages/ajaxDoGetDisplayName.jsp", name = "success" )})
			})
			public String ajaxDoGetDisplayName() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetDisplayName' method");
			}
			try
			{
				//String id = getParamValue("id");
				//List accountList1 = staffManager.getAll(User.class);
				ViewAllUsers viewAllUsers =(ViewAllUsers) staffManager.get(ViewAllUsers.class,Long.valueOf(getParamValue("id")),"accountId");
				if(!ObjectFunctions.isNullOrEmpty(viewAllUsers))
				{
					setViewAllUsers(viewAllUsers);
				}
				viewAllUsers=null;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 @Actions( { @Action(value = "ajaxStaffBirthDayWishes", results = { @Result(location = "ajaxStaffBirthDayWishes.jsp", name = "success") }) })
		public String ajaxBirthDayWishes() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxStaffBirthDayWishes' method");
			}
			try {
				
				//String recieveAccountId = getParamValue("accountId");
				//String rollNum = getParamValue("rollNumber");
				//String personName = getParamValue("personName");
				//String senderFirstName = getParamValue("firstName");
				//String senderLastName = getParamValue("lastName");
				setSelectedId(getParamValue("accountId"));
				setAnyId(getParamValue("rollNumber"));
				setWishTitle("Happy BirthDay");
				setWishDescription("Wish you many more happy returns of the day");

			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}

			return SUCCESS;
		}
		
		public String ajaxAcceptKBankStudies() throws URTUniversalException {
			try	{
				if(StringFunctions.isNotNullOrEmpty(getParamValue("id"))){
					setKnowledgeBank((KBank) (staffManager.get(KBank.class,Long.valueOf(getParamValue("id")))));
						 if(!ObjectFunctions.isNullOrEmpty(getKnowledgeBank())){
							if("I".equalsIgnoreCase(getKnowledgeBank().getStatus())){
								getKnowledgeBank().setStatus("A");
							}
							staffManager.save(getKnowledgeBank()); 
							setKnowledgeBankList(staffManager.getAll(KBank.class, "kBankTypeId = "+getParamValue("kBankTypeId")+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classId="+classId));
							if(!ObjectFunctions.isNullOrEmpty(getKnowledgeBankList())){
								Collections.sort(getKnowledgeBankList());
								if(getKnowledgeBankList().size()>=3){
									setObjectList(getKnowledgeBankList().subList(0,3));
					          	}
								else{
									setObjectList(getKnowledgeBankList());
								}
							}
						 }
					}
				}catch(Exception ex){
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
		}
		public String ajaxDoAddKBankFavouriteStudies() throws URTUniversalException {
	    	try	{
				if(!StringFunctions.isNullOrEmpty(getParamValue("id")) && !ObjectFunctions.isNullOrEmpty(getUser())){
					KBank knowledgeBank = (KBank) staffManager.get(KBank.class,Long.valueOf(getParamValue("id")));
					if(!ObjectFunctions.isNullOrEmpty(knowledgeBank)){
						 User user=(User) staffManager.get(User.class, getUser().getId());
						 user.addKBankFavourite(knowledgeBank);
						 staffManager.save(user);
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		public String ajaxKBankFavourites() throws URTUniversalException {
	    	try	{
	    		if(!ObjectFunctions.isNullOrEmpty(getUser())){
	    			//setUser((User) staffManager.get(User.class, getUser().getId()));
				List kBankFavouriteList= staffManager.getMyFavouritiesByUserId(getUser().getId());
				if(!ObjectFunctions.isNullOrEmpty(kBankFavouriteList))
				{
					Iterator kBankFavouriteIdsIterator=kBankFavouriteList.iterator();
					for(Iterator kBankFavouritesIterator=kBankFavouriteIdsIterator;kBankFavouritesIterator.hasNext();){
						Object[] ccuserArray=(Object[])kBankFavouritesIterator.next();
						String kBankId = ccuserArray[1].toString();
						if(!ObjectFunctions.isNullOrEmpty(kBankId))
						{
							KBank kbank=(KBank) (staffManager.get(KBank.class,Long.valueOf(kBankId)));
							if(!ObjectFunctions.isNullOrEmpty(kBankId))
							{
								getObjectList().add(kbank);
							}
							kbank=null;
						}
					 }
				  }
	    		}
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		public String doAddComments() throws URTUniversalException {
		   	try	{
				if(!StringFunctions.isNullOrEmpty(getParamValue("id")) && !ObjectFunctions.isNullOrEmpty(getUser())){
					setKnowledgeBank((KBank) staffManager.get(KBank.class,Long.valueOf(getParamValue("id"))));
					setKBankTypeName(String.valueOf(getKnowledgeBank().getkBankType().getTypeName()));
					setSelectedId(String.valueOf(getKnowledgeBank().getkBankType().getId()));
					setTempList(getKnowledgeBank().getKBankCommentsList());
				}
			}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		public String addKBankComments() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddPostKBankComments' method");
			}
			try {	
				if(!StringFunctions.isNullOrEmpty(getParamValue("knowledgeBank.id")) && !ObjectFunctions.isNullOrEmpty(getUser()) && !ObjectFunctions.isNullOrEmpty(getKBankComments())){
					setKnowledgeBank((KBank) staffManager.get(KBank.class,Long.valueOf(getParamValue("knowledgeBank.id"))));
					if(!ObjectFunctions.isNullOrEmpty(getKnowledgeBank())) {
						//User user=(User) staffManager.get(User.class,getUser().getId());
						getKBankComments().setCommentAccount(getUser());
						getKBankComments().setCreatedById(getUser().getId());
						getKBankComments().setCreatedDate(new Date());
						getKBankComments().setLastAccessDate(new Date());
						getKnowledgeBank().addKBankComments(getKBankComments());
						setKnowledgeBank((KBank)staffManager.save(getKnowledgeBank()));
						setObjectList(getKnowledgeBank().getKBankCommentsList());
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return "success";
		}
		@Actions({
			@Action(value = "ajaxKhanVideoPlaylists", results = {})
						
			})
		public void getKhanVideoPlaylists() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'getKhanVideoPlaylists' method");
			}
			try {
				List<PlayList> allPlayList = staffManager.getAll(PlayList.class);
				if(!ObjectFunctions.isNullOrEmpty(allPlayList))
				{
					for(PlayList playList : allPlayList)
					{
						staffManager.remove(PlayList.class, playList.getId());
						playList=null;
					}
				}
				int statusCode=0;
				PlayList playList = null;
				HttpClient client=new HttpClient();
				GetMethod getPlayList=new GetMethod("http://www.khanacademy.org/");
				statusCode=client.executeMethod(getPlayList);
				if(statusCode==200){
					log.debug("getPlayList.getResponseBodyAsString()"+getPlayList.getResponseBodyAsString());
					String jsonResponse="[";
					jsonResponse+=getPlayList.getResponseBodyAsString();
					//JSONObject jsonOb= new JSONObject(jsonResponse.substring(jsonResponse.indexOf('{')));
					jsonResponse=jsonResponse.trim();		
					JSONArray playListJsonArray=new JSONArray(jsonResponse);
					for(int i=0;i<playListJsonArray.length();i++)
				    {
					playList = new PlayList();
					JSONObject playListJsonObject=playListJsonArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(playListJsonObject))
						{
						String api_url =(String) playListJsonObject.get("api_url");
						
						playList.setYoutubeUrl((String) playListJsonObject.get("youtube_url"));
						playList.setTitle((String) playListJsonObject.get("title"));
						playList.setDescription(playListJsonObject.get("description").toString());
						playList.setYoutubeId((String) playListJsonObject.get("youtube_id"));
						playList.setCreatedById(getUser().getId());
						playList.setCreatedDate(new Date());
						playList.setLastAccessDate(new Date());
						playList= (PlayList) staffManager.save(playList);
						// getting playListVideo object
						GetMethod getPlayListVideo=new GetMethod(api_url.trim());
						int pvideostatusCode=client.executeMethod(getPlayListVideo);
						if(pvideostatusCode==200){
							String pListVideoJsonResponse=getPlayListVideo.getResponseBodyAsString();
							pListVideoJsonResponse=pListVideoJsonResponse.trim();					
							JSONArray pListVideoJsonArrayInfo=new JSONArray(pListVideoJsonResponse);
							for(int j=0;j<pListVideoJsonArrayInfo.length();j++)
							{
								PlayListVideo playListVideo =new PlayListVideo();
								JSONObject pVideoJsonObject=pListVideoJsonArrayInfo.getJSONObject(j);
								if(!ObjectFunctions.isNullOrEmpty(pVideoJsonObject))
								{
										playListVideo.setKaUrl((String) pVideoJsonObject.get("ka_url"));
										playListVideo.setTitle((String) pVideoJsonObject.get("title"));
										playListVideo.setYoutubeId((String) pVideoJsonObject.get("youtube_id"));
										playListVideo.setYoutubeUrl((String) pVideoJsonObject.get("youtube_url"));
										playListVideo.setUrl((String) pVideoJsonObject.get("url"));
										playListVideo.setKeywords(pVideoJsonObject.get("keywords").toString());
										playListVideo.setReadableId((String) pVideoJsonObject.get("readable_id"));
										playListVideo.setCreatedById(getUser().getId());
										playListVideo.setCreatedDate(new Date());
										playListVideo.setLastAccessDate(new Date());
										
										if (! (playListVideo.getYoutubeId().equals("C5Lbjbyr1t4") || playListVideo.getYoutubeId().equals("wSqkieBUuo8")
											|| playListVideo.getYoutubeId().equals("0jk6uLZ1Tdc")|| playListVideo.getYoutubeId().equals("fkrv06F_KLo") 
											|| playListVideo.getYoutubeId().equals("Hlal9ME2Aig")|| playListVideo.getYoutubeId().equals("RhUdv0jjfcE") 
											|| playListVideo.getYoutubeId().equals("snw0BrCBQYQ")|| playListVideo.getYoutubeId().equals("aoXUWSwiDzE")
											|| playListVideo.getYoutubeId().equals("3Po3nfITsok")|| playListVideo.getYoutubeId().equals("s-5_H3z-Cv0")
											|| playListVideo.getYoutubeId().equals("rUT0pa87m7E")|| playListVideo.getYoutubeId().equals("O0uUVH8dRiU")
											|| playListVideo.getYoutubeId().equals("jQ-fS2lsslU")|| playListVideo.getYoutubeId().equals("Xz6rT9k8ftg")
											|| playListVideo.getYoutubeId().equals("SpDIXJ2I2D4")|| playListVideo.getYoutubeId().equals("hJ-_OoCHTks")
											|| playListVideo.getYoutubeId().equals("kyu-IQ-gBIg")|| playListVideo.getYoutubeId().equals("lLIo4kGRBEw")
											|| playListVideo.getYoutubeId().equals("lBi9bwz08EY")|| playListVideo.getYoutubeId().equals("ewEorPD4kdA")
											|| playListVideo.getYoutubeId().equals("I0b5-7UuwQ8")|| playListVideo.getYoutubeId().equals("BpBh8gvMifs")
											|| playListVideo.getYoutubeId().equals("s1QN7sSfBM8")|| playListVideo.getYoutubeId().equals("GfNB14D55gQ")
											|| playListVideo.getYoutubeId().equals("VhNkWdLGpmA")|| playListVideo.getYoutubeId().equals("6zixwWZ88tk")
											|| playListVideo.getYoutubeId().equals("rgvysb9emcQ")|| playListVideo.getYoutubeId().equals("Iqws-qzyZwc")
											|| playListVideo.getYoutubeId().equals("9wOalujeZf4")|| playListVideo.getYoutubeId().equals("BKEtsv9MbyY")
											|| playListVideo.getYoutubeId().equals("j37Mx24JHC4")|| playListVideo.getYoutubeId().equals("eUF59jCFcyQ")
											|| playListVideo.getYoutubeId().equals("17st-s5gg10")|| playListVideo.getYoutubeId().equals("tbQ_7zvRoN4")
											|| playListVideo.getYoutubeId().equals("4ES_vbSJ7LU")|| playListVideo.getYoutubeId().equals("husPzLE6sZc"))) 
										  {
											log.debug(" description :: "+ pVideoJsonObject.get("description"));
											if(!ObjectFunctions.isNullOrEmpty(pVideoJsonObject.get("description")))
											{
												playListVideo.setDescription(pVideoJsonObject.get("description").toString());
											}
											playList.addPlayListVideo(playListVideo);
										}
								}
							}
						}
						staffManager.save(playList);
					}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			//return SUCCESS;
		}


       public String ajaxGetKhanPlayListBase() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetKhanPlayListBase' method");
			}try{
				setSubjectsList(adminManager.getPlayListSubjects("PlayList"));
				setPresentList(staffManager.getAll(PlayList.class));
				Collections.sort(getPresentList());
				
			}catch(Exception ex){
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		public void ajaxGetPlayListVideosListBase(String playListId) throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetKhanPlayListBase' method");
			}try{
				PlayList playList = (PlayList)staffManager.get(PlayList.class,Long.valueOf(playListId));
				if(!ObjectFunctions.isNullOrEmpty(playList))
				{
					setPlayList(playList);
					if(!ObjectFunctions.isNullOrEmpty(playList.getPlayListVideo()))
					{
						setPlayListVideoSet(playList.getPlayListVideo());
					}
				}
			}catch(Exception ex){
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		
		public void ajaxPlaySelectedVideoBase(String playListVideoId) throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetKhanPlayListBase' method");
			}try{
				PlayListVideo playListVideo = (PlayListVideo)staffManager.get(PlayListVideo.class,Long.valueOf(playListVideoId));
				if(!ObjectFunctions.isNullOrEmpty(playListVideo))
				{
					setPlayListVideo(playListVideo);
				}
			}catch(Exception ex){
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		public void ajaxSearchKVideosByKeywordsBase(String keyword) throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetKhanPlayListBase' method");
			}try{
				List playListVideoList = staffManager.getPlayListVideosByKeywords(keyword);
				if(!ObjectFunctions.isNullOrEmpty(playListVideoList))
				{
					Collections.sort(playListVideoList);
					setPlayListVideoSet(ConvertUtil.convertListToSet(playListVideoList));
				}
			}catch(Exception ex){
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		public String ajaxGetKBankRating() {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxAddPostKBankComments' method");
				}
				try {	
					 String kBankId=getParamValue("kBankId");
					 String starValue=getParamValue("starValue");
					 setKnowledgeBank((KBank) staffManager.get(KBank.class,Long.valueOf(kBankId)));
					 setKBankRating(staffManager.getKBankRateByKBankId(kBankId));
					 getResponse().setContentType("application/x-json");
					 PrintWriter pw=getResponse().getWriter();
					 if(ObjectFunctions.isNullOrEmpty(getKBankRating())){
						 KBankRating kBankRating=new KBankRating();
						 kBankRating.setLogAccount(getUser());
						 kBankRating.setkBankId(Long.valueOf(kBankId));
						 kBankRating.setCreaterAccount(getUser());
						 kBankRating.setTotalValue(Integer.valueOf(starValue));
						 kBankRating.setTotalVotes(1);
						 kBankRating.setAverage((Integer.valueOf(starValue))/1);
						 getKnowledgeBank().addKBankRating(kBankRating);
						 staffManager.save(getKnowledgeBank());
						 setKBankRating(kBankRating);
					 }
					 else{
						    KBankRating kBankRating=new KBankRating();
						 	int oldTotal=getKBankRating().getTotalValue();
							int total=Integer.valueOf(starValue)+oldTotal;
							kBankRating.setTotalValue(total);
							int votevalue=getKBankRating().getTotalVotes();
							kBankRating.setTotalVotes(votevalue+1);
							float average=(float)kBankRating.getTotalValue().intValue()/(float)kBankRating.getTotalVotes().intValue();
							kBankRating.setAverage(average);
							kBankRating.setLogAccount(getUser());
							kBankRating.setCreaterAccount(getUser());
							getKnowledgeBank().addKBankRating(kBankRating);
							staffManager.save(getKnowledgeBank());
							setKBankRating(kBankRating);
					 }
					JSONObject msgJSONObject = new JSONObject();
					msgJSONObject.put("newRating", getKBankRating().getAverage());
					pw.println(msgJSONObject.toString());	
				}catch(Exception ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return null;
			}
		
		// Changed by Balu added  getUserAcademicYearId() 
		@Actions({
			@Action(value = "ajaxDoSendSchoolWideMessages", results = { @Result(location = "messages/sendSchoolWideMessagesHome.jsp", name = "success" )}),
			@Action(value = "ajaxDoSendSchoolWideEmails", results = { @Result(location = "messages/sendSchoolWideEmails.jsp", name = "success" )}),
			@Action(value = "ajaxDoSendChaiarManMessages", results = { @Result(location = "messages/ajaxCreateMessagesForChairMan.jsp", name = "success" )}),
			@Action(value = "ajaxDoSendChairManEmails", results = { @Result(location = "messages/email/sendEmailsFromChairMan.jsp", name = "success" )})

			})
		public String ajaxDoGetClassWideMessages(){
			try{
				String status = getParamValue("status");
				if(getUser().isSchoolTransport() || (!StringFunctions.isNullOrEmpty(status) && "TR".equalsIgnoreCase(status))){
				 setAnyTitle("TR");
				}if(!StringFunctions.isNullOrEmpty(getParamValue("plTitle")))
					setPlTitle(getParamValue("plTitle"));
				setMessages(null);
				setCustomer(getCustomerByCustId());
				setSmsCnt(staffManager.getTotalSmsCount(getUserCustId(),getUserAcademicYearId()));
			    ajaxDoClassWideMessages();
			    setSelectedId(getSelectedId()); //selectedId used when login class teacher set value in class wise sms 
			    setSmsAlloted((int) getCurrentAcademicYear().getAllotedsms()+(int) getCurrentAcademicYear().getPaidSms());
			}
			catch (Exception ex) {
				ex.getMessage();
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	
		@Actions( {
				@Action(value = "ajaxDoGetSchoolWideMessagesList", results = { @Result(location = "messages/sendSchoolWideMessagesList.jsp", name = "success") }),
				@Action(value = "ajaxCancelSchoolWideMessages", results = { @Result(location = "messages/sendSchoolWideMessagesList.jsp", name = "success") }),
				@Action(value = "ajaxSchoolEmailWideMessages", results = { @Result(location = "messages/sendSchoolWideMessagesList.jsp", name = "success") }),
				@Action(value = "ajaxDoGetTransportMessagesList", results = { @Result(location = "messages/sendSchoolWideMessagesList.jsp", name = "success") }),
				@Action(value = "ajaxDoGetClassWideMessagesList", results = { @Result(location = "messages/sendClassWideMessagesHome.jsp", name = "classWideMessages") }),
				@Action(value = "ajaxDoGetChairMessagesList", results = { @Result(location = "messages/sendSmsForAllStaff.jsp", name = "success") })})
		public String ajaxDoGetSchoolWideMessagesList()throws URTUniversalException {
	
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetSchoolWideMessagesList' method");
			}
			try {
				Date before30Day = DateFunctions.add(new Date(),-29);
				setCustomer(getCustomerByCustId());
				setSmsCnt(staffManager.getTotalSmsCount(getUserCustId(),getUserAcademicYearId()));
				List schoolWideMessagesList = null;
				String status = getParamValue("status");
				if(!ObjectFunctions.isNullOrEmpty(getUser()))
				{
				if (!ObjectFunctions.isNullOrEmpty(getAnyId())) {
					if ("M".equalsIgnoreCase(getAnyId())) {
						schoolWideMessagesList = staffManager.getAllMessagesByAccountId("M", getUser().getId(),String.valueOf(getUserAcademicYearId()),before30Day);
						//schoolWideMessagesList = staffManager.getAllMessagesByMessageStatus(getAnyId(),getUserCustId(), String.valueOf(getUserAcademicYearId()));
					} else if ("E".equalsIgnoreCase(getAnyId())) {
						schoolWideMessagesList = staffManager.getAllMessagesByAccountId("E", getUser().getId(),String.valueOf(getUserAcademicYearId()),before30Day);
						//schoolWideMessagesList = staffManager.getAllMessagesByMessageStatus(getAnyId(),getUserCustId(), String.valueOf(getUserAcademicYearId()));
					}
				}else
				{
					setAnyId("M");
					if(!getUser().isSchoolPrincipal())
						getIsClassTeacherOrNot();
					if(isTempBoolean() || getUser().isOnlySchoolHod() || getUser().isSchoolAsstStaff() || getUser().isAdminCoordinator() || getUser().isChairMan() || getUser().isReceptionist())
					{
						if(!getUser().isSchoolAsstStaff()  && !( getUser().isChairMan() || getUser().isReceptionist())){
							setTempBoolean(true); // this is used in when classteacher login check the tempboolean value
						}
						schoolWideMessagesList = staffManager.getAllMessagesByAccountId("M", getUser().getId(),String.valueOf(getUserAcademicYearId()),before30Day);	
					}
					else
					{
						//schoolWideMessagesList = staffManager.getAllMessagesByMessageStatus("M", getUserCustId(),String.valueOf(getUserAcademicYearId()));
						schoolWideMessagesList = staffManager.getAllMessagesByMessageStatus("", getUserCustId(),String.valueOf(getUserAcademicYearId()),before30Day);
					}
				}
				if("TR".equalsIgnoreCase(status) || getUser().isSchoolTransport()){
					before30Day = null;
					schoolWideMessagesList = staffManager.getAllMessagesByMessageStatus("TR",getUserCustId(), String.valueOf(getUserAcademicYearId()),before30Day);
				}
				setAnyTitle(status);
				if (!ObjectFunctions.isNullOrEmpty(schoolWideMessagesList)) {
					setNoticeBoardMessagesList(schoolWideMessagesList);
				}
				ajaxDoClassWideMessages();
				
				log.debug(getNoticeBoardMessagesList().size());
				/*if(isTempBoolean())
				{
					return "classWideMessages";
				}*/
			}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			
			return SUCCESS;
		}
	/********************************************************************
	 * Date              Name               Description
	 * ============      =======		    ==================
	 * April 1, 2013      Seshu		        Message description is not sending properly.
	 * April 3, 2013      Seshu				Changed By seshu on 3rd April Success message issue
	 * May 28, 2014		 Seshu				Code refactor 
	/********************************************************************/
		@Actions( {
				@Action(value = "ajaxSendSchoolWideMessages", results = { @Result(location = "messages/sendSchoolWideMessagesList.jsp", name = "success"),
																			@Result(location = "messages/sendClassWideMessagesHome.jsp", name = "classTeacher"),
																			@Result(location = "messages/sendClassWideMessagesHome.jsp", name = "SMSInquiry"),
																			@Result(location = "messages/sendSmsForAllStaff.jsp", name = "chairMan"),
																			@Result(location = "messages/sendSchoolWideMessage.jsp", name = "dummyInit") }) ,
				@Action(value = "ajaxSendExamAlertMsgToParent", results = { @Result(location = "messages/sendSchoolWideMessagesList.jsp", name = "success") })
		})
		public String ajaxSendSchoolWideMessages() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSendSchoolWideMessages' method");
			}
			try {
				log.debug("************ Start 'ajaxSendSchoolWideMessages' method ****************"); 
				List<String> fileUploadList =  new ArrayList<String>();
				StringBuffer pathName = null;
				if(getFileUpload().size()!=0)
			    { 
					pathName = new StringBuffer("userFiles/");
		    		pathName.append(getUserCustId());
		    		pathName.append("/uploadFiles");
		    		pathName.append("/");
		    		    
					for(int i=0;i<getFileUpload().size();i++)
			    	{
			    		if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i)))
			    		{
			    			File file = getFileUpload().get(i);
				    		String fileName = getFileUploadFileName().get(i);
				            File destDir = new File(getSession().getServletContext().getRealPath(pathName.toString()+fileName));
				            FileUtils.copyFile(file, destDir);
				            file = null;
			    		}
			    	}
					 File folder = new File(getSession().getServletContext().getRealPath(pathName.toString()));
					 File[] listOfFiles = folder.listFiles();
					 if(!ObjectFunctions.isNullOrEmpty(listOfFiles)){
						 int i = 0;
						 for (File filePaths : listOfFiles) {
						     if (filePaths.isFile()) {
						        fileUploadList.add(filePaths.getAbsolutePath());
						        log.debug(fileUploadList.get(i));
						     }
						 }
						 listOfFiles = null;
				    }
			    }
				if(!ObjectFunctions.isNullOrEmpty(getUpload()))
			    {
					log.debug(getUploadContentType());
					boolean excelFileType = false;
					excelFileType = validateExcelFileType(getUploadContentType());
					if(excelFileType){
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						return "dummyInit";
					}
					WorkbookSettings ws = new WorkbookSettings();
					ws.setLocale(new Locale("en", "EN"));
					Workbook workbook = Workbook.getWorkbook(getUpload(), ws);
					Sheet sheet = null;
					for (int sheetNum = 0; sheetNum < (workbook.getNumberOfSheets() - 1); sheetNum++) {
						sheet = workbook.getSheet(sheetNum);
						int rowSize = sheet.getRows();
						for (int i = 2; i < rowSize; i++) {
							if(StringFunctions.isNotNullOrEmpty(sheet.getCell(17, i).getContents())){
								if(sheet.getCell(17, i).getContents().length() == 10)
								{
									getMobileNumbersSet().add(sheet.getCell(17,i).getContents());
								}/*else
									getMobileNumbersSet().add(null);*/
							}/*else
								getMobileNumbersSet().add(null);*/
						}
				   }
			    }	
				if(getUser().isChairMan()){
					getMessages().setOtherType("OtherMember");
				}
				setAcademicYear(adminManager.getCurrentAcademicYear("Y",getUserCustId()));
				if((getUser().isSchoolAsstStaff() || getUser().isSchoolTeacher()) && "Y".equalsIgnoreCase(getCustomerByCustId().getPreferences().getApprovalRequiredForClassTeacherCreatedSMS()) )
				{
					setCustomer(getCustomerByCustId());
					setSmsCnt(staffManager.getTotalSmsCount(getUserCustId(),getUserAcademicYearId()));
					MessageEnquiryDetails smsDetails=new MessageEnquiryDetails();
					smsDetails.setMessageDescription(getMessages().getMessageDescription());
					smsDetails.setSenderName(getUser().getFullPersonName());
					smsDetails.setSmsCount(getChkBoxSelectedAccountIds().size());
					smsDetails.setMessageDate(getMessages().getMessageDate());
					smsDetails.setCustomer(getCustomerByCustId());
					smsDetails.setRoleName(getUser().getUserRoleDescription());
					smsDetails.setCreatedById(user.getId());
					smsDetails.setAcademicYear(getAcademicYear());
					String commaDelimitedStringOfStudent = StringUtil.convertListToString(chkBoxSelectedAccountIds);
					List<String> parentIds = adminManager.getAll("select parentId from vw_studentdetails where custId="+getUserCustId()+" and  academicYearId="+getUserAcademicYearId()+" and accountId in("+commaDelimitedStringOfStudent+") and status='Y'");
					String commaDelimitedStringOfParentIds = StringUtil.convertListToString(parentIds);
					smsDetails.setCheckBoxSelectedStudIds(commaDelimitedStringOfParentIds);
					smsDetails = (MessageEnquiryDetails) adminManager.save(smsDetails);
					return "SMSInquiry";
				}
				else
				addActionMessages(communicationManager.sendSchoolWideMessages(getMessages(), getUserCustId(), getAcademicYear(), getUser(), getChkBoxSelectedIds(), getChkBoxSelectedAccountIds(), getChkBoxClassSelectedIds(), getParamValue("trStatus"),fileUploadList,getMobileNumbersSet(),getAnyTitle(),getWishTitle()));
				if(fileUploadList.size() > 0)
	    		{
					for(String fileupload : fileUploadList)
					{
						File filePaths = new File(fileupload);
				    	filePaths.delete();
			    	}
	    		}
				if("classTeacher".equalsIgnoreCase(getSelectedId())){
					ajaxDoGetClassWideMessages();
					return "classTeacher";
				}
				if(getUser().isChairMan()){
					ajaxDoGetSchoolWideMessagesList();
					return "chairMan";
				}
				log.debug("************ End 'ajaxSendSchoolWideMessages' method ****************"); 
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			finally
			{
				ajaxDoGetSchoolWideMessagesList();
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxViewSWMessage", results = { @Result(location = "messages/viewSWMessage.jsp", name = "success" )}),
			@Action(value = "ajaxDoEditSchoolWideMessage", results = { @Result(location = "messages/editSchoolWideMessage.jsp", name = "success" )})
			})
			public String ajaxViewSWMessage() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewSWMessage' method");
			}
			try
			{
				String messageId = getParamValue("id");
				Messages messages =  (Messages)staffManager.get(Messages.class, Long.valueOf(messageId));
				if(!ObjectFunctions.isNullOrEmpty(messages))
				{
					setMessages(messages);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
		/*public void studentAttendanceViewForParents(long id){
    	    setTempId(id);
    	    getBaseStudentAttendance();
		}*/
		/********************************************************************
		 * Date              Name               Description
		 * ============      =======		    ==================
		 * Dec 12, 2013      Seshu		    	Validating whether score card generated for particular student or not.
		/********************************************************************/
		@Action(value = "ajaxExamSchedulesandMarks", results = { @Result(location = "ajaxStudentExamMarks.jsp", name = "success") })
		public String ajaxExamSchedulesandMarks()  throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxExamSchedulesandMarks' method");
			}
			try
			{
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(customer) && getStudentVo().getId() > 0){
					Student stud = (Student)adminManager.get(Student.class,getStudentVo().getId() );
					if(!ObjectFunctions.isNullOrEmpty(stud))
					{
						StudentVo studentVo = stud.deepCopyEntityToVO(stud);
						setStudentVo(studentVo);
						if(!ObjectFunctions.isNullOrEmpty(studentVo))
						{
							HashMap<Boolean,String>  fileDetails =  validateStudentFileExistOrNot(studentVo.getStudentName(),studentVo.getStudAdmissionNumber(),studentVo.getStudyClassVo().getId(),customer.getCustomerShortName());
							for(Map.Entry<Boolean,String> fileDetail : fileDetails.entrySet()){
								setTempString(fileDetail.getValue());
								setTempBoolean(fileDetail.getKey());
								fileDetail = null;
							}
							fileDetails = null;
						}
					}
					stud = null;
					customer = null;
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
		@Actions({
			@Action(value = "ajaxDoGetSchoolWideAlertsList", results = { @Result(location = "alerts/sendSchoolWideAlertsHome.jsp", name = "success" )}),
			@Action(value = "ajaxCancelSchoolWideAlerts", results = { @Result(location = "alerts/sendSchoolWideAlertsList.jsp", name = "success" )})
			})
			public String ajaxDoGetSchoolWideAlertsList() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoGetSchoolWideAlertsList' method");
			}
			try
			{
				List alertsList=null;
				boolean isAdmin=false;
				setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,new Date()));
				if(getUser().isSchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolHostel() || getUser().isSchoolDirector())
				{
					isAdmin=true;
					alertsList = staffManager.getAllAlertsByAlertStatus(getUserCustId(),"B","",isAdmin,getUserAcademicYearId() +"");
				}else if (getUser().isSchoolTeacher() || getUser().isSchoolLibrarian() || getUser().isSchoolFinance()) {
					alertsList = staffManager.getAllAlertsByAlertStatus(getUserCustId(),"B","SF",isAdmin,getUserAcademicYearId() +"");
				}
				else {
					alertsList = staffManager.getAllAlertsByAlertStatus(getUserCustId(),"B","ST",isAdmin,getUserAcademicYearId() +"");
				}
				if(!ObjectFunctions.isNullOrEmpty(alertsList))
				{
					setAlertsList(alertsList);
					Collections.sort(getAlertsList());
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxViewATAlert", results = { @Result(location = "alerts/viewSWAlert.jsp", name = "success" )}),
			@Action(value = "ajaxDoEditSchoolWideAlert", results = { @Result(location = "alerts/editSchoolWideAlert.jsp", name = "success" )})
			})

			public String ajaxViewSWAlert() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewATAlert' method");
			}

			try
			{
				String messageId = getParamValue("id");
				Object[] viewUserRoles=null;
				Messages messages = null;
				messages=  (Messages)staffManager.get(Messages.class, Long.valueOf(messageId));
				viewUserRoles=adminManager.get("select formatedFullName,accountId from vw_userRoles where custId="+getUserCustId()+" and accountId="+messages.getCreatedById()+"");
				if(!ObjectFunctions.isNullOrEmpty(viewUserRoles)){
					setAnyTitle(viewUserRoles[0].toString());
				}
				if(!ObjectFunctions.isNullOrEmpty(messages))
				{
					setMessages(messages);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxEditSchoolWideAlert", results = { @Result(location = "alerts/sendSchoolWideAlertsList.jsp", name = "success" )})
			})

			public String ajaxEditSchoolWideAlert() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxEditSchoolWideAlert' method");
			}

			try
			{
				String messageId=getParamValue("id");
				Messages lmessages =  (Messages)staffManager.get(Messages.class, Long.valueOf(messageId));
				if(!ObjectFunctions.isNullOrEmpty(getMessages().getTitle()))
				{
					 lmessages.setTitle(getMessages().getTitle());
			   }	
				if(!ObjectFunctions.isNullOrEmpty(getMessages().getMessageDescription()))
				{
					 lmessages.setMessageDescription(getMessages().getMessageDescription());
			   }	
				staffManager.save(lmessages);
				super.addActionMessage("Alert updated successfully.");
				ajaxDoGetSchoolWideAlertsList();
			}
			
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( { @Action(value = "ajaxDeleteAlert", results = { @Result(location = "alerts/sendSchoolWideAlertsList.jsp", name = "success") }) })
		public String ajaxDeleteAlert() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDeleteAlert' method");
			}

			try {
				
				String messageId = getParamValue("id");
				if(StringFunctions.isNullOrEmpty(messageId))
				{
					if(!StringFunctions.isNullOrEmpty(getParamValue("message.id")))
					{
						messageId=getParamValue("message.id");
					}
				}
				if(!StringFunctions.isNullOrEmpty(messageId))
				{
					Messages messages=(Messages)staffManager.get(Messages.class, Long.valueOf(messageId));
					messages.setAcademicYear(null);
					staffManager.remove(Messages.class, Long.valueOf(messageId));
					super.addActionMessage("Alert is deleted successfully.");
				}
				ajaxDoGetSchoolWideAlertsList();
			} 
			catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}

			return SUCCESS;
		}
		// Changed academicYearId by Balu 
		@Actions({
			@Action(value = "ajaxSendSchoolWideAlerts", results = { @Result(location = "alerts/sendSchoolWideAlertsList.jsp", name = "success" )}) })

			public String ajaxSendSchoolWideAlerts() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSendSchoolWideAlerts' method");
			}
			try
			{
				setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,new Date()));
				AcademicYear academicYear=getCurrentAcademicYear();
					if(!ObjectFunctions.isNullOrEmpty(getMessages()))
					{
						if(!ObjectFunctions.isNullOrEmpty(getCustomerByCustId())){
						     getMessages().setCustomer(getCustomerByCustId());
						}
						getMessages().setCreatedById(getUser().getId());
						getMessages().setCreatedDate(new Date());
						getMessages().setLastUpdatedDate(new Date());
						getMessages().setLastAccessDate(new Date());
						getMessages().setAcademicYear(academicYear);
						academicYear = null;
						staffManager.save(getMessages());
						super.addActionMessage("Alert posted successfully.");
					}
				List alertsList=null;
				boolean isAdmin=false;
				if(getUser().isSchoolAdmin())
				{
					isAdmin=true;
					alertsList = staffManager.getAllAlertsByAlertStatus(getUserCustId(),"B","",isAdmin,String.valueOf(getUserAcademicYearId() ));
				}
				if(getUser().isSchoolTeacher())
				{
					alertsList = staffManager.getAllAlertsByAlertStatus(getUserCustId(),"B","SF",isAdmin,String.valueOf(getUserAcademicYearId() ));
				}
				else{
					alertsList = staffManager.getAllAlertsByAlertStatus(getUserCustId(),"B","ST",isAdmin,String.valueOf(getUserAcademicYearId() ));
				}
				if(!ObjectFunctions.isNullOrEmpty(alertsList))
				{
					setAlertsList(alertsList);
					Collections.sort(getAlertsList());
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			ajaxDoGetSchoolWideMessagesList();
			return SUCCESS;
		}
		@Actions( { @Action(value = "readMoreAlerts", results = { @Result(location = "alerts/myAlertsHome.jsp", name = "viewReadMoreAlerts") }) })
		public String viewReadMoreAlerts() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'viewReadMoreAlerts' method");
			}
			try {
				ajaxDoGetSchoolWideAlertsList();
			} catch (Exception ex) {
				log.error(" entering Catch Block of viewReadMoreAlerts():"+ ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return "viewReadMoreAlerts";
		}
		public String staffLeaveApprovalsAsApprover() throws URTUniversalException {
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'doStaffLeaveApprovalsAsApprover' method");
			}
			try
			{
				String academicYearId = null;
				if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYear"))) {
					academicYearId = (String) getSession().getAttribute("academicYear");
				} else {
					academicYearId = (String) getSession().getAttribute("newYear");
				}
				/*ViewStaffPersonAccountDetails viewStaffPersonAccountDetails= (ViewStaffPersonAccountDetails)staffManager.get(ViewStaffPersonAccountDetails.class,id);
				if(!ObjectFunctions.isNullOrEmpty(viewStaffPersonAccountDetails))
				{
					setViewStaffPersonAccountDetails(viewStaffPersonAccountDetails);
				}*/
				//All Approval Leaves
				if ("Y".equalsIgnoreCase(getUser().getIsSchoolPrincipal())) {
					List viewStaffLeaveDetailsList= adminManager.getAllLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.PENDING_STATUS,Constants.SCHOOL_HOD,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStaffLeaveDetailsList))
					{
						setClassNameList(viewStaffLeaveDetailsList);
						setLeaveApprovalCount(getClassNameList().size());
						log.debug("Leave approval count issue:" + getLeaveApprovalCount());
					}
					List viewStaffAcceptLeaveDetailsList= adminManager.getAllLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.ACTIVE_STATUS, Constants.SCHOOL_HOD,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStaffAcceptLeaveDetailsList))
					{
						setAbsentList(viewStaffAcceptLeaveDetailsList);
					}
					List viewStaffRejectLeaveDetailsList= adminManager.getAllLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.REJECTED_STATUS, Constants.SCHOOL_HOD,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStaffRejectLeaveDetailsList))
					{
						setClassList(viewStaffRejectLeaveDetailsList);
					}
				}
				else if("Y".equalsIgnoreCase(getUser().getIsOnlySchoolHod()))
				{
					List viewStaffLeaveDetailsList= adminManager.getAllLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.PENDING_STATUS,Constants.SCHOOL_TEACHER,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStaffLeaveDetailsList))
					{
						setClassNameList(viewStaffLeaveDetailsList);
						setLeaveApprovalCount(getClassNameList().size());
						log.debug("Leave approval count issue:" + getLeaveApprovalCount());
					}
					List viewStaffAcceptLeaveDetailsList= adminManager.getAllLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.ACTIVE_STATUS, Constants.SCHOOL_TEACHER,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStaffAcceptLeaveDetailsList))
					{
						setAbsentList(viewStaffAcceptLeaveDetailsList);
					}
					List viewStaffRejectLeaveDetailsList= adminManager.getAllLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.REJECTED_STATUS, Constants.SCHOOL_TEACHER,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStaffRejectLeaveDetailsList))
					{
						setClassList(viewStaffRejectLeaveDetailsList);
					}
					
					List viewStudentLeaveDetailsList= staffManager.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.PENDING_STATUS,Constants.SCHOOL_STUDENT,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStudentLeaveDetailsList))
					{
						setStudentFeeAbove15List(ConvertUtil.convertListToSet(viewStudentLeaveDetailsList));
						setStudentLeaveApprovalCount(getStudentFeeAbove15List().size());
						log.debug("Leave approval count issue:" + getStudentLeaveApprovalCount());
					}
					List viewStudentAcceptLeaveDetailsList= staffManager.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.ACTIVE_STATUS, Constants.SCHOOL_STUDENT,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStudentAcceptLeaveDetailsList))
					{
						setStudentPaymentList(viewStudentAcceptLeaveDetailsList);
					}
					List viewStudentRejectLeaveDetailsList= staffManager.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.REJECTED_STATUS, Constants.SCHOOL_STUDENT,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStudentRejectLeaveDetailsList))
					{
						setClassFeeList(viewStudentRejectLeaveDetailsList);
						
					}
				}
				else if("Y".equalsIgnoreCase(getUser().getIsSchoolTeacher()))
				{
					List viewStudentLeaveDetailsList= staffManager.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.PENDING_STATUS,Constants.SCHOOL_STUDENT,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStudentLeaveDetailsList))
					{
						setClassNameList(viewStudentLeaveDetailsList);
						setLeaveApprovalCount(getClassNameList().size());
						log.debug("Leave approval count issue:" + getLeaveApprovalCount());
					}
					log.debug("Leave approval ACTIVE_STATUS =========== ");
					List viewStudentAcceptLeaveDetailsList= staffManager.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.ACTIVE_STATUS, Constants.SCHOOL_STUDENT,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStudentAcceptLeaveDetailsList))
					{
						setAbsentList(viewStudentAcceptLeaveDetailsList);
					}
					log.debug("Leave approval REJECTED_STATUS =========== ");
					List viewStudentRejectLeaveDetailsList= staffManager.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.REJECTED_STATUS, Constants.SCHOOL_STUDENT,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStudentRejectLeaveDetailsList))
					{
						setClassList(viewStudentRejectLeaveDetailsList);
					}
					List viewParentExpLeaveDetailsList= staffManager.getAllStudentLeavesByStatusAndRoleNameAndSupervisorId(getUserCustId(),Constants.ADD_RIDE_SUBJECT, Constants.SCHOOL_STUDENT,getUser().getId(),Long.valueOf(academicYearId));
					if(!ObjectFunctions.isNullOrEmpty(viewStudentRejectLeaveDetailsList))
					{
						setLeavesList(viewParentExpLeaveDetailsList);
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}

	public void ajaxMyChildsClassSchedule(long id){
	    setLeaveApprovalCount(id);
	    //ajaxClassWiseUpcomingExamSchedules();
	}

	public void generateAllClassFeeDetails(List<ClassName> classNamesList,List<SchoolTerms> schoolTermsList, List<FeeType> feeTypeList,PDFGenerator pDFGenerator, String reqYear) {
		try {
			String fontPath = getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+ "/Droid-Sans/DroidSans-Bold.ttf");
			FontFactory.register(fontPath);
			// Header Part (School Name,Student Name,Class Name)
			PdfPTable headerTable = new PdfPTable(2);
			PdfPTable headerTable1 = null;
			headerTable.setWidthPercentage(100);
			headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
			// PdfPCell cell=null;
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
				if (!ObjectFunctions.isNullOrEmpty(customer.getOrganization())) {
					headerTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper(customer.getOrganization().toUpperCase(), 2,fontPath, "#FFFFFF", 15, "#005CB9",Element.ALIGN_CENTER, 5.0f));
					headerTable.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper("School Class Fee Details",2, fontPath,"#FFFFFF", 12, "#005CB9",Element.ALIGN_CENTER, 5.0f));
					pDFGenerator.getDocument().add(headerTable);
				}
				customer = null;
			}
			int noOfColumns = schoolTermsList.size() + 1;
			PdfPTable feeHeaderContentTable = new PdfPTable(noOfColumns);
			feeHeaderContentTable.setWidthPercentage(100);
			int[] widths = new int[noOfColumns];
			widths[0] = 7;
			for (int i = 1; i < widths.length; i++)
				widths[i] = 5;
			feeHeaderContentTable.setWidths(widths);
			feeHeaderContentTable.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingJasper1("", noOfColumns, fontPath));
			feeHeaderContentTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass("Fee Type","#000", fontPath));
			for (SchoolTerms schoolTerms : schoolTermsList) {
				feeHeaderContentTable.addCell(PDFGenerator.getPdfCellHeaderCellJasperClass(schoolTerms.getTermName(), "#000",fontPath));
			}
			feeHeaderContentTable.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingJasper1("", noOfColumns, fontPath));
			pDFGenerator.getDocument().add(feeHeaderContentTable);
			if (!ObjectFunctions.isNullOrEmpty(classNamesList)) {
				for (ClassName className : classNamesList) {
					List<ViewClassFeeDetails> classFeeDetails = adminManager.getClassFeeDetailsList(className.getId(), reqYear,getUserCustId());
					if(!ObjectFunctions.isNullOrEmpty(classFeeDetails)){
						headerTable1 = new PdfPTable(2);
						headerTable1.setWidthPercentage(100);
						headerTable1.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper("Class Name: "+ className.getClassName(),2, fontPath, "#FFFFFF", 10, "#005CB9",Element.ALIGN_LEFT, 5.0f));
						headerTable1.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingJasper1("", 2,fontPath));
						pDFGenerator.getDocument().add(headerTable1);

						
						if (!ObjectFunctions.isNullOrEmpty(schoolTermsList) && !ObjectFunctions.isNullOrEmpty(feeTypeList) && !ObjectFunctions.isNullOrEmpty(classFeeDetails)) {
							if (!ObjectFunctions.isNullOrEmpty(schoolTermsList)) {
								// creating PDF page event to set header and Footer to document
								PdfPTable classFeeTable = new PdfPTable(1);
								classFeeTable.setWidthPercentage(100);
								classFeeTable.getDefaultCell().setBorder(Rectangle.BOX);
								PdfPTable feeContentTable = new PdfPTable(noOfColumns);
								feeContentTable.setWidthPercentage(100);
								widths[0] = 7;
								for (int i = 1; i < widths.length; i++)
									widths[i] = 5;
								feeContentTable.setWidths(widths);
								for (FeeType feeType : feeTypeList) {
									if (!ObjectFunctions.isNullOrEmpty(feeType)) {
										feeContentTable.addCell(PDFGenerator.getPdfCellWithLeftAlignJasper(feeType.getFeeType(),fontPath));
									}
									for (SchoolTerms schoolTerms : schoolTermsList) {
										Fee termFee = adminManager.getFeeByClasIdAndCustIdAndTermIdAndTypeId(className.getId(), feeType.getId(),schoolTerms.getId(),getUserCustId(), Long.valueOf(reqYear));
										if (!ObjectFunctions.isNullOrEmpty(termFee)) {
											if (termFee.getFeeAmount()!=0) {
												feeContentTable.addCell(PDFGenerator.getPdfCellWithLeftAlignJasper(String.valueOf(termFee.getFeeAmount()),fontPath));
											} else {
												feeContentTable.addCell(PDFGenerator.getPdfCellWithLeftAlignJasper("0.0",fontPath));
											}
										}else {
											feeContentTable.addCell(PDFGenerator.getPdfCellWithLeftAlignJasper("0.0",fontPath));
										}
									}
								}
								classFeeTable.addCell(feeContentTable);
								classFeeTable.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingJasper1("", noOfColumns, fontPath));
								pDFGenerator.getDocument().add(classFeeTable);
							}
							classFeeDetails = null;
						}
					}
					className = null;
					headerTable1=null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	public void ajaxSyllabusByScheduleId() throws URTUniversalException {
		try
		{
			if(!StringFunctions.isNullOrEmpty(getAnyId())){
				ExamSchedules schedule=(ExamSchedules)adminManager.get(ExamSchedules.class, Long.valueOf(getAnyId()));
				if(!ObjectFunctions.isNullOrEmpty(schedule)){
					//setSyllabusList(schedule.getSyllabus());
					schedule=null;
				}
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions({
		@Action(value = "ajaxGetKhanTopics", results = { @Result(location = "../admin/kBank/viewKhanTopicListHome.jsp", name = "success" )}) })
	public String ajaxGetKhanTopics() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetKhanTopics' method");
		}try{
			String subName=getParamValue("subjectName");
			setPlSubjectName(subName); 
			if(!StringFunctions.isNullOrEmpty(subName)){
				List playList = adminManager.getAllTitlesByPlaylist(subName);
 				if(!ObjectFunctions.isNullOrEmpty(playList)){
				 getObjectList().addAll(playList);
				}
			}
		}catch(Exception ex){
			log.error("Entering into 'catch block':"+ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxGetKhanSubTopics", results = { @Result(location = "../admin/kBank/viewKhanSubTopicList.jsp", name = "success" )}) })
		public String ajaxGetKhanSubTopics() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetKhanSubTopics' method");
			}try{
				String subName;
				String subjectName;
				if(!StringFunctions.isNullOrEmpty(getParamValue("subTopic")) && !StringFunctions.isNullOrEmpty(getParamValue("subjectName"))){
					 subName=getParamValue("subTopic");
					 subjectName=getParamValue("subjectName"); 
				}else
				{
					 subName=getParamValue("plTitle");
					 subjectName=getParamValue("plSubjectName"); 
				}
				String topicId=getParamValue("subTopicId");
				String plTitle=getParamValue("plTitle");
				setPlSubjectName(subjectName);
				setPlSubTopic(subName); 
				setPlTitle(plTitle);
				if(!StringFunctions.isNullOrEmpty(subName)){
					List subTopicList = adminManager.getPlayListForSubTopics(subName);
	 				if(subTopicList.size() != 1){
					 getObjectList().addAll(subTopicList);
					}else {
						List videoTopicList = adminManager.getVideoPlayList(Long.valueOf(topicId));
		 				if(!ObjectFunctions.isNullOrEmpty(videoTopicList)){
		 					getKnowledgeBankList().addAll(videoTopicList);
						}
					}
				}
			}catch(Exception ex){
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	@Actions({
		@Action(value = "ajaxGetKhanSubVideoTopics", results = { @Result(location = "../admin/kBank/viewKhanSubVideoTopicList.jsp", name = "success" )}) })
		public String ajaxGetKhanSubVideoTopics() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetKhanSubVideoTopics' method");
			}try{
				String subjectName=getParamValue("subjectName");
				String subTopic=getParamValue("subTopic");
				String title=getParamValue("title");
				setPlSubjectName(subjectName);
				setPlSubTopic(subTopic); 
				setPlTitle(title);
				String playListId=getParamValue("videoTopicId");
				setSelectedId(playListId);
				if(!StringFunctions.isNullOrEmpty(playListId)){
					List videoTopicList = adminManager.getVideoPlayList(Long.valueOf(playListId));
	 				if(!ObjectFunctions.isNullOrEmpty(videoTopicList)){
					 getObjectList().addAll(videoTopicList);
					}
			}
			}catch(Exception ex){
				log.error("Entering into 'catch block':"+ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	
	
	 /*
	  * Changes made for getStaff method by Prasad - 04-22-2013
	  */

	@Actions( { @Action(value = "ajaxGetStaffStudyClasses", results = { @Result(location = "../admin/common/ajaxViewStudyClassList.jsp", name = "success") }) })
	public String ajaxGetStaffStudyClasses() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStaffStudyClasses' method");
		}
		try {
			setStudyClassList(null);
			List<ClassTeacher> teacherSubjetsList = null;
			HashSet<StudyClass> classSections = new HashSet<StudyClass>();
			if(getUser().getId() > 0 )
			{
				setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
			}
			if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
				if (getStaff().getId() > 0 && getUserAcademicYearId() > 0) {
					//teacherSubjetsList = staffManager.getTeacherSubjectsByIdAndAcademicYear(getStaff().getId(), getUserAcademicYearId(),getUserCustId());
					List<String> classSectionIds = staffManager.getAll("select studyClassId from classTeacher where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and teacherId="+getStaff().getId()+" group by studyClassId");
					String teacherStudyClassId=null;
					if(!ObjectFunctions.isNullOrEmpty(classSectionIds))
						teacherStudyClassId=StringFunctions.convertListToCommaDelimitedString(classSectionIds);
					else
						teacherStudyClassId="0";
					List<StudyClass> studyClassList = staffManager.getAll(StudyClass.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id in ("+teacherStudyClassId+") and filledSeats<>0");
					if (!ObjectFunctions.isNullOrEmpty(studyClassList)) {
						for (StudyClass studyClass : studyClassList) {
							classSections.add(studyClass);
							getStudyClassesMap().put(studyClass.getClassId(),studyClass.getClassName());
							studyClass=null;
						}
						studyClassList=null;
					}
					classSectionIds=null;
					teacherStudyClassId=null;
					/*if (!ObjectFunctions.isNullOrEmpty(teacherSubjetsList)) {
						for (ClassTeacher teacher : teacherSubjetsList) {
							if (!ObjectFunctions.isNullOrEmpty(teacher.getStudyClass())) {
								if (teacher.getStudyClass().getFilledSeats() > 0) {
									classSections.add(teacher.getStudyClass());
									getStudyClassesMap().put(teacher.getStudyClass().getClassId(),teacher.getStudyClass().getClassName());
								}
							}
						}
					}*/
					if(getUser().isOnlySchoolHod() || getUser().isAdminCoordinator())
					{
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
				}
			}
			classSections = null;
			teacherSubjetsList = null;
			//staff = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	public List getHodStudyClassesList(long staffId,long academicyearId)
	{
		ArrayList<StudyClass> classSectionsList = new ArrayList<StudyClass>();
		StringBuffer sqlQuery = new StringBuffer("select  st.id,st.className,st.section,st.classNameClassId  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
    	.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+academicyearId).append(" and sm.staffId=").append(staffId);
		List<Object[]> studyclassesList=staffManager.getAll(sqlQuery.toString());
    	if(!ObjectFunctions.isNullOrEmpty(studyclassesList)){
    		for(Object[] obj :studyclassesList){
    			if(!ObjectFunctions.isNullOrEmpty(obj[0]) && !ObjectFunctions.isNullOrEmpty(obj[1]) && !ObjectFunctions.isNullOrEmpty(obj[3])){
    				StudyClass st= new StudyClass();
    				st.setId(Long.valueOf(obj[0].toString()));
    				st.setClassName(obj[1].toString());
    				st.setSection(obj[2].toString());
    				classSectionsList.add(st);
    				getStudyClassesMap().put(Long.valueOf(obj[3].toString()),obj[1].toString());
					st = null;
    			}
    		}
    	}
		return classSectionsList;
	}
	//public void saveActiveLogs(Customer customer, String mobileNumbers, String fromAddress, Messages message, boolean delivered){
	public void saveActiveLogs(String mobileNumbers, Messages message, boolean delivered){  
		try {
			messages=(Messages) userManager.saveOrUpdateObject(message);
			String[] mb = mobileNumbers.split(",");
			for(String mobileNumber : mb)
			{
				/*String type = "SMS";
				String sender = "E-SHCOOL";*/
				MessageActivity msg = new MessageActivity();
				if (!ObjectFunctions.isNullOrEmpty(messages.getCustomer()))
					msg.setCustomerId(messages.getCustomer().getId());
					msg.setToAddress(mobileNumber);
					msg.setFromAddress(messages.getSenderName());
					if(!StringFunctions.isNullOrEmpty(messages.getCustomer().getSender())){
						msg.setSender(messages.getCustomer().getSender());
				    }else{
				       	msg.setSender("E-SCHOOL");
				    }
					msg.setType("SMS");
					msg.setDelivered(delivered);
					msg.setPurpose(messages.getPurposeType());
					msg.setMessageId(messages.getId());
					adminManager.save(msg);
				msg = null;
			}
		}catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
            JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
        }
     }
	
	
	
	public void searchStudent(String criteria){
		try {
			//prepareAcademicYearId();
			setStudentsList(adminManager.getStudentsPayentByRollNumber(criteria,getUserCustId(),getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void searchForFeePaidInvoiceStudent(String criteria){
		try {
			setStudentsList(adminManager.getStudentsPayentByRollNumber(criteria,getUserCustId(),getUsrChgedAcademicId()));
			prepareStudentsFeeListBysearch(getStudentsList(),getUsrChgedAcademicId());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	/*
	  * Remove prepareAcademicYearId and use getUserAcademicYearId by venkatesh - 04-25-2013
	  */
	public String getStudentsAttendanceList() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'getStudentsAttendanceList' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getStudyClass())) {
				Date aDate=null;
				setClassId(getStudyClass().getStrId());
				if(StringFunctions.isNullOrEmpty(getAttendanceDate()))
				{
					setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.YYYY_MM_DD_PATTERN));
				}
				String splitDate[] = getAttendanceDate().split("-");
				if(splitDate.length<=1)
				{
					aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getAttendanceDate()));
					setAttendanceDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, aDate));
				}
				aDate=DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, getAttendanceDate());
				if (!ObjectFunctions.isNullOrEmpty(getStartDate()) && !ObjectFunctions.isNullOrEmpty(getEndDate()))
				if(DateFunctions.compare2Dates(aDate,getStartDate()) && DateFunctions.compare2Dates(getEndDate(),aDate)){
					List<ViewStudentPersonAccountDetails> studentsDetailsList = staffManager.getViewStudentPersonAccountDetailsByStudyClassIdandStatus(getStudyClass().getId(), "Y", String.valueOf(getUserAcademicYearId()));
					if(ObjectFunctions.isNotNullOrEmpty(studentsDetailsList)){
						Collections.sort(studentsDetailsList);
						List studentsList = staffManager.getStudentDailyAttendance(getUserCustId(), getStudyClass().getId(),getAttendanceDate());
						if(ObjectFunctions.isNotNullOrEmpty(studentsList))
						{
							if(studentsList.size() > 0){ 
								setAttendanceType("updateAttendance");
							}
						}
						else
						{
							if(!ObjectFunctions.isNullOrEmpty(studentsDetailsList))
							{
								setAttendanceType("addAttendance");
								
							}
						}
						generateStudentDailyAttendanceList(studentsDetailsList);
					}
				}
				else{
			 		super.addActionError("You cann't submit attendence before academic year start date and after end date.");
		 			return "todayHoliday";
			 	}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/*
	  * Remove prepareAcademicYearId and use getUserAcademicYearId by venkatesh - 04-25-2013
	  */
	public String doInitAttendanceData() throws URTUniversalException {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doInitAttendanceData' method");
		}
		try {
			// If Admin get all the classes for his/her school
			if ("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole())|| "ROLE_HOD".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole())) {
				setClassNameList(adminManager.GetAllStudyClasses(getUserCustId(),getAcademicYearId(),null));
				if (!StringFunctions.isNullOrEmpty(getClassId())) {
					setStudyClass((StudyClass)adminManager.get(StudyClass.class, Long.valueOf(getClassId())));
				}
				checkStudyClassHavingStudentsOrNot();
				setObjectList(getTempList2());
			} 
			else if("ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole())|| "ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole()))
			{
				ajaxGetClassTeacherClasses();
			}
			if(ObjectFunctions.isNullOrEmpty(getStudyClass()))
			{
				setStudyClass((StudyClass)getClassNameList().get(0));
				setClassId(getStudyClass().getStrId());
			}
			if(!"todayHoliday".equalsIgnoreCase(getCustomerName()))
			{
					getStudentsAttendanceList();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
		
	public String ajaxDoGetUserPorfileDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetUserPorfileDetails' method");
		}
		try {
			setObjectList(adminManager.getAllByCustIdAndStatus("ViewAllUsers", getUserCustId(),"N",0));
			if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
				Collections.sort(getObjectList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

    @Actions({ @Action(value = "ajaxDeleteMessage", results = { @Result(location = "messages/doGetSendMessagesList.jsp", name = "success") }) })
    public String ajaxDeleteMessage() throws URTUniversalException {

	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDeleteMessage' method");
	}
	try {

	    String messageId = getParamValue("id");
	    long replyScrapId = 0;
	    if (!StringFunctions.isNullOrEmpty(messageId)) {
		List<ReplyScrapMessage> replyMsgList = staffManager.getAllReplyMessagesListByScrapId(
			Long.valueOf(messageId), getUserCustId());
		if (!ObjectFunctions.isNullOrEmpty(replyMsgList)) {
		    for (ReplyScrapMessage replyScrapMessage : replyMsgList) {
			if (!ObjectFunctions.isNullOrEmpty(replyScrapMessage)) {
			    replyScrapId = replyScrapMessage.getId();
			    replyScrapMessage.setAcademicYear(null);
			    replyScrapMessage.setReceiverAccount(null);
			    replyScrapMessage.setSenderAccount(null);
			    adminManager.remove(ReplyScrapMessage.class, replyScrapId);
			}
		    }
		}
		adminManager.remove(ScrapMessage.class, Long.valueOf(messageId));
	    }

	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	ajaxGetSentMessages();
	return SUCCESS;
    }
    
	/**
	 * Added by Prasad for student attendance modifications
	 * */
	
	public void generateStudentDailyAttendanceList(List<ViewStudentPersonAccountDetails> studentsDetailsList){
		StudentDailyAttendance attendance = null;
		if (ObjectFunctions.isNullOrEmpty(getStudentsList()))
			setStudentsList(new ArrayList<ViewStudentPersonAccountDetails>());
		for(ViewStudentPersonAccountDetails studentDetails : studentsDetailsList )
		{
			attendance = studentManager.getStudentDailyAttendance(studentDetails.getStudentId(),getAttendanceDate(),studentDetails.getCustId(),studentDetails.getAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(attendance))
			{
				if(Constants.ACTIVE_STATUS.equalsIgnoreCase(String.valueOf(attendance.getLeaveRequest())))
				{
					studentDetails.setLeaveRequest('A');
				}
				else if(!(attendance.isPresent()))
				{
					studentDetails.setPresent(Constants.STATUS_FALSE);
				}
				
			}
			else
			{
				studentDetails.setPresent(Constants.STATUS_TRUE);	
			} 
			getStudentsList().add(studentDetails);
		 	attendance=null;
		}
	} 
	
	 @Actions({
			@Action(value = "ajaxDoClassSchoolWideMessages", results = { @Result(location = "messages/sendClassWideMessage.jsp", name = "success" )})
			})
			public String ajaxDoClassWideMessages() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoClassWideMessages' method");
			}
			try
			{
			    if (!getUser().isSchoolTeacher() && !getUser().isSchoolAsstStaff()) 
				{
			    	checkStudyClassHavingStudentsOrNot();
				    setTempList(adminManager.getAll(Student.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and description is null and PTAStatus='Y'"));
				}
			    else if (getUser().isSchoolTeacher() || getUser().isSchoolAsstStaff()) 
			    {
					ajaxGetClassTeacherClasses();
				}
	   		}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Jan 20, 2013    	 Babu		        Order by first name.
/********************************************************************/	
	@Actions( { @Action(value = "ajaxGetStudentListForm", results = { @Result(location = "messages/classStudentsList.jsp", name = "success") }) })
	public String ajaxGetStudentListForm() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentListForm' method");
		}
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("classSectionId=" + Long.valueOf(getClassId())+ " and custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and accountExpired='"+ Constants.NO_STRING + "' ");
			if(!StringFunctions.isNullOrEmpty(getWishTitle())){
				queryString.append(" and hostelMode='"+getWishTitle()+"' "); //here wishtitle is a DaysScholar or Hostler studnets.
			}
			if (getUser().isSchoolHostel() || getUser().isHostelFinance()) {
				queryString.append(" and bedId !=0");
			}
			queryString.append(" and description is null order by firstname ");
			setStudentsList(adminManager.getAll(ViewStudentClassDetails.class, queryString.toString()));
			setSelectedId(getSelectedId()); //selectedId used when login class teacher set value in class wise sms 
			ajaxDoClassWideMessages();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	 
		@Actions( { @Action(value = "ajaxSendClassWideMessages", results = { @Result(location = "messages/sendSchoolWideMessagesList.jsp", name = "success") }) })
		public String sendSmsToStudent() throws URTUniversalException {
	
			if (log.isDebugEnabled()) {
				log.debug("Entering 'sendSmsToStudent' method");
			}
	
			try {
				String trStatus = getParamValue("trStatus");
				//Customer customer;
				//String senderId = "";
				//customer = (Customer) adminManager.get(Customer.class,getUserCustId());
				Customer customer = getCustomerByCustId();
				List studentsList=null;
				List<StudyClass> studyClassList = null;
				if(StringFunctions.isNotNullOrEmpty(getSelectedId())){
					studyClassList = adminManager.getAll(StudyClass.class, " id in"+getSelectedId());
				}
				if (!ObjectFunctions.isNullOrEmpty(customer) && customer.isCheckMobileService()) {
					SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
					if (!ObjectFunctions.isNullOrEmpty(getMessages())) {
						//setAcademicYear(adminManager.getCurrentAcademicYear("Y",getUserCustId()));
						if (!ObjectFunctions.isNullOrEmpty(getUser().getPerson())) {
							getMessages().setCreatedById(getUser().getId());
							getMessages().setLastUpdatedById(getUser().getId());
						}
						if (!ObjectFunctions.isNullOrEmpty(customer)) {
							getMessages().setCustomer(customer);
						}
						getMessages().setAcademicYear(getCurrentAcademicYear());
						getMessages().setSenderName(getUser().getUserRoleDescription());
						if("TR".equals(trStatus)){
							getMessages().setStatus("TR");
							getMessages().setPurposeType("Transport");
						}else{
							getMessages().setStatus("M");
							getMessages().setPurposeType(getMessages().getTitle());
						} 
						getMessages().setMessageDescription("Dear Parent, Your son/daughter "+getMessages().getMessageDescription()+" . Thank you Principal from ");
						//Dear Parent, Your son/daughter <v1> . Thank you Principal from <v2>.
						if (!ObjectFunctions.isNullOrEmpty(getChkBoxSelectedIds())) {
							for (String accountId : getChkBoxSelectedIds()) {
								if (!StringFunctions.isNullOrEmpty(accountId)) {
									User user = (User) adminManager.get(User.class,Long.valueOf(accountId));
									if (!ObjectFunctions.isNullOrEmpty(user.getStudentParent().getId())&& !ObjectFunctions.isNullOrEmpty(user.getPerson().getMobileNumber())) {
										getMobileNumbersSet().add(user.getPerson().getMobileNumber());
									} else {
										getMobileNumbersSet().add(user.getPerson().getMobileNumber());
									}
								}
							}
						}else{
							if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
								for(StudyClass studyClass : studyClassList){
									studentsList=adminManager.getAll("select mobileNumber from vw_studentClassDetails where classSectionId="+studyClass.getId()+" and academicYearId="+getAcademicYear().getId()+" and custId="+getUserCustId()+" and status='Y'");
									if(!ObjectFunctions.isNullOrEmpty(studyClassList)){
										getMobileNumbersSet().addAll(studentsList);
										getMobileNumbersSet().size();
										studentsList= null;
									}
								}
							}
						}
						if (!ObjectFunctions.isNullOrEmpty(getMobileNumbersSet())) {
							boolean status = communicationManager.deliverSms(getMessages(),getMobileNumbersSet(),smsServiceProvider);
								if(status){
									super.addActionMessage("Sms has been delivered successfully.");
								}else{
									super.addActionError("Sms has not been delivered.");
								}
								setMobileNumbersSet(null);
						}
					}
				} else {
					super.addActionError("SMS service disabled, enable service.");
				}
				//ajaxDoClassWideMessages();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			ajaxDoGetSchoolWideMessagesList();
			return SUCCESS;
		}
		public String prepareMobileNumbersString(Set<String> mobileNumSet) {
			try {
	               String commaDelimitedString = org.springframework.util.StringUtils.collectionToCommaDelimitedString(mobileNumSet);
	               log.debug("commaDelimitedString-----"+commaDelimitedString);
	 
	               if(StringFunctions.isNotNullOrEmpty(commaDelimitedString)){
	            	   return commaDelimitedString;
	               }
			} catch (Exception ex) {
				log.debug("error : " + ex.getMessage());
				 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
		}
		public boolean validateMobileNumber(String mobileNum) {
			try {
				mobileNum = StringFunctions.stripSymbols(mobileNum, "+()-#$@,'`?& ");
				if (mobileNum.length() < 10 || mobileNum.length() > 12 || mobileNum.length() > 10 && mobileNum.length() < 12) {
					log.debug("Invalid mobile Number " + mobileNum);
					return false;
				} else
					return true;
	
			} catch (Exception ex) {
				log.debug(ex.getMessage());
				 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return false;
		}

	public void generateStudentMarksSheetByStudyClassAndSubjects(List<ViewStudentClassDetails> classStudentsList,String className,List<ExamSchedules> examScheduleDetails,ExcelView excelView,long examTypeId,String examTypeName,WritableCellFormat cellFormat8,WritableCellFormat cellFormat10){
		try{	
			if (getUserCustId() != 2)
			{
				Collections.sort(classStudentsList);
			}
			excelView.setWorkSheetName(className);
	    	excelView.createWorkSheet(0);
	    	excelView.setDefaultFormat(excelView.getArial10format());
	    	int size=examScheduleDetails.size()+2;
	    	prepareCustomerHeaderExacel(excelView,className,cellFormat8,cellFormat10,size);
			excelView.getWs().addCell(new Label(0,3, "Note :-\n1) You shouldn't change green colour fields.\n2) If any student is absent for particular subject add 'A' for that subject.", excelView.getUser14format()));
    		prepareMarksSheetSubHeaderLables(excelView,examTypeName,className,examScheduleDetails,classStudentsList,examTypeId);
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	@Actions( {
		 @Action(value = "ajaxStaffAndStudentUploadImage", results = { @Result(location = "ajaxStafAndStudentProfileImage.jsp", name = "success") })
		 })
	public String ajaxStaffUploadImage() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxStaffUploadImage' method");
		}
		try {
			long userId = getUser().getId();
			if (StringFunctions.isNotNullOrEmpty(String.valueOf(userId))) {
				setUser((User) staffManager .get(User.class, userId));
				//Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
				Customer customer = getCustomerByCustId();
				if (!ObjectFunctions.isNullOrEmpty(customer.getCustomerShortName())) {
					if (getUploadFileName() != null) {
						long profileImage = 0;
						if(!ObjectFunctions.isNullOrEmpty(getUser().getProfileImage()))
							profileImage = getUser().getProfileImage().getId();
						UserImage attachment = null;
						try {
							attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE, customer.getId(),getUserAcademicYearId(),profileImage);
							if (!ObjectFunctions.isNullOrEmpty(attachment)) {
								if(getUser().isSchoolStudent()){
									getUser().setProfileStuImage(attachment);
								}else{
									getUser().setProfileImage(attachment);
								}
								adminManager.save(getUser());
							}
							attachment = null;
						} catch (Throwable ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

 	@Actions( { @Action(value = "ajaxAllClassesStudentsAttendance", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "myStudents" }) }) })
 	public String generateChartForClassWiseStudentAttendance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxMyClassViewAttendence' method");
		}
		try {
			//prepareAcademicYearId();
			if(getUserAcademicYearId() > 0 && getTempId() > 0){
				 AcademicYear year=(AcademicYear)adminManager.get(AcademicYear.class, getUserAcademicYearId());
				 if(!ObjectFunctions.isNullOrEmpty(year)){
					 String[] months = {"","January", "February",
							  "March", "April", "May", "June", "July",
							  "August", "September", "October", "November",
							  "December"};
					 long presentStudents=0;
					 long absentStudents=0;
					 JSONObject ja = new JSONObject();
					 JSONArray cata = new JSONArray();
					 JSONArray presentArray = new JSONArray();
					 JSONArray absentArray = new JSONArray();
					 JSONArray series = new JSONArray();
					 JSONObject present = new JSONObject();
					 JSONObject absent = new JSONObject();
					 ja.put("title", getTempString()+" Class Student Attendance.");
					if(!ObjectFunctions.isNullOrEmpty(year.getStartDate()) && !ObjectFunctions.isNullOrEmpty(year.getEndDate())){
						List<SchoolHolidays> holidays = null;
						Date openDate = year.getStartDate();
						Date closeDate = year.getEndDate();
						Date todayDate=new Date();
						int currentMonth=DateFunctions.getMonthOfDate(todayDate);
						int currentYear = DateFunctions.getDayOfYear(new Date());
						if(DateFunctions.compare2Dates(todayDate,openDate) && DateFunctions.compare2Dates(closeDate,todayDate))
						{
							Calendar cal = Calendar.getInstance();
							cal.setTime(openDate);
							int monthId;
							int yearId;
							int monthTotalDays;
							int studentCount;
							for(Calendar startDate=cal;DateFunctions.compare2Dates(todayDate, startDate.getTime());){
								monthId=DateFunctions.getMonthOfDate(startDate.getTime());
								yearId = DateFunctions.getDayOfYear(startDate.getTime());
								if (monthId > 0) 
								{
									if (monthId == currentMonth) {
										monthTotalDays = DateFunctions.getDayOfMonth(new Date());
									} else {
										monthTotalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
									}
									 
								if (monthId == currentMonth && yearId == currentYear) {
									if (DateFunctions.compare2Dates(closeDate, new Date()))
										//holidays = adminManager.getAll(SchoolHolidays.class,"monthId="+ monthId+ " and academicYearId="+ getUserAcademicYearId() + " and custId="+ getUserCustId()+ " and holidayDate <= '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date())+ "'");
										holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()), null, null,null,null,null,monthId,"holidayBetweenDates",null);
									else
										//holidays = adminManager.getAll(SchoolHolidays.class,"monthId="+ monthId+ " and academicYearId="+getUserAcademicYearId() + " and custId="+ getUserCustId()+ " and holidayDate <= '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate)+ "'");
										holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate), null, null,null,null,null,monthId,"holidayBetweenDates",null);
								} else {
										//holidays = adminManager.getAll(SchoolHolidays.class,"monthId="+ monthId+ " and academicYearId="+ getUserAcademicYearId() + " and custId="+ getUserCustId()+ " and holidayDate <= '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate) + "'");
										holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate), null, null,null,null,null,monthId,"holidayBetweenDates",null);
								}

								if (!ObjectFunctions.isNullOrEmpty(holidays)) {
									monthTotalDays -= holidays.size();
								}
							
								
								float presentPercentage=0;
								float absentPercentage=0;
								ja.put("categories", presentArray);
								//presentStudents=staffManager.getStudentsAttendanceByClassSectionIdAndMonthId(getTempId(),monthId,getAcademicYearId(),getUserCustId(),Constants.YES_STRING);
								
								if(year.getManageAttendanceBy().equalsIgnoreCase("D"))
								{
									studentCount = adminManager.getCount("vw_studentDetails", "classSectionId="+getTempId()+" and academicYearId="+getUserAcademicYearId() +" and status='Y'");
									monthTotalDays = studentCount * monthTotalDays;
									absentStudents= staffManager.getStudentsAttendanceByClassSectionIdAndMonthId(Long.valueOf(getTempId()),monthId,getUserAcademicYearId(),getUserCustId(),Constants.NO_STRING);
									presentStudents = monthTotalDays - absentStudents;
								}
								else
								{
									monthTotalDays = 0;
									monthTotalDays = staffManager.getTotalWorkingDaysByClassSectionIdAndMonthId(Long.valueOf(getTempId()),monthId,getUserAcademicYearId(),getUserCustId(),0);
									presentStudents= staffManager.getStudentsMonthlyAttendanceByClassSectionIdAndMonthId(Long.valueOf(getTempId()),monthId,getUserAcademicYearId(),getUserCustId(),0);
									absentStudents = monthTotalDays - presentStudents;
								}
								
								if(presentStudents > 0 || absentStudents > 0){
									cata.put(months[monthId]);
									if(presentStudents > 0 && absentStudents > 0){
										presentPercentage=(float)(presentStudents) / (float)(presentStudents+absentStudents)*100;
										absentPercentage=(float)(absentStudents) / (float)(presentStudents+absentStudents)*100;
									}
									else if(presentStudents > 0)
										presentPercentage=(float)(presentStudents) / (float)(presentStudents)*100;
									else if(absentStudents > 0)
										absentPercentage=(float)(absentStudents) / (float)(absentStudents)*100;
									presentArray.put(roundTwoDecimals(presentPercentage));
									absentArray.put(roundTwoDecimals(absentPercentage));
								}
								absentStudents=0;
								presentStudents=0;
								startDate.add(Calendar.MONTH, 1);
								startDate.set(Calendar.DATE, 1);
							}
							}
							ja.put("categories", cata);
							present.put("type", "column");
							present.put("name", "Present");
							present.put("data", presentArray);
							series.put(present);
							absent.put("type", "column");
							absent.put("name", "Absentees");
							absent.put("data", absentArray);
							series.put(absent);
							ja.put("series",series);
						
					}
					}
					log.debug(ja.toString());
					getResponse().getOutputStream().print(ja.toString());
				 }
			}
		}
	 catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return null;
	}
	public double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
	 
	public String getBaseStaffAttendanceDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getBaseStaffAttendanceDetails' method");
		}
		try {
			if (getTempId() == 0) {
				String accountId = getParamValue("accountId");
				if(!StringFunctions.isNullOrEmpty(accountId))
				{
					setTempId(Long.valueOf(accountId));
				}
				else
				{
					setTempId(getUser().getId());
				}
			}
			if(getUserAcademicYearId() > 0 && getTempId() > 0){
				//String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};
				AcademicYear year=(AcademicYear)adminManager.get(AcademicYear.class, getUserAcademicYearId());
				if (!ObjectFunctions.isNullOrEmpty(year)) 
				{ if (!ObjectFunctions.isNullOrEmpty(year)) 
				   {
					   Date openDate = year.getStartDate();
						Date closeDate = year.getEndDate();
						Date todayDate=new Date();
						if(!ObjectFunctions.isNullOrEmpty(openDate) && !ObjectFunctions.isNullOrEmpty(closeDate)){
							int monthId;
							int yearId;
							int currentMonth=DateFunctions.getMonthOfDate(todayDate);
							int currentYear = DateFunctions.getDayOfYear(new Date());
							Calendar cal = Calendar.getInstance();
							cal.setTime(openDate);
							    double absentiesCount=0;
								double presentiesCount=0;
								double totalWorkingDays = 0;
					  if(year.getManageStaffAttendanceBy().equalsIgnoreCase("M") && StringFunctions.isNotNullOrEmpty(year.getManageStaffAttendanceBy()))
					   {
						   for (Calendar openDate1 = cal; DateFunctions.compare2Dates(closeDate, openDate1.getTime());) {
							monthId = DateFunctions.getMonthOfDate(openDate1.getTime());
							yearId = DateFunctions.getDayOfYear(openDate1.getTime());
							StaffMonthlyAttendance staffMonthlyAttendance = (StaffMonthlyAttendance) adminManager.get(StaffMonthlyAttendance.class, "accountId='"+getTempId()+"' and month='" + monthId+"' and academicYearId="+ getUserAcademicYearId());
							ExamSchedules examSchedules = new ExamSchedules();
							if (!ObjectFunctions.isNullOrEmpty(staffMonthlyAttendance)) 
									{
										totalWorkingDays =  Double.parseDouble(staffMonthlyAttendance.getTotalWorkingDays());
										presentiesCount= Double.parseDouble(staffMonthlyAttendance.getNoOfPresentDays());
										absentiesCount =totalWorkingDays -presentiesCount;
								        examSchedules.setMonthName(DateFunctions.getMonthForInt(monthId));
										examSchedules.setTotalWorkingDays((int)totalWorkingDays);
										examSchedules.setTotalPresentDays((int)presentiesCount);
										examSchedules.setTotalAbsentDays((int) absentiesCount);
										double percentage = 0;
										if(presentiesCount > 0)
										{
											percentage = roundTwoDecimals((presentiesCount / totalWorkingDays) * 100);	
										} 
										examSchedules.setAttendancePercentage(percentage);
								        getObjectList().add(examSchedules);
									} 
						  		else
						  		{
						  			if(monthId <= currentMonth && yearId == currentYear  || monthId > currentMonth && yearId != currentYear)
						  			{
							  			totalWorkingDays = 0;
										presentiesCount = 0;
								        absentiesCount =0;		
								        examSchedules.setMonthName(DateFunctions.getMonthForInt(monthId));
										examSchedules.setTotalWorkingDays((int)totalWorkingDays);
										examSchedules.setTotalPresentDays((int)presentiesCount);
										examSchedules.setTotalAbsentDays((int) absentiesCount);
										double percentage = 0;
										if(presentiesCount > 0)
										{
											percentage = roundTwoDecimals((presentiesCount / totalWorkingDays) * 100);	
										} 										
										examSchedules.setAttendancePercentage(percentage);
										getObjectList().add(examSchedules);
							  		}
						  		}
						  		openDate1.add(Calendar.MONTH, 1);
								openDate1.set(Calendar.DATE, 1);
					   }
					   }
					   else
					   {
							Map<String,Integer> monthWiseWorkingDays =null;
							if(DateFunctions.compare2Dates(todayDate,openDate) && DateFunctions.compare2Dates(closeDate,todayDate))
							{
								monthWiseWorkingDays = staffManager.getMonthwiseSchoolWorkingDays(getUserCustId(),year.getId(),openDate,todayDate,true,"", getClassId()); //here getClassId used to academicyear settings have class wise holiday(CH).
							}else if(todayDate.after(closeDate)){
								monthWiseWorkingDays = staffManager.getMonthwiseSchoolWorkingDays(getUserCustId(),year.getId(),openDate,closeDate,true,"", getClassId());
							}
							if(!ObjectFunctions.isNullOrEmpty(monthWiseWorkingDays)){
								Iterator it = monthWiseWorkingDays.entrySet().iterator();
							    while (it.hasNext()) {
							        Map.Entry pairs = (Map.Entry)it.next();
							        totalWorkingDays = Integer.valueOf(pairs.getValue().toString());
							        if(year.getUseBiometricForStaff().equalsIgnoreCase("N"))
							        {
							        	absentiesCount = adminManager.getCount("vw_StaffDailyAttendance", "accountId="+getTempId()+"  and monthName='"+pairs.getKey()+"' and present= 'N' and academicYearId="+getUserAcademicYearId()+" and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, todayDate)+"'");
							        	 presentiesCount =totalWorkingDays - absentiesCount;
							        }
							        else
							        {
							        	presentiesCount = adminManager.getCount("vw_StaffDailyAttendance", "accountId="+getTempId()+"  and monthName='"+pairs.getKey()+"' and present= 'Y' and academicYearId="+getUserAcademicYearId()+" and attendanceDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, todayDate)+"'");
							        	absentiesCount =totalWorkingDays - presentiesCount;
							        }
							       
							        ExamSchedules examSchedules = new ExamSchedules();
									examSchedules.setMonthName((String)pairs.getKey());
									examSchedules.setTotalWorkingDays((int)totalWorkingDays);
									examSchedules.setTotalPresentDays((int)presentiesCount);
									examSchedules.setTotalAbsentDays((int) absentiesCount);
									double percentage = 0;
									if(presentiesCount > 0)
									{
										percentage = roundTwoDecimals((presentiesCount / totalWorkingDays) * 100);
									}  
									examSchedules.setAttendancePercentage(percentage);
									getObjectList().add(examSchedules);
							    }
						}
						year = null;
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
	@Actions( { @Action(value = "ajaxGetSenderMessages", results = { @Result(location = "messages/doGetSendMessagesList.jsp", name = "success") }) })
	public String ajaxGetSentMessages() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSenderMessages' method");
		}
		try {
			//prepareAcademicYearId();
			setScrapMessagesList(staffManager.getAllScrapMessagesListBySenderAccId(getUser().getId(),Constants.ACTIVE_STATUS, null, getUserCustId(),String.valueOf(getUserAcademicYearId())));
			if(!ObjectFunctions.isNullOrEmpty(getScrapMessagesList()))
			   Collections.sort(getScrapMessagesList());

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	//messages code
	 @Actions({
			@Action(value = "ajaxDoInboxGetScrapMessagesList", results = { @Result(location = "messages/doGetScrapMessagesList.jsp", name = "success") }),
			@Action(value = "doGetSMS", results = { @Result(location = "messages/smsHome.jsp", name = "success") }),
			@Action(value = "doGetMyInbox", results = { @Result(location = "messages/myInboxHome.jsp", name = "success" )})
		})//last page --doGetScrapMessagesList.jsp
	 public String ajaxDoGetScrapMessagesList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetScrapMessagesList' method");
		}
		try
		{
			if(!ObjectFunctions.isNullOrEmpty(getUser()))
			{
				setAnyId(String.valueOf(getUser().getId()));
				setScrapMessagesList(staffManager.getAllScrapMessagesListByReceiverAccId(getUser().getId(),Constants.ACTIVE_STATUS,"",getUserCustId(),String.valueOf(getUserAcademicYearId()),"G"));
				setTempList(staffManager.getAllScrapMessagesListByReceiverAccId(getUser().getId(),Constants.ACTIVE_STATUS,"",getUserCustId(),String.valueOf(getUserAcademicYearId()),"C"));
				setTempList1(staffManager.getAllScrapMessagesListByReceiverAccId(getUser().getId(),Constants.ACTIVE_STATUS,"",getUserCustId(),String.valueOf(getUserAcademicYearId()),"S"));
				if(!ObjectFunctions.isNullOrEmpty(getScrapMessagesList()))
					Collections.sort(getScrapMessagesList());
			}
			
			setSmsCnt(staffManager.getTotalSmsCount(getUserCustId(),getUserAcademicYearId() ));
			setX(staffManager.getTransportSmsCount(getUserCustId(),getUserAcademicYearId()));
			setCustomer((Customer)adminManager.get(Customer.class, getUserCustId()));
			getIsClassTeacherOrNot();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		//ajaxStaffAndStudentBirthDays();
		return SUCCESS;
	}
	 @Actions({
		 @Action(value = "ajaxSendScrapMessage", results = { @Result(location = "messages/doGetScrapMessagesList.jsp", name = "success" )})
	 })
	public String ajaxSendScrapMessage() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendScrapMessage' method");
		}
		try
		{
			//String senderAccountId = getParamValue("fullPersonName");

			String senderAccoutIds=getParamValue("accountIds");
			ScrapMessage scrapMessage=null;
			AcademicYear academicYear=getCurrentAcademicYear();
			StringBuffer res = new StringBuffer(senderAccoutIds);	
			if (!ObjectFunctions.isNullOrEmpty(res)) {
				ScrapMessage scrapMessageObj = null;
				String[] accountIds = res.toString().split(",");
				if (accountIds.length > 0) {
					for (String senderAccountId : accountIds) {
						if (StringFunctions.isNotNullOrEmpty(senderAccountId)) {	
									ReplyScrapMessage replyScrapMessage = new ReplyScrapMessage();
									replyScrapMessage.setTitle(getScrapMessage().getTitle());
									replyScrapMessage.setScrapDescription(getScrapMessage().getScrapDescription());
									replyScrapMessage.setSenderAccount(getUser());
									replyScrapMessage.setReceiverAccount((User)staffManager.get(User.class, Long.valueOf(senderAccountId)));
									replyScrapMessage.setStatus(Constants.ACTIVE_STATUS);
									replyScrapMessage.setMessageType(getScrapMessage().getMessageType());
									User luser1 =(User)staffManager.get(User.class, getUser().getId());
									replyScrapMessage.setSenderAccount(luser1);
									replyScrapMessage.setCreatedDate(new Date());
									replyScrapMessage.setLastUpdatedDate(new Date());
									replyScrapMessage.setLastAccessDate(new Date());
									replyScrapMessage.setCustId(getUserCustId());
									replyScrapMessage.setAcademicYear(academicYear);
									scrapMessageObj=new  ScrapMessage();
									scrapMessageObj.setSenderAccount(luser1);
									scrapMessageObj.setReceiverAccount((User)staffManager.get(User.class, Long.valueOf(senderAccountId)));
									scrapMessageObj.setStatus(Constants.ACTIVE_STATUS);
									scrapMessageObj.setMessageType(getScrapMessage().getMessageType());
									scrapMessageObj.setCustId(getUserCustId());
									scrapMessageObj.setLastUpdatedById(getUser().getId());
									scrapMessageObj.setAcademicYear(academicYear);
									scrapMessageObj.setTitle(getScrapMessage().getTitle());
									scrapMessageObj.setScrapDescription(getScrapMessage().getScrapDescription());
									scrapMessageObj.addReplayScrapMessage(replyScrapMessage);
									scrapMessage = staffManager.saveScrapMessage(scrapMessageObj);
									
									List<ScrapMessageVO> scrapMessageVOs = new ArrayList<ScrapMessageVO>();
									scrapMessageVOs.add(scrapMessage.copyFromEntityToVo(scrapMessage));
									
									List<ReplyScrapMessageVO> replyScrapMessageVOs = new ArrayList<ReplyScrapMessageVO>();
									replyScrapMessage = scrapMessage.getReplayScrap().get(scrapMessage.getReplayScrap().size() - 1);
									
									ReplyScrapMessageVO  replyScrapMessageVo = replyScrapMessage.copyFromEntityToVo(replyScrapMessage);
									replyScrapMessageVo.setScrapMesssageId(scrapMessage.getId());
									replyScrapMessageVOs.add(replyScrapMessageVo);
									
									JSONObject main = new JSONObject();
									JSONObject subVal = new JSONObject();
									main.put("notificationFor", "Messages");
									subVal.put("title", getUser().getFullPersonName()+" sent a message");
									subVal.put("description", getUser().getFullPersonName()+" sent a message");
									//subVal.put("scrapMessageVOs",scrapMessageVOs);
									subVal.put("scrapMessageVOs",new JSONArray(new Gson().toJson(scrapMessageVOs)));
									//subVal.put("replyScrapMessageVOs",replyScrapMessageVOs);
									subVal.put("replyScrapMessageVOs",new JSONArray(new Gson().toJson(replyScrapMessageVOs)));
									main.put("information", subVal);
									log.debug(main.toString());
									adminManager.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+senderAccountId+",0)"); //To add notification for mobile app.
									
									replyScrapMessageVo = null;
									replyScrapMessage=null;
									luser1=null;
							//	}
						//	}
						}
						if(!ObjectFunctions.isNullOrEmpty(scrapMessage))
						{
							setScrapMessage(scrapMessage);
							List replayScrapMessageSet = scrapMessage.getReplayScrap();
							if(!ObjectFunctions.isNullOrEmpty(replayScrapMessageSet))
							{
								Collections.sort(replayScrapMessageSet);
								getReplyScrapMessageSet().add(ConvertUtil.convertListToSet(replayScrapMessageSet));
							}
						}
						ViewAllUsers viewAllUsers =(ViewAllUsers) staffManager.get(ViewAllUsers.class, Long.valueOf(senderAccountId),"accountId");
						if(!ObjectFunctions.isNullOrEmpty(viewAllUsers))
						{
							setViewAllUsers(viewAllUsers);
						}
						
					}
					super.addActionMessage("Message posted Successfully");
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxDoGetScrapMessagesList();
		return SUCCESS;
	}
	 @Actions({
			@Action(value = "ajaxDoReplayScrapMessages", results = { @Result(location = "messages/replyScrapMessageHome.jsp", name = "success") })
		})
	public String ajaxDoReplayScrapMessages() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoReplayScrapMessages' method");
		}
		try
		{
			String scrapMessageId = getParamValue("id");
			ViewAllUsers viewAllUsers=null;
			ScrapMessage scrapMessageInfo = (ScrapMessage)staffManager.get(ScrapMessage.class, Long.valueOf(scrapMessageId));
			if(!ObjectFunctions.isNullOrEmpty(scrapMessageInfo))
			{
				if("UR".equalsIgnoreCase(scrapMessageInfo.getIsNewMessage()))
				{
					scrapMessageInfo.setIsNewMessage("R");
					ScrapMessage newScrapMessage = staffManager.saveScrapMessage(scrapMessageInfo);
					setScrapMessage(newScrapMessage);
					List replayScrapMessageSet = newScrapMessage.getReplayScrap();
					if(!ObjectFunctions.isNullOrEmpty(replayScrapMessageSet))
					{
						Collections.sort(replayScrapMessageSet);
						setReplyScrapMessageSet(ConvertUtil.convertListToSet(replayScrapMessageSet));
					}
				}
				else
					if("RM".equalsIgnoreCase(scrapMessageInfo.getIsNewReply()))
					{
						scrapMessageInfo.setIsNewReply("D");
						if(ObjectFunctions.isNullOrEmpty(getUser().getId())){
							scrapMessageInfo.setScrapDescription(getReplyScrapMessage().getScrapDescription());
						}
						ScrapMessage newScrapMessage = staffManager.saveScrapMessage(scrapMessageInfo);
						setScrapMessage(newScrapMessage);
						List replayScrapMessageSet = newScrapMessage.getReplayScrap();
						if(!ObjectFunctions.isNullOrEmpty(replayScrapMessageSet))
						{
							Collections.sort(replayScrapMessageSet);
							setReplyScrapMessageSet(ConvertUtil.convertListToSet(replayScrapMessageSet));
						}
					}
				else
				{
					setScrapMessage(scrapMessageInfo);
					List replayScrapMessageSet = scrapMessageInfo.getReplayScrap();
					if(!ObjectFunctions.isNullOrEmpty(replayScrapMessageSet))
					{
						Collections.sort(replayScrapMessageSet);
						setReplyScrapMessageSet(ConvertUtil.convertListToSet(replayScrapMessageSet));
					}
				}
				if(getUser().getId() == scrapMessageInfo.getReceiverAccount().getId())
				{
					viewAllUsers =(ViewAllUsers) staffManager.get(ViewAllUsers.class, scrapMessageInfo.getSenderAccount().getId(), "accountId");
				}
				else
				{
					viewAllUsers =(ViewAllUsers) staffManager.get(ViewAllUsers.class, scrapMessageInfo.getReceiverAccount().getId(),"accountId");
				}
				if(!ObjectFunctions.isNullOrEmpty(viewAllUsers))
				{
					setViewAllUsers(viewAllUsers);
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
		@Action(value = "ajaxReplyNewScrapMessage", results = { @Result(location = "messages/replyScrapMessagesList.jsp", name = "success" )})
		})
	public String ajaxReplyNewScrapMessage() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxReplyNewScrapMessage' method");
		}

		try
		{
			if (!StringFunctions.isNullOrEmpty(getParamValue("scrapMessage.id")) && !StringFunctions.isNullOrEmpty(getParamValue("scrapMessage.senderAccount.id")) && !StringFunctions.isNullOrEmpty( getParamValue("scrapMessage.receiverAccount.id"))) {
				long scrapSenderAccountId = Long.valueOf(getParamValue("scrapMessage.senderAccount.id"));
				long scrapReceiverAccountId = Long.valueOf(getParamValue("scrapMessage.receiverAccount.id"));
				long scrapMessageId = Long.valueOf(getParamValue("scrapMessage.id"));
				AcademicYear academicYear=getCurrentAcademicYear();
				addActionMessages(staffManager.UpdateReplyNewScrapMessage(getUserCustId(),academicYear,scrapSenderAccountId, scrapReceiverAccountId, getReplyScrapMessage().getScrapDescription(), scrapMessageId,null,getUser().getId()));
				ScrapMessage newScrapMessage = (ScrapMessage)staffManager.get(ScrapMessage.class, Long.valueOf(scrapMessageId));
				if(!ObjectFunctions.isNullOrEmpty(newScrapMessage))
				{
					setScrapMessage(newScrapMessage);
					List replayScrapMessageSet = newScrapMessage.getReplayScrap();
					if(!ObjectFunctions.isNullOrEmpty(replayScrapMessageSet))
					{
						Collections.sort(replayScrapMessageSet);
						setReplyScrapMessageSet(ConvertUtil.convertListToSet(replayScrapMessageSet));
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxDoGetScrapMessagesList();
		return SUCCESS;
	}
	
	public void loadAcademicYearStartDateAndDates(long academicYearId){
		AcademicYear academicYear=(AcademicYear)adminManager.get(AcademicYear.class,academicYearId);
		//AcademicYear academicYear=getCurrentAcademicYear();
		if(!ObjectFunctions.isNullOrEmpty(academicYear)){
			setAcademicYearId(academicYear.getId());
			long pastYear=academicYear.getPastYear();
			long future=pastYear+1;
			setAcdmcYearRange(pastYear+":"+future);
			 if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate())){
				 setStartDate(DateFormatter.parseString(DateFormatter.MM_D_YY_PATTERN, DateUtil.getDateTime(DateFormatter.MM_D_YY_PATTERN,academicYear.getStartDate())));
				 setFromDate(DateFormatter.parseString(DateFormatter.MM_D_YY_PATTERN, DateUtil.getDateTime(DateFormatter.MM_D_YY_PATTERN,DateFunctions.add(academicYear.getStartDate(), 1))));
			 }
			 if(!ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
				 setEndDate(DateFormatter.parseString(DateFormatter.MM_D_YY_PATTERN, DateUtil.getDateTime(DateFormatter.MM_D_YY_PATTERN,academicYear.getEndDate())));
				 setAnyTitle(DateUtil.getDateTime(DateFormatter.MMDDYY_PATTERN,DateFunctions.add(academicYear.getEndDate(), 2)));
			 }
			 setToDate(new Date());
			 setAcademicYear(academicYear);
		}
	}
	/*
	* Removed prepareAcademicYearId and use getUserAcademicYearId and unnecessary code is done by venkatesh - 04-29-2013
	*/
	public void staffLeavesManagement(long id){
		try {
			List<Object[]> absetntList=null;
			if(getUserAcademicYearId() > 0){
				double casualLeaveDays = 0;
				double sickLeaveDays = 0;
				double earnedLeaveDays = 0;
				setTempBoolean(false);
				double paidLeavesCount = 0;
					if(!ObjectFunctions.isNullOrEmpty(getLeaveManagement())){
						//List<VWStaffAttendance> absetntList=staffManager.getStaffAttendanceByPresentStatusAndLeaveRequestStaus(getUserCustId(),getUserAcademicYearId(), id, Constants.NO_STRING," ");
						absetntList = adminManager.getAll("select attendanceDate,leaveRequest,present,leaveType from vw_StaffDailyAttendance where custId="+getUserCustId()+" and accountId ="+id+" and academicYearId="+getUserAcademicYearId()+" and present='"+Constants.NO_STRING+"' group by accountId,attendanceDate,leaveRequest,present,leaveType order by attendanceDate DESC");
						if (!ObjectFunctions.isNullOrEmpty(absetntList)) {
							int casualLeavesDuringHolidays=0;
							int earnedLeavesDuringHolidays=0;
							int paidLeavesDuringHolidays=0;
							int sickLeavesDuringHolidays=0;
							StaffDailyAttendance attendance=null;
							Date currentDate=DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							for (Object[] object : absetntList) {
								if(currentDate.compareTo(formatter.parse(object[0].toString())) == 0){
									if(!ObjectFunctions.isNullOrEmpty(object[1])){
										if("A".equalsIgnoreCase(object[1].toString()) )
											 setTempChar('A');
										else if("Y".equalsIgnoreCase(object[2].toString())) {
											setTempChar('L');
										}else{
											setTempChar('P');
										}
									}
								}
								if(!ObjectFunctions.isNullOrEmpty(object[3])){
									if("SL".equalsIgnoreCase(object[3].toString())){
										if(sickLeaveDays >= getLeaveManagement().getSickLeaves()){
												paidLeavesCount+= 1;
										}else
											sickLeaveDays += 1;
									}
									else if("PL".equalsIgnoreCase(object[3].toString())){
											paidLeavesCount+= 1;
									}else if("EL".equalsIgnoreCase(object[3].toString())){
										if(earnedLeaveDays >= getLeaveManagement().getEarnedLeaves()){
											paidLeavesCount+= 1;
										}else
											earnedLeaveDays += 1;
								   }else {
										if(casualLeaveDays >= getLeaveManagement().getCasualLeaves()){
											paidLeavesCount+= 1;
										}else
											casualLeaveDays += 1;
									}
								}
								//Checking whether user present or absent on before and after absent day
								Calendar holidayDate=Calendar.getInstance();
								holidayDate.setTime(formatter.parse(object[0].toString()));
								holidayDate.add(Calendar.DATE, -1);
								for(Calendar cal=holidayDate;;cal.add(Calendar.DATE, -1))
								{
									String newholidayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, cal.getTime());
									if(!getObjectList().contains(newholidayDate))
									{
										attendance=adminManager.getStaffAttendanceByAttendanceDateAndAccountIdAndCustId(getUserCustId(),id,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, cal.getTime()));
										if(ObjectFunctions.isNullOrEmpty(attendance) || attendance.isPresent())
										{
											casualLeavesDuringHolidays=0;
											paidLeavesDuringHolidays=0;
											break;
										}
										else
										{
											casualLeaveDays+=casualLeavesDuringHolidays;
											sickLeaveDays+=sickLeavesDuringHolidays;
											earnedLeaveDays+=earnedLeavesDuringHolidays;
											paidLeavesCount+=paidLeavesDuringHolidays;
											casualLeavesDuringHolidays=0;
											paidLeavesDuringHolidays=0;
											sickLeavesDuringHolidays=0;
											earnedLeavesDuringHolidays=0;
											break;
										}
									}else
									{
										if(!ObjectFunctions.isNullOrEmpty(object[3])){
											if("SL".equalsIgnoreCase(object[3].toString())){
												if(sickLeaveDays >= getLeaveManagement().getSickLeaves())
													paidLeavesCount+=1;
												else
													sickLeaveDays+=1;
											}else if("EL".equalsIgnoreCase(object[3].toString())){
												if(earnedLeaveDays >= getLeaveManagement().getEarnedLeaves()){
													paidLeavesCount+= 1;
												}else
													earnedLeaveDays += 1;
										   }else if("PL".equalsIgnoreCase(object[3].toString())){
												paidLeavesCount+=1;
											}else if(casualLeaveDays >= getLeaveManagement().getCasualLeaves()){
												paidLeavesCount+=1;
											}else
												casualLeaveDays+=1;
										}
									}
								}
								object=null;
							}
						}
						else
						{
							setEventId(String.valueOf(getLeaveManagement().getCasualLeaves()));
							setBedId(String.valueOf(getLeaveManagement().getSickLeaves()));
						}
						Object[] clCount= adminManager.get("select  id,IFNULL(sum(leavesCount),0) as leavesCount from leaves where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and leaveType='CL'"+" and leaveStatus='"+Constants.ACTIVE_STATUS+"' and accountId="+id);
						Object[] slCount= adminManager.get("select  id,IFNULL(sum(leavesCount),0) as leavesCount from leaves where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and leaveType='SL'"+" and leaveStatus='"+Constants.ACTIVE_STATUS+"' and accountId="+id);
						Object[] elCount= adminManager.get("select  id,IFNULL(sum(leavesCount),0) as leavesCount from leaves where  custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and leaveType='EL'"+" and leaveStatus='"+Constants.ACTIVE_STATUS+"' and accountId="+id);
						if(!ObjectFunctions.isNullOrEmpty(clCount) && !ObjectFunctions.isNullOrEmpty(clCount[1])){
							if(casualLeaveDays > Double.valueOf(clCount[1].toString()))
								setCasualLeave(Double.valueOf(clCount[1].toString()));
							else
								setCasualLeave(casualLeaveDays);
						} 
						if(!ObjectFunctions.isNullOrEmpty(slCount) && !ObjectFunctions.isNullOrEmpty(slCount[1])){
							if(sickLeaveDays > Double.valueOf(slCount[1].toString()))
								setSickLeave(Double.valueOf(slCount[1].toString()));
							else
								setSickLeave(sickLeaveDays);
								
						} 
						if(!ObjectFunctions.isNullOrEmpty(elCount) && !ObjectFunctions.isNullOrEmpty(elCount[1])){
							if(earnedLeaveDays > Double.valueOf(elCount[1].toString()))
								setEarnedLeave(Double.valueOf(elCount[1].toString()));
							else
								setEarnedLeave(earnedLeaveDays);
								
						} 
						setPayLeaves(paidLeavesCount);
					}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void prepareFeeIds(long termId,long classId,long categoryId,long boardingPointId,boolean transportFeeByBoardingPoint){
		try {
			if(ObjectFunctions.isNullOrEmpty(adminManager)){
				adminManager=(AdminManager)SpringContextAware.getBean("adminManager");
            }
			StringBuffer queryString=new StringBuffer();
			queryString.append("schoolTermId="+termId+" and categoryId="+categoryId+" and ( classId="+classId);
			
			if(transportFeeByBoardingPoint){
				if(boardingPointId!=0)
				queryString.append(" or routeBoardingPointId="+boardingPointId);
			}
			queryString.append(")");
			//List<Fee> feeList=adminManager.getAll(Fee.class, queryString.toString());
			
			List<Long>  feeIds = adminManager.getAll("select id from Fee where "+queryString.toString());
			/*StringBuffer feeIds = new StringBuffer();
			feeIds.append("(");
			if(!ObjectFunctions.isNullOrEmpty(feeList)){
				for(Fee fee:feeList){
					feeIds.append(fee.getId());
					feeIds.append(",");
				}
			}
			feeIds.append("0)");*/
			if(!ObjectFunctions.isNullOrEmpty(feeIds))
				setAnyId("("+StringFunctions.convertListToCommaDelimitedString(feeIds)+")");
			else
				setAnyId("(0)");
			boardingPointId=0;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	
	@Actions({
		@Action(value = "ajaxImportClassAndSections", results = {})
		})
		public void ajaxImportClassAndSections() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxImportClassAndSections' method");
		}
		try
		{
			//prepareAcademicYearId();
    		// TODO what if no academic year
    		if(getUsrChgedAcademicId() != 0 ){
    			String fileName =null;
    			if(ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName")))
    				fileName = "class&Sections Details";
    			else
    				fileName = "class&Sections Details_"+getSession().getAttribute("academicYearName");	
				ExcelView excelView = new ExcelView();
				getResponse().setContentType(excelView.getMimeType());
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
				excelView.setWorkSheetName("Class-Section");
		    	excelView.createWorkSheet(0);
		    	excelView.setDefaultFormat(excelView.getArial10format());
	        	//For School Name
		        WritableFont boldfont10 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
	        	//new WritableCellFormat(boldfont10);
				boldfont10.setColour(Colour.WHITE);
				//ExcelView.getUserFormattedCell(boldfont10, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);

	        	 excelView.getWs().setColumnView(5,10);
	        	 excelView.getWs().setColumnView(6,10);
    			excelView.getWs().addCell(new Label(0,0, "Class",excelView.getUsermore10BoldformatGreenBgClr()));
    			excelView.getWs().addCell(new Label(1,0, "Section",excelView.getUsermore10BoldformatGreenBgClr()));
    			excelView.getWs().addCell(new Label(2,0, "Capacity",excelView.getUsermore10BoldformatGreenBgClr()));
    			excelView.getWs().addCell(new Label(3,0, "Medium",excelView.getUsermore10BoldformatGreenBgClr()));
    			excelView.getWs().addCell(new Label(4,0, "ClassId",excelView.getUsermore10BoldformatGreenBgClr()));
    			excelView.getWs().addCell(new Label(5,0, "GroupNumber",excelView.getUsermore10BoldformatGreenBgClr()));
    			excelView.getWs().addCell(new Label(6,0, "HigherStandard",excelView.getUsermore10BoldformatGreenBgClr()));
    			List<ViewClassSectionDetails>  studyClassList = adminManager.getAll(ViewClassSectionDetails.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
    			//List<Object[]>  studyClassList = adminManager.getAllClassAndSectionsByCustIdAndAcademicYearId(getUserCustId(),getAcademicYearId());
				if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) {
					int columnStart = 1;
					for (ViewClassSectionDetails classSection : studyClassList) {
						if (!ObjectFunctions.isNullOrEmpty(classSection)) {
							excelView.getWs().addCell(new Label(0,columnStart,classSection.getClassName()));
							excelView.getWs().addCell(new Label(1,columnStart,classSection.getSection()));
							excelView.getWs().addCell(new Label(2,columnStart,classSection.getSectionCapacity()+""));
							excelView.getWs().addCell(new Label(3,columnStart,classSection.getClassMedium()));
							excelView.getWs().addCell(new Label(4,columnStart,classSection.getClassSectionId()+""));
							excelView.getWs().addCell(new Label(5,columnStart,classSection.getGroupNumber()));
							if(classSection.isHigherStandard())
								excelView.getWs().addCell(new Label(6,columnStart,"Y"));
							else
								excelView.getWs().addCell(new Label(6,columnStart,"N"));
							columnStart++;
						}
					}
				}
				excelView.getWb().write();
				excelView.getWb().close();
    		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	 public void searchHostelStudentOrStaff(String criteria,String type){
			try {
				//prepareAcademicYearId();
				if ("Student".equals(type)){
					setStudentsList(adminManager.getStudentsPayentByRollNumber(getAnyTitle(),getUserCustId(),getUserAcademicYearId()));
				}else{
					setStaffsList(adminManager.getStaffDetailsBySearchName(getAnyTitle(),getUserCustId(),Constants.YES_STRING));
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
	 public void prepareTimingsForSchoolInfoList() {
			String[] timings={"05:00 AM","05:05 AM","05:10 AM","05:15 AM","05:20 AM","05:25 AM","05:30 AM","05:35 AM","05:40 AM","05:45 AM","05:50 AM","05:55 AM","06:00 AM","06:05 AM","06:10 AM","06:15 AM","06:20 AM","06:25 AM","06:30 AM","06:35 AM","06:40 AM","06:45 AM","06:50 AM","06:55 AM","07:00 AM","07:05 AM","07:10 AM","07:15 AM","07:20 AM","07:25 AM","07:30 AM","07:35 AM","07:40 AM","07:45 AM","07:50 AM","07:55 AM","08:00 AM","08:05 AM","08:10 AM","08:15 AM","08:20 AM","08:25 AM","08:30 AM","08:35 AM","08:40 AM","08:45 AM","08:50 AM","08:55 AM","09:00 AM","09:05 AM","09:10 AM","09:15 AM","09:20 AM","09:25 AM","09:30 AM","09:35 AM","09:40 AM","09:45 AM","09:50 AM","09:55 AM","10:00 AM","10:05 AM","10:10 AM","10:15 AM","10:20 AM","10:25 AM","10:30 AM","10:35 AM","10:40 AM","10:45 AM","10:50 AM","10:55 AM","11:00 AM","11:05 AM","11:10 AM","11:15 AM","11:20 AM","11:25 AM","11:30 AM","11:35 AM","11:40 AM","11:45 AM","11:50 AM","11:55 AM","12:00 PM","12:05 PM","12:10 PM","12:15 PM","12:20 PM","12:25 PM","12:30 PM","12:35 PM","12:40 PM","12:45 PM","12:50 PM","12:55 PM",
					"01:00 PM","01:05 PM","01:10 PM","01:10 PM","01:20 PM","01:25 PM","01:30 PM","01:35 PM","01:40 PM","01:45 PM","01:50 PM","01:55 PM","02:00 PM","02:05 PM","02:10 PM","02:10 PM","02:20 PM","02:25 PM","02:30 PM","02:35 PM","02:40 PM","02:45 PM","02:50 PM","02:55 PM","03:00 PM","03:05 PM","03:10 PM","03:10 PM","03:20 PM","03:25 PM","03:30 PM","03:35 PM","03:40 PM","03:45 PM","03:50 PM","03:55 PM","04:00 PM","04:05 PM","04:10 PM","04:10 PM","04:20 PM","04:25 PM","04:30 PM","04:35 PM","04:40 PM","04:45 PM","04:50 PM","04:55 PM","05:00 PM","05:05 PM","05:10 PM","05:15 PM","05:20 PM","05:25 PM","05:30 PM","05:35 PM","05:40 PM","05:45 PM","05:50 PM","05:55 PM","06:00 PM","06:05 PM","06:10 PM","06:15 PM","06:20 PM","06:25 PM","06:30 PM","06:35 PM","06:40 PM","06:45 PM","06:50 PM","06:55 PM","07:00 PM","07:05 PM","07:10 PM","07:15 PM","07:20 PM","07:25 PM","07:30 PM","07:35 PM","07:40 PM","07:45 PM","07:50 PM","07:55 PM","08:00 PM","08:05 PM","08:10 PM","08:15 PM","08:20 PM","08:25 PM","08:30 PM","08:35 PM","08:40 PM","08:45 PM","08:50 PM","08:55 PM","09:00 PM","09:05 PM","09:10 PM","09:15 PM","09:20 PM","09:25 PM","09:30 PM","09:35 PM","09:40 PM","09:45 PM","09:50 PM","09:55 PM","10:00 PM","10:05 PM","10:10 PM","10:15 PM","10:20 PM","10:25 PM","10:30 PM","10:35 PM","10:40 PM","10:45 PM","10:50 PM","10:55 PM","11:00 PM","11:05 PM","11:10 PM","11:15 PM","11:20 PM","11:25 PM","11:30 PM","11:35 PM","11:40 PM","11:45 PM","11:50 PM","11:55 PM"};
			List arraylist=Arrays.asList(timings);
				getObjectList().addAll(arraylist);
		}
	 	
	
  public String organizationDetailsForPdf(String fontPath,PdfPTable defaultersHeaderReport,int width ) {
	  try{
		  //Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
		  Customer customer = getCustomerByCustId();
		  if (!ObjectFunctions.isNullOrEmpty(customer)) {
				if (!StringFunctions.isNullOrEmpty(customer.getOrganization())) {
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganization().toUpperCase(), width,fontPath, 15, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				if (!StringFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())) {
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganizationFullAddress().toUpperCase(), width,fontPath, 8, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Name Of The Headmaster:",20, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(customer.getCustomerFullPersonName(), 80, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Email Id:", 20, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(customer.getCustEmail(),30, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Cell No:", 10, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(customer.getMobileNumber(), 40, fontPath));
			}
 	 }
 	 catch(Exception ex){
 		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
 	 }
	     return null;
  }
  public String hostelOrganizationDetailsForPdf(String fontPath,PdfPTable defaultersHeaderReport,int width ) {
	  try{
		      //Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
		  		Customer customer = getCustomerByCustId();
				String clause = "academicYearId="+ getUserAcademicYearId() + " and custId = "+ getUserCustId()+" and rolename='"+getUsername()+"'";
				ViewStaffPersonAccountDetails accountDetails = (ViewStaffPersonAccountDetails) adminManager.get(ViewStaffPersonAccountDetails.class,clause);
				if (!ObjectFunctions.isNullOrEmpty(accountDetails)) {
					Hostel hostel = (Hostel)adminManager.get(Hostel.class, "custId=" + accountDetails.getCustId());
					if (!StringFunctions.isNullOrEmpty(hostel.getHostelName())) {
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(hostel.getHostelName().toUpperCase(), width,fontPath, 15, "#005CB9",Element.ALIGN_CENTER, 5.0f));
					}
					if (!StringFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())) {
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganizationFullAddress().toUpperCase(), width,fontPath, 8, "#005CB9",Element.ALIGN_CENTER, 5.0f));
					}
					
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Name Of The Hostel Admin:", 20, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(accountDetails.getPersonFullName(), 80, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Email Id:", 20, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(accountDetails.getEmail(), 30, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Cell No:", 10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(accountDetails.getMobileNumber(), 40, fontPath));
				}
		}
	  catch(Exception ex){
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	  }
	  return null;
  }
  public String hostelOrganizationDetailsForExcel(ExcelView excelView,WritableCellFormat cellFormat10,WritableCellFormat cellFormat8,int size) {
	  try{
		  if (getUsername().equalsIgnoreCase("ROLE_HOSTEL")) {
			  //Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
			  Customer customer = getCustomerByCustId();
			  String clause = "academicYearId <="+ getUserAcademicYearId() + " and custId = "+ getUserCustId()+" and rolename='"+getUsername()+"'";
				ViewStaffPersonAccountDetails accountDetails = (ViewStaffPersonAccountDetails) adminManager.get(ViewStaffPersonAccountDetails.class,clause);
				if (!ObjectFunctions.isNullOrEmpty(accountDetails)) {
					Hostel hostel = (Hostel)adminManager.get(Hostel.class, "custId=" + accountDetails.getCustId());
					if (!ObjectFunctions.isNullOrEmpty(hostel)) {
						if(!ObjectFunctions.isNullOrEmpty(hostel.getHostelName())){
							excelView.getWs().mergeCells(0, 1, size,2);
							excelView.getWs().addCell(new Label(0,1, hostel.getHostelName().toUpperCase(), cellFormat10));
						}
						if(!ObjectFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())){
							excelView.getWs().mergeCells(0, 3, size,3);
							excelView.getWs().addCell(new Label(0,3, customer.getOrganizationFullAddress(), cellFormat8));
						}
						excelView.getWs().mergeCells(0, 4, 2,4);
						excelView.getWs().addCell(new Label(0,4, "Name Of The Headmaster:", excelView.getWrapCellFormat()));
						if(!ObjectFunctions.isNullOrEmpty(accountDetails.getPersonFullName())){
							excelView.getWs().mergeCells(3, 4, size,4);
							excelView.getWs().addCell(new Label(3,4, accountDetails.getPersonFullName(), excelView.getWrapCellFormat()));
						}
						excelView.getWs().mergeCells(0, 5, 2,5);
						excelView.getWs().addCell(new Label(0,5, "Email ID:", excelView.getWrapCellFormat()));
							excelView.getWs().mergeCells(3, 5, 6,5);
							excelView.getWs().addCell(new Label(3,5, accountDetails.getEmail(), excelView.getWrapCellFormat()));
						excelView.getWs().mergeCells(7, 5, 7,5);
						excelView.getWs().addCell(new Label(7,5, "Cell No:", excelView.getWrapCellFormat()));
							excelView.getWs().mergeCells(8, 5, size,5);
							excelView.getWs().addCell(new Label(8,5, accountDetails.getMobileNumber(), excelView.getWrapCellFormat()));
					}
				}
					
		  }else{
			  //Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
			  Customer customer = getCustomerByCustId();
			  if (!ObjectFunctions.isNullOrEmpty(customer)) {
					if(!ObjectFunctions.isNullOrEmpty(customer.getOrganization())){
						excelView.getWs().mergeCells(0, 1, size,2);
						excelView.getWs().addCell(new Label(0,1, customer.getOrganization(), cellFormat10));
					}
					if(!ObjectFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())){
						excelView.getWs().mergeCells(0, 3, size,3);
						excelView.getWs().addCell(new Label(0,3, customer.getOrganizationFullAddress(), cellFormat8));
					}
					excelView.getWs().mergeCells(0, 4, 2,4);
					excelView.getWs().addCell(new Label(0,4, "Name Of The Headmaster:", excelView.getWrapCellFormat()));
					if(!ObjectFunctions.isNullOrEmpty(customer.getCustomerFullPersonName())){
						excelView.getWs().mergeCells(3, 4, size,4);
						excelView.getWs().addCell(new Label(3,4, customer.getCustomerFullPersonName(), excelView.getWrapCellFormat()));
					}
					excelView.getWs().mergeCells(0, 5, 2,5);
					excelView.getWs().addCell(new Label(0,5, "Email ID:", excelView.getWrapCellFormat()));
					excelView.getWs().mergeCells(3, 5, 6,5);
					excelView.getWs().addCell(new Label(3,5, customer.getCustEmail(), excelView.getWrapCellFormat()));
						
					excelView.getWs().mergeCells(7, 5, 7,5);
					excelView.getWs().addCell(new Label(7,5, "Cell No:", excelView.getWrapCellFormat()));
					excelView.getWs().mergeCells(8, 5, size,5);
					excelView.getWs().addCell(new Label(8,5, customer.getMobileNumber(), excelView.getWrapCellFormat()));
				}
		  }
	  }
	  catch(Exception ex){
		  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	  }
	  return null;
  }
  
  public void prepareStudentFeeDueAndCollection(Student student) {
		try {
			String currentDate = "";
			long invoiceNumber = 0;
			AcademicYear academicYear = null;
			List<SchoolTerms> schoolTermsList = null;
			Date newDate = new Date();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String toDayDate = formatter.format(newDate);
			String today = "";
			StringBuffer buffer = null;
			buffer = new StringBuffer();
			buffer.append("(");
			for (Object obj : getObjectList()) {
				SchoolFeeSetting feeSetting = (SchoolFeeSetting) obj;
				buffer.append(feeSetting.getId() + ",");
			}
			buffer.append("0)");
			/* The below service for to take the fee details with Genaral category because in transport we will assign fee only for Genaral category we will not take any other category so will assign Genaral category if the user have boarding point wise fee in academic year*/
			if (!ObjectFunctions.isNullOrEmpty(student)) {
				academicYear = getCurrentAcademicYear();
				double balanceFeeAmount = 0;
				Object[] totalTerms = null;
				schoolTermsList = adminManager.getAll(SchoolTerms.class,"custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and dueDate<'" + toDayDate+ " 00:00:00'" +(student.isJoinedThroughAdmissions()?"": " and applToNewStuds='N' ")+" and feeSettingId in "+ buffer.toString()+ " order by feeSettingId,dueDate");
				if (!ObjectFunctions.isNullOrEmpty(schoolTermsList)) {
					for (SchoolTerms schoolTerms : schoolTermsList) {
						ViewStudentFeePaymentDetails studentFeePaymentDetails = null;
						if (Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName())){
							studentFeePaymentDetails = adminManager.getStudentWiseFeePaidAndUnpaidDetails("vw_studentTransportFeePaymentDetails",getUserCustId(),academicYear.getId(),schoolTerms.getId(),student.getClassSectionId(),student.getId());
							totalTerms = adminManager.get("select count(distinct(schoolTermId)),schoolTermId from vw_studentTransportFeePaymentDetails where custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+" and dueDate <=curdate() and studentId="+student.getId());
							}
						else{
							studentFeePaymentDetails = adminManager.getStudentWiseFeePaidAndUnpaidDetails("vw_studentFeePaymentDetails",getUserCustId(),academicYear.getId(),schoolTerms.getId(),student.getClassSectionId(),student.getId());
							totalTerms = adminManager.get("select count(distinct(schoolTermId)),schoolTermId from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+" and dueDate <=curdate() and feeSettingId="+schoolTerms.getFeeSetting().getId()+" and studentId="+student.getId());
						}
						
						if (!ObjectFunctions.isNullOrEmpty(studentFeePaymentDetails)) {
							ViewStudentFeePaymentDetails feePaymentDetails = new ViewStudentFeePaymentDetails();
							feePaymentDetails.setId(student.getId());
							feePaymentDetails.setFirstName(student.getAccount().getPerson().getFullPersonName());
							feePaymentDetails.setRollNumber(student.getRollNumber());
							feePaymentDetails.setPhoneNumber(student.getAccount().getPerson().getMobileNumber());
							feePaymentDetails.setTermName(schoolTerms.getTermName());
							feePaymentDetails.setDueDate(schoolTerms.getDueDate());
							feePaymentDetails.setFeeAmount(studentFeePaymentDetails.getFeeAmount());
							feePaymentDetails.setPaymentAmount(studentFeePaymentDetails.getPaidAmount());
							feePaymentDetails.setDiscountAmount(studentFeePaymentDetails.getDiscountAmount());
							feePaymentDetails.setFeeSettingId(schoolTerms.getFeeSetting().getId());
							feePaymentDetails.setSettingName(schoolTerms.getFeeSetting().getSettingName());
							feePaymentDetails.setConcessionAmount(studentFeePaymentDetails.getConcessionAmount());
							feePaymentDetails.setNumberOfTerms(Long.valueOf(totalTerms[0].toString()));
							balanceFeeAmount = balanceFeeAmount + feePaymentDetails.getPaymentAmount();
							getFeeTypeList().add(feePaymentDetails);
							feePaymentDetails = null;
						}
					}
					setBalanceAmount(balanceFeeAmount);
					schoolTermsList=null;
				}
				schoolTermsList = adminManager.getAll(SchoolTerms.class, "custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId() +(student.isJoinedThroughAdmissions()?"": " and applToNewStuds='N' ")+" and feeSettingId in "+ buffer.toString()+ " order by feeSettingId,dueDate");
				if (!ObjectFunctions.isNullOrEmpty(schoolTermsList)) {
					for (SchoolTerms schoolTerms : schoolTermsList) {
						ViewStudentFeePaymentDetails studentFeePaymentDetails = null;
						if (Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName()))
							studentFeePaymentDetails = adminManager.getStudentWiseFeePaidAndUnpaidDetails("vw_studentTransportFeePaymentDetails",getUserCustId(),academicYear.getId(),schoolTerms.getId(),student.getClassSectionId(),student.getId());
						else
							studentFeePaymentDetails = adminManager.getStudentWiseFeePaidAndUnpaidDetails("vw_studentFeePaymentDetails",getUserCustId(),academicYear.getId(),schoolTerms.getId(),student.getClassSectionId(),student.getId());
						if (!ObjectFunctions.isNullOrEmpty(studentFeePaymentDetails)) {
							if(studentFeePaymentDetails.getPaymentAmount()>0){
								ViewStudentFeePaymentDetails feePaymentDetails = new ViewStudentFeePaymentDetails();
								feePaymentDetails.setId(student.getId());
								feePaymentDetails.setFirstName(student.getAccount().getPerson().getFullPersonName());
								feePaymentDetails.setRollNumber(student.getRollNumber());
								feePaymentDetails.setPhoneNumber(student.getAccount().getPerson().getMobileNumber());
								feePaymentDetails.setTermName(schoolTerms.getTermName());
								feePaymentDetails.setDueDate(schoolTerms.getDueDate());
								feePaymentDetails.setFeeAmount(studentFeePaymentDetails.getFeeAmount());
								feePaymentDetails.setPaymentAmount(studentFeePaymentDetails.getPaymentAmount());
								feePaymentDetails.setDiscountAmount(studentFeePaymentDetails.getDiscountAmount());
								feePaymentDetails.setFeeSettingId(schoolTerms.getFeeSetting().getId());
								feePaymentDetails.setSettingName(schoolTerms.getFeeSetting().getSettingName());
								getStudentFee14List().add(feePaymentDetails);
								feePaymentDetails = null;
							}
							studentFeePaymentDetails=null;
						}
					}
					schoolTermsList=null;
				}
				StringBuffer query = new StringBuffer(" studentId="+student.getId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.NO_STRING+"' and academicYearId="+ getUserAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(student.getAcademicYear().getReceiptGenerationType()) && student.getAcademicYear().getReceiptGenerationType().equalsIgnoreCase("A"))
					query.append(" and invoiceNumber !=0  group by invoiceNumber");
				else
					query.append(" and invoiceString is not null  group by  invoiceString");
				setAllStudentsList(adminManager.getAll(StudentPayment.class, query.toString()));
				
				setTempList(accountManager.getOtherFeePaymentDetails(getUserCustId(),getUserAcademicYearId(),student.getId()));
				//setAllStudentsList(adminManager.getFeesPaymentListByStudentPaymentId("ViewStudentFeePaymentDetails",student.getId(), student.getClassNameClassId().getId(), student.getCustId(),getUserAcademicYearId(), currentDate, "",invoiceNumber));
				student = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}

	public void prepareCustomerHeaderExacel(ExcelView excelView,String className, WritableCellFormat cellFormat8,WritableCellFormat cellFormat10, int size) {
		try {
			excelView.setWorkSheetName(className);
			excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			//Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());
			Customer customer = getCustomerByCustId();
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
				if (!ObjectFunctions.isNullOrEmpty(customer.getOrganization())) {
					excelView.getWs().mergeCells(0, 0, size, 1);
					excelView.getWs().addCell(new Label(0, 0, customer.getOrganization(),cellFormat10));
				}
				if (StringFunctions.isNotNullOrEmpty(customer.getCustomerFormattedAddress())) {
					excelView.getWs().mergeCells(0, 2, size, 2);
					excelView.getWs().addCell(new Label(0, 2, customer.getCustomerFormattedAddress(),cellFormat8));
				}
				customer = null;
			}
			excelView.getWs().mergeCells(0, 3, size, 5);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void prepareMarksSheetSubHeaderLables(ExcelView excelView,String examTypeName,String className,List<ExamSchedules> examScheduleDetails,List<ViewStudentClassDetails> classStudentsList,long examTypeId){
		try {
			ExamSchedules classSchedule=null;
			excelView.getWs().addCell(new Label(0,7, "Class", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1,7, className, excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(2,7, "Exam Type", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().mergeCells(4, 7, 6, 7);
			excelView.getWs().addCell(new Label(4,7, examTypeName, excelView.getUsermore10BoldformatGreenBgClr()));
			//for setting cell size
			excelView.getWs().setColumnView(0,10);
			excelView.getWs().setColumnView(1,20);
			excelView.getWs().setColumnView(2,30);
			excelView.getWs().addCell(new Label(0,8, "", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(0,9, "", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(0,10, "Admission Number", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1,8, "", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1,9, "", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(1,10, "Student Roll No", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(2,8, "", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(2,9, "", excelView.getUsermore10BoldformatGreenBgClr()));
			excelView.getWs().addCell(new Label(2,10, "Student Name", excelView.getUsermore10BoldformatGreenBgClr()));
			
			int column=4;
    		Map<String,List> subjectMap=new LinkedHashMap<String,List>();
    		List sechuduleObjects= null;
			for(ExamSchedules schedule:examScheduleDetails){				
				if(!ObjectFunctions.isNullOrEmpty(schedule))
	    		{   
					sechuduleObjects=new ArrayList();
					if(subjectMap.containsKey(schedule.getSubjectName())){
						sechuduleObjects=subjectMap.get(schedule.getSubjectName());
						sechuduleObjects.add(schedule);
						subjectMap.put(schedule.getSubjectName(),sechuduleObjects);
					}else{						
						sechuduleObjects.add(schedule);
						subjectMap.put(schedule.getSubjectName(),sechuduleObjects);
					}
					sechuduleObjects=null;
	    		}
				schedule = null;
			}
			if(!ObjectFunctions.isNullOrEmpty(subjectMap)){
				Iterator it = subjectMap.entrySet().iterator();
				 int startMergeCell=4;
			    while (it.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it.next();
			        String subject=(String)pairs.getKey();
			        ArrayList<ExamSchedules> subjectSchedules=(ArrayList<ExamSchedules>) pairs.getValue();
			        int mergeCount=subjectSchedules.size();			       
			        int endMergeCell=0;
			        if(subjectSchedules.size() > 0)
			        	classSchedule=subjectSchedules.get(0);
			        //for displaying subjects names and merging same subject name cells			        
			        excelView.getWs().addCell(new Label(column,9, subject, excelView.getUsermore10BoldformatGreenBgClr()));
			        endMergeCell=startMergeCell+mergeCount-1;
			        if(!ObjectFunctions.isNullOrEmpty(classSchedule)){
			        	if(classSchedule.isHigherStandard()){
			        		endMergeCell=startMergeCell+mergeCount;
			        	}
			        }
			        excelView.getWs().mergeCells(startMergeCell, 9, endMergeCell, 9);
			        startMergeCell=endMergeCell+1;
			        for(ExamSchedules schedule:subjectSchedules){				
			        	excelView.getWs().setColumnView(column,(schedule.getSubTypeName()+"Max Marks: "+schedule.getMaxMarks()+"").length());
						excelView.getWs().addCell(new Label(column,8, String.valueOf(schedule.getExamTypeId()+"_"+schedule.getId()), excelView.getUsermore10BoldformatGreenBgClr()));
			    		excelView.getWs().addCell(new Label(column,10, schedule.getSubTypeName()+"Max Marks: "+schedule.getMaxMarks()+"", excelView.getUsermore10BoldformatGreenBgClr()));			    		
			    		column++;
					}
			        if(!ObjectFunctions.isNullOrEmpty(classSchedule)){
			        	if(classSchedule.isHigherStandard()){
			        		excelView.getWs().addCell(new Label(column,8, classSchedule.getExamTypeId()+"_"+classSchedule.getId()+"_MOD", excelView.getUsermore10BoldformatGreenBgClr()));
			        		excelView.getWs().addCell(new Label(column,10, "MOD", excelView.getUsermore10BoldformatGreenBgClr()));
			        		column++;
			        	}
			        }
			        subjectSchedules=null;
			        
			    }				
			}	
			CellView cv=new CellView();
	        cv.setHidden(true);
	        excelView.getWs().setRowView(8, cv);
	        cv=new CellView();
	        cv.setHidden(true);
	        excelView.getWs().setColumnView(3, cv);
			int studentRowSize=11;
			List<ViewStudentMarks> studentMarks=null;
			for(ViewStudentClassDetails studentDetails:classStudentsList){
				if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
					excelView.getWs().addCell(new Label(0,studentRowSize, String.valueOf(studentDetails.getAdmissionNumber()), excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(1,studentRowSize, String.valueOf(studentDetails.getRollNumber()), excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(2,studentRowSize, studentDetails.getPersonFullName(), excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(3,studentRowSize, studentDetails.getStudId()+"", excelView.getUsermore10BoldformatGreenBgClr()));
					studentMarks = adminManager.getStudentMarksByStudentIdAndExamtypeId(studentDetails.getStudId(),examTypeId,getUserCustId(),getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
						for(ViewStudentMarks marks: studentMarks){
							// need to compare subjects
							Cell cell=excelView.getWs().findCell(marks.getExamTypeId()+"_"+marks.getScheduleId());
							if(!ObjectFunctions.isNullOrEmpty(cell)){
									if(marks.isPresent())
										excelView.getWs().addCell(new Label(cell.getColumn(),studentRowSize, String.valueOf(marks.getObtainedMarks()), excelView.getDefaultFormat()));
									else
										excelView.getWs().addCell(new Label(cell.getColumn(),studentRowSize, String.valueOf("A"), excelView.getDefaultFormat()));
									cell=null;
							}
							if(marks.getModerationMarks() > 0){
								cell=excelView.getWs().findCell(marks.getExamTypeId()+"_"+marks.getScheduleId()+"_MOD");
								if(!ObjectFunctions.isNullOrEmpty(cell)){
									excelView.getWs().addCell(new Label(cell.getColumn(),studentRowSize, String.valueOf(marks.getModerationMarks()), excelView.getDefaultFormat()));
									cell=null;
								}
							}
							marks=null;
						}
						studentMarks=null;
					}
					
					studentRowSize++;
					studentDetails=null;
				}
			}
			studentRowSize+=1;
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void prepareExcleSheetHeaders(ExcelView excelView,WritableCellFormat cellFormat8,WritableCellFormat cellFormat10) {
		try {
			//For School name
			WritableFont boldfont10,boldfont8=null;
        	boldfont10 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
        	cellFormat10 = new WritableCellFormat(boldfont10);
			boldfont10.setColour(Colour.WHITE);
			cellFormat10=ExcelView.getUserFormattedCell(boldfont10, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
			
			//For School Address
        	boldfont8 = new WritableFont(WritableFont.createFont("droidsans"),8,WritableFont.BOLD);
        	cellFormat8 = new WritableCellFormat(boldfont8);
	    	boldfont8.setColour(Colour.WHITE);
	    	cellFormat8=ExcelView.getUserFormattedCell(boldfont8, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
			//Staff staff = (Staff)adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void prepareStudentsListBystaffIdAndAcademicYearId(long accountId) {
		try {
			if (accountId > 0) {
				List classTeachers= adminManager.getAllHqlQuery("FROM ClassTeacher cteacher WHERE cteacher.staff.account="+accountId+" and cteacher.staff.status='Y' and cteacher.academicYear="+getUserAcademicYearId()+" and cteacher.classTeacher='Y'");
				if(ObjectFunctions.isNotNullOrEmpty(classTeachers)){
					ClassTeacher classTeacher = (ClassTeacher)classTeachers.get(0);
					if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
						StudyClass studyClass = classTeacher.getStudyClass();
						if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
							List<ViewStudentPersonAccountDetails> studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class, "classSectionId ="+studyClass.getId()+" and academicYearId="+getUserAcademicYearId()+" and description is null");
							if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
								setStudentsList(new ArrayList());
								setClassStudentsList(studentsList);
								setViewStudentPersonAccountDetailsList(studentsList);
								for (ViewStudentPersonAccountDetails studentDetails : studentsList) {
									if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
										getAllStudentsList().add(studentDetails.getRollNumber());
										getStudentsList().add(studentDetails.getFirstName());
									}
								}
							}
							setStudyClass(studyClass);
						}
					} 
					classTeacher = null;
				}
			 }
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions( {
		@Action(value = "ajaxDoEditApprovedLeaves", results = { @Result(location = "leaves/ajaxViewStaffLeaves.jsp", name = "success") }),
		@Action(value = "ajaxDoStaffEditApprovedLeaves", results = { @Result(location = "../staff/leaves/ajaxViewStaffLeaves.jsp", name = "success") })
	})
	public String ajaxDoEditApprovedLeaves() throws URTUniversalException {
	    if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxDoEditApprovedLeaves' method");
	    }
	    try {
	    	//prepareAcademicYearId();
	    	Student student = null;
		     if(!StringFunctions.isNullOrEmpty(getAnyId()) && getTempId()!=0){ //here getTempId() is a accountId
		    	 student = staffManager.getStudentByAcountId(getTempId());
		    	 
		    	 if(!StringFunctions.isNullOrEmpty(getAnyTitle()) && !StringFunctions.isNullOrEmpty(getTempString())){
		    		 String startDate= DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN,getAnyTitle());
						String endDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, getTempString());
				
						if(!ObjectFunctions.isNullOrEmpty(student))
						{
					            staffManager.removeStudentDailyAttendance(student.getId(),startDate,endDate);
						}
						else
						{
							staffManager.removeStaffAttendanceByaccountIdAndDates(getTempId(),startDate,endDate,getUserCustId(),getUserAcademicYearId());
							super.addActionMessage("Leave removed successfully. ");
						}
		    		
		    	 }
				 staffManager.remove(Leave.class, Long.valueOf(getAnyId()));
			 }
		     long id = getUser().getId();
				if(id != 0){
					setToDate(new Date());
					List schoolHolidaysList = adminManager.geAllSchoolHolidaysListByAcademicYearId(getUserCustId(),getUserAcademicYearId());
					if(!ObjectFunctions.isNullOrEmpty(schoolHolidaysList))
					{
						setObjectList(schoolHolidaysList);
					}
					ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = (ViewStaffPersonAccountDetails) staffManager.get(ViewStaffPersonAccountDetails.class, id,"accountId");
					if(!ObjectFunctions.isNullOrEmpty(viewStaffPersonAccountDetails))
					{
						setLeaveManagement((LeaveManagement) adminManager.get(LeaveManagement.class,"custId="+getUserCustId()+" and permanentOrContract='"+viewStaffPersonAccountDetails.getStaffType()+"' and roleId="+viewStaffPersonAccountDetails.getRoleId()+" and academicYearId="+getUserAcademicYearId()));
						//setLeaveManagement(adminManager.getLeaveManagementByRoleName(viewStaffPersonAccountDetails.getRoleId(), getUserCustId(),getUserAcademicYearId()));
					}
					staffLeavesManagement(id);
					staffLeaves(id);
				}
    		}
        catch (Exception ex) {
    	 	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        	return SUCCESS;
	}	
	/*
	* Removed prepareAcademicYearId and use getUserAcademicYearId is done by venkatesh - 04-29-2013
	*/  
	    public void staffLeaves(long id){
			try{
				setApprovedLeavesList(null);
				setLeavesList(null);
				setRejectedLeavesList(null);
				if(getUserAcademicYearId() > 0){
					/* this list ApprovedLeavesList*/
					setApprovedLeavesList(staffManager.getLeavesListByAccountId(id,Constants.ACTIVE_STATUS, getUserCustId(),getUserAcademicYearId()));
					/* this list PendingLeavesList*/
					setLeavesList(staffManager.getLeavesListByAccountId(id,Constants.PENDING_STATUS, getUserCustId(),getUserAcademicYearId()));
					/* this list RejectedLeavesList*/
					setRejectedLeavesList(staffManager.getLeavesListByAccountId(id,Constants.REJECTED_STATUS, getUserCustId(),getUserAcademicYearId()));
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}   
	    
	/* This methos use for to create new academic year. */
	public void createNewAcademicYear(AcademicYear futureAcademicYear,AcademicYear presentAcademicYear,String futureYear) {
		futureAcademicYear = new AcademicYear();
		futureAcademicYear.setCreatedById(getUser().getId());
		futureAcademicYear.setCreatedDate(new Date());
		futureAcademicYear.setCustId(getUserCustId());
		futureAcademicYear.setEndDate(null);
		futureAcademicYear.setStartDate(null);
		futureAcademicYear.setEndTime(presentAcademicYear.getEndTime());
		futureAcademicYear.setEveningBreakEndTime(presentAcademicYear.getEveningBreakEndTime());
		futureAcademicYear.setEveningBreakStartTime(presentAcademicYear.getEveningBreakStartTime());
		futureAcademicYear.setLastAccessDate(new Date());
		futureAcademicYear.setAcademicYear(futureYear);
		futureAcademicYear.setLunchEndTime(presentAcademicYear.getLunchEndTime());
		futureAcademicYear.setLunchStartTime(presentAcademicYear.getLunchStartTime());
		futureAcademicYear.setMorningBreakEndTime(presentAcademicYear.getMorningBreakEndTime());
		futureAcademicYear.setMorningBreakStartTime(presentAcademicYear.getMorningBreakStartTime());
		futureAcademicYear.setNextAcademicStartDate(null);
		futureAcademicYear.setNoOfHolidays(0);
		futureAcademicYear.setNoOfWorkingDays(0);
		futureAcademicYear.setPastYear(Long.valueOf(futureYear.split("-")[0]));
		futureAcademicYear.setStatus(Constants.NO_STRING);
		futureAcademicYear.setStartTime(presentAcademicYear.getStartTime());
		//futureAcademicYear.setVersion(0);
		futureAcademicYear.setHolidayStatus(presentAcademicYear.getHolidayStatus());
		futureAcademicYear.setIsDefaultExamTypeStatus(Constants.NO_STRING);
		futureAcademicYear.setEnableSchoolShift(Constants.NO_STRING);
		futureAcademicYear.setReceiptGenerationType(presentAcademicYear.getReceiptGenerationType());
		futureAcademicYear.setFeeModuleUsegeBy("W");
		futureAcademicYear = adminManager.saveAcademicYear(futureAcademicYear);
		setAcademicYear(futureAcademicYear);
	}
	public void ajaxGetClassSubjectsSettings(long classId){
	   	setViewClassSubjectsSettings(adminManager.getAllClassWiseSubjects("classSectionId="+classId));
		if(ObjectFunctions.isNotNullOrEmpty(getViewClassSubjectsSettings()))
			Collections.sort(getViewClassSubjectsSettings());
   }
	@Actions( {
		@Action(value = "ajaxGetClassWiseTimeTable", results = { @Result(location = "../admin/academic/timeTable/ajaxViewClasswiseTimetable.jsp", name = "success") })
		})
		public String ajaxGetClassWiseTimeTable() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetClassWiseTimeTable' method");
			}
			try {
				if(StringFunctions.isNotNullOrEmpty(getClassId())){
					setWeekDayList(adminManager.getAll(ViewClassWisePeriodsCountDetails.class,"classSectionId="+getClassId()));
					setObjectList(adminManager.getAll(TimeTable.class,"classSectionId="+getClassId()+" group by periodName,periodType order by periodType DESC,periodName"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	 @Actions( {
			@Action(value = "ajaxGetStaffTimeTable", results = { @Result(location = "../admin/academic/timeTable/ajaxViewStaffTimetable.jsp", name = "success") })
			})
			public String ajaxGetStaffTimeTable() throws URTUniversalException {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'ajaxGetStaffTimeTable' method");
				}
				try {
					//prepareAcademicYearId();
					if(getTempId() > 0){
						List<Object[]> classTeacherClasses= adminManager.getAll("select id,studyClassId from classTeacher where teacherId="+getTempId()+" and academicYearId="+getUserAcademicYearId()+" group by studyClassId");
						if(ObjectFunctions.isNotNullOrEmpty(classTeacherClasses)){
							StringBuffer classIds = new StringBuffer();
							classIds.append("(");
							for(Object[] obj: classTeacherClasses){
								classIds.append(obj[1].toString()+",");
							}
							classIds.append("0)");
							setWeekDayList(adminManager.getAll(ViewClassWisePeriodsCountDetails.class,"classSectionId in "+classIds.toString()+" group by dayId"));
							setObjectList(adminManager.getAll(TimeTable.class,"classSectionId in "+classIds.toString()+" group by periodName,periodType order by periodType DESC,periodName"));
							classIds = null;
							classTeacherClasses = null;
						}
						//setTempList(adminManager.getAll(ViewTimeTableDetails.class, "classSectionId="+getClassId()));
					}
				} catch (Exception ex) {
					ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				}
				return SUCCESS;
		}
	 @Actions( { @Action(value = "ajaxGetStaffTimeTableDetails", results = { @Result(type = "json", name = "success", params = {"includeProperties", "objectList.*" }) }) })
		public String ajaxGetStaffTimeTableDetails() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetStaffTimeTableDetails' method");
			}
			try {
				//prepareAcademicYearId();
				if(getTempId() > 0){
					setObjectList(adminManager.getAll(ViewTimeTableDetails.class, "teacherId="+getTempId()+" and academicYearId="+getUserAcademicYearId()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 
	public String schoolAddresDetailsOnlyForExcel(ExcelView excelView,int size) {
		  try{
				WritableFont boldfont10 = null;
				WritableCellFormat cellFormat10 = null;
				boldfont10 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
				cellFormat10 = new WritableCellFormat(boldfont10);
				boldfont10.setColour(Colour.WHITE);
				cellFormat10 = ExcelView.getUserFormattedCell(boldfont10, Colour.AQUA, false,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE, BorderLineStyle.NONE);

				// For School Address
				WritableFont boldfont8 = null;
				WritableCellFormat cellFormat8 = null;
				boldfont8 = new WritableFont(WritableFont.ARIAL, 8,WritableFont.BOLD, true);
				cellFormat8 = new WritableCellFormat(boldfont8);
				boldfont8.setColour(Colour.WHITE);
				cellFormat8 = ExcelView.getUserFormattedCell(boldfont8, Colour.AQUA, false, false,Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE, BorderLineStyle.NONE);

				  //Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());  
				Customer customer = getCustomerByCustId();
				  if (!ObjectFunctions.isNullOrEmpty(customer)) {
					  if(!ObjectFunctions.isNullOrEmpty(customer.getOrganization())){
						  excelView.getWs().mergeCells(0, 1, size,3);
						  excelView.getWs().addCell(new Label(0,1, customer.getOrganization(), cellFormat10));
					  }
					  if(!ObjectFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())){
						  excelView.getWs().mergeCells(0, 4, size,5);
						  excelView.getWs().addCell(new Label(0,4, customer.getOrganizationFullAddress(), cellFormat8));
					  }
					  
			  }
		  }
		  catch(Exception ex){
			  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		  }
		  return null;
	  }
	public String hostelStudentDetailsOnlyForExcel(ExcelView excelView,int size) {
		  try{
				WritableFont boldfont10 = null;
				WritableCellFormat cellFormat10 = null;
				boldfont10 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
				cellFormat10 = new WritableCellFormat(boldfont10);
				boldfont10.setColour(Colour.WHITE);
				cellFormat10 = ExcelView.getUserFormattedCell(boldfont10, Colour.AQUA, false,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE, BorderLineStyle.NONE);

				// For School Address
				WritableFont boldfont8 = null;
				WritableCellFormat cellFormat8 = null;
				boldfont8 = new WritableFont(WritableFont.ARIAL, 8,WritableFont.BOLD, true);
				cellFormat8 = new WritableCellFormat(boldfont8);
				boldfont8.setColour(Colour.WHITE);
				cellFormat8 = ExcelView.getUserFormattedCell(boldfont8, Colour.AQUA, false, false,Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE, BorderLineStyle.NONE);

				  //Customer customer = (Customer) adminManager.get(Customer.class,getUserCustId());  
				Customer customer = getCustomerByCustId();
				  if (!ObjectFunctions.isNullOrEmpty(customer)) {
					  if(!ObjectFunctions.isNullOrEmpty(customer.getOrganization())){
						  excelView.getWs().mergeCells(0, 1, size,3);
						  excelView.getWs().addCell(new Label(0,1, customer.getOrganization(), cellFormat10));
					  }
					  if(!ObjectFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())){
						  excelView.getWs().mergeCells(0, 4, size,5);
						  excelView.getWs().addCell(new Label(0,4, customer.getOrganizationFullAddress(), cellFormat8));
					  }
					  
			  }
		  }
		  catch(Exception ex){
			  ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			  JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		  }
		  return null;
	  }

	protected static int getMonthNumberFromMonthName(String month)  {
		
		int monthNum=0;
		try {
			Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			monthNum = cal.get(Calendar.MONTH);
		} catch (java.text.ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return monthNum + 1;

	}
	public void saveStudentFeePendingDetails(long feeId,Student student/*,long classId*/){
		try {
			StudentFeePaidDetails studentFeePaidDetails=null;
			List<StudentPayment> studentPaymentList=adminManager.getAll(StudentPayment.class, " studentId="+student.getId());
			if(!ObjectFunctions.isNullOrEmpty(studentPaymentList)){
				for(StudentPayment studentPayment : studentPaymentList){
					studentFeePaidDetails=(StudentFeePaidDetails)adminManager.get(StudentFeePaidDetails.class, " studentPaymentId="+studentPayment.getId()+" and feeId="+feeId);
					if(!ObjectFunctions.isNullOrEmpty(studentFeePaidDetails)){
						/*if(classId>0){
							studentFeePaidDetails.setFutureFeeStatus(Constants.PAYMENT_STATUS);
						}else{*/
							studentFeePaidDetails.setPaymentStatus(Constants.PAYMENT_STATUS);
						//}
						adminManager.save(studentFeePaidDetails);
						studentFeePaidDetails=null;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	} 
	/**Changed by seshu on 29th April. Code refactor for checking students available or not.*/
	public void checkClassHavingStudentsOrNot(List<ClassName> classList) {
		if (!ObjectFunctions.isNullOrEmpty(classList)) {
			if (ObjectFunctions.isNullOrEmpty(getClassList()))
				setClassList(new ArrayList<ClassName>());
			if (ObjectFunctions.isNullOrEmpty(getClassNameList()))
				setClassNameList(new ArrayList<ClassName>());
			for (ClassName className : classList) {
				if (className.isClassHavingStudents()) 
					getClassList().add(className);
				else 
					getClassNameList().add(className);
			}
			if (ObjectFunctions.isNotNullOrEmpty(getClassNameList()))
				Collections.sort(getClassNameList());
			if (ObjectFunctions.isNotNullOrEmpty(getClassList())) 
				Collections.sort(getClassList());
		}
	}
	
	public Map<String, State> getStatelist() {
		return statelist;
	}

	public void setStatelist(Map<String, State> statelist) {
		this.statelist = statelist;
	}
		
	public void ajaxGetClassSectionsAndMonthsDetailsForAttendance()
	{
		Map<String, Integer> monthNameList = (Map<String, Integer>) getSession().getAttribute("GetMonthwiseWorkingDays");
		if(ObjectFunctions.isNullOrEmpty(monthNameList))
		{
			monthNameList = adminManager.getMonthwiseSchoolWorkingDays(getUserCustId(),getUserAcademicYearId(),null,null,true,"", getClassId()); //here getClassId used to academicyear have class wise holiday(CH).
			
			if(!ObjectFunctions.isNullOrEmpty(monthNameList))
			{
				 getSession().setAttribute("GetMonthwiseWorkingDays",monthNameList);
			}
		}
		//setMonthNamesList(ajaxGetMonthwiseWorkingDays(getUsrChgedAcademicId(),null,null,true));
		setMonthNamesList(monthNameList);
		monthNameList = null;
		setStaffRoleName(getParamValue("staffAtt"));
		if(getUser().getUserRole().equalsIgnoreCase("ROLE_ADMIN") || getUser().getUserRole().equalsIgnoreCase("ROLE_ADMINOFFICER"))
		{
			List<StudyClass> studyClassList = (List<StudyClass>) getSession().getAttribute("GetStudyClassListGT0");
			if(ObjectFunctions.isNullOrEmpty(studyClassList))
			{
				studyClassList = adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and filledSeats > 0");
				
				if(!ObjectFunctions.isNullOrEmpty(studyClassList))
				{
					 getSession().setAttribute("GetStudyClassListGT0",studyClassList);
				}
				Collections.sort(studyClassList);
			}
			setStudyClassList(studyClassList);
			studyClassList = null;
			
			setObjectList(adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and filledSeats <= 0"));
			if(ObjectFunctions.isNotNullOrEmpty(getObjectList()))
				Collections.sort(getObjectList());
			
			studyClassList = (List<StudyClass>) getSession().getAttribute("GetStudyClassListLT0");
			if(ObjectFunctions.isNullOrEmpty(studyClassList))
			{
				studyClassList = adminManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and filledSeats <= 0");
				
				if(!ObjectFunctions.isNullOrEmpty(studyClassList))
				{
					 getSession().setAttribute("GetStudyClassListLT0",studyClassList);
				}
				Collections.sort(studyClassList);
			}
			setObjectList(studyClassList);
			studyClassList = null;
		}
		else
		{
			doInitAttendanceData();
		}
		
		
	}
	public void ajaxGetClassesHandleteachers(){
		try{
			setTempList(adminManager.getStaffsListByRoleAndCustIdAndStatus("'"+Constants.SCHOOL_PRINCIPAL+"','"+Constants.SCHOOL_TEACHER +"','"+Constants.SCHOOL_HOD+"','"+Constants.SCHOOL_ASST_STAFF +"'",getUserCustId(),Constants.YES_STRING,getUserAcademicYearId()));
			if(ObjectFunctions.isNotNullOrEmpty(getTempList()))
				Collections.sort(getTempList());
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void getRouteListByCustIdandAcademicYearId(long academicYearId)
	{ 
		Object[] allStudentsCount = null;
		List<Route> routesList = adminManager.getAll(Route.class,"custId="+getUserCustId()+" and academicYearId="+academicYearId+" and status='"+Constants.YES_STRING+"'");   //Here we have displayed only active routes with custId nad academicYearId
		if(!ObjectFunctions.isNullOrEmpty(routesList))
		{
			for(Route route : routesList)
			{
				List<ViewRouteWiseStudents> viewRouteWiseStudentsList = adminManager.getAll(ViewRouteWiseStudents.class, "routeId="+route.getId());
				allStudentsCount=adminManager.get("select count(distinct(s.studentId)),r.id from studentTransportDetails s join routeBoardingPoints br on(br.id = s.pickupBoardingPointId or br.id=s.dropBoardingPointId)"
						+ " left join route r on(r.id=br.routeId) where s.academicYearId= "+academicYearId+" and r.id="+route.getId());
				
				if(!ObjectFunctions.isNullOrEmpty(viewRouteWiseStudentsList))
				{
					route.setRouteWiseStudents(viewRouteWiseStudentsList);
				}
				if(!ObjectFunctions.isNullOrEmpty(allStudentsCount))
					route.setOverAllCount(Long.valueOf(allStudentsCount[0].toString()));
				else
					route.setOverAllCount(0);
				getRouteList().add(route);
				viewRouteWiseStudentsList = null;
			}
		}
		
		//setTotalStudentsCount(allStudentsCount);
		routesList = null;
		
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 27, 2013		 Seshu				Code Refactor. We are also calling this method from 'Admissions' -> 'Admitted Students'. 
 * 										So academic year id value will change based on admission settings.
/********************************************************************/
	@Action(value = "ajaxViewStudentVehicleInfo",  results = { @Result(location = "ajaxViewStudentVehicleInfo.jsp", name = "success") })
	public String ajaxViewVehicleInfo() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewStudentVehicleInfo' method");
		}
		try {
			getRouteListByCustIdandAcademicYearId(getAcademicYearId());
			if(getTempId() > 0)
				setViewStudentPersonAccountDetails((ViewStudentPersonAccountDetails)adminManager.get(ViewStudentPersonAccountDetails.class, "studentId="+getTempId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxEditStudentVehicleInformation", results = { @Result(location = "ajaxViewStudentVehicleInfo.jsp", name = "success") })
	public String ajaxEditStudentVehicleInformation() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxEditStudentVehicleInformation' method");
	}
	try {
		if (getStudent().getId() > 0) {
				setTempId(getStudent().getId());
				long vehicleAcademicDetailsId,boardingPointId=0;
				if(StringFunctions.isNullOrEmpty(getEventId())){
					super.addActionError("Please select vehicle.");
					return SUCCESS;
				}else
				{
					vehicleAcademicDetailsId = Long.valueOf(getEventId());
				}
				if(StringFunctions.isNullOrEmpty(getAnyId())){
					super.addActionError("Please select boarding point.");
					return SUCCESS;
				}else{
					boardingPointId = Long.valueOf(getAnyId());
				}
				if(vehicleAcademicDetailsId > 0 && boardingPointId > 0){
					Student student = (Student) adminManager.get(Student.class,getStudent().getId());
					if(!ObjectFunctions.isNullOrEmpty(student))
					{
						RouteBoardingPoints boardingPoint = (RouteBoardingPoints) adminManager.get(RouteBoardingPoints.class, "id =" +boardingPointId);
						if(!ObjectFunctions.isNullOrEmpty(boardingPoint))
							student.setRouteBoardingPoints(boardingPoint);
						else
							student.setRouteBoardingPoints(null);
							student.setVehicleAcademicDetailsId(vehicleAcademicDetailsId);
							adminManager.save(student);
							super.addActionMessage("Vehicle is successfully assigned to the student");
					}
				}
		}
	} catch (Exception ex) {
		log.error("Entering into 'catch block':" + ex.getMessage());
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}finally{
		ajaxViewVehicleInfo();
	}

	return SUCCESS;
}
	public void prepareStudentsFeeListBysearch(List<ViewStudentClassDetails> studentList, long academicYearId) {
		try {
			ViewStudentFeePaymentDetails feePaymentDetails = null;
			if (!ObjectFunctions.isNullOrEmpty(studentList)) {
				for (ViewStudentClassDetails stdPersonDetail : studentList) {
					/* We have to check the fee with classId wheather the that respective class students fee assigned are not if we configure we will show the make payment else we will show the fee not configured message in student invoice module */
					List<Fee> fees = adminManager.getAll(Fee.class, "custId="+ getUserCustId() + " and academicYearId="+ academicYearId + " and ( classId="+ stdPersonDetail.getClassId()+ " || routeBoardingPointId="+stdPersonDetail.getBoardingPointId()+" ) and feeAmount > 0");
					if (!ObjectFunctions.isNullOrEmpty(fees)) 
					{
						prepareFeeByStudent(stdPersonDetail,academicYearId);
					}
					else {
						feePaymentDetails = new ViewStudentFeePaymentDetails();
						feePaymentDetails.setPaymentStatus("NULL");
						feePaymentDetails.setId(stdPersonDetail.getStudId());
						feePaymentDetails.setAcademicYearId(academicYearId);
						getObjectList().add(feePaymentDetails);
					}
				
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void prepareStudentsFeeList(List<ViewStudentClassDetails> studentList, long academicYearId,long classId) {
		try {
			
			//StringBuffer queryString = null;
			
			if (!ObjectFunctions.isNullOrEmpty(studentList)) {
				log.debug("custId="+ getUserCustId() + " and academicYearId="+ academicYearId + " and ( classId="+ classId+ " || routeBoardingPointId >0 ) and feeAmount > 0");
				List<Fee> fees = adminManager.getAll(Fee.class, "custId="+ getUserCustId() + " and academicYearId="+ academicYearId + " and ( classId="+ classId+ " || routeBoardingPointId >0 ) and feeAmount > 0");
				if (!ObjectFunctions.isNullOrEmpty(fees)) {
				for (ViewStudentClassDetails stdPersonDetail : studentList) 
				{
					prepareFeeByStudent(stdPersonDetail,academicYearId);
				}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	 public void prepareFeeByStudent(ViewStudentClassDetails stdPersonDetail,long academicYearId)
	 {
		 ViewStudentFeePaymentDetails feePaymentDetails = null;
		 StringBuffer buffer = null;
		  List<Object[]> stdObject = null;
			if (!ObjectFunctions.isNullOrEmpty(stdPersonDetail)) {
				buffer = new StringBuffer();
				//queryString = new StringBuffer();
				buffer.append("(1,2");
				if ("T".equalsIgnoreCase(stdPersonDetail.getTransportMode())) {
					
					buffer.append(",3");
				}
				buffer.append(")");
				log.debug("select studentId,paymentStatus,invoiceNumber from vw_studentFeePaymentDetails where academicYearId="+ academicYearId+ " and custId="+ getUserCustId()+ "  and studentId="+ stdPersonDetail.getStudId()+" and paymentStatus='"+ Constants.NO_STRING+ "' and deleteStatus='"+ Constants.NO_STRING+ "' group by paymentStatus");
				stdObject = adminManager.getAll("select studentId,paymentStatus,invoiceNumber,id from vw_studentFeePaymentDetails where academicYearId="+ academicYearId+ " and custId="+ getUserCustId()+ "  and studentId="+ stdPersonDetail.getStudId()+" and paymentStatus='"+ Constants.NO_STRING+ "' and deleteStatus='"+ Constants.NO_STRING+ "' group by studentId,paymentStatus");//and classId="+ stdPersonDetail.getClassId()+ "
				/*double paidAmount=0.0;
				 double totalAmount=0.0;*/
				if (!ObjectFunctions.isNullOrEmpty(stdObject)) {
					for (Object[] str : stdObject) {
						feePaymentDetails = new ViewStudentFeePaymentDetails();
						feePaymentDetails.setPaymentStatus(str[1].toString());
						feePaymentDetails.setId(stdPersonDetail.getStudId());
						feePaymentDetails.setAcademicYearId(academicYearId);
						getObjectList().add(feePaymentDetails);
						str = null;
					}
				} else {
					stdObject = adminManager.getAll("select studentId,paymentStatus,invoiceNumber,id from vw_studentFeePaymentDetails where academicYearId="+ academicYearId+ " and custId="+ getUserCustId()+ "  and studentId="+ stdPersonDetail.getStudId()+" and feeSettingId in "+buffer.toString()+"   and paymentStatus='"+ Constants.PAYMENT_STATUS+ "' and deleteStatus='"+ Constants.NO_STRING+ "' group by studentId,paymentStatus");//and classId="+ stdPersonDetail.getClassId()+ "
					feePaymentDetails = new ViewStudentFeePaymentDetails();
					if (!ObjectFunctions.isNullOrEmpty(stdObject)) 
					{
						feePaymentDetails.setPaymentStatus(Constants.PAYMENT_STATUS);
					}
					else
					{
						feePaymentDetails.setPaymentStatus("NULL");
					}
					feePaymentDetails.setId(stdPersonDetail.getStudId());
					feePaymentDetails.setAcademicYearId(academicYearId);
					getObjectList().add(feePaymentDetails);
				}
				stdObject=null;
				buffer = null;
				stdPersonDetail=null;
			}
	
	 }
	 /**Removed prepareAcademicYearId and use getUserAcademicYearId by venkatesh on 14/05/2013
		 ***/
	 public void ajaxGetAllClassesAndTCSettings() {
			try {
				if (getUserAcademicYearId() > 0) {
					StringBuffer query = null;
					 query = new StringBuffer("custId=").append(getUserCustId());
					if("templateSettings".equalsIgnoreCase(getAnyTitle())){
						query.append(" and type='").append(getAnyTitle()).append("'");
					}
					else
						query.append(" and type='").append(getAnyTitle()).append("'");
					List<TcSettings> tcsettings = adminManager.getAll(TcSettings.class, query.toString());
					if(!ObjectFunctions.isNullOrEmpty(tcsettings)){
						setStudyClassList(new ArrayList<StudyClass>());
						for(TcSettings classNames : tcsettings){
							getStudyClassList().addAll(adminManager.getAll(StudyClass.class, " custId="+getUserCustId()+" and className='"+classNames.getClassNames()+"' and academicYearId="+getUserAcademicYearId()));
						}
					}
					query = null;
				}
				if(!ObjectFunctions.isNullOrEmpty(getStudyClassList())){
					Collections.sort(getStudyClassList());
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		
		
		public void ajaxGetAllClassesStudyAndBonafiedSettings() {
			try {
				if (getUserAcademicYearId() > 0) {
					StringBuffer query = new StringBuffer("custId=").append(getUserCustId());
					if("studyCertificate".equals(getAnyTitle()))
						query.append(" and filePath is not null and certificateType='SC'");
					else if("bonafiedCertificate".equals(getAnyTitle()))
						query.append(" and filePath is not null and certificateType='BC'");
					else if("noDuesCertificate".equals(getAnyTitle()))
						query.append(" and filePath is not null and certificateType='ND'");
					else if("feeCertificate".equals(getAnyTitle()))
						query.append(" and filePath is not null and certificateType='FC'");
					List<StudyAndBonafiedSettings> ObjectList = adminManager.getAll(StudyAndBonafiedSettings.class, query.toString());
					if(!ObjectFunctions.isNullOrEmpty(ObjectList)){
						setStudyClassList(new ArrayList<StudyClass>());
						for(StudyAndBonafiedSettings studyAndBonified : ObjectList){
							StudyClass className=(StudyClass)adminManager.get(StudyClass.class," custId="+getUserCustId()+" and id="+studyAndBonified.getStudyClassId()+" and academicYearId="+getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(className)){
								getStudyClassList().add(className);
							}
							className = null;
						}
						Collections.sort(getStudyClassList());
					}
					query = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		
	public void prepareSchoolFeeSettingList()
	{
		StringBuffer queryString = new StringBuffer();
		if(getUser().isSchoolHostel() || getUser().isHostelFinance()){
			queryString.append("status in ('"+Constants.HOSTEL_STATUS+"')");
		}else if (getUser().isSchoolTransport() || getUser().isTransportFinance()) {
			queryString.append("status in ('"+Constants.TRANSPORT_STATUS+"')");
		}else {
			if (!ObjectFunctions.isNullOrEmpty(getCustomerByCustId())) {
				queryString.append("status in ('"+Constants.SCHOOL_MODULE+"'");
				if (getCustomerByCustId().isTransportModuleStatus() && getCustomerByCustId().isHostelModuleStatus()) {
					queryString.append(",'"+Constants.TRANSPORT_STATUS+"','"+Constants.HOSTEL_STATUS+"'");
				} else if (getCustomerByCustId().isTransportModuleStatus()) {
					queryString.append(",'"+Constants.TRANSPORT_STATUS+"'");
				} else if (getCustomerByCustId().isHostelModuleStatus()){
					queryString.append(",'"+Constants.HOSTEL_STATUS+"'");
				}
				queryString.append(")");
			}
		}
		log.debug(queryString.toString());
		List<SchoolFeeSetting> feeSettingList=adminManager.getAll(SchoolFeeSetting.class,queryString.toString());
		StringBuffer feeSettingIds=new StringBuffer();
		if (!ObjectFunctions.isNullOrEmpty(feeSettingList)) {
			if (getTempId() == 0) {
				if (!ObjectFunctions.isNullOrEmpty(feeSettingList)) {
					SchoolFeeSetting feeSetting = feeSettingList.get(0);
					setTempId(feeSetting.getId());
					setTitle(feeSetting.getSettingName());
				}
			}else {
				setSchoolFeeSetting((SchoolFeeSetting)adminManager.get(SchoolFeeSetting.class, getTempId()));
			}
			feeSettingIds.append("(");
			for(SchoolFeeSetting feeSetting: feeSettingList){
				feeSettingIds.append(feeSetting.getId()+",");
			}
			feeSettingIds.append("0)");
			setAnyTitle(feeSettingIds.toString());
			setObjectList(feeSettingList);
		}
	}

	public void prepareStudentSchoolFeeSettingList(Student student) {
		StringBuffer queryString = new StringBuffer();
		int count = adminManager.getCount("studentTransportDetails", "custId="+student.getCustId()+" and academicYearId="+student.getAcademicYearId()+" and studentId="+student.getId());
		if(getUser().isSchoolHostel() || getUser().isHostelFinance()){
			if(!ObjectFunctions.isNullOrEmpty(student.getBed()))
			queryString.append("status in ('"+Constants.HOSTEL_STATUS+"')");
		}else if (count>0 && getUser().isSchoolTransport() || getUser().isTransportFinance()) {
			queryString.append("status in ('"+Constants.TRANSPORT_STATUS+"')");
		}else {
			if (!ObjectFunctions.isNullOrEmpty(student)) {
				queryString.append("status in ('" + Constants.SCHOOL_MODULE+ "'");
				if (count>0 && !ObjectFunctions.isNullOrEmpty(student.getBed())) {
					queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
					if(student.getBed().getId() !=0)
						queryString.append(",'" + Constants.HOSTEL_STATUS + "'");
				}if (count>0) {
					queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
				//}else if (!ObjectFunctions.isNullOrEmpty(student.getBed())) { change the below line code for hostel by cvs 12-3-2014.
				}if (StringFunctions.isNotNullOrEmpty(student.getHostelMode()) && "H".equalsIgnoreCase(student.getHostelMode().trim())) {
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
	public void prepareAdmissionApplicantStudentSchoolFeeSettingList(OnlineApplicationDetails onlineApplicationDetails) {
		StringBuffer queryString = new StringBuffer();
		if (!ObjectFunctions.isNullOrEmpty(onlineApplicationDetails)) {
				queryString.append("status in ('" + Constants.SCHOOL_MODULE+ "'");
				if (Constants.TRANSPORT_STATUS.equalsIgnoreCase(onlineApplicationDetails.getTransportMode())&& "H".equalsIgnoreCase(onlineApplicationDetails.getHostelMode())) {
					queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
					if("H".equalsIgnoreCase(onlineApplicationDetails.getHostelMode()))
						queryString.append(",'" + Constants.HOSTEL_STATUS + "'");
				}else if (Constants.TRANSPORT_STATUS.equalsIgnoreCase(onlineApplicationDetails.getTransportMode())) {
					queryString.append(",'" + Constants.TRANSPORT_STATUS + "' ");
				}else if ("H".equalsIgnoreCase(onlineApplicationDetails.getHostelMode())) {
					queryString.append(",'" + Constants.HOSTEL_STATUS + "'");
				}
				queryString.append(")");
		}
		List<SchoolFeeSetting> feeSettingList= adminManager.getAll(SchoolFeeSetting.class,queryString.toString());
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
	public void doPreparestudentFeeList(List<Object[]> studentIds,long schoolTermId, String todaydateinStr) {
		ViewStudentFeePaymentDetails feePaymentDetails = null;
		double discAmount = 0;
		double totalPaidAmount = 0;
		double discountAmount = 0;
		if (!ObjectFunctions.isNullOrEmpty(studentIds)) {
			AcademicYear academicYear = getCurrentAcademicYear();
			for (Object[] obj : studentIds) {
				String studentId = obj[0].toString();
				if (Long.valueOf(studentId) != 0) {
					ViewStudentClassDetails studentClassDetails = (ViewStudentClassDetails) adminManager.get(ViewStudentClassDetails.class, "studId="+ Long.valueOf(studentId));
					if (!ObjectFunctions.isNullOrEmpty(studentClassDetails)) {
						setAnyTitle(studentClassDetails.getClassAndSection());
						prepareFeeIds(schoolTermId, studentClassDetails.getClassId(),studentClassDetails.getCategoryId(),studentClassDetails.getBoardingPointId(),academicYear.isTransportFeeByBoardingPoint());
						List<Object[]> daysBetweenPaidlists = adminManager.getClassFeeTermsByStudentIdAndStatusAndCurrentDate("vw_studentFeePaymentDetails",studentClassDetails.getStudId(),getUserCustId(), getUserAcademicYearId(),studentClassDetails.getClassId(),todaydateinStr, getAnyId(), Long.valueOf(obj[1].toString()));
						//discAmount = adminManager.getStudentDiscountAmountByClassId("vw_studentFeePaymentDetails",studentClassDetails.getStudId(),studentClassDetails.getClassId(),getUserCustId(), getUserAcademicYearId(),getAnyId(), Long.valueOf(obj[1].toString()));
						if (!ObjectFunctions.isNullOrEmpty(daysBetweenPaidlists)) {
							for (Object[] obj1 : daysBetweenPaidlists) {
								//String payAmount=obj1[0].toString()
								double payAmount =Double.valueOf(obj1[0].toString());
								discAmount = Double.valueOf(obj1[16].toString());
								if (payAmount != 0) {
									Date paymentDate = (Date) obj1[1];
									feePaymentDetails = new ViewStudentFeePaymentDetails();
									totalPaidAmount += payAmount;
									if (discAmount != 0.0) {
										discountAmount += discAmount;
										feePaymentDetails.setDiscountAmount(discAmount);
									} else {
										feePaymentDetails.setDiscountAmount(discAmount);
									}
									feePaymentDetails.setPaymentAmount(payAmount);
									feePaymentDetails.setPaymentDate(paymentDate);
									feePaymentDetails.setStudentPaymentId(Long.parseLong(obj1[11].toString()));
									feePaymentDetails.setRollNumber(obj1[3].toString());
									if(!ObjectFunctions.isNullOrEmpty(obj1[5])){
										feePaymentDetails.setFirstName(obj1[4].toString()+ ' '+ obj1[5].toString());
									}else{
										feePaymentDetails.setFirstName(obj1[4].toString());
									}
									feePaymentDetails.setClassName(getAnyTitle());
									feePaymentDetails.setTermName(obj1[7].toString());
									feePaymentDetails.setFeeType(obj1[8].toString());
									if (!ObjectFunctions.isNullOrEmpty(obj1[6])) {
										if ("C".equalsIgnoreCase(obj1[6].toString())) {
											feePaymentDetails.setPaymentType("Cash");
										} else if ("D".equalsIgnoreCase(obj1[6].toString())) {
											if (!ObjectFunctions.isNullOrEmpty(obj1[9]))
												feePaymentDetails.setPaymentType("DD"+ "("+ obj1[9].toString()+ ")");

										} else if ("CH".equalsIgnoreCase(obj1[6].toString())) {
											if (!ObjectFunctions.isNullOrEmpty(obj1[10]))
												feePaymentDetails.setPaymentType("Cheque"+ "("+ obj1[10].toString()+ ")");
										}
									}
									feePaymentDetails.setAdmissionNumber(obj1[14].toString());
									feePaymentDetails.setFeeSettingId(Long.valueOf(obj1[12].toString()));
									feePaymentDetails.setSettingName(obj1[13].toString());
									feePaymentDetails.setPaymentConcessionAmount(Double.valueOf(obj1[18].toString()));
									getStudentsFeeTypeList().add(feePaymentDetails);
								}
								//payAmount = null;
								feePaymentDetails = null;
							}
							setTotalAmount(totalPaidAmount);
							// -// discountAmount
							setTempString(String.valueOf(discountAmount));
						}
						daysBetweenPaidlists = null;
						studentClassDetails = null;
						discAmount = 0;
					}
				}
			}
		}
	}
//Modified by Balu For Class And Section and date formate
	public void doGetclassWiseDefaultstudentsFeeList(SchoolTerms schoolTerm,String todaydateinStr,AcademicYear academicYear) {// ,Map<Long,StringBuffer> // studTermWiseMesgDetails
		// ViewStudentClassDetails studentClassDetails = null;
		double totalUnPaidAmount = 0;
		double totalPaidAmount = 0;
		double totalStuPaidAmount = 0;
		double stuentUnPaidAmount = 0;
		double stuDiscountAmount = 0;
		double discountAmount = 0;
		double concessionAmount = 0;
		double totalAmountDue = 0;
		double totalDiscountAmount = 0;
		double totalTermAmount = 0;
		double totalDeductedAmount = 0;
		double totalDuePaymentAmount = 0;
		List<ViewStudentFeePaymentDetails> feeUnpaidList = null;
		if (Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerm.getFeeSetting().getSettingName())) {
			feeUnpaidList = adminManager.getStudentFeeUnpaidDetails("vw_studentTransportFeeParticularsPayment",getUserCustId(), getUserAcademicYearId(),"("+String.valueOf(schoolTerm.getId())+")", getClassId());
		} else {
			feeUnpaidList = adminManager.getStudentFeeUnpaidDetails("vw_studentFeePaymentDetails", getUserCustId(),getUserAcademicYearId(), "("+String.valueOf(schoolTerm.getId())+")", getClassId());
		}

		if (!ObjectFunctions.isNullOrEmpty(feeUnpaidList)) {
			for (ViewStudentFeePaymentDetails studentFeePaymentDetails : feeUnpaidList) {
				ViewStudentFeePaymentDetails feePaymentDetails = new ViewStudentFeePaymentDetails();
				feePaymentDetails.setId(studentFeePaymentDetails.getStudentId());
				feePaymentDetails.setFirstName(studentFeePaymentDetails.getFullName());
				feePaymentDetails.setClassName(studentFeePaymentDetails.getClassAndSection());
				feePaymentDetails.setRollNumber(studentFeePaymentDetails.getRollNumber());
				feePaymentDetails.setAdmissionNumber(studentFeePaymentDetails.getAdmissionNumber());
				feePaymentDetails.setPhoneNumber(studentFeePaymentDetails.getMobileNumber());
				// For Class And Section
				feePaymentDetails.setFeeType(studentFeePaymentDetails.getClassAndSection());
				feePaymentDetails.setTermName(schoolTerm.getTermName());
				feePaymentDetails.setDueDate(schoolTerm.getDueDate());
				// For date displaying in String formate
				feePaymentDetails.setDescription(schoolTerm.getDueDateStr());
				feePaymentDetails.setFeeAmount(studentFeePaymentDetails.getFeeAmount()- studentFeePaymentDetails.getConcessionAmount());
				feePaymentDetails.setPaymentAmount(studentFeePaymentDetails.getPaidAmount());
				feePaymentDetails.setDiscountAmount(studentFeePaymentDetails.getDiscountAmount());
				feePaymentDetails.setFeeSettingId(schoolTerm.getFeeSetting().getId());
				feePaymentDetails.setSettingName(schoolTerm.getFeeSetting().getSettingName());
				getStudentsFeeTypeList().add(feePaymentDetails);
				getDisplayAttendanceSet().add(studentFeePaymentDetails.getStudentId());
				totalAmountDue += (studentFeePaymentDetails.getFeeAmount() - studentFeePaymentDetails.getConcessionAmount());// student term total amount
				totalDuePaymentAmount += studentFeePaymentDetails.getPaidAmount();// total due term paid amount
				totalStuPaidAmount += studentFeePaymentDetails.getPaymentAmount();// total term paid amount
				stuDiscountAmount += studentFeePaymentDetails.getDiscountAmount();
			}
			setThirtyTotalAmount(totalAmountDue);// all students term total amount
			setThirtyto60totalAmount(totalStuPaidAmount); // all students total paid term paid amount

			setFourteenTotalAmount(stuDiscountAmount);// all students total term due amount
			setPaymentAmount(totalDuePaymentAmount); // this is total due amount
		}
	}
	 	
	/**
	 * Return StudyClasses list by classNameClassId with sort by section name.
	 * @param classNameId is an ClassName.java of id (class table id) column
	 * @return List<StudyClass> list  
	*/
	public List<StudyClass> getAllStudyClassesByClassNameId(long classNameId){
		List<StudyClass> studyClasses=adminManager.getAll(StudyClass.class,"classNameClassId="+classNameId);
		if(ObjectFunctions.isNotNullOrEmpty(studyClasses))
			Collections.sort(studyClasses);
		return studyClasses;
	}
	
	/**
	 * Returns ExamTypes list with sort by examType sorting order
	 * If sorting values not available sorts based on ExamType name.
	 * <p>
	 * If we pass classId param it returns ExamTypes list based on ClassName table id column
	 * If we pass classSectionId param it returns ExamTypes list based on classSectionId table id column
	 * Other than these two conditions it returns ExamTypes list based on academicYearId
	*/
	public List<ExamTypes> getAllExamTypesByClassId(long classId,long classSectionId,long custId,long academicYearId){
		List<ExamTypes> examTypes = null; 
		if(classId > 0){
			StudyClass studyClass = (StudyClass)adminManager.get(StudyClass.class, " classNameClassId="+classId);
			if(!ObjectFunctions.isNullOrEmpty(studyClass) && ObjectFunctions.isNotNullOrEmpty(studyClass.getExamTypes()))
				examTypes = ConvertUtil.convertSetToList(studyClass.getExamTypes());
		}
		else if(classSectionId > 0){
			examTypes=adminManager.getExamTypesByStudyClassId(classSectionId);			
		}
		else{
			examTypes = adminManager.getAll(ExamTypes.class, "custId="+custId+" and academicYearId="+academicYearId);
		}
		// We already sorting the list if classSectionId exists.
		if(ObjectFunctions.isNotNullOrEmpty(examTypes) && classSectionId<=0)
			Collections.sort(examTypes);
		return examTypes;
	}

	/**
	 * Returns Marks uploaded ExamTypes list with sort by examType sorting order
	 * If sorting values not available sorts based on ExamType name.
	 * <p>
	 * If we pass classId param it returns ExamTypes list based on ClassName table id column
	 * If we pass classSectionId param it returns ExamTypes list based on classSectionId table id column
	 * Other than these two conditions it returns ExamTypes list based on academicYearId
	*/
	public List<ExamTypes> getMarksUploadedExamTypesByClassId(long classSectionId){
		StringBuffer query = new StringBuffer("select examTypeId from vw_examTypesSchedules where ");
		if(classSectionId > 0){
			query.append("classSectionId=");
			query.append(classSectionId);
		
		query.append(" and custId="+getUserCustId());
		query.append(" and  academicYearId="+getUserAcademicYearId());
		query.append(" group by examTypeId");
		List<BigInteger> examTypeIds = adminManager.getAll(query.toString());
		query = null;
		if(ObjectFunctions.isNotNullOrEmpty(examTypeIds)){
			String uploadedExamTypeIds = org.springframework.util.StringUtils.collectionToCommaDelimitedString(examTypeIds);
			List<ExamTypes> examTypes = adminManager.getAll(ExamTypes.class, "id in("+uploadedExamTypeIds+")");
			examTypeIds = null;
			uploadedExamTypeIds = null;
			if(ObjectFunctions.isNotNullOrEmpty(examTypes)){
				Collections.sort(examTypes);
				return examTypes;
			}
		 }
		}
		return null;
	}
	
	public void checkStudyClassHavingStudentsOrNot() 
	{
		 if("ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole())){
			List<BigInteger> studyClassIdsList = null;
			Object[] staff = adminManager.get("select id,description from staff where accountId="+getUser().getId()+" and  status='Y'");
			Long staffId = null;
			if(!ObjectFunctions.isNullOrEmpty(staff))
			{
				if(!ObjectFunctions.isNullOrEmpty(staff[0]))
				{
					staffId = Long.valueOf(staff[0].toString());
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(staffId)){
				StringBuffer query = new StringBuffer("select studyClassId from classTeacher where teacherId in(").append(staffId+")");
				List<BigInteger> studyClassIds = adminManager.getAll(query.toString());
				StringBuffer sqlQuery = new StringBuffer("select st.id  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
				.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+getUserAcademicYearId()).append(" and sm.staffId=").append(staffId);

				List<BigInteger> coOrdinatorStudyClassIds = adminManager.getAll(sqlQuery.toString());
				if (ObjectFunctions.isNotNullOrEmpty(studyClassIds) && ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
					for(BigInteger obj:coOrdinatorStudyClassIds){
						if(!studyClassIds.contains(obj)){
							studyClassIds.add(obj);
						}
					}
					studyClassIdsList = studyClassIds;
				}else if (ObjectFunctions.isNotNullOrEmpty(studyClassIds)) {
					studyClassIdsList = studyClassIds;
				}else if (ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
					studyClassIdsList = coOrdinatorStudyClassIds;
				} 
				if (ObjectFunctions.isNotNullOrEmpty(studyClassIdsList)) 
				{
					String classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(studyClassIdsList);
					List<StudyClass> studyClassList = adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(),classSectionIdsString);
					if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) 
					{
						int studentsCount=0;
						int count=0;
						for (StudyClass studyClass : studyClassList) 
						{
							log.debug(studyClass.getClassName());
							studentsCount = studentManager.getClassStudentsCountByClassId(studyClass.getId(),getUserCustId());
							if(studentsCount > 0 ){
								if(user.isSchoolAsstStaff()){
									count=adminManager.getCount("vw_classSectionTeacher","custId="+getUserCustId()+" and accountId="+getUser().getId()+ " and classSectionId="+studyClass.getId());
									if(count> 0)
										getStudyClassList().add(studyClass);
								}else
									getStudyClassList().add(studyClass);
						}else
						getTempList2().add(studyClass);
						}
					}
				}
			}
		}else{
			log.debug(getPlTitle());
			log.debug("User Id:" + getUser().getId());
	 		List<StudyClass> studyClassList = adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(),null);
			if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) 
			{
				int studentsCount=0;
				int count=0;
				for (StudyClass studyClass : studyClassList) 
				{
					log.debug(studyClass.getClassName());
					studentsCount = studentManager.getClassStudentsCountByClassId(studyClass.getId(),getUserCustId());
					if(studentsCount > 0 ){
						if(user.isSchoolAsstStaff()){
							count=adminManager.getCount("vw_classSectionTeacher","custId="+getUserCustId()+" and accountId="+getUser().getId()+ " and classSectionId="+studyClass.getId());
							if(count> 0)
								getStudyClassList().add(studyClass);
						}else
						 getStudyClassList().add(studyClass);
					}else
						getTempList2().add(studyClass);
					}
			}
		}
	}
	
	/** Code Modified by seshu on April 18th. To set class with students and class without students classes list.
	 */
	
	public boolean generateFeeTypes(AcademicYear futureAcademicYear,long accountId,AcademicYear presentAcademicYear){
		try {
			List<FeeType> newFeeTypeList=adminManager.getAll(FeeType.class, " custId="+getUserCustId()+" and academicYearId="+futureAcademicYear.getId());
			List<FeeType> currentYearFeeTypes=adminManager.getAll(FeeType.class, " custId="+getUserCustId()+" and academicYearId="+presentAcademicYear.getId());
			if(ObjectFunctions.isNotNullOrEmpty(currentYearFeeTypes)){
				FeeType feeType = null;
				for(FeeType currentFeeType: currentYearFeeTypes){
					feeType=(FeeType)adminManager.get(FeeType.class,"feeType='"+currentFeeType.getFeeType()+"' and academicYearId="+futureAcademicYear.getId());
					/*Ganesh - We should no check newFeeTypeList because If we have more than one fee Type when we are going to create first fee type it will create from secong fee type on wards it will not create because we will get fee type list with future academic year. */
					if(ObjectFunctions.isNullOrEmpty(feeType)){// && ObjectFunctions.isNullOrEmpty(newFeeTypeList)
						feeType=new FeeType();
						feeType.setCreatedById(accountId);
						feeType.setCreatedDate(new Date());
						feeType.setCustId(getUserCustId());
						//feeType.setDescription(currentFeeType.getDescription());
						feeType.setStatus(currentFeeType.getStatus());
						feeType.setFeeType(currentFeeType.getFeeType());
						feeType.setLastAccessDate(new Date());
						feeType.setAcademicYear(futureAcademicYear);
						feeType.setFeeSettingId(currentFeeType.getFeeSettingId());
						adminManager.save(feeType);
					}
					feeType=null;
				}
			}
			currentYearFeeTypes = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return false;
		}
		return true;
	} 
	public boolean generateFeeTerms(AcademicYear futureAcademicYear,long accountId,AcademicYear presentAcademicYear){
		try {
			SchoolTerms newSchoolTerms=null;
			SchoolTerms schoolTerm = null;
			List<SchoolTerms> newSchoolTermsList=adminManager.getAll(SchoolTerms.class, " custId="+getUserCustId()+" and academicYearId="+futureAcademicYear.getId());
			List<SchoolTerms> oldSchoolTermsList=adminManager.getAll(SchoolTerms.class, " custId="+getUserCustId()+" and academicYearId="+presentAcademicYear.getId());
			if(!ObjectFunctions.isNullOrEmpty(oldSchoolTermsList)){
				for(SchoolTerms schoolTerms:oldSchoolTermsList){
					schoolTerm=(SchoolTerms)adminManager.get(SchoolTerms.class,"termName='"+schoolTerms.getTermName()+"' and academicYearId="+futureAcademicYear.getId());
					/*Ganesh - We should no check newSchoolTermsList because If we have more than one term when we are going to create first term it will create from secong term on wards it will not create because we will get term list with future academic year. */
					if(ObjectFunctions.isNullOrEmpty(schoolTerm)){// && ObjectFunctions.isNullOrEmpty(newSchoolTermsList)
						Calendar date = Calendar.getInstance();  
						newSchoolTerms=new SchoolTerms();
						newSchoolTerms.setCreatedById(getUser().getId());
						newSchoolTerms.setCreatedDate(new Date());
						newSchoolTerms.setCustomer(getCustomerByCustId());
						newSchoolTerms.setDescription(schoolTerms.getDescription());
						newSchoolTerms.setTermName(schoolTerms.getTermName());
						newSchoolTerms.setStatus(schoolTerms.getStatus());
						newSchoolTerms.setFromMonthName(schoolTerms.getFromMonthName());
						newSchoolTerms.setToMonthName(schoolTerms.getToMonthName());
						newSchoolTerms.setAcademicYear(futureAcademicYear);
						newSchoolTerms.setNoOfDays(schoolTerms.getNoOfDays());
					//	newSchoolTerms.setMailContentDesc(schoolTerms.getMailContentDesc());
					//	newSchoolTerms.setMobileContentDesc(schoolTerms.getMobileContentDesc());
						newSchoolTerms.setFeeSetting(schoolTerms.getFeeSetting());
						date.setTime(schoolTerms.getFromDate());
						date.add(Calendar.YEAR,1);
						newSchoolTerms.setFromDate(date.getTime());
						date.setTime(schoolTerms.getToDate());
						date.add(Calendar.YEAR,1);
						newSchoolTerms.setToDate(date.getTime());
						date.setTime(schoolTerms.getDueDate());
						date.add(Calendar.YEAR,1);
						newSchoolTerms.setDueDate(date.getTime());
						adminManager.save(newSchoolTerms);
						newSchoolTerms = null;
					}
				}
			}
			newSchoolTermsList = null;
			oldSchoolTermsList = null;
			schoolTerm = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return false;
		}
		return true;
	}
	
	public String getAdmissionsOnlineApplicationDetails() {
		try {
			getSession().removeAttribute("admissionAcademicYearId");
			getSession().removeAttribute("admissionSettingId");
			setAcademicYearList(getAdmissionAcademicYears());
			setAdmissionSettings((AdmissionSettings)adminManager.get(AdmissionSettings.class, "custId="+getUserCustId()+" and status='"+Constants.YES_STRING+"'"));
			if(!ObjectFunctions.isNullOrEmpty(getAdmissionSettings())){
				setTempId2(getAdmissionSettings().getAcademicYearId());
			}
			String status=getParamValue("status");
			if("Y".equalsIgnoreCase(status)){
				super.addActionMessage("Admission settings updated successfully.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	public void prepareStudentsDetailsList() {
		try {
			long academicYearId=0;
			if(getAcademicYearId()>0){
				academicYearId=getAcademicYearId();
			}else{
				academicYearId=getUserAcademicYearId();
			}
			int count = 1;
			List<Object[]> studentsCount = null;
			if (academicYearId > 0) {
				if(count > 0)
				{
					//setAnyId(getFeeDDNumber());
					//log.debug("Fee Query"+ queryString.toString());
					Map<String,String> params=new HashMap();
					if (!StringFunctions.isNullOrEmpty(getAnyTitle()))
					{
						String searchStr = getAnyTitle().trim().replaceAll("'","");
						searchStr = searchStr.replaceAll(" ", "");
						//params.put("name", getAnyTitle().trim().replaceAll("'",""));	
						params.put("name", searchStr);
					}
					/*else
					{
						params.put("name", getAnyTitle().trim());
					}*/
					if (!ObjectFunctions.isNullOrEmpty(getStaff()) && getUser().isOnlySchoolTeacher()) {
						StringBuffer query = new StringBuffer("select studyClassId from classTeacher where teacherId in(").append(getStaff().getId()+")");
						List<BigInteger> studyClassIds = adminManager.getAll(query.toString());
						if (ObjectFunctions.isNotNullOrEmpty(studyClassIds)) {
						String classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(studyClassIds);
							if(StringFunctions.isNotNullOrEmpty(classSectionIdsString)){
								params.put("classSectionId", classSectionIdsString);
								params.put("staffClassStudents", "true");
							}
						}
					}else if(!ObjectFunctions.isNullOrEmpty(getStaff()) && getUser().isAdminCoordinator()){
						StringBuffer query = new StringBuffer("select studyClassId from classTeacher where teacherId in(").append(getStaff().getId()+")");
						List<BigInteger> studyClassIds = adminManager.getAll(query.toString());
						String classSectionIdsString  = null;
						StringBuffer sqlQuery = new StringBuffer("select st.id  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
						.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+academicYearId).append(" and sm.staffId=").append(getStaff().getId());

						List<BigInteger> coOrdinatorStudyClassIds = adminManager.getAll(sqlQuery.toString());
						if (ObjectFunctions.isNotNullOrEmpty(studyClassIds) && ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
							for(BigInteger obj:coOrdinatorStudyClassIds){
								if(!studyClassIds.contains(obj)){
									studyClassIds.add(obj);
								}
							}
							classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(studyClassIds);
						}else if (ObjectFunctions.isNotNullOrEmpty(studyClassIds)) {
							classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(studyClassIds);

						}else if (ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
							classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(coOrdinatorStudyClassIds);
						} 
						if(StringFunctions.isNotNullOrEmpty(classSectionIdsString)){
							params.put("classSectionId", classSectionIdsString);
							params.put("staffClassStudents", "true");
						}
					}
					else{
						params.put("classSectionId", getClassSectionId());
						params.put("admissionNumber", getFeeDDNumber());
						params.put("schoolTransport", getUser().getIsSchoolTransport());
						params.put("schoolHostler", getUser().getIsSchoolHostel());
						params.put("transportFinance", getUser().getIsTransportFinance());
						params.put("Upload",getAnyId());//added this for showing no image students--------rama
						if (!StringFunctions.isNullOrEmpty(getClassId())){
							params.put("classSectionId", getClassId());
						}
					}
					if (!StringFunctions.isNullOrEmpty(getSelectedId())){
						params.put("admissionNumber", getSelectedId().trim());
						params.put("manageStudent", getTempString3());
						if (!StringFunctions.isNullOrEmpty(params.get("manageStudent"))) {
							
							setObjectList(adminManager.getAll(ViewStudentClassDetails.class, "custId="+getUserCustId()+" and (admissionNumber like '%"+params.get("admissionNumber")+"%') and studDiscontinueDesc is not null  order by classId,classSectionId "));
						}
					}
					setStudentsList(studentManager.GetStudentClassDetailsList(getUserCustId(), academicYearId, params));
					AcademicYear academicYear = getCurrentAcademicYear();
					setFeeModuleUsege(academicYear.getFeeModuleUsegeBy());
					if (!ObjectFunctions.isNullOrEmpty(getStudentsList()) && !ObjectFunctions.isNullOrEmpty(getClassId()) ) {
							studentsCount = studentManager.getAll("select SUM(IF(gender='M',1,0))  as maleCount,SUM(IF(gender='F',1,0))  as femaleCount from vw_studentClassDetails where classSectionId="+ getClassId()+ " and  custId="+ getUserCustId()+ " and academicYearId="+ academicYearId + " and studDiscontinueDesc is null  ");
						if (!ObjectFunctions.isNullOrEmpty(studentsCount)) {
							for (Object[] obj1 : studentsCount) {
								if (!ObjectFunctions.isNullOrEmpty(obj1)) {
									int totalCount = getStudentsList().size();
									String boysCount = obj1[0].toString();
									String  girlsCount = obj1[1].toString();
									setTempString(boysCount);
									setTempId1(Long.valueOf(girlsCount));
									int TotalUnAssignedCount = totalCount- (Integer.valueOf(boysCount)+Integer.valueOf(girlsCount));
									setTempId2(Long.valueOf(TotalUnAssignedCount));
								}
							}
						}
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(getStudentsList())){
					if(getStudentsList().size()== 0 && Long.valueOf(getClassId()) > 0){
						setAlertSendType("classWiseStudents");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	
	// Adding method By Narahari at 24/04/2013 for reduced performence issue
	public List getAllHolidaysListByAcademicYearId(String status,long customerId,long academicYearId, long classNameClassId)
	{
		List<SchoolHolidays> holidayBoardList=null;
		if(classNameClassId<=0)
			holidayBoardList = (List) getSession().getAttribute("GetAllHolidaysListByAcademicYearId");
		if(ObjectFunctions.isNullOrEmpty(holidayBoardList))
		{
			//holidayBoardList = adminManager.getAllHolidaysListByAcademicYearId(status, customerId,academicYearId,classNameClassId);
			holidayBoardList = adminManager.getSchoolHolidaysListByDatesAndCustId(customerId,academicYearId,null,null,null,String.valueOf(classNameClassId),null,status,0,"sessionHolidays",null);
			
			if(!ObjectFunctions.isNullOrEmpty(holidayBoardList))
			{
				 getSession().setAttribute("GetAllHolidaysListByAcademicYearId",holidayBoardList);
				 Collections.sort(holidayBoardList);
			}
		}
		
		return holidayBoardList;
	}
	/**Changed by seshu on 26th April. */
	public void getCustomerAndStudyClassAndAcademicYear(){
		try {
			setCustomer(getCustomerByCustId());
			setStudyClassList(adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(),null));
			setAcademicYear((AcademicYear)adminManager.get(AcademicYear.class,"id="+getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}	
	public String manageAdmissionStudentFee() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'manageAdmissionStudentFee' method");
		}
		try {
			if(getTempId2() > 0){
				long receiptNumber=0;
				String paymentStatus = getParamValue("paymentStatus");
				Customer customer = getCustomerByCustId();
				setAnyTitle(String.valueOf(getTempId2()));
				setTodayDate(DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, new Date()));
				setTempList(adminManager.getAll(BankMaster.class));
				if(getTempId1() > 0){
					setOnlineApplicationDetails((OnlineApplicationDetails)adminManager.get(OnlineApplicationDetails.class, getTempId1()));
					if (!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())) {
						prepareSchoolFeeSettingList();
						setClassFeeList(adminManager.getAll(ViewOnlineApplicationStudentClassFees.class, "custId ="+getUserCustId()+" and academicYearId="+getOnlineApplicationDetails().getAcademicYear().getId()+" and classId="+getOnlineApplicationDetails().getClassId().getId()+"  and feeSettingId in "+getTempString()+"  group by termName order by feeSettingId,schoolTermId,feeId"));
						setSchoolFeeList(adminManager.getAll(ViewOnlineApplicationStudentClassFees.class, "custId ="+getUserCustId()+" and academicYearId="+getOnlineApplicationDetails().getAcademicYear().getId()+" and classId="+getOnlineApplicationDetails().getClassId().getId()+"  and feeSettingId in "+getTempString()+" order by feeSettingId"));
					}
					if(customer.isAcademicWiseFeeReceipt())
						receiptNumber=adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),getOnlineApplicationDetails().getAcademicYear().getId());
					else
						receiptNumber=adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),0);
				}
				else if(StringFunctions.isNotNullOrEmpty(getAnyId())){
					ViewStudentFeePaymentDetails paymentDetails=null;
					setStudent((Student)adminManager.get(Student.class, "id="+getAnyId()));
					setAnyId(null);
					prepareStudentSchoolFeeSettingList(getStudent());
					if(!ObjectFunctions.isNullOrEmpty(getStudent())){
						setClassFeeList(adminManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and academicYearId="+ getStudent().getAcademicYearId()+" and classId="+getStudent().getClassNameClassId().getId()+" and categoryId="+getStudent().getCategoryId()+"  and feeSettingId in "+getTempString()+" group by schoolTermId order by feeSettingId,schoolTermId,feeTypeId"));
						//setSchoolFeeList(adminManager.getAll(ViewStudentClassFeePaymentDetails.class, " studentId="+getStudent().getId()+" and custId="+getUserCustId()+" and deleteStatus='"+Constants.YES_STRING+"' and academicYearId="+ getStudent().getAcademicYearId()+" and categoryId="+getStudent().getCategoryId()+" and feeSettingId in "+getTempString()+" order by feeSettingId"));
						List<Object[]> studentFeeIds=adminManager.getAll("select feeId,schoolTermId from vw_studentClassFees where studentId="+getStudent().getId()+" and feeSettingId in "+getTempString());
						if(ObjectFunctions.isNotNullOrEmpty(studentFeeIds)){
							for (Object[] obj : studentFeeIds) {
								paymentDetails=new ViewStudentFeePaymentDetails();
								List<Object[]> studentPaymentList=adminManager.getAll("select id,feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount),sum(discountAmount) from vw_studentFeePaymentDetails where studentId="+getStudent().getId()+" and feeId="+Long.valueOf(obj[0].toString())+" and custId="+getUserCustId()+" and academicYearId="+getStudent().getAcademicYearId()+" and deleteStatus='"+Constants.NO_STRING+"'");
								log.debug("select id,feeSettingId,settingName,schoolTermId,termName,feeTypeId,feeType,feeId,feeAmount,sum(paymentAmount),sum(discountAmount) from vw_studentFeePaymentDetails where studentId="+getStudent().getId()+" and feeId="+Long.valueOf(obj[0].toString())+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and deleteStatus='"+Constants.NO_STRING+"'");
								if(ObjectFunctions.isNotNullOrEmpty(studentPaymentList)){
									for (Object[] feeDetails : studentPaymentList) {
										paymentDetails.setFeeSettingId(Long.valueOf(feeDetails[1].toString()));
										paymentDetails.setSettingName(feeDetails[2].toString());
										paymentDetails.setSchoolTermId(Long.valueOf(feeDetails[3].toString()));
										paymentDetails.setTermName(feeDetails[4].toString());
										paymentDetails.setFeeTypeId(Long.valueOf(feeDetails[5].toString()));
										paymentDetails.setFeeType(feeDetails[6].toString());
										paymentDetails.setFeeId(Long.valueOf(feeDetails[7].toString()));
										paymentDetails.setFeeAmount(Double.valueOf(feeDetails[8].toString()));
										paymentDetails.setPaymentAmount(Double.valueOf(feeDetails[9].toString()));
										paymentDetails.setDiscountAmount(Double.valueOf(feeDetails[10].toString()));
										if(Double.valueOf(feeDetails[9].toString())>0)
											paymentDetails.setPaidAmount(Double.valueOf(Double.valueOf(feeDetails[8].toString())- Double.valueOf(feeDetails[9].toString())+Double.valueOf(feeDetails[10].toString())));
										getSchoolFeeList().add(paymentDetails);
									}
								}
							}
						}
					}
					if(customer.isAcademicWiseFeeReceipt())
						receiptNumber=adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),getStudent().getAcademicYearId());
					else
						receiptNumber=adminManager.getInvoiceNumberByCustId("studentPayment", getUserCustId(),0);
				}
				if(StringFunctions.isNullOrEmpty(paymentStatus)){
					setTempString("N");
				}else{
					setTempString(paymentStatus);
				}
				setTempId(receiptNumber+1);
			}
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
		}
	public String ajaxFeeSettingInvoice() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxFeeSettingInvoice' method");
		}
		try {
			//getTemp1() gives applicaion Id, getAnyId() gives admission applied studentId
			if (getTempId2() > 0) {
				if (getTempId1() > 0) {
					setOnlineApplicationDetails((OnlineApplicationDetails) adminManager.get(OnlineApplicationDetails.class, getTempId1()));
					if (!ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())) {
						prepareAdmissionApplicantStudentSchoolFeeSettingList(getOnlineApplicationDetails());
					}
				}
				else if (StringFunctions.isNotNullOrEmpty(getAnyId())) {
					setDescription("viewAdmittedStudents");
					setStudent((Student) adminManager.get(Student.class, "id="+ getAnyId()));
					prepareStudentSchoolFeeSettingList(getStudent());
				}
			} else {
				setStudent((Student) adminManager.get(Student.class, "id="+ Long.valueOf(getParamValue("id"))));
				prepareStudentSchoolFeeSettingList(getStudent());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	public void prepareClassAssignmentDetailsList() {
		try 
		{ 
			Date aDate=null;
			if(!StringFunctions.isNullOrEmpty(getSubjectId())){
				setStudentsList(staffManager.getAll(ViewClassAssignmentDetails.class,"classSectionId="+getClassId()+" and subjectId="+getSubjectId()+" and academicYearId is not null order by assignmentDate DESC"));
			}else if(StringFunctions.isNotNullOrEmpty(getClassId())){
				setStudentsList(staffManager.getAll(ViewClassAssignmentDetails.class,"classSectionId="+getClassId()+"  and academicYearId is not null order by assignmentDate DESC"));
			}
			if(ObjectFunctions.isNotNullOrEmpty(getStudentsList()))
			{
				Collections.sort(getStudentsList());
			}
				//below lines are used to when change the class show the respected subjects
				if(StringFunctions.isNotNullOrEmpty(getClassId()))
				{
					if ("ROLE_ADMIN".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_PRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_VICEPRINCIPAL".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_ADMINOFFICER".equalsIgnoreCase(getUser().getUserRole())) {
						setSubjectsList(adminManager.getAll(ViewClassAssignmentDetails.class,"custId="+getUserCustId()+" and academicYearId="+getAcademicYearId()+" and classSectionId="+getClassId()+" group by subjectId"));
					}
					else if("ROLE_TEACHER".equalsIgnoreCase(getUser().getUserRole()) ||"ROLE_ADMIN_COORDINATOR".equalsIgnoreCase(getUser().getUserRole()) || "ROLE_HOD".equalsIgnoreCase(getUser().getUserRole()) || getUser().isSchoolAsstStaff())
					{
						setSubjectsList(getStaffSubjectsListForAssignment(Long.valueOf(getClassId())));
						
					}
					/*Siva: Added for showing class Assignement
					 * if(!ObjectFunctions.isNullOrEmpty(getSubjectsList())){
						getSubjectsList().add(new ViewClassAssignmentDetails(0,"Assignment"));	
					}*/
				}
					 
			//} 
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 9, 2013       Seshu		        For getting upcoming examSchedules for staff and principal.
/********************************************************************/	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 9, 2013       Seshu		        For getting upcoming examSchedules for staff and principal.
 * May 6, 2014       Seshu              Implemented services for exam schedules module.
/********************************************************************/	
	@Actions({
		@Action(value = "ajaxClassWiseUpcomingExamSchedules", results = { @Result(location = "../exam/ajaxUpcomingExamSchedulesInfo.jsp", name = "success") }) })
	public String ajaxClassWiseUpcomingExamSchedules() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxClassWiseUpcomingExamSchedules' method");
		}
		try {
			if(getUser().isSchoolTeacher()){
				setExamScheduleList(adminManager.getUsersStartAndEndDateExamSchedulesDetails(getUser().getId(),getUserAcademicYearId(),"future",true,0,0));
				setExamScheduleList(adminManager.getUsersStartAndEndDateExamSchedulesDetails(0,getUserAcademicYearId(),"future",false,0,0));
			}
			if(ObjectFunctions.isNotNullOrEmpty(getExamScheduleList()))
				Collections.sort(getExamScheduleList(),new ExamSchedulesStartDateComparator());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/* This method will return the current yea and next academic year to show the academic year list in admission @Ganesh */
	@SuppressWarnings("unchecked")
	public List<AcademicYear> getAdmissionAcademicYears()
	{
		StringBuffer queryString = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId>=").append(getUserAcademicYearId());
		List<AcademicYear> admissionAcademicList=new ArrayList<AcademicYear>();//adminManager.getAll(AcademicYear.class, queryString.toString());
		List<AdmissionSettings> admissionSettingList=adminManager.getAll(AdmissionSettings.class, queryString.toString());
		if(!ObjectFunctions.isNullOrEmpty(admissionSettingList)){
			for(AdmissionSettings settings:admissionSettingList){
				admissionAcademicList.add(settings.getAcademicYear());
			}
		}
		queryString = null;
		return admissionAcademicList;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Actions( { @Action(value = "ajaxGetClassTeacherClasses", results = { @Result(location = "../admin/common/ajaxViewStudyClassList.jsp", name = "success") }) })
	public String ajaxGetClassTeacherClasses() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetClassTeacherClasses' method");
		}
		try {
			//synchronized (BaseAction.class) {
				
			setStudyClassList(null);
			ClassTeacher classTeacherClass = null;
			HashSet<StudyClass> classSections = new HashSet<StudyClass>();
			if(getUser().getId() > 0 )
			{
				setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
			}
			if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
				if (getStaff().getId() > 0 && getUserAcademicYearId() > 0) {
					if(getUser().isSchoolTeacher() || getUser().isSchoolAsstStaff()){
						classTeacherClass = adminManager.getClassTeacherByAcademicId(getStaff().getId(),getUserCustId(),getUserAcademicYearId());
					}
					if(getUser().isOnlySchoolHod() || getUser().isAdminCoordinator())
					{
						List studyClassesList = getHodStudyClassesList(getStaff().getId(),getUserAcademicYearId());
						if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
						{
							classSections.addAll(studyClassesList);
						}
						if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
							setStudyClassList(ConvertUtil.convertSetToList(classSections));
							Collections.sort(getStudyClassList());
						}
						studyClassesList = null;
					}
					else if(getUser().isSchoolAsstStaff())
					{
						StringBuilder studyClassIds = new StringBuilder("(");
						List<ViewStaffSubjectsDetails> staffClassList =  adminManager.getAll(ViewStaffSubjectsDetails.class,"custId ="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" group by studyClassId");
						 if (!ObjectFunctions.isNullOrEmpty(staffClassList))
						 { 
							for (ViewStaffSubjectsDetails viewStaffSubjectsDetails : staffClassList) 
							{
								 long studentsCount = studentManager.getClassStudentsCountByClassId(viewStaffSubjectsDetails.getStudyClassId(),getUserCustId());
								 if(studentsCount > 0 )
									 studyClassIds.append(viewStaffSubjectsDetails.getStudyClassId()+",");
							}
							studyClassIds.append("0)");
						 }else
								studyClassIds.append("0)");
						 setStudyClassList(adminManager.getAll(StudyClass.class, " id in " + studyClassIds.toString()));
					}
					if (!ObjectFunctions.isNullOrEmpty(classTeacherClass)) 
					{
						classSections.add(classTeacherClass.getStudyClass());
						getSelectboxMap().put(classTeacherClass.getStudyClass().getClassId(),classTeacherClass.getStudyClass().getClassName());
					 
						if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
							if (!StringFunctions.isNullOrEmpty(getClassId())) 
							{
								if (getClassId().equalsIgnoreCase(classTeacherClass.getStudyClass().getStrId()))
									setStudyClass(classTeacherClass.getStudyClass());
							}
							setStudyClassList(ConvertUtil.convertSetToList(classSections));
							Collections.sort(getStudyClassList());
							if (ObjectFunctions.isNullOrEmpty(getClassNameList()))
							{
								setClassNameList(new ArrayList<ClassName>());
							}	
							getClassNameList().add(classTeacherClass.getStudyClass());	
							
						}
					}
					classSections = null;
					classTeacherClass = null;
				}
			}			
			staff = null;
			//}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}  
	public boolean getIsClassTeacherOrNot() {
		if (!ObjectFunctions.isNullOrEmpty(getUser())) {
			{
				//ClassTeacher classTeacher = null;
				//classTeacher = staffManager.getClassTeacherByAccountId(getUser().getId(), getUserAcademicYearId());
				Object classTeacher = staffManager.get("select * from classTeacher where academicYearId="+getUserAcademicYearId()+" and classTeacher='Y' and teacherId in (select id from staff where custId="+getUserCustId()+" and accountId="+getUser().getId()+" and description is null )");
				if (!ObjectFunctions.isNullOrEmpty(classTeacher)) {
					setTempBoolean(true);
					classTeacher=null;
					return true;
				}
			}

		}
		return false;
	}
	
	public void ajaxCreateSchoolTermBoardingPointWiseFee(SchoolTerms schoolTermsObj, RouteBoardingPoints boardingPoints,SchoolFeeSetting schoolFeeSetting,AcademicYear academicYear,Customer customer) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSchoolTermBoardingPointWiseFee' method");
		}
		try {
			//SchoolCategory category = (SchoolCategory)adminManager.get(SchoolCategory.class, " custId=" + getUserCustId()+" and categoryName='"+Constants.GENERAL_CATEGORY+"' ");
			List<SchoolCategory> categoryList = adminManager.getAll(SchoolCategory.class, " custId=" + getUserCustId());
			if (ObjectFunctions.isNotNullOrEmpty(categoryList)) {
				FeeType feeType = (FeeType) adminManager.get(FeeType.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " and feeSettingId="+ schoolFeeSetting.getId() + " and feeType='"+ Constants.TRANSPORT_FEES + "'");
				for (SchoolCategory category : categoryList) {
					if (!ObjectFunctions.isNullOrEmpty(category) && !ObjectFunctions.isNullOrEmpty(feeType)) {
						Fee fee = (Fee) adminManager.get(Fee.class, "custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and schoolTermId="+ schoolTermsObj.getId()+ " and feeTypeId=" + feeType.getId()+ " and categoryId=" + category.getId()+ " and routeBoardingPointId="+ boardingPoints.getId());
						if (ObjectFunctions.isNullOrEmpty(fee)) {
							fee = new Fee();
						}
						fee.setCreatedDate(new Date());
						fee.setCreatedById(getUser().getId());
						fee.setLastAccessDate(new Date());
						fee.setLastUpdatedById(getUser().getId());
						fee.setCustomer(customer);
						fee.setCategoryId(category.getId());
						fee.setAcademicYear(academicYear);
						fee.setFeeAmount(boardingPoints.getBoardingPointFeeAmount());
						fee.setRouteBoardingPointId(boardingPoints.getId());
						fee.setSchoolTerms(schoolTermsObj);
						fee.setFeeType(feeType);
						fee.setStatus(schoolFeeSetting.getStatus());
						adminManager.save(fee);
						adminManager.updateStudentPaymentStatusForTransportFee(Long.valueOf(boardingPoints.getId()),Long.valueOf(category.getId()));
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	public void prepareCategoryIds(List<SchoolCategory> schoolCategoryList){
		try {
			StringBuffer categoryIds = new StringBuffer();
			categoryIds.append("(");
			if(!ObjectFunctions.isNullOrEmpty(schoolCategoryList)){
				for(SchoolCategory category:schoolCategoryList){
					categoryIds.append(category.getId());
					categoryIds.append(",");
				}
			}
			categoryIds.append("0)");
			setTempString(categoryIds.toString());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	} 

	/*
	  * Remove prepareAcademicYearId and use getUserAcademicYearId by venkatesh - 04-25-2013
	  */
	public String getStudentsClassAssignmentList() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'getStudentsAttendanceList' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getClassId())) {
				 	//List<ViewStudentPersonAccountDetails> studentsDetailsList = staffManager.getViewStudentPersonAccountDetailsByStudyClassIdandStatus(Long.valueOf(getClassId()), "Y", String.valueOf(getUserAcademicYearId()));
				List<ViewStudentPersonAccountDetails> studentsDetailsList = adminManager.getAll(ViewStudentPersonAccountDetails.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+getClassId()+" and status='"+Constants.YES_STRING+"' and description is null");
					if(ObjectFunctions.isNotNullOrEmpty(studentsDetailsList)){
						Collections.sort(studentsDetailsList);
						List studentsList = staffManager.getAll(VWStudentClassAssignment.class,"assignmentId="+(Long.valueOf(getEventId()))+" order by studentName");
						if(ObjectFunctions.isNotNullOrEmpty(studentsList))
						{
							if(studentsList.size() > 0){
								setAttendanceType("updateAttendance");
							}
						}
						else
						{
							if(!ObjectFunctions.isNullOrEmpty(studentsDetailsList))
							{
								setAttendanceType("addAttendance");
							}
						}
						generateStudentAssignmentList(studentsDetailsList);
					} 
				 
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	public void generateStudentAssignmentList(List<ViewStudentPersonAccountDetails> studentsDetailsList){
		//VWStudentClassAssignment assignment = null;
		if (ObjectFunctions.isNullOrEmpty(getStudentsList()))
			setStudentsList(new ArrayList<ViewStudentPersonAccountDetails>());
		
		List<StudentClassAssignment> studentClassAssignmentList = adminManager.getAll(StudentClassAssignment.class, "assignmentId="+getEventId());
		if(!ObjectFunctions.isNullOrEmpty(studentClassAssignmentList))
		{
			setTempString("assignment");
			
			Map<Long, StudentClassAssignment> studentClassAssignmentMap = new HashMap<Long, StudentClassAssignment>();
			if(!ObjectFunctions.isNullOrEmpty(studentClassAssignmentList))
			{
				for(StudentClassAssignment studentClassAssignment : studentClassAssignmentList)
				{
					studentClassAssignmentMap.put(studentClassAssignment.getStudentId(), studentClassAssignment);
				}
			}
				
			for(ViewStudentPersonAccountDetails studentDetails : studentsDetailsList )
			{
				StudentClassAssignment studentClassAssignment = studentClassAssignmentMap.get(studentDetails.getStudentId());
				if(!ObjectFunctions.isNullOrEmpty(studentClassAssignment))
				{
					if(!(studentClassAssignment.isAssignmentDone()))
					{
						studentDetails.setPresent(Constants.STATUS_FALSE);
					}				
				}
				else
				{
					studentDetails.setPresent(Constants.STATUS_TRUE);	
				} 
				getStudentsList().add(studentDetails);
				studentClassAssignment=null;
			}
			studentClassAssignmentMap = null;
		}
		else
		{
			ClassAssignment classAssignment = (ClassAssignment)adminManager.get(ClassAssignment.class, "id="+getEventId());
			if(!ObjectFunctions.isNullOrEmpty(classAssignment))
			{
				if("P".equalsIgnoreCase(classAssignment.getStatus()))
				{
					setTempString("noAssignment");
					for(ViewStudentPersonAccountDetails studentDetails : studentsDetailsList )
					{
						studentDetails.setPresent(Constants.STATUS_FALSE);
						getStudentsList().add(studentDetails);
					}
				}
				else if("A".equalsIgnoreCase(classAssignment.getStatus()))
				{
					setTempString("assignment");
					for(ViewStudentPersonAccountDetails studentDetails : studentsDetailsList )
					{
						studentDetails.setPresent(Constants.STATUS_TRUE);
						getStudentsList().add(studentDetails);
					}
				}
			}
			classAssignment = null;
			
		}
		studentClassAssignmentList = null;
	}
	public String addOrUpdateStudentAssignment() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addOrUpdateStudentAssignment' method");
		}
		try {
			String sms = getParamValue("balance");
			List<String> studentAccountIds = new ArrayList<String>();
			if (StringFunctions.isNullOrEmpty(getAnyTitle())) {
				super.addActionError("Student Assignment is not registered due to system error. Please contact System Administrator");
				doInitAttendanceData();
				return SUCCESS;
			}
			JSONObject formData = new JSONObject(getAnyTitle());
			if (ObjectFunctions.isNullOrEmpty(formData)) {
				super.addActionError("Student Assignment is not registered due to system error. Please contact System Administrator");
				doInitAttendanceData();
				return SUCCESS;
			}
			JSONObject json;
			StudentClassAssignment assignment = null;
			ViewClassAssignmentDetails vwAssignmentDetails=null;
			String msgContent = null;
			String attType = null;
			setAttendanceDate((String) formData.get("attendanceDate"));
			log.debug("setAttendanceDate  ::" + getAttendanceDate());
			JSONArray classAssignmentData = (JSONArray) formData.get("data");

			Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, getAttendanceDate());
			if (ObjectFunctions.isNullOrEmpty(aDate)) {
				aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getAttendanceDate()));
			}
			 Customer customer = getCustomerByCustId();
			 Messages message = null;
			 log.debug("aDate ::" + aDate);
			 
			
			vwAssignmentDetails = (ViewClassAssignmentDetails) adminManager.get(ViewClassAssignmentDetails.class,"assignmentId="+getEventId());
			if(!ObjectFunctions.isNullOrEmpty(vwAssignmentDetails))
			{
				setTempString(vwAssignmentDetails.getSubjectName());
				setClassId(String.valueOf(vwAssignmentDetails.getClassSectionId()));
				if (ObjectFunctions.isNullOrEmpty(aDate)) {
					aDate = vwAssignmentDetails.getAssignmentDate();
				}
			}
			 
			 Map<Long, StudentClassAssignment> studentClassAssignmentMap = new HashMap<Long, StudentClassAssignment>();
			List<Student> studentObjList = new ArrayList<Student>();
			List<StudentClassAssignment> assignmentList = staffManager.getAll(StudentClassAssignment.class,"assignmentId="+Long.valueOf(getEventId()));
			if(!ObjectFunctions.isNullOrEmpty(assignmentList))
			{
				for(StudentClassAssignment studentClassAssignment : assignmentList)
				{
					studentClassAssignmentMap.put(studentClassAssignment.getStudentId(), studentClassAssignment);
				}
			}
			 
			 ClassAssignment classAssignment = (ClassAssignment) staffManager.get(ClassAssignment.class, "id="+getEventId());
			 if (!ObjectFunctions.isNullOrEmpty(classAssignment)) {
				 for (int i = 0; i < classAssignmentData.length(); i++) {
						User userObj = null;
						json = classAssignmentData.getJSONObject(i);
						if (!ObjectFunctions.isNullOrEmpty(json)) {
							log.debug("i : " + i);
							setAnyTitle((String) json.get("status"));
							setAnyId((String) json.get("studentId"));
							setEventId((String) json.get("assignmentId"));
							
							Student student =(Student) staffManager.get(Student.class, Long.valueOf(getAnyId()));
							if(!ObjectFunctions.isNullOrEmpty(student))
							{
								userObj = (User) student.getAccount();
								if(!ObjectFunctions.isNullOrEmpty(userObj))
								{
									if("P".equalsIgnoreCase(getAnyTitle()))
									{	
										studentAccountIds.add(String.valueOf(userObj.getId()));
									}
									assignment = studentClassAssignmentMap.get(student.getId());
									if (ObjectFunctions.isNullOrEmpty(assignment)) {
										assignment = new StudentClassAssignment();
										assignment.setCreatedById(getUser().getId());
										assignment.setCreatedDate(new Date());
										log.debug("Student Id : " + getAnyId());
										assignment.setAssignmentDate(aDate);
										assignment.setLastUpdatedDate(new Date());
										assignment.setLastAccessDate(new Date());
										assignment.setStudentId(Long.valueOf(getAnyId()));
										if ("A".equalsIgnoreCase(getAnyTitle()))
										{
											assignment.setAssignmentDone(Boolean.FALSE);
											assignment.setAssignmentId(Long.valueOf(getEventId()));
											if("SMS".equalsIgnoreCase(sms))
											{
												if(customer.isCheckMobileService()){
													if ("addAttendance".equalsIgnoreCase(getAttendanceType())&& StringFunctions.isNullOrEmpty(attType)) {
														log.debug("Assignment Type : " + attType);
														if (!ObjectFunctions.isNullOrEmpty(userObj)) 
														{
															studentObjList.add(student);
														}
													}
												}
											}
											attType = null;
											adminManager.save(assignment);
										}
									 } 
									else 
									{
										if("P".equalsIgnoreCase(getAnyTitle()))
										{	
											staffManager.remove(StudentClassAssignment.class,assignment.getId());
											attType = "updateAttr";
										}
										attType = null;
									}
									assignment = null;
									setMessages(null);
									message = null;
								}
							}
						}
					}
					 
				 classAssignment.setStatus(Constants.ACTIVE_STATUS);
				 adminManager.merge(classAssignment);
				 
				 setEventId(String.valueOf(classAssignment.getId()));
				 SendAssignmentSMSThread R1 = new SendAssignmentSMSThread(studentObjList,customer,getTempString(),aDate,getUser());
			      R1.start();
			      log.debug("Sending Notification to Mobile App on Assignment Completion.");
			      adminManager.sendNotificationForCompletionOfClassAssignments(studentAccountIds,classAssignment);
			      studentObjList = null;
			 }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/********************************************************************
	 * Date              Name               Description
	 * ========          ============       ==================
	 * July 8, 2013      Rama		        Displaying alerts for expirydates duration of 30 days.
	 * Dec 30, 2013		 Seshu				Code Refactor
	/********************************************************************/
	 public String getExpiryDates(){
		 //isTempBoolean() is true when request comes from 'Transport Warnings' link.
			try{			
			  int endDays = 30;
			   StringBuffer query= new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and ((insuranceDays!=0 and insuranceDays <="+endDays).append(") or (pollutionDays!=0 and pollutionDays <="+endDays).append(") or (fitnessDays!=0 and fitnessDays <="+endDays).append(") or (permitDays!=0 and permitDays <="+endDays).append(") or (roadTaxDays!=0 and roadTaxDays <="+endDays).append("))");
			   log.debug(query.toString());
			   List vehicles = adminManager.getAll(ViewVehiclesWithExpiryDatesInformation.class, query.toString());			  
			    if(ObjectFunctions.isNullOrEmpty(vehicles))
			    	 getSession().setAttribute("AllExpiryDates",false);
			    else{
			    	getSession().setAttribute("AllExpiryDates",true);
			    	if(isTempBoolean()){
			    		setNumberOfDays(endDays);
				    	setTempList(vehicles);
			    	}
			    }
			    query = null;
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			} 
			return SUCCESS;
	 }
	 public int getWorkingDaysByMonth(int monthId,AcademicYear academicYear, String classSectionId)
	 {
		 int days =0; 
			int yearId;
			int monthTotalDays = 0;
			Date closeDate = null;
			//AcademicYear year=getCurrentAcademicYear();
			 if(!ObjectFunctions.isNullOrEmpty(academicYear))
			 {
					 closeDate = academicYear.getEndDate();
			 }
			
		if (!ObjectFunctions.isNullOrEmpty(closeDate)) {
			List<SchoolHolidays> holidays = null;
			Date todayDate=new Date();
			int currentMonth=DateFunctions.getMonthOfDate(todayDate);
			int currentYear = DateFunctions.getDayOfYear(new Date());
		 	yearId = DateFunctions.getDayOfYear(new Date());
		 	Object[] classNameClassIds=null;
		 	if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()) && !StringFunctions.isNullOrEmpty(classSectionId)){
	 			 classNameClassIds= adminManager.get("select classId,className from vw_classSectionDetails where custId="+getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+" and classSectionId="+Long.valueOf(classSectionId));
		 	}
			if (monthId > 0) 
			{ 
				int schoolStartMonthId=academicYear.getStartDate().getMonth()+1;
				int schoolEndMonthId=academicYear.getEndDate().getMonth()+1;
				if(monthId == schoolStartMonthId){
					monthTotalDays = getActualMonthDaysByMonthId(monthId) - academicYear.getStartDate().getDate()-1;
				}else if(monthId == schoolEndMonthId){
					monthTotalDays=academicYear.getEndDate().getDate();
				}
				else{
					monthTotalDays = getActualMonthDaysByMonthId(monthId);
				}
				 if (monthId == currentMonth && yearId == currentYear) {
					 if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
				 			holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()), null, null,classNameClassIds[0].toString(),null,null,monthId,"holidayBetweenDates",null);
				 		}else{
				 			holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()), null, null,null,null,null,monthId,"holidayBetweenDates",null);
				 		}
				 }else{
					 if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
						 holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate), null, null,classNameClassIds[0].toString(),null,null,monthId,"holidayBetweenDates",null);
				 		}else{
				 			holidays=adminManager.getSchoolHolidaysListByDatesAndCustId(getUserCustId(),getUserAcademicYearId(), DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate), null, null,null,null,null,monthId,"holidayBetweenDates",null);
				 		}
				 }
				if (!ObjectFunctions.isNullOrEmpty(holidays)) {
					days= monthTotalDays - holidays.size();
				}
				else
					days= monthTotalDays;
			}
		} 
		 return days;
	 }
		@Actions( {
			@Action(value = "ajaxViewStudentHostelInfo",  results = { @Result(location = "../hostel/ajaxViewStudentHostelInfo.jsp", name = "success") }) })
		public String ajaxViewStudentHostelInfo() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewStudentHostelInfo' method");
			}
			try {
				HostelStudents hostelStudent = (HostelStudents) (adminManager.get(HostelStudents.class,"accountId="+getBankId())); //here bankId is a accountId
			    List<Object[]> stdObject=null;
			    List<Object[]> buildingWithFloorList = null;
			    setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
				if(!ObjectFunctions.isNullOrEmpty(hostelStudent))
				{ 
					  stdObject = adminManager.getAll("select roomId,roomName from vw_hostelStudentDetails where studentId="+hostelStudent.getStudentId());
					 if(ObjectFunctions.isNotNullOrEmpty(stdObject))
						{	
							for(Object[] str : stdObject)
							{
								setTempId(Long.valueOf(str[0].toString()));
							}
						}
					 buildingWithFloorList = adminManager.getAll("SELECT distinct(floorId),floorName,buildingId,bedId FROM vw_roomDetails where roomId ="+hostelStudent.getRoomId());
				   if(ObjectFunctions.isNotNullOrEmpty(buildingWithFloorList))
					{	
						for(Object[] str : buildingWithFloorList)
						{
							setAnyId(str[0].toString());
							setTempList1(hostelManager.getAll(Room.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+" and floorId="+Long.valueOf(str[0].toString())));
							setTempId2(Long.valueOf(str[2].toString()));
							setBankId(Long.valueOf(str[3].toString()));
							setBuilding((Building) hostelManager.get(Building.class,Long.valueOf(str[2].toString())));
							setTempList(hostelManager.getAll(ViewBuildingFloorDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " and buildingId="+ Long.valueOf(str[2].toString()) +" order by buildingName,floorName,floorLevel"));
							setTempList2(adminManager.getAll(Bed.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and roomId="+hostelStudent.getRoomId()+" order by bedName"));
						}
					}
				   setHostelStudents(hostelStudent);
				}else{
					setBankId(getBankId()); //here bankId is a accountId
					setTempId(getStudent().getId()); //here bankId is a studentId
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions( {	@Action(value = "ajaxEditStudentHostelInformation", results = { @Result(location = "../hostel/ajaxSearchStudentDetails.jsp", name = "success") })
		})
	public String ajaxEditStudentHostelInformation() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditStudentHostelInformation' method");
		}
		try {
			HostelStudents hostelStudents = null;
			if(getHostelStudents().getId()>0){
				hostelStudents = (HostelStudents) adminManager.get(HostelStudents.class,getHostelStudents().getId());
				setAnyId(String.valueOf(hostelStudents.getAccountId()));
			}else{
				hostelStudents = new HostelStudents();
				hostelStudents.setStudentId(getHostelStudents().getStudentId());
				hostelStudents.setAccountId(getHostelStudents().getAccountId());
				hostelStudents.setStatus(Constants.YES_STRING);
				hostelStudents.setCustId(getUserCustId());
				hostelStudents.setAcademicYear(getCurrentAcademicYear());
			}
			if(getTempId()>0)
				hostelStudents.setRoomId(getTempId());
			else
				hostelStudents.setRoomId(getHostelStudents().getRoomId());
			if(getBankId()>0)
				hostelStudents.setBedId(getBankId());
			else
				hostelStudents.setBedId(getHostelStudents().getBedId());
			hostelStudents.setCreatedById(getUser().getId());
			hostelStudents.setCreatedDate(new Date());
			HostelStudents hostelStudentObj=(HostelStudents)adminManager.save(hostelStudents);
			setBankId(hostelStudentObj.getAccountId());
			ajaxViewStudentHostelInfo();	
			hostelStudentObj=null;
			hostelStudents=null;
			super.addActionMessage("Hostel is successfully assigned to the student");
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
	public CommonType createOrUpdateReligion(String religions) {
		try {
			CommonType religion = null;
			if (!StringFunctions.isNullOrEmpty(String.valueOf(religions.toUpperCase()))) {
				String type = "RELIGION";
				religion = adminManager.getCommonType(getUserCustId(), type, String.valueOf(religions.toUpperCase()));
				if (ObjectFunctions.isNullOrEmpty(religion)) {
					religion = new CommonType();
					religion.setCustId(getUserCustId());
					religion.setSkillTypeName(String.valueOf(religions.toUpperCase()));
					religion.setType(type);
					//religion.setVersion(0);
					adminManager.save(religion);

				}
				if (!ObjectFunctions.isNullOrEmpty(religion)) {
					setCommonType(religion);
					return religion;
				}
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public SubCastSettings createOrUpdateCastName(String castName,String subcastName) {
		try {
			CastSettings castSettingsObj = null;
			SubCastSettings subCastSettingsObj = null;
			if (StringFunctions.isNotNullOrEmpty(castName)) {
				if (!StringFunctions.isNullOrEmpty(String.valueOf(castName.toUpperCase()))) {
					castSettingsObj = adminManager.getCastNames(castName.toUpperCase().trim(), getUserCustId());
					if (ObjectFunctions.isNullOrEmpty(castSettingsObj)) {
						castSettingsObj = new CastSettings();
						castSettingsObj.setCustId(getUserCustId());
						castSettingsObj.setCastName(castName.toUpperCase().trim());
						castSettingsObj.setCreatedById(getUser().getId());
						castSettingsObj.setCreatedDate(new Date());
					} else
						castSettingsObj.setLastUpdatedById(getUser().getId());
					if (!ObjectFunctions.isNullOrEmpty(castSettingsObj)) {
						if (StringFunctions.isNotNullOrEmpty(subcastName)) {
							subCastSettingsObj = adminManager.getSubCast(getUserCustId(), subcastName.toUpperCase().trim(),castSettingsObj.getId());
							if (ObjectFunctions.isNullOrEmpty(subCastSettingsObj)) {
								subCastSettingsObj = new SubCastSettings();
								subCastSettingsObj.setSubCastName(subcastName.toUpperCase().trim());
								subCastSettingsObj.setCustId(getUserCustId());
								subCastSettingsObj.setCreatedById(getUser().getId());
								subCastSettingsObj.setCreatedDate(new Date());
							} else
								subCastSettingsObj.setLastUpdatedById(getUser().getId());
							if (!ObjectFunctions.isNullOrEmpty(subCastSettingsObj)) {
								castSettingsObj.addSubCast(subCastSettingsObj);
							}
							castSettingsObj.setLastAccessDate(new Date());
							castSettingsObj.setLastUpdatedDate(new Date());
							if (castSettingsObj.getId() == 0|| subCastSettingsObj.getId() == 0)
								setCastSettings(adminManager.saveComunityName(castSettingsObj));
						}
					}
					if (!ObjectFunctions.isNullOrEmpty(subCastSettingsObj)) {
						subCastSettingsObj.setLastAccessDate(new Date());
						subCastSettingsObj.setLastUpdatedDate(new Date());
						setSubCastSettings(subCastSettingsObj);
						return subCastSettingsObj;
					}
				}
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;

	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Aug 22, 2013      Seshu		      	For getting month numbers By Name
/********************************************************************/	
 public enum MonthNames {
 	JANUARY(1),FEBRUARY(2),MARCH(3),APRIL(4),MAY(5),JUNE(6),JULY(7),AUGUST(8),SEPTEMBER(9),OCTOBER(10),NOVEMBER(11),DECEMBER(12);
 	private int monthNumber;
 	MonthNames(int number) {
 		monthNumber = number;
 	 }
 	   public int getMonthNumber() {
 	      return monthNumber;
 	   } 
 	}
 /********************************************************************
  * Date              Name               Description
  * ========          ============       ==================
  * Sep 16, 2013      Ganesh		     Generate Paid And Un-Paid Details In Pdf
 /********************************************************************/	
 public void genaratePaidAndUnPaidFeeDetails( ) {
		double totalDiscountAmount = 0;
		double termToatlPaid = 0;
		double termTotalAmount = 0;
		double totalTermAmount = 0;
		double paidAmount = 0;
		double totalPaidAmount = 0;
		double discountAmount = 0;
		double defaultDiductedAmount =0;
	 for (String termId : getChkBoxClassSelectedIds()) {
		 SchoolTerms schoolTerms = (SchoolTerms) adminManager.get(SchoolTerms.class, Long.valueOf(termId));
		ViewStudentFeePaymentDetails feePaymentDetails = null;
		 paidAmount = 0;
		 totalPaidAmount = 0;
		 discountAmount = 0;
		 defaultDiductedAmount =0;
		StringBuffer query = null;
		StringBuffer query1 = null;
		StringBuffer querString=new StringBuffer();
		querString.append("select admissionNumber,firstName,lastName,className,section,classId,sum(paymentAmount),sum(discountAmount),sum(paymentAmount+discountAmount),termName,studentId,paymentDate,status,committedFee,classSectionId from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and schoolTermId="+schoolTerms.getId()+" and classsectionId in"+getAnyTitle()+"  and status='Y' and description is null");
		if(Constants.TRANSPORT_FEES.equalsIgnoreCase(schoolTerms.getFeeSetting().getSettingName())){
			querString.append(" and vehicleAcademicDetailsId!=0 and routeId!=0");
		}
		if(Constants.HOSTEL_STATUS.equalsIgnoreCase(schoolTerms.getFeeSetting().getStatus())){
			querString.append(" and hostelMode='"+Constants.HOSTEL_STATUS+"' ");
		}
		querString.append(" and deleteStatus='N'  and paymentCommitFeeStatus='N' group by schoolTermId,studentId order by classSectionId,schoolTermId");
		List<Object[]> daysBetweenPaidlists = adminManager.getAll(querString.toString());
		querString = null;
		if (!ObjectFunctions.isNullOrEmpty(daysBetweenPaidlists)) {
			for (Object[] obj1 : daysBetweenPaidlists) {
				Object feeObject[] = adminManager.get("select sum(feeAmount),schoolTermId from vw_studentClassFees where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+obj1[10].toString()+" and  classSectionId ="+obj1[14].toString()+" and schoolTermId="+schoolTerms.getId()+" group by schoolTermId");
				if(!ObjectFunctions.isNullOrEmpty(feeObject)){
					if(!ObjectFunctions.isNullOrEmpty(feeObject[0])){
						query = new StringBuffer("select sum(paymentAmount),sum(discountAmount) from vw_studentFeePaymentDetails where studentId=").append(obj1[10].toString());
						query.append(" and custId="+getUserCustId()+"").append(" and academicYearId="+getUserAcademicYearId()+"").append(" and schoolTermId="+schoolTerms.getId()+"").append(" and deleteStatus='N'").append(" and status='Y'");
						defaultDiductedAmount = 0;
						if(!ObjectFunctions.isNullOrEmpty(obj1[13])){
							if(Double.valueOf(obj1[13].toString()) > 0){
								query1 = new StringBuffer(query);
								query1 .append(" and paymentCommitFeeStatus='"+Constants.YES_STRING+"' ");
								//log.debug(query1.toString());
								Object  deductedTotalAmount[] =adminManager.get(query1.toString());
								if(!ObjectFunctions.isNullOrEmpty(deductedTotalAmount)){
									if(!ObjectFunctions.isNullOrEmpty(deductedTotalAmount[0]))
										defaultDiductedAmount = Double.valueOf(deductedTotalAmount[0].toString());
								}
								query1 = null;
							}
						}
						Object  viewStudentPaymentDetails[] =adminManager.get(query.append(" and paymentCommitFeeStatus='N'").toString());
						query = null;
						
						if(defaultDiductedAmount == 0 || Double.valueOf(feeObject[0].toString()) <= defaultDiductedAmount)
							totalTermAmount = Double.valueOf(feeObject[0].toString());
						else
							totalTermAmount = Double.valueOf(feeObject[0].toString()) - defaultDiductedAmount;
						if(!ObjectFunctions.isNullOrEmpty(viewStudentPaymentDetails[0]))
							totalPaidAmount = Double.valueOf(viewStudentPaymentDetails[0].toString());
						else
							totalPaidAmount = 0;
						if(!ObjectFunctions.isNullOrEmpty(viewStudentPaymentDetails[1]))
							discountAmount = Double.valueOf(viewStudentPaymentDetails[1].toString());
						else
							discountAmount = 0;
							
						feePaymentDetails = new ViewStudentFeePaymentDetails();
						if(!ObjectFunctions.isNullOrEmpty(obj1[0]))
							feePaymentDetails.setAdmissionNumber(obj1[0].toString());
						if(!ObjectFunctions.isNullOrEmpty(obj1[2]))
							feePaymentDetails.setFirstName(obj1[1].toString()+ ' '+ obj1[2].toString());
						else {
							if(!ObjectFunctions.isNullOrEmpty(obj1[1]))
							feePaymentDetails.setFirstName(obj1[1].toString());
						}
						if(!StringFunctions.isNullOrEmpty(obj1[4].toString())){
							feePaymentDetails.setClassName(obj1[3].toString()+"-"+obj1[4].toString());
						}else {
							if(!ObjectFunctions.isNullOrEmpty(obj1[3]))
							feePaymentDetails.setClassName(obj1[3].toString());
						}
						feePaymentDetails.setFeeAmount(totalTermAmount);
						feePaymentDetails.setPaymentAmount(totalPaidAmount);
						feePaymentDetails.setDiscountAmount(discountAmount);
						feePaymentDetails.setPaidAmount(totalTermAmount - (totalPaidAmount + discountAmount));
						feePaymentDetails.setTermName(obj1[9].toString());
						paidAmount = Double.valueOf(obj1[6].toString());
						Date paymentDate = (Date) obj1[11];
						if(paidAmount >0){
							feePaymentDetails.setPaymentDate(paymentDate);
						}
						getStudentsFeeTypeList().add(feePaymentDetails);
						termTotalAmount = termTotalAmount + totalTermAmount;
						totalDiscountAmount = totalDiscountAmount + discountAmount;
						termToatlPaid = termToatlPaid + totalPaidAmount ;
					}
				}
		    }
				daysBetweenPaidlists = null;
				feePaymentDetails = null;
			}
			
	 }
	 	setTempString(String.valueOf(new DecimalFormat().format(termTotalAmount)));
		setBalance(String.valueOf((new DecimalFormat().format(termTotalAmount-(termToatlPaid + totalDiscountAmount)))));
		setTotalAmount(termToatlPaid);
		setFooter(String.valueOf(totalDiscountAmount));
}

 	@Transient
 	public boolean isCurrentAcademicYearId(long academicYearId){
 		Long currentAcademicYear = (Long)getSession().getAttribute("currentAcademicYearId");
 		if(ObjectFunctions.isNullOrEmpty(currentAcademicYear))
 			return false;
 		else if(currentAcademicYear == academicYearId)
 			return true;
 		else
 			return false;
 	} 
 	@Actions( {	@Action(value = "ajaxDownloadScorecardFromUserFiles", results = { 
 			@Result(location = "../hostel/ajaxViewStudentHostelInfo.jsp", name = "success") })
	})
 	public void ajaxDownloadScorecardFromUserFiles(){
 		try{
 			ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
			getResponse().setContentType("application/zip");
			getResponse().addHeader("Content-Disposition", "attachment; filename=ScoreCard.zip");
 			if(StringFunctions.isNotNullOrEmpty(getTempString())){
 				File directory = new File(getTempString());
 				if(directory.exists() && directory.listFiles().length > 0){
 					StringFunctions.zipFiles(directory,zipOutStream);
 				}else
 					adminManager.writeToFile("Scorecard not generated.",getSession().getServletContext().getRealPath(getTempString()+"readMe.doc"));
 			}else{
 				adminManager.writeToFile("Please contact us.",getSession().getServletContext().getRealPath(getTempString()+"readMe.doc"));
 			}
 			zipOutStream = null;
 		}catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
 	}
 	public HashMap<Boolean,String> validateStudentFileExistOrNot(String studentName,String admisssionNumber,long classSectionId,String customerShortName){
 		boolean filesExist = false;
 		StringBuffer filePath = new StringBuffer("userFiles/ScoreCardTemplates/").append(customerShortName).append("/ClassSection_").append(classSectionId).append("/")
		.append(studentName).append("_").append(admisssionNumber).append("/");
		String fieLocation = getSession().getServletContext().getRealPath(filePath.toString());
		File outFile = new File(fieLocation);
		if(outFile.exists() && outFile.listFiles().length > 0)
			filesExist = true;
		 HashMap<Boolean,String> fileDetails = new HashMap<Boolean,String>(1);
		 fileDetails.put(filesExist, fieLocation);
		 fieLocation = null;
		 return fileDetails;
 	}
 	
	/* Staff Upload/ Download documents */
	public String generateUploadFilepath(Customer customer,String memberName,long accountId,long staffId)
	{
		memberName = memberName.replaceAll(" ", "_");
		StringBuffer pathName = new StringBuffer("userFiles/Staff/");
		pathName.append(customer.getCustomerShortName()+getUserCustId()+memberName+"_"+accountId+staffId);
	    pathName.append("/");
		return pathName.toString();
	}
	public String generateTimtableUploadFilepath(Customer customer,long academicYearId,String staffOrClassTimeTable)
	{
		StringBuffer pathName = new StringBuffer("userFiles/timetable/");
		pathName.append(customer.getId()+"/"+academicYearId+"/"+staffOrClassTimeTable+"/");
		return pathName.toString();
	}
	public void downloadStaffDocuments(Customer customer,String memberName,long accountId,long staffId)
	{
		ZipOutputStream zipOutStream = null;
		try {
			File directory = Files.createTempDirectory("StaffDocuments").toFile();
			List<PersonDocuments> personDocumentsList = studentManager.getAll(PersonDocuments.class,"accountId="+accountId);
			if(!ObjectFunctions.isNullOrEmpty(personDocumentsList))
			{
				memberName = memberName.replaceAll(" ", "_");				 
				zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
				getResponse().setContentType("application/zip");
				getResponse().addHeader("Content-Disposition", "attachment; filename="+memberName+"_"+customer.getCustomerShortName().toUpperCase()+"_DOCUMENTS.zip"); 
				
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
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		 /*try{
			 ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
			   File directory = null;
				String pathName = null;
				 memberName = getAnyTitle();
				 memberName = memberName.replaceAll(" ", "_");
					getResponse().setContentType("application/zip");
					getResponse().addHeader("Content-Disposition", "attachment; filename="+memberName+"_"+customer.getCustomerShortName().toUpperCase()+"_DOCUMENTS.zip"); 
					pathName = generateUploadFilepath(customer,memberName,accountId,staffId);
					pathName = getSession().getServletContext().getRealPath(pathName);
					directory = new File(pathName);
					if(!ObjectFunctions.isNullOrEmpty(directory.listFiles())){
						if(directory.listFiles().length > 0)
						 StringFunctions.zipFiles(directory,zipOutStream);
						else
						 super.addActionError("There are no files found because you have not uploaded or you might be deleted.");
					}							
					else{
						super.addActionError("There are no files to download.");
					}
					
			zipOutStream = null;
		 }
		 catch(Exception ex){
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		 }*/
	}
	/**
	 * @description 
	 * @param msgs
	 */
	public void addActionMessages(Map<String,String> msgs)
	{
		try
		{
			/*Here I need to show different messages in circular meessage when we send email to any user. So for this reason I put different message for error and success @Ganesh*/
			String msg = null;
			if(!StringFunctions.isNullOrEmpty(msgs.get("1"))){
				msg =msgs.get("1");
				super.addActionError(msg);
			}else if(!StringFunctions.isNullOrEmpty(msgs.get("2"))){
				msg =msgs.get("2");
				super.addActionError(msg);
			}else if(!StringFunctions.isNullOrEmpty(msgs.get("3"))){
				msg =msgs.get("3");
				super.addActionError(msg);
			}else if(!StringFunctions.isNullOrEmpty(msgs.get("4"))){
				msg =msgs.get("4");
				super.addActionError(msg);
			}else if(!StringFunctions.isNullOrEmpty(msgs.get("5"))){
				msg =msgs.get("5");
				super.addActionError(msg);
			}
			msg = msgs.get("0");
			if (!StringFunctions.isNullOrEmpty(msg))
				super.addActionMessage(msg);
			msgs=null;
			msg=null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
	}
	
	public String sendIssuedBookMessage(User user,Customer customer,Messages messages) throws URTUniversalException{
		try{
			String[] email = null; 
			if(!StringFunctions.isNullOrEmpty(user.getPerson().getParentEmail()) || (!ObjectFunctions.isNullOrEmpty(user.getPrimaryAddress())  && !StringFunctions.isNullOrEmpty(user.getPrimaryAddress().getEmail())))
				email= new String[1];
			if(user.isSchoolStudent()){
				  if(!StringFunctions.isNullOrEmpty(user.getPerson().getParentEmail()))
					 email[0]=user.getPerson().getParentEmail(); 
				}else{
					if(!StringFunctions.isNullOrEmpty(user.getPrimaryAddress().getEmail()))
						email[0]=user.getPrimaryAddress().getEmail(); 
			    }
			if(!ObjectFunctions.isNullOrEmpty(email)){
				MailUtil mailUtil = new MailUtil(email, messages.getTitle(), messages.getMessageDescription(), getUser());
				mailUtil.sendEmailForIssuedBooks(user);
				mailUtil = null;
			}
			email = null;
			user=null;
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/*This method not working in SudentaAction. Means not getting Json responce in page -->ajaxViewExamResults.jsp .So moved this method to Base Action By cvs on 6-6-2014*/
	@Actions( { @Action(value = "ajaxGetStudentMarks", results = { @Result(type = "json", name = "success", params = {"includeProperties", "timingsList.*" }) }) })
	public String ajaxGetStudentMarks() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentMarks' method");
		}
		try {
			//getTempId1() is student Id
			if(getTempId1() > 0 ){
				JSONArray res = new JSONArray();
				JSONObject j;
				int sNo=0;
				//setStudentMarksList(studentManager.getAllMarksByStudId(getTempId1()));
				List<ViewStudentMarks> studentMarks = studentManager.getAllMarksByStudId(getTempId1(),getUserAcademicYearId());
				log.debug(getStudentMarksList().size());
				if(!ObjectFunctions.isNullOrEmpty(studentMarks)){
					for(ViewStudentMarks marks: studentMarks){
						if(!ObjectFunctions.isNullOrEmpty(marks))
						{
							j=new JSONObject();
						    if(subjectId!=String.valueOf(marks.getSubjectId())){
						    	sNo=0;
						    }
						if(marks.isPresent())
							j.put("INPUTVALUE",marks.getObtainedMarks());
						else
							j.put("INPUTVALUE","A");
						
						j.put("STUDENTID",marks.getStudId()); 
					    j.put("SUBJECTID",String.valueOf(marks.getSubjectId()));
					    j.put("EXAMSCHEDULEID",marks.getScheduleId());
					    /*if(marks.getSubjectId() == studentDetails.getOptionalSubjectId())
					    	j.put("optionalSubj",true);
					    else
					    	j.put("optionalSubj",false);*/
					    
					    log.debug("STUDENTID:" + marks.getStudId() + "    SUBJECTID:"+String.valueOf(marks.getSubjectId()) + "   INPUTVALUE:"+marks.getObtainedMarks()); 
						subjectId= String.valueOf(marks.getSubjectId());
						sNo++;
						res.put(j);
						}
						 
					}
					studentMarks=null;
				}
			j = new JSONObject();
			j.put("studentMarksSettingsData", res);
			getResponse().getOutputStream().print(j.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	//This method is for increasing clsssection capacity -------rama on 12/06/2014
	public void ajaxUpdateClassSectionCapacity(StudyClass studyClass){
		try
		{
			int classStrength = studentManager.getClassStudentsCountByClassIdandStatus(Long.valueOf(studyClass.getId()), Constants.YES_STRING,getUserCustId());
			int remainingSeats = 0;
			remainingSeats = studyClass.getSectionCapacity() - classStrength;
			if (remainingSeats <= 0) {
				studyClass.setSectionCapacity(studyClass.getSectionCapacity()+1);
				studyClass = (StudyClass)studentManager.saveOrUpdateObject(studyClass);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
	}
	//This method is for restricting  student attendance percentage to give feed back about staff and school.-----------rama on 26/06/2014
	public String ajaxCheckStudentspercentagewithFbSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckStudentspercentagewithFbSettings' method");
		}
		try {
			 if(!ObjectFunctions.isNullOrEmpty(getUser())){
				 AcademicYear academicYear = (AcademicYear)adminManager.get(AcademicYear.class,"id="+getUserAcademicYearId()+" and custId="+getUserCustId());
				 if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					 if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate())){
						 Student student = null;
						 FeedBackSettings fbSettings = null;
						 double percentage = 0;
						 double fbAttendance = 0;
						 int workingDays = 0;
						 int holidayscount = 0;
						 Date termStartDate = null;
						 Date termEndDate  = null;
						 String year = null;
						 int startMonth = 0;
						 int closeMonth = 0;
						 double totalworkingDays  = 0;
						 double totalpresentDays = 0;
						 ViewStudentsScoreCardProfileDetails stud = null;
						 if(getUser().isOnlySchoolAdmin()){
							 		int i;
							 		for(i=1 ; i<3 ;i++){
							 			if(i > 1){
							 				 year = DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, academicYear.getStartDate());
							 				 termStartDate = new Date("10/01/"+year+" 00:00:00");
											 termEndDate  = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear.getEndDate().toString());
							 			}
							 			else{
										 termStartDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear.getStartDate().toString());
										 year = DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, termStartDate);
										 termEndDate  =new Date("09/30/"+year+" 00:00:00");
							 			}
							 			  startMonth = DateFunctions.getMonthOfDate(termStartDate);
										  closeMonth = DateFunctions.getMonthOfDate(termEndDate);
										 int totalDays = DateFunctions.daysBetween(termStartDate,termEndDate);
										 holidayscount = adminManager.getCount("schoolHolidays", "custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and holidayDate between '"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,termStartDate)+"' and '"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,termEndDate)+"' ");
										 workingDays = totalDays - holidayscount;
										 if(!ObjectFunctions.isNullOrEmpty(getTempList())){
											 for(Object obj : getTempList()){
												 stud = (ViewStudentsScoreCardProfileDetails)obj;		
												 if(!ObjectFunctions.isNullOrEmpty(stud)){
													 if(academicYear.getManageAttendanceBy().equalsIgnoreCase("M") && StringFunctions.isNotNullOrEmpty(academicYear.getManageAttendanceBy())){
															Object[] attendancevalues =  adminManager.get("select sum(totalWorkingDays),sum(noOfPresentDays) from studentMonthlyAttendance where studentId="+stud.getStudId()+" and month between "+startMonth+" and  "+closeMonth+" ");
																if(!ObjectFunctions.isNullOrEmpty(attendancevalues)){
																	if(!ObjectFunctions.isNullOrEmpty(attendancevalues[0]))
																	 totalworkingDays = Double.valueOf(attendancevalues[0].toString());
																	if(!ObjectFunctions.isNullOrEmpty(attendancevalues[1]))
																	 totalpresentDays = Double.valueOf(attendancevalues[1].toString());
																	if(totalworkingDays > 0)
																	percentage = roundTwoDecimals((totalpresentDays / totalworkingDays)*100);
																	if(i >1){
																		stud.setTerm2Percentage(percentage);
																		stud.setTerm2WorkingDaysCount(totalworkingDays);
																	}
																	else{
																			stud.setTerm1Percentage(percentage);
																			stud.setTerm1WorkingDaysCount(totalworkingDays);
																		}
																	
													 }
														attendancevalues = null;
														totalpresentDays = 0;
														percentage = 0;
														totalworkingDays = 0;
												 }
												 if (academicYear.getManageAttendanceBy().equalsIgnoreCase("D") && StringFunctions.isNotNullOrEmpty(academicYear.getManageAttendanceBy())) {
													int abcentDays =  adminManager.getCount("studentDailyAttendance"," studentId="+stud.getStudId()+" and attendanceDate between '"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,termStartDate)+"' and '"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,termEndDate)+"' and present='"+Constants.NO_STRING+"' ");
													int totalPresentDays = workingDays - abcentDays;
														percentage = roundTwoDecimals(((double)totalPresentDays / (double)workingDays)*100);
														if(i >1){
															stud.setTerm2Percentage(percentage);
															stud.setTerm2WorkingDaysCount(workingDays);
														}
														else{
																stud.setTerm1Percentage(percentage);
																stud.setTerm1WorkingDaysCount(workingDays);
												}
														totalPresentDays = 0;
														percentage = 0;
														abcentDays = 0;
											}

										}
											 stud = null;
										obj = null;
									}
											 startMonth = 0;
											 closeMonth = 0;
											 workingDays = 0;
								}
										

							}
						}
						 if(getUser().isSchoolStudent()){
							 Date scStartDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear.getStartDate().toString());
							 Date toDayDate = new Date();
							 int totalDays = DateFunctions.daysBetween(scStartDate,toDayDate);
							 holidayscount = adminManager.getCount("schoolHolidays", "custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and holidayDate <='"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date())+"' ");
							  workingDays = totalDays - holidayscount;
								  student =(Student) adminManager.get(Student.class,"accountId="+getUser().getId()+" and custId="+getUserCustId()+" and academicYearId="+academicYear.getId());
								  fbSettings = (FeedBackSettings)adminManager.get(FeedBackSettings.class,"custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and status='"+Constants.ACTIVE_STATUS+"' ");
								 if(!ObjectFunctions.isNullOrEmpty(fbSettings)){
									 fbAttendance = Double.valueOf(fbSettings.getAttendancePercentage());
								}
							 if(!ObjectFunctions.isNullOrEmpty(student)){
								 if(academicYear.getManageAttendanceBy().equalsIgnoreCase("M") && StringFunctions.isNotNullOrEmpty(academicYear.getManageAttendanceBy())){
									Object[] attendancevalues =  adminManager.get("select sum(totalWorkingDays),sum(noOfPresentDays) from studentMonthlyAttendance where studentId="+student.getId()+"");
										if(!ObjectFunctions.isNullOrEmpty(attendancevalues)){
											 totalworkingDays = Double.valueOf(attendancevalues[0].toString());
											 totalpresentDays = Double.valueOf(attendancevalues[1].toString());
											percentage = roundTwoDecimals((totalpresentDays / totalworkingDays)*100);
									if(fbAttendance <= percentage){
										setTempString(String.valueOf(percentage));
									}
								}
								attendancevalues = null;
							}
							if (academicYear.getManageAttendanceBy().equalsIgnoreCase("D") && StringFunctions.isNotNullOrEmpty(academicYear.getManageAttendanceBy())) {
								int abcentDays =  adminManager.getCount("studentDailyAttendance"," studentId="+student.getId()+" and present='"+Constants.NO_STRING+"' ");
									int totalPresentDays = workingDays - abcentDays;
									percentage = roundTwoDecimals(((double)totalPresentDays / (double)workingDays)*100);
									if (fbAttendance <= percentage) {
										setTempString(String.valueOf(percentage));
									}
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

	public String organizationHeaderForPdf(String fontPath,PdfPTable headerReport) {
		  try{
			  Customer customer = getCustomerByCustId();

	            if (!ObjectFunctions.isNullOrEmpty(customer)) {
					if (!StringFunctions.isNullOrEmpty(customer.getOrganization())) {
						headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper("" + customer.getOrganization().toUpperCase(),100, fontPath, "#FFFFFF", 12,"#005CB9",Element.ALIGN_CENTER, 4.0f));
						// With out back ground color //   headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper("" + customer.getOrganization().toUpperCase(),100, fontPath, "#000000", 12,"#FFFFFF",Element.ALIGN_CENTER, 4.0f));  
					}
					if (!StringFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())) {
						headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndNormalFontColorFontSizeAndBackGrundColor(""+ customer.getOrganizationFullAddress(),100, fontPath, "#FFFFFF", 10,"#005CB9",Element.ALIGN_CENTER, 4.0f));
						// With out back ground color //   headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndNormalFontColorFontSizeAndBackGrundColor(""+ customer.getOrganizationFullAddress(),100, fontPath, "#000000", 10,"#FFFFFF",Element.ALIGN_CENTER, 4.0f));
					}
				}
	 	 }
	 	 catch(Exception ex){
	 		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	 	 }
		     return null;
	  }
	
	public byte[] getImage(Customer customer) throws IOException 
	{   
        InputStream is = new FileInputStream(getSession().getServletContext().getRealPath(customer.getCustomerOrgImage().getOriginalAttachmentFilePath()));               
        ByteArrayOutputStream img_bytes = new ByteArrayOutputStream(); 
        int b; 
        try { 
            while ((b = is.read()) != -1) { 
                img_bytes.write(b); 
            } 
        } finally { 
            is.close(); 
        } 
        return img_bytes.toByteArray(); 
    }
	 public int getDPI() { 
         return GraphicsEnvironment.isHeadless() ? 96 :  Toolkit.getDefaultToolkit().getScreenResolution(); 
	 } 
	public String generateUploadFeeReceiptFilepath(Customer customer)
	{
		StringBuffer pathName = new StringBuffer("userFiles/");
		pathName.append(customer.getCustomerShortName());
	    pathName.append("/feeReceipt");
	    pathName.append("/invoiceNo");
	    pathName.append("/");
		return pathName.toString();
	}
	@Actions({
		@Action(value = "ajaxDoSendLoginCredentialsMessages", results = { @Result(location = "../staff/sendLoginCredentials.jsp", name = "success" )})
		})
	public String ajaxDoSendLoginCredentialsMessages(){
		try{
			setCustomer(getCustomerByCustId());
			getSmsCount();
		}
		catch (Exception ex) {
			ex.getMessage();
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoSendLoginCredentials", results = { @Result(location = "../staff/staffCredentialsList.jsp", name = "success") }) })
	public String ajaxDoSendLoginCredentials() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSendLoginCredentials' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getAnyTitle())){
				if("N".equalsIgnoreCase(getAnyTitle()) || "AN".equalsIgnoreCase(getAnyTitle())){
					setObjectList(staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and roleId in (4,5,9,10,11,14,15,16,32,41,42,45,54,55) and (firstName!='' AND firstName is not null)"));
				}else if("S".equalsIgnoreCase(getAnyTitle()) || "AS".equalsIgnoreCase(getAnyTitle())){
					setObjectList(staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and roleId in (2,8,12,31,35,53)  and (firstName!='' AND firstName is not null)"));
				}else if("P".equalsIgnoreCase(getAnyTitle()) || "AP".equalsIgnoreCase(getAnyTitle())){
					setObjectList(staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and roleId in (7) and (firstName!='' AND firstName is not null)"));
				}else if("A".equalsIgnoreCase(getAnyTitle()) || "AA".equalsIgnoreCase(getAnyTitle())){
					setAnyTitle("S");
					setObjectList(adminManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and roleId in (2,8,12,31,35,4,9,10,11,14,15,16,32,41)  and (firstName!='' AND firstName is not null)"));
				}
			}
			if(StringFunctions.isNotNullOrEmpty(getClassId())){
				setViewStudentPersonAccountDetailsList(adminManager.getAll(ViewStudentPersonAccountDetails.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountExpired='N' and classSectionId='"+getClassId()+"' "));
			}
			
			setAvailableSMSCount(adminManager.getAvailableSmsCount(getUserCustId(), getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	public String ajaxDoSendMarksToMobile() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSendLoginCredentials' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getAnyTitle())){
				if("N".equalsIgnoreCase(getAnyTitle())){
					setObjectList(staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_AYAH' or roleName='ROLE_CLERK' or roleName='ROLE_STOREKEEPER' or roleName='ROLE_DRIVER' or roleName='ROLE_HELPER' or roleName='ROLE_HOSTEL' or roleName='ROLE_HOSTELFINANCE' or roleName='ROLE_MANAGEMENTTRAINEE' or roleName='ROLE_SWEEPER' or roleName='ROLE_PEON' or roleName='ROLE_TYPIST' or roleName='ROLE_WATCHMAN' or roleName='ROLE_ADMINOFFICER' or roleName='ROLE_CONDUCTOR' or roleName='ROLE_COMPUTEROPERATOR' or  roleName='ROLE_LIBRARIAN' or roleName='ROLE_LABASST' or roleName='ROLE_TRANSPORT'  or roleName='ROLE_TRANSPORTFINANCE' or roleName='ROLE_FINANCE' or roleName='ROLE_OTHERS' roleName='ROLE_STOREKEEPER' or roleName='ROLE_RECEPTIONIST' or roleName='ROLE_STAFF_NURSE') and (firstName!='' AND firstName is not null)"));
				}else if("S".equalsIgnoreCase(getAnyTitle())){
					setObjectList(staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_TEACHER' or roleName='ROLE_ADMIN_COORDINATOR' or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL' or roleName='ROLE_VICEPRINCIPAL') and (firstName!='' AND firstName is not null)"));
				}else if("P".equalsIgnoreCase(getAnyTitle())){
					setObjectList(staffManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and roleName='ROLE_PARENT' and (firstName!='' AND firstName is not null)"));
				}else if("A".equalsIgnoreCase(getAnyTitle())){
					setAnyTitle("S");
					setObjectList(adminManager.getAll(ViewUserRoles.class, "custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_TEACHER' or roleName='ROLE_ADMIN_COORDINATOR' or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL' or roleName='ROLE_VICEPRINCIPAL' or roleName='ROLE_AYAH' or roleName='ROLE_STOREKEEPER' or roleName='ROLE_CLERK' or roleName='ROLE_DRIVER' or roleName='ROLE_HELPER' or roleName='ROLE_HOSTEL' or roleName='ROLE_HOSTELFINANCE' or roleName='ROLE_MANAGEMENTTRAINEE' or roleName='ROLE_SWEEPER' or roleName='ROLE_PEON' or roleName='ROLE_TYPIST' or roleName='ROLE_WATCHMAN' or roleName='ROLE_ADMIN' or roleName='ROLE_ADMINOFFICER' or roleName='ROLE_CONDUCTOR' or roleName='ROLE_COMPUTEROPERATOR' or  roleName='ROLE_LIBRARIAN' or roleName='ROLE_LABASST' or roleName='ROLE_TRANSPORT'  or roleName='ROLE_TRANSPORTFINANCE' or roleName='ROLE_FINANCE' or roleName='ROLE_OTHERS' roleName='ROLE_STOREKEEPER' or roleName='ROLE_RECEPTIONIST' or roleName='ROLE_STAFF_NURSE') and (firstName!='' AND firstName is not null)"));
				}
			}
			if(getTempId() >0){
				SchoolShiftInfo schoolShiftObj=(SchoolShiftInfo) staffManager.get(SchoolShiftInfo.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+getTempId());
				if(ObjectFunctions.isNotNullOrEmpty(schoolShiftObj.getStaffShiftInfo())){
					for(Staff staffObj:schoolShiftObj.getStaffShiftInfo()){
						if(!ObjectFunctions.isNullOrEmpty(staffObj.getAccount()))
							getChkBoxSelectedAccountIds().add(String.valueOf(staffObj.getAccount().getId()));						
					}
				}schoolShiftObj=null;
			}
			if(StringFunctions.isNotNullOrEmpty(getClassId())){
				setViewStudentPersonAccountDetailsList(adminManager.getAll(ViewStudentPersonAccountDetails.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountExpired='N' and classSectionId='"+getClassId()+"' "));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({ @Action(value = "ajaxSendLoginCredentialsDetails", results = { @Result(location = "../staff/sendLoginCredentials.jsp", name = "success") }) })
	public String ajaxSendLoginCredentialsDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendLoginCredentialsDetails' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			setAcademicYear(adminManager.getCurrentAcademicYear("Y",getUserCustId()));
			/* @Ganesh The below code is customer specific because customer asking their own content so we are will check those customers and based on customer we will change message content */
			Properties urlConfigProp = getResouceBundleURLConfiguraionPropertiesFileDetails();
			String customerId = urlConfigProp.getProperty("s.credentiols.customerId");
			ArrayList<Long> arrayList = new ArrayList<Long>(Arrays.asList(customerId.split(",")).size());
			for (int i = 0; i < Arrays.asList(customerId.split(",")).size(); i++) {
				arrayList.add(Long.valueOf(customerId.split(",")[i].toString()));
			}
			boolean customerSpecific=arrayList.contains(getUserCustId());
			addActionMessages(communicationManager.sendLoginCredentials(getMessages(),getAcademicYear(),getUser(), getChkBoxSelectedIds(),getChkBoxSelectedAccountIds(),customerSpecific,getCustomer()));
			log.debug("End 'ajaxSendLoginCredentialsDetails' method");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		} finally {
			getSmsCount();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxUsersList", results = { @Result(location = "../staff/composeScrapMessage.jsp", name = "success") }) })
	public String ajaxUsersList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUsersList' method");
		}
		try {
			setPlTitle(getPlTitle());
			} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxDoGetCircularMessagesList", results = { @Result(location = "../staff/sendCircularAlertsMessagesHome.jsp", name = "success" )})
		})
	public String ajaxDoGetCircularMessagesList(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetCircularMessagesList' method");
		}
		try{
			List objectList= null;
			User account=getUser();
			if(!ObjectFunctions.isNullOrEmpty(account)){
				StringBuilder circularInfo=new StringBuilder();
				if(account.isSchoolAdmin()){
					objectList = staffManager.getAll("select circularDescription,circularDate from vw_circularusers where custId ="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" group by circularDescription,circularDate order by circularDate DESC " );
				}else {
					if(account.isParent()){
						circularInfo.append("circularStatus='AP'");
					}else if (account.isSchoolStudent()) {
						circularInfo.append("circularStatus='AC'");
					}else if(account.getRoleId() > 0){
						//2,8,12,31,35 Teaching Staff 
						// If anyone change the roles please update whereever we are using the roleId or roleName like this
						if(account.getRoleId()==2 || account.getRoleId()==8  || account.getRoleId()==12 || account.getRoleId()==31 || account.getRoleId()==35 ){
							circularInfo.append("circularStatus='AS'");
						}else{
							circularInfo.append("circularStatus='AN'");
						}
					}
					objectList = staffManager.getAll("select circularDescription,circularDate from vw_circularusers where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and  (userId="+account.getId()+" or "+circularInfo.toString()+" or circularStatus='AA') group by circularDescription,circularDate order by circularDate DESC " );
				}
				if(!ObjectFunctions.isNullOrEmpty(objectList))
				{
					setObjectList(objectList);
				}
			}
			account=null;
			objectList = null;
		}
		catch (Exception ex) {
			ex.getMessage();
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoSendCircularMessages", results = { @Result(location = "../staff/createCircularMessages.jsp", name = "success") }) })
	public String ajaxDoSendCircularMessages() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSendCircularMessages' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			setAcademicYear(adminManager.getCurrentAcademicYear("Y",getUserCustId()));
			getSmsCount();
			}catch (Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSaveCircularMessagesDetails", results = { @Result(location = "../staff/sendCircularAlertsMessagesHome.jsp", name = "success") }) })
	public String ajaxSaveCircularMessagesDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveCircularMessagesDetails' method");
		}
		try {
			List<User> usersList = null;
			Circular circular = null;
			if(StringFunctions.isNotNullOrEmpty(getSelectedId())){
				AcademicYear academicYearObj=adminManager.getCurrentAcademicYear("Y",getUserCustId());
				if(!ObjectFunctions.isNullOrEmpty(academicYearObj)){
					addActionMessages(communicationManager.addCircularMessageInfo(getSelectedId(),getMessages(),getUserCustId(),academicYearObj,getUser(),getChkBoxSelectedIds(), getChkBoxSelectedAccountIds(), getChkBoxClassSelectedIds(),getTempString(), false));
				}else{
					super.addActionError("Academic year details are not available.");
				}
				academicYearObj=null;
			}
				usersList = null;
				ajaxDoGetCircularMessagesList();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	public void createClassSections(Sheet sheet,AcademicYear academicYear,long custId) {
		try {
			log.debug("Started createClassSections........................");
			int rowSize = sheet.getRows();
			Cell cell;
			String className;
			String sectionName;
			String noOfStudents;
			ClassName classNameObj;
			Section sectionObj=null;
			String medium;
			String syllabusType;
			String classId;
			String groupNumber;
			for (int i = 1; i < rowSize; i++) {
				cell = sheet.getCell(0, i);
				className = cell.getContents();
				if (!StringFunctions.isNullOrEmpty(className)) {
					className = className.trim().toUpperCase();
					sectionName = sheet.getCell(1, i).getContents();
					noOfStudents = sheet.getCell(2, i).getContents();
					medium = sheet.getCell(3, i).getContents().toUpperCase();
					classId = sheet.getCell(4, i).getContents();
					groupNumber = sheet.getCell(5, i).getContents();
					syllabusType=sheet.getCell(7, i).getContents();
					if(StringFunctions.isNotNullOrEmpty(sectionName)){
						sectionName = sectionName.trim().toUpperCase();
						sectionObj = adminManager.getSectionBySectionName(sectionName, custId);
						if (ObjectFunctions.isNullOrEmpty(sectionObj)) {
							sectionObj = new Section();
							sectionObj.setCreatedById(getUser().getId());
							sectionObj.setCreatedDate(new Date());
						}else
							sectionObj.setLastUpdatedById(getUser().getId());
						sectionObj.setLastAccessDate(new Date());
						sectionObj.setLastUpdatedDate(new Date());
						sectionObj.setSection(sectionName);
						sectionObj.setCustId(custId);
						sectionObj = adminManager.saveSectionName(sectionObj);
					}
					classNameObj = adminManager.getClassByClassName(className, custId,academicYear.getId(),false);
					if (ObjectFunctions.isNullOrEmpty(classNameObj)) {
						classNameObj = new ClassName();
						classNameObj.setCreatedById(getUser().getId());
						classNameObj.setCreatedDate(new Date());
						int maxSortingOrder = adminManager.getMaxOfClassNameSortingOrder(academicYear.getId(), custId);
						classNameObj.setSortingOrder(maxSortingOrder + 1);
					}else
						classNameObj.setLastUpdatedById(getUser().getId());
					if("Y".equalsIgnoreCase(sheet.getCell(6, i).getContents()))
						classNameObj.setHigherStandard(true);
					else
						classNameObj.setHigherStandard(false);
					classNameObj.setClassName(className);
					classNameObj.setLastUpdatedDate(new Date());
					classNameObj.setLastAccessDate(new Date());
					classNameObj.setDescription(className);
					classNameObj.setCustId(custId);
					classNameObj.setAcademicYear(academicYear);
					classNameObj = adminManager.saveClassName(classNameObj);
					if (!StringFunctions.isNullOrEmpty(sectionName)) {
						if (!ObjectFunctions.isNullOrEmpty(classNameObj)) {
							if (!ObjectFunctions.isNullOrEmpty(sectionObj)) {
								createStudyClass(classNameObj, noOfStudents,sectionObj.getSection(),medium,classId,groupNumber,syllabusType);
							} else {
								createStudyClass(classNameObj, noOfStudents, "",medium,classId,groupNumber,syllabusType);
							}
						}
					} else {
						// In case no section value given in the sheet, just create a blank section record This is mandatory
						createStudyClass(classNameObj, noOfStudents, "",medium,classId,groupNumber,syllabusType);
					}
				}
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	/**
	 * @Description : Create Section
	 * @param sheet
	 */
	public void createStudyClass(ClassName cn,String capacity, String sectionName,String medium,String classId,String groupNumber,String syllabusType)
	{
		try
		{
			StudyClass studyClass= null;
			if(StringFunctions.isNotNullOrEmpty(classId)){
				 long id= Long.valueOf(classId);
				 studyClass =(StudyClass)adminManager.get(StudyClass.class, id);
				 if(ObjectFunctions.isNullOrEmpty(studyClass))
					{
						studyClass = new StudyClass();
						/*cn.setNoOfSections(cn.getNoOfSections()+1);
						cn=adminManager.saveClassName(cn);*/
						adminManager.updateQuery(" update class set noOfSections="+(cn.getNoOfSections()+1)+" where id ="+cn.getId());
					}
			}else{
				studyClass= adminManager.getclassByClassAndsection(cn.getClassName(),sectionName,cn.getCustId(),cn.getAcademicYearId());
				if (ObjectFunctions.isNullOrEmpty(studyClass))
				{
					studyClass = new StudyClass();
					/*cn.setNoOfSections(cn.getNoOfSections()+1);
					cn = adminManager.saveClassName(cn);*/

					adminManager.updateQuery(" update class set noOfSections="+(cn.getNoOfSections()+1)+" where id ="+cn.getId());
					
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(studyClass)){
				
				studyClass.setClassName(cn.getClassName());
				if(StringFunctions.isNotNullOrEmpty(capacity)){
					int studentsCount=studentManager.getClassStudentsCountByClassIdandStatus(studyClass.getId(),Constants.YES_STRING,cn.getCustId());
					int classCapacity=Integer.valueOf(capacity);
					if(classCapacity >= studentsCount)
						studyClass.setSectionCapacity(Integer.valueOf(capacity));
					else{
						log.debug("\n Section capacity should be more than actual capacity. Please reenter the capacity for class Name : "+studyClass.getClassName()+" "+studyClass.getSection());
					}
				studyClass.setSection(sectionName);
				studyClass.setClassNameClassId(cn);
				SyllabusType syllabusTypeObj  =null;
				if(!StringFunctions.isNullOrEmpty(syllabusType)){
					syllabusTypeObj =(SyllabusType)adminManager.get(SyllabusType.class, "syllabusTypeName='"+syllabusType.trim()+"'");
					if(!ObjectFunctions.isNullOrEmpty(syllabusTypeObj)){
						studyClass.setSyllabusType(syllabusTypeObj);
					}
					syllabusType= null;
				}
				Medium mediumId  =null;
				if(!StringFunctions.isNullOrEmpty(medium)){
					mediumId =(Medium)adminManager.get(Medium.class, "name='"+medium+"'");
					if(!ObjectFunctions.isNullOrEmpty(mediumId)){
						studyClass.setMediumId(mediumId);
					}
					mediumId= null;
				}
				studyClass.setGroupNumber(groupNumber);
				studyClass.setCustId(cn.getCustId());
				studyClass.setAcademicYear(cn.getAcademicYear());
				adminManager.save(studyClass);
				studyClass=null;
			}
			} 
			}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void loadClassAndSubjects(Sheet sheet,AcademicYear academicYear,long custId) {
		try {
			log.debug("Started loadClassAndSubjects........................");
			int rowSize = sheet.getRows();
			int columnSize = sheet.getColumns();
			String cellValue = "";
			StudySubject studySubject;
			StudyClass studyClass;
			Set<StudySubject> subjects;
			ViewClassSectionDetails classAndSection;
			StringBuffer query = null;
			int k=1;
			Map<String, StudySubject> subjectNames = new HashMap<String, StudySubject>();
			if(!ObjectFunctions.isNullOrEmpty(getTempString())){
				for (int j = 1; j < columnSize; j++) {
					try {
						studySubject = new StudySubject();
						if (!StringFunctions.isNullOrEmpty(sheet.getCell(j, 2).getContents().trim()))
							studySubject = adminManager.getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(custId, academicYear.getStrId(),sheet.getCell(j, 2).getContents().trim());
						if (!ObjectFunctions.isNullOrEmpty(studySubject)) {
							if (studySubject.getId() != 0)
								subjectNames.put(String.valueOf(j),
										studySubject);
						}
						studySubject = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				for (int i = 3; i < rowSize; i++) {
					if(!StringFunctions.isNullOrEmpty(sheet.getCell(0,i).getContents().trim()))
					{
						try {
							classAndSection = (ViewClassSectionDetails)adminManager.get(ViewClassSectionDetails.class,"classAndSection='"+sheet.getCell(0,i).getContents().trim()+"' and custId="+custId+" and academicYearId="+academicYear.getId()+"");
							log.debug("classAndSection='"+sheet.getCell(0,i).getContents().trim()+"' and custId="+custId+" and academicYearId="+academicYear.getId()+"");
							if(!ObjectFunctions.isNullOrEmpty(classAndSection)){
								studyClass = (StudyClass)adminManager.get(StudyClass.class,"id="+classAndSection.getClassSectionId()+" and custId="+custId+" and academicYearId="+academicYear.getId());
							if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
								subjects = new HashSet<StudySubject>();
								for (int j = 1; j < columnSize; j++) {
									cellValue = sheet.getCell(j,i).getContents();
									if (cellValue.equalsIgnoreCase("Y")) {
										if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j))))
										subjects.add(subjectNames.get(String.valueOf(j)));
									}
									else{
										if (cellValue.equalsIgnoreCase("N")) {
											if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j)))){
												int clsSubCount = studyClass.getSubjects().size();
												if(clsSubCount > 1){
													query  = new StringBuffer("classSectionId=").append(studyClass.getId()).append(" and studySubjectId in(").append(subjectNames.get(String.valueOf(j)).getId()).append(")").
													append(" and custId=").append(getUserCustId());
													adminManager.remove("classSubjectsSettings",query.toString()) ;
													query = null;
												}
											}
										}
									}
								}
								if (!ObjectFunctions.isNullOrEmpty(subjects)) {
									studyClass.setSubjects(subjects);
									adminManager.saveOrUpdateObject(studyClass);
								}
							}
							subjects=null;
							studyClass=null;
							}
							classAndSection = null;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else{
						log.debug("Class Not Found For :"+sheet.getCell(0,i).getContents());
						if(k > 5)
							break;
						k++;
	    			}
					/*else
						log.debug("Class Not Found For :"+sheet.getCell(0,i).getContents().trim());*/
					
				}
				subjectNames = null;
			}else{
				for (int j = 2; j < columnSize; j++) {
					try {
						studySubject = new StudySubject();
						if(!StringFunctions.isNullOrEmpty(sheet.getCell(j, 0).getContents().trim()))
						studySubject = adminManager.getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(custId, academicYear.getStrId(), sheet.getCell(j, 0).getContents().trim());
						if (!ObjectFunctions.isNullOrEmpty(studySubject)) {
							if(studySubject.getId() !=0)
							subjectNames.put(String.valueOf(j), studySubject);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int i = 1; i < rowSize; i++) {
					try {
						studyClass = adminManager.getclassByClassAndsection(sheet.getCell(0, i).getContents(), sheet.getCell(1, i).getContents(), custId,academicYear.getId());
						if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
							subjects = new HashSet<StudySubject>();
							for (int j = 2; j < columnSize; j++) {
								cellValue = sheet.getCell(j, i).getContents();
								if (cellValue.equalsIgnoreCase("Y")) {
									if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j))))
									subjects.add(subjectNames.get(String.valueOf(j)));
								}
							}
							if (!ObjectFunctions.isNullOrEmpty(subjects)) {
								studyClass.setSubjects(subjects);
								adminManager.save(studyClass);
							}
						}
						else{
							if(StringFunctions.isNullOrEmpty(sheet.getCell(0,i).getContents()))
							{
								log.debug("Class Not Found For :"+sheet.getCell(0,i).getContents());
								if(k > 5)
									break;
								k++;
							}
						}
						subjects=null;
						studyClass=null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void loadTeacherSubject(Sheet sheet,AcademicYear academicYear,long custId)
	{
		try
		{
			log.debug("Started Creating Teacher Subjects........................");
   			int rowSize = sheet.getRows();
	    	int columnSize = sheet.getColumns();
	    	StaffElgibleSubjects elgibleSubject=null;
	    	Cell cell;
			String[] acountRules = new String[3];
			cell = sheet.getCell(0, 0);
			acountRules[0] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(1, 0); 	
			acountRules[1] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(2, 0);
			acountRules[2] = StringFunctions.trim(cell.getContents());
			User staffAcc = null;
	    	Staff staff;
	    	StudySubject studySubject;
	    	StaffElgibleSubjects elgibleSub;
	    	String cellValue;
	    	String userName = null;
	    	Map<String, StudySubject> subjectNames = new HashMap<String, StudySubject>();
			for (int j = 2; j < columnSize; j++) {
				studySubject = new StudySubject();
				if (!StringFunctions.isNullOrEmpty(sheet.getCell(j, 1).getContents().trim())) 
				studySubject = adminManager.getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(custId, academicYear.getStrId(), sheet.getCell(j, 1).getContents().trim());
				if (!ObjectFunctions.isNullOrEmpty(studySubject)) {
					if(studySubject.getId()!=0)
						log.debug("class subject teacher :"+studySubject.getId()+"-"+studySubject.getName());
					subjectNames.put(String.valueOf(j), studySubject);
				}
				studySubject=null;
			}
	    	for(int i=1;i < rowSize; i++)
   	  	  	{
	    		userName = StringFunctions.prepareUserName(acountRules[0].toLowerCase(),sheet.getCell(
						Integer.valueOf(acountRules[1]), i).getContents().toLowerCase(),sheet.getCell(Integer.parseInt(acountRules[2]), i).getContents().toLowerCase());
	    		staffAcc = adminManager.getUserByUserName(userName);	
	    		if (!ObjectFunctions.isNullOrEmpty(staffAcc)) {
	    			staff = adminManager.getStaffByAcountId(staffAcc.getId(),Constants.YES_STRING);
	    			if (!ObjectFunctions.isNullOrEmpty(staff))
	   	    		{
	    				for(int j=3;j< columnSize;j++)
	   	    		    {
		  	   	    		cellValue = sheet.getCell(j, i).getContents();
							if (cellValue.equalsIgnoreCase("Y")) {
								if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j))))
								elgibleSubject=adminManager.getStudySubjectTeacherByStudyClassAndCustId(academicYear.getId(),staff.getId(),subjectNames.get(String.valueOf(j)).getId());
								if(ObjectFunctions.isNullOrEmpty(elgibleSubject)){
									elgibleSub = new StaffElgibleSubjects();
									elgibleSub.setCreatedDate(new Date());
									elgibleSub.setLastAccessDate(new Date());
									elgibleSub.setLastUpdatedDate(new Date());
		  			   	  		    elgibleSub.setAcademicYear(academicYear);
		  							elgibleSub.setStaffId(staff);
		  							elgibleSub.setStudySubjectId(subjectNames.get(String.valueOf(j)));
	  								elgibleSub.setCreatedById(getUser().getId());
		  							adminManager.save(elgibleSub);
								}else{
									log.debug("**** "+staff.getFullPersonName()+" is already assigned to  subject.");
								}
							}
							elgibleSub=null;	
	  	   	    		}
	    				
	   	    		}
	    		}
	    		staff = null;
	    		staffAcc = null;
	    	 }
	    	cell=null;
		}
		catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void loadClassSubjectTeacher(Sheet sheet,AcademicYear academicYear,long custId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'loadClassSubjectTeacher' method");
		}
		try
		{
			log.debug("Started loadClassSubjectTeacher........................");
   			int rowSize = sheet.getRows();
	    	int columnSize = sheet.getColumns();
	    	StudySubject studySubject;
	    	Cell cell;
	    	User staffAcc = null;
	    	String[] acountRules = new String[3];
	    	cell = sheet.getCell(0, 0);
			acountRules[0] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(1, 0); 	
			acountRules[1] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(2, 0);
			acountRules[2] = StringFunctions.trim(cell.getContents());
			String userName = null;
			//ClassTeacher classTeacher = null;
	    	StudyClass studyClass;
	    	ViewClassSectionDetails classAndSection;
	    	Staff staff;
	    	ViewStaffPersonAccountDetails staffObject = null;
	    	StaffElgibleSubjects elgibleSub;
	    	StaffElgibleSubjects elgibleSubject=null;
	    	Map<String, StudySubject> subjectNames = new HashMap<String, StudySubject>();
	    	int k=1;
	    	if(!ObjectFunctions.isNullOrEmpty(getTempString())){
				for (int j = 3; j < columnSize; j++) {
					studySubject = new StudySubject();
					studySubject = adminManager.getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(custId, academicYear.getStrId(), sheet.getCell(j, 2).getContents().trim());
					if (!ObjectFunctions.isNullOrEmpty(studySubject)) {
						subjectNames.put(String.valueOf(j), studySubject);
					}
				}
		    	for(int i=3;i < rowSize; i++)
	   	  	    {
		    		try
		    		{
		    			if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(0,i).getContents()))
			    		{
			    			staffObject = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class,"fullName='"+sheet.getCell(0,i).getContents()+"' and custId="+custId+" and academicYearId="+academicYear.getStrId()+" and status='Y' ");
			    			if (!ObjectFunctions.isNullOrEmpty(staffObject))
			    			{
			    				staff = (Staff)adminManager.get(Staff.class, "id="+staffObject.getStaffId()+" and custId="+custId+" and academicYearId="+academicYear.getId());
			    				log.debug("staffid="+staffObject.getStaffId()+" and custId="+custId+" and academicYearId="+academicYear.getId());
		    					if(!ObjectFunctions.isNullOrEmpty(staff))
		    					{
		    						classAndSection = (ViewClassSectionDetails)adminManager.get(ViewClassSectionDetails.class,"classAndSection='"+sheet.getCell(1,i).getContents().trim()+"' and custId="+custId+" and academicYearId="+academicYear.getId()+"");
		    						log.debug("classAndSection='"+sheet.getCell(1,i).getContents()+"' and custId="+custId+" and academicYearId="+academicYear.getId()+"");
			    					if(!ObjectFunctions.isNullOrEmpty(classAndSection)){
			   							studyClass = (StudyClass)adminManager.get(StudyClass.class,"id="+classAndSection.getClassSectionId()+" and custId="+custId+" and academicYearId="+academicYear.getId());
					    			if (!ObjectFunctions.isNullOrEmpty(studyClass)){
					    			for(int j=3;j< columnSize;j++){
					    				if(sheet.getCell(j,i).getContents().equalsIgnoreCase("Y")){	
					    					if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j)))){
												elgibleSubject=adminManager.getStudySubjectTeacherByStudyClassAndCustId(academicYear.getId(),staff.getId(),subjectNames.get(String.valueOf(j)).getId());
												if(ObjectFunctions.isNullOrEmpty(elgibleSubject)){
													elgibleSub = new StaffElgibleSubjects();
													elgibleSub.setCreatedDate(new Date());
													elgibleSub.setLastAccessDate(new Date());
													elgibleSub.setLastUpdatedDate(new Date());
						  			   	  		    elgibleSub.setAcademicYear(academicYear);
						  							elgibleSub.setStaffId(staff);
						  							elgibleSub.setStudySubjectId(subjectNames.get(String.valueOf(j)));
					  								elgibleSub.setCreatedById(getUser().getId());
						  							adminManager.save(elgibleSub);
						  							elgibleSub = null;
												}else{
													if(!ObjectFunctions.isNullOrEmpty(elgibleSubject))
													{
														ClassTeacher classTeacher=adminManager.getStudySubjectTeacherByStudyClassAndCustId(custId,academicYear.getId(),staffObject.getStaffId(),studyClass.getId(),subjectNames.get(String.valueOf(j)).getId());
							   	    					if(!ObjectFunctions.isNullOrEmpty(classTeacher))
							   	    						adminManager.remove(ClassTeacher.class, classTeacher.getId());
							   	    						classTeacher = null;
															log.debug("**** "+staff.getFullPersonName()+" is already assigned to  subject.");
													}
											}
										}
									}
						   	    	if(sheet.getCell(j,i).getContents().equalsIgnoreCase("T")){	
				   	    				if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j)))){
											elgibleSubject=adminManager.getStudySubjectTeacherByStudyClassAndCustId(academicYear.getId(),staff.getId(),subjectNames.get(String.valueOf(j)).getId());
											if(ObjectFunctions.isNullOrEmpty(elgibleSubject)){
												elgibleSub = new StaffElgibleSubjects();
												elgibleSub.setCreatedDate(new Date());
												elgibleSub.setLastAccessDate(new Date());
												elgibleSub.setLastUpdatedDate(new Date());
					  			   	  		    elgibleSub.setAcademicYear(academicYear);
					  							elgibleSub.setStaffId(staff);
					  							elgibleSub.setStudySubjectId(subjectNames.get(String.valueOf(j)));
				  								elgibleSub.setCreatedById(getUser().getId());
					  							adminManager.save(elgibleSub);
					  							elgibleSub = null;
											}
											ClassTeacher classTeacher=adminManager.getStudySubjectTeacherByStudyClassAndCustId(custId,academicYear.getId(),staffObject.getStaffId(),studyClass.getId(),subjectNames.get(String.valueOf(j)).getId());
					   	    				if(ObjectFunctions.isNullOrEmpty(classTeacher)){
					   	    					classTeacher = new ClassTeacher();
						   	    				if(sheet.getCell(2,i).getContents().equalsIgnoreCase("Y"))
						   	   	    			{
						   	    					classTeacher.setClassTeacher(Boolean.TRUE);
						   	   	    			}
						   	    				else
						   	    				{
						   	    					classTeacher.setClassTeacher(Boolean.FALSE);
						   	    				}
												classTeacher.setStudyClass(studyClass);
												classTeacher.setStaff(staff);
												classTeacher.setStudySubject(subjectNames.get(String.valueOf(j)));
												classTeacher.setAcademicYear(academicYear);
												classTeacher.setCustId(custId);
												adminManager.save(classTeacher);
					   	    				}else{
					   	    					if(!ObjectFunctions.isNullOrEmpty(classTeacher)){
						   	    					if(sheet.getCell(2,i).getContents().equalsIgnoreCase("Y")){
							   	    					classTeacher.setClassTeacher(Boolean.TRUE);
							   	   	    			}
							   	    				else{
							   	    					classTeacher.setClassTeacher(Boolean.FALSE);
							   	    				}
						   	    					adminManager.save(classTeacher);
						   	    					log.debug("*** "+staff.getFullPersonName()+" is already assigned to "+studyClass.getClassAndSection()+" Class "+subjectNames.get(String.valueOf(j)).getName()+" subject.");
					   	    					}
					   	    				else
												log.debug("**** "+staff.getFullPersonName()+" is already assigned to  subject.");
		
											}
					   	    				classTeacher =null;
										}
						   	    	}
				   	    			else{
				   	    				if(sheet.getCell(j,i).getContents().equalsIgnoreCase("N"))
				   	    				{
				   	    					if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j))))
				   	    					{
				   	    						int TScount = adminManager.getCount("classTeacher", "teacherId="+staff.getId()+"  and studySubjectId="+subjectNames.get(String.valueOf(j)).getId()+" and custId="+custId+" and academicYearId="+academicYear.getId()+" ");
						   	    				if(TScount >0){
													classTeacher=adminManager.getStudySubjectTeacherByStudyClassAndCustId(custId,academicYear.getId(),staffObject.getStaffId(),studyClass.getId(),subjectNames.get(String.valueOf(j)).getId());
						   	    					if(!ObjectFunctions.isNullOrEmpty(classTeacher))
						   	    						adminManager.remove(ClassTeacher.class, classTeacher.getId());
						   	    				}
						   	    				 if(!ObjectFunctions.isNullOrEmpty(subjectNames.get(String.valueOf(j)))){
								   	    			int  EScount = adminManager.getCount("staffElgibleSubjects", "staffId="+staff.getId()+" and academicYearId="+academicYear.getId()+" ");
								   	    			if(EScount >1){
								   	    				elgibleSubject=adminManager.getStudySubjectTeacherByStudyClassAndCustId(academicYear.getId(),staff.getId(),subjectNames.get(String.valueOf(j)).getId());
														if(!ObjectFunctions.isNullOrEmpty(elgibleSubject))
															adminManager.remove(StaffElgibleSubjects.class,elgibleSubject.getId());
														}
													}
				   	    						}
											}
										}
									}
								}
					    			classAndSection = null;
						}
						else
						{
	    					if (ObjectFunctions.isNullOrEmpty(classAndSection)){
			    				log.debug("No Class Find for : " + sheet.getCell(1,i).getContents() + "***");
			    				continue;
								}
							}
						}
		    					k=0;
		    					staff = null;
						}else{
		    				if (ObjectFunctions.isNullOrEmpty(staffObject)){
			    				log.debug("********No Staff Find for : " + sheet.getCell(0,i));
			    				continue;
		    				}
		    			}
			    			staffObject = null;
			    	}
					else{
						if(StringFunctions.isNullOrEmpty(sheet.getCell(0,i).getContents()))
						{
							log.debug("********No Staff Name found for : " + sheet.getCell(0,i));
							if(k > 5)
								break;
							k++;
						}
	    			}
				}
	    		catch (Exception ex) {
		    		log.debug(" Error Saving Class TEacher : " + ex.getMessage());
		    		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    		 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
				}
			}
		    	staffObject = null;
		    	elgibleSubject = null;
		    	classAndSection = null;
		    	staff = null;
		    	studyClass = null;
		    	subjectNames = null;
		    	studySubject = null;
			}
	    	else{
	    		for (int j = 5; j < columnSize; j++) {
					studySubject = new StudySubject();
					studySubject = adminManager.getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(custId, academicYear.getStrId(), sheet.getCell(j,1).getContents().trim());
					if (!ObjectFunctions.isNullOrEmpty(studySubject)) {
						subjectNames.put(String.valueOf(j), studySubject);
					}
				}
		    	for(int i=1;i < rowSize; i++)
	   	  	    {
		    		try
		    		{
		    			userName = StringFunctions.prepareUserName(acountRules[0].toLowerCase(),sheet.getCell(Integer.valueOf(acountRules[1]), i).getContents().toLowerCase(),sheet.getCell(Integer.parseInt(acountRules[2]),i).getContents().toLowerCase());
			    		staffAcc = adminManager.getUserByUserName(userName);
			    		if (!ObjectFunctions.isNullOrEmpty(staffAcc)) {
			    			staff = adminManager.getStaffByAcountIdAndCustId(staffAcc.getId(),Constants.YES_STRING,getUserCustId());
			    			if (ObjectFunctions.isNullOrEmpty(staff))
			    			{
			    				log.debug("********No Staff Find for : " + sheet.getCell(Integer.valueOf(acountRules[1]),i));
			    				continue;
			    			}
			    			studyClass = adminManager.getclassByClassAndsection(sheet.getCell(2,i).getContents(), sheet.getCell(3,i).getContents(),custId,academicYear.getId());
			    			if (ObjectFunctions.isNullOrEmpty(studyClass))
		   					{
			    				log.debug("No Staff Find for : " + sheet.getCell(2,i).getContents() + " Section : ***"+ sheet.getCell(3,i).getContents()+"***");
			    				continue;
		   					}
			    			
			    			for(int j=5;j< columnSize;j++)
			   	    		{
			    				
			   	    			if(sheet.getCell(j,i).getContents().equalsIgnoreCase("Y"))
			   	    			{
			   	    				ClassTeacher classTeacher=adminManager.getStudySubjectTeacherByStudyClassAndCustId(custId,academicYear.getId(),staff.getId(),studyClass.getId(),subjectNames.get(String.valueOf(j)).getId());
			   	    				if(ObjectFunctions.isNullOrEmpty(classTeacher)){
			   	    					classTeacher = new ClassTeacher();
				   	    				if(sheet.getCell(4,i).getContents().equalsIgnoreCase("Y"))
				   	   	    			{
				   	    					classTeacher.setClassTeacher(Boolean.TRUE);
				   	   	    			}
				   	    				else
				   	    				{
				   	    					classTeacher.setClassTeacher(Boolean.FALSE);
				   	    				}
										classTeacher.setStudyClass(studyClass);
										classTeacher.setStaff(staff);
										classTeacher.setStudySubject(subjectNames.get(String.valueOf(j)));
										classTeacher.setAcademicYear(academicYear);
										classTeacher.setCustId(custId);
										adminManager.save(classTeacher);
			   	    				}else{
			   	    					log.debug("*** "+staff.getFullPersonName()+" is already assigned to "+studyClass.getClassAndSection()+" Class "+subjectNames.get(String.valueOf(j)).getName()+" subject.");
			   	    				}
			   	    				classTeacher = null;
			   	    			}
			   	    		}
				    	}
			    		else
			    		{
			    			log.debug("**** Load Class Teacher Subjects : Staff Not found : " +  sheet.getCell(Integer.valueOf(acountRules[1]), i).getContents() );
			    			continue;
			    		}
		    		}
		    		catch (Exception ex) {
			    		log.debug(" Error Saving Class TEacher : " + ex.getMessage());
			    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
					}
				}
			}
	   cell=null;
		}
		catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	} 
	/**
	 * @description Load subjects by reading the sheel tab called 'Subject'
	 * @param sheet
	 */
	public void loadSubjects(Sheet sheet,AcademicYear academicYear,long custId) {
		try
		{
				int rowSize = sheet.getRows();
				Cell cell;
				String subjectName;
				String subjectShortName;
				String subjectNumber;
				StudySubject studySubject = null;
				StudySubject studySubjectNumber = null;
				for (int i = 1; i < rowSize; i++) {
					cell = sheet.getCell(0, i);
					subjectName = cell.getContents();
					cell = sheet.getCell(1, i);
					subjectShortName = cell.getContents();
					cell = sheet.getCell(2, i);
					subjectNumber = cell.getContents();
					if (!StringFunctions.isNullOrEmpty(subjectName.trim())) {
							studySubject = adminManager.getStudySubjectsBySubjectNameAndCustIdAndAcademicYear(custId, academicYear.getStrId() ,subjectName);
							if (ObjectFunctions.isNullOrEmpty(studySubject)) {
								studySubject = new StudySubject();
								studySubject.setCustId(custId);
								studySubject.setName(subjectName);
								studySubject.setAcademicYear(academicYear);
								studySubject.setDescription(subjectShortName);
								studySubject.setSubjectNumber(subjectNumber);
								adminManager.save(studySubject);
								studySubject = null;
							}else{
								studySubjectNumber= (StudySubject)adminManager.get(StudySubject.class, "subjectNumber="+studySubject.getId()+" and custId="+custId+" and academicYearId="+academicYear.getStrId());
								if (ObjectFunctions.isNullOrEmpty(studySubjectNumber)) {
									studySubject.setSubjectNumber(subjectNumber);
								}
							}
							studySubject = null;
					}
					else
					{
						log.debug("*****not created Subject Name:" + subjectName);
					}
					cell = null;
				}
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public void loadAcademicYearPlanner(Sheet sheet,AcademicYear academicYear,long custId) {
		try
		{
			if(!ObjectFunctions.isNullOrEmpty(academicYear)){
	            if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(0,1).getContents())){
	            	if(!ObjectFunctions.isNullOrEmpty(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(0,1).getContents())))
	            		 academicYear.setStartDate(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(0,1).getContents()));
	            }
	            if(!ObjectFunctions.isNullOrEmpty(sheet.getCell(1,1).getContents())){
	            	if(!ObjectFunctions.isNullOrEmpty(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(1,1).getContents())))
	            		 academicYear.setEndDate(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN, sheet.getCell(1,1).getContents()));
	            }
				adminManager.save(academicYear);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	
	public String LoadCustomerEnrollAndStaffSubjectsSheet(){
		 if (log.isDebugEnabled()) {
		        log.debug("Entering 'uploadingCustomerEnrollmentAndUpdateSheet' method");
		 }
 	 try{ 
 		 // Load work book and check for the failure
 		  if(!loadWorkBook()){
	  			log.debug("Failed to load the file....");
			    super.addActionError("Unable to load the file....");
			    return SUCCESS; 
 		  }
 		 long custId = 0;
 		 AcademicYear academicYear = null;
 		 if(!ObjectFunctions.isNullOrEmpty(getTempString())){
 			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("Configurations");
						
			long academicYearId = 0;
			if (ObjectFunctions.isNullOrEmpty(sheet)) {
				super.addActionError("File type not matched.");
				log.debug("No file to upload....");
				return "dummyInit";
				
				/*academicYearId = getUserAcademicYearId();
				custId = getUserCustId();*/
			} else {
				Row row = sheet.getRow(0);
				academicYearId = (long) row.getCell(1).getNumericCellValue();
				row = sheet.getRow(1);
				custId = (long) row.getCell(1).getNumericCellValue();
			}
			log.debug(" AcademicYearId****** "+academicYearId+" **** UserCustId***** "+custId);
			   academicYear = (AcademicYear) adminManager.get(AcademicYear.class, "id="+academicYearId+" and custId="+custId+" and status='Y' ");
 		 }
 		 else{
	  		  custId= getUserCustId();
	  		  academicYear = adminManager.getCurrentAcademicYear("Y", custId);
		   	  if (ObjectFunctions.isNullOrEmpty(academicYear)) {
		   			log.debug("You do not have a active academic year....");
	   		    super.addActionError("You do not have a active academic year.");
	   		    return SUCCESS; 
	   	  }
	   	  statelist = adminManager.getCountryStates(0);
 		 }
 		 boolean classSubject = true;
 		 boolean classSubjectTeacher = true;
  	      String sheetName=""; 
  	      Sheet sheet;
  	      for(int sheetNum=0; sheetNum < workbook.getNumberOfSheets(); sheetNum++){
  	    	sheet = workbook.getSheet(sheetNum);
  	    	if (!ObjectFunctions.isNullOrEmpty(sheet)) {
  	    		sheetName=sheet.getName();
  	  	    	log.debug(" **************** sheetName:" + sheetName); 
  	    		if (sheetName.equalsIgnoreCase("Subject")) {
  	    			loadSubjects(sheet,academicYear,custId);
  	    		}
  	    		else if (sheetName.equalsIgnoreCase("Staff")) {
  	    			staffManager.loadTeacher(sheet,"",custId,getUser().getId());
  	    		}
  	    		else if (sheetName.equalsIgnoreCase("Class-Section"))
  	    		{
  	    			createClassSections(sheet,academicYear,custId);
  	    		}
  	    		else if (sheetName.equalsIgnoreCase("Class-Subject") && classSubject)
	   	    	{
  	    			classSubject = false;
	   	    		loadClassAndSubjects(sheet,academicYear,custId);
	   	    	}
  	    		else if (sheetName.equalsIgnoreCase("Teacher-Subject"))
	   	    	{
  	    			loadTeacherSubject(sheet,academicYear,custId);
	   	    	}
  	    		else if(sheetName.equalsIgnoreCase("Class-Subject-Teacher") && classSubjectTeacher)
	   	    	{
  	    			classSubjectTeacher = false;
  	    			loadClassSubjectTeacher(sheet,academicYear,custId);
	   	    	}
  	    		else if(sheetName.equalsIgnoreCase("Academic Year Planner"))
	   	    	{
  	    			loadAcademicYearPlanner(sheet,academicYear,custId);
	   	    	}
  	    	}
  	      }
	    }
 	 	catch(Exception ex) {
    	 log.error("Entering into 'catch block':"+ex.getMessage());
    	 super.addActionError("System error! Please contact system administrator");
	 	 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 	JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	 }
		return SUCCESS;
	}
	public HashMap<String,String> validateStudentScoreCardFileExistOrNot(long classSectionId,List<ExamTypes> examTypesList,long accountId){
 		StringBuffer filePath = null;
 		 HashMap<String,String> sorecardfileDetails = new HashMap<String,String>();
 		if(!ObjectFunctions.isNullOrEmpty(examTypesList)){
 			for(ExamTypes examType : examTypesList){
 				if(!ObjectFunctions.isNullOrEmpty(examType))
			 		 filePath = new StringBuffer("userfiles/ccer/").append(getUserCustId()).append("/"+getUserAcademicYearId()).append("/").append("scorecards").append("/"+examType.getId()).append("/"+classSectionId).append("/")
			 		 	.append(accountId).append(".pdf");
 				String fileLocation = filePath.toString();
 				File path = new File(getSession().getServletContext().getRealPath(fileLocation));
 				File outFile = new File(fileLocation);
 				if(path.exists() && !path.isDirectory()){
 					sorecardfileDetails.put(examType.getExamType(), fileLocation);
 				}
 				outFile = null;
 				filePath = null;
 				fileLocation = null;
 				path = null;
 			}
 		}
		 return sorecardfileDetails;
 	}
	
	@Actions( { 
	    @Action(value = "ajaxCheckStaffUniqueNumber", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
		public String ajaxCheckStaffUniqueNumber() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckStaffUniqueNumber' method");
		}
		try {
			List staffUniqueNumbersList = null;
			String staffUniqueNumber = null;
			if(!StringFunctions.isNullOrEmpty(getAnyId())){
				String selectedId = getAnyId().replace("?keyWord=","-");
					String[] uniqueNumber = selectedId.split("-");
					if(uniqueNumber.length > 1){
					String personId = uniqueNumber[0];
					staffUniqueNumber = uniqueNumber[1];
					}
					else{
						staffUniqueNumber = getParamValue("keyWord");
					}
			}
			else{
				staffUniqueNumber = getParamValue("keyWord");
			}
			if (StringFunctions.isNotNullOrEmpty(staffUniqueNumber)) {
				staffUniqueNumbersList = adminManager.getAll(ViewStaffPersonAccountDetails.class," staffUniqueNumber like '"+staffUniqueNumber.trim()+"' and custId="+getUserCustId());
			}
			if (staffUniqueNumbersList.size() > 0) {
			    setAutoCheck("1");
			} else {
			    setAutoCheck("0");
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { 
	    @Action(value = "ajaxCheckStudentUniqueNumber", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
		public String ajaxCheckStudentUniqueNumber() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckStudentUniqueNumber' method");
		}
		try {
			List studentUniqueNumbersList = null;
			String studentUniqueNumber = null;
			if(!StringFunctions.isNullOrEmpty(getAnyId())){
				String selectedId = getAnyId().replace("?keyWord=","-");
					String[] uniqueNumber = selectedId.split("-");
					String personId = uniqueNumber[0];
					studentUniqueNumber = uniqueNumber[1];
					if (StringFunctions.isNotNullOrEmpty(studentUniqueNumber)) {
						studentUniqueNumbersList = adminManager.getAll(ViewStudentPersonAccountDetails.class," studentUniqueNumber like '"+studentUniqueNumber.trim()+"' and custId="+getUserCustId()+" and personId not in("+personId+")");
					}
			}
			else{
				studentUniqueNumber = getParamValue("keyWord");
				if (StringFunctions.isNotNullOrEmpty(studentUniqueNumber)) {
					studentUniqueNumbersList = adminManager.getAll(ViewStudentPersonAccountDetails.class," studentUniqueNumber like '"+studentUniqueNumber.trim()+"' and custId="+getUserCustId());
				}
			}
			if (studentUniqueNumbersList.size() > 0) {
			    setAutoCheck("1");
			} else {
			    setAutoCheck("0");
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	// User Login Detail *** 20/10/2014 *** Ramarao Jadapolu
	public void saveUserLoginMetaData(){
		try{
			ThreadPoolTaskExecutor userLoginMetaDataTaskExecutor = (ThreadPoolTaskExecutor) SpringContextAware.getBean("taskExecutor");
			UserLoginMetaDataThread userLoginMetaDataThread = new UserLoginMetaDataThread();
			userLoginMetaDataThread.setUserId(getUser().getId());
			userLoginMetaDataThread.setAcademicYearId(getUserAcademicYearId());
			userLoginMetaDataThread.setIpAddress(getUserIpAddress());
			userLoginMetaDataThread.setCustId(getUserCustId());
			userLoginMetaDataTaskExecutor.execute(userLoginMetaDataThread);
			/*LoginUserMetaData luMetaData = new LoginUserMetaData();
			luMetaData.setUserId(getUser().getId());
			luMetaData.setAcademicYearId(getUserAcademicYearId());
			luMetaData.setIpAddress(ipAddress);
			luMetaData.setCustId(getUserCustId());
			webUserMetaDataTaskExecutor.execute(luMetaData);*/
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public String generateStaffCurricularUploadFilepath(Customer customer,String memberName,long accountId,long staffId,StaffCurricularActivities staffCurricularActivities)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'generateStaffCurricularUploadFilepath' method");
		}
		memberName = memberName.replaceAll(" ", "_");
		StringBuffer pathName = new StringBuffer("userFiles/Staff/");
		pathName.append(customer.getCustomerShortName()+getUserCustId()+memberName+"_"+accountId+staffId);
		pathName.append("/curriculam/activityType"+staffCurricularActivities.getActivityTypeId()+"/"+staffCurricularActivities.getActivityName());
	    pathName.append("/");
		return pathName.toString();
	}
	
	public void downloadStaffCurricularDocuments(Customer customer,String memberName,Staff staff,StaffCurricularActivities staffCurricularActivities)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadStaffCurricularDocuments' method");
		}
		 try{
			 ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
			   File directory = null;
				String pathName = null;
				User account = staff.getAccount();
				 memberName = memberName.replaceAll(" ", "_");
					getResponse().setContentType("application/zip");
					getResponse().addHeader("Content-Disposition", "attachment; filename="+memberName+"_"+customer.getCustomerShortName().toUpperCase()+"_DOCUMENTS.zip"); 
					pathName = generateStaffCurricularUploadFilepath(customer,memberName,account.getId(),staff.getId(),staffCurricularActivities);
					pathName = getSession().getServletContext().getRealPath(pathName);
					directory = new File(pathName);
					if(!ObjectFunctions.isNullOrEmpty(directory.listFiles())){
						if(directory.listFiles().length > 0)
						 StringFunctions.zipFiles(directory,zipOutStream);
						else
						 super.addActionError("There are no files found because you have not uploaded or you might be deleted.");
					}							
					else{
						super.addActionError("There are no files to download.");
					}
					
			zipOutStream = null;
		 }
		 catch(Exception ex){
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		 }
	}
	public String generateStudentCurricularUploadFilepath(Customer customer,String memberName,long accountId,long studentId,StudentCurricularActivities studentCurricularActivities)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'generateStudentCurricularUploadFilepath' method");
		}
		memberName = memberName.replaceAll(" ", "_");
		StringBuffer pathName = new StringBuffer("userFiles/Student/");
		pathName.append(customer.getCustomerShortName()+getUserCustId()+memberName+"_"+accountId+studentId);
		pathName.append("/curriculam/activityType"+studentCurricularActivities.getActivityTypeId()+"/"+studentCurricularActivities.getActivityName());
	    pathName.append("/");
		return pathName.toString();
	}
	public void downloadStudentCurricularDocuments(Customer customer,String memberName,Student student,StudentCurricularActivities studentCurricularActivities)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadStaffCurricularDocuments' method");
		}
		 try{
			 ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
			   File directory = null;
				String pathName = null;
				User account = student.getAccount();
				 memberName = memberName.replaceAll(" ", "_");
					getResponse().setContentType("application/zip");
					getResponse().addHeader("Content-Disposition", "attachment; filename="+memberName+"_"+customer.getCustomerShortName().toUpperCase()+"_DOCUMENTS.zip"); 
					pathName = generateStudentCurricularUploadFilepath(customer,memberName,account.getId(),student.getId(),studentCurricularActivities);
					pathName = getSession().getServletContext().getRealPath(pathName);
					directory = new File(pathName);
					if(!ObjectFunctions.isNullOrEmpty(directory.listFiles())){
						if(directory.listFiles().length > 0)
						 StringFunctions.zipFiles(directory,zipOutStream);
						else
						 super.addActionError("There are no files found because you have not uploaded or you might be deleted.");
					}							
					else{
						super.addActionError("There are no files to download.");
					}
					
			zipOutStream = null;
		 }
		 catch(Exception ex){
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		 }
	}
	
	public int getMonthNumberByMonthName(String monthName) 
	{
		Date date;
		int monthNumber = 0;
		try {
			date = new SimpleDateFormat("MMM").parse(monthName);//put your month name here
			Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    monthNumber=cal.get(Calendar.MONTH) + 1;
		    
		    //log.debug(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		    cal = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		return monthNumber;
	}
	
	public String geMonthNameByMonthId(int monthId) 
	{
		 if (monthId >= 1 && monthId <= 12 ) {
			 String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			    return monthNames[monthId-1];
		 }
		return "";
	}
	
	public int getActualMonthDaysByMonthId(int monthId) 
	{
		if(monthId > 0) // month Id starts from 1 to 12
		{
			Calendar calendar =Calendar.getInstance();
	        calendar.set(Calendar.MONTH, monthId-1);
	        log.debug("Month Id:" + monthId + "   Month count:"+calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			//return geActualMonthDaysByMonthName(geMonthNameByMonthId(monthId));
		}
		return 0;
	}
	
	public int geActualMonthDaysByMonthName(String monthName) 
	{
		Date date;
		int actualMonthDays = 0;
		try {
			date = new SimpleDateFormat("MMM").parse(monthName);//put your month name here
			Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		   // monthNumber=cal.get(Calendar.MONTH) + 1;
		    actualMonthDays= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		    cal = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		return actualMonthDays;
	}
	
		public void addCommittedFeeActionMessages(Map<String,String> msgs)
		{
			try
			{
				if (!StringFunctions.isNullOrEmpty(msgs.get("10")))
					super.addActionError(msgs.get("10"));
				else if (!StringFunctions.isNullOrEmpty(msgs.get("11")))
					super.addActionError(msgs.get("11"));
				else if (!StringFunctions.isNullOrEmpty(msgs.get("12")))
					super.addActionError(msgs.get("12"));
				getCollectionAndFeeDuesList().putAll(msgs);
				msgs=null;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		
		}
		public HashMap<Integer,String> ajaxGetAllMonthNamesMonthIdByStartAndEndDate(Date fstartDate, Date fendDate) {
			int monthId;
			int yearId;
	  		if(!ObjectFunctions.isNullOrEmpty(fstartDate) && !ObjectFunctions.isNullOrEmpty(fendDate)){
	  			HashMap<Integer,String> academicYearWorkingDays = new HashMap<Integer,String>();
	  			Calendar cal = Calendar.getInstance();
				cal.setTime(fstartDate);
				Date todayDate=new Date();
				if(todayDate.after(fendDate) || todayDate.equals(fendDate)){
	        		for(Calendar startDate=cal;DateFunctions.compare2Dates(fendDate, startDate.getTime());){
	        			monthId=DateFunctions.getMonthOfDate(startDate.getTime());
						java.util.Date d = new java.util.Date(startDate.getTimeInMillis());
						academicYearWorkingDays.put(monthId,new SimpleDateFormat("MMMM").format(d));
						yearId = DateFunctions.getDayOfYear(startDate.getTime());
						//log.debug("monthId"+monthId+"MonthName "+new SimpleDateFormat("MMMM").format(d)+"yearId"+yearId);
						startDate.add(Calendar.MONTH, 1);
						startDate.set(Calendar.DATE, 1);
					}
	        	}else if(todayDate.before(fendDate)){
	        		for(Calendar startDate=cal;DateFunctions.compare2Dates(todayDate, startDate.getTime());){
						monthId=DateFunctions.getMonthOfDate(startDate.getTime());
						java.util.Date d = new java.util.Date(startDate.getTimeInMillis());
						academicYearWorkingDays.put(monthId,new SimpleDateFormat("MMMM").format(d));
						yearId = DateFunctions.getDayOfYear(startDate.getTime());
						startDate.add(Calendar.MONTH, 1);
						startDate.set(Calendar.DATE, 1);
					}
	        	}
	  			return academicYearWorkingDays;
				}
		  	return null;
		  }
		    
	    public Set<String> ajaxGetAllMonthNamesByStartAndEndDate(Date fstartDate, Date fendDate) {
			 HashSet<String> aListMonth = new HashSet<String>();
			 int monthId;
			 int yearId;
			if(!ObjectFunctions.isNullOrEmpty(fstartDate) && !ObjectFunctions.isNullOrEmpty(fendDate)){
				Calendar cal = Calendar.getInstance();
			cal.setTime(fstartDate);
			Date todayDate=new Date();
			if(todayDate.after(fendDate) || todayDate.equals(fendDate)){
	   		for(Calendar startDate=cal;DateFunctions.compare2Dates(fendDate, startDate.getTime());){
	   			monthId=DateFunctions.getMonthOfDate(startDate.getTime());
					java.util.Date d = new java.util.Date(startDate.getTimeInMillis());
					//academicYearWorkingDays.put(monthId,new SimpleDateFormat("MMMM").format(d));
					aListMonth.add(new SimpleDateFormat("MMMM").format(d));
					yearId = DateFunctions.getDayOfYear(startDate.getTime());
					//log.debug("monthId"+monthId+"MonthName "+new SimpleDateFormat("MMMM").format(d)+"yearId"+yearId);
					startDate.add(Calendar.MONTH, 1);
					startDate.set(Calendar.DATE, 1);
				}
		   	}else if(todayDate.before(fendDate)){
		   		for(Calendar startDate=cal;DateFunctions.compare2Dates(todayDate, startDate.getTime());){
						monthId=DateFunctions.getMonthOfDate(startDate.getTime());
						java.util.Date d = new java.util.Date(startDate.getTimeInMillis());
						aListMonth.add(new SimpleDateFormat("MMMM").format(d));
						yearId = DateFunctions.getDayOfYear(startDate.getTime());
						startDate.add(Calendar.MONTH, 1);
						startDate.set(Calendar.DATE, 1);
					}
		   	}
				return aListMonth;
			}
	 	return null;
	 }
    @Actions( { @Action(value = "ajaxCheckClassWiseCommittedFee", results = { @Result(type = "json", name = "success", params = {"includeProperties", "isCommittedFee" }) }) })
		public String ajaxCheckClassWiseCommittedFee()throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxCheckClassWiseCommittedFee' method");
			}
			try {
				StudyClass studyClass = null;
				long academicYearId =0;
				StringBuffer query = new StringBuffer();
				if(StringFunctions.isNotNullOrEmpty(getStudyClassId())){
					if("AD".equalsIgnoreCase(getTempString())){
						/* Below contition for in admission pending,short lint,rejected edit application we will get only stdy class. But in admitted application edit we will get both classId and studyClassId. that's ahy we put below condition.*/
						if(!StringFunctions.isNullOrEmpty(getClassId())){
							if(Long.valueOf(getClassId()) != 0)
								query.append("classNameClassId="+getClassId()+" and id="+getStudyClassId());
						}
						else
							query.append("classNameClassId="+getStudyClassId());
						studyClass = (StudyClass)adminManager.get(StudyClass.class, query.toString());
						academicYearId = getAcademicYearId();
					}else{
						studyClass = (StudyClass)adminManager.get(StudyClass.class, "id="+getStudyClassId());
						academicYearId = getUserAcademicYearId();
					}
					if(!ObjectFunctions.isNullOrEmpty(studyClass)){
						long categoryId = -1;
						if(!ObjectFunctions.isNullOrEmpty(getParamValue("categoryId")) && getParamValue("categoryId").matches("[0-9]+")){
							categoryId = Long.valueOf(getParamValue("categoryId"));
						}
						/* Here we will check committed fee fee class wise because In that class commiteed fee is not configured we have to disable committed fee input. Below parameters we no need to pass feeSettingIds that's why We pass empty. */
						double committedConfiguredFee = studentManager.getCommittedConfiguredFeeAmountCategoryWise(getUserCustId(),academicYearId,studyClass.getId(),categoryId,"");
						JSONObject commiteedFeeJson=null;
						if(committedConfiguredFee !=0 )
							commiteedFeeJson = new JSONObject().put("isCommittedFee", true);
						else
							commiteedFeeJson = new JSONObject().put("isCommittedFee", false);
						getResponse().getOutputStream().print(commiteedFeeJson.toString());
						commiteedFeeJson=null;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
    public void ajaxUpdateClassSectionAndFilledSeatsCapacity(StudyClass studyClass){
    	if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateClassSectionAndFilledSeatsCapacity' method");
		}
		try
		{
			int classStrength = studentManager.getClassStudentsCountByClassIdandStatus(Long.valueOf(studyClass.getId()), Constants.YES_STRING,getUserCustId());
			int  remainingSeats = studyClass.getSectionCapacity() - classStrength;
			if (remainingSeats <= 0) {
				studyClass.setSectionCapacity(classStrength);
			}
			studyClass.setFilledSeats(classStrength);
			studyClass = (StudyClass)studentManager.saveOrUpdateObject(studyClass);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
	}
    public String ajaxGenerateFeeReceiptPDFReport(ViewStudentClassDetails viewStudentClassDetails,List<ViewStudentFeePaymentDetails> studentPaymentList,double totalAmount,StudentPayment studentPayment,String feeSettingIds,Customer customer,AcademicYear academicYear,Student student,List<ViewStudentTransportFeePaymentDetails> studentTransportPaymentList) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGenerateFeeReceiptPDFReport' method");
			}
			double paymentAmount=0;
			double discountAmount=0;
			double paymentConcessionAmount=0;
			double feeAmount=0;
		//	String parentEmail = null;
			String fileName = null;
			double invoiceTotAmt = 0;
			double termBalanceAmount = 0;
			double totalBalanceAmount = 0;
			String invoiceNumber = null;
			PDFGenerator pDFGenerator = new PDFGenerator();
			boolean sendBalanceAmount = true;
			boolean sendPaymnetSMSToManagement = false;
			String roleIds = "";
			if(!ObjectFunctions.isNullOrEmpty(customer.getPreferences())){
				
				if(!ObjectFunctions.isNullOrEmpty(customer.getPreferences().getFeeBalanceAmountInPaymnetSMS())){
					if("N".equalsIgnoreCase(customer.getPreferences().getFeeBalanceAmountInPaymnetSMS())){
						sendBalanceAmount = false;
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(customer.getPreferences().getFeePaymentNotificationToManagement())){
					if("Y".equalsIgnoreCase(customer.getPreferences().getFeePaymentNotificationToManagement())){
						sendPaymnetSMSToManagement = true;
						boolean isFirst = true;
						for(Role role :customer.getPreferences().getRoles() ){
							if(isFirst){
								roleIds = Long.toString(role.getId());
								isFirst = false;
							}else{
								roleIds =  roleIds+","+Long.toString(role.getId());
							}
						}
					}
				}
			}
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			//Added By Siva for Countrywise currency
			//Country country = (Country)adminManager.get(Country.class, customer.getAddress().getCountryId());
			getResponse().setContentType(pDFGenerator.getMimeType());
			pDFGenerator.createDocumentJasper();
			//fileName = "FEE_RECEIPT_"+ invoiceNumber+"_"+DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
			if (academicYear.getReceiptGenerationType().equalsIgnoreCase("A") && !ObjectFunctions.isNullOrEmpty(studentPayment.getInvoiceNumber()))
				invoiceNumber = String.valueOf(studentPayment.getInvoiceNumber()).replaceAll("/", "_");
			else
				invoiceNumber = studentPayment.getInvoiceString().replaceAll("/", "_");
			if("future".equalsIgnoreCase(getFeePaymentType())) 
				fileName = "FUTURE_FEE_RECEIPT_"+ invoiceNumber+".pdf";
			else
				fileName = "FEE_RECEIPT_"+ invoiceNumber+".pdf";
			File file = new File(getSession().getServletContext().getRealPath("userfiles/tempFiles/"+fileName));
		      if (file.createNewFile()){
		        log.debug("File is created!" + fileName);
		      }else{
		        log.debug("File already exists.");
		      }
		    String pathName = generateUploadFeeReceiptFilepath(customer);
   		 	File destDir = new File(getSession().getServletContext().getRealPath(pathName+fileName));
   		 	FileUtils.copyFile(file, destDir);
			 
			pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), new FileOutputStream(file.getAbsolutePath())));
			pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), new FileOutputStream(destDir.getAbsolutePath())));
			PdfHeaderFooterMarkJasper phfmj = new PdfHeaderFooterMarkJasper();
			pDFGenerator.getPdfWriter().setPageEvent(phfmj);
			pDFGenerator.getDocument().open();
			file.deleteOnExit();
			if(!ObjectFunctions.isNullOrEmpty(studentPaymentList) || !ObjectFunctions.isNullOrEmpty(studentTransportPaymentList))
			{
				String fontPath = getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+ "/Droid-Sans/DroidSans-Bold.ttf");
				FontFactory.register(fontPath);
				// creating pDF page event to set header and Footer to document
				PdfPTable mainTable = new PdfPTable(1);
				mainTable.setWidthPercentage(100);
				mainTable.setSplitLate(false);
				mainTable.getDefaultCell().setBorder(Rectangle.BOX);
				int width =3;
				PdfPTable defaultersHeaderReport = new PdfPTable(width);
				defaultersHeaderReport.setWidthPercentage(100);
				PdfPTable headerReport = new PdfPTable(100);
				headerReport.setWidthPercentage(100);
				organizationHeaderForPdf(fontPath,headerReport);
				headerReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings("FEE RECEIPT ",100, fontPath));
				mainTable.addCell(headerReport);
				//parentEmail = viewStudentClassDetails.getParentEmail();			
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Student Name : " + viewStudentClassDetails.getFullName(),1,10, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",1,10, fontPath));
				if("T".equalsIgnoreCase(getPaymentType()))
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("   Date : " + getTodayDate(),1,10, fontPath));
				else
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("   Date : " + studentPayment.getPaymentDateStr(),1,10, fontPath));
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Admission No : " + viewStudentClassDetails.getAdmissionNumber(),1,10, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",1,10, fontPath));
				if(!StringFunctions.isNullOrEmpty(viewStudentClassDetails.getMobileNumber())) {
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Mobile No : " + viewStudentClassDetails.getMobileNumber(),1,10, fontPath));
				}
				else
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Mobile No : -- ",1,10, fontPath));
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("Class / Section : " + viewStudentClassDetails.getClassAndSection(),1,10, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",1,10, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" Father Name : " + viewStudentClassDetails.getFatherName(),1,10, fontPath));
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont("Receipt No :  " + invoiceNumber,1,10, fontPath));
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderFont(" ",width,10, fontPath));
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(" S.No",1, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Terms & Particulars ",1, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor("Amount",1, fontPath));
				
				long schoolTermsId = 0;
				String  alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				int i=1;
				double paidAmount = 0;
				char ch=0;
				int k=0;
				/*Paragraph first = new Paragraph();
				if(!ObjectFunctions.isNullOrEmpty(country.getCountryCurrency())){
					BaseFont bf = BaseFont.createFont(getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+ "/Arial/arial.ttf"), BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
					com.lowagie.text.Font font = new com.lowagie.text.Font(bf, 12);
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Paid Amount ",2,10, fontPath));
				        first.add(new Chunk(" \u20B9", font));
				}*/
				if(!ObjectFunctions.isNullOrEmpty(studentPaymentList)){
					for(ViewStudentFeePaymentDetails viewStudentFeePaymentDetails:studentPaymentList)
					{
						 ch= alphabet.charAt(k);
						if(viewStudentFeePaymentDetails.getSchoolTermId() != schoolTermsId)
						{
							i=1;
							
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor(""+ch,1, fontPath));
							k++;
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("FEES FOR THE MONTH OF : " + viewStudentFeePaymentDetails.getTermName(),1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("",1, fontPath));
							
						}
						
						paidAmount = viewStudentFeePaymentDetails.getPaymentAmount() + viewStudentFeePaymentDetails.getDiscountAmount();
						invoiceTotAmt = invoiceTotAmt + paidAmount;
						termBalanceAmount = viewStudentFeePaymentDetails.getTermwiseTotalBalanceAmount();
						totalBalanceAmount = viewStudentFeePaymentDetails.getTotalBalanceAmount();
						
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor(""+i,1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("" + viewStudentFeePaymentDetails.getFeeType(),1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor(""+ viewStudentFeePaymentDetails.getPaymentAmount(),1, fontPath));
						i++;
						
						schoolTermsId = viewStudentFeePaymentDetails.getSchoolTermId();
					}
					schoolTermsId=0;
				}
				if(!ObjectFunctions.isNullOrEmpty(studentTransportPaymentList)){
					for(ViewStudentTransportFeePaymentDetails studentTransportFeePaymentDetails:studentTransportPaymentList)
					{
						 ch= alphabet.charAt(k);
						if(studentTransportFeePaymentDetails.getSchoolTermId() != schoolTermsId)
						{
							i=1;
							
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor(""+ch,1, fontPath));
							k++;
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("FEES FOR THE MONTH OF : " + studentTransportFeePaymentDetails.getTermName(),1, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("",1, fontPath));
							
						}
						Object Object[] = adminManager.get("select totalBalanceAmount,termwiseTotalBalanceAmount from studentPayment where custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and studentId="+studentTransportFeePaymentDetails.getStudentId()+" and id="+studentTransportFeePaymentDetails.getStudentPaymentId());
						paidAmount = studentTransportFeePaymentDetails.getPaymentAmount() + studentTransportFeePaymentDetails.getDiscountAmount();
						invoiceTotAmt = invoiceTotAmt + paidAmount;
						termBalanceAmount = Double.valueOf(Object[1].toString());
						totalBalanceAmount =Double.valueOf(Object[0].toString());
						
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor(""+i,1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor("" + studentTransportFeePaymentDetails.getFeeType(),1, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignFontSizeJasperNoColor(""+ studentTransportFeePaymentDetails.getPaymentAmount(),1, fontPath));
						i++;
						
						schoolTermsId = studentTransportFeePaymentDetails.getSchoolTermId();
					}
					schoolTermsId=0;
				}
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Total Amount ",2,10, fontPath));
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+ roundTwoDecimals(invoiceTotAmt),1, fontPath));
				
				if(!StringFunctions.isNullOrEmpty(getWishTitle()))
				{
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Excess Amount ",2,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+getWishTitle(),1, fontPath));
				}
				if(!ObjectFunctions.isNullOrEmpty(getBalance()))
				{
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Used Excess Amount ",2,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+getBalance(),1, fontPath));
				}
				if(getThirtyTotalAmount() > 0)
				{
					invoiceTotAmt = invoiceTotAmt - getThirtyTotalAmount();
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Discount Amount ",2,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+roundTwoDecimals(getThirtyTotalAmount()),1, fontPath));
				}
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Paid Amount ",2,10, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+roundTwoDecimals(invoiceTotAmt),1, fontPath));
				
				if(!ObjectFunctions.isNullOrEmpty(customer.getPreferences()) && "Y".equalsIgnoreCase(customer.getPreferences().getVisibleTermWiseBalanceAmount())){
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Term Balance Amount ",2,10, fontPath));
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+roundTwoDecimals(termBalanceAmount),1, fontPath));
				}
				if("Y".equalsIgnoreCase(customer.getShowBalanceAmountInFeeReceipt())){
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithRightAlignJasperNoColorFont(" Balance Amount ",2,10, fontPath));
					if(getUserCustId()==180 || getUserCustId()==109 || getUserCustId()==116){
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+getBedId(),1, fontPath));
					}else
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithLeftAlignJasperNoColor(""+totalBalanceAmount,1, fontPath));

				}
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingsWithRightAlign("Authorized Signature",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(" ",width, fontPath));
				
				defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithAlignLeftNoColorWithNoBorderNormalFont("Fees will not be refunded at any circumstances.",width-1,8, fontPath));
				mainTable.addCell(defaultersHeaderReport);
				//pDFGenerator.getDocument().add(first);
				pDFGenerator.getDocument().add(mainTable);
				pDFGenerator.getDocument().close();
				pDFGenerator = null;
				mainTable = null;
				defaultersHeaderReport = null;
			}
		    phfmj = null;
			getSmsCount();
			if(!StringFunctions.isNullOrEmpty(viewStudentClassDetails.getParentEmail()) && customer.isCheckEmailService())
			{
				String[] emailAddresses = new String[1];
				String[] attachmentFiles = new String[1];
				attachmentFiles[0] = destDir.getAbsolutePath();
				emailAddresses[0] = viewStudentClassDetails.getParentEmail();
				log.debug(attachmentFiles[0]);
				String body = "We have received your children "+ viewStudentClassDetails.getFullName() +" payment of "+ roundTwoDecimals(invoiceTotAmt) +" on "+studentPayment.getPaymentDateStr()+". Please find the payment receipt from the attachment.";
				MailUtil mailUtil=new MailUtil(emailAddresses, "School Fee Payment", body,getUser() ,attachmentFiles,adminManager.getContactFromEmail(customer));
				mailUtil.setContactEmail(customer.getContactEmail());
				mailUtil.setContactPasword(customer.getContactPassword());
				mailUtil.sendEmailToFeeReceiptToParent(adminManager.getContactFromEmail(customer));
				mailUtil=null;
			}
			if(!StringFunctions.isNullOrEmpty(viewStudentClassDetails.getMobileNumber()) && customer.isCheckMobileService() && customer.isCheckMobilePaymentService() && (getSmsAlloted()!=0 && getSmsAlloted() > getSmsCnt()) )
			{
				double bal=0;
				boolean smsStatus = false;
				StringBuffer smsContent= new StringBuffer();
				Object[] totalAmount1 = null;
				Object[] totalAmount2 = null;
				StringBuffer query = null;
				StringBuffer feequery = null;
				query = new StringBuffer("select sum(feeAmount),classId from vw_classFeeDetails where custId="+getUserCustId()+" and categoryId="+viewStudentClassDetails.getCategoryId()+" and feeSettingId in "+feeSettingIds+" and academicYearId=");
				//feequery = new StringBuffer("select sum(paymentAmount),sum(discountAmount),sum(paymentConcessionAmount) from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and studentId="+viewStudentClassDetails.getStudId()+" and academicYearId=");
				if(getAcademicYearId()>0){ // here getAcademicyearId is a futureAcademiyearId
					if(!ObjectFunctions.isNullOrEmpty(viewStudentClassDetails.getFutureAcademicClassSecId())){
						query.append(getAcademicYearId()+" and classSectionId="+viewStudentClassDetails.getFutureAcademicClassSecId());
						totalAmount1=adminManager.get(query.toString());
						totalAmount2=adminManager.get("select sum(paymentAmount),sum(discountAmount) from vw_studentsFutureAcademicFeeDetails where custId="+getUserCustId()+" and studentId="
						+viewStudentClassDetails.getStudId()+" and futureAcademicId="+getAcademicYearId());
						
						if(!ObjectFunctions.isNullOrEmpty(totalAmount1[0]) && !ObjectFunctions.isNullOrEmpty(totalAmount2[0]) && !ObjectFunctions.isNullOrEmpty(totalAmount2[1])){
							bal=Double.valueOf(totalAmount1[0].toString())-(Double.valueOf(totalAmount2[0].toString())+Double.valueOf(totalAmount2[2].toString())
									+Double.valueOf(totalAmount2[1].toString()));
							smsContent.append("Dear Parent, We have received your children "+ viewStudentClassDetails.getFullName() +"  payment of Rs."+ roundTwoDecimals(invoiceTotAmt) +" on "
							+studentPayment.getPaymentDateStr()+". Your balance amount is Rs." +roundTwoDecimals(bal)+" Thank you from ");
							smsContent.append("Dear Parent, We have received your children "+ viewStudentClassDetails.getFullName() +"  payment of Rs."+ roundTwoDecimals(invoiceTotAmt) +" on "
									+studentPayment.getPaymentDateStr());
							if(sendBalanceAmount){
								smsContent.append(". Your balance amount is Rs." +roundTwoDecimals(bal)+" Thank you from ");
							}else{
								smsContent.append(". Thank you from ");
							}
						}
					}else
					{
						String sqlquery="select IFNULL(sum(feeAmount),0) as feeAmount,IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL(sum(IFNULL(concessionAmount,0)),0) as concessionAmount from "
								+ "vw_studentFeeParticularsPayment where studentId="+viewStudentClassDetails.getStudId()+ " and academicYearId="+getAcademicYearId()+" and deleteStatus='N'  and"
										+ " feeSettingId in " +feeSettingIds+" and feeSettingId !=3";
						String sqlTransQuery="select IFNULL((select SUM(IFNULL(feeAmount,0)) as feeAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+viewStudentClassDetails.getStudId()+" and academicYearId="+getAcademicYearId()+"),0) as feeAmount,IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL((select SUM(IFNULL(concessionAmount,0)) as concessionAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+viewStudentClassDetails.getStudId()+" and academicYearId="+getAcademicYearId()+"),0) as concessionAmount from "
								+ "vw_studentTransportFeePaymentDetails where studentId="+viewStudentClassDetails.getStudId()+ " and academicYearId="+getAcademicYearId()+" and deleteStatus='N' ";
						totalAmount1=adminManager.get(sqlquery);
						
						totalAmount2 = adminManager.get(sqlTransQuery);
						feeAmount =(Double.valueOf(totalAmount1[0].toString())+Double.valueOf(totalAmount2[0].toString()));
						paymentAmount=(Double.valueOf(totalAmount1[1].toString())+Double.valueOf(totalAmount2[1].toString()));
						discountAmount=(Double.valueOf(totalAmount1[2].toString())+Double.valueOf(totalAmount2[2].toString()));
                  		paymentConcessionAmount=(Double.valueOf(totalAmount1[3].toString())+Double.valueOf(totalAmount2[3].toString()));
						
						//if(!ObjectFunctions.isNullOrEmpty(totalAmount1[0]) && !ObjectFunctions.isNullOrEmpty(totalAmount1[0]) && !ObjectFunctions.isNullOrEmpty(totalAmount1[1])){
							bal=(feeAmount-(paymentAmount +discountAmount + paymentConcessionAmount));
							smsContent.append("Dear Parent, We have received your children "+ viewStudentClassDetails.getFullName() +"  payment of Rs."+ roundTwoDecimals(invoiceTotAmt) +" on "
									+ ""+studentPayment.getPaymentDateStr());
							if(sendBalanceAmount){
								smsContent.append(". Your balance amount is Rs." +roundTwoDecimals(bal)+" Thank you from ");
							}else{
								smsContent.append(". Thank you from ");
							}
						//}
						paymentAmount=0;
						discountAmount=0;
						paymentConcessionAmount=0;
						feeAmount=0;
					}
				}else{
					String sqlquery="select sum(IFNULL(feeAmount,0)) as feeAmount,sum(IFNULL(paymentAmount,0)) as paymentAmount,sum(IFNULL(discountAmount,0)) as discountAmount,sum(IFNULL(concessionAmount,0)) as concessionAmount "
							+ "from vw_studentFeeParticularsPayment where studentId="+viewStudentClassDetails.getStudId()+ " and academicYearId="+getUserAcademicYearId()+" and deleteStatus='N'  "
									+ "and feeSettingId in " +feeSettingIds+" and feeSettingId !=3";
					String sqlTransQuery="select IFNULL((select SUM(IFNULL(feeAmount,0)) as feeAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+viewStudentClassDetails.getStudId()+" and academicYearId="+getUserAcademicYearId()+"),0) as feeAmount,IFNULL(sum(IFNULL(paymentAmount,0)),0) as paymentAmount,IFNULL(sum(IFNULL(discountAmount,0)),0) as discountAmount,IFNULL((select SUM(IFNULL(concessionAmount,0)) as concessionAmount from vw_studentTransportFees where custId="+getUserCustId()+" and studentId="+viewStudentClassDetails.getStudId()+" and academicYearId="+getUserAcademicYearId()+"),0) as concessionAmount "
							+ "from vw_studentTransportFeePaymentDetails where studentId="+viewStudentClassDetails.getStudId()+ " and academicYearId="+getUserAcademicYearId()+" and deleteStatus='N'  ";
					totalAmount1=adminManager.get(sqlquery);
					totalAmount2 = adminManager.get(sqlTransQuery);
					//totalAmount2 = totalAmount1;
					totalAmount2 = adminManager.get(sqlTransQuery);
					feeAmount =(Double.valueOf(totalAmount1[0].toString())+Double.valueOf(totalAmount2[0].toString()));
					paymentAmount=(Double.valueOf(totalAmount1[1].toString())+Double.valueOf(totalAmount2[1].toString()));
					discountAmount=(Double.valueOf(totalAmount1[2].toString())+Double.valueOf(totalAmount2[2].toString()));
              		paymentConcessionAmount=(Double.valueOf(totalAmount1[3].toString())+Double.valueOf(totalAmount2[3].toString()));
					//if(!ObjectFunctions.isNullOrEmpty(totalAmount1[0])  && !ObjectFunctions.isNullOrEmpty(totalAmount1[1])){
						bal=(feeAmount-(paymentAmount + discountAmount + paymentConcessionAmount));
						smsContent.append("Dear Parent, We have received your children "+ viewStudentClassDetails.getFullName() +"  payment of Rs."+ roundTwoDecimals(invoiceTotAmt) +" on "
						+studentPayment.getPaymentDateStr());
						if(sendBalanceAmount){
							smsContent.append(". Your balance amount is Rs." +roundTwoDecimals(bal)+" Thank you from ");
						}else{
							smsContent.append(". Thank you from ");
						}
					//}
					paymentAmount=0;
					discountAmount=0;
					paymentConcessionAmount=0;
					feeAmount=0;
				}
				if(!ObjectFunctions.isNullOrEmpty(totalAmount1) && !ObjectFunctions.isNullOrEmpty(totalAmount2)){
					
					Messages message = new Messages();
					message.setMessageDescription(smsContent.toString());
					message.setStatus("M");
					message.setAcademicYear(academicYear);
					message.setCreatedById(getUser().getId());
					message.setCustomer(customer);
					message.setSmsSenderId(customer.getSender());
					String mobileType = customer.getMobileType();
					getMobileNumbersSet().addAll(adminManager.addMobileNumbersBasedOnAddressType(mobileType,viewStudentClassDetails.getMobileNumber(),viewStudentClassDetails.getSecondaryMobileNumber(),viewStudentClassDetails.getAnotherMobileNumber(),viewStudentClassDetails.getAnotherSecondaryMobileNumber(),viewStudentClassDetails.getAddressType()));

					if (!ObjectFunctions.isNullOrEmpty(getMobileNumbersSet())) {
						/*Student student = null;
						student = (Student) adminManager.get(Student.class, "custId="+viewStudentClassDetails.getCustId()+" and academicYearId="+viewStudentClassDetails.getAcademicYearId()+" and id="+viewStudentClassDetails.getStudId());
						*/message = communicationManager.saveMessageDetailsTracking(message,student,null,null);
						smsStatus = communicationManager.deliverSms(message,getMobileNumbersSet(),smsServiceProvider);
						setMobileNumbersSet(null);
					}
					if(sendPaymnetSMSToManagement){
						List<String> mobileNumbersList =  adminManager.getAllStaffMobileNumbersByRole(customer.getId(),roleIds);
						if(!ObjectFunctions.isNullOrEmpty(mobileNumbersList)){
							for(String number :mobileNumbersList){
								getMobileNumbersSet().add(number);
							}
							smsContent = new StringBuffer();;
							smsContent.append("Dear Management,"+ viewStudentClassDetails.getFullName() +","+viewStudentClassDetails.getClassAndSection()+ " has paid Rs."+ roundTwoDecimals(invoiceTotAmt) +" on "
									+studentPayment.getPaymentDateStr());
									if(sendBalanceAmount){
										smsContent.append(".Balance amount is Rs." +roundTwoDecimals(bal)+". Thank you from ");
									}else{
										smsContent.append(". Thank you from ");
									}
							message.setMessageDescription(smsContent.toString());
							message = communicationManager.saveMessageDetailsTracking(message,student,null,null);
							smsStatus = communicationManager.deliverSms(message,getMobileNumbersSet(),smsServiceProvider);
							setMobileNumbersSet(null);
						}
					}
					message = null;
					log.debug(smsStatus);
				}
			}
			
			file.deleteOnExit();
			destDir.deleteOnExit();
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
    public void checkStudyClassHaveHolidyOrNot(long custId, long academicYearId, List<StudyClass> studyClassList, String holidayDate) {
		if (log.isDebugEnabled()) {
			log.debug("Entering AdminManager 'createHolidays' method");
		}
		try {
			 int studentsCount=0;
				SchoolHolidays classHoliday=null;
				for (StudyClass studyClass : studyClassList) 
				{
					Object[] classNameClassIds= adminManager.get("select classId,className from vw_classSectionDetails where custId="+custId+ " and academicYearId="+academicYearId+" and classSectionId="+studyClass.getId());
		 			if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
		 				classHoliday=(SchoolHolidays)studentManager.get(SchoolHolidays.class, "custId="+custId+" and academicYearId="+academicYearId+" and classId="+Long.valueOf(classNameClassIds[0].toString())+" and holidayDate='"+holidayDate+"' ");
		 				if(ObjectFunctions.isNullOrEmpty(classHoliday)){ 
		 					studentsCount = studentManager.getClassStudentsCountByClassId(studyClass.getId(),custId);
		 					if(studentsCount > 0 )
		 						getStudyClassList().add(studyClass);
		 				}else{
		 					 getTempList1().add(studyClass);
		 				}
		 				if(studentsCount == 0 )
						  getTempList2().add(studyClass);
		 			}
		 			classNameClassIds=null;
				}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
    @Actions({ @Action(value = "ajaxChangePaymentMobileServiceStatus", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "status" }) }) })
	public String ajaxChangePaymentMobileServiceStatus() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxChangeMobileServiceStatus' method");
			}
			try {
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					if(customer.isCheckMobilePaymentService())
						customer.setCheckMobilePaymentService(false);
					else
						customer.setCheckMobilePaymentService(true);
				 customer=userManager.saveCustomer(customer);
				 setStatus(String.valueOf(customer.isCheckMobilePaymentService()));
				}
				
	    	} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    	 }
			return SUCCESS;
		}
    @Actions({ @Action(value = "ajaxChangeParentRegistrationMobileServiceStatus", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "status" }) }) })
	public String ajaxChangeParentRegistrationMobileServiceStatus() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxChangeParentRegistrationMobileServiceStatus' method");
			}
			try {
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					if(customer.isCheckParentSmsService())
						customer.setCheckParentSmsService(false);
					else
						customer.setCheckParentSmsService(true);
				 customer=userManager.saveCustomer(customer);
				 setStatus(String.valueOf(customer.isCheckParentSmsService()));
				}
				
	    	} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    	 }
			return SUCCESS;
		}
    
    @Actions({ @Action(value = "ajaxChangeAssignmentMobileServiceStatus", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "status" }) }) })
	public String ajaxChangeAssignmentMobileServiceStatus() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxChangeAssignmentMobileServiceStatus' method");
			}
			try {
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					if(customer.isCheckAssignmentSmsService())
						customer.setCheckAssignmentSmsService(false);
					else
						customer.setCheckAssignmentSmsService(true);
				 customer=userManager.saveCustomer(customer);
				 setStatus(String.valueOf(customer.isCheckAssignmentSmsService()));
				}
				
	    	} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    	 }
			return SUCCESS;
		}
    
    /* This for catching the conversion exception ----------- 4/03/2015 -------Rama */
    public boolean tryParseInt(String value) {  
        try  
        {  
            Integer.parseInt(value);  
            return true;  
         } catch(NumberFormatException nfe)  
         {  
             return false;  
         }  
   }
    
    public String generateMeetingMinutesUploadFilepath(long orgId,long meetingDetailsId)
	{
		StringBuffer pathName = new StringBuffer("userFiles/meetingMinutes/");
		pathName.append(orgId+"/"+meetingDetailsId);
	    pathName.append("/");
		return pathName.toString();
	}
    
    public void downloadMeetingMinutsFiles(long orgId,long meetingDetailsId,String fileName)
	{
		 try{
			 ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
			   File directory = null;
				String pathName = null;
				MeetingDetails meetingDetails = (MeetingDetails) adminManager.get(MeetingDetails.class, meetingDetailsId);
				 String memberName = meetingDetails.getMeetingAgenda().replaceAll(" ", "_");
					getResponse().setContentType("application/zip");
					getResponse().addHeader("Content-Disposition", "attachment; filename="+memberName+"_Files.zip"); 
					pathName = generateMeetingMinutesUploadFilepath(orgId,meetingDetailsId);
					pathName = getSession().getServletContext().getRealPath(pathName);
					directory = new File(pathName);
					if(!ObjectFunctions.isNullOrEmpty(directory.listFiles())){
						if(directory.listFiles().length > 0)
						{
							if(!StringFunctions.isNullOrEmpty(fileName))
							{
								StringFunctions.zipFiles(directory,zipOutStream);
							}
							else
								StringFunctions.zipFiles(directory,zipOutStream);
						}
						 
						else
						 super.addActionError("There are no files found because you have not uploaded or you might be deleted.");
					}							
					else{
						super.addActionError("There are no files to download.");
					}
					
			zipOutStream = null;
			meetingDetails = null;
		 }
		 catch(Exception ex){
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		 }
	}
    
    
    
    public void genereateTermWiseMarksReport() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxDownloadExamsMarkSheet' method");
   		}
   		try
   		{
   			StudyClass studyClass = null;
			if (getUserAcademicYearId()!=0) {
 				String fileName ;
 				StringBuffer query = null;
 				if (getUsername().equalsIgnoreCase("ROLE_HOSTEL")) {
					fileName = "Term Wise Marks Details In Hostel";
				}else{
					fileName = "Term Wise Marks Details In School";
				}
 				getResponse().setContentType("application/vnd.ms-excel");
 				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
 				HSSFSheet sheet = null;
 				List<Object[]> classStudentsList = null;
   				Row row = null;
   				org.apache.poi.ss.usermodel.Cell cell= null;
   				Integer col=null;
   				Object[] marks=null; 
				studyClass = (StudyClass) adminManager.get(StudyClass.class,"id="+getClassId()+"  and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" ");
   				int rowNum = 7;	
   				double obtainedMarks =0;
				double totalMarks = 0;
				double subTotalMarks = 0;
				double studTotalMarks = 0;
				double subMarks = 0;
				long subjectId = 0;
				long examTypeId = 0;
				int column = 0;
				double sumOfTotal = 0;
				double percentageMarks = 0;
				SchoolGrades gradeObjec = null;
				int calculatedMarks = 0;
				HashMap<String,Integer> examSchedulesCells = null;
   				if(!ObjectFunctions.isNullOrEmpty(studyClass)){
   					HSSFWorkbook wb = new HSSFWorkbook();
   					CellStyle style =  wb.createCellStyle();
   					style.setAlignment(CellStyle.ALIGN_RIGHT);
   					Map<String, CellStyle> styles = PrepareStudentExcel.createStyles(wb);
						sheet = (HSSFSheet) wb.createSheet(studyClass.getClassAndSection());
						sheet = (HSSFSheet) wb.createSheet("ScholasticConfig");
		 				sheet = wb.getSheet(studyClass.getClassAndSection());
		   				createExamSchedulesHeader(wb.getSheetAt(0),styles);
		   				createExamSchedulesHeader(wb.getSheetAt(1),styles);
						sheet.setColumnHidden(3, true);
						sheet = wb.getSheetAt(0);
						row = sheet.createRow(1);
						row.setHeightInPoints(25);
						sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:Z$2"));
						cell = row.createCell(0);
						query = new StringBuffer("Class Name : ").append(studyClass.getClassAndSection());
						cell.setCellValue(query.toString());
						query = null;
						cell.setCellStyle(styles.get("title"));
						if(getUser().isSchoolStudent() || getUser().isParent()){
							if(getUser().isParent()){
								StringBuffer sql = new StringBuffer();
								//sql.append("from Student stud WHERE stud.account.parentId=").append(getUser().getId()).append(" and stud.status='Y'").append(" and  classSectionId=").append(studyClass.getId());
								  sql.append("from Student stud WHERE stud.account.id=").append(getUser().getSelectedStudentId()).append(" and stud.status='Y'").append(" and  classSectionId=").append(studyClass.getId());
								List<Student> accntIds = adminManager.getAllHqlQuery(sql.toString());
								if(!ObjectFunctions.isNullOrEmpty(accntIds)){
									for(Student accntId :accntIds ){
										classStudentsList = adminManager.getAll("select studId,fullName,admissionNumber,rollNumber from vw_studentClassDetails where custId=" + getUserCustId()+ " and classSectionId="+ studyClass.getId()+ " and academicYearId="+ getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'"+" and studId ="+accntId.getId());
									}
								}
							}else{
							   classStudentsList = adminManager.getAll("select studId,fullName,admissionNumber,rollNumber from vw_studentClassDetails where custId=" + getUserCustId()+ " and classSectionId="+ studyClass.getId()+ " and academicYearId="+ getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'"+" and accountId="+getUser().getId());
							}
						}else{
							if (getUser().isSchoolHostel()) {
								classStudentsList = adminManager.getAll("select studId,fullName,admissionNumber,rollNumber from vw_studentClassDetails where custId=" + getUserCustId()+ " and classSectionId="+ studyClass.getId()+ " and academicYearId="+ getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'"+" and bedId!=0");
							}else{
								classStudentsList = adminManager.getAll("select studId,fullName,admissionNumber,rollNumber from vw_studentClassDetails where custId=" + getUserCustId()+ " and classSectionId="+ studyClass.getId()+ " and academicYearId="+ getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"' order by rollNumber");
								if("N".equalsIgnoreCase(customer.getAlphaNumericRollNumber()) || StringFunctions.isNullOrEmpty(customer.getAlphaNumericRollNumber()))
									Collections.sort(classStudentsList,new StudentRollNumberComparator());
							}
						}
						if(!ObjectFunctions.isNullOrEmpty(classStudentsList)){
							query = new StringBuffer("FROM ExamSchedules schedule WHERE schedule.classSection=").append(studyClass.getId()).append(" and schedule.examType.id in ").append(getExamType()).append(" and schedule.academicYear=").append(getUserAcademicYearId()+" and schedule.scheduled='"+Constants.YES_STRING+"' order by schedule.classSectionSubject,schedule.examType,schedule.subType");
							List<ExamSchedules> examScheduleDetails=adminManager.getAllHqlQuery(query.toString());
							List<ExamSchedules> examTypesList=adminManager.getAllHqlQuery("FROM ExamSchedules schedule WHERE schedule.classSection="+studyClass.getId()+" and schedule.examType.id in "+getExamType()+" and schedule.academicYear="+getUserAcademicYearId()+" and schedule.scheduled='"+Constants.YES_STRING+"' group by schedule.examType");
							createHeaderForActivities(wb.getSheetAt(1),styles,examTypesList,classStudentsList);
							query = null;	
							if(!ObjectFunctions.isNullOrEmpty(examScheduleDetails)){
	   							sheet.createFreezePane(4, 7);
								examSchedulesCells = createExamScheulesSubjectsHeader(sheet,styles,studyClass,examScheduleDetails,examTypesList);							
								if(!ObjectFunctions.isNullOrEmpty(examSchedulesCells)){
	   								for(Object[] studentDetails : classStudentsList){
	   									if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
	   										row = sheet.createRow(rowNum);
	   										cell=row.createCell(0);
	   										cell.setCellValue(studentDetails[3].toString());
	   										cell.setCellStyle(styles.get("headerInfo"));
	   										cell=row.createCell(1);
	   										cell.setCellValue(studentDetails[2].toString());
	   										cell.setCellStyle(styles.get("headerInfo"));
	   										cell=row.createCell(2);
	   										cell.setCellValue(studentDetails[1].toString());
	   										cell.setCellStyle(styles.get("headerInfo"));
  											 obtainedMarks =0;
  											 totalMarks = 0;
  											 studTotalMarks = 0;
  											 subMarks = 0;
  											 subjectId = 0;
  											 examTypeId = 0;
  											 column = 0;
  											 subTotalMarks = 0;
  											 for(ExamSchedules examSchedules : examScheduleDetails){
  												marks = adminManager.get(" select maxMarks,obtainedMarks,subTypeName,scheduleId,present,subjectId,examTypeId,subjectName,subtypeId from vw_studentExamMarks where studId="+studentDetails[0].toString()+" and examTypeId="+examSchedules.getExamTypeId()+" and scheduleId="+examSchedules.getId()+"  order by subjectId,examTypeId,subTypeId");
  												if(!ObjectFunctions.isNullOrEmpty(marks)){
 	  												col = examSchedulesCells.get(marks[2].toString()+"_"+marks[3].toString());
 	  												if(!ObjectFunctions.isNullOrEmpty(col)){
 	  													cell = row.createCell(col);
 	  													column = col;
	  													 totalMarks = Double.valueOf(marks[0].toString());
 	  													if(marks[4].toString().equalsIgnoreCase("Y")){
 	  														obtainedMarks = Double.valueOf(marks[1].toString());
 	  													if(getTempId() >0){
 	  													if((marks[2].toString().equalsIgnoreCase("FA1")) || (marks[2].toString().equalsIgnoreCase("FA2")) || (marks[2].toString().equalsIgnoreCase("FA3")) || (marks[2].toString().equalsIgnoreCase("FA4"))){
 	  														  subMarks = (obtainedMarks/totalMarks)*getTempId();
 	 	  													 if((examTypeId == 0 || examTypeId == Long.valueOf(marks[6].toString())) && (subjectId == 0 || subjectId == Long.valueOf(marks[5].toString())) && examTypesList.size() == 1){
  	 	  														 sumOfTotal = sumOfTotal + getTempId();
  	 	  													 } if((subjectId == 0 || subjectId == Long.valueOf(marks[5].toString())) && examTypesList.size()>1)
  	 	  														  sumOfTotal = sumOfTotal + getTempId();
 	  													 }
 	  													}
 	  													if(getTempId1() >0){
 	  													 if((marks[2].toString().equalsIgnoreCase("SA1")) || (marks[2].toString().equalsIgnoreCase("SA2")) || (marks[2].toString().equalsIgnoreCase("SA3")) || (marks[2].toString().equalsIgnoreCase("SA4"))){
 	  														 subMarks = (obtainedMarks/totalMarks)*getTempId1();
 	 	  													 if((examTypeId == 0 || examTypeId == Long.valueOf(marks[6].toString())) && (subjectId == 0 || subjectId == Long.valueOf(marks[5].toString())) && examTypesList.size() == 1){
 	 	  														 sumOfTotal = sumOfTotal + getTempId1();
 	 	  												 } if((subjectId == 0 || subjectId == Long.valueOf(marks[5].toString())) && examTypesList.size()>1)
 	 	  														sumOfTotal = sumOfTotal + getTempId1();
 	  													}
 	  													}else{
 	  														subMarks = obtainedMarks;
 	  													 if((examTypeId == 0 || examTypeId == Long.valueOf(marks[6].toString())) && (subjectId == 0 || subjectId == Long.valueOf(marks[5].toString())) && examTypesList.size() == 1){
 	  														 sumOfTotal = sumOfTotal + totalMarks;
 	  													 }
 	  													 if((subjectId == 0 || subjectId == Long.valueOf(marks[5].toString())) && examTypesList.size()>1)
	  														 sumOfTotal = sumOfTotal + totalMarks;
 	  													}
 	  													cell.setCellValue((int)Math.round(subMarks));	
 	  												 if(examTypeId == 0 || examTypeId == Long.valueOf(marks[6].toString())){
  														if(subjectId >0 && subjectId != Long.valueOf(marks[5].toString())){
  															col = examSchedulesCells.get(examTypeId+"_"+subjectId);
  															if(!ObjectFunctions.isNullOrEmpty(col)){
  																cell = row.createCell(col);
  																cell.setCellValue((int)Math.round(subTotalMarks));
  																studTotalMarks=studTotalMarks+subTotalMarks;
  															}
  															subTotalMarks=0;
  															col = examSchedulesCells.get("GrandTotal"+"_"+subjectId);
  															if(!ObjectFunctions.isNullOrEmpty(col)){
  																cell = row.createCell(col);
  																cell.setCellValue((int)Math.round(studTotalMarks));
  																percentageMarks = percentageMarks + ((studTotalMarks/sumOfTotal)*100);
  																	sumOfTotal = 0;
	  																if(getTempId() >0){
	  			 	  													if((marks[2].toString().equalsIgnoreCase("FA1")) || (marks[2].toString().equalsIgnoreCase("FA2")) || (marks[2].toString().equalsIgnoreCase("FA3")) || (marks[2].toString().equalsIgnoreCase("FA4"))){
	  			  	 	  														 sumOfTotal = sumOfTotal + getTempId();
	  			 	  													 }
	  			 	  													}
	  			 	  													if(getTempId1() >0){
	  			 	  													 if((marks[2].toString().equalsIgnoreCase("SA1")) || (marks[2].toString().equalsIgnoreCase("SA2")) || (marks[2].toString().equalsIgnoreCase("SA3")) || (marks[2].toString().equalsIgnoreCase("SA4"))){
	  			 	 	  														 sumOfTotal = sumOfTotal + getTempId1();
	  			 	  													}
	  			 	  													}else{
	  			 	  														sumOfTotal = sumOfTotal + totalMarks;
	  			 	  													}
  																calculatedMarks = (int) Math.round(percentageMarks);
  																studTotalMarks = 0;
  																percentageMarks = 0;
  															}
  															col = examSchedulesCells.get("Grade"+"_"+subjectId);
  															if(!ObjectFunctions.isNullOrEmpty(col)){
  																cell = row.createCell(col);
																gradeObjec = (SchoolGrades)adminManager.get(SchoolGrades.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and minMarks<="+calculatedMarks+" and maxMarks>="+calculatedMarks+" ");
  																if(!ObjectFunctions.isNullOrEmpty(gradeObjec)){
  																	cell.setCellValue(gradeObjec.getGradeName());
  																	cell.setCellStyle(style);
  																}
  																gradeObjec = null;
  															}
  															calculatedMarks = 0;
  														}
  															subTotalMarks = subTotalMarks + subMarks;
 	  													}
  														if(examTypeId > 0 && examTypeId != Long.valueOf(marks[6].toString())){
  															col = examSchedulesCells.get(examTypeId+"_"+subjectId);
  															if(!ObjectFunctions.isNullOrEmpty(col)){
  																cell = row.createCell(col);
  																cell.setCellValue((int)Math.round(subTotalMarks));
  																studTotalMarks=studTotalMarks+subTotalMarks;
  															}
  															subTotalMarks=0;
  															subTotalMarks=subTotalMarks+subMarks;
  															if(subjectId >0 && subjectId != Long.valueOf(marks[5].toString())){
	  															col = examSchedulesCells.get("GrandTotal"+"_"+subjectId);
	  															if(!ObjectFunctions.isNullOrEmpty(col)){
	  																cell = row.createCell(col);
	  																cell.setCellValue((int)Math.round(studTotalMarks));
	  																percentageMarks = percentageMarks + ((studTotalMarks/sumOfTotal)*100);
	  																	sumOfTotal = 0;
		  																if(getTempId() >0){
		  			 	  													if((marks[2].toString().equalsIgnoreCase("FA1")) || (marks[2].toString().equalsIgnoreCase("FA2")) || (marks[2].toString().equalsIgnoreCase("FA3")) || (marks[2].toString().equalsIgnoreCase("FA4"))){
		  			  	 	  														 sumOfTotal = sumOfTotal + getTempId();
		  			 	  													 }
		  			 	  													}
		  			 	  													if(getTempId1() >0){
		  			 	  													 if((marks[2].toString().equalsIgnoreCase("SA1")) || (marks[2].toString().equalsIgnoreCase("SA2")) || (marks[2].toString().equalsIgnoreCase("SA3")) || (marks[2].toString().equalsIgnoreCase("SA4"))){
		  			 	 	  														 sumOfTotal = sumOfTotal + getTempId1();
		  			 	  													}
		  			 	  													}else{
		  			 	  														sumOfTotal = sumOfTotal + totalMarks;
		  			 	  													}
	  																calculatedMarks = (int) Math.round(percentageMarks);
	  																percentageMarks = 0;
	  																studTotalMarks=0;
	  															}
	  															col = examSchedulesCells.get("Grade"+"_"+subjectId);
	  															if(!ObjectFunctions.isNullOrEmpty(col)){
	  																cell = row.createCell(col);
																	gradeObjec = (SchoolGrades)adminManager.get(SchoolGrades.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and minMarks<="+calculatedMarks+" and maxMarks>="+calculatedMarks+" ");
	  																if(!ObjectFunctions.isNullOrEmpty(gradeObjec)){
	  																	cell.setCellValue(gradeObjec.getGradeName());
	  																	cell.setCellStyle(style);
	  																}
	  																gradeObjec = null;
	  															}
	  															calculatedMarks = 0;
  															}
  														}
  															
													}
  													else{
  														col = examSchedulesCells.get(examTypeId+"_"+subjectId);
															if(!ObjectFunctions.isNullOrEmpty(col)){
																cell = row.createCell(col);
																cell.setCellValue((int)Math.round(subTotalMarks));
																studTotalMarks=studTotalMarks+subTotalMarks;
															}
															subTotalMarks=0;
															subTotalMarks=subTotalMarks+subMarks;
  														col = examSchedulesCells.get("GrandTotal"+"_"+subjectId);
															if(!ObjectFunctions.isNullOrEmpty(col)){
																cell = row.createCell(col);
																cell.setCellValue((int)Math.round(studTotalMarks));
																percentageMarks = percentageMarks + ((studTotalMarks/sumOfTotal)*100);
	  																if(getTempId() >0){
	  			 	  													if((marks[2].toString().equalsIgnoreCase("FA1")) || (marks[2].toString().equalsIgnoreCase("FA2")) || (marks[2].toString().equalsIgnoreCase("FA3")) || (marks[2].toString().equalsIgnoreCase("FA4"))){
	  			  	 	  														 sumOfTotal = sumOfTotal + getTempId();
	  			 	  													 }
	  			 	  													}
	  			 	  													if(getTempId1() >0){
	  			 	  													 if((marks[2].toString().equalsIgnoreCase("SA1")) || (marks[2].toString().equalsIgnoreCase("SA2")) || (marks[2].toString().equalsIgnoreCase("SA3")) || (marks[2].toString().equalsIgnoreCase("SA4"))){
	  			 	 	  														 sumOfTotal = sumOfTotal + getTempId1();
	  			 	  													}
	  			 	  													}else{
	  			 	  														sumOfTotal = sumOfTotal + totalMarks;
	  			 	  													}
  																calculatedMarks = (int) Math.round(percentageMarks);
  																percentageMarks = 0;
																studTotalMarks=0;
															}
															col = examSchedulesCells.get("Grade"+"_"+subjectId);
															if(!ObjectFunctions.isNullOrEmpty(col)){
																cell = row.createCell(col);
																gradeObjec = (SchoolGrades)adminManager.get(SchoolGrades.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and minMarks<="+calculatedMarks+" and maxMarks>="+calculatedMarks+" ");
																if(!ObjectFunctions.isNullOrEmpty(gradeObjec)){
																	cell.setCellValue(gradeObjec.getGradeName());
																	cell.setCellStyle(style);
																}
																gradeObjec = null;
															}
															calculatedMarks = 0;
		 	  											col = examSchedulesCells.get(marks[2].toString()+"_"+marks[3].toString());
		 	  											if(!ObjectFunctions.isNullOrEmpty(col)){
	 	  													cell = row.createCell(col);
	 	  													cell.setCellValue("AB");
	 	  													cell.setCellStyle(style);
		 	  											}
  													}	
  												}
  												
  											}else{
  												ExamSchedules maxMarks = (ExamSchedules)adminManager.get(ExamSchedules.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+examSchedules.getId()+" and examTypeId="+examSchedules.getExamTypeId());
  												if(!ObjectFunctions.isNullOrEmpty(maxMarks)){
  													if(getTempId() >0){
 	  													if((maxMarks.getSubTypeName().equalsIgnoreCase("FA1")) || (maxMarks.getSubTypeName().equalsIgnoreCase("FA2")) || (maxMarks.getSubTypeName().equalsIgnoreCase("FA3")) || (maxMarks.getSubTypeName().equalsIgnoreCase("FA4"))){
  	 	  														 sumOfTotal = sumOfTotal + getTempId();
 	  													 }
 	  													}
 	  													if(getTempId1() >0){
 	  													 if((maxMarks.getSubTypeName().equalsIgnoreCase("SA1")) || (maxMarks.getSubTypeName().equalsIgnoreCase("SA2")) || (maxMarks.getSubTypeName().equalsIgnoreCase("SA3")) || (maxMarks.getSubTypeName().equalsIgnoreCase("SA4"))){
 	 	  														 sumOfTotal = sumOfTotal + getTempId1();
 	  													}
 	  													}else{
 	  														sumOfTotal = sumOfTotal + maxMarks.getMaxMarks();
 	  													}
  												}
  												if(examTypeId == 0 || examTypeId == examSchedules.getExamTypeId()){
													if(subjectId >0 && subjectId != examSchedules.getStudySubjectId()){
														col = examSchedulesCells.get(examTypeId+"_"+subjectId);
															if(!ObjectFunctions.isNullOrEmpty(col)){
																cell = row.createCell(col);
																cell.setCellValue((int)Math.round(subTotalMarks));
																studTotalMarks=studTotalMarks+subTotalMarks;
															}
															subTotalMarks=0;															
															if(subjectId >0 && subjectId != examSchedules.getStudySubjectId()){
																col = examSchedulesCells.get("GrandTotal"+"_"+subjectId);
																if(!ObjectFunctions.isNullOrEmpty(col)){
																	cell = row.createCell(col);
																	cell.setCellValue((int)Math.round(studTotalMarks));
																	percentageMarks = percentageMarks + ((studTotalMarks/sumOfTotal)*100);
	  																calculatedMarks = (int) Math.round(percentageMarks);
	  																percentageMarks = 0;
																	studTotalMarks=0;
																}
															col = examSchedulesCells.get("Grade"+"_"+subjectId);
																if(!ObjectFunctions.isNullOrEmpty(col)){
																	cell = row.createCell(col);
																	gradeObjec = (SchoolGrades)adminManager.get(SchoolGrades.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and minMarks<="+calculatedMarks+" and maxMarks>="+calculatedMarks+" ");
	  																if(!ObjectFunctions.isNullOrEmpty(gradeObjec)){
	  																	cell.setCellValue(gradeObjec.getGradeName());
	  																	cell.setCellStyle(style);
	  																}
	  																gradeObjec = null;
																}
																calculatedMarks = 0;
															}
													}
													subTotalMarks = subTotalMarks + subMarks;
  											 }
  											else{
												if(examTypeId > 0 && examTypeId != examSchedules.getExamTypeId()){
													col = examSchedulesCells.get(examTypeId+"_"+subjectId);
													if(!ObjectFunctions.isNullOrEmpty(col)){
														cell = row.createCell(col);
														cell.setCellValue((int)Math.round(subTotalMarks));
														studTotalMarks=studTotalMarks+subTotalMarks;
													}
													subTotalMarks=0;
													subTotalMarks=subTotalMarks+subMarks;
													if(subjectId >0 && subjectId !=examSchedules.getStudySubjectId()){
														col = examSchedulesCells.get("GrandTotal"+"_"+subjectId);
														if(!ObjectFunctions.isNullOrEmpty(col)){
															cell = row.createCell(col);
															cell.setCellValue((int)Math.round(studTotalMarks));
															percentageMarks = percentageMarks + ((studTotalMarks/sumOfTotal)*100);
															calculatedMarks = (int) Math.round(percentageMarks);
															percentageMarks = 0;
															studTotalMarks=0;
														}
														col = examSchedulesCells.get("Grade"+"_"+subjectId);
															if(!ObjectFunctions.isNullOrEmpty(col)){
																cell = row.createCell(col);
																gradeObjec = (SchoolGrades)adminManager.get(SchoolGrades.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and minMarks<="+calculatedMarks+" and maxMarks>="+calculatedMarks+" ");
  																if(!ObjectFunctions.isNullOrEmpty(gradeObjec)){
  																	cell.setCellValue(gradeObjec.getGradeName());
  																	cell.setCellStyle(style);
  																}
  																gradeObjec = null;
															}
															calculatedMarks = 0;
													}
												}
												
											 }
  												maxMarks = null;
  										}
  											subMarks = 0;
											examTypeId = examSchedules.getExamTypeId();
											subjectId = examSchedules.getStudySubjectId();
  											cell = null;
  											examSchedules=null;
										}
											col = examSchedulesCells.get("Total"+"_"+subjectId);
											if(!ObjectFunctions.isNullOrEmpty(col)){
												cell = row.createCell(col);
												cell.setCellValue((int)Math.round(subTotalMarks));
	  											studTotalMarks = studTotalMarks + subTotalMarks;
											}
											col = examSchedulesCells.get("GrandTotal"+"_"+subjectId);
											if(!ObjectFunctions.isNullOrEmpty(col)){
												cell = row.createCell(col);
												cell.setCellValue((int)Math.round(studTotalMarks));
												percentageMarks = percentageMarks + ((studTotalMarks/sumOfTotal)*100);
												calculatedMarks = (int) Math.round(percentageMarks);
												percentageMarks = 0;
												sumOfTotal = 0;
											}
											col = examSchedulesCells.get("Grade"+"_"+subjectId);
											if(!ObjectFunctions.isNullOrEmpty(col)){
												cell = row.createCell(col);
												gradeObjec = (SchoolGrades)adminManager.get(SchoolGrades.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and minMarks<="+calculatedMarks+" and maxMarks>="+calculatedMarks+" ");
												if(!ObjectFunctions.isNullOrEmpty(gradeObjec)){
													cell.setCellValue(gradeObjec.getGradeName());
													cell.setCellStyle(style);
												}
												gradeObjec = null;
											}
											calculatedMarks = 0;
	   										studentDetails=null;
	   									}
	   									rowNum++;
	   									
	   								}
	   							}
							}
							else{
			   			    		sheet.createRow(10).createCell(0).setCellValue("No Exam Schedules found for this class.");
							}
							}else{
		   			    		sheet.createRow(10).createCell(0).setCellValue("No students available for this class.");
							}
							wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
			   				wb.write(getResponse().getOutputStream());
			   				sheet = null;
			}
			}
   		}

   		catch(Exception ex)
   		{
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   	}
	
	public HashMap<String,Integer> createExamScheulesSubjectsHeader(HSSFSheet sheet,Map<String, CellStyle> styles,StudyClass studyClass,List<ExamSchedules> examScheduleDetails,List<ExamSchedules> examTypesList ){
		StringBuffer query = null;
		HashMap<String,Integer> scheduleSubType = null;
		List sechuduleObjects= null;
		Row scheduleIdsRow = null;
		Row scheduleSubjectNameRow = null;
		Row row = null;
		HashMap<String,Integer> examSchedulesCells = null;
		HashMap<String,List<ExamSchedules>> subjectMap=new LinkedHashMap<String,List<ExamSchedules>>();
		if(!ObjectFunctions.isNullOrEmpty(examScheduleDetails)){
			scheduleIdsRow = sheet.createRow(3);
			scheduleIdsRow.setZeroHeight(true);
			scheduleSubjectNameRow = sheet.createRow(4);
			Row  scheduleExamTypeRow = sheet.createRow(5);
			scheduleSubjectNameRow.setHeightInPoints(20);
			row = sheet.createRow(6);
			row.setHeightInPoints(30);
			org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
			cell.setCellValue("Roll Number");
			cell.setCellStyle(styles.get("headerInfo"));
			cell = row.createCell(1);
			cell.setCellValue("Admission Number");
			cell.setCellStyle(styles.get("headerInfo"));
			cell = row.createCell(2);
			cell.setCellValue("Student Name");
			cell.setCellStyle(styles.get("headerInfo"));
			int subColnum = 4;
			int subTypeColnum = 4;
			int examNameColumn = 4;
			List<ExamSchedules> subjectSchedules = null;
			ExamSchedules examSchedule = null;
			int totalSubTypesCount =0;
			examSchedulesCells = new HashMap<String,Integer>();
			long subjectId=0;
			long examTypeId = 0;
			int k=2;
			int l=0;
			for(ExamSchedules examSchedules : examScheduleDetails){
				if(subjectId != examSchedules.getClassSectionSubject().getId()){
					if(subjectId >0){
						subColnum++;
						cell = scheduleExamTypeRow.createCell(subColnum);
						sheet.addMergedRegion(new CellRangeAddress(scheduleExamTypeRow.getRowNum(),scheduleExamTypeRow.getRowNum()+1,subColnum,subColnum));
			        	cell.setCellValue("Grand Total");
			        	cell.setCellStyle(styles.get("headerInfo"));
			        	query = new StringBuffer().append("GrandTotal").append("_").append(subjectId);
			        	scheduleIdsRow.createCell(subColnum).setCellValue(query.toString());
		        		examSchedulesCells.put(query.toString(), subColnum);
		        		query = null;
			        	subColnum++;
			        	cell = scheduleSubjectNameRow.createCell(subColnum);
			        	sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum()+2,subColnum,subColnum));
			        	cell.setCellValue("Grade");
			        	cell.setCellStyle(styles.get("headerInfo"));
			        	query = new StringBuffer().append("Grade").append("_").append(subjectId);
			        	scheduleIdsRow.createCell(subColnum).setCellValue(query.toString());
		        		examSchedulesCells.put(query.toString(), subColnum);
		        		query = null;
			        	subColnum++;
					}
					
					cell = scheduleSubjectNameRow.createCell(subColnum);
					sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum(),subColnum,subColnum+k));
		        	cell.setCellValue(examSchedules.getClassSectionSubject().getName());		        	
		        	cell.setCellStyle(styles.get("headerInfo"));
				}  
				if(examTypeId!=examSchedules.getExamTypeId()){
				if(examTypeId>0 && subjectId == examSchedules.getClassSectionSubject().getId() ){
					cell = row.createCell(subTypeColnum);
					cell.setCellValue("Total");
		        	cell.setCellStyle(styles.get("headerInfo"));
		        	query = new StringBuffer().append(examTypeId).append("_").append(subjectId);
		        	scheduleIdsRow.createCell(subTypeColnum).setCellValue(query.toString());
	        		examSchedulesCells.put(query.toString(), subTypeColnum);
	        		query = null;
		        	subTypeColnum++;
		        	subColnum++;
					cell = scheduleExamTypeRow.createCell(subColnum);
				 }
				 else{
					 if(examTypeId>0 && subjectId != examSchedules.getClassSectionSubject().getId() ){
						 cell = row.createCell(subTypeColnum);
							cell.setCellValue("Total");
				        	cell.setCellStyle(styles.get("headerInfo"));
				        	query = new StringBuffer().append(examTypeId).append("_").append(subjectId);
				        	scheduleIdsRow.createCell(subTypeColnum).setCellValue(query.toString());
			        		examSchedulesCells.put(query.toString(), subTypeColnum);
			        		query = null;
				        	subTypeColnum = subTypeColnum+2;
				        	subTypeColnum++;
					 }
						cell = scheduleExamTypeRow.createCell(subColnum);
					 }
				  cell.setCellValue(examSchedules.getExamTypeName());
				  cell.setCellStyle(styles.get("headerInfo"));
				}
				if((examTypeId == examSchedules.getExamTypeId()) && subjectId != examSchedules.getClassSectionSubject().getId()){
					    cell = row.createCell(subTypeColnum);
						cell.setCellValue("Total");
			        	cell.setCellStyle(styles.get("headerInfo"));
			        	query = new StringBuffer().append(examTypeId).append("_").append(subjectId);
			        	scheduleIdsRow.createCell(subTypeColnum).setCellValue(query.toString());
		        		examSchedulesCells.put(query.toString(), subTypeColnum);
		        		query = null;
			        	subTypeColnum = subTypeColnum+2;
					    cell = scheduleExamTypeRow.createCell(subColnum);
					    cell.setCellValue(examSchedules.getExamTypeName());
					    cell.setCellStyle(styles.get("headerInfo"));
					    subTypeColnum++;
				} 
    			cell = row.createCell(subTypeColnum);
	        	cell.setCellValue(examSchedules.getSubTypeName());
	        	cell.setCellStyle(styles.get("headerInfo"));
	        	query = new StringBuffer().append(examSchedules.getSubTypeName()).append("_").append(examSchedules.getId());
	        	scheduleIdsRow.createCell(subTypeColnum).setCellValue(query.toString());
        		examSchedulesCells.put(query.toString(), subTypeColnum);
        		query = null;
	        	subTypeColnum++;
	        	subColnum++;
				subjectId = examSchedules.getClassSectionSubject().getId();
				examTypeId = examSchedules.getExamTypeId();
			}
			 cell = row.createCell(subTypeColnum);
			 cell.setCellValue("Total");
	         cell.setCellStyle(styles.get("headerInfo"));
	         query = new StringBuffer().append("Total").append("_").append(subjectId);
	         scheduleIdsRow.createCell(subTypeColnum).setCellValue(query.toString());
     		 examSchedulesCells.put(query.toString(), subTypeColnum);
     		 query = null;
	         cell = scheduleExamTypeRow.createCell(subColnum+1);
			 sheet.addMergedRegion(new CellRangeAddress(scheduleExamTypeRow.getRowNum(),scheduleExamTypeRow.getRowNum()+1,subColnum+1,subColnum+1));
			 cell.setCellValue("Grand Total");
        	 cell.setCellStyle(styles.get("headerInfo"));
        	 query = new StringBuffer().append("GrandTotal").append("_").append(subjectId);
 	         scheduleIdsRow.createCell(subTypeColnum+1).setCellValue(query.toString());
      		 examSchedulesCells.put(query.toString(), subTypeColnum+1);
      		 query = null;
      		 cell = scheduleSubjectNameRow.createCell(subColnum+2);
     		 sheet.addMergedRegion(new CellRangeAddress(scheduleSubjectNameRow.getRowNum(),scheduleSubjectNameRow.getRowNum()+2,subColnum+2,subColnum+2));
			 cell.setCellValue("Grade");
        	 cell.setCellStyle(styles.get("headerInfo"));
        	 query = new StringBuffer().append("Grade").append("_").append(subjectId);
	         scheduleIdsRow.createCell(subColnum+2).setCellValue(query.toString());
     		 examSchedulesCells.put(query.toString(), subColnum+2);
      		 query = null;
      		 
     		
		}
		scheduleIdsRow = null;
		row = null;
		scheduleSubType = null;
		sechuduleObjects= null;
		subjectMap=null;
		return examSchedulesCells;
  	}
	
	
	public void createExamSchedulesHeader(HSSFSheet sheet,Map<String, CellStyle> styles){
  		Customer customer = getCustomerByCustId();
  		Row row = null;
		org.apache.poi.ss.usermodel.Cell cell = null;
		String custAddress = null;
		StringBuffer query = null;
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
					row=sheet.createRow(0);
					custAddress = customer.getOrganizationFullAddress();
					if (StringFunctions.isNotNullOrEmpty(customer.getOrganization()) || StringFunctions.isNotNullOrEmpty(custAddress)) {
						row.setHeightInPoints(50);
						sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:Z$1"));
						if(StringFunctions.isNotNullOrEmpty(customer.getOrganization()) && StringFunctions.isNotNullOrEmpty(custAddress))
							query = new StringBuffer(customer.getOrganization()).append("\n").append(custAddress);
						else if(StringFunctions.isNotNullOrEmpty(customer.getOrganization()))
							query = new StringBuffer(customer.getOrganization());
						else
							query = new StringBuffer(custAddress);
						cell = row.createCell(0);
						cell.setCellValue(query.toString());
						cell.setCellStyle(styles.get("title"));
					}
					customer = null;
				 if(sheet.getSheetName().equalsIgnoreCase("ScholasticConfig")){
					row=sheet.createRow(1);
					row.setHeightInPoints(50);
					sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:Z$2"));
					cell = row.createCell(0);
					cell.setCellValue("Scholastic Areas");
					cell.setCellStyle(styles.get("title"));
				}
				
			}
		styles = null;
		row = null;
		cell = null;
		custAddress = null;
		query = null;
  	}
	public void createHeaderForActivities(HSSFSheet sheet,Map<String, CellStyle> styles,List<ExamSchedules> examTypesList,List<Object[]> classStudentsList){
		StringBuffer query = null;
        List<Object[]> studentActivityMarks = null;
		List<Object[]> activityTypes=null;
		HashMap<String,Integer> acativitiesCells = null;
		int startMergeCell=3;
		int endMergeCell = 0;
		int examTypesMergStrCell = 3;
		int examTypesEndMergCell = 0;
		Row activitiesIdsRow = sheet.createRow(3);
		activitiesIdsRow.setZeroHeight(true);
		Row row = sheet.createRow(7);
		int rowNum = 8;
		Row ActivitiesRow = sheet.createRow(4);
		Row subActivitiesRow = sheet.createRow(6);
		Row ExamTypesRow = sheet.createRow(5);
		row.setHeightInPoints(30);
		org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
		query = new StringBuffer("select activity.id,activity.activityName From studentActivities activity JOIN studentActivityTypes type on(activity.id=type.studentActivityId) JOIN activityTypeClasses activityClass on (activityClass.classId=")
		.append(getClassId()).append(" and activityClass.activityTypeId=type.id) and activity.custId=").append(getUserCustId()).append(" and activity.academicYearId=").append(getUserAcademicYearId())
	    .append(" group by activity.id");
		List<Object[]> studentActivities = adminManager.getAll(query.toString());
		AcademicYear academicYear = (AcademicYear)adminManager.get(AcademicYear.class,getUserAcademicYearId());
		query = null;
		if(!ObjectFunctions.isNullOrEmpty(studentActivities)){
			if(studentActivities.size() <=20){
				cell.setCellValue("Roll Number");
				cell.setCellStyle(styles.get("headerInfo"));
				cell = row.createCell(1);
				cell.setCellValue("Admission Number");
				cell.setCellStyle(styles.get("headerInfo"));
				cell = row.createCell(2);
				cell.setCellValue("Student Name");
				cell.setCellStyle(styles.get("headerInfo"));
				sheet.createFreezePane(3, 8);
				acativitiesCells = new HashMap<String,Integer>();
				for(Object[] activity : studentActivities){
					if(!ObjectFunctions.isNullOrEmpty(activity[0]) && !ObjectFunctions.isNullOrEmpty(activity[1])){
						query = new StringBuffer("select activityType.id,activityType.activityTypeName FROM studentActivityTypes activityType JOIN activityTypeClasses activityTypeClasses on (activityType.id=activityTypeClasses.activityTypeId and activityTypeClasses.classId=")
						.append(getClassId()).append(") and activityType.academicYearId=").append(getUserAcademicYearId()).append(" and activityType.custId=").append(getUserCustId())
						.append(" and activityType.studentActivityId=").append(activity[0].toString());
						 activityTypes = adminManager.getAll(query.toString());
						 query = null;
						 if(!ObjectFunctions.isNullOrEmpty(activity)){
							 if(ObjectFunctions.isNotNullOrEmpty(activityTypes)){
								cell = ActivitiesRow.createCell(startMergeCell);
								 endMergeCell = startMergeCell+((activityTypes.size()*2)*examTypesList.size())-1;
		    		     		 sheet.addMergedRegion(new CellRangeAddress(ActivitiesRow.getRowNum(),ActivitiesRow.getRowNum(),startMergeCell,endMergeCell));
								 cell.setCellValue(activity[1].toString());
					        	 cell.setCellStyle(styles.get("headerInfo"));
		    		     			for(ExamSchedules examType : examTypesList){
		    		     				cell = ExamTypesRow.createCell(examTypesMergStrCell);
		    		     				examTypesEndMergCell = examTypesMergStrCell + activityTypes.size()*2-1;
		    	    		     		sheet.addMergedRegion(new CellRangeAddress(ExamTypesRow.getRowNum(),ExamTypesRow.getRowNum(),examTypesMergStrCell,examTypesEndMergCell));
		    		     				cell.setCellValue(examType.getExamTypeName());
		    		     				cell.setCellStyle(styles.get("headerInfo"));
		    		     					
		    	    		     		for(Object[] activityType: activityTypes){
			    		     				cell = subActivitiesRow.createCell(startMergeCell);
			    	    		     		sheet.addMergedRegion(new CellRangeAddress(subActivitiesRow.getRowNum(),subActivitiesRow.getRowNum(),startMergeCell,startMergeCell+1));
			    		     				cell.setCellValue(activityType[1].toString());
			    		     				cell.setCellStyle(styles.get("headerInfo"));
			    	    		     		cell = row.createCell(startMergeCell);
			    		     				cell.setCellValue("Grade");
			    		     				cell.setCellStyle(styles.get("headerInfo"));
			    		     				query = new StringBuffer(examType.getExamTypeId()+"_"+activityType[0].toString()+"_"+"Grade");
			    		     				acativitiesCells.put(query.toString(), cell.getColumnIndex());
			    		     				activitiesIdsRow.createCell(startMergeCell).setCellValue(query.toString());
			    		     				query = null;
			    		     				cell = row.createCell(startMergeCell);
			    		     				cell.setCellValue("Description");
			    		     				cell.setCellStyle(styles.get("headerInfo"));
			    		     				query = new StringBuffer(examType.getExamTypeId()+"_"+activityType[0].toString()+"_"+"Description");
			    		     				acativitiesCells.put(query.toString(), cell.getColumnIndex());
			    		     				activitiesIdsRow.createCell(startMergeCell+1).setCellValue(query.toString());
			    		     				query = null;
			        						startMergeCell += 2;
			    		     			}
		    	    		     		examTypesMergStrCell = startMergeCell;
		    		     			}
		    		     			
		    		     		 }
		        				startMergeCell = examTypesMergStrCell;
						}
					}
				}
				Integer column =null;
				for(Object[] studentDetails : classStudentsList){
						if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
							row = sheet.createRow(rowNum);
							cell=row.createCell(0);
							cell.setCellValue(studentDetails[3].toString());
							cell.setCellStyle(styles.get("headerInfo"));
							cell=row.createCell(1);
							cell.setCellValue(studentDetails[2].toString());
							cell.setCellStyle(styles.get("headerInfo"));
							cell=row.createCell(2);
							cell.setCellValue(studentDetails[1].toString());
							cell.setCellStyle(styles.get("headerInfo"));
			     			for(ExamSchedules examType : examTypesList){
			     				studentActivityMarks = adminManager.getAll("select examTypeId,studentActivityTypeId,grade,description,studentsAssessmentId from  studentAcademicPerformance where studId="+studentDetails[0].toString()+" and examTypeId="+examType.getExamTypeId());
	   							if(!ObjectFunctions.isNullOrEmpty(studentActivityMarks)){
				     				for(Object[] academicPerformance :studentActivityMarks){
				     					column = acativitiesCells.get(academicPerformance[0].toString()+"_"+academicPerformance[1].toString()+"_"+"Grade");
											if(!ObjectFunctions.isNullOrEmpty(column)){
												cell = row.createCell(column);
												cell.setCellValue(academicPerformance[2].toString());
											}
					     					column = acativitiesCells.get(academicPerformance[0].toString()+"_"+academicPerformance[1].toString()+"_"+"Description");
					     					if(!ObjectFunctions.isNullOrEmpty(column)){
												cell = row.createCell(column);
												cell.setCellValue(academicPerformance[3].toString());
											}
	
				     				}
	   							}
			     			}
						}
						rowNum++;
				}
			}
		}else{
	    		sheet.createRow(10).createCell(0).setCellValue("Grades Not uploaded.");
		}
	}
	
	@Actions({
		@Action(value = "ajaxViewMessageDetails", results = { @Result(location = "messages/ajaxViewMessageDetailsList.jsp", name = "success") })
	})
	public String ajaxViewMessageDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMessageDetails' method");
		}
		try{
			List<MessageDetailsTracking> messageDetailsTrackingList = null;
			String messageId = null;
			messageId = getParamValue("tempId");			
			if(!ObjectFunctions.isNullOrEmpty(messageId)){
				messageDetailsTrackingList = adminManager.getAll(MessageDetailsTracking.class, "custId="+getUserCustId()+" and messageId="+messageId);
				if (!ObjectFunctions.isNullOrEmpty(messageDetailsTrackingList)) {
					for(MessageDetailsTracking messageDetailsTracking : messageDetailsTrackingList)
					{
						if(!ObjectFunctions.isNullOrEmpty(messageDetailsTracking.getAccount()))
						{
							if("Y".equalsIgnoreCase(messageDetailsTracking.getAccount().getIsSchoolStudent()))
							{
								Student student = (Student)adminManager.get(Student.class, "accountId="+messageDetailsTracking.getAccount().getId()+" and academicYearId="+messageDetailsTracking.getAcademicYearId() +" and description is null");
								if(!ObjectFunctions.isNullOrEmpty(student))
								{
									messageDetailsTracking.setClassName(student.getClassSection().getClassAndSection());
								}
								student = null;
							}
						}
						getMessagesList().add(messageDetailsTracking);
					}
					//setMessagesList(messageDetailsTrackingList);
				}			
			}
		}
		catch(Exception ex)
   		{
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
		return SUCCESS;
	}
	
	  public String showSchoolUrlInExcelSheetFooter(int col,ExcelView excelView,int size){
		  if (log.isDebugEnabled()) {
				log.debug("Entering 'showSchoolUrlInExcelSheetFooter' method");
			}
		 try {
			  	int col1 =col+1;
				excelView.getWs().mergeCells(0, col1,size,col1);
				excelView.setRowSize(100);
				excelView.getWs().addCell(new Label(0,col1,"Generated by : www.EazySchool.com",excelView.getWrapCellFormatCenter()));
				WritableHyperlink h1 = new WritableHyperlink(0,col1,size,col1,new URL("http://www.EazySchool.com"),"Generated by:www.EazySchool.com");
				excelView.getWs().addHyperlink(h1);
				//WritableHyperlink h1 = new WritableHyperlink(col1,col1,size,col1,new URL("https://www.eazyschool.in"),"Generated by:www.eazyschool.in");
			 }
	 	 catch(Exception ex){
	 		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	 	 }
		     return null;
		} 
	  public String showSchoolUrlInPOIExcelSheetFooter(org.apache.poi.ss.usermodel.Workbook workbook,HSSFWorkbook wb,HSSFSheet sheet,int col,int size){
		  if (log.isDebugEnabled()) {
				log.debug("Entering 'showSchoolUrlInPOIExcelSheetFooter' method");
			}
		 try {
			 	Font hlink_font = null;
			 	CellStyle hlink_style = null;
			     if(!ObjectFunctions.isNullOrEmpty(wb)){
			    	  hlink_style = wb.createCellStyle();
				      hlink_font = wb.createFont();
			     }else{
			    	 hlink_style = workbook.createCellStyle();
				     hlink_font = workbook.createFont();
			     }
			     org.apache.poi.ss.usermodel.Cell cell = null;	
			     hlink_font.setUnderline(Font.U_SINGLE);
			     hlink_font.setColor(IndexedColors.BLUE.getIndex());
			     hlink_style.setAlignment(hlink_style.ALIGN_RIGHT);
			     hlink_style.setFont(hlink_font);
			 	 HSSFHyperlink url_link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
			 	 url_link.setAddress("http://www.eazyschool.com");
	             cell = sheet.createRow(col).createCell(0);
	             sheet.addMergedRegion(new CellRangeAddress(col,col,0,size));
	             cell.setCellValue("Generated by : www.EazySchool.com");         
	             cell.setHyperlink(url_link);
	             cell.setCellStyle(hlink_style);
			 }
	 	 catch(Exception ex){
	 		 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	 		JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	 	 }
		     return null;
		} 
	  @Actions( { @Action(value = "ajaxDoSendSmsForIndividualStaff", results = { @Result(location = "messages/IndividualStaffsList.jsp", name = "success") }) })
		public String ajaxDoSendSmsForIndividualStaff() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoSendSmsForIndividualStaff' method");
			}
			try {
				log.debug(getEventId());
				String staffType = getParamValue("stafTYpe");
				log.debug(staffType);
				List<Object[]> objectList = null; 
					if("NT".equalsIgnoreCase(staffType)){
						 objectList = staffManager.getAll("select firstname,lastName,mobileNumber,accountId,staffEmail,roleDescription  from vw_allUsers where custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_AYAH' or roleName='ROLE_CLERK' or roleName='ROLE_STOREKEEPER' or roleName='ROLE_DRIVER' or roleName='ROLE_HELPER' or roleName='ROLE_HOSTEL' or roleName='ROLE_HOSTELFINANCE' or roleName='ROLE_MANAGEMENTTRAINEE' or roleName='ROLE_SWEEPER' or roleName='ROLE_PEON' or roleName='ROLE_TYPIST' or roleName='ROLE_WATCHMAN' or roleName='ROLE_ADMIN' or roleName='ROLE_ADMINOFFICER' or roleName='ROLE_CONDUCTOR' or roleName='ROLE_COMPUTEROPERATOR' or  roleName='ROLE_LIBRARIAN' or roleName='ROLE_LABASST' or roleName='ROLE_TRANSPORT'  or roleName='ROLE_TRANSPORTFINANCE' or roleName='ROLE_FINANCE' or roleName='ROLE_OTHERS' or roleName='ROLE_DIRECTOR' or roleName='ROLE_STOREKEEPER' or roleName='ROLE_RECEPTIONIST' or roleName='ROLE_STAFF_NURSE') and (firstName!='' AND firstName is not null)");
					}else if("T".equalsIgnoreCase(staffType)){
						 objectList = staffManager.getAll("select firstname,lastName,mobileNumber,accountId,staffEmail,roleDescription  from vw_allUsers where custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_TEACHER' or roleName='ROLE_ADMIN_COORDINATOR' or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL' or roleName='ROLE_VICEPRINCIPAL' or roleName='ROLE_ASST_TEACHER') and (firstName!='' AND firstName is not null)");
					}else if("CT".equalsIgnoreCase(staffType)){
						 objectList = staffManager.getAll("select firstname,lastName,mobileNumber,accountId,staffEmail from vw_classSectionTeacher where custId="+getUserCustId()+" and academicyearId="+getUserAcademicYearId()+" and classTeacher='Y' and status='Y'");
					}
					else if("VP".equalsIgnoreCase(staffType)){
						 objectList = staffManager.getAll("select firstname,lastName,mobileNumber,accountId,staffEmail,roleDescription  from vw_allUsers where custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_VICEPRINCIPAL' or roleName='ROLE_PRINCIPAL') and (firstName!='' AND firstName is not null)");
					}
					else if("MGT".equalsIgnoreCase(staffType)){
						 objectList = staffManager.getAll("select firstname,lastName,mobileNumber,accountId,staffEmail,roleDescription  from vw_allUsers where custId="+getUserCustId()+" and accountExpired='N' and (roleName='ROLE_CHAIR_MAN' or roleName='ROLE_EXECUTIVE_VICE_CHAIRMAN' or roleName='ROLE_VICE_CHAIRMAN' or roleName='ROLE_SECRETARY' or roleName='ROLE_TREASURER' or roleName='ROLE_JOINT_SECRETARIES' or roleName='ROLE_EXECUTIVE_MEMBER' or roleName='ROLE_MEMBER') and (firstName!='' AND firstName is not null)");
					}
			    	if(!ObjectFunctions.isNullOrEmpty(objectList)){
			    		setObjectList(objectList);
			    	}
			    	setAvailableSMSCount(adminManager.getAvailableSmsCount(getUserCustId(), getUserAcademicYearId()));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	  public String checkStaffShiftTime(String shiftTime,String bioInTime) throws URTUniversalException{
			if(log.isDebugEnabled()){
				log.debug("Entering 'checkStaffShiftTime' method");
			}
			String staffLateTime=null;
			try {
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				Date date1 = format.parse(shiftTime);
				Date date2 = format.parse(bioInTime);
				long difference = date2.getTime() - date1.getTime();
			    long diffSeconds = difference / 1000 % 60;
			    long diffMinutes = difference / (60 * 1000) % 60;
			    long diffHours = difference / (60 * 60 * 1000);
			    staffLateTime= diffHours+":"+diffMinutes+":"+diffSeconds;
			}
			catch(Exception err)
			{
				err.printStackTrace();
			}
			return staffLateTime;
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
	  
		public void getSmsCount(){
			if (log.isDebugEnabled()) {
				log.debug("Entering 'getSmsCount' method");
			}
			try {
				setSmsCnt(staffManager.getTotalSmsCount(getUserCustId(),getUserAcademicYearId()));
				setSmsAlloted(0);
				AcademicYear academicYear = getCurrentAcademicYear();
				if(!ObjectFunctions.isNullOrEmpty(academicYear))
					setSmsAlloted((int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms());
				academicYear = null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		public String getContactFromEmail(Customer customer){
			 String contactEmail = null;
			 if(StringFunctions.isNullOrEmpty(customer.getContactEmail()) & StringFunctions.isNullOrEmpty(customer.getContactPassword())){
					return contactEmail = "messages@eazyschool.com";
				}else {
					return  contactEmail = customer.getContactEmail();
				}
		 }
		@Actions( { @Action(value = "ajaxDoPopUpSmsPreview", results = { @Result(location = "messages/ajaxPopUpSmsPreview.jsp", name = "success") }) })
	    public String ajaxDoPopUpSmsPreview() throws URTUniversalException {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxDoPopUpSmsPreview' method");
			}
			try {
				if(StringFunctions.isNotNullOrEmpty(getTempString()))
				 setTempString(getTempString());
			} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
	    }
		public void prepareNotifications(String todayDate,AcademicYear academicYear)
		{
			if(!ObjectFunctions.isNullOrEmpty(academicYear))
			{
				if("D".equalsIgnoreCase(academicYear.getManageAttendanceBy())){
					setObjectList(adminManager.getAll("select studentname,accountId,rollNumber,className from vw_StudentDailyAttendance where custId="+getUserCustId()+" and academicYearId="+academicYear.getId()+" and attendanceDate = '"+todayDate+"' "));
				}
			}
			setTempList(adminManager.getAll("select  accountId,staffName,roleName from vw_StaffDailyAttendance where custId="+getUserCustId()+" and attendanceDate ='"+todayDate+"' and present='N'"));
		}
		 
		public void generateBarcodeForStudent1(long userId){
			try{
				User user = (User)adminManager.get(User.class, userId);
				AcademicYear academicYear = getCurrentAcademicYear();
				if(!ObjectFunctions.isNullOrEmpty(user)){
					BitMatrix bitMatrix;
					Writer writer = new QRCodeWriter();
					String barcodeNumber = String.format("S"+"%07d", user.getId());
					bitMatrix = new Code128Writer().encode(barcodeNumber, BarcodeFormat.CODE_128, 150, 80, null);
					
			    	File tempFile = File.createTempFile(barcodeNumber, ".png");
		            tempFile.deleteOnExit();
		            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(tempFile));
			    	
					user.setBarcode(adminManager.getUploadImageFilePath(tempFile, academicYear.getAcademicYear(),barcodeNumber+".png"));
					studentManager.saveOrUpdateObject(user);
					user=null;
							
				}
			}catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		
		
		
		
		
	@Actions({ @Action(value = "ajaxGetBaseStudentsAttendance", results = { @Result(location = "../common/ajaxStudentAttendance.jsp", name = "success") }) })
	public String ajaxGetBaseStudentsAttendance() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetBaseStudentsAttendance' method");
		}
		try {
			Customer customer =	getCustomerByCustId();
			
			if(!"P".equalsIgnoreCase(customer.getStandardType())){
					setObjectList(null);
					long academicYearId = 0;
					long accountId = 0;
					long studentId = 0;
					Object[] viewStudentPersonAccountDetails = null;
					if (getTempId1() > 0) {
						viewStudentPersonAccountDetails = studentManager.get("select accountId,academicYearId,studId from vw_studentClassDetails where custId="+ getUserCustId()+ " and studId="+ getTempId1());
						if (!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails)) {
							academicYearId = Long.valueOf(viewStudentPersonAccountDetails[1].toString());
							accountId = Long.valueOf(viewStudentPersonAccountDetails[0].toString());
							studentId = Long.valueOf(viewStudentPersonAccountDetails[2].toString());
						}
					} else if (StringFunctions.isNotNullOrEmpty(getParamValue("accountId"))) {
						accountId = Long.valueOf(getParamValue("accountId"));
						academicYearId = getUserAcademicYearId();
					} else if (getTempId() > 0) {
						accountId = Long.valueOf(getTempId());
						academicYearId = getUserAcademicYearId();
					} else {
						accountId = getUser().getId();
						academicYearId = getUserAcademicYearId();
					}
					if (academicYearId > 0 && accountId > 0) {
						/*@Ganesh: Here we should check student available current academic year or not. Because some students joing to future academic year and with out starting future academic year we no need to call below code. */
						Student studentObj = (Student) adminManager.get(Student.class,"custId=" + getUserCustId() + " and academicYearId="+ academicYearId + " and accountId="+ accountId + " and description is null");
						if (!ObjectFunctions.isNullOrEmpty(studentObj)) {
							AcademicYear year = (AcademicYear) studentManager.get(AcademicYear.class, academicYearId);
							if (!ObjectFunctions.isNullOrEmpty(year)) {
								Date openDate = year.getStartDate();
								Date closeDate = year.getEndDate();
								Date todayDate = new Date();
								if (!ObjectFunctions.isNullOrEmpty(openDate) && !ObjectFunctions.isNullOrEmpty(closeDate)) {
									int monthId;
									int yearId;
									int currentMonth = DateFunctions.getMonthOfDate(todayDate);
									int currentYear = DateFunctions.getDayOfYear(new Date());
									Calendar cal = Calendar.getInstance();
									cal.setTime(openDate);
									double absentiesCount = 0;
									double presentiesCount = 0;
									double totalWorkingDays = 0;
									if ("M".equalsIgnoreCase(year.getManageAttendanceBy())) {
										Object[] student = studentManager.get("select id,custId from student where custId="+ getUserCustId()+ " and academicYearId="+ year.getId()+ " and accountId=" + accountId);
										if (!ObjectFunctions.isNullOrEmpty(student) && !ObjectFunctions.isNullOrEmpty(student[0])) {
											studentId = Long.valueOf(student[0].toString());
										}
										student = null;
										for (Calendar openDate1 = cal; DateFunctions.compare2Dates(closeDate,openDate1.getTime());) {
											monthId = DateFunctions.getMonthOfDate(openDate1.getTime());
											yearId = DateFunctions.getDayOfYear(openDate1.getTime());
											StudentMonthlyAttendance studentMonthlyAttendance = (StudentMonthlyAttendance) studentManager.get(StudentMonthlyAttendance.class,"studentId='" + studentId+ "' and month='"+ monthId + "'");
											ExamSchedules examSchedules = new ExamSchedules();
											if (!ObjectFunctions.isNullOrEmpty(studentMonthlyAttendance)) {
												totalWorkingDays = Double.parseDouble(studentMonthlyAttendance.getTotalWorkingDays());
												presentiesCount = Double.parseDouble(studentMonthlyAttendance.getNoOfPresentDays());
												absentiesCount = totalWorkingDays - presentiesCount;
												examSchedules.setMonthName(DateFunctions.getMonthForInt(monthId));
												examSchedules.setTotalWorkingDays((int) totalWorkingDays);
												
												String presentCount = String.valueOf(presentiesCount).substring(String.valueOf(presentiesCount).indexOf(".")).substring(1);
												if(Long.valueOf(presentCount.toString()) == 0){
													examSchedules.setTotalPresentDays((int) presentiesCount);
												}else
													examSchedules.setAttTotalPresentDays(presentiesCount);
												
												String number = String.valueOf(absentiesCount).substring(String.valueOf(absentiesCount).indexOf(".")).substring(1);
												if(Long.valueOf(number.toString()) == 0){
													examSchedules.setTotalAbsentDays((int) absentiesCount);
												}else
													examSchedules.setAttTotalAbsentDays(absentiesCount);
											
												//examSchedules.setTotalPresentDays((int) presentiesCount);
												//examSchedules.setTotalAbsentDays((int) absentiesCount);
												double percentage = roundTwoDecimals((presentiesCount / totalWorkingDays) * 100);
												examSchedules.setAttendancePercentage(percentage);
												getObjectList().add(examSchedules);
											} else {
												if (monthId <= currentMonth && yearId == currentYear || monthId > currentMonth && yearId != currentYear) {
													totalWorkingDays = 0;
													presentiesCount = 0;
													absentiesCount = 0;
													examSchedules.setMonthName(DateFunctions.getMonthForInt(monthId));
													examSchedules.setTotalWorkingDays((int) totalWorkingDays);
													examSchedules.setTotalPresentDays((int) presentiesCount);
													examSchedules.setTotalAbsentDays((int) absentiesCount);
													double percentage = 0;
													examSchedules.setAttendancePercentage(percentage);
													getObjectList().add(examSchedules);
												}
											}
											openDate1.add(Calendar.MONTH, 1);
											openDate1.set(Calendar.DATE, 1);
										}
									} else {
										Map<String, Integer> monthWiseWorkingDays = null;
										if (DateFunctions.compare2Dates(todayDate,openDate) && DateFunctions.compare2Dates(closeDate, todayDate)) {
											monthWiseWorkingDays = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYearId, openDate,todayDate, true, "",getClassId()); // here getClassId() used to academicyear have class wise holiday(CH).
										} else if (todayDate.after(closeDate)) {
											monthWiseWorkingDays = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYearId, openDate,closeDate, true, "",getClassId());
										}
										
										if (!ObjectFunctions.isNullOrEmpty(monthWiseWorkingDays)) {
											double presentCountAtt = 0.0;
											double absentAttCount = 0.0;
											int attendanceSubmittedDaysCount =0;
											for (Map.Entry<String, Integer> enty : monthWiseWorkingDays.entrySet()) {
												attendanceSubmittedDaysCount = 0;
												totalWorkingDays = Integer.valueOf(enty.getValue());
												// Get the count of attendance submitted days.
												attendanceSubmittedDaysCount = adminManager.getCount("staffDailyAttendanceSubmitTrack"," month(attendanceDate) = "+getMonthNumberFromMonthName(enty.getKey())+ " and academicYearId="+getUserAcademicYearId() +" and classSectionId="+studentObj.getClassSectionId());
											
												presentCountAtt = adminManager.getCount("vw_StudentDailyAttendance","custId="+ getUserCustId()+ " and academicYearId="+ academicYearId+ " and monthName='"+ enty.getKey()+ "' and present= 'N' and accountId="+ accountId+ " and attendanceDate <='"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)+ "'");
												absentAttCount = adminManager.getCount("vw_StudentDailyAttendance","custId="+ getUserCustId()+ " and academicYearId="+ academicYearId+ " and monthName='"+ enty.getKey()+ "' and afternoonSession= 'N' and accountId="+ accountId+ " and attendanceDate <='"+ DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,todayDate)+ "'");
												absentiesCount = (presentCountAtt+absentAttCount)/2;
												//presentiesCount = totalWorkingDays - absentiesCount;
												presentiesCount = attendanceSubmittedDaysCount - absentiesCount;
												ExamSchedules examSchedules = new ExamSchedules();
												examSchedules.setMonthName(enty.getKey());
												examSchedules.setTotalWorkingDays((int) totalWorkingDays);
												examSchedules.setTotalAttendancePercentage(attendanceSubmittedDaysCount); // holds the attendance sbumitted days count
												String presentCount = String.valueOf(presentiesCount).substring(String.valueOf(presentiesCount).indexOf(".")).substring(1);
													if(Long.valueOf(presentCount.toString()) == 0){
														examSchedules.setTotalPresentDays((int) presentiesCount);
													}else
														examSchedules.setAttTotalPresentDays(presentiesCount);
												String number = String.valueOf(absentiesCount).substring(String.valueOf(absentiesCount).indexOf(".")).substring(1);
												if(Long.valueOf(number.toString()) == 0){
													examSchedules.setTotalAbsentDays((int) absentiesCount);
												}else
													examSchedules.setAttTotalAbsentDays(absentiesCount);
												if (presentiesCount > 0 && totalWorkingDays > 0) {
													double percentage = roundTwoDecimals((presentiesCount / totalWorkingDays) * 100);
													examSchedules.setAttendancePercentage(percentage);
													getObjectList().add(examSchedules);
												}
												
											}
										}
										year = null;
									}
								}
							}
						}
					}
			}else{
				//If Organization type was preschool

				
				long studentId = 0;
				long accountId = 0;
				long academicYearId = 0;
				long studyClassId = 0;
				 if (getTempId() > 0) {
					accountId = Long.valueOf(getTempId());
					academicYearId = getUserAcademicYearId();
				}
				
				if (academicYearId > 0 && accountId > 0) {
					Student studentObj = (Student) adminManager.get(Student.class,"custId=" + getUserCustId() + " and academicYearId="+ academicYearId + " and accountId="+ accountId + " and description is null");
					if (!ObjectFunctions.isNullOrEmpty(studentObj)) {
						studentId = studentObj.getId();
						studyClassId = studentObj.getClassSection().getId();
						AcademicYear year = (AcademicYear) studentManager.get(AcademicYear.class, academicYearId);
						if (!ObjectFunctions.isNullOrEmpty(year)) {
							Date openDate = year.getStartDate();
							Date closeDate = year.getEndDate();
							Date todayDate = new Date();
							if (!ObjectFunctions.isNullOrEmpty(openDate) && !ObjectFunctions.isNullOrEmpty(closeDate)) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(openDate);
								double absentiesCount = 0;
								double presentiesCount = 0;
								double totalWorkingDays = 0;
								
									Map<String, Integer> monthWiseWorkingDays = null;
									if (DateFunctions.compare2Dates(todayDate,openDate) && DateFunctions.compare2Dates(closeDate, todayDate)) {
										monthWiseWorkingDays = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYearId, openDate,todayDate, true, "",getClassId()); // here getClassId() used to academicyear have class wise holiday(CH).
									} else if (todayDate.after(closeDate)) {
										monthWiseWorkingDays = studentManager.getMonthwiseSchoolWorkingDays(getUserCustId(),academicYearId, openDate,closeDate, true, "",getClassId());
									}
									if (!ObjectFunctions.isNullOrEmpty(monthWiseWorkingDays)) {
										for (Map.Entry<String, Integer> enty : monthWiseWorkingDays.entrySet()) {
											totalWorkingDays = Integer.valueOf(enty.getValue());
											presentiesCount = adminManager.getCount("studentDailyAttendanceTimeTrack","custId="+ getUserCustId()+ " and academicYearId="+ academicYearId
													+" and studentId="+ studentId+" and studyClassId="+ studyClassId+ " and monthname(attendanceDate) ='"+ enty.getKey()+ "'");
									    	ExamSchedules examSchedules = new ExamSchedules();
											examSchedules.setMonthName(enty.getKey());
											examSchedules.setTotalWorkingDays((int) totalWorkingDays);
											if(presentiesCount == 0){
												examSchedules.setTotalPresentDays((int) presentiesCount);
											}else
												examSchedules.setAttTotalPresentDays(presentiesCount);
											examSchedules.setTotalAbsentDays((int)(totalWorkingDays - presentiesCount));
											
											if (totalWorkingDays > 0) {
												double percentage = roundTwoDecimals((presentiesCount / totalWorkingDays) * 100);
												examSchedules.setAttendancePercentage(percentage);
												getObjectList().add(examSchedules);
											}
											
										}
									}
									
								}
						
						}
					}
				}
		
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}

	

	public StringBuffer prepareCusotmerActivationEmailForInternalTeam(StringBuffer sb,String date,Customer customer)throws URTUniversalException {
		try {
			sb.append("Dear All,");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			sb.append("A new school "+customer.getOrganization()+" from "+customer.getAddress().getCity()+" Joined with our Eazyschool software on "+date+". From today they are ready to use our software.");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			sb.append("**NOTE: This is an automated email from EazySchool Software. Please DO NOT REPLY to this email.");
			customer=null;
			return sb;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}

	 @Actions( { 
		    @Action(value = "ajaxCheckCustomerShortNameAvailableOrNot", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
		public String ajaxCheckCustomerShortNameAvailableOrNot() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckCustomerShortNameAvailableOrNot' method");
		}
		try {
		    String shortName = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(shortName)) {
			List shortNamesList = adminManager.checkCustomerShortName(shortName);
				if (ObjectFunctions.isNullOrEmpty(shortNamesList)) {
					setAutoCheck("0");
				} else if (shortNamesList.size() > 0) {
					setAutoCheck("1");
				} else {
					setAutoCheck("0");
				}
		    }
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	 @Actions( { 
		    @Action(value = "ajaxCheckBioMetricId", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
			public String ajaxCheckBioMetricId() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxCheckBioMetricId' method");
			}
			try {
				List bioMetricIdList = null;
				String bioMetricId = null;
				if(!StringFunctions.isNullOrEmpty(getAnyId())){
					String selectedId = getAnyId().replace("?keyWord=","-");
					String[] uniqueNumber = selectedId.split("-");
					String personId = uniqueNumber[0];
					bioMetricId = uniqueNumber[1];
				}
				else{
					bioMetricId = getParamValue("keyWord");
				}
				if (StringFunctions.isNotNullOrEmpty(bioMetricId)) {
					bioMetricIdList = adminManager.getAll(ViewStaffPersonAccountDetails.class," bioMetricId like "+bioMetricId.trim()+" and custId="+getUserCustId());
				}
				if (bioMetricIdList.size() > 0) {
				    setAutoCheck("1");
				} else {
				    setAutoCheck("0");
				}
			} catch (Exception ex) {
				log.error("Entering into 'catch block':" + ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	 public void allTeachersAndHodStudyClassesList(long accountId,long academicYearId) {
		 StringBuffer query = new StringBuffer("FROM StudyClass studyClass WHERE studyClass.id in(")
			.append("SELECT cteacher.studyClass FROM ClassTeacher cteacher WHERE cteacher.staff.account=").append(accountId)
		  	.append(" and cteacher.academicYear=").append(academicYearId).append(" and cteacher.staff.status='Y' group by cteacher.studyClass)");
		  		//setStudyClassList(adminManager.getAllHqlQuery(query.toString()));
			HashSet<StudyClass> classSections = new HashSet<StudyClass>();
		  		List studyClasseList = adminManager.getAllHqlQuery(query.toString());
		  		if(!ObjectFunctions.isNullOrEmpty(studyClasseList)){
		  			classSections.addAll(studyClasseList);
		  		}
		  		if(getUser().isOnlySchoolHod() || getUser().isAdminCoordinator()){
		  			
		  			Object[] staff = adminManager.get("select id,description from staff where accountId="+accountId+" and  status='Y'");
					if(!ObjectFunctions.isNullOrEmpty(staff))
					{
						if(!ObjectFunctions.isNullOrEmpty(staff[0]))
						{
							List studyClassesList =getHodStudyClassesList(Long.valueOf(staff[0].toString()),academicYearId);
							if(!ObjectFunctions.isNullOrEmpty(studyClassesList))
							{
								classSections.addAll(studyClassesList);
							}
							studyClassesList =  null;
						}	
					}
				}
		  		if (ObjectFunctions.isNotNullOrEmpty(classSections)) {
					setStudyClassList(ConvertUtil.convertSetToList(classSections));
					Collections.sort(getStudyClassList());
				}
		  		classSections = null;
	  		if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList()))
		  		Collections.sort(getStudyClassList());
		  	query = null;
		}
	 
	 public void viewDefaultersFeeTermsAndClass(){
		setClassList(adminManager.getAllByCustId("ClassName", getUserCustId(),getUserAcademicYearId()));
		Collections.sort(getClassList());
		prepareSchoolFeeSettingList();
		setSchoolTermsList(adminManager.getAll(SchoolTerms.class,"custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId() +" and feeSettingId in "+getAnyTitle()+" and dueDate <='"+DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date())+" 00:00:00' order by feeSettingId"));
	 }
	 
	 public Properties getResouceBundleURLConfiguraionPropertiesFileDetails() throws FileNotFoundException {
			Properties configProp = new Properties();
			InputStream in = new FileInputStream(new File(getSession().getServletContext().getRealPath("/WEB-INF/classes/eschoolURL_en.properties")));
			if(!ObjectFunctions.isNullOrEmpty(in)){
				try {
		        	configProp.load(in);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
			return configProp;
		}
	 public boolean validateExcelFileType(String uploadfileType) {
		try {
			if (!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(uploadfileType)|| Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(uploadfileType) ||  Constants.FILE_TYPE_APPLICATION_MSEXCEL.equalsIgnoreCase(uploadfileType)
					|| Constants.FILE_TYPE_APPLICATION_MS_EXCEL.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_XML.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_OPD.equalsIgnoreCase(uploadfileType)
					|| Constants.FILE_TYPE_APPLICATION_XLSX.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_XLTX.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_XLSM.equalsIgnoreCase(uploadfileType)
					|| Constants.FILE_TYPE_APPLICATION_XLTM.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_XLAM.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_XLSB.equalsIgnoreCase(uploadfileType)
					|| Constants.FILE_TYPE_APPLICATION_STREAM_CSV.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_DOWNLOAD.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_STREAM_XLS.equalsIgnoreCase(uploadfileType)
					|| Constants.FILE_TYPE_STREAM.equalsIgnoreCase(uploadfileType) || Constants.FILE_TYPE_APPLICATION_STREAM.equalsIgnoreCase(uploadfileType)
					)) {
					log.debug("fileType " + uploadfileType);
					return true;
			} else
				return false;

		} catch (Exception ex) {
			log.debug(ex.getMessage());
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return false;
	}
	 
	 public void paySlipDownloadInPDF(long accountIds,File folder) {
		 try {
			 getResponse().setContentType("application/pdf");
			 getResponse().addHeader("Content-Disposition", "attachment; filename="+accountIds+".pdf");
 				FileInputStream fileInputStream = new FileInputStream(folder);
	  			OutputStream responseOutputStream = getResponse().getOutputStream();
	  			int bytes;
	  			while ((bytes = fileInputStream.read()) != -1) {
	  				responseOutputStream.write(bytes);
	  			}
	  		fileInputStream.close();
	  		responseOutputStream.flush();
		 } catch (Exception ex) {
			log.debug(ex.getMessage());
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			 JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	 }
	 
	 
	 
	 
	 @Actions({ @Action(value = "ajaxDoViewVideos", results = { @Result(location = "../admin/videos/ajaxDoViewVideos.jsp", name = "success") }) })
		public String ajaxDoViewVideos() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoViewVideos' method");
			}
			try {
				String receiverType=getAllVideosString();
				List svList= adminManager.getAll(SchoolVideos.class, "custId="+ getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+" and receiverType in("+receiverType+") ORDER BY id DESC");
				if (!ObjectFunctions.isNullOrEmpty(svList)) {
					if (svList.size() > 8) {
						setObjectList(svList.subList(0, 8));
						setTempString("viewMore");
					}else{
						setObjectList(svList);
					}
				} 
				receiverType=null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
		@Actions({ @Action(value = "ajaxViewAllVideos", results = { @Result(location = "../admin/videos/ajaxViewAllVideos.jsp", name = "success") }) })
		public String ajaxViewAllVideos() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewAllVideos' method");
			}
			try {
				String receiverType=getAllVideosString();
				setTempList(adminManager.getAll(SchoolVideos.class, "custId="+ getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+" and receiverType in("+receiverType+") ORDER BY id DESC"));
				receiverType=null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
		public String getAllVideosString(){
			StringBuffer query = new StringBuffer();
			if ("Y".equalsIgnoreCase(getUser().getIsOnlySchoolAdmin())) {
				query.append("'A','S','ST','P'");
			}
			else if ("Y".equalsIgnoreCase(getUser().getIsSchoolStudent())) {
				query.append("'ST','A'");
			}else if ("Y".equalsIgnoreCase(getUser().getIsParent())) {
				query.append("'P','A'");
			}else{
				query.append("'S','A'");
			}
			return  query.toString();
		}
		
		@Actions({ @Action(value = "ajaxDoAddVideos", results = { @Result(location = "../admin/videos/ajaxDoAddVideos.jsp", name = "success") }) })
		public String ajaxDoAddVideos() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddVideos' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getSchoolVideos())){
					setSchoolVideos((SchoolVideos)adminManager.get(SchoolVideos.class, getSchoolVideos().getId()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		
		@Actions({ @Action(value = "ajaxAddVideos", results = { @Result(location = "../admin/videos/ajaxDoViewVideos.jsp", name = "success") }) })
		public String ajaxAddVideos() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddVideos' method");
			}
			try {
				SchoolVideos sv =null;
				if(getSchoolVideos().getId()>0){
					sv = (SchoolVideos)adminManager.get(SchoolVideos.class, getSchoolVideos().getId());
					super.addActionMessage("Video updated Successfully.");
				}else{
					sv=new SchoolVideos();
					sv.setCreatedById(getUser().getId());
					sv.setCustId(getUserCustId());
					sv.setAcademicYearId(getUserAcademicYearId());
					sv.setCreatedDate(new Date());
					super.addActionMessage("Video added Successfully.");
				}
			    sv.setLastUpdatedById(getUser().getId());
			    sv.setTitle(getSchoolVideos().getTitle());
			    sv.setEmbedCode(getSchoolVideos().getEmbedCode());
			    sv.setReceiverType(getSchoolVideos().getReceiverType());
				adminManager.merge(sv);
				ajaxDoViewVideos();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		@Actions({ @Action(value = "ajaxDeleteVedios", results = { @Result(location = "../admin/videos/ajaxViewVideos.jsp", name = "success") }) })
		public String ajaxDeleteVedios() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDeleteVedios' method");
			}
			try {
				adminManager.remove(SchoolVideos.class, getSchoolVideos().getId());
				super.addActionMessage("Video is deleted successfully.");
				ajaxDoViewVideos();
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		public void ajaxGetClassHavingPeriodsOrNot() {
			 
			 setStudyClassList(adminManager.getAll(StudyClass.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
				List<ViewClassWisePeriodsCountDetails> classWisePeriodsCountDetails = adminManager.getAll(ViewClassWisePeriodsCountDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " order by classSectionId");
				Map<Long, List<ViewClassWisePeriodsCountDetails>> classWisePeriodsCountDetailsMap = new HashMap<Long, List<ViewClassWisePeriodsCountDetails>>();
				long classSectionId = 0;
				List<ViewClassWisePeriodsCountDetails> periodsCountDetailsList = null;
				/*@Ganesh : Here I am getting all class section details those details I will store hash map and each class and section class periods I will store in one class with respective class section once I done above process I will iterte the class section get the respective class section perios. Here we have performance issue that's why we modified in this process. Now it is coming in with in seconds.*/
				if (!ObjectFunctions.isNullOrEmpty(classWisePeriodsCountDetails)) {
					for (ViewClassWisePeriodsCountDetails periodsCountDetails : classWisePeriodsCountDetails) {
						if (periodsCountDetails.getClassSectionId() != classSectionId) {
							periodsCountDetailsList = new ArrayList<ViewClassWisePeriodsCountDetails>();
						}
						periodsCountDetailsList.add(periodsCountDetails);
						classWisePeriodsCountDetailsMap.put(periodsCountDetails.getClassSectionId(),periodsCountDetailsList);
						classSectionId = periodsCountDetails.getClassSectionId();
					}
				}
				if (ObjectFunctions.isNotNullOrEmpty(getStudyClassList())) {
					for (StudyClass studyClass : getStudyClassList()) {
						studyClass.setTimeTablePeriodsDetails(classWisePeriodsCountDetailsMap.get(studyClass.getId()));
						studyClass=null;
					}
					Collections.sort(getStudyClassList());
					setNumberOfDays(adminManager.getCount("workingDays","academicYearId=" + getUserAcademicYearId()));
				}
		 }
		
		public List getStaffSubjectsListForAssignment(long studyClassId)
		{
			try {
				List<ClassTeacher> classTeacherList = null;
				Staff staff = (Staff) staffManager.get(Staff.class, " accountId = " + getUser().getId() + " and status = '" + Constants.YES_STRING+"'");
				ClassTeacher classTeacher = (ClassTeacher)staffManager.get(ClassTeacher.class,"studyClassId="+studyClassId+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and teacherId="+staff.getId()+ " and classTeacher='Y' group by studySubjectId ");
				if(!ObjectFunctions.isNullOrEmpty(classTeacher))
					classTeacherList = staffManager.getAll(ClassTeacher.class,"studyClassId="+studyClassId+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+ " group by studySubjectId ");
				else
					classTeacherList = staffManager.getAll(ClassTeacher.class,"studyClassId="+studyClassId+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and teacherId="+staff.getId()+ " group by studySubjectId ");
				
				if(ObjectFunctions.isNotNullOrEmpty(classTeacherList))
				{
					StringBuffer subjectIds = new StringBuffer();
					for(ClassTeacher classTeacher1 : classTeacherList)
					{
						subjectIds.append(classTeacher1.getStudySubjectId()+",");
						classTeacher1 = null;
					}
					
					staff = null;
					classTeacher = null;
					 classTeacherList = null;
					 
					return adminManager.getAll(ViewClassAssignmentDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+studyClassId+"  and subjectId in ("+subjectIds.toString()+"0) group by subjectId");
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		

	public String SecondaryUsernameAvailableOrNot(String username){
		try {
			List<User> staffSeconrdyUserList = null;
			List<User> custStaffSeconrdyUserList = null;
			if (StringFunctions.isNotNullOrEmpty(username)) {
				staffSeconrdyUserList = adminManager.getAll(User.class," username like '" + username+ "' and accountEnabled='Y' ");
				if (staffSeconrdyUserList.size() > 0) {
					User user = staffSeconrdyUserList.get(0);
					if(user.isParent()){
						staffSeconrdyUserList = adminManager.getAll(User.class," username like 'S" + username+ "' and accountEnabled='Y' ");
						if(staffSeconrdyUserList.size()>0){
							return "1";
						}
					}else
						return "1";
				} else {
					custStaffSeconrdyUserList = adminManager.getAll(User.class," username like '" + username+ "' and accountEnabled='N' and custId ="+getUserCustId());
					if(custStaffSeconrdyUserList.size()>0){
						return "1";
					}else
						return "0";
				}
			}
			staffSeconrdyUserList = null;
			custStaffSeconrdyUserList=null;
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	@Actions( { @Action(value = "ajaxSendOTPToSecondaryusername", results = { @Result(type = "json", name = "success", params = {"includeProperties", "isOTPSend" }) }) })
	public String ajaxSendOTPToSecondaryusername()throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendOTPToSecondaryusername' method");
		}
		try {
			JSONObject sendOTPJson=null;
			getSmsCount();
			if(StringFunctions.isNotNullOrEmpty(getTempString()) && getTempString().length() ==10){
				String randamNumber = RandomStringUtils.randomNumeric(6);
				User user =(User)adminManager.get(User.class, getUser().getId());
				Customer customer = getCustomerByCustId();
				if(!ObjectFunctions.isNullOrEmpty(user)){
					if(!ObjectFunctions.isNullOrEmpty(customer) && customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(getTempString())  && (getSmsAlloted()!=0 && getSmsAlloted() > getSmsCnt())){
						SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
						Messages message = new Messages();
		              	StringBuffer msgContent = new StringBuffer();
		              	 Set<String> mobileNumberset = new HashSet<String>();
	              		 msgContent.append("Dear "+user.getFullPersonName()+", Please enter OTP : "+randamNumber+" to complete your mobile number updation process. Thank you from ");
		              	log.debug(msgContent.toString());
		              	message.setMessageDescription(msgContent.toString());
		              	message.setCreatedById(getUser().getId());
		                message.setAcademicYear(getCurrentAcademicYear());
		                message.setPurposeType("regd: OTP");
		              	message.setCustomer(customer);
		              	message.setSmsSenderId(customer.getSender());
		              	mobileNumberset.add(getTempString());
		              	if (ObjectFunctions.isNotNullOrEmpty(mobileNumberset)){
			                message = communicationManager.saveMessageDetailsTracking(message,null,null,user);
			                communicationManager.deliverSms(message,mobileNumberset, smsServiceProvider);
			                msgContent = null;
			                mobileNumberset = null;
		              	}
					user.setOTP(randamNumber);
					adminManager.save(user);
					user=null;
					sendOTPJson = new JSONObject().put("isOTPSend", true);
				}else
					sendOTPJson = new JSONObject().put("isOTPSend", false);
			}else
				sendOTPJson = new JSONObject().put("isOTPSend", false);
				getResponse().getOutputStream().print(sendOTPJson.toString());
				sendOTPJson=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	public String generateStudentDisabilityUploadFilepath(Customer customer,String memberName,long applicationId,long accountId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'generateStudentDisabilityUploadFilepath' method");
		}
		memberName = memberName.replaceAll(" ", "_");
		StringBuffer pathName = new StringBuffer("userFiles/Student/");
		if(accountId>0){
			pathName.append(customer.getCustomerShortName()+customer.getId()+memberName+"_"+accountId);
			pathName.append("/physicallyHandicapped/docs");
		}
		else if(applicationId>0){
			pathName.append("Admissions/");
			pathName.append(customer.getCustomerShortName()+customer.getId()+memberName+"_"+applicationId);
			pathName.append("/Disability/docs");
		}
	    pathName.append("/");
		return pathName.toString();
	}
	public void removeStudentDisabiltyDocuments(long tempId1,long tempId) {
		StringBuffer pathName =null;
		log.debug(getAnyId());
		Customer customer = getCustomerByCustId();
		if (tempId!=0){
				setStudent((Student) studentManager.get(Student.class,tempId));
		}
		if(tempId1>0)
			setOnlineApplicationDetails((OnlineApplicationDetails) adminManager.get(OnlineApplicationDetails.class,tempId1));

		if (!ObjectFunctions.isNullOrEmpty(getStudent()) || !ObjectFunctions.isNullOrEmpty(getOnlineApplicationDetails())) 
		{
			if(tempId1!=0)
				pathName = new StringBuffer(generateStudentDisabilityUploadFilepath(customer,getOnlineApplicationDetails().getFirstName().replaceAll(" ", "_"),getOnlineApplicationDetails().getId(),0));
			if(tempId!=0)
				pathName = new StringBuffer(generateStudentDisabilityUploadFilepath(customer,getStudent().getAccount().getPerson().getFirstName().replaceAll(" ", "_"),0,getStudent().getAccount().getId()));
		    if(StringFunctions.isNotNullOrEmpty(pathName.toString())){
		    	File destFile = new File(getSession().getServletContext().getRealPath(pathName.toString()));
	    		if(destFile.exists()){	
			    	if((tempId>0 && (!"deleteAll".equalsIgnoreCase(getEventId()))) || (!"deleteAll".equalsIgnoreCase(getEventId()) && tempId1>0)){
		    				File[] children = destFile.listFiles();
					        if (children == null) {
					            // Either dir does not exist or is not a directory
					        	try {
									FileUtils.deleteDirectory(destFile);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					        } else {
					            for (int i=0; i<children.length; i++) {
					                // Get filename of file or directory
					            	if(getFileName().equals(children[i].getName()))
					            	children[i].delete();
					            }
					            super.addActionMessage("Document removed successfully.");
				            }
		    			}else{
		    				try {
								FileUtils.deleteDirectory(destFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    				super.addActionMessage("Documents removed successfully.");
		    			}
			    	setFileName(null);
	    		}
	    		else{
	    			super.addActionMessage("Directory doesn't exist.");
	    		}
			}
		}
	}
	
	public Map<Long,String> getCountriesNamesMap()
	{
		Map<Long,String> countriesNamesMap = new HashMap<Long, String>();
		List<Country> countryList = (List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST);
		if(!ObjectFunctions.isNullOrEmpty(countryList))
		{
			for(Country country : countryList)
			{
				countriesNamesMap.put(country.getId(), country.getCountryName());
			}
			return countriesNamesMap;
		}
		return null;
	}
	
	@Actions( { @Action(value = "ajaxSendOTPToForgotPassword", results = { @Result(type = "json", name = "success", params = {"includeProperties", "isOTPSend" }) }) })
	public String ajaxSendOTPToForgotPassword()throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendOTPToForgotPassword' method");
		}
		try { 
				String randamNumber = RandomStringUtils.randomNumeric(6);
				//Customer masterCustomer=(Customer)adminManager.getMasterCustomerById();
				User user = userManager.getUserByUserName(getParamValue("username"));
				if(!ObjectFunctions.isNullOrEmpty(user)){
					Customer masterCustomer = null;
					Customer customer = (Customer)adminManager.get(Customer.class, "id="+user.getCustId());
					if(customer.getId() == 173)
						masterCustomer = customer;
					else
						masterCustomer = (Customer)adminManager.getMasterCustomerById();
						SMSServiceProviders smsServiceProvider=(SMSServiceProviders) adminManager.getSMSServiceProviderByCustId(masterCustomer.getSmsServiceProviderId());
						Messages message = new Messages();
		              	StringBuffer msgContent = new StringBuffer();
		              	Set<String> mobileNumberset = new HashSet<String>();
	              		msgContent.append("Dear "+user.getFullPersonName()+", Please enter OTP : "+randamNumber+" to complete your forgot password updation process. Thank you from ");
		              	log.debug(msgContent.toString());
		              	message.setMessageDescription(msgContent.toString());
		              	message.setCreatedById(user.getId());
		                message.setAcademicYear(getCurrentAcademicYear());
		                message.setPurposeType("regd:Forgot Password OTP");
		              	message.setCustomer(masterCustomer);
		              	message.setSenderName(masterCustomer.getSender());
		              	ViewAllUsers viewAllUsers =(ViewAllUsers) adminManager.get(ViewAllUsers.class,"accountId="+user.getId()+" and username='"+user.getUsername()+"'");
		              	if(!ObjectFunctions.isNullOrEmpty(viewAllUsers.getMobileNumber()))
		              		mobileNumberset.add(viewAllUsers.getMobileNumber());
		              	if (ObjectFunctions.isNotNullOrEmpty(mobileNumberset)){
			                message = communicationManager.saveMessageDetailsTracking(message,null,null,user);
			                communicationManager.deliverSms(message,mobileNumberset,smsServiceProvider);
			                user.setOTP(randamNumber);
							adminManager.save(user);
			                msgContent = null;
			                mobileNumberset = null;
			                setTempString("O"); // Here send otp after show/hide the Otp, Usrename field
			                String mobileNo = viewAllUsers.getMobileNumber().substring (6);
			                setTempString2("******"+mobileNo);
			                super.addActionMessage("OTP has been sent to your mobile number. Please enter the OTP.");
		              	}else{
		              		super.addActionError("No communication details found.");
		              	}
					user=null;
					smsServiceProvider = null;
					message = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
		public String getUserIpAddress(){
			if (log.isDebugEnabled()) {
				log.debug("Entering 'getUserIpAddress' method");
			}
			String ipAddress = null;
			try{
				if (!ObjectFunctions.isNullOrEmpty(getUser())) {
					getRequest().getHeader("VIA");
					String address = getRequest().getHeader("X-FORWARDED-FOR");
					if (address == null) {
						ipAddress = getRequest().getRemoteAddr();
					}
				}
			}catch (Exception ex) {
				ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return ipAddress;
		}
		/**
		 *  User Computer Name 
		 * @return
		 */
		
		public String getUserComputerName(){
			if (log.isDebugEnabled()) {
				log.debug("Entering 'getUserComputerName' method");
			}
			String computerName = null;
			try{
				computerName = InetAddress.getLocalHost().getHostName();
			}catch (Exception ex) {
				ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return computerName;
		}
		/**
		 * User Computer Username 
		 * @return
		 */
		
		public String getUserComputerUsername(){
			if (log.isDebugEnabled()) {
				log.debug("Entering 'getUserComputerUsername' method");
			}
			String computerUsername = null;
			try{
				computerUsername = System.getProperty("user.name");
			}catch (Exception ex) {
				ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return computerUsername;
		}
		public void checkPreviousAcademicYearPensingStudentFee(){
			try {
				AcademicYear  academicYear = (AcademicYear)adminManager.get(AcademicYear.class, getUserAcademicYearId());
				Customer customer = getCustomerByCustId();
			    if(!ObjectFunctions.isNullOrEmpty(academicYear)){
			    	setFeeModuleUsege(academicYear.getFeeModuleUsegeBy());
			    	String prevYear=(Long.valueOf(academicYear.getAcademicYear().split("-")[0])-1)+"-"+(Long.valueOf(academicYear.getAcademicYear().split("-")[1])-1);
			    	if(!StringFunctions.isNullOrEmpty(prevYear)){
			    	AcademicYear previousYear = (AcademicYear)adminManager.get(AcademicYear.class, "custId="+getUserCustId()+" and academicYear='"+prevYear+"' ");
			    	if(!ObjectFunctions.isNullOrEmpty(previousYear)){
			    		if("N".equalsIgnoreCase(customer.getShowPreviousYearPendingFee()))
			    				previousFeeValidationCheckingAndSaveStatus(previousYear,customer);
			    		else{
			    			long studentCount = adminManager.getCount("student", "custId="+getUserCustId()+" and academicYearId="+previousYear.getId()+" and feePaidStatus='P' and description is null");
			    			if(studentCount>0)
			    				previousFeeValidationCheckingAndSaveStatus(previousYear,customer);
			    			else
			    				setCustomer(customer);
			    		}
			    		setTempId(previousYear.getId());
		    			setTempString(previousYear.getAcademicYear());
			    	}
			    	}
			    	academicYear=null;
			    	prevYear=null;
			    }
			} catch (Exception ex) {
				log.error("Entering into 'catch block':" + ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		public void previousFeeValidationCheckingAndSaveStatus(AcademicYear previousYear,Customer customer){
	    	if(!ObjectFunctions.isNullOrEmpty(previousYear)){
	    		Fee fee=(Fee)adminManager.get(Fee.class, "custId="+getUserCustId()+" and academicYearId="+previousYear.getId());
	    		if(!ObjectFunctions.isNullOrEmpty(fee)){
	    			//List<Student> studentList=adminManager.getAll(Student.class, "custId="+getUserCustId()+" and academicYearId="+previousYear.getId()+" and feePaidStatus='P' and description is null");
	    			long studentCount = adminManager.getCount("student", "custId="+getUserCustId()+" and academicYearId="+previousYear.getId()+" and feePaidStatus='P' and description is null");
	    			if(studentCount>0){
	    				customer.setShowPreviousYearPendingFee("Y");
	    				customer=(Customer)adminManager.saveOrUpdateObject(customer);
	    				setCustomer(customer);
	    				setStudentSize(studentCount);
	    				//setStudentsList(studentList);
	    				//studentList=null;
	    				studentCount=0;
	    			}
	    			fee=null;
	    		}
	    		previousYear=null;
	    	}
	    	log.debug("previousFeeValidationCheckingAndSaveStatus end:"+new Date());
		}
		
		@Actions( { @Action(value = "ajaxDoCheckAllSMSCount", results = { @Result(type = "json", name = "success", params = {"includeProperties", "tempString" }) }) })
		public String ajaxDoCheckAllSMSCount()throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoCheckAllSMSCount' method");
			}
			try {
					String mainTempString = getTempString();
					JSONObject errorMsgJson=new JSONObject();
					int allMobileNumbersCount = 0;
					
					if("A".equalsIgnoreCase(mainTempString) || "P".equalsIgnoreCase(mainTempString))
					{
						List<Object[]> studentMobielsList = adminManager.getAll("select mobileNumber,accountId from vw_studentDetails where custId="+getUserCustId()+" and academicYearId="+ getUserAcademicYearId() +" and mobileNumber is not null and (mobileNumber <> '' AND mobileNumber !='+91-0000000000') and status='Y'");
						if (!ObjectFunctions.isNullOrEmpty(studentMobielsList)) {
							allMobileNumbersCount = studentMobielsList.size();
						}
						studentMobielsList = null;
					}
				
					if("A".equalsIgnoreCase(mainTempString) || "SA".equalsIgnoreCase(mainTempString))
					{
						List<Object[]> staffMobielsList = adminManager.getAll("select mobileNumber,accountId from vw_staffPayrollDetails where custId="+getUserCustId()+" and academicYearId<="+ getUserAcademicYearId() +" and mobileNumber is not null and (mobileNumber <> '')");
						if (!ObjectFunctions.isNullOrEmpty(staffMobielsList)) {
							allMobileNumbersCount = allMobileNumbersCount + staffMobielsList.size();
						}
						staffMobielsList = null;
					}
					
					int availableSMSCount = adminManager.getAvailableSmsCount(getUserCustId(), getUserAcademicYearId());
					
					if(allMobileNumbersCount>availableSMSCount)
					{
						errorMsgJson.put("ErrorMsg","Your available SMS count is "+availableSMSCount+" and your are trying to send "+allMobileNumbersCount+" SMS. Please increase your SMS count by contacting EazySchool supporting team (080-46620999).");
					}
					getResponse().getOutputStream().print(errorMsgJson.toString());
					errorMsgJson = null;
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
				JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return null;
		}

		public String admissionNumberGenerationBySetting(OnlineApplicationDetails onlineApplicationDetails,long classNameClassId)
		{
			 String admissionNumber = null;
			 String prefix="";
			 String postfix="";
			 int sequenceNumber = 0;
			 Object[] admissionNumberSettingsObj = null;
			 AdmissionNumberSettings admissionNumberSettings = null;
			if(!ObjectFunctions.isNullOrEmpty(onlineApplicationDetails)){
				admissionNumberSettings = (AdmissionNumberSettings)adminManager.get(AdmissionNumberSettings.class, "custId="+getUserCustId()+" and academicYearId="+ onlineApplicationDetails.getAcademicYear().getId() +" and admissionNumberType='SW'" );
			}
			if(!ObjectFunctions.isNullOrEmpty(admissionNumberSettings))
			{
				if (!ObjectFunctions.isNullOrEmpty(admissionNumberSettings.getPrefix())) 
				 {
					 prefix = admissionNumberSettings.getPrefix();
				 }
				 if (!ObjectFunctions.isNullOrEmpty(admissionNumberSettings.getPostfix())) 
				 {
					 postfix = admissionNumberSettings.getPostfix();
				 }
				 sequenceNumber = admissionNumberSettings.getSequenceNumber();
				 
			}
			else
			{
				admissionNumberSettingsObj = adminManager.get("select ans.prefix,ans.postfix,ans.sequenceNumber,ans.admissionNumberType from admissionNumberSettings ans LEFT JOIN admissionNumberClasses anc on (ans.id=anc.admissionNumberSettingsId) LEFT JOIN class c on (c.id=anc.classId) where ans.custId="+ getUserCustId()+ " and ans.academicYearId="+ onlineApplicationDetails.getAcademicYear().getId()+ " and c.id="+ classNameClassId);
				 if (!ObjectFunctions.isNullOrEmpty(admissionNumberSettingsObj)) 
				 {
					 if (!ObjectFunctions.isNullOrEmpty(admissionNumberSettingsObj[0])) 
					 {
						 prefix = admissionNumberSettingsObj[0].toString();
					 }
					 if (!ObjectFunctions.isNullOrEmpty(admissionNumberSettingsObj[1])) 
					 {
						 postfix = admissionNumberSettingsObj[1].toString();
					 }
					 sequenceNumber = Integer.parseInt(admissionNumberSettingsObj[2].toString());
				 }
			}
			 
			if(!ObjectFunctions.isNullOrEmpty(admissionNumberSettings) || !ObjectFunctions.isNullOrEmpty(admissionNumberSettingsObj))
			{
				String admissionNumberStr = prefix+"%"+postfix;
				 StringBuilder stBuilder = new StringBuilder();
				 stBuilder.append(" joinedThroughAdmissions='"+ Constants.YES_STRING+ "'");
				 stBuilder.append(" and custId=" + getUserCustId());
				 if(ObjectFunctions.isNullOrEmpty(admissionNumberSettings))
				 {
					 stBuilder.append(" and classId=" + classNameClassId);
				 }
				 
				 stBuilder.append(" and academicYearId=" + onlineApplicationDetails.getAcademicYear().getId() +" and admissionNumber like '"+admissionNumberStr+"' order by accountId DESc Limit 1");
				 
				 ViewStudentClassDetails viewStudentClassDetails = (ViewStudentClassDetails)adminManager.get(ViewStudentClassDetails.class,stBuilder.toString());
				 if (!ObjectFunctions.isNullOrEmpty(viewStudentClassDetails)) 
				 {
					 String sequenceNumberStr = viewStudentClassDetails.getAdmissionNumber().replaceFirst(prefix, "").replaceAll(postfix, "");
					 admissionNumber = prefix+(Integer.parseInt(sequenceNumberStr)+1)+postfix;
					 sequenceNumberStr = null;
				 }
				 else
				 {
					 admissionNumber = prefix+sequenceNumber+postfix;
				 }
				 //setAdmissionNumber(admissionNumber.trim());
				 return admissionNumber.trim();
			}
			return null;
		}

		public long getCountryId() {
			return countryId;
		}

		public void setCountryId(long countryId) {
			this.countryId = countryId;
		}

		/**
		 * @return the rolesList
		 */
		public List getRolesList() {
			return rolesList;
		}

		/**
		 * @param rolesList the rolesList to set
		 */
		public void setRolesList(List rolesList) {
			this.rolesList = rolesList;
		}
		/* Ravi Theja 02-08-2017 Cretae method for show the staff details based on login user in Task related*/
		public String getStaffDetailsBasedOnUser(){
			try{
				List<ViewStaffPersonAccountDetails> vwStaffDetails = null;
				if(getUser().isSchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector() || Constants.SCHOOL_VICEPRINCIPAL.equalsIgnoreCase(getUser().getRoleName()) || Constants.SCHOOL_ADMINOFFICER.equalsIgnoreCase(getUser().getRoleName())){
					vwStaffDetails = staffManager.getAll(ViewStaffPersonAccountDetails.class, "custId="+getUserCustId()+ " and status='"+Constants.YES_STRING+"' and accountId !=" +getUser().getId());
					ViewStaffPersonAccountDetails staffDetails = (ViewStaffPersonAccountDetails) staffManager.get(ViewStaffPersonAccountDetails.class, "custId="+getUserCustId()+ " and status='"+Constants.YES_STRING+"' and accountId="+getUser().getId());
					if(StringFunctions.isNotNullOrEmpty(vwStaffDetails.toString().replace("[]", ""))){
						vwStaffDetails.add(0, staffDetails);
					}else{
						vwStaffDetails = new ArrayList<ViewStaffPersonAccountDetails>();
						vwStaffDetails.add(staffDetails);
					}
					setObjectList(vwStaffDetails);
				}else if(getIsClassTeacherOrNot()){
					List<ViewClassSectionTeacher> classTeacherList = null;
					ViewClassSectionTeacher classTeacher = (ViewClassSectionTeacher) staffManager.get(ViewClassSectionTeacher.class, "status='"+Constants.YES_STRING+"' and classTeacher='"+Constants.YES_STRING+"' and custId="+getUserCustId()+ " and accountId="+getUser().getId());
					classTeacherList = staffManager.getAll(ViewClassSectionTeacher.class,"status='"+Constants.YES_STRING+"' and academicYearId="+getUserAcademicYearId()+" and classSectionId="+classTeacher.getClassSectionId()+" and custId="+getUserCustId()+" and accountId !="+ classTeacher.getAccountId()+" group by accountId order by teacherId DESC");
					if(!ObjectFunctions.isNullOrEmpty(classTeacherList)){
						if(!ObjectFunctions.isNullOrEmpty(classTeacher))
							classTeacherList.add(0, classTeacher);
					}else{
						classTeacherList = new ArrayList<ViewClassSectionTeacher>();
						classTeacherList.add(classTeacher);
					}
					setObjectList(classTeacherList);
				}else if(Constants.SCHOOL_HOD.equalsIgnoreCase(getUser().getRoleName())){
					List staffDetails = null;
					List<ViewClassSectionTeacher> classTeacherList = null;
					staffDetails = staffManager.getAll(ViewStaffPersonAccountDetails.class, "custId="+getUserCustId()+ " and status='"+Constants.YES_STRING+"' and accountId="+getUser().getId());
					Staff staff =(Staff) staffManager.get(Staff.class, "accountId="+getUser().getId());
					ViewStaffPersonAccountDetails staffAccountDetails = (ViewStaffPersonAccountDetails) staffManager.get(ViewStaffPersonAccountDetails.class, "custId="+getUserCustId()+ " and status='"+Constants.YES_STRING+"' and accountId="+getUser().getId());
					String staffStudyClassIds=StringUtil.convertListToString(staffManager.getAll("select studyClassId from staffMultipleStudyClasses where staffId="+staff.getId()));
					if(StringFunctions.isNotNullOrEmpty(staffStudyClassIds)){
						classTeacherList = staffManager.getAll(ViewClassSectionTeacher.class,"status='"+Constants.YES_STRING+"' and academicYearId="+getUserAcademicYearId()+" and classSectionId in ("+staffStudyClassIds+") and custId="+getUserCustId()+" and accountId !="+ staffAccountDetails.getAccountId()+" group by accountId order by teacherId DESC");
						if(!ObjectFunctions.isNullOrEmpty(classTeacherList)){
							staffDetails = classTeacherList;
							if(!ObjectFunctions.isNullOrEmpty(staffAccountDetails))
								staffDetails.add(0, staffAccountDetails);
						}else{
							staffDetails = new ArrayList<ViewClassSectionTeacher>();
							staffDetails.add(staffAccountDetails);
						}
						setObjectList(staffDetails);
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			} 
			return SUCCESS;
		 }
		/*Ravi Theja Panem 04-AUG-2017*/
		public String ajaxShowTaskDetailsLoginUser() throws URTUniversalException {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxShowTaskDetailsLoginUser' method");
			}
			try {
				int staffTaskDetailsCount = adminManager.getCount("vw_taskDetailsAndTaskHistory", "custId="+getUserCustId()+" and accountId="+getUser().getId()+" and status='O' ");
				if(!ObjectFunctions.isNullOrEmpty(staffTaskDetailsCount) && staffTaskDetailsCount > 0){
					getSession().setAttribute("tempString1","ST");
					setTempId(staffTaskDetailsCount);
				}else{
					getSession().removeAttribute("tempString1");
				}
			} catch (Exception ex) {
			    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}

		public List getStudentTransportTermsList() {
			return studentTransportTermsList;
		}

		public void setStudentTransportTermsList(List studentTransportTermsList) {
			this.studentTransportTermsList = studentTransportTermsList;
		}
        /**
         * This method returns the admin coordinator class list
         * @param staffId
         * @param academicyearId
         * @return
         */
		public List<StudyClass> getAdminCoordinatorStudyClassesList(long staffId,long academicyearId)
		{
			ArrayList<StudyClass> classSectionsList = new ArrayList<StudyClass>();
			List<BigInteger> studyClassIdsList = null;

			if(!ObjectFunctions.isNullOrEmpty(staffId)){
				StringBuffer query = new StringBuffer("select studyClassId from classTeacher where teacherId in(").append(staffId+")");
				List<BigInteger> studyClassIds = adminManager.getAll(query.toString());
				StringBuffer sqlQuery = new StringBuffer("select st.id  from staffMultipleStudyClasses sm LEFT JOIN studyClass st  on (st.id=sm.studyClassId)") 
				.append(" where st.custId=").append(getUserCustId()).append(" and st.academicYearId="+getUserAcademicYearId()).append(" and sm.staffId=").append(staffId);

				List<BigInteger> coOrdinatorStudyClassIds = adminManager.getAll(sqlQuery.toString());
				if (ObjectFunctions.isNotNullOrEmpty(studyClassIds) && ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
					for(BigInteger obj:coOrdinatorStudyClassIds){
						if(!studyClassIds.contains(obj)){
							studyClassIds.add(obj);
						}
					}
				studyClassIdsList = studyClassIds;
				}else if (ObjectFunctions.isNotNullOrEmpty(studyClassIds)) {
					studyClassIdsList = studyClassIds;
				}else if (ObjectFunctions.isNotNullOrEmpty(coOrdinatorStudyClassIds)) {
					studyClassIdsList = coOrdinatorStudyClassIds;
				} 
				if (ObjectFunctions.isNotNullOrEmpty(studyClassIdsList)) 
				{
					String classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(studyClassIdsList);
					List<StudyClass> studyClassList = adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(),classSectionIdsString);
					if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) 
					{
						for (StudyClass studyClass : studyClassList) 
						{
							classSectionsList.add(studyClass);
							getStudyClassesMap().put(studyClass.getClassId(),studyClass.getClassName());
						}
					}
				}
			}
			return classSectionsList;

		}
		public void getSchoolWideAlertsforAllRoles(){
			try
			{
				List dashBoardAlertsList=null;
				if (getUser().isSchoolTeacher() || getUser().isSchoolAsstStaff() || getUser().isOnlySchoolHod() || getUser().isOnlySchoolTeacher() || getUser().isAdminCoordinator()) {
					dashBoardAlertsList=studentManager.getAll(Messages.class,"custId= "+getUserCustId()+" and  academicYearId="+getUserAcademicYearId()+" and messageType='B' and receiverType='SF' and messageDate>=curDate() order by messageDate desc limit 1");
				}
				else {
					dashBoardAlertsList=studentManager.getAll(Messages.class,"custId= "+getUserCustId()+" and  academicYearId="+getUserAcademicYearId()+" and messageType='B' and receiverType='ST' and messageDate>=curDate() order by messageDate desc limit 1");
				}
				if(!ObjectFunctions.isNullOrEmpty(dashBoardAlertsList))
				{
					setDashBoardAlertsList(dashBoardAlertsList);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		
		public void getReminderToUserLogin(){
			try
			{
				 List<Reminder> reminderList=studentManager.getAll(Reminder.class,"custId="+getUserCustId()+" and accountId="+getUser().getId()+" and expirationDate >= curDate() and (reminderoption = 'E' or specificDate = curDate())");
				 if(!ObjectFunctions.isNullOrEmpty(reminderList)){
					 Collections.sort(reminderList);
					 setReminderDetails(reminderList);
				 }
				 reminderList = null;
				 getSession().removeAttribute("showReminder");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		public void getTaskReminderToUserLogin(){
			try
			{
				List<ViewTaskDetailsAndTaskHistory> taskReminderList = studentManager.getAll(ViewTaskDetailsAndTaskHistory.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getId()+" and status in ('O','H') and taskDate >= curDate() and (reminderOption = 'E' or specificDate=curDate())");
				 if(!ObjectFunctions.isNullOrEmpty(taskReminderList)){
					 Collections.sort(taskReminderList);
					 setTaskReminderDetails(taskReminderList);
				 }
				 taskReminderList = null;
				 getSession().removeAttribute("showTaskReminder");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		}
		 @Actions({
				@Action(value = "ajaxGetAbsenteesListByType", results = { @Result(location = "messages/ajaxAbsentStudentOrStaffList.jsp", name = "success" )})
				})
		 		public String ajaxSendMessagesForAbsentees() throws URTUniversalException {
					if (log.isInfoEnabled()) {
						log.info("Entering 'ajaxSendMessagesForAbsentees' method");
					}
					try{
						List<Object[]> absenteesList= null;
						boolean type=false;
						// absentees message sending for P->Students S-Staff 
						if("P".equalsIgnoreCase(getAnyTitle())){
							type=true;
							absenteesList = studentManager.getAll("select vs.fullName,vs.classNameAndSection,vs.mobileNumber,vs.parentId from vw_studentDetails vs where vs.studentId in(select vsd.studentId from vw_StudentDailyAttendance vsd where vsd.custId="+getUserCustId()+" and vsd.academicYearId="+getUserAcademicYearId()+" and (vsd.present = 'N' or vsd.afternoonSession ='N') and vsd.attendanceDate=curDate()) and vs.status ='Y'");
						}
						else if("S".equalsIgnoreCase(getAnyTitle())){
							absenteesList = studentManager.getAll("select vstd.fullName,vstd.roleDescription,vstd.mobileNumber,vstd.accountId from vw_staffDetails vstd where vstd.staffId in(select vd.staffId from vw_StaffDailyAttendance vd where vd.custId="+getUserCustId()+" and vd.academicYearId="+getUserAcademicYearId()+" and (vd.present = 'N' or vd.afternoonSession = 'N') and vd.attendanceDate=curDate()) and vstd.status ='Y'");
						}
						setTempBoolean(type);
						if(!ObjectFunctions.isNullOrEmpty(absenteesList))
							setAbsentStudOrStaffList(absenteesList);
						absenteesList = null;
					}catch(Exception exp){
						exp.printStackTrace();
					}
					return SUCCESS;
		 		}
		/**
		 * @return the feeModuleUsege
		 */
		public String getFeeModuleUsege() {
			return feeModuleUsege;
		}

		/**
		 * @param feeModuleUsege the feeModuleUsege to set
		 */
		public void setFeeModuleUsege(String feeModuleUsege) {
			this.feeModuleUsege = feeModuleUsege;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the attendanceSubmittedClassesCount
		 */
		public int getAttendanceSubmittedClassesCount() {
			return attendanceSubmittedClassesCount;
		}

		/**
		 * @param attendanceSubmittedClassesCount the attendanceSubmittedClassesCount to set
		 */
		public void setAttendanceSubmittedClassesCount(
				int attendanceSubmittedClassesCount) {
			this.attendanceSubmittedClassesCount = attendanceSubmittedClassesCount;
		}

		public String getPaymentDateStr() {
			return paymentDateStr;
		}

		public void setPaymentDateStr(String paymentDateStr) {
			this.paymentDateStr = paymentDateStr;
		}

		public List getDashBoardAlertsList() {
			if(ObjectFunctions.isNullOrEmpty(this.dashBoardAlertsList)){
				this.dashBoardAlertsList=new ArrayList();
			}
			return dashBoardAlertsList;
		}

		public void setDashBoardAlertsList(List dashBoardAlertsList) {
			this.dashBoardAlertsList = dashBoardAlertsList;
		}
		
		public long getStudentsTransportCount() {
			return studentsTransportCount;
		}

		public void setStudentsTransportCount(long studentsTransportCount) {
			this.studentsTransportCount = studentsTransportCount;
		}

		
		public List getTotalStudentsCount() {
			return TotalStudentsCount;
		}

		public void setTotalStudentsCount(List totalStudentsCount) {
			TotalStudentsCount = totalStudentsCount;
		}

		public String getReceiptGenarationDate() {
			return receiptGenarationDate;
		}

		public void setReceiptGenarationDate(String receiptGenarationDate) {
			this.receiptGenarationDate = receiptGenarationDate;
		}

		public void commonJREException(Exception ex){
			ex.printStackTrace();
			RayGunException raygex = new RayGunException(); raygex.sendRayGunException(ex); raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}

		public String getPaymentTime() {
			return paymentTime;
		}

		public void setPaymentTime(String paymentTime) {
			this.paymentTime = paymentTime;
		}

		public StudentFeeRefund getStudentFeeRefund() {
			return studentFeeRefund;
		}

		public void setStudentFeeRefund(StudentFeeRefund studentFeeRefund) {
			this.studentFeeRefund = studentFeeRefund;
		}
        
		/**
		 * @return the houseTypeList
		 */
		public List<HouseType> getHouseTypeList() {
			return houseTypeList;
		}

		/**
		 * @param houseTypeList the houseTypeList to set
		 */
		public void setHouseTypeList(List<HouseType> houseTypeList) {
			this.houseTypeList = houseTypeList;
		}
		
		public ViewStudentFeeRefundDetails getRefundedFeeDetails() {
			return refundedFeeDetails;
		}

		public void setRefundedFeeDetails(ViewStudentFeeRefundDetails refundedFeeDetails) {
			this.refundedFeeDetails = refundedFeeDetails;
		}

		public String getFilterName() {
			return filterName;
		}

		public void setFilterName(String filterName) {
			this.filterName = filterName;
		}
		public List getReminderDetails() {
			return reminderDetails;
		}

		public void setReminderDetails(List reminderDetails) {
			this.reminderDetails = reminderDetails;
		}
		public double getBalanceAmount() {
			return balanceAmount;
		}

		public void setBalanceAmount(double balanceAmount) {
			this.balanceAmount = balanceAmount;
		}

		public List<Object[]> getAbsentStudOrStaffList() {
			return absentStudOrStaffList;
		}

		public void setAbsentStudOrStaffList(List<Object[]> absentStudOrStaffList) {
			this.absentStudOrStaffList = absentStudOrStaffList;
		}

		public String getMessageRecieverType() {
			return messageRecieverType;
		}

		public void setMessageRecieverType(String messageRecieverType) {
			this.messageRecieverType = messageRecieverType;
		}

		public List getTaskReminderDetails() {
			return taskReminderDetails;
		}

		public void setTaskReminderDetails(List taskReminderDetails) {
			this.taskReminderDetails = taskReminderDetails;
		}
		@Actions({ @Action(value = "ajaxViewStaffAndStudentTimetableDetails", results = { @Result(location = "../admin/manualTimetable/ajaxViewStaffTimeTableDetails.jsp", name = "success"),
				  @Result(location = "../admin/timeTable/ajaxViewStudentTimeTableDetails.jsp", name = "studentDashboard")}) })
		public String ajaxViewStaffAndStudentTimetableDetails() throws URTUniversalException {
			if (log.isInfoEnabled()) log.info("Entering 'ajaxViewStaffAndStudentTimetableDetails' method");
			try{
				if(!ObjectFunctions.isNullOrEmpty(getUser())){
					TimeTableSettings timeTableSettingsObj= getTimeTableSettingsByAcademicYearId(getUserAcademicYearId()); // (TimeTableSettings) adminManager.get(TimeTableSettings.class,"custId="+custId+" and academicDetailsId="+academicDetails.getId()); 
					setTimeTableSettings(timeTableSettingsObj);
					if(!ObjectFunctions.isNullOrEmpty(getTimeTableSettings())){
						setTimeTableDetailsCount(adminManager.getCount("vw_timeTableStaffDetails", "accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId()));
						if((getUser().isOnlySchoolTeacher() || getUser().isOnlySchoolHod()) && getTimeTableDetailsCount()==0){
							return "SUCCESS";
						}
						List<Long> workingDayListIds = adminManager.getAll("select dayId from workingDays where academicYearId="+getUserAcademicYearId());
						String workingDayIds=null;
						if(!ObjectFunctions.isNullOrEmpty(workingDayListIds))
							workingDayIds = StringFunctions.convertListToCommaDelimitedString(workingDayListIds);
						setWeekDayList(adminManager.getAll(WeekDays.class,"id in  ("+workingDayIds+")"));
						workingDayIds=null;workingDayListIds=null;
						setTimeTablePeriodList(adminManager.getAll("select id,periodName,TIME_FORMAT(startTime, '%h:%i') as startTime,TIME_FORMAT(endTime, '%h:%i') as endTime from timeTablePeriod where timeTableSettingId="+timeTableSettingsObj.getId()));
						if(getUser().isSchoolStudent()){
							StudyClass studyClass = (StudyClass) adminManager.get(StudyClass.class, "id="+getTempId()+" and status='"+Constants.YES_STRING+"'");
							if(!ObjectFunctions.isNullOrEmpty(studyClass) && ObjectFunctions.isNotNullOrEmpty(studyClass.getSubjects())){
								setSubjectsList(ConvertUtil.convertSetToList(studyClass.getSubjects()));
								for(StudySubject obj:studyClass.getSubjects()){
									if(!ObjectFunctions.isNullOrEmpty(obj)){
									if(obj.isLanguage()){
										getObjectList1().add(obj);
									}
									if(Constants.YES_STRING.equalsIgnoreCase(obj.getSubjectType())){
										getObjectList().add(obj);
									}
									obj=null;
									}
								}
							}studyClass=null;
						}
						timeTableSettingsObj=null;
					}
					if(getUser().isSchoolStudent()){
						return "studentDashboard";
					}
				}
			} catch(Exception ex) {
				ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
			return SUCCESS;
	}
	public TimeTableSettings getTimeTableSettings() {
		return timeTableSettings;
	}
	public void setTimeTableSettings(TimeTableSettings timeTableSettings) {
		this.timeTableSettings = timeTableSettings;
	}
	public List getTimeTablePeriodList() {
		if (ObjectFunctions.isNullOrEmpty(this.timeTablePeriodList)) {
			this.timeTablePeriodList = new ArrayList();
		}
		return timeTablePeriodList;
	}
	public void setTimeTablePeriodList(List timeTablePeriodList) {
		this.timeTablePeriodList = timeTablePeriodList;
	}
	public List getObjectList1() {
		if (ObjectFunctions.isNullOrEmpty(this.objectList1)) {
			this.objectList1 = new ArrayList();
		}
		return this.objectList1;
	}
	public void setObjectList1(List objectList1) {
		this.objectList1 = objectList1;
	}
	public int getTimeTableDetailsCount() {
		return timeTableDetailsCount;
	}
	public void setTimeTableDetailsCount(int timeTableDetailsCount) {
		this.timeTableDetailsCount = timeTableDetailsCount;
	}
	public TimeTableSettings getTimeTableSettingsByAcademicYearId(long academicYearId) {
		if (log.isInfoEnabled()) log.info("Entering 'getTimeTableSettingsByAcademicYearId' method");
		try{
			if(academicYearId > 0) {
				return (TimeTableSettings)adminManager.get(TimeTableSettings.class,"academicYearId="+academicYearId);
			}
		}catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
		public List<ViewStudyClassSubjects> getStudyClassSubjectDetails() {
			return studyClassSubjectDetails;
		}
		public void setStudyClassSubjectDetails(
				List<ViewStudyClassSubjects> studyClassSubjectDetails) {
			this.studyClassSubjectDetails = studyClassSubjectDetails;
		}
		public String getClassSectionNameId() {
			return classSectionNameId;
		}
		public void setClassSectionNameId(String classSectionNameId) {
			this.classSectionNameId = classSectionNameId;
		}
		public String getStudyClassSubjectId() {
			return studyClassSubjectId;
		}
		public void setStudyClassSubjectId(String studyClassSubjectId) {
			this.studyClassSubjectId = studyClassSubjectId;
		}
		public List<StaffSyllabusPlanner> getSyllabusPlannerList() {
			return syllabusPlannerList;
		}
		public void setSyllabusPlannerList(
				List<StaffSyllabusPlanner> syllabusPlannerList) {
			this.syllabusPlannerList = syllabusPlannerList;
		}
		public List getQuestionBankDetails() {
			return questionBankDetails;
		}
		public void setQuestionBankDetails(List questionBankDetails) {
			this.questionBankDetails = questionBankDetails;
		}
		public String getLessonId() {
			return lessonId;
		}
		public void setLessonId(String lessonId) {
			this.lessonId = lessonId;
		}
		public String getQuestionBankId() {
			return questionBankId;
		}
		public void setQuestionBankId(String questionBankId) {
			this.questionBankId = questionBankId;
		}
		public List<ParentIncomeRange> getParentIncomeRangesList() {
			return parentIncomeRangesList;
		}
		public void setParentIncomeRangesList(
				List<ParentIncomeRange> parentIncomeRangesList) {
			this.parentIncomeRangesList = parentIncomeRangesList;
		}
		public String getSelectedIncomeId() {
			return selectedIncomeId;
		}
		public void setSelectedIncomeId(String selectedIncomeId) {
			this.selectedIncomeId = selectedIncomeId;
		}
		public List<Events> getEventsList() {
			if(ObjectFunctions.isNullOrEmpty(this.eventsList)){
			this.eventsList = new ArrayList<Events>();
			}
			return eventsList;
			}
			public void setEventsList(List<Events> eventsList) {
			this.eventsList = eventsList;
			}
 } 