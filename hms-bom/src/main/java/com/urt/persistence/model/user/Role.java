package com.urt.persistence.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

import com.hyniva.sms.ws.vo.RoleVo;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Immutable
@Table(name = "Role")
public class Role extends PersistentObject implements GrantedAuthority {
    
    private static final long serialVersionUID = 3690197650654049848L;
    
    private String name;
    private String description;
    
    public Role(long id) {
    	setId(id);
    }

    public Role() {
    }
    /**
     * @see org.acegisecurity.GrantedAuthority#getAuthority()
     */
    @Transient
    public String getAuthority() {
        return getName();
    }
    
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return this.name;
    }

    @Column(name = "description", nullable = true, length = 128)
    public String getDescription() {
        return this.description;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        final Role role = (Role) o;

        return !(name != null ? !name.equals(role.name) : role.name != null);

    }
    @Override
    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(" Id: ")
                .append(getId())
                .append(" Version: ")
                
                .append(" Role: ")
                .append(this.getName())
                .append(" Description: ")
                .append(this.getDescription())
                .toString();
    }

    @Override
    public int compareTo(Object object) {
        Role myClass = (Role) object;
        return new CompareToBuilder().append(this.description,
                myClass.description).append(this.name, myClass.name)
                .toComparison();
    }

    public RoleVo copyFromEntityToVo(Role role)
	{
    	RoleVo roleVo = new RoleVo();
    	roleVo.name = role.name;
    	roleVo.description = role.description;
    	roleVo.id = role.id;
    	
		return roleVo;
	}

    
    public Set<RoleVo> copyFromEntityToVo(Set<Role> roles)
	{
    	Set<RoleVo> roleSet = new HashSet<RoleVo>();
    	for(Role role : roles)
    	{
    		RoleVo roleVo = new RoleVo();
        	roleVo.name = role.name;
        	roleVo.description = role.description;
        	roleVo.id = role.id;
        	roleSet.add(roleVo);
    	}
		return roleSet;
	}
    
    
    public Set<Role> copyFromVoToEntity(Role role1,Set<RoleVo> roles)
	{
    	Set roleSet = new HashSet<Role>();
    	for(RoleVo roleVo : roles)
    	{
    		Role role = new Role();
    		role.name = roleVo.name;
        	role.description = roleVo.description;
        	role.id = roleVo.id;
        	roleSet.add(role);
    	}
    	
    	
		return roleSet;
	}
    
    public Role copyFromVoToEntity(Role role,RoleVo roleVo)
	{
    	role.name = roleVo.name;
    	role.description = roleVo.description;
    	role.id = roleVo.id;
    	
    	
		return role;
	}
    
    
}
