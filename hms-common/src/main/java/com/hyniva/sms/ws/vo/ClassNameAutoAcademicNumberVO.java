package com.hyniva.sms.ws.vo;


public class ClassNameAutoAcademicNumberVO {
	
	private long admissionNumberSettingsId;
	private String className;
	private String prefix;
	private String postfix;
	private int sequenceNumber;
	private String academicYear;
	private long academicYearId;
	private long classId;
	private boolean studentsAvailable;
	private long admissionSettingId;
	
	
	
	
	
	public long getAdmissionSettingId() {
		return admissionSettingId;
	}
	public void setAdmissionSettingId(long admissionSettingId) {
		this.admissionSettingId = admissionSettingId;
	}
	public boolean isStudentsAvailable() {
		return studentsAvailable;
	}
	public void setStudentsAvailable(boolean studentsAvailable) {
		this.studentsAvailable = studentsAvailable;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public long getAdmissionNumberSettingsId() {
		return admissionNumberSettingsId;
	}
	public void setAdmissionNumberSettingsId(long admissionNumberSettingsId) {
		this.admissionNumberSettingsId = admissionNumberSettingsId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	
}
