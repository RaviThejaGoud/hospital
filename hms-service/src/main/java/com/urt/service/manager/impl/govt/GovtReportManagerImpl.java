package com.urt.service.manager.impl.govt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urt.persistence.interfaces.govt.GovtReportDao;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.govt.GovtReportManager;

/**
 * Implementation of UserResourcesManager interface.
 * </p>
 * 
 * <p>
 * <a href="GovtReportManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class GovtReportManagerImpl extends UniversalManagerImpl implements 
	GovtReportManager {

	@Autowired
	private GovtReportDao govtReportDao;
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List findExistCustomer(String keyWord){
		 return govtReportDao.findExistCustomer(keyWord);
	 }
	 
}
