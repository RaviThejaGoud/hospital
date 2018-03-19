package com.urt.persistence.model.sports;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hyniva.sms.ws.vo.sports.TeamVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Student;

@Entity
@Table(name="teamPlayers")
public class TeamPlayers extends PersistentObject{
	
	protected String playerStatus;
	protected Student player;

	/**
	 * @return the player
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="players",insertable=true, updatable=true)
	public Student getPlayer() {
		return player;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Student player) {
		this.player = player;
	}
	
	//protected List<Student> playersIds;
	protected Long teamId;
	
	
	
	@Column(name = "playerStatus", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getPlayerStatus() {
		return playerStatus;
	}
	/**
	 * @param playerStatus the playerStatus to set
	 */
	public void setPlayerStatus(String playerStatus) {
		this.playerStatus = playerStatus;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Transient
	public TeamVO copyFromEntityToVo()
	{
		
		TeamVO teamVO= new TeamVO();
		teamVO.setId(this.getId());
		return teamVO;
	}
	@Transient
	public TeamPlayers copyFromVoToEntity(Team teamVO)
	{
		this.setTeamId(teamVO.getId());
		return this;
	}
	/**
	 * @return the teamId
	 */
	public Long getTeamId() {
		return teamId;
	}
	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	
	
}
