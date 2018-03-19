package com.urt.persistence.model.exam;
// default package

import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;


/**
 * ViewExamSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="vw_examSchedule")
public class ViewExamSchedule  implements java.io.Serializable,Comparable {
	private static final long serialVersionUID = 1L;
	
	private long classSectionId;
    private long academicYearId;
    private String className;
    private String section;
    private String subjectName;
    private long examTypeId;
    private String examType;
    private double minMarks;
    private double scheduleMaxMarks;
    private Date examDate;
    private String startTime;
    private String endTime;
    private long custId;
    private long scheduleId;
    private long subjectId;
    private long subTypeId;
    private String subTypeName;
    private boolean subTypeSchedule;
    private boolean scheduled;
    private long classId;
    private double examTypeMaxMarks;
    private boolean predefinedSubType;
    private String subjectNumber;
    private int subjectSortingOrder;
    private int classSortingOrder;
    private int examTypeSortingOrder;
    private int subTypeSortingOrder;
    private String subjectType;
    private boolean higherStandard;
    
    
    @Column(name = "higherStandard", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isHigherStandard() {
		return higherStandard;
	}
	/**
	 * @param higherStandard the higherStandard to set
	 */
	public void setHigherStandard(boolean higherStandard) {
		this.higherStandard = higherStandard;
	}
	
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
 	public int getSubjectSortingOrder() {
		return subjectSortingOrder;
	}
	public void setSubjectSortingOrder(int subjectSortingOrder) {
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
	
	/**
	 * @return the predefinedSubType
	 */
    @Column(name = "predefinedSubType", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isPredefinedSubType() {
		return predefinedSubType;
	}
	/**
	 * @param predefinedSubType the predefinedSubType to set
	 */
	public void setPredefinedSubType(boolean predefinedSubType) {
		this.predefinedSubType = predefinedSubType;
	}
    
    /** default constructor */
    public ViewExamSchedule()
    {
    	
    }
    public ViewExamSchedule(long classSectionId,long academicYearId,String className,String section,String subjectName,long examTypeId,String examType,double minMarks,double scheduleMaxMarks,Date examDate,String startTime,String endTime,long custId,long scheduleId,long subjectId,long subTypeId,String subTypeName,boolean subTypeSchedule,double maxMarks,String subjectType) {
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
         this.subTypeSchedule = subTypeSchedule;      
         this.subjectType = subjectType;
    }

   
   
    // Property accessors
/*    @Id
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="classSectionId", column=@Column(name="classSectionId", nullable=false) ), 
        @AttributeOverride(name="academicYearId", column=@Column(name="academicYearId") ), 
        @AttributeOverride(name="className", column=@Column(name="className") ), 
        @AttributeOverride(name="section", column=@Column(name="section") ), 
        @AttributeOverride(name="subjectName", column=@Column(name="subjectName") ), 
        @AttributeOverride(name="examTypeId", column=@Column(name="examTypeId") ), 
        @AttributeOverride(name="examType", column=@Column(name="examType") ), 
        @AttributeOverride(name="minMarks", column=@Column(name="minMarks") ), 
        @AttributeOverride(name="maxMarks", column=@Column(name="maxMarks") ), 
        @AttributeOverride(name="examDate", column=@Column(name="examDate", length=19) ), 
        @AttributeOverride(name="startTime", column=@Column(name="startTime") ), 
        @AttributeOverride(name="endTime", column=@Column(name="endTime") ), 
        @AttributeOverride(name="custId", column=@Column(name="custId", nullable=false) ), 
        @AttributeOverride(name="scheduleId", column=@Column(name="scheduleId", nullable=false) ), 
        @AttributeOverride(name="subjectId", column=@Column(name="subjectId") ), 
        @AttributeOverride(name="subTypeId", column=@Column(name="subTypeId") ), 
        @AttributeOverride(name="subTypeName", column=@Column(name="subTypeName") ), 
        @AttributeOverride(name="schedule", column=@Column(name="schedule", length=1) ) } )

    
    public ViewExamSchedulePK getId() {
        return this.id;
    }
    
    public void setId(ViewExamSchedulePK id) {
        this.id = id;
    }
  */ 
    
    @Id
    @Column(name="classSectionId", nullable=false,unique=false)

    public long getClassSectionId() {
        return this.classSectionId;
    }
    
    @Column(name="examTypeMaxMarks", nullable=false)
	public double getExamTypeMaxMarks() {
		return examTypeMaxMarks;
	}
	public void setExamTypeMaxMarks(double examTypeMaxMarks) {
		this.examTypeMaxMarks = examTypeMaxMarks;
	}
	
	public void setClassSectionId(long classSectionId) {
        this.classSectionId = classSectionId;
    }
	
	@Id
    @Column(name="academicYearId")
    public long getAcademicYearId() {
        return this.academicYearId;
    }
    
    public void setAcademicYearId(long academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Column(name="className")

    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    /**
	 * @return the subjectType
	 */
    @Column(name="subjectType")
	public String getSubjectType() {
		return subjectType;
	}
	/**
	 * @param subjectType the subjectType to set
	 */
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
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
    
    @Id
    @Column(name="examTypeId",unique=false)
    public long getExamTypeId() {
        return this.examTypeId;
    }
    
    public void setExamTypeId(long examTypeId) {
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
    
    @Id
    @Column(name="custId", nullable=false,unique=false)
    public long getCustId() {
        return this.custId;
    }
    
    public void setCustId(long custId) {
        this.custId = custId;
    }

    @Column(name="scheduleId", nullable=false)
    public long getScheduleId() {
        return this.scheduleId;
    }
    
    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Column(name="subjectId", nullable=false,unique=false)
    @Id
    public long getSubjectId() {
        return this.subjectId;
    }
    
    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }
    
    @Id
    @Column(name="subTypeId",nullable=false,unique=false)
    public long getSubTypeId() {
        return this.subTypeId;
    }
    
    public void setSubTypeId(long subTypeId) {
        this.subTypeId = subTypeId;
    }

    @Column(name="subTypeName")

    public String getSubTypeName() {
        return this.subTypeName;
    }
    
    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    @Column(name = "subTypeSchedule", nullable = true, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isSubTypeSchedule() {
		return subTypeSchedule;
	}
	public void setSubTypeSchedule(boolean subTypeSchedule) {
		this.subTypeSchedule = subTypeSchedule;
	}
	
	@Column(name = "scheduled", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isScheduled() {
		return scheduled;
	}
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}
	
	@Column(name="classId",nullable=false)
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (academicYearId ^ (academicYearId >>> 32));
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result
				+ (int) (classSectionId ^ (classSectionId >>> 32));
		result = prime * result + (int) (custId ^ (custId >>> 32));
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result
				+ ((examDate == null) ? 0 : examDate.hashCode());
		result = prime * result
				+ ((examType == null) ? 0 : examType.hashCode());
		result = prime * result + (int) (examTypeId ^ (examTypeId >>> 32));
		result = prime * result + (subTypeSchedule ? 1231 : 1237);
		result = prime * result + (int) (scheduleId ^ (scheduleId >>> 32));
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + (int) (subTypeId ^ (subTypeId >>> 32));
		result = prime * result
				+ ((subTypeName == null) ? 0 : subTypeName.hashCode());
		result = prime * result + (int) (subjectId ^ (subjectId >>> 32));
		result = prime * result
				+ ((subjectName == null) ? 0 : subjectName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ViewExamSchedule))
			return false;
		ViewExamSchedule other = (ViewExamSchedule) obj;
		if (academicYearId != other.academicYearId)
			return false;
		if (classSectionId != other.classSectionId)
			return false;
		if (custId != other.custId)
			return false;
		if (examTypeId != other.examTypeId)
			return false;
		if (subTypeSchedule != other.subTypeSchedule)
			return false;
		if (subTypeId != other.subTypeId)
			return false;
		if (subjectId != other.subjectId)
			return false;
		return true;
	}
	
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		ViewExamSchedule target = (ViewExamSchedule) object;
	    if (target.getSubjectName() != null && this.getSubjectName() != null)
	    {
	        if(this.getSubjectName().equalsIgnoreCase(target.getSubjectName()))
	            return 0;
	        else 
	           return this.getSubjectName().compareToIgnoreCase(target.getSubjectName());
	             
	    }
	    return 0;
	}
	@Transient
	public long getExamTypeMaxMarksLongType(){
		DecimalFormat twoDForm = new DecimalFormat("#");
		return Long.valueOf(twoDForm.format(this.examTypeMaxMarks));
	}
	@Transient
	public long getScheduleMaxMarksLongType(){
		DecimalFormat twoDForm = new DecimalFormat("#");
		return Long.valueOf(twoDForm.format(this.scheduleMaxMarks));
	}
	@Transient
	public String getClassAndSection(){
		if(ObjectFunctions.isNullOrEmpty(this.section))
			return this.className;
		else
			return this.className+"-"+this.section;
	}
	@Transient
	public String getExamDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getExamDate());
		
	}
	
	@Transient
    public String getExamTypeIdAndScheduleId(){
    	StringBuffer ids = new StringBuffer();
    	return ids.append(this.getExamTypeId()).append("_").append(this.getScheduleId()).toString();
    }
	
}