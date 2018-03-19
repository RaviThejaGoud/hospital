package com.urt.persistence.interfaces.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;


/**
 * Data Access Object (DAO) interface. 
 *
 * @author <a href="mailto:sreeram@uroomtech.com">Sreeramulu J</a>
 * 
 * Modifications and studyClass by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * This thing used to be named simply 'GenericDao' in versions of appfuse prior to 2.0.
 * It was renamed in an attempt to distinguish and describe it as something 
 * different than GenericDao.  GenericDao is intended for subclassing, and was
 * named Generic because 1) it has very general functionality and 2) is 
 * 'generic' in the Java 5 sense of the word... aka... it uses Generics.
 * 
 * Implementations of this class are not intended for subclassing. You most
 * likely would want to subclass GenericDao.  The only real difference is that 
 * instances of java.lang.Class are passed into the methods in this class, and 
 * they are part of the constructor in the GenericDao, hence you'll have to do 
 * some casting if you use this one.
 * 
 * @see com.urt.sis.dao.interfaces.user.GenericDao
 */
//@Transactional
public interface UniversalDao {

    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @param clazz the type of objects (a.k.a. while table) to get data from
     * @return List of populated objects
     */
	@Transactional
    List getAll(Class clazz)  throws DataAccessException;

    /**
     * Generic method to get an object based on class and identifier. An 
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param clazz model class to lookup
     * @param id the identifier (primary key) of the class
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    Object get(Class clazz, Serializable id) throws DataAccessException;
    Object get(Class clazz,String clause) throws DataAccessException;
    
    Object get(Class clazz, Serializable id, String columnName) throws DataAccessException;
    /**
     * Generic method to save an object - handles both update and insert.
     * @param o the object to save
     * @return a populated object
     */
    //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    @Transactional
    Object saveObject(Object o) throws DataAccessException;
    
   // @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    @Transactional 
    Object saveOrUpdateObject(Object object);
	

    /**
     * Generic method to delete an object based on class and id
     * @param clazz model class to lookup
     * @param id the identifier (primary key) of the class
     */
    void remove(Class clazz, Serializable id) throws DataAccessException;
    
   // @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class )
    List getAll(Class clazz,  String clause) throws DataAccessException;

    int remove(String clazz, String clause) throws DataAccessException;
    int getCount(String clazz, String clause) throws DataAccessException;
    
   // @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class )
    List getAll(String sqlQuery) throws DataAccessException;
    Object[] get(String sqlQuery) throws DataAccessException;
    
  //  @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class )
    List getAllHqlQuery(String hqlQuery) throws DataAccessException;
    int getCountForGroupByClause(String clazz, String clause)  throws DataAccessException;
    /*List getHolidaysListByMonthIdForAttendance(int monthId,long orgId,String holidayYear,Date date,long customerId,long classId, String staffOrStudent);*/
    User getUserEmailByUserName(String email);
    AcademicYear getCurrentAcademicYear(String status,long custId);
    Role getRoleByName(String rolename);
    
    /**
     * Gets users information based on login name.
     * @param username the user's username
     * @return User populated User object
     */
    User getUserByUserName(String username);
    CommonType getCommonType(long custId,String type,String mediumId);
    
    CastSettings getCastNames(String castName,long custId);
    SubCastSettings getSubCast(long custId,String subCastName,long castId);
	List getAllByCustId(String clazz,long custId,long academicYearId);
	List<Student> getStudentList (StringBuffer studentQuery);
	List<Staff> getStaffList (StringBuffer staffQuery);
	int getAllottedSmsCount(long custId,long academicYearId);
	int getTotalSmsCount(long custId,long academicYearId);
	long inserMessagesBySql(Messages messages);
	void insertMessageDetailsTracking(Set<MessageDetailsTracking> messageDetailsTrackings,long messageId,long userId);
	
	long getInvoiceNumberByCustId(String table,long custId,long academicYearId);
	void updateStudentFeePaidStatus(long studentId,String status);
	 @Transactional
	 Object saveSmsObject(Object o) throws DataAccessException;
	 int updateQuery(String query);
	 @Transactional
	 Object mergeObject(Object o) throws DataAccessException;
	 User getUserBySecondaryUserName(String secondaryUsername);
	 @Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 void removeAllAndroidMobileUsers(String notRegisteredKey);
	 @Transactional
	 void persist(Object o) throws DataAccessException;
	 
	 int getSum(String clause) throws DataAccessException;
	 AcademicYear getFeatureAcademicYear(long custId , long currentAcademicYearId);
}