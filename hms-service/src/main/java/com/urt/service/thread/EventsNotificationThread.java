package com.urt.service.thread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.event.AlbumAttachment;
import com.urt.persistence.model.event.Events;
import com.urt.persistence.model.event.EventsAlbum;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.util.email.MailUtil;

public class EventsNotificationThread  implements Runnable{
	
	private static final Log log = LogFactory.getLog(EventsNotificationThread.class);
	private Thread t;
	private Events event;
	private long eventVoId;
	private String noSMSAndEmail;
	//private String albumAttachmentIds;
	List<AlbumAttachment> albumAttachmentList;
	
	
	@Autowired
	public CommunicationManager communicationManager;
	
	@Autowired
	public AdminManager adminManager;
	
	
	public EventsNotificationThread(Events event,long eventVoId,List<AlbumAttachment> albumAttachmentList) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.event = event;
		this.albumAttachmentList =  albumAttachmentList;
	}

	
	public EventsNotificationThread(Events event,long eventVoId,String noSMSAndEmail,List<AlbumAttachment> albumAttachmentList) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.event = event;
		this.noSMSAndEmail = noSMSAndEmail;
		this.albumAttachmentList =  albumAttachmentList;
	}

	@Override
	public void run() 
	{
		try {
			int availableSmsCount=0;
			AcademicYear academicYear = event.getAcademicYear();
			int sendSmsCount=communicationManager.getTotalSmsCount(academicYear.getCustId(), academicYear.getId()) ;
			int allotedSmsCount=(int) (academicYear.getAllotedsms() + academicYear.getPaidSms());
			if(allotedSmsCount >= sendSmsCount){
				availableSmsCount=allotedSmsCount - sendSmsCount;
			}
			Customer customer = (Customer) adminManager.get(Customer.class, "id="+academicYear.getCustId());
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			User userObj = (User) adminManager.get(User.class, "id="+event.getCreatedById());
			JSONObject main = new JSONObject();
			JSONObject subVal = new JSONObject();
			JSONObject eventSub = new JSONObject();
			JSONArray albumAttachmentArray = new JSONArray();
			
			JSONArray eventArray = new JSONArray();
			main.put("notificationFor", "Events");
			
			if(!StringFunctions.isNullOrEmpty(noSMSAndEmail))
			{
				subVal.put("title", "Photos added for "+ event.getEventName() +" event");
				subVal.put("description",  "Photos added for "+ event.getEventName() +" event");
			}
			else
			{
				if(eventVoId > 0)
					subVal.put("title", event.getEventName()+" event updated.");
				else
					subVal.put("title", "A New Event created "+ event.getEventName());
				subVal.put("description", event.getEventDescription());
			}
			
			
			eventSub.put("id",event.getId());
			eventSub.put("startDateTime", ObjectFunctions.isNullOrEmpty(event.getStartDate())? "": DateFunctions.convertDateToString(event.getStartDate()));
			eventSub.put("endDateTime",ObjectFunctions.isNullOrEmpty(event.getEndDate())? "": DateFunctions.convertDateToString(event.getEndDate()));
			eventSub.put("eventName",event.getEventName());
			eventSub.put("startTime",ObjectFunctions.isNullOrEmpty(event.getStartTime())? "": event.getStartTime());
			eventSub.put("endTime",ObjectFunctions.isNullOrEmpty(event.getEndTime())? "": event.getEndTime());
			eventSub.put("eventDescription",event.getEventDescription());
			eventSub.put("status","E");
			eventSub.put("eventFor",event.getEventFor());
			eventSub.put("eventCreatedUserId",event.getCreatedById());
			eventSub.put("address",event.getEventAddress());
			eventSub.put("academicYearId",event.getAcademicYear().getId());
			eventSub.put("custId",event.getCustId());
			
			
			if(!ObjectFunctions.isNullOrEmpty(albumAttachmentList))
			{
				for(AlbumAttachment albumAttachment1 : albumAttachmentList)
				{
					JSONObject albumAttachment = new JSONObject();
					albumAttachment.put("id", albumAttachment1.getId());
					albumAttachment.put("fileName", albumAttachment1.getFileName());
					albumAttachment.put("filePath", albumAttachment1.getFilePath());
					albumAttachmentArray.put(albumAttachment);
				}
			}
			else
			{
				List<EventsAlbum> eventsAlbumList = adminManager.getAll(EventsAlbum.class, "custId="+event.getCustId()+" and academicYearId="+event.getAcademicYear().getId()+" and eventId="+event.getId());
				if(!ObjectFunctions.isNullOrEmpty(eventsAlbumList))
				{
					for(EventsAlbum eventsAlbum : eventsAlbumList)
					{
						Set<AlbumAttachment> albumAttachmentSet = eventsAlbum.getAlbumAttachment();
						if(!ObjectFunctions.isNullOrEmpty(eventsAlbumList))
						{
							for(AlbumAttachment albumAttachment1 : albumAttachmentSet)
							{
								JSONObject albumAttachment = new JSONObject();
								albumAttachment.put("id", albumAttachment1.getId());
								albumAttachment.put("fileName", albumAttachment1.getFileName());
								albumAttachment.put("filePath", albumAttachment1.getFilePath());
								albumAttachmentArray.put(albumAttachment);
							}
						}
					}
				}
			}
			
			eventSub.put("albumAttachmentVo", albumAttachmentArray);
			
			
			if("all".equalsIgnoreCase(event.getEventFor()))
			{
				try {
					
					eventArray.put(eventSub);
					subVal.put("eventsVoList", eventArray);
					subVal.put("academicYearId",event.getAcademicYear().getId());
					
					main.put("information", subVal);
					log.debug(main.toString()); 
					adminManager.sendNotificationToAndroidMobileUsers(event.getCustId(),main.toString()); //To add notification for mobile app.
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(StringFunctions.isNullOrEmpty(noSMSAndEmail))
				{
					if(customer.isCheckMobileService() || customer.isCheckEmailService())
					{
						if (!ObjectFunctions.isNullOrEmpty(customer) && customer.isCheckMobileService()) 
						{
							if(academicYear.isSendEventAlertSMS() || academicYear.isSendEventAlertsByEmail())
							{
								List<Object[]> staffDetails=adminManager.getAll("select fullName,mobileNumber,email from vw_staffDetails where custId="+ customer.getId() + " and status='"+ Constants.YES_STRING+ "' and academicYearId="+ academicYear.getId()+" order by staffId ");
								if(!ObjectFunctions.isNullOrEmpty(staffDetails))
								{
									for(Object[] staObj : staffDetails)
									{
										if (customer.isCheckMobileService() && academicYear.isSendEventAlertSMS() && availableSmsCount >0) 
										{
											if(!ObjectFunctions.isNullOrEmpty(staObj[1]))
											{
												log.debug(staObj[0].toString() + " " + staObj[1].toString());
												try {
													sendSms(event,customer,academicYear,staObj[0].toString(),staObj[1].toString(),smsServiceProvider);
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										}
										
										if (customer.isCheckEmailService() && academicYear.isSendEventAlertsByEmail()) 
										{
											if(!ObjectFunctions.isNullOrEmpty(staObj[2]))
											{
												log.debug(staObj[0].toString() + " " + staObj[2].toString());
												try {
													sendEmail(event,userObj,customer,staObj[0].toString(),staObj[2].toString());
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										}
									}
								}
								
								//String query = "select fullName,mobileNumber,studentEmail,parentEmail from vw_studentDetails ";
								//adminManager.getAll(query+ " where classSectionId="+ studyClass.getId()+ "  and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and description is null and studentEmail is not null");
								
								List<Object[]> studentDetails= adminManager.getAll("select fullName,mobileNumber,studentEmail,parentEmail,fatherName from vw_studentDetails where custId="+customer.getId()+" and academicYearId="+academicYear.getId()+ " and description is null");
								if(!ObjectFunctions.isNullOrEmpty(studentDetails))
								{
									for(Object[] stuObj : studentDetails)
									{
										if (customer.isCheckMobileService() && academicYear.isSendEventAlertSMS()  && availableSmsCount >0) 
										{
											if(!ObjectFunctions.isNullOrEmpty(stuObj[1]))
											{
												try {
													sendSms(event,customer,academicYear,stuObj[0].toString(),stuObj[1].toString(),smsServiceProvider);
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										}
										
										if (customer.isCheckEmailService() && academicYear.isSendEventAlertsByEmail()) 
										{
											if(!ObjectFunctions.isNullOrEmpty(stuObj[3]))
											{
												try {
													sendEmail(event,userObj,customer,stuObj[0].toString(),stuObj[2].toString());
													sendEmail(event,userObj,customer,stuObj[4].toString(),stuObj[3].toString());
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
							
						}
					}
				}
				
			}
			else if("cs".equalsIgnoreCase(event.getEventFor()))
			{
				StringBuffer allAccountIds = new StringBuffer();
				allAccountIds.append("(");
				StringBuffer studyClassIds = new StringBuffer();
				if(!ObjectFunctions.isNullOrEmpty(event.getStudyClass()))
				{
					for(StudyClass studyClass : event.getStudyClass())
					{
						studyClassIds.append(studyClass.getId()+",");
					}
					
					eventSub.put("studyClassIds",studyClassIds);
					
					String query = "select CONCAT(GROUP_CONCAT(Distinct(accountId)),',',GROUP_CONCAT(DISTINCT(parentId))) as studentParentUserIds,GROUP_CONCAT(DISTINCT(mobileNumber)) as parentMobileNumber,GROUP_CONCAT(DISTINCT(parentEmail)) as parentEmail  from vw_studentClasswiseAndPersonalInfo where classSectionId in ("+studyClassIds.toString()+"0)";
					log.debug(query);
					Object[] studentAccountObjArr = (Object[])adminManager.get(query);
					if(!ObjectFunctions.isNullOrEmpty(studentAccountObjArr))
					{
						if(!ObjectFunctions.isNullOrEmpty(studentAccountObjArr[0]))
						{
							allAccountIds.append(studentAccountObjArr[0].toString());
						}
					}
				}
				
				StringBuffer nonTeachingRoleIds = new StringBuffer();
				if(!ObjectFunctions.isNullOrEmpty(event.getRole()))
				{
					for(Role role : event.getRole())
					{
						nonTeachingRoleIds.append(role.getId()+",");
					}
				}
				
				nonTeachingRoleIds.append("1,12,31");
				
				
				String query = "select GROUP_CONCAT(Distinct(accountId)) as staffUserIds,GROUP_CONCAT(DISTINCT(mobileNumber)) as staffMobileNumber,GROUP_CONCAT(DISTINCT(email)) as staffEmail  from vw_staffDetails where roleId in ("+nonTeachingRoleIds.toString()+")";
				//log.debug(query);
				Object[] staffAccountObjArr = (Object[])adminManager.get(query);
				if(!ObjectFunctions.isNullOrEmpty(staffAccountObjArr))
				{
					if(!ObjectFunctions.isNullOrEmpty(staffAccountObjArr[0]))
					{
						allAccountIds.append(staffAccountObjArr[0].toString());
					}
				}
				
				allAccountIds.append("0)");
				try {
					
					eventArray.put(eventSub);
					subVal.put("eventsVoList", eventArray);
					subVal.put("academicYearId",event.getAcademicYear().getId());
					
					main.put("information", subVal);
					
					log.debug(main.toString());
					adminManager.sendNotificationToAndroidMobileUsersByUserIds(event.getCustId(),main.toString(),allAccountIds.toString()); //To add notification for mobile app.
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(StringFunctions.isNullOrEmpty(noSMSAndEmail))
				{
					List<Object[]> studentDetails= adminManager.getAll("select fullName,mobileNumber,studentEmail,parentEmail,fatherName from vw_studentDetails where classSectionId in ("+studyClassIds.toString()+"0) and custId="+customer.getId()+" and academicYearId="+academicYear.getId()+ " and description is null");
					if(!ObjectFunctions.isNullOrEmpty(studentDetails))
					{
						for(Object[] stuObj : studentDetails)
						{
							if (customer.isCheckMobileService() && academicYear.isSendEventAlertSMS()  && availableSmsCount >0) 
							{
								if(!ObjectFunctions.isNullOrEmpty(stuObj[1]))
								{
									try {
										sendSms(event,customer,academicYear,stuObj[0].toString(),stuObj[1].toString(),smsServiceProvider);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
							
							if (customer.isCheckEmailService() && academicYear.isSendEventAlertsByEmail()) 
							{
								if(!ObjectFunctions.isNullOrEmpty(stuObj[3]))
								{
									try {
										sendEmail(event,userObj,customer,stuObj[0].toString(),stuObj[2].toString());
										sendEmail(event,userObj,customer,stuObj[4].toString(),stuObj[3].toString());
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
					studentDetails = null;
					
					log.debug("select fullName,mobileNumber,email from vw_staffDetails where custId="+ customer.getId() + " and status='"+ Constants.YES_STRING+ "' and academicYearId="+ academicYear.getId()+" and roleId in ("+nonTeachingRoleIds.toString()+")");
					List<Object[]> staffDetails=adminManager.getAll("select fullName,mobileNumber,email from vw_staffDetails where custId="+ customer.getId() + " and status='"+ Constants.YES_STRING+ "' and academicYearId="+ academicYear.getId()+" and roleId in ("+nonTeachingRoleIds.toString()+")");
					if(!ObjectFunctions.isNullOrEmpty(staffDetails))
					{
						for(Object[] staObj : staffDetails)
						{
							if (customer.isCheckMobileService() && academicYear.isSendEventAlertSMS()  && availableSmsCount >0) 
							{
								if(!ObjectFunctions.isNullOrEmpty(staObj[1]))
								{
									try {
										sendSms(event,customer,academicYear,staObj[0].toString(),staObj[1].toString(),smsServiceProvider);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
							
							if (customer.isCheckEmailService() && academicYear.isSendEventAlertsByEmail()) 
							{
								if(!ObjectFunctions.isNullOrEmpty(staObj[2]))
								{
									try {
										sendEmail(event,userObj,customer,staObj[0].toString(),staObj[2].toString());
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
					staffDetails = null;
				}
			}
			
			else if("s".equalsIgnoreCase(event.getEventFor()))
			{
				List<Object[]> staffDetails= null;
				
				if("A".equalsIgnoreCase(event.getStaffEvent()))
					staffDetails=adminManager.getAll("select fullName,mobileNumber,email,accountId from vw_staffDetails where custId="+ customer.getId() + " and status='"+ Constants.YES_STRING+ "' and academicYearId="+ academicYear.getId()+" order by staffId ");
				else if("T".equalsIgnoreCase(event.getStaffEvent()))
				{
					staffDetails=adminManager.getAll("select fullName,mobileNumber,email,accountId from vw_staffDetails where custId="+ customer.getId() +" and roleName in ('ROLE_TEACHER','ROLE_HOD','ROLE_PRINCIPAL','ROLE_ASST_TEACHER','ROLE_ADMIN_COORDINATOR') and  status='"+ Constants.YES_STRING+ "' and academicYearId="+ academicYear.getId()+" order by staffId ");
				}
				else if("N".equalsIgnoreCase(event.getStaffEvent()))
				{
					staffDetails=adminManager.getAll("select fullName,mobileNumber,email,accountId from vw_staffDetails where custId="+ customer.getId() + " and roleName not in ('ROLE_TEACHER','ROLE_HOD','ROLE_PRINCIPAL','ROLE_ASST_TEACHER','ROLE_ADMIN_COORDINATOR') and status='"+ Constants.YES_STRING+ "' and academicYearId="+ academicYear.getId()+" order by staffId ");
				}	
				
				try {
					
					eventSub.put("staffEvent",event.getStaffEvent());
					eventArray.put(eventSub);
					subVal.put("eventsVoList", eventArray);
					subVal.put("academicYearId",event.getAcademicYear().getId());
					
					main.put("information", subVal);
					log.debug(main.toString()); 
					if(!ObjectFunctions.isNullOrEmpty(staffDetails))
					{
						StringBuffer staffAccountIds = new StringBuffer();
						staffAccountIds.append("(");
						for(Object[] staObj : staffDetails)
						{
							staffAccountIds.append(staObj[3].toString()+",");
						}
						staffAccountIds.append("0)");
						adminManager.sendNotificationToAndroidMobileUsersByUserIds(event.getCustId(),main.toString(),staffAccountIds.toString()); //To add notification for mobile app.
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(StringFunctions.isNullOrEmpty(noSMSAndEmail))
				{
					if(customer.isCheckMobileService() || customer.isCheckEmailService())
					{
						if (!ObjectFunctions.isNullOrEmpty(customer) && customer.isCheckMobileService()) 
						{
							if(academicYear.isSendEventAlertSMS() || academicYear.isSendEventAlertsByEmail())
							{
								
								if(!ObjectFunctions.isNullOrEmpty(staffDetails))
								{
									for(Object[] staObj : staffDetails)
									{
										if (customer.isCheckMobileService() && academicYear.isSendEventAlertSMS()  && availableSmsCount >0) 
										{
											if(!ObjectFunctions.isNullOrEmpty(staObj[1]))
											{
												log.debug(staObj[0].toString() + " " + staObj[1].toString());
												try {
													sendSms(event,customer,academicYear,staObj[0].toString(),staObj[1].toString(),smsServiceProvider);
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										}
										
										if (customer.isCheckEmailService() && academicYear.isSendEventAlertsByEmail()) 
										{
											if(!ObjectFunctions.isNullOrEmpty(staObj[2]))
											{
												log.debug(staObj[0].toString() + " " + staObj[2].toString());
												try {
													sendEmail(event,userObj,customer,staObj[0].toString(),staObj[2].toString());
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendSms(Events event,Customer customer,AcademicYear academicYear,String fullName,String mobileNumber,SMSServiceProviders smsServiceProvider)
	{
		try {
			Set<String> mobileNumberset = new HashSet<String>();
			mobileNumberset.add(mobileNumber);
			Messages message = new Messages();
			message.setCustomer(customer);	
			message.setSmsSenderId(customer.getSender());
			StringBuffer buffer = new StringBuffer();
			buffer.append("Dear "+ fullName+", We are pleased to invite you for "+ event.getEventName() +" on "+ event.getEventStartDateStr()+" at " + event.getEventAddress());
			message.setMessageDescription(buffer.toString());
			message.setAcademicYear(academicYear);
			message.setStatus(Constants.MODIFY_STATUS);
			message.setCreatedById(event.getCreatedById());
			message.setPurposeType("Event");
			log.debug(buffer.toString());
			Student student = null;
			message = communicationManager.saveMessageDetailsTracking(message,student,null,null);	
			boolean smsStatus = communicationManager.deliverSms(message,mobileNumberset, smsServiceProvider);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void sendEmail(Events event,User userObj,Customer customer,String fullName,String email)
	{
		try {
			String fromEmail = null;
			 if(StringFunctions.isNullOrEmpty(customer.getContactEmail()) && StringFunctions.isNullOrEmpty(customer.getContactPassword())){
				 fromEmail =  "messages@eazyschool.com";
				}else {
					fromEmail=  customer.getContactEmail();
				}
			 
			 
			String msg = "Dear "+ fullName+", \nWe are pleased to invite you for "+ event.getEventName() +" on "+ event.getEventStartDateStr()+" at " + event.getEventAddress();
			String[] emailAddresses = new String[1];
			emailAddresses[0] = email;
			MailUtil mailUtil=new MailUtil(emailAddresses, "Event Creation", msg, userObj);
			mailUtil.sendEventEmail(fromEmail);
			mailUtil=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public void start ()
	   {
	      if (t == null)
	      {
	         t = new Thread (this);
	         t.start ();
	      }
	   }
	 
	 
}
