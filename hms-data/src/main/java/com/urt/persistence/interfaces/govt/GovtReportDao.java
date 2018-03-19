package com.urt.persistence.interfaces.govt;

import java.util.List;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.customer.Customer;

/**
 * Resources Data Access Object (Dao) interface.
 * 
 * <p>
 * <a href="GovtReportDao.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface GovtReportDao extends UniversalDao {
	 List<Customer> findExistCustomer(String keyWord);
	 //GovtMessSettings getMessSettingByCustIdMealTypeCode(long custId,String mealTypeCode);
	 //StudentMessAccess  getStudentMessAccess(long studentId,String date,String mealType);
	
}
