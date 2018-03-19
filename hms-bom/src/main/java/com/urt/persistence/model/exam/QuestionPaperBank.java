package com.urt.persistence.model.exam;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.exam.QuestionPaperBankVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.study.StaffSyllabusPlanner;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;

@Entity
@Table(name="questionPaperBank")
public class QuestionPaperBank extends PersistentObject{

	private static final long serialVersionUID = 8525790419133532592L;
	private long custId;
    private long academicYearId;
    private String materialName;
    private String description;
    protected StudyClass studyClass;
    protected StudySubject studySubject;
    protected StaffSyllabusPlanner staffSyllabusPlanner;
    protected List<Attachment> attachment;
    
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="studyClassId",insertable=true,updatable=true)
	public StudyClass getStudyClass() {
		return studyClass;
	}
	public void setStudyClass(StudyClass studyClass) {
		this.studyClass = studyClass;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="syllabusPlannerId",insertable=true,updatable=true)
	public StaffSyllabusPlanner getStaffSyllabusPlanner() {
		return staffSyllabusPlanner;
	}
	public void setStaffSyllabusPlanner(StaffSyllabusPlanner staffSyllabusPlanner) {
		this.staffSyllabusPlanner = staffSyllabusPlanner;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="questionPaperBankId")
	public List<Attachment> getAttachment() {
		if(ObjectFunctions.isNullOrEmpty(this.attachment)){
			this.attachment = new ArrayList<Attachment>();
		}
		return attachment;
	}
	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="subjectId",insertable=true,updatable=true)
	public StudySubject getStudySubject() {
		return studySubject;
	}
	public void setStudySubject(StudySubject studySubject) {
		this.studySubject = studySubject;
	}
	public QuestionPaperBank() {
		this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
	}
	public QuestionPaperBank(Long id) {
		setId(id);
	}
	@Transient
	public QuestionPaperBankVO copyFromEntityToVO(){
		QuestionPaperBankVO questionPaperBankVO = new QuestionPaperBankVO();
		questionPaperBankVO.setId(this.getId());
		questionPaperBankVO.setAcademicYearId(this.getAcademicYearId());
		questionPaperBankVO.setDescription(this.getDescription());
		questionPaperBankVO.setCustId(this.getCustId());
		questionPaperBankVO.setMaterialName(this.getMaterialName());
		return questionPaperBankVO;
	}
	@Transient
	public QuestionPaperBank copyFromVoToEntity(QuestionPaperBankVO questionPaperBankVO){
		this.setId(questionPaperBankVO.getId());
		this.setCustId(questionPaperBankVO.getCustId());
		this.setAcademicYearId(questionPaperBankVO.getAcademicYearId());
		this.setDescription(questionPaperBankVO.getDescription());
		this.setMaterialName(questionPaperBankVO.getMaterialName());
		return this;
	}
	@Transient
	public void addAttachments(Attachment questionPaperAttachments) {
		if(ObjectFunctions.isNullOrEmpty(questionPaperAttachments)){
			questionPaperAttachments=new Attachment();
		}
		getAttachment().add(questionPaperAttachments);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
    
	
}
