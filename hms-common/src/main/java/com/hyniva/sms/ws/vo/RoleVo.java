package com.hyniva.sms.ws.vo;

public class RoleVo {
	
	public long id;
	public String name;
	public String description;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 
	public String getAuthority() {
        return getName();
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
