package com.urt.service.manager.impl.user;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AcademicYearVo;
import com.hyniva.sms.ws.vo.ChangePasswordVO;
import com.hyniva.sms.ws.vo.CustomerPreferencesVO;
import com.hyniva.sms.ws.vo.CustomerVo;
import com.hyniva.sms.ws.vo.ForgotPasswordVO;
import com.hyniva.sms.ws.vo.MasterCustomerVo;
import com.hyniva.sms.ws.vo.SchoolAreaMainVO;
import com.hyniva.sms.ws.vo.SchoolAreaVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.interfaces.user.UserDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.LoginAccessbilityRoles;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.mobile.SchoolArea;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.user.UserManager;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.jrexception.JRExceptionClient;


/**
 * Implementation of UserManager interface.</p>
 * 
 * <p>
 * <a href="UserManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class UserManagerImpl extends UniversalManagerImpl implements UserManager {
	
	@Autowired
    private UserDao userDao;
    
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public ViewStudentPersonAccountDetails getViewStudentDetails(long accountId, String status) {
		return  (ViewStudentPersonAccountDetails) userDao.getViewStudentDetails(accountId,status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Customer saveCustomer(Customer customer){
		 return userDao.saveCustomer(customer);
	 }
	public User saveUser(User user){
		return userDao.saveUser(user);
		
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public User getAccountByCustIdAndStatus(String username,long custId,String status){
		return userDao.getAccountByCustIdAndStatus(username, custId,status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public User getAccountByCustId(String username,long custId){
		 return userDao.getAccountByCustId(username, custId);
	 }
	 public void removeRoleByUserIdAndRoleIdNotIn(String roleId,long userId){
		 userDao.removeRoleByUserIdAndRoleIdNotIn(roleId,userId);
	}
	 public String getRolesByRoleIdAndUserId(long userId,long roleId){
		 return userDao.getRolesByRoleIdAndUserId(userId,roleId);
	 }
	 
	public List<Object[]> searchParentMobileNumber(String mobileNumber, String parentEmail) {
		if (log.isDebugEnabled()) {
			log.debug("Entering StudentManager 'CreateParentLoginAccount' method");
		}
		try {
			StringBuffer sb = new StringBuffer();
			if (!StringFunctions.isNullOrEmpty(mobileNumber) && !StringFunctions.isNullOrEmpty(parentEmail))
				sb.append(" WHERE sd.mobileNumber like '%" + mobileNumber + "%' or sd.parentEmail='" + parentEmail + "' ");
			else if (!StringFunctions.isNullOrEmpty(mobileNumber))
				sb.append(" WHERE sd.mobileNumber like '%" + mobileNumber + "%' ");
			else if (!StringFunctions.isNullOrEmpty(parentEmail))
				sb.append(" WHERE sd.parentEmail='" + parentEmail + "' ");
			return (List<Object[]>) this.getAll("select sd.mobileNumber,sd.parentEmail,c.organization,c.contactNumber FROM vw_studentDetails sd  LEFT JOIN customer c on (c.id = sd.custId)"+ sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	public UserVO getUserDetails(UserTokenVO userTokenVO){
		UserVO userVO = null;
		try {
			String psw = PasswordUtils.passwordEncoder(userTokenVO.getPassword(), null);
			User user = (User) this.get(User.class, "username='"+userTokenVO.getUsername()+"' and password='"+psw+"'");
			if(!ObjectFunctions.isNullOrEmpty(user)){
				AcademicYear academicYear  = super.getCurrentAcademicYear("Y",user.getCustId());
				userVO = new UserVO();
				userVO.setFirstName(StringFunctions.isNullOrEmpty(user.getPerson().getFirstName())?"":user.getPerson().getFirstName());
				userVO.setLastName(StringFunctions.isNullOrEmpty(user.getPerson().getLastName())?"":user.getPerson().getLastName());
				userVO.setUsername(user.getUsername());
				userVO.setRole(user.getUserRoleName());
				if(!ObjectFunctions.isNullOrEmpty(user.getProfileImage()))
				{
					userVO.setUserImageId(user.getProfileImage().getId());
					userVO.setImageUrl(user.getProfileImage().getPath());
				}
				
				Customer customer = (Customer) this.get(Customer.class, user.getCustId());
				userVO.setEnabled(user.isEnabled()&& customer.getStatus());
				for(Role role : user.getRoles()){
					LoginAccessbilityRoles accessrole = (LoginAccessbilityRoles)this.get(LoginAccessbilityRoles.class,"customerId="+user.getCustId()+" and roleId="+role.getId()+" and (status='"+Constants.NO_STRING+"' OR androidStatus='"+Constants.NO_STRING+"')");
					//Disbaling the account when accessrole found null, not handling staff and parent having same account
					if(!ObjectFunctions.isNullOrEmpty(accessrole)) {
						userVO.setEnabled(false);
					}
				}
				//Disabling parent if there are no active children
				if(user.isParent() && userVO.isEnabled())
				{
						List<ViewAllUsers> vwusersList = this.getAllHqlQuery("from ViewAllUsers where roleId = 3 and parentId="+ user.getId()+" and accountEnabled='"+Constants.YES_STRING+"' and accountExpired='"+Constants.NO_STRING+"'");
						if (ObjectFunctions.isNullOrEmpty(vwusersList)) 
							userVO.setEnabled(false);
				}
				userVO.createIdentifier(user.getId(),user.getCustId(), academicYear.getId());
				user = null;
				psw =null;
				return userVO;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return userVO;
	} 
	
	public CustomerVo getCustomerDetails(long custId){
		CustomerVo customerVo=null;
		MasterCustomerVo masterCustomerVo = null;
		AcademicYearVo academicYearVo = null;
		SchoolAreaVO schoolAreaVo = null;
		try {
			Customer customer = (Customer) this.get(Customer.class, custId);
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
				customerVo = new CustomerVo();
				customerVo.setId(customer.getId());
				customerVo.setCustomerName(ObjectFunctions.isNullOrEmpty(customer.getCustomerName())? "": customer.getCustomerName());
				customerVo.setCustEmail(customer.getCustEmail());
				customerVo.setCustomerShortName(customer.getCustomerShortName());
				//customerVo.setCustomerStatus( customer.getCustomerStatus());
				customerVo.setStatus(customer.getStatus());
				customerVo.setFirstName(customer.getFirstName());
				customerVo.setLastName(customer.getLastName());
				customerVo.setContactNumber(ObjectFunctions.isNullOrEmpty(customer.getContactNumber())? "": customer.getContactNumber());
				customerVo.setTransportModuleStatus(customer.isTransportModuleStatus()? "Y": "N");
				customerVo.setHostelModuleStatus(customer.isHostelModuleStatus()? "Y": "N");
				customerVo.setSchoolName(ObjectFunctions.isNullOrEmpty(customer.getOrganization())? "": customer.getOrganization());
				customerVo.setParentPermissionStatus(customer.getParentPermissionStatus());
				customerVo.setStaffPermissionStatus(customer.getStaffPermissionStatus());
				customerVo.setStandardType(customer.getStandardType());
				customerVo.setSocietyName(ObjectFunctions.isNullOrEmpty(customer.getSocietyName())? "": customer.getSocietyName());
				customerVo.setRecognisedBy(ObjectFunctions.isNullOrEmpty(customer.getRecognisedBy())? "": customer.getRecognisedBy());
				if(!ObjectFunctions.isNullOrEmpty(customer.getMasterCustomer())){
					masterCustomerVo = new MasterCustomerVo();
					masterCustomerVo.setId(customer.getMasterCustomer().getId());
					masterCustomerVo.setFirstName(ObjectFunctions.isNullOrEmpty(customer.getMasterCustomer().getFirstName())?"": customer.getMasterCustomer().getFirstName());
					masterCustomerVo.setLastName(ObjectFunctions.isNullOrEmpty(customer.getMasterCustomer().getLastName())?"": customer.getMasterCustomer().getLastName());
				}
				customerVo.setMasterCustomerVo(masterCustomerVo);
				masterCustomerVo = null;
				
				List<AcademicYear> academicYears = this.getAll(AcademicYear.class, "custId = "+ customer.getId());
				if(!ObjectFunctions.isNullOrEmpty(academicYears)){
					for(AcademicYear academicYear:academicYears){
						academicYearVo = new AcademicYearVo();
						academicYearVo.setId(academicYear.getId());
						academicYearVo.setAcademicYear(academicYear.getAcademicYear());
						academicYearVo.setStartDate(ObjectFunctions.isNullOrEmpty(academicYear.getStartDate())? "": DateFunctions.convertDateToString(academicYear.getStartDate()));
						academicYearVo.setEndDate(ObjectFunctions.isNullOrEmpty(academicYear.getStartDate())? "": DateFunctions.convertDateToString(academicYear.getEndDate()));
						if(!ObjectFunctions.isNullOrEmpty(academicYear.isClassTeacherHandleFirstPeriod()))
							academicYearVo.setClassTeacherHandleFirstPeriod(String.valueOf(academicYear.isClassTeacherHandleFirstPeriod()));
						academicYearVo.setPastYear( ObjectFunctions.isNullOrEmpty(academicYear.getPastYear())? 0: academicYear.getPastYear());
						academicYearVo.setStatus(academicYear.getStatus());
						academicYearVo.setAllowedTotalSms(academicYear.getAllotedsms());
						academicYearVo.setPaidSms(academicYear.getPaidSms());
						academicYearVo.setManageAttendanceBy(ObjectFunctions.isNullOrEmpty(academicYear.getManageAttendanceBy())? "D": academicYear.getManageAttendanceBy());
						academicYearVo.setManageStaffAttendanceBy(ObjectFunctions.isNullOrEmpty(academicYear.getManageStaffAttendanceBy())? "D": academicYear.getManageStaffAttendanceBy());
						academicYearVo.setCaptureAttendanceBy(ObjectFunctions.isNullOrEmpty(academicYear.getCaptureAttendanceBy())? "O": academicYear.getCaptureAttendanceBy());
						academicYearVo.setCaptureAttendanceForStaff(ObjectFunctions.isNullOrEmpty(academicYear.getCaptureAttendanceForStaff())? "O": academicYear.getCaptureAttendanceForStaff());
						academicYearVo.setUseBiometricForStaff(academicYear.getUseBiometricForStaff());
						academicYearVo.setUseBiometricForStudent(academicYear.getUseBiometricForStudent());
						academicYearVo.setHolidayStatus(academicYear.getHolidayStatus());
						academicYearVo.setFeeModuleUsegeBy(ObjectFunctions.isNullOrEmpty(academicYear.getFeeModuleUsegeBy())? "W": academicYear.getFeeModuleUsegeBy());
						customerVo.getAcademicYearVoList().add(academicYearVo);
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(customer.getSchoolAreas())){
					for(SchoolArea areaPoints: customer.getSchoolAreas()){
						schoolAreaVo = new SchoolAreaVO();
						schoolAreaVo.setLatitude(areaPoints.getLatitude());
						schoolAreaVo.setLongitude(areaPoints.getLongitude());
						customerVo.getSchoolAreaVoList().add(schoolAreaVo);
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(customer.getPreferences())){
					CustomerPreferencesVO preferences = new CustomerPreferencesVO();
					preferences.setFeeBalanceAmountInPaymnetSMS(customer.getPreferences().getFeeBalanceAmountInPaymnetSMS());
					preferences.setFeePaymentNotificationToManagement(customer.getPreferences().getFeePaymentNotificationToManagement());
					preferences.setParentMobileNoVisibleToTeacher(customer.getPreferences().getParentMobileNoVisibleToTeacher());
					preferences.setVisibleFeeInfoToParent(customer.getPreferences().getVisibleFeeInfoToParent());
					if(!ObjectFunctions.isNullOrEmpty(customer.getPreferences().getRoles())){
						String roleIds = "";
						int i=0;
						for(Role role:customer.getPreferences().getRoles()){
							if(i==0){
								roleIds =Long.toString(role.getId());
								i=i+1;
							}else{
								roleIds =roleIds +","+ Long.toString(role.getId());
							}
						}
						preferences.setPaymnetNotificationRoleIds(roleIds);
					}else{
						preferences.setPaymnetNotificationRoleIds(null);
					}
					
					if(!ObjectFunctions.isNullOrEmpty(customer.isCheckEmailService())){
						if(customer.isCheckEmailService()){
							preferences.setEmailServiceEnabled("Y");
						}else{
							preferences.setEmailServiceEnabled("N");
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(customer.isCheckMobileService())){
						if(customer.isCheckMobileService()){
							preferences.setSmsServiceEnabled("Y");
						}else{
							preferences.setSmsServiceEnabled("N");
						}
					}
					if(!ObjectFunctions.isNullOrEmpty(customer.isCheckMobilePaymentService())){
						if(customer.isCheckMobilePaymentService()){
							preferences.setPaymentSMSServiceEnabled("Y");
						}else{
							preferences.setPaymentSMSServiceEnabled("N");
						}
					}
					
					customerVo.setPreferences(preferences);	
				}
				
				return customerVo;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return customerVo;
	}
	public boolean createCustomerSchoolAreaDetails(SchoolAreaMainVO schoolAreaMainVo){
		try {
			SchoolArea schoolArea =null;
			long custId = schoolAreaMainVo.getIdentifier().getCustId();
			long accountId = schoolAreaMainVo.getIdentifier().getAccountId();
			List<SchoolArea> points = this.getAll(SchoolArea.class, "custId = "+custId);
			if(ObjectFunctions.isNotNullOrEmpty(points)){
				for(SchoolArea area : points){
					this.remove(SchoolArea.class, area.getId());
				}
				//this.removeSchoolAreas(custId);
				points=null;
			}
			if(!ObjectFunctions.isNullOrEmpty(schoolAreaMainVo.getSchoolAreaVO())){
				for(SchoolAreaVO schoolAreaVo : schoolAreaMainVo.getSchoolAreaVO()){
					schoolArea = new SchoolArea();
					schoolArea.setCustId(custId);
					schoolArea.setLatitude(schoolAreaVo.getLatitude());
					schoolArea.setLongitude(schoolAreaVo.getLongitude());
					schoolArea.setCreatedById(accountId);
					schoolArea.setCreatedDate(new Date());
					this.save(schoolArea);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			return false;
		}
		return true;
	}
	public void removeSchoolAreas(long custId){
		userDao.removeSchoolAreas(custId);
	}
	 
	public boolean getUserForgotPasswordDetails(ForgotPasswordVO forgotPasswordVO){
		boolean userStatus = false;
		try {
			//List<Person> personlist = this.getAll(ViewAllUsers.class,"mobileNumber='"+"+91-"+mobileNumber+"' or studentMobile='"+"+91-"+mobileNumber+"' or secondaryMobileNumber='"+"+91-"+mobileNumber+"' or anotherMobileNumber='"+"+91-"+mobileNumber+"' or anotherSecondaryMobileNumber='"+"+91-"+mobileNumber+"' or studentEmail="+emailId+" or parentEmail="+emailId+" or anotherParentEmail="+emailId);
			//List<ViewAllUsers> viewAllUserslist = this.getAll(ViewAllUsers.class,"mobileNumber='"+forgotPasswordVO.getMobileNumber()+"' or studentMobile='"+forgotPasswordVO.getMobileNumber()+"' or studentEmail='"+forgotPasswordVO.getEmail()+"' or parentEmail='"+forgotPasswordVO.getEmail()+"' or staffEmail='"+forgotPasswordVO.getEmail()+"' group by accountId");
			//List<ViewAllUsers> viewAllUserslist = this.getAll(ViewAllUsers.class,"mobileNumber='"+forgotPasswordVO.getMobileNumber()+"' or studentEmail='"+forgotPasswordVO.getEmail()+"' or parentEmail='"+forgotPasswordVO.getEmail()+"' or staffEmail='"+forgotPasswordVO.getEmail()+"' group by accountId");
			if(!ObjectFunctions.isNullOrEmpty(forgotPasswordVO)){
				/*for(ViewAllUsers viewAllUsers :viewAllUserslist ){
					if(!ObjectFunctions.isNullOrEmpty(viewAllUsers)){
						User user = (User) this.get(User.class, "personId="+viewAllUsers.getPersonId());*/
						User user = (User) this.getUserByUserName(forgotPasswordVO.getMobileNumber());
						if(!ObjectFunctions.isNullOrEmpty(user)){
							userStatus = true;
							this.sendForgotPasswordByUserName(user);
							//Commented by Siva on 25 Aug 2016 for not going SMS
							/*
							String newPassword = StringUtil.generateRandomString();
							user.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
							this.save(user);
							log.debug("pawd:"+newPassword);
							String roleDes = user.getUserRoleDescription();
							boolean smsStatus = false;
							Messages message = new Messages();
							message.setStatus("M");
							message.setCreatedById(user.getId());
							message.setSenderName(roleDes);
							Set<String> mobileNumbersSet = null;
							MailUtil mailUtil = null;
							String[] emailAddresses = new String[1];
							Customer customer = (Customer) this.get(Customer.class, user.getCustId());
							//if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(forgotPasswordVO.getMobileNumber()))){
								mobileNumbersSet = new HashSet<String>(); 
								if("Student".equalsIgnoreCase(roleDes)){
									if(!StringFunctions.isNullOrEmpty(viewAllUsers.getStudentMobile()))
									{
										mobileNumbersSet.add(viewAllUsers.getStudentMobile());
									}
									if(!StringFunctions.isNullOrEmpty(viewAllUsers.getStudentEmail()))
									{
										emailAddresses[0]=viewAllUsers.getStudentEmail();
									}
								}else{
									if(!StringFunctions.isNullOrEmpty(viewAllUsers.getMobileNumber()))
									{
										mobileNumbersSet.add(viewAllUsers.getMobileNumber());
									}
									if("Parent".equalsIgnoreCase(roleDes)){
										if(!StringFunctions.isNullOrEmpty(viewAllUsers.getParentEmail()))
										emailAddresses[0]=viewAllUsers.getParentEmail();
									}else{
										if(!StringFunctions.isNullOrEmpty(viewAllUsers.getStaffEmail()))
										emailAddresses[0]=viewAllUsers.getStaffEmail();
									}
								}
								if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)){
									//Sending message to all roles
									smsStatus = this.sendSMSForStudent(user,mobileNumbersSet,message,user.getCustId(),newPassword);
								}
								if(smsStatus){
									if("Student".equalsIgnoreCase(roleDes)){
										Student studentobj = null;
										studentobj = (Student)this.get(Student.class,"custId="+user.getCustId()+" and accountId="+user.getId());
										message = this.saveMessageDetailsTracking(message,studentobj,null,null);
									}else if("Parent".equalsIgnoreCase(roleDes)){
										 User userObj = null;
										 userObj = (User) this.get(User.class, "custId="+ user.getCustId()+" and id="+user.getId());
										 message = this.saveMessageDetailsTracking(message,null,null,userObj);	
									}else{
										Staff staff = null;
										staff = (Staff) this.get(Staff.class, "custId="+ user.getCustId()+" and id="+user.getId());
										message = this.saveMessageDetailsTracking(message,null,staff,null);
									}
								}
								mailUtil=new MailUtil(emailAddresses,"Regd : Updated Login Credentials",customer.getId(),customer.getSender(),"Administrator",this.getContactFromEmail(customer));
								mailUtil.setContactEmail(customer.getContactEmail());
								mailUtil.setContactPasword(customer.getContactPassword());
								log.debug(viewAllUsers.getPersonFullName());
								mailUtil.sendMailToUpdatedCredentials(user.getUsername().toLowerCase(),newPassword.toLowerCase(),viewAllUsers.getFirstName(),customer.getOrganization(),this.getContactFromEmail(customer),roleDes);
								mailUtil=null;
							user = null;
							viewAllUsers = null;
						*/}
					//}
				//}
				return userStatus;
			}
			//viewAllUserslist = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return userStatus;
	} 
	
	public boolean changeUserPasswordDetails(ChangePasswordVO changePasswordVO){
		boolean userStatus = false;
		try {
			User curuser = (User)this.get(User.class,Long.valueOf(changePasswordVO.getUserId()));
			if(!ObjectFunctions.isNullOrEmpty(curuser)){
				String oldPassword = PasswordUtils.passwordEncoder(changePasswordVO.getOldPassword(),null);
				if(!oldPassword.equals(curuser.getPassword())){
					userStatus = false; 
					return userStatus;
				}
				curuser.setPassword(PasswordUtils.passwordEncoder(changePasswordVO.getNewPassword(), null));
				this.save(curuser);
				userStatus = true;
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return userStatus;
	}
	
	public UserVO getStudentDetailsByAdmissionAndMobileNumbers(String admissionNumber, String mobileNumber, int randomNumber){
		try {
			/*List<ViewStudentPersonAccountDetails> studentsList =null;
			
			studentsList =  this.getAllHqlQuery("FROM ViewStudentPersonAccountDetails vs WHERE vs.admissionNumber = '"+ admissionNumber + "' and (vs.mobileNumber='"+mobileNumber+"' or vs.studentMobile='"+mobileNumber+"')");
			if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
				for(ViewStudentPersonAccountDetails student : studentsList) {*/
					UserVO userVO = null;
					User user = (User) this.getUserByUserName(mobileNumber);
					if(!ObjectFunctions.isNullOrEmpty(user)){
						AcademicYear academicYear  = super.getCurrentAcademicYear("Y",user.getCustId());
						userVO = new UserVO();
						userVO.setFirstName(user.getPerson().getFirstName());
						userVO.setLastName(user.getPerson().getLastName());
						userVO.setUsername(user.getUsername());
						userVO.setRole(user.getUserRoleName());
						if(!ObjectFunctions.isNullOrEmpty(user.getProfileImage()))
						userVO.setUserImageId(user.getProfileImage().getId());
						Customer customer = (Customer) this.get(Customer.class, user.getCustId());
						userVO.setEnabled(user.isEnabled()&& customer.getStatus());
						//Using channel identifier to set random number
						userVO.createIdentifier(user.getId(),user.getCustId(), academicYear.getId(), String.valueOf(randomNumber));
						user = null;
						if(!ObjectFunctions.isNullOrEmpty(mobileNumber))
							sendRandomNumberForReg(mobileNumber,randomNumber,"parent");
						return userVO;
					}
					
				//}
					else return null;
			/*}
			else return null;*/
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	
	public UserVO getStaffDetailsByMobileNumber(String mobileNumber, int randomNumber){
		try {
			UserVO userVO = null;
			List<ViewStaffPersonAccountDetails> staffList =null;
			staffList =  this.getAllHqlQuery("FROM ViewStaffPersonAccountDetails vs WHERE vs.mobileNumber='"+mobileNumber+"'");
			if (!ObjectFunctions.isNullOrEmpty(staffList)) {
				for(ViewStaffPersonAccountDetails staff : staffList) {
					User user = (User) this.getUserByUserName(staff.getUsername());
					if(!ObjectFunctions.isNullOrEmpty(user)){
						AcademicYear academicYear  = super.getCurrentAcademicYear("Y",user.getCustId());
						userVO = new UserVO();
						userVO.setFirstName(user.getPerson().getFirstName());
						userVO.setLastName(user.getPerson().getLastName());
						userVO.setUsername(user.getUsername());
						userVO.setRole(user.getUserRoleName());
						if(!ObjectFunctions.isNullOrEmpty(user.getProfileImage()))
						userVO.setUserImageId(user.getProfileImage().getId());
						Customer customer = (Customer) this.get(Customer.class, user.getCustId());
						userVO.setEnabled(user.isEnabled()&& customer.getStatus());
						//Using channel identifier to set random number
						userVO.createIdentifier(user.getId(),user.getCustId(), academicYear.getId(), String.valueOf(randomNumber));
						user = null;
						if(!ObjectFunctions.isNullOrEmpty(staff.getMobileNumber()))
							sendRandomNumberForReg(mobileNumber,randomNumber,"staff");
						return userVO;
					}
				}
				//return userVO;
			}
			else return null;
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	public void sendRandomNumberForReg(String mobileNumber,  int randomNumber, String account)
	{
		try{
		Set<String> mobileNumbersSet = null;
		boolean smsStatus = false;
		//StringBuffer smsContent =null;
		Messages message = new Messages();
		message.setStatus("M");
		//message.setCreatedById(parentAccount.getId());
		//message.setSenderName(parentAccount.getUserRoleDescription());
		if(!ObjectFunctions.isNullOrEmpty(StringFunctions.getMobileNumberLengthChecking(mobileNumber))){
			mobileNumbersSet=new HashSet<String>(); 
			//mobileNumbersSet = this.addMobileNumbersBasedOnAddressType(customer.getMobileType(),StringFunctions.getMobileNumberLengthChecking(stuPerson.getMobileNumber()),stuPerson.getSecondaryMobileNumber(),StringFunctions.getMobileNumberLengthChecking(stuPerson.getAnotherMobileNumber()),stuPerson.getAnotherSecondaryMobileNumber(),stuPerson.getAddressType());
			mobileNumbersSet.add(mobileNumber);
			if(!ObjectFunctions.isNullOrEmpty(mobileNumbersSet))
				smsStatus =  sendSMSForRandomNumber(mobileNumbersSet,message, randomNumber, account);
				if(smsStatus)
					message = this.saveMessageDetailsTracking(message,null,null,null);
		}
		message = null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean sendSMSForRandomNumber(Set<String> mobileNumbersSet,Messages message, int randomNumber, String account) throws URTUniversalException {
		boolean smsStatus = false;
		try
		{
			Customer masterCustomer=this.getMasterCustomerById();
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)this.getSMSServiceProviderByCustId(masterCustomer.getSmsServiceProviderId());
			StringBuffer smsContent = null;
			if(account.equalsIgnoreCase("parent"))
			smsContent = new StringBuffer("Dear Parent, Your OTP for Eazyschool registration is "+ randomNumber);
			else if(account.equalsIgnoreCase("staff"))
				smsContent = new StringBuffer("Dear Staff, Your OTP for Eazyschool registration is "+ randomNumber);
			smsContent.append(" Thank you from ");
			//smsContent.append(" Eazyschool");
			if(!ObjectFunctions.isNullOrEmpty(smsContent)){
				message.setMessageDescription(smsContent.toString());
				message.setCustomer(masterCustomer);
				message.setSenderName(masterCustomer.getSender());
				smsStatus = this.deliverSms(message,mobileNumbersSet,smsServiceProvider);
				smsContent = null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return smsStatus;
	}
}
