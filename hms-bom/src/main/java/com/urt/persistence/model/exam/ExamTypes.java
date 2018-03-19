/**
 * 
 */
package com.urt.persistence.model.exam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.StudyClass;

/**
 * @author urtuser
 *
 */
@Entity
@Table(name = "examTypes")
public class ExamTypes extends PersistentObject{

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private long custId;
	private String examType;
    private String minMarks;
    private String maxMarks;
    protected AcademicYear academicYear;
    //protected Set<ClassName> classes;
    private Integer noOfDays;
    private String mailContentDesc;
    private String mobileContentDesc;
    private int sortingOrder;
    private String selectedClassIds;
    private List tempList;
    protected Set<SubType> examSubTypes;
    private String selectedExamSubTypeIds;
    protected Set<StudyClass> studyClasses;
    
    private long examTypeStateId;
    
    @Column(name = "examTypeStateId", columnDefinition="int(4) default 0")
    public long getExamTypeStateId() {
		return examTypeStateId;
	}
	public void setExamTypeStateId(long examTypeStateId) {
		this.examTypeStateId = examTypeStateId;
	}
	/**
	 * @return the tempList
	 */
    @Transient
	public List getTempList() {
		return tempList;
	}
	/**
	 * @param tempList the tempList to set
	 */
	public void setTempList(List tempList) {
		this.tempList = tempList;
	}
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
    
    @Column(name = "mobileContentDesc", nullable = true, length = 160)
	public String getMobileContentDesc() {
		return mobileContentDesc;
	}
	public void setMobileContentDesc(String mobileContentDesc) {
		this.mobileContentDesc = mobileContentDesc;
	}
    @Column(name = "noOfDays", nullable = true, length = 20)
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
    
	@Column(name = "mailContentDesc", nullable = true, length = 1024)
	public String getMailContentDesc() {
		return mailContentDesc;
	}
	public void setMailContentDesc(String mailContentDesc) {
		this.mailContentDesc = mailContentDesc;
	}
	
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId")
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
    /*@ManyToMany
    @JoinTable(name = "classExamTypes", joinColumns = { @JoinColumn(name = "examTypeId") },
       inverseJoinColumns = { @JoinColumn(name = "classNameId") })
    public Set<ClassName> getClasses() {
    	if(classes == null)
        {
    		classes = new HashSet<ClassName>();
        }
		return classes;
	}
	public void setClasses(Set<ClassName> classes) {
		this.classes = classes;
	}*/
       
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
	 * @return the examType
	 */
	public String getExamType() {
		return examType;
	}
	/**
	 * @param examType the examType to set
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}
	/**
	 * @return the minMarks
	 */
	public String getMinMarks() {
		return minMarks;
	}
	/**
	 * @param minMarks the minMarks to set
	 */
	public void setMinMarks(String minMarks) {
		this.minMarks = minMarks;
	}
	/**
	 * @return the maxMarks
	 */
	public String getMaxMarks() {
		return maxMarks;
	}
	/**
	 * @param maxMarks the maxMarks to set
	 */
	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}
	@Override
	public int compareTo(Object object) {
		ExamTypes examType=(ExamTypes)object;
		if(examType.getSortingOrder() > 0 && this.getSortingOrder() > 0){
			if(this.getSortingOrder() > examType.getSortingOrder())
				return 1;
			else if(this.getSortingOrder() == examType.getSortingOrder())
				return 0;
			else
				return -1;
		}else{
			return this.getExamType().compareToIgnoreCase(examType.getExamType());			
		}
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
	@Transient
	public String getSelectedClassIds() {
		return selectedClassIds;
	}
	public void setSelectedClassIds(String selectedClassIds) {
		this.selectedClassIds = selectedClassIds;
	}
	public void copyFrom(ExamTypes types)
    {
		setCustId(types.getCustId());
		setExamType(types.getExamType());
	    setMinMarks(types.getMinMarks());
	    setMaxMarks(types.getMaxMarks());
	    setAcademicYear(types.getAcademicYear());
	    //setClasses(types.getClasses());
	    setMailContentDesc(types.getMailContentDesc());
	    setMobileContentDesc(types.getMobileContentDesc());
	    setNoOfDays(types.getNoOfDays());
    }
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "examTypesAndSubTypes", joinColumns = { @JoinColumn(name = "examTypeId") },
    inverseJoinColumns = { @JoinColumn(name = "subTypeId") })
	public Set<SubType> getExamSubTypes() {
		if(examSubTypes == null)
        {
			examSubTypes = new HashSet<SubType>();
        }
		return examSubTypes;
	}
	public void setExamSubTypes(Set<SubType> examSubTypes) {
		this.examSubTypes = examSubTypes;
	}
	@Transient
	public String getSelectedExamSubTypeIds() {
		return selectedExamSubTypeIds;
	}
	public void setSelectedExamSubTypeIds(String selectedExamSubTypeIds) {
		this.selectedExamSubTypeIds = selectedExamSubTypeIds;
	}
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "classSectionExamTypes", joinColumns = { @JoinColumn(name = "examTypeId") },
       inverseJoinColumns = { @JoinColumn(name = "classSectionId") })
	public Set<StudyClass> getStudyClasses() {
		if(studyClasses == null)
        {
			studyClasses = new HashSet<StudyClass>();
        }
		return studyClasses;
		
	}
	public void setStudyClasses(Set<StudyClass> studyClasses) {
		this.studyClasses = studyClasses;
	}
	@Transient
	public String getClassIds(){
		StringBuffer classIds = new StringBuffer("(");
		if(!ObjectFunctions.isNullOrEmpty(this.studyClasses)){
			for(StudyClass studyClass : this.studyClasses){
				classIds.append(studyClass.getClassId()).append(",");
			}
			classIds.append("0)");
			return classIds.toString();
		}else{
			return classIds.append("(0)").toString();
		}
	}
	@Transient
	public String getClassSectionIds(){
		StringBuffer classSectionIds = new StringBuffer("(");
		if(!ObjectFunctions.isNullOrEmpty(this.studyClasses)){
			for(StudyClass studyClass : this.studyClasses){
				classSectionIds.append(studyClass.getId()).append(",");
			}
			classSectionIds.append("0)");
			return classSectionIds.toString();
		}else{
			return classSectionIds.append("0)").toString();
		}
	}
}
