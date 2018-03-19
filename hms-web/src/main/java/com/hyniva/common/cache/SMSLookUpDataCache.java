/********************************************************************
 * Copyright (C) 2018
 * HYNIVA
 * All Rights Reserved 
 *
 * File: SMSLookUpDataCache.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   Jan 20, 2018     KulaSekhar		       Initial Version
/ ********************************************************************/
package com.hyniva.common.cache;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.BankMaster;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.Medium;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.exam.MotherTongue;
import com.urt.service.manager.interfaces.admin.AdminManager;



public class SMSLookUpDataCache {

	@SuppressWarnings("unchecked")
	public static HashMap lookUpDataMap;
	static Logger logger = Logger.getLogger(SMSLookUpDataCache.class);


	@Autowired
	private AdminManager adminManager;

	@SuppressWarnings({ "unchecked", "static-access" })
	@PostConstruct
	public void initIt() {
		try {
			// Create hash map to set all reference table data
			HashMap hm = new HashMap();
			// getting mother tongue details
			List<MotherTongue> motherTongueList = adminManager.getAll(MotherTongue.class);
			if(ObjectFunctions.isNotNullOrEmpty(motherTongueList)){

				Collections.sort(motherTongueList, new Comparator<MotherTongue>() {
					@Override
					public int compare(MotherTongue o1, MotherTongue o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
			}
			hm.put(Constants.MOTHER_TONGUE_LIST,motherTongueList);

			// getting state details
			List<State> stateList = adminManager.getAll(State.class);

			if(ObjectFunctions.isNotNullOrEmpty(stateList)){

				Collections.sort(stateList, new Comparator<State>() {
					@Override
					public int compare(State o1, State o2) {
						return o1.getStateName().compareTo(o2.getStateName());
					}
				});
			}
			hm.put(Constants.STATE_LIST,stateList);

			// getting country details
			List<Country> countryList = adminManager.getAll(Country.class);

			if(ObjectFunctions.isNotNullOrEmpty(countryList)){

				Collections.sort(countryList, new Comparator<Country>() {
					@Override
					public int compare(Country o1, Country o2) {
						return o1.getCountryName().compareTo(o2.getCountryName());
					}
				});
			}
			hm.put(Constants.COUNTRY_LIST,countryList);	
			
			// getting medium details
			List<Medium> mediumList = adminManager.getAll(Medium.class);

			if(ObjectFunctions.isNotNullOrEmpty(mediumList)){

				Collections.sort(mediumList, new Comparator<Medium>() {
					@Override
					public int compare(Medium o1, Medium o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
			}
			hm.put(Constants.MEDIUM_LIST,mediumList);	
			
			// getting Bank Details details
			List<BankMaster> bankList = adminManager.getAll(BankMaster.class);
			if(ObjectFunctions.isNotNullOrEmpty(bankList)){
				Collections.sort(bankList, new Comparator<BankMaster>() {
					@Override
					public int compare(BankMaster o1, BankMaster o2) {
						return o1.getBankName().compareTo(o2.getBankName());
						}
					});
			}
			hm.put(Constants.BANK_LIST,bankList);	
					

			this.lookUpDataMap = hm;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
