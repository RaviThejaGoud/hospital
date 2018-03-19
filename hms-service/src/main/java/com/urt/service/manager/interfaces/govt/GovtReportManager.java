package com.urt.service.manager.interfaces.govt;

import java.util.List;

import com.urt.persistence.model.customer.Customer;
import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="GovtReportManager.java.html"><i>View Source</i></a></p>
 */
public interface GovtReportManager extends UniversalManager {
    
	 List<Customer> findExistCustomer(String keyWord);
     //GovtMessSettings getMessSettingByCustIdMealTypeCode(long custId,String mealTypeCode); 
	 //StudentMessAccess getStudentMessAccess(long studentId,String date,String mealType);
	
}
