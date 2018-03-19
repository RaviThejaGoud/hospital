package com.urt.persistence.interfaces.subscription;

import java.util.List;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.customer.Customer;

/**
 * Resources Data Access Object (Dao) interface.
 * 
 * <p>
 * <a href="SubscriptionDao.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface SubscriptionDao extends UniversalDao {
		List<Customer> findExistCustomer(String keyWord);
	
}
