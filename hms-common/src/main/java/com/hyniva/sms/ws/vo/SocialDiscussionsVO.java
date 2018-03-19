package com.hyniva.sms.ws.vo;

import java.util.Date;
import java.util.List;

public class SocialDiscussionsVO {
	private long id;
	private String description;
    private String status;
    private Date messageDate;
    private String messageDateStr;
    private long custId;
    private UserVO accountVo;
    private String subject;
    private String attachmentPath;
    private String attachmentName;
    private String attachmentType;
    private String category;
    private String categoryStr;
    protected List<SocialDiscussionsCommentsVO> socialDiscussionsCommentsVoList;
    private String adjustedStudentImagePath;    
    
	 
	public String getAdjustedStudentImagePath() {
		return adjustedStudentImagePath;
	}
	public void setAdjustedStudentImagePath(String adjustedStudentImagePath) {
		this.adjustedStudentImagePath = adjustedStudentImagePath;
	}
    
    
	public String getMessageDateStr() {
		return messageDateStr;
	}
	public void setMessageDateStr(String messageDateStr) {
		this.messageDateStr = messageDateStr;
	}
	public String getCategoryStr() {
		return categoryStr;
	}
	public void setCategoryStr(String categoryStr) {
		this.categoryStr = categoryStr;
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
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	 
	public UserVO getAccountVo() {
		return accountVo;
	}
	public void setAccountVo(UserVO accountVo) {
		this.accountVo = accountVo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<SocialDiscussionsCommentsVO> getSocialDiscussionsCommentsVoList() {
		return socialDiscussionsCommentsVoList;
	}
	public void setSocialDiscussionsCommentsVoList(
			List<SocialDiscussionsCommentsVO> socialDiscussionsCommentsVoList) {
		this.socialDiscussionsCommentsVoList = socialDiscussionsCommentsVoList;
	}
}
