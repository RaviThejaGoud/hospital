package com.urt.webapp.action.subscription;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.SendNotifications;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.LoginAccessbilityRoles;
import com.urt.persistence.model.customer.Organization;
import com.urt.persistence.model.secretary.FinancialYear;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.subscription.SubscriptionManager;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;



/**
 * Action for facilitating Resources Management feature.
 */
@Namespace("/subscription")
public class SubscriptionAction extends BaseAction {
	
	private static final long serialVersionUID = -1646390427462403153L;
	protected SubscriptionManager subscriptionManager;
	protected User regUser;
	protected Long selectedStudentId;
	   

	public Long getSelectedStudentId() {
		return selectedStudentId;
	}

	public void setSelectedStudentId(Long selectedStudentId) {
		this.selectedStudentId = selectedStudentId;
	}

	/**
	 * @return the subscriptionManager
	 */
	@JSON(serialize = false)
	public SubscriptionManager getSubscriptionManager() {
		return subscriptionManager;
	}

	/**
	 * @param subscriptionManager the subscriptionManager to set
	 */
	public void setSubscriptionManager(SubscriptionManager subscriptionManager) {
		this.subscriptionManager = subscriptionManager;
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
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * April 16, 2013    Prasad		        For using of Academic Year through out application
 * May 9, 2013		 Seshu				Removed father name from session and provided common code for student and parent. 
/********************************************************************/	
	@Actions({
		@Action(value = "userAccess", results = { @Result(location = "login.jsp", name = "subscriptionHome"), @Result(location = "../signup/forgotPassword.jsp", name = "multipleUsers") })})
	public String userAccess() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'userAccess' method");
		}
		try{
			saveUserLoginMetaData(); // To save the user login details *** Ramarao Jadapolu
			getSession().setAttribute("loggedIn", "user");
			if(getUser().isSecretary() || getUser().isSecretaryPA() || getUser().isChairMan())
				getSession().setAttribute("customerStatus", "Y"); // its used for Secretary,isChairMan
			if (getUser().isOnlySchoolAdmin()) {
				Set<Customer> customers=getUser().getBranches();
				if(ObjectFunctions.isNotNullOrEmpty(customers)){
					if(customers.size() > 1){
						List<Customer> customersList=ConvertUtil.convertSetToList(customers);
						getSession().setAttribute("branches",customersList);
						if(StringFunctions.isNullOrEmpty(getParamValue("branch"))){
							getSession().setAttribute("branch", ""+getUserCustId());
						}
						else{
							getSession().setAttribute("branch", getParamValue("branch"));
						}
					}
				}
			}
			if(getUser().isSchoolDEO() || getUser().isSchoolCEO() || getUser().isSchoolSEO() || getUser().isSchoolBEO())
			{
				getResponse().sendRedirect(getRequest().getContextPath()+"/govt/govtReports.do");
			}
			if(getUser().isSecretary() || getUser().isSecretaryPA())
			{
				getSession().removeAttribute("OrgcustomersList");
				getSession().removeAttribute("SessionOrganizationId");
				getSession().removeAttribute("SessionCurrentFinancialYearId");
				Object[] organizationMembersObj=(Object[]) adminManager.get("select orgId, accountId from organizationMembers  where accountId=" + getUser().getId() + " and status ='"+Constants.YES_STRING+"'");
				if(!ObjectFunctions.isNullOrEmpty(organizationMembersObj))
				{
					if(!ObjectFunctions.isNullOrEmpty(organizationMembersObj[0]))
					{
						Organization organization= (Organization)adminManager.get(Organization.class,  "id=" +organizationMembersObj[0]);
						getSession().setAttribute("schoolName",organization.getOrganizationName());
						List<Customer> customersList = adminManager.getAll(Customer.class, "orgId="+Long.valueOf(organizationMembersObj[0].toString()));
						getSession().setAttribute("OrgcustomersList",customersList);
						customersList = null;
						FinancialYear financialYear = (FinancialYear)adminManager.get(FinancialYear.class," orgId="+Long.valueOf(organizationMembersObj[0].toString()) + " and status='"+Constants.YES_STRING+"'");
						if(!ObjectFunctions.isNullOrEmpty(financialYear))
						{
							getSession().setAttribute("SessionCurrentFinancialYearId",financialYear.getId());
							financialYear = null;
						}
					}
					getSession().setAttribute("SessionOrganizationId",organizationMembersObj[0].toString());
				}
				getResponse().sendRedirect(getRequest().getContextPath()+"/admin/secretaryDashboard.do");
			}
			if(getUser().isChairMan()){
				getResponse().sendRedirect(getRequest().getContextPath()+"/staff/chairManDashboard.do");	
			}
			if(getUser().isMessManager()){
				getResponse().sendRedirect(getRequest().getContextPath()+"/staff/messManagerDashboard.do");	
			}
			Customer customer = null;
				if(!ObjectFunctions.isNullOrEmpty(getUser()))
				{
					//Removing customer session before logging.
					getSession().removeAttribute("userCustomer");
					getSession().removeAttribute("customer2");
					getSession().removeAttribute("customer");
					if(getUser().isParent())
					{
							List<ViewAllUsers> vwusersList = subscriptionManager.getAllHqlQuery("from ViewAllUsers where roleId = 3 and parentId="+ getUser().getId()+" and accountEnabled='"+Constants.YES_STRING+"' and accountExpired='"+Constants.NO_STRING+"'");
							if (!ObjectFunctions.isNullOrEmpty(vwusersList)) {
								if(vwusersList.size() > 1)
								{
									getSession().setAttribute("vwusersList", vwusersList);
									if(!ObjectFunctions.isNullOrEmpty(getSelectedStudentId())){
										getUser().setSelectedStudentId(getSelectedStudentId());
										getSession().setAttribute("selectedStudentId", getSelectedStudentId());
									}
									else{
										getUser().setSelectedStudentId(vwusersList.get(0).getAccountId());
										getSession().setAttribute("selectedStudentId", vwusersList.get(0).getAccountId());
									}
								}
								else{
									getUser().setSelectedStudentId(vwusersList.get(0).getAccountId());
									getSession().setAttribute("selectedStudentId", vwusersList.get(0).getAccountId());
								}
							}
							else {
								String url=getHostUrl()+"/j_spring_security_logout";
								getResponse().sendRedirect(getRequest().getContextPath()+"/signup/logoutsuccessForParent.do");
							}
							if(!ObjectFunctions.isNullOrEmpty(getUser().getSelectedStudentId())){
								Object cust[]=subscriptionManager.get("select id,custId from Account where id="+getUser().getSelectedStudentId());
								Customer customer2 = (Customer)subscriptionManager.get(Customer.class, Long.valueOf(cust[1].toString()));
								if(!ObjectFunctions.isNullOrEmpty(customer2))
									getSession().setAttribute("customer2", customer2);
							}
					}
					Customer customerObj = (Customer) adminManager.get(Customer.class, "id="+getUserCustId()+" and status='"+Constants.YES_STRING+"'");
					if(!ObjectFunctions.isNullOrEmpty(customerObj))
						getSession().setAttribute("customer",customerObj);
					if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("customer2"))){
						customer = (Customer)getSession().getAttribute("customer2");
					}
					else{
						customer=(Customer) adminManager.get(Customer.class, getUserCustId());
					}
					getSession().setAttribute("userCustomer",customer);
					if(!ObjectFunctions.isNullOrEmpty(customer) && customer.getStatus())
					{
						getSession().removeAttribute("GetAllStudyClasses");
						getSession().removeAttribute("GetMonthwiseWorkingDays");
						getSession().removeAttribute("GetStudyClassListGT0");
						getSession().removeAttribute("GetStudyClassListLT0");
						getSession().removeAttribute("GetAllClassNames");
						getSession().removeAttribute("GetStudyClassesByCustIdAndAcademicYearId");
						getSession().removeAttribute("GetAllHolidaysListByAcademicYearId");
						getSession().removeAttribute("customerEmailService");
						getSession().removeAttribute("customerMobileService");
						getSession().removeAttribute("newAcademicYear");
						getSession().removeAttribute("passwordStatus");
						getSession().removeAttribute("parentMobileNoVisibleToTeacher");
						
						getSession().setAttribute("customerTransportStauts", customer.isTransportModuleStatus());
						getSession().setAttribute("customerHostelMode", customer.isHostelModuleStatus());
						getSession().setAttribute("customerStatus", customer.getStatus()? "Y": "N");
						getSession().setAttribute("custName", customer.getCustomerName());
						getSession().setAttribute("organization", customer.getOrganization());
						//Added for EAZ-2756
						if(!ObjectFunctions.isNullOrEmpty(customer.getPreferences())){
							getSession().setAttribute("parentMobileNoVisibleToTeacher",customer.getPreferences().getParentMobileNoVisibleToTeacher());
						}else{
							getSession().setAttribute("parentMobileNoVisibleToTeacher","Y");
						}
						if(!ObjectFunctions.isNullOrEmpty(customer.getCustomerVision()))
							getSession().setAttribute("customerVision",customer.getCustomerVision());
						if(!ObjectFunctions.isNullOrEmpty(customer.getCustomerMission()))
							getSession().setAttribute("customerMission", customer.getCustomerMission());
					   if(!ObjectFunctions.isNullOrEmpty(customer.getOrganization()))
			            	   getSession().setAttribute("schoolName",customer.getOrganization());
					   if(!ObjectFunctions.isNullOrEmpty(customer.getOrganizationFullAddress()))
		            	   getSession().setAttribute("schoolAddress",customer.getOrganizationFullAddress());					   
			           if(!ObjectFunctions.isNullOrEmpty(customer.getCustomerLogo()))
			        	   getSession().setAttribute("custImage",customer.getCustomerLogo());
			           else
			        	   getSession().removeAttribute("custImage");
			           if(!ObjectFunctions.isNullOrEmpty(customer.isCheckEmailService()))
			        	   getSession().setAttribute("customerEmailService", customer.isCheckEmailService());
			           if(!ObjectFunctions.isNullOrEmpty(customer.isCheckMobileService()))
			        	   getSession().setAttribute("customerMobileService", customer.isCheckMobileService());
			           if(getUser().isSchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector()){
			        	   //this is used for master admin send new features release 
								List<SendNotifications> sendNotificationsList = adminManager.getAll(SendNotifications.class);
								if(!ObjectFunctions.isNullOrEmpty(sendNotificationsList)){
									SendNotifications sendNotifications=(SendNotifications)sendNotificationsList.get(0);
									getSession().setAttribute("newFeatures", sendNotifications.getDescription());
									sendNotifications=null;
								}else{
									getSession().setAttribute("newFeatures", "null");
								}
							}
			           if(!ObjectFunctions.isNullOrEmpty(getUser().isPasswordStatus()))
			        	   getSession().setAttribute("passwordStatus",getUser().isPasswordStatus());
					
					Set<Role> userRoles = getUser().getRoles();
					if(userRoles.size() >0){
						for(Role role : getUser().getRoles()){
							if(!ObjectFunctions.isNullOrEmpty(role)){
								LoginAccessbilityRoles accessrole = (LoginAccessbilityRoles)adminManager.get(LoginAccessbilityRoles.class,"customerId="+getUserCustId()+" and roleId="+role.getId()+" and status='"+Constants.YES_STRING+"' ");
								AcademicYear newAcademicYear = (AcademicYear)adminManager.get(AcademicYear.class,"custId="+customer.getId()+" and status='"+Constants.YES_STRING+"' ");
								if(!ObjectFunctions.isNullOrEmpty(accessrole) || "ROLE_SEO".equalsIgnoreCase(role.getName())){
									if(!ObjectFunctions.isNullOrEmpty(getUser().getPerson())) {
									   String firstName=getUser().getPerson().getFirstName();
									   if(("ROLE_STUDENT".equalsIgnoreCase(role.getName())) || ("ROLE_PARENT".equalsIgnoreCase(role.getName()))){
										   getSession().setAttribute("firstName", firstName);
									   }else{
										   getSession().setAttribute("firstName", firstName.concat("(").concat(role.getDescription()).concat(")"));
									   }
									   
									   
						             
						           }
									String year=null;
									AcademicYear academicYear  = adminManager.getCurrentAcademicYear("Y",customer.getId());
									if(!ObjectFunctions.isNullOrEmpty(academicYear)){
										getSession().setAttribute("currentAcademicYearId", academicYear.getId());
										getSession().setAttribute("enableSchoolShift", academicYear.getEnableSchoolShift());
										List<AcademicYear> academicYearList=null;
										if(academicYear.getId()!=0){
											academicYearList=adminManager.getPastAcademicYears(academicYear.getId(),customer.getId());
										}else{
											academicYearList=studentManager.getAllAcademicYearsBycustId(customer.getId());
										}
										if(ObjectFunctions.isNullOrEmpty(academicYearList)){
											getSession().removeAttribute("academicYearList");
										}
										else{
											getSession().setAttribute("academicYearList",academicYearList);
										}
										getSession().setAttribute("academicYear", String.valueOf(academicYear.getId()));
										getSession().setAttribute("academicYearName", String.valueOf(academicYear.getAcademicYear()));
										getSession().setAttribute("timetableGenerationByManual",academicYear.isTimetableGenerationByManual());
										if(!ObjectFunctions.isNullOrEmpty(getParamValue("academicYearId"))){
											getSession().setAttribute("academicYearName",getParamValue("academicYearName"));
											getSession().setAttribute("academicYear", getParamValue("academicYearId"));
											getSession().setAttribute("academicYearName",getParamValue("academicYearName"));
											getSession().setAttribute("timetableGenerationByManual",academicYear.isTimetableGenerationByManual());
											if (Long.parseLong(getParamValue("academicYearId")) == academicYear.getId())
											{
												getSession().setAttribute("previousYear","N"); 
											}
											else
											{
												getSession().setAttribute("previousYear","Y"); 
											}
											year= getParamValue("academicYearId");
										}else{
											if(!ObjectFunctions.isNullOrEmpty(academicYear)){
											 year=String.valueOf(academicYear.getId());
											 getSession().setAttribute("academicYear",year);
											 getSession().setAttribute("academicYearName",academicYear.getAcademicYear());
											 getSession().setAttribute("previousYear","N"); 
											 getSession().setAttribute("timetableGenerationByManual",academicYear.isTimetableGenerationByManual());
											 if(!ObjectFunctions.isNullOrEmpty(academicYear.getEndDate())){
												 if(academicYear.getEndDate().before(new Date()))
													 getSession().setAttribute("dateExceeded","Y");
												 else
													getSession().setAttribute("dateExceeded","N");
											 }
											 academicYear=null;
											}
										}
									}
									if(getUser().isOnlySchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector() || getUser().isSchoolTeacher() || getUser().isSchoolAsstStaff() || getUser().isSchoolTransport() || getUser().isTransportFinance() || getUser().isSchoolFinance() || getUser().isSchoolLibrarian() || getUser().isSchoolHostel() || getUser().isHostelFinance() || getUser().isSchoolClerk() || getUser().isSchoolManager()){
										getSession().setAttribute("showTaskReminder", "Y");
									}
									if(StringFunctions.isNotNullOrEmpty(getParamValue("requestURL"))){
										getResponse().sendRedirect(getParamValue("requestURL"));
										if(!ObjectFunctions.isNullOrEmpty(getParamValue("passwordHint"))){
											getSession().setAttribute("passwordHints", "P");
										}
									}else if (getUser().isOnlySchoolAdmin() || getUser().isSchoolPrincipal() || getUser().isSchoolDirector()) {
										getSession().setAttribute("showReminder","Y");
										if((newAcademicYear.getStartDate() == null && newAcademicYear.getEndDate() == null)){
											getSession().setAttribute("newAcademicYear", "C");
											getResponse().sendRedirect(getRequest().getContextPath()+"/admin/manageAcademics.do#target=ES.ajaxify AAP");
										}else{
											getExpiryDates();
											getResponse().sendRedirect(getRequest().getContextPath()+"/admin/manageAcademics.do");	
										}
										getSession().setAttribute("passwordHints", "");
									}	
									else if (getUser().isSchoolTeacher() || getUser().isSchoolAsstStaff()) {
										getSession().setAttribute("showReminder","Y");
										if(getUser().isSchoolAsstStaff()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/admin/asstStaffClassHome.do");										
										}else{
											getSession().setAttribute("isClassTeacher",adminManager.isUserAsClassTeacher(getUser().getId(),0,getUserAcademicYearId())); 
											getSession().setAttribute("showReminder","Y");
											getResponse().sendRedirect(getRequest().getContextPath()+"/staff/staffHome.do");
										}
									}else if(getUser().isSchoolStudent() || getUser().isParent()){
										getSession().removeAttribute("SessionIsOldStudent");
										if(getUser().isSchoolStudent()){
											if(getUserAcademicYearId() > 0){
												Student studentDetails = (Student)studentManager.get(Student.class,"accountId="+getUser().getId()+"  and description is null");//and academicYearId="+getUserAcademicYearId()+"
												if(!ObjectFunctions.isNullOrEmpty(studentDetails)){
													getUser().setSelectedStudentId(getUser().getId());
													getSession().setAttribute("studentClassSection", studentDetails.getClassAndSection());
													if(!ObjectFunctions.isNullOrEmpty(studentDetails.getProfileImage()))
													getSession().setAttribute("studentImg", studentDetails.getProfileImage().getAdjustedAttachmentFilePath());
												}else{
													getSession().setAttribute("SessionIsOldStudent","YES");
													getResponse() .sendRedirect( getRequest() .getContextPath() + "/alumnee/dashboard.do");
													
													return null;
												}
												studentDetails = null;
											}
										}
										if(!ObjectFunctions.isNullOrEmpty(getParamValue("passwordHint"))){
											getSession().setAttribute("passwordHint", "PW");
										}else{
											getSession().setAttribute("passwordHint", "");
										}
										//For Parent checking all conditions in the above code only
										getResponse().sendRedirect(getRequest().getContextPath()+"/student/studentHome.do");
										
									}
										else if(getUser().isSchoolTransport()){
											getExpiryDates();
											getResponse().sendRedirect(getRequest().getContextPath()+"/admin/transportDashboard.do");
										}
										else if(getUser().isTransportFinance()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/schoolfee/adminGetSchoolFee.do");
										}
										else if(getUser().isSchoolFinance()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/schoolfee/adminGetSchoolFee.do");
										}
										else if(getUser().isSchoolLibrarian()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/library/libraryHome.do");
										}
										else if(getUser().isSchoolHostel()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/hostel/manageHostel.do");
										}
										else if(getUser().isHostelFinance()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/schoolfee/adminGetSchoolFee.do");
										}
										else if(getUser().isMasterAdmin()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/masterAdmin/customerDetails.do");
										}
										else if(getUser().isSchoolClerk()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/staff/clerkStaffDashboard.do");
										}
										else if(getUser().isSchoolManager())
										{
											getSession().removeAttribute("SessionOrganizationId");
											getSession().removeAttribute("SessionCurrentFinancialYearId");
											getSession().setAttribute("SessionOrganizationId",String.valueOf(customer.getOrgId()));
											
											FinancialYear financialYear = (FinancialYear)adminManager.get(FinancialYear.class," orgId="+customer.getOrgId() + " and status='"+Constants.YES_STRING+"'");
											if(!ObjectFunctions.isNullOrEmpty(financialYear))
											{
												getSession().setAttribute("SessionCurrentFinancialYearId",financialYear.getId());
												
												financialYear = null;
											}
											getResponse().sendRedirect(getRequest().getContextPath()+"/admin/schoolManagerDashboard.do");
										}else if(getUser().isStoreKeeper()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/store/storeKeeperDashboard.do");
										}else if(getUser().isReceptionist()){
											getResponse().sendRedirect(getRequest().getContextPath()+"/admin/receptionistHome.do");;
										}
												break;
											}
										else {
											String url=getHostUrl()+"/j_spring_security_logout";
											getResponse().sendRedirect(getRequest().getContextPath()+"/signup/logoutsuccess.do");
											}
										}
									}
								}
							}
							else
							{
								String url=getHostUrl()+"/j_spring_security_logout";
								getResponse().sendRedirect(getRequest().getContextPath()+"/signup/logoutsuccess.do");
							}
						}
								else {
									String url=getHostUrl()+"/j_spring_security_logout";
									getResponse().sendRedirect(getRequest().getContextPath()+"/signup/logoutsuccess.do");
								}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
					}	
					return null;
				}
 
}