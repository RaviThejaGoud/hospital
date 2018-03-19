package com.urt.persistence.model.transport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.RouteBoardingPointsVO;
import com.hyniva.sms.ws.vo.RouteVo;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "routeBoardingPoints")
public class RouteBoardingPoints  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    protected boolean status;
    protected String boardingPointName;
    protected String boardingPointStatTime;
    protected String boardingPointEndTime;
    protected String boardingPointReturnStatTime;
    protected String boardingPointReturnEndTime;
    protected long custId;
    //protected TransportFee transportFee;
    protected long academicYearId;
    protected double boardingPointFeeAmount;
    private long boardingPointOrder;
    private Route route;
    
    @Transient
    private String studentsPaidFee;
    
    
    
    
    
	public String getStudentsPaidFee() {
		return studentsPaidFee;
	}


	public void setStudentsPaidFee(String studentsPaidFee) {
		this.studentsPaidFee = studentsPaidFee;
	}


	public RouteBoardingPoints() {
        
    }

   
	public RouteBoardingPoints(long id) {
        setId(id);
    }

	/**
	 * @return the route
	 */
	@ManyToOne
	@JoinColumn(name="routeId", insertable=false, updatable=false)
	public Route getRoute() {
		return route;
	}
	/**
	 * @param route the route to set
	 */
	public void setRoute(Route route) {
		this.route = route;
	}


	@Override
	public int compareTo(Object object) {
		RouteBoardingPoints target = (RouteBoardingPoints) object;
    	if (target != null && this != null)
        {
    		if(this.getBoardingPointOrder() >= target.getBoardingPointOrder())
    			return 1;
    		else
    			return 0;
        }
		return 0;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final RouteBoardingPoints transport = (RouteBoardingPoints) o;

        if (boardingPointName != null ? !boardingPointName.equals(transport.getBoardingPointName()) : transport.getBoardingPointName() != null) return false;

        return true;
    }

    public int hashCode() {
        return (getStrId() != null ? this.getStrId().hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("status", this.status)
        .append("boardingPointName", this.boardingPointName)
        .toString();
	}
	
	
	@Column(name = "status", nullable = false, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}


	/**
	 * @return the BoardingPointName
	 */
	@Column(name = "boardingPointName", nullable = true, length = 40)
	public String getBoardingPointName() {
		return boardingPointName;
	}


	/**
	 * @param BoardingPointName the BoardingPointName to set
	 */
	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}


	/**
	 * @return the bordingPointStatTime
	 */
	public String getBoardingPointStatTime() {
		return boardingPointStatTime;
	}


	/**
	 * @param bordingPointStatTime the bordingPointStatTime to set
	 */
	public void setBoardingPointStatTime(String boardingPointStatTime) {
		this.boardingPointStatTime = boardingPointStatTime;
	}


	/**
	 * @return the bordingPointEndTime
	 */
	public String getBoardingPointEndTime() {
		return boardingPointEndTime;
	}


	/**
	 * @param bordingPointEndTime the bordingPointEndTime to set
	 */
	public void setBoardingPointEndTime(String boardingPointEndTime) {
		this.boardingPointEndTime = boardingPointEndTime;
	}


	/**
	 * @return the bordingPointReturnStatTime
	 */
	public String getBoardingPointReturnStatTime() {
		return boardingPointReturnStatTime;
	}


	/**
	 * @param bordingPointReturnStatTime the bordingPointReturnStatTime to set
	 */
	public void setBoardingPointReturnStatTime(String boardingPointReturnStatTime) {
		this.boardingPointReturnStatTime = boardingPointReturnStatTime;
	}


	/**
	 * @return the bordingPointReturnEndTime
	 */
	public String getBoardingPointReturnEndTime() {
		return boardingPointReturnEndTime;
	}


	/**
	 * @param bordingPointReturnEndTime the bordingPointReturnEndTime to set
	 */
	public void setBoardingPointReturnEndTime(String boardingPointReturnEndTime) {
		this.boardingPointReturnEndTime = boardingPointReturnEndTime;
	}
	
	@Column(name = "custId", nullable = false, length = 10)
	 public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	/*@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="transportFeeId", insertable=true, updatable=true)
	public TransportFee getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(TransportFee transportFee) {
		this.transportFee = transportFee;
	}*/
	@Column(name = "academicYearId", nullable = false, length = 10)
	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Column(name = "boardingPointFeeAmount",nullable = false,columnDefinition="double default 0")
	public double getBoardingPointFeeAmount() {
		return boardingPointFeeAmount;
	}


	public void setBoardingPointFeeAmount(double boardingPointFeeAmount) {
		this.boardingPointFeeAmount = boardingPointFeeAmount;
	}


	@Column(name = "boardingPointOrder", columnDefinition="int(4) default 0")
	public long getBoardingPointOrder() {
		return boardingPointOrder;
	}

	public void setBoardingPointOrder(long boardingPointOrder) {
		this.boardingPointOrder = boardingPointOrder;
	}
	public void copyFrom(RouteBoardingPoints boardingPoint){
		setBoardingPointEndTime(boardingPoint.getBoardingPointEndTime());
		setBoardingPointFeeAmount(boardingPoint.getBoardingPointFeeAmount());
		setBoardingPointName(boardingPoint.getBoardingPointName());
		setBoardingPointOrder(boardingPoint.getBoardingPointOrder());
		setBoardingPointReturnEndTime(boardingPoint.getBoardingPointReturnEndTime());
		setBoardingPointReturnStatTime(boardingPoint.getBoardingPointReturnStatTime());
		setBoardingPointStatTime(boardingPoint.getBoardingPointStatTime());
		setCustId(boardingPoint.getCustId());
		setStatus(boardingPoint.getStatus());
	}
	
	
	
	public RouteBoardingPointsVO copyFromEntityToVo(RouteBoardingPoints routeBoardingPoints)
	{
		RouteBoardingPointsVO routeBoardingPointsVO = new RouteBoardingPointsVO();
		
		routeBoardingPointsVO.id = routeBoardingPoints.id;
		routeBoardingPointsVO.status = routeBoardingPoints.status;
		routeBoardingPointsVO.boardingPointName = routeBoardingPoints.boardingPointName;
		routeBoardingPointsVO.boardingPointStatTime = routeBoardingPoints.boardingPointStatTime;
		routeBoardingPointsVO.boardingPointEndTime = routeBoardingPoints.boardingPointEndTime;
		routeBoardingPointsVO.boardingPointReturnStatTime = routeBoardingPoints.boardingPointReturnStatTime;
		routeBoardingPointsVO.boardingPointReturnEndTime = routeBoardingPoints.boardingPointReturnEndTime;
		routeBoardingPointsVO.custId = routeBoardingPoints.custId;
		routeBoardingPointsVO.academicYearId = routeBoardingPoints.academicYearId;
		routeBoardingPointsVO.boardingPointFeeAmount = routeBoardingPoints.boardingPointFeeAmount;
		routeBoardingPointsVO.boardingPointOrder = routeBoardingPoints.boardingPointOrder;
		//routeBoardingPointsVO.route = routeBoardingPoints.route;
		
		if(!ObjectFunctions.isNullOrEmpty(routeBoardingPoints.getRoute()))
		{
			RouteVo routeVo = new RouteVo();
			routeVo.id = routeBoardingPoints.getRoute().getId();
			routeVo.status = routeBoardingPoints.getRoute().status;
			routeVo.routePointName = routeBoardingPoints.getRoute().routePointName;
			routeVo.routeEndName = routeBoardingPoints.getRoute().routeEndName;
			routeVo.routeName = routeBoardingPoints.getRoute().routeName;
			routeVo.routePointStartTime = routeBoardingPoints.getRoute().routePointStartTime;
			routeVo.routePointEndTime = routeBoardingPoints.getRoute().routePointEndTime;
			routeVo.routePointReturnStartTime = routeBoardingPoints.getRoute().routePointReturnStartTime;
			routeVo.routePointReturnEndTime = routeBoardingPoints.getRoute().routePointReturnEndTime;
			routeVo.routePointFeeAmount = routeBoardingPoints.getRoute().routePointFeeAmount;
			routeVo.custId = routeBoardingPoints.getRoute().custId;
			routeVo.academicYearId = routeBoardingPoints.getRoute().academicYearId;
			routeVo.routeStartTimeinMns = routeBoardingPoints.getRoute().routeStartTimeinMns;
			routeVo.routeEndTimeinMns = routeBoardingPoints.getRoute().routeEndTimeinMns;
			routeBoardingPointsVO.setRouteVo(routeVo);
		}
		return routeBoardingPointsVO;
	}
	
	public RouteBoardingPoints copyFromVoToEntity(RouteBoardingPoints routeBoardingPoints, RouteBoardingPointsVO routeBoardingPointsVO)
	{
		if(routeBoardingPoints.getId() == 0)
			routeBoardingPoints.id = routeBoardingPointsVO.id;
		routeBoardingPoints.status = routeBoardingPointsVO.status;
		routeBoardingPoints.boardingPointName = routeBoardingPointsVO.boardingPointName;
		routeBoardingPoints.boardingPointStatTime = routeBoardingPointsVO.boardingPointStatTime;
		routeBoardingPoints.boardingPointEndTime = routeBoardingPointsVO.boardingPointEndTime;
		routeBoardingPoints.boardingPointReturnStatTime = routeBoardingPointsVO.boardingPointReturnStatTime;
		routeBoardingPoints.boardingPointReturnEndTime = routeBoardingPointsVO.boardingPointReturnEndTime;
		routeBoardingPoints.custId = routeBoardingPointsVO.custId;
		routeBoardingPoints.academicYearId = routeBoardingPointsVO.academicYearId;
		routeBoardingPoints.boardingPointFeeAmount = routeBoardingPointsVO.boardingPointFeeAmount;
		routeBoardingPoints.boardingPointOrder = routeBoardingPointsVO.boardingPointOrder;
		//routeBoardingPointsVO.route = routeBoardingPoints.route;
		
		return routeBoardingPoints;
	}
}
    

  

