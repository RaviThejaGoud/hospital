package com.urt.persistence.impl.subscription;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.subscription.SubscriptionDao;
import com.urt.persistence.model.customer.Customer;
import com.urt.util.common.RayGunException;

@Transactional
public class SubscriptionDaoHibernate extends UniversalHibernateDao implements	SubscriptionDao {
	 public List<Customer> findExistCustomer(String keyWord) {
		 try{
			 List customerList=this.getAllHqlQuery("from Customer where custEmail='"+keyWord+"'");
			 if(!ObjectFunctions.isNullOrEmpty(customerList)){
				 return  (List<Customer>) customerList;
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	  }
	
}
