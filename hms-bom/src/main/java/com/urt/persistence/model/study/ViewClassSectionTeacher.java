package com.urt.persistence.model.study;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;


/**
 * ViewClassSectionTeacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="vw_classSectionTeacher")

public class ViewClassSectionTeacher  implements java.io.Serializable,Comparable {


    // Fields    
	private static final long serialVersionUID = 3832626162173359411L;
     private ViewClassSectionTeacherPK id;


    // Constructors

    /** default constructor */
    public ViewClassSectionTeacher() {
    }

    
    /** full constructor */
    public ViewClassSectionTeacher(ViewClassSectionTeacherPK id) {
        this.id = id;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="classId", column=@Column(name="classId", nullable=false) ), 
        @AttributeOverride(name="classSectionId", column=@Column(name="classSectionId") ), 
        @AttributeOverride(name="filledSeats", column=@Column(name="filledSeats") ), 
        @AttributeOverride(name="sectionCapacity", column=@Column(name="sectionCapacity") ), 
        @AttributeOverride(name="academicYearId", column=@Column(name="academicYearId") ), 
        @AttributeOverride(name="className", column=@Column(name="className") ), 
        @AttributeOverride(name="section", column=@Column(name="section") ), 
        @AttributeOverride(name="teacherId", column=@Column(name="teacherId") ), 
        @AttributeOverride(name="classTeacher", column=@Column(name="classTeacher", length=1) ), 
        @AttributeOverride(name="subjectId", column=@Column(name="subjectId") ), 
        @AttributeOverride(name="firstName", column=@Column(name="firstName", length=128) ), 
        @AttributeOverride(name="lastName", column=@Column(name="lastName", length=128) ), 
        @AttributeOverride(name="mobileNumber", column=@Column(name="mobileNumber", length=50) ), 
        @AttributeOverride(name="accountId", column=@Column(name="accountId") ),
        @AttributeOverride(name="username", column=@Column(name="username", length=128) ) } )

    @Id
    public ViewClassSectionTeacherPK getId() {
        return this.id;
    }
    
    public void setId(ViewClassSectionTeacherPK id) {
        this.id = id;
    }
   
   @Transient
   public int getAvailableSeats(){
	   return this.id.getSectionCapacity() - this.getId().getFilledSeats();
   }

   public int compareTo(Object object) {
	   ViewClassSectionTeacher target = (ViewClassSectionTeacher) object;
   	if (target != null && this != null && this.id != null && target.id != null)
       {
   		if(this.id.getClassSortingOrder() > target.id.getClassSortingOrder())
				return 1;
			else if(this.id.getClassSortingOrder() == target.id.getClassSortingOrder()){
				if(this.id.getSection().compareTo(target.id.getSection()) > 0)
					return 1;
			}
			else
				return 0;
       }
		return 0;
	}
   @Transient
	public String getStaffFullName(){
		StringBuffer staffName = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(this.id.getFirstName()))
			staffName.append(this.id.getFirstName());
		if(StringFunctions.isNotNullOrEmpty(this.id.getLastName())){
			if(staffName.length() > 0)
				staffName.append(" ");
			staffName.append(this.id.getLastName());
		}
		return staffName.toString();
	}
	@Transient 
	public String getClassAndSection(){
		StringBuffer classAndSection = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(this.id.getClassName()))
			classAndSection.append(this.id.getClassName());
		if(StringFunctions.isNotNullOrEmpty(this.id.getSection())){
			if(classAndSection.length() > 0)
				classAndSection.append(" ");
			classAndSection.append(this.id.getSection());
		}
		return classAndSection.toString();
	}
	@Transient
	public String getClassTeacherName(){
		if(this.id.isClassTeacher()){
			return getStaffFullName();
		}
		return null;
	}
	@Transient
	public String getFullName(){
		StringBuffer staffName = new StringBuffer();
		if(StringFunctions.isNotNullOrEmpty(this.id.getFirstName()))
			staffName.append(this.id.getFirstName());
		if(StringFunctions.isNotNullOrEmpty(this.id.getLastName())){
			if(staffName.length() > 0)
				staffName.append(" ");
			staffName.append(this.id.getLastName());
		}
		return staffName.toString();
	}
	@Transient
	public long getAccountId(){
		if(!ObjectFunctions.isNullOrEmpty(this.id)){
			return this.id.getAccountId();
		}
		return 0;
	}
	@Transient
	public long getClassSectionId(){
		if(!ObjectFunctions.isNullOrEmpty(this.id)){
			return this.id.getClassSectionId();
		}
		return 0;
	}
}