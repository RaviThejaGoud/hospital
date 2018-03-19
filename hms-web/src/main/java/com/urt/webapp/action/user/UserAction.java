package com.urt.webapp.action.user;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.image.CropImage;
import com.churchgroup.util.image.ThumbNail;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.staff.StaffManager;
import com.urt.service.manager.interfaces.subscription.SubscriptionManager;
import com.urt.service.manager.interfaces.user.UserManager;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.webapp.action.base.BaseAction;

/**
 * Action for facilitating User Management feature.
 */
@Namespace("/user")
@Actions( {
	 @Action(value = "ajaxDoGetContactUs", results = { @Result(location = "contactUs.jsp", name = "success") }) 
})
public class UserAction extends BaseAction {
	
	private static final long serialVersionUID = -1646390427462403153L;
	
	private List<User> users;
	 private String              theme            = "ems";
	  private boolean             google           = false;
	  private boolean             ajaxhistory      = false;

	/**
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	@Actions({
			@Action(value = "/create-user", results = { @Result(location = "success.jsp", name = "success") }) })
	public String execute() throws URTUniversalException {

		try
			{
			User user = new User();
	        user.setUsername("ram13456@uroomtech.com");
	        user.setSecondaryUsername("support@eazyschool.com");
			user.setPassword(PasswordUtils.passwordEncoder("ram123",null));
	        Person person = new Person();
	        person.setFirstName("Jadapolu");
	        person.setLastName("Sreeramulu");
	        Address address = new Address();
	        address.setAddressLine1("20th Street 1");
	        address.setCity("Madreas");
	        address.setState("TN");
	        address.setPostalCode("89240");
	        user.setCommunicationAddress(address);
	         address = new Address();
	        address.setAddressLine1("10th Street 1");
	        address.setCity("HYDa");
	        address.setState("AP");
	        address.setPostalCode("45748");
	        user.setPrimaryAddress(address);
	        user.setPerson(person);
	        userManager.save(user);
	     //   List<User> users = userManager.getAll(User.class);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "getAllUsers", results = { @Result(location = "users.jsp", name = "success") }) })
	public String getAllUsers() throws URTUniversalException {

		try
		{
			setUsers(userManager.getAll(User.class));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
}
	@Actions({
		@Action(value = "ajaxDoEditProfile", results = { @Result(location = "ajaxEditProfile.jsp", name = "success") }) })
	public String doEditProfile() throws URTUniversalException {
		try
		{
			long accountId=0;
			if(!ObjectFunctions.isNullOrEmpty(getUser()))
				accountId=getUser().getId();
			if(accountId >0)
			{
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				setCountryList((List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST));
			    setUser((User)subscriptionManager.get(User.class,accountId));
			    if(getUser().isSchoolStudent()){
			    	setStudent((Student)staffManager.get(Student.class," accountId="+accountId+" and academicYearId="+getUserAcademicYearId()));
			    }else{
			    	setStaff((Staff)adminManager.getStaffByAcountId(accountId,Constants.YES_STRING));
			    }
			    if(!ObjectFunctions.isNullOrEmpty(getUser().getPrimaryAddress())){
			    	Country countryObj=(Country) adminManager.get(Country.class,"countryCode='"+getUser().getPrimaryAddress().getCountry()+"'");
			    	if(!ObjectFunctions.isNullOrEmpty(countryObj)){
			    		setTempString(countryObj.getCountryName());
			    	}
			    	countryObj=null;
			    }
			    //setClassList(subscriptionManager.getAll(ClassName.class));
				//setSectionList(subscriptionManager.getAll(Section.class));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
		
}
	public User savePersonAddress(User user){
		try {
			if(!ObjectFunctions.isNullOrEmpty(user))
			{
				Address address = null;
				if(!ObjectFunctions.isNullOrEmpty(user.getPrimaryAddress())){
					address = user.getPrimaryAddress();
					address=((Address)subscriptionManager.get(Address.class, user.getPrimaryAddress().getId()));
				}else
					address =  new Address();
				address.setStreetName(getUser().getPrimaryAddress().getStreetName());
				address.setCity(getUser().getPrimaryAddress().getCity());
				address.setState(getUser().getPrimaryAddress().getState());
				address.setCountry(getUser().getPrimaryAddress().getCountry());
				Country countryObj=(Country) adminManager.get(Country.class,"countryCode='"+getUser().getPrimaryAddress().getCountry()+"'");
		    	if(!ObjectFunctions.isNullOrEmpty(countryObj)){
		    		setTempString(countryObj.getCountryName());
		    		address.setCountryId(countryObj.getId());
		    	}
				address.setPostalCode(getUser().getPrimaryAddress().getPostalCode());
				address.setEmail(getUser().getPrimaryAddress().getEmail());
				address.setLastUpdatedDate(new Date());
				user.setLastUpdatedDate(new Date());
				user.setPrimaryAddress(address);
				if(user.isSchoolTeacher())
					return user;
				else
					return (User)userManager.saveUser(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	@Actions({
		@Action(value = "ajaxEditProfile", results = { @Result(location = "ajaxEditProfileDetails.jsp", name = "success"), @Result(location = "ajaxEditProfileDetails.jsp", name = "actionError") }) })
	public String editProfile() throws URTUniversalException {

		try
		{
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			setCountryList((List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST));
			boolean smsStatus=false;
			boolean defaultSms=false;
		   	long userId=getUser().getId();
				if(userId > 0)
				{
					User luser=((User)subscriptionManager.get(User.class,userId));
			//		Customer customer=(Customer)adminManager.get(Customer.class, getUserCustId());
					String userName=null;
					setCustomer(getCustomerByCustId());
					/*Ganesh Now we are updating staff username who are able to access and as staff . */
					if(!luser.isParent() ||  !luser.isSchoolStudent()){
						if(!StringFunctions.isNullOrEmpty(getUser().getPerson().getMobileNumber())){
							if(!StringFunctions.isNullOrEmpty(getUser().getOTP())){
								if(luser.getOTP().equalsIgnoreCase(getUser().getOTP())){
									userName =  getUser().getPerson().getMobileNumber();
									//luser.setUsername(userName);
									User user =userManager.usernameAvailabulity(userName,getUserCustId());
									if(!ObjectFunctions.isNullOrEmpty(user)){
										/*Ganesh If enter mobile number already exist with user we will check user if user is staff we will not allow to add user if the existing user is parent we will allow the user and add "S" before the username create the username even that username available with "S" also we will restrict user.*/
										if(user.isParent()){
											userName ="S"+userName;
											user =userManager.usernameAvailabulity(userName,getUserCustId());
											if(ObjectFunctions.isNullOrEmpty(user)){
												luser.setUsername(userName);
												defaultSms=true;
											}
										}
									}else
										luser.setUsername(userName);
										
									smsStatus=true;
								}else{
									super.addActionError("Please enter valid OTP");
									return "actionError";
								}
							}
						}
					}
					
					
					Staff lstaff=((Staff)adminManager.getStaffByAcountId(userId,Constants.YES_STRING));
				    Student lstudent=((Student)staffManager.getStudentByAcountId(userId));
				    Person person = null;
				    if(ObjectFunctions.isNullOrEmpty(luser.getPerson()))
				    	person = new Person();
				    else
				    	person = luser.getPerson();
					if(luser.isParent()){
					   // User studUser = null;
					    String oldState = null;
					    String oldStreet = null;
					    String oldPincode = null;
					    String oldCity = null;
					    String oldPhoneNumber,oldMobileNumber2,oldMobileNumber1,oldEmail = null;
					   // studUser = ((User)subscriptionManager.get(User.class,"id="+luser.getStudentParent().getId()));
					    List<ViewAllUsers> vwusersList = subscriptionManager.getAllHqlQuery("from ViewAllUsers where roleId = 3 and parentId="+ luser.getId()+" and accountEnabled='"+Constants.YES_STRING+"' and accountExpired='"+Constants.NO_STRING+"'");
					    if(!ObjectFunctions.isNullOrEmpty(vwusersList))
					    {
					    	for(ViewAllUsers vwUser : vwusersList)
						    {
							    //studUser = ((User)subscriptionManager.get(User.class,"id="+luser.getStudentParent().getId()));
					    		User studUser = ((User)subscriptionManager.get(User.class,"id="+vwUser.getAccountId()));
								oldEmail = studUser.getPerson().getParentEmail();
								oldMobileNumber1 = studUser.getPerson().getMobileNumber();
								oldMobileNumber2 = studUser.getPerson().getSecondaryMobileNumber();
								oldPhoneNumber = studUser.getPerson().getPhoneNumber();
								Person studPerson = null;
							    studPerson = studUser.getPerson();
								/*** for Saving Parent information in to Student Records ***/
								studPerson.setFatherName(getUser().getPerson().getFirstName());
								studPerson.setParentEmail(getUser().getPerson().getParentEmail());
								studPerson.setMobileNumber(getUser().getPerson().getMobileNumber());
								studPerson.setSecondaryMobileNumber(getUser().getPerson().getSecondaryMobileNumber());
								studPerson.setPhoneNumber(getUser().getPerson().getPhoneNumber());
								if(!ObjectFunctions.isNullOrEmpty(studUser.getPrimaryAddress())){
									if(!ObjectFunctions.isNullOrEmpty(studUser.getPrimaryAddress().getState())){
										State stateName = ((State)subscriptionManager.get(State.class,"stateCode='"+studUser.getPrimaryAddress().getState()+"'"));
										oldState =  stateName.getStateName();
									}
									if(!ObjectFunctions.isNullOrEmpty(studUser.getPrimaryAddress().getStreetName()))
										oldStreet = studUser.getPrimaryAddress().getStreetName();
									if(!ObjectFunctions.isNullOrEmpty(studUser.getPrimaryAddress().getStreetName()))
										oldCity =  studUser.getPrimaryAddress().getCity();
									if(!ObjectFunctions.isNullOrEmpty(studUser.getPrimaryAddress().getPostalCode()))
										oldPincode =  studUser.getPrimaryAddress().getPostalCode();
								}
								studUser.setPerson(studPerson);
							    savePersonAddress(studUser);
							    State stateNames = ((State)subscriptionManager.get(State.class,"stateCode='"+getUser().getPrimaryAddress().getState()+"'"));
							    String newStateName = stateNames.getStateName();
							    if(getCustomer().isCheckEmailService()){
									if((!studUser.getPerson().getParentEmail().equalsIgnoreCase(oldEmail)) || (!studUser.getPerson().getMobileNumber().equalsIgnoreCase(oldMobileNumber1)) || (!studUser.getPerson().getSecondaryMobileNumber().equalsIgnoreCase(oldMobileNumber2)) || (!studUser.getPerson().getPhoneNumber().equalsIgnoreCase(oldPhoneNumber)) 
											|| (!studUser.getPrimaryAddress().getStreetName().equalsIgnoreCase(oldStreet)) || (!studUser.getPrimaryAddress().getCity().equalsIgnoreCase(oldCity)) || (!newStateName.equalsIgnoreCase(oldState)) || (!studUser.getPrimaryAddress().getPostalCode().equalsIgnoreCase(oldPincode))){
										String[] emailAddresses = new String[1];
										Student studentStudyClass = null;
										studentStudyClass = ((Student)subscriptionManager.get(Student.class,"accountId="+studUser.getId()));
										emailAddresses[0]=getCustomer().getCustEmail();
										MailUtil mailUtil=new MailUtil(emailAddresses,"Regd : Parent Updated Communication Details",getCustomer().getId(),getCustomer().getSender(),"Administrator",this.getContactFromEmail(getCustomer()));
										mailUtil.setContactEmail(getCustomer().getContactEmail());
										mailUtil.setContactPasword(getCustomer().getContactPassword());
										mailUtil.sendMailToAdminWhenParentChangeEmail(studUser,studentStudyClass.getClassSection().getClassAndSection(),oldMobileNumber1,oldMobileNumber2,oldPhoneNumber,oldCity,oldStreet,oldState,oldPincode,oldEmail,newStateName,this.getContactFromEmail(getCustomer()));
										mailUtil=null;
									}
								}
							    studUser = null;
						    }
					    }
					}
					if(luser.isSchoolStudent())
					{
						subscriptionManager.save(lstudent);
					}					
					if(!ObjectFunctions.isNullOrEmpty(luser.getPerson()))
					{
						person.setFirstName(getUser().getPerson().getFirstName());
						person.setLastName(getUser().getPerson().getLastName());
						person.setFatherName(getUser().getPerson().getFatherName());
						person.setMotherName(getUser().getPerson().getMotherName());
						person.setDateOfBirth(getUser().getPerson().getDateOfBirth());
						person.setBloodGroup(getUser().getPerson().getBloodGroup());
						person.setMaritalStatus(getUser().getPerson().getMaritalStatus());
						person.setPhoneNumber(getUser().getPerson().getPhoneNumber());
						person.setMobileNumber(getUser().getPerson().getMobileNumber());
						person.setSecondaryMobileNumber(getUser().getPerson().getSecondaryMobileNumber());
						person.setExperience(getUser().getPerson().getExperience());
						person.setParentEmail(getUser().getPerson().getParentEmail());
						person.setStudentEmail(getUser().getPerson().getStudentEmail());
						person.setStudentMobile(getUser().getPerson().getStudentMobile());
						person.setSummary(getUser().getPerson().getSummary());
						person.setRationCardNumber(getUser().getPerson().getRationCardNumber());
						person.setCommunityNumber(getUser().getPerson().getCommunityNumber());
						luser.setPerson(person);
					}
					if(luser.isSchoolTeacher())
					{
						User user = savePersonAddress(luser);
						lstaff.setQualification1(getStaff().getQualification1());
	   					lstaff.setQualification2(getStaff().getQualification2());
	   					lstaff.setAccount(user);
						subscriptionManager.save(lstaff);
					}else
						savePersonAddress(luser);
					if(!ObjectFunctions.isNullOrEmpty(lstaff)){
						adminManager.sendStaffNotificationWhenEditOrAdd(lstaff.getId(),"Staff Profile Update","Staff Profile Update");
					}
					if(smsStatus)
						sendStaffMobilenumberModification(luser,getUser().getPerson().getMobileNumber(),defaultSms,getCustomer());
					setUser(luser);
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(luser, luser.getPassword(),luser.getAuthorities());	
					SecurityContextHolder.getContext().setAuthentication(auth);	
				}
				super.addActionMessage("Profile updated successfully ");
			}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
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

	/**
	 * @param subscriptionManager the subscriptionManager to set
	 */
	public void setSubscriptionManager(SubscriptionManager subscriptionManager) {
		this.subscriptionManager = subscriptionManager;
	}
	@Actions({
		@Action(value = "ajaxDoChangePassword", results = { @Result(location = "ajaxChangePassword.jsp", name = "doChangePassword") })})
	public String doChangePassword(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doChangePassword' method");
		}
        try{
            //setCustomerId(getParamValue("custId"));
            if(StringFunctions.isNullOrEmpty(String.valueOf(getUserCustId()))){
    				setCustId(getUserCustId());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return "doChangePassword";
    } 

	@Actions( { @Action(value = "ajaxChangePassword", results = { @Result(location = "ajaxEditProfileDetails.jsp", name = "success") }),
		@Action(value = "ajaxChangeParentPassword", results = { @Result(location = "ajaxChangeParentPassword.jsp", name = "success") })  })
	public String changePassword(){
		if (log.isDebugEnabled()) {
		log.debug("Entering 'changePassword' method");
		}
		try {
			//long userId = Long.valueOf(getParamValue("user.id"));  getUser(); //
			User user= (User)userManager.get(User.class, getUser().getId());
			if(!ObjectFunctions.isNullOrEmpty(user)){
				String encryptPassword=PasswordUtils.passwordEncoder(getUser().getPassword(),null); 				
				user.setPassword(encryptPassword);
				user.setPasswordStatus(false);
				userManager.save(user);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),user.getAuthorities());	
				SecurityContextHolder.getContext().setAuthentication(auth);	
				user=null;
			}
			super.addActionMessage("Password updated successfully ");
			doEditProfile();
		}
		catch (Exception ex) {
		log.error(" entering Catch Block of changePassword():" + ex.getMessage());
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
		}

	/**
	 * @param staffManager the staffManager to set
	 */
	public void setStaffManager(StaffManager staffManager) {
		this.staffManager = staffManager;
	}
	@Action(value = "ajaxUserRegistation", results = { @Result(location = "login.jsp", name = "success") })
	public String saveUser() throws URTUniversalException {
		try {
			if (!ObjectFunctions.isNullOrEmpty(getNewUser())) {
				String encryptPassword = PasswordUtils.passwordEncoder(getNewUser().getPassword(), null);
				getNewUser().setPassword(encryptPassword);
				if (!ObjectFunctions.isNullOrEmpty(getNewUser().getPerson())) {
					getNewUser().setPerson(getNewUser().getPerson());
				}
				/*if (!ObjectFunctions.isNullOrEmpty(getNewUser().getPrimaryContact())) {
					getNewUser().setPrimaryContact(getNewUser().getPrimaryContact());
				}*/
				if (!ObjectFunctions.isNullOrEmpty(getNewUser().getPrimaryAddress())) {
					getNewUser().setPrimaryAddress(getNewUser().getPrimaryAddress());
				}
				getNewUser().addRole(userManager.getRoleByName(Constants.USER_ROLE));
				//adminManager.save(getNewUser());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Action(value = "ajaxCancelContactUs", results = { @Result(location = "login.jsp", name = "success") })
	public String cancelContactUs() throws URTUniversalException {
		try {
			String url=getHostUrl()+"/j_spring_security_logout";
			getResponse().sendRedirect(url);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Action(value = "ajaxAddContactUs", results = { @Result(location = "login.jsp", name = "success") })
	public String ajaxAddContactUs() throws URTUniversalException {
		try {
			String url=getHostUrl()+"/j_spring_security_logout";
			getResponse().sendRedirect(url);
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/*
	@Actions({
		@Action(value = "ajaxChangePassword", results = { @Result(location = "ajaxChangePassword.jsp", name = "changePassword") })})
	public String changePassword() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'changePassword' method");
		}
		try{
			User user=getUser();
			if(!ObjectFunctions.isNullOrEmpty(user)){
				String encryptPassword=passwordEncoder(getUser().getPassword(),null); 				
				user.setPassword(encryptPassword);
				userManager.save(user);
				user=null;
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return "changePassword";
	}*/
	@Actions( { @Action(value = "ajaxAddUserImage", results = { @Result(location = "ajaxEditProfile.jsp", name = "success") }) })
	public String addUserImage()throws URTUniversalException
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addUserImage' method");
		}
		try
		{
			long userId=getUser().getId();
			long customerId=0;
			if(StringFunctions.isNotNullOrEmpty(String.valueOf(userId)))
			{	
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				User luser=((User)subscriptionManager.get(User.class,userId));
				Customer customer=(Customer)adminManager.get(Customer.class, getUserCustId());
				long userImageId = 0;
				if(getUploadFileName()!= null){
					if(!ObjectFunctions.isNullOrEmpty(luser.getProfileImage()))
						userImageId = luser.getProfileImage().getId();
					UserImage attachment=null;
				try {
					if(!ObjectFunctions.isNullOrEmpty(customer)){
						attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE,customer.getId(),getUserAcademicYearId(),userImageId);
					}else{
						attachment = profileImageUpload(Constants.FILE_TYPE_IMAGE,customerId,getUserAcademicYearId(),userImageId);
					}
					if(!ObjectFunctions.isNullOrEmpty(attachment))
		             {
						luser.setProfileImage(attachment);// when student login and upload the profile image save the imageId in account table changed by cvs
						super.addActionMessage("Photo updated successfully.");
		             }
					 userManager.saveUser(luser);
					 if(!luser.getRoleName().equalsIgnoreCase(Constants.SCHOOL_STUDENT))
					 {
						 Staff staff = (Staff)adminManager.getStaffByAcountId(luser.getId(),Constants.YES_STRING);
						 if(!ObjectFunctions.isNullOrEmpty(staff)){
							 userManager.sendStaffNotificationWhenEditOrAdd(staff.getId(), "Staff Profile Update", "Staff Profile Update");
						 }
					 }
					//getResponse().sendRedirect(getRequest().getContextPath()+"/subscription/userAccess.do?requestURL="+getRequest().getContextPath()+"/student/studentHome.do");
					 setUser(luser);
					 luser =null;
					 customer = null;
					attachment = null;
				} catch (Throwable e) {
					e.printStackTrace();
				}	
			}
		  }
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( {
		@Action(value = "ajaxCropUserImage", results = { @Result(location = "ajaxEditProfile.jsp", name = "success") }) })
	public String ajaxCropUserImage()throws URTUniversalException
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCropUserImage' method");
		}
		try
		{
			CropImage cropImage=new CropImage();
			User user=userManager.getAccountByCustIdAndStatus(getUser().getUsername(),getUserCustId(),Constants.YES_STRING);
			setUser(user);
			cropImage.cropImage(getSession().getServletContext().getRealPath(getUser().getProfileImage().getPath()+getUser().getProfileImage().getName()),getSession().getServletContext().getRealPath(getUser().getProfileImage().getPath()+getUser().getProfileImage().getThumbNail()),getX(),getY(),getW(),getH());
			ThumbNail thumbNail = new ThumbNail();
			thumbNail.createThumbNail(getSession().getServletContext().getRealPath(getUser().getProfileImage().getPath()+getUser().getProfileImage().getThumbNail()), getSession().getServletContext().getRealPath(getUser().getProfileImage().getPath()+"thumb_"+getUser().getProfileImage().getName()));
			ThumbNail stampThumbNail = new ThumbNail(200,61);
			stampThumbNail.createStamp(getSession().getServletContext().getRealPath(getUser().getProfileImage().getPath()+"thumb_"+getUser().getProfileImage().getName()), getSession().getServletContext().getRealPath(getUser().getProfileImage().getPath()+"stamp_"+getUser().getProfileImage().getName()));
			doEditProfile();
 		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	public void sendStaffMobilenumberModification(User user, String mobileNumber,boolean defaultSms,Customer customer)throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'sendStaffMobilenumberModification' method");
		}
		try {
			getSmsCount();
			if (!ObjectFunctions.isNullOrEmpty(user) && StringFunctions.isNotNullOrEmpty(mobileNumber) && mobileNumber.length() == 10) {
				if (!ObjectFunctions.isNullOrEmpty(customer) && customer.isCheckMobileService() && !StringFunctions.isNullOrEmpty(mobileNumber) && (getSmsAlloted() != 0 && getSmsAlloted() > getSmsCnt())) {
					SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
					Messages message = new Messages();
					StringBuffer msgContent = new StringBuffer();
					Set<String> mobileNumberset = new HashSet<String>();
					if(defaultSms)
						msgContent.append("Dear "+ user.getFullPersonName()+ ", Your mobile number is updated successfully and your user name is changed to "+user.getUsername()+".Thank you from ");
					else
						msgContent.append("Dear "+ user.getFullPersonName()+ ", Your mobile number is updated successfully and your username is changed to your new mobile number. Thank you from ");
					log.debug(msgContent.toString());
					message.setMessageDescription(msgContent.toString());
					message.setCreatedById(getUser().getId());
					message.setAcademicYear(getCurrentAcademicYear());
					message.setPurposeType("regd: OTP");
					message.setCustomer(customer);
					message.setSmsSenderId(customer.getSender());
					mobileNumberset.add(mobileNumber);
					if (ObjectFunctions.isNotNullOrEmpty(mobileNumberset)) {
						message = communicationManager.saveMessageDetailsTracking(message, null,null, user);
						communicationManager.deliverSms(message,mobileNumberset, smsServiceProvider);
						msgContent = null;
						mobileNumberset = null;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
	}
}