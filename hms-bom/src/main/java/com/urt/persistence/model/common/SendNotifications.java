/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Scraps.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Feb 03, 2011       Narahari          Created
/ ********************************************************************/

package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Narahari
 *
 */
@Entity
@Table(name = "sendNotifications")
public class SendNotifications extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     
     private String description;
     protected String status="A";
      
     
     
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "description", nullable = true, length = 2024)
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public SendNotifications() {
		super();
	}


	public SendNotifications( String status) {
		super();
		this.status = status;
		
	}


	@Override
	public int compareTo(Object object) {
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return "";
	}
	 @Transient
		public String getCreatedDateStr() {
			return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getCreatedDate());
		}
}