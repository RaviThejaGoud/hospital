package com.urt.persistence.impl.user;

import java.util.List;

import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.user.RoleDao;
import com.urt.persistence.model.user.Role;


/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Role objects.
 *
 * <p>
 * <a href="RoleDaoHibernate.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a> 
 */
public class RoleDaoHibernate extends UniversalHibernateDao implements RoleDao {

    public List getRoles(Role role) {
       // return getHibernateTemplate().find("from Role");
    	 return this.getAllHqlQuery("from Role");
    }
    
    public Role getRole(Long roleId) {
        return (Role) this.get(Role.class, roleId);
    }

    public void saveRole(Role role) {
    	
        this.saveObject(role);
    }

    public void removeRole(String rolename) {
        Object role = getRoleByName(rolename);
        getHibernateTemplate().delete(role);
    }

	public Role getRole(long roleId) {
        //List roles = getHibernateTemplate().find("from Role where id=?", roleId);
		List roles = this.getAllHqlQuery("from Role where id="+roleId);
        if (roles.isEmpty()) {
            return null;
        } else {
            return (Role) roles.get(0);
        }
    }

}
