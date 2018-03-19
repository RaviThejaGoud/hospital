package com.hyniva.sms.ws.vo;

import java.util.Date;

public class SocialDiscussionsCommentsVO {

	 private String commentDescription;
     private String status;
     private Date commentDate;
     private long custId;
     private UserVO commentUserAccountVo;
     private long id;
     
     
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
	public UserVO getCommentUserAccountVo() {
		return commentUserAccountVo;
	}
	public void setCommentUserAccountVo(UserVO commentUserAccountVo) {
		this.commentUserAccountVo = commentUserAccountVo;
	}
     
}
