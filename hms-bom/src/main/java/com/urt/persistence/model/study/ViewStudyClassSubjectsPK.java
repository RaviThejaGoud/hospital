package com.urt.persistence.model.study;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * ViewStudyClassSubjectsPK entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class ViewStudyClassSubjectsPK  implements java.io.Serializable {


    // Fields    

     private Long classId;
     private Long studyClassId;
     private String className;
     private String section;
     private Long custId;
     private Long academicYearId;
     private Long subjectId;
     private String subjectName;
     private String subjectNumber;
     private long sortingOrder;
     private long classSortingOrder;
     
     @Column(name = "classSortingOrder", columnDefinition="int(4) default 0")
     public long getClassSortingOrder() {
		return classSortingOrder;
	}
	public void setClassSortingOrder(long classSortingOrder) {
		this.classSortingOrder = classSortingOrder;
	}
	/**
 	 * @return the sortingOrder
 	 */
     @Column(name = "sortingOrder", columnDefinition="int(4) default 0")
 	public long getSortingOrder() {
 		return sortingOrder;
 	}
 	/**
 	 * @param sortingOrder the sortingOrder to set
 	 */
 	public void setSortingOrder(long sortingOrder) {
 		this.sortingOrder = sortingOrder;
 	}
     /**
 	 * @return the subjectNumber
 	 */
 	public String getSubjectNumber() {
 		return subjectNumber;
 	}
 	/**
 	 * @param subjectNumber the subjectNumber to set
 	 */
 	public void setSubjectNumber(String subjectNumber) {
 		this.subjectNumber = subjectNumber;
 	}
    // Constructors

    /** default constructor */
    public ViewStudyClassSubjectsPK() {
    }

    
    /** full constructor */
    public ViewStudyClassSubjectsPK(Long classId, Long studyClassId, String className, String section, Long custId, Long academicYearId, Long subjectId, String subjectName) {
        this.classId = classId;
        this.studyClassId = studyClassId;
        this.className = className;
        this.section = section;
        this.custId = custId;
        this.academicYearId = academicYearId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

   
    // Property accessors

    @Column(name="classId")

    public Long getClassId() {
        return this.classId;
    }
    
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Column(name="studyClassId")

    public Long getStudyClassId() {
        return this.studyClassId;
    }
    
    public void setStudyClassId(Long studyClassId) {
        this.studyClassId = studyClassId;
    }

    @Column(name="className")

    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name="section")

    public String getSection() {
        return this.section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }

    @Column(name="custId")

    public Long getCustId() {
        return this.custId;
    }
    
    public void setCustId(Long custId) {
        this.custId = custId;
    }

    @Column(name="academicYearId")

    public Long getAcademicYearId() {
        return this.academicYearId;
    }
    
    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Column(name="subjectId")

    public Long getSubjectId() {
        return this.subjectId;
    }
    
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name="subjectName")

    public String getSubjectName() {
        return this.subjectName;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
   



   @Override
public boolean equals(Object other) {
         if ( this == other  ) {
        	 return true;
         } else if ( other == null || !(other instanceof ViewStudyClassSubjectsPK) ) {
        	 return false;
         } else {		
			 ViewStudyClassSubjectsPK castOther = ( ViewStudyClassSubjectsPK ) other; 
	         
			 return  this.getClassId()==castOther.getClassId() || ( this.getClassId()!=null && castOther.getClassId()!=null && this.getClassId().equals(castOther.getClassId()) ) 
	 &&  this.getStudyClassId()==castOther.getStudyClassId() || ( this.getStudyClassId()!=null && castOther.getStudyClassId()!=null && this.getStudyClassId().equals(castOther.getStudyClassId()) ) 
	 &&  this.getClassName()==castOther.getClassName() || ( this.getClassName()!=null && castOther.getClassName()!=null && this.getClassName().equals(castOther.getClassName()) ) 
	 &&  this.getSection()==castOther.getSection() || ( this.getSection()!=null && castOther.getSection()!=null && this.getSection().equals(castOther.getSection()) ) 
	 &&  this.getCustId()==castOther.getCustId() || ( this.getCustId()!=null && castOther.getCustId()!=null && this.getCustId().equals(castOther.getCustId()) ) 
	 &&  this.getAcademicYearId()==castOther.getAcademicYearId() || ( this.getAcademicYearId()!=null && castOther.getAcademicYearId()!=null && this.getAcademicYearId().equals(castOther.getAcademicYearId()) ) 
	 &&  this.getSubjectId()==castOther.getSubjectId() || ( this.getSubjectId()!=null && castOther.getSubjectId()!=null && this.getSubjectId().equals(castOther.getSubjectId()) ) 
	 &&   this.getSubjectName()==castOther.getSubjectName() ||  this.getSubjectName()!=null && castOther.getSubjectName()!=null && this.getSubjectName().equals(castOther.getSubjectName()) ;
         }
   }
   
   @Override
public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getClassId() == null ? 0 : this.getClassId().hashCode() );
         result = 37 * result + ( getStudyClassId() == null ? 0 : this.getStudyClassId().hashCode() );
         result = 37 * result + ( getClassName() == null ? 0 : this.getClassName().hashCode() );
         result = 37 * result + ( getSection() == null ? 0 : this.getSection().hashCode() );
         result = 37 * result + ( getCustId() == null ? 0 : this.getCustId().hashCode() );
         result = 37 * result + ( getAcademicYearId() == null ? 0 : this.getAcademicYearId().hashCode() );
         result = 37 * result + ( getSubjectId() == null ? 0 : this.getSubjectId().hashCode() );
         result = 37 * result + ( getSubjectName() == null ? 0 : this.getSubjectName().hashCode() );
         return result;
   }   





}