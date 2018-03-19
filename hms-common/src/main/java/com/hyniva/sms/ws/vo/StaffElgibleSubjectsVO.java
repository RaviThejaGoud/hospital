package com.hyniva.sms.ws.vo;


public class StaffElgibleSubjectsVO {
	public long id;
	public StaffVO staffIdVo;
	public StudySubjectVO studySubjectIdVo;
	public AcademicYearVo academicYearVo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StaffVO getStaffIdVo() {
		return staffIdVo;
	}
	public void setStaffIdVo(StaffVO staffIdVo) {
		this.staffIdVo = staffIdVo;
	}
	public StudySubjectVO getStudySubjectIdVo() {
		return studySubjectIdVo;
	}
	public void setStudySubjectIdVo(StudySubjectVO studySubjectIdVo) {
		this.studySubjectIdVo = studySubjectIdVo;
	}
	public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}
	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}
    
    
    
	

}
