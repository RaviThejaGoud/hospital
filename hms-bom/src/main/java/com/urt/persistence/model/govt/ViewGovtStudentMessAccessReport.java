package com.urt.persistence.model.govt;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* vw_govtschoolsreport entity. @author MyEclipse Persistence Tools
*/
/**
 * @author urt
 *
 */
@Entity
@Table(name = "vw_studentMessAccessedDetails") 
public class ViewGovtStudentMessAccessReport implements Serializable,Cloneable,Comparable {


	private static final long serialVersionUID = 1L;
	private long custId;
	private Date accessedDate;
	protected String mealType;  
	private String present;
	private long studentId;
	private long academicYearId;
	
	public Date getAccessedDate() {
		return accessedDate;
	}

	public void setAccessedDate(Date accessedDate) {
		this.accessedDate = accessedDate;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	private long stateId;
	private long districtId;
	private long mandalId;
	private long villageId;
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String mandalCode;
	private String mandalName;
	private String villageCode;
	private String villageName;
	

	// Constructors

	/** default constructor */
	public ViewGovtStudentMessAccessReport() {
	}

	@Id
	public long getCustId() {
		return custId;
	}


	public void setCustId(long custId) {
		this.custId = custId;
	}
    
  

	public long getStateId() {
		return stateId;
	}


	public void setStateId(long stateId) {
		this.stateId = stateId;
	}


	public long getDistrictId() {
		return districtId;
	}


	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}


	public long getMandalId() {
		return mandalId;
	}


	public void setMandalId(long mandalId) {
		this.mandalId = mandalId;
	}


	public long getVillageId() {
		return villageId;
	}


	public void setVillageId(long villageId) {
		this.villageId = villageId;
	}


	public String getStateCode() {
		return stateCode;
	}


	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public String getDistrictCode() {
		return districtCode;
	}


	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public String getMandalCode() {
		return mandalCode;
	}


	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}


	public String getMandalName() {
		return mandalName;
	}


	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}


	public String getVillageCode() {
		return villageCode;
	}


	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}


	public String getVillageName() {
		return villageName;
	}


	public void setVillageName(String villageName) {
		this.villageName = villageName;
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

	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
     
}