package com.hyniva.sms.ws.vo.exam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.AlbumAttachmentVO;
import com.hyniva.sms.ws.vo.AttachmentVO;
import com.hyniva.sms.ws.vo.StudyClassVO;
import com.hyniva.sms.ws.vo.StudySubjectVO;
import com.hyniva.sms.ws.vo.sports.TeamVO;
import com.hyniva.sms.ws.vo.study.StaffSyllabusPlannerVO;



public class QuestionPaperBankVO {
	
	private long id;
	private long custId;
    private long academicYearId;
    private String materialName;
    private String description;
    protected StudyClassVO studyClassVO;
    protected StudySubjectVO studySubjectVO;
    protected StaffSyllabusPlannerVO staffSyllabusPlannerVo;
    private List<AttachmentVO> attachmentVOs;
    private List<File> fileUpload;
    private List<String> fileUploadFileName = new ArrayList<String>();
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public StudyClassVO getStudyClassVO() {
		return studyClassVO;
	}
	public void setStudyClassVO(StudyClassVO studyClassVO) {
		this.studyClassVO = studyClassVO;
	}
	public StudySubjectVO getStudySubjectVO() {
		return studySubjectVO;
	}
	public void setStudySubjectVO(StudySubjectVO studySubjectVO) {
		this.studySubjectVO = studySubjectVO;
	}
	public StaffSyllabusPlannerVO getStaffSyllabusPlannerVo() {
		if(ObjectFunctions.isNullOrEmpty(this.staffSyllabusPlannerVo))
			this.staffSyllabusPlannerVo= new StaffSyllabusPlannerVO();
		return staffSyllabusPlannerVo;
	}
	public void setStaffSyllabusPlannerVo(
			StaffSyllabusPlannerVO staffSyllabusPlannerVo) {
		this.staffSyllabusPlannerVo = staffSyllabusPlannerVo;
	}
	public List<AttachmentVO> getAttachmentVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.attachmentVOs))
			this.attachmentVOs=new ArrayList<AttachmentVO>();
		return attachmentVOs;
	}
	public void setAttachmentVOs(List<AttachmentVO> attachmentVOs) {
		this.attachmentVOs = attachmentVOs;
	}
	public List<File> getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
    
}
