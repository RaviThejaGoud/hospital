package com.hyniva.sms.ws.vo;
import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class StudyClassVO{
	
	public long id;
	public String section; 
	public int sectionCapacity;
	public List<StudySubjectVO> studySubjectList;
	public long classId;
	@ExcelField(position = 7)
	public String classAndSection;
	
	public String className;
	
	public String description;    
    public long custId; 
    //public ClassName classNameClassId;
    public long sectionsSize;
    public long availableSeates;
    private List classTeacherList;
   // public AcademicYear academicYear;
    //public List<Syllabus> syllabus;
    public int filledSeats;
    //private Medium mediumId;
    public String groupNumber; 
   // public Set<StudySubject> subjects;
   // public SyllabusType syllabusType;
   // private List<ViewClassWisePeriodsCountDetails> timeTablePeriodsDetails;
    public String educationType;
    //public String isClassTimetableUploaded;
   // public Set<ExamTypes> examTypes;
    public AcademicYearVo academicYearVo;
    public ClassNameVO classNameVo;
    public String classTimetableUploadedFilePath;
	
	
	
    
	public String getClassTimetableUploadedFilePath() {
		return classTimetableUploadedFilePath;
	}
	public void setClassTimetableUploadedFilePath(
			String classTimetableUploadedFilePath) {
		this.classTimetableUploadedFilePath = classTimetableUploadedFilePath;
	}
	public long getSectionsSize() {
		return sectionsSize;
	}
	public void setSectionsSize(long sectionsSize) {
		this.sectionsSize = sectionsSize;
	}
	public ClassNameVO getClassNameVo() {
		return classNameVo;
	}
	public void setClassNameVo(ClassNameVO classNameVo) {
		this.classNameVo = classNameVo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAvailableSeates() {
		return availableSeates;
	}
	public void setAvailableSeates(long availableSeates) {
		this.availableSeates = availableSeates;
	}
	public List getClassTeacherList() {
		return classTeacherList;
	}
	public void setClassTeacherList(List classTeacherList) {
		this.classTeacherList = classTeacherList;
	}
	public int getFilledSeats() {
		return filledSeats;
	}
	public void setFilledSeats(int filledSeats) {
		this.filledSeats = filledSeats;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public String getEducationType() {
		return educationType;
	}
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	/*public String getIsClassTimetableUploaded() {
		return isClassTimetableUploaded;
	}
	public void setIsClassTimetableUploaded(String isClassTimetableUploaded) {
		this.isClassTimetableUploaded = isClassTimetableUploaded;
	}*/
	public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}
	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public String getClassAndSection() {
		return classAndSection;
	}
	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<StudySubjectVO> getStudySubjectList() {
		if(ObjectFunctions.isNullOrEmpty(this.studySubjectList))
		{
			this.studySubjectList = new ArrayList<StudySubjectVO>(); 
		}
		return studySubjectList;
	}
	public void setStudySubjectList(List<StudySubjectVO> studySubjectList) {
		this.studySubjectList = studySubjectList;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getSectionCapacity() {
		return sectionCapacity;
	}
	public void setSectionCapacity(int sectionCapacity) {
		this.sectionCapacity = sectionCapacity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
