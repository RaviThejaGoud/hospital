package com.urt.persistence.model.study;
/********************************************************************
 * Copyright (C) 2005-06

 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
/ ********************************************************************/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.StaffVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Salary;
import com.urt.persistence.model.common.StaffStatutory;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.hostel.Bed;
import com.urt.persistence.model.user.User;

/**
 * Represents a person's full name
 */
@Entity
@Table(name = "staff")
public class Staff extends PersistentObject {
	    /**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	protected String qualification1;
	protected String qualification2;
	protected String status="Y";
	
	protected AcademicYear academicYear;
	protected long custId;
	protected Set<ClassTeacher> classTeachers;
	/*protected long totalLeaves;
	protected long casualLeaves;
	protected long sickLeaves;*/
	private User account;
	protected String supervisorId;
	protected boolean classTeacher;
	//protected String eligibleSubjects;
	//protected Set<StudySubject> studySubjectsList;
	protected Double salary;
	protected String staffType;
	protected long organizationTypeId;
	private String classNameAndSection;
	private Bed bedId;
	private long hostelCategoryId;
	protected List tempList;
	protected String description;
	
	protected List<Salary> salaryDetails;
	
	protected List<StaffStatutory> staffStatutories;
	//protected List<StaffLoanDetails> staffLoanDetails;
	private String staffPayrollMonth ;
	private String staffPayrollYear;
	//private String isTimetableUploaded;
	//protected List<Payroll> payrollDetails;
	private String outSideSchoolDuty;
	private Set<StudyClass> studyClasses;
	private String schoolMess;
	private String staffGrade;
	//private String timetableUploadedFilePath;
	private List<StaffTimetables> staffTimetables;
	
	

	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="staffId")
	public List<StaffTimetables> getStaffTimetables() {
		if(ObjectFunctions.isNullOrEmpty(this.staffTimetables))
		{
	    	  this.staffTimetables=new ArrayList<StaffTimetables>();
	    }
		return staffTimetables;
	}

	public void setStaffTimetables(List<StaffTimetables> staffTimetables) {
		this.staffTimetables = staffTimetables;
	}

	public String getStaffGrade() {
		return staffGrade;
	}

	public void setStaffGrade(String staffGrade) {
		this.staffGrade = staffGrade;
	}

	@Column(name = "schoolMess", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getSchoolMess() {
		return schoolMess;
	}

	public void setSchoolMess(String schoolMess) {
		this.schoolMess = schoolMess;
	} 
	 
	@ManyToMany
    @JoinTable(name = "staffMultipleStudyClasses",
       joinColumns = { @JoinColumn(name = "staffId") },
       inverseJoinColumns = { @JoinColumn(name = "StudyClassId") })
    public Set<StudyClass> getStudyClasses() {
		return studyClasses;
	}
	public void setStudyClasses(Set<StudyClass> studyClasses) {
		this.studyClasses = studyClasses;
	}
	
	
	@Column(name = "outSideSchoolDuty", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getOutSideSchoolDuty() {
		return outSideSchoolDuty;
	}

	public void setOutSideSchoolDuty(String outSideSchoolDuty) {
		this.outSideSchoolDuty = outSideSchoolDuty;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
      @JoinColumn(name="staffId")
     public List<StaffStatutory> getStaffStatutories() 
     {
		if(ObjectFunctions.isNullOrEmpty(this.staffStatutories))
		{
	    	  this.staffStatutories=new ArrayList<StaffStatutory>();
	    }
             return this.staffStatutories;
     }

     public void setStaffStatutories(List<StaffStatutory> staffStatutories) {
             this.staffStatutories = staffStatutories;
     }
	 
	public String getStaffPayrollMonth() {
		return staffPayrollMonth;
	}
	public void setStaffPayrollMonth(String staffPayrollMonth) {
		this.staffPayrollMonth = staffPayrollMonth;
	}
	public String getStaffPayrollYear() {
		return staffPayrollYear;
	}
	public void setStaffPayrollYear(String staffPayrollYear) {
		this.staffPayrollYear = staffPayrollYear;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="staffId") 
	public List<Salary> getSalaryDetails() {
		if(ObjectFunctions.isNullOrEmpty(this.salaryDetails))
		{
	    	  this.salaryDetails=new ArrayList<Salary>();
	    }
		return (List<Salary>) this.salaryDetails;
	}
	
	public void setSalaryDetails(List<Salary> salaryDetails) {
		this.salaryDetails = salaryDetails;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	/**
	 * @return the hostelCategoryId
	 */
	@Column(name = "hostelCategoryId",columnDefinition = "int default 0")
	public long getHostelCategoryId() {
		return hostelCategoryId;
	}
	/**
	 * @param hostelCategoryId the hostelCategoryId to set
	 */
	public void setHostelCategoryId(long hostelCategoryId) {
		this.hostelCategoryId = hostelCategoryId;
	}
	/**
	 * @return the bedId
	 */
	@OneToOne
	    @JoinColumn(name="bedId",nullable=true)
	public Bed getBedId() {
		return bedId;
	}
	/**
	 * @param bedId the bedId to set
	 */
	 
	public void setBedId(Bed bedId) {
		this.bedId = bedId;
	}
	
	/**
	 * @return the organizationTypeId
	 */
	@Column(name = "organizationTypeId", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getOrganizationTypeId() {
		return organizationTypeId;
	}

	/**
	 * @param organizationTypeId the organizationTypeId to set
	 */
	public void setOrganizationTypeId(long organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}

	public String getStaffType() {
		return this.staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/*public String getEligibleSubjects() {
		return this.eligibleSubjects;
	}

	public void setEligibleSubjects(String eligibleSubjects) {
		this.eligibleSubjects = eligibleSubjects;
	}*/

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="accountId", insertable=true, updatable=true, nullable=true)
	@PrimaryKeyJoinColumn(name = "accountId", referencedColumnName = "id")
	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
	}

	@Transient
	public String getFullPersonName() {
		if(ObjectFunctions.isNullOrEmpty(this.account))
		{
			return "";
		}
		return this.account.getFullPersonName();
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
    
	
	public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	public String getQualification2() {
		return qualification2;
	}

	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	/**
	 * @return the classTeachers
	 */
	@OneToMany
	@JoinColumn(name="teacherId") 
	public Set<ClassTeacher> getClassTeachers() {
		if(ObjectFunctions.isNullOrEmpty(this.classTeachers))
		{
			this.classTeachers = new HashSet<ClassTeacher>();
		}
		return this.classTeachers;
	}


	/**
	 * @param classTeachers the classTeachers to set
	 */
	public void setClassTeachers(Set<ClassTeacher> classTeachers) {
		this.classTeachers = classTeachers;
	}

	/**
	 * Constructor for staff.
	 */
	public Staff() {
		super();
	}
	
	public Staff(long id) {
        setId(id);
    }
	

	/*public Staff(long accountId, String qualification) {
		super();
		this.accountId = accountId;
		this.qualification = qualification;
	}*/
	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Staff)) {
			return false;
		} else {

			Staff staff = (Staff) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder
				.append(
					account,
					staff.getAccount())
				.append(qualification1, staff.getQualification1());
				

			return builder.isEquals();
		}
	}
  /*  public long getTotalLeaves() {
		return totalLeaves;
	}


	public void setTotalLeaves(long totalLeaves) {
		this.totalLeaves = totalLeaves;
	}*/
	
/*	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
*/
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId") 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	
	/*public long getCasualLeaves() {
		return casualLeaves;
	}

	public long getSickLeaves() {
		return sickLeaves;
	}

	public void setCasualLeaves(long casualLeaves) {
		this.casualLeaves = casualLeaves;
	}

	public void setSickLeaves(long sickLeaves) {
		this.sickLeaves = sickLeaves;
	}*/

	/**
	 * Formats the name as follows:
	 * [title ]firstName[ middleName] lastName[ suffix]
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		StringBuffer buffer = new StringBuffer(STRING_BUFFER_SIZE);
		if (!StringFunctions.isNullOrEmpty(qualification1)) {
			buffer.append(qualification1).append(" ");			
		}
		buffer.append(account);	
		buffer.append(this.getId());	
		return buffer.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(375130981, -1650712895)
                .append(this.account).append(this.account).append(
                        this.qualification1).append(this.qualification1).toHashCode();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        Staff staff = (Staff) object;
        if(ObjectFunctions.isNullOrEmpty(staff)){
        	return 0;
        }else
        	return this.getFullPersonName().compareToIgnoreCase(staff.getFullPersonName());
    }
    
    public void addClassTeacher(ClassTeacher classTeacher){
		if(ObjectFunctions.isNullOrEmpty(this.getClassTeachers())){
			this.classTeachers=new HashSet<ClassTeacher>();
		}
		this.classTeachers.add(classTeacher);
	}
	
	@Transient
    public String getEmpId()
    {
        return "E"+getStrId();
    }

	/**
	 * @return the classTeacher
	 */
	@Column(name = "classTeacher", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isClassTeacher() {
		return classTeacher;
	}

	/*public void setStudySubjectsList(Set<StudySubject> studySubjectsList) {
		this.studySubjectsList = studySubjectsList;
	}*/

	/**
	 * @param classTeacher the classTeacher to set
	 */
	public void setClassTeacher(boolean classTeacher) {
		this.classTeacher = classTeacher;
	}
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "staffElgibleSubjects", joinColumns = { @JoinColumn(name = "staffId") }, inverseJoinColumns = { @JoinColumn(name = "studySubjectId") })
    public Set<StudySubject> getStudySubjectsList() {
        if(studySubjectsList == null)
        {
        	studySubjectsList = new HashSet<StudySubject>();
        }
        return studySubjectsList;
    }*/
	 /*public void addStudySubject(StudySubject studySubject) {
		 getStudySubjectsList().add(studySubject);
	        
	    }*/
	@Transient
	public String getStaffTypeDesc() 
	{
		if ("C".equalsIgnoreCase(getStaffType())) {
			return "Temporary";
		} else {
			return "Permanent";
		}
	}

	/**
	 * @return the classNameAndSection
	 */
	@Transient
	public String getClassNameAndSection() {
		return classNameAndSection;
	}

	/**
	 * @param classNameAndSection the classNameAndSection to set
	 */
	public void setClassNameAndSection(String classNameAndSection) {
		this.classNameAndSection = classNameAndSection;
	}
	
	@Transient
	public List getTempList() {
		if(ObjectFunctions.isNullOrEmpty(this.tempList)){
			this.tempList=new ArrayList();
		}
		return this.tempList;
	}
	public void setTempList(List tempList) {
		this.tempList = tempList;
	}
	
	@Transient
	public void addSalaryDetailsSettings(Salary salaryDetails) {
		if(ObjectFunctions.isNullOrEmpty(salaryDetails)){
			salaryDetails=new Salary();
		}
		getSalaryDetails().add(salaryDetails);
	}
	
	@Transient
	public void addStaffStatutory(StaffStatutory staffStatutories) {
		if(ObjectFunctions.isNullOrEmpty(staffStatutories)){
			staffStatutories=new StaffStatutory();
		}
		getStaffStatutories().add(staffStatutories);
	}
	
	// For SMSAPP *** Ramarao
	@Transient
	public String getAdjustedAttachmentFilePath() {
	    if(!ObjectFunctions.isNullOrEmpty(getAccount().getProfileImage())) {
	        return getAccount().getProfileImage().getPath().concat(getAccount().getProfileImage().getThumbNail());
	    }
	    return UserImage.getStudyImageNotFoundFile();
	}
		
	@Transient
	public Staff copyFromVoToEntity(Staff staff, StaffVO staffVo)
	{
		if(staff.getId() == 0)
			staff.id = staffVo.id;
		staff.qualification1 = staffVo.qualification1;
		staff.custId = staffVo.custId;
		staff.qualification2 = staffVo.qualification2;
		staff.supervisorId = staffVo.supervisorId;
		staff.description = staffVo.description;
		staff.classTeacher = staffVo.classTeacher;
		staff.salary = staffVo.salary;
		staff.staffType = staffVo.staffType;
		staff.organizationTypeId = staffVo.organizationTypeId;
		staff.classNameAndSection = staffVo.classNameAndSection;
		staff.hostelCategoryId = staffVo.hostelCategoryId;
		staff.staffPayrollMonth = staffVo.staffPayrollMonth;
		staff.staffPayrollYear = staffVo.staffPayrollYear;
		//staff.isTimetableUploaded = staffVo.isTimetableUploaded;
		staff.outSideSchoolDuty = staffVo.outSideSchoolDuty;
		staff.schoolMess = staffVo.schoolMess;
		staff.staffGrade = staffVo.staffGrade;
		return staff;
	}
	@Transient
	public StaffVO copyFromEntityToVo(Staff staff)
	{
		StaffVO staffVo = new StaffVO();
		
		staffVo.id = staff.id;
		staffVo.qualification1 = staff.qualification1;
		staffVo.custId = staff.custId;
		staffVo.qualification2 = staff.qualification2;
		staffVo.supervisorId = staff.supervisorId;
		staffVo.description = staff.description;
		staffVo.classTeacher = staff.classTeacher;
		staffVo.salary = staff.salary;
		staffVo.staffType = staff.staffType;
		staffVo.organizationTypeId = staff.organizationTypeId;
		staffVo.classNameAndSection = staff.classNameAndSection;
		staffVo.hostelCategoryId = staff.hostelCategoryId;
		staffVo.staffPayrollMonth = staff.staffPayrollMonth;
		staffVo.staffPayrollYear = staff.staffPayrollYear;
		//staffVo.isTimetableUploaded = staff.isTimetableUploaded;
		staffVo.outSideSchoolDuty = staff.outSideSchoolDuty;
		staffVo.schoolMess = staff.schoolMess;
		staff.staffGrade = staffVo.staffGrade;
		return staffVo;
	}
	
	@Transient
	public void addStaffTimetables(StaffTimetables staffTimetables) {
		if(ObjectFunctions.isNullOrEmpty(staffTimetables)){
			staffTimetables=new StaffTimetables();
		}
		getStaffTimetables().add(staffTimetables);
	}
}