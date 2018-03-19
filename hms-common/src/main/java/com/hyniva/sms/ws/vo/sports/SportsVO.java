package com.hyniva.sms.ws.vo.sports;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.StaffVO;



public class SportsVO {
	
	private Long id;
	private String sportName;
	private Integer numberOfPlayers;
	private Long custId;
	private List<StaffVO> coachList;
	protected List<String> coachIds;
	//private String coachIds;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the numberOfPlayers
	 */
	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}
	/**
	 * @param numberOfPlayers the numberOfPlayers to set
	 */
	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
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
	 * @return the coachList
	 */
	public List<StaffVO> getCoachList() {
		if(ObjectFunctions.isNullOrEmpty(coachList)){
		this.coachList=new ArrayList<StaffVO>();
		}
		return coachList;
	}
	/**
	 * @param coachList the coachList to set
	 */
	public void setCoachList(List<StaffVO> coachList) {
		this.coachList = coachList;
	}
	/**
	 * @return the coachIds
	 */
	public List<String> getCoachIds() {
		if(ObjectFunctions.isNullOrEmpty(this.coachIds)){
			this.coachIds=new ArrayList<String>();
		}
		return coachIds;
	}
	/**
	 * @param coachIds the coachIds to set
	 */
	public void setCoachIds(List<String> coachIds) {
		this.coachIds = coachIds;
	}
	
	
	
}
