package com.hyniva.sms.ws.vo;


public class ExamSchedulesVO {
	
	protected long id;
    protected String startDate;
    protected String startTime;
    protected String endDate;
    protected String endTime;
    protected double maxMarks;
    protected boolean scheduled;
    protected String examDate;
    protected ExamTypeVO examTypeVO;
    protected SubTypeVO subTypeVO;
    protected ClassSectionSubjectVO classSectionSubjectVO;
    protected StudyClassVO studyClassVO;
    
    
    
    
    
	public ExamTypeVO getExamTypeVO() {
		return examTypeVO;
	}
	public void setExamTypeVO(ExamTypeVO examTypeVO) {
		this.examTypeVO = examTypeVO;
	}
	public SubTypeVO getSubTypeVO() {
		return subTypeVO;
	}
	public void setSubTypeVO(SubTypeVO subTypeVO) {
		this.subTypeVO = subTypeVO;
	}
	public ClassSectionSubjectVO getClassSectionSubjectVO() {
		return classSectionSubjectVO;
	}
	public void setClassSectionSubjectVO(ClassSectionSubjectVO classSectionSubjectVO) {
		this.classSectionSubjectVO = classSectionSubjectVO;
	}
	public StudyClassVO getStudyClassVO() {
		return studyClassVO;
	}
	public void setStudyClassVO(StudyClassVO studyClassVO) {
		this.studyClassVO = studyClassVO;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	public boolean isScheduled() {
		return scheduled;
	}
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}
}
