package com.urt.persistence.model.common;
/********************************************************************
 * Copyright (C) 2017-18
 * Hyniva
 * All Rights Reserved 
 *
 * File: CountryCurrency.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Jun 30, 2017        Siva Nagaraju        Created
/********************************************************************/
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "CountryCurrency")
public class CountryCurrency extends PersistentObject {
	
   private static final long serialVersionUID = 1L;
	
   private String currencyName;
   private String currencySymbol;
   private String currencyWord;
   private long countryId;
   
	
   public CountryCurrency() {
		super();
		// TODO Auto-generated constructor stub
	}
	
   public String getCurrencyName() {
		return currencyName;
	}


	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}


	public String getCurrencySymbol() {
		return currencySymbol;
	}


	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getCurrencyWord() {
		return currencyWord;
	}

	public void setCurrencyWord(String currencyWord) {
		this.currencyWord = currencyWord;
	}
	
	public long getCountryId() {
		return countryId;
	}


	public void setCountryId(long countryId) {
		this.countryId = countryId;
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
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}


}