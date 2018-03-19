package com.hyniva.sms.ws.vo;

import com.churchgroup.common.constants.Constants;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class UserImageVO extends SMSBaseVO {
	
	public long id;
	public String fileUsed = Constants.FILE_TYPE_IMAGE;
    public String usage="Y";
    public String name;
    public String type;
    public String typePath;
    public long size;
    public String path;
    public String thumbNail;
    public String mapFile;
    
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFileUsed() {
		return fileUsed;
	}
	public void setFileUsed(String fileUsed) {
		this.fileUsed = fileUsed;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypePath() {
		return typePath;
	}
	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getThumbNail() {
		return thumbNail;
	}
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
	public String getMapFile() {
		return mapFile;
	}
	public void setMapFile(String mapFile) {
		this.mapFile = mapFile;
	}

    
    
}
