package com.urt.persistence.model.exam;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;


/**
 * ViewExamSchedulePK entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ViewExamSchedulePK  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long classSectionId;
     private Long academicYearId;
     private String className;
     private String section;
     private String subjectName;
     private Long examTypeId;
     private String examType;
     private double minMarks;
     private double scheduleMaxMarks;
     private Date examDate;
     private String startTime;
     private String endTime;
     private Long custId;
     private Long scheduleId;
     private Long subjectId;
     private Long subTypeId;
     private String subTypeName;
     private boolean schedule;
     private double examTypeMaxMarks;
     private String subjectNumber;
     private long subjectSortingOrder;
     private int classSortingOrder;
     private int examTypeSortingOrder;
     private int subTypeSortingOrder;

     
     @Column(name = "examTypeSortingOrder", columnDefinition="int(4) default 0")
     public int getExamTypeSortingOrder() {
 		return examTypeSortingOrder;
 	}
 	public void setExamTypeSortingOrder(int examTypeSortingOrder) {
 		this.examTypeSortingOrder = examTypeSortingOrder;
 	}
 	@Column(name = "subTypeSortingOrder", columnDefinition="int(4) default 0")
 	public int getSubTypeSortingOrder() {
 		return subTypeSortingOrder;
 	}
 	public void setSubTypeSortingOrder(int subTypeSortingOrder) {
 		this.subTypeSortingOrder = subTypeSortingOrder;
 	}
     
     @Column(name = "classSortingOrder", columnDefinition="int(4) default 0")
     public int getClassSortingOrder() {
 		return classSortingOrder;
	 }
	 public void setClassSortingOrder(int classSortingOrder) {
	 	this.classSortingOrder = classSortingOrder;
	 }
     
     /**
  	 * @return the sortingOrder
  	 */
     @Column(name = "subjectSortingOrder", columnDefinition="int(4) default 0")
  	public long getSubjectSortingOrder() {
 		return subjectSortingOrder;
 	}
 	public void setSubjectSortingOrder(long subjectSortingOrder) {
 		this.subjectSortingOrder = subjectSortingOrder;
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
  	
    @Column(name="examTypeMaxMarks", nullable=false)
 	public double getExamTypeMaxMarks() {
 		return examTypeMaxMarks;
 	}
 	public void setExamTypeMaxMarks(double examTypeMaxMarks) {
 		this.examTypeMaxMarks = examTypeMaxMarks;
 	}
    // Constructors

    /** default constructor */
    public ViewExamSchedulePK() {
    }

	/** minimal constructor */
    public ViewExamSchedulePK(Long academicYearId, Long custId, Long classSectionId, Long examTypeId) {
        this.classSectionId = classSectionId;
        this.custId = custId;
        this.examTypeId = examTypeId;
        this.academicYearId = academicYearId;
    }
    
    /** full constructor */
    public ViewExamSchedulePK(Long classSectionId, Long academicYearId, String className, String section, String subjectName, Long examTypeId, String examType, double minMarks, double scheduleMaxMarks, Date examDate, String startTime, String endTime, Long custId, Long scheduleId, Long subjectId, Long subTypeId, String subTypeName, boolean schedule) {
        this.classSectionId = classSectionId;
        this.academicYearId = academicYearId;
        this.className = className;
        this.section = section;
        this.subjectName = subjectName;
        this.examTypeId = examTypeId;
        this.examType = examType;
        this.minMarks = minMarks;
        this.scheduleMaxMarks = scheduleMaxMarks;
        this.examDate = examDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.custId = custId;
        this.scheduleId = scheduleId;
        this.subjectId = subjectId;
        this.subTypeId = subTypeId;
        this.subTypeName = subTypeName;
        this.schedule = schedule;
    }

   
    // Property accessors

    @Column(name="classSectionId", nullable=false)

    public Long getClassSectionId() {
        return this.classSectionId;
    }
    
    public void setClassSectionId(Long classSectionId) {
        this.classSectionId = classSectionId;
    }

    @Column(name="academicYearId")

    public Long getAcademicYearId() {
        return this.academicYearId;
    }
    
    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
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

    @Column(name="subjectName")

    public String getSubjectName() {
        return this.subjectName;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Column(name="examTypeId")

    public Long getExamTypeId() {
        return this.examTypeId;
    }
    
    public void setExamTypeId(Long examTypeId) {
        this.examTypeId = examTypeId;
    }

    @Column(name="examType")

    public String getExamType() {
        return this.examType;
    }
    
    public void setExamType(String examType) {
        this.examType = examType;
    }

    @Column(name="minMarks")
	public double getMinMarks() {
		return minMarks;
	}
	public void setMinMarks(double minMarks) {
		this.minMarks = minMarks;
	}

    @Column(name="scheduleMaxMarks",columnDefinition="double default 0.0")
	public double getScheduleMaxMarks() {
		return scheduleMaxMarks;
	}
	public void setScheduleMaxMarks(double scheduleMaxMarks) {
		this.scheduleMaxMarks = scheduleMaxMarks;
	}

    @Column(name="examDate", length=19)
	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
    

    @Column(name="startTime")

    public String getStartTime() {
        return this.startTime;
    }
    

	public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(name="endTime")

    public String getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name="custId", nullable=false)

    public Long getCustId() {
        return this.custId;
    }
    
    public void setCustId(Long custId) {
        this.custId = custId;
    }

    @Column(name="scheduleId", nullable=false)

    public Long getScheduleId() {
        return this.scheduleId;
    }
    
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Column(name="subjectId")

    public Long getSubjectId() {
        return this.subjectId;
    }
    
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name="subTypeId")

    public Long getSubTypeId() {
        return this.subTypeId;
    }
    
    public void setSubTypeId(Long subTypeId) {
        this.subTypeId = subTypeId;
    }

    @Column(name="subTypeName")

    public String getSubTypeName() {
        return this.subTypeName;
    }
    
    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    @Column(name = "schedule", nullable = true, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
   



   @Override
public boolean equals(Object other) {
         if ( this == other  ){
        	 return true;
         }else if ( other == null || !(other instanceof ViewExamSchedulePK)){
        	 return false;
         }else{
		
			 ViewExamSchedulePK castOther = ( ViewExamSchedulePK ) other; 
	         
			 return  this.getClassSectionId()==castOther.getClassSectionId() || ( this.getClassSectionId()!=null && castOther.getClassSectionId()!=null && this.getClassSectionId().equals(castOther.getClassSectionId()) ) 
					 &&  this.getAcademicYearId()==castOther.getAcademicYearId() || ( this.getAcademicYearId()!=null && castOther.getAcademicYearId()!=null && this.getAcademicYearId().equals(castOther.getAcademicYearId()) ) 
					 &&  this.getExamTypeId()==castOther.getExamTypeId() || ( this.getExamTypeId()!=null && castOther.getExamTypeId()!=null && this.getExamTypeId().equals(castOther.getExamTypeId()) ) 
					 &&  this.getCustId()==castOther.getCustId() || ( this.getCustId()!=null && castOther.getCustId()!=null && this.getCustId().equals(castOther.getCustId()) ) 
					 &&  this.getSubjectId()==castOther.getSubjectId() || ( this.getSubjectId()!=null && castOther.getSubjectId()!=null && this.getSubjectId().equals(castOther.getSubjectId()) ) 
					 &&  this.getSubTypeId()==castOther.getSubTypeId() ||  this.getSubTypeId()!=null && castOther.getSubTypeId()!=null && this.getSubTypeId().equals(castOther.getSubTypeId());
         }
   }
   

@Override
public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getClassSectionId() == null ? 0 : this.getClassSectionId().hashCode() );
         result = 37 * result + ( getAcademicYearId() == null ? 0 : this.getAcademicYearId().hashCode() );
         result = 37 * result + ( getClassName() == null ? 0 : this.getClassName().hashCode() );
         result = 37 * result + ( getSection() == null ? 0 : this.getSection().hashCode() );
         result = 37 * result + ( getSubjectName() == null ? 0 : this.getSubjectName().hashCode() );
         result = 37 * result + ( getExamTypeId() == null ? 0 : this.getExamTypeId().hashCode() );
         result = 37 * result + ( getExamType() == null ? 0 : this.getExamType().hashCode() );
         result = 37 * result + ( getExamDate() == null ? 0 : this.getExamDate().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getCustId() == null ? 0 : this.getCustId().hashCode() );
         result = 37 * result + ( getScheduleId() == null ? 0 : this.getScheduleId().hashCode() );
         result = 37 * result + ( getSubjectId() == null ? 0 : this.getSubjectId().hashCode() );
         result = 37 * result + ( getSubTypeId() == null ? 0 : this.getSubTypeId().hashCode() );
         result = 37 * result + ( getSubTypeName() == null ? 0 : this.getSubTypeName().hashCode() );
         return result;
   }   

}