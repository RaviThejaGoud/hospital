package com.urt.persistence.model.study;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.StudySubjectVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "studySubject")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class StudySubject  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
    @ExcelField(position = 2)
    protected String name;                  
    protected String description; 
    protected long custId;
    //protected long parentId;
    private List expecTeacherList;
    private List teacherList;
    protected String staffAccountId;
    protected AcademicYear academicYear;
    private List subjectClassList;
    protected String subjectNumber;    
    private int sortingOrder;
    private boolean language;
    private String subjectStaffId;
    protected String subjectType;  
    
    /* Default we will put "N", If any one select optional subject we will change to "Y" */
    @Column(name = "subjectType", nullable = true, length = 1,columnDefinition="char(1) default 'N'")        
    public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
    
    @Transient
    public String getSubjectStaffId() {
		return subjectStaffId;
	}
	public void setSubjectStaffId(String subjectStaffId) {
		this.subjectStaffId = subjectStaffId;
	}
	/**
	 * @return the language
	 */
    @Column(name = "language", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(boolean language) {
		this.language = language;
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
     * @return the academicYear
     */
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    
    @Transient
    public String getStaffAccountId() {
		return staffAccountId;
	}




	public void setStaffAccountId(String staffAccountId) {
		this.staffAccountId = staffAccountId;
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

    
    public StudySubject() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

    public StudySubject(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		StudySubject sub=(StudySubject)object;
		if(sub.getSortingOrder() > 0 && this.getSortingOrder() > 0){
			if(this.getSortingOrder() > sub.getSortingOrder())
				return 1;
			else if(this.getSortingOrder() == sub.getSortingOrder())
				return 0;
			else
				return -1;
		}else if(StringFunctions.isNotNullOrEmpty(sub.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(this.getSubjectNumber())){
			return this.getSubjectNumber().compareToIgnoreCase(sub.getSubjectNumber());
		}else{
			return this.getName().compareToIgnoreCase(sub.getName());			
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("name", this.name).toString();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

/*	*//**
	 * @return the parentId
	 *//*
	public long getParentId() {
		return parentId;
	}

	*//**
	 * @param parentId the parentId to set
	 *//*
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}*/
	@Transient
	public List getExpecTeacherList() {
		if(ObjectFunctions.isNullOrEmpty(this.expecTeacherList))
		{
			this.expecTeacherList = new ArrayList();
		}
		return this.expecTeacherList;
	}

	public void setExpecTeacherList(List expecTeacherList) {
		this.expecTeacherList = expecTeacherList;
	}
	@Transient
	public List getTeacherList() {
		if(ObjectFunctions.isNullOrEmpty(this.teacherList))
		{
			this.teacherList = new ArrayList();
		}
		return teacherList;
	}
	public void setTeacherList(List teacherList) {
		this.teacherList = teacherList;
	}
	public void copyFrom(StudySubject subject)
    {
		 	setName(subject.getName());                  
		    setDescription(subject.getDescription()); 
		    setCustId(subject.getCustId());
		    //setAcademicYear(subject.getAcademicYear());
		    setSortingOrder(subject.getSortingOrder());
    }
	@Transient
	public List getSubjectClassList() {
		if(ObjectFunctions.isNullOrEmpty(this.subjectClassList))
		{
			this.subjectClassList = new ArrayList();
		}
		return this.subjectClassList;
	}
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(this.academicYear)){
			return 0;
		}else
			return this.academicYear.getId();
	}
	
	@Transient
	public StudySubject copyFromVoToEntity(StudySubject studySubject, StudySubjectVO studySubjectVO)
	{
		if(studySubject.getId() == 0)
			studySubject.id = studySubjectVO.id;
		studySubject.name = studySubjectVO.name;
		studySubject.custId = studySubjectVO.custId;
		studySubject.staffAccountId = studySubjectVO.staffAccountId;
		studySubject.subjectNumber = studySubjectVO.subjectNumber;
		studySubject.description = studySubjectVO.description;
		studySubject.sortingOrder = studySubjectVO.sortingOrder;
		studySubject.language = studySubjectVO.language;
		studySubject.subjectStaffId = studySubjectVO.subjectStaffId;
		studySubject.subjectType = studySubjectVO.subjectType;
		return studySubject;
	}
	
	@Transient
	public StudySubjectVO copyFromEntityToVo(StudySubject studySubject)
	{
		StudySubjectVO studySubjectVo = new StudySubjectVO();
		studySubjectVo.id = studySubject.id;
		studySubjectVo.name = studySubject.name;
		studySubjectVo.custId = studySubject.custId;
		studySubjectVo.description = studySubject.description;
		studySubjectVo.staffAccountId = studySubject.staffAccountId;
		studySubjectVo.subjectNumber = studySubject.subjectNumber;
		studySubjectVo.sortingOrder = studySubject.sortingOrder;
		studySubjectVo.language = studySubject.language;
		studySubjectVo.subjectStaffId = studySubject.subjectStaffId;
		studySubjectVo.subjectType = studySubject.subjectType;
		return studySubjectVo;
	}
	
}
    
