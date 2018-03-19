package com.urt.persistence.model.study;

import java.util.Comparator;

import com.churchgroup.util.object.ObjectFunctions;

public class StaffRoleNameComparator implements Comparator {
	public int compare(Object roleId1, Object roleId2) {
    	ViewStaffPersonAccountDetails role1=(ViewStaffPersonAccountDetails)roleId1;
    	ViewStaffPersonAccountDetails role2=(ViewStaffPersonAccountDetails)roleId2;
    	if (!ObjectFunctions.isNullOrEmpty(role1) && !ObjectFunctions.isNullOrEmpty(role2))
        {
    				return role1.getRoleName().compareToIgnoreCase(role2.getRoleName());
        }
        return 0;
	
    }
}
