package com.urt.persistence.model.study;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;


/**
 * ViewStudyClassSubjects entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="vw_studyClassSubjectDetails")

public class ViewStudyClassSubjects  implements java.io.Serializable,Comparable {


    // Fields    

    // private ViewStudyClassSubjectsPK id;
     private long classId;
     private long studyClassId;
     private String className;
     private String section;
     private long custId;
     private long academicYearId;
     private long subjectId;
     private String subjectName;
     private String subjectNumber;
     private long sortingOrder;
     private long classSortingOrder;
     private String subjectType;
    // Constructors

    /** default constructor */
    public ViewStudyClassSubjects() {
    }

    
    public String getSubjectType() {
		return subjectType;
	}


	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}


	/** full constructor */
   /* public ViewStudyClassSubjects(ViewStudyClassSubjectsPK id) {
        this.id = id;
    }*/

   
    // Property accessors
   /* @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="classId", column=@Column(name="classId") ), 
        @AttributeOverride(name="studyClassId", column=@Column(name="studyClassId") ), 
        @AttributeOverride(name="className", column=@Column(name="className") ), 
        @AttributeOverride(name="section", column=@Column(name="section") ), 
        @AttributeOverride(name="custId", column=@Column(name="custId") ), 
        @AttributeOverride(name="academicYearId", column=@Column(name="academicYearId") ), 
        @AttributeOverride(name="subjectId", column=@Column(name="subjectId") ), 
        @AttributeOverride(name="subjectName", column=@Column(name="subjectName") ),
        @AttributeOverride(name="subjectNumber", column=@Column(name="subjectNumber") ),
        @AttributeOverride(name="sortingOrder", column=@Column(name="sortingOrder") )
        } )*/
    
    /*@Id
    public ViewStudyClassSubjectsPK getId() {
        return this.id;
    }
    
    public void setId(ViewStudyClassSubjectsPK id) {
        this.id = id;
    }*/
   
    
    @Column(name="classId",unique=false)
	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long classId) {
		this.classId = classId;
	}

    @Column(name="studyClassId",unique=false)
	public Long getStudyClassId() {
		return studyClassId;
	}


	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}

    @Column(name="custId",unique=false)
	public Long getCustId() {
		return custId;
	}


	public void setCustId(Long custId) {
		this.custId = custId;
	}

    @Column(name="academicYearId",unique=false)
	public Long getAcademicYearId() {
		return academicYearId;
	}


	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Id
    @Column(name="subjectId",unique=false)
	public Long getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public String getSubjectNumber() {
		return subjectNumber;
	}


	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}


	public long getSortingOrder() {
		return sortingOrder;
	}


	public void setSortingOrder(long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}


	public long getClassSortingOrder() {
		return classSortingOrder;
	}


	public void setClassSortingOrder(long classSortingOrder) {
		this.classSortingOrder = classSortingOrder;
	}

	
	public int compareTo(Object object) {
		ViewStudyClassSubjects sub=(ViewStudyClassSubjects) object;
		if(sub.getClassSortingOrder() > 0 && this.getClassSortingOrder() > 0){
			if(this.getClassSortingOrder() > sub.getClassSortingOrder())
				return 1;
			else if(this.getClassSortingOrder() == sub.getClassSortingOrder())			
    				return 0;	
				else
					return -1;
		}
		return 0;
	}	
		/*if(sub.getSortingOrder() > 0 && this.getSortingOrder() > 0){
			if(this.getSortingOrder() >= sub.getSortingOrder())
				return 1;
			else
				return 0;
		}else if(StringFunctions.isNotNullOrEmpty(sub.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(this.getSubjectNumber())){
			return this.getSubjectNumber().compareToIgnoreCase(sub.getSubjectNumber());
		}else
			return this.getSubjectName().compareToIgnoreCase(sub.getSubjectName());			
	}*/

	@Transient
	 public String getClassAndSection()
    {
    	if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getClassName()+" - "+getSection();
    }
	@Transient
	 public String getClassSectionIdAndClassId()
	{
	   	return getStudyClassId()+"_"+getClassId();
   }

}