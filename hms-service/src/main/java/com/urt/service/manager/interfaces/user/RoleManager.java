/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="RoleManager.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
package com.urt.service.manager.interfaces.user;

import java.util.List;

import com.urt.persistence.model.user.Role;

public interface RoleManager {

    List getRoles(Role role);

    Role getRole(long roleId);
    
    void saveRole(Role role);

    void removeRole(String rolename);
}
