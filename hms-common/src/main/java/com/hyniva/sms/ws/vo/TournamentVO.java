package com.hyniva.sms.ws.vo;

/********************************************************************
 * Copyright (C) 2017-18
 * Hyniva
 * All Rights Reserved 
 *
 * File: Tournament.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  1.0  Jul 25, 2017       Siva Nagaraju G       Created
/********************************************************************/
import java.io.File;
import java.util.Date;

import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.hyniva.sms.ws.vo.sports.SportsVO;
import com.hyniva.sms.ws.vo.sports.TeamVO;


public class TournamentVO extends SMSBaseVO {

	private static final long serialVersionUID = 11L;
	
	protected Long tournamentId;
	protected String tournamentName;
	protected Long custId;
	protected Date startDate;
	protected Date endDate;
	protected SportsVO sportsVO;
	protected TeamVO teamVO;
	protected AddressVO addressVO;
	protected AttachmentVO attachmentVO;
	protected Long userId;
	private File upload;
	
	public Long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
	public String getTournamentName() {
		return tournamentName;
	}
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public SportsVO getSportsVO() {
		return sportsVO;
	}
	public void setSportsVO(SportsVO sportsVO) {
		this.sportsVO = sportsVO;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public AttachmentVO getAttachmentVO() {
		return attachmentVO;
	}
	public void setAttachmentVO(AttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}
	public TeamVO getTeamVO() {
		return teamVO;
	}
	public void setTeamVO(TeamVO teamVO) {
		this.teamVO = teamVO;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
