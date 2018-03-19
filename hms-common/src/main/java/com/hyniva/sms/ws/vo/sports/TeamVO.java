package com.hyniva.sms.ws.vo.sports;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;


public class TeamVO {
	
	
	private Long id;
	private String teamName;
	private Long sportsId;
	private Long custId;
	private Long academicYearId;
	private List<PlayersVO> playersList;
	private String sportName;
	private String playerStatus;
	private SportsVO sportsVO;
	protected List<String> studentIds;
	protected Long captainId;
	protected Long viceCaptainId;
	
	public SportsVO getSportsVO() {
		if(ObjectFunctions.isNullOrEmpty(this.sportsVO))
			this.sportsVO= new SportsVO();
		return sportsVO;
	}
	public void setSportsVO(SportsVO sportsVO) {
		this.sportsVO = sportsVO;
	}
	/**
	 * @return the playerStatus
	 */
	public String getPlayerStatus() {
		return playerStatus;
	}
	/**
	 * @param playerStatus the playerStatus to set
	 */
	public void setPlayerStatus(String playerStatus) {
		this.playerStatus = playerStatus;
	}
	/**
	 * @return the sportName
	 */
	public String getSportName() {
		return sportName;
	}
	/**
	 * @param sportName the sportName to set
	 */
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	/**
	 * @return the playersList
	 */
	public List<PlayersVO> getPlayersList() {
		if(ObjectFunctions.isNullOrEmpty(playersList)){
			this.playersList=new ArrayList<PlayersVO>();
			}
		return playersList;
	}
	/**
	 * @param playersList the playersList to set
	 */
	public void setPlayersList(List<PlayersVO> playersList) {
		this.playersList = playersList;
	}
	
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param id the id to set
	 */
	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	/**
	 * @return the sportsId
	 */
	public Long getSportsId() {
		return sportsId;
	}
	/**
	 * @param sportsId the sportsId to set
	 */
	public void setSportsId(Long sportsId) {
		this.sportsId = sportsId;
	}
	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
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
	/**
	 * @return the playersList
	 *//*
	public List<StudentVo> getPlayersList() {
		if(ObjectFunctions.isNullOrEmpty(playersList)){
			this.playersList=new ArrayList<StudentVo>();
			}
		return playersList;
	}
	*//**
	 * @param playersList the playersList to set
	 *//*
	public void setPlayersList(List<StudentVo> playersList) {
		this.playersList = playersList;
	}
	*/
	/**
	 * @return the captainId
	 */
	public Long getCaptainId() {
		return captainId;
	}
	/**
	 * @param captainId the captainId to set
	 */
	public void setCaptainId(Long captainId) {
		this.captainId = captainId;
	}
	/**
	 * @return the viceCaptainId
	 */
	public Long getViceCaptainId() {
		return viceCaptainId;
	}
	/**
	 * @param viceCaptainId the viceCaptainId to set
	 */
	public void setViceCaptainId(Long viceCaptainId) {
		this.viceCaptainId = viceCaptainId;
	}
	/**
	 * @return the studentIds
	 */
	public List<String> getStudentIds() {
		return studentIds;
	}
	/**
	 * @param studentIds the studentIds to set
	 */
	public void setStudentIds(List<String> studentIds) {
		this.studentIds = studentIds;
	}
	
	public String getTeamWithSport(){
		if(!ObjectFunctions.isNullOrEmpty(this.getTeamName()) && !ObjectFunctions.isNullOrEmpty(this.getSportName())){
				return this.getTeamName() + " - "+ this.getSportName();
		}
		return null;
	}
	
}
