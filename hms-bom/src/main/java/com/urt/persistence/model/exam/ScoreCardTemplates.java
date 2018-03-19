package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "scoreCardTemplates")
public class ScoreCardTemplates  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	
    
    private String fileName;
    private long custId;
    private String className;
    private String docFileName;
    private long examTypeId;
    private String examType;
    
    private String filePath;
    

    
    
	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
    public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
    @Column(name = "examTypeId", nullable = false ,columnDefinition="bigint(20) default 0")
    public long getExamTypeId() {
		return examTypeId;
	}
	public void setExamTypeId(long examTypeId) {
		this.examTypeId = examTypeId;
	}
	public ScoreCardTemplates() {
        
    }
    public ScoreCardTemplates(long id) {
        setId(id);
    }
    
    public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
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
        .append("ClassName", this.className)
        .append("File Name",this.fileName).toString();
	}
	@Override
	public int compareTo(Object object) {
		ScoreCardTemplates days = (ScoreCardTemplates) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}
	@Transient
	public String getTemplateFileName(){
		if(StringFunctions.isNullOrEmpty(this.fileName))
			return "";
		else
			//return this.fileName.substring(this.fileName.lastIndexOf("/")+1);
			return this.fileName;
	}
	
	@Transient
	public String getDocTemplateFileName(){
		if(StringFunctions.isNullOrEmpty(this.docFileName))
			return "";
		else
			//return this.docFileName.substring(this.docFileName.lastIndexOf("/")+1);\
			return this.docFileName;
	}
	
}

