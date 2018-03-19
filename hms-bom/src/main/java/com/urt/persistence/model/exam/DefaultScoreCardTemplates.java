package com.urt.persistence.model.exam;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "defaultScoreCardTemplates")
public class DefaultScoreCardTemplates  extends PersistentObject{
    private static final long serialVersionUID = 383262616217335941L;
	
    
    
    private long custId;
    private String scoreCardName;
    private long noOfExamTypes;
    private long noOfSubTypes;
    private String excelFileName;
    private String imageFileName;
    private String excelFilePath;
    private String imageFilePath;

    public DefaultScoreCardTemplates(){
    	
    }
    
	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getScoreCardName() {
		return scoreCardName;
	}

	public void setScoreCardName(String scoreCardName) {
		this.scoreCardName = scoreCardName;
	}
	public long getNoOfExamTypes() {
		return noOfExamTypes;
	}
	
	public void setNoOfExamTypes(long noOfExamTypes) {
		this.noOfExamTypes = noOfExamTypes;
	}
	public long getNoOfSubTypes() {
		return noOfSubTypes;
	}

	public void setNoOfSubTypes(long noOfSubTypes) {
		this.noOfSubTypes = noOfSubTypes;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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
		DefaultScoreCardTemplates defaultScoreCard =(DefaultScoreCardTemplates)object;
		if(id==defaultScoreCard.getId())
			return 0;
		else if (id > defaultScoreCard.getId()) 
			return 1;
		else
			return -1;
	}
	@Transient
	public String getDefaultScoreCardPath() {
		if (!StringFunctions.isNullOrEmpty(getExcelFilePath())) {
			return getExcelFilePath().concat(getExcelFileName());
		}
		return null;
	}
	
	
}

