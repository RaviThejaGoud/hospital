package com.urt.service.manager.interfaces.base;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyniva.sms.ws.vo.common.SMSVO;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.ScrapMessage;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;

/**
 * Business Facade interface. 
 *
 * @author <a href="mailto:sreeram@uroomtech.com">Sreeramulu J</a>
 *
 * Modifications and studyClass by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * This thing used to be named simply 'GenericManager' in versions of AppFuse prior to 2.0.
 * It was renamed in an attempt to distinguish and describe it as something
 * different than GenericManager.  GenericManager is intended for subclassing, and was
 * named Generic because 1) it has very general functionality and 2) is
 * 'generic' in the Java 5 sense of the word... aka... it uses Generics.
 *
 * Implementations of this class are not intended for subclassing. You most
 * likely would want to subclass GenericManager.  The only real difference is that
 * instances of java.lang.Class are passed into the methods in this class, and
 * they are part of the constructor in the GenericManager, hence you'll have to do
 * some casting if you use this one.
 *
 * @see com.einvite.service.GenericManager
 */
public interface UniversalManager {
    /**
     * Generic method used to get a all objects of a particular type. 
     * @param clazz the type of objects 
     * @return List of populated objects
     */
	
    List getAll(Class clazz);

    /**
     * Generic method to get an object based on class and identifier. 
     * 
     * @param clazz model class to lookup
     * @param id the identifier (primary key) of the class
     * @return a populated object 
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
	
    Object get(Class clazz, Serializable id);
	
    Object get(Class clazz, Serializable id, String columnName);
	
    Object get(Class clazz,String clause) throws DataAccessException;

    /**
     * Generic method to save an object.
     * @param o the object to save
     * @return a populated object
     */
	
    Object save(Object o);
    
    void persist(Object o);
	
    Object saveOrUpdateObject(Object o);
	
    Object merge(Object o);
    /**
     * Generic method to delete an object based on class and id
     * @param clazz model class to lookup
     * @param id the identifier of the class
     */
    void remove(Class clazz, Serializable id);
    
    List getAll(Class clazz,  String clause);
    int remove(String clazz, String clause);
    int getCount(String clazz, String clause);
    
    List getAll(String sqlQuery);
    Object[] get(String sqlQuery);
    
    List getAllHqlQuery(String hqlQuery);
    int getCountForGroupByClause(String clazz, String clause);
    
    User getUserEmailByUserName(String email);
    
    /**
	 * @description Get the customer object
	 * @param customer Id
	 * @return customer object or null
	 * 
	 */
    
    Customer getCustomerByCustId(Serializable custId);
    
    AcademicYear getCurrentAcademicYear(String status, long custId);
    
    Role getRoleByName(String rolename);
    
    Map<String, Integer> getMonthwiseSchoolWorkingDays(long custId,long academicYearId, Date startDate, Date closeDate,boolean isMthWiseWrkDaysUptoCurrentDate,String option, String classSectionId);
    
    Map<String,List> getSchoolAcademicYearWorkingDays(long academicYearId);
    
    /**
     * 
     * @param custId
     * @param academicYearId
     * @param classIds
     * @param accountIds
     * @param hostlerStatus
     * @param studentQuery
     * @return
     */
    
    Set GetStudentMobileNumbers(long custId, long academicYearId, List<String> classIds,List<String> accountIds,String hostlerStatus,StringBuffer query,String mobileType);
    
    /**
     * 
     * @param countryCode
     * @return map
     */
    
    Map<String, State> getCountryStates(int countryCode);
    /*List<SchoolHolidays> getHolidaysListByMonthIdForAttendance(int monthId,long orgId,String holidayYear,Date date,long customerId, long classId, String staffOrStudent);*/	
    boolean IsUserAccountExists(String precode, String firstName, String initial);
    
    /**
     * Gets users information based on login name.
     * @param username the user's username
     * @return User populated User object
     */
    
    User getUserByUserName(String username);
    
    CommonType getCommonType(long custId,String type,String mediumId);
    
    CastSettings getCastNames(String stateName,long custId);
    
    SubCastSettings getSubCast(long custId,String subCastName,long castId);
    
    void writeToFile(String content, String path);
    
    List getAllByCustId(String clazz,long custId,long academicYearId); 
   // List getAllByCustId(String clazz,long custId);
    void updateClassAndSectionCapacity(long custId, long academicYearId, long classId, long classSectionId);	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    boolean deliverSms(Messages message, Set<String> mobileNumbers,SMSServiceProviders smsServiceProvider);
    
    List<StudyClass> getStudyClassesForClassTeacherAndAdmin(User user,long academicYearId);
    
    Map<String,String> sendForgotPasswordByUserName(User luser);
    
    SchoolHolidays getHolidayByCustIdAndAcademicYearId(long custId, long academicYearId, String holidayDate, long classId, String description, String status, String tempString);
    
	List<SchoolHolidays> getSchoolHolidaysListByDatesAndCustId(long custId, long academicYearId, String holidayDate, String holidayStartDate, String holidayEndDate, String classId, String description, String status, int monthId, String anyTitle, String yearId);
    
	Map<String,String> UpdateReplyNewScrapMessage(long custId, AcademicYear academicYear, long scrapSenderAccountId, long scrapReceiverAccountId, String scrapDescription, long scrapMessageId,String scrapDate,long userId);
    
	ScrapMessage getScrapMessageById(long scrapMessageId);
	/* To save the Tracking details of  each message when list of objects are sent.  */
	Messages addMessageDetailsTracking (Messages message,List<Student> studentsList,List<Staff> staffList,Set<String> otherMobileNumbers);
	/* To save the Tracking details of  each message when single object is sent.  */
	
	Messages saveMessageDetailsTracking (Messages message,Student student,Staff staff,User user);
	MessageDetailsTracking entryMessageTracking(Messages message,MessageDetailsTracking messageDetailsTracking,Student student,Staff staff,String mobileNumber,User user);
	void saveServiceRequesByCustId(long custId, long academicYearId, String serviceName, String channel);	
	/*To get the students list with the particular query we pass*/
	
	List<Student> getStudentList (StringBuffer studentQuery);
	/*To get the staff list with the particular query we pass*/
	
	List<Staff> getStaffList (StringBuffer staffQuery);
	int getAllottedSmsCount(long custId,long academicYearId);
	void sendNotificationToAndroidMobileUsers(long custId,String textMessage);
	int getTotalSmsCount(long custId,long academicYearId);
	
	Set<String> addMobileNumberBasedOnSettings(String mobileType,String primaryMobNumber,String secondaryMobNumber);
	
	String getContactFromEmail(Customer customer);
	
	Set<String> addMobileNumbersBasedOnAddressType(String mobileType,String mobileNumber1,String mobileNumber2,String anotherMobNum1,String anotherMobNum2,String addressType);
	StudentPayment entryStudentCommittedPayment(Customer customer,Student student,double pendingAmount,long createdUserId,String ipAddress);
	long getInvoiceNumberByCustId(String table,long custId,long academicYearId);
	
	long inserMessagesBySql(Messages messages);
	void insertMessageDetailsTracking(Set<MessageDetailsTracking> messageDetailsTrackings,long messageId,long userId);
	Object saveSmsObject(Object o);
	int updateQuery(String query);
	void scoreCardGeneratonNotification(String stuAndParentId, long classSectionId,String fullName,long examTypeId,String examType,long userAcademicYearId,long userCustId,long accountId);
	void sendNotificationToAndroidMobileUsersByUserIds(long custId,String textMessage,String userIds);
	boolean sendSMSForStudent(User user,Set<String> mobileNumbersSet,Messages message,String organizationName,SMSServiceProviders smsServiceProviders);
	boolean getResouceBundleURLConfiguraionPropertiesFileDetails(File file,long userCustId);
	void sendStaffNotificationWhenEditOrAdd(long staffId,String description,String title);
	boolean IsSecondaryUserAccountExists(String secondaryUsername);
	User getUserBySecondaryUserName(String secondaryUsername);
	User usernameAvailabulity(String username,long custId);
	 Map<String,String> sendForgotPasswordByUserName(User luser,String password);
	 File loadImageFromURL(String fileName);
	 SMSServiceProviders getSMSServiceProviderByCustId(long custId);
	 Customer getMasterCustomerById();
	 int sendSMS(SMSVO smsVO) ;
	 AcademicYear getFeatureAcademicYear(long custId , long currentAcademicYearId);
}
