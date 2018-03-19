package com.hyniva.sms.ws.vo;

import java.io.File;

import com.churchgroup.util.string.StringFunctions;


/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
public class DefaultScoreCardTemplatesVO  {
    
	private long id;
    private long custId;
    private String scoreCardName;
    private long noOfExamTypes;
    private long noOfSubTypes;
    private String excelFileName;
    private String imageFileName;
    private String excelFilePath;
    private String imageFilePath;
    private File excelFilePathFile;
    private File imageFilePathFile;

    public DefaultScoreCardTemplatesVO(){
    	
    }
    
    
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public File getExcelFilePathFile() {
		return excelFilePathFile;
	}


	public void setExcelFilePathFile(File excelFilePathFile) {
		this.excelFilePathFile = excelFilePathFile;
	}


	public File getImageFilePathFile() {
		return imageFilePathFile;
	}


	public void setImageFilePathFile(File imageFilePathFile) {
		this.imageFilePathFile = imageFilePathFile;
	}
	
	public String getAdjustedAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getImageFilePath())) {
			return "../"+getImageFilePath().concat(getImageFileName());
		}
		return getStudyImageNotFoundFile();
	}
	public static String getStudyImageNotFoundFile()
    {
    	return "/images/common/photo_notAvailable.jpg"; 
    }
}

