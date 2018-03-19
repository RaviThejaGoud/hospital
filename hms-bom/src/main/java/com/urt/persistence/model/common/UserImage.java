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
import org.apache.struts2.ServletActionContext;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.UserImageVO;
import com.urt.persistence.model.base.PersistentObject;

/**
 * @author Sreeram J
 * @mailTO: jsreeram29@yahoo.com
 *
 */
@Entity
@Table(name = "UserImage")
public class UserImage extends PersistentObject  {
   
     private static final long serialVersionUID = 1L;
     private String fileUsed = Constants.FILE_TYPE_IMAGE;
     private String usage="Y";
     private String name;
     private String type;
     private String typePath;
     private long size;
     private String path;
     private String thumbNail;
     private String mapFile;
    
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
     * @param thumbNail the thumbNail to set
     */
    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

   
    /** default constructor */
    public UserImage() {
        this.path="/images/common/";
        this.name="photo_notAvailable.jpg";
    }
    
    /** full constructor */
    public UserImage(String fileName, String fileType, long fileSize) {
        this.name = fileName;
        this.type = fileType;
        this.size = fileSize;
    }
    @Transient
    public static String getImageNotFoundFile()
    {
        //return "/images/common/photo_notAvailable.jpg"; 
    	//return ServletActionContext.getServletContext().getRealPath("/images/common/photo_notAvailable.jpg"); 
    	 String imageNotFoundUrl = ServletActionContext.getServletContext().getInitParameter("hostUrl");
    	 imageNotFoundUrl = imageNotFoundUrl + "/images/common/photo_notAvailable.jpg";
		return imageNotFoundUrl;
    }
    @Transient
    public static String getStudyImageNotFoundFile()
    {
    	 String imageNotFoundUrl = ServletActionContext.getServletContext().getInitParameter("hostUrl");
    	 imageNotFoundUrl = imageNotFoundUrl + "/images/common/photo_notAvailable.jpg";
		 return imageNotFoundUrl;
    	//return ServletActionContext.getServletContext().getRealPath("/images/common/photo_notAvailable.jpg"); 
    }
    @Transient
    public String getOriginalAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getPath()))
        {
            //return getPath().concat(getName());s
        	return getPath();
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    
 
    @Transient
    public String getCropAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getPath()))
        {
            //return getPath().concat(getName());
        	return getPath();
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    
    @Transient
    public String getAdjustedAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getPath()))
        {
        	log.debug(getPath()); 
        	//return ".."+getPath().concat(getName());
        	return getPath();
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    @Transient
    public String getHrefOriginalAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getPath()))
        {
            //return getPath().substring(1).concat(getName());
        	return getPath();
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    @Transient
    public String getHrefAdjustedAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getPath()))
        {
            //return getPath().substring(1).concat(getThumbNail());
        	return getPath();
        }
        return UserImage.getStudyImageNotFoundFile();
    }
   
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        UserImage myClass = (UserImage) object;
        return new CompareToBuilder().append(this.type, myClass.type)
                .append(this.usage, myClass.usage).append(this.name,
                        myClass.name).append(this.path,
                        myClass.path).append(this.size,
                        myClass.size).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof UserImage)) {
            return false;
        }
        UserImage rhs = (UserImage) object;
        return new EqualsBuilder().append(this.type, rhs.type).append(
                this.path, rhs.path).append(this.name, rhs.name)
                .append(this.size, rhs.size).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1923559909, -664973933).append(
                this.type).append(this.path).append(this.name)
                .append(this.size).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("fileSize",
                        this.size).append("fileName", this.name)
                .append("filePath", this.path).append("fileType",
                        this.type).toString();
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


	/**
	 * @return the usage
	 */
    @Column(name = "usageType", nullable = true, length = 10)
    //@Type(type="yes_no")
	public String getUsage() {
		return usage;
	}


	/**
	 * @param usage the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}


	/**
	 * @return the name
	 */
	 @Column(name = "name", nullable = true, length = 255)
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the type
	 */
	 @Column(name = "type", nullable = true, length = 255)
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the typePath
	 */
	 @Column(name = "typePath", nullable = true, length = 255)
	public String getTypePath() {
		return typePath;
	}


	/**
	 * @param typePath the typePath to set
	 */
	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}


	/**
	 * @return the size
	 */
	 @Column(name = "size", nullable = true, length = 3)
	public long getSize() {
		return size;
	}


	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}


	/**
	 * @return the path
	 */
	 @Column(name = "path", nullable = true, length = 512)
	public String getPath() {
		return path;
	}


	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	@Transient
	public String getThumbNailFile() {
		if (!StringFunctions.isNullOrEmpty(getTypePath())
				&& !StringFunctions.isNullOrEmpty(getThumbNail())) {
			return getTypePath().concat("thumb_" + getName());
		} else {
			return "/images/admin/photo_notAvailable.jpg";
		}
	}
	public void copyFrom(UserImage userImage) {
   	 this.fileUsed = userImage.getFileUsed();
   	 this.usage = userImage.getUsage();
   	 this.name = userImage.getName();
   	 this.type = userImage.getType();
   	 this.typePath = userImage.getTypePath();
   	 this.size =userImage.getSize();
   	 this.path = userImage.getPath();
   	 this.thumbNail = userImage.getThumbNail();
   	 this.mapFile = userImage.getMapFile();
    }
	

	public UserImage copyFromVoToEntity(UserImage userImage, UserImageVO userImageVo)
	{
		if(userImage.getId() == 0)
			userImage.id = userImageVo.id;
		userImage.fileUsed = userImageVo.fileUsed;
		userImage.usage = userImageVo.usage;
		userImage.name = userImageVo.name;
		userImage.type = userImageVo.type;
		userImage.typePath = userImageVo.typePath;
		userImage.size = userImageVo.size;
		userImage.path = userImageVo.path;
		userImage.thumbNail = userImageVo.thumbNail;
		userImage.mapFile = userImageVo.mapFile;
		
		return userImage;
		
	}
	
	public UserImageVO copyFromEntityToVo(UserImage userImage)
	{
		UserImageVO userImageVo = new UserImageVO();
		userImageVo.id = userImage.id;
		userImageVo.fileUsed = userImage.fileUsed;
		userImageVo.usage = userImage.usage;
		userImageVo.name = userImage.name;
		userImageVo.type = userImage.type;
		userImageVo.typePath = userImage.typePath;
		userImageVo.size = userImage.size;
		userImageVo.path = userImage.path;
		userImageVo.thumbNail = userImage.thumbNail;
		userImageVo.mapFile = userImage.mapFile;
		
		return userImageVo;
	}
	@Transient
    public static String getBloodGroupImageFile()
    {
    	return "/images/common/Blood.png"; 
    }
}