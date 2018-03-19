package com.urt.persistence.model.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AcademicYearVo;
import com.hyniva.sms.ws.vo.ClassNameVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "class")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class ClassName  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String description;  
    @ExcelField(position = 3)
    protected String className; 
    protected long custId; 
    //protected Fee feeDetails;
    protected boolean admissionsOpen;
    protected int absentiesCount;
    protected int noOfSections;
    private int noOfStudents;
    protected AcademicYear academicYear;
    //protected Set<ExamTypes> examTypes;
    //private List<StudyClass> classSection;
    private List<ViewClassSectionTeacher> classSectionTeacher;
    private int sortingOrder;
    protected boolean higherStandard;
    private int extendableClassCapacity;
    protected boolean entranceMarksUploaded;
    private List<ViewClassSectionDetails> classSectionDetails;
    
    @Column(name = "entranceMarksUploaded", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
    public boolean isEntranceMarksUploaded() {
		return entranceMarksUploaded;
	}
	public void setEntranceMarksUploaded(boolean entranceMarksUploaded) {
		this.entranceMarksUploaded = entranceMarksUploaded;
	}
	@Column(name = "extendableClassCapacity", columnDefinition="int(4) default 0")
    public int getExtendableClassCapacity() {
		return extendableClassCapacity;
	}
	public void setExtendableClassCapacity(int extendableClassCapacity) {
		this.extendableClassCapacity = extendableClassCapacity;
	}
	/**
	 * @return the higherStandard
	 */
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
    @OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="classId",referencedColumnName="id")
	@javax.xml.bind.annotation.XmlTransient()  
    public List<ViewClassSectionTeacher> getClassSectionTeacher() {
    	if(ObjectFunctions.isNotNullOrEmpty(classSectionTeacher))
    		Collections.sort(classSectionTeacher);
		return classSectionTeacher;
	}
   
	public void setClassSectionTeacher(List<ViewClassSectionTeacher> classSectionTeacher) {
		this.classSectionTeacher = classSectionTeacher;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="classId",referencedColumnName="id")
	@javax.xml.bind.annotation.XmlTransient()
	public List<ViewClassSectionDetails> getClassSectionDetails() {
		if(ObjectFunctions.isNotNullOrEmpty(classSectionDetails))
    		Collections.sort(classSectionDetails);
		return classSectionDetails;
	}
	public void setClassSectionDetails(
			List<ViewClassSectionDetails> classSectionDetails) {
		this.classSectionDetails = classSectionDetails;
	}
	/*@Transient
	public List<StudyClass> getClassSection() {
    	if(ObjectFunctions.isNullOrEmpty(classSection)){
    		this.classSection = new ArrayList<StudyClass>();
    	}
		return this.classSection;
	}
	public void setClassSection(List<StudyClass> classSection) {
		this.classSection = classSection;
	}*/

    
	/*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "classExamTypes", joinColumns = { @JoinColumn(name = "classNameId") },
       inverseJoinColumns = { @JoinColumn(name = "examTypeId") })
    @javax.xml.bind.annotation.XmlTransient()  
    public Set<ExamTypes> getExamTypes() {
    	if(examTypes == null)
        {
    		examTypes = new HashSet<ExamTypes>();
        }
		return examTypes;
	}
	public void setExamTypes(Set<ExamTypes> examTypes) {
		this.examTypes = examTypes;
	}
	*/
	/**
     * @return the academicYear
     */
    //@OneToOne(fetch=FetchType.LAZY)
	@OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    
  /**
	 * @return the custId
	 */
	 @Column(name="custId", nullable=true, length=10, unique=false)
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
	 * @return the description
	 */
	@Column(name="description", nullable=true, length=10256, unique=false)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the className
	 */
	@Column(name="className", nullable=true, length=255, unique=false)
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	
	public ClassName() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }
    @Transient	 
    public int getAbsentiesCount() {
		return absentiesCount;
	}

	/**
	 * @return the sortingOrder
	 */
    @Column(name = "sortingOrder", columnDefinition="int(4) default 0")
	public int getSortingOrder() {
		return sortingOrder;
	}
	/**
	 * @param sortingOrder the sortingOrder to set
	 */
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
	public void setAbsentiesCount(int absentiesCount) {
		this.absentiesCount = absentiesCount;
	}

	public ClassName(long id) {
        setId(id);
    }

	//changed by seshu
    @Override
	public int compareTo(Object object) {
    	ClassName target = (ClassName) object;
    	if (target != null && this != null)
        {
    		if(this.getSortingOrder() > target.getSortingOrder())
    			return 1;
    		else if(this.getSortingOrder() == target.getSortingOrder())
    			return 0;
    		else
    			return -1;
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
		// TODO Auto-generated method stub
		return "";
	}
  
	/*@Transient
	public Fee getFeeDetails() {
		if(ObjectFunctions.isNullOrEmpty(this.feeDetails)){
			this.feeDetails=new Fee();
		}
		return feeDetails;
	}
	public void setFeeDetails(Fee feeDetails) {
		this.feeDetails = feeDetails;
	}*/
	
	/**
	 * @return the admissionsOpen
	 */
	@Column(name = "admissionsOpen", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isAdmissionsOpen() {
		return admissionsOpen;
	}
	

	/**
	 * @param admissionsOpen the admissionsOpen to set
	 */
	public void setAdmissionsOpen(boolean admissionsOpen) {
		this.admissionsOpen = admissionsOpen;
	}
	@Transient
	public int getNoOfStudents() {
		return noOfStudents;
	}

	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	
	public void copyFrom(ClassName obj)
    {
		 setDescription(obj.getDescription());    
		 setClassName(obj.getClassName());
		 setNoOfSections(obj.getNoOfSections());
		 setCustId(obj.getCustId()); 
		 setSortingOrder(obj.getSortingOrder());
		 setNoOfStudents(obj.getNoOfStudents());
		 setAcademicYear(obj.getAcademicYear());
		 setCreatedDate(new Date());
		 setLastAccessDate(new Date());
		 
    }
	/**
	 * @return the noOfSections
	 */
	@Column(name = "noOfSections", columnDefinition="int(4) default 0")
	public int getNoOfSections() {
		return noOfSections;
	}
	/**
	 * @param noOfSections the noOfSections to set
	 */
	public void setNoOfSections(int noOfSections) {
		this.noOfSections = noOfSections;
	}
	@Transient
	public int getClassCapacity()
	{
		String section="";
		int total=0;
		String sc="";
		for(ViewClassSectionDetails ct: getClassSectionDetails())
		{
			sc=ct.getSection();
			if(StringFunctions.isNullOrEmpty(sc))
				sc="*";
			
			if(section.indexOf(sc) == -1)
			{
				total += ct.getSectionCapacity();
				section = "," + sc;
			}
		}
		return total;
	}
	@Transient
	public int getClassSectionAvailableSeats()
	{
		String section="";
		int total=0;
		String sc="";
		for(ViewClassSectionDetails ct: getClassSectionDetails())
		{
			sc=ct.getSection();
			if(StringFunctions.isNullOrEmpty(sc))
				sc="*";
			if(section.indexOf(sc) == -1)
			{
				total += ct.getAvailableSeats();
				section = "," + sc;
			}
		}
		return total;
	}
	@Transient
	public List getClassTeacher()
	{
		List<String> cl = new ArrayList<String>();
		Set<String> classTeachers=new HashSet<String>();
		if(!ObjectFunctions.isNullOrEmpty(getClassSectionTeacher())){
			for(ViewClassSectionTeacher ct: getClassSectionTeacher())
			{
				if("Y".equalsIgnoreCase(ct.getId().getStatus()))
				{
					if(ct.getId().isClassTeacher()){
						if(StringFunctions.isNullOrEmpty(ct.getId().getSection()))
							classTeachers.add( ct.getId().getFirstName()+" " + ct.getId().getLastName());
						else
							classTeachers.add(ct.getId().getSection() + " : " + ct.getId().getFirstName()+" " + ct.getId().getLastName());
					}
				}
			}
		}
		if(ObjectFunctions.isNotNullOrEmpty(classTeachers)){
			cl.addAll(classTeachers);
			Collections.sort(cl, String.CASE_INSENSITIVE_ORDER);
		}
		return cl;
	}
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(this.academicYear)){
			return 0;
		}else
			return this.academicYear.getId();
	}
	
	@Transient
	public boolean isClassHavingStudents(){
		if(ObjectFunctions.isNotNullOrEmpty(getClassSectionDetails())){
			for(ViewClassSectionDetails ct: getClassSectionDetails())
			{
				if(ct.getFilledSeats() > 0)
					return true;
			}
		}
		return false;
	} 
	@Transient
	public int getClassFilledSeatsCount(){
		String section="";
		int total=0;
		String sc="";
		for(ViewClassSectionDetails ct: getClassSectionDetails())
		{
			sc=ct.getSection();
			if(StringFunctions.isNullOrEmpty(sc))
				sc="*";
			if(section.indexOf(sc) == -1)
			{
				total += ct.getFilledSeats();
				section = "," + sc;
			}
		}
		return total;
	}
	
	
	public ClassNameVO copyFromEntityToVo(ClassName className,AcademicYearVo academicYearVo)
	{
		ClassNameVO classNameVO = new ClassNameVO();
		
		classNameVO.id = className.id;
		classNameVO.description = className.description;
		classNameVO.className = className.className;
		classNameVO.custId = className.custId;
		classNameVO.admissionsOpen = className.admissionsOpen;
		classNameVO.absentiesCount = className.absentiesCount;
		classNameVO.noOfSections = className.noOfSections;
		classNameVO.noOfStudents = className.noOfStudents;
		if(!ObjectFunctions.isNullOrEmpty(academicYearVo))
			classNameVO.academicYearVo = academicYearVo;
		//classNameVO.classSectionTeacher = className.classSectionTeacher;
		classNameVO.sortingOrder = className.sortingOrder;
		classNameVO.higherStandard = className.higherStandard;
		classNameVO.extendableClassCapacity = className.extendableClassCapacity;
		classNameVO.entranceMarksUploaded = className.entranceMarksUploaded;
		//classNameVO.classSectionDetails = className.classSectionDetails;
		
		return classNameVO;
	}
	
	
	public ClassName copyFromVoToEntity(ClassName className,ClassNameVO classNameVO)
	{
		if(className.getId() == 0)
			className.id = classNameVO.id;
		className.description = classNameVO.description;
		className.className = classNameVO.className;
		className.custId = classNameVO.custId;
		className.admissionsOpen = classNameVO.admissionsOpen;
		className.absentiesCount = classNameVO.absentiesCount;
		className.noOfSections = classNameVO.noOfSections;
		className.noOfStudents = classNameVO.noOfStudents;
		//classNameVO.classSectionTeacher = className.classSectionTeacher;
		className.sortingOrder = classNameVO.sortingOrder;
		className.higherStandard = classNameVO.higherStandard;
		className.extendableClassCapacity = classNameVO.extendableClassCapacity;
		className.entranceMarksUploaded = classNameVO.entranceMarksUploaded;
		//classNameVO.classSectionDetails = className.classSectionDetails;
		
		return className;
	}
}
    

  

