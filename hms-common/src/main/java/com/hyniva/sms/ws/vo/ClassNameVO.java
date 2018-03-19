package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;


public class ClassNameVO{
	
	    public String className; 
	    public int noOfSections;
	    public int sortingOrder;
	    public long id;
	    public List<StudyClassVO> studyClassList;
	    public String description;  
	    public long custId; 
	    public boolean admissionsOpen;
	    public int absentiesCount;
	    public int noOfStudents;
	    public AcademicYearVo academicYearVo;
	    public boolean higherStandard;
	    public int extendableClassCapacity;
	    public boolean entranceMarksUploaded;
	    
	    
	    
	    
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
		public boolean isAdmissionsOpen() {
			return admissionsOpen;
		}
		public void setAdmissionsOpen(boolean admissionsOpen) {
			this.admissionsOpen = admissionsOpen;
		}
		public int getAbsentiesCount() {
			return absentiesCount;
		}
		public void setAbsentiesCount(int absentiesCount) {
			this.absentiesCount = absentiesCount;
		}
		public int getNoOfStudents() {
			return noOfStudents;
		}
		public void setNoOfStudents(int noOfStudents) {
			this.noOfStudents = noOfStudents;
		}
		public AcademicYearVo getAcademicYearVo() {
			return academicYearVo;
		}
		public void setAcademicYearVo(AcademicYearVo academicYearVo) {
			this.academicYearVo = academicYearVo;
		}
		public boolean isHigherStandard() {
			return higherStandard;
		}
		public void setHigherStandard(boolean higherStandard) {
			this.higherStandard = higherStandard;
		}
		public int getExtendableClassCapacity() {
			return extendableClassCapacity;
		}
		public void setExtendableClassCapacity(int extendableClassCapacity) {
			this.extendableClassCapacity = extendableClassCapacity;
		}
		public boolean isEntranceMarksUploaded() {
			return entranceMarksUploaded;
		}
		public void setEntranceMarksUploaded(boolean entranceMarksUploaded) {
			this.entranceMarksUploaded = entranceMarksUploaded;
		}
		public List<StudyClassVO> getStudyClassList() {
			if(ObjectFunctions.isNullOrEmpty(this.studyClassList))
			{
				this.studyClassList = new ArrayList<StudyClassVO>(); 
			}
			return studyClassList;
		}
		public void setStudyClassList(List<StudyClassVO> studyClassList) {
			this.studyClassList = studyClassList;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public int getNoOfSections() {
			return noOfSections;
		}
		public void setNoOfSections(int noOfSections) {
			this.noOfSections = noOfSections;
		}
		public int getSortingOrder() {
			return sortingOrder;
		}
		public void setSortingOrder(int sortingOrder) {
			this.sortingOrder = sortingOrder;
		}
	    
	    
    
}
