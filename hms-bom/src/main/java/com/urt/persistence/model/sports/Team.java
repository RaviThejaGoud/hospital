package com.urt.persistence.model.sports;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.sports.TeamVO;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name="team")
public class Team  extends PersistentObject {
	
	private static final long serialVersionUID = 2058384971464433978L;
	protected String teamName;
	protected Long custId;
	//protected Long sportsId;
	protected Long academicYearId;
	protected Set<TeamPlayers> teamPlayers;
	protected Sport sports;
	
	@OneToOne
	@JoinColumn(name="sportId", insertable=true, updatable=true) 
	public Sport getSports() {
		return sports;
	}
	public void setSports(Sport sports) {
		this.sports = sports;
	}
	@Transient
	public String getSportName() {
		if(!ObjectFunctions.isNullOrEmpty(this.sports))
		{
			return this.sports.getSportName();
		}
		return "";
	}
	/**
	 * @return the sportsId
	 *//*
	public Long getSportsId() {
		return sportsId;
	}
	*//**
	 * @param sportsId the sportsId to set
	 *//*
	public void setSportsId(Long sportsId) {
		this.sportsId = sportsId;
	}
*/
	
	@Column(name="teamName",length=50 ,nullable=false)
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	
	/**
	 * @return the academicYearId
	 */
	public Long getAcademicYearId() {
		return academicYearId;
	}
	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
 	@JoinColumn(name="teamId")
	public Set<TeamPlayers> getTeamPlayers() {
		return teamPlayers;
	}
	
	public void setTeamPlayers(Set<TeamPlayers> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}

	public void addTeamPlayersDetails(TeamPlayers teamPlayers) {
		if (ObjectFunctions.isNullOrEmpty(this.teamPlayers)) {
			this.teamPlayers = new HashSet<TeamPlayers>();
		}
		getTeamPlayers().add(teamPlayers);
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
	public TeamVO copyFromEntityToVo()
	{
		TeamVO teamVO= new TeamVO();
		teamVO.setId(this.getId());
		teamVO.setTeamName(this.getTeamName());
		teamVO.setCustId(this.getCustId());
		//teamVO.setSportsId(this.getSportsId());
		teamVO.setAcademicYearId(this.getAcademicYearId());
		return teamVO;
	}
	@Transient
	public Team copyFromVoToEntity(TeamVO teamVO)
	{
		this.setCustId(teamVO.getCustId());
		//this.setSportsId(Long.valueOf(teamVO.getSportsId()));
	//	this.setSportsId(teamVO.getSportsId());
		this.setTeamName(teamVO.getTeamName());
		this.setAcademicYearId(teamVO.getAcademicYearId());
		this.setLastAccessDate(new Date());
		return this;
	}
	
	
	
	
}
