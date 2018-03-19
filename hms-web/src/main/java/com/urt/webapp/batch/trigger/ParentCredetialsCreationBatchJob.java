/********************************************************************
 * Copyright (C) 2007-08
 * IFS
 * All Rights Reserved 
 *
 * File: CampaignBatchJob.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Apr 28, 2008       Sreeram J          Created
/********************************************************************/

package com.urt.webapp.batch.trigger;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.SMSServiceProviders;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.Role;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.MobileSMSDataTaskExecutor;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.student.StudentManager;
import com.urt.util.common.PasswordUtils;

public class ParentCredetialsCreationBatchJob extends MobileSMSDataTaskExecutor
		implements Job {

	private static final Log log = LogFactory.getLog(ParentCredetialsCreationBatchJob.class);
	// @SuppressWarnings("unused")
	private static final long serialVersionUID = 6137644058516625485L;
	private static Log _log = LogFactory.getLog(ParentCredetialsCreationBatchJob.class);

	public ParentCredetialsCreationBatchJob() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Autowired
	private AdminManager adminManager;
	@Autowired
	private StudentManager studentManager;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		String jobName = context.getJobDetail().getDescription();
		log.info("ParentCredetialsCreationBatchJob Batch Process Started : " + jobName + " executing at " + new Date());
		log.info("Entering parent batch");
		try {
			Student student = null;
			List<BigInteger> custIds = null;
			List<Customer> customersList = null;
			List<User> userList = null;
			List<BigInteger> accountIds = null;
			AcademicYear academicYear = null;
			Customer masterCustomer =(Customer) adminManager.get(Customer.class, Long.valueOf(1));
			/*Role parentRole = adminManager.getRoleByName(Constants.SCHOOL_PARENT);
			SMSServiceProviders smsServiceProviders=(SMSServiceProviders)  adminManager.getSMSServiceProviderByCustId(masterCustomer.getSmsServiceProviderId());*/
			//int count = adminManager.getCount("Account","id in (select userId from UserRoles where roleId=3) and parentId is null or parentId=0");
			/*int count = adminManager.getCount("Account a LEFT JOIN studentparent sp ON(a.id=sp.studentAccountId) ","a.id in (select userId from UserRoles where roleId=3) and sp.parentAccountId is null or sp.parentAccountId=0");
			// int count1 = adminManager.getCount("student","accountId in (select id from Account where parentId is null or parentId=0)");
			// log.debug(count+" "+count1);
			if (count > 0) {*/
				// List<String> custIds = adminManager.getAll("select group_concat(custId) from Account where id in (select userId from UserRoles where roleId=3) and parentId is null or parentId=0");
				//custIds = adminManager.getAll("select custId from Account where id in (select userId from UserRoles where roleId=3) and parentId is null or parentId=0 group by custId");
				//custIds = adminManager.getAll("select a.custId from Account a LEFT JOIN studentparent sp ON(sp.studentAccountId = a.id) where a.id in (select userId from UserRoles where roleId=3) and sp.parentAccountId is null or sp.parentAccountId=0 group by a.custId");
				// log.debug(custIds);
				//customersList = adminManager.getAll(Customer.class, "id in ("+StringFunctions.convertListToCommaDelimitedString(custIds)+") and status='"+ Constants.YES_STRING+ "'");
				customersList = adminManager.getAll(Customer.class, "status='"+ Constants.YES_STRING+ "'");
				//customersList = adminManager.getAll(Customer.class, "id in (191) and status='"+ Constants.YES_STRING+ "'");
				if (!ObjectFunctions.isNullOrEmpty(customersList)) {
					for (Customer customer : customersList) {
						log.info("Customer Id :"+customer.getId());
						academicYear = adminManager.getCurrentAcademicYear(Constants.YES_STRING, customer.getId());
						if (!ObjectFunctions.isNullOrEmpty(academicYear)) {
							//accountIds = adminManager.getAll("select id from Account where custId="+ customer.getId()+ " and id in (select userId from UserRoles where roleId=3) and parentId is null or parentId=0 group by id");
							//accountIds = adminManager.getAll("select a.id from Account a LEFT JOIN studentparent sp ON(a.id = sp.studentAccountId) where a.custId="+ customer.getId()+ " and a.id in (select userId from UserRoles where roleId=3) and sp.parentAccountId is null or sp.parentAccountId=0 group by a.id");
 							accountIds = adminManager.getAll("select id from Account where custId="+customer.getId()+" and accountEnabled='Y' and id IN(select userId from UserRoles where roleId=3) and id NOT IN (select studentAccountId from studentparent where studentAccountId in(select id from Account where custId="+customer.getId()+"))");
							log.info(accountIds);
							userList = adminManager.getAll(User.class,"id in ("+ StringFunctions.convertListToCommaDelimitedString(accountIds)+ ")");
							log.info(userList.size());
							if (!ObjectFunctions.isNullOrEmpty(userList)) {
								for (User user : userList) {
										student = (Student) adminManager.get(Student.class,"accountId="+ user.getId()+" and academicYearId="+academicYear.getId()+" and description is NULL");
										if(!ObjectFunctions.isNullOrEmpty(student)) {
											studentManager.createParentLoginAccount(customer,academicYear, student,false,  masterCustomer,null);
										}
									user = null;
									student = null;
								}
								userList = null;
								accountIds = null;
							} else {
								_log.info("Parent is not found for student.(OR) No Parent found for sending Parent login credentails.");
							}
							academicYear = null;
						}
						customer = null;
					}
					customersList = null;
				}
				custIds = null;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		_log.info("ParentCredetialsCreationBatchJob Batch Completed successfully.....");
	}
}