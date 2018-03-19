/*************************************************************************
* Copyright (C) 2015
* ACG
* All Rights Reserved 
*
* File: UserVO.java  
*************************************************************************
* Ver     Date          Name                      Description
* --- ------- ------------ ----------------------------------------------
* 0.1   June 18, 2015    Sreeram		       Created
**************************************************************************/
package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;



public class ShareUserActivitiesVO{
	
	private long id;
	private String description;
    private String status;
    private String messageDate;
    private long custId;
    protected List<ShareUserActivitiesCommentsVO> shareUserActivitiesCommentsVoList;
    private UserVO accountVO;
    private String messageType;
    private String imagePath;
    private String imageName;
    private String imageType;
    private String eventName;
    private String startDateTime;
    private String endDateTime;
    private String eventLocation;
    private String visibleType;
    private String createdDate;
    private String messageDateStr;
    protected List<String> allImagesPathList;
    private String adjustedStudentImagePath;
    
    
    
    
    
    
	 
	public String getAdjustedStudentImagePath() {
		return adjustedStudentImagePath;
	}
	public void setAdjustedStudentImagePath(String adjustedStudentImagePath) {
		this.adjustedStudentImagePath = adjustedStudentImagePath;
	}
	public List<String> getAllImagesPathList() {
		if(ObjectFunctions.isNullOrEmpty(this.allImagesPathList))
		{
			this.allImagesPathList = new ArrayList<String>(); 
		}
		return allImagesPathList;
	}
	public void setAllImagesPathList(List<String> allImagesPathList) {
		this.allImagesPathList = allImagesPathList;
	}
	public String getMessageDateStr() {
		return messageDateStr;
	}
	public void setMessageDateStr(String messageDateStr) {
		this.messageDateStr = messageDateStr;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getVisibleType() {
		return visibleType;
	}
	public void setVisibleType(String visibleType) {
		this.visibleType = visibleType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public List<ShareUserActivitiesCommentsVO> getShareUserActivitiesCommentsVoList() {
		if(ObjectFunctions.isNullOrEmpty(this.shareUserActivitiesCommentsVoList))
		{
			this.shareUserActivitiesCommentsVoList = new ArrayList<ShareUserActivitiesCommentsVO>(); 
		}
		return shareUserActivitiesCommentsVoList;
	}
	public void setShareUserActivitiesCommentsVoList(List<ShareUserActivitiesCommentsVO> shareUserActivitiesCommentsVoList) { 
		this.shareUserActivitiesCommentsVoList = shareUserActivitiesCommentsVoList;
	}
	 
	public UserVO getAccountVO() {
		return accountVO;
	}
	public void setAccountVO(UserVO accountVO) {
		this.accountVO = accountVO;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
}