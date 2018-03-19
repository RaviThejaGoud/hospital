package com.urt.service.manager.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urt.persistence.interfaces.user.RoleDao;
import com.urt.persistence.model.user.Role;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.user.RoleManager;

/**
 * Implementation of RoleManager interface.</p>
 * 
 * <p><a href="RoleManagerImpl.java.html"><i>View Source</i></a></p>
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Component
public class RoleManagerImpl extends UniversalManagerImpl implements RoleManager {
    
	@Autowired
	private RoleDao roledao;

  
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
    public List getRoles(Role role) {
        return roledao.getRoles(role);
    }

    public Role getRole(long roleId) {
        return roledao.getRole(roleId);
    }

    public void saveRole(Role role) {
    	roledao.saveRole(role);
    }

    public void removeRole(String rolename) {
    	roledao.removeRole(rolename);
    }

}
