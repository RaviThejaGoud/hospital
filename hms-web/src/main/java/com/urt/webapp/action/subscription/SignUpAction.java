package com.urt.webapp.action.subscription;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.AdmissionSettings;
import com.urt.persistence.model.common.CastSettings;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.FeedbackGrades;
import com.urt.persistence.model.common.GovStaffInfo;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.CustomerEnrollmentRequest;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.InviteCustomerEnrollment;
import com.urt.persistence.model.customer.Organization;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.exam.CommonType;
import com.urt.persistence.model.exam.KBankType;
import com.urt.persistence.model.exam.SubType;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.study.GeneralSubjects;
import com.urt.persistence.model.study.SchoolCategory;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.SyllabusType;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.subscription.OrganizationTypes;
import com.urt.persistence.model.subscription.PackageDetails;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.webapp.action.base.BaseAction;



/**
 * Action for facilitating Resources Management feature.
 */
@Namespace("/signup") 
@Actions( {
	@Action(value = "doForgotPassword", results = { @Result(location = "forgotPassword.jsp", name = "success") }),
	@Action(value = "doParentRegistration", results = { @Result(location = "doParentRegistration.jsp", name = "success") }),
    @Action(value = "ajaxDoViewAdminDetails", results = { @Result(location = "popupUserEnquiry.jsp", name = "success") }),
	@Action(value = "ajaxCapturePhotoForAdmissions", results = { @Result(location ="../admin/admission/ajaxPopupCapturePhoto.jsp", name = "success") })
	
})
public class SignUpAction extends BaseAction {
	
	private static final long serialVersionUID = -1646390427462403153L;

	protected Customer customer;

	protected User regUser;
	protected List subscriptionList;
	protected String productName;
	protected double productPrice;
	protected String subscriptionType;
	private String renewdate;
	private String amountWithoutTax;
	private String amountWithTax;
	private String renewDay;
	private long productId;
	private Person person;
	protected OrganizationTypes organizationTypes;
	private List customersList;
	private long stateId;
	

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public List getCustomersList() {
		if(ObjectFunctions.isNullOrEmpty(this.customersList)){
			this.customersList=new ArrayList();
		}
		return this.customersList;
	}

	public void setCustomersList(List customersList) {
		this.customersList = customersList;
	}
	/**
	 * @return the organizationTypes
	 */
	public OrganizationTypes getOrganizationTypes() {
		if(ObjectFunctions.isNullOrEmpty(this.organizationTypes)){
			this.organizationTypes=new OrganizationTypes();
		}
		return organizationTypes;
	}

	/**
	 * @param organizationTypes the organizationTypes to set
	 */
	public void setOrganizationTypes(OrganizationTypes organizationTypes) {
		this.organizationTypes = organizationTypes;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List getSubscriptionList() {
		if (ObjectFunctions.isNullOrEmpty(this.subscriptionList)) {
			this.subscriptionList = new ArrayList();
		}
		return subscriptionList;
	}

	public void setSubscriptionList(List subscriptionList) {
		this.subscriptionList = subscriptionList;
	}
	/**
		 * @return the customer
		 */
		public Customer getCustomer() {
			if(ObjectFunctions.isNullOrEmpty(this.customer)){
				this.customer=new Customer();
			}
			return customer;
		} 

		/**
		 * @param customer the customer to set
		 */
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		/**
		 * @return the productName
		 */
		public String getProductName() {
			return productName;
		}

		/**
		 * @param productName the productName to set
		 */
		public void setProductName(String productName) {
			this.productName = productName;
		}

		/**
		 * @return the productPrice
		 */
		public double getProductPrice() {
			return productPrice;
		}

		/**
		 * @param productPrice the productPrice to set
		 */
		public void setProductPrice(double productPrice) {
			this.productPrice = productPrice;
		}

		/**
		 * @return the subscriptionType
		 */
		public String getSubscriptionType() {
			return subscriptionType;
		}

		/**
		 * @param subscriptionType the subscriptionType to set
		 */
		public void setSubscriptionType(String subscriptionType) {
			this.subscriptionType = subscriptionType;
		}
		/**
		 * @return the renewdate
		 */
		public String getRenewdate() {
			return renewdate;
		}

		/**
		 * @param renewdate the renewdate to set
		 */
		public void setRenewdate(String renewdate) {
			this.renewdate = renewdate;
		}

		/**
		 * @return the amountWithoutTax
		 */
		public String getAmountWithoutTax() {
			return amountWithoutTax;
		}

		/**
		 * @param amountWithoutTax the amountWithoutTax to set
		 */
		public void setAmountWithoutTax(String amountWithoutTax) {
			this.amountWithoutTax = amountWithoutTax;
		}

		/**
		 * @return the amountWithTax
		 */
		public String getAmountWithTax() {
			return amountWithTax;
		}

		/**
		 * @param amountWithTax the amountWithTax to set
		 */
		public void setAmountWithTax(String amountWithTax) {
			this.amountWithTax = amountWithTax;
		}

		/**
		 * @return the renewDay
		 */
		public String getRenewDay() {
			return renewDay;
		}

		/**
		 * @param renewDay the renewDay to set
		 */
		public void setRenewDay(String renewDay) {
			this.renewDay = renewDay;
		}
		
		/**
		 * @return the regUser
		 */
		public User getRegUser() {
			if(ObjectFunctions.isNullOrEmpty(this.regUser)){
				this.regUser=new User();
			}
			return regUser;
		}

		/**
		 * @param regUser the regUser to set
		 */
		public void setRegUser(User regUser) {
			this.regUser = regUser;
		}

		
	
	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}
	@Actions({
		@Action(value = "admissionDetails", results = { @Result(location = "ajaxAdmissionSettings.jsp", name = "success") }),
		@Action(value = "publicViewAdmissionDetails", results = { @Result(location = "ViewAdmissionDetails.jsp", name = "success") })
	})
	public String admissionDetails() throws URTUniversalException {
		try 
		{ 
			String custId = getParamValue("id");
        	List<AdmissionSettings> admissionSettingsList=adminManager.getAll(AdmissionSettings.class, " custId="+custId+" and status='Y'");
        	Customer customer = (Customer)adminManager.get(Customer.class,"id="+custId);
    		if(!ObjectFunctions.isNullOrEmpty(customer)){
    			setCustomer(customer);
    		}
        	if(!ObjectFunctions.isNullOrEmpty(admissionSettingsList))
			{
        		for(AdmissionSettings admissionSettings: admissionSettingsList){
        			Date admissionEnd=admissionSettings.getEndDate();
					Date date=new Date();
					Calendar cal = Calendar.getInstance();
					Calendar calClose = Calendar.getInstance();
					calClose.setTime(admissionEnd);
		        	cal.setTime(date);
		        	String startMonth = new SimpleDateFormat("MM").format(cal.getTime());
	     	        String startDateDay = new SimpleDateFormat("dd").format(cal.getTime());
	 				String startDayYear= new SimpleDateFormat("yyyy").format(cal.getTime());
	 		        int currentDateDay1=Integer.parseInt(startDateDay);
	 		        int startMonthDay=Integer.parseInt(startMonth);
	 		        int startYear1=Integer.parseInt(startDayYear);
	 		        String closeDay = new SimpleDateFormat("MM").format(calClose.getTime());
	    	        String closeDateDay = new SimpleDateFormat("dd").format(calClose.getTime());
					String closeDayYear= new SimpleDateFormat("yyyy").format(calClose.getTime());
			        int closeDateDay1=Integer.parseInt(closeDay);
			        int closeMonthDay=Integer.parseInt(closeDateDay);
			        int closeYear1=Integer.parseInt(closeDayYear);
	 		        Calendar currentDate=Calendar.getInstance();
	 		        Calendar closeDate=Calendar.getInstance();
	 		        currentDate.set(startYear1, startMonthDay, currentDateDay1);
	 		        closeDate.set(closeYear1, closeDateDay1,closeMonthDay);
	 		        if(currentDate.before(closeDate) || currentDate.equals(closeDate)) {
	 		        	admissionSettings.setAdmissionOpenOrClose("Y");
	 		         }
					getAdmissionSettingsList().add(admissionSettings);
					admissionSettings=null;
        		}
        		customer = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "registration", results = { @Result(location = "registration.jsp", name = "success") }),
		@Action(value = "onlineApplicationForm", results = { @Result(location = "ajaxOnlineApplicationForm.jsp", name = "success") })
	})
	public String onlineApplcationForm() throws URTUniversalException {
		try{
			
			setCustId(Long.valueOf(getParamValue("customerId")));
			setAdmissionSettings(adminManager.getAdmissionSettingsByCustIdAndYear(getCustId(), Long.valueOf(getParamValue("academicYearId"))));
			AcademicYear academicYear=adminManager.getCurrentAcademicYear(Constants.YES_STRING, getCustId());
			if(!ObjectFunctions.isNullOrEmpty(getAdmissionSettings())){
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));	
				setCastSettingList(adminManager.getAllByCustId("CastSettings",getCustId(),0));
				setClassList(adminManager.getClassesByClassIdsAndAdmissionStatus(getCustId(),academicYear.getId(),Constants.YES_STRING,null,true));
				setTempList(adminManager.getAllClassNames(getCustId(),academicYear.getId()));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "onlineApplicationFormByCustomer", results = { @Result(location = "onlineApplicationFormByCustomer.jsp", name = "success") })
	public String onlineApplicationFormByCustomer() throws URTUniversalException {
		try{
			setAdmissionSettings((AdmissionSettings)adminManager.get(AdmissionSettings.class,getAdmissionSettings().getId()));
			if(!ObjectFunctions.isNullOrEmpty(getAdmissionSettings())){
				AcademicYear academicYear=getAdmissionSettings().getAcademicYear();
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					setAcademicYear(academicYear);
				setCustomer((Customer)adminManager.get(Customer.class,academicYear.getCustId()));
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));	
				setCastSettingList(adminManager.getAllByCustId("CastSettings",academicYear.getCustId(),0));
				setClassList(adminManager.getClassesByClassIdsAndAdmissionStatus(academicYear.getCustId(),academicYear.getId(),Constants.YES_STRING,null,true));
				setTempList1(adminManager.getAll(CommonType.class, "custId="+ academicYear.getCustId() + " and type='RELIGION'"));
				setRouteList(adminManager.getAll(Route.class,"custId="+academicYear.getCustId()+" and academicYearId="+academicYear.getId()));
				academicYear = null;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "parentRegStep1", results = { @Result(location = "doParentRegistration.jsp", name = "success") })})
	public String parentRegStep1() throws URTUniversalException {
		try{
			List<Object[]> parentsList = userManager.searchParentMobileNumber(getAnyId(),getAnyTitle());
			if(parentsList.size()>0){
				for(Object[] object : parentsList) {
					if(!ObjectFunctions.isNullOrEmpty(object)) { 
						if(!ObjectFunctions.isNullOrEmpty(object[1])){
							setTempString(object[0].toString());//moble number
							setPlTitle(object[1].toString()); //email Id
							break;
						}
						else{
							super.addActionMessage("Thank you, You are in system but your email not exit in system. Please Contact "+object[2]+" - "+object[3]+".");
							break;
						}
					}
				}
					
			}else{
				super.addActionError("Oops! your email or mobile number are not exist in system. Please contact your children's school.");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "parentRegStep2", results = { @Result(location = "parentRegStep2.jsp", name = "success") })})
	public String parentRegStep2() throws URTUniversalException {
		try{
			long accId=Long.valueOf(getParamValue("accountId"));
			if(!ObjectFunctions.isNullOrEmpty(accId)){
				User user = (User) adminManager.get(User.class,accId);
				setTempId(user.getId());
				ViewStudentPersonAccountDetails viewStudentPersonAccountDetails=studentManager.getStudentDetailsByAccountIdandStatus(accId,Constants.YES_STRING);
					if(!ObjectFunctions.isNullOrEmpty(viewStudentPersonAccountDetails))
					{
						setViewStudentPersonAccountDetails(viewStudentPersonAccountDetails);
					}	 
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	 
	@Actions({
		@Action(value = "ajaxParentRegStep3", results = { @Result(location = "parentRegStep3.jsp", name = "success"), @Result(location = "parentRegStep2.jsp", name = "userExist")}) })  
	public String parentRegStep3() throws URTUniversalException {
		try{
			String parentId = null;
			User user = null;
			Person person=null;
			long accountId=Long.valueOf(getParamValue("tempId"));
			if(!ObjectFunctions.isNullOrEmpty(accountId)){
				user = (User) adminManager.get(User.class,accountId);
				if (!StringFunctions.isNullOrEmpty(getViewStudentPersonAccountDetails().getParentEmail())) {
					User luser = ((User) userManager .getAccountByCustIdAndStatus(getViewStudentPersonAccountDetails().getParentEmail(),user.getCustId(),Constants.YES_STRING));
					if (ObjectFunctions.isNullOrEmpty(luser)) {
						person =new Person();
						person.setFatherName(getViewStudentPersonAccountDetails().getFatherName());
						person.setFirstName(getViewStudentPersonAccountDetails().getFatherName());
						person.setLastName(getParamValue("lastName"));
						person.setMobileNumber(getViewStudentPersonAccountDetails().getMobileNumber());
						person.setParentEmail(getViewStudentPersonAccountDetails().getParentEmail());
						person.setDateOfBirth(getStartDate());
						adminManager.save(person);
						User parentAccount = new User();
						parentAccount.setUsername(getViewStudentPersonAccountDetails().getParentEmail());
						parentAccount.setPassword(PasswordUtils.passwordEncoder(getParamValue("password"), null));
						parentAccount.setCustId(user.getCustId());
						parentAccount.addNewRole(adminManager.getRoleByName(Constants.SCHOOL_PARENT));
						parentAccount.setPerson(person);
						parentAccount =(User)adminManager.save(parentAccount);
						parentId = parentAccount.getStrId();
						user.getPerson().setFatherName(getViewStudentPersonAccountDetails().getFatherName());
						user.getPerson().setParentEmail(getViewStudentPersonAccountDetails().getParentEmail());
						user.getPerson().setMobileNumber(getViewStudentPersonAccountDetails().getMobileNumber());
						user.addParentChild(parentAccount);
						adminManager.save(user);
						parentAccount = null;
						person = null;	
						super.addActionMessage("ParentRegistration added successfully");
					}else {
						super.addActionError("userName already exist.");
						return "userExist";
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "ajaxExistingParentRegStep3", results = { @Result(location = "parentRegStep3.jsp", name = "success"),@Result(location = "parentRegStep2.jsp", name = "userNotExist")}) })  
	public String exitParentRegStep3() throws URTUniversalException {
		try{
			User user = null;
			Person person=null;
			if(!StringFunctions.isNullOrEmpty(getParamValue("tempId"))){
				long accountId=Long.valueOf(getParamValue("tempId"));
				if(!ObjectFunctions.isNullOrEmpty(accountId)){
					user = (User) adminManager.get(User.class,accountId);
					if (!StringFunctions.isNullOrEmpty(getAnyTitle())) {
						User parentUser=userManager.getUserByUserName(getAnyTitle());
						if (!ObjectFunctions.isNullOrEmpty(parentUser)) {
							boolean isOnlyParentRole=false;
							Set<Role> roles=parentUser.getRoles();
							if(ObjectFunctions.isNotNullOrEmpty(roles)){
								if(roles.size() == 1){
									for(Role role:roles){
										if("ROLE_PARENT".equalsIgnoreCase(role.getName()))
											isOnlyParentRole=true;
									}
								}
							}
							if(isOnlyParentRole){
								person=(Person)adminManager.get(Person.class, accountId);
								user.addParentChild(parentUser);
								user.getPerson().setFatherName(person.getFatherName());
								user.getPerson().setParentEmail(parentUser.getUsername());
								user.getPerson().setMobileNumber(person.getMobileNumber());
								adminManager.save(user);
								super.addActionMessage("ParentRegistration added successfully");
							}else{
								super.addActionError("This user have multiple roles.");
								return "userNotExist";
							}
						}
						else {
							super.addActionError("userName not exist.");
							return "userNotExist";
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	} 
	
	@Actions({
        @Action(value = "exportOnlineApplicationPDFGenerator", results = { @Result(location = "jasper/admission/application.jasper", type="jasper", name = "success",params = {"dataSource","objectList","format", "PDF"}) }) })
   
        public String exportOnlineTicketPDFGenerator()
        {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'exportOnlineTicketPDFGenerator' method");
        }
        try
            {
	            String applicationNumber =  getParamValue("apNo");
	            if(StringFunctions.isNotNullOrEmpty(applicationNumber)){
	            	Customer customer = (Customer)adminManager.get(Customer.class,getCustId());
	            	if(!ObjectFunctions.isNullOrEmpty(customer)){
	            		setAnyTitle(customer.getOrganization());
	            		setTempString(customer.getCustomerFormattedAddress());
	            	}
	            	String date=DateFormatter.formatDate(DateFormatter.YYYYMMDDHHMMSS_PATTERN, new Date());
            		getObjectList().add(adminManager.getDetailsByApplicationNumberAndView(applicationNumber));
                    getResponse().setHeader("Content-Disposition","attachment; filename=PrintApplication"+date+".pdf");
                    customer = null;
	            }
            }
            catch(Exception ex)
            {
                log.error("Entering into 'catch block':"+ex.getMessage());
                ex.printStackTrace();
            }
            return SUCCESS;
        }
    @Action(value = "eazyContactUs", results = { @Result(location = "login.jsp", name = "success")})
    public String ajaxEazyContactUs() throws URTUniversalException {
        try {
            if (!StringFunctions.isNullOrEmpty(getContactUs().getCustomerName()) && !StringFunctions.isNullOrEmpty(getContactUs().getCustomerEmail()) && !StringFunctions.isNullOrEmpty(getContactUs().getPhoneNumber()) && !StringFunctions.isNullOrEmpty(getContactUs().getComments()))
            {   
               
                adminManager.save(getContactUs());
                String getProtocol=getRequest().getScheme();
                String getDomain=getRequest().getServerName();
                String path = getRequest().getContextPath();
                String urlPath = getProtocol+"://"+getDomain+path;
                List adminsList=adminManager.getAllAdminsByUrlPath(urlPath);
                if(!ObjectFunctions.isNullOrEmpty(adminsList)){
                    Customer customer  = null;
                    String[] emailAddresses =null;
                    MailUtil mailUtil=null;
                    for(Object obj:adminsList){
                    	customer  = (Customer)obj;
                    	emailAddresses = new String[2];
                    	mailUtil=new MailUtil(emailAddresses,"Information You Requested from Eazy School",customer.getId(),customer.getSender(),getUser().getUserRoleDescription(),"messages@eazyschool.com");
             	        emailAddresses[0]=customer.getCustEmail();
                    	mailUtil.sendMailForContactUs(getContactUs());
                        customer = null;
                        mailUtil=null;
                        emailAddresses=null;
                    }
                }
            }
        } catch (Exception ex) {	
            ex.printStackTrace();
        }
        return SUCCESS;
    }
    
	    @Actions({
			@Action(value = "ajaxMasterSaveCustomerDetails", results = { @Result(location = "../masterAdmin/ajaxCustomerDetails.jsp", name = "success")}),
			@Action(value = "ajaxMasterSaveNewUrlCustomerDetails", results = { @Result(location = "../masterAdmin/ajaxViewCustomerRegistrationDetails.jsp", name = "success") }),
			@Action(value = "ajaxSaveCustomerDetails", results = { @Result(location = "ajaxRegistrationSuccess.jsp", name = "success"), @Result(location = "registration.jsp", name = "signup")})
		})
		public String ajaxSaveCustomerDetails() throws URTUniversalException {
			try{
				
				CustomerEnrollmentRequest customerEnrollmentRequest = null;
				InviteCustomerEnrollment inviteCustomerEnrollment = null;
				if(!ObjectFunctions.isNullOrEmpty(getCustomerEnrollmentRequest()))
				{
					if(getCustomerEnrollmentRequest().getId() > 0)
					{
						customerEnrollmentRequest = (CustomerEnrollmentRequest) adminManager.get(CustomerEnrollmentRequest.class, "id="+getCustomerEnrollmentRequest().getId());
					}
				}
				if(!StringFunctions.isNullOrEmpty(getTempString2()))
				{
					if(!ObjectFunctions.isNullOrEmpty(customerEnrollmentRequest))
					{
						inviteCustomerEnrollment = customerEnrollmentRequest.getInviteCustomerEnrollment();
						if("Reject".equalsIgnoreCase(getTempString2()))
						{
							customerEnrollmentRequest.setStatus(Constants.REJECTED_STATUS);
							adminManager.saveOrUpdateObject(customerEnrollmentRequest);
							inviteCustomerEnrollment.setStatus(Constants.REJECTED_STATUS);
							adminManager.saveOrUpdateObject(inviteCustomerEnrollment);
							super.addActionError("Successfully Rejected the customer.");
							String[] emailAddresses = new String[1];
							emailAddresses[0]=customerEnrollmentRequest.getCustEmail(); 
							MailUtil mailUtil = new MailUtil(emailAddresses, "Your requested eazyschool account was rejected.", null, null,null,"messages@eazyschool.com");
							mailUtil.sendMailForNewCustomerRegistrationReject(customerEnrollmentRequest);
							mailUtil=null;
							emailAddresses=null;
							setObjectList(adminManager.getAll(CustomerEnrollmentRequest.class, "status='"+Constants.PENDING_STATUS+"'"));
							return SUCCESS;
						}
					}
					
				}
				if(!ObjectFunctions.isNullOrEmpty(getCustomer())){
					
					User user=userManager.getUserByUserName(getCustomer().getCustEmail());
					Person person = new Person();
					State state;
					Country country;
					Role role = new Role();
						Address primaryAddress=new Address();
						primaryAddress.setAddressLine2(getAddress().getStreetName());
						primaryAddress.setStreetName(getAddress().getStreetName());
		     			primaryAddress.setCity(getAddress().getCity());
		     			primaryAddress.setAddressLine1(getAddress().getAddressLine1());
		     			//primaryAddress.setAddressLine1(getCustomer().getAddress().getAddressLine1());
		     			//primaryAddress.setState(getAddress().getState());     
		     			primaryAddress.setPostalCode(getAddress().getPostalCode()); 
		     			primaryAddress.setEmail(getCustomer().getCustEmail());
		     			String encryptPassword=PasswordUtils.passwordEncoder(getCustomer().getPassword(),null); 
		     			role=userManager.getRoleByName(Constants.SCHOOL_ADMIN);
		     			person.setFirstName(getCustomer().getFirstName());
			 			person.setLastName(getCustomer().getLastName());
			 			person.setPhoneNumber(getCustomer().getContactNumber());
			 			person.setMobileNumber(getCustomer().getMobileNumber());
			 			if(!ObjectFunctions.isNullOrEmpty(getCountry())){
				 			if(!ObjectFunctions.isNullOrEmpty(getCountry().getId()) && getCountry().getId() > 0){
				 				country=(Country) adminManager.get(Country.class, "id="+getCountry().getId());
				 				if(!ObjectFunctions.isNullOrEmpty(country)){
				 					primaryAddress.setCountry(country.getCountryCode());
				 					primaryAddress.setCountryId(country.getId());
				 				}
				 				country=null;
				 			}
			 			}
				 			else if(StringFunctions.isNotNullOrEmpty(getParamValue("countryId")))
				 			{
				 				String countryId=getParamValue("countryId");
				 				country = (Country) adminManager.get(Country.class,"id="+Long.valueOf(countryId));
				 				if(!ObjectFunctions.isNullOrEmpty(country))
				 				primaryAddress.setCountry(country.getCountryCode());
				 				primaryAddress.setCountryId(Long.valueOf(countryId));
				 				if(!ObjectFunctions.isNullOrEmpty(getState()))
					 			primaryAddress.setStateId(getState().getId());
				 				country=null;
				 			}
			 			
			 			if(!ObjectFunctions.isNullOrEmpty(getState())){
				 			if(!ObjectFunctions.isNullOrEmpty(getState().getId()) && getState().getId() > 0) {
				 				state = (State) adminManager.get(State.class,"id="+getState().getId());
				 				if(!ObjectFunctions.isNullOrEmpty(state)){
				 				 primaryAddress.setState(state.getStateCode()); 
				 				 primaryAddress.setStateId(state.getId());
				 				}
				 				state=null;
				 			}
			 			}
			 			else if(StringFunctions.isNotNullOrEmpty(getParamValue("stateId")))
			 			{
			 				String stateId=getParamValue("stateId");
			 				state = (State) adminManager.get(State.class,"id="+Long.valueOf(stateId));
			 				if(!ObjectFunctions.isNullOrEmpty(state))
			 				primaryAddress.setState(state.getStateCode());
			 				primaryAddress.setStateId(Long.valueOf(stateId));
				 			state=null;
			 			}
			 			Organization organization = (Organization) adminManager.get(Organization.class, "organizationName='"+getCustomer().getOrganization().replaceAll("'","''").trim()+"'");
			 			if(ObjectFunctions.isNullOrEmpty(organization))
			 			{
			 				organization = new Organization();
			 				organization.setAddress(primaryAddress);
							organization.setOrganizationName(getCustomer().getOrganization().trim());
							organization.setStatus(Constants.YES_STRING);
							organization.setContactNumber(getCustomer().getContactNumber());
							organization.setMobileNumber(getCustomer().getMobileNumber());
							organization = (Organization) adminManager.save(organization);
			 			}
					    Address customerAddress=new Address();
					    customerAddress.setStreetName(getCustomer().getAddress().getStreetName());
					    customerAddress.setAddressLine1(getCustomer().getAddress().getStreetName());
					    customerAddress.setCity(getCustomer().getAddress().getCity());
					    customerAddress.setState(getCustomer().getAddress().getState());     			
					    customerAddress.setPostalCode(getCustomer().getAddress().getPostalCode());
					    getCustomer().setAddress(primaryAddress);
					    getCustomer().setAllowedTotalSms(getCustomer().getAllowedTotalSms());
					    getRegUser().setUsername(getCustomer().getCustEmail());
					    getRegUser().setSecondaryUsername(getCustomer().getCustEmail());
					    getRegUser().setPassword(encryptPassword); 
		 			    getRegUser().setPrimaryAddress(customerAddress); 
		 			    getRegUser().addRole(role);
		 			    getCustomer().setStatus(true);
		 			   String isHostel=getParamValue("isHostel");
		 			  if(StringFunctions.isNotNullOrEmpty(isHostel)){
	                		    if ("on".equalsIgnoreCase(isHostel)) {
	                			getCustomer().setHostelModuleStatus(true);
	                		    } else {
	                			getCustomer().setHostelModuleStatus(false);
	                		    }
		 			  }
		 			 String isTransport=getParamValue("isTransport");
		 			  if(StringFunctions.isNotNullOrEmpty(isTransport)){
	               		    if ("on".equalsIgnoreCase(isTransport)) {
	               		    	getCustomer().setTransportModuleStatus(true);
	               		    	getCustomer().setCheckTransportService(true);
	               		    } else {
	               		    	getCustomer().setTransportModuleStatus(false);
	               		    	getCustomer().setCheckTransportService(false);
	               		    }
		 			  }
		 			    getCustomer().setCheckEmailService(true);
		 			    getCustomer().setCheckMobileService(true);
		 			    getCustomer().setCheckMobilePaymentService(false);
		 			    getCustomer().setPaymentType('P');
		 			    getCustomer().setOrgnizationTypeId(getOrganizationTypes().getId());
		 			    getCustomer().setStatus(true);
		 			    if(StringFunctions.isNotNullOrEmpty(getAccountType())){
		 			    	getCustomer().setAccountType(getAccountType());
		 			    }else{
		 			    	getCustomer().setAccountType(getAccountType());
		 			    }
		 			    getCustomer().setMobileType("P");
		 			    getCustomer().setSmsCost(0.14);
		 			    getCustomer().setSmsServiceProviderId(getCustomer().getSmsServiceProviderId());
		 			    //getCustomer().setSender("eazySchool");
		 			    getCustomer().setOrgId(organization.getId());
		 			    getCustomer().setFeeReceiptModel(getCustomer().getFeeReceiptModel());
		 			    getCustomer().setStandardType(getCustomer().getStandardType());
		 			    getCustomer().setShowPreviousYearPendingFee("N");
		 			    getCustomer().setShowTotalOrPaidAmount("N");
		 			    getCustomer().setAllowPastDatesForPayments("N");
		 			    getCustomer().setAllowDiscountOptOnOtherRoles("N");
		 			    getCustomer().setAllowedPastDatesForPayments(0L);
		 			    if(getUser().isSchoolSEO() || getUser().isSchoolDEO() || getUser().isSchoolCEO() || getUser().isSchoolBEO())
		 			    {
		 			    	GovStaffInfo govStaffInfo = (GovStaffInfo)adminManager.get(GovStaffInfo.class, " accountId=" + getUser().getId()+" and status='"+Constants.YES_STRING+"'");
		 			    }
		 			  if (!ObjectFunctions.isNullOrEmpty(getChkBoxSelectedIds())) 
						{
							for (Object subjectIdObj : getChkBoxSelectedIds()) {
								try {
									String syllabusTypeId = (String) subjectIdObj;
									SyllabusType syllabusType = (SyllabusType) adminManager.get(SyllabusType.class, Long.parseLong(syllabusTypeId));
									getCustomer().addSyllabusTypeInfo(syllabusType);
									syllabusType = null;
								} catch (Exception ex) {
									log.debug(ex.getMessage());
									ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
								}
							}
						}
		 			    Customer customer=userManager.saveCustomer(getCustomer());
		 			    getRegUser().setPerson(person);
		 			    getRegUser().setCustId(customer.getId());
		 			    user=userManager.saveUser(getRegUser());
		 			    log.debug("New User  Id"  +user.getId());
		 			    AcademicYear year=new AcademicYear();
		 			    Date date = new Date();
		 			    SimpleDateFormat simpleDateformat=new SimpleDateFormat("yyyy");
		 			    if(!ObjectFunctions.isNullOrEmpty(simpleDateformat.format(date))){
		 			    	year.setPastYear(Long.valueOf(simpleDateformat.format(date)));
		 			    	Long futureYear=Long.valueOf(simpleDateformat.format(date).substring(2))+1;
			 			    year.setAcademicYear(simpleDateformat.format(date)+"-"+futureYear);	 			    	
		 			    }
		 			    year.setIsDefaultExamTypeStatus("N");
		 			    year.setCustId(user.getCustId());
		 			    year.setStatus("Y");
		 			    year.setHolidayStatus("SH");
		 			    year.setAllotedsms(getAcademicYear().getAllotedsms());
		 			    year.setFeeModuleUsegeBy("W");
		 			   year=adminManager.saveAcademicYear(year);
		 			   log.debug("ACadmic YEar Id "  +year.getId());
		 			   	
		 			    Staff staff =new Staff();
		 			    staff.setCreatedById(getRegUser().getId());
		 			    staff.setAccount(user);
		 			    staff.setCustId(customer.getId());
		 			    staff.setStatus(Constants.YES_STRING);
		 			    staff.setAcademicYear(year);
		 			    staff = (Staff) adminManager.save(staff);
		 			   List<Role> roles=adminManager.getAll(Role.class);
				  	      if(!ObjectFunctions.isNullOrEmpty(roles)){
				  	    	  log.debug(roles);
				  	    	adminManager.insertIntoLoginAcceessbilityRoles(roles,user.getCustId());
				  	    	roles=null;
					  	}
		 			    for(int i=0;i<6;i++){
		 			    	KBankType type=new KBankType();
		 			    	type.setCustId(user.getCustId());
		 			    	type.setStatus("Y");
		 			    	if(i==0)
		 			    		type.setTypeName("My Contribution");
		 			    	if(i==1)
		 			    		type.setTypeName("My Projects");
		 			    	if(i==2)
		 			    		type.setTypeName("My Class Material");
		 			    	if(i==3)
		 			    		type.setTypeName("Competitive Training");
		 			    	if(i==4)
		 			    		type.setTypeName("Case Studies");
		 			    	if(i==5)
		 			    		type.setTypeName("White Papers");
		 			    	userManager.save(type);
		 			    	type=null;
		 			    }
		 			   for(int i=0;i<5;i++){
		 				  FeedbackGrades feedbackGrades = new FeedbackGrades();
		 				 feedbackGrades.setCustId(user.getCustId());
		 				 feedbackGrades.setStatus("Y");
		 			    	if(i==0)
		 			    	{
		 			    		feedbackGrades.setDescription("Bad");
		 			    	feedbackGrades.setTitle("1");
		 			    	}
		 			    	if(i==1){
		 			    		feedbackGrades.setDescription("Average");
		 			    	feedbackGrades.setTitle("2");
		 			    	}
		 			    	if(i==2){
		 			    		feedbackGrades.setDescription("Above Average");
		 			    	feedbackGrades.setTitle("3");
		 			    	}
		 			    	if(i==3){
		 			    		feedbackGrades.setDescription("Good");
		 			    	feedbackGrades.setTitle("4");
		 			    	}
		 			    	if(i==4){
		 			    		feedbackGrades.setDescription("Excellent");
		 			    	feedbackGrades.setTitle("5");
		 			    	}
		 			    	userManager.save(feedbackGrades);
		 			    	feedbackGrades=null;
		 			    }
			 		   CommonType commonType2 = null;
		                  String[] menuItems = {"BREAKFAST","LUNCH","DINNER","MORNING SNACKS","EVENING SNACKS"};
		                         for (String token : menuItems) {
		                           if(!StringFunctions.isNullOrEmpty(token)){        
		                        	   commonType2 = new CommonType();
		                        	   commonType2.setCustId(user.getCustId());
		                        	   commonType2.setCreatedById(user.getId());
		  							   commonType2.setCreatedDate(new Date());
		  							   commonType2.setLastUpdatedDate(new Date());
		                        	   commonType2.setSkillTypeName(token);
		                        	   commonType2.setType("MENUITEMS");
		                        	   //commonType2.setVersion(0);
		                               adminManager.save(commonType2);
		                               commonType2=null;
		                           }
		                    
		              }
		 			  String[] physically = {"BLIND", "ORTHOPATICALLY HANDICAP", "DEAF"};
		 				  CommonType physicalType =null;
		 				  for (String token : physically) {
		 					  if(!StringFunctions.isNullOrEmpty(token)){
		 						 physicalType = new CommonType();
		 						physicalType.setCustId(user.getCustId());
		 						physicalType.setCreatedById(user.getId());
		 						physicalType.setLastUpdatedDate(new Date());
		 						physicalType.setCreatedDate(new Date());
		 						physicalType.setSkillTypeName(token);
		 						physicalType.setType("HANDICAP");
		 						//physicalType.setVersion(0);
		 						adminManager.saveOrUpdateObject(physicalType);
		 					  }
		 					 physicalType=null;
		 				  }	 			    
		 			  String[] religion = {"CHRISTIAN ", "MUSLIM", "JAIN","HINDU","Other"};
		 				  CommonType commonReligion = null;
		 				  for (String token : religion) {
		 					  if(!StringFunctions.isNullOrEmpty(token)){
		 						 commonReligion = new CommonType();
		 						commonReligion.setCustId(user.getCustId());
		 						commonReligion.setCreatedById(user.getId());
		 						commonReligion.setLastUpdatedDate(new Date());
		 						commonReligion.setCreatedDate(new Date());
		 						commonReligion.setSkillTypeName(token);
		 						commonReligion.setType("RELIGION");
		 						adminManager.saveOrUpdateObject(commonReligion);
		 					  }
		 					 commonReligion=null;
		 				  }	 			    
		 			    SubType subType=new SubType();
		 			    subType.setCustId(user.getCustId());
		 			    subType.setSchedule(true);
		 			    subType.setSubTypeName("Theory");
		 			    subType.setAcademicYear(year);
		 			    subType.setPredefinedSubType(true);
		 			    subType.setSortingOrder(1);
		 			    adminManager.save(subType);
		 			   SchoolCategory category=new SchoolCategory();
					   category.setCustId(user.getCustId());
					   category.setCategoryName("General");
					   adminManager.save(category);
					   List<SchoolFeeSetting> feeSettingsList=adminManager.getAll(SchoolFeeSetting.class);
					   if(!ObjectFunctions.isNullOrEmpty(feeSettingsList)){
						   FeeType feeType=null;
						   for (SchoolFeeSetting schoolFeeSetting : feeSettingsList) {
							    feeType=new FeeType();
								if (schoolFeeSetting.getSettingName().equalsIgnoreCase("Terms Fee")) {
									feeType.setFeeType("Tuition Fee");
								} else if (schoolFeeSetting.getSettingName().equalsIgnoreCase("Transport Fee")) {
									feeType.setFeeType("Transport Fee");
								} else if (schoolFeeSetting.getSettingName().equalsIgnoreCase("Hostel Fee")) {
									feeType.setFeeType("Hostel Fee");
								}else {
									feeType.setFeeType("Admission Fee");
								}
							   feeType.setCustId(user.getCustId());
							   feeType.setAcademicYear(year);
							   feeType.setFeeSettingId(schoolFeeSetting.getId());
							   adminManager.save(feeType);
						   }
						   feeType=null;
					   }
					   CastSettings castSettings=new CastSettings();
					   castSettings.setCustId(user.getCustId());
					   castSettings.setCastName("Other");
					   adminManager.save(castSettings);
					   if (!ObjectFunctions.isNullOrEmpty(getAnyId().toString())){
						   StudySubject subject = null;
						   String defaultSubId[]=getAnyId().split(",");
						   for(String subjectId : defaultSubId) {
							   if(Long.valueOf(subjectId)>0){
								   GeneralSubjects genSubject=(GeneralSubjects) adminManager.get(GeneralSubjects.class,"id="+subjectId);
								   if(!ObjectFunctions.isNullOrEmpty(genSubject)){
									   subject = new StudySubject();
									   subject.setName(genSubject.getName().trim());
									   subject.setDescription(genSubject.getDescription());
									   subject.setSubjectNumber(genSubject.getSubjectNumber());
									   subject.setLanguage(genSubject.isLanguage());
									   subject.setSubjectType(genSubject.getSubjectType());
									   subject.setCreatedById(user.getId());
									   subject.setLastUpdatedById(user.getId());
									   subject.setLastUpdatedDate(new Date());
									   subject.setLastAccessDate(new Date());
									   subject.setAcademicYear(year);
									   subject.setCustId(user.getCustId());
								   }
								   adminManager.save(subject);
								   genSubject=null;
							   }subjectId=null;
							}
					   }
					   if(!StringFunctions.isNullOrEmpty(getTempString2()))
						{
							if(!ObjectFunctions.isNullOrEmpty(customerEnrollmentRequest))
							{
								inviteCustomerEnrollment = customerEnrollmentRequest.getInviteCustomerEnrollment();
								if("Accept".equalsIgnoreCase(getTempString2()))
								{
									customerEnrollmentRequest.setStatus(Constants.ACTIVE_STATUS);
									adminManager.saveOrUpdateObject(customerEnrollmentRequest);
									inviteCustomerEnrollment.setStatus(Constants.ACTIVE_STATUS);
									adminManager.saveOrUpdateObject(inviteCustomerEnrollment);
									super.addActionMessage("Successfully Accepted the customer.");
									setObjectList(adminManager.getAll(CustomerEnrollmentRequest.class, "status='"+Constants.PENDING_STATUS+"'"));
								}
							}
						}
					if(customer.isCheckEmailService())
					{
						String[] emailAddresses = new String[1];
						emailAddresses[0]=customer.getCustEmail(); 
						File folder = new File(getSession().getServletContext().getRealPath("userfiles/AllTemplates/")); 
						 File[] listOfFiles = folder.listFiles();
						 int i = 0;
						 String[] fileUploadStr = null;
						 if(!ObjectFunctions.isNullOrEmpty(listOfFiles)){
							i = 0;
							fileUploadStr = new String[listOfFiles.length];
							 for (File file : listOfFiles) { 
								 if (file.isFile()) {
									fileUploadStr[i] = file.toString();
									log.debug(file.getName());
									file = null;
								 }
								 i++;
							 }
						 }
						MailUtil mailUtil = new MailUtil(emailAddresses, "Welcome to Eazy School Management", null, user,fileUploadStr,"messages@eazyschool.com");
						mailUtil.sendMailForCustomerRegistration(customer);
						mailUtil=null;
						emailAddresses=null;
					}
					if(customer.isCheckMobileService()){
						if(!ObjectFunctions.isNullOrEmpty(customer.getMobileNumber())){
							getMobileNumbersSet().add(customer.getMobileNumber());
							if(!ObjectFunctions.isNullOrEmpty(getMobileNumbersSet())){
								communicationManager.SendSmsToCustomer(getMobileNumbersSet(),customer,user,year);
							}
	
						}
					}
					DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
					List<Object> emailsList = new ArrayList<Object>();
					List<Object> supportEmails = adminManager.getAll("select email from supportTeam");
					if (!ObjectFunctions.isNullOrEmpty(customer) && !ObjectFunctions.isNullOrEmpty(supportEmails)) {
						emailsList.addAll(supportEmails);
						emailsList.add("support@eazyschool.com");
						StringBuffer br = new StringBuffer();
						br = prepareCusotmerActivationEmailForInternalTeam(br,dateFormat.format(date), customer);
						String[] emailAddresses = new String[emailsList.size()];
						if (!ObjectFunctions.isNullOrEmpty(emailsList)) {
							List<Object> emailsIdsList = new ArrayList(new HashSet(emailsList));
							int i=0;
							for (Object staffEmailIds : emailsIdsList) {
								emailAddresses[i] = staffEmailIds.toString();
								i++;
							}
							MailUtil mailUtil = new MailUtil(emailAddresses,"Confirmation "+customer.getOrganization()+" EazySchool Account Activation",br.toString(), getUser(), null,"messages@eazyschool.com");
							mailUtil.sendEmailToSupportingTeam("messages@eazyschool.com");
							emailAddresses=null;
							mailUtil = null;
							br = null;
						}
					}
		 			 year=null;
		 			primaryAddress=null; 	
		 			organization = null;
					setUser(null);
	 			    person=null;
	 				setRegUser(null);
	 				role=null;
	 				customer = null;
	 				super.addActionMessage("Registration completed successfully.");
	 				Map<Long,String>  countriesNamesMap = getCountriesNamesMap();
	 				 List<Customer> allActiveCustomersList = adminManager.getAll(Customer.class,"accountType='C' and status='Y' and organizationSubTypeId = 0 order by createdDate desc");
	 				 if(!ObjectFunctions.isNullOrEmpty(allActiveCustomersList))
	 				 {
	 					 for(Customer customerObj : allActiveCustomersList)
	 					 {
	 						if(!ObjectFunctions.isNullOrEmpty(customerObj.getAddress()))
	 							customerObj.setCountryName(countriesNamesMap.get(customerObj.getAddress().getCountryId()));
	 						else
	 							customerObj.setCountryName("India");
	 						getCustomersList().add(customerObj);
	 					 }
	 					 List<Customer> interNationCustomersList = adminManager.getAllHqlQuery("FROM Customer c WHERE c.address.countryId !='99' and c.accountType='C' and c.status='Y' and c.organizationSubTypeId = 0 order by c.createdDate desc"); // Getting InterNational Customers List
	 					 if(!ObjectFunctions.isNullOrEmpty(interNationCustomersList))
	 					 {
	 						setTempId1(interNationCustomersList.size()); // InterNational Customes Count
	 					 }
	 					
	 					 setTempId2(allActiveCustomersList.size() - getTempId1());
	 				 }
	 				 countriesNamesMap = null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	}
    @Actions({
		@Action(value = "ajaxGetCustomerName", results = { @Result(type="json", name = "success", params = {"includeProperties","autoCheck"}) })})
	public String existCustomerName() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'existCustomerName' method");
		}
		try
		{
			String keyWord=getParamValue("keyWord");
			if(StringFunctions.isNotNullOrEmpty(keyWord))
			{
				List<Customer> customersList=adminManager.findExistCustomer(keyWord);
				if(ObjectFunctions.isNullOrEmpty(customersList))
				{
					setAutoCheck("0");
				}
				else if(customersList.size() > 0)
				{
					setAutoCheck("1");
				}
				else
				{
					setAutoCheck("0");
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	return SUCCESS;
	}
    @Actions({
		@Action(value = "viewEmbedCode", results = { @Result(location = "embedCode.jsp", name = "success") })
	})
	public String viewEmbedCode() throws URTUniversalException {
		return SUCCESS;
	}
    
    @Actions({
		@Action(value = "ajaxDoAddNewCustomerRegister", results = { @Result(location = "../masterAdmin/ajaxGovtCustomerDetails.jsp", name = "success") }),
		@Action(value = "ajaxGetPackageDetails", results = { @Result(location = "registration.jsp", name = "success") })	
	})
    public String ajaxGetPackageDetails() throws URTUniversalException {
    	setSmsServiceProvidersList(adminManager.getAll(SMSServiceProviders.class,"isCustomerSpecific='N'"));
    	setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
    	setOrganizationTypesList(adminManager.getAll(OrganizationTypes.class));
  	setObjectList(adminManager.getAll(Organization.class));
    	setCountryList((List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST));
    	if(getUser().isSchoolSEO() || getUser().isSchoolDEO() || getUser().isSchoolCEO() || getUser().isSchoolBEO())
	    {
	    	GovStaffInfo govStaffInfo = (GovStaffInfo)adminManager.get(GovStaffInfo.class, " accountId=" + getUser().getId()+" and status='"+Constants.YES_STRING+"'");
	    }
    	setSyllabusTypeList(adminManager.getAll(SyllabusType.class,"status='Y'"));
    	setCustomer(null);
		return SUCCESS;
	}
    @Actions({ @Action(value = "ajaxOnlineCastDetailsByState", results = { @Result(location = "ajaxCastListByState.jsp", name = "success") })})
	public String onlineCastDetailsByState() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'onlineCastDetailsByState' method");
			}
			try {
				String custId=getParamValue("custId");
				List castList= adminManager.getAllByCustId("CastSettings", Long.valueOf(custId),0);
				CastSettings castSetting = null;
				 if(!ObjectFunctions.isNullOrEmpty(castList)){
					 setCastSettingList(castList);
					 castSetting = new CastSettings();
					 castSetting.setCastName("OTHER");
					 getCastSettingList().add(castSetting);
				 }
				 else{
					 castSetting = new CastSettings();
					 castSetting.setCastName("OTHER");
					 getCastSettingList().add(castSetting);
				 }
				 castSetting=null;
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
		}
	    	return SUCCESS;
	}

    /*Changed by seshu on April 10th.*/
    /*RaviTeja 01-12-2016. Here Check the OTP to forgot Password Functionality */
	@Actions( { @Action(value = "ajaxForgotPassword", results = {
			@Result(location = "forgotPassword.jsp", name = "sentForgotPasswordOTP"),
			@Result(location = "forgotPassword.jsp", name = "userNotFound"),
			@Result(location = "forgotPassword.jsp", name = "actionError"),
			@Result(location = "ajaxChangePassword.jsp", name = "changePassword"),
			@Result(location = "forgotPassword.jsp", name = "failure") }) })
	public String retrievePassword() throws Exception {
		log.debug(" Entered into send password method");
		if (log.isDebugEnabled()) {
			log.debug("Entering 'retrievePassword' method");
		}
		// Check for the mandatory elements before persisting the data to db
		Map<String, String> msgs =new HashMap<String, String>();
		try {
			if (StringFunctions.isNullOrEmpty(getParamValue("username"))) {
				return "failure";
			}
			else {		
					User luser = userManager.getUserByUserName(getParamValue("username"));
					if (ObjectFunctions.isNullOrEmpty(luser)) {
						addActionError(getText("Invalid username,please try with the registered username."));
						return "userNotFound";
					}
					/*RaviTeja 01-12-2016. Here Check the OTP to forgot Password Functionality */
					if(luser.isEnabled()){
						if(!ObjectFunctions.isNullOrEmpty(getAnyId())){
							if(getAnyId().equalsIgnoreCase("OTP")){
								String forgotOTP =getParamValue("otp");
								if(!ObjectFunctions.isNullOrEmpty(luser)){
									if(!ObjectFunctions.isNullOrEmpty(forgotOTP)){
										if(forgotOTP.equalsIgnoreCase(luser.getOTP())){
											super.addActionMessage("Authentication is success. Please update your password");
											setTempId(luser.getId());
											return "changePassword";
										}else{
											setTempString("O"); // Here send otp after show/hide the Otp, Usrename field
											setTempString2(getTempString2());
											super.addActionError(" Incorrect OTP. Please try again.");
											return "actionError";
										}
									}
								}
							}
						}else{
							ajaxSendOTPToForgotPassword();
						}
					}else{
						super.addActionError("User is disabled . Please contact your administrator.");
					}
			}
		} catch (Exception e) {
			super.addActionError("User name is not found in the system. Please try with a valid user name");
			return "userNotFound";
		}
		return "sentForgotPasswordOTP";
	}
	/*RaviTeja 01-12-2016. Here Check the OTP to forgot Password Functionality */
	@Actions( { @Action(value = "ajaxChangeForgotPassword", results = {
			@Result(location = "ajaxChangePassword.jsp", name = "updatePassword"),
			@Result(location = "ajaxChangePassword.jsp", name = "userNotFound"),
			@Result(location = "ajaxChangePassword.jsp", name = "failure") }) })
	public String ajaxChangeForgotPassword() throws Exception {
		log.debug(" Entered into send password method");
		if (log.isDebugEnabled()) {
			log.debug("Entering 'retrievePassword' method");
		}
		// Check for the mandatory elements before persisting the data to db
		Map<String, String> msgs =new HashMap<String, String>();
		try {
			if (StringFunctions.isNullOrEmpty(getParamValue("customerId"))) {
				return "failure";
			}
			else {		
				String userId =getParamValue("customerId");
				String password =getParamValue("password");
				User luser=((User)subscriptionManager.get(User.class,Long.valueOf(userId)));
					if (ObjectFunctions.isNullOrEmpty(luser)) {
						addActionError(getText("Invalid username, please try with the registered username."));
						return "userNotFound";
					}
					/*below method move the code signupAction to UniversalManagerImpl.java */
					msgs = userManager.sendForgotPasswordByUserName(luser,password);
					if (!StringFunctions.isNullOrEmpty(msgs.get("1")))
						super.addActionMessage(msgs.get("1"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("2")))
						super.addActionMessage(msgs.get("2"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("3")))
						super.addActionError(msgs.get("3"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("4")))
						super.addActionMessage(msgs.get("4"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("5")))
						super.addActionError(msgs.get("5")); 
					 
					msgs=null;
					setTempString3("R"); //Here tempstring3 using update the password redirect to login.jsp page
			}
		} catch (Exception e) {
			super.addActionError("User name is not found in the system.Please try with a valid user name");
			return "userNotFound";
		}
		return "updatePassword";
	}
	
	@Actions({ @Action(value = "ajaxGetSubCastDetailsByCast", results = { @Result(location = "ajaxGetOnlineSubCastListByCastId.jsp", name = "success") })}) 
	public String ajaxGetSubCastDetailsByCast() {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxGetSubCastDetailsByCast' method");
	}
	try {
		long castId = getOnlineApplicationDetails().getCastId().getId();
		CastSettings castSettings = (CastSettings)staffManager.get(CastSettings.class, castId);
		 if(!ObjectFunctions.isNullOrEmpty(castSettings)){
			 setCastSettings(castSettings);
			 setAllUsersSet(getCastSettings().getSubCastSettings());
		 }
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return SUCCESS;
	}
	@Actions( { @Action(value = "downloadApplicationForm", results = { @Result(location = "jasper/admission/downloadApplicationForm.jasper", type = "jasper", name = "success", params = {
			"dataSource", "objectList", "format", "PDF" })  }) })
	public String downloadApplicationForm(){
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'downloadApplicationForm' method");
	        }
		try{
			String pdf = "SchoolOnlineApplication" + StringFunctions.getReplaceAll(DateFormatter.getTodayDateStr(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN) , " ", "-") + ".pdf";
				Customer customer =(Customer)adminManager.get(Customer.class,getCustId());
				setTempString("userfiles/"+customer.getCustomerShortName().trim().toLowerCase());
				if(StringFunctions.isNotNullOrEmpty(getTempString())){
			    	File destFile = new File(getSession().getServletContext().getRealPath(getTempString()));
			    	if(destFile.mkdirs()){
			    		setAnyTitle(getSession().getServletContext().getRealPath(getTempString().concat("/logo.png")));
						log.debug(getSession().getServletContext().getRealPath(getTempString())+" directory created.");
			    	}
					else{
						setAnyTitle(getSession().getServletContext().getRealPath(getTempString().concat("/logo.png")));
						log.debug(getSession().getServletContext().getRealPath(getTempString())+" directories Not Created");
					}
				}
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					getObjectList().add(customer);
				}
				getResponse().setHeader("Content-Disposition","attachment; filename="+pdf);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetOrganizationSubTypes", results = { @Result(location = "../masterAdmin/ajaxGetOrganizationalSubTypes.jsp", name = "success") }) })
		public String ajaxGetOrganizationSubTypes() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetOrganizationSubTypes' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getCustomerEnrollmentRequest()))
				{
					if(getCustomerEnrollmentRequest().getId() > 0)
					{
						setCustomerEnrollmentRequest((CustomerEnrollmentRequest) adminManager.get(CustomerEnrollmentRequest.class, "id="+getCustomerEnrollmentRequest().getId()));
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
	
	    @Actions({
			@Action(value = "ajaxMasterCustomerRegistation", results = { @Result(location = "../masterAdmin/ajaxPackageDetails.jsp", name = "success"),
					@Result(location = "../masterAdmin/ajaxGovtCustomerRegistration.jsp", name = "govtsuccess")}),
			@Action(value = "customerRegister", results = { @Result(location = "packageDetails.jsp", name = "success") })	
		})
	    public String addGovtCustomerRegister() throws URTUniversalException {
            try{
            	setSyllabusTypeList(adminManager.getAll(SyllabusType.class,"status='Y'"));
            	
            	if("GC".equalsIgnoreCase(getParamValue("custType"))){
            		setAlertSendType(getParamValue("custType"));
            		getStaffRoles().put("", Constants.SELECTROLE);
            		if(getUser().isMasterAdmin())
            			getStaffRoles().put(Constants.SCHOOL_SEO, "State Education Board");
            		else
            		{
	            		getStaffRoles().put(Constants.SCHOOL_DEO, "District Education Officers");
	            		getStaffRoles().put(Constants.SCHOOL_CEO, "Cluster Education Officer");
	            		getStaffRoles().put(Constants.SCHOOL_BEO, "Block Education Officer");
            		}
            		setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
            		return "govtsuccess";
            	}else{
            		setPackageDetailsList(adminManager.getAll(PackageDetails.class));
            	}
            }
            catch(Exception ex)
            {
                    ex.printStackTrace();
            }
            return SUCCESS;
	    }

		
		@Actions( { @Action(value = "ajaxGovtCustomerDetails", results = { @Result(location = "../govt/ajaxGovtCustomers.jsp", name = "success"), @Result(location = "registration.jsp", name = "signup") }) })
		public String ajaxGovtCustomerDetails() {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGovtCustomerDetails' method");
			}
			try {
				if(!ObjectFunctions.isNullOrEmpty(getCustomer())){
					User user=userManager.getUserByUserName(getCustomer().getCustEmail());
					String encryptPassword=PasswordUtils.passwordEncoder("eazyschool123",null); 
					Person person = new Person();
					Role role = new Role();
					Address primaryAddress=new Address();
					Staff staff = new Staff();
					GovStaffInfo govStaffInfo = new GovStaffInfo();
	     			primaryAddress.setEmail(getCustomer().getCustEmail());
	     			String paramVal =getParamValue("roleName");
	     			role=userManager.getRoleByName(paramVal);
	     			
	     		if(!ObjectFunctions.isNullOrEmpty(role)){
	     			person.setFirstName(getCustomer().getFirstName());
		 			person.setLastName(getCustomer().getLastName());
		 			
		 			
		 			if (paramVal.equalsIgnoreCase(Constants.SCHOOL_DEO)){
		 				primaryAddress.setDistrictId(Long.valueOf(getSelectedId()));
		 			}
		 			else if (paramVal.equalsIgnoreCase(Constants.SCHOOL_CEO)){
		 				  primaryAddress.setDistrictId(Long.valueOf(getSelectedId()));
		 				  primaryAddress.setMandalId(Long.valueOf(getAnyId()));
		 			}
		 			else if (paramVal.equalsIgnoreCase(Constants.SCHOOL_BEO)){
		 				  primaryAddress.setDistrictId(Long.valueOf(getSelectedId()));
		 				  primaryAddress.setMandalId(Long.valueOf(getAnyId()));
		 			}
		 			
		 		   getRegUser().setPrimaryAddress(primaryAddress); 
		 		   getRegUser().setUsername(getCustomer().getCustEmail());
		 		   getRegUser().setPassword(encryptPassword);
		 		   getRegUser().addRole(role);
		 		   
    			   if(!ObjectFunctions.isNullOrEmpty(user))
   					{	
   					super.addActionError("Email Address already Exists.");
   					return "signup";
   					}
    			   else{
        			   getRegUser().setPerson(person);
        			   getRegUser().setCustId(0);
        			   staff.setAccount(getRegUser());
        			   staff.setCustId(0);
        			   staff = (Staff)adminManager.save(staff);
        			   govStaffInfo.setGovRole(role);
        			   govStaffInfo.setGovStaff(staff);
        			   govStaffInfo.setGovUsers(staff.getAccount());
        			   govStaffInfo.setPeriodStartDate(new Date());
        			   govStaffInfo.setStatus(Constants.YES_STRING);
        			   govStaffInfo.setCreatedById(getUser().getId());
        			   govStaffInfo.setCreatedDate(new Date());
        			   govStaffInfo.setLastAccessDate(new Date());
        			   govStaffInfo.setLastUpdatedById(getUser().getId());
        			   govStaffInfo.setLastUpdatedDate(new Date());
        			   
        			   adminManager.save(govStaffInfo);
    				   super.addActionMessage("SEO added successfully.");
    				   ajaxGovtCustomersList();
    			   }
	     		}
			}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxGovtCustomersList", results = { @Result(location = "../govt/ajaxGovtCustomers.jsp", name = "success")})
		})
		public String ajaxGovtCustomersList() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGovtCustomersList' method");
			}
			try{
				 setCustomersList(adminManager.getAll(Customer.class,"organizationSubTypeId > 0"));
				 
				 
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
		
		@Actions({
			@Action(value = "ajaxAddGovtStaffRegister", results = {@Result(location = "../masterAdmin/ajaxGovtStaffDetails.jsp", name = "success")})
		})
	    public String ajaxAddGovtStaffRegister() throws URTUniversalException {
            try{
        		getStaffRoles().put("", Constants.SELECTROLE);
        		if(getUser().isSchoolSEO())
        		{
        			getStaffRoles().put(Constants.SCHOOL_DEO, "District Education Officers");
            		getStaffRoles().put(Constants.SCHOOL_CEO, "Cluster Education Officer");
            		getStaffRoles().put(Constants.SCHOOL_BEO, "Block Education Officer");
        		}
        		else if(getUser().isSchoolDEO())
        		{
        			getStaffRoles().put(Constants.SCHOOL_CEO, "Cluster Education Officer");
            		getStaffRoles().put(Constants.SCHOOL_BEO, "Block Education Officer");
        		}
        		else if(getUser().isSchoolCEO())
        		{
        			getStaffRoles().put(Constants.SCHOOL_BEO, "Block Education Officer");
        		}
        		setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
            }
            catch(Exception ex)
            {
                    ex.printStackTrace();
            }
            return SUCCESS;
	    }
		
		
		@Actions({ @Action(value = "sendPassWordToUsers", results = {
				@Result(location = "parentRegStep3.jsp", name = "userNotFound"),
				@Result(location = "parentRegStep3.jsp", name = "success")}) })
		public String sendPassWordToUsers() throws Exception {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'retrievePassword' method");
			}
			Map<String, String> msgs =new HashMap<String, String>();
			try {
				User luser = null;
				if(!StringFunctions.isNullOrEmpty(getTempString()) || !StringFunctions.isNullOrEmpty(getPlTitle())){
					if(!StringFunctions.isNullOrEmpty(getTempString())){
						ViewStudentPersonAccountDetails studentDetails= (ViewStudentPersonAccountDetails)userManager.get(ViewStudentPersonAccountDetails.class,"mobileNumber='"+getTempString()+"' ");
						if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
							if(!ObjectFunctions.isNullOrEmpty(studentDetails.getParentEmail())){
								luser = userManager.getUserByUserName(studentDetails.getParentEmail());
							}
						}
					}
					else if(!StringFunctions.isNullOrEmpty(getPlTitle())){
						luser = userManager.getUserByUserName(getPlTitle());
					}
					if (ObjectFunctions.isNullOrEmpty(luser)) {
						addActionMessage(getText("errors.password.noUser"));
						return "userNotFound";
					}
					msgs = userManager.sendForgotPasswordByUserName(luser);
					
					if (!StringFunctions.isNullOrEmpty(msgs.get("1")))
						super.addActionMessage(msgs.get("1"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("2")))
						super.addActionMessage(msgs.get("2"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("3")))
						super.addActionError(msgs.get("3"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("4")))
						super.addActionMessage(msgs.get("4"));
					else if (!StringFunctions.isNullOrEmpty(msgs.get("5")))
						super.addActionError(msgs.get("5")); 
					msgs=null;
				}
			} catch (Exception e) {
				super.addActionError("User name is not found in the system.Please try with a valid user name");
				return "userNotFound";
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxGetGeneralSubjects", results = { @Result(location = "../masterAdmin/ajaxGetGeneralSubjects.jsp", name = "success")})
		})
		public String ajaxGetGeneralSubjects() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetGeneralSubjects' method");
			}
			try{
				if(StringFunctions.isNotNullOrEmpty(getAnyTitle().toString().replaceAll("[\\[\\](){}]",""))){
					setTempList2(adminManager.getAll(GeneralSubjects.class,"syllabusTypeId in ("+getAnyTitle().toString().replaceAll("[\\[\\](){}]","")+")"));
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "ajaxDoLoginForm", results = { @Result(location = "../masterAdmin/loginFormForWeb.jsp", name = "success")})
		})
		public String ajaxDoLoginForm() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoLoginForm' method");
			}
			try{
				log.debug("Hello I am calling ....");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			return SUCCESS;
		}
		@Actions({
			@Action(value = "logoutsuccess", results = { @Result(location = "logout.jsp", name = "success") })})
		public String logoutsuccess(){ 
			super.addActionError("Dear user, Your account status is in Inactive. So you are not able to perform any operations.Contact Eazyschool support team at 080-46620999 to resume your services");
			return SUCCESS;	
			
		}
		@Actions({
			@Action(value = "logoutsuccessForParent", results = { @Result(location = "logout.jsp", name = "success") })})
		public String logoutsuccessForParent(){ 
			super.addActionError("Dear Parent, No active students found to login, Please contact school administrator");
			return SUCCESS;	
			
		}
		
		
		@Actions({
			@Action(value = "doCreateNewCustomerAccount", results = { @Result(location = "../masterAdmin/doCreateNewCustomerAccount.jsp", name = "success") })
		})
	    public String doCreateNewCustomerAccount() throws URTUniversalException {
			
			if(!StringFunctions.isNullOrEmpty(getParamValue("inviteId")))
			{
				InviteCustomerEnrollment inviteCustomerEnrollment = (InviteCustomerEnrollment) adminManager.get(InviteCustomerEnrollment.class, "id=" + getParamValue("inviteId") + " and status='"+Constants.PENDING_STATUS+"'");
				if (!ObjectFunctions.isNullOrEmpty(inviteCustomerEnrollment)) {
					setInviteCustomerEnrollment(inviteCustomerEnrollment);
					
			    	setSmsServiceProvidersList(adminManager.getAll(SMSServiceProviders.class));
			    	setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			    	String packageId=getParamValue("packageDetails_");
			    	setTempString(packageId);
			    	setOrganizationTypesList(adminManager.getAll(OrganizationTypes.class));
			    	setObjectList(adminManager.getAll(Organization.class));
			    	setSyllabusTypeList(adminManager.getAll(SyllabusType.class,"status='Y'"));
			    	setCustomerEnrollmentRequest((CustomerEnrollmentRequest) adminManager.get(CustomerEnrollmentRequest.class, "status='"+Constants.PENDING_STATUS+"' and inviteCustomerEnrollmentId="+getInviteCustomerEnrollment().getId()));
			    	if (!ObjectFunctions.isNullOrEmpty(getCustomerEnrollmentRequest())) {
			    		Set<SyllabusType> syllabusTypeSet = getCustomerEnrollmentRequest().getSyllabusType();
				    	if(!ObjectFunctions.isNullOrEmpty(syllabusTypeSet))
				    	{
				    		for(SyllabusType syllabusType : syllabusTypeSet)
				    		{
				    			getChkBoxSelectedIds().add(String.valueOf(syllabusType.getId()));
				    		}
				    	}
			    	}
				}
			}
			return SUCCESS;
		}
		
		@Actions({
			@Action(value = "urlSaveNewCustomerDetails", results = { @Result(location = "../masterAdmin/customerEnrollmentRequestMsg.jsp", name = "success") })
		})
	    public String urlSaveNewCustomerDetails() throws URTUniversalException {
			
			State state = null;
			CustomerEnrollmentRequest customerEnrollmentRequest = new CustomerEnrollmentRequest();
			Address primaryAddress=new Address();
			Address customerAddress=new Address();
			if(getCustomerEnrollmentRequest().getId() > 0)
			{
				customerEnrollmentRequest =  (CustomerEnrollmentRequest) adminManager.get(CustomerEnrollmentRequest.class, "id=" + getCustomerEnrollmentRequest().getId() + " and status='"+Constants.PENDING_STATUS+"' and inviteCustomerEnrollmentId="+getInviteCustomerEnrollment().getId());
				if (!ObjectFunctions.isNullOrEmpty(customerEnrollmentRequest)) 
				{
					primaryAddress= customerEnrollmentRequest.getAddress();
					customerAddress = customerEnrollmentRequest.getOrganizationAddress();
					
					adminManager.remove("syllabusCustomerEnrollmentInfo", "customerEnrollmentRequestId=" + customerEnrollmentRequest.getId());
				}
			}
				InviteCustomerEnrollment inviteCustomerEnrollment = (InviteCustomerEnrollment) adminManager.get(InviteCustomerEnrollment.class, "id=" + getInviteCustomerEnrollment().getId() + " and status='"+Constants.PENDING_STATUS+"'");
				if (!ObjectFunctions.isNullOrEmpty(inviteCustomerEnrollment)) 
				{
					primaryAddress.setStreetName(getCustomerEnrollmentRequest().getOrganizationAddress().getStreetName());
	     			primaryAddress.setCity(getCustomerEnrollmentRequest().getOrganizationAddress().getCity());
	     			primaryAddress.setAddressLine1(getCustomerEnrollmentRequest().getOrganizationAddress().getAddressLine1());
	     			primaryAddress.setPostalCode(getCustomerEnrollmentRequest().getOrganizationAddress().getPostalCode()); 
	     			primaryAddress.setEmail(getCustomerEnrollmentRequest().getCustEmail());
	     			customerEnrollmentRequest.setFirstName(getCustomerEnrollmentRequest().getFirstName());
	     			customerEnrollmentRequest.setLastName(getCustomerEnrollmentRequest().getLastName());
	     			customerEnrollmentRequest.setContactNumber(getCustomerEnrollmentRequest().getContactNumber());
	     			customerEnrollmentRequest.setMobileNumber(getCustomerEnrollmentRequest().getMobileNumber());
		 			if(!ObjectFunctions.isNullOrEmpty(getState().getId()) && getState().getId() > 0)
		 			{
		 				state = (State) adminManager.get(State.class,"id="+getState().getId());
		 				if(!ObjectFunctions.isNullOrEmpty(state)){
		 				 primaryAddress.setState(state.getStateCode()); 
		 				 primaryAddress.setStateId(state.getId());
		 				}
		 				state=null;
		 			}
		 			else if(!ObjectFunctions.isNullOrEmpty(getCustomerEnrollmentRequest().getOrganizationAddress().getStateId()))
		 			{
		 				state = (State) adminManager.get(State.class,"id="+getCustomerEnrollmentRequest().getOrganizationAddress().getStateId());
		 				if(!ObjectFunctions.isNullOrEmpty(state))
		 				primaryAddress.setState(state.getStateCode());
		 				primaryAddress.setStateId(state.getId());
			 			state=null;
		 			}
		 			
				    customerAddress.setStreetName(getCustomerEnrollmentRequest().getAddress().getStreetName());
				    customerAddress.setCity(getCustomerEnrollmentRequest().getAddress().getCity());
				    customerAddress.setState(getCustomerEnrollmentRequest().getAddress().getState());     			
				    customerAddress.setPostalCode(getCustomerEnrollmentRequest().getAddress().getPostalCode());
				    customerEnrollmentRequest.setOrganizationAddress(primaryAddress); 
				    customerEnrollmentRequest.setAddress(customerAddress);
				    customerEnrollmentRequest.setFeeReceiptModel(getCustomerEnrollmentRequest().getFeeReceiptModel());
				    customerEnrollmentRequest.setCustEmail(getCustomerEnrollmentRequest().getCustEmail());
				    customerEnrollmentRequest.setOrganizationLevel(getCustomerEnrollmentRequest().getOrganizationLevel());
				    customerEnrollmentRequest.setCustomerMission(getCustomerEnrollmentRequest().getCustomerMission());
				    customerEnrollmentRequest.setCustomerVision(getCustomerEnrollmentRequest().getCustomerVision());
				    customerEnrollmentRequest.setCustomerShortName(getCustomerEnrollmentRequest().getCustomerShortName());
				    customerEnrollmentRequest.setOrgnizationTypeId(getCustomerEnrollmentRequest().getOrgnizationTypeId());
				    customerEnrollmentRequest.setOrganization(getCustomerEnrollmentRequest().getOrganization());
				    customerEnrollmentRequest.setPassword(getCustomerEnrollmentRequest().getPassword());
				    
				    customerEnrollmentRequest.setTermsConditions(Constants.ACTIVE_STATUS);
				    
				    
				    if("on".equalsIgnoreCase(getParamValue("isHostel")))
				    	customerEnrollmentRequest.setHostelModuleStatus(true);
				    else
				    	customerEnrollmentRequest.setHostelModuleStatus(false);
				    
				    if("on".equalsIgnoreCase(getParamValue("isTransport")))
				    	customerEnrollmentRequest.setTransportModuleStatus(true);
				    else
				    	customerEnrollmentRequest.setTransportModuleStatus(false);
				    
				    
				    PackageDetails packageDetails = (PackageDetails) adminManager.get(PackageDetails.class, "id=" + inviteCustomerEnrollment.getId());
				    customerEnrollmentRequest.setPackageDetails(packageDetails);
				    
				    if (!ObjectFunctions.isNullOrEmpty(getChkBoxSelectedIds())) 
					{
						for (Object subjectIdObj : getChkBoxSelectedIds()) {
							try {
								String syllabusTypeId = (String) subjectIdObj;
								SyllabusType syllabusType = (SyllabusType) adminManager.get(SyllabusType.class, Long.parseLong(syllabusTypeId));
								customerEnrollmentRequest.addSyllabusTypeInfo(syllabusType);
								syllabusType = null;
							} catch (Exception ex) {
								log.debug(ex.getMessage());
								ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
							}
						}
					}
	 			    
				    customerEnrollmentRequest.setStatus(Constants.PENDING_STATUS);
				    customerEnrollmentRequest.setInviteCustomerEnrollment(inviteCustomerEnrollment);
					setCustomerEnrollmentRequest((CustomerEnrollmentRequest) adminManager.save(customerEnrollmentRequest));
					
					
					List<Object> emailsList = new ArrayList<Object>();
					List<Object> supportEmails = adminManager.getAll("select email from supportTeam");
					if (!ObjectFunctions.isNullOrEmpty(getCustomerEnrollmentRequest()) && !ObjectFunctions.isNullOrEmpty(supportEmails)) {
						String[] emailAddresses = new String[emailsList.size()];
						if (!ObjectFunctions.isNullOrEmpty(emailsList)) {
							List<Object> emailsIdsList = new ArrayList(new HashSet(emailsList));
							int i=0;
							for (Object staffEmailIds : emailsIdsList) {
								emailAddresses[i] = staffEmailIds.toString();
								i++;
							}
							StringBuffer msg = new StringBuffer("Dear Support Team");
							msg.append("<br/>");
							msg.append("A new client has requested for access to the EazySchool product, So please login to support account and check in new customer details to accept / reject.");
							MailUtil mailUtil = new MailUtil(emailAddresses,"New Customer Requested for the eazyschool account "+getCustomerEnrollmentRequest().getOrganization()+" EazySchool Account Activation","", null, null,"messages@eazyschool.com");
							mailUtil.sendCustomerRequestToSupportingTeam("messages@eazyschool.com",msg);
							emailAddresses=null;
							mailUtil = null;
						}
					}
					
					packageDetails = null;
					inviteCustomerEnrollment = null;
				}
			return SUCCESS;
		}
		
		 @Actions({
				@Action(value = "ajaxDoApproveRejectCustomerRegistration", results = { @Result(location = "../masterAdmin/ajaxDoApproveRejectCustomerRegistration.jsp", name = "success") })
			})
		    public String ajaxDoApproveRejectCustomerRegistration() throws URTUniversalException {
			 
		    	setCustomerEnrollmentRequest((CustomerEnrollmentRequest) adminManager.get(CustomerEnrollmentRequest.class, "id="+getCustomerEnrollmentRequest().getId()));
		    	
		    	if (!ObjectFunctions.isNullOrEmpty(getCustomerEnrollmentRequest())) {
		    		Set<SyllabusType> syllabusTypeSet = getCustomerEnrollmentRequest().getSyllabusType();
			    	if(!ObjectFunctions.isNullOrEmpty(syllabusTypeSet))
			    	{
			    		for(SyllabusType syllabusType : syllabusTypeSet)
			    		{
			    			getChkBoxSelectedIds().add(String.valueOf(syllabusType.getId()));
			    		}
			    	}
		    	}
		    	setSmsServiceProvidersList(adminManager.getAll(SMSServiceProviders.class));
		    	setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
		    	String packageId=getParamValue("packageDetails_");
		    	setTempString(packageId);
		    	setOrganizationTypesList(adminManager.getAll(OrganizationTypes.class));
		    	setObjectList(adminManager.getAll(Organization.class));
		    	if(getUser().isSchoolSEO() || getUser().isSchoolDEO() || getUser().isSchoolCEO() || getUser().isSchoolBEO())
			    {
			    	GovStaffInfo govStaffInfo = (GovStaffInfo)adminManager.get(GovStaffInfo.class, " accountId=" + getUser().getId()+" and status='"+Constants.YES_STRING+"'");
			    }
		    	setSyllabusTypeList(adminManager.getAll(SyllabusType.class,"status='Y'"));
				return SUCCESS;
			}
		 
		
}