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

import com.hyniva.sms.ws.vo.PersonHealthDetailsVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "PersonHealthDetails")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class PersonHealthDetails extends PersistentObject {

     private static final long serialVersionUID = 1L;
     
     private String allergies;
     private String heartProblem;
     private String diabetes;
     private String asthma;
     private String otherMedicalCondition;
     private String familyDoctor;
     private String doctorsContactNo;
     private String emergencyNo1;
     private String emergencyNo2;
     
     

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

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getHeartProblem() {
		return heartProblem;
	}

	public void setHeartProblem(String heartProblem) {
		this.heartProblem = heartProblem;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	public String getAsthma() {
		return asthma;
	}

	public void setAsthma(String asthma) {
		this.asthma = asthma;
	}

	public String getOtherMedicalCondition() {
		return otherMedicalCondition;
	}

	public void setOtherMedicalCondition(String otherMedicalCondition) {
		this.otherMedicalCondition = otherMedicalCondition;
	}

	public String getFamilyDoctor() {
		return familyDoctor;
	}

	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}

	public String getDoctorsContactNo() {
		return doctorsContactNo;
	}

	public void setDoctorsContactNo(String doctorsContactNo) {
		this.doctorsContactNo = doctorsContactNo;
	}

	public String getEmergencyNo1() {
		return emergencyNo1;
	}

	public void setEmergencyNo1(String emergencyNo1) {
		this.emergencyNo1 = emergencyNo1;
	}

	public String getEmergencyNo2() {
		return emergencyNo2;
	}

	public void setEmergencyNo2(String emergencyNo2) {
		this.emergencyNo2 = emergencyNo2;
	}

	public PersonHealthDetails deepCopyVoToEntity(
			PersonHealthDetailsVO personHealthDetailsVO) {
		 this.allergies = personHealthDetailsVO.getAllergies();
		 this.heartProblem = personHealthDetailsVO.getHeartProblem();
		 this.diabetes = personHealthDetailsVO.getDiabetes();
		 this.asthma = personHealthDetailsVO.getAsthma();
		 this.otherMedicalCondition = personHealthDetailsVO.getOtherMedicalCondition();
		 this.familyDoctor = personHealthDetailsVO.getFamilyDoctor();
		 this.doctorsContactNo = personHealthDetailsVO.getDoctorsContactNo();
		 this.emergencyNo1 = personHealthDetailsVO.getEmergencyNo1();
		 this.emergencyNo2 = personHealthDetailsVO.getEmergencyNo2();
		return this;
	}
     

}
