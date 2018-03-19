/**
 * 
 */
package com.urt.service.manager.interfaces.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyniva.sms.ws.vo.CircularMessagesVO;
import com.hyniva.sms.ws.vo.MessagesMainVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Circular;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Leave;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;

/**
 * @author uroomtech
 *
 */
public interface CommunicationManager extends UniversalManager {

	Map<String, String> sendMarksToUsers(HashMap keys, AcademicYear academicYear , String selectedStudentIds) throws URTUniversalException;
	
	Set prepareParentCommunicationDetails(Messages message, long custId, long academicId,User user,List<String> accountIds,String hostlerStatus,StringBuffer Query,String mobileType);
	@Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	Map<String,String>  sendSchoolWideMessages(Messages message, long custId, AcademicYear academicYear,User user,List<String> selectedIds, List<String> selectedAccountIds,List<String> selectedClassIds, String trStatus,List<String> fileUploadList,Set<String> mobileNumbersSet,String hostlerStatus,String otherMessageSalutation);
	Set prepareStaffCommunicationDetails(Messages message, long custId, long academicYearId,User user,List<String> accountIds,StringBuffer Query);
	int deliverEmails(String msgDesc, String title, Set emailIds,User user,List<String> fileUploadList,Customer customer);
	Set prepareEmailIdsForClasses(List<String> classIds,long custId, long academicYearId, User user);
	Set prepareEmailIdsForAccountIds(List<String> accountIds,long custId, long academicYearId, User user);
	Map<String,String> sendLoginCredentials(Messages message,AcademicYear academicYear,User user,List<String> selectedIds, List<String> selectedAccountIds,boolean customerSpesific,Customer customer);
	String SendSmsToAdminAndStaff(String anyTitle,Set<String> mobileNumbers,long custId,User user,AcademicYear academicYear,Leave leave);
	String SendSmsToCustomer(Set<String> mobileNumbers,Customer customer,User user,AcademicYear academicYear);
	int sendSMSForSchoolWideMessages(MessagesMainVO messagesMainVO);
	boolean sendFeeReminderForParent(Customer customer,SMSServiceProviders smsprovider, ViewStudentFeePaymentDetails feePaymentDetails,AcademicYear academicYear,String defaultMsg,long userId);
	void sendCircularNotification(Circular circular,Customer customer,List<String> accountIds);
	int submitCircularMessage(CircularMessagesVO circularMessagesVO);
	Map<String,String> addCircularMessageInfo(String alertType,Messages message, long userCustId, AcademicYear academicYearObj,User userObj, List<String> alertTypeIds, List<String> userAcountIds, List<String> classIds,String checkAllOrIndividual, boolean isMobile);
	int getAvailableSmsCount(long custId,long academicYearId);
}
