/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
 * File: Attachment.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 23, 2006           Sreeram J          Created
/ ********************************************************************/

package com.urt.persistence.model.common;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Sreeram J
 * @mailTO: jsreeram29@yahoo.com
 *
 */
@Entity
@Table(name = "attachment")
public class Attachment extends PersistentObject  {
   
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     private String fileUsed = Constants.FILE_DOCS_USAGE;
     private String fileName;
     private String fileType;
     private String fileTypePath;
     private String fileSize;
     private String filePath;
     private String thumbNail;
     private String mapFile;

    /**
     * @param thumbNail the thumbNail to set
     */
    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }
    /** default constructor */
    public Attachment() {
        this.filePath="/images/common/";
        this.fileName="photo_notAvailable.jpg";
    }
    
    /** full constructor */
    public Attachment(String fileName, String fileType, String fileSize) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
    @Column(name = "name", nullable = true, length = 124)
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Column(name = "fileType", nullable = true, length = 40)
    public String getFileType() {
        return this.fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    @Column(name = "fileSize", nullable = true, length = 2048)
    public String getFileSize() {
        return this.fileSize;
    }
    
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    /**
     * @return Returns the filePath.
     */
    @Column(name = "filePath", nullable = true, length = 1024)
    public String getFilePath() {
          return this.filePath;
    }
    @Transient
    public static String getImageNotFoundFile()
    {
        return "/images/common/photo_notAvailable.jpg"; 
    }
    @Transient
    public static String getStudyImageNotFoundFile()
    {
        return "/images/common/studyImageNotAvailable.jpg"; 
    }
    @Transient
    public String getOriginalAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getFilePath()))
        {
            return getFilePath().concat(getFileName());
        }
        return Attachment.getStudyImageNotFoundFile();
    }
    @Transient
    public String getAdjustedAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getFilePath()))
        {
            return getFilePath().concat(getThumbNail());
        }
        return Attachment.getStudyImageNotFoundFile();
    }
    @Transient
    public String getHrefOriginalAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getFilePath()))
        {
            return getFilePath().substring(1).concat(getFileName());
        }
        return Attachment.getStudyImageNotFoundFile();
    }
    @Transient
    public String getHrefAdjustedAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getFilePath()))
        {
            return getFilePath().substring(1).concat(getThumbNail());
        }
        return Attachment.getStudyImageNotFoundFile();
    }
    /**
     * @param filePath The filePath to set.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return Returns the fileUsed.
     */
    @Column(name = "fileUsed", nullable = true, length = 50)
    public String getFileUsed() {
        return this.fileUsed;
    }

    /**
     * @param fileUsed The fileUsed to set.
     */
    public void setFileUsed(String fileUsed) {
        this.fileUsed = fileUsed;
    }
    /**
     * @return the fileTypePath
     */
    @Column(name = "fileTypePath", nullable = true, length = 1024)
    public String getFileTypePath() {
        return fileTypePath;
    }

    /**
     * @param fileTypePath the fileTypePath to set
     */
    public void setFileTypePath(String fileTypePath) {
        this.fileTypePath = fileTypePath;
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        Attachment myClass = (Attachment) object;
        return new CompareToBuilder().append(this.fileType, myClass.fileType)
                .append(this.fileUsed, myClass.fileUsed).append(this.fileName,
                        myClass.fileName).append(this.filePath,
                        myClass.filePath).append(this.fileSize,
                        myClass.fileSize).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Attachment)) {
            return false;
        }
        Attachment rhs = (Attachment) object;
        return new EqualsBuilder().append(this.fileType, rhs.fileType).append(
                this.filePath, rhs.filePath).append(this.fileName, rhs.fileName)
                .append(this.fileSize, rhs.fileSize).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1923559909, -664973933).append(
                this.fileType).append(this.filePath).append(this.fileName)
                .append(this.fileSize).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("fileSize",
                        this.fileSize).append("fileName", this.fileName)
                .append("filePath", this.filePath).append("fileType",
                        this.fileType).toString();
    }

    /**
     * @return the thumbNail
     */
    @Column(name = "thumbNail", nullable = true, length = 255)
    public String getThumbNail() {
        return thumbNail;
    }

    @Column(name = "mapFile", nullable = true, length = 255)
    public String getMapFile() {
        return mapFile;
    }

    public void setMapFile(String mapFile) {
        this.mapFile = mapFile;
    }
    
    
}