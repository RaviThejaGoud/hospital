/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Messages.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Dec 03, 2010       Narahari          Created
/ ********************************************************************/

package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

/** @author Narahari */
@Entity
@Table(name = "messagesGuidDetails")
public class MessagesGuidDetails extends PersistentObject {

    private static final long serialVersionUID = 1L;
    private String trackingDetails;
    
    
	public String getTrackingDetails() {
		return trackingDetails;
	}
	public void setTrackingDetails(String trackingDetails) {
		this.trackingDetails = trackingDetails;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
   
}