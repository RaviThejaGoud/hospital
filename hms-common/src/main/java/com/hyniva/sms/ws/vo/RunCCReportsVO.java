package com.hyniva.sms.ws.vo;

import java.util.List;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class RunCCReportsVO extends SMSBaseVO{
	
	private String fileName;
	private  String result;
	private String exception;
	private String serverType;
	private String xmlFilePath;
	private List<StaffTimetablesVO> staffTimetables;
	private List<StudyClassTimetablesVO> studyClassTimetables;
	
	
	
	public String getXmlFilePath() {
		return xmlFilePath;
	}
	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	public List<StaffTimetablesVO> getStaffTimetables() {
		return staffTimetables;
	}
	public void setStaffTimetables(List<StaffTimetablesVO> staffTimetables) {
		this.staffTimetables = staffTimetables;
	}
	public List<StudyClassTimetablesVO> getStudyClassTimetables() {
		return studyClassTimetables;
	}
	public void setStudyClassTimetables(
			List<StudyClassTimetablesVO> studyClassTimetables) {
		this.studyClassTimetables = studyClassTimetables;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	
	
}