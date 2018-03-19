package com.urt.service.manager.impl.base;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyniva.sms.ws.vo.ClassTeacherVO;
import com.hyniva.sms.ws.vo.ReplyScrapMessageVO;
import com.hyniva.sms.ws.vo.ScrapMessageVO;
import com.hyniva.sms.ws.vo.ViewStaffPersonAccountDetailsVO;
import com.hyniva.sms.ws.vo.common.SMSVO;
import com.spring.context.SpringContextAware;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.AndroidMobileUsers;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.MessageDetailsTracking;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.MessagesGuidDetails;
import com.urt.persistence.model.common.ReplyScrapMessage;
import com.urt.persistence.model.common.ScrapMessage;
import com.urt.persistence.model.common.ServiceRequestHistory;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.SubCastSettings;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.study.ClassTeacher;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.StudentPayment;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewClassFeeDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.jrexception.JRExceptionClient;

@Component
//@Transactional
public class UniversalManagerImpl implements UniversalManager {
    /**
     * Log instance for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * UniversalDao instance, ready to charge forward and persist to the database
     */
    @Autowired
    protected UniversalDao dao;
    
    @Autowired
    protected MobileSMSDataTaskExecutor mobileSMSDataTaskExecutor;

	/**
     * {@inheritDoc}
     */
    //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    @Transactional
    public Object get(Class clazz, Serializable id) {
        return dao.get(clazz, id);
    }
    
    //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    @Transactional
    public Object get(Class clazz, Serializable id, String columnName) {
        return dao.get(clazz, id, columnName);
    }
    @Transactional
    public Object get(Class clazz, String clause) {
        return dao.get(clazz, clause);
    }

    /**
     * {@inheritDoc}
     */
    //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    @Transactional
    public List getAll(Class clazz) {
        return dao.getAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Class clazz, Serializable id) {
    	dao.remove(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    @Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    public Object save(Object o) {
    	//log.debug("Hello");
        return dao.saveObject(o);
    }
    
    public void persist(Object o) {
        dao.persist(o);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    public Object saveOrUpdateObject(Object o) {
		return dao.saveOrUpdateObject(o);
	}
    @Transactional
    public Object merge(Object o) {
		return dao.mergeObject(o);
	}
	/**
	 * @return the dao
	 */
	public UniversalDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(UniversalDao dao) {
		this.dao = dao;
	}
    
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED, rollbackFor = RuntimeException.class )
	@Transactional
	public List getAll(Class clazz,  String clause){
		return dao.getAll(clazz,clause);
	}
	
	
	@Transactional
	public int updateQuery(String query)
	{
		return dao.updateQuery(query);
	}
	
	public int remove(String clazz, String clause){
		return dao.remove(clazz,clause);
	}
	////@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public int getCount(String clazz, String clause){
		return dao.getCount(clazz,clause);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List getAll(String sqlQuery){
		return dao.getAll(sqlQuery);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public Object[] get(String sqlQuery){
		return dao.get(sqlQuery);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	@Transactional
	public List getAllHqlQuery(String hqlQuery){
		return dao.getAllHqlQuery(hqlQuery);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public int getCountForGroupByClause(String clazz, String clause){
		return dao.getCountForGroupByClause(clazz,clause);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Customer getCustomerByCustId(Serializable custId) {
		  Object obj = dao.get(Customer.class,custId);
		  if (ObjectFunctions.isNullOrEmpty(obj))
			  return null;
		  else
			  return (Customer) obj;
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public User getUserEmailByUserName(String email){
		return dao.getUserEmailByUserName(email);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public AcademicYear getCurrentAcademicYear(String status, long custId) {
		return dao.getCurrentAcademicYear(status, custId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Role getRoleByName(String rolename) {
        return dao.getRoleByName(rolename);
	}
	
	public Map<String, Integer> getMonthwiseSchoolWorkingDays(long custId,
			long academicYearId, Date startDate, Date closeDate,boolean isMthWiseWrkDaysUptoCurrentDate, String option, String classSectionId) 
	{
		AcademicYear academicYear =(AcademicYear) this.get(AcademicYear.class, academicYearId);
		if (ObjectFunctions.isNullOrEmpty(startDate)&& ObjectFunctions.isNullOrEmpty(closeDate)) {
			if (!ObjectFunctions.isNullOrEmpty(academicYear))
			{	
				if(!ObjectFunctions.isNullOrEmpty(academicYear.getStartDate())&& !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
					startDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear.getStartDate().toString());
					closeDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear.getEndDate().toString());
				}
			}
		}
		
		if (!ObjectFunctions.isNullOrEmpty(startDate)&& !ObjectFunctions.isNullOrEmpty(closeDate)) {
			Map<String, Integer> monthwiseWorkingDays = new LinkedHashMap<String, Integer>();
			int startMonth = DateFunctions.getMonthOfDate(startDate);
			int closeMonth = DateFunctions.getMonthOfDate(closeDate);
			int monthTotalDays = 0;
			int monthId;
			int yearId;
			List<SchoolHolidays> holidays = null;
			Calendar cal = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("MMMM"); 
			int currentMonth = DateFunctions.getMonthOfDate(new Date());
			int currentYear = DateFunctions.getDayOfYear(new Date());
			cal.setTime(startDate);
			for (Calendar openDate = cal; DateFunctions.compare2Dates(closeDate, openDate.getTime());) {
				monthId = DateFunctions.getMonthOfDate(openDate.getTime());
				yearId = DateFunctions.getDayOfYear(openDate.getTime());
				if(monthId <= currentMonth && yearId == currentYear  || monthId > currentMonth && yearId != currentYear)
				{
					if (monthId == startMonth) {
						if (isMthWiseWrkDaysUptoCurrentDate) {
							if (monthId == currentMonth && yearId == currentYear) {
								monthTotalDays = DateFunctions.getDayOfMonth(new Date()) - DateFunctions.getDayOfMonth(startDate) + 1;
							} else {
								monthTotalDays = cal.getActualMaximum(cal.DAY_OF_MONTH)- DateFunctions.getDayOfMonth(startDate) + 1;
							}
						} else
							monthTotalDays = cal.getActualMaximum(cal.DAY_OF_MONTH)- DateFunctions.getDayOfMonth(startDate) + 1;
					} else if (monthId == closeMonth) {
						if (isMthWiseWrkDaysUptoCurrentDate) {
							if (monthId == currentMonth && yearId == currentYear) {
								if (DateFunctions.compare2Dates(closeDate,new Date()))
									monthTotalDays = DateFunctions.getDayOfMonth(new Date());
								else
									monthTotalDays = DateFunctions.getDayOfMonth(closeDate);
							} else
								monthTotalDays = DateFunctions.getDayOfMonth(closeDate);
						} else
							monthTotalDays = DateFunctions.getDayOfMonth(closeDate);
					} else if (monthId == currentMonth && yearId == currentYear) {
						if (isMthWiseWrkDaysUptoCurrentDate) {
							monthTotalDays = DateFunctions.getDayOfMonth(new Date());
						} else
							monthTotalDays = cal.getActualMaximum(cal.DAY_OF_MONTH);
					} else
						monthTotalDays = cal.getActualMaximum(cal.DAY_OF_MONTH);
					Object[] classNameClassIds=null;
					if("CH".equalsIgnoreCase(academicYear.getHolidayStatus()) && !StringFunctions.isNullOrEmpty(classSectionId)){
			 			classNameClassIds= this.get("select classId,className from vw_classSectionDetails where custId="+custId+ " and academicYearId="+academicYear.getId()+" and classSectionId="+Long.valueOf(classSectionId));
					}
					if (monthId == currentMonth && yearId == currentYear) {
						if (DateFunctions.compare2Dates(closeDate, new Date())){
				 			if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
				 				holidays=this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()),null,null,classNameClassIds[0].toString(),null,null,monthId,"holidayBetweenDates",null);
				 			}else{
				 				holidays=this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,new Date()),null,null,null,null,null,monthId,"holidayBetweenDates",null);
					 		}
						}else{
							if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
								holidays=this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate),null,null,classNameClassIds[0].toString(),null,null,monthId,"holidayBetweenDates",null);
				 			}else{
				 				holidays=this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate),null,null,null,null,null,monthId,"holidayBetweenDates",null);
					 		}
						}
					}else{
				 			if(!ObjectFunctions.isNullOrEmpty(classNameClassIds) && !ObjectFunctions.isNullOrEmpty(classNameClassIds[0]) ){
				 				holidays=this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate),null,null,classNameClassIds[0].toString(),null,null,monthId,"holidayBetweenDates",null);
				 			}
				 		 else{
				 				holidays=this.getSchoolHolidaysListByDatesAndCustId(custId,academicYearId,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,closeDate),null,null,null,null,null,monthId,"holidayBetweenDates",null);
				 		}
					}
					if (!ObjectFunctions.isNullOrEmpty(holidays)) {
						monthTotalDays -= holidays.size();
					}
					/*
					 * log.debug("Month Name : "+df.format(openDate.getTime(
					 * ))); log.debug("No of Days: "+monthTotalDays);
					 */
					if ("Attendance Summary Class Wise(Monthly)".equalsIgnoreCase(option)|| "Attendance Summary Class Wise(Fully)".equalsIgnoreCase(option)) {
						
						monthwiseWorkingDays.put(df.format(openDate.getTime()).toUpperCase(),0);
					} else {
						monthwiseWorkingDays.put(df.format(openDate.getTime()).toUpperCase(),monthTotalDays);
					}
				}
				openDate.add(Calendar.MONTH, 1);
				openDate.set(Calendar.DATE, 1);
			}
			return monthwiseWorkingDays;
		}
		return null;
	}
    public Map<String,List> getSchoolAcademicYearWorkingDays(long academicYearId)
    {
		List<Object[]> academicYears= this.getAll("select startDate,endDate from academicYear where id="+academicYearId);
	  	if(!ObjectFunctions.isNullOrEmpty(academicYears)){
	  		Date startDate=null;
	  		Date closeDate = null;
	  		for(Object[] academicYear : academicYears){
	  			if(!ObjectFunctions.isNullOrEmpty(academicYear[0]) && !ObjectFunctions.isNullOrEmpty(academicYear[1])){
	  				startDate= DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, academicYear[0].toString());
			  		closeDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear[1].toString());
	  			}
	  		}
	  		if(!ObjectFunctions.isNullOrEmpty(startDate) && !ObjectFunctions.isNullOrEmpty(closeDate)){
	  			Map<String ,List> academicYearWorkingDays = new LinkedHashMap<String, List>();
	  			/*int startMonth=DateFunctions.getMonthOfDate(startDate);
	  			int closeMonth=DateFunctions.getMonthOfDate(closeDate);
	  			int monthTotalDays = 0;*/
	  			List<String> holidays=null;
	  			int monthId=0;
	  			int chgMonthId=0;
	  			Calendar cal = Calendar.getInstance();
	  			DateFormat df = new SimpleDateFormat("MMM");
	  			String dateStr=null;
				cal.setTime(startDate);
				List<Date> workingDays = null;
	  			for(Calendar openDate=cal;DateFunctions.compare2Dates(closeDate,openDate.getTime());){
	  				dateStr = new SimpleDateFormat("yyyy-MM-dd").format(openDate.getTime());
	  				chgMonthId = DateFunctions.getMonthOfDate(openDate.getTime());
	  				if(monthId != chgMonthId){
	  					holidays = this.getAll("select holidayDate from schoolHolidays where monthId="+chgMonthId+" and academicYearId="+academicYearId);
	  					workingDays = new ArrayList<Date>();
	  					monthId = chgMonthId;
	  				}
	  				workingDays.add(openDate.getTime());
	  				if(ObjectFunctions.isNotNullOrEmpty(holidays)){
	  					for(String holiday: holidays){
	  						if(StringFunctions.isNotNullOrEmpty(holiday)){
	  							if(holiday.equalsIgnoreCase(dateStr)){
	  								workingDays.remove(openDate.getTime());
	  								break;
	  							}
	  						}	
	  					}
	  				}
	  				academicYearWorkingDays.put(df.format(openDate.getTime()),workingDays);
					openDate.add(Calendar.DATE, 1);
	  			}
	  			return academicYearWorkingDays;
				}
	  		}
	  	return null;
	  }
    
    /**
     * @Comment : JSR -- need to check getUser. hostel and transport conditions
     * @param custId
     * @param academicYearId
     * @param classIds
     * @param accountIds
     * @return
     */
    public Set GetStudentMobileNumbers(long custId, long academicYearId, List<String> classIds,List<String> accountIds, String hostlerStatus,StringBuffer studentQuery,String mobileType) {
		
		try {
			Set<String> mobileNumberset = new HashSet<String>();
			List<Object[]> studentsList = null;
			StringBuffer queryString = null;
			String	mobileNumbers = null;
			String	mobileNumber = null;
			if (ObjectFunctions.isNotNullOrEmpty(classIds)) {
				String commaDelimitedString = StringUtil.convertListToString(classIds);
				/*studyClassList = this.getAll(StudyClass.class," id in (" + commaDelimitedString + ")");
				if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) {
					for (StudyClass studyClass : studyClassList) {*/
						queryString = new StringBuffer();
						queryString.append("select");
						if("B".equalsIgnoreCase(mobileType)){
							queryString.append(" mobileNumber,secondaryMobileNumber,anotherMobileNumber,anotherSecondaryMobileNumber");
						}else if("P".equalsIgnoreCase(mobileType)){
							queryString.append(" mobileNumber,anotherMobileNumber");
						}else
							queryString.append(" secondaryMobileNumber,anotherSecondaryMobileNumber");
						
						queryString.append(" ,addressType from vw_studentClassDetails where classSectionId in ("+ commaDelimitedString+ " ) and academicYearId="+ academicYearId+ " and custId="+ custId+ " and status in ('Y','S','B') and (mobileNumber!=0 AND mobileNumber is not null AND mobileNumber !='+91-0000000000' )");
						//studentsQuery.append("select s.custId as custId,s.academicYearId as academicYear,s.accountId as account from Student s LEFT JOIN Account a on (a.id = s.account and  a.custId=s.custId)")
						studentQuery.append("FROM Student s WHERE s.custId="+custId+" and s.academicYear="+academicYearId+" and s.account IN (SELECT a.id FROM User a WHERE a.custId="+custId+" and a.person in (select p.id from Person p where (p.mobileNumber!=0 AND p.mobileNumber is not null AND p.mobileNumber !='+91-0000000000')))")
									//.append(" LEFT JOIN Person p on(p.id = a.account) where s.custId=").append(custId)
									//.append(" LEFT JOIN Person p on(p.id = a.personId) where s.custId=").append(custId)
									//.append(" and s.academicYearId=").append(academicYearId)
									.append(" and s.classSection in ( ").append(commaDelimitedString).append(" )")
									//.append(" ) and (p.mobileNumber!=0 AND p.mobileNumber is not null AND p.mobileNumber !='+91-0000000000') ")									
									.append(" and s.description is null");	
							if(StringFunctions.isNotNullOrEmpty(hostlerStatus)){
							queryString.append(" and hostelMode='"+hostlerStatus+"'");
							studentQuery.append(" and s.hostelMode='"+hostlerStatus+"'");
						}
						/*	if (getUser().isSchoolHostel() || getUser().isHostelFinance()) {
							queryString.append(" and bedId != 0");
						} else if (getUser().isSchoolTransport() || getUser().isTransportFinance()) {
							queryString.append(" and transportMode='"+ Constants.TRANSPORT_STATUS + "' ");
						}*/
						studentsList = this.getAll(queryString.toString());
						if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
							for(Object[] obj : studentsList){
								if("B".equalsIgnoreCase(mobileType)){	
									if("R".equalsIgnoreCase(obj[4].toString())){
										if(!ObjectFunctions.isNullOrEmpty(obj[1]))
											mobileNumbers = obj[1].toString();
										if(!ObjectFunctions.isNullOrEmpty(obj[0]))
											mobileNumber = obj[0].toString();
									}else{
										if(!ObjectFunctions.isNullOrEmpty(obj[3]))
											mobileNumbers = obj[3].toString();
										if(!ObjectFunctions.isNullOrEmpty(obj[2]))
											mobileNumber = obj[2].toString();
									}
									mobileNumberset.addAll(this.addMobileNumberBasedOnSettings(mobileType,mobileNumber,mobileNumbers));
									mobileNumber = null;
									mobileNumbers = null;
								}else{
									if("R".equalsIgnoreCase(obj[2].toString())){
										if(!ObjectFunctions.isNullOrEmpty(obj[0]))
											mobileNumberset.add(obj[0].toString());
									}else{
										if(!ObjectFunctions.isNullOrEmpty(obj[1]))
											mobileNumberset.add(obj[1].toString());
									}
								}
							}
							studentsList = null;
						}
					}
			if (ObjectFunctions.isNotNullOrEmpty(accountIds)) {
				String commaDelimitedString = StringUtil.convertListToString(accountIds);
				queryString = new StringBuffer();
				queryString.append("select");
				if("B".equalsIgnoreCase(mobileType)){
					queryString.append(" mobileNumber,secondaryMobileNumber,anotherMobileNumber,anotherSecondaryMobileNumber");
				}else if("P".equalsIgnoreCase(mobileType)){
					queryString.append(" mobileNumber,anotherMobileNumber");
				}else
					queryString.append(" secondaryMobileNumber,anotherSecondaryMobileNumber");
				
				queryString.append(" ,addressType from vw_studentClassDetails where academicYearId="+ academicYearId+ " and custId="+ custId + " and status in ('Y','S','B') and (mobileNumber!=0 AND mobileNumber is not null AND mobileNumber !='+91-0000000000') and accountId in ("+ commaDelimitedString + ")");
				//studentsQuery.append("select s.custId as custId,s.academicYearId as academicYear,s.accountId as account from Student s LEFT JOIN Account a on (a.id = s.account and  a.custId=s.custId)")
							//.append(" LEFT JOIN Person p on(p.id = a.person) where s.custId=").append(custId)
				studentQuery.append("FROM Student s WHERE s.custId="+custId+" and s.academicYear="+academicYearId+" and s.account IN (SELECT u.id FROM User u WHERE u.custId="+custId+" and u.person in (select p.id from Person p where (p.mobileNumber!=0 AND p.mobileNumber is not null AND p.mobileNumber !='+91-0000000000')) and u.id in ("+commaDelimitedString+"))")
							//.append(" and s.academicYearId=").append(academicYearId)
							//.append(" and a.id in (").append(commaDelimitedString)
							//.append(" ) and (p.mobileNumber!=0 AND p.mobileNumber is not null AND p.mobileNumber !='+91-0000000000')")
							.append(" and s.description is null");	
				if(StringFunctions.isNotNullOrEmpty(hostlerStatus)){
					queryString.append(" and hostelMode='"+hostlerStatus+"'");
					studentQuery.append(" and s.hostelMode='"+hostlerStatus+"'");
				}
				/*if (getUser().isSchoolHostel() || getUser().isHostelFinance()) {
					queryString.append(" and bedId != 0");
				} else if (getUser().isSchoolTransport() || getUser().isTransportFinance()) {
					queryString.append(" and transportMode='"+ Constants.TRANSPORT_STATUS + "' ");
				}*/
				studentsList = this.getAll(queryString.toString());
				if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
					for(Object[] obj : studentsList){
						if("B".equalsIgnoreCase(mobileType)){	
							if("R".equalsIgnoreCase(obj[4].toString())){
								if(!ObjectFunctions.isNullOrEmpty(obj[1]))
									mobileNumbers = obj[1].toString();
								if(!ObjectFunctions.isNullOrEmpty(obj[0]))
									mobileNumber = obj[0].toString();
							}else{
								if(!ObjectFunctions.isNullOrEmpty(obj[3]))
									mobileNumbers = obj[3].toString();
								if(!ObjectFunctions.isNullOrEmpty(obj[2]))
									mobileNumber = obj[2].toString();
							}
							mobileNumberset.addAll(this.addMobileNumberBasedOnSettings(mobileType,mobileNumber,mobileNumbers));
							mobileNumber = null;
							mobileNumbers = null;
						}else{
							if(!ObjectFunctions.isNullOrEmpty(obj[2]) && "R".equalsIgnoreCase(obj[2].toString())){
								if(!ObjectFunctions.isNullOrEmpty(obj[0]))
									mobileNumberset.add(obj[0].toString());
							}else{
								if(!ObjectFunctions.isNullOrEmpty(obj[1]))
									mobileNumberset.add(obj[1].toString());
							}
						}	
					}
					studentsList = null;
				}
			}
			return mobileNumberset;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
    
    public Map<String, State> getCountryStates(int countryCode)
	{
		Map<String, State> statelist = new HashMap<String, State>();
		List<State> statesList = this.getAll(State.class);
		if (!ObjectFunctions.isNullOrEmpty(statesList)) {
			Collections.sort(statesList);
			for (State states : statesList) {
				statelist.put(states.getStateName(), states);
			}
		}
		return statelist;
	}
    
    /*public List<SchoolHolidays> getHolidaysListByMonthIdForAttendance(int monthId,long orgId,String holidayYear,Date date,long customerId,long classId, String staffOrStudent)
	{
		return dao.getHolidaysListByMonthIdForAttendance(monthId, orgId, holidayYear, date,customerId, classId, staffOrStudent);
	}*/
    
    //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    public User getUserByUserName(String username) {
        return (User) dao.getUserByUserName(username);
    }

	public boolean IsUserAccountExists(String precode, String firstName, String initial)
	{
		String userName = StringFunctions.prepareUserName(precode, firstName, initial);
		try
		{
			if(ObjectFunctions.isNullOrEmpty(this.getUserByUserName(userName)))
			{
				return false;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return false;
		}
		return true;
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public CommonType getCommonType(long custId,String type,String mediumId){
		 return dao.getCommonType(custId,type,mediumId);
	 }
	 
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public CastSettings getCastNames(String castName,long custId){
			return dao.getCastNames(castName, custId);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public SubCastSettings getSubCast(long custId,String subCastName,long castId){
			return dao.getSubCast(custId, subCastName, castId);
	}	 
	 
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<Student> getStudentList (StringBuffer studentQuery){
		 return dao.getStudentList (studentQuery);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<Staff> getStaffList (StringBuffer staffQuery){
		 return dao.getStaffList (staffQuery);
	 }
	 
	public void writeToFile(String content, String path){
		 try{
			 POIFSFileSystem fs = new POIFSFileSystem();
			 DirectoryEntry directory = fs.getRoot();
		 	 FileOutputStream out = null;
		 	 directory.createDocument("WordDocument", new ByteArrayInputStream(content.getBytes()));
			 out = new FileOutputStream(path);
			 fs.writeFilesystem(out);
			 out.close();
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllByCustId(String clazz, long custId,long academicYearId) {
		return dao.getAllByCustId(clazz, custId,academicYearId);
	}
	 
	/*public List getAllByCustId(String clazz,long custId){
		return dao.getAllByCustId(clazz,custId);
	}*/
	
	/** JSR TO-DO: Refactor this code
	 * @Description : This is mandatory to call after student added to the class and section
	 * @param classId
	 * @param classSectionId
	 */
	public void updateClassAndSectionCapacity(long custId, long academicYearId, long classId, long classSectionId) {
		try {
			
			List<StudyClass> studyClassList=null;
			// Update Section Capacity
			if (classSectionId > 0) {
				StudyClass sc = (StudyClass) this.get(StudyClass.class,classSectionId);
				if (!ObjectFunctions.isNullOrEmpty(sc))
				{
					StringBuffer queryBuff=new StringBuffer();
					queryBuff.append(" classSectionId=");
					queryBuff.append(classSectionId);	
					queryBuff.append(" and status='");
					queryBuff.append(Constants.YES_STRING);
					queryBuff.append("' and custId=");
					queryBuff.append(custId);
					queryBuff.append(" and description is null");
					sc.setFilledSeats(this.getCount("student", queryBuff.toString()));
					int classStrength = sc.getFilledSeats();
					int remainingSeats = 0;
					remainingSeats = sc.getSectionCapacity() - classStrength;
					if (remainingSeats <= 0) {
						sc.setSectionCapacity(sc.getSectionCapacity()+1);
					}
					queryBuff = null;
					this.save(sc);
					sc = null;
				}
			}
			// Update all sections capacity
			else if (classId > 0) {
				if (academicYearId > 0) {
					studyClassList = this.getAll(StudyClass.class, " classNameClassId = "+ classId +" and custId="+custId+" and academicYearId="+academicYearId);
				}
			}
			// Do it for the entire customer
			else if (academicYearId > 0) {
					studyClassList = this.getAllByCustId("StudyClass", custId,academicYearId);
			}
			if (!ObjectFunctions.isNullOrEmpty(studyClassList)) {
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append(" status='");
				queryBuff.append(Constants.YES_STRING);
				queryBuff.append("' and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and description is null ");
				queryBuff.append(" and classSectionId=");
				//queryBuff.append(classId);
				
				for (StudyClass studyClass : studyClassList) {
					if (!ObjectFunctions.isNullOrEmpty(studyClass)) {
						studyClass.setFilledSeats(this.getCount("student", queryBuff.toString()+studyClass.getId()));
						this.save(studyClass);
						studyClass = null;
					}
				}
				queryBuff=null;
				studyClassList = null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	/*Changed by seshu on 10th Aug 2013. Messages sent with out loggin*/
	@Transactional
    public boolean deliverSms(Messages message, Set<String> mobileNumbers,SMSServiceProviders smsprovider) {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'deliverSms' method");
		}
		Customer customer = null;
		try {
		    Set<String> mobileNumbersSet = new HashSet<String>();
		    StringBuffer invalidMobileNos = new StringBuffer();
		    customer = message.getCustomer();
		    if (!ObjectFunctions.isNullOrEmpty(mobileNumbers) && !StringFunctions.isNullOrEmpty(message.getMessageDescription())) {
			String mNumber = null;
			/*Object[] smsProvider = this.get("select id,smsServiceProviderId from customer where id="+custId);
	    	Object[] smsProviderName = this.get("select serviceProvider,id from smsServiceProviders where id="+smsProvider[1]);*/
	    	/*if (ObjectFunctions.isNullOrEmpty(message.getCustomer())) {
			    customer = (Customer) this.get(Customer.class, custId);
			    message.setCustomer(customer);
			    customer = null;
			}else
				customer = message.getCustomer();*/
			for (String mbNumber : mobileNumbers) {
			    if (StringFunctions.isNotNullOrEmpty(mbNumber)) {
			    	 if (!ObjectFunctions.isNullOrEmpty(smsprovider)){
			    		 if("MsgClub".equalsIgnoreCase(smsprovider.getServiceProvider()) || "mobicomm".equalsIgnoreCase(smsprovider.getServiceProvider())){
				    		if(mbNumber.length()==10)
				    			mNumber = mbNumber;
				    		else if(mbNumber.length()==14){
				    			String mNumers1[] = mbNumber.split("-");
				    			mNumber = mNumers1[1];
				    		}
					    }
			    		 else if("ValueFirst".equalsIgnoreCase(smsprovider.getServiceProvider()))
						    	mNumber = StringFunctions.getFormattedMobileNumber(mbNumber);
						    else if("InternationalValueFirst".equalsIgnoreCase(smsprovider.getServiceProvider())){
						    	Country country = (Country)this.get(Country.class, customer.getAddress().getCountryId());
						    	mNumber = StringFunctions.getFormattedMobileNumberOtherCountry(mbNumber,country.getPhoneCode());
						    	country = null;
						    }
				    	if(StringFunctions.isNullOrEmpty(mNumber)){
				    		invalidMobileNos.append(mbNumber);
				    		invalidMobileNos.append(",");
				    	}else
				    		mobileNumbersSet.add(mNumber);	 
			    	 }
			    }
			}
			log.debug("After Calling Deviler SMS.....mobileNumberset---"+mobileNumbers+"-------messageDesc-----"+message.getMessageDescription());
			mobileNumbersSet.remove(null);
			message.setSentSms(mobileNumbersSet.size());
			message.setMobileNumbers(StringUtil.convertSetToString(mobileNumbersSet));
			message.setMessageType("SMS");
			message.setInvalidMobileNos(invalidMobileNos.toString());
			double count = 0.0,total =160.0,charcount=0.0;
			 String input = message.getMessageDescription();
			 count = input.length()/total;
			 charcount = Math.ceil(count);
			 message.setMessagesCount((int)charcount * mobileNumbersSet.size());
			 //  int s = Long.valueOf(n);
			/*if(ObjectFunctions.isNullOrEmpty(message.getAcademicYear())){
				message.setAcademicYear(this.getCurrentAcademicYear("Y", custId));
			}*/
			//SMSServiceProviders smsprovider = (SMSServiceProviders) this.get(SMSServiceProviders.class,"id="+Long.valueOf(smsProvider[1].toString()));
			message.setSmsProviders(smsprovider);
			String xmlRespPath = SpringContextAware.getRealPath("userFiles/" + message.getCustomer().getCustomerShortName().trim() + "//" + "smsxmlresp/");
			if(StringFunctions.isNotNullOrEmpty(xmlRespPath)){
				message.setSmsResXmlLocation(xmlRespPath);
			}
			
			message.setGuid(mobileSMSDataTaskExecutor.prepareSendSMS(message));
			if("mobicomm".equalsIgnoreCase(smsprovider.getServiceProvider())){
				MessagesGuidDetails messagesGuidDetails = new MessagesGuidDetails();
	             messagesGuidDetails.setTrackingDetails(message.getGuid());
	             messagesGuidDetails = (MessagesGuidDetails) this.saveSmsObject(messagesGuidDetails);
	             message.setGuid(String.valueOf(messagesGuidDetails.getId()));
	             messagesGuidDetails = null;
			}
			/*SendSms sendSms = new SendSms();
			message.setGuid(sendSms.prepareSendSMS(message));*/
			
			/*long messageId = this.inserMessagesBySql(message);
			log.debug("message id "+messageId);
			if(!ObjectFunctions.isNullOrEmpty(message.getMessageDetailsTracking())){
				insertMessageDetailsTracking(message.getMessageDetailsTracking(),messageId,message.getCreatedById());
				for(MessageDetailsTracking detailsTracking : message.getMessageDetailsTracking()){
					detailsTracking.setMessageId(messageId);
					this.save(detailsTracking);
					detailsTracking=null;
				}
			}*/
			message = (Messages) this.saveSmsObject(message);
			if (StringFunctions.isNullOrEmpty(message.getGuid()))
			    return false;
			else
			    return true;
			//smsProvider = null;
	    	//smsProviderName = null;
		    }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			customer=null;
		}
		return false;
    }
    public List<StudyClass> getStudyClassesForClassTeacherAndAdmin(User user,long academicYearId){
    	List<StudyClass> studyClasses=null;
    	try{
    		if(user.isOnlySchoolAdmin() || user.isSchoolPrincipal()  || user.isSchoolHostel() || user.isSchoolClerk() || user.isSchoolAsstStaff() || user.isSchoolDirector()){
    			studyClasses = this.getAll(StudyClass.class, "academicYearId="+academicYearId);
    		}else if(user.isOnlySchoolTeacher() || user.isOnlySchoolHod() || user.isAdminCoordinator()){
    			StringBuffer query = new StringBuffer("select cteacher.studyClass FROM ClassTeacher cteacher WHERE cteacher.staff.account=").append(user.getId())
    		  	.append(" and cteacher.academicYear=").append(academicYearId).append(" and cteacher.staff.status='Y' and cteacher.classTeacher='Y' group by cteacher.studyClass");
    			studyClasses = this.getAllHqlQuery(query.toString());
    		}
    		if(ObjectFunctions.isNotNullOrEmpty(studyClasses)){
    			Collections.sort(studyClasses);
    		}
    	}catch (Exception ex) {
    		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
    	return studyClasses;
    }
    public Map<String,String> sendForgotPasswordByUserName(User luser){
		 Map<String, String> msg = new HashMap<String, String>();
		try{
			Customer customer=(Customer) this.get(Customer.class, luser.getCustId());
			Student student = null;
			Staff staff = null;
			User account = null;			
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				AcademicYear academicYear=(AcademicYear) this.getCurrentAcademicYear(Constants.YES_STRING,customer.getId());
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					if(!ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentMobile()) && !StringFunctions.isNullOrEmpty(luser.getPerson().getStudentEmail()) || !ObjectFunctions.isNullOrEmpty(luser.getPerson().getParentEmail()) 
							|| !ObjectFunctions.isNullOrEmpty(luser.getPrimaryAddress()) && !StringFunctions.isNullOrEmpty(luser.getPrimaryAddress().getEmail()) || 
							!ObjectFunctions.isNullOrEmpty(luser.getPerson()) && !StringFunctions.isNullOrEmpty(luser.getPerson().getMobileNumber())){
						String newPassword = StringUtil.generateRandomString();
						
						/*this method move the code signupAction to UniversalManagerImpl.java */
						/*Boolean encrypt = (Boolean) getConfiguration().get(Constants.ENCRYPT_PASSWORD);
						String algorithm = (String) getConfiguration().get(Constants.ENC_ALGORITHM);
						if (encrypt != null && encrypt) {
							algorithm = (String) getConfiguration().get(Constants.ENC_ALGORITHM);
							if (algorithm == null) { // should only happen for test case
								log.debug("assuming testcase, setting algorithm to 'SHA'");
								algorithm = "SHA";
							}
						}*/
						luser.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
						luser.setConfirmPassword(newPassword);
						luser.setPasswordStatus(true);
						luser.setLastUpdatedDate(new Date());
						luser.setLastAccessDate(new Date());
						this.save(luser);
					 /*
					 * When email is not available to user we need to send that
					 * password to mobile numbe if mobile number available
					 */
					if(!ObjectFunctions.isNullOrEmpty(luser.getPrimaryAddress()) && 
							!StringFunctions.isNullOrEmpty(luser.getPrimaryAddress().getEmail())){
						String[] emailAddresses = new String[1];
						emailAddresses[0] = luser.getPrimaryAddress().getEmail();
						MailUtil mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
						mailUtil.setContactEmail(customer.getContactEmail());
						mailUtil.setContactPasword(customer.getContactPassword());
						mailUtil.sendMailForPwdChange(luser.getConfirmPassword(),luser.getUsername(),this.getContactFromEmail(customer));
						mailUtil=null;
						emailAddresses=null;
						//super.addActionMessage("Password has been sent to your email.Please update your password after login.");
						msg.put("1","Password has been sent to your email.Please update your password after login.");
					}
					else if(luser.isSchoolStudent() && !ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentEmail())){
							String[] emailAddresses = new String[1];
							emailAddresses[0] = luser.getPerson().getStudentEmail();
							MailUtil mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
							mailUtil.setContactEmail(customer.getContactEmail());
							mailUtil.setContactPasword(customer.getContactPassword());
							mailUtil.sendMailForPwdChange(luser.getConfirmPassword(),luser.getUsername(),this.getContactFromEmail(customer));
							msg.put("1","Password has been sent to your email.Please update your password after login.");
							mailUtil=null;
							emailAddresses=null;
					}
					else if(luser.isParent() && !ObjectFunctions.isNullOrEmpty(luser.getPrimaryAddress().getEmail())){
						String[] emailAddresses = new String[1];
						emailAddresses[0] = luser.getPrimaryAddress().getEmail();
						MailUtil mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
						mailUtil.setContactEmail(customer.getContactEmail());
						mailUtil.setContactPasword(customer.getContactPassword());
						mailUtil.sendMailForPwdChange(luser.getConfirmPassword(),luser.getUsername(),this.getContactFromEmail(customer));
						msg.put("1","Password has been sent to your email.Please update your password after login.");
						mailUtil=null;
						emailAddresses=null;
				   }
					else {
							Messages messages = new Messages();
							StringBuffer msgContent = new StringBuffer();
							if(luser.isSchoolStudent() && ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentMobile()) && ObjectFunctions.isNullOrEmpty(luser.getPerson().getParentEmail())){
								msgContent.append("Dear Parent, Your child "+luser.getPerson().getFullPersonName()+" password for Eazyschool account has been reset to '");
							}else{
								msgContent.append("Your password for Eazyschool portal login is '");
							}
							msgContent.append(newPassword);
							msgContent.append("' Thank you from ");
							messages.setCustomer(customer);
							messages.setSmsSenderId(customer.getSender());
							messages.setMessageDescription(msgContent.toString());
							messages.setStatus("A");// here change the FPWD to A because is send to automatic the message 
							messages.setSenderName("System");
							messages.setAcademicYear(academicYear);
							messages.setPurposeType("Regd: ForGot Password");
							Set<String> mobileNumber=new HashSet<String>();
							if(luser.isSchoolStudent() || luser.isParent()){
								if(luser.isSchoolStudent()){
									if (StringFunctions.isNotNullOrEmpty(luser.getPerson().getStudentMobile())){
										mobileNumber.add(luser.getPerson().getStudentMobile());
										student = (Student) this.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+ luser.getId());
										messages = this.saveMessageDetailsTracking(messages,student,null,null);	
										if(this.deliverSms(messages,mobileNumber,smsServiceProvider))
											msg.put("2","Password has been sent to your mobile number.Please update your password after login.");
										else
											msg.put("3","Password has not sent to your mobile number due to some technical reasons. Please contact Administrator.");
									}else{
										if(!ObjectFunctions.isNullOrEmpty(luser.getPerson().getParentEmail())){
											String[] emailAddresses = new String[1];
											emailAddresses[0] = luser.getPerson().getParentEmail();
											MailUtil mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
											mailUtil.setContactEmail(customer.getContactEmail());
											mailUtil.setContactPasword(customer.getContactPassword());
											mailUtil.sendMailForStudPwdChange(luser.getConfirmPassword(),luser,this.getContactFromEmail(customer));
											msg.put("1","Password has been sent to your parent email.Please update your password after login.");
											mailUtil=null;
											emailAddresses=null;
										}else{
											if (StringFunctions.isNotNullOrEmpty(luser.getPerson().getMobileNumber())){
												mobileNumber.add(luser.getPerson().getMobileNumber());
												student = (Student) this.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+ luser.getId());
												messages = this.saveMessageDetailsTracking(messages,student,null,null);	
											if(this.deliverSms(messages,mobileNumber,smsServiceProvider))
												msg.put("4","Password has been sent to your parent mobile number.Please update your password after login.");
											else
												msg.put("3","Password has not sent to your mobile number due to some technical reasons. Please contact Administrator.");
											}
										}	
									}
								}else{
									if (StringFunctions.isNotNullOrEmpty(luser.getPerson().getMobileNumber())){
										mobileNumber.add(luser.getPerson().getMobileNumber());										
										account = (User) this.get(User.class, "custId="+customer.getId()+" and id="+ luser.getId());
										messages = this.saveMessageDetailsTracking(messages,null,null,account);	
										if(this.deliverSms(messages,mobileNumber,smsServiceProvider))
											msg.put("2","Password has been sent to your mobile number.Please update your password after login.");
										else
											msg.put("3","Password has not sent to your mobile number due to some technical reasons. Please contact Administrator.");
									}
									if(!ObjectFunctions.isNullOrEmpty(luser.getPerson().getParentEmail())){
										String[] emailAddresses = new String[1];
										emailAddresses[0] = luser.getPerson().getParentEmail();
										MailUtil mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
										mailUtil.setContactEmail(customer.getContactEmail());
										mailUtil.setContactPasword(customer.getContactPassword());
										mailUtil.sendMailForPwdChange(luser.getConfirmPassword(),luser.getUsername(),this.getContactFromEmail(customer));
										msg.put("1","Password has been sent to your email.Please update your password after login.");
										mailUtil=null;
										emailAddresses=null;
								   }
								}
							}									
							else{
								if (StringFunctions.isNotNullOrEmpty(luser.getPerson().getMobileNumber())){
									mobileNumber.add(luser.getPerson().getMobileNumber());
									staff = (Staff) this.get(Staff.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+ luser.getId());
									messages = this.saveMessageDetailsTracking(messages,null,staff,null);	
									if(this.deliverSms(messages,mobileNumber,smsServiceProvider))
										msg.put("2","Password has been sent to your mobile number.Please update your password after login.");
									else
										msg.put("3","Password has not sent to your mobile number due to some technical reasons. Please contact Administrator.");
								}	
							}
							mobileNumber = null;
							messages = null;
							msgContent = null;
					   
					}
				  }else {
					  msg.put("5","We do not find any communication details(Mobile or Email) for sending password. Please contact Administrator.");
				  }
				}
				academicYear = null;
			}
			 
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return msg;
	}
    
    public SchoolHolidays getHolidayByCustIdAndAcademicYearId(long custId, long academicYearId, String holidayDate, long classId, String description, String status, String tempString){
	if (log.isDebugEnabled()) {
	log.debug("Entering  AdminManager 'getHolidayByCustIdAndAcademicYearId' method");
	}
	SchoolHolidays schoolHolidays=null;
	try {
		StringBuffer queryBuff=new StringBuffer();
		queryBuff.append(" custId=");
		queryBuff.append(custId);	
		if(academicYearId > 0) {
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
		}
		if(classId > 0) {
			queryBuff.append(" and classId=");
			queryBuff.append(classId);
		}
		if(!StringFunctions.isNullOrEmpty(holidayDate) && "holidayDateLike".equalsIgnoreCase(tempString))
			queryBuff.append(" and holidayDate like '"+ holidayDate+ "%'");
		
		if(!StringFunctions.isNullOrEmpty(holidayDate) && "holidayDateEqual".equalsIgnoreCase(tempString))
			queryBuff.append(" and holidayDate ='"+ holidayDate+ "'");
		
		if("AlertsToMobile".equalsIgnoreCase(tempString)){
			Date afterOneDay = DateFunctions.add(new Date(), 1);
			queryBuff.append(" and startDate='"+new SimpleDateFormat("yyyy-MM-dd").format(afterOneDay)+"' and (status='H' or status='W') group by description,startDate,endDate");
		}
		log.debug(queryBuff.toString());
		//queryBuff.append(" order by holidayDate ASC LIMIT 1");
		schoolHolidays = (SchoolHolidays)this.get(SchoolHolidays.class,queryBuff.toString());
		queryBuff=null;
	  }catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return schoolHolidays;
	}
    
	public List<SchoolHolidays> getSchoolHolidaysListByDatesAndCustId(long custId, long academicYearId, String holidayDate, String holidayStartDate, String holidayEndDate, String classId, String description, String status, int monthId, String anyTitle, String yearId)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering  AdminManager 'getSchoolHolidaysListByDatesAndCustId' method");
		}
		List<SchoolHolidays> schoolHolidaysList =null;
		try {
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("custId=");
			queryBuff.append(custId);	
			if(academicYearId > 0) {
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
			}
			if(!StringFunctions.isNullOrEmpty(classId)){
				queryBuff.append(" and classId in (" + classId + ")");
			}
			
			if(!StringFunctions.isNullOrEmpty(yearId)){
				queryBuff.append(" and yearId=");
				queryBuff.append(yearId);
			}
			
			if(monthId > 0){
				queryBuff.append(" and monthId=");
				queryBuff.append(monthId);
			}
			
			if(!StringFunctions.isNullOrEmpty(holidayStartDate) && !StringFunctions.isNullOrEmpty(holidayEndDate))
				queryBuff.append(" and holidayDate between '"+holidayStartDate+"' and '"+holidayEndDate+"' ");
			
			if(!StringFunctions.isNullOrEmpty(holidayDate) && "holidayBetweenDates".equalsIgnoreCase(anyTitle))
				queryBuff.append(" and holidayDate <= '"+holidayDate+"'");
			
			if(!StringFunctions.isNullOrEmpty(holidayDate) && "holidayDateEqual".equalsIgnoreCase(anyTitle))
				queryBuff.append(" and date(holidayDate) = date('"+holidayDate+"')");
			
			if(!StringFunctions.isNullOrEmpty(holidayDate) && "featureHolidayDate".equalsIgnoreCase(anyTitle))
				queryBuff.append(" and endDate >= '"+holidayDate+"'");
			
			if(!StringFunctions.isNullOrEmpty(status)){
				queryBuff.append(" and status='"+ status+ "'");
				if( "sessionHolidays".equalsIgnoreCase(anyTitle)){
					if(status.equalsIgnoreCase("H"))
					 	 queryBuff.append(" group by description,startDate,endDate"); 
				}
			  }
			
			 if("groupByDesc".equalsIgnoreCase(anyTitle))
					queryBuff.append(" group by classId,description order by id");
			 
			if("OrderByDate".equalsIgnoreCase(anyTitle))
				queryBuff.append(" order by holidayDate");
			
			log.debug(queryBuff.toString());
			schoolHolidaysList = this.getAll(SchoolHolidays.class, queryBuff.toString());
			queryBuff=null;
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
 		return schoolHolidaysList;
	}
	public Map<String,String> UpdateReplyNewScrapMessage(long custId,AcademicYear academicYear,long scrapSenderAccountId, long scrapReceiverAccountId, String scrapDescription, long scrapMessageId,String scrapDate,long userId) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'UpdateReplyNewScrapMessage' method");
		}
		Map<String, String> msg = new HashMap<String, String>();
		try {					
			ScrapMessage scrapMessage = null;
			if(!ObjectFunctions.isNullOrEmpty(scrapMessageId))
			{
				User senderObj=null;
				User receiverObj=null;
				scrapMessage = (ScrapMessage)this.get(ScrapMessage.class, Long.valueOf(scrapMessageId));
				if(!ObjectFunctions.isNullOrEmpty(scrapMessage)){
					scrapMessage.setReplyStatus(Constants.YES_STRING);
					scrapMessage.setIsNewMessage("UR");
					scrapMessage.setIsNewReply("RM");
					scrapMessage.setScrapDescription(scrapDescription);
					ReplyScrapMessage replyScrapMessage = new ReplyScrapMessage();
					replyScrapMessage.setTitle(scrapMessage.getTitle());
					replyScrapMessage.setScrapDescription(scrapDescription);
					replyScrapMessage.setMessageType(scrapMessage.getMessageType());
					if(Long.valueOf(scrapSenderAccountId) == userId)
					{
						senderObj=(User)this.get(User.class, userId);
						receiverObj=(User)this.get(User.class, Long.valueOf(scrapReceiverAccountId));
						if(!ObjectFunctions.isNullOrEmpty(senderObj)){
							replyScrapMessage.setSenderAccount(senderObj);
						}else{
							msg.put("4", "Invalid user details.");
						}
						if(!ObjectFunctions.isNullOrEmpty(receiverObj)){
							replyScrapMessage.setReceiverAccount(receiverObj);
						}else{
							msg.put("4", "Invalid user details.");
						}
					}
					else
					{
						//Here change the code to reduced the database hit 
						senderObj=(User)this.get(User.class, Long.valueOf(scrapSenderAccountId));
						receiverObj=(User)this.get(User.class, Long.valueOf(scrapReceiverAccountId));
						if(!ObjectFunctions.isNullOrEmpty(senderObj)){
							replyScrapMessage.setReceiverAccount(senderObj);
							scrapMessage.setReceiverAccount(senderObj);
						}else{
							msg.put("4", "Invalid user details.");
						}
						if(!ObjectFunctions.isNullOrEmpty(receiverObj)){
							replyScrapMessage.setSenderAccount(receiverObj);
							scrapMessage.setSenderAccount(receiverObj);
						}else{
							msg.put("4", "Invalid user details.");
						}
						//scrapMessage = (ScrapMessage)this.get(ScrapMessage.class, Long.valueOf(scrapMessageId));
						//this.save(scrapMessage);
					}
					if (StringFunctions.isNullOrEmpty(msg.get("4"))){
						replyScrapMessage.setAcademicYear(academicYear);
						replyScrapMessage.setStatus(Constants.ACTIVE_STATUS);
						replyScrapMessage.setCreatedById(userId);
						//replyScrapMessage.setCreatedDate(scrapDate); //scrapDate used feature in sms-app
						replyScrapMessage.setCreatedDate(new Date());
						replyScrapMessage.setLastAccessDate(new Date());
						replyScrapMessage.setLastUpdatedDate(new Date());
						replyScrapMessage.setCustId(custId);
						replyScrapMessage.setLastUpdatedById(userId);
						//scrapMessage = (ScrapMessage)this.get(ScrapMessage.class, Long.valueOf(scrapMessageId));
						scrapMessage.addReplayScrapMessage(replyScrapMessage);
						scrapMessage = (ScrapMessage)this.save(scrapMessage);
						
						List<ScrapMessageVO> scrapMessageVOs = new ArrayList<ScrapMessageVO>();
						scrapMessageVOs.add(scrapMessage.copyFromEntityToVo(scrapMessage));
						
						List<ReplyScrapMessageVO> replyScrapMessageVOs = new ArrayList<ReplyScrapMessageVO>();
						
						replyScrapMessage = scrapMessage.getReplayScrap().get(scrapMessage.getReplayScrap().size() - 1);
						
						ReplyScrapMessageVO  replyScrapMessageVo = replyScrapMessage.copyFromEntityToVo(replyScrapMessage);
						replyScrapMessageVo.setScrapMesssageId(scrapMessage.getId());
						replyScrapMessageVOs.add(replyScrapMessageVo);
						
						//replyScrapMessageVOs.add(replyScrapMessage.copyFromEntityToVo(replyScrapMessage));
						
						JSONObject main = new JSONObject();
						JSONObject subVal = new JSONObject();
						main.put("notificationFor", "Messages");
						subVal.put("title", senderObj.getFullPersonName()+" sent a message");
						subVal.put("description", senderObj.getFullPersonName()+" sent a message");
						//subVal.put("scrapMessageVOs",scrapMessageVOs);
						subVal.put("scrapMessageVOs",new JSONArray(new Gson().toJson(scrapMessageVOs)));
						//subVal.put("replyScrapMessageVOs",scrapMessageVOs);
						subVal.put("replyScrapMessageVOs",new JSONArray(new Gson().toJson(replyScrapMessageVOs)));
						main.put("information", subVal);
						log.debug(main.toString());
						this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),"("+scrapReceiverAccountId+",0)"); //To add notification for mobile app.
						
						msg.put("0", "Message posted Successfully.");
					}
					//super.addActionMessage("Message posted Successfully");
				}else{
					msg.put("5", "Invalid scrap message details.");
				}
			}
		} catch (Exception ex) {
			msg.put("1", "Message not sent.");
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return msg;
	}
	public ScrapMessage getScrapMessageById(long scrapMessageId){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getScrapMessageById' method");
		}
		//ScrapMessage newScrapMessage = null;
		ScrapMessage scrapMessageInfo = null;
		try {	
			 scrapMessageInfo = (ScrapMessage)this.get(ScrapMessage.class, Long.valueOf(scrapMessageId));
			 if(!ObjectFunctions.isNullOrEmpty(scrapMessageInfo)){
				if("UR".equalsIgnoreCase(scrapMessageInfo.getIsNewMessage()))
				{
					scrapMessageInfo.setIsNewMessage("R");
					scrapMessageInfo = (ScrapMessage)this.save(scrapMessageInfo);
					//if(!ObjectFunctions.isNullOrEmpty(newScrapMessage))
						//return newScrapMessage;
				}
			 }
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	 return scrapMessageInfo;
	}
	public Messages addMessageDetailsTracking (Messages message,List<Student> studentsList,List<Staff> staffList,Set<String> otherMobileNumbers){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addMessageDetailsTracking' method");
		}
		MessageDetailsTracking messageDetailsTracking =null;
		try{
			if(!ObjectFunctions.isNullOrEmpty(studentsList)){
		    	for(Student student : studentsList ){	    		
	    			if((!(student.getAccount().getPerson().getMobileNumber()).equalsIgnoreCase("0000000000")) && (!(student.getAccount().getPerson().getMobileNumber()).equalsIgnoreCase("+91-0000000000"))){
	    				messageDetailsTracking = this.entryMessageTracking(message,messageDetailsTracking,student,null,null,null);
						message.addMessageDetails(messageDetailsTracking);
						messageDetailsTracking = null;	
		    		}
		    	}
	    	}	    	
	    	if(!ObjectFunctions.isNullOrEmpty(staffList)){    		
	    		for(Staff staff : staffList ){   	
	    			if((!staff.getAccount().getPerson().getMobileNumber().equalsIgnoreCase("0000000000")) && (!staff.getAccount().getPerson().getMobileNumber().equalsIgnoreCase("+91-0000000000"))){
	    				messageDetailsTracking = this.entryMessageTracking(message,messageDetailsTracking,null,staff,null,null);
						message.addMessageDetails(messageDetailsTracking);
						messageDetailsTracking = null;
	    			}
	    		}
	    	} 
	    	if(!ObjectFunctions.isNullOrEmpty(otherMobileNumbers)){    		
	    		for(String mobileNumber : otherMobileNumbers ){   	
	    			if((!mobileNumber.equalsIgnoreCase("0000000000")) && (!mobileNumber.equalsIgnoreCase("+91-0000000000")) && (!StringFunctions.isNullOrEmpty(mobileNumber))){
	    				messageDetailsTracking = this.entryMessageTracking(message,messageDetailsTracking,null,null,mobileNumber,null);
						message.addMessageDetails(messageDetailsTracking);
						messageDetailsTracking = null;
	    			}
	    		}
	    	} 
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}	
    	return message;    
	}
	public Messages saveMessageDetailsTracking (Messages message,Student student,Staff staff,User user){ 
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addMessageDetailsTracking' method");
		}		
		MessageDetailsTracking messageDetailsTracking =null;
		try{
	    	if(!ObjectFunctions.isNullOrEmpty(student)){	 
			     messageDetailsTracking = this.entryMessageTracking(message, messageDetailsTracking, student,null,null,null);		   			
			     message.addMessageDetails(messageDetailsTracking);
			     messageDetailsTracking = null;				
	    	}	    	
	    	if(!ObjectFunctions.isNullOrEmpty(staff)){    
	    		 messageDetailsTracking = this.entryMessageTracking(message, messageDetailsTracking,null,staff,null,null);		   			
			     message.addMessageDetails(messageDetailsTracking);
			     messageDetailsTracking = null;				
	    	}  
	    	if(!ObjectFunctions.isNullOrEmpty(user)){ 
			     messageDetailsTracking = this.entryMessageTracking(message, messageDetailsTracking,null,null,null,user);							
					message.addMessageDetails(messageDetailsTracking);
					messageDetailsTracking = null; 			
	    	}  
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}	
    	return message;
    }
	
	public void saveServiceRequesByCustId(long custId, long academicYearId, String serviceName, String channel) {
		if (log.isDebugEnabled()) {
			log.debug("Entering  'saveServiceRequesByCustId' method");
		}
		try {
			ServiceRequestHistory serviceRequestHistory = new ServiceRequestHistory();
			serviceRequestHistory.setCustId(custId);
			serviceRequestHistory.setChannel(channel);
			serviceRequestHistory.setServiceName(serviceName);
			serviceRequestHistory.setAcademicYearId(academicYearId);
			serviceRequestHistory.setCreatedDate(new Date());
			this.save(serviceRequestHistory);
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	public MessageDetailsTracking entryMessageTracking(Messages message,MessageDetailsTracking messageDetailsTracking,Student student,Staff staff,String mobileNumber,User user){
		try {
			messageDetailsTracking = new MessageDetailsTracking();
			String mbNumber = null;
			messageDetailsTracking.setCreatedById(message.getCreatedById());
			messageDetailsTracking.setCreatedDate(new Date());
			messageDetailsTracking.setLastAccessDate(new Date());
			messageDetailsTracking.setLastUpdatedDate(new Date());
			if(!ObjectFunctions.isNullOrEmpty(student)){
				messageDetailsTracking.setCustId(student.getCustId());
				messageDetailsTracking.setAcademicYearId(student.getAcademicYearId());
				messageDetailsTracking.setAccount(student.getAccount());
				mbNumber =  StringFunctions.getFormattedMobileNumber(student.getAccount().getPerson().getMobileNumber());
				if(ObjectFunctions.isNullOrEmpty(mbNumber)){
					messageDetailsTracking.setDeliveryStatus('I');
					messageDetailsTracking.setMobileNumber(student.getAccount().getPerson().getMobileNumber());
				}else{
					messageDetailsTracking.setDeliveryStatus('N');
					messageDetailsTracking.setMobileNumber(mbNumber);
				}
			}else if (!ObjectFunctions.isNullOrEmpty(staff)) {
				messageDetailsTracking.setCustId(staff.getCustId());
    			messageDetailsTracking.setAcademicYearId(staff.getAcademicYear().getId());
    			messageDetailsTracking.setAccount(staff.getAccount());
    			mbNumber =  StringFunctions.getFormattedMobileNumber(staff.getAccount().getPerson().getMobileNumber());
				if(ObjectFunctions.isNullOrEmpty(mbNumber)){
					messageDetailsTracking.setDeliveryStatus('I');
					messageDetailsTracking.setMobileNumber(staff.getAccount().getPerson().getMobileNumber());
				}else{
					messageDetailsTracking.setDeliveryStatus('N');
					messageDetailsTracking.setMobileNumber(mbNumber);
				}
			}else if (!StringFunctions.isNullOrEmpty(mobileNumber)) {
				messageDetailsTracking.setCustId(message.getCustomer().getId());
    			messageDetailsTracking.setAcademicYearId(message.getAcademicYear().getId());
    			mbNumber =  StringFunctions.getFormattedMobileNumber(mobileNumber);
				if(ObjectFunctions.isNullOrEmpty(mbNumber)){
					messageDetailsTracking.setDeliveryStatus('I');
					messageDetailsTracking.setMobileNumber(mobileNumber);
				}else{
					messageDetailsTracking.setDeliveryStatus('N');
					messageDetailsTracking.setMobileNumber(mbNumber);
				}
			}else if(!ObjectFunctions.isNullOrEmpty(user)){
				messageDetailsTracking.setCustId(user.getCustId());
				if(!ObjectFunctions.isNullOrEmpty(message.getAcademicYear()))		 
				  messageDetailsTracking.setAcademicYearId(message.getAcademicYear().getId());			 
				messageDetailsTracking.setAccount(user); 
				mbNumber =  StringFunctions.getFormattedMobileNumber(user.getPerson().getMobileNumber());
				if(ObjectFunctions.isNullOrEmpty(mbNumber)){
					messageDetailsTracking.setDeliveryStatus('I');
					messageDetailsTracking.setMobileNumber(user.getPerson().getMobileNumber());
				}else{
					messageDetailsTracking.setDeliveryStatus('N');
					messageDetailsTracking.setMobileNumber(mbNumber);
				}
			}				
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return messageDetailsTracking;
	}	
	public int getAllottedSmsCount(long custId,long academicYearId){
		return dao.getAllottedSmsCount(custId,academicYearId);
	}
	public void sendNotificationToAndroidMobileUsers(long custId,String textMessage){
        try {
        	if(StringFunctions.isNotNullOrEmpty(textMessage) && custId >0){
        		 MulticastResult multicastResult =null;
        		//Please add here your project API key: "Key for browser apps (with referers)".
                //If you added "API key Key for server apps (with IP locking)" or "Key for Android apps (with certificates)" here
                //then you may get error responses.
            	ArrayList<String> devicesList =null;
             //   Sender sender = new  Sender("AIzaSyBCQPlzyPRPVX8ZQIHa-K3l6lYoyNMKD6k");

                // use this to send message with payload data
                Message message = new Message.Builder()
                .collapseKey("Eazy School") // The key for all updates.
                .timeToLive(2000000)// Time in seconds to keep message queued if device offline.
                .delayWhileIdle(true)
                .addData("message", textMessage) //you can get this message on client side app
                .build(); 
               // Use this code to send notification message to a single device
               // com.google.android.gcm.server.Result result = sender.send(message,"APA91bEHRZOurAtFUvJDLyxRNIKLh4b6Y915aeCYmY-yJaCoI9V13bIZOp4NvJG4DO4ebWflM3Ky74E7d-FePTHuplwVGrk3GY_7FQjvkUQj2qkm-_mSE-9ClHB4r_bQCUkqLHlbJ4VRqciGMdgtWcPRg_GMx7x5cA",1);
               // log.debug("Message Result: "+result.toString()); //Print message result on console
                log.debug("CustID::"+custId);
                List<AndroidMobileUsers> androidUserList=this.getAll(AndroidMobileUsers.class,"custId="+custId);
                log.debug("Android size::"+androidUserList.size());
                devicesList = new ArrayList<String>();
                for(Object obj:androidUserList){
                	AndroidMobileUsers androidUser=(AndroidMobileUsers)obj;
                	if(!ObjectFunctions.isNullOrEmpty(androidUser)){
                		//Use this code to send notification message to multiple devices
                		//add your devices RegisterationID, one for each device     
                		devicesList.add(androidUser.getRegistrationKey());
                	}androidUser=null;
                }androidUserList=null;
                log.debug("Devices size::"+devicesList.size());
               if(devicesList.size() >0){
            	   sendFCMNotifications(message, devicesList, devicesList.size());
            	 //Use this code for multicast messages   
                  // multicastResult = sender.send(message, devicesList, devicesList.size());
                  //log.debug("Message Result: "+multicastResult.toString());//Print multicast message result on console
               }
                multicastResult=null;
                devicesList=null;
                message=null;
               // sender=null;
        	}
        } catch (Exception e) {
        	e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
        }
    }
	public int getTotalSmsCount(long custId,long academicYearId){
		return dao.getTotalSmsCount(custId,academicYearId);
	}
	public Set<String> addMobileNumberBasedOnSettings(String mobileType,String primaryMobNumber,String secondaryMobNumber){
		Set<String> mobileNumbersSet = new HashSet<String>();
		try {
			if(!ObjectFunctions.isNullOrEmpty(primaryMobNumber))
				mobileNumbersSet.add(primaryMobNumber);
			if(!ObjectFunctions.isNullOrEmpty(secondaryMobNumber))
				mobileNumbersSet.add(secondaryMobNumber);
		 } catch (Exception e) {
			e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
		return mobileNumbersSet;
	}
	public String getContactFromEmail(Customer customer){
		 if(StringFunctions.isNullOrEmpty(customer.getContactEmail()) && StringFunctions.isNullOrEmpty(customer.getContactPassword())){
				return "messages@eazyschool.com";
			}else {
				return  customer.getContactEmail();
			}
    }
	@Transactional
	public Set<String> addMobileNumbersBasedOnAddressType(String mobileType,String mobileNumber1,String mobileNumber2,String anotherMobNum1,String anotherMobNum2,String addressType){
		Set<String> mobileNumberset = null;
		try {
			 mobileNumberset = new HashSet<String>();
			if("B".equalsIgnoreCase(mobileType)){
				if("R".equalsIgnoreCase(addressType))
					mobileNumberset.addAll(this.addMobileNumberBasedOnSettings(addressType,mobileNumber1,mobileNumber2));
				else
					mobileNumberset.addAll(this.addMobileNumberBasedOnSettings(addressType,anotherMobNum1,anotherMobNum2));
			}else if("P".equalsIgnoreCase(mobileType)){
				if("R".equalsIgnoreCase(addressType))
					mobileNumberset.add(mobileNumber1);
				else
					mobileNumberset.add(anotherMobNum1);
			}else{
				if("R".equalsIgnoreCase(addressType))
					mobileNumberset.add(mobileNumber2);
				else
					mobileNumberset.add(anotherMobNum2);
			}	
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return mobileNumberset;
	}
	
	public StudentPayment entryStudentCommittedPayment(Customer customer,Student student,double pendingAmount,long createdUserId,String ipAddress){
		StudentPayment payment = null;
		if(pendingAmount !=0){
			long receiptNumber=0;
			String paymentStatus="P";
			double paidAmount =0;
			boolean processContinue =true;
			double allowedCommittedFee=pendingAmount;
			List<ViewClassFeeDetails> commitedClassFee = this.getAll(ViewClassFeeDetails.class, "custId="+customer.getId()+" and classSectionId="+student.getClassSection().getId()+" and categoryId="+student.getCategoryId()+" and committedFeeStatus='Y' order by priorityPosition,dueDate");
			if(!ObjectFunctions.isNullOrEmpty(commitedClassFee)){

				if(customer.isAcademicWiseFeeReceipt())
					receiptNumber = this.getInvoiceNumberByCustId("studentPayment", customer.getId(),student.getAcademicYearId()); 
				else
					receiptNumber = this.getInvoiceNumberByCustId("studentPayment", customer.getId(),0);
				if (receiptNumber == 0 && customer.getStartInvoiceNumber() != 0) {
					receiptNumber = customer.getStartInvoiceNumber();
				}else{
					receiptNumber = receiptNumber+1;
				}
				payment =  new StudentPayment();
				payment.setAcademicYear(student.getAcademicYear());
				payment.setCreatedById(createdUserId);
				payment.setCustId(customer.getId());
				payment.setInvoiceNumber(receiptNumber);
				payment.setIpAddress(ipAddress);
				payment.setLastUpdatedById(createdUserId);
				payment.setPaymentDate(new Date());
				payment.setPaymentType("CH");
				//payment.setDiscountDesc("");
				payment.setStudent(student);
				payment.setPaidAmount(pendingAmount);
				for(ViewClassFeeDetails feeDetails : commitedClassFee){
					if(processContinue == false)
						continue;
					Fee fee = (Fee)this.get(Fee.class,feeDetails.getFeeId());
					StudentFeePaidDetails studentFee = new StudentFeePaidDetails();
					studentFee.setCreatedById(createdUserId);
					studentFee.setCustId(customer.getId());
					studentFee.setFee(fee);
					studentFee.setLastUpdatedById(createdUserId);
					studentFee.setStudentId(student.getId());
					studentFee.setDeleteStatus(Constants.YES_STRING);
					
					if(feeDetails.getFeeAmount() >= allowedCommittedFee && allowedCommittedFee != 0){
						paidAmount= allowedCommittedFee;
						if(feeDetails.getFeeAmount() == paidAmount)
							paymentStatus = "P";
						else
							paymentStatus = "N";
					processContinue=false;
                    allowedCommittedFee =0;
					}else{
						paidAmount= feeDetails.getFeeAmount();
					}
					studentFee.setPaymentStatus(paymentStatus);
					studentFee.setPaymentAmount(paidAmount);
					studentFee.setCommittedFeeStatus(Constants.YES_STRING);
					payment.addStudentFeeDetails(studentFee);
					allowedCommittedFee = (allowedCommittedFee-feeDetails.getFeeAmount());
					feeDetails = null;
					fee = null;
				}
				commitedClassFee=null;
			}
			receiptNumber=0;
			payment = (StudentPayment)this.saveOrUpdateObject(payment);
			dao.updateStudentFeePaidStatus(student.getId(),"C");
			//this.checkStudentFeePaidStatus(student.getAcademicYearId(),customer.getId(),student);
		}
		return payment;
	}
	
	@Transactional
	public long getInvoiceNumberByCustId(String table,long custId,long academicyearId){
		return dao.getInvoiceNumberByCustId(table,custId,academicyearId);
	}
	@Transactional
	public long inserMessagesBySql(Messages messages){
		return dao.inserMessagesBySql(messages);
	}
	public void insertMessageDetailsTracking(Set<MessageDetailsTracking> messageDetailsTrackings,long messageId,long userId){
		dao.insertMessageDetailsTracking(messageDetailsTrackings,messageId,userId);
	}
	@Transactional
	public Object saveSmsObject(Object o) {
        return dao.saveSmsObject(o);
    }
	public void scoreCardGeneratonNotification(String stuAndParentId, long classSectionId,String fullName,long examTypeId,String examType,long userAcademicYearId,long userCustId,long accountI){
        try {
        	//log.debug(stuAndParentId+","+ classSectionId+","+ fullName+","+ examTypeId+","+ examType+","+ userAcademicYearId+","+ userCustId+","+accountI);
        	if(StringFunctions.isNotNullOrEmpty(stuAndParentId.toString()) && classSectionId >0){
        		//MulticastResult multicastResult =null;
            	ArrayList<String> devicesList =null;
                Sender sender = new  Sender("AIzaSyBCQPlzyPRPVX8ZQIHa-K3l6lYoyNMKD6k");
                JSONObject main = new JSONObject();
    			JSONObject subVal = new JSONObject();
    			main.put("notificationFor", "ScoreCardDownload");
    			subVal.put("title", "Score Card Generated");
    			subVal.put("description", "Your children "+fullName+" score card for "+examType+" is generated, click to download.");
    			subVal.put("custId", userCustId);
    			subVal.put("academicYearId", userAcademicYearId);
    			subVal.put("examTypeId", examTypeId);
    			subVal.put("studyClassId", classSectionId);
    			subVal.put("accountId", accountI);
    			main.put("information", subVal);
    			log.debug("Venkatehs:-"+main.toString());
                Message message = new Message.Builder()
                .collapseKey("Eazy School") // The key for all updates.
                .timeToLive(2000000)// Time in seconds to keep message queued if device offline.
                .delayWhileIdle(true)
                .addData("message", main.toString()) //you can get this message on client side app
                .build(); 
               List<AndroidMobileUsers> androidUserList=this.getAll(AndroidMobileUsers.class,"custId="+userCustId+" and accountId in("+stuAndParentId.toString()+") group by accountId");
               if(ObjectFunctions.isNotNullOrEmpty(androidUserList)){
                   devicesList = new ArrayList<String>();
                    for(Object obj:androidUserList){
                    	AndroidMobileUsers androidUser=(AndroidMobileUsers)obj;
                    	if(!ObjectFunctions.isNullOrEmpty(androidUser)){
                    		//Use this code to send notification message to multiple devices
                    		//add your devices RegisterationID, one for each device     
                    		devicesList.add(androidUser.getRegistrationKey());
                    	}androidUser=null;
                    }androidUserList=null;
                   if(devicesList.size() >0){
                	 //Use this code for multicast messages   
                	   sendFCMNotifications(message, devicesList, devicesList.size());
                      // multicastResult = sender.send(message, devicesList, devicesList.size());
                       //log.debug("Message Result: "+multicastResult.toString());//Print multicast message result on console
                   }
               }
              // multicastResult=null;
                devicesList=null;
                message=null;
                sender=null;
        	}
        } catch (Exception e) {
        	e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
        }
    }
	
	public void sendNotificationToAndroidMobileUsersByUserIds(long custId,String textMessage,String userIds){
        try {
        	if(StringFunctions.isNotNullOrEmpty(textMessage)){
        		log.info(textMessage+" :textMessage: "+userIds);
        		 MulticastResult multicastResult =null;
        		//Please add here your project API key: "Key for browser apps (with referers)".
                //If you added "API key Key for server apps (with IP locking)" or "Key for Android apps (with certificates)" here
                //then you may get error responses.
            	ArrayList<String> devicesList =null;
                //Sender sender = new  Sender("AIzaSyBCQPlzyPRPVX8ZQIHa-K3l6lYoyNMKD6k");

                // use this to send message with payload data
                Message message = new Message.Builder()
                .collapseKey("Eazy School") // The key for all updates.
                .timeToLive(2000000)// Time in seconds to keep message queued if device offline.
                .delayWhileIdle(true)
                .addData("message", textMessage) //you can get this message on client side app
                .build(); 
               // Use this code to send notification message to a single device
               // com.google.android.gcm.server.Result result = sender.send(message,"APA91bEHRZOurAtFUvJDLyxRNIKLh4b6Y915aeCYmY-yJaCoI9V13bIZOp4NvJG4DO4ebWflM3Ky74E7d-FePTHuplwVGrk3GY_7FQjvkUQj2qkm-_mSE-9ClHB4r_bQCUkqLHlbJ4VRqciGMdgtWcPRg_GMx7x5cA",1);
               // log.debug("Message Result: "+result.toString()); //Print message result on console
                List<AndroidMobileUsers> androidUserList;
                if(custId > 0)
                	androidUserList=this.getAll(AndroidMobileUsers.class,"custId="+custId + " and accountId in " + userIds);
                else
                	androidUserList=this.getAll(AndroidMobileUsers.class,"accountId in " + userIds);
                devicesList = new ArrayList<String>();
                for(Object obj:androidUserList){
                	AndroidMobileUsers androidUser=(AndroidMobileUsers)obj;
                	if(!ObjectFunctions.isNullOrEmpty(androidUser)){
                		//Use this code to send notification message to multiple devices
                		//add your devices RegisterationID, one for each device     
                		devicesList.add(androidUser.getRegistrationKey());
                	}androidUser=null;
                }androidUserList=null;
               if(devicesList.size() >0){
            	   sendFCMNotifications(message, devicesList, devicesList.size());
            	 //Use this code for multicast messages   
                  // multicastResult = sender.send(message, devicesList, devicesList.size());
                  // log.debug("Message Result: "+multicastResult.toString());//Print multicast message result on console
               }
                multicastResult=null;
                devicesList=null;
                message=null;
                //sender=null;
                
        	}
        } catch (Exception e) {
        	e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
        }
    }
	public boolean sendSMSForStudent(User user,Set<String> mobileNumbersSet,Messages message,String organizatioNname,SMSServiceProviders smsServiceProviders) throws URTUniversalException {
		boolean smsStatus = false;
		try
		{
			StringBuffer smsContent = new StringBuffer("Dear "+user.getPerson().getFirstName()+", Your "+organizatioNname+" account login details are below URL:www.eazyschool.in Username:"+user.getUsername().toLowerCase()+" Password:");
			smsContent.append(user.getUsername().toLowerCase()+" from ");
			if(!ObjectFunctions.isNullOrEmpty(smsContent)){
				message.setMessageDescription(smsContent.toString());
				smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProviders);
				smsContent = null;
				mobileNumbersSet = null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return smsStatus;
	}
	public boolean getResouceBundleURLConfiguraionPropertiesFileDetails(File file,long userCustId){
		try {
			Properties configProp = new Properties();
			InputStream in = new FileInputStream(file);
			configProp.load(in);
			String customerId = configProp.getProperty("s.credentiols.customerId");
			ArrayList<Long> arrayList = new ArrayList<Long>(Arrays.asList(customerId.split(",")).size());
			for (int i = 0; i < Arrays.asList(customerId.split(",")).size(); i++) {
				arrayList.add(Long.valueOf(customerId.split(",")[i].toString()));
			}
			boolean customerSpecific=arrayList.contains(userCustId);
			return customerSpecific;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return false; 
	}
	private void sendFCMNotifications(Message message, List<String> devicesList, int devicesListSize)
	{
		//FCM code to send Notifications  
	    FirebaseOptions options = null; 
	    log.debug("Entering FCM notificatin");
	    try {
	    	//API is moved from personal account to hr@hyniva.com account
	    	/*String fcmEazyschoolJsonPath = SpringContextAware.getRealPath("userFiles/fcm/EazySchool-0b679f155495.json");
	    	log.debug("Path::"+fcmEazyschoolJsonPath);
	        options = new FirebaseOptions.Builder()
	        .setServiceAccount(new FileInputStream(fcmEazyschoolJsonPath))
	            .setDatabaseUrl("https://hynivafirebase.firebaseio.com/")
	            .build();*/
	    	String fcmEazyschoolJsonPath = SpringContextAware.getRealPath("userFiles/fcm/hyniva-1363d-firebase-adminsdk-s8301-4e51830137.json");
	    	log.debug("Path::"+fcmEazyschoolJsonPath);
	        options = new FirebaseOptions.Builder().setServiceAccount(new FileInputStream(fcmEazyschoolJsonPath)).setDatabaseUrl("https://hyniva-1363d.firebaseio.com/")
	            .build();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    /*String fcmDefaultName = FirebaseApp.DEFAULT_APP_NAME;
	    if(fcmDefaultName == null && fcmDefaultName == "" )*/
	    if(ObjectFunctions.isNullOrEmpty(FirebaseApp.getApps()))
	    	FirebaseApp.initializeApp(options);
	    
	    log.debug("FCM App size:"+FirebaseApp.getApps().size());

	    // Obtain serverKey from Project Settings -> Cloud Messaging tab
	    // for "My Notification Server" project in Firebase Console.
	    //String serverKey = <get_server_key_from_firebase_console>;
	    //String serverKey = "AAAA3iFOQjg:APA91bG1S7SWxUZKm9MSIWvtxLBJlt6C-YyzrBXYxcc4V3lsF37Zwo7c0_dz7Cd6XMf9zu4w2neE0KEFvE4eAp0G1CLiVKmUVaktrO8CsW4v6sPCXMSvKGYlRaxYiRH4JxybKWfiNBlYQ6-CBRsAYywP1Ywv6DJrQg";
	    String serverKey = "AAAAyv6NfnM:APA91bEbKl78JWSXlRwXZ3YmpqGtuZXZ0qPZ_naZ4NiheAsD6Kko9L-GLGZ-_y1-8FI4Pp7GkD53VBjuBWksG62tlyPYozY3xt0F4LqPF3amHBeWXnQjWnWWjudpjJAcAWty42sPm-vx";
	            try {
	                Sender sender = new FCMSender(serverKey);
	                if(devicesListSize >0){
	                	log.debug("Entering FCM notificatin send");
	               	 //Use this code for multicast messages   
	                	MulticastResult multicastResult = sender.send(message, devicesList, devicesList.size());
	                      log.info("FCM Message Result: "+multicastResult.toString());//Print multicast message result on console
	                      String currentVal= multicastResult.toString();
	                      if(StringFunctions.isNotNullOrEmpty(currentVal)){
	                    	  String[] resultsVal =currentVal.toString().split("results:") ;
		          			  String resultValAfterSplit =resultsVal[1].toString().replace("[", "").replace("]", "");
		          			  String[] finalResultsVal=resultValAfterSplit.split(",");
			          		  int positionVal=0;
			          		 if(StringFunctions.isNotNullOrEmpty(finalResultsVal.toString())){
				          		  for(String finalVal: finalResultsVal){
				       				 if(StringFunctions.isNotNullOrEmpty(finalVal)){
				       					// log.debug("finalVal Print: "+finalVal);
				       					 if(finalVal.contains("errorCode=NotRegistered")){
				       						 //log.debug("I am here: "+positionVal);
				       						 String notRegisterKeyVal=devicesList.get(positionVal);
				       						 if(StringFunctions.isNotNullOrEmpty(notRegisterKeyVal)){
				       							//this.deleteNotRegisteredKeyVal(notRegisterKeyVal);
				       							List<AndroidMobileUsers> androidMobileUsersList = this.getAll(AndroidMobileUsers.class,"registrationKey ='"+notRegisterKeyVal.trim()+"'");
				       							if(ObjectFunctions.isNotNullOrEmpty(androidMobileUsersList)){
				       								this.removeAllAndroidMobileUsers(notRegisterKeyVal.trim());
				       								log.debug("Removed NotRegisteredKey: "+notRegisterKeyVal);
				       							}else{
				       								log.debug("Not available registeredKey: "+notRegisterKeyVal);
				       							}
				       						 }
				       					 }
				       					positionVal++;
				       				 }
				       			 }
			          		 }
	                      }
	                  }
	            } 
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	}
	
	public void sendStaffNotificationWhenEditOrAdd(long staffId,String description,String title)
	   {
		 StringBuffer accountIdbuffer =null;
		 ViewStaffPersonAccountDetailsVO staffPersonAccountDetailsVO = null;
		 List<ViewStaffPersonAccountDetailsVO> staffPersonAccountDetailsVOs = new ArrayList<ViewStaffPersonAccountDetailsVO>();
		 ViewStaffPersonAccountDetails staffObj = null;
		 try {
			 if(staffId>0){
				 staffObj = (ViewStaffPersonAccountDetails) this.get(ViewStaffPersonAccountDetails.class, "staffId="+staffId);
				// Staff Messages
				 List<String> toAccountIds = null;
				 if(!ObjectFunctions.isNullOrEmpty(staffObj)){
					 	toAccountIds = new ArrayList<String>();
					 	toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM androidMobileUsers WHERE custId = "+staffObj.getCustId());
						accountIdbuffer = new StringBuffer("(");
						accountIdbuffer.append(toAccountIds);
						accountIdbuffer.append(")");
					 	staffPersonAccountDetailsVO = new ViewStaffPersonAccountDetailsVO();
						staffPersonAccountDetailsVO.setStaffId(staffObj.getStaffId());
						staffPersonAccountDetailsVO.setAccountId(staffObj.getAccountId());
						staffPersonAccountDetailsVO.setStaffType(StringFunctions.isNullOrEmpty(staffObj.getStaffType())? "": staffObj.getStaffType());
						staffPersonAccountDetailsVO.setQualification1(StringFunctions.isNullOrEmpty(staffObj.getQualification1())? "": staffObj.getQualification1());
						staffPersonAccountDetailsVO.setQualification2(StringFunctions.isNullOrEmpty(staffObj.getQualification2())? "": staffObj.getQualification2());
						staffPersonAccountDetailsVO.setDateofJoining(ObjectFunctions.isNullOrEmpty(staffObj.getDateofJoining())? "": DateFunctions.convertDateToString(staffObj.getDateofJoining()));
						staffPersonAccountDetailsVO.setUsername(StringFunctions.isNullOrEmpty(staffObj.getUsername())? "": staffObj.getUsername());
						staffPersonAccountDetailsVO.setRoleName(staffObj.getRoleName());
						staffPersonAccountDetailsVO.setFirstName(staffObj.getFirstName());
						staffPersonAccountDetailsVO.setLastName(StringFunctions.isNullOrEmpty(staffObj.getLastName())? "": staffObj.getLastName());
						staffPersonAccountDetailsVO.setGender(StringFunctions.isNullOrEmpty(staffObj.getGender())? "": staffObj.getGender());
						staffPersonAccountDetailsVO.setImageId(staffObj.getImageId());
						staffPersonAccountDetailsVO.setMobileNumber(StringFunctions.isNullOrEmpty(staffObj.getMobileNumber())? "": staffObj.getMobileNumber());
						staffPersonAccountDetailsVO.setPhoneNumber(StringFunctions.isNullOrEmpty(staffObj.getPhoneNumber())? "": staffObj.getPhoneNumber());
						staffPersonAccountDetailsVO.setDateOfBirth(ObjectFunctions.isNullOrEmpty(staffObj.getDateOfBirth())? "": DateFunctions.convertDateToString(staffObj.getDateOfBirth()));
						staffPersonAccountDetailsVO.setBioMetricId(staffObj.getBioMetricId());
						staffPersonAccountDetailsVO.setEmail(StringFunctions.isNullOrEmpty(staffObj.getEmail())? "": staffObj.getEmail());
						staffPersonAccountDetailsVO.setStatus(StringFunctions.isNullOrEmpty(staffObj.getStatus())? "": staffObj.getStatus());
						staffPersonAccountDetailsVO.setStaffUniqueNumber(StringFunctions.isNullOrEmpty(staffObj.getStaffUniqueNumber())? "": staffObj.getStaffUniqueNumber());
						staffPersonAccountDetailsVO.setDesignation(StringFunctions.isNullOrEmpty(staffObj.getDesignation())? "": staffObj.getDesignation());
						staffPersonAccountDetailsVO.setBankName(StringFunctions.isNullOrEmpty(staffObj.getBankName())? "": staffObj.getBankName());
						staffPersonAccountDetailsVO.setBankAccountNumber(StringFunctions.isNullOrEmpty(staffObj.getBankAccountNumber())? "": staffObj.getBankAccountNumber());
						staffPersonAccountDetailsVO.setBankBranchName(StringFunctions.isNullOrEmpty(staffObj.getBankBranchName())? "": staffObj.getBankBranchName());
						staffPersonAccountDetailsVO.setSalary(ObjectFunctions.isNullOrEmpty(staffObj.getSalary())? 0.0: staffObj.getSalary());
						staffPersonAccountDetailsVO.setIfscCode(ObjectFunctions.isNullOrEmpty(staffObj.getIfscCode())? "": staffObj.getIfscCode());
						staffPersonAccountDetailsVO.setPanNumber(StringFunctions.isNullOrEmpty(staffObj.getPanNumber())? "": staffObj.getPanNumber());
						staffPersonAccountDetailsVO.setPfNo(StringFunctions.isNullOrEmpty(staffObj.getPfNo())? "": staffObj.getPfNo());
						staffPersonAccountDetailsVO.setEsiNo(StringFunctions.isNullOrEmpty(staffObj.getEsiNo())? "": staffObj.getEsiNo());
						staffPersonAccountDetailsVO.setSalaryPaymentMode(StringFunctions.isNullOrEmpty(staffObj.getSalaryPaymentMode())? "": staffObj.getSalaryPaymentMode());
						staffPersonAccountDetailsVO.setStaffGrade(StringFunctions.isNullOrEmpty(staffObj.getStaffGrade())? "": staffObj.getStaffGrade());
						staffPersonAccountDetailsVO.setStaffLocation(StringFunctions.isNullOrEmpty(staffObj.getStaffLocation())? "": staffObj.getStaffLocation());
						staffPersonAccountDetailsVO.setAcademicYearId(Long.valueOf(staffObj.getAcademicYearId()));
						staffPersonAccountDetailsVO.setCustId(staffObj.getCustId());
						staffPersonAccountDetailsVO.setImageUrl(StringFunctions.isNullOrEmpty(staffObj.getImagePath())? "": staffObj.getImagePath());
						// Staff Subjects
						List<ClassTeacher> staffSubjectList = this.getAll(ClassTeacher.class, "teacherId="+ staffObj.getStaffId() +" and academicYearId="+ staffObj.getAcademicYearId());
						if (!ObjectFunctions.isNullOrEmpty(staffSubjectList)) {
							ClassTeacherVO classTeacherVO = null;
							for(ClassTeacher staffSubject : staffSubjectList){
								classTeacherVO = new ClassTeacherVO();
								classTeacherVO.setClassTeacher(ObjectFunctions.isNullOrEmpty(staffSubject.getIsClassTeacher())? "": staffSubject.getIsClassTeacher());
								classTeacherVO.setStudyClassId(staffSubject.getStudyClassId());
								classTeacherVO.setStudySubjectId(staffSubject.getStudySubjectId());
								classTeacherVO.setSubjectName(staffSubject.getStudySubject().getName());
								classTeacherVO.setPeriodsCount(staffSubject.getPeriodsCount());
								classTeacherVO.setStaffId(staffSubject.getStaffId());
								staffPersonAccountDetailsVO.getClassTeacherVOs().add(classTeacherVO);
								classTeacherVO=null;
							}
						}
						staffPersonAccountDetailsVO.setStatus(staffObj.getStatus());
						staffPersonAccountDetailsVOs.add(staffPersonAccountDetailsVO);
						JSONObject main = new JSONObject();	
						JSONObject subVal = new JSONObject();
						main.put("notificationFor", "staff profile update");
						subVal.put("description", description);
						subVal.put("title", title);
						GsonBuilder gsonBuilder = new GsonBuilder(); gsonBuilder.serializeNulls(); 
						Gson gson = gsonBuilder.create();
						JSONParser parser = new JSONParser();
						subVal.put("staffPersonAccountDetailsVOS",(org.json.simple.JSONArray)parser.parse(gson.toJson(staffPersonAccountDetailsVOs)));
						main.put("information", subVal);
						this.sendNotificationToAndroidMobileUsersByUserIds(staffObj.getCustId(),main.toString(),accountIdbuffer.toString().replace("[", "").replace("]", "")); //To add notification for mobile app.
				 }staffPersonAccountDetailsVO=null;
				 staffObj=null;
				 staffPersonAccountDetailsVOs=null;
				 toAccountIds=null;
			 }
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	public boolean IsSecondaryUserAccountExists(String secondaryUsername){
		try
		{
			if(ObjectFunctions.isNullOrEmpty(this.getUserBySecondaryUserName(secondaryUsername)))
			{
				return false;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			return false;
		}
		return true;
	
	}
    public User getUserBySecondaryUserName(String secondaryUsername) {
        return (User) dao.getUserBySecondaryUserName(secondaryUsername);
    }
    @Transactional
	public void removeAllAndroidMobileUsers(String notRegisteredKey) {
		dao.removeAllAndroidMobileUsers(notRegisteredKey);
	}
	 public String SendSmsToAdminAndStaff(String anyTitle,Set<String> mobileNumbers, long custId,User user,AcademicYear academicYear,Leave leave)throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering CommunicationManagerImpl 'SendSmsToAdminAndStaff' method");
			}
			try {
				Messages mssag = null;
				Customer customer = this.getCustomerByCustId(custId);
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				if(!ObjectFunctions.isNullOrEmpty(customer)){
				if(customer.isCheckMobileService()){
				if(!StringFunctions.isNullOrEmpty(anyTitle)){
					if(anyTitle.equalsIgnoreCase("R")){
							mssag = new Messages();
							mssag.setStatus("STFLR");
							mssag.setCreatedById(user.getId());
							mssag.setLastUpdatedById(user.getId());
							mssag.setPurposeType("Staff Leave Rejected");
							mssag.setSenderName(user.getUserRoleDescription());
							mssag.setAcademicYear(academicYear);
							if(!ObjectFunctions.isNullOrEmpty(customer)){
								mssag.setCustomer(customer);
							}
							mssag.setSmsSenderId(customer.getSender());
							if(leave.isHalfDay()){
								if(!ObjectFunctions.isNullOrEmpty(leave.getSessionType())){
									if("M".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()==0.5)
										mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+",Your half day leave for Morning session on "+leave.getUserFormattedEndDate()+" has been rejected. Please contact your Admin/Principal to get approval of your leave. Thank you from ");
									else if("A".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()==0.5)
										mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+",Your half day leave for Afternoon session on "+leave.getUserFormattedEndDate()+" has been rejected. Please contact your Admin/Principal to get approval of your leave. Thank you from ");
									if("M".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()!=0.5)
										mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+", Your leave(s) from "+leave.getUserFormattedStartDate()+" to "+leave.getUserFormattedEndDate()+"(Morning Session) has been rejected. Please contact your Admin/Principal to get approval of your leave. Thank you from ");
									else if("A".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()!=0.5)
										mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+", Your leave(s) from "+leave.getUserFormattedStartDate()+" to "+leave.getUserFormattedEndDate()+"(Afternoon Session) has been rejected. Please contact your Admin/Principal to get approval of your leave. Thank you from ");
								}
							}else
								mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+", Your leave(s) from "+leave.getUserFormattedStartDate()+" to "+leave.getUserFormattedEndDate()+" has been rejected. Please contact your Admin/Principal to get approval of your leave. Thank you from ");
				    	}
					else{
						mssag = new Messages();
						mssag.setStatus("STFLA");
						mssag.setCreatedById(user.getId());
						mssag.setLastUpdatedById(user.getId());
						mssag.setPurposeType("Staff Leave Approved");
						mssag.setSenderName(user.getUserRoleDescription());
						mssag.setAcademicYear(academicYear);
						if(!ObjectFunctions.isNullOrEmpty(customer)){
							mssag.setCustomer(customer);
						}
						mssag.setSmsSenderId(customer.getSender());
						if(leave.isHalfDay()){
							if(!ObjectFunctions.isNullOrEmpty(leave.getSessionType())){
								if("M".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()==0.5)
									mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+",Your half day leave for Morning session on "+leave.getUserFormattedEndDate()+" has been Approved. Thank you from ");
								else if("A".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()==0.5)
									mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+",Your half day leave for Afternoon session on "+leave.getUserFormattedEndDate()+" has been Approved. Thank you from ");
								if("M".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()!=0.5)
								 	mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+", Your leave(s) from "+leave.getUserFormattedStartDate()+" to "+leave.getUserFormattedEndDate()+"(Morning Session) has been Approved. Thank you from ");
								else if("A".equalsIgnoreCase(leave.getSessionType()) && leave.getLeavesCount()!=0.5)
								 	mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+", Your leave(s) from "+leave.getUserFormattedStartDate()+" to "+leave.getUserFormattedEndDate()+"(Afternoon Session) has been Approved. Thank you from ");
							}
						}else
						 	mssag.setMessageDescription("Dear "+leave.getUser().getPerson().getPersonFullName()+", Your leave(s) from "+leave.getUserFormattedStartDate()+" to "+leave.getUserFormattedEndDate()+" has been Approved. Thank you from ");
						}
				}
				else{
					mssag = new Messages();
					mssag.setStatus("STFL");
					mssag.setCreatedById(user.getId());
					mssag.setLastUpdatedById(user.getId());
					mssag.setPurposeType("Staff Applied For Leave ToDay");
					mssag.setSenderName(user.getUserRoleDescription());
					mssag.setAcademicYear(academicYear);
					if(!ObjectFunctions.isNullOrEmpty(customer)){
						mssag.setCustomer(customer);
					}
					mssag.setSmsSenderId(customer.getSender());
					mssag.setMessageDescription("Dear Admin/Principal, Mr/Ms."+leave.getUser().getPerson().getPersonFullName()+" has applied the leave(s) from "+leave.getUserFormattedStartDate()+" to "+leave.getUserFormattedEndDate()+". Please log into eazyschool application to approve/reject the leave Thank you from ");
				  }	
				if(!ObjectFunctions.isNullOrEmpty(mobileNumbers) && StringFunctions.isNotNullOrEmpty(anyTitle)){
					if((anyTitle.equalsIgnoreCase("R"))||(anyTitle.equalsIgnoreCase("A"))){
						if((leave.getUser().getRoleId())==3){
							Student student = null;
							student = (Student) this.get(Student.class, "custId="+custId+" and academicYearId="+academicYear.getId()+" and accountId="+leave.getUser().getId());
							mssag = this.saveMessageDetailsTracking(mssag,student,null,null);				
							}			
						else{
							Staff staff = null;
							staff = (Staff) this.get(Staff.class, "custId="+custId+" and academicYearId="+academicYear.getId()+" and accountId="+leave.getUser().getId());
							mssag = this.saveMessageDetailsTracking(mssag,null,staff,null);		
							}
					 	}
				
					else {
					Staff staff = null;
					staff = (Staff) this.get(Staff.class, "custId="+custId+" and academicYearId="+academicYear.getId()+" and accountId="+user.getId());
					mssag = this.saveMessageDetailsTracking(mssag,null,staff,null);					
					}
				}	
				 if(!ObjectFunctions.isNullOrEmpty(mobileNumbers)){
					@SuppressWarnings("unused")
					boolean msgStatus = this.deliverSms(mssag,mobileNumbers,smsServiceProvider);
					if(msgStatus = true){
						log.debug("Sms has been delivered successfully.");
					}else{
						log.debug("Sms has not been delivered.");
					}
					mobileNumbers=null;
				  }
				}else{
					log.debug("Please enable your SMS Service...");
				}
				}
				else{
					log.debug("Customer doesn't exist...."+custId);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	public User usernameAvailabulity(String userName, long custId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering  'usernameAvailabulity' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(userName)) {
				List<User> staffSeconrdyUserList = null;
				List<User> custStaffSeconrdyUserList = null;
				if (StringFunctions.isNotNullOrEmpty(userName)) {
					//staffSeconrdyUserList = this.getAll(User.class," username = '" + userName+ "' and accountEnabled='Y' ");
					//Commented by Siva, Here cwe are checking only mobile no to maintain global username for parents
					staffSeconrdyUserList = this.getAll(User.class," username = '" + userName+"'");
					if (staffSeconrdyUserList.size() > 0) {
						return (User) staffSeconrdyUserList.get(0);
					} /*else {
						//custStaffSeconrdyUserList = this.getAll(User.class," username like '"+ userName+ "' and accountEnabled='N' and custId ="+ custId);
						//Commented by Siva, Here custId is not required because we are checking only mobile no to maintain global username 
						custStaffSeconrdyUserList = this.getAll(User.class," username like '"+ userName+ "' and accountEnabled='N'");
						if (custStaffSeconrdyUserList.size() > 0) {
							return (User) custStaffSeconrdyUserList.get(0);
						}
					}*/
				}
				staffSeconrdyUserList = null;
				custStaffSeconrdyUserList = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	
	public int getAvailableSmsCount(long custId,long academicYearId){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getSmsCount' method");
		}
		int sendSmsCount=0;
		int allotedSmsCount=0;
		int availableSmsCount=0;
		try {
			sendSmsCount=this.getTotalSmsCount(custId, academicYearId) ;
			AcademicYear academicYearObj=(AcademicYear) this.get(AcademicYear.class,academicYearId);
			if(!ObjectFunctions.isNullOrEmpty(academicYearObj)){
				allotedSmsCount=(int) (academicYearObj.getAllotedsms() + academicYearObj.getPaidSms());
			}
			if((allotedSmsCount == 0) || (allotedSmsCount <= sendSmsCount)){
				return 0;
			}else{
				availableSmsCount=allotedSmsCount - sendSmsCount;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return availableSmsCount;
	}
	 /**
     * Utility method to save InputStream data to target location/file
     * @param inStream - InputStream to be saved
     * @param target - full path to destination file
     */
	public void saveToFile(InputStream inStream, String target) throws IOException {
		try{
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*public void updateStudentFeePaidStatus(long studentId,String status){
		dao.updateStudentFeePaidStatus(studentId,status);
	}*/
	// RaviTeja 01-12-2016 : Change The entire Forgot Password Method
	public Map<String,String> sendForgotPasswordByUserName(User luser, String password){
		 Map<String, String> msg = new HashMap<String, String>();
		try{
			Customer customer=(Customer) this.get(Customer.class, luser.getCustId());
			Student student = null;
			Staff staff = null;
			User account = null;	
			MailUtil mailUtil = null;
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				AcademicYear academicYear=(AcademicYear) this.getCurrentAcademicYear(Constants.YES_STRING,customer.getId());
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					if(!ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentMobile()) && !StringFunctions.isNullOrEmpty(luser.getPerson().getStudentEmail()) || !ObjectFunctions.isNullOrEmpty(luser.getPerson().getParentEmail()) 
							|| !ObjectFunctions.isNullOrEmpty(luser.getPrimaryAddress()) && !StringFunctions.isNullOrEmpty(luser.getPrimaryAddress().getEmail()) || 
							!ObjectFunctions.isNullOrEmpty(luser.getPerson()) && !StringFunctions.isNullOrEmpty(luser.getPerson().getMobileNumber())){
					if(!ObjectFunctions.isNullOrEmpty(password)){
						luser.setPassword(PasswordUtils.passwordEncoder(password, null));
						luser.setConfirmPassword(password);
						luser.setPasswordStatus(false);
						luser.setLastUpdatedDate(new Date());
						luser.setLastAccessDate(new Date());
						this.save(luser);
					}
					if((!ObjectFunctions.isNullOrEmpty(luser.getPrimaryAddress()) && !StringFunctions.isNullOrEmpty(luser.getPrimaryAddress().getEmail())) || !ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentEmail()) || !ObjectFunctions.isNullOrEmpty(luser.getPerson().getParentEmail())){
						String[] emailAddresses = new String[1];
						if(luser.isSchoolStudent())
							emailAddresses[0] = luser.getPerson().getStudentEmail();
						else if(luser.isParent())
							emailAddresses[0] = luser.getPerson().getParentEmail();
						else	
							emailAddresses[0] = luser.getPrimaryAddress().getEmail();
						if(!ObjectFunctions.isNullOrEmpty(emailAddresses[0])){
							
							mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
							/*if(luser.isParent())
								mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
							else if(luser.isSchoolStudent())
								mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
							else
								mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));*/
							mailUtil.setContactEmail(customer.getContactEmail());
							mailUtil.setContactPasword(customer.getContactPassword());
							mailUtil.sendMailForPwdChange(luser.getConfirmPassword(),luser.getUsername(),this.getContactFromEmail(customer));
							mailUtil=null;
							emailAddresses=null;
							msg.put("1","Password has been updated successfully sent to your Email.");
						}
					}
					if(StringFunctions.isNotNullOrEmpty(luser.getPerson().getStudentMobile()) || StringFunctions.isNotNullOrEmpty(luser.getPerson().getMobileNumber())){
						Messages messages = new Messages();
						StringBuffer msgContent = new StringBuffer();
						if(luser.isSchoolStudent() && ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentMobile()) && ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentEmail())){
							msgContent.append("Dear Parent, Your child "+luser.getPerson().getFullPersonName()+" password for Eazyschool account has been reset to '");
						}else{
							msgContent.append("Your password for Eazyschool portal login is '");
						}
						msgContent.append(password);
						msgContent.append("' Thank you from ");
						messages.setCustomer(customer);
						messages.setSmsSenderId(customer.getSender());
						messages.setMessageDescription(msgContent.toString());
						messages.setStatus("A");// here change the FPWD to A because is send to automatic the message 
						messages.setSenderName("System");
						messages.setAcademicYear(academicYear);
						messages.setPurposeType("Regd: Forgot Password");
						Set<String> mobileNumber=new HashSet<String>();
						if (StringFunctions.isNotNullOrEmpty(luser.getPerson().getStudentMobile()) || StringFunctions.isNotNullOrEmpty(luser.getPerson().getMobileNumber())){
							if(luser.isSchoolStudent() || luser.isParent()){
								if(StringFunctions.isNotNullOrEmpty(luser.getPerson().getMobileNumber())){
									mobileNumber.add(luser.getPerson().getMobileNumber());
									account = (User) this.get(User.class, "custId="+customer.getId()+" and id="+ luser.getId());
									messages = this.saveMessageDetailsTracking(messages,null,null,account);	
								}else{
									mobileNumber.add(luser.getPerson().getStudentMobile());
									student = (Student) this.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+ luser.getId());
									messages = this.saveMessageDetailsTracking(messages,student,null,null);
								}
							}else{
								mobileNumber.add(luser.getPerson().getMobileNumber());
								staff = (Staff) this.get(Staff.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+ luser.getId());
								messages = this.saveMessageDetailsTracking(messages,null,staff,null);
							}
							if(!ObjectFunctions.isNullOrEmpty(mobileNumber))
								if(this.deliverSms(messages,mobileNumber,smsServiceProvider))
									msg.put("2","Password has been updated successfully.");
								else
									msg.put("3","Password has not updated due to some technical reasons. Please contact Administrator.");
						}
					mobileNumber = null;
					messages = null;
					msgContent = null;
				  }
			}else{
					  msg.put("5","We do not find any communication details(Mobile or Email) for sending password. Please contact Administrator.");
				 }
				}
				academicYear = null;
			}
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return msg;
	}
	
	public File loadImageFromURL(String fileName)
	{
		try {
			BufferedImage img = ImageIO.read(new URL(fileName));
			File tempFile = File.createTempFile(""+Math.random(), ".jpg");
			
			ImageIO.write(img, "jpg", tempFile);
			return tempFile;
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public SMSServiceProviders getSMSServiceProviderByCustId(long smsServiceProviderId){
		return (SMSServiceProviders)this.get(SMSServiceProviders.class, smsServiceProviderId);
	}
	public Customer getMasterCustomerById(){
		return (Customer)this.get(Customer.class, Long.valueOf(1));
	}
	
	/**
	 * This method used to send the SMS 
	 */
	@Override
	public int sendSMS(SMSVO smsVO) {
		Customer customer = getCustomerByCustId(smsVO.getIdentifier().getCustId());
		AcademicYear academicYear = getCurrentAcademicYear(Constants.YES_STRING,smsVO.getIdentifier().getCustId());
		int totalUsedSMSCount = getTotalSmsCount(smsVO.getIdentifier().getCustId(),smsVO.getIdentifier().getAcademicYearId());
		int allotedSMSCount = (int) academicYear.getAllotedsms()+(int) academicYear.getPaidSms();
		Set<String> mobileNumbers;
		if(!ObjectFunctions.isNullOrEmpty(smsVO.getMobileNumber())){
			 mobileNumbers =	org.springframework.util.StringUtils.commaDelimitedListToSet(smsVO.getMobileNumber());
			 double count = 0.0,total =160.0,charcount=0.0;
			 String input = smsVO.getMessage();
			 count = input.length()/total;
			 charcount = Math.ceil(count);
			 int totalSMSs =(int)charcount * mobileNumbers.size();
			 if(allotedSMSCount - (totalUsedSMSCount + totalSMSs ) >=0)	{
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				User user = (User) dao.get(User.class,smsVO.getIdentifier().getAccountId());
				Messages msgs = new Messages();
				msgs.setStatus("M");
				msgs.setMessageType("SMS");
				msgs.setCreatedById(user.getId());
				msgs.setLastUpdatedById(user.getId());
				msgs.setPurposeType(smsVO.getMessageType());
				msgs.setSenderName(user.getUserRoleDescription());
				msgs.setAcademicYear(academicYear);
				msgs.setSmsSenderId(customer.getSender());
				msgs.setChannel(smsVO.getIdentifier().getChannel());
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					msgs.setCustomer(customer);
				}
				msgs.setMessageDescription(smsVO.getMessage() + " ");	
				msgs = this.saveMessageDetailsTracking(msgs, null, null, user);
				boolean smsStatus = this.deliverSms(msgs, mobileNumbers, smsServiceProvider);
				if(smsStatus = true){
					//return "SMS has been delivered successfully.";
					return 0;
				}else{
					//return "SMS has not been delivered.";
					return 1;
				}
			
			}else{
				//return "Sms is not delivered due to insufficient sms balance/sms service is disabled.";
				return 2;
			}
		}
		return -1;
	}

	@Override
	public AcademicYear getFeatureAcademicYear(long custId,
			long currentAcademicYearId) {
		return dao.getFeatureAcademicYear(custId, currentAcademicYearId);
	}
	
	
}