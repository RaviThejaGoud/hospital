package com.hyniva.sms.ws.vo;



public class AlbumAttachmentVO {
	
	
	public long id;
	protected String fileName;
	protected String filePath;
	/*protected String fileSize;
	protected String fileType;
	protected String fileTypePath;
	protected String fileUsed;
	protected String mapFile;
	protected String thumbNail;
	protected String status;
	protected long custId;
	protected long academicYearId;*/
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*public long getCustId() {
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
	}*/

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String value) {
		this.fileName = value;
	}

	/**
	 * Gets the value of the filePath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the value of the filePath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFilePath(String value) {
		this.filePath = value;
	}

	/*public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String value) {
		this.fileSize = value;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String value) {
		this.fileType = value;
	}

	public String getFileTypePath() {
		return fileTypePath;
	}

	public void setFileTypePath(String value) {
		this.fileTypePath = value;
	}

	public String getFileUsed() {
		return fileUsed;
	}

	public void setFileUsed(String value) {
		this.fileUsed = value;
	}

	public String getMapFile() {
		return mapFile;
	}

	public void setMapFile(String value) {
		this.mapFile = value;
	}

	public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String value) {
		this.thumbNail = value;
	}*/

	/*public String getHrefAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getFilePath())) {
			return "../"+getFilePath().concat(getFileName());
		}
		return UserImageVO.getStudyImageNotFoundFile();
	}*/
}
