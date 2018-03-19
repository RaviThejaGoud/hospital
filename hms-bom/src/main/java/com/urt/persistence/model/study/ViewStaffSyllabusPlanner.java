package com.urt.persistence.model.study;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ViewSyllabusPlannerComments entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "vw_staffSyllabusPlanner")
public class ViewStaffSyllabusPlanner  implements Serializable,Cloneable,Comparable {
	
	private static final long serialVersionUID = 1L;
    
    private long staffSyllabusPlannerId;
    protected long custId;
    private long academicYearId;
    private long studySubjectId;
    private long studyClassId;
    protected String subjectName;
    private long staffId;
    

    @Id
    public long getStaffSyllabusPlannerId() {
		return staffSyllabusPlannerId;
	}

	public void setStaffSyllabusPlannerId(long staffSyllabusPlannerId) {
		this.staffSyllabusPlannerId = staffSyllabusPlannerId;
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

	public long getStudySubjectId() {
		return studySubjectId;
	}

	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
	}

	public long getStudyClassId() {
		return studyClassId;
	}

	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
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
}
