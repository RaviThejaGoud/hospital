package com.urt.webapp.action.masteradmin;

import java.io.File;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.SendNotifications;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.StudyAndBonafiedSettings;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.common.ViewUserLoginMetaData;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.CustomerEnrollmentRequest;
import com.urt.persistence.model.customer.InviteCustomerEnrollment;
import com.urt.persistence.model.customer.MasterCustomer;
import com.urt.persistence.model.customer.Organization;
import com.urt.persistence.model.customer.OrganizationMembers;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.exam.DefaultScoreCardTemplates;
import com.urt.persistence.model.exam.Developers;
import com.urt.persistence.model.exam.SupportTicket;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.subscription.PackageDetails;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.util.email.MailUtil;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;

/**
 * @author prasad
 *
 */
@Namespace("/masterAdmin")
public class MasterAdminAction extends BaseAction {
	

	private static final long serialVersionUID = -1646390427462403153L;
	
	private Customer customer;
	private List customersList;
	protected User regUser;
	private long assignedUserId;
	private long statusId;
	private List templateList;
	private Organization orgObj;
	private SendNotifications sendNotifications;
	
	
	
	
	public SendNotifications getSendNotifications() {
		return sendNotifications;
	}

	public void setSendNotifications(SendNotifications sendNotifications) {
		this.sendNotifications = sendNotifications;
	}
	public String autoCheck;
	
	public String getAutoCheck() {
		return autoCheck;
	}

	public void setAutoCheck(String autoCheck) {
		this.autoCheck = autoCheck;
	}

	public Organization getOrgObj() {
		return orgObj;
	}

	public void setOrgObj(Organization orgObj) {
		this.orgObj = orgObj;
	}

	public List getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List templateList) {
		this.templateList = templateList;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
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
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	public long getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	
	@Actions({ @Action(value = "customerDetails", results = { @Result(location = "ajaxMasterAdmin.jsp", name = "success") }) })
	public String customerDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'customerDetails' method");
		}
		try {
			 
			Map<Long,String>  countriesNamesMap = getCountriesNamesMap();
			 List<Customer> allActiveCustomersList = adminManager.getAll(Customer.class,"accountType='C' and status='Y' and organizationSubTypeId = 0 order by createdDate desc");
			 if(!ObjectFunctions.isNullOrEmpty(allActiveCustomersList))
			 {
				 for(Customer customer : allActiveCustomersList)
				 {
					if(!ObjectFunctions.isNullOrEmpty(customer.getAddress()))
						customer.setCountryName(countriesNamesMap.get(customer.getAddress().getCountryId()));
					else
						customer.setCountryName("India");
					getCustomersList().add(customer);
				 }
				 List<Customer> interNationCustomersList = adminManager.getAllHqlQuery("FROM Customer c WHERE c.address.countryId !='99' and c.accountType='C' and c.status='Y' and c.organizationSubTypeId = 0 order by c.createdDate desc"); // Getting International Customers List
				 if(!ObjectFunctions.isNullOrEmpty(interNationCustomersList))
				 {
					setTempId1(interNationCustomersList.size()); // International Customers Count
				 }
				
				 setTempId2(allActiveCustomersList.size() - getTempId1()); // Indian Customers Count
			 }
			 countriesNamesMap = null;
				
			 setTemplateList(adminManager.getAll(StudyAndBonafiedSettings.class));
			 if(ObjectFunctions.isNullOrEmpty(getTemplateList())){
				 List<StudyAndBonafiedSettings> templateList = getTemplateList();
				 for(StudyAndBonafiedSettings templateList1 : templateList){
					 if(!ObjectFunctions.isNullOrEmpty(customer)){
						long custId = customer.getId();
						 setTemplateList(adminManager.getAll(StudyAndBonafiedSettings.class, "bonafiedFileName ="+getFileName()));
					 }
				 }
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/*
	 * Made changes to update status of the customer when the customer is deleted from Master admin . With this customerStatus will be updated to N 
	 */
	@Actions( { @Action(value = "ajaxDeleteCustomer", results = { @Result(location = "ajaxMasterAdmin.jsp", name = "success") }) })
	public String ajaxDeleteCustomer() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteCustomer' method");
		}

		try {
			String customerId = getParamValue("id");
			if(StringFunctions.isNullOrEmpty(customerId))
			{
				if(!StringFunctions.isNullOrEmpty(getParamValue("customer.id")))
				{
					customerId=getParamValue("customer.id");
				}
			}
			//String leavesId=getParamValue("leavesId");
			if(!StringFunctions.isNullOrEmpty(customerId))
			{
				Customer customer=(Customer)adminManager.get(Customer.class, Long.valueOf(customerId));
				if(!ObjectFunctions.isNullOrEmpty(customer))
				{
				 customer.setStatus(false);
				 adminManager.save(customer);
				}
				//adminManager.remove(Customer.class, Long.valueOf(customerId));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoEditCustomer", results = { @Result(location = "ajaxDoEditCustomerSettings.jsp", name = "success") }) })
	public String ajaxDoEditCustomer() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoEditCustomer' method");
		}
		try {
			if(getCustId()>0)
			setCustomer((Customer) adminManager.get(Customer.class, getCustId()));
			setViewAllUsers((ViewAllUsers)adminManager.get(ViewAllUsers.class, "custId="+getCustomer().getId()+" and roleId=1"));
			setObjectList(adminManager.getAll(Organization.class));
			setSmsServiceProvidersList(adminManager.getAll(SMSServiceProviders.class,"isCustomerSpecific='N'"));
			setAcademicYear((AcademicYear)adminManager.get(AcademicYear.class,"custId="+getCustId()+" and status='Y'"));
			if(!ObjectFunctions.isNullOrEmpty(getCustomer()))
			{
				SMSServiceProviders sMSServiceProviders =(SMSServiceProviders) adminManager.get(SMSServiceProviders.class,"id="+getCustomer().getSmsServiceProviderId());
				if(!ObjectFunctions.isNullOrEmpty(getCustomer()))
				{
					setSmsServiceProvider(sMSServiceProviders);
				}
				sMSServiceProviders = null;
				//adminManager.updateCustomersFeeUpdatesInStudent(getCustomer().getId());
				//createStudentsMultiParentCredentails(getCustId());
			}
			//super.addActionMessage("Successfully fee configured customer students");
			customerDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoEditGovtCustomer", results = { @Result(location ="ajaxDoEditGovtCustomerSettings.jsp", name = "success") }) })
	public String ajaxDoEditGovtCustomer() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoEditGovtCustomer' method");
		}
		try {
			ajaxDoEditCustomer();
			//createStudentsMultiParentCredentails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxEditGovtCustomerSettings", results = { @Result(location ="../govt/ajaxGovtCustomers.jsp", name = "success") }) })
	public String ajaxEditGovtCustomerSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoEditGovtCustomer' method");
		}
		try {
			if (getCustId()>0 && !ObjectFunctions.isNullOrEmpty(getUserAcademicYearId())) {
				setAcademicYearId(getUserAcademicYearId());
				Customer customer = (Customer) adminManager.get(Customer.class, getCustId());
				customer.setOrganization(getCustomer().getOrganization());
				customer.setCustEmail(getCustomer().getCustEmail());
				customer.setContactNumber(getCustomer().getContactNumber());
				customer.setAllowedTotalSms(getCustomer().getAllowedTotalSms());
				String isHostelModuleStatus = getParamValue("customer.hostelModuleStatus");
				if (StringFunctions.isNotNullOrEmpty(isHostelModuleStatus)) {
					if ("on".equalsIgnoreCase(isHostelModuleStatus)) {
						customer.setHostelModuleStatus(true);
					} else {
						customer.setHostelModuleStatus(false);
					}
				} else
					customer.setHostelModuleStatus(false);
				String isTransportModuleStatus = getParamValue("customer.transportModuleStatus");
				if (StringFunctions.isNotNullOrEmpty(isTransportModuleStatus)) {
					if ("on".equalsIgnoreCase(isTransportModuleStatus)) {
						customer.setTransportModuleStatus(true);
					} else {
						customer.setTransportModuleStatus(false);
					}
				} else
					customer.setTransportModuleStatus(false);
				adminManager.save(customer);
				super.addActionMessage("Govt Customer Settings updated successfully.");
			}
			 setCustomersList(adminManager.getAll(Customer.class,"organizationSubTypeId > 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxEditCustomerSettings", results = { @Result(location ="ajaxCustomerDetails.jsp", name = "success") }) })
	public String ajaxEditCustomerSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditCustomerSettings' method");
		}
		try {
			if (getCustId()>0 && !ObjectFunctions.isNullOrEmpty(getUserAcademicYearId())) {
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, getAcademicYearId());
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					log.debug(academicYear.getPaidSms());
					academicYear.setAllotedsms(getAcademicYear().getAllotedsms());
					if(getTempId() > 0){
						academicYear.setPaidSms(academicYear.getPaidSms()+getTempId());
					}
					adminManager.save(academicYear);
				}
				setAcademicYearId(getUserAcademicYearId());
				Organization organization = null;
				Customer customer = (Customer) adminManager.get(Customer.class, getCustId());
				if(!ObjectFunctions.isNullOrEmpty(getCustomer().getOrganization()) && !ObjectFunctions.isNullOrEmpty(customer)){
					if(!customer.getOrganization().equalsIgnoreCase(getCustomer().getOrganization()))
						customer.setOrganization(getCustomer().getOrganization());
				}
				customer.setCustEmail(getCustomer().getCustEmail());
				customer.setContactNumber(getCustomer().getContactNumber());
				customer.setMobileNumber(getCustomer().getMobileNumber());
				customer.setSmsCost(getCustomer().getSmsCost());
				//customer.setAllowedTotalSms(getCustomer().getAllowedTotalSms());
				/*if(getCustomer().getOrgId() > 0)
					organization = (Organization) adminManager.get(Organization.class, getCustomer().getOrgId());
				else*/
					if(!StringFunctions.isNullOrEmpty(getCustomer().getOrganization()))
						organization = (Organization) adminManager.get(Organization.class, "organizationName='"+getCustomer().getOrganization().replaceAll("'","''").trim()+"'");
					
	 			if(ObjectFunctions.isNullOrEmpty(organization) && !StringFunctions.isNullOrEmpty(getCustomer().getOrganization()))
	 			{
	 				organization = new Organization();
	 				organization.setAddress(customer.getAddress());
					organization.setOrganizationName(getCustomer().getOrganization());
					organization.setStatus(Constants.YES_STRING);
					organization.setContactNumber(customer.getContactNumber());
					organization.setMobileNumber(customer.getMobileNumber());
					organization = (Organization) adminManager.save(organization);
	 			}
	 			if(!ObjectFunctions.isNullOrEmpty(organization)){
	 				customer.setOrgId(organization.getId());
	 				customer.setOrganization(organization.getOrganizationName());
	 			}else
	 				customer.setOrgId(0);
	 			customer.setSmsServiceProviderId(getCustomer().getSmsServiceProviderId());
				String isHostelModuleStatus = getParamValue("customer.hostelModuleStatus");
				if (StringFunctions.isNotNullOrEmpty(isHostelModuleStatus)) {
					if ("on".equalsIgnoreCase(isHostelModuleStatus)) {
						customer.setHostelModuleStatus(true);
					} else {
						customer.setHostelModuleStatus(false);
					}
				} else
					customer.setHostelModuleStatus(false);
				String isTransportModuleStatus = getParamValue("customer.transportModuleStatus");
				if (StringFunctions.isNotNullOrEmpty(isTransportModuleStatus)) {
					if ("on".equalsIgnoreCase(isTransportModuleStatus)) {
						customer.setTransportModuleStatus(true);
					} else {
						customer.setTransportModuleStatus(false);
					}
				} else
					customer.setTransportModuleStatus(false);
				adminManager.save(customer);
				if(getPersonVo().getId() > 0){
					Person person = (Person) adminManager.get(Person.class, getPersonVo().getId());
					if(!ObjectFunctions.isNullOrEmpty(person)){
						person.setMobileNumber(getViewAllUsers().getMobileNumber());
						adminManager.save(person);
					}
					person=null;
				}
				if(getAddressVo().getId()>0){
					Address address = (Address) adminManager.get(Address.class, getAddressVo().getId());
					if(!ObjectFunctions.isNullOrEmpty(address)){
						address.setEmail(getViewAllUsers().getStaffEmail());
						adminManager.save(address);
					}
					address=null;
				}
				super.addActionMessage("CustomerSettings  updated successfully.");
			}
			customerDetails();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDocancelCustomer", results = { @Result(location = "ajaxCustomerDetails.jsp", name = "success") }) })
	public String ajaxDocancelCustomer() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDocancelCustomer' method");

		}
		try {
			customerDetails();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "masterAdminHome", results = { @Result(location = "masterAdminHome.jsp", name = "success") }),
		 @Action(value = "ajaxViewPackageDetails", results = { @Result(location = "ajaxViewPackages.jsp", name = "success") })})
	public String masterAdminHome() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'masterAdminHome' method");
		}
		try {
			setPackageDetailsList(adminManager.getAll(PackageDetails.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxDoAddPackage", results = { @Result(location = "ajaxAddPackage.jsp", name = "success") }) })
	public String ajaxDoAddPackage() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddPackage' method");
		}
		try {
			String id=getParamValue("id");
			if(!StringFunctions.isNullOrEmpty(id)){
				setPackageDetails((PackageDetails)adminManager.get(PackageDetails.class, Long.valueOf(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxAddPackgeDetails", results = { @Result(location = "ajaxViewPackages.jsp", name = "success") }) })
	public String ajaxAddPackgeDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddPackgeDetails' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getPackageDetails())){
				adminManager.save(getPackageDetails());
				super.addActionMessage("Package details addded successfully.");
			}
			masterAdminHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxEditPackgeDetails", results = { @Result(location = "ajaxViewPackages.jsp", name = "success") }) })
	public String ajaxEditPackgeDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddPackgeDetails' method");
		}
		try {
			String id=getParamValue("id");
			if(!StringFunctions.isNullOrEmpty(id)){
				PackageDetails details = (PackageDetails)adminManager.get(PackageDetails.class, Long.valueOf(id));
				//details.setPackageName(getPackageDetails().getPackageName());
				details.setCostPerStudent(getPackageDetails().getCostPerStudent());
				details.setMaxAllowableStudents(getPackageDetails().getMaxAllowableStudents());
				details.setStudentsRange(getPackageDetails().getStudentsRange());
				adminManager.save(details);
				super.addActionMessage("Package details updated successfully.");
			    details=null;
			}
			masterAdminHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({ 
		@Action(value = "ajaxDoEditSenderId", results = { @Result(location = "ajaxDoEditSenderId.jsp", name = "success") }),
		@Action(value = "ajaxDoAddCustomer", results = { @Result(location = "ajaxAddCustomer.jsp", name = "success") }) })
	public String ajaxDoAddCustomer() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddCustomer' method");
		}
		try {
			String customerId = getParamValue("id");			
			if(!StringFunctions.isNullOrEmpty(customerId)){
				setCustomer((Customer) adminManager.get(Customer.class, Long.valueOf(customerId)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxAddCustomerDetails", results = { @Result(location = "ajaxCustomerDetails.jsp", name = "success") }) })
	public String ajaxAddCustomerDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddCustomerDetails' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getCustomer())){
				log.debug("----------In step--------------create account");
				User user=userManager.getUserByUserName(getCustomer().getCustEmail());
				Address primaryAddress=new Address();
				primaryAddress.setEmail(getCustomer().getCustEmail());
     			String encryptPassword=PasswordUtils.passwordEncoder(getCustomer().getPassword(),null); 
     			getRegUser().setUsername(getCustomer().getCustEmail());
     		    getRegUser().setPassword(encryptPassword); 
 			   // getRegUser().setPrimaryContact(phoneNumber);
 			    getRegUser().setPrimaryAddress(primaryAddress);
	 			Customer customer = new Customer();
	 			customer.setOrganization(getCustomer().getOrganization());
	 			customer.setCustEmail(getCustomer().getCustEmail());
	 			customer.setContactNumber(getCustomer().getContactNumber());
	 			adminManager.save(customer);
				if(!ObjectFunctions.isNullOrEmpty(user))
				{	
					super.addActionError("Email Address already Exists.");
					return "signup";
	     			
				}
				
			customer=null;
				setCustomer(null);
				super.addActionMessage("New Customer added succesfully.");
			customerDetails();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "feedbackMasterAdminHome", results = { @Result(location = "ajaxFeedbackMasterAdmin.jsp", name = "success") }) })
	public String feedbackMasterAdminHome() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'feedbackMasterAdminHome' method");
		}
		try {
			 setCustomersList(adminManager.getAll(Customer.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewCustomerMessage", results = { @Result(location = "customerMessages.jsp", name = "success") }) })
	public String ajaxViewCustomerMessage() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewCustomerMessage' method");
		}
		try {
			String id=getParamValue("id");
			List supportTicketList= adminManager.getAllByCustId("SupportTicket",Long.valueOf(id),0);
			if (!ObjectFunctions.isNullOrEmpty(supportTicketList)) {
				setSupportTicketMessagesList(supportTicketList);
				Collections.sort(getSupportTicketMessagesList());
			}
			setObjectList(adminManager.getAllByCustId("CommonType",getUserCustId(),0));
			setDevelopersList(adminManager.getAll(Developers.class));
			String assignedUserId = getParamValue("assignedUserId");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxAssignUserForIssue", results = { @Result(location = "ajaxFeedbackDetails.jsp", name = "success") }) })
	public String ajaxAssignUserForIssue() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAssignUserForIssue' method");
		}
		try {
			String supportTicketId =  getParamValue("tempId");
			long assignedUserId = getAssignedUserId();
			Customer customer = null;
 			if(getUserCustId() > 0)
 				customer=(Customer)adminManager.get(Customer.class, getUserCustId());
 			else
    	    	customer = (Customer)adminManager.get(Customer.class, getUser().getCustId());
			SupportTicket supportTicket=(SupportTicket)adminManager.get(SupportTicket.class, Long.valueOf(supportTicketId));
			if (!ObjectFunctions.isNullOrEmpty(assignedUserId)) {
				Developers developers=(Developers)adminManager.get(Developers.class, Long.valueOf(assignedUserId));
				if (!ObjectFunctions.isNullOrEmpty(developers))
				{
				 supportTicket.setAssignedUserId(assignedUserId);
				 supportTicket.setStatus("I");
				 adminManager.save(supportTicket);
				 MailUtil mailUtil=new MailUtil(null,"Information You Requested from Eazy School",customer.getId(),customer.getSender(),getUser().getUserRoleDescription(),"messages@eazyschool.com");
				 mailUtil.sendMailForDevelopers(developers,"messages@eazyschool.com");
				 mailUtil=null;
				// sendMailForDevelopers(developers);
				}
				developers=null;
		  }
			super.addActionMessage("User assigned succesfully [check an automated e-mail].");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxManageSMSProviders", results = { @Result(location = "ajaxManageSMSProviders.jsp", name = "success") }) })
	public String ajaxManageSMSProviders() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageSMSProviders' method");
		}
		try {
			setObjectList(adminManager.getAll(SMSServiceProviders.class));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxCreateSMSProvider", results = { @Result(location = "ajaxCreateSMSProvider.jsp", name = "success") }) })
	public String ajaxCreateSMSProvider() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSMSProvider' method");
		}
		try {
			if(getSmsServiceProvider().getId() > 0)
				setSmsServiceProvider((SMSServiceProviders)adminManager.get(SMSServiceProviders.class, getSmsServiceProvider().getId()));
			else
				setSmsServiceProvider(null);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSaveSmsProviderDetails", results = { @Result(location = "ajaxSMSProvidersDetails.jsp", name = "success") }) })
	public String ajaxSaveSmsProviderDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveSmsProviderDetails' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getSmsServiceProvider())){
				SMSServiceProviders smsUrl = null;
				if(getSmsServiceProvider().getId() > 0){
					smsUrl=(SMSServiceProviders)adminManager.get(SMSServiceProviders.class, getSmsServiceProvider().getId());
				}
				else{
					smsUrl = new SMSServiceProviders();
					smsUrl.setCreatedById(getUser().getId());
				}
				smsUrl.setActiveUrl(getSmsServiceProvider().isActiveUrl());
				smsUrl.setLastAccessDate(new Date());
				smsUrl.setLastUpdatedById(getUser().getId());
				smsUrl.setLastUpdatedDate(new Date());
				smsUrl.setServiceProvider(getSmsServiceProvider().getServiceProvider());
				smsUrl.setUrl(getSmsServiceProvider().getUrl());
				/*smsUrl.getUrl().replaceAll("���������", "");
				smsUrl.getUrl().replaceAll("[������������������������������������]", "");
				smsUrl.getUrl().replaceAll("[���������������������������������������������]", "");
				smsUrl.getUrl().replaceAll("[������������������������������������]", "");
				smsUrl.getUrl().replaceAll("[������������������������������������]", "");
				smsUrl.getUrl().replaceAll("���������", "");
				smsUrl.getUrl().replaceAll("[������������������������������������������������������]", "");
				smsUrl.getUrl().replaceAll("[������������������]", "");
				smsUrl.getUrl().replaceAll("[���������]", "");
				smsUrl.getUrl().replaceAll("���������", "���������");
				smsUrl.getUrl().replaceAll("[������������������������������������]", "");
				smsUrl.getUrl().replaceAll("���������", "");*/
				smsUrl = adminManager.saveSMSUrl(smsUrl);
				if(getSmsServiceProvider().isActiveUrl()){
					adminManager.updateRemainingSMSProviderstoInactiveState(smsUrl.getId());
				}
				smsUrl=null;
				super.addActionMessage("Succesfully Updated SMS Service Provider Details.");
				setSmsServiceProvider(null);
			}
			ajaxManageSMSProviders();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxDoCreateMaster", results = { @Result(location = "ajaxMasterMultiCustmerList.jsp", name = "success") }) })
	public String ajaxDoCreateMaster() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoCreateMaster' method");
		}
		try {
			setObjectList(adminManager.getAll(MasterCustomer.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxMasterAdminCustomerDetails", results = { @Result(location = "ajaxMasterMultiCustmerList.jsp", name = "success") }) })
	public String ajaxSaveCustomerDetails() throws URTUniversalException {
		try {
			Address customerAddress = null;
			MasterCustomer masterCustomer= null;
			if(getTempId()!=0){
				masterCustomer=(MasterCustomer)adminManager.get(MasterCustomer.class, getTempId());
				customerAddress = getMasterCustomer().getAddress();
				masterCustomer.setLastUpdatedDate(new Date());
				masterCustomer.setLastUpdatedById(getUser().getId());
			}else {
				customerAddress = new Address();
				masterCustomer = new MasterCustomer();
			}
			customerAddress.setStreetName(getMasterCustomer().getAddress().getStreetName());
			customerAddress.setCity(getMasterCustomer().getAddress().getCity());
			customerAddress.setState(getMasterCustomer().getAddress().getState());
			customerAddress.setPostalCode(getMasterCustomer().getAddress().getPostalCode());
			masterCustomer.setAddress(customerAddress);
			masterCustomer.setFirstName(getMasterCustomer().getFirstName());
			masterCustomer.setLastName(getMasterCustomer().getLastName());
			masterCustomer.setOrganization(getMasterCustomer().getOrganization());
			masterCustomer.setCustEmail(getMasterCustomer().getCustEmail());
			masterCustomer.setMobileNumber(getMasterCustomer().getMobileNumber());
			masterCustomer.setContactNumber(getMasterCustomer().getContactNumber());
			masterCustomer.setStatus(true);
			masterCustomer.setCustomerStatus("A");
			//adminManager.save(getMasterCustomer());
			log.debug("Organization Values are -----"+getAnyId());
			if(!ObjectFunctions.isNullOrEmpty(masterCustomer) && !StringFunctions.isNullOrEmpty(getAnyId())){
				List<Customer> customerList=adminManager.getAll(Customer.class, " id in ("+getAnyId()+")");
				if(!ObjectFunctions.isNullOrEmpty(customerList))
					masterCustomer.setCustomers(ConvertUtil.convertListToSet(customerList));
			}
			adminManager.save(masterCustomer);
			super.addActionMessage("Multi Branch Admin Registration completed successfully .");
			setCustomersList(adminManager.getAll(Customer.class));

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			setObjectList(adminManager.getAll(MasterCustomer.class));
			setMasterCustomer(null);
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxEditMultiBranchCustomer", results = { @Result(location = "../masterAdmin/ajaxManageMasterCustmerRegistration.jsp", name = "success") }) })
	public String ajaxEditMultiBranchCustomer() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditMultiBranchCustomer' method");
		}
		try {
			setMasterCustomer(null);
			setTempList(null);
			setObjectList(adminManager.getAll(MasterCustomer.class));
			setCustomersList(adminManager.getAll(Customer.class, " organization is not null"));
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			if (getTempId() != 0) {
				setMasterCustomer((MasterCustomer) adminManager.get(MasterCustomer.class, getTempId()));
				if (!ObjectFunctions.isNullOrEmpty(getMasterCustomer().getCustomers())) {
					for (Customer customer : getMasterCustomer().getCustomers()) {
						getTempList().add(customer.getId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxCustomerFeeConfiguration", results = { @Result(location = "ajaxCustomerDetails.jsp", name = "success") }) })
	public String ajaxCustomerFeeConfiguration() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCustomerFeeConfiguration' method");
		}
		try {
			Customer customer = (Customer)adminManager.get(Customer.class, getCustomer().getId());
			if(!ObjectFunctions.isNullOrEmpty(customer)){
				adminManager.updateCustomersFeeUpdatesInStudent(customer.getId());
			}
			super.addActionMessage("Successfully fee configured all custome's students");
			log.debug("Successfully fee configured all custome's students");
			customerDetails();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "downloadCustomerTemplates", results = {}) })
	public String downloadCustomerTemplates() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'downloadCustomerTemplates' method");
		}
		try {
			if (getCustId() > 0) {
				StringBuffer filePath = null;
				// getCustomerByCustId();
				Customer customer = (Customer) adminManager.get(Customer.class,
						getCustId());
				ZipOutputStream zipOutStream = new ZipOutputStream(
						getResponse().getOutputStream());
				if (getTempString().equalsIgnoreCase("ST")) {
					filePath = new StringBuffer("userfiles/").append(
							"ScoreCardTemplates/").append(
							customer.getCustomerShortName());
				}
				if (getTempString().equalsIgnoreCase("TL")) {
					filePath = new StringBuffer("userfiles/").append(
							"TCTemplate/").append(
							customer.getCustomerShortName());
				}
				if (getTempString().equalsIgnoreCase("BC")
						|| getTempString().equalsIgnoreCase("SC")) {
					filePath = new StringBuffer("userfiles/").append(
							"StudyAndBonafiedTemplate/").append(
							customer.getCustomerShortName());
				}
				File destFile = new File(getSession().getServletContext()
						.getRealPath(filePath.toString()));
				getResponse().setContentType("application/zip");
				if (StringFunctions.isNullOrEmpty(filePath.toString()))
					getResponse().addHeader("Content-Disposition",
							"attachment; filename=STUDENT_SCORE_DOCUMENTS.zip");
				else
					getResponse().addHeader("Content-Disposition",
							"attachment; filename=STUDENT_SCORE_DOCUMENTS.zip");
				File directory = null;
				directory = new File(destFile.toString());
				if (directory.exists()) {
					if (directory.listFiles().length > 0)
						StringFunctions.zipFiles(directory, zipOutStream);
				}
				zipOutStream = null;
				destFile = null;
				filePath = null;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// User IpAddress and Location *** 20/10/2014 *** Ramarao Jadapolu
	@Actions({ @Action(value = "ajaxGetUserIpAddressLocationDetails", results = { @Result(location = "ajaxUserIpAddressLocationDetails.jsp", name = "success") }) })
	public String ajaxGetUserIpAddressLocationDetails() {
		if (log.isInfoEnabled()) {
			log.info("Entering 'ajaxGetUserIpAddressLocationDetails' method");
		}
		try {
			List userLoginMetaDataList = adminManager.getAll(ViewUserLoginMetaData.class);
			if(!ObjectFunctions.isNullOrEmpty(userLoginMetaDataList)) {
				setObjectList(userLoginMetaDataList);
			}
		} catch (Exception ex) {
			ex.printStackTrace();JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxOrganizationMemberDetails", results = { @Result(location = "ajaxOrganizationMemberDetails.jsp", name = "success") }) })
	public String ajaxOrganizationMemberDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxOrganizationMemberDetails' method");
		}
		try {
			
			if(!getUser().isMasterAdmin())
			{
				if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("SessionOrganizationId"))){
				Organization organization = (Organization)adminManager.get(Organization.class,Long.valueOf(getSession().getAttribute("SessionOrganizationId").toString()));
				   if(!ObjectFunctions.isNullOrEmpty(organization))
				   {
					   if(!ObjectFunctions.isNullOrEmpty(organization.getOrganizationMembers()))
					   {
						   setObjectList(organization.getOrganizationMembers());
					   }
				   }
				}
			}
			else
			{
				setObjectList(adminManager.getAll(Organization.class));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoCreateOrganizationMembers", results = { @Result(location = "ajaxCreateTrustMemberRegistration.jsp", name = "success") }) })
	public String ajaxDoCreateOrganizationMembers() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoCreateOrganizationMembers' method");
		}
		try {
			//getStaffRoles().put("", Constants.SELECTROLE);
			if(getUser().isSecretary())
				getStaffRoles().put(Constants.SCHOOL_SECRETARY_PA, "Secretary PA");
			else
				getStaffRoles().put(Constants.SCHOOL_SECRETARY, "Secretary");
			
    		
    		String SessionOrganizationId = (String)getSession().getAttribute("SessionOrganizationId");
    		if(!StringFunctions.isNullOrEmpty(SessionOrganizationId)) 
    		{
    			setOrganizationObj((Organization)adminManager.get(Organization.class,"id='"+SessionOrganizationId+"'"));
    		}
    		else
    			setObjectList(adminManager.getAll(Organization.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxCreateOrganizationMembers", results = { @Result(location = "ajaxOrganizationMemberDetails.jsp", name = "success") }) })
	public String ajaxCreateOrganizationMembers() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateOrganizationMembers' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getNewUser()) && getOrganizationObj().getId() > 0)
			{
				User user=userManager.getUserByUserName(getNewUser().getUsername());
	 		   
			   if(!ObjectFunctions.isNullOrEmpty(user))
				{	
				   super.addActionError("Email Address already Exists.");
				   return "success";
				}
			   else
			   {
				   String encryptPassword=PasswordUtils.passwordEncoder("eazyschool123",null); 
				   Person person = new Person();
					Role role = new Role();
	     			String paramVal =getParamValue("roleName");
	     			role=userManager.getRoleByName(paramVal);
				   if(!ObjectFunctions.isNullOrEmpty(role))
				   {
					   Organization organization=(Organization) adminManager.get(Organization.class, getOrganizationObj().getId());
					   if(!ObjectFunctions.isNullOrEmpty(organization))
					   {
						   user = new User();
						   Address primaryAddress=new Address();
						   Staff staff = new Staff();
						   OrganizationMembers organizationMembers = new OrganizationMembers();
			     		   primaryAddress.setEmail(getNewUser().getUsername());
			     		   
			     		   person.setFirstName(getPerson().getFirstName());
				 		   person.setLastName(getPerson().getLastName());
				 		   person.setMobileNumber(getPerson().getMobileNumber());
				 		   person.setAddressType("R");
			     		   person = (Person)adminManager.save(person);
			     		   user.setPerson(person);
			     		   user.setCustId(0);
			     		   //user.setVersion(0);
						   
						   user.setPrimaryAddress(primaryAddress); 
				 		   user.setUsername(getNewUser().getUsername());
				 		   user.setPassword(encryptPassword);
				 		   user.addRole(role);
				 			
		    			   staff.setAccount(user);
		    			   staff.setCustId(0);
		    			   //staff.setVersion(0);
		    			   staff = (Staff)adminManager.save(staff);
		    			   
		    			   organizationMembers.setOrganizationRole(role);
		    			   organizationMembers.setOrganizationStaff(staff);
		    			   organizationMembers.setOrganizationUsers(staff.getAccount());
		    			   organizationMembers.setPeriodStartDate(new Date());
		    			   organizationMembers.setStatus(Constants.YES_STRING);
		    			   //organizationMembers.setVersion(0);
		    			   
		    			   organizationMembers.setCreatedById(getUser().getId());
		    			   organizationMembers.setCreatedDate(new Date());
		    			   organizationMembers.setLastAccessDate(new Date());
		    			   organizationMembers.setLastUpdatedById(getUser().getId());
		    			   organizationMembers.setLastUpdatedDate(new Date());
		    			   
		    			   organization.addOrganizationMembers(organizationMembers);
		    			   adminManager.save(organization);
		    			   
		    			   //insert into loginAccessbilityRoles(customerId,roleId,status,version) values (19,36,'Y',0);
		    			   
						   super.addActionMessage("secretary added successfully.");
						   
						   primaryAddress = null;
						   staff = null;
						   organizationMembers = null;
					   }
					   organization = null;
				   }
				   person = null;
			   }
			   user = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ajaxOrganizationMemberDetails();
		return SUCCESS;
	}
	/*public void createStudentsMultiParentCredentails(long custId){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'createStudentsMultiParentCredentails' method");
		}
        try {
        	if(custId >0){
        		Customer custObj=(Customer) adminManager.get(Customer.class,"id="+custId+" and status='Y'");
        		if(!ObjectFunctions.isNullOrEmpty(custObj)){
        			if(StringFunctions.isNotNullOrEmpty(custObj.getCustomerShortName())){
        				AcademicYear academicYearObj=(AcademicYear) adminManager.get(AcademicYear.class,"custId="+custObj.getId()+" and status='Y'");
        				if(!ObjectFunctions.isNullOrEmpty(academicYearObj)){
        					List<Student> studentDetailList=adminManager.getAll(Student.class,"custId="+custObj.getId()+" and academicYearId="+academicYearObj.getId());
            	        	log.debug("studentDetailList Size:-"+studentDetailList.size());
            	        	for(Student stuObj:studentDetailList){
            					if(!ObjectFunctions.isNullOrEmpty(stuObj)){
        							String response = createParentLoginCredentials(custObj.getId(),stuObj.getAccount().getId());
        							if(StringFunctions.isNotNullOrEmpty(response)){	
        								stuObj.getAccount().setParentId(response);
        							}
        							adminManager.save(stuObj);
            					}stuObj=null;
            	        	}academicYearObj=null;studentDetailList=null;studentDetailList=null;
        				}
        			}else{
        				log.debug("Customer Short Name Not Available..." + custObj.getCustomerShortName());
        			}
        		}custObj=null;
        	}
        } catch (Exception e) {
        	e.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
        }
    }*/
	
	@Actions( { @Action(value = "ajaxSendSMSToALLCustomersDetails", results = { @Result(location = "ajaxSendSmsToAllCustomers.jsp", name = "success") }) })
	public String ajaxSendSMSToALLCustomersDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendSMSToALLCustomersDetails' method");
		}
		try {
    		if(getUserCustId()>0) 
    			setObjectList(adminManager.getAll(Customer.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxSendSMSToAllCustomers", results = { @Result(location = "ajaxSendSmsToAllCustomers.jsp", name = "success") }) })
	public String ajaxSendSMSToAllCustomers() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendSMSToAllCustomers' method");
		}
		try {
			List<String> fileUploadList =  new ArrayList<String>();
			StringBuffer pathName = null;
			String[] attachedFiles = null;
			if(getFileUpload().size()!=0) { 
				pathName = new StringBuffer("userFiles/");
	    		pathName.append(getUserCustId());
	    		pathName.append("/uploadFiles");
	    		pathName.append("/");
				for(int i=0;i<getFileUpload().size();i++)
		    	{
		    		if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i)))
		    		{
		    			File file = getFileUpload().get(i);
			    		String fileName = getFileUploadFileName().get(i);
			            File destDir = new File(getSession().getServletContext().getRealPath(pathName.toString()+fileName));
			            FileUtils.copyFile(file, destDir);
			            file = null;
		    		}
		    	}
				 File folder = new File(getSession().getServletContext().getRealPath(pathName.toString()));
				 File[] listOfFiles = folder.listFiles();
				 if(!ObjectFunctions.isNullOrEmpty(listOfFiles)){
					 int i = 0;
					 for (File filePaths : listOfFiles) {
					     if (filePaths.isFile()) {
					        fileUploadList.add(filePaths.getAbsolutePath());
					        attachedFiles =fileUploadList.toArray(new String[fileUploadList.size()]);
					        log.debug(fileUploadList.get(i));
					     }
					 }
					 listOfFiles = null;
			    }
		    }
			Customer customer = adminManager.getCustomerByCustId(getUserCustId());
			SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
				if (!ObjectFunctions.isNullOrEmpty(getChkBoxSelectedIds())) {
					for (String chBox : getChkBoxSelectedIds()) {
						String alertType = chBox;
						Set<String> mobileNumbersSet = new HashSet<String>();
						StringBuffer smsContent;
						StringBuffer selectedMembers=new StringBuffer();
						if(!StringFunctions.isNullOrEmpty(getAnyTitle()) && "AA".equalsIgnoreCase(getAnyTitle()) && "AD".equalsIgnoreCase(getEventId()) && "AP".equalsIgnoreCase(getPlTitle())  && "AS".equalsIgnoreCase(getSelectedId()) && "ST".equalsIgnoreCase(getPlSubjectName()) ){
							if (!"E".equalsIgnoreCase(alertType)) 
							selectedMembers.append("32,");
						}if(!StringFunctions.isNullOrEmpty(getEventId()) && "AD".equalsIgnoreCase(getEventId())){
							if (!"E".equalsIgnoreCase(alertType)) 
							selectedMembers.append("1,");
						}
						if(!StringFunctions.isNullOrEmpty(getPlTitle()) && "AP".equalsIgnoreCase(getPlTitle()))
							selectedMembers.append("12,31,");
						if(!StringFunctions.isNullOrEmpty(getSelectedId()) && "AS".equalsIgnoreCase(getSelectedId()))
							selectedMembers.append("2,8,");
						if(!StringFunctions.isNullOrEmpty(getPlSubjectName()) && "ST".equalsIgnoreCase(getPlSubjectName()))
							selectedMembers.append("7,");
						selectedMembers.append("0");
						log.debug(selectedMembers.toString());
						List allMobileNumbers = null;
						if ("M".equalsIgnoreCase(alertType)) {
							if(!StringFunctions.isNullOrEmpty(getAnyTitle()) && "AA".equalsIgnoreCase(getAnyTitle()))
								allMobileNumbers = adminManager.getAll("select mobileNumber from vw_allUsers where roleId in ("+selectedMembers.toString()+") and (mobileNumber!=0 AND mobileNumber is not null AND mobileNumber !='+91-0000000000') and accountExpired = 'N' ");
							else
								allMobileNumbers = adminManager.getAll("select mobileNumber from vw_allUsers where custId="+getAnyTitle()+" and roleId in ("+selectedMembers.toString()+") and (mobileNumber!=0 AND mobileNumber is not null AND mobileNumber !='+91-0000000000') and accountExpired = 'N' ");
							if (!ObjectFunctions.isNullOrEmpty(allMobileNumbers)) {
								mobileNumbersSet.addAll(allMobileNumbers);
								User loggedUser = (User) adminManager.get(User.class, getUser().getId());
								if (!ObjectFunctions.isNullOrEmpty(loggedUser)) {
									Messages objMsg = new Messages();
									objMsg.setCreatedById(loggedUser.getId());
									objMsg.setCreatedDate(new Date());
									objMsg.setLastAccessDate(new Date());
									objMsg.setLastUpdatedDate(new Date());
									objMsg.setStatus("M");
									objMsg.setSmsSenderId(loggedUser.getUserRoleDescription());
									smsContent= new StringBuffer();
									smsContent.append("Dear Sir/Madam, ");
									smsContent.append(getMessages().getMessageDescription());
									//smsContent.append("we are developed sms app. You can download this Android app on to your Mobile / Tab from the playstore. Here is the playstore link for eazyschool app https://goo.gl/Q5Odw7.");
									smsContent.append(" Thank you from ");
									objMsg.setMessageDescription(smsContent.toString());
									objMsg.setCustomer(customer);
									objMsg.setSenderName(customer.getSender());
									if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)) {
										objMsg = adminManager.saveMessageDetailsTracking(objMsg,null,null,loggedUser);
										adminManager.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
										super.addActionMessage("SMS(s) has been delivered successfully.");
									}
									mobileNumbersSet = null;
									objMsg = null;
									smsContent=null;
								}
							}else{
								super.addActionError("SMS(s) has not been delivered.");
							}
							allMobileNumbers = null;
						}if ("E".equalsIgnoreCase(alertType)) 
						{
							List customerEmails = null;	
							List staffEmails = null;
							List parentEmails = null;
							List emailsList = new ArrayList();
							if(!StringFunctions.isNullOrEmpty(getAnyTitle()) && "AA".equalsIgnoreCase(getAnyTitle())){
								if(!StringFunctions.isNullOrEmpty(getAnyTitle()) && "AA".equalsIgnoreCase(getAnyTitle()) && "AD".equalsIgnoreCase(getEventId())){
									 customerEmails = adminManager.getAll("select custEmail from customer where accountType='C' and status='Y'");
									 if(!ObjectFunctions.isNullOrEmpty(customerEmails))
									 	emailsList.addAll(customerEmails);
								}if("AP".equalsIgnoreCase(getPlTitle()) || "AS".equalsIgnoreCase(getSelectedId())){
									staffEmails = adminManager.getAll("select staffEmail from vw_allUsers where roleId in ("+selectedMembers.toString()+") and (staffEmail!='' AND staffEmail is not null) and accountExpired = 'N' ");
									if(!ObjectFunctions.isNullOrEmpty(staffEmails))
										emailsList.addAll(staffEmails);
								}if("ST".equalsIgnoreCase(getPlSubjectName())){
									parentEmails = adminManager.getAll("select parentEmail from vw_allUsers where roleId in ("+selectedMembers.toString()+") and (parentEmail!='' AND parentEmail is not null) and accountExpired = 'N' ");
									if(!ObjectFunctions.isNullOrEmpty(parentEmails))
										emailsList.addAll(parentEmails);
								}
								
							}
							else{
								if("AD".equalsIgnoreCase(getEventId())){
									 customerEmails = adminManager.getAll("select custEmail from customer where Id="+getAnyTitle()+" and accountType='C' and status='Y'");
									 if(!ObjectFunctions.isNullOrEmpty(customerEmails))
										 	emailsList.addAll(customerEmails);
								}
								if("AP".equalsIgnoreCase(getPlTitle())  || "AS".equalsIgnoreCase(getSelectedId())){
									staffEmails = adminManager.getAll("select staffEmail from vw_allUsers where custId="+getAnyTitle()+" and roleId in ("+selectedMembers.toString()+") and (staffEmail!='' AND staffEmail is not null) and accountExpired = 'N' ");
									if(!ObjectFunctions.isNullOrEmpty(staffEmails))
										emailsList.addAll(staffEmails);
								}
								if("ST".equalsIgnoreCase(getPlSubjectName())){
									parentEmails = adminManager.getAll("select parentEmail from vw_allUsers where roleId in ("+selectedMembers.toString()+") and (parentEmail!='' AND parentEmail is not null) and accountExpired = 'N' ");
									if(!ObjectFunctions.isNullOrEmpty(parentEmails))
										emailsList.addAll(parentEmails);
								}
							}
							
							if (!ObjectFunctions.isNullOrEmpty(ConvertUtil.convertListToSet(emailsList))) {
								String[] emailAddresses = new String[ConvertUtil.convertListToSet(emailsList).size()];
								List<Object> emailsIdsList = new ArrayList(new HashSet(emailsList));
								int i = 0;
								for (Object staffEmailIds : emailsIdsList) {
									emailAddresses[i] = staffEmailIds.toString();
									i++;
								}

								MailUtil mailUtil = new MailUtil(emailAddresses, getMessages().getTitle(), getMessages().getEmailContent(), getUser(),attachedFiles,"messages@eazyschool.com");
								mailUtil.sendEmailToSchoolWideMessages("messages@eazyschool.com");
								if (fileUploadList.size() > 0) {
									for (String fileupload : fileUploadList) {
										File filePaths = new File(fileupload);
										filePaths.delete();
									}
								}
								mailUtil = null;
								super.addActionMessage("E-Mail(s) has been delivered successfully.");
							}
							else {
								super.addActionError("E-Mail(s) has not been delivered.");
							}
							customerEmails = null;
							staffEmails = null;
						}
						selectedMembers=null;
						customer=null;
					}
				}	
			}		
			ajaxSendSMSToALLCustomersDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/*@Actions({ @Action(value = "ajaxUpdateStudyClassIdInsteadOfClassId", results = { @Result(location = "ajaxParentCreateOrUpdateLoginDetails.jsp", name = "success") }) })
	public String updateStudyClassIdInsteadOfClassId() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateStudyClassIdInsteadO66fClassId' method");
		}
		try {
			List<Object[]> classExamTypes=adminManager.getAll("select classNameId,examTypeId from classExamTypes");
			//List<Object[]> classExamTypes=adminManager.getAll("select classNameId,examTypeId from classExamTypes where classNameId="+Long.valueOf(538));
			 for(Object scheduleClass[]: classExamTypes){
				if(!ObjectFunctions.isNullOrEmpty(scheduleClass[0]) && !ObjectFunctions.isNullOrEmpty(scheduleClass[1])){
//					setStudyClassList(adminManager.getAll(StudyClass.class,"classNameClassId="+Long.valueOf(scheduleClass[0].toString())));/custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and 
					//setStudyClassList(adminManager.getAll(StudyClass.class,"custId="+Long.valueOf(50)+" and academicYearId="+Long.valueOf(61)+" and classNameClassId="+Long.valueOf(538)));
					if(ObjectFunctions.isNotNullOrEmpty(getStudyClassList())){
						for(StudyClass studyClassObj:getStudyClassList()){
							int count=adminManager.getCount("classSectionExamTypes", "classSectionId="+studyClassObj.getId()+" and examTypeId="+scheduleClass[1].toString());
							if(count == 0)
							adminManager.insertStudyClassIdInsteadOfClassId(studyClassObj.getId(),Long.valueOf(scheduleClass[1].toString()));						
						}
					}
				}
				scheduleClass = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	*/
	@Actions( { @Action(value = "ajaxUpadateSenderIdName", results = { @Result(location = "ajaxCustomerDetails.jsp", name = "success") }) })
	public String ajaxUpadateSenderIdName() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpadateSenderIdName' method");
		}
		try {
			Customer customer = (Customer)adminManager.get(Customer.class, getCustomer().getId());
			if(!ObjectFunctions.isNullOrEmpty(customer)){
			if (customer.isCheckMobileService()) {
				SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
				Set<String> mobileNumbersSet = new HashSet<String>();
				StringBuffer smsContent;
					if (!ObjectFunctions.isNullOrEmpty(customer.getMobileNumber())) {
						mobileNumbersSet.add(customer.getMobileNumber());
						User loggedUser = (User) adminManager.get(User.class, getUser().getId());
						if (!ObjectFunctions.isNullOrEmpty(loggedUser)) {
							Messages objMsg = new Messages();
							objMsg.setCreatedById(loggedUser.getId());
							objMsg.setCreatedDate(new Date());
							objMsg.setLastAccessDate(new Date());
							objMsg.setLastUpdatedDate(new Date());
							objMsg.setStatus("M");
							objMsg.setSenderName(loggedUser.getUserRoleDescription());
							objMsg.setAcademicYear(getCurrentAcademicYear());
							smsContent= new StringBuffer();
							smsContent.append("Dear "+customer.getCustomerFullPersonName()+",");
							smsContent.append(" Your Eazyschool SMS sender Id is updated to "+getCustomer().getSender()+". Please call to EazySchool supporting team if sender ID is not updated.");
							smsContent.append(" Thank you from ");
							objMsg.setMessageDescription(smsContent.toString());
							objMsg.setCustomer(customer);
							objMsg.setSmsSenderId(customer.getSender());
							if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)) {
								log.debug("Before Calling Deviler SMS.....mobileNumberset---"+mobileNumbersSet+"-------messageDesc-----Your Eazyschool SMS sender Id is updated to");
								objMsg = adminManager.saveMessageDetailsTracking(objMsg,null,null,loggedUser);
								adminManager.deliverSms(objMsg,mobileNumbersSet,smsServiceProvider);
								super.addActionMessage("SMS(s) has been delivered successfully.");
							}
							mobileNumbersSet = null;
							objMsg = null;
							smsContent=null;
						}
					}else{
						super.addActionError("SMS(s) has not been delivered.");
					}
				} 
				if (customer.isCheckEmailService()) {
					List emailsList = new ArrayList();
					List  supportEmails = adminManager.getAll("select email from supportTeam");
					if(!ObjectFunctions.isNullOrEmpty(customer.getCustEmail()) && !ObjectFunctions.isNullOrEmpty(supportEmails)){
						emailsList.add(customer.getCustEmail());
						emailsList.addAll(supportEmails);
					if(!ObjectFunctions.isNullOrEmpty(customer.getCustEmail()) &&  ObjectFunctions.isNullOrEmpty(supportEmails))
						emailsList.add(customer.getCustEmail());
					if(ObjectFunctions.isNullOrEmpty(customer.getCustEmail()) &&  !ObjectFunctions.isNullOrEmpty(supportEmails))
							emailsList = new ArrayList(supportEmails);
					StringBuffer br=new StringBuffer();
					br.append("Dear "+customer.getCustomerFullPersonName()+",");
					br.append( "<br>" );br.append( " " );br.append( "<br>" );
					br.append(" This email is to confirm that your sender ID is updated to "+getCustomer().getSender()+". Please call EazySchool supporting team if your sender ID is not updated.");
					br.append( "<br>" );br.append( " " );br.append( "<br>" );
					br.append("Email : support@eazyschool.com");
					br.append( "<br>" );
					br.append("Phone : 080-46620999.");
					br.append( "<br>" );br.append( " " );br.append( "<br>" );
					br.append("**NOTE: This is an automated email from EazySchool Software. Please DO NOT REPLY to this email.");
					if(!ObjectFunctions.isNullOrEmpty(emailsList)){
						List emailsIdsList = new ArrayList(new HashSet(emailsList));
						for(Object staffEmailIds : emailsIdsList) {
							String[] emailAddresses = new String[1];
							emailAddresses[0] = staffEmailIds.toString();
							MailUtil mailUtil = new MailUtil(emailAddresses,"SMS Sender ID Update Confirmation",br.toString(),getUser() ,null,"messages@eazyschool.com");
							mailUtil.sendEmailToSupportingTeam("messages@eazyschool.com");
							mailUtil = null;
						}
						br=null;
						super.addActionMessage("E-Mail(s) has been delivered successfully.");
					}
					else{
						super.addActionError("E-Mail(s) has not been delivered.");
					}
				 }
			} 
			customer.setSender(getCustomer().getSender());
			customer.setSenderIdDesc(getCustomer().getSenderIdDesc());
			adminManager.save(customer);
			customerDetails();
			super.addActionMessage("Successfully updated sender Id");
			customer=null;
		}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/* @Description 24th AUG 2015  RaviTejaGoud : Modularization  below method  show disable the customerDescription */  
	@Actions( { @Action(value = "ajaxDoDisableCustomer", results = { @Result(location = "ajaxDoCustomerDisable.jsp", name = "success") }) })
    public String ajaxDoDisableCustomer() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDoDisableCustomer' method");
		}
		try {
			 setCustomer(null);
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
    }
	@Actions( { @Action(value = "ajaxDoEnableCustomer", results = { @Result(location = "ajaxDoCustomerEnable.jsp", name = "success") }) })
    public String ajaxDoEnableCustomer() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDoEnableCustomer' method");
		}
		try {
			 setCustomer(null);
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
    }
	/* @Description 24th AUG 2015  RaviTejaGoud : Modularization  below method  show disable the customer*/  
	@Action(value = "ajaxDisableCustomer", results = { @Result(location = "ajaxCustomerDetails.jsp", name = "success") }) 
	 public String ajaxDisableCustomer() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxDisableCustomer' method");
		}
		try {
			 Map<String,String> msg = adminManager.disableCustomer( getTempId(),getCustomer().getCustomerInActiveDescription(), getUser().getId());
			 addActionMessages(msg);
			 setPlTitle("D"); //D for Deactive(inActiveCustomer) the customers
			 ajaxActiveAndInActiveCustomerSMSAndEmail();
			 customerDetails();
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
    }
	/* @Description 24th AUG 2015  RaviTejaGoud : Modularization  below method  disable the Customer*/  
	@Actions( { @Action(value = "ajaxViewExpiredCustomers", results = { @Result(location = "ajaxViewExpiredCustomersList.jsp", name = "success") }) })
	public String ajaxViewExpiredCustomers() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewExpiredCustomers' method");
		}
		try {
			Map<Long,String>  countriesNamesMap = getCountriesNamesMap();
			List<Customer> allInActiveCustomersList = adminManager.getAll(Customer.class,"accountType='C' and status='"+Constants.NO_STRING+"' and customerInActiveDescription is not null order by createdDate desc");
			
			 if(!ObjectFunctions.isNullOrEmpty(allInActiveCustomersList))
			 {
				 for(Customer customer : allInActiveCustomersList)
				 {
					if(!ObjectFunctions.isNullOrEmpty(customer.getAddress()))
						customer.setCountryName(countriesNamesMap.get(customer.getAddress().getCountryId()));
					else
						customer.setCountryName("India");
					getObjectList().add(customer);
				 }
				 List<Customer> interNationCustomersList = adminManager.getAllHqlQuery("FROM Customer c WHERE c.address.countryId !='99' and c.accountType='C' and  c.status='"+Constants.NO_STRING+"' and c.customerInActiveDescription is not null order by c.createdDate desc"); // Getting Indian Customers List
				 if(!ObjectFunctions.isNullOrEmpty(interNationCustomersList))
				 {
					setTempId1(interNationCustomersList.size()); // National Customes Count
				 }
				
				 setTempId(allInActiveCustomersList.size() - getTempId1());
			 }
			 countriesNamesMap = null;
			/*if(ObjectFunctions.isNotNullOrEmpty(getObjectList())) 
					Collections.sort(getObjectList());*/
 		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	/* @Description24th AUG 2015  RaviTejaGoud : Modularization  below method  enable the Customer*/  
	@Actions( { @Action(value = "ajaxEnableCustomerfDetails", results = { @Result(location = "ajaxViewExpiredCustomersList.jsp", name = "success") }) })
	public String ajaxEnableCustomerfDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEnableCustomerfDetails' method");
		}
		try {
			if(getTempId()>0){  //here tempId is custId
				/*List<User> userList = adminManager.getAll(User.class,"custId="+getTempId());
				if(!ObjectFunctions.isNullOrEmpty(userList)){
					for(User user: userList){
						user.setAccountExpired(false);
						user.setEnabled(true);
						adminManager.save(user);
					}
				}*/
				Customer customer = null;
				customer =(Customer)staffManager.get(Customer.class,getTempId());
				//customer.setCustomerStatus("A");  //A for Active the customers
				customer.setCustomerInActiveDescription(getCustomer().getCustomerInActiveDescription());
				customer.setStatus(true);
				customer.setCreatedDate(new Date());
				adminManager.save(customer);
				super.addActionMessage("Customer Account Activated successfully.");
				customer=null;
			}
			setPlTitle("A");  //A for Active(enableCustomer) the customers
			ajaxActiveAndInActiveCustomerSMSAndEmail();
			ajaxViewExpiredCustomers();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	/* @Description 25th AUG 2015  RaviTejaGoud : Send Mail and SMS in active or Inactive customer*/  
	@Actions( { @Action(value = "ajaxActiveAndInActiveCustomerSMSAndEmail", results = { @Result(location = "ajaxCustomerDetails.jsp", name = "success") }) })
	public String ajaxActiveAndInActiveCustomerSMSAndEmail() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxActiveAndInActiveCustomerSMSAndEmail' method");
		}
		try {
			customerActivateAndInactivateSMSAndEmailCommunication();
			customerDetails();
		}catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	/* @Description 15th Oct 2015 RaviTeja: added organization add to multiple school*/  
	@Actions( { @Action(value = "ajaxOranizationCustomerView", results = { @Result(location = "ajaxOrganizationCustomerDetails.jsp", name = "success") }) })
	public String ajaxOranizationCustomerView() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxOranizationCustomerView' method");
		}
		try {
			setTempList(adminManager.getAll(Organization.class));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxOranizationCustomerDetails", results = { @Result(location = "ajaxOrganizationCustomers.jsp", name = "success") }) })
	public String ajaxOranizationCustomer() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doAddStaff' method");
		}
		try {
			Organization organization = null;
			if(getTempId() != 0){
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				setCustomersList(adminManager.getAll(Customer.class,"status='Y' and organizationSubTypeId = 0 and id not in (1) and (orgId=0 or orgId="+getTempId()+") and organization is not null order by id"));
				organization =(Organization) adminManager.get(Organization.class,"id="+getTempId());
				setCustomerList(adminManager.getAll(Customer.class,"status='Y' and orgId="+getTempId()+" and organization is not null order by id"));
					setOrgObj(organization);
					setAddress(organization.getAddress());
					if(!ObjectFunctions.isNullOrEmpty(getCustomerList())){
							for(Customer obj:getCustomerList()){
								getChkBoxSelectedIds().add(String.valueOf(obj.getId()));
							}
					}
			}else{
				setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
				setCustomersList(adminManager.getAll(Customer.class,"status='Y' and organizationSubTypeId = 0 and (orgId is null or orgId=0) and id not in (1) and organization is not null order by id"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { 
	    @Action(value = "ajaxCheckOrganizationName", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
		public String ajaxCheckOrganizationName() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckOrganizationName' method");
		}
		try {
		    String organizationName = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(organizationName)) {
				List organizationNameList = adminManager.getAll(Organization.class, "organizationName='"+organizationName+"'");
				if (ObjectFunctions.isNullOrEmpty(organizationNameList)) {
					setAutoCheck("0");
				} else if (organizationNameList.size() > 0) {
					setAutoCheck("1");
				} else {
					setAutoCheck("0");
				}
				organizationNameList=null;
		    }
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoAddOraganizationCustomers", results = { @Result(location = "ajaxOrganizationCustomerDetails.jsp", name = "success") }) })
	public String ajaxDoAddOraganizationCustomers() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddOraganizationCustomers' method");
		}
		try {
			Organization organization = null;
			Address address = null;
			if(!ObjectFunctions.isNullOrEmpty(getOrgObj())  && !ObjectFunctions.isNullOrEmpty(getAddress())){
				if(getTempId() != 0){
					organization =(Organization) adminManager.get(Organization.class,"id="+getTempId());
						organization.setLastAccessDate(new Date());
						organization.setLastUpdatedById(getUser().getId());
						organization.setLastUpdatedDate(new Date());
						address = organization.getAddress(); 
						setPlTitle("OU"); // OU means OrganizationUpdate
				}else{
					organization = new Organization();
						organization.setCreatedById(getUser().getId());
						organization.setCreatedDate(new Date());
					address = new Address();	
				}
						organization.setOrganizationName(getOrgObj().getOrganizationName());
						organization.setMobileNumber(getOrgObj().getMobileNumber());
						organization.setStatus("Y");
						address.setStreetName(getAddress().getStreetName());
						address.setCity(getAddress().getCity());
						address.setState(getAddress().getState());
						address.setPostalCode(getAddress().getPostalCode());
						address=(Address) adminManager.save(address);
						organization.setAddress(address);
					   adminManager.saveOrUpdateObject(organization);
					   setTempId(organization.getId());
						if(!ObjectFunctions.isNullOrEmpty(getAnyTitle())){
							 adminManager.updateSelectedCustomersOrgId(getAnyTitle(),getTempId(),getTempString());
						}
						if(!ObjectFunctions.isNullOrEmpty(getPlTitle())){
							if(getPlTitle().equalsIgnoreCase("OU"))
								super.addActionMessage("Successfully update organization customers.");
						} else
							super.addActionMessage("Successfully added organization customers.");
			}
			organization =null;
			address =null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		ajaxOranizationCustomerView();
		return SUCCESS;
	}
	@Actions( { 
	    @Action(value = "ajaxCheckTrustMemberEmail", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
		public String ajaxCheckTrustMemberEmail() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckTrustMemberEmail' method");
		}
		try {
		    String checkEmail = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(checkEmail)) {
				List checkEmailList = adminManager.getAll(User.class, "username='"+checkEmail+"'");
				if (ObjectFunctions.isNullOrEmpty(checkEmailList)) {
					setAutoCheck("0");
				} else if (checkEmailList.size() > 0) {
					setAutoCheck("1");
				} else {
					setAutoCheck("0");
				}
				checkEmailList=null;
		    }
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "ajaxGenerateBarcodeExistingStudents", results = { @Result(location = "ajaxParentCreateOrUpdateLoginDetails.jsp", name = "success") }) })
	public String ajaxGenerateBarcodeExistingStudents() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGenerateBarcodeExistingStudents' method");
		}
		try {
			User user=null;
			List<User> userList =null;
			List<Customer> customersList=adminManager.getAll(Customer.class,"status='"+Constants.YES_STRING+"' and barcodeStatus='"+Constants.YES_STRING+"' "); 
			if(ObjectFunctions.isNotNullOrEmpty(customersList)){
				for(Customer custObj:customersList){
	        		if(!ObjectFunctions.isNullOrEmpty(custObj)){
	        			if("Y".equalsIgnoreCase(custObj.getBarcodeStatus())){
	        				//List<BigInteger> accountIds = adminManager.getAll("SELECT a.id,a.barcode FROM Account a JOIN Role r WHERE  a.custId="+custObj.getId()+" and r.id in ('3') and a.accountExpired = 'N' and a.barcode is null");
	        				List<BigInteger> accountIds = adminManager.getAll("select accountId from vw_allUsers where custId="+custObj.getId()+" and roleId in (2,3,8) and accountExpired = 'N' and barcode is null");
	    					String accountIdString=null;
	    					if (ObjectFunctions.isNotNullOrEmpty(accountIds)) 
	    						accountIdString = StringFunctions.convertListToCommaDelimitedString(accountIds);
	    					else
	    						accountIdString="0";
	    					userList = adminManager.getAll(User.class, "id in("+accountIdString+")");
	    					if(!ObjectFunctions.isNullOrEmpty(userList)){
	    						for(User userObj:userList){
	    							if(!ObjectFunctions.isNullOrEmpty(userObj)){	
	    								//user= (User)adminManager.get(User.class,userObj.getId());
	    								//log.debug("userId="+user.getId());
	    								//generateBarcodeForStudent(user.getId());
	    								adminManager.generateBarcode(userObj.getId());
	    							}
	    						}
	    					}
	        			}else{
	        				log.debug("Customer Short Name Not Available..." + custObj.getCustomerShortName());
	        			}
	        		}custObj=null;
	        	}
				super.addActionMessage("Barcodes generated successfully.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxScoreCardDefaultTemplates", results = { @Result(location = "ajaxScoreCardDefaultTemplateDetails.jsp", name = "success") }) })
	public String ajaxScoreCardDefaultTemplates() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxScoreCardDefaultTemplates' method");
		}
		try {
			setObjectList(adminManager.getDefaultTemplatesList());
		} catch (Exception e) {
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxDoCreateDefaultTemplate", results = { @Result(location = "ajaxCreateScoreCardDefaultTemplate.jsp", name = "success") }) })
	public String ajaxDoCreateDefaultTemplate() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoCreateDefaultTemplate' method");
		}
		try {
			if(getDefaultScoreCardTemplatesVO().getId()>0)
				setDefaultScoreCardTemplatesVO(adminManager.getDefaultScorecardTemplate(getDefaultScoreCardTemplatesVO().getId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxCreateDefaultScoreCardTemplates", results = { @Result(location = "ajaxScoreCardDefaultTemplateDetails.jsp", name = "success") }) })
	public String ajaxCreateDefaultScoreCardTemplates() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateDefaultScoreCardTemplates' method");
		}
		try {
			DefaultScoreCardTemplates defaultScoreCardTemplates =null;
			if(!ObjectFunctions.isNullOrEmpty(getDefaultScoreCardTemplatesVO())){
				defaultScoreCardTemplates =(DefaultScoreCardTemplates)adminManager.get(DefaultScoreCardTemplates.class, getDefaultScoreCardTemplatesVO().getId());
				defaultScoreCardTemplates.setLastUpdatedDate(new Date());
			}
			if(ObjectFunctions.isNullOrEmpty(defaultScoreCardTemplates)){
				defaultScoreCardTemplates=new DefaultScoreCardTemplates();
				defaultScoreCardTemplates.setCreatedDate(new Date());
			}
			defaultScoreCardTemplates.setLastUpdatedById(getUser().getId());
			defaultScoreCardTemplates.setLastUpdatedDate(new Date());
			defaultScoreCardTemplates.setCustId(getUserCustId());
			defaultScoreCardTemplates.setScoreCardName(getDefaultScoreCardTemplatesVO().getScoreCardName());
			defaultScoreCardTemplates.setNoOfExamTypes(getDefaultScoreCardTemplatesVO().getNoOfExamTypes());
			defaultScoreCardTemplates.setNoOfSubTypes(getDefaultScoreCardTemplatesVO().getNoOfSubTypes());
			//defaultScoreCardTemplates.
			StringBuffer pathName = null;
			MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
			Enumeration<String> fileParaNames = multiWrapper.getFileParameterNames();
				while (fileParaNames.hasMoreElements()) 
				{
					String key = fileParaNames.nextElement();
					File[] fileObject = multiWrapper.getFiles(key);
					String[] localSysfileNames = multiWrapper.getFileNames(key);
					setUploadFileName(StringFunctions.stripSymbols(localSysfileNames[0]));
					pathName = new StringBuffer("userFiles/ScoreCardTemplates/").append("default");
					
					if(getUploadFileName().contains(".png") || getUploadFileName().contains(".jpg")){
						pathName.append("/").append("image").append("/");
						defaultScoreCardTemplates.setImageFileName(getUploadFileName());
						defaultScoreCardTemplates.setImageFilePath(pathName.toString());
					}
					else if(getUploadFileName().contains(".xls") || getUploadFileName().contains(".xlsx")){
						pathName.append("/").append("excel").append("/");
						defaultScoreCardTemplates.setExcelFileName(getUploadFileName());
						defaultScoreCardTemplates.setExcelFilePath(pathName.toString());
					}
					pathName.append(getUploadFileName());
					File destDir = new File(getSession().getServletContext().getRealPath(pathName.toString()));
					FileUtils.copyFile(fileObject[0], destDir);
					
					key = null;
					fileObject = null;
					localSysfileNames = null;
					destDir = null;
					pathName = null;
				}
				adminManager.save(defaultScoreCardTemplates);
				super.addActionMessage("Default template uploaded suceesfully.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		ajaxScoreCardDefaultTemplates();
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoSendSMSNewFeatures", results = { @Result(location ="ajaxSendNewSmsFeatures.jsp", name = "success") }) })
	public String ajaxDoSendSMSNewFeatures() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSendSMSNewFeatures' method");
		}
		try {
			setObjectList(adminManager.getAll(SendNotifications.class));
		} catch (Exception e) {
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxEditNotifications", results = { @Result(location ="ajaxEditSendNewSmsFeatures.jsp", name = "success") }) })
	public String ajaxEditNotifications() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditNotifications' method");
		}
		try {
			setSendNotifications((SendNotifications)adminManager.get(SendNotifications.class,getTempId2()));
		} catch (Exception e) {
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSendSMSNewFeaturesToAllCustomers", results = { @Result(location ="ajaxSendNewSmsFeatures.jsp", name = "success") }) })
	public String ajaxSendSMSNewFeatures() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendSMSNewFeatures' method");
		}
		try {
			SendNotifications snObj=null;
			if(getTempId()>0){
				snObj=(SendNotifications)adminManager.get(SendNotifications.class, getTempId());
				snObj.setLastUpdatedDate(new Date());
				super.addActionMessage("New features updated successfully.");
			}else{
				snObj=new SendNotifications();
				snObj.setCreatedDate(new Date());
				super.addActionMessage("New features sended successfully.");
			}
			snObj.setDescription(getSendNotifications().getDescription());
			snObj.setCreatedById(getUser().getId());
			adminManager.save(snObj);
			setObjectList(adminManager.getAll(SendNotifications.class));
		} catch (Exception e) {
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDeleteNotification", results = { @Result(location ="ajaxSendNewSmsFeatures.jsp", name = "success") }) })
	public String ajaxDeleteNotification() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteNotification' method");
		}
		try {
			adminManager.remove(SendNotifications.class, getTempId1());
			setObjectList(adminManager.getAll(SendNotifications.class));
			super.addActionMessage("message deleted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
		return SUCCESS;
	}

	public void customerActivateAndInactivateSMSAndEmailCommunication()throws URTUniversalException {
		try {
			Customer customer = (Customer) adminManager.get(Customer.class,getTempId());
			if (!ObjectFunctions.isNullOrEmpty(customer)) {
				Set<String> mobileNumbersSet = new HashSet<String>();
				StringBuffer smsContent;
				if (!ObjectFunctions.isNullOrEmpty(customer.getMobileNumber())) {
					SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
					mobileNumbersSet.add(customer.getMobileNumber());
					User loggedUser = (User) adminManager.get(User.class,getUser().getId());
					if (!ObjectFunctions.isNullOrEmpty(loggedUser)) {
						Messages objMsg = new Messages();
						objMsg.setCreatedById(loggedUser.getId());
						objMsg.setCreatedDate(new Date());
						objMsg.setLastAccessDate(new Date());
						objMsg.setLastUpdatedDate(new Date());
						objMsg.setStatus("M");
						objMsg.setSenderName(loggedUser.getUserRoleDescription());
						smsContent = new StringBuffer();
						if (getPlTitle().equalsIgnoreCase("A")) { // A for active(enableCustomer) the customers
							smsContent.append("Dear "+ customer.getCustomerFullPersonName()+ ",");
							smsContent.append("This is to confirm that your eazyschool account is Activated. Please call 080-46620999 if you have any questions.");
						} else if (getPlTitle().equalsIgnoreCase("D")) {
							smsContent.append("Dear "+ customer.getCustomerFullPersonName()+ ",");
							smsContent.append("This is to confirm that your eazyschool account is deactivated. Please call 080-46620999 if you have any questions.");
						}
						smsContent.append(" Thank you from ");
						objMsg.setMessageDescription(smsContent.toString());
						objMsg.setCustomer(customer);
						objMsg.setSmsSenderId(customer.getSender());
						if (!ObjectFunctions.isNullOrEmpty(mobileNumbersSet)) {
							log.debug("Before Calling Deviler SMS.....mobileNumberset---"+ mobileNumbersSet+ "-------messageDesc-----Your Eazyschool account status is in Inactive");
							objMsg = adminManager.saveMessageDetailsTracking(objMsg, null, null, loggedUser);
							adminManager.deliverSms(objMsg, mobileNumbersSet,smsServiceProvider);
							// super.addActionMessage("SMS(s) has been delivered successfully.");
						}
						mobileNumbersSet = null;
						objMsg = null;
						smsContent = null;
					}
				}

				DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
				Date date = new Date();
				log.debug(dateFormat.format(date));
				List<Object> emailsList = new ArrayList<Object>();
				List<Object> supportEmails = adminManager.getAll("select email from supportTeam");
				if (!ObjectFunctions.isNullOrEmpty(customer.getCustEmail()) && !ObjectFunctions.isNullOrEmpty(supportEmails)) {
					emailsList.addAll(supportEmails);
					emailsList.add("support@eazyschool.com");
					if (!ObjectFunctions.isNullOrEmpty(customer.getCustEmail()) && ObjectFunctions.isNullOrEmpty(supportEmails))
						emailsList.add(customer.getCustEmail());
					if (ObjectFunctions.isNullOrEmpty(customer.getCustEmail()) && !ObjectFunctions.isNullOrEmpty(supportEmails))
						emailsList = new ArrayList(supportEmails);
					StringBuffer br = new StringBuffer();
					if (getPlTitle().equalsIgnoreCase("A")) { // A for Active(enableCustomer) the customers
						String[] emailAddresses = new String[1];
						emailAddresses[0] = customer.getCustEmail();
						br = prepareCustomerActivationEmail(br,dateFormat.format(date),customer.getOrganization());
						MailUtil mailUtil = new MailUtil(emailAddresses,"Confirmation on your EazySchool Account Activation",br.toString(), getUser(), null,"messages@eazyschool.com");
						mailUtil.sendEmailToSupportingTeam("messages@eazyschool.com");
						mailUtil = null;
						br = null;
						br = new StringBuffer();
						br = prepareCusotmerActivationEmailForInternalTeam(br,dateFormat.format(date), customer);
					} else if (getPlTitle().equalsIgnoreCase("D")) {
						emailsList.add(customer.getCustEmail());
						br = prepareCustomerDeactivationEmail(br,dateFormat.format(date),customer.getOrganization());
					}
					if (!ObjectFunctions.isNullOrEmpty(emailsList)) {
						List emailsIdsList = new ArrayList(new HashSet(emailsList));
						for (Object staffEmailIds : emailsIdsList) {
							String[] emailAddresses = new String[1];
							emailAddresses[0] = staffEmailIds.toString();
							if (getPlTitle().equalsIgnoreCase("A")) { // A for Active the customers
								MailUtil mailUtil = new MailUtil(emailAddresses,"Confirmation "+customer.getOrganization()+" EazySchool Account Activation",br.toString(), getUser(), null,"messages@eazyschool.com");
								mailUtil.sendEmailToSupportingTeam("messages@eazyschool.com");
								mailUtil = null;
							} else if (getPlTitle().equalsIgnoreCase("D")) {
								MailUtil mailUtil1 = new MailUtil(emailAddresses,"Confirmation "+customer.getOrganization()+" EazySchool Account Deactivation",br.toString(), getUser(), null,"messages@eazyschool.com");
								mailUtil1.sendEmailToSupportingTeam("messages@eazyschool.com");
								mailUtil1 = null;
							}
						}
						br = null;
					}
				}
				adminManager.save(customer);
				customer = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	public StringBuffer prepareCustomerActivationEmail(StringBuffer sb,String date,String Organization)throws URTUniversalException{
		try {
			sb.append("Dear "+Organization+",");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			sb.append("This email is to confirm that your EazySchool account is activated on "+date+". From now onwards you can access your EazySchool account.");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			//br.append("Thanks for choosing eazyschool application again.");
			//br.append( "<br>" );br.append( " " );br.append( "<br>" );
			sb.append("Please feel free to call our supporting team in case if you need any support.");
			sb.append( "<br>" );sb.append( " " );
			sb.append("Email : support@eazyschool.com");
			sb.append( "<br>" );
			sb.append("Phone : 080-46620999.");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			sb.append("**NOTE: This is an automated email from EazySchool Software. Please DO NOT REPLY to this email.");
			return sb;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	@Actions( { @Action(value = "ajaxCreateCustomerRecordToStaffTable", results = { @Result(location = "ajaxMasterAdmin.jsp", name = "success") }) })
	public String ajaxCreateCustomerRecordToStaffTable() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateCustomerRecordToStaffTable' method");
		}
		try {
			Staff staff = null;
			User user = null;
			AcademicYear year =null;
			List<Customer> customerList=adminManager.getAll(Customer.class,"status='Y' and id !=1 order by id");
				if(!ObjectFunctions.isNullOrEmpty(customerList)){
					for(Customer obj:customerList){
						year = (AcademicYear) adminManager.get(AcademicYear.class,"custId="+obj.getId()+" order by academicYear,custId LIMIT 1");
						user = (User) adminManager.get(User.class,"custId="+obj.getId()+" order by id LIMIT 1");
						if(!ObjectFunctions.isNullOrEmpty(year)){
							 if(!ObjectFunctions.isNullOrEmpty(user)){
								staff = new Staff();
								staff.setCreatedById(obj.getId());
								staff.setCustId(obj.getId());
				 			    staff.setAccount(user);
				 			    staff.setStatus(Constants.YES_STRING);
				 			    staff.setAcademicYear(year);
				 			    staff = (Staff) adminManager.save(staff);
							 }
						}
						year=null;
						user=null;
						staff=null;
					}
				}
				customerDetails();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSMSToALLCustomersLoginDetails", results = { @Result(location = "ajaxSendSmsToAllCustomersloginDetails.jsp", name = "success") }) })
	public String ajaxSMSToALLCustomersLoginDetails() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSMSToALLCustomersLoginDetails' method");
		}
		try {
    		//if(getUserCustId()>0) 
    			//setObjectList(adminManager.getAll(Customer.class));
    		//List<ViewUserRoles> userRolesList = adminManager.getAll(ViewUserRoles.class,"custId=")
		} catch (Exception e) {
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
		}
		return SUCCESS;
	}
	@Actions({ @Action(value = "ajaxSearchSchoolMembersDetails", results = { @Result(type = "json", name = "success") }) })
	public String ajaxSearchSchoolMembersDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchSchoolMembersDetails' method");
		}
		try {
			List<Customer> customerList = null;
			String searchword = getParamValue("searchword");

			if (!StringFunctions.isNullOrEmpty(searchword)) {
				customerList = adminManager.getAll(Customer.class,"organization like '%"+ searchword+ "%'");
				JSONArray res = new JSONArray();
				JSONObject j;
				j = new JSONObject();
				if (!ObjectFunctions.isNullOrEmpty(customerList)) {
					for (Customer customerObj : customerList) {
						j = new JSONObject();
						j.put("custId", customerObj.getId());
						j.put("title",customerObj.getOrganization());
						res.put(j);
					}
				} else {
					j = new JSONObject();
					j.put("custId", 0);
					j.put("title", "No Results Found !!");
					res.put(j);
				}
				j = new JSONObject();
				j.put("movies", res);
				getResponse().getOutputStream().print(j.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	
	@Actions({
		@Action(value = "ajaxDoViewSchoolPersonDetails", results = { @Result(location = "ajaxViewSchoolMemberDetails.jsp", name = "success") })
	})
	public String ajaxDoViewSchoolPersonDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewSchoolPersonDetails' method");
		}
		try {
			//log.debug(getParamValue("title"));
			log.info(getParamValue("custId"));
			setEventId(getParamValue("custId"));
			setTempList(adminManager.getAll(Role.class));
			/*List<ViewUserRoles> alluserRoles = adminManager.getAll(ViewUserRoles.class,"custId="+getEventId()+" group by roleId");
			if (!ObjectFunctions.isNullOrEmpty(alluserRoles)) {
				setTempList(alluserRoles);
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxGetSchoolRoleDetailsByRoleId", results = { @Result(location = "viewSchoolPersonsDetails.jsp", name = "success") })
	})
	public String ajaxGetSchoolRoleDetailsByRoleId() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSchoolRoleDetailsByRoleId' method");
		}
		try {
			//setCustomer(getCustomerByCustId());
			//getSmsCount();
			List<ViewAllUsers> allusers = null;
			if(!ObjectFunctions.isNullOrEmpty(getPlTitle())){
				 allusers = adminManager.getAll(ViewAllUsers.class,"custId="+getTempId()+" and roleId="+getTempId1()+" and (mobileNumber='"+"+91-"+getPlTitle()+"' or studentMobile='"+"+91-"+getPlTitle()+"' or parentEmail='"+getPlTitle()+"' or studentEmail='"+getPlTitle()+"' or staffEmail='"+getPlTitle()+"')");
			}/*else if(!ObjectFunctions.isNullOrEmpty(getPlTitle())){
				 allusers = adminManager.getAll(ViewAllUsers.class,"custId="+getTempId()+" and roleId="+getTempId1()+" and (mobileNumber='"+"+91-"+getPlTitle()+"' or studentMobile='"+"+91-"+getPlTitle()+"')");
			}else{
				 allusers = adminManager.getAll(ViewAllUsers.class,"custId="+getTempId()+" and roleId="+getTempId1()+" and (parentEmail='"+getPlSubTopic()+"' or studentEmail='"+getPlSubTopic()+"' or staffEmail='"+getPlSubTopic()+"')");
			}*/
			if (!ObjectFunctions.isNullOrEmpty(allusers)) {
				setObjectList(allusers);
				log.debug(allusers.size());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "ajaxSendLoginCredetialsfromSupport", results = { @Result(location = "viewSchoolPersonsDetails.jsp", name = "success") })
	})
	public String ajaxSendLoginCredetialsfromSupport() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSendLoginCredetialsfromSupport' method");
		}
		try {
			log.debug(getAnyTitle());
			User luser = (User)adminManager.get(User.class,"id="+getAnyTitle());
			if(!ObjectFunctions.isNullOrEmpty(luser)){
				Customer customer=(Customer) adminManager.get(Customer.class, luser.getCustId());
				if(!ObjectFunctions.isNullOrEmpty(customer)){
					SMSServiceProviders smsServiceProvider = (SMSServiceProviders)adminManager.getSMSServiceProviderByCustId(customer.getSmsServiceProviderId());
					AcademicYear academicYear=(AcademicYear) adminManager.getCurrentAcademicYear(Constants.YES_STRING,customer.getId());
					if(!ObjectFunctions.isNullOrEmpty(academicYear)){
						if(!ObjectFunctions.isNullOrEmpty(luser.getPerson().getStudentMobile()) && !StringFunctions.isNullOrEmpty(luser.getPerson().getStudentEmail()) || !ObjectFunctions.isNullOrEmpty(luser.getPerson().getParentEmail()) 
								|| !ObjectFunctions.isNullOrEmpty(luser.getPrimaryAddress()) && !StringFunctions.isNullOrEmpty(luser.getPrimaryAddress().getEmail()) || 
								!ObjectFunctions.isNullOrEmpty(luser.getPerson()) && !StringFunctions.isNullOrEmpty(luser.getPerson().getMobileNumber())){
							String newPassword = StringUtil.generateRandomString();
							/*this method move the code signupAction to UniversalManagerImpl.java */
							luser.setPassword(PasswordUtils.passwordEncoder(newPassword, null));
							luser.setConfirmPassword(newPassword);
							adminManager.save(luser);
							if("S".equalsIgnoreCase(getTempString()) && customer.getAllowedTotalSms() != 0){
								Messages messages = new Messages();
								StringBuffer msgContent = new StringBuffer();
								if(!ObjectFunctions.isNullOrEmpty(luser.getPerson().getFullPersonName()) && ObjectFunctions.isNullOrEmpty(getAnyId())){
									msgContent.append("Dear "+luser.getPerson().getFirstName()+", ");
								}else if(luser.isParent() && ObjectFunctions.isNullOrEmpty(luser.getPerson().getFullPersonName())){
									msgContent.append("Dear Parent,");
								}
								msgContent.append("Here is your eazyschool account login details.Login Id:");
								msgContent.append(luser.getUsername());
								//msgContent.append(newPassword);
								msgContent.append(" Password:");
								msgContent.append(newPassword);
								msgContent.append(" Thank you from ");
								messages.setCustomer(customer);
								messages.setSenderName(customer.getSender());
								messages.setMessageDescription(msgContent.toString());
								log.debug(msgContent.toString());
								messages.setStatus("FPWD");
								messages.setSenderName("Master Admin");
								messages.setAcademicYear(academicYear);
								messages.setPurposeType("Regd: ForGot Password");
								Set<String> mobileNumber=new HashSet<String>();
								if(luser.isSchoolStudent()){
									mobileNumber.add(getAnyId());
									Student student = (Student) adminManager.get(Student.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+ luser.getId());
									messages = adminManager.saveMessageDetailsTracking(messages,student,null,null);	
								}else if(luser.isParent()){
									mobileNumber.add(getAnyId());
									User account = (User) adminManager.get(User.class, "custId="+customer.getId()+" and id="+ luser.getId());
									messages = adminManager.saveMessageDetailsTracking(messages,null,null,account);	
								}else{
									mobileNumber.add(getAnyId());
									Staff staff = (Staff) adminManager.get(Staff.class, "custId="+customer.getId()+" and academicYearId="+academicYear.getId()+" and accountId="+ luser.getId());
									messages = adminManager.saveMessageDetailsTracking(messages,null,staff,null);	
								}
								if(adminManager.deliverSms(messages,mobileNumber,smsServiceProvider))
									super.addActionMessage("Credentials sent successfully.");
								else
									super.addActionMessage("Credentials not sent.");
							} else if("E".equalsIgnoreCase(getTempString())){

								String[] emailAddresses = new String[1];
								emailAddresses[0] = getAnyId();
								MailUtil mailUtil = new MailUtil(emailAddresses,"Forgot Password.",customer.getId(),customer.getSender(),luser.getUserRoleDescription(),this.getContactFromEmail(customer));
								mailUtil.setContactEmail(customer.getContactEmail());
								mailUtil.setContactPasword(customer.getContactPassword());
								mailUtil.sendEMailForPwdAndUserNameChange(luser.getConfirmPassword(),luser.getUsername(),this.getContactFromEmail(customer),luser.getPerson().getFullPersonName());
								mailUtil=null;
								emailAddresses=null;
								super.addActionMessage("Credentials sent successfully.");
						
							} else{
								super.addActionError("Credentials not sent because sms not available in the requested customer account.");
							}
				       }
					}
				}	
			}
			ajaxGetSchoolRoleDetailsByRoleId();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	
	
	@Actions({
		@Action(value = "ajaxViewNewCustomersDetails", results = { @Result(location = "ajaxManageNewCustomersDetails.jsp", name = "success") })
	})
	public String ajaxViewNewCustomersDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewNewCustomersDetails' method");
		}
		try {
			setObjectList(adminManager.getAll(InviteCustomerEnrollment.class));
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "ajaxDoAddNewCustomersDetails", results = { @Result(location = "ajaxAddNewCustomersDetails.jsp", name = "success") })
	})
	public String ajaxDoNewCustomersDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoNewCustomersDetails' method");
		}
		try {
			setPackageDetailsList(adminManager.getAll(PackageDetails.class));
			if(!ObjectFunctions.isNullOrEmpty(getInviteCustomerEnrollment()))
				setInviteCustomerEnrollment((InviteCustomerEnrollment) adminManager.get(InviteCustomerEnrollment.class, "id=" + getInviteCustomerEnrollment().getId()));
			else
				setInviteCustomerEnrollment(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "ajaxAddNewCustomersDetails", results = { @Result(location = "ajaxViewNewCustomersDetails.jsp", name = "success") })
	})
	public String ajaxAddNewCustomersDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoNewCustomersDetails' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getInviteCustomerEnrollment()))
			{
				InviteCustomerEnrollment inviteCustomerEnrollment = new InviteCustomerEnrollment();
				if(getInviteCustomerEnrollment().getId() > 0)
					inviteCustomerEnrollment = (InviteCustomerEnrollment) adminManager.get(InviteCustomerEnrollment.class, "id=" + getInviteCustomerEnrollment().getId());
				
				PackageDetails packageDetails = (PackageDetails) adminManager.get(PackageDetails.class, "id=" + getInviteCustomerEnrollment().getPackageDetails().getId());
				
				inviteCustomerEnrollment.setAccountType(getInviteCustomerEnrollment().getAccountType());
				inviteCustomerEnrollment.setSchoolName(getInviteCustomerEnrollment().getSchoolName()); 
				//inviteCustomerEnrollment.setTotalStudents(getInviteCustomerEnrollment().getTotalStudents());
				inviteCustomerEnrollment.setEmail(getInviteCustomerEnrollment().getEmail());
				inviteCustomerEnrollment.setTrailEndDate(getInviteCustomerEnrollment().getTrailEndDate());
				inviteCustomerEnrollment.setTrailStartDate(getInviteCustomerEnrollment().getTrailStartDate());
				inviteCustomerEnrollment.setStatus(Constants.PENDING_STATUS);
				inviteCustomerEnrollment.setPackageDetails(packageDetails);
				inviteCustomerEnrollment = (InviteCustomerEnrollment) adminManager.save(inviteCustomerEnrollment);
				
				
				String[] emailAddresses = new String[1];
				emailAddresses[0] = getInviteCustomerEnrollment().getEmail();
				
				MailUtil mailUtil = new MailUtil(emailAddresses,"Customer Enrollment Invitation",inviteCustomerEnrollment.getSchoolName(),inviteCustomerEnrollment.getSchoolName());
				//mailUtil.setContactEmail(customer.getContactEmail());
				//mailUtil.setContactPasword(customer.getContactPassword());
				mailUtil.sendEMailForInviteCustomerEnrollment(inviteCustomerEnrollment.getSchoolName(),String.valueOf(packageDetails.getStudentsRange()),inviteCustomerEnrollment.getEmail(),inviteCustomerEnrollment.getAccountType(),inviteCustomerEnrollment.getId());
				mailUtil=null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		ajaxViewNewCustomersDetails();
		return SUCCESS;
	}
	
	@Actions({
		@Action(value = "ajaxManageCustomerRegistrationDetails", results = { @Result(location = "ajaxManageCustomerRegistrationDetails.jsp", name = "success") }),		
		@Action(value = "ajaxViewAllCustomerRegistrationDetails", results = { @Result(location = "ajaxViewCustomerRegistrationDetails.jsp", name = "success") })
		
	})
	public String ajaxManageCustomerRegistrationDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageCustomerRegistrationDetails' method");
		}
		try {
			
			if(!StringFunctions.isNullOrEmpty(getTempString2()))
				setObjectList(adminManager.getAll(CustomerEnrollmentRequest.class, "status='"+Constants.ACTIVE_STATUS+"' OR status='"+Constants.REJECTED_STATUS+"'"));
			else
				setObjectList(adminManager.getAll(CustomerEnrollmentRequest.class, "status='"+Constants.PENDING_STATUS+"'"));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	}
	@Actions( { 
	    @Action(value = "ajaxCheckEmailId", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
	public String ajaxCheckEmailId() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxCheckEmailId' method");
	}
	try {
	    String custEmail = getParamValue("keyWord");
	    if (StringFunctions.isNotNullOrEmpty(custEmail)) {
		List usersList = adminManager.checkPersonId(custEmail);
		if (ObjectFunctions.isNullOrEmpty(usersList)) {
			setAutoCheck("0");
		} else if (usersList.size() > 0) {
			setAutoCheck("1");
		} else {
			setAutoCheck("0");
		}
	    }
	} catch (Exception ex) {
		log.error("Entering into 'catch block':" + ex.getMessage());
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
}
	
	public StringBuffer prepareCustomerDeactivationEmail(StringBuffer sb,String date,String Organization)throws URTUniversalException {
		try {
			sb.append("Dear "+Organization+",");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			sb.append("This email is to confirm that your EazySchool account is deactivated on "+date+". From now onwards your are no longer accessible to your EazySchool account.");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			sb.append("For activation of your account please contact EazySchool supporting Team.");
			sb.append( "<br>" );sb.append( " " );
			sb.append("Email : support@eazyschool.com");
			sb.append( "<br>" );
			sb.append("Phone : 080-46620999.");
			sb.append( "<br>" );sb.append( " " );sb.append( "<br>" );
			sb.append("**NOTE: This is an automated email from EazySchool Software. Please DO NOT REPLY to this email.");
			return sb;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
	@Actions({ @Action(value = "ajaxGetDemoCustomerDetails", results = { @Result(location = "ajaxViewDemoCustomerList.jsp", name = "success") }) })
	public String ajaGetDemoCustomerDetails() {
		if (log.isInfoEnabled()) log.info("Entering 'ajaGetDemoCustomerDetails' method");
		try {
			List<Customer> allActiveCustomersList = adminManager.getAll(Customer.class,"accountType='D' or accountType='M' and status='Y' and organizationSubTypeId = 0 order by createdDate desc"); //All active demo customers
			if(!ObjectFunctions.isNullOrEmpty(allActiveCustomersList)){
				setCustomersList(allActiveCustomersList);
			}
			List<Customer> allInActiveCustomersList = adminManager.getAll(Customer.class,"accountType='D' and status='"+Constants.NO_STRING+"' and customerInActiveDescription is not null order by createdDate desc");
			setTempId1(allInActiveCustomersList.size()); // All InActive Demo Customers Count
			setTempId2(allActiveCustomersList.size()-allInActiveCustomersList.size()); //All Active Demo Customers Count
			allActiveCustomersList = null;
			allInActiveCustomersList=null;
			setTemplateList(adminManager.getAll(StudyAndBonafiedSettings.class));
			if(ObjectFunctions.isNullOrEmpty(getTemplateList())){
				List<StudyAndBonafiedSettings> templateList = getTemplateList();
				for(StudyAndBonafiedSettings template : templateList){
					if(!ObjectFunctions.isNullOrEmpty(template.getCustId())){
						setTemplateList(adminManager.getAll(StudyAndBonafiedSettings.class, "custId="+template.getId()+" and bonafiedFileName ="+getFileName()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}