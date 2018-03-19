package com.hyniva.sms.ws.vo.sports;

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
import java.beans.Transient;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AddressVO;
import com.hyniva.sms.ws.vo.AttachmentVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class TournamentVO extends SMSBaseVO {

	private static final long serialVersionUID = 11L;

	private static final String DEFAULT_BUFFER_SIZE = null;
	
	protected Long tournamentId;
	protected String tournamentName;
	protected Long custId;
	protected Date startDate;
	protected Date endDate;
	protected TeamVO teamVO;
	protected AddressVO addressVO;
	//protected AttachmentVO attachmentVO;
	protected List<AttachmentVO> attachmentsVO;
	protected Long userId;
	private File upload;
	private List<File> fileUpload;
	private List<String> fileUploadFileName = new ArrayList<String>();
	//private String tourEditStatus;
	private String tourDeleteStatus;
	
	
	
	
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
	public AddressVO getAddressVO() {
		if(ObjectFunctions.isNullOrEmpty(this.addressVO))
			this.addressVO= new AddressVO();
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public List<AttachmentVO> getAttachmentsVO() {
		if(ObjectFunctions.isNullOrEmpty(attachmentsVO)){
			this.attachmentsVO=new ArrayList<AttachmentVO>();
			}
		return attachmentsVO;
	}
	public void setAttachmentsVO(List<AttachmentVO> attachmentsVO) {
		this.attachmentsVO = attachmentsVO;
	}
	/*public AttachmentVO getAttachmentVO() {
		if(ObjectFunctions.isNullOrEmpty(this.attachmentVO))
			this.attachmentVO= new AttachmentVO();
		return attachmentVO;
	}
	public void setAttachmentVO(AttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}*/
	public TeamVO getTeamVO() {
		if(ObjectFunctions.isNullOrEmpty(this.teamVO))
			this.teamVO= new TeamVO();
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
	/*public String getTourEditStatus() {
		return tourEditStatus;
	}
	public void setTourEditStatus(String tourEditStatus) {
		
		this.tourEditStatus = tourEditStatus;
	}*/
	public String getTourDeleteStatus() {
		return tourDeleteStatus;
	}
	public void setTourDeleteStatus(String tourDeleteStatus) {
		this.tourDeleteStatus = tourDeleteStatus;
	}
	// for getting the full address what we are setting in the address.
	@Transient
	public String getTourLocationAddressDesc() {

		StringBuffer buffer = new StringBuffer();

		if (!StringFunctions.isNullOrEmpty(addressVO.addressLine1)) {
			buffer.append(getAddressVO().getAddressLine1());
		}
		if (!StringFunctions.isNullOrEmpty(addressVO.addressLine2)) {
			if (!StringFunctions.isNullOrEmpty(addressVO.addressLine1)) {
				buffer.append(", ");
			}
			buffer.append(getAddressVO().getAddressLine2());
		}
		if (!StringFunctions.isNullOrEmpty(addressVO.city)) {
			if (!StringFunctions.isNullOrEmpty(addressVO.addressLine2) || !StringFunctions.isNullOrEmpty(getAddressVO().addressLine1)) {
				buffer.append(", ");
			}
			buffer.append(getAddressVO().getCity());
		}
		if (!StringFunctions.isNullOrEmpty(addressVO.postalCode)) {
			if (!StringFunctions.isNullOrEmpty(addressVO.city)) {
				buffer.append("-");
			}
			buffer.append(getAddressVO().getPostalCode());
		}
		return buffer.toString();
	}
	
	public List<File> getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	@Transient
    public String getStartDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getStartDate()); 
    }
	@Transient
    public String getEndDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getEndDate()); 
    }
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	
}
