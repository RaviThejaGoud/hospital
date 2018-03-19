package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "staffSubstitutionTimeTable")
public class StaffSubstitutionTimeTable  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    private long custId;
    private long academicYearId;
    private int dayId;
    private StudyClass classSection;
    private StudySubject subject;
    //private Staff teacher;
    private String periodName;
    private TimetablePeriods timetablePeriods;
    //private Set<Staff> teachers;
    private Staff substituteStaff;
    private String substitutionDate;
    private Staff actualStaff;
    
    
    
    
    @OneToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="substituteStaffId")
    public Staff getSubstituteStaff() {
		return substituteStaff;
	}

	public void setSubstituteStaff(Staff substituteStaff) {
		this.substituteStaff = substituteStaff;
	}

	@OneToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="actualStaffId")
    public Staff getActualStaff() {
		return actualStaff;
	}

	public void setActualStaff(Staff actualStaff) {
		this.actualStaff = actualStaff;
	}

	public String getSubstitutionDate() {
		return substitutionDate;
	}

	public void setSubstitutionDate(String substitutionDate) {
		this.substitutionDate = substitutionDate;
	}

	@OneToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="timetablePeriodId")
    public TimetablePeriods getTimetablePeriods() {
		return timetablePeriods;
	}

	public void setTimetablePeriods(TimetablePeriods timetablePeriods) {
		this.timetablePeriods = timetablePeriods;
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

	public int getDayId() {
		return dayId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	@OneToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="classSectionId")
	public StudyClass getClassSection() {
		return classSection;
	}

	public void setClassSection(StudyClass classSection) {
		this.classSection = classSection;
	}

	@OneToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="subjectId")
	public StudySubject getSubject() {
		return subject;
	}

	public void setSubject(StudySubject subject) {
		this.subject = subject;
	}

	/*@OneToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="teacherId")
	public Staff getTeacher() {
		return teacher;
	}

	public void setTeacher(Staff teacher) {
		this.teacher = teacher;
	}*/

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public StaffSubstitutionTimeTable() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

    public StaffSubstitutionTimeTable(long id) {
        setId(id);
    }
 
	@Override
	public int compareTo(Object object) {
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	/*@ManyToMany
    @JoinTable(name = "staffTimeTablePeriods",
       joinColumns = { @JoinColumn(name = "timeTableId") },
       inverseJoinColumns = { @JoinColumn(name = "teacherId") })

	public Set<Staff> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Staff> teachers) {
		this.teachers = teachers;
	}

	public void  addTeacher(Staff staff){
    	if(!ObjectFunctions.isNullOrEmpty(staff)){
    		if(ObjectFunctions.isNullOrEmpty(getTeachers())){
        		this.teachers=new HashSet<Staff>();
        	}
    		getTeachers().add(staff);
    	}
    }*/

}
    

  

