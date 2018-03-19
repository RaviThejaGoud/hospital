/**
 * 
 */
package com.urt.service.manager.impl.user;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.google.gson.Gson;
import com.hyniva.sms.ws.vo.CircularMessagesVO;
import com.hyniva.sms.ws.vo.MessagesMainVO;
import com.hyniva.sms.ws.vo.MessagesVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.hyniva.sms.ws.vo.ViewCircularUsersVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.interfaces.user.UserDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Circular;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.user.CommunicationManager;
import com.urt.service.thread.AddCircularMessageThread;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.util.jrexception.JRExceptionClient;

/**
 * @author Sreeram J
 * 
 */
@Component
public class CommunicationManagerImpl extends UniversalManagerImpl implements CommunicationManager {

	@Autowired
	private UserDao userDao;

	@Transactional
	public Map<String, String> sendMarksToUsers(HashMap keys, AcademicYear academicYear,String selectedStudentIds) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'sendMarksToUsers' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try {
			Customer customer = super.getCustomerByCustId((Long) keys.get("custId"));
			if (ObjectFunctions.isNullOrEmpty(customer) || !customer.isCheckMobileService()){
				msg.put("3", "SMS service disabled, enable service.");
			}
			boolean smsSuccess = false;
			boolean smsStatus = false;
			String mobileNumbers = null;
			String mobileNumber = null;
			String firstName = null;
		//	String admissionNumber = null;
			StringBuffer buffer = null;
			StringBuffer failedMsgs = null;
			long classId = (Long) keys.get("classId");
			long examTypeId = (Long) keys.get("examTypeId");
			String gradeType = (String) keys.get("gradeType");
			failedMsgs = new StringBuffer();
			failedMsgs.append("(");
			long studId = 0;
			Student student =null;
			DecimalFormat df = new DecimalFormat("#.##");
			SimpleDateFormat formatter = new SimpleDateFormat(DateFormatter.ddMMMyyyy_PATTERN1);
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			String mobileType = customer.getMobileType();
			StringBuffer invalidMobileNum = new StringBuffer();
			//List<Object[]> studentsList = super.getAll("select firstName,mobileNumber,admissionNumber,studId from vw_studentClassDetails where academicYearId="+ academicYear.getId()+ " and custId="+ customer.getId()+ " and studDiscontinueDesc is null and classId="+ classId+ " and (mobileNumber !='' and mobileNumber is not null )");
			StringBuffer query = new StringBuffer("select p.firstName,a.admissionNumber,sm.studId,sum(sm.obtainedMarks) as totalObtaineedMarks,ifnull(sum(es.maxMarks),0) as totalMaxMarks,");
			if("B".equalsIgnoreCase(mobileType)){
				query.append(" p.mobileNumber,p.secondaryMobileNumber,p.anotherMobileNumber,p.anotherSecondaryMobileNumber");
			}else if("P".equalsIgnoreCase(customer.getMobileType()))
				query.append(" p.mobileNumber,p.anotherMobileNumber");
			else{
				query.append(" p.secondaryMobileNumber,p.anotherSecondaryMobileNumber");
			}
			//query.append(" ,p.addressType,a.accountId from studentMarks vsm LEFT JOIN vw_studentClassDetails vsc on (vsm.studid=vsc.studId and vsm.custId=vsc.custId ) where vsm.custId="+customer.getId()+" and vsm.academicYearId="+academicYear.getId()+" and vsm.classSectionId="+classId+" and vsm.examTypeId="+examTypeId+" and vsc.accountId in "+selectedStudentIds+" and vsm.status='Y' and vsm.studDiscontinueDesc is null and (mobileNumber is not null or secondarymobileNumber is not null) group by vsc.studId order by sum(vsm.obtainedMarks) desc");
			query.append(" ,p.addressType,s.accountId from studentMarks sm JOIN student s ON (sm.studId=s.id and s.status='Y' and s.description is null) JOIN Account a ON (a.id=s.accountId) ");
			query.append(" JOIN Person p ON (a.personId=p.id) JOIN examSchedules es ON (sm.examScheduleId = es.Id and es.classSectionId = s.classSectionId and es.custId=s.custId and es.academicYearId=s.academicYearId) JOIN examTypes et ON (et.id=es.examTypeId)");
			query.append(" JOIN studySubject ss ON (ss.id = es.classSubjectId) JOIN subType st ON (st.id = es.subTypeId) JOIN studyClass sc ON (sc.id = s.classSectionId) where s.classSectionId="+classId+" and  es.examTypeId="+examTypeId+" ");
			query.append(" and s.accountId in "+selectedStudentIds+" and (p.mobileNumber is not null or p.secondarymobileNumber is not null) group by sm.studId order by sm.studId desc ");
			
			//LEFT JOIN vw_studentClassDetails vsc on (vsm.studid=vsc.studId and vsm.custId=vsc.custId ) where vsm.custId="+customer.getId()+" and vsm.academicYearId="+academicYear.getId()+" and vsm.classSectionId="+classId+" and vsm.examTypeId="+examTypeId+" and vsc.accountId in "+selectedStudentIds+" and vsm.status='Y' and vsm.studDiscontinueDesc is null and (mobileNumber is not null or secondarymobileNumber is not null) group by vsc.studId order by sum(vsm.obtainedMarks) desc");
			log.debug(query.toString());
			List<Object[]> studentsList = super.getAll(query.toString());
			if (ObjectFunctions.isNotNullOrEmpty(studentsList)) {
				Messages message = null;
				//SMSServiceProviders smsprovider = (SMSServiceProviders) super.get(SMSServiceProviders.class, "activeUrl='Y'");
				long userId = (Long) keys.get("userId");
				double rank=0;
				int rankNumber=1;
				for (Object[] obje : studentsList) {
					try {
						Set<String> mobileNumbersSet = new HashSet<String>();
						message = new Messages(customer, smsServiceProvider, academicYear,"SMS", "Send Marks To Mobile",Constants.MODIFY_STATUS);
//		Double avgMarks = null;
						Object[] grade = null;
						firstName = obje[0].toString();
						if("B".equalsIgnoreCase(mobileType)){
							if("R".equalsIgnoreCase(obje[9].toString())){
								if(!ObjectFunctions.isNullOrEmpty(obje[6]))
									mobileNumbers = obje[6].toString();
								if(!ObjectFunctions.isNullOrEmpty(obje[5]))
									mobileNumber = obje[5].toString();
							}else{
								if(!ObjectFunctions.isNullOrEmpty(obje[8]))
									mobileNumbers = obje[8].toString();
								if(!ObjectFunctions.isNullOrEmpty(obje[7]))
									mobileNumber = obje[7].toString();
							}
							
							mobileNumbersSet.addAll(addMobileNumberBasedOnSettings(mobileType,mobileNumber,mobileNumbers));
							
							/*if(mobileNumbers.length()==10 || mobileNumber.length()==10)
								mobileNumbersSet.addAll(addMobileNumberBasedOnSettings(mobileType,mobileNumber,mobileNumbers));
							else{
								if(mobileNumbers.length()!=10)
									invalidMobileNum.append(obje[1].toString()+":"+mobileNumbers+",");
								else if(mobileNumber.length()!=10)
									invalidMobileNum.append(obje[1].toString()+":"+mobileNumber+",");
							}*/
							mobileNumber = null;
							mobileNumbers = null;
						}else{
							if("R".equalsIgnoreCase(obje[7].toString())){
								if(!ObjectFunctions.isNullOrEmpty(obje[5])){
									if(obje[5].toString().length()==10)
										mobileNumbersSet.add(obje[5].toString());
									else
										invalidMobileNum.append(obje[1].toString()+":"+obje[5].toString()+",");
								}
							}else{
								if(!ObjectFunctions.isNullOrEmpty(obje[6]))
									if(obje[6].toString().length()==10)
										mobileNumbersSet.add(obje[6].toString());
									else
										invalidMobileNum.append(obje[1].toString()+":"+obje[6].toString()+",");
							    }
  }
						/*if(!ObjectFunctions.isNullOrEmpty(obje[5])){
							if(!ObjectFunctions.isNullOrEmpty(obje[6]))
								mobileNumbers = obje[6].toString();
							mobileNumbersSet = this.addMobileNumberBasedOnSettings(customer.getMobileType(),obje[1].toString(),mobileNumbers);
							//mobileNumbers = obje[1].toString();
							//mobileNumbersSet.add(mobileNumbers);
						}*/
//	admissionNumber = obje[2].toString();
						studId = Long.valueOf(obje[2].toString());
						//message.setCustomer(customer);
						if (ObjectFunctions.isNotNullOrEmpty(mobileNumbersSet)) {
							StringBuffer studentMarksStr = new StringBuffer("select et.examType,DATE_FORMAT(es.startDate, '%d-%b-%Y') as startDate,ss.description,ss.name,sm.present,sum((sm.obtainedMarks + sm.moderationMarks)) as totalSubjectMarksObtained,sum(es.maxMarks) as subjectTotalMarks from studentMarks sm ");
							studentMarksStr.append("JOIN student s ON (sm.studId=s.id and s.status='Y' and s.description is null) JOIN examSchedules es ON (sm.examScheduleId = es.Id and es.custId=s.custId and es.academicYearId=s.academicYearId) ");
							studentMarksStr.append("JOIN examTypes et ON (et.id=es.examTypeId)").append("JOIN studySubject ss ON (ss.id = es.classSubjectId)").append("where s.classSectionId="+ classId + " and et.id="+ examTypeId + " and sm.studId="+ studId+ " group by ss.id");
							log.debug(studentMarksStr.toString());
							List<Object[]> viewStudentMarksDetailsList = super.getAll(studentMarksStr.toString());
							/*where custId=" + customer.getId()+ " and academicYearId="+ academicYear.getId()+ " and classNameClassId="+ classId + " and examTypeId="+ examTypeId + " and studId="+ studId+ " group by subjectId");
							List<ViewStudentMarksDetails> viewStudentMarksDetailsList = super.getAll(ViewStudentMarksDetails.class,"sm.custId=" + customer.getId()+ " and academicYearId="+ academicYear.getId()+ " and classSectionId="+ classId + " and examTypeId="+ examTypeId + " and s.id="+ studId+ " group by ss.id");
							//List<Object> viewStudentMarksDetailsList = super.getAll("select examType,examScStartDate,subjectShortName,subjectName,subjectShortName,present,sum((obtainedMarks + moderationMarks)) AS totalSubjectMarksObtained,sum(es.maxMarks) AS subjectTotalMarks from vw_studentMarksDetails where custId=" + customer.getId()+ " and academicYearId="+ academicYear.getId()+ " and classSectionId="+ classId + " and examTypeId="+ examTypeId + " and studId="+ studId+ " group by subjectId");
*/
							if (!ObjectFunctions.isNullOrEmpty(viewStudentMarksDetailsList)) {
								double total = 0;
								buffer = new StringBuffer();
								long count = 1;
								int totalSubjMarks = 0;
								double percentage = 0;
								int studentPresentCount = 0;
								String examType = null;
								String examScheduleStartDate = null;
								for (Object[] studentMarksDetails : viewStudentMarksDetailsList) 
								{
									if (count == 1) {
										buffer.append("Dear Parent, Your child " + firstName +" "+ studentMarksDetails[0].toString()+ " ("+studentMarksDetails[1].toString()+") marks are ");
										examType = studentMarksDetails[0].toString();
										examScheduleStartDate = studentMarksDetails[1].toString();
									}
									if (StringFunctions.isNullOrEmpty(studentMarksDetails[2].toString())) {
										if (studentMarksDetails[3].toString().length() <= 3) {
											buffer.append(studentMarksDetails[3].toString());
										} else {
											buffer.append(studentMarksDetails[3].toString().toUpperCase().substring(0, 3));
										}
									} else {
										buffer.append(studentMarksDetails[2].toString());
									}
									//if("Y".equalsIgnoreCase(studentMarksDetails[4].toString()))
									if("Y".equalsIgnoreCase(studentMarksDetails[4].toString()) || Double.valueOf(studentMarksDetails[5].toString()) > 0)
									{
										if(Double.valueOf(studentMarksDetails[5].toString())%1==0)
											buffer.append(":"+Double.valueOf(studentMarksDetails[5].toString()).intValue());
										else
											buffer.append(":"+Double.valueOf(studentMarksDetails[5].toString()));
									}
									else
									{
										studentPresentCount++;
										buffer.append(":A");
									}
										
									total += Double.valueOf(studentMarksDetails[5].toString());
									totalSubjMarks += Double.valueOf(studentMarksDetails[6].toString());

									if (count < viewStudentMarksDetailsList.size()) {
										buffer.append(",");
									}
									count++;
								}
								percentage = (total / totalSubjMarks) * 100;
								
								if(studentPresentCount != viewStudentMarksDetailsList.size())
								{
									if("R".equalsIgnoreCase(gradeType)){
										buffer.append(". TOTAL : "+Math.round(Double.valueOf(obje[3].toString()))+"/"+Math.round(Double.valueOf(obje[4].toString())));
										if(rank == 0 || rank == Double.valueOf(obje[3].toString())){
											log.debug("Student name and rank "+firstName +"rank :"+rankNumber);
										}else{
											rankNumber++;
											log.debug("Student name and rank "+firstName +"rank :"+rankNumber);
										}
										//buffer.append(". Percentage : ").append(df.format(percentage));
										buffer.append(" & Rank "+rankNumber);
									}else if("G".equalsIgnoreCase(gradeType)){
										buffer.append(". Percentage : ").append(df.format(percentage));
										grade = super.get("select id,gradeName from overAllGrades where custId="+ customer.getId()+ " and maxmarks >="+ percentage+ " and minmarks <="+ percentage+ " and academicYearId="+ academicYear.getId());
										if (!ObjectFunctions.isNullOrEmpty(grade) && !ObjectFunctions.isNullOrEmpty(grade[1])) {
											buffer.append(" & Grade "+ grade[1].toString());
										}
									}
									else{
										buffer.append(". TOTAL : "+Math.round(Double.valueOf(obje[3].toString()))+"/"+Math.round(Double.valueOf(obje[4].toString())));
										buffer.append("& Average : ").append(df.format(percentage));
									}
								}
								else
								{
									buffer = new StringBuffer();
									//buffer.append("Your child <V1> was absent for <V2> exam conducted on <V3>. Thank you from <V4>");
									buffer.append("Dear Parent, Your child "+firstName+" was absent for "+examType+" exam conducted on "+examScheduleStartDate+".");
								}
								
								buffer.append(".Thank you from ");
								message.setMessageDescription(buffer.toString());
								message.setStatus(Constants.MODIFY_STATUS);
								message.setCreatedById(userId);
								message.setSentSms(mobileNumbersSet.size());
								message.setAcademicYear(academicYear);
								message.setMobileNumbers(StringUtil.convertSetToString(mobileNumbersSet));
								student = (Student) this.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and id="+studId);
								message = this.saveMessageDetailsTracking(message, student, null, null);
								smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProvider);
								if (smsStatus) {
									smsSuccess = true;
								} else{
									failedMsgs.append(firstName + ",");
								}
							}
						}else{
							if(rank != Double.valueOf(obje[3].toString()))
								rankNumber++;
						}
						mobileNumbersSet = null;
						student = null;
						message = null;
						//mobileNumbers=null;
						rank=Double.valueOf(obje[3].toString());
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
			if (failedMsgs.length() > 1) {
				failedMsgs.append(")");
				//String msgdel = failedMsgs.toString().replace(",)", ")"+"Marks has not been delivered.");
				msg.put("1",failedMsgs.toString().replace(",)", ")"+" Marks has not been delivered."));
			} else if(smsSuccess) {
				msg.put("0","Marks has been delivered successfully.");
			}
			if(invalidMobileNum.length()>1){
				String invalidMob = invalidMobileNum.toString();
				msg.put("2",invalidMob+" Invalid Mobile Numbers.");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return msg;
	}
	
    public Set prepareParentCommunicationDetails(Messages message, long custId, long academicId,User user,List<String> accountId,String hostlerStatus,StringBuffer parentQuery,String mobileType){
		try{
			Set<String> mobileNumberset = new HashSet<String>();
			 if ("P".equalsIgnoreCase(message.getReceiverType()) ||  "A".equalsIgnoreCase(message.getReceiverType()) || "PTA".equalsIgnoreCase(message.getReceiverType()) || "AP".equalsIgnoreCase(message.getReceiverType()) || "AA".equalsIgnoreCase(message.getReceiverType())){
				StringBuffer query = new StringBuffer();
				List<Object[]> allUsersList = null;
				List allUserstrList = null;
				if ("M".equalsIgnoreCase(message.getStatus())){
					 query.append("select ");
					 if("B".equalsIgnoreCase(mobileType)){
						 query.append("mobileNumber,secondaryMobileNumber,anotherMobileNumber,anotherSecondaryMobileNumber ");
					 }else if("P".equalsIgnoreCase(mobileType)){
						 query.append("mobileNumber,anotherMobileNumber ");
					 }else
						 query.append("secondaryMobileNumber,anotherSecondaryMobileNumber ");
					 
					  query.append(" ,addressType from vw_studentDetails where (mobileNumber!=0 AND mobileNumber is not null AND mobileNumber !='+91-0000000000')");
					 parentQuery.append("FROM Student s WHERE s.custId="+custId+" and s.academicYear="+academicId+" and s.account IN (SELECT a.id FROM User a WHERE a.custId="+custId+" and a.person in (select p.id from Person p where (p.mobileNumber!=0 AND p.mobileNumber is not null AND p.mobileNumber !='+91-0000000000'))");
					/* parentQuery.append("select s.custId as custId,s.academicYearId as academicYear,s.accountId as account from student s LEFT JOIN Account a on (a.id = s.accountId and  a.custId=s.custId)")																		
						.append(" LEFT JOIN Person p on(p.id = a.personId) where s.custId=").append(custId)
						.append(" and s.academicYearId=").append(academicId)
						.append(" and (p.mobileNumber!=0 AND p.mobileNumber is not null AND p.mobileNumber !='+91-0000000000')");	*/
				}else
					query.append("select parentEmail  from vw_studentDetails where (parentEmail != '' AND parentEmail is not null)");
				query.append(" and status='Y' and custId=");
				query.append(custId);
				if(!ObjectFunctions.isNullOrEmpty(accountId)){
					String commaDelimitedString = StringUtil.convertListToString(accountId);
					if(!ObjectFunctions.isNullOrEmpty(commaDelimitedString)){
						query.append(" and parentId in(").append(commaDelimitedString).append(")");
						//parentQuery.append(" and a.parentId in(").append(commaDelimitedString).append(")");
						parentQuery.append(" and a.studentParent in (select id from Account pa where pa.id in(").append(commaDelimitedString).append("))");
					}
				}
				query.append(" and academicYearId=");
				query.append(academicId);
				if("PTA".equalsIgnoreCase(message.getReceiverType())){
					query.append(" and ptaStatus='Y'");
					parentQuery.append("  and s.ptaStatus='Y'");
				}
				if(user.isSchoolHostel() || user.isHostelFinance()){
					query.append(" and bedId != 0");
					parentQuery.append(" and s.bedId != 0");
				}else if (user.isSchoolTransport() || user.isTransportFinance()) {
					query.append(" and transportMode='"+Constants.TRANSPORT_STATUS+"'");
					parentQuery.append(" and s.transportMode='"+Constants.TRANSPORT_STATUS+"'");
				}
				if (("P".equalsIgnoreCase(message.getReceiverType())  || "AP".equalsIgnoreCase(message.getReceiverType())  || "AA".equalsIgnoreCase(message.getReceiverType())) && StringFunctions.isNotNullOrEmpty(hostlerStatus)){
					query.append(" and hostelMode='"+hostlerStatus+"'");
					parentQuery.append(" and s.hostelMode='"+hostlerStatus+"'");
				}
				parentQuery.append(")");
				//log.debug("parentQuery: "+parentQuery);
				if("M".equalsIgnoreCase(message.getStatus()))
					allUsersList = this.getAll(query.toString());
				else
					allUserstrList = this.getAll(query.toString());
				if (!ObjectFunctions.isNullOrEmpty(allUsersList)){
					 for(Object[] obj : allUsersList){
						String	mobileNumbers = null;
						String	mobileNumber = null;
						if("B".equalsIgnoreCase(mobileType) && "M".equalsIgnoreCase(message.getStatus())){
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
							if(!ObjectFunctions.isNullOrEmpty(obj[2])){
								if("R".equalsIgnoreCase(obj[2].toString())){
									if(!ObjectFunctions.isNullOrEmpty(obj[0]))
										mobileNumberset.add(obj[0].toString());
								}else{
									if(!ObjectFunctions.isNullOrEmpty(obj[1]))
										mobileNumberset.add(obj[1].toString());
								}
							}
						}
					}
				}else if(!ObjectFunctions.isNullOrEmpty(allUserstrList)){
					mobileNumberset.addAll(allUserstrList);
				}
				allUsersList = null;
				allUserstrList = null;
				query = null;
			}
			if(ObjectFunctions.isNotNullOrEmpty(mobileNumberset)){
				return mobileNumberset;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * May 28, 2013      Seshu			    status is not storing in messages table because of
 * 										message object is overridden with with new message object.
/********************************************************************/	
    public Map<String,String>  sendSchoolWideMessages(Messages messageInfo, long custId, AcademicYear academicYear,User user,List<String> selectedIds, List<String> selectedAccountIds,List<String> selectedClassIds, String trStatus,List<String> fileUploadList,Set<String> mobileNumbersSet,String hostlerStatus,String otherMessageSalutation) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering CommunicationManager 'sendSchoolWideMessages' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try {
			Customer customer = this.getCustomerByCustId(custId);
			Messages message = null;
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				if (customer.isCheckEmailService() || customer.isCheckMobileService()) {
					SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
					if (!ObjectFunctions.isNullOrEmpty(messageInfo)) {
						if (!ObjectFunctions.isNullOrEmpty(customer)) {
							messageInfo.setCustomer(customer);
							messageInfo.setSmsSenderId(customer.getSender());
						}
						StringBuffer smsContent;
						boolean smsStatus = false;
						boolean emailStatus = false;
						boolean smsOptSelected = false;
						boolean emailOptSelected = false;
						boolean emailIds = false;
						int returnCode =0;
						List<Student> studentList = null;
						//List<String> accountIds = null;
						StringBuffer studentQuery= null;
						StringBuffer staffQuery= null;
						if (!ObjectFunctions.isNullOrEmpty(user)) {
							messageInfo.setCreatedById(user.getId());
							messageInfo.setCreatedDate(new Date());
							messageInfo.setLastAccessDate(new Date());
							messageInfo.setSenderName(user.getUserRoleDescription());
						}
						messageInfo.setPurposeType(messageInfo.getTitle());
						messageInfo.setAcademicYear(academicYear);
						messageInfo.setMessageContent(messageInfo.getMessageDescription());
						if (!ObjectFunctions.isNullOrEmpty(selectedIds)) {
							int availableSmsCount=this.getAvailableSmsCount(custId,academicYear.getId());
							for (String chBox : selectedIds) {
								messageInfo.setStatus(chBox);
								if ("M".equalsIgnoreCase(messageInfo.getStatus())) {
									smsOptSelected = true;
									log.debug("----sms block----"+chBox);
									if(customer.isCheckMobileService() && availableSmsCount > 0){
										Set mobileNumbers = null;
										Set<String> otherMobileNumbers = null;
										if ("P".equalsIgnoreCase(messageInfo.getReceiverType()) || 
												"A".equalsIgnoreCase(messageInfo.getReceiverType()) || "PTA".equalsIgnoreCase(messageInfo.getReceiverType()) || "AP".equalsIgnoreCase(messageInfo.getReceiverType()) || "AA".equalsIgnoreCase(messageInfo.getReceiverType())){
											/** System can't send the free form text fields, always
											 * the text must be formated according to the template
											**/		
											studentQuery= new StringBuffer();
											mobileNumbers = this.prepareParentCommunicationDetails(messageInfo,customer.getId(),academicYear.getId(),user,selectedAccountIds,hostlerStatus,studentQuery,customer.getMobileType());
											selectedAccountIds = null;
											if(ObjectFunctions.isNotNullOrEmpty(mobileNumbers)){
												smsContent= new StringBuffer();
												smsContent.append("Dear Parent, ");
												smsContent.append(messageInfo.getMessageContent());
												smsContent.append(" Thank you from ");
												messageInfo.setMessageDescription(smsContent.toString());
												message = new Messages();
												message.copyFrom(messageInfo);
												//studentList = this.getStudentList(studentQuery);
												message = this.addMessageDetailsTracking(message,null,null,null);																								
												smsStatus =this.deliverSms(message,mobileNumbers,smsServiceProvider);
												studentList = null;
												studentQuery=null;
											} 
										}																			
										if ("S".equalsIgnoreCase(messageInfo.getReceiverType()) || 
												"A".equalsIgnoreCase(messageInfo.getReceiverType()) || "AS".equalsIgnoreCase(messageInfo.getReceiverType()) || "AA".equalsIgnoreCase(messageInfo.getReceiverType())){ 
												List<Staff> staffList = null;
												staffQuery= new StringBuffer();
												mobileNumbers = this.prepareStaffCommunicationDetails(messageInfo,custId,academicYear.getId(),user,selectedAccountIds,staffQuery);
												selectedAccountIds = null;
												if(ObjectFunctions.isNotNullOrEmpty(mobileNumbers)){
													smsContent= new StringBuffer();
													if("VP".equalsIgnoreCase(messageInfo.getStaffType()))
														smsContent.append("Dear Principal, ");
													else if("MGT".equalsIgnoreCase(messageInfo.getStaffType()))
														smsContent.append("Dear Management, ");
													else
														smsContent.append("Dear Staff, ");
													smsContent.append(messageInfo.getMessageContent());
													smsContent.append(" Thank you from ");
													messageInfo.setMessageDescription(smsContent.toString());
													message = new Messages();
													message.copyFrom(messageInfo);	
													staffList = this.getStaffList(staffQuery);
													message = this.addMessageDetailsTracking(message, null,staffList,null);
													smsStatus = this.deliverSms(message,mobileNumbers,smsServiceProvider);
													staffQuery=null;
												}
										}
										if(ObjectFunctions.isNotNullOrEmpty(selectedClassIds)){		
											studentQuery= new StringBuffer();
											mobileNumbers = this.GetStudentMobileNumbers(custId, academicYear.getId(), selectedClassIds,null,hostlerStatus,studentQuery,customer.getMobileType());
											if(ObjectFunctions.isNotNullOrEmpty(mobileNumbers)){
												smsContent= new StringBuffer();
												smsContent.append("Dear Parent, ");
												smsContent.append(messageInfo.getMessageContent());
												smsContent.append(" Thank you from ");
												messageInfo.setMessageDescription(smsContent.toString());
												message = new Messages();
												message.copyFrom(messageInfo);
												studentList = this.getStudentList(studentQuery);
												message = this.addMessageDetailsTracking(message,studentList,null,null);		
												smsStatus = this.deliverSms(message,mobileNumbers,smsServiceProvider);
												studentList = null;
												studentQuery=null;
											}
										}
										if(ObjectFunctions.isNotNullOrEmpty(selectedAccountIds)){
											List<String> studIds = null;
												studentQuery= new StringBuffer();
												mobileNumbers = this.GetStudentMobileNumbers(custId, academicYear.getId(), null,selectedAccountIds,hostlerStatus,studentQuery,customer.getMobileType());
												if(ObjectFunctions.isNotNullOrEmpty(mobileNumbers)){
													smsContent= new StringBuffer();
													smsContent.append("Dear Parent, ");
													smsContent.append(messageInfo.getMessageContent());
													smsContent.append(" Thank you from ");
													messageInfo.setMessageDescription(smsContent.toString());
													message = new Messages();
													message.copyFrom(messageInfo);
													String commaDelimitedString = StringUtil.convertListToString(selectedAccountIds);
													studIds = this.getAll("select GROUP_CONCAT(DISTINCT(s.id)) from student s join studentparent sp on(s.accountId=sp.studentAccountId) join Account a on(a.id = s.accountId) join Person p on(p.id = a.personId) where s.custId="+customer.getId()+" and s.status = 'Y' and s.academicYearId="+academicYear.getId()+" and p.mobileNumber!=0 and p.mobileNumber is not null and p.mobileNumber !='+91-0000000000' and sp.parentAccountId in ("+commaDelimitedString+")");
													String commaDelimitedStringOfStudent = StringUtil.convertListToString(studIds);
													studentList = this.getAll(Student.class, "id in("+commaDelimitedStringOfStudent+")");
													message = this.addMessageDetailsTracking(message,studentList,null,null);
													smsStatus = this.deliverSms(message,mobileNumbers,smsServiceProvider);
													studentList = null;
													studentQuery=null;
											}
										}
										if ("O".equalsIgnoreCase(messageInfo.getReceiverType())){
											smsContent= new StringBuffer();
											message = new Messages();
											if("Student".equalsIgnoreCase(messageInfo.getOtherType()))
												smsContent.append("Dear Parent, ");
											else if("Staff".equalsIgnoreCase(messageInfo.getOtherType()))
												smsContent.append("Dear Staff, ");
											else if("OtherMember".equalsIgnoreCase(messageInfo.getOtherType())){
												smsContent.append("Dear ");
												if(!StringFunctions.isNullOrEmpty(otherMessageSalutation))
													smsContent.append(otherMessageSalutation+", ");
											}
											smsContent.append(messageInfo.getMessageContent());
											smsContent.append(" Thank you from ");
											messageInfo.setMessageDescription(smsContent.toString());
											message.copyFrom(messageInfo);
											
											if(ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
												otherMobileNumbers=messageInfo.getOtherContactDetails();
												if(ObjectFunctions.isNotNullOrEmpty(otherMobileNumbers)){
													message = this.addMessageDetailsTracking(message, null, null,otherMobileNumbers);
													smsStatus = this.deliverSms(message,otherMobileNumbers,smsServiceProvider);
												}
											}else if(ObjectFunctions.isNotNullOrEmpty(mobileNumbersSet)){
												message = this.addMessageDetailsTracking(messageInfo, null, null,mobileNumbersSet);
												smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProvider);
											}
											
											/*if(ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
												otherMobileNumbers=messageInfo.getOtherContactDetails();
												if(ObjectFunctions.isNotNullOrEmpty(otherMobileNumbers)){													
													if("Student".equalsIgnoreCase(messageInfo.getOtherType()))
														smsContent.append("Dear Parent, ");
													else if("Staff".equalsIgnoreCase(messageInfo.getOtherType()))
														smsContent.append("Dear Staff, ");
													else if("OtherMember".equalsIgnoreCase(messageInfo.getOtherType())){
														smsContent.append("Dear ");
														if(!StringFunctions.isNullOrEmpty(otherMessageSalutation))
															smsContent.append(otherMessageSalutation+", ");
													}
													smsContent.append(messageInfo.getMessageContent());
													smsContent.append(" Thank you from ");
													messageInfo.setMessageDescription(smsContent.toString());
													message.copyFrom(messageInfo);
													message = this.addMessageDetailsTracking(message, null, null,otherMobileNumbers);
													smsStatus = this.deliverSms(message,otherMobileNumbers,customer.getId());
													}	
											}
											else if(ObjectFunctions.isNotNullOrEmpty(mobileNumbersSet)){
												smsContent.append("Dear Parent, ");
												smsContent.append(messageInfo.getMessageContent());
												smsContent.append(" Thank you from ");
												messageInfo.setMessageDescription(smsContent.toString());												
												message.copyFrom(messageInfo);
												message = this.addMessageDetailsTracking(messageInfo, null, null,mobileNumbersSet);
												smsStatus = this.deliverSms(message,mobileNumbersSet,customer.getId());
											}*/
										}
									}else{
										//msg.put("6", "Circular is Saved but sms is not delivered due to insufficient sms balance");
										msg.put("6", "Data is saved but sms is not delivered due to insufficient sms balance");
									}
								}
								if ("E".equalsIgnoreCase(messageInfo.getStatus())) 
								{
									log.debug("----email block----");
									emailOptSelected = true;
									if (customer.isCheckEmailService()){
										Set<String> emailIdsSet = new HashSet<String>();
										if ("P".equalsIgnoreCase(messageInfo.getReceiverType()) || "A".equalsIgnoreCase(messageInfo.getReceiverType()) || "PTA".equalsIgnoreCase(messageInfo.getReceiverType())  || "AP".equalsIgnoreCase(messageInfo.getReceiverType()) || "AA".equalsIgnoreCase(messageInfo.getReceiverType())) {
											//* Here 'studentQuery' is not using for E-Mail this is using only for message details tracking sms 
											studentQuery= new StringBuffer();
											emailIdsSet = this.prepareParentCommunicationDetails(messageInfo,custId,academicYear.getId(),user,selectedAccountIds,hostlerStatus,studentQuery,customer.getMobileType());
											if(ObjectFunctions.isNotNullOrEmpty(emailIdsSet)){
												returnCode = this.deliverEmails(messageInfo.getEmailContent(),messageInfo.getTitle(),emailIdsSet,user,fileUploadList,customer);
												emailIds = true;
											}
											studentQuery=null;
										} //else{
										if ("S".equalsIgnoreCase(messageInfo.getReceiverType()) || "A".equalsIgnoreCase(messageInfo.getReceiverType())  || "AS".equalsIgnoreCase(messageInfo.getReceiverType()) || "AA".equalsIgnoreCase(messageInfo.getReceiverType()))  {
											//* Here 'staffQuery' is not using for E-Mail this is using only for message details tracking sms 
											staffQuery= new StringBuffer();
											emailIdsSet = this.prepareStaffCommunicationDetails(messageInfo,custId,academicYear.getId(),user,selectedAccountIds,staffQuery); 
											if(ObjectFunctions.isNotNullOrEmpty(emailIdsSet)){	
												returnCode = this.deliverEmails(messageInfo.getEmailContent(),messageInfo.getTitle(),emailIdsSet,user,fileUploadList,customer);
												emailIds = true;
											}
											staffQuery=null;
										}
										if ("O".equalsIgnoreCase(messageInfo.getReceiverType())){
											//if ("O".equalsIgnoreCase(messageInfo.getReceiverType()) || "Staff".equalsIgnoreCase(messageInfo.getReceiverType())) {
											emailIdsSet=messageInfo.getOtherContactDetails();
											if(ObjectFunctions.isNotNullOrEmpty(emailIdsSet)){	
												returnCode = this.deliverEmails(messageInfo.getEmailContent(),messageInfo.getTitle(),emailIdsSet,user,fileUploadList,customer);
												emailIds = true;
											}
										}
										if(ObjectFunctions.isNotNullOrEmpty(selectedClassIds)){
											emailIdsSet = prepareEmailIdsForClasses(selectedClassIds,custId, academicYear.getId(), user);
											if(ObjectFunctions.isNotNullOrEmpty(emailIdsSet)){
												returnCode = this.deliverEmails(messageInfo.getEmailContent(),messageInfo.getTitle(),emailIdsSet,user,fileUploadList,customer);
												emailIds = true;
											}
										}
										if(ObjectFunctions.isNotNullOrEmpty(selectedAccountIds)){
											emailIdsSet = prepareEmailIdsForAccountIds(selectedAccountIds,custId, academicYear.getId(),user);
											if(ObjectFunctions.isNotNullOrEmpty(emailIdsSet)){
												returnCode = this.deliverEmails(messageInfo.getEmailContent(),messageInfo.getTitle(),emailIdsSet,user,fileUploadList,customer);
												emailIds = true;
											}
										}
										//}
										//emailStatus = deliverEmails(message.getMessageDescription(),title,emailIdsSet);
										if(returnCode == 0 && emailIds)
											emailStatus =true;
										else{
											if(returnCode==1)
												msg.put("2", "From email address or password wrong. E-Mail(s) has not been delivered.");
											else if(returnCode==2)
												msg.put("3", "E-Mail(s) has not been delivered. Connection timed out");
											else if(returnCode==3)
												msg.put("4", "E-Mail(s) has not been delivered. System error occurred. Please contact customer support!");
											else if(!emailIds)
												msg.put("5", "E-mail(s) is not available.E-Mail(s) has not been delivered.");
											emailStatus =false;
										}
									}
								}
							}
						}
						else if("TR".equals(trStatus) && 
									ObjectFunctions.isNotNullOrEmpty(selectedAccountIds) && 
									customer.isCheckMobileService()){
								log.debug("----transport block----");
								smsOptSelected = true;
								if(ObjectFunctions.isNotNullOrEmpty(selectedAccountIds)){
									//Set trAccountIds = userManager.GetStudentMobileNumbers(getUserCustId(), getCurrentAcademicId(),null,selectedAccountIds);
									studentQuery= new StringBuffer();
									Set trAccountIds = this.GetStudentMobileNumbers(custId, academicYear.getId(),null,selectedAccountIds,hostlerStatus,studentQuery,customer.getMobileType());
									if(ObjectFunctions.isNotNullOrEmpty(trAccountIds)){
										messageInfo.setStatus("TR");
										messageInfo.setPurposeType("Transport");
										smsContent= new StringBuffer();
										smsContent.append("Dear Parent, ");
										smsContent.append(messageInfo.getMessageContent());
										smsContent.append(" Thank you from ");
										messageInfo.setMessageDescription(smsContent.toString());
										studentList = this.getStudentList(studentQuery);
										message = this.addMessageDetailsTracking(messageInfo,studentList,null,null);
									    smsStatus = this.deliverSms(message,trAccountIds,smsServiceProvider);
									}studentQuery=null;
								}
							}
						else if("AM".equalsIgnoreCase(trStatus) && !StringFunctions.isNullOrEmpty(messageInfo.getReceiverType())){
							log.info("SMS For Absentees");
							smsOptSelected = true;
							Set<String> trAccountIds = null;
							List<String> studIds = null;
							selectedAccountIds.remove(0);
							//List<String> accountIds = null;
							if("P".equalsIgnoreCase(messageInfo.getReceiverType())){
								studentQuery= new StringBuffer();
								messageInfo.setStatus("M");
								if(!ObjectFunctions.isNullOrEmpty(selectedAccountIds))
									trAccountIds = this.prepareParentCommunicationDetails(messageInfo,customer.getId(),academicYear.getId(),user,selectedAccountIds,hostlerStatus,studentQuery,customer.getMobileType());
								if(ObjectFunctions.isNotNullOrEmpty(trAccountIds)){
									smsContent= new StringBuffer();
									smsContent.append("Dear Parent, ");
									smsContent.append(messageInfo.getMessageContent());
									smsContent.append(" Thank you from ");
									messageInfo.setMessageDescription(smsContent.toString());
									message = new Messages();
									message.copyFrom(messageInfo);
									String commaDelimitedString = StringUtil.convertListToString(selectedAccountIds);
									studIds = this.getAll("select GROUP_CONCAT(DISTINCT(s.id)) from student s join studentparent sp on(s.accountId=sp.studentAccountId) join Account a on(a.id = s.accountId) join Person p on(p.id = a.personId) where s.custId="+customer.getId()+" and s.status = 'Y' and s.academicYearId="+academicYear.getId()+" and p.mobileNumber!=0 and p.mobileNumber is not null and p.mobileNumber !='+91-0000000000' and sp.parentAccountId in ("+commaDelimitedString+")");
									String commaDelimitedStringOfStudent = StringUtil.convertListToString(studIds);
									studentList = this.getAll(Student.class, "id in("+commaDelimitedStringOfStudent+")");
								  //  studentList = this.getStudentList(studentQuery);
									message = this.addMessageDetailsTracking(message,studentList,null,null);																								
									smsStatus =this.deliverSms(message,trAccountIds,smsServiceProvider);
									studentList = null;
									selectedAccountIds = null;
									studentQuery=null;
								}
							}
							else if("S".equalsIgnoreCase(messageInfo.getReceiverType())){
								List<Staff> staffList = null;
								staffQuery= new StringBuffer();
								messageInfo.setStaffType("A");
								messageInfo.setStatus("M");
								if(!ObjectFunctions.isNullOrEmpty(selectedAccountIds))
									trAccountIds = this.prepareStaffCommunicationDetails(messageInfo,custId,academicYear.getId(),user,selectedAccountIds,staffQuery);
								if(ObjectFunctions.isNotNullOrEmpty(trAccountIds)){
									smsContent= new StringBuffer();
									smsContent.append("Dear Staff, ");
									smsContent.append(messageInfo.getMessageContent());
									smsContent.append(" Thank you from ");
									messageInfo.setMessageDescription(smsContent.toString());
									message = new Messages();
									message.copyFrom(messageInfo);	
									staffList = this.getStaffList(staffQuery);
									message = this.addMessageDetailsTracking(message, null,staffList,null);
									smsStatus = this.deliverSms(message,trAccountIds,smsServiceProvider);
									staffQuery=null;
								}
							}
						}
						if(smsOptSelected || emailOptSelected){
							if(smsOptSelected && emailOptSelected){
								if(smsStatus || emailStatus){
									if(smsStatus && emailStatus){
										msg.put("0", "E-Mail(s) and SMS(s) has been delivered successfully.");
									}else if(smsStatus){
										//msg.put("5", "E-Mail(s) has not been delivered.");
										msg.put("0", "SMS(s) has been delivered successfully.");
									}else{
										msg.put("0", "E-Mail(s) has been delivered successfully.");
										msg.put("1", "SMS(s) has not been delivered.");
									}
								}else{
									//msg.put("5", "E-Mail(s) and SMS(s) has not been delivered.");
								}
							}else if(smsOptSelected){
								if(smsStatus)
									msg.put("0","SMS(s) has been delivered successfully.");
								else
									msg.put("1", "SMS(s) has not been delivered.");
							}else{
								if(emailStatus)
									msg.put("0","E-Mail(s) has been delivered successfully.");
								else
									msg.put("5", "E-Mail(s) has not been delivered.");
							}
						}
						message = null;
						messageInfo = null;
				}
			}else {
				msg.put("1","Email or SMS services are disabled, enable services.");
			}
		}	
		}catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		
		return msg;
	}
    
    /**
	 * @Description:  Prepare Staff mobile numbers or emails id's to deliver the message
	 * @param message
	 * @return
	 */
	 /*Changed by seshu on 1st April
	  If select ALL option mobile numbers are returning. Changed query.*/
    public Set prepareStaffCommunicationDetails(Messages message, long custId, long academicYearId,User user,List<String> accountId,StringBuffer staffQuery) {
    	if (log.isDebugEnabled()) {
			log.debug("Entering CommunicationManager 'prepareStaffCommunicationDetails' method");
		}
		try {
			Set<String> mobileNumberset = new HashSet<String>();
			List allUsersList = null;
			if ("S".equalsIgnoreCase(message.getReceiverType()) || "A".equalsIgnoreCase(message.getReceiverType()) || "AS".equalsIgnoreCase(message.getReceiverType()) || "AA".equalsIgnoreCase(message.getReceiverType()) ) {
				StringBuffer query = new StringBuffer();			
				// This is very critical to identify whether SMS or email ids
				staffQuery.append("select s.id as staffId from staff s LEFT JOIN Account a on (s.accountId= a.id and s.custId= a.custId)  LEFT JOIN Person p on (a.personId= p.id) LEFT JOIN UserRoles ur on (a.id = ur.userId )");
					if ("M".equalsIgnoreCase(message.getStatus()))
					if("CT".equalsIgnoreCase(message.getStaffType()))
					{
						query.append("select distinct(mobileNumber) from vw_classSectionTeacher where (mobileNumber!=0 AND mobileNumber is not null AND mobileNumber !='+91-0000000000') and status='Y' and classTeacher='Y' and academicYearId<="+academicYearId +" and custId=");
						staffQuery.append(" JOIN  classTeacher ct on (ct.teacherId=s.id and ct.classTeacher='Y')");
					}
					else
					{
						query.append("select distinct(mobileNumber) from vw_staffDetails where (mobileNumber!=0 AND mobileNumber is not null AND mobileNumber !='+91-0000000000')  and status='Y' and academicYearId<="+academicYearId+" and custId=");						
					}
				else
					query.append("select email from vw_staffDetails where (email != ''  AND email is not null) and status='Y' and academicYearId<="+academicYearId+" and custId=");
				
				//query.append(" and status='Y' and academicYearStatus='Y' and custId=");
				query.append(custId);
				staffQuery.append(" where s.custId=").append(custId).append(" and s.academicYearId=").append(academicYearId);
				staffQuery.append(" and (p.mobileNumber!=0 AND p.mobileNumber is not null AND p.mobileNumber !='+91-0000000000')");
				if(!ObjectFunctions.isNullOrEmpty(accountId)){
					String commaDelimitedString = StringUtil.convertListToString(accountId);
					if(!ObjectFunctions.isNullOrEmpty(commaDelimitedString)){
						query.append(" and accountId in (").append(commaDelimitedString).append(")");
						staffQuery.append(" and s.accountId in (").append(commaDelimitedString).append(")");
					}
				}
				if(user.isSchoolHostel() || user.isHostelFinance()){
					query.append(" and bedId != 0");
					staffQuery.append(" and s.bedId != 0");
				}
				if (StringFunctions.isNotNullOrEmpty(message.getStaffType())) {
					
					if("A".equalsIgnoreCase(message.getStaffType())  || "AA".equalsIgnoreCase(message.getReceiverType())){
						    query.append(" and roleId not in (3,7)");
						staffQuery.append(" and ur.roleId not in (3,7)");
						
					}
					if ("T".equalsIgnoreCase(message.getStaffType())  || "AS".equalsIgnoreCase(message.getReceiverType())) {
						        query.append(" and roleId in (2,8,12,31,35,53)");
						staffQuery.append(" and ur.roleId in (2,8,12,31,35,53)");
					}
					if ("NT".equalsIgnoreCase(message.getStaffType())  || "AN".equalsIgnoreCase(message.getReceiverType())) {
						        query.append(" and roleId in (1,4,5,6,9,10,11,14,15,16,17,18,19,20,21,22,23,24,25,30,32,33,38,41,42,43,44,45,46,54,55,56)");
						staffQuery.append(" and ur.roleId in (1,4,5,6,9,10,11,14,15,16,17,18,19,20,21,22,23,24,25,30,32,33,38,41,42,43,44,45,46,54,55,56)");
					}
					if ("VP".equalsIgnoreCase(message.getStaffType())  || "AS".equalsIgnoreCase(message.getReceiverType())) { //here we are getting  vice principal(VP),principal roles
						       query.append(" and roleId in (12,31)");
						staffQuery.append(" and ur.roleId in (12,31)");
					}
				}
				staffQuery.append(" GROUP BY a.id");
				allUsersList = this.getAll(query.toString());
				//log.debug(query.toString());
				if (!ObjectFunctions.isNullOrEmpty(allUsersList)) {
					//log.debug("mobile size:" +allUsersList.size());
					mobileNumberset.addAll(allUsersList);
				}
				allUsersList = null;
				query = null;
			}

			if (ObjectFunctions.isNotNullOrEmpty(mobileNumberset)) {
				return mobileNumberset;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /** @param msgDesc
     * @param title
     * @param emailIds
     * @return
     * @Description 27th March Sreeram: Refactored the code to use the new way
     *              of sending emails out */
    public int deliverEmails(String msgDesc, String title, Set emailIds,User user,List<String> fileUploadList,Customer customer) {
    	int returnCode = 0;
		try {
		    if (!ObjectFunctions.isNullOrEmpty(emailIds)) {
			int i = 0;
			String[] emailAdd = new String[emailIds.size()];
			String[] fileUploadStr = null;
			
			for (Object emailId : emailIds) {
			    if (!ObjectFunctions.isNullOrEmpty(emailId)) {
				emailAdd[i] = emailId.toString();
				emailId = null;
			    }
			    i++;
			}
			if(!ObjectFunctions.isNullOrEmpty(fileUploadList))
			{
				i = 0;
				fileUploadStr = new String[fileUploadList.size()];
				for(String fileupload : fileUploadList)
				{
					if (!ObjectFunctions.isNullOrEmpty(fileupload)) {
						fileUploadStr[i] = fileupload.toString();
						fileupload = null;
					  }
					  i++;
				}
			}
			MailUtil mailUtil = new MailUtil(emailAdd, title, msgDesc, user,fileUploadStr,this.getContactFromEmail(customer));
			emailAdd = null;
			mailUtil.setContactEmail(customer.getContactEmail());
			mailUtil.setContactPasword(customer.getContactPassword());
			returnCode = mailUtil.sendEmailToSchoolWideMessages(this.getContactFromEmail(customer));
			mailUtil = null;
			return returnCode;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    returnCode =3;
		}
		return returnCode;
    }
    
    public Set<String> prepareEmailIdsForClasses(List<String> classIds,long custId, long academicYearId, User user){
		
		try{
			List<StudyClass> studyClassList = null;
			List studentsList=null;
			Set<String> emailIdsSet = new HashSet<String>();
			StringBuffer queryString=null;
			if(ObjectFunctions.isNotNullOrEmpty(classIds)){
				String commaDelimitedString = StringUtil.convertListToString(classIds);
				studyClassList = this.getAll(StudyClass.class, " id in (" + commaDelimitedString + ")");
				if(ObjectFunctions.isNotNullOrEmpty(studyClassList)){
					for (StudyClass studyClass : studyClassList) {
						queryString=new StringBuffer();
						queryString.append("select parentEmail from vw_studentClassDetails where classSectionId="+studyClass.getId()+" and academicYearId="+academicYearId+" and custId="+custId+" and status='Y' and (parentEmail!='' AND parentEmail is not null)");
						if(user.isSchoolHostel() || user.isHostelFinance()){
							queryString.append(" and bedId !=0");
						}else if (user.isSchoolTransport() || user.isTransportFinance()){
							queryString.append(" and transportMode='"+Constants.TRANSPORT_STATUS+"'");
						}
						studentsList=this.getAll(queryString.toString());
						if(!ObjectFunctions.isNullOrEmpty(studentsList)){
							if(!ObjectFunctions.isNullOrEmpty(studentsList)){
								emailIdsSet.addAll(studentsList);
								studentsList= null;
							}
						}
						queryString=null;
					}
				}
			}
			
			if(ObjectFunctions.isNotNullOrEmpty(emailIdsSet)){
				return emailIdsSet;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public Set<String> prepareEmailIdsForAccountIds(List<String> accountIds,long custId, long academicYearId, User user){
		
		try{
			
			Set<String> emailIdsSet = new HashSet<String>();
			StringBuffer queryString=null;
			List studentsList=null;
			if (ObjectFunctions.isNotNullOrEmpty(accountIds)) {
				String commaDelimitedString = StringUtil.convertListToString(accountIds);
				queryString=new StringBuffer();
				queryString.append("select parentEmail from vw_studentClassDetails where academicYearId="+ academicYearId+ " and custId="+ custId+ " and status in ('Y','S','B') and (parentEmail!='' AND parentEmail is not null) and accountId in ("+ commaDelimitedString + ")");
				if(user.isSchoolHostel() || user.isHostelFinance()){
					queryString.append(" and bedId != 0");
				}else if (user.isSchoolTransport() || user.isTransportFinance()) {
					queryString.append(" and transportMode='"+Constants.TRANSPORT_STATUS+"' ");
				}
				studentsList = this.getAll(queryString.toString());
				if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
					emailIdsSet.addAll(studentsList);
					studentsList = null;
				}
			}
			if(ObjectFunctions.isNotNullOrEmpty(emailIdsSet)){
				return emailIdsSet;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    public Set prepareParentCredentialDetails(Messages message, long custId, long academicId,long accountId,String mobileType){
		try{
			Set<String> mobileNumberset = new HashSet<String>();
			StringBuffer query = new StringBuffer();
			List<Object[]> allUsersList = null;
			List allUserstrList = null;
			if ("M".equalsIgnoreCase(message.getStatus())){
				query.append("select");
				if("B".equalsIgnoreCase(mobileType)){
					query.append(" mobileNumber,secondaryMobileNumber ");
				}else if("P".equalsIgnoreCase(mobileType))
					query.append(" mobileNumber ");
				else
					query.append(" secondaryMobileNumber ");
			}
			if ("P".equalsIgnoreCase(message.getReceiverType())){
				if ("M".equalsIgnoreCase(message.getStatus())){
					if("B".equalsIgnoreCase(mobileType))
						query.append(",anotherMobileNumber,anotherSecondaryMobileNumber");
					else if("P".equalsIgnoreCase(mobileType))
						query.append(",anotherMobileNumber");
					else
						query.append(",anotherSecondaryMobileNumber");
					
					query.append(",addressType from vw_studentDetails where ((mobileNumber!=0 AND mobileNumber is not null) or (anotherMobileNumber!=0 AND mobileNumber is not null)) and parentId=");
				}else
					query.append("select parentEmail  from vw_studentDetails where (parentEmail != '' AND parentEmail is not null) and parentId=");
			}else if ("ST".equalsIgnoreCase(message.getReceiverType())) {
				if ("M".equalsIgnoreCase(message.getStatus())){
					query.append("from vw_studentDetails where (mobileNumber!=0 AND mobileNumber is not null) and accountId=");
				}else
					query.append("select parentEmail  from vw_studentDetails where (parentEmail != '' AND parentEmail is not null) and accountId=");
			}
			query.append(accountId);
			query.append(" and status='Y' and custId=");
			query.append(custId);
			query.append(" and academicYearId=");
			query.append(academicId);
			if ("P".equalsIgnoreCase(message.getReceiverType())){
				allUsersList = this.getAll(query.toString());
				if (!ObjectFunctions.isNullOrEmpty(allUsersList)) {
					String mobileNumber = null;
					String mobileNumbers = null;
					for(Object[] obj : allUsersList){
						if("B".equalsIgnoreCase(mobileType)){
							if("R".equalsIgnoreCase(obj[4].toString())){
								if(!ObjectFunctions.isNullOrEmpty(obj[1]))
									mobileNumber=obj[1].toString();
								if(!ObjectFunctions.isNullOrEmpty(obj[0]))
									mobileNumbers=obj[0].toString();
							}else{
								if(!ObjectFunctions.isNullOrEmpty(obj[3]))
									mobileNumber=obj[3].toString();
								if(!ObjectFunctions.isNullOrEmpty(obj[2]))
									mobileNumbers=obj[2].toString();
							}
							mobileNumberset.addAll(this.addMobileNumberBasedOnSettings(mobileType, mobileNumbers, mobileNumber));
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
					allUsersList = null;
				} 
			}else{
				allUserstrList = this.getAll(query.toString());
				if(!ObjectFunctions.isNullOrEmpty(allUserstrList))
					mobileNumberset.addAll(allUserstrList);
			}
			
			allUserstrList = null;
			query = null;
			if(ObjectFunctions.isNotNullOrEmpty(mobileNumberset)){
				return mobileNumberset;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    public Set prepareStaffCredentialDetails(Messages message, long custId, long academicYearId, long accounId) {
		
		try {
			Set<String> mobileNumberset = new HashSet<String>();
			List allUsersList = null;
			if ("S".equalsIgnoreCase(message.getReceiverType()) || "N".equalsIgnoreCase(message.getReceiverType())) {
				StringBuffer query = new StringBuffer();
				// This is very critical to identify whether SMS or email ids
				if ("M".equalsIgnoreCase(message.getStatus())){
					
					query.append("select mobileNumber from vw_staffDetails where (mobileNumber!=0 AND mobileNumber is not null)  and status='Y' and custId=");//and academicYearStatus='Y'  
					
					/*if("S".equalsIgnoreCase(message.getReceiverType())){
						query.append("select mobileNumber from vw_staffDetails where (mobileNumber!=0 AND mobileNumber is not null)  and status='Y' and academicYearStatus='Y' and (roleName='ROLE_TEACHER' or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL' or roleName='ROLE_ASST_TEACHER' or roleName='ROLE_VICEPRINCIPAL') and custId=");
					}
					else if("N".equalsIgnoreCase(message.getReceiverType())){
						query.append("select mobileNumber from vw_staffDetails where (mobileNumber!=0 AND mobileNumber is not null)  and status='Y' and academicYearStatus='Y' and (roleName='ROLE_AYAH' or roleName='ROLE_CLERK' or roleName='ROLE_DRIVER' or roleName='ROLE_HELPER' or roleName='ROLE_SWEEPER' or roleName='ROLE_PEON' or roleName='ROLE_TYPIST' or roleName='ROLE_WATCHMAN' or roleName='ROLE_ADMIN' or roleName='ROLE_ADMINOFFICER' or roleName='ROLE_CONDUCTOR' or roleName='ROLE_COMPUTEROPERATOR' or roleName='ROLE_LABASST'  or roleName='ROLE_TRANSPORT'  or roleName='ROLE_TRANSPORTFINANCE' or roleName='ROLE_FINANCE' or roleName='ROLE_OTHERS') and custId=");
					}*/
				}
				/*else{
					if("S".equalsIgnoreCase(message.getReceiverType())){
						query.append("select email from vw_staffDetails where (email != ''  AND email is not null) and status='Y' and academicYearStatus='Y' and (roleName='ROLE_TEACHER' or roleName='ROLE_HOD' or  roleName='ROLE_PRINCIPAL') and custId=");
					}
					else if("N".equalsIgnoreCase(message.getReceiverType())){
						query.append("select email from vw_staffDetails where (email != ''  AND email is not null) and status='Y' and academicYearStatus='Y' and (roleName='ROLE_AYAH' or roleName='ROLE_CLERK' or roleName='ROLE_DRIVER' or roleName='ROLE_HELPER' or roleName='ROLE_SWEEPER' or roleName='ROLE_PEON' or roleName='ROLE_TYPIST' or roleName='ROLE_WATCHMAN' or roleName='ROLE_ADMIN' or roleName='ROLE_CONDUCTOR' or roleName='ROLE_COMPUTEROPERATOR' or roleName='ROLE_LABASST' or roleName='ROLE_OTHERS') and custId=");
					}
				}	*/
				query.append(custId);
				query.append(" and  academicYearId<="+academicYearId);
				query.append(" and  accountId="+accounId);
				allUsersList = this.getAll(query.toString());
				if (!ObjectFunctions.isNullOrEmpty(allUsersList)) {
					mobileNumberset.addAll(allUsersList);
				}
				allUsersList = null;
				query = null;
			}
			if (ObjectFunctions.isNotNullOrEmpty(mobileNumberset)) {
				return mobileNumberset;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    public Map<String,String>  sendLoginCredentials(Messages messageInfo,AcademicYear academicYear,User user, List<String> selectedIds,List<String> selectedAccountIds,boolean customerSpesific,Customer customer) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering CommunicationManager 'sendlogincredentials' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try {
			StringBuffer queryString=null;
			Messages message = null;
			StringBuffer smsContent;
			boolean smsStatus = false;
			boolean emailStatus = false;
			boolean smsOptSelected = false;
			boolean emailOptSelected = false;
			User account = null;
			//User accountUser = null;
			ViewAllUsers adminUser =null;
			//Customer customer = this.getCustomerByCustId(custId);
			if (customer.isCheckEmailService() || customer.isCheckMobileService()) {
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				if (!ObjectFunctions.isNullOrEmpty(selectedIds)) {
					if (ObjectFunctions.isNotNullOrEmpty(selectedAccountIds)) {
						messageInfo.setCustomer(customer);
						String commaDelimitedString = StringUtil.convertListToString(selectedAccountIds);
						queryString = new StringBuffer();
						queryString.append("custId="+ customer.getId()+ " and accountExpired='N' and (username!='' AND username is not null) and id in ("+ commaDelimitedString+ ")");
						List<User> usersList = this.getAll(User.class,queryString.toString());
						if(customerSpesific){
							adminUser = (ViewAllUsers)this.get(ViewAllUsers.class, "custId="+customer.getId()+" and roleId=1");
						}
						if (!ObjectFunctions.isNullOrEmpty(usersList)) 
						{
							 account = new User();
							List<UserVO> userVoList = account.copyFromEntityToUserVoList(usersList); // this is converting the userslist to user vo list
							if (!ObjectFunctions.isNullOrEmpty(userVoList)) 
							{
								 for (UserVO accountuserVo : userVoList) {
									 try {
											//below lines used to we generating new password for staff,parent and student
											String newPassword = StringUtil.generateRandomString();//accountuserVo.getUsername(); 
											accountuserVo.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
											accountuserVo.setConfirmPassword(newPassword);
											for (String chBox : selectedIds) {
											    messageInfo.setStatus(chBox); // comment for this this is send login credentials we send to automatic but here save manual no i will change the code 
											    if ("M".equalsIgnoreCase(chBox)) { // here messageInfo.setStatus() in this place already check it's come M(sms) or E(email) from chBox param so i will remove the messagesInfo.setStatus() to chBox 
											    	smsOptSelected = true;
											    	log.debug("----sms block----" + chBox);
											    	if (customer.isCheckMobileService()) {
														Set<String> mobileNumbers = null;
														smsContent = new StringBuffer();
														   if ("P".equalsIgnoreCase(messageInfo.getReceiverType()) || "ST".equalsIgnoreCase(messageInfo.getReceiverType())) {
																   mobileNumbers = this.prepareParentCredentialDetails(messageInfo,customer.getId(),academicYear.getId(),accountuserVo.getId(),customer.getMobileType());
																	if (ObjectFunctions.isNotNullOrEmpty(mobileNumbers)) {
																		if(customerSpesific){
																			smsContent.append("Dear "+(!StringFunctions.isNullOrEmpty(customer.getSender())?customer.getSender():"Hyniva")+" Parent,");
																		}else
																			smsContent.append("Dear "+accountuserVo.getPersonVo().getFirstName()+ ",");													
																	 if ("P".equalsIgnoreCase(messageInfo.getReceiverType())){
																		 User userObj = null;
																		 userObj = (User) this.get(User.class, "custId="+customer.getId()+" and id="+accountuserVo.getId());
																		 messageInfo = this.saveMessageDetailsTracking(messageInfo,null,null,userObj);	
																	 }
																	 else{
																		 Student student = (Student) this.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+accountuserVo.getId());
																		 messageInfo = this.saveMessageDetailsTracking(messageInfo,student,null,null);
																		 student = null;
																	 	}
																	}
														   }else if ("S".equalsIgnoreCase(messageInfo.getReceiverType()) || "N".equalsIgnoreCase(messageInfo.getReceiverType())) {
																mobileNumbers = this.prepareStaffCredentialDetails(messageInfo,customer.getId(),academicYear.getId(),accountuserVo.getId());
																if(ObjectFunctions.isNotNullOrEmpty(mobileNumbers)){
																	smsContent.append("Dear "+accountuserVo.getPersonVo().getFirstName()+ ",");												
																Staff staff = null;
																staff = (Staff) this.get(Staff.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+accountuserVo.getId());
																 messageInfo = this.saveMessageDetailsTracking(messageInfo,null,staff,null);
																}
														   }
														   if(customerSpesific){
															   smsContent.append(" Download the EazySchool Android App from play store or https://goo.gl/Q5Odw7 Username:"+accountuserVo.getUsername() +" Password:"+newPassword+", For any help call "+adminUser.getMobileNumber()+" from "+adminUser.getFirstName());
														   }else{
															   smsContent.append("Your "+customer.getOrganization()+" account password has been reset to ");
															   smsContent.append(" User Name: ");
															   smsContent.append(accountuserVo.getUsername() );
															   smsContent.append(" Password: ");
															   smsContent.append("'"+ newPassword +"'"); 
														   }
														   /* We are generating random password to any if user send login credentiols to any user instead of checking all conditions */
														   /*if ("P".equalsIgnoreCase(messageInfo.getReceiverType())) {
															   smsContent.append(customer.getCustomerShortName()+"2VsnHJG9");  
															   accountuserVo.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
														   }else{
															   smsContent.append(accountuserVo.getUsername());
														   }*/
														   smsContent.append(" Thank you from ");
														   messageInfo.setMessageDescription(smsContent.toString());
														   messageInfo.setCreatedById(user.getId());
														   messageInfo.setStatus("A");
														   messageInfo.setCustomer(customer);
														   messageInfo.setAcademicYear(academicYear);
														   message = new Messages();
														   message.copyFrom(messageInfo);
														   if(!ObjectFunctions.isNullOrEmpty(mobileNumbers))
															   smsStatus = this.deliverSms(message,mobileNumbers,smsServiceProvider);
														   message = null;
													}
												}
											if ("E".equalsIgnoreCase(chBox)) {
												log.debug("----email block----" +chBox);
												emailOptSelected = true;
												MailUtil mailUtil=null;
												if (customer.isCheckEmailService()){
													if ("P".equalsIgnoreCase(messageInfo.getReceiverType()) || "ST".equalsIgnoreCase(messageInfo.getReceiverType())) {
														if(!ObjectFunctions.isNullOrEmpty(accountuserVo.getPersonVo().getParentEmail())){
														String[] emailAddresses = new String[1];
														emailAddresses[0]=accountuserVo.getPersonVo().getParentEmail();
														if("ST".equalsIgnoreCase(messageInfo.getReceiverType())){
															  mailUtil=new MailUtil(emailAddresses,"Regd : Student Registration Email",customer.getId(),customer.getSender(),"Administrator",this.getContactFromEmail(customer));
														}else{
															  mailUtil=new MailUtil(emailAddresses,"Regd : Parent Registration Email",customer.getId(),customer.getSender(),"Administrator",this.getContactFromEmail(customer));
														}
														mailUtil.setContactEmail(customer.getContactEmail());
														mailUtil.setContactPasword(customer.getContactPassword());
														emailStatus=mailUtil.sendMailToParentForRegistration(accountuserVo.getUsername().trim(),newPassword,accountuserVo.getPersonVo().getFirstName(),customer.getOrganization(),this.getContactFromEmail(customer));
														}
													} 
													else if ("S".equalsIgnoreCase(messageInfo.getReceiverType()) || "N".equalsIgnoreCase(messageInfo.getReceiverType())) {
														String[] emailAddresses = new String[1];
														if(!ObjectFunctions.isNullOrEmpty(accountuserVo.getPrimaryAddressVo())){
															if(!ObjectFunctions.isNullOrEmpty(accountuserVo.getPrimaryAddressVo().getEmail())){
																emailAddresses[0]=accountuserVo.getPrimaryAddressVo().getEmail();
																 mailUtil=new MailUtil(emailAddresses,"Regd : Staff Registration Email",customer.getId(),customer.getSender(),"Administrator",this.getContactFromEmail(customer));
																 mailUtil.setContactEmail(customer.getContactEmail());
																 mailUtil.setContactPasword(customer.getContactPassword());
																emailStatus=mailUtil.sendMailToParentForRegistration(accountuserVo.getUsername(),newPassword,accountuserVo.getPersonVo().getFirstName(),customer.getOrganization(),this.getContactFromEmail(customer));
															}
														}
												} 
												  mailUtil=null;
													}
												}
											}
											/*accountUser = new User();
											accountUser = accountUser.copyFromVoToEntity(accountUser, accountuserVo);
											super.save(accountUser);
											accountUser = null;*/
											this.updateQuery("update Account set password='"+accountuserVo.getPassword()+"', passwordStatus='Y' where id="+accountuserVo.getId());
											
											accountuserVo = null;
									} catch (Exception e) {
										e.printStackTrace();
										continue;
									}
								}
							}
							userVoList = null;
						}
					}	
				}	
			if(smsOptSelected || emailOptSelected){
				if(smsOptSelected && emailOptSelected){
					if(smsStatus || emailStatus){
						if(smsStatus && emailStatus){
							msg.put("0", "E-Mail(s) and SMS(s) has been delivered successfully.");
						}else if(smsStatus){
							msg.put("1", "E-Mail(s) has not been delivered.");
							msg.put("0", "SMS(s) has been delivered successfully.");
						}else{
							msg.put("0", "E-Mail(s) has been delivered successfully.");
							msg.put("1", "SMS(s) has not been delivered.");
						}
					}else{
						msg.put("1", "E-Mail(s) and SMS(s) has not been delivered.");
					}
				}else if(smsOptSelected){
					if(smsStatus)
						msg.put("0","SMS(s) has been delivered successfully.");
					else
						msg.put("1", "SMS(s) has not been delivered.");
				}else{
					if(emailStatus)
						msg.put("0","E-Mail(s) has been delivered successfully.");
					else
						msg.put("1", "E-Mail(s) has not been delivered.");
				}
			}
			message = null;
			messageInfo = null;
		}else {
			msg.put("1","Email or SMS services are disabled, enable services.");
		}
		}catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return msg;
	}
   /* public String SendSmsToAdminAndStaff(String anyTitle,Set<String> mobileNumbers, long custId,User user,AcademicYear academicYear,Leave leave)throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering CommunicationManagerImpl 'SendSmsToAdminAndStaff' method");
		}
		try {
			Messages mssag = null;
			Customer customer = this.getCustomerByCustId(custId);
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
				boolean msgStatus = this.deliverSms(mssag,mobileNumbers,custId);
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
	}*/
    public String SendSmsToCustomer(Set<String> mobileNumbers, Customer customer,User user, AcademicYear academicYear)throws URTUniversalException  {
		try{
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			Messages msgs = new Messages();
			StringBuffer smsContent = new StringBuffer();
			msgs.setStatus("CR");
			msgs.setCreatedById(user.getId());
			msgs.setLastUpdatedById(user.getId());
			msgs.setPurposeType("Regd : Customer Registration");
			msgs.setSenderName(user.getUserRoleDescription());
			msgs.setAcademicYear(academicYear);
			msgs.setSmsSenderId(customer.getSender());
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				msgs.setCustomer(customer);
			}
			smsContent.append("Dear "+customer.getCustomerFullPersonName()+",");
			smsContent.append("Your "+customer.getOrganization()+" account is created successfully.Your UserName/password are ");
			smsContent.append(customer.getCustEmail());
			smsContent.append("/");
			smsContent.append(customer.getPassword());
			smsContent.append(" Thank you from ");
			msgs.setMessageDescription(smsContent.toString());	
			if(!ObjectFunctions.isNullOrEmpty(mobileNumbers)){
			msgs = this.saveMessageDetailsTracking(msgs, null, null, user);
			log.debug("mobNo="+mobileNumbers);
			@SuppressWarnings("unused")
			boolean smsStatus = this.deliverSms(msgs, mobileNumbers, smsServiceProvider);
			if(smsStatus = true){
				log.debug("Sms has been delivered successfully.");
			}else{
				log.debug("Sms has not been delivered.");
			}
		}
		} catch(Exception ex){
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}			
		return null;
	}
    public int sendSMSForSchoolWideMessages(MessagesMainVO messagesMainVO){
    	//boolean tempBoolean=false;
    	int returnVal=0;
		Map<String, String> msgs =new HashMap<String, String>();
		try {
			long custId = messagesMainVO.getIdentifier().getCustId();
			long accountId = messagesMainVO.getIdentifier().getAccountId();
			if (custId>0 && accountId>0) {
				//String[] selectedType = null;
				String[] selectedType = new String[1];
				String trStatus = null;
				List<String> fileUploadList=null;
				User user = (User) this.get(User.class, Long.valueOf(accountId));
				AcademicYear academicYear = (this.getCurrentAcademicYear("Y",custId));
				int availableSmsCount=0;
				int sendSmsCount=this.getTotalSmsCount(academicYear.getCustId(), academicYear.getId()) ;
				int allotedSmsCount=(int) (academicYear.getAllotedsms() + academicYear.getPaidSms());
				if(allotedSmsCount >= sendSmsCount){
					availableSmsCount=allotedSmsCount - sendSmsCount;
				}
				//if(availableSmsCount > 0){
					Messages messges = null;
					if (!ObjectFunctions.isNullOrEmpty(messagesMainVO.getMessagesVOs())) {
						for(MessagesVO objMessageVO : messagesMainVO.getMessagesVOs()){
							MessagesVO messagesVO=(MessagesVO)objMessageVO;
							
							String receiverType = messagesVO.getReceiverType();
							String messageDescription = messagesVO.getMessageDescription();
							String hostlerStatus = messagesVO.getHostlerStatus();
							String purposeType = messagesVO.getPurposeType();
							String title = messagesVO.getTitle();
							String otherType = messagesVO.getOtherType();
							String staffType = messagesVO.getStaffType();
							String messageType = messagesVO.getMessageType();
							String studentAccountIds = messagesVO.getStudentAccountIds();
							String classIds = messagesVO.getClassIds();
							
								
							List<String> selectedTypeArray = Arrays.asList(selectedType);
							List<String> accountIdsArray=null;
							List<String> classIdsArray=null;
							if(!StringFunctions.isNullOrEmpty(studentAccountIds)){
								accountIdsArray = Arrays.asList(studentAccountIds.toString().split(","));
							}
							
							messges = new Messages();
							messges.setReceiverType(receiverType);
							messges.setMessageDescription(messageDescription);
							messges.setTitle(title);
							messges.setOtherType(otherType);
							messges.setStaffType(staffType);
							messges.setPurposeType(purposeType);
							messges.setMessageType(messageType);
							if(StringFunctions.isNullOrEmpty(objMessageVO.getStatus()))
							{
								messges.setStatus("M");
								selectedType[0]= "M";
							}
							else
							{
								selectedType[0]= objMessageVO.getStatus();
								messges.setStatus(objMessageVO.getStatus());
							}
							
							
							messges.setEmailContent(objMessageVO.getEmailContent());
							messges.setEmailIds(objMessageVO.getEmailIds());
							
							/*if(StringFunctions.isNullOrEmpty(objMessageVO.getStatus()))
							{
								messges.setStatus("M");
								selectedType[0]= "M";
							}*/
							
							if(!StringFunctions.isNullOrEmpty(classIds)){
								classIdsArray = Arrays.asList(classIds.toString().split(","));
							}
							messges.setMessageType(objMessageVO.getOtherMobileNos());
							
							msgs = this.sendSchoolWideMessages(messges, custId, academicYear, user, selectedTypeArray,accountIdsArray, classIdsArray, trStatus,fileUploadList,null,hostlerStatus,messagesVO.getMessageSalutation());
					     }
						String msg = msgs.get("1");
						if (!StringFunctions.isNullOrEmpty(msg))
							returnVal =1;
						msg = msgs.get("0");
						if (!StringFunctions.isNullOrEmpty(msg))
							returnVal =0;
						
						//If email has not delivered
						msg = msgs.get("5");
						if (!StringFunctions.isNullOrEmpty(msg))
							returnVal =3;
						
						//From Email address and password wrong
						msg = msgs.get("2");
						if (!StringFunctions.isNullOrEmpty(msg))
							returnVal =4;
						
						//If insufficient sms balance
						msg = msgs.get("6");
						if (!StringFunctions.isNullOrEmpty(msg))
							returnVal =2;
						
						msgs=null;
						msg=null;
						messges=null;
					}
					/*}else{
					return 2;
				}*/
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return returnVal;
	}
    
    public boolean sendFeeReminderForParent(Customer customer,SMSServiceProviders smsprovider, ViewStudentFeePaymentDetails feePaymentDetails,AcademicYear academicYear,String defaultMsg,long userId){
    	try {
    		Student student = (Student)this.get(Student.class, feePaymentDetails.getId());
			Set<String> mobileNumbersSet = new HashSet<String>();
			mobileNumbersSet.add(feePaymentDetails.getPhoneNumber());
			Messages message = new Messages(customer, smsprovider, academicYear,"SMS", "Send Fee Defaulter Remainder",Constants.MODIFY_STATUS);
			message.setMessageDescription(defaultMsg);
			message.setStatus(Constants.MODIFY_STATUS);
			message.setCreatedById(userId);
			message.setSentSms(1);
			message.setMobileNumbers(feePaymentDetails.getPhoneNumber());
			message = this.saveMessageDetailsTracking(message, student, null, null);
			boolean smsStatus = this.deliverSms(message,mobileNumbersSet,smsprovider);
			return smsStatus;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
    	return false;
    }
    
   public void sendCircularNotification(Circular circular,Customer customer,List<String> accountIds)
   {
	   		 String commaDelimitedString = StringUtil.convertListToString(accountIds);
			 StringBuffer accountIdbuffer = new StringBuffer("(");
			 accountIdbuffer.append(commaDelimitedString);
			 accountIdbuffer.append(")");
			 String message = "Circular Message created.";

			 ViewCircularUsersVO circularUsersVO = null;
			 List<ViewCircularUsersVO> circularUsersVOs = new ArrayList<ViewCircularUsersVO>();
			 try {
				 	MessagesMainVO  messagesMainVO  = new MessagesMainVO();
				 	// Circular Messages
					circularUsersVO = new ViewCircularUsersVO();
					circularUsersVO.setCircularDescription(circular.getCircularDescription());
					circularUsersVO.setCircularDate(String.valueOf(circular.getCircularDate()));
					circularUsersVOs.add(circularUsersVO);
					messagesMainVO.setCircularMessagesVOs(circularUsersVOs);
					//circularUsersVOs = null;
			
			 		JSONObject main = new JSONObject();
					JSONObject subVal = new JSONObject();
					main.put("notificationFor", "Circular");
					subVal.put("description", circular.getCircularDescription());
					subVal.put("title", message);
					subVal.put("academicYearId", circular.getAcademicYear());
					subVal.put("circularMessagesVOs",new JSONArray(new Gson().toJson(circularUsersVOs)));
					main.put("information", subVal);
					
					this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
   }
   
   public int submitCircularMessage(CircularMessagesVO circularMessagesVO)
	{
		try {
			long custId = circularMessagesVO.getIdentifier().getCustId();
			long academicYearId = circularMessagesVO.getIdentifier().getAcademicYearId();
			long accountId = circularMessagesVO.getIdentifier().getAccountId();
			String checkAllOrIndividual = "";
			if(("AP".equalsIgnoreCase(circularMessagesVO.getTo()) && StringFunctions.isNullOrEmpty(circularMessagesVO.getClassSectionIds())) || ("AP".equalsIgnoreCase(circularMessagesVO.getTo()) && StringFunctions.isNotNullOrEmpty(circularMessagesVO.getClassSectionIds()))){
				if(("AP".equalsIgnoreCase(circularMessagesVO.getTo()) && StringFunctions.isNullOrEmpty(circularMessagesVO.getClassSectionIds()))){
					checkAllOrIndividual=circularMessagesVO.getTo();
				}else{
					checkAllOrIndividual="I";
				}
			}else{
				if(StringFunctions.isNullOrEmpty(circularMessagesVO.getClassSectionIds())){
					checkAllOrIndividual=circularMessagesVO.getTo();
				}else{
					return 1;
				}
			}
			List<String> boxSelectedIds=null;
			if("E".equalsIgnoreCase(circularMessagesVO.getAlertType())){
				boxSelectedIds=new ArrayList<String>();
				boxSelectedIds.add("E");
			}else if ("M".equalsIgnoreCase(circularMessagesVO.getAlertType()) || "S".equalsIgnoreCase(circularMessagesVO.getAlertType())) {
				boxSelectedIds=new ArrayList<String>();
				boxSelectedIds.add("M");
			}else if("SE".equalsIgnoreCase(circularMessagesVO.getAlertType())){
				boxSelectedIds=new ArrayList<String>();
				boxSelectedIds.add("E");
				boxSelectedIds.add("M");
			}else{
				return 1;
			}
			User curUser= (User)this.get(User.class,accountId);
			if(!ObjectFunctions.isNullOrEmpty(curUser)){
					AcademicYear academicYearObj= (AcademicYear)this.get(AcademicYear.class,academicYearId);
					if(!ObjectFunctions.isNullOrEmpty(academicYearObj)){
						List<String> toAccountIds = new ArrayList<String>();
						ArrayList<String> classIds = new  ArrayList<String>(Arrays.asList(circularMessagesVO.getClassSectionIds()));
						if("AP".equalsIgnoreCase(circularMessagesVO.getTo()))
						{
							if(StringFunctions.isNotNullOrEmpty(classIds.toString().replace("[", "").replace("]", ""))){
								//toAccountIds= this.getAll("SELECT GROUP_CONCAT(DISTINCT(parentId)) FROM Account WHERE id IN (SELECT accountId FROM student WHERE academicYearId="+academicYearId+" and custId="+custId+" and classSectionId IN ("+classIds.toString().replace("[", "").replace("]", "")+"))");
								toAccountIds= this.getAll("SELECT GROUP_CONCAT(DISTINCT(parentAccountId)) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN (SELECT accountId FROM student WHERE academicYearId="+academicYearId+" and custId="+custId+" and classSectionId IN ("+classIds.toString().replace("[", "").replace("]", "")+"))");
							}else{
								//toAccountIds= this.getAll("SELECT GROUP_CONCAT(DISTINCT(parentId)) FROM Account WHERE id IN (SELECT accountId FROM student WHERE academicYearId="+academicYearId+" and custId="+custId+")");
								toAccountIds= this.getAll("SELECT GROUP_CONCAT(DISTINCT(parentAccountId)) FROM Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) WHERE id IN (SELECT accountId FROM student WHERE academicYearId="+academicYearId+" and custId="+custId+")");
							}
						}
						//Sending to teaching staff
						else if("AS".equalsIgnoreCase(circularMessagesVO.getTo()))
						{
							toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_staffDetails WHERE roleId IN(2, 8, 12, 31, 35) AND custId = "+custId);
						}
						//Sending to Non - teaching staff
						else if("AN".equalsIgnoreCase(circularMessagesVO.getTo()))
						{
							toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_staffDetails WHERE roleId IN (4,9,10,11,14,15,16,32,41) AND custId = "+custId);
						}
						//Sending to All teaching,nonTeaching,parent,student
						else if("AA".equalsIgnoreCase(circularMessagesVO.getTo())){
							toAccountIds = this.getAll("SELECT GROUP_CONCAT(DISTINCT(accountId)) FROM vw_userRoles WHERE roleId IN (2,3,7,8,12,31,35,4,9,10,11,14,15,16,32,41) AND accountExpired='N' AND custId = "+custId);
						}
						if(StringFunctions.isNotNullOrEmpty(toAccountIds.toString().replace("[", "").replace("]", "")) ){
							Messages messageObj=new Messages(); 
							messageObj.setMessageDescription(circularMessagesVO.getCircularDescription());
							messageObj.setReceiverType(circularMessagesVO.getTo());
							this.addCircularMessageInfo(circularMessagesVO.getAlertType(),messageObj,custId,academicYearObj,curUser,boxSelectedIds,toAccountIds,null,checkAllOrIndividual, true);
							toAccountIds=null;
							classIds=null;
						}else{
							return 1;
						}
						return 0;
				}else{
					return 2;
				}
			}else{
				return 3;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return 4;
		}
	}
   public Map<String,String> addCircularMessageInfo(String alertType,Messages message, long userCustId, AcademicYear academicYearObj,User userObj, List<String> alertTypeIds, List<String> userAcountIds, List<String> classIds,String checkAllOrIndividual, boolean isApi)
  	{   Map<String,String> messageInfo=null;
		try {
			List<User> usersList = null;
			Circular circularObj=null;
			if(StringFunctions.isNotNullOrEmpty(alertType)){
				circularObj = new Circular();
				circularObj.setCustId(userCustId);
				circularObj.setCreatedById(userObj.getId());
				circularObj.setCreatedDate(new Date());
				circularObj.setLastUpdatedDate(new Date());
				circularObj.setLastAccessDate(new Date());
				circularObj.setAcademicYear(academicYearObj.getId());
				circularObj.setSenderAccountId(userObj.getId());
				String adate = DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, new Date());
				circularObj.setCircularDate(adate);
				circularObj.setType(alertType);
				if("I".equalsIgnoreCase(checkAllOrIndividual)){
					String commaDelimitedString = StringUtil.convertListToString(userAcountIds);
					usersList = this.getAll(User.class,"id in (" +commaDelimitedString+")");
					circularObj.addNewUser(usersList);
				}
				circularObj.setCircularDescription(message.getMessageDescription());
				circularObj.setCircularStatus(checkAllOrIndividual);
				this.save(circularObj);
				//Sending mobile notifications
				this.sendCircularNotification(circularObj,new Customer(),userAcountIds);
				
				
				if(!ObjectFunctions.isNullOrEmpty(alertTypeIds)){
					if("AS".equalsIgnoreCase(message.getReceiverType()) || "AN".equalsIgnoreCase(message.getReceiverType()))
						message.setReceiverType("AS");
					
					message.setEmailContent(message.getMessageDescription());
					message.setTitle("Regd : Circular Messages");
					//addActionMessages(this.sendSchoolWideMessages(message, userCustId, academicYearObj, userObj, alertTypeIds, userAcountIds, classIds, null,null,null,null,null));
					//messageInfo=this.sendSchoolWideMessages(message, userCustId, academicYearObj, userObj, alertTypeIds, userAcountIds, classIds, null,null,null,null,null);
					if(isApi){
						AddCircularMessageThread r1 = new AddCircularMessageThread(message, userCustId, academicYearObj, userObj, alertTypeIds, userAcountIds, classIds);
						new Thread(r1).start();
						return null;
					 }else
						 messageInfo=this.sendSchoolWideMessages(message, userCustId, academicYearObj, userObj, alertTypeIds, userAcountIds, classIds, null,null,null,null,null);
				}
				
			}
			alertTypeIds=null;userAcountIds=null;classIds=null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return messageInfo;
  	}
}
