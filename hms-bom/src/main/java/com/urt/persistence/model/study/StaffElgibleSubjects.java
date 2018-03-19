package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

@Entity
@Table(name = "staffElgibleSubjects")
public class StaffElgibleSubjects  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected Staff staffId;
    protected StudySubject studySubjectId;
    protected AcademicYear academicYear;
    
    
    
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="staffId")
    public Staff getStaffId() {
		return staffId;
	}
	public void setStaffId(Staff staffId) {
		this.staffId = staffId;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="studySubjectId")
	public StudySubject getStudySubjectId() {
		return studySubjectId;
	}
	public void setStudySubjectId(StudySubject studySubjectId) {
		this.studySubjectId = studySubjectId;
	}
	
	/**
     * @return the academicYear
     */
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    

    
    public StaffElgibleSubjects() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

	public StaffElgibleSubjects(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		StaffElgibleSubjects staffElgible=(StaffElgibleSubjects)object;
		if(!ObjectFunctions.isNullOrEmpty(staffElgible)){
			StudySubject sub=staffElgible.getStudySubjectId();
			 if(!ObjectFunctions.isNullOrEmpty(sub) && !ObjectFunctions.isNullOrEmpty(this.getStudySubjectId())){
				 if(sub.getSortingOrder() > 0 && this.getStudySubjectId().getSortingOrder() > 0){
						if(this.getStudySubjectId().getSortingOrder() >= sub.getSortingOrder())
							return 1;
						else
							return 0;
					}else if(StringFunctions.isNotNullOrEmpty(sub.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(this.getStudySubjectId().getSubjectNumber())){
						return this.getStudySubjectId().getSubjectNumber().compareToIgnoreCase(sub.getSubjectNumber());
					}else{
						return this.getStudySubjectId().getName().compareToIgnoreCase(sub.getName());			
					}
			 }else 
				 return 0;
		}else
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("staffId", this.staffId.getId()).toString();
	}

	

	public void copyFrom(StaffElgibleSubjects subject)
    {
		setStaffId(subject.getStaffId());
		setStudySubjectId(subject.getStudySubjectId());
		setAcademicYear(subject.getAcademicYear());
    }
}
    

  

