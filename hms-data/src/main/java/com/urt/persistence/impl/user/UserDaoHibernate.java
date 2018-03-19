package com.urt.persistence.impl.user;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.exception.base.URTDataAccessException;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.user.UserDao;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.User;
import com.urt.util.common.RayGunException;

@Transactional
public class UserDaoHibernate extends UniversalHibernateDao implements UserDao,UserDetailsService {
	
	private static final Log log = LogFactory.getLog(UserDaoHibernate.class);
	/**
	 * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException 
	{
		//List users = getHibernateTemplate().find("from User where username=?", username);
		/*List users=null;
		List<Person> persons=null;
		users = getHibernateTemplate().find("from User where username=?", username);*/
		List users = this.getAllHqlQuery("from User where (username='"+username+"'  OR secondaryUsername='"+username+"') and accountExpired='N'");
		
		if (ObjectFunctions.isNullOrEmpty(users)) {
			/*persons = this.getAllHqlQuery("from Person where mobileNumber='"+ username+"' or  mobileNumber='+91-"+username+"' or parentEmail='"+username+"' or studentMobile='"+username+"' or  studentMobile='+91-"+username+"' or studentEmail='"+username+"'");
			if (!ObjectFunctions.isNullOrEmpty(persons)) {
				users = getHibernateTemplate().find("from User where personId=?", persons.get(0).getId());	
			}	
			if (!ObjectFunctions.isNullOrEmpty(users)) {
				return (UserDetails) users.get(0);
			}
			else*/
			throw new UsernameNotFoundException("user '" + username + "' not found...");
		}
		else {
			//User luser = (User) users.get(0);
			//luser.setLastAccessDate(new Date());
			//this.saveObject(luser);
			//luser=null;
			return (UserDetails) users.get(0);
		}
	}

	public String passwordEncoder(String plainText, String saltText) {
		ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
		SystemWideSaltSource saltSource = new SystemWideSaltSource();
		saltSource.setSystemWideSalt(saltText);
		// Parameter in getSalt() can be null because SystemWideSaltSource does
		// not use it
		String encodedPassword = passwordEncoder.encodePassword(plainText,
				saltSource.getSalt(null));
		return encodedPassword;
	}

	public ViewStudentPersonAccountDetails getViewStudentDetails(long accountId, String studentStatus) {
    	try{
    		StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from ViewStudentPersonAccountDetails where accountId=");
			queryBuff.append(accountId);
			queryBuff.append(" and status = '");
			queryBuff.append(studentStatus);
			queryBuff.append("'");
			log.debug(queryBuff.toString()); 
			List studentsList=this.getAll(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(studentsList)){
				return (ViewStudentPersonAccountDetails)studentsList.get(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
    }
	public Customer saveCustomer(Customer customer){
		try{
			
			 this.saveObject(customer);
			 return customer;
		}
		catch (HibernateException ex) {
		     ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;throw new URTDataAccessException(ex.getMessage());
	    }
	}
	public User saveUser(User user){
		try
		{
			
		  this.saveObject(user);
		  return user;
	   }
		catch (HibernateException ex) {
		     ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;throw new URTDataAccessException(ex.getMessage());
	    }
	}
	
	public User getAccountByCustIdAndStatus(String username,long custId,String status){
	 List users = this.getAllHqlQuery("from User where username='"+username+"' and accountEnabled='"+status+"' and custId="+custId);
       if (!ObjectFunctions.isNullOrEmpty(users))
       {
               return (User) users.get(0);
       }
        return null;
	}
	public User getAccountByCustId(String username,long custId){
	 List users = this.getAllHqlQuery("from User where username='"+username+"' and custId="+custId);
       if (!ObjectFunctions.isNullOrEmpty(users))
       {
               return (User) users.get(0);
       }
      return null;
	}
	 
	public void removeRoleByUserIdAndRoleIdNotIn(String roleId,long userId){
	   try{
		   StringBuffer sqlString =new StringBuffer();
			 sqlString.append("delete from UserRoles where userId="+userId+" and  roleId not in "+roleId+" ");
			 Query qry = getSession().createSQLQuery(sqlString.toString());
			 int row = qry.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
   }
	
	public String getRolesByRoleIdAndUserId(long userId,long roleId){
		try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select roleId from UserRoles where userId=");
			queryBuff.append(userId);	
			queryBuff.append(" and roleId=");
			queryBuff.append(roleId); 
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				BigInteger var= ((BigInteger)resultList.get(0));
				return var.toString();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	 
	public void  removeSchoolAreas(long custId){
		   try{
			   StringBuffer sqlString =new StringBuffer();
				 sqlString.append("delete from schoolArea where custId="+custId);
				 Query qry = getSession().createSQLQuery(sqlString.toString());
				 int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
	   }
}
