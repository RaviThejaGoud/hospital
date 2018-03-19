package com.urt.persistence.model.event;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.UserImage;

@Entity
@Table(name = "albumAttachment")
public class AlbumAttachment extends PersistentObject {
	
	private static final long serialVersionUID = 3832626162173359411L;
	
	protected String filePath;
	protected String fileName;
	/*protected String fileSize;
	protected String fileType;
	protected String fileTypePath;
	protected String fileUsed;
	protected String mapFile;
	protected String thumbNail;
	protected String status;*/
	protected long custId;
	protected long academicYearId;
	
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String value) {
		this.fileName = value;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String value) {
		this.filePath = value;
	}

	/*public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String value) {
		this.fileSize = value;
	}*/

	/*public String getFileType() {
		return fileType;
	}

	public void setFileType(String value) {
		this.fileType = value;
	}*/

	/*public String getFileTypePath() {
		return fileTypePath;
	}

	public void setFileTypePath(String value) {
		this.fileTypePath = value;
	}*/

	/*public String getFileUsed() {
		return fileUsed;
	}

	public void setFileUsed(String value) {
		this.fileUsed = value;
	}*/

	/*public String getMapFile() {
		return mapFile;
	}

	public void setMapFile(String value) {
		this.mapFile = value;
	}*/

	/*public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String value) {
		this.thumbNail = value;
	}*/

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
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*@Transient
	public String getAlbumAttachmentFilePath() {
		String imagePath="file:///"+ServletActionContext.getServletContext().getRealPath(getHrefAttachmentFilePath());
		return imagePath;
	}*/
	@Transient
	public String getHrefAttachmentFilePath() {
		if (!StringFunctions.isNullOrEmpty(getFilePath())) {
			//return "../"+getFilePath().concat(getFileName());
			return getFilePath();
		}
		return UserImage.getStudyImageNotFoundFile();
	}
}
