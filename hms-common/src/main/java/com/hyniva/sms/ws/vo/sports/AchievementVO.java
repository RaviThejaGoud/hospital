package com.hyniva.sms.ws.vo.sports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.AcademicYearVo;
import com.hyniva.sms.ws.vo.AttachmentVO;

public class AchievementVO {
	
	private Long id;
	private TeamVO teamVO;
	private TournamentVO tournamentVO;
	private String description;
	private List<File> fileUpload;
	private List<AttachmentVO> attachmentVOs;
	private List<String> fileUploadFileName = new ArrayList<String>();
	private AcademicYearVo academicYearVo;
	private Long custId;
	protected Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TeamVO getTeamVO() {
		if(ObjectFunctions.isNullOrEmpty(this.teamVO))
			this.teamVO= new TeamVO();
		return teamVO;
	}
	public void setTeamVO(TeamVO teamVO) {
		this.teamVO = teamVO;
	}
	public TournamentVO getTournamentVO() {
		if(ObjectFunctions.isNullOrEmpty(this.tournamentVO))
			this.tournamentVO= new TournamentVO();
		return tournamentVO;
	}
	public void setTournamentVO(TournamentVO tournamentVO) {
		
		this.tournamentVO = tournamentVO;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<File> getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}
	public List<AttachmentVO> getAttachmentVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.attachmentVOs))
			this.attachmentVOs=new ArrayList<AttachmentVO>();
		return attachmentVOs;
	}
	public void setAttachmentVOs(List<AttachmentVO> attachmentVOs) {
		this.attachmentVOs = attachmentVOs;
	}
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	public AcademicYearVo getAcademicYearVo() {
		return academicYearVo;
	}
	public void setAcademicYearVo(AcademicYearVo academicYearVo) {
		this.academicYearVo = academicYearVo;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	
	
}
