package com.urt.service.manager.impl.subscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urt.persistence.interfaces.subscription.SubscriptionDao;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.subscription.SubscriptionManager;

/**
 * Implementation of UserResourcesManager interface.
 * </p>
 * 
 * <p>
 * <a href="SubscriptionManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class SubscriptionManagerImpl extends UniversalManagerImpl implements
		SubscriptionManager {

	@Autowired
	private SubscriptionDao subscriptionDao;
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List findExistCustomer(String keyWord){
		 return subscriptionDao.findExistCustomer(keyWord);
	 }
	
}
