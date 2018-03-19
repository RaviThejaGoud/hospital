package com.urt.persistence.model.user;
/********************************************************************
 * Copyright (C) 2017-18
 * Hyniva
 * All Rights Reserved 
 *
 * File: ParentsEmploymentDetails.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0   Jun 20, 2017       Siva Nagaraju G       Created
/********************************************************************/

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hyniva.sms.ws.vo.ParentsEmploymentDetailsVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "ParentsEmploymentDetails")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class ParentsEmploymentDetails extends PersistentObject {

     private static final long serialVersionUID = 1L;
     
     private String fatherOrganizationName;
     private String fatherDesignation;
     private String fatherSelfEmployed;
     private String fatherNatureofBusiness;
     private String motherOrganizationName;
     private String motherDesignation;
     private String motherSelfEmployed;
     private String motherNatureofBusiness;
     private Double motherAnnualIncome;
     
     
     

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

	public String getFatherOrganizationName() {
		return fatherOrganizationName;
	}

	public void setFatherOrganizationName(String fatherOrganizationName) {
		this.fatherOrganizationName = fatherOrganizationName;
	}

	public String getFatherDesignation() {
		return fatherDesignation;
	}

	public void setFatherDesignation(String fatherDesignation) {
		this.fatherDesignation = fatherDesignation;
	}

	public String getFatherSelfEmployed() {
		return fatherSelfEmployed;
	}

	public void setFatherSelfEmployed(String fatherSelfEmployed) {
		this.fatherSelfEmployed = fatherSelfEmployed;
	}

	public String getFatherNatureofBusiness() {
		return fatherNatureofBusiness;
	}

	public void setFatherNatureofBusiness(String fatherNatureofBusiness) {
		this.fatherNatureofBusiness = fatherNatureofBusiness;
	}

	public String getMotherOrganizationName() {
		return motherOrganizationName;
	}

	public void setMotherOrganizationName(String motherOrganizationName) {
		this.motherOrganizationName = motherOrganizationName;
	}

	public String getMotherDesignation() {
		return motherDesignation;
	}

	public void setMotherDesignation(String motherDesignation) {
		this.motherDesignation = motherDesignation;
	}

	public String getMotherSelfEmployed() {
		return motherSelfEmployed;
	}

	public void setMotherSelfEmployed(String motherSelfEmployed) {
		this.motherSelfEmployed = motherSelfEmployed;
	}

	public String getMotherNatureofBusiness() {
		return motherNatureofBusiness;
	}

	public void setMotherNatureofBusiness(String motherNatureofBusiness) {
		this.motherNatureofBusiness = motherNatureofBusiness;
	}

	public Double getMotherAnnualIncome() {
		return motherAnnualIncome;
	}

	public void setMotherAnnualIncome(Double motherAnnualIncome) {
		this.motherAnnualIncome = motherAnnualIncome;
	}

	public ParentsEmploymentDetails deepCopyVoToEntity(
			ParentsEmploymentDetailsVO parentsEmploymentDetailsVO) {
		this.fatherOrganizationName = parentsEmploymentDetailsVO.getFatherOrganizationName();
		this.fatherDesignation = parentsEmploymentDetailsVO.getFatherDesignation();
		this.fatherSelfEmployed = parentsEmploymentDetailsVO.getFatherSelfEmployed();
		this.fatherNatureofBusiness = parentsEmploymentDetailsVO.getFatherNatureofBusiness();
		this.motherOrganizationName = parentsEmploymentDetailsVO.getMotherOrganizationName();
		this.motherDesignation = parentsEmploymentDetailsVO.getMotherDesignation();
		this.motherSelfEmployed = parentsEmploymentDetailsVO.getMotherSelfEmployed();
		this.motherNatureofBusiness = parentsEmploymentDetailsVO.getMotherNatureofBusiness();
		this.motherAnnualIncome = parentsEmploymentDetailsVO.getMotherAnnualIncome();
		return this;
	}

}
