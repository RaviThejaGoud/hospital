package com.urt.persistence.model.exam;
// default package

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
@Table(name="vw_examScheduleDetails")
public class ViewExamScheduleDetails  implements java.io.Serializable,Comparable {

	private static final long serialVersionUID = 1L;
	
	private long classSectionId;
    private long academicYearId;
    private String className;
    private String section;
    private long examTypeId;
    private String examTypeName;
    private Date examDate;
    private Date endDate;
    private long custId;
    private long scheduleId;
    private long subTypeId;
    private String subTypeName;
    private boolean subTypeSchedule;
    private boolean scheduled;
    private long classId;
    private int classSortingOrder;
    private int examTypeSortingOrder;
    private int subTypeSortingOrder;
    private String classAndSection;
    private Date startDate;
    private double maxMarks;
    private String subjectName;
    private String startTime;
    private String endTime;
    
	public double getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	
	
    
    /** default constructor */
    public ViewExamScheduleDetails()
    {
    	
    }
    public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Id
    @Column(name="classSectionId", nullable=false,unique=false)

    public long getClassSectionId() {
        return this.classSectionId;
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

   
	@Column(name="section")

    public String getSection() {
        return this.section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }

    @Id
    @Column(name="examTypeId",unique=false)
    public long getExamTypeId() {
        return this.examTypeId;
    }
    
    public void setExamTypeId(long examTypeId) {
        this.examTypeId = examTypeId;
    }

    @Column(name="examTypeName")

    public String getExamTypeName() {
        return this.examTypeName;
    }
    
    public void setExamType(String examTypeName) {
        this.examTypeName = examTypeName;
    }
    @Column(name="examDate", length=19)
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ViewExamScheduleDetails))
			return false;
		
		return true;
	}
	
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
	    return 0;
	}
	
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	
	@Transient
	public String getExamDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getExamDate());
	}
	@Transient
    public String getEndDateStr() {
	    if(ObjectFunctions.isNullOrEmpty(this.getEndDate()))
	    	return "";
	    else
	    	return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getEndDate());
    }
	@Transient
    public String getStartDateStr() {
		if(ObjectFunctions.isNullOrEmpty(this.getStartDate()))
			return "";
		else
         return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getStartDate());
    }
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}