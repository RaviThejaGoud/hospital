package com.urt.persistence.model.study;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;


/**
 * ViewClassSectionTeacherPK entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class ViewClassSectionTeacherPK implements java.io.Serializable {


    // Fields    

     private Long classId;
     private Long classSectionId;
     private Integer filledSeats;
     private Integer sectionCapacity;
     private Long academicYearId;
     private String className;
     private String section;
     private Long teacherId;
     private boolean classTeacher;
     private Long subjectId;
     private String firstName;
     private String lastName;
     private String mobileNumber;
     private String username;
     private long accountId;
     private int classSortingOrder;
     private String status;


    // Constructors

    /** default constructor */
    public ViewClassSectionTeacherPK() {
    }

	/** minimal constructor */
    public ViewClassSectionTeacherPK(Long classId) {
        this.classId = classId;
    }
    
    /** full constructor */
    public ViewClassSectionTeacherPK(Long classId, Long classSectionId, Integer filledSeats, Integer sectionCapacity, Long academicYearId, String className, String section, Long teacherId, boolean classTeacher, Long subjectId, String firstName, String lastName, String mobileNumber, String username,String status) {
        this.classId = classId;
        this.classSectionId = classSectionId;
        this.filledSeats = filledSeats;
        this.sectionCapacity = sectionCapacity;
        this.academicYearId = academicYearId;
        this.className = className;
        this.section = section;
        this.teacherId = teacherId;
        this.classTeacher = classTeacher;
        this.subjectId = subjectId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.status = status;
    }

   
    
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Property accessors
    public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
	
    public int getClassSortingOrder() {
		return classSortingOrder;
	}

	public void setClassSortingOrder(int classSortingOrder) {
		this.classSortingOrder = classSortingOrder;
	}

	@Column(name="classId", nullable=false)

    public Long getClassId() {
        return this.classId;
    }
    
	public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Column(name="classSectionId")

    public Long getClassSectionId() {
        return this.classSectionId;
    }
    
    public void setClassSectionId(Long classSectionId) {
        this.classSectionId = classSectionId;
    }

    @Column(name="filledSeats")

    public Integer getFilledSeats() {
        return this.filledSeats;
    }
    
    public void setFilledSeats(Integer filledSeats) {
        this.filledSeats = filledSeats;
    }

    @Column(name="sectionCapacity")

    public Integer getSectionCapacity() {
        return this.sectionCapacity;
    }
    
    public void setSectionCapacity(Integer sectionCapacity) {
        this.sectionCapacity = sectionCapacity;
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

    @Column(name="teacherId")

    public Long getTeacherId() {
        return this.teacherId;
    }
    
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    

    @Column(name="subjectId")

    public Long getSubjectId() {
        return this.subjectId;
    }
    
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name="firstName", length=128)

    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="lastName", length=128)

    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="mobileNumber", length=50)

    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name="username", length=128)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
   



  
/**
 * @return the classTeacher
 */
   
   @Column(name = "classTeacher", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
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
		
		public boolean equals(Object o) 
		{
	        
	        return true;
	    }

	    public int hashCode() {
	        int result = 0;
	        
	        return result;
	    }

}