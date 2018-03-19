package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table studyAndBonafiedSettings.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "studyAndBonafiedSettings")
public class StudyAndBonafiedSettings  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	

    private String studyfileName;
    private long custId;
    /*private String className;*/
    private String bonafiedFileName;
    private String dueCertificateFileName;
    private long studyClassId;
    
    private String fileName;
    private String certificateType;//SC - Study Certificate, BC - Bonafied Certificate, ND - No Due Certificate, FC - Fee Certificate
    
    private String filePath;
    private String feeCertificateFileName;
    
	
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
    
	public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public StudyAndBonafiedSettings(){
        
    }
    public StudyAndBonafiedSettings(long id) {
        setId(id);
    }
  /*  public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}*/
	public String getStudyfileName() {
		return studyfileName;
	}
	public void setStudyfileName(String studyfileName) {
		this.studyfileName = studyfileName;
	}
	public String getBonafiedFileName() {
		return bonafiedFileName;
	}
	public void setBonafiedFileName(String bonafiedFileName) {
		this.bonafiedFileName = bonafiedFileName;
	}
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	
	/**
	 * @return the dueCertificateFileName
	 */
	public String getDueCertificateFileName() {
		return dueCertificateFileName;
	}
	/**
	 * @param dueCertificateFileName the dueCertificateFileName to set
	 */
	public void setDueCertificateFileName(String dueCertificateFileName) {
		this.dueCertificateFileName = dueCertificateFileName;
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
        .append("ClassName", this.studyClassId)
        .append("Study File Name",this.studyfileName).toString();
		
	}
	@Override
	public int compareTo(Object object) {
		StudyAndBonafiedSettings days = (StudyAndBonafiedSettings) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}
	@Transient
	public String getTemplateStudyFileName(){
		if(StringFunctions.isNullOrEmpty(this.studyfileName))
			return "";
		else
			return this.studyfileName.substring(this.studyfileName.lastIndexOf("/")+1);
	}
	
	@Transient
	public String getBonafiedTemplateFileName(){
		if(StringFunctions.isNullOrEmpty(this.bonafiedFileName))
			return "";
		else
			return this.bonafiedFileName.substring(this.bonafiedFileName.lastIndexOf("/")+1);
	}
	@Transient
	public String getDueCirtificateTemplateFileName(){
		if(StringFunctions.isNullOrEmpty(this.dueCertificateFileName))
			return "";
		else
			return this.dueCertificateFileName.substring(this.dueCertificateFileName.lastIndexOf("/")+1);
	}
	@Transient
	public String getFeeCirtificateTemplateFileName() {
		if (StringFunctions.isNullOrEmpty(this.feeCertificateFileName))
			return "";
		else
			return this.feeCertificateFileName.substring(this.feeCertificateFileName.lastIndexOf("/") + 1);
	}
	public String getFeeCertificateFileName() {
		return feeCertificateFileName;
	}
	public void setFeeCertificateFileName(String feeCertificateFileName) {
		this.feeCertificateFileName = feeCertificateFileName;
	}

	

}

