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

import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Sreeram J
 * @mailTO: jsreeram29@yahoo.com
 *
 */
@Entity
@Table(name = "personDocuments")
public class PersonDocuments extends PersistentObject  {
   
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     private String fileName;
     private String filePath;
     private long academicYearId;

     
     @Column(name = "academicYearId",nullable = false, columnDefinition=" int default 0")
    public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	/**
     * @param thumbNail the thumbNail to set
     */
    /** default constructor */
    public PersonDocuments() {
    }
    
    @Column(name = "name", nullable = true, length = 124)
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
    public String getHrefOriginalAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getFilePath()))
        {
            return getFilePath().substring(1).concat(getFileName());
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
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
    	PersonDocuments myClass = (PersonDocuments) object;
        return new CompareToBuilder().append(this.fileName,
                        myClass.fileName).append(this.filePath,
                        myClass.filePath).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Attachment)) {
            return false;
        }
        PersonDocuments rhs = (PersonDocuments) object;
        return new EqualsBuilder().append(
                this.filePath, rhs.filePath).append(this.fileName, rhs.fileName)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1923559909, -664973933).append(this.filePath).append(this.fileName)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("fileName", this.fileName)
                .append("filePath", this.filePath).toString();
    }

    
   
    
    
}