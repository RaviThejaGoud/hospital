package com.urt.persistence.interfaces.user;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.User;

/**
 * User Data Access Object (Dao) interface.
 *
 * <p>
 * <a href="UserDao.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDao extends UniversalDao {
    
    
    /**
     * Gets users information based on login name.
     * @param username the user's username
     * @return userDetails populated userDetails object
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
     
    /**
     * Generic method to save an object - handles both update and insert.
     * @param o the object to save
     * @return a populated object
     */
    @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    Object saveObject(Object o);
    
    ViewStudentPersonAccountDetails getViewStudentDetails(long accountId,String status);
    
    @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    Customer saveCustomer(Customer customer);
    
    @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    User saveUser(User user);
    User getAccountByCustId(String username,long custId);
    User getAccountByCustIdAndStatus(String username,long custId,String status);
    void removeRoleByUserIdAndRoleIdNotIn(String roleId,long userId);
    String getRolesByRoleIdAndUserId(long userId,long roleId);
    void removeSchoolAreas(long custId);
}
