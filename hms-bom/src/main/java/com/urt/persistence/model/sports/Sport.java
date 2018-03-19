package com.urt.persistence.model.sports;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hyniva.sms.ws.vo.sports.SportsVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Staff;

@Entity
@Table(name = "sports")
public class Sport extends PersistentObject {

	private static final long serialVersionUID = 11L;
	

	protected String sportName;
	protected  Integer numberOfPlayers;
	protected Long custId;
	protected List<Staff> coachList;
	
	@Column(name = "sportName",length=40 ,nullable=false)
	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	@Column(name = "noOfPlayers")
	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "sportsCoach", joinColumns = { @JoinColumn(name = "sportId") }, inverseJoinColumns = { @JoinColumn(name = "coachId") })
	public List<Staff> getCoachList() {
		return coachList;
	}

	public void setCoachList(List<Staff> coachList) {
		this.coachList = coachList;
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
	public SportsVO copyFromEntityToVo()
	{
		
		SportsVO sportsVo= new SportsVO();
		sportsVo.setId(this.getId());
		sportsVo.setNumberOfPlayers(this.getNumberOfPlayers());
		sportsVo.setCustId(this.getCustId());
		sportsVo.setSportName(this.getSportName());
	/*	sportsVo.coachList=sports.coachList;*/
		return sportsVo;
	}
	@Transient
	public Sport copyFromVoToEntity(SportsVO sportsVO)
	{
		this.setCustId(sportsVO.getCustId());//custId = sportsVO.getCustId();
		this.setSportName(sportsVO.getSportName());//sportName = sportsVO.getSportName();
		this.setNumberOfPlayers(sportsVO.getNumberOfPlayers());//(sportsVO.getNumberOfPlayers());numberOfPlayers = sportsVO.getNumberOfPlayers();
		this.setLastAccessDate(new Date());
		this.setLastUpdatedDate(new Date());
	/*	this.setCoachList(getCoachList());*/
		return this;
	}
	
}
