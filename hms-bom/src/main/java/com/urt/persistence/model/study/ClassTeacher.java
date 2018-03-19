package com.urt.persistence.model.study;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;


@Entity
@Table(name = "classTeacher")
public class ClassTeacher  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected  StudyClass studyClass;
     
    protected StudySubject studySubject;
    
    protected long custId;
    
    protected Staff  staff;
    
    protected boolean classTeacher; // is class teacher 
    protected AcademicYear academicYear;
    private int periodsCount;
    
    @Column(name = "periodsCount", nullable = false, columnDefinition="int default 0")
    public int getPeriodsCount() {
		return periodsCount;
	}
	public void setPeriodsCount(int periodsCount) {
		this.periodsCount = periodsCount;
	}
	/**
     * @return the academicYear
     */
    @OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
   

	/**
	 * @return the staff
	 */
    @ManyToOne
	@JoinColumn(name="teacherId")
	@PrimaryKeyJoinColumn(name = "teacherId", referencedColumnName = "id")
	public Staff getStaff() {
		return staff;
	}
	/**
	 * @param staff the staff to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	
    
    public ClassTeacher() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
        this.classTeacher = Boolean.FALSE;
    }

    public ClassTeacher(long id) {
        setId(id);
    }
 
    /**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Override
	public int compareTo(Object object) {
		ClassTeacher target = (ClassTeacher) object;
        if (target.getStudyClass().getClassAndSection() != null && this.getStudyClass().getClassAndSection() != null)
        {
            if(this.getStudyClass().getClassAndSection().equalsIgnoreCase(target.getStudyClass().getClassAndSection())){
            	if(!ObjectFunctions.isNullOrEmpty(this.getStudySubject()) && !ObjectFunctions.isNullOrEmpty(target.getStudySubject())){
            		if(this.getStudySubject().getSortingOrder() >= target.getStudySubject().getSortingOrder())
    					return 1;
                	else
                		return 0;
            	}
            }
            else 
               return this.getStudyClass().getClassAndSection().compareToIgnoreCase(target.getStudyClass().getClassAndSection());
                 
        }
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
        .append("custId", this.custId)
        .append("classTeacher",
                this.classTeacher).append("Staff",
                  this.getStaff()).append("StudyClass",
                        this.getStudyClass()).append("StudySubject",
                                this.getStudySubject()).toString();
	}
	/**
	 * @return the studyClass
	 */
	//@ManyToOne(cascade=CascadeType.ALL)
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "studyClassId", referencedColumnName = "id")
	public StudyClass getStudyClass() {
		return studyClass;
	}

	/**
	 * @param studyClass the studyClass to set
	 */
	public void setStudyClass(StudyClass studyClass) {
		this.studyClass = studyClass;
	}

	/**
	 * @return the studySubject
	 */
	 @ManyToOne
     @JoinColumn(name = "studySubjectId", referencedColumnName = "id")
	public StudySubject getStudySubject() {
		return studySubject;
	}

	/**
	 * @param studySubject the studySubject to set
	 */
	public void setStudySubject(StudySubject studySubject) {
		this.studySubject = studySubject;
	}
	
	@Transient
    public String getIsClassTeacher(){
		if(isClassTeacher())
		{
			return "Yes";
		}
		else
		{
			return "No";
		}
    }
	public void copyFrom(ClassTeacher obj)
    {
		setStudyClass(obj.getStudyClass());
	    setStudySubject(obj.getStudySubject());
	    setCustId(obj.getCustId());
	    setStaff(obj.getStaff());
	    setClassTeacher(obj.isClassTeacher());
	    setAcademicYear(obj.getAcademicYear());
        setCreatedDate(new Date());
        setLastAccessDate(new Date());
    }
	/**
	 * @return the classTeacher
	 */
	@Column(name = "classTeacher", nullable = true, length = 1, columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
   
	public boolean isClassTeacher() {
		return classTeacher;
	}
	/**
	 * @param classTeacher the classTeacher to set
	 */
	public void setClassTeacher(boolean classTeacher) {
		this.classTeacher = classTeacher;
	}
	
	@Transient
	public String getSubjectName() {
		if(ObjectFunctions.isNullOrEmpty(this.getStudySubject()))
		{
			return "";
		}
		return this.getStudySubject().getName();
	}
	
	@Transient
	public String getFullPersonName() {
		if(ObjectFunctions.isNullOrEmpty(this.staff))
		{
			return "";
		}else if(ObjectFunctions.isNullOrEmpty(this.staff.getAccount()))
		{
			return "";
		}else
			return this.staff.getAccount().getFullPersonName();
	}
	@Transient
	public long getStudyClassId(){
		if(ObjectFunctions.isNullOrEmpty(this.studyClass))
		{
			return 0;
		}
		return this.studyClass.getId();
	}
	@Transient
	public long getStudySubjectId(){
		if(ObjectFunctions.isNullOrEmpty(this.studySubject))
		{
			return 0;
		}
		return this.studySubject.getId();
	}
	@Transient
	public String getStudyClassName(){
		if(ObjectFunctions.isNullOrEmpty(this.studyClass))
		{
			return "";
		}
		return this.studyClass.getClassName();
	}
	@Transient
	public String getStudyClassSection(){
		if(ObjectFunctions.isNullOrEmpty(this.studyClass))
		{
			return "";
		}
		return this.studyClass.getSection();
	}
	@Transient
	public String getStudySubjectName(){
		if(ObjectFunctions.isNullOrEmpty(this.studySubject))
		{
			return "";
		}
		return this.studySubject.getName();
	}
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(this.academicYear))
		{
			return 0;
		}
		return this.academicYear.getId();
	}
	@Transient
	public String getStaffActiveStatus(){
		if(ObjectFunctions.isNullOrEmpty(this.staff))
		{
			return "N";
		}
		return this.staff.getStatus();
	}
	
	@Transient
	public String getClassTeatherName(){
		if(ObjectFunctions.isNullOrEmpty(getStaff()))
		{
			return null;
		}
		if(ObjectFunctions.isNullOrEmpty(getStaff().getAccount())){
			//long classTeacherAccountId =getStaff().getAccount().getId();
			return null;
		}
		return this.getStaff().getAccount().getUsername()+"("+getStaff().getAccount().getUserRoleDescription()+")";
	}
	@Transient
	public long getStaffId(){
		if(ObjectFunctions.isNullOrEmpty(this.staff))
			return 0;
		else
			return this.staff.getId();
	}
}
