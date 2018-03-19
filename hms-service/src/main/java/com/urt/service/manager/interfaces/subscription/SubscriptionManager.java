package com.urt.service.manager.interfaces.subscription;

import java.util.List;

import com.urt.persistence.model.customer.Customer;
import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="SubscriptionManager.java.html"><i>View Source</i></a></p>
 */
public interface SubscriptionManager extends UniversalManager {
	List<Customer> findExistCustomer(String keyWord);
   
	
}
