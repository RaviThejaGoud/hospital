package com.urt.persistence.interfaces.user;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.user.Role;

/**
 * Role Data Access Object (DAO) interface.
 *
 * <p><a href="RoleDao.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface RoleDao extends UniversalDao {
   
    /**
     * Gets a list of roles based on parameters passed in.
     *
     * @return List populated list of roles
     */
     List getRoles(Role role);

    /**
     * Saves a role's information
     * @param role the object to be saved
     */
     @Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
     void saveRole(Role role);

    /**
     * Removes a role from the database by name
     * @param rolename the role's rolename
     */
     void removeRole(String rolename);
    
     Role getRole(long roleId);
}
