/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: HouseType.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   Dec 25, 2017     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "Ref_HouseType")
public class HouseType extends PersistentObject {
	
   private static final long serialVersionUID = 1L;
	
   private String type;
   private String description;
		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
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