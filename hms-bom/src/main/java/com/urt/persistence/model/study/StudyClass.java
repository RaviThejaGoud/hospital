package com.urt.persistence.model.study;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AcademicYearVo;
import com.hyniva.sms.ws.vo.ClassNameVO;
import com.hyniva.sms.ws.vo.StudyClassVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Medium;
import com.urt.persistence.model.exam.ExamTypes;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "studyClass")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class StudyClass  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String description;    
    @ExcelField(position = 7)
    protected String className; 
    //@ExcelField(position = 7)
    protected String section; 
    protected long custId; 
    protected int sectionCapacity;
    protected ClassName classNameClassId;
    protected long sectionsSize;
    protected long availableSeates;
    private List classTeacherList;
    protected AcademicYear academicYear;
    //protected List<Syllabus> syllabus;
    protected int filledSeats;
    private Medium mediumId;
    protected String groupNumber; 
    protected Set<StudySubject> subjects;
    protected SyllabusType syllabusType;
    private List<ViewClassWisePeriodsCountDetails> timeTablePeriodsDetails;
    private String educationType;
    //private String isClassTimetableUploaded;
    protected Set<ExamTypes> examTypes;
    private SyllabusTypeSchoolCode syllabusTypeSchoolCode;
    private String classTimetableUploadedFilePath;
    
    private List<ViewClassSectionDetails> classSectionDetails;
    
    
    @OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="classSectionId",referencedColumnName="id")
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
	
	public String getClassTimetableUploadedFilePath() {
		return classTimetableUploadedFilePath;
	}
	public void setClassTimetableUploadedFilePath(
			String classTimetableUploadedFilePath) {
		this.classTimetableUploadedFilePath = classTimetableUploadedFilePath;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="syllabusTypeSchoolCodeId", insertable=true, updatable=true)
    public SyllabusTypeSchoolCode getSyllabusTypeSchoolCode() {
		return syllabusTypeSchoolCode;
	}
	public void setSyllabusTypeSchoolCode(
			SyllabusTypeSchoolCode syllabusTypeSchoolCode) {
		this.syllabusTypeSchoolCode = syllabusTypeSchoolCode;
	}
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "classSectionExamTypes", joinColumns = { @JoinColumn(name = "classSectionId") },
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
    
    /**
	 * @return the isClassTimetableUploaded
	 */
    /*@Column(name = "isClassTimetableUploaded", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getIsClassTimetableUploaded() {
		return isClassTimetableUploaded;
	}
	public void setIsClassTimetableUploaded(String isClassTimetableUploaded) {
		this.isClassTimetableUploaded = isClassTimetableUploaded;
	}*/
	
	@Column(name = "educationType", nullable = true, length = 50)
    public String getEducationType() {
		return educationType;
	}
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
    /*@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="classSectionId",referencedColumnName="id")*/
	@javax.xml.bind.annotation.XmlTransient()
    @Transient
    public List<ViewClassWisePeriodsCountDetails> getTimeTablePeriodsDetails() {
		return timeTablePeriodsDetails;
	}
	public void setTimeTablePeriodsDetails(
			List<ViewClassWisePeriodsCountDetails> timeTablePeriodsDetails) {
		this.timeTablePeriodsDetails = timeTablePeriodsDetails;
	}
	@OneToOne
    @JoinColumn(name="syllabusTypeId",nullable=true)
    public SyllabusType getSyllabusType() {
		return syllabusType;
	}
	public void setSyllabusType(SyllabusType syllabusType) {
		this.syllabusType = syllabusType;
	}
	
	/**
	 * @return the medium
	 */
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="mediumId", nullable=true)
	public Medium getMediumId() {
		return mediumId;
	}
	/**
	 * @param medium the medium to set
	 */
	public void setMediumId(Medium mediumId) {
		this.mediumId = mediumId;
	}
	/**
	 * @return the groupNumber
	 */
	public String getGroupNumber() {
		return groupNumber;
	}
	/**
	 * @param groupNumber the groupNumber to set
	 */
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	/*@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.MERGE})
	@JoinColumn(name="studyClassId") 
	public List<Syllabus> getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(List<Syllabus> syllabus) {
		this.syllabus = syllabus;
	}*/
	
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
	 * @return the classTeacherList
	 */
    @Transient
	public List getClassTeacherList() {
    	return getClassNameClassId().getClassTeacher();
		//return classTeacherList;
	}
	/**
	 * @param classTeacherList the classTeacherList to set
	 */
	public void setClassTeacherList(List classTeacherList) {
		this.classTeacherList = classTeacherList;
	}

	@Transient
    public long getAvailableSeates() {
		return availableSeates;
	}

	public void setAvailableSeates(long availableSeates) {
		this.availableSeates = availableSeates;
	}

	@Transient
    public long getSectionsSize() {
		return sectionsSize;
	}

	public void setSectionsSize(long sectionsSize) {
		this.sectionsSize = sectionsSize;
	}

	
	@OneToOne( fetch=FetchType.EAGER)
    @JoinColumn(name="classNameClassId")
	public ClassName getClassNameClassId() {
		return classNameClassId;
	}
	public void setClassNameClassId(ClassName classNameClassId) {
		this.classNameClassId = classNameClassId;
	}
	
	
   @Column(name = "sectionCapacity", columnDefinition="int(4) default 0")
	public int getSectionCapacity() {
		return sectionCapacity;
	}
	public void setSectionCapacity(int sectionCapacity) {
		this.sectionCapacity = sectionCapacity;
	}
	
	 @Column(name = "filledSeats", columnDefinition="int(4) default 0")
	public int getFilledSeats() {
		return filledSeats;
	}
	public void setFilledSeats(int filledSeats) {
		this.filledSeats = filledSeats;
	}




    /**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(Set<StudySubject> subjects) {
		this.subjects = subjects;
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
	 * @return the description
	 */
	@Transient
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
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	public StudyClass() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }
    
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "ClassSubject",
       joinColumns = { @JoinColumn(name = "studyClassId") },
       inverseJoinColumns = { @JoinColumn(name = "subjectId") })
    @javax.xml.bind.annotation.XmlTransient()
    public Set<StudySubject> getSubjects() {
        if(subjects == null)
        {
            subjects = new HashSet<StudySubject>();
        }
        return this.subjects;
    }

	 
    public StudyClass(long id) {
        setId(id);
    }

    @Override
	public int compareTo(Object object) {
    	StudyClass target = (StudyClass) object;
    	if (target != null && this != null && this.getClassNameClassId()!=null && target.getClassNameClassId()!=null)
        {
    		if(this.getClassNameClassId().getSortingOrder() > target.getClassNameClassId().getSortingOrder())
    				return 1;
    		
    		else if(this.getClassNameClassId().getSortingOrder() == target.getClassNameClassId().getSortingOrder())
    				return 0;
    		
    		else  return -1;
    		}
    		else
    			return 0;
	}

    @Override
	public boolean equals(Object o) {
		if(o != null && ((StudyClass)o).id == this.id){
			//if(((StudyClass)o).id == this.id)
				return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (int)this.id;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("Class Name", this.className).toString();
	}
	@Transient
	public long getClassId()
	{
		if (ObjectFunctions.isNullOrEmpty(getClassNameClassId()))
			return 0;
		
		return getClassNameClassId().getId();
	}
    @Transient
    public String getClassAndSection()
    {
    	if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getClassName().trim()+" - "+getSection().trim();
    }
    @Transient
	public String getSubjectsString(){
    	try{
    		StringBuffer subjectBuff=new StringBuffer();
	    	if(!ObjectFunctions.isNullOrEmpty(getSubjects())){
		    	Iterator subjectIterator=getSubjects().iterator();
		        for (Iterator<StudySubject> subjectObjectIterator=subjectIterator; subjectObjectIterator.hasNext();) {
		        	StudySubject subject=subjectObjectIterator.next();
		        	if(!subjectBuff.toString().equals("")){
		        		subjectBuff.append(",");
		        	}
		        	subjectBuff.append(subject.getName());	 
		        }  
		        subjectIterator=null;
	    	}
	    	return subjectBuff.toString();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return "";
    }
    
    public void  addSubject(StudySubject subject){
    	if(ObjectFunctions.isNullOrEmpty(getSubjects())){
    		this.subjects=new HashSet<StudySubject>();
    	}
    	getSubjects().add(subject);
    }

    /*public void addSyllabus(Syllabus syllabus) {
		if(ObjectFunctions.isNullOrEmpty(getSyllabus())){
			this.syllabus=new ArrayList<Syllabus>();
		}
		this.syllabus.add(syllabus);
	}*/
    
    @Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(this.academicYear)){
			return 0;
		}else
			return this.academicYear.getId();
	}
    @Transient
    public String getClassSectionAndMediumId()
    {
		if(StringFunctions.isNullOrEmpty(getClassAndSection()) && ObjectFunctions.isNullOrEmpty(getMediumId())){
			return "";
		}else if(StringFunctions.isNullOrEmpty(getClassAndSection())){
			return ":"+getMediumId().getId();
		}else if(ObjectFunctions.isNullOrEmpty(getMediumId()))
			return getClassAndSection();
		else
			return getClassAndSection()+":"+ getMediumId().getId();
    }
    @Transient
	public int getTotalPeriods()
	{
    	if(ObjectFunctions.isNullOrEmpty(getTimeTablePeriodsDetails()))
    		return 0;
    	else{
    		int total=0;
    		for(ViewClassWisePeriodsCountDetails periods: getTimeTablePeriodsDetails())
    		{
    			total+=periods.getPeriodsCount();
    		}
    		return total;
    	}
	}
    @Transient
	public int getTotalDays()
	{
    	if(ObjectFunctions.isNullOrEmpty(getTimeTablePeriodsDetails()))
    		return 0;
    	else{
    		int total=0;
    		return total;
    	}
	}
    @Transient
	public String getDayIds()
	{
    	if(ObjectFunctions.isNullOrEmpty(getTimeTablePeriodsDetails()))
    		return "(0)";
    	else{
    		StringBuffer dayIds= new StringBuffer();
    		dayIds.append("(");
    		for(ViewClassWisePeriodsCountDetails periods: getTimeTablePeriodsDetails())
    		{
    			dayIds.append(periods.getDayId()+",");
    		}
    		dayIds.append("0)");
    		return dayIds.toString();
    	}
	}
    @Transient
    public String getClassNameWithClassSectionId(){
    	StringBuffer res = new StringBuffer();
    	if(StringFunctions.isNotNullOrEmpty(getClassName()))
    		res.append(getClassName());
    	res.append(":").append(getId());
    	return res.toString();
    }
    @Transient
    public String getClassNameAndSectionWithClassSectionId(){
    	StringBuffer res = new StringBuffer();
    	if(StringFunctions.isNotNullOrEmpty(getClassAndSection()))
    		res.append(getClassAndSection());
    	res.append(":").append(getId());
    	return res.toString();
    }
    @Transient
    public String getClassSectionIdAndClassId(){
    	StringBuffer classSectionAndClassId = new StringBuffer();
    	classSectionAndClassId.append(this.getId()).append(":").append(this.getClassId());
    	return classSectionAndClassId.toString();
    }
    @Transient
    public int getClassNameSortingOrder(){
    	if(ObjectFunctions.isNullOrEmpty(this.classNameClassId))
    		return 0;
    	else
    		return this.classNameClassId.getSortingOrder();
    }
    
    
	public StudyClassVO copyFromEntityToVo(StudyClass studyClass,AcademicYearVo academicYearVo, ClassNameVO classNameVo)
	{
    	StudyClassVO studyClassVO = new StudyClassVO();
    	
    	studyClassVO.id = studyClass.id;
    	studyClassVO.description = studyClass.description;
		studyClassVO.className = studyClass.className;
		studyClassVO.section = studyClass.section;
		studyClassVO.custId = studyClass.custId;
		studyClassVO.sectionCapacity = studyClass.sectionCapacity;
		if(!ObjectFunctions.isNullOrEmpty(classNameVo))
			studyClassVO.classNameVo = classNameVo;
		studyClassVO.sectionsSize = studyClass.sectionsSize;
		studyClassVO.availableSeates = studyClass.availableSeates;
		if(!ObjectFunctions.isNullOrEmpty(academicYearVo))
			studyClassVO.academicYearVo = academicYearVo;
		studyClassVO.filledSeats = studyClass.filledSeats;
		/*if(!ObjectFunctions.isNullOrEmpty(academicYearVo))
			studyClassVO.mediumId = studyClass.mediumId;*/
		studyClassVO.groupNumber = studyClass.groupNumber;
		//studyClassVO.subjects = studyClass.subjects;
		//studyClassVO.syllabusType = studyClass.syllabusType;
		//studyClassVO.timeTablePeriodsDetails = studyClass.timeTablePeriodsDetails;
		studyClassVO.educationType = studyClass.educationType;
		//studyClassVO.isClassTimetableUploaded = studyClass.isClassTimetableUploaded;
		
		studyClassVO.classTimetableUploadedFilePath = studyClass.classTimetableUploadedFilePath;
		
		
		//studyClassVO.examTypes = studyClass.examTypes;
		
		return studyClassVO;
	}
	
	public StudyClass copyFromVoToEntity(StudyClass studyClass, StudyClassVO studyClassVO)
	{
		if(studyClass.getId() == 0)
			studyClass.id = studyClassVO.id;
		studyClass.description = studyClassVO.description;
		studyClass.className = studyClassVO.className;
		studyClass.section = studyClassVO.section;
		studyClass.custId = studyClassVO.custId;
		studyClass.sectionCapacity = studyClassVO.sectionCapacity;
		studyClass.sectionsSize = studyClassVO.sectionsSize;
		studyClass.availableSeates = studyClassVO.availableSeates;
		studyClass.filledSeats = studyClassVO.filledSeats;
		/*if(!ObjectFunctions.isNullOrEmpty(academicYearVo))
			studyClassVO.mediumId = studyClass.mediumId;*/
		studyClass.groupNumber = studyClassVO.groupNumber;
		//studyClassVO.subjects = studyClass.subjects;
		//studyClassVO.syllabusType = studyClass.syllabusType;
		//studyClassVO.timeTablePeriodsDetails = studyClass.timeTablePeriodsDetails;
		studyClass.educationType = studyClassVO.educationType;
		//studyClass.isClassTimetableUploaded = studyClassVO.isClassTimetableUploaded;
		studyClass.classTimetableUploadedFilePath = studyClassVO.classTimetableUploadedFilePath;
		
		return studyClass;
	}
	
	@Transient
    public String getSectionName()
    {
    	if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getSection().trim();
    }
	
	@Transient
	public String getSubjectNames() {
		StringBuilder subjNames=null;
		if(!ObjectFunctions.isNullOrEmpty(this.getSubjects()))
		{
			List<StudySubject> subjects=ConvertUtil.convertSetToList(this.getSubjects());
			Collections.sort(subjects);
			subjNames=new StringBuilder();
			int num=1;
			for(StudySubject subject : subjects){
				subjNames.append(num).append(".").append(" ").append(subject.getName()).append(" ");
				num++;
			}
			return subjNames.toString();
		}
		return " ";
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
	
}
    

  

