package com.urt.persistence.model.sports;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.sports.TournamentVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Attachment;

@Entity
@Table(name = "tournament")
public class Tournament extends PersistentObject {

	private static final long serialVersionUID = 11L;
	

	protected String tournamentName;
	protected Long custId;
	protected Date startDate;
	protected Date endDate;
	//protected Sport sport;
	protected Team team;
	protected Address address;
	protected List<Attachment> attachments;
	protected AcademicYear academicYear;
	
	@Column(name = "tournamentName",length=128 ,nullable=false)
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
	
	/*@OneToOne
	@JoinColumn(name="sportId", insertable=true, updatable=true) 
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}*/
	
	@OneToOne
	@JoinColumn(name="teamId", insertable=true, updatable=true) 
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId") 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	@Transient
	public String getTeamName() {
		if(!ObjectFunctions.isNullOrEmpty(this.team))
		{
			return this.team.getTeamName();
		}
		return "";
	}
	
	
	@OneToOne
	@JoinColumn(name="addressId", insertable=true, updatable=true) 
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="tournamentId")
	public List<Attachment> getAttachments() {
		if(ObjectFunctions.isNullOrEmpty(this.attachments))
		{
	    	 this.attachments=new ArrayList<Attachment>();
	    }
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	/*@OneToOne
	@JoinColumn(name="attachmentId", insertable=true, updatable=true) 
	public Attachment getAttachment() {
		if(ObjectFunctions.isNullOrEmpty(this.attachment))
			this.attachment= new Attachment();
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}*/
	@Override
	public String toString() {
		return "";
	}
	@Override
	public boolean equals(Object o) {
		return false;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	@Override
	public int compareTo(Object object) {
		return 0;
	}
	@Transient
	public TournamentVO copyFromEntityToVo()
	{
		TournamentVO tournamentVO= new TournamentVO();
		tournamentVO.setTournamentId(this.getId());
		tournamentVO.setTournamentName(this.getTournamentName());
		tournamentVO.setCustId(this.getCustId());
		tournamentVO.setUserId(this.getCreatedById());
		tournamentVO.setStartDate(this.getStartDate());
		tournamentVO.setEndDate(this.getEndDate());
		return tournamentVO;
	}
	@Transient
	public Tournament copyFromVoToEntity(TournamentVO tournamentVO)
	{
		this.setCustId(tournamentVO.getCustId());
		this.setCreatedById(tournamentVO.getUserId());
		this.setLastUpdatedById(tournamentVO.getUserId());
		this.setTournamentName(tournamentVO.getTournamentName());
		this.setStartDate(tournamentVO.getStartDate());
		this.setEndDate(tournamentVO.getEndDate());
		return this;
	}
	
	@Transient
    public String getStartDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getStartDate()); 
    }
	@Transient
    public String getEndDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, getEndDate()); 
    }
	/*@Transient
	public String getTournamentAddressDesc() {

		//StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);

		if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
			buffer.append(getAddressLine1());
		}
		if (!StringFunctions.isNullOrEmpty(this.addressLine2)) {
			if (!StringFunctions.isNullOrEmpty(this.addressLine1)) {
				buffer.append(", ");
			}
			buffer.append(getAddressLine2());
		}
		if (!StringFunctions.isNullOrEmpty(this.city)) {
			if (!StringFunctions.isNullOrEmpty(this.addressLine2) || !StringFunctions.isNullOrEmpty(this.addressLine1)) {
				buffer.append(", ");
			}
			buffer.append(getCity());
		}
		if (!StringFunctions.isNullOrEmpty(this.state)) {
			if (!StringFunctions.isNullOrEmpty(this.city)) {
				buffer.append(", ");
			}
			buffer.append(getState());
		}
		if (!StringFunctions.isNullOrEmpty(this.postalCode)) {
			if (!StringFunctions.isNullOrEmpty(this.state)) {
				buffer.append(", ");
			}
			buffer.append(getPostalCode());
		}
		return buffer.toString();
	}*/
	
	@Transient
	public void addAttachments(Attachment tourAttachments) {
		if(ObjectFunctions.isNullOrEmpty(tourAttachments)){
			tourAttachments=new Attachment();
		}
		getAttachments().add(tourAttachments);
	}
	/*@Transient
	public void address(Address tournamentLocations) {
		if(ObjectFunctions.isNullOrEmpty(tournamentLocations)){
			tournamentLocations= new Address();
		}
		getAddress().addAll(tournamentLocations);
	}*/
}
