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

import java.util.Date;

import org.springframework.security.core.userdetails.User;



public class ShareUserActivitiesCommentsVO{
	
	private String commentDescription;
    private String status;
    private Date commentDate;
    private long custId;
    private long id;
    private User commentUserAccount;
    private String commentDateStr;
    
    
    
	public String getCommentDateStr() {
		return commentDateStr;
	}
	public void setCommentDateStr(String commentDateStr) {
		this.commentDateStr = commentDateStr;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCommentDescription() {
		return commentDescription;
	}
	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public User getCommentUserAccount() {
		return commentUserAccount;
	}
	public void setCommentUserAccount(User commentUserAccount) {
		this.commentUserAccount = commentUserAccount;
	}
    
    
    
}