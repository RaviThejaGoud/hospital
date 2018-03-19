package com.urt.persistence.impl.govt;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.govt.GovtReportDao;
import com.urt.persistence.model.customer.Customer;
import com.urt.util.common.RayGunException;

@Transactional
public class GovtReportDaoHibernate extends UniversalHibernateDao implements	GovtReportDao {
	private static final Log log = LogFactory.getLog(GovtReportDaoHibernate.class);
	 public List<Customer> findExistCustomer(String keyWord) {
		 try{
			 List customerList=this.getAllHqlQuery("from Customer where custEmail='"+keyWord+"'");
			 if(!ObjectFunctions.isNullOrEmpty(customerList)){
				 return (List<Customer>) customerList;
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	  }
	
}
