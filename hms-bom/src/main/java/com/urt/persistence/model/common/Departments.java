package com.urt.persistence.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.StudyClass;

@Entity
@Table(name = "Departments")
public class Departments extends PersistentObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String departmentName;
	private String departmentHeadName;
	protected long deptStaffId;
    protected long custId;
	protected long academicYearId;
	protected String mobileNumber;
	protected Set<StudyClass> studyClass;
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentHeadName() {
		return departmentHeadName;
	}

	public void setDepartmentHeadName(String departmentHeadName) {
		this.departmentHeadName = departmentHeadName;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getDeptStaffId() {
		return deptStaffId;
	}

	public void setDeptStaffId(long deptStaffId) {
		this.deptStaffId = deptStaffId;
	}

	/**
	 * @return the branches
	 */
    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "deptStudyClasses",
       joinColumns = { @JoinColumn(name = "deptId") },
       inverseJoinColumns = { @JoinColumn(name = "studyClassesId") })
    
    public Set<StudyClass> getStudyClass() {
		return this.studyClass;
	}
	
    public void setStudyClass(Set<StudyClass> studyClass) {
		this.studyClass = studyClass;
	}
    
	public void addDeptClasses(StudyClass studyClass) {
		if(ObjectFunctions.isNullOrEmpty(this.getStudyClass())){
			this.studyClass=new HashSet<StudyClass>();
		}
		this.studyClass.add(studyClass);
	}
	 public void removeStudyClass(StudyClass studyClass) {
		 getStudyClass().remove(studyClass);
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
