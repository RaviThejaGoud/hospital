package com.churchgroup.common.constants;



/**
 * Constant values used throughout the application.
 *
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:sjadapolu@uroomtech.com">Sreeramulu Jadapolu</a>
 */ 
public class Constants {
    //~ Static fields/initializers =============================================
   
    /** The name of the ResourceBundle used in this application */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /** The encryption algorithm key to be used for passwords */
    public static final String ENC_ALGORITHM = "algorithm";

    /** A flag to indicate if passwords should be encrypted */
    public static final String ENCRYPT_PASSWORD = "encryptPassword";
    public static final String VALUE="N";

    /** File separator from System properties */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /** User home from System properties */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /** The name of the configuration hashmap stored in application scope. */
    public static final String CONFIG = "appConfig";
    public static final String FALSE_KEY="false";

    /** 
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */ 
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts.action.LOCALE";
    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String DROP_DOWN_SELECT = "userForm";
    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";
    
    /**
     * The request scope attribute that holds the Groups list
     */
    public static final String GROUPS_LIST = "groupsList";
    
    /**
     * The request scope attribute that holds the Organization Category List
     */
    public static final String ORG_CATEGORY_LIST = "orgCategoryList";
    
    public static final String GROUP_ID = "selectedGroupId";
    
    public static final String LEADER_GROUP_IDS = "leaderGroupsId";
    public static final String PREVIOUS_REQUEST_URI = "previousRequestURI";
    
    public static final String GET_ALL_USERS = "getAllUsers";
    public static final String GET_ALL_USERS_WITHOUT_GROUPS = "getAllUsersWithoutGroups";
    
    /**
     * The name of the Primary role
     */
    public static final String PRIMARY_STATUS = "Primary";
    /**
     * This variable is used to determine the group event status (recurring events)
     */
    public static final String GROUP_EVENT_STATUS = "G";
    /**
     * This variable is used to determine the group event status (recurring events)
     */
    public static final String EVENT_RECURRING_STATUS = "R";
    /**
     * The name of the Primary role
     */
    public static final String ALTERNATIVE_STATUS = "Alternative";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    
    
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String MF_ADMIN = "ROLE_MF_ADMIN";
    
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String BSF_ADMIN = "ROLE_BSF_ADMIN";
    
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String SIS_ADMIN = "ROLE_SIS_ADMIN";
    
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String EMS_ADMIN = "ROLE_EMS_ADMIN";
    
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String CAMPAIGN_ADMIN = "ROLE_CAMPAIGN_ADMIN";
    public static final String EVENTS_ADMIN = "ROLE_EVENTS_ADMIN";
    public static final String NEIGHBOURHOOD_ADMIN = "ROLE_NEIGHBOURHOOD_ADMIN";
    public static final String GROUPS_ADMIN = "ROLE_GROUPS_ADMIN";
    public static final String METRICS_ADMIN = "ROLE_METRICS_ADMIN";
    public static final String PROJECTS_ADMIN = "ROLE_PROJECTS_ADMIN";  
    public static final String THESTORY_ADMIN = "ROLE_THESTORY_ADMIN";
    /* Online Ticket Roles */
    public static final String MASTER_ADMIN = "ROLE_MASTER_ADMIN";
    public static final String DISTRIBUTOR_ADMIN = "ROLE_DISTRIBUTOR_ADMIN";
    public static final String CUSTOMER_ADMIN = "ROLE_CUSTOMER_ADMIN";
    
    public static final String SCHOOL_ADMIN = "ROLE_ADMIN";  
    public static final String SCHOOL_TEACHER = "ROLE_TEACHER";
    public static final String SCHOOL_STUDENT = "ROLE_STUDENT";
    public static final String SCHOOL_CLERK = "ROLE_CLERK";
    public static final String SCHOOL_PRINCIPAL = "ROLE_PRINCIPAL";
    public static final String SCHOOL_PARENT = "ROLE_PARENT";
    public static final String SCHOOL_HOD = "ROLE_HOD";
    public static final String SCHOOL_LIBRARIAN = "ROLE_LIBRARIAN";
    public static final String SCHOOL_MASTERADMIN = "ROLE_MASTERADMIN";
    public static final String SCHOOL_OTHER = "ROLE_OTHER";
    public static final String SCHOOL_DRIVER = "ROLE_DRIVER";
    public static final String SCHOOL_HELPER = "ROLE_HELPER";
    public static final String SCHOOL_HOSTEL = "ROLE_HOSTEL";
    public static final String SCHOOL_HOSTELFINANCE = "ROLE_HOSTELFINANCE";
    public static final String SCHOOL_TRANSPORT = "ROLE_TRANSPORT";
    public static final String SCHOOL_TRANSPORTFINANCE = "ROLE_TRANSPORTFINANCE";
    public static final String SCHOOL_FINANCE = "ROLE_FINANCE";
    public static final String NEIGHBOURHOOD_LEADER = "ROLE_NEIGHBOURHOOD_LEADER";
    public static final String NEIGHBOURHOOD_BASIC_ADMIN = "ROLE_NEIGHBOURHOOD_BASIC_ADMIN";
    public static final String AREA_COMMUNITY_LEADER = "ROLE_AREA_COMMUNITY_LEADER";
    public static final String SCHOOL_PEON = "ROLE_PEON";
    public static final String SCHOOL_AYAH = "ROLE_AYAH";
    public static final String SCHOOL_CONDUCTOR = "ROLE_CONDUCTOR";
    public static final String SCHOOL_SYSTEMADMINISTRATOR = "ROLE_SYSTEMADMINISTRATOR";
    public static final String SCHOOL_LABASST = "ROLE_LABASST";
    public static final String SCHOOL_MANAGEMENTTRAINEE = "ROLE_MANAGEMENTTRAINEE";
    public static final String SCHOOL_WATCHMAN = "ROLE_WATCHMAN";
    public static final String SCHOOL_TYPIST  = "ROLE_TYPIST"; 
    public static final String SCHOOL_SWEEPER  = "ROLE_SWEEPER"; 
    public static final String SCHOOL_SEO  = "ROLE_SEO"; 
    public static final String SCHOOL_DEO  = "ROLE_DEO"; 
    public static final String SCHOOL_CEO  = "ROLE_CEO";
    public static final String SCHOOL_BEO  = "ROLE_BEO";
    public static final String SCHOOL_VICEPRINCIPAL = "ROLE_VICEPRINCIPAL";
    public static final String SCHOOL_PUBLICRELATIONOFFICER = "ROLE_PUBLICRELATIONOFFICER";
    public static final String SCHOOL_ADMINOFFICER = "ROLE_ADMINOFFICER";
    public static final String FPS = "ROLE_FPS";
    
    public static final String SCHOOL_ASST_STAFF = "ROLE_ASST_TEACHER";
    public static final String SCHOOL_SECRETARY = "ROLE_SECRETARY";
    public static final String SCHOOL_SECRETARY_PA = "ROLE_SECRETARY_PA";
    public static final String SCHOOL_MANAGER = "ROLE_MANAGER";
    public static final String SCHOOL_ROLE_CHAIR_MAN = "ROLE_CHAIR_MAN";
    public static final String SCHOOL_ROLE_MESS_MANAGER = "ROLE_MESS_MANAGER";
    
    public static final String SCHOOL_ROLE_DIRECTOR = "ROLE_DIRECTOR";
    public static final String SCHOOL_ROLE_SALES_HEAD = "ROLE_SALES_HEAD";
    public static final String SCHOOL_ROLE_SALES_EXECUTIVE = "ROLE_SALES_EXECUTIVE";
    public static final String SCHOOL_ROLE_COORDINATOR = "ROLE_COORDINATOR";
    public static final String SCHOOL_ROLE_BDM = "ROLE_BDM";
    
    public static final String SCHOOL_ROLE_EXECUTIVE_VICE_CHAIRMAN = "ROLE_EXECUTIVE_VICE_CHAIRMAN";
    public static final String SCHOOL_ROLE_VICE_CHAIRMAN = "ROLE_VICE_CHAIRMAN";
    public static final String SCHOOL_ROLE_TREASURER = "ROLE_TREASURER";
    public static final String SCHOOL_ROLE_JOINT_SECRETARIES = "ROLE_JOINT_SECRETARIES";
    public static final String SCHOOL_ROLE_EXECUTIVE_MEMBER = "ROLE_EXECUTIVE_MEMBER";
    public static final String SCHOOL_ROLE_MEMBER = "ROLE_MEMBER";
    public static final String SCHOOL_ADMIN_COORDINATOR = "ROLE_ADMIN_COORDINATOR";
    public static final String SCHOOL_ROLE_STOREKEEPER = "ROLE_STOREKEEPER";
    public static final String SCHOOL_ROLE_RECEPTIONIST = "ROLE_RECEPTIONIST";
    public static final String SCHOOL_ROLE_STAFF_NURSE = "ROLE_STAFF_NURSE";

    
    /**
     * Bank Roles
     */
    
    public static final String BANK_CLERK  = "ROLE_BANK_CLERK";
    public static final String BANK_MANAGER  = "ROLE_BANK_MANAGER";
    public static final String BANK_CEO  = "ROLE_BANK_CEO";
    public static final String BANK_ADMIN  = "ROLE_BANK_ADMIN";
    
    
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String TICKER_MASTER_ADMIN = "ROLE_TICKER_MASTER_ADMIN";
    public static final String TICKER_CLIENT_ADMIN = "ROLE_TICKER_CLIENT_ADMIN";
    
    public static final String PRICENGINE_ADMIN  = "ROLE_ADMIN"; 
    public static final String PRICENGINE_CSR  = "ROLE_CSR"; 
    /*
     * payment types
     */
    
//    private static final String SINGLE_PAYMENT="SINGLE_PAYMENT";
  //  private static final String TERM_PAYMENT="TERM_PAYMENT";
    
    
    /*
     * FEE types
     */
    //private static final String TRANSPORT_FEE="TRANSPORT_FEE";
    //private static final String ADMISSION_FEE="ADMISSION_FEE";
    //private static final String EXAMINATION_FEE="EXAMINATION_FEE";
  
    
    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";
    /**
     * The name of the Leader role, as specified in web.xml
     */
    public static final String LEADER_ROLE = "ROLE_LEADER";
    
    /**
     * The name of the Leader role, as specified in web.xml
     */
    public static final String DELEGATE_ROLE = "ROLE_DELEGATE";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";
    
    /**
     * The name of the available Active Group Categories, a request-scoped attribute
     */
    public static final String ACTIVE_GROUP_CATEGORY = "activeGroupCategory";
    
    /**
     * The name of the available Active Group Categories, a request-scoped attribute
     */
    public static final String ACTIVE_GROUP_TYPE_C0NTEXT = "activeGroupTypeContent";
    /**
     * The name of the available Active Group Categories, a request-scoped attribute
     */
    public static final String ACTIVE_COUNTRY_STATE_C0NTEXT = "activeCountryStateContext";
    /**
     * To store all the time stamps 12:00 AM - 11:45 PM
     */
    public static final String TIMESTAMP_BETWEEN_12AM_1145PM = "timeStampBetween12AM1145PM";
    
    /**
     * The name of the available Phone Usage list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_PHONE_USAGES= "availablePhoneUsages";
    
    /**
     * The name of the available Phone Usage list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_MARITAL_STATUS= "availableMaritalStatus";
    
    /**
     * The name of the available Phone Usage list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_GENDAR= "availableGendar";
    
    /**
     * The name of the available ACTIVE_STATUS, a request-scoped attribute
     * used to check the status of event or group, etc
     */
    public static final String ACTIVE_STATUS= "A";
    public static final String PENDING_STATUS= "P";
    public static final String REJECTED_STATUS= "R";
    
    public static final String PENDING_STATUS_DESC= "Pending";
    public static final String CONTACTED_STATUS_DESC= "Contacted";
    public static final String REQUIRES_RESPONSE_DESC= "Requires Response";
    public static final String DAYS_STATUS= " Days";
    
    /**
     * The name of the available SHARE_PRIVACY_NOT_SHARE, a request-scoped attribute
     * used to if member is opted to not share
     */
    public static final String SHARE_PRIVACY_NOT_SHARE= "3";
    
    /**
     * The name of the available Home contact details, a request-scoped attribute
     * used to  determine if member has Home contact details or not
     */
    public static final String HOME_CONTACTS= "Home";
    /**
     * The name of the available Home contact details, a request-scoped attribute
     * used to  determine if member has Home contact details or not
     */
    public static final String WORK_CONTACTS= "Work";
    public static final String CELL_CONTACTS= "Cell";
    public static final String OTHER_CONTACTS= "Other";
    public static final String CHURCH_WIDE_EVENT_TYPE= "E";
    public static final String CHURCH_WIDE_MESSAGE_TYPE= "M";
    /**
     * The name of the available EVENT_FREQUENCY_WEEKLY, 
     */
    public static final String EVENT_FREQUENCY_WEEKLY= "W";
    public static final String EVENT_FREQUENCY_WEEKLY_DESCRIPTION= "Weekly";
    /**
     * The name of the available EVENT_FREQUENCY_EVERY_OTHER_WEEK, 
     * "E"
     */
    public static final String EVENT_FREQUENCY_EVERY_OTHER_WEEK= "E";
    public static final String EVENT_FREQUENCY_EVERY_OTHER_WEEK_DESCRIPTION= "Every Other Week";
    /**
     * The name of the available EVENT_FREQUENCY_EVERY_OTHER_WEEK, 
     */
    public static final String EVENT_FREQUENCY_MONTHLY= "M";
    public static final String EVENT_FREQUENCY_MONTHLY_DESCRIPTION= "Monthly";
    
    public static final String EVENT_FREQUENCY_ONCE= "O";
    
    public static final String EVENT_SUBJECT= "Group Weekly Meeting";
    
    public static final String EVENT_DESCRIPTION= "Group Weekly Meeting";
    
    public static final String EVENT_MONTHLY_SUBJECT= "Group Monthly Meeting";
    
    public static final String EVENT_MONTHLY_DESCRIPTION= "Group Monthly Meeting";
    
    /**
     * The name of the available ALL_GROUPS 
     */
    public static final String ALL_GROUPS= "AGS";
    
    public static final String ICON_LIFE_GROUP="/images/church/icon_group16_001.gif";
    public static final String ICON_ABF_GROUP="/images/church/icon_group16_002.gif";
    public static final String ICON_DISCOVERY_GROUP="/images/church/icon_group16_003.gif";
    public static final String ICON_GENERAL_GROUP="/images/church/icon_church16.gif";
    
    public static final String GROUP_STATUS_REJECTED="R";
    public static final String GROUP_STATUS_ACTIVE="A";
    public static final String GROUP_STATUS_CLOSED="C";
    public static final String GROUP_STATUS_CLOSED_DESC="Closed";
    public static final String GROUP_STATUS_FULL="F";
    public static final String GROUP_STATUS_BREAK="B";
    public static final String GROUP_SEARCH_MAP_SHOW_STATUS="Yes";
    public static final String GROUP_SEARCH_MAP_HIDE_STATUS="No";
    
    public static final String GROUP_STATUS_ACTIVE_DESCRIPTION="Open";
    public static final String GROUP_STATUS_FULL_DESCRIPTION="Full";
    public static final String GROUP_STATUS_BREAK_DESCRIPTION="On Break Until";
    
    public static final String GROUP_GENDER_MALE="M";
    public static final String GROUP_GENDER_WOMEN="W";
    public static final String GROUP_GENDER_MIXED="I";
    public static final String GROUP_GENDER_SINGLES="S";
    
    public static final String GROUP_GENDER_MALE_DESCRIPTION="Men";
    public static final String GROUP_GENDER_WOMEN_DESCRIPTION="Women";
    public static final String GROUP_GENDER_MIXED_DESCRIPTION="Mixed";
    public static final String GROUP_GENDER_SINGLES_DESCRIPTION="Singles";
    public static final String YES="Yes";
    public static final String YES_STRING="Y";
    public static final String NO="No";
    public static final String NO_STRING="N";
    public static final String DECLINE_STATUS="D";
    public static final String DELETE_STATUS="D";
    public static final String TENTATIVE_STATUS="T";
    public static final String TRANSPORT_STATUS="T";
    public static final String HOSTEL_STATUS="H";
    public static final String HOSTEL_FEE="Hostel Fee";
    public static final String TRANSPORT_FEES="Transport Fee";
    public static final String CSS_THEME="csstheme";
    public static final String POPUP_THEME="popUptheme";
    public static final String MODIFY_STATUS="M";
    public static final String DELETE_EXAMSCHEDULES="DE";
    
    
    public static final String STRING_SEPERATOR="~";
    
    public static final String EVENT_DATE_TOMORROW="T";
    public static final String EVENT_DATE_TODAY="Y";
    public static final String EVENT_DATE_LATER_THIS_WEEK="L";
    
    public static final String URI_TYPE_PARAM="type";
    public static final String URI_ID_PARAM="id";
    public static final String NO_URI="noURI";
    
    public static final String MESSAGE_TYPE="MESSAGE";
    public static final String PRAYER_REQUEST_TYPE="PRAYER REQUEST";
    
    public static final String MSG_PRAYER_REQUEST="P";
    public static final String ALL_MESSAGES_EVENTS="A";
    public static final String GROUP_MESSAGES_EVENTS="G";
    public static final String CHURCH_MESSAGES_EVENTS="C";
    public static final String SELECT = "- Select -";
    public static final String SELECT_ID = "N";
    public static final String SELECTROLE = "- Select Role-";
    
    public static final String LEADER_CONTENT = "L";
    public static final String GENERAL_CATEGORY = "General";
    
    /**
     * User is Planning to Visit group
     */
    public static final String INQUIRY_REQUEST_VISIT_STATUS = "V";
    public static final String INQUIRY_REQUEST_VISIT_DESC = "Planning on Visiting";
    /**
     * Contacted, Awaiting Responce
     */
    public static final String INQUIRY_REQUEST_AWAITING_STATUS = "R";
    public static final String INQUIRY_REQUEST_AWAITING_DESC = "Awaiting Responce";
    /**
     * Contacted, No longer interested
     */
    public static final String INQUIRY_REQUEST_NOT_INTERESTED_STATUS = "D";
    public static final String INQUIRY_REQUEST_NOT_INTERESTED_DESC = "No longer interested";
    /**
     * Contacted, Group is Full or closed
     */
    public static final String INQUIRY_REQUEST_GROUP_CLOSEDRFULL_STATUS = "F";
    public static final String INQUIRY_REQUEST_GROUP_CLOSEDRFULL_DESC = "Group is Full or closed";
    /**
     * Contacted, Add to Group
     */
    public static final String INQUIRY_REQUEST_ADD_2GROUP_STATUS = "A";
    public static final String INQUIRY_REQUEST_ADD_2GROUP_DESC = "Add to Group";
    /**
     * Contacted, Invite to Register and Add to Group
     */
    public static final String INQUIRY_REQUEST_INVITE_STATUS = "I";
    public static final String INQUIRY_REQUEST_INVITE_DESC = "Invite to Register and Add to Group";
    
    public static final String FILE_PROFILE_USAGE = "Profile";
    public static final String FILE_PROFILE_DIR = "struts.multipart.userProfileImgDir";
    public static final String ALBUM_PROFILE_DIR = "struts.multipart.albumDir";
    public static final String ADVERISEMENT_PROFILE_DIR = "struts.multipart.advtDir";
    public static final String FILE_DOCS_USAGE = "Docs";
    public static final String FILE_DOCS_DIR = "struts.multipart.userDocsDir";
    
    public static final String FILE_TYPE_IMAGE = "image";
    public static final String FILE_TYPE_APPLICATION_DOWNLOAD = "application/download";
    public static final String FILE_TYPE_APPLICATION = "application";
    public static final String FILE_TYPE_STREAM = "octet-stream";
    public static final String FILE_TYPE_APPLICATION_STREAM = "application/octet-stream";
    public static final String FILE_TYPE_PATH = "images/filetypes/";
    public static final String FILE_TYPE_WORD = "msword";
    public static final String FILE_TYPE_PDF = "pdf";
    
    
    
    public static final String FILE_TYPE_XLS = "vnd.ms-excel";
    public static final String FILE_TYPE_APPLICATION_XLS = "application/vnd.ms-excel";
    public static final String FILE_TYPE_APPLICATION_MSEXCEL = "application/x-msexcel";
    public static final String FILE_TYPE_APPLICATION_MS_EXCEL = "application/ms-excel";
    public static final String FILE_TYPE_XML = "vnd.ms-excel";
    public static final String FILE_TYPE_APPLICATION_OPD = "application/vnd.oasis.opendocument.spreadsheet";
    
    public static final String FILE_TYPE_APPLICATION_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String FILE_TYPE_APPLICATION_XLTX = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
    public static final String FILE_TYPE_APPLICATION_XLSM = "application/vnd.ms-excel.sheet.macroEnabled.12";
    public static final String FILE_TYPE_APPLICATION_XLTM = "application/vnd.ms-excel.template.macroEnabled.12";
    public static final String FILE_TYPE_APPLICATION_XLAM = "application/vnd.ms-excel.addin.macroEnabled.12";
    public static final String FILE_TYPE_APPLICATION_XLSB = "application/vnd.ms-excel.sheet.binary.macroEnabled.12";
    
    
    
    public static final String FILE_TYPE_CSV = "text/CSV";
    public static final String FILE_TYPE_APPLICATION_CSV = "application/CSV";
    public static final String FILE_TYPE_APPLICATION_STREAM_CSV = "application/octet-stream";
  
    public static final String FILE_TYPE_TEXT = "text";
    public static final String FILE_TYPE_PPT = "vnd.ms-powerpoint";
    public static final String FILE_TYPE_ZIP = "x-zip-compressed";
    public static final String FILE_TYPE_MDB = "mdb";
    public static final String FILE_TYPE_FOLDER = "folder";
    public static final String HELP_CONTENT_FOR_LEADER = "HL";
    public static final String HELP_CONTENT_FOR_MEMBER = "HM";
    public static final String HELP_CONTENT_FOR_TEACHER = "HT";
    public static final String HELP_CONTENT_FOR_STUDENT = "HS";
    public static final String SYSTEM_ERROR_MESSAGE = "System error has occurred. Please try again or Contact System Administrator";
    public static final String DRAFT     = "DRAFT";
    
    public static final String SMS_SERVICE_URL = "http://api.myvaluefirst.com/psms/servlet/psms.Eservice2";
    public static final String ADDRESS_TAG = "<ADDRESS FROM=\"{0}\" TO=\"{1}\" SEQ=\"{2}\" />";
    public static final String STATUS_TAG = "<STATUS SEQ=\"{0}\" />";
    public static final String DATA_TAG_SEND = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!DOCTYPE MESSAGE SYSTEM \"http://127.0.0.1:80/psms/dtd/messagev12.dtd\"><MESSAGE VER=\"1.2\"><USER USERNAME=\"{0}\" PASSWORD=\"{1}\"/><SMS UDH=\"0\" CODING=\"1\" TEXT=\"{2}\" PROPERTY=\"\" ID=\"1\">{3}</SMS></MESSAGE>";
    public static final String DATA_TAG_STATUS_REQ = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!DOCTYPE STATUSREQUEST SYSTEM \"http://127.0.0.1:80/psms/dtd/requeststatusv12.dtd\"><STATUSREQUEST VER=\"1.2\"><USER USERNAME=\"{0}\" PASSWORD=\"{1}\"/><GUID GUID=\"{2}\">{3}</GUID></STATUSREQUEST>";
    public static final String SMS_CREDITS = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!DOCTYPE REQUESTCREDIT SYSTEM \"http://127.0.0.1:80/psms/dtd/requestcredit.dtd\"><REQUESTCREDIT USERNAME=\"{0}\" PASSWORD=\"{1}\"></REQUESTCREDIT>";
  
    public static final String PUBLISHED = "PUBLISHED";
    public static final String SCHEDULED = "SCHEDULED";
    
    public static final String PAYPAL_CANCEL_STATUS = "c2aebxdm4iw9lfu8v";
    public static final String PAYPAL_STATUS_SUCCESS = "kjsne6hs913nds7420md31gasdjae0wndaw6";
    
    public static final String APPROVED = "APPROVED";
    public static final String DISAPPROVED = "DISAPPROVED";
    public static final String SPAM = "SPAM";
    public static final String PENDING = "PENDING";
    
    public static final String CHURCH_WIDE_EVENT_ADVT="churchwideeventadvt";
    public static final String CUSTOMER_USER_HEADER_LOGO = "user_header_logo";
    public static final String CUSTOMER_LOGO = "customerLogo";
    
    
    public static final String ORGANIZATION_CACHE_OBJECT = "organization_cache_object";
    public static final String ORGANIZATION_ADDRESS_OBJECT = "organization_address_object";
    public static final String ORGANIZATION_DETAIL_OBJECT = "organization_detail_object";
    public static final String UNIT_TEST="U";
    public static final String HALFYEAR_TEST="H";
    public static final String QUARTER_TEST="Q";
    public static final String ANNUAL_TEST="A";
    
    public static final String ACTION_MESSAGE_ID = "successMsg";
    public static final String ACTION_FAILURE_MESSAGE_ID = "failureMsg";

	public static final String DEFAULT_STATUS= "D";
	public static final String ONLY_IMAGE= "B";
	public static final String TEACHING_CONTENT= "T";
	public static final String CONTACT_US_CONTENT= "U";
	public static final String HOME_BANNER_STATUS= "H";
	public static final String HOME_MENU_STATUS= "M";
	public static final String HOME_PAGE_BANNER_ADVT="homepagebanner";
	public static final String URI_NAV_PARAM="nav";
	
	 public static final String OFFER_RIDE = "O";
	 public static final String ADD_RIDE_SUBJECT= "AS";
	 public static final String ADD_RIDE_BODY = "AB";
	 public static final String ADD_RIDE_EMAIL = "AE";
	 public static final String ADD_RIDE_FOOTER = "AF";
	 
	 public static final String OFFER_RIDE_WITHOUTAPPROVE = "OFFER_RIDE";
	 public static final String OFFER_RIDE_UNDERAPPROVE  = "OFFER_RIDE_UNDERAPPROVE ";
	 public static final String APPROVED_RIDE = "APPROVED_RIDE";
	 public static final String DECLINE_RIDE = "DECLINE_RIDE";
	 public static final String REQUEST_RIDE = "REQUEST_RIDE";
	 public static final String NEW_OFFER_TO_APPROVE = "NEWOFFER_TOAPPROVE";
	 public static final String FILE_MINUTES_USAGE = "M";
	 public static final String FILE_MOVIES_USAGE = "M";
	 public static final boolean STATUS_TRUE = true;
	 public static final boolean STATUS_FALSE = false;
	 public static final String GILITE_FILE_DOCS_DIR = "/userfiles/userDocs";
	 public static final String CUSTOMER_MY_ACCOUNT = "M";
	 public static final String CUSTOMER_UPPER_ROOM_ACCOUNT = "U";
	 public static final String CUSTOMER_DISTRIBUTOR_ACCOUNT = "D";
	 public static final String OPERATOR_ROLE = "ROLE_OPERATOR";
	 public static final String SUCCESS = "success";
	 public static final String FAILURE = "failure";
	 public static final String SCHOOL_MODULE = "S";
	 public static final String BLOCKED_STATUS="B";
	 public static final String PAYMENT_STATUS="P";
	 
	 public static final String BOOK_OPEND="O";
	 public static final String BOOK_CLOSED="C";
	 public static final String BOOK_SUBMITED="S";
	 public static final String BOOK_RECEIVED="R";
	 public static final String PARTIAL_STATUS="P";
	 public static final String ISSUE_BOOK="IB";
	 public static final String READING_BOOK="RB";
	 public static final String POST_TO_PULBLIC="P";
	 public static final String POST_TO_ONLYME="O";
	 public static final String POST_TO_FRIENDS="F";
	 
	 public static final String SUSPEND_STATUS = "S";
	 
	 public static final String FILE_TYPE_APPLICATION_STREAM_XLS = "application/xls";
	 public static final String FILE_TYPE_IMAGE_PNG = "image/png";
	 public static final String FILE_TYPE_IMAGE_JPG = "image/jpg";
	 public static final String FILE_TYPE_IMAGE_JPEG = "image/jpeg";
	 public static final String FILE_TYPE_IMAGE_GIF = "image/gif";
	 
	//new Array("jpg","pdf","jpeg","gif","png","doc","docx","xls","xlsx","ppt","txt")) == false)
	public static final String DEVELOPER_TEAM = "D";
	public static final String SUPPORT_TEAM = "S";
	public static final String TESTING_TEAM = "T";
		
	public static final String A_FOUR = "A4";
	public static final String FEE_CERTIFICATE = "FC";
	public static final String MOTHER_TONGUE_LIST = "MOTHER_TONGUE_LIST";
	public static final String STATE_LIST = "STATE_LIST";
	public static final String COUNTRY_LIST = "COUNTRY_LIST";
	public static final String MEDIUM_LIST = "MEDIUM_LIST";
	public static final String BANK_LIST = "BANK_LIST";
	public static final String PERIOD="Period";
	public static final String SHORT_BREAK="Short Break";
	public static final String LUNCH_BREAK="Lunch Break";
}