package com.urt.persistence.model.sports;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.sports.AchievementVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Attachment;
@Entity
@Table(name="achievements")
public class Achievement extends PersistentObject{
	
	protected Team team;
	protected Tournament tournament;
	protected String description;
	protected Long custId;
	protected AcademicYear academicYear;
	protected List<Attachment> attachments;
	
	@OneToOne
	@JoinColumn(name="teamId", insertable=true, updatable=true) 
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@OneToOne
	@JoinColumn(name="tournamentId", insertable=true, updatable=true) 
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId") 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="achievementId")
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
	public AchievementVO copyFromEntityToVo()
	{
		AchievementVO achievementVO= new AchievementVO();
		achievementVO.setId(this.getId());
		achievementVO.setCustId(this.getCustId());
		achievementVO.setDescription(this.getDescription());
	//	achievementVO.getTeamVO().setId(this.getTeam().getId());
	//	achievementVO.getTournamentVO().setTournamentId(this.getTournament().getId());
		return achievementVO;
	}
	@Transient
	public Achievement copyFromVoToEntity(AchievementVO achievementVO)
	{
		this.setCustId(achievementVO.getCustId());
		this.setDescription(achievementVO.getDescription());
		return this;
	}
	
	@Transient
	public void addAttachments(Attachment tourAttachments) {
		if(ObjectFunctions.isNullOrEmpty(tourAttachments)){
			tourAttachments=new Attachment();
		}
		getAttachments().add(tourAttachments);
	}
	
}
